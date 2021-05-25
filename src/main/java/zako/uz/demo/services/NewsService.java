package zako.uz.demo.services;

import org.springframework.web.multipart.MultipartFile;
import zako.uz.demo.entity.News;
import zako.uz.demo.payload.ApiResponse;
import zako.uz.demo.payload.NewsReq;

import java.util.List;

public interface NewsService {
    ApiResponse saveNews(NewsReq news);
    ApiResponse updateNews(NewsReq newsReq, Long newsId);
    ApiResponse deleteNews(Long newsId);
    News getNewsById(Long newsId);
    List<News> getNewsList();
}
