package com.vi.migrationtool.keycloak;

import com.vi.migrationtool.common.MigrationTasks;
import com.vi.migrationtool.config.BeanAwareSpringLiquibase;
import java.util.List;
import liquibase.database.Database;
import lombok.Data;

@Data
public class KeycloakAddCustomAttributeToUsersWithRoleTask extends MigrationTasks {
  private String customAttribute;

  private Long value;

  private List<String> roleNames;

  @Override
  public void execute(Database database) {
    KeycloakService keycloakService = BeanAwareSpringLiquibase.getBean(KeycloakService.class);
    keycloakService.addCustomAttributeToUsersWithRole(customAttribute, value, roleNames);
  }
}
