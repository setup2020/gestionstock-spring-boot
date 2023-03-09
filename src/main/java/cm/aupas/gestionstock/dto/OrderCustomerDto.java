package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.Address;
import cm.aupas.gestionstock.domain.Category;
import cm.aupas.gestionstock.domain.OrderCustomer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class OrderCustomerDto {
    private Long id;
    private String reference;


    private Instant dateOrder;


    private CustomerDto customer;

    private String status;

    private Long companyId;



    private List<LineOrderCustomerDto> lineOrderCustomers;

    public static OrderCustomer toEntity(OrderCustomerDto orderCustomerDto) {
        if (orderCustomerDto == null) {
            return null;
        }

        OrderCustomer orderCustomer=new OrderCustomer();
        orderCustomer.setId(orderCustomerDto.getId());
        orderCustomer.setReference(orderCustomerDto.getReference());
        orderCustomer.setCustomer(CustomerDto.toEntity(orderCustomerDto.getCustomer()));
        orderCustomer.setStatus(orderCustomerDto.getStatus());
        orderCustomer.setCustomer(CustomerDto.toEntity(orderCustomerDto.getCustomer()));
        orderCustomer.setCompanyId(orderCustomer.getCompanyId());
        orderCustomer.setCustomer(CustomerDto.toEntity(orderCustomerDto.getCustomer()));

        return  orderCustomer;
    }

    public  static   OrderCustomerDto fromEntity(OrderCustomer orderCustomer) {
        if (orderCustomer == null) {
            return null;
        }

       return  OrderCustomerDto
               .builder()
               .id(orderCustomer.getId())
               .customer(CustomerDto.fromEntity(orderCustomer.getCustomer()))
               .status(orderCustomer.getStatus())
               .companyId(orderCustomer.getCompanyId())
               .reference(orderCustomer.getReference())
               .dateOrder(orderCustomer.getDateOrder())

               .build();
    }
    }
