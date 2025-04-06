package com.serviciotecsup.service;

import com.serviciotecsup.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private final String URL_DESTINO = "http://localhost:8080/api/webhook/usuario";

    private final RestTemplate restTemplate;
    private final String secretKey;

    public WebhookService(RestTemplate restTemplate,
                          @Value("${webhook.secret}") String secretKey) {
        this.restTemplate = restTemplate;
        this.secretKey = secretKey;
    }

    public void notificarCreacionOActualizacion(Usuario usuario) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Webhook-Secret", secretKey);
        HttpEntity<Usuario> request = new HttpEntity<>(usuario, headers);
        restTemplate.postForObject(URL_DESTINO, request, String.class);
    }

    public void notificarEliminacion(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Webhook-Secret", secretKey);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        restTemplate.exchange(URL_DESTINO + "/" + code, HttpMethod.DELETE, request, String.class);
    }
}
