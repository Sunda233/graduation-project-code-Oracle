package com.esp.boot.mapper;

import com.esp.boot.entity.EspChatlog;
import com.esp.boot.entity.EspVisitappointment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatlogMapper {
    /**
     * 看护人员查询收件箱发件箱
     */
    List<EspChatlog> getSomeInfoByCaregiver(@Param("no") String no, @Param("shoufa") Integer shoufa);
    /**
     * 亲属查询收件箱发件箱
     */
    List<EspChatlog> getSomeInfoByRelatives(@Param("no") String no, @Param("shoufa") Integer shoufa);

    /**
     * 通过ID查询
     */
    EspChatlog getOneInfoByID(@Param("id") Integer id);

    /**
     * 添加一条站内信
     */
    public boolean insertinfo(EspChatlog info);

    /**
     * 看护删除私信
     *
     * @return
     */
    public boolean deleteinfoByCaregiver(@Param("id") Integer id);

    /**
     * 亲属删除私信
     */
    public boolean deleteinfoByRelatives(@Param("id") Integer id);

    /**
     * 真正删除私信
     */
    public boolean deleteinfo(@Param("id") Integer id);
}
