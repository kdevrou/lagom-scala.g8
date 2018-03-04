package $package$.impl

import java.util.UUID

import $package$.api
import $package$.api.$module;format="Camel"$Service
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.transport.NotFound
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}

import scala.concurrent.ExecutionContext

/**
  * Implementation of the $module;format="Camel"$Service.
  */
class $module;format="Camel"$ServiceImpl(persistentEntityRegistry: PersistentEntityRegistry)(implicit ec: ExecutionContext) extends $module;format="Camel"$Service {

  override def get$module;format="Camel"$(id: UUID) = ServiceCall { _ =>
    // Look up the $module$ entity for the given ID.
    val ref = entityRef(id)

    // Ask the entity the Hello command.
    ref.ask(Get$module;format="Camel"$).map {
      case Some($module;format="camel"$) => api.$module;format="Camel"$(Some($module;format="camel"$.id), $module;format="camel"$.name)
      case None => throw NotFound("not found")
    }
  }

  override def make$module;format="Camel"$ = ServiceCall { request =>
    // Look up the $module$ entity for the given ID.
    val ref = entityRef(request.id)

    // Tell the entity to use the greeting message specified.
    ref.ask(Create$module;format="Camel"$(request.id.get, request.name))
  }

  override def $module;format="camel"$Topic(): Topic[api.$module;format="Camel"$Event] =
    TopicProducer.singleStreamWithOffset {
      fromOffset =>
        persistentEntityRegistry.eventStream($module;format="Camel"$Event.Tag, fromOffset)
          .map(ev => (convertEvent(ev), ev.offset))
    }

  private def convertEvent(eventStreamElement: EventStreamElement[$module;format="Camel"$Event]): api.$module;format="Camel"$Event = {
    eventStreamElement match {
      case EventStreamElement($module;format="camel"$Id, $module;format="Camel"$Created(id, name), offset) => api.$module;format="Camel"$Created(id, name)
    }
  }

  private def entityRef($module;format="camel"$Id: UUID) = entityRefString($module;format="camel"$Id.toString)

  private def entityRefString($module;format="camel"$Id: String) = persistentEntityRegistry.refFor[$module;format="Camel"$Entity]($module;format="camel"$Id)
}
