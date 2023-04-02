package org.codej.restAPi.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.codej.restAPi.board.entity.Role;

import java.util.List;

@Mapper
public interface RoleMapper {
    void save(List<Role> roleType);
    List<String> findAll();

}
