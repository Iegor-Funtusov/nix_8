package ua.com.alevel.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

//    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseContainer<Boolean>> create(@RequestBody User user) {
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseContainer<>(true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseContainer<Boolean>> update(@RequestBody User user, @PathVariable Integer id) {
        user.setId(id);
        userService.update(user);
        return ResponseEntity.ok(new ResponseContainer<>(true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseContainer<Boolean>> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok(new ResponseContainer<>(true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContainer<User>> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(new ResponseContainer<>(userService.findById(id)));
    }

    @GetMapping("/email")
    public ResponseEntity<ResponseContainer<User>> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(new ResponseContainer<>(userService.findByEmail(email)));
    }

    @GetMapping
    public ResponseEntity<ResponseContainer<DataTableResponse<User>>> findAll(WebRequest webRequest) {
        return ResponseEntity.ok(new ResponseContainer<>(userService.findAll(webRequest)));
    }
}
