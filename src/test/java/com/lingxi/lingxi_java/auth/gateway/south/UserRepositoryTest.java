package com.lingxi.lingxi_java.auth.gateway.south;

import com.lingxi.lingxi_java.auth.domain.UserRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;
    @Resource
    private EntityManager entityManager;

    @Test
//    @Transactional
    void testCreatedAtIsNotNullWhenModify() {
//        AuthenticationUtil.setUserId(JwtUtil.generateByUserId(1L));
//        UserDO newUser = new UserDO();
//        newUser.setId(1L);
//        newUser.setNickName("灵希3");
//        newUser.setPassword("test");
//        newUser.setAvatar("./logo.png");
//        newUser.setUsername("username");
//        userRepository.save(newUser);
    }

}