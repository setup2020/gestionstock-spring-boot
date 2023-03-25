package cm.aupas.gestionstock.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChangePasswordDto {
    private Long id;
    private String password;
    private String confirmPassword;
}
