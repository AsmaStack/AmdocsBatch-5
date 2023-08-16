package com.amdocs.ims;


class User {
    private int user_id;
    private String user_name;
    private String email;
    private String pass;
    private String role;

    public User(int user_id, String user_name, String cust_email, String pass, String role) {
        super();
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = cust_email;
        this.pass = pass;
        this.role = role;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getCust_email() {
        return email;
    }
    public void setCust_email(String cust_email) {
        this.email = cust_email;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return String.format("%-10s%-30s%-40s%-10s", user_id, user_name,email, role);
    }
}
