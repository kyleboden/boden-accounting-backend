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
@Table(name = "brokerage_total")
public class BrokerageTotal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "total_in_account")
    private double totalInAccount;

    @Column(name = "total_tithed")
    private double totalTithed;

    @Transient
    public double getTotalNonTithed() {
        return totalInAccount - totalTithed;
    }
}
