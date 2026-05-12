package kyle.practice.boden_accounting_backend.controller;

import kyle.practice.boden_accounting_backend.dto.MonthlyReviewDto;
import kyle.practice.boden_accounting_backend.service.MonthlyReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/monthly-reviews")
public class MonthlyReviewController {

    private MonthlyReviewService monthlyReviewService;

    // Build Add Monthly Review REST API
    @PostMapping
    public ResponseEntity<MonthlyReviewDto> createMonthlyEntry(@RequestBody MonthlyReviewDto monthlyReviewDto) {
       var savedEntry = monthlyReviewService.createMonthlyEntry(monthlyReviewDto);
       return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
    }

    // Build Get Monthly Review REST API
    @GetMapping("{id}")
    public ResponseEntity<MonthlyReviewDto> getMonthlyEntryById(@PathVariable("id") Long entryId) {
        var monthlyEntryDto = monthlyReviewService.getMonthlyEntryById(entryId);
        return ResponseEntity.ok(monthlyEntryDto);
    }

    // Build get all monthly reviews REST API
    @GetMapping
    public ResponseEntity<List<MonthlyReviewDto>> getAllMonthlyEntries() {
        List<MonthlyReviewDto> entries = monthlyReviewService.getAllMonthlyEntries();
        return ResponseEntity.ok(entries);
    }

    // Build update monthly review REST API
    @PutMapping("{id}")
    public ResponseEntity<MonthlyReviewDto> updateMonthlyEntry(@PathVariable("id") Long entryId, @RequestBody MonthlyReviewDto updatedEntry) {
        var monthlyEntryDto = monthlyReviewService.updateMonthlyEntry(entryId, updatedEntry);
        return ResponseEntity.ok(monthlyEntryDto);
    }

    // Build delete monthly review REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMonthlyEntry(@PathVariable("id") Long entryId) {
        monthlyReviewService.deleteMonthlyEntry(entryId);
        return ResponseEntity.ok("Monthly entry deleted successfully");
    }
}
