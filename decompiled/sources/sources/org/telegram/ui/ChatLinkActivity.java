package org.telegram.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.ManageChatTextCell;
import org.telegram.ui.Cells.ManageChatUserCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.JoinToSendSettingsView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingStickerDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.GroupCreateFinalActivity;

/* loaded from: classes6.dex */
public class ChatLinkActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private int chatEndRow;
    private int chatStartRow;
    private ArrayList<TLRPC.Chat> chats;
    private boolean chatsLoaded;
    private int createChatRow;
    private TLRPC.Chat currentChat;
    private long currentChatId;
    private int detailRow;
    private EmptyTextProgressView emptyView;
    private int helpRow;
    private TLRPC.ChatFull info;
    private boolean isChannel;
    private boolean joinRequestProgress;
    private int joinToSendInfoRow;
    private boolean joinToSendProgress;
    private int joinToSendRow;
    private JoinToSendSettingsView joinToSendSettings;
    private RecyclerListView listView;
    private ListAdapter listViewAdapter;
    private boolean loadingChats;
    private int removeChatRow;
    private int rowCount;
    private SearchAdapter searchAdapter;
    private ActionBarMenuItem searchItem;
    private boolean searchWas;
    private boolean searching;
    private TLRPC.Chat waitingForFullChat;
    private AlertDialog waitingForFullChatProgressAlert;

    /* renamed from: org.telegram.ui.ChatLinkActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChatLinkActivity this$0;

        public AnonymousClass1(ChatLinkActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatLinkActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ ChatLinkActivity this$0;

        public AnonymousClass2(ChatLinkActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            ChatLinkActivity.m8368$$Nest$fgetsearchAdapter(this.this$0).searchDialogs(null);
            ChatLinkActivity.m8376$$Nest$fputsearching(this.this$0, false);
            ChatLinkActivity.m8375$$Nest$fputsearchWas(this.this$0, false);
            ChatLinkActivity.m8363$$Nest$fgetlistView(this.this$0).setAdapter(ChatLinkActivity.m8364$$Nest$fgetlistViewAdapter(this.this$0));
            ChatLinkActivity.m8364$$Nest$fgetlistViewAdapter(this.this$0).notifyDataSetChanged();
            ChatLinkActivity.m8363$$Nest$fgetlistView(this.this$0).setFastScrollVisible(true);
            ChatLinkActivity.m8363$$Nest$fgetlistView(this.this$0).setVerticalScrollBarEnabled(false);
            ChatLinkActivity.m8355$$Nest$fgetemptyView(this.this$0).setShowAtCenter(false);
            View r0 = this.this$0.fragmentView;
            int r1 = Theme.key_windowBackgroundGray;
            r0.setBackgroundColor(Theme.getColor(r1));
            this.this$0.fragmentView.setTag(Integer.valueOf(r1));
            ChatLinkActivity.m8355$$Nest$fgetemptyView(this.this$0).showProgress();
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            ChatLinkActivity.m8376$$Nest$fputsearching(this.this$0, true);
            ChatLinkActivity.m8355$$Nest$fgetemptyView(this.this$0).setShowAtCenter(true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r5) {
            if (ChatLinkActivity.m8368$$Nest$fgetsearchAdapter(this.this$0) != null) goto L5;
            return;
        L5:
            String r52 = r5.getText().toString();
            if (r52.length() == 0) goto L12;
            ChatLinkActivity.m8375$$Nest$fputsearchWas(this.this$0, true);
            if (ChatLinkActivity.m8363$$Nest$fgetlistView(this.this$0) == null) goto L12;
            if (ChatLinkActivity.m8363$$Nest$fgetlistView(this.this$0).getAdapter() == ChatLinkActivity.m8368$$Nest$fgetsearchAdapter(this.this$0)) goto L12;
            ChatLinkActivity.m8363$$Nest$fgetlistView(this.this$0).setAdapter(ChatLinkActivity.m8368$$Nest$fgetsearchAdapter(this.this$0));
            View r0 = this.this$0.fragmentView;
            int r2 = Theme.key_windowBackgroundWhite;
            r0.setBackgroundColor(Theme.getColor(r2));
            this.this$0.fragmentView.setTag(Integer.valueOf(r2));
            ChatLinkActivity.m8368$$Nest$fgetsearchAdapter(this.this$0).notifyDataSetChanged();
            ChatLinkActivity.m8363$$Nest$fgetlistView(this.this$0).setFastScrollVisible(false);
            ChatLinkActivity.m8363$$Nest$fgetlistView(this.this$0).setVerticalScrollBarEnabled(true);
            ChatLinkActivity.m8355$$Nest$fgetemptyView(this.this$0).showProgress();
        L12:
            ChatLinkActivity.m8368$$Nest$fgetsearchAdapter(this.this$0).searchDialogs(r52);
        }
    }

    /* renamed from: org.telegram.ui.ChatLinkActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements GroupCreateFinalActivity.GroupCreateFinalActivityDelegate {
        final /* synthetic */ ChatLinkActivity this$0;

        public AnonymousClass3(ChatLinkActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.GroupCreateFinalActivity.GroupCreateFinalActivityDelegate
        public void didFailChatCreation() {
        }

        @Override // org.telegram.ui.GroupCreateFinalActivity.GroupCreateFinalActivityDelegate
        public void didFinishChatCreation(GroupCreateFinalActivity r2, long r3) {
            ChatLinkActivity r1 = this.this$0;
            ChatLinkActivity.m8377$$Nest$mlinkChat(r1, r1.getMessagesController().getChat(Long.valueOf(r3)), r2);
        }

        @Override // org.telegram.ui.GroupCreateFinalActivity.GroupCreateFinalActivityDelegate
        public void didStartChatCreation() {
        }
    }

    public static class EmptyView extends LinearLayout implements NotificationCenter.NotificationCenterDelegate {
        private int currentAccount;
        private LoadingStickerDrawable drawable;
        private BackupImageView stickerView;

        public EmptyView(Context r8) {
            super(r8);
            this.currentAccount = UserConfig.selectedAccount;
            setPadding(0, AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f));
            setOrientation(1);
            this.stickerView = new BackupImageView(r8);
            LoadingStickerDrawable r82 = new LoadingStickerDrawable(this.stickerView, "M476.1,397.4c25.8-47.2,0.3-105.9-50.9-120c-2.5-6.9-7.8-12.7-15-16.4l0.4-229.4c0-12.3-10-22.4-22.4-22.4H128.5c-12.3,0-22.4,10-22.4,22.4l-0.4,229.8v0c0,6.7,2.9,12.6,7.6,16.7c-51.6,15.9-79.2,77.2-48.1,116.4c-8.7,11.7-13.4,27.5-14,47.2c-1.7,34.5,21.6,45.8,55.9,45.8c52.3,0,99.1,4.6,105.1-36.2c16.5,0.9,7.1-37.3-6.5-53.3c18.4-22.4,18.3-52.9,4.9-78.2c-0.7-5.3-3.8-9.8-8.1-12.6c-1.5-2-1.6-2-2.1-2.7c0.2-1,1.2-11.8-3.4-20.9h138.5c-4.8,8.8-4.7,17-2.9,22.1c-5.3,4.8-6.8,12.3-5.2,17c-11.4,24.9-10,53.8,4.3,77.5c-6.8,9.7-11.2,21.7-12.6,31.6c-0.2-0.2-0.4-0.3-0.6-0.5c0.8-3.3,0.4-6.4-1.3-7.8c9.3-12.1-4.5-29.2-17-21.7c-3.8-2.8-10.6-3.2-18.1-0.5c-2.4-10.6-21.1-10.6-28.6-1c-1.3,0.3-2.9,0.8-4.5,1.9c-5.2-0.9-10.9,0.1-14.1,4.4c-6.9,3-9.5,10.4-7.8,17c-0.9,1.8-1.1,4-0.8,6.3c-1.6,1.2-2.3,3.1-2,4.9c0.1,0.6,10.4,56.6,11.2,62c0.3,1.8,1.5,3.2,3.1,3.9c8.7,3.4,12,3.8,30.1,9.4c2.7,0.8,2.4,0.8,6.7-0.1c16.4-3.5,30.2-8.9,30.8-9.2c1.6-0.6,2.7-2,3.1-3.7c0.1-0.4,6.8-36.5,10-53.2c0.9,4.2,3.3,7.3,7.4,7.5c1.2,7.8,4.4,14.5,9.5,19.9c16.4,17.3,44.9,15.7,64.9,16.1c38.3,0.8,74.5,1.5,84.4-24.4C488.9,453.5,491.3,421.3,476.1,397.4z", AndroidUtilities.dp(104.0f), AndroidUtilities.dp(104.0f));
            this.drawable = r82;
            this.stickerView.setImageDrawable(r82);
            addView(this.stickerView, LayoutHelper.createLinear(104, 104, 49, 0, 2, 0, 0));
        }

        private void setSticker() {
            TLRPC.TL_messages_stickerSet r0 = MediaDataController.getInstance(this.currentAccount).getStickerSetByName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME);
            if (r0 != null) goto L5;
            r0 = MediaDataController.getInstance(this.currentAccount).getStickerSetByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME);
        L5:
            TLRPC.TL_messages_stickerSet r7 = r0;
            if (r7 != null) goto L8;
        L11:
            MediaDataController r02 = MediaDataController.getInstance(this.currentAccount);
            if (r7 != null) goto L14;
            boolean r3 = true;
        L15:
            r02.loadStickersByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME, false, r3);
            this.stickerView.setImageDrawable(this.drawable);
            return;
        L14:
            r3 = false;
            goto L15
        L8:
            if (r7.documents.size() < 3) goto L11;
            this.stickerView.setImage(ImageLocation.getForDocument(r7.documents.get(2)), "104_104", "tgs", this.drawable, r7);
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 == NotificationCenter.diceStickersDidLoad) goto L5;
            return;
        L5:
            if (AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME.equals((String) r3[0]) == false) goto L9;
            setSticker();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            setSticker();
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.diceStickersDidLoad);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.diceStickersDidLoad);
        }
    }

    public class HintInnerCell extends FrameLayout {
        private EmptyView emptyView;
        private TextView messageTextView;
        final /* synthetic */ ChatLinkActivity this$0;

        public HintInnerCell(ChatLinkActivity r9, Context r10) {
            this.this$0 = r9;
            super(r10);
            EmptyView r0 = new EmptyView(r10);
            this.emptyView = r0;
            addView(r0, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 10.0f, 0.0f, 0.0f));
            TextView r02 = new TextView(r10);
            this.messageTextView = r02;
            r02.setTextColor(Theme.getColor(Theme.key_chats_message));
            this.messageTextView.setTextSize(1, 14.0f);
            this.messageTextView.setGravity(17);
            if (ChatLinkActivity.m8358$$Nest$fgetisChannel(r9) == true) goto L5;
            TLRPC.Chat r92 = r9.getMessagesController().getChat(Long.valueOf(ChatLinkActivity.m8357$$Nest$fgetinfo(r9).linked_chat_id));
            if (r92 == null) goto L15;
            this.messageTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("DiscussionGroupHelp", R.string.DiscussionGroupHelp, new Object[]{r92.title})));
        L15:
            addView(this.messageTextView, LayoutHelper.createFrame(-1, -2.0f, 51, 52.0f, 143.0f, 52.0f, 18.0f));
            return;
        L5:
            if (ChatLinkActivity.m8357$$Nest$fgetinfo(r9) != null) goto L7;
        L11:
            this.messageTextView.setText(LocaleController.getString(R.string.DiscussionChannelHelp3));
            goto L15
        L7:
            if (ChatLinkActivity.m8357$$Nest$fgetinfo(r9).linked_chat_id == 0) goto L11;
            TLRPC.Chat r93 = r9.getMessagesController().getChat(Long.valueOf(ChatLinkActivity.m8357$$Nest$fgetinfo(r9).linked_chat_id));
            if (r93 == null) goto L15;
            this.messageTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("DiscussionChannelGroupSetHelp2", R.string.DiscussionChannelGroupSetHelp2, new Object[]{r93.title})));
            goto L15
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ ChatLinkActivity this$0;

        /* renamed from: org.telegram.ui.ChatLinkActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends JoinToSendSettingsView {
            final /* synthetic */ ListAdapter this$1;
            final /* synthetic */ TLRPC.Chat val$chat;

            /* renamed from: $r8$lambda$-VPnR2xE8ryQaMrG256ryIgu9hg, reason: not valid java name */
            public static /* synthetic */ void m8378$r8$lambda$VPnR2xE8ryQaMrG256ryIgu9hg(AnonymousClass1 r0, Runnable r1) {
                r0.lambda$onJoinToSendToggle$8(r1);
            }

            public static /* synthetic */ void $r8$lambda$1hON528Md6KiWgYhnSpCFhJNuMQ(AnonymousClass1 r0, Runnable r1) {
                r0.lambda$onJoinRequestToggle$2(r1);
            }

            public static /* synthetic */ void $r8$lambda$75L77VbE3BhBSsBrojS_8jwtJ1Y(AnonymousClass1 r0, TLRPC.Chat r1, boolean r2, Runnable r3) {
                r0.lambda$onJoinRequestToggle$3(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$HjDoXZHxzFVk8f60u6VizEN5OYg(AnonymousClass1 r0) {
                r0.lambda$onJoinRequestToggle$1();
            }

            public static /* synthetic */ void $r8$lambda$YnSY9euC0EF6Y1LNGHIPQF0gzlY(AnonymousClass1 r0, TLRPC.Chat r1) {
                r0.lambda$onJoinToSendToggle$6(r1);
            }

            public static /* synthetic */ void $r8$lambda$b6jke_BgExE0X8oUSzxObyZgL2M(AnonymousClass1 r0, Runnable r1) {
                r0.lambda$overrideCancel$4(r1);
            }

            public static /* synthetic */ void $r8$lambda$frkbtJDmJMTuX193cZAU33FatT4(AnonymousClass1 r0, TLRPC.Chat r1, boolean r2, Runnable r3) {
                r0.lambda$onJoinToSendToggle$9(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$isBgo2WuqEE3ZlJoWCTONQfcWUE(AnonymousClass1 r0) {
                r0.lambda$onJoinToSendToggle$5();
            }

            public static /* synthetic */ void $r8$lambda$jGx8XgJ112Glnff3a9UTaISYiLU(AnonymousClass1 r0, boolean r1, TLRPC.Chat r2) {
                r0.lambda$onJoinToSendToggle$7(r1, r2);
            }

            /* renamed from: $r8$lambda$mydHeTevI82prazHmqpXG-ifTOs, reason: not valid java name */
            public static /* synthetic */ void m8379$r8$lambda$mydHeTevI82prazHmqpXGifTOs(AnonymousClass1 r0, Runnable r1, long r2) {
                r0.lambda$migrateIfNeeded$0(r1, r2);
            }

            public AnonymousClass1(ListAdapter r1, Context r2, TLRPC.Chat r3, TLRPC.Chat r4) {
                this.this$1 = r1;
                this.val$chat = r4;
                super(r2, r3);
            }

            private /* synthetic */ void lambda$migrateIfNeeded$0(Runnable r3, long r4) {
                if (r4 == 0) goto L10;
                boolean r0 = ChatLinkActivity.m8358$$Nest$fgetisChannel(this.this$1.this$0);
                ListAdapter r1 = this.this$1;
                if (r0 == false) goto L7;
                ChatLinkActivity.m8350$$Nest$fgetchats(r1.this$0).set(0, this.this$1.this$0.getMessagesController().getChat(Long.valueOf(r4)));
            L8:
                r3.run();
                return;
            L7:
                ChatLinkActivity.m8371$$Nest$fputcurrentChatId(r1.this$0, r4);
                ChatLinkActivity r2 = this.this$1.this$0;
                ChatLinkActivity.m8370$$Nest$fputcurrentChat(r2, r2.getMessagesController().getChat(Long.valueOf(r4)));
                goto L8
            }

            private /* synthetic */ void lambda$onJoinRequestToggle$1() {
                ChatLinkActivity.m8372$$Nest$fputjoinRequestProgress(this.this$1.this$0, false);
            }

            private /* synthetic */ void lambda$onJoinRequestToggle$2(Runnable r2) {
                ChatLinkActivity.m8372$$Nest$fputjoinRequestProgress(this.this$1.this$0, false);
                r2.run();
            }

            private /* synthetic */ void lambda$onJoinRequestToggle$3(TLRPC.Chat r8, boolean r9, Runnable r10) {
                r8.join_request = r9;
                this.this$1.this$0.getMessagesController().toggleChatJoinRequest(r8.id, r9, new ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda3(this), new ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda4(this, r10));
            }

            private /* synthetic */ void lambda$onJoinToSendToggle$5() {
                ChatLinkActivity.m8372$$Nest$fputjoinRequestProgress(this.this$1.this$0, false);
            }

            private /* synthetic */ void lambda$onJoinToSendToggle$6(TLRPC.Chat r2) {
                r2.join_request = true;
                this.isJoinRequest = true;
                this.joinRequestCell.setChecked(true);
            }

            private /* synthetic */ void lambda$onJoinToSendToggle$7(boolean r7, TLRPC.Chat r8) {
                ChatLinkActivity.m8373$$Nest$fputjoinToSendProgress(this.this$1.this$0, false);
                if (r7 == false) goto L5;
                return;
            L5:
                if (r8.join_request == false) goto L9;
                r8.join_request = false;
                ChatLinkActivity.m8372$$Nest$fputjoinRequestProgress(this.this$1.this$0, true);
                this.this$1.this$0.getMessagesController().toggleChatJoinRequest(r8.id, false, new ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda8(this), new ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda9(this, r8));
                return;
            }

            private /* synthetic */ void lambda$onJoinToSendToggle$8(Runnable r2) {
                ChatLinkActivity.m8373$$Nest$fputjoinToSendProgress(this.this$1.this$0, false);
                r2.run();
            }

            private /* synthetic */ void lambda$onJoinToSendToggle$9(TLRPC.Chat r8, boolean r9, Runnable r10) {
                r8.join_to_send = r9;
                this.this$1.this$0.getMessagesController().toggleChatJoinToSend(r8.id, r9, new ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda5(this, r9, r8), new ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda6(this, r10));
            }

            private /* synthetic */ void lambda$overrideCancel$4(Runnable r3) {
                ChatLinkActivity.m8373$$Nest$fputjoinToSendProgress(this.this$1.this$0, false);
                ChatLinkActivity.m8372$$Nest$fputjoinRequestProgress(this.this$1.this$0, false);
                r3.run();
            }

            private void migrateIfNeeded(Runnable r9, Runnable r10) {
                if (ChatObject.isChannel(ChatLinkActivity.m8353$$Nest$fgetcurrentChat(this.this$1.this$0)) == true) goto L6;
                this.this$1.this$0.getMessagesController().convertToMegaGroup(this.this$1.this$0.getParentActivity(), this.val$chat.id, this.this$1.this$0, new ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda2(this, r10), r9);
                return;
            L6:
                r10.run();
            }

            private Runnable overrideCancel(Runnable r2) {
                return new ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda7(this, r2);
            }

            @Override // org.telegram.ui.Components.JoinToSendSettingsView
            public boolean onJoinRequestToggle(boolean r5, Runnable r6) {
                if (ChatLinkActivity.m8359$$Nest$fgetjoinRequestProgress(this.this$1.this$0) == false) goto L6;
                return false;
            L6:
                ChatLinkActivity.m8372$$Nest$fputjoinRequestProgress(this.this$1.this$0, true);
                migrateIfNeeded(overrideCancel(r6), new ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda1(this, this.val$chat, r5, r6));
                return true;
            }

            @Override // org.telegram.ui.Components.JoinToSendSettingsView
            public boolean onJoinToSendToggle(boolean r5, Runnable r6) {
                if (ChatLinkActivity.m8361$$Nest$fgetjoinToSendProgress(this.this$1.this$0) == false) goto L6;
                return false;
            L6:
                ChatLinkActivity.m8373$$Nest$fputjoinToSendProgress(this.this$1.this$0, true);
                migrateIfNeeded(overrideCancel(r6), new ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda0(this, this.val$chat, r5, r6));
                return true;
            }
        }

        public ListAdapter(ChatLinkActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (ChatLinkActivity.m8365$$Nest$fgetloadingChats(this.this$0) == false) goto L9;
            if (ChatLinkActivity.m8351$$Nest$fgetchatsLoaded(this.this$0) == true) goto L9;
            return 0;
        L9:
            return ChatLinkActivity.m8367$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != ChatLinkActivity.m8356$$Nest$fgethelpRow(this.this$0)) goto L7;
            return 3;
        L7:
            if (r2 != ChatLinkActivity.m8352$$Nest$fgetcreateChatRow(this.this$0)) goto L9;
            return 2;
        L9:
            if (r2 != ChatLinkActivity.m8366$$Nest$fgetremoveChatRow(this.this$0)) goto L12;
            return 2;
        L12:
            if (r2 < ChatLinkActivity.m8349$$Nest$fgetchatStartRow(this.this$0)) goto L18;
            if (r2 >= ChatLinkActivity.m8348$$Nest$fgetchatEndRow(this.this$0)) goto L18;
            return 0;
        L18:
            if (r2 != ChatLinkActivity.m8362$$Nest$fgetjoinToSendRow(this.this$0)) goto L21;
            return 4;
        L21:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            int r0 = r1.getItemViewType();
            if (r0 != 0) goto L5;
            return true;
        L5:
            if (r0 == 2) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
            int r0 = r10.getItemViewType();
            boolean r3 = false;
            if (r0 == 0) goto L30;
            if (r0 != 1) goto L6;
            TextInfoPrivacyCell r102 = (TextInfoPrivacyCell) r10.itemView;
            if (r11 != ChatLinkActivity.m8360$$Nest$fgetjoinToSendInfoRow(this.this$0)) goto L23;
            r102.setText(LocaleController.getString(R.string.ChannelSettingsJoinRequestInfo));
            return;
        L23:
            if (r11 == ChatLinkActivity.m8354$$Nest$fgetdetailRow(this.this$0)) goto L25;
            return;
        L25:
            if (ChatLinkActivity.m8358$$Nest$fgetisChannel(this.this$0) == false) goto L28;
            r102.setText(LocaleController.getString(R.string.DiscussionChannelHelp2));
            return;
        L28:
            r102.setText(LocaleController.getString(R.string.DiscussionGroupHelp2));
            return;
        L6:
            if (r0 != 2) goto L42;
            ManageChatTextCell r103 = (ManageChatTextCell) r10.itemView;
            if (ChatLinkActivity.m8358$$Nest$fgetisChannel(this.this$0) == true) goto L11;
            int r9 = Theme.key_text_RedRegular;
            r103.setColors(r9, r9);
            r103.setText(LocaleController.getString(R.string.DiscussionUnlinkChannel), null, R.drawable.msg_remove, false);
            return;
        L11:
            if (ChatLinkActivity.m8357$$Nest$fgetinfo(this.this$0).linked_chat_id == 0) goto L14;
            int r92 = Theme.key_text_RedRegular;
            r103.setColors(r92, r92);
            r103.setText(LocaleController.getString(R.string.DiscussionUnlinkGroup), null, R.drawable.msg_remove, false);
            return;
        L14:
            r103.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            r103.setText(LocaleController.getString(R.string.DiscussionCreateGroup), null, R.drawable.msg_groups, true);
            return;
        L42:
            return;
        L30:
            ManageChatUserCell r104 = (ManageChatUserCell) r10.itemView;
            r104.setTag(Integer.valueOf(r11));
            TLRPC.Chat r02 = (TLRPC.Chat) ChatLinkActivity.m8350$$Nest$fgetchats(this.this$0).get(r11 - ChatLinkActivity.m8349$$Nest$fgetchatStartRow(this.this$0));
            String r6 = ChatObject.getPublicUsername(r02);
            if (TextUtils.isEmpty(r6) == false) goto L33;
            String r62 = null;
        L35:
            if (r11 == (ChatLinkActivity.m8348$$Nest$fgetchatEndRow(this.this$0) - 1)) goto L37;
        L38:
            r3 = true;
        L39:
            r104.setData(r02, null, r62, r3);
            return;
        L37:
            if (ChatLinkActivity.m8357$$Nest$fgetinfo(this.this$0).linked_chat_id == 0) goto L39;
        L33:
            r62 = "@" + r6;
            goto L35
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L5;
            View r32 = new ManageChatUserCell(this.mContext, 6, 2, false);
        L19:
            return new RecyclerListView.Holder(r32);
        L5:
            if (r4 == 1) goto L16;
            if (r4 != 2) goto L8;
            r32 = new ManageChatTextCell(this.mContext);
            goto L19
        L8:
            if (r4 == 4) goto L10;
            r32 = new HintInnerCell(this.this$0, this.mContext);
            r32.setTag(-33024);
            goto L19
        L10:
            boolean r42 = ChatLinkActivity.m8358$$Nest$fgetisChannel(this.this$0);
            ChatLinkActivity r0 = this.this$0;
            if (r42 == false) goto L13;
            TLRPC.Chat r33 = (TLRPC.Chat) ChatLinkActivity.m8350$$Nest$fgetchats(r0).get(0);
        L14:
            ChatLinkActivity r43 = this.this$0;
            AnonymousClass1 r02 = new AnonymousClass1(this, this.mContext, r33, r33);
            ChatLinkActivity.m8374$$Nest$fputjoinToSendSettings(r43, r02);
            r32 = r02;
            goto L19
        L13:
            r33 = ChatLinkActivity.m8353$$Nest$fgetcurrentChat(r0);
            goto L14
        L16:
            r32 = new TextInfoPrivacyCell(this.mContext);
            goto L19
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof ManageChatUserCell) == false) goto L6;
            ((ManageChatUserCell) r0).recycle();
            return;
        }
    }

    public class SearchAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        private ArrayList<TLRPC.Chat> searchResult;
        private ArrayList<CharSequence> searchResultNames;
        private Runnable searchRunnable;
        final /* synthetic */ ChatLinkActivity this$0;

        public static /* synthetic */ void $r8$lambda$3YhMggRmTCmzio1dSBJ3GkgsKWk(SearchAdapter r0, ArrayList r1, ArrayList r2) {
            r0.lambda$updateSearchResults$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$MoU3PcmFqVEBnhsOYjZtxDrBff8(SearchAdapter r0, String r1) {
            r0.lambda$searchDialogs$0(r1);
        }

        /* renamed from: $r8$lambda$ZC-Luf2cnvYQoNIil_wjr8I_zys, reason: not valid java name */
        public static /* synthetic */ void m8380$r8$lambda$ZCLuf2cnvYQoNIil_wjr8I_zys(SearchAdapter r0, String r1, ArrayList r2) {
            r0.lambda$processSearch$1(r1, r2);
        }

        /* renamed from: $r8$lambda$bK77-hnm4D_jHrZQai3cqUwgEAE, reason: not valid java name */
        public static /* synthetic */ void m8381$r8$lambda$bK77hnm4D_jHrZQai3cqUwgEAE(SearchAdapter r0, String r1) {
            r0.lambda$processSearch$2(r1);
        }

        public SearchAdapter(ChatLinkActivity r1, Context r2) {
            this.this$0 = r1;
            this.searchResult = new ArrayList();
            this.searchResultNames = new ArrayList();
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$processSearch$1(String r19, ArrayList r20) {
            String r1 = r19.trim().toLowerCase();
            if (r1.length() != 0) goto L6;
            updateSearchResults(new ArrayList(), new ArrayList());
            return;
        L6:
            String r2 = LocaleController.getInstance().getTranslitString(r1);
            if (r1.equals(r2) == false) goto L9;
        L10:
            r2 = null;
        L11:
            int r3 = 0;
            boolean r5 = true;
            if (r2 == null) goto L14;
            int r6 = 1;
        L15:
            int r62 = r6 + 1;
            String[] r7 = new String[r62];
            r7[0] = r1;
            if (r2 == null) goto L18;
            r7[1] = r2;
        L18:
            ArrayList<TLRPC.Chat> r12 = new ArrayList();
            ArrayList<CharSequence> r22 = new ArrayList();
            int r8 = 0;
        L20:
            if (r8 >= r20.size()) goto L65;
            TLRPC.Chat r10 = (TLRPC.Chat) r20.get(r8);
            String r11 = r10.title.toLowerCase();
            String r122 = LocaleController.getInstance().getTranslitString(r11);
            if (r11.equals(r122) == false) goto L24;
            r122 = null;
        L24:
            int r13 = r3;
            int r15 = r13;
            String r14 = null;
        L25:
            if (r13 >= r62) goto L63;
            String r32 = r7[r13];
            if (r11.startsWith(r32) == false) goto L29;
        L55:
            r15 = 1;
        L56:
            if (r15 != 0) goto L57;
            r13 = r13 + 1;
            r5 = true;
            goto L25
        L57:
            boolean r4 = true;
            if (r15 != 1) goto L60;
            r22.add(AndroidUtilities.generateSearchName(r10.title, null, r32));
            Object r112 = null;
        L61:
            r12.add(r10);
        L64:
            r8 = r8 + 1;
            r5 = r4;
            r3 = 0;
            goto L20
        L60:
            r112 = null;
            r22.add(AndroidUtilities.generateSearchName("@" + r14, null, "@" + r32));
            goto L61
        L29:
            if (r11.contains(" " + r32) == true) goto L55;
            if (r122 != null) goto L32;
        L36:
            String r42 = r10.username;
            if (r42 != null) goto L39;
        L42:
            ArrayList<TLRPC.TL_username> r43 = r10.usernames;
            if (r43 == null) goto L56;
            if (r43.isEmpty() == true) goto L56;
            int r44 = 0;
        L48:
            if (r44 >= r10.usernames.size()) goto L56;
            TLRPC.TL_username r52 = r10.usernames.get(r44);
            int r17 = r44;
            if (r52.active == false) goto L54;
            if (r52.username.startsWith(r32) == false) goto L54;
            r14 = r52.username;
        L41:
            r15 = 2;
        L54:
            r44 = r17 + 1;
            goto L48
        L39:
            if (r42.startsWith(r32) == false) goto L42;
            r14 = r10.username;
            goto L41
        L32:
            if (r122.startsWith(r32) == true) goto L55;
            if (r122.contains(" " + r32) == false) goto L36;
        L63:
            r4 = r5;
            goto L64
        L65:
            updateSearchResults(r12, r22);
            return;
        L14:
            r6 = 0;
            goto L15
        L9:
            if (r2.length() != 0) goto L11;
            goto L10
        }

        private /* synthetic */ void lambda$processSearch$2(String r4) {
            this.searchRunnable = null;
            ArrayList r0 = new ArrayList(ChatLinkActivity.m8350$$Nest$fgetchats(this.this$0));
            Utilities.searchQueue.postRunnable(new ChatLinkActivity$SearchAdapter$$ExternalSyntheticLambda2(this, r4, r0));
        }

        private /* synthetic */ void lambda$searchDialogs$0(String r1) {
            processSearch(r1);
        }

        private /* synthetic */ void lambda$updateSearchResults$3(ArrayList r2, ArrayList r3) {
            if (ChatLinkActivity.m8369$$Nest$fgetsearching(this.this$0) == true) goto L5;
            return;
        L5:
            this.searchResult = r2;
            this.searchResultNames = r3;
            if (ChatLinkActivity.m8363$$Nest$fgetlistView(this.this$0).getAdapter() != ChatLinkActivity.m8368$$Nest$fgetsearchAdapter(this.this$0)) goto L8;
            ChatLinkActivity.m8355$$Nest$fgetemptyView(this.this$0).showTextView();
        L8:
            notifyDataSetChanged();
        }

        private void processSearch(String r2) {
            AndroidUtilities.runOnUIThread(new ChatLinkActivity$SearchAdapter$$ExternalSyntheticLambda1(this, r2));
        }

        private void updateSearchResults(ArrayList<TLRPC.Chat> r2, ArrayList<CharSequence> r3) {
            AndroidUtilities.runOnUIThread(new ChatLinkActivity$SearchAdapter$$ExternalSyntheticLambda3(this, r2, r3));
        }

        public TLRPC.Chat getItem(int r1) {
            return this.searchResult.get(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.searchResult.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() == 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            TLRPC.Chat r0 = this.searchResult.get(r9);
            String r1 = ChatObject.getPublicUsername(r0);
            CharSequence r7 = this.searchResultNames.get(r9);
            CharSequence r2 = null;
            if (r7 != null) goto L5;
        L9:
            ManageChatUserCell r82 = (ManageChatUserCell) r8.itemView;
            r82.setTag(Integer.valueOf(r9));
            r82.setData(r0, r7, r2, false);
            return;
        L5:
            if (TextUtils.isEmpty(r1) == true) goto L9;
            if (r7.toString().startsWith("@" + r1) == false) goto L9;
            r2 = r7;
            r7 = null;
            goto L9
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            ManageChatUserCell r32 = new ManageChatUserCell(this.mContext, 6, 2, false);
            r32.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            return new RecyclerListView.Holder(r32);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof ManageChatUserCell) == false) goto L6;
            ((ManageChatUserCell) r0).recycle();
            return;
        }

        public void searchDialogs(String r3) {
            if (this.searchRunnable == null) goto L6;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L6:
            if (TextUtils.isEmpty(r3) == false) goto L9;
            this.searchResult.clear();
            this.searchResultNames.clear();
            notifyDataSetChanged();
            return;
        L9:
            DispatchQueue r0 = Utilities.searchQueue;
            ChatLinkActivity$SearchAdapter$$ExternalSyntheticLambda0 r1 = new ChatLinkActivity$SearchAdapter$$ExternalSyntheticLambda0(this, r3);
            this.searchRunnable = r1;
            r0.postRunnable(r1, 300);
        }
    }

    /* renamed from: $r8$lambda$-GZK_xQqsK7Agx7wSNwF9Qmbn_c, reason: not valid java name */
    public static /* synthetic */ void m8341$r8$lambda$GZK_xQqsK7Agx7wSNwF9Qmbn_c(ChatLinkActivity r0) {
        r0.lambda$linkChat$11();
    }

    public static /* synthetic */ void $r8$lambda$10oMKUjf_S2voeKw9FbYSL8NAJo(ChatLinkActivity r0, AlertDialog[] r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$1SNC0nXo5GvMFh5fqgYnjzwfcSI(ChatLinkActivity r0, DialogInterface r1) {
        r0.lambda$showLinkAlert$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$96TA8rPWIIreTIOxVuK5Ilx_qCQ(ChatLinkActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$9KjzbGCfiKudkDr6LSfSxwM7nH4(ChatLinkActivity r0, AlertDialog[] r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$createView$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$BwJbOeBrzhUQsWMsTQjwQPugM7A(ChatLinkActivity r0) {
        r0.lambda$createView$0();
    }

    public static /* synthetic */ void $r8$lambda$E7iJxnmaK7S1eAFALDz_NKYawDw(ChatLinkActivity r0) {
        r0.lambda$getThemeDescriptions$18();
    }

    /* renamed from: $r8$lambda$SY-cRFuQWX6AGwn3E-tc8MvYgyA, reason: not valid java name */
    public static /* synthetic */ void m8342$r8$lambda$SYcRFuQWX6AGwn3Etc8MvYgyA(ChatLinkActivity r0, TLRPC.ChatFull r1, TLRPC.Chat r2, AlertDialog r3, int r4) {
        r0.lambda$showLinkAlert$9(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$SoewZMUwGxtJjBAVX0aqlk885bE(ChatLinkActivity r0, AlertDialog[] r1, int r2) {
        r0.lambda$createView$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$TU8uBTtuAXnq5Rm4L6He405Almo(ChatLinkActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadChats$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$U2XZU2jZ4lhFFgKnvQk40cMt8wY(ChatLinkActivity r0, int r1, DialogInterface r2) {
        r0.lambda$createView$3(r1, r2);
    }

    /* renamed from: $r8$lambda$UFdDkbsmVr8hDxCbsLBf-o8Snx8, reason: not valid java name */
    public static /* synthetic */ void m8343$r8$lambda$UFdDkbsmVr8hDxCbsLBfo8Snx8(ChatLinkActivity r0, View r1, int r2) {
        r0.lambda$createView$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$aVvGX2SdAxAxmx9u7gwDzQQf4uo(ChatLinkActivity r0) {
        r0.lambda$showLinkAlert$8();
    }

    public static /* synthetic */ void $r8$lambda$d1awwAjVChS72HJWDCJVK5fM6Ww(ChatLinkActivity r0, BaseFragment r1, long r2) {
        r0.lambda$linkChat$10(r1, r2);
    }

    /* renamed from: $r8$lambda$diCx-8c28Drbyqd8Yon8tw_88y0, reason: not valid java name */
    public static /* synthetic */ void m8344$r8$lambda$diCx8c28Drbyqd8Yon8tw_88y0(ChatLinkActivity r0, AlertDialog[] r1, TLRPC.Chat r2, BaseFragment r3) {
        r0.lambda$linkChat$12(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$e1y2g-TNp6YdYlu2vWDDFqV5RsA, reason: not valid java name */
    public static /* synthetic */ void m8345$r8$lambda$e1y2gTNp6YdYlu2vWDDFqV5RsA(ChatLinkActivity r0, AlertDialog[] r1, int r2) {
        r0.lambda$linkChat$15(r1, r2);
    }

    /* renamed from: $r8$lambda$jns7aeeujpTWk7tO-tjN4xvyo30, reason: not valid java name */
    public static /* synthetic */ void m8346$r8$lambda$jns7aeeujpTWk7tOtjN4xvyo30(ChatLinkActivity r0, AlertDialog[] r1, TLRPC.Chat r2, BaseFragment r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$linkChat$13(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$pPnvimQzrzy8uJl1_1J84reHWSY(ChatLinkActivity r0, int r1, DialogInterface r2) {
        r0.lambda$linkChat$14(r1, r2);
    }

    /* renamed from: $r8$lambda$z-RCO4TnfIfl-UF1V6Ai1HoU6l8, reason: not valid java name */
    public static /* synthetic */ void m8347$r8$lambda$zRCO4TnfIflUF1V6Ai1HoU6l8(ChatLinkActivity r0, TLObject r1) {
        r0.lambda$loadChats$16(r1);
    }

    /* renamed from: -$$Nest$fgetchatEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8348$$Nest$fgetchatEndRow(ChatLinkActivity r0) {
        return r0.chatEndRow;
    }

    /* renamed from: -$$Nest$fgetchatStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8349$$Nest$fgetchatStartRow(ChatLinkActivity r0) {
        return r0.chatStartRow;
    }

    /* renamed from: -$$Nest$fgetchats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m8350$$Nest$fgetchats(ChatLinkActivity r0) {
        return r0.chats;
    }

    /* renamed from: -$$Nest$fgetchatsLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8351$$Nest$fgetchatsLoaded(ChatLinkActivity r0) {
        return r0.chatsLoaded;
    }

    /* renamed from: -$$Nest$fgetcreateChatRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8352$$Nest$fgetcreateChatRow(ChatLinkActivity r0) {
        return r0.createChatRow;
    }

    /* renamed from: -$$Nest$fgetcurrentChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m8353$$Nest$fgetcurrentChat(ChatLinkActivity r0) {
        return r0.currentChat;
    }

    /* renamed from: -$$Nest$fgetdetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8354$$Nest$fgetdetailRow(ChatLinkActivity r0) {
        return r0.detailRow;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyTextProgressView m8355$$Nest$fgetemptyView(ChatLinkActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgethelpRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8356$$Nest$fgethelpRow(ChatLinkActivity r0) {
        return r0.helpRow;
    }

    /* renamed from: -$$Nest$fgetinfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.ChatFull m8357$$Nest$fgetinfo(ChatLinkActivity r0) {
        return r0.info;
    }

    /* renamed from: -$$Nest$fgetisChannel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8358$$Nest$fgetisChannel(ChatLinkActivity r0) {
        return r0.isChannel;
    }

    /* renamed from: -$$Nest$fgetjoinRequestProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8359$$Nest$fgetjoinRequestProgress(ChatLinkActivity r0) {
        return r0.joinRequestProgress;
    }

    /* renamed from: -$$Nest$fgetjoinToSendInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8360$$Nest$fgetjoinToSendInfoRow(ChatLinkActivity r0) {
        return r0.joinToSendInfoRow;
    }

    /* renamed from: -$$Nest$fgetjoinToSendProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8361$$Nest$fgetjoinToSendProgress(ChatLinkActivity r0) {
        return r0.joinToSendProgress;
    }

    /* renamed from: -$$Nest$fgetjoinToSendRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8362$$Nest$fgetjoinToSendRow(ChatLinkActivity r0) {
        return r0.joinToSendRow;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m8363$$Nest$fgetlistView(ChatLinkActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlistViewAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m8364$$Nest$fgetlistViewAdapter(ChatLinkActivity r0) {
        return r0.listViewAdapter;
    }

    /* renamed from: -$$Nest$fgetloadingChats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8365$$Nest$fgetloadingChats(ChatLinkActivity r0) {
        return r0.loadingChats;
    }

    /* renamed from: -$$Nest$fgetremoveChatRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8366$$Nest$fgetremoveChatRow(ChatLinkActivity r0) {
        return r0.removeChatRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8367$$Nest$fgetrowCount(ChatLinkActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchAdapter m8368$$Nest$fgetsearchAdapter(ChatLinkActivity r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8369$$Nest$fgetsearching(ChatLinkActivity r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fputcurrentChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8370$$Nest$fputcurrentChat(ChatLinkActivity r0, TLRPC.Chat r1) {
        r0.currentChat = r1;
    }

    /* renamed from: -$$Nest$fputcurrentChatId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8371$$Nest$fputcurrentChatId(ChatLinkActivity r0, long r1) {
        r0.currentChatId = r1;
    }

    /* renamed from: -$$Nest$fputjoinRequestProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8372$$Nest$fputjoinRequestProgress(ChatLinkActivity r0, boolean r1) {
        r0.joinRequestProgress = r1;
    }

    /* renamed from: -$$Nest$fputjoinToSendProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8373$$Nest$fputjoinToSendProgress(ChatLinkActivity r0, boolean r1) {
        r0.joinToSendProgress = r1;
    }

    /* renamed from: -$$Nest$fputjoinToSendSettings, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8374$$Nest$fputjoinToSendSettings(ChatLinkActivity r0, JoinToSendSettingsView r1) {
        r0.joinToSendSettings = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8375$$Nest$fputsearchWas(ChatLinkActivity r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8376$$Nest$fputsearching(ChatLinkActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$mlinkChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8377$$Nest$mlinkChat(ChatLinkActivity r0, TLRPC.Chat r1, BaseFragment r2) {
        r0.linkChat(r1, r2);
    }

    public ChatLinkActivity(long r3) {
        this.chats = new ArrayList();
        boolean r0 = false;
        this.joinToSendProgress = false;
        this.joinRequestProgress = false;
        this.currentChatId = r3;
        TLRPC.Chat r32 = getMessagesController().getChat(Long.valueOf(r3));
        this.currentChat = r32;
        if (ChatObject.isChannel(r32) == true) goto L5;
    L7:
        this.isChannel = r0;
        return;
    L5:
        if (this.currentChat.megagroup == true) goto L7;
        r0 = true;
        goto L7
    }

    private /* synthetic */ void lambda$createView$0() {
        getMessagesController().loadFullChat(this.currentChatId, 0, true);
    }

    private /* synthetic */ void lambda$createView$1(AlertDialog[] r5) {
        r5[0].dismiss();     // Catch: Throwable -> L8
    L4:
        r5[0] = null;
        this.info.linked_chat_id = 0;
        NotificationCenter r52 = NotificationCenter.getInstance(this.currentAccount);
        int r1 = NotificationCenter.chatInfoDidLoad;
        TLRPC.ChatFull r2 = this.info;
        Boolean r3 = Boolean.FALSE;
        r52.postNotificationName(r1, new Object[]{r2, 0, r3, r3});
        AndroidUtilities.runOnUIThread(new ChatLinkActivity$$ExternalSyntheticLambda18(this), 1000);
        if (this.isChannel == true) goto L11;
        finishFragment();
        return;
    }

    private /* synthetic */ void lambda$createView$2(AlertDialog[] r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ChatLinkActivity$$ExternalSyntheticLambda15(this, r1));
    }

    private /* synthetic */ void lambda$createView$3(int r1, DialogInterface r2) {
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r1, true);
    }

    private /* synthetic */ void lambda$createView$4(AlertDialog[] r4, int r5) {
        AlertDialog r1 = r4[0];
        if (r1 != null) goto L5;
        return;
    L5:
        r1.setOnCancelListener(new ChatLinkActivity$$ExternalSyntheticLambda16(this, r5));
        showDialog(r4[0]);
    }

    private /* synthetic */ void lambda$createView$5(AlertDialog r3, int r4) {
        if (this.isChannel == true) goto L5;
    L8:
        AlertDialog[] r32 = {new AlertDialog(getParentActivity(), 3)};
        TLRPC.TL_channels_setDiscussionGroup r42 = new TLRPC.TL_channels_setDiscussionGroup();
        if (this.isChannel == false) goto L11;
        r42.broadcast = MessagesController.getInputChannel(this.currentChat);
        r42.group = new TLRPC.TL_inputChannelEmpty();
    L12:
        AndroidUtilities.runOnUIThread(new ChatLinkActivity$$ExternalSyntheticLambda12(this, r32, getConnectionsManager().sendRequest(r42, new ChatLinkActivity$$ExternalSyntheticLambda11(this, r32))), 500);
        return;
    L11:
        r42.broadcast = new TLRPC.TL_inputChannelEmpty();
        r42.group = MessagesController.getInputChannel(this.currentChat);
        goto L12
    L5:
        if (this.info.linked_chat_id != 0) goto L8;
    }

    private /* synthetic */ void lambda$createView$6(View r7, int r8) {
        if (getParentActivity() == null) goto L48;
        RecyclerView.Adapter r72 = this.listView.getAdapter();
        SearchAdapter r0 = this.searchAdapter;
        if (r72 != r0) goto L8;
        TLRPC.Chat r73 = r0.getItem(r8);
    L15:
        if (r73 == null) goto L25;
        if (this.isChannel == true) goto L19;
    L22:
        Bundle r82 = new Bundle();
        r82.putLong("chat_id", r73.id);
        presentFragment(new ChatActivity(r82));
        return;
    L19:
        if (this.info.linked_chat_id != 0) goto L22;
        showLinkAlert(r73, true);
        return;
    L25:
        if (r8 == this.createChatRow) goto L27;
        return;
    L27:
        if (this.isChannel == false) goto L36;
        if (this.info.linked_chat_id != 0) goto L36;
        Bundle r74 = new Bundle();
        r74.putLongArray("result", new long[]{getUserConfig().getClientUserId()});
        r74.putInt("chatType", 4);
        TLRPC.Chat r83 = this.currentChat;
        if (r83 == null) goto L33;
        r74.putString("title", LocaleController.formatString("GroupCreateDiscussionDefaultName", R.string.GroupCreateDiscussionDefaultName, new Object[]{r83.title}));
    L33:
        GroupCreateFinalActivity r84 = new GroupCreateFinalActivity(r74);
        r84.setDelegate(new AnonymousClass3(this));
        presentFragment(r84);
        return;
    L36:
        if (this.chats.isEmpty() == true) goto L49;
        TLRPC.Chat r75 = this.chats.get(0);
        AlertDialog.Builder r85 = new AlertDialog.Builder(getParentActivity());
        if (this.isChannel == false) goto L41;
        String r02 = LocaleController.getString(R.string.DiscussionUnlinkGroup);
        String r76 = LocaleController.formatString("DiscussionUnlinkChannelAlert", R.string.DiscussionUnlinkChannelAlert, new Object[]{r75.title});
    L42:
        r85.setTitle(r02);
        r85.setMessage(AndroidUtilities.replaceTags(r76));
        r85.setPositiveButton(LocaleController.getString(R.string.DiscussionUnlink), new ChatLinkActivity$$ExternalSyntheticLambda5(this));
        r85.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r77 = r85.create();
        showDialog(r77);
        TextView r6 = (TextView) r77.getButton(-1);
        if (r6 == null) goto L47;
        r6.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L47:
        return;
    L41:
        r02 = LocaleController.getString(R.string.DiscussionUnlinkChannel);
        r76 = LocaleController.formatString("DiscussionUnlinkGroupAlert", R.string.DiscussionUnlinkGroupAlert, new Object[]{r75.title});
        goto L42
    L49:
        return;
    L8:
        int r78 = this.chatStartRow;
        if (r8 >= r78) goto L11;
    L13:
        r73 = null;
        goto L15
    L11:
        if (r8 >= this.chatEndRow) goto L13;
        r73 = this.chats.get(r8 - r78);
        goto L15
    }

    private /* synthetic */ void lambda$getThemeDescriptions$18() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L14;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof ManageChatUserCell) == false) goto L9;
        ((ManageChatUserCell) r3).update(0);
    L9:
        r2 = r2 + 1;
        goto L5
    L14:
        return;
    }

    private /* synthetic */ void lambda$linkChat$10(BaseFragment r3, long r4) {
        if (r4 == 0) goto L6;
        getMessagesController().toggleChannelInvitesHistory(r4, false);
        linkChat(getMessagesController().getChat(Long.valueOf(r4)), r3);
        return;
    }

    private /* synthetic */ void lambda$linkChat$11() {
        getMessagesController().loadFullChat(this.currentChatId, 0, true);
    }

    private /* synthetic */ void lambda$linkChat$12(AlertDialog[] r4, TLRPC.Chat r5, BaseFragment r6) {
        AlertDialog r1 = r4[0];
        if (r1 != null) goto L12;
    L6:
        TLRPC.ChatFull r42 = this.info;
        r42.linked_chat_id = r5.id;
        NotificationCenter r43 = NotificationCenter.getInstance(this.currentAccount);
        int r52 = NotificationCenter.chatInfoDidLoad;
        TLRPC.ChatFull r12 = this.info;
        Boolean r2 = Boolean.FALSE;
        r43.postNotificationName(r52, new Object[]{r12, 0, r2, r2});
        AndroidUtilities.runOnUIThread(new ChatLinkActivity$$ExternalSyntheticLambda17(this), 1000);
        if (r6 == null) goto L9;
        removeSelfFromStack();
        r6.finishFragment();
        return;
    L9:
        finishFragment();
        return;
    L12:
        r1.dismiss();     // Catch: Throwable -> L11
    L5:
        r4[0] = null;
        goto L6
    }

    private /* synthetic */ void lambda$linkChat$13(AlertDialog[] r1, TLRPC.Chat r2, BaseFragment r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new ChatLinkActivity$$ExternalSyntheticLambda13(this, r1, r2, r3));
    }

    private /* synthetic */ void lambda$linkChat$14(int r1, DialogInterface r2) {
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r1, true);
    }

    private /* synthetic */ void lambda$linkChat$15(AlertDialog[] r4, int r5) {
        AlertDialog r1 = r4[0];
        if (r1 != null) goto L5;
        return;
    L5:
        r1.setOnCancelListener(new ChatLinkActivity$$ExternalSyntheticLambda14(this, r5));
        showDialog(r4[0]);
    }

    private /* synthetic */ void lambda$loadChats$16(TLObject r4) {
        if ((r4 instanceof TLRPC.messages_Chats) == false) goto L12;
        TLRPC.messages_Chats r42 = (TLRPC.messages_Chats) r4;
        getMessagesController().putChats(r42.chats, false);
        ArrayList<TLRPC.Chat> r43 = r42.chats;
        this.chats = r43;
        Iterator<TLRPC.Chat> r44 = r43.iterator();
    L6:
        if (r44.hasNext() == false) goto L12;
        TLRPC.Chat r0 = r44.next();
        if (ChatObject.isForum(r0) == true) goto L11;
        if (ChatObject.isMonoForum(r0) == false) goto L6;
    L11:
        r44.remove();
    L12:
        this.loadingChats = false;
        this.chatsLoaded = true;
        updateRows();
    }

    private /* synthetic */ void lambda$loadChats$17(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChatLinkActivity$$ExternalSyntheticLambda9(this, r1));
    }

    private /* synthetic */ void lambda$showLinkAlert$7(DialogInterface r1) {
        this.waitingForFullChat = null;
    }

    private /* synthetic */ void lambda$showLinkAlert$8() {
        AlertDialog r0 = this.waitingForFullChatProgressAlert;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setOnCancelListener(new ChatLinkActivity$$ExternalSyntheticLambda10(this));
        showDialog(this.waitingForFullChatProgressAlert);
    }

    private /* synthetic */ void lambda$showLinkAlert$9(TLRPC.ChatFull r2, TLRPC.Chat r3, AlertDialog r4, int r5) {
        if (r2.hidden_prehistory == false) goto L5;
        getMessagesController().toggleChannelInvitesHistory(r3.id, false);
    L5:
        linkChat(r3, null);
    }

    private void linkChat(TLRPC.Chat r8, BaseFragment r9) {
        if (r8 != null) goto L5;
        return;
    L5:
        if (ChatObject.isChannel(r8) == true) goto L9;
        getMessagesController().convertToMegaGroup(getParentActivity(), r8.id, this, new ChatLinkActivity$$ExternalSyntheticLambda6(this, r9));
        return;
    L9:
        if (r9 == null) goto L11;
        AlertDialog r7 = null;
    L12:
        AlertDialog[] r72 = {r7};
        TLRPC.TL_channels_setDiscussionGroup r0 = new TLRPC.TL_channels_setDiscussionGroup();
        r0.broadcast = MessagesController.getInputChannel(this.currentChat);
        r0.group = MessagesController.getInputChannel(r8);
        AndroidUtilities.runOnUIThread(new ChatLinkActivity$$ExternalSyntheticLambda8(this, r72, getConnectionsManager().sendRequest(r0, new ChatLinkActivity$$ExternalSyntheticLambda7(this, r72, r8, r9), 64)), 500);
        return;
    L11:
        r7 = new AlertDialog(getParentActivity(), 3);
        goto L12
    }

    private void loadChats() {
        if (this.info.linked_chat_id == 0) goto L11;
        this.chats.clear();
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.info.linked_chat_id));
        if (r0 == null) goto L7;
        this.chats.add(r0);
    L7:
        ActionBarMenuItem r02 = this.searchItem;
        if (r02 == null) goto L11;
        r02.setVisibility(8);
    L11:
        if (this.loadingChats == false) goto L13;
        return;
    L13:
        if (this.isChannel == true) goto L15;
        return;
    L15:
        if (this.info.linked_chat_id != 0) goto L21;
        this.loadingChats = true;
        TLRPC.TL_channels_getGroupsForDiscussion r03 = new TLRPC.TL_channels_getGroupsForDiscussion();
        getConnectionsManager().sendRequest(r03, new ChatLinkActivity$$ExternalSyntheticLambda2(this));
        return;
    }

    private void showLinkAlert(TLRPC.Chat r21, boolean r22) {
        TLRPC.ChatFull r2 = getMessagesController().getChatFull(r21.id);
        int r3 = 3;
        if (r2 != null) goto L7;
        if (r22 == false) goto L46;
        getMessagesController().loadFullChat(r21.id, 0, true);
        this.waitingForFullChat = r21;
        this.waitingForFullChatProgressAlert = new AlertDialog(getParentActivity(), 3);
        AndroidUtilities.runOnUIThread(new ChatLinkActivity$$ExternalSyntheticLambda0(this), 500);
        return;
    L46:
        return;
    L7:
        AlertDialog.Builder r5 = new AlertDialog.Builder(getParentActivity());
        TextView r6 = new TextView(getParentActivity());
        r6.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r6.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L10;
        int r7 = 5;
    L11:
        r6.setGravity(r7 | 48);
        if (ChatObject.isPublic(r21) == true) goto L14;
        String r72 = LocaleController.formatString("DiscussionLinkGroupPublicPrivateAlert", R.string.DiscussionLinkGroupPublicPrivateAlert, new Object[]{r21.title, this.currentChat.title});
    L19:
        if (r2.hidden_prehistory == false) goto L21;
        r72 = r72 + "\n\n" + LocaleController.getString(R.string.DiscussionLinkGroupAlertHistory);
    L21:
        r6.setText(AndroidUtilities.replaceTags(r72));
        FrameLayout r73 = new FrameLayout(getParentActivity());
        r5.setView(r73);
        AvatarDrawable r9 = new AvatarDrawable();
        r9.setTextSize(AndroidUtilities.dp(12.0f));
        BackupImageView r10 = new BackupImageView(getParentActivity());
        r10.setRoundRadius(ExteraConfig.getAvatarCorners(40.0f));
        if (LocaleController.isRTL == false) goto L24;
        int r11 = 5;
    L25:
        r73.addView(r10, LayoutHelper.createFrame(40, 40.0f, r11 | 48, 22.0f, 5.0f, 22.0f, 0.0f));
        TextView r112 = new TextView(getParentActivity());
        r112.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        r112.setTextSize(1, 20.0f);
        r112.setTypeface(AndroidUtilities.bold());
        r112.setLines(1);
        r112.setMaxLines(1);
        r112.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L28;
        int r4 = 5;
    L29:
        r112.setGravity(r4 | 16);
        r112.setEllipsize(TextUtils.TruncateAt.END);
        r112.setText(r21.title);
        boolean r42 = LocaleController.isRTL;
        if (r42 == false) goto L32;
        int r12 = 5;
    L33:
        int r15 = r12 | 48;
        int r122 = 76;
        if (r42 == false) goto L36;
        int r14 = 21;
    L37:
        float r142 = r14;
        if (r42 == true) goto L41;
        r122 = 21;
    L41:
        r73.addView(r112, LayoutHelper.createFrame(-1, -2.0f, r15, r142, 11.0f, r122, 0.0f));
        if (LocaleController.isRTL == false) goto L44;
        r3 = 5;
    L44:
        r73.addView(r6, LayoutHelper.createFrame(-2, -2.0f, r3 | 48, 24.0f, 57.0f, 24.0f, 9.0f));
        r9.setInfo(this.currentAccount, r21);
        r10.setForUserOrChat(r21, r9);
        r5.setPositiveButton(LocaleController.getString(R.string.DiscussionLinkGroup), new ChatLinkActivity$$ExternalSyntheticLambda1(this, r2, r21));
        r5.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r5.create());
        return;
    L36:
        r14 = 76;
        goto L37
    L32:
        r12 = 3;
        goto L33
    L28:
        r4 = 3;
        goto L29
    L24:
        r11 = 3;
        goto L25
    L14:
        boolean r74 = ChatObject.isPublic(this.currentChat);
        String r92 = r21.title;
        if (r74 == true) goto L17;
        r72 = LocaleController.formatString("DiscussionLinkGroupPrivateAlert", R.string.DiscussionLinkGroupPrivateAlert, new Object[]{r92, this.currentChat.title});
        goto L19
    L17:
        r72 = LocaleController.formatString("DiscussionLinkGroupPublicAlert", R.string.DiscussionLinkGroupPublicAlert, new Object[]{r92, this.currentChat.title});
        goto L19
    L10:
        r7 = 3;
        goto L11
    }

    private void updateRows() {
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.currentChatId));
        this.currentChat = r0;
        if (r0 == null) goto L48;
        this.createChatRow = -1;
        this.chatStartRow = -1;
        this.chatEndRow = -1;
        this.removeChatRow = -1;
        this.detailRow = -1;
        this.joinToSendRow = -1;
        this.joinToSendInfoRow = -1;
        this.rowCount = 1;
        int r1 = 0;
        this.helpRow = 0;
        if (this.isChannel == true) goto L8;
        this.chatStartRow = 1;
        int r2 = this.chats.size();
        int r02 = 1 + r2;
        this.chatEndRow = r02;
        this.rowCount = r2 + 2;
        this.createChatRow = r02;
    L14:
        int r03 = this.rowCount;
        this.rowCount = r03 + 1;
        this.detailRow = r03;
        if (this.isChannel == false) goto L21;
        if (this.chats.size() > 0) goto L19;
    L36:
        ListAdapter r04 = this.listViewAdapter;
        if (r04 == null) goto L39;
        r04.notifyDataSetChanged();
    L39:
        ActionBarMenuItem r05 = this.searchItem;
        if (r05 != null) goto L42;
        return;
    L42:
        if (this.chats.size() > 10) goto L45;
        r1 = 8;
    L45:
        r05.setVisibility(r1);
        return;
    L19:
        if (this.info.linked_chat_id == 0) goto L36;
    L21:
        if (this.isChannel == false) goto L23;
        TLRPC.Chat r06 = this.chats.get(0);
    L24:
        if (r06 == null) goto L36;
        if (ChatObject.isPublic(r06) == false) goto L30;
        if (this.isChannel == false) goto L36;
    L30:
        if (r06.creator == true) goto L35;
        TLRPC.TL_chatAdminRights r07 = r06.admin_rights;
        if (r07 == null) goto L36;
        if (r07.ban_users == false) goto L36;
    L35:
        int r08 = this.rowCount;
        this.rowCount = r08 + 1;
        this.joinToSendRow = r08;
        goto L36
    L23:
        r06 = this.currentChat;
        goto L24
    L8:
        if (this.info.linked_chat_id != 0) goto L10;
        this.rowCount = 1 + 1;
        this.createChatRow = 1;
    L10:
        int r09 = this.rowCount;
        this.chatStartRow = r09;
        int r010 = r09 + this.chats.size();
        this.rowCount = r010;
        this.chatEndRow = r010;
        if (this.info.linked_chat_id == 0) goto L14;
        this.rowCount = r010 + 1;
        this.createChatRow = r010;
        goto L14
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        this.searching = false;
        this.searchWas = false;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        int r2 = 1;
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.Discussion));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenuItem r1 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this));
        this.searchItem = r1;
        r1.setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.searchAdapter = new SearchAdapter(this, r8);
        FrameLayout r12 = new FrameLayout(r8);
        this.fragmentView = r12;
        int r3 = Theme.key_windowBackgroundGray;
        r12.setBackgroundColor(Theme.getColor(r3));
        this.fragmentView.setTag(Integer.valueOf(r3));
        FrameLayout r13 = (FrameLayout) this.fragmentView;
        EmptyTextProgressView r32 = new EmptyTextProgressView(r8);
        this.emptyView = r32;
        r32.showProgress();
        this.emptyView.setText(LocaleController.getString(R.string.NoResult));
        r13.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r33 = new RecyclerListView(r8);
        this.listView = r33;
        r33.setSections();
        this.listView.setEmptyView(this.emptyView);
        this.listView.setLayoutManager(new LinearLayoutManager(r8, 1, false));
        RecyclerListView r0 = this.listView;
        ListAdapter r34 = new ListAdapter(this, r8);
        this.listViewAdapter = r34;
        r0.setAdapter(r34);
        RecyclerListView r82 = this.listView;
        if (LocaleController.isRTL == true) goto L6;
        r2 = 2;
    L6:
        r82.setVerticalScrollbarPosition(r2);
        r13.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setOnItemClickListener(new ChatLinkActivity$$ExternalSyntheticLambda4(this));
        updateRows();
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        TLRPC.Chat r0 = null;
        if (r5 != NotificationCenter.chatInfoDidLoad) goto L14;
        TLRPC.ChatFull r52 = (TLRPC.ChatFull) r7[0];
        long r62 = r52.id;
        if (r62 != this.currentChatId) goto L7;
        this.info = r52;
        loadChats();
        updateRows();
        return;
    L7:
        TLRPC.Chat r53 = this.waitingForFullChat;
        if (r53 != null) goto L10;
        return;
    L10:
        if (r53.id != r62) goto L60;
        this.waitingForFullChatProgressAlert.dismiss();     // Catch: Throwable -> L55
    L12:
        this.waitingForFullChatProgressAlert = null;
        showLinkAlert(this.waitingForFullChat, false);
        this.waitingForFullChat = null;
        return;
    L60:
        return;
    L14:
        if (r5 != NotificationCenter.updateInterfaces) goto L44;
        if ((((Integer) r7[0]).intValue() & MessagesController.UPDATE_MASK_CHAT) != 0) goto L18;
        return;
    L18:
        if (this.currentChat == null) goto L63;
        TLRPC.Chat r54 = getMessagesController().getChat(Long.valueOf(this.currentChat.id));
        if (r54 == null) goto L23;
        this.currentChat = r54;
    L23:
        if (this.chats.size() <= 0) goto L28;
        TLRPC.Chat r55 = getMessagesController().getChat(Long.valueOf(this.chats.get(0).id));
        if (r55 == null) goto L28;
        this.chats.set(0, r55);
    L28:
        if (this.isChannel == true) goto L30;
        r0 = this.currentChat;
    L33:
        if (r0 == null) goto L64;
        JoinToSendSettingsView r56 = this.joinToSendSettings;
        if (r56 != null) goto L37;
        return;
    L37:
        if (this.joinRequestProgress == true) goto L40;
        r56.setJoinRequest(r0.join_request);
    L40:
        if (this.joinToSendProgress == true) goto L66;
        this.joinToSendSettings.setJoinToSend(r0.join_to_send);
        return;
    L66:
        return;
    L64:
        return;
    L30:
        if (this.chats.size() <= 0) goto L33;
        r0 = this.chats.get(0);
        goto L33
    L63:
        return;
    L44:
        if (r5 != NotificationCenter.dialogDeleted) goto L67;
        long r57 = ((Long) r7[0]).longValue();
        if ((-this.currentChatId) != r57) goto L68;
        INavigationLayout r58 = this.parentLayout;
        if (r58 != null) goto L50;
    L53:
        removeSelfFromStack();
        return;
    L50:
        if (r58.getLastFragment() != this) goto L53;
        finishFragment();
        return;
    L68:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ChatLinkActivity$$ExternalSyntheticLambda3 r8 = new ChatLinkActivity$$ExternalSyntheticLambda3(this);
        int r16 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ManageChatUserCell.class, ManageChatTextCell.class}, null, null, null, r16));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        int r22 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"nameTextView"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"statusColor"}, null, null, r8, Theme.key_windowBackgroundWhiteGrayText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"statusOnlineColor"}, null, null, r8, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HintInnerCell.class}, new String[]{"messageTextView"}, null, null, null, Theme.key_chats_message));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"textView"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueButton));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueIcon));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        getNotificationCenter().addObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().addObserver(this, NotificationCenter.dialogDeleted);
        loadChats();
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().removeObserver(this, NotificationCenter.dialogDeleted);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listViewAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }

    public void setInfo(TLRPC.ChatFull r1) {
        this.info = r1;
    }
}
