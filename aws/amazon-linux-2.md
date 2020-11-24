# Server

For eg,. we are going to create new server `ketquathethao`


## Create server

>See https://docs.nginx.com/nginx/deployment-guides/amazon-web-services/ec2-instances-for-nginx/

- Choose `Amazon Linux 2 AMI (HVM), SSD Volume Type - ami-03faaf9cde2b38e9f (64 ビット x86) / ami-0d8566954ee6c6bb1 (64 ビット Arm)`
- Select free instance `t2.micro`
- At tab `instance setting`, do nothing
- At tab `add storage`, add up to 30GB
- At tab `tag`, add a tag , for eg,.`sitename`/`ketquathethao`
- At tab `security`, add http and https protocol
- Click launch -> enter new key pair, for eg,. `ketquathethao` -> click download `.pem` file -> Done.


## Setup server

- Connect to server via ssh

   ```bash
   sudo ssh -i "ketquathethao.pem" ec2-user@ec2-54-169-162-39.ap-southeast-1.compute.amazonaws.com
   echo "Check os info"
   cat /etc/os-release
   ```

- Install nginx

   ```bash
   # Install nginx
   sudo amazon-linux-extras install epel
   sudo amazon-linux-extras install nginx1
   # Start nginx and Enable nginx when boot
   sudo systemctl start nginx
   sudo systemctl enable nginx
   # Issue permission, pls change: `user` and `group` to `nginx`
   egrep '^(user|group)' /etc/nginx/nginx.conf
   ```

- Install git

   ```bash
   echo "Install git"
   sudo yum install git
   ```

- Install mariadb 10.5

   ```bash
   echo "See https://stackoverflow.com/questions/39025524/how-to-install-mysql-5-7-on-amazon-ec2"
   sudo  amazon-linux-extras | grep mariadb
   echo "Add repo"
   sudo tee /etc/yum.repos.d/mariadb.repo<<EOF
[mariadb]
name = MariaDB
baseurl = http://yum.mariadb.org/10.5/centos7-amd64
gpgkey=https://yum.mariadb.org/RPM-GPG-KEY-MariaDB
gpgcheck=1
EOF
   
   echo "Make cache"
   sudo yum makecache
   echo "List repo"
   sudo yum repolist
   echo "Install mariadb"
   sudo yum install MariaDB-server MariaDB-client
   echo "Start and enable service"
   sudo systemctl enable --now mariadb
   echo "Check status"
   systemctl status mariadb
   echo "Secure"
   sudo mysql_secure_installation
   ```

- Setup mariadb password for all projects

   ```bash
   mysql -u root -p
   SET PASSWORD = PASSWORD('your_new_password');
   \q
   ```

- Install php

   ```bash
   # Reference: https://techviewleo.com/install-php-7-on-amazon-linux/
   # OK JP: https://qiita.com/nagahama/items/2fdc820791bee5d564ca
   # Check linux extras, if no result is returned, then install it
   which amazon-linux-extras
   sudo yum install -y amazon-linux-extras
   # Confirm which topic is available
   sudo  amazon-linux-extras | grep php
   sudo amazon-linux-extras enable php7.4
   # Now install more packages
   sudo yum clean metadata
   sudo yum install php-cli php-pdo php-fpm php-json php-mysqlnd
   sudo yum install php php-{pear,cgi,common,curl,mbstring,gd,mysqlnd,gettext,bcmath,json,xml,fpm,intl,zip,imap}
   
   # This is worked version (at tisobongda)
   sudo yum install php
   sudo amazon-linux-extras install epel #Amazon Linux でepel-releaseパッケージをインストール出来るようにする
   sudo yum install epel-release #epel-releaseパッケージをインストール
   sudo rpm -Uvh http://rpms.famillecollet.com/enterprise/remi-release-7.rpm #remiリポジトリを使えるようにする
   sudo yum install -y php74 php74-php php74-php-fpm
   sudo ln -s /usr/bin/php74 /usr/bin/php
   php -v # or: php --version
   ```
   
   ** Tips: To switch php version, for eg,. switch to php7.2 by run below

   ```bash
   sudo amazon-linux-extras disable php7.4
   sudo amazon-linux-extras disable php7.3
   sudo amazon-linux-extras enable php7.2
   sudo yum install php php-{pear,cgi,common,curl,mbstring,gd,mysqlnd,gettext,bcmath,json,xml,fpm,intl,zip,imap}
   ```


## For each project

- Create new file `/etc/nginx/conf.d/ketquathethao.com.conf` with below content, then run `sudo service nginx reload`

   ```bash
   # listen for ketquathethao.com domain
   server {
      listen       80;
      server_name  ketquathethao.com;

      #auth_basic "Cosmos Initia";
      #auth_basic_user_file /var/www/cosmos/.htpasswd;

      root  /var/www/ketquathethao/tisobongda/public;
      index index.php index.html;

      access_log /var/www/ketquathethao/access.log;
      error_log /var/www/ketquathethao/error.log;

      #location /internal_data/ { internal; }
      #location /library/ { internal; }
      #location = /crossdomain.xml {}

      client_max_body_size 20M;

      location / {
         #allow 211.15.59.138;
         #allow 211.15.59.139;
         #allow 171.245.250.70;

         #deny all;

         try_files  $uri $uri/ /index.php?$uri&$args;
      }

      location ~ /\.ht { deny  all; }

      location ~ \.php$ {
         try_files  $uri /index.php;
         fastcgi_pass   unix:/var/run/php-fpm/www.sock;
         fastcgi_index  index.php;
         fastcgi_param  SCRIPT_FILENAME  $document_root$fastcgi_script_name;
         include        fastcgi_params;
         fastcgi_buffers 256 8k;
      }
   }
   ```

- Create database for the project

   ```bash
   mysql -u root -p
   create database tisobongda;
   \q
   ```

- Create logs and Clone project

   ```bash
   cd /var
   sudo mkdir www
   cd www
   sudo mkdir ketquathethao
   cd ketquathethao
   sudo touch access.log error.log
   sudo git clone https://github.com/darkcompet/tisobongda.git
   ```

- Copy `vendor` to project

   ```bash
   cd /var/www/tmp
   sudo git pull
   # sudo git clone https://darkcompet@github.com/darkcompet/tmp.git
   sudo cp ketquathethao-vendor.zip ../ketquathethao/tisobongda
   cd ../ketquathethao/tisobongda
   sudo unzip ketquathethao-vendor.zip
   sudo rm ketquathethao-vendor.zip
   ```

- Config project permissions

   ```bash
   sudo chmod -R 0777 storage
   sudo chmod -R 0777 bootstrap
   ```

- Prepare Laravel application

   ```bash
   echo "Generate app key"
   sudo php artisan key:generate
   ```
