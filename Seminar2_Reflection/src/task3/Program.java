package task3;

import java.util.UUID;

public class Program {
    public static void main(String[] args) throws IllegalAccessException {
        Employee user = new Employee("Stanislav", "sample@gmail.com");
        UUID pk = UUID.randomUUID();
        user.setId(pk);

        QueryBuilder queryBuilder = new QueryBuilder();
        String insertQuery = queryBuilder.buildInsertQuery(user);
        System.out.printf("Insert Query: %s\n", insertQuery);

        String selectQuery = queryBuilder.buildSelectQuery(Employee.class, pk);
        System.out.printf("Select Query: %s\n", selectQuery);

        String updateQuery = queryBuilder.buildUpdateQuery(user);
        System.out.printf("Update Query: %s\n", updateQuery);
    }
}
