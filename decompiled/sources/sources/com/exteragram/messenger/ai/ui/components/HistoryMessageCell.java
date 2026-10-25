package com.exteragram.messenger.ai.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.ai.data.Message;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class HistoryMessageCell extends FrameLayout {
    private boolean needDivider;
    private final Theme.ResourcesProvider resourcesProvider;
    private final TextView textView;
    private final TextView titleView;

    public static class Factory extends UItem.UItemFactory<HistoryMessageCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem asHistoryCell(int r1, Message r2, CharSequence r3, boolean r4) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.id = r1;
            r0.object = r2;
            r0.text = r3;
            r0.checked = r4;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            if ((r1 instanceof HistoryMessageCell) == false) goto L8;
            HistoryMessageCell r12 = (HistoryMessageCell) r1;
            Object r0 = r2.object;
            if ((r0 instanceof Message) == false) goto L9;
            r12.set((Message) r0, r2.text, r2.checked, r3);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r5, UItem r6) {
            Object r4 = r5.object;
            if ((r4 instanceof Message) == false) goto L14;
            Message r42 = (Message) r4;
            Object r0 = r6.object;
            if ((r0 instanceof Message) == false) goto L14;
            Message r02 = (Message) r0;
            if (r5.checked != r6.checked) goto L14;
            if (TextUtils.equals(r42.role(), r02.role()) == false) goto L14;
            if (TextUtils.equals(r5.text, r6.text) == false) goto L14;
            return true;
        L14:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public HistoryMessageCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new HistoryMessageCell(r1, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r1, UItem r2) {
            if (r1.id != r2.id) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public HistoryMessageCell(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        this.resourcesProvider = r12;
        setWillNotDraw(false);
        LinearLayout r0 = new LinearLayout(r11);
        r0.setOrientation(1);
        addView(r0, LayoutHelper.createFrame(-1, -2.0f, 51, 22.0f, 10.0f, 22.0f, 11.0f));
        TextView r2 = new TextView(r11);
        this.titleView = r2;
        r2.setTextSize(1, 14.0f);
        r2.setTypeface(AndroidUtilities.bold());
        r2.setSingleLine(true);
        r2.setEllipsize(TextUtils.TruncateAt.END);
        r2.setGravity(3);
        r0.addView(r2, LayoutHelper.createLinear(-1, -2));
        TextView r22 = new TextView(r11);
        this.textView = r22;
        r22.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r12));
        r22.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, r12));
        r22.setTextSize(1, 15.0f);
        r22.setGravity(3);
        r0.addView(r22, LayoutHelper.createLinear(-1, -2, 0.0f, 3.0f, 0.0f, 0.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.needDivider == false) goto L6;
        r9.drawLine(AndroidUtilities.dp(22.0f), getMeasuredHeight() - 1, getMeasuredWidth() - AndroidUtilities.dp(22.0f), getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
    }

    public void set(Message r2, CharSequence r3, boolean r4, boolean r5) {
        this.needDivider = r5;
        TextView r52 = this.textView;
        if (r4 == false) goto L5;
        int r0 = Integer.MAX_VALUE;
    L6:
        r52.setMaxLines(r0);
        TextView r53 = this.textView;
        if (r4 == false) goto L9;
        TextUtils.TruncateAt r42 = null;
    L10:
        r53.setEllipsize(r42);
        boolean r22 = "assistant".equals(r2.role());
        TextView r43 = this.titleView;
        if (r22 == true) goto L13;
        int r54 = R.string.FromYou;
    L14:
        r43.setText(LocaleController.getString(r54));
        TextView r44 = this.titleView;
        if (r22 == true) goto L17;
        int r23 = Theme.key_windowBackgroundWhiteBlueHeader;
    L18:
        r44.setTextColor(Theme.getColor(r23, this.resourcesProvider));
        TextView r1 = this.textView;
        if (r3 != null) goto L21;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L21:
        r1.setText(Emoji.replaceEmoji(r3, r1.getPaint().getFontMetricsInt(), false));
        return;
    L17:
        r23 = Theme.key_windowBackgroundWhiteGrayText2;
        goto L18
    L13:
        r54 = R.string.AIAssistant;
        goto L14
    L9:
        r42 = TextUtils.TruncateAt.END;
        goto L10
    L5:
        r0 = 6;
        goto L6
    }
}
