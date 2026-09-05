package kyle.practice.boden_accounting_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kyle.practice.boden_accounting_backend.entity.MonthlyReview;

import java.util.List;
import java.util.Optional;

public interface MonthlyReviewRepository extends JpaRepository<MonthlyReview, Long> {
    List<MonthlyReview> findAllByOwnerUserId(String ownerUserId);

    Optional<MonthlyReview> findByIdAndOwnerUserId(Long id, String ownerUserId);
}
