package com.example.helloworld.auth;
import com.example.helloworld.core.User;
import com.example.helloworld.core.User2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import com.example.helloworld.db.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;
public class ExampleAuthenticator implements Authenticator<BasicCredentials, User> {
 
     private final UserDAO userDAO;
     private final SessionFactory sessionFactory;

     public ExampleAuthenticator(final UserDAO userDAO,
            final SessionFactory sessionFactory) {
        this.userDAO = userDAO;
        this.sessionFactory = sessionFactory;
    }
    /**
     * Valid users with mapping user -> roles
     */
    private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of(
        "guest", ImmutableSet.of(),
        "la", ImmutableSet.of("BASIC_GUY"),
        "admin", ImmutableSet.of("ADMIN", "BASIC_GUY")
    );
   @UnitOfWork
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        Session session = sessionFactory.openSession();
        Optional<User2> result;
            
            try {
            ManagedSessionContext.bind(session);
            result = userDAO.findRole(credentials.getUsername(), credentials.getPassword());
            // String role = result.get().getRole();
            if(!result.isPresent()){
                return Optional.empty();
            }else if(VALID_USERS.containsKey(result.get().getRole()) && result.get().getPassword().equals(credentials.getPassword())) {
                return Optional.of(new User(result.get().getRole(), VALID_USERS.get(result.get().getRole())));
            }else{
              return Optional.empty();
            }
    //    if (VALID_USERS.containsKey(role) && "secret".equals(credentials.getPassword())) {
    //        return Optional.of(new User(role, VALID_USERS.get(role)));
    //    }else{

    //    }
     
        } catch (Exception e) {
            throw new AuthenticationException(e);
        } finally {
            ManagedSessionContext.unbind(sessionFactory);
            session.close();
        }

    
       
        
      
    
    }
}
