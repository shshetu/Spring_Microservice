package com.shetu.spring_microservice.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(AcountsWebApplication.class)
public class AccountServer {
}
