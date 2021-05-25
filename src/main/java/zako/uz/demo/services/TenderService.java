package zako.uz.demo.services;

import org.springframework.web.multipart.MultipartFile;
import zako.uz.demo.entity.Tenders;
import zako.uz.demo.payload.ApiResponse;
import zako.uz.demo.payload.TenderRequest;

import java.util.List;

public interface TenderService {
    ApiResponse saveTender(TenderRequest tenderRequest);
    ApiResponse updateTender(TenderRequest tenderRequest, Long tenderId);
    ApiResponse deleteTender(Long tenderId);
    Tenders getTendersById(Long tenderId);
    List<Tenders> getTendersList();
}
