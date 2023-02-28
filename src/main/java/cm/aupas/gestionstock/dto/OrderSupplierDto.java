package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.Supplier;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class OrderSupplierDto {

    private Long id;
    private String reference;

    private Instant dateOrder;


    private Supplier supplier;


    private List<LineOrderSupplierDto> lineOrderSuppliers;
}
