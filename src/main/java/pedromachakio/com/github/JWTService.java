package pedromachakio.com.github;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import pedromachakio.com.github.domain.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JWTService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.signature-key}")
    private String signatureKey;

    public String generateToken(User user) {
        long expirationString = Long.valueOf(expiration); // para converter string para long
        LocalDateTime actualTimeOfExpiration = LocalDateTime.now().plusMinutes(expirationString); // tou a somar a hora atual com a duração de expiração para dar a hora de expiraçao
        Instant instant = actualTimeOfExpiration.atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(instant);

      /*  HashMap<String, Object> claims = new HashMap<>();
        claims.put("userEmail", "useremail@email.com");
        claims.put("userRoles", "USER");*/

        System.out.println( "GANDA CHAVE O MANIHNO !!!!!!!!!!!!! " + signatureKey);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact(); // para gerar uma string

                //.setClaims(claims) // podia passar outras infos customizadas
    }

    private Claims getClaims(String token) throws ExpiredJwtException { // throw if token is expired
        return Jwts
                .parser()
                .setSigningKey(signatureKey)
                .parseClaimsJws(token)
                .getBody();
    } // para decode e obter as infos (claims) do token

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime localTime = expirationDate
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();// para converter um objeto do tipo Date para LocalDateTime

            return !LocalDateTime.now().isAfter(localTime); // é valido quando a data agora nao passou a data de expiraçao
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoggedUser(String token) throws ExpiredJwtException {
        return getClaims(token).getSubject();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SalesApp.class);
        JWTService service = context.getBean(JWTService.class);
        User user = User.builder().username("fulano").build();
        String token = service.generateToken(user);
        System.out.println("!!!!!!!!!!!!!!!!! TA AQUI CRL !!!!!!!!!!!!!!!!!!!!!!!!! :  " + token);

        System.out.println(service.isTokenValid("Token valido?? " + token));
        System.out.println(service.getLoggedUser("the user is: " + token));
    }
}
