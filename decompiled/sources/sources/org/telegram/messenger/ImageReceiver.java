package org.telegram.messenger;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.annotation.Keep;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.AttachableDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.ClipRoundedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LoadingStickerDrawable;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclableDrawable;
import org.telegram.ui.Components.VectorAvatarThumbDrawable;

/* loaded from: classes.dex */
public class ImageReceiver implements NotificationCenter.NotificationCenterDelegate, AnimatedEmojiSpan.InvalidateHolder {
    public static final int DEFAULT_CROSSFADE_DURATION = 150;
    private static final int TYPE_CROSSFDADE = 2;
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_MEDIA = 3;
    public static final int TYPE_THUMB = 1;
    private static final float[] radii = null;
    private static PorterDuffColorFilter selectedColorFilter;
    private static PorterDuffColorFilter selectedGroupColorFilter;
    private boolean allowCrossfadeWithImage;
    private boolean allowDecodeSingleFrame;
    private boolean allowDrawWhileCacheGenerating;
    private boolean allowLoadingOnAttachedOnly;
    private boolean allowLottieVibration;
    private boolean allowStartAnimation;
    private boolean allowStartLottieAnimation;
    private int animateFromIsPressed;
    public int animatedFileDrawableRepeatMaxCount;
    private boolean animationReadySent;
    private boolean attachedToWindow;
    private int autoRepeat;
    private int autoRepeatCount;
    private long autoRepeatTimeout;
    private Object blendMode;
    private boolean canceledLoading;
    private boolean centerRotation;
    public boolean clip;
    private ColorFilter colorFilter;
    private ComposeShader composeShader;
    private byte crossfadeAlpha;
    private float crossfadeByScale;
    private int crossfadeDuration;
    private Drawable crossfadeImage;
    private String crossfadeKey;
    private BitmapShader crossfadeShader;
    private boolean crossfadeWithOldImage;
    private boolean crossfadeWithThumb;
    private boolean crossfadingWithThumb;
    private int currentAccount;
    private float currentAlpha;
    private int currentCacheType;
    private String currentExt;
    private int currentGuid;
    private Drawable currentImageDrawable;
    private String currentImageFilter;
    private String currentImageKey;
    private ImageLocation currentImageLocation;
    private boolean currentKeyQuality;
    private int currentLayerNum;
    private Drawable currentMediaDrawable;
    private String currentMediaFilter;
    private String currentMediaKey;
    private ImageLocation currentMediaLocation;
    private int currentOpenedLayerFlags;
    private Object currentParentObject;
    private long currentSize;
    private Drawable currentThumbDrawable;
    private String currentThumbFilter;
    private String currentThumbKey;
    private ImageLocation currentThumbLocation;
    private long currentTime;
    private ArrayList<Decorator> decorators;
    private ImageReceiverDelegate delegate;
    private final RectF drawRegion;
    private boolean emojiPaused;
    private int[] emptyRoundRadius;
    private long endTime;
    private int fileLoadingPriority;
    private boolean forceCrossfade;
    private boolean forceLoding;
    private boolean forceNotMedia;
    private boolean forcePreview;
    private Bitmap gradientBitmap;
    private BitmapShader gradientShader;
    private boolean ignoreImageSet;
    public boolean ignoreNotifications;
    private float imageH;
    protected int imageInvert;
    protected int imageOrientation;
    private BitmapShader imageShader;
    private int imageTag;
    private float imageW;
    private float imageX;
    private float imageY;
    private boolean invalidateAll;
    public final Runnable invalidateRunnable;
    private boolean isAspectFit;
    private int isLastFrame;
    private int isPressed;
    private boolean isRoundRect;
    private boolean isRoundVideo;
    private boolean isVisible;
    private long lastUpdateAlphaTime;
    private Bitmap legacyBitmap;
    private Canvas legacyCanvas;
    private Paint legacyPaint;
    private BitmapShader legacyShader;
    private final ArrayList<Runnable> loadingOperations;
    private boolean manualAlphaAnimator;
    private Object mark;
    private BitmapShader mediaShader;
    private int mediaTag;
    private boolean needsQualityThumb;
    private NotificationCenter.ObserversGroup observersGroup;
    private float overrideAlpha;
    private int param;
    private Runnable parentRunnable;
    private View parentView;
    List<ImageReceiver> preloadReceivers;
    private float pressedProgress;
    private float previousAlpha;
    private TLRPC.Document qulityThumbDocument;
    private Paint roundPaint;
    private final Path roundPath;
    private final int[] roundRadius;
    private final RectF roundRect;
    private SetImageBackup setImageBackup;
    private final Matrix shaderMatrix;
    private boolean shouldGenerateQualityThumb;
    private float sideClip;
    private boolean skipUpdateFrame;
    private long startTime;
    private Drawable staticThumbDrawable;
    public BitmapShader staticThumbShader;
    private ImageLocation strippedLocation;
    private int thumbInvert;
    private int thumbOrientation;
    public BitmapShader thumbShader;
    private int thumbTag;
    private String uniqKeyPrefix;
    private boolean useRoundForThumb;
    private boolean useRoundRadius;
    public boolean useSharedAnimationQueue;
    private boolean videoThumbIsSame;
    private Runnable visibleInvalidate;

    public static class BackgroundThreadDrawHolder {
        private AnimatedFileDrawable animation;
        public boolean animationNotReady;
        public ColorFilter colorFilter;
        private Drawable crossfadeImage;
        private BitmapShader crossfadeShader;
        private boolean crossfadeWithOldImage;
        private boolean crossfadingWithThumb;
        private float currentAlpha;
        public RectF drawRegion;
        private Drawable imageDrawable;
        public float imageH;
        private BitmapShader imageShader;
        public float imageW;
        public float imageX;
        public float imageY;
        private RLottieDrawable lottieDrawable;
        private Drawable mediaDrawable;
        private BitmapShader mediaShader;
        public float overrideAlpha;
        Paint paint;
        private float previousAlpha;
        private Path roundPath;
        private int[] roundRadius;
        private Drawable staticThumbDrawable;
        public BitmapShader staticThumbShader;
        public int threadIndex;
        private Drawable thumbDrawable;
        private BitmapShader thumbShader;
        public long time;

        /* renamed from: -$$Nest$fgetanimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedFileDrawable m4197$$Nest$fgetanimation(BackgroundThreadDrawHolder r0) {
            return r0.animation;
        }

        /* renamed from: -$$Nest$fgetcrossfadeImage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m4198$$Nest$fgetcrossfadeImage(BackgroundThreadDrawHolder r0) {
            return r0.crossfadeImage;
        }

        /* renamed from: -$$Nest$fgetcrossfadeShader, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BitmapShader m4199$$Nest$fgetcrossfadeShader(BackgroundThreadDrawHolder r0) {
            return r0.crossfadeShader;
        }

        /* renamed from: -$$Nest$fgetcrossfadeWithOldImage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4200$$Nest$fgetcrossfadeWithOldImage(BackgroundThreadDrawHolder r0) {
            return r0.crossfadeWithOldImage;
        }

        /* renamed from: -$$Nest$fgetcrossfadingWithThumb, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4201$$Nest$fgetcrossfadingWithThumb(BackgroundThreadDrawHolder r0) {
            return r0.crossfadingWithThumb;
        }

        /* renamed from: -$$Nest$fgetcurrentAlpha, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m4202$$Nest$fgetcurrentAlpha(BackgroundThreadDrawHolder r0) {
            return r0.currentAlpha;
        }

        /* renamed from: -$$Nest$fgetimageDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m4203$$Nest$fgetimageDrawable(BackgroundThreadDrawHolder r0) {
            return r0.imageDrawable;
        }

        /* renamed from: -$$Nest$fgetimageShader, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BitmapShader m4204$$Nest$fgetimageShader(BackgroundThreadDrawHolder r0) {
            return r0.imageShader;
        }

        /* renamed from: -$$Nest$fgetlottieDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieDrawable m4205$$Nest$fgetlottieDrawable(BackgroundThreadDrawHolder r0) {
            return r0.lottieDrawable;
        }

        /* renamed from: -$$Nest$fgetmediaDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m4206$$Nest$fgetmediaDrawable(BackgroundThreadDrawHolder r0) {
            return r0.mediaDrawable;
        }

        /* renamed from: -$$Nest$fgetmediaShader, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BitmapShader m4207$$Nest$fgetmediaShader(BackgroundThreadDrawHolder r0) {
            return r0.mediaShader;
        }

        /* renamed from: -$$Nest$fgetpreviousAlpha, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m4208$$Nest$fgetpreviousAlpha(BackgroundThreadDrawHolder r0) {
            return r0.previousAlpha;
        }

        /* renamed from: -$$Nest$fgetroundPath, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Path m4209$$Nest$fgetroundPath(BackgroundThreadDrawHolder r0) {
            return r0.roundPath;
        }

        /* renamed from: -$$Nest$fgetroundRadius, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int[] m4210$$Nest$fgetroundRadius(BackgroundThreadDrawHolder r0) {
            return r0.roundRadius;
        }

        /* renamed from: -$$Nest$fgetstaticThumbDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m4211$$Nest$fgetstaticThumbDrawable(BackgroundThreadDrawHolder r0) {
            return r0.staticThumbDrawable;
        }

        /* renamed from: -$$Nest$fgetthumbDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m4212$$Nest$fgetthumbDrawable(BackgroundThreadDrawHolder r0) {
            return r0.thumbDrawable;
        }

        /* renamed from: -$$Nest$fgetthumbShader, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BitmapShader m4213$$Nest$fgetthumbShader(BackgroundThreadDrawHolder r0) {
            return r0.thumbShader;
        }

        /* renamed from: -$$Nest$fputanimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4214$$Nest$fputanimation(BackgroundThreadDrawHolder r0, AnimatedFileDrawable r1) {
            r0.animation = r1;
        }

        /* renamed from: -$$Nest$fputcrossfadeImage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4215$$Nest$fputcrossfadeImage(BackgroundThreadDrawHolder r0, Drawable r1) {
            r0.crossfadeImage = r1;
        }

        /* renamed from: -$$Nest$fputcrossfadeShader, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4216$$Nest$fputcrossfadeShader(BackgroundThreadDrawHolder r0, BitmapShader r1) {
            r0.crossfadeShader = r1;
        }

        /* renamed from: -$$Nest$fputcrossfadeWithOldImage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4217$$Nest$fputcrossfadeWithOldImage(BackgroundThreadDrawHolder r0, boolean r1) {
            r0.crossfadeWithOldImage = r1;
        }

        /* renamed from: -$$Nest$fputcrossfadingWithThumb, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4218$$Nest$fputcrossfadingWithThumb(BackgroundThreadDrawHolder r0, boolean r1) {
            r0.crossfadingWithThumb = r1;
        }

        /* renamed from: -$$Nest$fputcurrentAlpha, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4219$$Nest$fputcurrentAlpha(BackgroundThreadDrawHolder r0, float r1) {
            r0.currentAlpha = r1;
        }

        /* renamed from: -$$Nest$fputimageDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4220$$Nest$fputimageDrawable(BackgroundThreadDrawHolder r0, Drawable r1) {
            r0.imageDrawable = r1;
        }

        /* renamed from: -$$Nest$fputimageShader, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4221$$Nest$fputimageShader(BackgroundThreadDrawHolder r0, BitmapShader r1) {
            r0.imageShader = r1;
        }

        /* renamed from: -$$Nest$fputlottieDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4222$$Nest$fputlottieDrawable(BackgroundThreadDrawHolder r0, RLottieDrawable r1) {
            r0.lottieDrawable = r1;
        }

        /* renamed from: -$$Nest$fputmediaDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4223$$Nest$fputmediaDrawable(BackgroundThreadDrawHolder r0, Drawable r1) {
            r0.mediaDrawable = r1;
        }

        /* renamed from: -$$Nest$fputmediaShader, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4224$$Nest$fputmediaShader(BackgroundThreadDrawHolder r0, BitmapShader r1) {
            r0.mediaShader = r1;
        }

        /* renamed from: -$$Nest$fputpreviousAlpha, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4225$$Nest$fputpreviousAlpha(BackgroundThreadDrawHolder r0, float r1) {
            r0.previousAlpha = r1;
        }

        /* renamed from: -$$Nest$fputroundPath, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4226$$Nest$fputroundPath(BackgroundThreadDrawHolder r0, Path r1) {
            r0.roundPath = r1;
        }

        /* renamed from: -$$Nest$fputstaticThumbDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4227$$Nest$fputstaticThumbDrawable(BackgroundThreadDrawHolder r0, Drawable r1) {
            r0.staticThumbDrawable = r1;
        }

        /* renamed from: -$$Nest$fputthumbDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4228$$Nest$fputthumbDrawable(BackgroundThreadDrawHolder r0, Drawable r1) {
            r0.thumbDrawable = r1;
        }

        /* renamed from: -$$Nest$fputthumbShader, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4229$$Nest$fputthumbShader(BackgroundThreadDrawHolder r0, BitmapShader r1) {
            r0.thumbShader = r1;
        }

        public BackgroundThreadDrawHolder() {
            this.roundRadius = new int[4];
            this.drawRegion = new RectF();
        }

        public void getBounds(Rect r4) {
            if (r4 == null) goto L5;
            int r0 = (int) this.imageX;
            r4.left = r0;
            int r1 = (int) this.imageY;
            r4.top = r1;
            r4.right = (int) (r0 + this.imageW);
            r4.bottom = (int) (r1 + this.imageH);
            return;
        }

        public void getBounds(RectF r4) {
            if (r4 == null) goto L5;
            float r0 = this.imageX;
            r4.left = r0;
            float r1 = this.imageY;
            r4.top = r1;
            r4.right = r0 + this.imageW;
            r4.bottom = r1 + this.imageH;
            return;
        }

        public void release() {
            this.animation = null;
            this.lottieDrawable = null;
            int r1 = 0;
        L4:
            if (r1 >= 4) goto L6;
            int[] r2 = this.roundRadius;
            r2[r1] = r2[r1];
            r1 = r1 + 1;
            goto L4
        L6:
            this.mediaDrawable = null;
            this.mediaShader = null;
            this.imageDrawable = null;
            this.imageShader = null;
            this.thumbDrawable = null;
            this.thumbShader = null;
            this.staticThumbShader = null;
            this.staticThumbDrawable = null;
            this.crossfadeImage = null;
            this.colorFilter = null;
        }

        public void setBounds(Rect r2) {
            if (r2 == null) goto L5;
            this.imageX = r2.left;
            this.imageY = r2.top;
            this.imageW = r2.width();
            this.imageH = r2.height();
            return;
        }
    }

    /* loaded from: classes5.dex */
    public static class BitmapHolder {
        public Bitmap bitmap;
        public Drawable drawable;
        private String key;
        public int orientation;
        private boolean recycleOnRelease;

        public BitmapHolder(Bitmap r1) {
            this.bitmap = r1;
            this.recycleOnRelease = true;
        }

        public BitmapHolder(Bitmap r1, String r2, int r3) {
            this.bitmap = r1;
            this.key = r2;
            this.orientation = r3;
            if (r2 == null) goto L6;
            ImageLoader.getInstance().incrementUseCount(this.key);
            return;
        }

        public BitmapHolder(Drawable r1, String r2, int r3) {
            this.drawable = r1;
            this.key = r2;
            this.orientation = r3;
            if (r2 == null) goto L6;
            ImageLoader.getInstance().incrementUseCount(this.key);
            return;
        }

        public int getHeight() {
            Bitmap r0 = this.bitmap;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.getHeight();
        }

        public String getKey() {
            return this.key;
        }

        public int getWidth() {
            Bitmap r0 = this.bitmap;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.getWidth();
        }

        public boolean isRecycled() {
            Bitmap r0 = this.bitmap;
            if (r0 != null) goto L5;
            return true;
        L5:
            if (r0.isRecycled() == true) goto L11;
            return false;
        L11:
            return true;
        }

        public void release() {
            if (this.key == null) goto L5;
            boolean r0 = ImageLoader.getInstance().decrementUseCount(this.key);
            if (ImageLoader.getInstance().isInMemCache(this.key, false) == true) goto L28;
            if (r0 == false) goto L28;
            Bitmap r02 = this.bitmap;
            if (r02 == null) goto L17;
            r02.recycle();
            goto L28
        L17:
            Drawable r03 = this.drawable;
            if (r03 == null) goto L28;
            if ((r03 instanceof RLottieDrawable) == false) goto L23;
            ((RLottieDrawable) r03).recycle(false);
            goto L28
        L23:
            if ((r03 instanceof AnimatedFileDrawable) == false) goto L26;
            ((AnimatedFileDrawable) r03).recycle();
            goto L28
        L26:
            if ((r03 instanceof BitmapDrawable) == false) goto L28;
            ((BitmapDrawable) r03).getBitmap().recycle();
        L28:
            this.key = null;
            this.bitmap = null;
            this.drawable = null;
            return;
        L5:
            if (this.recycleOnRelease == false) goto L9;
            Bitmap r04 = this.bitmap;
            if (r04 == null) goto L9;
            r04.recycle();
        L9:
            this.bitmap = null;
            this.drawable = null;
        }
    }

    /* loaded from: classes5.dex */
    public static abstract class Decorator {
        public Decorator() {
        }

        public void onAttachedToWindow(ImageReceiver r1) {
        }

        public void onDetachedFromWidnow() {
        }

        public abstract void onDraw(Canvas r1, ImageReceiver r2);
    }

    public interface ImageReceiverDelegate {
        void didSetImage(ImageReceiver r1, boolean r2, boolean r3, boolean r4);

        default void didSetImageBitmap(int r1, String r2, Drawable r3) {
        }

        default void onAnimationReady(ImageReceiver r1) {
        }
    }

    public static class ReactionLastFrame extends BitmapDrawable {
        public static final float LAST_FRAME_SCALE = 1.2f;

        public ReactionLastFrame(Bitmap r1) {
            super(r1);
        }
    }

    public static class SetImageBackup {
        public int cacheType;
        public String ext;
        public String imageFilter;
        public ImageLocation imageLocation;
        public String mediaFilter;
        public ImageLocation mediaLocation;
        public Object parentObject;
        public long size;
        public Drawable thumb;
        public String thumbFilter;
        public ImageLocation thumbLocation;

        /* renamed from: -$$Nest$mclear, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4230$$Nest$mclear(SetImageBackup r0) {
            r0.clear();
        }

        /* renamed from: -$$Nest$misSet, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4231$$Nest$misSet(SetImageBackup r0) {
            return r0.isSet();
        }

        /* renamed from: -$$Nest$misWebfileSet, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4232$$Nest$misWebfileSet(SetImageBackup r0) {
            return r0.isWebfileSet();
        }

        private SetImageBackup() {
        }

        public /* synthetic */ SetImageBackup(ImageReceiverIA r1) {
            this();
        }

        private void clear() {
            this.imageLocation = null;
            this.thumbLocation = null;
            this.mediaLocation = null;
            this.thumb = null;
        }

        private boolean isSet() {
            if (this.imageLocation == null) goto L5;
            return true;
        L5:
            if (this.thumbLocation == null) goto L7;
            return true;
        L7:
            if (this.mediaLocation == null) goto L9;
            return true;
        L9:
            if (this.thumb != null) goto L17;
            return false;
        L17:
            return true;
        }

        private boolean isWebfileSet() {
            ImageLocation r0 = this.imageLocation;
            if (r0 != null) goto L5;
        L8:
            ImageLocation r02 = this.thumbLocation;
            if (r02 != null) goto L11;
        L14:
            ImageLocation r2 = this.mediaLocation;
            if (r2 != null) goto L17;
            return false;
        L17:
            if (r2.webFile == null) goto L19;
            return true;
        L19:
            if (r2.path == null) goto L29;
            return true;
        L29:
            return false;
        L11:
            if (r02.webFile == null) goto L13;
            return true;
        L13:
            if (r02.path == null) goto L14;
            return true;
        L5:
            if (r0.webFile == null) goto L7;
            return true;
        L7:
            if (r0.path == null) goto L8;
            return true;
        }
    }

    static {
        PorterDuff.Mode r1 = PorterDuff.Mode.MULTIPLY;
        selectedColorFilter = new PorterDuffColorFilter(-2236963, r1);
        selectedGroupColorFilter = new PorterDuffColorFilter(-4473925, r1);
        radii = new float[8];
    }

    public ImageReceiver() {
        this(null);
    }

    public ImageReceiver(View r3) {
        this.allowCrossfadeWithImage = true;
        this.fileLoadingPriority = 1;
        this.useRoundForThumb = true;
        this.allowLottieVibration = true;
        this.allowStartAnimation = true;
        this.allowStartLottieAnimation = true;
        this.autoRepeat = 1;
        this.autoRepeatCount = -1;
        this.drawRegion = new RectF();
        this.isVisible = true;
        this.useRoundRadius = true;
        this.roundRadius = new int[4];
        this.isRoundRect = true;
        this.roundRect = new RectF();
        this.shaderMatrix = new Matrix();
        this.roundPath = new Path();
        this.overrideAlpha = 1.0f;
        this.previousAlpha = 1.0f;
        this.crossfadeAlpha = 1;
        this.crossfadeByScale = 0.05f;
        this.crossfadeDuration = 150;
        this.loadingOperations = new ArrayList();
        this.allowLoadingOnAttachedOnly = false;
        this.clip = true;
        this.invalidateRunnable = new ImageReceiver$$ExternalSyntheticLambda1(this);
        this.parentView = r3;
        this.roundPaint = new Paint(3);
        this.currentAccount = UserConfig.selectedAccount;
    }

    private void checkAlphaAnimation(boolean r9, BackgroundThreadDrawHolder r10) {
        if (this.manualAlphaAnimator == true) goto L30;
        float r0 = this.currentAlpha;
        if (r0 == 1.0f) goto L29;
        if (r9 == true) goto L24;
        if (r10 == null) goto L18;
        long r2 = System.currentTimeMillis();
        long r4 = this.lastUpdateAlphaTime;
        long r22 = r2 - r4;
        if (r4 != 0) goto L13;
        r22 = 16;
    L13:
        if (r22 > 30) goto L15;
    L17:
        this.currentAlpha += r22 / this.crossfadeDuration;
    L20:
        if (this.currentAlpha <= 1.0f) goto L24;
        this.currentAlpha = 1.0f;
        this.previousAlpha = 1.0f;
        if (this.crossfadeImage == null) goto L24;
        recycleBitmap(null, 2);
        this.crossfadeShader = null;
        goto L24
    L15:
        if (AndroidUtilities.screenRefreshRate <= 60.0f) goto L17;
        r22 = 30;
        goto L17
    L18:
        this.currentAlpha = r0 + (16.0f / this.crossfadeDuration);
    L24:
        if (r10 == null) goto L27;
        AndroidUtilities.runOnUIThread(new ImageReceiver$$ExternalSyntheticLambda1(this));
        return;
    L27:
        invalidate();
        return;
    L29:
        return;
    }

    private void drawBitmapDrawable(Canvas r10, BitmapDrawable r11, BackgroundThreadDrawHolder r12, int r13) {
        if (r12 != null) goto L4;
        r11.setAlpha(r13);
        if ((r11 instanceof RLottieDrawable) == false) goto L23;
        ((RLottieDrawable) r11).drawInternal(r10, null, false, this.currentTime, 0);
        return;
    L23:
        if ((r11 instanceof AnimatedFileDrawable) == false) goto L26;
        ((AnimatedFileDrawable) r11).drawInternal(r10, false, this.currentTime, 0);
        return;
    L26:
        r11.draw(r10);
        return;
    L4:
        if ((r11 instanceof RLottieDrawable) == false) goto L8;
        ((RLottieDrawable) r11).drawInBackground(r10, r12.imageX, r12.imageY, r12.imageW, r12.imageH, r13, r12.colorFilter, r12.threadIndex);
        return;
    L8:
        if ((r11 instanceof AnimatedFileDrawable) == false) goto L11;
        ((AnimatedFileDrawable) r11).drawInBackground(r10, r12.imageX, r12.imageY, r12.imageW, r12.imageH, r13, r12.colorFilter, r12.threadIndex);
        return;
    L11:
        Bitmap r0 = r11.getBitmap();
        if (r0 != null) goto L14;
        return;
    L14:
        if (r12.paint != null) goto L16;
        r12.paint = new Paint(1);
    L16:
        r12.paint.setAlpha(r13);
        r12.paint.setColorFilter(r12.colorFilter);
        r10.save();
        r10.translate(r12.imageX, r12.imageY);
        r10.scale(r12.imageW / r0.getWidth(), r12.imageH / r0.getHeight());
        r10.drawBitmap(r0, 0.0f, 0.0f, r12.paint);
        r10.restore();
    }

    private void drawDrawable(Canvas r10, Drawable r11, int r12, BitmapShader r13, int r14, int r15, BackgroundThreadDrawHolder r16) {
        if (this.isPressed != 0) goto L10;
        float r1 = this.pressedProgress;
        if (r1 == 0.0f) goto L10;
        float r17 = r1 - 0.10666667f;
        this.pressedProgress = r17;
        if (r17 >= 0.0f) goto L9;
        this.pressedProgress = 0.0f;
    L9:
        invalidate();
    L10:
        int r7 = this.isPressed;
        if (r7 == 0) goto L13;
        this.pressedProgress = 1.0f;
        this.animateFromIsPressed = r7;
    L13:
        float r3 = this.pressedProgress;
        if (r3 == 0.0f) goto L20;
        if (r3 == 1.0f) goto L20;
        drawDrawable(r10, r11, r12, r13, r14, r15, r7, r16);
        drawDrawable(r10, r11, (int) (r12 * this.pressedProgress), r13, r14, r15, this.animateFromIsPressed, r16);
        return;
    L20:
        drawDrawable(r10, r11, r12, r13, r14, r15, r7, r16);
    }

    public static File getAvatarLocalFile(int r6, TLObject r7) {
        ImageLocation r2 = ImageLocation.getForUserOrChat(r6, r7, 1);     // Catch: Exception -> L10
        File r62 = FileLoader.getInstance(r6).getLocalFile(r2);     // Catch: Exception -> L10
        if (r62 == null) goto L6;
        return r62;
    L6:
        String r63 = r2.getKey(r7, r2, true);     // Catch: Exception -> L10
        if (r2.path == null) goto L12;
        r63 = r63 + "." + ImageLoader.getHttpUrlExtension(r2.path, "jpg");     // Catch: Exception -> L10
    L45:
        File r72 = new File(FileLoader.getDirectory(4), r63);     // Catch: Exception -> L10
        if (r72.exists() == true) goto L48;
        return null;
    L48:
        return r72;
    L12:
        TLRPC.PhotoSize r73 = r2.photoSize;     // Catch: Exception -> L10
        if ((r73 instanceof TLRPC.TL_photoStrippedSize) == false) goto L16;
    L44:
        r63 = r63 + ".jpg";     // Catch: Exception -> L10
        goto L45
    L16:
        if ((r73 instanceof TLRPC.TL_photoPathSize) == true) goto L44;
        if (r2.location == null) goto L21;
        r63 = r63 + ".jpg";     // Catch: Exception -> L10
        goto L45
    L21:
        WebFile r74 = r2.webFile;     // Catch: Exception -> L10
        if (r74 == null) goto L25;
        r63 = r63 + "." + ImageLoader.getHttpUrlExtension(r2.webFile.url, FileLoader.getMimeTypePart(r74.mime_type));     // Catch: Exception -> L10
        goto L45
    L25:
        if (r2.secureDocument == null) goto L27;
        r63 = r63 + ".jpg";     // Catch: Exception -> L10
        goto L45
    L27:
        TLRPC.Document r75 = r2.document;     // Catch: Exception -> L10
        if (r75 == null) goto L45;
        String r76 = FileLoader.getDocumentFileName(r75);     // Catch: Exception -> L10
        int r3 = r76.lastIndexOf(46);     // Catch: Exception -> L10
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r3 != (-1)) goto L33;
        String r77 = _UrlKt.FRAGMENT_ENCODE_SET;
    L35:
        if (r77.length() <= 1) goto L37;
        r5 = r77;
    L43:
        r63 = r63 + r5;     // Catch: Exception -> L10
        goto L45
    L37:
        if ("video/mp4".equals(r2.document.mime_type) == false) goto L40;
        r5 = ".mp4";
        goto L43
    L40:
        if ("video/x-matroska".equals(r2.document.mime_type) == false) goto L43;
        r5 = ".mkv";
        goto L43
    L33:
        r77 = r76.substring(r3);     // Catch: Exception -> L10
    L10:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private boolean hasRoundRadius() {
        return true;
    }

    private void loadImage() {
        ImageLoader.getInstance().loadImageForImageReceiver(this, this.preloadReceivers);
        invalidate();
    }

    private void onBitmapException(Drawable r14) {
        if (r14 != this.currentMediaDrawable) goto L8;
        if (this.currentMediaKey == null) goto L8;
        ImageLoader.getInstance().removeImage(this.currentMediaKey);
        this.currentMediaKey = null;
    L17:
        setImage(this.currentMediaLocation, this.currentMediaFilter, this.currentImageLocation, this.currentImageFilter, this.currentThumbLocation, this.currentThumbFilter, this.currentThumbDrawable, this.currentSize, this.currentExt, this.currentParentObject, this.currentCacheType);
        return;
    L8:
        if (r14 != this.currentImageDrawable) goto L13;
        if (this.currentImageKey == null) goto L13;
        ImageLoader.getInstance().removeImage(this.currentImageKey);
        this.currentImageKey = null;
    L13:
        if (r14 != this.currentThumbDrawable) goto L17;
        if (this.currentThumbKey == null) goto L17;
        ImageLoader.getInstance().removeImage(this.currentThumbKey);
        this.currentThumbKey = null;
        goto L17
    }

    private void setDrawableShader(Drawable r3, BitmapShader r4) {
        if (r3 != this.currentThumbDrawable) goto L7;
        this.thumbShader = r4;
        return;
    L7:
        if (r3 != this.staticThumbDrawable) goto L11;
        this.staticThumbShader = r4;
        return;
    L11:
        if (r3 != this.currentMediaDrawable) goto L15;
        this.mediaShader = r4;
        return;
    L15:
        if (r3 != this.currentImageDrawable) goto L37;
        this.imageShader = r4;
        if (this.gradientShader != null) goto L19;
        return;
    L19:
        if ((r3 instanceof BitmapDrawable) == true) goto L21;
        return;
    L21:
        if (Build.VERSION.SDK_INT < 28) goto L24;
        this.composeShader = new ComposeShader(this.gradientShader, this.imageShader, PorterDuff.Mode.DST_IN);
        return;
    L24:
        BitmapDrawable r32 = (BitmapDrawable) r3;
        int r42 = r32.getBitmap().getWidth();
        int r33 = r32.getBitmap().getHeight();
        Bitmap r0 = this.legacyBitmap;
        if (r0 != null) goto L27;
    L30:
        Bitmap r02 = this.legacyBitmap;
        if (r02 == null) goto L33;
        r02.recycle();
    L33:
        this.legacyBitmap = Bitmap.createBitmap(r42, r33, Bitmap.Config.ARGB_8888);
        this.legacyCanvas = new Canvas(this.legacyBitmap);
        Bitmap r43 = this.legacyBitmap;
        Shader.TileMode r03 = Shader.TileMode.CLAMP;
        this.legacyShader = new BitmapShader(r43, r03, r03);
        if (this.legacyPaint != null) goto L41;
        Paint r34 = new Paint();
        this.legacyPaint = r34;
        r34.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        return;
    L41:
        return;
    L27:
        if (r0.getWidth() != r42) goto L30;
        if (this.legacyBitmap.getHeight() != r33) goto L30;
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setStaticDrawable(Drawable r3) {
        Drawable r0 = this.staticThumbDrawable;
        if (r3 != r0) goto L6;
        return;
    L6:
        if ((r0 instanceof AttachableDrawable) == true) goto L8;
        AttachableDrawable r02 = null;
    L12:
        this.staticThumbDrawable = r3;
        if (this.attachedToWindow == false) goto L18;
        if ((r3 instanceof AttachableDrawable) == false) goto L18;
        ((AttachableDrawable) r3).onAttachedToWindow(this);
    L18:
        if (this.attachedToWindow == false) goto L22;
        if (r02 == null) goto L23;
        r02.onDetachedFromWindow(this);
        return;
    L23:
        return;
    L22:
        return;
    L8:
        if (r0.equals(r3) == true) goto L25;
        r02 = (AttachableDrawable) this.staticThumbDrawable;
        goto L12
    }

    private void updateDrawableRadius(Drawable r5) {
        if (r5 == null) goto L38;
        int[] r1 = getRoundRadius(true);
        if ((r5 instanceof ClipRoundedDrawable) == false) goto L9;
        ((ClipRoundedDrawable) r5).setRadii(r1[0], r1[1], r1[2], r1[3]);
        return;
    L9:
        if (hasRoundRadius() == true) goto L13;
        if (this.gradientShader != null) goto L13;
    L17:
        setDrawableShader(r5, null);
        return;
    L13:
        if ((r5 instanceof BitmapDrawable) == true) goto L20;
        if ((r5 instanceof AvatarDrawable) == false) goto L17;
    L20:
        if ((r5 instanceof AvatarDrawable) == false) goto L23;
        ((AvatarDrawable) r5).setRoundRadius(r1[0]);
        return;
    L23:
        BitmapDrawable r0 = (BitmapDrawable) r5;
        if ((r0 instanceof RLottieDrawable) == false) goto L27;
        return;
    L27:
        if ((r0 instanceof AnimatedFileDrawable) == false) goto L31;
        ((AnimatedFileDrawable) r5).setRoundRadius(r1);
        return;
    L31:
        if (r0.getBitmap() != null) goto L33;
        return;
    L33:
        if (r0.getBitmap().isRecycled() == true) goto L37;
        Bitmap r02 = r0.getBitmap();
        Shader.TileMode r2 = Shader.TileMode.CLAMP;
        setDrawableShader(r5, new BitmapShader(r02, r2, r2));
        return;
    L37:
        return;
    }

    public void addDecorator(Decorator r2) {
        if (this.decorators != null) goto L5;
        this.decorators = new ArrayList();
    L5:
        this.decorators.add(r2);
        if (this.attachedToWindow == false) goto L9;
        r2.onAttachedToWindow(this);
        return;
    }

    public void addLoadingImageRunnable(Runnable r2) {
        ArrayList<Runnable> r0 = this.loadingOperations;
        monitor-enter(r0);
        this.loadingOperations.add(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void bumpPriority() {
        ImageLoader.getInstance().changeFileLoadingPriorityForImageReceiver(this);
    }

    public boolean canInvertBitmap() {
        if ((this.currentMediaDrawable instanceof ExtendedBitmapDrawable) == false) goto L5;
        return true;
    L5:
        if ((this.currentImageDrawable instanceof ExtendedBitmapDrawable) == false) goto L7;
        return true;
    L7:
        if ((this.currentThumbDrawable instanceof ExtendedBitmapDrawable) == false) goto L9;
        return true;
    L9:
        if ((this.staticThumbDrawable instanceof ExtendedBitmapDrawable) == true) goto L17;
        return false;
    L17:
        return true;
    }

    public void cancelLoadImage() {
        this.forceLoding = false;
        ImageLoader.getInstance().cancelLoadingForImageReceiver(this, true);
        this.canceledLoading = true;
    }

    public void clearDecorators() {
        if (this.decorators != null) goto L5;
        return;
    L5:
        if (this.attachedToWindow == false) goto L10;
        int r0 = 0;
    L8:
        if (r0 >= this.decorators.size()) goto L10;
        this.decorators.get(r0).onDetachedFromWidnow();
        r0 = r0 + 1;
    L10:
        this.decorators.clear();
    }

    public void clearImage() {
        int r0 = 0;
    L4:
        if (r0 >= 4) goto L6;
        recycleBitmap(null, r0);
        r0 = r0 + 1;
        goto L4
    L6:
        ImageLoader.getInstance().cancelLoadingForImageReceiver(this, true);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        if (r4 != NotificationCenter.didReplacedPhotoInMemCache) goto L27;
        String r42 = (String) r6[0];
        String r52 = this.currentMediaKey;
        if (r52 != null) goto L7;
    L11:
        String r53 = this.currentImageKey;
        if (r53 != null) goto L14;
    L18:
        String r54 = this.currentThumbKey;
        if (r54 != null) goto L21;
        return;
    L21:
        if (r54.equals(r42) == false) goto L66;
        this.currentThumbKey = (String) r6[1];
        Object r43 = r6[2];
        this.currentThumbLocation = (ImageLocation) r43;
        SetImageBackup r3 = this.setImageBackup;
        if (r3 == null) goto L67;
        r3.thumbLocation = (ImageLocation) r43;
        return;
    L67:
        return;
    L66:
        return;
    L14:
        if (r53.equals(r42) == false) goto L18;
        this.currentImageKey = (String) r6[1];
        Object r55 = r6[2];
        this.currentImageLocation = (ImageLocation) r55;
        SetImageBackup r2 = this.setImageBackup;
        if (r2 == null) goto L18;
        r2.imageLocation = (ImageLocation) r55;
        goto L18
    L7:
        if (r52.equals(r42) == false) goto L11;
        this.currentMediaKey = (String) r6[1];
        Object r56 = r6[2];
        this.currentMediaLocation = (ImageLocation) r56;
        SetImageBackup r22 = this.setImageBackup;
        if (r22 == null) goto L11;
        r22.mediaLocation = (ImageLocation) r56;
        goto L11
    L27:
        if (r4 != NotificationCenter.stopAllHeavyOperations) goto L43;
        Integer r44 = (Integer) r6[0];
        if (this.currentLayerNum >= r44.intValue()) goto L75;
        int r57 = this.currentOpenedLayerFlags;
        int r45 = r44.intValue() | r57;
        this.currentOpenedLayerFlags = r45;
        if (r45 == 0) goto L68;
        RLottieDrawable r46 = getLottieAnimation();
        if (r46 != null) goto L36;
    L38:
        AnimatedFileDrawable r32 = getAnimation();
        if (r32 == null) goto L69;
        r32.stop();
        return;
    L69:
        return;
    L36:
        if (r46.isHeavyDrawable() == false) goto L38;
        r46.stop();
        goto L38
    L68:
        return;
    L75:
        return;
    L43:
        if (r4 != NotificationCenter.startAllHeavyOperations) goto L70;
        Integer r47 = (Integer) r6[0];
        if (this.currentLayerNum >= r47.intValue()) goto L71;
        int r58 = this.currentOpenedLayerFlags;
        if (r58 == 0) goto L76;
        int r48 = (~r47.intValue()) & r58;
        this.currentOpenedLayerFlags = r48;
        if (r48 != 0) goto L72;
        RLottieDrawable r49 = getLottieAnimation();
        if (r49 == null) goto L55;
        r49.setAllowVibration(this.allowLottieVibration);
    L55:
        if (this.allowStartLottieAnimation == false) goto L60;
        if (r49 == null) goto L60;
        if (r49.isHeavyDrawable() == false) goto L60;
        r49.start();
    L60:
        AnimatedFileDrawable r410 = getAnimation();
        if (this.allowStartAnimation == false) goto L73;
        if (r410 == null) goto L74;
        r410.checkRepeat();
        invalidate();
        return;
    L74:
        return;
    L73:
        return;
    L72:
        return;
    L76:
        return;
    L71:
        return;
    }

    public boolean draw(Canvas r2) {
        return draw(r2, null);
    }

    public boolean draw(Canvas r32, BackgroundThreadDrawHolder r33) {
        ImageReceiver r1 = this;
        Canvas r2 = r32;
        if (r1.gradientBitmap != null) goto L5;
    L7:
        if (r33 == null) goto L9;
        boolean r10 = true;
    L205:
        if (r10 == false) goto L14;
        AnimatedFileDrawable r3 = BackgroundThreadDrawHolder.m4197$$Nest$fgetanimation(r33);     // Catch: Exception -> L12
        RLottieDrawable r4 = BackgroundThreadDrawHolder.m4205$$Nest$fgetlottieDrawable(r33);     // Catch: Exception -> L12
        int[] r5 = BackgroundThreadDrawHolder.m4210$$Nest$fgetroundRadius(r33);     // Catch: Exception -> L12
        Drawable r6 = BackgroundThreadDrawHolder.m4206$$Nest$fgetmediaDrawable(r33);     // Catch: Exception -> L12
        BitmapShader r7 = BackgroundThreadDrawHolder.m4207$$Nest$fgetmediaShader(r33);     // Catch: Exception -> L12
        Drawable r11 = BackgroundThreadDrawHolder.m4203$$Nest$fgetimageDrawable(r33);     // Catch: Exception -> L12
        BitmapShader r12 = BackgroundThreadDrawHolder.m4204$$Nest$fgetimageShader(r33);     // Catch: Exception -> L12
        BitmapShader r13 = BackgroundThreadDrawHolder.m4213$$Nest$fgetthumbShader(r33);     // Catch: Exception -> L12
        BitmapShader r14 = r33.staticThumbShader;     // Catch: Exception -> L12
        Drawable r15 = BackgroundThreadDrawHolder.m4198$$Nest$fgetcrossfadeImage(r33);     // Catch: Exception -> L12
        BackgroundThreadDrawHolder.m4200$$Nest$fgetcrossfadeWithOldImage(r33);     // Catch: Exception -> L12
        boolean r16 = BackgroundThreadDrawHolder.m4201$$Nest$fgetcrossfadingWithThumb(r33);     // Catch: Exception -> L12
        Drawable r17 = BackgroundThreadDrawHolder.m4212$$Nest$fgetthumbDrawable(r33);     // Catch: Exception -> L12
        Drawable r18 = BackgroundThreadDrawHolder.m4211$$Nest$fgetstaticThumbDrawable(r33);     // Catch: Exception -> L12
        float r19 = BackgroundThreadDrawHolder.m4202$$Nest$fgetcurrentAlpha(r33);     // Catch: Exception -> L12
        float r20 = BackgroundThreadDrawHolder.m4208$$Nest$fgetpreviousAlpha(r33);     // Catch: Exception -> L12
        BitmapShader r21 = BackgroundThreadDrawHolder.m4199$$Nest$fgetcrossfadeShader(r33);     // Catch: Exception -> L12
        boolean r9 = r33.animationNotReady;     // Catch: Exception -> L12
        float r182 = r33.overrideAlpha;     // Catch: Exception -> L12
        float r0 = r19;
        BitmapShader r192 = r21;
        boolean r212 = r9;
        Drawable r92 = r6;
        Drawable r62 = r18;
    L210:
    L28:
        e = e;
    L29:
        r2 = r32;
    L13:
        boolean r23 = false;
    L189:
        FileLog.e(e);
    L191:
        if (r1.gradientBitmap != null) goto L193;
    L195:
        if (r23 == true) goto L197;
    L204:
        return r23;
    L197:
        if (r1.isVisible == false) goto L204;
        if (r1.decorators == null) goto L204;
        int r93 = 0;
    L202:
        if (r93 >= r1.decorators.size()) goto L204;
        r1.decorators.get(r93).onDraw(r2, r1);
        r93 = r93 + 1;
        goto L202
    L193:
        if (r1.currentImageKey == null) goto L195;
        r2.restore();
        goto L195
    L25:
        if (r1.useRoundRadius == true) goto L27;
        r5 = r1.emptyRoundRadius;     // Catch: Exception -> L28
    L27:
        int[] r22 = r5;
        if (r3 == null) goto L32;
        r3.setRoundRadius(r22);     // Catch: Exception -> L28
    L32:
        if (r3 != null) goto L36;
        if (r4 != null) goto L36;
    L43:
        boolean r42 = r1.forcePreview;     // Catch: Exception -> L28
        if (r42 == false) goto L46;
    L50:
        if (r42 == true) goto L55;
        if (r11 == null) goto L55;
        if (r212 == false) goto L54;
        if (r92 == null) goto L55;
    L54:
        int r43 = r1.imageOrientation;     // Catch: Exception -> L28
        int r72 = r1.imageInvert;     // Catch: Exception -> L28
        Drawable r52 = r11;
        r212 = false;
    L69:
        float r34 = r1.crossfadeByScale;     // Catch: Exception -> L28
        boolean r26 = r10;
        if (r34 <= 0.0f) goto L72;
        float r25 = Math.min((r34 * r0) + r0, 1.0f);     // Catch: Exception -> L28
    L74:
        if (r52 != null) goto L76;
        Drawable r35 = r62;
        boolean r102 = r212;
        r23 = true;
        Drawable r213 = r52;
        if (r35 != null) goto L176;
        r2 = r32;
        r1.checkAlphaAnimation(r102, r33);     // Catch: Exception -> L12
        r23 = false;
    L183:
        if (r213 != null) goto L191;
        if (r102 == false) goto L191;
        if (r26 == true) goto L191;
        r1.invalidate();     // Catch: Exception -> L188
    L188:
        e = e;
        goto L189
    L176:
        if ((r35 instanceof VectorAvatarThumbDrawable) == false) goto L179;
        ((VectorAvatarThumbDrawable) r35).setParent(r1);     // Catch: Exception -> L28
    L179:
        r2 = r32;
        r1.drawDrawable(r2, r35, (int) (r182 * 255.0f), null, r1.thumbOrientation, r1.thumbInvert, r33);     // Catch: Exception -> L12
        r1.checkAlphaAnimation(r102, r33);     // Catch: Exception -> L12
        goto L183
    L76:
        if (r1.crossfadeAlpha != 0) goto L78;
        Drawable r36 = r52;
        r102 = r212;
        r23 = true;
        r1 = this;
        BackgroundThreadDrawHolder r8 = r33;
        r1.drawDrawable(r32, r36, (int) (r182 * 255.0f), r12, r43, r72, r8);     // Catch: Exception -> L28
        r213 = r36;
    L167:
        if (r102 == true) goto L169;
    L171:
        boolean r37 = false;
    L172:
        r1.checkAlphaAnimation(r37, r8);     // Catch: Exception -> L28
        r2 = r32;
        goto L183
    L169:
        if (r1.crossfadeWithThumb == false) goto L171;
        r37 = r23;
        goto L172
    L78:
        if (r20 == 1.0f) goto L82;
        if (r52 == r11) goto L83;
        if (r52 != r92) goto L82;
    L83:
        if (r62 == null) goto L82;
        if (r1.useRoundForThumb == false) goto L88;
        if (r14 != null) goto L88;
        r1.updateDrawableRadius(r62);     // Catch: Exception -> L28
        r14 = r1.staticThumbShader;     // Catch: Exception -> L28
    L88:
        Drawable r38 = r62;
        int r63 = r43;
        float r28 = 1.0f;
        r102 = r212;
        r23 = true;
        Drawable r214 = r52;
        BitmapShader r53 = r14;
        r1.drawDrawable(r32, r38, (int) (r182 * 255.0f), r53, r63, r72, r33);     // Catch: Exception -> L28
        r22 = r22;
        r14 = r53;
    L90:
        int[] r54 = r22;
        Drawable r24 = r17;
        boolean r44 = r1.crossfadeWithThumb;     // Catch: Exception -> L162
        if (r44 == false) goto L96;
        if (r102 == false) goto L96;
        Drawable r39 = r214;
        r1.drawDrawable(r32, r39, (int) (r182 * 255.0f), r12, r63, r72, r33);     // Catch: Exception -> L28
        r8 = r33;
        r213 = r39;
    L96:
        int r29 = r72;
        BitmapShader r172 = r12;
        Drawable r122 = r214;
        int r215 = r63;
        if (r44 == true) goto L99;
    L143:
        Canvas r27 = r32;
        int[] r142 = r54;
    L146:
        if (r1.crossfadeByScale > 0.0f) goto L148;
    L156:
        boolean r02 = false;
    L158:
        r1.drawDrawable(r27, r122, (int) ((r182 * r25) * 255.0f), r172, r215, r29, r33);     // Catch: Exception -> L162
        if (r02 == false) goto L164;
        r32.restore();     // Catch: Exception -> L162
    L164:
        r1 = this;
        r8 = r33;
        r213 = r122;
        goto L167
    L148:
        if (r25 >= r28) goto L156;
        if (r16 == false) goto L156;
        r27.save();     // Catch: Exception -> L12
        r1.roundPath.rewind();     // Catch: Exception -> L12
        RectF r310 = AndroidUtilities.rectTmp;     // Catch: Exception -> L12
        float r45 = r1.imageX;     // Catch: Exception -> L12
        float r55 = r1.imageY;     // Catch: Exception -> L12
        r310.set(r45, r55, r1.imageW + r45, r1.imageH + r55);     // Catch: Exception -> L12
        int r311 = 0;
    L152:
        if (r311 >= r142.length) goto L154;
        float[] r46 = radii;     // Catch: Exception -> L12
        int r56 = r311 * 2;     // Catch: Exception -> L12
        int r64 = r142[r311];     // Catch: Exception -> L12
        r46[r56] = r64;     // Catch: Exception -> L12
        r46[r56 + 1] = r64;     // Catch: Exception -> L12
        r311 = r311 + 1;     // Catch: Exception -> L12
        goto L152
    L154:
        r1.roundPath.addRoundRect(AndroidUtilities.rectTmp, radii, Path.Direction.CW);     // Catch: Exception -> L12
        r27.clipPath(r1.roundPath);     // Catch: Exception -> L12
        float r312 = (r1.crossfadeByScale * (r28 - CubicBezierInterpolator.EASE_IN.getInterpolation(r0))) + r28;     // Catch: Exception -> L12
        r27.scale(r312, r312, r1.getCenterX(), r1.getCenterY());     // Catch: Exception -> L12
        r02 = r23;
        goto L158
    L99:
        if (r25 == r28) goto L143;
        if (r122 == r11) goto L116;
        if (r122 == r92) goto L116;
        if (r122 == r24) goto L109;
        if (r122 == r15) goto L109;
        if (r122 != r38) goto L125;
        if (r15 == null) goto L125;
    L117:
        r38 = r15;
        r13 = r192;
    L126:
        if (r38 == null) goto L143;
        if ((r38 instanceof SvgHelper.SvgDrawable) == false) goto L130;
    L134:
        float r210 = ((r28 - r25) * r182) * 255.0f;     // Catch: Exception -> L28
    L133:
        int r211 = (int) r210;     // Catch: Exception -> L28
        r27 = r32;
        r142 = r54;
        r1.drawDrawable(r27, r38, r211, r13, r1.thumbOrientation, r1.thumbInvert, r33);     // Catch: Exception -> L12
        if (r211 == 255) goto L146;
        if ((r38 instanceof Emoji.EmojiDrawable) == false) goto L146;
        r38.setAlpha(255);     // Catch: Exception -> L12
        goto L146
    L130:
        if ((r38 instanceof Emoji.EmojiDrawable) == true) goto L134;
        r210 = (r20 * r182) * 255.0f;     // Catch: Exception -> L28
    L125:
        r38 = null;
        r13 = null;
    L109:
        if (r38 == null) goto L125;
        if (r1.useRoundForThumb == false) goto L115;
        if (r14 != null) goto L115;
        r1.updateDrawableRadius(r38);     // Catch: Exception -> L28
        BitmapShader r216 = r1.staticThumbShader;     // Catch: Exception -> L28
    L114:
        r13 = r216;
    L115:
        r13 = r14;
    L116:
        if (r15 != null) goto L117;
        if (r24 == null) goto L120;
        r38 = r24;
        goto L126
    L120:
        if (r38 == null) goto L125;
        if (r1.useRoundForThumb == false) goto L115;
        if (r14 != null) goto L115;
        r1.updateDrawableRadius(r38);     // Catch: Exception -> L28
        r216 = r1.staticThumbShader;     // Catch: Exception -> L28
    L162:
        e = e;
        r1 = this;
    L82:
        r38 = r62;
        r28 = 1.0f;
        r102 = r212;
        r23 = true;
        r63 = r43;
        r214 = r52;
        goto L90
    L72:
        r25 = r0;
    L55:
        if (r15 == null) goto L58;
        if (r16 == true) goto L58;
        r43 = r1.imageOrientation;     // Catch: Exception -> L28
        r72 = r1.imageInvert;     // Catch: Exception -> L28
        r52 = r15;
        r12 = r192;
    L58:
        if (r17 == null) goto L61;
        r43 = r1.thumbOrientation;     // Catch: Exception -> L28
        r72 = r1.thumbInvert;     // Catch: Exception -> L28
        r12 = r13;
        r52 = r17;
        goto L69
    L61:
        if ((r62 instanceof BitmapDrawable) == true) goto L63;
        r52 = null;
        r12 = null;
        r43 = 0;
        r72 = 0;
        goto L69
    L63:
        if (r1.useRoundForThumb == false) goto L66;
        if (r14 != null) goto L66;
        r1.updateDrawableRadius(r62);     // Catch: Exception -> L28
        BitmapShader r73 = r1.staticThumbShader;     // Catch: Exception -> L28
    L67:
        r43 = r1.thumbOrientation;     // Catch: Exception -> L28
        r12 = r73;
        r14 = r12;
        r72 = r1.thumbInvert;     // Catch: Exception -> L28
        r52 = r62;
    L66:
        r73 = r14;
        goto L67
    L46:
        if (r1.forceNotMedia == true) goto L50;
        if (r92 == null) goto L50;
        if (r212 == true) goto L50;
        r43 = r1.imageOrientation;     // Catch: Exception -> L28
        r12 = r7;
        r72 = r1.imageInvert;     // Catch: Exception -> L28
        r52 = r92;
    L36:
        if (r212 == true) goto L43;
        if (r1.animationReadySent == true) goto L43;
        if (r10 == true) goto L43;
        r1.animationReadySent = true;     // Catch: Exception -> L28
        ImageReceiverDelegate r47 = r1.delegate;     // Catch: Exception -> L28
        if (r47 == null) goto L43;
        r47.onAnimationReady(r1);     // Catch: Exception -> L28
        goto L43
    L14:
        r3 = r1.getAnimation();     // Catch: Exception -> L12
        r4 = r1.getLottieAnimation();     // Catch: Exception -> L12
        r5 = r1.roundRadius;     // Catch: Exception -> L12
        Drawable r65 = r1.currentMediaDrawable;     // Catch: Exception -> L12
        r7 = r1.mediaShader;     // Catch: Exception -> L12
        r11 = r1.currentImageDrawable;     // Catch: Exception -> L12
        r12 = r1.imageShader;     // Catch: Exception -> L12
        Drawable r03 = r1.currentThumbDrawable;     // Catch: Exception -> L12
        r13 = r1.thumbShader;     // Catch: Exception -> L12
        r14 = r1.staticThumbShader;     // Catch: Exception -> L12
        boolean r94 = r1.crossfadingWithThumb;     // Catch: Exception -> L12
        r15 = r1.crossfadeImage;     // Catch: Exception -> L12
        Drawable r04 = r1.staticThumbDrawable;     // Catch: Exception -> L12
        float r05 = r1.currentAlpha;     // Catch: Exception -> L12
        float r06 = r1.previousAlpha;     // Catch: Exception -> L12
        BitmapShader r07 = r1.crossfadeShader;     // Catch: Exception -> L12
        float r08 = r1.overrideAlpha;     // Catch: Exception -> L12
        if (r3 != null) goto L17;
    L18:
        if (r4 != null) goto L20;
    L22:
        r212 = false;
    L23:
        r182 = r08;
        r0 = r05;
        r92 = r65;
        r62 = r04;
        r17 = r03;
        r16 = r94;
        r20 = r06;
        r192 = r07;
        goto L210
    L20:
        if (r4.hasBitmap() == true) goto L22;
    L21:
        r212 = true;
        goto L23
    L17:
        if (r3.hasBitmap() == false) goto L21;
    L12:
        e = e;
        goto L13
    L9:
        r10 = false;
        goto L205
    L5:
        if (r1.currentImageKey == null) goto L7;
        r2.save();
        float r09 = r1.imageX;
        float r313 = r1.imageY;
        r2.clipRect(r09, r313, r1.imageW + r09, r1.imageH + r313);
        r2.drawColor(-16777216);
        goto L7
    }

    public void drawDrawable(Canvas r40, Drawable r41, int r42, BitmapShader r43, int r44, int r45, int r46, BackgroundThreadDrawHolder r47) {
        if (r47 == null) goto L6;
        float r9 = r47.imageX;
        float r10 = r47.imageY;
        float r11 = r47.imageH;
        float r12 = r47.imageW;
        RectF r13 = r47.drawRegion;
        ColorFilter r14 = r47.colorFilter;
        int[] r15 = BackgroundThreadDrawHolder.m4210$$Nest$fgetroundRadius(r47);
    L5:
        int[] r16 = r15;
        if (this.useRoundRadius == true) goto L11;
        int[] r152 = this.emptyRoundRadius;
    L10:
        float r162 = r11;
        if ((r41 instanceof BitmapDrawable) == false) goto L366;
        BitmapDrawable r112 = (BitmapDrawable) r41;
        float r21 = r12;
        boolean r122 = r41 instanceof RLottieDrawable;
        if (r122 == false) goto L17;
        boolean r22 = r122;
        ((RLottieDrawable) r41).skipFrameUpdate = this.skipUpdateFrame;
    L20:
        if (r43 == null) goto L22;
        Paint r8 = this.roundPaint;
    L23:
        int r123 = Build.VERSION.SDK_INT;
        float r23 = r9;
        if (r123 < 29) goto L31;
        Object r92 = this.blendMode;
        if (r92 != null) goto L28;
    L30:
        r8.setBlendMode(null);
        goto L31
    L28:
        if (this.gradientShader != null) goto L30;
        r8.setBlendMode(ImageReceiver$$ExternalSyntheticApiModelOutline0.m(r92));
    L31:
        if (r8 != null) goto L33;
    L35:
        boolean r82 = false;
    L36:
        if (r82 == false) goto L43;
        if (r46 != 0) goto L43;
        if (r43 == null) goto L41;
        this.roundPaint.setColorFilter(null);
    L53:
        if (r14 != null) goto L55;
    L59:
        boolean r7 = r112 instanceof AnimatedFileDrawable;
        if (r7 == false) goto L62;
    L63:
        boolean r26 = r7;
        int r72 = r44 % 360;
        if (r72 != 90) goto L79;
    L83:
        int r73 = r112.getIntrinsicHeight();
        int r93 = r112.getIntrinsicWidth();
    L82:
        boolean r83 = false;
    L84:
        float r142 = this.sideClip;
        float r27 = r21 - (r142 * 2.0f);
        float r143 = r162 - (r142 * 2.0f);
        boolean r29 = r83;
        if (r21 != 0.0f) goto L87;
        float r84 = 1.0f;
    L88:
        float r31 = r84;
        if (r162 != 0.0f) goto L91;
        float r85 = 1.0f;
    L93:
        if (r29 == false) goto L95;
        r31 = r31 / 1.2f;
        r85 = r85 / 1.2f;
    L95:
        float r144 = r85;
        float r86 = r31;
        if (r43 == null) goto L242;
        if (r47 != null) goto L242;
        if (this.isAspectFit == false) goto L144;
        float r87 = Math.max(r86, r144);
        float r74 = (int) (r73 / r87);
        float r94 = (int) (r93 / r87);
        r13.set(r23 + ((r21 - r74) / 2.0f), ((r162 - r94) / 2.0f) + r10, r23 + ((r21 + r74) / 2.0f), r10 + ((r162 + r94) / 2.0f));
        if (this.isVisible == false) goto L361;
        this.shaderMatrix.reset();
        this.shaderMatrix.setTranslate((int) r13.left, (int) r13.top);
        if (r45 == 0) goto L114;
        Matrix r75 = this.shaderMatrix;
        if (r45 != 1) goto L108;
        float r95 = -1.0f;
    L109:
        if (r45 != 2) goto L111;
        float r6 = -1.0f;
    L112:
        r75.preScale(r95, r6, r13.width() / 2.0f, r13.height() / 2.0f);
        goto L114
    L111:
        r6 = 1.0f;
        goto L112
    L108:
        r95 = 1.0f;
    L114:
        if (r44 != 90) goto L117;
        this.shaderMatrix.preRotate(90.0f);
        this.shaderMatrix.preTranslate(0.0f, -r13.width());
    L122:
        float r3 = 1.0f / r87;
        this.shaderMatrix.preScale(r3, r3);
        r43.setLocalMatrix(this.shaderMatrix);
        this.roundPaint.setShader(r43);
        this.roundPaint.setAlpha(r42);
        this.roundRect.set(r13);
        if (this.isRoundRect == true) goto L125;
    L136:
        int r0 = 0;
    L138:
        if (r0 >= r152.length) goto L140;
        float[] r32 = radii;
        int r4 = r0 * 2;
        int r5 = r152[r0];
        r32[r4] = r5;
        r32[r4 + 1] = r5;
        r0 = r0 + 1;
        goto L138
    L140:
        this.roundPath.reset();
        this.roundPath.addRoundRect(this.roundRect, radii, Path.Direction.CW);
        this.roundPath.close();
        if (r40 == null) goto L361;
        r40.drawPath(this.roundPath, this.roundPaint);
        goto L361
    L125:
        if (this.useRoundRadius == false) goto L136;
        if (r40 == null) goto L361;
        int r02 = r152[0];     // Catch: Exception -> L131
        RectF r33 = this.roundRect;
        if (r02 != 0) goto L133;
        r40.drawRect(r33, this.roundPaint);     // Catch: Exception -> L131
    L418:
        goto L361
    L133:
        r40.drawRoundRect(r33, r02, r02, this.roundPaint);     // Catch: Exception -> L131
    L131:
        e = move-exception;
        onBitmapException(r112);
        FileLog.e(e);
        goto L361
    L117:
        if (r44 != 180) goto L120;
        this.shaderMatrix.preRotate(180.0f);
        this.shaderMatrix.preTranslate(-r13.width(), -r13.height());
        goto L122
    L120:
        if (r44 != 270) goto L122;
        this.shaderMatrix.preRotate(270.0f);
        this.shaderMatrix.preTranslate(-r13.height(), 0.0f);
    L361:
        if (r22 == false) goto L364;
        ((RLottieDrawable) r41).skipFrameUpdate = false;
        return;
    L364:
        if ((r41 instanceof AnimatedFileDrawable) == false) goto L430;
        ((AnimatedFileDrawable) r41).skipFrameUpdate = false;
        return;
    L430:
        return;
    L144:
        if (this.legacyCanvas == null) goto L146;
        int[] r34 = r152;
        BitmapDrawable r35 = r112;
        this.roundRect.set(0.0f, 0.0f, this.legacyBitmap.getWidth(), this.legacyBitmap.getHeight());
        this.legacyCanvas.drawBitmap(this.gradientBitmap, null, this.roundRect, null);
        this.legacyCanvas.drawBitmap(r35.getBitmap(), null, this.roundRect, this.legacyPaint);
    L148:
        if (r43 == this.imageShader) goto L150;
    L155:
        this.roundPaint.setShader(r43);
    L156:
        float r113 = 1.0f / Math.min(r86, r144);
        RectF r2 = this.roundRect;
        float r36 = this.sideClip;
        r2.set(r23 + r36, r10 + r36, (r23 + r21) - r36, (r10 + r162) - r36);
        if (Math.abs(r86 - r144) <= 5.0E-4f) goto L162;
        float r24 = r73 / r144;
        if (r24 <= r27) goto L161;
        float r25 = (int) r24;
        r13.set(r23 - ((r25 - r27) / 2.0f), r10, r23 + ((r25 + r27) / 2.0f), r10 + r143);
        float r88 = r23;
    L164:
        if (this.isVisible == false) goto L361;
        this.shaderMatrix.reset();
        Matrix r28 = this.shaderMatrix;
        if (r29 == false) goto L168;
        r28.setTranslate((r13.left + this.sideClip) - (((r13.width() * 1.2f) - r13.width()) / 2.0f), (r13.top + this.sideClip) - (((r13.height() * 1.2f) - r13.height()) / 2.0f));
    L169:
        if (r45 == 0) goto L180;
        Matrix r210 = this.shaderMatrix;
        if (r45 != 1) goto L174;
        float r76 = -1.0f;
    L175:
        if (r45 != 2) goto L177;
        float r62 = -1.0f;
    L178:
        r210.preScale(r76, r62, r13.width() / 2.0f, r13.height() / 2.0f);
        goto L180
    L177:
        r62 = 1.0f;
        goto L178
    L174:
        r76 = 1.0f;
    L180:
        if (r44 != 90) goto L183;
        this.shaderMatrix.preRotate(90.0f);
        this.shaderMatrix.preTranslate(0.0f, -r13.width());
    L188:
        this.shaderMatrix.preScale(r113, r113);
        if (this.isRoundVideo == false) goto L191;
        float r211 = (r27 + (AndroidUtilities.roundMessageInset * 2)) / r27;
        this.shaderMatrix.postScale(r211, r211, r13.centerX(), r13.centerY());
    L191:
        BitmapShader r212 = this.legacyShader;
        if (r212 == null) goto L194;
        r212.setLocalMatrix(this.shaderMatrix);
    L194:
        r43.setLocalMatrix(this.shaderMatrix);
        if (this.composeShader == null) goto L217;
        int r213 = this.gradientBitmap.getWidth();
        int r48 = this.gradientBitmap.getHeight();
        if (r21 != 0.0f) goto L199;
        float r52 = 1.0f;
    L200:
        if (r162 != 0.0f) goto L202;
        float r63 = 1.0f;
    L204:
        if (Math.abs(r52 - r63) <= 5.0E-4f) goto L209;
        float r37 = r213 / r63;
        if (r37 <= r27) goto L208;
        r213 = (int) r37;
        float r38 = r213;
        r13.set(r88 - ((r38 - r27) / 2.0f), r10, ((r38 + r27) / 2.0f) + r88, r10 + r143);
    L210:
        if (r21 != 0.0f) goto L212;
        float r214 = 1.0f;
    L213:
        if (r162 != 0.0f) goto L215;
        float r39 = 1.0f;
    L216:
        float r114 = 1.0f / Math.min(r214, r39);
        this.shaderMatrix.reset();
        Matrix r215 = this.shaderMatrix;
        float r310 = r13.left;
        float r49 = this.sideClip;
        r215.setTranslate(r310 + r49, r13.top + r49);
        this.shaderMatrix.preScale(r114, r114);
        this.gradientShader.setLocalMatrix(this.shaderMatrix);
        goto L217
    L215:
        r39 = r48 / r143;
        goto L216
    L212:
        r214 = r213 / r27;
        goto L213
    L208:
        r48 = (int) (r48 / r52);
        float r311 = r48;
        r13.set(r88, r10 - ((r311 - r143) / 2.0f), r88 + r27, r10 + ((r311 + r143) / 2.0f));
        goto L210
    L209:
        r13.set(r88, r10, r88 + r27, r10 + r143);
        goto L210
    L202:
        r63 = r48 / r143;
        goto L204
    L199:
        r52 = r213 / r27;
    L217:
        this.roundPaint.setAlpha(r42);
        if (this.isRoundRect == true) goto L220;
    L234:
        int r03 = 0;
        int[] r153 = r34;
    L236:
        if (r03 >= r153.length) goto L238;
        float[] r216 = radii;
        int r410 = r03 * 2;
        int r53 = r153[r03];
        r216[r410] = r53;
        r216[r410 + 1] = r53;
        r03 = r03 + 1;
        goto L236
    L238:
        this.roundPath.reset();
        this.roundPath.addRoundRect(this.roundRect, radii, Path.Direction.CW);
        this.roundPath.close();
        if (r40 == null) goto L361;
        r40.drawPath(this.roundPath, this.roundPaint);
        goto L361
    L220:
        if (this.useRoundRadius == false) goto L234;
        if (r40 == null) goto L361;
        int r04 = r34[0];     // Catch: Exception -> L226
        if (r04 != 0) goto L229;
        if (r29 == false) goto L228;
        RectF r05 = AndroidUtilities.rectTmp;     // Catch: Exception -> L226
        r05.set(this.roundRect);     // Catch: Exception -> L226
        r05.inset((-((r13.width() * 1.2f) - r13.width())) / 2.0f, (-((r13.height() * 1.2f) - r13.height())) / 2.0f);     // Catch: Exception -> L226
        r40.drawRect(r05, this.roundPaint);     // Catch: Exception -> L226
    L424:
        goto L361
    L228:
        r40.drawRect(this.roundRect, this.roundPaint);     // Catch: Exception -> L226
        goto L424
    L229:
        r40.drawRoundRect(this.roundRect, r04, r04, this.roundPaint);     // Catch: Exception -> L226
    L226:
        e = move-exception;
        if (r47 != null) goto L233;
        onBitmapException(r35);
    L233:
        FileLog.e(e);
        goto L361
    L183:
        if (r44 != 180) goto L186;
        this.shaderMatrix.preRotate(180.0f);
        this.shaderMatrix.preTranslate(-r13.width(), -r13.height());
        goto L188
    L186:
        if (r44 != 270) goto L188;
        this.shaderMatrix.preRotate(270.0f);
        this.shaderMatrix.preTranslate(-r13.height(), 0.0f);
        goto L188
    L168:
        float r77 = r13.left;
        float r96 = this.sideClip;
        r28.setTranslate(r77 + r96, r13.top + r96);
        goto L169
    L161:
        float r217 = (int) (r93 / r86);
        r88 = r23;
        r13.set(r88, r10 - ((r217 - r143) / 2.0f), r23 + r27, ((r217 + r143) / 2.0f) + r10);
        goto L164
    L162:
        r88 = r23;
        r13.set(r88, r10, r88 + r27, r10 + r143);
        goto L164
    L150:
        if (this.gradientShader == null) goto L155;
        ComposeShader r218 = this.composeShader;
        Paint r312 = this.roundPaint;
        if (r218 == null) goto L154;
        r312.setShader(r218);
        goto L156
    L154:
        r312.setShader(this.legacyShader);
        goto L156
    L146:
        r35 = r112;
        r34 = r152;
    L242:
        if (this.isAspectFit == false) goto L273;
        float r411 = Math.max(r86, r144);
        r40.save();
        int r54 = (int) (r73 / r411);
        int r412 = (int) (r93 / r411);
        if (r47 != null) goto L248;
        float r55 = r54;
        float r413 = r412;
        r13.set(r23 + ((r21 - r55) / 2.0f), ((r162 - r413) / 2.0f) + r10, r23 + ((r21 + r55) / 2.0f), ((r162 + r413) / 2.0f) + r10);
        r112.setBounds((int) r13.left, (int) r13.top, (int) r13.right, (int) r13.bottom);
        if (r26 == false) goto L248;
        ((AnimatedFileDrawable) r112).setActualDrawRect(r13.left, r13.top, r13.width(), r13.height());
    L248:
        if (r47 == null) goto L257;
        if (r152 == null) goto L257;
        if (r152[0] <= 0) goto L257;
        r40.save();
        if (BackgroundThreadDrawHolder.m4209$$Nest$fgetroundPath(r47) != null) goto L255;
        Path r414 = new Path();
        BackgroundThreadDrawHolder r115 = r47;
        BackgroundThreadDrawHolder.m4226$$Nest$fputroundPath(r115, r414);
    L256:
        r414.rewind();
        RectF r56 = AndroidUtilities.rectTmp;
        r56.set(r23, r10, r23 + r21, r10 + r162);
        r414.addRoundRect(r56, r152[0], r152[2], Path.Direction.CW);
        r40.clipPath(r414);
    L259:
        if (this.isVisible == true) goto L416;
    L266:
        r40.restore();
        if (r115 == null) goto L361;
        if (r152 == null) goto L361;
        if (r152[0] <= 0) goto L361;
        r40.restore();
        goto L361
    L416:
        r112.setAlpha(r42);     // Catch: Exception -> L262
        drawBitmapDrawable(r40, r112, r115, r42);     // Catch: Exception -> L262
    L262:
        e = move-exception;
        if (r115 != null) goto L265;
        onBitmapException(r112);
    L265:
        FileLog.e(e);
        goto L266
    L255:
        r115 = r47;
        r414 = BackgroundThreadDrawHolder.m4209$$Nest$fgetroundPath(r115);
    L257:
        r115 = r47;
        goto L259
    L273:
        if (r40 == null) goto L361;
        if (Math.abs(r86 - r144) <= 1.0E-5f) goto L322;
        r40.save();
        if (this.clip == false) goto L280;
        float r372 = r86;
        r40.clipRect(r23, r10, r23 + r21, r10 + r162);
    L281:
        if (r45 != 1) goto L283;
        float r17 = r144;
        r40.scale(-1.0f, 1.0f, r21 / 2.0f, r162 / 2.0f);
    L286:
        int r415 = r44 % 360;
        if (r415 != 0) goto L289;
    L292:
        float r57 = r73 / r17;
        if (r57 <= r21) goto L295;
        float r58 = (int) r57;
        r13.set(r23 - ((r58 - r21) / 2.0f), r10, ((r58 + r21) / 2.0f) + r23, r10 + r162);
    L296:
        if (r26 == false) goto L298;
        ((AnimatedFileDrawable) r112).setActualDrawRect(r23, r10, r21, r162);
    L298:
        if (r47 != null) goto L307;
        if (r415 != 90) goto L302;
    L305:
        float r416 = r13.width() / 2.0f;
        float r59 = r13.height() / 2.0f;
        float r64 = r13.centerX();
        float r78 = r13.centerY();
        r112.setBounds((int) (r64 - r59), (int) (r78 - r416), (int) (r64 + r59), (int) (r78 + r416));
        goto L307
    L302:
        if (r415 == 270) goto L305;
        r112.setBounds((int) r13.left, (int) r13.top, (int) r13.right, (int) r13.bottom);
    L307:
        if (this.isVisible == true) goto L309;
    L321:
        r40.restore();
        goto L361
    L309:
        if (r123 >= 29) goto L422;
    L316:
        drawBitmapDrawable(r40, r112, r47, r42);     // Catch: Exception -> L313
    L313:
        e = move-exception;
        if (r47 != null) goto L320;
        onBitmapException(r112);
    L320:
        FileLog.e(e);
        goto L321
    L422:
        if (this.blendMode == null) goto L315;
        r112.getPaint().setBlendMode(ImageReceiver$$ExternalSyntheticApiModelOutline0.m(this.blendMode));     // Catch: Exception -> L313
        goto L316
    L315:
        r112.getPaint().setBlendMode(null);     // Catch: Exception -> L313
        goto L316
    L295:
        float r510 = (int) (r93 / r372);
        r13.set(r23, r10 - ((r510 - r162) / 2.0f), r23 + r21, ((r510 + r162) / 2.0f) + r10);
        goto L296
    L289:
        if (this.centerRotation == false) goto L291;
        r40.rotate(r44, r21 / 2.0f, r162 / 2.0f);
        goto L292
    L291:
        r40.rotate(r44, 0.0f, 0.0f);
        goto L292
    L283:
        r17 = r144;
        if (r45 != 2) goto L286;
        r40.scale(1.0f, -1.0f, r21 / 2.0f, r162 / 2.0f);
        goto L286
    L280:
        r372 = r86;
        goto L281
    L322:
        r40.save();
        if (r45 != 1) goto L326;
        r40.scale(-1.0f, 1.0f, r21 / 2.0f, r162 / 2.0f);
    L328:
        int r417 = r44 % 360;
        if (r417 != 0) goto L331;
    L334:
        r13.set(r23, r10, r23 + r21, r10 + r162);
        if (this.isRoundVideo == false) goto L337;
        int r511 = AndroidUtilities.roundMessageInset;
        r13.inset(-r511, -r511);
    L337:
        if (r26 == false) goto L339;
        ((AnimatedFileDrawable) r112).setActualDrawRect(r23, r10, r21, r162);
    L339:
        if (r47 != null) goto L348;
        if (r417 != 90) goto L343;
    L346:
        float r418 = r13.width() / 2.0f;
        float r512 = r13.height() / 2.0f;
        float r65 = r13.centerX();
        float r79 = r13.centerY();
        r112.setBounds((int) (r65 - r512), (int) (r79 - r418), (int) (r65 + r512), (int) (r79 + r418));
        goto L348
    L343:
        if (r417 == 270) goto L346;
        r112.setBounds((int) r13.left, (int) r13.top, (int) r13.right, (int) r13.bottom);
    L348:
        if (this.isVisible == true) goto L350;
    L360:
        r40.restore();
        goto L361
    L350:
        if (r123 >= 29) goto L414;
    L357:
        drawBitmapDrawable(r40, r112, r47, r42);     // Catch: Exception -> L354
    L354:
        e = move-exception;
        onBitmapException(r112);
        FileLog.e(e);
        goto L360
    L414:
        if (this.blendMode == null) goto L356;
        r112.getPaint().setBlendMode(ImageReceiver$$ExternalSyntheticApiModelOutline0.m(this.blendMode));     // Catch: Exception -> L354
        goto L357
    L356:
        r112.getPaint().setBlendMode(null);     // Catch: Exception -> L354
        goto L357
    L331:
        if (this.centerRotation == false) goto L333;
        r40.rotate(r44, r21 / 2.0f, r162 / 2.0f);
        goto L334
    L333:
        r40.rotate(r44, 0.0f, 0.0f);
        goto L334
    L326:
        if (r45 != 2) goto L328;
        r40.scale(1.0f, -1.0f, r21 / 2.0f, r162 / 2.0f);
        goto L328
    L91:
        r85 = r93 / r143;
        goto L93
    L87:
        r84 = r73 / r27;
        goto L88
    L79:
        if (r72 == 270) goto L83;
        r73 = r112.getIntrinsicWidth();
        r93 = r112.getIntrinsicHeight();
        goto L82
    L62:
        if ((r112 instanceof RLottieDrawable) == true) goto L63;
        Bitmap r97 = r112.getBitmap();
        if (r97 != null) goto L67;
    L69:
        r26 = r7;
        int r710 = r44 % 360;
        if (r710 == 90) goto L74;
        if (r710 == 270) goto L74;
        r73 = r97.getWidth();
        r93 = r97.getHeight();
    L75:
        r83 = r112 instanceof ReactionLastFrame;
    L74:
        r73 = r97.getHeight();
        r93 = r97.getWidth();
        goto L75
    L67:
        if (r97.isRecycled() == false) goto L69;
        return;
    L55:
        if (this.gradientShader != null) goto L59;
        if (r43 == null) goto L58;
        this.roundPaint.setColorFilter(r14);
        goto L59
    L58:
        r112.setColorFilter(r14);
        goto L59
    L41:
        if (this.staticThumbDrawable == r41) goto L53;
        r112.setColorFilter(null);
    L43:
        if (r82 == true) goto L53;
        if (r46 == 0) goto L53;
        if (r46 != 1) goto L50;
        if (r43 == null) goto L49;
        this.roundPaint.setColorFilter(selectedColorFilter);
        goto L53
    L49:
        r112.setColorFilter(selectedColorFilter);
        goto L53
    L50:
        if (r43 == null) goto L52;
        this.roundPaint.setColorFilter(selectedGroupColorFilter);
        goto L53
    L52:
        r112.setColorFilter(selectedGroupColorFilter);
        goto L53
    L33:
        if (r8.getColorFilter() == null) goto L35;
        r82 = true;
        goto L36
    L22:
        r8 = r112.getPaint();
        goto L23
    L17:
        r22 = r122;
        if ((r41 instanceof AnimatedFileDrawable) == false) goto L20;
        ((AnimatedFileDrawable) r41).skipFrameUpdate = this.skipUpdateFrame;
        goto L20
    L366:
        float r89 = r9;
        float r711 = r12;
        if (r47 != null) goto L382;
        if (this.isAspectFit == false) goto L379;
        int r419 = r41.getIntrinsicWidth();
        int r513 = r41.getIntrinsicHeight();
        float r66 = this.sideClip;
        float r124 = r711 - (r66 * 2.0f);
        float r67 = r162 - (r66 * 2.0f);
        if (r711 != 0.0f) goto L373;
        float r125 = 1.0f;
    L375:
        if (r162 != 0.0f) goto L377;
        float r68 = 1.0f;
    L378:
        float r69 = Math.max(r125, r68);
        float r420 = (int) (r419 / r69);
        float r514 = (int) (r513 / r69);
        r13.set(r89 + ((r711 - r420) / 2.0f), ((r162 - r514) / 2.0f) + r10, ((r420 + r711) / 2.0f) + r89, r10 + ((r514 + r162) / 2.0f));
    L380:
        r41.setBounds((int) r13.left, (int) r13.top, (int) r13.right, (int) r13.bottom);
        goto L382
    L377:
        r68 = r513 / r67;
        goto L378
    L373:
        r125 = r419 / r124;
        goto L375
    L379:
        r13.set(r89, r10, r89 + r711, r10 + r162);
    L382:
        if (this.isVisible == false) goto L432;
        if (r40 != null) goto L385;
        return;
    L385:
        if ((r41 instanceof SvgHelper.SvgDrawable) == false) goto L389;
        SvgHelper.SvgDrawable r98 = (SvgHelper.SvgDrawable) r41;
        r98.setParent(this);
    L387:
        SvgHelper.SvgDrawable r102 = r98;
    L394:
        if (r14 == null) goto L421;
        if (r41 == null) goto L421;
        r41.setColorFilter(r14);
    L421:
        r41.setAlpha(r42);     // Catch: Exception -> L404
        if (r47 == null) goto L408;
        if (r102 == null) goto L407;
        long r06 = r47.time;     // Catch: Exception -> L404
        if (r06 != 0) goto L406;
        r06 = System.currentTimeMillis();     // Catch: Exception -> L404
    L406:
        ((SvgHelper.SvgDrawable) r41).drawInternal(r40, true, r47.threadIndex, r06, r47.imageX, r47.imageY, r47.imageW, r47.imageH);     // Catch: Exception -> L404
    L411:
        if (r102 == null) goto L434;
        r102.setParent(null);
        return;
    L434:
        return;
    L407:
        r41.draw(r40);     // Catch: Exception -> L404
        goto L411
    L408:
        r41.draw(r40);     // Catch: Exception -> L404
    L404:
        e = move-exception;
        FileLog.e(e);
        goto L411
    L389:
        if ((r41 instanceof ClipRoundedDrawable) == false) goto L393;
        ClipRoundedDrawable r421 = (ClipRoundedDrawable) r41;
        if ((r421.getDrawable() instanceof SvgHelper.SvgDrawable) == false) goto L393;
        r98 = (SvgHelper.SvgDrawable) r421.getDrawable();
        r98.setParent(this);
    L393:
        r102 = null;
        goto L394
    L432:
        return;
    L11:
        r152 = r16;
        goto L10
    L6:
        r9 = this.imageX;
        r10 = this.imageY;
        r11 = this.imageH;
        r12 = this.imageW;
        r13 = this.drawRegion;
        r14 = this.colorFilter;
        r15 = this.roundRadius;
        goto L5
    }

    public boolean getAllowStartAnimation() {
        return this.allowStartAnimation;
    }

    @Keep
    public float getAlpha() {
        return this.overrideAlpha;
    }

    public AnimatedEmojiDrawable getAnimatedEmojiDrawable() {
        Drawable r0 = this.currentMediaDrawable;
        if ((r0 instanceof AnimatedEmojiDrawable) == true) goto L5;
        Drawable r02 = this.currentImageDrawable;
        if ((r02 instanceof AnimatedEmojiDrawable) == true) goto L9;
        Drawable r03 = this.currentThumbDrawable;
        if ((r03 instanceof AnimatedEmojiDrawable) == true) goto L13;
        Drawable r2 = this.staticThumbDrawable;
        if ((r2 instanceof AnimatedEmojiDrawable) == true) goto L17;
        return null;
    L17:
        return (AnimatedEmojiDrawable) r2;
    L13:
        return (AnimatedEmojiDrawable) r03;
    L9:
        return (AnimatedEmojiDrawable) r02;
    L5:
        return (AnimatedEmojiDrawable) r0;
    }

    public int getAnimatedOrientation() {
        AnimatedFileDrawable r0 = getAnimation();
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getOrientation();
    }

    public AnimatedFileDrawable getAnimation() {
        Drawable r0 = this.currentMediaDrawable;
        if ((r0 instanceof AnimatedFileDrawable) == true) goto L5;
        Drawable r02 = this.currentImageDrawable;
        if ((r02 instanceof AnimatedFileDrawable) == true) goto L9;
        Drawable r03 = this.currentThumbDrawable;
        if ((r03 instanceof AnimatedFileDrawable) == true) goto L13;
        Drawable r2 = this.staticThumbDrawable;
        if ((r2 instanceof AnimatedFileDrawable) == true) goto L17;
        return null;
    L17:
        return (AnimatedFileDrawable) r2;
    L13:
        return (AnimatedFileDrawable) r03;
    L9:
        return (AnimatedFileDrawable) r02;
    L5:
        return (AnimatedFileDrawable) r0;
    }

    public int getAutoRepeat() {
        return this.autoRepeat;
    }

    public Bitmap getBitmap() {
        RLottieDrawable r0 = getLottieAnimation();
        if (r0 != null) goto L5;
    L8:
        AnimatedFileDrawable r02 = getAnimation();
        if (r02 != null) goto L11;
    L14:
        Drawable r03 = this.currentMediaDrawable;
        if ((r03 instanceof BitmapDrawable) == true) goto L17;
    L22:
        Drawable r1 = this.currentImageDrawable;
        if ((r1 instanceof BitmapDrawable) == true) goto L25;
    L30:
        Drawable r12 = this.currentThumbDrawable;
        if ((r12 instanceof BitmapDrawable) == true) goto L33;
    L38:
        Drawable r3 = this.staticThumbDrawable;
        if ((r3 instanceof BitmapDrawable) == true) goto L41;
        return null;
    L41:
        return ((BitmapDrawable) r3).getBitmap();
    L33:
        if ((r12 instanceof AnimatedFileDrawable) == true) goto L38;
        if ((r03 instanceof RLottieDrawable) == true) goto L38;
        return ((BitmapDrawable) r12).getBitmap();
    L25:
        if ((r1 instanceof AnimatedFileDrawable) == true) goto L30;
        if ((r03 instanceof RLottieDrawable) == true) goto L30;
        return ((BitmapDrawable) r1).getBitmap();
    L17:
        if ((r03 instanceof AnimatedFileDrawable) == true) goto L22;
        if ((r03 instanceof RLottieDrawable) == true) goto L22;
        return ((BitmapDrawable) r03).getBitmap();
    L11:
        if (r02.hasBitmap() == false) goto L14;
        return r02.getAnimatedBitmap();
    L5:
        if (r0.hasBitmap() == false) goto L8;
        return r0.getAnimatedBitmap();
    }

    public int getBitmapHeight() {
        getDrawable();
        AnimatedFileDrawable r0 = getAnimation();
        if (r0 == null) goto L13;
        int r3 = this.imageOrientation;
        if ((r3 % 360) == 0) goto L12;
        if ((r3 % 360) == 180) goto L12;
        return r0.getIntrinsicWidth();
    L12:
        return r0.getIntrinsicHeight();
    L13:
        RLottieDrawable r02 = getLottieAnimation();
        if (r02 != null) goto L16;
        Bitmap r03 = getBitmap();
        if (r03 != null) goto L25;
        Drawable r32 = this.staticThumbDrawable;
        if (r32 != null) goto L22;
        return 1;
    L22:
        return r32.getIntrinsicHeight();
    L25:
        int r33 = this.imageOrientation;
        if ((r33 % 360) == 0) goto L33;
        if ((r33 % 360) == 180) goto L33;
        return r03.getWidth();
    L33:
        return r03.getHeight();
    L16:
        return r02.getIntrinsicHeight();
    }

    public BitmapHolder getBitmapSafe() {
        AnimatedFileDrawable r0 = getAnimation();
        RLottieDrawable r1 = getLottieAnimation();
        int r3 = 0;
        if (r1 != null) goto L5;
    L8:
        if (r0 != null) goto L10;
    L15:
        Drawable r02 = this.currentMediaDrawable;
        if ((r02 instanceof BitmapDrawable) == true) goto L18;
    L23:
        Drawable r12 = this.currentImageDrawable;
        if ((r12 instanceof BitmapDrawable) == true) goto L26;
    L30:
        Drawable r13 = this.currentThumbDrawable;
        if ((r13 instanceof BitmapDrawable) == true) goto L33;
    L37:
        Drawable r6 = this.staticThumbDrawable;
        if ((r6 instanceof BitmapDrawable) == false) goto L40;
        Bitmap r62 = ((BitmapDrawable) r6).getBitmap();
    L7:
        String r03 = null;
    L41:
        if (r62 != null) goto L43;
        return null;
    L43:
        return new BitmapHolder(r62, r03, r3);
    L40:
        r62 = null;
        r03 = null;
        goto L41
    L33:
        if ((r13 instanceof AnimatedFileDrawable) == true) goto L37;
        if ((r02 instanceof RLottieDrawable) == true) goto L37;
        Bitmap r04 = ((BitmapDrawable) r13).getBitmap();
        String r63 = this.currentThumbKey;
    L22:
        Bitmap r5 = r04;
        r03 = r63;
        r62 = r5;
        goto L41
    L26:
        if ((r12 instanceof AnimatedFileDrawable) == true) goto L30;
        if ((r02 instanceof RLottieDrawable) == true) goto L30;
        r04 = ((BitmapDrawable) r12).getBitmap();
        r63 = this.currentImageKey;
        goto L22
    L18:
        if ((r02 instanceof AnimatedFileDrawable) == true) goto L23;
        if ((r02 instanceof RLottieDrawable) == true) goto L23;
        r04 = ((BitmapDrawable) r02).getBitmap();
        r63 = this.currentMediaKey;
        goto L22
    L10:
        if (r0.hasBitmap() == false) goto L15;
        r62 = r0.getAnimatedBitmap();
        r3 = r0.getOrientation();
        if (r3 == 0) goto L7;
        return new BitmapHolder(Bitmap.createBitmap(r62), null, r3);
    L5:
        if (r1.hasBitmap() == false) goto L8;
        r62 = r1.getAnimatedBitmap();
        goto L7
    }

    public int getBitmapWidth() {
        getDrawable();
        AnimatedFileDrawable r0 = getAnimation();
        if (r0 == null) goto L13;
        int r3 = this.imageOrientation;
        if ((r3 % 360) == 0) goto L12;
        if ((r3 % 360) == 180) goto L12;
        return r0.getIntrinsicHeight();
    L12:
        return r0.getIntrinsicWidth();
    L13:
        RLottieDrawable r02 = getLottieAnimation();
        if (r02 != null) goto L16;
        Bitmap r03 = getBitmap();
        if (r03 != null) goto L25;
        Drawable r32 = this.staticThumbDrawable;
        if (r32 != null) goto L22;
        return 1;
    L22:
        return r32.getIntrinsicWidth();
    L25:
        int r33 = this.imageOrientation;
        if ((r33 % 360) == 0) goto L33;
        if ((r33 % 360) == 180) goto L33;
        return r03.getHeight();
    L33:
        return r03.getWidth();
    L16:
        return r02.getIntrinsicWidth();
    }

    public int getCacheType() {
        return this.currentCacheType;
    }

    public float getCenterX() {
        return this.imageX + (this.imageW / 2.0f);
    }

    public float getCenterY() {
        return this.imageY + (this.imageH / 2.0f);
    }

    public int getCurrentAccount() {
        return this.currentAccount;
    }

    public float getCurrentAlpha() {
        return this.currentAlpha;
    }

    public RectF getDrawRegion() {
        return this.drawRegion;
    }

    public Drawable getDrawable() {
        Drawable r0 = this.currentMediaDrawable;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Drawable r02 = this.currentImageDrawable;
        if (r02 == null) goto L8;
        return r02;
    L8:
        Drawable r03 = this.currentThumbDrawable;
        if (r03 == null) goto L11;
        return r03;
    L11:
        Drawable r1 = this.staticThumbDrawable;
        if (r1 == null) goto L14;
        return r1;
    L14:
        return null;
    }

    public BitmapHolder getDrawableSafe() {
        Drawable r0 = this.currentMediaDrawable;
        if ((r0 instanceof BitmapDrawable) == true) goto L5;
    L9:
        Drawable r1 = this.currentImageDrawable;
        if ((r1 instanceof BitmapDrawable) == true) goto L12;
    L17:
        r1 = this.currentThumbDrawable;
        if ((r1 instanceof BitmapDrawable) == true) goto L20;
    L24:
        r0 = this.staticThumbDrawable;
        if ((r0 instanceof BitmapDrawable) == false) goto L27;
        String r4 = null;
    L28:
        if (r0 != null) goto L30;
        return null;
    L30:
        return new BitmapHolder(r0, r4, 0);
    L27:
        r4 = null;
        r0 = null;
        goto L28
    L20:
        if ((r1 instanceof AnimatedFileDrawable) == true) goto L24;
        if ((r0 instanceof RLottieDrawable) == true) goto L24;
        r4 = this.currentThumbKey;
    L16:
        r0 = r1;
        goto L28
    L12:
        if ((r1 instanceof AnimatedFileDrawable) == true) goto L17;
        if ((r0 instanceof RLottieDrawable) == true) goto L17;
        r4 = this.currentImageKey;
        goto L16
    L5:
        if ((r0 instanceof AnimatedFileDrawable) == true) goto L9;
        if ((r0 instanceof RLottieDrawable) == true) goto L9;
        r4 = this.currentMediaKey;
        goto L28
    }

    public String getExt() {
        return this.currentExt;
    }

    public int getFileLoadingPriority() {
        return this.fileLoadingPriority;
    }

    public float getImageAspectRatio() {
        int r0 = this.imageOrientation % 180;
        RectF r1 = this.drawRegion;
        if (r0 == 0) goto L7;
        float r02 = r1.height();
        float r2 = this.drawRegion.width();
    L6:
        return r02 / r2;
    L7:
        r02 = r1.width();
        r2 = this.drawRegion.height();
        goto L6
    }

    public Drawable getImageDrawable() {
        return this.currentImageDrawable;
    }

    public String getImageFilter() {
        return this.currentImageFilter;
    }

    public float getImageHeight() {
        return this.imageH;
    }

    public String getImageKey() {
        return this.currentImageKey;
    }

    public ImageLocation getImageLocation() {
        return this.currentImageLocation;
    }

    public float getImageWidth() {
        return this.imageW;
    }

    public float getImageX() {
        return this.imageX;
    }

    public float getImageX2() {
        return this.imageX + this.imageW;
    }

    public float getImageY() {
        return this.imageY;
    }

    public float getImageY2() {
        return this.imageY + this.imageH;
    }

    public int getInvert() {
        return this.imageInvert;
    }

    public ArrayList<Runnable> getLoadingOperations() {
        return this.loadingOperations;
    }

    public RLottieDrawable getLottieAnimation() {
        Drawable r0 = this.currentMediaDrawable;
        if ((r0 instanceof RLottieDrawable) == true) goto L5;
        Drawable r02 = this.currentImageDrawable;
        if ((r02 instanceof RLottieDrawable) == true) goto L9;
        Drawable r03 = this.currentThumbDrawable;
        if ((r03 instanceof RLottieDrawable) == true) goto L13;
        Drawable r2 = this.staticThumbDrawable;
        if ((r2 instanceof RLottieDrawable) == true) goto L17;
        return null;
    L17:
        return (RLottieDrawable) r2;
    L13:
        return (RLottieDrawable) r03;
    L9:
        return (RLottieDrawable) r02;
    L5:
        return (RLottieDrawable) r0;
    }

    public Object getMark() {
        return this.mark;
    }

    public Drawable getMediaDrawable() {
        return this.currentMediaDrawable;
    }

    public String getMediaFilter() {
        return this.currentMediaFilter;
    }

    public String getMediaKey() {
        return this.currentMediaKey;
    }

    public ImageLocation getMediaLocation() {
        return this.currentMediaLocation;
    }

    public int getNewGuid() {
        int r0 = this.currentGuid + 1;
        this.currentGuid = r0;
        return r0;
    }

    public int getOrientation() {
        return this.imageOrientation;
    }

    public int getParam() {
        return this.param;
    }

    public Object getParentObject() {
        return this.currentParentObject;
    }

    public void getParentPosition(int[] r1) {
        View r0 = this.parentView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.getLocationInWindow(r1);
    }

    public View getParentView() {
        return this.parentView;
    }

    public boolean getPressed() {
        if (this.isPressed == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public TLRPC.Document getQualityThumbDocument() {
        return this.qulityThumbDocument;
    }

    public int[] getRoundRadius() {
        return this.roundRadius;
    }

    public int[] getRoundRadius(boolean r2) {
        if (this.useRoundRadius == true) goto L8;
        if (r2 == false) goto L8;
        return this.emptyRoundRadius;
    L8:
        return this.roundRadius;
    }

    public float getSideClip() {
        return this.sideClip;
    }

    public long getSize() {
        return this.currentSize;
    }

    public Drawable getStaticThumb() {
        return this.staticThumbDrawable;
    }

    public ImageLocation getStrippedLocation() {
        return this.strippedLocation;
    }

    public int getTag(int r2) {
        if (r2 != 1) goto L7;
        return this.thumbTag;
    L7:
        if (r2 != 3) goto L11;
        return this.mediaTag;
    L11:
        return this.imageTag;
    }

    public Drawable getThumb() {
        return this.currentThumbDrawable;
    }

    public Bitmap getThumbBitmap() {
        Drawable r0 = this.currentThumbDrawable;
        if ((r0 instanceof BitmapDrawable) == true) goto L5;
        Drawable r2 = this.staticThumbDrawable;
        if ((r2 instanceof BitmapDrawable) == true) goto L9;
        return null;
    L9:
        return ((BitmapDrawable) r2).getBitmap();
    L5:
        return ((BitmapDrawable) r0).getBitmap();
    }

    public BitmapHolder getThumbBitmapSafe() {
        Drawable r0 = this.currentThumbDrawable;
        if ((r0 instanceof BitmapDrawable) == false) goto L5;
        Bitmap r02 = ((BitmapDrawable) r0).getBitmap();
        String r3 = this.currentThumbKey;
    L9:
        if (r02 != null) goto L11;
        return null;
    L11:
        return new BitmapHolder(r02, r3, 0);
    L5:
        Drawable r32 = this.staticThumbDrawable;
        if ((r32 instanceof BitmapDrawable) == false) goto L8;
        r02 = ((BitmapDrawable) r32).getBitmap();
        r3 = null;
        goto L9
    L8:
        r3 = null;
        r02 = null;
        goto L9
    }

    public String getThumbFilter() {
        return this.currentThumbFilter;
    }

    public String getThumbKey() {
        return this.currentThumbKey;
    }

    public ImageLocation getThumbLocation() {
        return this.currentThumbLocation;
    }

    public String getUniqKeyPrefix() {
        return this.uniqKeyPrefix;
    }

    public boolean getVisible() {
        return this.isVisible;
    }

    public boolean hasBitmapImage() {
        if (this.currentImageDrawable == null) goto L5;
        return true;
    L5:
        if (this.currentThumbDrawable == null) goto L7;
        return true;
    L7:
        if (this.staticThumbDrawable == null) goto L9;
        return true;
    L9:
        if (this.currentMediaDrawable != null) goto L17;
        return false;
    L17:
        return true;
    }

    public boolean hasImageLoaded() {
        if (this.currentImageDrawable == null) goto L5;
        return true;
    L5:
        if (this.currentMediaDrawable != null) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean hasImageSet() {
        if (this.currentImageDrawable == null) goto L5;
        return true;
    L5:
        if (this.currentMediaDrawable == null) goto L7;
        return true;
    L7:
        if (this.currentThumbDrawable == null) goto L9;
        return true;
    L9:
        if (this.staticThumbDrawable == null) goto L11;
        return true;
    L11:
        if (this.currentImageKey == null) goto L13;
        return true;
    L13:
        if (this.currentMediaKey != null) goto L23;
        return false;
    L23:
        return true;
    }

    public boolean hasMediaSet() {
        if (this.currentMediaDrawable == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasNotThumb() {
        if (this.currentImageDrawable == null) goto L5;
        return true;
    L5:
        if (this.currentMediaDrawable == null) goto L7;
        return true;
    L7:
        if ((this.staticThumbDrawable instanceof VectorAvatarThumbDrawable) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public boolean hasNotThumbOrOnlyStaticThumb() {
        if (this.currentImageDrawable == null) goto L5;
        return true;
    L5:
        if (this.currentMediaDrawable != null) goto L23;
        Drawable r0 = this.staticThumbDrawable;
        if ((r0 instanceof VectorAvatarThumbDrawable) == true) goto L24;
        if (r0 != null) goto L10;
        return false;
    L10:
        if ((r0 instanceof AvatarDrawable) == false) goto L12;
        return false;
    L12:
        if (this.currentImageKey == null) goto L14;
        return false;
    L14:
        if (this.currentMediaKey == null) goto L25;
        return false;
    L25:
        return true;
    L24:
        return true;
    L23:
        return true;
    }

    public boolean hasStaticThumb() {
        if (this.staticThumbDrawable == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.Components.AnimatedEmojiSpan.InvalidateHolder
    public void invalidate() {
        View r0 = this.parentView;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.invalidateAll == false) goto L9;
        r0.invalidate();
        return;
    L9:
        float r1 = this.imageX;
        float r3 = this.imageY;
        r0.invalidate((int) r1, (int) r3, (int) (r1 + this.imageW), (int) (r3 + this.imageH));
    }

    public boolean isAllowStartAnimation() {
        return this.allowStartAnimation;
    }

    public boolean isAnimationRunning() {
        AnimatedFileDrawable r0 = getAnimation();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isRunning() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isAspectFit() {
        return this.isAspectFit;
    }

    public boolean isAttachedToWindow() {
        return this.attachedToWindow;
    }

    public boolean isCrossfadingWithOldImage() {
        if (this.crossfadeWithOldImage == true) goto L5;
        return false;
    L5:
        if (this.crossfadeImage != null) goto L7;
        return false;
    L7:
        if (this.crossfadingWithThumb == true) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isCurrentKeyQuality() {
        return this.currentKeyQuality;
    }

    public boolean isForceLoding() {
        return this.forceLoding;
    }

    public boolean isForcePreview() {
        return this.forcePreview;
    }

    public boolean isInsideImage(float r3, float r4) {
        float r0 = this.imageX;
        if (r3 >= r0) goto L5;
        return false;
    L5:
        if (r3 > (r0 + this.imageW)) goto L14;
        float r32 = this.imageY;
        if (r4 >= r32) goto L9;
        return false;
    L9:
        if (r4 > (r32 + this.imageH)) goto L16;
        return true;
    L16:
        return false;
    L14:
        return false;
    }

    public boolean isLottieRunning() {
        RLottieDrawable r0 = getLottieAnimation();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isRunning() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isNeedsQualityThumb() {
        return this.needsQualityThumb;
    }

    public boolean isShouldGenerateQualityThumb() {
        return this.shouldGenerateQualityThumb;
    }

    public void moveImageToFront() {
        ImageLoader.getInstance().moveToFront(this.currentImageKey);
        ImageLoader.getInstance().moveToFront(this.currentThumbKey);
    }

    public void moveLottieToFront() {
        Drawable r0 = this.currentMediaDrawable;
        if ((r0 instanceof RLottieDrawable) == false) goto L5;
        BitmapDrawable r02 = (BitmapDrawable) r0;
        String r3 = this.currentMediaKey;
    L9:
        if (r3 == null) goto L15;
        if (r02 == null) goto L16;
        ImageLoader.getInstance().moveToFront(r3);
        if (ImageLoader.getInstance().isInMemCache(r3, true) == true) goto L17;
        ImageLoader.getInstance().getLottieMemCahce().put(r3, r02);
        return;
    L17:
        return;
    L16:
        return;
    L15:
        return;
    L5:
        Drawable r03 = this.currentImageDrawable;
        if ((r03 instanceof RLottieDrawable) == false) goto L8;
        r02 = (BitmapDrawable) r03;
        r3 = this.currentImageKey;
        goto L9
    L8:
        r02 = null;
        r3 = null;
        goto L9
    }

    public boolean onAttachedToWindow() {
        if (this.attachedToWindow == false) goto L5;
        return false;
    L5:
        this.attachedToWindow = true;
        this.currentOpenedLayerFlags = NotificationCenter.getGlobalInstance().getCurrentHeavyOperationFlags() & (~this.currentLayerNum);
        if (this.ignoreNotifications == true) goto L12;
        NotificationCenter.ObserversGroup r2 = this.observersGroup;
        if (r2 == null) goto L10;
        r2.removeAllObservers();
        this.observersGroup = null;
    L10:
        this.observersGroup = NotificationCenter.getGlobalInstance().createWeakObserversGroup(this).add(NotificationCenter.didReplacedPhotoInMemCache).add(NotificationCenter.stopAllHeavyOperations).add(NotificationCenter.startAllHeavyOperations);
    L12:
        if (setBackupImage() == false) goto L14;
        return true;
    L14:
        RLottieDrawable r0 = getLottieAnimation();
        if (r0 == null) goto L17;
        r0.addParentView(this);
        r0.setAllowVibration(this.allowLottieVibration);
    L17:
        if (r0 != null) goto L19;
    L25:
        AnimatedFileDrawable r02 = getAnimation();
        if (r02 == null) goto L28;
        r02.addParent(this);
    L28:
        if (r02 != null) goto L30;
    L34:
        AnimatedEmojiDrawable r03 = getAnimatedEmojiDrawable();
        if (r03 == null) goto L38;
        r03.addView(this);
    L38:
        if (NotificationCenter.getGlobalInstance().isAnimationInProgress() == false) goto L40;
        didReceivedNotification(NotificationCenter.stopAllHeavyOperations, this.currentAccount, new Object[]{512});
    L40:
        Object r04 = this.staticThumbDrawable;
        if ((r04 instanceof AttachableDrawable) == false) goto L44;
        ((AttachableDrawable) r04).onAttachedToWindow(this);
    L44:
        if (this.decorators == null) goto L49;
        int r05 = 0;
    L47:
        if (r05 >= this.decorators.size()) goto L49;
        this.decorators.get(r05).onAttachedToWindow(this);
        r05 = r05 + 1;
    L49:
        return false;
    L30:
        if (this.allowStartAnimation == false) goto L34;
        if (this.currentOpenedLayerFlags != 0) goto L34;
        r02.checkRepeat();
        invalidate();
        goto L34
    L19:
        if (this.allowStartLottieAnimation == false) goto L25;
        if (r0.isHeavyDrawable() == true) goto L23;
    L24:
        r0.start();
        goto L25
    L23:
        if (this.currentOpenedLayerFlags != 0) goto L25;
        goto L24
    }

    public void onDetachedFromWindow() {
        if (this.attachedToWindow == false) goto L47;
        int r0 = 0;
        this.attachedToWindow = false;
        if (this.currentImageLocation != null) goto L14;
        if (this.currentMediaLocation != null) goto L14;
        if (this.currentThumbLocation != null) goto L14;
        if (this.staticThumbDrawable != null) goto L14;
    L18:
        if (this.ignoreNotifications == true) goto L22;
        NotificationCenter.ObserversGroup r1 = this.observersGroup;
        if (r1 == null) goto L22;
        r1.removeAllObservers();
        this.observersGroup = null;
    L22:
        Object r12 = this.staticThumbDrawable;
        if ((r12 instanceof AttachableDrawable) == false) goto L26;
        ((AttachableDrawable) r12).onDetachedFromWindow(this);
    L26:
        if (this.staticThumbDrawable == null) goto L28;
        setStaticDrawable(null);
        this.staticThumbShader = null;
    L28:
        clearImage();
        this.roundPaint.setShader(null);
        if (this.isPressed != 0) goto L31;
        this.pressedProgress = 0.0f;
    L31:
        AnimatedEmojiDrawable r13 = getAnimatedEmojiDrawable();
        if (r13 == null) goto L34;
        r13.removeView(this);
    L34:
        AnimatedFileDrawable r14 = getAnimation();
        if (r14 == null) goto L37;
        r14.removeParent(this);
    L37:
        RLottieDrawable r15 = getLottieAnimation();
        if (r15 == null) goto L41;
        r15.removeParentView(this);
    L41:
        if (this.decorators != null) goto L43;
        return;
    L43:
        if (r0 >= this.decorators.size()) goto L48;
        this.decorators.get(r0).onDetachedFromWidnow();
        r0 = r0 + 1;
        goto L43
    L48:
        return;
    L14:
        if (this.setImageBackup != null) goto L16;
        this.setImageBackup = new SetImageBackup(null);
    L16:
        SetImageBackup r16 = this.setImageBackup;
        r16.mediaLocation = this.currentMediaLocation;
        r16.mediaFilter = this.currentMediaFilter;
        r16.imageLocation = this.currentImageLocation;
        r16.imageFilter = this.currentImageFilter;
        r16.thumbLocation = this.currentThumbLocation;
        r16.thumbFilter = this.currentThumbFilter;
        r16.thumb = this.staticThumbDrawable;
        r16.size = this.currentSize;
        r16.ext = this.currentExt;
        r16.cacheType = this.currentCacheType;
        r16.parentObject = this.currentParentObject;
        goto L18
    }

    public void recycleBitmap(String r8, int r9) {
        if (r9 != 3) goto L5;
        String r3 = this.currentMediaKey;
        Drawable r4 = this.currentMediaDrawable;
    L10:
        if (r3 == null) goto L19;
        if (r3.startsWith("-") == false) goto L14;
    L15:
        String r5 = ImageLoader.getInstance().getReplacedKey(r3);
        if (r5 == null) goto L19;
        r3 = r5;
        goto L19
    L14:
        if (r3.startsWith("strippedmessage-") == true) goto L15;
    L19:
        if ((r4 instanceof RLottieDrawable) == false) goto L22;
        ((RLottieDrawable) r4).removeParentView(this);
    L22:
        if ((r4 instanceof AnimatedFileDrawable) == false) goto L25;
        ((AnimatedFileDrawable) r4).removeParent(this);
    L25:
        if ((r4 instanceof AnimatedEmojiDrawable) == false) goto L27;
        ((AnimatedEmojiDrawable) r4).removeView(this);
    L27:
        if (r3 == null) goto L58;
        if (r8 != null) goto L30;
    L31:
        if (r4 == null) goto L58;
        if ((r4 instanceof RLottieDrawable) == false) goto L39;
        RLottieDrawable r42 = (RLottieDrawable) r4;
        boolean r82 = ImageLoader.getInstance().decrementUseCount(r3);
        if (ImageLoader.getInstance().isInMemCache(r3, true) == true) goto L58;
        if (r82 == false) goto L58;
        r42.recycle(false);
        goto L58
    L39:
        if ((r4 instanceof AnimatedFileDrawable) == false) goto L52;
        AnimatedFileDrawable r43 = (AnimatedFileDrawable) r4;
        if (r43.isWebmSticker == false) goto L49;
        boolean r83 = ImageLoader.getInstance().decrementUseCount(r3);
        if (ImageLoader.getInstance().isInMemCache(r3, true) == true) goto L46;
        if (r83 == false) goto L58;
        r43.recycle();
        goto L58
    L46:
        if (r83 == false) goto L58;
        r43.stop();
        goto L58
    L49:
        if (r43.hasParents() == true) goto L58;
        r43.recycle();
        goto L58
    L52:
        if ((r4 instanceof BitmapDrawable) == false) goto L58;
        Bitmap r84 = ((BitmapDrawable) r4).getBitmap();
        boolean r44 = ImageLoader.getInstance().decrementUseCount(r3);
        if (ImageLoader.getInstance().isInMemCache(r3, false) == true) goto L58;
        if (r44 == false) goto L58;
        ArrayList r32 = new ArrayList();
        r32.add(r84);
        AndroidUtilities.recycleBitmaps(r32);
        goto L58
    L30:
        if (r8.equals(r3) == false) goto L31;
    L58:
        if (r9 != 3) goto L61;
        this.currentMediaKey = null;
        this.currentMediaDrawable = null;
        this.mediaShader = null;
        return;
    L61:
        if (r9 != 2) goto L64;
        this.crossfadeKey = null;
        this.crossfadeImage = null;
        this.crossfadeShader = null;
        return;
    L64:
        if (r9 != 1) goto L67;
        this.currentThumbDrawable = null;
        this.currentThumbKey = null;
        this.thumbShader = null;
        return;
    L67:
        this.currentImageDrawable = null;
        this.currentImageKey = null;
        this.imageShader = null;
        return;
    L5:
        if (r9 != 2) goto L7;
        r3 = this.crossfadeKey;
        r4 = this.crossfadeImage;
        goto L10
    L7:
        if (r9 != 1) goto L9;
        r3 = this.currentThumbKey;
        r4 = this.currentThumbDrawable;
        goto L10
    L9:
        r3 = this.currentImageKey;
        r4 = this.currentImageDrawable;
        goto L10
    }

    public void setAllowDecodeSingleFrame(boolean r1) {
        this.allowDecodeSingleFrame = r1;
    }

    public void setAllowDrawWhileCacheGenerating(boolean r1) {
        this.allowDrawWhileCacheGenerating = r1;
    }

    public void setAllowLoadingOnAttachedOnly(boolean r1) {
        this.allowLoadingOnAttachedOnly = r1;
    }

    public void setAllowLottieVibration(boolean r1) {
        this.allowLottieVibration = r1;
    }

    public void setAllowStartAnimation(boolean r1) {
        this.allowStartAnimation = r1;
    }

    public void setAllowStartLottieAnimation(boolean r1) {
        this.allowStartLottieAnimation = r1;
    }

    @Keep
    public void setAlpha(float r1) {
        this.overrideAlpha = r1;
    }

    public void setAspectFit(boolean r1) {
        this.isAspectFit = r1;
    }

    public void setAutoRepeat(int r1) {
        this.autoRepeat = r1;
        RLottieDrawable r0 = getLottieAnimation();
        if (r0 == null) goto L6;
        r0.setAutoRepeat(r1);
        return;
    }

    public void setAutoRepeatCount(int r2) {
        this.autoRepeatCount = r2;
        if (getLottieAnimation() == null) goto L6;
        getLottieAnimation().setAutoRepeatCount(r2);
        return;
    L6:
        this.animatedFileDrawableRepeatMaxCount = r2;
        if (getAnimation() == null) goto L10;
        getAnimation().repeatCount = 0;
        return;
    }

    public void setAutoRepeatTimeout(long r3) {
        this.autoRepeatTimeout = r3;
        RLottieDrawable r32 = getLottieAnimation();
        if (r32 == null) goto L6;
        r32.setAutoRepeatTimeout(this.autoRepeatTimeout);
        return;
    }

    public boolean setBackupImage() {
        SetImageBackup r1 = this.setImageBackup;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (SetImageBackup.m4231$$Nest$misSet(r1) == false) goto L35;
        SetImageBackup r13 = this.setImageBackup;
        this.setImageBackup = null;
        Drawable r2 = r13.thumb;
        if ((r2 instanceof BitmapDrawable) == false) goto L17;
        BitmapDrawable r22 = (BitmapDrawable) r2;
        if ((r22 instanceof RLottieDrawable) == true) goto L17;
        if ((r22 instanceof AnimatedFileDrawable) == true) goto L17;
        if (r22.getBitmap() == null) goto L17;
        if (r22.getBitmap().isRecycled() == false) goto L17;
        r13.thumb = null;
    L17:
        setImage(r13.mediaLocation, r13.mediaFilter, r13.imageLocation, r13.imageFilter, r13.thumbLocation, r13.thumbFilter, r13.thumb, r13.size, r13.ext, r13.parentObject, r13.cacheType);
        SetImageBackup.m4230$$Nest$mclear(r13);
        this.setImageBackup = r13;
        RLottieDrawable r12 = getLottieAnimation();
        if (r12 == null) goto L20;
        r12.setAllowVibration(this.allowLottieVibration);
    L20:
        if (r12 != null) goto L22;
        return true;
    L22:
        if (this.allowStartLottieAnimation == true) goto L24;
        return true;
    L24:
        if (r12.isHeavyDrawable() == true) goto L26;
    L27:
        r12.start();
        return true;
    L26:
        if (this.currentOpenedLayerFlags == 0) goto L27;
        return true;
    L35:
        return false;
    }

    public void setBlendMode(Object r1) {
        this.blendMode = r1;
        invalidate();
    }

    public void setColorFilter(ColorFilter r1) {
        this.colorFilter = r1;
    }

    public void setCrossfadeAlpha(byte r1) {
        this.crossfadeAlpha = r1;
    }

    public void setCrossfadeByScale(float r1) {
        this.crossfadeByScale = r1;
    }

    public void setCrossfadeDuration(int r1) {
        this.crossfadeDuration = r1;
    }

    public void setCrossfadeWithOldImage(boolean r1) {
        this.crossfadeWithOldImage = r1;
    }

    public void setCurrentAccount(int r1) {
        this.currentAccount = r1;
    }

    public void setCurrentAlpha(float r1) {
        this.currentAlpha = r1;
    }

    public void setCurrentTime(long r1) {
        this.currentTime = r1;
    }

    public void setDelegate(ImageReceiverDelegate r1) {
        this.delegate = r1;
    }

    public BackgroundThreadDrawHolder setDrawInBackgroundThread(BackgroundThreadDrawHolder r4, int r5) {
        if (r4 != null) goto L4;
        r4 = new BackgroundThreadDrawHolder();
    L4:
        r4.threadIndex = r5;
        BackgroundThreadDrawHolder.m4214$$Nest$fputanimation(r4, getAnimation());
        BackgroundThreadDrawHolder.m4222$$Nest$fputlottieDrawable(r4, getLottieAnimation());
        boolean r52 = false;
        int r0 = 0;
    L6:
        if (r0 >= 4) goto L8;
        BackgroundThreadDrawHolder.m4210$$Nest$fgetroundRadius(r4)[r0] = this.roundRadius[r0];
        r0 = r0 + 1;
        goto L6
    L8:
        BackgroundThreadDrawHolder.m4223$$Nest$fputmediaDrawable(r4, this.currentMediaDrawable);
        BackgroundThreadDrawHolder.m4224$$Nest$fputmediaShader(r4, this.mediaShader);
        BackgroundThreadDrawHolder.m4220$$Nest$fputimageDrawable(r4, this.currentImageDrawable);
        BackgroundThreadDrawHolder.m4221$$Nest$fputimageShader(r4, this.imageShader);
        BackgroundThreadDrawHolder.m4228$$Nest$fputthumbDrawable(r4, this.currentThumbDrawable);
        BackgroundThreadDrawHolder.m4229$$Nest$fputthumbShader(r4, this.thumbShader);
        r4.staticThumbShader = this.staticThumbShader;
        BackgroundThreadDrawHolder.m4227$$Nest$fputstaticThumbDrawable(r4, this.staticThumbDrawable);
        BackgroundThreadDrawHolder.m4215$$Nest$fputcrossfadeImage(r4, this.crossfadeImage);
        r4.colorFilter = this.colorFilter;
        BackgroundThreadDrawHolder.m4218$$Nest$fputcrossfadingWithThumb(r4, this.crossfadingWithThumb);
        BackgroundThreadDrawHolder.m4217$$Nest$fputcrossfadeWithOldImage(r4, this.crossfadeWithOldImage);
        BackgroundThreadDrawHolder.m4219$$Nest$fputcurrentAlpha(r4, this.currentAlpha);
        BackgroundThreadDrawHolder.m4225$$Nest$fputpreviousAlpha(r4, this.previousAlpha);
        BackgroundThreadDrawHolder.m4216$$Nest$fputcrossfadeShader(r4, this.crossfadeShader);
        if (BackgroundThreadDrawHolder.m4197$$Nest$fgetanimation(r4) == null) goto L13;
        if (BackgroundThreadDrawHolder.m4197$$Nest$fgetanimation(r4).hasBitmap() == true) goto L13;
    L16:
        r52 = true;
    L17:
        r4.animationNotReady = r52;
        r4.imageX = this.imageX;
        r4.imageY = this.imageY;
        r4.imageW = this.imageW;
        r4.imageH = this.imageH;
        r4.overrideAlpha = this.overrideAlpha;
        return r4;
    L13:
        if (BackgroundThreadDrawHolder.m4205$$Nest$fgetlottieDrawable(r4) == null) goto L17;
        if (BackgroundThreadDrawHolder.m4205$$Nest$fgetlottieDrawable(r4).hasBitmap() == true) goto L17;
        goto L16
    }

    public void setEmojiPaused(boolean r2) {
        if (this.emojiPaused == r2) goto L16;
        this.emojiPaused = r2;
        this.allowStartLottieAnimation = !r2;
        RLottieDrawable r1 = getLottieAnimation();
        if (r1 == null) goto L14;
        if (r2 == false) goto L11;
        r1.stop();
        return;
    L11:
        if (r1.isRunning() == true) goto L15;
        r1.start();
        return;
    L15:
        return;
    L14:
        return;
    }

    public void setFileLoadingPriority(int r2) {
        if (this.fileLoadingPriority == r2) goto L10;
        this.fileLoadingPriority = r2;
        if (this.attachedToWindow == true) goto L7;
        return;
    L7:
        if (hasImageSet() == false) goto L12;
        ImageLoader.getInstance().changeFileLoadingPriorityForImageReceiver(this);
        return;
    L12:
        return;
    }

    public void setForUserOrChat(TLObject r2, Drawable r3) {
        setForUserOrChat(r2, r3, null);
    }

    public void setForUserOrChat(TLObject r8, Drawable r9, Object r10) {
        setForUserOrChat(r8, r9, r10, false, 0, false);
    }

    public void setForUserOrChat(TLObject r18, Drawable r19, Object r20, boolean r21, int r22, boolean r23) {
        if (r20 != null) goto L5;
        Object r5 = r18;
    L6:
        setUseRoundForThumbDrawable(true);
        TLRPC.VideoSize r6 = null;
        ImageLocation r62 = null;
        r62 = null;
        r62 = null;
        r62 = null;
        r62 = null;
        r62 = null;
        r62 = null;
        r62 = null;
        r62 = null;
        r62 = null;
        r62 = null;
        r6 = null;
        if ((r18 instanceof TLRPC.User) == false) goto L62;
        TLRPC.User r4 = (TLRPC.User) r18;
        boolean r8 = r4.premium;
        TLRPC.UserProfilePhoto r9 = r4.photo;
        if (r9 == null) goto L59;
        BitmapDrawable r10 = r9.strippedBitmap;
        if (r9.stripped_thumb == null) goto L13;
        boolean r92 = true;
    L14:
        if (r22 != 3) goto L23;
        TLRPC.UserFull r11 = MessagesController.getInstance(this.currentAccount).getUserFull(r4.id);
        if (r11 == null) goto L23;
        if (r4.photo.personal == false) goto L20;
        TLRPC.Photo r112 = r11.personal_photo;
    L21:
        if (r112 == null) goto L23;
        TLRPC.VideoSize r113 = FileLoader.getVectorMarkupVideoSize(r112);
    L24:
        if (r113 != null) goto L60;
        if (r21 == false) goto L60;
        if (MessagesController.getInstance(this.currentAccount).isPremiumUser(r4) == false) goto L60;
        if (r4.photo.has_video == false) goto L60;
        if (LiteMode.isEnabled(1024) == false) goto L60;
        TLRPC.UserFull r12 = MessagesController.getInstance(this.currentAccount).getUserFull(r4.id);
        if (r12 != null) goto L36;
        MessagesController.getInstance(this.currentAccount).loadFullUser(r4, this.currentGuid, false);
        goto L60
    L36:
        if (r4.photo.personal == false) goto L38;
        TLRPC.Photo r42 = r12.personal_photo;
    L39:
        if (r42 == null) goto L60;
        r113 = FileLoader.getVectorMarkupVideoSize(r42);
        if (r113 != null) goto L60;
        ArrayList<TLRPC.VideoSize> r122 = r42.video_sizes;
        if (r122 == null) goto L60;
        if (r122.isEmpty() == true) goto L60;
        TLRPC.VideoSize r63 = FileLoader.getClosestVideoSizeWithSize(r122, 100);
        int r14 = 0;
    L48:
        if (r14 >= r122.size()) goto L58;
        TLRPC.VideoSize r15 = r122.get(r14);
        if ("p".equals(r15.type) == false) goto L53;
        r63 = r15;
    L53:
        if ((r15 instanceof TLRPC.TL_videoSizeEmojiMarkup) == false) goto L55;
    L56:
        r113 = r15;
    L57:
        r14 = r14 + 1;
        goto L48
    L55:
        if ((r15 instanceof TLRPC.TL_videoSizeStickerMarkup) == false) goto L57;
    L58:
        r62 = ImageLocation.getForPhoto(r63, r42);
        goto L60
    L38:
        r42 = r12.profile_photo;
    L60:
        ImageLocation r43 = r62;
        BitmapDrawable r7 = r10;
        r6 = r113;
    L71:
        if (r6 == null) goto L75;
        if (r22 == 0) goto L75;
        setImageBitmap(new VectorAvatarThumbDrawable(r6, r8, r22));
        return;
    L75:
        int r2 = this.currentAccount;
        if (r23 == true) goto L79;
        ImageLocation r24 = ImageLocation.getForUserOrChat(r2, r18, 1);
        String r3 = "50_50";
    L78:
        String r16 = r3;
        ImageLocation r32 = r24;
        if (r43 == null) goto L83;
        setImage(r43, "avatar", r32, r16, null, null, r7, 0, null, r5, 0);
        this.animatedFileDrawableRepeatMaxCount = 3;
        return;
    L83:
        Object r114 = r5;
        BitmapDrawable r33 = r7;
        if (r33 == null) goto L88;
        setImage(r32, r16, r33, null, r114, 0);
        return;
    L88:
        if (r92 == false) goto L91;
        setImage(r32, r16, ImageLocation.getForUserOrChat(this.currentAccount, r18, 2), "50_50_b", r19, r114, 0);
        return;
    L91:
        setImage(r32, r16, r19, null, r114, 0);
        return;
    L79:
        r24 = ImageLocation.getForUserOrChat(r2, r18, 0);
        r3 = "100_100";
        goto L78
    L20:
        r112 = r11.profile_photo;
    L23:
        r113 = null;
        goto L24
    L13:
        r92 = false;
        goto L14
    L59:
        r10 = null;
        r113 = null;
        r92 = false;
        goto L60
    L62:
        if ((r18 instanceof TLRPC.Chat) == false) goto L70;
        TLRPC.ChatPhoto r44 = ((TLRPC.Chat) r18).photo;
        if (r44 == null) goto L70;
        r7 = r44.strippedBitmap;
        if (r44.stripped_thumb == null) goto L68;
        r92 = true;
    L69:
        r43 = null;
        r8 = false;
        goto L71
    L68:
        r92 = false;
    L70:
        r43 = null;
        r7 = null;
        r8 = false;
        r92 = false;
        goto L71
    L5:
        r5 = r20;
        goto L6
    }

    public void setForceCrossfade(boolean r1) {
        this.forceCrossfade = r1;
    }

    public void setForceLoading(boolean r1) {
        this.forceLoding = r1;
    }

    public void setForceNotMedia(boolean r1) {
        this.forceNotMedia = r1;
    }

    public void setForcePreview(boolean r1) {
        this.forcePreview = r1;
    }

    public void setGradientBitmap(Bitmap r3) {
        if (r3 != null) goto L4;
        this.gradientShader = null;
        this.composeShader = null;
        this.legacyShader = null;
        this.legacyCanvas = null;
        Bitmap r1 = this.legacyBitmap;
        if (r1 == null) goto L12;
        r1.recycle();
        this.legacyBitmap = null;
    L12:
        this.gradientBitmap = r3;
        return;
    L4:
        if (this.gradientShader != null) goto L6;
    L7:
        Shader.TileMode r12 = Shader.TileMode.CLAMP;
        this.gradientShader = new BitmapShader(r3, r12, r12);
        updateDrawableRadius(this.currentImageDrawable);
    L8:
        this.isRoundRect = true;
        goto L12
    L6:
        if (this.gradientBitmap == r3) goto L8;
        goto L7
    }

    public void setIgnoreImageSet(boolean r1) {
        this.ignoreImageSet = r1;
    }

    public void setImage(String r12, String r13, Drawable r14, String r15, long r16) {
        setImage(ImageLocation.getForPath(r12), r13, null, null, r14, r16, r15, null, 1);
    }

    public void setImage(ImageLocation r12, String r13, Drawable r14, long r15, String r17, Object r18, int r19) {
        setImage(r12, r13, null, null, r14, r15, r17, r18, r19);
    }

    public void setImage(ImageLocation r12, String r13, Drawable r14, String r15, Object r16, int r17) {
        setImage(r12, r13, null, null, r14, 0, r15, r16, r17);
    }

    public void setImage(ImageLocation r12, String r13, ImageLocation r14, String r15, long r16, String r18, Object r19, int r20) {
        setImage(r12, r13, r14, r15, null, r16, r18, r19, r20);
    }

    public void setImage(ImageLocation r14, String r15, ImageLocation r16, String r17, Drawable r18, long r19, String r21, Object r22, int r23) {
        setImage(null, null, r14, r15, r16, r17, r18, r19, r21, r22, r23);
    }

    public void setImage(ImageLocation r14, String r15, ImageLocation r16, String r17, Drawable r18, Object r19, int r20) {
        setImage(null, null, r14, r15, r16, r17, r18, 0, null, r19, r20);
    }

    public void setImage(ImageLocation r12, String r13, ImageLocation r14, String r15, String r16, Object r17, int r18) {
        setImage(r12, r13, r14, r15, null, 0, r16, r17, r18);
    }

    public void setImage(ImageLocation r20, String r21, ImageLocation r22, String r23, ImageLocation r24, String r25, Drawable r26, long r27, String r29, Object r30, int r31) {
        ImageLocation r1 = r20;
        ImageLocation r3 = r22;
        if (this.allowLoadingOnAttachedOnly == false) goto L12;
        if (this.attachedToWindow == true) goto L12;
        if (this.setImageBackup != null) goto L9;
        this.setImageBackup = new SetImageBackup(null);
    L9:
        SetImageBackup r0 = this.setImageBackup;
        r0.mediaLocation = r1;
        r0.mediaFilter = r21;
        r0.imageLocation = r3;
        r0.imageFilter = r23;
        r0.thumbLocation = r24;
        r0.thumbFilter = r25;
        r0.thumb = r26;
        r0.size = r27;
        r0.ext = r29;
        r0.cacheType = r31;
        r0.parentObject = r30;
        return;
    L12:
        if (this.ignoreImageSet == false) goto L15;
        return;
    L15:
        if (this.crossfadeWithOldImage == false) goto L21;
        SetImageBackup r13 = this.setImageBackup;
        if (r13 == null) goto L21;
        if (SetImageBackup.m4232$$Nest$misWebfileSet(r13) == false) goto L21;
        setBackupImage();
    L21:
        SetImageBackup r132 = this.setImageBackup;
        if (r132 == null) goto L24;
        SetImageBackup.m4230$$Nest$mclear(r132);
    L24:
        boolean r14 = true;
        if (r3 != null) goto L55;
        if (r24 != null) goto L55;
        if (r1 != null) goto L55;
        int r12 = 0;
    L30:
        if (r12 >= 4) goto L32;
        recycleBitmap(null, r12);
        r12 = r12 + 1;
        goto L30
    L32:
        this.currentImageLocation = null;
        this.currentImageFilter = null;
        this.currentImageKey = null;
        this.currentMediaLocation = null;
        this.currentMediaFilter = null;
        this.currentMediaKey = null;
        this.currentThumbLocation = null;
        this.currentThumbFilter = null;
        this.currentThumbKey = null;
        this.currentMediaDrawable = null;
        this.mediaShader = null;
        this.currentImageDrawable = null;
        this.imageShader = null;
        this.composeShader = null;
        this.thumbShader = null;
        this.crossfadeShader = null;
        this.legacyShader = null;
        this.legacyCanvas = null;
        Bitmap r15 = this.legacyBitmap;
        if (r15 == null) goto L35;
        r15.recycle();
        this.legacyBitmap = null;
    L35:
        this.currentExt = r29;
        this.currentParentObject = null;
        this.currentCacheType = 0;
        this.roundPaint.setShader(null);
        setStaticDrawable(r26);
        this.currentAlpha = 1.0f;
        this.previousAlpha = 1.0f;
        this.currentSize = 0;
        updateDrawableRadius(this.staticThumbDrawable);
        ImageLoader.getInstance().cancelLoadingForImageReceiver(this, true);
        invalidate();
        ImageReceiverDelegate r16 = this.delegate;
        if (r16 == null) goto L129;
        Drawable r2 = this.currentImageDrawable;
        if (r2 == null) goto L40;
    L47:
        boolean r32 = true;
    L48:
        if (r2 == null) goto L50;
    L52:
        r14 = false;
    L53:
        r16.didSetImage(this, r32, r14, false);
        return;
    L50:
        if (this.currentMediaDrawable != null) goto L52;
    L40:
        if (this.currentThumbDrawable != null) goto L47;
        if (this.staticThumbDrawable != null) goto L47;
        if (this.currentMediaDrawable != null) goto L47;
        r32 = false;
        goto L48
    L129:
        return;
    L55:
        if (r3 == null) goto L57;
        String r162 = r3.getKey(r30, null, false);
    L58:
        if (r162 != null) goto L61;
        if (r3 == null) goto L61;
        r3 = null;
    L61:
        this.animatedFileDrawableRepeatMaxCount = Math.max(this.autoRepeatCount, 0);
        this.currentKeyQuality = false;
        if (r162 == null) goto L64;
    L79:
        String r122 = r162;
        if (r122 == null) goto L84;
        if (r23 == null) goto L84;
        r122 = r122 + "@" + r23;
    L84:
        if (this.uniqKeyPrefix == null) goto L86;
        r122 = this.uniqKeyPrefix + r122;
    L86:
        if (r1 == null) goto L88;
        String r142 = r1.getKey(r30, null, false);
    L89:
        if (r142 != null) goto L92;
        if (r1 == null) goto L92;
        r1 = null;
    L92:
        if (r142 == null) goto L96;
        if (r21 == null) goto L96;
        r142 = r142 + "@" + r21;
    L96:
        if (this.uniqKeyPrefix == null) goto L98;
        r142 = this.uniqKeyPrefix + r142;
    L98:
        if (r142 != null) goto L103;
        String r7 = this.currentImageKey;
        if (r7 == null) goto L103;
        if (r7.equals(r122) == false) goto L103;
    L107:
        ImageReceiverDelegate r72 = this.delegate;
        if (r72 == null) goto L128;
        Drawable r152 = this.currentImageDrawable;
        if (r152 == null) goto L112;
    L119:
        boolean r153 = true;
    L120:
        if (r152 == null) goto L122;
    L125:
        boolean r8 = false;
    L126:
        r72.didSetImage(this, r153, r8, false);
        goto L128
    L122:
        if (this.currentMediaDrawable != null) goto L125;
        r8 = true;
        goto L126
    L112:
        if (this.currentThumbDrawable != null) goto L119;
        if (this.staticThumbDrawable != null) goto L119;
        if (this.currentMediaDrawable != null) goto L119;
        r153 = false;
    L128:
        if (this.canceledLoading == false) goto L204;
    L130:
        ImageLocation r73 = this.strippedLocation;
        if (r73 != null) goto L136;
        if (r1 == null) goto L135;
        r73 = r1;
        goto L136
    L135:
        r73 = r3;
    L136:
        if (r73 != null) goto L138;
        r73 = r24;
    L138:
        if (r24 == null) goto L140;
        String r74 = r24.getKey(r30, r73, false);
    L141:
        if (r74 == null) goto L145;
        if (r25 == null) goto L145;
        r74 = r74 + "@" + r25;
    L145:
        if (this.crossfadeWithOldImage == false) goto L170;
        Object r82 = this.currentParentObject;
        if ((r82 instanceof MessageObject) == true) goto L149;
    L153:
        Drawable r83 = this.currentMediaDrawable;
        if (r83 == null) goto L161;
        if ((r83 instanceof AnimatedFileDrawable) == false) goto L158;
        ((AnimatedFileDrawable) r83).stop();
        ((AnimatedFileDrawable) this.currentMediaDrawable).removeParent(this);
    L158:
        recycleBitmap(r74, 1);
        recycleBitmap(null, 2);
        recycleBitmap(r142, 0);
        this.crossfadeImage = this.currentMediaDrawable;
        this.crossfadeShader = this.mediaShader;
        this.crossfadeKey = this.currentImageKey;
        this.crossfadingWithThumb = false;
        this.currentMediaDrawable = null;
        this.currentMediaKey = null;
    L159:
        boolean r9 = true;
    L171:
        this.currentImageLocation = r3;
        this.currentImageFilter = r23;
        this.currentImageKey = r122;
        this.currentMediaLocation = r1;
        this.currentMediaFilter = r21;
        this.currentMediaKey = r142;
        this.currentThumbLocation = r24;
        this.currentThumbFilter = r25;
        this.currentThumbKey = r74;
        this.currentParentObject = r30;
        this.currentExt = r29;
        this.currentSize = r27;
        this.currentCacheType = r31;
        setStaticDrawable(r26);
        this.imageShader = null;
        this.composeShader = null;
        this.thumbShader = null;
        this.staticThumbShader = null;
        this.mediaShader = null;
        this.legacyShader = null;
        this.legacyCanvas = null;
        this.roundPaint.setShader(null);
        Bitmap r17 = this.legacyBitmap;
        if (r17 == null) goto L174;
        r17.recycle();
        this.legacyBitmap = null;
    L174:
        this.currentAlpha = 1.0f;
        this.previousAlpha = 1.0f;
        updateDrawableRadius(this.staticThumbDrawable);
        ImageReceiverDelegate r18 = this.delegate;
        if (r18 == null) goto L194;
        Drawable r28 = this.currentImageDrawable;
        if (r28 == null) goto L179;
    L186:
        boolean r123 = r9;
    L187:
        if (r28 == null) goto L189;
    L192:
        boolean r210 = false;
    L191:
        boolean r84 = false;
        r18.didSetImage(this, r123, r210, false);
    L195:
        loadImage();
        if ((r30 instanceof MessageObject) == true) goto L198;
    L200:
        boolean r143 = r84;
    L201:
        this.isRoundVideo = r143;
        return;
    L198:
        if (((MessageObject) r30).isRoundVideo() == false) goto L200;
        r143 = r9;
        goto L201
    L189:
        if (this.currentMediaDrawable != null) goto L192;
        r210 = r9;
        goto L191
    L179:
        if (this.currentThumbDrawable != null) goto L186;
        if (this.staticThumbDrawable != null) goto L186;
        if (this.currentMediaDrawable != null) goto L186;
        r123 = false;
        goto L187
    L194:
        r84 = false;
        goto L195
    L161:
        if (this.currentImageDrawable == null) goto L164;
        recycleBitmap(r74, 1);
        recycleBitmap(null, 2);
        recycleBitmap(r142, 3);
        this.crossfadeShader = this.imageShader;
        this.crossfadeImage = this.currentImageDrawable;
        this.crossfadeKey = this.currentImageKey;
        this.crossfadingWithThumb = false;
        this.currentImageDrawable = null;
        this.currentImageKey = null;
        goto L159
    L164:
        if (this.currentThumbDrawable == null) goto L167;
        recycleBitmap(r122, 0);
        recycleBitmap(null, 2);
        recycleBitmap(r142, 3);
        this.crossfadeShader = this.thumbShader;
        this.crossfadeImage = this.currentThumbDrawable;
        this.crossfadeKey = this.currentThumbKey;
        this.crossfadingWithThumb = false;
        this.currentThumbDrawable = null;
        this.currentThumbKey = null;
        goto L159
    L167:
        if (this.staticThumbDrawable == null) goto L169;
        recycleBitmap(r122, 0);
        recycleBitmap(r74, 1);
        recycleBitmap(null, 2);
        recycleBitmap(r142, 3);
        this.crossfadeShader = this.staticThumbShader;
        this.crossfadeImage = this.staticThumbDrawable;
        this.crossfadingWithThumb = false;
        this.crossfadeKey = null;
        this.currentThumbDrawable = null;
        this.currentThumbKey = null;
        goto L159
    L169:
        recycleBitmap(r122, 0);
        r9 = true;
        recycleBitmap(r74, 1);
        recycleBitmap(null, 2);
        recycleBitmap(r142, 3);
        this.crossfadeShader = null;
        goto L171
    L149:
        if (((MessageObject) r82).lastGeoWebFileSet == null) goto L153;
        if ((MessageObject.getMedia((MessageObject) r82) instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L153;
        Object r85 = this.currentParentObject;
        ((MessageObject) r85).lastGeoWebFileLoaded = ((MessageObject) r85).lastGeoWebFileSet;
        goto L153
    L170:
        r9 = true;
        recycleBitmap(r122, 0);
        recycleBitmap(r74, 1);
        recycleBitmap(null, 2);
        recycleBitmap(r142, 3);
        this.crossfadeShader = null;
        goto L171
    L140:
        r74 = null;
        goto L141
    L204:
        return;
    L103:
        String r75 = this.currentMediaKey;
        if (r75 == null) goto L130;
        if (r75.equals(r142) == false) goto L130;
    L88:
        r142 = null;
        goto L89
    L64:
        if (this.needsQualityThumb == false) goto L79;
        if ((r30 instanceof MessageObject) == false) goto L68;
    L69:
        TLRPC.Document r133 = this.qulityThumbDocument;
        if (r133 != null) goto L73;
        r133 = ((MessageObject) r30).getDocument();
    L73:
        if (r133 == null) goto L79;
        if (r133.dc_id == 0) goto L79;
        if (r133.id == 0) goto L79;
        r162 = "q_" + r133.dc_id + "_" + r133.id;
        this.currentKeyQuality = true;
        goto L79
    L68:
        if (this.qulityThumbDocument == null) goto L79;
    L57:
        r162 = null;
        goto L58
    }

    public void setImageBitmap(Bitmap r3) {
        BitmapDrawable r0 = null;
        if (r3 == null) goto L5;
        r0 = new BitmapDrawable(null, r3);
    L5:
        setImageBitmap(r0);
    }

    public void setImageBitmap(Drawable r8) {
        boolean r1 = true;
        ImageLoader.getInstance().cancelLoadingForImageReceiver(this, true);
        if (this.crossfadeWithOldImage == true) goto L5;
        int r0 = 0;
    L18:
        if (r0 >= 4) goto L20;
        recycleBitmap(null, r0);
        r0 = r0 + 1;
    L20:
        Drawable r02 = this.staticThumbDrawable;
        if ((r02 instanceof RecyclableDrawable) == false) goto L24;
        ((RecyclableDrawable) r02).recycle();
    L24:
        if ((r8 instanceof AnimatedEmojiDrawable) == false) goto L29;
        AnimatedEmojiDrawable r03 = (AnimatedEmojiDrawable) r8;
        if (this.attachedToWindow == false) goto L59;
        r03.addView(this);
    L59:
        this.staticThumbShader = null;
        this.thumbShader = null;
        this.roundPaint.setShader(null);
        setStaticDrawable(r8);
        updateDrawableRadius(r8);
        this.currentMediaLocation = null;
        this.currentMediaFilter = null;
        Drawable r82 = this.currentMediaDrawable;
        if ((r82 instanceof AnimatedFileDrawable) == false) goto L62;
        ((AnimatedFileDrawable) r82).removeParent(this);
    L62:
        this.currentMediaDrawable = null;
        this.currentMediaKey = null;
        this.mediaShader = null;
        this.currentImageLocation = null;
        this.currentImageFilter = null;
        this.currentImageDrawable = null;
        this.currentImageKey = null;
        this.imageShader = null;
        this.composeShader = null;
        this.legacyShader = null;
        this.legacyCanvas = null;
        Bitmap r83 = this.legacyBitmap;
        if (r83 == null) goto L65;
        r83.recycle();
        this.legacyBitmap = null;
    L65:
        this.currentThumbLocation = null;
        this.currentThumbFilter = null;
        this.currentThumbKey = null;
        this.currentKeyQuality = false;
        this.currentExt = null;
        this.currentSize = 0;
        this.currentCacheType = 0;
        this.currentAlpha = 1.0f;
        this.previousAlpha = 1.0f;
        SetImageBackup r84 = this.setImageBackup;
        if (r84 == null) goto L68;
        SetImageBackup.m4230$$Nest$mclear(r84);
    L68:
        ImageReceiverDelegate r85 = this.delegate;
        if (r85 != null) goto L71;
    L78:
        invalidate();
        if (this.forceCrossfade == true) goto L81;
        return;
    L81:
        if (this.crossfadeWithOldImage == true) goto L83;
        return;
    L83:
        if (this.crossfadeImage == null) goto L96;
        this.currentAlpha = 0.0f;
        this.lastUpdateAlphaTime = System.currentTimeMillis();
        if (this.currentThumbDrawable == null) goto L87;
    L90:
        this.crossfadeWithThumb = r1;
        return;
    L87:
        if (this.staticThumbDrawable != null) goto L90;
        r1 = false;
        goto L90
    L96:
        return;
    L71:
        if (this.currentThumbDrawable == null) goto L73;
    L76:
        boolean r04 = true;
    L77:
        r85.didSetImage(this, r04, true, false);
        goto L78
    L73:
        if (this.staticThumbDrawable != null) goto L76;
        r04 = false;
        goto L77
    L29:
        if ((r8 instanceof AnimatedFileDrawable) == false) goto L47;
        AnimatedFileDrawable r05 = (AnimatedFileDrawable) r8;
        r05.setParentView(this.parentView);
        if (this.attachedToWindow == false) goto L34;
        r05.addParent(this);
    L34:
        if (this.useSharedAnimationQueue == false) goto L36;
    L39:
        boolean r2 = true;
    L40:
        r05.setUseSharedQueue(r2);
        if (this.allowStartAnimation == true) goto L43;
    L45:
        r05.setAllowDecodeSingleFrame(this.allowDecodeSingleFrame);
        goto L59
    L43:
        if (this.currentOpenedLayerFlags != 0) goto L45;
        r05.checkRepeat();
        goto L45
    L36:
        if (r05.isWebmSticker == true) goto L39;
        r2 = false;
        goto L40
    L47:
        if ((r8 instanceof RLottieDrawable) == false) goto L59;
        RLottieDrawable r06 = (RLottieDrawable) r8;
        if (this.attachedToWindow == false) goto L51;
        r06.addParentView(this);
    L51:
        r06.setAllowVibration(this.allowLottieVibration);
        if (this.allowStartLottieAnimation == true) goto L54;
    L58:
        r06.setAllowDecodeSingleFrame(true);
        goto L59
    L54:
        if (r06.isHeavyDrawable() == true) goto L56;
    L57:
        r06.start();
        goto L58
    L56:
        if (this.currentOpenedLayerFlags != 0) goto L58;
    L5:
        if (this.currentImageDrawable == null) goto L8;
        recycleBitmap(null, 1);
        recycleBitmap(null, 2);
        recycleBitmap(null, 3);
        this.crossfadeShader = this.imageShader;
        this.crossfadeImage = this.currentImageDrawable;
        this.crossfadeKey = this.currentImageKey;
        this.crossfadingWithThumb = true;
        goto L20
    L8:
        if (this.currentThumbDrawable == null) goto L11;
        recycleBitmap(null, 0);
        recycleBitmap(null, 2);
        recycleBitmap(null, 3);
        this.crossfadeShader = this.thumbShader;
        this.crossfadeImage = this.currentThumbDrawable;
        this.crossfadeKey = this.currentThumbKey;
        this.crossfadingWithThumb = true;
        goto L20
    L11:
        if (this.staticThumbDrawable == null) goto L13;
        recycleBitmap(null, 0);
        recycleBitmap(null, 1);
        recycleBitmap(null, 2);
        recycleBitmap(null, 3);
        this.crossfadeShader = this.staticThumbShader;
        this.crossfadeImage = this.staticThumbDrawable;
        this.crossfadingWithThumb = true;
        this.crossfadeKey = null;
        goto L20
    L13:
        int r07 = 0;
    L14:
        if (r07 >= 4) goto L16;
        recycleBitmap(null, r07);
        r07 = r07 + 1;
        goto L14
    L16:
        this.crossfadeShader = null;
        goto L20
    }

    public boolean setImageBitmapByKey(Drawable r8, String r9, int r10, boolean r11, int r12) {
        if (r8 == null) goto L241;
        if (r9 == null) goto L241;
        if (this.currentGuid != r12) goto L241;
        if (r10 != 0) goto L88;
        if (r9.equals(this.currentImageKey) == true) goto L13;
        return false;
    L13:
        ImageReceiverDelegate r3 = this.delegate;
        if (r3 == null) goto L17;
        r3.didSetImageBitmap(r10, r9, r8);
    L17:
        if ((r8 instanceof AnimatedFileDrawable) == true) goto L26;
        ImageLoader.getInstance().incrementUseCount(this.currentImageKey);
        if (this.videoThumbIsSame == true) goto L21;
    L32:
        boolean r92 = true;
    L33:
        this.currentImageDrawable = r8;
        if ((r8 instanceof ExtendedBitmapDrawable) == false) goto L36;
        ExtendedBitmapDrawable r102 = (ExtendedBitmapDrawable) r8;
        this.imageOrientation = r102.getOrientation();
        this.imageInvert = r102.getInvert();
    L36:
        updateDrawableRadius(r8);
        if (r92 == true) goto L39;
    L86:
        this.currentAlpha = 1.0f;
        this.previousAlpha = 1.0f;
    L189:
        ImageReceiverDelegate r93 = this.delegate;
        if (r93 == null) goto L209;
        Drawable r103 = this.currentImageDrawable;
        if (r103 == null) goto L194;
    L201:
        boolean r122 = true;
    L202:
        if (r103 == null) goto L204;
    L206:
        boolean r104 = false;
    L207:
        r93.didSetImage(this, r122, r104, r11);
        goto L209
    L204:
        if (this.currentMediaDrawable != null) goto L206;
        r104 = true;
        goto L207
    L194:
        if (this.currentThumbDrawable != null) goto L201;
        if (this.staticThumbDrawable != null) goto L201;
        if (this.currentMediaDrawable != null) goto L201;
        r122 = false;
    L209:
        if ((r8 instanceof AnimatedEmojiDrawable) == false) goto L214;
        AnimatedEmojiDrawable r82 = (AnimatedEmojiDrawable) r8;
        if (this.attachedToWindow == false) goto L239;
        r82.addView(this);
    L239:
        invalidate();
        return true;
    L214:
        if ((r8 instanceof AnimatedFileDrawable) == false) goto L227;
        AnimatedFileDrawable r83 = (AnimatedFileDrawable) r8;
        r83.setUseSharedQueue(this.useSharedAnimationQueue);
        if (this.attachedToWindow == false) goto L219;
        r83.addParent(this);
    L219:
        if (this.allowStartAnimation == true) goto L221;
    L223:
        r83.setAllowDecodeSingleFrame(this.allowDecodeSingleFrame);
        this.animationReadySent = false;
        View r84 = this.parentView;
        if (r84 == null) goto L239;
        r84.invalidate();
        goto L239
    L221:
        if (this.currentOpenedLayerFlags != 0) goto L223;
        r83.checkRepeat();
        goto L223
    L227:
        if ((r8 instanceof RLottieDrawable) == false) goto L239;
        RLottieDrawable r85 = (RLottieDrawable) r8;
        if (this.attachedToWindow == false) goto L232;
        r85.addParentView(this);
    L232:
        if (this.allowStartLottieAnimation == true) goto L234;
    L238:
        r85.setAllowDecodeSingleFrame(true);
        r85.setAutoRepeat(this.autoRepeat);
        r85.setAutoRepeatCount(this.autoRepeatCount);
        r85.setAutoRepeatTimeout(this.autoRepeatTimeout);
        r85.setAllowDrawFramesWhileCacheGenerating(this.allowDrawWhileCacheGenerating);
        this.animationReadySent = false;
        goto L239
    L234:
        if (r85.isHeavyDrawable() == true) goto L236;
    L237:
        r85.start();
        goto L238
    L236:
        if (this.currentOpenedLayerFlags != 0) goto L238;
    L39:
        if (this.isVisible == false) goto L86;
        if (r11 == true) goto L44;
        if (this.forcePreview == true) goto L44;
    L46:
        if (this.crossfadeDuration == 0) goto L86;
        Drawable r94 = this.currentMediaDrawable;
        if ((r94 instanceof RLottieDrawable) == true) goto L50;
    L52:
        Drawable r95 = this.currentMediaDrawable;
        if ((r95 instanceof AnimatedFileDrawable) == false) goto L58;
        if (((AnimatedFileDrawable) r95).hasBitmap() == true) goto L189;
    L58:
        if ((this.currentImageDrawable instanceof RLottieDrawable) == false) goto L65;
        Drawable r96 = this.staticThumbDrawable;
        if ((r96 instanceof LoadingStickerDrawable) == true) goto L65;
        if ((r96 instanceof SvgHelper.SvgDrawable) == true) goto L65;
        if ((r96 instanceof Emoji.EmojiDrawable) == false) goto L189;
    L65:
        Drawable r97 = this.currentThumbDrawable;
        if (r97 == null) goto L68;
    L71:
        if (r97 != null) goto L73;
    L75:
        this.previousAlpha = 1.0f;
    L76:
        this.currentAlpha = 0.0f;
        this.lastUpdateAlphaTime = System.currentTimeMillis();
        if (this.crossfadeImage == null) goto L79;
    L84:
        boolean r98 = true;
    L85:
        this.crossfadeWithThumb = r98;
        goto L189
    L79:
        if (this.currentThumbDrawable != null) goto L84;
        if (this.staticThumbDrawable != null) goto L84;
        r98 = false;
        goto L85
    L73:
        if (this.staticThumbDrawable == null) goto L75;
        this.previousAlpha = this.currentAlpha;
        goto L76
    L68:
        if (this.staticThumbDrawable != null) goto L71;
        if (this.forceCrossfade == false) goto L189;
    L50:
        if (((RLottieDrawable) r94).hasBitmap() == false) goto L52;
    L44:
        if (this.forceCrossfade == false) goto L86;
    L21:
        if (r8 != this.currentImageDrawable) goto L23;
    L25:
        r92 = false;
        goto L33
    L23:
        if (this.currentAlpha < 1.0f) goto L25;
    L26:
        AnimatedFileDrawable r99 = (AnimatedFileDrawable) r8;
        r99.setStartEndTime(this.startTime, this.endTime);
        if (r99.isWebmSticker == false) goto L30;
        ImageLoader.getInstance().incrementUseCount(this.currentImageKey);
    L30:
        if (this.videoThumbIsSame == false) goto L32;
        r92 = !r99.hasBitmap();
        goto L33
    L88:
        if (r10 == 3) goto L90;
        if (r10 != 1) goto L189;
        if (this.currentThumbDrawable == null) goto L148;
        return false;
    L148:
        if (this.forcePreview == true) goto L164;
        AnimatedFileDrawable r32 = getAnimation();
        if (r32 != null) goto L152;
    L154:
        Drawable r33 = this.currentImageDrawable;
        if (r33 != null) goto L157;
    L158:
        Drawable r34 = this.currentMediaDrawable;
        if (r34 == null) goto L164;
        if ((r34 instanceof AnimatedFileDrawable) == true) goto L164;
    L162:
        return false;
    L157:
        if ((r33 instanceof AnimatedFileDrawable) == false) goto L162;
    L152:
        if (r32.hasBitmap() == false) goto L154;
        return false;
    L164:
        if (r9.equals(this.currentThumbKey) == true) goto L166;
        return false;
    L166:
        ImageReceiverDelegate r35 = this.delegate;
        if (r35 == null) goto L169;
        r35.didSetImageBitmap(r10, r9, r8);
    L169:
        ImageLoader.getInstance().incrementUseCount(this.currentThumbKey);
        this.currentThumbDrawable = r8;
        if ((r8 instanceof ExtendedBitmapDrawable) == false) goto L172;
        ExtendedBitmapDrawable r910 = (ExtendedBitmapDrawable) r8;
        this.thumbOrientation = r910.getOrientation();
        this.thumbInvert = r910.getInvert();
    L172:
        updateDrawableRadius(r8);
        if (r11 == false) goto L175;
    L188:
        this.currentAlpha = 1.0f;
        this.previousAlpha = 1.0f;
        goto L189
    L175:
        if (this.crossfadeAlpha == 2) goto L188;
        Object r911 = this.currentParentObject;
        if ((r911 instanceof MessageObject) == true) goto L179;
    L183:
        this.currentAlpha = 0.0f;
        this.previousAlpha = 1.0f;
        this.lastUpdateAlphaTime = System.currentTimeMillis();
        if (this.staticThumbDrawable == null) goto L186;
        boolean r912 = true;
    L187:
        this.crossfadeWithThumb = r912;
        goto L189
    L186:
        r912 = false;
        goto L187
    L179:
        if (((MessageObject) r911).isRoundVideo() == false) goto L183;
        if (((MessageObject) this.currentParentObject).isSending() == false) goto L183;
        this.currentAlpha = 1.0f;
        this.previousAlpha = 1.0f;
        goto L189
    L90:
        if (r9.equals(this.currentMediaKey) == true) goto L92;
        return false;
    L92:
        ImageReceiverDelegate r36 = this.delegate;
        if (r36 == null) goto L96;
        r36.didSetImageBitmap(r10, r9, r8);
    L96:
        if ((r8 instanceof AnimatedFileDrawable) == true) goto L98;
        ImageLoader.getInstance().incrementUseCount(this.currentMediaKey);
    L112:
        this.currentMediaDrawable = r8;
        updateDrawableRadius(r8);
        if (this.currentImageDrawable != null) goto L189;
        if (r11 == true) goto L118;
        if (this.forcePreview == true) goto L118;
    L119:
        Drawable r913 = this.currentThumbDrawable;
        if (r913 != null) goto L124;
        if (this.staticThumbDrawable != null) goto L124;
    L127:
        if (r913 != null) goto L129;
    L131:
        this.previousAlpha = 1.0f;
    L132:
        this.currentAlpha = 0.0f;
        this.lastUpdateAlphaTime = System.currentTimeMillis();
        if (this.crossfadeImage == null) goto L135;
    L140:
        boolean r914 = true;
    L141:
        this.crossfadeWithThumb = r914;
        goto L189
    L135:
        if (this.currentThumbDrawable != null) goto L140;
        if (this.staticThumbDrawable != null) goto L140;
        r914 = false;
        goto L141
    L129:
        if (this.staticThumbDrawable == null) goto L131;
        this.previousAlpha = this.currentAlpha;
    L124:
        if (this.currentAlpha == 1.0f) goto L127;
        if (this.forceCrossfade == false) goto L189;
    L118:
        if (this.forceCrossfade == true) goto L119;
        this.currentAlpha = 1.0f;
        this.previousAlpha = 1.0f;
        goto L189
    L98:
        AnimatedFileDrawable r915 = (AnimatedFileDrawable) r8;
        r915.setStartEndTime(this.startTime, this.endTime);
        if (r915.isWebmSticker == false) goto L102;
        ImageLoader.getInstance().incrementUseCount(this.currentMediaKey);
    L102:
        if (this.videoThumbIsSame == false) goto L112;
        Drawable r105 = this.currentThumbDrawable;
        if ((r105 instanceof AnimatedFileDrawable) == true) goto L108;
        if ((this.currentImageDrawable instanceof AnimatedFileDrawable) == false) goto L112;
    L108:
        if ((r105 instanceof AnimatedFileDrawable) == false) goto L110;
        long r37 = ((AnimatedFileDrawable) r105).getLastFrameTimestamp();
    L111:
        r915.seekTo(r37, true, true);
        goto L112
    L110:
        r37 = 0;
    L241:
        return false;
    }

    public void setImageCoords(float r1, float r2, float r3, float r4) {
        this.imageX = r1;
        this.imageY = r2;
        this.imageW = r3;
        this.imageH = r4;
    }

    public void setImageCoords(Rect r2) {
        if (r2 == null) goto L5;
        this.imageX = r2.left;
        this.imageY = r2.top;
        this.imageW = r2.width();
        this.imageH = r2.height();
        return;
    }

    public void setImageCoords(RectF r2) {
        if (r2 == null) goto L5;
        this.imageX = r2.left;
        this.imageY = r2.top;
        this.imageW = r2.width();
        this.imageH = r2.height();
        return;
    }

    public void setImageWidth(int r1) {
        this.imageW = r1;
    }

    public void setImageX(float r1) {
        this.imageX = r1;
    }

    public void setImageY(float r1) {
        this.imageY = r1;
    }

    public void setInvalidateAll(boolean r1) {
        this.invalidateAll = r1;
    }

    public void setLayerNum(int r2) {
        this.currentLayerNum = r2;
        if (this.attachedToWindow == false) goto L6;
        this.currentOpenedLayerFlags = NotificationCenter.getGlobalInstance().getCurrentHeavyOperationFlags() & (~this.currentLayerNum);
        return;
    }

    public void setManualAlphaAnimator(boolean r1) {
        this.manualAlphaAnimator = r1;
    }

    public void setMark(Object r1) {
        this.mark = r1;
    }

    public void setMediaStartEndTime(long r2, long r4) {
        this.startTime = r2;
        this.endTime = r4;
        Drawable r1 = this.currentMediaDrawable;
        if ((r1 instanceof AnimatedFileDrawable) == false) goto L6;
        ((AnimatedFileDrawable) r1).setStartEndTime(r2, r4);
        return;
    }

    public void setNeedsQualityThumb(boolean r1) {
        this.needsQualityThumb = r1;
    }

    public void setOrientation(int r2, int r3, boolean r4) {
    L2:
        if (r2 >= 0) goto L5;
        r2 = r2 + 360;
    L5:
        if (r2 <= 360) goto L7;
        r2 = r2 - 360;
        goto L5
    L7:
        this.thumbOrientation = r2;
        this.imageOrientation = r2;
        this.thumbInvert = r3;
        this.imageInvert = r3;
        this.centerRotation = r4;
    }

    public void setOrientation(int r2, boolean r3) {
        setOrientation(r2, 0, r3);
    }

    public void setParam(int r1) {
        this.param = r1;
    }

    public void setParentView(View r2) {
        this.parentView = r2;
        AnimatedFileDrawable r22 = getAnimation();
        if (r22 != null) goto L5;
        return;
    L5:
        if (this.attachedToWindow == false) goto L9;
        r22.setParentView(this.parentView);
        return;
    }

    public void setPreloadingReceivers(List<ImageReceiver> r1) {
        this.preloadReceivers = r1;
    }

    public void setPressed(int r1) {
        this.isPressed = r1;
    }

    public void setQualityThumbDocument(TLRPC.Document r1) {
        this.qulityThumbDocument = r1;
    }

    public void setRoundRadius(int r1) {
        setRoundRadius(new int[]{r1, r1, r1, r1});
    }

    public void setRoundRadius(int r1, int r2, int r3, int r4) {
        setRoundRadius(new int[]{r1, r2, r3, r4});
    }

    public void setRoundRadius(int[] r9) {
        int r1 = r9[0];
        this.isRoundRect = true;
        int r3 = 0;
        boolean r4 = false;
    L3:
        int[] r5 = this.roundRadius;
        if (r3 >= r5.length) goto L11;
        int r6 = r5[r3];
        int r7 = r9[r3];
        if (r6 == r7) goto L8;
        r4 = true;
    L8:
        if (r1 == r7) goto L10;
        this.isRoundRect = false;
    L10:
        r5[r3] = r7;
        r3 = r3 + 1;
        goto L3
    L11:
        if (r4 == false) goto L32;
        Drawable r92 = this.currentImageDrawable;
        if (r92 != null) goto L15;
    L17:
        Drawable r93 = this.currentMediaDrawable;
        if (r93 != null) goto L20;
    L22:
        Drawable r94 = this.currentThumbDrawable;
        if (r94 == null) goto L25;
        updateDrawableRadius(r94);
    L25:
        Drawable r95 = this.staticThumbDrawable;
        if (r95 == null) goto L33;
        updateDrawableRadius(r95);
        return;
    L33:
        return;
    L20:
        if (this.mediaShader != null) goto L22;
        updateDrawableRadius(r93);
        goto L22
    L15:
        if (this.imageShader != null) goto L17;
        updateDrawableRadius(r92);
        goto L17
    }

    public void setRoundRadiusEnabled(boolean r3) {
        if (this.useRoundRadius == r3) goto L26;
        this.useRoundRadius = r3;
        if (r3 == false) goto L7;
    L9:
        Drawable r32 = this.currentImageDrawable;
        if (r32 != null) goto L12;
    L14:
        Drawable r33 = this.currentMediaDrawable;
        if (r33 != null) goto L17;
    L19:
        Drawable r34 = this.currentThumbDrawable;
        if (r34 == null) goto L22;
        updateDrawableRadius(r34);
    L22:
        Drawable r35 = this.staticThumbDrawable;
        if (r35 == null) goto L27;
        updateDrawableRadius(r35);
        return;
    L27:
        return;
    L17:
        if (this.mediaShader != null) goto L19;
        updateDrawableRadius(r33);
        goto L19
    L12:
        if (this.imageShader != null) goto L14;
        updateDrawableRadius(r32);
        goto L14
    L7:
        if (this.emptyRoundRadius != null) goto L9;
        this.emptyRoundRadius = new int[]{0, 0, 0, 0};
        goto L9
    }

    public void setShouldGenerateQualityThumb(boolean r1) {
        this.shouldGenerateQualityThumb = r1;
    }

    public void setSideClip(float r1) {
        this.sideClip = r1;
    }

    public void setSkipUpdateFrame(boolean r1) {
        this.skipUpdateFrame = r1;
    }

    public void setStrippedLocation(ImageLocation r1) {
        this.strippedLocation = r1;
    }

    public void setTag(int r2, int r3) {
        if (r3 != 1) goto L7;
        this.thumbTag = r2;
        return;
    L7:
        if (r3 != 3) goto L10;
        this.mediaTag = r2;
        return;
    L10:
        this.imageTag = r2;
    }

    public void setUniqKeyPrefix(String r1) {
        this.uniqKeyPrefix = r1;
    }

    public void setUseRoundForThumbDrawable(boolean r1) {
        this.useRoundForThumb = r1;
    }

    public void setUseSharedAnimationQueue(boolean r1) {
        this.useSharedAnimationQueue = r1;
    }

    public void setVideoThumbIsSame(boolean r1) {
        this.videoThumbIsSame = r1;
    }

    public void setVisible(boolean r2, boolean r3) {
        if (this.isVisible == r2) goto L13;
        this.isVisible = r2;
        if (r3 == false) goto L11;
        invalidate();
        Runnable r1 = this.visibleInvalidate;
        if (r1 == null) goto L12;
        r1.run();
        return;
    L12:
        return;
    L11:
        return;
    }

    public void setVisibleInvalidate(Runnable r1) {
        this.visibleInvalidate = r1;
    }

    public void skipDraw() {
    }

    public void startAnimation() {
        startAnimation(false);
    }

    public void startAnimation(boolean r2) {
        AnimatedFileDrawable r0 = getAnimation();
        if (r0 == null) goto L6;
        r0.setUseSharedQueue(this.useSharedAnimationQueue);
        r0.start();
        return;
    L6:
        RLottieDrawable r1 = getLottieAnimation();
        if (r1 != null) goto L9;
        return;
    L9:
        if (r1.isRunning() == true) goto L13;
        r1.restart(r2);
        return;
    }

    public void startCrossfadeFromStaticThumb(Bitmap r3) {
        startCrossfadeFromStaticThumb(new BitmapDrawable(null, r3));
    }

    public void startCrossfadeFromStaticThumb(Drawable r3) {
        this.currentThumbKey = null;
        this.currentThumbDrawable = null;
        this.thumbShader = null;
        this.staticThumbShader = null;
        this.roundPaint.setShader(null);
        setStaticDrawable(r3);
        this.crossfadeWithThumb = true;
        this.currentAlpha = 0.0f;
        updateDrawableRadius(this.staticThumbDrawable);
    }

    public void stopAnimation() {
        AnimatedFileDrawable r0 = getAnimation();
        if (r0 == null) goto L6;
        r0.stop();
        return;
    L6:
        RLottieDrawable r1 = getLottieAnimation();
        if (r1 == null) goto L10;
        r1.stop();
        return;
    }

    public void updateStaticDrawableThump(Bitmap r3) {
        this.staticThumbShader = null;
        this.roundPaint.setShader(null);
        setStaticDrawable(new BitmapDrawable(r3));
    }

    public boolean updateThumbShaderMatrix() {
        Drawable r2 = this.currentThumbDrawable;
        if (r2 == null) goto L8;
        BitmapShader r4 = this.thumbShader;
        if (r4 == null) goto L8;
        drawDrawable(null, r2, 255, r4, 0, 0, 0, null);
        return true;
    L8:
        Drawable r22 = this.staticThumbDrawable;
        if (r22 == null) goto L14;
        BitmapShader r42 = this.staticThumbShader;
        if (r42 == null) goto L16;
        drawDrawable(null, r22, 255, r42, 0, 0, 0, null);
        return true;
    L16:
        return false;
    L14:
        return false;
    }
}
