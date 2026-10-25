package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AvatarSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CanvasButton;
import org.telegram.ui.Components.CheckBoxBase;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.Shaker;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.Components.spoilers.SpoilerEffect2;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoryWidgetsImageDecorator;
import org.telegram.ui.Stories.recorder.DominantColors;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes6.dex */
public class SharedPhotoVideoCell2 extends FrameLayout {
    static boolean lastAutoDownload;
    static long lastUpdateDownloadSettingsTime;
    private final AnimatedFloat animatedProgress;
    private final AnimatedFloat animatedReordering;
    ValueAnimator animator;
    private boolean attached;
    private Text authorText;
    public ImageReceiver blurImageReceiver;
    private final RectF bounds;
    CanvasButton canvasButton;
    private boolean check2;
    CheckBoxBase checkBoxBase;
    float checkBoxProgress;
    private final Path chipPath;
    private final float[] chipRadii;
    private Path clipPath;
    float crossfadeProgress;
    float crossfadeToColumnsCount;
    SharedPhotoVideoCell2 crossfadeView;
    int currentAccount;
    MessageObject currentMessageObject;
    int currentParentColumnsCount;
    boolean drawVideoIcon;
    boolean drawViews;
    FlickerLoadingView globalGradientView;
    private Drawable gradientDrawable;
    private boolean gradientDrawableLoading;
    float highlightProgress;
    float imageAlpha;
    public ImageReceiver imageReceiver;
    public int imageReceiverColor;
    public ImageReceiver imageReceiverFullSize;
    float imageScale;
    public boolean isFirst;
    public boolean isLast;
    public boolean isSearchingHashtag;
    public boolean isStory;
    public boolean isStoryPinned;
    public boolean isStoryUploading;
    public boolean isTop;
    private SpoilerEffect mediaSpoilerEffect;
    private SpoilerEffect2 mediaSpoilerEffect2;
    private Path path;
    private Bitmap privacyBitmap;
    private Paint privacyPaint;
    private int privacyType;
    private final Paint progressPaint;
    private final Path rectPath;
    private boolean reorder;
    private boolean reordering;
    private final Paint scrimPaint;
    private Text sensitiveText;
    private Text sensitiveTextShort;
    private Text sensitiveTextShort2;
    private Shaker shaker;
    SharedResources sharedResources;
    boolean showLivePhoto;
    boolean showVideoLayout;
    private final Path spoilerClipPath;
    private final float[] spoilerClipRadii;
    private float spoilerMaxRadius;
    private float spoilerRevealProgress;
    private float spoilerRevealX;
    private float spoilerRevealY;
    public int storyId;
    private int style;
    StaticLayout videoInfoLayot;
    String videoText;
    AnimatedFloat viewsAlpha;
    AnimatedTextView.AnimatedTextDrawable viewsText;

    /* renamed from: org.telegram.ui.Cells.SharedPhotoVideoCell2$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ SharedPhotoVideoCell2 this$0;

        public AnonymousClass1(SharedPhotoVideoCell2 r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            SharedPhotoVideoCell2 r1 = this.this$0;
            r1.currentMessageObject.isMediaSpoilersRevealedInSharedMedia = true;
            r1.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Cells.SharedPhotoVideoCell2$2, reason: invalid class name */
    public class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ SharedPhotoVideoCell2 this$0;

        public AnonymousClass2(SharedPhotoVideoCell2 r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            SharedPhotoVideoCell2 r0 = this.this$0;
            r0.checkBoxProgress = ((Float) r2.getAnimatedValue()).floatValue();
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Cells.SharedPhotoVideoCell2$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ SharedPhotoVideoCell2 this$0;
        final /* synthetic */ boolean val$checked;

        public AnonymousClass3(SharedPhotoVideoCell2 r1, boolean r2) {
            this.this$0 = r1;
            this.val$checked = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ValueAnimator r0 = this.this$0.animator;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.equals(r2) == false) goto L13;
            SharedPhotoVideoCell2 r22 = this.this$0;
            if (this.val$checked == false) goto L9;
            float r1 = 1.0f;
        L10:
            r22.checkBoxProgress = r1;
            r22.animator = null;
            return;
        L9:
            r1 = 0.0f;
            goto L10
        }
    }

    public static class SharedResources {
        private Paint backgroundPaint;
        Paint highlightPaint;
        SparseArray<String> imageFilters;
        Drawable playDrawable;
        private final HashMap<Integer, Bitmap> privacyBitmaps;
        TextPaint textPaint;
        Drawable viewDrawable;

        /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m7031$$Nest$fgetbackgroundPaint(SharedResources r0) {
            return r0.backgroundPaint;
        }

        public SharedResources(Context r5, Theme.ResourcesProvider r6) {
            this.textPaint = new TextPaint(1);
            this.backgroundPaint = new Paint();
            this.highlightPaint = new Paint();
            this.imageFilters = new SparseArray();
            this.privacyBitmaps = new HashMap();
            this.textPaint.setTextSize(AndroidUtilities.dp(12.0f));
            TextPaint r0 = this.textPaint;
            if (Theme.isCurrentThemeMonet() == false) goto L5;
            int r1 = Theme.getColor(Theme.key_chat_mediaTimeText);
        L6:
            r0.setColor(r1);
            this.textPaint.setTypeface(AndroidUtilities.bold());
            Drawable r02 = ContextCompat.getDrawable(r5, R.drawable.play_mini_video).mutate();
            this.playDrawable = r02;
            r02.setBounds(0, 0, r02.getIntrinsicWidth(), this.playDrawable.getIntrinsicHeight());
            Drawable r52 = ContextCompat.getDrawable(r5, R.drawable.filled_views).mutate();
            this.viewDrawable = r52;
            r52.setBounds(0, 0, (int) (r52.getIntrinsicWidth() * 0.7f), (int) (this.viewDrawable.getIntrinsicHeight() * 0.7f));
            this.backgroundPaint.setColor(Theme.getColor(Theme.key_sharedMedia_photoPlaceholder, r6));
            return;
        L5:
            r1 = -1;
            goto L6
        }

        public String getFilterString(int r3) {
            String r0 = this.imageFilters.get(r3);
            if (r0 != null) goto L6;
            String r02 = r3 + "_" + r3 + "_isc";
            this.imageFilters.put(r3, r02);
            return r02;
        L6:
            return r0;
        }

        public Bitmap getPrivacyBitmap(Context r9, int r10) {
            Bitmap r0 = this.privacyBitmaps.get(Integer.valueOf(r10));
            if (r0 == null) goto L5;
            return r0;
        L5:
            Bitmap r92 = BitmapFactory.decodeResource(r9.getResources(), r10);
            int r02 = r92.getWidth();
            int r1 = r92.getHeight();
            Bitmap.Config r2 = Bitmap.Config.ARGB_8888;
            Bitmap r03 = Bitmap.createBitmap(r02, r1, r2);
            Canvas r12 = new Canvas(r03);
            Paint r3 = new Paint(3);
            PorterDuff.Mode r5 = PorterDuff.Mode.SRC_IN;
            r3.setColorFilter(new PorterDuffColorFilter(-10461088, r5));
            r12.drawBitmap(r92, 0.0f, 0.0f, r3);
            Utilities.stackBlurBitmap(r03, AndroidUtilities.dp(1.0f));
            Bitmap r13 = Bitmap.createBitmap(r92.getWidth(), r92.getHeight(), r2);
            Canvas r22 = new Canvas(r13);
            r22.drawBitmap(r03, 0.0f, 0.0f, r3);
            r22.drawBitmap(r03, 0.0f, 0.0f, r3);
            r22.drawBitmap(r03, 0.0f, 0.0f, r3);
            r3.setColorFilter(new PorterDuffColorFilter(-1, r5));
            r22.drawBitmap(r92, 0.0f, 0.0f, r3);
            r03.recycle();
            r92.recycle();
            this.privacyBitmaps.put(Integer.valueOf(r10), r13);
            return r13;
        }
    }

    public static /* synthetic */ void $r8$lambda$3Hbv1nBM8GSw6P1bvMUrbn4OJhI(SharedPhotoVideoCell2 r0, int[] r1) {
        r0.lambda$drawImpl$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$cQEpH16jx_mZlE4OHJ0TrtLN_b4(SharedPhotoVideoCell2 r0, ValueAnimator r1) {
        r0.lambda$startRevealMedia$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$iIdbxUnC8wPEcjs7ERfSHN75hC0(SharedPhotoVideoCell2 r0, ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$of6TNqZn6z3vgoMH618oc6-kFkE, reason: not valid java name */
    public static /* synthetic */ void m7030$r8$lambda$of6TNqZn6z3vgoMH618oc6kFkE(SharedPhotoVideoCell2 r0) {
        r0.lambda$setStyle$1();
    }

    public SharedPhotoVideoCell2(Context r10, SharedResources r11, int r12) {
        super(r10);
        this.imageReceiverColor = 0;
        this.imageReceiverFullSize = new ImageReceiver();
        this.imageReceiver = new ImageReceiver();
        this.blurImageReceiver = new ImageReceiver();
        this.imageAlpha = 1.0f;
        this.imageScale = 1.0f;
        this.drawVideoIcon = true;
        CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.viewsAlpha = new AnimatedFloat(this, 0, 350, r8);
        this.viewsText = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.path = new Path();
        this.rectPath = new Path();
        this.chipPath = new Path();
        this.chipRadii = new float[8];
        this.spoilerClipPath = new Path();
        this.spoilerClipRadii = new float[8];
        this.style = 0;
        this.scrimPaint = new Paint(1);
        this.progressPaint = new Paint(1);
        this.animatedProgress = new AnimatedFloat(this, 0, 200, r8);
        this.bounds = new RectF();
        this.animatedReordering = new AnimatedFloat(this, 0, 320, r8);
        this.sharedResources = r11;
        this.currentAccount = r12;
        setChecked(false, false);
        this.imageReceiver.setParentView(this);
        this.imageReceiverFullSize.setParentView(this);
        this.blurImageReceiver.setParentView(this);
        this.imageReceiver.setDelegate(new SharedPhotoVideoCell2$$ExternalSyntheticLambda0(this));
        this.viewsText.setCallback(this);
        this.viewsText.setTextSize(AndroidUtilities.dp(12.0f));
        AnimatedTextView.AnimatedTextDrawable r9 = this.viewsText;
        if (Theme.isCurrentThemeMonet() == false) goto L5;
        int r112 = Theme.getColor(Theme.key_chat_mediaTimeText);
    L6:
        r9.setTextColor(r112);
        this.viewsText.setTypeface(AndroidUtilities.bold());
        this.viewsText.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        setWillNotDraw(false);
        return;
    L5:
        r112 = -1;
        goto L6
    }

    private boolean canAutoDownload(MessageObject r5) {
        if ((System.currentTimeMillis() - lastUpdateDownloadSettingsTime) <= 5000) goto L6;
        lastUpdateDownloadSettingsTime = System.currentTimeMillis();
        lastAutoDownload = DownloadController.getInstance(this.currentAccount).canDownloadMedia(r5);
    L6:
        return lastAutoDownload;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v2, types: [boolean, int] */
    private void drawImpl(Canvas r27, boolean r28, float r29, float r30, float r31) {
        float r7 = getPadding() * r29;
        if (this.isFirst == false) goto L5;
        float r10 = r7;
    L6:
        float r11 = this.animatedReordering.set(this.reordering);
        float r1 = ((getMeasuredWidth() - r10) - r7) * this.imageScale;
        float r2 = ((getMeasuredHeight() - 0.0f) - r7) * this.imageScale;
        if (r28 == false) goto L10;
        ImageReceiver r3 = this.imageReceiverFullSize;
    L9:
        ImageReceiver r12 = r3;
        r12.setAlpha(r30);
        if (this.crossfadeProgress > 0.5f) goto L14;
    L18:
        float r17 = r2;
        if (this.currentMessageObject != null) goto L23;
        if (this.style == 1) goto L23;
    L31:
        if (getParent() == null) goto L43;
        FlickerLoadingView r22 = this.globalGradientView;
        if (r22 == null) goto L43;
        r22.setParentSize(((View) getParent()).getMeasuredWidth(), getMeasuredHeight(), -getX());
        this.globalGradientView.updateColors();
        this.globalGradientView.updateGradient();
        if (this.crossfadeProgress > 0.5f) goto L37;
    L41:
        float r32 = 0.0f;
    L42:
        float r23 = r10 + r32;
        float r302 = 0.5f;
        float r282 = 9.0f;
        float r13 = 1.0f;
        boolean r14 = true;
        float r15 = r1;
        r27.drawRect(r23, r32, r23 + r1, r32 + r17, this.globalGradientView.getPaint());
    L44:
        invalidate();
        ?? r142 = r14;
    L45:
        float r16 = this.imageAlpha;
        if (r16 == r13) goto L48;
        r27.saveLayerAlpha(0.0f, 0.0f, (r10 + r7) + r15, (r7 + 0.0f) + r17, (int) (r16 * 255.0f), 31);
    L49:
        CheckBoxBase r18 = this.checkBoxBase;
        if (r18 == null) goto L54;
        if (r18.isChecked() == false) goto L54;
    L60:
        if (this.check2 == false) goto L62;
    L58:
        float r4 = r10;
        float r5 = r17;
        char r292 = 7;
        ?? r102 = 0;
        Canvas r24 = r27;
    L80:
        if (this.isStory == true) goto L82;
    L92:
        float r19 = this.checkBoxProgress;
        if (r19 <= 0.0f) goto L100;
        if (this.check2 == false) goto L97;
        float r33 = 0.825f;
    L98:
        float r110 = AndroidUtilities.lerp(r13, r33, r19);
        float r34 = r15 * r110;
        float r111 = r110 * r5;
        float r42 = (r4 + (r15 / 2.0f)) - (r34 / 2.0f);
        float r52 = (r5 / 2.0f) - (r111 / 2.0f);
        r12.setImageCoords(r42, r52, r34, r111);
        this.blurImageReceiver.setImageCoords(r42, r52, r34, r111);
    L109:
        if (this.isFirst == true) goto L111;
    L113:
        int r112 = AndroidUtilities.dp(r13);
    L114:
        int r113 = AndroidUtilities.lerp(r112, AndroidUtilities.dp(12.0f), this.checkBoxProgress);
        if (this.isLast == true) goto L117;
    L119:
        int r43 = AndroidUtilities.dp(r13);
    L120:
        r12.setRoundRadius(r113, AndroidUtilities.lerp(r43, AndroidUtilities.dp(12.0f), this.checkBoxProgress), AndroidUtilities.lerp(AndroidUtilities.dp(r13), AndroidUtilities.dp(12.0f), this.checkBoxProgress), AndroidUtilities.lerp(AndroidUtilities.dp(r13), AndroidUtilities.dp(12.0f), this.checkBoxProgress));
        this.blurImageReceiver.setRoundRadius(r12.getRoundRadius());
        if (this.check2 == false) goto L131;
        r24.save();
        if (this.reorder == false) goto L125;
    L126:
        r24.translate(r12.getCenterX(), r12.getCenterY());
        if (this.shaker != null) goto L129;
        this.shaker = new Shaker(this);
    L129:
        this.shaker.concat(r24, Math.max(this.checkBoxProgress, r11));
        float r44 = r13 - (r11 * 0.075f);
        r24.scale(r44, r44);
        r24.translate(-r12.getCenterX(), -r12.getCenterY());
        goto L131
    L125:
        if (this.reordering == true) goto L126;
    L131:
        if (PhotoViewer.isShowingImage(this.currentMessageObject) == true) goto L176;
        r12.draw(r24);
        MessageObject r114 = this.currentMessageObject;
        if (r114 != null) goto L135;
    L170:
        Canvas r115 = r24;
        float r283 = 0.075f;
        float r172 = 0.0f;
        ImageReceiver r232 = r12;
        int r72 = -1;
    L172:
        if (this.isSearchingHashtag == true) goto L178;
        float r25 = this.highlightProgress;
        if (r25 <= r172) goto L178;
        this.sharedResources.highlightPaint.setColor(ColorUtils.setAlphaComponent(-16777216, (int) ((r25 * r302) * 255.0f)));
        r115.drawRect(r232.getDrawRegion(), this.sharedResources.highlightPaint);
    L178:
        if (this.isStoryUploading == false) goto L184;
        this.scrimPaint.setColor(805306368);
        r115.drawRect(r232.getDrawRegion(), this.scrimPaint);
        this.progressPaint.setStyle(Paint.Style.STROKE);
        this.progressPaint.setColor(r72);
        this.progressPaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        this.progressPaint.setStrokeJoin(Paint.Join.ROUND);
        this.progressPaint.setStrokeCap(Paint.Cap.ROUND);
        float r26 = AndroidUtilities.dp(18.0f);
        RectF r210 = AndroidUtilities.rectTmp;
        r210.set(r232.getCenterX() - r26, r232.getCenterY() - r26, r232.getCenterX() + r26, r232.getCenterY() + r26);
        float r35 = ((System.currentTimeMillis() % 1500) / 1500.0f) * 360.0f;
        AnimatedFloat r53 = this.animatedProgress;
        MessageObject r6 = this.currentMessageObject;
        if (r6 == null) goto L182;
        float r62 = r6.getProgress();
    L183:
        r115.drawArc(r210, r35, 360.0f * r53.set(AndroidUtilities.lerp(0.15f, 0.95f, r62)), false, this.progressPaint);
        invalidate();
        goto L184
    L182:
        r62 = r172;
    L184:
        this.bounds.set(r232.getImageX(), r232.getImageY(), r232.getImageX2(), r232.getImageY2());
        if (this.showLivePhoto == false) goto L189;
        Drawable r211 = Theme.chat_livePhoto;
        if (r211 == null) goto L189;
        r211.setBounds((int) (this.bounds.left + AndroidUtilities.dp(8.0f)), (int) (this.bounds.top + AndroidUtilities.dp(8.0f)), (int) ((this.bounds.left + AndroidUtilities.dp(8.0f)) + (Theme.chat_livePhoto.getIntrinsicWidth() * 0.75f)), (int) ((this.bounds.top + AndroidUtilities.dp(8.0f)) + (Theme.chat_livePhoto.getIntrinsicHeight() * 0.75f)));
        Theme.chat_livePhoto.draw(r115);
    L189:
        drawDuration(r115, this.bounds, r31);
        drawViews(r115, this.bounds, r31);
        boolean r212 = this.isSearchingHashtag;
        RectF r36 = this.bounds;
        if (r212 == true) goto L192;
        drawPrivacy(r115, r36, r31);
    L194:
        if (this.check2 == false) goto L196;
        r115.restore();
    L196:
        CheckBoxBase r213 = this.checkBoxBase;
        if (r213 != null) goto L199;
    L224:
        r115.restore();
        return;
    L199:
        if (this.style != 1) goto L201;
    L202:
        r115.save();
        if (this.check2 == false) goto L213;
        if (this.reorder == false) goto L207;
    L208:
        r115.translate(r232.getCenterX(), r232.getCenterY());
        if (this.shaker != null) goto L211;
        this.shaker = new Shaker(this);
    L211:
        this.shaker.concat(r115, Math.max(this.checkBoxProgress, r11) * r302);
        float r45 = 1.0f - (r11 * r283);
        r115.scale(r45, r45);
        r115.translate(-r232.getCenterX(), -r232.getCenterY());
        goto L213
    L207:
        if (this.reordering == true) goto L208;
    L213:
        if (this.style != 1) goto L217;
        float r214 = ((AndroidUtilities.dp(2.0f) + r15) - AndroidUtilities.dp(25.0f)) - AndroidUtilities.dp(4.0f);
        int r37 = AndroidUtilities.dp(4.0f);
    L215:
        float r38 = r37;
    L220:
        r115.translate(r214, r38);
        this.checkBoxBase.draw(r115);
        if (this.canvasButton == null) goto L223;
        RectF r46 = AndroidUtilities.rectTmp;
        r46.set(r214, r38, this.checkBoxBase.bounds.width() + r214, this.checkBoxBase.bounds.height() + r38);
        this.canvasButton.setRect(r46);
    L223:
        r115.restore();
        goto L224
    L217:
        if (this.check2 == false) goto L219;
        r214 = (AndroidUtilities.dp(2.0f) + r15) - AndroidUtilities.dp((this.checkBoxProgress * 5.0f) + 22.0f);
        r38 = (AndroidUtilities.dp(5.0f) * this.checkBoxProgress) + AndroidUtilities.dp(-2.0f);
        goto L220
    L219:
        r214 = (AndroidUtilities.dp(2.0f) + r15) - AndroidUtilities.dp(29.0f);
        r37 = AndroidUtilities.dp(5.0f);
        goto L215
    L201:
        if (r213.getProgress() == r172) goto L224;
    L192:
        drawAuthor(r115, r36, r31);
        goto L194
    L135:
        if (r114.hasMediaSpoilers() == false) goto L170;
        if (this.currentMessageObject.isMediaSpoilersRevealedInSharedMedia == true) goto L170;
        r24.save();
        RectF r116 = AndroidUtilities.rectTmp;
        r283 = 0.075f;
        r116.set(r12.getImageX(), r12.getImageY(), r12.getImageX2(), r12.getImageY2());
        int[] r47 = r12.getRoundRadius();
        float[] r54 = this.spoilerClipRadii;
        float r63 = r47[r102];
        r54[r142] = r63;
        r54[r102] = r63;
        float r64 = r47[r142];
        r54[3] = r64;
        r54[2] = r64;
        float r65 = r47[2];
        r54[5] = r65;
        r54[4] = r65;
        float r48 = r47[3];
        r54[r292] = r48;
        r54[6] = r48;
        this.spoilerClipPath.rewind();
        Path r49 = this.spoilerClipPath;
        float[] r55 = this.spoilerClipRadii;
        Path.Direction r66 = Path.Direction.CW;
        r49.addRoundRect(r116, r55, r66);
        r24.clipPath(this.spoilerClipPath);
        if (this.spoilerRevealProgress == 0.0f) goto L141;
        this.path.rewind();
        r172 = 0.0f;
        float r293 = r13;
        this.path.addCircle(this.spoilerRevealX, this.spoilerRevealY, this.spoilerMaxRadius * this.spoilerRevealProgress, r66);
        r24.clipPath(this.path, Region.Op.DIFFERENCE);
    L142:
        this.blurImageReceiver.draw(r24);
        if (this.mediaSpoilerEffect2 == null) goto L146;
        r24.save();
        r24.translate(r12.getImageX(), r12.getImageY());
        this.mediaSpoilerEffect2.draw(r24, this, (int) r12.getImageWidth(), (int) r12.getImageHeight());
        r24.restore();
    L149:
        r24.restore();
        if (this.currentMessageObject.isSensitive() == true) goto L152;
        r115 = r24;
        r72 = -1;
        r232 = r12;
    L169:
        invalidate();
        goto L172
    L152:
        if (this.sensitiveText != null) goto L154;
        SpannableStringBuilder r410 = new SpannableStringBuilder("x " + LocaleController.getString(R.string.MessageSensitiveContent));
        r410.setSpan(new ColoredImageSpan(R.drawable.filled_sensitive), r102, r142, 33);
        this.sensitiveText = new Text(r410, 14.0f, AndroidUtilities.bold());
    L154:
        Text r39 = this.sensitiveText;
        if (r15 >= ((AndroidUtilities.dp(13.0f) * 2) + r39.getCurrentWidth())) goto L161;
        if (this.sensitiveTextShort != null) goto L159;
        SpannableStringBuilder r310 = new SpannableStringBuilder("x " + LocaleController.getString(R.string.MessageSensitiveContentShort));
        r310.setSpan(new ColoredImageSpan(R.drawable.filled_sensitive), r102, r142, 33);
        this.sensitiveTextShort = new Text(r310, 14.0f, AndroidUtilities.bold());
    L159:
        r39 = this.sensitiveTextShort;
    L161:
        if (r15 < ((AndroidUtilities.dp(26.0f) * 2) + r39.getCurrentWidth())) goto L163;
        int r411 = 13;
        int r56 = 32;
    L167:
        float r73 = r12.getImageX() + (r12.getImageWidth() / 2.0f);
        float r9 = r12.getImageY() + (r12.getImageHeight() / 2.0f);
        float r103 = r39.getCurrentWidth() + AndroidUtilities.dp(r411 + r411);
        float r57 = AndroidUtilities.dp(r56) / 2.0f;
        Text r21 = r39;
        float r132 = AndroidUtilities.lerp(0.8f, r293, r293 - this.spoilerRevealProgress);
        float r104 = r103 / 2.0f;
        float r311 = r104 * r132;
        float r222 = r57 * r132;
        r232 = r12;
        r116.set(r73 - r311, r9 - r222, r73 + r311, r9 + r222);
        this.rectPath.reset();
        this.rectPath.addRoundRect(r116, r57, r57, r66);
        r24.save();
        r24.clipPath(this.rectPath);
        float r312 = this.blurImageReceiver.getAlpha();
        this.blurImageReceiver.setAlpha((1.0f - this.spoilerRevealProgress) * r312);
        this.blurImageReceiver.draw(r24);
        this.blurImageReceiver.setAlpha(r312);
        r24.restore();
        Paint r313 = Theme.getThemePaint("paintChatTimeBackground");
        int r67 = r313.getAlpha();
        r313.setAlpha((int) ((r67 * (1.0f - this.spoilerRevealProgress)) * 0.35f));
        r24.drawRoundRect(r116, r57, r57, r313);
        r313.setAlpha(r67);
        r24.save();
        r24.scale(r132, r132, r73, r9);
        r72 = -1;
        r21.draw(r24, (r73 - r104) + AndroidUtilities.dp(r411), r9, -1, 1.0f - this.spoilerRevealProgress);
        r115 = r24;
        r115.restore();
        goto L169
    L163:
        if (this.sensitiveTextShort2 != null) goto L165;
        this.sensitiveTextShort2 = new Text(new SpannableStringBuilder(LocaleController.getString(R.string.MessageSensitiveContentShort)), 13.0f, AndroidUtilities.bold());
    L165:
        r39 = this.sensitiveTextShort2;
        r411 = 10;
        r56 = 28;
        goto L167
    L146:
        if (this.mediaSpoilerEffect != null) goto L148;
        this.mediaSpoilerEffect = new SpoilerEffect();
    L148:
        this.mediaSpoilerEffect.setColor(ColorUtils.setAlphaComponent(-1, (int) (Color.alpha(-1) * 0.325f)));
        this.mediaSpoilerEffect.setBounds((int) r12.getImageX(), (int) r12.getImageY(), (int) r12.getImageX2(), (int) r12.getImageY2());
        this.mediaSpoilerEffect.draw(r24);
        goto L149
    L141:
        r172 = 0.0f;
        r293 = r13;
        goto L142
    L176:
        r115 = r24;
        r283 = 0.075f;
        r172 = 0.0f;
        r232 = r12;
        r72 = -1;
        goto L178
    L117:
        if (this.isTop == false) goto L119;
        r43 = AndroidUtilities.dp(18.0f);
        goto L120
    L111:
        if (this.isTop == false) goto L113;
        r112 = AndroidUtilities.dp(18.0f);
        goto L114
    L97:
        r33 = 0.787f;
        goto L98
    L100:
        if (this.crossfadeProgress > r302) goto L102;
    L106:
        float r117 = 0.0f;
    L107:
        float r314 = r4 + r117;
        r12.setImageCoords(r314, r117, r15, r5);
        this.blurImageReceiver.setImageCoords(r314, r117, r15, r5);
        goto L109
    L102:
        if (this.crossfadeToColumnsCount == r282) goto L106;
        if (this.currentParentColumnsCount == 9) goto L106;
        r117 = r13;
        goto L107
    L82:
        if (this.currentParentColumnsCount != r142) goto L92;
        float r118 = getHeight() * 0.72f;
        Drawable r315 = this.gradientDrawable;
        if (r315 == 0) goto L86;
        r315.setBounds(r102, r102, getWidth(), getHeight());
        this.gradientDrawable.draw(r24);
    L91:
        r12.setImageCoords((r15 - r118) / 2.0f, 0.0f, r118, getHeight());
        goto L109
    L86:
        if (this.gradientDrawableLoading == true) goto L91;
        if (r12.getBitmap() == null) goto L91;
        this.gradientDrawableLoading = r142;
        DominantColors.getColors(r102, r12.getBitmap(), Theme.isCurrentThemeDark(), new SharedPhotoVideoCell2$$ExternalSyntheticLambda2(this));
        goto L91
    L62:
        if (this.isFirst == true) goto L64;
    L66:
        float r119 = 0.0f;
    L68:
        if (this.isLast == true) goto L70;
    L72:
        float r316 = 0.0f;
    L74:
        if (r119 == 0.0f) goto L76;
    L78:
        r4 = r10;
        r5 = r17;
        r102 = 0;
        r24 = r27;
        RectF r68 = AndroidUtilities.rectTmp;
        r292 = 7;
        r68.set(r4, 0.0f, r4 + r15, r5);
        float[] r74 = this.chipRadii;
        r74[r142] = r119;
        r74[0] = r119;
        r74[3] = r316;
        r74[2] = r316;
        r74[7] = 0.0f;
        r74[6] = 0.0f;
        r74[5] = 0.0f;
        r74[4] = 0.0f;
        this.chipPath.rewind();
        this.chipPath.addRoundRect(r68, this.chipRadii, Path.Direction.CW);
        r24.drawPath(this.chipPath, SharedResources.m7031$$Nest$fgetbackgroundPaint(this.sharedResources));
        goto L80
    L76:
        if (r316 != 0.0f) goto L78;
        float r120 = r10;
        r102 = 0;
        r5 = r17;
        r27.drawRect(r120, 0.0f, r10 + r15, r5, SharedResources.m7031$$Nest$fgetbackgroundPaint(this.sharedResources));
        r4 = r120;
        r24 = r27;
        r292 = 7;
        goto L80
    L70:
        if (this.isTop == false) goto L72;
        r316 = AndroidUtilities.dp(18.0f);
        goto L74
    L64:
        if (this.isTop == false) goto L66;
        r119 = AndroidUtilities.dp(18.0f);
    L54:
        if (this.checkBoxProgress > 0.0f) goto L60;
        if (PhotoViewer.isShowingImage(this.currentMessageObject) == false) goto L58;
    L48:
        r27.save();
        goto L49
    L37:
        if (this.crossfadeToColumnsCount == 9.0f) goto L41;
        if (this.currentParentColumnsCount == 9) goto L41;
        r32 = 1.0f;
    L43:
        r14 = true;
        r302 = 0.5f;
        r282 = 9.0f;
        r15 = r1;
        r13 = 1.0f;
    L23:
        if (r12.hasBitmapImage() == false) goto L31;
        if (r12.getCurrentAlpha() != 1.0f) goto L31;
        if (this.imageAlpha != 1.0f) goto L31;
        r142 = 1;
        r302 = 0.5f;
        r282 = 9.0f;
        r15 = r1;
        r13 = 1.0f;
        goto L45
    L14:
        if (this.crossfadeToColumnsCount == 9.0f) goto L18;
        if (this.currentParentColumnsCount == 9) goto L18;
        float r317 = r29 * 2.0f;
        r1 = r1 - r317;
        r2 = r2 - r317;
        goto L18
    L10:
        r3 = this.imageReceiver;
        goto L9
    L5:
        r10 = 0.0f;
        goto L6
    }

    private float getPadding() {
        if (this.crossfadeProgress == 0.0f) goto L15;
        float r0 = this.crossfadeToColumnsCount;
        if (r0 == 9.0f) goto L9;
        if (this.currentParentColumnsCount != 9) goto L15;
    L9:
        if (r0 != 9.0f) goto L13;
        float r02 = AndroidUtilities.dpf2(1.0f) * this.crossfadeProgress;
        float r1 = AndroidUtilities.dpf2(2.0f);
        float r6 = this.crossfadeProgress;
    L12:
        return r02 + (r1 * (1.0f - r6));
    L13:
        r02 = AndroidUtilities.dpf2(2.0f) * this.crossfadeProgress;
        r1 = AndroidUtilities.dpf2(1.0f);
        r6 = this.crossfadeProgress;
    L15:
        if (this.currentParentColumnsCount != 9) goto L19;
        return AndroidUtilities.dpf2(1.0f);
    L19:
        return AndroidUtilities.dpf2(2.0f);
    }

    private int getPrivacyType(MessageObject r5) {
        if (this.isStoryPinned == false) goto L7;
        return 100;
    L7:
        if (this.isStory == false) goto L23;
        if (r5 == null) goto L23;
        TL_stories.StoryItem r0 = r5.storyItem;
        if (r0 == null) goto L23;
        if (r0.parsedPrivacy != null) goto L14;
        r0.parsedPrivacy = new StoryPrivacyBottomSheet.StoryPrivacy(this.currentAccount, r0.privacy);
    L14:
        int r4 = r5.storyItem.parsedPrivacy.type;
        if (r4 != 2) goto L17;
    L22:
        return r4;
    L17:
        if (r4 == 1) goto L22;
        if (r4 == 3) goto L22;
        return -1;
    L23:
        return -1;
    }

    private TLRPC.MessageMedia getStoryMedia(MessageObject r1) {
        if (r1 == null) goto L8;
        TL_stories.StoryItem r0 = r1.storyItem;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.media;
    L8:
        return null;
    }

    private /* synthetic */ void lambda$drawImpl$2(int[] r3) {
        if (this.gradientDrawableLoading == true) goto L5;
        return;
    L5:
        this.gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, r3);
        invalidate();
        this.gradientDrawableLoading = false;
    }

    private /* synthetic */ void lambda$new$0(ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        if (r2 == false) goto L14;
        if (r3 == true) goto L14;
        MessageObject r12 = this.currentMessageObject;
        if (r12 == null) goto L14;
        if (r12.hasMediaSpoilers() == false) goto L14;
        if (this.imageReceiver.getBitmap() == null) goto L14;
        if (this.blurImageReceiver.getBitmap() == null) goto L13;
        this.blurImageReceiver.getBitmap().recycle();
    L13:
        this.blurImageReceiver.setImageBitmap(Utilities.stackBlurBitmapMax(this.imageReceiver.getBitmap()));
    L14:
        if (r2 == false) goto L24;
        if (r3 == false) goto L17;
        return;
    L17:
        if (this.check2 == true) goto L19;
        return;
    L19:
        if (this.imageReceiver.getBitmap() == null) goto L27;
        int r13 = AndroidUtilities.getDominantColor(this.imageReceiver.getBitmap());
        this.imageReceiverColor = r13;
        CheckBoxBase r0 = this.checkBoxBase;
        if (r0 == null) goto L28;
        r0.setBackgroundColor(Theme.blendOver(r13, Theme.multAlpha(-1, 0.25f)));
        return;
    L28:
        return;
    L27:
        return;
    }

    private /* synthetic */ void lambda$setStyle$1() {
        onCheckBoxPressed();
    }

    private /* synthetic */ void lambda$startRevealMedia$3(ValueAnimator r1) {
        this.spoilerRevealProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private boolean mediaEqual(TLRPC.MessageMedia r4, TLRPC.MessageMedia r5) {
        if (r4 != null) goto L7;
        if (r5 != null) goto L7;
        return true;
    L7:
        if (r4 == null) goto L25;
        if (r5 == null) goto L25;
        TLRPC.Document r1 = r4.document;
        if (r1 == null) goto L18;
        TLRPC.Document r42 = r5.document;
        if (r42 != null) goto L15;
    L17:
        return false;
    L15:
        if (r42.id != r1.id) goto L17;
        return true;
    L18:
        TLRPC.Photo r43 = r4.photo;
        if (r43 == null) goto L25;
        TLRPC.Photo r52 = r5.photo;
        if (r52 == null) goto L25;
        if (r52.id != r43.id) goto L25;
        return true;
    L25:
        return false;
    }

    private void setMessageObject(MessageObject r21, int r22, boolean r23) {
        int r3 = r22;
        if (r3 >= 1) goto L5;
        r3 = 1;
    L5:
        int r4 = this.currentParentColumnsCount;
        this.currentParentColumnsCount = r3;
        MessageObject r5 = this.currentMessageObject;
        if (r5 != null) goto L10;
        if (r21 != null) goto L10;
        return;
    L10:
        if (r5 == null) goto L38;
        if (r21 == null) goto L38;
        if (r5.getId() != r21.getId()) goto L38;
        MessageObject r52 = this.currentMessageObject;
        if (r52 == null) goto L17;
        StoriesController.UploadingStory r8 = r52.uploadingStory;
    L19:
        if (r8 != r21.uploadingStory) goto L38;
        if (r52 == null) goto L22;
        StoriesController.StoriesList r82 = r52.parentStoriesList;
    L24:
        if (r82 != r21.parentStoriesList) goto L38;
        if (mediaEqual(getStoryMedia(r52), getStoryMedia(r21)) == false) goto L38;
        if (r4 != r3) goto L38;
        int r42 = this.privacyType;
        if (r42 != 100) goto L31;
        boolean r53 = true;
    L33:
        if (r53 != this.isStoryPinned) goto L38;
        if (r42 != getPrivacyType(r21)) goto L38;
        if (r23 == true) goto L38;
        return;
    L31:
        r53 = false;
        goto L33
    L22:
        r82 = null;
        goto L24
    L17:
        r8 = null;
    L38:
        this.currentMessageObject = r21;
        if (r21 != null) goto L41;
    L43:
        boolean r43 = false;
    L44:
        this.isStory = r43;
        MessageObject r44 = this.currentMessageObject;
        if (r44 != null) goto L47;
    L49:
        boolean r45 = false;
    L50:
        this.isStoryUploading = r45;
        updateSpoilers2();
        float r54 = 0.0f;
        if (r21 != null) goto L55;
        this.imageReceiver.onDetachedFromWindow();
        this.imageReceiverFullSize.onDetachedFromWindow();
        this.blurImageReceiver.onDetachedFromWindow();
        this.videoText = null;
        this.drawViews = false;
        this.viewsAlpha.set(0.0f, true);
        this.viewsText.setText(_UrlKt.FRAGMENT_ENCODE_SET, false);
        this.videoInfoLayot = null;
        this.showVideoLayout = false;
        this.showLivePhoto = false;
        this.gradientDrawableLoading = false;
        this.gradientDrawable = null;
        this.privacyType = -1;
        this.privacyBitmap = null;
        this.authorText = null;
        updateAccessibilityDescription();
        return;
    L55:
        if (this.attached == false) goto L57;
        this.imageReceiver.onAttachedToWindow();
        this.imageReceiverFullSize.onAttachedToWindow();
        this.blurImageReceiver.onAttachedToWindow();
    L57:
        if (r23 == false) goto L60;
        ImageReceiver r83 = this.imageReceiverFullSize;
    L59:
        ImageReceiver r9 = r83;
        String r84 = MessagesController.getInstance(this.currentAccount).getRestrictionReason(r21.messageOwner.restriction_reason);
        int r10 = (int) ((AndroidUtilities.displaySize.x / r3) / AndroidUtilities.density);
        if (r23 == false) goto L64;
        r10 = (((int) (AndroidUtilities.displaySize.x / AndroidUtilities.density)) * 3) / 5;
    L64:
        String r11 = this.sharedResources.getFilterString(r10);
        if (r3 <= 2) goto L69;
        if (r23 == true) goto L69;
        int r12 = 320;
    L70:
        this.videoText = null;
        this.videoInfoLayot = null;
        this.showVideoLayout = false;
        this.showLivePhoto = false;
        this.imageReceiver.clearDecorators();
        this.imageReceiverFullSize.clearDecorators();
        if (this.isStory == false) goto L79;
        TL_stories.StoryViews r13 = r21.storyItem.views;
        if (r13 == null) goto L79;
        int r46 = r13.views_count;
        if (r46 <= 0) goto L77;
        boolean r132 = true;
    L78:
        this.drawViews = r132;
        this.viewsText.setText(AndroidUtilities.formatWholeNumber(r46, 0), false);
    L80:
        AnimatedFloat r47 = this.viewsAlpha;
        if (this.drawViews == false) goto L83;
        r54 = 1.0f;
    L83:
        r47.set(r54, true);
        if (r21.parentStoriesList == null) goto L86;
        Object r18 = r21.storyItem;
    L88:
        if (TextUtils.isEmpty(r84) == true) goto L90;
        int r48 = 2;
    L196:
        r9.setImageBitmap(ContextCompat.getDrawable(getContext(), R.drawable.photo_placeholder_in));
    L198:
        if (this.blurImageReceiver.getBitmap() == null) goto L201;
        this.blurImageReceiver.getBitmap().recycle();
        this.blurImageReceiver.setImageBitmap(null);
    L201:
        if (r9.getBitmap() != null) goto L203;
    L207:
        TL_stories.StoryItem r55 = r21.storyItem;
        if (r55 == null) goto L210;
        r9.addDecorator(new StoryWidgetsImageDecorator(r55));
    L210:
        setPrivacyType(getPrivacyType(r21));
        if (this.isSearchingHashtag == false) goto L221;
        long r56 = r21.getDialogId();
        SpannableStringBuilder r1 = new SpannableStringBuilder("x ");
        r1.append(MessagesController.getInstance(this.currentAccount).getPeerName(r56));
        int r92 = this.currentAccount;
        if (r3 != r48) goto L215;
        float r102 = 16.0f;
    L216:
        AvatarSpan r85 = new AvatarSpan(this, r92, r102);
        r85.setDialogId(r56);
        r1.setSpan(r85, 0, 1, 33);
        if (r3 != r48) goto L219;
        float r32 = 14.0f;
    L220:
        this.authorText = new Text(r1, r32, AndroidUtilities.bold());
        goto L221
    L219:
        r32 = 10.1666f;
        goto L220
    L215:
        r102 = 13.66f;
    L221:
        updateAccessibilityDescription();
        invalidate();
        return;
    L203:
        if (this.currentMessageObject.hasMediaSpoilers() == false) goto L207;
        if (this.currentMessageObject.isMediaSpoilersRevealed == true) goto L207;
        this.blurImageReceiver.setImageBitmap(Utilities.stackBlurBitmapMax(r9.getBitmap()));
        goto L207
    L90:
        TL_stories.StoryItem r49 = r21.storyItem;
        if (r49 != null) goto L93;
    L95:
        StoriesController.UploadingStory r410 = r21.uploadingStory;
        if (r410 == null) goto L100;
        String r411 = r410.firstFramePath;
        if (r411 == null) goto L100;
        ImageLocation r412 = ImageLocation.getForPath(r411);
        r48 = 2;
        r9.setImage(r412, r11, null, null, r18, 0);
    L100:
        r48 = 2;
        TLRPC.Document r57 = r21.getDocument();
        TLRPC.Photo r86 = r21.getPhoto();
        if (MessageObject.isVideoDocument(r57) == false) goto L149;
        this.showVideoLayout = !r21.isLivePhoto();
        this.showLivePhoto = r21.isLivePhoto();
        if (r3 != 9) goto L105;
    L107:
        ImageLocation r103 = r21.mediaThumb;
        if (r103 == null) goto L114;
        BitmapDrawable r122 = r21.strippedThumb;
        if (r122 == null) goto L112;
        r9.setImage(r103, r11, r122, null, r18, 0);
        goto L198
    L112:
        r9.setImage(r103, r11, r21.mediaSmallThumb, r11 + "_b", null, 0, null, r18, 0);
        goto L198
    L114:
        if (r21.hasVideoCover() == false) goto L138;
        TLRPC.PhotoSize r58 = FileLoader.getClosestPhotoSizeWithSize(r21.photoThumbs, 50);
        TLRPC.PhotoSize r87 = FileLoader.getClosestPhotoSizeWithSize(r21.photoThumbs, r12, false, r58, this.isStory);
        if (r87 != r58) goto L118;
        r58 = null;
    L118:
        BitmapDrawable r104 = r21.strippedThumb;
        TLObject r123 = r21.photoThumbsObject;
        if (r104 == null) goto L129;
        ImageLocation r105 = ImageLocation.getForObject(r87, r123);
        BitmapDrawable r14 = r21.strippedThumb;
        if (r87 == null) goto L123;
        long r15 = r87.size;
    L125:
        if (r21.shouldEncryptPhotoOrVideo() == false) goto L127;
        int r19 = 2;
    L128:
        r9.setImage(r105, r11, null, null, r14, r15, null, r18, r19);
        goto L198
    L127:
        r19 = 1;
        goto L128
    L123:
        r15 = 0;
        goto L125
    L129:
        ImageLocation r106 = ImageLocation.getForObject(r87, r123);
        ImageLocation r124 = ImageLocation.getForObject(r58, r21.photoThumbsObject);
        String r133 = r11 + "_b";
        if (r87 == null) goto L132;
        long r142 = r87.size;
    L133:
        Object r17 = r18;
        if (r21.shouldEncryptPhotoOrVideo() == false) goto L136;
        int r182 = 2;
    L137:
        r9.setImage(r106, r11, r124, r133, r142, null, r17, r182);
        goto L198
    L136:
        r182 = 1;
        goto L137
    L132:
        r142 = 0;
        goto L133
    L138:
        TLRPC.Document r88 = r21.getDocument();
        TLRPC.PhotoSize r59 = FileLoader.getClosestPhotoSizeWithSize(r88.thumbs, 50);
        TLRPC.PhotoSize r107 = FileLoader.getClosestPhotoSizeWithSize(r88.thumbs, r12, false, null, this.isStory);
        if (r59 == r107) goto L141;
    L143:
        if (r59 == null) goto L196;
        if (r21.strippedThumb == null) goto L147;
        r9.setImage(ImageLocation.getForDocument(r107, r88), r11, r21.strippedThumb, null, r18, 0);
        goto L198
    L147:
        r9.setImage(ImageLocation.getForDocument(r107, r88), r11, ImageLocation.getForDocument(r59, r88), r11 + "_b", null, 0, null, r18, 0);
        goto L198
    L141:
        if (this.isStory == true) goto L143;
        r107 = null;
        goto L143
    L105:
        if (r21.isLivePhoto() == true) goto L107;
        this.videoText = AndroidUtilities.formatShortDuration((int) r21.getDuration());
        goto L107
    L149:
        if (r86 == null) goto L196;
        if (r21.photoThumbs.isEmpty() == true) goto L196;
        if (r21.mediaExists == false) goto L155;
    L163:
        ImageLocation r108 = r21.mediaThumb;
        if (r108 == null) goto L169;
        BitmapDrawable r125 = r21.strippedThumb;
        if (r125 == null) goto L168;
        r9.setImage(r108, r11, r125, null, r18, 0);
        goto L198
    L168:
        r9.setImage(r108, r11, r21.mediaSmallThumb, r11 + "_b", null, 0, null, r18, 0);
        goto L198
    L169:
        TLRPC.PhotoSize r510 = FileLoader.getClosestPhotoSizeWithSize(r21.photoThumbs, 50);
        TLRPC.PhotoSize r89 = FileLoader.getClosestPhotoSizeWithSize(r21.photoThumbs, r12, false, r510, this.isStory);
        if (r89 != r510) goto L172;
        r510 = null;
    L172:
        BitmapDrawable r109 = r21.strippedThumb;
        TLObject r126 = r21.photoThumbsObject;
        if (r109 == null) goto L183;
        ImageLocation r1010 = ImageLocation.getForObject(r89, r126);
        BitmapDrawable r143 = r21.strippedThumb;
        if (r89 == null) goto L177;
        long r152 = r89.size;
    L179:
        if (r21.shouldEncryptPhotoOrVideo() == false) goto L181;
        int r192 = 2;
    L182:
        r9.setImage(r1010, r11, null, null, r143, r152, null, r18, r192);
        goto L198
    L181:
        r192 = 1;
        goto L182
    L177:
        r152 = 0;
        goto L179
    L183:
        ImageLocation r1011 = ImageLocation.getForObject(r89, r126);
        if (r23 == false) goto L186;
        ImageLocation r127 = null;
    L187:
        String r134 = r11 + "_b";
        if (r89 == null) goto L190;
        long r144 = r89.size;
    L191:
        Object r172 = r18;
        if (r21.shouldEncryptPhotoOrVideo() == false) goto L194;
        int r183 = 2;
    L195:
        r9.setImage(r1011, r11, r127, r134, r144, null, r172, r183);
        goto L198
    L194:
        r183 = 1;
        goto L195
    L190:
        r144 = 0;
        goto L191
    L186:
        r127 = ImageLocation.getForObject(r510, r21.photoThumbsObject);
        goto L187
    L155:
        if (canAutoDownload(r21) == true) goto L163;
        if (this.isStory == true) goto L163;
        BitmapDrawable r145 = r21.strippedThumb;
        if (r145 == null) goto L162;
        r9.setImage(null, null, null, null, r145, 0, null, r18, 0);
        goto L198
    L162:
        r9.setImage(null, null, ImageLocation.getForObject(FileLoader.getClosestPhotoSizeWithSize(r21.photoThumbs, 50), r21.photoThumbsObject), "b", null, 0, null, r18, 0);
        goto L198
    L93:
        if ((r49.media instanceof TLRPC.TL_messageMediaUnsupported) == false) goto L95;
        r49.dialogId = r21.getDialogId();
        Drawable r413 = getContext().getResources().getDrawable(R.drawable.msg_emoji_recent).mutate();
        r413.setColorFilter(new PorterDuffColorFilter(1090519039, PorterDuff.Mode.SRC_IN));
        r9.setImageBitmap(new CombinedDrawable(new ColorDrawable(-13421773), r413));
        r48 = 2;
        goto L198
    L86:
        r18 = r21;
        goto L88
    L77:
        r132 = false;
    L79:
        this.drawViews = false;
        this.viewsAlpha.set(0.0f, true);
        this.viewsText.setText(_UrlKt.FRAGMENT_ENCODE_SET, false);
    L69:
        r12 = AndroidUtilities.getPhotoSize();
        goto L70
    L60:
        r83 = this.imageReceiver;
        goto L59
    L47:
        if (r44.uploadingStory == null) goto L49;
        r45 = true;
        goto L50
    L41:
        if (r21.isStory() == false) goto L43;
        r43 = true;
        goto L44
    }

    private void setPrivacyType(int r3) {
        if (this.privacyType != r3) goto L5;
        return;
    L5:
        this.privacyType = r3;
        this.privacyBitmap = null;
        if (r3 != 1) goto L8;
        int r32 = R.drawable.msg_stories_closefriends;
    L18:
        if (r32 == 0) goto L20;
        this.privacyBitmap = this.sharedResources.getPrivacyBitmap(getContext(), r32);
    L20:
        invalidate();
        return;
    L8:
        if (r3 != 2) goto L10;
        r32 = R.drawable.msg_folders_private;
        goto L18
    L10:
        if (r3 != 3) goto L12;
        r32 = R.drawable.msg_folders_groups;
        goto L18
    L12:
        if (r3 == 100) goto L14;
        r32 = 0;
        goto L18
    L14:
        r32 = R.drawable.msg_pin_mini;
        goto L18
    }

    private void updateAccessibilityDescription() {
        MessageObject r1 = this.currentMessageObject;     // Catch: Exception -> L7
        if (r1 != null) goto L9;
        setContentDescription(null);     // Catch: Exception -> L7
        return;
    L9:
        boolean r2 = r1.isStory();     // Catch: Exception -> L7
        StringBuilder r3 = new StringBuilder();     // Catch: Exception -> L7
        if (r2 == false) goto L15;
        if (this.isStoryPinned == false) goto L15;
        r3.append(LocaleController.getString(R.string.AccDescrStoryPinned));     // Catch: Exception -> L7
    L15:
        if (r1.isLivePhoto() == false) goto L19;
        String r4 = LocaleController.getString(R.string.AccDescrLivePhoto);     // Catch: Exception -> L7
    L17:
        int r6 = 0;
    L31:
        if (r3.length() <= 0) goto L33;
        r3.append(", ");     // Catch: Exception -> L7
    L33:
        r3.append(r4);     // Catch: Exception -> L7
        if (r6 <= 0) goto L36;
        r3.append(", ");     // Catch: Exception -> L7
        r3.append(LocaleController.formatDuration(r6));     // Catch: Exception -> L7
    L36:
        if (r2 == false) goto L47;
        TL_stories.StoryItem r22 = r1.storyItem;     // Catch: Exception -> L7
        if (r22 == null) goto L47;
        TL_stories.StoryViews r23 = r22.views;     // Catch: Exception -> L7
        if (r23 == null) goto L45;
        if (r23.views_count <= 0) goto L45;
        r3.append(", ");     // Catch: Exception -> L7
        r3.append(LocaleController.formatPluralString("Views", r1.storyItem.views.views_count, new Object[0]));     // Catch: Exception -> L7
    L45:
        if (r1.storyItem.date <= 0) goto L47;
        r3.append(", ");     // Catch: Exception -> L7
        r3.append(LocaleController.formatString(R.string.AccDescrPostedDate, new Object[]{LocaleController.formatDateAudio(r1.storyItem.date, false)}));     // Catch: Exception -> L7
    L47:
        setContentDescription(r3.toString());     // Catch: Exception -> L7
        return;
    L19:
        if (r1.isRoundVideo() == false) goto L23;
        r4 = LocaleController.getString(R.string.AccDescrRoundVideo);     // Catch: Exception -> L7
        double r62 = r1.getDuration();     // Catch: Exception -> L7
    L21:
        r6 = (int) r62;     // Catch: Exception -> L7
        goto L31
    L23:
        if (r1.isVideo() == false) goto L25;
    L28:
        r4 = LocaleController.getString(R.string.AttachVideo);     // Catch: Exception -> L7
        r62 = r1.getDuration();     // Catch: Exception -> L7
        goto L21
    L25:
        if (r1.isVideoStory() == true) goto L28;
        r4 = LocaleController.getString(R.string.AttachPhoto);     // Catch: Exception -> L7
    L7:
        e = move-exception;
        FileLog.e(e);
        setContentDescription(null);     // Catch: Exception -> L52
        return;
    }

    private void updateSpoilers2() {
        if (getMeasuredHeight() > 0) goto L5;
        return;
    L5:
        if (getMeasuredWidth() <= 0) goto L24;
        MessageObject r0 = this.currentMessageObject;
        if (r0 != null) goto L10;
    L17:
        SpoilerEffect2 r02 = this.mediaSpoilerEffect2;
        if (r02 == null) goto L23;
        r02.detach(this);
        this.mediaSpoilerEffect2 = null;
        return;
    L23:
        return;
    L10:
        if (r0.hasMediaSpoilers() == false) goto L17;
        if (SpoilerEffect2.supports() == false) goto L17;
        if (this.mediaSpoilerEffect2 != null) goto L22;
        this.mediaSpoilerEffect2 = SpoilerEffect2.getInstance(this);
        return;
    L22:
        return;
    }

    public boolean canRevealSpoiler() {
        MessageObject r0 = this.currentMessageObject;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.hasMediaSpoilers() == true) goto L7;
        return false;
    L7:
        if (this.spoilerRevealProgress == 0.0f) goto L9;
        return false;
    L9:
        if (this.currentMessageObject.isMediaSpoilersRevealedInSharedMedia == true) goto L16;
        return true;
    L16:
        return false;
    }

    public void customDraw(View r10, Canvas r11, float r12, float r13, float r14) {
        r11.save();
        if (this.clipPath != null) goto L5;
        this.clipPath = new Path();
    L5:
        this.clipPath.rewind();
        RectF r4 = AndroidUtilities.rectTmp;
        r4.set(0.0f, 0.0f, r12, r13);
        float r5 = AndroidUtilities.dp(12.0f) * r14;
        this.clipPath.addRoundRect(r4, r5, r5, Path.Direction.CW);
        this.clipPath.close();
        r11.clipPath(this.clipPath);
        r11.scale(r12 / getWidth(), r13 / getHeight());
        boolean r7 = this.imageReceiverFullSize.hasImageLoaded();
        if (r7 == true) goto L8;
    L9:
        float r3 = 1.0f - r14;
        drawImpl(r11, false, r3, 1.0f, r3);
    L10:
        if (r7 == true) goto L12;
    L14:
        r11.restore();
        return;
    L12:
        if (r14 <= 0.0f) goto L14;
        drawImpl(r11, true, 1.0f - r14, r14, 0.0f);
        goto L14
    L8:
        if (r14 >= 1.0f) goto L10;
        goto L9
    }

    public void drawAuthor(Canvas r10, RectF r11, float r12) {
        if (this.isStory == false) goto L19;
        ImageReceiver r0 = this.imageReceiver;
        if (r0 == null) goto L9;
        if (r0.getVisible() == true) goto L9;
        return;
    L9:
        if (this.isSearchingHashtag == true) goto L11;
        return;
    L11:
        if (this.authorText == null) goto L22;
        float r02 = AndroidUtilities.dp(5.33f);
        Text r3 = this.authorText.ellipsize((int) (r11.width() - (2.0f * r02))).setVerticalClipPadding(AndroidUtilities.dp(14.0f)).setShadow(0.4f * r12);
        float r5 = r11.left + r02;
        float r112 = r11.top;
        if (this.currentParentColumnsCount > 2) goto L16;
        float r9 = 15.0f;
    L17:
        r3.draw(r10, r5, r112 + AndroidUtilities.dp(r9), Theme.multAlpha(-1, r12), 1.0f);
        return;
    L16:
        r9 = 11.33f;
        goto L17
    L22:
        return;
    }

    public void drawCrossafadeImage(Canvas r4) {
        if (this.crossfadeView == null) goto L6;
        r4.save();
        r4.translate(getX(), getY());
        this.crossfadeView.setImageScale(((getMeasuredWidth() - AndroidUtilities.dp(2.0f)) * this.imageScale) / (this.crossfadeView.getMeasuredWidth() - AndroidUtilities.dp(2.0f)), false);
        this.crossfadeView.draw(r4);
        r4.restore();
        return;
    }

    public void drawDuration(Canvas r20, RectF r21, float r22) {
        float r3 = r22;
        if (this.showVideoLayout == false) goto L57;
        ImageReceiver r4 = this.imageReceiver;
        if (r4 != null) goto L7;
    L9:
        float r42 = r21.width() + (AndroidUtilities.dp(20.0f) * this.checkBoxProgress);
        float r5 = r21.width() / r42;
        if (r3 >= 1.0f) goto L12;
        r3 = (float) Math.pow(r3, 8.0d);
    L12:
        r20.save();
        r20.translate(r21.left, r21.top);
        r20.scale(r5, r5, 0.0f, r21.height());
        r20.clipRect(0.0f, 0.0f, r21.width(), r21.height());
        int r52 = this.currentParentColumnsCount;
        if (r52 != 9) goto L15;
    L19:
        if (r52 >= 9) goto L23;
        if (this.videoText == null) goto L23;
    L25:
        boolean r43 = viewsOnLeft(r42);
        int r53 = AndroidUtilities.dp(8.0f);
        StaticLayout r7 = this.videoInfoLayot;
        int r9 = 0;
        if (r7 == null) goto L28;
        int r72 = r7.getWidth();
    L29:
        int r54 = r53 + r72;
        if (this.drawVideoIcon == false) goto L32;
        int r73 = AndroidUtilities.dp(10.0f);
    L33:
        int r55 = r54 + r73;
        float r10 = AndroidUtilities.dp(5.0f);
        float r6 = ((AndroidUtilities.dp(1.0f) + r21.height()) - AndroidUtilities.dp(17.0f)) - AndroidUtilities.dp(4.0f);
        if (r43 == false) goto L36;
        int r44 = AndroidUtilities.dp(22.0f);
    L37:
        r20.translate(r10, r6 - r44);
        RectF r45 = AndroidUtilities.rectTmp;
        r45.set(0.0f, 0.0f, r55, AndroidUtilities.dp(17.0f));
        int r56 = Theme.chat_timeBackgroundPaint.getAlpha();
        Theme.chat_timeBackgroundPaint.setAlpha((int) (r56 * r3));
        r20.drawRoundRect(r45, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), Theme.chat_timeBackgroundPaint);
        Theme.chat_timeBackgroundPaint.setAlpha(r56);
        if (this.drawVideoIcon == false) goto L50;
        r20.save();
        if (this.videoInfoLayot != null) goto L43;
        int r46 = AndroidUtilities.dp(5.0f);
    L44:
        r20.translate(r46, (AndroidUtilities.dp(17.0f) - this.sharedResources.playDrawable.getIntrinsicHeight()) / 2.0f);
        boolean r47 = Theme.isCurrentThemeMonet();
        SharedResources r62 = this.sharedResources;
        if (r47 == false) goto L47;
        r62.playDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_mediaTimeText), PorterDuff.Mode.MULTIPLY));
    L48:
        this.sharedResources.playDrawable.draw(r20);
        r20.restore();
        goto L50
    L47:
        r62.playDrawable.setAlpha((int) ((this.imageAlpha * 255.0f) * r3));
        goto L48
    L43:
        r46 = AndroidUtilities.dp(4.0f);
    L50:
        if (this.videoInfoLayot != null) goto L52;
    L55:
        r20.restore();
        return;
    L52:
        if (this.drawVideoIcon == false) goto L54;
        r9 = 10;
    L54:
        r20.translate(AndroidUtilities.dp(r9 + 4), (AndroidUtilities.dp(17.0f) - this.videoInfoLayot.getHeight()) / 2.0f);
        int r2 = this.sharedResources.textPaint.getAlpha();
        this.sharedResources.textPaint.setAlpha((int) (r2 * r3));
        this.videoInfoLayot.draw(r20);
        this.sharedResources.textPaint.setAlpha(r2);
        goto L55
    L36:
        r44 = 0;
        goto L37
    L32:
        r73 = 0;
        goto L33
    L28:
        r72 = 0;
    L23:
        if (this.videoInfoLayot == null) goto L25;
        this.videoInfoLayot = null;
        goto L25
    L15:
        if (this.videoInfoLayot != null) goto L19;
        if (this.videoText == null) goto L19;
        this.videoInfoLayot = new StaticLayout(this.videoText, this.sharedResources.textPaint, (int) Math.ceil(this.sharedResources.textPaint.measureText(r9)), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        goto L25
    L7:
        if (r4.getVisible() == true) goto L9;
        return;
    }

    public void drawPrivacy(Canvas r5, RectF r6, float r7) {
        if (this.isStory == false) goto L14;
        Bitmap r0 = this.privacyBitmap;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isRecycled() == true) goto L16;
        int r02 = AndroidUtilities.dp((r6.width() / (r6.width() + (AndroidUtilities.dp(20.0f) * this.checkBoxProgress))) * 17.33f);
        r5.save();
        float r03 = r02;
        r5.translate((r6.right - r03) - AndroidUtilities.dp(5.66f), r6.top + AndroidUtilities.dp(5.66f));
        if (this.privacyPaint != null) goto L12;
        this.privacyPaint = new Paint(3);
    L12:
        this.privacyPaint.setAlpha((int) (r7 * 255.0f));
        RectF r62 = AndroidUtilities.rectTmp;
        r62.set(0.0f, 0.0f, r03, r03);
        r5.drawBitmap(this.privacyBitmap, null, r62, this.privacyPaint);
        r5.restore();
        return;
    L16:
        return;
    }

    public void drawViews(Canvas r9, RectF r10, float r11) {
        if (this.isStory == false) goto L31;
        ImageReceiver r0 = this.imageReceiver;
        if (r0 == null) goto L9;
        if (r0.getVisible() == true) goto L9;
        return;
    L9:
        if (this.currentParentColumnsCount >= 5) goto L33;
        float r02 = r10.width() + (AndroidUtilities.dp(20.0f) * this.checkBoxProgress);
        float r1 = r10.width() / r02;
        boolean r03 = viewsOnLeft(r02);
        float r2 = this.viewsAlpha.set(this.drawViews);
        float r112 = r11 * r2;
        if (r112 >= 1.0f) goto L15;
        r112 = (float) Math.pow(r112, 8.0d);
    L15:
        if (r2 <= 0.0f) goto L34;
        r9.save();
        r9.translate(r10.left, r10.top);
        if (r03 == false) goto L20;
        float r22 = 0.0f;
    L21:
        r9.scale(r1, r1, r22, r10.height());
        r9.clipRect(0.0f, 0.0f, r10.width(), r10.height());
        float r12 = AndroidUtilities.dp(26.0f) + this.viewsText.getCurrentWidth();
        if (r03 == false) goto L24;
        float r04 = AndroidUtilities.dp(5.0f);
    L25:
        r9.translate(r04, ((AndroidUtilities.dp(1.0f) + r10.height()) - AndroidUtilities.dp(17.0f)) - AndroidUtilities.dp(4.0f));
        RectF r05 = AndroidUtilities.rectTmp;
        r05.set(0.0f, 0.0f, r12, AndroidUtilities.dp(17.0f));
        int r23 = Theme.chat_timeBackgroundPaint.getAlpha();
        Theme.chat_timeBackgroundPaint.setAlpha((int) (r23 * r112));
        r9.drawRoundRect(r05, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), Theme.chat_timeBackgroundPaint);
        Theme.chat_timeBackgroundPaint.setAlpha(r23);
        r9.save();
        r9.translate(AndroidUtilities.dp(3.0f), (AndroidUtilities.dp(17.0f) - this.sharedResources.viewDrawable.getBounds().height()) / 2.0f);
        boolean r06 = Theme.isCurrentThemeMonet();
        SharedResources r24 = this.sharedResources;
        if (r06 == false) goto L28;
        r24.viewDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_mediaTimeText), PorterDuff.Mode.MULTIPLY));
    L29:
        this.sharedResources.viewDrawable.draw(r9);
        r9.restore();
        r9.translate(AndroidUtilities.dp(22.0f), 0.0f);
        this.viewsText.setBounds(0, 0, (int) r12, AndroidUtilities.dp(17.0f));
        this.viewsText.setAlpha((int) (r112 * 255.0f));
        this.viewsText.draw(r9);
        r9.restore();
        return;
    L28:
        r24.viewDrawable.setAlpha((int) ((this.imageAlpha * 255.0f) * r112));
        goto L29
    L24:
        r04 = (r10.width() - AndroidUtilities.dp(5.0f)) - r12;
        goto L25
    L20:
        r22 = r10.width();
        goto L21
    L34:
        return;
    L33:
        return;
    }

    public View getCrossfadeView() {
        return this.crossfadeView;
    }

    public int getMessageId() {
        MessageObject r0 = this.currentMessageObject;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getId();
    }

    public MessageObject getMessageObject() {
        return this.currentMessageObject;
    }

    public int getStyle() {
        return this.style;
    }

    public void initFullSizeReceiver() {
        setMessageObject(this.currentMessageObject, this.currentParentColumnsCount, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        CheckBoxBase r0 = this.checkBoxBase;
        if (r0 == null) goto L6;
        r0.onAttachedToWindow();
    L6:
        if (this.currentMessageObject == null) goto L8;
        this.imageReceiver.onAttachedToWindow();
        this.imageReceiverFullSize.onAttachedToWindow();
        this.blurImageReceiver.onAttachedToWindow();
    L8:
        SpoilerEffect2 r02 = this.mediaSpoilerEffect2;
        if (r02 != null) goto L11;
        return;
    L11:
        if (r02.destroyed == false) goto L14;
        this.mediaSpoilerEffect2 = SpoilerEffect2.getInstance(this);
        return;
    L14:
        r02.attach(this);
    }

    public void onCheckBoxPressed() {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        CheckBoxBase r0 = this.checkBoxBase;
        if (r0 == null) goto L6;
        r0.onDetachedFromWindow();
    L6:
        if (this.currentMessageObject == null) goto L8;
        this.imageReceiver.onDetachedFromWindow();
        this.imageReceiverFullSize.onDetachedFromWindow();
        this.blurImageReceiver.onDetachedFromWindow();
    L8:
        SpoilerEffect2 r02 = this.mediaSpoilerEffect2;
        if (r02 == null) goto L12;
        r02.detach(this);
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r7) {
        super.onDraw(r7);
        drawImpl(r7, false, 1.0f, 1.0f, 1.0f);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
    L7:
        e = move-exception;
        FileLog.e(e);
        return;
    L4:
        if (this.currentMessageObject == null) goto L12;
        r1.setEnabled(true);     // Catch: Exception -> L7
        r1.setClickable(true);     // Catch: Exception -> L7
        r1.addAction(16);     // Catch: Exception -> L7
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        int r32 = View.MeasureSpec.getSize(r3);
        boolean r42 = this.isStory;
        if (r42 == false) goto L5;
        int r0 = (int) (r32 * 1.25f);
    L6:
        if (r42 == true) goto L8;
    L10:
        setMeasuredDimension(r32, r0);
        updateSpoilers2();
        return;
    L8:
        if (this.currentParentColumnsCount != 1) goto L10;
        r0 = r0 / 2;
        goto L10
    L5:
        r0 = r32;
        goto L6
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        CanvasButton r0 = this.canvasButton;
        if (r0 == null) goto L9;
        if (r0.checkTouchEvent(r2) == false) goto L9;
        return true;
    L9:
        return super.onTouchEvent(r2);
    }

    public void setCheck2() {
        this.check2 = true;
    }

    public void setChecked(boolean r8, boolean r9) {
        CheckBoxBase r0 = this.checkBoxBase;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        if (r02 != r8) goto L11;
        return;
    L11:
        if (this.checkBoxBase != null) goto L20;
        CheckBoxBase r03 = new CheckBoxBase(this, 21, null);
        this.checkBoxBase = r03;
        r03.setColor(-1, Theme.key_sharedMedia_photoPlaceholder, Theme.key_checkboxCheck);
        if (this.check2 == false) goto L17;
        int r04 = this.imageReceiverColor;
        if (r04 == 0) goto L17;
        this.checkBoxBase.setBackgroundColor(Theme.blendOver(r04, Theme.multAlpha(-1, 0.25f)));
    L17:
        this.checkBoxBase.setDrawUnchecked(false);
        this.checkBoxBase.setBackgroundType(1);
        this.checkBoxBase.setBounds(0, 0, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        if (this.attached == false) goto L20;
        this.checkBoxBase.onAttachedToWindow();
    L20:
        this.checkBoxBase.setChecked(r8, r9);
        ValueAnimator r05 = this.animator;
        if (r05 == null) goto L23;
        this.animator = null;
        r05.cancel();
    L23:
        float r06 = 0.0f;
        if (r9 == false) goto L29;
        float r92 = this.checkBoxProgress;
        if (r8 == false) goto L28;
        r06 = 1.0f;
    L28:
        ValueAnimator r93 = ValueAnimator.ofFloat(new float[]{r92, r06});
        this.animator = r93;
        r93.addUpdateListener(new AnonymousClass2(this));
        this.animator.setDuration(200);
        this.animator.addListener(new AnonymousClass3(this, r8));
        this.animator.start();
    L32:
        invalidate();
        return;
    L29:
        if (r8 == false) goto L31;
        r06 = 1.0f;
    L31:
        this.checkBoxProgress = r06;
        goto L32
    L5:
        if (r0.isChecked() == false) goto L7;
        r02 = true;
        goto L8
    }

    public void setCrossfadeView(SharedPhotoVideoCell2 r1, float r2, int r3) {
        this.crossfadeView = r1;
        this.crossfadeProgress = r2;
        this.crossfadeToColumnsCount = r3;
    }

    public void setGradientView(FlickerLoadingView r1) {
        this.globalGradientView = r1;
    }

    public void setHighlightProgress(float r2) {
        if (this.highlightProgress == r2) goto L6;
        this.highlightProgress = r2;
        invalidate();
        return;
    }

    public void setImageAlpha(float r2, boolean r3) {
        if (this.imageAlpha == r2) goto L8;
        this.imageAlpha = r2;
        if (r3 == false) goto L9;
        invalidate();
        return;
    L9:
        return;
    }

    public void setImageScale(float r2, boolean r3) {
        if (this.imageScale == r2) goto L8;
        this.imageScale = r2;
        if (r3 == false) goto L9;
        invalidate();
        return;
    L9:
        return;
    }

    public void setMessageObject(MessageObject r2, int r3) {
        setMessageObject(r2, r3, false);
    }

    public void setReorder(boolean r1) {
        this.reorder = r1;
        invalidate();
    }

    public void setReordering(boolean r2, boolean r3) {
        if (this.reordering != r2) goto L5;
        return;
    L5:
        this.reordering = r2;
        if (r3 == true) goto L8;
        this.animatedReordering.force(r2);
    L8:
        invalidate();
    }

    public void setStyle(int r5) {
        if (this.style == r5) goto L13;
        this.style = r5;
        if (r5 != 1) goto L12;
        CheckBoxBase r52 = new CheckBoxBase(this, 21, null);
        this.checkBoxBase = r52;
        r52.setColor(-1, Theme.key_sharedMedia_photoPlaceholder, Theme.key_checkboxCheck);
        this.checkBoxBase.setDrawUnchecked(true);
        this.checkBoxBase.setBackgroundType(0);
        this.checkBoxBase.setBounds(0, 0, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        if (this.attached == false) goto L10;
        this.checkBoxBase.onAttachedToWindow();
    L10:
        CanvasButton r53 = new CanvasButton(this);
        this.canvasButton = r53;
        r53.setDelegate(new SharedPhotoVideoCell2$$ExternalSyntheticLambda1(this));
        return;
    L12:
        return;
    }

    public void setVideoText(String r3, boolean r4) {
        this.videoText = r3;
        if (r3 == null) goto L5;
        boolean r1 = true;
    L6:
        this.showVideoLayout = r1;
        this.showLivePhoto = false;
        if (r1 == false) goto L13;
        StaticLayout r0 = this.videoInfoLayot;
        if (r0 == null) goto L13;
        if (r0.getText().toString().equals(r3) == true) goto L13;
        this.videoInfoLayot = null;
    L13:
        this.drawVideoIcon = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public void startRevealMedia(float r5, float r6) {
        this.spoilerRevealX = r5;
        this.spoilerRevealY = r6;
        this.spoilerMaxRadius = (float) Math.sqrt(Math.pow(getWidth(), 2.0d) + Math.pow(getHeight(), 2.0d));
        ValueAnimator r52 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration((long) MathUtils.clamp(this.spoilerMaxRadius * 0.3f, 250.0f, 550.0f));
        r52.setInterpolator(CubicBezierInterpolator.EASE_BOTH);
        r52.addUpdateListener(new SharedPhotoVideoCell2$$ExternalSyntheticLambda3(this));
        r52.addListener(new AnonymousClass1(this));
        r52.start();
    }

    public void updateViews() {
        if (this.isStory == false) goto L16;
        MessageObject r0 = this.currentMessageObject;
        if (r0 == null) goto L16;
        TL_stories.StoryItem r02 = r0.storyItem;
        if (r02 == null) goto L16;
        TL_stories.StoryViews r03 = r02.views;
        if (r03 == null) goto L16;
        int r04 = r03.views_count;
        if (r04 <= 0) goto L13;
        boolean r3 = true;
    L14:
        this.drawViews = r3;
        this.viewsText.setText(AndroidUtilities.formatWholeNumber(r04, 0), true);
        return;
    L13:
        r3 = false;
    L16:
        this.drawViews = false;
        this.viewsText.setText(_UrlKt.FRAGMENT_ENCODE_SET, false);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.viewsText != r2) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean viewsOnLeft(float r6) {
        if (this.isStory == true) goto L5;
    L27:
        return false;
    L5:
        if (this.currentParentColumnsCount >= 5) goto L27;
        int r0 = AndroidUtilities.dp(26.0f) + ((int) this.viewsText.getCurrentWidth());
        if (this.showVideoLayout == false) goto L18;
        int r2 = AndroidUtilities.dp(8.0f);
        StaticLayout r4 = this.videoInfoLayot;
        if (r4 == null) goto L12;
        int r42 = r4.getWidth();
    L13:
        int r22 = r2 + r42;
        if (this.drawVideoIcon == false) goto L16;
        int r5 = AndroidUtilities.dp(10.0f);
    L17:
        int r23 = r22 + r5;
    L19:
        if (r0 <= 0) goto L22;
        if (r23 <= 0) goto L22;
        int r52 = AndroidUtilities.dp(8.0f);
    L24:
        if (((r0 + r52) + r23) <= r6) goto L27;
        return true;
    L22:
        r52 = 0;
        goto L24
    L16:
        r5 = 0;
        goto L17
    L12:
        r42 = 0;
        goto L13
    L18:
        r23 = 0;
        goto L19
    }
}
