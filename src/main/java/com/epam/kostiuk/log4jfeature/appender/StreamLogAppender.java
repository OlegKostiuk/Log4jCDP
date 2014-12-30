package com.epam.kostiuk.log4jfeature.appender;

import org.apache.log4j.Category;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Custom log appender was used for multi thread logging. Each thread load his logs to stream in this appender.
 */

public class StreamLogAppender extends WriterAppender {

    private static final Logger LOG = Logger.getLogger(StreamLogAppender.class);
    public static final String STREAM_APPENDER = "stream_log";

    static ThreadLocal<ByteArrayOutputStream> streamPerThread = new ThreadLocal<ByteArrayOutputStream>() {
        @Override
        protected ByteArrayOutputStream initialValue() {
            return new ByteArrayOutputStream();
        }
    };

    public static ByteArrayOutputStream getCurrentStreamAppender() {

        StreamLogAppender appender = (StreamLogAppender) LOG.getAppender(STREAM_APPENDER);
        while (appender == null) {
            Category parent = LOG.getParent();
            if (parent == null) {
                break;
            }
            appender = (StreamLogAppender) parent.getAppender(STREAM_APPENDER);
        }

        return appender.getCurrentStream();
    }

    public StreamLogAppender() {
    }

    public StreamLogAppender(Layout layout) {
        setLayout(layout);
        activateOptions();
    }

    public ByteArrayOutputStream getCurrentStream() {
        return streamPerThread.get();
    }

    @Override
    public void activateOptions() {
        setWriter(createWriter(new CurrentThreadOutStream()));
    }

    private static class CurrentThreadOutStream extends ByteArrayOutputStream {
        public CurrentThreadOutStream() {
        }

        @Override
        public void close() {
        }

        @Override
        public void flush() throws IOException {
            ByteArrayOutputStream stream = streamPerThread.get();
            if (stream != null) {
                stream.flush();
            }
        }

        @Override
        public void write(final byte[] b) throws IOException {
            ByteArrayOutputStream stream = streamPerThread.get();
            if (stream != null) {
                stream.write(b);
            }
        }

        @Override
        public void write(final byte[] b, final int off, final int len) {
            ByteArrayOutputStream stream = streamPerThread.get();
            if (stream != null) {
                stream.write(b, off, len);
            }
        }

        @Override
        public void write(final int b) {
            ByteArrayOutputStream stream = streamPerThread.get();
            if (stream != null) {
                stream.write(b);
            }
        }
    }
}
