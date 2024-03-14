package griffio.dialect

import app.cash.sqldelight.dialect.api.IntermediateType
import app.cash.sqldelight.dialect.api.PrimitiveType
import app.cash.sqldelight.dialect.api.SqlDelightDialect
import app.cash.sqldelight.dialect.api.TypeResolver
import app.cash.sqldelight.dialects.mysql.MySqlDialect
import app.cash.sqldelight.dialects.mysql.MySqlTypeResolver
import com.alecstrong.sql.psi.core.psi.SqlFunctionExpr

class CustomDialect : SqlDelightDialect by MySqlDialect() {

    override fun typeResolver(parentResolver: TypeResolver) = CustomResolver(MySqlTypeResolver(parentResolver))

    class CustomResolver(private val typeResolver: TypeResolver) :
        TypeResolver by typeResolver {
        override fun functionType(functionExpr: SqlFunctionExpr): IntermediateType? {
            return when (functionExpr.functionName.text.lowercase()) {
                "inet_aton" -> IntermediateType(PrimitiveType.TEXT)
                "inet_ntoa" -> IntermediateType(PrimitiveType.TEXT)
                else -> typeResolver.functionType(functionExpr)
            }
        }
    }
}
