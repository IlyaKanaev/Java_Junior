package org.example.task1;


/**
 * Задание 1: Создайте класс Person с полями name и age. Реализуйте
 * сериализацию и десериализацию этого класса в файл.
 */

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Nick", 23);
        System.out.println(person);
        Person.serialization(person, person.FILE);
        person = Person.deserialization(person.FILE);
        System.out.println(person);
   }
}