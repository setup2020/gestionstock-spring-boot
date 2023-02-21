package cm.aupas.gestionstock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mvt_stock")
public class MovementStock extends AbstractEntity{


    @Column(name = "date_mvt")
    private Instant dateMvt;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "type_mvt")
    private TypeMvtStock typeMvt;


    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;



}
