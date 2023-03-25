package cm.aupas.gestionstock.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
@Builder
public class Customer extends AbstractEntity{


    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private  String firstName;
    @Column(name = "code")
    private  String code;
    private String photo;
    private String email;
    private String phone;
    @Embedded
    private Address address;


    @Column(name = "company_id")
    private Long companyId;

    @OneToMany(mappedBy = "customer")
    private List<OrderCustomer> orderCustomers;


}
