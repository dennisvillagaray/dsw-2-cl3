package denaror.com.cl3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import denaror.com.cl3.entity.Person;

public interface PersonRepositoryJPA extends JpaRepository<Person, Long> {

}
