
// Create TAP Connection
val tap = new Connect("http://tap.is-qut.nlytx.io")

// Get and print the Current Schema
tap.fetchSchema

tap.schemaNameTypes.foreach { case (name,value) => println(s"$name >> $value")}

// Analyse some text for some basic metrics
val text = "This is a very small test of TAP. It should produce some metrics on these two sentences!"
val json = tap.analyseText("metrics",text)

println()
println(s"METRICS:\n$json")
