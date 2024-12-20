package com.p5k.bacao.socket.core.handler;

import cn.hutool.extra.spring.SpringUtil;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.p5k.bacao.http.core.base.ResultObject;
import com.p5k.bacao.http.core.exception.ServiceException;
import com.p5k.bacao.http.core.exception.template.IResponseFactory;
import com.p5k.bacao.http.core.util.WebUtils;
import com.p5k.bacao.socket.core.enums.ListenEvent;
import com.p5k.bacao.socket.core.enums.SendEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public abstract class BaseHandler<T> implements DataListener<T> {

  final ListenEvent listenEvent;
  private final IResponseFactory responseFactory = SpringUtil.getBean(IResponseFactory.class);

  protected BaseHandler(ListenEvent listenEvent) {
    this.listenEvent = listenEvent;
  }

  @Override
  public void onData(SocketIOClient socketIOClient, T t, AckRequest ackRequest) {
    String userId = socketIOClient.get("userId");
    try {
      long startTime = System.currentTimeMillis();
      onData(socketIOClient, t, ackRequest, userId);
      long endTime = System.currentTimeMillis();
      log.info("Spent duration: {}ms", endTime - startTime);

    } catch (ServiceException e) {
      socketIOClient.sendEvent(SendEvent.EX_ERROR.getMessage(), handleAPIException(e));
    } catch (Exception e) {
      socketIOClient.sendEvent(SendEvent.EX_ERROR.getMessage(),
          responseFactory.produce(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }
  }

  public String getEventName() {
    return listenEvent.getMessage();
  }

  public abstract void onData(SocketIOClient client, T t, AckRequest ackRequest, String userId);


  private ResultObject<Object> handleAPIException(ServiceException exception) {
    if (null != exception.getResponseCodeEnum()) {
      String message = WebUtils.getMessage(exception.getResponseCodeEnum().getMessage()
          , exception.getParams());
      return responseFactory.produce(exception.getResponseCodeEnum().getCode(), message);
    } else {
      return responseFactory.produce(HttpStatus.INTERNAL_SERVER_ERROR.value(),
          exception.getMessage());
    }
  }
}
