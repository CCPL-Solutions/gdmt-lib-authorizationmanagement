package com.edu.colegiominayticha.authorizationmanagement.dataprovider.rest.msidentitymanagementneg.model;

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
public class GroupDto {

    private UUID id;
    private String name;

}
