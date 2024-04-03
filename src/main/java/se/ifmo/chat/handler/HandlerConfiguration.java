package se.ifmo.chat.handler;

import se.ifmo.core.commands.Command;

import java.util.List;

public record HandlerConfiguration(List<Command> commands) {}
