package com.hkc.cqrs.core.pipelines.auth;

import java.util.List;

public interface AuthorizedRequest {
    List<String> getRequiredRoles();
}
