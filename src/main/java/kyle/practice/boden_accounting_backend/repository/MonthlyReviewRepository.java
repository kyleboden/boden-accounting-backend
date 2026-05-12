package kyle.practice.boden_accounting_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kyle.practice.boden_accounting_backend.entity.MonthlyReview;

public interface MonthlyReviewRepository extends JpaRepository<MonthlyReview, Long> {
}
