package org.telegram.ui.Stories;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes7.dex */
public class StoryFailView extends FrameLayout {
    private final TextView button;
    private final Paint redPaint;
    private final TextView subtitleTextView;
    private final TextView titleTextView;
    private final Paint whitePaint;

    public StoryFailView(Context r13, Theme.ResourcesProvider r14) {
        super(r13);
        Paint r0 = new Paint(1);
        this.redPaint = r0;
        Paint r2 = new Paint(1);
        this.whitePaint = r2;
        r0.setColor(Theme.getColor(Theme.key_text_RedBold, r14));
        r2.setColor(-1);
        setWillNotDraw(false);
        TextView r22 = new TextView(r13);
        this.titleTextView = r22;
        r22.setTextSize(1, 14.0f);
        r22.setText(LocaleController.getString(R.string.StoryError));
        r22.setTextColor(-1);
        addView(r22, LayoutHelper.createFrame(-2, -2.0f, 19, 44.0f, 0.0f, 0.0f, 0.0f));
        TextView r23 = new TextView(r13);
        this.subtitleTextView = r23;
        r23.setTextSize(1, 8.0f);
        r23.setTextColor(Theme.multAlpha(-1, 0.5f));
        r23.setVisibility(8);
        r23.setTranslationY(AndroidUtilities.dp(9.0f));
        addView(r23, LayoutHelper.createFrame(-2, -2.0f, 19, 44.0f, 0.0f, 0.0f, 0.0f));
        TextView r24 = new TextView(r13);
        this.button = r24;
        r24.setPadding(AndroidUtilities.dp(13.0f), 0, AndroidUtilities.dp(13.0f), 0);
        r24.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(16.0f), 536870911, 956301311));
        r24.setTypeface(AndroidUtilities.bold());
        r24.setText(LocaleController.getString(R.string.TryAgain));
        r24.setTextSize(1, 14.0f);
        r24.setTextColor(-1);
        r24.setGravity(17);
        addView(r24, LayoutHelper.createFrame(-2, 32.0f, 21, 0.0f, 0.0f, 12.0f, 0.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        super.onDraw(r10);
        float r0 = AndroidUtilities.dp(23.0f);
        float r1 = getHeight() / 2.0f;
        r10.drawCircle(r0, r1, AndroidUtilities.dp(10.0f), this.redPaint);
        RectF r2 = AndroidUtilities.rectTmp;
        r2.set(r0 - AndroidUtilities.dp(1.0f), r1 - AndroidUtilities.dpf2(4.6f), AndroidUtilities.dp(1.0f) + r0, AndroidUtilities.dpf2(1.6f) + r1);
        r10.drawRoundRect(r2, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.whitePaint);
        r2.set(r0 - AndroidUtilities.dp(1.0f), AndroidUtilities.dpf2(2.6f) + r1, r0 + AndroidUtilities.dp(1.0f), r1 + AndroidUtilities.dpf2(4.6f));
        r10.drawRoundRect(r2, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.whitePaint);
    }

    public void set(TLRPC.TL_error r3) {
        if (r3 != null) goto L4;
    L8:
        this.titleTextView.setTranslationY(0.0f);
        this.subtitleTextView.setVisibility(8);
        return;
    L4:
        if (TextUtils.isEmpty(r3.text) == true) goto L8;
        this.titleTextView.setTranslationY(-AndroidUtilities.dpf2(5.33f));
        this.subtitleTextView.setText(r3.text);
        this.subtitleTextView.setVisibility(0);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener r1) {
        this.button.setOnClickListener(r1);
    }
}
