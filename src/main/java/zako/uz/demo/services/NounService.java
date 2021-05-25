package zako.uz.demo.services;

import org.springframework.web.multipart.MultipartFile;
import zako.uz.demo.entity.Noun;
import zako.uz.demo.payload.ApiResponse;
import zako.uz.demo.payload.NounRequest;

import java.util.List;

public interface NounService {
    ApiResponse saveNoun(NounRequest nounRequest);
    ApiResponse updateNoun(NounRequest nounReq, Long nounId);
    ApiResponse deleteNoun(Long nounId);
    Noun getNounById(Long nounId);
    List<Noun> getNounsList();
}
