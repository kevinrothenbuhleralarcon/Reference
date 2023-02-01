package ch.kra.validation;

import ch.kra.validation.model.User;
import ch.kra.validation.validation.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class ValidationController {

    private UserValidator userValidator;

    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(@Valid User user, BindingResult result) {
        userValidator.validate(user, result);
//        if (Strings.isNotBlank(user.getFirstName()) && Strings.isNotBlank(user.getLastName()) && user.getFirstName().equals(user.getLastName())) {
//            result.rejectValue("lastName", "crossBinding", "Last name cannot be the same as first name.");
//        }
        if (result.hasErrors()) {
            return "sign-up";
        }
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String getResult() {
        return "result";
    }
}
