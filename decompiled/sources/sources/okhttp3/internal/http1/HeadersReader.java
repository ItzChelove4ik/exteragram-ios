package okhttp3.internal.http1;

import com.google.android.gms.cast.MediaStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.Headers;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSource;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/http1/HeadersReader;", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/BufferedSource;", "<init>", "(Lokio/BufferedSource;)V", "getSource", "()Lokio/BufferedSource;", "headerLimit", _UrlKt.FRAGMENT_ENCODE_SET, "readLine", _UrlKt.FRAGMENT_ENCODE_SET, "readHeaders", "Lokhttp3/Headers;", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class HeadersReader {
    public static final Companion Companion = null;
    private static final int HEADER_LIMIT = 262144;
    private long headerLimit;
    private final BufferedSource source;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lokhttp3/internal/http1/HeadersReader$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "HEADER_LIMIT", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public HeadersReader(BufferedSource r3) {
        this.source = r3;
        this.headerLimit = MediaStatus.COMMAND_STREAM_TRANSFER;
    }

    public final BufferedSource getSource() {
        return this.source;
    }

    public final Headers readHeaders() {
        Headers.Builder r0 = new Headers.Builder();
    L3:
        String r1 = readLine();
        if (r1.length() == 0) goto L6;
        r0.addLenient$okhttp(r1);
        goto L3
    L6:
        return r0.build();
    }

    public final String readLine() {
        String r0 = this.source.readUtf8LineStrict(this.headerLimit);
        this.headerLimit -= r0.length();
        return r0;
    }
}
