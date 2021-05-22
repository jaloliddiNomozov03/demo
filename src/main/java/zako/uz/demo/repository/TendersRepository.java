package zako.uz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zako.uz.demo.entity.Tenders;

public interface TendersRepository extends JpaRepository<Tenders, Long> {
}
