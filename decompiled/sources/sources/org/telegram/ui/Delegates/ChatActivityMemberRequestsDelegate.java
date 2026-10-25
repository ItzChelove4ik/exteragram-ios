package org.telegram.ui.Delegates;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.AvatarsImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MemberRequestsBottomSheet;

/* loaded from: classes7.dex */
public class ChatActivityMemberRequestsDelegate {
    private AvatarsImageView avatarsView;
    private MemberRequestsBottomSheet bottomSheet;
    private TLRPC.ChatFull chatInfo;
    private int closePendingRequestsCount;
    private ImageView closeView;
    private final int currentAccount;
    private final TLRPC.Chat currentChat;
    private ChangeVisibilityDelegate delegate;
    private final BaseFragment fragment;
    private int pendingRequestsCount;
    private TextView requestsCountTextView;
    private LinearLayout requestsDataLayout;
    public FrameLayout root;

    /* renamed from: org.telegram.ui.Delegates.ChatActivityMemberRequestsDelegate$1, reason: invalid class name */
    public class AnonymousClass1 extends AvatarsImageView {
        final /* synthetic */ ChatActivityMemberRequestsDelegate this$0;

        public AnonymousClass1(ChatActivityMemberRequestsDelegate r1, Context r2, boolean r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.AvatarsImageView, android.view.View
        public void onMeasure(int r2, int r3) {
            int r22 = this.avatarsDrawable.count;
            if (r22 != 0) goto L5;
            int r23 = 0;
        L6:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r23), TLObject.FLAG_30), r3);
            return;
        L5:
            r23 = ((r22 - 1) * 20) + 24;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Delegates.ChatActivityMemberRequestsDelegate$2, reason: invalid class name */
    public class AnonymousClass2 extends MemberRequestsBottomSheet {
        final /* synthetic */ ChatActivityMemberRequestsDelegate this$0;

        public AnonymousClass2(ChatActivityMemberRequestsDelegate r1, BaseFragment r2, long r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.UsersAlertBase, org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            if (ChatActivityMemberRequestsDelegate.m14276$$Nest$fgetbottomSheet(this.this$0) != null) goto L5;
        L7:
            super.dismiss();
            return;
        L5:
            if (ChatActivityMemberRequestsDelegate.m14276$$Nest$fgetbottomSheet(this.this$0).isNeedRestoreDialog() == true) goto L7;
            ChatActivityMemberRequestsDelegate.m14277$$Nest$fputbottomSheet(this.this$0, null);
            goto L7
        }
    }

    public interface ChangeVisibilityDelegate {
        void setVisible(boolean r1, boolean r2);
    }

    public static /* synthetic */ void $r8$lambda$bUQ7dcNPCMokuKN_xBQ_uWUNG28(ChatActivityMemberRequestsDelegate r0, View r1) {
        r0.lambda$getView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$yBib9swDhdUn1NI5GYidRUtdkfI(ChatActivityMemberRequestsDelegate r0, View r1) {
        r0.lambda$getView$1(r1);
    }

    /* renamed from: -$$Nest$fgetbottomSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MemberRequestsBottomSheet m14276$$Nest$fgetbottomSheet(ChatActivityMemberRequestsDelegate r0) {
        return r0.bottomSheet;
    }

    /* renamed from: -$$Nest$fputbottomSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14277$$Nest$fputbottomSheet(ChatActivityMemberRequestsDelegate r0, MemberRequestsBottomSheet r1) {
        r0.bottomSheet = r1;
    }

    public ChatActivityMemberRequestsDelegate(BaseFragment r2, TLRPC.Chat r3) {
        this.closePendingRequestsCount = -1;
        this.fragment = r2;
        this.currentChat = r3;
        this.currentAccount = r2.getCurrentAccount();
    }

    private void animatePendingRequests(boolean r5, boolean r6) {
        if (this.root.getVisibility() != 0) goto L5;
        boolean r0 = true;
    L6:
        if (r5 == r0) goto L26;
        if (r5 == true) goto L10;
    L21:
        ChangeVisibilityDelegate r4 = this.delegate;
        if (r4 == null) goto L25;
        r4.setVisible(r5, r6);
        return;
    L25:
        return;
    L10:
        if (this.closePendingRequestsCount == (-1)) goto L12;
    L14:
        int r02 = this.pendingRequestsCount;
        int r2 = this.closePendingRequestsCount;
        if (r02 == r2) goto L27;
        if (r2 == 0) goto L21;
        if (this.currentChat == null) goto L21;
        this.fragment.getMessagesController().setChatPendingRequestsOnClose(this.currentChat.id, 0);
        goto L21
    L27:
        return;
    L12:
        if (this.currentChat == null) goto L14;
        this.closePendingRequestsCount = this.fragment.getMessagesController().getChatPendingRequestsOnClosed(this.currentChat.id);
        goto L14
    L26:
        return;
    L5:
        r0 = false;
        goto L6
    }

    private /* synthetic */ void lambda$getView$0(View r1) {
        showBottomSheet();
    }

    private /* synthetic */ void lambda$getView$1(View r4) {
        this.fragment.getMessagesController().setChatPendingRequestsOnClose(this.currentChat.id, this.pendingRequestsCount);
        this.closePendingRequestsCount = this.pendingRequestsCount;
        animatePendingRequests(false, true);
    }

    private void setPendingRequests(int r5, List<Long> r6, boolean r7) {
        if (this.root == null) goto L32;
        int r0 = 0;
        if (r5 > 0) goto L13;
        if (this.currentChat == null) goto L10;
        this.fragment.getMessagesController().setChatPendingRequestsOnClose(this.currentChat.id, 0);
        this.closePendingRequestsCount = 0;
    L10:
        animatePendingRequests(false, r7);
        this.pendingRequestsCount = 0;
        return;
    L13:
        if (this.pendingRequestsCount == r5) goto L29;
        this.pendingRequestsCount = r5;
        this.requestsCountTextView.setText(LocaleController.formatPluralString("JoinUsersRequests", r5, new Object[0]));
        animatePendingRequests(true, r7);
        if (r6 != null) goto L17;
        return;
    L17:
        if (r6.isEmpty() == true) goto L31;
        int r72 = Math.min(3, r6.size());
    L19:
        if (r0 >= r72) goto L24;
        TLRPC.User r1 = this.fragment.getMessagesController().getUser(r6.get(r0));
        if (r1 == null) goto L23;
        this.avatarsView.setObject(r0, this.currentAccount, r1);
    L23:
        r0 = r0 + 1;
        goto L19
    L24:
        this.avatarsView.setCount(r72);
        this.avatarsView.commitTransition(true);
        return;
    L31:
        return;
    L29:
        return;
    }

    private void showBottomSheet() {
        if (this.bottomSheet != null) goto L5;
        this.bottomSheet = new AnonymousClass2(this, this.fragment, this.currentChat.id);
    L5:
        this.fragment.showDialog(this.bottomSheet);
    }

    public void fillThemeDescriptions(List<ThemeDescription> r10) {
        r10.add(new ThemeDescription(this.requestsCountTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_topPanelTitle));
        r10.add(new ThemeDescription(this.closeView, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_topPanelClose));
    }

    public View getView() {
        if (this.root != null) goto L8;
        FrameLayout r0 = new FrameLayout(this.fragment.getParentActivity());
        this.root = r0;
        r0.setBackground(Theme.getSelectorDrawable(false));
        this.root.setOnClickListener(new ChatActivityMemberRequestsDelegate$$ExternalSyntheticLambda0(this));
        LinearLayout r02 = new LinearLayout(this.fragment.getParentActivity());
        this.requestsDataLayout = r02;
        r02.setOrientation(0);
        this.root.addView(this.requestsDataLayout, LayoutHelper.createFrame(-1, -1.0f, 48, 0.0f, 0.0f, 100.0f, 2.0f));
        AnonymousClass1 r03 = new AnonymousClass1(this, this.fragment.getParentActivity(), false);
        this.avatarsView = r03;
        r03.setAvatarsTextSize(AndroidUtilities.dp(18.0f));
        this.avatarsView.reset();
        this.requestsDataLayout.addView(this.avatarsView, LayoutHelper.createFrame(-2, -1.0f, 48, 8.0f, 0.0f, 10.0f, 0.0f));
        TextView r04 = new TextView(this.fragment.getParentActivity());
        this.requestsCountTextView = r04;
        r04.setEllipsize(TextUtils.TruncateAt.END);
        this.requestsCountTextView.setGravity(16);
        this.requestsCountTextView.setSingleLine();
        this.requestsCountTextView.setText(null);
        this.requestsCountTextView.setTextColor(this.fragment.getThemedColor(Theme.key_chat_topPanelTitle));
        this.requestsCountTextView.setTypeface(AndroidUtilities.bold());
        this.requestsDataLayout.addView(this.requestsCountTextView, LayoutHelper.createFrame(-1, -1.0f, 48, 0.0f, 0.0f, 0.0f, 0.0f));
        ImageView r05 = new ImageView(this.fragment.getParentActivity());
        this.closeView = r05;
        r05.setBackground(Theme.createSelectorDrawable(this.fragment.getThemedColor(Theme.key_inappPlayerClose) & 436207615, 1, AndroidUtilities.dp(14.0f)));
        this.closeView.setColorFilter(new PorterDuffColorFilter(this.fragment.getThemedColor(Theme.key_chat_topPanelClose), PorterDuff.Mode.MULTIPLY));
        this.closeView.setContentDescription(LocaleController.getString(R.string.Close));
        this.closeView.setImageResource(R.drawable.miniplayer_close);
        this.closeView.setScaleType(ImageView.ScaleType.CENTER);
        this.closeView.setOnClickListener(new ChatActivityMemberRequestsDelegate$$ExternalSyntheticLambda1(this));
        this.root.addView(this.closeView, LayoutHelper.createFrame(36, -1.0f, 21, 0.0f, 0.0f, 4.0f, 1.0f));
        TLRPC.ChatFull r06 = this.chatInfo;
        if (r06 == null) goto L8;
        setPendingRequests(r06.requests_pending, r06.recent_requesters, false);
    L8:
        return this.root;
    }

    public void onBackToScreen() {
        MemberRequestsBottomSheet r0 = this.bottomSheet;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isNeedRestoreDialog() == false) goto L9;
        showBottomSheet();
        return;
    }

    public void setChatInfo(TLRPC.ChatFull r2, boolean r3) {
        this.chatInfo = r2;
        if (r2 == null) goto L6;
        setPendingRequests(r2.requests_pending, r2.recent_requesters, r3);
        return;
    }

    public void setDelegate(ChangeVisibilityDelegate r1) {
        this.delegate = r1;
    }
}
