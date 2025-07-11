<!-- Title -->
<h1 align="center"><b>CYBERGAME MANAGEMENT APPLICATION - Vườn Sao Băng</b></h1>

## THÀNH VIÊN NHÓM
<a name="thanhvien"></a>
| STT    | Họ và Tên              | Github                                               |
| ------ | ----------------------:|-----------------------------------------------------:|
| 1      | Nguyễn Võ Ngọc Bảo     |[AdamNbz](https://github.com/AdamNbz)                 |
| 2      | Lê Nguyễn Thành Công   |[LibraJeager](https://github.com/LibraJeager)         |
| 3      | Mai Nguyễn Bảo Duy     |[mduy2k5](https://github.com/mduy2k5)                 |
| 4      | Trần Đại Hải           |[TranDaiHai2107](https://github.com/TranDaiHai2107)   |
| 5      | Huỳnh Doãn Khải Duy    |                                                      |
## MÔ TẢ DỰ ÁN
Mục tiêu chính của đề tài “Hệ thống quản lý Cybergame” là thiết kế và xây dựng một phần mềm quản lý toàn diện, tích hợp đầy đủ các chức năng cần thiết nhằm nâng cao hiệu quả quản lý cho các Cybergame.

# Yêu cầu chức năng
## A. Quản lý máy tính, khu vực:
  ### 1. Danh sách máy trạm:
    • Lưu trữ thông tin từng máy (ID máy, cấu hình phần cứng, trạng thái hoạt động).
    • Theo dõi tình trạng hoạt động (đang sử dụng, hỏng hóc, bảo trì).
  a.2. Danh sách các khu vực
    • Lưu trữ thông tin theo từng khu vực (Classic, Vip, Couple, Esports,…)
    • Theo dõi tình trạng các khu vực (bảo trì, đang hoạt động)
**B. Quản lý khách hàng**
  b.1. Đăng ký tài khoản:
    • Khách hàng đăng ký tài khoản thành viên.
    • Quản lý thông tin khách hàng (tên, số điện thoại, lịch sử sử dụng, số dư còn lại).
  b.2. Quản lý giờ chơi:
    • Theo dõi thời gian sử dụng của từng khách hàng.
    • Gửi thông báo nhắc nhở khi gần hết giờ.
  b.3. Khuyến mãi và ưu đãi:
    • Hỗ trợ gói giảm giá cho thành viên VIP hoặc khách hàng thân thiết.
    • Tích điểm thưởng đổi quà như: dịch vụ, hạng khách hàng, hiện vật,…..
    • Tự động tính khuyến mãi cho khách theo chương trình khuyến mãi của Cybergame
**C. Quản lý tài chính**
  c.1. Thu phí sử dụng:
    • Tính phí theo thời gian chơi (theo giờ).
    • Hỗ trợ thanh toán trực tiếp hoặc qua ví điện tử.
  c.2. Báo cáo doanh thu:
    • Tổng hợp doanh thu theo ngày, tháng.
    • Phân loại nguồn thu: phí giờ chơi, bán đồ ăn, thức uống, dịch vụ khác (Nạp game, mua thẻ game, bán key bản quyền game).
  c.3. Theo dõi chi phí:
    • Quản lý chi phí vận hành: điện, nước, internet, bảo trì máy móc
    • Quản lý chi phí lương nhân viên
**D. Quản lý sản phẩm**
  d.1. Quản lý đồ ăn và thức uống:
    • Lưu trữ danh sách sản phẩm có sẵn (mì gói, nước uống, snack, v.v.).
    • Theo dõi tồn kho.
  d.2. Bán hàng tích hợp:
    • Ghi nhận đơn đặt hàng từ khách hàng (thông qua nhân viên hoặc hệ thống tự động trên máy).
    • Thống kê doanh số bán đồ ăn, thức uống.
  d.3. Quản lý các bộ phận tồn kho thiết bị: Bàn phím, chuột, màn hình, ram, rom
**E. Quản lý nhân viên**
  e.1. Quản lý thông tin nhân viên
    • Quản lý các thông tin cơ bản của nhân viên (Họ tên, ngày sinh …)
    • Công việc của nhân viên, lương hàng giờ.
  e.2. Phân công ca làm việc:
    • Lập lịch làm việc cho nhân viên.
    • Theo dõi giờ làm và chấm công tự động.
    • Quản lý lịch nghỉ phép của nhân viên.
**F. An ninh và kiểm soát (Option)**
  f.1. Quản lý đăng nhập:
    • Yêu cầu khách hàng đăng nhập tài khoản trước khi sử dụng máy.
    • Theo dõi lịch sử truy cập và tải xuống của từng máy để đảm bảo an ninh.
    • Quản lý lịch sử truy cập hệ thống của nhân viên
  f.2. Camera giám sát:
    • Tích hợp hệ thống camera giám sát toàn bộ khu vực .
    • Lưu trữ dữ liệu video trong thời gian quy định.
**G. Báo cáo tổng hợp**
  g.1. Thống kê doanh số:
    • Tổng hợp số giờ sử dụng của từng máy.
    • Thống kê số lượng khách hàng theo thời gian.
  g.2. Đánh giá kinh doanh:
    • Phân tích các gói dịch vụ, san phẩm phổ biến.
    • Đưa ra đề xuất tối ưu hóa dịch vụ và chi phí.
**H. Quản lý sự kiện quan trọng và khuyến mãi**
  • Tạo sự kiện: Quản lý các sự kiện đặc biệt như giải đấu game, khuyến mãi giờ vàng, v.v.
  • Quản lý khuyến mãi: Tạo và quản lý các chương trình khuyến mãi, giảm giá cho khách hàng.
**I. Tích lũy điểm quy đổi nhận quà**
  • Khách hàng tích lũy điểm qua quá trình chơi bằng cách nạp tiền hoặc chơi các trò chơi mà Cybergame tự tổ chức, hoặc sử dụng các dịch vụ, sản phẩm của quán.
  • Khách hàng quy đổi quà (hiện vật, dịch vụ,..) bằng điểm tích lũy.
