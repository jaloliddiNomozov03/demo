package zako.uz.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenderRequest {
    private Long id;
    private String titleUz;
    private String titleRu;
    private String hashCode;
    private String startDate;
    private String finishedDate;
}
