package gethigh.fp_be.model;

import javax.persistence.*;

@Entity
@Table(name = "store_categories")
public class StoreCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Store store;

    public StoreCategories() {
    }

    public StoreCategories(Long id, String name, Store store) {
        this.id = id;
        this.name = name;
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
