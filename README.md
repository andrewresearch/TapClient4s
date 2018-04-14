[ ![Download](https://api.bintray.com/packages/heta/heta-tap/tapclient4s/images/download.svg?version=0.1.1) ](https://bintray.com/heta/heta-tap/tapclient4s/0.1.1/link) [![https://img.shields.io/badge/license-Apache%202.0-blue.svg](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

# TapClient4s
Scala client library for [TAP](https://github.com/heta-io/tap)

### Installation

The library is available in BinTray. Add it to your `build.sbt` as follows:

```sbtshell
libraryDependencies += "io.heta" %% "tapclient4s" % "0.1.2"
resolvers += Resolver.bintrayRepo("heta", "heta-tap")
```

### Basic Example

```scala
import io.heta.TapConnection

// Create TAP Connection
val tap = new TapConnection("http://tap.is-qut.nlytx.io")

// Get and print the Current Schema
tap.fetchSchema

tap.schemaNameTypes.foreach { case (name,value) => println(s"$name >> $value")}

// Analyse some text for some basic metrics
val text = "This is a very small test of TAP. It should produce some metrics on these two sentences!"
val json = tap.analyseText("metrics",text)

println()
println(s"METRICS:\n$json")
```

should output:

```

vocabulary >> VocabResult
syllables >> syllables
spelling >> SpellingResult
cleanMinimal >> StringResult
metrics >> MetricsResult
cleanAscii >> StringResult
visible >> StringResult
annotations >> SentencesResult
clean >> StringResult
reflectExpressions >> ReflectExpressionsResult
posStats >> PosStatsResult
cleanPreserve >> StringResult
moves >> StringListResult
expressions >> ExpressionsResult

METRICS:
{"data":{"metrics":{"analytics":{"words":17,"sentences":2,"sentWordCounts":[8,9],"averageSentWordCount":8.5}}}}

Process finished with exit code 0

```

### Currently available queries

| Query | Return Type |
|-------|-------------|
| visible | `StringResult` |
| clean | `StringResult` |
| cleanPreserve | `StringResult` |
| cleanMinimal | `StringResult` |
| cleanAscii | `StringResult` |
| annotations | `SentencesResult` |
| vocabulary | `VocabResult` |
| metrics | `MetricsResult` |
| expressions | `ExpressionsResult` |
| syllables | `syllables` |
| spelling | `SpellingResult` |
| posStats | `PosStatsResult` |
| reflectExpressions | `ReflectExpressionsResult` |
| moves | `StringListResult` |