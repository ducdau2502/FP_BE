package gethigh.fp_be.model;

import javax.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    private Double totalPrice;

    @ManyToOne
    private AccountDetail accountDetail;

    @ManyToOne
    private Product product;

    public Cart() {
    }

    public Cart(Long id, Long quantity, AccountDetail accountDetail, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.accountDetail = accountDetail;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public AccountDetail getAccountDetail() {
        return accountDetail;
    }

    public void setAccountDetail(AccountDetail accountDetail) {
        this.accountDetail = accountDetail;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getTotalPrice() {
        return (this.quantity * product.getPrice());
    }
}
