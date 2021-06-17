package controller;

import model.Customer;
import model.CustomerFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.ICustomerService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("Customer/")
@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private Environment environment;

    @GetMapping("Show")
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("list");
        List<Customer> customerList = customerService.showAll();
        modelAndView.addObject("Customer",customerList);
        return modelAndView;
    }

    @GetMapping("Create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer",new CustomerFile());
        return modelAndView;
    }

    @PostMapping("Create")
    public ModelAndView create(CustomerFile customerFile){
        MultipartFile multipartFile = customerFile.getImg();
        String nameFile = multipartFile.getOriginalFilename();
        String localFile = environment.getProperty("fileImg");
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(localFile+nameFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer();
        customer.setId((long) (Math.random()*100000));
        customer.setName(customerFile.getName());
        customer.setAddress(customerFile.getAddress());
        customer.setImg(nameFile);
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer",new CustomerFile());
        modelAndView.addObject("messege","Thêm thành công");
        return modelAndView;
    }

    @GetMapping("Remove")
    public String remove(Long id){
        customerService.remove(id);
        return "redirect:Show";
    }
//
//    @GetMapping("Update")
//    public





}
