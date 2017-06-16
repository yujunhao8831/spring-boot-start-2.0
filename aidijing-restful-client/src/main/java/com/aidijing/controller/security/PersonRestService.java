package com.aidijing.controller.security;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonRestService {
    private static final List< Person > persons;

    static {
        persons = new ArrayList<>();
        persons.add( new Person( "Hello", "World" ) );
        persons.add( new Person( "Foo", "Bar" ) );
    }

    @RequestMapping( path = "/persons", method = RequestMethod.GET )
    public static List< Person > getPersons () {
        return persons;
    }

    @RequestMapping( path = "/persons/{name}", method = RequestMethod.GET )
    public static Person getPerson ( @PathVariable( "name" ) String name ) {
        return persons.stream()
                      .filter( person -> name.equalsIgnoreCase( person.getName() ) )
                      .findAny().orElse( null );
    }

    static class Person {
        private String name;
        private String email;

        public Person () {
        }

        public Person ( String name, String email ) {
            this.name = name;
            this.email = email;
        }

        public String getName () {
            return name;
        }

        public void setName ( String name ) {
            this.name = name;
        }

        public String getEmail () {
            return email;
        }

        public void setEmail ( String email ) {
            this.email = email;
        }
    }
}


