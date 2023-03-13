package cm.aupas.gestionstock.domain;

import cm.aupas.gestionstock.domain.enums.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "order_supplier")
public class OrderSupplier extends AbstractEntity{
    @Column(name = "reference")
    private String reference;
    @Column(name = "date_order")
    private Instant dateOrder;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "company_id")
    private Long companyId;
    private StatusOrder status;



    @OneToMany(mappedBy = "orderSupplier")
    private List<LineOrderSupplier> lineOrderSuppliers;

}
