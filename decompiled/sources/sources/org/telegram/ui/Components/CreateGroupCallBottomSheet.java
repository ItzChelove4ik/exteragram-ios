package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.JoinCallAlert;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class CreateGroupCallBottomSheet extends BottomSheetWithRecyclerListView {
    private final boolean canRtmpStream;
    private final List<TLRPC.Peer> chats;
    private final long dialogId;
    private final BaseFragment fragment;
    private final boolean isChannelOrGiga;
    private boolean isScheduleSelected;
    private final JoinCallAlert.JoinCallAlertDelegate joinCallDelegate;
    private final boolean needSelector;
    private TLRPC.InputPeer selectAfterDismiss;
    private TLRPC.Peer selectedPeer;

    /* renamed from: org.telegram.ui.Components.CreateGroupCallBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ CreateGroupCallBottomSheet this$0;

        public AnonymousClass1(CreateGroupCallBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            if (CreateGroupCallBottomSheet.m10058$$Nest$fgetneedSelector(this.this$0) == false) goto L6;
            r8.drawRect(CreateGroupCallBottomSheet.access$000(this.this$0), 0.0f, getMeasuredWidth() - CreateGroupCallBottomSheet.access$100(this.this$0), 1.0f, Theme.dividerPaint);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.CreateGroupCallBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ CreateGroupCallBottomSheet this$0;

        /* renamed from: $r8$lambda$coti8T4md5-0KYDUY-SQJqDGCJw, reason: not valid java name */
        public static /* synthetic */ void m10060$r8$lambda$coti8T4md50KYDUYSQJqDGCJw(AnonymousClass2 r0) {
            r0.lambda$onBindViewHolder$0();
        }

        public AnonymousClass2(CreateGroupCallBottomSheet r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onBindViewHolder$0() {
            TLRPC.Peer r1 = CreateGroupCallBottomSheet.m10059$$Nest$fgetselectedPeer(this.this$0);
            BaseFragment r2 = CreateGroupCallBottomSheet.m10055$$Nest$fgetfragment(this.this$0);
            long r3 = CreateGroupCallBottomSheet.m10054$$Nest$fgetdialogId(this.this$0);
            boolean r5 = true;
            if (CreateGroupCallBottomSheet.m10053$$Nest$fgetchats(this.this$0).size() > 1) goto L6;
            r5 = false;
        L6:
            CreateRtmpStreamBottomSheet.show(r1, r2, r3, r5, CreateGroupCallBottomSheet.m10057$$Nest$fgetjoinCallDelegate(this.this$0));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            boolean r0 = CreateGroupCallBottomSheet.m10058$$Nest$fgetneedSelector(this.this$0);
            CreateGroupCallBottomSheet r1 = this.this$0;
            if (r0 == false) goto L7;
            return CreateGroupCallBottomSheet.m10053$$Nest$fgetchats(r1).size() + 3;
        L7:
            if (CreateGroupCallBottomSheet.m10052$$Nest$fgetcanRtmpStream(r1) == false) goto L10;
            return 2;
        L10:
            return 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L4;
            return 0;
        L4:
            if (r1 != 1) goto L6;
            return 1;
        L6:
            if (r1 == 2) goto L12;
            return 3;
        L12:
            return 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 3) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            boolean r2 = true;
            if (r9.getItemViewType() != 3) goto L19;
            TLRPC.Peer r0 = (TLRPC.Peer) CreateGroupCallBottomSheet.m10053$$Nest$fgetchats(this.this$0).get(r10 - 3);
            long r4 = MessageObject.getPeerId(r0);
            CreateGroupCallBottomSheet r6 = this.this$0;
            if (r4 <= 0) goto L7;
            TLObject r1 = MessagesController.getInstance(CreateGroupCallBottomSheet.access$300(r6)).getUser(Long.valueOf(r4));
            String r42 = LocaleController.getString(R.string.VoipGroupPersonalAccount);
        L8:
            GroupCreateUserCell r92 = (GroupCreateUserCell) r9.itemView;
            if (r10 == (getItemCount() - 1)) goto L11;
            boolean r102 = true;
        L12:
            r92.setObject(r1, null, r42, r102);
            if (r0 == CreateGroupCallBottomSheet.m10059$$Nest$fgetselectedPeer(this.this$0)) goto L16;
            r2 = false;
        L16:
            r92.setChecked(r2, false);
            return;
        L11:
            r102 = false;
            goto L12
        L7:
            r1 = MessagesController.getInstance(CreateGroupCallBottomSheet.access$400(r6)).getChat(Long.valueOf(-r4));
            r42 = null;
            goto L8
        L19:
            if (r9.getItemViewType() != 2) goto L23;
            HeaderCell r8 = (HeaderCell) r9.itemView;
            r8.setTextSize(15.0f);
            r8.setPadding(0, 0, 0, AndroidUtilities.dp(2.0f));
            r8.setText(LocaleController.getString(R.string.VoipChatDisplayedAs).replace(":", _UrlKt.FRAGMENT_ENCODE_SET));
            return;
        L23:
            if (r9.getItemViewType() != 1) goto L26;
            ((TextInfoPrivacyCell) r9.itemView).setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.VoipChatStreamWithAnotherApp), Theme.key_windowBackgroundWhiteBlueHeader, 0, new CreateGroupCallBottomSheet$2$$ExternalSyntheticLambda0(this)), true, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f)));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            Context r32 = r3.getContext();
            if (r4 != 1) goto L5;
            TextInfoPrivacyCell r42 = new TextInfoPrivacyCell(r32);
            r42.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, CreateGroupCallBottomSheet.access$200(this.this$0)));
            r42.setTopPadding(17);
            r42.setBottomPadding(17);
            View r43 = r42;
        L12:
            r43.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r43);
        L5:
            if (r4 != 2) goto L7;
            r43 = new HeaderCell(r32, 22);
            goto L12
        L7:
            if (r4 == 3) goto L9;
            r43 = new TopCell(r32, CreateGroupCallBottomSheet.m10056$$Nest$fgetisChannelOrGiga(this.this$0));
            goto L12
        L9:
            r43 = new GroupCreateUserCell(r32, 1, 0, false);
            goto L12
        }
    }

    public static class TopCell extends LinearLayout {
        public TopCell(Context r12, boolean r13) {
            super(r12);
            setOrientation(1);
            RLottieImageView r1 = new RLottieImageView(r12);
            r1.setAutoRepeat(true);
            r1.setAnimation(R.raw.utyan_schedule, 112, 112);
            r1.playAnimation();
            addView(r1, LayoutHelper.createLinear(112, 112, 49, 0, 24, 0, 0));
            TextView r14 = new TextView(r12);
            r14.setTypeface(AndroidUtilities.bold());
            if (r13 == false) goto L5;
            String r3 = LocaleController.formatString(R.string.StartVoipChannelTitle, new Object[0]);
        L6:
            r14.setText(r3);
            r14.setTextSize(1, 20.0f);
            r14.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            addView(r14, LayoutHelper.createLinear(-2, -2, 1, 0, 14, 0, 7));
            TextView r15 = new TextView(r12);
            r15.setTextSize(1, 14.0f);
            r15.setGravity(1);
            r15.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
            if (r13 == false) goto L9;
            String r122 = LocaleController.formatString(R.string.VoipChannelStart2, new Object[0]);
        L10:
            r15.setText(r122);
            r15.setLineSpacing(r15.getLineSpacingExtra(), r15.getLineSpacingMultiplier() * 1.1f);
            addView(r15, LayoutHelper.createLinear(-2, -2, 1, 28, 0, 28, 17));
            return;
        L9:
            r122 = LocaleController.formatString(R.string.VoipGroupStart2, new Object[0]);
            goto L10
        L5:
            r3 = LocaleController.formatString(R.string.StartVoipChatTitle, new Object[0]);
            goto L6
        }
    }

    /* renamed from: $r8$lambda$REC7Ifag-bfvB2esLGB5OlaabPM, reason: not valid java name */
    public static /* synthetic */ void m10050$r8$lambda$REC7IfagbfvB2esLGB5OlaabPM(CreateGroupCallBottomSheet r0, View r1, int r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: $r8$lambda$S_smijtur7AOAqjl-wbpCRKTDV0, reason: not valid java name */
    public static /* synthetic */ void m10051$r8$lambda$S_smijtur7AOAqjlwbpCRKTDV0(CreateGroupCallBottomSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$jFdbpdR6potrZq2n1yZUZ3a25r4(CreateGroupCallBottomSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: -$$Nest$fgetcanRtmpStream, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10052$$Nest$fgetcanRtmpStream(CreateGroupCallBottomSheet r0) {
        return r0.canRtmpStream;
    }

    /* renamed from: -$$Nest$fgetchats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m10053$$Nest$fgetchats(CreateGroupCallBottomSheet r0) {
        return r0.chats;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m10054$$Nest$fgetdialogId(CreateGroupCallBottomSheet r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m10055$$Nest$fgetfragment(CreateGroupCallBottomSheet r0) {
        return r0.fragment;
    }

    /* renamed from: -$$Nest$fgetisChannelOrGiga, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10056$$Nest$fgetisChannelOrGiga(CreateGroupCallBottomSheet r0) {
        return r0.isChannelOrGiga;
    }

    /* renamed from: -$$Nest$fgetjoinCallDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ JoinCallAlert.JoinCallAlertDelegate m10057$$Nest$fgetjoinCallDelegate(CreateGroupCallBottomSheet r0) {
        return r0.joinCallDelegate;
    }

    /* renamed from: -$$Nest$fgetneedSelector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10058$$Nest$fgetneedSelector(CreateGroupCallBottomSheet r0) {
        return r0.needSelector;
    }

    /* renamed from: -$$Nest$fgetselectedPeer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Peer m10059$$Nest$fgetselectedPeer(CreateGroupCallBottomSheet r0) {
        return r0.selectedPeer;
    }

    public CreateGroupCallBottomSheet(BaseFragment r23, ArrayList<TLRPC.Peer> r24, long r25, JoinCallAlert.JoinCallAlertDelegate r27) {
        super(r23, false, false);
        TLRPC.Chat r5 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r25));
        this.fragment = r23;
        this.dialogId = r25;
        this.topPadding = 0.26f;
        ArrayList r1 = new ArrayList(r24);
        this.chats = r1;
        this.joinCallDelegate = r27;
        boolean r2 = ChatObject.isChannelOrGiga(r5);
        this.isChannelOrGiga = r2;
        this.selectedPeer = (TLRPC.Peer) r1.get(0);
        if (r1.size() <= 1) goto L5;
        boolean r12 = true;
    L6:
        this.needSelector = r12;
        this.canRtmpStream = ChatObject.canManageCalls(r5);
        Context r13 = this.containerView.getContext();
        this.containerView.addView(new AnonymousClass1(this, r13), LayoutHelper.createFrame(-1, 120.0f, 80, 0.0f, 0.0f, 0.0f, 0.0f));
        TextView r52 = new TextView(r13);
        r52.setGravity(17);
        TextUtils.TruncateAt r7 = TextUtils.TruncateAt.END;
        r52.setEllipsize(r7);
        r52.setSingleLine(true);
        r52.setTextSize(1, 14.0f);
        r52.setTypeface(AndroidUtilities.bold());
        if (r2 == false) goto L9;
        String r9 = LocaleController.formatString(R.string.VoipChannelStartVoiceChat, new Object[0]);
    L10:
        r52.setText(r9);
        r52.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        int r10 = AndroidUtilities.dp(8.0f);
        int r11 = Theme.key_featuredStickers_addButton;
        r52.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r10, Theme.getColor(r11), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhite), 120)));
        this.containerView.addView(r52, LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 0.0f, 16.0f, 60.0f));
        TextView r102 = new TextView(r13);
        r102.setGravity(17);
        r102.setEllipsize(r7);
        r102.setSingleLine(true);
        r102.setTextSize(1, 14.0f);
        r102.setTypeface(AndroidUtilities.bold());
        if (r2 == false) goto L13;
        String r14 = LocaleController.formatString(R.string.VoipChannelScheduleVoiceChat, new Object[0]);
    L14:
        r102.setText(r14);
        r102.setLetterSpacing(0.025f);
        r102.setTextColor(Theme.getColor(r11));
        r102.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(r11), 120)));
        this.containerView.addView(r102, LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 0.0f, 16.0f, 6.0f));
        r52.setOnClickListener(new CreateGroupCallBottomSheet$$ExternalSyntheticLambda0(this));
        r102.setOnClickListener(new CreateGroupCallBottomSheet$$ExternalSyntheticLambda1(this));
        RecyclerListView r15 = this.recyclerListView;
        int r22 = this.backgroundPaddingLeft;
        r15.setPadding(r22, 0, r22, AndroidUtilities.dp(120.0f));
        this.recyclerListView.setOnItemClickListener(new CreateGroupCallBottomSheet$$ExternalSyntheticLambda2(this));
        fixNavigationBar();
        updateTitle();
        return;
    L13:
        r14 = LocaleController.formatString(R.string.VoipGroupScheduleVoiceChat, new Object[0]);
        goto L14
    L9:
        r9 = LocaleController.formatString(R.string.VoipGroupStartVoiceChat, new Object[0]);
        goto L10
    L5:
        r12 = false;
        goto L6
    }

    public static /* synthetic */ int access$000(CreateGroupCallBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$100(CreateGroupCallBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(CreateGroupCallBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$300(CreateGroupCallBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(CreateGroupCallBottomSheet r0) {
        return r0.currentAccount;
    }

    private /* synthetic */ void lambda$new$0(View r3) {
        this.selectAfterDismiss = MessagesController.getInstance(this.currentAccount).getInputPeer(MessageObject.getPeerId(this.selectedPeer));
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(View r3) {
        this.selectAfterDismiss = MessagesController.getInstance(this.currentAccount).getInputPeer(MessageObject.getPeerId(this.selectedPeer));
        this.isScheduleSelected = true;
        dismiss();
    }

    private /* synthetic */ void lambda$new$2(View r5, int r6) {
        if (r6 <= 3) goto L17;
        this.selectedPeer = this.chats.get(r6 - 4);
        if ((r5 instanceof GroupCreateUserCell) == false) goto L8;
        ((GroupCreateUserCell) r5).setChecked(true, true);
    L8:
        int r1 = 0;
    L10:
        if (r1 >= this.recyclerListView.getChildCount()) goto L22;
        View r2 = this.recyclerListView.getChildAt(r1);
        if (r2 == r5) goto L16;
        if ((r2 instanceof GroupCreateUserCell) == false) goto L16;
        ((GroupCreateUserCell) r2).setChecked(false, true);
    L16:
        r1 = r1 + 1;
        goto L10
    L22:
        return;
    }

    public static void show(ArrayList<TLRPC.Peer> r7, BaseFragment r8, long r9, JoinCallAlert.JoinCallAlertDelegate r11) {
        if (r7.isEmpty() == false) goto L5;
        return;
    L5:
        CreateGroupCallBottomSheet r1 = new CreateGroupCallBottomSheet(r8, r7, r9, r11);
        if (r8 != null) goto L8;
    L11:
        r1.show();
        return;
    L8:
        if (r8.getParentActivity() == null) goto L11;
        r8.showDialog(r1);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r1) {
        return new AnonymousClass2(this);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        TLRPC.InputPeer r0 = this.selectAfterDismiss;
        if (r0 == null) goto L10;
        JoinCallAlert.JoinCallAlertDelegate r1 = this.joinCallDelegate;
        boolean r4 = true;
        if (this.chats.size() > 1) goto L8;
        r4 = false;
    L8:
        r1.didSelectChat(r0, r4, this.isScheduleSelected, false);
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        if (this.isChannelOrGiga == false) goto L7;
        return LocaleController.getString(R.string.StartVoipChannelTitle);
    L7:
        return LocaleController.getString(R.string.StartVoipChatTitle);
    }
}
