# EShop - ADV Shop
## Muhammad Radhiya Arshq - 2306275885

### Refleksi 1

Dari mengerjakan Tutorial dan exercise, saya menggunakan beberapa coding standards dalam mengimplementasi fitur-fitur aplikasi seperti :

    - Menggunakan nama yang bermakna pada variabel seperti productID, productName dan pada nama function seperti create, update, delete yang dapat dimengerti dengan jelas
    - Menerapkan One Function One Task, saya membuat function findByID untuk mencari product berdasarkan ID, dan fungsi ini hanya melakukan hal tersebut sama seperti create, update dan delete.
    - Menggunakan format yang seragam, seperti indentation yang rapi, penggunaan camelCase untuk variabel dan metode, serta PascalCase untuk nama kelas.
    - Beberapa fungsi yang memiliki pola serupa telah direfaktor ke dalam metode yang dapat digunakan kembali agar lebih modular dan mudah dikelola seperti pada update dan delete menggunakan fungsi findByID untuk mendapat objek product.

Kode yang saya tulis sudah cukup memenuhi prinsip clean code dan secure coding, namun masih ada beberapa aspek yang bisa ditingkatkan untuk meningkatkan maintainability dan keamanan sistem. 
Ini dapat dilakukan dengan menggunakan UUID dalam pembuatan produk. Ini karena sebelumnya ketika objek product dibuat, ID dari objek tersebut null/kosong sehingga harus dibuat id unik, ini bisa menggunakan UUID atau counter. 
Lalu mungkin untuk dapat menggunakan data struktur yang lebih baik untuk menyimpan data produk dibanding sebuah list. 



### Refleksi 2
> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.

> What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

