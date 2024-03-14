package griffio

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.mysql.cj.jdbc.MysqlDataSource
import griffio.queries.Sample

private fun getSqlDriver(): SqlDriver {
    val datasource = MysqlDataSource()
    datasource.setURL("jdbc:mysql://localhost:3306/mysql")
    return datasource.asJdbcDriver()
}

fun main() {
    val driver = getSqlDriver()
    val q = Sample(driver).openWhiteListQueries
    val lastId = q.insert(1, "10.0.5.9").executeAsOne()
}
