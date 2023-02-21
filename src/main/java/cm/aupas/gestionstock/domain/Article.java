package cm.aupas.gestionstock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "article")
public class Article extends AbstractEntity{


    @Column(name = "reference")
    private String reference;
    @Column(name = "designation")
    private String designation;
    @Column(name = "price_unit_ht")
    private BigDecimal priceUnitHt;
    @Column(name = "tva")
    private  BigDecimal tva;
    @Column(name = "price_unit_ttc")
    private BigDecimal priceUnitTtc;
    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "cagegory_id")
    private Category category;

}
