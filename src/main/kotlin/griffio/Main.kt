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
    // driver.execute(null, "CREATE EXTENSION IF NOT EXISTS pg_trgm", 0) // https://github.com/cashapp/sqldelight/pull/4541
    Sample.Schema.create(driver)
    val sample = Sample(driver)
    val trgm = sample.trgmQueries
    trgm.insertTrgm("accommodate")
    trgm.insertTrgm("acomodate")
    trgm.insertTrgm("conscientious")
    trgm.insertTrgm("consientious")
    trgm.insertTrgm("necessary")
    trgm.insertTrgm("neccesary")
    trgm.insertTrgm("maybe neccesary")
    trgm.insertTrgm("referred")
    trgm.insertTrgm("to be referred")
    trgm.insertTrgm("refered")
    println(trgm.selectTrgm().executeAsList().joinToString("\n"))
// output:
//    SelectTrgm(t1=accommodate, t2=referred, sml=0.0, wsml=0.0)
//    SelectTrgm(t1=accommodate, t2=refered, sml=0.0, wsml=0.0)
//    SelectTrgm(t1=accommodate, t2=accommodate, sml=1.0, wsml=1.0)
//    SelectTrgm(t1=accommodate, t2=acomodate, sml=0.5714286, wsml=0.5714286)
//    SelectTrgm(t1=accommodate, t2=conscientious, sml=0.0, wsml=0.0)
    sample.cryptoQueries.insertProfile("user1", "pAsS!WoRd")
}
