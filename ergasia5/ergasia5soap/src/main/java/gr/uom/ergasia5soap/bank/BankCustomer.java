package gr.uom.ergasia5soap.bank;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "BankCustomer", indexes = {
        @Index(name = "idx_bankcustomer_name", columnList = "name")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToOne
    private BankAccount account;

}
