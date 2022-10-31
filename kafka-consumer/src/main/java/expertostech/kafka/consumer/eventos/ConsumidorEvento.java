package expertostech.kafka.consumer.eventos;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

@Slf4j
public class ConsumidorEvento {


    private final KafkaConsumer<String, String> consumer;


    public ConsumidorEvento() {
        consumer = criarConsumer();
    }



    private KafkaConsumer<String, String> criarConsumer() {

        if (consumer != null) {
            return consumer;
        }

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "default");


        return new KafkaConsumer<String, String>(properties);

    }



    public void executar(){

        List<String> topicos = new ArrayList<>(); // Lista com o nomes do tópico
        topicos.add("RegistroEvento"); // <- Nome do tópico
        consumer.subscribe(topicos);

        log.info("Iniciando consumer do tópico...");

        boolean continuar = true;
        while(continuar) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) { // Para o caso de receber mais de um msg...
                gravarMsg(record.topic(), record.partition(), record.value());

                if (record.value() == "fechar") {
                    continuar = false;
                }
            }
        }
        consumer.close();
    }

    private void gravarMsg(String topico, int particao, String mensagem) {
        log.info("Metadados: \n" +
                "Tópico: {}\n" +
                "Partição: {}\n" +
                "Mensagem: {}", topico, particao, mensagem);
    }


}
