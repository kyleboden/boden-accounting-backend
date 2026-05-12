package kyle.practice.boden_accounting_backend.controller;

import kyle.practice.boden_accounting_backend.dto.BrokerageTotalDto;
import kyle.practice.boden_accounting_backend.service.BrokerageTotalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/brokerage-totals")
public class BrokerageTotalController {

    private BrokerageTotalService brokerageTotalService;

    @PostMapping
    public ResponseEntity<BrokerageTotalDto> createBrokerageTotal(@RequestBody BrokerageTotalDto brokerageTotalDto) {
        var savedBrokerageTotal = brokerageTotalService.createBrokerageTotal(brokerageTotalDto);
        return new ResponseEntity<>(savedBrokerageTotal, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BrokerageTotalDto> getBrokerageTotalById(@PathVariable("id") Long brokerageTotalId) {
        var brokerageTotalDto = brokerageTotalService.getBrokerageTotalById(brokerageTotalId);
        return ResponseEntity.ok(brokerageTotalDto);
    }

    @GetMapping
    public ResponseEntity<List<BrokerageTotalDto>> getAllBrokerageTotals() {
        var brokerageTotals = brokerageTotalService.getAllBrokerageTotals();
        return ResponseEntity.ok(brokerageTotals);
    }

    @PutMapping("{id}")
    public ResponseEntity<BrokerageTotalDto> updateBrokerageTotal(@PathVariable("id") Long brokerageTotalId,
                                                                   @RequestBody BrokerageTotalDto updatedBrokerageTotal) {
        var brokerageTotalDto = brokerageTotalService.updateBrokerageTotal(brokerageTotalId, updatedBrokerageTotal);
        return ResponseEntity.ok(brokerageTotalDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBrokerageTotal(@PathVariable("id") Long brokerageTotalId) {
        brokerageTotalService.deleteBrokerageTotal(brokerageTotalId);
        return ResponseEntity.ok("Brokerage total deleted successfully");
    }
}
