package okhttp3;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Typography;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 &2\u00020\u0001:\u0003$%&B'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\r\u001a\u00020\u0005H\u0016J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\b\u001aJ\r\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0002\b\u001bJ\r\u0010\u0013\u001a\u00020\u0014H\u0007¢\u0006\u0002\b\u001cJ\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\b\u001dJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\u001a\u0010\"\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010#\u001a\u00020\u0019H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\fR\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u00118G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015¨\u0006'"}, d2 = {"Lokhttp3/MultipartBody;", "Lokhttp3/RequestBody;", "boundaryByteString", "Lokio/ByteString;", "type", "Lokhttp3/MediaType;", "parts", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/MultipartBody$Part;", "<init>", "(Lokio/ByteString;Lokhttp3/MediaType;Ljava/util/List;)V", "()Lokhttp3/MediaType;", "()Ljava/util/List;", "contentType", "contentLength", _UrlKt.FRAGMENT_ENCODE_SET, "boundary", _UrlKt.FRAGMENT_ENCODE_SET, "()Ljava/lang/String;", "size", _UrlKt.FRAGMENT_ENCODE_SET, "()I", "part", "index", "isOneShot", _UrlKt.FRAGMENT_ENCODE_SET, "-deprecated_type", "-deprecated_boundary", "-deprecated_size", "-deprecated_parts", "writeTo", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/BufferedSink;", "writeOrCountBytes", "countBytes", "Part", "Builder", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMultipartBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartBody.kt\nokhttp3/MultipartBody\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,381:1\n1761#2,3:382\n*S KotlinDebug\n*F\n+ 1 MultipartBody.kt\nokhttp3/MultipartBody\n*L\n52#1:382,3\n*E\n"})
/* loaded from: classes5.dex */
public final class MultipartBody extends RequestBody {

    @JvmField
    public static final MediaType ALTERNATIVE = null;
    private static final byte[] COLONSPACE = null;
    private static final byte[] CRLF = null;
    public static final Companion Companion = null;
    private static final byte[] DASHDASH = null;

    @JvmField
    public static final MediaType DIGEST = null;

    @JvmField
    public static final MediaType FORM = null;

    @JvmField
    public static final MediaType MIXED = null;

    @JvmField
    public static final MediaType PARALLEL = null;
    private final ByteString boundaryByteString;
    private long contentLength;
    private final MediaType contentType;
    private final List<Part> parts;
    private final MediaType type;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\r\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003J \u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u000bJ\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0002\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lokhttp3/MultipartBody$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "boundary", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;)V", "Lokio/ByteString;", "type", "Lokhttp3/MediaType;", "parts", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/MultipartBody$Part;", "setType", "addPart", "body", "Lokhttp3/RequestBody;", "headers", "Lokhttp3/Headers;", "addFormDataPart", "name", "value", "filename", "part", "build", "Lokhttp3/MultipartBody;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nMultipartBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartBody.kt\nokhttp3/MultipartBody$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,381:1\n1#2:382\n*E\n"})
    public static final class Builder {
        private final ByteString boundary;
        private final List<Part> parts;
        private MediaType type;

        @JvmOverloads
        public Builder() {
            this(null, 1, null);
        }

        @JvmOverloads
        public Builder(String r2) {
            this.boundary = ByteString.Companion.encodeUtf8(r2);
            this.type = MultipartBody.MIXED;
            this.parts = new ArrayList();
        }

        public /* synthetic */ Builder(String r1, int r2, DefaultConstructorMarker r3) {
            if ((r2 & 1) == 0) goto L5;
            r1 = UUID.randomUUID().toString();
        L5:
            this(r1);
        }

        public final Builder addFormDataPart(String r2, String r3) {
            addPart(Part.Companion.createFormData(r2, r3));
            return this;
        }

        public final Builder addFormDataPart(String r2, String r3, RequestBody r4) {
            addPart(Part.Companion.createFormData(r2, r3, r4));
            return this;
        }

        public final Builder addPart(Headers r2, RequestBody r3) {
            addPart(Part.Companion.create(r2, r3));
            return this;
        }

        public final Builder addPart(Part r2) {
            this.parts.add(r2);
            return this;
        }

        public final Builder addPart(RequestBody r2) {
            addPart(Part.Companion.create(r2));
            return this;
        }

        public final MultipartBody build() {
            if (this.parts.isEmpty() == false) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Multipart body must have at least one part.");
            return null;
        L5:
            return new MultipartBody(this.boundary, this.type, _UtilJvmKt.toImmutableList(this.parts));
        }

        public final Builder setType(MediaType r3) {
            if (Intrinsics.areEqual(r3.type(), "multipart") == false) goto L6;
            this.type = r3;
            return this;
        L6:
            Options$Companion$$ExternalSyntheticBUOutline0.m("multipart != ", r3);
            return null;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u000e\u001a\u00020\u000f*\u00060\u0010j\u0002`\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0014R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lokhttp3/MultipartBody$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MIXED", "Lokhttp3/MediaType;", "ALTERNATIVE", "DIGEST", "PARALLEL", "FORM", "COLONSPACE", _UrlKt.FRAGMENT_ENCODE_SET, "CRLF", "DASHDASH", "appendQuotedString", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "appendQuotedString$okhttp", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final void appendQuotedString$okhttp(StringBuilder r5, String r6) {
            r5.append(Typography.quote);
            int r0 = r6.length();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L14;
            char r2 = r6.charAt(r1);
            if (r2 != '\n') goto L7;
            r5.append("%0A");
        L13:
            r1 = r1 + 1;
            goto L3
        L7:
            if (r2 == '\r') goto L11;
            if (r2 == '\"') goto L10;
            r5.append(r2);
            goto L13
        L10:
            r5.append("%22");
            goto L13
        L11:
            r5.append("%0D");
            goto L13
        L14:
            r5.append(Typography.quote);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u001b\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b\nJ\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\b\u000bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\t¨\u0006\r"}, d2 = {"Lokhttp3/MultipartBody$Part;", _UrlKt.FRAGMENT_ENCODE_SET, "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/RequestBody;", "<init>", "(Lokhttp3/Headers;Lokhttp3/RequestBody;)V", "()Lokhttp3/Headers;", "()Lokhttp3/RequestBody;", "-deprecated_headers", "-deprecated_body", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Part {
        public static final Companion Companion = null;
        private final RequestBody body;
        private final Headers headers;

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0007J\"\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\u000f"}, d2 = {"Lokhttp3/MultipartBody$Part$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Lokhttp3/MultipartBody$Part;", "body", "Lokhttp3/RequestBody;", "headers", "Lokhttp3/Headers;", "createFormData", "name", _UrlKt.FRAGMENT_ENCODE_SET, "value", "filename", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nMultipartBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartBody.kt\nokhttp3/MultipartBody$Part$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,381:1\n1#2:382\n*E\n"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            @JvmStatic
            public final Part create(Headers r2, RequestBody r3) {
                if (r2 == null) goto L5;
                String r0 = r2.get("Content-Type");
            L6:
                if (r0 != null) goto L15;
                if (r2 == null) goto L9;
                String r02 = r2.get("Content-Length");
            L10:
                if (r02 == null) goto L12;
                f$$ExternalSyntheticBUOutline1.m("Unexpected header: Content-Length");
                return null;
            L12:
                return new Part(r2, r3, null);
            L9:
                r02 = null;
                goto L10
            L15:
                f$$ExternalSyntheticBUOutline1.m("Unexpected header: Content-Type");
                return null;
            L5:
                r0 = null;
                goto L6
            }

            @JvmStatic
            public final Part create(RequestBody r2) {
                return create(null, r2);
            }

            @JvmStatic
            public final Part createFormData(String r4, String r5) {
                return createFormData(r4, null, RequestBody.Companion.create$default(RequestBody.Companion, r5, null, 1, null));
            }

            @JvmStatic
            public final Part createFormData(String r3, String r4, RequestBody r5) {
                StringBuilder r0 = new StringBuilder();
                r0.append("form-data; name=");
                Companion r1 = MultipartBody.Companion;
                r1.appendQuotedString$okhttp(r0, r3);
                if (r4 == null) goto L5;
                r0.append("; filename=");
                r1.appendQuotedString$okhttp(r0, r4);
            L5:
                String r32 = r0.toString();
                return create(new Headers.Builder().addUnsafeNonAscii("Content-Disposition", r32).build(), r5);
            }
        }

        static {
            Companion = new Companion(null);
        }

        private Part(Headers r1, RequestBody r2) {
            this.headers = r1;
            this.body = r2;
        }

        public /* synthetic */ Part(Headers r1, RequestBody r2, DefaultConstructorMarker r3) {
            this(r1, r2);
        }

        @JvmStatic
        public static final Part create(Headers r1, RequestBody r2) {
            return Companion.create(r1, r2);
        }

        @JvmStatic
        public static final Part create(RequestBody r1) {
            return Companion.create(r1);
        }

        @JvmStatic
        public static final Part createFormData(String r1, String r2) {
            return Companion.createFormData(r1, r2);
        }

        @JvmStatic
        public static final Part createFormData(String r1, String r2, RequestBody r3) {
            return Companion.createFormData(r1, r2, r3);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "body", imports = {}))
        @JvmName(name = "-deprecated_body")
        /* renamed from: -deprecated_body, reason: not valid java name */
        public final RequestBody m3685deprecated_body() {
            return this.body;
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
        @JvmName(name = "-deprecated_headers")
        /* renamed from: -deprecated_headers, reason: not valid java name */
        public final Headers m3686deprecated_headers() {
            return this.headers;
        }

        @JvmName(name = "body")
        public final RequestBody body() {
            return this.body;
        }

        @JvmName(name = "headers")
        public final Headers headers() {
            return this.headers;
        }
    }

    static {
        Companion = new Companion(null);
        MediaType.Companion r0 = MediaType.Companion;
        MIXED = r0.get("multipart/mixed");
        ALTERNATIVE = r0.get("multipart/alternative");
        DIGEST = r0.get("multipart/digest");
        PARALLEL = r0.get("multipart/parallel");
        FORM = r0.get("multipart/form-data");
        COLONSPACE = new byte[]{58, 32};
        CRLF = new byte[]{13, 10};
        DASHDASH = new byte[]{45, 45};
    }

    public MultipartBody(ByteString r1, MediaType r2, List<Part> r3) {
        this.boundaryByteString = r1;
        this.type = r2;
        this.parts = r3;
        this.contentType = MediaType.Companion.get(r2 + "; boundary=" + boundary());
        this.contentLength = -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final long writeOrCountBytes(BufferedSink r13, boolean r14) {
        if (r14 == false) goto L4;
        r13 = new Buffer();
        Buffer r0 = r13;
    L5:
        int r1 = this.parts.size();
        long r3 = 0;
        int r5 = 0;
    L6:
        if (r5 >= r1) goto L25;
        Part r6 = this.parts.get(r5);
        Headers r7 = r6.headers();
        RequestBody r62 = r6.body();
        r13.write(DASHDASH);
        r13.write(this.boundaryByteString);
        r13.write(CRLF);
        if (r7 == null) goto L12;
        int r8 = r7.size();
        int r9 = 0;
    L10:
        if (r9 >= r8) goto L12;
        r13.writeUtf8(r7.name(r9)).write(COLONSPACE).writeUtf8(r7.value(r9)).write(CRLF);
        r9 = r9 + 1;
    L12:
        MediaType r72 = r62.contentType();
        if (r72 == null) goto L15;
        r13.writeUtf8("Content-Type: ").writeUtf8(r72.toString()).write(CRLF);
    L15:
        long r73 = r62.contentLength();
        if (r73 != (-1)) goto L20;
        if (r14 == false) goto L20;
        r0.clear();
        return -1;
    L20:
        byte[] r92 = CRLF;
        r13.write(r92);
        if (r14 == false) goto L23;
        r3 = r3 + r73;
    L24:
        r13.write(r92);
        r5 = r5 + 1;
        goto L6
    L23:
        r62.writeTo(r13);
        goto L24
    L25:
        byte[] r12 = DASHDASH;
        r13.write(r12);
        r13.write(this.boundaryByteString);
        r13.write(r12);
        r13.write(CRLF);
        if (r14 == false) goto L34;
        long r32 = r3 + r0.size();
        r0.clear();
        return r32;
    L34:
        return r3;
    L4:
        r0 = 0;
        goto L5
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "boundary", imports = {}))
    @JvmName(name = "-deprecated_boundary")
    /* renamed from: -deprecated_boundary, reason: not valid java name */
    public final String m3681deprecated_boundary() {
        return boundary();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "parts", imports = {}))
    @JvmName(name = "-deprecated_parts")
    /* renamed from: -deprecated_parts, reason: not valid java name */
    public final List<Part> m3682deprecated_parts() {
        return this.parts;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    @JvmName(name = "-deprecated_size")
    /* renamed from: -deprecated_size, reason: not valid java name */
    public final int m3683deprecated_size() {
        return size();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "type", imports = {}))
    @JvmName(name = "-deprecated_type")
    /* renamed from: -deprecated_type, reason: not valid java name */
    public final MediaType m3684deprecated_type() {
        return this.type;
    }

    @JvmName(name = "boundary")
    public final String boundary() {
        return this.boundaryByteString.utf8();
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        long r0 = this.contentLength;
        if (r0 != (-1)) goto L6;
        long r02 = writeOrCountBytes(null, true);
        this.contentLength = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.contentType;
    }

    @Override // okhttp3.RequestBody
    public boolean isOneShot() {
        List<Part> r2 = this.parts;
        if ((r2 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r22 = r2.iterator();
    L9:
        if (r22.hasNext() == false) goto L14;
        if (((Part) r22.next()).body().isOneShot() == false) goto L9;
        return true;
    L14:
        return false;
    L5:
        if (r2.isEmpty() == false) goto L7;
        return false;
    }

    public final Part part(int r1) {
        return this.parts.get(r1);
    }

    @JvmName(name = "parts")
    public final List<Part> parts() {
        return this.parts;
    }

    @JvmName(name = "size")
    public final int size() {
        return this.parts.size();
    }

    @JvmName(name = "type")
    public final MediaType type() {
        return this.type;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink r2) {
        writeOrCountBytes(r2, false);
    }
}
