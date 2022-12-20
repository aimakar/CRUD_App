package ru.makarov.springcourse.dao;


import org.springframework.stereotype.Component;
import ru.makarov.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Makarov on 20.12.2022
 */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 23, "tom@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 35, "bobd@ya.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 65, "mkinderq@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 35, "katya@yahoo.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }

}