package models

import play.api.libs.json.{Json, OFormat}

case class GroceryListItem(item: String, price: Double)

object GroceryListItem {
  implicit val GroceryListJson: OFormat[GroceryListItem] = Json.format[GroceryListItem]
}

