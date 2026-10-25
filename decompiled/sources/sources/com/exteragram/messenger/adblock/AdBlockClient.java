package com.exteragram.messenger.adblock;

import android.text.TextUtils;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import com.exteragram.messenger.adblock.data.BlockResult;
import com.exteragram.messenger.adblock.data.UrlCosmeticResources;
import com.exteragram.messenger.adblock.interop.AdBlock;
import com.google.android.gms.cast.MediaError;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class AdBlockClient {
    private static final IOException BLOCKED_EXCEPTION = null;

    /* renamed from: com.exteragram.messenger.adblock.AdBlockClient$1, reason: invalid class name */
    public class AnonymousClass1 extends IOException {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            monitor-enter(this);
            monitor-exit(this);
            return this;
        }
    }

    public static class BlockedInputStream extends InputStream implements InputStreamRetargetInterface {
        private BlockedInputStream() {
        }

        public /* synthetic */ BlockedInputStream(AdBlockClientIA r1) {
            this();
        }

        @Override // java.io.InputStream
        public int available() {
            throw AdBlockClient.m1005$$Nest$sfgetBLOCKED_EXCEPTION();
        }

        @Override // java.io.InputStream
        public int read() {
            throw AdBlockClient.m1005$$Nest$sfgetBLOCKED_EXCEPTION();
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream r1) {
            return DesugarInputStream.transferTo(this, r1);
        }
    }

    public static class CosmeticHide {
        private final String[] exceptions;
        private final boolean genericHide;
        private final String hideCss;
        private final String injectedScript;
        private final String url;

        public CosmeticHide(String r1, String r2, String r3, String[] r4, boolean r5) {
            this.url = r1;
            this.hideCss = r2;
            this.injectedScript = r3;
            this.exceptions = r4;
            this.genericHide = r5;
        }

        public String[] getExceptions() {
            return this.exceptions;
        }

        public String getHideCss() {
            return this.hideCss;
        }

        public String getInjectedScript() {
            return this.injectedScript;
        }

        public String getUrl() {
            return this.url;
        }

        public boolean isGenericHide() {
            return this.genericHide;
        }
    }

    /* renamed from: -$$Nest$sfgetBLOCKED_EXCEPTION, reason: not valid java name */
    public static /* bridge */ /* synthetic */ IOException m1005$$Nest$sfgetBLOCKED_EXCEPTION() {
        return BLOCKED_EXCEPTION;
    }

    static {
        BLOCKED_EXCEPTION = new AnonymousClass1("Blocked by filter");
    }

    public static WebResourceResponse createBlockedResponse(String r10, BlockResult r11) {
        String r112 = r11.getRedirect();
        if (r112 == null) goto L12;
        if (r112.startsWith("data:") == false) goto L12;
        int r0 = r112.indexOf(59);
        int r1 = r112.indexOf(44);
        if (r0 <= 5) goto L12;
        if (r1 <= r0) goto L12;
        String r4 = r112.substring(5, r0);     // Catch: IllegalArgumentException -> L17
        byte[] r113 = Base64.decode(r112.substring(r1 + 1), 0);     // Catch: IllegalArgumentException -> L17
        HashMap r8 = new HashMap();     // Catch: IllegalArgumentException -> L17
        r8.put("Content-Type", r4);     // Catch: IllegalArgumentException -> L17
        r8.put("Access-Control-Allow-Credentials", "true");     // Catch: IllegalArgumentException -> L17
        r8.put("Access-Control-Allow-Headers", "Cache-Control");     // Catch: IllegalArgumentException -> L17
        r8.put("Access-Control-Allow-Origin", "*");     // Catch: IllegalArgumentException -> L17
        return new WebResourceResponse(r4, null, 200, "OK", r8, new ByteArrayInputStream(r113));
    L12:
        if ("sub_frame".equals(r10) == false) goto L16;
        return new WebResourceResponse("text/html", "utf-8", MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, "Internal Server Error", null, null);
    L16:
        return new WebResourceResponse("text/plain", "utf-8", new BlockedInputStream(null));
    }

    private static String createHideCss(String[] r6) {
        if (r6 != null) goto L5;
    L21:
        return null;
    L5:
        if (r6.length == 0) goto L21;
        StringBuilder r1 = new StringBuilder();
        int r2 = r6.length;
        int r3 = 0;
    L8:
        if (r3 >= r2) goto L18;
        String r4 = r6[r3];
        if (TextUtils.isEmpty(r4) == true) goto L16;
        if (r4.indexOf(123) >= 0) goto L16;
        if (r4.indexOf(125) >= 0) goto L16;
        r1.append(r4);
        r1.append("{display:none!important}\n");
    L16:
        r3 = r3 + 1;
        goto L8
    L18:
        if (r1.length() <= 0) goto L21;
        return r1.toString();
    }

    public static CosmeticHide getCosmeticHide(String r6) {
        UrlCosmeticResources r0 = AdBlock.getCosmeticResources(r6);
        if (r0 != null) goto L7;
        return null;
    L7:
        return new CosmeticHide(r6, createHideCss(r0.getHideSelectors()), r0.getInjectedScript(), r0.getExceptions(), r0.isGenericHide());
    }

    public static String getHiddenSelectorsCss(CosmeticHide r1, String[] r2, String[] r3) {
        if (r2.length != 0) goto L9;
        if (r3.length != 0) goto L9;
        return null;
    L9:
        return createHideCss(AdBlock.getHiddenSelectors(r2, r3, r1.getExceptions()));
    }

    private static String getRequestExtension(String r3) {
        if (r3 != null) goto L5;
    L28:
        return null;
    L5:
        if (r3.isEmpty() == true) goto L28;
        int r1 = r3.indexOf(63);
        if (r1 <= 0) goto L10;
        r3 = r3.substring(0, r1);
    L10:
        int r12 = r3.lastIndexOf(47);
        if (r12 <= 0) goto L13;
        r3 = r3.substring(r12 + 1);
    L13:
        int r13 = r3.lastIndexOf(46);
        if (r13 <= 0) goto L26;
        if (r13 == (r3.length() - 1)) goto L26;
        String r32 = r3.substring(r13 + 1).toLowerCase(Locale.ROOT);
        if (r32.isEmpty() == false) goto L22;
        return null;
    L22:
        if (r32.length() <= 8) goto L24;
        return null;
    L24:
        return r32;
    L26:
        if (r3.endsWith("js") == false) goto L28;
        return "js";
    }

    private static String getRequestMime(String r1) {
        if ("mhtml".equals(r1) == false) goto L5;
        return "multipart/related";
    L5:
        if ("mht".equals(r1) == false) goto L8;
        return "multipart/related";
    L8:
        if ("json".equals(r1) == false) goto L11;
        return "application/json";
    L11:
        String r12 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(r1);
        if (r12 != null) goto L14;
        return "application/octet-stream";
    L14:
        if (r12.isEmpty() == true) goto L21;
        return r12;
    L21:
        return "application/octet-stream";
    }

    public static String getRequestType(WebResourceRequest r3, String r4) {
        if ("OPTIONS".equals(r3.getMethod()) == false) goto L6;
        return "beacon";
    L6:
        String r0 = r3.getUrl().toString();
        Map<String, String> r1 = r3.getRequestHeaders();
        if (r3.isForMainFrame() == false) goto L13;
        if (r0.equals(r4) == false) goto L13;
        return "main_frame";
    L13:
        if (r0.startsWith("ws") == false) goto L16;
        return "websocket";
    L16:
        if (r1 != null) goto L18;
    L21:
        if (r1 == null) goto L23;
        String r42 = r1.get("Accept");
    L25:
        if (r3.isForMainFrame() == true) goto L31;
        if (r42 == null) goto L31;
        if (r42.startsWith("text/html") == false) goto L31;
        return "sub_frame";
    L31:
        String r32 = getRequestExtension(r0);
        if ("js".equals(r32) == false) goto L36;
        return "script";
    L36:
        if ("css".equals(r32) == false) goto L40;
        return "stylesheet";
    L40:
        if ("otf".equals(r32) == false) goto L42;
        return "font";
    L42:
        if ("ttf".equals(r32) == false) goto L44;
        return "font";
    L44:
        if ("ttc".equals(r32) == false) goto L46;
        return "font";
    L46:
        if ("woff".equals(r32) == false) goto L48;
        return "font";
    L48:
        if ("woff2".equals(r32) == false) goto L51;
        return "font";
    L51:
        if ("php".equals(r32) == true) goto L57;
        String r33 = getRequestMime(r32);
        if ("application/octet-stream".equals(r33) == true) goto L57;
        return getRequestTypeFromMime(r33);
    L57:
        if (TextUtils.isEmpty(r42) == false) goto L59;
        return "other";
    L59:
        if ("*/*".equals(r42) == true) goto L70;
        int r34 = r42.indexOf(44);
        if (r34 <= 0) goto L65;
        r42 = r42.substring(0, r34).trim();
    L65:
        return getRequestTypeFromMime(r42);
    L70:
        return "other";
    L23:
        r42 = null;
        goto L25
    L18:
        if ("XMLHttpRequest".equals(r1.get("X-Requested-With")) == false) goto L21;
        return "xhr";
    }

    private static String getRequestTypeFromMime(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L6;
        return "other";
    L6:
        if ("application/javascript".equals(r2) == false) goto L8;
        return "script";
    L8:
        if ("application/x-javascript".equals(r2) == false) goto L10;
        return "script";
    L10:
        if ("text/javascript".equals(r2) == false) goto L12;
        return "script";
    L12:
        if ("application/json".equals(r2) == false) goto L15;
        return "script";
    L15:
        if ("text/css".equals(r2) == false) goto L19;
        return "stylesheet";
    L19:
        if (r2.startsWith("image/") == false) goto L23;
        return "image";
    L23:
        if (r2.startsWith("video/") == false) goto L25;
        return "media";
    L25:
        if (r2.startsWith("audio/") == false) goto L28;
        return "media";
    L28:
        if (r2.startsWith("font/") == false) goto L31;
        return "font";
    L31:
        return "other";
    }

    public static BlockResult isAdRequest(WebResourceRequest r1, String r2) {
        String r0 = getRequestType(r1, r2);
        return AdBlock.getBlockResult(r1.getUrl().toString(), r2, r0);
    }
}
