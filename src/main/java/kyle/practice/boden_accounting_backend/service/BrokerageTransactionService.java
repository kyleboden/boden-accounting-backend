package kyle.practice.boden_accounting_backend.service;

import kyle.practice.boden_accounting_backend.dto.BrokerageTransactionDto;

import java.util.List;

public interface BrokerageTransactionService {

    BrokerageTransactionDto createBrokerageTransaction(BrokerageTransactionDto brokerageTransactionDto);

    BrokerageTransactionDto getBrokerageTransactionById(Long transactionId);

    List<BrokerageTransactionDto> getAllBrokerageTransactions();

    BrokerageTransactionDto updateBrokerageTransaction(Long transactionId, BrokerageTransactionDto updatedTransaction);

    void deleteBrokerageTransaction(Long transactionId);
}
