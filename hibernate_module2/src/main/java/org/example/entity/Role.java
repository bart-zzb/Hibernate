package org.example.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    Long id;
    String name;
    Set<Permission> permissions = new HashSet<Permission>();
}
