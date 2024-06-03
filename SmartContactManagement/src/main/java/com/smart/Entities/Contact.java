package com.smart.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int cId;

  private String name;
  private String secondName;
  private String work;
  private String email;
  private String phone;
  private String image;

  @Column(length = 50000)
  private String description;

  // This User object is for mapping and this is unidirectional
  @ManyToOne
  private User user;
}
