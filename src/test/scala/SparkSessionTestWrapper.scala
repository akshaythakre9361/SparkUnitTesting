import org.apache.spark.sql.SparkSession

trait SparkSessionTestWrapper {
  val spark = SparkSession
    .builder()
    .appName("spark test example")
    .master("local")
    .getOrCreate()

  val sc = spark.sparkContext
}
