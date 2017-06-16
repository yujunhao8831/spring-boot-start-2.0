package com.aidijing.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 后台管理角色表
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-06-15
 */
@Data
@Accessors(chain = true)
@TableName("manager_role")
public class ManagerRole extends Model<ManagerRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 角色名称
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 角色名称core
     */
	@TableField("role_name_code")
	private String roleNameCode;
    /**
     * 描述
     */
	private String description;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 备注
     */
	private String remark;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ManagerRole{" +
			"id=" + id +
			", roleName=" + roleName +
			", roleNameCode=" + roleNameCode +
			", description=" + description +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", remark=" + remark +
			"}";
	}
}
