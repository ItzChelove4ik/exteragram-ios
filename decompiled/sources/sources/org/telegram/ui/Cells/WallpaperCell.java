package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.io.File;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.WallpapersListActivity;

/* loaded from: classes6.dex */
public abstract class WallpaperCell extends FrameLayout {
    private Paint backgroundPaint;
    private Drawable checkDrawable;
    private Paint circlePaint;
    private int currentType;
    public boolean drawStubBackground;
    private Paint framePaint;
    private boolean isBottom;
    private boolean isTop;
    int size;
    private int spanCount;
    private WallpaperView[] wallpaperViews;

    public class WallpaperView extends FrameLayout {
        private AnimatorSet animator;
        private CheckBox checkBox;
        private Object currentWallpaper;
        private BackupImageView imageView;
        private ImageView imageView2;
        private boolean isSelected;
        private View selector;
        final /* synthetic */ WallpaperCell this$0;

        /* renamed from: org.telegram.ui.Cells.WallpaperCell$WallpaperView$1, reason: invalid class name */
        public class AnonymousClass1 extends BackupImageView {
            final /* synthetic */ WallpaperView this$1;
            final /* synthetic */ WallpaperCell val$this$0;

            public AnonymousClass1(WallpaperView r1, Context r2, WallpaperCell r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Components.BackupImageView, android.view.View
            public void onDraw(Canvas r14) {
                super.onDraw(r14);
                if ((WallpaperView.m7130$$Nest$fgetcurrentWallpaper(this.this$1) instanceof WallpapersListActivity.ColorWallpaper) == false) goto L5;
            L8:
                r14.drawLine(1.0f, 0.0f, getMeasuredWidth() - 1, 0.0f, WallpaperCell.m7128$$Nest$fgetframePaint(this.this$1.this$0));
                Canvas r7 = r14;
                r7.drawLine(0.0f, 0.0f, 0.0f, getMeasuredHeight(), WallpaperCell.m7128$$Nest$fgetframePaint(this.this$1.this$0));
                r7.drawLine(getMeasuredWidth() - 1, 0.0f, getMeasuredWidth() - 1, getMeasuredHeight(), WallpaperCell.m7128$$Nest$fgetframePaint(this.this$1.this$0));
                r7.drawLine(1.0f, getMeasuredHeight() - 1, getMeasuredWidth() - 1, getMeasuredHeight() - 1, WallpaperCell.m7128$$Nest$fgetframePaint(this.this$1.this$0));
            L10:
                if (WallpaperView.m7131$$Nest$fgetisSelected(this.this$1) == false) goto L13;
                WallpaperCell.m7127$$Nest$fgetcirclePaint(this.this$1.this$0).setColor(Theme.serviceMessageColorBackup);
                int r142 = getMeasuredWidth() / 2;
                int r0 = getMeasuredHeight() / 2;
                r7.drawCircle(r142, r0, AndroidUtilities.dp(20.0f), WallpaperCell.m7127$$Nest$fgetcirclePaint(this.this$1.this$0));
                WallpaperCell.m7126$$Nest$fgetcheckDrawable(this.this$1.this$0).setBounds(r142 - (WallpaperCell.m7126$$Nest$fgetcheckDrawable(this.this$1.this$0).getIntrinsicWidth() / 2), r0 - (WallpaperCell.m7126$$Nest$fgetcheckDrawable(this.this$1.this$0).getIntrinsicHeight() / 2), r142 + (WallpaperCell.m7126$$Nest$fgetcheckDrawable(this.this$1.this$0).getIntrinsicWidth() / 2), r0 + (WallpaperCell.m7126$$Nest$fgetcheckDrawable(this.this$1.this$0).getIntrinsicHeight() / 2));
                WallpaperCell.m7126$$Nest$fgetcheckDrawable(this.this$1.this$0).draw(r7);
                return;
            L13:
                return;
            L5:
                if ((WallpaperView.m7130$$Nest$fgetcurrentWallpaper(this.this$1) instanceof WallpapersListActivity.FileWallpaper) == true) goto L8;
                r7 = r14;
                goto L10
            }
        }

        /* renamed from: org.telegram.ui.Cells.WallpaperCell$WallpaperView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ WallpaperView this$1;
            final /* synthetic */ boolean val$checked;

            public AnonymousClass2(WallpaperView r1, boolean r2) {
                this.this$1 = r1;
                this.val$checked = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator r2) {
                if (WallpaperView.m7129$$Nest$fgetanimator(this.this$1) != null) goto L5;
                return;
            L5:
                if (WallpaperView.m7129$$Nest$fgetanimator(this.this$1).equals(r2) == false) goto L9;
                WallpaperView.m7132$$Nest$fputanimator(this.this$1, null);
                return;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (WallpaperView.m7129$$Nest$fgetanimator(this.this$1) != null) goto L5;
                return;
            L5:
                if (WallpaperView.m7129$$Nest$fgetanimator(this.this$1).equals(r2) == false) goto L11;
                WallpaperView.m7132$$Nest$fputanimator(this.this$1, null);
                if (this.val$checked == true) goto L12;
                this.this$1.setBackgroundColor(0);
                return;
            L12:
                return;
            }
        }

        /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatorSet m7129$$Nest$fgetanimator(WallpaperView r0) {
            return r0.animator;
        }

        /* renamed from: -$$Nest$fgetcurrentWallpaper, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Object m7130$$Nest$fgetcurrentWallpaper(WallpaperView r0) {
            return r0.currentWallpaper;
        }

        /* renamed from: -$$Nest$fgetisSelected, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m7131$$Nest$fgetisSelected(WallpaperView r0) {
            return r0.isSelected;
        }

        /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7132$$Nest$fputanimator(WallpaperView r0, AnimatorSet r1) {
            r0.animator = r1;
        }

        public WallpaperView(WallpaperCell r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            setWillNotDraw(false);
            AnonymousClass1 r1 = new AnonymousClass1(this, r9, r8);
            this.imageView = r1;
            addView(r1, LayoutHelper.createFrame(-1, -1, 51));
            ImageView r12 = new ImageView(r9);
            this.imageView2 = r12;
            r12.setImageResource(R.drawable.ic_gallery_background);
            this.imageView2.setScaleType(ImageView.ScaleType.CENTER);
            addView(this.imageView2, LayoutHelper.createFrame(-1, -1, 51));
            View r13 = new View(r9);
            this.selector = r13;
            r13.setBackgroundDrawable(Theme.getSelectorDrawable(false));
            addView(this.selector, LayoutHelper.createFrame(-1, -1.0f));
            CheckBox r82 = new CheckBox(r9, R.drawable.round_check2);
            this.checkBox = r82;
            r82.setVisibility(4);
            this.checkBox.setColor(Theme.getColor(Theme.key_checkbox), Theme.getColor(Theme.key_checkboxCheck));
            addView(this.checkBox, LayoutHelper.createFrame(22, 22.0f, 53, 0.0f, 2.0f, 2.0f, 0.0f));
        }

        @Override // android.view.View
        public void clearAnimation() {
            super.clearAnimation();
            AnimatorSet r0 = this.animator;
            if (r0 == null) goto L6;
            r0.cancel();
            this.animator = null;
            return;
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.imageView.invalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            if (this.this$0.drawStubBackground == false) goto L7;
            if (this.checkBox.isChecked() == false) goto L7;
        L12:
            r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), WallpaperCell.m7125$$Nest$fgetbackgroundPaint(this.this$0));
            return;
        L7:
            if (this.imageView.getImageReceiver().hasBitmapImage() == false) goto L12;
            if (this.imageView.getImageReceiver().getCurrentAlpha() != 1.0f) goto L12;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r4) {
            this.selector.drawableHotspotChanged(r4.getX(), r4.getY());
            return super.onTouchEvent(r4);
        }

        public void setChecked(boolean r8, boolean r9) {
            if (this.checkBox.getVisibility() == 0) goto L5;
            this.checkBox.setVisibility(0);
        L5:
            this.checkBox.setChecked(r8, r9);
            AnimatorSet r0 = this.animator;
            if (r0 == null) goto L8;
            r0.cancel();
            this.animator = null;
        L8:
            float r02 = 1.0f;
            if (r9 == false) goto L18;
            AnimatorSet r92 = new AnimatorSet();
            this.animator = r92;
            BackupImageView r3 = this.imageView;
            if (r8 == false) goto L13;
            float r4 = 0.8875f;
        L14:
            ObjectAnimator r32 = ObjectAnimator.ofFloat(r3, "scaleX", new float[]{r4});
            BackupImageView r42 = this.imageView;
            if (r8 == false) goto L17;
            r02 = 0.8875f;
        L17:
            r92.playTogether(new Animator[]{r32, ObjectAnimator.ofFloat(r42, "scaleY", new float[]{r02})});
            this.animator.setDuration(200);
            this.animator.addListener(new AnonymousClass2(this, r8));
            this.animator.start();
        L26:
            invalidate();
            return;
        L13:
            r4 = 1.0f;
            goto L14
        L18:
            BackupImageView r93 = this.imageView;
            if (r8 == false) goto L21;
            float r1 = 0.8875f;
        L22:
            r93.setScaleX(r1);
            BackupImageView r94 = this.imageView;
            if (r8 == false) goto L25;
            r02 = 0.8875f;
        L25:
            r94.setScaleY(r02);
            goto L26
        L21:
            r1 = 1.0f;
            goto L22
        }

        public void setWallpaper(Object r24, Object r25, Drawable r26, boolean r27) {
            this.currentWallpaper = r24;
            int r3 = 0;
            this.imageView.setVisibility(0);
            this.imageView2.setVisibility(4);
            TLRPC.PhotoSize r4 = null;
            this.imageView.setBackgroundDrawable(null);
            this.imageView.getImageReceiver().setColorFilter(null);
            this.imageView.getImageReceiver().setAlpha(1.0f);
            this.imageView.getImageReceiver().setBlendMode(null);
            this.imageView.getImageReceiver().setGradientBitmap(null);
            if (r24 != r25) goto L5;
            boolean r5 = true;
        L6:
            this.isSelected = r5;
            if ((r24 instanceof TLRPC.TL_wallPaper) == false) goto L49;
            TLRPC.TL_wallPaper r1 = (TLRPC.TL_wallPaper) r24;
            TLRPC.PhotoSize r2 = FileLoader.getClosestPhotoSizeWithSize(r1.document.thumbs, AndroidUtilities.dp(100.0f));
            TLRPC.PhotoSize r32 = FileLoader.getClosestPhotoSizeWithSize(r1.document.thumbs, AndroidUtilities.dp(180.0f));
            if (r32 == r2) goto L12;
            r4 = r32;
        L12:
            if (r4 == null) goto L15;
            long r52 = r4.size;
        L14:
            long r15 = r52;
            if (r1.pattern == false) goto L42;
            TLRPC.WallPaperSettings r33 = r1.settings;
            if (r33.third_background_color == 0) goto L30;
            TLRPC.WallPaperSettings r34 = r1.settings;
            MotionBackgroundDrawable r17 = new MotionBackgroundDrawable(r34.background_color, r34.second_background_color, r34.third_background_color, r34.fourth_background_color, true);
            if (r1.settings.intensity < 0) goto L23;
        L26:
            this.imageView.setBackground(r17);
            if (Build.VERSION.SDK_INT < 29) goto L29;
            this.imageView.getImageReceiver().setBlendMode(PatternCell$$ExternalSyntheticApiModelOutline0.m());
        L29:
            TLRPC.WallPaperSettings r35 = r1.settings;
            int r36 = MotionBackgroundDrawable.getPatternColor(r35.background_color, r35.second_background_color, r35.third_background_color, r35.fourth_background_color);
        L32:
            if (Build.VERSION.SDK_INT >= 29) goto L34;
        L35:
            this.imageView.getImageReceiver().setColorFilter(new PorterDuffColorFilter(AndroidUtilities.getPatternColor(r36), PorterDuff.Mode.SRC_IN));
        L36:
            BackupImageView r9 = this.imageView;
            if (r4 == null) goto L39;
            r9.setImage(ImageLocation.getForDocument(r4, r1.document), "180_180", ImageLocation.getForDocument(r2, r1.document), null, "jpg", r15, 1, r1);
        L40:
            this.imageView.getImageReceiver().setAlpha(Math.abs(r1.settings.intensity) / 100.0f);
            return;
        L39:
            r9.setImage(ImageLocation.getForDocument(r2, r1.document), "180_180", null, null, "jpg", r15, 1, r1);
            goto L40
        L34:
            if (r1.settings.third_background_color != 0) goto L36;
        L23:
            if (Theme.getActiveTheme().isDark() == false) goto L26;
            this.imageView.getImageReceiver().setGradientBitmap(r17.getBitmap());
            goto L29
        L30:
            this.imageView.setBackgroundColor(Theme.getWallpaperColor(r33.background_color));
            r36 = AndroidUtilities.getPatternColor(r1.settings.background_color);
            goto L32
        L42:
            BackupImageView r92 = this.imageView;
            if (r4 == null) goto L46;
            r92.setImage(ImageLocation.getForDocument(r4, r1.document), "180_180", ImageLocation.getForDocument(r2, r1.document), "100_100_b", "jpg", r15, 1, r1);
            return;
        L46:
            r92.setImage(ImageLocation.getForDocument(r1.document), "180_180", ImageLocation.getForDocument(r2, r1.document), "100_100_b", "jpg", r15, 1, r1);
            return;
        L15:
            r52 = r1.document.size;
            goto L14
        L49:
            if ((r24 instanceof WallpapersListActivity.ColorWallpaper) == false) goto L102;
            WallpapersListActivity.ColorWallpaper r12 = (WallpapersListActivity.ColorWallpaper) r24;
            if (r12.path != null) goto L68;
            if (r12.pattern != null) goto L68;
            if ("d".equals(r12.slug) == true) goto L68;
            this.imageView.setImageBitmap(null);
            if (r12.isGradient == false) goto L61;
            this.imageView.setBackground(new MotionBackgroundDrawable(r12.color, r12.gradientColor1, r12.gradientColor2, r12.gradientColor3, true));
            return;
        L61:
            int r22 = r12.gradientColor1;
            BackupImageView r0 = this.imageView;
            if (r22 == 0) goto L65;
            r0.setBackground(new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{r12.color | (-16777216), r12.gradientColor1 | (-16777216)}));
            return;
        L65:
            r0.setBackgroundColor(r12.color | (-16777216));
            return;
        L68:
            if (r12.gradientColor2 == 0) goto L76;
            MotionBackgroundDrawable r122 = new MotionBackgroundDrawable(r12.color, r12.gradientColor1, r12.gradientColor2, r12.gradientColor3, true);
            float r23 = r12.intensity;
            BackupImageView r6 = this.imageView;
            if (r23 < 0.0f) goto L74;
            r6.setBackground(new MotionBackgroundDrawable(r12.color, r12.gradientColor1, r12.gradientColor2, r12.gradientColor3, true));
            if (Build.VERSION.SDK_INT < 29) goto L75;
            this.imageView.getImageReceiver().setBlendMode(PatternCell$$ExternalSyntheticApiModelOutline0.m());
        L75:
            int r28 = MotionBackgroundDrawable.getPatternColor(r12.color, r12.gradientColor1, r12.gradientColor2, r12.gradientColor3);
        L78:
            if ("d".equals(r12.slug) == true) goto L80;
            File r37 = r12.path;
            if (r37 == null) goto L88;
            this.imageView.setImage(r37.getAbsolutePath(), "180_180", null);
            return;
        L88:
            TLRPC.PhotoSize r38 = FileLoader.getClosestPhotoSizeWithSize(r12.pattern.document.thumbs, 100);
            if (r38 == null) goto L92;
            long r42 = r38.size;
        L93:
            this.imageView.setImage(ImageLocation.getForDocument(r38, r12.pattern.document), "180_180", null, null, "jpg", r42, 1, r12.pattern);
            this.imageView.getImageReceiver().setAlpha(Math.abs(r12.intensity));
            if (Build.VERSION.SDK_INT >= 29) goto L96;
        L99:
            this.imageView.getImageReceiver().setColorFilter(new PorterDuffColorFilter(AndroidUtilities.getPatternColor(r28), PorterDuff.Mode.SRC_IN));
            return;
        L96:
            if (r12.gradientColor2 == 0) goto L99;
            return;
        L92:
            r42 = r12.pattern.document.size;
            goto L93
        L80:
            if (r12.defaultCache != null) goto L82;
            r12.defaultCache = SvgHelper.getBitmap(R.raw.default_pattern, 100, 180, -16777216);
        L82:
            this.imageView.setImageBitmap(r12.defaultCache);
            this.imageView.getImageReceiver().setAlpha(Math.abs(r12.intensity));
            return;
        L74:
            r6.getImageReceiver().setGradientBitmap(r122.getBitmap());
            goto L75
        L76:
            r28 = AndroidUtilities.getPatternColor(r12.color);
            goto L78
        L102:
            if ((r24 instanceof WallpapersListActivity.FileWallpaper) == false) goto L118;
            WallpapersListActivity.FileWallpaper r13 = (WallpapersListActivity.FileWallpaper) r24;
            File r39 = r13.originalPath;
            if (r39 == null) goto L107;
            this.imageView.setImage(r39.getAbsolutePath(), "180_180", null);
            return;
        L107:
            File r310 = r13.path;
            if (r310 == null) goto L111;
            this.imageView.setImage(r310.getAbsolutePath(), "180_180", null);
            return;
        L111:
            boolean r311 = "t".equals(r13.slug);
            BackupImageView r02 = this.imageView;
            if (r311 == false) goto L115;
            r02.setImageDrawable(Theme.getThemedWallpaper(true, r02));
            return;
        L115:
            r02.setImageResource(r13.thumbResId);
            return;
        L118:
            if ((r24 instanceof MediaController.SearchImage) == false) goto L131;
            MediaController.SearchImage r14 = (MediaController.SearchImage) r24;
            TLRPC.Photo r29 = r14.photo;
            if (r29 == null) goto L129;
            TLRPC.PhotoSize r210 = FileLoader.getClosestPhotoSizeWithSize(r29.sizes, AndroidUtilities.dp(100.0f));
            TLRPC.PhotoSize r53 = FileLoader.getClosestPhotoSizeWithSize(r14.photo.sizes, AndroidUtilities.dp(180.0f));
            if (r53 == r210) goto L125;
            r4 = r53;
        L125:
            if (r4 == null) goto L127;
            r3 = r4.size;
        L127:
            this.imageView.setImage(ImageLocation.getForPhoto(r4, r14.photo), "180_180", ImageLocation.getForPhoto(r210, r14.photo), "100_100_b", "jpg", r3, 1, r14);
            return;
        L129:
            this.imageView.setImage(r14.thumbUrl, "180_180", null);
            return;
        L131:
            this.isSelected = false;
            return;
        L5:
            r5 = false;
            goto L6
        }
    }

    public static /* synthetic */ boolean $r8$lambda$8WRtcQ2rMCDFhgzs7PfIDfDjEGI(WallpaperCell r0, WallpaperView r1, int r2, View r3) {
        return r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$oAsc34FoBJ6RPNIITP0AwaL4q7Q(WallpaperCell r0, WallpaperView r1, int r2, View r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m7125$$Nest$fgetbackgroundPaint(WallpaperCell r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetcheckDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m7126$$Nest$fgetcheckDrawable(WallpaperCell r0) {
        return r0.checkDrawable;
    }

    /* renamed from: -$$Nest$fgetcirclePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m7127$$Nest$fgetcirclePaint(WallpaperCell r0) {
        return r0.circlePaint;
    }

    /* renamed from: -$$Nest$fgetframePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m7128$$Nest$fgetframePaint(WallpaperCell r0) {
        return r0.framePaint;
    }

    public WallpaperCell(Context r2) {
        this(r2, 5);
    }

    public WallpaperCell(Context r4, int r5) {
        super(r4);
        this.drawStubBackground = true;
        this.spanCount = 3;
        this.wallpaperViews = new WallpaperView[r5];
        int r52 = 0;
    L3:
        WallpaperView[] r1 = this.wallpaperViews;
        if (r52 >= r1.length) goto L6;
        WallpaperView r2 = new WallpaperView(this, r4);
        r1[r52] = r2;
        addView(r2);
        r2.setOnClickListener(new WallpaperCell$$ExternalSyntheticLambda0(this, r2, r52));
        r2.setOnLongClickListener(new WallpaperCell$$ExternalSyntheticLambda1(this, r2, r52));
        r52 = r52 + 1;
        goto L3
    L6:
        Paint r53 = new Paint();
        this.framePaint = r53;
        r53.setColor(855638016);
        this.circlePaint = new Paint(1);
        this.checkDrawable = r4.getResources().getDrawable(R.drawable.background_selected).mutate();
        Paint r42 = new Paint();
        this.backgroundPaint = r42;
        r42.setColor(Theme.getColor(Theme.key_sharedMedia_photoPlaceholder));
    }

    private /* synthetic */ void lambda$new$0(WallpaperView r1, int r2, View r3) {
        onWallpaperClick(WallpaperView.m7130$$Nest$fgetcurrentWallpaper(r1), r2);
    }

    private /* synthetic */ boolean lambda$new$1(WallpaperView r1, int r2, View r3) {
        return onWallpaperLongClick(WallpaperView.m7130$$Nest$fgetcurrentWallpaper(r1), r2);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        int r0 = 0;
    L4:
        if (r0 >= this.spanCount) goto L6;
        this.wallpaperViews[r0].invalidate();
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        if (this.spanCount != 1) goto L6;
        super.onLayout(r3, r4, r5, r6, r7);
        return;
    L6:
        int r42 = AndroidUtilities.dp(14.0f);
        int r62 = 0;
        if (this.isTop == false) goto L9;
        int r32 = AndroidUtilities.dp(14.0f);
    L11:
        if (r62 >= this.spanCount) goto L13;
        int r52 = this.wallpaperViews[r62].getMeasuredWidth();
        WallpaperView r72 = this.wallpaperViews[r62];
        r72.layout(r42, r32, r42 + r52, r72.getMeasuredHeight() + r32);
        r42 = r42 + (r52 + AndroidUtilities.dp(6.0f));
        r62 = r62 + 1;
        goto L11
    L13:
        return;
    L9:
        r32 = 0;
        goto L11
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r9, int r10) {
        float r0 = 6.0f;
        int r2 = 0;
        if (this.spanCount != 1) goto L6;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.size, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.size + AndroidUtilities.dp(6.0f), TLObject.FLAG_30));
        setPadding(0, 0, 0, AndroidUtilities.dp(6.0f));
        return;
    L6:
        int r92 = View.MeasureSpec.getSize(r9);
        int r102 = r92 - AndroidUtilities.dp(((this.spanCount - 1) * 6) + 28);
        int r3 = r102 / this.spanCount;
        int r4 = this.currentType;
        if (r4 != 0) goto L9;
    L14:
        int r42 = AndroidUtilities.dp(180.0f);
    L16:
        if (this.isTop == false) goto L18;
        int r5 = AndroidUtilities.dp(14.0f);
    L19:
        int r52 = r5 + r42;
        if (this.isBottom == false) goto L22;
        r0 = 14.0f;
    L22:
        setMeasuredDimension(r92, r52 + AndroidUtilities.dp(r0));
    L23:
        int r93 = this.spanCount;
        if (r2 >= r93) goto L30;
        WallpaperView r02 = this.wallpaperViews[r2];
        if (r2 != (r93 - 1)) goto L28;
        int r94 = r102;
    L29:
        r02.measure(View.MeasureSpec.makeMeasureSpec(r94, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r42, TLObject.FLAG_30));
        r102 = r102 - r3;
        r2 = r2 + 1;
        goto L23
    L28:
        r94 = r3;
        goto L29
    L30:
        return;
    L18:
        r5 = 0;
        goto L19
    L9:
        if (r4 == 2) goto L14;
        if (r4 == 3) goto L14;
        r42 = r3;
        goto L16
    }

    public abstract void onWallpaperClick(Object r1, int r2);

    public boolean onWallpaperLongClick(Object r1, int r2) {
        return false;
    }

    public void setChecked(int r1, boolean r2, boolean r3) {
        this.wallpaperViews[r1].setChecked(r2, r3);
    }

    public void setParams(int r3, boolean r4, boolean r5) {
        this.spanCount = r3;
        this.isTop = r4;
        this.isBottom = r5;
        int r52 = 0;
    L3:
        WallpaperView[] r0 = this.wallpaperViews;
        if (r52 >= r0.length) goto L10;
        WallpaperView r02 = r0[r52];
        if (r52 >= r3) goto L8;
        int r1 = 0;
    L9:
        r02.setVisibility(r1);
        this.wallpaperViews[r52].clearAnimation();
        r52 = r52 + 1;
        goto L3
    L8:
        r1 = 8;
        goto L9
    }

    public void setSize(int r2) {
        if (this.size == r2) goto L6;
        this.size = r2;
        requestLayout();
        return;
    }

    public void setWallpaper(int r2, int r3, Object r4, Object r5, Drawable r6, boolean r7) {
        this.currentType = r2;
        WallpaperView[] r22 = this.wallpaperViews;
        if (r4 != null) goto L6;
        r22[r3].setVisibility(8);
        this.wallpaperViews[r3].clearAnimation();
        return;
    L6:
        r22[r3].setVisibility(0);
        this.wallpaperViews[r3].setWallpaper(r4, r5, r6, r7);
    }
}
