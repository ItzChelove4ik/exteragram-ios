package org.telegram.ui.community.cells;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.IconBackgroundColors;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.SettingsActivity;

/* loaded from: classes3.dex */
public class CommunityRequestsCell extends LinearLayout implements Theme.Colorable {
    private final SettingsActivity.SettingCell.Background iconBackground;
    private final FrameLayout iconLayout;
    private final ImageView iconView;
    private boolean mUnreadMode;
    private final boolean mini;
    private final Theme.ResourcesProvider resourcesProvider;
    private final LinearLayout textLayout;
    private final TextView titleView;
    private final TextView valueView;

    /* loaded from: classes7.dex */
    public static class Factory extends UItem.UItemFactory<CommunityRequestsCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(int r3, IconBackgroundColors r4, int r5, CharSequence r6, CharSequence r7, boolean r8) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.id = r3;
            r0.iconResId = r5;
            r0.text = r6;
            r0.textValue = r7;
            r0.longValue = (r4.top & 4294967295L) | (r4.bottom << 32);
            r0.accent = r8;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r8, UItem r9, boolean r10, UniversalAdapter r11, UniversalRecyclerView r12) {
            long r102 = r9.longValue;
            CommunityRequestsCell r0 = (CommunityRequestsCell) r8;
            r0.set((int) r102, (int) (r102 >>> 32), r9.iconResId, r9.text, r9.textValue, r9.accent);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public CommunityRequestsCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new CommunityRequestsCell(r1, r5);
        }
    }

    public CommunityRequestsCell(Context r2, Theme.ResourcesProvider r3) {
        this(r2, r3, false);
    }

    public CommunityRequestsCell(Context r20, Theme.ResourcesProvider r21, boolean r22) {
        super(r20);
        this.resourcesProvider = r21;
        this.mini = r22;
        setOrientation(0);
        FrameLayout r4 = new FrameLayout(r20);
        this.iconLayout = r4;
        SettingsActivity.SettingCell.Background r5 = new SettingsActivity.SettingCell.Background();
        this.iconBackground = r5;
        r4.setBackground(r5);
        ImageView r52 = new ImageView(r20);
        this.iconView = r52;
        r52.setScaleType(ImageView.ScaleType.FIT_CENTER);
        r4.addView(r52, LayoutHelper.createFrame(24, 24, 17));
        LinearLayout r53 = new LinearLayout(r20);
        this.textLayout = r53;
        r53.setOrientation(1);
        TextView r8 = new TextView(r20);
        this.titleView = r8;
        r8.setTextSize(1, 16.0f);
        r53.addView(r8, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
        TextView r82 = new TextView(r20);
        this.valueView = r82;
        r82.setGravity(17);
        r82.setMinWidth(AndroidUtilities.dp(20.66f));
        r82.setPadding(AndroidUtilities.dp(6.33f), 0, AndroidUtilities.dp(6.33f), 0);
        r82.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L13;
        addView(r82, LayoutHelper.createLinear(-2.0f, 20.66f, 16, 13.33f, 0.0f, 0.0f, 0.0f));
        if (r22 == false) goto L7;
        int r17 = 12;
    L8:
        addView(r53, LayoutHelper.createLinear(0, -2, 1.0f, 23, 20, 0, r17, 0));
        if (r22 == false) goto L11;
        int r13 = 9;
    L12:
        addView(r4, LayoutHelper.createLinear(28, 28, 21, 0, 0, r13, 0));
    L21:
        updateColors();
        setUnreadMode(true);
        return;
    L11:
        r13 = 14;
        goto L12
    L7:
        r17 = 16;
        goto L8
    L13:
        if (r22 == false) goto L15;
        int r14 = 9;
    L16:
        addView(r4, LayoutHelper.createLinear(28, 28, 19, r14, 0, 0, 0));
        if (r22 == false) goto L19;
        int r15 = 12;
    L20:
        addView(r53, LayoutHelper.createLinear(0, -2, 1.0f, 23, r15, 0, 20, 0));
        addView(r82, LayoutHelper.createLinear(-2.0f, 20.66f, 16, 0.0f, 0.0f, 13.33f, 0.0f));
        goto L21
    L19:
        r15 = 16;
        goto L20
    L15:
        r14 = 14;
        goto L16
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        int r22 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30);
        if (this.mini == false) goto L5;
        float r0 = 44.0f;
    L6:
        super.onMeasure(r22, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r0), TLObject.FLAG_30));
        return;
    L5:
        r0 = 50.0f;
        goto L6
    }

    public void set(int r3, int r4, int r5, CharSequence r6, CharSequence r7, boolean r8) {
        FrameLayout r0 = this.iconLayout;
        if (r5 == 0) goto L5;
        int r1 = 0;
    L6:
        r0.setVisibility(r1);
        TextView r02 = this.titleView;
        if (r5 != 0) goto L9;
        float r12 = AndroidUtilities.dp(2.0f);
    L10:
        r02.setTranslationX(r12);
        this.iconBackground.setColor(r3, r4);
        this.iconView.setImageResource(r5);
        setTitle(r6);
        setValue(r7);
        setUnreadMode(r8);
        return;
    L9:
        r12 = 0.0f;
        goto L10
    L5:
        r1 = 8;
        goto L6
    }

    public void setTitle(CharSequence r1) {
        this.titleView.setText(r1);
    }

    public void setUnreadMode(boolean r5) {
        if (this.mUnreadMode == r5) goto L21;
        this.mUnreadMode = r5;
        TextView r0 = this.valueView;
        if (r5 == false) goto L7;
        float r1 = 13.0f;
    L8:
        r0.setTextSize(1, r1);
        TextView r02 = this.valueView;
        ShapeDrawable r12 = null;
        if (r5 == false) goto L11;
        Typeface r2 = AndroidUtilities.bold();
    L12:
        r02.setTypeface(r2);
        TextView r03 = this.valueView;
        if (r5 == false) goto L15;
        int r22 = Theme.key_chats_unreadCounterText;
    L16:
        r03.setTextColor(Theme.getColor(r22, this.resourcesProvider));
        TextView r04 = this.valueView;
        if (r5 == false) goto L19;
        r12 = Theme.createRoundRectDrawable(AndroidUtilities.dp(10.33f), Theme.getColor(Theme.key_chats_unreadCounter, this.resourcesProvider));
    L19:
        r04.setBackground(r12);
        return;
    L15:
        r22 = Theme.key_windowBackgroundWhiteBlueText;
        goto L16
    L11:
        r2 = null;
        goto L12
    L7:
        r1 = 16.0f;
        goto L8
    }

    public void setValue(CharSequence r3) {
        TextView r0 = this.valueView;
        if (TextUtils.isEmpty(r3) == true) goto L5;
        int r1 = 0;
    L6:
        r0.setVisibility(r1);
        this.valueView.setText(r3);
        return;
    L5:
        r1 = 8;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        TextView r0 = this.valueView;
        if (this.mUnreadMode == false) goto L5;
        int r1 = Theme.key_chats_unreadCounterText;
    L6:
        r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        TextView r02 = this.valueView;
        if (this.mUnreadMode == false) goto L9;
        ShapeDrawable r12 = Theme.createRoundRectDrawable(AndroidUtilities.dp(10.33f), Theme.getColor(Theme.key_chats_unreadCounter, this.resourcesProvider));
    L10:
        r02.setBackground(r12);
        SettingsActivity.SettingCell.Background r03 = this.iconBackground;
        Theme.ResourcesProvider r4 = this.resourcesProvider;
        if (r4 == null) goto L13;
        boolean r42 = r4.isDark();
    L14:
        r03.setDrawBorder(r42);
        return;
    L13:
        r42 = Theme.isCurrentThemeDark();
        goto L14
    L9:
        r12 = null;
        goto L10
    L5:
        r1 = Theme.key_windowBackgroundWhiteBlueText;
        goto L6
    }
}
