package gethigh.fp_be.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String avatar;

    @OneToOne
    private AccountDetail storeOwner;

    @ManyToOne
    private StoreCategories categoriesList;

    public Store() {
    }

    public Store(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Store(Long id, String name, String description, String avatar, AccountDetail storeOwner, StoreCategories categoriesList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.storeOwner = storeOwner;
        this.categoriesList = categoriesList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public AccountDetail getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(AccountDetail storeOwner) {
        this.storeOwner = storeOwner;
    }

    public StoreCategories getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(StoreCategories categoriesList) {
        this.categoriesList = categoriesList;
    }
}
