package swe.dev.timeoffers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import swe.dev.timeoffers.entity.Offer;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class OfferController {

    @GetMapping("/editOffer/{id}")
    public String showEditOffer(Model model) {

        return "/editOffer";
    }


    @GetMapping("/createoffer/{id}")
    public String showCreateOffer(Model model) {
        Offer newOffer = new Offer();



        newOffer.setStartDate(new Date());
        model.addAttribute("newOffer", newOffer);
        return "/createoffer";
    }
}
