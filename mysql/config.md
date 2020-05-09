# Setup & Configuration


## Setup


### On Mac

Just download mysql dmg package to install from [mysql](https://dev.mysql.com/downloads/mysql/5.7.html).
Also download [workbench](https://dev.mysql.com/downloads/workbench/) to handle with query, managing...


### On Windows

In Windows, we just install [mysql workbench](https://dev.mysql.com/downloads/workbench/) and install
`mysql` from workbench.


#### On Linux

We need manual install mysql from cli.

- Check whether mysql server is existed before install

   ```bash
   yum list mysql*-server
   ```

- Install your prefered version (eg,.5.7).

   ```bash
   sudo yum install mysql57-server
   ```

- Enable at boot time so mysql can auto start when we start OS

   ```bash
   sudo chkconfig mysqld on
   ```

- Start and check status

   ```bash
   sudo service mysqld start
   sudo service mysqld status
   ```

- Secure mysql server

   ```bash
   Set a password for root accounts.
   Remove root accounts that are accessible from outside the local host.
   Delete anonymous-user accounts.
   Erase the test database (which by default can be accessed by all users, even anonymous users), and privileges that permit anyone to access databases with names that start with test_.
   ```

   Enter `sudo /usr/bin/mysql_secure_installation`, do as below sample:

   ```
   New password: 
    
   Re-enter new password: 
    
   Estimated strength of the password: 100 
   Do you wish to continue with the password provided?(Press y|Y for Yes, any other key for No) : Y
   By default, a MySQL installation has an anonymous user,
   allowing anyone to log into MySQL without having to have
   a user account created for them. This is intended only for
   testing, and to make the installation go a bit smoother.
   You should remove them before moving into a production
   environment.
    
   Remove anonymous users? (Press y|Y for Yes, any other key for No) : Y
   Success.
    
    
   Normally, root should only be allowed to connect from
   'localhost'. This ensures that someone cannot guess at
   the root password from the network.
    
   Disallow root login remotely? (Press y|Y for Yes, any other key for No) : Y
   Success.
    
   By default, MySQL comes with a database named 'test' that
   anyone can access. This is also intended only for testing,
   and should be removed before moving into a production
   environment.
    
    
   Remove test database and access to it? (Press y|Y for Yes, any other key for No) : Y
    - Dropping test database...
   Success.
    
    - Removing privileges on test database...
   Success.
    
   Reloading the privilege tables will ensure that all changes
   made so far will take effect immediately.
    
   Reload privilege tables now? (Press y|Y for Yes, any other key for No) : Y
   Success.
    
   All done!
   ```
