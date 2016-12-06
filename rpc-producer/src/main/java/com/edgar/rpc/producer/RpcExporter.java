package com.edgar.rpc.producer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by edgar on 16-12-4.
 */
public class RpcExporter {
  private static Executor executor =
      Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  public static void export(String host, int port) throws IOException {
    ServerSocket serverSocket =  new ServerSocket();
    serverSocket.bind(new InetSocketAddress(host, port));
    while (true) {
      executor.execute(new ExporterTask(serverSocket.accept()));
    }
  }
}
