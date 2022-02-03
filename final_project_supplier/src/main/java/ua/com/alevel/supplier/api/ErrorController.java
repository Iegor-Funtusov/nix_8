package ua.com.alevel.supplier.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/error")
public class ErrorController {

    @GetMapping("/401")
    public ResponseEntity<String> error401() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Пшел вон!!!");
    }
}
