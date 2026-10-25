package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.ClickableAnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoriesListPlaceProvider;

/* loaded from: classes6.dex */
public abstract class ProfileChannelCell extends FrameLayout implements Theme.Colorable {
    public final DialogCell dialogCell;
    private final TextView headerView;
    private boolean loading;
    private AnimatedFloat loadingAlpha;
    private final LoadingDrawable loadingDrawable;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean set;
    private final AnimatedTextView subscribersView;

    /* renamed from: org.telegram.ui.Cells.ProfileChannelCell$1, reason: invalid class name */
    public class AnonymousClass1 implements DialogCell.DialogCellDelegate {
        final /* synthetic */ ProfileChannelCell this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ BaseFragment val$fragment;

        public AnonymousClass1(ProfileChannelCell r1, BaseFragment r2, Context r3) {
            this.this$0 = r1;
            this.val$fragment = r2;
            this.val$context = r3;
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public boolean canClickButtonInside() {
            return true;
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void onButtonClicked(DialogCell r1) {
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void onButtonLongPress(DialogCell r1) {
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void openHiddenStories() {
            StoriesController r0 = this.val$fragment.getMessagesController().getStoriesController();
            if (r0.getHiddenList().isEmpty() == false) goto L5;
            return;
        L5:
            int r2 = 0;
            if (r0.getUnreadState(DialogObject.getPeerDialogId(r0.getHiddenList().get(0).peer)) == 0) goto L8;
            boolean r1 = true;
        L9:
            ArrayList<Long> r6 = new ArrayList();
        L11:
            if (r2 >= r0.getHiddenList().size()) goto L18;
            long r3 = DialogObject.getPeerDialogId(r0.getHiddenList().get(r2).peer);
            if (r1 == true) goto L15;
        L16:
            r6.add(Long.valueOf(r3));
        L17:
            r2 = r2 + 1;
            goto L11
        L15:
            if (r0.getUnreadState(r3) == 0) goto L17;
        L18:
            this.val$fragment.getOrCreateStoryViewer().open(this.val$context, null, r6, 0, null, null, StoriesListPlaceProvider.of(this.this$0), false);
            return;
        L8:
            r1 = false;
            goto L9
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void openStory(DialogCell r4, Runnable r5) {
            if (this.val$fragment.getMessagesController().getStoriesController().hasStories(r4.getDialogId()) == false) goto L6;
            this.val$fragment.getOrCreateStoryViewer().doOnAnimationReady(r5);
            this.val$fragment.getOrCreateStoryViewer().open(this.val$fragment.getContext(), r4.getDialogId(), StoriesListPlaceProvider.of(this.this$0));
            return;
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void showChatPreview(DialogCell r1) {
        }
    }

    public static class ChannelMessageFetcher {
        private ArrayList<Runnable> callbacks;
        public long channel_id;
        public final int currentAccount;
        public boolean error;
        public boolean loaded;
        public boolean loading;
        public ArrayList<MessageObject> messageObjects;
        public int message_id;
        private int searchId;

        public static /* synthetic */ void $r8$lambda$4GaFD12QWPuSINXyCdFUe08goNA(ChannelMessageFetcher r0, int r1, TLObject r2, MessagesStorage r3, long r4) {
            r0.lambda$fetch$2(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$_5L58a9DKQFpxgWPt647q27VuP4(ChannelMessageFetcher r0, int r1, MessagesStorage r2, long r3, TLObject r5, TLRPC.TL_error r6) {
            r0.lambda$fetch$3(r1, r2, r3, r5, r6);
        }

        /* renamed from: $r8$lambda$b_ZPnIBnNrQtzq7Jh-ucvg1Wb-k, reason: not valid java name */
        public static /* synthetic */ int m7002$r8$lambda$b_ZPnIBnNrQtzq7Jhucvg1Wbk(TLRPC.Message r0) {
            return r0.id;
        }

        public static /* synthetic */ void $r8$lambda$bef_t3o84U4N8DSCyizInc5e4BA(ChannelMessageFetcher r0, int r1, ArrayList r2, long r3, int r5, MessagesStorage r6) {
            r0.lambda$fetch$4(r1, r2, r3, r5, r6);
        }

        public static /* synthetic */ int $r8$lambda$l262fAYW2iJyI_UQqtIitcyMLrM(TLRPC.Message r0) {
            return r0.id;
        }

        public static /* synthetic */ void $r8$lambda$yiJ3Njwe3gB6B60iF3nkKlBCew4(ChannelMessageFetcher r0, int r1, MessagesStorage r2, long r3, long r5, int r7) {
            r0.lambda$fetch$5(r1, r2, r3, r5, r7);
        }

        public ChannelMessageFetcher(int r2) {
            this.messageObjects = new ArrayList();
            this.callbacks = new ArrayList();
            this.currentAccount = r2;
        }

        private void done(boolean r4) {
            int r0 = 0;
            this.loading = false;
            this.loaded = true;
            this.error = r4;
            ArrayList<Runnable> r42 = this.callbacks;
            int r1 = r42.size();
        L3:
            if (r0 >= r1) goto L5;
            Runnable r2 = r42.get(r0);
            r0 = r0 + 1;
            r2.run();
            goto L3
        L5:
            this.callbacks.clear();
        }

        private /* synthetic */ void lambda$fetch$2(int r17, TLObject r18, MessagesStorage r19, long r20) {
            if (r17 == this.searchId) goto L6;
            return;
        L6:
            if ((r18 instanceof TLRPC.messages_Messages) == false) goto L22;
            TLRPC.messages_Messages r6 = (TLRPC.messages_Messages) r18;
            MessagesController.getInstance(this.currentAccount).putUsers(r6.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r6.chats, false);
            r19.putUsersAndChats(r6.users, r6.chats, true, true);
            r19.putMessages(r6, -r20, 3, 0, false, 0, 0);
            if (r17 == this.searchId) goto L11;
            return;
        L11:
            if (r6.messages.isEmpty() == true) goto L20;
            this.messageObjects.clear();
            Collections.sort(r6.messages, Comparator.comparingInt(new ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda5()));
            ArrayList<TLRPC.Message> r1 = r6.messages;
            TLRPC.Message r12 = r1.get(r1.size() - 1);
            long r2 = r12.grouped_id;
            if (r2 == 0) goto L19;
            ArrayList<TLRPC.Message> r13 = r6.messages;
            int r5 = r13.size();
            int r62 = 0;
        L15:
            if (r62 >= r5) goto L20;
            TLRPC.Message r7 = r13.get(r62);
            r62 = r62 + 1;
            TLRPC.Message r72 = r7;
            if (r72.grouped_id != r2) goto L15;
            this.messageObjects.add(new MessageObject(this.currentAccount, r72, false, true));
            goto L15
        L19:
            this.messageObjects.add(new MessageObject(this.currentAccount, r12, false, true));
        L20:
            done(false);
            return;
        L22:
            done(true);
        }

        private /* synthetic */ void lambda$fetch$3(int r3, MessagesStorage r4, long r5, TLObject r7, TLRPC.TL_error r8) {
            AndroidUtilities.runOnUIThread(new ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda4(this, r3, r7, r4, r5));
        }

        private /* synthetic */ void lambda$fetch$4(int r11, ArrayList r12, long r13, int r15, MessagesStorage r16) {
            if (r11 == this.searchId) goto L6;
            return;
        L6:
            if (r12.isEmpty() == true) goto L19;
            this.messageObjects.clear();
            Collections.sort(r12, Comparator.comparingInt(new ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda2()));
            TLRPC.Message r0 = (TLRPC.Message) r12.get(r12.size() - 1);
            long r2 = r0.grouped_id;
            if (r2 == 0) goto L14;
            int r02 = r12.size();
            int r4 = 0;
        L10:
            if (r4 >= r02) goto L16;
            Object r6 = r12.get(r4);
            r4 = r4 + 1;
            TLRPC.Message r62 = (TLRPC.Message) r6;
            if (r62.grouped_id != r2) goto L10;
            this.messageObjects.add(new MessageObject(this.currentAccount, r62, false, true));
        L16:
            if (this.messageObjects.isEmpty() == true) goto L19;
            done(false);
            return;
        L14:
            this.messageObjects.add(new MessageObject(this.currentAccount, r0, false, true));
        L19:
            TLRPC.TL_channels_getMessages r122 = new TLRPC.TL_channels_getMessages();
            r122.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(r13);
            int r03 = 10;
        L20:
            if (r03 < 0) goto L25;
            int r1 = r15 - r03;
            if (r1 < 0) goto L24;
            r122.id.add(Integer.valueOf(r1));
        L24:
            r03 = r03 - 1;
            goto L20
        L25:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r122, new ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda3(this, r11, r16, r13));
        }

        private /* synthetic */ void lambda$fetch$5(int r17, MessagesStorage r18, long r19, long r21, int r23) {
            ArrayList r3 = new ArrayList();
            ArrayList<TLRPC.User> r0 = new ArrayList();
            ArrayList<TLRPC.Chat> r1 = new ArrayList();
            SQLiteCursor r2 = null;
            if (r17 > 0) goto L9;
            SQLiteCursor r6 = r18.getDatabase().queryFinalized("SELECT data, mid FROM messages_v2 WHERE uid = ? ORDER BY mid DESC LIMIT 10", new Object[]{Long.valueOf(-r19)});     // Catch: Throwable -> L5 Exception -> L7
        L41:
            ArrayList<Long> r8 = new ArrayList();     // Catch: Throwable -> L16 Exception -> L18
            ArrayList r9 = new ArrayList();     // Catch: Throwable -> L16 Exception -> L18
        L12:
            if (r6.next() == false) goto L21;
            NativeByteBuffer r11 = r6.byteBufferValue(0);     // Catch: Throwable -> L16 Exception -> L18
            if (r11 == null) goto L12;
            TLRPC.Message r10 = TLRPC.Message.TLdeserialize(r11, r11.readInt32(false), false);     // Catch: Throwable -> L16 Exception -> L18
            r10.readAttachPath(r11, r21);     // Catch: Throwable -> L16 Exception -> L18
            r11.reuse();     // Catch: Throwable -> L16 Exception -> L18
            r10.id = r6.intValue(1);     // Catch: Throwable -> L16 Exception -> L18
            r10.dialog_id = -r19;     // Catch: Throwable -> L16 Exception -> L18
            MessagesStorage.addUsersAndChatsFromMessage(r10, r8, r9, null);     // Catch: Throwable -> L16 Exception -> L18
            r3.add(r10);     // Catch: Throwable -> L16 Exception -> L18
            goto L12
        L21:
            r6.dispose();     // Catch: Throwable -> L16 Exception -> L18
            if (r3.isEmpty() == false) goto L24;
        L29:
            r6.dispose();
        L33:
            AndroidUtilities.runOnUIThread(new ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda1(this, r23, r3, r19, r17, r18));
            return;
        L24:
            if (r8.isEmpty() == true) goto L27;
            r18.getUsersInternal(r8, r0);     // Catch: Throwable -> L16 Exception -> L18
        L27:
            if (r9.isEmpty() == true) goto L29;
            r18.getChatsInternal(TextUtils.join(",", r9), r1);     // Catch: Throwable -> L16 Exception -> L18
        L18:
            e = e;
            r2 = r6;
        L30:
            FileLog.e(e);     // Catch: Throwable -> L5
            if (r2 == null) goto L33;
            r2.dispose();
        L16:
            th = th;
            r2 = r6;
        L35:
            if (r2 == null) goto L37;
            r2.dispose();
        L37:
            throw th;
        L9:
            r6 = r18.getDatabase().queryFinalized("SELECT data, mid FROM messages_v2 WHERE uid = ? AND mid <= ? ORDER BY mid DESC LIMIT 10", new Object[]{Long.valueOf(-r19), Integer.valueOf(r17)});     // Catch: Throwable -> L5 Exception -> L7
        L7:
            e = e;
        L5:
            th = th;
            goto L35
        }

        public void fetch(long r12, int r14) {
            if (this.loaded == true) goto L7;
            if (this.loading == true) goto L7;
        L13:
            int r10 = this.searchId + 1;
            this.searchId = r10;
            this.loading = true;
            this.channel_id = r12;
            this.message_id = r14;
            long r8 = UserConfig.getInstance(this.currentAccount).getClientUserId();
            MessagesStorage r5 = MessagesStorage.getInstance(this.currentAccount);
            r5.getStorageQueue().postRunnable(new ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda0(this, r14, r5, r12, r8, r10));
            return;
        L7:
            if (this.channel_id == r12) goto L9;
        L12:
            this.loaded = false;
            this.messageObjects.clear();
            goto L13
        L9:
            if (this.message_id != r14) goto L12;
        }

        public void fetch(TLRPC.UserFull r3) {
            if (r3 != null) goto L4;
        L8:
            this.searchId++;
            this.loaded = true;
            this.messageObjects.clear();
            done(false);
            return;
        L4:
            if ((r3.flags2 & 64) == 0) goto L8;
            fetch(r3.personal_channel_id, r3.personal_channel_message);
        }

        public void subscribe(Runnable r2) {
            if (this.loaded == false) goto L6;
            r2.run();
            return;
        L6:
            this.callbacks.add(r2);
        }
    }

    public ProfileChannelCell(BaseFragment r24) {
        super(r24.getContext());
        CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.loadingAlpha = new AnimatedFloat(320, r8);
        this.set = false;
        Context r11 = r24.getContext();
        Theme.ResourcesProvider r15 = r24.getResourceProvider();
        this.resourcesProvider = r15;
        LinearLayout r9 = new LinearLayout(r11);
        r9.setOrientation(0);
        addView(r9, LayoutHelper.createFrame(-1, -2.0f, 55, 16.66f, 11.6f, 16.66f, 0.0f));
        TextView r2 = new TextView(r11);
        this.headerView = r2;
        r2.setTypeface(AndroidUtilities.bold());
        r2.setTextSize(1, 14.0f);
        r2.setText(LocaleController.getString(R.string.ProfileChannel));
        r9.addView(r2, LayoutHelper.createLinear(-2, -2, 51));
        ClickableAnimatedTextView r22 = new ClickableAnimatedTextView(r11);
        this.subscribersView = r22;
        r22.getDrawable().setHacks(true, true, true);
        r22.setAnimationProperties(0.3f, 0, 165, r8);
        r22.setTypeface(AndroidUtilities.bold());
        r22.setTextSize(AndroidUtilities.dp(11.0f));
        r22.setPadding(AndroidUtilities.dp(4.33f), 0, AndroidUtilities.dp(4.33f), 0);
        r22.setGravity(3);
        r9.addView(r22, LayoutHelper.createLinear(-1, 17, 51, 4, 1, 4, 0));
        DialogCell r92 = new DialogCell(null, r11, false, true, UserConfig.selectedAccount, r15);
        this.dialogCell = r92;
        r92.setBackgroundColor(0);
        r92.setDialogCellDelegate(new AnonymousClass1(this, r24, r11));
        r92.avatarStart = 15;
        r92.messagePaddingStart = 83;
        addView(r92, LayoutHelper.createFrame(-1, -2, 87));
        updateColors();
        setWillNotDraw(false);
        LoadingDrawable r1 = new LoadingDrawable();
        this.loadingDrawable = r1;
        int r0 = Theme.key_listSelector;
        r1.setColors(Theme.multAlpha(Theme.getColor(r0, r15), 1.25f), Theme.multAlpha(Theme.getColor(r0, r15), 0.8f));
        r1.setRadiiDp(8.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r7) {
        super.dispatchDraw(r7);
        float r0 = this.loadingAlpha.set(this.loading);
        if (r0 <= 0.0f) goto L6;
        this.loadingDrawable.setAlpha((int) (r0 * 255.0f));
        RectF r02 = AndroidUtilities.rectTmp;
        r02.set(this.dialogCell.getX() + AndroidUtilities.dp(this.dialogCell.messagePaddingStart + 6), this.dialogCell.getY() + AndroidUtilities.dp(38.0f), (this.dialogCell.getX() + AndroidUtilities.dp(this.dialogCell.messagePaddingStart + 6)) + (getWidth() * 0.5f), this.dialogCell.getY() + AndroidUtilities.dp(46.33f));
        this.loadingDrawable.setBounds(r02);
        this.loadingDrawable.draw(r7);
        r02.set(this.dialogCell.getX() + AndroidUtilities.dp(this.dialogCell.messagePaddingStart + 6), this.dialogCell.getY() + AndroidUtilities.dp(56.0f), (this.dialogCell.getX() + AndroidUtilities.dp(this.dialogCell.messagePaddingStart + 6)) + (getWidth() * 0.36f), this.dialogCell.getY() + AndroidUtilities.dp(64.33f));
        this.loadingDrawable.setBounds(r02);
        this.loadingDrawable.draw(r7);
        r02.set(((this.dialogCell.getX() + this.dialogCell.getWidth()) - AndroidUtilities.dp(16.0f)) - AndroidUtilities.dp(43.0f), this.dialogCell.getY() + AndroidUtilities.dp(12.0f), (this.dialogCell.getX() + this.dialogCell.getWidth()) - AndroidUtilities.dp(16.0f), this.dialogCell.getY() + AndroidUtilities.dp(20.33f));
        this.loadingDrawable.setBounds(r02);
        this.loadingDrawable.draw(r7);
        invalidate();
        return;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(102.0f), TLObject.FLAG_30));
    }

    public abstract int processColor(int r1);

    public void set(TLRPC.Chat r11, ArrayList<MessageObject> r12) {
        boolean r6 = this.set;
        if (r11 != null) goto L5;
    L8:
        boolean r0 = true;
    L9:
        this.subscribersView.cancelAnimation();
        float r2 = 0.0f;
        this.subscribersView.setPivotX(0.0f);
        AnimatedTextView r1 = this.subscribersView;
        float r3 = 1.0f;
        if (r6 == false) goto L23;
        ViewPropertyAnimator r13 = r1.animate();
        if (r0 == false) goto L14;
        r2 = 1.0f;
    L14:
        ViewPropertyAnimator r14 = r13.alpha(r2);
        if (r0 == false) goto L17;
        float r4 = 1.0f;
    L18:
        ViewPropertyAnimator r15 = r14.scaleX(r4);
        if (r0 == true) goto L22;
        r3 = 0.8f;
    L22:
        r15.scaleY(r3).setDuration(420).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
    L34:
        if (r11 == null) goto L51;
        int[] r02 = new int[1];
        boolean r16 = AndroidUtilities.isAccessibilityScreenReaderEnabled();
        int r22 = r11.participants_count;
        if (r16 == false) goto L38;
        r02[0] = r22;
        String r17 = String.valueOf(r22);
    L39:
        this.subscribersView.setText(LocaleController.formatPluralString("Subscribers", r02[0], new Object[0]).replace(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r02[0])}), r17), true);
        if (r12 != null) goto L42;
    L45:
        boolean r03 = true;
    L46:
        this.loading = r03;
        if (r03 == false) goto L49;
        this.dialogCell.setDialog(-r11.id, null, 0, false, r6);
    L50:
        this.dialogCell.update(0);
        this.dialogCell.invalidate();
        goto L51
    L49:
        MessageObject r32 = r12.get(r12.size() - 1);
        this.dialogCell.setDialog(-r11.id, r32, r12, r32.messageOwner.date, false, r6);
        r6 = r6;
        goto L50
    L42:
        if (r12.isEmpty() == true) goto L45;
        r03 = false;
        goto L46
    L38:
        r17 = LocaleController.formatShortNumber(r22, r02);
    L51:
        if (r6 == true) goto L53;
        this.loadingAlpha.set(this.loading, true);
    L53:
        invalidate();
        this.set = true;
        return;
    L17:
        r4 = 0.8f;
        goto L18
    L23:
        if (r0 == false) goto L25;
        float r42 = 1.0f;
    L26:
        r1.setAlpha(r42);
        AnimatedTextView r18 = this.subscribersView;
        if (r0 == false) goto L29;
        float r43 = 1.0f;
    L30:
        r18.setScaleX(r43);
        AnimatedTextView r19 = this.subscribersView;
        if (r0 == false) goto L33;
        r2 = 1.0f;
    L33:
        r19.setScaleY(r2);
        goto L34
    L29:
        r43 = 0.0f;
        goto L30
    L25:
        r42 = 0.0f;
        goto L26
    L5:
        if (r11.participants_count > 0) goto L8;
        r0 = false;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        int r0 = processColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader, this.resourcesProvider));
        this.subscribersView.setTextColor(r0);
        this.subscribersView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), Theme.multAlpha(r0, 0.1f)));
        this.headerView.setTextColor(r0);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.loadingDrawable != r2) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
