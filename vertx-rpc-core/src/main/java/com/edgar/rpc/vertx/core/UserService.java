package com.edgar.rpc.vertx.core;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * Created by edgar on 16-12-5.
 */
@ProxyGen
public interface UserService {

  void save(JsonObject jsonObject, Handler<AsyncResult<JsonObject>> handler);

  void get(Integer userId, Handler<AsyncResult<JsonObject>> handler);
}
