package com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg;

import com.edu.colegiominayticha.authorizationmanagement.config.MsIdentityManagementPropertiesConfig;
import com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.models.FindAllPermissionsResponseDto;
import com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.models.GroupDto;
import com.edu.colegiominayticha.gdmtlibcommons.exception.classification.technical.CommunicationException;
import com.edu.colegiominayticha.gdmtlibcommons.service.GdmtRestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class MsIdentityManagementNegImpl implements MsIdentityManagementNeg {

    private static final String IDENTITY_TYPE = "identityType";
    private static final String IDENTITY_ID = "identityId";
    private static final String OBJECT_TYPE = "objectType";
    private static final String OBJECT_ID = "objectId";

    private final MsIdentityManagementPropertiesConfig msIdentityManagementProperties;
    private final GdmtRestTemplate gdmtRestTemplate;

    @Override
    public List<GroupDto> findAllGroups(String username) {
        log.info("[GDMT_START] [findAllGroups] Calling  MsIdentityManagementNegImpl.findAllGroups");

        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(
                            msIdentityManagementProperties.getFindAllGroups())
                    .queryParam("username", username);

            var responseEntity = gdmtRestTemplate.getForEntity(uriBuilder.toUriString(), GroupDto[].class);
            var response = List.of(Objects.requireNonNull(responseEntity.getBody()));

            log.info("[GDMT_END_OK] [findAllGroups] Response [{}]", response);
            return response;
        } catch (Exception e) {
            log.error("[GDMT_END_EX] [findAllGroups] Exception message [{}]", e.getMessage());
            throw new CommunicationException(e.getMessage());
        }
    }

    @Override
    public List<FindAllPermissionsResponseDto> findAllPermissions(
            String identityType, UUID identityId, String objectType, UUID objectId) {
        log.info("[GDMT_START] [findAllPermissions] Calling  MsIdentityManagementNegImpl.findAllPermissions");

        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(
                            msIdentityManagementProperties.getFindAllPermissions())
                    .queryParam(IDENTITY_TYPE, identityType)
                    .queryParam(IDENTITY_ID, identityId)
                    .queryParam(OBJECT_TYPE, objectType)
                    .queryParam(OBJECT_ID, objectId);

            var responseEntity = gdmtRestTemplate.getForEntity(uriBuilder.toUriString(), FindAllPermissionsResponseDto[].class);
            var response = List.of(Objects.requireNonNull(responseEntity.getBody()));

            log.info("[GDMT_END_OK] [findAllPermissions] Response [{}]", response);
            return response;
        } catch (Exception e) {
            log.error("[GDMT_END_EX] [findAllPermissions] Exception message [{}]", e.getMessage());
            throw new CommunicationException(e.getMessage());
        }
    }
}
