package com.aidijing.controller;

import com.aidijing.common.ResponseEntity;
import com.aidijing.domain.SystemConfig;
import com.aidijing.service.SystemConfigService;
import com.github.pagehelper.PageRowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 系统配置表(MYISAM引擎) 前端控制器
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-06-16
 */
@RestController
@RequestMapping( "/system-config" )
public class SystemConfigController {
    @Autowired
    private SystemConfigService systemConfigService;

    /*
            
         主键  id  Long         
             
         key  configKey  String         
             
         value  configValue  String         
             
         说明  configDescription  String         
             
         创建时间  createTime  Date         
             
         修改时间  updateTime  Date         
             
         备注  remark  String         
             
     */

    /**
     * URI    : { GET } /system-config/{id}
     *
     * @param id : 主键
     * @return
     */
    @GetMapping( "{id}" )
    public ResponseEntity< SystemConfig > get ( @PathVariable Long id ) {
        return ResponseEntity.ok().setResponseContent( systemConfigService.selectById( id ) );
    }

    /**
     * URI    : { GET } /system-config     
     *
     * @param pageRowBounds : {@link PageRowBounds}
     * @return
     */
    @GetMapping
    public ResponseEntity< SystemConfig > list ( PageRowBounds pageRowBounds ) {
        return ResponseEntity.ok().setResponseContent( systemConfigService.listPage( pageRowBounds ) );
    }

    /**
     * URI    : { POST } /system-config     
     *
     * @param systemConfig : {@link SystemConfig}
     * @return
     */
    @PostMapping
    public ResponseEntity< SystemConfig > insert ( @RequestBody SystemConfig systemConfig ) {
        if ( ! systemConfigService.insert( systemConfig ) ) {
            return ResponseEntity.ok( "保存失败" );
        }
        return ResponseEntity.ok( "保存成功" );
    }

    /**
     * URI    : { PUT } /system-config/{id}
     *
     * @param id           : 主键
     * @param systemConfig : {@link SystemConfig}
     * @return
     */
    @PutMapping( "{id}" )
    public ResponseEntity< SystemConfig > update ( @PathVariable Long id,
                                                   @RequestBody SystemConfig systemConfig ) {
        if ( ! systemConfigService.updateById( systemConfig.setId( id ) ) ) {
            return ResponseEntity.ok( "更新失败" );
        }
        return ResponseEntity.ok( "更新成功" );
    }

    /**
     * URI    : { DELETE } /system-config/{id}
     *
     * @param id : 主键
     * @return
     */
    @DeleteMapping( "{id}" )
    public ResponseEntity< SystemConfig > delete ( @PathVariable Long id ) {
        if ( ! systemConfigService.deleteById( id ) ) {
            return ResponseEntity.ok( "删除失败" );
        }
        return ResponseEntity.ok( "删除成功" );
    }


}