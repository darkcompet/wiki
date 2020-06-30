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


## Insert

To insert new records to table, we should not usr `find()`, instead of that, we should
use `query()` to perform the action.

   ```sql
   $data = $articles->find()
      ->select(['title', 'body', 'published'])
      ->where(['id' => 3]);

   $query = $articles->query()
      ->insert(['title', 'body', 'published'])
      ->values($data)
      ->execute();
   ```


## Update

As insert, to update we should use `query()` as below

   ```sql
   $query = $articles->query();
   $query->update()
      ->set(['published' => true])
      ->where(['id' => $id])
      ->execute();
   ```