package com.example.bookstoreapp.model.mapper;

import com.example.bookstoreapp.model.dto.RoleDto;
import com.example.bookstoreapp.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto convertToRoleDto(Role role);
}