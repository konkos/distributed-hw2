package gr.uom.ergasia5soap.bank;

import io.spring.guides.gs_producing_web_service.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public BankService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public Optional<BankCustomer> findAccountByCustomerName(String name) {
        return customerRepository.findByName(name);
    }
}
