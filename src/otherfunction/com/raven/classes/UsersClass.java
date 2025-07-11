package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

public class UsersClass implements UpdatableEntity {
    private String userId;       // USER_ID
    private String hoTen;        // HOTEN
    private String roleType;     // ROLE_TYPE
    private String sdt;          // SDT
    private Date ngaySinh;       // NGAYSINH
    private String email;        // EMAIL
    private String diaChi;       // DIACHI
    private Date createdAt;      // CREATED_AT
    private int isDelete;        // IS_DELETE

    // Constructor
    public UsersClass(String userId, String hoTen, String roleType, String sdt, Date ngaySinh, String email, String diaChi, Date createdAt, int isDelete) {
        this.userId = userId;
        this.hoTen = hoTen;
        this.roleType = roleType;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.diaChi = diaChi;
        this.createdAt = createdAt;
        this.isDelete = isDelete;
    }

    // Default Constructor
    public UsersClass() {}

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    // toString Method
    @Override
    public String toString() {
        return "UsersClass{" +
                "userId='" + userId + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", roleType='" + roleType + '\'' +
                ", sdt='" + sdt + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", createdAt=" + createdAt +
                ", isDelete=" + isDelete +
                '}';
    }
    @Override
    public boolean update() {
        return false;
    }
    @Override
    public boolean insert() {
        return false;
    }
}