package edu.berkeley.cs.scadr.snippet

import scala.xml.NodeSeq
import net.liftweb.http._
import net.liftweb.util._
import net.liftweb.common._
import S._
import Helpers._
import scala.xml._

import edu.berkeley.cs.scadr.model._

class RegisterUser {
	def register(xhtml: NodeSeq): NodeSeq = {
	  var username = ""
	  var password = ""
	  def process() = {
      try {
        PiqlUser.create(username, password)
        S.notice("User " + username + " created!")
        redirectTo("index")
      } catch {
        case ExistingUsernameException(u) => 
          S.error("general_error", Text("Please pick a different username"))
      }
	  }

	  bind("e", xhtml,
	  		"username" -> SHtml.text(username, (u: String) => username = u),
	  		"password" -> SHtml.text(password, (p: String) => password = p),
	  		"submit" -> SHtml.submit("submit", process))
	}
}
