package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.google.android.gms.cast.MediaError;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ChatThemeController;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.TelegramQRCodeWriter;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.wallpaper.WallpaperBitmapHolder;
import org.telegram.tgnet.ResultCallback;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.EdgeToEdgeSupportMode;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.ActionBar.theme.ThemeKey;
import org.telegram.ui.CameraScanActivity;
import org.telegram.ui.Cells.SettingsSearchCell;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatThemeBottomSheet;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.HideViewAfterAnimation;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StaticLayoutEx;
import org.telegram.ui.Components.ThemeSmallPreviewView;

/* loaded from: classes6.dex */
public class QrActivity extends BaseFragment {
    private static List<EmojiThemes> cachedThemes;
    private static boolean firstOpen;
    private static final ArrayMap<String, int[]> qrColorsMap = null;
    private BackupImageView avatarImageView;
    private View backgroundView;
    private long chatId;
    private ImageView closeImageView;
    private MotionBackgroundDrawable currMotionDrawable;
    private EmojiThemes currentTheme;
    private final ArrayMap<String, Bitmap> emojiThemeDarkIcons;
    private Bitmap emojiThemeIcon;
    private final EmojiThemes homeTheme;
    private Insets insets;
    private boolean isCurrentThemeDark;
    private boolean isFragmentViewPortrait;
    private RLottieImageView logoImageView;
    private Bitmap logoOptimal;
    private final Rect logoRect;
    private ValueAnimator patternAlphaAnimator;
    private ValueAnimator patternIntensityAnimator;
    private MotionBackgroundDrawable prevMotionDrawable;
    private int[] prevQrColors;
    private int prevSystemUiVisibility;
    private QrView qrView;
    private final ThemeResourcesProvider resourcesProvider;
    private int selectedPosition;
    private MotionBackgroundDrawable tempMotionDrawable;
    private FrameLayout themeLayout;
    private ThemeListViewController themesViewController;
    private long userId;

    /* renamed from: org.telegram.ui.QrActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        private boolean ignoreLayout;
        final /* synthetic */ QrActivity this$0;

        public AnonymousClass1(QrActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            super.dispatchTouchEvent(r1);
            return true;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r7, int r8, int r9, int r10, int r11) {
            int r72 = getMeasuredWidth();
            int r82 = getMeasuredHeight();
            int r92 = 0;
            if (r72 >= r82) goto L5;
            boolean r102 = true;
        L7:
            if (QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).getVisibility() == 0) goto L9;
            QrActivity.m18405$$Nest$fgetbackgroundView(this.this$0).setClipBounds(null);
        L14:
            QrActivity.m18405$$Nest$fgetbackgroundView(this.this$0).layout(0, 0, r72, r82);
            if (QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).getVisibility() != 0) goto L17;
            r92 = QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).getMeasuredHeight();
        L17:
            QrActivity r112 = this.this$0;
            if (r102 == false) goto L20;
            int r113 = (r72 - QrActivity.m18416$$Nest$fgetqrView(r112).getMeasuredWidth()) / 2;
        L21:
            QrActivity r0 = this.this$0;
            if (r102 == false) goto L24;
            int r02 = (QrActivity.m18409$$Nest$fgetinsets(r0).top + (((((r82 - r92) - QrActivity.m18409$$Nest$fgetinsets(this.this$0).top) - QrActivity.m18416$$Nest$fgetqrView(this.this$0).getMeasuredHeight()) - AndroidUtilities.dp(48.0f)) / 2)) + AndroidUtilities.dp(52.0f);
        L25:
            QrActivity.m18416$$Nest$fgetqrView(this.this$0).layout(r113, r02, QrActivity.m18416$$Nest$fgetqrView(this.this$0).getMeasuredWidth() + r113, QrActivity.m18416$$Nest$fgetqrView(this.this$0).getMeasuredHeight() + r02);
            if (r102 == false) goto L29;
            int r2 = (r72 - QrActivity.m18404$$Nest$fgetavatarImageView(this.this$0).getMeasuredWidth()) / 2;
            int r1 = r02 - AndroidUtilities.dp(48.0f);
            QrActivity.m18404$$Nest$fgetavatarImageView(this.this$0).layout(r2, r1, QrActivity.m18404$$Nest$fgetavatarImageView(this.this$0).getMeasuredWidth() + r2, QrActivity.m18404$$Nest$fgetavatarImageView(this.this$0).getMeasuredHeight() + r1);
        L29:
            if (QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).getVisibility() != 0) goto L34;
            QrActivity r12 = this.this$0;
            if (r102 == false) goto L33;
            int r73 = (r72 - QrActivity.m18419$$Nest$fgetthemeLayout(r12).getMeasuredWidth()) / 2;
            QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).layout(r73, getMeasuredHeight() - r92, QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).getMeasuredWidth() + r73, getMeasuredHeight());
            goto L34
        L33:
            int r83 = (r82 - QrActivity.m18419$$Nest$fgetthemeLayout(r12).getMeasuredHeight()) / 2;
            QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).layout(getMeasuredWidth() - QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).getMeasuredWidth(), r83, getMeasuredWidth(), QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).getMeasuredHeight() + r83);
        L34:
            QrActivity.m18412$$Nest$fgetlogoImageView(this.this$0).layout(QrActivity.m18413$$Nest$fgetlogoRect(this.this$0).left + r113, QrActivity.m18413$$Nest$fgetlogoRect(this.this$0).top + r02, r113 + QrActivity.m18413$$Nest$fgetlogoRect(this.this$0).right, r02 + QrActivity.m18413$$Nest$fgetlogoRect(this.this$0).bottom);
            int r74 = QrActivity.m18409$$Nest$fgetinsets(this.this$0).left + AndroidUtilities.dp(11.0f);
            int r93 = QrActivity.m18409$$Nest$fgetinsets(this.this$0).top + AndroidUtilities.dp(11.0f);
            QrActivity.m18406$$Nest$fgetcloseImageView(this.this$0).layout(r74, r93, QrActivity.m18406$$Nest$fgetcloseImageView(this.this$0).getMeasuredWidth() + r74, QrActivity.m18406$$Nest$fgetcloseImageView(this.this$0).getMeasuredHeight() + r93);
            return;
        L24:
            r02 = (r82 - QrActivity.m18416$$Nest$fgetqrView(r0).getMeasuredHeight()) / 2;
            goto L25
        L20:
            r113 = QrActivity.m18409$$Nest$fgetinsets(r112).left + ((((r72 - QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).getMeasuredWidth()) - QrActivity.m18409$$Nest$fgetinsets(this.this$0).left) - QrActivity.m18416$$Nest$fgetqrView(this.this$0).getMeasuredWidth()) / 2);
            goto L21
        L9:
            if (r102 == false) goto L11;
            AndroidUtilities.rectTmp2.set(0, 0, r72, (r82 - QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).getMeasuredHeight()) + AndroidUtilities.dp(25.0f));
        L12:
            QrActivity.m18405$$Nest$fgetbackgroundView(this.this$0).setClipBounds(AndroidUtilities.rectTmp2);
            goto L14
        L11:
            AndroidUtilities.rectTmp2.set(0, 0, (r72 - QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).getWidth()) + AndroidUtilities.dp(25.0f), r82);
            goto L12
        L5:
            r102 = false;
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            int r0 = View.MeasureSpec.getSize(r9);
            int r1 = View.MeasureSpec.getSize(r10);
            if (r0 >= r1) goto L5;
            boolean r4 = true;
        L6:
            QrActivity.m18422$$Nest$fputisFragmentViewPortrait(this.this$0, r4);
            BackupImageView r5 = QrActivity.m18404$$Nest$fgetavatarImageView(this.this$0);
            if (r4 == false) goto L9;
            int r6 = 0;
        L10:
            r5.setVisibility(r6);
            super.onMeasure(r9, r10);
            if (r4 == false) goto L14;
            this.ignoreLayout = true;
            QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).setPadding(QrActivity.m18409$$Nest$fgetinsets(this.this$0).left, AndroidUtilities.dp(8.0f), QrActivity.m18409$$Nest$fgetinsets(this.this$0).right, QrActivity.m18409$$Nest$fgetinsets(this.this$0).bottom);
            this.ignoreLayout = false;
            QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r1 + QrActivity.m18409$$Nest$fgetinsets(this.this$0).bottom, Integer.MIN_VALUE));
            QrActivity.m18416$$Nest$fgetqrView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(260.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(330.0f), TLObject.FLAG_30));
            return;
        L14:
            this.ignoreLayout = true;
            QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).setPadding(0, (QrActivity.m18409$$Nest$fgetinsets(this.this$0).top * 2) / 3, QrActivity.m18409$$Nest$fgetinsets(this.this$0).right, QrActivity.m18409$$Nest$fgetinsets(this.this$0).bottom);
            this.ignoreLayout = false;
            QrActivity.m18419$$Nest$fgetthemeLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(273.0f) + QrActivity.m18409$$Nest$fgetinsets(this.this$0).right, TLObject.FLAG_30), r10);
            QrActivity.m18416$$Nest$fgetqrView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(260.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(310.0f), TLObject.FLAG_30));
            return;
        L9:
            r6 = 8;
            goto L10
        L5:
            r4 = false;
            goto L6
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == true) goto L6;
            super.requestLayout();
            return;
        }
    }

    /* renamed from: org.telegram.ui.QrActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ QrActivity this$0;

        public AnonymousClass2(QrActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            if (QrActivity.m18410$$Nest$fgetisCurrentThemeDark(this.this$0) == false) goto L5;
            int r0 = -15590870;
        L6:
            r5.drawColor(r0);
            if (QrActivity.m18414$$Nest$fgetprevMotionDrawable(this.this$0) == null) goto L9;
            QrActivity.m18414$$Nest$fgetprevMotionDrawable(this.this$0).setBounds(0, 0, getWidth(), getHeight());
        L9:
            QrActivity.m18407$$Nest$fgetcurrMotionDrawable(this.this$0).setBounds(0, 0, getWidth(), getHeight());
            if (QrActivity.m18414$$Nest$fgetprevMotionDrawable(this.this$0) == null) goto L12;
            QrActivity.m18414$$Nest$fgetprevMotionDrawable(this.this$0).draw(r5);
        L12:
            QrActivity.m18407$$Nest$fgetcurrMotionDrawable(this.this$0).draw(r5);
            super.onDraw(r5);
            return;
        L5:
            r0 = -6569073;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.QrActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements ResultCallback<List<EmojiThemes>> {
        final /* synthetic */ QrActivity this$0;

        public AnonymousClass3(QrActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.tgnet.ResultCallback
        public /* bridge */ /* synthetic */ void onComplete(List<EmojiThemes> r1) {
            onComplete2(r1);
        }

        /* renamed from: onComplete, reason: avoid collision after fix types in other method */
        public void onComplete2(List<EmojiThemes> r1) {
            QrActivity.m18427$$Nest$monDataLoaded(this.this$0, r1);
            QrActivity.m18429$$Nest$sfputcachedThemes(r1);
        }

        @Override // org.telegram.tgnet.ResultCallback
        public void onError(TLRPC.TL_error r2) {
            Toast.makeText(this.this$0.getParentActivity(), r2.text, 0).show();
        }
    }

    /* renamed from: org.telegram.ui.QrActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ QrActivity this$0;
        final /* synthetic */ int[] val$newQrColors;

        public AnonymousClass4(QrActivity r1, int[] r2) {
            this.this$0 = r1;
            this.val$newQrColors = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r8) {
            super.onAnimationCancel(r8);
            float r82 = ((Float) ((ValueAnimator) r8).getAnimatedValue()).floatValue();
            if (this.val$newQrColors == null) goto L6;
            System.arraycopy(new int[]{ColorUtils.blendARGB(QrActivity.m18415$$Nest$fgetprevQrColors(this.this$0)[0], this.val$newQrColors[0], r82), ColorUtils.blendARGB(QrActivity.m18415$$Nest$fgetprevQrColors(this.this$0)[1], this.val$newQrColors[1], r82), ColorUtils.blendARGB(QrActivity.m18415$$Nest$fgetprevQrColors(this.this$0)[2], this.val$newQrColors[2], r82), ColorUtils.blendARGB(QrActivity.m18415$$Nest$fgetprevQrColors(this.this$0)[3], this.val$newQrColors[3], r82)}, 0, QrActivity.m18415$$Nest$fgetprevQrColors(this.this$0), 0, 4);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            super.onAnimationEnd(r4);
            int[] r42 = this.val$newQrColors;
            if (r42 == null) goto L5;
            System.arraycopy(r42, 0, QrActivity.m18415$$Nest$fgetprevQrColors(this.this$0), 0, 4);
        L5:
            QrActivity.m18424$$Nest$fputprevMotionDrawable(this.this$0, null);
            QrActivity.m18423$$Nest$fputpatternAlphaAnimator(this.this$0, null);
            QrActivity.m18407$$Nest$fgetcurrMotionDrawable(this.this$0).setBackgroundAlpha(1.0f);
            QrActivity.m18407$$Nest$fgetcurrMotionDrawable(this.this$0).setPatternAlpha(1.0f);
        }
    }

    /* renamed from: org.telegram.ui.QrActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements CameraScanActivity.CameraScanActivityDelegate {
        final /* synthetic */ int val$currentAccount;
        final /* synthetic */ BaseFragment val$fragment;

        public static /* synthetic */ void $r8$lambda$AbAsGaq6Ez53MDPn0H07abYhqMI(BaseFragment r4, Long r5) {
            if (r4.isFinished == false) goto L5;
            return;
        L5:
            if (r5 != null) goto L7;
        L11:
            AndroidUtilities.runOnUIThread(new QrActivity$5$$ExternalSyntheticLambda2());
            return;
        L7:
            if (r5.longValue() == LongCompanionObject.MAX_VALUE) goto L11;
            r4.presentFragment(ProfileActivity.of(r5.longValue()), true);
        }

        /* renamed from: $r8$lambda$FJ__XDYBC5dyJ9v3sAvH-Nn4sKo, reason: not valid java name */
        public static /* synthetic */ void m18430$r8$lambda$FJ__XDYBC5dyJ9v3sAvHNn4sKo() {
            BulletinFactory.global().createSimpleBulletin(LocaleController.getString(R.string.ScanQrCode), LocaleController.getString(R.string.ErrorOccurred)).show();
        }

        public static /* synthetic */ void $r8$lambda$_9EC9iJaJ2AEh6l6oeFTfzYoFrM() {
            BulletinFactory.global().createSimpleBulletin(LocaleController.getString(R.string.ScanQrCode), LocaleController.getString(R.string.ErrorOccurred)).show();
        }

        public AnonymousClass5(int r1, BaseFragment r2) {
            this.val$currentAccount = r1;
            this.val$fragment = r2;
        }

        @Override // org.telegram.ui.CameraScanActivity.CameraScanActivityDelegate
        public void didFindQr(String r3) {
            String r32 = Browser.extractUsername(r3);
            if (TextUtils.isEmpty(r32) == true) goto L6;
            MessagesController.getInstance(this.val$currentAccount).getUserNameResolver().resolve(r32, new QrActivity$5$$ExternalSyntheticLambda0(this.val$fragment));
            return;
        L6:
            AndroidUtilities.runOnUIThread(new QrActivity$5$$ExternalSyntheticLambda1());
        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(EmojiThemes r1, int r2);
    }

    public static class QrView extends View {
        private static final float RADIUS = 0.0f;
        private static final float SHADOW_SIZE = 0.0f;
        private Bitmap backgroundBitmap;
        private final Paint bitmapGradientPaint;
        private QrCenterChangedListener centerChangedListener;
        private Runnable checkTimerToken;
        private Bitmap contentBitmap;
        private AnimatedFloat contentBitmapAlpha;
        private Paint crossfadeFromPaint;
        private Paint crossfadeToPaint;
        private final int crossfadeWidthDp;
        private boolean firstPrepare;
        private final MotionBackgroundDrawable gradientDrawable;
        private final BitmapShader gradientShader;
        private final BitmapShader gradientTextShader;
        private Integer hadHeight;
        private String hadLink;
        private String hadUserText;
        private Integer hadWidth;
        private boolean hasTimer;
        private boolean isPhone;
        private String link;
        private int linkExpires;
        private RLottieDrawable loadingMatrix;
        private boolean logoCenterSet;
        private Bitmap oldContentBitmap;
        private float[] radii;
        private boolean setData;
        private StaticLayout shareUsernameLayout;
        private TextPaint shareUsernameLayoutPaint;
        private AnimatedTextView.AnimatedTextDrawable timerTextDrawable;
        private String username;

        /* renamed from: org.telegram.ui.QrActivity$QrView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatedTextView.AnimatedTextDrawable {
            final /* synthetic */ QrView this$0;

            public AnonymousClass1(QrView r1, boolean r2, boolean r3, boolean r4) {
                this.this$0 = r1;
                super(r2, r3, r4);
            }

            @Override // android.graphics.drawable.Drawable
            public void invalidateSelf() {
                this.this$0.invalidate();
            }
        }

        public interface QrCenterChangedListener {
            void onCenterChanged(int r1, int r2, int r3, int r4);
        }

        public static /* synthetic */ void $r8$lambda$2iYc8CXhdvukKCE5VgAq2hDUf8Y(QrView r0, int r1, int r2) {
            r0.lambda$new$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$BLO4PV5UKhvXpwEBxWy_NosgeCc(QrView r0, Bitmap r1, float r2, int r3, float r4) {
            r0.lambda$prepareContent$7(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$Fc2RMLSwrHIJfapUtWpARCLC_yw(QrView r0) {
            r0.lambda$new$5();
        }

        public static /* synthetic */ void $r8$lambda$YbTSJvzn4fMm4qc9mX0pMZwswrE(QrView r0, TLRPC.TL_exportedContactToken r1) {
            r0.lambda$new$3(r1);
        }

        /* renamed from: $r8$lambda$aiQpmDA-nQfH-mwODqgCF38RvsI, reason: not valid java name */
        public static /* synthetic */ void m18431$r8$lambda$aiQpmDAnQfHmwODqgCF38RvsI(QrView r0, TLRPC.TL_exportedContactToken r1) {
            r0.lambda$new$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$mLqYLUERoFGwZbpv6prQUBi_FM4(QrView r0, int r1, int r2) {
            r0.lambda$onSizeChanged$0(r1, r2);
        }

        /* renamed from: $r8$lambda$s-Bkkl7SlQjn6RLC0cCFMMH2x94, reason: not valid java name */
        public static /* synthetic */ void m18432$r8$lambda$sBkkl7SlQjn6RLC0cCFMMH2x94(QrView r0, int r1, int r2) {
            r0.lambda$setData$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$ykciF7qhVmTN8Mxt5x4x2aMzxbA(QrView r0) {
            r0.lambda$prepareContent$6();
        }

        static {
            SHADOW_SIZE = AndroidUtilities.dp(2.0f);
            RADIUS = AndroidUtilities.dp(20.0f);
        }

        public QrView(Context r25) {
            super(r25);
            MotionBackgroundDrawable r8 = new MotionBackgroundDrawable();
            this.gradientDrawable = r8;
            Paint r9 = new Paint(1);
            this.bitmapGradientPaint = r9;
            CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.contentBitmapAlpha = new AnimatedFloat(1.0f, this, 0, 2000, r7);
            this.crossfadeFromPaint = new Paint(1);
            this.crossfadeToPaint = new Paint(1);
            this.crossfadeWidthDp = 120;
            this.radii = new float[8];
            this.checkTimerToken = new QrActivity$QrView$$ExternalSyntheticLambda0(this);
            this.firstPrepare = true;
            r8.setIndeterminateAnimation(true);
            r8.setParentView(this);
            Bitmap r2 = r8.getBitmap();
            Shader.TileMode r3 = Shader.TileMode.MIRROR;
            BitmapShader r1 = new BitmapShader(r2, r3, r3);
            this.gradientShader = r1;
            BitmapShader r11 = new BitmapShader(r8.getBitmap(), r3, r3);
            this.gradientTextShader = r11;
            r9.setShader(r1);
            AnonymousClass1 r12 = new AnonymousClass1(this, false, true, false);
            this.timerTextDrawable = r12;
            r12.setAnimationProperties(0.35f, 0, 300, r7);
            this.timerTextDrawable.setCallback(this);
            this.timerTextDrawable.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD));
            this.timerTextDrawable.getPaint().setShader(r11);
            this.timerTextDrawable.setGravity(17);
            this.timerTextDrawable.setTextSize(AndroidUtilities.dp(35.0f));
            this.timerTextDrawable.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            Paint r13 = this.crossfadeFromPaint;
            Shader.TileMode r16 = Shader.TileMode.CLAMP;
            r13.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(120.0f), new int[]{-1, 0}, new float[]{0.0f, 1.0f}, r16));
            Paint r14 = this.crossfadeFromPaint;
            PorterDuff.Mode r6 = PorterDuff.Mode.DST_OUT;
            r14.setXfermode(new PorterDuffXfermode(r6));
            this.crossfadeToPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(120.0f), new int[]{0, -1}, new float[]{0.0f, 1.0f}, r16));
            this.crossfadeToPaint.setXfermode(new PorterDuffXfermode(r6));
        }

        private void drawLoading(Canvas r17) {
            if (this.loadingMatrix == null) goto L13;
            int r7 = (getWidth() - AndroidUtilities.dp(60.0f)) / 33;
            int r9 = (r7 * 33) + 32;
            int r10 = (getWidth() - r9) / 2;
            int r2 = (int) (getHeight() * 0.15f);
            Point r3 = AndroidUtilities.displaySize;
            if (r3.x <= r3.y) goto L7;
            r2 = (int) (getHeight() * 0.09f);
        L7:
            int r11 = r2;
            RectF r22 = AndroidUtilities.rectTmp;
            r22.set(0.0f, 0.0f, getWidth(), getHeight());
            r17.saveLayerAlpha(r22, 255, 31);
            int r12 = r10 + 16;
            int r13 = r11 + 16;
            r17.drawRect(r12, r13, (getWidth() - r10) - 16, (((getWidth() + r11) - r10) - r10) - 16, this.bitmapGradientPaint);
            r17.save();
            this.loadingMatrix.setBounds(r12, r13, (getWidth() - r10) - 16, (((getWidth() + r11) - r10) - r10) - 16);
            this.loadingMatrix.draw(r17);
            r17.restore();
            r17.restore();
            float r122 = getWidth() / 2.0f;
            float r23 = r11;
            float r32 = r10;
            float r132 = ((getWidth() / 2.0f) + r23) - r32;
            float r14 = ((Math.round((r8 / 4.65f) / r6) * r7) / 2) * 0.75f;
            r17.drawCircle(r122, r132, r14, this.bitmapGradientPaint);
            TelegramQRCodeWriter.drawSideQuads(r17, r32, r23, this.bitmapGradientPaint, 7.0f, r7, 16, r9, 0.75f, this.radii, true);
            if (this.logoCenterSet == true) goto L14;
            QrCenterChangedListener r1 = this.centerChangedListener;
            if (r1 == null) goto L15;
            r1.onCenterChanged((int) (r122 - r14), (int) (r132 - r14), (int) (r122 + r14), (int) (r132 + r14));
            this.logoCenterSet = true;
            return;
        L15:
            return;
        L14:
            return;
        }

        private /* synthetic */ void lambda$new$2(int r1, int r2) {
            prepareContent(r1, r2);
        }

        private /* synthetic */ void lambda$new$3(TLRPC.TL_exportedContactToken r4) {
            if (r4 != null) goto L4;
            return;
        L4:
            int r0 = this.linkExpires;
            if (r0 != 0) goto L7;
        L9:
            this.linkExpires = r4.expires;
            setData(r4.url, null, false, true);
            return;
        L7:
            if (r0 >= r4.expires) goto L9;
            VibratorUtils.vibrate(100);
            goto L9
        }

        private /* synthetic */ void lambda$new$4(TLRPC.TL_exportedContactToken r2) {
            AndroidUtilities.runOnUIThread(new QrActivity$QrView$$ExternalSyntheticLambda7(this, r2));
        }

        private /* synthetic */ void lambda$new$5() {
            AndroidUtilities.cancelRunOnUIThread(this.checkTimerToken);
            boolean r0 = this.hasTimer;
            if (r0 == true) goto L6;
            return;
        L6:
            if (r0 == true) goto L8;
        L10:
            int r02 = this.linkExpires;
            String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r02 == 0) goto L15;
            if ((System.currentTimeMillis() / 1000) >= this.linkExpires) goto L15;
        L22:
            int r03 = this.linkExpires;
            if (r03 <= 0) goto L35;
            if (this.link == null) goto L35;
            long r5 = Math.max(0, (r03 - (System.currentTimeMillis() / 1000)) - 1);
            int r04 = (int) (r5 % 60);
            int r52 = Math.min(99, (int) (r5 / 60));
            AnimatedTextView.AnimatedTextDrawable r6 = this.timerTextDrawable;
            StringBuilder r7 = new StringBuilder();
            if (r52 >= 10) goto L29;
            String r10 = MVEL.VERSION_SUB;
        L30:
            r7.append(r10);
            r7.append(r52);
            r7.append(":");
            if (r04 >= 10) goto L33;
            r2 = MVEL.VERSION_SUB;
        L33:
            r7.append(r2);
            r7.append(r04);
            r6.setText(r7.toString(), true, false);
            goto L35
        L29:
            r10 = _UrlKt.FRAGMENT_ENCODE_SET;
        L35:
            if (isAttachedToWindow() == false) goto L38;
            AndroidUtilities.runOnUIThread(this.checkTimerToken, 1000);
            return;
        L38:
            return;
        L15:
            if (this.linkExpires == 0) goto L17;
            this.link = null;
            Utilities.themeQueue.postRunnable(new QrActivity$QrView$$ExternalSyntheticLambda5(this, getWidth(), getHeight()));
            this.timerTextDrawable.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        L17:
            MessagesController r05 = MessagesController.getInstance(UserConfig.selectedAccount);
            if (this.linkExpires != 0) goto L20;
            long r53 = 750;
        L21:
            r05.requestContactToken(r53, new QrActivity$QrView$$ExternalSyntheticLambda6(this));
            goto L22
        L20:
            r53 = 1750;
            goto L21
        L8:
            if (this.loadingMatrix != null) goto L10;
            RLottieDrawable r06 = new RLottieDrawable(R.raw.qr_matrix, AndroidUtilities.dp(200.0f), AndroidUtilities.dp(200.0f));
            this.loadingMatrix = r06;
            r06.setMasterParent(this);
            this.loadingMatrix.getPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            this.loadingMatrix.setAutoRepeat(1);
            this.loadingMatrix.start();
            goto L10
        }

        private /* synthetic */ void lambda$onSizeChanged$0(int r1, int r2) {
            prepareContent(r1, r2);
        }

        private /* synthetic */ void lambda$prepareContent$6() {
            this.firstPrepare = false;
            Bitmap r0 = this.contentBitmap;
            if (r0 == null) goto L9;
            this.contentBitmap = null;
            this.contentBitmapAlpha.set(0.0f, true);
            Bitmap r1 = this.oldContentBitmap;
            if (r1 == null) goto L7;
            r1.recycle();
        L7:
            this.oldContentBitmap = r0;
            invalidate();
            return;
        }

        private /* synthetic */ void lambda$prepareContent$7(Bitmap r4, float r5, int r6, float r7) {
            Bitmap r0 = this.contentBitmap;
            this.contentBitmap = r4.extractAlpha();
            if (this.firstPrepare == true) goto L5;
            this.contentBitmapAlpha.set(0.0f, true);
        L5:
            this.firstPrepare = false;
            Bitmap r42 = this.oldContentBitmap;
            if (r42 == null) goto L8;
            r42.recycle();
        L8:
            this.oldContentBitmap = r0;
            QrCenterChangedListener r43 = this.centerChangedListener;
            if (r43 == null) goto L11;
            float r62 = r6 * 0.5f;
            r43.onCenterChanged((int) (r5 - r62), (int) (r7 - r62), (int) (r5 + r62), (int) (r7 + r62));
            this.logoCenterSet = true;
        L11:
            invalidate();
        }

        private /* synthetic */ void lambda$setData$1(int r1, int r2) {
            prepareContent(r1, r2);
        }

        private void prepareContent(int r25, int r26) {
            if (r25 == 0) goto L118;
            if (r26 != 0) goto L7;
            return;
        L7:
            if (TextUtils.isEmpty(this.username) == false) goto L11;
            if (this.hasTimer == true) goto L11;
        L12:
            AndroidUtilities.runOnUIThread(new QrActivity$QrView$$ExternalSyntheticLambda3(this));
            return;
        L11:
            if (TextUtils.isEmpty(this.link) == true) goto L12;
            if (this.hasTimer == false) goto L17;
            String r5 = null;
        L22:
            if (TextUtils.equals(r5, this.hadUserText) == true) goto L24;
        L34:
            Bitmap r3 = Bitmap.createBitmap(r25, r26, Bitmap.Config.ARGB_8888);
            TextPaint r7 = new TextPaint(65);
            int r6 = -16777216;
            r7.setColor(-16777216);
            r7.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD));
            int r8 = r3.getWidth() - (AndroidUtilities.dp(20.0f) * 2);
            if (this.hasTimer == true) goto L64;
            int r9 = 0;
        L37:
            if (r9 > 2) goto L64;
            if (r9 != 0) goto L40;
            Drawable r14 = ContextCompat.getDrawable(getContext(), R.drawable.qr_at_large);
            r7.setTextSize(AndroidUtilities.dp(30.0f));
        L43:
            if (r14 == null) goto L45;
            r14.setBounds(0, 0, r14.getIntrinsicWidth(), r14.getIntrinsicHeight());
            r14.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.SRC_IN));
        L45:
            SpannableStringBuilder r62 = new SpannableStringBuilder(" " + r5);
            if (this.isPhone == true) goto L48;
            r62.setSpan(new SettingsSearchCell.VerticalImageSpan(r14), 0, 1, 33);
        L48:
            float r4 = r7.measureText(r62, 1, r62.length()) + r14.getBounds().width();
            if (r9 > 1) goto L54;
            if (r4 <= r8) goto L54;
            r9 = r9 + 1;
            r6 = -16777216;
        L54:
            if (r4 <= r8) goto L56;
            int r92 = 2;
        L57:
            if (r92 <= 1) goto L59;
            int r15 = (((int) (r14.getBounds().width() + r4)) / 2) + AndroidUtilities.dp(2.0f);
        L60:
            if (r15 <= r8) goto L62;
            int r82 = (((int) (r4 + r14.getBounds().width())) / 3) + AndroidUtilities.dp(4.0f);
            int r152 = 3;
        L63:
            int r22 = 0;
            int r42 = 3;
            float r21 = 30.0f;
            int r16 = -16777216;
            StaticLayout r63 = StaticLayoutEx.createStaticLayout(r62, r7, r82, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false, null, Math.min(AndroidUtilities.dp(10.0f) + r82, r3.getWidth()), r152);
        L65:
            float r83 = r7.descent() - r7.ascent();
            if (r63 != null) goto L68;
            int r12 = r22;
        L69:
            float r72 = r12 * r83;
            int r10 = r25 - (AndroidUtilities.dp(r21) * 2);
            HashMap r122 = new HashMap();
            r122.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            r122.put(EncodeHintType.MARGIN, Integer.valueOf(r22));
            TelegramQRCodeWriter r84 = new TelegramQRCodeWriter();
            int r93 = r42;
            Bitmap r19 = null;
        L71:
            if (r93 >= 5) goto L84;
            r122.put(EncodeHintType.QR_VERSION, Integer.valueOf(r93));     // Catch: Exception -> L82
            int r11 = r93;
            int r20 = r11;
            r19 = r84.encode(this.link, r10, r10, r122, null, 0.75f, 16777215, r16);     // Catch: Exception -> L104
            int r94 = r16;
            r22 = r84.getImageSize();     // Catch: Exception -> L103
        L83:
            if (r19 != null) goto L84;
            r16 = r94;
            r93 = r20 + 1;
        L80:
            r94 = r16;
        L81:
            r20 = r11;
        L82:
            r20 = r93;
        L84:
            Bitmap r85 = r19;
            int r102 = r22;
            if (r85 == null) goto L121;
            Canvas r112 = new Canvas(r3);
            r112.drawColor(16777215);
            float r123 = (r25 - r85.getWidth()) / 2.0f;
            float r13 = r26;
            float r142 = 0.15f * r13;
            if (r63 == null) goto L95;
            if (r63.getLineCount() != r42) goto L95;
            r142 = 0.13f * r13;
        L95:
            if (((ViewGroup) getParent()).getMeasuredWidth() < ((ViewGroup) getParent()).getMeasuredHeight()) goto L98;
            r142 = r13 * 0.09f;
        L98:
            r112.drawBitmap(r85, r123, r142, new Paint(r42));
            Paint r43 = new Paint(1);
            r43.setColor(-16777216);
            float r124 = r123 + (r85.getWidth() * 0.5f);
            float r95 = (r85.getWidth() * 0.5f) + r142;
            r112.drawCircle(r124, r95, r102 * 0.5f, r43);
            if (r63 == null) goto L101;
            r112.save();
            r112.translate((r112.getWidth() - r63.getWidth()) * 0.5f, ((r85.getHeight() + r142) + (((r112.getHeight() - (r142 + r85.getHeight())) - r72) * 0.5f)) - AndroidUtilities.dp(4.0f));
            r63.draw(r112);
            r112.restore();
            r85.recycle();
        L101:
            this.hadWidth = Integer.valueOf(r25);
            this.hadHeight = Integer.valueOf(r26);
            this.hadUserText = r5;
            this.hadLink = this.link;
            AndroidUtilities.runOnUIThread(new QrActivity$QrView$$ExternalSyntheticLambda4(this, r3, r124, r102, r95));
            return;
        L121:
            return;
        L68:
            r12 = r63.getLineCount();
            goto L69
        L62:
            r82 = r15;
            r152 = r92;
            goto L63
        L59:
            r15 = r8;
            goto L60
        L56:
            r92 = 1;
            goto L57
        L40:
            if (r9 != 1) goto L42;
            r14 = ContextCompat.getDrawable(getContext(), R.drawable.qr_at_medium);
            r7.setTextSize(AndroidUtilities.dp(25.0f));
            goto L43
        L42:
            r14 = ContextCompat.getDrawable(getContext(), R.drawable.qr_at_small);
            r7.setTextSize(AndroidUtilities.dp(19.0f));
        L64:
            r42 = 3;
            r22 = 0;
            r21 = 30.0f;
            r16 = r6;
            r63 = null;
            goto L65
        L24:
            if (TextUtils.equals(this.link, this.hadLink) == false) goto L34;
            Integer r32 = this.hadWidth;
            if (r32 == null) goto L34;
            if (this.hadHeight == null) goto L34;
            if (r32.intValue() != r25) goto L34;
            if (this.hadHeight.intValue() != r26) goto L34;
            return;
        L17:
            boolean r33 = this.isPhone;
            r5 = this.username;
            if (r33 == true) goto L22;
            r5 = r5.toUpperCase();
            goto L22
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.checkTimerToken.run();
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            RLottieDrawable r0 = this.loadingMatrix;
            if (r0 == null) goto L6;
            r0.stop();
            this.loadingMatrix.recycle(false);
            this.loadingMatrix = null;
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r19) {
            super.onDraw(r19);
            Bitmap r2 = this.backgroundBitmap;
            if (r2 == null) goto L5;
            r19.drawBitmap(r2, 0.0f, 0.0f, null);
        L5:
            float r9 = this.contentBitmapAlpha.set(1.0f);
            if (r9 > 0.0f) goto L8;
        L10:
            boolean r13 = false;
        L12:
            if (r9 >= 1.0f) goto L21;
            if (r13 == false) goto L15;
            RectF r22 = AndroidUtilities.rectTmp;
            r22.set(0.0f, 0.0f, getWidth(), getHeight());
            r19.saveLayerAlpha(r22, 255, 31);
        L15:
            Bitmap r23 = this.oldContentBitmap;
            if (r23 == null) goto L18;
            r19.drawBitmap(r23, 0.0f, 0.0f, this.bitmapGradientPaint);
        L19:
            if (r13 == false) goto L21;
            float r24 = AndroidUtilities.dp(120.0f);
            r19.save();
            r19.translate(0.0f, (-r24) + ((getHeight() + r24) * (1.0f - r9)));
            float r17 = 1.0f;
            int r8 = 255;
            r19.drawRect(0.0f, 0.0f, getWidth(), getHeight() + r24, this.crossfadeToPaint);
            r19.restore();
            r19.restore();
        L22:
            if (r9 <= 0.0f) goto L32;
            if (r13 == false) goto L25;
            RectF r25 = AndroidUtilities.rectTmp;
            r25.set(0.0f, 0.0f, getWidth(), getHeight());
            r19.saveLayerAlpha(r25, r8, 31);
        L25:
            Bitmap r26 = this.contentBitmap;
            if (r26 == null) goto L28;
            r19.drawBitmap(r26, 0.0f, 0.0f, this.bitmapGradientPaint);
            this.gradientDrawable.updateAnimation();
        L29:
            if (r13 == false) goto L32;
            float r27 = AndroidUtilities.dp(120.0f);
            r19.save();
            float r3 = -r27;
            r19.translate(0.0f, ((getHeight() + r27) * (r17 - r9)) + r3);
            r19.drawRect(0.0f, r3 - getHeight(), getWidth(), getHeight() + r27, this.crossfadeFromPaint);
            r19.restore();
            r19.restore();
            goto L32
        L28:
            drawLoading(r19);
        L32:
            if (this.hasTimer == false) goto L42;
            float r28 = getWidth() + AndroidUtilities.dp(6.0f);
            if (this.shareUsernameLayout == null) goto L40;
            r19.save();
            r19.translate(0.0f, r28);
            if (this.shareUsernameLayout.getWidth() == getWidth()) goto L38;
            setForShare(true);
        L38:
            this.shareUsernameLayout.draw(r19);
            r19.restore();
            return;
        L40:
            int r29 = (int) r28;
            this.timerTextDrawable.setBounds(0, r29, getWidth(), AndroidUtilities.dp(40.0f) + r29);
            this.timerTextDrawable.draw(r19);
            return;
        L42:
            return;
        L18:
            drawLoading(r19);
        L21:
            r17 = 1.0f;
            r8 = 255;
            goto L22
        L8:
            if (r9 >= 1.0f) goto L10;
            r13 = true;
            goto L12
        }

        @Override // android.view.View
        public void onSizeChanged(int r6, int r7, int r8, int r9) {
            super.onSizeChanged(r6, r7, r8, r9);
            if (r6 != r8) goto L7;
            if (r7 != r9) goto L7;
            return;
        L7:
            Bitmap r82 = this.backgroundBitmap;
            if (r82 == null) goto L10;
            r82.recycle();
            this.backgroundBitmap = null;
        L10:
            Paint r83 = new Paint(1);
            r83.setColor(-1);
            float r92 = AndroidUtilities.dp(4.0f);
            float r0 = SHADOW_SIZE;
            r83.setShadowLayer(r92, 0.0f, r0, 251658240);
            this.backgroundBitmap = Bitmap.createBitmap(r6, r7, Bitmap.Config.ARGB_8888);
            Canvas r93 = new Canvas(this.backgroundBitmap);
            float r2 = r6;
            RectF r1 = new RectF(r0, r0, r2 - r0, getHeight() - r0);
            float r02 = RADIUS;
            r93.drawRoundRect(r1, r02, r02, r83);
            if (this.setData == false) goto L13;
            Utilities.themeQueue.postRunnable(new QrActivity$QrView$$ExternalSyntheticLambda2(this, r6, r7));
        L13:
            float r62 = Math.max((getWidth() * 1.0f) / this.gradientDrawable.getBitmap().getWidth(), (getHeight() * 1.0f) / this.gradientDrawable.getBitmap().getHeight());
            Matrix r72 = new Matrix();
            r72.setScale(r62, r62);
            this.gradientShader.setLocalMatrix(r72);
            Matrix r73 = new Matrix();
            r73.setScale(r62, r62);
            r73.postTranslate(r2 / 2.0f, getWidth() + AndroidUtilities.dp(6.0f));
            this.gradientTextShader.setLocalMatrix(r73);
        }

        public void setCenterChangedListener(QrCenterChangedListener r1) {
            this.centerChangedListener = r1;
        }

        public void setColors(int r2, int r3, int r4, int r5) {
            this.gradientDrawable.setColors(r2, r3, r4, r5);
            invalidate();
        }

        public void setData(String r2, String r3, boolean r4, boolean r5) {
            this.setData = true;
            this.username = r3;
            this.isPhone = r4;
            if (r5 == false) goto L8;
            TLRPC.TL_exportedContactToken r22 = MessagesController.getInstance(UserConfig.selectedAccount).getCachedContactToken();
            if (r22 == null) goto L7;
            this.link = r22.url;
            this.linkExpires = r22.expires;
        L9:
            this.hasTimer = r5;
            int r23 = getWidth();
            int r32 = getHeight();
            Utilities.themeQueue.postRunnable(new QrActivity$QrView$$ExternalSyntheticLambda1(this, r23, r32));
            invalidate();
            this.checkTimerToken.run();
            return;
        L7:
            this.link = null;
            goto L9
        L8:
            this.link = r2;
            goto L9
        }

        public void setForShare(boolean r13) {
            if (this.hasTimer == true) goto L5;
            return;
        L5:
            if (r13 == true) goto L7;
            this.shareUsernameLayout = null;
            return;
        L7:
            if (this.shareUsernameLayoutPaint != null) goto L9;
            this.shareUsernameLayoutPaint = new TextPaint(1);
        L9:
            this.shareUsernameLayoutPaint.setShader(this.gradientTextShader);
            this.shareUsernameLayoutPaint.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD));
            this.shareUsernameLayoutPaint.setTextSize(AndroidUtilities.dp(25.0f));
            String r132 = this.username;
            if (r132 != null) goto L12;
            r132 = _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            this.shareUsernameLayout = StaticLayoutEx.createStaticLayout(Emoji.replaceEmoji(r132, this.shareUsernameLayoutPaint.getFontMetricsInt(), false), this.shareUsernameLayoutPaint, getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, getWidth() - AndroidUtilities.dp(60.0f), 1);
        }

        public void setPosAnimationProgress(float r1) {
            this.gradientDrawable.posAnimationProgress = r1;
        }
    }

    public class ThemeListViewController implements NotificationCenter.NotificationCenterDelegate {
        public final ChatThemeBottomSheet.Adapter adapter;
        private final Drawable backgroundDrawable;
        private final Paint backgroundPaint;
        private final View bottomShadow;
        private View changeDayNightView;
        private ValueAnimator changeDayNightViewAnimator;
        private float changeDayNightViewProgress;
        private final RLottieDrawable darkThemeDrawable;
        private final RLottieImageView darkThemeView;
        private boolean forceDark;
        private final BaseFragment fragment;
        protected boolean isLightDarkChangeAnimation;
        private OnItemSelectedListener itemSelectedListener;
        private LinearLayoutManager layoutManager;
        private boolean prevIsPortrait;
        public int prevSelectedPosition;
        public final FlickerLoadingView progressView;
        private final RecyclerListView recyclerView;
        public final FrameLayout rootLayout;
        public final TextView scanButton;
        public final ImageView scanButtonIcon;
        public final LinearLayout scanButtonWrap;
        private final LinearSmoothScroller scroller;
        public ChatThemeBottomSheet.ChatThemeItem selectedItem;
        public final TextView shareButton;
        final /* synthetic */ QrActivity this$0;
        public final TextView titleView;
        private final View topShadow;
        private final Window window;

        /* renamed from: org.telegram.ui.QrActivity$ThemeListViewController$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearSmoothScroller {
            final /* synthetic */ ThemeListViewController this$1;
            final /* synthetic */ QrActivity val$this$0;

            public AnonymousClass1(ThemeListViewController r1, Context r2, QrActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateTimeForScrolling(int r1) {
                return super.calculateTimeForScrolling(r1) * 6;
            }
        }

        /* renamed from: org.telegram.ui.QrActivity$ThemeListViewController$2, reason: invalid class name */
        public class AnonymousClass2 extends FrameLayout {
            private final Rect backgroundPadding;
            final /* synthetic */ ThemeListViewController this$1;
            final /* synthetic */ BaseFragment val$fragment;
            final /* synthetic */ QrActivity val$this$0;

            public AnonymousClass2(ThemeListViewController r2, Context r3, QrActivity r4, BaseFragment r5) {
                this.this$1 = r2;
                this.val$this$0 = r4;
                this.val$fragment = r5;
                super(r3);
                Rect r32 = new Rect();
                this.backgroundPadding = r32;
                ThemeListViewController.m18436$$Nest$fgetbackgroundPaint(r2).setColor(r5.getThemedColor(Theme.key_windowBackgroundWhite));
                ThemeListViewController.m18435$$Nest$fgetbackgroundDrawable(r2).setCallback(this);
                ThemeListViewController.m18435$$Nest$fgetbackgroundDrawable(r2).getPadding(r32);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r7) {
                if (ThemeListViewController.m18443$$Nest$fgetprevIsPortrait(this.this$1) == false) goto L5;
                Drawable r0 = ThemeListViewController.m18435$$Nest$fgetbackgroundDrawable(this.this$1);
                Rect r1 = this.backgroundPadding;
                r0.setBounds(-r1.left, -r1.top, getWidth() + this.backgroundPadding.right, getHeight() + this.backgroundPadding.bottom);
                ThemeListViewController.m18435$$Nest$fgetbackgroundDrawable(this.this$1).draw(r7);
            L6:
                super.dispatchDraw(r7);
                return;
            L5:
                RectF r02 = AndroidUtilities.rectTmp;
                r02.set(0.0f, 0.0f, getWidth() + AndroidUtilities.dp(14.0f), getHeight());
                r7.drawRoundRect(r02, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), ThemeListViewController.m18436$$Nest$fgetbackgroundPaint(this.this$1));
                goto L6
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r14, int r15) {
                boolean r0 = QrActivity.m18411$$Nest$fgetisFragmentViewPortrait(this.this$1.this$0);
                int r1 = AndroidUtilities.dp(12.0f);
                ThemeListViewController r2 = this.this$1;
                if (r0 == false) goto L8;
                ThemeListViewController.m18444$$Nest$fgetrecyclerView(r2).setLayoutParams(LayoutHelper.createFrame(-1, 104.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 0.0f, 44.0f, 0.0f, 0.0f));
                ThemeListViewController.m18444$$Nest$fgetrecyclerView(this.this$1).setPadding(r1, 0, r1, 0);
                ThemeListViewController r12 = this.this$1;
                if (r12.scanButtonWrap == null) goto L7;
                r12.shareButton.setLayoutParams(LayoutHelper.createFrame(-1, 48.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 16.0f, 162.0f, 16.0f, 72.0f));
                this.this$1.scanButtonWrap.setLayoutParams(LayoutHelper.createFrame(-1, 48.0f, 8388691, 16.0f, 162.0f, 16.0f, 16.0f));
            L12:
                ThemeListViewController r13 = this.this$1;
                if (r0 == false) goto L16;
                ThemeListViewController.m18437$$Nest$fgetbottomShadow(r13).setVisibility(8);
                ThemeListViewController.m18445$$Nest$fgettopShadow(this.this$1).setVisibility(8);
            L21:
                if (ThemeListViewController.m18443$$Nest$fgetprevIsPortrait(this.this$1) == r0) goto L26;
                RecyclerListView r16 = ThemeListViewController.m18444$$Nest$fgetrecyclerView(this.this$1);
                ThemeListViewController r22 = this.this$1;
                LinearLayoutManager r3 = ThemeListViewController.m18450$$Nest$mgetLayoutManager(r22, r0);
                ThemeListViewController.m18448$$Nest$fputlayoutManager(r22, r3);
                r16.setLayoutManager(r3);
                ThemeListViewController.m18444$$Nest$fgetrecyclerView(this.this$1).requestLayout();
                ThemeListViewController r17 = this.this$1;
                int r23 = r17.prevSelectedPosition;
                if (r23 == (-1)) goto L25;
                r17.setSelectedPosition(r23);
            L25:
                ThemeListViewController.m18449$$Nest$fputprevIsPortrait(this.this$1, r0);
            L26:
                super.onMeasure(r14, r15);
                return;
            L16:
                if (r13.shareButton == null) goto L18;
                int r24 = 136;
            L19:
                ThemeListViewController.m18437$$Nest$fgetbottomShadow(r13).setVisibility(0);
                ThemeListViewController.m18437$$Nest$fgetbottomShadow(this.this$1).setLayoutParams(LayoutHelper.createFrame(-1, AndroidUtilities.dp(2.0f), 80, 0.0f, 0.0f, 0.0f, r24));
                ThemeListViewController.m18445$$Nest$fgettopShadow(this.this$1).setVisibility(0);
                ThemeListViewController.m18445$$Nest$fgettopShadow(this.this$1).setLayoutParams(LayoutHelper.createFrame(-1, AndroidUtilities.dp(2.0f), 48, 0.0f, 44.0f, 0.0f, 0.0f));
                goto L21
            L18:
                r24 = 80;
                goto L19
            L7:
                r12.shareButton.setLayoutParams(LayoutHelper.createFrame(-1, 48.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 16.0f, 162.0f, 16.0f, 16.0f));
                goto L12
            L8:
                ThemeListViewController.m18444$$Nest$fgetrecyclerView(r2).setPadding(r1, r1 / 2, r1, r1);
                ThemeListViewController r18 = this.this$1;
                if (r18.scanButtonWrap == null) goto L11;
                ThemeListViewController.m18444$$Nest$fgetrecyclerView(r18).setLayoutParams(LayoutHelper.createFrame(-1, -1.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 0.0f, 44.0f, 0.0f, 136.0f));
                this.this$1.shareButton.setLayoutParams(LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 0.0f, 16.0f, 72.0f));
                this.this$1.scanButtonWrap.setLayoutParams(LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 0.0f, 16.0f, 16.0f));
                goto L12
            L11:
                ThemeListViewController.m18444$$Nest$fgetrecyclerView(r18).setLayoutParams(LayoutHelper.createFrame(-1, -1.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 0.0f, 44.0f, 0.0f, 80.0f));
                this.this$1.shareButton.setLayoutParams(LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 0.0f, 16.0f, 16.0f));
                goto L12
            }

            @Override // android.view.View
            public boolean verifyDrawable(Drawable r2) {
                if (r2 != ThemeListViewController.m18435$$Nest$fgetbackgroundDrawable(this.this$1)) goto L5;
                return true;
            L5:
                if (super.verifyDrawable(r2) == true) goto L11;
                return false;
            L11:
                return true;
            }
        }

        /* renamed from: org.telegram.ui.QrActivity$ThemeListViewController$3, reason: invalid class name */
        public class AnonymousClass3 extends RLottieImageView {
            final /* synthetic */ ThemeListViewController this$1;
            final /* synthetic */ QrActivity val$this$0;

            public AnonymousClass3(ThemeListViewController r1, Context r2, QrActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
                super.onInitializeAccessibilityNodeInfo(r1);
                if (QrActivity.m18410$$Nest$fgetisCurrentThemeDark(this.this$1.this$0) == false) goto L6;
                r1.setText(LocaleController.getString(R.string.AccDescrSwitchToDayTheme));
                return;
            L6:
                r1.setText(LocaleController.getString(R.string.AccDescrSwitchToNightTheme));
            }
        }

        /* renamed from: org.telegram.ui.QrActivity$ThemeListViewController$4, reason: invalid class name */
        public class AnonymousClass4 extends RecyclerView.OnScrollListener {
            final /* synthetic */ ThemeListViewController this$1;
            final /* synthetic */ QrActivity val$this$0;
            private int yScroll;

            public AnonymousClass4(ThemeListViewController r1, QrActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
                this.yScroll = 0;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                super.onScrolled(r1, r2, r3);
                this.yScroll += r3;
                ThemeListViewController.m18445$$Nest$fgettopShadow(this.this$1).setAlpha((this.yScroll * 1.0f) / AndroidUtilities.dp(6.0f));
            }
        }

        /* renamed from: org.telegram.ui.QrActivity$ThemeListViewController$5, reason: invalid class name */
        public class AnonymousClass5 extends View {
            final /* synthetic */ ThemeListViewController this$1;
            final /* synthetic */ Bitmap val$bitmap;
            final /* synthetic */ Canvas val$bitmapCanvas;
            final /* synthetic */ Paint val$bitmapPaint;
            final /* synthetic */ float val$cx;
            final /* synthetic */ float val$cy;
            final /* synthetic */ boolean val$isDark;
            final /* synthetic */ float val$r;
            final /* synthetic */ float val$x;
            final /* synthetic */ Paint val$xRefPaint;
            final /* synthetic */ float val$y;

            public AnonymousClass5(ThemeListViewController r1, Context r2, boolean r3, Canvas r4, float r5, float r6, float r7, Paint r8, Bitmap r9, Paint r10, float r11, float r12) {
                this.this$1 = r1;
                this.val$isDark = r3;
                this.val$bitmapCanvas = r4;
                this.val$cx = r5;
                this.val$cy = r6;
                this.val$r = r7;
                this.val$xRefPaint = r8;
                this.val$bitmap = r9;
                this.val$bitmapPaint = r10;
                this.val$x = r11;
                this.val$y = r12;
                super(r2);
            }

            @Override // android.view.View
            public void onDraw(Canvas r7) {
                super.onDraw(r7);
                if (this.val$isDark == true) goto L5;
                r7.drawCircle(this.val$cx, this.val$cy, this.val$r * (1.0f - ThemeListViewController.m18439$$Nest$fgetchangeDayNightViewProgress(this.this$1)), this.val$bitmapPaint);
            L9:
                r7.save();
                r7.translate(this.val$x, this.val$y);
                ThemeListViewController.m18441$$Nest$fgetdarkThemeView(this.this$1).draw(r7);
                r7.restore();
                return;
            L5:
                if (ThemeListViewController.m18439$$Nest$fgetchangeDayNightViewProgress(this.this$1) <= 0.0f) goto L7;
                this.val$bitmapCanvas.drawCircle(this.val$cx, this.val$cy, this.val$r * ThemeListViewController.m18439$$Nest$fgetchangeDayNightViewProgress(this.this$1), this.val$xRefPaint);
            L7:
                r7.drawBitmap(this.val$bitmap, 0.0f, 0.0f, this.val$bitmapPaint);
                goto L9
            }
        }

        /* renamed from: org.telegram.ui.QrActivity$ThemeListViewController$6, reason: invalid class name */
        public class AnonymousClass6 extends AnimatorListenerAdapter {
            final /* synthetic */ ThemeListViewController this$1;

            public AnonymousClass6(ThemeListViewController r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                if (ThemeListViewController.m18438$$Nest$fgetchangeDayNightView(this.this$1) != null) goto L5;
            L8:
                ThemeListViewController.m18447$$Nest$fputchangeDayNightViewAnimator(this.this$1, null);
                super.onAnimationEnd(r4);
                return;
            L5:
                if (ThemeListViewController.m18438$$Nest$fgetchangeDayNightView(this.this$1).getParent() == null) goto L7;
                ((ViewGroup) ThemeListViewController.m18438$$Nest$fgetchangeDayNightView(this.this$1).getParent()).removeView(ThemeListViewController.m18438$$Nest$fgetchangeDayNightView(this.this$1));
            L7:
                ThemeListViewController.m18446$$Nest$fputchangeDayNightView(this.this$1, null);
                goto L8
            }
        }

        /* renamed from: org.telegram.ui.QrActivity$ThemeListViewController$7, reason: invalid class name */
        public class AnonymousClass7 implements ThemeDescription.ThemeDescriptionDelegate {
            private boolean isAnimationStarted;
            final /* synthetic */ ThemeListViewController this$1;

            public AnonymousClass7(ThemeListViewController r1) {
                this.this$1 = r1;
                this.isAnimationStarted = false;
            }

            @Override // org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate
            public void didSetColor() {
            }

            @Override // org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate
            public void onAnimationProgress(float r5) {
                if (r5 == 0.0f) goto L5;
            L7:
                ThemeListViewController.m18440$$Nest$fgetdarkThemeDrawable(this.this$1).setColorFilter(new PorterDuffColorFilter(ThemeListViewController.m18442$$Nest$fgetfragment(this.this$1).getThemedColor(Theme.key_featuredStickers_addButton), PorterDuff.Mode.SRC_IN));
                ThemeListViewController r0 = this.this$1;
                if (r0.isLightDarkChangeAnimation == false) goto L11;
                ThemeListViewController.m18453$$Nest$msetItemsAnimationProgress(r0, r5);
            L11:
                if (r5 == 1.0f) goto L13;
                return;
            L13:
                if (this.isAnimationStarted == false) goto L17;
                ThemeListViewController r52 = this.this$1;
                r52.isLightDarkChangeAnimation = false;
                ThemeListViewController.m18451$$Nest$monAnimationEnd(r52);
                this.isAnimationStarted = false;
                return;
            L17:
                return;
            L5:
                if (this.isAnimationStarted == true) goto L7;
                ThemeListViewController.m18452$$Nest$monAnimationStart(this.this$1);
                this.isAnimationStarted = true;
                goto L7
            }
        }

        public static /* synthetic */ void $r8$lambda$RBVExwMYNnNr76FhuKZHnM2Zdz0(ThemeListViewController r0, boolean r1) {
            r0.lambda$setupLightDarkTheme$3(r1);
        }

        /* renamed from: $r8$lambda$Zx9CFwGcON-1sJn-2HF8-D06yYs, reason: not valid java name */
        public static /* synthetic */ void m18433$r8$lambda$Zx9CFwGcON1sJn2HF8D06yYs(ThemeListViewController r0, ValueAnimator r1) {
            r0.lambda$setupLightDarkTheme$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$cMM7TrcYdg2HB1GpMr_gZADfIUc(ThemeListViewController r0, int r1) {
            r0.lambda$onItemClicked$1(r1);
        }

        /* renamed from: $r8$lambda$e3bpb41lHzY0dMOprklAI5uqL-U, reason: not valid java name */
        public static /* synthetic */ void m18434$r8$lambda$e3bpb41lHzY0dMOprklAI5uqLU(ThemeListViewController r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: -$$Nest$fgetbackgroundDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m18435$$Nest$fgetbackgroundDrawable(ThemeListViewController r0) {
            return r0.backgroundDrawable;
        }

        /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m18436$$Nest$fgetbackgroundPaint(ThemeListViewController r0) {
            return r0.backgroundPaint;
        }

        /* renamed from: -$$Nest$fgetbottomShadow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m18437$$Nest$fgetbottomShadow(ThemeListViewController r0) {
            return r0.bottomShadow;
        }

        /* renamed from: -$$Nest$fgetchangeDayNightView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m18438$$Nest$fgetchangeDayNightView(ThemeListViewController r0) {
            return r0.changeDayNightView;
        }

        /* renamed from: -$$Nest$fgetchangeDayNightViewProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m18439$$Nest$fgetchangeDayNightViewProgress(ThemeListViewController r0) {
            return r0.changeDayNightViewProgress;
        }

        /* renamed from: -$$Nest$fgetdarkThemeDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieDrawable m18440$$Nest$fgetdarkThemeDrawable(ThemeListViewController r0) {
            return r0.darkThemeDrawable;
        }

        /* renamed from: -$$Nest$fgetdarkThemeView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieImageView m18441$$Nest$fgetdarkThemeView(ThemeListViewController r0) {
            return r0.darkThemeView;
        }

        /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BaseFragment m18442$$Nest$fgetfragment(ThemeListViewController r0) {
            return r0.fragment;
        }

        /* renamed from: -$$Nest$fgetprevIsPortrait, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m18443$$Nest$fgetprevIsPortrait(ThemeListViewController r0) {
            return r0.prevIsPortrait;
        }

        /* renamed from: -$$Nest$fgetrecyclerView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerListView m18444$$Nest$fgetrecyclerView(ThemeListViewController r0) {
            return r0.recyclerView;
        }

        /* renamed from: -$$Nest$fgettopShadow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m18445$$Nest$fgettopShadow(ThemeListViewController r0) {
            return r0.topShadow;
        }

        /* renamed from: -$$Nest$fputchangeDayNightView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18446$$Nest$fputchangeDayNightView(ThemeListViewController r0, View r1) {
            r0.changeDayNightView = r1;
        }

        /* renamed from: -$$Nest$fputchangeDayNightViewAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18447$$Nest$fputchangeDayNightViewAnimator(ThemeListViewController r0, ValueAnimator r1) {
            r0.changeDayNightViewAnimator = r1;
        }

        /* renamed from: -$$Nest$fputlayoutManager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18448$$Nest$fputlayoutManager(ThemeListViewController r0, LinearLayoutManager r1) {
            r0.layoutManager = r1;
        }

        /* renamed from: -$$Nest$fputprevIsPortrait, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18449$$Nest$fputprevIsPortrait(ThemeListViewController r0, boolean r1) {
            r0.prevIsPortrait = r1;
        }

        /* renamed from: -$$Nest$mgetLayoutManager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LinearLayoutManager m18450$$Nest$mgetLayoutManager(ThemeListViewController r0, boolean r1) {
            return r0.getLayoutManager(r1);
        }

        /* renamed from: -$$Nest$monAnimationEnd, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18451$$Nest$monAnimationEnd(ThemeListViewController r0) {
            r0.onAnimationEnd();
        }

        /* renamed from: -$$Nest$monAnimationStart, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18452$$Nest$monAnimationStart(ThemeListViewController r0) {
            r0.onAnimationStart();
        }

        /* renamed from: -$$Nest$msetItemsAnimationProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18453$$Nest$msetItemsAnimationProgress(ThemeListViewController r0, float r1) {
            r0.setItemsAnimationProgress(r1);
        }

        public ThemeListViewController(QrActivity r23, BaseFragment r24, Window r25) {
            this.this$0 = r23;
            this.backgroundPaint = new Paint(1);
            this.prevSelectedPosition = -1;
            this.fragment = r24;
            this.window = r25;
            Activity r3 = r24.getParentActivity();
            this.scroller = new AnonymousClass1(this, r3, r23);
            Drawable r5 = r3.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
            this.backgroundDrawable = r5;
            int r7 = r24.getThemedColor(Theme.key_dialogBackground);
            PorterDuff.Mode r8 = PorterDuff.Mode.MULTIPLY;
            r5.setColorFilter(new PorterDuffColorFilter(r7, r8));
            AnonymousClass2 r52 = new AnonymousClass2(this, r3, r23, r24);
            this.rootLayout = r52;
            TextView r6 = new TextView(r3);
            this.titleView = r6;
            r6.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            r6.setLines(1);
            r6.setSingleLine(true);
            r6.setTextColor(r24.getThemedColor(Theme.key_dialogTextBlack));
            r6.setTextSize(1, 20.0f);
            r6.setTypeface(AndroidUtilities.bold());
            r6.setPadding(AndroidUtilities.dp(21.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(21.0f), AndroidUtilities.dp(8.0f));
            r52.addView(r6, LayoutHelper.createFrame(-1, -2.0f, 8388659, 0.0f, 0.0f, 62.0f, 0.0f));
            int r62 = Theme.key_featuredStickers_addButton;
            int r72 = r24.getThemedColor(r62);
            int r12 = AndroidUtilities.dp(28.0f);
            RLottieDrawable r10 = new RLottieDrawable(R.raw.sun_outline, r12, r12, false, null);
            this.darkThemeDrawable = r10;
            this.forceDark = !Theme.getActiveTheme().isDark();
            setForceDark(Theme.getActiveTheme().isDark(), false);
            r10.setPlayInDirectionOfCustomEndFrame(true);
            r10.setColorFilter(new PorterDuffColorFilter(r72, PorterDuff.Mode.SRC_IN));
            AnonymousClass3 r73 = new AnonymousClass3(this, r3, r23);
            this.darkThemeView = r73;
            r73.setAnimation(r10);
            r73.setScaleType(ImageView.ScaleType.CENTER);
            r73.setOnClickListener(new QrActivity$ThemeListViewController$$ExternalSyntheticLambda0(this));
            r73.setAlpha(0.0f);
            r73.setVisibility(4);
            r52.addView(r73, LayoutHelper.createFrame(44, 44.0f, 8388661, 0.0f, -2.0f, 7.0f, 0.0f));
            FlickerLoadingView r74 = new FlickerLoadingView(r3, r24.getResourceProvider());
            this.progressView = r74;
            r74.setVisibility(0);
            r52.addView(r74, LayoutHelper.createFrame(-1, 104.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 0.0f, 44.0f, 0.0f, 0.0f));
            this.prevIsPortrait = true;
            RecyclerListView r75 = new RecyclerListView(r3);
            this.recyclerView = r75;
            ChatThemeBottomSheet.Adapter r102 = new ChatThemeBottomSheet.Adapter(QrActivity.access$100(r23), QrActivity.m18417$$Nest$fgetresourcesProvider(r23), 2);
            this.adapter = r102;
            r75.setAdapter(r102);
            r75.setClipChildren(false);
            r75.setClipToPadding(false);
            r75.setItemAnimator(null);
            r75.setNestedScrollingEnabled(false);
            LinearLayoutManager r122 = getLayoutManager(this.prevIsPortrait);
            this.layoutManager = r122;
            r75.setLayoutManager(r122);
            r75.setOnItemClickListener(new QrActivity$ThemeListViewController$$ExternalSyntheticLambda1(this));
            r75.setOnScrollListener(new AnonymousClass4(this, r23));
            r52.addView(r75);
            View r76 = new View(r3);
            this.topShadow = r76;
            r76.setAlpha(0.0f);
            r76.setBackground(ContextCompat.getDrawable(r3, R.drawable.shadowdown));
            r76.setRotation(180.0f);
            r52.addView(r76);
            View r77 = new View(r3);
            this.bottomShadow = r77;
            r77.setBackground(ContextCompat.getDrawable(r3, R.drawable.shadowdown));
            r52.addView(r77);
            TextView r78 = new TextView(r3);
            this.shareButton = r78;
            r78.setBackground(Theme.AdaptiveRipple.filledRect(r24.getThemedColor(r62), new float[]{24.0f}));
            TextUtils.TruncateAt r9 = TextUtils.TruncateAt.END;
            r78.setEllipsize(r9);
            r78.setGravity(17);
            r78.setLines(1);
            r78.setSingleLine(true);
            r78.setText(LocaleController.getString(R.string.ShareQrCode));
            r78.setTextColor(r24.getThemedColor(Theme.key_featuredStickers_buttonText));
            r78.setTextSize(1, 15.0f);
            r78.setTypeface(AndroidUtilities.bold());
            r52.addView(r78);
            if (UserConfig.getInstance(QrActivity.access$200(r23)).getClientUserId() != QrActivity.m18420$$Nest$fgetuserId(r23)) goto L6;
            LinearLayout r1 = new LinearLayout(r3);
            this.scanButtonWrap = r1;
            r1.setBackground(Theme.AdaptiveRipple.createRect(ColorUtils.setAlphaComponent(Theme.AdaptiveRipple.calcRippleColor(r24.getThemedColor(r62)), 25), new float[]{24.0f}));
            r1.setOrientation(0);
            r1.setGravity(17);
            ImageView r79 = new ImageView(r3);
            this.scanButtonIcon = r79;
            r79.setLayoutParams(LayoutHelper.createLinear(24, 24, 17, 0, 0, 10, 0));
            r79.setImageResource(R.drawable.profile_qr_scan_24);
            r79.setColorFilter(new PorterDuffColorFilter(r24.getThemedColor(r62), r8));
            r1.addView(r79);
            TextView r710 = new TextView(r3);
            this.scanButton = r710;
            r710.setEllipsize(r9);
            r710.setGravity(17);
            r710.setLines(1);
            r710.setSingleLine(true);
            r710.setText(LocaleController.getString(R.string.ScanQrCode));
            r710.setTextColor(r24.getThemedColor(r62));
            r710.setTextSize(1, 15.0f);
            r710.setTypeface(AndroidUtilities.bold());
            r1.addView(r710);
            r52.addView(r1);
            return;
        L6:
            this.scanButtonWrap = null;
            this.scanButtonIcon = null;
            this.scanButton = null;
        }

        private LinearLayoutManager getLayoutManager(boolean r4) {
            BaseFragment r3 = this.fragment;
            if (r4 == false) goto L7;
            return new LinearLayoutManager(r3.getParentActivity(), 0, false);
        L7:
            return new GridLayoutManager(r3.getParentActivity(), 3, 1, false);
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            if (this.changeDayNightViewAnimator == null) goto L5;
            return;
        L5:
            setupLightDarkTheme(!this.forceDark);
        }

        private /* synthetic */ void lambda$onItemClicked$1(int r4) {
            RecyclerView.LayoutManager r0 = this.recyclerView.getLayoutManager();
            if (r0 != null) goto L5;
        L12:
            this.prevSelectedPosition = r4;
            return;
        L5:
            if (this.prevIsPortrait == true) goto L7;
            int r1 = r4;
        L11:
            this.scroller.setTargetPosition(r1);
            r0.startSmoothScroll(this.scroller);
            goto L12
        L7:
            if (r4 <= this.prevSelectedPosition) goto L9;
            r1 = Math.min(r4 + 1, this.adapter.items.size() - 1);
            goto L11
        L9:
            r1 = Math.max(r4 - 1, 0);
            goto L11
        }

        private /* synthetic */ void lambda$setupLightDarkTheme$2(ValueAnimator r1) {
            this.changeDayNightViewProgress = ((Float) r1.getAnimatedValue()).floatValue();
            this.changeDayNightView.invalidate();
        }

        private /* synthetic */ void lambda$setupLightDarkTheme$3(boolean r5) {
            ChatThemeBottomSheet.Adapter r0 = this.adapter;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.items == null) goto L21;
            setForceDark(r5, true);
            if (this.selectedItem == null) goto L11;
            this.isLightDarkChangeAnimation = true;
            setDarkTheme(r5);
        L11:
            if (this.adapter.items == null) goto L20;
            int r02 = 0;
        L14:
            if (r02 >= this.adapter.items.size()) goto L16;
            ChatThemeBottomSheet.ChatThemeItem r1 = this.adapter.items.get(r02);
            r1.themeIndex = r5 ? 1 : 0;
            ChatThemeBottomSheet.ChatThemeItem r12 = this.adapter.items.get(r02);
            r12.icon = QrActivity.m18426$$Nest$mgetEmojiThemeIcon(this.this$0, this.adapter.items.get(r02).chatTheme, r5);
            r02 = r02 + 1;
            goto L14
        L16:
            QrActivity.m18425$$Nest$fputtempMotionDrawable(this.this$0, null);
            this.adapter.notifyDataSetChanged();
            return;
        L20:
            return;
        }

        private void onAnimationEnd() {
            this.isLightDarkChangeAnimation = false;
        }

        private void onAnimationStart() {
            ChatThemeBottomSheet.Adapter r0 = this.adapter;
            if (r0 == null) goto L11;
            List<ChatThemeBottomSheet.ChatThemeItem> r02 = r0.items;
            if (r02 == null) goto L11;
            Iterator<ChatThemeBottomSheet.ChatThemeItem> r03 = r02.iterator();
        L8:
            if (r03.hasNext() == false) goto L11;
            ChatThemeBottomSheet.ChatThemeItem r1 = r03.next();
            r1.themeIndex = this.forceDark ? 1 : 0;
        L11:
            if (this.isLightDarkChangeAnimation == true) goto L15;
            setItemsAnimationProgress(1.0f);
            return;
        }

        private void setItemsAnimationProgress(float r3) {
            int r0 = 0;
        L4:
            if (r0 >= this.adapter.getItemCount()) goto L6;
            this.adapter.items.get(r0).animationProgress = r3;
            r0 = r0 + 1;
            goto L4
        }

        @SuppressLint({"NotifyDataSetChanged"})
        private void setupLightDarkTheme(boolean r17) {
            ValueAnimator r0 = this.changeDayNightViewAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            FrameLayout r02 = (FrameLayout) this.fragment.getParentActivity().getWindow().getDecorView();
            FrameLayout r13 = (FrameLayout) this.window.getDecorView();
            Bitmap r9 = Bitmap.createBitmap(r13.getWidth(), r13.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas r4 = new Canvas(r9);
            this.darkThemeView.setAlpha(0.0f);
            r02.draw(r4);
            r13.draw(r4);
            this.darkThemeView.setAlpha(1.0f);
            Paint r8 = new Paint(1);
            r8.setColor(-16777216);
            r8.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            Paint r10 = new Paint(1);
            r10.setFilterBitmap(true);
            int[] r2 = new int[2];
            this.darkThemeView.getLocationInWindow(r2);
            float r11 = r2[0];
            float r12 = r2[1];
            Shader.TileMode r22 = Shader.TileMode.CLAMP;
            r10.setShader(new BitmapShader(r9, r22, r22));
            this.changeDayNightView = new AnonymousClass5(this, this.fragment.getParentActivity(), r17, r4, r11 + (this.darkThemeView.getMeasuredWidth() / 2.0f), r12 + (this.darkThemeView.getMeasuredHeight() / 2.0f), Math.max(r9.getHeight(), r9.getWidth()) * 0.9f, r8, r9, r10, r11, r12);
            this.changeDayNightViewProgress = 0.0f;
            ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.changeDayNightViewAnimator = r03;
            r03.addUpdateListener(new QrActivity$ThemeListViewController$$ExternalSyntheticLambda3(this));
            this.changeDayNightViewAnimator.addListener(new AnonymousClass6(this));
            this.changeDayNightViewAnimator.setDuration(400);
            this.changeDayNightViewAnimator.setInterpolator(Easings.easeInOutQuad);
            this.changeDayNightViewAnimator.start();
            r13.addView(this.changeDayNightView, new ViewGroup.LayoutParams(-1, -1));
            AndroidUtilities.runOnUIThread(new QrActivity$ThemeListViewController$$ExternalSyntheticLambda4(this, r17));
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        @SuppressLint({"NotifyDataSetChanged"})
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.emojiLoaded) goto L6;
            this.adapter.notifyDataSetChanged();
            return;
        }

        public ArrayList<ThemeDescription> getThemeDescriptions() {
            AnonymousClass7 r7 = new AnonymousClass7(this);
            ArrayList<ThemeDescription> r9 = new ArrayList();
            int r12 = ThemeDescription.FLAG_BACKGROUND;
            Paint r14 = this.backgroundPaint;
            int r17 = Theme.key_dialogBackground;
            r9.add(new ThemeDescription(null, r12, null, r14, null, null, r17));
            r9.add(new ThemeDescription(null, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, new Drawable[]{this.backgroundDrawable}, r7, r17));
            r9.add(new ThemeDescription(this.titleView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_dialogTextBlack));
            r9.add(new ThemeDescription(this.recyclerView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ThemeSmallPreviewView.class}, null, null, null, Theme.key_dialogBackgroundGray));
            int r1 = r9.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            ThemeDescription r3 = r9.get(r2);
            r2 = r2 + 1;
            Theme.ResourcesProvider r4 = this.fragment.getResourceProvider();
            r3.resourcesProvider = r4;
            goto L3
        L5:
            return r9;
        }

        public void onCreate() {
            ChatThemeController r0 = ChatThemeController.getInstance(QrActivity.access$300(this.this$0));
            r0.preloadAllWallpaperThumbs(true);
            r0.preloadAllWallpaperThumbs(false);
            r0.preloadAllWallpaperImages(true);
            r0.preloadAllWallpaperImages(false);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        }

        public void onDataLoaded() {
            this.darkThemeView.setAlpha(0.0f);
            this.darkThemeView.animate().alpha(1.0f).setDuration(150).start();
            this.darkThemeView.setVisibility(0);
            this.progressView.animate().alpha(0.0f).setListener(new HideViewAfterAnimation(this.progressView)).setDuration(150).start();
            this.recyclerView.setAlpha(0.0f);
            this.recyclerView.animate().alpha(1.0f).setDuration(150).start();
        }

        public void onDestroy() {
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        }

        public void onItemClicked(View r6, int r7) {
            if (this.adapter.items.get(r7) != this.selectedItem) goto L5;
            return;
        L5:
            if (this.changeDayNightView != null) goto L26;
            int r0 = 0;
            this.isLightDarkChangeAnimation = false;
            this.selectedItem = this.adapter.items.get(r7);
            this.adapter.setSelectedItem(r7);
            this.rootLayout.postDelayed(new QrActivity$ThemeListViewController$$ExternalSyntheticLambda2(this, r7), 100);
        L9:
            if (r0 >= this.recyclerView.getChildCount()) goto L15;
            ThemeSmallPreviewView r1 = (ThemeSmallPreviewView) this.recyclerView.getChildAt(r0);
            if (r1 == r6) goto L13;
            r1.cancelAnimation();
        L13:
            r0 = r0 + 1;
            goto L9
        L15:
            if (this.adapter.items.get(r7).chatTheme.showAsDefaultStub == true) goto L17;
            ((ThemeSmallPreviewView) r6).playEmojiAnimation();
        L17:
            OnItemSelectedListener r62 = this.itemSelectedListener;
            if (r62 == null) goto L25;
            r62.onItemSelected(this.selectedItem.chatTheme, r7);
            return;
        L25:
            return;
        }

        public void setDarkTheme(boolean r3) {
            QrActivity.m18421$$Nest$fputisCurrentThemeDark(this.this$0, r3);
            QrActivity r32 = this.this$0;
            QrActivity.m18428$$Nest$monItemSelected(r32, QrActivity.m18408$$Nest$fgetcurrentTheme(r32), QrActivity.m18418$$Nest$fgetselectedPosition(this.this$0), false);
        }

        public void setForceDark(boolean r4, boolean r5) {
            if (this.forceDark == r4) goto L21;
            this.forceDark = r4;
            if (r4 == false) goto L8;
            int r42 = this.darkThemeDrawable.getFramesCount() - 1;
        L9:
            RLottieDrawable r2 = this.darkThemeDrawable;
            if (r5 == false) goto L15;
            r2.setCustomEndFrame(r42);
            RLottieImageView r3 = this.darkThemeView;
            if (r3 == null) goto L19;
            r3.playAnimation();
            return;
        L19:
            return;
        L15:
            r2.setCustomEndFrame(r42);
            this.darkThemeDrawable.setCurrentFrame(r42, false, true);
            RLottieImageView r32 = this.darkThemeView;
            if (r32 == null) goto L20;
            r32.invalidate();
            return;
        L20:
            return;
        L8:
            r42 = 0;
            goto L9
        }

        public void setItemSelectedListener(OnItemSelectedListener r1) {
            this.itemSelectedListener = r1;
        }

        public void setSelectedPosition(int r2) {
            this.prevSelectedPosition = r2;
            this.adapter.setSelectedItem(r2);
            if (r2 > 0) goto L5;
        L7:
            this.layoutManager.scrollToPositionWithOffset(Math.min(r2, this.adapter.items.size() - 1), 0);
            return;
        L5:
            if (r2 >= (this.adapter.items.size() / 2)) goto L7;
            r2 = r2 - 1;
            goto L7
        }
    }

    public class ThemeResourcesProvider implements Theme.ResourcesProvider {
        private SparseIntArray colors;
        final /* synthetic */ QrActivity this$0;

        private ThemeResourcesProvider(QrActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ThemeResourcesProvider(QrActivity r1, QrActivityIA r2) {
            this(r1);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public int getColor(int r1) {
            SparseIntArray r0 = this.colors;
            if (r0 == null) goto L7;
            return r0.get(r1);
        L7:
            return Theme.getColor(r1);
        }

        public void initColors(EmojiThemes r2, boolean r3) {
            this.colors = r2.createColors(QrActivity.access$000(this.this$0), r3 ? 1 : 0);
        }
    }

    /* renamed from: $r8$lambda$0E-yDDa2czSNDrWDfHsuloQFS0c, reason: not valid java name */
    public static /* synthetic */ void m18397$r8$lambda$0EyDDa2czSNDrWDfHsuloQFS0c(QrActivity r0, ValueAnimator r1) {
        r0.lambda$onPatternLoaded$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$1yU5od2VlculF5H92hPnUx69eiI(QrActivity r0) {
        r0.lambda$createView$6();
    }

    public static /* synthetic */ void $r8$lambda$2vgo0PfNSJe1kffmuNNovBbAw34(QrActivity r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    /* renamed from: $r8$lambda$6Ol5ZyKHau-3mV6WeLoo8YntNAU, reason: not valid java name */
    public static /* synthetic */ void m18398$r8$lambda$6Ol5ZyKHau3mV6WeLoo8YntNAU(QrActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$6qDYGYCWbwydrms_sSvEGiTem54(QrActivity r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$E7rBsS4H1foN5vAZS5f1iJ3Fb8M(QrActivity r0, boolean r1, long r2, Pair r4) {
        r0.lambda$onItemSelected$9(r1, r2, r4);
    }

    /* renamed from: $r8$lambda$HGhk549-Po2R11im-coamo1Q1G4, reason: not valid java name */
    public static /* synthetic */ void m18399$r8$lambda$HGhk549Po2R11imcoamo1Q1G4(QrActivity r0) {
        r0.lambda$onItemSelected$11();
    }

    public static /* synthetic */ void $r8$lambda$HJZN_0TGgwGmG17uo3py87IaJLk(QrActivity r0) {
        r0.lambda$createView$5();
    }

    public static /* synthetic */ void $r8$lambda$HvPgaCA4woSToVqpz1DsTqj8wX0(QrActivity r0, boolean r1, EmojiThemes r2, INavigationLayout.ThemeAnimationSettings r3) {
        r0.lambda$onItemSelected$14(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$ZaQqQ5gdXrQ-njxE3yfVPKU9BIA, reason: not valid java name */
    public static /* synthetic */ void m18400$r8$lambda$ZaQqQ5gdXrQnjxE3yfVPKU9BIA(QrActivity r0, EmojiThemes r1, int r2) {
        r0.lambda$createView$2(r1, r2);
    }

    /* renamed from: $r8$lambda$al0FQBqRO-YKmPKAUSgY9MzTQrs, reason: not valid java name */
    public static /* synthetic */ void m18401$r8$lambda$al0FQBqROYKmPKAUSgY9MzTQrs(QrActivity r0) {
        r0.lambda$performShare$15();
    }

    /* renamed from: $r8$lambda$bazVbo57L8MBAx5Lzaf_PwBC-aM, reason: not valid java name */
    public static /* synthetic */ void m18402$r8$lambda$bazVbo57L8MBAx5Lzaf_PwBCaM(QrActivity r0, int[] r1, ValueAnimator r2) {
        r0.lambda$onItemSelected$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ckJRUMicL1tJepVNyjSKHGDKVOo(QrActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$h0Pu4sJtj6Cq26Sh0ocUCroXerk(QrActivity r0) {
        r0.lambda$createView$7();
    }

    public static /* synthetic */ void $r8$lambda$hSuIWYCdEfxAg7xhvnjo8109pHk(QrActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onRequestPermissionsResultFragment$16(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$iVhGplJYd5kRJz3lIBb8aSVV70g(QrActivity r0, int r1, int r2, int r3, int r4) {
        r0.lambda$createView$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$ky9AvYooqHeFZmAfw7jzxb5j6mw(QrActivity r0) {
        r0.lambda$getThemeDescriptions$17();
    }

    public static /* synthetic */ void $r8$lambda$npsqP65DwQjDRKJQiafjUDlVphk(QrActivity r0) {
        r0.lambda$onItemSelected$13();
    }

    /* renamed from: $r8$lambda$rnGJxc11l7T187qaJPi7L9CS-7E, reason: not valid java name */
    public static /* synthetic */ void m18403$r8$lambda$rnGJxc11l7T187qaJPi7L9CS7E(QrActivity r0, Bitmap r1) {
        r0.lambda$onItemSelected$10(r1);
    }

    /* renamed from: -$$Nest$fgetavatarImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m18404$$Nest$fgetavatarImageView(QrActivity r0) {
        return r0.avatarImageView;
    }

    /* renamed from: -$$Nest$fgetbackgroundView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m18405$$Nest$fgetbackgroundView(QrActivity r0) {
        return r0.backgroundView;
    }

    /* renamed from: -$$Nest$fgetcloseImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m18406$$Nest$fgetcloseImageView(QrActivity r0) {
        return r0.closeImageView;
    }

    /* renamed from: -$$Nest$fgetcurrMotionDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MotionBackgroundDrawable m18407$$Nest$fgetcurrMotionDrawable(QrActivity r0) {
        return r0.currMotionDrawable;
    }

    /* renamed from: -$$Nest$fgetcurrentTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiThemes m18408$$Nest$fgetcurrentTheme(QrActivity r0) {
        return r0.currentTheme;
    }

    /* renamed from: -$$Nest$fgetinsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Insets m18409$$Nest$fgetinsets(QrActivity r0) {
        return r0.insets;
    }

    /* renamed from: -$$Nest$fgetisCurrentThemeDark, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18410$$Nest$fgetisCurrentThemeDark(QrActivity r0) {
        return r0.isCurrentThemeDark;
    }

    /* renamed from: -$$Nest$fgetisFragmentViewPortrait, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18411$$Nest$fgetisFragmentViewPortrait(QrActivity r0) {
        return r0.isFragmentViewPortrait;
    }

    /* renamed from: -$$Nest$fgetlogoImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m18412$$Nest$fgetlogoImageView(QrActivity r0) {
        return r0.logoImageView;
    }

    /* renamed from: -$$Nest$fgetlogoRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m18413$$Nest$fgetlogoRect(QrActivity r0) {
        return r0.logoRect;
    }

    /* renamed from: -$$Nest$fgetprevMotionDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MotionBackgroundDrawable m18414$$Nest$fgetprevMotionDrawable(QrActivity r0) {
        return r0.prevMotionDrawable;
    }

    /* renamed from: -$$Nest$fgetprevQrColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m18415$$Nest$fgetprevQrColors(QrActivity r0) {
        return r0.prevQrColors;
    }

    /* renamed from: -$$Nest$fgetqrView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ QrView m18416$$Nest$fgetqrView(QrActivity r0) {
        return r0.qrView;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ThemeResourcesProvider m18417$$Nest$fgetresourcesProvider(QrActivity r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetselectedPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18418$$Nest$fgetselectedPosition(QrActivity r0) {
        return r0.selectedPosition;
    }

    /* renamed from: -$$Nest$fgetthemeLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m18419$$Nest$fgetthemeLayout(QrActivity r0) {
        return r0.themeLayout;
    }

    /* renamed from: -$$Nest$fgetuserId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m18420$$Nest$fgetuserId(QrActivity r2) {
        return r2.userId;
    }

    /* renamed from: -$$Nest$fputisCurrentThemeDark, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18421$$Nest$fputisCurrentThemeDark(QrActivity r0, boolean r1) {
        r0.isCurrentThemeDark = r1;
    }

    /* renamed from: -$$Nest$fputisFragmentViewPortrait, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18422$$Nest$fputisFragmentViewPortrait(QrActivity r0, boolean r1) {
        r0.isFragmentViewPortrait = r1;
    }

    /* renamed from: -$$Nest$fputpatternAlphaAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18423$$Nest$fputpatternAlphaAnimator(QrActivity r0, ValueAnimator r1) {
        r0.patternAlphaAnimator = r1;
    }

    /* renamed from: -$$Nest$fputprevMotionDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18424$$Nest$fputprevMotionDrawable(QrActivity r0, MotionBackgroundDrawable r1) {
        r0.prevMotionDrawable = r1;
    }

    /* renamed from: -$$Nest$fputtempMotionDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18425$$Nest$fputtempMotionDrawable(QrActivity r0, MotionBackgroundDrawable r1) {
        r0.tempMotionDrawable = r1;
    }

    /* renamed from: -$$Nest$mgetEmojiThemeIcon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m18426$$Nest$mgetEmojiThemeIcon(QrActivity r0, EmojiThemes r1, boolean r2) {
        return r0.getEmojiThemeIcon(r1, r2);
    }

    /* renamed from: -$$Nest$monDataLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18427$$Nest$monDataLoaded(QrActivity r0, List r1) {
        r0.onDataLoaded(r1);
    }

    /* renamed from: -$$Nest$monItemSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18428$$Nest$monItemSelected(QrActivity r0, EmojiThemes r1, int r2, boolean r3) {
        r0.onItemSelected(r1, r2, r3);
    }

    /* renamed from: -$$Nest$sfputcachedThemes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18429$$Nest$sfputcachedThemes(List r0) {
        cachedThemes = r0;
    }

    static {
        ArrayMap<String, int[]> r0 = new ArrayMap();
        qrColorsMap = r0;
        r0.put("🏠d", new int[]{-9324972, -13856649, -6636738, -9915042});
        r0.put("🐥d", new int[]{-12344463, -7684788, -6442695, -8013488});
        r0.put("⛄d", new int[]{-10051073, -10897938, -12469550, -7694337});
        r0.put("💎d", new int[]{-11429643, -11814958, -5408261, -2128185});
        r0.put("👨\u200d🏫d", new int[]{-6637227, -12015466, -13198627, -10631557});
        r0.put("🌷d", new int[]{-1146812, -1991901, -1745517, -3443241});
        r0.put("💜d", new int[]{-1156738, -1876046, -5412366, -28073});
        r0.put("🎄d", new int[]{-1281978, -551386, -1870308, -742870});
        r0.put("🎮d", new int[]{-15092782, -2333964, -1684365, -1269214});
        r0.put("🏠n", new int[]{-15368239, -11899662, -15173939, -13850930});
        r0.put("🐥n", new int[]{-11033320, -14780848, -9594089, -12604587});
        r0.put("⛄n", new int[]{-13930790, -13665098, -14833975, -9732865});
        r0.put("💎n", new int[]{-5089608, -9481473, -14378302, -13337899});
        r0.put("👨\u200d🏫n", new int[]{-14447768, -9199261, -15356801, -15823723});
        r0.put("🌷n", new int[]{-2534316, -2984177, -3258783, -5480504});
        r0.put("💜n", new int[]{-3123030, -2067394, -2599576, -6067757});
        r0.put("🎄n", new int[]{-2725857, -3242459, -3248848, -3569123});
        r0.put("🎮n", new int[]{-3718333, -1278154, -16338695, -6076417});
        firstOpen = true;
    }

    public QrActivity(Bundle r3) {
        super(r3);
        this.resourcesProvider = new ThemeResourcesProvider(this, null);
        EmojiThemes r32 = EmojiThemes.createHomeQrTheme(this.currentAccount);
        this.homeTheme = r32;
        this.logoRect = new Rect();
        this.emojiThemeDarkIcons = new ArrayMap();
        this.prevQrColors = null;
        this.currMotionDrawable = new MotionBackgroundDrawable();
        this.currentTheme = r32;
        this.selectedPosition = -1;
        this.insets = Insets.NONE;
    }

    public static /* synthetic */ int access$000(QrActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(QrActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(QrActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(QrActivity r0) {
        return r0.currentAccount;
    }

    private void applyScreenSettings() {
        if (getParentActivity() == null) goto L6;
        getParentActivity().getWindow().getDecorView().setSystemUiVisibility(this.prevSystemUiVisibility | 1028);
        return;
    }

    private Bitmap getEmojiThemeIcon(EmojiThemes r10, boolean r11) {
        if (r11 == false) goto L14;
        Bitmap r112 = this.emojiThemeDarkIcons.get(r10.emoji);
        if (r112 != null) goto L12;
        r112 = Bitmap.createBitmap(this.emojiThemeIcon.getWidth(), this.emojiThemeIcon.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r0 = new Canvas(r112);
        int[] r1 = qrColorsMap.get(r10.emoji + "n");
        if (r1 != null) goto L8;
    L11:
        r0.drawBitmap(this.emojiThemeIcon, 0.0f, 0.0f, null);
        r0.setBitmap(null);
        this.emojiThemeDarkIcons.put(r10.emoji, r112);
        goto L12
    L8:
        if (this.tempMotionDrawable != null) goto L10;
        this.tempMotionDrawable = new MotionBackgroundDrawable(0, 0, 0, 0, true);
    L10:
        this.tempMotionDrawable.setColors(r1[0], r1[1], r1[2], r1[3]);
        this.tempMotionDrawable.setBounds(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), r0.getWidth() - AndroidUtilities.dp(6.0f), r0.getHeight() - AndroidUtilities.dp(6.0f));
        this.tempMotionDrawable.draw(r0);
    L12:
        return r112;
    L14:
        return this.emojiThemeIcon;
    }

    private /* synthetic */ void lambda$createView$0(int r2, int r3, int r4, int r5) {
        this.logoRect.set(r2, r3, r4, r5);
        this.qrView.requestLayout();
    }

    private /* synthetic */ void lambda$createView$1(View r1) {
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$2(EmojiThemes r2, int r3) {
        onItemSelected(r2, r3, true);
    }

    private /* synthetic */ void lambda$createView$3(View r2) {
        this.themesViewController.shareButton.setClickable(false);
        performShare();
    }

    private /* synthetic */ void lambda$createView$4(View r2) {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if (getParentActivity().checkSelfPermission("android.permission.CAMERA") == 0) goto L9;
        getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 34);
        return;
    L9:
        openCameraScanActivity(this);
    }

    private /* synthetic */ void lambda$createView$5() {
        onItemSelected(this.currentTheme, 0, true);
        RLottieDrawable r0 = this.logoImageView.getAnimatedDrawable();
        if (this.logoOptimal != null) goto L7;
        if (r0 == null) goto L8;
        this.logoOptimal = Bitmap.createBitmap(r0.getIntrinsicWidth(), r0.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        r0.prepareForGenerateCache();
        r0.setGeneratingFrame(33);
        r0.getNextFrame(this.logoOptimal);
        r0.releaseForGenerateCache();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$createView$6() {
        this.homeTheme.loadPreviewColors(this.currentAccount);
        View r0 = this.fragmentView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.postDelayed(new QrActivity$$ExternalSyntheticLambda11(this), 17);
    }

    private /* synthetic */ void lambda$createView$7() {
        firstOpen = false;
        List<EmojiThemes> r0 = cachedThemes;
        if (r0 != null) goto L5;
    L9:
        ChatThemeController.getInstance(this.currentAccount).requestAllChatThemes(new AnonymousClass3(this), true);
        return;
    L5:
        if (r0.isEmpty() == true) goto L9;
        onDataLoaded(cachedThemes);
    }

    private /* synthetic */ void lambda$getThemeDescriptions$17() {
        setNavigationBarColor(getThemedColor(Theme.key_windowBackgroundGray));
    }

    private /* synthetic */ void lambda$onItemSelected$10(Bitmap r3) {
        onPatternLoaded(r3, 34, true);
    }

    private /* synthetic */ void lambda$onItemSelected$11() {
        AndroidUtilities.runOnUIThread(new QrActivity$$ExternalSyntheticLambda17(this, SvgHelper.getBitmap(R.raw.default_pattern, this.backgroundView.getWidth(), this.backgroundView.getHeight(), -16777216)));
    }

    private /* synthetic */ void lambda$onItemSelected$12(int[] r6, ValueAnimator r7) {
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        MotionBackgroundDrawable r0 = this.prevMotionDrawable;
        if (r0 == null) goto L5;
        r0.setBackgroundAlpha(1.0f);
        this.prevMotionDrawable.setPatternAlpha(1.0f - r72);
    L5:
        this.currMotionDrawable.setBackgroundAlpha(r72);
        this.currMotionDrawable.setPatternAlpha(r72);
        if (r6 == null) goto L8;
        int r02 = ColorUtils.blendARGB(this.prevQrColors[0], r6[0], r72);
        int r1 = ColorUtils.blendARGB(this.prevQrColors[1], r6[1], r72);
        int r2 = ColorUtils.blendARGB(this.prevQrColors[2], r6[2], r72);
        int r62 = ColorUtils.blendARGB(this.prevQrColors[3], r6[3], r72);
        this.qrView.setColors(r02, r1, r2, r62);
    L8:
        this.backgroundView.invalidate();
    }

    private /* synthetic */ void lambda$onItemSelected$13() {
        this.resourcesProvider.initColors(this.currentTheme, this.isCurrentThemeDark);
    }

    private /* synthetic */ void lambda$onItemSelected$14(boolean r2, EmojiThemes r3, INavigationLayout.ThemeAnimationSettings r4) {
        ThemeResourcesProvider r0 = this.resourcesProvider;
        if (r2 == false) goto L5;
        r0.initColors(r3, this.isCurrentThemeDark);
    L6:
        r4.afterStartDescriptionsAddedRunnable = new QrActivity$$ExternalSyntheticLambda16(this);
        this.parentLayout.animateThemedValues(r4, null);
        LinearLayout r22 = this.themesViewController.scanButtonWrap;
        if (r22 == null) goto L10;
        r22.setBackground(Theme.AdaptiveRipple.createRect(ColorUtils.setAlphaComponent(Theme.AdaptiveRipple.calcRippleColor(getThemedColor(Theme.key_featuredStickers_addButton)), 25), new float[]{6.0f}));
        return;
    L10:
        return;
    L5:
        r0.initColors(this.currentTheme, this.isCurrentThemeDark);
        goto L6
    }

    private /* synthetic */ void lambda$onItemSelected$9(boolean r5, long r6, Pair r8) {
        long r0 = this.currentTheme.getThemeId(r5 ? 1 : 0);
        if (r8 != null) goto L5;
        return;
    L5:
        if (r0 == 0) goto L16;
        long r2 = ((Long) r8.first).longValue();
        Bitmap r52 = ((WallpaperBitmapHolder) r8.second).bitmap;
        if (r2 != r0) goto L17;
        if (r52 == null) goto L18;
        long r02 = SystemClock.elapsedRealtime() - r6;
        int r62 = this.currMotionDrawable.getIntensity();
        if (r02 <= 150) goto L12;
        boolean r7 = true;
    L13:
        onPatternLoaded(r52, r62, r7);
        return;
    L12:
        r7 = false;
        goto L13
    L18:
        return;
    L17:
        return;
    }

    private /* synthetic */ void lambda$onPatternLoaded$8(ValueAnimator r1) {
        this.currMotionDrawable.setPatternAlpha(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$onRequestPermissionsResultFragment$16(AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        getParentActivity().startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$performShare$15() {
        ThemeListViewController r1 = this.themesViewController;
        if (r1 != null) goto L5;
        return;
    L5:
        r1.shareButton.setClickable(true);
    }

    private WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
        this.insets = AndroidUtilities.getDefaultWindowInsets(r2, false);
        this.fragmentView.requestLayout();
        return WindowInsetsCompat.CONSUMED;
    }

    private void onDataLoaded(List<EmojiThemes> r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        if (r7.isEmpty() == false) goto L6;
        return;
    L6:
        if (this.themesViewController == null) goto L29;
        int r1 = 0;
        r7.set(0, this.homeTheme);
        ArrayList r0 = new ArrayList(r7.size());
        int r2 = 0;
    L10:
        if (r2 >= r7.size()) goto L12;
        EmojiThemes r3 = r7.get(r2);
        r3.loadPreviewColors(this.currentAccount);
        ChatThemeBottomSheet.ChatThemeItem r4 = new ChatThemeBottomSheet.ChatThemeItem(r3);
        boolean r5 = this.isCurrentThemeDark;
        r4.themeIndex = r5 ? 1 : 0;
        r4.icon = getEmojiThemeIcon(r3, r5);
        r0.add(r4);
        r2 = r2 + 1;
        goto L10
    L12:
        this.themesViewController.adapter.setItems(r0);
    L14:
        if (r1 == r0.size()) goto L19;
        if (ThemeKey.equals(((ChatThemeBottomSheet.ChatThemeItem) r0.get(r1)).chatTheme.getThemeKey(), this.currentTheme.getThemeKey()) == true) goto L17;
        r1 = r1 + 1;
        goto L14
    L17:
        this.themesViewController.selectedItem = (ChatThemeBottomSheet.ChatThemeItem) r0.get(r1);
    L20:
        if (r1 == (-1)) goto L22;
        this.themesViewController.setSelectedPosition(r1);
    L22:
        this.themesViewController.onDataLoaded();
        return;
    L19:
        r1 = -1;
        goto L20
    }

    private void onItemSelected(EmojiThemes r12, int r13, boolean r14) {
        this.selectedPosition = r13;
        EmojiThemes r132 = this.currentTheme;
        boolean r0 = this.isCurrentThemeDark;
        this.currentTheme = r12;
        EmojiThemes.ThemeItem r1 = r12.getThemeItem(r0 ? 1 : 0);
        ValueAnimator r2 = this.patternAlphaAnimator;
        if (r2 == null) goto L5;
        float r22 = Math.max(0.5f, 1.0f - ((Float) r2.getAnimatedValue()).floatValue()) * 1.0f;
        this.patternAlphaAnimator.cancel();
    L6:
        MotionBackgroundDrawable r4 = this.currMotionDrawable;
        this.prevMotionDrawable = r4;
        r4.setIndeterminateAnimation(false);
        this.prevMotionDrawable.setAlpha(255);
        MotionBackgroundDrawable r42 = new MotionBackgroundDrawable();
        this.currMotionDrawable = r42;
        r42.setCallback(this.backgroundView);
        this.currMotionDrawable.setColors(r1.patternBgColor, r1.patternBgGradientColor1, r1.patternBgGradientColor2, r1.patternBgGradientColor3);
        this.currMotionDrawable.setParentView(this.backgroundView);
        this.currMotionDrawable.setPatternAlpha(1.0f);
        this.currMotionDrawable.setIndeterminateAnimation(true);
        MotionBackgroundDrawable r15 = this.prevMotionDrawable;
        if (r15 == null) goto L9;
        this.currMotionDrawable.posAnimationProgress = r15.posAnimationProgress;
    L9:
        this.qrView.setPosAnimationProgress(this.currMotionDrawable.posAnimationProgress);
        TLRPC.WallPaper r16 = this.currentTheme.getWallpaper(r0 ? 1 : 0);
        if (r16 == null) goto L12;
        this.currMotionDrawable.setPatternBitmap(r16.settings.intensity);
        long r7 = SystemClock.elapsedRealtime();
        this.currentTheme.loadWallpaper(r0 ? 1 : 0, new QrActivity$$ExternalSyntheticLambda12(this, r0, r7));
    L13:
        MotionBackgroundDrawable r17 = this.currMotionDrawable;
        r17.setPatternColorFilter(r17.getPatternColor());
        ArrayMap<String, int[]> r18 = qrColorsMap;
        StringBuilder r43 = new StringBuilder();
        r43.append(r12.emoji);
        if (r0 == false) goto L16;
        String r122 = "n";
    L17:
        r43.append(r122);
        int[] r123 = r18.get(r43.toString());
        if (r14 == true) goto L20;
        if (r123 == null) goto L25;
        this.qrView.setColors(r123[0], r123[1], r123[2], r123[3]);
        System.arraycopy(r123, 0, this.prevQrColors, 0, 4);
    L25:
        this.prevMotionDrawable = null;
        this.backgroundView.invalidate();
    L27:
        if (this.isCurrentThemeDark == false) goto L29;
        Theme.ThemeInfo r124 = Theme.getCurrentNightTheme();
    L30:
        INavigationLayout.ThemeAnimationSettings r02 = new INavigationLayout.ThemeAnimationSettings(null, r124.currentAccentId, this.isCurrentThemeDark, !r14);
        r02.applyTheme = false;
        r02.onlyTopFragment = true;
        r02.resourcesProvider = getResourceProvider();
        r02.duration = (int) (r22 * 250.0f);
        AndroidUtilities.runOnUIThread(new QrActivity$$ExternalSyntheticLambda15(this, r14, r132, r02));
        return;
    L29:
        r124 = Theme.getCurrentTheme();
        goto L30
    L20:
        if (this.prevQrColors != null) goto L22;
        int[] r8 = new int[4];
        this.prevQrColors = r8;
        System.arraycopy(r123, 0, r8, 0, 4);
    L22:
        this.currMotionDrawable.setAlpha(255);
        this.currMotionDrawable.setBackgroundAlpha(0.0f);
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.patternAlphaAnimator = r03;
        r03.addUpdateListener(new QrActivity$$ExternalSyntheticLambda14(this, r123));
        this.patternAlphaAnimator.addListener(new AnonymousClass4(this, r123));
        this.patternAlphaAnimator.setDuration((int) (r22 * 250.0f));
        this.patternAlphaAnimator.start();
        goto L27
    L16:
        r122 = "d";
        goto L17
    L12:
        Utilities.themeQueue.postRunnable(new QrActivity$$ExternalSyntheticLambda13(this), 35);
        goto L13
    L5:
        r22 = 1.0f;
        goto L6
    }

    private void onPatternLoaded(Bitmap r3, int r4, boolean r5) {
        if (r3 == null) goto L11;
        this.currMotionDrawable.setPatternBitmap(r4, r3, true);
        ValueAnimator r32 = this.patternIntensityAnimator;
        if (r32 == null) goto L6;
        r32.cancel();
    L6:
        if (r5 == false) goto L9;
        ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.patternIntensityAnimator = r33;
        r33.addUpdateListener(new QrActivity$$ExternalSyntheticLambda18(this));
        this.patternIntensityAnimator.setDuration(250);
        this.patternIntensityAnimator.start();
        return;
    L9:
        this.currMotionDrawable.setPatternAlpha(1.0f);
        return;
    }

    public static void openCameraScanActivity(BaseFragment r3) {
        CameraScanActivity.showAsSheet(r3, false, 1, new AnonymousClass5(r3.getCurrentAccount(), r3));
    }

    private boolean phoneIsPublic() {
        ArrayList<TLRPC.PrivacyRule> r0 = ContactsController.getInstance(this.currentAccount).getPrivacyRules(6);
        if (r0 != null) goto L5;
        return false;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= r0.size()) goto L13;
        TLRPC.PrivacyRule r3 = r0.get(r2);
        if ((r3 instanceof TLRPC.TL_privacyValueAllowAll) == true) goto L10;
        if ((r3 instanceof TLRPC.TL_privacyValueDisallowAll) == true) goto L13;
        if ((r3 instanceof TLRPC.TL_privacyValueAllowContacts) == true) goto L16;
        r2 = r2 + 1;
        goto L7
    L16:
        char r02 = 1;
    L18:
        if (r02 != 2) goto L38;
        ArrayList<TLRPC.PrivacyRule> r7 = ContactsController.getInstance(this.currentAccount).getPrivacyRules(7);
        if (r7 != null) goto L22;
    L37:
        return true;
    L22:
        if (r7.size() == 0) goto L37;
        int r22 = 0;
    L26:
        if (r22 >= r7.size()) goto L38;
        TLRPC.PrivacyRule r32 = r7.get(r22);
        if ((r32 instanceof TLRPC.TL_privacyValueAllowAll) == true) goto L29;
        if ((r32 instanceof TLRPC.TL_privacyValueDisallowAll) == true) goto L32;
        if ((r32 instanceof TLRPC.TL_privacyValueAllowContacts) == true) goto L35;
        r22 = r22 + 1;
        goto L26
    L35:
        return false;
    L32:
        return false;
    L29:
        return true;
    L38:
        if (r02 == 0) goto L42;
        if (r02 == 1) goto L42;
        return false;
    L42:
        return true;
    L10:
        r02 = 0;
    L13:
        r02 = 2;
        goto L18
    }

    private void restoreScreenSettings() {
        if (getParentActivity() == null) goto L6;
        getParentActivity().getWindow().getDecorView().setSystemUiVisibility(this.prevSystemUiVisibility);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r29) {
        setHasOwnBackground(true);
        this.isCurrentThemeDark = Theme.getActiveTheme().isDark();
        boolean r4 = false;
        this.actionBar.setAddToContainer(false);
        this.actionBar.setBackground(null);
        this.actionBar.setItemsColor(-1, false);
        AnonymousClass1 r3 = new AnonymousClass1(this, r29);
        AnonymousClass2 r7 = new AnonymousClass2(this, r29);
        this.backgroundView = r7;
        r3.addView(r7);
        if (this.userId == 0) goto L21;
        TLRPC.User r72 = getMessagesController().getUser(Long.valueOf(this.userId));
        if (r72 == null) goto L19;
        String r8 = UserObject.getPublicUsername(r72);
        if (r8 != null) goto L17;
        String r11 = UserObject.getUserName(r72);
        if (phoneIsPublic() == false) goto L16;
        r8 = r72.phone;
        if (r8 != null) goto L13;
    L15:
        boolean r12 = true;
        boolean r13 = false;
    L18:
        AvatarDrawable r14 = new AvatarDrawable(r72);
        ImageLocation r15 = ImageLocation.getForUser(this.currentAccount, r72, 1);
        long r16 = 0;
        ImageLocation r73 = ImageLocation.getForUser(this.currentAccount, r72, 0);
        r4 = r12;
    L20:
        ImageLocation r19 = r73;
        AvatarDrawable r23 = r14;
        ImageLocation r21 = r15;
    L27:
        QrView r74 = new QrView(r29);
        this.qrView = r74;
        r74.setColors(-9324972, -13856649, -6636738, -9915042);
        if (r8 == null) goto L30;
        String r75 = "https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/" + r8;
    L31:
        QrView r9 = this.qrView;
        if (r11 == null) goto L34;
        r8 = r11;
    L34:
        r9.setData(r75, r8, r4, r13);
        this.qrView.setCenterChangedListener(new QrActivity$$ExternalSyntheticLambda1(this));
        r3.addView(this.qrView);
        RLottieImageView r42 = new RLottieImageView(r29);
        this.logoImageView = r42;
        r42.setAutoRepeat(true);
        this.logoImageView.setAnimation(R.raw.plane_logo_plain, 60, 60);
        this.logoImageView.playAnimation();
        r3.addView(this.logoImageView);
        BackupImageView r43 = new BackupImageView(r29);
        this.avatarImageView = r43;
        r43.setRoundRadius(AndroidUtilities.dp(42.0f));
        this.avatarImageView.setSize(AndroidUtilities.dp(84.0f), AndroidUtilities.dp(84.0f));
        r3.addView(this.avatarImageView, LayoutHelper.createFrame(84, 84, 51));
        this.avatarImageView.setImage(r19, "84_84", r21, "50_50", r23, null, null, 0, null);
        ImageView r44 = new ImageView(r29);
        this.closeImageView = r44;
        r44.setContentDescription(LocaleController.getString(R.string.AccDescrGoBack));
        this.closeImageView.setBackground(Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(34.0f), 671088640, 687865855));
        this.closeImageView.setImageResource(R.drawable.ic_ab_back);
        this.closeImageView.setScaleType(ImageView.ScaleType.CENTER);
        this.closeImageView.setOnClickListener(new QrActivity$$ExternalSyntheticLambda2(this));
        r3.addView(this.closeImageView, LayoutHelper.createFrame(34, 34.0f));
        this.emojiThemeIcon = Bitmap.createBitmap(AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f), Bitmap.Config.ARGB_8888);
        Canvas r1 = new Canvas(this.emojiThemeIcon);
        RectF r45 = AndroidUtilities.rectTmp;
        r45.set(0.0f, 0.0f, this.emojiThemeIcon.getWidth(), this.emojiThemeIcon.getHeight());
        Paint r76 = new Paint(1);
        r76.setColor(-1);
        r1.drawRoundRect(r45, AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), r76);
        r76.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        r1.drawBitmap(BitmapFactory.decodeResource(ApplicationLoader.applicationContext.getResources(), R.drawable.msg_qr_mini), (this.emojiThemeIcon.getWidth() - r4.getWidth()) * 0.5f, (this.emojiThemeIcon.getHeight() - r4.getHeight()) * 0.5f, r76);
        r1.setBitmap(null);
        ThemeListViewController r17 = new ThemeListViewController(this, this, getParentActivity().getWindow());
        this.themesViewController = r17;
        this.themeLayout = r17.rootLayout;
        r17.onCreate();
        this.themesViewController.setItemSelectedListener(new QrActivity$$ExternalSyntheticLambda3(this));
        this.themesViewController.titleView.setText(LocaleController.getString(R.string.QrCode));
        this.themesViewController.progressView.setViewType(17);
        this.themesViewController.shareButton.setOnClickListener(new QrActivity$$ExternalSyntheticLambda4(this));
        LinearLayout r18 = this.themesViewController.scanButtonWrap;
        if (r18 == null) goto L37;
        r18.setOnClickListener(new QrActivity$$ExternalSyntheticLambda5(this));
    L37:
        r3.addView(this.themeLayout, LayoutHelper.createFrame(-1, -2, 80));
        this.currMotionDrawable.setIndeterminateAnimation(true);
        this.fragmentView = r3;
        Utilities.themeQueue.postRunnable(new QrActivity$$ExternalSyntheticLambda6(this), 25);
        View r110 = this.fragmentView;
        QrActivity$$ExternalSyntheticLambda7 r2 = new QrActivity$$ExternalSyntheticLambda7(this);
        if (firstOpen == false) goto L40;
        long r92 = 250;
    L41:
        r110.postDelayed(r2, r92);
        this.prevSystemUiVisibility = getParentActivity().getWindow().getDecorView().getSystemUiVisibility();
        applyScreenSettings();
        ViewCompat.setOnApplyWindowInsetsListener(this.fragmentView, new QrActivity$$ExternalSyntheticLambda8(this));
        return this.fragmentView;
    L40:
        r92 = r16;
        goto L41
    L30:
        r75 = null;
        goto L31
    L13:
        if (r8.startsWith("+") == true) goto L15;
        r8 = "+".concat(r8);
        goto L15
    L16:
        r13 = true;
        r12 = false;
        goto L18
    L17:
        r12 = false;
        r13 = false;
        r11 = null;
        goto L18
    L19:
        r16 = 0;
        r13 = false;
        r73 = null;
        r8 = null;
        r11 = null;
        r14 = null;
        r15 = null;
        goto L20
    L21:
        r16 = 0;
        if (this.chatId == 0) goto L26;
        TLRPC.Chat r77 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (r77 == null) goto L26;
        r8 = ChatObject.getPublicUsername(r77);
        r14 = new AvatarDrawable(r77);
        r15 = ImageLocation.getForChat(this.currentAccount, r77, 1);
        r73 = ImageLocation.getForChat(this.currentAccount, r77, 0);
        r13 = false;
        r11 = null;
    L26:
        r13 = false;
        r8 = null;
        r11 = null;
        r19 = null;
        r21 = null;
        r23 = null;
        goto L27
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public EdgeToEdgeSupportMode getEdgeToEdgeSupportMode() {
        return EdgeToEdgeSupportMode.FULL;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Theme.ResourcesProvider getResourceProvider() {
        return this.resourcesProvider;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = super.getThemeDescriptions();
        r1.addAll(this.themesViewController.getThemeDescriptions());
        QrActivity$$ExternalSyntheticLambda9 r9 = new QrActivity$$ExternalSyntheticLambda9(this);
        TextView r4 = this.themesViewController.shareButton;
        int r5 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        int r10 = Theme.key_featuredStickers_addButton;
        r1.add(new ThemeDescription(r4, r5, null, null, null, r9, r10));
        r1.add(new ThemeDescription(this.themesViewController.shareButton, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_featuredStickers_addButtonPressed));
        if (this.themesViewController.scanButton == null) goto L5;
        r1.add(new ThemeDescription(this.themesViewController.scanButton, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, r9, r10));
        r1.add(new ThemeDescription(this.themesViewController.scanButtonIcon, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, r9, r10));
    L5:
        int r2 = r1.size();
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L8;
        ThemeDescription r42 = r1.get(r3);
        r3 = r3 + 1;
        Theme.ResourcesProvider r52 = getResourceProvider();
        r42.resourcesProvider = r52;
        goto L6
    L8:
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.userId = this.arguments.getLong("user_id");
        this.chatId = this.arguments.getLong("chat_id");
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        this.themesViewController.onDestroy();
        this.themesViewController = null;
        this.emojiThemeIcon.recycle();
        this.emojiThemeIcon = null;
        int r0 = 0;
    L3:
        int r1 = this.emojiThemeDarkIcons.size();
        ArrayMap<String, Bitmap> r2 = this.emojiThemeDarkIcons;
        if (r0 >= r1) goto L9;
        Bitmap r12 = r2.valueAt(r0);
        if (r12 == null) goto L8;
        r12.recycle();
    L8:
        r0 = r0 + 1;
        goto L3
    L9:
        r2.clear();
        restoreScreenSettings();
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        restoreScreenSettings();
        super.onPause();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r2, String[] r3, int[] r4) {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if (r2 == 34) goto L8;
        return;
    L8:
        if (r4.length > 0) goto L10;
    L13:
        new AlertDialog.Builder(getParentActivity()).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.QRCodePermissionNoCameraWithHint))).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new QrActivity$$ExternalSyntheticLambda0(this)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).setTopAnimation(R.raw.permission_request_camera, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).show();
        return;
    L10:
        if (r4[0] != 0) goto L13;
        openCameraScanActivity(this);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    @SuppressLint({"SourceLockedOrientationActivity"})
    public void onResume() {
        super.onResume();
        applyScreenSettings();
    }

    public void performShare() {
        Point r0 = AndroidUtilities.displaySize;
        int r02 = Math.min(r0.x, r0.y);
        Point r1 = AndroidUtilities.displaySize;
        int r12 = Math.max(r1.x, r1.y);
        float r3 = r02;
        if (((r12 * 1.0f) / r3) <= 1.92f) goto L5;
        r12 = (int) (r3 * 1.92f);
    L5:
        Bitmap r2 = Bitmap.createBitmap(r02, r12, Bitmap.Config.ARGB_8888);
        Canvas r32 = new Canvas(r2);
        this.themeLayout.setVisibility(8);
        this.closeImageView.setVisibility(8);
        this.logoImageView.setVisibility(8);
        this.logoImageView.getAnimatedDrawable();
        QrView r4 = this.qrView;
        if (r4 == null) goto L8;
        r4.setForShare(true);
    L8:
        this.fragmentView.measure(View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r12, TLObject.FLAG_30));
        this.fragmentView.layout(0, 0, r02, r12);
        this.fragmentView.draw(r32);
        RectF r03 = AndroidUtilities.rectTmp;
        r03.set(this.logoImageView.getLeft(), this.logoImageView.getTop(), this.logoImageView.getRight(), this.logoImageView.getBottom());
        if (this.logoOptimal == null) goto L11;
        r32.drawBitmap(this.logoOptimal, null, r03, new Paint(2));
    L11:
        r32.setBitmap(null);
        this.themeLayout.setVisibility(0);
        this.closeImageView.setVisibility(0);
        this.logoImageView.setVisibility(0);
        ViewGroup r04 = (ViewGroup) this.fragmentView.getParent();
        this.fragmentView.layout(0, 0, r04.getWidth(), r04.getHeight());
        QrView r05 = this.qrView;
        if (r05 == null) goto L14;
        r05.setForShare(false);
    L14:
        Uri r06 = AndroidUtilities.getBitmapShareUri(r2, "qr_tmp.jpg", Bitmap.CompressFormat.JPEG);
        if (r06 != null) goto L23;
    L21:
        AndroidUtilities.runOnUIThread(new QrActivity$$ExternalSyntheticLambda10(this), 500);
        return;
    L23:
        getParentActivity().startActivityForResult(Intent.createChooser(new Intent("android.intent.action.SEND").setType("image/*").putExtra("android.intent.extra.STREAM", r06), LocaleController.getString(R.string.InviteByQRCode)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: ActivityNotFoundException -> L19
    L19:
        e = move-exception;
        e.printStackTrace();
        goto L21
    }
}
