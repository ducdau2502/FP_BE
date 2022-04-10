package gethigh.fp_be.model;

import javax.persistence.*;

@Entity
@Table(name = "product_feedback")
public class ProductFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    private AccountDetail accountFeedback;

    @ManyToOne
    private Product productFeedback;

    public ProductFeedback() {
    }

    public ProductFeedback(Long id,
                           String content,
                           AccountDetail accountFeedback,
                           Product productFeedback) {
        this.id = id;
        this.content = content;
        this.accountFeedback = accountFeedback;
        this.productFeedback = productFeedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AccountDetail getAccountFeedback() {
        return accountFeedback;
    }

    public void setAccountFeedback(AccountDetail accountFeedback) {
        this.accountFeedback = accountFeedback;
    }

    public Product getProductFeedback() {
        return productFeedback;
    }

    public void setProductFeedback(Product productFeedback) {
        this.productFeedback = productFeedback;
    }
}
