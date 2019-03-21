import org.apache.spark.sql.SparkSession






object Excercise extends App {


  //create a basic SparkSession
  val spark = SparkSession
    .builder().master("local")
    .appName("Spark SQL basic example")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()

  // For implicit conversions like converting RDDs to DataFrames

  //DATA FRAME

  val df = spark.read
    .format("com.databricks.spark.csv")
    .option("delimiter", "|")
    .option("header", "false")
    .load("/home/gawshalini/Documents/learn/spark/spark-with-scala-in-intellij/project2/src/main/resources/MSC050120170625000000176477GCDR.dat.00.IUC")

  df.show()

    val host: String = "localhost"
    val port: Int = 3306
    val database: String = "SparkExercise"
    val table: String = "test"
    val user: String = "root"
    val password: String = "123"

    val options = Map(
      "url" -> s"jdbc:mysql://$host:$port/$database?zeroDateTimeBehavior=convertToNull",
      "dbtable" -> table,
      "user" -> user,
      "password" -> password,
       "driver"->"com.mysql.jdbc.Driver"
    )

  df.write.format("jdbc").options(options).save()






//READ FROM DB
//  //create a basic SparkSession
//  val spark = SparkSession
//    .builder().master("local")
//    .appName("Spark SQL basic example")
//    .config("spark.some.config.option", "some-value")
//    .getOrCreate()
//
//  val host: String = "localhost"
//  val port: Int = 3306
//  val database: String = "SparkExercise"
//  val table: String = "baby_names"
//  val user: String = "root"
//  val password: String = "123"
//
//  val options = Map(
//    "url" -> s"jdbc:mysql://$host:$port/$database?zeroDateTimeBehavior=convertToNull",
//    "dbtable" -> table,
//    "user" -> user,
//    "password" -> password,
//     "driver"->"com.mysql.jdbc.Driver"
//  )
//
//  val df1 = spark.read.format("jdbc").options(options).load()
//  df1.show()

}
