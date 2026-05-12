package kyle.practice.boden_accounting_backend.controller;

import kyle.practice.boden_accounting_backend.dto.BrokerageTransactionDto;
import kyle.practice.boden_accounting_backend.service.BrokerageTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/brokerage-transactions")
public class BrokerageTransactionController {

    private BrokerageTransactionService brokerageTransactionService;

    @PostMapping
    public ResponseEntity<BrokerageTransactionDto> createTransaction(@RequestBody BrokerageTransactionDto transactionDto) {
        var savedTransaction = brokerageTransactionService.createBrokerageTransaction(transactionDto);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BrokerageTransactionDto> getTransactionById(@PathVariable("id") Long transactionId) {
        var transactionDto = brokerageTransactionService.getBrokerageTransactionById(transactionId);
        return ResponseEntity.ok(transactionDto);
    }

    @GetMapping
    public ResponseEntity<List<BrokerageTransactionDto>> getAllTransactions() {
        var transactions = brokerageTransactionService.getAllBrokerageTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("{id}")
    public ResponseEntity<BrokerageTransactionDto> updateTransaction(@PathVariable("id") Long transactionId,
                                                                     @RequestBody BrokerageTransactionDto updatedTransaction) {
        var transactionDto = brokerageTransactionService.updateBrokerageTransaction(transactionId, updatedTransaction);
        return ResponseEntity.ok(transactionDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") Long transactionId) {
        brokerageTransactionService.deleteBrokerageTransaction(transactionId);
        return ResponseEntity.ok("Brokerage transaction deleted successfully");
    }
}
