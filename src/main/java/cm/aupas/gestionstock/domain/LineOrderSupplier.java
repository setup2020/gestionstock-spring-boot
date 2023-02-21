package cm.aupas.gestionstock.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "line_order_supplier")
public class LineOrderSupplier extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "order_supplier_id")
    private OrderSupplier orderSupplier;


    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "price_unity")
    private BigDecimal priceUnity;



}
