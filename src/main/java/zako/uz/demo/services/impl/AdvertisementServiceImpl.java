package zako.uz.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zako.uz.demo.entity.Advertisement;
import zako.uz.demo.exception.ResourceNotFoundException;
import zako.uz.demo.payload.ApiResponse;
import zako.uz.demo.repository.AdvertisementRepository;
import zako.uz.demo.services.AdvertisementService;
import zako.uz.demo.services.AttachmentService;

import java.util.List;
@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private AttachmentService attachmentService;
    @Override
    public ApiResponse saveAdvertisement(Advertisement advertisement) {
        try {
            advertisementRepository.save(advertisement);
            return new ApiResponse(Boolean.TRUE,"Successfully saved");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed");
        }
    }

    @Override
    public ApiResponse updateAdvertisement(Advertisement advertisement, Long advertiseId) {
        try {
            Advertisement newAdvertise = advertisementRepository.findById(advertiseId)
                    .orElseThrow(()->new ResourceNotFoundException("Advertisement","id", advertiseId));
            newAdvertise.setId(advertiseId);
            if (advertisement.getAttachmentPdf()!=null){
                newAdvertise.setAttachmentPdf(attachmentService.findByHashCode(advertisement.getAttachmentPdf().getHashCode()));
            }else {
                newAdvertise.setAttachmentPdf(null);
            }
            newAdvertise.setDescriptionUz(advertisement.getDescriptionUz());
            newAdvertise.setDescriptionRu(advertisement.getDescriptionRu());
            newAdvertise.setTitleUz(advertisement.getTitleUz());
            newAdvertise.setTitleRu(advertisement.getTitleRu());
            newAdvertise.setDate(advertisement.getDate());
            advertisementRepository.save(newAdvertise);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed");
        }
    }

    @Override
    public ApiResponse deleteAdvertisement(Long newsId) {
        try {
            advertisementRepository.deleteById(newsId);
            return new ApiResponse(Boolean.TRUE,"Success deleted");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed!");
        }
    }

    @Override
    public Advertisement getAdvertisementById(Long advertiseId) {
        try {
            return advertisementRepository.findById(advertiseId).orElseThrow(()->new ResourceNotFoundException("Advertisement","AdvertisementId", advertiseId));
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Advertisement> getAdvertisementsList() {
        try {
            return advertisementRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }
}
