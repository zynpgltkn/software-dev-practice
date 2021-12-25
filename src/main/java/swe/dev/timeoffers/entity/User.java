package swe.dev.timeoffers.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.awt.*;
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

    private String description;

    @Column(nullable = true, length = 64)
    private String profilePicture;

    private String photosImagePath;

    @OneToMany(mappedBy = "creatorUser", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    private Set<Offer> offerSet;

    private String geoLocation;

    public Long getId() {
        return id;
    }

    public User(){
    }

    public User(String firstName, String lastName, String email, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
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

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
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
}
