package com.wms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wms.bean.Item;
import com.wms.model.ResponseBodyWrapper;
import com.wms.service.ItemService;

@RestController
@RequestMapping(value = "/api/item")
public class ItemController {

}
