package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.People;
import com.example.services.PeopleService;
import com.google.gson.Gson;

//@Controller
@RestController //@RestController = @Controller+ @ResponseBody
@RequestMapping("/keepal")
public class PeopleController {
	
	private final static Logger log = LoggerFactory.getLogger(PeopleController.class);

	@Autowired
	private PeopleService pser;
	
	/**
	 * GET请求方式
	 * 请求demo:http://localhost:8567/keepal/get?id=31
	 * @param id
	 * @return
	 */
	@RequestMapping(value =  "/get",method=RequestMethod.GET)
	public People getPeople(@RequestParam long id){
		Optional<People> optional = pser.getRepository().findById(id);
		return optional.get();
	}
	
	/**
	 * POST请求方式
	 * 请求demo:{"name":"wow","age":"23","sex":"female","tel":"12306","email":"123@136.com"}
	 * @param id
	 * @return 成功返回增加的People/失败返回错误提示
	 * @throws Exception 
	 */
	@RequestMapping(value =  "/add",method=RequestMethod.POST)
	public String addPeople(@RequestBody People people) throws Exception{
		log.debug("收到的people实例值:{}",people);
		//因为People某些字段不允许为空，所以提前做个合法判断
		if(people.isLegal()){
			Date date = new Date();
			people.setCreateDate(date);
			people.setUpdateDate(date);
			People thePeople = pser.addPeopel(people);
			return new Gson().toJson(thePeople);
		}
		return "some error with the People data-format.";
	}
	
	/**
	 *PUT请求方式
	 * 请求demo:{"id":1,"name":"wow","age":"24","sex":"female","tel":"12306","email":"123@136.com"}
	 * 这里需要注意两点
	 * 1.POST方法不是幂等的，而PUT方法则有幂等性
	 * 2.这里使用的saveAndFlush可能不是很准确，在id默认不传时，会新增一条记录。
	 * @param id
	 * @return 成功返回增加的People/失败返回错误提示
	 * @throws Exception 
	 */
	@RequestMapping(value =  "/update",method=RequestMethod.PUT)
	public String updatePeople(@RequestBody People people) throws Exception{
		log.debug("收到的people实例值:{}",people);
		//因为People某些字段不允许为空，所以提前做个合法判断
		if(people.isLegal()){
			Date date = new Date();
			people.setUpdateDate(date);
			People thePeople = pser.getRepository().saveAndFlush(people);
			return new Gson().toJson(thePeople);
		}
		return "some error with the People data-format.";
	}
	
	/**
	 *DELETE请求方式
	 * 请求demo:http://localhost:8567/keepal/delete?id=1
	 * @param id
	 * @return 成功返回增加的SUCCESS/失败返回错误提示
	 * @throws Exception 
	 */
	@RequestMapping(value =  "/delete",method=RequestMethod.DELETE)
	public String deletePeople(@RequestParam long id){
		try{
			pser.getRepository().deleteById(id);
		}catch(Exception e){
			log.error("执行删除异常e:{}",e.getMessage());
			return "DELETE FAIL,MSG:"+e.getMessage();
		}
		return "DELETE SUCCESS";
	}


	@RequestMapping("/message")
	public List<People> message() {
		log.info("访问:message");
		List<People> ps = pser.getRepository().findAll();
		if (ps == null || ps.size() <= 0) {
//			return "there are no people.";
			return null;
		} else {
			return ps;
		}
	}
	
	

	@RequestMapping("/hello")
	public String hello() throws Exception {
		log.info("访问:hello");
//		int x = 1/0;
		People peo = new People();
		Date date = new Date();
		peo.setAge("18");
		peo.setCreateDate(date);
		peo.setEmail("123@qq.com");
		peo.setName("keepal");
		peo.setSex("male");
		peo.setTel("10086");
		peo.setUpdateDate(date);
		peo = pser.addPeopel(peo);
		return new Gson().toJson(peo);
	}
}
