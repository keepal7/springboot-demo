package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.People;
import com.example.repository.PeopleRepository;

/**
 * People对应的service
 * @author keepal
 * @date 2018年11月15日
 */
//@Transactional  //标注class时，代表该类所有方法都需要进行事物管理 
@Service
public class PeopleService {

	@Autowired
	private PeopleRepository peopleRepository;

	/**
	 * 	标注方法时  代表该方法需要进行事物管理 
	 *  传播行为类型：
	 *  SUPPORTS:支持当前事物，如果当前没有事物则以非事物方式执行。一般用于查询。
	 *  REQUIRED:如果当前不存在事物，就新建一个事物。如果已存在那么就加入当前事物。一般用于DML
	 *  
	 *  MANDATORY:使用当前的事务，如果当前没有事务，就抛出异常。
	 *  NEVER:以非事物方式执行，如果存在事物则抛出异常。
	 *  REQUIRES_NEW:新建事务，如果当前存在事务，把当前事务挂起。
	 *  SUPPORTED:以非事物方式执行，如果存在就挂起当前事物。
	 *  NESTED:如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。
	 */
	@Transactional(rollbackFor = Exception.class,propagation=Propagation.REQUIRED)
	public People addPeopel(People p){
		People pe =	peopleRepository.save(p);
//		int x = 1/0;//事物测试代码
		return pe;
	}
	
	@Transactional(rollbackFor = Exception.class,propagation=Propagation.REQUIRED)
	public void delByName(String name) {
		peopleRepository.delByName(name);
//		int x = 1/0;//事物测试代码
	}
	
	@Transactional(rollbackFor = Exception.class,propagation=Propagation.REQUIRED)
	public void updateByName(String name, String newAge) {
		peopleRepository.updateByName(name, newAge);
//		int x = 1/0;//事物测试代码
	}

	public List<People> findByName(String name) {
		return peopleRepository.getByName(name);
	}

	/**
	 * 这个方法是为了方便调用jpa默认的方法
	 * 如果要单独对一些方法增添事物的话
	 * 需要单独在service层新建一个方法再加上事物注解
	 */
	public PeopleRepository getRepository() {
		return peopleRepository;
	}

}
