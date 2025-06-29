# Library gdmt-lib-authorizationmanagement

## 1. Introduction

### 1.1 Overview

The gdmt-lib-authorizationmanagement library provides a set of utilities for validating user permissions within  
groups for managing users, groups, document types, document areas, folders, and documents.

### 1.2 Features

- Validation of a user's permissions, based on their group membership, over various objects, including users, groups,
  document types, and document areas.
- Validation of a user's permissions, based on their group membership, over specific objects such as folders and
  documents within a designated document area.

## 2. Getting Started

### 2.1 Installation

Add the following dependency to your pom.xml file:

```xml

<dependency>
    <groupId>com.edu.colegiominayticha</groupId>
    <artifactId>gdmt-lib-authorizationmanagement</artifactId>
    <version>${version}</version>
</dependency>
```

### 2.2 Configuration Properties

The library requires certain properties to be defined in your application's configuration file (application.yml or
application.properties):

```yml
application:
  permissionsSettings:
    enabled: true

data-provider:
  rest:
    gdmt-ms-identitymanagement-neg:
      baseUrl: http://{host}/gdmt-ms-identitymanagement-neg/api/{version}
      operations:
        findAllGroups: ${data-provider.rest.gdmt-ms-identitymanagement-neg.baseUrl}/groups
        findAllPermissions: ${data-provider.rest.gdmt-ms-identitymanagement-neg.baseUrl}/permissions
```

### 2.3 Usage

#### Injecting the AuthorizationManagerService

Inject the AuthorizationManagerService into your class where permissions need to be checked:

```java
import org.springframework.beans.factory.annotation.Autowired;

public class YourService {
    @Autowired
    private AuthorizationManagerService authorizationManagement;

    public void checkPermission() {
        if (appProperties.getPermissionsSettings().isEnabled()) {
            authorizationManagement.checkPermission("<objectType>", "<action>");
        }
    }

    public void checkPermission() {
        if (appProperties.getPermissionsSettings().isEnabled()) {
            authorizationManagement.checkPermission("<objectType>", "<objectId>", "<action>");
        }
    }
}
```

**Note:**  
Replace `<objectType>` with the object type for which the permission is being checked.  
Replace `action` with the action for which the permission is being checked.
Replace `<objectId>` with the object id for which the permission is being checked.

## 3. Changelog

### 1.0.0 - 29-06-2025

- **Initial release:** Support for checking group permissions.

## Copyright

Copyright (c) CCPL Solutions.
