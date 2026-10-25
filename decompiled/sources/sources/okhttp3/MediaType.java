package okhttp3;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB/\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u0003J\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0012J\r\u0010\u0005\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0013J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0013\u0010\u0005\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000bR\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u001b"}, d2 = {"Lokhttp3/MediaType;", _UrlKt.FRAGMENT_ENCODE_SET, "mediaType", _UrlKt.FRAGMENT_ENCODE_SET, "type", "subtype", "parameterNamesAndValues", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "getMediaType$okhttp", "()Ljava/lang/String;", "[Ljava/lang/String;", "charset", "Ljava/nio/charset/Charset;", "defaultValue", "parameter", "name", "-deprecated_type", "-deprecated_subtype", "toString", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MediaType {
    public static final Companion Companion = null;
    private static final Regex PARAMETER = null;
    private static final String QUOTED = "\"([^\"]*)\"";
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    private static final Regex TYPE_SUBTYPE = null;
    private final String mediaType;
    private final String[] parameterNamesAndValues;
    private final String subtype;
    private final String type;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\n\u001a\u00020\u000b*\u00020\u0005H\u0007¢\u0006\u0002\b\fJ\u0013\u0010\r\u001a\u0004\u0018\u00010\u000b*\u00020\u0005H\u0007¢\u0006\u0002\b\u000eJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0005H\u0007¢\u0006\u0002\b\u0010J\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u0005H\u0007¢\u0006\u0002\b\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokhttp3/MediaType$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TOKEN", _UrlKt.FRAGMENT_ENCODE_SET, "QUOTED", "TYPE_SUBTYPE", "Lkotlin/text/Regex;", "PARAMETER", "toMediaType", "Lokhttp3/MediaType;", "get", "toMediaTypeOrNull", "parse", "mediaType", "-deprecated_get", "-deprecated_parse", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nMediaType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaType.kt\nokhttp3/MediaType$Companion\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,183:1\n37#2,2:184\n*S KotlinDebug\n*F\n+ 1 MediaType.kt\nokhttp3/MediaType$Companion\n*L\n145#1:184,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaType()", imports = {"okhttp3.MediaType.Companion.toMediaType"}))
        @JvmName(name = "-deprecated_get")
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final MediaType m3679deprecated_get(String r1) {
            return get(r1);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaTypeOrNull()", imports = {"okhttp3.MediaType.Companion.toMediaTypeOrNull"}))
        @JvmName(name = "-deprecated_parse")
        /* renamed from: -deprecated_parse, reason: not valid java name */
        public final MediaType m3680deprecated_parse(String r1) {
            return parse(r1);
        }

        @JvmStatic
        @JvmName(name = "get")
        public final MediaType get(String r13) {
            MatchResult r12 = MediaType.access$getTYPE_SUBTYPE$cp().matchAt(r13, 0);
            if (r12 == null) goto L33;
            String r3 = r12.getGroupValues().get(1);
            Locale r5 = Locale.ROOT;
            String r32 = r3.toLowerCase(r5);
            String r52 = r12.getGroupValues().get(2).toLowerCase(r5);
            ArrayList r6 = new ArrayList();
            int r122 = r12.getRange().getLast();
        L5:
            int r123 = r122 + 1;
            if (r123 >= r13.length()) goto L32;
            MatchResult r8 = MediaType.access$getPARAMETER$cp().matchAt(r13, r123);
            if (r8 == null) goto L30;
            MatchGroup r124 = r8.getGroups().get(1);
            if (r124 == null) goto L12;
            String r125 = r124.getValue();
        L13:
            if (r125 == null) goto L14;
            MatchGroup r9 = r8.getGroups().get(2);
            if (r9 == null) goto L18;
            String r92 = r9.getValue();
        L19:
            if (r92 != null) goto L22;
            r92 = r8.getGroups().get(3).getValue();
        L28:
            r6.add(r125);
            r6.add(r92);
            r122 = r8.getRange().getLast();
            goto L5
        L22:
            if (StringsKt.startsWith$default(r92, '\'', false, 2, null) == false) goto L28;
            if (StringsKt.endsWith$default(r92, '\'', false, 2, null) == false) goto L28;
            if (r92.length() <= 2) goto L28;
            r92 = r92.substring(1, r92.length() - 1);
            goto L28
        L18:
            r92 = null;
            goto L19
        L14:
            r122 = r8.getRange().getLast();
            goto L5
        L12:
            r125 = null;
            goto L13
        L30:
            throw new IllegalArgumentException(("Parameter is not formatted correctly: \"" + r13.substring(r123) + "\" for: \"" + r13 + Typography.quote).toString());
        L32:
            return new MediaType(r13, r32, r52, (String[]) r6.toArray(new String[0]));
        L33:
            MediaType$Companion$$ExternalSyntheticBUOutline0.m("No subtype found for: \"", r13, 34);
            return null;
        }

        @JvmStatic
        @JvmName(name = "parse")
        public final MediaType parse(String r1) {
            return get(r1);
        L4:
            return null;
        }
    }

    static {
        Companion = new Companion(null);
        TYPE_SUBTYPE = new Regex("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
        PARAMETER = new Regex(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    }

    public MediaType(String r1, String r2, String r3, String[] r4) {
        this.mediaType = r1;
        this.type = r2;
        this.subtype = r3;
        this.parameterNamesAndValues = r4;
    }

    public static final /* synthetic */ Regex access$getPARAMETER$cp() {
        return PARAMETER;
    }

    public static final /* synthetic */ Regex access$getTYPE_SUBTYPE$cp() {
        return TYPE_SUBTYPE;
    }

    public static /* synthetic */ Charset charset$default(MediaType r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        return r0.charset(r1);
    }

    @JvmStatic
    @JvmName(name = "get")
    public static final MediaType get(String r1) {
        return Companion.get(r1);
    }

    @JvmStatic
    @JvmName(name = "parse")
    public static final MediaType parse(String r1) {
        return Companion.parse(r1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "subtype", imports = {}))
    @JvmName(name = "-deprecated_subtype")
    /* renamed from: -deprecated_subtype, reason: not valid java name */
    public final String m3677deprecated_subtype() {
        return this.subtype;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "type", imports = {}))
    @JvmName(name = "-deprecated_type")
    /* renamed from: -deprecated_type, reason: not valid java name */
    public final String m3678deprecated_type() {
        return this.type;
    }

    @JvmOverloads
    public final Charset charset() {
        return charset$default(this, null, 1, null);
    }

    @JvmOverloads
    public final Charset charset(Charset r2) {
        String r1 = parameter("charset");
        if (r1 != null) goto L8;
        return r2;
    L8:
        return Charset.forName(r1);
    L7:
        return r2;
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof MediaType) == true) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual(((MediaType) r2).mediaType, this.mediaType) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final String getMediaType$okhttp() {
        return this.mediaType;
    }

    public int hashCode() {
        return this.mediaType.hashCode();
    }

    public final String parameter(String r5) {
        int r2 = 0;
        int r0 = ProgressionUtilKt.getProgressionLastElement(0, this.parameterNamesAndValues.length - 1, 2);
        if (r0 >= 0) goto L5;
        return null;
    L5:
        if (StringsKt.equals(this.parameterNamesAndValues[r2], r5, true) == true) goto L7;
        if (r2 == r0) goto L14;
        r2 = r2 + 2;
        goto L5
    L14:
        return null;
    L7:
        return this.parameterNamesAndValues[r2 + 1];
    }

    @JvmName(name = "subtype")
    public final String subtype() {
        return this.subtype;
    }

    public String toString() {
        return this.mediaType;
    }

    @JvmName(name = "type")
    public final String type() {
        return this.type;
    }
}
