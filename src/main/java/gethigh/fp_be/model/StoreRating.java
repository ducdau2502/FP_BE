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
    private AccountDetail accountRating;

    @ManyToOne
    private Store storeRating;
}
