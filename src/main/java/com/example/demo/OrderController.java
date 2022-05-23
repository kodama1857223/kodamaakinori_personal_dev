package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailRepository orderDetailRepository;

	@RequestMapping(value = "/order")
    public ModelAndView index(ModelAndView mv) {
			
		Cart cart = getCartFromSession();
		
			mv.addObject("items", cart.getItems());
			mv.addObject("total", cart.getTotal());
			mv.setViewName("customerInfo");
			return mv;
    }
	
	@RequestMapping(value = "/order/confirm", method = RequestMethod.POST)
    public ModelAndView comfirm(
            ModelAndView mv,
            @RequestParam(name="name") String name, 
            @RequestParam(name="address") String address, 
            @RequestParam(name="tel") String tel, 
            @RequestParam(name="email") String email 
    		) {
			Customer customer = new Customer(name, address, tel, email);
			session.setAttribute("customerInfo", customer);

			Cart cart = getCartFromSession();
		
			mv.addObject("items", cart.getItems());
			mv.addObject("total", cart.getTotal());
			mv.setViewName("confirm");
			return mv;
    }
	
	@RequestMapping(value = "/order/doOrder", method = RequestMethod.POST)
    public ModelAndView doOrder(ModelAndView mv) {
			
			Customer customer = (Customer)session.getAttribute("customerInfo");
			customerRepository.saveAndFlush(customer);

			Cart cart = getCartFromSession();
			
			Order order = new Order(
						customer.getCode(),
						new Date(),
						cart.getTotal()
			);
			int orderCode = orderRepository.saveAndFlush(order).getCode();
			
			Map<Integer, Item>items = cart.getItems();
			List<OrderDetail> orderDetails = new ArrayList<>();
			
			for(Item item : items.values()) {
				orderDetails.add(new OrderDetail(orderCode, item));
			}
				orderDetailRepository.saveAll(orderDetails);	
				session.setAttribute("cart", new Cart());
			
			mv.addObject("orderNumber", orderCode);
			mv.setViewName("ordered");
			return mv;
    }
	
private Cart getCartFromSession() {
		
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}

}
