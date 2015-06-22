package com.couchbase

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
 * This class contains test-cases to connect with couchbase,
 * store data in couchbase and
 * fetch data from couchbase
 */
@RunWith(classOf[JUnitRunner])
class CouchbaseConnectionTest extends FunSuite {

  test("One should be able to store data in couchbase") {
    val person = Person("knoldus", 3, "info@knoldus.com")
    assert(PersonDB.storePerson(person) === person)
  }

  test("One should be able to fetch data from couchbase") {
    val person = Person("knoldus", 3, "info@knoldus.com")
    assert(PersonDB.fetchPerson === person)
  }
}