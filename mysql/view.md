# View


## Creation

- Create view

   ```sql
   SELECT * FROM mysql.user;
   
   DROP view auth_users_view;
   
   CREATE 
      ALGORITHM = UNDEFINED 
      DEFINER = `root`@`localhost` 
      SQL SECURITY DEFINER
   VIEW `auth_users_view` AS
      SELECT 
         `auth`.`company_login` AS `company_login`,
         `auth`.`user_login` AS `user_login`,
         `auth`.`password` AS `password`,
         `auth`.`type` AS `auth_type`,
         `users`.`auth_id` AS `auth_id`,
         `users`.`user_id` AS `user_id`,
         `users`.`main` AS `main`,
         `users`.`type` AS `type`,
         `users`.`registration_type` AS `registration_type`,
         `users`.`role_id` AS `role_id`,
         `users`.`main_img` AS `main_img`,
         `users`.`created` AS `created`,
         `users`.`created_user` AS `created_user`,
         `users`.`modified` AS `modified`,
         `users`.`modified_user` AS `modified_user`
      FROM
         (`users`
         JOIN `auth` ON ((`users`.`auth_id` = `auth`.`auth_id`)))
   ```
