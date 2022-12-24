package gr.uom.ergasia5soap.country;


import org.springframework.ws.server.endpoint.annotation.Endpoint;


@Endpoint
public class CountryEndpoint {
//    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
//
//    private CountryRepository countryRepository;
//
//    @Autowired
//    public CountryEndpoint(CountryRepository countryRepository) {
//        this.countryRepository = countryRepository;
//    }
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
//    @ResponsePayload
//    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
//        GetCountryResponse response = new GetCountryResponse();
//        response.setCountry(countryRepository.findCountry(request.getName()));
//
//        return response;
//    }
}