package com.aidijing.domain.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * {@link com.aidijing.domain.ManagerPermissionResource} 实体中所有枚举值
 *
 * @author : 披荆斩棘
 * @date : 2016/12/14
 */
public interface ManagerPermissionResourceEnum {

    /**
     * 资源类型(API:接口,MENU:菜单,BUTTON:按钮)
     * <p>
     * 对应字段 {@link com.aidijing.domain.ManagerPermissionResource#resourceType}
     */
    @Getter
    enum ResourceType implements BaseEnumInterface< ResourceType > {

        API( "API", "接口" ),
        MENU( "MENU", "菜单" ),
        BUTTON( "BUTTON", "按钮" );

        /** code **/
        private String code;
        /** 注释 **/
        private String comment;

        ResourceType ( String code, String comment ) {
            this.code = code;
            this.comment = comment;
        }

        @Override
        public boolean isEnumCode ( final String inputCode ) {
            return Objects.nonNull( getEnum( inputCode ) );
        }

        @Override
        public boolean isNotEnumCode ( final String inputCode ) {
            return ! isEnumCode( inputCode );
        }

        @Override
        public String getCodeComment ( final String inputCode ) {
            if ( Objects.isNull( inputCode ) ) {
                return null;
            }
            for ( ResourceType value : ResourceType.values() ) {
                if ( value.getCode().equals( inputCode ) ) {
                    return value.getComment();
                }
            }
            return null;
        }

        @Override
        public ResourceType getEnum ( final String inputCode ) {
            if ( Objects.isNull( inputCode ) ) {
                return null;
            }
            for ( ResourceType thisEnum : ResourceType.values() ) {
                if ( thisEnum.getCode().equals( inputCode ) ) {
                    return thisEnum;
                }
            }
            return null;
        }


    }


}
