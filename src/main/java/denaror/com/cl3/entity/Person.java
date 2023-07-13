package denaror.com.cl3.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotBlank
  String name;

  @NotBlank
  String surName;

  @NotBlank
  @Pattern(regexp = "\\d{8}", message = "DNI must be 8 digits")
  String dni;

  @NotBlank
  String address;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Past(message = "Birth date must be in the past")
  LocalDate birthDate;

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$", message = "Email must be valid")
  String email;
}
