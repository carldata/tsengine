# FlowScript language

[![Build status](https://travis-ci.org/carldata/flow-script.svg?branch=master)](https://travis-ci.org/carldata/flow-script)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.carldata/flow-script_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.carldata/flow-script_2.12)

FlowScript is a script engine for Time Series data.

The goal for FlowScript is to add the possibility for writing user scripts working on Time Series data.
Since scripts will be written be users and run on the server, then it is important that the scripts
run in a safe sandbox and don't introduce any security loopholes. 
The program written in FlowScript is executed by interpreter with the help of runtime functions.


## Quick start
 
 Add the following dependency to the build.sbt
 ```scala
 libraryDependencies += "io.github.carldata" %% "flow-script" % "0.9.5"
 ```

### Create test script
Lets say that we have this minimalistic script

```flowscript
def main(xs: TimeSeries): TimeSeries = xs+1 
```
 
### Compile script

```scala
import com.carl.sf.Compiler
import com.carl.sf.core.Core
import com.carl.sf.compiler.AST.Module

val script: String = "..."
val result: Either[String, Module] = Compiler.compile(script, Seq(Core.header))
```
[Core](https://github.com/carldata/flow-script/blob/master/src/main/scala/com/carl/sf/core) 
contains modules with core function definitions.
 
The compiler returns either error string or compiled module AST. 

### Execute script

```scala
import com.carl.sf.Interpreter
import com.carl.sf.compiler.Executable.ExecCode
import com.carl.sf.core.Core
import com.carl.sf.Runtime.{Value, NumberValue}
import carldata.series.TimeSeries


val exec: ExecCode = ???
val ts = TimeSeries.fromTimestamps(Seq((1L, 1f), (2L, 1f), (3L, 1f)))
val result: Either[String, Value] = new Interpreter(exec, Core).run("main", Seq(ts))
```

Interpreter returns either error string or computed value.

You can also check the folder **examples** for script examples.


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
