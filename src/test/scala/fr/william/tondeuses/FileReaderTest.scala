package fr.william.tondeuses

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Matchers._
import org.scalatest.FlatSpec

class FileReaderTest extends FlatSpec{
  
  val instance = new FileReader

  "An array with valid instructions" should "return a valid program object" in {
    val instructions = Array("5 5","1 2 N","GAGAGAGAA","3 3 E","AADAADADDA")
    
    val program = instance.createProgramFromFile(instructions)
    
    program.lawn.xSummit should be (5)
    program.lawn.ySummit should be (5)
    
    program.mowers.size should be (2)
    program.mowers(0).xCoordinate should be (1)
    program.mowers(0).yCoordinate should be (2)
    program.mowers(0).orientation should be ("N")
    program.mowers(0).instructions should be ("GAGAGAGAA")
    
    program.mowers(1).xCoordinate should be (3)
    program.mowers(1).yCoordinate should be (3)
    program.mowers(1).orientation should be ("E")
    program.mowers(1).instructions should be ("AADAADADDA")    
    
  }

  "An array with an invalid mower coordinate" should "throw an IllegalStateException" in {
    val instructions = Array("5 5","1 N","ADDAADDAAD","4 3 N","ADDAAGAAD")
    
    intercept[IllegalStateException] {
      instance.createProgramFromFile(instructions)
    }
  }
  
  "An array with two lawn coordinates" should "throw an IllegalStateException" in {
    val instructions = Array("5 5","1 1","1 2 N","ADDAADDAAD","4 3 N","ADDAAGAAD")
    intercept[IllegalStateException] {
      instance.createProgramFromFile(instructions)
    }
  }  
  
  "An isValidLawnRow call with a string array containing digits" should "return true" in 
  {
    val lawnCoordinates = Array("1", "2")
    instance.isValidLawnCoordinates(lawnCoordinates) should be (true)
  }  
  
  "An isValidLawnRow call with a string array containing one digit" should "return false" in 
  {
    val invalidLawnCoordinates = Array("1")
    instance.isValidLawnCoordinates(invalidLawnCoordinates) should be (false)
  }  
  
  "An isValidMowRow call with a string array containing two digits and 'N' "should "return true" in 
  {
    val mowSpawnCoordinates = Array("1","2","N")
    instance.isValidMowerRow(mowSpawnCoordinates) should be (true)
  }
  
  "An isValidMowRow call with one digit and 'N'" should "return false" in 
  {
    val mowSpawnCoordinates = Array("1","N")
    instance.isValidMowerRow(mowSpawnCoordinates) should be (false)
  }  
  
  "An isValideMowerInstruction call with a string array containing 'GAGAGAGAA' " should "return true" in 
  {
    val mowSequence = Array("GAGAGAGAA")
    instance.isValidMowerInstruction(mowSequence) should be (true)
  }
  
   "An isValideMowerInstruction call with a string array containing 'FFFFF' " should "return false" in 
   {
    val mowSequence = Array("FFFFF")
    instance.isValidMowerInstruction(mowSequence) should be (false)
   }  
  
}