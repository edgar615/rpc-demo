package com.edgar.rpc.consumer;

import com.edgar.rpc.core.EchoService;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;

/**
 * Created by edgar on 16-12-4.
 */
public class RpcTest {

  public static void main(String[] args) {
    EchoService echoService = (EchoService) new RpcImporter().importer(EchoService.class, new InetSocketAddress("localhost", 8080));
    System.out.println(echoService.echo("edgar"));
  }
}
