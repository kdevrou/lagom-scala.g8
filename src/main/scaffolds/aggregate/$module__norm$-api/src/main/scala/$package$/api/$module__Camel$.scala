package $package$.api

import java.util.UUID
import play.api.libs.json.{Format, Json}

case class $name;format="Camel"$(
  id: Option[UUID],
  name: String  // not specifically needed, passed in as example for make$name;format="Camel"$ testing
) {
  def safeId = id.getOrElse(UUID.randomUUID())
}

object $name;format="Camel"$ {
  implicit val format: Format[$name;format="Camel"$] = Json.format
}
