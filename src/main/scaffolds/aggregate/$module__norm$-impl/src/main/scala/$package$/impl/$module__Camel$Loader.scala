package $package$.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import $package$.api.$module;format="Camel"$Service
import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import com.softwaremill.macwire._

class $module;format="Camel"$Loader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new $module;format="Camel"$Application(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new $module;format="Camel"$Application(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[$module;format="Camel"$Service])
}

abstract class $module;format="Camel"$Application(context: LagomApplicationContext)
  extends LagomApplication(context)
    with CassandraPersistenceComponents
    with LagomKafkaComponents
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[$module;format="Camel"$Service](wire[$module;format="Camel"$ServiceImpl])

  // Register the JSON serializer registry
  override lazy val jsonSerializerRegistry = $module;format="Camel"$SerializerRegistry

  // Register the $module$ persistent entity
  persistentEntityRegistry.register(wire[$module;format="Camel"$Entity])
}
