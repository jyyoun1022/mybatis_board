package org.codej.restAPi.board.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codej.restAPi.board.entity.Role;
import org.codej.restAPi.board.entity.RoleType;
import org.codej.restAPi.board.mapper.RoleMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Log4j2
@Profile("local") // 이클래스는 profile 이 local 일 때만 빈으로 등록
@Transactional
public class InitDB {

    private final RoleMapper roleMapper;

    @PostConstruct // 빈의 생성과 의존성 주입이 끝난 뒤에 수행할 초기화 코드
    public void initDB() {
        log.info("Initialize Database");
        initRole();

    }

    private void initRole() {
        /**
         * 권한 있는거 찾고 놔두고 추가된다면 save
         */
        List<String> DBList = roleMapper.findAll();
        List<String> EnumList = Arrays.stream(RoleType.values()).map(i -> i.getValue()).collect(Collectors.toList());
        boolean flag = DBList.size() == EnumList.size();
        EnumList.removeAll(DBList);
        if (!flag || DBList.isEmpty()) {
//            db에는 없고 칼럼에만 있을 때
            EnumList.removeAll(DBList);
            EnumList.stream().forEach(i -> log.info(!flag == true ? "ROLE 타입 추가됨 :::" +i : ""));
            List<Role> roleTyeList = EnumList.stream().map(i -> new Role(i)).collect(Collectors.toList());

            roleMapper.save(roleTyeList);
        }
    }
}
