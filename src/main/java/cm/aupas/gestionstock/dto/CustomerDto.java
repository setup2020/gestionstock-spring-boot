package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.Category;
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
    private Long companyId;
    @Embedded
    private AddressDto address;


    @JsonIgnore
    private List<OrderCustomerDto> orderCustomers;

    public static Customer toEntity(CustomerDto customerDto){

        if(customerDto==null){
            return null;
            // T
            // ODO
        }
        Customer customer=new Customer();
        customer.setMail(customerDto.getMail());
        customer.setPhone(customerDto.getPhone());
        customer.setPhoto(customerDto.getPhoto());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setId(customerDto.getId());
        customer.setAddress(AddressDto.toEntity(customerDto.getAddress()));
        //customer.setOrderCustomers(OrderCustomerDto.toEntity(customerDto.getOrderCustomers()));
        //customer.setAddress(customerDto.getAddress());

        return  customer;
    }

    public static CustomerDto fromEntity(Customer customer){
        if(customer==null){
            return null;
            // TODO
        }

        return  CustomerDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .id(customer.getId())
                .mail(customer.getMail())
                .phone(customer.getPhone())
                .photo(customer.getPhoto())
                .address(AddressDto.fromEntity(customer.getAddress()))
                .companyId(customer.getCompanyId())

                .build();

    }
}
