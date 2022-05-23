package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ItemRepository itemRepository;

	@RequestMapping("/cart")
	public ModelAndView cartList(ModelAndView mv) {
		
		Cart cart = getCartFromSession();
		
		mv.addObject("items", cart.getItems());
		mv.addObject("total", cart.getTotal());
		
		mv.setViewName("cart");	
        return mv;
	}
	
	@RequestMapping(value = "/cart/add/{itemCode}")
    public ModelAndView addCart(
            ModelAndView mv,
            @PathVariable(name="itemCode")int itemCode,
            @RequestParam(name="quantity", defaultValue="1") int quantity 
    		) {
			Cart cart = getCartFromSession();
			Item item = itemRepository.findById(itemCode).get();
			cart.addCart(item, quantity);
		
			mv.addObject("items", cart.getItems());
			mv.addObject("total", cart.getTotal());
			mv.setViewName("cart");
			return mv;
    }
	
	@RequestMapping(value = "/cart/delete/{itemCode}")
    public ModelAndView deleteCart(
            ModelAndView mv,
            @PathVariable(name="itemCode")int itemCode
    		) {
			Cart cart = getCartFromSession();

			cart.deleteCart(itemCode);
		
			mv.addObject("items", cart.getItems());
			mv.addObject("total", cart.getTotal());
			mv.setViewName("cart");
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
