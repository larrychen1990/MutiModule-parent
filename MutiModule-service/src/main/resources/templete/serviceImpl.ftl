package com.alexgaoyh.MutiModule.service.${packageName}.impl;

import java.util.List;

import com.alexgaoyh.MutiModule.persist.${packageName}.${className};
import com.alexgaoyh.MutiModule.persist.${packageName}.${className}Example;
import com.alexgaoyh.MutiModule.persist.${packageName}.${className}Mapper;
import com.alexgaoyh.MutiModule.persist.util.Pagination;
import com.alexgaoyh.MutiModule.service.${packageName}.I${className}Service;

/**
 * 
 * @desc I${className}Service 接口实现类
 *
 * @author alexgaoyh
 */
public class ${className}ServiceImpl implements I${className}Service {

	private ${className}Mapper ${smallClassName}Mapper;

	//------------------get set方法 begin
	public ${className}Mapper get${className}Mapper() {
		return ${smallClassName}Mapper;
	}

	public void set${className}Mapper(${className}Mapper ${smallClassName}Mapper) {
		this.${smallClassName}Mapper = ${smallClassName}Mapper;
	}
	//------------------get set方法 end

	@Override
	public void insert(${className} ${smallClassName}) {
		${smallClassName}Mapper.insert(${smallClassName});
	}

	@Override
	public ${className} selectByPrimaryKey(Integer id) {
		return ${smallClassName}Mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKeySelective(${className} ${smallClassName}) {
		return ${smallClassName}Mapper.updateByPrimaryKeySelective(${smallClassName});
	}
	
	@Override
	public int countByExample(${className}Example example) {
		return ${smallClassName}Mapper.countByExample(example);
	}
	
	@Override
	public List<${className}> selectByExample(${className}Example example) {
		return ${smallClassName}Mapper.selectByExample(example);
	}

	@Override
	public Pagination<${className}> getPanigationByRowBounds(${className}Example example) {
		
		int count = ${smallClassName}Mapper.countByExample(example);
		List<${className}> list = ${smallClassName}Mapper.selectByExample(example);
		
		return new Pagination(example.getMyRowBounds(), count, list);
	}

}
