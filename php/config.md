# PHP Configuration


## On Linux

- List version of php to choose prefer one.

   ```bash
   yum list php*-fpm
   ```

- After that, if you wanna install php version 7.3, then enter.

   ```bash
   sudo yum install php73-fpm
   ```

- To make php better, we can install more modules for it.

   ```
   php73-gd – A module for PHP applications for using the gd graphics library.
   php73-mysqlnd – A module for PHP applications that use MySQL databases.
   php73-opcache – The Zend OPcache provides faster PHP execution through opcode caching and optimization. It improves PHP performance by storing precompiled script bytecode in the shared memory.
   php73-pdo – The php-pdo package contains a dynamic shared object that will add database access abstraction layer to PHP. This module provides a common interface for accessing MySQL, PostgreSQL or other databases.
   php73-xmlrpc – The php-xmlrpc package contains a dynamic shared object that will add support for the XML-RPC protocol to PHP.
   ```

   ```bash
   sudo yum install php73-gd php73-mysqlnd php73-opcache php73-xmlrpc php73-pdo php73-mbstring 
   ```

Note that, We can list more modules by `yum list php73-*`. 

- Enable php from service at boot time.

   ```bash
   sudo chkconfig php-fpm on
   ```

- Start php and check its status.

   ```bash
   sudo service php-fpm start
   sudo service php-fpm status
   ```

Note that, we can verify Unix socket is up and running php by `ls -l /var/run/php-fpm/www.sock`.

- Configuration.

We can confirm configuration of php by `cat /etc/nginx/default.d/php.conf`.

- Change group and user from `apache` to `nginx`.

   ```bash
   sudo nano /etc/php-fpm.d/www.conf

   user = apache
   group = apache
   ↓
   user = nginx
   group = nginx
   ```

- Reload php by `sudo service php-fpm reload`.


## On Mac

https://getgrav.org/blog/macos-catalina-apache-multiple-php-versions

==> php
To enable PHP in Apache add the following to httpd.conf and restart Apache:
    LoadModule php7_module /usr/local/opt/php/lib/httpd/modules/libphp7.so

    <FilesMatch \.php$>
        SetHandler application/x-httpd-php
    </FilesMatch>

Finally, check DirectoryIndex includes index.php
    DirectoryIndex index.php index.html

The php.ini and php-fpm.ini file can be found in:
    /usr/local/etc/php/7.4/

To have launchd start php now and restart at login:
  brew services start php

Or, if you don't want/need a background service you can just run:
  php-fpm



## On Windows

Use `xampp`  instead.
