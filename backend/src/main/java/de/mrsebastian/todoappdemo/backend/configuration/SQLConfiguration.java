package de.mrsebastian.todoappdemo.backend.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Profile(Profiles.PROFILE_SQL)
@EntityScan(
        basePackages = {
                "org.springframework.data.jpa.convert.threeten",
                "de.mrsebastian.todoappdemo.backend.person.dataaccess.entity",
                "de.mrsebastian.todoappdemo.backend.task.dataaccess.entity"
        }
)
@EnableJpaRepositories(
        basePackages = {
                "de.mrsebastian.todoappdemo.backend.person.dataaccess.entity",
                "de.mrsebastian.todoappdemo.backend.task.dataaccess.entity"
        }
)
public class SQLConfiguration {

}
