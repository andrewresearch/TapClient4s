
name := "tapclient4s"

version := "0.1.2"

scalaVersion := "2.12.4"

organization := "io.heta"

libraryDependencies += "com.lihaoyi" %% "ujson" % "0.6.5"
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"

bintrayOrganization := Some("heta")
bintrayRepository := "heta-tap"
licenses += ("Apache-2.0", url("https://opensource.org/licenses/Apache-2.0"))
credentials += Credentials(Path.userHome / ".bintray" / ".credentials")