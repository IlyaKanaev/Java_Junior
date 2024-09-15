package org.example.task1;

public class Main {
    public static void main(String[] args) {

        Person person = new Person("Nick", 23);
        person.serialize("data.txt");
        System.out.println(Person.deserialize("data.txt"));
    }
}