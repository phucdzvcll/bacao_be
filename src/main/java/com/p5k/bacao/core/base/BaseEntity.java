package com.p5k.bacao.core.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.p5k.bacao.core.pools.TimeZonePool;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    //@OrderBy
    @TableField(value = "systime", fill = FieldFill.INSERT)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(timezone = TimeZonePool.GMT_KR, shape = JsonFormat.Shape.STRING)
    private LocalDateTime systime;

    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(value = "created_name", fill = FieldFill.INSERT)
    private String createdName;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(timezone = TimeZonePool.GMT_KR, shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @TableField(value = "updated_by", fill = FieldFill.UPDATE)
    private String updatedBy;

    @TableField(value = "updated_name", fill = FieldFill.UPDATE)
    private String updatedName;

    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(timezone = TimeZonePool.GMT_KR, shape = JsonFormat.Shape.STRING)
    private LocalDateTime updatedAt;

    @TableField("del_flag")
    private String delFlag;

    @TableField(value = "use_yn")
    private String useYn;
}
