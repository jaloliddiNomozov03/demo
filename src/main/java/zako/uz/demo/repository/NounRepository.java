package zako.uz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zako.uz.demo.entity.Noun;

public interface NounRepository extends JpaRepository<Noun, Long> {
}
