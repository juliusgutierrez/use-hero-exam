package ph.com.irs.web.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ph.com.irs.web.ExamApplication;

/**
 * Created by julius on 13/09/2018.
 */

@Configuration
@EnableJpaRepositories(basePackageClasses = ExamApplication.class)
public class JpaConfig {

  @PersistenceContext
  private EntityManager entityManager;

  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(entityManager);
  }

}
