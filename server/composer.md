# Composer

## Installation

#### On Linux

- Run below instruction

   ```bash
   #  install the PHP CLI (command line interface) package and all other dependencies
   sudo yum install php-cli php-zip wget unzip

   # download the Composer installer script
   php -r "copy('https://getcomposer.org/installer', 'composer-setup.php');"

   # install Composer in the /usr/local/bin directory
   sudo php composer-setup.php --install-dir=/usr/local/bin --filename=composer
   ```

#### On Mac

- Run below instruction

   ```bash
   php -r "copy('https://getcomposer.org/installer', 'composer-setup.php');"
   php -r "if (hash_file('sha384', 'composer-setup.php') === 'e0012edf3e80b6978849f5eff0d4b4e4c79ff1609dd1e613307e16318854d24ae64f26d17af3ef0bf7cfb710ca74755a') { echo 'Installer verified'; } else { echo 'Installer corrupt'; unlink('composer-setup.php'); } echo PHP_EOL;"
   php composer-setup.php
   php -r "unlink('composer-setup.php');"

   # if not exist, please create
   mkdir -p /usr/local/bin

   mv composer.phar /usr/local/bin/composer
   ```
