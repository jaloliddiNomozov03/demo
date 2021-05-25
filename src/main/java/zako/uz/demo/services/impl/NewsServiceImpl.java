package zako.uz.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zako.uz.demo.entity.News;
import zako.uz.demo.exception.ResourceNotFoundException;
import zako.uz.demo.payload.ApiResponse;
import zako.uz.demo.payload.NewsReq;
import zako.uz.demo.repository.NewsRepository;
import zako.uz.demo.services.AttachmentService;
import zako.uz.demo.services.NewsService;

import java.util.List;
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private AttachmentService attachmentService;
    @Override
    public ApiResponse saveNews(NewsReq newsReq) {
        try {
            News news = new News();
            news.setDescriptionUz(newsReq.getDescriptionUz());
            news.setDescriptionRu(newsReq.getDescriptionRu());
            news.setAttachment(attachmentService.findByHashCode(newsReq.getHashCode()));
            return new ApiResponse(Boolean.TRUE,"Successfully saved");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed");
        }
    }

    @Override
    public ApiResponse updateNews(NewsReq newsReq, Long newsId) {
        try {
            News newNews = newsRepository.findById(newsId)
                    .orElseThrow(()->new ResourceNotFoundException("News","id", newsId));
//            String hashId = newNews.getAttachment().getHashCode();
            newNews.setAttachment(attachmentService.findByHashCode(newsReq.getHashCode()));
            newNews.setDescriptionUz(newsReq.getDescriptionUz());
            newNews.setDescriptionRu(newsReq.getDescriptionRu());
            newsRepository.save(newNews);
            return new ApiResponse(Boolean.TRUE,"Success");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed");
        }
    }

    @Override
    public ApiResponse deleteNews(Long newsId) {
        try {
            String hashId = newsRepository.findById(newsId)
                    .orElseThrow(()->new ResourceNotFoundException("News","Id",newsId)).getAttachment().getHashCode();
            newsRepository.deleteById(newsId);
            attachmentService.delete(hashId);
            return new ApiResponse(Boolean.TRUE,"Success deleted");
        }catch (Exception e){
            return new ApiResponse(Boolean.FALSE,"Failed!");
        }
    }

    @Override
    public News getNewsById(Long newsId) {
        try {
            return newsRepository.findById(newsId).orElseThrow(()->new ResourceNotFoundException("News","newsId", newsId));
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<News> getNewsList() {
        try {
            return newsRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }
}
