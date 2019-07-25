package com.dev.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.dev.dto.CollegueDTO;
import com.dev.entity.TopCollegue;
import com.dev.persistence.TopCollegueRepository;
import com.dev.security.InfosAuthentification;
import com.dev.service.TopCollegueService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(path = "/topcollegues")
public class AuthentificationController {

    @Value("${jwt.expires_in}")
    private Integer EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${collegue.api.url}")
    private String API_URL;

    @Autowired
    private TopCollegueService topCollegueService;

    @Autowired
    private TopCollegueRepository topCollegueRepository;

    private PasswordEncoder passwordEncoder;

    public AuthentificationController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> authenticate(@RequestBody InfosAuthentification infos) throws URISyntaxException {

        Optional<TopCollegue> topCollegueRecherche = this.topCollegueService.findByNomUtilisateur(infos.getNomUtilisateur());

        if (!topCollegueRecherche.isPresent()) {
            RestTemplate rt = new RestTemplate();
            try {
                ResponseEntity<String> postForEntity = rt.postForEntity(API_URL + "collegues/auth", infos,
                        String.class);
                String cookieAuth = postForEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

                RequestEntity<Void> req = null;
                try {
                    req = RequestEntity.get(new URI(API_URL + "collegues/me"))
                            .header(HttpHeaders.COOKIE, cookieAuth)
                            .build();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                CollegueDTO collegue = rt.exchange(req, CollegueDTO.class).getBody();

                TopCollegue topCollegue = null;

                if (collegue != null && collegue.getMatricule() != null) {
                    topCollegue = new TopCollegue(collegue.getMatricule(), collegue.getNomUtilisateur(), collegue.getMotDePasse(),
                            collegue.getNom(), collegue.getPrenoms(),
                            collegue.getPhotoUrl(), collegue.getRoles());

                    if (infos.getPhotoUrl() != null && infos.getPhotoUrl() != "") {
                        topCollegue.setPhotoUrl(infos.getPhotoUrl()); // TODO exception si photoURL pas conforme
                    }

                    this.topCollegueService.creerTopCollegue(topCollegue);
                    topCollegueRecherche = Optional.of(topCollegue);
                }
            } catch (HttpClientErrorException e) {
            }
        }

        return topCollegueRecherche
                .filter(utilisateur -> passwordEncoder.matches(infos.getMotDePasse(), utilisateur.getMotDePasse()))
                .map(utilisateur -> {

                    Map<String, Object> infosSupplementaireToken = new HashMap<>();
                    infosSupplementaireToken.put("roles", utilisateur.getRoles());

                    if (infos.getPhotoUrl() != null && infos.getPhotoUrl() != "") {
                        utilisateur.setPhotoUrl(infos.getPhotoUrl()); // TODO exception si photoURL pas conforme
                    }

                    this.topCollegueRepository.flush();

                    String jetonJWT = Jwts.builder()
                            .setSubject(utilisateur.getNomUtilisateur())
                            .addClaims(infosSupplementaireToken)
                            .setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
                            .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
                            .compact();

                    ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJWT)
                            .httpOnly(true)
                            .maxAge(EXPIRES_IN * 1000)
                            .path("/")
                            .build();

                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                            .build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
