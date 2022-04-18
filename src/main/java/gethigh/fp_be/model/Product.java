package gethigh.fp_be.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer inventoryQuantity;
    private Integer soldQuantity;
    private String description;

    private String coverImage;

    @ManyToOne
    private Store store;

    public Product() {
    }

    public Product(Long id, String name, Double price, Integer inventoryQuantity, Integer soldQuantity, String description, Store store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventoryQuantity = inventoryQuantity;
        this.soldQuantity = soldQuantity;
        this.description = description;
        this.store = store;
    }

    public Product(String name, Double price, Integer inventoryQuantity, Integer soldQuantity, String description, Store store) {
        this.name = name;
        this.price = price;
        this.inventoryQuantity = inventoryQuantity;
        this.soldQuantity = soldQuantity;
        this.description = description;
        this.store = store;
    }

    public Product(String name, Double price, Integer inventoryQuantity, String description) {
        this.name = name;
        this.price = price;
        this.inventoryQuantity = inventoryQuantity;
        this.description = description;
    }

    public Product(Long id, String name, Double price, Integer inventoryQuantity, Integer soldQuantity, String description, String coverImage, Store store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventoryQuantity = inventoryQuantity;
        this.soldQuantity = soldQuantity;
        this.description = description;
        this.coverImage = coverImage;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(Integer inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
