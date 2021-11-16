package pedromachakio.com.github.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pedromachakio.com.github.validation.NotEmptyList;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    @NotNull(message = "Provide client ID")
    private Integer client;

    @NotNull(message = "Price can't be empty")
    private BigDecimal totalPrice;

    @NotEmptyList(message = "Order must have at least one product.")
    private List<ProductsOrderedDTO> items;


}
