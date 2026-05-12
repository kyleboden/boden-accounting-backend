package kyle.practice.boden_accounting_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReviewDto {

    private Long id;
    private double kyleIncome;
    private double sarahIncome;
    private double giftCardIncome;
    private double brokerageWithdrawal;
    private double hsaWithdrawal;
    private double totalBank;
    private double totalBrokerage;
    private double tithingPaid;
    private double investedTithed;
    private double investedNontithed;
    private LocalDate date;
    private String notes;
}
