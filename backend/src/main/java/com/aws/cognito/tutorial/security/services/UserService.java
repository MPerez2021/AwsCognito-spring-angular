package com.aws.cognito.tutorial.security.services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.aws.cognito.tutorial.security.dtos.SignInDto;
import com.aws.cognito.tutorial.security.dtos.SignUpDto;
import com.aws.cognito.tutorial.security.entities.User;
import com.aws.cognito.tutorial.security.enums.RoleName;
import com.aws.cognito.tutorial.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;
    private AWSCognitoIdentityProvider awsCognitoIdentityProvider;

    @Value(value = "${aws.cognito.clientId}")
    private String clientId;

    public UserService(AWSCognitoIdentityProvider awsCognitoIdentityProvider) {
        this.awsCognitoIdentityProvider = awsCognitoIdentityProvider;
    }

    public void createUser(User user){
        userRepository.save(user);
    }

    public void signUpUser(SignUpDto signUpDto){
        AttributeType attributeType = new AttributeType().withName("email").withValue(signUpDto.getEmail());
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.withClientId(clientId)
                .withPassword(signUpDto.getPassword())
                .withUsername(signUpDto.getEmail())
                .withUserAttributes(attributeType);
        awsCognitoIdentityProvider.signUp(signUpRequest);

        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setName(signUpDto.getName());
        user.setLastName(signUpDto.getLastName());
        user.setRole(roleService.getRoleByRoleName(RoleName.ROLE_ADMIN).get());
        createUser(user);
    }

    public String signInUser(SignInDto signInDto){
        final Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", signInDto.getEmail());
        authParams.put("PASSWORD", signInDto.getPassword());

        InitiateAuthRequest initiateAuthRequest = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .withClientId(clientId)
                .withAuthParameters(authParams);

        InitiateAuthResult initiateAuthResult = awsCognitoIdentityProvider.initiateAuth(initiateAuthRequest);
        return initiateAuthResult.getAuthenticationResult().getAccessToken();
    }

    public Optional<User> getUserByEmail(String userEmail){
        return userRepository.findByEmail(userEmail);
    }
}
