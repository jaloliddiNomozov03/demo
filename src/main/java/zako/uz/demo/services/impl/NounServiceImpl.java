package zako.uz.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zako.uz.demo.entity.Noun;
import zako.uz.demo.exception.ResourceNotFoundException;
import zako.uz.demo.payload.ApiResponse;
import zako.uz.demo.repository.NounRepository;
import zako.uz.demo.services.AttachmentService;
import zako.uz.demo.services.NounService;

import java.util.List;
@Service
public class NounServiceImpl implements NounService {
    @Autowired
    private NounRepository nounRepository;
    @Autowired
    private AttachmentService attachmentService;
    @Override
    public ApiResponse saveNoun(Noun noun) {
        try {
            nounRepository.save(noun);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            System.out.println(e);
        }
        return new ApiResponse(Boolean.FALSE,"Failed!");
    }

    @Override
    public ApiResponse updateNoun(Noun nounReq, Long nounId) {
        try {
            Noun noun = nounRepository.findById(nounId)
                    .orElseThrow(()->new ResourceNotFoundException("Noun","id",nounId));
            noun.setDate(nounReq.getDate());
            if (nounReq.getAttachment()!=null){
                noun.setAttachment(attachmentService.findByHashCode(nounReq.getAttachment().getHashCode()));
            }
            noun.setDescriptionUz(nounReq.getDescriptionUz());
            noun.setDescriptionRu(nounReq.getDescriptionRu());
            nounRepository.save(noun);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            System.out.println(e);
        }
        return new ApiResponse(Boolean.FALSE,"Failed");
    }

    @Override
    public ApiResponse deleteNoun(Long nounId) {
        try {
            nounRepository.deleteById(nounId);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed!");
        }
    }

    @Override
    public Noun getNounById(Long nounId) {
        try {
            return nounRepository.findById(nounId).orElseThrow(()->new ResourceNotFoundException("Noun","Id", nounId));
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Noun> getNounsList() {
        try {
            return nounRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }
}
