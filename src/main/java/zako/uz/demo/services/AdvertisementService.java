package zako.uz.demo.services;

import zako.uz.demo.entity.Advertisement;
import zako.uz.demo.payload.ApiResponse;

import java.util.List;

public interface AdvertisementService {
    ApiResponse saveAdvertisement(Advertisement advertisement);
    ApiResponse updateAdvertisement(Advertisement advertisement, Long advertisementId);
    ApiResponse deleteAdvertisement(Long advertisementId);
    Advertisement getAdvertisementById(Long advertisementId);
    List<Advertisement> getAdvertisementsList();
}
