package com.exteragram.messenger.plugins.utils;

import android.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u0005H\u0007J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/plugins/utils/SdkSignature;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "PUBLIC_KEY", _UrlKt.FRAGMENT_ENCODE_SET, "PAYLOAD_PREFIX", "verify", _UrlKt.FRAGMENT_ENCODE_SET, "archive", "Ljava/io/File;", "version", "abi", "signature", "digest", "file", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSdkSignature.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SdkSignature.kt\ncom/exteragram/messenger/plugins/utils/SdkSignature\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,79:1\n492#2,12:80\n*S KotlinDebug\n*F\n+ 1 SdkSignature.kt\ncom/exteragram/messenger/plugins/utils/SdkSignature\n*L\n48#1:80,12\n*E\n"})
/* loaded from: classes4.dex */
public final class SdkSignature {
    public static final SdkSignature INSTANCE = null;
    private static final String PAYLOAD_PREFIX = "extera-pysdk";
    private static final String PUBLIC_KEY = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAER7oj20N/vwmRC/Zig0lX/xOQvh6kSkPOhuoZlIm7IYHyY0qo3YqMGVfGqjjqX4ZcsGiDy91ELUEElv3bCSfHTg==";

    static {
        INSTANCE = new SdkSignature();
    }

    private SdkSignature() {
    }

    private final String digest(File r5) {
        MessageDigest r4 = MessageDigest.getInstance("SHA-256");
        FileInputStream r0 = new FileInputStream(r5);
        byte[] r52 = new byte[1048576];     // Catch: Throwable -> L12
    L4:
        int r1 = r0.read(r52);     // Catch: Throwable -> L12
        int r2 = 0;
        if (r1 <= 0) goto L6;
        r4.update(r52, 0, r1);     // Catch: Throwable -> L12
        goto L4
    L6:
        Unit r53 = Unit.INSTANCE;     // Catch: Throwable -> L12
        CloseableKt.closeFinally(r0, null);
        byte[] r42 = r4.digest();
        StringBuilder r54 = new StringBuilder(r42.length * 2);
        int r02 = r42.length;
    L8:
        if (r2 >= r02) goto L11;
        byte r12 = r42[r2];
        StringCompanionObject r3 = StringCompanionObject.INSTANCE;
        r54.append(String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(r12)}, 1)));
        r2 = r2 + 1;
        goto L8
    L11:
        return r54.toString();
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    }

    @JvmStatic
    public static final boolean verify(File r5, String r6, String r7, String r8) {
        if (r6 != null) goto L5;
    L34:
        FileLog.e("Python SDK update is not signed");
        return false;
    L5:
        if (r6.length() == 0) goto L34;
        if (r7 == null) goto L34;
        if (r7.length() == 0) goto L34;
        if (r8 == null) goto L34;
        if (r8.length() == 0) goto L34;
        if (r5.exists() == true) goto L36;
        FileLog.e("Python SDK archive to verify does not exist");
        return false;
    L36:
        String r52 = "extera-pysdk|" + r6 + "|" + r7 + "|" + INSTANCE.digest(r5);     // Catch: Throwable -> L25
        PublicKey r0 = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(Base64.decode(PUBLIC_KEY, 0)));     // Catch: Throwable -> L25
        Signature r1 = Signature.getInstance("SHA256withECDSA");     // Catch: Throwable -> L25
        r1.initVerify(r0);     // Catch: Throwable -> L25
        r1.update(r52.getBytes(Charsets.UTF_8));     // Catch: Throwable -> L25
        StringBuilder r53 = new StringBuilder();     // Catch: Throwable -> L25
        int r02 = 0;
    L21:
        if (r02 >= r8.length()) goto L28;
        char r3 = r8.charAt(r02);     // Catch: Throwable -> L25
        if (CharsKt.isWhitespace(r3) == true) goto L27;
        r53.append(r3);     // Catch: Throwable -> L25
    L27:
        r02 = r02 + 1;     // Catch: Throwable -> L25
        goto L21
    L28:
        boolean r54 = r1.verify(Base64.decode(r53.toString(), 0));     // Catch: Throwable -> L25
        if (r54 == true) goto L31;
        FileLog.e("Python SDK signature does not match (" + r6 + ", " + r7 + ")");     // Catch: Throwable -> L25
    L31:
        return r54;
    L25:
        th = move-exception;
        FileLog.e("Failed to verify Python SDK signature", th);
        return false;
    }
}
