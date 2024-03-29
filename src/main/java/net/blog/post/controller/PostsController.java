package net.blog.post.controller;

import net.blog.post.model.Posts;
import net.blog.post.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class PostsController {

    @Autowired
    public PostsService service;

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("result");
        List<Posts> listCustomer = service.listAll();
        mav.addObject("listCustomer", listCustomer);
        return mav;
    }

    @RequestMapping("/add")
    public String newPost(Map<String,Object>model){
        model.put("posts",new Posts());
        return "new";
    }

    @RequestMapping(value = "/edit/save", method = RequestMethod.POST)
    public String editSave(@ModelAttribute("posts") Posts posts){
        service.save(posts);
        return "redirect:/";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("posts") Posts posts){
        service.save(posts);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editPosts(@PathVariable("id") int id){
        ModelAndView modelAndView=new ModelAndView("editPost");
        Posts posts=service.get(id);
        modelAndView.addObject("posts",posts);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id){
        ModelAndView modelAndView=new ModelAndView("deletePost");
        Posts posts=service.get(id);
        modelAndView.addObject("posts",posts);
        return modelAndView;
    }
    @RequestMapping("/delete/delete-confirm")
    public String deletePost(@RequestParam int id){
        service.delete(id);
        return "redirect:/";
    }

}
