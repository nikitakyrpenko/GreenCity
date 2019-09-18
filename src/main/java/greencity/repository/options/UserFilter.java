package greencity.repository.options;

import greencity.dto.filter.UserFilterDto;
import greencity.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * Find id by email.
 *
 * @return User id
 */
public class UserFilter implements Specification<User> {
    private UserFilterDto userFilterDto;

    /**
     * Find id by email.
     */
    public UserFilter(UserFilterDto userFilterDto) {
        this.userFilterDto = userFilterDto;
    }

    /**
     * Find id by email.
     *
     * @return User id
     */
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(hasFieldsLike(root, criteriaBuilder, userFilterDto.getSearchReg()));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    /**
     * Find id by email.
     *
     * @return User id
     */
    private Predicate hasFieldsLike(Root<User> r, CriteriaBuilder cb, String reg) {
        return cb.or(
            cb.like(r.get("firstName"), reg),
            cb.like(r.get("lastName"), reg),
            cb.like(r.get("email"), reg),
            cb.like(r.get("dateOfRegistration").as(String.class), reg)
        );
    }
}
