# SqlDelight 2.0.2 with Custom Dialect

A custom dialect can be needed if you want to extend a vendor dialect, like PostgreSql, with functions and types
that are not currently defined. This maybe because they belong to an optional extension that you want to use.

Example PostgreSql extensions introduce functions that are not currently supported by SqlDelight.

A new type resolver is needed https://github.com/griffio/sqldelight-custom-dialect/blob/master/customDialect/src/main/kotlin/griffio/dialect/CustomDialect.kt#L15

```
Some functions we want to use:

pg_trgm extension 

similarity ( text, text ) → real

word_similarity ( text, text ) → real

strict_word_similarity ( text, text ) → real


pgcrypto extension 

crypt(text, text) -> text

gen_salt(text, int) -> text

```

* `CREATE EXTENSION IF NOT EXISTS` supported in `.sq` and `.sqm`
  * MERGED - https://github.com/cashapp/sqldelight/pull/4541
* Function parameters cannot mix types - Only the return type can be specified 
  * AWAITING FIX https://github.com/cashapp/sqldelight/issues/4133
