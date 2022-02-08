package ua.com.alevel.final_project.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("appLogger")
public class AppLogger {

    private static final Logger INFO = LoggerFactory.getLogger("info");
    private static final Logger WARN = LoggerFactory.getLogger("warn");
    private static final Logger ERROR = LoggerFactory.getLogger("error");

    public void log(String msg, AppLoggerLevel level) {
        switch (level) {
            case INFO -> INFO.info(msg);
            case WARN -> WARN.warn(msg);
            case ERROR -> ERROR.error(msg);
        }
    }
}
