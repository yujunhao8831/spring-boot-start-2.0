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
 * 后台管理用户历史记录操作表(MYISAM引擎)
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-06-15
 */
@Data
@Accessors(chain = true)
@TableName("manager_user_action_history")
public class ManagerUserActionHistory extends Model<ManagerUserActionHistory> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 后台管理用户ID
     */
	@TableField("manager_user_id")
	private Long managerUserId;
    /**
     * 后台管理用户真实姓名
     */
	@TableField("manager_user_real_name")
	private String managerUserRealName;
    /**
     * 操作级别(FATAL_1 : 致命,能影响到应用 , ERROR_2 : 错误,会影响正常功能, WARN_3 : 日常警告 ,INFO_5 : 日常记录)
     */
	@TableField("action_level")
	private String actionLevel;
    /**
     * 操作类型
     */
	@TableField("action_type")
	private String actionType;
    /**
     * 操作日志(也用于可以存储异常栈信息,或者运行的sql)
     */
	@TableField("action_log")
	private String actionLog;
    /**
     * 操作ip地址
     */
	@TableField("acion_ip_address")
	private String acionIpAddress;
    /**
     * 操作描述
     */
	@TableField("action_description")
	private String actionDescription;
    /**
     * 是否警报(注意【强制】POJO 类的 Boolean 属性不能加 is，而数据库字段必须加 is_，要求在 resultMap 中 进行字段与属性之间的映射。)
     */
	@TableField("is_warn")
	private Boolean warn;
    /**
     * 动作开始时间
     */
	@TableField("action_start_time")
	private Date actionStartTime;
    /**
     * 动作结束时间
     */
	@TableField("action_end_time")
	private Date actionEndTime;
    /**
     * 总执行时间
     */
	@TableField("aciton_total_time")
	private Date acitonTotalTime;
    /**
     * 操作类
     */
	@TableField("action_class")
	private String actionClass;
    /**
     * 操作方法
     */
	@TableField("action_method")
	private String actionMethod;
    /**
     * 方法参数
     */
	@TableField("action_args")
	private String actionArgs;
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ManagerUserActionHistory{" +
			"id=" + id +
			", managerUserId=" + managerUserId +
			", managerUserRealName=" + managerUserRealName +
			", actionLevel=" + actionLevel +
			", actionType=" + actionType +
			", actionLog=" + actionLog +
			", acionIpAddress=" + acionIpAddress +
			", actionDescription=" + actionDescription +
			", warn=" + warn +
			", actionStartTime=" + actionStartTime +
			", actionEndTime=" + actionEndTime +
			", acitonTotalTime=" + acitonTotalTime +
			", actionClass=" + actionClass +
			", actionMethod=" + actionMethod +
			", actionArgs=" + actionArgs +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
