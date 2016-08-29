package fr.william.tondeuses

import scala.io.Source

import fr.william.model.Lawn
import fr.william.model.Mower
import fr.william.model.Program
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer

class FileReader {
  private val validMowSequenceCharacters = List('A','G','D')
  private val validOrientations = List('N','E','W','S')
  private var mowers = ListBuffer[Mower]()
  private var instructions = ListBuffer[String]()
  
  def createProgramFromFile(file:Array[String]) : Program = 
  {
    //The first line should be the the lawn coordinates.
    val lawnCoordinatesRow = file(0).split(" ")
    val lawn = createLawn(lawnCoordinatesRow)
    
    //We drop the line where the lawn coordinates are. We assume that we wont have more lawn coordinates anymore.
    val mowersArray = file.slice(1,file.size)
    createMowers(mowersArray)

    val program = new Program(lawn,mowers)
    program
  }
  
  def createLawn(lawnCoordinates:Array[String]) : Lawn = 
  {
    if (isValidLawnCoordinates(lawnCoordinates))
      new Lawn(lawnCoordinates(0).toInt,lawnCoordinates(1).toInt) 
    else
      throw new IllegalStateException("The file must start with the lawn coordinates.")
  }
  
  /**
    * Check if the array contains 2 elements :
    *     - A number
    *     - A number
 		* @param lawnCoordinates
 		* @return
 		*/
  def isValidLawnCoordinates(lawnCoordinates:Array[String]) : Boolean = 
  {
    if(lawnCoordinates.size == 2) 
    {
      val xCoordinate = lawnCoordinates(0)
      val yCoordinate = lawnCoordinates(1)

      isAllDigit(xCoordinate) && isAllDigit(yCoordinate)
    } 
    else
      false
  }  
  
  def isAllDigit(numbers:String): Boolean = numbers.forall { number => Character.isDigit(number) }  
  
  def createMowers(mowersArray:Array[String]) : Unit = 
  {
    for (line <- mowersArray)
    {
      //For each line, we create an array of entries where each entries are separated by a " ", so for a mower coordinates line we should have Array(1,2,N)
      val array = line.split(" ")
      array match 
      {
        case array if isValidMowerRow(array) => mowers.append(new Mower(array(0).toInt,array(1).toInt,array(2)))
        case array if isValidMowerInstruction(array) => instructions.append(array(0))
        case _ => throw new IllegalStateException("The file format should be : \nNumber Number\nNumber Number Letter(N,E,W,S)\nLetters(A,D,G) eg : AGAAGDAD")
      }
    }
    
    if(mowers.size == instructions.size) 
    {
      setMowersInstructions()
    } else
      throw new IllegalStateException("You must have the same number of mowers and instructions.")
  }
  
  /**
    * Check if the array contain 3 elements :
    *     - A Number
    *     - A Number
    *     - An Orientation : (N,E,W,S) 
 		* @param row
 		* @return
 		*/
  def isValidMowerRow(row:Array[String]) : Boolean =
  {
    if(row.size == 3) 
    {
      val xCoordinate = row(0)
      val yCoordinate = row(1)
      val orientation = row(2)

      isAllDigit(xCoordinate) && isAllDigit(yCoordinate) && isValidOrientation(orientation)
    } 
    else
      false
    }  

  def isValidOrientation(oritentation:String):Boolean = 
    oritentation.size == 1 && oritentation.forall { char => Character.isAlphabetic(char) && validOrientations.contains(char) }  
  
  /**
    * Check if the array contains 1 element :
    *     - A sequence of characarters without spaces
    *     - A given character must be A or G or D 
 		* @param row
 		* @return
 		*/
  def isValidMowerInstruction(row:Array[String]) : Boolean = 
  {
    if(row.size == 1)
      row(0).forall {char => Character.isAlphabetic(char) && isValidMowerSequenceCharacter(char)}
    else
      false
  }  
 
  def isValidMowerSequenceCharacter(character:Char) : Boolean = validMowSequenceCharacters.contains(character)  
  
  def setMowersInstructions() 
  {
    var index = 0
    
    mowers.foreach { mower => 
      mower.instructions = instructions(index)
      index +=1
    }
  }
}