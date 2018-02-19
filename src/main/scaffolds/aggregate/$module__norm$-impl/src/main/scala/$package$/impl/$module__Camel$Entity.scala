package $package$.impl

import java.util.UUID
import akka.Done
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventTag, PersistentEntity}
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}
import play.api.libs.json.{Format, Json}

import scala.collection.immutable.Seq

class $module;format="Camel"$Entity extends PersistentEntity {

  override type Command = $module;format="Camel"$Command[_]
  override type Event = $module;format="Camel"$Event
  override type State = Option[$module;format="Camel"$State]

  /**
    * The initial state. This is used if there is no snapshotted state to be found.
    */
  override def initialState: Option[$module;format="Camel"$State] = None

  /**
    * An entity can define different behaviours for different states, so the behaviour
    * is a function of the current state to a set of actions.
    */
  override def behavior: Behavior = {
    case Some($module;format="Camel"$State) =>
      Actions().onReadOnlyCommand[Create$module;format="Camel"$, Option[$module;format="Camel"$State]] {
        case (Get$module;format = "Camel" $, ctx, state) => ctx.reply (state)
      }.onReadOnlyCommand[Create$module;format="Camel"$, Done] {
        case (Create$module;format="Camel"$(id, name), ctx, state) => ctx.invalidCommand("$module;format="Camel"$ already exists")
      }
    case None =>
      Actions().onReadOnlyCommand[Create$module;format="Camel"$, Option[$module;format="Camel"$State]] {
       case (Get$module;format = "Camel" $, ctx, state) => ctx.reply (state)
      }.onCommand[Create$module;format="Camel"$, Done] {
        case (Create$module;format="Camel"$(id, name), ctx, state) => ctx.thenPersist($module;format="Camel"$Created(id, name))(_ => ctx.reply(Done))
      }.onEvent {
        case ($module;format="Camel"$Created(id, name), state) => Some($module;format="Camel"$State(id, name))
      }
  }
}

/**
  * The current state held by the persistent entity.
  */
case class $module;format="Camel"$State(id: UUID, name: String)

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
case class Create$module;format="Camel"$(id: UUID, name: String) extends $module;format="Camel"$Command[Done]

object Create$module;format="Camel"$ {
  implicit val format: Format[Create$module;format="Camel"$] = Json.format
}
/**
  * A command to get a $module$
  */
case class Get$module;format="Camel"$(id : UUID) extends $module;format="Camel"$Command[Done] with ReplyType[Option[$module;format="Camel"$State]]

object Get$module;format="Camel"$ {
  implicit val format: Format[Get$module;format="Camel"$] = Json.format
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
