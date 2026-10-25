package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class RLottieImageView extends ImageView {
    private boolean attachedToWindow;
    private boolean autoRepeat;
    private int autoRepeatCount;
    public boolean cached;
    private RLottieDrawable drawable;
    private ImageReceiver imageReceiver;
    private HashMap<String, Integer> layerColors;
    private Integer layerNum;
    private boolean onlyLastFrame;
    private boolean playing;
    private boolean startOnAttach;

    /* renamed from: org.telegram.ui.Components.RLottieImageView$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends ImageReceiver {
        final /* synthetic */ RLottieImageView this$0;

        public AnonymousClass1(RLottieImageView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.messenger.ImageReceiver
        public boolean setImageBitmapByKey(Drawable r2, String r3, int r4, boolean r5, int r6) {
            if (r2 == null) goto L5;
            this.this$0.onLoaded();
        L5:
            return super.setImageBitmapByKey(r2, r3, r4, r5, r6);
        }
    }

    /* renamed from: org.telegram.ui.Components.RLottieImageView$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends Drawable {
        final /* synthetic */ RLottieImageView this$0;
        final /* synthetic */ int val$h;
        final /* synthetic */ int val$w;

        public AnonymousClass2(RLottieImageView r1, int r2, int r3) {
            this.this$0 = r1;
            this.val$w = r2;
            this.val$h = r3;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r7) {
            Rect r0 = AndroidUtilities.rectTmp2;
            r0.set(getBounds().centerX() - (AndroidUtilities.dp(this.val$w) / 2), getBounds().centerY() - (AndroidUtilities.dp(this.val$h) / 2), getBounds().centerX() + (AndroidUtilities.dp(this.val$w) / 2), getBounds().centerY() + (AndroidUtilities.dp(this.val$h) / 2));
            RLottieImageView.m12289$$Nest$fgetimageReceiver(this.this$0).setImageCoords(r0);
            RLottieImageView.m12289$$Nest$fgetimageReceiver(this.this$0).draw(r7);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            RLottieImageView.m12289$$Nest$fgetimageReceiver(this.this$0).setAlpha(r2 / 255.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            RLottieImageView.m12289$$Nest$fgetimageReceiver(this.this$0).setColorFilter(r1);
        }
    }

    /* renamed from: -$$Nest$fgetimageReceiver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m12289$$Nest$fgetimageReceiver(RLottieImageView r0) {
        return r0.imageReceiver;
    }

    public RLottieImageView(Context r1) {
        super(r1);
        this.autoRepeatCount = -1;
    }

    public void clearAnimationDrawable() {
        RLottieDrawable r0 = this.drawable;
        if (r0 == null) goto L5;
        r0.stop();
    L5:
        ImageReceiver r02 = this.imageReceiver;
        if (r02 == null) goto L8;
        r02.onDetachedFromWindow();
        this.imageReceiver = null;
    L8:
        this.drawable = null;
        setImageDrawable(null);
    }

    public void clearLayerColors() {
        this.layerColors.clear();
    }

    public RLottieDrawable getAnimatedDrawable() {
        return this.drawable;
    }

    public ImageReceiver getImageReceiver() {
        return this.imageReceiver;
    }

    public boolean isPlaying() {
        RLottieDrawable r0 = this.drawable;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isRunning() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
        ImageReceiver r0 = this.imageReceiver;
        if (r0 == null) goto L7;
        r0.onAttachedToWindow();
        if (this.playing == false) goto L7;
        this.imageReceiver.startAnimation();
    L7:
        RLottieDrawable r02 = this.drawable;
        if (r02 == null) goto L13;
        r02.setCallback(this);
        if (this.playing == false) goto L14;
        this.drawable.start();
        return;
    L14:
        return;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
        RLottieDrawable r0 = this.drawable;
        if (r0 == null) goto L5;
        r0.stop();
    L5:
        ImageReceiver r1 = this.imageReceiver;
        if (r1 == null) goto L9;
        r1.onDetachedFromWindow();
        return;
    }

    public void onLoaded() {
    }

    public void playAnimation() {
        RLottieDrawable r0 = this.drawable;
        if (r0 == null) goto L5;
    L7:
        this.playing = true;
        if (this.attachedToWindow == false) goto L15;
        if (r0 == null) goto L11;
        r0.start();
    L11:
        ImageReceiver r3 = this.imageReceiver;
        if (r3 == null) goto L17;
        r3.startAnimation();
        return;
    L17:
        return;
    L15:
        this.startOnAttach = true;
        return;
    L5:
        if (this.imageReceiver != null) goto L7;
    }

    public void replaceColors(int[] r1) {
        RLottieDrawable r0 = this.drawable;
        if (r0 == null) goto L6;
        r0.replaceColors(r1);
        return;
    }

    public void setAnimation(int r2, int r3, int r4) {
        setAnimation(r2, r3, r4, null);
    }

    public void setAnimation(int r7, int r8, int r9, int[] r10) {
        setAnimation(new RLottieDrawable(r7, AndroidUtilities.dp(r8), AndroidUtilities.dp(r9), false, r10));
    }

    public void setAnimation(TLRPC.Document r19, int r20, int r21) {
        ImageReceiver r1 = this.imageReceiver;
        if (r1 == null) goto L5;
        r1.onDetachedFromWindow();
        this.imageReceiver = null;
    L5:
        if (r19 == null) goto L47;
        AnonymousClass1 r12 = new AnonymousClass1(this);
        this.imageReceiver = r12;
        r12.setAllowLoadingOnAttachedOnly(true);
        String r13 = r19.localThumbPath;
        if (r13 == null) goto L10;
        String r7 = r20 + "_" + r21;
        ImageLocation r6 = ImageLocation.getForPath(r13);
    L11:
        TLRPC.PhotoSize r14 = FileLoader.getClosestPhotoSizeWithSize(r19.thumbs, 90);
        if (this.onlyLastFrame == false) goto L14;
        this.imageReceiver.setImage(ImageLocation.getForDocument(r19), r20 + "_" + r21 + "_lastframe", ImageLocation.getForDocument(r14, r19), r20 + "_" + r21, r6, r7, null, 0, null, r19, 1);
    L31:
        this.imageReceiver.setAspectFit(true);
        this.imageReceiver.setParentView(this);
        boolean r15 = this.autoRepeat;
        ImageReceiver r2 = this.imageReceiver;
        if (r15 == false) goto L34;
        r2.setAutoRepeat(1);
        this.imageReceiver.setAllowStartLottieAnimation(true);
        this.imageReceiver.setAllowStartAnimation(true);
    L35:
        int r16 = this.autoRepeatCount;
        if (r16 == (-1)) goto L38;
        this.imageReceiver.setAutoRepeatCount(r16);
    L38:
        ImageReceiver r17 = this.imageReceiver;
        Integer r22 = this.layerNum;
        if (r22 == null) goto L41;
        int r23 = r22.intValue();
    L42:
        r17.setLayerNum(r23);
        this.imageReceiver.clip = false;
        setImageDrawable(new AnonymousClass2(this, r20, r21));
        if (this.attachedToWindow == false) goto L46;
        this.imageReceiver.onAttachedToWindow();
        return;
    L46:
        return;
    L41:
        r23 = 7;
        goto L42
    L34:
        r2.setAutoRepeat(0);
        goto L35
    L14:
        boolean r24 = "video/webm".equals(r19.mime_type);
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r24 == false) goto L24;
        ImageReceiver r25 = this.imageReceiver;
        ImageLocation r26 = ImageLocation.getForDocument(r19);
        StringBuilder r9 = new StringBuilder();
        r9.append(r20);
        r9.append("_");
        r9.append(r21);
        if (this.cached == false) goto L19;
        r3 = "_pcache";
    L19:
        r9.append(r3);
        r9.append("_g");
        String r32 = r9.toString();
        if (r6 != null) goto L23;
        r6 = ImageLocation.getForDocument(r14, r19);
    L23:
        r25.setImage(r26, r32, r6, r7, null, r19.size, null, r19, 1);
        goto L31
    L24:
        SvgHelper.SvgDrawable r8 = DocumentObject.getSvgThumb(r19.thumbs, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f);
        if (r8 == null) goto L27;
        r8.overrideWidthAndHeight(512, 512);
    L27:
        ImageReceiver r27 = this.imageReceiver;
        ImageLocation r28 = ImageLocation.getForDocument(r19);
        StringBuilder r10 = new StringBuilder();
        r10.append(r20);
        r10.append("_");
        r10.append(r21);
        if (this.cached == false) goto L30;
        r3 = "_pcache";
    L30:
        r10.append(r3);
        r27.setImage(r28, r10.toString(), ImageLocation.getForDocument(r14, r19), r20 + "_" + r21, r6, r7, r8, 0, null, r19, 1);
        goto L31
    L10:
        r6 = null;
        r7 = null;
        goto L11
    }

    public void setAnimation(RLottieDrawable r5) {
        if (this.drawable != r5) goto L5;
        return;
    L5:
        ImageReceiver r0 = this.imageReceiver;
        if (r0 == null) goto L8;
        r0.onDetachedFromWindow();
        this.imageReceiver = null;
    L8:
        this.drawable = r5;
        r5.setMasterParent(this);
        if (this.autoRepeat == false) goto L11;
        this.drawable.setAutoRepeat(1);
    L11:
        int r52 = this.autoRepeatCount;
        if (r52 == (-1)) goto L15;
        this.drawable.setAutoRepeatCount(r52);
    L15:
        if (this.layerColors == null) goto L21;
        this.drawable.beginApplyLayerColors();
        Iterator<Map.Entry<String, Integer>> r53 = this.layerColors.entrySet().iterator();
    L18:
        if (r53.hasNext() == false) goto L20;
        Map.Entry<String, Integer> r1 = r53.next();
        this.drawable.setLayerColor(r1.getKey(), r1.getValue().intValue());
        goto L18
    L20:
        this.drawable.commitApplyLayerColors();
    L21:
        this.drawable.setAllowDecodeSingleFrame(true);
        setImageDrawable(this.drawable);
    }

    public void setAutoRepeat(boolean r1) {
        this.autoRepeat = r1;
    }

    public void setAutoRepeatCount(int r1) {
        this.autoRepeatCount = r1;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int r1) {
        super.setImageResource(r1);
        this.drawable = null;
    }

    public void setLayerColor(String r3, int r4) {
        if (this.layerColors != null) goto L5;
        this.layerColors = new HashMap();
    L5:
        this.layerColors.put(r3, Integer.valueOf(r4));
        RLottieDrawable r2 = this.drawable;
        if (r2 == null) goto L9;
        r2.setLayerColor(r3, r4);
        return;
    }

    public void setLayerNum(Integer r1) {
        this.layerNum = r1;
        ImageReceiver r0 = this.imageReceiver;
        if (r0 == null) goto L6;
        r0.setLayerNum(r1.intValue());
        return;
    }

    public void setOnAnimationEndListener(Runnable r1) {
        RLottieDrawable r0 = this.drawable;
        if (r0 == null) goto L6;
        r0.setOnAnimationEndListener(r1);
        return;
    }

    public void setOnlyLastFrame(boolean r1) {
        this.onlyLastFrame = r1;
    }

    public void setProgress(float r1) {
        RLottieDrawable r0 = this.drawable;
        if (r0 == null) goto L6;
        r0.setProgress(r1);
        return;
    }

    public void stopAnimation() {
        RLottieDrawable r0 = this.drawable;
        if (r0 == null) goto L5;
    L7:
        this.playing = false;
        if (this.attachedToWindow == false) goto L15;
        if (r0 == null) goto L11;
        r0.stop();
    L11:
        ImageReceiver r3 = this.imageReceiver;
        if (r3 == null) goto L17;
        r3.stopAnimation();
        return;
    L17:
        return;
    L15:
        this.startOnAttach = false;
        return;
    L5:
        if (this.imageReceiver != null) goto L7;
    }
}
