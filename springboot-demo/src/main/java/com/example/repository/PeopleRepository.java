package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.People;

/**
 * People对应的Repository
 * @author keepal
 * @date 2018年11月17日
 */
@Repository
public interface PeopleRepository extends JpaRepository<People, Long>{

	/**2018/7/22
	 * 这里有两个坑:
	 * 1.Query这个后面只能跟实体类名称不能跟表名
	 * 2.其次delete操作需要加上注解@Modifying，其次在对应的@Service那里加上事物注解:@Transactional
	 */
	@Modifying
	@Query(value = "delete from People  where name =:name")
	void delByName(@Param(value = "name")String name);
	
	@Modifying
	@Query(value = "update People set age = ?2 where name = ?1")
	void updateByName(String name,String newAge);
	
	/**2018/7/22
	 * 按照规范的命名，去执行相关sql.支持find/get
	 */
	List<People> getByName(String name);
	
	
	
	
	
	
	
	
	
	
}
