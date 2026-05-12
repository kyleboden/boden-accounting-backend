package kyle.practice.boden_accounting_backend.service;

import java.util.List;

import kyle.practice.boden_accounting_backend.dto.MonthlyReviewDto;


public interface MonthlyReviewService {

    MonthlyReviewDto createMonthlyEntry(MonthlyReviewDto monthlyEntryDto);

    MonthlyReviewDto getMonthlyEntryById(Long entryId);

    List<MonthlyReviewDto> getAllMonthlyEntries();

    MonthlyReviewDto updateMonthlyEntry(Long entryId, MonthlyReviewDto updatedEntry);

    void deleteMonthlyEntry(Long entryId);
}
