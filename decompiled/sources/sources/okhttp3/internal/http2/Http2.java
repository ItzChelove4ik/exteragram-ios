package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.text.StringsKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.url._UrlKt;
import okio.ByteString;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007J&\u0010'\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010(\u001a\u00020)J\u0015\u0010*\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u0007H\u0000¢\u0006\u0002\b+J\u0016\u0010,\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u0018\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001d¨\u0006-"}, d2 = {"Lokhttp3/internal/http2/Http2;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CONNECTION_PREFACE", "Lokio/ByteString;", "INITIAL_MAX_FRAME_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "TYPE_DATA", "TYPE_HEADERS", "TYPE_PRIORITY", "TYPE_RST_STREAM", "TYPE_SETTINGS", "TYPE_PUSH_PROMISE", "TYPE_PING", "TYPE_GOAWAY", "TYPE_WINDOW_UPDATE", "TYPE_CONTINUATION", "FLAG_NONE", "FLAG_ACK", "FLAG_END_STREAM", "FLAG_END_HEADERS", "FLAG_END_PUSH_PROMISE", "FLAG_PADDED", "FLAG_PRIORITY", "FLAG_COMPRESSED", "FRAME_NAMES", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/String;", "FLAGS", "BINARY", "frameLog", "inbound", _UrlKt.FRAGMENT_ENCODE_SET, "streamId", "length", "type", "flags", "frameLogWindowUpdate", "windowSizeIncrement", _UrlKt.FRAGMENT_ENCODE_SET, "formattedType", "formattedType$okhttp", "formatFlags", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Http2 {
    private static final String[] BINARY = null;

    @JvmField
    public static final ByteString CONNECTION_PREFACE = null;
    private static final String[] FLAGS = null;
    public static final int FLAG_ACK = 1;
    public static final int FLAG_COMPRESSED = 32;
    public static final int FLAG_END_HEADERS = 4;
    public static final int FLAG_END_PUSH_PROMISE = 4;
    public static final int FLAG_END_STREAM = 1;
    public static final int FLAG_NONE = 0;
    public static final int FLAG_PADDED = 8;
    public static final int FLAG_PRIORITY = 32;
    private static final String[] FRAME_NAMES = null;
    public static final int INITIAL_MAX_FRAME_SIZE = 16384;
    public static final Http2 INSTANCE = null;
    public static final int TYPE_CONTINUATION = 9;
    public static final int TYPE_DATA = 0;
    public static final int TYPE_GOAWAY = 7;
    public static final int TYPE_HEADERS = 1;
    public static final int TYPE_PING = 6;
    public static final int TYPE_PRIORITY = 2;
    public static final int TYPE_PUSH_PROMISE = 5;
    public static final int TYPE_RST_STREAM = 3;
    public static final int TYPE_SETTINGS = 4;
    public static final int TYPE_WINDOW_UPDATE = 8;

    static {
        INSTANCE = new Http2();
        CONNECTION_PREFACE = ByteString.Companion.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
        FRAME_NAMES = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        FLAGS = new String[64];
        String[] r1 = new String[256];
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= 256) goto L5;
        r1[r3] = StringsKt.replace$default(_UtilJvmKt.format("%8s", new Object[]{Integer.toBinaryString(r3)}), ' ', '0', false, 4, null);
        r3 = r3 + 1;
        goto L3
    L5:
        BINARY = r1;
        String[] r0 = FLAGS;
        r0[0] = _UrlKt.FRAGMENT_ENCODE_SET;
        r0[1] = "END_STREAM";
        int[] r12 = {1};
        r0[8] = "PADDED";
        int r32 = r12[0];
        r0[r32 | 8] = r0[r32] + "|PADDED";
        r0[4] = "END_HEADERS";
        r0[32] = "PRIORITY";
        r0[36] = "END_HEADERS|PRIORITY";
        int[] r02 = {4, 32, 36};
        int r5 = 0;
    L7:
        if (r5 >= 3) goto L9;
        int r6 = r02[r5];
        int r7 = r12[0];
        String[] r8 = FLAGS;
        int r9 = r7 | r6;
        r8[r9] = r8[r7] + '|' + r8[r6];
        r8[r9 | 8] = r8[r7] + '|' + r8[r6] + "|PADDED";
        r5 = r5 + 1;
        goto L7
    L9:
        int r03 = FLAGS.length;
    L10:
        if (r2 >= r03) goto L15;
        String[] r13 = FLAGS;
        if (r13[r2] != null) goto L14;
        r13[r2] = BINARY[r2];
    L14:
        r2 = r2 + 1;
        goto L10
    }

    private Http2() {
    }

    public final String formatFlags(int r7, int r8) {
        if (r8 != 0) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (r7 == 2) goto L41;
        if (r7 == 3) goto L41;
        if (r7 == 4) goto L35;
        if (r7 == 6) goto L35;
        if (r7 == 7) goto L41;
        if (r7 == 8) goto L41;
        String[] r6 = FLAGS;
        if (r8 >= r6.length) goto L21;
        String r62 = r6[r8];
    L20:
        String r0 = r62;
        if (r7 == 5) goto L25;
    L28:
        if (r7 == 0) goto L30;
    L33:
        return r0;
    L30:
        if ((r8 & 32) == 0) goto L33;
        return StringsKt.replace$default(r0, "PRIORITY", "COMPRESSED", false, 4, null);
    L25:
        if ((r8 & 4) == 0) goto L28;
        return StringsKt.replace$default(r0, "HEADERS", "PUSH_PROMISE", false, 4, null);
    L21:
        r62 = BINARY[r8];
    L35:
        if (r8 != 1) goto L39;
        return "ACK";
    L39:
        return BINARY[r8];
    L41:
        return BINARY[r8];
    }

    public final String formattedType$okhttp(int r2) {
        String[] r1 = FRAME_NAMES;
        if (r2 >= r1.length) goto L7;
        return r1[r2];
    L7:
        return _UtilJvmKt.format("0x%02x", new Object[]{Integer.valueOf(r2)});
    }

    public final String frameLog(boolean r2, int r3, int r4, int r5, int r6) {
        String r0 = formattedType$okhttp(r5);
        String r1 = formatFlags(r5, r6);
        if (r2 == false) goto L5;
        String r22 = "<<";
    L7:
        return _UtilJvmKt.format("%s 0x%08x %5d %-13s %s", new Object[]{r22, Integer.valueOf(r3), Integer.valueOf(r4), r0, r1});
    L5:
        r22 = ">>";
        goto L7
    }

    public final String frameLogWindowUpdate(boolean r2, int r3, int r4, long r5) {
        String r1 = formattedType$okhttp(8);
        if (r2 == false) goto L5;
        String r22 = "<<";
    L7:
        return _UtilJvmKt.format("%s 0x%08x %5d %-13s %d", new Object[]{r22, Integer.valueOf(r3), Integer.valueOf(r4), r1, Long.valueOf(r5)});
    L5:
        r22 = ">>";
        goto L7
    }
}
