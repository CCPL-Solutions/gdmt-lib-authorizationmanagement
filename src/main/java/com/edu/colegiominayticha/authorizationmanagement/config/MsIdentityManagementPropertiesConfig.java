package com.edu.colegiominayticha.authorizationmanagement.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("data-provider.rest.gdmt-ms-identitymanagement-neg.operations")
public class MsIdentityManagementPropertiesConfig {

    private String findAllGroups;
    private String findAllPermissions;

}