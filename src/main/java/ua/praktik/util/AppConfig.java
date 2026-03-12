package ua.praktik.util;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(basePackages = "ua.praktik")
@PropertySource("classpath:database.properties")
public class AppConfig {

  @Autowired Environment env;

  // private static String url = "jdbc:mysql://localhost:3306/notiondb";
  // private static String user = "notion";
  // private static String pass = "255455";

  @Bean
  public DataSource dataSource(Environment env) {
    HikariDataSource ds = new HikariDataSource();
    ds.setDriverClassName(env.getProperty("driver"));
    ds.setJdbcUrl(env.getProperty("url"));
    ds.setUsername(env.getProperty("user"));
    ds.setPassword(env.getProperty("pass"));
    return ds;
  }

  @Bean
  public JdbcTemplate JdbcTemplate(DataSource ds) {
    return new JdbcTemplate(ds);
  }
}
