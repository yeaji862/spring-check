package spring.check.oauth;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OauthLoginResponse {
    private String access_token;
    private String refreshToken;
    private String scope;
    private String token_type;
}
