package cm.aupas.gestionstock.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@MappedSuperclass
public class AbstractEntity  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "created_at",nullable = false)
    @CreatedDate
    @JsonIgnore
    private Instant createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    @JsonIgnore
    private Instant updatedAt;
}
