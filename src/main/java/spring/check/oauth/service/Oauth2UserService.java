package spring.check.oauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import spring.check.editPassLink.CreateRandomId;
import spring.check.oauth.OauthConfig;
import spring.check.oauth.repository.OauthMapper;
import spring.check.user.dto.Members;
import spring.check.user.UserPassEncoder;

@Service
@Slf4j
@RequiredArgsConstructor
public class Oauth2UserService{

    private final OauthMapper mapper;
    private final OauthConfig oauthConfig;
    private final UserPassEncoder userPassEncoder;


    public Members oauth2User(WebClient.RequestHeadersSpec<?> requestHeadersSpec, String division) {
        String userMail = oauthConfig.userMailFind(requestHeadersSpec, division);
        Members members = mapper.findId(userMail, division);

        if(members == null){
            members = setMembers(userMail, division);
            members.setUserNum(mapper.signIn(setMembers(userMail, division)));
            members = mapper.findId(userMail, division);
        }

        return members;

    }

    private Members setMembers(String userMail, String division){
        Members members = new Members();
        members.setUserMail(userMail);
        members.setUserPass(userPassEncoder.passwordEncoder(new CreateRandomId().createRandomId()));
        members.setDivision(division);

        return members;
    }
}
