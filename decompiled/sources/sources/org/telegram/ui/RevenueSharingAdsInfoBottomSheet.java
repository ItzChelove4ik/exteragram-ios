package org.telegram.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

/* loaded from: classes6.dex */
public class RevenueSharingAdsInfoBottomSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private final LinearLayout customView;
    private final Paint topIconBgPaint;

    public class FeatureCell extends FrameLayout {
        final /* synthetic */ RevenueSharingAdsInfoBottomSheet this$0;

        public FeatureCell(RevenueSharingAdsInfoBottomSheet r23, Context r24, int r25, CharSequence r26, CharSequence r27) {
            this.this$0 = r23;
            super(r24);
            boolean r2 = LocaleController.isRTL;
            ImageView r3 = new ImageView(getContext());
            Drawable r4 = getContext().getResources().getDrawable(r25).mutate();
            int r6 = Theme.key_windowBackgroundWhiteBlackText;
            r4.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r6, RevenueSharingAdsInfoBottomSheet.access$000(r23)), PorterDuff.Mode.MULTIPLY));
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
            r32.setTextColor(Theme.getColor(r6, RevenueSharingAdsInfoBottomSheet.access$100(r23)));
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
            r33.setTextColor(Theme.getColor(Theme.key_player_actionBarSubtitle, RevenueSharingAdsInfoBottomSheet.access$200(r23)));
            r33.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, RevenueSharingAdsInfoBottomSheet.access$300(r23)));
            r33.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            if (r2 == false) goto L27;
            int r172 = 5;
        L28:
            if (r2 == false) goto L30;
            float r182 = 27.0f;
        L31:
            if (r2 == false) goto L33;
            float r202 = 68.0f;
        L34:
            addView(r33, LayoutHelper.createFrame(-2, -2.0f, r172, r182, 18.0f, r202, 0.0f));
            return;
        L33:
            r202 = 27.0f;
            goto L34
        L30:
            r182 = 68.0f;
            goto L31
        L27:
            r172 = 3;
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

    /* renamed from: $r8$lambda$0qOK9iP2AFyUZN745WC-rUA2DSM, reason: not valid java name */
    public static /* synthetic */ void m18501$r8$lambda$0qOK9iP2AFyUZN745WCrUA2DSM(RevenueSharingAdsInfoBottomSheet r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$2vayCJt3Orb_6tYQcD_gValmHCk(RevenueSharingAdsInfoBottomSheet r0, Utilities.Callback r1, Theme.ResourcesProvider r2, ImageView r3, View r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$fzPksb2H_U-e4Jjb8dHSCDvgO-8, reason: not valid java name */
    public static /* synthetic */ void m18502$r8$lambda$fzPksb2H_Ue4Jjb8dHSCDvgO8(RevenueSharingAdsInfoBottomSheet r0, View r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$lFpfEVbN8ZA6rgia9jDv8vnxGLw(RevenueSharingAdsInfoBottomSheet r0) {
        r0.lambda$new$2();
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public RevenueSharingAdsInfoBottomSheet(Context r26, boolean r27, Theme.ResourcesProvider r28, Utilities.Callback<ItemOptions> r29) {
        super(r26, null, false, false, false, r28);
        fixNavigationBar();
        this.topPadding = 0.2f;
        Paint r0 = new Paint(1);
        this.topIconBgPaint = r0;
        r0.setStyle(Paint.Style.FILL);
        int r9 = Theme.key_featuredStickers_addButton;
        r0.setColor(Theme.getColor(r9, r28));
        LinearLayout r10 = new LinearLayout(r26);
        this.customView = r10;
        r10.setPadding(this.backgroundPaddingLeft + AndroidUtilities.dp(6.0f), 0, this.backgroundPaddingLeft + AndroidUtilities.dp(6.0f), 0);
        r10.setOrientation(1);
        FrameLayout r02 = new FrameLayout(r26);
        RLottieImageView r3 = new RLottieImageView(getContext());
        ImageView.ScaleType r4 = ImageView.ScaleType.CENTER;
        r3.setScaleType(r4);
        r3.setImageResource(R.drawable.large_ads_info);
        r3.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        r3.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(r9, r28)));
        r02.addView(r3, LayoutHelper.createFrame(80, 80.0f, 1, 0.0f, 20.0f, 0.0f, 0.0f));
        if (r29 == null) goto L5;
        ImageView r32 = new ImageView(r26);
        r32.setImageDrawable(ContextCompat.getDrawable(r26, R.drawable.ic_ab_other));
        r32.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        r32.setScaleType(r4);
        r32.setColorFilter(Theme.getColor(Theme.key_dialogTextGray3));
        r32.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 1));
        r32.setOnClickListener(new RevenueSharingAdsInfoBottomSheet$$ExternalSyntheticLambda0(this, r29, r28, r32));
        r02.addView(r32, LayoutHelper.createFrame(24, 24.0f, 53, 12.0f, 14.0f, 14.0f, 12.0f));
    L5:
        r10.addView(r02, LayoutHelper.createLinear(-1, 100, 0.0f, 0.0f, 0.0f, 0.0f));
        TextView r03 = new TextView(r26);
        r03.setText(LocaleController.getString(R.string.AboutRevenueSharingAds));
        r03.setTypeface(AndroidUtilities.bold());
        int r7 = Theme.key_windowBackgroundWhiteBlackText;
        r03.setTextColor(Theme.getColor(r7, r28));
        r03.setTextSize(1, 20.0f);
        r03.setGravity(1);
        r10.addView(r03, LayoutHelper.createLinear(-2, -2, 1, 22, 14, 22, 0));
        TextView r04 = new TextView(r26);
        r04.setText(LocaleController.getString(R.string.RevenueSharingAdsAlertSubtitle));
        r04.setTextColor(Theme.getColor(r7, r28));
        r04.setTextSize(1, 14.0f);
        r04.setGravity(1);
        r10.addView(r04, LayoutHelper.createLinear(-2, -2, 1, 22, 8, 22, 0));
        int r33 = R.drawable.menu_privacy;
        if (r27 == false) goto L8;
        int r42 = R.string.RevenueSharingAdsInfo1TitleBot;
    L9:
        String r43 = LocaleController.getString(r42);
        if (r27 == false) goto L12;
        int r5 = R.string.RevenueSharingAdsInfo1SubtitleBot;
    L13:
        r10.addView(new FeatureCell(this, r26, r33, r43, LocaleController.getString(r5)), LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 20, 0, 0));
        int r34 = R.drawable.menu_feature_split;
        if (r27 == false) goto L16;
        int r1 = R.string.RevenueSharingAdsInfo2TitleBot;
    L17:
        String r44 = LocaleController.getString(r1);
        if (r27 == false) goto L20;
        int r12 = R.string.RevenueSharingAdsInfo2SubtitleBot;
    L21:
        r10.addView(new FeatureCell(this, r26, r34, r44, LocaleController.getString(r12)), LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 16, 0, 0));
        if (r27 == false) goto L24;
        int r05 = R.string.RevenueSharingAdsInfo3SubtitleBot;
    L25:
        String r06 = LocaleController.formatString(r05, new Object[]{Integer.valueOf(MessagesController.getInstance(UserConfig.selectedAccount).channelRestrictSponsoredLevelMin)});
        int r2 = Theme.key_chat_messageLinkIn;
        r10.addView(new FeatureCell(this, r26, R.drawable.menu_feature_noads, LocaleController.getString(R.string.RevenueSharingAdsInfo3Title), AndroidUtilities.replaceSingleTag(r06, r2, 0, new RevenueSharingAdsInfoBottomSheet$$ExternalSyntheticLambda1(this))), LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 16, 0, 0));
        View r07 = new View(getContext());
        r07.setBackgroundColor(Theme.getColor(Theme.key_divider, r28));
        LinearLayout.LayoutParams r35 = new LinearLayout.LayoutParams(-1, 1);
        r35.setMargins(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(20.0f));
        r10.addView(r07, r35);
        TextView r08 = new TextView(r26);
        if (r27 == false) goto L28;
        int r36 = R.string.RevenueSharingAdsInfo4TitleBot;
    L29:
        r08.setText(LocaleController.getString(r36));
        r08.setTypeface(AndroidUtilities.bold());
        r08.setTextColor(Theme.getColor(r7, r28));
        r08.setTextSize(1, 20.0f);
        r08.setTextAlignment(4);
        r08.setGravity(17);
        r10.addView(r08, LayoutHelper.createLinear(-2, -2, 1, 22, 0, 22, 0));
        if (r27 == false) goto L32;
        int r09 = R.string.RevenueSharingAdsInfo4Subtitle2Bot;
    L33:
        SpannableStringBuilder r010 = AndroidUtilities.replaceTags(LocaleController.getString(r09));
        SpannableStringBuilder r45 = AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.RevenueSharingAdsInfo4SubtitleLearnMore), r2, 0, new RevenueSharingAdsInfoBottomSheet$$ExternalSyntheticLambda2(this));
        SpannableString r52 = new SpannableString(">");
        ColoredImageSpan r14 = new ColoredImageSpan(R.drawable.attach_arrow_right);
        r14.setOverrideColor(Theme.getColor(r2));
        r14.setScale(0.7f, 0.7f);
        r14.setWidth(AndroidUtilities.dp(12.0f));
        r14.setTranslateY(1.0f);
        r52.setSpan(r14, 0, r52.length(), 33);
        SpannableStringBuilder r011 = AndroidUtilities.replaceCharSequence(">", AndroidUtilities.replaceCharSequence("%1$s", r010, r45), r52);
        LinkSpanDrawable.LinksTextView r37 = new LinkSpanDrawable.LinksTextView(r26);
        r37.setText(r011);
        r37.setTextColor(Theme.getColor(r7, r28));
        r37.setTextSize(1, 14.0f);
        r37.setGravity(1);
        r37.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r10.addView(r37, LayoutHelper.createLinear(-2, -2, 1, 26, 8, 26, 0));
        TextView r012 = new TextView(r26);
        r012.setLines(1);
        r012.setSingleLine(true);
        r012.setGravity(17);
        r012.setEllipsize(TextUtils.TruncateAt.END);
        r012.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r28));
        r012.setTypeface(AndroidUtilities.bold());
        r012.setTextSize(1, 14.0f);
        r012.setText(LocaleController.getString(R.string.RevenueSharingAdsAlertButton));
        r012.setBackground(Theme.AdaptiveRipple.filledRect(Theme.getColor(r9, r28), new float[]{6.0f}));
        r012.setOnClickListener(new RevenueSharingAdsInfoBottomSheet$$ExternalSyntheticLambda3(this));
        r10.addView(r012, LayoutHelper.createLinear(-1, 48, 0, 14, 22, 14, 14));
        this.adapter.update(false);
        return;
    L32:
        r09 = R.string.RevenueSharingAdsInfo4Subtitle2;
        goto L33
    L28:
        r36 = R.string.RevenueSharingAdsInfo4Title;
        goto L29
    L24:
        r05 = R.string.RevenueSharingAdsInfo3Subtitle;
        goto L25
    L20:
        r12 = R.string.RevenueSharingAdsInfo2Subtitle;
        goto L21
    L16:
        r1 = R.string.RevenueSharingAdsInfo2Title;
        goto L17
    L12:
        r5 = R.string.RevenueSharingAdsInfo1Subtitle;
        goto L13
    L8:
        r42 = R.string.RevenueSharingAdsInfo1Title;
        goto L9
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(RevenueSharingAdsInfoBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(RevenueSharingAdsInfoBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(RevenueSharingAdsInfoBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(RevenueSharingAdsInfoBottomSheet r0) {
        return r0.resourcesProvider;
    }

    private /* synthetic */ void lambda$new$0(Utilities.Callback r1, Theme.ResourcesProvider r2, ImageView r3, View r4) {
        r1.run(ItemOptions.makeOptions(this.container, r2, r3, true).setGravity(5).setDrawScrim(false).translate(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(-32.0f)));
    }

    private /* synthetic */ void lambda$new$1() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        r0.presentFragment(new PremiumPreviewFragment(PremiumPreviewFragment.featureTypeToServerString(3)));
        dismiss();
    }

    private /* synthetic */ void lambda$new$2() {
        dismiss();
        Browser.openUrl(getContext(), LocaleController.getString(R.string.PromoteUrl));
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        dismiss();
    }

    public static RevenueSharingAdsInfoBottomSheet showAlert(Context r1, BaseFragment r2, boolean r3, Theme.ResourcesProvider r4) {
        return showAlert(r1, r2, r3, r4, null);
    }

    public static RevenueSharingAdsInfoBottomSheet showAlert(Context r1, BaseFragment r2, boolean r3, Theme.ResourcesProvider r4, Utilities.Callback<ItemOptions> r5) {
        RevenueSharingAdsInfoBottomSheet r0 = new RevenueSharingAdsInfoBottomSheet(r1, r3, r4, r5);
        if (r2 != null) goto L5;
        r0.show();
        return r0;
    L5:
        if (r2.getParentActivity() == null) goto L7;
        r2.showDialog(r0);
    L7:
        return r0;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new RevenueSharingAdsInfoBottomSheet$$ExternalSyntheticLambda4(this), this.resourcesProvider);
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
