
import scala.collection.mutable
import ujson._
import scalaj.http._

import scala.collection.mutable.ArrayBuffer

class Connect(hostUrl:String = "http://localhost:9000", endpoint:String = "/graphql") {

  private var currentSchemaNameTypes:mutable.HashMap[String,String] = mutable.HashMap[String,String]()

  def url:String = hostUrl + endpoint

  def schemaNameTypes:mutable.HashMap[String,String] = this.currentSchemaNameTypes

  def fetchSchema: Js.Value = this.tapConnect(buildQuery("schema")) match {
      case None => Js.Arr()
      case Some(json) =>
        //println(json)
        val currentSchema:ArrayBuffer[Js.Value] = json("data")("__schema")("queryType")("fields").arr
        currentSchema.foreach { field =>
          println(field)
          val f = field.asInstanceOf[Js.Obj]
          this.currentSchemaNameTypes += (f("name").str -> f("type")("ofType")("name").str)
        }
        currentSchema
  }

  def analyseText(queryName:String,text:String): Js.Value = tapConnect(buildQueryWithInput(queryName,text)).getOrElse(Js.Obj())

  def query(name:String):String = {
    if(schemaNameTypes.contains(name)) {
      Queries.query(name)
    } else ""
  }

  private def tapConnect(query:Js.Value):Option[Js.Value] = try {
    val request:HttpRequest = Http(this.url).postData(write(query)).header("content-type", "application/json")
    val response = request.asString
    //println(response)
    Some(read(response.body))
  } catch {
    case e:Exception =>
      System.err.print(e.getMessage)
      None
  }

  private def buildQuery(name:String):Js.Obj = Js.Obj("query" -> Js.Str(Queries.query(name)))

  private def buildQueryWithInput(name:String,input:String):Js.Obj = Js.Obj(
    "query" -> Js.Str(Queries.query(name)),
    "variables" -> Js.Obj("input" -> Js.Str(input))
  )

}

