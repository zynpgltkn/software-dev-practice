package swe.dev.timeoffers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import swe.dev.timeoffers.entity.User;
import swe.dev.timeoffers.repository.UserRepository;
import swe.dev.timeoffers.util.FileUploadUtil;

import javax.validation.Valid;
import java.io.IOException;

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


    @PostMapping("/uploadprofilepic/{id}")
    public String showUpdateProfilePic(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("singleUser", user);
        return "uploadprofilepic";
    }


    @RequestMapping(value = "/savepic/{id}", method = RequestMethod.POST)
    public String upload(@RequestParam("image") MultipartFile file, RedirectAttributes redirectAttributes, @PathVariable("id") long id, Model model) throws IOException {

        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "uploads/" + id;
        user.setProfilePicture(fileName,uploadDir);

        userRepository.save(user);
        FileUploadUtil.saveFile(uploadDir, fileName, file);

        model.addAttribute("singleUser", user);

        return "redirect:/welcomeprofile";
    }

    @PostMapping("/editdescription/{id}")
    public String editProfileDescription(@PathVariable("id") long id, Model model){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("singleUser", user);
        return "edituserdescription";
    }

    @RequestMapping("/submitnewdescription/{id}")
    public String saveNewDescription(@PathVariable("id") long id, @Valid User user,
                                     BindingResult result, Model model){
        User userfromdb = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userfromdb.setDescription(user.getDescription());
        userRepository.save(userfromdb);

    return "redirect:/welcomeprofile";
    }

}
