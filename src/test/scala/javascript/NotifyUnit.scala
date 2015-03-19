package javascript

import info.atende.notify.Notify
import org.scalajs.jquery.jQuery
import utest._

object NotifyUnit extends TestSuite {
  import info.atende.notify.Status

  def tests = TestSuite {
    "should create a default tray notification"- {
      Notify.show("Hello World", delay = 0)

      val ndom = jQuery("#" + Notify.ROOT_ID)
      val firstChild = ndom.children().first()
      assert(ndom != null)
      assert(firstChild.length > 0)
      assert(firstChild.hasClass("alert-box"))
      assert(firstChild.hasClass("v-notification"))
      assert(firstChild.hasClass("v-notification-tray"))
      assert(firstChild.html.contains("<p>Hello World</p>"))

//      val position = ndom.offset().asInstanceOf[js.Dynamic]
//      assert(position.top == 0)
//

//      notification.show("Warning",status: Status.WARNING, delay: 0)
//      assert(ndom.children.length, equals(1))
//      firstNotification = ndom.children.first
//      assert(firstNotification.classes.contains('warning'), isTrue)
//      ndom.children.clear()
//      notification.show('',status: Status.ERROR, delay: 0)
//      assert(ndom.children.first.classes.contains('alert'), isTrue)
//      ndom.children.clear()
//      notification.show('',status: Status.INFO, delay: 0)
//      assert(ndom.children.first.classes.contains('info'), isTrue)
//      ndom.children.clear()
//      notification.show('',status: Status.SUCCESS, delay: 0)
//      assert(ndom.children.first.classes.contains('success'), isTrue)

      assert(jQuery(s"#${Notify.ROOT_ID} div:contains('Hello')").length == 1)
      firstChild.remove()
    }
    "should close the notification on click"- {
      Notify.show("Hello World", delay = 0)
      val ndom = jQuery("#" + Notify.ROOT_ID)
      val firstChild = ndom.children().first()
      firstChild.click()
      assert(ndom.children().length == 0)
      val nf = jQuery(".alert-box")
      assert(nf.length == 0)
      ndom.children().remove()

    }
    "should create a warning notification"-{
      Notify.show("Warning", status = Status.WARNING, delay = 0)
      val ndom = jQuery("#" + Notify.ROOT_ID)
      assert(ndom.children.length == 1)
      val firstNotification = ndom.children.first
      assert(firstNotification.hasClass("warning"))
      firstNotification.remove()
    }
    "should create a info notification"-{
      Notify.show("Warning", status = Status.INFO, delay = 0)
      val ndom = jQuery("#" + Notify.ROOT_ID)
      assert(ndom.children.length == 1)
      val firstNotification = ndom.children.first
      assert(firstNotification.hasClass("info"))
      firstNotification.remove()
    }

    "should create a success notification"-{
      Notify.show("Warning", status = Status.SUCCESS, delay = 0)
      val ndom = jQuery("#" + Notify.ROOT_ID)
      assert(ndom.children.length == 1)
      val firstNotification = ndom.children.first
      assert(firstNotification.hasClass("success"))
      firstNotification.remove()
    }

    "should create a error notification"-{
      Notify.show("Warning", status = Status.ERROR, delay = 0)
      val ndom = jQuery("#" + Notify.ROOT_ID)
      assert(ndom.children.length == 1)
      val firstNotification = ndom.children.first
      assert(firstNotification.hasClass("error"))
      firstNotification.remove()
    }
  }
}