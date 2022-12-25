package gr.uom.ergasia5soap.bank;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BankService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public BankService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Optional<BankCustomer> findAccountByCustomerName(String name) {
        return customerRepository.findByName(name);
    }

    @Transactional
    public BankAccount withdrawAmount(BankAccount account, double amount) {
        Long id = account.getId();
        BankAccount bankAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));

        if (account.getAmount() < amount) throw new RuntimeException("Insufficient Amount");

        double newAmount = bankAccount.getAmount() - amount;
        bankAccount.setAmount(newAmount);
        accountRepository.save(bankAccount);

        return bankAccount;
    }

    @Transactional
    public BankAccount depositAmount(BankAccount account, double amount) {
        Long id = account.getId();
        BankAccount bankAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));
        double newAmount = bankAccount.getAmount() + amount;
        bankAccount.setAmount(newAmount);
        accountRepository.save(bankAccount);

        return bankAccount;
    }

    @Transactional
    public BankCustomer getCustomerInfo(String customerName) {
        BankCustomer bankCustomer = customerRepository.findByName(customerName).orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));
        return bankCustomer;
    }

    @Transactional
    public boolean transferAmount(String customerNameSender, String customerNameReceiver, double amount) {
        BankCustomer bankCustomerSender = customerRepository.findByName(customerNameSender)
                .orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));
        BankCustomer bankCustomerReceiver = customerRepository.findByName(customerNameReceiver)
                .orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));

        boolean isSufficientAmount = bankCustomerSender.getAccount().getAmount() >= amount;

        if (!isSufficientAmount) throw new RuntimeException("Insufficient Amount");

        double senderBalance = bankCustomerSender.getAccount().getAmount();
        if (senderBalance < amount) throw new RuntimeException("Invalid AMOUNT");

        BankAccount accountSender = bankCustomerSender.getAccount();
        BankAccount accountReceiver = bankCustomerReceiver.getAccount();

        accountSender.setAmount(accountSender.getAmount() - amount);
        accountReceiver.setAmount(accountReceiver.getAmount() + amount);

        accountRepository.save(accountSender);
        accountRepository.save(accountReceiver);

        return true;
    }
}
