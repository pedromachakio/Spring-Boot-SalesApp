package pedromachakio.com.github.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfoDTO {

    private Integer orderId;
    private String taxPayerId;
    private String clientName;
    private BigDecimal totalAmount;
    private String orderDate;
    private String status;
    private List<OrderedProductInfoDto> items;
}
