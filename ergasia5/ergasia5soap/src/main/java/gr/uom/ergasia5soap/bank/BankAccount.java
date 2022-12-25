package gr.uom.ergasia5soap.bank;

import io.spring.guides.gs_producing_web_service.Currency;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "account")
    private BankCustomer customer;

    private double amount;

    private Currency currency;

}
