package com.example.ProTaskifyAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
@Builder
public class Student implements UserDetails {
  @Id
  @Column(name = "student_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int student_id;

  @Column(name = "student_name", length = 50)
  private String student_name;

  @ManyToOne
  @JoinColumn(name = "class_id")
  private Class classID;

  @ManyToOne
  @JoinColumn(name = "group_id")
  private Group groupID;

  @Column(name = "score")
  private float score;

  @Column(name = "link_facebook", length = 100)
  private String link_facebook;

  @Column(name = "email", length = 50)
  private String email;

  @Column(name = "email", length = 50)
  private String password;

  @Column(name = "github", length = 50)
  private String github;

  @Column(name = "skills", length = 50)
  private String skills;

  @Column(name = "about", length = 100)
  private String about;

  @Column(name = "picture")
  @Lob
  private String picture;

  @Column(name = "status")
  private String status;

  @Column(name = "is_leader")
  private boolean is_leader;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
