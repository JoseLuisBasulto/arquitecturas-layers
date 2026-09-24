package model;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private final int id;
    private final String customerName;
    private final List<Product> productList;
    private OrderState orderState;
    private BigDecimal subtotal;
    private BigDecimal taxes;
    private BigDecimal discount;
    private BigDecimal total;

    public Order(int id, String customerName, List<Product> productList) {
        this.id = id;
        this.customerName = customerName;
        this.productList = productList;
        this.orderState = OrderState.PENDING;
        this.subtotal = BigDecimal.ZERO;
        this.taxes = BigDecimal.ZERO;
        this.discount = BigDecimal.ZERO;
        this.total = BigDecimal.ZERO;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
