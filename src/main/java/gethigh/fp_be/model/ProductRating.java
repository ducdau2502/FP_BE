package gethigh.fp_be.model;

import javax.persistence.*;

@Entity
@Table(name = "productRatings")
public class ProductRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String level;

    @ManyToOne
    private AccountDetail accountRating;

    @ManyToOne
    private Product productRating;
}
