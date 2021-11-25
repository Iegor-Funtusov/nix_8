package ua.com.alevel;

import org.reflections.Reflections;
import org.reflections.Store;
import ua.com.alevel.crud.Department;
import ua.com.alevel.crud.Employee;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class GenericMain {

    public static void main(String[] args) {
        System.out.println("GenericMain.main");

        Store store = new Reflections("ua.com.alevel").getStore();
        store.forEach((k, v) -> {
            if (k.equals("TypesAnnotated")) {
                v.forEach((a, b) -> {
                    if (a.equals(CustomAnnotation.class.getName())) {
                        Set<Class<?>> classes = new HashSet<>();
                        for (String s : b) {
                            try {
                                classes.add(Class.forName(s));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        for (Class<?> aClass : classes) {
                            try {
                                Object newAnnotationClass = aClass.getDeclaredConstructor().newInstance();
                                Field[] fields = newAnnotationClass.getClass().getDeclaredFields();
                                for (Field field : fields) {
                                    if (field.isAnnotationPresent(Value.class)) {
                                        Value value = field.getAnnotation(Value.class);
                                        String val = value.value();
                                        field.setAccessible(true);
                                        field.set(newAnnotationClass, val);
                                    }
                                }
                                Method[] methods = newAnnotationClass.getClass().getDeclaredMethods();
                                for (Method method : methods) {
                                    if (method.isAnnotationPresent(InitMethod.class)) {
                                        method.setAccessible(true);
                                        method.invoke(newAnnotationClass);
                                    }
                                }
                            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });

//        GenType<Number, Number> genType;
//
//        Department department = (Department) WithoutGenType.newInstance(Department.class);
//        System.out.println("department = " + department);
//
//        String s = WithoutGenType.newInstanceParam(String.class);
//        System.out.println("s = " + s);
//
//        department = WithoutGenType.newInstanceParam(Department.class);
//        System.out.println("department = " + department);
//
//        department = WithoutGenType.newInstanceParamBaseEntity(Department.class);
//        System.out.println("department = " + department);
//
//        Employee employee = WithoutGenType.newInstanceParamBaseEntity(Employee.class);
//
//        WithoutGenType.getNumber(Integer.class);
//        WithoutGenType.getNumberSuper(Number.class);
//
//        CustomList customList = new CustomList();
//        customList.add(1);
//        customList.add(2);
//        customList.add(3);
//        customList.add(4);
//
//        Object object = customList.get(1);
//        if (object.toString().matches("\\d")) {
//            int i = Integer.parseInt(object.toString());
//            System.out.println("i = " + i);
//        }
//
//        GenericList<Integer> list = new GenericList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//
//
//        Integer integer = list.get(3);
//        System.out.println("integer = " + integer);
    }
}
