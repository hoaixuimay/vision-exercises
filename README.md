# vision-exercises

 Một người giàu có đến từ thành Babylon muốn sở hữu một nơi đáng sống nhất. Anh ta muốn một nơi đáng sống nhất phải thuận tiện đi lại từ thành phố này qua thành phố khác và không bị hạn chế di chuyển. Bạn trong vai người tư vấn, hãy đưa cho anh ta một nơi rộng nhất, tức là nơi có nhiều thành phố nhất, và có thể di chuyển bất cứ đâu trong thành phố. Hãy đề nghị anh ta số thành phố  mà anh ta có thể sở hữu.
 
 Input:
 ```
 C : tổng số  thành phố
 R : tổng số  con đường
 X Y: đường hai chiều từ thành phố X tới thành phố  Y
 
 Điều kiện
 0 < C < 10000
 0 < R <= 49995000
 0 < X, Y <= 10000, X != Y
 ```
 Output:
 ```
 K : số thành phố  (nơi đáng sống nhất) người đến từ Babylon có thể mua.
 ```
 Example 1:
 Input:
 ```
 3
 2
 1 2
 2 3
```
 Output:
 ```
 3
```

 Example 2:
 Input:
 ```
 7
 8
 4 5
 5 6
 6 4
 1 2
 2 3
 1 3
 7 3
 7 1
```
 Output:
 ```
 4
 ```
 
 Giải thích:
 ```
 Ví dụ 1: Cho 3 thành phố  đều có đường kết nối giữa các thành phố  nên kết quả là 3
 ```
 ```
 Ví dụ 2: Cho 7 thành phố, và 8 đường kết nối, đường 4, 5, 6 có thể đi qua lại, đường 1,2,3,7 có thể qua lại 
          K
 ```
