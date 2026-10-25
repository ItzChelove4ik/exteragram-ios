package org.telegram.ui.Stories;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.Layout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.timepicker.TimeModel;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumButtonView;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class StealthModeAlert extends BottomSheet {
    private final PremiumButtonView button;
    private Listener listener;
    private boolean stealthModeIsActive;
    private int type;
    Runnable updateButtonRunnuble;

    /* renamed from: org.telegram.ui.Stories.StealthModeAlert$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ StealthModeAlert this$0;
        final /* synthetic */ float val$topOffset;

        /* renamed from: org.telegram.ui.Stories.StealthModeAlert$1$1, reason: invalid class name and collision with other inner class name */
        public class C00671 implements Bulletin.Delegate {
            final /* synthetic */ AnonymousClass1 this$1;

            public C00671(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getTopOffset(int r1) {
                return (int) (this.this$1.val$topOffset + AndroidUtilities.dp(58.0f));
            }
        }

        public AnonymousClass1(StealthModeAlert r1, Context r2, float r3) {
            this.this$0 = r1;
            this.val$topOffset = r3;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            Bulletin.addDelegate(this.this$0.container, new C00671(this));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Bulletin.removeDelegate(this.this$0.container);
        }
    }

    public class ItemCell extends FrameLayout {
        TextView description;
        ImageView imageView;
        TextView textView;
        final /* synthetic */ StealthModeAlert this$0;

        public ItemCell(StealthModeAlert r11, Context r12) {
            this.this$0 = r11;
            super(r12);
            ImageView r0 = new ImageView(r12);
            this.imageView = r0;
            r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_featuredStickers_addButton), PorterDuff.Mode.MULTIPLY));
            addView(this.imageView, LayoutHelper.createFrame(28, 28.0f, 0, 25.0f, 12.0f, 16.0f, 0.0f));
            TextView r02 = new TextView(r12);
            this.textView = r02;
            r02.setTypeface(AndroidUtilities.bold());
            this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, StealthModeAlert.access$000(r11)));
            this.textView.setTextSize(1, 14.0f);
            addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 0, 68.0f, 8.0f, 16.0f, 0.0f));
            TextView r03 = new TextView(r12);
            this.description = r03;
            r03.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, StealthModeAlert.access$100(r11)));
            this.description.setTextSize(1, 14.0f);
            addView(this.description, LayoutHelper.createFrame(-1, -2.0f, 0, 68.0f, 28.0f, 16.0f, 8.0f));
        }
    }

    public interface Listener {
        void onButtonClicked(boolean r1);
    }

    /* renamed from: $r8$lambda$8RCZlFLwVhO4hPcjt6fZFCu-1jw, reason: not valid java name */
    public static /* synthetic */ void m19681$r8$lambda$8RCZlFLwVhO4hPcjt6fZFCu1jw() {
    }

    public static /* synthetic */ void $r8$lambda$V1dBXsEinN1XUmgboDP4oTZUztE(TLObject r0, TLRPC.TL_error r1) {
        AndroidUtilities.runOnUIThread(new StealthModeAlert$$ExternalSyntheticLambda4());
    }

    public static /* synthetic */ void $r8$lambda$VFn0scyyAtwh7Zs4xVBlqLgTpc4(StealthModeAlert r0, TLRPC.User r1, int r2, Theme.ResourcesProvider r3, View r4) {
        r0.lambda$new$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$rZl9zXO1Jia84MPGleijcjUCx1E(StealthModeAlert r0) {
        r0.lambda$new$4();
    }

    public static /* synthetic */ void $r8$lambda$uTAIF01eKTFAcSmMpRaxV7ucPB0(StealthModeAlert r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public StealthModeAlert(Context r19, float r20, int r21, Theme.ResourcesProvider r22) {
        super(r19, false, r22);
        this.updateButtonRunnuble = new StealthModeAlert$$ExternalSyntheticLambda0(this);
        fixNavigationBar();
        this.type = r21;
        AnonymousClass1 r5 = new AnonymousClass1(this, getContext(), r20);
        ImageView r6 = new ImageView(getContext());
        r6.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        r6.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_featuredStickers_addButton)));
        r6.setImageResource(R.drawable.large_stealth);
        if (Theme.isCurrentThemeMonet() == false) goto L5;
        int r7 = Theme.getColor(Theme.key_chats_actionIcon);
    L6:
        r6.setColorFilter(r7, PorterDuff.Mode.SRC_IN);
        r5.addView(r6, LayoutHelper.createFrame(80, 80.0f, 1, 0.0f, 18.0f, 0.0f, 0.0f));
        LinearLayout r62 = new LinearLayout(getContext());
        r62.setOrientation(1);
        r5.addView(r62, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 116.0f, 0.0f, 0.0f));
        TextView r8 = new TextView(getContext());
        r8.setTextSize(1, 20.0f);
        r8.setTypeface(AndroidUtilities.bold());
        r8.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r22));
        r8.setText(LocaleController.getString(R.string.StealthModeTitle));
        r62.addView(r8, LayoutHelper.createLinear(-2, -2, 1));
        SimpleTextView r82 = new SimpleTextView(getContext());
        r82.setTextSize(14);
        r82.setAlignment(Layout.Alignment.ALIGN_CENTER);
        r82.setMaxLines(100);
        r82.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r22));
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L9;
        r82.setText(LocaleController.getString(R.string.StealthModeHint));
    L10:
        r62.addView(r82, LayoutHelper.createLinear(-2, -2, 1, 36, 10, 36, 0));
        ItemCell r83 = new ItemCell(this, getContext());
        r83.imageView.setImageResource(R.drawable.msg_stealth_5min);
        r83.textView.setText(LocaleController.getString(R.string.HideRecentViews));
        r83.description.setText(LocaleController.getString(R.string.HideRecentViewsDescription));
        r62.addView(r83, LayoutHelper.createLinear(-1, -2, 0, 0, 20, 0, 0));
        ItemCell r84 = new ItemCell(this, getContext());
        r84.imageView.setImageResource(R.drawable.msg_stealth_25min);
        r84.textView.setText(LocaleController.getString(R.string.HideNextViews));
        r84.description.setText(LocaleController.getString(R.string.HideNextViewsDescription));
        r62.addView(r84, LayoutHelper.createLinear(-1, -2, 0, 0, 10, 0, 0));
        PremiumButtonView r85 = new PremiumButtonView(r19, AndroidUtilities.dp(8.0f), true, r22);
        this.button = r85;
        r85.drawGradient = false;
        r85.overlayTextView.getDrawable().setSplitByWords(false);
        r85.setIcon(R.raw.unlock_icon);
        ScaleStateListAnimator.apply(r85);
        TLRPC.User r1 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        if (r1.premium == true) goto L13;
        r85.setIcon(R.raw.unlock_icon);
        r85.setButton(LocaleController.getString(R.string.UnlockStealthMode), new StealthModeAlert$$ExternalSyntheticLambda1(this));
    L14:
        r62.addView(r85, LayoutHelper.createLinear(-1, 48, 80, 14, 24, 14, 16));
        setCustomView(r5);
        r85.setOnClickListener(new StealthModeAlert$$ExternalSyntheticLambda2(this, r1, r21, r22));
        return;
    L13:
        updateButton(false);
        goto L14
    L9:
        r82.setText(LocaleController.getString(R.string.StealthModePremiumHint));
        goto L10
    L5:
        r7 = -1;
        goto L6
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(StealthModeAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(StealthModeAlert r0) {
        return r0.resourcesProvider;
    }

    private /* synthetic */ void lambda$new$0(View r3) {
        dismiss();
        BaseFragment r2 = LaunchActivity.getLastFragment();
        if (r2 == null) goto L6;
        r2.showDialog(new PremiumFeatureBottomSheet(r2, 14, false));
        return;
    }

    private /* synthetic */ void lambda$new$3(TLRPC.User r5, int r6, Theme.ResourcesProvider r7, View r8) {
        if (r5.premium == true) goto L9;
        dismiss();
        BaseFragment r4 = LaunchActivity.getLastFragment();
        if (r4 == null) goto L41;
        r4.showDialog(new PremiumFeatureBottomSheet(r4, 14, false));
        return;
    L41:
        return;
    L9:
        if (this.stealthModeIsActive == false) goto L14;
        dismiss();
        Listener r42 = this.listener;
        if (r42 == null) goto L42;
        r42.onButtonClicked(false);
        return;
    L42:
        return;
    L14:
        StoriesController r52 = MessagesController.getInstance(this.currentAccount).getStoriesController();
        TL_stories.TL_storiesStealthMode r0 = r52.getStealthMode();
        if (r0 != null) goto L17;
    L29:
        TL_stories.TL_stories_activateStealthMode r72 = new TL_stories.TL_stories_activateStealthMode();
        r72.future = true;
        r72.past = true;
        TL_stories.TL_storiesStealthMode r82 = new TL_stories.TL_storiesStealthMode();
        r82.flags |= 3;
        r82.cooldown_until_date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + MessagesController.getInstance(this.currentAccount).stealthModeCooldown;
        r82.active_until_date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + MessagesController.getInstance(this.currentAccount).stealthModeFuture;
        r52.setStealthMode(r82);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r72, new StealthModeAlert$$ExternalSyntheticLambda3());
        this.containerView.performHapticFeedback(3);     // Catch: Exception -> L38
    L31:
        dismiss();
        if (r6 != 0) goto L34;
        showStealthModeEnabledBulletin();
    L34:
        Listener r43 = this.listener;
        if (r43 == null) goto L45;
        r43.onButtonClicked(true);
        return;
    L45:
        return;
    L17:
        if (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() > r0.cooldown_until_date) goto L29;
        if (this.stealthModeIsActive == false) goto L25;
        dismiss();
        Listener r44 = this.listener;
        if (r44 == null) goto L43;
        r44.onButtonClicked(false);
        return;
    L43:
        return;
    L25:
        BulletinFactory r45 = BulletinFactory.of(this.container, r7);
        if (r45 == null) goto L44;
        r45.createErrorBulletin(AndroidUtilities.replaceTags(LocaleController.getString(R.string.StealthModeCooldownHint))).show(true);
        return;
    }

    private /* synthetic */ void lambda$new$4() {
        if (isShowing() == false) goto L6;
        updateButton(true);
        return;
    }

    public static void showStealthModeEnabledBulletin() {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0.getLastStoryViewer() == null) goto L5;
        BulletinFactory r02 = BulletinFactory.of(r0.getLastStoryViewer().windowView, r0.getLastStoryViewer().getResourceProvider());
    L6:
        if (r02 == null) goto L9;
        r02.createSimpleLargeBulletin(R.drawable.msg_stories_stealth2, LocaleController.getString(R.string.StealthModeOn), LocaleController.getString(R.string.StealthModeOnHint)).show();
        return;
    L9:
        return;
    L5:
        r02 = BulletinFactory.global();
        goto L6
    }

    private void updateButton(boolean r9) {
        TL_stories.TL_storiesStealthMode r0 = MessagesController.getInstance(this.currentAccount).getStoriesController().getStealthMode();
        if (r0 != null) goto L5;
    L8:
        if (r0 == null) goto L14;
        int r2 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        int r02 = r0.cooldown_until_date;
        if (r2 > r02) goto L14;
        long r22 = r02 - ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        int r03 = (int) (r22 % 60);
        long r23 = r22 / 60;
        StringBuilder r3 = new StringBuilder();
        Locale r4 = Locale.ENGLISH;
        r3.append(String.format(r4, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf((int) (r23 / 60))}));
        r3.append(String.format(r4, ":%02d", new Object[]{Integer.valueOf((int) (r23 % 60))}));
        r3.append(String.format(r4, ":%02d", new Object[]{Integer.valueOf(r03)}));
        this.button.setOverlayText(LocaleController.formatString("AvailableIn", R.string.AvailableIn, new Object[]{r3.toString()}), true, r9);
        this.button.overlayTextView.setTextColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_featuredStickers_buttonText), 125));
        AndroidUtilities.cancelRunOnUIThread(this.updateButtonRunnuble);
        AndroidUtilities.runOnUIThread(this.updateButtonRunnuble, 1000);
        return;
    L14:
        int r04 = this.type;
        if (r04 != 0) goto L17;
        this.button.setOverlayText(LocaleController.getString(R.string.EnableStealthMode), true, r9);
    L19:
        this.button.overlayTextView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        return;
    L17:
        if (r04 != 1) goto L19;
        this.button.setOverlayText(LocaleController.getString(R.string.EnableStealthModeAndOpenStory), true, r9);
        goto L19
    L5:
        if (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() >= r0.active_until_date) goto L8;
        this.stealthModeIsActive = true;
        this.button.setOverlayText(LocaleController.getString(R.string.StealthModeIsActive), true, r9);
        this.button.overlayTextView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
    }

    public void setListener(Listener r1) {
        this.listener = r1;
    }
}
