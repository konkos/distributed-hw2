package gr.uom.ergasia5soap;

import gr.uom.ergasia5soap.bank.AccountRepository;
import gr.uom.ergasia5soap.bank.BankAccount;
import gr.uom.ergasia5soap.bank.BankCustomer;
import gr.uom.ergasia5soap.bank.CustomerRepository;
import io.spring.guides.gs_producing_web_service.Currency;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    private static final double INITIAL_AMOUNT = 100;

    public MyRunner(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        BankCustomer bankCustomer1 = BankCustomer.builder()
                .id(1L).name("Customer 1").build();

        BankCustomer bankCustomer2 = BankCustomer.builder()
                .id(2L).name("Customer 2").build();

        BankCustomer bankCustomer3 = BankCustomer.builder()
                .id(3L).name("Customer 3").build();

        customerRepository.save(bankCustomer1);
        customerRepository.save(bankCustomer2);
        customerRepository.save(bankCustomer3);

        BankAccount bankAccount1 = BankAccount.builder()
                .currency(Currency.EUR)
                .amount(INITIAL_AMOUNT)
                .build();
        bankCustomer1.setAccount(bankAccount1);

        BankAccount bankAccount2 = BankAccount.builder()
                .currency(Currency.EUR)
                .amount(INITIAL_AMOUNT)
                .build();
        bankCustomer2.setAccount(bankAccount2);

        BankAccount bankAccount3 = BankAccount.builder()
                .currency(Currency.EUR)
                .amount(INITIAL_AMOUNT)
                .build();
        bankCustomer3.setAccount(bankAccount3);


        accountRepository.save(bankAccount1);
        accountRepository.save(bankAccount2);
        accountRepository.save(bankAccount3);

        bankCustomer1.setAccount(bankAccount1);
        bankCustomer2.setAccount(bankAccount2);
        bankCustomer3.setAccount(bankAccount3);

        customerRepository.save(bankCustomer1);
        customerRepository.save(bankCustomer2);
        customerRepository.save(bankCustomer3);
    }
}
