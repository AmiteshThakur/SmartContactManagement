package com.smart.Entities;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="User")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String name;
@Column(unique = true)
private String email;
private String password;
private String role;
private boolean enabled;
private String imageUrl;
@Column(length= 500)
private String about;

/* `@OneToMany(cascade = CascadeType.ALL)` in Spring Boot specifies a one-to-many relationship between two entities, where changes to the parent entity (like save, update, delete) are automatically applied to the related child entities. This ensures the parent and child entities stay synchronized in the database. */
//This is for mapping with another entity.
@OneToMany(cascade = CascadeType.ALL)
private List<Contact> contacts= new ArrayList<>();

}
