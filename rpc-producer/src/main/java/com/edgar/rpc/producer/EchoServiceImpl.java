package com.edgar.rpc.producer;

import com.edgar.rpc.core.EchoService;

/**
 * Created by edgar on 16-12-4.
 */
public class EchoServiceImpl implements EchoService {
  @Override
  public String echo(String ping) {
    return "pong : " + ping;
  }
}
