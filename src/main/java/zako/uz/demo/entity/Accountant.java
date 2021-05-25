package zako.uz.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Accountant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String textUz;
    private String textRu;
    @OneToOne(fetch = FetchType.LAZY)
    private Attachment accountantAttach;
    @OneToOne(fetch = FetchType.LAZY)
    private Attachment reportAttach;
}
