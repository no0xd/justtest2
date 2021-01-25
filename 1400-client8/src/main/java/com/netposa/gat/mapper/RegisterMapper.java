package com.netposa.gat.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.netposa.gat.model.regist.RegisterContext;

@Mapper
public interface RegisterMapper {
    /**
     * @author netposa
     * @Description 注册实体插入
     * @return int
     **/
    public int insertRegister(RegisterContext register);
    
    /**
     * @author netposa
     * @Description 注册信息更新
     * @return int
     **/
//	@Update("UPDATE register SET Status=#{register.Status}, RegisterTime=#{register.RegisterTime}  WHERE DeviceID = #{register.DeviceID}")
    public int updateRegister(@Param("register")RegisterContext register);
    
    /**
     * @author netposa
     * @Description 获取注册信息
     * @return List
     **/
//    @Select("select * from register")
    public List<RegisterContext> getRegisters();
    
    /**
     * @author netposa
     * @Description 是否注册
     * @return String
     **/
//    @Select("SELECT r.Status  FROM register r WHERE	r.IP =  #{IP}")
    public String isRegist(String ip);

    /**
    * @author netposa
    * @Description 注销
    * @Param [register]
    * @return int
    **/
//    @Update("UPDATE register SET  Status = #{register.Status},CancelTime = #{register.CancelTime}  WHERE DeviceID = #{register.DeviceID}")
    public int unRegister(@Param("register") RegisterContext register);
}
