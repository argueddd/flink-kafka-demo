import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

object SourceTest {
  def main(args: Array[String]): Unit = {
    // 创建流处理环境
    val senv = StreamExecutionEnvironment.getExecutionEnvironment

    // kafka配置信息
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "10.205.19.158:9092")
    // 读取kafka数据
    val kafkaStream = senv
      .addSource(new FlinkKafkaConsumer[String]("origin_num", new SimpleStringSchema(), properties))

    // 读取的数据简单处理
    val resultDataStream = kafkaStream.filter(_.nonEmpty)
      .map(item => {
        item.toInt + 1
      })
      .map(item => {
        scala.math.pow(item, 2)
      })
      .map(item => {
        "输出结果：" + item.toString
      })

    // 打印控制台
    resultDataStream.print()

    // 驱动执行程序，传入程序名称
    senv.execute("source test")
  }
}
