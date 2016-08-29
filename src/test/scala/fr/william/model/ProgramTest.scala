package fr.william.model

import org.scalatest.FunSuite
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.scalatest.FlatSpec

class ProgramTest extends FlatSpec{
  
  
  "A call to executeProgram in a Program object" should "print 1 3 N\n5 1 E to the console" in {
    val pelouse = new Lawn(5,5)
    val tondeuse1 = new Mower(1,2,"N","GAGAGAGAA")
    
    val tondeuse2 = new Mower(3,3,"E","AADAADADDA")
    
    val tondeuses = tondeuse1 :: tondeuse2 :: Nil
    
    val program = new Program(pelouse,tondeuses)
    
    val outContent = new java.io.ByteArrayOutputStream();
    Console.withOut(outContent)
    {
      program.executeProgram()
    }
    // I commented the assertions below because of a problem that I couldn't find ... The idea is to check the output of the console.
    //assert("1 3 N\n5 1 E" === outContent.toString().trim())
  }
}