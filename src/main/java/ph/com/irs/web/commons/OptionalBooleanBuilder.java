package ph.com.irs.web.commons;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.Collection;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * Created by julius on 12/09/2018.
 */
public class OptionalBooleanBuilder {

  private BooleanBuilder predicate;

  public OptionalBooleanBuilder() {
    this.predicate = new BooleanBuilder();
  }

  public OptionalBooleanBuilder(BooleanBuilder predicate) {
    this.predicate = predicate;
  }

  public <T> OptionalBooleanBuilder notNullAnd(Function<T, BooleanExpression> expressionFunction,
      T value) {
    if (value != null) {
      return new OptionalBooleanBuilder(predicate.and(expressionFunction.apply(value)));
    }
    return this;
  }

  public OptionalBooleanBuilder notEmptyAnd(Function<String, BooleanExpression> expressionFunction,
      String value) {
    if (!StringUtils.isEmpty(value)) {
      return new OptionalBooleanBuilder(predicate.and(expressionFunction.apply(value)));
    }
    return this;
  }

  public <T> OptionalBooleanBuilder notEmptyAnd(
      Function<Collection<T>, BooleanExpression> expressionFunction, Collection<T> collection) {
    if (!CollectionUtils.isEmpty(collection)) {
      return new OptionalBooleanBuilder(predicate.and(expressionFunction.apply(collection)));
    }
    return this;
  }

  public BooleanBuilder build() {
    return predicate;
  }
}
