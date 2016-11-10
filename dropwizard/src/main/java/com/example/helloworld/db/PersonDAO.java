package com.example.helloworld.db;

import com.example.helloworld.core.Person;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PersonDAO extends AbstractDAO<Person> {
 

    public PersonDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(get(id));
    }	

    public Person create(Person person) {
        return persist(person);
    }
    
  /* public  List<Person>  update(Long id, Person person) {
      return  list(namedQuery("com.example.helloworld.core.Person.update").setParameter("id",id)
                                                                          .setParameter("start",person.getStart())
                                                                          .setParameter("end",person.getEnd()));
    }*/
 
    public Person saveOrUpdate(Person person) {
    return persist(person);
}
    public void delete(Person person) {
		if (person != null) {
			currentSession().delete(person);
		}
	}

    public List<Person> findAll() {
        return list(namedQuery("com.example.helloworld.core.Person.findAll"));
    }


}
