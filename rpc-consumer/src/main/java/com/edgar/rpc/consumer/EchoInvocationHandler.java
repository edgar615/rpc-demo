package com.edgar.rpc.consumer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by edgar on 16-12-4.
 */
public class EchoInvocationHandler implements InvocationHandler {

  private final Class<?> serviceClass;
  private final InetSocketAddress addr;

  public EchoInvocationHandler(Class<?> serviceClass, InetSocketAddress addr) {
    this.serviceClass = serviceClass;
    this.addr = addr;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Socket socket = null;
    ObjectOutputStream os = null;
    ObjectInputStream is = null;
    try {
      socket = new Socket();
      socket.connect(addr);
      os = new ObjectOutputStream(socket.getOutputStream());
      os.writeUTF(serviceClass.getName());
      os.writeUTF(method.getName());
      os.writeObject(method.getParameterTypes());
      os.writeObject(args);
      is = new ObjectInputStream(socket.getInputStream());
      return is.readObject();
    } finally {
      if (socket != null) {
        socket.close();
      }
      if (os != null) {
        os.close();
      }
      if (is != null) {
        is.close();
      }
    }
  }
}
