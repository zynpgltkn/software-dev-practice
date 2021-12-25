package swe.dev.timeoffers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import swe.dev.timeoffers.repository.UserRepository;

@Controller
public class UserProfileController {
    private UserRepository userRepository;

    @Autowired
    public UserProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/welcomeprofile")
    public String showUserProfile(Model model) {
        model.addAttribute("singleUser", userRepository.findByEmail("zey@nep.com"));
        return "welcomeprofile";
    }
}
