package gr.uom.ergasia5soap.bank;

import io.spring.guides.gs_producing_web_service.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<BankCustomer, Long> {
    Optional<BankCustomer> findByName(String name);
}
