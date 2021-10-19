package ua.com.alevel;

import java.lang.reflect.Field;

public class SQLQeury {

    public String generateStringSql(Class<?> aClass) {
        String query =  "insert * from " + aClass.getName() + "s" + " where ";
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            query += field.getName() + " test1 ";
        }
        query += ";";

        return query;
    }

    public String generateStringBuilderSql(Class<?> aClass) {
        StringBuilder query = new StringBuilder("insert * from " + aClass.getName() + "s" + " where ");
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            query.append(field.getName() + " test1 ");
        }
        query.append(";");

        return query.toString();
    }
}
