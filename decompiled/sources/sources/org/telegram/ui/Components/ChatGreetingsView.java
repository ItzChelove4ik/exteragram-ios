package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.utils.BitmapsCache;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Premium.PremiumButtonView;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes7.dex */
public abstract class ChatGreetingsView extends LinearLayout {
    private int backgroundHeight;
    private final int currentAccount;
    private TextView descriptionView;
    private boolean disableBackground;
    boolean ignoreLayot;
    private boolean isSuggest;
    private Listener listener;
    public BackupImageView nextStickerToSendView;
    private TLRPC.Document preloadedGreetingsSticker;
    private TextView premiumButtonView;
    private RLottieImageView premiumIconView;
    private boolean premiumLock;
    private TextView premiumTextView;
    public boolean preview;
    private final Theme.ResourcesProvider resourcesProvider;
    boolean showGreetings;
    public FrameLayout stickerContainer;
    boolean stickerExplicitlyHidden;
    public BackupImageView stickerToSendView;
    private TextView titleView;
    private AnimatorSet togglingStickersAnimator;
    private float viewTop;
    private float viewTranslationX;
    private boolean visiblePartSet;
    boolean wasDraw;

    /* renamed from: org.telegram.ui.Components.ChatGreetingsView$1, reason: invalid class name */
    public class AnonymousClass1 extends TextView {
        private final Path clipPath;
        StarParticlesView.Drawable starParticlesDrawable;
        final /* synthetic */ ChatGreetingsView this$0;

        public AnonymousClass1(ChatGreetingsView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.clipPath = new Path();
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r2) {
            if (this.starParticlesDrawable == null) goto L5;
            r2.save();
            r2.clipPath(this.clipPath);
            this.starParticlesDrawable.onDraw(r2);
            r2.restore();
            invalidate();
        L5:
            super.onDraw(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            StarParticlesView.Drawable r12 = new StarParticlesView.Drawable(10);
            this.starParticlesDrawable = r12;
            r12.type = 100;
            r12.isCircle = false;
            r12.roundEffect = true;
            r12.useRotate = false;
            r12.useBlur = true;
            r12.checkBounds = true;
            r12.size1 = 1;
            r12.k3 = 0.98f;
            r12.k2 = 0.98f;
            r12.k1 = 0.98f;
            r12.paused = false;
            r12.speedScale = 0.0f;
            r12.minLifeTime = 750;
            r12.randLifeTime = 750;
            r12.init();
            RectF r13 = AndroidUtilities.rectTmp;
            r13.set(0.0f, 0.0f, getWidth(), getHeight());
            this.starParticlesDrawable.rect.set(r13);
            this.starParticlesDrawable.rect2.set(r13);
            this.starParticlesDrawable.resetPositions();
            this.clipPath.reset();
            this.clipPath.addRoundRect(r13, getHeight() / 2.0f, getHeight() / 2.0f, Path.Direction.CW);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatGreetingsView$2, reason: invalid class name */
    public class AnonymousClass2 implements ImageReceiver.ImageReceiverDelegate {
        final /* synthetic */ ChatGreetingsView this$0;
        final /* synthetic */ Runnable val$whenDone;
        private boolean waited;

        /* renamed from: $r8$lambda$btIy-qglyuTiZB5a2tU2a3b30fE, reason: not valid java name */
        public static /* synthetic */ void m9945$r8$lambda$btIyqglyuTiZB5a2tU2a3b30fE(AnonymousClass2 r0, Runnable r1) {
            r0.lambda$didSetImageBitmap$0(r1);
        }

        public AnonymousClass2(ChatGreetingsView r1, Runnable r2) {
            this.this$0 = r1;
            this.val$whenDone = r2;
        }

        private /* synthetic */ void lambda$didSetImageBitmap$0(Runnable r1) {
            ChatGreetingsView.m9944$$Nest$mtoggleToNextSticker(this.this$0);
            if (r1 == null) goto L6;
            r1.run();
            return;
        }

        @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
        public void didSetImage(ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        }

        @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
        public void didSetImageBitmap(int r1, String r2, Drawable r3) {
            if (this.waited == true) goto L24;
            if (r1 != 0) goto L7;
        L8:
            if (r3 == null) goto L22;
            this.waited = true;
            if ((r3 instanceof RLottieDrawable) == false) goto L17;
            RLottieDrawable r32 = (RLottieDrawable) r3;
            BitmapsCache r12 = r32.bitmapsCache;
            if (r12 == null) goto L17;
            if (r12.needGenCache() == false) goto L17;
            r32.whenCacheDone = new ChatGreetingsView$2$$ExternalSyntheticLambda0(this, this.val$whenDone);
            return;
        L17:
            ChatGreetingsView.m9944$$Nest$mtoggleToNextSticker(this.this$0);
            Runnable r0 = this.val$whenDone;
            if (r0 == null) goto L23;
            r0.run();
            return;
        L23:
            return;
        L22:
            return;
        L7:
            if (r1 == 3) goto L8;
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatGreetingsView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        private boolean cancelled;
        final /* synthetic */ ChatGreetingsView this$0;

        public AnonymousClass3(ChatGreetingsView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.cancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (this.cancelled == false) goto L5;
            return;
        L5:
            ChatGreetingsView r32 = this.this$0;
            BackupImageView r0 = r32.stickerToSendView;
            r32.stickerToSendView = r32.nextStickerToSendView;
            r32.nextStickerToSendView = r0;
            r0.setVisibility(8);
            this.this$0.nextStickerToSendView.setAlpha(0.0f);
            this.this$0.stickerToSendView.setVisibility(0);
            this.this$0.stickerToSendView.setAlpha(1.0f);
        }
    }

    public interface Listener {
        void onGreetings(TLRPC.Document r1);
    }

    public static /* synthetic */ void $r8$lambda$13a8CLbV7ZHYOY05YUpOVmneXOw(BottomSheet r2, View r3) {
        BaseFragment r32 = LaunchActivity.getLastFragment();
        if (r32 == null) goto L6;
        r32.presentFragment(new PremiumPreviewFragment("contact"));
        r2.dismiss();
        return;
    }

    public static /* synthetic */ void $r8$lambda$QgZpcVSii6PwdrzxQs5KZeJ8Xrc(ChatGreetingsView r0, View r1) {
        r0.lambda$setPremiumLock$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$dfFFOdFo4xLiRcDV9mlguiebWpg(ChatGreetingsView r0, TLRPC.Document r1, View r2) {
        r0.lambda$setSticker$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$faacyxMxmTwvil4YLIxpkUaI26g(ChatGreetingsView r0, TLRPC.Document r1, View r2) {
        r0.lambda$setNextSticker$2(r1, r2);
    }

    /* renamed from: -$$Nest$mtoggleToNextSticker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9944$$Nest$mtoggleToNextSticker(ChatGreetingsView r0) {
        r0.toggleToNextSticker();
    }

    public ChatGreetingsView(Context r5, TLRPC.User r6, int r7, TLRPC.Document r8, Theme.ResourcesProvider r9) {
        super(r5);
        this.showGreetings = !ExteraConfig.getDisableGreetingSticker();
        setOrientation(1);
        this.currentAccount = r7;
        this.resourcesProvider = r9;
        setPadding(0, AndroidUtilities.dp(8.0f), 0, 0);
        TextView r62 = new TextView(r5);
        this.titleView = r62;
        r62.setTextSize(1, 14.0f);
        this.titleView.setTypeface(AndroidUtilities.bold());
        this.titleView.setTextAlignment(4);
        this.titleView.setGravity(17);
        TextView r63 = new TextView(r5);
        this.descriptionView = r63;
        r63.setTextAlignment(4);
        this.descriptionView.setGravity(17);
        this.descriptionView.setTextSize(1, 14.0f);
        this.descriptionView.setGravity(1);
        this.stickerContainer = new FrameLayout(r5);
        BackupImageView r64 = new BackupImageView(r5);
        this.stickerToSendView = r64;
        r64.getImageReceiver().setAspectFit(true);
        this.stickerContainer.addView(this.stickerToSendView, LayoutHelper.createFrame(112, 112.0f));
        ScaleStateListAnimator.apply(this.stickerToSendView);
        BackupImageView r65 = new BackupImageView(r5);
        this.nextStickerToSendView = r65;
        r65.getImageReceiver().setAspectFit(true);
        this.stickerContainer.addView(this.nextStickerToSendView, LayoutHelper.createFrame(112, 112.0f));
        this.nextStickerToSendView.setVisibility(8);
        this.nextStickerToSendView.setAlpha(0.0f);
        ScaleStateListAnimator.apply(this.nextStickerToSendView);
        updateLayout();
        updateColors();
        setText(LocaleController.getString(R.string.NoMessages), LocaleController.getString(R.string.NoMessagesGreetingsDescription));
        this.preloadedGreetingsSticker = r8;
        if (r8 != null) goto L6;
        this.preloadedGreetingsSticker = MediaDataController.getInstance(r7).getGreetingsSticker();
        return;
    }

    public static String createFilter(TLRPC.Document r6) {
        if (AndroidUtilities.isTablet() == false) goto L6;
        float r0 = AndroidUtilities.getMinTabletSide();
        float r1 = 0.4f;
    L5:
        float r02 = r0 * r1;
        int r12 = 0;
        int r2 = 0;
    L9:
        if (r2 >= r6.attributes.size()) goto L14;
        TLRPC.DocumentAttribute r3 = r6.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L12;
        r2 = r2 + 1;
        goto L9
    L12:
        r12 = r3.w;
        int r22 = r3.h;
    L16:
        if (MessageObject.isAnimatedStickerDocument(r6, true) == false) goto L20;
        if (r12 != 0) goto L20;
        if (r22 != 0) goto L20;
        r12 = 512;
        r22 = 512;
    L20:
        if (r12 != 0) goto L22;
        r22 = (int) r02;
        r12 = r22 + AndroidUtilities.dp(100.0f);
    L22:
        int r62 = (int) (r22 * (r02 / r12));
        int r13 = (int) r02;
        float r23 = r62;
        if (r23 <= r02) goto L25;
        r13 = (int) (r13 * (r02 / r23));
        r62 = r13;
    L25:
        float r03 = r13;
        float r14 = AndroidUtilities.density;
        return String.format(Locale.US, "%d_%d", new Object[]{Integer.valueOf((int) (r03 / r14)), Integer.valueOf((int) (r62 / r14))});
    L14:
        r22 = 0;
        goto L16
    L6:
        Point r04 = AndroidUtilities.displaySize;
        r0 = Math.min(r04.x, r04.y);
        r1 = 0.5f;
        goto L5
    }

    private void fetchSticker() {
        if (this.preloadedGreetingsSticker == null) goto L5;
        return;
    L5:
        if (this.stickerExplicitlyHidden == true) goto L11;
        TLRPC.Document r0 = MediaDataController.getInstance(this.currentAccount).getGreetingsSticker();
        this.preloadedGreetingsSticker = r0;
        if (this.wasDraw == false) goto L12;
        setSticker(r0);
        return;
    L12:
        return;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$setNextSticker$2(TLRPC.Document r1, View r2) {
        Listener r0 = this.listener;
        if (r0 == null) goto L6;
        r0.onGreetings(r1);
        return;
    }

    private /* synthetic */ void lambda$setPremiumLock$0(View r2) {
        this.premiumIconView.setProgress(0.0f);
        this.premiumIconView.playAnimation();
    }

    private /* synthetic */ void lambda$setSticker$1(TLRPC.Document r1, View r2) {
        Listener r0 = this.listener;
        if (r0 == null) goto L6;
        r0.onGreetings(r1);
        return;
    }

    public static void showPremiumSheet(Context r18, int r19, long r20, Theme.ResourcesProvider r22) {
        BottomSheet r2 = new BottomSheet(r18, false, r22);
        r2.fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, r22));
        LinearLayout r4 = new LinearLayout(r18);
        r4.setOrientation(1);
        r4.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        RLottieImageView r6 = new RLottieImageView(r18);
        r6.setScaleType(ImageView.ScaleType.CENTER);
        r6.setAnimation(R.raw.large_message_lock, 80, 80);
        r6.playAnimation();
        if (Theme.isCurrentThemeMonet() == false) goto L5;
        int r8 = Theme.getColor(Theme.key_chats_actionIcon);
    L6:
        r6.setColorFilter(new PorterDuffColorFilter(r8, PorterDuff.Mode.SRC_IN));
        r6.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r22)));
        r4.addView(r6, LayoutHelper.createLinear(80, 80, 1, 0, 16, 0, 16));
        boolean r62 = MessagesController.getInstance(r19).premiumFeaturesBlocked();
        TextView r7 = new TextView(r18);
        r7.setTypeface(AndroidUtilities.bold());
        r7.setGravity(17);
        int r9 = Theme.key_dialogTextBlack;
        r7.setTextColor(Theme.getColor(r9, r22));
        r7.setTextSize(1, 20.0f);
        if (r62 == false) goto L9;
        int r10 = R.string.PremiumMessageHeaderLocked;
    L10:
        r7.setText(LocaleController.getString(r10));
        r4.addView(r7, LayoutHelper.createLinear(-1, -2, 1, 12, 0, 12, 0));
        TextView r72 = new TextView(r18);
        r72.setGravity(17);
        r72.setTextColor(Theme.getColor(r9, r22));
        r72.setTextSize(1, 14.0f);
        if (r20 <= 0) goto L13;
        String r82 = UserObject.getFirstName(MessagesController.getInstance(r19).getUser(Long.valueOf(r20)));
    L14:
        if (r62 == false) goto L16;
        int r92 = R.string.PremiumMessageTextLocked;
    L17:
        r72.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r92, new Object[]{r82, r82})));
        r4.addView(r72, LayoutHelper.createLinear(-1, -2, 1, 12, 9, 12, 19));
        if (r62 == true) goto L20;
        PremiumButtonView r63 = new PremiumButtonView(r18, true, r22);
        r63.setOnClickListener(new ChatGreetingsView$$ExternalSyntheticLambda0(r2));
        r63.setOverlayText(LocaleController.getString(R.string.PremiumMessageButton), false, false);
        r4.addView(r63, LayoutHelper.createLinear(-1, 48, 1, 0, 0, 0, 4));
    L20:
        r2.setCustomView(r4);
        r2.show();
        return;
    L16:
        r92 = R.string.PremiumMessageText;
        goto L17
    L13:
        r82 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    L9:
        r10 = R.string.PremiumMessageHeader;
        goto L10
    L5:
        r8 = -1;
        goto L6
    }

    private void toggleToNextSticker() {
        AnimatorSet r1 = this.togglingStickersAnimator;
        if (r1 == null) goto L5;
        r1.cancel();
    L5:
        this.nextStickerToSendView.setVisibility(0);
        this.stickerToSendView.setVisibility(0);
        AnimatorSet r12 = new AnimatorSet();
        this.togglingStickersAnimator = r12;
        r12.setDuration(420);
        this.togglingStickersAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.togglingStickersAnimator.addListener(new AnonymousClass3(this));
        AnimatorSet r13 = this.togglingStickersAnimator;
        Property r6 = View.ALPHA;
        ObjectAnimator r3 = ObjectAnimator.ofFloat(this.nextStickerToSendView, r6, new float[]{0.0f, 1.0f});
        Property r8 = View.SCALE_X;
        ObjectAnimator r5 = ObjectAnimator.ofFloat(this.nextStickerToSendView, r8, new float[]{0.7f, 1.0f});
        Property r10 = View.SCALE_Y;
        Property r15 = View.TRANSLATION_Y;
        r13.playTogether(new Animator[]{r3, r5, ObjectAnimator.ofFloat(this.nextStickerToSendView, r10, new float[]{0.7f, 1.0f}), ObjectAnimator.ofFloat(this.nextStickerToSendView, r15, new float[]{-AndroidUtilities.dp(24.0f), 0.0f}), ObjectAnimator.ofFloat(this.stickerToSendView, r6, new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(this.stickerToSendView, r8, new float[]{1.0f, 0.7f}), ObjectAnimator.ofFloat(this.stickerToSendView, r10, new float[]{1.0f, 0.7f}), ObjectAnimator.ofFloat(this.stickerToSendView, r15, new float[]{0.0f, AndroidUtilities.dp(24.0f)})});
        this.togglingStickersAnimator.start();
    }

    private void updateColors() {
        TextView r0 = this.titleView;
        int r1 = Theme.key_chat_serviceText;
        r0.setTextColor(getThemedColor(r1));
        this.descriptionView.setTextColor(getThemedColor(r1));
    }

    private void updateLayout() {
        removeAllViews();
        if (this.showGreetings == false) goto L5;
    L8:
        int r0 = AndroidUtilities.dp(8.0f);
    L9:
        setPadding(0, r0, 0, 0);
        int r1 = 9;
        if (this.premiumLock == false) goto L24;
        addView(this.premiumIconView, LayoutHelper.createLinear(78, 78, 49, 20, 9, 20, 9));
        boolean r02 = MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked();
        TextView r2 = this.premiumTextView;
        if (r02 == false) goto L14;
        r1 = 13;
    L14:
        addView(r2, LayoutHelper.createLinear(-2, -2, 49, 20, 0, 20, r1));
        if (r02 == true) goto L51;
        TextView r03 = this.premiumButtonView;
        if (r03 == null) goto L21;
        if (TextUtils.isEmpty(r03.getText()) == true) goto L21;
    L22:
        addView(this.premiumButtonView, LayoutHelper.createLinear(-2, 30, 49, 20, 2, 20, 13));
        return;
    L21:
        if (this.isSuggest == false) goto L22;
        return;
    L51:
        return;
    L24:
        TextView r04 = this.titleView;
        boolean r22 = this.showGreetings;
        if (r22 == false) goto L27;
    L30:
        int r8 = 20;
    L31:
        int r4 = 6;
        if (r22 == false) goto L34;
    L37:
        int r9 = 6;
    L38:
        if (r22 == false) goto L40;
    L43:
        int r10 = 20;
    L44:
        if (r22 == false) goto L46;
    L49:
        addView(r04, LayoutHelper.createLinear(-2, -2, 1, r8, r9, r10, r4));
        addView(this.descriptionView, LayoutHelper.createLinear(-2, -2, 1, 20, 6, 20, 6));
        addView(this.stickerContainer, LayoutHelper.createLinear(112, 112, 1, 16, 10, 16, 16));
        return;
    L46:
        if (this.preview == true) goto L49;
        r4 = 3;
        goto L49
    L40:
        if (this.preview == true) goto L43;
        r10 = 9;
        goto L44
    L34:
        if (this.preview == true) goto L37;
        r9 = 2;
        goto L38
    L27:
        if (this.preview == true) goto L30;
        r8 = 9;
        goto L31
    L5:
        if (this.preview == true) goto L8;
        r0 = 0;
        goto L9
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r10) {
        if (this.disableBackground == true) goto L9;
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        r0.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
    L8:
        Canvas r1 = r10;
        r1.drawRoundRect(0.0f, 0.0f, getWidth(), getHeight(), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), Theme.getThemePaint("paintChatActionBackground", this.resourcesProvider));
    L11:
        if (this.wasDraw == true) goto L15;
        this.wasDraw = true;
        if (this.stickerExplicitlyHidden == true) goto L15;
        setSticker(this.preloadedGreetingsSticker);
    L15:
        super.dispatchDraw(r1);
        return;
    L7:
        Theme.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
        goto L8
    L9:
        r1 = r10;
        goto L11
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        fetchSticker();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        this.ignoreLayot = true;
        this.descriptionView.setVisibility(0);
        if (this.stickerExplicitlyHidden == true) goto L5;
        this.stickerContainer.setVisibility(0);
    L5:
        super.onMeasure(r4, r5);
        if (this.showGreetings == false) goto L10;
        if (getMeasuredHeight() > View.MeasureSpec.getSize(r5)) goto L10;
    L12:
        this.descriptionView.setVisibility(0);
        if (this.stickerExplicitlyHidden == true) goto L15;
        this.stickerContainer.setVisibility(0);
    L15:
        this.ignoreLayot = false;
        super.onMeasure(r4, r5);
        return;
    L10:
        if (this.preview == true) goto L12;
        this.descriptionView.setVisibility(8);
        this.stickerContainer.setVisibility(8);
        goto L15
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayot == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void resetPremiumLock() {
        setPremiumLock(false, null, null, null);
    }

    @Override // android.view.View
    public void setBackground(Drawable r1) {
        super.setBackground(r1);
        this.disableBackground = true;
    }

    public void setListener(Listener r1) {
        this.listener = r1;
    }

    public void setNextSticker(TLRPC.Document r14, Runnable r15) {
        if (r14 != null) goto L4;
        return;
    L4:
        AnimatorSet r0 = this.togglingStickersAnimator;
        if (r0 == null) goto L7;
        r0.cancel();
    L7:
        this.nextStickerToSendView.getImageReceiver().setDelegate(new AnonymousClass2(this, r15));
        SvgHelper.SvgDrawable r4 = DocumentObject.getSvgThumb(r14, Theme.key_chat_serviceBackground, 1.0f);
        if (r4 == null) goto L10;
        TLRPC.Document r6 = r14;
        this.nextStickerToSendView.setImage(ImageLocation.getForDocument(r14), createFilter(r14), r4, 0, r6);
    L11:
        this.nextStickerToSendView.setOnClickListener(new ChatGreetingsView$$ExternalSyntheticLambda3(this, r6));
        return;
    L10:
        TLRPC.PhotoSize r142 = FileLoader.getClosestPhotoSizeWithSize(r14.thumbs, 90);
        this.nextStickerToSendView.setImage(ImageLocation.getForDocument(r14), createFilter(r14), ImageLocation.getForDocument(r142, r14), null, 0, r14);
        r6 = r14;
        goto L11
    }

    public void setPremiumLock(boolean r7, CharSequence r8, CharSequence r9, View.OnClickListener r10) {
        setPremiumLock(r7, false, r8, r9, r10);
    }

    public void setPremiumLock(boolean r6, boolean r7, CharSequence r8, CharSequence r9, View.OnClickListener r10) {
        if (this.premiumLock != r6) goto L5;
        return;
    L5:
        this.premiumLock = r6;
        this.isSuggest = r7;
        if (r6 == true) goto L8;
    L20:
        updateLayout();
        return;
    L8:
        if (this.premiumIconView != null) goto L13;
        RLottieImageView r62 = new RLottieImageView(getContext());
        this.premiumIconView = r62;
        r62.setScaleType(ImageView.ScaleType.CENTER);
        this.premiumIconView.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        this.premiumIconView.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(78.0f), 469762048));
        RLottieImageView r63 = this.premiumIconView;
        if (r7 == false) goto L12;
        r63.setImageResource(R.drawable.filled_chatlist2);
        goto L13
    L12:
        r63.setAnimation(R.raw.large_message_lock, 80, 80);
        this.premiumIconView.setOnClickListener(new ChatGreetingsView$$ExternalSyntheticLambda1(this));
    L13:
        this.premiumIconView.playAnimation();
        if (this.premiumTextView != null) goto L16;
        TextView r64 = new TextView(getContext());
        this.premiumTextView = r64;
        r64.setTextAlignment(4);
        this.premiumTextView.setGravity(17);
        this.premiumTextView.setTextSize(1, 13.0f);
    L16:
        this.premiumTextView.setText(r8);
        TextView r65 = this.premiumTextView;
        r65.setMaxWidth(HintView2.cutInFancyHalf(r65.getText(), this.premiumTextView.getPaint()));
        TextView r66 = this.premiumTextView;
        int r82 = Theme.key_chat_serviceText;
        r66.setTextColor(getThemedColor(r82));
        this.premiumTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        if (this.premiumButtonView != null) goto L19;
        AnonymousClass1 r67 = new AnonymousClass1(this, getContext());
        this.premiumButtonView = r67;
        r67.setTextAlignment(4);
        this.premiumButtonView.setGravity(17);
        this.premiumButtonView.setTypeface(AndroidUtilities.bold());
        this.premiumButtonView.setTextSize(1, 14.0f);
        this.premiumButtonView.setPadding(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f));
        this.premiumButtonView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(15.0f), 503316480, 855638016));
        ScaleStateListAnimator.apply(this.premiumButtonView);
    L19:
        this.premiumButtonView.setText(r9);
        this.premiumButtonView.setTextColor(getThemedColor(r82));
        this.premiumButtonView.setOnClickListener(r10);
        goto L20
    }

    public void setPreview(CharSequence r4, CharSequence r5) {
        this.preview = true;
        TextView r0 = this.titleView;
        String r1 = null;
        if (r4 != null) goto L5;
        String r2 = null;
    L7:
        if (TextUtils.isEmpty(r2) == false) goto L9;
        r4 = LocaleController.getString(R.string.NoMessages);
    L9:
        r0.setText(r4);
        TextView r42 = this.descriptionView;
        if (r5 == null) goto L14;
        r1 = r5.toString().trim();
    L14:
        if (TextUtils.isEmpty(r1) == false) goto L16;
        r5 = LocaleController.getString(R.string.NoMessagesGreetingsDescription);
    L16:
        r42.setText(r5);
        TextView r43 = this.descriptionView;
        if (r43.getText().length() <= 60) goto L19;
        int r52 = Math.min((int) (AndroidUtilities.displaySize.x * 0.5f), HintView2.cutInFancyHalf(this.descriptionView.getText(), this.descriptionView.getPaint()));
    L20:
        r43.setMaxWidth(r52);
        updateLayout();
        return;
    L19:
        r52 = (int) (AndroidUtilities.displaySize.x * 0.5f);
        goto L20
    L5:
        r2 = r4.toString().trim();
        goto L7
    }

    public void setSticker(String r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        this.stickerExplicitlyHidden = false;
        this.stickerContainer.setVisibility(0);
        this.wasDraw = true;
        this.nextStickerToSendView.clearImage();
        this.stickerToSendView.setImage(ImageLocation.getForPath(r9), "256_256", null, null, 0, null);
    }

    public void setSticker(TLRPC.Document r15) {
        if (r15 == null) goto L5;
        this.stickerExplicitlyHidden = false;
        this.stickerContainer.setVisibility(0);
        this.wasDraw = true;
        this.nextStickerToSendView.clearImage();
        SvgHelper.SvgDrawable r5 = DocumentObject.getSvgThumb(r15, Theme.key_chat_serviceBackground, 1.0f);
        if (r5 == null) goto L11;
        TLRPC.Document r7 = r15;
        this.stickerToSendView.setImage(ImageLocation.getForDocument(r15), createFilter(r15), r5, 0, r7);
    L12:
        this.stickerToSendView.setOnClickListener(new ChatGreetingsView$$ExternalSyntheticLambda2(this, r7));
        return;
    L11:
        TLRPC.PhotoSize r152 = FileLoader.getClosestPhotoSizeWithSize(r15.thumbs, 90);
        this.stickerToSendView.setImage(ImageLocation.getForDocument(r15), createFilter(r15), ImageLocation.getForDocument(r152, r15), null, 0, r15);
        r7 = r15;
        goto L12
    L5:
        if (this.preview == false) goto L14;
        this.stickerExplicitlyHidden = true;
        this.wasDraw = true;
        this.stickerToSendView.clearImage();
        this.nextStickerToSendView.clearImage();
        this.stickerContainer.setVisibility(8);
        this.stickerToSendView.setOnClickListener(null);
        return;
    }

    public void setText(CharSequence r2, CharSequence r3) {
        this.titleView.setText(r2);
        this.descriptionView.setText(r3);
        TextView r22 = this.descriptionView;
        r22.setMaxWidth(HintView2.cutInFancyHalf(r22.getText(), this.descriptionView.getPaint()));
        this.stickerToSendView.setContentDescription(this.descriptionView.getText());
    }

    public void setVisiblePart(float r2, int r3) {
        this.visiblePartSet = true;
        this.backgroundHeight = r3;
        this.viewTop = r2;
        this.viewTranslationX = 0.0f;
    }
}
