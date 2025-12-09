package com.subclear.controller;

import com.subclear.model.Subscription;
import com.subclear.service.SubscriptionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashboardController {
    
    @Autowired
    private SubscriptionService subscriptionService;
    
    @GetMapping("/")
    public String dashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("username", username);
        model.addAttribute("subscriptions", subscriptionService.getAllSubscriptions(username));
        model.addAttribute("totalMonthly", subscriptionService.calculateTotalMonthly(username));
        model.addAttribute("activeCount", subscriptionService.countActive(username));
        return "dashboard";
    }
    
    @GetMapping("/add")
    public String showAddForm(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        model.addAttribute("subscription", new Subscription());
        return "add-subscription";
    }
    
    @PostMapping("/add")
    public String addSubscription(@ModelAttribute Subscription subscription, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        subscription.setUserId(username);
        subscription.setActive(true);
        subscriptionService.saveSubscription(subscription);
        return "redirect:/";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        subscriptionService.getSubscriptionById(id).ifPresent(sub -> model.addAttribute("subscription", sub));
        return "edit-subscription";
    }
    
    @PostMapping("/edit/{id}")
    public String updateSubscription(@PathVariable Long id, @ModelAttribute Subscription subscription, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        subscription.setId(id);
        subscription.setUserId(username);
        subscriptionService.saveSubscription(subscription);
        return "redirect:/";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteSubscription(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        subscriptionService.deleteSubscription(id);
        return "redirect:/";
    }
    
    @PostMapping("/toggle/{id}")
    public String toggleSubscription(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        subscriptionService.toggleSubscription(id);
        return "redirect:/";
    }
}
