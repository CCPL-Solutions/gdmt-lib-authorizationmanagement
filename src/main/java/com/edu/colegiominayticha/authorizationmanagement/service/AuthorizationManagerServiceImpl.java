package com.edu.colegiominayticha.authorizationmanagement.service;

import com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.MsIdentityManagementNeg;
import com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.model.GroupDto;
import com.edu.colegiominayticha.gdmtlibcommons.exception.classification.security.ForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.edu.colegiominayticha.gdmtlibcommons.crosscutting.constants.HeadersConstants.X_USER_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationManagerServiceImpl implements AuthorizationManagerService {

    private static final String IDENTITY_TYPE_GROUP = "GROUP";
    private final MsIdentityManagementNeg msIdentityManagementNeg;

    @Override
    public void checkPermission(String objectType, String action) {
        check(objectType, null, action);
    }

    @Override
    public void checkPermission(String objectType, UUID objectId, String action) {
        check(objectType, objectId, action);
    }

    private void check(String objectType, UUID objectId, String action) {

        var groups = getGroups();
        var hasPermission = false;

        for (var group : groups) {
            var permissions = msIdentityManagementNeg.findAllPermissions(
                    IDENTITY_TYPE_GROUP, group.getId(), objectType, objectId);

            for (var permission : permissions) {
                if (permission.getPermissionName().equals(action)) {
                    hasPermission = true;
                    break;
                }
            }
        }

        if (!hasPermission) {
            throw new ForbiddenException("User does not have permission to perform this action");
        }
    }

    private List<GroupDto> getGroups() {
        var username = getUsername();
        return msIdentityManagementNeg.findAllGroups(username);
    }

    private static String getUsername() {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) Objects.requireNonNull(requestAttributes)).getRequest().getHeader(X_USER_ID);
    }

}
