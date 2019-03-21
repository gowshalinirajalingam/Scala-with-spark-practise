import org.apache.spark.sql.SparkSession


object check_spark_running  extends App {


    val spark = SparkSession.builder.master("local").appName("Simple Application").getOrCreate()
    println(s"Lines with a: Lines with b:")
    spark.stop()


}
