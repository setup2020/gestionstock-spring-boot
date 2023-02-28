package cm.aupas.gestionstock.handlers;

import cm.aupas.gestionstock.exceptions.ErrorCode;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;
    private ErrorCode code;

    private String message;
    private List<String> errors=new ArrayList<>();

}
