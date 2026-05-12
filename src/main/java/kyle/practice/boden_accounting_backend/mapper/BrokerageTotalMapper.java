package kyle.practice.boden_accounting_backend.mapper;

import kyle.practice.boden_accounting_backend.dto.BrokerageTotalDto;
import kyle.practice.boden_accounting_backend.entity.BrokerageTotal;

public class BrokerageTotalMapper {

    public static BrokerageTotalDto mapToBrokerageTotalDto(BrokerageTotal brokerageTotal) {
        return new BrokerageTotalDto(
                brokerageTotal.getId(),
                brokerageTotal.getDate(),
                brokerageTotal.getTotalInAccount(),
                brokerageTotal.getTotalTithed()
        );
    }

    public static BrokerageTotal mapToBrokerageTotal(BrokerageTotalDto brokerageTotalDto) {
        return new BrokerageTotal(
                brokerageTotalDto.getId(),
                brokerageTotalDto.getDate(),
                brokerageTotalDto.getTotalInAccount(),
                brokerageTotalDto.getTotalTithed()
        );
    }
}
