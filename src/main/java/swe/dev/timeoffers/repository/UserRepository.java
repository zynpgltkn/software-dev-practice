package swe.dev.timeoffers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import swe.dev.timeoffers.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findByLastName(String lastName);
    public List<User> findByEmail(String email);
}
