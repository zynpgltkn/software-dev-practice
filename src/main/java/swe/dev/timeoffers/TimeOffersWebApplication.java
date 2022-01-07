package swe.dev.timeoffers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import swe.dev.timeoffers.entity.Offer;
import swe.dev.timeoffers.entity.User;
import swe.dev.timeoffers.exception.UserAlreadyExistException;
import swe.dev.timeoffers.repository.OfferRepository;
import swe.dev.timeoffers.repository.UserRepository;
import swe.dev.timeoffers.service.UserService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TimeOffersWebApplication {

    private static UserRepository userRepository;
    private static OfferRepository offerRepository;

    @Autowired
    public TimeOffersWebApplication(UserRepository userRepository,OfferRepository offerRepository) {

        TimeOffersWebApplication.userRepository = userRepository;
        TimeOffersWebApplication.offerRepository = offerRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(TimeOffersWebApplication.class, args);

        return;/*
        System.exit(0);

        User moss = new User("Maurice", "Moss", "maurice@gmail.com", "MauriceMock");
        userRepository.save(moss);
        userRepository.save(new User("Santino", "Briggs", "santino@gmail.com", "SantinoMock"));
        userRepository.save(new User("Kiersten", "Rollins", "kiersten@gmail.com", "KierstenMock"));
        userRepository.save(new User("Alan", "Jensen", "alan@gmail.com", "AlanMock"));
        userRepository.save(new User("Chaim", "Franklin", "chaim@gmail.com", "ChaimMock"));
        userRepository.save(new User("Cole", "Livingston", "cola@gmail.com", "ColeMock"));
        userRepository.save(new User("Teagan", "Henry", "teagan@gmail.com", "TeaganMock"));
        userRepository.save(new User("Nathaly", "Payne", "nathaly@gmail.com", "NathalyMock"));
        userRepository.save(new User("Kameron", "Spencer", "spencer@gmail.com","SpencerMock"));
        userRepository.save(new User("Kendall", "Hutchinson", "kendall@gmail.com", "KendallMock"));
        userRepository.save(new User("Donovan", "Hawkins", "donovan@gmail.com", "DonovanMock"));
        userRepository.save(new User("Miriam", "Roman", "miriam@gmail.com", "MiriamMock"));

        offerRepository.save(new Offer(moss, "Learn FRP from the Dungeon Master", "I am a seasoned Dungeon Master and I will teach you your brand new life long hobby.", 3, "111"));
*/
    }

}
