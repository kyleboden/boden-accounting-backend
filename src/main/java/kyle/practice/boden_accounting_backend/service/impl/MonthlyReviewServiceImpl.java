package kyle.practice.boden_accounting_backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import kyle.practice.boden_accounting_backend.dto.MonthlyReviewDto;
import kyle.practice.boden_accounting_backend.dto.BrokerageTransactionDto;
import kyle.practice.boden_accounting_backend.entity.TransactionType;
import java.time.LocalDate;
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
    private kyle.practice.boden_accounting_backend.service.BrokerageTransactionService brokerageTransactionService;

    @Override
    public MonthlyReviewDto createMonthlyEntry(MonthlyReviewDto monthlyReviewDto) {
        // Create monthly review entry
        var monthlyReview = MonthlyReviewMapper.mapToMonthlyReview(monthlyReviewDto);
        var savedMonthlyReview = monthlyReviewRepository.save(monthlyReview);

        // create brokerage transactions for invested amounts (always non-tithed) and withdrawals
        // -- Deposit -- 
        if (savedMonthlyReview.getInvestedNonTithed() > 0) {
            var txDto = new BrokerageTransactionDto(
                    null,
                    LocalDate.now(),
                    TransactionType.DEPOSIT,
                    savedMonthlyReview.getInvestedNonTithed(),
                    false,
                    "Monthly review - invested: " + (savedMonthlyReview.getNotes() == null ? "" : savedMonthlyReview.getNotes())
            );
            var created = brokerageTransactionService.createBrokerageTransaction(txDto);
            savedMonthlyReview.setDepositId(created.getId());
        }

        // -- Withdrawal -- 
        if (savedMonthlyReview.getBrokerageWithdrawal() > 0) {
            var withdrawTx = new BrokerageTransactionDto(
                    null,
                    LocalDate.now(),
                    TransactionType.WITHDRAWAL,
                    savedMonthlyReview.getBrokerageWithdrawal(),
                    false,
                    "Monthly review - brokerage withdrawal: " + (savedMonthlyReview.getNotes() == null ? "" : savedMonthlyReview.getNotes())
            );
            var createdW = brokerageTransactionService.createBrokerageTransaction(withdrawTx);
            savedMonthlyReview.setWithdrawalId(createdW.getId());
        }
        // persist transaction links
        monthlyReviewRepository.save(savedMonthlyReview);
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
        monthlyReview.setInvestedNonTithed(updatedMonthlyReview.getInvestedNonTithed());

        // handle investment transaction always non-tithed when sent
        Long existingInvestmentTxId = monthlyReview.getDepositId();
        if (monthlyReview.getInvestedNonTithed() > 0) {
            var txDto = new BrokerageTransactionDto(
                    existingInvestmentTxId,
                    LocalDate.now(),
                    TransactionType.DEPOSIT,
                    monthlyReview.getInvestedNonTithed(),
                    false,
                    "Monthly review - invested: " + (updatedMonthlyReview.getNotes() == null ? "" : updatedMonthlyReview.getNotes())
            );
            if (existingInvestmentTxId != null) {
                brokerageTransactionService.updateBrokerageTransaction(existingInvestmentTxId, txDto);
            } else {
                var created = brokerageTransactionService.createBrokerageTransaction(txDto);
                monthlyReview.setDepositId(created.getId());
            }
        } else if (existingInvestmentTxId != null) {
            brokerageTransactionService.deleteBrokerageTransaction(existingInvestmentTxId);
            monthlyReview.setDepositId(null);
        }

        // handle brokerage withdrawal transaction
        double newWithdrawal = updatedMonthlyReview.getBrokerageWithdrawal();
        Long existingWithdrawalTxId = monthlyReview.getWithdrawalId();
        monthlyReview.setBrokerageWithdrawal(newWithdrawal);
        if (newWithdrawal > 0) {
            var wDto = new BrokerageTransactionDto(
                    existingWithdrawalTxId,
                    LocalDate.now(),
                    TransactionType.WITHDRAWAL,
                    newWithdrawal,
                    false,
                    "Monthly review - brokerage withdrawal: " + (updatedMonthlyReview.getNotes() == null ? "" : updatedMonthlyReview.getNotes())
            );
            if (existingWithdrawalTxId != null) {
                brokerageTransactionService.updateBrokerageTransaction(existingWithdrawalTxId, wDto);
            } else {
                var createdW = brokerageTransactionService.createBrokerageTransaction(wDto);
                monthlyReview.setWithdrawalId(createdW.getId());
            }
        } else if (existingWithdrawalTxId != null) {
            brokerageTransactionService.deleteBrokerageTransaction(existingWithdrawalTxId);
            monthlyReview.setWithdrawalId(null);
        }
        monthlyReview.setDate(updatedMonthlyReview.getDate());
        monthlyReview.setNotes(updatedMonthlyReview.getNotes());
        monthlyReviewRepository.save(monthlyReview);
        return MonthlyReviewMapper.mapToMonthlyReviewDto(monthlyReview);
    }

    @Override
    public void deleteMonthlyEntry(Long entryId) {
        var monthlyReview = monthlyReviewRepository.findById(entryId)
                .orElseThrow(() -> new ResourceNotFoundException("Monthly review not found with id: " + entryId));

        if (monthlyReview.getDepositId() != null) {
            brokerageTransactionService.deleteBrokerageTransaction(monthlyReview.getDepositId());
        }

        if (monthlyReview.getWithdrawalId() != null) {
            brokerageTransactionService.deleteBrokerageTransaction(monthlyReview.getWithdrawalId());
        }

        monthlyReviewRepository.deleteById(entryId);
    }
}
