package zako.uz.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zako.uz.demo.entity.Noun;
import zako.uz.demo.exception.ResourceNotFoundException;
import zako.uz.demo.payload.ApiResponse;
import zako.uz.demo.payload.NounRequest;
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
    public ApiResponse saveNoun(NounRequest nounRequest) {
        try {
            Noun noun = new Noun();
            noun.setAttachment(attachmentService.findByHashCode(nounRequest.getHashCode()));
            noun.setDescriptionRu(nounRequest.getDescriptionRu());
            noun.setDescriptionUz(nounRequest.getDescriptionUz());
            noun.setDate(nounRequest.getDate());
            nounRepository.save(noun);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            System.out.println(e);
        }
        return new ApiResponse(Boolean.FALSE,"Failed!");
    }

    @Override
    public ApiResponse updateNoun(NounRequest nounReq, Long nounId) {
        try {
            Noun noun = nounRepository.findById(nounId)
                    .orElseThrow(()->new ResourceNotFoundException("Noun","id",nounId));
            noun.setDate(nounReq.getDate());
            noun.setDescriptionUz(nounReq.getDescriptionUz());
            noun.setDescriptionRu(nounReq.getDescriptionRu());
            noun.setAttachment(attachmentService.findByHashCode(nounReq.getHashCode()));
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
            String hashCode = nounRepository.findById(nounId).
                    orElseThrow(()->new ResourceNotFoundException("Noun","Id",nounId)).getAttachment().getHashCode();
            nounRepository.deleteById(nounId);
            attachmentService.delete(hashCode);
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
