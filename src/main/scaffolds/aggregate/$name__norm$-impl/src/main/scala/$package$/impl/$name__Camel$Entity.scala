package $package$.impl

import java.util.UUID
import akka.Done
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventTag, PersistentEntity}
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}
import play.api.libs.json.{Format, Json}

import scala.collection.immutable.Seq

/**
  * This is an event sourced entity. It has a state, [[$name;format="Camel"$State]], which
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
class $name;format="Camel"$Entity extends PersistentEntity {

  override type Command = $name;format="Camel"$Command[_]
  override type Event = $name;format="Camel"$Event
  override type State = Option[$name;format="Camel"$State]

  /**
    * The initial state. This is used if there is no snapshotted state to be found.
    */
  override def initialState: $name;format="Camel"$State = None

  /**
    * An entity can define different behaviours for different states, so the behaviour
    * is a function of the current state to a set of actions.
    */
  override def behavior: Behavior = {
    case Some($name;format="Camel"$State) => Actions().onCommand[Create$name;format="Camel"$, Done] {
      case (Create$name;format="Camel"$(name), ctx, state) =>
        ctx.thenPersist($name;format="Camel"$Created(name)) { _ =>
          ctx.reply(Done)
        }
    }.onEvent {
      case ($name;format="Camel"$Created(name), state) =>
        $name;format="Camel"$State(newMessage, LocalDateTime.now().toString)
//    }.onReadOnlyCommand[UUID, String] {
//      case (Hello(name), ctx, state) =>
//      ctx.reply(s"\$message, \$name!")
    }
  }
}

/**
  * The current state held by the persistent entity.
  */
case class $name;format="Camel"$State(name: String)

object $name;format="Camel"$State {
    implicit val format: Format[$name;format="Camel"$State] = Json.format
}

/**
  * Events that the $name$ entity supports
  */
sealed trait $name;format="Camel"$Event extends AggregateEvent[$name;format="Camel"$Event] {
  def aggregateTag = $name;format="Camel"$Event.Tag
}

object $name;format="Camel"$Event {
  val Tag = AggregateEventTag[$name;format="Camel"$Event]
}

/**
  * An event that records the creation of $name$
  */
case class $name;format="Camel"$Created(id: UUID, name: String) extends $name;format="Camel"$Event

object $name;format="Camel"$Created {

  implicit val format: Format[$name;format="Camel"$Created] = Json.format
}

/**
  * Commands that the $name$ entity supports
  */
sealed trait $name;format="Camel"$Command[R] extends ReplyType[R]

/**
  * A command to create a $name$
  */
case class Create$name;format="Camel"$(id UUID, name: String) extends $name;format="Camel"$Command[Done]

object Create$name;format="Camel"$ {
  implicit val format: Format[Create$name;format="Camel"$] = Json.format
}

/**
  * Serializer registry for $name$
  */
object $name;format="Camel"$SerializerRegistry extends JsonSerializerRegistry {
  override def serializers: Seq[JsonSerializer[_]] = Seq(
    JsonSerializer[$name;format="Camel"$Created],
    JsonSerializer[Create$name;format="Camel"$],
    JsonSerializer[$name;format="Camel"$State]
  )
}
