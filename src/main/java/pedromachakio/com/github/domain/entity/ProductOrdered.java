package pedromachakio.com.github.domain.entity;

import javax.persistence.*;

@Entity
public class ProductOrdered {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne // muitos itens pedidos para um produto??
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne // muitos itens pedidos num pedido
    @JoinColumn(name = "ORDER_DETAILS_ID")
    private OrderDetails orderDetails_ProductOrdered;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderDetails getOrderDetails_ProductOrdered() {
        return orderDetails_ProductOrdered;
    }

    public void setOrderDetails_ProductOrdered(OrderDetails orderDetails_ProductOrdered) {
        this.orderDetails_ProductOrdered = orderDetails_ProductOrdered;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
