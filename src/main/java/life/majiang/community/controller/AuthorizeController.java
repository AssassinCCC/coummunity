package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.url}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        //accessTokenDTO.setRedirect_url("http://localhost:8080/callback");
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setState(state);
        //accessTokenDTO.setClient_secret("f7d5a98683aea8bf279e7391162d9c9afa154c9a");
        accessTokenDTO.setClient_secret(clientSecret);
        //accessTokenDTO.setClient_id("79e6a1234dd794c27828");
        accessTokenDTO.setClient_id(clientId);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}