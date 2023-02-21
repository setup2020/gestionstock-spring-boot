package cm.aupas.gestionstock.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company extends AbstractEntity{

    @Column(name = "name")
    private String name;
    @Embedded
    private Address address;

    @Column(name = "code_fiscal")
    private  String codeFiscal;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private  String phone;

    @Column(name = "web_site")
    private String webSite;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "company")
    List<User> users;


}
