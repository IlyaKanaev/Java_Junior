package org.example.task2;

/**
 * Задание 2: Используя JPA, создайте базу данных для хранения объектов класса
 * Person. Реализуйте методы для добавления, обновления и удаления объектов Person.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.task2.ListApp.*;

public class Main {
    public static void main(String[] args) {
        ListApp.createPersonsDataBase();
    }
}
