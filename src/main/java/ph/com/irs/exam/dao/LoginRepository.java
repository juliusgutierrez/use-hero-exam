package ph.com.irs.exam.dao;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ph.com.irs.exam.model.Login;

/**
 * Created by julius on 10/09/2018.
 */
@Repository
public interface LoginRepository extends CrudRepository<Login, Long>,
    LoginRepositoryCustom,
    PagingAndSortingRepository<Login, Long>,
    QuerydslPredicateExecutor<Login> {

  @Query(value = "SELECT DATE_FORMAT(DATE(login_time), '%Y%m%d') logintime "
      + "FROM login GROUP BY logintime ORDER BY logintime ASC",
      nativeQuery = true)
  List<Date> findAllUniqueDates();

}
