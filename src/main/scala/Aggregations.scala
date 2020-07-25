import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object Aggregations {

  def totalGoalsAgg()(df: DataFrame): DataFrame = {
    df.groupBy("name").agg(sum("goals").as("total_goals"))
  }

}
