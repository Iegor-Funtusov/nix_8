package ua.com.alevel;

public class AnonimClass {

    public void test() {
        AnonimInterfaceImpl anonimInterfaceImpl = new AnonimInterfaceImpl();
        AnonimInterface anonimInterface = new AnonimInterfaceImpl();

        anonimInterface = new AnonimInterface() {
            @Override
            public void test1() {

            }

            @Override
            public void test2() {

            }
        };

        Anonim anonim = new Anonim() {
            @Override
            public int sum(int a, int b) {
                return a + b;
            }
        };

        anonim = (a, b) -> a + b;

        Anonim1 anonim1 = new Anonim1() {
            @Override
            public Anonim1 get() {
                return null;
            }
        };

        Anonim2 anonim2 = new Anonim2() {
            @Override
            public void test(Anonim1 anonim1) {

            }
        };

        anonim2.test(anonim1.get());

        anonim1 = () -> null;

        anonim2 = (a) -> { };

        anonim2.test(() -> null);

    }
}
