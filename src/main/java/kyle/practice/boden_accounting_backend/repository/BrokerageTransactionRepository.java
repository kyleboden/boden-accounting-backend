package kyle.practice.boden_accounting_backend.repository;

import kyle.practice.boden_accounting_backend.entity.BrokerageTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrokerageTransactionRepository extends JpaRepository<BrokerageTransaction, Long> {
    List<BrokerageTransaction> findAllByOwnerUserId(String ownerUserId);

    Optional<BrokerageTransaction> findByIdAndOwnerUserId(Long id, String ownerUserId);
}
