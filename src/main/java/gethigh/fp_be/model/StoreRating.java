package gethigh.fp_be.model;

import javax.persistence.*;

@Entity
@Table(name = "store_ratings")
public class StoreRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer level;

    @ManyToOne
    private AccountDetail account;

    @ManyToOne
    private Store store;

    public StoreRating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public AccountDetail getAccount() {
        return account;
    }

    public void setAccount(AccountDetail account) {
        this.account = account;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
