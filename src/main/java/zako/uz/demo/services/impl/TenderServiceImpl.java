package zako.uz.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zako.uz.demo.entity.Tenders;
import zako.uz.demo.exception.ResourceNotFoundException;
import zako.uz.demo.payload.ApiResponse;
import zako.uz.demo.payload.TenderRequest;
import zako.uz.demo.repository.TendersRepository;
import zako.uz.demo.services.AttachmentService;
import zako.uz.demo.services.TenderService;

import java.util.List;

@Service
public class TenderServiceImpl implements TenderService {
    @Autowired
    private TendersRepository tendersRepository;
    @Autowired
    private AttachmentService attachmentService;
    @Override
    public ApiResponse saveTender(TenderRequest tenderRequest) {
        try {
            Tenders tenders = new Tenders();
            tenders.setAttachment(attachmentService.findByHashCode(tenderRequest.getHashCode()));
            tenders.setFinishedDate(tenderRequest.getFinishedDate());
            tenders.setStartDate(tenderRequest.getStartDate());
            tenders.setTitleRu(tenderRequest.getTitleRu());
            tenders.setTitleUz(tenderRequest.getTitleUz());
            tendersRepository.save(tenders);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            System.out.println(e);
        }
        return new ApiResponse(Boolean.FALSE,"Failed");
    }

    @Override
    public ApiResponse updateTender(TenderRequest tenderRequest, Long tenderId) {
        try {
            Tenders newTender = tendersRepository.findById(tenderId)
                    .orElseThrow(()->new ResourceNotFoundException("Tenders","Id", tenderId));
            newTender.setTitleUz(tenderRequest.getTitleUz());
            newTender.setTitleRu(tenderRequest.getTitleRu());
            newTender.setStartDate(tenderRequest.getStartDate());
            newTender.setFinishedDate(tenderRequest.getFinishedDate());
            newTender.setAttachment(attachmentService.findByHashCode(tenderRequest.getHashCode()));
            tendersRepository.save(newTender);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            System.out.println(e);
            return new ApiResponse(Boolean.FALSE,"Failed");
        }
    }

    @Override
    public ApiResponse deleteTender(Long tenderId) {
        try {
            String hashCode = tendersRepository.findById(tenderId).
                    orElseThrow(()->new ResourceNotFoundException("Tender","Id",tenderId)).getAttachment().getHashCode();
            tendersRepository.deleteById(tenderId);
            attachmentService.delete(hashCode);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed!");
        }
    }

    @Override
    public Tenders getTendersById(Long tenderId) {
        try {
            return tendersRepository.findById(tenderId)
                    .orElseThrow(()-> new ResourceNotFoundException("Tenders","Id", tenderId));
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Tenders> getTendersList() {
        try {
            return tendersRepository.findAll();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
