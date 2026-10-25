package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.PollCreateCheckCell;
import org.telegram.ui.Cells.PollEditTextCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ChatActivity$$ExternalSyntheticLambda269;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.ChatActivityEnterViewAnimatedIconView;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ChatAttachAlertDocumentLayout;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.SuggestEmojiView;
import org.telegram.ui.Components.poll.PollAttachedMedia;
import org.telegram.ui.Components.poll.PollAttachedMediaPack;
import org.telegram.ui.Components.poll.WebPageLoader;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaFile;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaGallery;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaLink;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaLocation;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaMusic;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaSticker;
import org.telegram.ui.Components.poll.sheets.CountrySelectBottomSheet;
import org.telegram.ui.ContentPreviewViewer;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stories.recorder.KeyboardNotifier;

/* loaded from: classes7.dex */
public class ChatAttachAlertPollLayout extends ChatAttachAlert.AttachAlertLayout implements SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate, NotificationCenter.NotificationCenterDelegate {
    private final int MAX_CAPTION_LENGTH;
    private final int[] POLL_DURATION_OPTIONS;
    private int addAnswerRow;
    private boolean allowAdding;
    private boolean allowAddingOptions;
    private int allowAddingRow;
    private boolean allowMarking;
    private int allowMarkingRow;
    private boolean allowNesterScroll;
    private boolean allowRevoting;
    private boolean anonymousPoll;
    private int answerHeaderRow;
    private int answerSectionRow;
    private int answerStartRow;
    private final CharSequence[] answers;
    private final boolean[] answersChecks;
    private int answersCount;
    private final PollAttachedMediaPack attachedMedia;
    private final Paint checkboxPaint;
    private ArrayList<String> countriesList;
    private ChatAttachAlert currentAttachAlert;
    private int currentAttachAlertIndex;
    private PollEditTextCell currentCell;
    private PollCreateActivityDelegate delegate;
    private int descriptionRow;
    private CharSequence descriptionString;
    private boolean destroyed;
    private boolean doneItemEnabled;
    private int emojiPadding;
    public EmojiView emojiView;
    public boolean emojiViewVisible;
    public boolean emojiViewWasVisible;
    private int emptyRow;
    private boolean hideResults;
    private boolean hintShowed;
    private HintView hintView;
    private boolean ignoreLayout;
    private boolean isAnimatePopupClosing;
    public boolean isEmojiSearchOpened;
    private final boolean isPremium;
    private final DefaultItemAnimator itemAnimator;
    private int keyboardHeight;
    private int keyboardHeightLand;
    private final KeyboardNotifier keyboardNotifier;
    private boolean keyboardVisible;
    private int lastSizeChangeValue1;
    private boolean lastSizeChangeValue2;
    private final FillLastLinearLayoutManager layoutManager;
    private final ListAdapter listAdapter;
    private final RecyclerListView listView;
    private final int maxAnswersCount;
    private boolean multipleChoise;
    private final Runnable openKeyboardRunnable;
    private int paddingRow;
    private int poll2vAllowAddingRow;
    private int poll2vAllowRevotingRow;
    private int poll2vAnonymousRow;
    private int poll2vLimitByCountryListRow;
    private final ToggleRow poll2vLimitByCountryRow;
    private int poll2vLimitDurationHideResultsRow;
    private int poll2vLimitDurationHideResultsRowInfo;
    private int poll2vLimitDurationRow;
    private int poll2vLimitDurationTimeRow;
    private int poll2vMultipleRow;
    private int poll2vQuizRow;
    private int poll2vShuffleRow;
    private final ToggleRow poll2vSubscribersOnlyRow;
    private int pollLimitDeadline;
    private int pollLimitDuration;
    private int questionHeaderRow;
    private int questionRow;
    private int questionSectionRow;
    private CharSequence questionString;
    private boolean quizOnly;
    private boolean quizPoll;
    private int requestFieldFocusAtPosition;
    private int rowCount;
    private int settingsHeaderRow;
    private int settingsSectionRow;
    private int showMediaHintIndexAfterSmoothScroll;
    private boolean shuffleOptions;
    private boolean smoothScrollToOption;
    private int solutionInfoRow;
    private int solutionRow;
    private int solutionRowHeader;
    private CharSequence solutionString;
    private SuggestEmojiView suggestEmojiPanel;
    private final boolean todo;
    private final ToggleRow[] toggleRows;
    private int topPadding;
    private boolean waitingForKeyboardOpen;
    public boolean wasEmojiSearchOpened;
    private final WebPageLoader webPageLoader;

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        public AnonymousClass1(ChatAttachAlertPollLayout r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ChatAttachAlertPollLayout.m9857$$Nest$fgetcurrentCell(this.this$0) == null) goto L19;
            EditTextBoldCursor r0 = ChatAttachAlertPollLayout.m9857$$Nest$fgetcurrentCell(this.this$0).getEditField();
            if (ChatAttachAlertPollLayout.m9860$$Nest$fgetdestroyed(this.this$0) == true) goto L20;
            if (r0 != null) goto L8;
            return;
        L8:
            if (ChatAttachAlertPollLayout.m9909$$Nest$fgetwaitingForKeyboardOpen(this.this$0) == true) goto L10;
            return;
        L10:
            if (ChatAttachAlertPollLayout.m9866$$Nest$fgetkeyboardVisible(this.this$0) == false) goto L12;
            return;
        L12:
            if (AndroidUtilities.usingHardwareInput == false) goto L14;
            return;
        L14:
            if (AndroidUtilities.isInMultiwindow == false) goto L16;
            return;
        L16:
            if (AndroidUtilities.isTablet() == false) goto L26;
            r0.requestFocus();
            AndroidUtilities.showKeyboard(r0);
            AndroidUtilities.cancelRunOnUIThread(ChatAttachAlertPollLayout.m9872$$Nest$fgetopenKeyboardRunnable(this.this$0));
            AndroidUtilities.runOnUIThread(ChatAttachAlertPollLayout.m9872$$Nest$fgetopenKeyboardRunnable(this.this$0), 100);
            return;
        L26:
            return;
        L20:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$10, reason: invalid class name */
    public class AnonymousClass10 implements EmojiView.EmojiViewDelegate {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        public static /* synthetic */ void $r8$lambda$BibU_RD139zQQnPglEUIalqCm1I(AnonymousClass10 r0, AlertDialog r1, int r2) {
            r0.lambda$onClearEmojiRecent$0(r1, r2);
        }

        public AnonymousClass10(ChatAttachAlertPollLayout r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onClearEmojiRecent$0(AlertDialog r1, int r2) {
            this.this$0.emojiView.clearRecentEmoji();
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean isSearchOpened() {
            return this.this$0.isEmojiSearchOpened;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean onBackspace() {
            if (ChatAttachAlertPollLayout.m9857$$Nest$fgetcurrentCell(this.this$0) != null) goto L5;
            return false;
        L5:
            EditTextBoldCursor r3 = ChatAttachAlertPollLayout.m9857$$Nest$fgetcurrentCell(this.this$0).getEditField();
            if (r3 != null) goto L8;
            return false;
        L8:
            r3.dispatchKeyEvent(new KeyEvent(0, 67));
            return true;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onClearEmojiRecent() {
            AlertDialog.Builder r0 = new AlertDialog.Builder(this.this$0.getContext(), this.this$0.resourcesProvider);
            r0.setTitle(LocaleController.getString(R.string.ClearRecentEmojiTitle));
            r0.setMessage(LocaleController.getString(R.string.ClearRecentEmojiText));
            r0.setPositiveButton(LocaleController.getString(R.string.ClearButton), new ChatAttachAlertPollLayout$10$$ExternalSyntheticLambda0(this));
            r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            r0.show();
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onCustomEmojiSelected(long r3, TLRPC.Document r5, String r6, boolean r7) {
            if (ChatAttachAlertPollLayout.m9857$$Nest$fgetcurrentCell(this.this$0) == null) goto L22;
            EditTextBoldCursor r2 = ChatAttachAlertPollLayout.m9857$$Nest$fgetcurrentCell(this.this$0).getEditField();
            if (r2 != null) goto L8;
            return;
        L8:
            int r72 = r2.getSelectionEnd();
            if (r72 >= 0) goto L20;
            r72 = 0;
        L20:
            SpannableString r1 = new SpannableString(r6);     // Catch: Exception -> L17
            if (r5 == null) goto L14;
            AnimatedEmojiSpan r32 = new AnimatedEmojiSpan(r5, r2.getPaint().getFontMetricsInt());     // Catch: Exception -> L17
        L15:
            r32.cacheType = 3;     // Catch: Exception -> L17
            r1.setSpan(r32, 0, r1.length(), 33);     // Catch: Exception -> L17
            r2.setText(r2.getText().insert(r72, r1));     // Catch: Exception -> L17
            int r73 = r72 + r1.length();     // Catch: Exception -> L17
            r2.setSelection(r73, r73);     // Catch: Exception -> L17
            return;
        L14:
            r32 = new AnimatedEmojiSpan(r3, r2.getPaint().getFontMetricsInt());     // Catch: Exception -> L17
        L17:
            e = move-exception;
            FileLog.e(e);
            return;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onEmojiSelected(String r4) {
            if (ChatAttachAlertPollLayout.m9857$$Nest$fgetcurrentCell(this.this$0) == null) goto L18;
            EditTextBoldCursor r3 = ChatAttachAlertPollLayout.m9857$$Nest$fgetcurrentCell(this.this$0).getEditField();
            if (r3 != null) goto L8;
            return;
        L8:
            int r0 = r3.getSelectionEnd();
            if (r0 >= 0) goto L16;
            r0 = 0;
        L16:
            CharSequence r42 = Emoji.replaceEmoji(r4, r3.getPaint().getFontMetricsInt(), false);     // Catch: Exception -> L13
            r3.setText(r3.getText().insert(r0, r42));     // Catch: Exception -> L13
            int r02 = r0 + r42.length();     // Catch: Exception -> L13
            r3.setSelection(r02, r02);     // Catch: Exception -> L13
            return;
        L13:
            e = move-exception;
            FileLog.e(e);
            return;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onSearchOpenClose(int r1) {
            ChatAttachAlertPollLayout r0 = this.this$0;
            if (r1 == 0) goto L5;
            boolean r12 = true;
        L6:
            r0.isEmojiSearchOpened = r12;
            r0.parentAlert.sizeNotifierFrameLayout.requestLayout();
            return;
        L5:
            r12 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$11, reason: invalid class name */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;
        final /* synthetic */ float val$toY;

        public AnonymousClass11(ChatAttachAlertPollLayout r1, float r2) {
            this.this$0 = r1;
            this.val$toY = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.emojiView.setTranslationY(this.val$toY);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$12, reason: invalid class name */
    public class AnonymousClass12 extends PhotoViewer.EmptyPhotoViewerProvider {
        private boolean openReplace;
        final /* synthetic */ ChatAttachAlertPollLayout this$0;
        final /* synthetic */ int val$index;

        public AnonymousClass12(ChatAttachAlertPollLayout r1, int r2) {
            this.this$0 = r1;
            this.val$index = r2;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean allowCaption() {
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void onClose() {
            super.onClose();
            if (this.openReplace == false) goto L6;
            ChatAttachAlertPollLayout.m9927$$Nest$mopenAttachMenuForOptions(this.this$0, this.val$index);
            return;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void onPollAttachDelete() {
            ChatAttachAlertPollLayout.m9929$$Nest$msetAttachedMedia(this.this$0, this.val$index, null);
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void onPollAttachReplace() {
            this.openReplace = true;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$13, reason: invalid class name */
    public class AnonymousClass13 implements ContentPreviewViewer.ContentPreviewViewerDelegate {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;
        final /* synthetic */ int val$index;

        /* renamed from: $r8$lambda$C1LH0yEG6KFFb6U4G2p1CMh7E-4, reason: not valid java name */
        public static /* synthetic */ void m9933$r8$lambda$C1LH0yEG6KFFb6U4G2p1CMh7E4(AnonymousClass13 r0, int r1) {
            r0.lambda$getCustomItemOptions$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$Tm0FjPJkxk68nVPaGozBO09OqRA(AnonymousClass13 r0, int r1) {
            r0.lambda$getCustomItemOptions$1(r1);
        }

        public AnonymousClass13(ChatAttachAlertPollLayout r1, int r2) {
            this.this$0 = r1;
            this.val$index = r2;
        }

        private /* synthetic */ void lambda$getCustomItemOptions$0(int r1) {
            ChatAttachAlertPollLayout.m9927$$Nest$mopenAttachMenuForOptions(this.this$0, r1);
        }

        private /* synthetic */ void lambda$getCustomItemOptions$1(int r2) {
            ChatAttachAlertPollLayout.m9929$$Nest$msetAttachedMedia(this.this$0, r2, null);
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public ItemOptions getCustomItemOptions(ViewGroup r4, View r5) {
            return ItemOptions.makeOptions(r4, new View(this.this$0.getContext())).setDimAlpha(0).setDrawScrim(false).add(R.drawable.msg_replace, LocaleController.getString(R.string.ReplaceAttachedPollMedia), new ChatAttachAlertPollLayout$13$$ExternalSyntheticLambda0(this, this.val$index)).add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new ChatAttachAlertPollLayout$13$$ExternalSyntheticLambda1(this, this.val$index));
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public long getDialogId() {
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$14, reason: invalid class name */
    public class AnonymousClass14 extends ChatAttachAlert {
        final /* synthetic */ Runnable val$onDismiss;

        public AnonymousClass14(Context r1, BaseFragment r2, boolean r3, boolean r4, boolean r5, Theme.ResourcesProvider r6, Runnable r7) {
            this.val$onDismiss = r7;
            super(r1, r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert, org.telegram.ui.ActionBar.BottomSheet
        public void dismissInternal() {
            super.dismissInternal();
            Runnable r0 = this.val$onDismiss;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$15, reason: invalid class name */
    public class AnonymousClass15 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ Utilities.Callback val$callback;
        final /* synthetic */ ChatAttachAlert val$chatAttachAlert;
        final /* synthetic */ BaseFragment val$fragment;

        public static /* synthetic */ void $r8$lambda$sLVSbAIvOLv47zv07mQOu3f1YTI(Utilities.Callback r1, String r2) {
            r1.run(new PollAttachedMediaLink(r2));
        }

        public AnonymousClass15(BaseFragment r1, Utilities.Callback r2, ChatAttachAlert r3) {
            this.val$fragment = r1;
            this.val$callback = r2;
            this.val$chatAttachAlert = r3;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
            if (r1 != 15) goto L6;
            AlertsCreator.showAddLinkToPoll(this.val$fragment.getContext(), this.val$fragment.getResourceProvider(), null, null, new ChatAttachAlertPollLayout$15$$ExternalSyntheticLambda0(this.val$callback), null);
        L37:
            this.val$chatAttachAlert.dismiss(true);
            return;
        L6:
            if (r1 != 7) goto L8;
        L9:
            HashMap<Object, Object> r12 = this.val$chatAttachAlert.getPhotoLayout().getSelectedPhotos();
            ArrayList<Object> r22 = this.val$chatAttachAlert.getPhotoLayout().getSelectedPhotosOrder();
            if (r22.size() <= 0) goto L37;
            Object r13 = r12.get(r22.get(0));
            SendMessagesHelper.SendingMediaInfo r23 = new SendMessagesHelper.SendingMediaInfo();
            String r52 = null;
            if ((r13 instanceof MediaController.PhotoEntry) == false) goto L22;
            MediaController.PhotoEntry r14 = (MediaController.PhotoEntry) r13;
            String r42 = r14.imagePath;
            if (r42 == null) goto L16;
            r23.path = r42;
        L17:
            r23.thumbPath = r14.thumbPath;
            r23.coverPath = r14.coverPath;
            r23.videoEditedInfo = r14.editedInfo;
            r23.isLivePhoto = r14.isLivePhoto();
            r23.livePhotoVideoOffset = r14.livePhotoVideoOffset;
            r23.discardLivePhoto = true;
            r23.isVideo = r14.isVideo;
            CharSequence r43 = r14.caption;
            if (r43 == null) goto L20;
            r52 = r43.toString();
        L20:
            r23.caption = r52;
            r23.entities = r14.entities;
            r23.masks = r14.stickers;
            r23.ttl = r14.ttl;
            r23.emojiMarkup = r14.emojiMarkup;
            r23.originalPhotoEntry = r14;
        L36:
            this.val$callback.run(new PollAttachedMediaGallery(r23));
            goto L37
        L16:
            r23.path = r14.path;
            goto L17
        L22:
            if ((r13 instanceof MediaController.SearchImage) == false) goto L36;
            MediaController.SearchImage r15 = (MediaController.SearchImage) r13;
            String r44 = r15.imagePath;
            if (r44 == null) goto L26;
            r23.path = r44;
        L27:
            r23.thumbPath = r15.thumbPath;
            r23.coverPath = r15.coverPath;
            r23.videoEditedInfo = r15.editedInfo;
            CharSequence r45 = r15.caption;
            if (r45 == null) goto L30;
            r52 = r45.toString();
        L30:
            r23.caption = r52;
            r23.entities = r15.entities;
            r23.masks = r15.stickers;
            r23.ttl = r15.ttl;
            TLRPC.BotInlineResult r46 = r15.inlineResult;
            if (r46 != null) goto L33;
        L35:
            r15.date = (int) (System.currentTimeMillis() / 1000);
            goto L36
        L33:
            if (r15.type != 1) goto L35;
            r23.inlineResult = r46;
            r23.params = r15.params;
            goto L35
        L26:
            r23.searchImage = r15;
            goto L27
        L8:
            if (r1 != 8) goto L37;
            goto L9
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didSelectBot(TLRPC.User r1) {
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void doOnIdle(Runnable r1) {
            NotificationCenter.getInstance(this.val$fragment.getCurrentAccount()).doOnIdle(r1);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public boolean needEnterComment() {
            return false;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void onCameraOpened() {
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$16, reason: invalid class name */
    public class AnonymousClass16 implements EmojiView.EmojiViewDelegate {
        final /* synthetic */ Utilities.Callback val$callback;
        final /* synthetic */ ChatAttachAlert val$chatAttachAlert;

        public AnonymousClass16(Utilities.Callback r1, ChatAttachAlert r2) {
            this.val$callback = r1;
            this.val$chatAttachAlert = r2;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onCustomEmojiSelected(long r1, TLRPC.Document r3, String r4, boolean r5) {
            this.val$callback.run(new PollAttachedMediaSticker(r3, null));
            this.val$chatAttachAlert.dismiss(true);
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onStickerSelected(View r1, TLRPC.Document r2, String r3, Object r4, MessageObject.SendAnimationData r5, boolean r6, int r7, int r8) {
            this.val$callback.run(new PollAttachedMediaSticker(r2, r4));
            this.val$chatAttachAlert.dismiss(true);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$17, reason: invalid class name */
    public class AnonymousClass17 implements ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate {
        final /* synthetic */ Utilities.Callback val$callback;
        final /* synthetic */ ChatAttachAlert val$chatAttachAlert;
        final /* synthetic */ BaseFragment val$fragment;

        public AnonymousClass17(Utilities.Callback r1, ChatAttachAlert r2, BaseFragment r3) {
            this.val$callback = r1;
            this.val$chatAttachAlert = r2;
            this.val$fragment = r3;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void didSelectFiles(ArrayList<String> r1, String r2, ArrayList<TLRPC.MessageEntity> r3, ArrayList<MessageObject> r4, boolean r5, int r6, int r7, long r8, boolean r10, long r11) {
            if (r1 != null) goto L4;
        L6:
            this.val$chatAttachAlert.dismiss(true);
            return;
        L4:
            if (r1.isEmpty() == true) goto L6;
            this.val$callback.run(new PollAttachedMediaFile(r1.get(0)));
            goto L6
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void didSelectPhotos(ArrayList<SendMessagesHelper.SendingMediaInfo> r1, boolean r2, int r3, int r4, long r5) {
            if (r1 != null) goto L4;
        L6:
            this.val$chatAttachAlert.dismiss(true);
            return;
        L4:
            if (r1.isEmpty() == true) goto L6;
            this.val$callback.run(new PollAttachedMediaGallery(r1.get(0)));
            goto L6
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void startDocumentSelectActivity() {
            Intent r0 = new Intent("android.intent.action.GET_CONTENT");     // Catch: Exception -> L4
            r0.setType("*/*");     // Catch: Exception -> L4
            this.val$fragment.getParentActivity().startActivityForResult(r0, 28);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        public AnonymousClass2(ChatAttachAlertPollLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void requestChildOnScreen(View r2, View r3) {
            if ((r2 instanceof PollEditTextCell) == true) goto L5;
            return;
        L5:
            super.requestChildOnScreen(r2, r3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.ViewParent
        public boolean requestChildRectangleOnScreen(View r3, Rect r4, boolean r5) {
            r4.bottom += AndroidUtilities.dp(60.0f);
            return super.requestChildRectangleOnScreen(r3, r4, r5);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends DefaultItemAnimator {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        public AnonymousClass3(ChatAttachAlertPollLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r3) {
            if (r3.getAdapterPosition() != 0) goto L6;
            ChatAttachAlertPollLayout r2 = this.this$0;
            r2.parentAlert.updateLayout(r2, true, 0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends FillLastLinearLayoutManager {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$4$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearSmoothScroller {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDyToMakeVisible(View r3, int r4) {
                if (ChatAttachAlertPollLayout.m9901$$Nest$fgetsmoothScrollToOption(this.this$1.this$0) == false) goto L5;
                r4 = -1;
            L5:
                int r32 = super.calculateDyToMakeVisible(r3, r4);
                if (ChatAttachAlertPollLayout.m9901$$Nest$fgetsmoothScrollToOption(this.this$1.this$0) == false) goto L9;
                r32 = r32 + AndroidUtilities.dp(160.0f);
            L9:
                if (ChatAttachAlertPollLayout.m9901$$Nest$fgetsmoothScrollToOption(this.this$1.this$0) == true) goto L12;
                r32 = r32 - ((ChatAttachAlertPollLayout.m9908$$Nest$fgettopPadding(this.this$1.this$0) - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(7.0f));
            L12:
                if (ChatAttachAlertPollLayout.m9901$$Nest$fgetsmoothScrollToOption(this.this$1.this$0) == false) goto L17;
                if (r32 != 0) goto L17;
                if (ChatAttachAlertPollLayout.m9899$$Nest$fgetshowMediaHintIndexAfterSmoothScroll(this.this$1.this$0) < 0) goto L17;
                ChatAttachAlertPollLayout r42 = this.this$1.this$0;
                ChatAttachAlertPollLayout.m9931$$Nest$mshowMediaHint(r42, ChatAttachAlertPollLayout.m9899$$Nest$fgetshowMediaHintIndexAfterSmoothScroll(r42));
                ChatAttachAlertPollLayout.m9916$$Nest$fputshowMediaHintIndexAfterSmoothScroll(this.this$1.this$0, -1);
            L17:
                ChatAttachAlertPollLayout.m9917$$Nest$fputsmoothScrollToOption(this.this$1.this$0, false);
                return r32;
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateTimeForDeceleration(int r1) {
                return super.calculateTimeForDeceleration(r1) * 2;
            }
        }

        public AnonymousClass4(ChatAttachAlertPollLayout r1, Context r2, int r3, boolean r4, int r5, RecyclerView r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int[] getChildRectangleOnScreenScrollAmount(View r3, Rect r4) {
            int r0 = getHeight() - getPaddingBottom();
            int r2 = (r3.getTop() + r4.top) - r3.getScrollY();
            int r32 = r4.height() + r2;
            int r1 = Math.min(0, r2);
            int r33 = Math.max(0, r32 - r0);
            if (r1 != 0) goto L7;
            r1 = Math.min(r2, r33);
        L7:
            return new int[]{0, r1};
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
            AnonymousClass1 r22 = new AnonymousClass1(this, r1.getContext());
            r22.setTargetPosition(r3);
            startSmoothScroll(r22);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$5, reason: invalid class name */
    public class AnonymousClass5 implements CountrySelectBottomSheet.Listener {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        public AnonymousClass5(ChatAttachAlertPollLayout r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.poll.sheets.CountrySelectBottomSheet.Listener
        public void onCountrySelected(List<String> r2) {
            ChatAttachAlertPollLayout.m9856$$Nest$fgetcountriesList(this.this$0).clear();
            ChatAttachAlertPollLayout.m9856$$Nest$fgetcountriesList(this.this$0).addAll(r2);
            if (ChatAttachAlertPollLayout.m9877$$Nest$fgetpoll2vLimitByCountryListRow(this.this$0) < 0) goto L6;
            ChatAttachAlertPollLayout.m9868$$Nest$fgetlistAdapter(this.this$0).notifyItemChanged(ChatAttachAlertPollLayout.m9877$$Nest$fgetpoll2vLimitByCountryListRow(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        public AnonymousClass6(ChatAttachAlertPollLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r3, int r4) {
            if (r4 != 0) goto L14;
            int r32 = AndroidUtilities.dp(13.0f);
            int r42 = this.this$0.parentAlert.getBackgroundPaddingTop();
            if ((((this.this$0.parentAlert.scrollOffsetY[0] - r42) - r32) + r42) >= ActionBar.getCurrentActionBarHeight()) goto L11;
            RecyclerListView.Holder r33 = (RecyclerListView.Holder) ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$0).findViewHolderForAdapterPosition(1);
            if (r33 == null) goto L11;
            int r34 = (r33.itemView.getTop() - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(65.0f);
            if (r34 <= 0) goto L11;
            ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$0).smoothScrollBy(0, r34);
        L11:
            if (ChatAttachAlertPollLayout.m9899$$Nest$fgetshowMediaHintIndexAfterSmoothScroll(this.this$0) < 0) goto L15;
            ChatAttachAlertPollLayout r35 = this.this$0;
            ChatAttachAlertPollLayout.m9931$$Nest$mshowMediaHint(r35, ChatAttachAlertPollLayout.m9899$$Nest$fgetshowMediaHintIndexAfterSmoothScroll(r35));
            ChatAttachAlertPollLayout.m9916$$Nest$fputshowMediaHintIndexAfterSmoothScroll(this.this$0, -1);
            return;
        L15:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r4, int r5, int r6) {
            ChatAttachAlertPollLayout r42 = this.this$0;
            r42.parentAlert.updateLayout(r42, true, r6);
            if (ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$0) != null) goto L5;
        L21:
            if (r6 != 0) goto L23;
            return;
        L23:
            if (ChatAttachAlertPollLayout.m9863$$Nest$fgethintView(this.this$0) == null) goto L27;
            ChatAttachAlertPollLayout.m9863$$Nest$fgethintView(this.this$0).hide();
            return;
        L27:
            return;
        L5:
            if (ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$0).isShown() == false) goto L21;
            SuggestEmojiView.AnchorViewDelegate r43 = ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$0).getDelegate();
            boolean r52 = r43 instanceof PollEditTextCell;
            ChatAttachAlertPollLayout r0 = this.this$0;
            if (r52 == false) goto L20;
            RecyclerView.ViewHolder r44 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(r0).findContainingViewHolder((PollEditTextCell) r43);
            if (r44 == null) goto L19;
            int r53 = r44.getAdapterPosition();
            int r02 = ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$0).getDirection();
            ChatAttachAlertPollLayout r1 = this.this$0;
            if (r02 != 0) goto L13;
            ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(r1).setTranslationY((r44.itemView.getY() - AndroidUtilities.dp(166.0f)) + r44.itemView.getMeasuredHeight());
        L15:
            if (r53 >= ChatAttachAlertPollLayout.m9867$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition()) goto L17;
        L18:
            ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$0).forceClose();
            goto L21
        L17:
            if (r53 <= ChatAttachAlertPollLayout.m9867$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition()) goto L21;
        L13:
            ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(r1).setTranslationY(r44.itemView.getY());
            goto L15
        L19:
            ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$0).forceClose();
            goto L21
        L20:
            ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(r0).forceClose();
            goto L21
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$7, reason: invalid class name */
    public class AnonymousClass7 extends SuggestEmojiView {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        public AnonymousClass7(ChatAttachAlertPollLayout r1, Context r2, int r3, SuggestEmojiView.AnchorViewDelegate r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.SuggestEmojiView
        public int emojiCacheType() {
            return 3;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        public AnonymousClass8(ChatAttachAlertPollLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.emojiView.setTranslationY(0.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        public AnonymousClass9(ChatAttachAlertPollLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ChatAttachAlertPollLayout.m9913$$Nest$fputisAnimatePopupClosing(this.this$0, false);
            this.this$0.emojiView.setTranslationY(0.0f);
            this.this$0.hideEmojiView();
        }
    }

    public static class EmptyView extends View {
        public EmptyView(Context r1) {
            super(r1);
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private final Context mContext;
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends PollEditTextCell {
            final /* synthetic */ ListAdapter this$1;
            final /* synthetic */ int val$viewType;

            public AnonymousClass1(ListAdapter r1, Context r2, boolean r3, int r4, View.OnClickListener r5, Theme.ResourcesProvider r6, int r7) {
                this.this$1 = r1;
                this.val$viewType = r7;
                super(r2, r3, r4, r5, r6);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onActionModeStart(EditTextBoldCursor r3, ActionMode r4) {
                if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$1.this$0) == false) goto L5;
            L15:
                super.onActionModeStart(r3, r4);
                return;
            L5:
                if (this.val$viewType != 11) goto L15;
                if (r3.isFocused() == true) goto L9;
                return;
            L9:
                if (r3.hasSelection() == false) goto L18;
                Menu r32 = r4.getMenu();
                if (r32.findItem(android.R.id.copy) == null) goto L19;
                ChatActivity.fillActionModeMenu(r32, ((ChatActivity) this.this$1.this$0.parentAlert.baseFragment).getCurrentEncryptedChat(), false, true);
                return;
            L19:
                return;
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEditTextFocusChanged(boolean r2) {
                ChatAttachAlertPollLayout.m9925$$Nest$monCellFocusChanges(this.this$1.this$0, this, r2);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEmojiButtonClicked(PollEditTextCell r1) {
                ChatAttachAlertPollLayout.m9926$$Nest$monEmojiClicked(this.this$1.this$0, r1);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onFieldTouchUp(EditTextBoldCursor r2) {
                this.this$1.this$0.parentAlert.makeFocusable(r2, true);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public boolean onPastedMultipleLines(ArrayList<CharSequence> r8) {
                if (r8.isEmpty() == false) goto L5;
                return false;
            L5:
                this.textView.getText().replace(this.textView.getSelectionStart(), this.textView.getSelectionEnd(), r8.remove(0));
                int r0 = 0;
            L7:
                if (r8.isEmpty() == true) goto L15;
                if (r0 >= ChatAttachAlertPollLayout.m9870$$Nest$fgetmaxAnswersCount(this.this$1.this$0)) goto L15;
                int r2 = ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$1.this$0).length - 1;
            L11:
                ListAdapter r4 = this.this$1;
                if (r2 <= r0) goto L14;
                ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(r4.this$0)[r2] = ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$1.this$0)[r2 - 1];
                r2 = r2 - 1;
                goto L11
            L14:
                ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(r4.this$0)[r0] = r8.remove(0);
                ChatAttachAlertPollLayout r22 = this.this$1.this$0;
                ChatAttachAlertPollLayout.m9910$$Nest$fputanswersCount(r22, ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(r22) + 1);
                r0 = r0 + 1;
            L15:
                ChatAttachAlertPollLayout.m9932$$Nest$mupdateRows(this.this$1.this$0);
                ChatAttachAlertPollLayout r82 = this.this$1.this$0;
                ChatAttachAlertPollLayout.m9915$$Nest$fputrequestFieldFocusAtPosition(r82, (ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(r82) + r0) - 1);
                ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$1.this$0).setItemAnimator(ChatAttachAlertPollLayout.m9865$$Nest$fgetitemAnimator(this.this$1.this$0));
                ChatAttachAlertPollLayout.m9868$$Nest$fgetlistAdapter(this.this$1.this$0).notifyDataSetChanged();
                return true;
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$ListAdapter$2, reason: invalid class name */
        public class AnonymousClass2 implements TextWatcher {
            final /* synthetic */ ListAdapter this$1;
            final /* synthetic */ PollEditTextCell val$cell;
            final /* synthetic */ int val$viewType;

            public AnonymousClass2(ListAdapter r1, PollEditTextCell r2, int r3) {
                this.this$1 = r1;
                this.val$cell = r2;
                this.val$viewType = r3;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r9) {
                if (this.val$cell.getTag() == null) goto L5;
                return;
            L5:
                int r0 = this.val$viewType;
                ListAdapter r1 = this.this$1;
                if (r0 != 11) goto L8;
                int r02 = ChatAttachAlertPollLayout.m9858$$Nest$fgetdescriptionRow(r1.this$0);
            L9:
                RecyclerView.ViewHolder r12 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$1.this$0).findViewHolderForAdapterPosition(r02);
                if (r12 != null) goto L12;
            L17:
                int r3 = this.val$viewType;
                ListAdapter r4 = this.this$1;
                if (r3 != 11) goto L20;
                ChatAttachAlertPollLayout.m9912$$Nest$fputdescriptionString(r4.this$0, r9);
            L21:
                if (r12 == null) goto L23;
                ChatAttachAlertPollLayout.m9930$$Nest$msetTextLeft(this.this$1.this$0, r12.itemView, r02);
            L23:
                ChatAttachAlertPollLayout.m9921$$Nest$mcheckDoneButton(this.this$1.this$0);
                return;
            L20:
                ChatAttachAlertPollLayout.m9914$$Nest$fputquestionString(r4.this$0, r9);
                goto L21
            L12:
                if (ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0) == null) goto L17;
                ImageSpan[] r32 = (ImageSpan[]) r9.getSpans(0, r9.length(), ImageSpan.class);
                int r42 = r32.length;
                int r6 = 0;
            L14:
                if (r6 >= r42) goto L16;
                r9.removeSpan(r32[r6]);
                r6 = r6 + 1;
                goto L14
            L16:
                Emoji.replaceEmoji(r9, this.val$cell.getEditField().getPaint().getFontMetricsInt(), false);
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setDirection(1);
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setDelegate(this.val$cell);
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setTranslationY(r12.itemView.getY());
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).fireUpdate();
                goto L17
            L8:
                r02 = ChatAttachAlertPollLayout.m9890$$Nest$fgetquestionRow(r1.this$0);
                goto L9
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$ListAdapter$3, reason: invalid class name */
        public class AnonymousClass3 extends PollEditTextCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass3(ListAdapter r1, Context r2, boolean r3, int r4, View.OnClickListener r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onActionModeStart(EditTextBoldCursor r2, ActionMode r3) {
                if (r2.isFocused() == true) goto L5;
                return;
            L5:
                if (r2.hasSelection() == false) goto L12;
                Menu r22 = r3.getMenu();
                if (r22.findItem(android.R.id.copy) == null) goto L13;
                ChatActivity.fillActionModeMenu(r22, ((ChatActivity) this.this$1.this$0.parentAlert.baseFragment).getCurrentEncryptedChat(), false, true);
                return;
            L13:
                return;
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEditTextFocusChanged(boolean r2) {
                ChatAttachAlertPollLayout.m9925$$Nest$monCellFocusChanges(this.this$1.this$0, this, r2);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEmojiButtonClicked(PollEditTextCell r1) {
                ChatAttachAlertPollLayout.m9926$$Nest$monEmojiClicked(this.this$1.this$0, r1);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onFieldTouchUp(EditTextBoldCursor r2) {
                this.this$1.this$0.parentAlert.makeFocusable(r2, true);
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$ListAdapter$4, reason: invalid class name */
        public class AnonymousClass4 implements TextWatcher {
            final /* synthetic */ ListAdapter this$1;
            final /* synthetic */ PollEditTextCell val$cell;

            public AnonymousClass4(ListAdapter r1, PollEditTextCell r2) {
                this.this$1 = r1;
                this.val$cell = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r7) {
                if (this.val$cell.getTag() == null) goto L5;
                return;
            L5:
                RecyclerView.ViewHolder r0 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$1.this$0).findViewHolderForAdapterPosition(ChatAttachAlertPollLayout.m9903$$Nest$fgetsolutionRow(this.this$1.this$0));
                if (r0 != null) goto L8;
            L13:
                ChatAttachAlertPollLayout.m9918$$Nest$fputsolutionString(this.this$1.this$0, r7);
                if (r0 == null) goto L16;
                ChatAttachAlertPollLayout r72 = this.this$1.this$0;
                ChatAttachAlertPollLayout.m9930$$Nest$msetTextLeft(r72, r0.itemView, ChatAttachAlertPollLayout.m9903$$Nest$fgetsolutionRow(r72));
            L16:
                ChatAttachAlertPollLayout.m9921$$Nest$mcheckDoneButton(this.this$1.this$0);
                return;
            L8:
                if (ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0) == null) goto L13;
                ImageSpan[] r1 = (ImageSpan[]) r7.getSpans(0, r7.length(), ImageSpan.class);
                int r2 = r1.length;
                int r4 = 0;
            L10:
                if (r4 >= r2) goto L12;
                r7.removeSpan(r1[r4]);
                r4 = r4 + 1;
                goto L10
            L12:
                Emoji.replaceEmoji(r7, this.val$cell.getEditField().getPaint().getFontMetricsInt(), false);
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setDirection(1);
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setDelegate(this.val$cell);
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setTranslationY(r0.itemView.getY());
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).fireUpdate();
                goto L13
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$ListAdapter$5, reason: invalid class name */
        public class AnonymousClass5 extends View {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass5(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r1, int r2) {
                setMeasuredDimension(View.MeasureSpec.getSize(r1), ChatAttachAlertPollLayout.m9908$$Nest$fgettopPadding(this.this$1.this$0));
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$ListAdapter$6, reason: invalid class name */
        public class AnonymousClass6 extends PollEditTextCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass6(ListAdapter r1, Context r2, boolean r3, int r4, View.OnClickListener r5, Theme.ResourcesProvider r6) {
                this.this$1 = r1;
                super(r2, r3, r4, r5, r6);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public boolean drawDivider() {
                RecyclerView.ViewHolder r0 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$1.this$0).findContainingViewHolder(this);
                if (r0 == null) goto L10;
                int r02 = r0.getAdapterPosition();
                if (ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(this.this$1.this$0) != ChatAttachAlertPollLayout.m9870$$Nest$fgetmaxAnswersCount(this.this$1.this$0)) goto L10;
                if (r02 != ((ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$1.this$0) + ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(this.this$1.this$0)) - 1)) goto L10;
                return false;
            L10:
                return true;
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public boolean isChecked(PollEditTextCell r2) {
                RecyclerView.ViewHolder r22 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$1.this$0).findContainingViewHolder(r2);
                if (r22 == null) goto L8;
                int r23 = r22.getAdapterPosition();
                if (r23 == (-1)) goto L10;
                int r24 = r23 - ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$1.this$0);
                return ChatAttachAlertPollLayout.m9853$$Nest$fgetanswersChecks(this.this$1.this$0)[r24];
            L10:
                return false;
            L8:
                return false;
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onActionModeStart(EditTextBoldCursor r2, ActionMode r3) {
                if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$1.this$0) == true) goto L5;
                super.onActionModeStart(r2, r3);
                return;
            L5:
                if (r2.isFocused() == true) goto L7;
                return;
            L7:
                if (r2.hasSelection() == false) goto L16;
                Menu r22 = r3.getMenu();
                if (r22.findItem(android.R.id.copy) == null) goto L17;
                ChatActivity.fillActionModeMenu(r22, ((ChatActivity) this.this$1.this$0.parentAlert.baseFragment).getCurrentEncryptedChat(), false, true);
                return;
            L17:
                return;
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onCheckBoxClick(PollEditTextCell r5, boolean r6) {
                if (r6 == true) goto L4;
            L16:
                super.onCheckBoxClick(r5, r6);
                RecyclerView.ViewHolder r52 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$1.this$0).findContainingViewHolder(r5);
                if (r52 == null) goto L21;
                int r53 = r52.getAdapterPosition();
                if (r53 == (-1)) goto L21;
                int r54 = r53 - ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$1.this$0);
                ChatAttachAlertPollLayout.m9853$$Nest$fgetanswersChecks(this.this$1.this$0)[r54] = r6;
            L21:
                ChatAttachAlertPollLayout.m9921$$Nest$mcheckDoneButton(this.this$1.this$0);
                return;
            L4:
                if (ChatAttachAlertPollLayout.m9894$$Nest$fgetquizPoll(this.this$1.this$0) == false) goto L16;
                if (ChatAttachAlertPollLayout.m9871$$Nest$fgetmultipleChoise(this.this$1.this$0) == true) goto L16;
                Arrays.fill(ChatAttachAlertPollLayout.m9853$$Nest$fgetanswersChecks(this.this$1.this$0), false);
                ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$1.this$0).getChildCount();
                int r0 = ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$1.this$0);
            L9:
                if (r0 >= (ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$1.this$0) + ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(this.this$1.this$0))) goto L16;
                RecyclerView.ViewHolder r2 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$1.this$0).findViewHolderForAdapterPosition(r0);
                if (r2 == null) goto L15;
                View r22 = r2.itemView;
                if ((r22 instanceof PollEditTextCell) == false) goto L15;
                ((PollEditTextCell) r22).setChecked(false, true);
            L15:
                r0 = r0 + 1;
                goto L9
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEditTextFocusChanged(boolean r2) {
                ChatAttachAlertPollLayout.m9925$$Nest$monCellFocusChanges(this.this$1.this$0, this, r2);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEmojiButtonClicked(PollEditTextCell r1) {
                ChatAttachAlertPollLayout.m9926$$Nest$monEmojiClicked(this.this$1.this$0, r1);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onFieldTouchUp(EditTextBoldCursor r2) {
                this.this$1.this$0.parentAlert.makeFocusable(r2, true);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public boolean onPastedMultipleLines(ArrayList<CharSequence> r8) {
                if (r8.isEmpty() == false) goto L5;
                return false;
            L5:
                int r0 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$1.this$0).getChildAdapterPosition(this) - ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$1.this$0);
                if (r0 >= 0) goto L8;
                return false;
            L8:
                this.textView.getText().replace(this.textView.getSelectionStart(), this.textView.getSelectionEnd(), r8.remove(0));
                int r02 = r0 + 1;
            L10:
                if (r8.isEmpty() == true) goto L18;
                if (r02 >= ChatAttachAlertPollLayout.m9870$$Nest$fgetmaxAnswersCount(this.this$1.this$0)) goto L18;
                int r3 = ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$1.this$0).length - 1;
            L14:
                ListAdapter r4 = this.this$1;
                if (r3 <= r02) goto L17;
                ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(r4.this$0)[r3] = ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$1.this$0)[r3 - 1];
                r3 = r3 - 1;
                goto L14
            L17:
                ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(r4.this$0)[r02] = r8.remove(0);
                ChatAttachAlertPollLayout r32 = this.this$1.this$0;
                ChatAttachAlertPollLayout.m9910$$Nest$fputanswersCount(r32, ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(r32) + 1);
                r02 = r02 + 1;
            L18:
                ChatAttachAlertPollLayout.m9932$$Nest$mupdateRows(this.this$1.this$0);
                ChatAttachAlertPollLayout r82 = this.this$1.this$0;
                ChatAttachAlertPollLayout.m9915$$Nest$fputrequestFieldFocusAtPosition(r82, (ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(r82) + r02) - 1);
                ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$1.this$0).setItemAnimator(ChatAttachAlertPollLayout.m9865$$Nest$fgetitemAnimator(this.this$1.this$0));
                ChatAttachAlertPollLayout.m9868$$Nest$fgetlistAdapter(this.this$1.this$0).notifyDataSetChanged();
                return true;
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public boolean shouldShowCheckBox() {
                return ChatAttachAlertPollLayout.m9894$$Nest$fgetquizPoll(this.this$1.this$0);
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPollLayout$ListAdapter$7, reason: invalid class name */
        public class AnonymousClass7 implements TextWatcher {
            final /* synthetic */ ListAdapter this$1;
            final /* synthetic */ PollEditTextCell val$cell;

            public AnonymousClass7(ListAdapter r1, PollEditTextCell r2) {
                this.this$1 = r1;
                this.val$cell = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r9) {
                RecyclerView.ViewHolder r0 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$1.this$0).findContainingViewHolder(this.val$cell);
                if (r0 == null) goto L22;
                int r1 = r0.getAdapterPosition();
                int r2 = r1 - ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$1.this$0);
                if (r2 >= 0) goto L7;
                return;
            L7:
                if (r2 < ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$1.this$0).length) goto L10;
                return;
            L10:
                if (ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0) == null) goto L19;
                ImageSpan[] r3 = (ImageSpan[]) r9.getSpans(0, r9.length(), ImageSpan.class);
                int r4 = r3.length;
                int r6 = 0;
            L12:
                if (r6 >= r4) goto L14;
                r9.removeSpan(r3[r6]);
                r6 = r6 + 1;
                goto L12
            L14:
                Emoji.replaceEmoji(r9, this.val$cell.getEditField().getPaint().getFontMetricsInt(), false);
                float r32 = (r0.itemView.getY() - AndroidUtilities.dp(166.0f)) + r0.itemView.getMeasuredHeight();
                ListAdapter r62 = this.this$1;
                if (r32 <= 0.0f) goto L17;
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(r62.this$0).setDirection(0);
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setTranslationY(r32);
            L18:
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setDelegate(this.val$cell);
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).fireUpdate();
                goto L19
            L17:
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(r62.this$0).setDirection(1);
                ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setTranslationY(r0.itemView.getY());
            L19:
                ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$1.this$0)[r2] = r9;
                ChatAttachAlertPollLayout.m9930$$Nest$msetTextLeft(this.this$1.this$0, this.val$cell, r1);
                ChatAttachAlertPollLayout.m9921$$Nest$mcheckDoneButton(this.this$1.this$0);
                return;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: $r8$lambda$3KVDPGbSyw9E5-cNTsjfrmIl1t0, reason: not valid java name */
        public static /* synthetic */ boolean m9934$r8$lambda$3KVDPGbSyw9E5cNTsjfrmIl1t0(PollEditTextCell r1, View r2, int r3, KeyEvent r4) {
            EditTextBoldCursor r22 = (EditTextBoldCursor) r2;
            if (r3 == 67) goto L5;
            return false;
        L5:
            if (r4.getAction() == 0) goto L7;
            return false;
        L7:
            if (r22.length() != 0) goto L13;
            r1.callOnDelete();
            return true;
        L13:
            return false;
        }

        /* renamed from: $r8$lambda$COrDnk8-0FLBMzv1JQOuXUEEb8s, reason: not valid java name */
        public static /* synthetic */ void m9935$r8$lambda$COrDnk80FLBMzv1JQOuXUEEb8s(ListAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$E0bBDWQFEQ88FUrXzzNxFA7UmQI(ListAdapter r0, PollEditTextCell r1, View r2) {
            r0.lambda$onCreateViewHolder$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$NnnX6wO2hcp0gJPBC6rWq4Nc9I4(ListAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$2(r1);
        }

        /* renamed from: $r8$lambda$_0EU7t--SAPHKmxHV6pjp1-VIHw, reason: not valid java name */
        public static /* synthetic */ void m9936$r8$lambda$_0EU7tSAPHKmxHV6pjp1VIHw(ListAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        public static /* synthetic */ boolean $r8$lambda$aDaygsdqPNW3PQtQ3dM7mDFtIEs(ListAdapter r0, PollEditTextCell r1, TextView r2, int r3, KeyEvent r4) {
            return r0.lambda$onCreateViewHolder$4(r1, r2, r3, r4);
        }

        public ListAdapter(ChatAttachAlertPollLayout r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(View r1) {
            ChatAttachAlertPollLayout.m9928$$Nest$mopenAttachOrReplaceMenuForOptions(this.this$0, -2);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1(View r1) {
            ChatAttachAlertPollLayout.m9928$$Nest$mopenAttachOrReplaceMenuForOptions(this.this$0, -3);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$2(View r3) {
            ChatAttachAlertPollLayout.m9923$$Nest$mdeletePollAnswerView(this.this$0, r3, (PollEditTextCell) r3.getParent(), true);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$3(PollEditTextCell r1, View r2) {
            RecyclerView.ViewHolder r12 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$0).findContainingViewHolder(r1);
            if (r12 == null) goto L11;
            int r13 = r12.getAdapterPosition() - ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$0);
            if (r13 >= 0) goto L7;
            return;
        L7:
            if (r13 >= ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$0).length) goto L13;
            ChatAttachAlertPollLayout.m9928$$Nest$mopenAttachOrReplaceMenuForOptions(this.this$0, r13);
            return;
        L13:
            return;
        }

        private /* synthetic */ boolean lambda$onCreateViewHolder$4(PollEditTextCell r3, TextView r4, int r5, KeyEvent r6) {
            if (r5 != 5) goto L22;
            RecyclerView.ViewHolder r42 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$0).findContainingViewHolder(r3);
            if (r42 == null) goto L21;
            int r43 = r42.getAdapterPosition();
            if (r43 == (-1)) goto L21;
            int r62 = r43 - ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$0);
            if (r62 != (ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(this.this$0) - 1)) goto L14;
            if (ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(this.this$0) >= ChatAttachAlertPollLayout.m9870$$Nest$fgetmaxAnswersCount(this.this$0)) goto L14;
            ChatAttachAlertPollLayout.m9919$$Nest$maddNewField(this.this$0);
        L14:
            if (r62 != (ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(this.this$0) - 1)) goto L16;
            AndroidUtilities.hideKeyboard(r3.getTextView());
            goto L21
        L16:
            RecyclerView.ViewHolder r2 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$0).findViewHolderForAdapterPosition(r43 + 1);
            if (r2 == null) goto L21;
            View r22 = r2.itemView;
            if ((r22 instanceof PollEditTextCell) == false) goto L21;
            ((PollEditTextCell) r22).getTextView().requestFocus();
        L21:
            return true;
        L22:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ChatAttachAlertPollLayout.m9896$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != ChatAttachAlertPollLayout.m9876$$Nest$fgetpoll2vAnonymousRow(this.this$0)) goto L5;
            return 10;
        L5:
            if (r2 != ChatAttachAlertPollLayout.m9883$$Nest$fgetpoll2vMultipleRow(this.this$0)) goto L7;
            return 10;
        L7:
            if (r2 != ChatAttachAlertPollLayout.m9884$$Nest$fgetpoll2vQuizRow(this.this$0)) goto L9;
            return 10;
        L9:
            if (r2 != ChatAttachAlertPollLayout.m9874$$Nest$fgetpoll2vAllowAddingRow(this.this$0)) goto L11;
            return 10;
        L11:
            if (r2 != ChatAttachAlertPollLayout.m9875$$Nest$fgetpoll2vAllowRevotingRow(this.this$0)) goto L13;
            return 10;
        L13:
            if (r2 != ChatAttachAlertPollLayout.m9885$$Nest$fgetpoll2vShuffleRow(this.this$0)) goto L15;
            return 10;
        L15:
            if (r2 != ChatAttachAlertPollLayout.m9881$$Nest$fgetpoll2vLimitDurationRow(this.this$0)) goto L17;
            return 10;
        L17:
            if (r2 != ChatAttachAlertPollLayout.m9886$$Nest$fgetpoll2vSubscribersOnlyRow(this.this$0).row) goto L19;
            return 10;
        L19:
            if (r2 != ChatAttachAlertPollLayout.m9878$$Nest$fgetpoll2vLimitByCountryRow(this.this$0).row) goto L22;
            return 10;
        L22:
            if (r2 != ChatAttachAlertPollLayout.m9889$$Nest$fgetquestionHeaderRow(this.this$0)) goto L24;
            return 0;
        L24:
            if (r2 != ChatAttachAlertPollLayout.m9849$$Nest$fgetanswerHeaderRow(this.this$0)) goto L26;
            return 0;
        L26:
            if (r2 != ChatAttachAlertPollLayout.m9897$$Nest$fgetsettingsHeaderRow(this.this$0)) goto L28;
            return 0;
        L28:
            if (r2 != ChatAttachAlertPollLayout.m9904$$Nest$fgetsolutionRowHeader(this.this$0)) goto L31;
            return 0;
        L31:
            if (r2 != ChatAttachAlertPollLayout.m9891$$Nest$fgetquestionSectionRow(this.this$0)) goto L35;
            return 1;
        L35:
            if (r2 != ChatAttachAlertPollLayout.m9850$$Nest$fgetanswerSectionRow(this.this$0)) goto L37;
            return 2;
        L37:
            if (r2 != ChatAttachAlertPollLayout.m9898$$Nest$fgetsettingsSectionRow(this.this$0)) goto L39;
            return 2;
        L39:
            if (r2 != ChatAttachAlertPollLayout.m9902$$Nest$fgetsolutionInfoRow(this.this$0)) goto L41;
            return 2;
        L41:
            if (r2 != ChatAttachAlertPollLayout.m9880$$Nest$fgetpoll2vLimitDurationHideResultsRowInfo(this.this$0)) goto L44;
            return 2;
        L44:
            if (r2 != ChatAttachAlertPollLayout.m9841$$Nest$fgetaddAnswerRow(this.this$0)) goto L46;
            return 3;
        L46:
            if (r2 != ChatAttachAlertPollLayout.m9882$$Nest$fgetpoll2vLimitDurationTimeRow(this.this$0)) goto L48;
            return 3;
        L48:
            if (r2 != ChatAttachAlertPollLayout.m9877$$Nest$fgetpoll2vLimitByCountryListRow(this.this$0)) goto L51;
            return 3;
        L51:
            if (r2 != ChatAttachAlertPollLayout.m9890$$Nest$fgetquestionRow(this.this$0)) goto L55;
            return 4;
        L55:
            if (r2 != ChatAttachAlertPollLayout.m9858$$Nest$fgetdescriptionRow(this.this$0)) goto L59;
            return 11;
        L59:
            if (r2 != ChatAttachAlertPollLayout.m9903$$Nest$fgetsolutionRow(this.this$0)) goto L63;
            return 7;
        L63:
            if (r2 != ChatAttachAlertPollLayout.m9844$$Nest$fgetallowAddingRow(this.this$0)) goto L65;
            return 6;
        L65:
            if (r2 != ChatAttachAlertPollLayout.m9846$$Nest$fgetallowMarkingRow(this.this$0)) goto L67;
            return 6;
        L67:
            if (r2 != ChatAttachAlertPollLayout.m9879$$Nest$fgetpoll2vLimitDurationHideResultsRow(this.this$0)) goto L70;
            return 6;
        L70:
            if (r2 != ChatAttachAlertPollLayout.m9861$$Nest$fgetemptyRow(this.this$0)) goto L74;
            return 8;
        L74:
            if (r2 != ChatAttachAlertPollLayout.m9873$$Nest$fgetpaddingRow(this.this$0)) goto L77;
            return 9;
        L77:
            return 5;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r22 = r2.getAdapterPosition();
            if (r22 != ChatAttachAlertPollLayout.m9841$$Nest$fgetaddAnswerRow(this.this$0)) goto L5;
            return true;
        L5:
            if (r22 != ChatAttachAlertPollLayout.m9884$$Nest$fgetpoll2vQuizRow(this.this$0)) goto L7;
            return true;
        L7:
            if (r22 != ChatAttachAlertPollLayout.m9876$$Nest$fgetpoll2vAnonymousRow(this.this$0)) goto L9;
            return true;
        L9:
            if (r22 != ChatAttachAlertPollLayout.m9883$$Nest$fgetpoll2vMultipleRow(this.this$0)) goto L11;
            return true;
        L11:
            if (r22 != ChatAttachAlertPollLayout.m9874$$Nest$fgetpoll2vAllowAddingRow(this.this$0)) goto L13;
            return true;
        L13:
            if (r22 != ChatAttachAlertPollLayout.m9881$$Nest$fgetpoll2vLimitDurationRow(this.this$0)) goto L15;
            return true;
        L15:
            if (r22 != ChatAttachAlertPollLayout.m9875$$Nest$fgetpoll2vAllowRevotingRow(this.this$0)) goto L17;
            return true;
        L17:
            if (r22 != ChatAttachAlertPollLayout.m9885$$Nest$fgetpoll2vShuffleRow(this.this$0)) goto L19;
            return true;
        L19:
            if (r22 != ChatAttachAlertPollLayout.m9882$$Nest$fgetpoll2vLimitDurationTimeRow(this.this$0)) goto L21;
            return true;
        L21:
            if (r22 != ChatAttachAlertPollLayout.m9879$$Nest$fgetpoll2vLimitDurationHideResultsRow(this.this$0)) goto L23;
            return true;
        L23:
            if (r22 != ChatAttachAlertPollLayout.m9878$$Nest$fgetpoll2vLimitByCountryRow(this.this$0).row) goto L25;
            return true;
        L25:
            if (r22 != ChatAttachAlertPollLayout.m9886$$Nest$fgetpoll2vSubscribersOnlyRow(this.this$0).row) goto L27;
            return true;
        L27:
            if (r22 == ChatAttachAlertPollLayout.m9877$$Nest$fgetpoll2vLimitByCountryListRow(this.this$0)) goto L44;
            return false;
        L44:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r14, int r15) {
            int r0 = r14.getItemViewType();
            int r1 = 3;
            if (r0 == 0) goto L128;
            boolean r5 = true;
            boolean r6 = false;
            if (r0 != 6) goto L7;
            TextCheckCell r142 = (TextCheckCell) r14.itemView;
            if (r15 != ChatAttachAlertPollLayout.m9844$$Nest$fgetallowAddingRow(this.this$0)) goto L121;
            String r152 = LocaleController.getString(R.string.TodoAllowAddingTasks);
            boolean r02 = ChatAttachAlertPollLayout.m9842$$Nest$fgetallowAdding(this.this$0);
            if (ChatAttachAlertPollLayout.m9846$$Nest$fgetallowMarkingRow(this.this$0) == (-1)) goto L118;
            r6 = true;
        L118:
            r142.setTextAndCheck(r152, r02, r6);
            r142.setEnabled(true, null);
            return;
        L121:
            if (r15 != ChatAttachAlertPollLayout.m9846$$Nest$fgetallowMarkingRow(this.this$0)) goto L125;
            r142.setTextAndCheck(LocaleController.getString(R.string.TodoAllowMarkingDone), ChatAttachAlertPollLayout.m9845$$Nest$fgetallowMarking(this.this$0), false);
            r142.setEnabled(true, null);
            return;
        L125:
            if (r15 != ChatAttachAlertPollLayout.m9879$$Nest$fgetpoll2vLimitDurationHideResultsRow(this.this$0)) goto L159;
            r142.setTextAndCheck(LocaleController.getString(R.string.PollV2HideResults), ChatAttachAlertPollLayout.m9862$$Nest$fgethideResults(this.this$0), false);
            r142.setEnabled(true, null);
            return;
        L159:
            return;
        L7:
            if (r0 == 2) goto L87;
            if (r0 != 3) goto L10;
            TextCell r143 = (TextCell) r14.itemView;
            int r03 = ChatAttachAlertPollLayout.m9877$$Nest$fgetpoll2vLimitByCountryListRow(this.this$0);
            ChatAttachAlertPollLayout r12 = this.this$0;
            if (r15 != r03) goto L78;
            ChatAttachAlertPollLayout.m9920$$Nest$mcheckAllowedCountriesList(r12, r143, false);
            return;
        L78:
            if (r15 != ChatAttachAlertPollLayout.m9882$$Nest$fgetpoll2vLimitDurationTimeRow(r12)) goto L81;
            ChatAttachAlertPollLayout.m9922$$Nest$mcheckDurationInfoRow(this.this$0, r143, false);
            return;
        L81:
            r143.setColors(-1, Theme.key_telegram_color_text);
            Drawable r153 = this.mContext.getResources().getDrawable(R.drawable.poll_add_circle);
            Drawable r04 = this.mContext.getResources().getDrawable(R.drawable.poll_add_plus);
            CombinedDrawable r13 = new CombinedDrawable(r153, r04);
            if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$0) == false) goto L84;
            int r2 = R.string.TodoNewTask;
        L85:
            r143.setTextAndIcon(LocaleController.getString(r2), r13, false);
            int r22 = this.this$0.getThemedColor(Theme.key_switchTrackChecked);
            PorterDuff.Mode r3 = PorterDuff.Mode.MULTIPLY;
            r153.setColorFilter(new PorterDuffColorFilter(r22, r3));
            r04.setColorFilter(new PorterDuffColorFilter(this.this$0.getThemedColor(Theme.key_checkboxCheck), r3));
            r143.imageLeft = 20;
            r143.offsetFromImage = 58;
            return;
        L84:
            r2 = R.string.AddAnOption;
            goto L85
        L10:
            if (r0 != 9) goto L12;
            r14.itemView.requestLayout();
            return;
        L12:
            if (r0 != 10) goto L161;
            PollCreateCheckCell r7 = (PollCreateCheckCell) r14.itemView;
            r7.setDivider(false);
            if (r15 != ChatAttachAlertPollLayout.m9876$$Nest$fgetpoll2vAnonymousRow(this.this$0)) goto L18;
            r7.setTextAndValueAndIconAndCheck(LocaleController.getString(R.string.PollV2ShowWhoVoted), LocaleController.getString(R.string.PollV2ShowWhoVotedInfo), IconBackgroundColors.BLUE, R.drawable.filled_poll_view_24, !ChatAttachAlertPollLayout.m9848$$Nest$fgetanonymousPoll(this.this$0));
        L56:
            if (r15 != ChatAttachAlertPollLayout.m9884$$Nest$fgetpoll2vQuizRow(this.this$0)) goto L60;
            r7.getCheckBox().setIconVisible(ChatAttachAlertPollLayout.m9893$$Nest$fgetquizOnly(this.this$0), false);
            return;
        L60:
            if (r15 != ChatAttachAlertPollLayout.m9874$$Nest$fgetpoll2vAllowAddingRow(this.this$0)) goto L69;
            Switch r144 = r7.getCheckBox();
            if (ChatAttachAlertPollLayout.m9894$$Nest$fgetquizPoll(this.this$0) == false) goto L64;
        L67:
            r144.setIconVisible(r5, false);
            return;
        L64:
            if (ChatAttachAlertPollLayout.m9848$$Nest$fgetanonymousPoll(this.this$0) == true) goto L67;
            r5 = false;
            goto L67
        L69:
            r7.getCheckBox().setIconVisible(false, false);
            return;
        L18:
            if (r15 != ChatAttachAlertPollLayout.m9883$$Nest$fgetpoll2vMultipleRow(this.this$0)) goto L21;
            r7.setTextAndValueAndIconAndCheck(LocaleController.getString(R.string.PollV2AllowMultipleAnswers), LocaleController.getString(R.string.PollV2AllowMultipleAnswersInfo), IconBackgroundColors.ORANGE, R.drawable.filled_poll_multiple_24, ChatAttachAlertPollLayout.m9871$$Nest$fgetmultipleChoise(this.this$0));
            goto L56
        L21:
            if (r15 != ChatAttachAlertPollLayout.m9875$$Nest$fgetpoll2vAllowRevotingRow(this.this$0)) goto L24;
            r7.setTextAndValueAndIconAndCheck(LocaleController.getString(R.string.PollV2AllowRevoting), LocaleController.getString(R.string.PollV2AllowRevotingInfo), IconBackgroundColors.PURPLE, R.drawable.filled_poll_revote_24, ChatAttachAlertPollLayout.m9847$$Nest$fgetallowRevoting(this.this$0));
            goto L56
        L24:
            if (r15 != ChatAttachAlertPollLayout.m9874$$Nest$fgetpoll2vAllowAddingRow(this.this$0)) goto L27;
            r7.setTextAndValueAndIconAndCheck(LocaleController.getString(R.string.PollV2AllowAddingOptions), LocaleController.getString(R.string.PollV2AllowAddingOptionsInfo), IconBackgroundColors.CYAN, R.drawable.filled_poll_add_24, ChatAttachAlertPollLayout.m9843$$Nest$fgetallowAddingOptions(this.this$0));
            goto L56
        L27:
            if (r15 != ChatAttachAlertPollLayout.m9885$$Nest$fgetpoll2vShuffleRow(this.this$0)) goto L30;
            r7.setTextAndValueAndIconAndCheck(LocaleController.getString(R.string.PollV2ShuffleOptions), LocaleController.getString(R.string.PollV2ShuffleOptionsInfo), IconBackgroundColors.ORANGE_DEEP, R.drawable.filled_poll_shuffle_24, ChatAttachAlertPollLayout.m9900$$Nest$fgetshuffleOptions(this.this$0));
            goto L56
        L30:
            if (r15 != ChatAttachAlertPollLayout.m9884$$Nest$fgetpoll2vQuizRow(this.this$0)) goto L33;
            r7.setTextAndValueAndIconAndCheck(LocaleController.getString(R.string.PollV2SetCorrectAnswer), LocaleController.getString(R.string.PollV2SetCorrectAnswerInfo), IconBackgroundColors.GREEN, R.drawable.filled_poll_correct_24, ChatAttachAlertPollLayout.m9894$$Nest$fgetquizPoll(this.this$0));
            goto L56
        L33:
            if (r15 != ChatAttachAlertPollLayout.m9878$$Nest$fgetpoll2vLimitByCountryRow(this.this$0).row) goto L36;
            r7.setTextAndValueAndIconAndCheck(LocaleController.getString(R.string.PollV2LimitByCountry), LocaleController.getString(R.string.PollV2LimitByCountryInfo), IconBackgroundColors.BLUE_LIGHT, R.drawable.filled_location, ChatAttachAlertPollLayout.m9878$$Nest$fgetpoll2vLimitByCountryRow(this.this$0).checked);
            goto L56
        L36:
            if (r15 != ChatAttachAlertPollLayout.m9886$$Nest$fgetpoll2vSubscribersOnlyRow(this.this$0).row) goto L39;
            r7.setTextAndValueAndIconAndCheck(LocaleController.getString(R.string.PollV2RestrictToSubscribers), LocaleController.getString(R.string.PollV2RestrictToSubscribersInfo), IconBackgroundColors.BLUE_DEEP, R.drawable.msg_folders_groups, ChatAttachAlertPollLayout.m9886$$Nest$fgetpoll2vSubscribersOnlyRow(this.this$0).checked);
            goto L56
        L39:
            if (r15 != ChatAttachAlertPollLayout.m9881$$Nest$fgetpoll2vLimitDurationRow(this.this$0)) goto L56;
            String r8 = LocaleController.getString(R.string.PollV2LimitDuration);
            String r9 = LocaleController.getString(R.string.PollV2LimitDurationInfo);
            IconBackgroundColors r10 = IconBackgroundColors.RED;
            int r11 = R.drawable.filled_poll_deadline_24;
            if (ChatAttachAlertPollLayout.m9888$$Nest$fgetpollLimitDuration(this.this$0) == 0) goto L43;
        L46:
            boolean r122 = true;
        L47:
            r7.setTextAndValueAndIconAndCheck(r8, r9, r10, r11, r122);
            if (ChatAttachAlertPollLayout.m9888$$Nest$fgetpollLimitDuration(this.this$0) == 0) goto L50;
        L53:
            boolean r145 = true;
        L54:
            r7.setDivider(r145);
            goto L56
        L50:
            if (ChatAttachAlertPollLayout.m9887$$Nest$fgetpollLimitDeadline(this.this$0) != 0) goto L53;
            r145 = false;
            goto L54
        L43:
            if (ChatAttachAlertPollLayout.m9887$$Nest$fgetpollLimitDeadline(this.this$0) != 0) goto L46;
            r122 = false;
            goto L47
        L161:
            return;
        L87:
            TextInfoPrivacyCell r146 = (TextInfoPrivacyCell) r14.itemView;
            r146.setFixedSize(0);
            new CombinedDrawable(new ColorDrawable(this.this$0.getThemedColor(Theme.key_windowBackgroundGray)), Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow)).setFullsize(true);
            if (r15 != ChatAttachAlertPollLayout.m9902$$Nest$fgetsolutionInfoRow(this.this$0)) goto L92;
            r146.setText(LocaleController.getString(R.string.AddAnExplanationInfo));
            return;
        L92:
            if (r15 != ChatAttachAlertPollLayout.m9898$$Nest$fgetsettingsSectionRow(this.this$0)) goto L95;
            r146.setFixedSize(12);
            r146.setText(null);
            return;
        L95:
            int r05 = ChatAttachAlertPollLayout.m9870$$Nest$fgetmaxAnswersCount(this.this$0) - ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(this.this$0);
            ChatAttachAlertPollLayout r16 = this.this$0;
            if (r05 <= 0) goto L98;
            boolean r06 = ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(r16);
            ChatAttachAlertPollLayout r17 = this.this$0;
            if (r06 == false) goto L108;
            r146.setText(LocaleController.formatPluralStringComma("TodoNewTaskInfo", ChatAttachAlertPollLayout.m9870$$Nest$fgetmaxAnswersCount(r17) - ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(this.this$0)));
            return;
        L108:
            if (r15 != ChatAttachAlertPollLayout.m9880$$Nest$fgetpoll2vLimitDurationHideResultsRowInfo(r17)) goto L111;
            r146.setText(LocaleController.getString(R.string.PollV2HideResultsInfo));
            return;
        L111:
            r146.setText(LocaleController.formatString(R.string.AddAnOptionInfo, new Object[]{LocaleController.formatPluralString("Option", ChatAttachAlertPollLayout.m9870$$Nest$fgetmaxAnswersCount(this.this$0) - ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(this.this$0), new Object[0])}));
            return;
        L98:
            if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(r16) == false) goto L100;
            int r132 = R.string.TodoAddTaskInfoMax;
        L101:
            r146.setText(LocaleController.getString(r132));
            return;
        L100:
            r132 = R.string.AddAnOptionInfoMax;
            goto L101
        L128:
            HeaderCell r147 = (HeaderCell) r14.itemView;
            if (r15 != ChatAttachAlertPollLayout.m9889$$Nest$fgetquestionHeaderRow(this.this$0)) goto L137;
            r147.getTextView().setGravity(19);
            if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$0) == false) goto L133;
            int r133 = R.string.TodoTitle;
        L134:
            r147.setText(LocaleController.getString(r133));
            return;
        L133:
            r133 = R.string.PollQuestion2;
            goto L134
        L137:
            if (r15 != ChatAttachAlertPollLayout.m9904$$Nest$fgetsolutionRowHeader(this.this$0)) goto L140;
            r147.getTextView().setGravity(19);
            r147.setText(LocaleController.getString(R.string.AddAnExplanationHeader));
            return;
        L140:
            TextView r07 = r147.getTextView();
            if (LocaleController.isRTL == false) goto L143;
            r1 = 5;
        L143:
            r07.setGravity(r1 | 16);
            int r08 = ChatAttachAlertPollLayout.m9849$$Nest$fgetanswerHeaderRow(this.this$0);
            ChatAttachAlertPollLayout r18 = this.this$0;
            if (r15 != r08) goto L156;
            if (ChatAttachAlertPollLayout.m9893$$Nest$fgetquizOnly(r18) == false) goto L150;
            r147.setText(LocaleController.getString(R.string.QuizAnswers));
            return;
        L150:
            if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$0) == false) goto L152;
            int r134 = R.string.TodoItemsTitle;
        L153:
            r147.setText(LocaleController.getString(r134));
            return;
        L152:
            r134 = R.string.AnswerOptions2;
            goto L153
        L156:
            if (r15 != ChatAttachAlertPollLayout.m9897$$Nest$fgetsettingsHeaderRow(r18)) goto L160;
            r147.setText(LocaleController.getString(R.string.Settings));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r13, int r14) {
            switch(r14) {
                case 0: goto L26;
                case 1: goto L25;
                case 2: goto L24;
                case 3: goto L23;
                case 4: goto L17;
                case 5: goto L4;
                case 6: goto L16;
                case 7: goto L11;
                case 8: goto L10;
                case 9: goto L9;
                case 10: goto L8;
                case 11: goto L17;
                default: goto L4;
            };
        L4:
            Context r5 = this.mContext;
            boolean r7 = ChatAttachAlertPollLayout.m9864$$Nest$fgetisPremium(this.this$0);
            ChatAttachAlertPollLayout$ListAdapter$$ExternalSyntheticLambda2 r8 = new ChatAttachAlertPollLayout$ListAdapter$$ExternalSyntheticLambda2(this);
            Theme.ResourcesProvider r9 = this.this$0.resourcesProvider;
            AnonymousClass6 r3 = new AnonymousClass6(this, r5, false, r7 ? 1 : 0, r8, r9);
            if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$0) == true) goto L7;
            r3.setTextRight(140);
            r3.addAttachView().setOnClickListener(new ChatAttachAlertPollLayout$ListAdapter$$ExternalSyntheticLambda3(this, r3));
        L7:
            int r12 = Theme.key_pollCreateIcons;
            r3.setIconsColor(r12);
            r3.supportMultiselect();
            r3.getCheckBox().setColor(-1, r12, Theme.key_checkboxCheck);
            r3.addTextWatcher(new AnonymousClass7(this, r3));
            r3.setShowNextButton(true);
            EditTextBoldCursor r122 = r3.getTextView();
            r122.setImeOptions(r122.getImeOptions() | 5);
            r122.setOnEditorActionListener(new ChatAttachAlertPollLayout$ListAdapter$$ExternalSyntheticLambda4(this, r3));
            r122.setOnKeyListener(new ChatAttachAlertPollLayout$ListAdapter$$ExternalSyntheticLambda5(r3));
            View r32 = r3;
        L27:
            r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r32);
        L8:
            PollCreateCheckCell r33 = new PollCreateCheckCell(this.mContext, this.this$0.resourcesProvider);
            r33.getCheckBox().setIcon(R.drawable.permission_locked);
            r32 = r33;
            goto L27
        L9:
            View r34 = new AnonymousClass5(this, this.mContext);
            r34.setTag(-33024);
            r32 = r34;
            goto L27
        L10:
            View r35 = new EmptyView(this.mContext);
            r35.setTag(-33024);
            r32 = r35;
            goto L27
        L11:
            AnonymousClass3 r4 = new AnonymousClass3(this, this.mContext, false, ChatAttachAlertPollLayout.m9864$$Nest$fgetisPremium(this.this$0) ? 1 : 0, null);
            r4.createErrorTextView();
            if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$0) == true) goto L14;
            r4.setTextRight(98);
            r4.addAttachView().setOnClickListener(new ChatAttachAlertPollLayout$ListAdapter$$ExternalSyntheticLambda1(this));
        L14:
            r4.setIconsColor(Theme.key_pollCreateIcons);
            r4.addTextWatcher(new AnonymousClass4(this, r4));
            View r42 = r4;
        L15:
            r32 = r42;
            goto L27
        L16:
            r32 = new TextCheckCell(this.mContext, this.this$0.resourcesProvider);
            goto L27
        L17:
            Context r6 = this.mContext;
            boolean r82 = ChatAttachAlertPollLayout.m9864$$Nest$fgetisPremium(this.this$0);
            Theme.ResourcesProvider r10 = this.this$0.resourcesProvider;
            AnonymousClass1 r43 = new AnonymousClass1(this, r6, false, r82 ? 1 : 0, null, r10, r14);
            if (r14 == 11) goto L20;
        L22:
            r43.createErrorTextView();
            r43.setIconsColor(Theme.key_pollCreateIcons);
            r43.addTextWatcher(new AnonymousClass2(this, r43, r14));
            r42 = r43;
            goto L15
        L20:
            if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$0) == true) goto L22;
            r43.setTextRight(98);
            r43.addAttachView().setOnClickListener(new ChatAttachAlertPollLayout$ListAdapter$$ExternalSyntheticLambda0(this));
            goto L22
        L23:
            r32 = new TextCell(this.mContext, this.this$0.resourcesProvider);
            goto L27
        L24:
            r32 = new TextInfoPrivacyCell(this.mContext, this.this$0.resourcesProvider);
            goto L27
        L25:
            View r36 = new ShadowSectionCell(this.mContext, this.this$0.resourcesProvider);
            Drawable r123 = Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow);
            new CombinedDrawable(new ColorDrawable(this.this$0.getThemedColor(Theme.key_windowBackgroundGray)), r123).setFullsize(true);
            r32 = r36;
            goto L27
        L26:
            r42 = new HeaderCell(this.mContext, Theme.key_windowBackgroundWhiteBlueHeader, 21, 15, false, this.this$0.resourcesProvider);
            goto L15
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r9) {
            int r2 = r9.getItemViewType();
            CharSequence r4 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r2 != 4) goto L14;
            PollEditTextCell r22 = (PollEditTextCell) r9.itemView;
            r22.setTag(1);
            if (ChatAttachAlertPollLayout.m9892$$Nest$fgetquestionString(this.this$0) == null) goto L8;
            r4 = ChatAttachAlertPollLayout.m9892$$Nest$fgetquestionString(this.this$0);
        L8:
            if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$0) == false) goto L10;
            int r1 = R.string.TodoTitlePlaceholder;
        L11:
            r22.setTextAndHint(r4, LocaleController.getString(r1), true);
            r22.setTag(null);
            ChatAttachAlertPollLayout.m9930$$Nest$msetTextLeft(this.this$0, r9.itemView, r9.getAdapterPosition());
            return;
        L10:
            r1 = R.string.QuestionHint;
            goto L11
        L14:
            if (r2 != 11) goto L21;
            PollEditTextCell r0 = (PollEditTextCell) r9.itemView;
            r0.setTag(1);
            if (ChatAttachAlertPollLayout.m9859$$Nest$fgetdescriptionString(this.this$0) == null) goto L18;
            r4 = ChatAttachAlertPollLayout.m9859$$Nest$fgetdescriptionString(this.this$0);
        L18:
            r0.setTextAndHint(r4, LocaleController.getString(R.string.QuestionDescriptionHint), false);
            r0.setTag(null);
            r0.attachView.setAttachedMedia(ChatAttachAlertPollLayout.m9855$$Nest$fgetattachedMedia(this.this$0).get(-2), false);
            ChatAttachAlertPollLayout.m9930$$Nest$msetTextLeft(this.this$0, r9.itemView, r9.getAdapterPosition());
            return;
        L21:
            if (r2 != 5) goto L35;
            int r23 = r9.getAdapterPosition();
            PollEditTextCell r3 = (PollEditTextCell) r9.itemView;
            r3.setTag(1);
            r3.setCheckboxMultiselect(ChatAttachAlertPollLayout.m9871$$Nest$fgetmultipleChoise(this.this$0), false);
            int r12 = r23 - ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$0);
            CharSequence r42 = ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$0)[r12];
            if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$0) == false) goto L25;
            int r7 = R.string.TodoTaskPlaceholder;
        L26:
            r3.setTextAndHint(r42, LocaleController.getString(r7), true);
            r3.setTag(null);
            if (ChatAttachAlertPollLayout.m9895$$Nest$fgetrequestFieldFocusAtPosition(this.this$0) != r23) goto L30;
            EditTextBoldCursor r02 = r3.getTextView();
            r02.requestFocus();
            AndroidUtilities.showKeyboard(r02);
            ChatAttachAlertPollLayout.m9915$$Nest$fputrequestFieldFocusAtPosition(this.this$0, -1);
        L30:
            if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$0) == true) goto L32;
            r3.attachView.setAttachedMedia(ChatAttachAlertPollLayout.m9855$$Nest$fgetattachedMedia(this.this$0).get(r12), false);
        L32:
            ChatAttachAlertPollLayout.m9930$$Nest$msetTextLeft(this.this$0, r9.itemView, r23);
            return;
        L25:
            r7 = R.string.OptionHint;
            goto L26
        L35:
            if (r2 != 7) goto L44;
            PollEditTextCell r03 = (PollEditTextCell) r9.itemView;
            r03.setTag(1);
            if (ChatAttachAlertPollLayout.m9905$$Nest$fgetsolutionString(this.this$0) == null) goto L39;
            r4 = ChatAttachAlertPollLayout.m9905$$Nest$fgetsolutionString(this.this$0);
        L39:
            r03.setTextAndHint(r4, LocaleController.getString(R.string.AddAnExplanation), false);
            r03.setTag(null);
            if (ChatAttachAlertPollLayout.m9907$$Nest$fgettodo(this.this$0) == true) goto L42;
            r03.attachView.setAttachedMedia(ChatAttachAlertPollLayout.m9855$$Nest$fgetattachedMedia(this.this$0).get(-3), false);
        L42:
            ChatAttachAlertPollLayout.m9930$$Nest$msetTextLeft(this.this$0, r9.itemView, r9.getAdapterPosition());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewDetachedFromWindow(RecyclerView.ViewHolder r3) {
            if (r3.getItemViewType() != 4) goto L5;
        L8:
            EditTextBoldCursor r32 = ((PollEditTextCell) r3.itemView).getTextView();
            if (r32.isFocused() == true) goto L11;
            return;
        L11:
            if (ChatAttachAlertPollLayout.m9864$$Nest$fgetisPremium(this.this$0) == true) goto L13;
        L16:
            ChatAttachAlertPollLayout.m9911$$Nest$fputcurrentCell(this.this$0, null);
            r32.clearFocus();
            AndroidUtilities.hideKeyboard(r32);
            return;
        L13:
            if (ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$0) == null) goto L15;
            ChatAttachAlertPollLayout.m9906$$Nest$fgetsuggestEmojiPanel(this.this$0).forceClose();
        L15:
            ChatAttachAlertPollLayout.m9924$$Nest$mhideEmojiPopup(this.this$0, true);
            goto L16
        L5:
            if (r3.getItemViewType() == 11) goto L8;
            if (r3.getItemViewType() == 5) goto L8;
        }

        public void swapElements(int r6, int r7) {
            int r0 = r6 - ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$0);
            int r1 = r7 - ChatAttachAlertPollLayout.m9851$$Nest$fgetanswerStartRow(this.this$0);
            if (r0 < 0) goto L12;
            if (r1 >= 0) goto L6;
            return;
        L6:
            if (r0 < ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(this.this$0)) goto L8;
            return;
        L8:
            if (r1 >= ChatAttachAlertPollLayout.m9854$$Nest$fgetanswersCount(this.this$0)) goto L15;
            PollAttachedMedia r2 = ChatAttachAlertPollLayout.m9855$$Nest$fgetattachedMedia(this.this$0).get(r0);
            ChatAttachAlertPollLayout.m9855$$Nest$fgetattachedMedia(this.this$0).set(r0, ChatAttachAlertPollLayout.m9855$$Nest$fgetattachedMedia(this.this$0).get(r1));
            ChatAttachAlertPollLayout.m9855$$Nest$fgetattachedMedia(this.this$0).set(r1, r2);
            CharSequence r22 = ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$0)[r0];
            ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$0)[r0] = ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$0)[r1];
            ChatAttachAlertPollLayout.m9852$$Nest$fgetanswers(this.this$0)[r1] = r22;
            boolean r23 = ChatAttachAlertPollLayout.m9853$$Nest$fgetanswersChecks(this.this$0)[r0];
            ChatAttachAlertPollLayout.m9853$$Nest$fgetanswersChecks(this.this$0)[r0] = ChatAttachAlertPollLayout.m9853$$Nest$fgetanswersChecks(this.this$0)[r1];
            ChatAttachAlertPollLayout.m9853$$Nest$fgetanswersChecks(this.this$0)[r1] = r23;
            notifyItemMoved(r6, r7);
            return;
        L15:
            return;
        }
    }

    public interface PollCreateActivityDelegate {
        void sendPoll(TLRPC.MessageMedia r1, CharSequence r2, PollAttachedMediaPack r3, ArrayList<Integer> r4, boolean r5, int r6, long r7);
    }

    public class ToggleRow {
        public boolean checked;
        public int row;
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        private ToggleRow(ChatAttachAlertPollLayout r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ToggleRow(ChatAttachAlertPollLayout r1, ChatAttachAlertPollLayoutIA r2) {
            this(r1);
        }

        public void addRows(int r2) {
            ChatAttachAlertPollLayout.m9868$$Nest$fgetlistAdapter(this.this$0).notifyItemRangeInserted(this.row + 1, r2);
        }

        public void removeRows(int r2) {
            ChatAttachAlertPollLayout.m9868$$Nest$fgetlistAdapter(this.this$0).notifyItemRangeRemoved(this.row + 1, r2);
        }

        public void setDivider(boolean r3) {
            if (this.row >= 0) goto L5;
            return;
        L5:
            RecyclerView.ViewHolder r0 = ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$0).findViewHolderForAdapterPosition(this.row);
            if (r0 == null) goto L11;
            View r02 = r0.itemView;
            if ((r02 instanceof PollCreateCheckCell) == false) goto L11;
            ((PollCreateCheckCell) r02).setDivider(r3);
            return;
        L11:
            ChatAttachAlertPollLayout.m9868$$Nest$fgetlistAdapter(this.this$0).notifyItemChanged(this.row);
        }
    }

    public class TouchHelperCallback extends ItemTouchHelper.Callback {
        final /* synthetic */ ChatAttachAlertPollLayout this$0;

        public TouchHelperCallback(ChatAttachAlertPollLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
            super.clearView(r1, r2);
            r2.itemView.setPressed(false);
            r2.itemView.setBackground(null);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r1, RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() == 5) goto L7;
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        L7:
            return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onChildDraw(Canvas r1, RecyclerView r2, RecyclerView.ViewHolder r3, float r4, float r5, int r6, boolean r7) {
            super.onChildDraw(r1, r2, r3, r4, r5, r6, r7);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r2, RecyclerView.ViewHolder r3, RecyclerView.ViewHolder r4) {
            if (r3.getItemViewType() == r4.getItemViewType()) goto L6;
            return false;
        L6:
            ChatAttachAlertPollLayout.m9868$$Nest$fgetlistAdapter(this.this$0).swapElements(r3.getAdapterPosition(), r4.getAdapterPosition());
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r4, int r5) {
            if (r5 == 0) goto L4;
            ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$0).setItemAnimator(ChatAttachAlertPollLayout.m9865$$Nest$fgetitemAnimator(this.this$0));
            ChatAttachAlertPollLayout.m9869$$Nest$fgetlistView(this.this$0).cancelClickRunnables(false);
            r4.itemView.setPressed(true);
            r4.itemView.setBackgroundColor(this.this$0.getThemedColor(Theme.key_dialogBackground));
        L4:
            super.onSelectedChanged(r4, r5);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    /* renamed from: $r8$lambda$-jlepIoq0AQw1blvj4WTNrS5C5A, reason: not valid java name */
    public static /* synthetic */ Drawable m9830$r8$lambda$jlepIoq0AQw1blvj4WTNrS5C5A(String r1, String r2, View r3) {
        return PollAttachedMediaFile.createMessagePreviewDrawable(r3, r1, r2, null, null);
    }

    public static /* synthetic */ void $r8$lambda$1xMoot37YPnPnJxjVW8s5ZuAHsA(ChatAttachAlertPollLayout r0, View r1, PollEditTextCell r2, AlertDialog r3, int r4) {
        r0.lambda$deletePollAnswerView$13(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$5w7_4X0k93Rh6d7zFlBMq0SLIFg(ChatAttachAlertPollLayout r0) {
        r0.lambda$openAttachMenuForOptions$24();
    }

    /* renamed from: $r8$lambda$6_zwOl-z57IXoD9chwJLjAd2syQ, reason: not valid java name */
    public static /* synthetic */ void m9831$r8$lambda$6_zwOlz57IXoD9chwJLjAd2syQ(ChatAttachAlertPollLayout r0, int r1, String r2) {
        r0.lambda$openEditOrReplaceMenu$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$7QnHkJ2NM5MUB9Wbolnw9B6G_Eg(ChatAttachAlertPollLayout r0, int r1) {
        r0.lambda$showOptionsForDrawable$19(r1);
    }

    /* renamed from: $r8$lambda$9w1_RlEquHPRRrW-P2y0Rh0a-FE, reason: not valid java name */
    public static /* synthetic */ void m9832$r8$lambda$9w1_RlEquHPRRrWP2y0Rh0aFE() {
    }

    public static /* synthetic */ void $r8$lambda$DOeKNajM8xUSkIFmhY9Gx5R7FxE(ChatAttachAlertPollLayout r0, ValueAnimator r1) {
        r0.lambda$hideEmojiPopup$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$DnIrJP0mIWPWc0hQYkCmrvhHPCg(ChatAttachAlertPollLayout r0, TLRPC.TL_messageMediaPoll r1, ArrayList r2, Long r3, boolean r4, int r5, int r6) {
        r0.lambda$onPollDoneButtonClick$7(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$FyomEPpf22t9E_38FCDQswy0T7w(ChatAttachAlertPollLayout r0, Context r1, View r2, Theme.ResourcesProvider r3) {
        r0.lambda$new$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$NjvH0FLk4BuUZ99YffYtLlRhHrs(ChatAttachAlertPollLayout r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Np5AAq0OmnCk7YA__ZwEEeOcsg0(ChatAttachAlertPollLayout r0, PollAttachedMedia r1, TLRPC.WebPage r2, TLObject r3) {
        r0.lambda$setAttachedMedia$25(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$OG1oehFLeXh6VHg_d4GuBf_ZbTg(View r0, DialogInterface r1) {
        r0.setTag(null);
    }

    /* renamed from: $r8$lambda$Sx-8Ndeku1lmxeu5gQt9ZKBMgJM, reason: not valid java name */
    public static /* synthetic */ void m9833$r8$lambda$Sx8Ndeku1lmxeu5gQt9ZKBMgJM(ChatAttachAlertPollLayout r0, float r1, float r2, ValueAnimator r3) {
        r0.lambda$animateEmojiViewTranslationY$12(r1, r2, r3);
    }

    public static /* synthetic */ Drawable $r8$lambda$VeMvVDCpTuC_FDsnlT3ZAok1WZA(String r1, String r2, PollAttachedMediaMusic r3, View r4) {
        return PollAttachedMediaFile.createMessagePreviewDrawable(r4, r1, r2, r3.messageObject.getDocument(), r3.messageObject);
    }

    public static /* synthetic */ void $r8$lambda$YbyRLXlAvusUY_jQksdYboAzouA(Utilities.Callback r0, ChatAttachAlert r1, ArrayList r2, CharSequence r3, boolean r4, int r5, int r6, long r7, boolean r9, long r10) {
        if (r2 != null) goto L4;
    L6:
        r1.dismiss(true);
        return;
    L4:
        if (r2.isEmpty() == true) goto L6;
        r0.run(new PollAttachedMediaMusic((MessageObject) r2.get(0)));
        goto L6
    }

    /* renamed from: $r8$lambda$bQeHo5LNfDv_Pt8QqJCkPjQlk-U, reason: not valid java name */
    public static /* synthetic */ void m9834$r8$lambda$bQeHo5LNfDv_Pt8QqJCkPjQlkU(ChatAttachAlertPollLayout r0, ValueAnimator r1) {
        r0.lambda$showEmojiPopup$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$ba7D4R4BvHHriodrTTOHe43XS34(ChatAttachAlertPollLayout r0, int r1) {
        r0.lambda$openEditOrReplaceMenu$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$bo6H5FvUr1CvT4Cd5cGqZbGfeGo(Utilities.Callback r0, TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
        r0.run(new PollAttachedMediaLocation(r1));
    }

    /* renamed from: $r8$lambda$c8EwF21i7-X0Y4eFwsZ7hABiCLY, reason: not valid java name */
    public static /* synthetic */ void m9835$r8$lambda$c8EwF21i7X0Y4eFwsZ7hABiCLY(ChatAttachAlertPollLayout r0, int r1) {
        r0.lambda$showOptionsForDrawable$20(r1);
    }

    /* renamed from: $r8$lambda$fqT72UwXmp-Fx41nSLcJZSBW6WU, reason: not valid java name */
    public static /* synthetic */ void m9836$r8$lambda$fqT72UwXmpFx41nSLcJZSBW6WU(ChatAttachAlertPollLayout r0, int r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$fqvD6Lp7juAPjPPjn--mplnmHcs, reason: not valid java name */
    public static /* synthetic */ void m9837$r8$lambda$fqvD6Lp7juAPjPPjnmplnmHcs(ChatAttachAlertPollLayout r0, Theme.ResourcesProvider r1, ChatAttachAlert r2, Context r3, View r4, int r5) {
        r0.lambda$new$4(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$hDFOTjASetvoG5hK0G_5C6HqzK4(ChatAttachAlertPollLayout r0, ChatActivity r1, TLRPC.TL_messageMediaToDo r2, Long r3) {
        r0.lambda$onTodoDoneButtonClick$6(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$rZOzCJkd3iXCairjDGPnKf9oI0Y(ChatAttachAlertPollLayout r0, ChatActivity r1, TLRPC.TL_messageMediaPoll r2, ArrayList r3, Long r4) {
        r0.lambda$onPollDoneButtonClick$8(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$rc5eewhVL8hE-IqjksDxD7wSDfw, reason: not valid java name */
    public static /* synthetic */ void m9838$r8$lambda$rc5eewhVL8hEIqjksDxD7wSDfw(ChatAttachAlertPollLayout r0, TLRPC.TL_messageMediaToDo r1, Long r2, boolean r3, int r4, int r5) {
        r0.lambda$onTodoDoneButtonClick$5(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$wOzDXufHnPEWSe-pcFN76H27k1Q, reason: not valid java name */
    public static /* synthetic */ void m9839$r8$lambda$wOzDXufHnPEWSepcFN76H27k1Q(ChatAttachAlertPollLayout r0, View r1, boolean r2, int r3, int r4) {
        r0.lambda$new$1(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$wk-rZfS-5QEwEHfXlegsuhFfEtE, reason: not valid java name */
    public static /* synthetic */ void m9840$r8$lambda$wkrZfS5QEwEHfXlegsuhFfEtE(ChatAttachAlertPollLayout r0, int r1, PollAttachedMedia r2) {
        r0.lambda$openAttachMenuForOptions$23(r1, r2);
    }

    /* renamed from: -$$Nest$fgetaddAnswerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9841$$Nest$fgetaddAnswerRow(ChatAttachAlertPollLayout r0) {
        return r0.addAnswerRow;
    }

    /* renamed from: -$$Nest$fgetallowAdding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9842$$Nest$fgetallowAdding(ChatAttachAlertPollLayout r0) {
        return r0.allowAdding;
    }

    /* renamed from: -$$Nest$fgetallowAddingOptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9843$$Nest$fgetallowAddingOptions(ChatAttachAlertPollLayout r0) {
        return r0.allowAddingOptions;
    }

    /* renamed from: -$$Nest$fgetallowAddingRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9844$$Nest$fgetallowAddingRow(ChatAttachAlertPollLayout r0) {
        return r0.allowAddingRow;
    }

    /* renamed from: -$$Nest$fgetallowMarking, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9845$$Nest$fgetallowMarking(ChatAttachAlertPollLayout r0) {
        return r0.allowMarking;
    }

    /* renamed from: -$$Nest$fgetallowMarkingRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9846$$Nest$fgetallowMarkingRow(ChatAttachAlertPollLayout r0) {
        return r0.allowMarkingRow;
    }

    /* renamed from: -$$Nest$fgetallowRevoting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9847$$Nest$fgetallowRevoting(ChatAttachAlertPollLayout r0) {
        return r0.allowRevoting;
    }

    /* renamed from: -$$Nest$fgetanonymousPoll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9848$$Nest$fgetanonymousPoll(ChatAttachAlertPollLayout r0) {
        return r0.anonymousPoll;
    }

    /* renamed from: -$$Nest$fgetanswerHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9849$$Nest$fgetanswerHeaderRow(ChatAttachAlertPollLayout r0) {
        return r0.answerHeaderRow;
    }

    /* renamed from: -$$Nest$fgetanswerSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9850$$Nest$fgetanswerSectionRow(ChatAttachAlertPollLayout r0) {
        return r0.answerSectionRow;
    }

    /* renamed from: -$$Nest$fgetanswerStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9851$$Nest$fgetanswerStartRow(ChatAttachAlertPollLayout r0) {
        return r0.answerStartRow;
    }

    /* renamed from: -$$Nest$fgetanswers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence[] m9852$$Nest$fgetanswers(ChatAttachAlertPollLayout r0) {
        return r0.answers;
    }

    /* renamed from: -$$Nest$fgetanswersChecks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean[] m9853$$Nest$fgetanswersChecks(ChatAttachAlertPollLayout r0) {
        return r0.answersChecks;
    }

    /* renamed from: -$$Nest$fgetanswersCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9854$$Nest$fgetanswersCount(ChatAttachAlertPollLayout r0) {
        return r0.answersCount;
    }

    /* renamed from: -$$Nest$fgetattachedMedia, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PollAttachedMediaPack m9855$$Nest$fgetattachedMedia(ChatAttachAlertPollLayout r0) {
        return r0.attachedMedia;
    }

    /* renamed from: -$$Nest$fgetcountriesList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m9856$$Nest$fgetcountriesList(ChatAttachAlertPollLayout r0) {
        return r0.countriesList;
    }

    /* renamed from: -$$Nest$fgetcurrentCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PollEditTextCell m9857$$Nest$fgetcurrentCell(ChatAttachAlertPollLayout r0) {
        return r0.currentCell;
    }

    /* renamed from: -$$Nest$fgetdescriptionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9858$$Nest$fgetdescriptionRow(ChatAttachAlertPollLayout r0) {
        return r0.descriptionRow;
    }

    /* renamed from: -$$Nest$fgetdescriptionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m9859$$Nest$fgetdescriptionString(ChatAttachAlertPollLayout r0) {
        return r0.descriptionString;
    }

    /* renamed from: -$$Nest$fgetdestroyed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9860$$Nest$fgetdestroyed(ChatAttachAlertPollLayout r0) {
        return r0.destroyed;
    }

    /* renamed from: -$$Nest$fgetemptyRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9861$$Nest$fgetemptyRow(ChatAttachAlertPollLayout r0) {
        return r0.emptyRow;
    }

    /* renamed from: -$$Nest$fgethideResults, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9862$$Nest$fgethideResults(ChatAttachAlertPollLayout r0) {
        return r0.hideResults;
    }

    /* renamed from: -$$Nest$fgethintView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView m9863$$Nest$fgethintView(ChatAttachAlertPollLayout r0) {
        return r0.hintView;
    }

    /* renamed from: -$$Nest$fgetisPremium, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9864$$Nest$fgetisPremium(ChatAttachAlertPollLayout r0) {
        return r0.isPremium;
    }

    /* renamed from: -$$Nest$fgetitemAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DefaultItemAnimator m9865$$Nest$fgetitemAnimator(ChatAttachAlertPollLayout r0) {
        return r0.itemAnimator;
    }

    /* renamed from: -$$Nest$fgetkeyboardVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9866$$Nest$fgetkeyboardVisible(ChatAttachAlertPollLayout r0) {
        return r0.keyboardVisible;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FillLastLinearLayoutManager m9867$$Nest$fgetlayoutManager(ChatAttachAlertPollLayout r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m9868$$Nest$fgetlistAdapter(ChatAttachAlertPollLayout r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m9869$$Nest$fgetlistView(ChatAttachAlertPollLayout r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetmaxAnswersCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9870$$Nest$fgetmaxAnswersCount(ChatAttachAlertPollLayout r0) {
        return r0.maxAnswersCount;
    }

    /* renamed from: -$$Nest$fgetmultipleChoise, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9871$$Nest$fgetmultipleChoise(ChatAttachAlertPollLayout r0) {
        return r0.multipleChoise;
    }

    /* renamed from: -$$Nest$fgetopenKeyboardRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m9872$$Nest$fgetopenKeyboardRunnable(ChatAttachAlertPollLayout r0) {
        return r0.openKeyboardRunnable;
    }

    /* renamed from: -$$Nest$fgetpaddingRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9873$$Nest$fgetpaddingRow(ChatAttachAlertPollLayout r0) {
        return r0.paddingRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vAllowAddingRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9874$$Nest$fgetpoll2vAllowAddingRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vAllowAddingRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vAllowRevotingRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9875$$Nest$fgetpoll2vAllowRevotingRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vAllowRevotingRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vAnonymousRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9876$$Nest$fgetpoll2vAnonymousRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vAnonymousRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vLimitByCountryListRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9877$$Nest$fgetpoll2vLimitByCountryListRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vLimitByCountryListRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vLimitByCountryRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ToggleRow m9878$$Nest$fgetpoll2vLimitByCountryRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vLimitByCountryRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vLimitDurationHideResultsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9879$$Nest$fgetpoll2vLimitDurationHideResultsRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vLimitDurationHideResultsRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vLimitDurationHideResultsRowInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9880$$Nest$fgetpoll2vLimitDurationHideResultsRowInfo(ChatAttachAlertPollLayout r0) {
        return r0.poll2vLimitDurationHideResultsRowInfo;
    }

    /* renamed from: -$$Nest$fgetpoll2vLimitDurationRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9881$$Nest$fgetpoll2vLimitDurationRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vLimitDurationRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vLimitDurationTimeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9882$$Nest$fgetpoll2vLimitDurationTimeRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vLimitDurationTimeRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vMultipleRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9883$$Nest$fgetpoll2vMultipleRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vMultipleRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vQuizRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9884$$Nest$fgetpoll2vQuizRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vQuizRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vShuffleRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9885$$Nest$fgetpoll2vShuffleRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vShuffleRow;
    }

    /* renamed from: -$$Nest$fgetpoll2vSubscribersOnlyRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ToggleRow m9886$$Nest$fgetpoll2vSubscribersOnlyRow(ChatAttachAlertPollLayout r0) {
        return r0.poll2vSubscribersOnlyRow;
    }

    /* renamed from: -$$Nest$fgetpollLimitDeadline, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9887$$Nest$fgetpollLimitDeadline(ChatAttachAlertPollLayout r0) {
        return r0.pollLimitDeadline;
    }

    /* renamed from: -$$Nest$fgetpollLimitDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9888$$Nest$fgetpollLimitDuration(ChatAttachAlertPollLayout r0) {
        return r0.pollLimitDuration;
    }

    /* renamed from: -$$Nest$fgetquestionHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9889$$Nest$fgetquestionHeaderRow(ChatAttachAlertPollLayout r0) {
        return r0.questionHeaderRow;
    }

    /* renamed from: -$$Nest$fgetquestionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9890$$Nest$fgetquestionRow(ChatAttachAlertPollLayout r0) {
        return r0.questionRow;
    }

    /* renamed from: -$$Nest$fgetquestionSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9891$$Nest$fgetquestionSectionRow(ChatAttachAlertPollLayout r0) {
        return r0.questionSectionRow;
    }

    /* renamed from: -$$Nest$fgetquestionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m9892$$Nest$fgetquestionString(ChatAttachAlertPollLayout r0) {
        return r0.questionString;
    }

    /* renamed from: -$$Nest$fgetquizOnly, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9893$$Nest$fgetquizOnly(ChatAttachAlertPollLayout r0) {
        return r0.quizOnly;
    }

    /* renamed from: -$$Nest$fgetquizPoll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9894$$Nest$fgetquizPoll(ChatAttachAlertPollLayout r0) {
        return r0.quizPoll;
    }

    /* renamed from: -$$Nest$fgetrequestFieldFocusAtPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9895$$Nest$fgetrequestFieldFocusAtPosition(ChatAttachAlertPollLayout r0) {
        return r0.requestFieldFocusAtPosition;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9896$$Nest$fgetrowCount(ChatAttachAlertPollLayout r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsettingsHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9897$$Nest$fgetsettingsHeaderRow(ChatAttachAlertPollLayout r0) {
        return r0.settingsHeaderRow;
    }

    /* renamed from: -$$Nest$fgetsettingsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9898$$Nest$fgetsettingsSectionRow(ChatAttachAlertPollLayout r0) {
        return r0.settingsSectionRow;
    }

    /* renamed from: -$$Nest$fgetshowMediaHintIndexAfterSmoothScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9899$$Nest$fgetshowMediaHintIndexAfterSmoothScroll(ChatAttachAlertPollLayout r0) {
        return r0.showMediaHintIndexAfterSmoothScroll;
    }

    /* renamed from: -$$Nest$fgetshuffleOptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9900$$Nest$fgetshuffleOptions(ChatAttachAlertPollLayout r0) {
        return r0.shuffleOptions;
    }

    /* renamed from: -$$Nest$fgetsmoothScrollToOption, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9901$$Nest$fgetsmoothScrollToOption(ChatAttachAlertPollLayout r0) {
        return r0.smoothScrollToOption;
    }

    /* renamed from: -$$Nest$fgetsolutionInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9902$$Nest$fgetsolutionInfoRow(ChatAttachAlertPollLayout r0) {
        return r0.solutionInfoRow;
    }

    /* renamed from: -$$Nest$fgetsolutionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9903$$Nest$fgetsolutionRow(ChatAttachAlertPollLayout r0) {
        return r0.solutionRow;
    }

    /* renamed from: -$$Nest$fgetsolutionRowHeader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9904$$Nest$fgetsolutionRowHeader(ChatAttachAlertPollLayout r0) {
        return r0.solutionRowHeader;
    }

    /* renamed from: -$$Nest$fgetsolutionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m9905$$Nest$fgetsolutionString(ChatAttachAlertPollLayout r0) {
        return r0.solutionString;
    }

    /* renamed from: -$$Nest$fgetsuggestEmojiPanel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SuggestEmojiView m9906$$Nest$fgetsuggestEmojiPanel(ChatAttachAlertPollLayout r0) {
        return r0.suggestEmojiPanel;
    }

    /* renamed from: -$$Nest$fgettodo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9907$$Nest$fgettodo(ChatAttachAlertPollLayout r0) {
        return r0.todo;
    }

    /* renamed from: -$$Nest$fgettopPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9908$$Nest$fgettopPadding(ChatAttachAlertPollLayout r0) {
        return r0.topPadding;
    }

    /* renamed from: -$$Nest$fgetwaitingForKeyboardOpen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9909$$Nest$fgetwaitingForKeyboardOpen(ChatAttachAlertPollLayout r0) {
        return r0.waitingForKeyboardOpen;
    }

    /* renamed from: -$$Nest$fputanswersCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9910$$Nest$fputanswersCount(ChatAttachAlertPollLayout r0, int r1) {
        r0.answersCount = r1;
    }

    /* renamed from: -$$Nest$fputcurrentCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9911$$Nest$fputcurrentCell(ChatAttachAlertPollLayout r0, PollEditTextCell r1) {
        r0.currentCell = r1;
    }

    /* renamed from: -$$Nest$fputdescriptionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9912$$Nest$fputdescriptionString(ChatAttachAlertPollLayout r0, CharSequence r1) {
        r0.descriptionString = r1;
    }

    /* renamed from: -$$Nest$fputisAnimatePopupClosing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9913$$Nest$fputisAnimatePopupClosing(ChatAttachAlertPollLayout r0, boolean r1) {
        r0.isAnimatePopupClosing = r1;
    }

    /* renamed from: -$$Nest$fputquestionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9914$$Nest$fputquestionString(ChatAttachAlertPollLayout r0, CharSequence r1) {
        r0.questionString = r1;
    }

    /* renamed from: -$$Nest$fputrequestFieldFocusAtPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9915$$Nest$fputrequestFieldFocusAtPosition(ChatAttachAlertPollLayout r0, int r1) {
        r0.requestFieldFocusAtPosition = r1;
    }

    /* renamed from: -$$Nest$fputshowMediaHintIndexAfterSmoothScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9916$$Nest$fputshowMediaHintIndexAfterSmoothScroll(ChatAttachAlertPollLayout r0, int r1) {
        r0.showMediaHintIndexAfterSmoothScroll = r1;
    }

    /* renamed from: -$$Nest$fputsmoothScrollToOption, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9917$$Nest$fputsmoothScrollToOption(ChatAttachAlertPollLayout r0, boolean r1) {
        r0.smoothScrollToOption = r1;
    }

    /* renamed from: -$$Nest$fputsolutionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9918$$Nest$fputsolutionString(ChatAttachAlertPollLayout r0, CharSequence r1) {
        r0.solutionString = r1;
    }

    /* renamed from: -$$Nest$maddNewField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9919$$Nest$maddNewField(ChatAttachAlertPollLayout r0) {
        r0.addNewField();
    }

    /* renamed from: -$$Nest$mcheckAllowedCountriesList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9920$$Nest$mcheckAllowedCountriesList(ChatAttachAlertPollLayout r0, TextCell r1, boolean r2) {
        r0.checkAllowedCountriesList(r1, r2);
    }

    /* renamed from: -$$Nest$mcheckDoneButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9921$$Nest$mcheckDoneButton(ChatAttachAlertPollLayout r0) {
        r0.checkDoneButton();
    }

    /* renamed from: -$$Nest$mcheckDurationInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9922$$Nest$mcheckDurationInfoRow(ChatAttachAlertPollLayout r0, TextCell r1, boolean r2) {
        r0.checkDurationInfoRow(r1, r2);
    }

    /* renamed from: -$$Nest$mdeletePollAnswerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9923$$Nest$mdeletePollAnswerView(ChatAttachAlertPollLayout r0, View r1, PollEditTextCell r2, boolean r3) {
        r0.deletePollAnswerView(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mhideEmojiPopup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9924$$Nest$mhideEmojiPopup(ChatAttachAlertPollLayout r0, boolean r1) {
        r0.hideEmojiPopup(r1);
    }

    /* renamed from: -$$Nest$monCellFocusChanges, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9925$$Nest$monCellFocusChanges(ChatAttachAlertPollLayout r0, PollEditTextCell r1, boolean r2) {
        r0.onCellFocusChanges(r1, r2);
    }

    /* renamed from: -$$Nest$monEmojiClicked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9926$$Nest$monEmojiClicked(ChatAttachAlertPollLayout r0, PollEditTextCell r1) {
        r0.onEmojiClicked(r1);
    }

    /* renamed from: -$$Nest$mopenAttachMenuForOptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9927$$Nest$mopenAttachMenuForOptions(ChatAttachAlertPollLayout r0, int r1) {
        r0.openAttachMenuForOptions(r1);
    }

    /* renamed from: -$$Nest$mopenAttachOrReplaceMenuForOptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9928$$Nest$mopenAttachOrReplaceMenuForOptions(ChatAttachAlertPollLayout r0, int r1) {
        r0.openAttachOrReplaceMenuForOptions(r1);
    }

    /* renamed from: -$$Nest$msetAttachedMedia, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9929$$Nest$msetAttachedMedia(ChatAttachAlertPollLayout r0, int r1, PollAttachedMedia r2) {
        r0.setAttachedMedia(r1, r2);
    }

    /* renamed from: -$$Nest$msetTextLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9930$$Nest$msetTextLeft(ChatAttachAlertPollLayout r0, View r1, int r2) {
        r0.setTextLeft(r1, r2);
    }

    /* renamed from: -$$Nest$mshowMediaHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9931$$Nest$mshowMediaHint(ChatAttachAlertPollLayout r0, int r1) {
        r0.showMediaHint(r1);
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9932$$Nest$mupdateRows(ChatAttachAlertPollLayout r0) {
        r0.updateRows();
    }

    public ChatAttachAlertPollLayout(ChatAttachAlert r23, Context r24, boolean r25, Theme.ResourcesProvider r26, Boolean r27) {
        super(r23, r24, r26);
        this.answersCount = 1;
        this.allowRevoting = true;
        this.shuffleOptions = true;
        this.allowAddingOptions = true;
        this.multipleChoise = true;
        this.allowAdding = true;
        this.allowMarking = true;
        this.requestFieldFocusAtPosition = -1;
        ToggleRow r0 = new ToggleRow(this, null);
        this.poll2vSubscribersOnlyRow = r0;
        ToggleRow r3 = new ToggleRow(this, null);
        this.poll2vLimitByCountryRow = r3;
        this.toggleRows = new ToggleRow[]{r0, r3};
        this.countriesList = new ArrayList();
        this.POLL_DURATION_OPTIONS = new int[]{3600, 10800, 28800, 86400, 259200};
        this.openKeyboardRunnable = new AnonymousClass1(this);
        this.smoothScrollToOption = false;
        this.showMediaHintIndexAfterSmoothScroll = -1;
        this.isEmojiSearchOpened = false;
        this.wasEmojiSearchOpened = false;
        Paint r32 = new Paint(1);
        this.checkboxPaint = r32;
        this.attachedMedia = new PollAttachedMediaPack();
        this.todo = r25;
        int r4 = getAnswersMaxCount();
        this.maxAnswersCount = r4;
        this.answers = new CharSequence[r4];
        this.answersChecks = new boolean[r4];
        boolean r12 = AccountInstance.getInstance(this.parentAlert.currentAccount).getUserConfig().isPremium();
        this.isPremium = r12;
        if (r27 == null) goto L5;
        boolean r42 = r27.booleanValue();
        this.quizPoll = r42;
        this.quizOnly = r42;
        this.allowAddingOptions = !r42;
        this.allowRevoting = !r42;
    L5:
        updateRows();
        r32.setColor(getThemedColor(Theme.key_telegram_color));
        this.parentAlert.sizeNotifierFrameLayout.setDelegate(this);
        ListAdapter r13 = new ListAdapter(this, r24);
        this.listAdapter = r13;
        AnonymousClass2 r6 = new AnonymousClass2(this, r24);
        this.listView = r6;
        this.iBlur3Capture = r6;
        this.iBlur3CaptureView = r6;
        this.occupyStatusBar = true;
        this.occupyNavigationBar = true;
        AnonymousClass3 r33 = new AnonymousClass3(this);
        this.itemAnimator = r33;
        r6.setItemAnimator(r33);
        this.countriesList.clear();
        r33.setSupportsChangeAnimations(false);
        r33.setDelayAnimations(false);
        r33.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r33.setDurations(350);
        r6.setClipToPadding(false);
        r6.setVerticalScrollBarEnabled(false);
        r6.setSections(true);
        AnonymousClass4 r02 = new AnonymousClass4(this, r24, 1, false, AndroidUtilities.dp(65.0f) + AndroidUtilities.statusBarHeight, r6);
        this.layoutManager = r02;
        r6.setLayoutManager(r02);
        r02.setSkipFirstItem();
        new ItemTouchHelper(new TouchHelperCallback(this)).attachToRecyclerView(r6);
        addView(r6, LayoutHelper.createFrame(-1, -1, 51));
        r6.setPreserveFocusAfterLayout(true);
        r6.setAdapter(r13);
        r6.setOnItemClickListener(new ChatAttachAlertPollLayout$$ExternalSyntheticLambda6(this, r26, r23, r24));
        r6.setOnScrollListener(new AnonymousClass6(this));
        HintView r03 = new HintView(r24, 4);
        this.hintView = r03;
        r03.setAlpha(0.0f);
        this.hintView.setVisibility(4);
        addView(this.hintView, LayoutHelper.createFrame(-2, -2.0f, 51, 19.0f, 0.0f, 19.0f, 0.0f));
        this.MAX_CAPTION_LENGTH = MessagesController.getInstance(this.parentAlert.currentAccount).config.pollCaptionLengthMax.get();
        this.webPageLoader = new WebPageLoader(this.parentAlert.currentAccount);
        NotificationCenter.getInstance(this.parentAlert.currentAccount).addObserver(this, NotificationCenter.didReceivedWebpagesInUpdates);
        if (r12 == false) goto L8;
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        AnonymousClass7 r04 = new AnonymousClass7(this, r24, this.parentAlert.currentAccount, null, r26);
        this.suggestEmojiPanel = r04;
        r04.forbidCopy();
        this.suggestEmojiPanel.forbidSetAsStatus();
        this.suggestEmojiPanel.setHorizontalPadding(AndroidUtilities.dp(24.0f));
        addView(this.suggestEmojiPanel, LayoutHelper.createFrame(-2, 160, 51));
    L8:
        this.keyboardNotifier = new KeyboardNotifier(this.parentAlert.sizeNotifierFrameLayout, null);
        checkDoneButton();
    }

    private void addNewField() {
        resetSuggestEmojiPanel();
        this.listView.setItemAnimator(this.itemAnimator);
        boolean[] r0 = this.answersChecks;
        int r1 = this.answersCount;
        r0[r1] = false;
        int r12 = r1 + 1;
        this.answersCount = r12;
        if (r12 != this.answers.length) goto L5;
        this.listAdapter.notifyItemRemoved(this.addAnswerRow);
    L5:
        this.listAdapter.notifyItemInserted(this.addAnswerRow);
        updateRows();
        this.requestFieldFocusAtPosition = (this.answerStartRow + this.answersCount) - 1;
        this.listAdapter.notifyItemChanged(this.answerSectionRow);
        this.listAdapter.notifyItemChanged(this.emptyRow);
    }

    private void animateEmojiViewTranslationY(float r3, float r4) {
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r0.addUpdateListener(new ChatAttachAlertPollLayout$$ExternalSyntheticLambda1(this, r3, r4));
        r0.addListener(new AnonymousClass11(this, r4));
        r0.setDuration(250);
        r0.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r0.start();
    }

    private void checkAllowAddingOptionsRow() {
        if (this.quizPoll == false) goto L5;
    L7:
        boolean r0 = false;
    L8:
        if (r0 == true) goto L10;
        this.allowAddingOptions = false;
    L10:
        int r3 = this.poll2vAllowAddingRow;
        if (r3 >= 0) goto L13;
        return;
    L13:
        RecyclerView.ViewHolder r32 = this.listView.findViewHolderForAdapterPosition(r3);
        if (r32 != null) goto L17;
        this.listAdapter.notifyItemChanged(this.poll2vAllowAddingRow);
        return;
    L17:
        PollCreateCheckCell r5 = (PollCreateCheckCell) r32.itemView;
        if (r0 == true) goto L20;
        r5.setChecked(false);
    L20:
        r5.getCheckBox().setIconVisible(!r0, true);
        return;
    L5:
        if (this.anonymousPoll == true) goto L7;
        r0 = true;
        goto L8
    }

    private void checkAllowedCountriesList(TextCell r3, boolean r4) {
        r3.setTextAndValue(LocaleController.getString(R.string.PollV2AllowedCountries), formatCountriesList(this.countriesList), r4, true);
    }

    private boolean checkDiscard() {
        int r1 = 0;
        if (TextUtils.isEmpty(getFixedString(this.questionString)) == true) goto L5;
    L11:
        boolean r0 = false;
    L12:
        if (r0 == true) goto L14;
    L19:
        if (r0 == true) goto L29;
        AlertDialog.Builder r12 = new AlertDialog.Builder(this.parentAlert.baseFragment.getParentActivity());
        if (this.todo == false) goto L23;
        int r2 = R.string.CancelTodoAlertTitle;
    L24:
        r12.setTitle(LocaleController.getString(r2));
        if (this.todo == false) goto L27;
        int r22 = R.string.CancelTodoAlertText;
    L28:
        r12.setMessage(LocaleController.getString(r22));
        r12.setPositiveButton(LocaleController.getString(R.string.PassportDiscard), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda0(this));
        r12.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r12.show();
        goto L29
    L27:
        r22 = R.string.CancelPollAlertText;
        goto L28
    L23:
        r2 = R.string.CancelPollAlertTitle;
    L29:
        return r0;
    L14:
        if (r1 >= this.answersCount) goto L19;
        r0 = TextUtils.isEmpty(getFixedString(this.answers[r1]));
        if (r0 == false) goto L19;
        r1 = r1 + 1;
        goto L14
    L5:
        if (TextUtils.isEmpty(getFixedString(this.descriptionString)) == false) goto L11;
        if (TextUtils.isEmpty(getFixedString(this.solutionString)) == false) goto L11;
        if (this.attachedMedia.medias.size() != 0) goto L11;
        r0 = true;
        goto L12
    }

    private void checkDoneButton() {
        if (this.quizPoll == false) goto L13;
        int r0 = 0;
        int r2 = 0;
    L6:
        if (r0 >= this.answersChecks.length) goto L15;
        if (TextUtils.isEmpty(getFixedString(this.answers[r0])) == true) goto L12;
        if (this.answersChecks[r0] == false) goto L12;
        r2 = r2 + 1;
    L12:
        r0 = r0 + 1;
    L15:
        if (this.todo == false) goto L17;
        int r02 = getMessagesController().todoTitleLengthMax;
    L19:
        if (this.todo == false) goto L21;
        int r3 = getMessagesController().todoItemLengthMax;
    L23:
        if (TextUtils.isEmpty(getFixedString(this.descriptionString)) == true) goto L28;
        if (this.descriptionString.length() <= this.MAX_CAPTION_LENGTH) goto L28;
    L26:
        boolean r03 = false;
    L38:
        int r4 = 0;
        int r6 = 0;
        boolean r7 = false;
    L39:
        CharSequence[] r8 = this.answers;
        if (r4 >= r8.length) goto L48;
        if (TextUtils.isEmpty(getFixedString(r8[r4])) == true) goto L47;
        if (this.answers[r4].length() > r3) goto L45;
        r6 = r6 + 1;
        r7 = true;
        goto L47
    L45:
        r6 = 0;
        r7 = true;
    L47:
        r4 = r4 + 1;
    L48:
        if (r6 >= 1) goto L50;
    L52:
        r03 = false;
    L54:
        if (TextUtils.isEmpty(this.solutionString) == true) goto L56;
    L64:
        this.allowNesterScroll = false;
    L65:
        this.parentAlert.setAllowNestedScroll(this.allowNesterScroll);
        this.doneItemEnabled = r03;
        this.parentAlert.updateDoneItemEnabled();
        return;
    L56:
        if (TextUtils.isEmpty(this.questionString) == false) goto L64;
        if (TextUtils.isEmpty(this.descriptionString) == false) goto L64;
        if (r7 == true) goto L64;
        if (this.attachedMedia.medias.size() > 0) goto L64;
        this.allowNesterScroll = true;
        goto L65
    L50:
        if (this.quizPoll == false) goto L54;
        if (r2 >= 1) goto L54;
    L28:
        if (TextUtils.isEmpty(getFixedString(this.solutionString)) == true) goto L33;
        if (this.solutionString.length() > 200) goto L26;
    L33:
        if (TextUtils.isEmpty(getFixedString(this.questionString)) == true) goto L26;
        if (this.questionString.length() > r02) goto L26;
        r03 = true;
        goto L38
    L21:
        r3 = 100;
        goto L23
    L17:
        r02 = 255;
        goto L19
    L13:
        r2 = 0;
        goto L15
    }

    private void checkDurationInfoRow(TextCell r5, boolean r6) {
        if (this.pollLimitDeadline == 0) goto L7;
        r5.setTextAndValue(LocaleController.getString(R.string.PollV2PollEnds), LocaleController.formatShortDateTime(this.pollLimitDeadline), r6, false);
        return;
    L7:
        if (this.pollLimitDuration == 0) goto L10;
        r5.setTextAndValue(LocaleController.getString(R.string.PollV2PollDuration), LocaleController.formatPluralString("Hours", this.pollLimitDuration / 3600, new Object[0]), r6, false);
        return;
    L10:
        r5.setTextAndValue(LocaleController.getString(R.string.PollV2PollEnds), null, r6, false);
    }

    private void checkPollLinkMedia(PollAttachedMediaLink r3, boolean r4) {
        boolean r0 = this.webPageLoader.isLoading(r3.url);
        r3.setWebPage(this.webPageLoader.getWebPage(r3.url), r0, r4);
    }

    private void collapseSearchEmojiView() {
        if (this.isEmojiSearchOpened == false) goto L6;
        this.emojiView.closeSearch(false);
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.emojiView.getLayoutParams();
        r0.height -= AndroidUtilities.dp(120.0f);
        this.emojiView.setLayoutParams(r0);
        this.emojiPadding = r0.height;
        this.wasEmojiSearchOpened = this.isEmojiSearchOpened;
        this.isEmojiSearchOpened = false;
        animateEmojiViewTranslationY(-AndroidUtilities.dp(120.0f), 0.0f);
        return;
    }

    private void createEmojiView() {
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L8;
        if (r0.currentAccount == UserConfig.selectedAccount) goto L8;
        this.parentAlert.sizeNotifierFrameLayout.removeView(r0);
        this.emojiView = null;
    L8:
        if (this.emojiView == null) goto L10;
        return;
    L10:
        EmojiView r1 = new EmojiView(null, true, false, false, getContext(), true, null, null, true, this.resourcesProvider, false);
        this.emojiView = r1;
        r1.emojiCacheType = 3;
        r1.shouldLightenBackground = false;
        r1.fixBottomTabContainerTranslation = false;
        r1.setShouldDrawBackground(false);
        this.emojiView.allowEmojisForNonPremium(false);
        this.emojiView.setVisibility(8);
        if (AndroidUtilities.isTablet() == false) goto L13;
        this.emojiView.setForseMultiwindowLayout(true);
    L13:
        this.emojiView.setDelegate(new AnonymousClass10(this));
        this.parentAlert.sizeNotifierFrameLayout.addView(this.emojiView);
        this.emojiView.setBottomInset(AndroidUtilities.navigationBarHeight);
    }

    private void deletePollAnswerView(View r8, PollEditTextCell r9, boolean r10) {
        if (r8.getTag() != null) goto L52;
        r8.setTag(1);
        RecyclerView.ViewHolder r1 = this.listView.findContainingViewHolder(r9);
        if (r1 == null) goto L53;
        int r12 = r1.getAdapterPosition();
        if (r12 == (-1)) goto L54;
        int r3 = r12 - this.answerStartRow;
        if (this.attachedMedia.get(r3) == null) goto L14;
        boolean r4 = true;
    L16:
        if (r10 == false) goto L32;
        if (r4 == false) goto L32;
        BaseFragment r102 = this.parentAlert.baseFragment;
        if (r102 == null) goto L32;
        AlertDialog.Builder r0 = new AlertDialog.Builder(r102.getParentActivity(), this.resourcesProvider);
        if (this.quizPoll == true) goto L23;
        int r103 = R.string.DiscardPollOptionWithMediaAlertTitle;
    L24:
        AlertDialog.Builder r104 = r0.setTitle(LocaleController.getString(r103));
        if (this.quizPoll == true) goto L27;
        int r02 = R.string.DiscardPollOptionWithMediaMessage;
    L28:
        AlertDialog r7 = r104.setMessage(LocaleController.getString(r02)).setPositiveButton(LocaleController.getString(R.string.Delete), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda16(this, r8, r9)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).setOnCancelListener(new ChatAttachAlertPollLayout$$ExternalSyntheticLambda17(r8)).create();
        r7.show();
        TextView r72 = (TextView) r7.getButton(-1);
        if (r72 == null) goto L51;
        r72.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L51:
        return;
    L27:
        r02 = R.string.DiscardQuizOptionWithMediaMessage;
        goto L28
    L23:
        r103 = R.string.DiscardQuizOptionWithMediaAlertTitle;
    L32:
        this.attachedMedia.removeAnswerAndShift(r3);
        this.listView.setItemAnimator(this.itemAnimator);
        this.listAdapter.notifyItemRemoved(r12);
        CharSequence[] r82 = this.answers;
        int r105 = r3 + 1;
        System.arraycopy(r82, r105, r82, r3, (r82.length - 1) - r3);
        boolean[] r83 = this.answersChecks;
        System.arraycopy(r83, r105, r83, r3, (r83.length - 1) - r3);
        CharSequence[] r84 = this.answers;
        r84[r84.length - 1] = null;
        boolean[] r106 = this.answersChecks;
        r106[r106.length - 1] = false;
        int r107 = this.answersCount - 1;
        this.answersCount = r107;
        if (r107 != (r84.length - 1)) goto L35;
        this.listAdapter.notifyItemInserted((this.answerStartRow + r84.length) - 1);
    L35:
        RecyclerView.ViewHolder r85 = this.listView.findViewHolderForAdapterPosition(r12 - 1);
        EditTextBoldCursor r92 = r9.getTextView();
        if (r85 == null) goto L41;
        View r86 = r85.itemView;
        if ((r86 instanceof PollEditTextCell) == false) goto L41;
        ((PollEditTextCell) r86).getTextView().requestFocus();
    L46:
        r92.clearFocus();
        checkDoneButton();
        updateRows();
        SuggestEmojiView r87 = this.suggestEmojiPanel;
        if (r87 == null) goto L49;
        r87.forceClose();
        this.suggestEmojiPanel.setDelegate(null);
    L49:
        this.listAdapter.notifyItemChanged(this.answerSectionRow);
        this.listAdapter.notifyItemChanged(this.emptyRow);
        return;
    L41:
        if (r92.isFocused() == false) goto L44;
        AndroidUtilities.hideKeyboard(r92);
        hideEmojiPopup(true);
        goto L46
    L44:
        if (this.isEmojiSearchOpened == false) goto L46;
        hideEmojiPopup(true);
        goto L46
    L14:
        r4 = false;
        goto L16
    L54:
        return;
    L53:
        return;
    }

    private static String formatCountriesList(ArrayList<String> r3) {
        if (r3.isEmpty() == false) goto L7;
        return LocaleController.getString(R.string.SearchCountriesSelect);
    L7:
        if (r3.size() != 1) goto L11;
        return LocaleController.getCountryName(r3.get(0));
    L11:
        return LocaleController.formatPluralString("PollV2AllowedCountriesListManyP", r3.size(), new Object[0]);
    }

    public static int getAllowedLayoutsForIndex(int r1) {
        if (r1 != (-2)) goto L5;
        return 74;
    L5:
        if (r1 == (-3)) goto L11;
        return 41026;
    L11:
        return 74;
    }

    private int getAnswersMaxCount() {
        if (this.todo == false) goto L7;
        return getMessagesController().todoItemsMax;
    L7:
        return getMessagesController().config.pollAnswersMax.get();
    }

    private int getCurrentAccount() {
        ChatAttachAlert r0 = this.parentAlert;
        if (r0 == null) goto L7;
        return r0.currentAccount;
    L7:
        return UserConfig.selectedAccount;
    }

    public static CharSequence getFixedString(CharSequence r6) {
        if (TextUtils.isEmpty(r6) == false) goto L5;
        return r6;
    L5:
        CharSequence r62 = AndroidUtilities.getTrimmedString(r6);
    L7:
        if (TextUtils.indexOf(r62, "\n\n\n") < 0) goto L10;
        r62 = TextUtils.replace(r62, new String[]{"\n\n\n"}, new CharSequence[]{"\n\n"});
    L10:
        if (TextUtils.indexOf(r62, "\n\n\n") != 0) goto L12;
        r62 = TextUtils.replace(r62, new String[]{"\n\n\n"}, new CharSequence[]{"\n\n"});
        goto L10
    L12:
        return r62;
    }

    private MessagesController getMessagesController() {
        return MessagesController.getInstance(getCurrentAccount());
    }

    public static int getStartLayoutForMedia(PollAttachedMedia r1) {
        if ((r1 instanceof PollAttachedMediaMusic) == false) goto L7;
        return 3;
    L7:
        if ((r1 instanceof PollAttachedMediaFile) == false) goto L11;
        return 4;
    L11:
        if ((r1 instanceof PollAttachedMediaSticker) == false) goto L19;
        if (((PollAttachedMediaSticker) r1).isEmoji == false) goto L16;
        return 14;
    L16:
        return 13;
    L19:
        if ((r1 instanceof PollAttachedMediaLocation) == false) goto L23;
        return 6;
    L23:
        if ((r1 instanceof PollAttachedMediaLink) == false) goto L26;
        return 15;
    L26:
        return 1;
    }

    private void hideEmojiPopup(boolean r4) {
        if (this.isPremium == true) goto L6;
        return;
    L6:
        if (this.emojiViewVisible == false) goto L11;
        this.emojiView.scrollEmojiToTop();
        this.emojiView.closeSearch(false);
        if (r4 == false) goto L10;
        this.emojiView.hideSearchKeyboard();
    L10:
        this.isEmojiSearchOpened = false;
        showEmojiPopup(0);
    L11:
        if (r4 == false) goto L20;
        EmojiView r42 = this.emojiView;
        if (r42 != null) goto L15;
    L18:
        hideEmojiView();
        return;
    L15:
        if (r42.getVisibility() != 0) goto L18;
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{0.0f, this.emojiView.getMeasuredHeight()});
        r43.addUpdateListener(new ChatAttachAlertPollLayout$$ExternalSyntheticLambda3(this));
        this.isAnimatePopupClosing = true;
        r43.addListener(new AnonymousClass9(this));
        r43.setDuration(250);
        r43.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r43.start();
        return;
    }

    private /* synthetic */ void lambda$animateEmojiViewTranslationY$12(float r1, float r2, ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(AndroidUtilities.lerp(r1, r2, r32));
    }

    private /* synthetic */ void lambda$checkDiscard$9(AlertDialog r1, int r2) {
        this.parentAlert.dismiss();
    }

    private /* synthetic */ void lambda$deletePollAnswerView$13(View r1, PollEditTextCell r2, AlertDialog r3, int r4) {
        r1.setTag(null);
        deletePollAnswerView(r1, r2, false);
    }

    private /* synthetic */ void lambda$hideEmojiPopup$11(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(r12);
    }

    private /* synthetic */ void lambda$new$0(int r2, View r3) {
        this.pollLimitDeadline = 0;
        this.pollLimitDuration = r2;
        if ((r3 instanceof TextCell) == false) goto L6;
        checkDurationInfoRow((TextCell) r3, true);
        return;
    L6:
        this.listAdapter.notifyItemChanged(this.poll2vLimitDurationTimeRow);
    }

    private /* synthetic */ void lambda$new$1(View r1, boolean r2, int r3, int r4) {
        if (r2 == false) goto L9;
        this.pollLimitDeadline = r3;
        this.pollLimitDuration = 0;
        if ((r1 instanceof TextCell) == false) goto L7;
        checkDurationInfoRow((TextCell) r1, true);
        return;
    L7:
        this.listAdapter.notifyItemChanged(this.poll2vLimitDurationTimeRow);
        return;
    }

    private /* synthetic */ void lambda$new$3(Context r9, View r10, Theme.ResourcesProvider r11) {
        AlertsCreator.createPollCloseDatePickerDialog(r9, this.pollLimitDeadline, new ChatAttachAlertPollLayout$$ExternalSyntheticLambda12(this, r10), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda13(), new AlertsCreator.ScheduleDatePickerColors(r11), r11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$new$4(Theme.ResourcesProvider r7, ChatAttachAlert r8, Context r9, View r10, int r11) {
        if (r11 != this.poll2vLimitByCountryListRow) goto L6;
        CountrySelectBottomSheet r82 = new CountrySelectBottomSheet(getContext(), r7);
        r82.setListener(new AnonymousClass5(this));
        r82.prepare(this.countriesList);
        r82.show();
        return;
    L6:
        int r1 = 0;
        if (r11 != this.poll2vLimitDurationTimeRow) goto L15;
        ItemOptions r83 = ItemOptions.makeOptions(r8.container, r7, r10);
        int r112 = 0;
    L9:
        int[] r0 = this.POLL_DURATION_OPTIONS;
        if (r112 >= r0.length) goto L12;
        int r02 = r0[r112];
        TimerDrawable r2 = TimerDrawable.getTtlIcon(r02);
        r2.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), PorterDuff.Mode.SRC_IN));
        r83.add(r2, LocaleController.formatPluralString("Hours", r02 / 3600, new Object[0]), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda9(this, r02, r10));
        r112 = r112 + 1;
        goto L9
    L12:
        r83.add(R.drawable.msg_customize, LocaleController.getString(R.string.PollV2PollDurationOptionCustom), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda10(this, r9, r10, r7));
        r83.setDrawScrim(false);
        r83.setDimAlpha(0);
        r83.show();
        return;
    L15:
        if (r11 != this.addAnswerRow) goto L18;
        addNewField();
        return;
    L18:
        boolean r72 = r10 instanceof TextCheckCell;
        if (r72 == false) goto L21;
    L22:
        boolean r84 = this.quizPoll;
        SuggestEmojiView r92 = this.suggestEmojiPanel;
        if (r92 == null) goto L25;
        r92.forceClose();
    L25:
        ToggleRow[] r93 = this.toggleRows;
        int r03 = r93.length;
        int r22 = 0;
    L27:
        if (r22 >= r03) goto L39;
        ToggleRow r4 = r93[r22];
        if (r11 == r4.row) goto L30;
        r22 = r22 + 1;
        goto L27
    L30:
        boolean r94 = r4.checked;
        boolean r04 = !r94;
        r4.checked = r04;
        if (r11 != this.poll2vLimitByCountryRow.row) goto L37;
        this.listView.setItemAnimator(this.itemAnimator);
        this.poll2vLimitByCountryRow.setDivider(r04);
        ToggleRow r23 = this.poll2vLimitByCountryRow;
        if (r94 == true) goto L35;
        r23.addRows(1);
    L36:
        updateRows();
        goto L37
    L35:
        r23.removeRows(1);
    L37:
        boolean r95 = true;
        boolean r05 = r04;
    L40:
        if (r95 == true) goto L157;
        if (r11 != this.poll2vAnonymousRow) goto L45;
        r05 = this.anonymousPoll;
        this.anonymousPoll = !r05;
        checkAllowAddingOptionsRow();
        goto L157
    L45:
        int r96 = this.allowAddingRow;
        if (r11 != r96) goto L49;
        r05 = !this.allowAdding;
        this.allowAdding = r05;
        goto L157
    L49:
        if (r11 != this.poll2vAllowAddingRow) goto L57;
        if (this.quizPoll == false) goto L53;
    L55:
        r05 = this.allowAddingOptions;
        goto L157
    L53:
        if (this.anonymousPoll == true) goto L55;
        this.allowAddingOptions = !this.allowAddingOptions;
        goto L55
    L57:
        if (r11 != this.poll2vShuffleRow) goto L60;
        r05 = !this.shuffleOptions;
        this.shuffleOptions = r05;
        goto L157
    L60:
        if (r11 != this.poll2vLimitDurationRow) goto L85;
        if (this.pollLimitDuration == 0) goto L64;
    L73:
        this.pollLimitDuration = 0;
        this.pollLimitDeadline = 0;
        int r97 = this.poll2vLimitDurationTimeRow;
        updateRows();
        this.listView.setItemAnimator(this.itemAnimator);
        this.listAdapter.notifyItemRangeRemoved(r97, 3);
        RecyclerView.ViewHolder r98 = this.listView.findViewHolderForAdapterPosition(this.poll2vLimitDurationRow);
        if (r98 == null) goto L79;
        View r99 = r98.itemView;
        if ((r99 instanceof PollCreateCheckCell) == false) goto L79;
        ((PollCreateCheckCell) r99).setDivider(false);
    L79:
        if (this.pollLimitDuration == 0) goto L81;
    L82:
        r1 = 1;
    L83:
        r05 = r1;
        goto L157
    L81:
        if (this.pollLimitDeadline == 0) goto L83;
    L64:
        if (this.pollLimitDeadline != 0) goto L73;
        this.pollLimitDuration = 86400;
        this.pollLimitDeadline = 0;
        int r910 = this.poll2vLimitDurationTimeRow;
        updateRows();
        if (r910 >= 0) goto L79;
        RecyclerView.ViewHolder r911 = this.listView.findViewHolderForAdapterPosition(this.poll2vLimitDurationRow);
        if (r911 == null) goto L72;
        View r912 = r911.itemView;
        if ((r912 instanceof PollCreateCheckCell) == false) goto L72;
        ((PollCreateCheckCell) r912).setDivider(true);
    L72:
        this.listView.setItemAnimator(this.itemAnimator);
        this.listAdapter.notifyItemRangeInserted(this.poll2vLimitDurationTimeRow, 3);
        goto L79
    L85:
        if (r11 != this.poll2vAllowRevotingRow) goto L88;
        r05 = !this.allowRevoting;
        this.allowRevoting = r05;
        goto L157
    L88:
        if (r11 != this.allowMarkingRow) goto L97;
        r05 = !this.allowMarking;
        this.allowMarking = r05;
        updateRows();
        int r12 = this.allowAddingRow;
        if (r12 < 0) goto L93;
        if (r96 >= 0) goto L93;
        this.listView.setItemAnimator(this.itemAnimator);
        this.listAdapter.notifyItemInserted(this.allowAddingRow);
    L93:
        if (r96 < 0) goto L157;
        if (r12 >= 0) goto L157;
        this.listView.setItemAnimator(this.itemAnimator);
        this.listAdapter.notifyItemRemoved(r96);
        goto L157
    L97:
        if (r11 != this.poll2vMultipleRow) goto L118;
        boolean r913 = this.multipleChoise;
        r05 = !r913;
        this.multipleChoise = r05;
        if (r913 == true) goto L101;
    L111:
        int r914 = this.listView.getChildCount();
    L112:
        if (r1 >= r914) goto L157;
        RecyclerListView r24 = this.listView;
        RecyclerView.ViewHolder r25 = r24.getChildViewHolder(r24.getChildAt(r1));
        if (r25.getItemViewType() != 5) goto L116;
        ((PollEditTextCell) r25.itemView).setCheckboxMultiselect(this.multipleChoise, true);
    L116:
        r1 = r1 + 1;
        goto L112
    L101:
        if (this.quizPoll == false) goto L111;
        int r915 = 0;
        boolean r26 = false;
    L103:
        boolean[] r42 = this.answersChecks;
        if (r915 >= r42.length) goto L111;
        if (r26 == false) goto L108;
        r42[r915] = false;
    L110:
        r915 = r915 + 1;
        r26 = r26;
        goto L103
    L108:
        if (r42[r915] == false) goto L110;
        r26 = true;
        goto L110
    L118:
        if (r11 != this.poll2vLimitDurationHideResultsRow) goto L121;
        r05 = !this.hideResults;
        this.hideResults = r05;
        goto L157
    L121:
        if (r11 != this.poll2vQuizRow) goto L157;
        if (this.quizOnly == true) goto L204;
        this.listView.setItemAnimator(this.itemAnimator);
        r05 = !this.quizPoll;
        this.quizPoll = r05;
        int r916 = this.solutionRowHeader;
        updateRows();
        boolean r27 = this.quizPoll;
        ListAdapter r5 = this.listAdapter;
        if (r27 == false) goto L128;
        r5.notifyItemRangeInserted(this.solutionRowHeader, 3);
    L129:
        this.listAdapter.notifyItemChanged(this.emptyRow);
        if (this.quizPoll == false) goto L137;
        this.allowRevoting = false;
        int r917 = this.poll2vAllowRevotingRow;
        if (r917 < 0) goto L143;
        RecyclerView.ViewHolder r918 = this.listView.findViewHolderForAdapterPosition(r917);
        if (r918 == null) goto L136;
        ((PollCreateCheckCell) r918.itemView).setChecked(false);
        goto L143
    L136:
        this.listAdapter.notifyItemChanged(this.poll2vAllowRevotingRow);
    L143:
        checkAllowAddingOptionsRow();
        if (this.quizPoll == false) goto L157;
        if (this.multipleChoise == true) goto L157;
        int r919 = 0;
        boolean r28 = false;
    L148:
        boolean[] r43 = this.answersChecks;
        if (r919 >= r43.length) goto L157;
        if (r28 == false) goto L153;
        r43[r919] = false;
    L155:
        r919 = r919 + 1;
        r28 = r28;
        goto L148
    L153:
        if (r43[r919] == false) goto L155;
        r28 = true;
        goto L155
    L137:
        int r920 = this.poll2vAllowRevotingRow;
        if (r920 < 0) goto L143;
        if (this.listView.findViewHolderForAdapterPosition(r920) != null) goto L143;
        this.listAdapter.notifyItemChanged(this.poll2vAllowRevotingRow);
        goto L143
    L128:
        r5.notifyItemRangeRemoved(r916, 3);
        goto L129
    L204:
        return;
    L157:
        if (this.hintShowed == true) goto L159;
    L161:
        this.listView.getChildCount();
        int r921 = this.answerStartRow;
    L163:
        if (r921 >= (this.answerStartRow + this.answersCount)) goto L176;
        RecyclerView.ViewHolder r13 = this.listView.findViewHolderForAdapterPosition(r921);
        if (r13 == null) goto L175;
        View r14 = r13.itemView;
        if ((r14 instanceof PollEditTextCell) == false) goto L175;
        PollEditTextCell r15 = (PollEditTextCell) r14;
        r15.setShowCheckBox(this.quizPoll, true);
        r15.setChecked(this.answersChecks[r921 - this.answerStartRow], r84);
        if (r15.getTop() <= AndroidUtilities.dp(40.0f)) goto L175;
        if (r11 != this.poll2vQuizRow) goto L175;
        if (this.hintShowed == true) goto L175;
        this.hintView.setText(LocaleController.getString(R.string.PollTapToSelect));
        this.hintView.showForView(r15.getCheckBox(), true);
        this.hintShowed = true;
    L175:
        r921 = r921 + 1;
        goto L163
    L176:
        if (r72 == false) goto L179;
        ((TextCheckCell) r10).setChecked(r05);
    L181:
        checkDoneButton();
        return;
    L179:
        if ((r10 instanceof PollCreateCheckCell) == false) goto L181;
        ((PollCreateCheckCell) r10).setChecked(r05);
        goto L181
    L159:
        if (this.quizPoll == true) goto L161;
        this.hintView.hide();
        goto L161
    L39:
        r95 = false;
        r05 = 0;
        goto L40
    L21:
        if ((r10 instanceof PollCreateCheckCell) == true) goto L22;
    }

    private /* synthetic */ void lambda$onPollDoneButtonClick$7(TLRPC.TL_messageMediaPoll r10, ArrayList r11, Long r12, boolean r13, int r14, int r15) {
        this.delegate.sendPoll(r10, this.descriptionString, this.attachedMedia, r11, r13, r14, r12.longValue());
        this.parentAlert.dismiss(true);
    }

    private /* synthetic */ void lambda$onPollDoneButtonClick$8(ChatActivity r13, TLRPC.TL_messageMediaPoll r14, ArrayList r15, Long r16) {
        if (r13.isInScheduleMode() == false) goto L6;
        AlertsCreator.createScheduleDatePickerDialog(r13.getParentActivity(), r13.getDialogId(), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda8(this, r14, r15, r16));
        return;
    L6:
        this.delegate.sendPoll(r14, this.descriptionString, this.attachedMedia, r15, true, 0, r16.longValue());
        this.parentAlert.dismiss(true);
    }

    private /* synthetic */ void lambda$onTodoDoneButtonClick$5(TLRPC.TL_messageMediaToDo r10, Long r11, boolean r12, int r13, int r14) {
        this.delegate.sendPoll(r10, null, null, null, r12, r13, r11.longValue());
        this.parentAlert.dismiss(true);
    }

    private /* synthetic */ void lambda$onTodoDoneButtonClick$6(ChatActivity r13, TLRPC.TL_messageMediaToDo r14, Long r15) {
        if (r13.isInScheduleMode() == false) goto L6;
        AlertsCreator.createScheduleDatePickerDialog(r13.getParentActivity(), r13.getDialogId(), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda11(this, r14, r15));
        return;
    L6:
        this.delegate.sendPoll(r14, null, null, null, true, 0, r15.longValue());
        this.parentAlert.dismiss(true);
    }

    private /* synthetic */ void lambda$openAttachMenuForOptions$23(int r1, PollAttachedMedia r2) {
        setAttachedMedia(r1, r2);
    }

    private /* synthetic */ void lambda$openAttachMenuForOptions$24() {
        this.currentAttachAlertIndex = -1;
        this.currentAttachAlert = null;
    }

    private /* synthetic */ void lambda$openEditOrReplaceMenu$17(int r2, String r3) {
        setAttachedMedia(r2, new PollAttachedMediaLink(r3));
    }

    private /* synthetic */ void lambda$openEditOrReplaceMenu$18(int r2) {
        setAttachedMedia(r2, null);
    }

    private /* synthetic */ void lambda$setAttachedMedia$25(PollAttachedMedia r1, TLRPC.WebPage r2, TLObject r3) {
        checkPollLinkMedia((PollAttachedMediaLink) r1, true);
    }

    private /* synthetic */ void lambda$showEmojiPopup$10(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(r12);
    }

    private /* synthetic */ void lambda$showOptionsForDrawable$19(int r1) {
        openAttachMenuForOptions(r1);
    }

    private /* synthetic */ void lambda$showOptionsForDrawable$20(int r2) {
        setAttachedMedia(r2, null);
    }

    private int mediaIndexToAdapterPosition(int r2) {
        if (r2 != (-2)) goto L7;
        return this.descriptionRow;
    L7:
        if (r2 == (-3)) goto L9;
        int r0 = this.answerStartRow;
        if (r0 < 0) goto L17;
        if (r2 >= 0) goto L14;
        return -1;
    L14:
        if (r2 < this.answersCount) goto L16;
        return -1;
    L16:
        return r0 + r2;
    L17:
        return -1;
    L9:
        return this.solutionRow;
    }

    private void onCellFocusChanges(PollEditTextCell r4, boolean r5) {
        if (this.isPremium == false) goto L20;
        if (r5 == true) goto L6;
        return;
    L6:
        if (this.currentCell == r4) goto L8;
    L12:
        PollEditTextCell r52 = this.currentCell;
        this.currentCell = r4;
        r4.setEmojiButtonVisibility(true);
        ChatActivityEnterViewAnimatedIconView r1 = r4.getEmojiButton();
        ChatActivityEnterViewAnimatedIconView.State r2 = ChatActivityEnterViewAnimatedIconView.State.SMILE;
        r1.setState(r2, false);
        updateSuggestEmojiPanelDelegate(this.listView.findContainingViewHolder(r4));
        if (r52 == null) goto L22;
        if (r52 != r4) goto L16;
        return;
    L16:
        if (this.emojiViewVisible == false) goto L18;
        collapseSearchEmojiView();
        hideEmojiPopup(false);
        openKeyboardInternal();
    L18:
        r52.setEmojiButtonVisibility(false);
        r52.getEmojiButton().setState(r2, false);
        return;
    L22:
        return;
    L8:
        if (this.emojiViewVisible == false) goto L12;
        if (this.isEmojiSearchOpened == false) goto L12;
        collapseSearchEmojiView();
        this.emojiViewVisible = false;
        goto L12
    }

    private void onEmojiClicked(PollEditTextCell r1) {
        this.currentCell = r1;
        if (this.emojiViewVisible == false) goto L6;
        collapseSearchEmojiView();
        openKeyboardInternal();
        return;
    L6:
        showEmojiPopup(1);
    }

    private void onPollDoneButtonClick() {
        int r1 = 0;
        if (this.quizPoll == true) goto L5;
    L18:
        int r0 = 0;
    L19:
        CharSequence[] r2 = this.answers;
        if (r0 >= r2.length) goto L28;
        if (TextUtils.isEmpty(getFixedString(r2[r0])) == false) goto L27;
        if (this.attachedMedia.get(r0) == null) goto L27;
        this.smoothScrollToOption = true;
        this.showMediaHintIndexAfterSmoothScroll = r0;
        this.listView.smoothScrollToPosition(this.answerStartRow + r0);
        return;
    L27:
        r0 = r0 + 1;
        goto L19
    L28:
        CharSequence[] r22 = {getFixedString(this.questionString)};
        ArrayList<TLRPC.MessageEntity> r02 = MediaDataController.getInstance(this.parentAlert.currentAccount).getEntities(r22, true);
        CharSequence r23 = r22[0];
        if (r02 == null) goto L36;
        int r3 = r02.size();
        int r5 = 0;
    L31:
        if (r5 >= r3) goto L36;
        TLRPC.MessageEntity r6 = r02.get(r5);
        if ((r6.offset + r6.length) <= r23.length()) goto L35;
        r6.length = r23.length() - r6.offset;
    L35:
        r5 = r5 + 1;
    L36:
        TLRPC.TL_messageMediaPoll r32 = new TLRPC.TL_messageMediaPoll();
        TLRPC.TL_poll r52 = new TLRPC.TL_poll();
        r32.poll = r52;
        r52.multiple_choice = this.multipleChoise;
        r52.quiz = this.quizPoll;
        r52.public_voters = !this.anonymousPoll;
        r52.open_answers = this.allowAddingOptions;
        r52.revoting_disabled = !this.allowRevoting;
        r52.shuffle_answers = this.shuffleOptions;
        r52.subscribers_only = this.poll2vSubscribersOnlyRow.checked;
        if (this.poll2vLimitByCountryRow.checked == true) goto L39;
    L41:
        TLRPC.Poll r53 = r32.poll;
        r53.creator = true;
        int r62 = this.pollLimitDuration;
        if (r62 == 0) goto L44;
        r53.hide_results_until_close = this.hideResults;
        r53.close_period = r62;
        r53.flags |= 16;
    L47:
        r53.question = new TLRPC.TL_textWithEntities();
        r32.poll.question.text = r23.toString();
        r32.poll.question.entities = r02;
        ArrayList r03 = new ArrayList(this.maxAnswersCount);
        int r24 = 0;
    L48:
        CharSequence[] r54 = this.answers;
        if (r24 >= r54.length) goto L70;
        if (TextUtils.isEmpty(getFixedString(r54[r24])) == false) goto L53;
        this.attachedMedia.removeAnswerAndShift(r32.poll.answers.size());
    L69:
        r24 = r24 + 1;
        goto L48
    L53:
        CharSequence[] r63 = {getFixedString(this.answers[r24])};
        ArrayList<TLRPC.MessageEntity> r55 = MediaDataController.getInstance(this.parentAlert.currentAccount).getEntities(r63, true);
        CharSequence r64 = r63[0];
        if (r55 == null) goto L61;
        int r7 = r55.size();
        int r8 = 0;
    L56:
        if (r8 >= r7) goto L61;
        TLRPC.MessageEntity r9 = r55.get(r8);
        if ((r9.offset + r9.length) <= r64.length()) goto L60;
        r9.length = r64.length() - r9.offset;
    L60:
        r8 = r8 + 1;
    L61:
        TLRPC.TL_pollAnswer r72 = new TLRPC.TL_pollAnswer();
        TLRPC.TL_textWithEntities r82 = new TLRPC.TL_textWithEntities();
        r72.text = r82;
        r82.text = r64.toString();
        r72.text.entities = r55;
        r72.option = new byte[]{(byte) (r32.poll.answers.size() + 48)};
        if (this.multipleChoise == true) goto L66;
        if (this.quizPoll == true) goto L66;
    L68:
        r32.poll.answers.add(r72);
    L66:
        if (this.answersChecks[r24] == false) goto L68;
        r03.add(Integer.valueOf(r32.poll.answers.size()));
        goto L68
    L70:
        r32.results = new TLRPC.TL_pollResults();
        CharSequence r25 = getFixedString(this.solutionString);
        if (r25 == null) goto L80;
        r32.results.solution = r25.toString();
        ArrayList<TLRPC.MessageEntity> r12 = MediaDataController.getInstance(this.parentAlert.currentAccount).getEntities(new CharSequence[]{r25}, true);
        if (r12 == null) goto L78;
        if (r12.isEmpty() == true) goto L78;
        r32.results.solution_entities = r12;
    L78:
        if (TextUtils.isEmpty(r32.results.solution) == true) goto L80;
        r32.results.flags |= 16;
    L80:
        ChatAttachAlert r13 = this.parentAlert;
        AlertsCreator.ensurePaidMessageConfirmation(r13.currentAccount, r13.getDialogId(), this.parentAlert.getAdditionalMessagesCount() + 1, new ChatAttachAlertPollLayout$$ExternalSyntheticLambda7(this, (ChatActivity) r13.baseFragment, r32, r03));
        return;
    L44:
        int r65 = this.pollLimitDeadline;
        if (r65 == 0) goto L47;
        r53.hide_results_until_close = this.hideResults;
        r53.close_date = r65;
        r53.flags |= 32;
        goto L47
    L39:
        if (this.countriesList.isEmpty() == true) goto L41;
        TLRPC.Poll r56 = r32.poll;
        r56.flags |= 4096;
        r56.countries_iso2.addAll(this.countriesList);
        goto L41
    L5:
        if (this.doneItemEnabled == true) goto L18;
        int r04 = 0;
    L8:
        if (r1 >= this.answersChecks.length) goto L15;
        if (TextUtils.isEmpty(getFixedString(this.answers[r1])) == true) goto L14;
        if (this.answersChecks[r1] == false) goto L14;
        r04 = r04 + 1;
    L14:
        r1 = r1 + 1;
        goto L8
    L15:
        if (r04 > 0) goto L99;
        showQuizHint();
        return;
    }

    private void onTodoDoneButtonClick() {
        CharSequence[] r2 = {getFixedString(this.questionString)};
        ArrayList<TLRPC.MessageEntity> r0 = MediaDataController.getInstance(this.parentAlert.currentAccount).getEntities(r2, true);
        CharSequence r22 = r2[0];
        if (r0 == null) goto L10;
        int r4 = r0.size();
        int r5 = 0;
    L5:
        if (r5 >= r4) goto L10;
        TLRPC.MessageEntity r6 = r0.get(r5);
        if ((r6.offset + r6.length) <= r22.length()) goto L9;
        r6.length = r22.length() - r6.offset;
    L9:
        r5 = r5 + 1;
    L10:
        TLRPC.TL_messageMediaToDo r42 = new TLRPC.TL_messageMediaToDo();
        TLRPC.TodoList r52 = new TLRPC.TodoList();
        r42.todo = r52;
        boolean r62 = this.allowMarking;
        if (r62 == true) goto L13;
    L15:
        boolean r7 = false;
    L16:
        r52.others_can_append = r7;
        r52.others_can_complete = r62;
        r52.title = new TLRPC.TL_textWithEntities();
        r42.todo.title.text = r22.toString();
        r42.todo.title.entities = r0;
        int r02 = 0;
    L17:
        CharSequence[] r23 = this.answers;
        if (r02 >= r23.length) goto L32;
        if (TextUtils.isEmpty(getFixedString(r23[r02])) == true) goto L31;
        CharSequence[] r53 = {getFixedString(this.answers[r02])};
        ArrayList<TLRPC.MessageEntity> r24 = MediaDataController.getInstance(this.parentAlert.currentAccount).getEntities(r53, true);
        CharSequence r54 = r53[0];
        if (r24 == null) goto L30;
        int r63 = r24.size();
        int r72 = 0;
    L25:
        if (r72 >= r63) goto L30;
        TLRPC.MessageEntity r8 = r24.get(r72);
        if ((r8.offset + r8.length) <= r54.length()) goto L29;
        r8.length = r54.length() - r8.offset;
    L29:
        r72 = r72 + 1;
    L30:
        TLRPC.TodoItem r64 = new TLRPC.TodoItem();
        TLRPC.TL_textWithEntities r73 = new TLRPC.TL_textWithEntities();
        r64.title = r73;
        r73.text = r54.toString();
        r64.title.entities = r24;
        r64.id = r42.todo.list.size() + 1;
        r42.todo.list.add(r64);
    L31:
        r02 = r02 + 1;
        goto L17
    L32:
        ChatAttachAlert r03 = this.parentAlert;
        AlertsCreator.ensurePaidMessageConfirmation(r03.currentAccount, r03.getDialogId(), this.parentAlert.getAdditionalMessagesCount() + 1, new ChatAttachAlertPollLayout$$ExternalSyntheticLambda4(this, (ChatActivity) r03.baseFragment, r42));
        return;
    L13:
        if (this.allowAdding == false) goto L15;
        r7 = true;
        goto L16
    }

    private void openAttachMenuForOptions(int r5) {
        this.currentAttachAlertIndex = r5;
        this.currentAttachAlert = openPollAttachMenu(this.parentAlert.baseFragment, getStartLayoutForMedia(this.attachedMedia.get(r5)), getAllowedLayoutsForIndex(r5), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda14(this, r5), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda15(this));
    }

    private void openAttachOrReplaceMenuForOptions(int r2) {
        if (this.attachedMedia.get(r2) == null) goto L6;
        openEditOrReplaceMenu(r2);
        return;
    L6:
        openAttachMenuForOptions(r2);
    }

    private void openEditOrReplaceMenu(int r15) {
        PollAttachedMedia r0 = this.attachedMedia.get(r15);
        if (r0 == null) goto L40;
        ChatAttachAlert r1 = this.parentAlert;
        if (r1 == null) goto L41;
        BaseFragment r12 = r1.baseFragment;
        if (r12 == null) goto L42;
        Activity r13 = r12.getParentActivity();
        if ((r0 instanceof PollAttachedMediaGallery) == false) goto L14;
        ArrayList<Object> r5 = new ArrayList(1);
        r5.add(((PollAttachedMediaGallery) r0).photoEntry);
        PhotoViewer.getInstance().setParentActivity(r13);
        PhotoViewer.getInstance().openPhotoForSelect(r5, 0, 14, false, new AnonymousClass12(this, r15), null);
        return;
    L14:
        if ((r0 instanceof PollAttachedMediaSticker) == false) goto L23;
        PollAttachedMediaSticker r02 = (PollAttachedMediaSticker) r0;
        ContentPreviewViewer.getInstance().setParentActivity(r13);
        ContentPreviewViewer.getInstance().setDelegate(new AnonymousClass13(this, r15));
        ContentPreviewViewer r3 = ContentPreviewViewer.getInstance();
        TLRPC.Document r4 = r02.sticker;
        if (MessageObject.isAnimatedEmoji(r4) == false) goto L19;
        int r152 = 2;
    L20:
        r3.open(r4, null, _UrlKt.FRAGMENT_ENCODE_SET, null, null, r152, false, r02.parent, this.resourcesProvider, 200);
        return;
    L19:
        r152 = 0;
        goto L20
    L23:
        if ((r0 instanceof PollAttachedMediaFile) == false) goto L27;
        PollAttachedMediaFile r03 = (PollAttachedMediaFile) r0;
        showOptionsForDrawable(r15, new ChatAttachAlertPollLayout$$ExternalSyntheticLambda18(r03.name, AndroidUtilities.formatFileSize(r03.size, true, true) + " " + r03.ext), AndroidUtilities.dp(240.0f), AndroidUtilities.dp(60.0f));
        return;
    L27:
        if ((r0 instanceof PollAttachedMediaMusic) == false) goto L31;
        PollAttachedMediaMusic r04 = (PollAttachedMediaMusic) r0;
        TLRPC.Document r14 = r04.messageObject.getDocument();
        showOptionsForDrawable(r15, new ChatAttachAlertPollLayout$$ExternalSyntheticLambda19(MessageObject.getMusicTitle(r14, true), MessageObject.getMusicAuthor(r14, true) + " - " + LocaleController.formatShortDuration((int) MessageObject.getDocumentDuration(r14)), r04), AndroidUtilities.dp(240.0f), AndroidUtilities.dp(60.0f));
        return;
    L31:
        if ((r0 instanceof PollAttachedMediaLocation) == false) goto L35;
        showOptionsForDrawable(r15, new ChatAttachAlertPollLayout$$ExternalSyntheticLambda20((PollAttachedMediaLocation) r0), AndroidUtilities.dp(300.0f), (AndroidUtilities.dp(300.0f) * 9) / 16);
        return;
    L35:
        if ((r0 instanceof PollAttachedMediaLink) == false) goto L38;
        PollAttachedMediaLink r05 = (PollAttachedMediaLink) r0;
        AlertsCreator.showAddLinkToPoll(getContext(), this.resourcesProvider, r05.url, r05.getWebPage(), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda21(this, r15), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda22(this, r15));
        return;
    L38:
        openAttachMenuForOptions(r15);
        return;
    L42:
        return;
    L41:
        return;
    }

    private void openKeyboardInternal() {
        if (this.currentCell == null) goto L6;
        this.keyboardNotifier.awaitKeyboard();
        EditTextBoldCursor r0 = this.currentCell.getEditField();
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    L6:
        if (AndroidUtilities.usingHardwareInput == false) goto L8;
        int r02 = 0;
    L9:
        showEmojiPopup(r02);
        if (AndroidUtilities.usingHardwareInput == false) goto L12;
        return;
    L12:
        if (this.keyboardVisible == false) goto L14;
        return;
    L14:
        if (AndroidUtilities.isInMultiwindow == false) goto L16;
        return;
    L16:
        if (AndroidUtilities.isTablet() == true) goto L22;
        this.waitingForKeyboardOpen = true;
        AndroidUtilities.cancelRunOnUIThread(this.openKeyboardRunnable);
        AndroidUtilities.runOnUIThread(this.openKeyboardRunnable, 100);
        return;
    L22:
        return;
    L8:
        r02 = 2;
        goto L9
    }

    public static ChatAttachAlert openPollAttachMenu(BaseFragment r8, int r9, int r10, Utilities.Callback<PollAttachedMedia> r11, Runnable r12) {
        if (r8 != null) goto L5;
        return null;
    L5:
        AnonymousClass14 r0 = new AnonymousClass14(r8.getContext(), r8, false, false, true, r8.getResourceProvider(), r12);
        r0.setDelegate(new AnonymousClass15(r8, r11, r0));
        r0.setEmojiViewDelegate(new AnonymousClass16(r11, r0));
        r0.getPhotoLayout().loadGalleryPhotos();
        r0.setMaxSelectedPhotos(1, true);
        r0.enablePollAttachMode(r10);
        r0.setLocationActivityDelegate(new ChatAttachAlertPollLayout$$ExternalSyntheticLambda23(r11));
        r0.setDocumentsDelegate(new AnonymousClass17(r11, r0, r8));
        r0.setAudioSelectDelegate(new ChatAttachAlertPollLayout$$ExternalSyntheticLambda24(r11, r0));
        r0.init();
        r0.setFocusable(true);
        r0.show();
        return r0;
    }

    private void resetSuggestEmojiPanel() {
        SuggestEmojiView r0 = this.suggestEmojiPanel;
        if (r0 == null) goto L6;
        r0.setDelegate(null);
        this.suggestEmojiPanel.forceClose();
        return;
    }

    private void setAttachedMedia(int r4, PollAttachedMedia r5) {
        PollAttachedMediaPack r0 = this.attachedMedia;
        if (r5 == null) goto L5;
        r0.set(r4, r5);
    L6:
        int r42 = mediaIndexToAdapterPosition(r4);
        if (r42 < 0) goto L15;
        RecyclerView.ViewHolder r02 = this.listView.findViewHolderForAdapterPosition(r42);
        if (r02 == null) goto L13;
        View r03 = r02.itemView;
        if ((r03 instanceof PollEditTextCell) == false) goto L13;
        ((PollEditTextCell) r03).attachView.setAttachedMedia(r5, true);
    L13:
        this.listAdapter.notifyItemChanged(r42);
    L15:
        if ((r5 instanceof PollAttachedMediaLink) == false) goto L17;
        PollAttachedMediaLink r04 = (PollAttachedMediaLink) r5;
        this.webPageLoader.get(r04.url, new ChatAttachAlertPollLayout$$ExternalSyntheticLambda2(this, r5));
        checkPollLinkMedia(r04, false);
    L17:
        checkDoneButton();
        return;
    L5:
        r0.remove(r4);
        goto L6
    }

    private void setTextLeft(View r5, int r6) {
        if ((r5 instanceof PollEditTextCell) == false) goto L50;
        PollEditTextCell r52 = (PollEditTextCell) r5;
        int r1 = 0;
        if (r6 != this.descriptionRow) goto L12;
        int r62 = this.MAX_CAPTION_LENGTH;
        CharSequence r0 = this.descriptionString;
        if (r0 == null) goto L10;
        r1 = r0.length();
    L10:
        int r02 = r62 - r1;
    L38:
        float r63 = r62;
        if (r02 > (r63 - (0.7f * r63))) goto L46;
        r52.setText2(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r02)}));
        SimpleTextView r53 = r52.getTextView2();
        if (r02 >= 0) goto L43;
        int r64 = Theme.key_text_RedRegular;
    L44:
        r53.setTextColor(getThemedColor(r64));
        r53.setTag(Integer.valueOf(r64));
        return;
    L43:
        r64 = Theme.key_windowBackgroundWhiteGrayText3;
        goto L44
    L46:
        r52.setText2(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L12:
        if (r6 != this.questionRow) goto L21;
        if (this.todo == false) goto L16;
        r62 = getMessagesController().todoTitleLengthMax;
    L17:
        CharSequence r03 = this.questionString;
        if (r03 == null) goto L10;
        r1 = r03.length();
        goto L10
    L16:
        r62 = 255;
        goto L17
    L21:
        if (r6 != this.solutionRow) goto L26;
        CharSequence r65 = this.solutionString;
        if (r65 == null) goto L25;
        r1 = r65.length();
    L25:
        r62 = 200;
        r02 = 200 - r1;
        goto L38
    L26:
        int r04 = this.answerStartRow;
        if (r6 >= r04) goto L29;
        return;
    L29:
        if (r6 >= (this.answersCount + r04)) goto L49;
        int r66 = r6 - r04;
        if (this.todo == false) goto L33;
        int r05 = getMessagesController().todoItemLengthMax;
    L34:
        CharSequence r67 = this.answers[r66];
        if (r67 == null) goto L37;
        r1 = r67.length();
    L37:
        int r3 = r05;
        r02 = r05 - r1;
        r62 = r3;
        goto L38
    L33:
        r05 = 100;
        goto L34
    L49:
        return;
    }

    private void showEmojiPopup(int r10) {
        if (this.isPremium == false) goto L66;
        ChatActivityEnterViewAnimatedIconView r0 = null;
        if (r10 != 1) goto L47;
        EmojiView r102 = this.emojiView;
        if (r102 != null) goto L10;
    L12:
        boolean r103 = false;
    L13:
        createEmojiView();
        this.emojiView.setVisibility(0);
        this.emojiViewWasVisible = this.emojiViewVisible;
        this.emojiViewVisible = true;
        EmojiView r3 = this.emojiView;
        if (this.keyboardHeight > 0) goto L20;
        if (AndroidUtilities.isTablet() == false) goto L18;
        this.keyboardHeight = AndroidUtilities.dp(150.0f);
        goto L20
    L18:
        this.keyboardHeight = MessagesController.getGlobalEmojiSettings().getInt("kbd_height", AndroidUtilities.dp(200.0f));
    L20:
        if (this.keyboardHeightLand <= 0) goto L22;
    L25:
        Point r4 = AndroidUtilities.displaySize;
        if (r4.x <= r4.y) goto L28;
        int r42 = this.keyboardHeightLand;
    L29:
        FrameLayout.LayoutParams r5 = (FrameLayout.LayoutParams) r3.getLayoutParams();
        r5.height = AndroidUtilities.navigationBarHeight + r42;
        r3.setLayoutParams(r5);
        if (AndroidUtilities.isInMultiwindow == false) goto L32;
    L36:
        this.emojiPadding = r42;
        this.keyboardNotifier.fire();
        this.parentAlert.sizeNotifierFrameLayout.requestLayout();
        PollEditTextCell r32 = this.currentCell;
        if (r32 == null) goto L40;
        r0 = r32.getEmojiButton();
    L40:
        if (r0 == null) goto L42;
        r0.setState(ChatActivityEnterViewAnimatedIconView.State.KEYBOARD, true);
    L42:
        if (r103 == false) goto L44;
        return;
    L44:
        if (this.keyboardVisible == true) goto L65;
        ValueAnimator r104 = ValueAnimator.ofFloat(new float[]{this.emojiPadding, 0.0f});
        r104.addUpdateListener(new ChatAttachAlertPollLayout$$ExternalSyntheticLambda5(this));
        r104.addListener(new AnonymousClass8(this));
        r104.setDuration(250);
        r104.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r104.start();
        return;
    L65:
        return;
    L32:
        if (AndroidUtilities.isTablet() == true) goto L36;
        PollEditTextCell r33 = this.currentCell;
        if (r33 == null) goto L36;
        AndroidUtilities.hideKeyboard(r33.getEditField());
        goto L36
    L28:
        r42 = this.keyboardHeight;
        goto L29
    L22:
        if (AndroidUtilities.isTablet() == false) goto L24;
        this.keyboardHeightLand = AndroidUtilities.dp(150.0f);
        goto L25
    L24:
        this.keyboardHeightLand = MessagesController.getGlobalEmojiSettings().getInt("kbd_height_land3", AndroidUtilities.dp(200.0f));
        goto L25
    L10:
        if (r102.getVisibility() != 0) goto L12;
        r103 = true;
        goto L13
    L47:
        PollEditTextCell r34 = this.currentCell;
        if (r34 == null) goto L51;
        r0 = r34.getEmojiButton();
    L51:
        if (r0 == null) goto L53;
        r0.setState(ChatActivityEnterViewAnimatedIconView.State.SMILE, true);
    L53:
        EmojiView r02 = this.emojiView;
        if (r02 == null) goto L60;
        this.emojiViewWasVisible = this.emojiViewVisible;
        this.emojiViewVisible = false;
        this.isEmojiSearchOpened = false;
        if (AndroidUtilities.usingHardwareInput == false) goto L58;
    L59:
        r02.setVisibility(8);
        goto L60
    L58:
        if (AndroidUtilities.isInMultiwindow == true) goto L59;
    L60:
        if (r10 != 0) goto L62;
        this.emojiPadding = 0;
    L62:
        this.keyboardNotifier.fire();
        this.parentAlert.sizeNotifierFrameLayout.requestLayout();
        return;
    }

    private void showMediaHint(int r3) {
        RecyclerView.ViewHolder r32 = this.listView.findViewHolderForAdapterPosition(this.answerStartRow + r3);
        if (r32 == null) goto L13;
        View r33 = r32.itemView;
        if ((r33 instanceof PollEditTextCell) == false) goto L14;
        PollEditTextCell r34 = (PollEditTextCell) r33;
        if (r34.getTop() <= AndroidUtilities.dp(40.0f)) goto L15;
        SuggestEmojiView r0 = this.suggestEmojiPanel;
        if (r0 == null) goto L11;
        r0.forceClose();
    L11:
        this.hintView.setText(LocaleController.getString(R.string.PollAddTextOrRemoveMedia));
        this.hintView.showForView(r34.getCheckBox(), true);
        ImageView r35 = this.hintView.arrowImageView;
        r35.setTranslationX(r35.getTranslationX() + AndroidUtilities.dp(48.0f));
        HintView r2 = this.hintView;
        r2.setTranslationY(r2.getTranslationY() + AndroidUtilities.dp(10.0f));
        return;
    L15:
        return;
    L14:
        return;
    }

    private void showOptionsForDrawable(int r5, Utilities.CallbackReturn<View, Drawable> r6, int r7, int r8) {
        ItemOptions r52 = ItemOptions.makeOptions(this, new View(getContext())).setDimAlpha(0).setDrawScrim(false).add(R.drawable.msg_replace, LocaleController.getString(R.string.ReplaceAttachedPollMedia), new ChatAttachAlertPollLayout$$ExternalSyntheticLambda25(this, r5)).add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new ChatAttachAlertPollLayout$$ExternalSyntheticLambda26(this, r5));
        ScrimOptions r0 = new ScrimOptions(getContext(), this.resourcesProvider);
        r52.setOnDismiss(new ChatActivity$$ExternalSyntheticLambda269(r0));
        r52.setMinWidth(AndroidUtilities.dp(185.0f));
        r52.setupSelectors();
        r0.setItemOptions(r52);
        r0.setScrimDrawable(r6.run(r0.getWindowView()), r7, r8);
        r0.setOptionsAtCenter();
        r0.show();
    }

    private void showQuizHint() {
        int r0 = this.answerStartRow;
    L4:
        if (r0 >= (this.answerStartRow + this.answersCount)) goto L17;
        RecyclerView.ViewHolder r1 = this.listView.findViewHolderForAdapterPosition(r0);
        if (r1 == null) goto L16;
        View r12 = r1.itemView;
        if ((r12 instanceof PollEditTextCell) == false) goto L16;
        PollEditTextCell r13 = (PollEditTextCell) r12;
        if (r13.getTop() <= AndroidUtilities.dp(40.0f)) goto L16;
        SuggestEmojiView r02 = this.suggestEmojiPanel;
        if (r02 == null) goto L14;
        r02.forceClose();
    L14:
        this.hintView.setText(LocaleController.getString(R.string.PollTapToSelect));
        this.hintView.showForView(r13.getCheckBox(), true);
        return;
    L16:
        r0 = r0 + 1;
        goto L4
    }

    private void updateRows() {
        this.solutionRowHeader = -1;
        this.solutionRow = -1;
        this.solutionInfoRow = -1;
        this.poll2vMultipleRow = -1;
        this.poll2vAnonymousRow = -1;
        this.poll2vLimitDurationRow = -1;
        this.poll2vLimitDurationTimeRow = -1;
        this.poll2vLimitDurationHideResultsRow = -1;
        this.poll2vLimitDurationHideResultsRowInfo = -1;
        this.poll2vAllowAddingRow = -1;
        this.poll2vShuffleRow = -1;
        this.poll2vAllowRevotingRow = -1;
        this.poll2vQuizRow = -1;
        this.poll2vSubscribersOnlyRow.row = -1;
        this.poll2vLimitByCountryRow.row = -1;
        this.poll2vLimitByCountryListRow = -1;
        this.allowAddingRow = -1;
        this.allowMarkingRow = -1;
        this.addAnswerRow = -1;
        this.answerStartRow = -1;
        this.settingsSectionRow = -1;
        this.descriptionRow = -1;
        this.paddingRow = 0;
        int r2 = 1 + 1;
        this.questionHeaderRow = 1;
        int r3 = r2 + 1;
        this.rowCount = r3;
        this.questionRow = r2;
        boolean r4 = this.todo;
        if (r4 == true) goto L5;
        this.rowCount = r2 + 2;
        this.descriptionRow = r3;
    L5:
        int r22 = this.rowCount;
        int r32 = r22 + 1;
        this.questionSectionRow = r22;
        int r23 = r22 + 2;
        this.rowCount = r23;
        this.answerHeaderRow = r32;
        int r33 = this.answersCount;
        if (r33 == 0) goto L9;
        this.answerStartRow = r23;
        this.rowCount = r23 + r33;
    L9:
        if (r33 == this.answers.length) goto L11;
        int r24 = this.rowCount;
        this.rowCount = r24 + 1;
        this.addAnswerRow = r24;
    L11:
        int r25 = this.rowCount;
        this.answerSectionRow = r25;
        int r5 = r25 + 2;
        this.rowCount = r5;
        this.settingsHeaderRow = r25 + 1;
        if (r4 == false) goto L16;
        int r0 = r25 + 3;
        this.rowCount = r0;
        this.allowMarkingRow = r5;
        if (this.allowMarking == false) goto L42;
        this.rowCount = r25 + 4;
        this.allowAddingRow = r0;
    L42:
        int r02 = this.rowCount;
        this.rowCount = r02 + 1;
        this.emptyRow = r02;
        return;
    L16:
        TLRPC.Chat r26 = ((ChatActivity) this.parentAlert.baseFragment).getCurrentChat();
        if (ChatObject.isChannel(r26) == true) goto L19;
    L21:
        boolean r27 = false;
    L22:
        if (r27 == true) goto L24;
        int r1 = this.rowCount;
        this.rowCount = r1 + 1;
        this.poll2vAnonymousRow = r1;
    L25:
        int r12 = this.rowCount;
        int r34 = r12 + 1;
        this.rowCount = r34;
        this.poll2vMultipleRow = r12;
        if (r27 == true) goto L28;
        this.rowCount = r12 + 2;
        this.poll2vAllowAddingRow = r34;
    L29:
        int r03 = this.rowCount;
        this.poll2vAllowRevotingRow = r03;
        this.poll2vShuffleRow = r03 + 1;
        int r13 = r03 + 3;
        this.rowCount = r13;
        this.poll2vQuizRow = r03 + 2;
        if (r27 == false) goto L34;
        ToggleRow r28 = this.poll2vSubscribersOnlyRow;
        int r35 = r03 + 4;
        this.rowCount = r35;
        r28.row = r13;
        ToggleRow r14 = this.poll2vLimitByCountryRow;
        int r29 = r03 + 5;
        this.rowCount = r29;
        r14.row = r35;
        if (r14.checked == false) goto L34;
        this.rowCount = r03 + 6;
        this.poll2vLimitByCountryListRow = r29;
    L34:
        int r04 = this.rowCount;
        int r15 = r04 + 1;
        this.rowCount = r15;
        this.poll2vLimitDurationRow = r04;
        if (this.pollLimitDuration == 0) goto L37;
    L38:
        this.poll2vLimitDurationTimeRow = r15;
        this.poll2vLimitDurationHideResultsRow = r04 + 2;
        this.rowCount = r04 + 4;
        this.poll2vLimitDurationHideResultsRowInfo = r04 + 3;
    L39:
        int r05 = this.rowCount;
        int r16 = r05 + 1;
        this.rowCount = r16;
        this.settingsSectionRow = r05;
        if (this.quizPoll == false) goto L42;
        this.solutionRowHeader = r16;
        this.solutionRow = r05 + 2;
        this.rowCount = r05 + 4;
        this.solutionInfoRow = r05 + 3;
        goto L42
    L37:
        if (this.pollLimitDeadline == 0) goto L39;
    L28:
        this.allowAddingOptions = false;
        goto L29
    L24:
        this.anonymousPoll = true;
        goto L25
    L19:
        if (r26.megagroup == true) goto L21;
        r27 = true;
        goto L22
    }

    private void updateSuggestEmojiPanelDelegate(RecyclerView.ViewHolder r3) {
        SuggestEmojiView r0 = this.suggestEmojiPanel;
        if (r0 == null) goto L13;
        r0.forceClose();
        SuggestEmojiView r02 = this.suggestEmojiPanel;
        if (r02 == null) goto L14;
        if (r3 != null) goto L8;
        return;
    L8:
        if ((r3.itemView instanceof PollEditTextCell) == false) goto L16;
        SuggestEmojiView.AnchorViewDelegate r03 = r02.getDelegate();
        View r32 = r3.itemView;
        if (r03 == r32) goto L17;
        this.suggestEmojiPanel.setDelegate((PollEditTextCell) r32);
        return;
    L17:
        return;
    L16:
        return;
    L14:
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.didReceivedWebpagesInUpdates) goto L11;
        int r22 = 0;
        LongSparseArray<TLRPC.WebPage> r32 = (LongSparseArray) r4[0];
        this.webPageLoader.apply(r32);
        int r33 = this.attachedMedia.medias.size();
    L5:
        if (r22 >= r33) goto L24;
        PollAttachedMedia r42 = this.attachedMedia.medias.get(r22);
        if ((r42 instanceof PollAttachedMediaLink) == false) goto L9;
        checkPollLinkMedia((PollAttachedMediaLink) r42, true);
    L9:
        r22 = r22 + 1;
        goto L5
    L24:
        return;
    L11:
        if (r2 != NotificationCenter.emojiLoaded) goto L22;
        EmojiView r23 = this.emojiView;
        if (r23 == null) goto L15;
        r23.invalidateViews();
    L15:
        PollEditTextCell r24 = this.currentCell;
        if (r24 == null) goto L23;
        int r25 = r24.getEditField().getCurrentTextColor();
        this.currentCell.getEditField().setTextColor(-1);
        this.currentCell.getEditField().setTextColor(r25);
        return;
    L23:
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getButtonsHideOffset() {
        return AndroidUtilities.dp(70.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        if (this.listView.getChildCount() > 1) goto L5;
        return Integer.MAX_VALUE;
    L5:
        View r0 = this.listView.getChildAt(1);
        if (r0 != null) goto L8;
        return Integer.MAX_VALUE;
    L8:
        RecyclerListView.Holder r3 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r0);
        int r02 = (((int) r0.getY()) - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(20.0f);
        if (r02 <= 0) goto L14;
        if (r3 == null) goto L14;
        if (r3.getAdapterPosition() != 1) goto L14;
        int r1 = r02;
    L15:
        if (r02 < 0) goto L20;
        if (r3 == null) goto L20;
        if (r3.getAdapterPosition() != 1) goto L20;
    L22:
        return r02 + AndroidUtilities.dp(25.0f);
    L20:
        r02 = r1;
    L14:
        r1 = 0;
        goto L15
    }

    public int getEmojiPadding() {
        return this.emojiPadding;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(17.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return this.topPadding;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_dialogScrollGlow));
        int r10 = Theme.key_windowBackgroundGrayShadow;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, r10));
        int r9 = Theme.key_windowBackgroundGray;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ShadowSectionCell.class}, null, null, null, r9));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{EmptyView.class}, null, null, null, r9));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r10));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r9));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        int r18 = Theme.key_text_RedRegular;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{HeaderCell.class}, new String[]{"textView2"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{HeaderCell.class}, new String[]{"textView2"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText3));
        int r15 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{PollEditTextCell.class}, new String[]{"textView"}, null, null, null, r15));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_HINTTEXTCOLOR, new Class[]{PollEditTextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        int r36 = Theme.key_windowBackgroundWhiteGrayIcon;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_HINTTEXTCOLOR, new Class[]{PollEditTextCell.class}, new String[]{"deleteImageView"}, null, null, null, r36));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_HINTTEXTCOLOR, new Class[]{PollEditTextCell.class}, new String[]{"moveImageView"}, null, null, null, r36));
        RecyclerListView r8 = this.listView;
        int r6 = ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE;
        r1.add(new ThemeDescription(r8, ThemeDescription.FLAG_DRAWABLESELECTEDSTATE | r6, new Class[]{PollEditTextCell.class}, new String[]{"deleteImageView"}, null, null, null, Theme.key_stickers_menuSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{PollEditTextCell.class}, new String[]{"textView2"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{PollEditTextCell.class}, new String[]{"checkBox"}, null, null, null, r36));
        int r19 = Theme.key_checkboxCheck;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{PollEditTextCell.class}, new String[]{"checkBox"}, null, null, null, r19));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r15));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrack));
        int r23 = Theme.key_switchTrackChecked;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, r23));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_telegram_color_text));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r23));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r19));
        return r1;
    }

    public void hideEmojiView() {
        if (this.emojiViewVisible == true) goto L14;
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L14;
        if (r0.getVisibility() == 8) goto L14;
        PollEditTextCell r02 = this.currentCell;
        if (r02 == null) goto L13;
        ChatActivityEnterViewAnimatedIconView r03 = r02.getEmojiButton();
        if (r03 == null) goto L13;
        r03.setState(ChatActivityEnterViewAnimatedIconView.State.SMILE, false);
    L13:
        this.emojiView.setVisibility(8);
    L14:
        int r04 = this.emojiPadding;
        this.emojiPadding = 0;
        if (r04 == 0) goto L18;
        this.keyboardNotifier.fire();
        return;
    }

    public boolean isAnimatePopupClosing() {
        return this.isAnimatePopupClosing;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean isDoneItemEnabled() {
        return this.doneItemEnabled;
    }

    public boolean isPopupShowing() {
        return this.emojiViewVisible;
    }

    public boolean isPopupVisible() {
        EmojiView r0 = this.emojiView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getVisibility() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isWaitingForKeyboardOpen() {
        return this.waitingForKeyboardOpen;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int needsActionBar() {
        return 1;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onBackPressed() {
        if (this.emojiViewVisible == false) goto L7;
        hideEmojiPopup(true);
        return true;
    L7:
        if (checkDiscard() == true) goto L10;
        return true;
    L10:
        return super.onBackPressed();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onDestroy() {
        super.onDestroy();
        this.destroyed = true;
        NotificationCenter.getInstance(this.parentAlert.currentAccount).removeObserver(this, NotificationCenter.didReceivedWebpagesInUpdates);
        if (this.isPremium == false) goto L8;
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L9;
        this.parentAlert.sizeNotifierFrameLayout.removeView(r0);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onDismissWithTouchOutside() {
        if (checkDiscard() == true) goto L7;
        return false;
    L7:
        return super.onDismissWithTouchOutside();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHidden() {
        this.parentAlert.updateDoneItemEnabled();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHideShowProgress(float r1) {
        this.parentAlert.updateDoneItemEnabled();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onMenuItemClick(int r2) {
        if (r2 == 40) goto L5;
        return;
    L5:
        if (this.todo == false) goto L8;
        onTodoDoneButtonClick();
        return;
    L8:
        onPollDoneButtonClick();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPause() {
        super.onPause();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
    L6:
        if (this.isPremium == false) goto L14;
        hideEmojiPopup(false);
        SuggestEmojiView r1 = this.suggestEmojiPanel;
        if (r1 == null) goto L10;
        r1.forceClose();
    L10:
        PollEditTextCell r12 = this.currentCell;
        if (r12 == null) goto L15;
        r12.setEmojiButtonVisibility(false);
        this.currentCell.getTextView().clearFocus();
        AndroidUtilities.hideKeyboard(this.currentCell.getEditField());
        return;
    L15:
        return;
    }

    public void onPollAttachFilePicker(Intent r3) {
        if (this.currentAttachAlertIndex != (-1)) goto L5;
        return;
    L5:
        if (this.currentAttachAlert == null) goto L26;
        if (r3 != null) goto L9;
    L16:
        Uri r32 = null;
    L17:
        if (r32 != null) goto L20;
        BulletinFactory.of(this.parentAlert.container, this.resourcesProvider).createErrorBulletin(LocaleController.getString(R.string.UnsupportedAttachment), this.resourcesProvider).show();
        return;
    L20:
        setAttachedMedia(this.currentAttachAlertIndex, new PollAttachedMediaFile(r32));
        ChatAttachAlert r2 = this.currentAttachAlert;
        if (r2 == null) goto L25;
        r2.dismiss(true);
        return;
    L25:
        return;
    L9:
        if (r3.getData() == null) goto L12;
        r32 = r3.getData();
        goto L17
    L12:
        if (r3.getClipData() == null) goto L16;
        ClipData r33 = r3.getClipData();
        if (r33.getItemCount() <= 0) goto L16;
        r32 = r33.getItemAt(0).getUri();
        goto L17
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r3, int r4) {
        if (this.parentAlert.sizeNotifierFrameLayout.measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L5;
    L21:
        int r32 = AndroidUtilities.dp(52.0f);
        this.parentAlert.setAllowNestedScroll(false);
    L22:
        int r33 = r32 + AndroidUtilities.statusBarHeight;
        this.ignoreLayout = true;
        if (this.topPadding == r33) goto L25;
    L26:
        this.topPadding = r33;
        this.listView.setPaddingWithoutRequestLayout(0, 0, 0, this.listPaddingBottom);
        this.listView.setItemAnimator(null);
        this.listAdapter.notifyItemChanged(this.paddingRow);
    L27:
        this.ignoreLayout = false;
        return;
    L25:
        if (this.listView.getPaddingBottom() == this.listPaddingBottom) goto L27;
    L5:
        if (this.emojiViewVisible == true) goto L21;
        if (this.isAnimatePopupClosing == true) goto L21;
        if (this.isEmojiSearchOpened == true) goto L21;
        if (AndroidUtilities.isTablet() == true) goto L16;
        Point r34 = AndroidUtilities.displaySize;
        if (r34.x <= r34.y) goto L16;
        int r35 = (int) (r4 / 3.5f);
    L17:
        r32 = r35 - AndroidUtilities.dp(13.0f);
        if (r32 >= 0) goto L20;
        r32 = 0;
    L20:
        this.parentAlert.setAllowNestedScroll(this.allowNesterScroll);
    L16:
        r35 = (r4 / 5) * 2;
        goto L17
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r2) {
        this.parentAlert.actionBar.getTitleTextView().setBuildFullLayout(true);     // Catch: Exception -> L12
    L4:
        if (this.todo == false) goto L6;
        this.parentAlert.actionBar.setTitle(LocaleController.getString(R.string.TodoTitle));
    L10:
        this.parentAlert.updateDoneItemEnabled();
        this.layoutManager.scrollToPositionWithOffset(0, 0);
        return;
    L6:
        boolean r22 = this.quizOnly;
        ChatAttachAlert r0 = this.parentAlert;
        if (r22 == false) goto L9;
        r0.actionBar.setTitle(LocaleController.getString(R.string.NewQuiz));
        goto L10
    L9:
        r0.actionBar.setTitle(LocaleController.getString(R.string.NewPoll));
        goto L10
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate
    public void onSizeChanged(int r7, boolean r8) {
        if (this.isPremium == true) goto L6;
        return;
    L6:
        if (r7 <= AndroidUtilities.dp(50.0f)) goto L17;
        if (this.keyboardVisible == false) goto L17;
        if (AndroidUtilities.isInMultiwindow == true) goto L17;
        if (AndroidUtilities.isTablet() == true) goto L17;
        if (r8 == false) goto L15;
        this.keyboardHeightLand = r7;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height_land3", this.keyboardHeightLand).commit();
        goto L17
    L15:
        this.keyboardHeight = r7;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height", this.keyboardHeight).commit();
    L17:
        if (this.emojiViewVisible == false) goto L39;
        if (r8 == false) goto L20;
        int r0 = this.keyboardHeightLand;
    L22:
        if (this.isEmojiSearchOpened == false) goto L24;
        r0 = r0 + AndroidUtilities.dp(120.0f);
    L24:
        int r02 = r0 + AndroidUtilities.navigationBarHeight;
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) this.emojiView.getLayoutParams();
        int r3 = r1.width;
        int r4 = AndroidUtilities.displaySize.x;
        if (r3 == r4) goto L27;
    L30:
        r1.width = r4;
        r1.height = r02;
        this.emojiView.setLayoutParams(r1);
        this.emojiPadding = r1.height;
        this.keyboardNotifier.fire();
        this.parentAlert.sizeNotifierFrameLayout.requestLayout();
        boolean r03 = this.wasEmojiSearchOpened;
        if (r03 == this.isEmojiSearchOpened) goto L37;
        if (r03 == false) goto L35;
        int r04 = -AndroidUtilities.dp(120.0f);
    L36:
        animateEmojiViewTranslationY(r04, 0.0f);
        goto L37
    L35:
        r04 = AndroidUtilities.dp(120.0f);
    L37:
        this.wasEmojiSearchOpened = this.isEmojiSearchOpened;
        goto L39
    L27:
        if (r1.height != r02) goto L30;
        if (this.wasEmojiSearchOpened == this.isEmojiSearchOpened) goto L39;
    L20:
        r0 = this.keyboardHeight;
    L39:
        if (this.lastSizeChangeValue1 == r7) goto L41;
    L43:
        this.lastSizeChangeValue1 = r7;
        this.lastSizeChangeValue2 = r8;
        boolean r82 = this.keyboardVisible;
        PollEditTextCell r05 = this.currentCell;
        if (r05 != null) goto L46;
        this.keyboardVisible = false;
    L55:
        if (this.keyboardVisible == false) goto L60;
        if (this.emojiViewVisible == false) goto L60;
        showEmojiPopup(0);
    L60:
        if (this.emojiPadding == 0) goto L68;
        boolean r72 = this.keyboardVisible;
        if (r72 == true) goto L68;
        if (r72 == r82) goto L68;
        if (this.emojiViewVisible == true) goto L68;
        this.emojiPadding = 0;
        this.keyboardNotifier.fire();
        this.parentAlert.sizeNotifierFrameLayout.requestLayout();
    L68:
        if (this.keyboardVisible == true) goto L70;
        return;
    L70:
        if (this.waitingForKeyboardOpen == false) goto L74;
        this.waitingForKeyboardOpen = false;
        AndroidUtilities.cancelRunOnUIThread(this.openKeyboardRunnable);
        return;
    L74:
        return;
    L46:
        if (r05.getEditField().isFocused() == true) goto L48;
    L51:
        boolean r73 = false;
    L52:
        this.keyboardVisible = r73;
        goto L55
    L48:
        if (this.keyboardNotifier.keyboardVisible() == false) goto L51;
        if (r7 <= 0) goto L51;
        r73 = true;
        goto L52
    L41:
        if (this.lastSizeChangeValue2 != r8) goto L43;
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
        this.listView.smoothScrollToPosition(1);
    }

    public void setDelegate(PollCreateActivityDelegate r1) {
        this.delegate = r1;
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        this.parentAlert.getSheetContainer().invalidate();
    }
}
