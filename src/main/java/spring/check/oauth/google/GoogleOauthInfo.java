package spring.check.oauth.google;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.check.oauth.dto.OauthInfo;

@Component
public class GoogleOauthInfo implements OauthInfo{

    @Value("${google.auth.url}")
    private String url;
    @Value("${google.userInfo.url}")
    private String userInfoUrl;
    @Value("${google.redirect.url}")
    private String redirectUrl;
    @Value("${google.client.id}")
    private String clientId;
    @Value("${google.secret}")
    private String secret;

    @Override
    public String getUserInfoUrl(){
        return userInfoUrl;
    }

    @Override
    public String authReqUrl(){
        return "https://accounts.google.com/o/oauth2/v2/auth?" +
                "client_id=" + clientId +
                "&&redirect_uri=" + redirectUrl +
                "&&scope=profile,email&&access_type=offline&&response_type=code";
    }
    @Override
    public String tokenReqUrl(){
        return "https://oauth2.googleapis.com/token?" +
                "clientId=" + clientId +
                "&&clientSecret=" + secret +
                "&&grantType=authorization_code" +
                "&&redirectUri=" + redirectUrl;
    }

}
