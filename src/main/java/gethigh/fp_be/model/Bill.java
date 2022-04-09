package gethigh.fp_be.model;


import javax.persistence.*;

@Entity
@Table(name = "buills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private AccountDetail customer;

    @ManyToOne
    private Store storeBuy;

    public Bill() {
    }

    public Bill(Long id,
                AccountDetail customer,
                Store storeBuy) {
        this.id = id;
        this.customer = customer;
        this.storeBuy = storeBuy;
    }

    public Long getId() {
        return id;
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
