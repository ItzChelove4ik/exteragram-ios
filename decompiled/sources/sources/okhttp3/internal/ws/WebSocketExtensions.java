package okhttp3.internal.ws;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.url._UrlKt;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dBG\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0011J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0011J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003JN\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lokhttp3/internal/ws/WebSocketExtensions;", _UrlKt.FRAGMENT_ENCODE_SET, "perMessageDeflate", _UrlKt.FRAGMENT_ENCODE_SET, "clientMaxWindowBits", _UrlKt.FRAGMENT_ENCODE_SET, "clientNoContextTakeover", "serverMaxWindowBits", "serverNoContextTakeover", "unknownValues", "<init>", "(ZLjava/lang/Integer;ZLjava/lang/Integer;ZZ)V", "Ljava/lang/Integer;", "noContextTakeover", "clientOriginated", "component1", "component2", "()Ljava/lang/Integer;", "component3", "component4", "component5", "component6", "copy", "(ZLjava/lang/Integer;ZLjava/lang/Integer;ZZ)Lokhttp3/internal/ws/WebSocketExtensions;", "equals", "other", "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@IgnoreJRERequirement
/* loaded from: classes5.dex */
public final class WebSocketExtensions {
    public static final Companion Companion = null;
    private static final String HEADER_WEB_SOCKET_EXTENSION = "Sec-WebSocket-Extensions";

    @JvmField
    public final Integer clientMaxWindowBits;

    @JvmField
    public final boolean clientNoContextTakeover;

    @JvmField
    public final boolean perMessageDeflate;

    @JvmField
    public final Integer serverMaxWindowBits;

    @JvmField
    public final boolean serverNoContextTakeover;

    @JvmField
    public final boolean unknownValues;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lokhttp3/internal/ws/WebSocketExtensions$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "HEADER_WEB_SOCKET_EXTENSION", _UrlKt.FRAGMENT_ENCODE_SET, "parse", "Lokhttp3/internal/ws/WebSocketExtensions;", "responseHeaders", "Lokhttp3/Headers;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final WebSocketExtensions parse(Headers r21) {
            int r1 = r21.size();
            int r4 = 0;
            boolean r6 = false;
            Integer r7 = null;
            boolean r8 = false;
            Integer r9 = null;
            boolean r10 = false;
            boolean r11 = false;
        L3:
            if (r4 >= r1) goto L57;
            if (StringsKt.equals(r21.name(r4), WebSocketExtensions.HEADER_WEB_SOCKET_EXTENSION, true) == false) goto L55;
            String r14 = r21.value(r4);
            int r5 = 0;
        L9:
            if (r5 >= r14.length()) goto L55;
            int r16 = r5;
            int r52 = _UtilCommonKt.delimiterOffset$default(r14, ',', r16, 0, 4, null);
            int r2 = _UtilCommonKt.delimiterOffset(r14, ';', r16, r52);
            String r12 = _UtilCommonKt.trimSubstring(r14, r16, r2);
            int r22 = r2 + 1;
            if (StringsKt.equals(r12, "permessage-deflate", true) == true) goto L12;
            r5 = r22;
            r11 = true;
            goto L9
        L12:
            if (r6 == true) goto L28;
            r11 = true;
        L14:
            if (r22 >= r52) goto L53;
            int r3 = _UtilCommonKt.delimiterOffset(r14, ';', r22, r52);
            int r62 = _UtilCommonKt.delimiterOffset(r14, SignatureVisitor.INSTANCEOF, r22, r3);
            String r23 = _UtilCommonKt.trimSubstring(r14, r22, r62);
            if (r62 >= r3) goto L18;
            String r63 = StringsKt.removeSurrounding(_UtilCommonKt.trimSubstring(r14, r62 + 1, r3), "\"");
        L19:
            int r32 = r3 + 1;
            if (StringsKt.equals(r23, "client_max_window_bits", true) == true) goto L21;
            if (StringsKt.equals(r23, "client_no_context_takeover", true) == false) goto L38;
            if (r8 == false) goto L34;
            r11 = true;
        L34:
            if (r63 == null) goto L36;
            r11 = true;
        L36:
            r22 = r32;
            r8 = true;
            goto L14
        L38:
            if (StringsKt.equals(r23, "server_max_window_bits", true) == true) goto L39;
            if (StringsKt.equals(r23, "server_no_context_takeover", true) == false) goto L27;
            if (r10 == false) goto L50;
            r11 = true;
        L50:
            if (r63 == null) goto L52;
            r11 = true;
        L52:
            r22 = r32;
            r10 = true;
        L27:
            r22 = r32;
        L28:
            r11 = true;
            goto L14
        L39:
            if (r9 == null) goto L41;
            r11 = true;
        L41:
            if (r63 == null) goto L43;
            r9 = StringsKt.toIntOrNull(r63);
        L44:
            if (r9 == null) goto L27;
        L29:
            r22 = r32;
            goto L14
        L43:
            r9 = null;
            goto L44
        L21:
            if (r7 == null) goto L23;
            r11 = true;
        L23:
            if (r63 == null) goto L25;
            r7 = StringsKt.toIntOrNull(r63);
        L26:
            if (r7 != null) goto L29;
        L25:
            r7 = null;
            goto L26
        L18:
            r63 = null;
            goto L19
        L53:
            r5 = r22;
            r6 = true;
        L55:
            r4 = r4 + 1;
            goto L3
        L57:
            return new WebSocketExtensions(r6, r7, r8, r9, r10, r11);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public WebSocketExtensions() {
        this(false, null, false, null, false, false, 63, null);
    }

    public WebSocketExtensions(boolean r1, Integer r2, boolean r3, Integer r4, boolean r5, boolean r6) {
        this.perMessageDeflate = r1;
        this.clientMaxWindowBits = r2;
        this.clientNoContextTakeover = r3;
        this.serverMaxWindowBits = r4;
        this.serverNoContextTakeover = r5;
        this.unknownValues = r6;
    }

    public /* synthetic */ WebSocketExtensions(boolean r3, Integer r4, boolean r5, Integer r6, boolean r7, boolean r8, int r9, DefaultConstructorMarker r10) {
        if ((r9 & 1) == 0) goto L6;
        r3 = false;
    L6:
        if ((r9 & 2) == 0) goto L9;
        r4 = null;
    L9:
        if ((r9 & 4) == 0) goto L12;
        r5 = false;
    L12:
        if ((r9 & 8) == 0) goto L15;
        r6 = null;
    L15:
        if ((r9 & 16) == 0) goto L18;
        r7 = false;
    L18:
        if ((r9 & 32) == 0) goto L20;
        r8 = false;
    L20:
        this(r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ WebSocketExtensions copy$default(WebSocketExtensions r0, boolean r1, Integer r2, boolean r3, Integer r4, boolean r5, boolean r6, int r7, Object r8) {
        if ((r7 & 1) == 0) goto L6;
        r1 = r0.perMessageDeflate;
    L6:
        if ((r7 & 2) == 0) goto L9;
        r2 = r0.clientMaxWindowBits;
    L9:
        if ((r7 & 4) == 0) goto L12;
        r3 = r0.clientNoContextTakeover;
    L12:
        if ((r7 & 8) == 0) goto L15;
        r4 = r0.serverMaxWindowBits;
    L15:
        if ((r7 & 16) == 0) goto L18;
        r5 = r0.serverNoContextTakeover;
    L18:
        if ((r7 & 32) == 0) goto L20;
        r6 = r0.unknownValues;
    L20:
        boolean r72 = r5;
        boolean r82 = r6;
        boolean r52 = r3;
        Integer r62 = r4;
        return r0.copy(r1, r2, r52, r62, r72, r82);
    }

    public final boolean component1() {
        return this.perMessageDeflate;
    }

    public final Integer component2() {
        return this.clientMaxWindowBits;
    }

    public final boolean component3() {
        return this.clientNoContextTakeover;
    }

    public final Integer component4() {
        return this.serverMaxWindowBits;
    }

    public final boolean component5() {
        return this.serverNoContextTakeover;
    }

    public final boolean component6() {
        return this.unknownValues;
    }

    public final WebSocketExtensions copy(boolean r1, Integer r2, boolean r3, Integer r4, boolean r5, boolean r6) {
        return new WebSocketExtensions(r1, r2, r3, r4, r5, r6);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof WebSocketExtensions) == true) goto L8;
        return false;
    L8:
        WebSocketExtensions r52 = (WebSocketExtensions) r5;
        if (this.perMessageDeflate == r52.perMessageDeflate) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.clientMaxWindowBits, r52.clientMaxWindowBits) == true) goto L15;
        return false;
    L15:
        if (this.clientNoContextTakeover == r52.clientNoContextTakeover) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.serverMaxWindowBits, r52.serverMaxWindowBits) == true) goto L21;
        return false;
    L21:
        if (this.serverNoContextTakeover == r52.serverNoContextTakeover) goto L24;
        return false;
    L24:
        if (this.unknownValues == r52.unknownValues) goto L26;
        return false;
    L26:
        return true;
    }

    public int hashCode() {
        int r0 = Boolean.hashCode(this.perMessageDeflate) * 31;
        Integer r1 = this.clientMaxWindowBits;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        int r02 = (((r0 + r12) * 31) + Boolean.hashCode(this.clientNoContextTakeover)) * 31;
        Integer r13 = this.serverMaxWindowBits;
        if (r13 == null) goto L11;
        r2 = r13.hashCode();
    L11:
        return ((((r02 + r2) * 31) + Boolean.hashCode(this.serverNoContextTakeover)) * 31) + Boolean.hashCode(this.unknownValues);
    L5:
        r12 = r1.hashCode();
        goto L6
    }

    public final boolean noContextTakeover(boolean r1) {
        if (r1 == false) goto L6;
        return this.clientNoContextTakeover;
    L6:
        return this.serverNoContextTakeover;
    }

    public String toString() {
        return "WebSocketExtensions(perMessageDeflate=" + this.perMessageDeflate + ", clientMaxWindowBits=" + this.clientMaxWindowBits + ", clientNoContextTakeover=" + this.clientNoContextTakeover + ", serverMaxWindowBits=" + this.serverMaxWindowBits + ", serverNoContextTakeover=" + this.serverNoContextTakeover + ", unknownValues=" + this.unknownValues + ')';
    }
}
