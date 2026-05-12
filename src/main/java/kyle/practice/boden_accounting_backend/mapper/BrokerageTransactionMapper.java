package kyle.practice.boden_accounting_backend.mapper;

import kyle.practice.boden_accounting_backend.dto.BrokerageTransactionDto;
import kyle.practice.boden_accounting_backend.entity.BrokerageTransaction;

public class BrokerageTransactionMapper {

    public static BrokerageTransactionDto mapToBrokerageTransactionDto(BrokerageTransaction transaction) {
        return new BrokerageTransactionDto(
                transaction.getId(),
                transaction.getDate(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.isTithed(),
                transaction.getNotes()
        );
    }

    public static BrokerageTransaction mapToBrokerageTransaction(BrokerageTransactionDto transactionDto) {
        return new BrokerageTransaction(
                transactionDto.getId(),
                transactionDto.getDate(),
                transactionDto.getType(),
                transactionDto.getAmount(),
                transactionDto.isTithed(),
                transactionDto.getNotes()
        );
    }
}
