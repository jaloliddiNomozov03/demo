package zako.uz.demo.services;

import zako.uz.demo.entity.Tenders;
import zako.uz.demo.payload.ApiResponse;

import java.util.List;

public interface TenderService {
    ApiResponse saveTender(Tenders tenders);
    ApiResponse updateTender(Tenders tenders, Long tenderId);
    ApiResponse deleteTender(Long tenderId);
    Tenders getTendersById(Long tenderId);
    List<Tenders> getTendersList();
}
