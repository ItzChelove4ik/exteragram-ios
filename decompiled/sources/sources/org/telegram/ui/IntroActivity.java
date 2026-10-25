package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Looper;
import android.os.Parcelable;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.EmuDetector;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.GenericProvider;
import org.telegram.messenger.Intro;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.BottomPagesView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SimpleThemeDescription;
import org.telegram.ui.Components.voip.CellFlickerDrawable;

/* loaded from: classes6.dex */
public class IntroActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private BottomPagesView bottomPages;
    private final int currentAccount;
    private long currentDate;
    private int currentViewPagerPage;
    private RLottieDrawable darkThemeDrawable;
    private boolean destroyed;
    private boolean dragging;
    private EGLThread eglThread;
    private FrameLayout frameContainerView;
    private FrameLayout frameLayout2;
    private boolean isOnLogout;
    private boolean justCreated;
    private boolean justEndDragging;
    private int lastPage;
    private LocaleController.LocaleInfo localeInfo;
    private String[] messages;
    private final Object pagerHeaderTag;
    private final Object pagerMessageTag;
    private int startDragX;
    private TextView startMessagingButton;
    private GradientDrawable startMessagingButtonBackground;
    private boolean startPressed;
    private TextView switchLanguageTextView;
    private CharSequence[] titles;
    private ViewPager viewPager;

    /* renamed from: org.telegram.ui.IntroActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ IntroActivity this$0;
        final /* synthetic */ FrameLayout val$themeFrameLayout;
        final /* synthetic */ int val$themeMargin;

        public AnonymousClass1(IntroActivity r1, Context r2, FrameLayout r3, int r4) {
            this.this$0 = r1;
            this.val$themeFrameLayout = r3;
            this.val$themeMargin = r4;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
            super.onLayout(r4, r5, r6, r7, r8);
            int r82 = (r8 - r6) / 4;
            int r42 = r82 * 3;
            int r52 = (r42 - AndroidUtilities.dp(275.0f)) / 2;
            int r1 = 0;
            IntroActivity.m15493$$Nest$fgetframeLayout2(this.this$0).layout(0, r52, IntroActivity.m15493$$Nest$fgetframeLayout2(this.this$0).getMeasuredWidth(), IntroActivity.m15493$$Nest$fgetframeLayout2(this.this$0).getMeasuredHeight() + r52);
            int r53 = (r52 + AndroidUtilities.dp(150.0f)) + AndroidUtilities.dp(139.0f);
            int r62 = (getMeasuredWidth() - IntroActivity.m15487$$Nest$fgetbottomPages(this.this$0).getMeasuredWidth()) / 2;
            IntroActivity.m15487$$Nest$fgetbottomPages(this.this$0).layout(r62, r53, IntroActivity.m15487$$Nest$fgetbottomPages(this.this$0).getMeasuredWidth() + r62, IntroActivity.m15487$$Nest$fgetbottomPages(this.this$0).getMeasuredHeight() + r53);
            IntroActivity.m15502$$Nest$fgetviewPager(this.this$0).layout(0, 0, IntroActivity.m15502$$Nest$fgetviewPager(this.this$0).getMeasuredWidth(), IntroActivity.m15502$$Nest$fgetviewPager(this.this$0).getMeasuredHeight());
            int r43 = r42 + ((r82 - IntroActivity.m15498$$Nest$fgetstartMessagingButton(this.this$0).getMeasuredHeight()) / 2);
            int r54 = (getMeasuredWidth() - IntroActivity.m15498$$Nest$fgetstartMessagingButton(this.this$0).getMeasuredWidth()) / 2;
            IntroActivity.m15498$$Nest$fgetstartMessagingButton(this.this$0).layout(r54, r43, IntroActivity.m15498$$Nest$fgetstartMessagingButton(this.this$0).getMeasuredWidth() + r54, IntroActivity.m15498$$Nest$fgetstartMessagingButton(this.this$0).getMeasuredHeight() + r43);
            int r44 = r43 - AndroidUtilities.dp(30.0f);
            int r55 = (getMeasuredWidth() - IntroActivity.m15500$$Nest$fgetswitchLanguageTextView(this.this$0).getMeasuredWidth()) / 2;
            IntroActivity.m15500$$Nest$fgetswitchLanguageTextView(this.this$0).layout(r55, r44 - IntroActivity.m15500$$Nest$fgetswitchLanguageTextView(this.this$0).getMeasuredHeight(), IntroActivity.m15500$$Nest$fgetswitchLanguageTextView(this.this$0).getMeasuredWidth() + r55, r44);
            ViewGroup.MarginLayoutParams r45 = (ViewGroup.MarginLayoutParams) this.val$themeFrameLayout.getLayoutParams();
            int r56 = AndroidUtilities.dp(this.val$themeMargin);
            if (AndroidUtilities.isTablet() == true) goto L6;
            r1 = AndroidUtilities.statusBarHeight;
        L6:
            int r57 = r56 + r1;
            if (r45.topMargin == r57) goto L10;
            r45.topMargin = r57;
            this.val$themeFrameLayout.requestLayout();
            return;
        }
    }

    /* renamed from: org.telegram.ui.IntroActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements TextureView.SurfaceTextureListener {
        final /* synthetic */ IntroActivity this$0;

        public static /* synthetic */ void $r8$lambda$CsgISwFQG2IRYmcbM54PUj2xt00(AnonymousClass2 r0) {
            r0.lambda$onSurfaceTextureAvailable$0();
        }

        public AnonymousClass2(IntroActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onSurfaceTextureAvailable$0() {
            Intro.setPage(IntroActivity.m15489$$Nest$fgetcurrentViewPagerPage(this.this$0));
            Intro.setDate((System.currentTimeMillis() - IntroActivity.m15488$$Nest$fgetcurrentDate(this.this$0)) / 1000.0f);
            Intro.onDrawFrame(0);
            if (IntroActivity.m15491$$Nest$fgeteglThread(this.this$0) != null) goto L5;
            return;
        L5:
            if (IntroActivity.m15491$$Nest$fgeteglThread(this.this$0).isAlive() == true) goto L7;
            return;
        L7:
            if (EGLThread.m15515$$Nest$fgeteglDisplay(IntroActivity.m15491$$Nest$fgeteglThread(this.this$0)) != null) goto L9;
            return;
        L9:
            if (EGLThread.m15516$$Nest$fgeteglSurface(IntroActivity.m15491$$Nest$fgeteglThread(this.this$0)) == null) goto L18;
            EGLThread.m15513$$Nest$fgetegl10(IntroActivity.m15491$$Nest$fgeteglThread(this.this$0)).eglSwapBuffers(EGLThread.m15515$$Nest$fgeteglDisplay(IntroActivity.m15491$$Nest$fgeteglThread(this.this$0)), EGLThread.m15516$$Nest$fgeteglSurface(IntroActivity.m15491$$Nest$fgeteglThread(this.this$0)));     // Catch: Exception -> L12
            return;
        L19:
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r4, int r5, int r6) {
            if (IntroActivity.m15491$$Nest$fgeteglThread(this.this$0) != null) goto L7;
            if (r4 == null) goto L8;
            IntroActivity.m15507$$Nest$fputeglThread(this.this$0, new EGLThread(this.this$0, r4));
            IntroActivity.m15491$$Nest$fgeteglThread(this.this$0).setSurfaceTextureSize(r5, r6);
            IntroActivity.m15491$$Nest$fgeteglThread(this.this$0).postRunnable(new IntroActivity$2$$ExternalSyntheticLambda0(this));
            IntroActivity.m15491$$Nest$fgeteglThread(this.this$0).postRunnable(EGLThread.m15512$$Nest$fgetdrawRunnable(IntroActivity.m15491$$Nest$fgeteglThread(this.this$0)));
            return;
        L8:
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r1) {
            if (IntroActivity.m15491$$Nest$fgeteglThread(this.this$0) == null) goto L7;
            IntroActivity.m15491$$Nest$fgeteglThread(this.this$0).shutdown();
            IntroActivity.m15507$$Nest$fputeglThread(this.this$0, null);
            return true;
        L7:
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
            if (IntroActivity.m15491$$Nest$fgeteglThread(this.this$0) == null) goto L6;
            IntroActivity.m15491$$Nest$fgeteglThread(this.this$0).setSurfaceTextureSize(r2, r3);
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        }
    }

    /* renamed from: org.telegram.ui.IntroActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ IntroActivity this$0;

        public AnonymousClass3(IntroActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r3) {
            if (r3 != 1) goto L6;
            IntroActivity.m15506$$Nest$fputdragging(this.this$0, true);
            IntroActivity r32 = this.this$0;
            IntroActivity.m15510$$Nest$fputstartDragX(r32, IntroActivity.m15502$$Nest$fgetviewPager(r32).getCurrentItem() * IntroActivity.m15502$$Nest$fgetviewPager(this.this$0).getMeasuredWidth());
            return;
        L6:
            if (r3 == 0) goto L10;
            if (r3 == 2) goto L10;
            return;
        L10:
            if (IntroActivity.m15490$$Nest$fgetdragging(this.this$0) == false) goto L13;
            IntroActivity.m15508$$Nest$fputjustEndDragging(this.this$0, true);
            IntroActivity.m15506$$Nest$fputdragging(this.this$0, false);
        L13:
            if (IntroActivity.m15494$$Nest$fgetlastPage(this.this$0) == IntroActivity.m15502$$Nest$fgetviewPager(this.this$0).getCurrentItem()) goto L17;
            IntroActivity r2 = this.this$0;
            IntroActivity.m15509$$Nest$fputlastPage(r2, IntroActivity.m15502$$Nest$fgetviewPager(r2).getCurrentItem());
            return;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r2, float r3, int r4) {
            IntroActivity.m15487$$Nest$fgetbottomPages(this.this$0).setPageOffset(r2, r3);
            float r32 = IntroActivity.m15502$$Nest$fgetviewPager(this.this$0).getMeasuredWidth();
            if (r32 != 0.0f) goto L5;
            return;
        L5:
            Intro.setScrollOffset((((r2 * r32) + r4) - (IntroActivity.m15489$$Nest$fgetcurrentViewPagerPage(this.this$0) * r32)) / r32);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r1) {
            IntroActivity.m15504$$Nest$fputcurrentViewPagerPage(this.this$0, r1);
        }
    }

    /* renamed from: org.telegram.ui.IntroActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends TextView {
        private final CellFlickerDrawable cellFlickerDrawable;
        final /* synthetic */ IntroActivity this$0;

        public AnonymousClass4(IntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            CellFlickerDrawable r12 = new CellFlickerDrawable();
            this.cellFlickerDrawable = r12;
            r12.drawFrame = false;
            r12.repeatProgress = 2.0f;
        }

        @Override // android.view.View
        public void draw(Canvas r2) {
            IntroActivity.m15499$$Nest$fgetstartMessagingButtonBackground(this.this$0).draw(r2);
            super.draw(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r5) {
            super.onDraw(r5);
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            this.cellFlickerDrawable.draw(r5, r0, getMeasuredHeight() / 2.0f, null);
            invalidate();
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r3, int r4) {
            if (View.MeasureSpec.getSize(r3) <= AndroidUtilities.dp(260.0f)) goto L6;
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(320.0f), TLObject.FLAG_30), r4);
            return;
        L6:
            super.onMeasure(r3, r4);
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            IntroActivity.m15499$$Nest$fgetstartMessagingButtonBackground(this.this$0).setBounds(0, 0, r1, r2);
            IntroActivity.m15499$$Nest$fgetstartMessagingButtonBackground(this.this$0).setCornerRadius(Math.min(r1, r2) / 2.0f);
            this.cellFlickerDrawable.setParentWidth(r1);
        }
    }

    /* renamed from: org.telegram.ui.IntroActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements NotificationCenter.NotificationCenterDelegate {
        final /* synthetic */ IntroActivity this$0;
        final /* synthetic */ AlertDialog val$loaderDialog;

        public static /* synthetic */ void $r8$lambda$MA_xNB23EM9Wc4kUfPmSXYOpfJo(AnonymousClass5 r0) {
            r0.lambda$didReceivedNotification$0();
        }

        public AnonymousClass5(IntroActivity r1, AlertDialog r2) {
            this.this$0 = r1;
            this.val$loaderDialog = r2;
        }

        private /* synthetic */ void lambda$didReceivedNotification$0() {
            this.this$0.presentFragment(new LoginActivity().setIntroView(IntroActivity.m15492$$Nest$fgetframeContainerView(this.this$0), IntroActivity.m15498$$Nest$fgetstartMessagingButton(this.this$0)), true);
            IntroActivity.m15505$$Nest$fputdestroyed(this.this$0, true);
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.reloadInterface) goto L6;
            this.val$loaderDialog.dismiss();
            NotificationCenter.getGlobalInstance().removeObserver(this, r1);
            AndroidUtilities.runOnUIThread(new IntroActivity$5$$ExternalSyntheticLambda0(this), 100);
            return;
        }
    }

    public class EGLThread extends DispatchQueue {
        private Runnable drawRunnable;
        private EGL10 egl10;
        private EGLConfig eglConfig;
        private EGLContext eglContext;
        private EGLDisplay eglDisplay;
        private EGLSurface eglSurface;
        private boolean initied;
        private long lastDrawFrame;
        private float maxRefreshRate;
        private SurfaceTexture surfaceTexture;
        private final GenericProvider<Void, Bitmap> telegramMaskProvider;
        private final int[] textures;
        final /* synthetic */ IntroActivity this$0;

        /* renamed from: org.telegram.ui.IntroActivity$EGLThread$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ EGLThread this$1;

            public AnonymousClass1(EGLThread r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (EGLThread.m15517$$Nest$fgetinitied(this.this$1) == false) goto L30;
                long r0 = System.currentTimeMillis();
                if (EGLThread.m15514$$Nest$fgeteglContext(this.this$1).equals(EGLThread.m15513$$Nest$fgetegl10(this.this$1).eglGetCurrentContext()) == false) goto L10;
                if (EGLThread.m15516$$Nest$fgeteglSurface(this.this$1).equals(EGLThread.m15513$$Nest$fgetegl10(this.this$1).eglGetCurrentSurface(12377)) == false) goto L10;
            L15:
                int r2 = (int) Math.min(r0 - EGLThread.m15518$$Nest$fgetlastDrawFrame(this.this$1), 16);
                Intro.setPage(IntroActivity.m15489$$Nest$fgetcurrentViewPagerPage(this.this$1.this$0));
                Intro.setDate((r0 - IntroActivity.m15488$$Nest$fgetcurrentDate(this.this$1.this$0)) / 1000.0f);
                Intro.onDrawFrame(r2);
                EGLThread.m15513$$Nest$fgetegl10(this.this$1).eglSwapBuffers(EGLThread.m15515$$Nest$fgeteglDisplay(this.this$1), EGLThread.m15516$$Nest$fgeteglSurface(this.this$1));
                EGLThread.m15521$$Nest$fputlastDrawFrame(this.this$1, r0);
                float r3 = 0.0f;
                if (EGLThread.m15519$$Nest$fgetmaxRefreshRate(this.this$1) != 0.0f) goto L24;
                float[] r22 = ((WindowManager) ApplicationLoader.applicationContext.getSystemService("window")).getDefaultDisplay().getSupportedRefreshRates();
                int r5 = r22.length;
                int r6 = 0;
            L18:
                if (r6 >= r5) goto L23;
                float r7 = r22[r6];
                if (r7 <= r3) goto L22;
                r3 = r7;
            L22:
                r6 = r6 + 1;
                goto L18
            L23:
                EGLThread.m15522$$Nest$fputmaxRefreshRate(this.this$1, r3);
            L24:
                long r23 = System.currentTimeMillis() - r0;
                EGLThread r02 = this.this$1;
                r02.postRunnable(EGLThread.m15512$$Nest$fgetdrawRunnable(r02), Math.max(((long) (1000.0f / EGLThread.m15519$$Nest$fgetmaxRefreshRate(this.this$1))) - r23, 0));
                return;
            L10:
                if (EGLThread.m15513$$Nest$fgetegl10(this.this$1).eglMakeCurrent(EGLThread.m15515$$Nest$fgeteglDisplay(this.this$1), EGLThread.m15516$$Nest$fgeteglSurface(this.this$1), EGLThread.m15516$$Nest$fgeteglSurface(this.this$1), EGLThread.m15514$$Nest$fgeteglContext(this.this$1)) == true) goto L15;
                if (BuildVars.LOGS_ENABLED == false) goto L29;
                FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(EGLThread.m15513$$Nest$fgetegl10(this.this$1).eglGetError()));
                return;
            L29:
                return;
            }
        }

        /* renamed from: $r8$lambda$7LXCaMmSl-_zxBSp3pQPev_oFIc, reason: not valid java name */
        public static /* synthetic */ Bitmap m15511$r8$lambda$7LXCaMmSl_zxBSp3pQPev_oFIc(Void r6) {
            int r62 = AndroidUtilities.dp(150.0f);
            Bitmap r0 = Bitmap.createBitmap(AndroidUtilities.dp(200.0f), r62, Bitmap.Config.ARGB_8888);
            Canvas r1 = new Canvas(r0);
            r1.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            Paint r2 = new Paint(1);
            r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            r1.drawCircle(r0.getWidth() / 2.0f, r0.getHeight() / 2.0f, r62 / 2.0f, r2);
            return r0;
        }

        public static /* synthetic */ void $r8$lambda$Cv6jEUf0MlHe2pQ5maTGbBPCPfY(EGLThread r0) {
            r0.lambda$shutdown$2();
        }

        public static /* synthetic */ Bitmap $r8$lambda$foVV6ESqdPYksTpmJ2qe1lzbeC4(Void r4) {
            Paint r42 = new Paint(1);
            r42.setColor(ContextCompat.getColor(ApplicationLoader.applicationContext, R.color.ic_background));
            int r0 = AndroidUtilities.dp(150.0f);
            Bitmap r1 = Bitmap.createBitmap(r0, r0, Bitmap.Config.ARGB_8888);
            float r02 = r0 / 2.0f;
            new Canvas(r1).drawCircle(r02, r02, r02, r42);
            return r1;
        }

        /* renamed from: -$$Nest$fgetdrawRunnable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m15512$$Nest$fgetdrawRunnable(EGLThread r0) {
            return r0.drawRunnable;
        }

        /* renamed from: -$$Nest$fgetegl10, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EGL10 m15513$$Nest$fgetegl10(EGLThread r0) {
            return r0.egl10;
        }

        /* renamed from: -$$Nest$fgeteglContext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EGLContext m15514$$Nest$fgeteglContext(EGLThread r0) {
            return r0.eglContext;
        }

        /* renamed from: -$$Nest$fgeteglDisplay, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EGLDisplay m15515$$Nest$fgeteglDisplay(EGLThread r0) {
            return r0.eglDisplay;
        }

        /* renamed from: -$$Nest$fgeteglSurface, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EGLSurface m15516$$Nest$fgeteglSurface(EGLThread r0) {
            return r0.eglSurface;
        }

        /* renamed from: -$$Nest$fgetinitied, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15517$$Nest$fgetinitied(EGLThread r0) {
            return r0.initied;
        }

        /* renamed from: -$$Nest$fgetlastDrawFrame, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m15518$$Nest$fgetlastDrawFrame(EGLThread r2) {
            return r2.lastDrawFrame;
        }

        /* renamed from: -$$Nest$fgetmaxRefreshRate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m15519$$Nest$fgetmaxRefreshRate(EGLThread r0) {
            return r0.maxRefreshRate;
        }

        /* renamed from: -$$Nest$fgettelegramMaskProvider, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GenericProvider m15520$$Nest$fgettelegramMaskProvider(EGLThread r0) {
            return r0.telegramMaskProvider;
        }

        /* renamed from: -$$Nest$fputlastDrawFrame, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15521$$Nest$fputlastDrawFrame(EGLThread r0, long r1) {
            r0.lastDrawFrame = r1;
        }

        /* renamed from: -$$Nest$fputmaxRefreshRate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15522$$Nest$fputmaxRefreshRate(EGLThread r0, float r1) {
            r0.maxRefreshRate = r1;
        }

        /* renamed from: -$$Nest$mloadTexture, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15523$$Nest$mloadTexture(EGLThread r0, int r1, int r2, int r3, boolean r4) {
            r0.loadTexture(r1, r2, r3, r4);
        }

        /* renamed from: -$$Nest$mloadTexture, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15524$$Nest$mloadTexture(EGLThread r0, GenericProvider r1, int r2, boolean r3) {
            r0.loadTexture(r1, r2, r3);
        }

        public EGLThread(IntroActivity r1, SurfaceTexture r2) {
            this.this$0 = r1;
            super("EGLThread");
            this.textures = new int[24];
            this.telegramMaskProvider = new IntroActivity$EGLThread$$ExternalSyntheticLambda1();
            this.drawRunnable = new AnonymousClass1(this);
            this.surfaceTexture = r2;
        }

        private boolean initGL() {
            EGL10 r1 = (EGL10) EGLContext.getEGL();
            this.egl10 = r1;
            EGLDisplay r12 = r1.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.eglDisplay = r12;
            if (r12 != EGL10.EGL_NO_DISPLAY) goto L10;
            if (BuildVars.LOGS_ENABLED == false) goto L7;
            FileLog.e("eglGetDisplay failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L7:
            finish();
            return false;
        L10:
            if (this.egl10.eglInitialize(r12, new int[2]) == false) goto L12;
            int[] r9 = new int[1];
            EGLConfig[] r7 = new EGLConfig[1];
            if (EmuDetector.with(this.this$0.getParentActivity()).detect() == false) goto L20;
            int[] r4 = {12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 24, 12344};
        L22:
            if (this.egl10.eglChooseConfig(this.eglDisplay, r4, r7, 1, r9) == true) goto L29;
            if (BuildVars.LOGS_ENABLED == false) goto L26;
            FileLog.e("eglChooseConfig failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L26:
            finish();
            return false;
        L29:
            if (r9[0] <= 0) goto L61;
            EGLConfig r42 = r7[0];
            this.eglConfig = r42;
            EGLContext r43 = this.egl10.eglCreateContext(this.eglDisplay, r42, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
            this.eglContext = r43;
            if (r43 != null) goto L38;
            if (BuildVars.LOGS_ENABLED == false) goto L35;
            FileLog.e("eglCreateContext failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L35:
            finish();
            return false;
        L38:
            if (this.surfaceTexture == null) goto L58;
            EGLSurface r44 = this.egl10.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, this.surfaceTexture, null);
            this.eglSurface = r44;
            if (r44 == null) goto L54;
            if (r44 == EGL10.EGL_NO_SURFACE) goto L54;
            if (this.egl10.eglMakeCurrent(this.eglDisplay, r44, r44, this.eglContext) == false) goto L47;
            GLES20.glGenTextures(23, this.textures, 0);
            loadTexture(R.drawable.intro_fast_arrow_shadow, 0);
            loadTexture(R.drawable.intro_fast_arrow, 1);
            loadTexture(R.drawable.intro_fast_body, 2);
            loadTexture(R.drawable.intro_fast_spiral, 3);
            loadTexture(R.drawable.intro_ic_bubble_dot, 4);
            loadTexture(R.drawable.intro_ic_bubble, 5);
            loadTexture(R.drawable.intro_ic_cam_lens, 6);
            loadTexture(R.drawable.intro_ic_cam, 7);
            loadTexture(R.drawable.intro_ic_pencil, 8);
            loadTexture(R.drawable.intro_ic_pin, 9);
            loadTexture(R.drawable.intro_ic_smile_eye, 10);
            loadTexture(R.drawable.intro_ic_smile, 11);
            loadTexture(R.drawable.intro_ic_videocam, 12);
            loadTexture(R.drawable.intro_knot_down, 13);
            loadTexture(R.drawable.intro_knot_up, 14);
            loadTexture(R.drawable.intro_powerful_infinity_white, 15);
            loadTexture(R.drawable.intro_powerful_infinity, 16);
            loadTexture(R.drawable.intro_powerful_mask, 17, Theme.getColor(Theme.key_windowBackgroundWhite), false);
            loadTexture(R.drawable.intro_powerful_star, 18);
            loadTexture(R.drawable.intro_private_door, 19);
            loadTexture(R.drawable.intro_private_screw, 20);
            loadTexture(R.drawable.intro_etg_arrow, 21);
            loadTexture(new IntroActivity$EGLThread$$ExternalSyntheticLambda2(), 22);
            loadTexture(this.telegramMaskProvider, 23);
            updateTelegramTextures();
            updatePowerfulTextures();
            int[] r45 = this.textures;
            Intro.setPrivateTextures(r45[19], r45[20]);
            int[] r46 = this.textures;
            Intro.setFreeTextures(r46[14], r46[13]);
            int[] r47 = this.textures;
            Intro.setFastTextures(r47[2], r47[3], r47[1], r47[0]);
            int[] r2 = this.textures;
            Intro.setIcTextures(r2[4], r2[5], r2[6], r2[7], r2[8], r2[9], r2[10], r2[11], r2[12]);
            Intro.onSurfaceCreated();
            IntroActivity.m15503$$Nest$fputcurrentDate(this.this$0, System.currentTimeMillis() - 1000);
            return true;
        L47:
            if (BuildVars.LOGS_ENABLED == false) goto L49;
            FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L49:
            finish();
            return false;
        L54:
            if (BuildVars.LOGS_ENABLED == false) goto L56;
            FileLog.e("createWindowSurface failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L56:
            finish();
            return false;
        L58:
            finish();
            return false;
        L61:
            if (BuildVars.LOGS_ENABLED == false) goto L63;
            FileLog.e("eglConfig not initialized");
        L63:
            finish();
            return false;
        L20:
            r4 = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 24, 12326, 0, 12338, 1, 12337, 2, 12344};
            goto L22
        L12:
            if (BuildVars.LOGS_ENABLED == false) goto L14;
            FileLog.e("eglInitialize failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L14:
            finish();
            return false;
        }

        private /* synthetic */ void lambda$shutdown$2() {
            finish();
            Looper r0 = Looper.myLooper();
            if (r0 == null) goto L6;
            r0.quit();
            return;
        }

        private void loadTexture(int r2, int r3) {
            loadTexture(r2, r3, 0, false);
        }

        private void loadTexture(int r5, int r6, int r7, boolean r8) {
            Drawable r52 = this.this$0.getParentActivity().getResources().getDrawable(r5);
            if ((r52 instanceof BitmapDrawable) == false) goto L12;
            if (r8 == false) goto L6;
            GLES20.glDeleteTextures(1, this.textures, r6);
            GLES20.glGenTextures(1, this.textures, r6);
        L6:
            Bitmap r53 = ((BitmapDrawable) r52).getBitmap();
            GLES20.glBindTexture(3553, this.textures[r6]);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            if (r7 == 0) goto L10;
            Bitmap r82 = Bitmap.createBitmap(r53.getWidth(), r53.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas r0 = new Canvas(r82);
            Paint r1 = new Paint(5);
            r1.setColorFilter(new PorterDuffColorFilter(r7, PorterDuff.Mode.SRC_IN));
            r0.drawBitmap(r53, 0.0f, 0.0f, r1);
            GLUtils.texImage2D(3553, 0, r82, 0);
            r82.recycle();
            return;
        L10:
            GLUtils.texImage2D(3553, 0, r53, 0);
            return;
        }

        private void loadTexture(GenericProvider<Void, Bitmap> r2, int r3) {
            loadTexture(r2, r3, false);
        }

        private void loadTexture(GenericProvider<Void, Bitmap> r2, int r3, boolean r4) {
            if (r4 == false) goto L4;
            GLES20.glDeleteTextures(1, this.textures, r3);
            GLES20.glGenTextures(1, this.textures, r3);
        L4:
            Bitmap r22 = r2.provide(null);
            GLES20.glBindTexture(3553, this.textures[r3]);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLUtils.texImage2D(3553, 0, r22, 0);
            r22.recycle();
        }

        public void finish() {
            if (this.eglSurface == null) goto L5;
            EGL10 r0 = this.egl10;
            EGLDisplay r2 = this.eglDisplay;
            EGLSurface r3 = EGL10.EGL_NO_SURFACE;
            r0.eglMakeCurrent(r2, r3, r3, EGL10.EGL_NO_CONTEXT);
            this.egl10.eglDestroySurface(this.eglDisplay, this.eglSurface);
            this.eglSurface = null;
        L5:
            EGLContext r02 = this.eglContext;
            if (r02 == null) goto L8;
            this.egl10.eglDestroyContext(this.eglDisplay, r02);
            this.eglContext = null;
        L8:
            EGLDisplay r03 = this.eglDisplay;
            if (r03 == null) goto L12;
            this.egl10.eglTerminate(r03);
            this.eglDisplay = null;
            return;
        }

        @Override // org.telegram.messenger.DispatchQueue, java.lang.Thread, java.lang.Runnable
        public void run() {
            this.initied = initGL();
            super.run();
        }

        public void setSurfaceTextureSize(int r3, int r4) {
            Intro.onSurfaceChanged(r3, r4, Math.min(r3 / 150.0f, r4 / 150.0f), 0);
        }

        public void shutdown() {
            postRunnable(new IntroActivity$EGLThread$$ExternalSyntheticLambda0(this));
        }

        public void updatePowerfulTextures() {
            int[] r4 = this.textures;
            Intro.setPowerfulTextures(r4[17], r4[18], r4[16], r4[15]);
        }

        public void updateTelegramTextures() {
            int[] r3 = this.textures;
            Intro.setTelegramTextures(r3[22], r3[21], r3[23]);
        }
    }

    public class IntroAdapter extends PagerAdapter {
        final /* synthetic */ IntroActivity this$0;

        /* renamed from: org.telegram.ui.IntroActivity$IntroAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            final /* synthetic */ IntroAdapter this$1;
            final /* synthetic */ TextView val$headerTextView;
            final /* synthetic */ TextView val$messageTextView;

            public AnonymousClass1(IntroAdapter r1, Context r2, TextView r3, TextView r4) {
                this.this$1 = r1;
                this.val$headerTextView = r3;
                this.val$messageTextView = r4;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
                int r62 = ((((((r6 - r4) / 4) * 3) - AndroidUtilities.dp(275.0f)) / 2) + AndroidUtilities.dp(150.0f)) + AndroidUtilities.dp(25.0f);
                int r32 = AndroidUtilities.dp(18.0f);
                TextView r42 = this.val$headerTextView;
                r42.layout(r32, r62, r42.getMeasuredWidth() + r32, this.val$headerTextView.getMeasuredHeight() + r62);
                int r63 = (r62 + ((int) this.val$headerTextView.getTextSize())) + AndroidUtilities.dp(18.0f);
                int r22 = AndroidUtilities.dp(16.0f);
                TextView r33 = this.val$messageTextView;
                r33.layout(r22, r63, r33.getMeasuredWidth() + r22, this.val$messageTextView.getMeasuredHeight() + r63);
            }
        }

        private IntroAdapter(IntroActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ IntroAdapter(IntroActivity r1, IntroActivityIA r2) {
            this(r1);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup r1, int r2, Object r3) {
            r1.removeView((View) r3);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return IntroActivity.m15501$$Nest$fgettitles(this.this$0).length;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup r14, int r15) {
            TextView r0 = new TextView(r14.getContext());
            r0.setTag(IntroActivity.m15496$$Nest$fgetpagerHeaderTag(this.this$0));
            TextView r1 = new TextView(r14.getContext());
            r1.setTag(IntroActivity.m15497$$Nest$fgetpagerMessageTag(this.this$0));
            AnonymousClass1 r2 = new AnonymousClass1(this, r14.getContext(), r0, r1);
            int r3 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setTextColor(Theme.getColor(r3));
            r0.setTextSize(1, 26.0f);
            r0.setTypeface(AndroidUtilities.bold());
            r0.setGravity(17);
            r2.addView(r0, LayoutHelper.createFrame(-1, -2.0f, 51, 18.0f, 244.0f, 18.0f, 0.0f));
            r1.setTextColor(Theme.getColor(r3));
            r1.setTextSize(1, 15.0f);
            r1.setLineSpacing(AndroidUtilities.dpf2(2.33f), 1.0f);
            r1.setTypeface(AndroidUtilities.regular());
            r1.setGravity(17);
            r2.addView(r1, LayoutHelper.createFrame(-1, -2.0f, 51, 16.0f, 286.0f, 16.0f, 0.0f));
            r14.addView(r2, 0);
            r0.setText(IntroActivity.m15501$$Nest$fgettitles(this.this$0)[r15]);
            r1.setText(AndroidUtilities.replaceTags(IntroActivity.m15495$$Nest$fgetmessages(this.this$0)[r15]));
            return r2;
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
            IntroActivity.m15487$$Nest$fgetbottomPages(this.this$0).setCurrentPage(r2);
            IntroActivity.m15504$$Nest$fputcurrentViewPagerPage(this.this$0, r2);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void unregisterDataSetObserver(DataSetObserver r1) {
            if (r1 == null) goto L5;
            super.unregisterDataSetObserver(r1);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$7mhmq2DEF5GFqShURjzkX3wrnME(IntroActivity r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$Ph9RDOe1b03roB4G_OcewPD5Jw8(IntroActivity r0, String r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$checkContinueText$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$VTBlJsUBe6sXwBO17tv2YcjbofI(IntroActivity r0, TLRPC.LangPackString r1, String r2) {
        r0.lambda$checkContinueText$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$XaOIspUqdyttgCL4EuhmefpuD8w(IntroActivity r0) {
        r0.lambda$updateColors$6();
    }

    /* renamed from: $r8$lambda$_IW-tx742GJi8iNXa62p1x8AzjE, reason: not valid java name */
    public static /* synthetic */ void m15485$r8$lambda$_IWtx742GJi8iNXa62p1x8AzjE(IntroActivity r0) {
        r0.lambda$getThemeDescriptions$5();
    }

    public static /* synthetic */ void $r8$lambda$gSABRftPc9iDBGKYH71lJNwVLw0(IntroActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    /* renamed from: $r8$lambda$sikt0l5qi3-78mS1dnJIL6IhczE, reason: not valid java name */
    public static /* synthetic */ void m15486$r8$lambda$sikt0l5qi378mS1dnJIL6IhczE(IntroActivity r0, RLottieImageView r1, View r2) {
        r0.lambda$createView$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetbottomPages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BottomPagesView m15487$$Nest$fgetbottomPages(IntroActivity r0) {
        return r0.bottomPages;
    }

    /* renamed from: -$$Nest$fgetcurrentDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m15488$$Nest$fgetcurrentDate(IntroActivity r2) {
        return r2.currentDate;
    }

    /* renamed from: -$$Nest$fgetcurrentViewPagerPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15489$$Nest$fgetcurrentViewPagerPage(IntroActivity r0) {
        return r0.currentViewPagerPage;
    }

    /* renamed from: -$$Nest$fgetdragging, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15490$$Nest$fgetdragging(IntroActivity r0) {
        return r0.dragging;
    }

    /* renamed from: -$$Nest$fgeteglThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EGLThread m15491$$Nest$fgeteglThread(IntroActivity r0) {
        return r0.eglThread;
    }

    /* renamed from: -$$Nest$fgetframeContainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m15492$$Nest$fgetframeContainerView(IntroActivity r0) {
        return r0.frameContainerView;
    }

    /* renamed from: -$$Nest$fgetframeLayout2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m15493$$Nest$fgetframeLayout2(IntroActivity r0) {
        return r0.frameLayout2;
    }

    /* renamed from: -$$Nest$fgetlastPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15494$$Nest$fgetlastPage(IntroActivity r0) {
        return r0.lastPage;
    }

    /* renamed from: -$$Nest$fgetmessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String[] m15495$$Nest$fgetmessages(IntroActivity r0) {
        return r0.messages;
    }

    /* renamed from: -$$Nest$fgetpagerHeaderTag, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m15496$$Nest$fgetpagerHeaderTag(IntroActivity r0) {
        return r0.pagerHeaderTag;
    }

    /* renamed from: -$$Nest$fgetpagerMessageTag, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m15497$$Nest$fgetpagerMessageTag(IntroActivity r0) {
        return r0.pagerMessageTag;
    }

    /* renamed from: -$$Nest$fgetstartMessagingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m15498$$Nest$fgetstartMessagingButton(IntroActivity r0) {
        return r0.startMessagingButton;
    }

    /* renamed from: -$$Nest$fgetstartMessagingButtonBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GradientDrawable m15499$$Nest$fgetstartMessagingButtonBackground(IntroActivity r0) {
        return r0.startMessagingButtonBackground;
    }

    /* renamed from: -$$Nest$fgetswitchLanguageTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m15500$$Nest$fgetswitchLanguageTextView(IntroActivity r0) {
        return r0.switchLanguageTextView;
    }

    /* renamed from: -$$Nest$fgettitles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence[] m15501$$Nest$fgettitles(IntroActivity r0) {
        return r0.titles;
    }

    /* renamed from: -$$Nest$fgetviewPager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPager m15502$$Nest$fgetviewPager(IntroActivity r0) {
        return r0.viewPager;
    }

    /* renamed from: -$$Nest$fputcurrentDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15503$$Nest$fputcurrentDate(IntroActivity r0, long r1) {
        r0.currentDate = r1;
    }

    /* renamed from: -$$Nest$fputcurrentViewPagerPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15504$$Nest$fputcurrentViewPagerPage(IntroActivity r0, int r1) {
        r0.currentViewPagerPage = r1;
    }

    /* renamed from: -$$Nest$fputdestroyed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15505$$Nest$fputdestroyed(IntroActivity r0, boolean r1) {
        r0.destroyed = r1;
    }

    /* renamed from: -$$Nest$fputdragging, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15506$$Nest$fputdragging(IntroActivity r0, boolean r1) {
        r0.dragging = r1;
    }

    /* renamed from: -$$Nest$fputeglThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15507$$Nest$fputeglThread(IntroActivity r0, EGLThread r1) {
        r0.eglThread = r1;
    }

    /* renamed from: -$$Nest$fputjustEndDragging, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15508$$Nest$fputjustEndDragging(IntroActivity r0, boolean r1) {
        r0.justEndDragging = r1;
    }

    /* renamed from: -$$Nest$fputlastPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15509$$Nest$fputlastPage(IntroActivity r0, int r1) {
        r0.lastPage = r1;
    }

    /* renamed from: -$$Nest$fputstartDragX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15510$$Nest$fputstartDragX(IntroActivity r0, int r1) {
        r0.startDragX = r1;
    }

    public IntroActivity() {
        this.pagerHeaderTag = new Object();
        this.pagerMessageTag = new Object();
        this.currentAccount = UserConfig.selectedAccount;
        this.lastPage = 0;
        this.justCreated = false;
        this.startPressed = false;
    }

    private void checkContinueText() {
        LocaleController.LocaleInfo r0 = LocaleController.getInstance().getCurrentLocaleInfo();
        String r1 = MessagesController.getInstance(this.currentAccount).suggestedLangCode;
        if (r1 != null) goto L5;
    L10:
        r1 = LocaleController.getInstance().getSystemDefaultLocale().getLanguage();
        if (r1 != null) goto L13;
        r1 = "en";
    L13:
        int r5 = 0;
        if (r1.contains("-") == false) goto L16;
        String r4 = r1.split("-")[0];
    L17:
        String r6 = LocaleController.getLocaleAlias(r4);
        LocaleController.LocaleInfo r7 = null;
        LocaleController.LocaleInfo r8 = null;
    L19:
        if (r5 >= LocaleController.getInstance().languages.size()) goto L34;
        LocaleController.LocaleInfo r9 = LocaleController.getInstance().languages.get(r5);
        if (r9.shortName.equals("en") == false) goto L24;
        r7 = r9;
    L24:
        if (r9.shortName.replace("_", "-").equals(r1) == false) goto L26;
    L29:
        r8 = r9;
    L30:
        if (r7 == null) goto L33;
        if (r8 != null) goto L34;
    L33:
        r5 = r5 + 1;
        goto L19
    L26:
        if (r9.shortName.equals(r4) == true) goto L29;
        if (r9.shortName.equals(r6) == false) goto L30;
    L34:
        if (r7 == null) goto L48;
        if (r8 == null) goto L49;
        if (r7 == r8) goto L50;
        TLRPC.TL_langpack_getStrings r2 = new TLRPC.TL_langpack_getStrings();
        if (r8 == r0) goto L41;
        r2.lang_code = r8.getLangCode();
        this.localeInfo = r8;
    L42:
        r2.keys.add("ContinueOnThisLanguage");
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new IntroActivity$$ExternalSyntheticLambda4(this, r1), 8);
        return;
    L41:
        r2.lang_code = r7.getLangCode();
        this.localeInfo = r7;
        goto L42
    L50:
        return;
    L49:
        return;
    L48:
        return;
    L16:
        r4 = r1;
        goto L17
    L5:
        if (r1.equals("en") == false) goto L13;
        if (LocaleController.getInstance().getSystemDefaultLocale().getLanguage() == null) goto L13;
        if (LocaleController.getInstance().getSystemDefaultLocale().getLanguage().equals("en") == true) goto L13;
        goto L10
    }

    private /* synthetic */ void lambda$checkContinueText$3(TLRPC.LangPackString r2, String r3) {
        if (this.destroyed == true) goto L6;
        this.switchLanguageTextView.setText(r2.value);
        MessagesController.getGlobalMainSettings().edit().putString("language_showed2", r3.toLowerCase()).apply();
        return;
    }

    private /* synthetic */ void lambda$checkContinueText$4(String r1, TLObject r2, TLRPC.TL_error r3) {
        if ((r2 instanceof Vector) == false) goto L11;
        Vector r22 = (Vector) r2;
        if (r22.objects.isEmpty() == true) goto L13;
        TLRPC.LangPackString r23 = (TLRPC.LangPackString) r22.objects.get(0);
        if ((r23 instanceof TLRPC.TL_langPackString) == false) goto L12;
        AndroidUtilities.runOnUIThread(new IntroActivity$$ExternalSyntheticLambda5(this, r23, r1));
        return;
    L12:
        return;
    L13:
        return;
    }

    private /* synthetic */ void lambda$createView$0(RLottieImageView r10, View r11) {
        if (DialogsActivity.switchingTheme == false) goto L5;
        return;
    L5:
        DialogsActivity.switchingTheme = true;
        boolean r0 = Theme.isCurrentThemeDark();
        boolean r1 = !r0;
        if (r0 == true) goto L9;
        Theme.ThemeInfo r2 = Theme.getTheme("Night");
    L8:
        Theme.ThemeInfo r3 = r2;
        Theme.selectedAutoNightType = 0;
        Theme.saveAutoNightThemeConfig();
        Theme.cancelAutoNightThemeCallbacks();
        RLottieDrawable r9 = this.darkThemeDrawable;
        if (r0 == true) goto L13;
        int r4 = r9.getFramesCount() - 1;
    L14:
        r9.setCustomEndFrame(r4);
        r10.playAnimation();
        int[] r5 = new int[2];
        r10.getLocationInWindow(r5);
        r5[0] = r5[0] + (r10.getMeasuredWidth() / 2);
        r5[1] = r5[1] + (r10.getMeasuredHeight() / 2);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme, new Object[]{r3, Boolean.FALSE, r5, -1, Boolean.valueOf(r1), r10});
        if (r0 == true) goto L17;
        int r92 = R.string.AccDescrSwitchToDayTheme;
    L18:
        r10.setContentDescription(LocaleController.getString(r92));
        return;
    L17:
        r92 = R.string.AccDescrSwitchToNightTheme;
        goto L18
    L13:
        r4 = 0;
        goto L14
    L9:
        r2 = Theme.getTheme("Blue");
        goto L8
    }

    private /* synthetic */ void lambda$createView$1(View r4) {
        if (this.startPressed == false) goto L5;
        return;
    L5:
        this.startPressed = true;
        presentFragment(new LoginActivity().setIntroView(this.frameContainerView, this.startMessagingButton), true);
        this.destroyed = true;
    }

    private /* synthetic */ void lambda$createView$2(View r5) {
        if (this.startPressed == false) goto L5;
        return;
    L5:
        if (this.localeInfo == null) goto L10;
        this.startPressed = true;
        AlertDialog r1 = new AlertDialog(r5.getContext(), 3);
        r1.setCanCancel(false);
        r1.showDelayed(1000);
        NotificationCenter.getGlobalInstance().addObserver(new AnonymousClass5(this, r1), NotificationCenter.reloadInterface);
        LocaleController.getInstance().applyLanguage(this.localeInfo, true, false, this.currentAccount);
        return;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$5() {
        updateColors(true);
    }

    private /* synthetic */ void lambda$updateColors$6() {
        EGLThread r0 = this.eglThread;
        int r1 = R.drawable.intro_powerful_mask;
        int r2 = Theme.key_windowBackgroundWhite;
        EGLThread.m15523$$Nest$mloadTexture(r0, r1, 17, Theme.getColor(r2), true);
        this.eglThread.updatePowerfulTextures();
        EGLThread r02 = this.eglThread;
        EGLThread.m15524$$Nest$mloadTexture(r02, EGLThread.m15520$$Nest$fgettelegramMaskProvider(r02), 23, true);
        this.eglThread.updateTelegramTextures();
        Intro.setBackgroundColor(Theme.getColor(r2));
    }

    private void updateColors(boolean r7) {
        GradientDrawable r0 = this.startMessagingButtonBackground;
        int r1 = Theme.key_featuredStickers_addButton;
        r0.setColors(new int[]{getThemedColor(r1), getThemedColor(Theme.key_featuredStickers_addButton2)});
        View r02 = this.fragmentView;
        int r2 = Theme.key_windowBackgroundWhite;
        r02.setBackgroundColor(Theme.getColor(r2));
        this.switchLanguageTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
        this.startMessagingButton.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        int r5 = 0;
        this.startMessagingButton.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(24.0f), 0, Theme.getColor(Theme.key_featuredStickers_addButtonPressed)));
        this.darkThemeDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r1), PorterDuff.Mode.SRC_IN));
        this.bottomPages.invalidate();
        if (r7 == false) goto L11;
        EGLThread r72 = this.eglThread;
        if (r72 == null) goto L8;
        r72.postRunnable(new IntroActivity$$ExternalSyntheticLambda6(this));
    L8:
        if (r5 >= this.viewPager.getChildCount()) goto L10;
        View r73 = this.viewPager.getChildAt(r5);
        TextView r03 = (TextView) r73.findViewWithTag(this.pagerHeaderTag);
        int r12 = Theme.key_windowBackgroundWhiteBlackText;
        r03.setTextColor(Theme.getColor(r12));
        ((TextView) r73.findViewWithTag(this.pagerMessageTag)).setTextColor(Theme.getColor(r12));
        r5 = r5 + 1;
        goto L8
    L10:
        return;
    L11:
        Intro.setBackgroundColor(Theme.getColor(r2));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r20) {
        this.actionBar.setAddToContainer(false);
        ScrollView r2 = new ScrollView(r20);
        r2.setFillViewport(true);
        RLottieImageView r5 = new RLottieImageView(r20);
        FrameLayout r6 = new FrameLayout(r20);
        r6.addView(r5, LayoutHelper.createFrame(28, 28, 17));
        AnonymousClass1 r7 = new AnonymousClass1(this, r20, r6, 4);
        this.frameContainerView = r7;
        r2.addView(r7, LayoutHelper.createScroll(-1, -2, 51));
        RLottieDrawable r12 = new RLottieDrawable(R.raw.sun, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
        this.darkThemeDrawable = r12;
        r12.setPlayInDirectionOfCustomEndFrame(true);
        this.darkThemeDrawable.beginApplyLayerColors();
        this.darkThemeDrawable.commitApplyLayerColors();
        RLottieDrawable r72 = this.darkThemeDrawable;
        if (Theme.getCurrentTheme().isDark() == false) goto L5;
        int r9 = this.darkThemeDrawable.getFramesCount() - 1;
    L6:
        r72.setCustomEndFrame(r9);
        RLottieDrawable r73 = this.darkThemeDrawable;
        if (Theme.getCurrentTheme().isDark() == false) goto L9;
        int r92 = this.darkThemeDrawable.getFramesCount() - 1;
    L10:
        r73.setCurrentFrame(r92, false);
        if (Theme.getCurrentTheme().isDark() == false) goto L13;
        int r74 = R.string.AccDescrSwitchToDayTheme;
    L14:
        r5.setContentDescription(LocaleController.getString(r74));
        r5.setAnimation(this.darkThemeDrawable);
        r6.setOnClickListener(new IntroActivity$$ExternalSyntheticLambda1(this, r5));
        FrameLayout r52 = new FrameLayout(r20);
        this.frameLayout2 = r52;
        this.frameContainerView.addView(r52, LayoutHelper.createFrame(-1, -2.0f, 51, 0.0f, 78.0f, 0.0f, 0.0f));
        TextureView r53 = new TextureView(r20);
        this.frameLayout2.addView(r53, LayoutHelper.createFrame(200, 150, 17));
        r53.setSurfaceTextureListener(new AnonymousClass2(this));
        ViewPager r54 = new ViewPager(r20);
        this.viewPager = r54;
        r54.setAdapter(new IntroAdapter(this, null));
        this.viewPager.setPageMargin(0);
        this.viewPager.setOffscreenPageLimit(1);
        this.frameContainerView.addView(this.viewPager, LayoutHelper.createFrame(-1, -1.0f));
        this.viewPager.addOnPageChangeListener(new AnonymousClass3(this));
        this.startMessagingButtonBackground = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, null);
        AnonymousClass4 r55 = new AnonymousClass4(this, r20);
        this.startMessagingButton = r55;
        ScaleStateListAnimator.apply(r55, 0.02f, 1.2f);
        this.startMessagingButton.setText(LocaleController.getString(R.string.StartMessaging));
        this.startMessagingButton.setGravity(17);
        this.startMessagingButton.setTypeface(AndroidUtilities.bold());
        this.startMessagingButton.setTextSize(1, 15.0f);
        this.startMessagingButton.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        this.frameContainerView.addView(this.startMessagingButton, LayoutHelper.createFrame(-1, 48.0f, 81, 16.0f, 0.0f, 16.0f, 76.0f));
        this.startMessagingButton.setOnClickListener(new IntroActivity$$ExternalSyntheticLambda2(this));
        BottomPagesView r56 = new BottomPagesView(r20, this.viewPager, 6);
        this.bottomPages = r56;
        this.frameContainerView.addView(r56, LayoutHelper.createFrame(66, 5.0f, 49, 0.0f, 350.0f, 0.0f, 0.0f));
        TextView r57 = new TextView(r20);
        this.switchLanguageTextView = r57;
        r57.setGravity(17);
        this.switchLanguageTextView.setTextSize(1, 16.0f);
        this.frameContainerView.addView(this.switchLanguageTextView, LayoutHelper.createFrame(-2, 30.0f, 81, 0.0f, 0.0f, 0.0f, 20.0f));
        this.switchLanguageTextView.setOnClickListener(new IntroActivity$$ExternalSyntheticLambda3(this));
        this.frameContainerView.addView(r6, LayoutHelper.createFrame(64, 64.0f, 53, 0.0f, 4.0f, 4.0f, 0.0f));
        this.fragmentView = r2;
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.suggestedLangpack);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.configLoaded);
        ConnectionsManager.getInstance(this.currentAccount).updateDcSettings();
        LocaleController.getInstance().loadRemoteLanguages(this.currentAccount);
        checkContinueText();
        this.justCreated = true;
        updateColors(false);
        return this.fragmentView;
    L13:
        r74 = R.string.AccDescrSwitchToNightTheme;
        goto L14
    L9:
        r92 = 0;
        goto L10
    L5:
        r9 = 0;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.suggestedLangpack) goto L5;
    L8:
        checkContinueText();
        return;
    L5:
        if (r1 == NotificationCenter.configLoaded) goto L8;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getNavigationBarColor() {
        return getThemedColor(Theme.key_windowBackgroundWhite);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        return SimpleThemeDescription.createThemeDescriptions(new IntroActivity$$ExternalSyntheticLambda0(this), new int[]{Theme.key_windowBackgroundWhite, Theme.key_windowBackgroundWhiteBlueText4, Theme.key_chats_actionBackground, Theme.key_chats_actionPressedBackground, Theme.key_featuredStickers_buttonText, Theme.key_windowBackgroundWhiteBlackText});
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean hasForceLightStatusBar() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, null, true)) <= 0.699999988079071d) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public AnimatorSet onCustomTransitionAnimation(boolean r2, Runnable r3) {
        if (this.isOnLogout == false) goto L6;
        AnimatorSet r1 = new AnimatorSet().setDuration(50);
        r1.playTogether(new Animator[]{ValueAnimator.ofFloat(new float[0])});
        return r1;
    L6:
        return null;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        MessagesController.getGlobalMainSettings().edit().putLong("intro_crashed_time", System.currentTimeMillis()).apply();
        this.titles = new CharSequence[]{LocaleController.getString(R.string.AppName), LocaleController.getString(R.string.Page2Title), LocaleController.getString(R.string.Page3Title), LocaleController.getString(R.string.Page5Title), LocaleController.getString(R.string.Page4Title), LocaleController.getString(R.string.Page6Title)};
        this.messages = new String[]{LocaleController.getString(R.string.Page1Message), LocaleController.getString(R.string.Page2Message), LocaleController.getString(R.string.Page3Message), LocaleController.getString(R.string.Page5Message), LocaleController.getString(R.string.Page4Message), LocaleController.getString(R.string.Page6Message)};
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        this.destroyed = true;
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.suggestedLangpack);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.configLoaded);
        MessagesController.getGlobalMainSettings().edit().putLong("intro_crashed_time", 0).apply();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        AndroidUtilities.unlockOrientation(getParentActivity());
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    @SuppressLint({"SourceLockedOrientationActivity"})
    public void onResume() {
        super.onResume();
        if (this.justCreated == false) goto L9;
        boolean r0 = LocaleController.isRTL;
        ViewPager r1 = this.viewPager;
        if (r0 == false) goto L7;
        r1.setCurrentItem(6);
        this.lastPage = 6;
    L8:
        this.justCreated = false;
        goto L9
    L7:
        r1.setCurrentItem(0);
        this.lastPage = 0;
    L9:
        AndroidUtilities.lockOrientation(getParentActivity(), 1);
    }

    public IntroActivity setOnLogout() {
        this.isOnLogout = true;
        return this;
    }
}
