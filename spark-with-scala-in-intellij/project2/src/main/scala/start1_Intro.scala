import org.apache.spark
import org.apache.spark.{SparkConf, SparkContext}
import start1_Intro.babyNames



object start1_Intro  extends App {

  //Note:The way to interact with Spark is via a SparkContext.

    //Create a SparkContext to initialize Spark
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Word Count")
    val sc = new SparkContext(conf)

  //Note: RDDs are loaded from an external data set or created via a SparkContext.After obtaining a SparkContext, developers interact with Spark via Resilient Distributed Datasets.
    //create RDD
    val babyNames = sc.textFile("/home/gawshalini/Documents/learn/spark/spark-with-scala-in-intellij/project2/src/main/resources/Baby_Names__Beginning_2007.csv")


  //Note: When working with a Spark RDDs, there are two available operations: actions or transformations
  //count,first are actions
  //
    //Number of items in this Dataset
    println(babyNames.count)     //o/p:235511

    //First line in this Dataset
    println( babyNames.first())      //o/p:Year,First Name,County,Sex,Count



  //Note:Transformations create new RDDs using existing RDDs.
    val rows = babyNames.map(line => line.split(","))


  //map function(Pass each element of the RDD through the supplied function)

  //  What did this example do?  Iterates over every line in the babyNames RDD (originally created from baby_names.csv file)
  // and splits into new RDD of Arrays of Strings.The arrays contain a String separated by comma characters in the source RDD (CSV).
  //flatmap(flatMap is helpful with nested datasets.  It may be beneficial to think of the RDD source as hierarchical JSON )
  //This is unlike CSV which has no hierarchical structural.

  //Here row(2) means taking 2 column values
    println(rows.map(row =>row(2)))   //o/p:MapPartitionsRDD[3] at map at start1.scala:46



    //taking distint count of column2
    println(rows.map(row =>row(2)).distinct.count())  //o/p:126

    //count of column1 where the row contains "DAVID"
    println(rows.filter(row=>row(1).contains("DAVID")).count())   //o/p:328



  //    //Print each value in the text file
  //    val rows = babyNames.map(line => line.split(","))    // RDD[ Array[ String ]
  //    rows.foreach { x => x.foreach { y => println(y) } }
  //

  //  import scala.collection.JavaConversions._
  //
  //  for (babyName <- babyNames.collect) {
  //    println(babyName)
  //  }

  //Access each cell values
  //    val splitRdd = babyNames.map { s =>
  //      val a = s.split(" ,")
  //      a(0)
  //       }
  //
  //  // printing Line
  //  splitRdd.foreach(println)


}
