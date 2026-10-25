package org.telegram.ui.Cells;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import okhttp3.internal.url._UrlKt;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.URLSpanNoUnderline;

/* loaded from: classes6.dex */
public abstract class SettingsSuggestionCell extends LinearLayout {
    private int currentAccount;
    private int currentType;
    private TextView detailTextView;
    private TextView noButton;
    private Theme.ResourcesProvider resourcesProvider;
    private TextView textView;
    private TextView yesButton;

    public static /* synthetic */ void $r8$lambda$XCIo2sWZk2J3UHzGLPWhZEETQkE(SettingsSuggestionCell r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$adE_ae-zXy7UNlzCDETTiU0qvck, reason: not valid java name */
    public static /* synthetic */ void m7007$r8$lambda$adE_aezXy7UNlzCDETTiU0qvck(SettingsSuggestionCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public SettingsSuggestionCell(Context r18, Theme.ResourcesProvider r19) {
        super(r18);
        this.currentAccount = UserConfig.selectedAccount;
        this.resourcesProvider = r19;
        setOrientation(1);
        TextView r4 = new TextView(r18);
        this.textView = r4;
        r4.setTextSize(1, 15.0f);
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        TextView r42 = this.textView;
        if (LocaleController.isRTL == false) goto L5;
        int r5 = 5;
    L6:
        r42.setGravity(r5 | 16);
        this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader, r19));
        View r43 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        int r52 = 5;
    L10:
        addView(r43, LayoutHelper.createLinear(-1, -2, r52 | 48, 21, 15, 21, 0));
        LinkSpanDrawable.LinksTextView r44 = new LinkSpanDrawable.LinksTextView(r18, r19);
        this.detailTextView = r44;
        r44.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r19));
        this.detailTextView.setTextSize(1, 14.0f);
        this.detailTextView.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, r19));
        this.detailTextView.setHighlightColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkSelection, r19));
        this.detailTextView.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        TextView r45 = this.detailTextView;
        if (LocaleController.isRTL == false) goto L13;
        int r8 = 5;
    L14:
        r45.setGravity(r8);
        View r46 = this.detailTextView;
        if (LocaleController.isRTL == false) goto L17;
        int r11 = 5;
    L18:
        addView(r46, LayoutHelper.createLinear(-2, -2, r11, 21, 14, 21, 0));
        LinearLayout r47 = new LinearLayout(r18);
        r47.setOrientation(0);
        addView(r47, LayoutHelper.createLinear(-1, 44, 21.0f, 16.0f, 21.0f, 15.0f));
        int r7 = 0;
    L20:
        if (r7 >= 2) goto L33;
        TextView r82 = new TextView(r18);
        r82.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{8.0f}));
        ScaleStateListAnimator.apply(r82, 0.02f, 1.5f);
        r82.setLines(1);
        r82.setSingleLine(true);
        r82.setGravity(1);
        r82.setEllipsize(TextUtils.TruncateAt.END);
        r82.setGravity(17);
        r82.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r19));
        r82.setTextSize(1, 14.0f);
        r82.setTypeface(AndroidUtilities.bold());
        if (r7 != 0) goto L24;
        int r13 = 0;
    L25:
        if (r7 != 0) goto L27;
        int r15 = 4;
    L28:
        r47.addView(r82, LayoutHelper.createLinear(0, 44, 0.5f, r13, 0, r15, 0));
        if (r7 != 0) goto L31;
        this.yesButton = r82;
        r82.setOnClickListener(new SettingsSuggestionCell$$ExternalSyntheticLambda0(this));
    L32:
        r7 = r7 + 1;
        goto L20
    L31:
        this.noButton = r82;
        r82.setOnClickListener(new SettingsSuggestionCell$$ExternalSyntheticLambda1(this));
        goto L32
    L27:
        r15 = 0;
        goto L28
    L24:
        r13 = 4;
        goto L25
    L33:
        return;
    L17:
        r11 = 3;
        goto L18
    L13:
        r8 = 3;
        goto L14
    L9:
        r52 = 3;
        goto L10
    L5:
        r5 = 3;
        goto L6
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onYesClick(this.currentType);
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        onNoClick(this.currentType);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
    }

    public abstract void onNoClick(int r1);

    public abstract void onYesClick(int r1);

    public void setType(int r8) {
        this.currentType = r8;
        if (r8 != 0) goto L16;
        TLRPC.User r82 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(UserConfig.getInstance(this.currentAccount).clientUserId));
        this.textView.setText(LocaleController.formatString(R.string.CheckPhoneNumber, new Object[]{PhoneFormat.getInstance().format("+" + r82.phone)}));
        String r83 = LocaleController.getString(R.string.CheckPhoneNumberInfo);
        SpannableStringBuilder r2 = new SpannableStringBuilder(r83);
        int r4 = r83.indexOf("**");
        int r84 = r83.lastIndexOf("**");
        if (r4 < 0) goto L13;
        if (r84 < 0) goto L13;
        if (r4 == r84) goto L13;
        r2.replace(r84, r84 + 2, _UrlKt.FRAGMENT_ENCODE_SET);
        r2.replace(r4, r4 + 2, _UrlKt.FRAGMENT_ENCODE_SET);
        r2.setSpan(new URLSpanNoUnderline(LocaleController.getString(R.string.CheckPhoneNumberLearnMoreUrl)), r4, r84 - 2, 33);     // Catch: Exception -> L11
    L11:
        e = move-exception;
        FileLog.e(e);
    L13:
        this.detailTextView.setText(r2);
        this.yesButton.setText(LocaleController.getString(R.string.CheckPhoneNumberYes));
        this.noButton.setVisibility(0);
        this.noButton.setText(LocaleController.getString(R.string.CheckPhoneNumberNo));
        return;
    L16:
        if (r8 != 1) goto L19;
        this.textView.setText(LocaleController.getString(R.string.YourPasswordHeader));
        this.detailTextView.setText(LocaleController.getString(R.string.YourPasswordRemember));
        this.yesButton.setText(LocaleController.getString(R.string.YourPasswordRememberYes));
        this.noButton.setVisibility(0);
        this.noButton.setText(LocaleController.getString(R.string.YourPasswordRememberNo));
        return;
    L19:
        if (r8 != 2) goto L24;
        this.textView.setText(LocaleController.getString(R.string.GraceSuggestionTitle));
        this.detailTextView.setText(LocaleController.getString(R.string.GraceSuggestionMessage));
        this.yesButton.setText(LocaleController.getString(R.string.GraceSuggestionButton));
        this.noButton.setVisibility(8);
        return;
    }
}
