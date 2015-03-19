package info.atende.notify

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.jquery.{JQuery, jQuery}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
/**
 * @author Giovanni Silva.
 */
@JSExport
object Notify {
  val ROOT_ID = "v-notifications-tray"
  val DEFAULT_DELAY = 400
  var animationEnabled = true
  var root: JQuery = _

  def show(message: String, title: String = "", delay: Int = DEFAULT_DELAY, status: Status.Status = Status.INFO,
            position: Position.Position = Position.TOP_CENTER): Unit = {
    root = createRootNode()
    _setupTrayPosition(position)
    appendPar(root, message, status)
  }

  def appendPar(targetNode: JQuery, text: String, status: Status.Status): Unit = {
    val divNode = jQuery("<div />")
    divNode.click((e:js.Any)=> applyHideAnimation(divNode))
    val parNode = document.createElement("p")
    divNode.addClass("v-notification alert-box v-notification-tray tray radius")
    divNode.addClass(status.toString.toLowerCase)
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    divNode.append(parNode)
    divNode.appendTo(targetNode)
  }

  def applyHideAnimation(element: JQuery): Unit = {
    if(animationEnabled)
      element.hide(DEFAULT_DELAY, {()=> element.remove()})
    else
      element.remove()
  }

  def createRootNode(): JQuery = {
    var root = jQuery(s"#$ROOT_ID")
    if(root.length == 0){
      root = jQuery("<div/>", js.Dynamic.literal(id = ROOT_ID))
      root.appendTo(document.body)
    }
    root
  }

  private def _setupTrayPosition(position: Position.Position) {
    val _trayRoot = root
    _trayRoot.removeClass()
    position match {
      case Position.TOP_RIGHT =>
      _trayRoot.addClass("v-top-right")
      case Position.TOP_LEFT =>
        _trayRoot.addClass("v-top-left")
      case Position.TOP_CENTER =>
        _trayRoot.addClass("v-top-center")
      case Position.BOTTOM_RIGHT =>
        _trayRoot.addClass("v-bottom-right")
      case Position.BOTTOM_LEFT =>
        _trayRoot.addClass("v-bottom-left")
      case Position.BOTTOM_CENTER =>
        _trayRoot.addClass("v-bottom-center")
    }
  }
}
object Status extends Enumeration {
  type Status = Value
  val WARNING, INFO, SUCCESS, ERROR = Value
}

object Position extends Enumeration {
  type Position = Value
  val TOP_RIGHT,TOP_LEFT,TOP_CENTER,BOTTOM_RIGHT,BOTTOM_LEFT,BOTTOM_CENTER = Value
}
