package com.edgar.rpc.consumer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by edgar on 16-12-4.
 */
public class RpcImporter<S> {

  public S importer(final Class<?> serviceClass, final InetSocketAddress addr) {
    return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(),
        new Class[]{serviceClass},
        new InvocationHandler() {
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
              //close
            }
          }
        });
  }
}
