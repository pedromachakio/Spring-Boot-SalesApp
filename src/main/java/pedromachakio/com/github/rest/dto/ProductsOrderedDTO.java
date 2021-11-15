package pedromachakio.com.github.rest.dto;

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
