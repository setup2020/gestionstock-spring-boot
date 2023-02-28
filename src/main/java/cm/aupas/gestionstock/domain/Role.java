package cm.aupas.gestionstock.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "role")
public class Role extends AbstractEntity{

    @Column(name = "role_name")
    private  String roleName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
