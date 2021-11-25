package ua.com.alevel;

import ua.com.alevel.crud.BaseEntity;

public class WithoutGenType {

    public static Object newInstance(Class aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E> E newInstanceParam(Class<E> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E extends BaseEntity> E newInstanceParamBaseEntity(Class<E> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <AAAAAAAAAAAAAAAAAAAA, BB extends B<AAAAAAAAAAAAAAAAAAAA>> Object newIns(Class<BB> aClass) {
        return null;
    }

    public static <E extends Number> Number getNumber(Class<? extends E> eClass) {
        return null;
    }

    public static Number getNumberSuper(Class<? super Number> eClass) {
        return null;
    }
}
