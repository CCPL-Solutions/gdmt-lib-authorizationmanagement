package com.edu.colegiominayticha.authorizationmanagement.service;

import com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.MsIdentityManagementNeg;
import com.edu.colegiominayticha.gdmtlibcommons.exception.classification.security.ForbiddenException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static com.edu.colegiominayticha.authorizationmanagement.DummyMock.getFindAllPermissionsResponseDtoList;
import static com.edu.colegiominayticha.authorizationmanagement.DummyMock.getGroupDtoList;
import static com.edu.colegiominayticha.gdmtlibcommons.crosscutting.constants.HeadersConstants.X_USER_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationManagerServiceImplTest {

    @Mock
    private MsIdentityManagementNeg msIdentityManagementNeg;
    @Mock
    private HttpServletRequest request;
    @Mock
    private ServletRequestAttributes attributes;

    @InjectMocks
    private AuthorizationManagerServiceImpl service;

    @Test
    void checkPermission() {
        // Given
        String objectType = "DOCUMENT_TYPE";
        String action = "CREATE_DOCUMENT_TYPE";
        var findAllPermissionsResponseDtoList = getFindAllPermissionsResponseDtoList("CREATE_DOCUMENT_TYPE");
        var groupList = getGroupDtoList();

        // When
        when(msIdentityManagementNeg.findAllGroups(anyString())).thenReturn(groupList);
        when(msIdentityManagementNeg.findAllPermissions(anyString(), any(UUID.class), anyString(), any())).thenReturn(findAllPermissionsResponseDtoList);

        try (MockedStatic<RequestContextHolder> mockedContextHolder = mockStatic(RequestContextHolder.class)) {
            when(request.getHeader(X_USER_ID)).thenReturn("testUser");
            when(attributes.getRequest()).thenReturn(request);
            mockedContextHolder.when(RequestContextHolder::getRequestAttributes).thenReturn(attributes);

            service.checkPermission(objectType, action);
        }

    }

    @Test
    void checkPermission2() {
        // Given
        String objectType = "DOCUMENT_TYPE";
        UUID objectId = UUID.randomUUID();
        String action = "CREATE_DOCUMENT_TYPE";
        var findAllPermissionsResponseDtoList = getFindAllPermissionsResponseDtoList("CREATE_DOCUMENT_TYPE");
        var groupList = getGroupDtoList();

        // When
        when(msIdentityManagementNeg.findAllGroups(anyString())).thenReturn(groupList);
        when(msIdentityManagementNeg.findAllPermissions(anyString(), any(UUID.class), anyString(), any())).thenReturn(findAllPermissionsResponseDtoList);

        try (MockedStatic<RequestContextHolder> mockedContextHolder = mockStatic(RequestContextHolder.class)) {
            when(request.getHeader(X_USER_ID)).thenReturn("testUser");
            when(attributes.getRequest()).thenReturn(request);
            mockedContextHolder.when(RequestContextHolder::getRequestAttributes).thenReturn(attributes);

            service.checkPermission(objectType, objectId, action);
        }

    }

    @Test
    void checkPermissionForbiddenException() {
        // Given
        String objectType = "DOCUMENT_TYPE";
        String action = "CREATE_DOCUMENT_TYPE";
        var findAllPermissionsResponseDtoList = getFindAllPermissionsResponseDtoList("READ_DOCUMENT_TYPE");
        var groupList = getGroupDtoList();

        // When
        when(msIdentityManagementNeg.findAllGroups(anyString())).thenReturn(groupList);
        when(msIdentityManagementNeg.findAllPermissions(anyString(), any(UUID.class), anyString(), any())).thenReturn(findAllPermissionsResponseDtoList);

        try (MockedStatic<RequestContextHolder> mockedContextHolder = mockStatic(RequestContextHolder.class)) {
            when(request.getHeader(X_USER_ID)).thenReturn("testUser");
            when(attributes.getRequest()).thenReturn(request);
            mockedContextHolder.when(RequestContextHolder::getRequestAttributes).thenReturn(attributes);

            assertThrows(ForbiddenException.class, () -> service.checkPermission(objectType, action));
        }

    }

}