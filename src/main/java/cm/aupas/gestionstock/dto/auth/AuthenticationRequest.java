package cm.aupas.gestionstock.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest{

    String grantType;
    String username;
    String password;
     boolean withRefreshToken;
    String refreshToken;
}
