package com.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisSentinelProperties {
    private String master;

    /**
     * 哨兵节点
     */
    private String nodes;

    /**
     * 哨兵配置
     */
    private boolean masterOnlyWrite;

    /**
     *
     */
    private int failMax;
}
