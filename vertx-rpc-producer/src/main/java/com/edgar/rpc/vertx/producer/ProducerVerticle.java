package com.edgar.rpc.vertx.producer;

import com.edgar.rpc.vertx.core.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * Created by edgar on 16-12-5.
 */
public class ProducerVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    new Launcher().execute("run", ProducerVerticle.class.getName(), "--cluster");
  }

  @Override
  public void start() throws Exception {
    UserService userService = new UserServiceImpl();
    ProxyHelper.registerService(UserService.class, vertx, userService, "rpc.demo.userservice");
  }
}
