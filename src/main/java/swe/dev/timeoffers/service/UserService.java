package swe.dev.timeoffers.service;

import swe.dev.timeoffers.entity.User;
import swe.dev.timeoffers.exception.UserAlreadyExistException;

public interface UserService {
    void save(final User user) throws UserAlreadyExistException;
    //boolean checkIfUserExist(final String email);
    //void sendRegistrationConfirmationEmail(final UserProfile user);
    //boolean verifyUser(final String token) throws InvalidTokenException;
    //Optional<UserProfile> getUserById(final String id) throws UnkownIdentifierException;
}
