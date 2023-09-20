package spring.check.oauth.kakao;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class KakaoAccount {
    private Boolean has_email;
    private Boolean email_needs_agreement;
    private Boolean is_email_valid;
    private Boolean is_email_verified;
    private String email;
}
