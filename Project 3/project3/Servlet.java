package pointofsalesbackend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Itemlist myData;
	private Vector<Item> cart;
	private String tempSelected;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		myData = new Itemlist();
		cart = new Vector<Item>();
		tempSelected = "";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("userName");
		String password = request.getParameter("password");
	
		if (!(user.equals("md")) && !(password.equals("pw"))) {
			response.getWriter()
					.append("<!DOCTYPE html>\r\n" + "<html\r\n" + "head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n"
							+ "<title>Grocery Store</title>\r\n" + "</head>\r\n" + "<body>\r\n"
							+ "	<form action=http://localhost:8080/project3/Servlet method=\"get\">\r\n"
							+ "		invalid user-name or password <br>\r\n"
							+ "		Enter your user-name: <input type=\"text\" name=\"userName\"> <br>\r\n"
							+ "		Enter your password: <input type=\"text\" name=\"password\"> <br>\r\n"
							+ "		<input type=\"submit\" value=\"Go!\" name=\"indexButton\">\r\n" + "	</form>\r\n"
							+ "</body>\r\n" + "</html>");
		} else {
			request.setAttribute("userName", user);

			if (request.getParameter("indexButton") != null) {
				String color = request.getParameter("backgroundColor");
				response.getWriter().append("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n"
						+ "<meta charset=\"ISO-8859-1\">\r\n" + "<title>Grocery Store</title>\r\n" + "</head>\r\n");
				if (color != null) {
					response.getWriter().append("<body style=\"background-color:" + color + ";\">\r\n");
				} else {
					response.getWriter().append("<body>\r\n");
				}

				response.getWriter()
						.append(
								  " here are your choices:<br>	<form action=http://localhost:8080/project3/Servlet\r\n"
								+ "		method=\"get\">\r\n" + "		<input type=\"hidden\" value=\"" + user
								+ "\" name=\"userName\">\r\n"
								+ "		<input type=\"submit\" value=\"Pick Item\" name=\"pickItemButton\">\r\n"
								+ "		<input type=\"submit\" value=\"View Cart\" name=\"viewCartButton\">\r\n"
								+ "	</form>\r\n" + "</body>\r\n" + "</html>");
			}
			if (request.getParameter("pickItemButton") != null || request.getParameter("return") != null) {
				String value = "<select name=\"items\">";
				Vector<Item> temp = new Vector<Item>();
				temp = myData.collectionOfProducts("all");
				for (Item i : temp) {
					value += "<option value=\"" + i.getProduct() + "\">" + i.getProduct() + "</option>";
				}
				value += "</select>\r\n";
				request.setAttribute("dropDownOptions", value);
				RequestDispatcher rd = request.getRequestDispatcher("/items.jsp");
				rd.forward(request, response);
			}
			if (request.getParameter("getItemInfo") != null) {
				tempSelected = request.getParameter("items");
				String itemChoice = request.getParameter("items");
				request.setAttribute("itemChoice", itemChoice);
				String desc = myData.findProduct(itemChoice).getCategory();
				request.setAttribute("itemDesc", desc);
				Double temPrice = myData.findProduct(itemChoice).getPrice();
				String price = "" + temPrice;
				request.setAttribute("price", price);
				int tempStock = myData.findProduct(itemChoice).getStock();
				String stock = "" + tempStock;
				request.setAttribute("stock", stock);

				RequestDispatcher rd = request.getRequestDispatcher("/Items2.jsp");
				rd.forward(request, response);
			}
			if (request.getParameter("viewCartButton") != null) {
				String value = "<select name=\"userCart\">";
				double tempTotal = 0;
				int tempSize = 0;
				for (Item i : cart) {
					value += "<option value=\"" + i.getProduct() + "\">" + i.getProduct() + "</option>";
					tempTotal += i.getPrice() * i.getStock();
					tempSize += i.getStock();
				}
				value += "</select>\r\n";
				DecimalFormat df = new DecimalFormat("#.##");
				String total = "" + df.format(tempTotal);
				String size = "" + tempSize;
				request.setAttribute("displayCart", value);
				request.setAttribute("cartTotal", total);
				request.setAttribute("cartSize", size);
				RequestDispatcher rd = request.getRequestDispatcher("/usercart.jsp");
				rd.forward(request, response);
			}
			if (request.getParameter("addButton") != null) {
				String tempStock = request.getParameter("amount");
				int stock = Integer.parseInt(tempStock);
				boolean exists = false;
				for (Item i : cart) {
					if (i.getProduct().equals(tempSelected)) {
						exists = true;
						i.addStock(stock);
						break;
					}
				}
				if (!exists) {
					Item tempItem = myData.findProduct(tempSelected);
					Item selected = new Item();
					selected.setProduct(tempSelected);
					selected.setPrice(tempItem.getPrice());
					selected.setStock(stock);
					cart.add(selected);
				}
				myData.findProduct(tempSelected).removeStock(stock);

				String value = "<select name=\"items\">";
				Vector<Item> temp = new Vector<Item>();
				temp = myData.collectionOfProducts("all");
				for (Item i : temp) {
					value += "<option value=\"" + i.getProduct() + "\">" + i.getProduct() + "</option>";
				}
				value += "</select>\r\n";
				request.setAttribute("dropDownOptions", value);
				RequestDispatcher rd = request.getRequestDispatcher("/items.jsp");
				rd.forward(request, response);
			}
			if (request.getParameter("removeCartButton") != null) {
				tempSelected = request.getParameter("userCart");
				String selected = request.getParameter("userCart");
				request.setAttribute("selectedItem", selected);
				for (Item i : cart) {
					if (selected.equals(i.getProduct())) {
						String price = "" + i.getPrice();
						request.setAttribute("price", price);
						String stock = "" + i.getStock();
						request.setAttribute("stock", stock);
					}
				}
				RequestDispatcher rd = request.getRequestDispatcher("/removecart.jsp");
				rd.forward(request, response);
			}
			if (request.getParameter("logOutButton") != null) {
				for (Item i : cart) {
					myData.findProduct(i.getProduct()).addStock(i.getStock());
				}
				cart.clear();
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}
			if (request.getParameter("checkOutButton") != null) {
				myData.writeFile();
				cart.clear();
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}
			if (request.getParameter("deleteCartButton") != null) {
				String itemChoice = tempSelected;
				String numRemove = request.getParameter("amount");
				int remove = Integer.parseInt(numRemove);
				for (Item i : cart) {
					if (i.getProduct().equals(itemChoice)) {
						i.removeStock(remove);
						if (i.getStock() == 0) {
							cart.remove(i);
						}
						break;
					}
					if (i.getStock() == 0) {
						cart.remove(i);
					}
				}
				myData.findProduct(tempSelected).addStock(remove);

				String value = "<select name=\"userCart\">";
				DecimalFormat df = new DecimalFormat("#.##");
				double tempTotal = 0;
				int tempSize = 0;
				for (Item i : cart) {
					value += "<option value=\"" + i.getProduct() + "\">" + i.getProduct() + "</option>";
					tempTotal += i.getPrice() * i.getStock();
					tempSize += i.getStock();
				}
				value += "</select>\r\n";
				String total = "" + df.format(tempTotal);
				String size = "" + tempSize;
				request.setAttribute("displayCart", value);
				request.setAttribute("cartTotal", total);
				request.setAttribute("cartSize", size);
				RequestDispatcher rd = request.getRequestDispatcher("/usercart.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
