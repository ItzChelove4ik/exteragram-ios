package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public class SettingsSearchCell extends FrameLayout {
    private ImageView imageView;
    private int left;
    private boolean needDivider;
    private TextView textView;
    private TextView valueTextView;

    public static class Factory extends UItem.UItemFactory<SettingsSearchCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(CharSequence r1, MessagesController.FaqSearchResult r2) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.text = r1;
            r0.object = r2;
            return r0;
        }

        public static UItem of(CharSequence r1, ProfileActivity.SearchAdapter.SearchResult r2) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.text = r1;
            r0.object = r2;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            Object r0 = r2.object;
            if ((r0 instanceof ProfileActivity.SearchAdapter.SearchResult) == false) goto L7;
            ProfileActivity.SearchAdapter.SearchResult r02 = (ProfileActivity.SearchAdapter.SearchResult) r0;
            ((SettingsSearchCell) r1).setTextAndValueAndIcon(r2.text, r02.path, r02.iconResId, r3);
            return;
        L7:
            if ((r0 instanceof MessagesController.FaqSearchResult) == false) goto L10;
            ((SettingsSearchCell) r1).setTextAndValue(r2.text, ((MessagesController.FaqSearchResult) r0).path, true, r3);
            return;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public SettingsSearchCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new SettingsSearchCell(r1);
        }
    }

    public static class VerticalImageSpan extends ImageSpan {
        public VerticalImageSpan(Drawable r1) {
            super(r1);
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            Drawable r0 = getDrawable();
            r1.save();
            int r32 = r9.getFontMetricsInt().descent;
            r1.translate(r5, ((r7 + r32) - ((r32 - r2.ascent) / 2)) - ((r0.getBounds().bottom - r0.getBounds().top) / 2));
            if (LocaleController.isRTL == false) goto L5;
            r1.scale(-1.0f, 1.0f, r0.getIntrinsicWidth() / 2, r0.getIntrinsicHeight() / 2);
        L5:
            r0.draw(r1);
            r1.restore();
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            Rect r0 = getDrawable().getBounds();
            if (r5 == null) goto L6;
            Paint.FontMetricsInt r12 = r1.getFontMetricsInt();
            int r22 = r12.descent;
            int r13 = r12.ascent;
            int r14 = r13 + ((r22 - r13) / 2);
            int r32 = (r0.bottom - r0.top) / 2;
            int r23 = r14 - r32;
            r5.ascent = r23;
            r5.top = r23;
            int r15 = r14 + r32;
            r5.bottom = r15;
            r5.descent = r15;
        L6:
            return r0.right;
        }
    }

    public SettingsSearchCell(Context r15) {
        super(r15);
        TextView r0 = new TextView(r15);
        this.textView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        TextView r02 = this.textView;
        if (LocaleController.isRTL == false) goto L5;
        int r3 = 5;
    L6:
        r02.setGravity(r3);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        TextView r03 = this.textView;
        boolean r32 = LocaleController.isRTL;
        if (r32 == false) goto L9;
        int r8 = 5;
    L11:
        if (r32 == false) goto L13;
        float r9 = 16.0f;
    L14:
        if (r32 == false) goto L16;
        float r11 = 71.0f;
    L17:
        addView(r03, LayoutHelper.createFrame(-2, -2.0f, r8, r9, 10.0f, r11, 0.0f));
        TextView r04 = new TextView(r15);
        this.valueTextView = r04;
        r04.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
        this.valueTextView.setTextSize(1, 13.0f);
        this.valueTextView.setTypeface(AndroidUtilities.regular());
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        TextView r05 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L20;
        int r1 = 5;
    L21:
        r05.setGravity(r1);
        TextView r06 = this.valueTextView;
        boolean r12 = LocaleController.isRTL;
        if (r12 == false) goto L24;
        int r82 = 5;
    L25:
        if (r12 == false) goto L27;
        float r92 = 16.0f;
    L28:
        if (r12 == false) goto L30;
        float r112 = 71.0f;
    L31:
        addView(r06, LayoutHelper.createFrame(-2, -2.0f, r82, r92, 33.0f, r112, 0.0f));
        ImageView r07 = new ImageView(r15);
        this.imageView = r07;
        r07.setScaleType(ImageView.ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY));
        ImageView r152 = this.imageView;
        if (LocaleController.isRTL == false) goto L34;
        int r83 = 5;
    L35:
        addView(r152, LayoutHelper.createFrame(48, 48.0f, r83, 10.0f, 8.0f, 10.0f, 0.0f));
        return;
    L34:
        r83 = 3;
        goto L35
    L30:
        r112 = 16.0f;
        goto L31
    L27:
        r92 = 71.0f;
        goto L28
    L24:
        r82 = 3;
        goto L25
    L20:
        r1 = 3;
        goto L21
    L16:
        r11 = 16.0f;
        goto L17
    L13:
        r9 = 71.0f;
        goto L14
    L9:
        r8 = 3;
        goto L11
    L5:
        r3 = 3;
        goto L6
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        if (this.needDivider == true) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r2 = r0;
        float r3 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(this.left);
    L13:
        r8.drawLine(r2, r3, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(this.left);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
    }

    public void setTextAndValue(CharSequence r11, String[] r12, boolean r13, boolean r14) {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        if (r13 == false) goto L11;
        this.valueTextView.setText(r11);
        SpannableStringBuilder r112 = new SpannableStringBuilder();
        int r132 = 0;
    L6:
        if (r132 >= r12.length) goto L10;
        if (r132 == 0) goto L9;
        r112.append(" > ");
        Drawable r6 = getContext().getResources().getDrawable(R.drawable.settings_arrow).mutate();
        r6.setBounds(0, 0, r6.getIntrinsicWidth(), r6.getIntrinsicHeight());
        r6.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText), PorterDuff.Mode.MULTIPLY));
        r112.setSpan(new VerticalImageSpan(r6), r112.length() - 2, r112.length() - 1, 33);
    L9:
        r112.append(r12[r132]);
        r132 = r132 + 1;
        goto L6
    L10:
        this.textView.setText(r112);
        this.valueTextView.setVisibility(0);
        r0.topMargin = AndroidUtilities.dp(10.0f);
    L21:
        int r122 = AndroidUtilities.dp(16.0f);
        r0.rightMargin = r122;
        r0.leftMargin = r122;
        FrameLayout.LayoutParams r123 = (FrameLayout.LayoutParams) this.valueTextView.getLayoutParams();
        int r113 = AndroidUtilities.dp(16.0f);
        r123.rightMargin = r113;
        r123.leftMargin = r113;
        this.imageView.setVisibility(8);
        this.needDivider = r14;
        setWillNotDraw(!r14);
        this.left = 16;
        return;
    L11:
        this.textView.setText(r11);
        if (r12 == null) goto L20;
        SpannableStringBuilder r114 = new SpannableStringBuilder();
        int r133 = 0;
    L15:
        if (r133 >= r12.length) goto L19;
        if (r133 == 0) goto L18;
        r114.append(" > ");
        Drawable r62 = getContext().getResources().getDrawable(R.drawable.settings_arrow).mutate();
        r62.setBounds(0, 0, r62.getIntrinsicWidth(), r62.getIntrinsicHeight());
        r62.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2), PorterDuff.Mode.MULTIPLY));
        r114.setSpan(new VerticalImageSpan(r62), r114.length() - 2, r114.length() - 1, 33);
    L18:
        r114.append(r12[r133]);
        r133 = r133 + 1;
        goto L15
    L19:
        this.valueTextView.setText(r114);
        this.valueTextView.setVisibility(0);
        r0.topMargin = AndroidUtilities.dp(10.0f);
        goto L21
    L20:
        r0.topMargin = AndroidUtilities.dp(21.0f);
        this.valueTextView.setVisibility(8);
        goto L21
    }

    public void setTextAndValueAndIcon(CharSequence r11, String[] r12, int r13, boolean r14) {
        this.textView.setText(r11);
        FrameLayout.LayoutParams r112 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        float r1 = 71.0f;
        if (LocaleController.isRTL == false) goto L5;
        float r0 = 16.0f;
    L6:
        r112.leftMargin = AndroidUtilities.dp(r0);
        if (LocaleController.isRTL == false) goto L9;
        float r02 = 71.0f;
    L10:
        r112.rightMargin = AndroidUtilities.dp(r02);
        if (r12 == null) goto L27;
        SpannableStringBuilder r4 = new SpannableStringBuilder();
        int r5 = 0;
    L14:
        if (r5 >= r12.length) goto L18;
        if (r5 == 0) goto L17;
        r4.append(" > ");
        Drawable r6 = getContext().getResources().getDrawable(R.drawable.settings_arrow).mutate();
        r6.setBounds(0, 0, r6.getIntrinsicWidth(), r6.getIntrinsicHeight());
        r6.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2), PorterDuff.Mode.MULTIPLY));
        r4.setSpan(new VerticalImageSpan(r6), r4.length() - 2, r4.length() - 1, 33);
    L17:
        r4.append(r12[r5]);
        r5 = r5 + 1;
        goto L14
    L18:
        this.valueTextView.setText(r4);
        this.valueTextView.setVisibility(0);
        r112.topMargin = AndroidUtilities.dp(10.0f);
        FrameLayout.LayoutParams r113 = (FrameLayout.LayoutParams) this.valueTextView.getLayoutParams();
        if (LocaleController.isRTL == false) goto L21;
        float r122 = 16.0f;
    L22:
        r113.leftMargin = AndroidUtilities.dp(r122);
        if (LocaleController.isRTL == true) goto L26;
        r1 = 16.0f;
    L26:
        r113.rightMargin = AndroidUtilities.dp(r1);
    L28:
        ImageView r114 = this.imageView;
        if (r13 == 0) goto L31;
        r114.setImageResource(r13);
        this.imageView.setVisibility(0);
    L32:
        this.left = 69;
        this.needDivider = r14;
        setWillNotDraw(!r14);
        return;
    L31:
        r114.setVisibility(8);
        goto L32
    L21:
        r122 = 71.0f;
        goto L22
    L27:
        r112.topMargin = AndroidUtilities.dp(21.0f);
        this.valueTextView.setVisibility(8);
        goto L28
    L9:
        r02 = 16.0f;
        goto L10
    L5:
        r0 = 71.0f;
        goto L6
    }
}
