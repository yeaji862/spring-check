package spring.check.oauth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

public interface Oauth2LoginService {

    ResponseEntity<?> authorizationCodeRequest(String url); // 인가 코드 요청
    String tokenRequest(String value, String code); // 토큰 요청
    WebClient.RequestHeadersSpec<?> clientInfoRequest(String value, String token); // 회원 정보 요청

}
