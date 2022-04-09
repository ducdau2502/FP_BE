package gethigh.fp_be.model;


import gethigh.fp_be.model.num.EAccountRole;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private EAccountRole name;

    public AccountRole() {
    }

    public AccountRole(Long id,
                       EAccountRole name) {
        this.id = id;
        this.name = name;
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
