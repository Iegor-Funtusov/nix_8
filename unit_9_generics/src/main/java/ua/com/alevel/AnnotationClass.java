package ua.com.alevel;

import java.io.Serializable;

@CustomAnnotation
public class AnnotationClass implements Serializable {

    @Value("Bla Bla")
    private String something;

    @InitMethod
    private void hello() {
        System.out.println("Hello World!!!");
        System.out.println("something = " + something);
    }

    private void test() { }
}
