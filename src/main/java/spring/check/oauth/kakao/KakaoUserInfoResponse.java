package spring.check.oauth.kakao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KakaoUserInfoResponse {
    private Long id;
    private String connected_at;
    private KakaoAccount kakao_account;
}
