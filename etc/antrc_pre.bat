@echo off
set ANT_OPTS=-Djava.library.path="%USERPROFILE%"/.ant -Dant.logger.defaults="%USERPROFILE%"/.ant/ansi.color
set ANT_ARGS=-logger com.hhnn.tools.ant.listener.WinColorConsoleLogger
