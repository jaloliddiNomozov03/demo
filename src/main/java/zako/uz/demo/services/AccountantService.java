package zako.uz.demo.services;

import org.springframework.web.multipart.MultipartFile;
import zako.uz.demo.entity.Accountant;
import zako.uz.demo.payload.AccountantRequest;
import zako.uz.demo.payload.ApiResponse;

import java.util.List;

public interface AccountantService {
    ApiResponse saveAccountant(AccountantRequest accountantRequest);
    ApiResponse updateAccountant(AccountantRequest accountantRequest,Long accountantId);
    ApiResponse deleteAccountant(Long accountant);
    Accountant getAccountantById(Long accountant);
    List<Accountant> getAccountantsList();
}
