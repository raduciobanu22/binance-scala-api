organization := "com.olvind"
name := "binance-scala-api"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.squareup.retrofit2" % "retrofit" % "2.3.0",
  "commons-codec" % "commons-codec" % "1.10",
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test
) ++ Seq("core", "generic", "parser").map(s => "io.circe" %% s"circe-$s" % "0.9.0")
