package gr.uom.ergasia5soap.bank;

import io.spring.guides.gs_producing_web_service.Account;
import io.spring.guides.gs_producing_web_service.WithdrawAmountRequest;
import io.spring.guides.gs_producing_web_service.WithdrawAmountResponse;
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public WithdrawAmountResponse getCountry(@RequestPayload WithdrawAmountRequest request) {
        String customerName = request.getCustomerName();
        double amount = request.getAmount();

        BankCustomer bankCustomer =
                bankService.findAccountByCustomerName(customerName)
                        .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        BankAccount account = bankCustomer.getAccount();

        WithdrawAmountResponse response = new WithdrawAmountResponse();


        return response;
    }
}
