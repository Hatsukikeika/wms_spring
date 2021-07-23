package com.wms.web;

import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wms.bean.User;
import com.wms.bean.DTO.UserCreationRequest;
import com.wms.model.ResponseBodyWrapper;
import com.wms.service.AccountService;
import com.wms.service.Exceptions.DataNotFoundException;



@RestController
@RequestMapping(value = "/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    
    // Create a new account
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @Transactional
    public ResponseBodyWrapper createAccount(@RequestBody @Valid UserCreationRequest reg) {
    	
    	accountService.createRootAccount(reg);
    	
    	return new ResponseBodyWrapper();
    }
    
    // Get user info for logged in user.
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @Transactional
    public ResponseBodyWrapper getAccountInfo(@RequestAttribute("$USERID") Long userid) throws DataNotFoundException {
    	
    	User userinfo = accountService.getAccountDetail(userid);
    	
    	ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper().putData(userinfo);
    	
        return responseBodyWrapper;
    }
    
    //@RequestMapping(value = "/sub", method = RequestMethod.POST)
    //@Transactional
    public ResponseBodyWrapper createSubAccount(@RequestAttribute("$GROUPID") Long groupid, @RequestParam String email) {
    	
    	accountService.createSubAccount(groupid, email);
    	
    	return new ResponseBodyWrapper();
    }
    
    //@RequestMapping(value = "/sub", method = RequestMethod.DELETE)
    //@Transactional
    public ResponseBodyWrapper removeSubAccount(@RequestAttribute("$GROUPID") Long groupid, @RequestParam long userid) {
    	
    	accountService.removeSubAccount(groupid, userid);
    	
    	return new ResponseBodyWrapper();
    }
    
    //@RequestMapping(value = "/val")
    //@Transactional
    public ResponseBodyWrapper validateAccount(@RequestParam long userid, @RequestParam long vid, @RequestParam String key) {
    	
    	accountService.validateAccount(userid, vid, key);;
    	
    	return new ResponseBodyWrapper();
    }
    
    //@RequestMapping(value = "/password", method = RequestMethod.PUT)
    //@Transactional
    public ResponseBodyWrapper changePasswrod(@RequestAttribute("$GROUPID") Long groupid,
    		@RequestAttribute("$GROUPID") String email, @RequestBody Map<String,String> pass) {
    	
    	accountService.changePassword(groupid, email, pass.get("oldpass"), pass.get("newpass"));
    	
    	return new ResponseBodyWrapper();
    }
}
