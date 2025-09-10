package com.example.nodepos.model;

public class userModel {
    private String data;
    private String uuid;
    private String nik;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String password;
    private String position;
    private String role;
    private String isActive;



    public userModel
            (
                String data,
                String uuid,
                String nik,
                String firstName,
                String lastName,
                String fullName,
                String email,
                String password,
                String position,
                String role,
                String isActive
            ){


                this.data = data;
                this.uuid = uuid;
                this.nik = nik;
                this.firstName = firstName;
                this.lastName = lastName;
                this.fullName = fullName;
                this.email = email;
                this.password = password;
                this.position = position;
                this.role = role;
                this.isActive = isActive;
             }

    public userModel() {

    }

    public String getData() { return data; }
    public String getUuid() { return uuid; }
    public String getNik() { return nik; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPosition() { return position; }
    public String getRole() { return role; }
    public String getIsActive() { return isActive; }



    public void setData(String data) {
        this.data = data;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
