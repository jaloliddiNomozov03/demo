package zako.uz.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsReq {
    private Long id;
    private String hashCode;
    private String descriptionUz;
    private String descriptionRu;
}
