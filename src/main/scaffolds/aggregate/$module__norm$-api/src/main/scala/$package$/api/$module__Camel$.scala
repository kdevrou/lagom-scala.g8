package $package$.api

import java.util.UUID
import play.api.libs.json.{Format, Json}

case class $module;format="Camel"$(
  id: Option[UUID],
  name: String  // not specifically needed, passed in as example for make$module;format="Camel"$ testing
) {
  def safeId = id.getOrElse(UUID.randomUUID())
}

object $module;format="Camel"$ {
  implicit val format: Format[$module;format="Camel"$] = Json.format
}
