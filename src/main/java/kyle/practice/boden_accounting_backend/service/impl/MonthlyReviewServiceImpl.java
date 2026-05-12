package kyle.practice.boden_accounting_backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import kyle.practice.boden_accounting_backend.dto.MonthlyReviewDto;
import kyle.practice.boden_accounting_backend.entity.MonthlyReview;
import kyle.practice.boden_accounting_backend.exception.ResourceNotFoundException;
import kyle.practice.boden_accounting_backend.mapper.MonthlyReviewMapper;
import kyle.practice.boden_accounting_backend.repository.MonthlyReviewRepository;
import kyle.practice.boden_accounting_backend.service.MonthlyReviewService;

import java.util.List;

@Service
@AllArgsConstructor
public class MonthlyReviewServiceImpl implements MonthlyReviewService {

    private MonthlyReviewRepository monthlyReviewRepository;

    @Override
    public MonthlyReviewDto createMonthlyEntry(MonthlyReviewDto monthlyReviewDto) {
        var monthlyReview = MonthlyReviewMapper.mapToMonthlyReview(monthlyReviewDto);
        var savedMonthlyReview = monthlyReviewRepository.save(monthlyReview);
        return MonthlyReviewMapper.mapToMonthlyReviewDto(savedMonthlyReview);
    }

    @Override
    public MonthlyReviewDto getMonthlyEntryById(Long entryId) {
        var monthlyReview = monthlyReviewRepository.findById(entryId).orElseThrow(() -> new ResourceNotFoundException("Monthly review not found with id: " + entryId));
        return MonthlyReviewMapper.mapToMonthlyReviewDto(monthlyReview);
    }

    @Override
    public List<MonthlyReviewDto> getAllMonthlyEntries() {
        List<MonthlyReview> monthlyReviews = monthlyReviewRepository.findAll();
        return monthlyReviews.stream().map(MonthlyReviewMapper::mapToMonthlyReviewDto).toList();
    }

    @Override
    public MonthlyReviewDto updateMonthlyEntry(Long entryId, MonthlyReviewDto updatedMonthlyReview) {
        var monthlyReview = monthlyReviewRepository.findById(entryId).orElseThrow(() -> new ResourceNotFoundException("Monthly review not found with id: " + entryId));
        monthlyReview.setKyleIncome(updatedMonthlyReview.getKyleIncome());
        monthlyReview.setSarahIncome(updatedMonthlyReview.getSarahIncome());
        monthlyReview.setGiftCardIncome(updatedMonthlyReview.getGiftCardIncome());
        monthlyReview.setBrokerageWithdrawal(updatedMonthlyReview.getBrokerageWithdrawal());
        monthlyReview.setHsaWithdrawal(updatedMonthlyReview.getHsaWithdrawal());
        monthlyReview.setTotalBank(updatedMonthlyReview.getTotalBank());
        monthlyReview.setTotalBrokerage(updatedMonthlyReview.getTotalBrokerage());
        monthlyReview.setTithingPaid(updatedMonthlyReview.getTithingPaid());
        monthlyReview.setInvestedTithed(updatedMonthlyReview.getInvestedTithed());
        monthlyReview.setInvestedNontithed(updatedMonthlyReview.getInvestedNontithed());
        monthlyReview.setDate(updatedMonthlyReview.getDate());
        monthlyReview.setNotes(updatedMonthlyReview.getNotes());
        monthlyReviewRepository.save(monthlyReview);
        return MonthlyReviewMapper.mapToMonthlyReviewDto(monthlyReview);
    }

    @Override
    public void deleteMonthlyEntry(Long entryId) {
        monthlyReviewRepository.findById(entryId).orElseThrow(() -> new ResourceNotFoundException("Monthly review not found with id: " + entryId));
        monthlyReviewRepository.deleteById(entryId);
    }
}
