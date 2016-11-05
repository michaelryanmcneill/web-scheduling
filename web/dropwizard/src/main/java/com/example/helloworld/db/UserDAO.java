package com.example.helloworld.db;

import com.example.helloworld.core.User2;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<User2> {
 

    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<User2> findById(Long id) {
        return Optional.ofNullable(get(id));
    }	

    public User2 create(User2 la) {
        return persist(la);
    }

    public User2 saveOrUpdate(User2 person) {
    return persist(person);
     }
    public void delete(User2 person) {
		if (person != null) {
			currentSession().delete(person);
		}
	}

    public List<User2> findAll() {
        return list(namedQuery("com.example.helloworld.core.User2.findAll"));
    }

    public Optional<User2> findRole(String name, String password) {
        return Optional.ofNullable(
            uniqueResult(
            namedQuery("com.example.helloworld.core.User2.nameandpassword")
            .setParameter("name", name)
            .setParameter("password", password)
               ));
    }

}
