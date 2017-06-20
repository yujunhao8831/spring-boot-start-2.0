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
 * 后台管理用户角色中间表
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-06-19
 */
@Data
@Accessors(chain = true)
@TableName("manager_user_role")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private  Long  id;
    /**
     * 后台管理用户_id
     */
	@TableField("user_id")
	private  Long  userId;
    /**
     * 后台管理角色_id
     */
	@TableField("role_id")
	private  Long  roleId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
