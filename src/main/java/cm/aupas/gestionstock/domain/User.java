package cm.aupas.gestionstock.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Builder
public class User  extends AbstractEntity{


    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name="date_birth")
    private Instant dateBirth;

    @Column(name = "password_hash")
    private  String password;
    @Embedded
    private  Address address;
    @Column(name = "email")
    private  String email;
    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "company_id")

    private Company company;

    @OneToMany(mappedBy = "user")
    private List<Role> roles;


}
