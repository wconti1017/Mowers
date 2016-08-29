package fr.william.model


class Mower(var xCoordinate:Int, var yCoordinate:Int, var orientation:String, var instructions:String = ""){
  
  def executeInstructions(lawn:Lawn) = 
  {
    instructions.foreach { instruction => 
      instruction match 
      {
        case 'A' => moveForward(lawn)
        case 'D' => turnRight()
        case 'G' => turnLeft()
        case _ => 
      }
    }
  }
  
  def moveForward(lawn:Lawn) = 
  {
    orientation match 
    {
      case "N" =>  yCoordinate match 
      {
        case point if (lawn.ySummit == point) => yCoordinate = point
        case point => yCoordinate = point + 1
      }
      case "E" => xCoordinate match 
      {
        case point if (lawn.xSummit == point) => xCoordinate = point
        case point => xCoordinate = point + 1
      }
      case "W" => xCoordinate match 
      {
        case point if (lawn.xSummit == 0) => xCoordinate = point
        case point => xCoordinate = point - 1
      }
      case "S" => yCoordinate match 
      {
        case point if (lawn.ySummit == 0) => yCoordinate = point
        case point => yCoordinate = point -1
      }
    }  
  }
  
  def turnLeft() = {
    orientation = orientation match {
      case "N" => "W"
      case "W" => "S"
      case "S" => "E"
      case "E" => "N"
      case _ => orientation
    }
  }
 
  def turnRight() = {
    orientation = orientation match {
      case "N" => "E"
      case "E" => "S"
      case "S" => "W"
      case "W" => "N"
      case _ => orientation
    }
  }  
  
  def showPosition() = {
    println(xCoordinate +" "+ yCoordinate+" "+orientation)
  }
  
}