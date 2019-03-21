import org.apache.spark.sql.SparkSession



object Start4_sparkSql_Dataframes_Datasets extends App {


  //create a basic SparkSession
  val spark = SparkSession
    .builder().master("local")
    .appName("Spark SQL basic example")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()

  // For implicit conversions like converting RDDs to DataFrames
  import spark.implicits._

  //DATA FRAME
  val df = spark.read.csv("/home/gawshalini/Documents/learn/spark/spark-with-scala-in-intellij/project2/src/main/resources/Baby_Names__Beginning_2007.csv")

  println(df.count())    //235511

  df.show()
  //show all rows
 // df.show(df.count().toInt,false)

  // Print the schema in a tree format
  df.printSchema()

  //select specific column
  df.select("_c0").show()

  //select multiple columns
  df.select($"_c0", $"_c1").show()

  //filter(select rows where count>21)
  df.filter($"_c4" > 200).show()

  //group by year and show the count
  df.groupBy("_c0").count().show()

//RUN SQL QUERY IN DATA FRAME

  // Register the DataFrame as a SQL temporary view
  df.createOrReplaceTempView(" BabyNames")

  val sqlDF = spark.sql("SELECT * FROM BabyNames where _c0='2013'")
  sqlDF.show()


  //Temporary views in Spark SQL are session-scoped and will disappear if the session that creates it terminates.
  //If you want to have a temporary view that is shared among all sessions and keep
  // alive until the Spark application terminates, you can create a global temporary view.
  // Global temporary view is tied to a system preserved database `global_temp`
  df.createGlobalTempView("BabyNames1")
  spark.sql("SELECT * FROM global_temp.BabyNames1").show()
  spark.newSession().sql("SELECT * FROM global_temp.BabyNames1").show()



  //CREATING DATA SETS
  //  Datasets are similar to RDDs, however, instead of using Java serialization
  // or Kryo they use a specialized Encoder to serialize the objects for processing or transmitting over the network.


  //create a class
    case class Person(name: String, age: Long)

    val caseClassDS = Seq(Person("Andy", 32)).toDS()
    caseClassDS.show()


    // Encoders for most common types are automatically provided by importing spark.implicits._
    val primitiveDS = Seq(1, 2, 3).toDS()
    println(primitiveDS.map(_ + 1).collect()) // Returns: Array(2, 3, 4)

    // DataFrames can be converted to a Dataset by providing a class. Mapping will be done by name
    case class Baby()
    val BabyNamesDs =df.as[Baby]
    BabyNamesDs.show()
    BabyNamesDs.select("_c0","_c1").groupBy("_c0","_c1").count().show()



}