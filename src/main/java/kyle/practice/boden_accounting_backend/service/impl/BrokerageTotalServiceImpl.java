package kyle.practice.boden_accounting_backend.service.impl;

import kyle.practice.boden_accounting_backend.dto.BrokerageTotalDto;
import kyle.practice.boden_accounting_backend.entity.BrokerageTotal;
import kyle.practice.boden_accounting_backend.exception.ResourceNotFoundException;
import kyle.practice.boden_accounting_backend.mapper.BrokerageTotalMapper;
import kyle.practice.boden_accounting_backend.repository.BrokerageTotalRepository;
import kyle.practice.boden_accounting_backend.service.BrokerageTotalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrokerageTotalServiceImpl implements BrokerageTotalService {

    private BrokerageTotalRepository brokerageTotalRepository;

    @Override
    public BrokerageTotalDto createBrokerageTotal(BrokerageTotalDto brokerageTotalDto) {
        BrokerageTotal brokerageTotal = BrokerageTotalMapper.mapToBrokerageTotal(brokerageTotalDto);
        BrokerageTotal savedBrokerageTotal = brokerageTotalRepository.save(brokerageTotal);
        return BrokerageTotalMapper.mapToBrokerageTotalDto(savedBrokerageTotal);
    }

    @Override
    public BrokerageTotalDto getBrokerageTotalById(Long brokerageTotalId) {
        BrokerageTotal brokerageTotal = brokerageTotalRepository.findById(brokerageTotalId)
                .orElseThrow(() -> new ResourceNotFoundException("Brokerage total not found with id: " + brokerageTotalId));
        return BrokerageTotalMapper.mapToBrokerageTotalDto(brokerageTotal);
    }

    @Override
    public List<BrokerageTotalDto> getAllBrokerageTotals() {
        return brokerageTotalRepository.findAll().stream()
                .map(BrokerageTotalMapper::mapToBrokerageTotalDto)
                .toList();
    }

    @Override
    public BrokerageTotalDto updateBrokerageTotal(Long brokerageTotalId, BrokerageTotalDto updatedBrokerageTotal) {
        BrokerageTotal brokerageTotal = brokerageTotalRepository.findById(brokerageTotalId)
                .orElseThrow(() -> new ResourceNotFoundException("Brokerage total not found with id: " + brokerageTotalId));

        brokerageTotal.setDate(updatedBrokerageTotal.getDate());
        brokerageTotal.setTotalInAccount(updatedBrokerageTotal.getTotalInAccount());
        brokerageTotal.setTotalTithed(updatedBrokerageTotal.getTotalTithed());

        BrokerageTotal updatedBrokerageTotalEntity = brokerageTotalRepository.save(brokerageTotal);
        return BrokerageTotalMapper.mapToBrokerageTotalDto(updatedBrokerageTotalEntity);
    }

    @Override
    public void deleteBrokerageTotal(Long brokerageTotalId) {
        BrokerageTotal brokerageTotal = brokerageTotalRepository.findById(brokerageTotalId)
                .orElseThrow(() -> new ResourceNotFoundException("Brokerage total not found with id: " + brokerageTotalId));
        brokerageTotalRepository.delete(brokerageTotal);
    }
}
