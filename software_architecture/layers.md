# Layers

Layers Design Pattern for software development.

---

## Key Concepts

> Keywords: Isolation, Monolithic, Couple.

- Like `MVC` design pattern (shortly `DP`), Layers breaks an application to some `isolated layers`,
and `coupling layers` for communication.

- By default, Layers contains 3 main layers: `Presentation`, `Business` and `Persistence`. And 1 support layer called
as `Common` to work with others.

---

## Description

- `Presentation` layer: Contains View components and its logic.

- `Business` layer: Holds logic of the app, and is bridge between Presentation layer and Persistence layer.

- `Persistence` layer: Work directly with database, remote server.

- `Common` package: Work as a library, so all layers can invoke directly to it.

Because of `isolation` concept, all layers `Presentation`, `Business` and `Persistence` should `only`
communicate to nearest layer from top to down direction.

We can figure out it as below illustration:

   ```bash
   Presentation ⇔ Common
      ↑↓
   Business ⇔ Common
      ↑↓
   Persistence ⇔ Common
   ```

Now, it is important time to note that: `Presentation` call `Business`, then `Business` call `Persistence`.
After that, result will be returned by this way: `Persistence` → `Business` → `Presentation`.

Also note that, `Presentation` should `not` invoke directly to `Persistence` and vice versa. And `Business`
must `not` call `Presentation` since have no `bridge` inside `Presentation`.

---

## Example

> Layers design pattern is suitable for `monolithic` applications.

When implement a feature, always take care of `isolation` concept.

Now, start by create an example of `sending email to client` by start from `Presentation` layer.


- In `Presentation`, suppose we have a controller class which receives some input from request.


   ```php
   namespace App\Presentation\Controllers;

   class MailController {
      public function sendMailToClient($customer_id) {
         // Validate input
         if ($customer_id == null) {
            return view("customer.invalid_customer");
         }

         // Prepare data to call api in business
         $address = "https://darkcompet.com";
         $subject = "Weekend Saling Status Report";
         $time = Carbon::now();
         $body = Business::mailLogic()->getMailBodyForCustomer($customer_id, $time);

         // Call send mail api in business
         $ok = Business::mailLogic()->sendMail($address, $subject, $body);

         return view("customer.index");
      }
   }
   ```

- At `Business`, create a `bridge` class named as `Business.php` for accessing from `Presentation`

   ```php
   namespace App\Business;

   // This class provides open apis for access from Presetation layer.
   class Business {
      public static function mailLogic() {
         return new MailLogic();
      }
   }
   ```

   ```php
   namespace App\Business\Logic;

   // All internal apis of Business layer should be hidden from outside.
   class MailLogic {
      public void sendMail($address, $subject, $body) {
         // Access to persistence layer
         return Persistence::mailService()->sendMail($address, $subject, $body);
      }
   }
   ```


- In `Persistence`, we create a open api supporter class `Persistence.php` for accessing from `Business` as below:

   ```php
   namespace App\Persistence;

   // This class provides open apis for access from Business layer.
   class Persistence {
      public static function mailService() {
         return new MailService();
      }
   }
   ```

   ```php
   namespace App\Persistence\Service;

   // All internal apis of Persistence layer should be hidden from outside.
   class MailService {
      public bool sendMail($address, $subject, $body) {
         // Suppose this call 3rd party service...
         return true;
      }
   }
   ```

---

## Discussion

From above example, we got some tips about `Layers` design pattern.

- Class in `Presentation` should `only` call `Business.php` at `App\Business`. And should `not`
call classes in `Persistence`.

- Logic classes in `Business` should `only` call `Persistence.php` at `App\Persistence`. And should `not`
call classes in `Presentation`.

- Class in `Persistence` should `only` work internally. And should `not` call classes in `Presentation` and `Business`.

- Each class `Logic` under `Business\Logic` should call mutually, instead of calling `Persistence`
since we maybe lost logic of the app.

---

## Reference

1. [software-architecture-patterns](https://www.oreilly.com/library/view/software-architecture-patterns/9781491971437/ch01.html)

2. [software-architecture](https://dzone.com/articles/software-architecture-the-5-patterns-you-need-to-k)
