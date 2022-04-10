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


    @ManyToOne
    private AccountDetail customer;

    @ManyToOne
    private Store storeBuy;

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
}
