package pedromachakio.com.github.rest.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailsDTO {
    private Integer client;
    private BigDecimal totalPrice;
    private List<ProductsOrderedDTO> items;

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ProductsOrderedDTO> getItems() {
        return items;
    }

    public void setItems(List<ProductsOrderedDTO> items) {
        this.items = items;
    }
}
