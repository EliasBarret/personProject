package br.com.elias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.elias.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
