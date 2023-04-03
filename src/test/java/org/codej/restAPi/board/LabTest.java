package org.codej.restAPi.board;

import org.codej.restAPi.board.entity.Role;
import org.codej.restAPi.board.entity.RoleType;
import org.codej.restAPi.board.mapper.RoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class LabTest {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    void test(){
        List<String> list = roleMapper.findAll();
        list.stream().forEach(i -> System.out.println(i));
        List<String> roleList = Arrays.stream(RoleType.values()).map(i -> i.getValue()).collect(Collectors.toList());
        System.out.println("==");
        roleList.stream().forEach(i -> System.out.println(i));

        boolean flag = roleList.containsAll(list);
        System.out.println(flag);
        if (!flag || list.isEmpty()) {
//            db에는 없고 칼럼에만 있을 때
            roleList.removeAll(list);
            roleList.stream().forEach(i -> System.out.println(i));
            List<Role> roleTyeList = roleList.stream().map(i -> new Role(i)).collect(Collectors.toList());

            roleMapper.save(roleTyeList);
        }

    }
}
