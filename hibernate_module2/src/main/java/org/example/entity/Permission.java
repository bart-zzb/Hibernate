package org.example.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {
    Long id;
    String name;
    Set<Role> roles = new HashSet<Role>();
}
