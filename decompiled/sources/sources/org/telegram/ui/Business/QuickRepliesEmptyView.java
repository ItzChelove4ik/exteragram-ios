package org.telegram.ui.Business;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes6.dex */
public class QuickRepliesEmptyView extends LinearLayout {
    private TextView descriptionView;
    private TextView descriptionView2;
    public RLottieImageView imageView;
    private final Theme.ResourcesProvider resourcesProvider;
    private TextView titleView;

    public class DotTextView extends TextView {
        final /* synthetic */ QuickRepliesEmptyView this$0;

        public DotTextView(QuickRepliesEmptyView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r5) {
            if (getPaddingLeft() <= 0) goto L5;
            r5.drawCircle((getPaddingLeft() - AndroidUtilities.dp(2.5f)) / 2.0f, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(2.5f), getPaint());
        L5:
            super.dispatchDraw(r5);
        }
    }

    public QuickRepliesEmptyView(Context r11, int r12, long r13, long r15, String r17, Theme.ResourcesProvider r18) {
        super(r11);
        setOrientation(1);
        this.resourcesProvider = r18;
        TextView r0 = new TextView(r11);
        this.titleView = r0;
        r0.setTextSize(1, 14.0f);
        this.titleView.setTypeface(AndroidUtilities.bold());
        this.titleView.setTextAlignment(4);
        this.titleView.setLineSpacing(AndroidUtilities.dp(1.66f), 1.0f);
        this.titleView.setGravity(17);
        DotTextView r02 = new DotTextView(this, r11);
        this.descriptionView = r02;
        r02.setTextAlignment(4);
        this.descriptionView.setGravity(17);
        this.descriptionView.setTextSize(1, 12.0f);
        this.descriptionView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        this.descriptionView.setGravity(1);
        RLottieImageView r03 = new RLottieImageView(r11);
        this.imageView = r03;
        r03.setScaleType(ImageView.ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        this.descriptionView.setMaxWidth(AndroidUtilities.dp(160.0f));
        int r04 = 22;
        int r2 = 9;
        if (r12 != 9) goto L7;
        this.imageView.setImageResource(R.drawable.large_greeting);
        this.titleView.setText(LocaleController.getString(R.string.WelcomeMessageEmptyTitle));
        this.descriptionView.setText(LocaleController.getString(R.string.WelcomeMessageEmptySubtitle));
        this.descriptionView.setMaxWidth(Math.min(AndroidUtilities.dp(160.0f), HintView2.cutInFancyHalf(this.descriptionView.getText(), this.descriptionView.getPaint())));
    L5:
        int r14 = r04;
        addView(this.imageView, LayoutHelper.createLinear(78, 78, 49, 20, 17, 20, 9));
        addView(this.titleView, LayoutHelper.createLinear(-2, -2, 49, 20, 0, 20, 6));
        TextView r05 = this.descriptionView;
        if (this.descriptionView2 != null) goto L24;
        r2 = 19;
    L24:
        addView(r05, LayoutHelper.createLinear(-2, -2, 49, r14, 0, r14, r2));
        TextView r112 = this.descriptionView2;
        if (r112 == null) goto L27;
        addView(r112, LayoutHelper.createLinear(-2, -2, 49, 12, 0, 12, 19));
    L27:
        updateColors();
        return;
    L7:
        if ("hello".equalsIgnoreCase(r17) == false) goto L10;
        this.imageView.setImageResource(R.drawable.large_greeting);
        this.titleView.setText(LocaleController.getString(R.string.BusinessGreetingIntroTitle));
        this.descriptionView.setText(LocaleController.getString(R.string.BusinessGreetingIntro));
        this.descriptionView.setMaxWidth(Math.min(AndroidUtilities.dp(160.0f), HintView2.cutInFancyHalf(this.descriptionView.getText(), this.descriptionView.getPaint())));
        goto L5
    L10:
        if ("away".equalsIgnoreCase(r17) == false) goto L13;
        this.imageView.setImageResource(R.drawable.large_away);
        this.titleView.setText(LocaleController.getString(R.string.BusinessAwayIntroTitle));
        this.descriptionView.setText(LocaleController.getString(R.string.BusinessAwayIntro));
        this.descriptionView.setMaxWidth(Math.min(AndroidUtilities.dp(160.0f), HintView2.cutInFancyHalf(this.descriptionView.getText(), this.descriptionView.getPaint())));
        goto L5
    L13:
        if (r12 != 5) goto L19;
        this.imageView.setImageResource(R.drawable.large_quickreplies);
        QuickRepliesController.QuickReply r122 = QuickRepliesController.getInstance(UserConfig.selectedAccount).findReply(r15);
        if (r122 != null) goto L17;
        String r123 = r17;
    L18:
        this.titleView.setText(LocaleController.getString(R.string.BusinessRepliesIntroTitle));
        this.descriptionView.setMaxWidth(AndroidUtilities.dp(208.0f));
        this.descriptionView.setTextAlignment(2);
        this.descriptionView.setGravity(3);
        this.descriptionView.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BusinessRepliesIntro1, new Object[]{r123})));
        this.descriptionView.setPadding(AndroidUtilities.dp(28.0f), 0, 0, 0);
        DotTextView r124 = new DotTextView(this, r11);
        this.descriptionView2 = r124;
        r124.setMaxWidth(AndroidUtilities.dp(208.0f));
        this.descriptionView2.setTextAlignment(2);
        this.descriptionView2.setGravity(3);
        this.descriptionView2.setTextSize(1, 13.0f);
        this.descriptionView2.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.BusinessRepliesIntro2)));
        this.descriptionView2.setPadding(AndroidUtilities.dp(28.0f), 0, 0, 0);
        goto L19
    L17:
        r123 = r122.name;
    L19:
        r04 = 12;
        goto L5
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void updateColors() {
        TextView r0 = this.titleView;
        int r1 = Theme.key_chat_serviceText;
        r0.setTextColor(getThemedColor(r1));
        this.descriptionView.setTextColor(getThemedColor(r1));
        TextView r02 = this.descriptionView2;
        if (r02 == null) goto L6;
        r02.setTextColor(getThemedColor(r1));
        return;
    }
}
