package $package$.impl

import java.util.UUID

import $package$.api
import $package$.api.{$module;format="Camel"$, $module;format="Camel"$Service}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}

import scala.concurrent.Future

/**
  * Implementation of the $module;format="Camel"$Service.
  */
class $module;format="Camel"$ServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends $module;format="Camel"$Service {

  override def get$module;format="Camel"$(id: UUID) = ServiceCall { _ =>
    // Look up the $module$ entity for the given ID.
    val ref = persistentEntityRegistry.refFor[$module;format="Camel"$Entity](id.toString)

    // Ask the entity the Hello command.
    ref.ask(Get$module;format="Camel"$(id))
  }

  override def make$module;format="Camel"$ = ServiceCall { request =>
    // Look up the $module$ entity for the given ID.
    val ref = persistentEntityRegistry.refFor[$module;format="Camel"$Entity](request.id.toString)

    // Tell the entity to use the greeting message specified.
    ref.ask(Create$module;format="Camel"$(request.id, request.name))
  }

  override def $module;format="camel"$Topic(): Topic[api.$module;format="Camel"$Event] =
    TopicProducer.singleStreamWithOffset {
      fromOffset =>
        persistentEntityRegistry.eventStream($module;format="Camel"$Event.Tag, fromOffset)
          .map(ev => (convertEvent(ev), ev.offset))
    }

  private def convertEvent($module;format="camel"$Event: EventStreamElement[$module;format="Camel"$Event]): api.$module;format="Camel"$Event = {
    $module;format="camel"$Event match {
      case $module;format="Camel"$Created(id, name) => api.$module;format="Camel"$Created(id, name)
    }
  }
}
