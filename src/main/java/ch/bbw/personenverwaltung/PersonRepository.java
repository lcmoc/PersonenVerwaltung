package ch.bbw.personenverwaltung;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

    public interface PersonRepository extends CrudRepository<Person, Long> {
        // List<Person> findByEmailAddressAndLastname(String email, String lastname);

        // // Enables the distinct flag for the query
        // List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
        // List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);

        // // Enabling ignoring case for an individual property
        // List<Person> findByLastnameIgnoreCase(String lastname);
        // // Enabling ignoring case for all suitable properties
        // List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);

        // // Enabling static ORDER BY for a query
        // List<Person> findByLastnameOrderByFirstnameAsc(String lastname);
        // List<Person> findByLastnameOrderByFirstnameDesc(String lastname);

    }
