package gethigh.fp_be.model;


import gethigh.fp_be.model.Bill;
import gethigh.fp_be.model.Product;

import javax.persistence.*;

@Entity
@Table(name = "billDetails")
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double discount;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Bill bill;

    public BillDetail() {
    }

    public BillDetail(Long id,
                      Integer quantity,
                      Double discount,
                      Product product,
                      Bill bill) {
        this.id = id;
        this.quantity = quantity;
        this.discount = discount;
        this.product = product;
        this.bill = bill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

}
