package gethigh.fp_be.model;


import gethigh.fp_be.model.num.EGender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "account_details")
public class AccountDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    private String address;
    private Integer identityCard;
    private String avatar;
    private LocalDate dateCreate;
    private Integer bankAccount;
    private String status;

    @OneToOne
    private Account account;


    public AccountDetail() {
    }

    public AccountDetail(
                         String fullName,
                         Integer age,
                         EGender gender,
                         String address,
                         Integer identityCard,
                         String avatar,
                         LocalDate dateCreate,
                         Integer bankAccount,
                         String status,
                         Account account) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.identityCard = identityCard;
        this.avatar = avatar;
        this.dateCreate = dateCreate;
        this.bankAccount = bankAccount;
        this.status = status;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(Integer identityCard) {
        this.identityCard = identityCard;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Integer bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
