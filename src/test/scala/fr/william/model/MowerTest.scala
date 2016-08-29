package fr.william.model

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Matchers._
import org.scalatest.FlatSpec

class MowerTest extends FlatSpec {
  
  "A (0,1,N) mower mowing in a (5,5) Lawn " should "have (0,2,N) coordinates" in {
    val tondeuse = new Mower(0,1,"N")
    val terrain = new Lawn(5,5)
    tondeuse.moveForward(terrain)
    
    tondeuse.yCoordinate should be (2)
    tondeuse.xCoordinate should be (0)
    tondeuse.orientation should be ("N")
  }
 
  "A (0,5,N) mower moving in a (5,5) Lawn" should "have (0,5,N) coordinates" in {
    val tondeuse = new Mower(0,5,"N")
    val terrain = new Lawn(5,5)
    tondeuse.moveForward(terrain)
    
    tondeuse.yCoordinate should be (5)
    tondeuse.xCoordinate should be (0)
    tondeuse.orientation should be ("N")
  }  
  
  "A (1,0,E) mower moving in a (5,5) Lawn" should "have (2,0,E) coordinates" in  {
    val tondeuse = new Mower(1,0,"E")
    val terrain = new Lawn(5,5)
    tondeuse.moveForward(terrain)
    
    
    tondeuse.xCoordinate should be (2)
    tondeuse.yCoordinate should be (0)
    tondeuse.orientation should be ("E")
  }  
  
  "A (5,0,E) mower moving in a (5,5) Lawn" should "have (5,0,E) coordinates" in  {
    val tondeuse = new Mower(5,0,"E")
    val terrain = new Lawn(5,5)
    tondeuse.moveForward(terrain)
    
    tondeuse.xCoordinate should be (5)
    tondeuse.yCoordinate should be (0)
    tondeuse.orientation should be ("E")
  }   
  
  "A (1,0,E) mower in a (5,5) Lawn turning left" should "have (5,0,N) coordinates" in  {
    val tondeuse = new Mower(5,0,"E")
    val terrain = new Lawn(5,5)
    tondeuse.turnLeft()
    
    tondeuse.xCoordinate should be (5)
    tondeuse.yCoordinate should be (0)
    tondeuse.orientation should be ("N")
  }
  
  "A (1,0,E) mower in a (5,5) Lawn turning right" should "have (5,0,S) coordinates" in  {
    val tondeuse = new Mower(5,0,"E")
    val terrain = new Lawn(5,5)
    tondeuse.turnRight()
    
    tondeuse.xCoordinate should be (5)
    tondeuse.yCoordinate should be (0)
    tondeuse.orientation should be ("S")
  }    
    
  "A (1,0,E) mower in a (5,5) Lawn receiving GAGAGAGAA instructions" should "have (1,3,S) coordinates" in {
    val tondeuse = new Mower(1,2,"N","GAGAGAGAA")
    val terrain = new Lawn(5,5)
    tondeuse.executeInstructions(terrain)
    
    tondeuse.xCoordinate should be (1)
    tondeuse.yCoordinate should be (3)
    tondeuse.orientation should be ("N")
  }
}