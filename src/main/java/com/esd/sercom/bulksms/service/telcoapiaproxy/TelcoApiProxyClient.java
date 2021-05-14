package com.esd.sercom.bulksms.service.telcoapiaproxy;

import com.esd.sercom.bulksms.model.DTO.EmailModel;
import com.esd.sercom.bulksms.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Component
public class TelcoApiProxyClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final RestTemplate restTemplate;
    private final Utilities utilities;
    protected HttpHeaders httpHeaders;

    @Autowired
    public TelcoApiProxyClient(RestTemplate restTemplate, Utilities utilities) {
        this.restTemplate = restTemplate;
        this.utilities = utilities;
        setDefaultHeader();
    }

    private void setDefaultHeader(){
        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.add("Content-Type", "application/json");
    }

    public String sendEmail(EmailModel emailModel){
        HttpEntity<EmailModel>
                emailModelHttpEntity = new HttpEntity<>(emailModel);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(utilities.getEmailEndpoint(), HttpMethod.POST, emailModelHttpEntity, String.class);
            return exchange.getStatusCode().is2xxSuccessful()? "Successful" : "Failed";
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
