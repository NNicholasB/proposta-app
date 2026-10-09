package com.study.proposta_app.agendador;

import com.study.proposta_app.entity.Proposta;
import com.study.proposta_app.repository.PropostaRepository;
import com.study.proposta_app.service.NotificacaoRabbitService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
@Component
public class PropostaSemIntegracao {

    private final PropostaRepository propostaRepository;

    private final NotificacaoRabbitService notificacaoRabbitService;

    @Value("${rabbitmq.propostapendente.exchange}")
    private final String exchange;

    private final Logger logger= LoggerFactory.getLogger(PropostaSemIntegracao.class);

    @Scheduled(fixedDelay = 10,timeUnit = TimeUnit.SECONDS)
    public void buscarPropostaSemIntegracao(){
        propostaRepository.findAllByIntegradaIsFalse().forEach(proposta-> {
                    try {
                        notificacaoRabbitService.notificar(proposta, exchange);
                        atualizarProposta(proposta);
                    } catch (RuntimeException ex) {
                       logger.error(ex.getMessage());
                    }

                }
                );
    }
    private void atualizarProposta(Proposta proposta){
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }

}
