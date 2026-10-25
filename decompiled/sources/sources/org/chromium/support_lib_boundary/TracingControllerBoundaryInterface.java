package org.chromium.support_lib_boundary;

import java.io.OutputStream;
import java.util.Collection;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public interface TracingControllerBoundaryInterface {
    boolean isTracing();

    void start(int r1, Collection<String> r2, int r3);

    boolean stop(OutputStream r1, Executor r2);
}
