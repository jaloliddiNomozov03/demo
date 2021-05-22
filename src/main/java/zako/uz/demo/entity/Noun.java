package zako.uz.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Noun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descriptionUz;
    private String descriptionRu;
    private String date;
    @OneToOne(fetch = FetchType.LAZY)
    private Attachment attachment;
}
