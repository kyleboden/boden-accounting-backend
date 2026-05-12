package kyle.practice.boden_accounting_backend.repository;

import kyle.practice.boden_accounting_backend.entity.BrokerageTotal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerageTotalRepository extends JpaRepository<BrokerageTotal, Long> {
}
