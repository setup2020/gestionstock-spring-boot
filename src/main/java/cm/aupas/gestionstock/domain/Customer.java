package cm.aupas.gestionstock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity{


    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private  String firstName;
    private String photo;
    private String mail;
    private String phone;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<OrderCustomer> orderCustomers;


}