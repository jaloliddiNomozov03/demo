package zako.uz.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import zako.uz.demo.entity.Attachment;
import zako.uz.demo.payload.ApiResponse;
import zako.uz.demo.repository.AttachRepository;
import zako.uz.demo.services.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    private AttachRepository attachmentRepository;
    @Autowired
    public AttachmentServiceImpl(AttachRepository attachmentRepository){
        this.attachmentRepository=attachmentRepository;
    }
    @Value("${upload.path}")
    private String filePath;
    @Override
    public ApiResponse save(MultipartFile multipartFile) {
        try {
            Attachment attachment = new Attachment();
            attachment.setContentType(multipartFile.getContentType());
            attachment.setSize(multipartFile.getSize());
            attachment.setHashCode(UUID.randomUUID().toString());
            attachment.setName(multipartFile.getOriginalFilename());

            Path path= Paths.get(this.filePath);
            path=checkPackage(path);
            attachment.setUploadPath(path.toFile().getAbsolutePath());
            attachmentRepository.save(attachment);

            File file = new File(path.toFile().getAbsoluteFile()+"/"+attachment.getHashCode());

            attachmentRepository.save(attachment);
            multipartFile.transferTo(file);
            return new ApiResponse(true, attachment.getHashCode());
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    private Path checkPackage(Path file) {
        if (!file.toFile().exists())
            file.toFile().mkdirs();
        return file;
    }

    @Override
    public ApiResponse delete(String hashCode) {
        try {
            Attachment attachment = attachmentRepository.findByHashCode(hashCode);
            Path path = Paths.get(this.filePath);
//            path=checkPackage(path);
            File file = new File(attachment.getUploadPath()+"/"+attachment.getHashCode());
            if (file.delete()){
                attachmentRepository.deleteById(attachment.getId());
                return new ApiResponse(true, "deleting successful");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return new ApiResponse(false, "deleting failed!");
    }

    @Override
    public Attachment findByHashCode(String hashCode) {
        try {
            return attachmentRepository.findByHashCode(hashCode);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ResponseEntity<InputStreamResource> getFile(String hashCode, HttpServletResponse response) throws IOException {
        Attachment attachment=attachmentRepository.findByHashCode(hashCode);
        if (attachment!=null) {
            Path path = Paths.get(this.filePath);
//            path = checkPackage(path);
            File file = new File(attachment.getUploadPath() + "/" + attachment.getHashCode());
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + attachment.getName());
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentType(MediaType.parseMediaType(attachment.getContentType()))
                    .body(resource);
        }else
            return null;
    }
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ResponseEntity<InputStreamResource> downloadFile(String hashCode, HttpServletResponse response) throws IOException {
        Attachment attachment=attachmentRepository.findByHashCode(hashCode);
        if (attachment!=null) {
            Path path = Paths.get(this.filePath);
            path = checkPackage(path);
            File file = new File(attachment.getUploadPath() + "/" + attachment.getHashCode());
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName());
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentType(MediaType.parseMediaType(attachment.getContentType()))
                    .body(resource);
        }else
            return null;
    }

    @Override
    public Page<Attachment> findFilePage(int page, int size) {
        try {
            Pageable pageable= PageRequest.of(page, size, Sort.by("createAt").descending());
            return attachmentRepository.findAll(pageable);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
