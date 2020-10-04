package kr.taggle.alarmbot.role.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRolesRepository {
    private static final String SERVER_ZONE = "Asia/Seoul";
    private static final String DEFAULT_DATE_PATTERN = "yyyyMMdd";
    private static final String DEFAULT_DATA_SEPARATOR = " ";
    private static final String DEFAULT_ROLE_DATA_CLASS_PATH = "classpath:role.txt";

    private final ApplicationContext applicationContext;
    private final Map<LocalDate, MemberRoles> roleTables;

    public MemberRolesRepository(ApplicationContext applicationContext) throws IOException {
        this.applicationContext = applicationContext;
        this.roleTables = loadRoleData();
    }

    private Map<LocalDate, MemberRoles> loadRoleData() throws IOException {
        final Resource resource = applicationContext.getResource(DEFAULT_ROLE_DATA_CLASS_PATH);
        final InputStream inputStream = resource.getInputStream();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufReader = new BufferedReader(inputStreamReader);
        return bufReader.lines().collect(Collectors.toMap(this::parseDateForKey, this::parseRolesForValue));
    }

    private MemberRoles parseRolesForValue(String data) {
        final String[] datas = data.split(DEFAULT_DATA_SEPARATOR);
        return MemberRoles.builder()
                .teamLeader(datas[1])
                .scrumMaster(datas[2])
                .writer(datas[3])
                .locationBorrower(datas[4])
                .calenderManager(datas[5])
                .build();
    }

    private LocalDate parseDateForKey(String data) {
        return LocalDate.parse(data.split(DEFAULT_DATA_SEPARATOR)[0],
                DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));
    }

    public MemberRoles findRoleByCurrentDay() {
        return roleTables.get(LocalDate.now(ZoneId.of(SERVER_ZONE)));
    }
}
