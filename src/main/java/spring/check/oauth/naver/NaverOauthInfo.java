package spring.check.oauth.naver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.check.oauth.dto.OauthInfo;

@Component
public class NaverOauthInfo implements OauthInfo {

    @Value("${naver.auth.url}")
    private String url;
    @Value("${naver.userInfo.url}")
    private String userInfoUrl;
    @Value("${naver.redirect.url}")
    private String redirectUrl;
    @Value("${naver.client.id}")
    private String clientId;
    @Value("${naver.secret}")
    private String secret;

    @Override
    public String getUserInfoUrl(){
        return userInfoUrl;
    }

    @Override
    public String authReqUrl(){
        return "https://nid.naver.com/oauth2.0/authorize?" +
                "client_id=" + clientId +
                "&&redirect_uri=" + redirectUrl +
                "&&response_type=code&&state=0000";
    }
    @Override
    public String tokenReqUrl(){
        return "https://nid.naver.com/oauth2.0/token?" +
                "client_id=" + clientId +
                "&&client_secret=" + secret +
                "&&grant_type=authorization_code&&state=0000";
    }
}
