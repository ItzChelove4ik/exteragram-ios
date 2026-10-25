package com.exteragram.messenger.ai.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.ai.data.Role;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class RoleCell extends FrameLayout {
    private final int currentAccount;
    private final BackupImageView emojiView;
    private boolean needDivider;
    private final RadioButton radioButton;
    private final TextView subtitleView;
    private final TextView titleView;

    public static class Factory extends UItem.UItemFactory<RoleCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem asRoleCell(Role r1, View.OnClickListener r2) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            r0.checked = r1.isSelected();
            r0.clickCallback = r2;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            if ((r1 instanceof RoleCell) == false) goto L8;
            RoleCell r12 = (RoleCell) r1;
            Object r0 = r2.object;
            if ((r0 instanceof Role) == false) goto L9;
            r12.set((Role) r0, r2.checked, r3);
            r12.getRadioButton().setOnClickListener(r2.clickCallback);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r5, UItem r6) {
            Object r4 = r5.object;
            if ((r4 instanceof Role) == false) goto L16;
            Role r42 = (Role) r4;
            Object r0 = r6.object;
            if ((r0 instanceof Role) == false) goto L16;
            Role r02 = (Role) r0;
            if (r5.checked != r6.checked) goto L16;
            if (TextUtils.equals(r42.getName(), r02.getName()) == false) goto L16;
            if (TextUtils.equals(r42.getPrompt(), r02.getPrompt()) == false) goto L16;
            if (r42.getEmojiId() != r02.getEmojiId()) goto L16;
            return true;
        L16:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RoleCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new RoleCell(r1, r3, r5);
        }
    }

    public RoleCell(Context r12, int r13, Theme.ResourcesProvider r14) {
        super(r12);
        this.currentAccount = r13;
        setWillNotDraw(false);
        BackupImageView r0 = new BackupImageView(r12);
        this.emojiView = r0;
        addView(r0, LayoutHelper.createFrame(32, 32.0f, 19, 18.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r02 = new LinearLayout(r12);
        r02.setOrientation(1);
        addView(r02, LayoutHelper.createFrame(-1, -2.0f, 19, 68.0f, 0.0f, 62.0f, 0.0f));
        TextView r2 = new TextView(r12);
        this.titleView = r2;
        r2.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r14));
        r2.setTextSize(1, 16.0f);
        r2.setSingleLine(true);
        r2.setMaxLines(1);
        TextUtils.TruncateAt r3 = TextUtils.TruncateAt.END;
        r2.setEllipsize(r3);
        r2.setGravity(3);
        r2.setIncludeFontPadding(false);
        r02.addView(r2, LayoutHelper.createLinear(-1, -2));
        TextView r22 = new TextView(r12);
        this.subtitleView = r22;
        r22.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r14));
        r22.setTextSize(1, 13.0f);
        r22.setSingleLine(true);
        r22.setMaxLines(1);
        r22.setEllipsize(r3);
        r22.setGravity(3);
        r22.setIncludeFontPadding(false);
        r02.addView(r22, LayoutHelper.createLinear(-1, -2, 0.0f, 4.0f, 0.0f, 0.0f));
        RadioButton r132 = new RadioButton(r12);
        this.radioButton = r132;
        r132.setSize(AndroidUtilities.dp(20.0f));
        r132.setColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_radioBackgroundChecked));
        addView(r132, LayoutHelper.createFrame(48, 48.0f, 21, 0.0f, 0.0f, 8.0f, 0.0f));
    }

    public RadioButton getRadioButton() {
        return this.radioButton;
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        if (this.needDivider == false) goto L6;
        r8.drawLine(AndroidUtilities.dp(68.0f), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(62.0f), TLObject.FLAG_30));
    }

    public void set(Role r3, boolean r4, boolean r5) {
        this.needDivider = r5;
        this.titleView.setText(r3.getName());
        this.subtitleView.setText(r3.getPrompt());
        this.subtitleView.setVisibility(0);
        this.radioButton.setChecked(r4, true);
        if (r3.getEmojiId() == 0) goto L5;
        long r32 = r3.getEmojiId();
    L6:
        this.emojiView.setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(3, this.currentAccount, r32));
        return;
    L5:
        r32 = 5359441070201513074L;
        goto L6
    }
}
