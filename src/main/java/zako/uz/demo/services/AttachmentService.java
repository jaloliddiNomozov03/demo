package zako.uz.demo.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import zako.uz.demo.entity.Attachment;
import zako.uz.demo.payload.ApiResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AttachmentService {
    ApiResponse save(MultipartFile multipartFile, Boolean auth);
    ApiResponse delete(String hashCode);
    Attachment findByHashCode(String hashCode);
    ResponseEntity<InputStreamResource> getFile(String hashCode, HttpServletResponse response) throws IOException;
    ResponseEntity<InputStreamResource> downloadFile(String hashCode, HttpServletResponse response) throws IOException;
    Page<Attachment> findFilePage(int page, int size);
}
