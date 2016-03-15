
package models


import com.google.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future


/**
  * Created by kunal on 8/3/16.
  */


case class User (name:String,email:String,address:String , mobile:String, emergency:String,id:Int=0)

class UserRepo @Inject()(protected val dbConfigProvider:DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] with UserTable {

  import driver.api._

  def getAll: Future[List[User]] = {
    db.run {
      userTable.to[List].result
    }
  }

  def add(name: String, email: String, address: String, mobile: String, emergency: String): Unit = {
    db.run {
      userTable += User(name, email, address, mobile, emergency)
    }
  }


  def create() = {
    db.run {
      userTable.schema.create
    }
  }

  def updateUser(id: Int, name: String, email: String, address: String, mobile: String, emergency: String): Unit = {
    db.run {
      userTable.filter(_.id === id).update(User(name, email, address, mobile,emergency,id))
    }

  }

  def deleteUser(id: Int): Future[Int]= {
    db.run {userTable.filter {_.id === id}.delete
    }
  }

  def getUserById(uid:Int):Future[Option[User]]={
    db.run {userTable.filter(_.id === uid).result.headOption}
  }
}

protected trait UserTable  {
  self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

 protected class UserTable(tag:Tag) extends Table[User](tag,"users"){
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name", O.SqlType("VARCHAR(20)"))
    val email = column[String]("email", O.SqlType("VARCHAR(20)"))
    val address = column[String]("address", O.SqlType("VARCHAR(20)"))
    val mobile = column[String]("mobile", O.SqlType("VARCHAR(10)"))
    val emergency = column[String]("emergency", O.SqlType("VARCHAR(50)"))
    def * = (name,email,address,mobile,emergency,id) <>(User.tupled, User.unapply)

  }
  lazy val userTable = TableQuery[UserTable]
  //  db.run{userTable.returning(userTable.map(_.id)) += User(1,"kunal","k@k.com","kunal","9999",true)}
}



