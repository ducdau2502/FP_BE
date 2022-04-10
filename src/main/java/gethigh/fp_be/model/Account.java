package gethigh.fp_be.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_roles",joinColumns = @JoinColumn(name = "account_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<AccountRole> roles = new HashSet<>();

    public Account() {
    }


    public Account(Long id,
                   String username,
                   String password,
                   String email,
                   Set<AccountRole> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }



    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AccountRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AccountRole> roles) {
        this.roles = roles;
    }
}
