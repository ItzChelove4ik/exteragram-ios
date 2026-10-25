package com.exteragram.messenger.preferences.utils;

import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.graphics.PathParser;
import com.exteragram.messenger.ExteraConfig;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0003¢\u0006\u0004\b\u000e\u0010\u0003J\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0014\u0010\u0013J'\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u0006¢\u0006\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/exteragram/messenger/preferences/utils/IconShapeHelper;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/graphics/Path;", "path", _UrlKt.FRAGMENT_ENCODE_SET, "width", "height", "resizePath", "(Landroid/graphics/Path;FF)Landroid/graphics/Path;", "getDefaultPath", "()Landroid/graphics/Path;", _UrlKt.FRAGMENT_ENCODE_SET, "initSystemPathCache", "Landroid/graphics/RectF;", "bounds", _UrlKt.FRAGMENT_ENCODE_SET, "calculateIfShouldUseRoundedRect", "(Landroid/graphics/Path;Landroid/graphics/RectF;)Z", "hasSharpCorners", "cornerRadius", "getFinalIconShapePath", "(FFF)Landroid/graphics/Path;", "cachedSystemPath", "Landroid/graphics/Path;", "isSystemPathSquare", "Z", "cacheInitialized", "scratchRect", "Landroid/graphics/RectF;", "Landroid/graphics/Matrix;", "scratchMatrix", "Landroid/graphics/Matrix;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class IconShapeHelper {
    public static final IconShapeHelper INSTANCE = null;
    private static boolean cacheInitialized;
    private static Path cachedSystemPath;
    private static boolean isSystemPathSquare;
    private static final Matrix scratchMatrix = null;
    private static final RectF scratchRect = null;

    static {
        INSTANCE = new IconShapeHelper();
        scratchRect = new RectF();
        scratchMatrix = new Matrix();
    }

    private IconShapeHelper() {
    }

    private final boolean calculateIfShouldUseRoundedRect(Path r6, RectF r7) {
        if (r6.isEmpty() == false) goto L6;
        return false;
    L6:
        Region r0 = new Region((int) r7.left, (int) r7.top, (int) r7.right, (int) r7.bottom);
        Region r1 = new Region();
        r1.setPath(r6, r0);
        if (r1.isRect() == false) goto L11;
        return true;
    L11:
        return hasSharpCorners(r6, r7);
    }

    private final Path getDefaultPath() {
        return PathParser.createPathFromPathData("M50,0A50,50,0,0,1,50,100A50,50,0,0,1,50,0");
    }

    private final boolean hasSharpCorners(Path r9, RectF r10) {
        if (r10.width() > 0.0f) goto L5;
    L16:
        return false;
    L5:
        if (r10.height() <= 0.0f) goto L16;
        int r8 = Math.max(3, (int) (Math.min(r10.width(), r10.height()) * 0.1f));
        Region r0 = new Region();
        r0.setPath(r9, new Region((int) r10.left, (int) r10.top, (int) r10.right, (int) r10.bottom));
        float r2 = r10.left;
        float r4 = r10.top;
        Rect r92 = new Rect((int) r2, (int) r4, ((int) r2) + r8, ((int) r4) + r8);
        float r3 = r10.right;
        float r5 = r10.top;
        Rect r22 = new Rect(((int) r3) - r8, (int) r5, (int) r3, ((int) r5) + r8);
        float r42 = r10.right;
        float r6 = r10.bottom;
        Rect r32 = new Rect(((int) r42) - r8, ((int) r6) - r8, (int) r42, (int) r6);
        float r52 = r10.left;
        float r102 = r10.bottom;
        Rect[] r82 = {r92, r22, r32, new Rect((int) r52, ((int) r102) - r8, ((int) r52) + r8, (int) r102)};
        Region r93 = new Region();
        int r103 = 0;
    L9:
        if (r103 >= 4) goto L14;
        Rect r23 = r82[r103];
        r93.set(r0);
        if (r93.op(r23, Region.Op.INTERSECT) == false) goto L12;
        r103 = r103 + 1;
        goto L9
    L12:
        return false;
    L14:
        return true;
    }

    private final void initSystemPathCache() {
        ColorDrawable r3 = new ColorDrawable(0);     // Catch: Throwable -> L8 Exception -> L10
        IconShapeHelper$$ExternalSyntheticApiModelOutline1.m();     // Catch: Throwable -> L8 Exception -> L10
        Path r32 = IconShapeHelper$$ExternalSyntheticApiModelOutline0.m(r3, r3).getIconMask();     // Catch: Throwable -> L8 Exception -> L10
        if (r32 != null) goto L6;
    L12:
        Path r4 = null;
    L13:
        if (r4 != null) goto L19;
        Resources r33 = Resources.getSystem();     // Catch: Throwable -> L8 Exception -> L10
        int r5 = r33.getIdentifier("config_icon_mask", "string", "android");     // Catch: Throwable -> L8 Exception -> L10
        if (r5 == 0) goto L19;
        String r34 = r33.getString(r5);     // Catch: Throwable -> L8 Exception -> L10
        if (TextUtils.isEmpty(r34) == true) goto L19;
        r4 = PathParser.createPathFromPathData(r34);     // Catch: Throwable -> L8 Exception -> L10
    L19:
        if (r4 != null) goto L21;
    L23:
        cachedSystemPath = null;     // Catch: Throwable -> L8 Exception -> L10
        isSystemPathSquare = false;     // Catch: Throwable -> L8 Exception -> L10
    L24:
        cacheInitialized = true;
        return;
    L21:
        if (r4.isEmpty() == true) goto L23;
        cachedSystemPath = r4;     // Catch: Throwable -> L8 Exception -> L10
        RectF r35 = new RectF();     // Catch: Throwable -> L8 Exception -> L10
        r4.computeBounds(r35, true);     // Catch: Throwable -> L8 Exception -> L10
        isSystemPathSquare = calculateIfShouldUseRoundedRect(r4, r35);     // Catch: Throwable -> L8 Exception -> L10
        goto L24
    L6:
        if (r32.isEmpty() == true) goto L12;
        r4 = new Path(r32);     // Catch: Throwable -> L8 Exception -> L10
    L8:
        th = move-exception;
        cacheInitialized = true;
        throw th;
    L10:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L8
        cachedSystemPath = null;     // Catch: Throwable -> L8
        isSystemPathSquare = false;     // Catch: Throwable -> L8
        cacheInitialized = true;
    }

    private final Path resizePath(Path r5, float r6, float r7) {
        Path r4 = new Path();
        if (r5 != null) goto L5;
    L17:
        return r4;
    L5:
        if (r5.isEmpty() == true) goto L17;
        if (r6 <= 0.0f) goto L17;
        if (r7 <= 0.0f) goto L17;
        RectF r1 = scratchRect;
        r5.computeBounds(r1, true);
        if (r1.width() <= 0.0f) goto L17;
        if (r1.height() <= 0.0f) goto L17;
        Matrix r2 = scratchMatrix;
        r2.reset();
        r2.setRectToRect(r1, new RectF(0.0f, 0.0f, r6, r7), Matrix.ScaleToFit.FILL);
        r5.transform(r2, r4);
        goto L17
    }

    public final Path getFinalIconShapePath(float r5, float r6, float r7) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
    L7:
        boolean r0 = false;
    L8:
        if (r0 == true) goto L10;
    L12:
        if (r0 == false) goto L15;
        Path r1 = cachedSystemPath;
        if (r1 == null) goto L15;
    L16:
        float r52 = AndroidUtilities.dpf2(r5);
        float r62 = AndroidUtilities.dpf2(r6);
        float r72 = AndroidUtilities.dpf2(r7);
        if (r72 <= 0.0f) goto L24;
        if (r0 == false) goto L24;
        if (isSystemPathSquare == false) goto L24;
        Path r4 = new Path();
        RectF r02 = scratchRect;
        r02.set(0.0f, 0.0f, r52, r62);
        r4.addRoundRect(r02, r72, r72, Path.Direction.CW);
        return r4;
    L24:
        return resizePath(r1, r52, r62);
    L15:
        r1 = getDefaultPath();
        goto L16
    L10:
        if (cacheInitialized == true) goto L12;
        initSystemPathCache();
        goto L12
    L5:
        if (ExteraConfig.getUseSystemIconShape() == false) goto L7;
        r0 = true;
        goto L8
    }
}
