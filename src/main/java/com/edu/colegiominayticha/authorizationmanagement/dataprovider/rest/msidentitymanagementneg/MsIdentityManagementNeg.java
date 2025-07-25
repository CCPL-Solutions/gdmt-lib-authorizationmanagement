package com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg;

import com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.model.FindAllPermissionsResponseDto;
import com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.model.GroupDto;

import java.util.List;
import java.util.UUID;

public interface MsIdentityManagementNeg {

    List<GroupDto> findAllGroups(String username);

    List<FindAllPermissionsResponseDto> findAllPermissions(
            String identityType, UUID identityId, String objectType, UUID objectId);

}
