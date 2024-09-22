package com.tosin.notez.user;


import com.tosin.notez.Tables;
import com.tosin.notez.exception.NotezException;
import com.tosin.notez.tables.daos.UsersDao;
import com.tosin.notez.tables.pojos.Users;
import com.tosin.notez.user.dto.Role;
import com.tosin.notez.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserRepository {


    private final UsersDao usersDao;
    private final ModelMapper modelMapper;
    private final DSLContext dslContext;


    public UserDto save(UserDto userDto) {

        Users users = new Users();
        users.setId(UUID.randomUUID());
        users.setEmail(userDto.getEmail());
        users.setRole(userDto.getRole().name());
        users.setPassword(userDto.getPassword());
        users.setUsername(userDto.getUsername());
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setCreatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));
        users.setUpdatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));

        try {
            usersDao.insert(users);
        } catch (DuplicateKeyException duplicateKeyException) {

            throw new NotezException("email/username already taken", HttpStatus.BAD_REQUEST);
        }
        return map(users, false);
    }

    public Optional<UserDto> findUserByEmail(String email) {

        Optional<Users> optionalUser = usersDao.fetchOptionalByEmail(email);

        return optionalUser.map(users -> map(users, true));
    }

    public boolean userExists(String email) {

        return dslContext.fetchExists(dslContext.selectFrom(Tables.USERS)
                .where(Tables.USERS.EMAIL.eq(email)));
    }


    private UserDto map(Users user, boolean withPassword) {

        UserDto map = modelMapper.map(user, UserDto.class);
        if (!withPassword) {
            map.setPassword(null);
        }
        map.setRole(Role.valueOf(user.getRole()));
        return map;
    }




}
