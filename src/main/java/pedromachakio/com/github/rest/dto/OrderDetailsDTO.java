package pedromachakio.com.github.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private Integer client;
    private BigDecimal totalPrice;
    private List<ProductsOrderedDTO> items;


}
