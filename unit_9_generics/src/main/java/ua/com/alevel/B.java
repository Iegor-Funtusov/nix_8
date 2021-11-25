package ua.com.alevel;

public class B<T> extends A<T> {

    private T subFruit;

    public T getSubFruit() {
        return subFruit;
    }

    public void setSubFruit(T subFruit) {
        this.subFruit = subFruit;
    }
}
