package spring.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import spring.check.oauth.service.Oauth2LoginServiceImpl;
import spring.check.oauth.service.Oauth2UserService;
import spring.check.user.dto.Members;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth2/login")
public class OauthController {

    private final Oauth2LoginServiceImpl oauth2LoginService;
    private final Oauth2UserService oauth2UserService;

    @GetMapping
    ResponseEntity<?> oauthLogin(@RequestParam String value){
        return oauth2LoginService.authorizationCodeRequest(value);
    }

    @GetMapping("/req")
    String oauthUserInfoCheck(@RequestParam String value,
                              @RequestParam(name = "error", required = false) String error,
                              @RequestParam(value = "code") String authCode, HttpSession session){
        if(error == null){
            String token = oauth2LoginService.tokenRequest(value, authCode);
            WebClient.RequestHeadersSpec<?> requestHeadersSpec = oauth2LoginService.clientInfoRequest(value, token);
            Members members = oauth2UserService.oauth2User(requestHeadersSpec, value);

            if(members != null){
                session.setAttribute("userNum" , members.getUserNum());
                session.setAttribute("userMail" , members.getUserMail());
                session.setAttribute("userImg", members.getUserImg());
                return "redirect:http://localhost:8080/check?date=" +
                        LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            }

        }

        return "/";
    }
}
