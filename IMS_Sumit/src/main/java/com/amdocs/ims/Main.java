package com.amdocs.ims;
import java.util.Scanner;

public class Main {
    private static int current_user=-1;
    public static  void main(String[] args){
        Scanner sc=new Scanner(System.in);
        boolean isterminated=false;
        System.out.println(JDBCConnection.getConnection());
        while(!isterminated) {
            if(current_user==-1) {
                System.out.println("\n1.Login\n2.Register\n3.Exit\n");
                int n=sc.nextInt();
                switch (n) {
                    case 1:
                        System.out.println("Enter Email: ");
                        String email=sc.next();
                        System.out.println("Enter Password: ");
                        String pass=sc.next();
                        int retrun_id= UserUtility.loginUser(email, pass);
                        if(retrun_id!=-1) {
                            System.out.println("Login successfully");
                            current_user=retrun_id;
                        }
                        else {
                            System.out.println("Invalid UserName or Password");
                        }
                        break;
                    case 2:

                        System.out.println("Enter Name: ");
                        String name=sc.next();
                        System.out.println("Enter Email: ");
                        String reg_email=sc.next();
                        System.out.println("Enter Password: ");
                        String reg_pass=sc.next();
                        String role=null;
                        System.out.println("1.Register as an Admin \n2.Register as a User");
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
                System.out.println("\n1. View Your Profile\n2. Make an Order\n"
                        + "3. View Your Order\n4. Cancel Your Order\n"
                        + "5. Browse Available Products\n6. Increase Product Quantity\n7. Add a New Product\n"
                        + "8. View Orders from Other Users\n9. Change Role\n"
                        + "10. See All Users\n11. Log Out\n");

                int m=sc.nextInt();
                switch (m) {
                    case 1:
                        UserUtility.seeProfile(current_user);
                        break;
                    case 2:
                        System.out.println("Enter Product id");
                        int product_id=sc.nextInt();
                        System.out.println("Enter Quantity");
                        int quantity=sc.nextInt();
                        UserUtility.orderProduct(current_user,product_id,quantity);
                        break;
                    case 3:
                        OrderItemUtility.seeOrder(current_user);
                        break;
                    case 4:
                        System.out.println("Enter Order Id to be cancelled");
                        int order_id=sc.nextInt();
                        OrderItemUtility.cancelOrder(order_id, current_user);
                        break;
                    case 5:
                        ProductUtility.showProduct();
                        break;
                    case 6:
                        if(!UserUtility.authenticateAdmin(current_user)) {
                            System.out.println("Only Admin can add product");
                        }
                        else{
                            System.out.println("Enter Product id to increase quantity");
                            int prod_id=sc.nextInt();
                            if(!ProductUtility.isExist(prod_id)){
                                System.out.println("No Product Exist with id "+prod_id);
                            }
                            else{
                                int avl_quantity=ProductUtility.getQuantity(prod_id);
                                System.out.println("Enter amount of quantity to increase");
                                int cur_quantity=sc.nextInt();
                                UserUtility.setquantity(prod_id,avl_quantity+cur_quantity);
                            }
                        }
                        break;
                    case 7:
                        if(!UserUtility.authenticateAdmin(current_user)) {
                            System.out.println("Only Admin can add product");
                        }
                        else {
                            System.out.println("Enter Product Name");
                            String prod_name = sc.next();
                            System.out.println("Enter Product Quantity");
                            int prod_quantity=sc.nextInt();
                            ProductUtility.addProduct(prod_name, prod_quantity);
                        }
                        break;
                    case 8:
                        if(!UserUtility.authenticateAdmin(current_user)) {
                            System.out.println("Only Admin can see other users order");
                        }
                        else {
                            System.out.println("Enter id of user to see his/her order");
                            int user_id=sc.nextInt();
                            OrderItemUtility.seeOrder(user_id);
                        }
                        break;
                    case 9:
                        if(!UserUtility.authenticateAdmin(current_user)) {
                            System.out.println("Only admin can change the role");
                        }
                        else {
                            System.out.println("Enter user id to change the role");
                            int user_id=sc.nextInt();
                            UserUtility.changeRole(user_id);
                        }
                        break;
                    case 10:
                        if(UserUtility.authenticateAdmin(current_user)) {
                            UserUtility.seeAllUser();
                        }
                        else {
                            System.out.println("Only Admin can see all the users list");
                        }
                        break;
                    case 11:
                        current_user=-1;
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            }
        }
        System.out.println("Program Terminated");
        sc.close();
    }
}
