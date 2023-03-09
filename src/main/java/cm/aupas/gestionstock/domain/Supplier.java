package cm.aupas.gestionstock.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "supplier")
public class Supplier extends AbstractEntity {

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private  String firstName;
    private String photo;
    private String email;
    private String phone;
    @Embedded
    private Address address;

    @Column(name = "company_id")
    private Long companyId;
    @OneToMany(mappedBy = "supplier")
    private List<OrderSupplier> orderSuppliers;
}
