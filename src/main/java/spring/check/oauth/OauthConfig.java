package spring.check.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import spring.check.oauth.dto.OauthInfo;
import spring.check.oauth.google.GoogleUserInfoResponse;
import spring.check.oauth.google.GoogleOauthInfo;
import spring.check.oauth.kakao.KakaoOauthInfo;
import spring.check.oauth.kakao.KakaoUserInfoResponse;
import spring.check.oauth.naver.NaverUserInfoResponse;
import spring.check.oauth.naver.NaverOauthInfo;

@Configuration
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
                KakaoUserInfoResponse kakaoUserInfoResponse = requestHeadersSpec.retrieve().bodyToFlux(KakaoUserInfoResponse.class).blockFirst();
                userMail =  (kakaoUserInfoResponse.getKakao_account().getEmail() != null) ?
                kakaoUserInfoResponse.getKakao_account().getEmail() : String.valueOf(kakaoUserInfoResponse.getId()); break; // 카카오는 이메일 값이 필수 요청 값이 아니기 때문에 이메일 정보가 없을 때에는 고유 id 번호 값으로 저장
            case "naver" :
                userMail =  requestHeadersSpec.retrieve().bodyToFlux(NaverUserInfoResponse.class).blockFirst().getResponse().getEmail(); break;
        }

        return userMail;
    }

}
