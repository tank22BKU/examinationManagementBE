package com.examManagementBE.entity.user;

import com.examManagementBE.common.constants.EntityConstants;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = EntityConstants.ROLE_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_ID")
    Integer roleId;

    @Column(name = "Role_name", length = 50, nullable = false, unique = true)
    String roleName;
}