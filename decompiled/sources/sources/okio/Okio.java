package okio;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;

@Metadata(d1 = {"okio/Okio__JvmOkioKt", "okio/Okio__OkioKt"}, d2 = {}, k = 4, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class Okio {
    @JvmName(name = "blackhole")
    public static final Sink blackhole() {
        return Okio__OkioKt.blackhole();
    }

    public static final BufferedSink buffer(Sink r0) {
        return Okio__OkioKt.buffer(r0);
    }

    public static final BufferedSource buffer(Source r0) {
        return Okio__OkioKt.buffer(r0);
    }

    @JvmOverloads
    public static final Sink sink(File r0, boolean r1) {
        return Okio__JvmOkioKt.sink(r0, r1);
    }

    public static final Sink sink(OutputStream r0) {
        return Okio__JvmOkioKt.sink(r0);
    }

    public static /* synthetic */ Sink sink$default(File r0, boolean r1, int r2, Object r3) {
        return Okio__JvmOkioKt.sink$default(r0, r1, r2, r3);
    }

    @JvmName(name = "socket")
    public static final Socket socket(java.net.Socket r0) {
        return Okio__JvmOkioKt.socket(r0);
    }

    public static final Source source(File r0) {
        return Okio__JvmOkioKt.source(r0);
    }

    public static final Source source(InputStream r0) {
        return Okio__JvmOkioKt.source(r0);
    }
}
