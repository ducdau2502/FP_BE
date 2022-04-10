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
}
