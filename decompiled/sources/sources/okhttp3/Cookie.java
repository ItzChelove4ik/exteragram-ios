package okhttp3;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.CastStatusCodes;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal._HostnamesCommonKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.http.DateFormattingKt;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 *2\u00020\u0001:\u0002)*B[\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016J\u0013\u0010\u0017\u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0017J\b\u0010\u001b\u001a\u00020\u0003H\u0016J\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u001cJ\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u001dJ\r\u0010\f\u001a\u00020\nH\u0007¢\u0006\u0002\b\u001eJ\r\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u001fJ\r\u0010\r\u001a\u00020\nH\u0007¢\u0006\u0002\b J\r\u0010\u0007\u001a\u00020\u0003H\u0007¢\u0006\u0002\b!J\r\u0010\b\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\"J\r\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0002\b#J\r\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\b$J\u0015\u0010\u001b\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\nH\u0000¢\u0006\u0002\b&J\u0006\u0010'\u001a\u00020(R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0011R\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0011R\u0013\u0010\u0005\u001a\u00020\u00068\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0012R\u0013\u0010\u0007\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u0013\u0010\b\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0011R\u0013\u0010\t\u001a\u00020\n8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0013R\u0013\u0010\u000b\u001a\u00020\n8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0013R\u0013\u0010\f\u001a\u00020\n8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0013R\u0013\u0010\r\u001a\u00020\n8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0013R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0011¨\u0006+"}, d2 = {"Lokhttp3/Cookie;", _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "value", "expiresAt", _UrlKt.FRAGMENT_ENCODE_SET, "domain", "path", "secure", _UrlKt.FRAGMENT_ENCODE_SET, "httpOnly", "persistent", "hostOnly", "sameSite", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ZZZZLjava/lang/String;)V", "()Ljava/lang/String;", "()J", "()Z", "matches", "url", "Lokhttp3/HttpUrl;", "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "-deprecated_name", "-deprecated_value", "-deprecated_persistent", "-deprecated_expiresAt", "-deprecated_hostOnly", "-deprecated_domain", "-deprecated_path", "-deprecated_httpOnly", "-deprecated_secure", "forObsoleteRfc2965", "toString$okhttp", "newBuilder", "Lokhttp3/Cookie$Builder;", "Builder", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Cookie {
    public static final Companion Companion = null;
    private static final Pattern DAY_OF_MONTH_PATTERN = null;
    private static final Pattern MONTH_PATTERN = null;
    private static final Pattern TIME_PATTERN = null;
    private static final Pattern YEAR_PATTERN = null;
    private final String domain;
    private final long expiresAt;
    private final boolean hostOnly;
    private final boolean httpOnly;
    private final String name;
    private final String path;
    private final boolean persistent;
    private final String sameSite;
    private final boolean secure;
    private final String value;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\bJ\u0018\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\bJ\u0006\u0010\u000e\u001a\u00020\u0000J\u0006\u0010\u0010\u001a\u00020\u0000J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\bJ\u0006\u0010\u0015\u001a\u00020\u0005R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lokhttp3/Cookie$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "cookie", "Lokhttp3/Cookie;", "(Lokhttp3/Cookie;)V", "name", _UrlKt.FRAGMENT_ENCODE_SET, "value", "expiresAt", _UrlKt.FRAGMENT_ENCODE_SET, "domain", "path", "secure", _UrlKt.FRAGMENT_ENCODE_SET, "httpOnly", "persistent", "hostOnly", "sameSite", "hostOnlyDomain", "build", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCookie.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cookie.kt\nokhttp3/Cookie$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,727:1\n1#2:728\n*E\n"})
    public static final class Builder {
        private String domain;
        private long expiresAt;
        private boolean hostOnly;
        private boolean httpOnly;
        private String name;
        private String path;
        private boolean persistent;
        private String sameSite;
        private boolean secure;
        private String value;

        public Builder() {
            this.expiresAt = DateFormattingKt.MAX_DATE;
            this.path = "/";
        }

        public Builder(Cookie r3) {
            this();
            this.name = r3.name();
            this.value = r3.value();
            this.expiresAt = r3.expiresAt();
            this.domain = r3.domain();
            this.path = r3.path();
            this.secure = r3.secure();
            this.httpOnly = r3.httpOnly();
            this.persistent = r3.persistent();
            this.hostOnly = r3.hostOnly();
            this.sameSite = r3.sameSite();
        }

        private final Builder domain(String r2, boolean r3) {
            String r0 = _HostnamesCommonKt.toCanonicalHost(r2);
            if (r0 == null) goto L6;
            this.domain = r0;
            this.hostOnly = r3;
            return this;
        L6:
            Native$$ExternalSyntheticBUOutline5.m("unexpected domain: ", r2);
            return null;
        }

        public final Cookie build() {
            String r1 = this.name;
            if (r1 == null) goto L14;
            String r2 = this.value;
            if (r2 == null) goto L12;
            long r3 = this.expiresAt;
            String r5 = this.domain;
            if (r5 != null) goto L9;
            f$$ExternalSyntheticBUOutline2.m("builder.domain == null");
            return null;
        L9:
            return new Cookie(r1, r2, r3, r5, this.path, this.secure, this.httpOnly, this.persistent, this.hostOnly, this.sameSite, null);
        L12:
            f$$ExternalSyntheticBUOutline2.m("builder.value == null");
            return null;
        L14:
            f$$ExternalSyntheticBUOutline2.m("builder.name == null");
            return null;
        }

        public final Builder domain(String r2) {
            return domain(r2, false);
        }

        public final Builder expiresAt(long r4) {
            if (r4 > 0) goto L6;
            r4 = Long.MIN_VALUE;
        L6:
            if (r4 <= DateFormattingKt.MAX_DATE) goto L8;
            r4 = 253402300799999L;
        L8:
            this.expiresAt = r4;
            this.persistent = true;
            return this;
        }

        public final Builder hostOnlyDomain(String r2) {
            return domain(r2, true);
        }

        public final Builder httpOnly() {
            this.httpOnly = true;
            return this;
        }

        public final Builder name(String r2) {
            if (Intrinsics.areEqual(StringsKt.trim(r2).toString(), r2) == false) goto L6;
            this.name = r2;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("name is not trimmed");
            return null;
        }

        public final Builder path(String r5) {
            if (StringsKt.startsWith$default(r5, "/", false, 2, null) == false) goto L6;
            this.path = r5;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("path must start with '/'");
            return null;
        }

        public final Builder sameSite(String r2) {
            if (Intrinsics.areEqual(StringsKt.trim(r2).toString(), r2) == false) goto L6;
            this.sameSite = r2;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("sameSite is not trimmed");
            return null;
        }

        public final Builder secure() {
            this.secure = true;
            return this;
        }

        public final Builder value(String r2) {
            if (Intrinsics.areEqual(StringsKt.trim(r2).toString(), r2) == false) goto L6;
            this.value = r2;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("value is not trimmed");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH\u0002J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\rH\u0007J'\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\rH\u0000¢\u0006\u0002\b\u0018J \u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J(\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u000bH\u0002J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\rH\u0002J\u0010\u0010\"\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\rH\u0002J\u001e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00140$2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&H\u0007R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lokhttp3/Cookie$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "YEAR_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "MONTH_PATTERN", "DAY_OF_MONTH_PATTERN", "TIME_PATTERN", "domainMatch", _UrlKt.FRAGMENT_ENCODE_SET, "urlHost", _UrlKt.FRAGMENT_ENCODE_SET, "domain", "pathMatch", "url", "Lokhttp3/HttpUrl;", "path", "parse", "Lokhttp3/Cookie;", "setCookie", "currentTimeMillis", _UrlKt.FRAGMENT_ENCODE_SET, "parse$okhttp", "parseExpires", "s", "pos", _UrlKt.FRAGMENT_ENCODE_SET, "limit", "dateCharacterOffset", PluginsConstants.Settings.TYPE_INPUT, "invert", "parseMaxAge", "parseDomain", "parseAll", _UrlKt.FRAGMENT_ENCODE_SET, "headers", "Lokhttp3/Headers;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCookie.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cookie.kt\nokhttp3/Cookie$Companion\n+ 2 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n*L\n1#1,727:1\n242#2:728\n*S KotlinDebug\n*F\n+ 1 Cookie.kt\nokhttp3/Cookie$Companion\n*L\n723#1:728\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ boolean access$domainMatch(Companion r0, String r1, String r2) {
            return r0.domainMatch(r1, r2);
        }

        public static final /* synthetic */ boolean access$pathMatch(Companion r0, HttpUrl r1, String r2) {
            return r0.pathMatch(r1, r2);
        }

        private final int dateCharacterOffset(String r4, int r5, int r6, boolean r7) {
        L2:
            if (r5 >= r6) goto L31;
            char r3 = r4.charAt(r5);
            if (r3 >= ' ') goto L8;
            if (r3 == '\t') goto L8;
        L26:
            boolean r32 = true;
        L28:
            if (r32 == (!r7)) goto L29;
            r5 = r5 + 1;
            goto L2
        L29:
            return r5;
        L8:
            if (r3 >= 127) goto L26;
            if ('0' > r3) goto L14;
            if (r3 < ':') goto L26;
        L14:
            if ('a' > r3) goto L19;
            if (r3 < '{') goto L26;
        L19:
            if ('A' <= r3) goto L21;
        L23:
            if (r3 == ':') goto L26;
            r32 = false;
            goto L28
        L21:
            if (r3 >= '[') goto L23;
        L31:
            return r6;
        }

        private final boolean domainMatch(String r4, String r5) {
            if (Intrinsics.areEqual(r4, r5) == false) goto L6;
            return true;
        L6:
            if (StringsKt.endsWith$default(r4, r5, false, 2, null) == true) goto L8;
        L12:
            return false;
        L8:
            if (r4.charAt((r4.length() - r5.length()) - 1) != '.') goto L12;
            if (_HostnamesCommonKt.canParseAsIpAddress(r4) == true) goto L12;
            return true;
        }

        private final String parseDomain(String r4) {
            if (StringsKt.endsWith$default(r4, ".", false, 2, null) == true) goto L9;
            String r3 = _HostnamesCommonKt.toCanonicalHost(StringsKt.removePrefix(r4, "."));
            if (r3 == null) goto L7;
            return r3;
        L7:
            Segment$$ExternalSyntheticBUOutline0.m();
            return null;
        L9:
            f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
            return null;
        }

        private final long parseExpires(String r23, int r24, int r25) {
            int r4 = dateCharacterOffset(r23, r24, r25, false);
            Matcher r5 = Cookie.access$getTIME_PATTERN$cp().matcher(r23);
            int r7 = -1;
            int r8 = -1;
            int r9 = -1;
            int r10 = -1;
            int r11 = -1;
            int r12 = -1;
        L4:
            if (r4 >= r25) goto L24;
            int r15 = dateCharacterOffset(r23, r4 + 1, r25, true);
            r5.region(r4, r15);
            if (r8 == (-1)) goto L8;
        L10:
            if (r9 == (-1)) goto L12;
        L14:
            if (r10 == (-1)) goto L16;
        L18:
            if (r7 != (-1)) goto L22;
            if (r5.usePattern(Cookie.access$getYEAR_PATTERN$cp()).matches() == false) goto L22;
            r7 = Integer.parseInt(r5.group(1));
        L22:
            r4 = dateCharacterOffset(r23, r15 + 1, r25, false);
            goto L4
        L16:
            if (r5.usePattern(Cookie.access$getMONTH_PATTERN$cp()).matches() == false) goto L18;
            r10 = StringsKt.indexOf$default(Cookie.access$getMONTH_PATTERN$cp().pattern(), r5.group(1).toLowerCase(Locale.US), 0, false, 6, null) / 4;
            goto L22
        L12:
            if (r5.usePattern(Cookie.access$getDAY_OF_MONTH_PATTERN$cp()).matches() == false) goto L14;
            r9 = Integer.parseInt(r5.group(1));
            goto L22
        L8:
            if (r5.usePattern(Cookie.access$getTIME_PATTERN$cp()).matches() == false) goto L10;
            r8 = Integer.parseInt(r5.group(1));
            r11 = Integer.parseInt(r5.group(2));
            r12 = Integer.parseInt(r5.group(3));
            goto L22
        L24:
            if (70 <= r7) goto L26;
        L28:
            if (r7 < 0) goto L32;
            if (r7 >= 70) goto L32;
            r7 = r7 + CastStatusCodes.AUTHENTICATION_FAILED;
        L32:
            if (r7 < 1601) goto L57;
            if (r10 == (-1)) goto L55;
            if (1 <= r9) goto L36;
        L53:
            f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
            return 0;
        L36:
            if (r9 >= 32) goto L53;
            if (r8 >= 0) goto L39;
        L51:
            f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
            return 0;
        L39:
            if (r8 >= 24) goto L51;
            if (r11 >= 0) goto L42;
        L49:
            f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
            return 0;
        L42:
            if (r11 >= 60) goto L49;
            if (r12 < 0) goto L47;
            if (r12 >= 60) goto L47;
            GregorianCalendar r0 = new GregorianCalendar(_UtilJvmKt.UTC);
            r0.setLenient(false);
            r0.set(1, r7);
            r0.set(2, r10 - 1);
            r0.set(5, r9);
            r0.set(11, r8);
            r0.set(12, r11);
            r0.set(13, r12);
            r0.set(14, 0);
            return r0.getTimeInMillis();
        L47:
            f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
            return 0;
        L55:
            f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
            return 0;
        L57:
            f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
            return 0;
        L26:
            if (r7 >= 100) goto L28;
            r7 = r7 + 1900;
            goto L28
        }

        private final long parseMaxAge(String r6) {
            long r5 = Long.parseLong(r6);     // Catch: NumberFormatException -> L8
            if (r5 > 0) goto L7;
            return Long.MIN_VALUE;
        L7:
            return r5;
        L8:
            e = move-exception;
            if (new Regex("-?\\d+").matches(r6) == true) goto L12;
            throw e;
        L12:
            if (StringsKt.startsWith$default(r6, "-", false, 2, null) == true) goto L19;
            return LongCompanionObject.MAX_VALUE;
        L19:
            return Long.MIN_VALUE;
        }

        private final boolean pathMatch(HttpUrl r5, String r6) {
            String r4 = r5.encodedPath();
            if (Intrinsics.areEqual(r4, r6) == false) goto L6;
            return true;
        L6:
            if (StringsKt.startsWith$default(r4, r6, false, 2, null) == true) goto L8;
        L13:
            return false;
        L8:
            if (StringsKt.endsWith$default(r6, "/", false, 2, null) == false) goto L11;
            return true;
        L11:
            if (r4.charAt(r6.length()) != '/') goto L13;
            return true;
        }

        @JvmStatic
        public final Cookie parse(HttpUrl r3, String r4) {
            return parse$okhttp(System.currentTimeMillis(), r3, r4);
        }

        public final Cookie parse$okhttp(long r31, HttpUrl r33, String r34) {
            int r4 = _UtilCommonKt.delimiterOffset$default(r34, ';', 0, 0, 6, null);
            int r2 = _UtilCommonKt.delimiterOffset$default(r34, SignatureVisitor.INSTANCEOF, 0, r4, 2, null);
            Cookie r3 = null;
            if (r2 != r4) goto L5;
            return null;
        L5:
            String r8 = _UtilCommonKt.trimSubstring$default(r34, 0, r2, 1, null);
            if (r8.length() != 0) goto L9;
        L10:
            return null;
        L9:
            if (_UtilCommonKt.indexOfControlOrNonAscii(r8) != (-1)) goto L10;
            String r22 = _UtilCommonKt.trimSubstring(r34, r2 + 1, r4);
            if (_UtilCommonKt.indexOfControlOrNonAscii(r22) == (-1)) goto L14;
            return null;
        L14:
            int r42 = r4 + 1;
            int r7 = r34.length();
            String r9 = null;
            String r15 = null;
            String r20 = null;
            boolean r10 = false;
            boolean r19 = false;
            boolean r23 = false;
            boolean r18 = true;
            long r13 = -1;
            long r21 = DateFormattingKt.MAX_DATE;
        L15:
            if (r42 >= r7) goto L45;
            int r11 = _UtilCommonKt.delimiterOffset(r34, ';', r42, r7);
            int r12 = _UtilCommonKt.delimiterOffset(r34, SignatureVisitor.INSTANCEOF, r42, r11);
            String r43 = _UtilCommonKt.trimSubstring(r34, r42, r12);
            if (r12 >= r11) goto L20;
            String r122 = _UtilCommonKt.trimSubstring(r34, r12 + 1, r11);
        L19:
            Cookie r26 = r3;
            if (StringsKt.equals(r43, "expires", true) == false) goto L26;
            r21 = parseExpires(r122, 0, r122.length());     // Catch: Throwable -> L85
        L24:
            r19 = true;
        L44:
            r42 = r11 + 1;
            r3 = r26;
            goto L15
        L26:
            if (StringsKt.equals(r43, "max-age", true) == false) goto L29;
            r13 = parseMaxAge(r122);
            goto L24
        L29:
            if (StringsKt.equals(r43, "domain", true) == false) goto L33;
            r15 = parseDomain(r122);     // Catch: Throwable -> L85
            r18 = false;
            goto L44
        L33:
            if (StringsKt.equals(r43, "path", true) == false) goto L36;
            r9 = r122;
            goto L44
        L36:
            if (StringsKt.equals(r43, "secure", true) == false) goto L39;
            r23 = true;
            goto L44
        L39:
            if (StringsKt.equals(r43, "httponly", true) == false) goto L42;
            r10 = true;
            goto L44
        L42:
            if (StringsKt.equals(r43, "samesite", true) == false) goto L44;
            r20 = r122;
            goto L44
        L20:
            r122 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L19
        L45:
            Cookie r262 = r3;
            if (r13 != Long.MIN_VALUE) goto L49;
            long r112 = Long.MIN_VALUE;
        L62:
            String r1 = r33.host();
            if (r15 != null) goto L66;
            r15 = r1;
        L69:
            if (r1.length() != r15.length()) goto L71;
        L73:
            String r0 = "/";
            if (r9 != null) goto L76;
        L79:
            String r24 = r33.encodedPath();
            int r14 = StringsKt.lastIndexOf$default(r24, '/', 0, false, 6, null);
            if (r14 == 0) goto L82;
            r0 = r24.substring(0, r14);
        L82:
            r9 = r0;
        L78:
            String r132 = r9;
            String r92 = r15;
            boolean r152 = r10;
            return new Cookie(r8, r22, r112, r92, r132, r23, r152, r19, r18, r20, null);
        L76:
            if (StringsKt.startsWith$default(r9, "/", false, 2, r262) == true) goto L78;
        L71:
            if (PublicSuffixDatabase.Companion.get().getEffectiveTldPlusOne(r15) != null) goto L73;
            return r262;
        L66:
            if (domainMatch(r1, r15) == true) goto L69;
            return r262;
        L49:
            if (r13 != (-1)) goto L51;
            r112 = r21;
            goto L62
        L51:
            if (r13 > 9223372036854775L) goto L53;
            long r133 = r13 * 1000;
        L54:
            long r134 = r31 + r133;
            if (r134 >= r31) goto L57;
        L60:
            r112 = 253402300799999L;
            goto L62
        L57:
            if (r134 > DateFormattingKt.MAX_DATE) goto L60;
            r112 = r134;
            goto L62
        L53:
            r133 = LongCompanionObject.MAX_VALUE;
            goto L54
        }

        @JvmStatic
        public final List<Cookie> parseAll(HttpUrl r6, Headers r7) {
            List<String> r72 = r7.values("Set-Cookie");
            int r0 = r72.size();
            List<Cookie> r1 = null;
            int r2 = 0;
            ArrayList r3 = null;
        L3:
            if (r2 >= r0) goto L11;
            Cookie r4 = parse(r6, r72.get(r2));
            if (r4 == null) goto L10;
            if (r3 != null) goto L9;
            r3 = new ArrayList();
        L9:
            r3.add(r4);
        L10:
            r2 = r2 + 1;
            goto L3
        L11:
            if (r3 == null) goto L13;
            r1 = Collections.unmodifiableList(r3);
        L13:
            if (r1 == null) goto L15;
            return r1;
        L15:
            return CollectionsKt.emptyList();
        }
    }

    static {
        Companion = new Companion(null);
        YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
        MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
        DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
        TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    }

    private Cookie(String r1, String r2, long r3, String r5, String r6, boolean r7, boolean r8, boolean r9, boolean r10, String r11) {
        this.name = r1;
        this.value = r2;
        this.expiresAt = r3;
        this.domain = r5;
        this.path = r6;
        this.secure = r7;
        this.httpOnly = r8;
        this.persistent = r9;
        this.hostOnly = r10;
        this.sameSite = r11;
    }

    public /* synthetic */ Cookie(String r1, String r2, long r3, String r5, String r6, boolean r7, boolean r8, boolean r9, boolean r10, String r11, DefaultConstructorMarker r12) {
        this(r1, r2, r3, r5, r6, r7, r8, r9, r10, r11);
    }

    public static final /* synthetic */ Pattern access$getDAY_OF_MONTH_PATTERN$cp() {
        return DAY_OF_MONTH_PATTERN;
    }

    public static final /* synthetic */ Pattern access$getMONTH_PATTERN$cp() {
        return MONTH_PATTERN;
    }

    public static final /* synthetic */ Pattern access$getTIME_PATTERN$cp() {
        return TIME_PATTERN;
    }

    public static final /* synthetic */ Pattern access$getYEAR_PATTERN$cp() {
        return YEAR_PATTERN;
    }

    @JvmStatic
    public static final Cookie parse(HttpUrl r1, String r2) {
        return Companion.parse(r1, r2);
    }

    @JvmStatic
    public static final List<Cookie> parseAll(HttpUrl r1, Headers r2) {
        return Companion.parseAll(r1, r2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "domain", imports = {}))
    @JvmName(name = "-deprecated_domain")
    /* renamed from: -deprecated_domain, reason: not valid java name */
    public final String m3632deprecated_domain() {
        return this.domain;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "expiresAt", imports = {}))
    @JvmName(name = "-deprecated_expiresAt")
    /* renamed from: -deprecated_expiresAt, reason: not valid java name */
    public final long m3633deprecated_expiresAt() {
        return this.expiresAt;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "hostOnly", imports = {}))
    @JvmName(name = "-deprecated_hostOnly")
    /* renamed from: -deprecated_hostOnly, reason: not valid java name */
    public final boolean m3634deprecated_hostOnly() {
        return this.hostOnly;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "httpOnly", imports = {}))
    @JvmName(name = "-deprecated_httpOnly")
    /* renamed from: -deprecated_httpOnly, reason: not valid java name */
    public final boolean m3635deprecated_httpOnly() {
        return this.httpOnly;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "name", imports = {}))
    @JvmName(name = "-deprecated_name")
    /* renamed from: -deprecated_name, reason: not valid java name */
    public final String m3636deprecated_name() {
        return this.name;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "path", imports = {}))
    @JvmName(name = "-deprecated_path")
    /* renamed from: -deprecated_path, reason: not valid java name */
    public final String m3637deprecated_path() {
        return this.path;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "persistent", imports = {}))
    @JvmName(name = "-deprecated_persistent")
    /* renamed from: -deprecated_persistent, reason: not valid java name */
    public final boolean m3638deprecated_persistent() {
        return this.persistent;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "secure", imports = {}))
    @JvmName(name = "-deprecated_secure")
    /* renamed from: -deprecated_secure, reason: not valid java name */
    public final boolean m3639deprecated_secure() {
        return this.secure;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "value", imports = {}))
    @JvmName(name = "-deprecated_value")
    /* renamed from: -deprecated_value, reason: not valid java name */
    public final String m3640deprecated_value() {
        return this.value;
    }

    @JvmName(name = "domain")
    public final String domain() {
        return this.domain;
    }

    public boolean equals(Object r5) {
        if ((r5 instanceof Cookie) == false) goto L26;
        Cookie r52 = (Cookie) r5;
        if (Intrinsics.areEqual(r52.name, this.name) == true) goto L7;
        return false;
    L7:
        if (Intrinsics.areEqual(r52.value, this.value) == true) goto L9;
        return false;
    L9:
        if (r52.expiresAt == this.expiresAt) goto L11;
        return false;
    L11:
        if (Intrinsics.areEqual(r52.domain, this.domain) == true) goto L13;
        return false;
    L13:
        if (Intrinsics.areEqual(r52.path, this.path) == true) goto L15;
        return false;
    L15:
        if (r52.secure == this.secure) goto L17;
        return false;
    L17:
        if (r52.httpOnly == this.httpOnly) goto L19;
        return false;
    L19:
        if (r52.persistent == this.persistent) goto L21;
        return false;
    L21:
        if (r52.hostOnly == this.hostOnly) goto L23;
        return false;
    L23:
        if (Intrinsics.areEqual(r52.sameSite, this.sameSite) == false) goto L37;
        return true;
    L37:
        return false;
    L26:
        return false;
    }

    @JvmName(name = "expiresAt")
    public final long expiresAt() {
        return this.expiresAt;
    }

    @IgnoreJRERequirement
    public int hashCode() {
        int r1 = (((((((((((((((((527 + this.name.hashCode()) * 31) + this.value.hashCode()) * 31) + Long.hashCode(this.expiresAt)) * 31) + this.domain.hashCode()) * 31) + this.path.hashCode()) * 31) + Boolean.hashCode(this.secure)) * 31) + Boolean.hashCode(this.httpOnly)) * 31) + Boolean.hashCode(this.persistent)) * 31) + Boolean.hashCode(this.hostOnly)) * 31;
        String r4 = this.sameSite;
        if (r4 == null) goto L5;
        int r42 = r4.hashCode();
    L7:
        return r1 + r42;
    L5:
        r42 = 0;
        goto L7
    }

    @JvmName(name = "hostOnly")
    public final boolean hostOnly() {
        return this.hostOnly;
    }

    @JvmName(name = "httpOnly")
    public final boolean httpOnly() {
        return this.httpOnly;
    }

    public final boolean matches(HttpUrl r4) {
        if (this.hostOnly == false) goto L5;
        boolean r0 = Intrinsics.areEqual(r4.host(), this.domain);
    L7:
        if (r0 == true) goto L10;
        return false;
    L10:
        if (Companion.access$pathMatch(Companion, r4, this.path) == true) goto L13;
        return false;
    L13:
        if (this.secure == true) goto L15;
        return true;
    L15:
        if (r4.isHttps() == true) goto L20;
        return false;
    L20:
        return true;
    L5:
        r0 = Companion.access$domainMatch(Companion, r4.host(), this.domain);
        goto L7
    }

    @JvmName(name = "name")
    public final String name() {
        return this.name;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    @JvmName(name = "path")
    public final String path() {
        return this.path;
    }

    @JvmName(name = "persistent")
    public final boolean persistent() {
        return this.persistent;
    }

    @JvmName(name = "sameSite")
    public final String sameSite() {
        return this.sameSite;
    }

    @JvmName(name = "secure")
    public final boolean secure() {
        return this.secure;
    }

    public String toString() {
        return toString$okhttp(false);
    }

    public final String toString$okhttp(boolean r6) {
        StringBuilder r0 = new StringBuilder();
        r0.append(this.name);
        r0.append(SignatureVisitor.INSTANCEOF);
        r0.append(this.value);
        if (this.persistent == false) goto L9;
        if (this.expiresAt != Long.MIN_VALUE) goto L7;
        r0.append("; max-age=0");
        goto L9
    L7:
        r0.append("; expires=");
        r0.append(DateFormattingKt.toHttpDateString(new Date(this.expiresAt)));
    L9:
        if (this.hostOnly == true) goto L14;
        r0.append("; domain=");
        if (r6 == false) goto L13;
        r0.append(".");
    L13:
        r0.append(this.domain);
    L14:
        r0.append("; path=");
        r0.append(this.path);
        if (this.secure == false) goto L18;
        r0.append("; secure");
    L18:
        if (this.httpOnly == false) goto L21;
        r0.append("; httponly");
    L21:
        if (this.sameSite == null) goto L24;
        r0.append("; samesite=");
        r0.append(this.sameSite);
    L24:
        return r0.toString();
    }

    @JvmName(name = "value")
    public final String value() {
        return this.value;
    }
}
