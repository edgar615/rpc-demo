package com.edgar.rpc.vertx.producer;

import com.edgar.rpc.vertx.core.UserService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceException;

/**
 * Created by edgar on 16-12-5.
 */
public class UserServiceImpl implements UserService {
  @Override
  public void save(JsonObject jsonObject, Handler<AsyncResult<JsonObject>> handler) {
    if (!jsonObject.containsKey("username")) {
      handler.handle(ServiceException.fail(400, "invalid arguments"));
    } else {
      handler.handle(Future.succeededFuture(jsonObject.copy()));
    }
  }

  @Override
  public void get(Integer userId, Handler<AsyncResult<JsonObject>> handler) {
    if (userId <= 0) {
      handler.handle(Future.failedFuture("404 not found"));
    } else {
      handler.handle(Future.succeededFuture(new JsonObject()
          .put("userId", userId)
          .put("username", "edgar")));
    }
  }
}
