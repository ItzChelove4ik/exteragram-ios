package org.telegram.messenger.video;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.adblock.ui.AdBlockMenuItem$$ExternalSyntheticLambda2;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Text;
import org.telegram.ui.DarkBlueThemeResourcesProvider;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ReportBottomSheet;
import org.telegram.ui.RevenueSharingAdsInfoBottomSheet;

/* loaded from: classes6.dex */
public class VideoAds {
    private static final LruCache<VideoAdsLocation, VideoAdsCache> cached = null;
    private final ArrayList<TLRPC.TL_sponsoredMessage> ads;
    private int between_delay;
    private Bulletin bulletin;
    private BulletinFactory bulletinFactory;
    private long bulletinShowTime;
    private final VideoAdsCache cache;
    private final int currentAccount;
    private long currentBulletinPassedTime;
    private ItemOptions currentMenu;
    private float currentMenuTranslationY;
    private final long dialogId;
    private boolean first;
    private boolean lastPopupShown;
    private long lastTime;
    private boolean loaded;
    private boolean loading;
    private final int msg_id;
    private Runnable onPopupCallback;
    private PremiumFeatureBottomSheet premiumSheet;
    private int requestId;
    private final Runnable showRunnable;
    private int start_delay;
    public boolean videoWasPlaying;
    private boolean waitingPaused;
    private long waitingTimeSince;

    /* renamed from: org.telegram.messenger.video.VideoAds$1, reason: invalid class name */
    public class AnonymousClass1 extends AdLayout {
        final /* synthetic */ VideoAds this$0;

        public AnonymousClass1(VideoAds r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public void updatePosition() {
            super.updatePosition();
            if (VideoAds.m5235$$Nest$fgetcurrentMenu(this.this$0) == null) goto L6;
            VideoAds.m5235$$Nest$fgetcurrentMenu(this.this$0).setTranslationY(getTranslationY() - VideoAds.m5236$$Nest$fgetcurrentMenuTranslationY(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.messenger.video.VideoAds$2, reason: invalid class name */
    public class AnonymousClass2 extends BaseFragment {
        final /* synthetic */ VideoAds this$0;

        public AnonymousClass2(VideoAds r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Context getContext() {
            return AndroidUtilities.findActivity(LaunchActivity.instance);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public int getCurrentAccount() {
            return VideoAds.m5234$$Nest$fgetcurrentAccount(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            Activity r0 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
            if (r0 == null) goto L5;
            return r0;
        L5:
            return LaunchActivity.instance;
        }
    }

    public static class AdLayout extends Bulletin.ButtonLayout {
        public final ImageView buttonView;
        public final BackupImageView imageView;
        private final LinearLayout linearLayout;
        public final LinkSpanDrawable.LinksTextView subtitleTextView;
        public final SimpleTextView titleTextView;

        public AdLayout(Context r11, Theme.ResourcesProvider r12) {
            super(r11, r12);
            setBackground(getThemedColor(Theme.key_undo_background));
            BackupImageView r122 = new BackupImageView(r11);
            this.imageView = r122;
            r122.setRoundRadius(AndroidUtilities.dp(48.0f));
            addView(r122, LayoutHelper.createFrameRelatively(36.0f, 36.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 9.0f, 0.0f, 0.0f, 0.0f));
            int r123 = getThemedColor(Theme.key_undo_infoColor);
            int r0 = getThemedColor(Theme.key_undo_cancelColor);
            LinearLayout r1 = new LinearLayout(r11);
            this.linearLayout = r1;
            r1.setOrientation(1);
            addView(r1, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 52.0f, 8.0f, 54.0f, 8.0f));
            SimpleTextView r3 = new SimpleTextView(r11);
            this.titleTextView = r3;
            r3.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            r3.setTextColor(r123);
            r3.setTextSize(14);
            r3.setTypeface(AndroidUtilities.bold());
            r1.addView(r3);
            LinkSpanDrawable.LinksTextView r32 = new LinkSpanDrawable.LinksTextView(r11);
            this.subtitleTextView = r32;
            r32.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            r32.setTextColor(r123);
            r32.setLinkTextColor(r0);
            r32.setTypeface(Typeface.SANS_SERIF);
            r32.setTextSize(1, 13.0f);
            r1.addView(r32);
            ImageView r124 = new ImageView(r11);
            this.buttonView = r124;
            r124.setScaleType(ImageView.ScaleType.CENTER);
            r124.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(getThemedColor(Theme.key_featuredStickers_addButton), 0.15f), 7));
            addView(r124, LayoutHelper.createFrameRelatively(32.0f, 32.0f, 8388629, 0.0f, 0.0f, 11.0f, 0.0f));
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public CharSequence getAccessibilityText() {
            return this.titleTextView.getText() + ".\n" + this.subtitleTextView.getText();
        }

        public void hideImage() {
            this.imageView.setVisibility(8);
            this.linearLayout.setLayoutParams(LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 10.0f, 8.0f, 54.0f, 8.0f));
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public void onShow() {
            super.onShow();
        }
    }

    public static class AdOptionsDrawable extends Drawable {
        private float alpha;
        public final Paint backgroundPaint;
        public final int color;
        public final Drawable icon;
        public final Text text;

        public AdOptionsDrawable(Context r5, int r6) {
            this.backgroundPaint = new Paint(1);
            this.text = new Text(LocaleController.getString(R.string.SponsoredMessageAd), 11.0f, AndroidUtilities.bold());
            this.alpha = 1.0f;
            this.color = r6;
            Drawable r52 = r5.getResources().getDrawable(R.drawable.ic_ab_other).mutate();
            this.icon = r52;
            r52.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.SRC_IN));
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r11) {
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(getBounds());
            r0.left += AndroidUtilities.dp(4.0f);
            this.backgroundPaint.setColor(Theme.multAlpha(this.color, this.alpha * 0.2f));
            r11.drawRoundRect(r0, r0.height(), r0.height(), this.backgroundPaint);
            this.text.draw(r11, r0.left + AndroidUtilities.dp(5.0f), r0.centerY(), this.color, this.alpha);
            this.icon.setBounds(getBounds().right - AndroidUtilities.dp(12.99f), getBounds().centerY() - AndroidUtilities.dp(5.665f), getBounds().right - AndroidUtilities.dp(1.66f), getBounds().centerY() + AndroidUtilities.dp(5.665f));
            this.icon.setAlpha((int) (this.alpha * 255.0f));
            this.icon.draw(r11);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(16.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return (int) ((AndroidUtilities.dp(4.0f) + this.text.getWidth()) + AndroidUtilities.dp(18.0f));
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            this.alpha = r2 / 255.0f;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static class CloseDrawable extends Drawable {
        private int alpha;
        private final long max_display_duration;
        private final long min_display_duration;
        private long minusTime;
        private final Paint paint;
        private final View parentView;
        private boolean paused;
        private long pausedTime;
        private final AnimatedFloat showCrossAnimated;
        private final AnimatedFloat showTimerAnimated;
        private final long startTime;
        private final AnimatedTextView.AnimatedTextDrawable timer;
        private final AnimatedFloat timerScaleAnimated;

        public CloseDrawable(View r10, int r11, int r12, long r13) {
            AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
            this.timer = r0;
            Paint r3 = new Paint(1);
            this.paint = r3;
            this.paused = false;
            this.alpha = 255;
            this.parentView = r10;
            this.startTime = System.currentTimeMillis() - r13;
            this.min_display_duration = r11 * 1000;
            this.max_display_duration = r12 * 1000;
            r3.setStyle(Paint.Style.STROKE);
            r3.setStrokeCap(Paint.Cap.ROUND);
            r3.setStrokeJoin(Paint.Join.ROUND);
            r3.setColor(-1);
            r0.setCallback(r10);
            r0.setGravity(17);
            r0.setTextSize(AndroidUtilities.dp(12.0f));
            r0.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
            r0.setOverrideFullWidth(AndroidUtilities.displaySize.x);
            r0.setTextColor(-1);
            CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.showCrossAnimated = new AnimatedFloat(r10, 0, 420, r8);
            this.showTimerAnimated = new AnimatedFloat(r10, 0, 420, r8);
            this.timerScaleAnimated = new AnimatedFloat(r10, 0, 420, r8);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r15) {
            float r7 = getBounds().centerX();
            float r8 = getBounds().centerY();
            if (this.paused == false) goto L5;
            long r2 = this.pausedTime;
        L6:
            long r22 = (r2 - this.minusTime) - this.startTime;
            long r4 = Math.max(0, this.min_display_duration - r22);
            long r9 = this.min_display_duration;
            float r11 = r4 / r9;
            boolean r92 = false;
            if (r22 >= r9) goto L9;
            boolean r23 = true;
        L10:
            float r12 = this.showTimerAnimated.set(r23);
            String r24 = _UrlKt.FRAGMENT_ENCODE_SET + ((int) Math.ceil(r4 / 1000.0d));
            AnimatedFloat r3 = this.timerScaleAnimated;
            if (r24.length() < 3) goto L14;
            float r42 = 0.825f;
        L17:
            float r32 = r3.set(r42);
            r15.save();
            r15.scale(r32, r32, r7, r8);
            this.timer.setText(r24);
            this.timer.setBounds(r7 - 1.0f, r8 - 1.0f, r7 + 1.0f, r8 + 1.0f);
            this.timer.setAlpha((int) (this.alpha * r12));
            this.timer.draw(r15);
            r15.restore();
            this.paint.setAlpha((int) (this.alpha * r12));
            this.paint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            RectF r25 = AndroidUtilities.rectTmp;
            r25.set(r7 - AndroidUtilities.dp(9.0f), r8 - AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f) + r7, AndroidUtilities.dp(9.0f) + r8);
            r15.drawArc(r25, -90.0f, r11 * (-360.0f), false, this.paint);
            AnimatedFloat r1 = this.showCrossAnimated;
            if (((1.0f - r11) * 360.0f) <= 75.0f) goto L20;
            r92 = true;
        L20:
            float r13 = r1.set(r92);
            float r33 = AndroidUtilities.lerp(r7, AndroidUtilities.dp(8.0f) + r7, r12);
            float r26 = AndroidUtilities.lerp(r8, r8 - AndroidUtilities.dp(8.0f), r12);
            float r43 = AndroidUtilities.lerp(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(3.0f), r12) * AndroidUtilities.lerp(0.35f, 1.0f, r13);
            this.paint.setAlpha((int) (this.alpha * r13));
            float r27 = r33 - r43;
            float r5 = r26 - r43;
            float r34 = r33 + r43;
            float r14 = r26 + r43;
            r15.drawLine(r27, r5, r34, r14, this.paint);
            r15.drawLine(r27, r14, r34, r5, this.paint);
            if (r12 <= 0.0f) goto L24;
            this.parentView.invalidate();
            return;
        L24:
            return;
        L14:
            if (r24.length() < 2) goto L16;
            r42 = 0.875f;
            goto L17
        L16:
            r42 = 1.0f;
            goto L17
        L9:
            r23 = false;
            goto L10
        L5:
            r2 = System.currentTimeMillis();
            goto L6
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        public boolean isCrossAvailable() {
            if (this.paused == false) goto L5;
            long r0 = this.pausedTime;
        L7:
            if ((r0 - this.startTime) <= this.min_display_duration) goto L10;
            return true;
        L10:
            return false;
        L5:
            r0 = System.currentTimeMillis() - this.minusTime;
            goto L7
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.alpha = r1;
        }

        public void setColor(int r2) {
            this.timer.setTextColor(r2);
            this.paint.setColor(r2);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r2) {
            this.timer.setColorFilter(r2);
            this.paint.setColorFilter(r2);
        }

        public void setPaused(boolean r5) {
            if (this.paused != r5) goto L5;
            return;
        L5:
            this.paused = r5;
            if (r5 == false) goto L9;
            this.pausedTime = System.currentTimeMillis();
            return;
        L9:
            this.minusTime += System.currentTimeMillis() - this.pausedTime;
        }
    }

    public static class VideoAdsCache {
        final ArrayList<TLRPC.TL_sponsoredMessage> ads;
        int betweenDelay;
        long loadTime;
        boolean loaded;
        final int msgId;
        int startDelay;

        public VideoAdsCache(int r2) {
            this.ads = new ArrayList();
            this.msgId = r2;
        }
    }

    public static class VideoAdsLocation {
        int currentAccount;
        long dialogId;

        public VideoAdsLocation(int r1, long r2) {
            this.currentAccount = r1;
            this.dialogId = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L15:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L15;
            VideoAdsLocation r52 = (VideoAdsLocation) r5;
            if (this.currentAccount != r52.currentAccount) goto L15;
            if (this.dialogId != r52.dialogId) goto L15;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Integer.valueOf(this.currentAccount), Long.valueOf(this.dialogId)});
        }
    }

    /* renamed from: $r8$lambda$-phx4CLieaAN1LpFJkJMLMXGT54, reason: not valid java name */
    public static /* synthetic */ void m5229$r8$lambda$phx4CLieaAN1LpFJkJMLMXGT54(VideoAds r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$load$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$1QGZewha_uIKm1JzxxXGiFZKI14(VideoAds r0, ItemOptions r1, TLRPC.TL_sponsoredMessage r2, Context r3, View r4) {
        r0.lambda$show$7(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$1_rZHwd9JJLiYDmhmjxpEq5ttBY(VideoAds r0, Bulletin r1, TLRPC.TL_sponsoredMessage r2) {
        r0.lambda$show$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$25c78JdMlTpwTPvx8GTica5Nn6k(VideoAds r0, PremiumFeatureBottomSheet r1) {
        r0.lambda$showPremium$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$6MLpqnPy3Jq1lo3FzjlXjRTXe_Q(TLRPC.TL_sponsoredMessage r0, View r1) {
        AndroidUtilities.addToClipboard(r0.additional_info);
    }

    public static /* synthetic */ void $r8$lambda$8ZQp6vjeH7g_nX12rycBzDMXj0M(ItemOptions r0, ItemOptions r1) {
        r0.openSwipeback(r1);
    }

    public static /* synthetic */ void $r8$lambda$ITh2oDGsY1PYWxGlnYgZllKWVe0(VideoAds r0, CloseDrawable r1, View r2) {
        r0.lambda$show$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$L8ZuivI4X7emHVJbhFg6JOaUw4M(VideoAds r0, TLObject r1) {
        r0.lambda$load$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$NMPaXQJA30ZcuySw7K2e7czhE1w(Context r2, Theme.ResourcesProvider r3) {
        RevenueSharingAdsInfoBottomSheet.showAlert(r2, null, false, r3);
    }

    /* renamed from: $r8$lambda$OfU8zEz-uTSWA2OYCyFM_rDNmcU, reason: not valid java name */
    public static /* synthetic */ void m5230$r8$lambda$OfU8zEzuTSWA2OYCyFM_rDNmcU(VideoAds r0, Bulletin r1, boolean[] r2) {
        r0.lambda$show$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$W_0Z3VTsKmFD0WI8sMVirDsGq5k(VideoAds r0) {
        r0.showPremium();
    }

    /* renamed from: $r8$lambda$ZO28X4H_z-4bEJyi8JTR0layIOY, reason: not valid java name */
    public static /* synthetic */ void m5231$r8$lambda$ZO28X4H_z4bEJyi8JTR0layIOY(VideoAds r0, ItemOptions r1) {
        r0.lambda$show$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$dqW88WhIPLEgpQtRW4TggbroeHQ(TLRPC.TL_sponsoredMessage r0, View r1) {
        AndroidUtilities.addToClipboard(r0.sponsor_info);
    }

    public static /* synthetic */ void $r8$lambda$hkuoM5syic_Vi8rlHzMT_zwzASE(VideoAds r0) {
        r0.show();
    }

    public static /* synthetic */ void $r8$lambda$iJNzr4cxQtKwCkMVBcwYn2ocNhw(VideoAds r0, Bulletin r1, boolean[] r2, CloseDrawable r3, long[] r4, Runnable r5, long[] r6, long r7, TLRPC.TL_sponsoredMessage r9, Boolean r10) {
        r0.lambda$show$4(r1, r2, r3, r4, r5, r6, r7, r9, r10);
    }

    public static /* synthetic */ boolean $r8$lambda$iVe8i2C8j2Ec5WZfQBkwOCfuyHY(TLRPC.TL_sponsoredMessage r0, View r1) {
        AndroidUtilities.addToClipboard(r0.url);
        return true;
    }

    /* renamed from: $r8$lambda$kgwf-kmGMY1P8mPtgHmFW64sxeA, reason: not valid java name */
    public static /* synthetic */ void m5232$r8$lambda$kgwfkmGMY1P8mPtgHmFW64sxeA(VideoAds r0, Context r1, TLRPC.TL_sponsoredMessage r2, ItemOptions r3) {
        r0.lambda$show$14(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$oDjoyHWVc3yd93UWolDwsC3_UNw(VideoAds r0, Utilities.Callback r1) {
        r0.lambda$show$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$rcQ09HmohjEMg8hao_x06iAdxdI(VideoAds r0, ItemOptions r1) {
        r0.lambda$show$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$xKpBcmBax0uJaFxXVyeT5bS3RkA(VideoAds r0, TLRPC.TL_sponsoredMessage r1, View r2) {
        r0.lambda$show$18(r1, r2);
    }

    /* renamed from: $r8$lambda$yEh0OdQ5_bZK7-ItmxhnVBWxPds, reason: not valid java name */
    public static /* synthetic */ void m5233$r8$lambda$yEh0OdQ5_bZK7ItmxhnVBWxPds(VideoAds r0, Bulletin r1, TLRPC.TL_sponsoredMessage r2, Context r3, Theme.ResourcesProvider r4, AdLayout r5, Utilities.Callback r6, View r7) {
        r0.lambda$show$17(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$yR7ErAhMRkQqulVVUfdULZTox5I(ItemOptions r0, View r1) {
        r0.closeSwipeback();
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5234$$Nest$fgetcurrentAccount(VideoAds r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ItemOptions m5235$$Nest$fgetcurrentMenu(VideoAds r0) {
        return r0.currentMenu;
    }

    /* renamed from: -$$Nest$fgetcurrentMenuTranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5236$$Nest$fgetcurrentMenuTranslationY(VideoAds r0) {
        return r0.currentMenuTranslationY;
    }

    static {
        cached = new LruCache(3);
    }

    private VideoAds(int r3, long r4, int r6, BulletinFactory r7, VideoAdsCache r8) {
        this.ads = new ArrayList();
        this.lastTime = 0;
        this.first = true;
        this.showRunnable = new VideoAds$$ExternalSyntheticLambda13(this);
        this.currentAccount = r3;
        this.dialogId = r4;
        this.msg_id = r6;
        this.cache = r8;
        this.lastTime = System.currentTimeMillis();
        init(r7);
    }

    private void checkPopupShownCallback() {
        if (this.lastPopupShown == isPopupShown()) goto L8;
        this.lastPopupShown = isPopupShown();
        Runnable r2 = this.onPopupCallback;
        if (r2 == null) goto L9;
        r2.run();
        return;
    L9:
        return;
    }

    public static void dropCache() {
        cached.evictAll();
    }

    private void init(BulletinFactory r3) {
        this.bulletinFactory = r3;
        this.lastTime = System.currentTimeMillis();
        this.first = true;
        VideoAdsCache r0 = this.cache;
        if (r0.loaded == false) goto L6;
        this.start_delay = r0.startDelay;
        this.between_delay = r0.betweenDelay;
        this.ads.addAll(r0.ads);
        this.loaded = true;
        schedule();
        return;
    L6:
        load();
    }

    private /* synthetic */ void lambda$load$0(TLObject r5) {
        if (this.loading == true) goto L6;
        return;
    L6:
        if ((r5 instanceof TLRPC.TL_messages_sponsoredMessages) == false) goto L8;
        TLRPC.TL_messages_sponsoredMessages r52 = (TLRPC.TL_messages_sponsoredMessages) r5;
        MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
        this.ads.addAll(r52.messages);
        this.start_delay = r52.start_delay;
        this.between_delay = r52.between_delay;
        this.cache.ads.clear();
        this.cache.ads.addAll(r52.messages);
        VideoAdsCache r0 = this.cache;
        r0.startDelay = r52.start_delay;
        r0.betweenDelay = r52.between_delay;
    L8:
        VideoAdsCache r53 = this.cache;
        r53.loadTime = System.currentTimeMillis();
        this.cache.loaded = true;
        this.loaded = true;
        this.loading = false;
        schedule();
    }

    private /* synthetic */ void lambda$load$1(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new VideoAds$$ExternalSyntheticLambda20(this, r1));
    }

    private /* synthetic */ void lambda$show$12(ItemOptions r3) {
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L9;
        r3.dismiss();
        Bulletin r32 = this.bulletin;
        if (r32 == null) goto L7;
        r32.setCanHide(true);
        this.bulletin.hide();
    L7:
        this.bulletinFactory.createAdReportedBulletin(LocaleController.getString(R.string.AdHidden)).show();
        MessagesController.getInstance(this.currentAccount).disableAds(true);
        return;
    L9:
        showPremium();
    }

    private /* synthetic */ void lambda$show$14(Context r10, TLRPC.TL_sponsoredMessage r11, ItemOptions r12) {
        int r0 = this.currentAccount;
        long r2 = this.dialogId;
        BulletinFactory r5 = this.bulletinFactory;
        DarkBlueThemeResourcesProvider r6 = new DarkBlueThemeResourcesProvider();
        VideoAds$$ExternalSyntheticLambda12 r7 = new VideoAds$$ExternalSyntheticLambda12(this);
        Objects.requireNonNull(r12);
        ReportBottomSheet.openSponsored(r0, r10, r2, r11, r5, r6, r7, new AdBlockMenuItem$$ExternalSyntheticLambda2(r12));
    }

    private /* synthetic */ void lambda$show$15(ItemOptions r3) {
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L9;
        r3.dismiss();
        Bulletin r32 = this.bulletin;
        if (r32 == null) goto L7;
        r32.setCanHide(true);
        this.bulletin.hide();
    L7:
        this.bulletinFactory.createAdReportedBulletin(LocaleController.getString(R.string.AdHidden)).show();
        MessagesController.getInstance(this.currentAccount).disableAds(true);
        return;
    L9:
        showPremium();
    }

    private /* synthetic */ void lambda$show$16(Utilities.Callback r2) {
        r2.run(Boolean.FALSE);
        this.currentMenu = null;
        checkPopupShownCallback();
    }

    private /* synthetic */ void lambda$show$17(Bulletin r19, TLRPC.TL_sponsoredMessage r20, Context r21, Theme.ResourcesProvider r22, AdLayout r23, Utilities.Callback r24, View r25) {
        Bulletin r4 = this.bulletin;
        if (r4 != null) goto L5;
        return;
    L5:
        if (r4 == r19) goto L74;
        return;
    L74:
        ViewGroup r42 = (ViewGroup) r4.getLayout().getParent().getParent();     // Catch: Exception -> L10
    L11:
        if (r42 == null) goto L80;
        DarkBlueThemeResourcesProvider r6 = new DarkBlueThemeResourcesProvider();
        int r9 = 0;
        ItemOptions r43 = ItemOptions.makeOptions(r42, r6, this.bulletin.getLayout(), true, false);
        r43.setSwipebackGravity(true, true);
        r43.setScaleOut(true);
        r43.setDimAlpha(0);
        r43.setDrawScrim(false);
        r43.setDismissWithButtons(false);
        if (r20.sponsor_info == null) goto L16;
    L21:
        ItemOptions r7 = r43.makeSwipeback();
        ActionBarMenuSubItem r10 = new ActionBarMenuSubItem(r21, true, false, r6);
        r10.setItemHeight(44);
        r10.setTextAndIcon(LocaleController.getString(R.string.Back), R.drawable.msg_arrow_back);
        AnimatedEmojiSpan.TextViewEmojis r11 = r10.getTextView();
        if (LocaleController.isRTL == false) goto L24;
        int r12 = 0;
    L26:
        if (LocaleController.isRTL == false) goto L28;
        int r13 = AndroidUtilities.dp(40.0f);
    L29:
        r11.setPadding(r12, 0, r13, 0);
        r10.setOnClickListener(new VideoAds$$ExternalSyntheticLambda1(r43));
        r7.addView(r10, LayoutHelper.createLinear(-1, -2));
        r7.addView(new ActionBarPopupWindow.GapView(r21, r6), LayoutHelper.createLinear(-1, 8));
        ArrayList r102 = new ArrayList();
        String r132 = r20.url;
        if (r132 == null) goto L39;
        if (TextUtils.equals(AndroidUtilities.getHostAuthority(r132), MessagesController.getInstance(this.currentAccount).linkPrefix) == true) goto L39;
        TextView r122 = new TextView(r21);
        r122.setTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r6));
        r122.setTextSize(1, 14.0f);
        r122.setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(10.0f));
        r122.setMaxWidth(AndroidUtilities.dp(300.0f));
        Uri r8 = Uri.parse(r20.url);
        r122.setText(Browser.replaceHostname(r8, Browser.IDN_toUnicode(r8.getHost()), null));
        int r5 = Theme.getColor(Theme.key_dialogButtonSelector, r6);
        if (r20.additional_info != null) goto L36;
        int r82 = 6;
    L37:
        r122.setBackground(Theme.createRadSelectorDrawable(r5, 0, r82));
        r122.setOnClickListener(new VideoAds$$ExternalSyntheticLambda3(this, r43, r20, r21));
        r122.setOnLongClickListener(new VideoAds$$ExternalSyntheticLambda4(r20));
        r102.add(r122);
        goto L39
    L36:
        r82 = 0;
    L39:
        if (r20.sponsor_info == null) goto L46;
        TextView r52 = new TextView(r21);
        r52.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r6));
        r52.setTextSize(1, 14.0f);
        r52.setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(10.0f));
        r52.setMaxWidth(AndroidUtilities.dp(300.0f));
        r52.setText(r20.sponsor_info);
        int r83 = Theme.getColor(Theme.key_dialogButtonSelector, r6);
        if (r20.additional_info != null) goto L43;
        int r112 = 6;
    L44:
        r52.setBackground(Theme.createRadSelectorDrawable(r83, 0, r112));
        r52.setOnClickListener(new VideoAds$$ExternalSyntheticLambda5(r20));
        r102.add(r52);
        goto L46
    L43:
        r112 = 0;
    L46:
        if (r20.additional_info == null) goto L49;
        TextView r53 = new TextView(r21);
        r53.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r6));
        r53.setTextSize(1, 14.0f);
        r53.setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(10.0f));
        r53.setMaxWidth(AndroidUtilities.dp(300.0f));
        r53.setText(r20.additional_info);
        r53.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_dialogButtonSelector, r22), 0, 6));
        r53.setOnClickListener(new VideoAds$$ExternalSyntheticLambda6(r20));
        r102.add(r53);
    L49:
        if (r9 >= r102.size()) goto L56;
        View r54 = (View) r102.get(r9);
        if (r9 <= 0) goto L54;
        FrameLayout r84 = new FrameLayout(r21);
        r84.setBackgroundColor(Theme.getColor(Theme.key_divider, r6));
        int r113 = -1;
        LinearLayout.LayoutParams r133 = LayoutHelper.createLinear(-1, 1);
        r133.height = 1;
        r7.addView(r84, r133);
    L55:
        r7.addView(r54, LayoutHelper.createLinear(r113, -2));
        r9 = r9 + 1;
        goto L49
    L54:
        r113 = -1;
        goto L55
    L56:
        r43.add(R.drawable.msg_channel, LocaleController.getString(R.string.SponsoredMessageSponsorReportable), new VideoAds$$ExternalSyntheticLambda7(r43, r7));
    L58:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L65;
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == true) goto L65;
        if (r20.can_report == true) goto L65;
        r43.add(R.drawable.msg_block2, LocaleController.getString(R.string.HideAd), new VideoAds$$ExternalSyntheticLambda8(this, r43));
    L65:
        if (r20.can_report == false) goto L70;
        r43.add(R.drawable.msg_info, LocaleController.getString(R.string.AboutRevenueSharingAds), new VideoAds$$ExternalSyntheticLambda9(r21, r6));
        r43.add(R.drawable.msg_block2, LocaleController.getString(R.string.ReportAd), new VideoAds$$ExternalSyntheticLambda10(this, r21, r20, r43));
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == true) goto L70;
        r43.addGap();
        r43.add(R.drawable.msg_cancel, LocaleController.getString(R.string.RemoveAds), new VideoAds$$ExternalSyntheticLambda11(this, r43));
    L70:
        if (r43.getItemsCount() <= 0) goto L81;
        this.currentMenu = r43;
        this.currentMenuTranslationY = r23.getTranslationY();
        r24.run(Boolean.TRUE);
        r43.setOnDismiss(new VideoAds$$ExternalSyntheticLambda2(this, r24));
        r43.show();
        checkPopupShownCallback();
        return;
    L81:
        return;
    L28:
        r13 = 0;
        goto L29
    L24:
        r12 = AndroidUtilities.dp(40.0f);
        goto L26
    L16:
        if (r20.additional_info != null) goto L21;
        String r72 = r20.url;
        if (r72 == null) goto L58;
        if (r72.startsWith("https://" + MessagesController.getInstance(this.currentAccount).linkPrefix) == true) goto L58;
    L80:
        return;
    L10:
        r42 = null;
        goto L11
    }

    private /* synthetic */ void lambda$show$18(TLRPC.TL_sponsoredMessage r11, View r12) {
        logSponsoredClicked(r11);
        Browser.openUrl(r12.getContext(), Uri.parse(r11.url), true, false, false, null, null, false, MessagesController.getInstance(UserConfig.selectedAccount).sponsoredLinksInappAllow, false);
    }

    private /* synthetic */ void lambda$show$2(CloseDrawable r1, View r2) {
        if (r1.isCrossAvailable() == false) goto L9;
        Bulletin r0 = this.bulletin;
        if (r0 == null) goto L17;
        r0.hide();
        return;
    L17:
        return;
    L9:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L15;
        Bulletin r12 = this.bulletin;
        if (r12 == null) goto L13;
        r12.hide();
        this.bulletin = null;
    L13:
        MessagesController.getInstance(this.currentAccount).disableAds(true);
        this.bulletinFactory.createAdReportedBulletin(LocaleController.getString(R.string.AdHidden)).show();
        return;
    L15:
        showPremium();
    }

    private /* synthetic */ void lambda$show$3(Bulletin r2, TLRPC.TL_sponsoredMessage r3) {
        Bulletin r0 = this.bulletin;
        if (r0 == null) goto L7;
        if (r0 != r2) goto L8;
        r0.setDuration((r3.max_display_duration - r3.min_display_duration) * 1000);
        this.bulletin.setCanHide(true);
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$show$4(Bulletin r4, boolean[] r5, CloseDrawable r6, long[] r7, Runnable r8, long[] r9, long r10, TLRPC.TL_sponsoredMessage r12, Boolean r13) {
        Bulletin r0 = this.bulletin;
        if (r0 == null) goto L24;
        if (r0 == r4) goto L6;
        return;
    L6:
        if (r13.booleanValue() == r5[0]) goto L27;
        boolean r42 = r13.booleanValue();
        r5[0] = r42;
        r6.setPaused(r42);
        if (r5[0] == false) goto L12;
        this.bulletin.setCanHide(false);
        r7[0] = System.currentTimeMillis();
        AndroidUtilities.cancelRunOnUIThread(r8);
        return;
    L12:
        AndroidUtilities.cancelRunOnUIThread(r8);
        r9[0] = r9[0] + (System.currentTimeMillis() - r7[0]);
        long r43 = (System.currentTimeMillis() - r10) - r9[0];
        long r62 = (r12.min_display_duration * 1000) - r43;
        long r11 = (r12.max_display_duration * 1000) - r43;
        if (r11 > 0) goto L19;
        Bulletin r44 = this.bulletin;
        if (r44 == null) goto L26;
        r44.hide();
        this.bulletin = null;
        return;
    L26:
        return;
    L19:
        if (r62 > 0) goto L22;
        this.bulletin.setDuration((int) r11);
        this.bulletin.setCanHide(true);
        return;
    L22:
        AndroidUtilities.runOnUIThread(r8, r62);
        return;
    L27:
        return;
    }

    private /* synthetic */ void lambda$show$5(Bulletin r3, boolean[] r4) {
        Bulletin r0 = this.bulletin;
        if (r0 == null) goto L19;
        if (r0 == r3) goto L6;
        return;
    L6:
        if (r4[0] == true) goto L21;
        r4[0] = true;
        ItemOptions r42 = this.currentMenu;
        if (r42 == null) goto L11;
        r42.dismiss();
        this.currentMenu = null;
    L11:
        this.bulletin = null;
        this.currentBulletinPassedTime = 0;
        this.lastTime = System.currentTimeMillis();
        if (this.waitingPaused == false) goto L15;
        this.waitingTimeSince = System.currentTimeMillis();
    L15:
        if (this.ads.isEmpty() == true) goto L17;
        this.ads.remove(0);
    L17:
        this.first = false;
        schedule();
        return;
    L21:
        return;
    }

    private /* synthetic */ void lambda$show$7(ItemOptions r11, TLRPC.TL_sponsoredMessage r12, Context r13, View r14) {
        r11.dismiss();
        logSponsoredClicked(r12);
        Browser.openUrl(r13, Uri.parse(r12.url), true, false, false, null, null, false, MessagesController.getInstance(this.currentAccount).sponsoredLinksInappAllow, false);
    }

    private /* synthetic */ void lambda$showPremium$19(PremiumFeatureBottomSheet r2) {
        if (r2 != this.premiumSheet) goto L6;
        this.premiumSheet = null;
        checkPopupShownCallback();
        return;
    }

    private void load() {
        if (this.loading == false) goto L5;
        return;
    L5:
        if (this.loaded == false) goto L8;
        return;
    L8:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L10;
    L12:
        this.loading = true;
        TLRPC.TL_messages_getSponsoredMessages r1 = new TLRPC.TL_messages_getSponsoredMessages();
        r1.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r1.flags = 1 | r1.flags;
        r1.msg_id = this.msg_id;
        this.requestId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new VideoAds$$ExternalSyntheticLambda21(this));
        return;
    L10:
        if (MessagesController.getInstance(this.currentAccount).isSponsoredDisabled() == false) goto L12;
    }

    public static VideoAds make(int r10, long r11, int r13, BulletinFactory r14) {
        VideoAdsLocation r0 = new VideoAdsLocation(r10, r11);
        LruCache<VideoAdsLocation, VideoAdsCache> r1 = cached;
        VideoAdsCache r2 = r1.get(r0);
        if (r2 != null) goto L5;
    L10:
        r2 = new VideoAdsCache(r13);
        r1.put(r0, r2);
    L12:
        return new VideoAds(r10, r11, r13, r14, r2);
    L5:
        if (r2.msgId != r13) goto L10;
        if ((System.currentTimeMillis() - r2.loadTime) <= 180000) goto L12;
        goto L10
    }

    private void schedule() {
        AndroidUtilities.cancelRunOnUIThread(this.showRunnable);
        if (this.loaded == true) goto L5;
        return;
    L5:
        if (this.ads.isEmpty() == false) goto L8;
        return;
    L8:
        if (this.first == false) goto L10;
        int r0 = this.start_delay;
    L11:
        AndroidUtilities.runOnUIThread(this.showRunnable, Math.max(0, (r0 * 1000) - (System.currentTimeMillis() - this.lastTime)));
        return;
    L10:
        r0 = this.between_delay;
        goto L11
    }

    private void show() {
        if (this.ads.isEmpty() == false) goto L5;
        return;
    L5:
        TLRPC.TL_sponsoredMessage r3 = this.ads.get(0);
        if (RemoteUtils.getBooleanConfigValue("hide_ads", false).booleanValue() == false) goto L9;
        logSponsoredShown(r3);
        this.ads.remove(0);
        this.first = false;
        this.lastTime = System.currentTimeMillis();
        schedule();
        return;
    L9:
        long r8 = System.currentTimeMillis() - this.currentBulletinPassedTime;
        this.bulletinShowTime = r8;
        Bulletin r0 = this.bulletin;
        if (r0 == null) goto L12;
        r0.hide();
        this.bulletin = null;
    L12:
        Context r12 = this.bulletinFactory.getContext();
        Theme.ResourcesProvider r13 = this.bulletinFactory.getResourcesProvider();
        AnonymousClass1 r14 = new AnonymousClass1(this, r12, r13);
        r14.titleTextView.setText(r3.title);
        SimpleTextView r02 = r14.titleTextView;
        Context r5 = this.bulletinFactory.getContext();
        int r6 = Theme.key_featuredStickers_addButton;
        r02.setRightDrawable(new AdOptionsDrawable(r5, Theme.getColor(r6, this.bulletinFactory.getResourcesProvider())));
        r14.subtitleTextView.setText(r3.message);
        TLRPC.MessageMedia r03 = r3.media;
        if (r03 == null) goto L20;
        TLRPC.Document r52 = r03.document;
        if (r52 == null) goto L17;
        r14.imageView.setImage(ImageLocation.getForDocument(r3.media.document), "48_48", ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r52.thumbs, 48), r3.media.document), "48_48", null, 0, 0, null);
    L24:
        CloseDrawable r4 = new CloseDrawable(r14.buttonView, r3.min_display_duration, r3.max_display_duration, this.currentBulletinPassedTime);
        r4.setColor(Theme.getColor(r6, this.bulletinFactory.getResourcesProvider()));
        r14.buttonView.setImageDrawable(r4);
        r14.buttonView.setOnClickListener(new VideoAds$$ExternalSyntheticLambda14(this, r4));
        Bulletin r2 = this.bulletinFactory.create(r14, r3.max_display_duration * 1000);
        this.bulletin = r2;
        r2.setCanHideOnShow = false;
        r2.setCanHide(false);
        VideoAds$$ExternalSyntheticLambda15 r62 = new VideoAds$$ExternalSyntheticLambda15(this, r2, r3);
        boolean[] r32 = new boolean[1];
        VideoAds$$ExternalSyntheticLambda16 r04 = new VideoAds$$ExternalSyntheticLambda16(this, r2, r32, r4, new long[1], r62, new long[1], r8, r3);
        AndroidUtilities.runOnUIThread(r62, r3.min_display_duration * 1000);
        Bulletin r42 = this.bulletin;
        r42.hideAfterBottomSheet = false;
        r42.setOnHideListener(new VideoAds$$ExternalSyntheticLambda17(this, r2, new boolean[1]));
        r14.titleTextView.setRightDrawableOnClick(new VideoAds$$ExternalSyntheticLambda18(this, r2, r3, r12, r13, r14, r04));
        this.bulletin.setOnClickListener(new VideoAds$$ExternalSyntheticLambda19(this, r3));
        this.bulletin.show();
        logSponsoredShown(r3);
        return;
    L17:
        TLRPC.Photo r05 = r03.photo;
        if (r05 == null) goto L24;
        TLRPC.PhotoSize r06 = FileLoader.getClosestPhotoSizeWithSize(r05.sizes, 48, true, null, true);
        r14.imageView.setImage(ImageLocation.getForPhoto(r06, r3.media.photo), "48_48", ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r3.media.photo.sizes, 48, true, r06, false), r3.media.photo), "48_48", null, 0, 0, null);
        goto L24
    L20:
        TLRPC.Photo r07 = r3.photo;
        if (r07 == null) goto L23;
        TLRPC.PhotoSize r08 = FileLoader.getClosestPhotoSizeWithSize(r07.sizes, 48, true, null, true);
        r14.imageView.setImage(ImageLocation.getForPhoto(r08, r3.photo), "48_48", ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r3.photo.sizes, 48, true, r08, false), r3.photo), "48_48", null, 0, 0, null);
        goto L24
    L23:
        r14.hideImage();
        goto L24
    }

    private void showPremium() {
        PremiumFeatureBottomSheet r0 = this.premiumSheet;
        if (r0 == null) goto L5;
        r0.dismiss();
        this.premiumSheet = null;
    L5:
        PremiumFeatureBottomSheet r1 = new PremiumFeatureBottomSheet(new AnonymousClass2(this), 3, true);
        this.premiumSheet = r1;
        r1.setOnDismissListener(new VideoAds$$ExternalSyntheticLambda0(this, r1));
        r1.show();
        checkPopupShownCallback();
    }

    public boolean isPopupShown() {
        ItemOptions r0 = this.currentMenu;
        if (r0 != null) goto L5;
    L6:
        PremiumFeatureBottomSheet r1 = this.premiumSheet;
        if (r1 != null) goto L9;
        return false;
    L9:
        if (r1.isShown() == false) goto L15;
        return true;
    L15:
        return false;
    L5:
        if (r0.isShown() == false) goto L6;
        return true;
    }

    public void logSponsoredClicked(TLRPC.TL_sponsoredMessage r2) {
        if (r2 == null) goto L9;
        TLRPC.TL_messages_clickSponsoredMessage r0 = new TLRPC.TL_messages_clickSponsoredMessage();
        r0.random_id = r2.random_id;
        r0.media = false;
        r0.fullscreen = false;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L8;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
        return;
    L8:
        return;
    }

    public void logSponsoredShown(TLRPC.TL_sponsoredMessage r2) {
        if (r2 == null) goto L9;
        TLRPC.TL_messages_viewSponsoredMessage r0 = new TLRPC.TL_messages_viewSponsoredMessage();
        r0.random_id = r2.random_id;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L8;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
        return;
    L8:
        return;
    }

    public void setPauseOnPopupCallback(Runnable r1) {
        this.onPopupCallback = r1;
    }

    public void setWaitingPaused(boolean r5) {
        if (this.waitingPaused == r5) goto L14;
        this.waitingPaused = r5;
        AndroidUtilities.cancelRunOnUIThread(this.showRunnable);
        if (r5 == false) goto L9;
        this.waitingTimeSince = System.currentTimeMillis();
        return;
    L9:
        this.lastTime += System.currentTimeMillis() - this.waitingTimeSince;
        if (this.bulletin != null) goto L13;
        schedule();
        return;
    L13:
        return;
    }

    public void stop() {
        if (this.bulletin == null) goto L10;
        this.currentBulletinPassedTime = System.currentTimeMillis() - this.bulletinShowTime;
        if (this.ads.isEmpty() == true) goto L9;
        TLRPC.TL_sponsoredMessage r0 = this.ads.get(0);
        if (this.currentBulletinPassedTime <= (r0.min_display_duration * 1000)) goto L9;
        this.currentBulletinPassedTime = 0;
        this.ads.remove(0);
        this.first = false;
    L9:
        this.bulletin.hide();
        this.bulletin = null;
    L11:
        ItemOptions r02 = this.currentMenu;
        if (r02 == null) goto L14;
        r02.dismiss();
        this.currentMenu = null;
    L14:
        this.bulletin = null;
        if (this.loading == false) goto L17;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.requestId, true);
        this.requestId = 0;
        this.loading = false;
    L17:
        AndroidUtilities.cancelRunOnUIThread(this.showRunnable);
        setWaitingPaused(true);
        return;
    L10:
        this.currentBulletinPassedTime = 0;
        goto L11
    }
}
