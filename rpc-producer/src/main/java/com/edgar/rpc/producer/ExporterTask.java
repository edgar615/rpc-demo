package com.edgar.rpc.producer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ServiceLoader;

/**
 * Created by edgar on 16-12-4.
 */
public class ExporterTask implements Runnable {

  private final Socket socket;

  public ExporterTask(Socket socket) {
    this.socket = socket;
  }

  public Object load(Class<?> serviceClass) {
    return ServiceLoader.load(serviceClass).iterator().next();
  }

  @Override
  public void run() {
    ObjectInputStream is = null;
    ObjectOutputStream os = null;

    try {
      is = new ObjectInputStream(socket.getInputStream());
      String interfaceName = is.readUTF();
      Class<?> service = Class.forName(interfaceName);
      String methodName = is.readUTF();
      Class<?>[] parameterTypes = (Class<?>[]) is.readObject();
      Object[] args = (Object[]) is.readObject();
      Method method = service.getMethod(methodName, parameterTypes);
      Object result = method.invoke(load(service), args);
      os = new ObjectOutputStream(socket.getOutputStream());
      os.writeObject(result);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (os != null) {
        try {
          os.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (socket != null) {
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
