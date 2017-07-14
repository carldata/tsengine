# FlowScript language

[![Build status](https://travis-ci.org/carldata/flow-script.svg?branch=master)](https://travis-ci.org/carldata/flow-script)

This project consists of a compiler and basic runtime system for FlowScript.

The main design goal for this language is a security. It should be safe to run any script written by the user 
on the server. 
The program written in FlowScript is executed by runtime which provide base functions for the script. 
Basic runtime implementation provides only few functions, which doesn't communicate with the OS.

Projects which want to use FlowScript and give scripts written in this language more power will extend runtime
with custom functions.

This language is intended for use cases like:

 * Add script to convert units in Time Series during data exports
 * Build script for custom report which will be run every 24h


## Build project
 
 ```bash
 sbt test
 ```


## How to include this library in your project

Lets say that we have this minimalistic script

```flowscript
module Demo1

// Read channel A2 and convert inches to centimeters
def main(a: Number, b: Number): Number = a 
```
 
### Compile script

```scala
import com.carl.sf.core.Core
val script: String = loadScript()
val ast: Either[String, Module] = Compiler.compile(code, Seq(Core.header))
```
[Core.header](https://github.com/carldata/flow-script/blob/master/src/main/scala/com/carl/sf/core/Core.scala) 
contains basic function definitions.
 
The compiler returns either error string or compiled module AST. 

### Execute script

```scala
import com.carl.sf.core.Core
val result: Either[String, Value] = new Interpreter(Core).run(ast, "main", Seq(NumberValue(1), NumberValue(2)))
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
