package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.Supplier;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String email;
    private String phone;
    @Embedded
    private AddressDto address;

    private Long companyId;

    @JsonIgnore
    private List<OrderSupplierDto> orderSuppliers;
    public static SupplierDto fromEntity(Supplier supplier){
        if(supplier==null){
            return null;
            // TODO
        }

        return  SupplierDto.builder()
                .firstName(supplier.getFirstName())
                .lastName(supplier.getLastName())
                .id(supplier.getId())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .photo(supplier.getPhoto())
                .address(AddressDto.fromEntity(supplier.getAddress()))
                .companyId(supplier.getCompanyId())

                .build();

    }
    public static Supplier toEntity(SupplierDto supplierDto){

        if(supplierDto==null){
            return null;
            // T
            // ODO
        }
        Supplier supplier=new Supplier();
        supplier.setEmail(supplierDto.getEmail());
        supplier.setPhone(supplierDto.getPhone());
        supplier.setPhoto(supplierDto.getPhoto());
        supplier.setFirstName(supplierDto.getFirstName());
        supplier.setLastName(supplierDto.getLastName());
        supplier.setId(supplierDto.getId());
        supplier.setAddress(AddressDto.toEntity(supplierDto.getAddress()));
        supplier.setCompanyId(supplierDto.getCompanyId());


        return  supplier;
    }



}
