package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.example.admin.pojo.Admin;
import org.example.admin.pojo.Permission;

import java.util.List;

/***
 * @Description:
 * @Author: ZBZ
 * @Date: 2021/8/14
 */
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * find permission by admin id
     * @Author: ZBZ
     * @Date: 2021/8/14
     * @param adminId:
     * @return: java.util.List<org.example.admin.pojo.Permission>
     **/
    @Select("SELECT * FROM ms_permission where id in (select permission_id from ms_admin_permission where admin_id=#{adminId})")
    List<Permission> findPermissionByAdminId(Long adminId);
}
