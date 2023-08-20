package com.security.app.paylods;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.security.app.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto implements Serializable {
   
    private Long id;
    private String name;
    private String email;
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String about;
    
    private Set<RoleDto> roles=new HashSet<>();
    
   
    
}
