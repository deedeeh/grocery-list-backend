package controllers

import models.GroceryListItem
import play.api.mvc.{BaseController, ControllerComponents}
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import scala.collection.mutable.ListBuffer

@Singleton
class GroceryListController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val groceryList = new ListBuffer[GroceryListItem]()

  def getItems() = Action {
    if (groceryList.isEmpty) {
      println("Sorry no items yet!")
      NoContent
    } else {
      Ok(Json.toJson(groceryList))
    }
  }

  def createItem() = Action { implicit request =>
    val response = request.body
    val jsonObject = response.asJson
    val groceryListItem: Option[GroceryListItem] =
      jsonObject.flatMap(Json.fromJson[GroceryListItem](_).asOpt)

    groceryListItem match {
      case Some(newItem) =>
        val item = GroceryListItem(newItem.item, newItem.price)
        groceryList += item
        println(item)
        Ok(Json.toJson(item))
      case None => BadRequest
    }
  }

}
