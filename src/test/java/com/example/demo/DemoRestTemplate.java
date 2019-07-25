package com.example.demo;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dev.dto.CollegueDTO;
import com.dev.security.InfosAuthentification;

public class DemoRestTemplate {

    public static void main(String[] args) throws URISyntaxException {
        RestTemplate rt = new RestTemplate();
        InfosAuthentification infos = new InfosAuthentification();
        infos.setNomUtilisateur("alexei01");
        infos.setMotDePasse("alexeipass");

        ResponseEntity<String> postForEntity = rt.postForEntity("https://aa-collegues-api.herokuapp.com/collegues/auth", infos, String.class);
        String cookieAuth = postForEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        RequestEntity<Void> req = RequestEntity.get(new URI("https://aa-collegues-api.herokuapp.com/collegues/me"))
                .header(HttpHeaders.COOKIE, cookieAuth)
                .build();
        CollegueDTO collegue = rt.exchange(req, CollegueDTO.class).getBody();
        System.out.println(collegue.getMotDePasse());
    }

}
