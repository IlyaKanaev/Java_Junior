package task2;

import java.lang.reflect.Field;

public class Program {
    public static void main(String[] args) throws IllegalAccessException {
        Car car = new Car("Toyota", "Blue", 2022);
        task2(car);
    }

    private static <T> void task2 (T obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();

        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true); // Разрешаем доступ к закрытому полю
            System.out.printf("%s: %s\n", field.getName(), field.get(obj));
        }
    }
}
