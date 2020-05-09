# Kilobytes Coding Conventions for Java

> @infor: info@kilobytes.com.vn  
> @author: hoa.nb@kilobytes.com.vn  
> @author: co.vp@kilobytes.com.vn

## Terminology

```
   Variable: biến số
   Constant: hằng số (final, static final)
   Method: hàm số
   Field: biến số (trong class)
```

## Naming

#### Variable

> Có 2 kiểu biến: `local` (trong method) và `field` (trong class). Cách đặt tên như nhau và ko phân biệt. Trường hợp tên biến tại local trùng với field, sẽ dùng từ khóa `this` để gọi tới.

- Viết hoa hết và phân biệt các từ bằng `underscore` cho các constant. Ví dụ:

```java
   final int AGE_OF_USER = 100;
   public static final String MIDDLE_NAME = "abc";
```

- Còn lại tất cả các biến khác, sẽ được bắt đầu với `lowercase`. Ví dụ:

```java
   int ageOfUser = 100;
   static String commonUserName = "test";
```

#### Class, Method

- Ký tự đầu của class sẽ là `uppercase`, ngược lại với method là `lowercase`.

- Vì class là đối tượng, nên tên sẽ chỉ chứa `danh từ hoặc tính trạng từ`, ko chứa động từ. Ngược lại, các method thể hiện hành động của đối tượng đó, nên tên sẽ được bắt đầu với `động từ`. Ví dụ:

```java
   public class ImageDecoder {
      public void decodeImage(Bitmap image) {
         rotateImageAsync(image)
         //todo
      }

      private void rotateImageAsync(Bitmap image) {
         //todo
      }
   }
```

## Format

#### Whitespace (tab, space, linefeed...)

> Các ký tự whitespace ngoài việc làm source code dễ nhìn ra, còn giúp lúc review dễ nắm bắt bố cục của chương trình hơn.

- Khi nào dùng Tab:
   - Khi muốn tạo hierachy-level của đoạn code tiếp theo.
   - Khi muốn indent các dòng khai báo biến.

- Nên dùng space khi nào:
   - Dùng space sau mỗi thao tác để thể hiện sự tách biệt với thao tác kế tiếp. Ví dụ sau mỗi keyword (int, String, for, while, do...), operator (=, +, -, *, /, ++, -=, *=...).

- Dùng linefeed khi nào:
   - Để phân vùng các cụm code. Thường trước mỗi vòng loop (for, while...), hoặc sau khi thực hiện xong 1 việc (khai báo, xử lý) trong 1 chuỗi thao tác.

```java
   public void sortAsBubble(int[] arr) {
      final int N = arr.length;

      for (int i = 0; i < N; ++i) {
         int indexOfMin = i;
         int min = arr[i];

         for (int j = i + 1; j < N; ++j) {
            if (min > arr[j]) {
               min = arr[j];
               indexOfMin = j;
            }
         }

         if (indexOfMin != i) {
            arr[i] ^= arr[indexOfMin];
            arr[indexOfMin] ^= arr[i];
            arr[i] ^= arr[indexOfMin];
         }
      }
   }
```

## Optimize

> Để có 1 phần mềm chạy mượt và chi phí bảo trì thấp, cần nên tối ưu trên 2 phương diện: cấu trúc source và hiệu năng code.

- Tối ưu cấu trúc source code:
   - Làm code dễ hiểu và ngắn gọn hơn. Ví dụ cách cleanup sau:

   ```java
      // Assure given x is not smaller than min, also not bigger than max.
      int lerp(int x, int min, int max) {
         if (x < min) {
            x = min;
         }
         if (x > max) {
            x = max;
         }

         return x;
      }

      ↓↓

      // Assure given x is not smaller than min, also not bigger than max.
      int lerp(int x, int min, int max) {
         return Math.max(min, Math.min(max, x));
      }
   ```

- Tối ưu hiệu năng code:
   - Cách iterate các phần tử trong vòng lặp for, while. Ví dụ cách tối ưu sau:

   ```java
      List<User> users;

      for (int i = 0; i < users.size(); ++i) {
         User user = users.get(i);
         //todo
      }

      ↓↓

      for (int i = 0, N = users.size(); i < N; ++i) {
         User user = users.get(i);
         //todo
      }

      ↓↓

      for (int i = users.size() - 1; i >= 0; --i) {
         User user = users.get(i);
         //todo
      }

      ↓↓

      for (User user : users) {
         //todo
      }
   ```

   - Loại bỏ check ko cần thiết trong mỗi vòng lặp. Ví dụ:

   ```java
      for (int i = 0; i < 100_000; ++i) {
         for (int j = 0; j < 100_000; ++j) {
            if (i * i - 2 * i + 9 > 1000_000_000) {
               //todo
            }
         }
      }

      ↓↓

      for (int i = 0; i < 100_000; ++i) {
         if (i * i - 2 * i + 9 > 1000_000_000) {
            for (int j = 0; j < 100_000; ++j) {
               //todo
            }
         }
      }
   ```

## Reference

- [Google Java Style Guide
](https://google.github.io/styleguide/javaguide.html)
