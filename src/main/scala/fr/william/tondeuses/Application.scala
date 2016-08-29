package fr.william.tondeuses

import scala.io.Source

object Application {
  
  val fileReader = new FileReader
  
  def main(args : Array[String]) 
  {
    if (args.size == 1)
    {
      val path = args(0)
      val lines = Source.fromFile(path).getLines().toArray //Reads the file from specified path and converts it to an Array of string where each index is a line.
      
      val program = fileReader.createProgramFromFile(lines)
      if (program.isValid()) 
      {
       program.executeProgram()
       sys.exit()
      }
      else
        sys.exit(1)
    } 
    else
      throw new IllegalArgumentException("You must specify the path of your file")
  }

}
