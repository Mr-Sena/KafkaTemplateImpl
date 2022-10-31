package expertostech.kafka.producer;

import expertostech.kafka.producer.eventos.ProducerEvento;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerApp {

    public static void main(String args[]) {

        ProducerApp aplicacao = new ProducerApp();
        aplicacao.iniciar();

    }

    private void iniciar() {
        log.info("Iniciando a aplicação...");
        ProducerEvento produtor = new ProducerEvento();
        produtor.executar();
    }

}
