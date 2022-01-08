package swe.dev.timeoffers.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;
    private String matchingPassword;

    private String description;

    @Column(length = 64)
    private String profilePicture;
    private String photosImagePath;

    @OneToMany(mappedBy = "creatorUser", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    private Set<Offer> offerSet;

    private String latitude;
    private String longitude;

    private Integer time;

    private boolean isMock;

    public Long getId() {
        return id;
    }

    public User(){

    }




    public void setDefaults(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        setProfilePicture("icon.jpeg","uploads");
        setLatitude("40.989250");
        setLongitude("29.138293");
        setTime(15);
        if(isMock == true) this.password = passwordEncoder.encode(this.password);
    }

    public User(String firstName, String lastName, String email, String password, boolean isMock) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        this.isMock = isMock;
        if(isMock == true) setDefaults();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId() {
    }

    public Set<Offer> getOfferSet() {
        return offerSet;
    }

    public void setOfferSet(Set<Offer> offerSet) {
        this.offerSet = offerSet;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture, String uploadDir) {
        this.profilePicture = profilePicture;
        setPhotosImagePath(uploadDir + "/" + profilePicture);
    }

    public String getPhotosImagePath() {
        return photosImagePath;
    }

    public void setPhotosImagePath(String photosImagePath) {
        this.photosImagePath = photosImagePath;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public boolean isMock() {
        return isMock;
    }

    public void setMock(boolean mock) {
        isMock = mock;
    }
}
