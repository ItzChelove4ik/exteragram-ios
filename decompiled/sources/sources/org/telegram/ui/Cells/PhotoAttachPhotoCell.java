package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.Components.spoilers.SpoilerEffect2;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public class PhotoAttachPhotoCell extends FrameLayout {
    private static Rect rect;
    private boolean allowLivePhotos;
    private AnimatorSet animator;
    private AnimatorSet animatorSet;
    private Paint backgroundPaint;
    private CheckBox2 checkBox;
    private FrameLayout checkFrame;
    private FrameLayout container;
    private Float crossfadeDuration;
    private PhotoAttachPhotoCellDelegate delegate;
    private ParentFastScrollDelegate fastScrollDelegate;
    private boolean hasSpoiler;
    private boolean highQuality;
    public BackupImageView imageView;
    private float imageViewCrossfadeProgress;
    private Bitmap imageViewCrossfadeSnapshot;
    private boolean isLast;
    private boolean isVertical;
    private int itemSize;
    private boolean itemSizeChanged;
    private SpannableString lock;
    private Path path;
    private MediaController.PhotoEntry photoEntry;
    private boolean pressed;
    private final Theme.ResourcesProvider resourcesProvider;
    private MediaController.SearchImage searchEntry;
    private SpoilerEffect spoilerEffect;
    private SpoilerEffect2 spoilerEffect2;
    private float spoilerMaxRadius;
    private float spoilerRevealProgress;
    private float spoilerRevealX;
    private float spoilerRevealY;
    private SpannableString star;
    private long stars;
    private boolean starsSelectedMultiple;
    private FrameLayout videoInfoContainer;
    private ImageView videoPlayImageView;
    private TextView videoTextView;
    private boolean zoomOnSelect;

    /* renamed from: org.telegram.ui.Cells.PhotoAttachPhotoCell$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ PhotoAttachPhotoCell this$0;

        public AnonymousClass1(PhotoAttachPhotoCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r6, View r7, long r8) {
            if (PhotoAttachPhotoCell.m6976$$Nest$fgetspoilerEffect2(this.this$0) == null) goto L27;
            if (r7 != this.this$0.imageView) goto L27;
            boolean r72 = super.drawChild(r6, r7, r8);
            if (PhotoAttachPhotoCell.m6970$$Nest$fgethasSpoiler(this.this$0) == true) goto L9;
        L25:
            return r72;
        L9:
            if (PhotoAttachPhotoCell.m6978$$Nest$fgetspoilerRevealProgress(this.this$0) == 1.0f) goto L25;
            if (PhotoAttachPhotoCell.m6974$$Nest$fgetphotoEntry(this.this$0) == null) goto L15;
            if (PhotoAttachPhotoCell.m6974$$Nest$fgetphotoEntry(this.this$0).isAttachSpoilerRevealed == true) goto L25;
        L15:
            if (PhotoAttachPhotoCell.m6978$$Nest$fgetspoilerRevealProgress(this.this$0) == 0.0f) goto L17;
            r6.save();
            PhotoAttachPhotoCell.m6973$$Nest$fgetpath(this.this$0).rewind();
            PhotoAttachPhotoCell.m6973$$Nest$fgetpath(this.this$0).addCircle(PhotoAttachPhotoCell.m6979$$Nest$fgetspoilerRevealX(this.this$0), PhotoAttachPhotoCell.m6980$$Nest$fgetspoilerRevealY(this.this$0), PhotoAttachPhotoCell.m6977$$Nest$fgetspoilerMaxRadius(this.this$0) * PhotoAttachPhotoCell.m6978$$Nest$fgetspoilerRevealProgress(this.this$0), Path.Direction.CW);
            r6.clipPath(PhotoAttachPhotoCell.m6973$$Nest$fgetpath(this.this$0), Region.Op.DIFFERENCE);
        L17:
            PhotoAttachPhotoCell.m6976$$Nest$fgetspoilerEffect2(this.this$0).draw(r6, PhotoAttachPhotoCell.m6968$$Nest$fgetcontainer(this.this$0), this.this$0.imageView.getMeasuredWidth(), this.this$0.imageView.getMeasuredHeight());
            if (PhotoAttachPhotoCell.m6974$$Nest$fgetphotoEntry(this.this$0) == null) goto L23;
            if (PhotoAttachPhotoCell.m6974$$Nest$fgetphotoEntry(this.this$0).starsAmount <= 0) goto L23;
            this.this$0.imageView.drawBlurredText(r6, 1.0f);
        L23:
            if (PhotoAttachPhotoCell.m6978$$Nest$fgetspoilerRevealProgress(this.this$0) == 0.0f) goto L25;
            r6.restore();
        L27:
            return super.drawChild(r6, r7, r8);
        }
    }

    /* renamed from: org.telegram.ui.Cells.PhotoAttachPhotoCell$2, reason: invalid class name */
    public class AnonymousClass2 extends BackupImageView {
        private Paint crossfadePaint;
        private long lastUpdate;
        private Drawable livePhotoIcon;
        private Drawable livePhotoIconOff;
        final /* synthetic */ PhotoAttachPhotoCell this$0;

        public AnonymousClass2(PhotoAttachPhotoCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.crossfadePaint = new Paint(1);
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r10) {
            AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
            if (r0 == null) goto L5;
            ImageReceiver r02 = r0.getImageReceiver();
        L6:
            if (r02 != null) goto L9;
            return;
        L9:
            if (this.width != (-1)) goto L11;
        L13:
            r02.setImageCoords(0.0f, 0.0f, getWidth(), getHeight());
            this.blurImageReceiver.setImageCoords(0.0f, 0.0f, getWidth(), getHeight());
        L14:
            r02.draw(r10);
            if (PhotoAttachPhotoCell.m6970$$Nest$fgethasSpoiler(this.this$0) == false) goto L35;
            if (PhotoAttachPhotoCell.m6978$$Nest$fgetspoilerRevealProgress(this.this$0) == 1.0f) goto L35;
            if (PhotoAttachPhotoCell.m6974$$Nest$fgetphotoEntry(this.this$0) == null) goto L23;
            if (PhotoAttachPhotoCell.m6974$$Nest$fgetphotoEntry(this.this$0).isAttachSpoilerRevealed == true) goto L35;
        L23:
            if (PhotoAttachPhotoCell.m6978$$Nest$fgetspoilerRevealProgress(this.this$0) == 0.0f) goto L25;
            r10.save();
            PhotoAttachPhotoCell.m6973$$Nest$fgetpath(this.this$0).rewind();
            PhotoAttachPhotoCell.m6973$$Nest$fgetpath(this.this$0).addCircle(PhotoAttachPhotoCell.m6979$$Nest$fgetspoilerRevealX(this.this$0), PhotoAttachPhotoCell.m6980$$Nest$fgetspoilerRevealY(this.this$0), PhotoAttachPhotoCell.m6977$$Nest$fgetspoilerMaxRadius(this.this$0) * PhotoAttachPhotoCell.m6978$$Nest$fgetspoilerRevealProgress(this.this$0), Path.Direction.CW);
            r10.clipPath(PhotoAttachPhotoCell.m6973$$Nest$fgetpath(this.this$0), Region.Op.DIFFERENCE);
        L25:
            this.blurImageReceiver.draw(r10);
            if (PhotoAttachPhotoCell.m6976$$Nest$fgetspoilerEffect2(this.this$0) == null) goto L28;
        L31:
            invalidate();
            if (PhotoAttachPhotoCell.m6978$$Nest$fgetspoilerRevealProgress(this.this$0) == 0.0f) goto L35;
            r10.restore();
            goto L35
        L28:
            if (PhotoAttachPhotoCell.m6975$$Nest$fgetspoilerEffect(this.this$0) != null) goto L30;
            PhotoAttachPhotoCell.m6986$$Nest$fputspoilerEffect(this.this$0, new SpoilerEffect());
            PhotoAttachPhotoCell.m6975$$Nest$fgetspoilerEffect(this.this$0).setColor(ColorUtils.setAlphaComponent(-1, (int) (Color.alpha(-1) * 0.325f)));
        L30:
            PhotoAttachPhotoCell.m6975$$Nest$fgetspoilerEffect(this.this$0).setBounds(0, 0, getWidth(), getHeight());
            PhotoAttachPhotoCell.m6975$$Nest$fgetspoilerEffect(this.this$0).draw(r10);
        L35:
            if (PhotoAttachPhotoCell.m6971$$Nest$fgetimageViewCrossfadeProgress(this.this$0) == 1.0f) goto L46;
            if (PhotoAttachPhotoCell.m6972$$Nest$fgetimageViewCrossfadeSnapshot(this.this$0) == null) goto L46;
            this.crossfadePaint.setAlpha((int) (CubicBezierInterpolator.DEFAULT.getInterpolation(1.0f - PhotoAttachPhotoCell.m6971$$Nest$fgetimageViewCrossfadeProgress(this.this$0)) * 255.0f));
            r10.drawBitmap(PhotoAttachPhotoCell.m6972$$Nest$fgetimageViewCrossfadeSnapshot(this.this$0), 0.0f, 0.0f, this.crossfadePaint);
            long r1 = Math.min(16, System.currentTimeMillis() - this.lastUpdate);
            if (PhotoAttachPhotoCell.m6969$$Nest$fgetcrossfadeDuration(this.this$0) != null) goto L41;
            float r3 = 250.0f;
        L42:
            PhotoAttachPhotoCell r5 = this.this$0;
            PhotoAttachPhotoCell.m6984$$Nest$fputimageViewCrossfadeProgress(r5, Math.min(1.0f, PhotoAttachPhotoCell.m6971$$Nest$fgetimageViewCrossfadeProgress(r5) + (r1 / r3)));
            this.lastUpdate = System.currentTimeMillis();
            invalidate();
            if (PhotoAttachPhotoCell.m6976$$Nest$fgetspoilerEffect2(this.this$0) == null) goto L51;
            PhotoAttachPhotoCell.m6968$$Nest$fgetcontainer(this.this$0).invalidate();
        L51:
            if (PhotoAttachPhotoCell.m6965$$Nest$fgetallowLivePhotos(this.this$0) == true) goto L53;
            return;
        L53:
            if (PhotoAttachPhotoCell.m6987$$Nest$misParentDoFastScroll(this.this$0) == false) goto L55;
            return;
        L55:
            if (PhotoAttachPhotoCell.m6974$$Nest$fgetphotoEntry(this.this$0) != null) goto L57;
            return;
        L57:
            if (PhotoAttachPhotoCell.m6974$$Nest$fgetphotoEntry(this.this$0).isLivePhotoAsync(new PhotoAttachPhotoCell$2$$ExternalSyntheticLambda0(this)) == true) goto L59;
            return;
        L59:
            if (PhotoAttachPhotoCell.m6974$$Nest$fgetphotoEntry(this.this$0).isUnalivePhoto() == false) goto L65;
            if (this.livePhotoIconOff != null) goto L63;
            this.livePhotoIconOff = getContext().getResources().getDrawable(R.drawable.media_live_off).mutate();
        L63:
            Drawable r9 = this.livePhotoIconOff;
        L68:
            r9.setBounds((int) (r02.getImageX() + AndroidUtilities.dp(8.0f)), (int) (r02.getImageY() + AndroidUtilities.dp(8.0f)), (int) (r02.getImageX() + AndroidUtilities.dp(30.0f)), (int) (r02.getImageY() + AndroidUtilities.dp(26.0f)));
            r9.draw(r10);
            return;
        L65:
            if (this.livePhotoIcon != null) goto L67;
            this.livePhotoIcon = getContext().getResources().getDrawable(R.drawable.media_live_on).mutate();
        L67:
            r9 = this.livePhotoIcon;
            goto L68
        L41:
            r3 = PhotoAttachPhotoCell.m6969$$Nest$fgetcrossfadeDuration(this.this$0).floatValue();
        L46:
            if (PhotoAttachPhotoCell.m6971$$Nest$fgetimageViewCrossfadeProgress(this.this$0) != 1.0f) goto L51;
            if (PhotoAttachPhotoCell.m6972$$Nest$fgetimageViewCrossfadeSnapshot(this.this$0) == null) goto L51;
            PhotoAttachPhotoCell.m6972$$Nest$fgetimageViewCrossfadeSnapshot(this.this$0).recycle();
            PhotoAttachPhotoCell.m6985$$Nest$fputimageViewCrossfadeSnapshot(this.this$0, null);
            PhotoAttachPhotoCell.m6983$$Nest$fputcrossfadeDuration(this.this$0, null);
            invalidate();
            goto L51
        L11:
            if (this.height == (-1)) goto L13;
            float r12 = (getWidth() - this.width) / 2;
            int r4 = getHeight();
            r02.setImageCoords(r12, (r4 - r5) / 2, this.width, this.height);
            ImageReceiver r13 = this.blurImageReceiver;
            float r42 = (getWidth() - this.width) / 2;
            int r52 = getHeight();
            r13.setImageCoords(r42, (r52 - r6) / 2, this.width, this.height);
            goto L14
        L5:
            r02 = this.imageReceiver;
            goto L6
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            PhotoAttachPhotoCell r12 = this.this$0;
            if (PhotoAttachPhotoCell.m6974$$Nest$fgetphotoEntry(r12) != null) goto L5;
        L7:
            boolean r0 = false;
        L8:
            PhotoAttachPhotoCell.m6988$$Nest$mupdateSpoilers2(r12, r0);
            return;
        L5:
            if (PhotoAttachPhotoCell.m6974$$Nest$fgetphotoEntry(this.this$0).hasSpoiler == false) goto L7;
            r0 = true;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Cells.PhotoAttachPhotoCell$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        private RectF rect;
        final /* synthetic */ PhotoAttachPhotoCell this$0;

        public AnonymousClass3(PhotoAttachPhotoCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new RectF();
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            this.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            r5.drawRoundRect(this.rect, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), Theme.chat_timeBackgroundPaint);
        }
    }

    /* renamed from: org.telegram.ui.Cells.PhotoAttachPhotoCell$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ PhotoAttachPhotoCell this$0;
        final /* synthetic */ boolean val$checked;

        public AnonymousClass5(PhotoAttachPhotoCell r1, boolean r2) {
            this.this$0 = r1;
            this.val$checked = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (PhotoAttachPhotoCell.m6966$$Nest$fgetanimator(this.this$0) != null) goto L5;
            return;
        L5:
            if (PhotoAttachPhotoCell.m6966$$Nest$fgetanimator(this.this$0).equals(r2) == false) goto L9;
            PhotoAttachPhotoCell.m6981$$Nest$fputanimator(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (PhotoAttachPhotoCell.m6966$$Nest$fgetanimator(this.this$0) != null) goto L5;
            return;
        L5:
            if (PhotoAttachPhotoCell.m6966$$Nest$fgetanimator(this.this$0).equals(r2) == false) goto L11;
            PhotoAttachPhotoCell.m6981$$Nest$fputanimator(this.this$0, null);
            if (this.val$checked == true) goto L12;
            this.this$0.setBackgroundColor(0);
            return;
        L12:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Cells.PhotoAttachPhotoCell$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ PhotoAttachPhotoCell this$0;

        public AnonymousClass6(PhotoAttachPhotoCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(PhotoAttachPhotoCell.m6967$$Nest$fgetanimatorSet(this.this$0)) == false) goto L6;
            PhotoAttachPhotoCell.m6982$$Nest$fputanimatorSet(this.this$0, null);
            return;
        }
    }

    public interface ParentFastScrollDelegate {
        boolean isInFastScroll();
    }

    public interface PhotoAttachPhotoCellDelegate {
        void onCheckClick(PhotoAttachPhotoCell r1);
    }

    public static /* synthetic */ void $r8$lambda$2HlgRxHsuaCNJ9cu8VmQI21HBlg(PhotoAttachPhotoCell r0, ValueAnimator r1) {
        r0.lambda$setChecked$1(r1);
    }

    /* renamed from: -$$Nest$fgetallowLivePhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6965$$Nest$fgetallowLivePhotos(PhotoAttachPhotoCell r0) {
        return r0.allowLivePhotos;
    }

    /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m6966$$Nest$fgetanimator(PhotoAttachPhotoCell r0) {
        return r0.animator;
    }

    /* renamed from: -$$Nest$fgetanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m6967$$Nest$fgetanimatorSet(PhotoAttachPhotoCell r0) {
        return r0.animatorSet;
    }

    /* renamed from: -$$Nest$fgetcontainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m6968$$Nest$fgetcontainer(PhotoAttachPhotoCell r0) {
        return r0.container;
    }

    /* renamed from: -$$Nest$fgetcrossfadeDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Float m6969$$Nest$fgetcrossfadeDuration(PhotoAttachPhotoCell r0) {
        return r0.crossfadeDuration;
    }

    /* renamed from: -$$Nest$fgethasSpoiler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6970$$Nest$fgethasSpoiler(PhotoAttachPhotoCell r0) {
        return r0.hasSpoiler;
    }

    /* renamed from: -$$Nest$fgetimageViewCrossfadeProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6971$$Nest$fgetimageViewCrossfadeProgress(PhotoAttachPhotoCell r0) {
        return r0.imageViewCrossfadeProgress;
    }

    /* renamed from: -$$Nest$fgetimageViewCrossfadeSnapshot, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m6972$$Nest$fgetimageViewCrossfadeSnapshot(PhotoAttachPhotoCell r0) {
        return r0.imageViewCrossfadeSnapshot;
    }

    /* renamed from: -$$Nest$fgetpath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m6973$$Nest$fgetpath(PhotoAttachPhotoCell r0) {
        return r0.path;
    }

    /* renamed from: -$$Nest$fgetphotoEntry, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaController.PhotoEntry m6974$$Nest$fgetphotoEntry(PhotoAttachPhotoCell r0) {
        return r0.photoEntry;
    }

    /* renamed from: -$$Nest$fgetspoilerEffect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpoilerEffect m6975$$Nest$fgetspoilerEffect(PhotoAttachPhotoCell r0) {
        return r0.spoilerEffect;
    }

    /* renamed from: -$$Nest$fgetspoilerEffect2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpoilerEffect2 m6976$$Nest$fgetspoilerEffect2(PhotoAttachPhotoCell r0) {
        return r0.spoilerEffect2;
    }

    /* renamed from: -$$Nest$fgetspoilerMaxRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6977$$Nest$fgetspoilerMaxRadius(PhotoAttachPhotoCell r0) {
        return r0.spoilerMaxRadius;
    }

    /* renamed from: -$$Nest$fgetspoilerRevealProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6978$$Nest$fgetspoilerRevealProgress(PhotoAttachPhotoCell r0) {
        return r0.spoilerRevealProgress;
    }

    /* renamed from: -$$Nest$fgetspoilerRevealX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6979$$Nest$fgetspoilerRevealX(PhotoAttachPhotoCell r0) {
        return r0.spoilerRevealX;
    }

    /* renamed from: -$$Nest$fgetspoilerRevealY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6980$$Nest$fgetspoilerRevealY(PhotoAttachPhotoCell r0) {
        return r0.spoilerRevealY;
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6981$$Nest$fputanimator(PhotoAttachPhotoCell r0, AnimatorSet r1) {
        r0.animator = r1;
    }

    /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6982$$Nest$fputanimatorSet(PhotoAttachPhotoCell r0, AnimatorSet r1) {
        r0.animatorSet = r1;
    }

    /* renamed from: -$$Nest$fputcrossfadeDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6983$$Nest$fputcrossfadeDuration(PhotoAttachPhotoCell r0, Float r1) {
        r0.crossfadeDuration = r1;
    }

    /* renamed from: -$$Nest$fputimageViewCrossfadeProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6984$$Nest$fputimageViewCrossfadeProgress(PhotoAttachPhotoCell r0, float r1) {
        r0.imageViewCrossfadeProgress = r1;
    }

    /* renamed from: -$$Nest$fputimageViewCrossfadeSnapshot, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6985$$Nest$fputimageViewCrossfadeSnapshot(PhotoAttachPhotoCell r0, Bitmap r1) {
        r0.imageViewCrossfadeSnapshot = r1;
    }

    /* renamed from: -$$Nest$fputspoilerEffect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6986$$Nest$fputspoilerEffect(PhotoAttachPhotoCell r0, SpoilerEffect r1) {
        r0.spoilerEffect = r1;
    }

    /* renamed from: -$$Nest$misParentDoFastScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6987$$Nest$misParentDoFastScroll(PhotoAttachPhotoCell r0) {
        return r0.isParentDoFastScroll();
    }

    /* renamed from: -$$Nest$mupdateSpoilers2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6988$$Nest$mupdateSpoilers2(PhotoAttachPhotoCell r0, boolean r1) {
        r0.updateSpoilers2(r1);
    }

    static {
        rect = new Rect();
    }

    public PhotoAttachPhotoCell(Context r18, Theme.ResourcesProvider r19) {
        super(r18);
        this.zoomOnSelect = true;
        this.backgroundPaint = new Paint();
        this.path = new Path();
        this.imageViewCrossfadeProgress = 1.0f;
        this.resourcesProvider = r19;
        setWillNotDraw(false);
        AnonymousClass1 r5 = new AnonymousClass1(this, r18);
        this.container = r5;
        addView(r5, LayoutHelper.createFrame(80, 80.0f));
        AnonymousClass2 r52 = new AnonymousClass2(this, r18);
        this.imageView = r52;
        r52.setBlurAllowed(true);
        int r9 = -1;
        this.container.addView(this.imageView, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass3 r53 = new AnonymousClass3(this, r18);
        this.videoInfoContainer = r53;
        r53.setWillNotDraw(false);
        this.videoInfoContainer.setPadding(AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(5.0f), 0);
        this.container.addView(this.videoInfoContainer, LayoutHelper.createFrame(-2, 17.0f, 83, 4.0f, 0.0f, 0.0f, 4.0f));
        ImageView r54 = new ImageView(r18);
        this.videoPlayImageView = r54;
        r54.setImageResource(R.drawable.play_mini_video);
        if (Theme.isCurrentThemeMonet() == false) goto L5;
        this.videoPlayImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_mediaTimeText), PorterDuff.Mode.MULTIPLY));
    L5:
        this.videoInfoContainer.addView(this.videoPlayImageView, LayoutHelper.createFrame(-2, -2, 19));
        TextView r55 = new TextView(r18);
        this.videoTextView = r55;
        if (Theme.isCurrentThemeMonet() == false) goto L8;
        r9 = Theme.getColor(Theme.key_chat_mediaTimeText);
    L8:
        r55.setTextColor(r9);
        this.videoTextView.setTypeface(AndroidUtilities.bold());
        this.videoTextView.setTextSize(1, 12.0f);
        this.videoTextView.setImportantForAccessibility(2);
        this.videoInfoContainer.addView(this.videoTextView, LayoutHelper.createFrame(-2, -2.0f, 19, 13.0f, -0.7f, 0.0f, 0.0f));
        CheckBox2 r56 = new CheckBox2(r18, 24, r19);
        this.checkBox = r56;
        r56.setDrawBackgroundAsArc(7);
        this.checkBox.setColor(Theme.key_chat_attachCheckBoxBackground, Theme.key_chat_attachPhotoBackground, Theme.key_chat_attachCheckBoxCheck);
        addView(this.checkBox, LayoutHelper.createFrame(26, 26.0f, 51, 52.0f, 4.0f, 0.0f, 0.0f));
        this.checkBox.setVisibility(0);
        setFocusable(true);
        FrameLayout r2 = new FrameLayout(r18);
        this.checkFrame = r2;
        addView(r2, LayoutHelper.createFrame(42, 42.0f, 51, 38.0f, 0.0f, 0.0f, 0.0f));
        this.itemSize = AndroidUtilities.dp(80.0f);
    }

    private boolean isParentDoFastScroll() {
        ParentFastScrollDelegate r0 = this.fastScrollDelegate;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isInFastScroll() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$setChecked$1(ValueAnimator r1) {
        this.imageView.setRoundRadius(((Integer) r1.getAnimatedValue()).intValue());
    }

    private void updateSpoilers2(boolean r2) {
        if (this.container == null) goto L22;
        BackupImageView r0 = this.imageView;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.getMeasuredHeight() > 0) goto L9;
        return;
    L9:
        if (this.imageView.getMeasuredWidth() <= 0) goto L27;
        if (r2 == true) goto L13;
    L18:
        SpoilerEffect2 r22 = this.spoilerEffect2;
        if (r22 == null) goto L26;
        r22.detach(this);
        this.spoilerEffect2 = null;
        return;
    L26:
        return;
    L13:
        if (SpoilerEffect2.supports() == false) goto L18;
        if (this.spoilerEffect2 != null) goto L25;
        this.spoilerEffect2 = SpoilerEffect2.getInstance(this);
        return;
    L25:
        return;
    L27:
        return;
    }

    private void updateVideoInfo() {
        MediaController.PhotoEntry r0 = this.photoEntry;
        if (r0 != null) goto L7;
        this.videoPlayImageView.setVisibility(8);
        this.videoInfoContainer.setVisibility(4);
        return;
    L7:
        if (r0.isVideo == false) goto L13;
        if (r0.isLivePhoto() == true) goto L13;
        this.imageView.setOrientation(0, true);
        this.videoInfoContainer.setVisibility(0);
        this.videoPlayImageView.setVisibility(0);
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) this.videoTextView.getLayoutParams();
        r02.leftMargin = AndroidUtilities.dp(13.0f);
        this.videoTextView.setText(AndroidUtilities.formatShortDuration(this.photoEntry.duration));
        return;
    L13:
        if (this.photoEntry.isHighQuality() == false) goto L19;
        if (ExteraConfig.getAlwaysSendInHD() == true) goto L19;
        this.videoInfoContainer.setVisibility(0);
        this.videoPlayImageView.setVisibility(8);
        FrameLayout.LayoutParams r03 = (FrameLayout.LayoutParams) this.videoTextView.getLayoutParams();
        r03.leftMargin = AndroidUtilities.dp(0.0f);
        this.videoTextView.setText(LocaleController.getString(R.string.ShortHighQuality));
        return;
    L19:
        if (this.photoEntry.isHighQuality() == false) goto L21;
    L24:
        this.videoPlayImageView.setVisibility(8);
        this.videoInfoContainer.setVisibility(4);
        return;
    L21:
        if (ExteraConfig.getAlwaysSendInHD() == false) goto L24;
        this.videoInfoContainer.setVisibility(0);
        this.videoPlayImageView.setVisibility(8);
        FrameLayout.LayoutParams r04 = (FrameLayout.LayoutParams) this.videoTextView.getLayoutParams();
        r04.leftMargin = AndroidUtilities.dp(0.0f);
        this.videoTextView.setText(LocaleController.getString(R.string.ShortStandardQuality));
    }

    public void callDelegate() {
        this.delegate.onCheckClick(this);
    }

    @Override // android.view.View
    public void clearAnimation() {
        super.clearAnimation();
        AnimatorSet r0 = this.animator;
        if (r0 == null) goto L17;
        r0.cancel();
        this.animator = null;
        FrameLayout r02 = this.container;
        float r2 = 1.0f;
        if (this.checkBox.isChecked() == false) goto L7;
        float r1 = 0.787f;
    L8:
        r02.setScaleX(r1);
        FrameLayout r03 = this.container;
        if (this.checkBox.isChecked() == false) goto L11;
        r2 = 0.787f;
    L11:
        r03.setScaleY(r2);
        BackupImageView r04 = this.imageView;
        if (this.checkBox.isChecked() == false) goto L14;
        int r4 = AndroidUtilities.dp(12.0f);
    L15:
        r04.setRoundRadius(r4);
        return;
    L14:
        r4 = 0;
        goto L15
    L7:
        r1 = 1.0f;
        goto L8
    }

    public CheckBox2 getCheckBox() {
        return this.checkBox;
    }

    public FrameLayout getCheckFrame() {
        return this.checkFrame;
    }

    public BackupImageView getImageView() {
        return this.imageView;
    }

    public MediaController.PhotoEntry getPhotoEntry() {
        return this.photoEntry;
    }

    public float getScale() {
        return this.container.getScaleX();
    }

    public int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public View getVideoInfoContainer() {
        return this.videoInfoContainer;
    }

    public boolean isChecked() {
        return this.checkBox.isChecked();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        SpoilerEffect2 r0 = this.spoilerEffect2;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.destroyed == false) goto L8;
        this.spoilerEffect2 = SpoilerEffect2.getInstance(this);
        return;
    L8:
        r0.attach(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SpoilerEffect2 r0 = this.spoilerEffect2;
        if (r0 == null) goto L5;
        r0.detach(this);
    L5:
        MediaController.PhotoEntry r1 = this.photoEntry;
        if (r1 == null) goto L9;
        r1.cancelLivePhotoParse();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        if (this.checkBox.isChecked() == false) goto L5;
    L20:
        this.backgroundPaint.setColor(getThemedColor(Theme.key_chat_attachPhotoBackground));
        r8.drawRect(0.0f, 0.0f, this.imageView.getMeasuredWidth(), this.imageView.getMeasuredHeight(), this.backgroundPaint);
        return;
    L5:
        if (this.container.getScaleX() != 1.0f) goto L20;
        if (this.imageView.getImageReceiver().hasNotThumb() == false) goto L20;
        if (this.imageView.getImageReceiver().getCurrentAlpha() != 1.0f) goto L20;
        MediaController.PhotoEntry r0 = this.photoEntry;
        if (r0 != null) goto L13;
    L14:
        MediaController.SearchImage r02 = this.searchEntry;
        if (r02 != null) goto L17;
        return;
    L17:
        if (PhotoViewer.isShowingImage(r02.getPathToAttach()) == true) goto L20;
        return;
    L13:
        if (PhotoViewer.isShowingImage(r0.path) == true) goto L20;
        goto L14
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r8) {
        super.onInitializeAccessibilityNodeInfo(r8);
        r8.setEnabled(true);
        StringBuilder r1 = new StringBuilder();
        MediaController.PhotoEntry r2 = this.photoEntry;
        if (r2 == null) goto L7;
        BackupImageView r3 = this.imageView;
        Objects.requireNonNull(r3);
        if (r2.isLivePhotoAsync(new PhotoAttachPhotoCell$$ExternalSyntheticLambda1(r3)) == false) goto L7;
        r1.append(LocaleController.getString(R.string.AttachLivePhoto));
    L14:
        if (this.photoEntry == null) goto L16;
        r1.append(". ");
        r1.append(LocaleController.getInstance().getFormatterStats().format(this.photoEntry.dateTaken * 1000));
    L16:
        r8.setText(r1);
        if (this.checkBox.isChecked() == false) goto L19;
        r8.setSelected(true);
    L19:
        r8.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.acc_action_open_photo, LocaleController.getString(R.string.Open)));
        return;
    L7:
        MediaController.PhotoEntry r22 = this.photoEntry;
        if (r22 != null) goto L10;
    L12:
        r1.append(LocaleController.getString(R.string.AttachPhoto));
        goto L14
    L10:
        if (r22.isVideo == false) goto L12;
        r1.append(LocaleController.getString(R.string.AttachVideo) + ", " + LocaleController.formatDuration(this.photoEntry.duration));
        goto L14
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        if (this.itemSizeChanged == false) goto L6;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.itemSize, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.itemSize + AndroidUtilities.dp(2.0f), TLObject.FLAG_30));
        return;
    L6:
        int r0 = 6;
        if (this.isVertical == false) goto L14;
        int r42 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30);
        if (this.isLast == false) goto L11;
        r0 = 0;
    L11:
        super.onMeasure(r42, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r0 + 80), TLObject.FLAG_30));
        return;
    L14:
        if (this.isLast == false) goto L16;
        r0 = 0;
    L16:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r0 + 80), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r6) {
        this.checkFrame.getHitRect(rect);
        boolean r1 = true;
        if (r6.getAction() != 0) goto L8;
        if (rect.contains((int) r6.getX(), (int) r6.getY()) == false) goto L20;
        this.pressed = true;
        invalidate();
    L21:
        if (r1 == false) goto L23;
        return r1;
    L23:
        return super.onTouchEvent(r6);
    L20:
        r1 = false;
        goto L21
    L8:
        if (this.pressed == false) goto L20;
        if (r6.getAction() != 1) goto L13;
        getParent().requestDisallowInterceptTouchEvent(true);
        this.pressed = false;
        playSoundEffect(0);
        sendAccessibilityEvent(1);
        this.delegate.onCheckClick(this);
        invalidate();
        goto L20
    L13:
        if (r6.getAction() != 3) goto L16;
        this.pressed = false;
        invalidate();
        goto L20
    L16:
        if (r6.getAction() != 2) goto L20;
        if (rect.contains((int) r6.getX(), (int) r6.getY()) == true) goto L20;
        this.pressed = false;
        invalidate();
        goto L20
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int r11, Bundle r12) {
        if (r11 != R.id.acc_action_open_photo) goto L6;
        View r0 = (View) getParent();
        r0.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, getLeft(), (getTop() + getHeight()) - 1, 0));
        r0.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, getLeft(), (getTop() + getHeight()) - 1, 0));
    L6:
        return super.performAccessibilityAction(r11, r12);
    }

    public void setChecked(int r8, boolean r9, boolean r10) {
        this.checkBox.setChecked(r8, r9, r10);
        boolean r0 = true;
        if (this.itemSizeChanged == false) goto L33;
        AnimatorSet r82 = this.animator;
        if (r82 == null) goto L7;
        r82.cancel();
        this.animator = null;
    L7:
        float r2 = 1.0f;
        if (r10 == false) goto L21;
        int r102 = this.imageView.getRoundRadius()[0];
        if (r9 == false) goto L12;
        int r83 = AndroidUtilities.dp(12.0f);
    L13:
        ValueAnimator r84 = ValueAnimator.ofInt(new int[]{r102, r83});
        r84.addUpdateListener(new PhotoAttachPhotoCell$$ExternalSyntheticLambda0(this));
        AnimatorSet r103 = new AnimatorSet();
        this.animator = r103;
        FrameLayout r4 = this.container;
        if (r9 == false) goto L16;
        float r5 = 0.787f;
    L17:
        ObjectAnimator r42 = ObjectAnimator.ofFloat(r4, View.SCALE_X, new float[]{r5});
        FrameLayout r52 = this.container;
        if (r9 == false) goto L20;
        r2 = 0.787f;
    L20:
        r103.playTogether(new Animator[]{r42, ObjectAnimator.ofFloat(r52, View.SCALE_Y, new float[]{r2}), r84});
        this.animator.setDuration(200);
        this.animator.addListener(new AnonymousClass5(this, r9));
        this.animator.start();
        goto L33
    L16:
        r5 = 1.0f;
        goto L17
    L12:
        r83 = 0;
        goto L13
    L21:
        FrameLayout r104 = this.container;
        if (r9 == false) goto L24;
        float r43 = 0.787f;
    L25:
        r104.setScaleX(r43);
        FrameLayout r105 = this.container;
        if (r9 == false) goto L28;
        r2 = 0.787f;
    L28:
        r105.setScaleY(r2);
        BackupImageView r106 = this.imageView;
        if (r9 == false) goto L31;
        int r85 = AndroidUtilities.dp(12.0f);
    L32:
        r106.setRoundRadius(r85);
        goto L33
    L31:
        r85 = 0;
        goto L32
    L24:
        r43 = 1.0f;
    L33:
        MediaController.PhotoEntry r86 = this.photoEntry;
        if (r86 != null) goto L36;
    L40:
        r0 = false;
    L41:
        setHighQuality(r0);
        return;
    L36:
        if (r86.isHighQuality() == false) goto L40;
        if (isChecked() == false) goto L40;
        goto L40
    }

    public void setDelegate(PhotoAttachPhotoCellDelegate r1) {
        this.delegate = r1;
    }

    public void setFastScrollDelegate(ParentFastScrollDelegate r1) {
        this.fastScrollDelegate = r1;
    }

    public void setHasSpoiler(boolean r2) {
        setHasSpoiler(r2, null);
    }

    public void setHasSpoiler(boolean r4, Float r5) {
        if (this.hasSpoiler == r4) goto L17;
        this.spoilerRevealProgress = 0.0f;
        boolean r1 = isLaidOut();
        Bitmap r2 = this.imageViewCrossfadeSnapshot;
        if (r1 == false) goto L10;
        this.imageViewCrossfadeSnapshot = AndroidUtilities.snapshotView(this.imageView);
        if (r2 == null) goto L9;
        r2.recycle();
    L9:
        this.imageViewCrossfadeProgress = 0.0f;
    L13:
        this.hasSpoiler = r4;
        this.crossfadeDuration = r5;
        this.imageView.setHasBlur(r4);
        this.imageView.invalidate();
        if (r4 == false) goto L18;
        updateSpoilers2(r4);
        return;
    L18:
        return;
    L10:
        if (r2 == null) goto L12;
        r2.recycle();
        this.imageViewCrossfadeSnapshot = null;
    L12:
        this.imageViewCrossfadeProgress = 1.0f;
        goto L13
    }

    public void setHighQuality(boolean r2) {
        if (r2 == true) goto L4;
    L6:
        boolean r22 = false;
    L8:
        if (this.highQuality == r22) goto L10;
        this.highQuality = r22;
    L10:
        updateVideoInfo();
        return;
    L4:
        if (isChecked() == false) goto L6;
        r22 = true;
        goto L8
    }

    public void setIsVertical(boolean r1) {
        this.isVertical = r1;
    }

    public void setItemSize(int r3) {
        this.itemSize = r3;
        FrameLayout.LayoutParams r32 = (FrameLayout.LayoutParams) this.container.getLayoutParams();
        int r0 = this.itemSize;
        r32.height = r0;
        r32.width = r0;
        FrameLayout.LayoutParams r33 = (FrameLayout.LayoutParams) this.checkFrame.getLayoutParams();
        r33.gravity = 53;
        r33.leftMargin = 0;
        FrameLayout.LayoutParams r34 = (FrameLayout.LayoutParams) this.checkBox.getLayoutParams();
        r34.gravity = 53;
        r34.leftMargin = 0;
        int r02 = AndroidUtilities.dp(5.0f);
        r34.topMargin = r02;
        r34.rightMargin = r02;
        this.checkBox.setDrawBackgroundAsArc(6);
        this.itemSizeChanged = true;
    }

    public void setNum(int r1) {
        this.checkBox.setNum(r1);
    }

    public void setOnCheckClickListener(View.OnClickListener r1) {
        this.checkFrame.setOnClickListener(r1);
    }

    public void setPhotoEntry(MediaController.PhotoEntry r6, boolean r7, boolean r8, boolean r9, boolean r10) {
        boolean r0 = false;
        this.pressed = false;
        MediaController.PhotoEntry r1 = this.photoEntry;
        if (r1 == null) goto L6;
        if (r1 == r6) goto L6;
        r1.cancelLivePhotoParse();
    L6:
        this.photoEntry = r6;
        this.isLast = r9;
        this.allowLivePhotos = r10;
        String r92 = r6.coverPath;
        if (r92 == null) goto L9;
        this.imageView.setImage(r92, null, Theme.chat_attachEmptyDrawable);
    L21:
        if (r8 == true) goto L23;
    L25:
        this.imageView.getImageReceiver().setVisible(!r0, true);
        CheckBox2 r82 = this.checkBox;
        float r93 = 1.0f;
        if (r0 == false) goto L28;
        float r12 = 0.0f;
    L29:
        r82.setAlpha(r12);
        FrameLayout r83 = this.videoInfoContainer;
        if (r0 == false) goto L32;
        r93 = 0.0f;
    L32:
        r83.setAlpha(r93);
        requestLayout();
        setHasSpoiler(r6.hasSpoiler);
        setHighQuality(r6.isHighQuality());
        setStarsPrice(r6.starsAmount, r7);
        return;
    L28:
        r12 = 1.0f;
        goto L29
    L23:
        if (PhotoViewer.isShowingImage(this.photoEntry.path) == false) goto L25;
        r0 = true;
        goto L25
    L9:
        String r94 = r6.thumbPath;
        if (r94 == null) goto L13;
        this.imageView.setImage(r94, null, Theme.chat_attachEmptyDrawable);
        goto L21
    L13:
        if (r6.path != null) goto L15;
        this.imageView.setImageDrawable(Theme.chat_attachEmptyDrawable);
        goto L21
    L15:
        if (r6.isVideo == true) goto L17;
    L19:
        BackupImageView r95 = this.imageView;
        MediaController.PhotoEntry r3 = this.photoEntry;
        r95.setOrientation(r3.orientation, r3.invert, true);
        this.imageView.setImage("thumb://" + this.photoEntry.imageId + ":" + this.photoEntry.path, null, Theme.chat_attachEmptyDrawable);
        goto L21
    L17:
        if (r6.isLivePhoto() == true) goto L19;
        this.imageView.setImage("vthumb://" + this.photoEntry.imageId + ":" + this.photoEntry.path, null, Theme.chat_attachEmptyDrawable);
        goto L21
    }

    public void setPhotoEntry(MediaController.SearchImage r13, boolean r14, boolean r15) {
        this.pressed = false;
        this.searchEntry = r13;
        this.isLast = r15;
        if (this.zoomOnSelect == false) goto L5;
        Drawable r0 = Theme.chat_attachEmptyDrawable;
    L6:
        TLRPC.PhotoSize r1 = r13.thumbPhotoSize;
        if (r1 == null) goto L9;
        this.imageView.setImage(ImageLocation.getForPhoto(r1, r13.photo), null, r0, r13);
    L30:
        if (r14 == true) goto L32;
    L34:
        boolean r02 = false;
    L35:
        this.imageView.getImageReceiver().setVisible(!r02, true);
        CheckBox2 r12 = this.checkBox;
        float r2 = 1.0f;
        if (r02 == false) goto L38;
        float r4 = 0.0f;
    L39:
        r12.setAlpha(r4);
        FrameLayout r16 = this.videoInfoContainer;
        if (r02 == false) goto L42;
        r2 = 0.0f;
    L42:
        r16.setAlpha(r2);
        requestLayout();
        setHasSpoiler(false);
        setHighQuality(false);
        setStarsPrice(0, false);
        return;
    L38:
        r4 = 1.0f;
        goto L39
    L32:
        if (PhotoViewer.isShowingImage(r13.getPathToAttach()) == false) goto L34;
        r02 = true;
        goto L35
    L9:
        TLRPC.PhotoSize r17 = r13.photoSize;
        if (r17 == null) goto L12;
        this.imageView.setImage(ImageLocation.getForPhoto(r17, r13.photo), "80_80", r0, r13);
        goto L30
    L12:
        String r18 = r13.thumbPath;
        if (r18 == null) goto L16;
        this.imageView.setImage(r18, null, r0);
        goto L30
    L16:
        if (TextUtils.isEmpty(r13.thumbUrl) == true) goto L23;
        ImageLocation r19 = ImageLocation.getForPath(r13.thumbUrl);
        if (r13.type == 1) goto L20;
    L22:
        this.imageView.setImage(r19, null, r0, r13);
        goto L30
    L20:
        if (r13.thumbUrl.endsWith("mp4") == false) goto L22;
        r19.imageType = 2;
        goto L22
    L23:
        TLRPC.Document r110 = r13.document;
        if (r110 == null) goto L29;
        MessageObject.getDocumentVideoThumb(r110);
        TLRPC.VideoSize r111 = MessageObject.getDocumentVideoThumb(r13.document);
        TLRPC.Document r3 = r13.document;
        if (r111 == null) goto L28;
        this.imageView.setImage(ImageLocation.getForDocument(r111, r13.document), null, ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r3.thumbs, 90), r13.document), "52_52", null, -1, 1, r13);
        goto L30
    L28:
        this.imageView.setImage(ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r3.thumbs, 320), r13.document), null, r0, r13);
        goto L30
    L29:
        this.imageView.setImageDrawable(r0);
        goto L30
    L5:
        r0 = getResources().getDrawable(R.drawable.nophotos);
        goto L6
    }

    public void setStarsPrice(long r7, boolean r9) {
        if (r9 == this.starsSelectedMultiple) goto L5;
    L8:
        this.stars = r7;
        this.starsSelectedMultiple = r9;
        if (r7 <= 0) goto L20;
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        if (this.star != null) goto L13;
        this.star = new SpannableString("⭐");
        ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.star_small_inner);
        r1.setScale(0.7f, 0.7f);
        SpannableString r4 = this.star;
        r4.setSpan(r1, 0, r4.length(), 33);
    L13:
        r0.append(this.star);
        r0.append("\u2009");
        if (r9 == true) goto L16;
        r0.append(Long.toString(r7));
    L21:
        this.imageView.setBlurredText(r0);
        this.imageView.invalidate();
        this.container.invalidate();
        return;
    L16:
        if (this.lock != null) goto L18;
        this.lock = new SpannableString("l");
        ColoredImageSpan r72 = new ColoredImageSpan(R.drawable.msg_mini_lock2);
        SpannableString r8 = this.lock;
        r8.setSpan(r72, 0, r8.length(), 33);
    L18:
        r0.append(this.lock);
        goto L21
    L20:
        r0 = null;
        goto L21
    L5:
        if (r7 != this.stars) goto L8;
    }

    public void showCheck(boolean r9) {
        float r0 = 1.0f;
        if (r9 == false) goto L7;
        if (this.checkBox.getAlpha() != 1.0f) goto L7;
        return;
    L7:
        if (r9 == false) goto L9;
    L11:
        AnimatorSet r2 = this.animatorSet;
        if (r2 == null) goto L14;
        r2.cancel();
        this.animatorSet = null;
    L14:
        AnimatorSet r22 = new AnimatorSet();
        this.animatorSet = r22;
        r22.setInterpolator(new DecelerateInterpolator());
        this.animatorSet.setDuration(180);
        AnimatorSet r23 = this.animatorSet;
        FrameLayout r3 = this.videoInfoContainer;
        if (r9 == false) goto L17;
        float r4 = 1.0f;
    L18:
        float[] r6 = {r4};
        Property r42 = View.ALPHA;
        ObjectAnimator r32 = ObjectAnimator.ofFloat(r3, r42, r6);
        CheckBox2 r62 = this.checkBox;
        if (r9 == true) goto L22;
        r0 = 0.0f;
    L22:
        r23.playTogether(new Animator[]{r32, ObjectAnimator.ofFloat(r62, r42, new float[]{r0})});
        this.animatorSet.addListener(new AnonymousClass6(this));
        this.animatorSet.start();
        return;
    L17:
        r4 = 0.0f;
        goto L18
    L9:
        if (this.checkBox.getAlpha() != 0.0f) goto L11;
    }

    public void showImage() {
        this.imageView.getImageReceiver().setVisible(true, true);
    }
}
