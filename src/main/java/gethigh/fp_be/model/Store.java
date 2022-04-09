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
    private AccountDetail storeOwer;

    @OneToMany
    private List<StoreCategories> categoriesList;

    @OneToMany
    private List<Product> productList;

    public Store() {
    }

    public Store(Long id,
                 String name,
                 String description,
                 String avatar,
                 AccountDetail storeOwer,
                 List<StoreCategories> categoriesList,
                 List<Product> productList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.storeOwer = storeOwer;
        this.categoriesList = categoriesList;
        this.productList = productList;
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

    public AccountDetail getStoreOwer() {
        return storeOwer;
    }

    public void setStoreOwer(AccountDetail storeOwer) {
        this.storeOwer = storeOwer;
    }

    public List<StoreCategories> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<StoreCategories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
