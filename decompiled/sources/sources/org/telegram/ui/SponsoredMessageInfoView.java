package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes6.dex */
public class SponsoredMessageInfoView extends FrameLayout {

    /* renamed from: org.telegram.ui.SponsoredMessageInfoView$1, reason: invalid class name */
    public class AnonymousClass1 extends TextView {
        final /* synthetic */ SponsoredMessageInfoView this$0;
        final /* synthetic */ Paint val$buttonPaint;

        public AnonymousClass1(SponsoredMessageInfoView r1, Context r2, Paint r3) {
            this.this$0 = r1;
            this.val$buttonPaint = r3;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), getMeasuredWidth() - AndroidUtilities.dp(1.0f), getMeasuredHeight() - AndroidUtilities.dp(1.0f));
            r7.drawRoundRect(r0, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.val$buttonPaint);
        }
    }

    /* renamed from: org.telegram.ui.SponsoredMessageInfoView$2, reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ SponsoredMessageInfoView this$0;
        final /* synthetic */ Runnable val$close;
        final /* synthetic */ Context val$context;

        public AnonymousClass2(SponsoredMessageInfoView r1, Runnable r2, Context r3) {
            this.this$0 = r1;
            this.val$close = r2;
            this.val$context = r3;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            Runnable r12 = this.val$close;
            if (r12 == null) goto L5;
            r12.run();
        L5:
            Browser.openUrl(this.val$context, LocaleController.getString(R.string.SponsoredMessageAlertLearnMoreUrl));
        }
    }

    /* renamed from: $r8$lambda$-lcwGVbeK2rFQO31j5nq6zopPzA, reason: not valid java name */
    public static /* synthetic */ void m18855$r8$lambda$lcwGVbeK2rFQO31j5nq6zopPzA(SponsoredMessageInfoView r0, Runnable r1, ClickableSpan r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GN2yZzjPPoFU4h4rXIGXvCZK7oI(SponsoredMessageInfoView r0, Runnable r1, ClickableSpan r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZXvfBmKafP_l_dmWKHMKzzf6Y98(SponsoredMessageInfoView r0, Runnable r1, ClickableSpan r2) {
        r0.lambda$new$0(r1, r2);
    }

    public SponsoredMessageInfoView(Context r22, Runnable r23, Theme.ResourcesProvider r24) {
        super(r22);
        LinearLayout r4 = new LinearLayout(r22);
        r4.setOrientation(1);
        TextView r6 = new TextView(r22);
        r6.setText(LocaleController.getString(R.string.SponsoredMessageInfo));
        r6.setTypeface(AndroidUtilities.bold());
        int r7 = Theme.key_windowBackgroundWhiteBlackText;
        r6.setTextColor(Theme.getColor(r7, r24));
        r6.setTextSize(1, 20.0f);
        LinkSpanDrawable.LinksTextView r8 = new LinkSpanDrawable.LinksTextView(r22, r24);
        r8.setText(AndroidUtilities.replaceLinks(LocaleController.getString("SponsoredMessageInfo2Description1"), r24));
        r8.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r24));
        r8.setTextColor(Theme.getColor(r7, r24));
        r8.setTextSize(1, 14.0f);
        r8.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r8.setOnLinkPressListener(new SponsoredMessageInfoView$$ExternalSyntheticLambda0(this, r23));
        LinkSpanDrawable.LinksTextView r11 = new LinkSpanDrawable.LinksTextView(r22);
        r11.setText(AndroidUtilities.replaceLinks(LocaleController.getString("SponsoredMessageInfo2Description2"), r24));
        r11.setTextColor(Theme.getColor(r7, r24));
        r11.setTextSize(1, 14.0f);
        r11.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r11.setOnLinkPressListener(new SponsoredMessageInfoView$$ExternalSyntheticLambda1(this, r23));
        LinkSpanDrawable.LinksTextView r13 = new LinkSpanDrawable.LinksTextView(r22);
        r13.setText(AndroidUtilities.replaceLinks(LocaleController.getString("SponsoredMessageInfo2Description3"), r24));
        r13.setTextColor(Theme.getColor(r7, r24));
        r13.setTextSize(1, 14.0f);
        r13.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r13.setOnLinkPressListener(new SponsoredMessageInfoView$$ExternalSyntheticLambda2(this, r23));
        Paint r14 = new Paint(1);
        r14.setStyle(Paint.Style.STROKE);
        int r15 = Theme.key_featuredStickers_addButton;
        r14.setColor(Theme.getColor(r15, r24));
        r14.setStrokeWidth(AndroidUtilities.dp(1.0f));
        AnonymousClass1 r10 = new AnonymousClass1(this, r22, r14);
        r10.setOnClickListener(new AnonymousClass2(this, r23, r22));
        r10.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r10.setText(LocaleController.getString(R.string.SponsoredMessageAlertLearnMoreUrl));
        r10.setTextColor(Theme.getColor(r15, r24));
        r10.setBackground(Theme.AdaptiveRipple.filledRect(Theme.getColor(Theme.key_dialogBackground, r24), new float[]{4.0f}));
        r10.setTextSize(1, 14.0f);
        r10.setGravity(16);
        LinkSpanDrawable.LinksTextView r2 = new LinkSpanDrawable.LinksTextView(r22);
        r2.setText(AndroidUtilities.replaceLinks(LocaleController.getString("SponsoredMessageInfo2Description4"), r24));
        r2.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r2.setTextColor(Theme.getColor(r7, r24));
        r2.setTextSize(1, 14.0f);
        r6.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
        r4.addView(r6);
        r8.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
        r4.addView(r8, LayoutHelper.createLinear(-1, -2, 0, 0, 18, 0, 0));
        r11.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
        r4.addView(r11, LayoutHelper.createLinear(-1, -2, 0, 0, 24, 0, 0));
        r13.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
        r4.addView(r13, LayoutHelper.createLinear(-1, -2, 0, 0, 24, 0, 0));
        r4.addView(r10, LayoutHelper.createLinear(-2, 34, 1, 22, 14, 22, 0));
        r2.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
        r4.addView(r2, LayoutHelper.createLinear(-1, -2, 0, 0, 14, 0, 0));
        ScrollView r1 = new ScrollView(getContext());
        r1.addView(r4);
        addView(r1, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 12.0f, 0.0f, 22.0f));
    }

    private /* synthetic */ void lambda$new$0(Runnable r1, ClickableSpan r2) {
        if (r1 == null) goto L4;
        r1.run();
    L4:
        r2.onClick(this);
    }

    private /* synthetic */ void lambda$new$1(Runnable r1, ClickableSpan r2) {
        if (r1 == null) goto L4;
        r1.run();
    L4:
        r2.onClick(this);
    }

    private /* synthetic */ void lambda$new$2(Runnable r1, ClickableSpan r2) {
        if (r1 == null) goto L4;
        r1.run();
    L4:
        r2.onClick(this);
    }
}
