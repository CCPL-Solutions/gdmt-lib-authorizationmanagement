package com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg;

import com.edu.colegiominayticha.authorizationmanagement.config.MsIdentityManagementPropertiesConfig;
import com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.model.FindAllPermissionsResponseDto;
import com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.model.GroupDto;
import com.edu.colegiominayticha.gdmtlibcommons.exception.classification.technical.CommunicationException;
import com.edu.colegiominayticha.gdmtlibcommons.service.GdmtRestTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MsIdentityManagementNegImplTest {

    @Mock
    private MsIdentityManagementPropertiesConfig msIdentityManagementProperties;
    @Mock
    private GdmtRestTemplate gdmtRestTemplate;
    @InjectMocks
    private MsIdentityManagementNegImpl client;

    @Test
    void findAllGroupsShouldReturnListOfGroups() {
        var username = "pedro.chavez";
        var url = "http://localhost:8080/api/v1/groups";
        var url2 = "http://localhost:8080/api/v1/groups?username=pedro.chavez";
        var responseEntity = ResponseEntity.ok(new GroupDto[]{new GroupDto()});

        when(msIdentityManagementProperties.getFindAllGroups()).thenReturn(url);
        when(gdmtRestTemplate.getForEntity(url2, GroupDto[].class)).thenReturn(responseEntity);
        List<GroupDto> response = client.findAllGroups(username);

        assertNotNull(response);
    }

    @Test
    void findAllGroupsShouldThrowCommunicationException() {
        var username = "pedro.chavez";
        var url = "http://localhost:8080/api/v1/groups";
        var url2 = "http://localhost:8080/api/v1/groups?username=pedro.chavez";
        var responseEntity = ResponseEntity.ok(new GroupDto[]{new GroupDto()});

        when(msIdentityManagementProperties.getFindAllGroups()).thenReturn(url);
        when(gdmtRestTemplate.getForEntity(url2, GroupDto[].class)).thenThrow(HttpClientErrorException.class);
        var exception = assertThrows(CommunicationException.class, () -> client.findAllGroups(username));

        assertNotNull(exception);
    }

    @Test
    void findAllPermissionsShouldReturnListOfPermissions() {
        var identityType = "user";
        var identityId = UUID.randomUUID();
        var objectType = "document";
        var objectId = UUID.randomUUID();
        var url = "http://localhost:8080/api/v1/permissions";
        var urlWithParams = String.format("%s?identityType=%s&identityId=%s&objectType=%s&objectId=%s",
                url, identityType, identityId, objectType, objectId);
        var responseEntity = ResponseEntity.ok(new FindAllPermissionsResponseDto[]{new FindAllPermissionsResponseDto()});

        when(msIdentityManagementProperties.getFindAllPermissions()).thenReturn(url);
        when(gdmtRestTemplate.getForEntity(urlWithParams, FindAllPermissionsResponseDto[].class)).thenReturn(responseEntity);
        List<FindAllPermissionsResponseDto> response = client.findAllPermissions(identityType, identityId, objectType, objectId);

        assertNotNull(response);
    }

    @Test
    void findAllPermissionsShouldThrowCommunicationException() {
        var identityType = "user";
        var identityId = UUID.randomUUID();
        var objectType = "document";
        var objectId = UUID.randomUUID();
        var url = "http://localhost:8080/api/v1/permissions";
        var urlWithParams = String.format("%s?identityType=%s&identityId=%s&objectType=%s&objectId=%s",
                url, identityType, identityId, objectType, objectId);

        when(msIdentityManagementProperties.getFindAllPermissions()).thenReturn(url);
        when(gdmtRestTemplate.getForEntity(urlWithParams, FindAllPermissionsResponseDto[].class)).thenThrow(HttpClientErrorException.class);
        var exception = assertThrows(CommunicationException.class, () -> client.findAllPermissions(identityType, identityId, objectType, objectId));

        assertNotNull(exception);
    }
}