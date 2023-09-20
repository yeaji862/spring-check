package spring.check.oauth.naver;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NaverUserInfoResponse {

    private String resultcode;
    private String message;
    private NaverResponse response;
}
