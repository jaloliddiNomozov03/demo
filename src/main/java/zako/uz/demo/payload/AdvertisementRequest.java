package zako.uz.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementRequest {
    private String titleUz;
    private String titleRu;
    private String date;
    private String hashCode;
}
