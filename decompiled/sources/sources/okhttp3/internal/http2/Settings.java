package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\r\u001a\u00020\u000eJ\u0019\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0086\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0005J\u0011\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0086\u0002J\u0006\u0010\u0014\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012J\u0006\u0010\u0017\u001a\u00020\u0005J\u000e\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005J\u000e\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005J\u000e\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\f\u0010\n¨\u0006\u001d"}, d2 = {"Lokhttp3/internal/http2/Settings;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "set", _UrlKt.FRAGMENT_ENCODE_SET, "values", _UrlKt.FRAGMENT_ENCODE_SET, "headerTableSize", "getHeaderTableSize", "()I", "initialWindowSize", "getInitialWindowSize", "clear", _UrlKt.FRAGMENT_ENCODE_SET, "id", "value", "isSet", _UrlKt.FRAGMENT_ENCODE_SET, "get", "size", "getEnablePush", "defaultValue", "getMaxConcurrentStreams", "getMaxFrameSize", "getMaxHeaderListSize", "merge", "other", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Settings {
    public static final int COUNT = 10;
    public static final Companion Companion = null;
    public static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535;
    public static final int ENABLE_PUSH = 2;
    public static final int HEADER_TABLE_SIZE = 1;
    public static final int INITIAL_WINDOW_SIZE = 4;
    public static final int MAX_CONCURRENT_STREAMS = 3;
    public static final int MAX_FRAME_SIZE = 5;
    public static final int MAX_HEADER_LIST_SIZE = 6;
    private int set;
    private final int[] values;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/internal/http2/Settings$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "DEFAULT_INITIAL_WINDOW_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "HEADER_TABLE_SIZE", "ENABLE_PUSH", "MAX_CONCURRENT_STREAMS", "INITIAL_WINDOW_SIZE", "MAX_FRAME_SIZE", "MAX_HEADER_LIST_SIZE", NotificationBadge.AdwHomeBadger.COUNT, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

    public Settings() {
        this.values = new int[10];
    }

    public final void clear() {
        this.set = 0;
        ArraysKt.fill$default(this.values, 0, 0, 0, 6, null);
    }

    public final int get(int r1) {
        return this.values[r1];
    }

    public final boolean getEnablePush(boolean r2) {
        if ((this.set & 4) != 0) goto L5;
        return r2;
    L5:
        if (this.values[2] != 1) goto L7;
        return true;
    L7:
        return false;
    }

    public final int getHeaderTableSize() {
        if ((this.set & 2) != 0) goto L5;
        return -1;
    L5:
        return this.values[1];
    }

    public final int getInitialWindowSize() {
        if ((this.set & 16) != 0) goto L5;
        return 65535;
    L5:
        return this.values[4];
    }

    public final int getMaxConcurrentStreams() {
        if ((this.set & 8) != 0) goto L5;
        return Integer.MAX_VALUE;
    L5:
        return this.values[3];
    }

    public final int getMaxFrameSize(int r2) {
        if ((this.set & 32) != 0) goto L5;
        return r2;
    L5:
        return this.values[5];
    }

    public final int getMaxHeaderListSize(int r2) {
        if ((this.set & 64) != 0) goto L5;
        return r2;
    L5:
        return this.values[6];
    }

    public final boolean isSet(int r2) {
        int r1 = this.set;
        if ((r1 & (1 << r2)) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final void merge(Settings r3) {
        int r0 = 0;
    L4:
        if (r0 >= 10) goto L9;
        if (r3.isSet(r0) == false) goto L8;
        set(r0, r3.get(r0));
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public final Settings set(int r4, int r5) {
        if (r4 < 0) goto L7;
        int[] r0 = this.values;
        if (r4 >= r0.length) goto L7;
        this.set = (1 << r4) | this.set;
        r0[r4] = r5;
    L7:
        return this;
    }

    public final int size() {
        return Integer.bitCount(this.set);
    }
}
