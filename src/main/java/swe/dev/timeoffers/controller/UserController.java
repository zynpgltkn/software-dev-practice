package swe.dev.timeoffers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import swe.dev.timeoffers.entity.User;
import swe.dev.timeoffers.repository.UserRepository;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String showUserList(Model model) {
        return "index";
    }

    @GetMapping("/listofusers")
    public String showUserLista(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "listofusers";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        User newUser = new User();
        model.addAttribute("newUser",newUser);
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@Valid User newUser, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        newUser.setDefaults();

        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);
        return "redirect:/welcomeprofile/"+newUser.getId();
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        User anonUser = new User();
        model.addAttribute("anonUser", anonUser);
        return "/login";
    }


    @PostMapping("/login")
    public String submitLoginForm(User anonUser, Model model){
        User usr = userRepository.findByEmail(anonUser.getEmail());

        if(usr == null) {
            model.addAttribute("error", "User not found");
            model.addAttribute("anonUser", new User());
            return "login";
        }
        String encodedDbPass = usr.getPassword();
        String anonPass = anonUser.getPassword();

        if(passwordEncoder.matches(anonPass, encodedDbPass)){
            return "redirect:/welcomeprofile/"+usr.getId();
        }

        model.addAttribute("error", "Something went wrong, try again");
        model.addAttribute("anonUser", new User());
        return "login";
    }


}
