package swe.dev.timeoffers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import swe.dev.timeoffers.entity.Offer;
import swe.dev.timeoffers.entity.User;
import swe.dev.timeoffers.repository.OfferRepository;
import swe.dev.timeoffers.repository.UserRepository;
import swe.dev.timeoffers.util.FileUploadUtil;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

@Controller
public class OfferController {

    private OfferRepository offerRepository;
    private UserRepository userRepository;

    @Autowired
    public OfferController(UserRepository userRepository, OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/seeoffer/{id}")
    public String showOffer(@PathVariable("id") long offerId,Model model) {
        Offer singleOffer = offerRepository.findById(offerId);
        model.addAttribute("singleOffer", singleOffer);

        return "/seeoffer";
    }

    @GetMapping("/editoffer/{id}")
    public String showEditOffer(@PathVariable("id") long offerId,Model model) {
        Offer singleOffer = offerRepository.findById(offerId);
        model.addAttribute("singleOffer", singleOffer);

        return "editoffer";
    }


    @PostMapping("/editoffer/{id}")
    public String updateOffer(@PathVariable("id") long offerId, Offer singleOffer, Model model) {
        Offer off = offerRepository.findById(offerId);
        off.setOfferParams(singleOffer);
        offerRepository.save(off);

        return "redirect:/welcomeprofile/"+singleOffer.getCreatorUser().getId();
    }

    @GetMapping("/createoffer/{id}")
    public String createOffer(@PathVariable("id") long id,Model model){
            Offer newOffer = new Offer();
            model.addAttribute("newOffer", newOffer);
            model.addAttribute("userId", id);
            return "createoffer";
    }


    @PostMapping("/createoffer/{id}")
    public String createNewOffer(@PathVariable("id") long id,@Valid Offer newOffer, BindingResult result, Model model, @RequestParam("image") MultipartFile file) throws  IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        Offer offer = new Offer(user);
        offer.setOfferParams(newOffer);
        offerRepository.save(offer);

        Offer offerfromdb = offerRepository.findById(offer.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid offer Id"));;

        if(file != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uploadDir = "offerpics/" + newOffer.getId();
            offerfromdb.setOfferPicture(fileName, uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, file);
        }
        offerRepository.save(offerfromdb);
       model.addAttribute("message", "You created a new Offer / Event!");

        return "redirect:/welcomeprofile/"+id;
    }
}
