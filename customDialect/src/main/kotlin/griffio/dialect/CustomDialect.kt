package griffio.dialect

import app.cash.sqldelight.dialect.api.IntermediateType
import app.cash.sqldelight.dialect.api.PrimitiveType
import app.cash.sqldelight.dialect.api.SqlDelightDialect
import app.cash.sqldelight.dialect.api.TypeResolver
import app.cash.sqldelight.dialects.postgresql.PostgreSqlDialect
import app.cash.sqldelight.dialects.postgresql.PostgreSqlTypeResolver
import com.alecstrong.sql.psi.core.psi.SqlFunctionExpr

class CustomDialect : SqlDelightDialect by PostgreSqlDialect() {

    override fun typeResolver(parentResolver: TypeResolver) = CustomResolver(PostgreSqlTypeResolver(parentResolver))

    class CustomResolver(private val typeResolver: TypeResolver) :
        TypeResolver by typeResolver {
        override fun functionType(functionExpr: SqlFunctionExpr): IntermediateType? {
            return when (functionExpr.functionName.text.lowercase()) {
                "word_similarity" -> IntermediateType(PrimitiveType.REAL)
                "similarity" -> IntermediateType(PrimitiveType.REAL)
                "strict_word_similarity" -> IntermediateType(PrimitiveType.REAL)
                else -> typeResolver.functionType(functionExpr)
            }
        }
    }
}
