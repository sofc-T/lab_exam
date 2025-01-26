package com.hr.hr.controller;

import javax.validation.Valid;

// import javax.servlet.http.HttpServletRequest;
// import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hr.hr.models.UserInfo;
import com.hr.hr.service.LeaveManageService;
import com.hr.hr.service.UserInfoService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * This controller will provide basic operations for users such as
 * signing-in, registering a new user, and handling authentication.
 * 
 */
@Controller
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    LeaveManageService leaveManageService;

    /**
     * This method opens up the login page if the user is not authenticated;
     * otherwise, it redirects the user to the home page.
     * 
     * @param mav ModelAndView object
     * @return ModelAndView object
     */
    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public ModelAndView login(ModelAndView mav, HttpServletRequest request) {       
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // System.out.println("Authentication Object: " + auth);
        // If the user is authenticated, redirect to home
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserInfo userInfo = userInfoService.findUserByEmail(auth.getName());
            mav.addObject("userInfo", userInfo);
            mav.setViewName("home");
            return mav;
        }
        
        // If not authenticated, show login page
        mav.setViewName("login");
        return mav;
    }

    /**
     * Opens the registration page to register a new user.
     * 
     * @param mav ModelAndView object
     * @return ModelAndView object
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(ModelAndView mav) {
        UserInfo userInfo = new UserInfo();
        mav.addObject("userInfo", userInfo);
        mav.setViewName("registration");
        return mav;
    }

    /**
     * Gets the form input from the registration page and adds the user to the
     * database.
     * 
     * @param mav          ModelAndView object
     * @param userInfo     User information object
     * @param bindResult   Binding result for form validation
     * @return ModelAndView object
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(ModelAndView mav, @Valid UserInfo userInfo, BindingResult bindResult) {

        UserInfo userExists = userInfoService.findUserByEmail(userInfo.getEmail());

        if (userExists != null) {
            bindResult.rejectValue("email", "error.user", "User already exists with this email ID");
        }

        if (bindResult.hasErrors()) {
            mav.setViewName("registration");
        } else {
            userInfoService.saveUser(userInfo);
            mav.addObject("successMessage", "User registered successfully! Awaiting Manager approval.");
            mav.addObject("userInfo", new UserInfo());
            mav.setViewName("login");
        }
        return mav;
    }

    /**
     * Shows the admin page after user authentication is done.
     * 
     * @param mav ModelAndView object
     * @param request HttpServletRequest
     * @return ModelAndView object
     */
    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView mav, HttpServletRequest request) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = userInfoService.findUserByEmail(auth.getName());
        request.getSession().setAttribute("userInfo", userInfo);
    
        mav.addObject("userInfo", userInfo);
        mav.setViewName("home");
        return mav;
    }
    

    

}
