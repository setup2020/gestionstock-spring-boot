package cm.aupas.gestionstock.dto;

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


    private List<LineOrderCustomerDto> lineOrderCustomers;
}
