package zako.uz.demo.services;

import zako.uz.demo.entity.Accountant;
import zako.uz.demo.payload.ApiResponse;

import java.util.List;

public interface AccountantService {
    ApiResponse saveAccountant(Accountant accountant);
    ApiResponse updateAccountant(Accountant accountant, Long accountantId);
    ApiResponse deleteAccountant(Long accountant);
    Accountant getAccountantById(Long accountant);
    List<Accountant> getAccountantsList();
}
