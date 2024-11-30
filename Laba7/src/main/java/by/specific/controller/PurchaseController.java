package by.specific.controller;

import by.specific.model.Purchase;
import by.specific.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PurchaseController {
    private PurchaseService purchaseService;
    private static boolean flag=true;
    private static List<Purchase> purchaseList = new ArrayList<Purchase>();
    @Autowired(required = true)
    @Qualifier(value = "purchaseService")
    public void setPurchaseService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @RequestMapping(value = "purchases", method = RequestMethod.GET)
    public String listPurchases(Model model){
        if(flag) {
            purchaseList = this.purchaseService.listPurchases();
        }
        flag = true;
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("listPurchases", purchaseList );
        return "purchases";
    }

    @RequestMapping(value = "/purchases/find")
    public String authorizationAccount(@ModelAttribute("purchase") Purchase purchase){
        purchaseList =  this.purchaseService.findPurchases(purchase.getName());
        flag = false;
        return "redirect:/purchases";
    }

    @RequestMapping(value = "/purchases/add", method = RequestMethod.POST)
    public String addPurchase(@ModelAttribute("purchase") Purchase purchase){
        if(purchase.getId() == 0){
            this.purchaseService.addPurchase(purchase);
        }else {
            this.purchaseService.updatePurchase(purchase);
        }

        return "redirect:/purchases";
    }

    @RequestMapping("/remove/{id}")
    public String removePurchase(@PathVariable("id") int id){
        this.purchaseService.removePurchase(id);

        return "redirect:/purchases";
    }

    @RequestMapping("edit/{id}")
    public String editPurchase(@PathVariable("id") int id, Model model){
        model.addAttribute("purchase", this.purchaseService.getPurchaseById(id));
        model.addAttribute("listPurchases", this.purchaseService.listPurchases());

        return "purchases";
    }

    @RequestMapping("purchaseinfo/{id}")
    public String purchaseData(@PathVariable("id") int id, Model model){
        model.addAttribute("purchase", this.purchaseService.getPurchaseById(id));

        return "purchaseinfo";
    }
}
