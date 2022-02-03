package ua.com.alevel.final_project.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.final_project.facade.PLPFacade;

@Controller
@RequestMapping("/books")
public class OpenBookController {

    private final PLPFacade plpFacade;

    public OpenBookController(PLPFacade plpFacade) {
        this.plpFacade = plpFacade;
    }

    @GetMapping
    public String books(Model model, WebRequest webRequest) {
        model.addAttribute("bookList", plpFacade.search(webRequest));
        return "/pages/open/plp";
    }
}
