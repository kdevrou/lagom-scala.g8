package $package$.impl

import java.util.UUID
import akka.Done
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventTag, PersistentEntity}
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}
import play.api.libs.json.{Format, Json}

import scala.collection.immutable.Seq

/**
  * This is an event sourced entity. It has a state, [[$module;format="Camel"$State]], which
  * stores what the greeting should be (eg, "Hello").
  *
  * Event sourced entities are interacted with by sending them commands. This
  * entity supports two commands, a [[UseGreetingMessage]] command, which is
  * used to change the greeting, and a [[Hello]] command, which is a read
  * only command which returns a greeting to the name specified by the command.
  *
  * Commands get translated to events, and it's the events that get persisted by
  * the entity. Each event will have an event handler registered for it, and an
  * event handler simply applies an event to the current state. This will be done
  * when the event is first created, and it will also be done when the entity is
  * loaded from the database - each event will be replayed to recreate the state
  * of the entity.
  *
  * This entity defines one event, the [[GreetingMessageChanged]] event,
  * which is emitted when a [[UseGreetingMessage]] command is received.
  */
class $module;format="Camel"$Entity extends PersistentEntity {

  override type Command = $module;format="Camel"$Command[_]
  override type Event = $module;format="Camel"$Event
  override type State = Option[$module;format="Camel"$State]

  /**
    * The initial state. This is used if there is no snapshotted state to be found.
    */
  override def initialState: $module;format="Camel"$State = None

  /**
    * An entity can define different behaviours for different states, so the behaviour
    * is a function of the current state to a set of actions.
    */
  override def behavior: Behavior = {
    case Some($module;format="Camel"$State) => Actions().onCommand[Create$module;format="Camel"$, Done] {
      case (Create$module;format="Camel"$(name), ctx, state) =>
        ctx.thenPersist($module;format="Camel"$Created(name)) { _ =>
          ctx.reply(Done)
        }
    }.onEvent {
      case ($module;format="Camel"$Created(name), state) =>
        $module;format="Camel"$State(newMessage, LocalDateTime.now().toString)
//    }.onReadOnlyCommand[UUID, String] {
//      case (Hello(name), ctx, state) =>
//      ctx.reply(s"\$message, \$module!")
    }
  }
}

/**
  * The current state held by the persistent entity.
  */
case class $module;format="Camel"$State(name: String)

object $module;format="Camel"$State {
    implicit val format: Format[$module;format="Camel"$State] = Json.format
}

/**
  * Events that the $module$ entity supports
  */
sealed trait $module;format="Camel"$Event extends AggregateEvent[$module;format="Camel"$Event] {
  def aggregateTag = $module;format="Camel"$Event.Tag
}

object $module;format="Camel"$Event {
  val Tag = AggregateEventTag[$module;format="Camel"$Event]
}

/**
  * An event that records the creation of $module$
  */
case class $module;format="Camel"$Created(id: UUID, name: String) extends $module;format="Camel"$Event

object $module;format="Camel"$Created {

  implicit val format: Format[$module;format="Camel"$Created] = Json.format
}

/**
  * Commands that the $module$ entity supports
  */
sealed trait $module;format="Camel"$Command[R] extends ReplyType[R]

/**
  * A command to create a $module$
  */
case class Create$module;format="Camel"$(id UUID, name: String) extends $module;format="Camel"$Command[Done]

object Create$module;format="Camel"$ {
  implicit val format: Format[Create$module;format="Camel"$] = Json.format
}

/**
  * Serializer registry for $module$
  */
object $module;format="Camel"$SerializerRegistry extends JsonSerializerRegistry {
  override def serializers: Seq[JsonSerializer[_]] = Seq(
    JsonSerializer[$module;format="Camel"$Created],
    JsonSerializer[Create$module;format="Camel"$],
    JsonSerializer[$module;format="Camel"$State]
  )
}
