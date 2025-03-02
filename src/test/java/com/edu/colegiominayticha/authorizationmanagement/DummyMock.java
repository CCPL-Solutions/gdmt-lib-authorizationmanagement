package com.edu.colegiominayticha.authorizationmanagement;

import com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.model.FindAllPermissionsResponseDto;
import com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.model.GroupDto;

import java.util.List;
import java.util.UUID;

public class DummyMock {

    public static List<FindAllPermissionsResponseDto> getFindAllPermissionsResponseDtoList(String permissionName) {
        return List.of(
                FindAllPermissionsResponseDto
                        .builder()
                        .identityType("GROUP")
                        .identityId(UUID.randomUUID())
                        .objectType("DOCUMENT_TYPE")
                        .permissionName(permissionName)
                        .build());
    }

    public static List<GroupDto> getGroupDtoList() {
        return List.of(
                GroupDto
                        .builder()
                        .id(UUID.randomUUID())
                        .name("GROUP")
                        .build());
    }

}
