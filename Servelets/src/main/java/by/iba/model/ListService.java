package by.iba.model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class ListService {
    private static ListService instance;
    private final List<Person> groupList = Collections.synchronizedList(new ArrayList<>());

    private ListService() {
        // Initialize with default persons
        groupList.add(new Person("Anna", "+375291234567", "anna.1.18@gmail.com"));
        groupList.add(new Person("Ivan", "+375331114534", "ivan.1.18@gmail.com"));
        groupList.add(new Person("Nikolai", "+3752998734534", "nik.1.18@gmail.com"));
    }

    public static synchronized ListService getInstance() {
        if (instance == null) {
            instance = new ListService();
        }
        return instance;
    }

    public List<Person> retrieveList() {
        return new ArrayList<>(groupList); // Возвращаем копию списка
    }

    public void addPerson(Person person) {
        groupList.add(person);
    }
}