package org.example;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class KafkaUtils {
    public static final String broker_list = "10.205.19.158:9092";
    public static final String topic = "quickstart-events";  // kafka topic，Flink 程序中需要和这个统一
    public static void writeToKafka() throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", broker_list);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); //key 序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); //value 序列化
        KafkaProducer producer = new KafkaProducer<String, String>(props);

        PlayStart playStart = new PlayStart();
        playStart.setTimestamp(System.currentTimeMillis());
        int user = new Random().nextInt(10000000);
        playStart.setUserID("user"+user);
        Map<String, String> tags = new HashMap<String, String>();
        Map<String, Object> fields = new HashMap<String, Object>();

        int ip = new Random().nextInt(100000)%255;
        tags.put("user_agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        tags.put("ip", "192.168.99."+ip);

        int program_id = new Random().nextInt(100000);
        int content_id = new Random().nextInt(100);
        int duration = new Random().nextInt(1000);
        fields.put("program_id", program_id);
        fields.put("content_id", program_id+""+content_id);
        fields.put("play_duration", duration);

        playStart.setTags(tags);
        playStart.setFields(fields);

        ProducerRecord record = new ProducerRecord<String, String>(topic, null, null, JSON.toJSONString(playStart));
        producer.send(record);
        System.out.println("发送数据: " + JSON.toJSONString(playStart));

        producer.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(3000);
            writeToKafka();
        }
    }
}
