package org.telegram.ui.iv;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessageChatArguments;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AIEditorAlert;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ChatAttachAlertDocumentLayout;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.MessageSendPreview;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.iv.RichEditor;
import org.telegram.ui.iv.RichEditorListView;
import org.telegram.ui.iv.RichEditorToolbar;

/* loaded from: classes7.dex */
public class ChatAttachAlertRichLayout extends ChatAttachAlert.AttachAlertLayout implements NotificationCenter.NotificationCenterDelegate {
    private static final int[] STYLE_FLAGS = null;
    private boolean attachButtonsShown;
    private int attachRaise;
    private RichCommandSuggestions commandSuggestions;
    private final int currentAccount;
    private int currentItemTop;
    private int emojiPadding;
    private boolean emojiSearchOpened;
    private RichEditText emojiTargetEditText;
    private int emojiTargetSelection;
    private EmojiView emojiView;
    private boolean emojiViewVisible;
    private boolean ignoreLayout;
    private boolean keyboardVisible;
    private int lastAttachRise;
    private final Runnable limitCheckRunnable;
    private final RichEditorListView listView;
    private ItemOptions menu;
    private MessageSendPreview messageSendPreview;
    private boolean sendButtonShown;
    private RichEditorToolbar toolbar;
    private final RichEditorToolbar.Delegate toolbarDelegate;

    /* renamed from: org.telegram.ui.iv.ChatAttachAlertRichLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements RichEditorListView.Delegate {
        final /* synthetic */ ChatAttachAlertRichLayout this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        /* renamed from: $r8$lambda$1xYkb-PAGsPtNY3omagHk4veYNM, reason: not valid java name */
        public static /* synthetic */ ItemOptions m21619$r8$lambda$1xYkbPAGsPtNY3omagHk4veYNM(AnonymousClass1 r0, Theme.ResourcesProvider r1, View r2) {
            return r0.lambda$onSlashSuggest$0(r1, r2);
        }

        public AnonymousClass1(ChatAttachAlertRichLayout r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            this.val$resourcesProvider = r2;
        }

        private /* synthetic */ ItemOptions lambda$onSlashSuggest$0(Theme.ResourcesProvider r7, View r8) {
            ChatAttachAlertRichLayout r0 = this.this$0;
            ItemOptions r6 = ItemOptions.makeOptions(r0, r7, r8, false, false, true);
            ChatAttachAlertRichLayout.m21600$$Nest$fputmenu(r0, r6);
            return r6;
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void makeEditTextFocusable(RichEditText r1, boolean r2) {
            ChatAttachAlertRichLayout.access$200(this.this$0).makeFocusable(r1, r2);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public ItemOptions makeMenu(View r7) {
            ChatAttachAlertRichLayout r0 = this.this$0;
            ItemOptions r6 = ItemOptions.makeOptions(r0, this.val$resourcesProvider, r7, false, false, true);
            ChatAttachAlertRichLayout.m21600$$Nest$fputmenu(r0, r6);
            return r6;
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onBlockButtonEditRequested(RichEditorListView.BlockButtonEdit r7, View r8) {
            ItemOptions r0 = ItemOptions.makeOptions(this.this$0, this.val$resourcesProvider, r8, false, false, true).dontFocus();
            ChatAttachAlertRichLayout r82 = this.this$0;
            ChatAttachAlertRichLayout.m21600$$Nest$fputmenu(r82, RichInlineButtonEditor.showBlock(r0, ChatAttachAlertRichLayout.access$400(r82).getBaseFragment(), this.this$0.getContext(), this.val$resourcesProvider, r7, true));
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onContentChanged() {
            ChatAttachAlertRichLayout.m21615$$Nest$mupdateSendButtonLoading(this.this$0);
            ChatAttachAlertRichLayout.m21616$$Nest$mupdateSendButtonLocked(this.this$0);
            ChatAttachAlertRichLayout.m21608$$Nest$mscheduleLimitCheck(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onHistoryChanged() {
            ChatAttachAlertRichLayout.m21614$$Nest$mupdateHistoryButtons(this.this$0);
            ChatAttachAlertRichLayout.m21616$$Nest$mupdateSendButtonLocked(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onInlineButtonEditRequested(RichEditorListView.InlineButtonEdit r7, View r8) {
            ItemOptions r0 = ItemOptions.makeOptions(this.this$0, this.val$resourcesProvider, r8, false, false, true).dontFocus();
            ChatAttachAlertRichLayout r82 = this.this$0;
            ChatAttachAlertRichLayout.m21600$$Nest$fputmenu(r82, RichInlineButtonEditor.show(r0, ChatAttachAlertRichLayout.access$300(r82).getBaseFragment(), this.this$0.getContext(), this.val$resourcesProvider, r7, true));
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onListLayoutUpdated() {
            int r0 = ChatAttachAlertRichLayout.m21590$$Nest$fgetcurrentItemTop(this.this$0);
            if (this.this$0.getCurrentItemTop() == r0) goto L5;
            ChatAttachAlertRichLayout.access$100(this.this$0).updateLayout(this.this$0, true, 0);
        L5:
            ChatAttachAlertRichLayout.m21618$$Nest$mupdateToolbarTopOffset(this.this$0);
            ChatAttachAlertRichLayout.m21611$$Nest$mupdateAttachRaise(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onListScrolled(int r4) {
            ChatAttachAlertRichLayout.access$000(this.this$0).updateLayout(this.this$0, true, r4);
            ChatAttachAlertRichLayout.m21618$$Nest$mupdateToolbarTopOffset(this.this$0);
            ChatAttachAlertRichLayout.m21611$$Nest$mupdateAttachRaise(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onOpenAttachRequest(int r1, int r2) {
            ChatAttachAlertRichLayout.m21604$$Nest$mopenAttach(this.this$0, r1, r2);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onOpenLocationRequest(BlockRow r1) {
            ChatAttachAlertRichLayout.m21605$$Nest$mopenLocationPicker(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onReorderEnd() {
            if (ChatAttachAlertRichLayout.m21595$$Nest$fgettoolbar(this.this$0) == null) goto L6;
            ChatAttachAlertRichLayout.m21595$$Nest$fgettoolbar(this.this$0).onReorderEnd();
            return;
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public boolean onReorderMove(float r2, float r3) {
            if (ChatAttachAlertRichLayout.m21595$$Nest$fgettoolbar(this.this$0) != null) goto L5;
            return false;
        L5:
            if (ChatAttachAlertRichLayout.m21595$$Nest$fgettoolbar(this.this$0).onReorderMove(r2, r3) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onReorderStart() {
            if (ChatAttachAlertRichLayout.m21595$$Nest$fgettoolbar(this.this$0) == null) goto L6;
            ChatAttachAlertRichLayout.m21595$$Nest$fgettoolbar(this.this$0).onReorderStart();
            return;
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onSelectionChanged() {
            ChatAttachAlertRichLayout.m21613$$Nest$mupdateFormattingPanel(this.this$0);
            ChatAttachAlertRichLayout.m21617$$Nest$mupdateToolbarBlockType(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onSlashSuggest(RichTextCell r5, String r6) {
            if (ChatAttachAlertRichLayout.m21588$$Nest$fgetcommandSuggestions(this.this$0) != null) goto L5;
            ChatAttachAlertRichLayout.m21596$$Nest$fputcommandSuggestions(this.this$0, new RichCommandSuggestions(new ChatAttachAlertRichLayout$1$$ExternalSyntheticLambda0(this, this.val$resourcesProvider), this.val$resourcesProvider));
        L5:
            ChatAttachAlertRichLayout.m21588$$Nest$fgetcommandSuggestions(this.this$0).update(r5, r6);
        }
    }

    /* renamed from: org.telegram.ui.iv.ChatAttachAlertRichLayout$2, reason: invalid class name */
    public class AnonymousClass2 implements RichEditorToolbar.Delegate {
        final /* synthetic */ ChatAttachAlertRichLayout this$0;

        /* renamed from: $r8$lambda$5173totAq3xKai6IE-2bm1nmgTY, reason: not valid java name */
        public static /* synthetic */ void m21620$r8$lambda$5173totAq3xKai6IE2bm1nmgTY(AnonymousClass2 r0, TL_iv.RichMessage r1) {
            r0.lambda$onAi$0(r1);
        }

        public AnonymousClass2(ChatAttachAlertRichLayout r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAi$0(TL_iv.RichMessage r1) {
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).addRichMessage(r1);
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public Theme.ResourcesProvider getResourcesProvider() {
            return ChatAttachAlertRichLayout.access$500(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onAi() {
            new RichAIComposeSheet(this.this$0.getContext(), ChatAttachAlertRichLayout.m21589$$Nest$fgetcurrentAccount(this.this$0), ChatAttachAlertRichLayout.access$600(this.this$0), new ChatAttachAlertRichLayout$2$$ExternalSyntheticLambda1(this)).show();
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onAiStyle() {
            RichEditorListView.SelectionEdit r0 = ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).beginSelectionEdit();
            if (r0 == null) goto L13;
            TL_iv.RichMessage r1 = r0.extractRichMessage();
            if (r1 != null) goto L8;
            return;
        L8:
            if (r1.blocks.isEmpty() == true) goto L14;
            new AIEditorAlert(this.this$0.getContext(), ChatAttachAlertRichLayout.access$700(this.this$0)).setText(r1).setOnUseRich(new ChatAttachAlertRichLayout$2$$ExternalSyntheticLambda0(r0)).show();
            return;
        L14:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onAttach() {
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).pendingMediaRow = null;
            ChatAttachAlertRichLayout.m21604$$Nest$mopenAttach(this.this$0, 90, 0);
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onBack() {
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onBlockButton(int r1, View r2) {
            ChatAttachAlertRichLayout.m21603$$Nest$monBlockButtonClicked(this.this$0, r1, r2);
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onButton(View r1) {
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).onInlineButtonClicked(r1);
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onDate() {
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).onDateClicked();
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onEmoji() {
            ChatAttachAlertRichLayout.m21610$$Nest$mtoggleEmojiPopup(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onFormatting(int r1) {
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).onFormattingClicked(r1);
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onLink() {
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).onLinkClicked();
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onMath() {
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).onMathClicked();
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onQuote() {
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).toggleQuoteOnSelection();
            ChatAttachAlertRichLayout.m21612$$Nest$mupdateFormattingButtons(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onRedo() {
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).redo();
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onSend() {
            this.this$0.sendSelectedItems(true, 0, 0, 0, false);
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public boolean onSendLongClick(View r1) {
            return ChatAttachAlertRichLayout.m21609$$Nest$mshowSendPreview(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichEditorToolbar.Delegate
        public void onUndo() {
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).undo();
        }
    }

    /* renamed from: org.telegram.ui.iv.ChatAttachAlertRichLayout$3, reason: invalid class name */
    public class AnonymousClass3 implements AlertsCreator.ScheduleDatePickerDelegate {
        final /* synthetic */ ChatAttachAlertRichLayout this$0;

        public AnonymousClass3(ChatAttachAlertRichLayout r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
        public void didSelectDate(boolean r8, int r9, int r10) {
            this.this$0.sendSelectedItems(r8, r9, r10, 0, false);
            if (ChatAttachAlertRichLayout.m21594$$Nest$fgetmessageSendPreview(this.this$0) == null) goto L6;
            ChatAttachAlertRichLayout.m21594$$Nest$fgetmessageSendPreview(this.this$0).dismissInstant();
            ChatAttachAlertRichLayout.m21601$$Nest$fputmessageSendPreview(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.iv.ChatAttachAlertRichLayout$4, reason: invalid class name */
    public class AnonymousClass4 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ ChatAttachAlertRichLayout this$0;

        public AnonymousClass4(ChatAttachAlertRichLayout r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
        }
    }

    /* renamed from: org.telegram.ui.iv.ChatAttachAlertRichLayout$5, reason: invalid class name */
    public class AnonymousClass5 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ ChatAttachAlertRichLayout this$0;
        final /* synthetic */ ChatAttachAlert val$alert;

        public AnonymousClass5(ChatAttachAlertRichLayout r1, ChatAttachAlert r2) {
            this.this$0 = r1;
            this.val$alert = r2;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
            if (r1 != 7) goto L5;
        L6:
            HashMap<Object, Object> r12 = this.val$alert.getPhotoLayout().getSelectedPhotos();
            ArrayList<Object> r22 = this.val$alert.getPhotoLayout().getSelectedPhotosOrder();
            BlockRow r42 = ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).pendingMediaRow;
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).pendingMediaRow = null;
            int r52 = 0;
        L8:
            if (r52 >= r22.size()) goto L16;
            Object r62 = r12.get(r22.get(r52));
            if ((r62 instanceof MediaController.PhotoEntry) == true) goto L11;
            r52 = r52 + 1;
            goto L8
        L11:
            ChatAttachAlertRichLayout r13 = this.this$0;
            if (r42 == null) goto L14;
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(r13).addMediaToRow(r42, (MediaController.PhotoEntry) r62);
            goto L16
        L14:
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(r13).attachMedia((MediaController.PhotoEntry) r62);
        L16:
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).pendingMediaRow = null;
            this.val$alert.dismiss(true);
            return;
        L5:
            if (r1 != 8) goto L16;
            goto L6
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didSelectBot(TLRPC.User r1) {
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void doOnIdle(Runnable r1) {
            NotificationCenter.getInstance(ChatAttachAlertRichLayout.m21589$$Nest$fgetcurrentAccount(this.this$0)).doOnIdle(r1);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public boolean needEnterComment() {
            return false;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void onCameraOpened() {
        }
    }

    /* renamed from: org.telegram.ui.iv.ChatAttachAlertRichLayout$6, reason: invalid class name */
    public class AnonymousClass6 implements ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate {
        final /* synthetic */ ChatAttachAlertRichLayout this$0;
        final /* synthetic */ ChatAttachAlert val$alert;

        public AnonymousClass6(ChatAttachAlertRichLayout r1, ChatAttachAlert r2) {
            this.this$0 = r1;
            this.val$alert = r2;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void didSelectFiles(ArrayList<String> r1, String r2, ArrayList<TLRPC.MessageEntity> r3, ArrayList<MessageObject> r4, boolean r5, int r6, int r7, long r8, boolean r10, long r11) {
            if (r1 != null) goto L5;
        L7:
            if (r4 != null) goto L9;
        L11:
            this.val$alert.dismiss(true);
            return;
        L9:
            if (r4.isEmpty() == true) goto L11;
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).attachDocument(r4.get(0));
            goto L11
        L5:
            if (r1.isEmpty() == true) goto L7;
            ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).attachDocument(r1.get(0));
            goto L11
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void startDocumentSelectActivity() {
            Intent r0 = new Intent("android.intent.action.GET_CONTENT");     // Catch: Exception -> L4
            r0.setType("*/*");     // Catch: Exception -> L4
            ChatAttachAlertRichLayout.access$800(this.this$0).baseFragment.startActivityForResult(r0, 21);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }
    }

    /* renamed from: org.telegram.ui.iv.ChatAttachAlertRichLayout$7, reason: invalid class name */
    public class AnonymousClass7 implements EmojiView.EmojiViewDelegate {
        final /* synthetic */ ChatAttachAlertRichLayout this$0;
        final /* synthetic */ ChatActivity val$chatActivity;

        public AnonymousClass7(ChatAttachAlertRichLayout r1, ChatActivity r2) {
            this.this$0 = r1;
            this.val$chatActivity = r2;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean isSearchOpened() {
            return ChatAttachAlertRichLayout.m21591$$Nest$fgetemojiSearchOpened(this.this$0);
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean isUserSelf() {
            ChatActivity r0 = this.val$chatActivity;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (UserObject.isUserSelf(r0.getCurrentUser()) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean onBackspace() {
            RichEditText r3 = ChatAttachAlertRichLayout.m21606$$Nest$mresolveEmojiTarget(this.this$0);
            if (r3 != null) goto L5;
        L9:
            return false;
        L5:
            if (r3.length() == 0) goto L9;
            r3.dispatchKeyEvent(new KeyEvent(0, 67));
            return true;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onCustomEmojiSelected(long r3, TLRPC.Document r5, String r6, boolean r7) {
            RichEditText r72 = ChatAttachAlertRichLayout.m21606$$Nest$mresolveEmojiTarget(this.this$0);
            if (r72 == null) goto L21;
            int r0 = ChatAttachAlertRichLayout.m21607$$Nest$mresolveEmojiTargetOffset(this.this$0, r72);
            if (r6 != null) goto L9;
            r6 = "😀";
        L9:
            SpannableString r1 = new SpannableString(r6);     // Catch: Exception -> L17
            if (r5 == null) goto L12;
            AnimatedEmojiSpan r32 = new AnimatedEmojiSpan(r5, r72.getPaint().getFontMetricsInt());     // Catch: Exception -> L17
        L13:
            r32.cacheType = AnimatedEmojiDrawable.getCacheTypeForEnterView();     // Catch: Exception -> L17
            r1.setSpan(r32, 0, r1.length(), 33);     // Catch: Exception -> L17
            r72.setText(r72.getText().insert(r0, r1));     // Catch: Exception -> L17
            int r02 = r0 + r1.length();     // Catch: Exception -> L17
            r72.setSelection(r02, r02);     // Catch: Exception -> L17
            if (r72 != ChatAttachAlertRichLayout.m21592$$Nest$fgetemojiTargetEditText(this.this$0)) goto L20;
            ChatAttachAlertRichLayout.m21599$$Nest$fputemojiTargetSelection(this.this$0, r02);     // Catch: Exception -> L17
            return;
        L20:
            return;
        L12:
            r32 = new AnimatedEmojiSpan(r3, r72.getPaint().getFontMetricsInt());     // Catch: Exception -> L17
        L22:
            return;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onEmojiSelected(String r6) {
            RichEditText r0 = ChatAttachAlertRichLayout.m21606$$Nest$mresolveEmojiTarget(this.this$0);
            if (r0 == null) goto L14;
            int r1 = ChatAttachAlertRichLayout.m21607$$Nest$mresolveEmojiTargetOffset(this.this$0, r0);
            CharSequence r62 = Emoji.replaceEmoji(r6, r0.getPaint().getFontMetricsInt(), false, null);     // Catch: Exception -> L10
            r0.setText(r0.getText().insert(r1, r62));     // Catch: Exception -> L10
            int r12 = r1 + r62.length();     // Catch: Exception -> L10
            r0.setSelection(r12, r12);     // Catch: Exception -> L10
            if (r0 != ChatAttachAlertRichLayout.m21592$$Nest$fgetemojiTargetEditText(this.this$0)) goto L13;
            ChatAttachAlertRichLayout.m21599$$Nest$fputemojiTargetSelection(this.this$0, r12);     // Catch: Exception -> L10
            return;
        L13:
            return;
        L15:
            return;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onSearchOpenClose(int r4) {
            boolean r0 = false;
            if (r4 == 0) goto L7;
            RichEditText r1 = ChatAttachAlertRichLayout.m21593$$Nest$fgetlistView(this.this$0).getFocusedEditTextOrNull();
            if (r1 == null) goto L7;
            ChatAttachAlertRichLayout.m21598$$Nest$fputemojiTargetEditText(this.this$0, r1);
            ChatAttachAlertRichLayout.m21599$$Nest$fputemojiTargetSelection(this.this$0, Math.max(0, r1.getSelectionEnd()));
        L7:
            ChatAttachAlertRichLayout r12 = this.this$0;
            if (r4 == 0) goto L10;
            r0 = true;
        L10:
            ChatAttachAlertRichLayout.m21597$$Nest$fputemojiSearchOpened(r12, r0);
            ChatAttachAlertRichLayout.m21602$$Nest$mlayoutBottomPanels(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.iv.ChatAttachAlertRichLayout$8, reason: invalid class name */
    public class AnonymousClass8 implements TextWatcher {
        final /* synthetic */ String[] val$source;
        final /* synthetic */ Runnable val$update;

        public AnonymousClass8(String[] r1, Runnable r2) {
            this.val$source = r1;
            this.val$update = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            this.val$source[0] = r3.toString();
            this.val$update.run();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: $r8$lambda$-qnvbV5Z12dzQK_G2C0uxHOnznA, reason: not valid java name */
    public static /* synthetic */ void m21573$r8$lambda$qnvbV5Z12dzQK_G2C0uxHOnznA(ChatAttachAlertRichLayout r0, View r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$0Rmhz-gntvJfn9jBLwSMtiRM2ek, reason: not valid java name */
    public static /* synthetic */ void m21574$r8$lambda$0RmhzgntvJfn9jBLwSMtiRM2ek(ChatAttachAlertRichLayout r0, View r1) {
        r0.lambda$showSendPreview$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$0hZCXUk_2gbly429s7dEPso9PMw(ChatAttachAlertRichLayout r0, BlockRow r1) {
        r0.lambda$showListMenu$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$4gONQVT4vVUasFJVAXvl7h8WqNI(ChatAttachAlertRichLayout r0, ItemOptions r1) {
        r0.lambda$showListMenu$17(r1);
    }

    /* renamed from: $r8$lambda$8yqdzTOvT6x370kKwIJECLf-PkY, reason: not valid java name */
    public static /* synthetic */ void m21575$r8$lambda$8yqdzTOvT6x370kKwIJECLfPkY(ChatAttachAlertRichLayout r0, BlockRow r1) {
        r0.lambda$showTextTypeMenu$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$9gR4576AaZaf5j1jbYm_0Pdj1c4(ChatAttachAlertRichLayout r0) {
        r0.lambda$showSendPreview$21();
    }

    public static /* synthetic */ void $r8$lambda$AvFJmRvvzvlP_GENQoZFekP6jjo(ChatAttachAlertRichLayout r0, ItemOptions r1) {
        r0.lambda$showListMenu$16(r1);
    }

    /* renamed from: $r8$lambda$FtluPg-1ucSB06pA3rH6D5oX7Hg, reason: not valid java name */
    public static /* synthetic */ void m21576$r8$lambda$FtluPg1ucSB06pA3rH6D5oX7Hg(ChatAttachAlertRichLayout r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$H10e6D80ZehqkxfTZNMNSRYgyHA(ChatAttachAlertRichLayout r0, BlockRow r1) {
        r0.lambda$showTextTypeMenu$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$LBTrb4C3SXJw4q0L7_RlGiErwIs(ChatAttachAlertRichLayout r0, long r1) {
        r0.lambda$showSendPreview$20(r1);
    }

    public static /* synthetic */ void $r8$lambda$MhiuP733VDsIFoRXPOnTaMlg28E(ChatAttachAlertRichLayout r0, BlockRow r1) {
        r0.lambda$showListMenu$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$NZaUwlU_C7CDlDLXzUALLp2APLg(ChatAttachAlertRichLayout r0, BlockRow r1, TL_iv.PageBlock r2, ItemOptions r3) {
        r0.lambda$addHeadingItem$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$SnlnIYKhSoVOQmVQhTusmpYMyaA(ChatAttachAlertRichLayout r0, BlockRow r1) {
        r0.lambda$openLocationPicker$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$SwwBKhb2X29BhweXRvIIIiNW3jo(String[] r1, String r2, Utilities.Callback2 r3) {
        Utilities.themeQueue.postRunnable(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda11(r1, r3));
    }

    public static /* synthetic */ void $r8$lambda$T_iX90XDQb6L6BZzm8gNZjs1QW0(EditTextCell r0, boolean[] r1, boolean[] r2, String r3, String[] r4, Utilities.Callback r5, DialogInterface r6) {
        r0.editText.clearFocus();
        AndroidUtilities.hideKeyboard(r0.editText);
        if (r1[0] == false) goto L5;
        return;
    L5:
        if (r2[0] == false) goto L7;
        return;
    L7:
        if (TextUtils.equals(r3, r4[0]) == true) goto L12;
        r1[0] = true;
        r5.run(r4[0]);
        return;
    }

    public static /* synthetic */ void $r8$lambda$Vqj5jiHbz40PIawjVspmypVBH1k(ChatAttachAlertRichLayout r0, BlockRow r1, ChatAttachAlert r2, TLRPC.MessageMedia r3, int r4, boolean r5, int r6, long r7) {
        r0.lambda$openLocationPicker$24(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$WaphGL3fMmL-TBn2DXtffZzAiww, reason: not valid java name */
    public static /* synthetic */ void m21577$r8$lambda$WaphGL3fMmLTBn2DXtffZzAiww(ChatAttachAlertRichLayout r0) {
        r0.updateSendButtonEnabled();
    }

    /* renamed from: $r8$lambda$XmCv70BUTvSf0IPH-KEfsKbsWTE, reason: not valid java name */
    public static /* synthetic */ void m21578$r8$lambda$XmCv70BUTvSf0IPHKEfsKbsWTE(EditTextCell r1) {
        r1.editText.requestFocus();
        AndroidUtilities.showKeyboard(r1.editText);
    }

    /* renamed from: $r8$lambda$XziTD2FX8CwVp-notqQKwpiHlhs, reason: not valid java name */
    public static /* synthetic */ void m21579$r8$lambda$XziTD2FX8CwVpnotqQKwpiHlhs(ChatAttachAlertRichLayout r0, BlockRow r1) {
        r0.lambda$showListMenu$14(r1);
    }

    /* renamed from: $r8$lambda$Zjled5bofGN-3SKK241Q7f6xf4U, reason: not valid java name */
    public static /* synthetic */ void m21580$r8$lambda$Zjled5bofGN3SKK241Q7f6xf4U(ButtonWithCounterView r0, boolean[] r1, Utilities.Callback r2, String[] r3, BottomSheet r4, View r5) {
        if (r0.isEnabled() == true) goto L6;
        return;
    L6:
        if (r1[0] == true) goto L8;
        r1[0] = true;
        r2.run(r3[0]);
    L8:
        r4.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$_w4zLTN74NScBlbNrdSdUEOSZCE(String[] r11, HorizontalScrollView r12, ButtonWithCounterView r13, boolean[] r14, Utilities.Callback2 r15, ImageView r16, Theme.ResourcesProvider r17, int[] r18) {
        if (TextUtils.isEmpty(r11[0].trim()) == false) goto L6;
        r12.setVisibility(8);
        r13.setEnabled(false);
        return;
    L6:
        boolean r6 = r14[0];
        String r2 = r11[0];
        r15.run(r2, new ChatAttachAlertRichLayout$$ExternalSyntheticLambda8(r2, r11, r16, r17, r6, r18, r13, r12, r14));
    }

    /* renamed from: $r8$lambda$aoYYG_S1-gxClDLy7HzggKZVtOw, reason: not valid java name */
    public static /* synthetic */ void m21581$r8$lambda$aoYYG_S1gxClDLy7HzggKZVtOw(ChatAttachAlertRichLayout r0, BlockRow r1) {
        r0.lambda$showTextTypeMenu$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$bWNjMp4xTtg_2VcbQ26UbgvVXBQ(ItemOptions r0, ItemOptions r1) {
        r0.openSwipeback(r1);
    }

    public static /* synthetic */ void $r8$lambda$ba2ECVT3zR2m_yZOzjVJIAf50JQ(ChatAttachAlertRichLayout r0, BlockRow r1) {
        r0.lambda$showTextTypeMenu$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$c9Yck8wJQIOR6LllU0AZ9rP6_d0(Utilities.Callback2 r0, Bitmap r1, boolean r2) {
        r0.run(r1, Boolean.valueOf(r2));
    }

    public static /* synthetic */ void $r8$lambda$d3964kCMQUhecv6rxd2aG5K4fwM(ChatAttachAlertRichLayout r0) {
        r0.updateToolbarTopOffset();
    }

    /* renamed from: $r8$lambda$f_Ud1jYnipAwPtcSIQxrsjKp8-M, reason: not valid java name */
    public static /* synthetic */ void m21582$r8$lambda$f_Ud1jYnipAwPtcSIQxrsjKp8M(ChatAttachAlertRichLayout r0, DialogInterface r1) {
        r0.lambda$showSendPreview$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$kPAmXiVcrSNtjVaVKtMxol4y1bw(String[] r3, Utilities.Callback2 r4) {
        boolean r0 = false;
        Latex r32 = Latex.render(r3[0], AndroidUtilities.dp(26.0f), false);
        if (r32 != null) goto L5;
        r32 = Latex.render(LocaleController.getString(R.string.ArticleLatexError), AndroidUtilities.dp(26.0f), false);
        r0 = true;
    L5:
        if (r32 == null) goto L7;
        Bitmap r33 = r32.bitmap;
    L8:
        AndroidUtilities.runOnUIThread(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda15(r4, r33, r0));
        return;
    L7:
        r33 = null;
        goto L8
    }

    /* renamed from: $r8$lambda$nh8712BfMLl6-OagiKsS4sm7sNY, reason: not valid java name */
    public static /* synthetic */ void m21583$r8$lambda$nh8712BfMLl6OagiKsS4sm7sNY(ChatAttachAlertRichLayout r0, ChatAttachAlert r1, ArrayList r2, CharSequence r3, boolean r4, int r5, int r6, long r7, boolean r9, long r10) {
        r0.lambda$openAttach$26(r1, r2, r3, r4, r5, r6, r7, r9, r10);
    }

    /* renamed from: $r8$lambda$pOxX8xTvRuVpp2SVyUksVxz-8dA, reason: not valid java name */
    public static /* synthetic */ void m21584$r8$lambda$pOxX8xTvRuVpp2SVyUksVxz8dA(ChatAttachAlertRichLayout r0, BlockRow r1) {
        r0.lambda$showTextTypeMenu$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$pTPnhfFjt3pfL7D5HHHacDtiFq4(ChatAttachAlertRichLayout r0, ChatAttachAlert r1, TLRPC.MessageMedia r2, int r3, boolean r4, int r5, long r6) {
        r0.lambda$openAttach$25(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$rlP4Sfc5ogW0RROBuxQtg01C7NU(ItemOptions r0) {
        r0.closeSwipeback();
    }

    public static /* synthetic */ void $r8$lambda$sDdJhmo0Fi7vLycaLVFdcE2GZhY(ChatAttachAlertRichLayout r0) {
        r0.lambda$showConversionSheet$27();
    }

    public static /* synthetic */ void $r8$lambda$sfWz0kYK5ivz_zUEmZydSC1BJw0(ChatAttachAlertRichLayout r0, BlockRow r1) {
        r0.lambda$showListMenu$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$wDQ1FMnnU7BRtlrOGGRepNzTc1s(ChatAttachAlertRichLayout r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$1(r1, r2);
    }

    /* renamed from: $r8$lambda$xmAloglnryY5KkNdfLNHAKRR-cQ, reason: not valid java name */
    public static /* synthetic */ void m21585$r8$lambda$xmAloglnryY5KkNdfLNHAKRRcQ(ChatAttachAlertRichLayout r0, TL_iv.pageBlockMath r1, String r2) {
        r0.lambda$onBlockButtonClicked$3(r1, r2);
    }

    /* renamed from: $r8$lambda$zp9lBohig9Yn8FGLzmyhbJ2dB-w, reason: not valid java name */
    public static /* synthetic */ void m21586$r8$lambda$zp9lBohig9Yn8FGLzmyhbJ2dBw(String r1, String[] r2, ImageView r3, Theme.ResourcesProvider r4, boolean r5, int[] r6, ButtonWithCounterView r7, HorizontalScrollView r8, boolean[] r9, Bitmap r10, Boolean r11) {
        if (TextUtils.equals(r1, r2[0]) == true) goto L6;
        return;
    L6:
        if (r11.booleanValue() == false) goto L10;
        r3.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_text_RedBold, r4), PorterDuff.Mode.SRC_IN));
        if (r5 == true) goto L11;
        int r12 = -r6[0];
        r6[0] = r12;
        AndroidUtilities.shakeViewSpring(r3, r12);
    L11:
        if (r10 == null) goto L13;
        r3.setImageBitmap(r10);
    L13:
        r7.setEnabled(!r11.booleanValue());
        if (r10 == null) goto L16;
        int r13 = 0;
    L17:
        r8.setVisibility(r13);
        r9[0] = r11.booleanValue();
        return;
    L16:
        r13 = 8;
        goto L17
    L10:
        r3.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r4), PorterDuff.Mode.SRC_IN));
        goto L11
    }

    /* renamed from: $r8$lambda$zspny-cZzeM5a1cKB479-Qul7u4, reason: not valid java name */
    public static /* synthetic */ void m21587$r8$lambda$zspnycZzeM5a1cKB479Qul7u4(ChatAttachAlertRichLayout r0) {
        r0.lambda$showSendPreview$22();
    }

    /* renamed from: -$$Nest$fgetcommandSuggestions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichCommandSuggestions m21588$$Nest$fgetcommandSuggestions(ChatAttachAlertRichLayout r0) {
        return r0.commandSuggestions;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21589$$Nest$fgetcurrentAccount(ChatAttachAlertRichLayout r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentItemTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21590$$Nest$fgetcurrentItemTop(ChatAttachAlertRichLayout r0) {
        return r0.currentItemTop;
    }

    /* renamed from: -$$Nest$fgetemojiSearchOpened, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21591$$Nest$fgetemojiSearchOpened(ChatAttachAlertRichLayout r0) {
        return r0.emojiSearchOpened;
    }

    /* renamed from: -$$Nest$fgetemojiTargetEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichEditText m21592$$Nest$fgetemojiTargetEditText(ChatAttachAlertRichLayout r0) {
        return r0.emojiTargetEditText;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichEditorListView m21593$$Nest$fgetlistView(ChatAttachAlertRichLayout r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetmessageSendPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageSendPreview m21594$$Nest$fgetmessageSendPreview(ChatAttachAlertRichLayout r0) {
        return r0.messageSendPreview;
    }

    /* renamed from: -$$Nest$fgettoolbar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichEditorToolbar m21595$$Nest$fgettoolbar(ChatAttachAlertRichLayout r0) {
        return r0.toolbar;
    }

    /* renamed from: -$$Nest$fputcommandSuggestions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21596$$Nest$fputcommandSuggestions(ChatAttachAlertRichLayout r0, RichCommandSuggestions r1) {
        r0.commandSuggestions = r1;
    }

    /* renamed from: -$$Nest$fputemojiSearchOpened, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21597$$Nest$fputemojiSearchOpened(ChatAttachAlertRichLayout r0, boolean r1) {
        r0.emojiSearchOpened = r1;
    }

    /* renamed from: -$$Nest$fputemojiTargetEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21598$$Nest$fputemojiTargetEditText(ChatAttachAlertRichLayout r0, RichEditText r1) {
        r0.emojiTargetEditText = r1;
    }

    /* renamed from: -$$Nest$fputemojiTargetSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21599$$Nest$fputemojiTargetSelection(ChatAttachAlertRichLayout r0, int r1) {
        r0.emojiTargetSelection = r1;
    }

    /* renamed from: -$$Nest$fputmenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21600$$Nest$fputmenu(ChatAttachAlertRichLayout r0, ItemOptions r1) {
        r0.menu = r1;
    }

    /* renamed from: -$$Nest$fputmessageSendPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21601$$Nest$fputmessageSendPreview(ChatAttachAlertRichLayout r0, MessageSendPreview r1) {
        r0.messageSendPreview = r1;
    }

    /* renamed from: -$$Nest$mlayoutBottomPanels, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21602$$Nest$mlayoutBottomPanels(ChatAttachAlertRichLayout r0) {
        r0.layoutBottomPanels();
    }

    /* renamed from: -$$Nest$monBlockButtonClicked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21603$$Nest$monBlockButtonClicked(ChatAttachAlertRichLayout r0, int r1, View r2) {
        r0.onBlockButtonClicked(r1, r2);
    }

    /* renamed from: -$$Nest$mopenAttach, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21604$$Nest$mopenAttach(ChatAttachAlertRichLayout r0, int r1, int r2) {
        r0.openAttach(r1, r2);
    }

    /* renamed from: -$$Nest$mopenLocationPicker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21605$$Nest$mopenLocationPicker(ChatAttachAlertRichLayout r0, BlockRow r1) {
        r0.openLocationPicker(r1);
    }

    /* renamed from: -$$Nest$mresolveEmojiTarget, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichEditText m21606$$Nest$mresolveEmojiTarget(ChatAttachAlertRichLayout r0) {
        return r0.resolveEmojiTarget();
    }

    /* renamed from: -$$Nest$mresolveEmojiTargetOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21607$$Nest$mresolveEmojiTargetOffset(ChatAttachAlertRichLayout r0, RichEditText r1) {
        return r0.resolveEmojiTargetOffset(r1);
    }

    /* renamed from: -$$Nest$mscheduleLimitCheck, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21608$$Nest$mscheduleLimitCheck(ChatAttachAlertRichLayout r0) {
        r0.scheduleLimitCheck();
    }

    /* renamed from: -$$Nest$mshowSendPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21609$$Nest$mshowSendPreview(ChatAttachAlertRichLayout r0, View r1) {
        return r0.showSendPreview(r1);
    }

    /* renamed from: -$$Nest$mtoggleEmojiPopup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21610$$Nest$mtoggleEmojiPopup(ChatAttachAlertRichLayout r0) {
        r0.toggleEmojiPopup();
    }

    /* renamed from: -$$Nest$mupdateAttachRaise, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21611$$Nest$mupdateAttachRaise(ChatAttachAlertRichLayout r0) {
        r0.updateAttachRaise();
    }

    /* renamed from: -$$Nest$mupdateFormattingButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21612$$Nest$mupdateFormattingButtons(ChatAttachAlertRichLayout r0) {
        r0.updateFormattingButtons();
    }

    /* renamed from: -$$Nest$mupdateFormattingPanel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21613$$Nest$mupdateFormattingPanel(ChatAttachAlertRichLayout r0) {
        r0.updateFormattingPanel();
    }

    /* renamed from: -$$Nest$mupdateHistoryButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21614$$Nest$mupdateHistoryButtons(ChatAttachAlertRichLayout r0) {
        r0.updateHistoryButtons();
    }

    /* renamed from: -$$Nest$mupdateSendButtonLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21615$$Nest$mupdateSendButtonLoading(ChatAttachAlertRichLayout r0) {
        r0.updateSendButtonLoading();
    }

    /* renamed from: -$$Nest$mupdateSendButtonLocked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21616$$Nest$mupdateSendButtonLocked(ChatAttachAlertRichLayout r0) {
        r0.updateSendButtonLocked();
    }

    /* renamed from: -$$Nest$mupdateToolbarBlockType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21617$$Nest$mupdateToolbarBlockType(ChatAttachAlertRichLayout r0) {
        r0.updateToolbarBlockType();
    }

    /* renamed from: -$$Nest$mupdateToolbarTopOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21618$$Nest$mupdateToolbarTopOffset(ChatAttachAlertRichLayout r0) {
        r0.updateToolbarTopOffset();
    }

    static {
        STYLE_FLAGS = new int[]{1, 2, 16, 8, 256, 4, 16384, 32768};
    }

    public ChatAttachAlertRichLayout(ChatAttachAlert r6, Context r7, int r8, Theme.ResourcesProvider r9) {
        super(r6, r7, r9);
        AnonymousClass2 r62 = new AnonymousClass2(this);
        this.toolbarDelegate = r62;
        this.attachButtonsShown = true;
        this.limitCheckRunnable = new ChatAttachAlertRichLayout$$ExternalSyntheticLambda9(this);
        this.currentAccount = r8;
        this.occupyStatusBar = true;
        this.occupyNavigationBar = true;
        RichEditorListView r1 = new RichEditorListView(r7, r8, r9, new AnonymousClass1(this, r9));
        this.listView = r1;
        r1.setAdaptiveLinkDialogs(false);
        r1.setAllowTapAboveContent(false);
        addView(r1, LayoutHelper.createFrame(-1, -1, 119));
        addView(r1.getOverlayView(), LayoutHelper.createFrame(-1, -1, 119));
        r1.seedEmptyArticle();
        r1.resetHistoryBaseline();
        setFocusable(true);
        setFocusableInTouchMode(true);
        if (Build.VERSION.SDK_INT < 26) goto L5;
        setDefaultFocusHighlightEnabled(false);
    L5:
        setBackground(null);
        setForeground(null);
        RichEditorToolbar r0 = new RichEditorToolbar(r7, r62);
        this.toolbar = r0;
        r0.setBackVisible(false);
        this.toolbar.setTopGradientVisible(false);
        updateSendButtonLocked();
        addView(this.toolbar, LayoutHelper.createFrame(-1, -1, 119));
        updateHistoryButtons();
        updateToolbarBlockType();
        updateAttachButtons(false);
        getViewTreeObserver().addOnGlobalFocusChangeListener(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda10(this));
    }

    public static /* synthetic */ ChatAttachAlert access$000(ChatAttachAlertRichLayout r0) {
        return r0.parentAlert;
    }

    public static /* synthetic */ ChatAttachAlert access$100(ChatAttachAlertRichLayout r0) {
        return r0.parentAlert;
    }

    public static /* synthetic */ ChatAttachAlert access$200(ChatAttachAlertRichLayout r0) {
        return r0.parentAlert;
    }

    public static /* synthetic */ ChatAttachAlert access$300(ChatAttachAlertRichLayout r0) {
        return r0.parentAlert;
    }

    public static /* synthetic */ ChatAttachAlert access$400(ChatAttachAlertRichLayout r0) {
        return r0.parentAlert;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$500(ChatAttachAlertRichLayout r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$600(ChatAttachAlertRichLayout r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$700(ChatAttachAlertRichLayout r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ChatAttachAlert access$800(ChatAttachAlertRichLayout r0) {
        return r0.parentAlert;
    }

    private void addHeadingItem(ItemOptions r4, BlockRow r5, TL_iv.PageBlock r6, int r7, String r8, int r9, ItemOptions r10) {
        if (r5 != null) goto L5;
    L7:
        boolean r1 = false;
    L8:
        r4.addChecked(r1, r7, r8, new ChatAttachAlertRichLayout$$ExternalSyntheticLambda0(this, r5, r6, r10));
        r4.getLast().textView.setTypeface(AndroidUtilities.bold());
        r4.getLast().textView.setTextSize(1, r9);
        return;
    L5:
        if (r5.block.getClass() != r6.getClass()) goto L7;
        r1 = true;
        goto L8
    }

    private int attachRaiseTarget(boolean r1) {
        if (r1 == true) goto L8;
        ChatAttachAlert r0 = this.parentAlert;
        if (r0.pinnedToTop == false) goto L7;
        return 0;
    L7:
        return r0.getTypeButtonsHeight();
    L8:
        return 0;
    }

    private int bottomNavInset() {
        if (this.keyboardVisible == false) goto L5;
        return 0;
    L5:
        if (this.emojiPadding <= 0) goto L8;
        return 0;
    L8:
        return AndroidUtilities.navigationBarHeight;
    }

    private boolean checkDiscard() {
        RichEditorListView r0 = this.listView;
        if (r0 != null) goto L6;
        return true;
    L6:
        if (r0.hasAnyText() == false) goto L9;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.ArticleSaveDraftTitle)).setMessage(LocaleController.getString(R.string.ArticleSaveDraftMessage)).setNegativeButton(LocaleController.getString(R.string.Delete), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda13(this)).setPositiveButton(LocaleController.getString(R.string.Save), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda14(this)).makeRed(-2).show();
        return false;
    L9:
        return true;
    }

    private void closeEmojiSearch() {
        if (this.emojiSearchOpened == true) goto L5;
        return;
    L5:
        this.emojiSearchOpened = false;
        EmojiView r1 = this.emojiView;
        if (r1 == null) goto L8;
        r1.closeSearch(false);
        this.emojiView.hideSearchKeyboard();
    L8:
        layoutBottomPanels();
    }

    private void createEmojiView() {
        if (this.emojiView == null) goto L5;
        return;
    L5:
        BaseFragment r1 = this.parentAlert.baseFragment;
        TLRPC.ChatFull r3 = null;
        if ((r1 instanceof ChatActivity) == false) goto L8;
        ChatActivity r12 = (ChatActivity) r1;
    L9:
        if (r12 == null) goto L11;
        r3 = r12.getCurrentChatInfo();
    L11:
        boolean r17 = LocaleUtils.canUseLocalPremiumEmojis(this.currentAccount);
        EmojiView r4 = new EmojiView(this.parentAlert.baseFragment, true, false, false, getContext(), true, r3, this.parentAlert.sizeNotifierFrameLayout, true, this.resourcesProvider, false, false, r17);
        this.emojiView = r4;
        r4.allowEmojisForNonPremium(r17, false);
        this.emojiView.setVisibility(8);
        EmojiView r2 = this.emojiView;
        r2.fixBottomTabContainerTranslation = false;
        r2.setBottomInset(AndroidUtilities.navigationBarHeight);
        this.emojiView.hideBottomTabContainerBackground();
        this.emojiView.setDelegate(new AnonymousClass7(this, r12));
        addView(this.emojiView, LayoutHelper.createFrame(-1, getEmojiPanelHeight(), 87));
        return;
    L8:
        r12 = null;
        goto L9
    }

    private int emojiVisibleHeight() {
        if (this.emojiSearchOpened == false) goto L7;
        return AndroidUtilities.dp(245.0f);
    L7:
        return this.emojiPadding;
    }

    private int firstItemTopRaw() {
        if (this.listView.getChildCount() <= 0) goto L5;
        int r1 = 0;
        int r2 = Integer.MAX_VALUE;
    L8:
        if (r1 >= this.listView.getChildCount()) goto L15;
        View r3 = this.listView.getChildAt(r1);
        if (this.listView.getChildAdapterPosition(r3) < 0) goto L14;
        if (r3.getTop() >= r2) goto L14;
        r2 = r3.getTop();
    L14:
        r1 = r1 + 1;
        goto L8
    L15:
        if (r2 == Integer.MAX_VALUE) goto L17;
        return r2;
    L17:
        return this.listView.getPaddingTop();
    L5:
        return this.listView.getPaddingTop();
    }

    private int getEmojiPanelHeight() {
        int r3 = this.parentAlert.sizeNotifierFrameLayout.measureKeyboardHeight();
        if (r3 > 0) goto L9;
        SharedPreferences r32 = MessagesController.getGlobalEmojiSettings();
        Point r1 = AndroidUtilities.displaySize;
        if (r1.x <= r1.y) goto L7;
        String r12 = "kbd_height_land3";
    L8:
        r3 = r32.getInt(r12, AndroidUtilities.dp(200.0f));
        goto L9
    L7:
        r12 = "kbd_height";
    L9:
        if (r3 > 0) goto L12;
        r3 = AndroidUtilities.dp(200.0f);
    L12:
        return r3 + AndroidUtilities.navigationBarHeight;
    }

    private void hideEmojiPopup() {
        hideEmojiPopup(false);
    }

    private void hideEmojiPopup(boolean r3) {
        if (this.emojiSearchOpened == false) goto L9;
        this.emojiSearchOpened = false;
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L9;
        r0.closeSearch(false);
        if (r3 == true) goto L9;
        this.emojiView.hideSearchKeyboard();
    L9:
        this.emojiTargetEditText = null;
        EmojiView r32 = this.emojiView;
        if (r32 == null) goto L12;
        r32.setTranslationY(0.0f);
        this.emojiView.setVisibility(8);
    L12:
        this.emojiViewVisible = false;
        this.emojiPadding = 0;
        RichEditorToolbar r33 = this.toolbar;
        if (r33 == null) goto L15;
        r33.setEmojiOpened(false);
    L15:
        updateAttachButtons(false);
        requestLayout();
    }

    private boolean isSendLocked() {
        if (MessagesController.getInstance(this.currentAccount).richEditorAllowed() == false) goto L5;
        return false;
    L5:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L7;
        return false;
    L7:
        if (this.listView.isLossy() == false) goto L13;
        return true;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$addHeadingItem$11(BlockRow r1, TL_iv.PageBlock r2, ItemOptions r3) {
        this.listView.turnIntoKeepList(r1, r2);
        r3.dismiss();
    }

    private /* synthetic */ void lambda$checkDiscard$1(AlertDialog r1, int r2) {
        this.parentAlert.dismiss();
    }

    private /* synthetic */ void lambda$checkDiscard$2(AlertDialog r1, int r2) {
        persistDraft();
        this.parentAlert.dismiss();
    }

    private /* synthetic */ void lambda$new$0(View r1, View r2) {
        updateToolbarBlockType();
    }

    private /* synthetic */ void lambda$onBlockButtonClicked$3(TL_iv.pageBlockMath r1, String r2) {
        if (r1 == null) goto L5;
        r1.source = r2;
        this.listView.adapter.update(false);
        return;
    L5:
        TL_iv.pageBlockMath r12 = new TL_iv.pageBlockMath();
        r12.source = r2;
        this.listView.addBlock(r12);
    }

    private /* synthetic */ void lambda$openAttach$25(ChatAttachAlert r1, TLRPC.MessageMedia r2, int r3, boolean r4, int r5, long r6) {
        if (r2 != null) goto L5;
    L9:
        r1.dismiss(true);
        return;
    L5:
        if (r2.geo == null) goto L9;
        TL_iv.pageBlockMap r42 = new TL_iv.pageBlockMap();
        r42.geo = r2.geo;
        r42.zoom = 15;
        r42.w = MediaError.DetailedErrorCode.TEXT_UNKNOWN;
        r42.h = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        this.listView.addBlock(r42);
        updateSendButton(true);
        r1.dismiss(true);
    }

    private /* synthetic */ void lambda$openAttach$26(ChatAttachAlert r1, ArrayList r2, CharSequence r3, boolean r4, int r5, int r6, long r7, boolean r9, long r10) {
        if (r2 != null) goto L4;
    L6:
        r1.dismiss(true);
        return;
    L4:
        if (r2.isEmpty() == true) goto L6;
        this.listView.attachAudio((MessageObject) r2.get(0));
        goto L6
    }

    private /* synthetic */ void lambda$openLocationPicker$23(BlockRow r3) {
        View r0 = this.listView.findViewByItemObject(r3);
        boolean r1 = r0 instanceof RichMapCell;
        RichEditorListView r2 = this.listView;
        if (r1 == false) goto L6;
        ((RichMapCell) r0).bind(r3, r2.getMapDelegate());
        return;
    L6:
        r2.adapter.update(false);
    }

    private /* synthetic */ void lambda$openLocationPicker$24(BlockRow r1, ChatAttachAlert r2, TLRPC.MessageMedia r3, int r4, boolean r5, int r6, long r7) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.geo == null) goto L20;
        RichEditorHistory r42 = this.listView.history;
        if (r42 == null) goto L9;
        r42.flush();
    L9:
        TL_iv.pageBlockMap r43 = (TL_iv.pageBlockMap) r1.block;
        r43.geo = r3.geo;
        r43.zoom = 15;
        if (r43.w > 0) goto L12;
    L13:
        r43.w = MediaError.DetailedErrorCode.TEXT_UNKNOWN;
        r43.h = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
    L14:
        RichEditorHistory r32 = this.listView.history;
        if (r32 == null) goto L17;
        r32.record();
    L17:
        updateSendButton(true);
        r2.dismiss(true);
        this.listView.post(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda24(this, r1));
        return;
    L12:
        if (r43.h > 0) goto L14;
    }

    private /* synthetic */ void lambda$showConversionSheet$27() {
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L6;
        new PremiumFeatureBottomSheet(this.parentAlert.baseFragment, getContext(), this.currentAccount, 43, true).show();
        return;
    }

    private /* synthetic */ void lambda$showListMenu$12(BlockRow r2) {
        this.listView.turnIntoList(r2, 0);
    }

    private /* synthetic */ void lambda$showListMenu$13(BlockRow r2) {
        this.listView.turnIntoList(r2, 1);
    }

    private /* synthetic */ void lambda$showListMenu$14(BlockRow r2) {
        this.listView.turnIntoList(r2, 2);
    }

    private /* synthetic */ void lambda$showListMenu$15(BlockRow r2) {
        this.listView.turnIntoList(r2, 3);
    }

    private /* synthetic */ void lambda$showListMenu$16(ItemOptions r2) {
        this.listView.indentSelection(false);
        r2.dismiss();
    }

    private /* synthetic */ void lambda$showListMenu$17(ItemOptions r2) {
        this.listView.indentSelection(true);
        r2.dismiss();
    }

    private /* synthetic */ void lambda$showSendPreview$18(DialogInterface r1) {
        this.messageSendPreview = null;
    }

    private /* synthetic */ void lambda$showSendPreview$19(View r8) {
        sendSelectedItems(true, 0, 0, 0, false);
        MessageSendPreview r7 = this.messageSendPreview;
        if (r7 == null) goto L6;
        r7.dismiss(true);
        this.messageSendPreview = null;
        return;
    }

    private /* synthetic */ void lambda$showSendPreview$20(long r3) {
        AlertsCreator.createScheduleDatePickerDialog(this.parentAlert.baseFragment.getParentActivity(), r3, new AnonymousClass3(this), this.resourcesProvider);
    }

    private /* synthetic */ void lambda$showSendPreview$21() {
        sendSelectedItems(true, 2147483646, 0, 0, false);
        MessageSendPreview r7 = this.messageSendPreview;
        if (r7 == null) goto L6;
        r7.dismiss(false);
        this.messageSendPreview = null;
        return;
    }

    private /* synthetic */ void lambda$showSendPreview$22() {
        sendSelectedItems(false, 0, 0, 0, false);
        MessageSendPreview r7 = this.messageSendPreview;
        if (r7 == null) goto L6;
        r7.dismiss(true);
        this.messageSendPreview = null;
        return;
    }

    private /* synthetic */ void lambda$showTextTypeMenu$10(BlockRow r2) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockFooter());
    }

    private /* synthetic */ void lambda$showTextTypeMenu$6(BlockRow r2) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockParagraph());
    }

    private /* synthetic */ void lambda$showTextTypeMenu$7(BlockRow r8) {
        this.listView.turnInto(r8, RichEditorListView.newBlockquote(), 0, 0, false, false);
    }

    private /* synthetic */ void lambda$showTextTypeMenu$8(BlockRow r8) {
        this.listView.turnInto(r8, RichEditorListView.newPullquote(), 0, 0, false, false);
    }

    private /* synthetic */ void lambda$showTextTypeMenu$9(BlockRow r2) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockPreformatted());
    }

    private void layoutBottomPanels() {
        int r0 = emojiVisibleHeight();
        EmojiView r1 = this.emojiView;
        float r2 = 0.0f;
        if (r1 == null) goto L14;
        if (this.emojiViewVisible == false) goto L11;
        float r3 = this.emojiPadding - r0;
        if (this.emojiSearchOpened == false) goto L9;
        float r4 = -this.parentAlert.currentPanTranslationY;
    L10:
        float r32 = r3 + r4;
    L12:
        r1.setTranslationY(r32);
        goto L14
    L9:
        r4 = 0.0f;
        goto L10
    L11:
        r32 = 0.0f;
    L14:
        if (this.toolbar != null) goto L16;
        return;
    L16:
        if (this.emojiViewVisible == false) goto L18;
        float r12 = r0;
    L20:
        if (this.emojiViewVisible == true) goto L22;
    L23:
        r12 = r12 + this.parentAlert.currentPanTranslationY;
    L24:
        this.toolbar.getBottomContainer().animate().cancel();
        this.toolbar.getBottomContainer().setTranslationY(-r12);
        boolean r13 = this.emojiViewVisible;
        if (r13 == false) goto L27;
        r2 = r0;
    L27:
        if (r13 == true) goto L29;
    L30:
        r2 = r2 + this.parentAlert.currentPanTranslationY;
    L31:
        this.toolbar.setBottomGradientTranslationY(-r2);
        if (this.lastAttachRise == this.attachRaise) goto L36;
        ViewPropertyAnimator r02 = this.toolbar.getBottomInnerContainer().animate();
        this.lastAttachRise = this.attachRaise;
        r02.translationY(-r1).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        return;
    L36:
        return;
    L29:
        if (this.emojiSearchOpened == false) goto L31;
    L22:
        if (this.emojiSearchOpened == false) goto L24;
    L18:
        r12 = bottomNavInset();
        goto L20
    }

    private void onBlockButtonClicked(int r3, View r4) {
        BlockRow r0 = this.listView.findFocusedRow();
        if (r3 != 1) goto L5;
        showTextTypeMenu(r0, r4);
        return;
    L5:
        if (r3 != 2) goto L7;
        showListMenu(r0, r4);
        return;
    L7:
        if (r3 != 4) goto L9;
        RichEditorListView r32 = this.listView;
        RichTableCell r42 = r32.activeCellSelectionTable;
        if (r42 != null) goto L38;
        RichTableCell r33 = r32.findFocusedTableCell();
        if (r33 == null) goto L38;
        if (r33.getModel() == null) goto L38;
        TL_iv.pageTableCell r02 = this.listView.focusedCellOf(r33);
        if (r02 == null) goto L38;
        this.listView.enterCellSelectionMode(r33, r02);
        r42 = r33;
    L38:
        if (r42 != null) goto L40;
    L45:
        this.listView.addBlock(RichTextCell.newEmptyTable(2, 2));
        return;
    L40:
        if (r42.getModel() == null) goto L45;
        if (r42.hasCellSelection() == false) goto L45;
        this.listView.showTableCellMenu(r42);
        return;
    L9:
        if (r3 != 7) goto L11;
        if (r0 == null) goto L19;
        TL_iv.PageBlock r34 = r0.block;
        if ((r34 instanceof TL_iv.pageBlockMath) == false) goto L19;
        TL_iv.pageBlockMath r35 = (TL_iv.pageBlockMath) r34;
    L20:
        Context r43 = getContext();
        if (r35 != null) goto L23;
    L26:
        String r03 = _UrlKt.FRAGMENT_ENCODE_SET;
    L27:
        showEditLatexSheet(r43, r03, new ChatAttachAlertRichLayout$$ExternalSyntheticLambda25(this, r35), this.resourcesProvider);
        return;
    L23:
        if (TextUtils.isEmpty(r35.source) == true) goto L26;
        r03 = r35.source;
    L19:
        r35 = null;
        goto L20
    L11:
        if (r3 == 9) goto L13;
        return;
    L13:
        this.listView.insertDetails();
    }

    private void openAttach(int r9, int r10) {
        if (this.parentAlert.baseFragment != null) goto L5;
        return;
    L5:
        ChatAttachAlert r1 = new ChatAttachAlert(getContext(), this.parentAlert.baseFragment, false, false, true, this.resourcesProvider);
        r1.setDelegate(new AnonymousClass5(this, r1));
        r1.getPhotoLayout().loadGalleryPhotos();
        r1.setMaxSelectedPhotos(1, true);
        r1.enablePollAttachMode(r9);
        r1.setLocationActivityDelegate(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda16(this, r1));
        r1.setAudioSelectDelegate(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda17(this, r1));
        r1.setDocumentsDelegate(new AnonymousClass6(this, r1));
        r1.init();
        if (r10 == 0) goto L8;
        r1.openAttachLayoutForType(r10);
    L8:
        r1.setFocusable(true);
        r1.show();
    }

    private void openLocationPicker(BlockRow r9) {
        BaseFragment r0 = this.parentAlert.baseFragment;
        if (r0 == null) goto L15;
        if (r9 != null) goto L7;
        return;
    L7:
        if ((r9.block instanceof TL_iv.pageBlockMap) == true) goto L10;
        return;
    L10:
        if (AndroidUtilities.isMapsInstalled(r0) == false) goto L17;
        ChatAttachAlert r1 = new ChatAttachAlert(getContext(), this.parentAlert.baseFragment, false, false, false, null);
        r1.setDelegate(new AnonymousClass4(this));
        r1.setLocationPicker();
        r1.setLocationActivityDelegate(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda18(this, r9, r1));
        r1.init();
        r1.show();
        return;
    L17:
        return;
    }

    private boolean persistDraft() {
        BaseFragment r1 = this.parentAlert.baseFragment;
        if ((r1 instanceof ChatActivity) == true) goto L5;
        return false;
    L5:
        ChatActivity r12 = (ChatActivity) r1;
        if (this.listView.canUndo() == true) goto L8;
        return false;
    L8:
        TL_iv.RichMessage r17 = this.listView.buildDraftRichMessage();
        AccountInstance.getInstance(this.currentAccount).getMediaDataController().saveDraft(r12.getDialogId(), r12.getDraftThreadId(), _UrlKt.FRAGMENT_ENCODE_SET, null, null, null, null, 0, false, false, r17);
        if (r12.getChatActivityEnterView() == null) goto L13;
        r12.getChatActivityEnterView().setRichDraftPreview(r17);
        return true;
    L13:
        return true;
    }

    private RichEditText resolveEmojiTarget() {
        RichEditText r0 = this.listView.getFocusedEditTextOrNull();
        if (r0 == null) goto L6;
        this.emojiTargetEditText = r0;
        this.emojiTargetSelection = Math.max(0, r0.getSelectionEnd());
        return r0;
    L6:
        RichEditText r02 = this.emojiTargetEditText;
        if (r02 == null) goto L10;
        return r02;
    L10:
        return this.listView.findFocusedEditText();
    }

    private int resolveEmojiTargetOffset(RichEditText r2) {
        if (r2 != this.emojiTargetEditText) goto L9;
        if (this.listView.getFocusedEditTextOrNull() == r2) goto L9;
        return Math.min(this.emojiTargetSelection, r2.length());
    L9:
        return Math.max(0, r2.getSelectionEnd());
    }

    private void saveDraftWithBulletin() {
        if ((this.parentAlert.baseFragment instanceof ChatActivity) == true) goto L6;
        return;
    L6:
        if (this.listView.canUndo() == true) goto L9;
        return;
    L9:
        if (persistDraft() == false) goto L12;
        BulletinFactory.of(this.toolbar, this.resourcesProvider).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.RichEditorDraftSaved)).show();
        return;
    }

    private void scheduleLimitCheck() {
        AndroidUtilities.cancelRunOnUIThread(this.limitCheckRunnable);
        AndroidUtilities.runOnUIThread(this.limitCheckRunnable, 1000);
    }

    private void showConversionSheet() {
        Context r0 = getContext();
        RichEditorListView r1 = this.listView;
        Objects.requireNonNull(r1);
        RichEditor.openConversionSheet(r0, new ChatAttachAlertRichLayout$$ExternalSyntheticLambda6(r1), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda7(this), this.resourcesProvider);
    }

    public static void showEditLatexSheet(Context r22, String r23, Utilities.Callback<String> r24, Theme.ResourcesProvider r25) {
        BottomSheet.Builder r11 = new BottomSheet.Builder(r22, true, r25);
        LinearLayout r12 = new LinearLayout(r22);
        r12.setOrientation(1);
        if (r23 != null) goto L5;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        String[] r3 = {r2};
        ImageView r8 = new ImageView(r22);
        r8.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        r8.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(8.0f), Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r25), 0.05f)));
        FrameLayout r26 = new FrameLayout(r22);
        r26.addView(r8, new FrameLayout.LayoutParams(-2, -2, 17));
        HorizontalScrollView r4 = new HorizontalScrollView(r22);
        r4.setHorizontalScrollBarEnabled(false);
        r4.setClipToPadding(false);
        r4.setFillViewport(true);
        r4.setVisibility(8);
        r4.addView(r26, new FrameLayout.LayoutParams(-2, -2));
        r12.addView(r4, LayoutHelper.createLinear(-1, -2, 49, 12, 2, 12, 0));
        ButtonWithCounterView r5 = new ButtonWithCounterView(r22, r25).setRound();
        boolean[] r14 = {false};
        boolean[] r0 = {false};
        ChatAttachAlertRichLayout$$ExternalSyntheticLambda2 r27 = new ChatAttachAlertRichLayout$$ExternalSyntheticLambda2(r3, r4, r5, r0, new ChatAttachAlertRichLayout$$ExternalSyntheticLambda1(r3), r8, r25, new int[]{6});
        EditTextCell r02 = new EditTextCell(r22, LocaleController.getString(R.string.ArticleLatexEquation), true, false, -1, r25);
        r02.editText.setImeOptions(6);
        r02.editText.setMaxLines(5);
        r02.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(24.0f), Theme.getColor(Theme.key_windowBackgroundWhite, r25)));
        r02.setText(r3[0]);
        r02.editText.addTextChangedListener(new AnonymousClass8(r3, r27));
        r12.addView(r02, LayoutHelper.createLinear(-1, -2, 55, 12, 8, 12, 0));
        r5.setText(LocaleController.getString(R.string.Done));
        r12.addView(r5, LayoutHelper.createLinear(-1, 48, 55, 12, 12, 12, 12));
        r27.run();
        r11.setCustomView(r12);
        r11.setOnPreDismissListener(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda3(r02, r14, r0, r23, r3, r24));
        BottomSheet r82 = r11.show();
        int r28 = Theme.key_windowBackgroundGray;
        r82.setBackgroundColor(Theme.getColor(r28, r25));
        r82.fixNavigationBar(Theme.getColor(r28, r25));
        r5.setOnClickListener(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda4(r5, r14, r24, r3, r82));
        AndroidUtilities.runOnUIThread(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda5(r02), 200);
        return;
    L5:
        r2 = r23;
        goto L6
    }

    private void showEmojiPopup() {
        createEmojiView();
        int r0 = getEmojiPanelHeight();
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) this.emojiView.getLayoutParams();
        r1.height = r0;
        this.emojiView.setLayoutParams(r1);
        this.emojiView.setTranslationY(0.0f);
        this.emojiView.setVisibility(0);
        this.emojiViewVisible = true;
        this.emojiPadding = r0;
        RichEditText r02 = this.listView.findFocusedEditText();
        if (r02 == null) goto L5;
        AndroidUtilities.hideKeyboard(r02);
    L5:
        RichEditorToolbar r03 = this.toolbar;
        if (r03 == null) goto L8;
        r03.setEmojiOpened(true);
    L8:
        updateAttachButtons(false);
        requestLayout();
    }

    private void showListMenu(BlockRow r8, View r9) {
        ItemOptions r0 = this.menu;
        if (r0 == null) goto L5;
        r0.dismiss();
    L5:
        ItemOptions r92 = ItemOptions.makeOptions(this, this.resourcesProvider, r9).dontFocus();
        boolean r02 = false;
        if (r8 != null) goto L8;
    L11:
        boolean r2 = true;
    L12:
        ItemOptions r22 = r92.addChecked(r2, R.drawable.field_carret_empty, LocaleController.getString(R.string.ArticleNone), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda26(this, r8));
        if (r8 != null) goto L15;
    L21:
        boolean r3 = false;
    L22:
        ItemOptions r23 = r22.addChecked(r3, R.drawable.iv_list, LocaleController.getString(R.string.ArticleListBulleted), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda27(this, r8));
        if (r8 != null) goto L25;
    L31:
        boolean r32 = false;
    L32:
        ItemOptions r24 = r23.addChecked(r32, R.drawable.iv_ordered_list, LocaleController.getString(R.string.ArticleListNumbered), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda28(this, r8));
        if (r8 != null) goto L35;
    L41:
        boolean r33 = false;
    L42:
        ItemOptions r25 = r24.addChecked(r33, R.drawable.iv_todo, LocaleController.getString(R.string.ArticleListTodo), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda29(this, r8));
        if (r8 != null) goto L45;
    L47:
        int r82 = R.drawable.iv_details;
        String r34 = LocaleController.getString(R.string.ArticleToggleBlock);
        RichEditorListView r4 = this.listView;
        Objects.requireNonNull(r4);
        r25.addChecked(r02, r82, r34, new ChatAttachAlertRichLayout$$ExternalSyntheticLambda30(r4));
        boolean r83 = this.listView.canIndentSelection();
        boolean r03 = this.listView.canOutdentSelection();
        if (r83 == true) goto L50;
        if (r03 == true) goto L50;
    L55:
        this.menu = r92.forceTop(true).show();
        return;
    L50:
        r92.addGap();
        if (r83 == false) goto L53;
        r92.add(R.drawable.iv_list_tab, LocaleController.getString(R.string.ArticleIndent), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda31(this, r92));
    L53:
        if (r03 == false) goto L55;
        r92.add(R.drawable.iv_list_untab, LocaleController.getString(R.string.ArticleOutdent), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda32(this, r92));
        goto L55
    L45:
        if ((r8.block instanceof TL_iv.pageBlockDetails) == false) goto L47;
        r02 = true;
        goto L47
    L35:
        if (r8.isInList() == false) goto L41;
        if (r8.isChecklist() == false) goto L41;
        if (r8.isOrdered() == true) goto L41;
        r33 = true;
        goto L42
    L25:
        if (r8.isInList() == false) goto L31;
        if (r8.isChecklist() == true) goto L31;
        if (r8.isOrdered() == false) goto L31;
        r32 = true;
        goto L32
    L15:
        if (r8.isInList() == false) goto L21;
        if (r8.isChecklist() == true) goto L21;
        if (r8.isOrdered() == true) goto L21;
        r3 = true;
        goto L22
    L8:
        if (r8.isInList() == false) goto L11;
        r2 = false;
        goto L12
    }

    private boolean showSendPreview(View r13) {
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L6;
        new PremiumFeatureBottomSheet(this.parentAlert.baseFragment, getContext(), this.currentAccount, 43, true).show();
        return true;
    L6:
        boolean r2 = false;
        if (this.listView.hasAnyText() == true) goto L10;
        return false;
    L10:
        if (this.listView.hasPendingUploads() == false) goto L13;
        return false;
    L13:
        if (this.listView.isWithinLimits() == true) goto L16;
        updateSendButtonEnabled();
        return false;
    L16:
        ArrayList<TL_iv.PageBlock> r0 = this.listView.flattenRowsToBlocks();
        if (r0.isEmpty() == false) goto L19;
        return false;
    L19:
        BaseFragment r3 = this.parentAlert.baseFragment;
        if ((r3 instanceof ChatActivity) == false) goto L22;
        ChatActivity r32 = (ChatActivity) r3;
    L23:
        MessageSendPreview r4 = this.messageSendPreview;
        if (r4 == null) goto L26;
        r4.dismiss(false);
        this.messageSendPreview = null;
    L26:
        MessageSendPreview r42 = new MessageSendPreview(getContext(), this.resourcesProvider);
        this.messageSendPreview = r42;
        r42.setOnDismissListener(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda19(this));
        long r6 = this.parentAlert.getDialogId();
        if (r32 == null) goto L29;
        MessageObject r43 = r32.getReplyMessage();
    L30:
        TLRPC.TL_message r8 = new TLRPC.TL_message();
        r8.id = 0;
        r8.out = true;
        r8.peer_id = MessagesController.getInstance(this.currentAccount).getPeer(r6);
        r8.from_id = MessagesController.getInstance(this.currentAccount).getPeer(UserConfig.getInstance(this.currentAccount).getClientUserId());
        r8.flags2 |= 8192;
        TL_iv.RichMessage r9 = new TL_iv.RichMessage();
        r8.rich_message = r9;
        r9.blocks = r0;
        r9.photos = this.listView.collectPhotos();
        r8.rich_message.documents = this.listView.collectDocuments();
        if (r43 != null) goto L33;
    L35:
        MessageObject r02 = new MessageObject(this.currentAccount, r8, false, false);
        if (r43 != null) goto L38;
    L40:
        r02.sendPreview = true;
        r02.isOutOwnerCached = Boolean.TRUE;
        r02.generateLayout(null);
        r02.notime = true;
        ArrayList<MessageObject> r44 = new ArrayList();
        r44.add(r02);
        this.messageSendPreview.setMessageObjects(r44);
        ChatActivityEnterView.SendButton r03 = this.toolbar.getSendButton();
        r03.setScaleX(1.0f);
        r03.setScaleY(1.0f);
        ChatActivityEnterView.SendButton r45 = this.messageSendPreview.setSendButton(r03, true, new ChatAttachAlertRichLayout$$ExternalSyntheticLambda20(this));
        if (r45 == null) goto L43;
        r45.setBackground(RichEditor.withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), getThemedColor(Theme.key_featuredStickers_addButton))));
        this.messageSendPreview.setSendButtonWidth(AndroidUtilities.dp(44.0f));
    L43:
        ItemOptions r04 = ItemOptions.makeOptions(this, this.resourcesProvider, r03);
        if (r32 != null) goto L46;
    L48:
        if (r32 != null) goto L50;
    L60:
        if (r2 == true) goto L62;
        r04.add(R.drawable.input_notify_off, LocaleController.getString(R.string.SendWithoutSound), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda23(this));
    L62:
        r04.setupSelectors();
        this.messageSendPreview.setItemOptions(r04);
        this.messageSendPreview.show();
        r13.performHapticFeedback(3, 2);     // Catch: Exception -> L65
    L64:
        return true;
    L50:
        if (r32.canScheduleMessage() == false) goto L60;
        int r33 = R.drawable.msg_calendar2;
        if (r2 == false) goto L54;
        int r46 = R.string.SetReminder;
    L55:
        r04.add(r33, LocaleController.getString(r46), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda21(this, r6));
        if (r2 == true) goto L60;
        if (r6 <= 0) goto L60;
        r04.add(R.drawable.msg_online, LocaleController.getString(R.string.SendWhenOnline), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda22(this));
        goto L60
    L54:
        r46 = R.string.ScheduleMessage;
        goto L55
    L46:
        if (UserObject.isUserSelf(r32.getCurrentUser()) == false) goto L48;
        r2 = true;
        goto L48
    L38:
        if (r43.isTopicMainMessage == true) goto L40;
        r02.replyMessageObject = r43;
        goto L40
    L33:
        if (r43.isTopicMainMessage == true) goto L35;
        TLRPC.TL_messageReplyHeader r05 = new TLRPC.TL_messageReplyHeader();
        r05.flags |= 16;
        r05.reply_to_msg_id = r43.getId();
        r8.reply_to = r05;
        goto L35
    L29:
        r43 = null;
        goto L30
    L22:
        r32 = null;
        goto L23
    }

    private void showTextTypeMenu(BlockRow r11, View r12) {
        ItemOptions r0 = this.menu;
        if (r0 == null) goto L5;
        r0.dismiss();
    L5:
        boolean r1 = true;
        ItemOptions r9 = ItemOptions.makeOptions(this, this.resourcesProvider, r12, true).dontFocus();
        ItemOptions r3 = r9.makeSwipeback();
        if (MessagesController.getInstance(this.currentAccount).richEditorAllowed() == false) goto L8;
    L10:
        boolean r122 = false;
    L11:
        r3.add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda33(r9));
        r3.addGap();
        addHeadingItem(r3, r11, new TL_iv.pageBlockHeading1(), R.drawable.iv_h1, LocaleController.getString(R.string.ArticleHeading1), SharedConfig.fontSize + 2, r9);
        addHeadingItem(r3, r11, new TL_iv.pageBlockHeading2(), R.drawable.iv_h2, LocaleController.getString(R.string.ArticleHeading2), SharedConfig.fontSize + 1, r9);
        addHeadingItem(r3, r11, new TL_iv.pageBlockHeading3(), R.drawable.iv_h3, LocaleController.getString(R.string.ArticleHeading3), SharedConfig.fontSize, r9);
        addHeadingItem(r3, r11, new TL_iv.pageBlockHeading4(), R.drawable.iv_h4, LocaleController.getString(R.string.ArticleHeading4), SharedConfig.fontSize - 1, r9);
        addHeadingItem(r3, r11, new TL_iv.pageBlockHeading5(), R.drawable.iv_h5, LocaleController.getString(R.string.ArticleHeading5), SharedConfig.fontSize - 2, r9);
        addHeadingItem(r3, r11, new TL_iv.pageBlockHeading6(), R.drawable.iv_h6, LocaleController.getString(R.string.ArticleHeading6), SharedConfig.fontSize - 3, r9);
        if (r11 != null) goto L14;
    L16:
        boolean r10 = false;
    L17:
        r9.addChecked(r10, new RichEditor.RequiresPremiumDrawable(getContext(), R.drawable.iv_h1).setPremium(r122), LocaleController.getString(R.string.ArticleHeading), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda34(r9, r3));
        if (r11 != null) goto L20;
    L22:
        boolean r102 = false;
    L23:
        r9.addChecked(r102, R.drawable.iv_text, LocaleController.getString(R.string.ArticleText), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda35(this, r11));
        if (r11 != null) goto L26;
    L28:
        boolean r103 = false;
    L29:
        r9.addChecked(r103, R.drawable.iv_quote, LocaleController.getString(R.string.ArticleQuote), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda36(this, r11));
        if (r11 != null) goto L32;
    L34:
        boolean r104 = false;
    L35:
        r9.addChecked(r104, new RichEditor.RequiresPremiumDrawable(getContext(), R.drawable.iv_pullquote).setPremium(r122), LocaleController.getString(R.string.ArticlePullquote), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda37(this, r11));
        if (r11 != null) goto L38;
    L40:
        boolean r105 = false;
    L41:
        r9.addChecked(r105, R.drawable.iv_code, LocaleController.getString(R.string.ArticleCode), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda38(this, r11));
        if (r11 != null) goto L44;
    L46:
        r1 = false;
    L47:
        r9.addChecked(r1, new RichEditor.RequiresPremiumDrawable(getContext(), R.drawable.iv_footer).setPremium(r122), LocaleController.getString(R.string.ArticleFooter), new ChatAttachAlertRichLayout$$ExternalSyntheticLambda39(this, r11));
        this.menu = r9.show();
        return;
    L44:
        if ((r11.block instanceof TL_iv.pageBlockFooter) == false) goto L46;
    L38:
        if ((r11.block instanceof TL_iv.pageBlockPreformatted) == false) goto L40;
        r105 = true;
        goto L41
    L32:
        if ((r11.block instanceof TL_iv.pageBlockPullquote) == false) goto L34;
        r104 = true;
        goto L35
    L26:
        if ((r11.block instanceof TL_iv.pageBlockBlockquote) == false) goto L28;
        r103 = true;
        goto L29
    L20:
        if ((r11.block instanceof TL_iv.pageBlockParagraph) == false) goto L22;
        r102 = true;
        goto L23
    L14:
        if (RichEditorListView.isHeading(r11.block) == false) goto L16;
        r10 = true;
        goto L17
    L8:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L10;
        r122 = true;
        goto L11
    }

    private void toggleEmojiPopup() {
        if (this.emojiViewVisible == false) goto L9;
        RichEditText r0 = this.listView.findFocusedEditText();
        if (r0 == null) goto L7;
        r0.requestEditFocus();
        AndroidUtilities.showKeyboard(r0);
    L7:
        hideEmojiPopup(true);
        return;
    L9:
        showEmojiPopup();
    }

    private void updateAttachButtons(boolean r4) {
        if (this.listView.hasAnyText() == false) goto L5;
    L8:
        boolean r0 = true;
    L9:
        this.parentAlert.setTypeButtonsHidden(r0, r4);
        this.attachRaise = attachRaiseTarget(r0);
        layoutBottomPanels();
        if (this.attachButtonsShown != r0) goto L13;
        this.attachButtonsShown = !r0;
        requestLayout();
        return;
    L13:
        return;
    L5:
        if (this.emojiViewVisible == true) goto L8;
        r0 = false;
        goto L9
    }

    private void updateAttachRaise() {
        if (this.listView.hasAnyText() == false) goto L5;
    L8:
        boolean r0 = true;
    L9:
        int r02 = attachRaiseTarget(r0);
        if (this.attachRaise == r02) goto L13;
        this.attachRaise = r02;
        layoutBottomPanels();
        return;
    L13:
        return;
    L5:
        if (this.emojiViewVisible == true) goto L8;
        r0 = false;
        goto L9
    }

    private void updateFormattingButtons() {
        TextSelectionHelper.ArticleTextSelectionHelper r1 = this.listView.getTextSelectionHelper();
        if (this.toolbar == null) goto L53;
        if (r1 != null) goto L6;
        return;
    L6:
        if (r1.isInSelectionMode() == false) goto L55;
        this.toolbar.setQuoteState(this.listView.isSelectionQuoted());
        if (this.listView.isTableSelection() == false) goto L13;
        updateFormattingButtonsTable();
        return;
    L13:
        if (this.listView.isCaptionSelection() == false) goto L16;
        updateFormattingButtonsCaption();
        return;
    L16:
        int r5 = r1.getStartCell();
        int r7 = r1.getEndCell();
        int r6 = r1.getStartOffset();
        int r8 = r1.getEndOffset();
        if (r5 < 0) goto L23;
        if (r7 < 0) goto L23;
        if (r7 < r5) goto L23;
        if (r7 >= this.listView.itemRows.size()) goto L23;
        boolean r9 = true;
    L24:
        if (r9 == false) goto L32;
        int[] r10 = STYLE_FLAGS;
        int r11 = r10.length;
        int r12 = 0;
        int r13 = 0;
    L26:
        if (r12 >= r11) goto L31;
        int r4 = r10[r12];
        if (this.listView.isStyleFullyApplied(r4, r5, r6, r7, r8) == false) goto L30;
        r13 = r13 | r4;
    L30:
        r12 = r12 + 1;
        goto L26
    L31:
        int r15 = r13;
    L33:
        RichEditorToolbar r14 = this.toolbar;
        if (r9 == true) goto L36;
    L38:
        boolean r16 = false;
    L39:
        if (r9 == true) goto L41;
    L43:
        boolean r17 = false;
    L44:
        if (r9 == false) goto L47;
        if (r5 != r7) goto L47;
        boolean r18 = true;
    L48:
        r14.setFormattingState(r15, r16, r17, r18, this.listView.canCreateInlineButtonOnSelection(), !this.listView.isSelectionAllHeadings());
        return;
    L47:
        r18 = false;
        goto L48
    L41:
        if (this.listView.isDateApplied(r5, r6, r7, r8) == false) goto L43;
        r17 = true;
        goto L44
    L36:
        if (this.listView.isLinkApplied(r5, r6, r7, r8) == false) goto L38;
        r16 = true;
        goto L39
    L32:
        r15 = 0;
    L23:
        r9 = false;
        goto L24
    L55:
        return;
    }

    private void updateFormattingButtonsCaption() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.listView.getTextSelectionHelper();
        RichEditText r1 = this.listView.captionEditText(r0.getStartCell());
        int r2 = r0.getStartOffset();
        int r02 = r0.getEndOffset();
        if (r1 != null) goto L5;
        int r4 = 0;
    L6:
        if (r1 != null) goto L8;
        int r03 = 0;
    L9:
        if (r1 == null) goto L18;
        if (r4 >= r03) goto L18;
        int[] r22 = STYLE_FLAGS;
        int r5 = r22.length;
        int r6 = 0;
        int r7 = 0;
    L12:
        if (r6 >= r5) goto L17;
        int r8 = r22[r6];
        if ((r1.getCurrentStyle(r4, r03) & r8) == 0) goto L16;
        r7 = r7 | r8;
    L16:
        r6 = r6 + 1;
        goto L12
    L17:
        int r9 = r7;
    L19:
        RichEditorToolbar r82 = this.toolbar;
        if (r1 == null) goto L25;
        if (r4 >= r03) goto L25;
        if (RichTextStyle.hasLink(r1.getText(), r4, r03) == false) goto L25;
        boolean r10 = true;
    L26:
        if (r1 == null) goto L31;
        if (r4 >= r03) goto L31;
        if (RichTextStyle.hasDate(r1.getText(), r4, r03) == false) goto L31;
        boolean r11 = true;
    L32:
        r82.setFormattingState(r9, r10, r11, true, this.listView.canCreateInlineButtonOnSelection(), true);
        return;
    L31:
        r11 = false;
    L25:
        r10 = false;
    L18:
        r9 = 0;
        goto L19
    L8:
        r03 = Math.max(0, Math.min(Math.max(r2, r02), r1.length()));
        goto L9
    L5:
        r4 = Math.max(0, Math.min(Math.min(r2, r02), r1.length()));
        goto L6
    }

    private void updateFormattingButtonsTable() {
        TextSelectionHelper.ArticleTextSelectionHelper r1 = this.listView.getTextSelectionHelper();
        int r4 = r1.getStartCell();
        int r5 = r1.getStartChildPosition();
        int r7 = r1.getEndChildPosition();
        int r6 = r1.getStartOffset();
        int r8 = r1.getEndOffset();
        int[] r12 = STYLE_FLAGS;
        int r9 = r12.length;
        int r11 = 0;
        int r13 = 0;
    L3:
        if (r11 >= r9) goto L9;
        int r3 = r12[r11];
        if (this.listView.isStyleFullyAppliedTable(r3, r4, r5, r6, r7, r8) == false) goto L7;
        r13 = r13 | r3;
    L7:
        r11 = r11 + 1;
        goto L3
    L9:
        if (r5 != r7) goto L11;
        boolean r16 = true;
    L12:
        if (r16 == false) goto L14;
        RichEditText r2 = this.listView.tableEditText(r4, r5);
    L15:
        int r32 = Math.max(0, Math.min(r6, r8));
        if (r2 != null) goto L18;
        int r42 = 0;
    L19:
        RichEditorToolbar r122 = this.toolbar;
        if (r2 == null) goto L25;
        if (r32 >= r42) goto L25;
        if (RichTextStyle.hasLink(r2.getText(), r32, r42) == false) goto L25;
        boolean r14 = true;
    L26:
        if (r2 == null) goto L31;
        if (r32 >= r42) goto L31;
        if (RichTextStyle.hasDate(r2.getText(), r32, r42) == false) goto L31;
        boolean r15 = true;
    L32:
        r122.setFormattingState(r13, r14, r15, r16, this.listView.canCreateInlineButtonOnSelection(), true);
        return;
    L31:
        r15 = false;
    L25:
        r14 = false;
        goto L26
    L18:
        r42 = Math.max(0, Math.min(Math.max(r6, r8), r2.length()));
        goto L19
    L14:
        r2 = null;
        goto L15
    L11:
        r16 = false;
        goto L12
    }

    private void updateFormattingPanel() {
        if (this.toolbar != null) goto L6;
        return;
    L6:
        if (this.listView.isInSelectionMode() == true) goto L8;
    L10:
        boolean r0 = false;
    L11:
        this.toolbar.showFormattingPanel(r0, true);
        if (r0 == false) goto L15;
        updateFormattingButtons();
        return;
    L15:
        return;
    L8:
        if (this.listView.selectionHasInlineFormattable() == false) goto L10;
        r0 = true;
        goto L11
    }

    private void updateHistoryButtons() {
        RichEditorToolbar r0 = this.toolbar;
        if (r0 == null) goto L6;
        r0.setHistoryEnabled(this.listView.canUndo(), this.listView.canRedo());
        return;
    }

    private void updateSendButton(boolean r1) {
        updateAttachButtons(r1);
    }

    private void updateSendButtonEnabled() {
        RichEditorToolbar r0 = this.toolbar;
        if (r0 == null) goto L6;
        r0.setSendEnabled(this.listView.isWithinLimits());
        return;
    }

    private void updateSendButtonLoading() {
        RichEditorToolbar r0 = this.toolbar;
        if (r0 == null) goto L5;
        r0.setSendLoading(this.listView.hasPendingUploads());
    L5:
        updateSendButton(true);
    }

    private void updateSendButtonLocked() {
        if (this.toolbar == null) goto L17;
        boolean r1 = false;
        if (MessagesController.getInstance(this.currentAccount).richEditorAllowed() == false) goto L7;
    L9:
        boolean r0 = false;
    L10:
        ChatActivityEnterView.SendButton r3 = this.toolbar.getSendButton();
        if (r0 == true) goto L13;
    L15:
        r3.setLocked(r1);
        this.toolbar.setPremiumLocked(r0);
        return;
    L13:
        if (this.listView.isLossy() == false) goto L15;
        r1 = true;
        goto L15
    L7:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L9;
        r0 = true;
        goto L10
    }

    private void updateToolbarBlockType() {
        if (this.toolbar != null) goto L5;
        return;
    L5:
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.listView.getTextSelectionHelper();
        if (r0 != null) goto L8;
    L13:
        BlockRow r02 = this.listView.findFocusedRow();
    L14:
        int r4 = 4;
        int r5 = 0;
        if (this.listView.findFocusedTableCell() != null) goto L46;
        if (r02 != null) goto L20;
    L18:
        r4 = 0;
        goto L46
    L20:
        if (r02.isChecklist() == false) goto L22;
    L45:
        r4 = 2;
        goto L46
    L22:
        if (r02.isInList() == true) goto L45;
        if (r02.isOrdered() == true) goto L45;
        TL_iv.PageBlock r1 = r02.block;
        if ((r1 instanceof TL_iv.pageBlockDetails) == true) goto L45;
        if ((r1 instanceof TL_iv.pageBlockTable) == true) goto L46;
        if ((r1 instanceof TL_iv.pageBlockMath) == false) goto L35;
        r4 = 7;
        goto L46
    L35:
        if (RichEditorListView.isHeading(r1) == true) goto L44;
        TL_iv.PageBlock r12 = r02.block;
        if ((r12 instanceof TL_iv.pageBlockParagraph) == true) goto L44;
        if ((r12 instanceof TL_iv.pageBlockPreformatted) == true) goto L44;
        if ((r12 instanceof TL_iv.pageBlockBlockquote) == true) goto L44;
        if ((r12 instanceof TL_iv.pageBlockPullquote) == false) goto L18;
    L44:
        r4 = 1;
    L46:
        if (r02 == null) goto L85;
        if (r4 != 1) goto L78;
        TL_iv.PageBlock r03 = r02.block;
        if ((r03 instanceof TL_iv.pageBlockHeading1) == false) goto L52;
        r5 = R.drawable.iv_h1;
        goto L85
    L52:
        if ((r03 instanceof TL_iv.pageBlockHeading2) == false) goto L55;
        r5 = R.drawable.iv_h2;
        goto L85
    L55:
        if ((r03 instanceof TL_iv.pageBlockHeading3) == false) goto L58;
        r5 = R.drawable.iv_h3;
        goto L85
    L58:
        if ((r03 instanceof TL_iv.pageBlockHeading4) == false) goto L61;
        r5 = R.drawable.iv_h4;
        goto L85
    L61:
        if ((r03 instanceof TL_iv.pageBlockHeading5) == false) goto L64;
        r5 = R.drawable.iv_h5;
        goto L85
    L64:
        if ((r03 instanceof TL_iv.pageBlockHeading6) == false) goto L67;
        r5 = R.drawable.iv_h6;
        goto L85
    L67:
        if ((r03 instanceof TL_iv.pageBlockPreformatted) == false) goto L70;
        r5 = R.drawable.iv_code;
        goto L85
    L70:
        if ((r03 instanceof TL_iv.pageBlockBlockquote) == false) goto L73;
        r5 = R.drawable.iv_quote;
        goto L85
    L73:
        if ((r03 instanceof TL_iv.pageBlockPullquote) == false) goto L76;
        r5 = R.drawable.iv_pullquote;
        goto L85
    L76:
        if ((r03 instanceof TL_iv.pageBlockFooter) == false) goto L85;
        r5 = R.drawable.iv_footer;
        goto L85
    L78:
        if (r4 != 2) goto L85;
        if (r02.isChecklist() == false) goto L83;
        r5 = R.drawable.iv_todo;
        goto L85
    L83:
        if (r02.isOrdered() == false) goto L85;
        r5 = R.drawable.iv_ordered_list;
    L85:
        this.toolbar.setSelectedBlockType(r4, r5);
        return;
    L8:
        if (r0.isInSelectionMode() == false) goto L13;
        int r13 = r0.getStartCell();
        if (r13 != r0.getEndCell()) goto L12;
        r02 = this.listView.rowForCell(r13);
        goto L14
    L12:
        r02 = null;
        goto L14
    }

    private void updateToolbarTopOffset() {
        if (this.toolbar != null) goto L5;
        return;
    L5:
        int r0 = (AndroidUtilities.statusBarHeight + ((ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(44.0f)) / 2)) - AndroidUtilities.dp(8.0f);
        this.toolbar.setTopButtonsOffset(Math.max(r0, firstItemTopRaw()));
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.currentUserPremiumStatusChanged) goto L6;
        updateSendButtonLocked();
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean disableBottomFade() {
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent r4) {
        if (r4.getAction() != 0) goto L11;
        if (r4.getKeyCode() != 47) goto L11;
        if (r4.isCtrlPressed() == false) goto L11;
        saveDraftWithBulletin();
        return true;
    L11:
        if (this.listView.handleKeyEvent(r4) == false) goto L14;
        return true;
    L14:
        return super.dispatchKeyEvent(r4);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r6) {
        if (this.listView.textSelectionHelper.isInSelectionMode() == false) goto L8;
        if (this.listView.textSelectionOverlay.onTouchEvent(r6) == false) goto L8;
        return true;
    L8:
        if (this.emojiSearchOpened == false) goto L12;
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L12;
        int r02 = (int) r0.getY();
    L13:
        int r03 = (r02 - AndroidUtilities.dp(60.0f)) - this.attachRaise;
        if (r6.getAction() != 0) goto L21;
        if (this.emojiViewVisible == false) goto L21;
        if (r6.getY() >= r03) goto L21;
        hideEmojiPopup();
    L21:
        if (r6.getAction() != 0) goto L27;
        if (r6.getY() <= AndroidUtilities.dp(60.0f)) goto L30;
        if (r6.getY() < r03) goto L27;
    L30:
        if (r6.getY() >= r03) goto L35;
        if (this.listView.handleSelectionTouch(r6) == false) goto L35;
        return true;
    L35:
        return super.dispatchTouchEvent(r6);
    L27:
        if (this.listView.textSelectionOverlay.checkOnTap(r6) == false) goto L30;
        r6.setAction(3);
    L12:
        r02 = getHeight() - this.emojiPadding;
        goto L13
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        if (this.listView.getChildCount() > 0) goto L6;
        RichEditorListView r0 = this.listView;
        int r2 = r0.getPaddingTop();
        this.currentItemTop = r2;
        r0.setTopGlowOffset(r2);
        return Integer.MAX_VALUE;
    L6:
        int r22 = 0;
        boolean r4 = false;
        int r3 = Integer.MAX_VALUE;
    L8:
        if (r22 >= this.listView.getChildCount()) goto L17;
        View r5 = this.listView.getChildAt(r22);
        int r6 = this.listView.getChildAdapterPosition(r5);
        if (r6 != 0) goto L12;
        r4 = true;
    L12:
        if (r6 < 0) goto L16;
        if (r5.getTop() >= r3) goto L16;
        r3 = r5.getTop();
    L16:
        r22 = r22 + 1;
        goto L8
    L17:
        if (r3 != Integer.MAX_VALUE) goto L19;
        return Integer.MAX_VALUE;
    L19:
        this.listView.setTopGlowOffset(Math.max(0, r3));
        int r32 = r3 - AndroidUtilities.statusBarHeight;
        int r1 = AndroidUtilities.dp(7.0f);
        if (r32 < AndroidUtilities.dp(7.0f)) goto L23;
        if (r4 == false) goto L23;
    L24:
        this.currentItemTop = r32;
        return r32;
    L23:
        r32 = r1;
        goto L24
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(56.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return (this.listView.getPaddingTop() - AndroidUtilities.statusBarHeight) - ActionBar.getCurrentActionBarHeight();
    }

    public TextSelectionHelper.ArticleTextSelectionHelper getTextSelectionHelper() {
        return this.listView.getTextSelectionHelper();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int needsActionBar() {
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        updateSendButtonLocked();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onBackPressed() {
        if (this.emojiSearchOpened == false) goto L7;
        closeEmojiSearch();
        return false;
    L7:
        if (this.emojiViewVisible == false) goto L11;
        hideEmojiPopup();
        return false;
    L11:
        if (this.listView.deselectIfAny() == false) goto L14;
        return false;
    L14:
        if (checkDiscard() == true) goto L18;
        return true;
    L18:
        return super.onBackPressed();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onContainerTranslationUpdated(float r1) {
        super.onContainerTranslationUpdated(r1);
        layoutBottomPanels();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onDestroy() {
        MessageSendPreview r0 = this.messageSendPreview;
        if (r0 == null) goto L5;
        r0.dismissInstant();
        this.messageSendPreview = null;
    L5:
        RichCommandSuggestions r02 = this.commandSuggestions;
        if (r02 == null) goto L8;
        r02.hide();
    L8:
        RichEditorListView r03 = this.listView;
        if (r03 == null) goto L11;
        r03.clearContent();
    L11:
        EmojiView r1 = this.emojiView;
        if (r1 == null) goto L15;
        r1.onDestroy();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onDismiss() {
        RichEditorListView r0 = this.listView;
        if (r0 == null) goto L6;
        r0.clearContent();
    L6:
        return super.onDismiss();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onDismissWithTouchOutside() {
        if (checkDiscard() == true) goto L7;
        return false;
    L7:
        return super.onDismissWithTouchOutside();
    }

    public void onExternalDocumentPicked(Intent r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.getData() == null) goto L9;
        this.listView.attachDocument(r2.getData());
        return;
    }

    public void onExternalMediaPicked(Intent r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.getData() == null) goto L9;
        this.listView.attachExternalMedia(r2.getData());
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHidden() {
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHide() {
        RichCommandSuggestions r0 = this.commandSuggestions;
        if (r0 == null) goto L6;
        r0.hide();
    L6:
        if (this.emojiViewVisible == false) goto L9;
        hideEmojiPopup();
    L9:
        if (this.sendButtonShown == false) goto L12;
        this.sendButtonShown = false;
        this.parentAlert.showSendButtonOnly(false, true);
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPanTransitionEnd() {
        super.onPanTransitionEnd();
        if (this.parentAlert.sizeNotifierFrameLayout.measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L5;
        boolean r0 = true;
    L6:
        this.keyboardVisible = r0;
        layoutBottomPanels();
        updateToolbarTopOffset();
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPanTransitionStart(boolean r1, int r2) {
        super.onPanTransitionStart(r1, r2);
        this.keyboardVisible = r1;
        layoutBottomPanels();
        if (r1 == true) goto L5;
    L9:
        updateToolbarTopOffset();
        return;
    L5:
        if (this.emojiViewVisible == false) goto L9;
        if (this.emojiSearchOpened == true) goto L9;
        hideEmojiPopup();
        goto L9
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r6, int r7) {
        boolean r62 = this.keyboardVisible;
        if (this.parentAlert.sizeNotifierFrameLayout.measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L5;
        boolean r0 = true;
    L6:
        this.keyboardVisible = r0;
        if (r0 == true) goto L13;
        if (r62 == false) goto L13;
        ItemOptions r63 = this.menu;
        if (r63 == null) goto L13;
        r63.dismiss();
        this.menu = null;
    L13:
        if (this.keyboardVisible == false) goto L15;
    L27:
        int r64 = AndroidUtilities.dp(52.0f);
        this.parentAlert.setAllowNestedScroll(false);
    L28:
        int r65 = r64 + (AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight());
        if (this.listView.hasAnyText() == false) goto L31;
    L34:
        int r72 = 0;
    L35:
        int r02 = ((bottomNavInset() + AndroidUtilities.dp(110.0f)) + r72) + this.emojiPadding;
        if (this.listView.getPaddingTop() == r65) goto L38;
    L39:
        this.ignoreLayout = true;
        this.listView.setPaddingWithoutRequestLayout(0, r65, 0, r02);
        this.ignoreLayout = false;
    L40:
        updateToolbarTopOffset();
        return;
    L38:
        if (this.listView.getPaddingBottom() == r02) goto L40;
    L31:
        if (this.emojiViewVisible == true) goto L34;
        r72 = this.parentAlert.getTypeButtonsHeight();
        goto L35
    L15:
        if (this.emojiPadding > AndroidUtilities.dp(20.0f)) goto L27;
        if (AndroidUtilities.isTablet() == true) goto L22;
        Point r66 = AndroidUtilities.displaySize;
        if (r66.x <= r66.y) goto L22;
        int r67 = (int) (r7 / 3.5f);
    L23:
        r64 = r67 - AndroidUtilities.dp(52.0f);
        if (r64 >= 0) goto L26;
        r64 = 0;
    L26:
        this.parentAlert.setAllowNestedScroll(true);
    L22:
        r67 = (r7 / 5) * 2;
        goto L23
    L5:
        r0 = false;
        goto L6
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r2) {
        this.parentAlert.actionBar.setTitle(_UrlKt.FRAGMENT_ENCODE_SET);
        this.listView.adapter.update(false);
        updateAttachButtons(false);
        post(new ChatAttachAlertRichLayout$$ExternalSyntheticLambda12(this));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void scrollToTop() {
        this.listView.smoothScrollToPosition(0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean sendSelectedItems(boolean r25, int r26, int r27, long r28, boolean r30) {
        if (isSendLocked() == false) goto L7;
        showConversionSheet();
        return false;
    L7:
        if (this.listView.hasAnyText() == true) goto L10;
        return false;
    L10:
        if (this.listView.hasPendingUploads() == false) goto L13;
        return false;
    L13:
        if (this.listView.isWithinLimits() == true) goto L17;
        updateSendButtonEnabled();
        return false;
    L17:
        if (MessagesController.getInstance(this.currentAccount).richEditorAllowed() == true) goto L25;
        BaseFragment r1 = this.parentAlert.baseFragment;
        if ((r1 instanceof ChatActivity) == false) goto L24;
        ChatActivityEnterView r12 = ((ChatActivity) r1).getChatActivityEnterView();
        if (r12 == null) goto L24;
        r12.sendConvertedRichAsSimple(this.listView.toSimpleMessage(), r25, r26, r27);
        this.parentAlert.dismiss(true);
        return true;
    L24:
        return false;
    L25:
        ArrayList<TL_iv.PageBlock> r5 = this.listView.flattenRowsToBlocks();
        if (r5.isEmpty() == false) goto L28;
        return false;
    L28:
        ArrayList<TLRPC.Photo> r6 = this.listView.collectPhotos();
        ArrayList<TLRPC.Document> r7 = this.listView.collectDocuments();
        ArrayList<TLRPC.InputUser> r8 = RichMessageButtonUsers.collect(this.currentAccount, r5);
        BaseFragment r13 = this.parentAlert.baseFragment;
        if ((r13 instanceof ChatActivity) == false) goto L32;
        ChatActivity r14 = (ChatActivity) r13;
        MessageObject r2 = r14.getReplyMessage();
        MessageObject r9 = r14.getThreadMessage();
        long r10 = r14.getSendMonoForumPeerId();
        SendMessageChatArguments r17 = r14.getMessageChatSendParams();
        MessageObject r122 = r2;
        MessageObject r132 = r9;
    L33:
        SendMessagesHelper.prepareSendingArticle(AccountInstance.getInstance(this.parentAlert.currentAccount), r5, r6, r7, r8, false, this.parentAlert.getDialogId(), r122, r132, r25, r26, r27, r17, r28, r10, 0);
        this.parentAlert.dismiss(true);
        return true;
    L32:
        r10 = 0;
        r122 = null;
        r132 = null;
        r17 = null;
        goto L33
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        this.parentAlert.getSheetContainer().invalidate();
        invalidate();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean shouldHideBottomButtons() {
        return !this.listView.hasAnyText();
    }
}
