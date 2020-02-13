package com.upgrad.proman.service.business;

import com.upgrad.proman.service.dao.UserDao;
import com.upgrad.proman.service.entity.UserEntity;
import com.upgrad.proman.service.exception.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;

    public UserEntity authenticate(final String username, final String password) throws AuthenticationFailedException {

        UserEntity userEntity = userDao.getUserByEmail(username);

        if(userEntity == null){
            throw new AuthenticationFailedException("ATH-001", "User email is not found");
        }

        final String encryptedPassword = passwordCryptographyProvider.encrypt(password, userEntity.getSalt());
        // Base64 encode -  password - password -  p -   0101   0101   0101   0101 - DIXW - p
        //                                                D      I       X       W


        if(encryptedPassword.equals(userEntity.getPassword())){
            // generate JWT send this as a response
            // Header - alg: "HS256" , type - "JWT"
            // Payload - "userinformation
            // signature - "secret
            // KWJKJWERKJNR.KJBASDKAKJDA.OJAOISDOAIHDA
            // AUTH0
            return null;
        }
        else
        {
            throw new AuthenticationFailedException("ATH-002", "Password Incorrect!");
        }

    }
}
