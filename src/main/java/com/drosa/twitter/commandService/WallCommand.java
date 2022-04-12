package com.drosa.twitter.commandService;

import com.drosa.twitter.domain.FollowedList;
import com.drosa.twitter.domain.Message;
import com.drosa.twitter.domain.Timeline;
import com.drosa.twitter.domain.User;
import com.drosa.twitter.repository.UserRepository;
import com.drosa.twitter.util.MessagePrinter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cuando se pide el wall, se consultan en el repositorio de followed
 * y se recopilan los mensajes, para luego ordenarlos y mostrarlos.
 * Este approach puede ser lento si se siguen a muchos usuarios, ya que
 * cada vez que se piede Wall, se deben de ordenar.
 * <p>
 * Otra aproximación, sería inyectar directamente en el wall de cada usuario que
 * sigue a otro, de esta forma la lectura sería inmediata. Para esto se necesitaría
 * mantener un repositorio-caché para cada wall de usuario
 */
public class WallCommand implements Command {
    private static final Pattern REGEX = Pattern.compile("^(\\S+) wall$");

    private final UserRepository userRepository;

    public WallCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String commandLine) {
        Matcher matcher = REGEX.matcher(commandLine);
        matcher.find();

        String userName = matcher.group(1);
        User user = userRepository.getUserOrAdd(userName);
        FollowedList followedList = user.getFollowedList();

        final List<User> usersToWallList = new ArrayList<>();
        usersToWallList.add(user);
        usersToWallList.addAll(followedList.getUserList());

        final List<Message> wall = new ArrayList<>();
        usersToWallList.forEach(userInWall -> {
            Timeline timeline = userInWall.getTimeLine();
            List<Message> messageList = timeline.getMessages();
            wall.addAll(messageList);
        });

        wall.sort(Comparator.comparing(Message::getCreatedAt).reversed());

        wall.forEach(MessagePrinter::printMessageForWall);

        return true;
    }

    public boolean matches(String input) {
        if (input != null && !input.isEmpty()) {
            Matcher matcher = REGEX.matcher(input);
            return matcher.find();
        }
        return false;
    }
}
