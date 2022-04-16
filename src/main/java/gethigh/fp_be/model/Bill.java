package gethigh.fp_be.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateCreate;

    private Long quantity;

    private Double totalPrice;

    @ManyToOne
    private AccountDetail customer;

    @ManyToOne
    private Store storeBuy;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Voucher voucher;

    public Bill() {
    }

    public Bill(Long id,
                LocalDate dateCreate,
                AccountDetail customer,
                Store storeBuy) {
        this.id = id;
        this.dateCreate = dateCreate;
        this.customer = customer;
        this.storeBuy = storeBuy;
    }

    public Bill(Long id, LocalDate dateCreate, Long quantity, Double totalPrice, AccountDetail customer, Store storeBuy, Product product, Voucher voucher) {
        this.id = id;
        this.dateCreate = dateCreate;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.storeBuy = storeBuy;
        this.product = product;
        this.voucher = voucher;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountDetail getCustomer() {
        return customer;
    }

    public void setCustomer(AccountDetail customer) {
        this.customer = customer;
    }

    public Store getStoreBuy() {
        return storeBuy;
    }

    public void setStoreBuy(Store storeBuy) {
        this.storeBuy = storeBuy;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
}
