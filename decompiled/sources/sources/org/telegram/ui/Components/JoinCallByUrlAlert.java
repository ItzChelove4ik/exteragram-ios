package org.telegram.ui.Components;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class JoinCallByUrlAlert extends BottomSheet {
    private boolean joinAfterDismiss;

    public static class BottomSheetCell extends FrameLayout {
        private View background;
        private TextView textView;

        /* renamed from: -$$Nest$fgetbackground, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m11179$$Nest$fgetbackground(BottomSheetCell r0) {
            return r0.background;
        }

        public BottomSheetCell(Context r11) {
            super(r11);
            View r0 = new View(r11);
            this.background = r0;
            r0.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{4.0f}));
            addView(this.background, LayoutHelper.createFrame(-1, -1.0f, 0, 16.0f, 16.0f, 16.0f, 16.0f));
            TextView r02 = new TextView(r11);
            this.textView = r02;
            r02.setLines(1);
            this.textView.setSingleLine(true);
            this.textView.setGravity(1);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setGravity(17);
            this.textView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
            this.textView.setTextSize(1, 14.0f);
            this.textView.setTypeface(AndroidUtilities.bold());
            addView(this.textView, LayoutHelper.createFrame(-2, -2, 17));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30));
        }

        public void setText(CharSequence r1) {
            this.textView.setText(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$KkDaDJiFYpuUPajRYEYzNnj3yQ4(JoinCallByUrlAlert r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public JoinCallByUrlAlert(Context r14, TLRPC.Chat r15) {
        super(r14, true);
        setApplyBottomPadding(false);
        setApplyTopPadding(false);
        LinearLayout r2 = new LinearLayout(r14);
        r2.setOrientation(1);
        setCustomView(r2);
        BackupImageView r3 = new BackupImageView(r14);
        r3.setRoundRadius(ExteraConfig.getAvatarCorners(90.0f));
        r2.addView(r3, LayoutHelper.createLinear(90, 90, 49, 0, 29, 0, 0));
        r3.setForUserOrChat(r15, new AvatarDrawable(r15));
        TextView r32 = new TextView(r14);
        r32.setTypeface(AndroidUtilities.bold());
        r32.setTextSize(1, 18.0f);
        r32.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r32.setGravity(1);
        r2.addView(r32, LayoutHelper.createLinear(-2, -2, 49, 17, 24, 17, 0));
        TextView r4 = new TextView(r14);
        r4.setTextSize(1, 14.0f);
        r4.setTextColor(Theme.getColor(Theme.key_dialogTextGray3));
        r4.setGravity(1);
        r2.addView(r4, LayoutHelper.createLinear(-2, -2, 49, 30, 8, 30, 0));
        ChatObject.Call r0 = AccountInstance.getInstance(this.currentAccount).getMessagesController().getGroupCall(r15.id, false);
        if (r0 != null) goto L5;
        r32.setText(r15.title);
        r4.setText(LocaleController.getString(R.string.NoOneJoinedYet));
    L13:
        BottomSheetCell r02 = new BottomSheetCell(r14);
        r02.setBackground(null);
        if (ChatObject.isChannelOrGiga(r15) == false) goto L16;
        r02.setText(LocaleController.getString(R.string.VoipChannelJoinVoiceChatUrl));
    L17:
        BottomSheetCell.m11179$$Nest$fgetbackground(r02).setOnClickListener(new JoinCallByUrlAlert$$ExternalSyntheticLambda0(this));
        r2.addView(r02, LayoutHelper.createLinear(-1, 50, 51, 0, 30, 0, 0));
        return;
    L16:
        r02.setText(LocaleController.getString(R.string.VoipGroupJoinVoiceChatUrl));
        goto L17
    L5:
        if (TextUtils.isEmpty(r0.call.title) == false) goto L7;
        r32.setText(r15.title);
    L8:
        int r03 = r0.call.participants_count;
        if (r03 != 0) goto L11;
        r4.setText(LocaleController.getString(R.string.NoOneJoinedYet));
        goto L13
    L11:
        r4.setText(LocaleController.formatPluralString("Participants", r03, new Object[0]));
        goto L13
    L7:
        r32.setText(r0.call.title);
        goto L8
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        this.joinAfterDismiss = true;
        dismiss();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        if (this.joinAfterDismiss == false) goto L6;
        onJoin();
        return;
    }

    public abstract void onJoin();
}
