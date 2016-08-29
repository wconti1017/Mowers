package fr.william.model

class Lawn(var xSummit: Int, var ySummit: Int) {
  
  def isValid() : Boolean = (xSummit != 0 && ySummit != 0)
  
}