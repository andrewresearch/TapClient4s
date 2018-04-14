import scala.collection.immutable.HashMap

object Queries {

  val query: HashMap[String, String] = HashMap(
    "metrics" ->
      """query Metrics($input: String!){
        | metrics(text: $input) {
        |   analytics {
        |     words
        |     sentences
        |     sentWordCounts
        |     averageSentWordCount
        |   }
        | }
        |}
      """.stripMargin,

    "annotations" ->
      """query TokeniseWithCLU($input: String!) {
        |    annotations(text:$input,pipetype:"clu") {
        |      analytics {
        |        idx
        |        start
        |        end
        |        length
        |        tokens {
        |          idx
        |          term
        |          lemma
        |          postag
        |          parent
        |          children
        |          deptype
        |          nertag
        |        }
        |      }
        |      timestamp
        |    }
        |  }
      """.stripMargin,

    "expressions" ->
      """query Expressions($input:String!) {
        |    expressions(text:$input) {
        |      analytics {
        |        sentIdx
        |        affect{
        |          text
        |        }
        |        epistemic {
        |          text
        |          startIdx
        |          endIdx
        |        }
        |        modal {
        |          text
        |        }
        |      }
        |    }
        |  }
      """.stripMargin,

    "reflectExpressions" ->
      """query ReflectExpressions($input:String!) {
        |    reflectExpressions(text:$input) {
        |      querytime
        |      analytics {
        |        counts {
        |          wordCount
        |          avgWordLength
        |          sentenceCount
        |          avgSentenceLength
        |        }
        |        summary {
        |          metaTagSummary {
        |            knowledge
        |            experience
        |            regulation
        |            none
        |          }
        |          phraseTagSummary {
        |            outcome
        |            temporal
        |            pertains
        |            consider
        |            anticipate
        |            definite
        |            possible
        |            selfReflexive
        |            emotive
        |            selfPossessive
        |            compare
        |            manner
        |            none
        |          }
        |        }
        |        tags {
        |          sentence
        |          phrases
        |          subTags
        |          metaTags
        |        }
        |      }
        |    }
        |  }
      """.stripMargin,

    "vocabulary" ->
      """query Vocab($input: String!) {
        |    vocabulary(text:$input){
        |      analytics {
        |        unique
        |        terms {
        |          term
        |          count
        |        }
        |      }
        |      timestamp
        |    }
        |  }
      """.stripMargin,

    "posStats" ->
      """query PosStats($input:String!){
        |    posStats(text:$input) {
        |      analytics {
        |        verbNounRatio
        |        futurePastRatio
        |        adjectiveWordRatio
        |        namedEntityWordRatio
        |        nounDistribution
        |        verbDistribution
        |        adjectiveDistribution
        |      }
        |    }
        |  }
      """.stripMargin,

    "syllables" ->
      """query Syllables($input:String!) {
        |    syllables(text:$input) {
        |      analytics {
        |        sentIdx
        |        avgSyllables
        |        counts
        |      }
        |      timestamp
        |    }
        |  }
      """.stripMargin,

    "spelling" ->
      """query Spelling($input:String!) {
        |    spelling(text:$input) {
        |      timestamp
        |      message
        |      querytime
        |      analytics {
        |        sentIdx
        |        spelling {
        |          message
        |          suggestions
        |          start
        |          end
        |        }
        |      }
        |    }
        |  }
      """.stripMargin,

    "schema" ->
    """{ __schema { queryType { name ...subtypes } } }
      |  fragment subtypes on __Type { fields {
      |    name
      |    type {
      |      ofType {
      |        name
      |      }
      |      fields {
      |        name
      |        type {
      |          ofType {
      |            name
      |          }
      |          fields {
      |            name
      |            type {
      |              ofType {
      |                name
      |              }
      |              fields {
      |                name
      |                type {
      |                  ofType {
      |                    name
      |                  }
      |                  fields {
      |                    name
      |                    type {
      |                      ofType {
      |                        name
      |                      }
      |                      fields {
      |                        name
      |                        type {
      |                          ofType {
      |                            name
      |                          }
      |                          fields {
      |                            name
      |                          }}}}}}}}}}}}}}
    """.stripMargin
  )

}