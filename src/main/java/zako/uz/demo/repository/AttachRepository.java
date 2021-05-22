package zako.uz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zako.uz.demo.entity.Attachment;
@Repository
public interface AttachRepository extends JpaRepository<Attachment, Long> {
    Attachment findByHashCode(String hashCode);
    Attachment findByName(String name);
}
