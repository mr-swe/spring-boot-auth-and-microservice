package com.example.family_care.application.service.constract;

import com.example.family_care.domain.entity.UserProfile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mahfuzur Rahman
 * @Date 8/17/2024
 */

public interface IAuthentication {

    String login(UserProfile request);

}
