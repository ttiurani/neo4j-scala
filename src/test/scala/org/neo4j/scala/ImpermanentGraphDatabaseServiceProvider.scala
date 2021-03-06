package org.neo4j.scala

import java.util.{HashMap => jMap}
import org.neo4j.test.ImpermanentGraphDatabase
import java.util.{HashMap => jMap}
import scala.collection.JavaConversions.mapAsJavaMap
import org.neo4j.test.TestGraphDatabaseFactory
import org.neo4j.server.database.GraphDatabaseFactory

/**
 * provides a specific Database Service
 * in this case an impermanent database service
 */
trait ImpermanentGraphDatabaseServiceProvider {
  
  /**
   * setup configuration parameters
   * @return Map[String, String] configuration parameters
   */
  def configParams = Map[String, String]()

  /**
   * Graph Database Factory to use to create the new
   */
  def testGraphDatabaseFactory: TestGraphDatabaseFactory

  /**
   * using an instance of an impermanent graph database
   */
  val ds: DatabaseService = {
    import collection.JavaConversions.mapAsJavaMap
    DatabaseServiceImpl(
        testGraphDatabaseFactory
        .newImpermanentDatabaseBuilder
        .setConfig(new jMap[String, String](configParams)).newGraphDatabase())
  }
}