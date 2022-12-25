package gr.uom.ergasia5soap.bank;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BankEndpoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private final BankService bankService;

    @Autowired
    public BankEndpoint(BankService bankService) {
        this.bankService = bankService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "withdrawAmountRequest")
    @ResponsePayload
    public WithdrawAmountResponse withdrawAmount(@RequestPayload WithdrawAmountRequest request) {
        String customerName = request.getCustomerName();
        double amount = request.getAmount();

        BankCustomer bankCustomer =
                bankService.findAccountByCustomerName(customerName)
                        .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        BankAccount account = bankService.withdrawAmount(bankCustomer.getAccount(), amount);

        ObjectFactory objectFactory = new ObjectFactory();
        Account responseAccount = objectFactory.createAccount();
        responseAccount.setAmount(account.getAmount());
        Customer responseCustomer = objectFactory.createCustomer();
        responseCustomer.setName(customerName);
        responseAccount.setCustomer(responseCustomer);

        WithdrawAmountResponse response = new WithdrawAmountResponse();
        response.setAccount(responseAccount);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "depositAmountRequest")
    @ResponsePayload
    public DepositAmountResponse withdrawAmount(@RequestPayload DepositAmountRequest request) {
        String customerName = request.getCustomerName();
        double amount = request.getAmount();

        BankCustomer bankCustomer =
                bankService.findAccountByCustomerName(customerName)
                        .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        BankAccount bankAccount = bankService.depositAmount(bankCustomer.getAccount(), amount);

        ObjectFactory objectFactory = new ObjectFactory();
        Account responseAccount = objectFactory.createAccount();
        responseAccount.setAmount(bankAccount.getAmount());
        Customer responseCustomer = objectFactory.createCustomer();
        responseCustomer.setName(customerName);
        responseAccount.setCustomer(responseCustomer);
        DepositAmountResponse response = new DepositAmountResponse();
        response.setAccount(responseAccount);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccountInfoRequest")
    @ResponsePayload
    public GetAccountInfoResponse getAccountInfo(@RequestPayload GetAccountInfoRequest request) {
        String customerName = request.getCustomerName();
        var customerInfo = bankService.getCustomerInfo(customerName);

        ObjectFactory objectFactory = new ObjectFactory();
        GetAccountInfoResponse getAccountInfoResponse = objectFactory.createGetAccountInfoResponse();
        Customer customer = objectFactory.createCustomer();
        customer.setName(customerInfo.getName());

        BankAccount account1 = customerInfo.getAccount();

        Account account = objectFactory.createAccount();
        account.setCustomer(customer);
        account.setAmount(account1.getAmount());
        account.setCurrency(account1.getCurrency());

        customer.setAccount(account);

        getAccountInfoResponse.setCustomer(customer);
        return getAccountInfoResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "transferAmountRequest")
    @ResponsePayload
    public TransferAmountResponse transferAmountRequest(@RequestPayload TransferAmountRequest request) {
        String customerNameSender = request.getCustomerName1();
        String customerNameReceiver = request.getCustomerName2();
        double amount = request.getAmount();
        boolean isSuccessful = bankService.transferAmount(customerNameSender, customerNameReceiver, amount);

        ObjectFactory objectFactory = new ObjectFactory();
        TransferAmountResponse transferAmountResponse = objectFactory.createTransferAmountResponse();
        transferAmountResponse.setSuccessful(isSuccessful);

        return transferAmountResponse;
    }
}
