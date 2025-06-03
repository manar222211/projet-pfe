package com.example.notification_service.dto;

public class UserDTO {
    private  Long id;

    private  String email ;
    private  String password ;

    private  String username ;
    private  String firstname ;
    private  String lastname ;

    private  String phone ;
    private  String photo ;


    private  String address ;

    private  String role ;

    private boolean confirm=false;

    private int nbmax;
    private int nbtotal=0;
    private String skills;
    private Integer nbexperience ;

    private Grade grade;

    private  Double salary;
    private String typeofContract;
    private  String maritalStatus;
    private  String extraHours;

    private Department department;

    private  String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }


    public UserDTO() {
    }

    public UserDTO(Long id, String email, String password, String username, String firstname, String lastname, String phone, String photo, String address, String role, boolean confirm) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.photo = photo;
        this.address = address;
        this.role = role;
        this.confirm = confirm;
    }

    public int getNbmax() {
        return nbmax;
    }

    public void setNbmax(int nbmax) {
        this.nbmax = nbmax;
    }

    public int getNbtotal() {
        return nbtotal;
    }

    public void setNbtotal(int nbtotal) {
        this.nbtotal = nbtotal;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Integer getNbexperience() {
        return nbexperience;
    }

    public void setNbexperience(Integer nbexperience) {
        this.nbexperience = nbexperience;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getTypeofContract() {
        return typeofContract;
    }

    public void setTypeofContract(String typeofContract) {
        this.typeofContract = typeofContract;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(String extraHours) {
        this.extraHours = extraHours;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
