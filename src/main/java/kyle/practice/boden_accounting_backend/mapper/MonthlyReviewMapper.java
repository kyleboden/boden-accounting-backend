package kyle.practice.boden_accounting_backend.mapper;

import kyle.practice.boden_accounting_backend.dto.MonthlyReviewDto;
import kyle.practice.boden_accounting_backend.entity.MonthlyReview;

public class MonthlyReviewMapper {

    public static MonthlyReviewDto mapToMonthlyReviewDto(MonthlyReview monthlyReview) {
        return new MonthlyReviewDto(
                monthlyReview.getId(),
                monthlyReview.getKyleIncome(),
                monthlyReview.getSarahIncome(),
                monthlyReview.getGiftCardIncome(),
                monthlyReview.getBrokerageWithdrawal(),
                monthlyReview.getHsaWithdrawal(),
                monthlyReview.getTotalBank(),
                monthlyReview.getTotalBrokerage(),
                monthlyReview.getTithingPaid(),
                monthlyReview.getInvestedNonTithed(),
                monthlyReview.getDepositId(),
                monthlyReview.getWithdrawalId(),
                monthlyReview.getDate(),
                monthlyReview.getNotes()
        );
    }

    public static MonthlyReview mapToMonthlyReview(MonthlyReviewDto monthlyReviewDto) {
        return new MonthlyReview(
                monthlyReviewDto.getId(),
                monthlyReviewDto.getKyleIncome(),
                monthlyReviewDto.getSarahIncome(),
                monthlyReviewDto.getGiftCardIncome(),
                monthlyReviewDto.getBrokerageWithdrawal(),
                monthlyReviewDto.getHsaWithdrawal(),
                monthlyReviewDto.getTotalBank(),
                monthlyReviewDto.getTotalBrokerage(),
                monthlyReviewDto.getTithingPaid(),
                monthlyReviewDto.getInvestedNonTithed(),
                monthlyReviewDto.getDepositId(),
                monthlyReviewDto.getWithdrawalId(),
                monthlyReviewDto.getDate(),
                monthlyReviewDto.getNotes()
        );
    }
}
