package ua.com.alevel;

public class GenericList<E> {

    private E[] objects;

    public GenericList() {
        objects = (E[]) new Object[10];
    }

    public void add(E o) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null) {
                objects[i] = o;
                break;
            }
        }
    }

    public E get(int index) {
        for (int i = 0; i < objects.length; i++) {
            if (i == index) {
                return objects[i];
            }
        }
        return null;
    }
}
