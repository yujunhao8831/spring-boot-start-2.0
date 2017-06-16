package com.aidijing.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 后台管理角色资源中间表
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-06-15
 */
@Data
@Accessors(chain = true)
@TableName("manager_role_permission_resource")
public class ManagerRolePermissionResource extends Model<ManagerRolePermissionResource> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 后台管理角色_id
     */
	@TableField("manager_role_id")
	private Long managerRoleId;
    /**
     * 后台管理权限资源_id
     */
	@TableField("manager_permission_resource_id")
	private Long managerPermissionResourceId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ManagerRolePermissionResource{" +
			"id=" + id +
			", managerRoleId=" + managerRoleId +
			", managerPermissionResourceId=" + managerPermissionResourceId +
			"}";
	}
}
