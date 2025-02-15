package com.hkc.cqrs.core.pipelines.auth;

import an.awesome.pipelinr.Command;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class AuthenticationBehavior implements Command.Middleware{

    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {
        System.out.println("Authentication behavior invoked");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(c instanceof AuthenticatedRequest || c instanceof AuthorizedRequest){
            if(auth == null || auth.isAuthenticated()){
                throw new RuntimeException("Authentication required");
            }
        }
        if(c instanceof AuthenticatedRequest){

            boolean hasRequiredRoles = auth
                    .getAuthorities()
                    .stream()
                    .anyMatch(
                            role -> ((AuthorizedRequest) c)
                                    .getRequiredRoles()
                                    .stream()
                                    .anyMatch(req->req.equalsIgnoreCase(role.getAuthority()))
                    );

            if(!hasRequiredRoles){
                throw new RuntimeException("Authentication required");
            }

        }


        return next.invoke();
    }
}
