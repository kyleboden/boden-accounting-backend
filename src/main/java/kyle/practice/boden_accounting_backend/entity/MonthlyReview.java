package kyle.practice.boden_accounting_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "monthlyReview")
public class MonthlyReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kyle_income")
    private double kyleIncome;

    @Column(name = "sarah_income")
    private double sarahIncome;

    @Column(name = "gift_card_income")
    private double giftCardIncome;

    @Column(name = "brokerage_withdrawal")
    private double brokerageWithdrawal;

    @Column(name = "hsa_withdrawal")
    private double hsaWithdrawal;

    @Column(name = "total_bank")
    private double totalBank;
    
    @Column(name = "total_brokerage")
    private double totalBrokerage;

    @Column(name = "tithing_paid")
    private double tithingPaid;

    @Column(name = "invested_tithed")
    private double investedTithed;

    @Column(name = "invested_nontithed")
    private double investedNontithed;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "notes")
    private String notes;
}


// - Kyle Income
// - Sarah Income
// - Gift Card
// - Brokerage Withdrawal
// - HSA Withdrawal
// - Total bank last day
// - Total brokerage last day
// - Amount Tithing paid
// - Tithed invested
// - Non-tithed invested (both this and tithed each create a new row in brokerage transactions)
// - Date
// - Notes

