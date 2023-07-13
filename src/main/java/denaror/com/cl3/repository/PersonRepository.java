package denaror.com.cl3.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


import denaror.com.cl3.entity.Person;
import denaror.com.cl3.interfaces.PersonResumen;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
  Optional<Person> findByDni(String dni);
  Page<PersonResumen> findResumenBy(Pageable pageable);
}
