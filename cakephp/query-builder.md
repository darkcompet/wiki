# Query Builder in CakePHP Framework

https://book.cakephp.org/3/en/orm/query-builder.html

## How to run query

- To run (execute) a query, we have to call one of: `execute(), toArray(), toList(), all(), first()` or `foreach` query object.

   ```php
   # this will not be executed until we loop it in `foreach` or call one of executable function.
   $articles
      ->find()
      ->select(['id' => 'pk', 'username' => 'uname', 'fullname' => 'fname', 'valid'])
      ->distinct('pk')
      ->where(['id >' => 1, 'name' => 'darkcompet']);
   ↓
   # this will be executed and we can get its result
   $articles
      ->find()
      ->select(['id' => 'pk', 'username' => 'uname', 'fullname' => 'fname', 'valid'])
      ->distinct('pk')
      ->where(['id >' => 1, 'name' => 'darkcompet'])
      ->all();
   ```


## Select

- Select except some fields

   ```php
   # version 3.6~
   $query = $userTable->find();
   $query->selectAllExcept($userTable, ['exceptField1', 'exceptField2', 'exceptField3']);
   ```

- Fetching array instead of entities

   ```php
   $query = $articles
      ->find()
      ->enableHydration(false); // Results as arrays instead of entities
      ->toList();
   ```
