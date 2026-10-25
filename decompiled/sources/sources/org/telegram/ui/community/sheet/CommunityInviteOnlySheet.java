package org.telegram.ui.community.sheet;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class CommunityInviteOnlySheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private ButtonWithCounterView cancelButton;
    private CommunityPendingInviteOnlyCell cell;
    private ButtonWithCounterView messageButton;

    public static class CommunityPendingInviteOnlyCell extends LinearLayout {
        private final BackupImageView avatarImage;
        private final TextView textView;
        private final TextView titleView;

        /* renamed from: -$$Nest$fgetavatarImage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BackupImageView m21570$$Nest$fgetavatarImage(CommunityPendingInviteOnlyCell r0) {
            return r0.avatarImage;
        }

        /* renamed from: -$$Nest$fgettextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m21571$$Nest$fgettextView(CommunityPendingInviteOnlyCell r0) {
            return r0.textView;
        }

        /* renamed from: -$$Nest$fgettitleView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m21572$$Nest$fgettitleView(CommunityPendingInviteOnlyCell r0) {
            return r0.titleView;
        }

        public CommunityPendingInviteOnlyCell(Context r11) {
            super(r11);
            setOrientation(1);
            BackupImageView r1 = new BackupImageView(r11);
            this.avatarImage = r1;
            r1.setRoundRadius(AndroidUtilities.dp(35.0f));
            addView(r1, LayoutHelper.createLinear(70, 70, 1));
            TextView r12 = new TextView(r11);
            this.titleView = r12;
            r12.setTypeface(AndroidUtilities.bold());
            r12.setTextSize(1, 20.0f);
            r12.setGravity(17);
            addView(r12, LayoutHelper.createLinear(-1, -2, 0, 0.0f, 11.33f, 0.0f, 7.0f));
            TextView r13 = new TextView(r11);
            this.textView = r13;
            r13.setTextSize(1, 14.0f);
            r13.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            r13.setGravity(17);
            addView(r13, LayoutHelper.createLinear(-1, -2));
        }
    }

    public static /* synthetic */ void $r8$lambda$IWxGTkEm_sGWQNTvJg8_dj9YmKg(CommunityInviteOnlySheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$fOnylowxukeknGEmtOeYhnlTAnw(CommunityInviteOnlySheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$l7PFJ4nnq6Kj_Wp87WQmqsnexR0(CommunityInviteOnlySheet r0, Runnable r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    public CommunityInviteOnlySheet(Context r11, TLRPC.Chat r12, TLRPC.User r13, Runnable r14) {
        super(r11, null, false, true, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, null);
        this.headerMoveTop = AndroidUtilities.dp(30.0f);
        RecyclerListView r10 = this.recyclerListView;
        int r112 = this.backgroundPaddingLeft;
        r10.setPadding(r112, 0, r112, AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(130.0f));
        this.recyclerListView.setClipToPadding(false);
        ButtonWithCounterView r102 = new ButtonWithCounterView(r11, this.resourcesProvider);
        this.cancelButton = r102;
        r102.setText(LocaleController.getString(R.string.Cancel));
        this.cancelButton.setNeutral();
        this.cancelButton.setRound();
        this.cancelButton.setOnClickListener(new CommunityInviteOnlySheet$$ExternalSyntheticLambda0(this));
        boolean r103 = ChatObject.isChannelAndNotMegaGroup(r12);
        ButtonWithCounterView r113 = new ButtonWithCounterView(r11, this.resourcesProvider);
        this.messageButton = r113;
        if (r103 == false) goto L5;
        int r132 = R.string.CommunityInviteOnlyChannelMessageOwner;
    L6:
        r113.setText(LocaleController.getString(r132));
        this.messageButton.setRound();
        this.messageButton.setOnClickListener(new CommunityInviteOnlySheet$$ExternalSyntheticLambda1(this, r14));
        CommunityPendingInviteOnlyCell r114 = new CommunityPendingInviteOnlyCell(r11);
        this.cell = r114;
        r114.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(17.0f));
        CommunityPendingInviteOnlyCell.m21570$$Nest$fgetavatarImage(this.cell).setForUserOrChat(r12, new AvatarDrawable(r12));
        CommunityPendingInviteOnlyCell.m21572$$Nest$fgettitleView(this.cell).setText(DialogObject.getName(r12));
        TextView r115 = CommunityPendingInviteOnlyCell.m21572$$Nest$fgettitleView(this.cell);
        int r122 = Theme.key_windowBackgroundWhiteBlackText;
        r115.setTextColor(getThemedColor(r122));
        SpannableStringBuilder r116 = new SpannableStringBuilder();
        r116.append("* ");
        r116.setSpan(new ColoredImageSpan(R.drawable.mini_ephemeral_hidden_14), 0, 1, 33);
        if (r103 == false) goto L9;
        int r104 = R.string.CommunityInviteOnlyChannelInfo;
    L10:
        r116.append(LocaleController.getString(r104));
        CommunityPendingInviteOnlyCell.m21571$$Nest$fgettextView(this.cell).setTextColor(getThemedColor(r122));
        CommunityPendingInviteOnlyCell.m21571$$Nest$fgettextView(this.cell).setText(r116);
        this.containerView.addView(this.messageButton, LayoutHelper.createFrameMarginPx(-1, 48.0f, 80, AndroidUtilities.dp(12.0f) + this.backgroundPaddingLeft, 0, AndroidUtilities.dp(12.0f) + this.backgroundPaddingLeft, AndroidUtilities.dp(70.0f) + AndroidUtilities.navigationBarHeight));
        this.containerView.addView(this.cancelButton, LayoutHelper.createFrameMarginPx(-1, 48.0f, 80, AndroidUtilities.dp(12.0f) + this.backgroundPaddingLeft, 0, AndroidUtilities.dp(12.0f) + this.backgroundPaddingLeft, AndroidUtilities.dp(12.0f) + AndroidUtilities.navigationBarHeight));
        this.adapter.update(false);
        return;
    L9:
        r104 = R.string.CommunityInviteOnlyGroupInfo;
        goto L10
    L5:
        r132 = R.string.CommunityInviteOnlyGroupMessageOwner;
        goto L6
    }

    private void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        r1.add(UItem.asCustom(0, this.cell));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(Runnable r1, View r2) {
        r1.run();
        dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new CommunityInviteOnlySheet$$ExternalSyntheticLambda2(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return null;
    }
}
