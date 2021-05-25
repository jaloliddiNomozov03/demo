package zako.uz.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NounRequest {
    private Long id;
    private String descriptionUz;
    private String descriptionRu;
    private String date;
    private String hashCode;
}
