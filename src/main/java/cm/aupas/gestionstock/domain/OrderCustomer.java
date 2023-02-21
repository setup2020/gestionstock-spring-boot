package cm.aupas.gestionstock.domain;


import ch.qos.logback.core.net.server.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "order_customer")
public class OrderCustomer extends AbstractEntity{

    @Column(name = "reference")
    private String reference;

    @Column(name = "date_order")
    private Instant dateOrder;

    @ManyToOne
    @JoinColumn(name ="client_id")
    private Customer customer;

    @OneToMany(mappedBy = "orderCustomer")
    private List<LineOrderCustomer> lineOrderCustomers;
}
