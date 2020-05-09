# PhpMyAdmin Configuration


## Setup

- Download phpMyAdmin from official site

- Copy file `config.sample.inc` to `config.inc`

- Edit or Add below lines to copied file

   ```bash
   $cfg['Servers'][$i]['host'] = 'localhost';
   $cfg['Servers'][$i]['port'] = '3306';
   $cfg['Servers'][$i]['password'] = '1234';
   ```
