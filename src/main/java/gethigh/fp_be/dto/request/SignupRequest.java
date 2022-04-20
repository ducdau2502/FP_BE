package gethigh.fp_be.dto.request;

import java.time.LocalDate;
import java.util.Set;

public class SignupRequest {

  private String username;


  private String email;

  private Set<String> role;


  private String password;

  private String fullName;
  private Integer age;

  private String gender;
  private String address;
  private String identityCard;
  private String avatar;
  private LocalDate dateCreate;
  private String bankAccount;
  private String statusName;

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

  public Set<String> getRole() {
    return role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getIdentityCard() {
    return identityCard;
  }

  public void setIdentityCard(String identityCard) {
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

  public String getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(String bankAccount) {
    this.bankAccount = bankAccount;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }
}
