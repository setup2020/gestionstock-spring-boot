package cm.aupas.gestionstock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "line_order_customer")
public class LineOrderCustomer extends AbstractEntity{


    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "order_customer_id")
    private OrderCustomer orderCustomer;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "price_unity")
    private BigDecimal priceUnity;



}
