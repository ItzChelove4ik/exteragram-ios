package org.telegram.ui.bots;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.collection.LongSparseArray;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.PreviewView;
import org.telegram.ui.TopicsFragment;
import org.telegram.ui.web.HttpGetFileTask;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes7.dex */
public class BotShareSheet extends BottomSheetWithRecyclerListView {
    private final ChatActionCell actionCell;
    private UniversalAdapter adapter;
    private final long botId;
    private final String botName;
    private final ButtonWithCounterView button;
    private final FrameLayout buttonContainer;
    private final LinearLayout chatListView;
    private final SizeNotifierFrameLayout chatView;
    private final int currentAccount;
    private final TLRPC.TL_messages_preparedInlineMessage message;
    private final ChatMessageCell messageCell;
    private MessageObject messageObject;
    private boolean openedDialogsActivity;
    private boolean sent;
    private final Utilities.Callback2<String, ArrayList<Long>> whenDone;

    /* renamed from: org.telegram.ui.bots.BotShareSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements NotificationCenter.NotificationCenterDelegate {
        final /* synthetic */ int val$currentAccount;
        final /* synthetic */ NotificationCenter.NotificationCenterDelegate[] val$delegateToRemove;
        final /* synthetic */ long val$pendingId;
        final /* synthetic */ Utilities.Callback val$whenLoaded;

        public AnonymousClass1(long r1, NotificationCenter.NotificationCenterDelegate[] r3, int r4, Utilities.Callback r5) {
            this.val$pendingId = r1;
            this.val$delegateToRemove = r3;
            this.val$currentAccount = r4;
            this.val$whenLoaded = r5;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r4, int r5, Object... r6) {
            int r52 = NotificationCenter.didReceivedWebpagesInUpdates;
            if (r4 != r52) goto L17;
            LongSparseArray r62 = (LongSparseArray) r6[0];
            if (r62 != null) goto L7;
            return;
        L7:
            if (r62.containsKey(this.val$pendingId) == false) goto L19;
            TLRPC.WebPage r63 = (TLRPC.WebPage) r62.get(this.val$pendingId);
            if (this.val$delegateToRemove[0] == null) goto L11;
            NotificationCenter.getInstance(this.val$currentAccount).removeObserver(this.val$delegateToRemove[0], r52);
            this.val$delegateToRemove[0] = null;
        L11:
            Utilities.Callback r3 = this.val$whenLoaded;
            if ((r63 instanceof TLRPC.TL_webPage) == true) goto L15;
            r63 = null;
        L15:
            r3.run(r63);
            return;
        L19:
            return;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotShareSheet$2, reason: invalid class name */
    public class AnonymousClass2 implements ChatActionCell.ChatActionCellDelegate {
        final /* synthetic */ BotShareSheet this$0;

        public AnonymousClass2(BotShareSheet r1) {
            this.this$0 = r1;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotShareSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends ChatMessageCell {
        final /* synthetic */ BotShareSheet this$0;

        public AnonymousClass3(BotShareSheet r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public boolean isDrawSelectionBackground() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotShareSheet$4, reason: invalid class name */
    public class AnonymousClass4 implements ChatMessageCell.ChatMessageCellDelegate {
        final /* synthetic */ BotShareSheet this$0;

        public AnonymousClass4(BotShareSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean canPerformActions() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotShareSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends SizeNotifierFrameLayout {
        final /* synthetic */ BotShareSheet this$0;

        public AnonymousClass5(BotShareSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isActionBarVisible() {
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isStatusBarVisible() {
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean useRootView() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotShareSheet$6, reason: invalid class name */
    public class AnonymousClass6 extends DialogsActivity {
        final /* synthetic */ BotShareSheet this$0;
        final /* synthetic */ Utilities.Callback2 val$whenDone;

        public AnonymousClass6(BotShareSheet r1, Bundle r2, Utilities.Callback2 r3) {
            this.this$0 = r1;
            this.val$whenDone = r3;
            super(r2);
        }

        @Override // org.telegram.ui.DialogsActivity
        public boolean clickSelectsDialog() {
            return true;
        }

        @Override // org.telegram.ui.DialogsActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onFragmentDestroy() {
            super.onFragmentDestroy();
            if (BotShareSheet.m21342$$Nest$fgetsent(this.this$0) == true) goto L8;
            BotShareSheet.m21343$$Nest$fputsent(this.this$0, true);
            Utilities.Callback2 r2 = this.val$whenDone;
            if (r2 == null) goto L9;
            r2.run("USER_DECLINED", null);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotShareSheet$7, reason: invalid class name */
    public class AnonymousClass7 extends MessageObject {
        public AnonymousClass7(int r1, TLRPC.Message r2, boolean r3, boolean r4) {
            super(r1, r2, r3, r4);
        }

        @Override // org.telegram.messenger.MessageObject
        public boolean isOut() {
            return false;
        }

        @Override // org.telegram.messenger.MessageObject
        public boolean isOutOwner() {
            return false;
        }
    }

    /* renamed from: $r8$lambda$-17G8wWAR-GR8C2AS8-tVfVpEZg, reason: not valid java name */
    public static /* synthetic */ void m21339$r8$lambda$17G8wWARGR8C2AS8tVfVpEZg(AlertDialog r11, Context r12, int r13, long r14, TLRPC.TL_messages_preparedInlineMessage r16, Theme.ResourcesProvider r17, Runnable r18, Utilities.Callback2 r19, TLRPC.WebPage r20) {
        r11.dismiss();
        new BotShareSheet(r12, r13, r14, r16, null, r20, r17, r18, r19).show();
    }

    public static /* synthetic */ boolean $r8$lambda$0fiLho5qdRFd06VbO76C_qE5xfk(BotShareSheet r0, int r1, TLRPC.TL_messages_preparedInlineMessage r2, long r3, BaseFragment r5, Utilities.Callback2 r6, DialogsActivity r7, ArrayList r8, CharSequence r9, boolean r10, boolean r11, int r12, int r13, TopicsFragment r14) {
        return r0.lambda$new$9(r1, r2, r3, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14);
    }

    public static /* synthetic */ void $r8$lambda$0o5aMPMbc8SQ2o4sN6GjfjuxIVQ(AlertDialog r11, Context r12, int r13, long r14, TLRPC.TL_messages_preparedInlineMessage r16, File[] r17, Theme.ResourcesProvider r18, Runnable r19, Utilities.Callback2 r20) {
        r11.dismiss();
        new BotShareSheet(r12, r13, r14, r16, r17[0], null, r18, r19, r20).show();
    }

    public static /* synthetic */ void $r8$lambda$F_fmB5oaljGgrU5BGz3SGrAIyRY(TLObject r12, int r13, AlertDialog r14, Context r15, long r16, Theme.ResourcesProvider r18, Runnable r19, Utilities.Callback2 r20) {
        if ((r12 instanceof TLRPC.TL_messages_preparedInlineMessage) == false) goto L31;
        TLRPC.TL_messages_preparedInlineMessage r6 = (TLRPC.TL_messages_preparedInlineMessage) r12;
        TLRPC.BotInlineMessage r122 = r6.result.send_message;
        if ((r122 instanceof TLRPC.TL_botInlineMessageMediaWebPage) == false) goto L10;
        TLRPC.TL_botInlineMessageMediaWebPage r123 = (TLRPC.TL_botInlineMessageMediaWebPage) r122;
        if (TextUtils.isEmpty(r123.url) == true) goto L10;
        r14.setOnCancelListener(new BotShareSheet$$ExternalSyntheticLambda3(loadWebPagePreview(r13, r123.url, new BotShareSheet$$ExternalSyntheticLambda2(r14, r15, r13, r16, r6, r18, r19, r20))));
        return;
    L10:
        File[] r7 = new File[1];
        BotShareSheet$$ExternalSyntheticLambda4 r0 = new BotShareSheet$$ExternalSyntheticLambda4(r14, r15, r13, r16, r6, r7, r18, r19, r20);
        TLRPC.WebDocument r124 = r6.result.content;
        if (r124 != null) goto L13;
    L28:
        r0.run();
        return;
    L13:
        if (TextUtils.isEmpty(r124.url) == true) goto L28;
        TLRPC.BotInlineResult r125 = r6.result;
        TLRPC.BotInlineMessage r132 = r125.send_message;
        if ((r132 instanceof TLRPC.TL_botInlineMessageMediaAuto) == false) goto L17;
    L18:
        String r126 = r125.content.url;
        String r133 = ImageLoader.getHttpUrlExtension(r126, null);
        if (TextUtils.isEmpty(r133) == false) goto L21;
        String r134 = FileLoader.getExtensionByMimeType(r6.result.content.mime_type);
    L22:
        File r152 = new File(FileLoader.getDirectory(4), Utilities.MD5(r126) + r134);
        if (r152.exists() == true) goto L26;
        HttpGetFileTask r135 = new HttpGetFileTask(new BotShareSheet$$ExternalSyntheticLambda5(r7, r0), null);
        r135.setDestFile(r152);
        r135.setMaxSize(8388608);
        r135.execute(new String[]{r126});
        r14.setOnCancelListener(new BotShareSheet$$ExternalSyntheticLambda6(r135));
        return;
    L26:
        r0.run();
        return;
    L21:
        r134 = "." + r133;
        goto L22
    L17:
        if ((r132 instanceof TLRPC.TL_botInlineMessageMediaWebPage) == false) goto L28;
    L31:
        if (r20 == null) goto L34;
        r20.run("MESSAGE_EXPIRED", null);
        return;
    }

    public static /* synthetic */ void $r8$lambda$H6rY4tHTKZdG56Ilq3PeqEHvCN4(int[] r7, Utilities.Callback r8, NotificationCenter.NotificationCenterDelegate[] r9, int r10, TL_account.webPagePreview r11, TLRPC.TL_error r12) {
        r7[0] = -1;
        TLRPC.MessageMedia r72 = r11.media;
        TLRPC.WebPage r122 = null;
        if ((r72 instanceof TLRPC.TL_messageMediaEmpty) == true) goto L20;
        TLRPC.WebPage r112 = r72.webpage;
        if ((r112 instanceof TLRPC.TL_webPageEmpty) == true) goto L20;
        if ((r72 instanceof TLRPC.TL_messageMediaWebPage) == true) goto L10;
        r8.run(null);
        return;
    L10:
        if ((r112 instanceof TLRPC.TL_webPagePending) == false) goto L14;
        AnonymousClass1 r1 = new AnonymousClass1(r112.id, r9, r10, r8);
        r9[0] = r1;
        NotificationCenter.getInstance(r10).addObserver(r1, NotificationCenter.didReceivedWebpagesInUpdates);
        return;
    L14:
        if ((r112 instanceof TLRPC.TL_webPage) == false) goto L16;
        r122 = r112;
    L16:
        r8.run(r122);
        return;
    L20:
        r8.run(null);
    }

    /* renamed from: $r8$lambda$LNROlNh-lx5gJOifXQCOoF3A5Pk, reason: not valid java name */
    public static /* synthetic */ void m21340$r8$lambda$LNROlNhlx5gJOifXQCOoF3A5Pk(int[] r4, int r5, NotificationCenter.NotificationCenterDelegate[] r6) {
        if (r4[0] < 0) goto L6;
        ConnectionsManager.getInstance(r5).cancelRequest(r4[0], true);
        r4[0] = -1;
    L6:
        if (r6[0] == null) goto L9;
        NotificationCenter.getInstance(r5).removeObserver(r6[0], NotificationCenter.didReceivedWebpagesInUpdates);
        r6[0] = null;
        return;
    }

    public static /* synthetic */ void $r8$lambda$UU5hNZVorMM4DDGJxEneGJn0QBs(int r3, AlertDialog r4, Context r5, long r6, Theme.ResourcesProvider r8, Runnable r9, Utilities.Callback2 r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new BotShareSheet$$ExternalSyntheticLambda1(r11, r3, r4, r5, r6, r8, r9, r10));
    }

    public static /* synthetic */ void $r8$lambda$aGJnF63itUB3rWRxUWMUDLwYWSg(Runnable r0, DialogInterface r1) {
        r0.run();
    }

    /* renamed from: $r8$lambda$gk81yP_ZCVQadcySBmcZi6q25-U, reason: not valid java name */
    public static /* synthetic */ void m21341$r8$lambda$gk81yP_ZCVQadcySBmcZi6q25U(File[] r1, Runnable r2, File r3) {
        r1[0] = r3;
        r2.run();
    }

    public static /* synthetic */ void $r8$lambda$mY8LuvEV_3qmggB33aqboOOH8Q8(HttpGetFileTask r0, DialogInterface r1) {
        r0.cancel(true);
    }

    public static /* synthetic */ void $r8$lambda$yPCwFuduZpR4sP_s2aNVh6B3hYE(BotShareSheet r0, TLRPC.TL_messages_preparedInlineMessage r1, Utilities.Callback2 r2, int r3, long r4, Runnable r6, View r7) {
        r0.lambda$new$10(r1, r2, r3, r4, r6, r7);
    }

    /* renamed from: -$$Nest$fgetsent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21342$$Nest$fgetsent(BotShareSheet r0) {
        return r0.sent;
    }

    /* renamed from: -$$Nest$fputsent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21343$$Nest$fputsent(BotShareSheet r0, boolean r1) {
        r0.sent = r1;
    }

    public BotShareSheet(Context r23, int r24, long r25, TLRPC.TL_messages_preparedInlineMessage r27, File r28, TLRPC.WebPage r29, Theme.ResourcesProvider r30, Runnable r31, Utilities.Callback2<String, ArrayList<Long>> r32) {
        super(r23, null, false, false, false, r30);
        this.openedDialogsActivity = false;
        this.sent = false;
        this.currentAccount = r24;
        this.message = r27;
        this.botId = r25;
        this.botName = UserObject.getUserName(MessagesController.getInstance(r24).getUser(Long.valueOf(r25)));
        this.whenDone = r32;
        setSlidingActionBar();
        this.headerPaddingTop = AndroidUtilities.dp(4.0f);
        this.headerPaddingBottom = AndroidUtilities.dp(-10.0f);
        this.messageObject = convert(r24, r25, r27.result, r28, r29);
        ChatActionCell r0 = new ChatActionCell(r23, false, r30);
        this.actionCell = r0;
        r0.setDelegate(new AnonymousClass2(this));
        r0.setCustomText(LocaleController.getString(R.string.BotShareMessagePreview));
        AnonymousClass3 r15 = new AnonymousClass3(this, r23, r24);
        this.messageCell = r15;
        r15.setDelegate(new AnonymousClass4(this));
        r15.setMessageObject(this.messageObject, null, false, false, false);
        LinearLayout r1 = new LinearLayout(r23);
        this.chatListView = r1;
        r1.setOrientation(1);
        r1.addView(r0, LayoutHelper.createLinear(-1, -2));
        r1.addView(r15, LayoutHelper.createLinear(-1, -2));
        AnonymousClass5 r02 = new AnonymousClass5(this, r23);
        this.chatView = r02;
        r02.setBackgroundImage(PreviewView.getBackgroundDrawable(null, r24, r25, Theme.isCurrentThemeDark()), false);
        r02.addView(r1, LayoutHelper.createFrame(-1, -1.0f, 119, 4.0f, 8.0f, 4.0f, 8.0f));
        FrameLayout r152 = new FrameLayout(r23);
        this.buttonContainer = r152;
        ButtonWithCounterView r11 = new ButtonWithCounterView(r23, r30).setRound();
        this.button = r11;
        r11.setText(LocaleController.getString(R.string.BotShareMessageShare), false);
        r11.setOnClickListener(new BotShareSheet$$ExternalSyntheticLambda10(this, r27, r32, r24, r25, r31));
        r152.addView(r11, LayoutHelper.createFrame(-1, 48.0f, 119, 10.0f, 10.0f, 10.0f, 10.0f));
        ViewGroup r03 = this.containerView;
        int r12 = this.backgroundPaddingLeft;
        r03.addView(r152, LayoutHelper.createFrameMarginPx(-1, -2.0f, 87, r12, 0, r12, 0));
        RecyclerListView r04 = this.recyclerListView;
        int r13 = this.backgroundPaddingLeft;
        r04.setPadding(r13, 0, r13, AndroidUtilities.dp(68.0f) + 1);
        this.recyclerListView.setSections();
        int r05 = Theme.key_windowBackgroundGray;
        setBackgroundColor(getThemedColor(r05));
        fixNavigationBar(getThemedColor(r05));
        this.adapter.update(false);
    }

    public static MessageObject convert(int r18, long r19, TLRPC.BotInlineResult r21, File r22, TLRPC.WebPage r23) {
        if (r22 == null) goto L135;
        if (r22.exists() == false) goto L135;
        if (r23 != null) goto L135;
        String r0 = r21.type;
        String r1 = r22.getAbsolutePath();
        r0.getClass();
        switch(r0.hashCode()) {
            case -1890252483: goto L36;
            case 102340: goto L32;
            case 3143036: goto L28;
            case 93166550: goto L24;
            case 106642994: goto L20;
            case 112202875: goto L16;
            case 112386354: goto L12;
            default: goto L10;
        };
    L10:
        char r2 = 65535;
    L40:
        switch(r2) {
            case 0: goto L49;
            case 1: goto L49;
            case 2: goto L49;
            case 3: goto L49;
            case 4: goto L43;
            case 5: goto L49;
            case 6: goto L49;
            default: goto L41;
        };
    L41:
        TLRPC.TL_photo r5 = null;
        TLRPC.TL_document r6 = null;
    L133:
        return convert(r18, r19, r21, r5, r6, null);
    L49:
        TLRPC.TL_document r24 = new TLRPC.TL_document();
        r24.id = 0;
        r24.size = 0;
        r24.dc_id = 0;
        r24.mime_type = r21.content.mime_type;
        r24.file_reference = new byte[0];
        r24.date = ConnectionsManager.getInstance(r18).getCurrentTime();
        TLRPC.TL_documentAttributeFilename r11 = new TLRPC.TL_documentAttributeFilename();
        r24.attributes.add(r11);
        switch(r0.hashCode()) {
            case -1890252483: goto L73;
            case 102340: goto L69;
            case 3143036: goto L65;
            case 93166550: goto L61;
            case 112202875: goto L57;
            case 112386354: goto L53;
            default: goto L51;
        };
    L51:
        char r16 = 65535;
    L77:
        switch(r16) {
            case 0: goto L105;
            case 1: goto L101;
            case 2: goto L97;
            case 3: goto L93;
            case 4: goto L80;
            case 5: goto L79;
            default: goto L78;
        };
    L78:
        TLRPC.TL_photo r3 = null;
    L123:
        if (r11.file_name != null) goto L126;
        r11.file_name = "file";
    L126:
        if (r24.mime_type != null) goto L129;
        r24.mime_type = "application/octet-stream";
    L129:
        if (r24.thumbs.isEmpty() == false) goto L131;
        TLRPC.TL_photoSize r02 = new TLRPC.TL_photoSize();
        int[] r12 = MessageObject.getInlineResultWidthAndHeight(r21);
        r02.w = r12[0];
        r02.h = r12[1];
        r02.size = 0;
        r02.location = new TLRPC.TL_fileLocationUnavailable();
        r02.type = "x";
        r24.thumbs.add(r02);
        r24.flags |= 1;
    L131:
        r6 = r24;
        r5 = r3;
        goto L133
    L79:
        TLRPC.TL_documentAttributeAudio r03 = new TLRPC.TL_documentAttributeAudio();
        r03.duration = MessageObject.getInlineResultDuration(r21);
        r03.voice = true;
        r11.file_name = "audio.ogg";
        r24.attributes.add(r03);
        goto L78
    L80:
        r11.file_name = "video.mp4";
        TLRPC.TL_documentAttributeVideo r13 = new TLRPC.TL_documentAttributeVideo();
        int[] r7 = MessageObject.getInlineResultWidthAndHeight(r21);
        r13.w = r7[0];
        r13.h = r7[1];
        r13.duration = MessageObject.getInlineResultDuration(r21);
        r13.supports_streaming = true;
        r24.attributes.add(r13);
    L88:
        th = move-exception;
        FileLog.e(th);
        goto L78
    L82:
        if (r21.thumb == null) goto L78;
        Bitmap r14 = ImageLoader.loadBitmap(new File(FileLoader.getDirectory(4), Utilities.MD5(r21.thumb.url) + "." + ImageLoader.getHttpUrlExtension(r21.thumb.url, "jpg")).getAbsolutePath(), null, 90.0f, 90.0f, true);     // Catch: Throwable -> L88
        if (r14 == null) goto L78;
        TLRPC.PhotoSize r04 = ImageLoader.scaleAndSaveImage(r14, 90.0f, 90.0f, 55, false);     // Catch: Throwable -> L88
        if (r04 == null) goto L90;
        r24.thumbs.add(r04);     // Catch: Throwable -> L88
        r24.flags |= 1;
    L90:
        r14.recycle();     // Catch: Throwable -> L88
        goto L78
    L93:
        TLRPC.TL_documentAttributeAudio r05 = new TLRPC.TL_documentAttributeAudio();
        r05.duration = MessageObject.getInlineResultDuration(r21);
        r05.title = r21.title;
        int r15 = r05.flags;
        r05.flags = r15 | 1;
        String r32 = r21.description;
        if (r32 == null) goto L96;
        r05.performer = r32;
        r05.flags = r15 | 3;
    L96:
        r11.file_name = "audio.mp3";
        r24.attributes.add(r05);
        goto L78
    L97:
        int r06 = r21.content.mime_type.lastIndexOf(47);
        if (r06 == (-1)) goto L100;
        r11.file_name = "file.".concat(r21.content.mime_type.substring(r06 + 1));
        goto L78
    L100:
        r11.file_name = "file";
        goto L78
    L101:
        r11.file_name = "animation.gif";
        if (r1.endsWith("mp4") == false) goto L104;
        r24.mime_type = "video/mp4";
        r24.attributes.add(new TLRPC.TL_documentAttributeAnimated());
        goto L78
    L104:
        r24.mime_type = "image/gif";
        goto L78
    L105:
        TLRPC.TL_documentAttributeSticker r17 = new TLRPC.TL_documentAttributeSticker();
        r17.alt = _UrlKt.FRAGMENT_ENCODE_SET;
        r17.stickerset = new TLRPC.TL_inputStickerSetEmpty();
        r24.attributes.add(r17);
        TLRPC.TL_documentAttributeImageSize r110 = new TLRPC.TL_documentAttributeImageSize();
        int[] r72 = MessageObject.getInlineResultWidthAndHeight(r21);
        r110.w = r72[0];
        r110.h = r72[1];
        r24.attributes.add(r110);
        r11.file_name = "sticker.webp";
    L119:
        th = th;
        r3 = null;
    L121:
        FileLog.e(th);
        goto L123
    L107:
        if (r21.thumb == null) goto L78;
        r3 = null;
        Bitmap r111 = ImageLoader.loadBitmap(new File(FileLoader.getDirectory(4), Utilities.MD5(r21.thumb.url) + "." + ImageLoader.getHttpUrlExtension(r21.thumb.url, "webp")).getAbsolutePath(), null, 90.0f, 90.0f, true);     // Catch: Throwable -> L115
        if (r111 == null) goto L123;
        TLRPC.PhotoSize r07 = ImageLoader.scaleAndSaveImage(r111, 90.0f, 90.0f, 55, false);     // Catch: Throwable -> L115
        if (r07 == null) goto L117;
        r24.thumbs.add(r07);     // Catch: Throwable -> L115
        r24.flags |= 1;
    L117:
        r111.recycle();     // Catch: Throwable -> L115
    L115:
        th = th;
        goto L121
    L53:
        if (r0.equals("voice") == false) goto L51;
        r16 = 5;
        goto L77
    L57:
        if (r0.equals(MediaStreamTrack.VIDEO_TRACK_KIND) == false) goto L51;
        r16 = 4;
        goto L77
    L61:
        if (r0.equals(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L51;
        r16 = 3;
        goto L77
    L65:
        if (r0.equals("file") == false) goto L51;
        r16 = 2;
        goto L77
    L69:
        if (r0.equals("gif") == false) goto L51;
        r16 = 1;
        goto L77
    L73:
        if (r0.equals("sticker") == false) goto L51;
        r16 = 0;
        goto L77
    L43:
        if (r22.exists() == false) goto L45;
        TLRPC.TL_photo r08 = SendMessagesHelper.getInstance(r18).generatePhotoSizes(r1, null);
    L46:
        if (r08 != null) goto L48;
        r08 = new TLRPC.TL_photo();
        r08.date = ConnectionsManager.getInstance(r18).getCurrentTime();
        r08.file_reference = new byte[0];
        TLRPC.TL_photoSize r112 = new TLRPC.TL_photoSize();
        int[] r25 = MessageObject.getInlineResultWidthAndHeight(r21);
        r112.w = r25[0];
        r112.h = r25[1];
        r112.size = 1;
        r112.location = new TLRPC.TL_fileLocationUnavailable();
        r112.type = "x";
        r08.sizes.add(r112);
    L48:
        r5 = r08;
        r6 = null;
        goto L133
    L45:
        r08 = null;
        goto L46
    L12:
        if (r0.equals("voice") == false) goto L10;
        r2 = 6;
        goto L40
    L16:
        if (r0.equals(MediaStreamTrack.VIDEO_TRACK_KIND) == false) goto L10;
        r2 = 5;
        goto L40
    L20:
        if (r0.equals("photo") == false) goto L10;
        r2 = 4;
        goto L40
    L24:
        if (r0.equals(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L10;
        r2 = 3;
        goto L40
    L28:
        if (r0.equals("file") == false) goto L10;
        r2 = 2;
        goto L40
    L32:
        if (r0.equals("gif") == false) goto L10;
        r2 = 1;
        goto L40
    L36:
        if (r0.equals("sticker") == false) goto L10;
        r2 = 0;
    L135:
        return convert(r18, r19, r21, null, null, r23);
    }

    public static MessageObject convert(int r4, long r5, TLRPC.BotInlineResult r7, TLRPC.Photo r8, TLRPC.Document r9, TLRPC.WebPage r10) {
        if (r8 != null) goto L4;
        r8 = r7.photo;
    L4:
        if (r9 != null) goto L6;
        r9 = r7.document;
    L6:
        TLRPC.TL_message r0 = new TLRPC.TL_message();
        r0.out = false;
        r0.flags |= 2048;
        r0.via_bot_id = r5;
        r0.date = ConnectionsManager.getInstance(r4).getCurrentTime();
        r0.peer_id = MessagesController.getInstance(r4).getPeer(UserConfig.getInstance(r4).getClientUserId());
        r0.from_id = MessagesController.getInstance(r4).getPeer(UserConfig.getInstance(r4).getClientUserId());
        TLRPC.BotInlineMessage r52 = r7.send_message;
        if (r52 != null) goto L9;
    L44:
        if (r8 == null) goto L46;
        TLRPC.TL_messageMediaPhoto r53 = new TLRPC.TL_messageMediaPhoto();
        r53.photo = r8;
        r0.flags |= 512;
        r0.media = r53;
    L48:
        TLRPC.BotInlineMessage r54 = r7.send_message;
        if (r54 == null) goto L54;
        TLRPC.ReplyMarkup r55 = r54.reply_markup;
        if (r55 == null) goto L54;
        r0.flags |= 64;
        r0.reply_markup = r55;
    L54:
        return new AnonymousClass7(r4, r0, true, true);
    L46:
        if (r9 == null) goto L48;
        TLRPC.TL_messageMediaDocument r56 = new TLRPC.TL_messageMediaDocument();
        r56.flags |= 1;
        r56.voice = "voice".equalsIgnoreCase(r7.type);
        r56.round = "round".equalsIgnoreCase(r7.type);
        r56.document = r9;
        r0.flags |= 512;
        r0.media = r56;
        goto L48
    L9:
        if ((r52 instanceof TLRPC.TL_botInlineMessageText) == false) goto L12;
        TLRPC.TL_botInlineMessageText r57 = (TLRPC.TL_botInlineMessageText) r52;
        r0.message = r57.message;
        r0.entities = r57.entities;
        goto L44
    L12:
        if ((r52 instanceof TLRPC.TL_botInlineMessageMediaContact) == false) goto L15;
        TLRPC.TL_botInlineMessageMediaContact r58 = (TLRPC.TL_botInlineMessageMediaContact) r52;
        TLRPC.TL_messageMediaContact r102 = new TLRPC.TL_messageMediaContact();
        r102.phone_number = r58.phone_number;
        r102.first_name = r58.first_name;
        r102.last_name = r58.last_name;
        r102.vcard = r58.vcard;
        r0.flags |= 512;
        r0.media = r102;
        goto L44
    L15:
        if ((r52 instanceof TLRPC.TL_botInlineMessageMediaGeo) == false) goto L18;
        TLRPC.TL_messageMediaGeo r103 = new TLRPC.TL_messageMediaGeo();
        r103.geo = ((TLRPC.TL_botInlineMessageMediaGeo) r52).geo;
        r0.flags |= 512;
        r0.media = r103;
        goto L44
    L18:
        if ((r52 instanceof TLRPC.TL_botInlineMessageMediaVenue) == false) goto L21;
        TLRPC.TL_botInlineMessageMediaVenue r59 = (TLRPC.TL_botInlineMessageMediaVenue) r52;
        TLRPC.TL_messageMediaVenue r104 = new TLRPC.TL_messageMediaVenue();
        r104.geo = r59.geo;
        r104.title = r59.title;
        r104.address = r59.address;
        r104.venue_id = r59.venue_id;
        r104.provider = r59.venue_type;
        r0.flags |= 512;
        r0.media = r104;
        goto L44
    L21:
        if ((r52 instanceof TLRPC.TL_botInlineMessageMediaAuto) == false) goto L26;
        TLRPC.TL_botInlineMessageMediaAuto r510 = (TLRPC.TL_botInlineMessageMediaAuto) r52;
        r0.message = r510.message;
        if (TLObject.hasFlag(r510.flags, 2) == false) goto L44;
        r0.flags |= 128;
        r0.entities = r510.entities;
        goto L44
    L26:
        if ((r52 instanceof TLRPC.TL_botInlineMessageMediaInvoice) == false) goto L32;
        TLRPC.TL_botInlineMessageMediaInvoice r511 = (TLRPC.TL_botInlineMessageMediaInvoice) r52;
        TLRPC.TL_messageMediaInvoice r105 = new TLRPC.TL_messageMediaInvoice();
        r105.shipping_address_requested = r511.shipping_address_requested;
        r105.test = r511.test;
        r105.title = r511.title;
        r105.description = r511.description;
        if (TLObject.hasFlag(r511.flags, 1) == false) goto L30;
        r105.flags |= 128;
        r105.webPhoto = r511.photo;
    L30:
        r105.currency = r511.currency;
        r105.total_amount = r511.total_amount;
        r0.flags |= 512;
        r0.media = r105;
        goto L44
    L32:
        if ((r52 instanceof TLRPC.TL_botInlineMessageMediaWebPage) == false) goto L42;
        TLRPC.TL_botInlineMessageMediaWebPage r512 = (TLRPC.TL_botInlineMessageMediaWebPage) r52;
        TLRPC.TL_messageMediaWebPage r1 = new TLRPC.TL_messageMediaWebPage();
        r1.force_large_media = r512.force_large_media;
        r1.force_small_media = r512.force_small_media;
        r1.manual = r512.manual;
        r1.safe = r512.safe;
        r0.invert_media = r512.invert_media;
        r0.message = r512.message;
        if (r10 == null) goto L36;
        r1.webpage = r10;
    L40:
        r0.flags |= 512;
        r0.media = r1;
        goto L44
    L36:
        TLRPC.TL_webPage r106 = new TLRPC.TL_webPage();
        if (TLObject.hasFlag(r512.flags, 2) == false) goto L39;
        r0.flags |= 128;
        r0.entities = r512.entities;
    L39:
        String r513 = r512.url;
        r106.display_url = r513;
        r106.url = r513;
        r1.webpage = r106;
        goto L40
    L42:
        if ((r52 instanceof TLRPC.TL_botInlineMessageRichMessage) == false) goto L44;
        r0.flags2 |= 8192;
        r0.rich_message = ((TLRPC.TL_botInlineMessageRichMessage) r52).rich_message;
        goto L44
    }

    private /* synthetic */ void lambda$new$10(TLRPC.TL_messages_preparedInlineMessage r15, Utilities.Callback2 r16, int r17, long r18, Runnable r20, View r21) {
        BaseFragment r6 = LaunchActivity.getSafeLastFragment();
        if (r6 == null) goto L46;
        this.openedDialogsActivity = true;
        Bundle r1 = new Bundle();
        r1.putBoolean("onlySelect", true);
        r1.putBoolean("canSelectTopics", true);
        r1.putInt("dialogsType", 1);
        if (r15.peer_types.isEmpty() == true) goto L24;
        int r4 = 0;
        r1.putBoolean("allowGroups", false);
        r1.putBoolean("allowMegagroups", false);
        r1.putBoolean("allowLegacyGroups", false);
        r1.putBoolean("allowUsers", false);
        r1.putBoolean("allowChannels", false);
        r1.putBoolean("allowBots", false);
        ArrayList<TLRPC.InlineQueryPeerType> r10 = r15.peer_types;
        int r11 = r10.size();
    L8:
        if (r4 >= r11) goto L24;
        TLRPC.InlineQueryPeerType r12 = r10.get(r4);
        r4 = r4 + 1;
        TLRPC.InlineQueryPeerType r122 = r12;
        if ((r122 instanceof TLRPC.TL_inlineQueryPeerTypePM) == true) goto L11;
        if ((r122 instanceof TLRPC.TL_inlineQueryPeerTypeBotPM) == true) goto L14;
        if ((r122 instanceof TLRPC.TL_inlineQueryPeerTypeBroadcast) == true) goto L17;
        if ((r122 instanceof TLRPC.TL_inlineQueryPeerTypeChat) == true) goto L20;
        if ((r122 instanceof TLRPC.TL_inlineQueryPeerTypeMegagroup) == false) goto L8;
        r1.putBoolean("allowMegagroups", true);
        goto L8
    L20:
        r1.putBoolean("allowLegacyGroups", true);
        goto L8
    L17:
        r1.putBoolean("allowChannels", true);
        goto L8
    L14:
        r1.putBoolean("allowBots", true);
        goto L8
    L11:
        r1.putBoolean("allowUsers", true);
    L24:
        AnonymousClass6 r8 = new AnonymousClass6(this, r1, r16);
        r8.setDelegate(new BotShareSheet$$ExternalSyntheticLambda11(this, r17, r15, r18, r6, r16));
        r6.presentFragment(r8);
        dismiss();
        if (r20 == null) goto L45;
        r20.run();
        return;
    L45:
        return;
    }

    private /* synthetic */ boolean lambda$new$9(int r30, TLRPC.TL_messages_preparedInlineMessage r31, long r32, BaseFragment r34, Utilities.Callback2 r35, DialogsActivity r36, ArrayList r37, CharSequence r38, boolean r39, boolean r40, int r41, int r42, TopicsFragment r43) {
        ArrayList r3 = new ArrayList();
        int r4 = r37.size();
        boolean r5 = false;
        int r6 = 0;
    L3:
        if (r6 >= r4) goto L20;
        Object r10 = r37.get(r6);
        r6 = r6 + 1;
        MessagesStorage.TopicKey r102 = (MessagesStorage.TopicKey) r10;
        long r12 = r102.dialogId;
        long r103 = r102.topicId;
        if (DialogObject.isEncryptedDialog(r12) == true) goto L3;
        if (r103 == 0) goto L14;
        TLRPC.TL_forumTopic r7 = MessagesController.getInstance(r30).getTopicsController().findTopic(-r12, r103);
        if (r7 == null) goto L14;
        if (r7.topicStartMessage == null) goto L14;
        int r104 = r30;
        MessageObject r8 = new MessageObject(r104, r7.topicStartMessage, r5, r5);
        r8.isTopicMainMessage = true;
        MessageObject r17 = r8;
    L15:
        HashMap r14 = new HashMap();
        r14.put("query_id", _UrlKt.FRAGMENT_ENCODE_SET + r31.query_id);
        r14.put("id", _UrlKt.FRAGMENT_ENCODE_SET + r31.result.id);
        r14.put("bot", _UrlKt.FRAGMENT_ENCODE_SET + r32);
        long r15 = r12;
        SendMessagesHelper.prepareSendingBotContextResult(r34, AccountInstance.getInstance(r104), r31.result, r14, r15, r17, r17, null, null, r40, r41, 0, null, 0, 0);
        if (r38 == null) goto L18;
        SendMessagesHelper r52 = SendMessagesHelper.getInstance(r104);
        MessageObject r142 = r17;
        SendMessagesHelper.SendMessageParams r82 = SendMessagesHelper.SendMessageParams.of(r38.toString(), r15, r142, r142, null, true, null, null, null, true, 0, 0, null, false);
        r15 = r15;
        r52.sendMessage(r82);
    L18:
        r3.add(Long.valueOf(r15));
        r6 = r6;
        r5 = false;
    L14:
        r104 = r30;
        r17 = null;
        goto L15
    L20:
        if (this.sent == true) goto L28;
        this.sent = true;
        if (r35 == null) goto L28;
        if (r3.size() <= 0) goto L26;
        String r72 = null;
    L27:
        r35.run(r72, r3);
        goto L28
    L26:
        r72 = "USER_DECLINED";
    L28:
        if (r43 == null) goto L31;
        r43.finishFragment();
        r36.removeSelfFromStack();
        return true;
    L31:
        r36.finishFragment();
        return true;
    }

    public static Runnable loadWebPagePreview(int r5, String r6, Utilities.Callback<TLRPC.WebPage> r7) {
        int[] r1 = new int[1];
        NotificationCenter.NotificationCenterDelegate[] r0 = new NotificationCenter.NotificationCenterDelegate[1];
        TL_account.getWebPagePreview r2 = new TL_account.getWebPagePreview();
        r2.message = r6;
        r1[0] = ConnectionsManager.getInstance(r5).sendRequestTyped(r2, new AiTonesController$$ExternalSyntheticLambda0(), new BotShareSheet$$ExternalSyntheticLambda7(r1, r7, r0, r5));
        return new BotShareSheet$$ExternalSyntheticLambda8(r1, r5, r0);
    }

    public static void share(Context r10, int r11, long r12, String r14, Theme.ResourcesProvider r15, Runnable r16, Utilities.Callback2<String, ArrayList<Long>> r17) {
        AlertDialog r2 = new AlertDialog(r10, 3);
        r2.showDelayed(500);
        TLRPC.TL_messages_getPreparedInlineMessage r9 = new TLRPC.TL_messages_getPreparedInlineMessage();
        r9.bot = MessagesController.getInstance(r11).getInputUser(r12);
        r9.id = r14;
        ConnectionsManager.getInstance(r11).sendRequest(r9, new BotShareSheet$$ExternalSyntheticLambda0(r11, r2, r10, r12, r15, r16, r17));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new BotShareSheet$$ExternalSyntheticLambda9(this), this.resourcesProvider);
        this.adapter = r0;
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        if (this.openedDialogsActivity == false) goto L5;
        return;
    L5:
        if (this.sent == true) goto L11;
        this.sent = true;
        Utilities.Callback2<String, ArrayList<Long>> r2 = this.whenDone;
        if (r2 == null) goto L12;
        r2.run("USER_DECLINED", null);
        return;
    L12:
        return;
    }

    public void fillItems(ArrayList<UItem> r2, UniversalAdapter r3) {
        r2.add(UItem.asCustom(-1, this.chatView));
        r2.add(UItem.asShadow(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotShareMessageInfo, new Object[]{this.botName}))));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.BotShareMessage);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onCreate(Bundle r2) {
        super.onCreate(r2);
        if (this.recyclerListView.getAdapter() != null) goto L5;
        int r22 = 0;
    L6:
        this.recyclerListView.scrollToPosition(Math.max(r22 - 1, 0));
        return;
    L5:
        r22 = this.recyclerListView.getAdapter().getItemCount();
        goto L6
    }
}
