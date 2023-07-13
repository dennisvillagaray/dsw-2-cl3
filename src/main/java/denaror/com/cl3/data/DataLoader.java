package denaror.com.cl3.data;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import denaror.com.cl3.entity.Person;
import denaror.com.cl3.repository.PersonRepositoryJPA;
import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
  private static final int MAX_AGE = 80;

  @Autowired
  private PersonRepositoryJPA personRepository;

  @PostConstruct
  private void loadData() {
    removeData();
    loadPerson();
  }

  private void loadPerson() {
    Random random = new Random();
    LocalDate currentDate = LocalDate.now();

    Person person1 = new Person();
    person1.setName("Dennis");
    person1.setSurName("Villagaray");
    person1.setDni(generateRandomDni(random));
    person1.setAddress("Av. Los Alamos 123");
    person1.setBirthDate(generateRandomDate(currentDate, random));
    person1.setEmail("dennis.villagarayg@gmail.com");

    Person person2 = new Person();
    person2.setName("Juan");
    person2.setSurName("Perez");
    person2.setDni(generateRandomDni(random));
    person2.setAddress("Av. Los Alamos 123");
    person2.setBirthDate(generateRandomDate(currentDate, random));
    person2.setEmail("juan.perez@gmail.com");

    Person person3 = new Person();
    person3.setName("Maria");
    person3.setSurName("Lopez");
    person3.setDni(generateRandomDni(random));
    person3.setAddress("Av. Los Alamos 123");
    person3.setBirthDate(generateRandomDate(currentDate, random));
    person3.setEmail("maria.lopez@gmail.com");

    System.out.println("Loading persons...");
    personRepository.saveAll(Arrays.asList(person1, person2, person3));
  }

  private LocalDate generateRandomDate(LocalDate currentDate, Random random) {
    int randomAge = random.nextInt(MAX_AGE + 1);
    return currentDate.minusYears(randomAge);
  }

  private String generateRandomDni(Random random) {
    int dniLength = 8;
    StringBuilder sb = new StringBuilder(dniLength);

    for (int i = 0; i < dniLength; i++) {
      int digit = random.nextInt(10);
      sb.append(digit);
    }

    return sb.toString();
  }

  private void removeData() {
    personRepository.deleteAll();
  }
}
