package gethigh.fp_be.model;


import javax.persistence.*;

@Entity
@Table(name = "store_likes")
public class StoreLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AccountDetail accountLike;

    @ManyToOne
    private Store store;

    public StoreLike(AccountDetail accountLike, Store store) {
        this.accountLike = accountLike;
        this.store = store;
    }

    public StoreLike() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountDetail getAccountLike() {
        return accountLike;
    }

    public void setAccountLike(AccountDetail accountLike) {
        this.accountLike = accountLike;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
