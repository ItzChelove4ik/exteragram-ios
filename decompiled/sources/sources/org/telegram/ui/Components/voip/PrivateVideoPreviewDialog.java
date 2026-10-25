package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.media.projection.MediaProjectionManager;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.io.File;
import java.io.FileOutputStream;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.voip.VideoCapturerDevice;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.webrtc.RendererCommon;

/* loaded from: classes7.dex */
public abstract class PrivateVideoPreviewDialog extends FrameLayout implements VoIPService.StateListener {
    private boolean cameraReady;
    private int currentPage;
    private int currentTexturePage;
    private boolean isDismissed;
    public boolean micEnabled;
    private RLottieImageView micIconView;
    private boolean needScreencast;
    private float outProgress;
    private float pageOffset;
    private final TextView positiveButton;
    private final VoIPTextureView textureView;
    private final TextView[] titles;
    private final LinearLayout titlesLayout;
    private final ViewPager viewPager;
    private int visibleCameraPage;

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialog$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewPager.OnPageChangeListener {
        private int scrollState;
        final /* synthetic */ PrivateVideoPreviewDialog this$0;
        private int willSetPage;

        public AnonymousClass1(PrivateVideoPreviewDialog r1) {
            this.this$0 = r1;
            this.scrollState = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r2) {
            this.scrollState = r2;
            if (r2 != 0) goto L6;
            PrivateVideoPreviewDialog.m13861$$Nest$fputcurrentTexturePage(this.this$0, this.willSetPage);
            PrivateVideoPreviewDialog.m13863$$Nest$monFinishMoveCameraPage(this.this$0);
            return;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r1, float r2, int r3) {
            PrivateVideoPreviewDialog.m13860$$Nest$fputcurrentPage(this.this$0, r1);
            PrivateVideoPreviewDialog.m13862$$Nest$fputpageOffset(this.this$0, r2);
            PrivateVideoPreviewDialog.m13864$$Nest$mupdateTitlesLayout(this.this$0);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
        /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r5) {
            int r0 = this.scrollState;
            PrivateVideoPreviewDialog r1 = this.this$0;
            if (r0 != 0) goto L11;
            ?? r02 = PrivateVideoPreviewDialog.m13857$$Nest$fgetneedScreencast(r1);
            PrivateVideoPreviewDialog r12 = this.this$0;
            if (r5 > r02) goto L7;
            PrivateVideoPreviewDialog.m13861$$Nest$fputcurrentTexturePage(r12, 1);
        L8:
            PrivateVideoPreviewDialog.m13863$$Nest$monFinishMoveCameraPage(this.this$0);
            return;
        L7:
            PrivateVideoPreviewDialog.m13861$$Nest$fputcurrentTexturePage(r12, 2);
            goto L8
        L11:
            if (r5 > PrivateVideoPreviewDialog.m13857$$Nest$fgetneedScreencast(r1)) goto L14;
            this.willSetPage = 1;
            return;
        L14:
            this.willSetPage = 2;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialog$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PrivateVideoPreviewDialog this$0;

        public AnonymousClass2(PrivateVideoPreviewDialog r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.dismiss(false, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialog$3, reason: invalid class name */
    public class AnonymousClass3 extends TextView {
        private Paint[] gradientPaint;
        final /* synthetic */ PrivateVideoPreviewDialog this$0;

        public AnonymousClass3(PrivateVideoPreviewDialog r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.gradientPaint = new Paint[PrivateVideoPreviewDialog.m13859$$Nest$fgettitles(r3).length];
            int r32 = 0;
        L3:
            Paint[] r42 = this.gradientPaint;
            if (r32 >= r42.length) goto L6;
            r42[r32] = new Paint(1);
            r32 = r32 + 1;
            goto L3
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r8) {
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            this.gradientPaint[PrivateVideoPreviewDialog.m13856$$Nest$fgetcurrentPage(this.this$0)].setAlpha(255);
            r8.drawRoundRect(r0, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.gradientPaint[PrivateVideoPreviewDialog.m13856$$Nest$fgetcurrentPage(this.this$0)]);
            if (PrivateVideoPreviewDialog.m13858$$Nest$fgetpageOffset(this.this$0) <= 0.0f) goto L7;
            int r2 = PrivateVideoPreviewDialog.m13856$$Nest$fgetcurrentPage(this.this$0) + 1;
            Paint[] r3 = this.gradientPaint;
            if (r2 >= r3.length) goto L7;
            r3[PrivateVideoPreviewDialog.m13856$$Nest$fgetcurrentPage(this.this$0) + 1].setAlpha((int) (PrivateVideoPreviewDialog.m13858$$Nest$fgetpageOffset(this.this$0) * 255.0f));
            r8.drawRoundRect(r0, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.gradientPaint[PrivateVideoPreviewDialog.m13856$$Nest$fgetcurrentPage(this.this$0) + 1]);
        L7:
            super.onDraw(r8);
        }

        @Override // android.view.View
        public void onSizeChanged(int r11, int r12, int r13, int r14) {
            super.onSizeChanged(r11, r12, r13, r14);
            int r122 = 0;
        L4:
            if (r122 >= this.gradientPaint.length) goto L23;
            if (r122 == 0) goto L7;
        L10:
            int r132 = -9015575;
            if (r122 != 0) goto L13;
        L18:
            int r142 = -9015575;
            r132 = -11033346;
        L9:
            int r0 = 0;
        L19:
            if (r0 == 0) goto L21;
            LinearGradient r1 = new LinearGradient(0.0f, 0.0f, getMeasuredWidth(), 0.0f, new int[]{r132, r142, r0}, null, Shader.TileMode.CLAMP);
        L22:
            this.gradientPaint[r122].setShader(r1);
            r122 = r122 + 1;
            goto L4
        L21:
            r1 = new LinearGradient(0.0f, 0.0f, getMeasuredWidth(), 0.0f, new int[]{r132, r142}, null, Shader.TileMode.CLAMP);
            goto L22
        L13:
            if (r122 == 1) goto L15;
        L17:
            r142 = -1026983;
            r0 = -1792170;
            goto L19
        L15:
            if (PrivateVideoPreviewDialog.m13857$$Nest$fgetneedScreencast(this.this$0) == false) goto L17;
        L7:
            if (PrivateVideoPreviewDialog.m13857$$Nest$fgetneedScreencast(this.this$0) == false) goto L10;
            r132 = -8919716;
            r142 = -11089922;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialog$4, reason: invalid class name */
    public class AnonymousClass4 implements RendererCommon.RendererEvents {
        final /* synthetic */ PrivateVideoPreviewDialog this$0;

        public AnonymousClass4(PrivateVideoPreviewDialog r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int r1, int r2, int r3) {
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialog$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ PrivateVideoPreviewDialog this$0;

        public AnonymousClass5(PrivateVideoPreviewDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            if (this.this$0.getParent() == null) goto L6;
            ((ViewGroup) this.this$0.getParent()).removeView(this.this$0);
            return;
        }
    }

    public class Adapter extends PagerAdapter {
        final /* synthetic */ PrivateVideoPreviewDialog this$0;

        private Adapter(PrivateVideoPreviewDialog r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(PrivateVideoPreviewDialog r1, PrivateVideoPreviewDialogIA r2) {
            this(r1);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup r1, int r2, Object r3) {
            r1.removeView((View) r3);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return PrivateVideoPreviewDialog.m13859$$Nest$fgettitles(this.this$0).length;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r12v3, types: [android.view.View, android.view.ViewGroup, android.widget.FrameLayout] */
        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup r11, int r12) {
            int r2 = 1;
            if (PrivateVideoPreviewDialog.m13857$$Nest$fgetneedScreencast(this.this$0) == false) goto L6;
            if (r12 != 0) goto L6;
            ?? r122 = new FrameLayout(this.this$0.getContext());
            r122.setBackground(new MotionBackgroundDrawable(-14602694, -13935795, -14395293, -14203560, true));
            ImageView r0 = new ImageView(this.this$0.getContext());
            r0.setScaleType(ImageView.ScaleType.CENTER);
            r0.setImageResource(R.drawable.screencast_big);
            r122.addView(r0, LayoutHelper.createFrame(82, 82.0f, 17, 0.0f, 0.0f, 0.0f, 60.0f));
            TextView r02 = new TextView(this.this$0.getContext());
            r02.setText(LocaleController.getString(R.string.VoipVideoPrivateScreenSharing));
            r02.setGravity(17);
            r02.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            r02.setTextColor(-1);
            r02.setTextSize(1, 15.0f);
            r02.setTypeface(AndroidUtilities.bold());
            r122.addView(r02, LayoutHelper.createFrame(-1, -2.0f, 17, 21.0f, 28.0f, 21.0f, 0.0f));
            ImageView r123 = r122;
        L22:
            if (r123.getParent() == null) goto L24;
            ((ViewGroup) r123.getParent()).removeView(r123);
        L24:
            r11.addView(r123, 0);
            return r123;
        L6:
            ImageView r1 = new ImageView(this.this$0.getContext());
            r1.setTag(Integer.valueOf(r12));
            File r4 = ApplicationLoader.getFilesDirFixed();     // Catch: Throwable -> L16
            StringBuilder r5 = new StringBuilder("cthumb");     // Catch: Throwable -> L16
            if (r12 == 0) goto L14;
            if (r12 == 1) goto L11;
        L13:
            r2 = 2;
            goto L14
        L11:
            if (PrivateVideoPreviewDialog.m13857$$Nest$fgetneedScreencast(this.this$0) == false) goto L13;
        L14:
            r5.append(r2);     // Catch: Throwable -> L16
            r5.append(".jpg");     // Catch: Throwable -> L16
            Bitmap r10 = BitmapFactory.decodeFile(new File(r4, r5.toString()).getAbsolutePath());     // Catch: Throwable -> L16
        L17:
            if (r10 == null) goto L19;
            r1.setImageBitmap(r10);
        L20:
            r1.setScaleType(ImageView.ScaleType.FIT_XY);
            r123 = r1;
            goto L22
        L19:
            r1.setImageResource(R.drawable.icplaceholder);
        L16:
            r10 = null;
            goto L17
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View r1, Object r2) {
            return r1.equals(r2);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void restoreState(Parcelable r1, ClassLoader r2) {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Parcelable saveState() {
            return null;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void setPrimaryItem(ViewGroup r1, int r2, Object r3) {
            super.setPrimaryItem(r1, r2, r3);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void unregisterDataSetObserver(DataSetObserver r1) {
            if (r1 == null) goto L5;
            super.unregisterDataSetObserver(r1);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$E7QEEpw1qfmDaSzAhBhswD9iymY(PrivateVideoPreviewDialog r0, RLottieDrawable r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FENvFcrNKlS_l_bCSpPZrO1BilU(PrivateVideoPreviewDialog r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZkfcIBRgHEy9JYiQ9kwHzLkLY4Q(PrivateVideoPreviewDialog r0, int r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcurrentPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13856$$Nest$fgetcurrentPage(PrivateVideoPreviewDialog r0) {
        return r0.currentPage;
    }

    /* renamed from: -$$Nest$fgetneedScreencast, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13857$$Nest$fgetneedScreencast(PrivateVideoPreviewDialog r0) {
        return r0.needScreencast;
    }

    /* renamed from: -$$Nest$fgetpageOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13858$$Nest$fgetpageOffset(PrivateVideoPreviewDialog r0) {
        return r0.pageOffset;
    }

    /* renamed from: -$$Nest$fgettitles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView[] m13859$$Nest$fgettitles(PrivateVideoPreviewDialog r0) {
        return r0.titles;
    }

    /* renamed from: -$$Nest$fputcurrentPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13860$$Nest$fputcurrentPage(PrivateVideoPreviewDialog r0, int r1) {
        r0.currentPage = r1;
    }

    /* renamed from: -$$Nest$fputcurrentTexturePage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13861$$Nest$fputcurrentTexturePage(PrivateVideoPreviewDialog r0, int r1) {
        r0.currentTexturePage = r1;
    }

    /* renamed from: -$$Nest$fputpageOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13862$$Nest$fputpageOffset(PrivateVideoPreviewDialog r0, float r1) {
        r0.pageOffset = r1;
    }

    /* renamed from: -$$Nest$monFinishMoveCameraPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13863$$Nest$monFinishMoveCameraPage(PrivateVideoPreviewDialog r0) {
        r0.onFinishMoveCameraPage();
    }

    /* renamed from: -$$Nest$mupdateTitlesLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13864$$Nest$mupdateTitlesLayout(PrivateVideoPreviewDialog r0) {
        r0.updateTitlesLayout();
    }

    public PrivateVideoPreviewDialog(Context r18, boolean r19, boolean r20) {
        super(r18);
        this.currentTexturePage = 1;
        this.visibleCameraPage = 1;
        this.needScreencast = r20;
        if (r20 == false) goto L5;
        int r2 = 3;
    L6:
        this.titles = new TextView[r2];
        ViewPager r22 = new ViewPager(r18);
        this.viewPager = r22;
        AndroidUtilities.setViewPagerEdgeEffectColor(r22, 2130706432);
        r22.setAdapter(new Adapter(this, null));
        r22.setPageMargin(0);
        r22.setOffscreenPageLimit(1);
        addView(r22, LayoutHelper.createFrame(-1, -1.0f));
        r22.addOnPageChangeListener(new AnonymousClass1(this));
        VoIPTextureView r23 = new VoIPTextureView(r18, false, false);
        this.textureView = r23;
        r23.renderer.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FILL);
        r23.scaleType = VoIPTextureView.SCALE_TYPE_FIT;
        r23.clipToTexture = true;
        r23.renderer.setAlpha(0.0f);
        r23.renderer.setRotateTextureWithScreen(true);
        r23.renderer.setUseCameraRotation(true);
        addView(r23, LayoutHelper.createFrame(-1, -1.0f));
        ActionBar r24 = new ActionBar(r18);
        r24.setBackButtonDrawable(new BackDrawable(false));
        r24.setBackgroundColor(0);
        r24.setItemsColor(Theme.getColor(Theme.key_voipgroup_actionBarItems), false);
        r24.setOccupyStatusBar(true);
        r24.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        addView(r24);
        AnonymousClass3 r25 = new AnonymousClass3(this, getContext());
        this.positiveButton = r25;
        r25.setMinWidth(AndroidUtilities.dp(64.0f));
        r25.setTag(-1);
        r25.setTextSize(1, 14.0f);
        int r6 = Theme.key_voipgroup_nameText;
        r25.setTextColor(Theme.getColor(r6));
        r25.setGravity(17);
        r25.setTypeface(AndroidUtilities.bold());
        r25.setText(LocaleController.getString(R.string.VoipShareVideo));
        r25.setForeground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(r6), 76)));
        r25.setPadding(0, AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f));
        r25.setOnClickListener(new PrivateVideoPreviewDialog$$ExternalSyntheticLambda0(this));
        addView(r25, LayoutHelper.createFrame(-1, 48.0f, 80, 0.0f, 0.0f, 0.0f, 64.0f));
        LinearLayout r26 = new LinearLayout(r18);
        this.titlesLayout = r26;
        addView(r26, LayoutHelper.createFrame(-2, 64, 80));
        int r27 = 0;
    L7:
        TextView[] r7 = this.titles;
        if (r27 >= r7.length) goto L22;
        r7[r27] = new TextView(r18);
        this.titles[r27].setTextSize(1, 12.0f);
        this.titles[r27].setTextColor(-1);
        this.titles[r27].setTypeface(AndroidUtilities.bold());
        this.titles[r27].setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        this.titles[r27].setGravity(16);
        this.titles[r27].setSingleLine(true);
        this.titlesLayout.addView(this.titles[r27], LayoutHelper.createLinear(-2, -1));
        if (r27 == 0) goto L12;
    L14:
        if (r27 == 0) goto L20;
        if (r27 == 1) goto L17;
    L19:
        this.titles[r27].setText(LocaleController.getString(R.string.VoipBackCamera));
    L21:
        this.titles[r27].setOnClickListener(new PrivateVideoPreviewDialog$$ExternalSyntheticLambda1(this, r27));
        r27 = r27 + 1;
        goto L7
    L17:
        if (this.needScreencast == false) goto L19;
    L20:
        this.titles[r27].setText(LocaleController.getString(R.string.VoipFrontCamera));
        goto L21
    L12:
        if (this.needScreencast == false) goto L14;
        this.titles[r27].setText(LocaleController.getString(R.string.VoipPhoneScreen));
        goto L21
    L22:
        setAlpha(0.0f);
        setTranslationX(AndroidUtilities.dp(32.0f));
        animate().alpha(1.0f).translationX(0.0f).setDuration(150).start();
        setWillNotDraw(false);
        VoIPService r28 = VoIPService.getSharedInstance();
        if (r28 == null) goto L25;
        this.textureView.renderer.setMirror(r28.isFrontFaceCamera());
        this.textureView.renderer.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), new AnonymousClass4(this));
        r28.setLocalSink(this.textureView.renderer, false);
    L25:
        this.viewPager.setCurrentItem(this.needScreencast ? 1 : 0);
        if (r19 == false) goto L33;
        RLottieImageView r29 = new RLottieImageView(r18);
        this.micIconView = r29;
        r29.setPadding(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f));
        this.micIconView.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(48.0f), ColorUtils.setAlphaComponent(-16777216, 76)));
        RLottieDrawable r4 = new RLottieDrawable(R.raw.voice_mini, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), true, null);
        this.micIconView.setAnimation(r4);
        this.micIconView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.micEnabled = true;
        r4.setCurrentFrame(69);
        this.micIconView.setOnClickListener(new PrivateVideoPreviewDialog$$ExternalSyntheticLambda2(this, r4));
        addView(this.micIconView, LayoutHelper.createFrame(48, 48.0f, 83, 24.0f, 0.0f, 0.0f, 136.0f));
        return;
    L33:
        return;
    L5:
        r2 = 2;
        goto L6
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        if (this.isDismissed == false) goto L6;
        return;
    L6:
        if (this.currentPage == 0) goto L8;
    L11:
        dismiss(false, true);
        return;
    L8:
        if (this.needScreencast == false) goto L11;
        MediaProjectionManager r22 = (MediaProjectionManager) getContext().getSystemService("media_projection");
        ((Activity) getContext()).startActivityForResult(r22.createScreenCaptureIntent(), 520);
    }

    private /* synthetic */ void lambda$new$1(int r1, View r2) {
        this.viewPager.setCurrentItem(r1, true);
    }

    private /* synthetic */ void lambda$new$2(RLottieDrawable r2, View r3) {
        boolean r32 = this.micEnabled;
        this.micEnabled = !r32;
        if (r32 == true) goto L5;
        r2.setCurrentFrame(36);
        r2.setCustomEndFrame(69);
    L6:
        r2.start();
        return;
    L5:
        r2.setCurrentFrame(69);
        r2.setCustomEndFrame(99);
        goto L6
    }

    private void onFinishMoveCameraPage() {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (this.currentTexturePage == this.visibleCameraPage) goto L15;
        if (r0 == null) goto L16;
        boolean r02 = r0.isFrontFaceCamera();
        int r1 = this.currentTexturePage;
        if (r1 != 1) goto L10;
        if (r02 == true) goto L10;
    L12:
        saveLastCameraBitmap();
        this.cameraReady = false;
        VoIPService.getSharedInstance().switchCamera();
        this.textureView.setAlpha(0.0f);
    L13:
        this.visibleCameraPage = this.currentTexturePage;
        return;
    L10:
        if (r1 != 2) goto L13;
        if (r02 == false) goto L13;
    L16:
        return;
    }

    private void saveLastCameraBitmap() {
        if (this.cameraReady == false) goto L21;
        Bitmap r2 = this.textureView.renderer.getBitmap();     // Catch: Throwable -> L15
        if (r2 == null) goto L18;
        Bitmap r1 = Bitmap.createBitmap(r2, 0, 0, r2.getWidth(), r2.getHeight(), this.textureView.renderer.getMatrix(), true);     // Catch: Throwable -> L15
        r2.recycle();     // Catch: Throwable -> L15
        Bitmap r22 = Bitmap.createScaledBitmap(r1, 80, (int) (r1.getHeight() / (r1.getWidth() / 80.0f)), true);     // Catch: Throwable -> L15
        if (r22 == null) goto L19;
        if (r22 == r1) goto L11;
        r1.recycle();     // Catch: Throwable -> L15
    L11:
        Utilities.blurBitmap(r22, 7);     // Catch: Throwable -> L15
        r22.compress(Bitmap.CompressFormat.JPEG, 87, new FileOutputStream(new File(ApplicationLoader.getFilesDirFixed(), "cthumb" + this.visibleCameraPage + ".jpg")));     // Catch: Throwable -> L15
        View r9 = this.viewPager.findViewWithTag(Integer.valueOf(this.visibleCameraPage - (!this.needScreencast ? 1 : 0)));     // Catch: Throwable -> L15
        if ((r9 instanceof ImageView) == false) goto L20;
        ((ImageView) r9).setImageBitmap(r22);     // Catch: Throwable -> L15
        return;
    L20:
        return;
    L19:
        return;
    L18:
        return;
    L22:
        return;
    }

    private void updateTitlesLayout() {
        TextView[] r0 = this.titles;
        int r1 = this.currentPage;
        TextView r2 = r0[r1];
        if (r1 >= (r0.length - 1)) goto L5;
        TextView r02 = r0[r1 + 1];
    L6:
        getMeasuredWidth();
        float r12 = r2.getLeft() + (r2.getMeasuredWidth() / 2);
        float r22 = (getMeasuredWidth() / 2) - r12;
        if (r02 == null) goto L9;
        r22 = r22 - (((r02.getLeft() + (r02.getMeasuredWidth() / 2)) - r12) * this.pageOffset);
    L9:
        int r13 = 0;
    L10:
        TextView[] r3 = this.titles;
        if (r13 >= r3.length) goto L22;
        int r4 = this.currentPage;
        float r6 = 0.9f;
        float r7 = 0.7f;
        if (r13 < r4) goto L21;
        if (r13 > (r4 + 1)) goto L21;
        float r8 = this.pageOffset;
        if (r13 != r4) goto L20;
        r7 = 1.0f - (0.3f * r8);
        r6 = 1.0f - (r8 * 0.1f);
        goto L21
    L20:
        r7 = 0.7f + (0.3f * r8);
        r6 = 0.9f + (r8 * 0.1f);
    L21:
        r3[r13].setAlpha(r7);
        this.titles[r13].setScaleX(r6);
        this.titles[r13].setScaleY(r6);
        r13 = r13 + 1;
        goto L10
    L22:
        this.titlesLayout.setTranslationX(r22);
        this.positiveButton.invalidate();
        if (this.needScreencast == true) goto L25;
    L30:
        this.textureView.setVisibility(0);
        int r03 = this.currentPage + (!this.needScreencast ? 1 : 0);
        int r14 = this.currentTexturePage;
        VoIPTextureView r23 = this.textureView;
        if (r03 != r14) goto L34;
        r23.setTranslationX((-this.pageOffset) * getMeasuredWidth());
        return;
    L34:
        r23.setTranslationX((1.0f - this.pageOffset) * getMeasuredWidth());
        return;
    L25:
        if (this.currentPage != 0) goto L30;
        if (this.pageOffset > 0.0f) goto L30;
        this.textureView.setVisibility(4);
        return;
    L5:
        r02 = null;
        goto L6
    }

    public void dismiss(boolean r3, boolean r4) {
        if (this.isDismissed == false) goto L5;
        return;
    L5:
        this.isDismissed = true;
        saveLastCameraBitmap();
        onDismiss(r3, r4);
        animate().alpha(0.0f).translationX(AndroidUtilities.dp(32.0f)).setDuration(150).setListener(new AnonymousClass5(this));
        invalidate();
    }

    public int getBackgroundColor() {
        return ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_voipgroup_actionBar), (int) ((getAlpha() * (1.0f - this.outProgress)) * 255.0f));
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        if (getParent() == null) goto L6;
        ((View) getParent()).invalidate();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L6;
        r0.registerStateListener(this);
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onCameraFirstFrameAvailable() {
        if (this.cameraReady == true) goto L6;
        this.cameraReady = true;
        this.textureView.animate().alpha(1.0f).setDuration(250);
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onCameraSwitch(boolean r1) {
        update();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L6;
        r0.unregisterStateListener(this);
        return;
    }

    public abstract void onDismiss(boolean r1, boolean r2);

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateTitlesLayout();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r10, int r11) {
        if (View.MeasureSpec.getSize(r10) <= View.MeasureSpec.getSize(r11)) goto L5;
        boolean r0 = true;
    L6:
        ViewGroup.MarginLayoutParams r1 = (ViewGroup.MarginLayoutParams) this.positiveButton.getLayoutParams();
        if (r0 == false) goto L9;
        int r3 = AndroidUtilities.dp(80.0f);
        r1.leftMargin = r3;
        r1.rightMargin = r3;
    L10:
        RLottieImageView r12 = this.micIconView;
        if (r12 == null) goto L16;
        ViewGroup.MarginLayoutParams r13 = (ViewGroup.MarginLayoutParams) r12.getLayoutParams();
        if (r0 == false) goto L15;
        int r02 = AndroidUtilities.dp(88.0f);
        r13.leftMargin = r02;
        r13.rightMargin = r02;
        goto L16
    L15:
        int r03 = AndroidUtilities.dp(24.0f);
        r13.leftMargin = r03;
        r13.rightMargin = r03;
    L16:
        super.onMeasure(r10, r11);
        measureChildWithMargins(this.titlesLayout, View.MeasureSpec.makeMeasureSpec(0, 0), 0, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f), TLObject.FLAG_30), 0);
        return;
    L9:
        int r32 = AndroidUtilities.dp(16.0f);
        r1.leftMargin = r32;
        r1.rightMargin = r32;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r1) {
        return true;
    }

    public void setBottomPadding(int r3) {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.positiveButton.getLayoutParams();
        r0.bottomMargin = AndroidUtilities.dp(64.0f) + r3;
        ((FrameLayout.LayoutParams) this.titlesLayout.getLayoutParams()).bottomMargin = r3;
        FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) this.micIconView.getLayoutParams();
        r2.bottomMargin = AndroidUtilities.dp(136.0f) + r3;
    }

    public void update() {
        if (VoIPService.getSharedInstance() == null) goto L6;
        this.textureView.renderer.setMirror(VoIPService.getSharedInstance().isFrontFaceCamera());
        return;
    }
}
