package zako.uz.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zako.uz.demo.entity.Accountant;
import zako.uz.demo.exception.ResourceNotFoundException;
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
    public ApiResponse saveAccountant(Accountant accountant) {
        try {
            accountantRepository.save(accountant);
            return new ApiResponse(Boolean.TRUE,"Successfully saved");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed");
        }
    }

    @Override
    public ApiResponse updateAccountant(Accountant accountant, Long accountantId) {
        try {
            Accountant newAccountant = accountantRepository.findById(accountantId)
                    .orElseThrow(()->new ResourceNotFoundException("Advertisement","id", accountant));
            newAccountant.setId(accountantId);
            if (accountant.getAccountantAttach()!=null){
                newAccountant.setAccountantAttach(attachmentService.findByHashCode(accountant.getAccountantAttach().getHashCode()));
            }else {
                newAccountant.setAccountantAttach(null);
            }
            if (accountant.getReportAttach()!=null){
                newAccountant.setReportAttach(attachmentService.findByHashCode(accountant.getReportAttach().getHashCode()));
            }else {
                newAccountant.setReportAttach(null);
            }
            newAccountant.setTextUz(accountant.getTextUz());
            newAccountant.setTextRu(accountant.getTextRu());
            accountantRepository.save(newAccountant);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed");
        }
    }

    @Override
    public ApiResponse deleteAccountant(Long accountantId) {
        try {
            accountantRepository.deleteById(accountantId);
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
