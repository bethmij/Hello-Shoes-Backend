package lk.ijse.gdse66.HelloShoes.service.impl;


import lk.ijse.gdse66.HelloShoes.auth.request.SignInRequest;
import lk.ijse.gdse66.HelloShoes.auth.request.SignUpRequest;
import lk.ijse.gdse66.HelloShoes.auth.response.JwtAuthResponse;
import lk.ijse.gdse66.HelloShoes.dto.UserDTO;
import lk.ijse.gdse66.HelloShoes.entity.User;
import lk.ijse.gdse66.HelloShoes.repository.UserRepo;
import lk.ijse.gdse66.HelloShoes.service.AuthenticationService;
import lk.ijse.gdse66.HelloShoes.service.JwtService;
import lk.ijse.gdse66.HelloShoes.service.util.enums.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final ModelMapper mapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        User user = userRepo.findByEmployee_Email(signInRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
            String generatedToken = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JwtAuthResponse signUp(SignUpRequest signUpRequest) {
        UserDTO userDTO = UserDTO.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.valueOf(signUpRequest.getRole()))
                .build();
        User savedUser = userRepo.save(mapper.map(userDTO, User.class));
        String generatedToken = jwtService.generateToken(savedUser);
        return JwtAuthResponse.builder().token(generatedToken).build();
    }

}
    /*User user = userRepo.findByEmail(signInRequest.getEmail())
            .map(userCheck -> {
                boolean matches = passwordEncoder.matches(signInRequest.getPassword(), userCheck.getPassword());
                if (!matches) {
                    new PassordNotFoundException("user not found")
                    System.out.println("Password matches!");
                } else {
                    System.out.println("Password does not match!");
                }

            })
            .orElseThrow(() -> new UsernameNotFoundException("user not found"));*/
