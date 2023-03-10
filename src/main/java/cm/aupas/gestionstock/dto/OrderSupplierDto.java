package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.OrderSupplier;
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
    private  Long companyId;
    private Long supplierId;


    private List<LineOrderSupplierDto> lineOrderSuppliers;

    public static OrderSupplierDto fromEntity(OrderSupplier orderSupplier){
        if(orderSupplier==null){
            return null;
        }
       return OrderSupplierDto.builder()
                .id(orderSupplier.getId())
                .reference(orderSupplier.getReference())
                .dateOrder(orderSupplier.getDateOrder())
                .companyId(orderSupplier.getCompanyId())

              //  .supplier(SupplierDto.fromEntity(orderSupplier.getSupplier()))
                .build();


    }

    public  static  OrderSupplier toEntity(OrderSupplierDto orderSupplierDto){
        OrderSupplier orderSupplier=new OrderSupplier();
        orderSupplier.setId(orderSupplierDto.getId());
        orderSupplier.setReference(orderSupplierDto.getReference());
        orderSupplier.setDateOrder(orderSupplierDto.getDateOrder());
        orderSupplier.setCompanyId(orderSupplierDto.getCompanyId());

       // orderSupplier.setSupplier(SupplierDto.toEntity(orderSupplierDto.getSupplier()));

        return orderSupplier;
    }
}
