import org.apache.flink.streaming.api.scala._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.Properties

object SinkTest {
  def main(args: Array[String]): Unit = {
    while ( {
      true
    }) {
      Thread.sleep(3000)
      writeToKafka()
    }
}
  def randomNum(): String = {
    val num = scala.util.Random.nextInt(100).toString
    num
  }

  def writeToKafka(): Unit = {
    // kafka配置信息
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "10.205.19.158:9092")

    properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer") //key 序列化
    properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer") //value 序列化
    val producer = new KafkaProducer[String, String](properties)

    // 写入kafka数据
    val number = randomNum()
    val record = new ProducerRecord[String, String]("origin_num", null, null, number)
    producer.send(record)
    producer.flush()

    System.out.println("发送数据: " + number)
    }
}
