package swe.dev.timeoffers.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="offer_id")
    private Long id;


    private String offerTitle;
    private String offerDescription;

    private String latitude;
    private String longitude;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    private Integer startTime; //It is going to be a value out of 24.
    private Integer offerTimeAmount;

    private boolean isEvent;

    private String offerPicture;
    private String offerImagePath;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User creatorUser;

    public Offer(User creatorUser, String offerTitle, String offerDescription, Integer offerTimeAmount, String latitude, String longitude) {
        this.creatorUser = creatorUser;
        this.offerTitle = offerTitle;
        this.offerDescription = offerDescription;
        this.offerTimeAmount = offerTimeAmount;
        if(offerTimeAmount==0)this.isEvent=true;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Offer() {
        startDate = new Date();
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public Integer getOfferTimeAmount() {
        return offerTimeAmount;
    }

    public void setOfferTimeAmount(Integer offerTimeAmount) {
        this.offerTimeAmount = offerTimeAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
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

    public boolean isEvent() {
        return isEvent;
    }

    public void setEvent(boolean event) {
        isEvent = event;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public String getOfferPicture() {
        return offerPicture;
    }

    public void setOfferPicture(String offerPicture) {
        this.offerPicture = offerPicture;
    }

    public String getOfferImagePath() {
        return offerImagePath;
    }

    public void setOfferImagePath(String offerImagePath) {
        this.offerImagePath = offerImagePath;
    }
}
