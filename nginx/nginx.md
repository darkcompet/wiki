# Nginx

Configuration document for `nginx` server.

---

## Setup and Control

#### On macOS

- Install `nginx` via `HomeBrew`:

   ```bash
      brew install nginx
   ```

   After installed, we got settings info:

   ```
   Config: /usr/local/etc/nginx/nginx.conf
   Servers info: /usr/local/etc/nginx/servers/
   Default home: /usr/local/Cellar/nginx/1.17.8/html/
   Document root: /usr/local/var/www/
   Log file: /usr/local/var/log/nginx/
   ```

- Control server:

```bash
   # start server
   nginx

   # stop server
   nginx -s stop

   # reload server (eg. after change config)
   nginx -s reload
```

#### On Linux

- Installation.

   ```bash
   # update OS first
   sudo yum update
   ```

   - Install nginx open source and verify it.

   ```bash
   sudo yum install nginx
   sudo nginx -v
   ```

   After installed, we got below config (should change config to put source code to our choice):

   ```
   Config: /etc/nginx/nginx.conf
   Source code: /usr/share/nginx/html
   ```

   - Auto-start server at boot time.

   ```bash
   sudo chkconfig nginx on
   ```

- Control server.

   ```bash
   # check running status
   sudo service nginx status

   # start server
   sudo service nginx start

   # stop server
   sudo service nginx stop

   # restart or reload server
   sudo service nginx restart
   sudo service nginx reload
   ```
