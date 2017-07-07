package com.carl.sf.compiler

import org.scalatest._


class SymbolTableTest extends FlatSpec with Matchers {

  "SymbolTable" should "not find undeclared symbol" in {
    val st = new SymbolTable()
    st.hasSymbol("x") shouldBe false
  }

  "SymbolTable" should "find declared symbol" in {
    val st = new SymbolTable().addSymbol("x")
    st.hasSymbol("x") shouldBe true
  }

  "SymbolTable" should "find declared symbol in current scope" in {
    val st = new SymbolTable().addSymbol("x")
    st.checkScope("x") shouldBe true
  }

  "SymbolTable" should "not find declared symbol in current scope" in {
    val st = new SymbolTable().addSymbol("x").addScope()
    st.checkScope("x") shouldBe false
  }

}