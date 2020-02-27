buildInfoKeys := Seq[BuildInfoKey](
  startYear,
  organization,
  organizationName,
  organizationHomepage,
  scalacOptions,
  javacOptions,
  version,
  scalaVersion,
  sbtVersion,
  sbtBinaryVersion,
  git.gitCurrentTags,
  git.gitDescribedVersion,
  git.gitCurrentBranch,
  git.gitHeadCommit,
  git.gitHeadCommitDate)

import Dependencies._

buildInfoKeys ++= Seq(
  "versionScala212" -> versionScala212,
  "versionScala213" -> versionScala213,
  "versionScalaXml" -> versionScalaXml,
  "versionScalaCollectionCompat" -> versionScalaCollectionCompat,
  "versionJava8Compat" -> versionJava8Compat,
  "versionScalameta" -> versionScalameta,
  "versionScalatest" -> versionScalatest,
  "versionAkka" -> versionAkka,
  "versionAkkaManagement" -> versionAkkaManagement,
  "versionAkkaHttp" -> versionAkkaHttp,
  "versionAkkaHttpCors" -> versionAkkaHttpCors,
  "versionAlpakka" -> versionAlpakka,
  "versionAlpakkaKafka" -> versionAlpakkaKafka,
  "versionAkkaPersistenceCassandra" -> versionAkkaPersistenceCassandra,
  "versionAkkaPersistenceJdbc" -> versionAkkaPersistenceJdbc,
  "versionCassandra" -> versionCassandra,
  "versionElastic4s" -> versionElastic4s,
  "versionConfig" -> versionConfig,
  "versionPureconfig" -> versionPureconfig,
  "versionGuice" -> versionGuice,
  "versionKamon" -> versionKamon,
  "versionKanela" -> versionKanela,
  "versionUuidGenerator" -> versionUuidGenerator,
  "versionHanlp" -> versionHanlp,
  "versionSlick" -> versionSlick,
  "versionSlickPg" -> versionSlickPg,
  "versionPoi" -> versionPoi,
  "versionQuartz" -> versionQuartz,
  "versionBcprovJdk15on" -> versionBcprovJdk15on,
  "versionNacos" -> versionNacos,
  "versionJsch" -> versionJsch,
  "versionJakartaMail" -> versionJakartaMail,
  "versionHikariCP" -> versionHikariCP,
  "versionMybatisPlus" -> versionMybatisPlus,
  "versionLombok" -> versionLombok,
  "versionMySQL" -> versionMySQL,
  "versionPostgres" -> versionPostgres,
  "versionRequests" -> versionRequests,
  "versionFastparse" -> versionFastparse,
  "versionOsLib" -> versionOsLib,
  "versionMongoScalaBson" -> versionMongoScalaBson,
  "versionMongoDriverReactivestreams" -> versionMongoDriverReactivestreams,
  "versionBson" -> versionBson,
  "versionKafka" -> versionKafka,
  "versionAlpnAgent" -> versionAlpnAgent,
  "versionLogback" -> versionLogback,
  "versionScalaLogging" -> versionScalaLogging,
  "versionLogstashLogback" -> versionLogstashLogback,
  "versionJwt" -> versionJwt,
  "versionJson4s" -> versionJson4s,
  "versionScalapbJson4s" -> versionScalapbJson4s)
