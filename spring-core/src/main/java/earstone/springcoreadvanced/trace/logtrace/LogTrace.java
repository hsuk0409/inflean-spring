package earstone.springcoreadvanced.trace.logtrace;


import earstone.springcoreadvanced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);

}
