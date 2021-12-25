package swe.dev.timeoffers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateServiceOfferController {

    @GetMapping("/CreateServiceOffer")
    public String createOffer(){
        return "createoffer";
    }

}
