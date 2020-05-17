# Setup & Configuration


## Config

- Download laravel installer via composer:

   ```bash
   composer global require laravel/installer
   ```

- Create new project

   ```bash
   # via laravel
   laravel new [project_name]

   # or via composer
   composer create-project --prefer-dist laravel/laravel [project_name] "5.8.*"
   ```

Now we can start server by run: `php artisan serve`



## Note when setup source code in server

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