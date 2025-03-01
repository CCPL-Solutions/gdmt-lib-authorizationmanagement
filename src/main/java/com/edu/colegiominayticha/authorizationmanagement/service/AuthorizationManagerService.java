package com.edu.colegiominayticha.authorizationmanagement.service;

import java.util.UUID;

public interface AuthorizationManagerService {

    void checkPermission(String objectType, String action);

    void checkPermission(String objectType, UUID objectId, String action);

}
