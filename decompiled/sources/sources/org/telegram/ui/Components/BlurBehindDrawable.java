package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;

/* loaded from: classes7.dex */
public class BlurBehindDrawable {
    private final float DOWN_SCALE;
    private boolean animateAlpha;
    private Bitmap[] backgroundBitmap;
    private Canvas[] backgroundBitmapCanvas;
    private View behindView;
    private float blurAlpha;
    BlurBackgroundTask blurBackgroundTask;
    private Canvas[] blurCanvas;
    private Bitmap[] blurredBitmapTmp;
    Paint emptyPaint;
    private boolean error;
    Paint errorBlackoutPaint;
    private float flashProgress;
    private boolean invalidate;
    private int lastH;
    private int lastW;
    private float panTranslationY;
    private View parentView;
    private boolean processingNextFrame;
    DispatchQueue queue;
    private Bitmap[] renderingBitmap;
    private Canvas[] renderingBitmapCanvas;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean show;
    private boolean skipDraw;
    private int toolbarH;
    private final int type;
    private boolean wasDraw;

    public class BlurBackgroundTask implements Runnable {
        boolean canceled;
        int height;
        final /* synthetic */ BlurBehindDrawable this$0;
        int width;

        public static /* synthetic */ void $r8$lambda$JAiAre2rmoslSSk60MHImrUfcsI(BlurBackgroundTask r0) {
            r0.lambda$run$0();
        }

        public BlurBackgroundTask(BlurBehindDrawable r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0() {
            if (this.canceled == true) goto L10;
            Bitmap[] r0 = BlurBehindDrawable.m9003$$Nest$fgetrenderingBitmap(this.this$0);
            Canvas[] r1 = BlurBehindDrawable.m9004$$Nest$fgetrenderingBitmapCanvas(this.this$0);
            BlurBehindDrawable r2 = this.this$0;
            BlurBehindDrawable.m9009$$Nest$fputrenderingBitmap(r2, BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(r2));
            BlurBehindDrawable r22 = this.this$0;
            BlurBehindDrawable.m9010$$Nest$fputrenderingBitmapCanvas(r22, BlurBehindDrawable.m9000$$Nest$fgetbackgroundBitmapCanvas(r22));
            BlurBehindDrawable.m9006$$Nest$fputbackgroundBitmap(this.this$0, r0);
            BlurBehindDrawable.m9007$$Nest$fputbackgroundBitmapCanvas(this.this$0, r1);
            BlurBehindDrawable.m9008$$Nest$fputprocessingNextFrame(this.this$0, false);
            if (BlurBehindDrawable.m9002$$Nest$fgetparentView(this.this$0) == null) goto L9;
            BlurBehindDrawable.m9002$$Nest$fgetparentView(this.this$0).invalidate();
            return;
        L9:
            return;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(this.this$0) != null) goto L5;
            BlurBehindDrawable.m9006$$Nest$fputbackgroundBitmap(this.this$0, new Bitmap[2]);
            BlurBehindDrawable.m9007$$Nest$fputbackgroundBitmapCanvas(this.this$0, new Canvas[2]);
        L5:
            int r0 = (int) (this.width / 15.0f);
            int r4 = 0;
        L6:
            if (r4 >= 2) goto L36;
            if (r4 != 0) goto L9;
            int r5 = BlurBehindDrawable.m9005$$Nest$fgettoolbarH(this.this$0);
        L10:
            int r52 = (int) (r5 / 15.0f);
            if (BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(this.this$0)[r4] != null) goto L13;
        L19:
            System.currentTimeMillis();
            if (BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(this.this$0)[r4] == null) goto L38;
        L25:
            BlurBehindDrawable r53 = this.this$0;
            if (r4 != 1) goto L28;
            BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(r53)[r4].eraseColor(BlurBehindDrawable.m9012$$Nest$mgetThemedColor(this.this$0, Theme.key_windowBackgroundWhite));
        L29:
            this.this$0.emptyPaint.setAlpha(255);
            Utilities.stackBlurBitmap(BlurBehindDrawable.m9001$$Nest$fgetblurredBitmapTmp(this.this$0)[r4], BlurBehindDrawable.m9011$$Nest$mgetBlurRadius(this.this$0));
            if (BlurBehindDrawable.m9000$$Nest$fgetbackgroundBitmapCanvas(this.this$0)[r4] == null) goto L33;
            BlurBehindDrawable.m9000$$Nest$fgetbackgroundBitmapCanvas(this.this$0)[r4].drawBitmap(BlurBehindDrawable.m9001$$Nest$fgetblurredBitmapTmp(this.this$0)[r4], 0.0f, 0.0f, this.this$0.emptyPaint);
        L33:
            if (this.canceled == true) goto L34;
            r4 = r4 + 1;
            goto L6
        L34:
            return;
        L28:
            BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(r53)[r4].eraseColor(0);
            goto L29
        L38:
            BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(this.this$0)[r4] = Bitmap.createBitmap(r0, r52, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L23
            BlurBehindDrawable.m9000$$Nest$fgetbackgroundBitmapCanvas(this.this$0)[r4] = new Canvas(BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(this.this$0)[r4]);     // Catch: Throwable -> L23
            BlurBehindDrawable.m9000$$Nest$fgetbackgroundBitmapCanvas(this.this$0)[r4].scale(r0 / BlurBehindDrawable.m9001$$Nest$fgetblurredBitmapTmp(this.this$0)[r4].getWidth(), r52 / BlurBehindDrawable.m9001$$Nest$fgetblurredBitmapTmp(this.this$0)[r4].getHeight());     // Catch: Throwable -> L23
        L23:
            th = move-exception;
            FileLog.e(th);
            goto L25
        L13:
            if (BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(this.this$0)[r4].getHeight() != r52) goto L17;
            if (BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(this.this$0)[r4].getWidth() == r0) goto L19;
        L17:
            if (BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(this.this$0)[r4] == null) goto L19;
            BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(this.this$0)[r4].recycle();
            BlurBehindDrawable.m8999$$Nest$fgetbackgroundBitmap(this.this$0)[r4] = null;
            goto L19
        L9:
            r5 = this.height;
            goto L10
        L36:
            AndroidUtilities.runOnUIThread(new BlurBehindDrawable$BlurBackgroundTask$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: $r8$lambda$JJ8-tuKaKQmn5ZgdGONKlqKf2zw, reason: not valid java name */
    public static /* synthetic */ void m8998$r8$lambda$JJ8tuKaKQmn5ZgdGONKlqKf2zw(BlurBehindDrawable r0) {
        r0.lambda$draw$1();
    }

    /* renamed from: -$$Nest$fgetbackgroundBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap[] m8999$$Nest$fgetbackgroundBitmap(BlurBehindDrawable r0) {
        return r0.backgroundBitmap;
    }

    /* renamed from: -$$Nest$fgetbackgroundBitmapCanvas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Canvas[] m9000$$Nest$fgetbackgroundBitmapCanvas(BlurBehindDrawable r0) {
        return r0.backgroundBitmapCanvas;
    }

    /* renamed from: -$$Nest$fgetblurredBitmapTmp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap[] m9001$$Nest$fgetblurredBitmapTmp(BlurBehindDrawable r0) {
        return r0.blurredBitmapTmp;
    }

    /* renamed from: -$$Nest$fgetparentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m9002$$Nest$fgetparentView(BlurBehindDrawable r0) {
        return r0.parentView;
    }

    /* renamed from: -$$Nest$fgetrenderingBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap[] m9003$$Nest$fgetrenderingBitmap(BlurBehindDrawable r0) {
        return r0.renderingBitmap;
    }

    /* renamed from: -$$Nest$fgetrenderingBitmapCanvas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Canvas[] m9004$$Nest$fgetrenderingBitmapCanvas(BlurBehindDrawable r0) {
        return r0.renderingBitmapCanvas;
    }

    /* renamed from: -$$Nest$fgettoolbarH, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9005$$Nest$fgettoolbarH(BlurBehindDrawable r0) {
        return r0.toolbarH;
    }

    /* renamed from: -$$Nest$fputbackgroundBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9006$$Nest$fputbackgroundBitmap(BlurBehindDrawable r0, Bitmap[] r1) {
        r0.backgroundBitmap = r1;
    }

    /* renamed from: -$$Nest$fputbackgroundBitmapCanvas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9007$$Nest$fputbackgroundBitmapCanvas(BlurBehindDrawable r0, Canvas[] r1) {
        r0.backgroundBitmapCanvas = r1;
    }

    /* renamed from: -$$Nest$fputprocessingNextFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9008$$Nest$fputprocessingNextFrame(BlurBehindDrawable r0, boolean r1) {
        r0.processingNextFrame = r1;
    }

    /* renamed from: -$$Nest$fputrenderingBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9009$$Nest$fputrenderingBitmap(BlurBehindDrawable r0, Bitmap[] r1) {
        r0.renderingBitmap = r1;
    }

    /* renamed from: -$$Nest$fputrenderingBitmapCanvas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9010$$Nest$fputrenderingBitmapCanvas(BlurBehindDrawable r0, Canvas[] r1) {
        r0.renderingBitmapCanvas = r1;
    }

    /* renamed from: -$$Nest$mgetBlurRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9011$$Nest$mgetBlurRadius(BlurBehindDrawable r0) {
        return r0.getBlurRadius();
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9012$$Nest$mgetThemedColor(BlurBehindDrawable r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public BlurBehindDrawable(View r3, View r4, int r5, Theme.ResourcesProvider r6) {
        this.invalidate = true;
        this.animateAlpha = true;
        this.DOWN_SCALE = 15.0f;
        this.flashProgress = 0.0f;
        this.blurBackgroundTask = new BlurBackgroundTask(this);
        this.emptyPaint = new Paint(2);
        Paint r0 = new Paint();
        this.errorBlackoutPaint = r0;
        this.type = r5;
        this.behindView = r3;
        this.parentView = r4;
        this.resourcesProvider = r6;
        r0.setColor(-16777216);
    }

    private void generateBlurredBitmaps() {
        Bitmap[] r0 = this.renderingBitmap;
        if (r0 != null) goto L6;
        r0 = new Bitmap[2];
        this.renderingBitmap = r0;
        this.renderingBitmapCanvas = new Canvas[2];
    L6:
        if (this.blurredBitmapTmp != null) goto L8;
        this.blurredBitmapTmp = new Bitmap[2];
        this.blurCanvas = new Canvas[2];
    L8:
        this.blurBackgroundTask.canceled = true;
        this.blurBackgroundTask = new BlurBackgroundTask(this);
        int r4 = 0;
    L9:
        if (r4 >= 2) goto L42;
        int r5 = this.parentView.getMeasuredHeight();
        int r6 = this.parentView.getMeasuredWidth();
        int r7 = AndroidUtilities.statusBarHeight + AndroidUtilities.dp(200.0f);
        this.toolbarH = r7;
        if (r4 == 0) goto L14;
        r7 = r5;
    L14:
        Bitmap r8 = r0[r4];
        if (r8 != null) goto L17;
    L20:
        DispatchQueue r82 = this.queue;
        if (r82 == null) goto L23;
        r82.cleanupQueue();
    L23:
        Bitmap[] r83 = this.blurredBitmapTmp;
        int r62 = (int) (r6 / 15.0f);
        Bitmap.Config r10 = Bitmap.Config.ARGB_8888;
        r83[r4] = Bitmap.createBitmap(r62, (int) (r7 / 15.0f), r10);
        if (r4 != 1) goto L26;
        this.blurredBitmapTmp[r4].eraseColor(getThemedColor(Theme.key_windowBackgroundWhite));
    L26:
        this.blurCanvas[r4] = new Canvas(this.blurredBitmapTmp[r4]);
        if (r4 != 0) goto L29;
        r5 = this.toolbarH;
    L29:
        this.renderingBitmap[r4] = Bitmap.createBitmap(r62, (int) (r5 / 15.0f), r10);
        this.renderingBitmapCanvas[r4] = new Canvas(this.renderingBitmap[r4]);
        this.renderingBitmapCanvas[r4].scale(this.renderingBitmap[r4].getWidth() / this.blurredBitmapTmp[r4].getWidth(), this.renderingBitmap[r4].getHeight() / this.blurredBitmapTmp[r4].getHeight());
        this.blurCanvas[r4].save();
        this.blurCanvas[r4].scale(0.06666667f, 0.06666667f, 0.0f, 0.0f);
        Drawable r52 = this.behindView.getBackground();
        if (r52 != null) goto L32;
        r52 = getBackgroundDrawable();
    L32:
        this.behindView.setTag(67108867, Integer.valueOf(r4));
        if (r4 != 0) goto L35;
        this.blurCanvas[r4].translate(0.0f, -this.panTranslationY);
        this.behindView.draw(this.blurCanvas[r4]);
    L35:
        if (r4 != 1) goto L37;
        Rect r63 = r52.getBounds();
        r52.setBounds(0, 0, this.behindView.getMeasuredWidth(), this.behindView.getMeasuredHeight());
        r52.draw(this.blurCanvas[r4]);
        r52.setBounds(r63);
        this.behindView.draw(this.blurCanvas[r4]);
    L37:
        this.behindView.setTag(67108867, null);
        this.blurCanvas[r4].restore();
        Utilities.stackBlurBitmap(this.blurredBitmapTmp[r4], getBlurRadius());
        this.emptyPaint.setAlpha(255);
        if (r4 != 1) goto L40;
        this.renderingBitmap[r4].eraseColor(getThemedColor(Theme.key_windowBackgroundWhite));
    L40:
        this.renderingBitmapCanvas[r4].drawBitmap(this.blurredBitmapTmp[r4], 0.0f, 0.0f, this.emptyPaint);
    L41:
        r4 = r4 + 1;
        goto L9
    L17:
        if (r8.getHeight() != r7) goto L20;
        if (r0[r4].getWidth() == this.parentView.getMeasuredWidth()) goto L41;
    }

    private Drawable getBackgroundDrawable() {
        Theme.ResourcesProvider r1 = this.resourcesProvider;
        if ((r1 instanceof ChatActivity.ThemeDelegate) == false) goto L7;
        return ((ChatActivity.ThemeDelegate) r1).getWallpaperDrawable();
    L7:
        return Theme.getCachedWallpaperNonBlocking();
    }

    private int getBlurRadius() {
        return 15;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$draw$1() {
        this.error = true;
        this.parentView.invalidate();
    }

    public void checkSizes() {
        if (this.renderingBitmap != null) goto L5;
        return;
    L5:
        if (this.parentView.getMeasuredHeight() != 0) goto L7;
        return;
    L7:
        if (this.parentView.getMeasuredWidth() == 0) goto L13;
        generateBlurredBitmaps();
        this.lastH = this.parentView.getMeasuredHeight();
        this.lastW = this.parentView.getMeasuredWidth();
        return;
    }

    public void draw(Canvas r15) {
        View r0 = this.parentView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getMeasuredHeight() != 0) goto L10;
        if (this.parentView.getMeasuredWidth() != 0) goto L10;
        return;
    L10:
        if (this.type == 1) goto L12;
    L16:
        Bitmap[] r02 = this.renderingBitmap;
        if (r02 != null) goto L21;
        if (this.error == true) goto L21;
    L38:
        if (this.animateAlpha == false) goto L40;
        float r5 = this.blurAlpha;
    L41:
        if (r02 != null) goto L47;
        if (this.error == false) goto L47;
        this.errorBlackoutPaint.setAlpha((int) (r5 * 50.0f));
        r15.drawPaint(this.errorBlackoutPaint);
        return;
    L47:
        if (r5 != 1.0f) goto L49;
        r15.save();
        Canvas r7 = r15;
    L50:
        if (r02 == null) goto L58;
        this.emptyPaint.setAlpha((int) (r5 * 255.0f));
        if (this.type != 1) goto L54;
        r7.translate(0.0f, this.panTranslationY);
    L54:
        r7.save();
        r7.scale(this.parentView.getMeasuredWidth() / r02[1].getWidth(), this.parentView.getMeasuredHeight() / r02[1].getHeight());
        r7.drawBitmap(r02[1], 0.0f, 0.0f, this.emptyPaint);
        r7.restore();
        r7.save();
        if (this.type != 0) goto L57;
        r7.translate(0.0f, this.panTranslationY);
    L57:
        r7.scale(this.parentView.getMeasuredWidth() / r02[0].getWidth(), this.toolbarH / r02[0].getHeight());
        r7.drawBitmap(r02[0], 0.0f, 0.0f, this.emptyPaint);
        r7.restore();
        this.wasDraw = true;
        r7.drawColor(ColorUtils.blendARGB(436207616, -1, this.flashProgress));
    L58:
        r7.restore();
        if (this.show == true) goto L61;
        return;
    L61:
        if (this.processingNextFrame == false) goto L63;
        return;
    L63:
        if (this.renderingBitmap != null) goto L65;
    L66:
        this.processingNextFrame = true;
        this.invalidate = false;
        if (this.blurredBitmapTmp != null) goto L69;
        this.blurredBitmapTmp = new Bitmap[2];
        this.blurCanvas = new Canvas[2];
    L69:
        int r152 = 0;
    L70:
        if (r152 >= 2) goto L98;
        if (this.blurredBitmapTmp[r152] != null) goto L74;
    L79:
        int r3 = this.parentView.getMeasuredHeight();
        int r52 = this.parentView.getMeasuredWidth();
        int r6 = AndroidUtilities.statusBarHeight + AndroidUtilities.dp(200.0f);
        this.toolbarH = r6;
        if (r152 != 0) goto L110;
        r3 = r6;
    L110:
        this.blurredBitmapTmp[r152] = Bitmap.createBitmap((int) (r52 / 15.0f), (int) (r3 / 15.0f), Bitmap.Config.ARGB_8888);     // Catch: Exception -> L95
        this.blurCanvas[r152] = new Canvas(this.blurredBitmapTmp[r152]);     // Catch: Exception -> L95
    L83:
        if (r152 != 1) goto L85;
        this.blurredBitmapTmp[r152].eraseColor(getThemedColor(Theme.key_windowBackgroundWhite));
    L85:
        this.blurCanvas[r152].save();
        this.blurCanvas[r152].scale(0.06666667f, 0.06666667f, 0.0f, 0.0f);
        Drawable r32 = this.behindView.getBackground();
        if (r32 != null) goto L88;
        r32 = getBackgroundDrawable();
    L88:
        this.behindView.setTag(67108867, Integer.valueOf(r152));
        if (r152 != 0) goto L91;
        this.blurCanvas[r152].translate(0.0f, -this.panTranslationY);
        this.behindView.draw(this.blurCanvas[r152]);
    L91:
        if (r32 == null) goto L94;
        if (r152 != 1) goto L94;
        Rect r53 = r32.getBounds();
        r32.setBounds(0, 0, this.behindView.getMeasuredWidth(), this.behindView.getMeasuredHeight());
        r32.draw(this.blurCanvas[r152]);
        r32.setBounds(r53);
        this.behindView.draw(this.blurCanvas[r152]);
    L94:
        this.behindView.setTag(67108867, null);
        this.blurCanvas[r152].restore();
        r152 = r152 + 1;
    L95:
        e = move-exception;
        FileLog.e(e);
        AndroidUtilities.runOnUIThread(new BlurBehindDrawable$$ExternalSyntheticLambda0(this));
        return;
    L74:
        if (this.parentView.getMeasuredWidth() != this.lastW) goto L79;
        if (this.parentView.getMeasuredHeight() != this.lastH) goto L79;
        this.blurredBitmapTmp[r152].eraseColor(0);
        goto L83
    L98:
        this.lastH = this.parentView.getMeasuredHeight();
        this.lastW = this.parentView.getMeasuredWidth();
        this.blurBackgroundTask.width = this.parentView.getMeasuredWidth();
        this.blurBackgroundTask.height = this.parentView.getMeasuredHeight();
        BlurBackgroundTask r153 = this.blurBackgroundTask;
        if (r153.width != 0) goto L101;
    L108:
        this.processingNextFrame = false;
        return;
    L101:
        if (r153.height == 0) goto L108;
        if (this.queue != null) goto L106;
        this.queue = new DispatchQueue("blur_thread_" + this);
    L106:
        this.queue.postRunnable(this.blurBackgroundTask);
        return;
    L65:
        if (this.invalidate == true) goto L66;
        return;
    L49:
        r7 = r15;
        r7.saveLayerAlpha(0.0f, 0.0f, this.parentView.getMeasuredWidth(), this.parentView.getMeasuredHeight(), (int) (r5 * 255.0f), 31);
        goto L50
    L40:
        r5 = 1.0f;
    L21:
        if (this.animateAlpha == false) goto L38;
        boolean r54 = this.show;
        if (r54 == false) goto L30;
        float r72 = this.blurAlpha;
        if (r72 == 1.0f) goto L30;
        float r73 = r72 + 0.09f;
        this.blurAlpha = r73;
        if (r73 <= 1.0f) goto L29;
        this.blurAlpha = 1.0f;
    L29:
        this.parentView.invalidate();
    L30:
        if (r54 == true) goto L38;
        float r55 = this.blurAlpha;
        if (r55 == 0.0f) goto L38;
        float r56 = r55 - 0.09f;
        this.blurAlpha = r56;
        if (r56 >= 0.0f) goto L36;
        this.blurAlpha = 0.0f;
    L36:
        this.parentView.invalidate();
        goto L38
    L12:
        if (this.wasDraw == true) goto L16;
        if (this.animateAlpha == true) goto L16;
        generateBlurredBitmaps();
        this.invalidate = false;
        goto L16
    }

    public void invalidate() {
        this.invalidate = true;
        View r1 = this.parentView;
        if (r1 == null) goto L6;
        r1.invalidate();
        return;
    }

    public boolean isFullyDrawing() {
        if (this.skipDraw == false) goto L5;
        return false;
    L5:
        if (this.wasDraw == true) goto L7;
        return false;
    L7:
        if (this.blurAlpha == 1.0f) goto L11;
        if (this.animateAlpha == false) goto L11;
        return false;
    L11:
        if (this.show == true) goto L13;
        return false;
    L13:
        if (this.parentView.getAlpha() != 1.0f) goto L21;
        return true;
    L21:
        return false;
    }

    public void onPanTranslationUpdate(float r1) {
        this.panTranslationY = r1;
        this.parentView.invalidate();
    }

    public void setAnimateAlpha(boolean r1) {
        this.animateAlpha = r1;
    }

    public void show(boolean r1) {
        this.show = r1;
    }
}
