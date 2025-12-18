package com.flip.flip_platform;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController 
{
    @GetMapping("/")
    public String home()
    {
        return "home";
    }  
    
    @GetMapping("/cases")
    public String cases(@RequestParam(defaultValue = "active") String status, Model model)
    {
        //hardcoded demo data - will swap to db soon
        List<Map<String, String>> allCases = List.of(
            Map.of("caseNumber", "1:25-cv-09104", "brand", "Example Brand", "jurisdiction", "N.D. Georgia", "status", "active"),
            Map.of("caseNumber", "1:24-cv-01234", "brand", "Old Brand",     "jurisdiction", "S.D. New York", "status", "inactive")
        );

        List<Map<String, String>> filtered = allCases.stream()
            .filter(c -> c.get("status").equalsIgnoreCase(status))
            .toList();

        model.addAttribute("status", status.toLowerCase());
        model.addAttribute("cases", filtered);
        return "dashboard";


    }

    @GetMapping("/cases/{caseNumber}")
    public String caseDetail(@PathVariable String caseNumber, Model model) {

        //more hardcoded info - to be replaced by DB
        Map<String, String> caseInfo = Map.of(
            "caseNumber", caseNumber,
            "brand", "Example Brand",
            "jurisdiction", "N.D. Georgia",
            "status", "active"
        );

        //more hardcoded info
        List<Map<String, String>> defendants = List.of(
            Map.of("merchantId", "MERCH-001", "doe", "1", "sellerName", "Seller A", "marketplace", "Amazon"),
            Map.of("merchantId", "MERCH-002", "doe", "2", "sellerName", "Seller B", "marketplace", "eBay")
        );

        model.addAttribute("caseInfo", caseInfo);
        model.addAttribute("defendants", defendants);
        return "case-detail";
    }
}
