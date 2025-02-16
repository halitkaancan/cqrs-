package com.hkc.cqrs.application.auth.login;

import an.awesome.pipelinr.Command;
import com.hkc.cqrs.core.pipelines.jwt.JwtService;
import com.hkc.cqrs.domain.entity.User;
import com.hkc.cqrs.persistence.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class LoggingCommand implements Command<LoginCommandResponse> {

    private String email;
    private String password;

    @Component
    @RequiredArgsConstructor
    public static class LogingCommandHandler implements Command.Handler<LoggingCommand,LoginCommandResponse>{
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;

        @Override
        public LoginCommandResponse handle(LoggingCommand loggingCommand) {

            User user = userRepository.findByEmail(loggingCommand.getEmail()).orElseThrow(()->new RuntimeException("Bad Credentials"));

            boolean isPasswordCorrect =  passwordEncoder.matches(loggingCommand.getPassword(),user.getPassword());

            if(!isPasswordCorrect){
                throw new RuntimeException("Bad Credentials");
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            String jwt = jwtService.generateToken(user.getEmail(), claims);
            return new LoginCommandResponse(jwt);
        }
    }
}
