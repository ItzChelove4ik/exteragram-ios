package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.text.TextPaint;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class TooManyCommunitiesHintCell extends FrameLayout {
    private TextView headerTextView;
    private FrameLayout imageLayout;
    private ImageView imageView;
    private TextView messageTextView;

    /* renamed from: org.telegram.ui.Cells.TooManyCommunitiesHintCell$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        RectF rect;
        final /* synthetic */ TooManyCommunitiesHintCell this$0;
        final /* synthetic */ Paint val$paint;
        final /* synthetic */ String val$s;
        final /* synthetic */ TextPaint val$textPaint;

        public AnonymousClass1(TooManyCommunitiesHintCell r1, Context r2, Paint r3, TextPaint r4, String r5) {
            this.this$0 = r1;
            this.val$paint = r3;
            this.val$textPaint = r4;
            this.val$s = r5;
            super(r2);
            this.rect = new RectF();
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            super.onDraw(r6);
            this.val$paint.setColor(Theme.getColor(Theme.key_text_RedRegular));
            r6.save();
            r6.translate((getMeasuredWidth() - this.val$textPaint.measureText(this.val$s)) - AndroidUtilities.dp(8.0f), AndroidUtilities.dpf2(7.0f));
            this.rect.set(0.0f, 0.0f, this.val$textPaint.measureText(this.val$s), this.val$textPaint.getTextSize());
            this.rect.inset(-AndroidUtilities.dp(6.0f), -AndroidUtilities.dp(3.0f));
            float r0 = (this.val$textPaint.getTextSize() / 2.0f) + AndroidUtilities.dp(3.0f);
            r6.drawRoundRect(this.rect, r0, r0, this.val$paint);
            r6.drawText(this.val$s, 0.0f, this.val$textPaint.getTextSize() - AndroidUtilities.dpf2(2.0f), this.val$textPaint);
            r6.restore();
        }
    }

    public TooManyCommunitiesHintCell(Context r13) {
        super(r13);
        ImageView r0 = new ImageView(r13);
        this.imageView = r0;
        int r2 = Theme.key_chats_nameMessage_threeLines;
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2), PorterDuff.Mode.MULTIPLY));
        TextView r02 = new TextView(r13);
        this.headerTextView = r02;
        r02.setTextColor(Theme.getColor(r2));
        this.headerTextView.setTextSize(1, 20.0f);
        this.headerTextView.setTypeface(AndroidUtilities.bold());
        this.headerTextView.setGravity(17);
        addView(this.headerTextView, LayoutHelper.createFrame(-1, -2.0f, 51, 52.0f, 75.0f, 52.0f, 0.0f));
        TextView r03 = new TextView(r13);
        this.messageTextView = r03;
        r03.setTextColor(Theme.getColor(Theme.key_chats_message));
        this.messageTextView.setTextSize(1, 14.0f);
        this.messageTextView.setGravity(17);
        addView(this.messageTextView, LayoutHelper.createFrame(-1, -2.0f, 51, 36.0f, 110.0f, 36.0f, 0.0f));
        TextPaint r7 = new TextPaint(1);
        r7.setColor(-1);
        r7.setTextSize(AndroidUtilities.dp(12.0f));
        r7.setTypeface(AndroidUtilities.bold());
        AnonymousClass1 r3 = new AnonymousClass1(this, r13, new Paint(1), r7, "500");
        this.imageLayout = r3;
        r3.setWillNotDraw(false);
        this.imageLayout.addView(this.imageView, LayoutHelper.createFrame(-2, -2, 1));
        addView(this.imageLayout, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 12.0f, 0.0f, 6.0f));
        this.headerTextView.setText(LocaleController.getString(R.string.TooManyCommunities));
        this.imageView.setImageResource(R.drawable.groups_limit1);
    }

    public void setMessageText(String r1) {
        this.messageTextView.setText(r1);
    }
}
