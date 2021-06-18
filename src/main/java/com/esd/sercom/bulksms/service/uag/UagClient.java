package com.esd.sercom.bulksms.service.uag;

import com.esd.sercom.bulksms.exceptions.BadRequestException;
import com.esd.sercom.bulksms.model.DTO.UagTransactionModificationDTO;
import com.esd.sercom.bulksms.util.Keyword;
import com.esd.sercom.bulksms.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class UagClient {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final RestTemplate restTemplate;
    private final Utilities utilities;
    protected HttpHeaders httpHeaders;


    public UagClient(RestTemplate restTemplate, Utilities utilities) {
        this.restTemplate = restTemplate;
        this.utilities = utilities;
    }

    private void setDefaultHeader(){
        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.add("Content-Type", "application/json");
    }

    public ResponseEntity<UagTransactionModificationDTO> modify(UagTransactionModificationDTO dto){
        HttpEntity<UagTransactionModificationDTO> entity = new HttpEntity<>(dto,httpHeaders);
        String url = utilities.getModifyUrl();
        try {
            return restTemplate.exchange(url, HttpMethod.POST,entity,UagTransactionModificationDTO.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public ResponseEntity<UagTransactionModificationDTO> query(String accountName, String keyword){
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        String url = keyword.equalsIgnoreCase(Keyword.QUERY.name())?
                utilities.getQueryUrl()+ "?accountName="+accountName:
                utilities.getVerificationUrl()+ "?accountName="+accountName;
        try {
            return restTemplate.exchange(url, HttpMethod.GET,entity,UagTransactionModificationDTO.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }
}
