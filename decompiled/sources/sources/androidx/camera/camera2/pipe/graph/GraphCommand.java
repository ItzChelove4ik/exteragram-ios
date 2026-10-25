package androidx.camera.camera2.pipe.graph;

import androidx.camera.camera2.pipe.Request;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\n\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\u0082\u0001\n\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphCommand;", _UrlKt.FRAGMENT_ENCODE_SET, "Invalidate", "Shutdown", "Stop", "Abort", "RequestProcessor", "Parameters", "Listeners", "Repeat", "Capture", "Trigger", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Abort;", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Capture;", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Invalidate;", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Listeners;", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Parameters;", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Repeat;", "Landroidx/camera/camera2/pipe/graph/GraphCommand$RequestProcessor;", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Shutdown;", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Stop;", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Trigger;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface GraphCommand {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphCommand$Abort;", "Landroidx/camera/camera2/pipe/graph/GraphCommand;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Abort implements GraphCommand {
        public static final Abort INSTANCE = null;

        static {
            INSTANCE = new Abort();
        }

        private Abort() {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphCommand$Capture;", "Landroidx/camera/camera2/pipe/graph/GraphCommand;", "requests", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request;", "<init>", "(Ljava/util/List;)V", "getRequests", "()Ljava/util/List;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Capture implements GraphCommand {
        private final List<Request> requests;

        public Capture(List<Request> r1) {
            this.requests = r1;
        }

        public final List<Request> getRequests() {
            return this.requests;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphCommand$Invalidate;", "Landroidx/camera/camera2/pipe/graph/GraphCommand;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Invalidate implements GraphCommand {
        public static final Invalidate INSTANCE = null;

        static {
            INSTANCE = new Invalidate();
        }

        private Invalidate() {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphCommand$Listeners;", "Landroidx/camera/camera2/pipe/graph/GraphCommand;", "listeners", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request$Listener;", "<init>", "(Ljava/util/List;)V", "getListeners", "()Ljava/util/List;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Listeners implements GraphCommand {
        private final List<Request.Listener> listeners;

        /* JADX WARN: Multi-variable type inference failed */
        public Listeners(List<? extends Request.Listener> r1) {
            this.listeners = r1;
        }

        public final List<Request.Listener> getListeners() {
            return this.listeners;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0007\u0018\u00002\u00020\u0001B/\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001d\u0010\u0002\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\u0005\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphCommand$Parameters;", "Landroidx/camera/camera2/pipe/graph/GraphCommand;", "graphParameters", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "graph3AParameters", "<init>", "(Ljava/util/Map;Ljava/util/Map;)V", "getGraphParameters", "()Ljava/util/Map;", "getGraph3AParameters", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Parameters implements GraphCommand {
        private final Map<?, Object> graph3AParameters;
        private final Map<?, Object> graphParameters;

        public Parameters(Map<?, ? extends Object> r1, Map<?, ? extends Object> r2) {
            this.graphParameters = r1;
            this.graph3AParameters = r2;
        }

        public final Map<?, Object> getGraph3AParameters() {
            return this.graph3AParameters;
        }

        public final Map<?, Object> getGraphParameters() {
            return this.graphParameters;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphCommand$Repeat;", "Landroidx/camera/camera2/pipe/graph/GraphCommand;", PluginsConstants.REQUEST, "Landroidx/camera/camera2/pipe/Request;", "<init>", "(Landroidx/camera/camera2/pipe/Request;)V", "getRequest", "()Landroidx/camera/camera2/pipe/Request;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Repeat implements GraphCommand {
        private final Request request;

        public Repeat(Request r1) {
            this.request = r1;
        }

        public final Request getRequest() {
            return this.request;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphCommand$RequestProcessor;", "Landroidx/camera/camera2/pipe/graph/GraphCommand;", "old", "Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", "new", "<init>", "(Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;)V", "getOld", "()Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", "getNew", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RequestProcessor implements GraphCommand {

        /* renamed from: new, reason: not valid java name */
        private final GraphRequestProcessor f0new;
        private final GraphRequestProcessor old;

        public RequestProcessor(GraphRequestProcessor r1, GraphRequestProcessor r2) {
            this.old = r1;
            this.f0new = r2;
        }

        public final GraphRequestProcessor getNew() {
            return this.f0new;
        }

        public final GraphRequestProcessor getOld() {
            return this.old;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphCommand$Shutdown;", "Landroidx/camera/camera2/pipe/graph/GraphCommand;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Shutdown implements GraphCommand {
        public static final Shutdown INSTANCE = null;

        static {
            INSTANCE = new Shutdown();
        }

        private Shutdown() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphCommand$Stop;", "Landroidx/camera/camera2/pipe/graph/GraphCommand;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Stop implements GraphCommand {
        public static final Stop INSTANCE = null;

        static {
            INSTANCE = new Stop();
        }

        private Stop() {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001d\u0010\u0002\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphCommand$Trigger;", "Landroidx/camera/camera2/pipe/graph/GraphCommand;", "triggerParameters", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/util/Map;)V", "getTriggerParameters", "()Ljava/util/Map;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Trigger implements GraphCommand {
        private final Map<?, Object> triggerParameters;

        public Trigger(Map<?, ? extends Object> r1) {
            this.triggerParameters = r1;
        }

        public final Map<?, Object> getTriggerParameters() {
            return this.triggerParameters;
        }
    }
}
