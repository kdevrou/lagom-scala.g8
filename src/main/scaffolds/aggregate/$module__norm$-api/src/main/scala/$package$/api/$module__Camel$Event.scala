package $package$.api

import play.api.libs.json.{Format, Json}
import java.util.UUID

sealed trait $module;format="Camel"$Event {
  val id: UUID
}

object $module;format="Camel"$Event {
  implicit val format: Format[$module;format="Camel"$Event] =
    derived.flat.oformat((__ \ "type").format[String])
}

// add events here as they should be exposed to clients of the service
// these will generally be upconverted from similar persistent events
