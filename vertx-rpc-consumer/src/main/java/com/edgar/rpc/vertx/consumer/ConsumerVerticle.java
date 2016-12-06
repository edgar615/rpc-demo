package com.edgar.rpc.vertx.consumer;

import com.edgar.rpc.vertx.core.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * Created by edgar on 16-12-5.
 */
public class ConsumerVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    new Launcher().execute("run", ConsumerVerticle.class.getName(), "--cluster");
  }

  @Override
  public void start() throws Exception {
    UserService userService = ProxyHelper.createProxy(UserService.class, vertx, "rpc.demo.userservice");
    userService.get(1, ar -> {
      if (ar.succeeded()) {
        System.out.println(ar.result());
      } else {
        System.err.println(ar.cause().getMessage());
      }
    });
  }
}
