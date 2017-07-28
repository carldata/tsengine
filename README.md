# FlowScript language

[![Build status](https://travis-ci.org/carldata/flow-script.svg?branch=master)](https://travis-ci.org/carldata/flow-script)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.carldata/flow-script_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.carldata/flow-script_2.12)

This project consists of a compiler and an interpreter for FlowScript.

The reason for yet another script language is security. It should be safe to run script written by the user 
on the server. 
The program written in FlowScript is executed by interpreter with the help of basic runtime, which provides some basic functions.

Projects which want to use FlowScript and give scripts written in this language more power will need to extend runtime
with custom functions.

This language is intended more as a "glue" then full blown language. Typical use cases:

 * Add script to convert units in Time Series during data exports
 * Build script for custom report which will be run every 24h


## Quick start
 
 Add the following dependency to the build.sbt
 ```
 libraryDependencies += "io.github.carldata" %% "flow-script" % "0.5.3"
 ```

### Create test script
Lets say that we have this minimalistic script

```flowscript
module Demo1

// Read channel A2 and convert inches to centimeters
def main(a: Number, b: Number): Number = a 
```
 
### Compile script

```scala
import com.carl.sf.Compiler
import com.carl.sf.core.Core
import com.carl.sf.compiler.AST.Module

val script: String = "..."
val result: Either[String, Module] = Compiler.compile(script, Seq(Core.header))
```
[Core.header](https://github.com/carldata/flow-script/blob/master/src/main/scala/com/carl/sf/core/Core.scala) 
contains basic function definitions.
 
The compiler returns either error string or compiled module AST. 

### Execute script

```scala
import com.carl.sf.Interpreter
import com.carl.sf.compiler.Executable.ExecCode
import com.carl.sf.core.Core
import com.carl.sf.Runtime.{Value, NumberValue}

val exec: ExecCode = ???
val result: Either[String, Value] = new Interpreter(exec, Core).run("main", Seq(NumberValue(1), NumberValue(2)))
```

Interpreter returns either error string or computed value.

### Extend the Runtime
The Runtime extends script with custom types and functions. Some basic types are implemented in 
the [Core.header](https://github.com/carldata/flow-script/blob/master/src/main/scala/com/carl/sf/core/Core.scala).

Also the following types are build in the compiler (because of the literals):

  * String
  * Numeric
  * Bool


# Join in!

We are happy to receive bug reports, fixes, documentation enhancements,
and other improvements.

Please report bugs via the
[github issue tracker](http://github.com/carldata/flow-script/issues).



# Redistributing

FlowScript source code is distributed under the Apache-2.0 license.

**Contributions**

Unless you explicitly state otherwise, any contribution intentionally submitted
for inclusion in the work by you, as defined in the Apache-2.0 license, shall be
licensed as above, without any additional terms or conditions.
