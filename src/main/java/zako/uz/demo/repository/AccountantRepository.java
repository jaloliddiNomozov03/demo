package zako.uz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zako.uz.demo.entity.Accountant;

public interface AccountantRepository extends JpaRepository<Accountant, Long> {
}
