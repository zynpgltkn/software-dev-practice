package swe.dev.timeoffers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import swe.dev.timeoffers.entity.Offer;
import swe.dev.timeoffers.entity.User;
import swe.dev.timeoffers.repository.OfferRepository;
import swe.dev.timeoffers.repository.UserRepository;

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

        User moss = new User("Maurice", "Moss", "maurice@gmail.com", "MauriceMock",true);
        userRepository.save(moss);
        User santino = new User("Santino", "Briggs", "santino@gmail.com", "SantinoMock",true);
        userRepository.save(santino);

        userRepository.save(new User("Kiersten", "Rollins", "kiersten@gmail.com", "KierstenMock",true));

        User alan = new User("Alan", "Jensen", "alan@gmail.com", "AlanMock",true);
        userRepository.save(alan);

        userRepository.save(new User("Chaim", "Franklin", "chaim@gmail.com", "ChaimMock",true));
        userRepository.save(new User("Cole", "Livingston", "cola@gmail.com", "ColeMock",true));
        userRepository.save(new User("Teagan", "Henry", "teagan@gmail.com", "TeaganMock",true));
        userRepository.save(new User("Nathaly", "Payne", "nathaly@gmail.com", "NathalyMock",true));
        userRepository.save(new User("Kameron", "Spencer", "spencer@gmail.com","SpencerMock",true));
        userRepository.save(new User("Kendall", "Hutchinson", "kendall@gmail.com", "KendallMock",true));
        userRepository.save(new User("Donovan", "Hawkins", "donovan@gmail.com", "DonovanMock",true));
        userRepository.save(new User("Miriam", "Roman", "miriam@gmail.com", "MiriamMock",true));

        offerRepository.save(new Offer(moss, "Learn FRP from the Dungeon Master", "I am a seasoned Dungeon Master and I will teach you your brand new life long hobby.", 3, "40.989250", "29.138293", "2022-01-11", 13));
        offerRepository.save(new Offer(moss, "Learn FRP from the Dungeon Master - Evening", "I am a seasoned Dungeon Master and I will teach you your brand new life long hobby.", 3, "40.989250", "29.138293","2022-01-31", 11));

        offerRepository.save(new Offer(santino, "Wing Chun Beginner Lesson", "Do you want to learn Wing Chun from Sihing Santino? Join me on one-to-one sessions where we start dive into the basic forms of Wing Chun, starting with Siu Nim Tau.", 1, "40.989250", "29.138293","2022-01-31", 19));
        offerRepository.save(new Offer(santino, "Wing Chun Intermediate Lesson", "Sihing Santino here! Do you want to keep on learning with Chum Kiu? You will be practicing on 'seeking the bridge' with you and your opponent! Apply today!", 1, "40.989250", "29.138293","2022-01-31", 9));
        offerRepository.save(new Offer(alan, "Get Some Sticks and Start Hitting!", "Get your rhythm swing on! I will show you my Drum kit :). What are the parts of the drum kit? How to hold drumsticks? Start practicing your rhythm with basic rudiments.", 1, "40.989250", "29.138293","2022-01-31", 13));

    }

}
