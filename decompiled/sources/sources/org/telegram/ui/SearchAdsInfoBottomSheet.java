package org.telegram.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

/* loaded from: classes6.dex */
public class SearchAdsInfoBottomSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private final LinearLayout customView;
    private final Paint topIconBgPaint;

    public class FeatureCell extends FrameLayout {
        final /* synthetic */ SearchAdsInfoBottomSheet this$0;

        public FeatureCell(SearchAdsInfoBottomSheet r23, Context r24, int r25, CharSequence r26, CharSequence r27) {
            this.this$0 = r23;
            super(r24);
            boolean r2 = LocaleController.isRTL;
            ImageView r3 = new ImageView(getContext());
            Drawable r4 = getContext().getResources().getDrawable(r25).mutate();
            int r6 = Theme.key_windowBackgroundWhiteBlackText;
            r4.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r6, SearchAdsInfoBottomSheet.access$000(r23)), PorterDuff.Mode.MULTIPLY));
            r3.setImageDrawable(r4);
            if (r2 == false) goto L5;
            int r9 = 5;
        L7:
            if (r2 == false) goto L9;
            float r10 = 0.0f;
        L10:
            if (r2 == false) goto L12;
            float r12 = 27.0f;
        L13:
            addView(r3, LayoutHelper.createFrame(24, 24.0f, r9, r10, 6.0f, r12, 0.0f));
            TextView r32 = new TextView(getContext());
            r32.setText(r26);
            r32.setTextColor(Theme.getColor(r6, SearchAdsInfoBottomSheet.access$100(r23)));
            r32.setTextSize(1, 14.0f);
            r32.setTypeface(AndroidUtilities.bold());
            if (r2 == false) goto L16;
            int r17 = 5;
        L18:
            if (r2 == false) goto L20;
            float r18 = 27.0f;
        L21:
            if (r2 == false) goto L23;
            float r20 = 68.0f;
        L24:
            addView(r32, LayoutHelper.createFrame(-2, -2.0f, r17, r18, 0.0f, r20, 0.0f));
            LinkSpanDrawable.LinksTextView r33 = new LinkSpanDrawable.LinksTextView(getContext());
            r33.setText(r27);
            r33.setTextSize(1, 14.0f);
            r33.setTextColor(Theme.getColor(Theme.key_player_actionBarSubtitle, SearchAdsInfoBottomSheet.access$200(r23)));
            r33.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, SearchAdsInfoBottomSheet.access$300(r23)));
            r33.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            r33.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            if (r2 == false) goto L27;
            int r102 = 5;
        L28:
            int r1 = 68;
            if (r2 == false) goto L31;
            int r5 = 27;
        L32:
            float r11 = r5 - 4;
            if (r2 == true) goto L36;
            r1 = 27;
        L36:
            addView(r33, LayoutHelper.createFrame(-2, -2.0f, r102, r11, 18.0f, r1 - 4, 0.0f));
            return;
        L31:
            r5 = 68;
            goto L32
        L27:
            r102 = 3;
            goto L28
        L23:
            r20 = 27.0f;
            goto L24
        L20:
            r18 = 68.0f;
            goto L21
        L16:
            r17 = 3;
            goto L18
        L12:
            r12 = 0.0f;
            goto L13
        L9:
            r10 = 27.0f;
            goto L10
        L5:
            r9 = 3;
            goto L7
        }
    }

    /* renamed from: $r8$lambda$-W_xyUnRnw8VK_evnbkLy8G6gQY, reason: not valid java name */
    public static /* synthetic */ void m18515$r8$lambda$W_xyUnRnw8VK_evnbkLy8G6gQY(SearchAdsInfoBottomSheet r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: $r8$lambda$DyJPuiihQCg8p3JGhgbF-IsWfV8, reason: not valid java name */
    public static /* synthetic */ void m18516$r8$lambda$DyJPuiihQCg8p3JGhgbFIsWfV8(SearchAdsInfoBottomSheet r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$EBB2iufauQ6NiK5IxPt9VEFyZSg(SearchAdsInfoBottomSheet r0, boolean r1, Runnable r2) {
        r0.lambda$new$0(r1, r2);
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public SearchAdsInfoBottomSheet(Context r26, Theme.ResourcesProvider r27, Runnable r28) {
        super(r26, null, false, false, false, r27);
        fixNavigationBar();
        this.topPadding = 0.2f;
        Paint r0 = new Paint(1);
        this.topIconBgPaint = r0;
        r0.setStyle(Paint.Style.FILL);
        int r8 = Theme.key_featuredStickers_addButton;
        r0.setColor(Theme.getColor(r8, r27));
        LinearLayout r9 = new LinearLayout(r26);
        this.customView = r9;
        r9.setPadding(this.backgroundPaddingLeft + AndroidUtilities.dp(6.0f), 0, this.backgroundPaddingLeft + AndroidUtilities.dp(6.0f), 0);
        r9.setOrientation(1);
        FrameLayout r02 = new FrameLayout(r26);
        RLottieImageView r3 = new RLottieImageView(getContext());
        r3.setScaleType(ImageView.ScaleType.CENTER);
        r3.setImageResource(R.drawable.large_ads_info);
        r3.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        r3.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(r8, r27)));
        r02.addView(r3, LayoutHelper.createFrame(80, 80.0f, 1, 0.0f, 20.0f, 0.0f, 0.0f));
        r9.addView(r02, LayoutHelper.createLinear(-1, 100, 0.0f, 0.0f, 0.0f, 0.0f));
        TextView r03 = new TextView(r26);
        r03.setText(LocaleController.getString(R.string.SearchAdsAboutTitle));
        r03.setTypeface(AndroidUtilities.bold());
        int r12 = Theme.key_windowBackgroundWhiteBlackText;
        r03.setTextColor(Theme.getColor(r12, r27));
        r03.setTextSize(1, 20.0f);
        r03.setGravity(1);
        r9.addView(r03, LayoutHelper.createLinear(-2, -2, 1, 22, 14, 22, 0));
        TextView r04 = new TextView(r26);
        r04.setText(LocaleController.getString(R.string.SearchAdsAboutSubtitle));
        r04.setTextColor(Theme.getColor(r12, r27));
        r04.setTextSize(1, 14.0f);
        r04.setGravity(1);
        r9.addView(r04, LayoutHelper.createLinear(-2, -2, 1, 22, 8, 22, 0));
        r9.addView(new FeatureCell(this, r26, R.drawable.menu_privacy, LocaleController.getString(R.string.SearchAdsAbout1Title), LocaleController.getString(R.string.SearchAdsAbout1Subtitle)), LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 20, 0, 0));
        boolean r05 = UserConfig.getInstance(this.currentAccount).isPremium();
        int r32 = R.drawable.menu_feature_noads;
        String r4 = LocaleController.getString(R.string.SearchAdsAbout2Title);
        if (r05 == false) goto L5;
        int r5 = R.string.SearchAdsAbout2SubtitlePremium;
    L6:
        r9.addView(new FeatureCell(this, r26, r32, r4, AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(r5), new SearchAdsInfoBottomSheet$$ExternalSyntheticLambda0(this, r05, r28)), true)), LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 16, 0, 0));
        View r06 = new View(getContext());
        r06.setBackgroundColor(Theme.getColor(Theme.key_divider, r27));
        LinearLayout.LayoutParams r33 = new LinearLayout.LayoutParams(-1, 1);
        r33.setMargins(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(20.0f));
        r9.addView(r06, r33);
        TextView r07 = new TextView(r26);
        r07.setText(LocaleController.getString(R.string.SearchAdsAboutLaunchTitle));
        r07.setTypeface(AndroidUtilities.bold());
        r07.setTextColor(Theme.getColor(r12, r27));
        r07.setTextSize(1, 20.0f);
        r07.setTextAlignment(4);
        r07.setGravity(17);
        r9.addView(r07, LayoutHelper.createLinear(-2, -2, 1, 22, 0, 22, 0));
        SpannableStringBuilder r08 = AndroidUtilities.replaceCharSequence("%1$s", AndroidUtilities.replaceTags(LocaleController.getString(R.string.SearchAdsAboutLaunchSubtitle)), AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.SearchAdsAboutLaunchLearnMore), new SearchAdsInfoBottomSheet$$ExternalSyntheticLambda1(this)), true));
        LinkSpanDrawable.LinksTextView r52 = new LinkSpanDrawable.LinksTextView(r26);
        r52.setText(r08);
        r52.setTextColor(Theme.getColor(r12, r27));
        r52.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r27));
        r52.setTextSize(1, 14.0f);
        r52.setGravity(1);
        r52.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r9.addView(r52, LayoutHelper.createLinear(-2, -2, 1, 26, 8, 26, 0));
        TextView r09 = new TextView(r26);
        r09.setLines(1);
        r09.setSingleLine(true);
        r09.setGravity(17);
        r09.setEllipsize(TextUtils.TruncateAt.END);
        r09.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r27));
        r09.setTypeface(AndroidUtilities.bold());
        r09.setTextSize(1, 14.0f);
        r09.setText(LocaleController.getString(R.string.SearchAdsAboutUnderstood));
        r09.setBackground(Theme.AdaptiveRipple.filledRect(Theme.getColor(r8, r27), new float[]{24.0f}));
        r09.setOnClickListener(new SearchAdsInfoBottomSheet$$ExternalSyntheticLambda2(this));
        r9.addView(r09, LayoutHelper.createLinear(-1, 48, 0, 14, 22, 14, 14));
        this.adapter.update(false);
        return;
    L5:
        r5 = R.string.SearchAdsAbout2Subtitle;
        goto L6
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(SearchAdsInfoBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(SearchAdsInfoBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(SearchAdsInfoBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(SearchAdsInfoBottomSheet r0) {
        return r0.resourcesProvider;
    }

    private /* synthetic */ void lambda$new$0(boolean r2, Runnable r3) {
        if (r2 == false) goto L4;
        MessagesController.getInstance(this.currentAccount).disableAds(true);
        r3.run();
    L8:
        dismiss();
        return;
    L4:
        BaseFragment r22 = LaunchActivity.getSafeLastFragment();
        if (r22 != null) goto L7;
        return;
    L7:
        r22.presentFragment(new PremiumPreviewFragment(PremiumPreviewFragment.featureTypeToServerString(3)));
        goto L8
    }

    private /* synthetic */ void lambda$new$1() {
        dismiss();
        Browser.openUrl(getContext(), LocaleController.getString(R.string.PromoteUrl));
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new SearchAdsInfoBottomSheet$$ExternalSyntheticLambda3(this), this.resourcesProvider);
        this.adapter = r0;
        return r0;
    }

    public void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        r1.add(UItem.asCustom(this.customView));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.AboutRevenueSharingAds);
    }
}
