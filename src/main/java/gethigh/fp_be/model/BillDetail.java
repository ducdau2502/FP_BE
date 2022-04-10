package gethigh.fp_be.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "bill_details")
public class BillDetail {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double discount;


    // cần xem lại
    @OneToMany
    private List<Product> product;

    @ManyToOne
    private Bill bill;

    public BillDetail() {
    }



}
