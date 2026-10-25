package com.exteragram.messenger.icons.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.icons.IconPack;
import com.exteragram.messenger.utils.text.LocaleUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.EffectsTextView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class IconPackCell extends FrameLayout {
    private final ImageView handle;
    private final IconPackPreviewView iconView;
    private boolean needDivider;
    private final RadioButton radioButton;
    private final EffectsTextView subtitle;
    private final TextView title;

    public static class Factory extends UItem.UItemFactory<IconPackCell> {
        /* renamed from: $r8$lambda$CP8IXe3DODPybMaF8F7-Kj-xaU4, reason: not valid java name */
        public static /* synthetic */ boolean m1448$r8$lambda$CP8IXe3DODPybMaF8F7KjxaU4(UniversalRecyclerView r0, View r1, View r2, MotionEvent r3) {
            if (r3.getAction() != 0) goto L8;
            if (r0 == null) goto L9;
            r0.startDrag(r0.getChildViewHolder(r1));
            return false;
        L9:
            return false;
        L8:
            return false;
        }

        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem asIconPackCell(IconPack r1) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        @SuppressLint({"ClickableViewAccessibility"})
        public void bindView(View r2, UItem r3, boolean r4, UniversalAdapter r5, UniversalRecyclerView r6) {
            if ((r2 instanceof IconPackCell) == false) goto L6;
            IconPackCell r1 = (IconPackCell) r2;
            r1.set((IconPack) r3.object, r4, r3.checked, r3.reordering);
            r1.getHandle().setOnTouchListener(new IconPackCell$Factory$$ExternalSyntheticLambda0(r6, r2));
            return;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public IconPackCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new IconPackCell(r1, r5);
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public IconPackCell(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        setWillNotDraw(false);
        IconPackPreviewView r1 = new IconPackPreviewView(r11);
        this.iconView = r1;
        addView(r1, LayoutHelper.createFrame(44, 44.0f, 19, 16.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r13 = new LinearLayout(r11);
        r13.setOrientation(1);
        addView(r13, LayoutHelper.createFrame(-1, -2.0f, 19, 76.0f, 0.0f, 60.0f, 0.0f));
        TextView r3 = new TextView(r11);
        this.title = r3;
        r3.setGravity(3);
        r3.setTextSize(1, 16.0f);
        TextUtils.TruncateAt r5 = TextUtils.TruncateAt.END;
        r3.setEllipsize(r5);
        r3.setSingleLine(true);
        r3.setMaxLines(1);
        r3.setIncludeFontPadding(false);
        r3.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r12));
        r3.setTypeface(AndroidUtilities.regular());
        r13.addView(r3, LayoutHelper.createLinear(-1, -2));
        EffectsTextView r32 = new EffectsTextView(r11);
        this.subtitle = r32;
        r32.setGravity(3);
        r32.setClickable(true);
        r32.setTypeface(AndroidUtilities.regular());
        r32.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r32.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
        r32.setTextSize(1, 13.0f);
        r32.setEllipsize(r5);
        r32.setSingleLine(true);
        r32.setMaxLines(1);
        r32.setIncludeFontPadding(false);
        r32.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        r13.addView(r32, LayoutHelper.createLinear(-1, -2, 0.0f, 4.0f, 0.0f, 0.0f));
        RadioButton r0 = new RadioButton(r11);
        this.radioButton = r0;
        r0.setSize(AndroidUtilities.dp(20.0f));
        r0.setColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_radioBackgroundChecked));
        addView(r0, LayoutHelper.createFrame(22, 22.0f, 21, 0.0f, 0.0f, 18.0f, 0.0f));
        ImageView r02 = new ImageView(r11);
        this.handle = r02;
        r02.setImageResource(R.drawable.list_reorder);
        r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon, r12), PorterDuff.Mode.MULTIPLY));
        r02.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        r02.setScaleType(ImageView.ScaleType.FIT_CENTER);
        addView(r02, LayoutHelper.createFrame(48, 48.0f, 21, 0.0f, 0.0f, 4.0f, 0.0f));
    }

    public ImageView getHandle() {
        return this.handle;
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        if (this.needDivider == false) goto L6;
        r8.drawLine(AndroidUtilities.dp(76.0f), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f), TLObject.FLAG_30));
    }

    public void set(IconPack r3, boolean r4, boolean r5, boolean r6) {
        this.needDivider = r4;
        this.title.setText(r3.getName());
        this.subtitle.setText(LocaleUtils.fullyFormatText(r3.getAuthor()));
        this.iconView.setIconPack(r3);
        boolean r32 = r3.isBase();
        ImageView r42 = this.handle;
        int r0 = 0;
        if (r32 == false) goto L6;
        r42.setVisibility(8);
        this.radioButton.setVisibility(0);
        this.radioButton.setChecked(r5, true);
        return;
    L6:
        if (r6 == true) goto L9;
        r0 = 8;
    L9:
        r42.setVisibility(r0);
        this.radioButton.setVisibility(8);
    }
}
