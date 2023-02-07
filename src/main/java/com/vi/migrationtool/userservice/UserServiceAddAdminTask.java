package com.vi.migrationtool.userservice;

import com.vi.migrationtool.common.MigrationTasks;
import com.vi.migrationtool.config.BeanAwareSpringLiquibase;
import com.vi.migrationtool.keycloak.KeycloakService;
import com.vi.migrationtool.keycloak.KeycloakUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import liquibase.database.Database;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Data
@Slf4j
public class UserServiceAddAdminTask extends MigrationTasks {

  private String roleName;

  private String adminType;

  @Override
  public void execute(Database database) {

    KeycloakService keycloakService = BeanAwareSpringLiquibase.getBean(KeycloakService.class);
    List<KeycloakUser> adminUsersWithRoleName = keycloakService.getUsersWithRoleName(roleName);
    log.info("Found users with role name of size: {}", adminUsersWithRoleName.size());
    JdbcTemplate jdbcTemplate = BeanAwareSpringLiquibase.getBean(JdbcTemplate.class);


    adminUsersWithRoleName.stream().filter(keycloakUser -> doesNotExistInDB(jdbcTemplate, keycloakUser.getId())).forEach(this::createAdminUserInDB);
  }

  private boolean doesNotExistInDB(JdbcTemplate jdbcTemplate, String id) {
    String sql = "SELECT * FROM admin WHERE admin_id = ?";

    var result = jdbcTemplate.query(sql, new Object[]{id},
        new RowMapper<Object>() {
          @Override
          public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getString("user_id");
          }
        });

    return result == null || result.isEmpty();
  }

  private void createAdminUserInDB(KeycloakUser keycloakUser) {
    log.info("Creating admin user in DB: {}", keycloakUser.getId());
    JdbcTemplate jdbcTemplate = BeanAwareSpringLiquibase.getBean(JdbcTemplate.class);
    jdbcTemplate.update(
        "INSERT INTO `admin` (`admin_id`, `tenant_id`, `username`, `first_name`, `last_name`, `email`, `type`, `rc_user_id`, `id_old`, `create_date`, `update_date`) VALUES\n"
            + "(?,?,?,?,?,?,'TENANT',NULL,NULL,'2023-01-16 10:17:37','2023-01-18 08:33:37')",
        keycloakUser.getId(),
        keycloakUser.getTenantId(),
        keycloakUser.getUsername(),
        keycloakUser.getFirstName(),
        keycloakUser.getLastName(),
        keycloakUser.getEmail()
    );
  }

}