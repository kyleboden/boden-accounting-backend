package kyle.practice.boden_accounting_backend.service;

import kyle.practice.boden_accounting_backend.dto.BrokerageTotalDto;

import java.util.List;

public interface BrokerageTotalService {

    BrokerageTotalDto createBrokerageTotal(BrokerageTotalDto brokerageTotalDto);

    BrokerageTotalDto getBrokerageTotalById(Long brokerageTotalId);

    List<BrokerageTotalDto> getAllBrokerageTotals();

    BrokerageTotalDto updateBrokerageTotal(Long brokerageTotalId, BrokerageTotalDto updatedBrokerageTotal);

    void deleteBrokerageTotal(Long brokerageTotalId);
}
