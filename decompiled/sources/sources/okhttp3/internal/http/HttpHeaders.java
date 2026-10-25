package okhttp3.internal.http;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.io.encoding.Base64;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.ByteString;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u001a\u0010\t\u001a\u00020\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rH\u0002\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u000bH\u0002\u001a\u0014\u0010\u0010\u001a\u00020\u000f*\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u001a\u000e\u0010\u0013\u001a\u0004\u0018\u00010\b*\u00020\u000bH\u0002\u001a\u000e\u0010\u0014\u001a\u0004\u0018\u00010\b*\u00020\u000bH\u0002\u001a\u001a\u0010\u0015\u001a\u00020\n*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0006\u001a\n\u0010\u001a\u001a\u00020\u000f*\u00020\u001b\u001a\u0010\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001bH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"QUOTED_STRING_DELIMITERS", "Lokio/ByteString;", "TOKEN_DELIMITERS", "parseChallenges", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Challenge;", "Lokhttp3/Headers;", "headerName", _UrlKt.FRAGMENT_ENCODE_SET, "readChallengeHeader", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/Buffer;", "result", _UrlKt.FRAGMENT_ENCODE_SET, "skipCommasAndWhitespace", _UrlKt.FRAGMENT_ENCODE_SET, "startsWith", "prefix", _UrlKt.FRAGMENT_ENCODE_SET, "readQuotedString", "readToken", "receiveHeaders", "Lokhttp3/CookieJar;", "url", "Lokhttp3/HttpUrl;", "headers", "promisesBody", "Lokhttp3/Response;", "hasBody", PluginsConstants.RESPONSE, "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "HttpHeaders")
/* loaded from: classes.dex */
public final class HttpHeaders {
    private static final ByteString QUOTED_STRING_DELIMITERS = null;
    private static final ByteString TOKEN_DELIMITERS = null;

    static {
        ByteString.Companion r0 = ByteString.Companion;
        QUOTED_STRING_DELIMITERS = r0.encodeUtf8("\"\\");
        TOKEN_DELIMITERS = r0.encodeUtf8("\t ,=");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "No longer supported", replaceWith = @ReplaceWith(expression = "response.promisesBody()", imports = {}))
    public static final boolean hasBody(Response r0) {
        return promisesBody(r0);
    }

    public static final List<Challenge> parseChallenges(Headers r7, String r8) {
        ArrayList r0 = new ArrayList();
        int r1 = r7.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L12;
        if (StringsKt.equals(r8, r7.name(r2), true) == false) goto L11;
        readChallengeHeader(new Buffer().writeUtf8(r7.value(r2)), r0);     // Catch: EOFException -> L9
    L9:
        e = move-exception;
        Platform.Companion.get().log("Unable to parse challenge", 5, e);
    L11:
        r2 = r2 + 1;
        goto L3
    L12:
        return r0;
    }

    public static final boolean promisesBody(Response r8) {
        if (Intrinsics.areEqual(r8.request().method(), "HEAD") == false) goto L5;
        return false;
    L5:
        int r0 = r8.code();
        if (r0 < 100) goto L10;
        if (r0 >= 200) goto L10;
    L15:
        if (_UtilJvmKt.headersContentLength(r8) == (-1)) goto L17;
    L20:
        return true;
    L17:
        if (StringsKt.equals("chunked", Response.header$default(r8, "Transfer-Encoding", null, 2, null), true) == true) goto L20;
        return false;
    L10:
        if (r0 == 204) goto L15;
        if (r0 == 304) goto L15;
        return true;
    }

    private static final void readChallengeHeader(Buffer r7, List<Challenge> r8) {
    L3:
        String r1 = null;
    L4:
        if (r1 != null) goto L8;
        skipCommasAndWhitespace(r7);
        r1 = readToken(r7);
        if (r1 != null) goto L8;
        return;
    L8:
        boolean r2 = skipCommasAndWhitespace(r7);
        String r3 = readToken(r7);
        if (r3 == null) goto L11;
        int r5 = _UtilCommonKt.skipAll(r7, Base64.padSymbol);
        boolean r6 = skipCommasAndWhitespace(r7);
        if (r2 == true) goto L21;
        if (r6 == true) goto L20;
        if (r7.exhausted() == false) goto L21;
    L20:
        r8.add(new Challenge(r1, Collections.singletonMap(null, r3 + StringsKt.repeat("=", r5))));
    L21:
        LinkedHashMap r22 = new LinkedHashMap();
        int r52 = r5 + _UtilCommonKt.skipAll(r7, Base64.padSymbol);
    L22:
        if (r3 != null) goto L26;
        r3 = readToken(r7);
        if (skipCommasAndWhitespace(r7) == true) goto L48;
        r52 = _UtilCommonKt.skipAll(r7, Base64.padSymbol);
    L48:
        r8.add(new Challenge(r1, r22));
        r1 = r3;
    L26:
        if (r52 == 0) goto L48;
        if (r52 > 1) goto L63;
        if (skipCommasAndWhitespace(r7) == true) goto L64;
        if (startsWith(r7, (byte) 34) == false) goto L36;
        String r62 = readQuotedString(r7);
    L37:
        if (r62 == null) goto L65;
        if (((String) r22.put(r3, r62)) != null) goto L66;
        if (skipCommasAndWhitespace(r7) == true) goto L47;
        if (r7.exhausted() == true) goto L47;
        return;
    L47:
        r3 = null;
        goto L22
    L66:
        return;
    L65:
        return;
    L36:
        r62 = readToken(r7);
        goto L37
    L64:
        return;
    L63:
        return;
    L11:
        if (r7.exhausted() == false) goto L46;
        r8.add(new Challenge(r1, MapsKt.emptyMap()));
        return;
    }

    private static final String readQuotedString(Buffer r11) {
        if (r11.readByte() != 34) goto L16;
        Buffer r0 = new Buffer();
    L5:
        long r3 = r11.indexOfElement(QUOTED_STRING_DELIMITERS);
        if (r3 == (-1)) goto L7;
        if (r11.getByte(r3) == 34) goto L10;
        if (r11.size() == (r3 + 1)) goto L14;
        r0.write(r11, r3);
        r11.readByte();
        r0.write(r11, 1);
        goto L5
    L14:
        return null;
    L10:
        r0.write(r11, r3);
        r11.readByte();
        return r0.readUtf8();
    L7:
        return null;
    L16:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return null;
    }

    private static final String readToken(Buffer r4) {
        long r0 = r4.indexOfElement(TOKEN_DELIMITERS);
        if (r0 != (-1)) goto L6;
        r0 = r4.size();
    L6:
        if (r0 != 0) goto L8;
        return null;
    L8:
        return r4.readUtf8(r0);
    }

    public static final void receiveHeaders(CookieJar r1, HttpUrl r2, Headers r3) {
        if (r1 == CookieJar.NO_COOKIES) goto L10;
        List<Cookie> r32 = Cookie.Companion.parseAll(r2, r3);
        if (r32.isEmpty() == false) goto L8;
        return;
    L8:
        r1.saveFromResponse(r2, r32);
        return;
    }

    private static final boolean skipCommasAndWhitespace(Buffer r3) {
        boolean r0 = false;
    L4:
        if (r3.exhausted() == true) goto L13;
        byte r1 = r3.getByte(0);
        if (r1 == 44) goto L7;
        if (r1 != 32) goto L11;
    L12:
        r3.readByte();
        goto L4
    L11:
        if (r1 != 9) goto L13;
    L7:
        r3.readByte();
        r0 = true;
    L13:
        return r0;
    }

    private static final boolean startsWith(Buffer r2, byte r3) {
        if (r2.exhausted() == false) goto L5;
        return false;
    L5:
        if (r2.getByte(0) != r3) goto L10;
        return true;
    L10:
        return false;
    }
}
