package swe.dev.timeoffers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import swe.dev.timeoffers.entity.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {
    public List<Offer> findAllByCreatorUserId(Long creatorUserId);
    //public List<Offer> findAllByGeoLocation(String geoLocation);
    public List<Offer> findAll();
}