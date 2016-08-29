package fr.william.model

class Program(var lawn:Lawn,var mowers:Seq[Mower]) {
  
  /**
    * Execute the instuctions for each mowers, at the end of his instructions, each mowers will print his position to the console.
 		* 
 		*/
  def executeProgram() = {
    var iterator = 0
    
    mowers.foreach { mower =>
      mowers(iterator).executeInstructions(lawn)
      mowers(iterator).showPosition()
      iterator+=1
    }
  }
  
  def isValid() = (lawn.isValid() && mowers.size != 0)
}