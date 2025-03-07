# GiàyHub - Phần Mềm Quản Lý Bán Giày

## 🛍️ Giới thiệu
GiàyHub là một phần mềm quản lý bán giày được xây dựng bằng Java Swing và kết nối cơ sở dữ liệu SQL Server thông qua JDBC. Ứng dụng giúp các cửa hàng giày quản lý hiệu quả các hoạt động kinh doanh từ đăng ký, đăng nhập, quản lý sản phẩm, khách hàng, đơn hàng đến thống kê báo cáo và phân quyền nhân viên.

## 🚀 Tính năng chính
1. **Đăng ký & Đăng nhập:**
   - Đăng ký tài khoản nhanh chóng bằng email, số điện thoại hoặc tên tài khoản.
   - Đăng nhập bảo mật với tính năng khôi phục mật khẩu.

2. **Quản lý khách hàng:**
   - Lưu trữ thông tin khách hàng và lịch sử mua hàng.
   - Tìm kiếm nhanh thông tin khách hàng.

3. **Quản lý sản phẩm:**
   - Quản lý danh sách sản phẩm, số lượng tồn kho, giá cả.
   - Tìm kiếm và thêm sản phẩm vào hóa đơn.

4. **Quản lý đơn hàng:**
   - Tạo đơn hàng, hóa đơn và quản lý giảm giá, thuế.
   - Thao tác sửa đổi đơn hàng và in hóa đơn bán hàng.

5. **Quản lý thanh toán:**
   - Hỗ trợ các phương thức thanh toán như tiền mặt, thẻ tín dụng, chuyển khoản, ví điện tử.

6. **Thống kê và báo cáo:**
   - Thống kê doanh số, sản phẩm bán chạy, khách hàng, thu nhập và lợi nhuận.

7. **Quản lý nhân viên:**
   - Quản lý thông tin nhân viên, phân quyền và theo dõi hiệu suất làm việc.

## 🛠️ Công nghệ sử dụng
- **Ngôn ngữ lập trình:** Java (Java Swing)
- **Cơ sở dữ liệu:** SQL Server
- **Kết nối CSDL:** JDBC
- **Công cụ hỗ trợ:** NetBeans, SQL Server Management Studio

## 📂 Cấu trúc dự án
```
GiayHub/
├── src/                # Thư mục mã nguồn
├── lib/                # Thư viện kết nối JDBC
├── assets/             # Tài nguyên (hình ảnh, icon)
├── database/           # File script SQL tạo CSDL
└── README.md           # Tài liệu hướng dẫn
```

## ⚙️ Cài đặt và sử dụng
1. **Clone dự án:**
```bash
git clone https://github.com/<your-username>/GiayHub.git
```

2. **Import vào NetBeans:**
- Mở NetBeans, chọn `File > Open Project` và trỏ đến thư mục `GiayHub`.

3. **Kết nối CSDL:**
- Chạy file script SQL trong thư mục `database` để tạo cơ sở dữ liệu.
- Cập nhật chuỗi kết nối trong code `DBConnection.java` cho phù hợp.

4. **Chạy dự án:**
- Nhấn `Run` trong NetBeans để khởi động ứng dụng.

## 🤝 Đóng góp
- Fork dự án, tạo nhánh (`feature/my-feature`), commit và gửi Pull Request.

## 📄 Giấy phép
Dự án này được phát hành dưới giấy phép **MIT License**.

---
Nếu bạn có bất kỳ câu hỏi hoặc ý kiến đóng góp nào, vui lòng liên hệ qua email của mình. Chúc bạn sử dụng phần mềm GiàyHub hiệu quả! 🚀

