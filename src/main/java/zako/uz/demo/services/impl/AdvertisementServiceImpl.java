package zako.uz.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zako.uz.demo.entity.Advertisement;
import zako.uz.demo.exception.ResourceNotFoundException;
import zako.uz.demo.payload.AdvertisementRequest;
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
    public ApiResponse saveAdvertisement(AdvertisementRequest advertisementRequest) {
        try {
            Advertisement advertisement = new Advertisement();
            advertisement.setAttachmentPdf(attachmentService.findByHashCode(advertisementRequest.getHashCode()));
            advertisement.setDate(advertisementRequest.getDate());
            advertisement.setTitleRu(advertisementRequest.getTitleRu());
            advertisement.setTitleUz(advertisementRequest.getTitleUz());
            advertisementRepository.save(advertisement);
            return new ApiResponse(Boolean.TRUE,"Successfully saved");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed");
        }
    }

    @Override
    public ApiResponse updateAdvertisement(AdvertisementRequest advertisement, Long advertiseId) {
        try {
            Advertisement newAdvertise = advertisementRepository.findById(advertiseId)
                    .orElseThrow(()->new ResourceNotFoundException("Advertisement","id", advertiseId));
            newAdvertise.setId(advertiseId);
            newAdvertise.setAttachmentPdf(attachmentService.findByHashCode(advertisement.getHashCode()));
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
    public ApiResponse deleteAdvertisement(Long advertiseId) {
        try {
            String hashId = advertisementRepository.findById(advertiseId)
                    .orElseThrow(()->new ResourceNotFoundException("Advertisement","Id",advertiseId)).getAttachmentPdf().getHashCode();
            advertisementRepository.deleteById(advertiseId);
            attachmentService.delete(hashId);
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
