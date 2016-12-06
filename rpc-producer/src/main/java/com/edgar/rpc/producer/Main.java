package com.edgar.rpc.producer;

import java.io.IOException;

/**
 * Created by edgar on 16-12-4.
 */
public class Main {
  public static void main(String[] args) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          RpcExporter.export("localhost", 8080);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}
