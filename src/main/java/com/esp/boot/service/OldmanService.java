package com.esp.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esp.boot.entity.EspOldmaninfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 老人信息service层
 */
public interface OldmanService extends IService<EspOldmaninfo> {/*继承mybatis顶级类，操作EspOldmaninfo*/

    /**
     * 根据id查询一条数据
     * @param id
     * @return
     */

    /**
     * 查询出所有老人数据
     */
    List<EspOldmaninfo> getAllInfo(EspOldmaninfo espOldmaninfo);

    /**
     *查询老人所有数据
     */
    List<EspOldmaninfo> getAllInfo();

    /**
     * 增加老人数据
     */
    public boolean insertinfo(EspOldmaninfo espOldmaninfo);
    /**
     * 删除老人数据，通过编号
     */
    public boolean deleteinfo(String no);

    /**
     * 修改老人信息
     */
    public boolean updateinfo(EspOldmaninfo espOldmaninfo);

    /**
     * 通过编号查询老人信息
     */
    EspOldmaninfo getOneInfo(String oldmanSerialnumber);

    /**
     * 通过身份证查询老人信息
     */
    EspOldmaninfo getOneInfoByIdN(String oldmanIdnumber);
    /**
     * 查看某看护名下所有老人
     */

    /**
     * 给老人分配房间号
     */
}
