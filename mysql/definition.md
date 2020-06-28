# MySQL Definition


## Command Line

> See https://dev.mysql.com/doc/refman/8.0/en/creating-database.html

- Basic action

   ```bash
      # login
      mysql -u root -p

      # show database list
      show databases;

      # create database
      create database [users];

      # delete database
      drop database [db_name];
   ```

## Import/Export database

   ```bash
   # import database from a file
   mysql -u [username] -p [database_name] < [file.sql]

   # export database to a file
   ```

## Change password

   ```bash
      # in mac or unix, we need start mysql server first
      mysql.server start
      mysql -u root -p
      SET PASSWORD = PASSWORD('your_new_password');
      \q
   ```

   From mysql 5.7, it allows connect to `root` account via socket. So to connect via username and password,
   we need change `plugin` from `socket` to `mysql_native_password`:

   ```bash
   ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';
   FLUSH PRIVILEGES;
   ```

- [Unix] Reset root password when forgotten

   ```bash
      mysql.server stop
      sudo mysqld_safe --skip-grant-tables
      sudo mysql -u root
      UPDATE mysql.user SET Password=PASSWORD('password') WHERE User='root' (for mysql 5.6-)
      UPDATE mysql.user SET authentication_string = PASSWORD('newPwd') (for mysql 5.7+)
      WHERE User = 'root' AND Host = 'localhost';
      FLUSH PRIVILEGES;
      \q
   ```


## Add/Remove a sqlmode

   ```bash
   # add a sqlmode, eg,. ONLY_FULL_GROUP_BY
   SET sql_mode=(SELECT CONCAT(@@sql_mode,',ONLY_FULL_GROUP_BY'));

   # remove a sqlmode, eg,. ONLY_FULL_GROUP_BY
   SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
   ```

## Kill sleeping processes

   ```bash
      mysqladmin processlist -u root -p
      mysqladmin kill [process_id] -u root -p
   ```

## Truncate, Drop, Delete table

- `truncate` will `clear data + reset auto-increment-value`, internally, `truncate` use `drop + create` table.
- `drop` will `clear data + delete table structure + remove associated objects (indices, contraints...)`.
- `delete` will just `clear data row-by-row`, so it is slow if table contains a lot of records.
