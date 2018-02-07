package $package$.impl

import java.util.UUID
import $package$.api
import $package$.api.{$name;format="Camel"$Service}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}

/**
  * Implementation of the $name;format="Camel"$Service.
  */
class $name;format="Camel"$ServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends $name;format="Camel"$Service {

  override def get$name;format="Camel"$(id: UUID) = ServiceCall { _ =>
    // Look up the $name$ entity for the given ID.
    val ref = persistentEntityRegistry.refFor[$name;format="Camel"$Entity](id)

    // Ask the entity the Hello command.
    ref.ask(Hello(id))
  }

  override def make$name;format="Camel"$ = ServiceCall { request =>
    // Look up the $name$ entity for the given ID.
    val ref = persistentEntityRegistry.refFor[$name;format="Camel"$Entity](id)

    // Tell the entity to use the greeting message specified.
    ref.ask($name;format="Camel"$(request.message))
  }

  override def $name;format="camel"$Topic(): Topic[api.$name;format="Camel"$Event] =
    TopicProducer.singleStreamWithOffset {
      fromOffset =>
        persistentEntityRegistry.eventStream($name;format="Camel"$Event.Tag, fromOffset)
          .map(ev => (convertEvent(ev), ev.offset))
    }

  private def convertEvent($name;format="camel"$Event: EventStreamElement[$name;format="Camel"$Event]): Future[(api.$name;format="Camel"$Event, Offset)] = {
    eventStreamElement match {
      case $name;format="Camel"$Created(msg) => api.GreetingMessageChanged(helloEvent.entityId, msg)
    }
  }
}
