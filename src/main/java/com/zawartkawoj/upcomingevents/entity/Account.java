package com.zawartkawoj.upcomingevents.entity;

import com.zawartkawoj.upcomingevents.validation.RegistrationValidationGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "accounts")
public class Account {

    @Id
    @NotNull
    @NotEmpty(
            message = "Please enter an email address.",
            groups = {RegistrationValidationGroup.class})
    private String email;
    @NotNull
    @NotEmpty(
            message = "Please enter your first name.",
            groups = {RegistrationValidationGroup.class})
    @Size(
            max = 20,
            message = "First name can have a maximum of 20 characters.",
            groups = {RegistrationValidationGroup.class})
    private String firstName;
    @NotNull
    @NotEmpty(
            message = "Please enter your last name.",
            groups = {RegistrationValidationGroup.class})
    @Size(
            max = 20,
            message = "Last name can have a maximum of 20 characters.",
            groups = {RegistrationValidationGroup.class})
    private String lastName;
    @NotNull
    @NotEmpty(
            message = "Please enter a password.",
            groups = {RegistrationValidationGroup.class})
    @Size(
            min = 8,
            message = "Password must be at least 8 characters long.",
            groups = {RegistrationValidationGroup.class})
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    public Account() {
    }

    public Account(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
