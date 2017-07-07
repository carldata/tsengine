# FlowScript language

[![Build status](https://travis-ci.org/carldata/flow-script.svg?branch=master)](https://travis-ci.org/carldata/flow-script)

This project consists of compiler and basic runtime system for FlowScript.

The main design goal for this language is a security. It should be safe to run any script written by the user 
on the server. 
The program written in FlowScript is executed by runtime which provide base functions for the script. 
Basic runtime implementation provides only few functions, which doesn't communicate with the OS.

Projects which want to use FlowScript and give scripts written in this language more power will extend runtime
with custom functions.

This language is intended for use cases like:

 * Add script to convert units in Time Series during data exports
 * Merging 2 Time Series to create new Series

 
## Build
 
 ```bash
 sbt test
 ```
 
 REPL console:
 
 ```bash
  sbt console
  ```


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