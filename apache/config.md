# Apache Setup & Configuration

Document for Apache server.

---

## On macOS

>https://getgrav.org/blog/macos-catalina-apache-multiple-php-versions

This provides environment setup for multiple version of php server at local. Choose your OS type to proceed.

To setup apache server in macOS, following below instruction.

- Install `XCode` first and `Homebrew`.

   ```bash
      # xcode
      xcode-select --install

      # brew
      /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
   ```

- For Catalina version, install missing libraries.

   ```bash
      brew install openldap libiconv
   ```

- Install `httpd`.

   ```bash
      brew install httpd
   ```

   At now, we got settings:

   ```bash
      # apache config file at
      /usr/local/etc/httpd/httpd.conf
   ```

   Start apache service: `sudo brew services start httpd`

   We can control own `apachectl` via:

   ```bash
      sudo apachectl start
      sudo apachectl stop
      sudo apachectl -k restart
   ```

   Note that, `macOS` has own `apachectl` and configured at `etc/apache2/httpd.conf`,
   maybe we need remove them if needed by deleting (just comment with `#`) the following
   in `/etc/apache2/httpd.conf`.

   ```bash
   nano /etc/apache2/httpd.conf

   LoadModule userdir_module libexec/apache2/mod_userdir.so
   LoadModule alias_module libexec/apache2/mod_alias.so
   LoadModule rewrite_module libexec/apache2/mod_rewrite.so
   LoadModule php7_module libexec/apache2/libphp7.so
   ```

- Open config file `/usr/local/etc/httpd/httpd.conf` and change something.

   ```bash
   nano /usr/local/etc/httpd/httpd.conf

   # change port to 80
   Listen 8080
   ↓
   Listen 80

   # change document root
   DocumentRoot "/usr/local/var/www"
   ↓
   DocumentRoot "/Users/your_user/sites"

   # also change directory
   Directory "/usr/local/var/www"
   ↓
   Directory "/Users/your_user/sites"

   # change None to All under Directory
   AllowOverride None
   ↓
   AllowOverride All

   # change user and group
   User _www
   ↓
   User your_user

   Group _www
   ↓
   Group staff

   # change server name (uncomment first)
   ServerName www.example.com:8080
   ↓
   ServerName localhost
   ```

- Install various version of php.

   ```bash
   # it is needed for php from 5.6 to 7.1
   brew tap exolnet/homebrew-deprecated

   # choose which you want
   brew install php@5.6
   brew install php@7.0
   brew install php@7.1
   brew install php@7.2
   brew install php@7.3
   brew install php@7.4
   ```

   Note that, you may have the need to tweak configuration settings of PHP to your needs.
   A common thing to change is the memory setting, or the `date.timezone` configuration.
   The `php.ini` files for each version of PHP are located in the following directories:

   ```bash
   /usr/local/etc/php/5.6/php.ini
   /usr/local/etc/php/7.0/php.ini
   /usr/local/etc/php/7.1/php.ini
   /usr/local/etc/php/7.2/php.ini
   /usr/local/etc/php/7.3/php.ini
   /usr/local/etc/php/7.4/php.ini
   ```

   About switch php version. You no longer have to unlink each version between
   installing PHP versions as they are not linked by default. Note that,
   if you got error then maybe try with `brew switch openssl 1.0.2n`.

   ```bash
   # switch from 7.4 to 5.6
   brew unlink php@7.4 && brew link --force --overwrite php@5.6
   ```

- Enable php in `/usr/local/etc/httpd/httpd.conf`

   Make sure below code is enabled in config file.

   ```bash
   LoadModule rewrite_module lib/httpd/modules/mod_rewrite.so
   ```

   This is config file example when use php 5.6.

   ```bash
   LoadModule php5_module /usr/local/opt/php@5.6/lib/httpd/modules/libphp5.so
   #LoadModule php7_module /usr/local/opt/php@7.0/lib/httpd/modules/libphp7.so
   #LoadModule php7_module /usr/local/opt/php@7.1/lib/httpd/modules/libphp7.so
   #LoadModule php7_module /usr/local/opt/php@7.2/lib/httpd/modules/libphp7.so
   #LoadModule php7_module /usr/local/opt/php@7.3/lib/httpd/modules/libphp7.so
   #LoadModule php7_module /usr/local/opt/php@7.4/lib/httpd/modules/libphp7.so
   ```

   Set indices for php by replacing as below.

   ```bash
   <IfModule dir_module>
      DirectoryIndex index.html
   </IfModule>
   ↓
   <IfModule dir_module>
      DirectoryIndex index.php index.html
   </IfModule>

   <FilesMatch \.php$>
      SetHandler application/x-httpd-php
   </FilesMatch>
   ```

- Finally, restart server as below and hit`localhost/` on your browser.

   ```bash
   sudo apachectl -k stop
   sudo apachectl start
   ```

#### Serves on multiple sites

- To make apache listen on multiple sites, we need turn on `httpd-vhosts`.

   ```bash
   nano /usr/local/etc/httpd/httpd.conf

   # make sure apache is listening on port 80 (at /usr/local/etc/httpd/httpd.conf)
   Listen 80

   # turn on vhost by remove comment below line (at /usr/local/etc/httpd/httpd.conf)
   Include /usr/local/etc/httpd/extra/httpd-vhosts.conf

   # at next, add your defined server (at /usr/local/etc/httpd/extra/httpd-vhosts.conf)
   nano /usr/local/etc/httpd/extra/httpd-vhosts.conf
   
   <VirtualHost *:80>
      DocumentRoot "C:\xampp\phpMyAdmin"
      ServerName phpmyadmin.local
      ErrorLog "C:\xampp\phpMyAdmin\log_error.txt"
      CustomLog "C:\xampp\phpMyAdmin\log_access.txt" common

      <Directory C:\xampp\phpMyAdmin>
         AllowOverride all
         Require all granted
      </Directory>
   </VirtualHost>

   # finally, register above site in /etc/hosts
   nano /etc/hosts
   127.0.0.1 phpmyadmin.local
   ```


## On Windows

> By default, Apache has no binary version, so we need download binary one from other site.

- Install [Visual C++ Redistributable for Visual Studio 2015-2019 x64](https://aka.ms/vs/15/release/VC_redist.x64.exe)

- Download binary apache from [ApacheLounge](https://www.apachelounge.com/download/).
Unzip the Apache24 folder to `C:/Apache24` (that is the ServerRoot in the config).
The default folder for your your webpages is DocumentRoot `C:/Apache24/htdocs`.
When you unzip to an other location, change Define SRVROOT `C:/Apache24`  in `httpd.conf`, for example to `E:/Apache24`.

- Install apache as Windows service by open cmd as Administrator:

   ```bash
   cd C:/Apache24/bin
   httpd.exe -k install -n "Apache HTTP Server"
   ```

- Start apache and test it work in browser `http://localhost`

   ```bash
   httpd.exe
   ```

- Shut down Apache by pressing `Ctrl+C` (It may take a few seconds)

- You can `start/stop` the service with the command:

  ```bash
  services.msc
  ```

- To see all Command line options:

  ```bash
  httpd -h
  ```

- ApacheMonitor:

Double click ApacheMonitor.exe, or put it in your Startup folder.
