package cm.aupas.gestionstock.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.List;

@Data
@Builder
public class SupplierDto {
    private Long id;
    private String lastName;

    private String firstName;
    private String photo;
    private String mail;
    private String phone;
    @Embedded
    private AddressDto address;
    private List<OrderSupplierDto> orderSuppliers;
}
