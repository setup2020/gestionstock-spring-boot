package cm.aupas.gestionstock.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "created_at")
   // @CreatedDate
    private Instant createdAt;
    @Column(name = "updated_at")
   // @LastModifiedDate

    private Instant updatedAt;
    @PrePersist
    void prePersist(){
        createdAt=Instant.now();
    }
    @PreUpdate
    void preUpdate(){
        updatedAt=Instant.now();
    }

}
