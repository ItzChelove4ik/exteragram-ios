package com.exteragram.messenger.components;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.pillstack.ui.pills.crypto.utils.ExchangeRates;
import com.exteragram.messenger.preferences.OtherPreferencesActivity;
import com.exteragram.messenger.utils.network.RemoteUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes4.dex */
public class SupporterBottomSheet extends BottomSheet {

    public class FeatureCell extends FrameLayout {
        final /* synthetic */ SupporterBottomSheet this$0;
        private final LinkSpanDrawable.LinksTextView tvSubtitle;

        public FeatureCell(SupporterBottomSheet r23, Context r24, int r25, CharSequence r26, CharSequence r27) {
            this.this$0 = r23;
            super(r24);
            boolean r2 = LocaleController.isRTL;
            ImageView r3 = new ImageView(getContext());
            Drawable r4 = ContextCompat.getDrawable(getContext(), r25).mutate();
            int r6 = Theme.key_windowBackgroundWhiteBlackText;
            r4.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r6, SupporterBottomSheet.access$000(r23)), PorterDuff.Mode.MULTIPLY));
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
            r32.setTextColor(Theme.getColor(r6, SupporterBottomSheet.access$100(r23)));
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
            this.tvSubtitle = r33;
            r33.setText(r27);
            r33.setTextSize(1, 14.0f);
            r33.setTextColor(Theme.getColor(Theme.key_dialogTextGray3, SupporterBottomSheet.access$200(r23)));
            r33.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, SupporterBottomSheet.access$300(r23)));
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
            addView(r33, LayoutHelper.createFrame(-2, -2.0f, r172, r182, 20.0f, r202, 0.0f));
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

        public void setSubtitle(CharSequence r1) {
            this.tvSubtitle.setText(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$1Es0Vbd0x8ScgNp7h16IbX_jSl8(SupporterBottomSheet r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$25sbCaF2XQ7-KGr0pq9Ml8yDrZA, reason: not valid java name */
    public static /* synthetic */ void m1231$r8$lambda$25sbCaF2XQ7KGr0pq9Ml8yDrZA(SupporterBottomSheet r0, BaseFragment r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$BDeKMIHChXU4nflyukFGpgGSFoc(SupporterBottomSheet r0, float r1, String r2, BaseFragment r3, FeatureCell r4, ExchangeRates.State r5) {
        r0.lambda$new$1(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$CoY2GmIF5zkyec6rlGa4lcOlD2c(SupporterBottomSheet r0, BaseFragment r1) {
        r0.lambda$new$0(r1);
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public SupporterBottomSheet(BaseFragment r21, Theme.ResourcesProvider r22) {
        super(r21.getParentActivity(), false, r22);
        Activity r2 = r21.getParentActivity();
        fixNavigationBar();
        LinearLayout r7 = new LinearLayout(r2);
        r7.setOrientation(1);
        RLottieImageView r3 = new RLottieImageView(getContext());
        r3.setScaleType(ImageView.ScaleType.CENTER);
        r3.setImageResource(R.drawable.extera_heart_large);
        r3.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        r3.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), ContextCompat.getColor(r2, R.color.ic_background)));
        r7.addView(r3, LayoutHelper.createLinear(80, 80, 1, 0, 28, 0, 0));
        TextView r32 = new TextView(r2);
        r32.setText(LocaleController.getString(R.string.SupportDevelopment));
        r32.setTypeface(AndroidUtilities.bold());
        int r4 = Theme.key_windowBackgroundWhiteBlackText;
        r32.setTextColor(Theme.getColor(r4, r22));
        r32.setTextSize(1, 20.0f);
        r32.setGravity(1);
        r7.addView(r32, LayoutHelper.createLinear(-2, -2, 1, 22, 14, 22, 0));
        TextView r33 = new TextView(r2);
        r33.setText(LocaleController.getString(R.string.SupportDevelopmentInfo));
        r33.setTextColor(Theme.getColor(r4, r22));
        r33.setTextSize(1, 14.0f);
        r33.setGravity(1);
        r7.addView(r33, LayoutHelper.createLinear(-2, -2, 1, 22, 8, 22, 0));
        float r9 = RemoteUtils.getFloatConfigValue("donates_amount_usd", 5.0f).floatValue();
        FeatureCell r0 = new FeatureCell(this, r2, R.drawable.menu_feature_paid, LocaleController.getString(R.string.MakeDonation), _UrlKt.FRAGMENT_ENCODE_SET);
        r7.addView(r0, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 20, 0, 0));
        SupporterBottomSheet$$ExternalSyntheticLambda0 r02 = new SupporterBottomSheet$$ExternalSyntheticLambda0(this, r9, "$" + r9, r21, r0);
        r02.run(ExchangeRates.getCached());
        ExchangeRates.fetch(r02);
        r7.addView(new FeatureCell(this, r2, R.drawable.menu_feature_wallpaper, LocaleController.getString(R.string.SendProof), AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.SendProofInfo), Theme.key_chat_messageLinkIn, 0, new SupporterBottomSheet$$ExternalSyntheticLambda1(this, r21))), LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 16, 0, 0));
        r7.addView(new FeatureCell(this, r2, R.drawable.menu_feature_reactions, LocaleController.getString(R.string.ReceiveBadge), LocaleController.getString(R.string.ReceiveBadgeInfo)), LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 16, 0, 0));
        ButtonWithCounterView r03 = new ButtonWithCounterView(r2, true, null);
        r03.setRound();
        r03.setText(LocaleController.getString(R.string.Close), false);
        r03.setOnClickListener(new SupporterBottomSheet$$ExternalSyntheticLambda2(this));
        r7.addView(r03, LayoutHelper.createLinear(-1, 48, 0, 14, 22, 14, 14));
        ScrollView r04 = new ScrollView(getContext());
        r04.addView(r7);
        setCustomView(r04);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(SupporterBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(SupporterBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(SupporterBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(SupporterBottomSheet r0) {
        return r0.resourcesProvider;
    }

    private /* synthetic */ void lambda$new$0(BaseFragment r1) {
        dismiss();
        r1.presentFragment(new OtherPreferencesActivity());
    }

    private /* synthetic */ void lambda$new$1(float r10, String r11, BaseFragment r12, FeatureCell r13, ExchangeRates.State r14) {
        if (r14 == null) goto L5;
        double r2 = r14.formatDonate("TON", 1.0d);
    L6:
        if (r14 == null) goto L8;
        double r0 = r14.formatDonate("RUB", 100.0d);
    L9:
        String r102 = "TON " + ExchangeRates.State.formatter.format(r2);
        String r142 = Math.round(r0) + "₽";
        SpannableStringBuilder r103 = AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.MakeDonationInfo, new Object[]{r11, r102 + ", " + r142}), Theme.key_chat_messageLinkIn, 0, new SupporterBottomSheet$$ExternalSyntheticLambda3(this, r12));
        SpannableString r112 = new SpannableString("TON");
        ColoredImageSpan r122 = new ColoredImageSpan(R.drawable.mini_gram_16);
        r122.setWidth(AndroidUtilities.dp(13.0f));
        r112.setSpan(r122, 0, r112.length(), 33);
        r13.setSubtitle(AndroidUtilities.replaceCharSequence("TON", r103, r112));
        return;
    L8:
        r0 = 100.0d * r10;
        goto L9
    L5:
        r2 = (r10 * 1.0d) * ((RemoteUtils.getIntConfigValue("donates_ton_markup_percent", 10).intValue() / 100.0d) + 1.0d);
        goto L6
    }

    private /* synthetic */ void lambda$new$2(BaseFragment r3) {
        dismiss();
        MessagesController.getInstance(this.currentAccount).openByUserName("exteraOwner", r3, 1);
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        dismiss();
    }

    public static SupporterBottomSheet showAlert(BaseFragment r1) {
        return showAlert(r1, r1.getResourceProvider());
    }

    public static SupporterBottomSheet showAlert(BaseFragment r1, Theme.ResourcesProvider r2) {
        SupporterBottomSheet r0 = new SupporterBottomSheet(r1, r2);
        if (r1.getParentActivity() == null) goto L5;
        r1.showDialog(r0);
    L5:
        return r0;
    }
}
