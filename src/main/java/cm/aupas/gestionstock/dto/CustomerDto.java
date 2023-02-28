package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.List;

@Data
@Builder
public class CustomerDto {
    private Long id;
    private String lastName;

    private String firstName;
    private String photo;
    private String mail;
    private String phone;
    @Embedded
    private AddressDto address;


    @JsonIgnore
    private List<OrderCustomerDto> orderCustomers;

    public Customer toEntity(CustomerDto customerDto){

        return  Customer.builder().build();
    }
}
