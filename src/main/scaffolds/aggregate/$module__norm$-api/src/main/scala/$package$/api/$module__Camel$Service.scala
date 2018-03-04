package $package$.api

import java.util.UUID
import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.broker.kafka.{KafkaProperties, PartitionKeyStrategy}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}

object $module;format="Camel"$Service  {
  val TOPIC_NAME = "$module;format="norm"$"
}

/**
  * The $module$ service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the $module;format="Camel"$Service.
  */
trait $module;format="Camel"$Service extends Service {

  /**
    * Example: curl http://localhost:9000/api/$module;format="lower"$/~UUID~
    */
  def get$module;format="Camel"$(id: UUID): ServiceCall[NotUsed, $module;format="Camel"$]

  /**
    * Example: curl -H "Content-Type: application/json" -X POST -d '{"name":
    * "new$module;format="Camel"$"}' http://localhost:9000/api/$module;format="lower"$
    */
  def make$module;format="Camel"$: ServiceCall[$module;format="Camel"$, Done]

  /**
    * This gets published to Kafka.
    */
  def $module;format="camel"$Topic(): Topic[$module;format="Camel"$Event]

  override final def descriptor = {
    import Service._
    // @formatter:off
    named("$module;format="norm"$")
      .withCalls(
        pathCall("/api/$module;format="lower"$/:id", get$module;format="Camel"$ _),
        pathCall("/api/$module;format="lower"$", make$module;format="Camel"$ _)
      )
      .withTopics(
        topic($module;format="Camel"$Service.TOPIC_NAME, this.$module;format="camel"$Topic)
          .addProperty(KafkaProperties.partitionKeyStrategy, PartitionKeyStrategy[$module;format="Camel"$Event](_.id.toString))
      )
      .withAutoAcl(true)
    // @formatter:on
  }
}
