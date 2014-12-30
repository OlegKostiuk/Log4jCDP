Log4j features project
========

- created console, file, rollingfile, MySQL, email appenders with different configs.
- used all log4j levels.
- implemented custom level between INFO and WARN.
- implemented custom appender. It aggregate all logs to stream. It support multi threading, each thread has own stream,
thus we can divide logs by threads and work with separate thread logs.
