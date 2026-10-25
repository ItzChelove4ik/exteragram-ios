package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Assertions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public final class DataSpec {

    @Deprecated
    public final long absoluteStreamPosition;
    public final Object customData;
    public final int flags;
    public final byte[] httpBody;
    public final int httpMethod;
    public final Map<String, String> httpRequestHeaders;
    public final String key;
    public final long length;
    public final long position;
    public final Uri uri;
    public final long uriPositionOffset;

    /* renamed from: androidx.media3.datasource.DataSpec$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private Object customData;
        private int flags;
        private byte[] httpBody;
        private int httpMethod;
        private Map<String, String> httpRequestHeaders;
        private String key;
        private long length;
        private long position;
        private Uri uri;
        private long uriPositionOffset;

        public Builder() {
            this.httpMethod = 1;
            this.httpRequestHeaders = Collections.EMPTY_MAP;
            this.length = -1;
        }

        private Builder(DataSpec r3) {
            this.uri = r3.uri;
            this.uriPositionOffset = r3.uriPositionOffset;
            this.httpMethod = r3.httpMethod;
            this.httpBody = r3.httpBody;
            this.httpRequestHeaders = r3.httpRequestHeaders;
            this.position = r3.position;
            this.length = r3.length;
            this.key = r3.key;
            this.flags = r3.flags;
            this.customData = r3.customData;
        }

        public /* synthetic */ Builder(DataSpec r1, AnonymousClass1 r2) {
            this(r1);
        }

        public DataSpec build() {
            Assertions.checkStateNotNull(this.uri, "The uri must be set.");
            return new DataSpec(this.uri, this.uriPositionOffset, this.httpMethod, this.httpBody, this.httpRequestHeaders, this.position, this.length, this.key, this.flags, this.customData, null);
        }

        public Builder setFlags(int r1) {
            this.flags = r1;
            return this;
        }

        public Builder setHttpBody(byte[] r1) {
            this.httpBody = r1;
            return this;
        }

        public Builder setHttpMethod(int r1) {
            this.httpMethod = r1;
            return this;
        }

        public Builder setHttpRequestHeaders(Map<String, String> r1) {
            this.httpRequestHeaders = r1;
            return this;
        }

        public Builder setKey(String r1) {
            this.key = r1;
            return this;
        }

        public Builder setLength(long r1) {
            this.length = r1;
            return this;
        }

        public Builder setPosition(long r1) {
            this.position = r1;
            return this;
        }

        public Builder setUri(Uri r1) {
            this.uri = r1;
            return this;
        }

        public Builder setUri(String r1) {
            this.uri = Uri.parse(r1);
            return this;
        }
    }

    static {
        MediaLibraryInfo.registerModule("media3.datasource");
    }

    public DataSpec(Uri r7) {
        this(r7, 0, -1);
    }

    private DataSpec(Uri r15, long r16, int r18, byte[] r19, Map<String, String> r20, long r21, long r23, String r25, int r26, Object r27) {
        byte[] r2 = r19;
        long r7 = r16 + r21;
        boolean r12 = false;
        if (r7 < 0) goto L5;
        boolean r11 = true;
    L6:
        Assertions.checkArgument(r11);
        if (r21 < 0) goto L9;
        boolean r112 = true;
    L10:
        Assertions.checkArgument(r112);
        if (r23 <= 0) goto L13;
    L14:
        r12 = true;
    L15:
        Assertions.checkArgument(r12);
        this.uri = (Uri) Assertions.checkNotNull(r15);
        this.uriPositionOffset = r16;
        this.httpMethod = r18;
        if (r2 != null) goto L18;
    L20:
        r2 = null;
    L21:
        this.httpBody = r2;
        this.httpRequestHeaders = Collections.unmodifiableMap(new HashMap(r20));
        this.position = r21;
        this.absoluteStreamPosition = r7;
        this.length = r23;
        this.key = r25;
        this.flags = r26;
        this.customData = r27;
        return;
    L18:
        if (r2.length == 0) goto L20;
    L13:
        if (r23 != (-1)) goto L15;
    L9:
        r112 = false;
        goto L10
    L5:
        r11 = false;
        goto L6
    }

    public /* synthetic */ DataSpec(Uri r1, long r2, int r4, byte[] r5, Map r6, long r7, long r9, String r11, int r12, Object r13, AnonymousClass1 r14) {
        this(r1, r2, r4, r5, r6, r7, r9, r11, r12, r13);
    }

    public DataSpec(Uri r8, long r9, long r11) {
        this(r8, r9, r11, null);
    }

    @Deprecated
    public DataSpec(Uri r15, long r16, long r18, String r20) {
        this(r15, 0, 1, null, Collections.EMPTY_MAP, r16, r18, r20, 0, null);
    }

    public static String getStringForHttpMethod(int r1) {
        if (r1 != 1) goto L5;
        return "GET";
    L5:
        if (r1 != 2) goto L7;
        return "POST";
    L7:
        if (r1 != 3) goto L10;
        return "HEAD";
    L10:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public Builder buildUpon() {
        return new Builder(this, null);
    }

    public final String getHttpMethodString() {
        return getStringForHttpMethod(this.httpMethod);
    }

    public boolean isFlagSet(int r1) {
        if ((this.flags & r1) != r1) goto L6;
        return true;
    L6:
        return false;
    }

    public DataSpec subrange(long r6) {
        long r0 = this.length;
        long r2 = -1;
        if (r0 == (-1)) goto L7;
        r2 = r0 - r6;
    L7:
        return subrange(r6, r2);
    }

    public DataSpec subrange(long r17, long r19) {
        if (r17 != 0) goto L8;
        if (this.length != r19) goto L8;
        return this;
    L8:
        return new DataSpec(this.uri, this.uriPositionOffset, this.httpMethod, this.httpBody, this.httpRequestHeaders, this.position + r17, r19, this.key, this.flags, this.customData);
    }

    public String toString() {
        return "DataSpec[" + getHttpMethodString() + " " + this.uri + ", " + this.position + ", " + this.length + ", " + this.key + ", " + this.flags + "]";
    }
}
