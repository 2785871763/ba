package com.zje.server;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.net.InetAddress;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/7/16 8:08
 */
@Import(SpringUtil.class)
@SpringBootApplication(scanBasePackages = {"com.zje"})
public class ServerApplication implements CommandLineRunner {

    @NacosInjected
    private NamingService namingService;
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${netty.port}")
    private int nettyPort;


    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class);
    }

    /**
     * 注册到 注册中心，服务发现，也可以用 zookeeper 其他组件
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        //获得本机IP
        String addr = InetAddress.getLocalHost().getHostAddress();
        namingService.registerInstance(applicationName, addr, nettyPort);
    }
}
