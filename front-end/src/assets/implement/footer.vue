<script setup>

</script>

<template>
    <footer class="main-footer">
  <div class="footer-content">

    <div class="footer-section">
      <h3>My Website</h3>
      <p>Nơi chia sẻ sách, truyện và nội dung thú vị mỗi ngày.</p>
    </div>

    <div class="footer-section">
      <h4>Liên kết</h4>
      <ul>
        <li><a href="#">Trang chủ</a></li>
        <li><a href="#">Thể loại</a></li>
        <li><a href="#">Top sách</a></li>
        <li><a href="#">Liên hệ</a></li>
      </ul>
    </div>

    <div class="footer-section">
      <h4>Kết nối</h4>
      <div class="social-icons">
        <a href="#">FB</a>
        <a href="#">IG</a>
        <a href="#">YT</a>
      </div>
    </div>

  </div>

  <div class="footer-bottom">
    © 2025 My Website – All rights reserved.
  </div>
</footer>
</template>

<style scoped>

.main-footer {
  color: black;
  padding: 40px 20px 20px;
  margin-top: 50px;
  font-family: sans-serif;
}

.footer-content {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 30px;
}

.footer-section h3,
.footer-section h4 {
  color: black;
  margin-bottom: 10px;
}

.footer-section p {
  max-width: 300px;
  line-height: 1.5;
}

.footer-section ul {
  list-style: none;
  padding: 0;
}

.footer-section ul li {
  margin: 5px 0;
}

.footer-section a {
  color: black;
  text-decoration: none;
}

.footer-section a:hover {
  color: black;
}

.social-icons a {
  margin-right: 10px;
  color: black;
  text-decoration: none;
  font-weight: bold;
}

.social-icons a:hover {
  color: black;
}

.footer-bottom {
  text-align: center;
  padding-top: 20px;
  border-top: 1px solid #333;
  margin-top: 20px;
  font-size: 14px;
  color: black;
}

</style>