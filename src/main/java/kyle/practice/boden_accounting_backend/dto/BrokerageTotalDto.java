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
public class BrokerageTotalDto {

    private Long id;
    private LocalDate date;
    private double totalInAccount;
    private double totalTithed;

    public double getTotalNonTithed() {
        return totalInAccount - totalTithed;
    }
}
