package com.lingxi.lingxi_java.auth;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lingxi.lingxi_java.auth.domain.User;
import com.lingxi.lingxi_java.auth.domain.UserRepository;
import com.lingxi.lingxi_java.utils.EncryptUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MyRepositoryTests {

    @Autowired
    private UserRepository repository;

    @Test
    void testExample() {
        User user = new User();
        String username = "holyliao";

        user.setUsername(username);
        user.setPassword(EncryptUtil.md5(username));
        repository.save(user);
        Optional<User> target = this.repository.findOneByUsername(username);

        assertTrue(target.isPresent());
        assertEquals(target.get().getUsername(), username);
        assertEquals(target.get().getPassword(), EncryptUtil.md5(username));
    }

}