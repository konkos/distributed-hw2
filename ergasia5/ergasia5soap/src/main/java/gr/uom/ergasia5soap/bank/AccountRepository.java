package gr.uom.ergasia5soap.bank;

import io.spring.guides.gs_producing_web_service.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccount, Long> {
}
