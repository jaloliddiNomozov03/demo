package zako.uz.demo.services;

import org.springframework.web.multipart.MultipartFile;
import zako.uz.demo.entity.Advertisement;
import zako.uz.demo.payload.AdvertisementRequest;
import zako.uz.demo.payload.ApiResponse;

import java.util.List;

public interface AdvertisementService {
    ApiResponse saveAdvertisement(AdvertisementRequest advertisementRequest);
    ApiResponse updateAdvertisement(AdvertisementRequest advertisementRequest, Long advertisementId);
    ApiResponse deleteAdvertisement(Long advertisementId);
    Advertisement getAdvertisementById(Long advertisementId);
    List<Advertisement> getAdvertisementsList();
}
