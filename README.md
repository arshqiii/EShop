# EShop - ADV Shop
## Muhammad Radhiya Arshq - 2306275885
Link Deployment : https://strict-merry-arshqiii-55ae2877.koyeb.app/

<details>
    <summary><b>Tutorial 1</b></summary>
  
---
  
### Refleksi 1

> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code. If you find any mistake in your source code, please explain how to improve your code.

Dari mengerjakan Tutorial dan exercise, saya menggunakan beberapa coding standards dalam mengimplementasi fitur-fitur aplikasi seperti :

- Menggunakan nama yang bermakna pada variabel seperti productID, productName dan pada nama function seperti create, update, delete yang dapat dimengerti dengan jelas
- Menerapkan One Function One Task, saya membuat function findByID untuk mencari product berdasarkan ID, dan fungsi ini hanya melakukan hal tersebut sama seperti create, update dan delete.
- Menggunakan format yang seragam, seperti indentation yang rapi, penggunaan camelCase untuk variabel dan metode, serta PascalCase untuk nama kelas.
- Beberapa fungsi yang memiliki pola serupa telah direfaktor ke dalam metode yang dapat digunakan kembali agar lebih modular dan mudah dikelola seperti pada update dan delete menggunakan fungsi findByID untuk mendapat objek product.

Kode yang saya tulis sudah cukup memenuhi prinsip clean code dan secure coding, namun masih ada beberapa aspek yang bisa ditingkatkan untuk meningkatkan maintainability dan keamanan sistem. 
Ini dapat dilakukan dengan menggunakan UUID dalam pembuatan produk. Ini karena sebelumnya ketika objek product dibuat, ID dari objek tersebut null/kosong sehingga harus dibuat id unik, ini bisa menggunakan UUID atau counter. 
Lalu mungkin dapat menggunakan data struktur yang lebih baik untuk menyimpan data produk dibanding sebuah list. Selain itu juga tidak terdapat exception jika pengguna mengisi quantity product kurang dari nol.

---

### Refleksi 2
> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

Menurut saya, sebuah unit test harus dibuat dengan tujuan menguji suatu fitur yang dibuat. Setiap fitur penting harus memiliki minimal 1 test. 
Dengan dibuat unit test proses debugging menjadi lebih mudah karena saya bisa dengan cepat mengidentifikasi bagian kode yang mengalami kesalahan. 
100% Code Coverage tidak menunjukkan kode yang ditulis tidak memiliki bug atau error, ini karena bisa saja terdapat edge-case yang tidak dicek oleh unit test.

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.
> What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

Jika disuruh membuat sebuah functional test dengan membuat kode yang sama maka kita akan mengurangi kualitas kode dengan meningkatkan redundansi, mengurangi keterbacaan, meningkatkan kompleksitas pemeliharaan, dan melanggar prinsip Don't Repeat Yourself (DRY).
Kita bisa tetap menjaga kebersihan kode dengan menggunakan pendekatan seperti membuat superclass abstrak yang menangani setup umum untuk testing sehingga mengurangi duplikasi kode dan menjaga kebersihan kode
  
</details>

<details>
    <summary><b>Tutorial 2</b></summary>

---

### Refleksi
> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

Selama mengerjakan exercise ini, saya menemukan beberapa code quality issues yang dideteksi oleh workflow Scorecard dan PMD. Berikut adalah perbaikan yang saya lakukan:
- Token-Permission : Menambahkan properti permissions di bagian atas file workflow untuk membatasi akses GITHUB_TOKEN, sehingga mengurangi potensi risiko keamanan.
- Dependency-Update-Tool : Mengintegrasikan Dependabot sebagai alat pembaruan dependensi otomatis, sehingga dapat mendeteksi dependensi yang sudah outdated dan menyarankan pembaruan secara berkala.
- Refactor Kode untuk Peningkatan Kualitas : Menghapus modifier public pada metode dalam interface karena tidak diperlukan serta Memperbaiki struktur if-else statements dengan menggantinya menggunakan return langsung dari hasil conditional test, sehingga kode menjadi lebih bersih dan mudah dibaca.
- Branch Protection : Menambahkan aturan perlindungan (branch protection rules) untuk membatasi creation, update, dan delete pada branch main, sehingga memastikan hanya perubahan yang telah diverifikasi yang bisa masuk ke dalam production.

---

> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Menurut saya, implementasi CI/CD yang diterapkan dalam proyek ini sudah memenuhi definisi Continuous Integration (CI) dan Continuous Deployment (CD). Workflow ci.yml di GitHub Actions secara otomatis membangun proyek dan menjalankan unit testing setiap kali ada push ke repositori (CI), proses ini diperkuat dengan alat bantu seperti PMD, Dependabot, Scorecard, serta unit test yang memastikan kualitas kode tetap terjaga. Proyek ini juga menerapkan mekanisme auto deploy menggunakan PaaS Koyeb, sehingga setiap perubahan yang dilakukan dalam repositori akan langsung diterapkan ke lingkungan produksi tanpa perlu intervensi manual (CD). Dengan kombinasi CI/CD ini, integrasi dan penerapan perubahan dalam proyek menjadi lebih cepat dan andal.

</details>

<details>
    <summary><b>Tutorial 3</b></summary>

---

### Refleksi

> 1) Explain what principles you apply to your project!

Dalam proyek ini, saya menerapkan SOLID principles untuk memastikan kode lebih terstruktur, mudah dipelihara, dan scalable. Berikut adalah bagaimana prinsip tersebut saya terapkan:

-  Single Responsibility Principle (SRP) – Saya memisahkan tanggung jawab di dalam proyek, misalnya dengan membuat controller terpisah untuk Homepage, Car, dan Product. Ini memastikan setiap kelas hanya menangani satu aspek fungsionalitas.

- Open/Closed Principle (OCP) – Saya membuat model abstrak `BaseModel`, yang memungkinkan model lain untuk melakukan ekstensi tanpa perlu memodifikasi kode yang sudah ada. Dengan begitu, kode tetap stabil meskipun ada fitur baru yang ditambahkan.

- Liskov Substitution Principle (LSP) – Saya memastikan bahwa subclass yang dibuat dapat menggantikan superclass tanpa mengubah perilaku aplikasi. Contohnya, saya dengan adanya atribut Color pada subclass Car, yang tidak mengganggu superclassnya `BaseModel`.

- Interface Segregation Principle (ISP) – Saya menghindari dependensi yang tidak perlu dengan memastikan hanya kelas tertentu yang bergantung pada interface yang mereka butuhkan. Misalnya, `CarServiceImpl` dan `ProductServiceImpl` hanya mengimplementasikan interface yang relevan dengan fungsinya.

- Dependency Inversion Principle (DIP) – Saya mengganti instance langsung dari `CarServiceImpl` dalam Controller dengan interface `CarService`. Dengan cara ini, kode lebih fleksibel dan tidak bergantung pada implementasi spesifik, sehingga lebih mudah diuji dan dikembangkan.

---
> 2) Explain the advantages of applying SOLID principles to your project with examples.

Menggunakan prinsip SOLID dalam proyek Spring Boot memberikan banyak keuntungan:

- Maintainability yang Lebih Baik : Dengan SRP, kode lebih bersih dan terorganisir. Setiap perubahan hanya perlu dilakukan di satu tempat tanpa mengganggu bagian lain.

- Kemudahan dalam Pengembangan dan Ekstensi : OCP memastikan bahwa fitur baru dapat ditambahkan tanpa merusak kode lama. Jika saya ingin menambahkan model atau fitur baru, saya cukup membuat subclass atau meng-extend yang sudah ada.

- Pewarisan yang Lebih Aman dan Stabil : LSP memastikan bahwa subclass bisa menggantikan superclass tanpa masalah. Ini mencegah bug akibat perilaku yang tidak sesuai ketika pewarisan digunakan.

- Kode yang Modular dan Fleksibel : ISP membantu memecah dependensi yang tidak perlu, sehingga kelas hanya menggunakan interface yang relevan dengan tugasnya. Ini mencegah kode yang membengkak dengan metode yang tidak digunakan.

- Kemudahan dalam Pengujian dan Pengembangan : DIP membuat kode lebih fleksibel karena bergantung pada abstraksi, bukan implementasi konkret. Dengan cara ini, pengujian unit dan penggantian layanan menjadi lebih mudah, yang mempercepat pengembangan.

---
> 3) Explain the disadvantages of not applying SOLID principles to your project with examples.

Tanpa menerapkan prinsip SOLID, proyek dapat mengalami berbagai tantangan yang memperlambat pengembangan dan meningkatkan risiko bug. Beberapa konsekuensinya adalah:

- Kode Sulit Dipelihara (Maintainability): kode cenderung menjadi tidak terstruktur dan sulit dipahami. Perubahan kecil pada satu bagian kode dapat memengaruhi bagian lain secara tidak terduga, sehingga meningkatkan risiko bug dan mempersulit proses pemeliharaan.

- Kode Sulit Diperluas (Extensibility): Tanpa prinsip seperti Open/Closed Principle (terbuka untuk ekstensi, tertutup untuk modifikasi), menambahkan fitur baru menjadi lebih rumit dan berisiko. Perubahan sering kali memerlukan modifikasi besar pada kode yang sudah ada, yang dapat merusak fungsionalitas yang sudah berjalan.

- Tingginya Keterikatan (High Coupling): Jika prinsip seperti Dependency Inversion Principle tidak diterapkan, komponen-komponen dalam sistem menjadi sangat tergantung satu sama lain. Hal ini membuat kode sulit diisolasi, diuji, dan digunakan kembali.

- Kesulitan dalam Pengujian (Testing): Kode yang tidak mengikuti SOLID cenderung sulit diuji secara terpisah (unit testing). Ketergantungan yang tinggi antara komponen membuat pengujian otomatis menjadi lebih kompleks dan memakan waktu.

- Biaya Pengembangan yang Lebih Tinggi: Dalam jangka panjang, kode yang tidak mengikuti SOLID akan membutuhkan lebih banyak waktu dan sumber daya untuk dipelihara, diperbaiki, dan dikembangkan. Hal ini dapat meningkatkan biaya pengembangan secara signifikan.

---

</details>

<details>
    <summary>Tutorial 4</summary>

---

### Refleksi 
> Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

Secara keseluruhan, dengan melakukan TDD pada tutorial ini saya dapat memastikan bahwa fitur berjalan sesuai dengan ekspektasi dan menjaga kualitas kode. Namun, ada beberapa aspek yang masih bisa saya tingkatkan, terutama dalam cakupan pengujian dan efisiensi kode. Namun untuk iterasi berikutnya, saya akan lebih fokus pada perencanaan skenario uji sebelum menulis implementasi karena saya merasa saya kurang strategis dalam merancang skenario uji sebelum mulai menulis implementasi, sehingga tidak semua pengujian selalu benar-benar mencerminkan kebutuhan sistem secara menyeluruh.

---

> You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.

-  Fast: Tes case yang saya buat dapat berjalan cukup cepat karena menggunakan mocking untuk menghindari ketergantungan pada database.

- Independent: Beberapa tes masih sudah independen dari class atau function lain, namun masih terdapat yang bergantung pada status objek yang sama, seperti yang menggunakan notasi @BeforeEach. 

- Repeatable: Tes dapat dijalankan berkali-kali dengan hasil yang konsisten karena menggunakan input yang dikontrol.

-  Self-Validating: Tes menggunakan assertions yang jelas untuk menentukan apakah hasilnya benar atau salah.

- Timely: Tes yang saya buat dibuat sebelum dan diperbaiki seiring penulisan kode sehingga saya dapat memahami kemungkinan input dan output fungsi yang dibuat. 

Secara keseluruhan, pengujian sudah cukup mengikuti prinsip F.I.R.S.T., tetapi masih ada ruang untuk perbaikan dalam hal independensi dan timeliness dari tes yang dibuat.

---
</details>


