package carldata.sf.compiler

/**
  * Symbol table contains set of symbols in current scope and link to
  * parent SymbolTable.
  */
class SymbolTable private (symbols: Set[String], parent: Option[SymbolTable]){

  def this() = this(Set(), None)


  /** Add scope to the current table */
  def addScope(): SymbolTable = {
    new SymbolTable(Set(), Some(this))
  }

  /** Find symbol in all scopes */
  def hasSymbol(x: String): Boolean = {
    symbols.contains(x) || parent.exists(_.hasSymbol(x))
  }

  /** Add symbol to the current scope */
  def addSymbol(x: String): SymbolTable = {
    new SymbolTable(symbols + x, parent)
  }

  /** Check symbol in the current scope only */
  def checkScope(x: String): Boolean = {
    symbols.contains(x)
  }
}

