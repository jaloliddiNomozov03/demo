package zako.uz.demo.services;

import zako.uz.demo.entity.Noun;
import zako.uz.demo.payload.ApiResponse;

import java.util.List;

public interface NounService {
    ApiResponse saveNoun(Noun noun);
    ApiResponse updateNoun(Noun nounReq, Long nounId);
    ApiResponse deleteNoun(Long nounId);
    Noun getNounById(Long nounId);
    List<Noun> getNounsList();
}
