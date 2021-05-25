package zako.uz.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountantRequest {
    private Long id;
    private String textUz;
    private String textRu;
    private String  accountantAttachHashCode;
    private String reportAttachHashCode;
}
