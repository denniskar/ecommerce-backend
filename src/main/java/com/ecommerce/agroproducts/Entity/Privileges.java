package com.ecommerce.agroproducts.Entity;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "privileges")
public class Privileges implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "privilege", length = 100, nullable = false, unique = true)
    private String privilege;

    @Column(name = "description", length = 150)
    private String description;

    @Column(name = "dateCreated", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime dateCreated;

    @ManyToMany(mappedBy = "privileges", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<Roles> roles;}