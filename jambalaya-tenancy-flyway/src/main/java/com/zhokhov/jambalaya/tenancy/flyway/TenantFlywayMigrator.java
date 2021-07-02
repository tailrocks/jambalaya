package com.zhokhov.jambalaya.tenancy.flyway;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.flywaydb.core.internal.jdbc.DriverDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.zhokhov.jambalaya.checks.Preconditions.checkNotBlank;

/**
 * @author Alexey Zhokhov
 */
public class TenantFlywayMigrator {

    private static final Logger LOG = LoggerFactory.getLogger(TenantFlywayMigrator.class);

    private final String url;
    private final String username;
    private final String password;

    public TenantFlywayMigrator(String url, String username, String password) {
        // TODO temp solution, remove after add otel support to Flyway
        this.url = url.replace("jdbc:otel:", "jdbc:");
        this.username = username;
        this.password = password;
    }

    // TODO validate schema name
    public synchronized void migrateSchema(@NonNull String schemaName) {
        checkNotBlank(schemaName, "schemaName");

        LOG.info("Migrating schema: {}", schemaName);

        DataSource dataSource = new DriverDataSource(
                Thread.currentThread().getContextClassLoader(),
                null,
                url,
                username,
                password
        );

        FluentConfiguration fluentConfiguration = new FluentConfiguration()
                .dataSource(dataSource)
                .defaultSchema(schemaName)
                .schemas(schemaName);

        Flyway flyway = fluentConfiguration.load();
        flyway.migrate();

        LOG.info("Successfully migrated schema: {}", schemaName);
    }

    // TODO validate schema name
    public synchronized void dropSchema(@NonNull String schemaName) throws SQLException {
        checkNotBlank(schemaName, "schemaName");

        LOG.info("Dropping schema: {}", schemaName);

        DataSource dataSource = new DriverDataSource(
                Thread.currentThread().getContextClassLoader(),
                null,
                url,
                username,
                password
        );

        try (Connection connection = dataSource.getConnection()) {
            // TODO SQL injection here, validate schema name!
            String sql = "DROP SCHEMA " + schemaName + " CASCADE;";

            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);

                LOG.info("Successfully dropped schema: {}", schemaName);
            }
        }
    }

}
