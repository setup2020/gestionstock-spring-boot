package cm.aupas.gestionstock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sale")

public class Sale extends AbstractEntity{

    // FIXE

    @Column(name = "reference")
    private  String reference;

    @Column(name = "date_sale")
    private Instant dateSale;

    @Column(name = "name")
    private String comment;

}
