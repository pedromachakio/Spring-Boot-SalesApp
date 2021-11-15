package pedromachakio.com.github.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderedProductInfoDto {

    private String description;
    private BigDecimal unitPrice;
    private Integer quantity;
}
