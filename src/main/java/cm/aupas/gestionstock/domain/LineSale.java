package cm.aupas.gestionstock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "line_sale")
public class LineSale extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;


    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;


    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "price_unity")
    private BigDecimal priceUnity;

}
