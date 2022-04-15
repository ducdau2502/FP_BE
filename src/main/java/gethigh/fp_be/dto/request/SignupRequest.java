package gethigh.fp_be.dto.request;

import gethigh.fp_be.model.num.EGender;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
