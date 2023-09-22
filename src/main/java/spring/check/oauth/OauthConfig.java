package spring.check.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import spring.check.oauth.google.GoogleUserInfoResponse;
import spring.check.oauth.google.GoogleOauthInfo;
import spring.check.oauth.kakao.KakaoOauthInfo;
import spring.check.oauth.kakao.KakaoUserInfoResponse;
import spring.check.oauth.naver.NaverUserInfoResponse;
import spring.check.oauth.naver.NaverOauthInfo;

@Component
@RequiredArgsConstructor
public class OauthConfig {

    private final GoogleOauthInfo googleOauthInfo;
    private final KakaoOauthInfo kakaoOauthInfo;
    private final NaverOauthInfo naverOauthInfo;

    public OauthInfo oauthInfo(String value){

        OauthInfo oauthInfo = null;
        switch (value){
            case "google":
                oauthInfo = googleOauthInfo; break;
            case "kakao":
                oauthInfo = kakaoOauthInfo; break;
            case "naver":
                oauthInfo = naverOauthInfo; break;
        }

        return oauthInfo;
    }

    public String userMailFind(WebClient.RequestHeadersSpec<?> requestHeadersSpec, String division){

        String userMail = "";

        switch (division){
            case "google" :
                userMail =  requestHeadersSpec.retrieve().bodyToFlux(GoogleUserInfoResponse.class).blockFirst().getEmail(); break;
            case "kakao" :
                userMail =  requestHeadersSpec.retrieve().bodyToFlux(KakaoUserInfoResponse.class).blockFirst().getKakao_account().getEmail(); break;
            case "naver" :
                userMail =  requestHeadersSpec.retrieve().bodyToFlux(NaverUserInfoResponse.class).blockFirst().getResponse().getEmail(); break;
        }

        return userMail;
    }

}