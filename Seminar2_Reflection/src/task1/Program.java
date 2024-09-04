package task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Program {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> personalClass = Class.forName("task1.Person");
        Constructor[] constructors = personalClass.getConstructors();
        Object personInstance = constructors[0].newInstance(null);

        Method displayInfoMethod = personalClass.getDeclaredMethod("displayInfo");
        displayInfoMethod.invoke(personInstance);

        Field nameField = personalClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(personInstance, "Alice");
        Field ageField = personalClass.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(personInstance, 30);
        displayInfoMethod.invoke(personInstance);
    }
}
