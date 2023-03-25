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

    @Column(name = "name")
    private  String name;
    @Column(name = "status")
    private  String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
