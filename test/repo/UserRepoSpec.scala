

import models.{User, UserRepo}
import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by knoldus on 14/3/16.
  */

class UserRepoSpec extends Specification {

  sequential

  def userRepo(implicit app: Application) = Application.instanceCache[UserRepo].apply(app)

  "User repository" should {


    "get all record" in new WithApplication {
      val result = userRepo.getAll
      val response = Await.result(result, Duration.Inf)
      response.head === User("rishabh","rishabh@gmail.com","kanpur","35555555","98998989",1)
    }

    "delete record by id" in new WithApplication{

      val result = userRepo.deleteUser(1)
      val response = Await.result(result,Duration.Inf)
      response === 1
    }

    "get by id" in new WithApplication {

      val result = userRepo.getUserById(1)
      val response = Await.result(result,Duration.Inf)
      response.head.name === "rishabh"

    }

  }


}
