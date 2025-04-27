package co.edu.uniminuto.appproject.entities;

public class User {
    private int id;
    private String user;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private boolean status;

    public User(){

    }

    public User( String user, String name, String password, String email, String phone, String address, boolean status) {

        this.user = user;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", user='").append(user).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
