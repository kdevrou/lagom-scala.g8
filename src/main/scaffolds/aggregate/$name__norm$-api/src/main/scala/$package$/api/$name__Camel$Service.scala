package $package$.api

import java.util.UUID
import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.broker.kafka.{KafkaProperties, PartitionKeyStrategy}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}

object $name;format="Camel"$Service  {
  val TOPIC_NAME = "greetings"
}

/**
  * The $name$ service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the $name;format="Camel"$Service.
  */
trait $name;format="Camel"$Service extends Service {

  /**
    * Example: curl http://localhost:9000/api/$name;format="lower"$/~UUID~
    */
  def get$name;format="Camel"$(id: UUID): ServiceCall[NotUsed, $name;format="Camel"$]

  /**
    * Example: curl -H "Content-Type: application/json" -X POST -d '{"name":
    * "new$name;format="Camel"$"}' http://localhost:9000/api/$name;format="lower"$
    */
  def make$name;format="Camel"$: ServiceCall[$name;format="Camel"$, Done]

  /**
    * This gets published to Kafka.
    */
  def $name;format="camel"$Topic(): Topic[$name;format="Camel"$Event]

  override final def descriptor = {
    import Service._
    // @formatter:off
    named("$name;format="norm"$")
      .withCalls(
        pathCall("/api/$name;format=\"lower\"$/:id", get$name;format="Camel"$ _),
        pathCall("/api/$name;format=\"lower\"$", make$name;format="Camel"$ _)
      )
      .withTopics(
        topic($name;format="Camel"$Service.TOPIC_NAME, $name;format="camel"$Topic)
          .addProperty(KafkaProperties.partitionKeyStrategy, PartitionKeyStrategy[$name;format="Camel"$Event](_.id))
      )
      .withAutoAcl(true)
    // @formatter:on
  }
}
