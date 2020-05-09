package pl.sgnit.charity;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("pl.sgnit.charity")
@EnableJpaRepositories(basePackages = "pl.sgnit.charity.repository")
@EnableTransactionManagement
public class AppConfig {
}
