# MySQL Manipulation


## JOIN

>inner join, left join, right join

- `inner join`: This is default join. Return all matching rows of left table and mapping row of right table.
That is, `result_row = matching_left_part + matching_right_part`.

- `left join`: Return all rows of left table and mapping row in right table. But, if no row in right table mapping with a row in left table,
then NULL row is return in right part. That is, `result_row = left_part + [right_part | NULL]`.

- `right join`: Return all rows of right table and mapping row in left table. But, if no row in left table mapping with a row in right table,
then NULL row is return in left part. That is, `result_row = right_part + [left_part | NULL]`.


## CRUD

- `Create`: create new database or table. See https://www.mysqltutorial.org/mysql-create-table/.

   ```sql
   CREATE DATABASE blog;
   
   CREATE TABLE [IF NOT EXISTS] table_name(
      column_1_definition,
      column_2_definition,
      ...,
      table_constraints
   ) ENGINE=storage_engine;

   -- eg,.
   CREATE TABLE IF NOT EXISTS tasks (
      task_id INT AUTO_INCREMENT PRIMARY KEY,
      title VARCHAR(255) NOT NULL,
      start_date DATE,
      due_date DATE,
      status TINYINT NOT NULL,
      priority TINYINT NOT NULL,
      description TEXT,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   )  ENGINE=INNODB;
   ```

   ※ Note: InnoDB became the default storage engine since MySQL version 5.5. The InnoDB storage engine brings many benefits of
   a relational database management system such as ACID transaction, referential integrity, and crash recovery.
   In the previous versions, MySQL used MyISAM as the default storage engine.


- `Insert`: insert one or more rows into table.

   ```sql
   INSERT INTO table_name (col1, col2, col3, ..., colN) VALUES (val1, val2, val3, ..., valN);

   -- eg,.
   INSERT INTO member (ID, name, age, address, salary) VALUES (1, 'Thanh', 24, 'Haiphong', 2000.00);
   ```

- `Update`: update a row in table

   ```sql
   UPDATE table_name SET col1 = val1, col2 = val2, ..., colN = valN WHERE [condition];
   ```

- `Delete`: delete one or more rows from table

   ```sql
   DELETE FROM table_name where [condition];
   ```

## NULL

To handle (compare) NULL value, we have to use `IS NULL, IS NOT NULL` keyword.

   ```sql
   SELECT count(id) FROM user WHERE name IS NOT NULL;
   SELECT count(id) FROM user WHERE address IS NULL;
   ```

## GROUP BY


## FUNCTION

- `ISNULL(nullable_value, return_value)`: Return other value if expression is null.

   ```sql
   SELECT ISNULL(NULL, "This is empty name");
   -- This is empty name
   
   SELECT ISNULL('2020-06-28', '2020-06-29');
   -- 2020-06-28
   ```

- `ISNUMERIC(value)`: Return 1 if given value is number, otherwise 0.

   ```sql
   SELECT ISNUMERIC(10 * 50);
   -- 1

   SELECT ISNUMERIC('2020-06-28');
   -- 0
   ```

- `ISDATE(value)`: Return 1 if given value is data, otherwise 0.

   ```sql
   SELECT ISDATE('2020-06-28 12:23:39');
   -- 1

   SELECT ISDATE('abc.com');
   -- 0
   ```