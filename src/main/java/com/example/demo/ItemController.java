package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ItemRepository itemRepository;
	
	@RequestMapping(value = "/items")
	public ModelAndView items(ModelAndView mv) {
		
		List<Item> itemList = itemRepository.findAll();
		mv.addObject("items", itemList);
    	mv.setViewName("list");	
        return mv;
	}
	
	@RequestMapping(value = "/items/{code}")
    public ModelAndView itemsByCode(
            ModelAndView mv,
            @PathVariable(name="code")int categoryCode
    		) {
    	
		List<Item> itemList = itemRepository.findByCategoryCode(categoryCode);
		mv.addObject("items", itemList);
    	mv.setViewName("list");
        return mv;
    }

}
