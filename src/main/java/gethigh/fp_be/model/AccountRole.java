package gethigh.fp_be.model;


import gethigh.fp_be.model.num.EAccountRole;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private EAccountRole name;

    public AccountRole() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EAccountRole getName() {
        return name;
    }

    public void setName(EAccountRole name) {
        this.name = name;
    }
}
