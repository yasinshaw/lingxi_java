package com.lingxi.lingxi_java.auth;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lingxi.lingxi_java.auth.domain.User;
import com.lingxi.lingxi_java.auth.domain.UserRepository;
import com.lingxi.lingxi_java.utils.EncryptUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("h2")
class MyRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    void testExample() {
        assertNotNull(entityManager);
        User user = new User();
        String username = "holyliao";
        user.setUsername(username);
        user.setPassword(EncryptUtil.md5(username));
        this.entityManager.persist(user);
        Optional<User> target = this.repository.findOneByUsername(username);

        assertTrue(target.isPresent());
        assertEquals(target.get().getUsername(), username);
        assertEquals(target.get().getPassword(), EncryptUtil.md5(username));
    }

}