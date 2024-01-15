package griffio

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import griffio.queries.Sample

import org.postgresql.ds.PGSimpleDataSource

private fun getSqlDriver(): SqlDriver {
    val datasource = PGSimpleDataSource()
    datasource.setURL("jdbc:postgresql://localhost:5432/postgres")
    datasource.applicationName = "App Main"
    return datasource.asJdbcDriver()
}

fun main() {
    val driver = getSqlDriver()
    driver.execute(null, "CREATE EXTENSION IF NOT EXISTS pg_trgm", 0) // https://github.com/cashapp/sqldelight/pull/4541
    Sample.Schema.create(driver)
    val queries = Sample(driver).trgmQueries
    queries.insertTrgm("accommodate")
    queries.insertTrgm("acomodate")
    queries.insertTrgm("conscientious")
    queries.insertTrgm("consientious")
    queries.insertTrgm("necessary")
    queries.insertTrgm("neccesary")
    queries.insertTrgm("maybe neccesary")
    queries.insertTrgm("referred")
    queries.insertTrgm("to be referred")
    queries.insertTrgm("refered")
    println(queries.selectTrgm().executeAsList().joinToString("\n"))
// output:
//    SelectTrgm(t1=accommodate, t2=referred, sml=0.0, wsml=0.0)
//    SelectTrgm(t1=accommodate, t2=refered, sml=0.0, wsml=0.0)
//    SelectTrgm(t1=accommodate, t2=accommodate, sml=1.0, wsml=1.0)
//    SelectTrgm(t1=accommodate, t2=acomodate, sml=0.5714286, wsml=0.5714286)
//    SelectTrgm(t1=accommodate, t2=conscientious, sml=0.0, wsml=0.0)
}
