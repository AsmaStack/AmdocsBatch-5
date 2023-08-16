package IMS;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class stockManagement {
	    private static String jdbcurl = "oracle.jdbc.driver.OracleDriver";
	    private static String connstring = "jdbc:oracle:thin:@localhost:1521:XE";
	    private static String username = "amdocsuser1";
	    private static String password = "naru";
	    static Statement stmt;
	    public static void createProduct() {
	    	try {
				Class.forName(jdbcurl);
				 Connection con = DriverManager.getConnection(connstring, username, password);
		    	 Scanner pd= new Scanner(System.in);
				 System.out.println("Enter product id ");
				 int PRODID= pd.nextInt();
				 System.out.println("Enter product name");
				 String PRODNAME=pd.next();
				 System.out.println("Enter product description");
				 String PRODDESC =pd.next();
				 System.out.println("Enter Product price");
				 int 	PRODPRICE =pd.nextInt();
				 System.out.println("Enter Product quantity");
				 int PRODQUANT =pd.nextInt();
				 String insertstmt="insert into Product values (?, ?, ?, ?, ?)";
				 PreparedStatement udinsert= con.prepareStatement (insertstmt);
				 udinsert.setInt (1, PRODID);
			     udinsert.setString (2, PRODNAME);
				 udinsert.setString (3, PRODDESC);	
				 udinsert.setInt (4, PRODPRICE);
				 udinsert.setInt (5, PRODQUANT);
				 udinsert.execute();
			     System.out.println("New product record is succesfully added Added");
			     
		    	}
		    	catch (ClassNotFoundException e) {
					e.printStackTrace();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	
	    	
	    		
	    	}
	    public static void updateProduct() {
	    	try {
				Class.forName(jdbcurl);
			    Connection con1 = DriverManager.getConnection(connstring, username, password);
			    Scanner scan2 = new Scanner(System.in);
			    String query = "SELECT * FROM Product WHERE ProdId = ?";
	            PreparedStatement preparedStatement = con1.prepareStatement(query);
	            System.out.println("enter product id");
	            int prodId =scan2.nextInt();
				preparedStatement.setInt(1, prodId);			            
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                System.out.println("Given product Id found in the database:");
	                System.out.println("Product ID: " + resultSet.getInt("ProdID"));	                
	                Scanner scan=new Scanner(System.in);	                
	                System.out.println("enter the new product name to be updated");
	                String PRODNAME	=scan.next();
	                System.out.println("enter the new produt description to be updated");
	                String PRODDESC=scan.next();
	                System.out.println("enter the new product price to be updated");
	                int PRODPRICE	 = scan.nextInt();
	                System.out.println("enter the new product quantity to be updated");
	                int PRODQUANT = scan.nextInt();
	                System.out.println(prodId);	
	                String updatestmt="update product set PRODNAME	=?,PRODDESC=?,PRODPRICE	=?,	PRODQUANT=? where PRODID=?";		                			                			                 
	                PreparedStatement psupdate = con1.prepareStatement(updatestmt);
	                psupdate.setString(1,PRODNAME);
	                psupdate.setString(2,PRODDESC);
	                psupdate.setInt(3,PRODPRICE	);
	                psupdate.setInt(4,PRODQUANT);
	                psupdate.setInt(5,prodId);
	                psupdate.execute();
	                System.out.println("Given product recoded is updated successfully");			                			                
	                // Add more columns as needed
	            } else {
	                System.out.println("Product with ID "+prodId+" s not found in the database.");
	            }			    				    			    				    	
	    	}
	    	catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    }
	    public static void deleteProduct()
	    {
	    	try {
				Class.forName(jdbcurl);
			    Connection con1 = DriverManager.getConnection(connstring, username, password);
			    Scanner scan2 = new Scanner(System.in);
			    String query = "SELECT * FROM Product WHERE ProdId = ?";
	            PreparedStatement preparedStatement = con1.prepareStatement(query);
	            System.out.println("Enter product id");
	            int prodId =scan2.nextInt();
				preparedStatement.setInt(1, prodId);			            
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                System.out.println("Product found in the database:");
	                System.out.println("Product ID: " + resultSet.getInt("ProdID"));			                			                			                			               			                			                
	                // Add more columns as needed			             			     			                
	                String deleteQuery = "DELETE FROM Product WHERE ProdID = ?";
	                PreparedStatement deleteStatement = con1.prepareStatement(deleteQuery);
	                deleteStatement.setInt(1, prodId);
	                int rowsDeleted = deleteStatement.executeUpdate();
	                if (rowsDeleted > 0) {
	                    System.out.println("Product with ID " + prodId + " deleted successfully.");
	                } else {
	                    System.out.println("Product deletion failed. Product with ID " + prodId + " not found.");
	                }
	                
	                
	            } else {
	                System.out.println("Product with ID "+prodId+" s not found in the database.");
	            }			    				    			    				    	
	    	}
	    	catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	}
	    public static void stockDetails() {
	    	try {
				Class.forName(jdbcurl);
				 Connection con = DriverManager.getConnection(connstring, username, password);
				 String query = "SELECT PRODID, PRODNAME, PRODDESC,PRODQUANT FROM product";
		            PreparedStatement preparedStatement = con.prepareStatement(query);

		            ResultSet resultSet = preparedStatement.executeQuery();

		            while (resultSet.next()) {
		                int productId = resultSet.getInt("PRODID");
		                String productName = resultSet.getString("prodname");
		                String productDescription = resultSet.getString("proddesc");
		                int productQuant = resultSet.getInt("PRODQUANT");

		                System.out.println("+" + "-".repeat(30) + "+");
		                System.out.println("| Product ID: " + productId + " ".repeat(14 - String.valueOf(productId).length()) + "|");
		                System.out.println("| Product Name: " + productName + " ".repeat(12 - productName.length()) + "|");
		                System.out.println("| Product Description: " + productDescription + " ".repeat(3) + "|");
		                System.out.println("| Product Quantity: " + productQuant + " ".repeat(8 - String.valueOf(productQuant).length()) + "|");
		                System.out.println("+" + "-".repeat(30) + "+");
		                
		            }
	    	}
	    	catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
}
