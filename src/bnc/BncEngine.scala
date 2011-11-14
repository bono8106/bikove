package bnc

abstract class BncEngine {
  
  class EBncError extends Exception;
  
  type TNumber = Int; // 0..9
  
  type TNumSet = Set[TNumber];
  
  type TBncNumber = Array[TNumber]; // size 4
  
  case class TBncMatch(bulls: Byte, cows: Byte);
  
  case class TBncSuggestion(number: TBncNumber, bncMatch: TBncMatch);

  object TBncMove extends Enumeration {
    val mGuess, mUser = Value;
  }
  type TBncMove = TBncMove.Value;
  
  object TBncMoveMode extends Enumeration {
    val mmUser, mmAutomatic = Value;
  }
  type TBncMoveMode = TBncMoveMode.Value;

  abstract class TCustomBncPlayer(val allowLeadZero: Boolean) {
    protected val suggestions: List[TBncSuggestion];
    protected val number: TBncNumber;

    protected def createNumber: TBncNumber;
    protected def createName: String;

    def guessNumber: TBncNumber;
    def randomNumber: TBncNumber;
    def isNumberValid(n: TBncNumber): Boolean;
    def mattch(suggestion: TBncNumber): TBncMatch;
    
    def addSuggestion(sugg: TBncSuggestion);
    def moveMode(move: TBncMove): TBncMoveMode;

    val playerName: String;
    def suggestion(index: Int): TBncSuggestion;
    def suggestionCount: Int;
  }

  abstract class TUserBncPlayer(allowLeadZero: Boolean) extends TCustomBncPlayer(allowLeadZero) {
    protected def createName: String;
    def moveMode(move: TBncMove): TBncMoveMode;
    
  }

  type TNumberList = collection.mutable.LinkedList[TBncNumber];

  abstract class TComputerBncPlayer(allowLeadZero: Boolean) extends TCustomBncPlayer(allowLeadZero) {
    protected val list: TNumberList;
    private def listCount = list.size;
    
    protected def generateList;
    protected def freeList;
    protected def shrinkList(suggestion: TBncNumber, mattch: TBncMatch);
    

    protected def createName: String;
    
    def addSuggestion(sugg: TBncSuggestion);
    def guessNumber: TBncNumber;
    def moveMode(move: TBncMove): TBncMoveMode;
    def variants = listCount;
    
  }

  def isMatchValid(m: TBncMatch): Boolean = m.bulls + m.cows <= 4 && (m.bulls != 3 || m.cows != 1);

  def findMatch(suggestion: TBncNumber, number: TBncNumber): TBncMatch = {
    var bulls, cows: Byte = 0;
    var i = 0;
    while (i < suggestion.size) {
      var j = 0;
      while (j < number.size) {
        if (number(j) == suggestion(i)) {
          if (i == j) {
            bulls = (bulls + 1).asInstanceOf[Byte];
          } else {
            cows = (cows + 1).asInstanceOf[Byte];
          }
        }

        j += 1;
      }
      
      i += 1;
    }
    return TBncMatch(bulls, cows);
  }
  def strToNumber(s: String): TBncNumber;
  def numbertoStr(n: TBncNumber): String;
  def matchToStr(mattch: TBncMatch, bullStr: String, cowStr: String, sep: String, nullStr: String): String;

  
  
}