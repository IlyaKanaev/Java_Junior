import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        String str = "";
        Method[] methods = str.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
