package kyle.practice.boden_accounting_backend.repository;

import kyle.practice.boden_accounting_backend.entity.BrokerageTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerageTransactionRepository extends JpaRepository<BrokerageTransaction, Long> {
}
