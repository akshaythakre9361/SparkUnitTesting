import Aggregations.totalGoalsAgg
import com.github.mrpowers.spark.fast.tests.DataFrameComparer
import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.scalatest.FunSpec

class AggregationsSpec extends FunSpec with SparkSessionTestWrapper with DataFrameComparer {

  it("shows how to test a groupBy operation") {
    val data = Seq(Row("messi", 2),
      Row("messi", 1),
      Row("pele", 3),
      Row("pele", 1))

    val schema = StructType(List(StructField("name", StringType, true),
      StructField("goals", IntegerType, true)))

    val goalsDF = spark.createDataFrame(sc.parallelize(data), schema)

    val resDF = goalsDF.transform(totalGoalsAgg())

    val expectedData = Seq(Row("pele", 4L),
      Row("messi", 3L))

    val expectedSchema = StructType(List(StructField("name", StringType, true),
      StructField("total_goals", LongType, true)))

    val expectedDF = spark.createDataFrame(sc.parallelize(expectedData), expectedSchema)

    assertSmallDataFrameEquality(resDF, expectedDF)
  }
}
