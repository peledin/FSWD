package ch.zhaw.fswd.powerDate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "UserTable")
public class User {

    @Id
    private String loginName;

    private String passwordHash;

    private String roleName;

    @OneToOne
    private Profile profile;

}
