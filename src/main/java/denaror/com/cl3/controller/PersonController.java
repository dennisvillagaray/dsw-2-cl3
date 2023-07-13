package denaror.com.cl3.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import denaror.com.cl3.entity.Person;
import denaror.com.cl3.repository.PersonRepository;
import denaror.com.cl3.repository.PersonRepositoryJPA;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
@Validated
@Tag(name = "Persons", description = "Person API")
public class PersonController {

  private PersonRepository personRepository;

  private PersonRepositoryJPA personRepositoryJPA;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Person register(@RequestBody @Valid Person person) {
    return this.personRepositoryJPA.save(person);
  }

  @GetMapping
  public Page<?> list(@ParameterObject Pageable pageable, @RequestParam(defaultValue = "false") Boolean isMobile) {

    if (isMobile) {
      return this.personRepository.findResumenBy(pageable);
    } else {
      return this.personRepository.findAll(pageable);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getProduct(@PathVariable Long id) {
    Optional<Person> person = personRepositoryJPA.findById(id);

    if (person.isPresent()) {
      return ResponseEntity.ok(person.get());
    } else {
      Map<String, String> response = new HashMap<>();
      response.put("message", "No se encontró la persona con ID " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
  }

  @GetMapping("/search-dni/{dni}")
  public ResponseEntity<Person> findByDni(@PathVariable String dni) {
    return ResponseEntity.of(this.personRepository.findByDni(dni));
  }

  @PutMapping("{id}")
  public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody @Validated Person person) {
    Optional<Person> existingPerson = personRepositoryJPA.findById(id);
    if (existingPerson.isPresent()) {
      person.setId(id);
      Person updatedPerson = personRepositoryJPA.save(person);
      return ResponseEntity.ok(updatedPerson);
    } else {
      Map<String, String> response = new HashMap<>();
      response.put("message", "No se encontró la persona con ID " + id + " para actualizar");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
    Optional<Person> existingPerson = personRepositoryJPA.findById(id);
    Map<String, String> response = new HashMap<>();
    if (existingPerson.isPresent()) {
      personRepositoryJPA.delete(existingPerson.get());
      response.put("message", "Se eliminó la persona con ID " + id + " correctamente");
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } else {
      response.put("message", "No se encontró la persona con ID " + id + " para eliminar");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
  }

}
