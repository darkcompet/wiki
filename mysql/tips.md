## Tips

## Foreign Key

- Uncheck foreign key for manipulation

   ```sql
   SET FOREIGN_KEY_CHECKS = 0;
   -- Do anything (alter auto_increment, modify...)
   SET FOREIGN_KEY_CHECKS = 1;
   ```

- Search item in joined-string

   ```sql
   SELECT * FROM user WHERE find_in_set(1, `pro_ids`);
   ```