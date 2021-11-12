package pedromachakio.com.github.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsOrderedDTO {
    private Integer product;
    private Integer quantity;


}
