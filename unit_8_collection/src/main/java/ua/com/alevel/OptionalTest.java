package ua.com.alevel;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class OptionalTest {

    public void test() {
//        BigDecimal decimal = new Customer().getBalance().getBalance();
//        BigDecimal decimal = new Customer()?.getBalance()?.getBalance();
        Customer customer = new Customer();
        if (customer != null) {
            Balance balance = customer.getBalance();
            if (balance != null) {
                BigDecimal bigDecimal = balance.getBalance();
                if (bigDecimal != null) {
                    System.out.println("decimal = " + bigDecimal);
                }
            }
        }
//        BigDecimal bigDecimal = Optional.ofNullable(customer.getBalance()).map((balance) -> new BigDecimal("00.00")).get();


//        BigDecimal bigDecimal = Optional.ofNullable(customer.getBalance()).filter(Objects::nonNull).orElseGet(Balance::new).getBalance();

//        BigDecimal bal = new BigDecimal("100.00");
        BigDecimal bal = null;
        Balance balance = new Balance();
        balance.setBalance(bal);
        customer = new Customer();
        customer.setBalance(balance);

        BigDecimal bigDecimal = Optional
                .ofNullable(customer.getBalance())
                .or(() -> Optional.of(new Balance()))
                .map(Balance::getBalance)
//                .orElse(new BigDecimal("10.00"));
                .orElseGet(() -> {
                    BigDecimal b = new BigDecimal("10.00");
                    b = b.add(new BigDecimal("20.00"));
                    return b;
                });
//                .orElseThrow(() -> new RuntimeException("balance not found"));


        System.out.println("bigDecimal = " + bigDecimal);

    }
}
