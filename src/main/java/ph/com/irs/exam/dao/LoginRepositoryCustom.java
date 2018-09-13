package ph.com.irs.exam.dao;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.Map;

/**
 * Created by julius on 13/09/2018.
 */
public interface LoginRepositoryCustom {

  Map<String, Long> doItRight(Predicate predicate);

}
