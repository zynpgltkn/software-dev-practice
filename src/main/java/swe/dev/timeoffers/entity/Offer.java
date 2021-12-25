package swe.dev.timeoffers.entity;

import javax.persistence.*;
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
    private Integer offerTimeAmount;
    private String geoLocation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User creatorUser;

    public Offer() {
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

    public String getGeoLocation() {
        return geoLocation;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }
}
