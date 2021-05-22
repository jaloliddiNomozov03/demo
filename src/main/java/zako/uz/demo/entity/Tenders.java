package zako.uz.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tenders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleUz;
    private String titleRu;
    @OneToOne(fetch = FetchType.LAZY)
    private Attachment attachment;
    private String startDate;
    private String finishedDate;
}
