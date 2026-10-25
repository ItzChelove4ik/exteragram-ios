package okhttp3;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import okhttp3.internal._HostnamesCommonKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okhttp3.internal.url._UrlKt;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Utf8$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\"\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u001b\u0018\u0000 K2\u00020\u0001:\u0002JKBc\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0016\u001a\u00020\u0017H\u0007¢\u0006\u0002\b\rJ\r\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\b\u001aJ\u0010\u0010#\u001a\u0004\u0018\u00010\u00032\u0006\u0010$\u001a\u00020\u0003J\u0016\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\n2\u0006\u0010$\u001a\u00020\u0003J\u000e\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\bJ\u0010\u0010+\u001a\u0004\u0018\u00010\u00032\u0006\u0010*\u001a\u00020\bJ\u0006\u0010-\u001a\u00020\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u00002\u0006\u0010/\u001a\u00020\u0003J\u0006\u00100\u001a\u000201J\u0010\u00100\u001a\u0004\u0018\u0001012\u0006\u0010/\u001a\u00020\u0003J\u0013\u00102\u001a\u00020\u00142\b\u00103\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u00104\u001a\u00020\bH\u0016J\b\u00105\u001a\u00020\u0003H\u0016J\b\u00106\u001a\u0004\u0018\u00010\u0003J\r\u0010\r\u001a\u00020\u0017H\u0007¢\u0006\u0002\b7J\r\u0010\u001a\u001a\u00020\u0019H\u0007¢\u0006\u0002\b8J\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b9J\r\u0010\u001b\u001a\u00020\u0003H\u0007¢\u0006\u0002\b:J\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b;J\r\u0010\u001c\u001a\u00020\u0003H\u0007¢\u0006\u0002\b<J\r\u0010\u0005\u001a\u00020\u0003H\u0007¢\u0006\u0002\b=J\r\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0002\b>J\r\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\b?J\r\u0010\u001d\u001a\u00020\bH\u0007¢\u0006\u0002\b@J\r\u0010\u001e\u001a\u00020\u0003H\u0007¢\u0006\u0002\bAJ\u0013\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0007¢\u0006\u0002\bBJ\u0013\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0007¢\u0006\u0002\bCJ\u000f\u0010 \u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\bDJ\u000f\u0010!\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\bEJ\r\u0010\"\u001a\u00020\bH\u0007¢\u0006\u0002\bFJ\u0013\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030&H\u0007¢\u0006\u0002\bGJ\u000f\u0010,\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\bHJ\u000f\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\bIR\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0010R\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0010R\u0013\u0010\u0005\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0010R\u0013\u0010\u0006\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u0013\u0010\u0007\u001a\u00020\b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0012R\u0018\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\f\u001a\u0004\u0018\u00010\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0010R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0011\u0010\u001b\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0010R\u0011\u0010\u001c\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0010R\u0011\u0010\u001d\u001a\u00020\b8G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0011R\u0011\u0010\u001e\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0010R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\n8G¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0012R\u0013\u0010 \u001a\u0004\u0018\u00010\u00038G¢\u0006\u0006\u001a\u0004\b \u0010\u0010R\u0013\u0010!\u001a\u0004\u0018\u00010\u00038G¢\u0006\u0006\u001a\u0004\b!\u0010\u0010R\u0011\u0010\"\u001a\u00020\b8G¢\u0006\u0006\u001a\u0004\b\"\u0010\u0011R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030&8G¢\u0006\u0006\u001a\u0004\b%\u0010'R\u0013\u0010,\u001a\u0004\u0018\u00010\u00038G¢\u0006\u0006\u001a\u0004\b,\u0010\u0010¨\u0006L"}, d2 = {"Lokhttp3/HttpUrl;", _UrlKt.FRAGMENT_ENCODE_SET, "scheme", _UrlKt.FRAGMENT_ENCODE_SET, "username", "password", "host", "port", _UrlKt.FRAGMENT_ENCODE_SET, "pathSegments", _UrlKt.FRAGMENT_ENCODE_SET, "queryNamesAndValues", "fragment", "url", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "()Ljava/lang/String;", "()I", "()Ljava/util/List;", "isHttps", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "toUrl", "Ljava/net/URL;", "toUri", "Ljava/net/URI;", "uri", "encodedUsername", "encodedPassword", "pathSize", "encodedPath", "encodedPathSegments", "encodedQuery", "query", "querySize", "queryParameter", "name", "queryParameterNames", _UrlKt.FRAGMENT_ENCODE_SET, "()Ljava/util/Set;", "queryParameterValues", "queryParameterName", "index", "queryParameterValue", "encodedFragment", "redact", "resolve", "link", "newBuilder", "Lokhttp3/HttpUrl$Builder;", "equals", "other", "hashCode", "toString", "topPrivateDomain", "-deprecated_url", "-deprecated_uri", "-deprecated_scheme", "-deprecated_encodedUsername", "-deprecated_username", "-deprecated_encodedPassword", "-deprecated_password", "-deprecated_host", "-deprecated_port", "-deprecated_pathSize", "-deprecated_encodedPath", "-deprecated_encodedPathSegments", "-deprecated_pathSegments", "-deprecated_encodedQuery", "-deprecated_query", "-deprecated_querySize", "-deprecated_queryParameterNames", "-deprecated_encodedFragment", "-deprecated_fragment", "Builder", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHttpUrl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpUrl.kt\nokhttp3/HttpUrl\n+ 2 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n*L\n1#1,1810:1\n246#2:1811\n242#2:1812\n*S KotlinDebug\n*F\n+ 1 HttpUrl.kt\nokhttp3/HttpUrl\n*L\n624#1:1811\n648#1:1812\n*E\n"})
/* loaded from: classes.dex */
public final class HttpUrl {
    public static final Companion Companion = null;
    private final String fragment;
    private final String host;
    private final String password;
    private final List<String> pathSegments;
    private final int port;
    private final List<String> queryNamesAndValues;
    private final String scheme;
    private final String url;
    private final String username;

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010$\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0005J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010%\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u0005J\u000e\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u0005J\u000e\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020\u0005J\u000e\u0010,\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0005J\u0018\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u00052\u0006\u0010-\u001a\u00020.H\u0002J\u0016\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u0005J\u0016\u00101\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u0005J\u000e\u00102\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u0014J\u000e\u00103\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u0005J\u0010\u00104\u001a\u00020\u00002\b\u00104\u001a\u0004\u0018\u00010\u0005J\u0010\u00105\u001a\u00020\u00002\b\u00105\u001a\u0004\u0018\u00010\u0005J\u0018\u00106\u001a\u00020\u00002\u0006\u00107\u001a\u00020\u00052\b\u00108\u001a\u0004\u0018\u00010\u0005J\u0018\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u00052\b\u0010;\u001a\u0004\u0018\u00010\u0005J\u0018\u0010<\u001a\u00020\u00002\u0006\u00107\u001a\u00020\u00052\b\u00108\u001a\u0004\u0018\u00010\u0005J\u0018\u0010=\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u00052\b\u0010;\u001a\u0004\u0018\u00010\u0005J\u000e\u0010>\u001a\u00020\u00002\u0006\u00107\u001a\u00020\u0005J\u000e\u0010?\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u0005J\u0010\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0005H\u0002J\u0010\u0010C\u001a\u00020\u00002\b\u0010C\u001a\u0004\u0018\u00010\u0005J\u0010\u0010!\u001a\u00020\u00002\b\u0010!\u001a\u0004\u0018\u00010\u0005J\r\u0010D\u001a\u00020\u0000H\u0000¢\u0006\u0002\bEJ\u0006\u0010F\u001a\u00020GJ\b\u0010H\u001a\u00020\u0014H\u0002J\b\u0010I\u001a\u00020\u0005H\u0016J\u001e\u0010J\u001a\u00020A*\b\u0012\u0004\u0012\u00020\u00050K2\n\u0010L\u001a\u00060Mj\u0002`NH\u0002J\u001f\u0010O\u001a\u00020\u00002\b\u0010P\u001a\u0004\u0018\u00010G2\u0006\u0010Q\u001a\u00020\u0005H\u0000¢\u0006\u0002\bRJ \u0010S\u001a\u00020A2\u0006\u0010Q\u001a\u00020\u00052\u0006\u0010T\u001a\u00020\u00142\u0006\u0010U\u001a\u00020\u0014H\u0002J0\u0010V\u001a\u00020A2\u0006\u0010Q\u001a\u00020\u00052\u0006\u0010W\u001a\u00020\u00142\u0006\u0010U\u001a\u00020\u00142\u0006\u0010X\u001a\u00020.2\u0006\u0010-\u001a\u00020.H\u0002J\b\u0010Y\u001a\u00020AH\u0002J\u0010\u0010Z\u001a\u00020.2\u0006\u0010Q\u001a\u00020\u0005H\u0002J\u0010\u0010[\u001a\u00020.2\u0006\u0010Q\u001a\u00020\u0005H\u0002J\u0014\u0010\\\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u001a*\u00020\u0005H\u0002J \u0010]\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\u00052\u0006\u0010W\u001a\u00020\u00142\u0006\u0010U\u001a\u00020\u0014H\u0002J\u001c\u0010^\u001a\u00020\u0014*\u00020\u00052\u0006\u0010W\u001a\u00020\u00142\u0006\u0010U\u001a\u00020\u0014H\u0002J \u0010_\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\u00052\u0006\u0010W\u001a\u00020\u00142\u0006\u0010U\u001a\u00020\u0014H\u0002J \u0010`\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\u00052\u0006\u0010W\u001a\u00020\u00142\u0006\u0010U\u001a\u00020\u0014H\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001c\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0007\"\u0004\b#\u0010\t¨\u0006a"}, d2 = {"Lokhttp3/HttpUrl$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "scheme", _UrlKt.FRAGMENT_ENCODE_SET, "getScheme$okhttp", "()Ljava/lang/String;", "setScheme$okhttp", "(Ljava/lang/String;)V", "encodedUsername", "getEncodedUsername$okhttp", "setEncodedUsername$okhttp", "encodedPassword", "getEncodedPassword$okhttp", "setEncodedPassword$okhttp", "host", "getHost$okhttp", "setHost$okhttp", "port", _UrlKt.FRAGMENT_ENCODE_SET, "getPort$okhttp", "()I", "setPort$okhttp", "(I)V", "encodedPathSegments", _UrlKt.FRAGMENT_ENCODE_SET, "getEncodedPathSegments$okhttp", "()Ljava/util/List;", "encodedQueryNamesAndValues", "getEncodedQueryNamesAndValues$okhttp", "setEncodedQueryNamesAndValues$okhttp", "(Ljava/util/List;)V", "encodedFragment", "getEncodedFragment$okhttp", "setEncodedFragment$okhttp", "username", "password", "addPathSegment", "pathSegment", "addPathSegments", "pathSegments", "addEncodedPathSegment", "encodedPathSegment", "addEncodedPathSegments", "alreadyEncoded", _UrlKt.FRAGMENT_ENCODE_SET, "setPathSegment", "index", "setEncodedPathSegment", "removePathSegment", "encodedPath", "query", "encodedQuery", "addQueryParameter", "name", "value", "addEncodedQueryParameter", "encodedName", "encodedValue", "setQueryParameter", "setEncodedQueryParameter", "removeAllQueryParameters", "removeAllEncodedQueryParameters", "removeAllCanonicalQueryParameters", _UrlKt.FRAGMENT_ENCODE_SET, "canonicalName", "fragment", "reencodeForUri", "reencodeForUri$okhttp", "build", "Lokhttp3/HttpUrl;", "effectivePort", "toString", "toPathString", _UrlKt.FRAGMENT_ENCODE_SET, "out", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "parse", "base", PluginsConstants.Settings.TYPE_INPUT, "parse$okhttp", "resolvePath", "startPos", "limit", "push", "pos", "addTrailingSlash", "pop", "isDot", "isDotDot", "toQueryNamesAndValues", "schemeDelimiterOffset", "slashCount", "portColonOffset", "parsePort", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHttpUrl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpUrl.kt\nokhttp3/HttpUrl$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1810:1\n1#2:1811\n1563#3:1812\n1634#3,3:1813\n1563#3:1816\n1634#3,3:1817\n*S KotlinDebug\n*F\n+ 1 HttpUrl.kt\nokhttp3/HttpUrl$Builder\n*L\n1265#1:1812\n1265#1:1813,3\n1266#1:1816\n1266#1:1817,3\n*E\n"})
    public static final class Builder {
        private String encodedFragment;
        private String encodedPassword;
        private final List<String> encodedPathSegments;
        private List<String> encodedQueryNamesAndValues;
        private String encodedUsername;
        private String host;
        private int port;
        private String scheme;

        public Builder() {
            this.encodedUsername = _UrlKt.FRAGMENT_ENCODE_SET;
            this.encodedPassword = _UrlKt.FRAGMENT_ENCODE_SET;
            this.port = -1;
            this.encodedPathSegments = CollectionsKt.mutableListOf(new String[]{_UrlKt.FRAGMENT_ENCODE_SET});
        }

        private final Builder addPathSegments(String r8, boolean r9) {
            int r3 = 0;
        L3:
            int r4 = _UtilCommonKt.delimiterOffset(r8, "/\\", r3, r8.length());
            if (r4 >= r8.length()) goto L6;
            boolean r5 = true;
            String r2 = r8;
            boolean r6 = r9;
            Builder r1 = this;
        L7:
            r1.push(r2, r3, r4, r5, r6);
            r3 = r4 + 1;
            if (r3 > r2.length()) goto L9;
            Builder this = r1;
            r8 = r2;
            r9 = r6;
            goto L3
        L9:
            return r1;
        L6:
            r5 = false;
            r1 = this;
            r2 = r8;
            r6 = r9;
            goto L7
        }

        private final int effectivePort() {
            int r0 = this.port;
            if (r0 == (-1)) goto L6;
            return r0;
        L6:
            return HttpUrl.Companion.defaultPort(this.scheme);
        }

        private final boolean isDot(String r2) {
            if (Intrinsics.areEqual(r2, ".") == false) goto L5;
        L9:
            return true;
        L5:
            if (StringsKt.equals(r2, "%2e", true) == true) goto L9;
            return false;
        }

        private final boolean isDotDot(String r2) {
            if (Intrinsics.areEqual(r2, "..") == false) goto L5;
        L13:
            return true;
        L5:
            if (StringsKt.equals(r2, "%2e.", true) == true) goto L13;
            if (StringsKt.equals(r2, ".%2e", true) == true) goto L13;
            if (StringsKt.equals(r2, "%2e%2e", true) == true) goto L13;
            return false;
        }

        private final int parsePort(String r11, int r12, int r13) {
            int r112 = Integer.parseInt(_UrlKt.canonicalize$default(r11, r12, r13, _UrlKt.FRAGMENT_ENCODE_SET, false, false, false, false, 120, null));     // Catch: NumberFormatException -> L10
            if (1 > r112) goto L9;
            if (r112 >= 65536) goto L9;
            return r112;
        L9:
            return -1;
        }

        private final void pop() {
            if (this.encodedPathSegments.remove(r0.size() - 1).length() == 0) goto L5;
        L8:
            this.encodedPathSegments.add(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L5:
            if (this.encodedPathSegments.isEmpty() == true) goto L8;
            this.encodedPathSegments.set(r2.size() - 1, _UrlKt.FRAGMENT_ENCODE_SET);
        }

        private final int portColonOffset(String r2, int r3, int r4) {
        L2:
            if (r3 >= r4) goto L14;
            char r1 = r2.charAt(r3);
            if (r1 == ':') goto L13;
            if (r1 != '[') goto L12;
        L8:
            r3 = r3 + 1;
            if (r3 >= r4) goto L12;
            if (r2.charAt(r3) != ']') goto L8;
        L12:
            r3 = r3 + 1;
            goto L2
        L13:
            return r3;
        L14:
            return r4;
        }

        private final void push(String r11, int r12, int r13, boolean r14, boolean r15) {
            String r112 = _UrlKt.canonicalize$default(r11, r12, r13, _UrlKt.PATH_SEGMENT_ENCODE_SET, r15, false, false, false, 112, null);
            if (isDot(r112) == false) goto L6;
            return;
        L6:
            if (isDotDot(r112) == false) goto L9;
            pop();
            return;
        L9:
            int r122 = this.encodedPathSegments.get(r12.size() - 1).length();
            List<String> r132 = this.encodedPathSegments;
            if (r122 != 0) goto L12;
            r132.set(r132.size() - 1, r112);
        L13:
            if (r14 == false) goto L16;
            this.encodedPathSegments.add(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L16:
            return;
        L12:
            r132.add(r112);
            goto L13
        }

        private final void removeAllCanonicalQueryParameters(String r5) {
            int r0 = this.encodedQueryNamesAndValues.size() - 2;
            int r1 = ProgressionUtilKt.getProgressionLastElement(r0, 0, -2);
            if (r1 <= r0) goto L5;
            return;
        L5:
            if (Intrinsics.areEqual(r5, this.encodedQueryNamesAndValues.get(r0)) == false) goto L10;
            this.encodedQueryNamesAndValues.remove(r0 + 1);
            this.encodedQueryNamesAndValues.remove(r0);
            if (this.encodedQueryNamesAndValues.isEmpty() == false) goto L10;
            this.encodedQueryNamesAndValues = null;
            return;
        L10:
            if (r0 == r1) goto L15;
            r0 = r0 - 2;
            goto L5
        }

        private final void resolvePath(String r11, int r12, int r13) {
            if (r12 == r13) goto L20;
            char r0 = r11.charAt(r12);
            if (r0 != '/') goto L7;
        L9:
            this.encodedPathSegments.clear();
            this.encodedPathSegments.add(_UrlKt.FRAGMENT_ENCODE_SET);
            r12 = r12 + 1;
        L10:
            int r6 = r12;
        L11:
            if (r6 >= r13) goto L27;
            int r7 = _UtilCommonKt.delimiterOffset(r11, "/\\", r6, r13);
            if (r7 >= r13) goto L15;
            boolean r8 = true;
        L16:
            Builder r4 = this;
            String r5 = r11;
            r4.push(r5, r6, r7, r8, true);
            if (r8 == true) goto L18;
            Builder this = r4;
            r11 = r5;
            r6 = r7;
            goto L11
        L18:
            r6 = r7 + 1;
            this = r4;
            r11 = r5;
            goto L11
        L15:
            r8 = false;
            goto L16
        L27:
            return;
        L7:
            if (r0 == '\\') goto L9;
            List<String> r02 = this.encodedPathSegments;
            r02.set(r02.size() - 1, _UrlKt.FRAGMENT_ENCODE_SET);
            goto L10
        }

        private final int schemeDelimiterOffset(String r6, int r7, int r8) {
            if ((r8 - r7) >= 2) goto L5;
            return -1;
        L5:
            char r5 = r6.charAt(r7);
            if (Intrinsics.compare(r5, 97) < 0) goto L10;
            if (Intrinsics.compare(r5, 122) > 0) goto L10;
        L14:
            r7 = r7 + 1;
            if (r7 >= r8) goto L38;
            char r52 = r6.charAt(r7);
            if ('a' > r52) goto L21;
            if (r52 < '{') goto L14;
        L21:
            if ('A' > r52) goto L26;
            if (r52 < '[') goto L14;
        L26:
            if ('0' > r52) goto L30;
            if (r52 < ':') goto L14;
        L30:
            if (r52 == '+') goto L14;
            if (r52 == '-') goto L14;
            if (r52 == '.') goto L14;
            if (r52 != ':') goto L38;
            return r7;
        L38:
            return -1;
        L10:
            if (Intrinsics.compare(r5, 65) < 0) goto L38;
            if (Intrinsics.compare(r5, 90) <= 0) goto L14;
            goto L38
        }

        private final int slashCount(String r3, int r4, int r5) {
            int r2 = 0;
        L3:
            if (r4 >= r5) goto L10;
            char r0 = r3.charAt(r4);
            if (r0 == '/') goto L9;
            if (r0 != '\\') goto L10;
        L9:
            r2 = r2 + 1;
            r4 = r4 + 1;
        L10:
            return r2;
        }

        private final void toPathString(List<String> r3, StringBuilder r4) {
            int r2 = r3.size();
            int r0 = 0;
        L3:
            if (r0 >= r2) goto L5;
            r4.append('/');
            r4.append(r3.get(r0));
            r0 = r0 + 1;
            goto L3
        }

        private final List<String> toQueryNamesAndValues(String r8) {
            ArrayList r7 = new ArrayList();
            int r3 = 0;
        L4:
            if (r3 > r8.length()) goto L15;
            String r1 = r8;
            int r82 = StringsKt.indexOf$default(r1, Typography.amp, r3, false, 4, null);
            if (r82 != (-1)) goto L8;
            r82 = r1.length();
        L8:
            int r2 = StringsKt.indexOf$default(r1, SignatureVisitor.INSTANCEOF, r3, false, 4, null);
            if (r2 == (-1)) goto L13;
            if (r2 > r82) goto L13;
            r7.add(r1.substring(r3, r2));
            r7.add(r1.substring(r2 + 1, r82));
        L14:
            r3 = r82 + 1;
            r8 = r1;
        L13:
            r7.add(r1.substring(r3, r82));
            r7.add(null);
            goto L14
        L15:
            return r7;
        }

        public final Builder addEncodedPathSegment(String r7) {
            push(r7, 0, r7.length(), false, true);
            return this;
        }

        public final Builder addEncodedPathSegments(String r2) {
            return addPathSegments(r2, true);
        }

        public final Builder addEncodedQueryParameter(String r12, String r13) {
            if (this.encodedQueryNamesAndValues != null) goto L5;
            this.encodedQueryNamesAndValues = new ArrayList();
        L5:
            this.encodedQueryNamesAndValues.add(_UrlKt.canonicalize$default(r12, 0, 0, _UrlKt.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, 83, null));
            List<String> r122 = this.encodedQueryNamesAndValues;
            if (r13 == null) goto L8;
            String r132 = _UrlKt.canonicalize$default(r13, 0, 0, _UrlKt.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, 83, null);
        L9:
            r122.add(r132);
            return this;
        L8:
            r132 = null;
            goto L9
        }

        public final Builder addPathSegment(String r7) {
            push(r7, 0, r7.length(), false, false);
            return this;
        }

        public final Builder addPathSegments(String r2) {
            return addPathSegments(r2, false);
        }

        public final Builder addQueryParameter(String r12, String r13) {
            if (this.encodedQueryNamesAndValues != null) goto L5;
            this.encodedQueryNamesAndValues = new ArrayList();
        L5:
            this.encodedQueryNamesAndValues.add(_UrlKt.canonicalize$default(r12, 0, 0, _UrlKt.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, 91, null));
            List<String> r122 = this.encodedQueryNamesAndValues;
            if (r13 == null) goto L8;
            String r132 = _UrlKt.canonicalize$default(r13, 0, 0, _UrlKt.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, 91, null);
        L9:
            r122.add(r132);
            return this;
        L8:
            r132 = null;
            goto L9
        }

        public final HttpUrl build() {
            String r1 = this.scheme;
            if (r1 == null) goto L29;
            String r3 = _UrlKt.percentDecode$default(this.encodedUsername, 0, 0, false, 7, null);
            String r4 = _UrlKt.percentDecode$default(this.encodedPassword, 0, 0, false, 7, null);
            String r42 = this.host;
            if (r42 == null) goto L27;
            int r5 = effectivePort();
            List<String> r7 = this.encodedPathSegments;
            String r8 = null;
            ArrayList r6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r7, 10));
            Iterator<T> r72 = r7.iterator();
        L8:
            if (r72.hasNext() == false) goto L10;
            r6.add(_UrlKt.percentDecode$default((String) r72.next(), 0, 0, false, 7, null));
            goto L8
        L10:
            List<String> r73 = this.encodedQueryNamesAndValues;
            if (r73 == null) goto L21;
            List<String> r74 = r73;
            ArrayList r10 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r74, 10));
            Iterator<T> r75 = r74.iterator();
        L14:
            if (r75.hasNext() == false) goto L20;
            String r11 = (String) r75.next();
            if (r11 == null) goto L18;
            String r9 = _UrlKt.percentDecode$default(r11, 0, 0, true, 3, null);
        L19:
            r10.add(r9);
            goto L14
        L18:
            r9 = null;
            goto L19
        L20:
            ArrayList r76 = r10;
        L22:
            String r92 = this.encodedFragment;
            if (r92 == null) goto L26;
            r8 = _UrlKt.percentDecode$default(r92, 0, 0, false, 7, null);
        L26:
            return new HttpUrl(r1, r3, r4, r42, r5, r6, r76, r8, toString(), null);
        L21:
            r76 = null;
            goto L22
        L27:
            Segment$$ExternalSyntheticBUOutline1.m("host == null");
            return null;
        L29:
            Segment$$ExternalSyntheticBUOutline1.m("scheme == null");
            return null;
        }

        public final Builder encodedFragment(String r11) {
            if (r11 == null) goto L4;
            String r112 = _UrlKt.canonicalize$default(r11, 0, 0, _UrlKt.FRAGMENT_ENCODE_SET, true, false, false, true, 51, null);
        L5:
            this.encodedFragment = r112;
            return this;
        L4:
            r112 = null;
            goto L5
        }

        public final Builder encodedPassword(String r11) {
            this.encodedPassword = _UrlKt.canonicalize$default(r11, 0, 0, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, 115, null);
            return this;
        }

        public final Builder encodedPath(String r5) {
            if (StringsKt.startsWith$default(r5, "/", false, 2, null) == false) goto L6;
            resolvePath(r5, 0, r5.length());
            return this;
        L6:
            Options$Companion$$ExternalSyntheticBUOutline0.m("unexpected encodedPath: ", r5);
            return null;
        }

        public final Builder encodedQuery(String r11) {
            if (r11 == null) goto L6;
            String r112 = _UrlKt.canonicalize$default(r11, 0, 0, _UrlKt.QUERY_ENCODE_SET, true, false, true, false, 83, null);
            if (r112 == null) goto L6;
            List<String> r113 = toQueryNamesAndValues(r112);
        L7:
            this.encodedQueryNamesAndValues = r113;
            return this;
        L6:
            r113 = null;
            goto L7
        }

        public final Builder encodedUsername(String r11) {
            this.encodedUsername = _UrlKt.canonicalize$default(r11, 0, 0, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, 115, null);
            return this;
        }

        public final Builder fragment(String r11) {
            if (r11 == null) goto L4;
            String r112 = _UrlKt.canonicalize$default(r11, 0, 0, _UrlKt.FRAGMENT_ENCODE_SET, false, false, false, true, 59, null);
        L5:
            this.encodedFragment = r112;
            return this;
        L4:
            r112 = null;
            goto L5
        }

        public final String getEncodedFragment$okhttp() {
            return this.encodedFragment;
        }

        public final String getEncodedPassword$okhttp() {
            return this.encodedPassword;
        }

        public final List<String> getEncodedPathSegments$okhttp() {
            return this.encodedPathSegments;
        }

        public final List<String> getEncodedQueryNamesAndValues$okhttp() {
            return this.encodedQueryNamesAndValues;
        }

        public final String getEncodedUsername$okhttp() {
            return this.encodedUsername;
        }

        public final String getHost$okhttp() {
            return this.host;
        }

        public final int getPort$okhttp() {
            return this.port;
        }

        public final String getScheme$okhttp() {
            return this.scheme;
        }

        public final Builder host(String r7) {
            String r72 = _HostnamesCommonKt.toCanonicalHost(_UrlKt.percentDecode$default(r7, 0, 0, false, 7, null));
            if (r72 == null) goto L6;
            this.host = r72;
            return this;
        L6:
            Native$$ExternalSyntheticBUOutline5.m("unexpected host: ", r7);
            return null;
        }

        public final Builder parse$okhttp(HttpUrl r23, String r24) {
            String r1 = r24;
            Builder r11 = null;
            int r3 = _UtilCommonKt.indexOfFirstNonAsciiWhitespace$default(r1, 0, 0, 3, null);
            int r12 = _UtilCommonKt.indexOfLastNonAsciiWhitespace$default(r1, r3, 0, 2, null);
            int r5 = schemeDelimiterOffset(r1, r3, r12);
            boolean r13 = true;
            if (r5 != (-1)) goto L5;
            if (r23 == null) goto L78;
            this.scheme = r23.scheme();
        L14:
            int r52 = slashCount(r1, r3, r12);
            char r6 = '#';
            if (r52 >= 2) goto L26;
            if (r23 == null) goto L26;
            if (Intrinsics.areEqual(r23.scheme(), this.scheme) == false) goto L26;
            this.encodedUsername = r23.encodedUsername();
            this.encodedPassword = r23.encodedPassword();
            this.host = r23.host();
            this.port = r23.port();
            this.encodedPathSegments.clear();
            this.encodedPathSegments.addAll(r23.encodedPathSegments());
            if (r3 != r12) goto L23;
        L24:
            encodedQuery(r23.encodedQuery());
        L64:
            int r2 = _UtilCommonKt.delimiterOffset(r1, "?#", r3, r12);
            resolvePath(r1, r3, r2);
            if (r2 < r12) goto L67;
        L69:
            char r112 = '#';
        L70:
            if (r2 < r12) goto L72;
        L74:
            return this;
        L72:
            if (r1.charAt(r2) != r112) goto L74;
            this.encodedFragment = _UrlKt.canonicalize$default(r1, r2 + 1, r12, _UrlKt.FRAGMENT_ENCODE_SET, true, false, false, true, 48, null);
            goto L74
        L67:
            if (r1.charAt(r2) != '?') goto L69;
            r112 = '#';
            int r32 = _UtilCommonKt.delimiterOffset(r1, '#', r2, r12);
            this.encodedQueryNamesAndValues = toQueryNamesAndValues(_UrlKt.canonicalize$default(r1, r2 + 1, r32, _UrlKt.QUERY_ENCODE_SET, true, false, true, false, 80, null));
            r2 = r32;
            goto L70
        L23:
            if (r1.charAt(r3) != '#') goto L64;
        L26:
            boolean r16 = false;
            boolean r17 = false;
            int r22 = r3 + r52;
        L27:
            int r33 = _UtilCommonKt.delimiterOffset(r1, "@/\\?#", r22, r12);
            if (r33 == r12) goto L30;
            char r4 = r1.charAt(r33);
        L31:
            if (r4 == 65535) goto L53;
            if (r4 == r6) goto L53;
            if (r4 == '/') goto L53;
            if (r4 == '\\') goto L53;
            if (r4 == '?') goto L53;
            if (r4 != '@') goto L27;
            if (r16 == true) goto L51;
            int r53 = _UtilCommonKt.delimiterOffset(r1, ':', r22, r33);
            Builder r21 = r11;
            boolean r18 = r13;
            String r25 = _UrlKt.canonicalize$default(r1, r22, r53, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, 112, null);
            if (r17 == false) goto L46;
            r25 = this.encodedUsername + "%40" + r25;
        L46:
            this.encodedUsername = r25;
            if (r53 == r33) goto L49;
            int r34 = r33;
            this.encodedPassword = _UrlKt.canonicalize$default(r24, r53 + 1, r34, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, 112, null);
            r16 = r18;
        L50:
            r1 = r24;
            int r8 = r34;
            r17 = r18;
        L52:
            r22 = r8 + 1;
            r13 = r18;
            r11 = r21;
            r6 = '#';
            goto L27
        L49:
            r34 = r33;
            goto L50
        L51:
            r21 = r11;
            r18 = r13;
            StringBuilder r113 = new StringBuilder();
            r113.append(this.encodedPassword);
            r113.append("%40");
            r1 = r24;
            r8 = r33;
            r113.append(_UrlKt.canonicalize$default(r1, r22, r33, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, 112, null));
            this.encodedPassword = r113.toString();
        L53:
            Builder r212 = r11;
            int r35 = portColonOffset(r1, r22, r33);
            int r7 = r35 + 1;
            if (r7 >= r33) goto L60;
            this.host = _HostnamesCommonKt.toCanonicalHost(_UrlKt.percentDecode$default(r1, r22, r35, false, 4, null));
            int r42 = parsePort(r1, r7, r33);
            this.port = r42;
            if (r42 != (-1)) goto L62;
            HttpUrl$Builder$$ExternalSyntheticBUOutline0.m("Invalid URL port: \"", r1.substring(r7, r33), 34);
            return r212;
        L62:
            if (this.host == null) goto L75;
            r3 = r33;
            goto L64
        L75:
            HttpUrl$Builder$$ExternalSyntheticBUOutline0.m("Invalid URL host: \"", r1.substring(r22, r35), 34);
            return r212;
        L60:
            this.host = _HostnamesCommonKt.toCanonicalHost(_UrlKt.percentDecode$default(r1, r22, r35, false, 4, null));
            this.port = HttpUrl.Companion.defaultPort(this.scheme);
            goto L62
        L30:
            r4 = 65535;
            goto L31
        L78:
            if (r1.length() <= 6) goto L80;
            String r0 = StringsKt.take(r1, 6) + "...";
        L81:
            f$$ExternalSyntheticBUOutline1.m("Expected URL scheme 'http' or 'https' but no scheme was found for ".concat(r0));
            return null;
        L80:
            r0 = r1;
            goto L81
        L5:
            if (StringsKt.startsWith(r1, "https:", r3, true) == false) goto L8;
            this.scheme = "https";
            r3 = r3 + 6;
            goto L14
        L8:
            if (StringsKt.startsWith(r1, "http:", r3, true) == false) goto L11;
            this.scheme = "http";
            r3 = r3 + 5;
            goto L14
        L11:
            throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but was '" + r1.substring(0, r5) + '\'');
        }

        public final Builder password(String r11) {
            this.encodedPassword = _UrlKt.canonicalize$default(r11, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, 123, null);
            return this;
        }

        public final Builder port(int r2) {
            if (1 <= r2) goto L5;
        L8:
            Utf8$$ExternalSyntheticBUOutline1.m("unexpected port: ", r2);
            return null;
        L5:
            if (r2 >= 65536) goto L8;
            this.port = r2;
            return this;
        }

        public final Builder query(String r11) {
            if (r11 == null) goto L6;
            String r112 = _UrlKt.canonicalize$default(r11, 0, 0, _UrlKt.QUERY_ENCODE_SET, false, false, true, false, 91, null);
            if (r112 == null) goto L6;
            List<String> r113 = toQueryNamesAndValues(r112);
        L7:
            this.encodedQueryNamesAndValues = r113;
            return this;
        L6:
            r113 = null;
            goto L7
        }

        public final Builder reencodeForUri$okhttp() {
            String r1 = this.host;
            String r2 = null;
            if (r1 == null) goto L5;
            String r12 = new Regex("[\"<>^`{|}]").replace(r1, _UrlKt.FRAGMENT_ENCODE_SET);
        L6:
            this.host = r12;
            int r13 = this.encodedPathSegments.size();
            int r3 = 0;
            int r4 = 0;
        L7:
            if (r4 >= r13) goto L9;
            List<String> r5 = this.encodedPathSegments;
            r5.set(r4, _UrlKt.canonicalize$default(r5.get(r4), 0, 0, _UrlKt.PATH_SEGMENT_ENCODE_SET_URI, true, true, false, false, 99, null));
            r4 = r4 + 1;
            goto L7
        L9:
            List<String> r14 = this.encodedQueryNamesAndValues;
            if (r14 == null) goto L18;
            int r42 = r14.size();
        L12:
            if (r3 >= r42) goto L18;
            String r6 = r14.get(r3);
            if (r6 == null) goto L16;
            String r52 = _UrlKt.canonicalize$default(r6, 0, 0, _UrlKt.QUERY_COMPONENT_ENCODE_SET_URI, true, true, true, false, 67, null);
        L17:
            r14.set(r3, r52);
            r3 = r3 + 1;
            goto L12
        L16:
            r52 = null;
        L18:
            String r53 = this.encodedFragment;
            if (r53 == null) goto L21;
            r2 = _UrlKt.canonicalize$default(r53, 0, 0, _UrlKt.FRAGMENT_ENCODE_SET_URI, true, true, false, true, 35, null);
        L21:
            this.encodedFragment = r2;
            return this;
        L5:
            r12 = null;
            goto L6
        }

        public final Builder removeAllEncodedQueryParameters(String r12) {
            if (this.encodedQueryNamesAndValues != null) goto L5;
            return this;
        L5:
            removeAllCanonicalQueryParameters(_UrlKt.canonicalize$default(r12, 0, 0, _UrlKt.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, 83, null));
            return this;
        }

        public final Builder removeAllQueryParameters(String r12) {
            if (this.encodedQueryNamesAndValues != null) goto L5;
            return this;
        L5:
            removeAllCanonicalQueryParameters(_UrlKt.canonicalize$default(r12, 0, 0, _UrlKt.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, 91, null));
            return this;
        }

        public final Builder removePathSegment(int r2) {
            this.encodedPathSegments.remove(r2);
            if (this.encodedPathSegments.isEmpty() == false) goto L5;
            this.encodedPathSegments.add(_UrlKt.FRAGMENT_ENCODE_SET);
        L5:
            return this;
        }

        public final Builder scheme(String r4) {
            if (StringsKt.equals(r4, "http", true) == false) goto L7;
            this.scheme = "http";
            return this;
        L7:
            if (StringsKt.equals(r4, "https", true) == false) goto L10;
            this.scheme = "https";
            return this;
        L10:
            Native$$ExternalSyntheticBUOutline5.m("unexpected scheme: ", r4);
            return null;
        }

        public final void setEncodedFragment$okhttp(String r1) {
            this.encodedFragment = r1;
        }

        public final void setEncodedPassword$okhttp(String r1) {
            this.encodedPassword = r1;
        }

        public final Builder setEncodedPathSegment(int r11, String r12) {
            String r122 = _UrlKt.canonicalize$default(r12, 0, 0, _UrlKt.PATH_SEGMENT_ENCODE_SET, true, false, false, false, 115, null);
            this.encodedPathSegments.set(r11, r122);
            if (isDot(r122) == false) goto L5;
        L7:
            Options$Companion$$ExternalSyntheticBUOutline0.m("unexpected path segment: ", r12);
            return null;
        L5:
            if (isDotDot(r122) == true) goto L7;
            return this;
        }

        public final void setEncodedQueryNamesAndValues$okhttp(List<String> r1) {
            this.encodedQueryNamesAndValues = r1;
        }

        public final Builder setEncodedQueryParameter(String r1, String r2) {
            removeAllEncodedQueryParameters(r1);
            addEncodedQueryParameter(r1, r2);
            return this;
        }

        public final void setEncodedUsername$okhttp(String r1) {
            this.encodedUsername = r1;
        }

        public final void setHost$okhttp(String r1) {
            this.host = r1;
        }

        public final Builder setPathSegment(int r11, String r12) {
            String r122 = _UrlKt.canonicalize$default(r12, 0, 0, _UrlKt.PATH_SEGMENT_ENCODE_SET, false, false, false, false, 123, null);
            if (isDot(r122) == false) goto L5;
        L8:
            Options$Companion$$ExternalSyntheticBUOutline0.m("unexpected path segment: ", r12);
            return null;
        L5:
            if (isDotDot(r122) == true) goto L8;
            this.encodedPathSegments.set(r11, r122);
            return this;
        }

        public final void setPort$okhttp(int r1) {
            this.port = r1;
        }

        public final Builder setQueryParameter(String r1, String r2) {
            removeAllQueryParameters(r1);
            addQueryParameter(r1, r2);
            return this;
        }

        public final void setScheme$okhttp(String r1) {
            this.scheme = r1;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            String r1 = this.scheme;
            if (r1 == null) goto L5;
            r0.append(r1);
            r0.append("://");
        L7:
            if (this.encodedUsername.length() <= 0) goto L10;
        L11:
            r0.append(this.encodedUsername);
            if (this.encodedPassword.length() <= 0) goto L14;
            r0.append(':');
            r0.append(this.encodedPassword);
        L14:
            r0.append('@');
        L15:
            String r12 = this.host;
            if (r12 == null) goto L22;
            if (StringsKt.contains$default(r12, ':', false, 2, null) == false) goto L20;
            r0.append('[');
            r0.append(this.host);
            r0.append(']');
            goto L22
        L20:
            r0.append(this.host);
        L22:
            if (this.port == (-1)) goto L24;
        L25:
            int r13 = effectivePort();
            String r3 = this.scheme;
            if (r3 != null) goto L28;
        L29:
            r0.append(':');
            r0.append(r13);
        L30:
            toPathString(this.encodedPathSegments, r0);
            if (this.encodedQueryNamesAndValues == null) goto L34;
            r0.append('?');
            Companion.access$toQueryString(HttpUrl.Companion, this.encodedQueryNamesAndValues, r0);
        L34:
            if (this.encodedFragment == null) goto L37;
            r0.append('#');
            r0.append(this.encodedFragment);
        L37:
            return r0.toString();
        L28:
            if (r13 == HttpUrl.Companion.defaultPort(r3)) goto L30;
        L24:
            if (this.scheme == null) goto L30;
        L10:
            if (this.encodedPassword.length() <= 0) goto L15;
        L5:
            r0.append("//");
            goto L7
        }

        public final Builder username(String r11) {
            this.encodedUsername = _UrlKt.canonicalize$default(r11, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, 123, null);
            return this;
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J \u0010\b\u001a\u00020\t*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\n2\n\u0010\u000b\u001a\u00060\fj\u0002`\rH\u0002J\u0011\u0010\u000e\u001a\u00020\u000f*\u00020\u0007H\u0007¢\u0006\u0002\b\u0010J\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u000f*\u00020\u0007H\u0007¢\u0006\u0002\b\u0012J\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u000f*\u00020\u0013H\u0007¢\u0006\u0002\b\u0010J\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u000f*\u00020\u0014H\u0007¢\u0006\u0002\b\u0010J\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\u0016J\u0017\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\u0017J\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u0013H\u0007¢\u0006\u0002\b\u0016J\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0018\u001a\u00020\u0014H\u0007¢\u0006\u0002\b\u0016¨\u0006\u0019"}, d2 = {"Lokhttp3/HttpUrl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "defaultPort", _UrlKt.FRAGMENT_ENCODE_SET, "scheme", _UrlKt.FRAGMENT_ENCODE_SET, "toQueryString", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "out", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "toHttpUrl", "Lokhttp3/HttpUrl;", "get", "toHttpUrlOrNull", "parse", "Ljava/net/URL;", "Ljava/net/URI;", "url", "-deprecated_get", "-deprecated_parse", "uri", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ void access$toQueryString(Companion r0, List r1, StringBuilder r2) {
            r0.toQueryString(r1, r2);
        }

        private final void toQueryString(List<String> r6, StringBuilder r7) {
            IntProgression r5 = RangesKt.step(RangesKt.until(0, r6.size()), 2);
            int r0 = r5.getFirst();
            int r1 = r5.getLast();
            int r52 = r5.getStep();
            if (r52 <= 0) goto L5;
            if (r0 > r1) goto L5;
        L7:
            String r2 = r6.get(r0);
            String r3 = r6.get(r0 + 1);
            if (r0 <= 0) goto L10;
            r7.append(Typography.amp);
        L10:
            r7.append(r2);
            if (r3 == null) goto L13;
            r7.append(SignatureVisitor.INSTANCEOF);
            r7.append(r3);
        L13:
            if (r0 == r1) goto L19;
            r0 = r0 + r52;
            goto L7
        L19:
            return;
        L5:
            if (r52 >= 0) goto L15;
            if (r1 <= r0) goto L7;
            return;
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrl()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrl"}))
        @JvmName(name = "-deprecated_get")
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final HttpUrl m3673deprecated_get(String r1) {
            return get(r1);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "uri.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}))
        @JvmName(name = "-deprecated_get")
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final HttpUrl m3674deprecated_get(URI r1) {
            return get(r1);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}))
        @JvmName(name = "-deprecated_get")
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final HttpUrl m3675deprecated_get(URL r1) {
            return get(r1);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}))
        @JvmName(name = "-deprecated_parse")
        /* renamed from: -deprecated_parse, reason: not valid java name */
        public final HttpUrl m3676deprecated_parse(String r1) {
            return parse(r1);
        }

        @JvmStatic
        public final int defaultPort(String r1) {
            if (Intrinsics.areEqual(r1, "http") == false) goto L7;
            return 80;
        L7:
            if (Intrinsics.areEqual(r1, "https") == false) goto L10;
            return 443;
        L10:
            return -1;
        }

        @JvmStatic
        @JvmName(name = "get")
        public final HttpUrl get(String r2) {
            return new Builder().parse$okhttp(null, r2).build();
        }

        @JvmStatic
        @JvmName(name = "get")
        public final HttpUrl get(URI r1) {
            return parse(r1.toString());
        }

        @JvmStatic
        @JvmName(name = "get")
        public final HttpUrl get(URL r1) {
            return parse(r1.toString());
        }

        @JvmStatic
        @JvmName(name = "parse")
        public final HttpUrl parse(String r1) {
            return get(r1);
        L4:
            return null;
        }
    }

    static {
        Companion = new Companion(null);
    }

    private HttpUrl(String r1, String r2, String r3, String r4, int r5, List<String> r6, List<String> r7, String r8, String r9) {
        this.scheme = r1;
        this.username = r2;
        this.password = r3;
        this.host = r4;
        this.port = r5;
        this.pathSegments = r6;
        this.queryNamesAndValues = r7;
        this.fragment = r8;
        this.url = r9;
    }

    public /* synthetic */ HttpUrl(String r1, String r2, String r3, String r4, int r5, List r6, List r7, String r8, String r9, DefaultConstructorMarker r10) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    @JvmStatic
    public static final int defaultPort(String r1) {
        return Companion.defaultPort(r1);
    }

    @JvmStatic
    @JvmName(name = "get")
    public static final HttpUrl get(String r1) {
        return Companion.get(r1);
    }

    @JvmStatic
    @JvmName(name = "get")
    public static final HttpUrl get(URI r1) {
        return Companion.get(r1);
    }

    @JvmStatic
    @JvmName(name = "get")
    public static final HttpUrl get(URL r1) {
        return Companion.get(r1);
    }

    @JvmStatic
    @JvmName(name = "parse")
    public static final HttpUrl parse(String r1) {
        return Companion.parse(r1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedFragment", imports = {}))
    @JvmName(name = "-deprecated_encodedFragment")
    /* renamed from: -deprecated_encodedFragment, reason: not valid java name */
    public final String m3654deprecated_encodedFragment() {
        return encodedFragment();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPassword", imports = {}))
    @JvmName(name = "-deprecated_encodedPassword")
    /* renamed from: -deprecated_encodedPassword, reason: not valid java name */
    public final String m3655deprecated_encodedPassword() {
        return encodedPassword();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPath", imports = {}))
    @JvmName(name = "-deprecated_encodedPath")
    /* renamed from: -deprecated_encodedPath, reason: not valid java name */
    public final String m3656deprecated_encodedPath() {
        return encodedPath();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPathSegments", imports = {}))
    @JvmName(name = "-deprecated_encodedPathSegments")
    /* renamed from: -deprecated_encodedPathSegments, reason: not valid java name */
    public final List<String> m3657deprecated_encodedPathSegments() {
        return encodedPathSegments();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedQuery", imports = {}))
    @JvmName(name = "-deprecated_encodedQuery")
    /* renamed from: -deprecated_encodedQuery, reason: not valid java name */
    public final String m3658deprecated_encodedQuery() {
        return encodedQuery();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedUsername", imports = {}))
    @JvmName(name = "-deprecated_encodedUsername")
    /* renamed from: -deprecated_encodedUsername, reason: not valid java name */
    public final String m3659deprecated_encodedUsername() {
        return encodedUsername();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "fragment", imports = {}))
    @JvmName(name = "-deprecated_fragment")
    /* renamed from: -deprecated_fragment, reason: not valid java name */
    public final String m3660deprecated_fragment() {
        return this.fragment;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "host", imports = {}))
    @JvmName(name = "-deprecated_host")
    /* renamed from: -deprecated_host, reason: not valid java name */
    public final String m3661deprecated_host() {
        return this.host;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "password", imports = {}))
    @JvmName(name = "-deprecated_password")
    /* renamed from: -deprecated_password, reason: not valid java name */
    public final String m3662deprecated_password() {
        return this.password;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "pathSegments", imports = {}))
    @JvmName(name = "-deprecated_pathSegments")
    /* renamed from: -deprecated_pathSegments, reason: not valid java name */
    public final List<String> m3663deprecated_pathSegments() {
        return this.pathSegments;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "pathSize", imports = {}))
    @JvmName(name = "-deprecated_pathSize")
    /* renamed from: -deprecated_pathSize, reason: not valid java name */
    public final int m3664deprecated_pathSize() {
        return pathSize();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "port", imports = {}))
    @JvmName(name = "-deprecated_port")
    /* renamed from: -deprecated_port, reason: not valid java name */
    public final int m3665deprecated_port() {
        return this.port;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "query", imports = {}))
    @JvmName(name = "-deprecated_query")
    /* renamed from: -deprecated_query, reason: not valid java name */
    public final String m3666deprecated_query() {
        return query();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "queryParameterNames", imports = {}))
    @JvmName(name = "-deprecated_queryParameterNames")
    /* renamed from: -deprecated_queryParameterNames, reason: not valid java name */
    public final Set<String> m3667deprecated_queryParameterNames() {
        return queryParameterNames();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "querySize", imports = {}))
    @JvmName(name = "-deprecated_querySize")
    /* renamed from: -deprecated_querySize, reason: not valid java name */
    public final int m3668deprecated_querySize() {
        return querySize();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "scheme", imports = {}))
    @JvmName(name = "-deprecated_scheme")
    /* renamed from: -deprecated_scheme, reason: not valid java name */
    public final String m3669deprecated_scheme() {
        return this.scheme;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to toUri()", replaceWith = @ReplaceWith(expression = "toUri()", imports = {}))
    @JvmName(name = "-deprecated_uri")
    /* renamed from: -deprecated_uri, reason: not valid java name */
    public final URI m3670deprecated_uri() {
        return uri();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to toUrl()", replaceWith = @ReplaceWith(expression = "toUrl()", imports = {}))
    @JvmName(name = "-deprecated_url")
    /* renamed from: -deprecated_url, reason: not valid java name */
    public final URL m3671deprecated_url() {
        return url();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "username", imports = {}))
    @JvmName(name = "-deprecated_username")
    /* renamed from: -deprecated_username, reason: not valid java name */
    public final String m3672deprecated_username() {
        return this.username;
    }

    @JvmName(name = "encodedFragment")
    public final String encodedFragment() {
        if (this.fragment != null) goto L6;
        return null;
    L6:
        int r0 = StringsKt.indexOf$default(this.url, '#', 0, false, 6, null) + 1;
        return this.url.substring(r0);
    }

    @JvmName(name = "encodedPassword")
    public final String encodedPassword() {
        if (this.password.length() != 0) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        int r0 = StringsKt.indexOf$default(this.url, ':', this.scheme.length() + 3, false, 4, null) + 1;
        int r1 = StringsKt.indexOf$default(this.url, '@', 0, false, 6, null);
        return this.url.substring(r0, r1);
    }

    @JvmName(name = "encodedPath")
    public final String encodedPath() {
        int r0 = StringsKt.indexOf$default(this.url, '/', this.scheme.length() + 3, false, 4, null);
        String r1 = this.url;
        int r12 = _UtilCommonKt.delimiterOffset(r1, "?#", r0, r1.length());
        return this.url.substring(r0, r12);
    }

    @JvmName(name = "encodedPathSegments")
    public final List<String> encodedPathSegments() {
        int r0 = StringsKt.indexOf$default(this.url, '/', this.scheme.length() + 3, false, 4, null);
        String r1 = this.url;
        int r12 = _UtilCommonKt.delimiterOffset(r1, "?#", r0, r1.length());
        ArrayList r2 = new ArrayList();
    L3:
        if (r0 >= r12) goto L5;
        int r02 = r0 + 1;
        int r3 = _UtilCommonKt.delimiterOffset(this.url, '/', r02, r12);
        r2.add(this.url.substring(r02, r3));
        r0 = r3;
        goto L3
    L5:
        return r2;
    }

    @JvmName(name = "encodedQuery")
    public final String encodedQuery() {
        if (this.queryNamesAndValues != null) goto L6;
        return null;
    L6:
        int r0 = StringsKt.indexOf$default(this.url, '?', 0, false, 6, null) + 1;
        String r1 = this.url;
        int r12 = _UtilCommonKt.delimiterOffset(r1, '#', r0, r1.length());
        return this.url.substring(r0, r12);
    }

    @JvmName(name = "encodedUsername")
    public final String encodedUsername() {
        if (this.username.length() != 0) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        int r0 = this.scheme.length() + 3;
        String r1 = this.url;
        int r12 = _UtilCommonKt.delimiterOffset(r1, ":@", r0, r1.length());
        return this.url.substring(r0, r12);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof HttpUrl) == true) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual(((HttpUrl) r2).url, this.url) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @JvmName(name = "fragment")
    public final String fragment() {
        return this.fragment;
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    @JvmName(name = "host")
    public final String host() {
        return this.host;
    }

    public final boolean isHttps() {
        return Intrinsics.areEqual(this.scheme, "https");
    }

    public final Builder newBuilder() {
        Builder r0 = new Builder();
        r0.setScheme$okhttp(this.scheme);
        r0.setEncodedUsername$okhttp(encodedUsername());
        r0.setEncodedPassword$okhttp(encodedPassword());
        r0.setHost$okhttp(this.host);
        if (this.port == Companion.defaultPort(this.scheme)) goto L5;
        int r1 = this.port;
    L6:
        r0.setPort$okhttp(r1);
        r0.getEncodedPathSegments$okhttp().clear();
        r0.getEncodedPathSegments$okhttp().addAll(encodedPathSegments());
        r0.encodedQuery(encodedQuery());
        r0.setEncodedFragment$okhttp(encodedFragment());
        return r0;
    L5:
        r1 = -1;
        goto L6
    }

    public final Builder newBuilder(String r2) {
        return new Builder().parse$okhttp(this, r2);
    L4:
        return null;
    }

    @JvmName(name = "password")
    public final String password() {
        return this.password;
    }

    @JvmName(name = "pathSegments")
    public final List<String> pathSegments() {
        return this.pathSegments;
    }

    @JvmName(name = "pathSize")
    public final int pathSize() {
        return this.pathSegments.size();
    }

    @JvmName(name = "port")
    public final int port() {
        return this.port;
    }

    @JvmName(name = "query")
    public final String query() {
        if (this.queryNamesAndValues != null) goto L6;
        return null;
    L6:
        StringBuilder r0 = new StringBuilder();
        Companion.access$toQueryString(Companion, this.queryNamesAndValues, r0);
        return r0.toString();
    }

    public final String queryParameter(String r6) {
        List<String> r0 = this.queryNamesAndValues;
        if (r0 != null) goto L5;
        return null;
    L5:
        IntProgression r02 = RangesKt.step(RangesKt.until(0, r0.size()), 2);
        int r2 = r02.getFirst();
        int r3 = r02.getLast();
        int r03 = r02.getStep();
        if (r03 <= 0) goto L8;
        if (r2 > r3) goto L8;
    L11:
        if (Intrinsics.areEqual(r6, this.queryNamesAndValues.get(r2)) == true) goto L13;
        if (r2 == r3) goto L16;
        r2 = r2 + r03;
    L16:
        return null;
    L13:
        return this.queryNamesAndValues.get(r2 + 1);
    L8:
        if (r03 >= 0) goto L16;
        if (r3 > r2) goto L16;
        goto L16
    }

    public final String queryParameterName(int r1) {
        List<String> r0 = this.queryNamesAndValues;
        if (r0 == null) goto L7;
        return r0.get(r1 * 2);
    L7:
        throw new IndexOutOfBoundsException();
    }

    @JvmName(name = "queryParameterNames")
    public final Set<String> queryParameterNames() {
        if (this.queryNamesAndValues == null) goto L5;
        LinkedHashSet r0 = new LinkedHashSet(this.queryNamesAndValues.size() / 2, 1.0f);
        IntProgression r1 = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        int r2 = r1.getFirst();
        int r3 = r1.getLast();
        int r12 = r1.getStep();
        if (r12 <= 0) goto L9;
        if (r2 > r3) goto L9;
    L11:
        r0.add(this.queryNamesAndValues.get(r2));
        if (r2 == r3) goto L15;
        r2 = r2 + r12;
    L15:
        return Collections.unmodifiableSet(r0);
    L9:
        if (r12 >= 0) goto L15;
        if (r3 > r2) goto L15;
    L5:
        return SetsKt.emptySet();
    }

    public final String queryParameterValue(int r1) {
        List<String> r0 = this.queryNamesAndValues;
        if (r0 == null) goto L7;
        return r0.get((r1 * 2) + 1);
    L7:
        throw new IndexOutOfBoundsException();
    }

    public final List<String> queryParameterValues(String r7) {
        if (this.queryNamesAndValues == null) goto L5;
        ArrayList r0 = new ArrayList(4);
        IntProgression r1 = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        int r2 = r1.getFirst();
        int r3 = r1.getLast();
        int r12 = r1.getStep();
        if (r12 <= 0) goto L9;
        if (r2 > r3) goto L9;
    L12:
        if (Intrinsics.areEqual(r7, this.queryNamesAndValues.get(r2)) == false) goto L14;
        r0.add(this.queryNamesAndValues.get(r2 + 1));
    L14:
        if (r2 == r3) goto L17;
        r2 = r2 + r12;
    L17:
        return Collections.unmodifiableList(r0);
    L9:
        if (r12 >= 0) goto L17;
        if (r3 > r2) goto L17;
    L5:
        return CollectionsKt.emptyList();
    }

    @JvmName(name = "querySize")
    public final int querySize() {
        List<String> r0 = this.queryNamesAndValues;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.size() / 2;
    }

    public final String redact() {
        return newBuilder("/...").username(_UrlKt.FRAGMENT_ENCODE_SET).password(_UrlKt.FRAGMENT_ENCODE_SET).build().toString();
    }

    public final HttpUrl resolve(String r1) {
        Builder r0 = newBuilder(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.build();
    }

    @JvmName(name = "scheme")
    public final String scheme() {
        return this.scheme;
    }

    public String toString() {
        return this.url;
    }

    public final String topPrivateDomain() {
        if (_HostnamesCommonKt.canParseAsIpAddress(this.host) == false) goto L7;
        return null;
    L7:
        return PublicSuffixDatabase.Companion.get().getEffectiveTldPlusOne(this.host);
    }

    @JvmName(name = "uri")
    public final URI uri() {
        String r3 = newBuilder().reencodeForUri$okhttp().toString();
        return new URI(r3);
    L5:
        e = move-exception;
        return URI.create(new Regex("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]").replace(r3, _UrlKt.FRAGMENT_ENCODE_SET));
    L8:
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    @JvmName(name = "url")
    public final URL url() {
        return new URL(this.url);
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    @JvmName(name = "username")
    public final String username() {
        return this.username;
    }
}
