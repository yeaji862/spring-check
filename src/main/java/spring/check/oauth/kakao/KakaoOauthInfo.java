package spring.check.oauth.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.check.oauth.dto.OauthInfo;

@Component
public class KakaoOauthInfo implements OauthInfo {

    @Value("${kakao.userInfo.url}")
    private String userInfoUrl;
    @Value("${kakao.redirect.url}")
    private String redirectUrl;
    @Value("${kakao.client.id}")
    private String clientId;

    @Override
    public String getUserInfoUrl(){
        return userInfoUrl;
    }

    @Override
    public String authReqUrl(){
        return "https://kauth.kakao.com/oauth/authorize?" +
                "client_id=" + clientId +
                "&&redirect_uri=" + redirectUrl +
                "&&response_type=code";
    }
    @Override
    public String tokenReqUrl(){
        return "https://kauth.kakao.com/oauth/token?" +
                "client_id=" + clientId +
                "&&redirect_uri=" + redirectUrl +
                "&&grant_type=authorization_code";
    }
}
