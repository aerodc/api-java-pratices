package services;

import javax.inject.Singleton;

@Singleton
public class AuthService {


    public boolean authenticate(String username, String password){

        // todo password hash validation

        return "admin".equals(username) && "admin".equals(password);
    }

    public boolean accessTokenValidation(String accessToken){
        //todo

        return "accesstokenadmintest123".equals(accessToken);
    }
}
