package kyle.practice.boden_accounting_backend.service.impl;

import kyle.practice.boden_accounting_backend.dto.BrokerageTransactionDto;
import kyle.practice.boden_accounting_backend.entity.BrokerageTransaction;
import kyle.practice.boden_accounting_backend.exception.ResourceNotFoundException;
import kyle.practice.boden_accounting_backend.mapper.BrokerageTransactionMapper;
import kyle.practice.boden_accounting_backend.repository.BrokerageTransactionRepository;
import kyle.practice.boden_accounting_backend.service.BrokerageTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrokerageTransactionServiceImpl implements BrokerageTransactionService {

    private BrokerageTransactionRepository brokerageTransactionRepository;

    @Override
    public BrokerageTransactionDto createBrokerageTransaction(BrokerageTransactionDto brokerageTransactionDto) {
        BrokerageTransaction transaction = BrokerageTransactionMapper.mapToBrokerageTransaction(brokerageTransactionDto);
        BrokerageTransaction savedTransaction = brokerageTransactionRepository.save(transaction);
        return BrokerageTransactionMapper.mapToBrokerageTransactionDto(savedTransaction);
    }

    @Override
    public BrokerageTransactionDto getBrokerageTransactionById(Long transactionId) {
        BrokerageTransaction transaction = brokerageTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Brokerage transaction not found with id: " + transactionId));
        return BrokerageTransactionMapper.mapToBrokerageTransactionDto(transaction);
    }

    @Override
    public List<BrokerageTransactionDto> getAllBrokerageTransactions() {
        return brokerageTransactionRepository.findAll().stream()
                .map(BrokerageTransactionMapper::mapToBrokerageTransactionDto)
                .toList();
    }

    @Override
    public BrokerageTransactionDto updateBrokerageTransaction(Long transactionId, BrokerageTransactionDto updatedTransaction) {
        BrokerageTransaction transaction = brokerageTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Brokerage transaction not found with id: " + transactionId));

        transaction.setDate(updatedTransaction.getDate());
        transaction.setType(updatedTransaction.getType());
        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setTithed(updatedTransaction.isTithed());
        transaction.setNotes(updatedTransaction.getNotes());

        brokerageTransactionRepository.save(transaction);
        return BrokerageTransactionMapper.mapToBrokerageTransactionDto(transaction);
    }

    @Override
    public void deleteBrokerageTransaction(Long transactionId) {
        brokerageTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Brokerage transaction not found with id: " + transactionId));
        brokerageTransactionRepository.deleteById(transactionId);
    }
}
