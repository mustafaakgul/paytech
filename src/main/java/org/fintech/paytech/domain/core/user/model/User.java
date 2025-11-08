package org.fintech.paytech.domain.core.user.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.fintech.paytech.domain.centralized.wallet.model.Wallet;
import org.fintech.paytech.model.BaseModel;

@Entity
@Table(name = "users")
public class User extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(length = 50, nullable = false, name = "first_name")
    private String firstName;

    @Column(length = 50, nullable = false, name = "last_name")
    private String lastName;

    @Column(length = 30, nullable = true, unique = true, name = "username")
    private String username;

    @NotNull
    @Column(length = 50, nullable = false, unique = true, name = "email")
    private String email;

    @NotNull
    @Column(length = 100, nullable = false, name = "password")
    private String password;

    @Column(length = 20, nullable = true, unique = true, name = "mobile_number")
    private String mobileNumber;

    @Column(name = "enabled")
    private Boolean enabled = true;

    @Column(name = "locked")
    private Boolean locked = false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Wallet wallet;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
