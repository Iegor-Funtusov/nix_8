package ua.com.alevel.interfaceclass;

public sealed abstract interface InterfaceClass permits ExtendsInterfaceClass {

    public static final int AGE = 10;

    public abstract void test();

    default void test2() {}
}
