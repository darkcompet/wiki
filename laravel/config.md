# Setup & Configuration


## Create project

- Create new project

   ```bash
   # via laravel (create new project with latest laravel version)
   composer global require laravel/installer
   laravel new `project_name`

   # or via composer
   composer create-project --prefer-dist laravel/laravel `project_name` "5.8.*"
   ```

Now we can start server by run: `php artisan serve`


## Extras setting

- Make public storage file visible to client by linking `public/storage` to `storage/app/public`.

   ```bash
   php artisan storage:link
   ```

- Migrate (create) database from migration php file

   ```bash
    # create migration file (should contains `s` in table name)
    php artisan make:migration create_users_table
    # apply migrations to db
    php artisan migrate

    # create seeder UserSeeder
    php artisan make:seeder UserSeeder
    # seed to init db
    php artisan db:seed

    # drop all tables and re-migrate
    php artisan migrate:fresh
   ```


## Note for setup source code in server

- When combine `nginx + laravel + mysql` then `bootstrap` and `storage` must be readable and writable by nginx.

   ```bash
      chmod 0777 -R bootstrap
      chmod 0777 -R storage
   ```

- Folder `vendor` must be installed correctly. If not, must copy from other machine.

   ```bash
      git clone https://darkcompet@github.com/darkcompet/tmp.git
      cp tmp/vendor.zip src/
      unzip src/vendor.zip
   ```

- Don't forget change info in `src/.env`.
