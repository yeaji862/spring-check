package spring.check.oauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import spring.check.oauth.OauthConfig;
import spring.check.oauth.dto.OauthLoginResponse;

import java.net.URI;

@Service
@Slf4j
@RequiredArgsConstructor
public class Oauth2LoginServiceImpl implements Oauth2LoginService {

    private final OauthConfig config;

    @Override
    public ResponseEntity<?> authorizationCodeRequest(String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(config.oauthInfo(value).authReqUrl()));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @Override
    public String tokenRequest(String value, String code) {
        String tokenReqUrl = config.oauthInfo(value).tokenReqUrl();
        return WebClient.builder().build().post()
                .uri(tokenReqUrl + "&&code=" + code)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(OauthLoginResponse.class).blockFirst().getAccess_token();
    }

    @Override
    public WebClient.RequestHeadersSpec<?> clientInfoRequest(String value, String token) {
        String userInfoUrl = config.oauthInfo(value).getUserInfoUrl();
        return WebClient.builder().build().get()
                .uri(userInfoUrl)
                .header("Authorization", "Bearer " + token);
    }
}
