package org.telegram.utils.proxy;

import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import j$.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes6.dex */
public final class ProxySettings {
    public static final ProxySettings EMPTY = null;
    private static final Pattern WEB_PROXY_MARKED_SECRET_PATTERN = null;
    private static final Pattern WEB_PROXY_PATH_PATTERN = null;
    private final String address;
    private final String password;
    private final int port;
    private final String secret;
    private final Type type;
    private final String user;

    /* renamed from: org.telegram.utils.proxy.ProxySettings$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$utils$proxy$ProxySettings$Type = null;

        static {
            int[] r0 = new int[Type.values().length];
            $SwitchMap$org$telegram$utils$proxy$ProxySettings$Type = r0;
            r0[Type.MTPROTO.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$org$telegram$utils$proxy$ProxySettings$Type[Type.WEB.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$org$telegram$utils$proxy$ProxySettings$Type[Type.SOCKS5.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public static final class Builder {
        private String address;
        private String password;
        private int port;
        private String secret;
        private Type type;
        private String user;

        /* renamed from: -$$Nest$fgetaddress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m22236$$Nest$fgetaddress(Builder r0) {
            return r0.address;
        }

        /* renamed from: -$$Nest$fgetpassword, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m22237$$Nest$fgetpassword(Builder r0) {
            return r0.password;
        }

        /* renamed from: -$$Nest$fgetport, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m22238$$Nest$fgetport(Builder r0) {
            return r0.port;
        }

        /* renamed from: -$$Nest$fgetsecret, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m22239$$Nest$fgetsecret(Builder r0) {
            return r0.secret;
        }

        /* renamed from: -$$Nest$fgettype, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Type m22240$$Nest$fgettype(Builder r0) {
            return r0.type;
        }

        /* renamed from: -$$Nest$fgetuser, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m22241$$Nest$fgetuser(Builder r0) {
            return r0.user;
        }

        private Builder() {
            this.type = Type.SOCKS5;
            this.address = _UrlKt.FRAGMENT_ENCODE_SET;
            this.user = _UrlKt.FRAGMENT_ENCODE_SET;
            this.password = _UrlKt.FRAGMENT_ENCODE_SET;
            this.secret = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public /* synthetic */ Builder(ProxySettingsIA r1) {
            this();
        }

        public ProxySettings build() {
            if (this.type != Type.WEB) goto L13;
            String r0 = ProxySettings.m22235$$Nest$smnormalizeWebAddress(this.address);
            this.address = r0;
            boolean r02 = ProxySettings.m22234$$Nest$smhasWebProxyPath(r0);
            String r1 = this.secret;
            if (r02 == false) goto L11;
            String r03 = ProxySettings.m22233$$Nest$smdecodeMarkedWebProxySecret(r1);
            if (r03 != null) goto L10;
            r03 = this.secret.toLowerCase(Locale.US);
        L10:
            this.secret = r03;
            goto L13
        L11:
            this.secret = r1.toLowerCase(Locale.US);
        L13:
            return new ProxySettings(this, null);
        }

        public Builder setAddress(String r1) {
            if (r1 != null) goto L5;
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            this.address = r1;
            return this;
        }

        public Builder setPassword(String r1) {
            if (r1 != null) goto L5;
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            this.password = r1;
            return this;
        }

        public Builder setPort(int r1) {
            this.port = r1;
            return this;
        }

        public Builder setSecret(String r1) {
            if (r1 != null) goto L5;
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            this.secret = r1;
            return this;
        }

        public Builder setType(Type r1) {
            if (r1 != null) goto L5;
            r1 = Type.SOCKS5;
        L5:
            this.type = r1;
            return this;
        }

        public Builder setUser(String r1) {
            if (r1 != null) goto L5;
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            this.user = r1;
            return this;
        }
    }

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type MTPROTO = null;
        public static final Type SOCKS5 = null;
        public static final Type WEB = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{SOCKS5, MTPROTO, WEB};
        }

        static {
            SOCKS5 = new Type("SOCKS5", 0);
            MTPROTO = new Type("MTPROTO", 1);
            WEB = new Type("WEB", 2);
            $VALUES = $values();
        }

        Type(String r1, int r2) {
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    /* renamed from: -$$Nest$smdecodeMarkedWebProxySecret, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m22233$$Nest$smdecodeMarkedWebProxySecret(String r0) {
        return decodeMarkedWebProxySecret(r0);
    }

    /* renamed from: -$$Nest$smhasWebProxyPath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22234$$Nest$smhasWebProxyPath(String r0) {
        return hasWebProxyPath(r0);
    }

    /* renamed from: -$$Nest$smnormalizeWebAddress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m22235$$Nest$smnormalizeWebAddress(String r0) {
        return normalizeWebAddress(r0);
    }

    static {
        WEB_PROXY_PATH_PATTERN = Pattern.compile("[A-Za-z0-9][A-Za-z0-9_-]*(/[A-Za-z0-9][A-Za-z0-9_-]*)*");
        WEB_PROXY_MARKED_SECRET_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");
        EMPTY = builder().build();
    }

    private ProxySettings(Builder r4) {
        Type r0 = Builder.m22240$$Nest$fgettype(r4);
        this.type = r0;
        this.address = Builder.m22236$$Nest$fgetaddress(r4);
        if (r0 != Type.WEB) goto L7;
        this.secret = Builder.m22239$$Nest$fgetsecret(r4);
        this.port = 0;
        this.user = _UrlKt.FRAGMENT_ENCODE_SET;
        this.password = _UrlKt.FRAGMENT_ENCODE_SET;
        return;
    L7:
        if (r0 != Type.MTPROTO) goto L11;
        this.secret = Builder.m22239$$Nest$fgetsecret(r4);
        this.port = Builder.m22238$$Nest$fgetport(r4);
        this.user = _UrlKt.FRAGMENT_ENCODE_SET;
        this.password = _UrlKt.FRAGMENT_ENCODE_SET;
        return;
    L11:
        if (r0 != Type.SOCKS5) goto L14;
        this.secret = _UrlKt.FRAGMENT_ENCODE_SET;
        this.port = Builder.m22238$$Nest$fgetport(r4);
        this.user = Builder.m22241$$Nest$fgetuser(r4);
        this.password = Builder.m22237$$Nest$fgetpassword(r4);
        return;
    L14:
        Segment$$ExternalSyntheticBUOutline0.m();
        throw null;
    }

    public /* synthetic */ ProxySettings(Builder r1, ProxySettingsIA r2) {
        this(r1);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    private static String bytesToHex(byte[] r6, int r7) {
        char[] r0 = new char[(r6.length - r7) * 2];
        char[] r1 = "0123456789abcdef".toCharArray();
        int r2 = r7;
    L4:
        if (r2 >= r6.length) goto L7;
        byte r3 = r6[r2];
        int r5 = (r2 - r7) * 2;
        r0[r5] = r1[(r3 & UByte.MAX_VALUE) >>> 4];
        r0[r5 + 1] = r1[r3 & 15];
        r2 = r2 + 1;
        goto L4
    L7:
        return new String(r0);
    }

    private static String decodeMarkedWebProxySecret(String r5) {
        if (TextUtils.isEmpty(r5) == false) goto L5;
    L19:
        return null;
    L5:
        if (WEB_PROXY_MARKED_SECRET_PATTERN.matcher(r5).matches() == false) goto L19;
        byte[] r2 = Base64.decode(r5, 11);     // Catch: IllegalArgumentException -> L20
        if (r2.length < 17) goto L19;
        if ((r2[0] & UByte.MAX_VALUE) != 112) goto L19;
        String r3 = bytesToHex(r2, 1);     // Catch: IllegalArgumentException -> L20
        if (isValidWebProxySecret(r3) == true) goto L16;
        return null;
    L16:
        if (Base64.encodeToString(r2, 11).equals(r5) == false) goto L19;
        return r3;
    }

    private static String encodeMarkedWebProxySecret(String r4) {
        if (isValidWebProxySecret(r4) == true) goto L5;
        return r4;
    L5:
        byte[] r42 = hexToBytes(r4);
        byte[] r0 = new byte[r42.length + 1];
        r0[0] = 112;
        System.arraycopy(r42, 0, r0, 1, r42.length);
        return Base64.encodeToString(r0, 11);
    }

    public static ProxySettings fromSharedPreferences(SharedPreferences r7) {
        String r0 = r7.getString("proxy_ip", _UrlKt.FRAGMENT_ENCODE_SET);
        String r2 = r7.getString("proxy_user", _UrlKt.FRAGMENT_ENCODE_SET);
        String r3 = r7.getString("proxy_pass", _UrlKt.FRAGMENT_ENCODE_SET);
        String r1 = r7.getString("proxy_secret", _UrlKt.FRAGMENT_ENCODE_SET);
        int r4 = r7.getInt("proxy_port", 1080);
        if (TextUtils.isEmpty(r1) == false) goto L5;
        Type r5 = Type.SOCKS5;
    L6:
        Type r72 = intToType(r7.getInt("proxy_type", typeToInt(r5)));
        return builder().setAddress(r0).setUser(r2).setPassword(r3).setSecret(r1).setPort(r4).setType(r72).build();
    L5:
        r5 = Type.MTPROTO;
        goto L6
    }

    public static ProxySettings fromUri(Uri r7) {
        if (r7 != null) goto L95;
        return null;
    L95:
        String r2 = r7.getScheme();     // Catch: Exception -> L92
        if (r2 != null) goto L9;
        return null;
    L9:
        if (r2.equalsIgnoreCase("http") == false) goto L11;
    L39:
        String r0 = r7.getHost();     // Catch: Exception -> L92
        if (r0 != null) goto L42;
        return null;
    L42:
        String r02 = r0.toLowerCase();     // Catch: Exception -> L92
        if (r02.equals("telegram.me") == false) goto L45;
    L49:
        String r03 = r7.getPath();     // Catch: Exception -> L92
        if (r03 != null) goto L53;
        return null;
    L53:
        if (r03.startsWith("/socks") == false) goto L57;
        Type r04 = Type.SOCKS5;     // Catch: Exception -> L92
    L55:
        Type r22 = r04;
    L62:
        String r05 = r7.getQueryParameter("server");     // Catch: Exception -> L92
        if (r05 != null) goto L66;
        r05 = r7.getQueryParameter("host");     // Catch: Exception -> L92
    L66:
        if (r22 != Type.WEB) goto L68;
    L70:
        String r3 = r7.getQueryParameter("port");     // Catch: Exception -> L92
        if (TextUtils.isEmpty(r3) == false) goto L93;
    L74:
        int r32 = 0;
    L75:
        String r4 = r7.getQueryParameter("secret");     // Catch: Exception -> L92
        Type r5 = Type.WEB;     // Catch: Exception -> L92
        if (r22 == r5) goto L78;
    L85:
        ProxySettings r72 = builder().setType(r22).setAddress(r05).setPort(r32).setUser(r7.getQueryParameter("user")).setPassword(r7.getQueryParameter("pass")).setSecret(r4).build();     // Catch: Exception -> L92
        if (r22 != r5) goto L90;
        if (r72.isValid() == true) goto L90;
        return null;
    L90:
        return r72;
    L78:
        if (hasWebProxyPath(r05) == false) goto L83;
        r4 = decodeMarkedWebProxySecret(r4);     // Catch: Exception -> L92
        if (r4 != null) goto L85;
        return null;
    L83:
        if (isValidWebProxySecret(r4) == true) goto L85;
        return null;
    L93:
        r32 = Integer.parseInt(r3);     // Catch: NumberFormatException -> L91 Exception -> L92
    L68:
        if (AndroidUtilities.checkHostForPunycode(r05) == false) goto L70;
        r05 = IDN.toASCII(r05, 1);     // Catch: Exception -> L92
        goto L70
    L57:
        if (r03.startsWith("/proxy") == false) goto L60;
        r04 = Type.MTPROTO;     // Catch: Exception -> L92
        goto L55
    L60:
        if (r03.startsWith("/webproxy") == false) goto L97;
        r04 = Type.WEB;     // Catch: Exception -> L92
        goto L55
    L97:
        return null;
    L45:
        if (r02.equals("t.me") == true) goto L49;
        if (r02.equals("telegram.dog") == true) goto L49;
        return null;
    L11:
        if (r2.equalsIgnoreCase("https") == true) goto L39;
        if (r2.equalsIgnoreCase("tg") == false) goto L38;
        String r73 = r7.toString();     // Catch: Exception -> L92
        if (r73.startsWith("tg://socks") == false) goto L18;
    L33:
        r22 = Type.SOCKS5;     // Catch: Exception -> L92
    L34:
        int r33 = r73.indexOf(63);     // Catch: Exception -> L92
        if (r33 >= 0) goto L37;
        return null;
    L37:
        r7 = Uri.parse("tg://telegram.org/".concat(r73.substring(r33)));     // Catch: Exception -> L92
        goto L62
    L18:
        if (r73.startsWith("tg:socks") == true) goto L33;
        if (r73.startsWith("tg://proxy") == false) goto L23;
    L32:
        r22 = Type.MTPROTO;     // Catch: Exception -> L92
        goto L34
    L23:
        if (r73.startsWith("tg:proxy") == true) goto L32;
        if (r73.startsWith("tg://webproxy") == false) goto L28;
    L31:
        r22 = Type.WEB;     // Catch: Exception -> L92
        goto L34
    L28:
        if (r73.startsWith("tg:webproxy") == true) goto L31;
        return null;
    L38:
        return null;
    L99:
        return null;
    }

    private static boolean hasWebProxyPath(String r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.indexOf(47) < 0) goto L9;
        return true;
    L9:
        return false;
    }

    private static byte[] hexToBytes(String r6) {
        int r0 = r6.length() / 2;
        byte[] r1 = new byte[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        int r3 = r2 * 2;
        int r4 = Character.digit(r6.charAt(r3), 16) << 4;
        r1[r2] = (byte) (Character.digit(r6.charAt(r3 + 1), 16) | r4);
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    public static Type intToType(int r1) {
        if (r1 == 0) goto L14;
        if (r1 == 1) goto L12;
        if (r1 == 2) goto L10;
        return Type.SOCKS5;
    L10:
        return Type.WEB;
    L12:
        return Type.MTPROTO;
    L14:
        return Type.SOCKS5;
    }

    private static boolean isHex(String r2, int r3) {
    L3:
        if (r3 >= r2.length()) goto L9;
        if (Character.digit(r2.charAt(r3), 16) < 0) goto L6;
        r3 = r3 + 1;
        goto L3
    L6:
        return false;
    L9:
        return true;
    }

    private static boolean isValidWebAddress(String r3) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return false;
    L5:
        int r0 = r3.indexOf(47);
        if (r0 < 0) goto L8;
        String r2 = r3.substring(0, r0);
    L9:
        if (r0 < 0) goto L11;
        String r32 = r3.substring(r0 + 1);
    L13:
        if (TextUtils.isEmpty(r2) == false) goto L15;
    L27:
        return false;
    L15:
        if (r2.indexOf(58) >= 0) goto L27;
        if (r2.indexOf(63) >= 0) goto L27;
        if (r2.indexOf(35) >= 0) goto L27;
        if (r32 == null) goto L29;
        if (isValidWebProxyPath(r32) == false) goto L27;
    L29:
        return r2.equals(IDN.toASCII(r2, 3).toLowerCase(Locale.US));
    L11:
        r32 = null;
        goto L13
    L8:
        r2 = r3;
        goto L9
    }

    private static boolean isValidWebProxyPath(String r2) {
        if (r2.length() <= 128) goto L5;
        return false;
    L5:
        if (WEB_PROXY_PATH_PATTERN.matcher(r2).matches() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isValidWebProxySecret(String r9) {
        if (r9 != null) goto L6;
        return false;
    L6:
        if (r9.length() != 32) goto L10;
        return isHex(r9, 0);
    L10:
        if (r9.length() == 34) goto L12;
    L17:
        return false;
    L12:
        if (r9.regionMatches(true, 0, "dd", 0, 2) == false) goto L17;
        if (isHex(r9, 2) == false) goto L17;
        return true;
    }

    private static String normalizeWebAddress(String r4) {
        if (TextUtils.isEmpty(r4) == false) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        int r1 = r4.indexOf(47);
        if (r1 < 0) goto L9;
        String r2 = r4.substring(0, r1);
    L10:
        if (r1 < 0) goto L12;
        String r12 = r4.substring(r1 + 1);
    L14:
        if (TextUtils.isEmpty(r2) == false) goto L16;
        return r4;
    L16:
        if (r2.indexOf(58) < 0) goto L18;
        return r4;
    L18:
        if (r2.indexOf(63) < 0) goto L20;
        return r4;
    L20:
        if (r2.indexOf(35) >= 0) goto L37;
        if (r12 == null) goto L32;
        if (isValidWebProxyPath(r12) == true) goto L32;
        return r4;
    L32:
        String r22 = IDN.toASCII(r2, 3).toLowerCase(Locale.US);     // Catch: IllegalArgumentException -> L31
        if (r12 != null) goto L29;
        return r22;
    L29:
        return r22 + '/' + r12;
    L39:
        return r4;
    L37:
        return r4;
    L12:
        r12 = null;
        goto L14
    L9:
        r2 = r4;
        goto L10
    }

    public static int typeToInt(Type r1) {
        int r12 = AnonymousClass1.$SwitchMap$org$telegram$utils$proxy$ProxySettings$Type[r1.ordinal()];
        int r0 = 1;
        if (r12 == 1) goto L8;
        r0 = 2;
        if (r12 == 2) goto L8;
        return 0;
    L8:
        return r0;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ProxySettings) == true) goto L8;
        return false;
    L8:
        ProxySettings r52 = (ProxySettings) r5;
        if (this.port == r52.port) goto L11;
    L21:
        return false;
    L11:
        if (this.type != r52.type) goto L21;
        if (Objects.equals(this.address, r52.address) == false) goto L21;
        if (Objects.equals(this.user, r52.user) == false) goto L21;
        if (Objects.equals(this.password, r52.password) == false) goto L21;
        if (Objects.equals(this.secret, r52.secret) == false) goto L21;
        return true;
    }

    public String getAddress() {
        return this.address;
    }

    public String getLink() {
        int r1 = AnonymousClass1.$SwitchMap$org$telegram$utils$proxy$ProxySettings$Type[this.type.ordinal()];
        if (r1 != 1) goto L5;
        StringBuilder r12 = new StringBuilder("https://t.me/proxy?");
    L30:
        r12.append("server=");     // Catch: UnsupportedEncodingException -> L29
        r12.append(URLEncoder.encode(this.address, "UTF-8"));     // Catch: UnsupportedEncodingException -> L29
        Type r2 = this.type;     // Catch: UnsupportedEncodingException -> L29
        Type r3 = Type.WEB;     // Catch: UnsupportedEncodingException -> L29
        if (r2 == r3) goto L13;
        r12.append("&port=");     // Catch: UnsupportedEncodingException -> L29
        r12.append(this.port);     // Catch: UnsupportedEncodingException -> L29
    L13:
        if (TextUtils.isEmpty(this.user) == true) goto L16;
        r12.append("&user=");     // Catch: UnsupportedEncodingException -> L29
        r12.append(URLEncoder.encode(this.user, "UTF-8"));     // Catch: UnsupportedEncodingException -> L29
    L16:
        if (TextUtils.isEmpty(this.password) == true) goto L19;
        r12.append("&pass=");     // Catch: UnsupportedEncodingException -> L29
        r12.append(URLEncoder.encode(this.password, "UTF-8"));     // Catch: UnsupportedEncodingException -> L29
    L19:
        if (TextUtils.isEmpty(this.secret) == true) goto L28;
        if (this.type == r3) goto L23;
    L25:
        String r4 = this.secret;     // Catch: UnsupportedEncodingException -> L29
    L26:
        r12.append("&secret=");     // Catch: UnsupportedEncodingException -> L29
        r12.append(URLEncoder.encode(r4, "UTF-8"));     // Catch: UnsupportedEncodingException -> L29
        goto L28
    L23:
        if (hasWebProxyPath(this.address) == false) goto L25;
        r4 = encodeMarkedWebProxySecret(this.secret);     // Catch: UnsupportedEncodingException -> L29
    L28:
        return r12.toString();
    L5:
        if (r1 == 2) goto L7;
        r12 = new StringBuilder("https://t.me/socks?");
        goto L30
    L7:
        r12 = new StringBuilder("https://t.me/webproxy?");
        goto L30
    }

    public String getPassword() {
        return this.password;
    }

    public int getPort() {
        return this.port;
    }

    public String getSecret() {
        return this.secret;
    }

    public Type getType() {
        return this.type;
    }

    public String getUser() {
        return this.user;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.type, this.address, Integer.valueOf(this.port), this.user, this.password, this.secret});
    }

    public boolean isValid() {
        if (TextUtils.isEmpty(this.address) == false) goto L6;
        return false;
    L6:
        if (this.type != Type.WEB) goto L14;
        if (isValidWebAddress(this.address) == true) goto L10;
    L12:
        return false;
    L10:
        if (isValidWebProxySecret(this.secret) == false) goto L12;
        return true;
    L14:
        if (this.port <= 0) goto L16;
        return true;
    L16:
        return false;
    }

    public void toSharedPreferences(SharedPreferences.Editor r7) {
        r7.putInt("proxy_type", typeToInt(this.type));
        r7.putString("proxy_ip", this.address);
        int r0 = AnonymousClass1.$SwitchMap$org$telegram$utils$proxy$ProxySettings$Type[this.type.ordinal()];
        if (r0 != 1) goto L5;
        r7.putString("proxy_secret", this.secret);
        r7.putInt("proxy_port", this.port);
        r7.remove("proxy_pass");
        r7.remove("proxy_user");
        return;
    L5:
        if (r0 != 2) goto L7;
        r7.putString("proxy_secret", this.secret);
        r7.remove("proxy_port");
        r7.remove("proxy_pass");
        r7.remove("proxy_user");
        return;
    L7:
        if (r0 == 3) goto L9;
        return;
    L9:
        r7.putInt("proxy_port", this.port);
        r7.remove("proxy_secret");
        if (TextUtils.isEmpty(this.password) == false) goto L12;
        r7.remove("proxy_pass");
    L14:
        if (TextUtils.isEmpty(this.user) == false) goto L17;
        r7.remove("proxy_user");
        return;
    L17:
        r7.putString("proxy_user", this.user);
        return;
    L12:
        r7.putString("proxy_pass", this.password);
        goto L14
    }
}
