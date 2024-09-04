package task3;

import java.lang.reflect.Field;
import java.util.UUID;

public class QueryBuilder {
    public String buildInsertQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        StringBuilder query = new StringBuilder("INSERT INTO ");
        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query
                    .append(tableAnnotation.name())
                    .append(" (");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    query.append(columnAnnotation.name()).append(", ");
                }
            }
            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }
            query.append(") VALUES (");
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    query.append("'").append(field.get(obj)).append("', ");
                }
            }
            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }
            query.append(")");
            return query.toString();
        } else return null;
    }

    public String buildSelectQuery(Class<?> clazz, UUID primaruKey) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name()).append(" WHERE ");
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // нам нужно поле с определенной аннотацией
            if (field.isAnnotationPresent(Column.class)) {
                // получаем доступ к этой анногтации через переменную
                Column columnAnnotation = field.getAnnotation(Column.class);
                // проверяем наличие у этой аннотации свойства primaryKey == true
                if (columnAnnotation.primaryKey()) {
                    query.append(columnAnnotation.name()).append(" = ").append(primaruKey);
                    break;
                }
            }
        }
        return query.toString();
    }

    public String buildUpdateQuery(Object obj) throws IllegalAccessException {
        // в этот раз нам снова понадобится объект
        Class<?> clazz = obj.getClass();
        StringBuilder query = new StringBuilder("UPDATE ");
        // Также начинаем с анализа аннотации @Table
        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name()).append(" SET ");
            // Пройдем по всем полям, обновим их, кроме поля с primaryKey
            // по которому будем делать условие
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true); // доступ к полю
                    // Получаем описание нашей аннотации
                    Column colunmAnnotation = field.getAnnotation(Column.class);
                    // если попадается primaryKey - пропускаем текущую итерацию
                    if (colunmAnnotation.primaryKey())
                        continue;
                    // вставляем имя поля = 'новое значение', - не забываем про обработку исключения
                    query.append(colunmAnnotation.name()).append(" = '").append(field.get(obj)).append("',");

                }
            }
            // удаляем последнюю запятую и пробел
            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }
            // добавляем WHERE
            query.append(" WHERE ");
            // добавляем значение primaryKey
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true); // доступ к полю
                    // Получаем описание нашей аннотации
                    Column colunmAnnotation = field.getAnnotation(Column.class);
                    // теперь, когда попадается primaryKey - добавляем его к query
                    if (colunmAnnotation.primaryKey())
                        // вставляем имя поля = 'новое значение', - не забываем про обработку исключения
                        query.append(colunmAnnotation.name()).append(" = ").append(field.get(obj)).append("'");
                    break;
                }
            }
            return query.toString();

        } else {
            return null;
        }
    }
    /**
     * TODO: Доработать в рамках домашней работы
     * @param clazz
     * @param primaryKey
     * @return
     */
    public String buildDeleteQuery(Class<?> clazz, UUID primaryKey) {
        return null;
    }
}
