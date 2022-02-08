package ua.com.alevel.final_project.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ua.com.alevel.final_project.persistence.entity.books.Book;
import ua.com.alevel.final_project.util.WebUtil;

import java.util.List;
import java.util.Map;

@Aspect
@Component
public class PublisherSearchStatisticAspect {

    @Pointcut("execution(* ua.com.alevel.final_project.service.impl.PLPServiceImpl.search(..))")
    public void pointcut() { }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("PublisherSearchStatisticAspect.doAspect");
        for (Object arg : pjp.getArgs()) {
            System.out.println("arg = " + arg);
        }
        Map<String, Object> queryMap = (Map<String, Object>) pjp.getArgs()[0];
        Object o = queryMap.get(WebUtil.PUBLISHER_PARAM);
//        queryMap.put(WebUtil.PUBLISHER_PARAM, 10); ERRRRRRRROOOOORRRRRRRRRR!!!!
        System.out.println("o = " + o);
        Object proceed = pjp.proceed();
        return proceed;
//        System.out.println("proceed = " + proceed);
//        List<Book> books = (List<Book>) proceed;
//        for (Book book : books) {
//            System.out.println("book = " + book);
//        }
//        return books;
    }
}
