package com.drosa.twitter.domain.service.printer;

import com.drosa.twitter.domain.entity.Message;

public interface MessagePrinter {

  void printMessageForWall(Message message);

  void printMessageForTimeLine(Message message);
}
