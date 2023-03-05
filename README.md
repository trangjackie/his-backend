# his-backend
This repo is for HIS backend


I. Cấu hình các service dùng config server
- config server đang được sử dụng cho các service auth, common, user
- gateway (sử dụng zuul) không nhận config, đang nghiên cứu phương án giải quyết
- Các bước cấu hình:
1. Vào thư mục configuration-service: cd configuration-service
2. Build project config: mvn clean install
3. Copy thư mục config vào thư mục target
4. Chạy file jar của config server

II. Cơ chế tracking sử dụng zipkin
1. import vào dependency trong file pom
    <dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-sleuth-zipkin</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-sleuth</artifactId>
	</dependency>
2. Cấu hình zipkin cho các service
    spring.zipkin.base-url=http://localhost:9411/
3. Download file zipkin.jar (https://zipkin.io)
4. Copy file zipkin.jar vào thư mục zipkin-server và chạy
java.exe -jar .\zipkin-server-2.24.0-exec.jar

III. Cấu trúc thư mục code
entity: các đối tượng thể hiện tương ứng 1 table trong cơ sở dữ liệu
dto: là các class đóng gói data để chuyển giữa client - server
converter: convert từ entity sang dto và ngược lại
repository: kết nối đến csdl
service: kết nối repo và controller
controller: là nơi nhận request từ người dùng, xử lý request