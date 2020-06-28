# MySQL Data Type


## DATA TYPES

- Numeric types

   Numeric Types | Description
   |---|---|
   TINYINT | A very small integer
   SMALLINT | A small integer
   MEDIUMINT | A medium-sized integer
   INT | A standard integer
   BIGINT | A large integer
   DECIMAL | A fixed-point number
   FLOAT | A single-precision floating point number
   DOUBLE | A double-precision floating point number
   BIT | A bit field

- String types

   String Types | Description
   |---|---|
   CHAR | A fixed-length nonbinary (character) string
   VARCHAR| A variable-length non-binary string
   BINARY | A fixed-length binary string
   VARBINARY | A variable-length binary string
   TINYBLOB | A very small BLOB (binary large object)
   BLOB | A small BLOB
   MEDIUMBLOB | A medium-sized BLOB
   LONGBLOB | A large BLOB
   TINYTEXT | A very small non-binary string
   TEXT | A small non-binary string
   MEDIUMTEXT | A medium-sized non-binary string
   LONGTEXT | A large non-binary string
   ENUM | An enumeration; each column value may be assigned one enumeration member
   SET | A set; each column value may be assigned zero or more SET members

- Date and Time types

   Date and Time Types | Description
   |---|---|
   DATE | A date value in CCYY-MM-DD format
   TIME | A time value in hh:mm:ss format
   DATETIME | A date and time value inCCYY-MM-DD hh:mm:ssformat
   TIMESTAMP | A timestamp value in CCYY-MM-DD hh:mm:ss format
   YEAR | A year value in CCYY or YY format


- Json types

- Spatial types

   Spatial Data Types | Description
   |---|---|
   GEOMETRY | A spatial value of any type
   POINT | A point (a pair of X-Y coordinates)
   LINESTRING | A curve (one or more POINT values)
   POLYGON | A polygon
   GEOMETRYCOLLECTION | A collection of GEOMETRYvalues
   MULTILINESTRING | A collection of LINESTRINGvalues
   MULTIPOINT | A collection of POINTvalues
   MULTIPOLYGON | A collection of POLYGONvalues


## VARCHAR


## TEXT

>https://www.mysqltutorial.org/mysql-text/

Text is different with CHAR and VARCHAR type, it is stored in hard-disk (not memory like CHAR or VARCHAT), so IO performance
is slow. Text is used to store long-text like article, post, paper... up to 4GB.

- `TINYTEXT`: store 255 bytes, (2^8 = 256, 1 byte overhead).
- `TEXT`: store 64 KB (65,535 bytes, 2 bytes overhead).
- `MEDIUMTEXT`: store 64MB (16,777,215 bytes, 3 bytes overhead).
- `LONGTEXT`: store 4GB (4,294,967,295 bytes, 4 bytes overhead).


## JSON

From version `5.7.8`, MySQL brings effecient type for query.

- Query with json

   ```sql
   -- create new table with json data contained
   CREATE TABLE events( 
      id int auto_increment primary key, 
      event_name varchar(255), 
      visitor varchar(255), 
      properties json, 
      browser json
   );

   -- insert json data
   INSERT INTO events(event_name, visitor, properties, browser)
   VALUES
      (
         'pageview',
         '1',
         '{ "page": "/" }',
         '{ "name": "Safari", "os": "Mac", "resolution": { "x": 1920, "y": 1080 } }'
      ),
      (
         'pageview',
         '2',
         '{ "page": "/contact" }',
         '{ "name": "Firefox", "os": "Windows", "resolution": { "x": 2560, "y": 1600 } }'
      );

   -- select raw field data in json (with bracket)
   SELECT browser->'$.name' browser FROM events LIMIT 1;
   -- "Safari"

   -- select field data in json (without bracket)
   SELECT browser->>'$.name' browser FROM events LIMIT 1;
   -- "Safari"
   ```
