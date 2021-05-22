package zako.uz.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zako.uz.demo.entity.Tenders;
import zako.uz.demo.exception.ResourceNotFoundException;
import zako.uz.demo.payload.ApiResponse;
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
    public ApiResponse saveTender(Tenders tenders) {
        try {
            tendersRepository.save(tenders);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            System.out.println(e);
        }
        return new ApiResponse(Boolean.FALSE,"Failed");
    }

    @Override
    public ApiResponse updateTender(Tenders tenders, Long tenderId) {
        try {
            Tenders newTender = tendersRepository.findById(tenderId).get();
            if (tenders.getAttachment()!=null){
                newTender.setAttachment(attachmentService.findByHashCode(tenders.getAttachment().getHashCode()));
            }
            newTender.setTitleUz(tenders.getTitleUz());
            newTender.setTitleRu(tenders.getTitleRu());
            newTender.setStartDate(tenders.getStartDate());
            newTender.setFinishedDate(tenders.getFinishedDate());
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
            tendersRepository.deleteById(tenderId);
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
