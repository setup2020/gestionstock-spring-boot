package cm.aupas.gestionstock.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.Instant;

@Data
@Builder
public class SaleDto {


    private Long id;
    private  String reference;

    private Instant dateSale;

    private String comment;
}
