package com.ecommerce.agroproducts.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role", length = 30, nullable = false, unique = true)
    private String role;


    @Column(name = "description", length = 150)
    private String description;


    @Column(name = "dateCreated", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")

    private ZonedDateTime dateCreated;


    @Column(name = "dateModified", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE"
                    + " CURRENT_TIMESTAMP")
    private ZonedDateTime dateModified;

    @OneToMany(mappedBy = "roles", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<Users> users;


    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "rolePrivileges",
            joinColumns = @JoinColumn(
                    name = "roleId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilegeId", referencedColumnName = "id"))
    private Set<Privileges> privileges;}

