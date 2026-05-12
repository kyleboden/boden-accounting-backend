package kyle.practice.boden_accounting_backend.dto;

import kyle.practice.boden_accounting_backend.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrokerageTransactionDto {

    private Long id;
    private LocalDate date;
    private TransactionType type;
    private double amount;
    private boolean tithed;
    private String notes;
}
