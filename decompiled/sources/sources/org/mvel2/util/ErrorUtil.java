package org.mvel2.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.url._UrlKt;
import org.mvel2.CompileException;
import org.mvel2.ErrorDetail;

/* loaded from: classes5.dex */
public class ErrorUtil {
    private static final Logger LOG = null;

    static {
        LOG = Logger.getLogger(ErrorUtil.class.getName());
    }

    public ErrorUtil() {
    }

    public static CompileException rewriteIfNeeded(CompileException r2, char[] r3, int r4) {
        if (r2.getExpr() != null) goto L6;
    L14:
        return r2;
    L6:
        if (r3 == r2.getExpr()) goto L14;
        if (r2.getExpr().length > r2.getCursor()) goto L15;
        r2.setCursor(r2.getExpr().length - 1);
    L15:
        String r42 = new String(r2.getExpr()).substring(r2.getCursor());     // Catch: Throwable -> L12
        r2.setExpr(r3);     // Catch: Throwable -> L12
        String r0 = new String(r3);     // Catch: Throwable -> L12
        r2.setCursor(r0.substring(r0.indexOf(new String(r2.getExpr()))).indexOf(r42));     // Catch: Throwable -> L12
        return r2;
    L12:
        th = move-exception;
        LOG.log(Level.WARNING, _UrlKt.FRAGMENT_ENCODE_SET, th);
        goto L14
    }

    public static ErrorDetail rewriteIfNeeded(ErrorDetail r2, char[] r3, int r4) {
        if (r3 == r2.getExpr()) goto L5;
        String r0 = new String(r2.getExpr()).substring(r2.getCursor());
        r2.setExpr(r3);
        r2.setCursor(r4 + new String(r3).substring(r4).indexOf(r0));
    L5:
        return r2;
    }
}
