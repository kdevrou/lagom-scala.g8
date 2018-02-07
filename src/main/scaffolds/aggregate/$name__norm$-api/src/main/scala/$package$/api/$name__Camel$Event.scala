package $package$.api

import java.util.UUID

sealed trait $name;format="Camel"$Event {
  val id: UUID
}

object $name;format="Camel"$Event {
  implicit val format: Format[$name;format="Camel"$Event] =
    derived.flat.oformat((__ \ "type").format[String])
}

// add events here as they should be exposed to clients of the service
// these will generally be upconverted from similar persistent events
