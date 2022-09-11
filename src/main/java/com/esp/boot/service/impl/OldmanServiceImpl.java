package com.esp.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esp.boot.entity.EspOldmaninfo;

import com.esp.boot.mapper.OldManMapper;
import com.esp.boot.service.OldmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*extends ServiceImpl<OldManMapper,EspOldmaninfo>,指定用哪张表，传哪个泛型，可以基本不用写方法，直接control+f12
* 例如查询所有直接使用list，controller层*/
@Service
public class OldmanServiceImpl extends ServiceImpl<OldManMapper,EspOldmaninfo> implements OldmanService {

    @Autowired
    OldManMapper oldManMapper;


    @Override
    public List<EspOldmaninfo> getAllInfo(EspOldmaninfo espOldmaninfo) {
        return null;
    /*   return oldManMapper.getAllInfo(espOldmaninfo);*/
    }

    /**
     * 获取所有老人信息
     * @return
     */
    @Override
    public List<EspOldmaninfo> getAllInfo() {
        return oldManMapper.getAllInfo();
    }

    /**
     * 添加一条老人信息
     * @param espOldmaninfo
     * @return
     */
    @Override
    public boolean insertinfo(EspOldmaninfo espOldmaninfo) {
        return oldManMapper.insertinfo(espOldmaninfo);
    }

    /**
     * 通过编号删除老人信息
     * @param no
     * @return
     */
    @Override
    public boolean deleteinfo(String no) {
        return oldManMapper.deleteinfo(no);
    }

    /**
     * 修改老人信息
     * @param espOldmaninfo
     * @return
     */
    @Override
    public boolean updateinfo(EspOldmaninfo espOldmaninfo) {
        System.out.println(espOldmaninfo);
        return oldManMapper.updateinfo(espOldmaninfo);
    }

    @Override
    public EspOldmaninfo getOneInfo(String oldmanSerialnumber) {
        return oldManMapper.getOneInfo(oldmanSerialnumber);
    }

    @Override
    public EspOldmaninfo getOneInfoByIdN(String oldmanIdnumber) {
        return oldManMapper.getOneInfoByIdN(oldmanIdnumber);
    }

}
