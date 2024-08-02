package com.github.fwaskito.user_registration.dto;

import org.hibernate.validator.constraints.Length;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Users")
public class UserDTO {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Length(max = 50)
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Length(max = 150)
    @Column(name = "address")
    private String address;

    @Email
    @NotEmpty
    @Length(max = 80)
    @Column(name = "email")
    private String email;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserDTO)) {
            return false;
        }
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", address='" + getAddress() + "'" +
                ", email='" + getEmail() + "'" +
                "}";
    }

}
