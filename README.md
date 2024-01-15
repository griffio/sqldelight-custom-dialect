# SqlDelight 2.0.1 with Custom Dialect

A custom dialect can be needed if you want to extend a vendor dialect, like PostgreSql, with functions and types
that are not currently defined. This maybe because they belong to an optional extension that you want to use.

Example PostgreSql extensions introduce functions that are not currently supported by SqlDelight.

```
pg_trgm module 

similarity ( text, text ) → real

word_similarity ( text, text ) → real

strict_word_similarity ( text, text ) → real

```

* CREATE EXTENSION support
  * AWAITING FIX - https://github.com/cashapp/sqldelight/pull/4541
* Function parameters cannot mix types - Only the return type can be specified 
  * AWAITING FIX https://github.com/cashapp/sqldelight/issues/4133
