package com.amdocs.ims;

import java.util.Scanner;

public class Main {
    private  static  int current_user=-1;
    public static  void main(String[] args){
        Scanner sc=new Scanner(System.in);
        boolean isterminated=false;
        System.out.println(JDBCConnection.getConnection());
        while(!isterminated) {
            if(current_user==-1) {
                System.out.println("\n1. Existing User\n2. New User\n3. Quit\n");
                System.out.println("Enter your choice: ");
                int n=sc.nextInt();
                switch (n) {
                    case 1:
                        System.out.println("Enter your Email Id: ");
                        String email=sc.next();
                        System.out.println("Enter your Password: ");
                        String pass=sc.next();
                        int retrun_id= UserUtility.loginUser(email, pass);
                        if(retrun_id!=-1) {
                            System.out.println("Login Successful. Welcome!");
                            current_user=retrun_id;
                        }
                        else {
                            System.out.println("Invalid Username or Password. Please try again!");
                        }
                        break;
                    case 2:

                        System.out.println("Enter Your Name: ");
                        String name=sc.next();
                        System.out.println("Enter Your Email: ");
                        String reg_email=sc.next();
                        System.out.println("Enter Password: ");
                        String reg_pass=sc.next();
                        String role=null;
                        System.out.println("1. Register as Admin. \n2. Register as User.");
                        int key=sc.nextInt();
                        switch (key) {
                            case 1:
                                role="admin";
                                break;
                            case 2:
                                role="user";
                                break;
                            default:
                                System.out.println("Invalid Choice");
                                break;
                        }
                        current_user= UserUtility.registerUser(name,reg_email,reg_pass,role);
                        break;
                    case 3:
                        isterminated=true;
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            }
            else {
                System.out.println("\n1. View your profile.\n2. Place new order. \n"
                        + "3. View your order history. \n4. Cancel your order. \n"
                        + "5. View product list.\n6. Update Stock\n7. Add new product.\n"
                        + "8. View Order(s) of other users.\n9. Swap role.\n"
                        + "10. View Existing Users\n11. Logout\n\nEnter your choice: ");
                int m=sc.nextInt();
                switch (m) {
                    case 1:
                        UserUtility.seeProfile(current_user);
                        break;
                    case 2:
                        System.out.println("Enter product id: ");
                        int product_id=sc.nextInt();
                        System.out.println("Enter quantity: ");
                        int quantity=sc.nextInt();
                        UserUtility.orderProduct(current_user,product_id,quantity);
                        break;
                    case 3:
                        OrderItemUtility.seeOrder(current_user);
                        break;
                    case 4:
                        System.out.println("Enter Order Id to be cancelled:");
                        int order_id=sc.nextInt();
                        OrderItemUtility.cancelOrder(order_id, current_user);
                        break;
                    case 5:
                        ProductUtility.showProduct();
                        break;

                    case 6:
                        if(!UserUtility.authenticateAdmin(current_user)) {
                            System.out.println("Only Admins can add new product.");
                        }
                        else{
                            System.out.println("Enter Product Id to increase quantity.");
                            int prod_id=sc.nextInt();
                            if(!ProductUtility.isExist(prod_id)){
                                System.out.println("No product exists with id: "+prod_id);
                            }
                            else{
                                int avl_quantity=ProductUtility.getQuantity(prod_id);
                                System.out.println("Enter Quantity to be increased: ");
                                int cur_quantity=sc.nextInt();
                                UserUtility.setquantity(prod_id,avl_quantity+cur_quantity);
                            }
                        }
                        break;

                    case 7:
                        if(!UserUtility.authenticateAdmin(current_user)) {
                            System.out.println("Only Admins can add new product.");
                        }
                        else {
                            System.out.println("Enter Product Name: ");
                            String prod_name = sc.next();
                            System.out.println("Enter Product Quantity: ");
                            int prod_quantity=sc.nextInt();
                            ProductUtility.addProduct(prod_name, prod_quantity);
                        }
                        break;
                    case 8:
                        if(!UserUtility.authenticateAdmin(current_user)) {
                            System.out.println("Only Admins can see orders of other users.");
                        }
                        else {
                            System.out.println("Enter User Id to fetch their orders: ");
                            int user_id=sc.nextInt();
                            OrderItemUtility.seeOrder(user_id);
                        }
                        break;
                    case 9:
                        if(!UserUtility.authenticateAdmin(current_user)) {
                            System.out.println("Only Admins can change the roles.");
                        }
                        else {
                            System.out.println("Enter the User Id of the User to change role: ");
                            int user_id=sc.nextInt();
                            UserUtility.changeRole(user_id);
                        }
                        break;
                    case 10:
                        if(UserUtility.authenticateAdmin(current_user)) {
                            UserUtility.seeAllUser();
                        }
                        else {
                            System.out.println("Only Admins can view Existing Users.");
                        }
                        break;
                    case 11:
                        current_user=-1;
                        break;
                    default:
                        System.out.println("Invalid Choice! Please Try Again.");
                        break;
                }
            }
        }
        System.out.println("Quitting Application....");
        System.out.println("Goodbye!");
        sc.close();

    }
}
