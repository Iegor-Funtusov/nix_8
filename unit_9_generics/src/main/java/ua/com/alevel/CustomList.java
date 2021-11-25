package ua.com.alevel;

public class CustomList {

    private Object[] objects;

    public CustomList() {
        objects = new Object[10];
    }

    public void add(Object o) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null) {
                objects[i] = o;
                break;
            }
        }
    }

    public Object get(int index) {
        for (int i = 0; i < objects.length; i++) {
            if (i == index) {
                return objects[i];
            }
        }
        return null;
    }
}
