package zako.uz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zako.uz.demo.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
