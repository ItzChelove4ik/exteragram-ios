package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import okhttp3.internal.url._UrlKt;
import okio.internal.DefaultSocket;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0006\u001a\u00020\u0005*\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0013\u0010\f\u001a\u00020\t*\u00020\bH\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\u001d\u0010\u0002\u001a\u00020\u0001*\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0002\u0010\u0010\u001a\u0011\u0010\u0006\u001a\u00020\u0005*\u00020\r¢\u0006\u0004\b\u0006\u0010\u0011¨\u0006\u0012"}, d2 = {"Ljava/io/OutputStream;", "Lokio/Sink;", "sink", "(Ljava/io/OutputStream;)Lokio/Sink;", "Ljava/io/InputStream;", "Lokio/Source;", "source", "(Ljava/io/InputStream;)Lokio/Source;", "Ljava/net/Socket;", "Lokio/Socket;", "socket", "(Ljava/net/Socket;)Lokio/Socket;", "asOkioSocket", "Ljava/io/File;", _UrlKt.FRAGMENT_ENCODE_SET, "append", "(Ljava/io/File;Z)Lokio/Sink;", "(Ljava/io/File;)Lokio/Source;", "okio"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "okio/Okio")
/* loaded from: classes.dex */
abstract /* synthetic */ class Okio__JvmOkioKt {
    @JvmOverloads
    public static final Sink sink(File r1, boolean r2) {
        return Okio.sink(new FileOutputStream(r1, r2));
    }

    public static final Sink sink(OutputStream r2) {
        return new OutputStreamSink(r2, new Timeout());
    }

    public static /* synthetic */ Sink sink$default(File r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = false;
    L6:
        return Okio.sink(r0, r1);
    }

    @JvmName(name = "socket")
    public static final Socket socket(java.net.Socket r1) {
        return new DefaultSocket(r1);
    }

    public static final Source source(File r2) {
        return new InputStreamSource(new FileInputStream(r2), Timeout.NONE);
    }

    public static final Source source(InputStream r2) {
        return new InputStreamSource(r2, new Timeout());
    }
}
