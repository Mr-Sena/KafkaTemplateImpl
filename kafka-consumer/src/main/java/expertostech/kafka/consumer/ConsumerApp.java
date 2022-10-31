package expertostech.kafka.consumer;

import expertostech.kafka.consumer.eventos.ConsumidorEvento;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsumerApp {

    public static void main(String args[]) {

        ConsumerApp aplicacao = new ConsumerApp();
        aplicacao.iniciar();

    }

    private void iniciar() {
        log.info("Iniciando a aplicação...");
        ConsumidorEvento consumidor = new ConsumidorEvento();
        consumidor.executar();
    }

}