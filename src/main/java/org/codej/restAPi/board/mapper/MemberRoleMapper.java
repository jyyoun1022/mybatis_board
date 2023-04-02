package org.codej.restAPi.board.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRoleMapper {

    void save(int memberId,int RoleId);

}
