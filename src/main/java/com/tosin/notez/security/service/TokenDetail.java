package com.tosin.notez.security.service;


import com.tosin.notez.user.dto.Role;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDetail {

    private UUID id;
    private String email;
    private String fullName;
    private String username;
    private Role role;
}
