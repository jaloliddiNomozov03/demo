package zako.uz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zako.uz.demo.entity.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
