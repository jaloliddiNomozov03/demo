package zako.uz.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zako.uz.demo.entity.Accountant;
import zako.uz.demo.exception.ResourceNotFoundException;
import zako.uz.demo.payload.AccountantRequest;
import zako.uz.demo.payload.ApiResponse;
import zako.uz.demo.repository.AccountantRepository;
import zako.uz.demo.services.AccountantService;
import zako.uz.demo.services.AttachmentService;

import java.util.List;
@Service
public class AccountantServiceImpl implements AccountantService {
    @Autowired
    private AccountantRepository accountantRepository;
    @Autowired
    private AttachmentService attachmentService;
    @Override
    public ApiResponse saveAccountant(AccountantRequest accountantRequest) {
        try {
            Accountant accountant = new Accountant();
            accountant.setReportAttach(attachmentService.findByHashCode(accountantRequest.getReportAttachHashCode()));
            accountant.setAccountantAttach(attachmentService.findByHashCode(accountantRequest.getAccountantAttachHashCode()));
            accountant.setTextRu(accountantRequest.getTextRu());
            accountant.setTextUz(accountantRequest.getTextUz());
            accountantRepository.save(accountant);
            return new ApiResponse(Boolean.TRUE,"Successfully saved");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed");
        }
    }

    @Override
    public ApiResponse updateAccountant(AccountantRequest accountantRequest, Long accountantId) {
        try {
            Accountant newAccountant = accountantRepository.findById(accountantId)
                    .orElseThrow(()->new ResourceNotFoundException("Advertisement","id", accountantId));
            newAccountant.setReportAttach(attachmentService.findByHashCode(accountantRequest.getReportAttachHashCode()));
            newAccountant.setAccountantAttach(attachmentService.findByHashCode(accountantRequest.getAccountantAttachHashCode()));
            newAccountant.setTextRu(accountantRequest.getTextRu());
            newAccountant.setTextUz(accountantRequest.getTextUz());
            accountantRepository.save(newAccountant);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed");
        }
    }

    @Override
    public ApiResponse deleteAccountant(Long accountantId) {
        try {
            Accountant accountant = accountantRepository.getById(accountantId);
            accountantRepository.deleteById(accountantId);
            attachmentService.delete(accountant.getAccountantAttach().getHashCode());
            attachmentService.delete(accountant.getReportAttach().getHashCode());
            return new ApiResponse(Boolean.TRUE,"Delete success");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed");
        }
    }

    @Override
    public Accountant getAccountantById(Long accountant) {
        try {
            return accountantRepository.findById(accountant)
                    .orElseThrow(()-> new ResourceNotFoundException("Accountant","Id",accountant));
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Accountant> getAccountantsList() {
        try {
            return accountantRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }
}
