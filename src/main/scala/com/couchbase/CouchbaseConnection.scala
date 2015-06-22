package com.couchbase

import com.couchbase.client.java.CouchbaseCluster
import com.couchbase.client.java.document.json.JsonObject
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper
import net.liftweb.json.parse
import net.liftweb.json.Extraction.decompose
import net.liftweb.json.JsonAST.render
import net.liftweb.json.compact
import net.liftweb.json.DefaultFormats
import com.couchbase.client.java.document.JsonDocument

/**
 * This class provides connection with couchbase,
 * stores object in couchbase using JSON document
 *
 * @author ayush
 */

/**
 * Person related information
 */
case class Person(name: String, age: Int, email: String)

object CouchbaseConnection {

  // Create a cluster reference
  val cluster = CouchbaseCluster.create("127.0.0.1")
  // Open a bucket person
  val bucket = cluster.openBucket("person")
}

trait LiftJsonHelper extends Serializable {
  @transient implicit val formats = new DefaultFormats { outer =>
    override val typeHintFieldName = "type"
  }
}

object PersonDB extends LiftJsonHelper {

  def storePerson(person: Person): Person = {
    val doc = JsonObject.fromJson(compact(render(decompose(person))))
    val result = CouchbaseConnection.bucket.upsert(JsonDocument.create("101", doc))
    val json = result.content().toString()
    parse(json).extract[Person]
  }

  def fetchPerson: Person = {
    val result = CouchbaseConnection.bucket.get("101")
    parse(result.content().toString()).extract[Person]
  }
}