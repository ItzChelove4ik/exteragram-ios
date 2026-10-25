package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import java.util.Date;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class DateEndCell extends FrameLayout {
    private final Theme.ResourcesProvider resourcesProvider;
    private long selectedTime;
    private final SimpleTextView timeTextView;
    private final SimpleTextView titleTextView;

    public DateEndCell(Context r18, Theme.ResourcesProvider r19) {
        super(r18);
        this.resourcesProvider = r19;
        SimpleTextView r3 = new SimpleTextView(r18);
        this.titleTextView = r3;
        r3.setTextSize(16);
        r3.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r19));
        int r6 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r5 = 5;
    L6:
        r3.setGravity(r5);
        addView(r3);
        SimpleTextView r52 = new SimpleTextView(r18);
        this.timeTextView = r52;
        r52.setTextSize(16);
        r52.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader, r19));
        if (LocaleController.isRTL == false) goto L9;
        int r1 = 3;
    L10:
        r52.setGravity(r1);
        addView(r52);
        r3.setText(LocaleController.formatString("BoostingDateAndTime", R.string.BoostingDateAndTime, new Object[0]));
        boolean r12 = LocaleController.isRTL;
        if (r12 == false) goto L13;
        int r8 = 5;
    L14:
        int r11 = r8 | 16;
        if (r12 == false) goto L17;
        float r122 = 0.0f;
    L18:
        if (r12 == false) goto L20;
        float r14 = 21.0f;
    L21:
        r3.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r11, r122, 0.0f, r14, 0.0f));
        boolean r13 = LocaleController.isRTL;
        if (r13 == true) goto L25;
        r6 = 5;
    L25:
        int r112 = r6 | 16;
        if (r13 == false) goto L28;
        float r123 = 21.0f;
    L29:
        if (r13 == false) goto L31;
        float r142 = 0.0f;
    L32:
        r52.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r112, r123, 0.0f, r142, 0.0f));
        setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r19));
        return;
    L31:
        r142 = 21.0f;
        goto L32
    L28:
        r123 = 0.0f;
        goto L29
    L20:
        r14 = 0.0f;
        goto L21
    L17:
        r122 = 21.0f;
        goto L18
    L13:
        r8 = 3;
        goto L14
    L9:
        r1 = 5;
        goto L10
    L5:
        r5 = 3;
        goto L6
    }

    public long getSelectedTime() {
        return this.selectedTime;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
    }

    public void setDate(long r2) {
        this.selectedTime = r2;
        Date r0 = new Date(r2);
        String r22 = LocaleController.getInstance().getFormatterDayMonth().format(r0);
        String r3 = LocaleController.getInstance().getFormatterDay().format(r0);
        this.timeTextView.setText(LocaleController.formatString("formatDateAtTime", R.string.formatDateAtTime, new Object[]{r22, r3}));
    }
}
