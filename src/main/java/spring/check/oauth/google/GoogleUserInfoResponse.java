package spring.check.oauth.google;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class GoogleUserInfoResponse {
    private String sub;
    private String email;

}
