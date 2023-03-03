package controllers

import models.GroceryListItem
import play.api.mvc.{BaseController, ControllerComponents}
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import scala.collection.mutable.ListBuffer

@Singleton
class GroceryListController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val groceryList = new ListBuffer[GroceryListItem]()
  groceryList += GroceryListItem("Chocolate", 2)
  groceryList += GroceryListItem("Cheese", 2.5)
  def getAll() = Action {
    if (groceryList.isEmpty) {
      NoContent
    } else {
      Ok(Json.toJson(groceryList))
    }
  }
}
