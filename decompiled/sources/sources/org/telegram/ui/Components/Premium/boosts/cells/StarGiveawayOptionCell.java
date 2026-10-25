package org.telegram.ui.Components.Premium.boosts.cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingSpan;
import org.telegram.ui.Components.RadioButton;

/* loaded from: classes7.dex */
public class StarGiveawayOptionCell extends FrameLayout {
    private final AnimatedFloat animatedStarsCount;
    private TL_stars.TL_starsGiveawayOption currentOption;
    private long currentOptionStarsPerUser;
    private SpannableString loading1;
    private SpannableString loading2;
    private TextView priceView;
    private RadioButton radioButton;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Drawable starDrawable;
    private final Drawable starDrawableOutline;
    private int starsCount;
    private AnimatedTextView subtitleView;
    private AnimatedTextView titleView;

    public StarGiveawayOptionCell(Context r18, Theme.ResourcesProvider r19) {
        super(r18);
        this.animatedStarsCount = new AnimatedFloat(this, 0, 500, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.resourcesProvider = r19;
        Drawable r0 = r18.getResources().getDrawable(R.drawable.star_small_outline).mutate();
        this.starDrawableOutline = r0;
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackground, r19), PorterDuff.Mode.SRC_IN));
        this.starDrawable = r18.getResources().getDrawable(R.drawable.star_small_inner).mutate();
        setWillNotDraw(false);
        AnimatedTextView r2 = new AnimatedTextView(r18);
        this.titleView = r2;
        r2.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r19));
        this.titleView.setTypeface(AndroidUtilities.bold());
        this.titleView.setTextSize(AndroidUtilities.dp(16.0f));
        addView(this.titleView, LayoutHelper.createFrame(-1, 20.0f, 51, 64.0f, 8.0f, 80.0f, 0.0f));
        SpannableString r22 = new SpannableString("x");
        this.loading1 = r22;
        r22.setSpan(new LoadingSpan(this.titleView, AndroidUtilities.dp(90.0f)), 0, 1, 33);
        AnimatedTextView r23 = new AnimatedTextView(r18, false, true, true);
        this.subtitleView = r23;
        int r5 = Theme.key_windowBackgroundWhiteGrayText2;
        r23.setTextColor(Theme.getColor(r5, r19));
        this.subtitleView.setTextSize(AndroidUtilities.dp(13.0f));
        addView(this.subtitleView, LayoutHelper.createFrame(-1, 14.0f, 51, 64.0f, 31.0f, 80.0f, 0.0f));
        SpannableString r24 = new SpannableString("x");
        this.loading2 = r24;
        r24.setSpan(new LoadingSpan(this.subtitleView, AndroidUtilities.dp(70.0f)), 0, 1, 33);
        TextView r02 = new TextView(r18);
        this.priceView = r02;
        r02.setTextColor(Theme.getColor(r5, r19));
        this.priceView.setTextSize(1, 16.0f);
        this.priceView.setGravity(5);
        addView(this.priceView, LayoutHelper.createFrame(-2, -2.0f, 21, 0.0f, 0.0f, 19.0f, 0.0f));
        RadioButton r03 = new RadioButton(r18);
        this.radioButton = r03;
        r03.setSize(AndroidUtilities.dp(20.0f));
        this.radioButton.setColor(Theme.getColor(Theme.key_checkboxDisabled, r19), Theme.getColor(Theme.key_dialogRadioBackgroundChecked, r19));
        addView(this.radioButton, LayoutHelper.createFrame(20, 20.0f, 19, 22.0f, 0.0f, 0.0f, 0.0f));
    }

    public TL_stars.TL_starsGiveawayOption getOption() {
        return this.currentOption;
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        super.onDraw(r15);
        float r0 = this.animatedStarsCount.set(this.starsCount);
        float r2 = AndroidUtilities.dp(24.0f);
        float r1 = AndroidUtilities.dp(24.0f);
        float r3 = AndroidUtilities.dp(2.5f);
        float r4 = AndroidUtilities.dp(64.0f);
        float r5 = AndroidUtilities.dp(8.0f);
        int r6 = ((int) Math.ceil(r0)) - 1;
    L3:
        if (r6 < 0) goto L5;
        float r7 = Utilities.clamp(r0 - r6, 1.0f, 0.0f);
        float r8 = ((((r6 - 1) - (1.0f - r7)) * r3) * 1.0f) + r4;
        int r10 = (int) r8;
        int r11 = (int) r5;
        int r82 = (int) (r8 + r2);
        int r12 = (int) (r5 + r1);
        this.starDrawableOutline.setBounds(r10, r11, r82, r12);
        int r72 = (int) (r7 * 255.0f);
        this.starDrawableOutline.setAlpha(r72);
        this.starDrawableOutline.draw(r15);
        this.starDrawable.setBounds(r10, r11, r82, r12);
        this.starDrawable.setAlpha(r72);
        this.starDrawable.draw(r15);
        r6 = r6 - 1;
        goto L3
    L5:
        this.titleView.setTranslationX(AndroidUtilities.dp(22.0f) + (r3 * r0));
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
    }

    public void setOption(TL_stars.TL_starsGiveawayOption r6, int r7, long r8, boolean r10, boolean r11) {
        if (this.currentOption != r6) goto L5;
        boolean r112 = true;
    L6:
        this.radioButton.setChecked(r10, r112);
        this.currentOption = r6;
        this.currentOptionStarsPerUser = r8;
        if (r112 == false) goto L9;
        this.subtitleView.cancelAnimation();
    L9:
        AnimatedTextView r102 = this.titleView;
        if (r6 != null) goto L12;
        r102.setText(this.loading1, false);
        this.subtitleView.setText(this.loading2, r112);
        this.priceView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L13:
        int r72 = r7 + 1;
        this.starsCount = r72;
        if (r112 == true) goto L16;
        this.animatedStarsCount.set(r72, true);
    L16:
        invalidate();
        return;
    L12:
        r102.setText(LocaleController.formatPluralStringComma("GiveawayStars", (int) r6.stars, ' '), false);
        this.subtitleView.setText(LocaleController.formatPluralStringComma("BoostingStarOptionPerUser", (int) r8, ','), r112);
        this.priceView.setText(BillingController.getInstance().formatCurrency(r6.amount, r6.currency));
        goto L13
    L5:
        r112 = false;
        goto L6
    }
}
