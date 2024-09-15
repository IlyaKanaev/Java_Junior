package org.example.task1;

import java.io.*;

public class Person implements Serializable {
    private final String name;
    private final int age;
    public final transient String FILE = "persondata.bin";


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void serialization(Person person, String file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(person);
            System.out.println("Объект Person сериализован.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Person deserialization(String file) {
        Person person;
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            person = (Person) objectInputStream.readObject();
            System.out.println("Объект Person десериализован.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return person;
    }
}
