ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "akka_http_example",
    idePackagePrefix := Some("com.example.akka_http"),
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.5",
      "org.slf4j" % "slf4j-simple" % "1.7.5",

      "com.typesafe.akka" % "akka-actor-typed_2.13" % "2.6.14",
      "com.typesafe.akka" % "akka-stream-typed_2.13" % "2.6.14",
      "com.typesafe.akka" % "akka-http_2.13" % "10.2.4",

      "com.typesafe.akka" % "akka-http-spray-json_2.13" % "10.2.4",

      "ch.megard" % "akka-http-cors_2.13" % "1.1.1",

      "com.typesafe.slick" %% "slick" % "3.3.3",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
      "com.github.tminglei" %% "slick-pg" % "0.20.3",
      "com.github.tminglei" %% "slick-pg_play-json" % "0.20.3",

      "org.postgresql" % "postgresql" % "42.3.4"
    )
  )
