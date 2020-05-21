package com.sy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sy.dao.XpgMapper;
import com.sy.entity.Xpg;
import com.sy.service.XpgService;

@Service
public class XpgServiceImpl implements XpgService {

	@Autowired
	private XpgMapper xpgMapper;

	@Override
	public Xpg selectXpgById(Integer id) {
		return xpgMapper.selectXpgById(id);
	}

	@Override
	@Transactional
	public Xpg insertXpg(Xpg xpg) {
		Xpg xpg1 = xpgMapper.selectXpgByName(xpg.getName());
		if(xpg1 != null){
			throw new RuntimeException("该4G码已存在，请重新输入");
		}
		xpgMapper.insertXpg(xpg);
		return xpg;
	}

	@Override
	public List<Xpg> selectXpgList(Xpg xpg) {
		return xpgMapper.selectXpgList(xpg);
	}
}
