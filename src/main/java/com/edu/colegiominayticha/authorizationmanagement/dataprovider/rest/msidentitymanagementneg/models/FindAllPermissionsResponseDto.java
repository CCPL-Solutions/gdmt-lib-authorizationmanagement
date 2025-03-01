package com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindAllPermissionsResponseDto {

    private String identityType;
    private UUID identityId;
    private String objectType;
    private UUID objectId;
    private String permissionName;

}

