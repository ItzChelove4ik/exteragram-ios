package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.util.Property;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.utils.chats.ChatUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FileRefController;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.EmptyCell;
import org.telegram.ui.Cells.FeaturedStickerSetInfoCell;
import org.telegram.ui.Cells.StickerEmojiCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.PremiumButtonView;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.ContentPreviewViewer;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stories.recorder.StoryEntry;

/* loaded from: classes7.dex */
public class StickersAlert extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private GridAdapter adapter;
    private List<ThemeDescription> animatingDescriptions;
    private int buttonTextColorKey;
    private Runnable cancelSearchCreatorRunnable;
    private int checkReqId;
    private Runnable checkRunnable;
    private boolean clearsInputField;
    private StickersAlertCustomButtonDelegate customButtonDelegate;
    private StickersAlertDelegate delegate;
    private ActionBarMenuSubItem deleteItem;
    private TextView descriptionTextView;
    private ItemTouchHelper dragAndDropHelper;
    private TLRPC.Document draggedDocument;
    private FrameLayout emptyView;
    private RecyclerListView gridView;
    private boolean ignoreLayout;
    private boolean ignoreMasterDismiss;
    private String importingSoftware;
    private ArrayList<Parcelable> importingStickers;
    private ArrayList<SendMessagesHelper.ImportingSticker> importingStickersPaths;
    private TLRPC.InputStickerSet inputStickerSet;
    private StickersAlertInstallDelegate installDelegate;
    private boolean isEditModeEnabled;
    private int itemHeight;
    private int itemSize;
    private String lastCheckName;
    private boolean lastNameAvailable;
    private GridLayoutManager layoutManager;
    private Runnable masterDismissListener;
    private Runnable onDismissListener;
    private ActionBarMenuItem optionsButton;
    private Activity parentActivity;
    private BaseFragment parentFragment;
    private FrameLayout pickerBottomFrameLayout;
    private AnimatedTextView pickerBottomLayout;
    private PremiumButtonView premiumButtonView;
    private ContentPreviewViewer.ContentPreviewViewerDelegate previewDelegate;
    private TextView previewSendButton;
    private View previewSendButtonShadow;
    public boolean probablyEmojis;
    private boolean refreshingFromMenu;
    private int reqId;
    private int scrollOffsetY;
    private TLRPC.Document selectedSticker;
    private SendMessagesHelper.ImportingSticker selectedStickerPath;
    private String setTitle;
    private View[] shadow;
    private AnimatorSet[] shadowAnimation;
    private boolean showEmoji;
    private boolean showTooltipWhenToggle;
    private TextView stickerEmojiTextView;
    private BackupImageView stickerImageView;
    private FrameLayout stickerPreviewLayout;
    public TLRPC.TL_messages_stickerSet stickerSet;
    private ArrayList<TLRPC.StickerSetCovered> stickerSetCovereds;
    private RecyclerListView.OnItemClickListener stickersOnItemClickListener;
    private final StickersShaker stickersShaker;
    private LinkSpanDrawable.LinksTextView titleTextView;
    private HashMap<String, SendMessagesHelper.ImportingSticker> uploadImportStickers;
    private Pattern urlPattern;

    /* renamed from: org.telegram.ui.Components.StickersAlert$1, reason: invalid class name */
    public class AnonymousClass1 implements ContentPreviewViewer.ContentPreviewViewerDelegate {
        final /* synthetic */ StickersAlert this$0;

        public static /* synthetic */ void $r8$lambda$b6LdHrmpIYabq4pGhBDZEgL53Mk(AnonymousClass1 r0, TLObject r1, boolean r2, AlertDialog r3) {
            r0.lambda$deleteSticker$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$u6tDrbQ_KoWhHD1zASonD3rz5A0(AnonymousClass1 r0, boolean r1, AlertDialog r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$deleteSticker$1(r1, r2, r3, r4);
        }

        public AnonymousClass1(StickersAlert r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$deleteSticker$0(TLObject r8, boolean r9, AlertDialog r10) {
            if ((r8 instanceof TLRPC.TL_messages_stickerSet) == false) goto L8;
            TLRPC.TL_messages_stickerSet r1 = (TLRPC.TL_messages_stickerSet) r8;
            MediaDataController.getInstance(UserConfig.selectedAccount).putStickerSet(r1);
            if (r9 == false) goto L7;
            MediaDataController.getInstance(UserConfig.selectedAccount).toggleStickerSet(null, r8, 0, null, false, false);
            goto L8
        L7:
            StickersAlert r82 = this.this$0;
            r82.stickerSet = r1;
            r82.loadStickerSet(false);
            StickersAlert.m13123$$Nest$mupdateFields(this.this$0);
        L8:
            r10.dismiss();
        }

        private /* synthetic */ void lambda$deleteSticker$1(boolean r1, AlertDialog r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new StickersAlert$1$$ExternalSyntheticLambda1(this, r3, r1, r2));
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean can() {
            TLRPC.TL_messages_stickerSet r0 = this.this$0.stickerSet;
            if (r0 == null) goto L11;
            TLRPC.StickerSet r02 = r0.set;
            if (r02 != null) goto L7;
            return true;
        L7:
            if (r02.emojis == false) goto L14;
            return false;
        L14:
            return true;
        L11:
            return true;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean canDeleteSticker(TLRPC.Document r1) {
            return true;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean canEditSticker() {
            return true;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean canSchedule() {
            if (StickersAlert.m13090$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (StickersAlert.m13090$$Nest$fgetdelegate(this.this$0).canSchedule() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void deleteSticker(TLRPC.Document r6) {
            this.this$0.stickerSet.documents.remove(r6);
            boolean r0 = this.this$0.stickerSet.documents.isEmpty();
            if (r0 == false) goto L5;
            this.this$0.dismiss();
        L5:
            StickersAlert.m13088$$Nest$fgetadapter(this.this$0).notifyDataSetChanged();
            AlertDialog r1 = new AlertDialog(this.this$0.getContext(), 3, StickersAlert.access$000(this.this$0));
            r1.showDelayed(350);
            TLRPC.TL_stickers_removeStickerFromSet r2 = new TLRPC.TL_stickers_removeStickerFromSet();
            r2.sticker = MediaDataController.getInputStickerSetItem(r6, _UrlKt.FRAGMENT_ENCODE_SET).document;
            ConnectionsManager.getInstance(StickersAlert.access$100(this.this$0)).sendRequest(r2, new StickersAlert$1$$ExternalSyntheticLambda0(this, r0, r1));
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void editSticker(TLRPC.Document r2) {
            StickersAlert.editSticker(StickersAlert.m13102$$Nest$fgetparentFragment(this.this$0), this.this$0.stickerSet, r2);
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public long getDialogId() {
            if ((StickersAlert.m13102$$Nest$fgetparentFragment(this.this$0) instanceof ChatActivity) == true) goto L5;
            return 0;
        L5:
            return ((ChatActivity) StickersAlert.m13102$$Nest$fgetparentFragment(this.this$0)).getDialogId();
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean isInScheduleMode() {
            if (StickersAlert.m13090$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (StickersAlert.m13090$$Nest$fgetdelegate(this.this$0).isInScheduleMode() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needOpen() {
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needRemove() {
            if (StickersAlert.m13096$$Nest$fgetimportingStickers(this.this$0) == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needSend(int r1) {
            if (StickersAlert.m13090$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void openSet(TLRPC.InputStickerSet r1, boolean r2) {
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void remove(SendMessagesHelper.ImportingSticker r1) {
            StickersAlert.m13121$$Nest$mremoveSticker(this.this$0, r1);
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void sendSticker(TLRPC.Document r10, String r11, Object r12, boolean r13, int r14, int r15) {
            if (StickersAlert.m13090$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return;
        L5:
            StickersAlert.m13090$$Nest$fgetdelegate(this.this$0).onStickerSelected(r10, r11, r12, null, StickersAlert.m13089$$Nest$fgetclearsInputField(this.this$0), r13, r14, 0);
            this.this$0.dismiss();
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$10, reason: invalid class name */
    public class AnonymousClass10 extends RecyclerView.ItemDecoration {
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass10(StickersAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            r1.left = 0;
            r1.right = 0;
            r1.bottom = 0;
            r1.top = 0;
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$11, reason: invalid class name */
    public class AnonymousClass11 extends RecyclerView.OnScrollListener {
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass11(StickersAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            StickersAlert.m13124$$Nest$mupdateLayout(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$12, reason: invalid class name */
    public class AnonymousClass12 extends FrameLayout {
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass12(StickersAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (StickersAlert.m13095$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$13, reason: invalid class name */
    public class AnonymousClass13 implements ActionBarMenuItem.ActionBarSubMenuItemDelegate {
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass13(StickersAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarSubMenuItemDelegate
        public void onHideSubMenu() {
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarSubMenuItemDelegate
        public void onShowSubMenu() {
            StickersAlert.m13118$$Nest$mcheckOptions(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$14, reason: invalid class name */
    public class AnonymousClass14 extends ShareAlert {
        final /* synthetic */ StickersAlert this$0;

        public static /* synthetic */ void $r8$lambda$rpi4IgpXn_4vQnrV117QZLS5x6M(AnonymousClass14 r0, LongSparseArray r1, int r2) {
            r0.lambda$onSend$0(r1, r2);
        }

        public AnonymousClass14(StickersAlert r1, Context r2, ArrayList r3, String r4, boolean r5, String r6, boolean r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        private /* synthetic */ void lambda$onSend$0(LongSparseArray r9, int r10) {
            boolean r0 = StickersAlert.m13102$$Nest$fgetparentFragment(this.this$0) instanceof ChatActivity;
            StickersAlert r1 = this.this$0;
            if (r0 == false) goto L7;
            UndoView r8 = ((ChatActivity) StickersAlert.m13102$$Nest$fgetparentFragment(r1)).getUndoView();
        L5:
            UndoView r02 = r8;
            if (r02 != null) goto L12;
            return;
        L12:
            if (r9.size() != 1) goto L15;
            r02.showWithAction(((TLRPC.Dialog) r9.valueAt(0)).id, 53, Integer.valueOf(r10));
            return;
        L15:
            r02.showWithAction(0, 53, Integer.valueOf(r10), Integer.valueOf(r9.size()), null, null);
            return;
        L7:
            if ((StickersAlert.m13102$$Nest$fgetparentFragment(r1) instanceof ProfileActivity) == false) goto L9;
            r8 = ((ProfileActivity) StickersAlert.m13102$$Nest$fgetparentFragment(this.this$0)).getUndoView();
            goto L5
        L9:
            r8 = null;
            goto L5
        }

        @Override // org.telegram.ui.Components.ShareAlert, org.telegram.ui.ActionBar.BottomSheet
        public void dismissInternal() {
            super.dismissInternal();
            if ((StickersAlert.m13102$$Nest$fgetparentFragment(this.this$0) instanceof ChatActivity) == false) goto L8;
            AndroidUtilities.requestAdjustResize(StickersAlert.m13102$$Nest$fgetparentFragment(this.this$0).getParentActivity(), StickersAlert.m13102$$Nest$fgetparentFragment(this.this$0).getClassGuid());
            if (((ChatActivity) StickersAlert.m13102$$Nest$fgetparentFragment(this.this$0)).getChatActivityEnterView().getVisibility() != 0) goto L9;
            StickersAlert.m13102$$Nest$fgetparentFragment(this.this$0).getFragmentView().requestLayout();
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(LongSparseArray<TLRPC.Dialog> r1, int r2, TLRPC.TL_forumTopic r3, boolean r4) {
            if (r4 == true) goto L4;
            return;
        L4:
            AndroidUtilities.runOnUIThread(new StickersAlert$14$$ExternalSyntheticLambda0(this, r1, r2), 100);
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$15, reason: invalid class name */
    public class AnonymousClass15 extends URLSpanNoUnderline {
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass15(StickersAlert r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View r4) {
            MessagesController.getInstance(StickersAlert.access$2700(this.this$0)).openByUserName(getURL(), StickersAlert.m13102$$Nest$fgetparentFragment(this.this$0), 1);
            this.this$0.dismiss();
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$16, reason: invalid class name */
    public class AnonymousClass16 implements TextWatcher {
        final /* synthetic */ StickersAlert this$0;
        final /* synthetic */ EditTextBoldCursor val$editText;
        final /* synthetic */ TextView val$message;
        final /* synthetic */ int[] val$state;

        public AnonymousClass16(StickersAlert r1, int[] r2, TextView r3, EditTextBoldCursor r4) {
            this.this$0 = r1;
            this.val$state = r2;
            this.val$message = r3;
            this.val$editText = r4;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            if (this.val$state[0] == 2) goto L5;
            return;
        L5:
            StickersAlert.m13119$$Nest$mcheckUrlAvailable(this.this$0, this.val$message, this.val$editText.getText().toString(), false);
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$17, reason: invalid class name */
    public class AnonymousClass17 extends AnimatorListenerAdapter {
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass17(StickersAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            StickersAlert.m13110$$Nest$fgetstickerPreviewLayout(this.this$0).setVisibility(8);
            StickersAlert.m13109$$Nest$fgetstickerImageView(this.this$0).setImageDrawable(null);
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$18, reason: invalid class name */
    public class AnonymousClass18 extends AnimatorListenerAdapter {
        final /* synthetic */ StickersAlert this$0;
        final /* synthetic */ int val$num;
        final /* synthetic */ boolean val$show;

        public AnonymousClass18(StickersAlert r1, int r2, boolean r3) {
            this.this$0 = r1;
            this.val$num = r2;
            this.val$show = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r3) {
            if (StickersAlert.m13107$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] != null) goto L5;
            return;
        L5:
            if (StickersAlert.m13107$$Nest$fgetshadowAnimation(this.this$0)[this.val$num].equals(r3) == false) goto L9;
            StickersAlert.m13107$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] = null;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (StickersAlert.m13107$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] != null) goto L5;
            return;
        L5:
            if (StickersAlert.m13107$$Nest$fgetshadowAnimation(this.this$0)[this.val$num].equals(r3) == true) goto L7;
            return;
        L7:
            if (this.val$show == true) goto L9;
            StickersAlert.m13106$$Nest$fgetshadow(this.this$0)[this.val$num].setVisibility(4);
        L9:
            StickersAlert.m13107$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] = null;
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$19, reason: invalid class name */
    public class AnonymousClass19 implements Bulletin.Delegate {
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass19(StickersAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            if (StickersAlert.m13103$$Nest$fgetpickerBottomFrameLayout(this.this$0) != null) goto L5;
            return 0;
        L5:
            return StickersAlert.m13103$$Nest$fgetpickerBottomFrameLayout(this.this$0).getHeight();
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$2, reason: invalid class name */
    public class AnonymousClass2 extends PhotoViewer.EmptyPhotoViewerProvider {
        public AnonymousClass2() {
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean allowCaption() {
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean isEditingSticker() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$3, reason: invalid class name */
    public class AnonymousClass3 extends PhotoViewer.EmptyPhotoViewerProvider {
        public AnonymousClass3() {
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean allowCaption() {
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean isEditingSticker() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$4, reason: invalid class name */
    public class AnonymousClass4 extends Transition {
        final /* synthetic */ StickersAlert this$0;

        public static /* synthetic */ void $r8$lambda$n3kZ9xVM0Go821LQeDhdNnwF6ss(AnonymousClass4 r0, int r1, int r2, ValueAnimator r3) {
            r0.lambda$createAnimator$0(r1, r2, r3);
        }

        public AnonymousClass4(StickersAlert r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$createAnimator$0(int r2, int r3, ValueAnimator r4) {
            float r42 = r4.getAnimatedFraction();
            StickersAlert.m13094$$Nest$fgetgridView(this.this$0).setAlpha(r42);
            StickersAlert.m13113$$Nest$fgettitleTextView(this.this$0).setAlpha(r42);
            if (r2 == 0) goto L6;
            int r22 = (int) (r2 * (1.0f - r42));
            StickersAlert.m13122$$Nest$msetScrollOffsetY(this.this$0, r3 + r22);
            StickersAlert.m13094$$Nest$fgetgridView(this.this$0).setTranslationY(r22);
            return;
        }

        @Override // android.transition.Transition
        public void captureEndValues(TransitionValues r4) {
            r4.values.put("start", Boolean.FALSE);
            r4.values.put("offset", Integer.valueOf(StickersAlert.access$3200(this.this$0).getTop() + StickersAlert.m13105$$Nest$fgetscrollOffsetY(this.this$0)));
        }

        @Override // android.transition.Transition
        public void captureStartValues(TransitionValues r4) {
            r4.values.put("start", Boolean.TRUE);
            r4.values.put("offset", Integer.valueOf(StickersAlert.access$3100(this.this$0).getTop() + StickersAlert.m13105$$Nest$fgetscrollOffsetY(this.this$0)));
        }

        @Override // android.transition.Transition
        public Animator createAnimator(ViewGroup r3, TransitionValues r4, TransitionValues r5) {
            int r32 = StickersAlert.m13105$$Nest$fgetscrollOffsetY(this.this$0);
            int r42 = ((Integer) r4.values.get("offset")).intValue() - ((Integer) r5.values.get("offset")).intValue();
            ValueAnimator r52 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r52.setDuration(250);
            r52.addUpdateListener(new StickersAlert$4$$ExternalSyntheticLambda0(this, r42, r32));
            return r52;
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$5, reason: invalid class name */
    public class AnonymousClass5 extends FrameLayout {
        private boolean fullHeight;
        private int lastNotifyWidth;
        private RectF rect;
        private Boolean statusBarOpen;
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass5(StickersAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new RectF();
        }

        private void updateLightStatusBar(boolean r7) {
            Boolean r0 = this.statusBarOpen;
            if (r0 != null) goto L5;
        L7:
            boolean r2 = false;
            if (AndroidUtilities.computePerceivedBrightness(StickersAlert.access$800(this.this$0, Theme.key_dialogBackground)) <= 0.721f) goto L10;
            boolean r02 = true;
        L12:
            if (AndroidUtilities.computePerceivedBrightness(Theme.blendOver(StickersAlert.access$900(this.this$0, Theme.key_actionBarDefault), 855638016)) <= 0.721f) goto L14;
            r2 = true;
        L14:
            this.statusBarOpen = Boolean.valueOf(r7);
            if (r7 == true) goto L18;
            r02 = r2;
        L18:
            AndroidUtilities.setLightStatusBar(this.this$0.getWindow(), r02);
            return;
        L10:
            r02 = false;
            goto L12
        L5:
            if (r0.booleanValue() != r7) goto L7;
        }

        @Override // android.view.View
        public void onDraw(Canvas r13) {
            int r0 = (StickersAlert.m13105$$Nest$fgetscrollOffsetY(this.this$0) - StickersAlert.access$1000(this.this$0)) + AndroidUtilities.dp(6.0f);
            int r1 = (StickersAlert.m13105$$Nest$fgetscrollOffsetY(this.this$0) - StickersAlert.access$1100(this.this$0)) - AndroidUtilities.dp(13.0f);
            int r2 = AndroidUtilities.statusBarHeight;
            int r12 = r1 + r2;
            int r02 = r0 + r2;
            boolean r3 = false;
            if (this.fullHeight == false) goto L12;
            int r22 = StickersAlert.access$1200(this.this$0) + r12;
            int r5 = AndroidUtilities.statusBarHeight;
            if (r22 >= (r5 * 2)) goto L7;
            r12 = r12 - Math.min(r5, ((r5 * 2) - r12) - StickersAlert.access$1300(this.this$0));
            float r23 = 1.0f - Math.min(1.0f, (r2 * 2) / AndroidUtilities.statusBarHeight);
        L8:
            int r52 = StickersAlert.access$1400(this.this$0) + r12;
            int r6 = AndroidUtilities.statusBarHeight;
            if (r52 >= r6) goto L11;
            int r53 = Math.min(r6, (r6 - r12) - StickersAlert.access$1500(this.this$0));
        L13:
            StickersAlert.access$1600(this.this$0).setBounds(0, r12, getMeasuredWidth(), getMeasuredHeight());
            StickersAlert.access$1700(this.this$0).draw(r13);
            if (r23 == 1.0f) goto L16;
            Theme.dialogs_onlineCirclePaint.setColor(StickersAlert.access$1800(this.this$0, Theme.key_dialogBackground));
            this.rect.set(StickersAlert.access$1900(this.this$0), StickersAlert.access$2000(this.this$0) + r12, getMeasuredWidth() - StickersAlert.access$2100(this.this$0), (StickersAlert.access$2200(this.this$0) + r12) + AndroidUtilities.dp(24.0f));
            r13.drawRoundRect(this.rect, AndroidUtilities.dp(12.0f) * r23, AndroidUtilities.dp(12.0f) * r23, Theme.dialogs_onlineCirclePaint);
        L16:
            int r14 = AndroidUtilities.REPLACING_TAG_TYPE_LINK;
            int r15 = AndroidUtilities.dp(36.0f);
            this.rect.set((getMeasuredWidth() - r15) / 2, r02, (getMeasuredWidth() + r15) / 2, AndroidUtilities.dp(4.0f) + r02);
            Theme.dialogs_onlineCirclePaint.setColor(StickersAlert.access$2300(this.this$0, Theme.key_sheet_scrollUp));
            Theme.dialogs_onlineCirclePaint.setAlpha((int) (r1.getAlpha() * Math.max(0.0f, Math.min(1.0f, (r02 - AndroidUtilities.statusBarHeight) / AndroidUtilities.dp(16.0f)))));
            r13.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
            if (r53 <= (AndroidUtilities.statusBarHeight / 2)) goto L19;
            r3 = true;
        L19:
            updateLightStatusBar(r3);
            if (r53 <= 0) goto L23;
            Theme.dialogs_onlineCirclePaint.setColor(StickersAlert.access$2400(this.this$0, Theme.key_dialogBackground));
            r13.drawRect(StickersAlert.access$2500(this.this$0), AndroidUtilities.statusBarHeight - r53, getMeasuredWidth() - StickersAlert.access$2600(this.this$0), AndroidUtilities.statusBarHeight, Theme.dialogs_onlineCirclePaint);
            return;
        L23:
            return;
        L11:
            r53 = 0;
            goto L13
        L7:
            r23 = 1.0f;
            goto L8
        L12:
            r53 = 0;
            r23 = 1.0f;
            goto L13
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L11;
            if (StickersAlert.m13105$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L11;
            if (r3.getY() >= StickersAlert.m13105$$Nest$fgetscrollOffsetY(this.this$0)) goto L11;
            this.this$0.dismiss();
            return true;
        L11:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
            int r1 = r6 - r4;
            if (this.lastNotifyWidth == r1) goto L9;
            this.lastNotifyWidth = r1;
            if (StickersAlert.m13088$$Nest$fgetadapter(this.this$0) == null) goto L9;
            if (StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0) == null) goto L9;
            StickersAlert.m13088$$Nest$fgetadapter(this.this$0).notifyDataSetChanged();
        L9:
            super.onLayout(r3, r4, r5, r6, r7);
            StickersAlert.m13124$$Nest$mupdateLayout(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r14, int r15) {
            int r152 = View.MeasureSpec.getSize(r15);
            boolean r1 = true;
            StickersAlert.m13115$$Nest$fputignoreLayout(this.this$0, true);
            setPadding(StickersAlert.access$200(this.this$0), AndroidUtilities.statusBarHeight, StickersAlert.access$300(this.this$0), 0);
            StickersAlert.m13115$$Nest$fputignoreLayout(this.this$0, false);
            boolean r0 = StickersAlert.m13120$$Nest$misEmoji(this.this$0);
            StickersAlert r2 = this.this$0;
            if (r0 == false) goto L12;
            int r02 = StickersAlert.m13094$$Nest$fgetgridView(r2).getMeasuredWidth();
            if (r02 != 0) goto L7;
            r02 = AndroidUtilities.displaySize.x;
        L7:
            GridAdapter r22 = StickersAlert.m13088$$Nest$fgetadapter(this.this$0);
            if (AndroidUtilities.isTablet() == false) goto L10;
            float r6 = 60.0f;
        L11:
            GridAdapter.m13130$$Nest$fputstickersPerRow(r22, Math.max(1, r02 / AndroidUtilities.dp(r6)));
            StickersAlert.m13117$$Nest$fputitemSize(this.this$0, (View.MeasureSpec.getSize(r14) - AndroidUtilities.dp(36.0f)) / GridAdapter.m13127$$Nest$fgetstickersPerRow(StickersAlert.m13088$$Nest$fgetadapter(this.this$0)));
            StickersAlert r03 = this.this$0;
            StickersAlert.m13116$$Nest$fputitemHeight(r03, StickersAlert.m13100$$Nest$fgetitemSize(r03));
        L13:
            float r04 = GridAdapter.m13127$$Nest$fgetstickersPerRow(StickersAlert.m13088$$Nest$fgetadapter(this.this$0));
            ViewGroup.MarginLayoutParams r23 = (ViewGroup.MarginLayoutParams) StickersAlert.m13094$$Nest$fgetgridView(this.this$0).getLayoutParams();
            int r62 = 3;
            if (StickersAlert.m13096$$Nest$fgetimportingStickers(this.this$0) == null) goto L18;
            int r3 = ((AndroidUtilities.dp(48.0f) + r23.bottomMargin) + (Math.max(3, (int) Math.ceil(StickersAlert.m13096$$Nest$fgetimportingStickers(this.this$0).size() / r04)) * StickersAlert.m13099$$Nest$fgetitemHeight(this.this$0))) + StickersAlert.access$400(this.this$0);
            int r05 = AndroidUtilities.statusBarHeight;
        L16:
            int r32 = r3 + r05;
        L29:
            if (StickersAlert.m13120$$Nest$misEmoji(this.this$0) == false) goto L32;
            r32 = (int) (r32 + (StickersAlert.m13099$$Nest$fgetitemHeight(this.this$0) * 0.15f));
        L32:
            if (StickersAlert.m13091$$Nest$fgetdescriptionTextView(this.this$0) == null) goto L34;
            StickersAlert.m13091$$Nest$fgetdescriptionTextView(this.this$0).measure(r14, View.MeasureSpec.makeMeasureSpec(9999, Integer.MIN_VALUE));
            r32 = r32 + StickersAlert.m13091$$Nest$fgetdescriptionTextView(this.this$0).getMeasuredHeight();
        L34:
            float r06 = r152 / 5.0f;
            if (r32 >= (r06 * 3.2d)) goto L37;
            int r07 = 0;
        L38:
            if (r07 == 0) goto L41;
            if (r32 >= r152) goto L41;
            r07 = r07 - (r152 - r32);
        L41:
            if (r07 != 0) goto L44;
            r07 = StickersAlert.access$700(this.this$0);
        L44:
            if (StickersAlert.m13091$$Nest$fgetdescriptionTextView(this.this$0) == null) goto L47;
            r07 = r07 + (AndroidUtilities.dp(32.0f) + StickersAlert.m13091$$Nest$fgetdescriptionTextView(this.this$0).getMeasuredHeight());
        L47:
            if (StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0) == null) goto L50;
            r07 = r07 + AndroidUtilities.dp(8.0f);
        L50:
            if (StickersAlert.m13094$$Nest$fgetgridView(this.this$0).getPaddingTop() == r07) goto L52;
            StickersAlert.m13115$$Nest$fputignoreLayout(this.this$0, true);
            StickersAlert.m13094$$Nest$fgetgridView(this.this$0).setPadding(AndroidUtilities.dp(10.0f), r07, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(8.0f));
            StickersAlert.m13093$$Nest$fgetemptyView(this.this$0).setPadding(0, r07, 0, 0);
            StickersAlert.m13115$$Nest$fputignoreLayout(this.this$0, false);
        L52:
            if (r32 >= r152) goto L55;
            r1 = false;
        L55:
            this.fullHeight = r1;
            super.onMeasure(r14, View.MeasureSpec.makeMeasureSpec(Math.min(r32, r152), TLObject.FLAG_30));
            return;
        L37:
            r07 = (int) (r06 * 2.0f);
            goto L38
        L18:
            if (StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0) == null) goto L20;
            r32 = ((((AndroidUtilities.dp(8.0f) + r23.bottomMargin) + (AndroidUtilities.dp(60.0f) * StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0).size())) + (GridAdapter.m13128$$Nest$fgetstickersRowCount(StickersAlert.m13088$$Nest$fgetadapter(this.this$0)) * StickersAlert.m13099$$Nest$fgetitemHeight(this.this$0))) + StickersAlert.access$500(this.this$0)) + AndroidUtilities.dp(24.0f);
            goto L29
        L20:
            int r33 = AndroidUtilities.dp(48.0f) + r23.bottomMargin;
            if (StickersAlert.m13120$$Nest$misEmoji(this.this$0) == false) goto L24;
            r62 = 2;
        L24:
            if (this.this$0.stickerSet == null) goto L26;
            int r08 = (int) Math.ceil(r2.documents.size() / r04);
        L27:
            r3 = (r33 + (Math.max(r62, r08) * StickersAlert.m13099$$Nest$fgetitemHeight(this.this$0))) + StickersAlert.access$600(this.this$0);
            r05 = AndroidUtilities.statusBarHeight;
            goto L16
        L26:
            r08 = 0;
            goto L27
        L10:
            r6 = 45.0f;
            goto L11
        L12:
            GridAdapter.m13130$$Nest$fputstickersPerRow(StickersAlert.m13088$$Nest$fgetadapter(r2), 5);
            StickersAlert.m13117$$Nest$fputitemSize(this.this$0, (View.MeasureSpec.getSize(r14) - AndroidUtilities.dp(36.0f)) / GridAdapter.m13127$$Nest$fgetstickersPerRow(StickersAlert.m13088$$Nest$fgetadapter(this.this$0)));
            StickersAlert.m13116$$Nest$fputitemHeight(this.this$0, AndroidUtilities.dp(82.0f));
            goto L13
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (StickersAlert.m13095$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerListView {
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass6(StickersAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r7, View r8, long r9) {
            if ((r8 instanceof StickerEmojiCell) == false) goto L9;
            if (StickersAlert.m13098$$Nest$fgetisEditModeEnabled(this.this$0) == false) goto L9;
            int r0 = StickersAlert.m13094$$Nest$fgetgridView(this.this$0).getChildViewHolder(r8).getAdapterPosition();
            r7.save();
            r7.rotate(StickersAlert.m13112$$Nest$fgetstickersShaker(this.this$0).getRotationValueForPos(r0), r8.getLeft() + (r8.getMeasuredWidth() / 2.0f), r8.getTop() + (r8.getMeasuredHeight() / 2.0f));
            r7.translate(StickersAlert.m13112$$Nest$fgetstickersShaker(this.this$0).getTranslateXValueForPos(r0), StickersAlert.m13112$$Nest$fgetstickersShaker(this.this$0).getTranslateYValueForPos(r0));
            boolean r82 = super.drawChild(r7, r8, r9);
            r7.restore();
            invalidate();
            return r82;
        L9:
            return super.drawChild(r7, r8, r9);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r7) {
            if (StickersAlert.m13098$$Nest$fgetisEditModeEnabled(this.this$0) == true) goto L5;
            boolean r72 = ContentPreviewViewer.getInstance().onInterceptTouchEvent(r7, StickersAlert.m13094$$Nest$fgetgridView(this.this$0), 0, StickersAlert.m13104$$Nest$fgetpreviewDelegate(this.this$0), this.resourcesProvider);
            if (super.onInterceptTouchEvent(r7) == true) goto L12;
            if (r72 == true) goto L14;
            return false;
        L14:
            return true;
        L12:
            return true;
        L5:
            return super.onInterceptTouchEvent(r7);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (StickersAlert.m13095$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$7, reason: invalid class name */
    public class AnonymousClass7 extends GridLayoutManager {
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass7(StickersAlert r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public boolean isLayoutRTL() {
            if (StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0) != null) goto L5;
            return false;
        L5:
            if (LocaleController.isRTL == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$8, reason: invalid class name */
    public class AnonymousClass8 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass8(StickersAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r2) {
            if (StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0) == null) goto L7;
            if ((GridAdapter.m13125$$Nest$fgetcache(StickersAlert.m13088$$Nest$fgetadapter(this.this$0)).get(r2) instanceof Integer) == false) goto L7;
        L9:
            return GridAdapter.m13127$$Nest$fgetstickersPerRow(StickersAlert.m13088$$Nest$fgetadapter(this.this$0));
        L7:
            if (r2 == GridAdapter.m13129$$Nest$fgettotalItems(StickersAlert.m13088$$Nest$fgetadapter(this.this$0))) goto L9;
            return 1;
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersAlert$9, reason: invalid class name */
    public class AnonymousClass9 extends ItemTouchHelper.SimpleCallback {
        private int movedPos;
        final /* synthetic */ StickersAlert this$0;

        public AnonymousClass9(StickersAlert r1, int r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.movedPos = -1;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
        public int getDragDirs(RecyclerView r3, RecyclerView.ViewHolder r4) {
            if (r4.getItemViewType() != 3) goto L7;
            return 0;
        L7:
            return super.getDragDirs(r3, r4);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r3, RecyclerView.ViewHolder r4, RecyclerView.ViewHolder r5) {
            if (r4.getItemViewType() != 3) goto L6;
            return false;
        L6:
            if (r4.getItemViewType() == r5.getItemViewType()) goto L9;
            return false;
        L9:
            if (this.this$0.stickerSet != null) goto L11;
            return false;
        L11:
            int r32 = r4.getAdapterPosition();
            int r42 = r5.getAdapterPosition();
            TLRPC.Document r52 = this.this$0.stickerSet.documents.remove(r32);
            this.this$0.stickerSet.documents.add(r42, r52);
            StickersAlert.m13088$$Nest$fgetadapter(this.this$0).notifyItemMoved(r32, r42);
            this.movedPos = r42;
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onMoved(RecyclerView r1, RecyclerView.ViewHolder r2, int r3, RecyclerView.ViewHolder r4, int r5, int r6, int r7) {
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r2, int r3) {
            super.onSelectedChanged(r2, r3);
            if (r3 != 0) goto L11;
            if (StickersAlert.m13092$$Nest$fgetdraggedDocument(this.this$0) == null) goto L11;
            if (this.movedPos <= 0) goto L11;
            TLRPC.TL_stickers_changeStickerPosition r22 = new TLRPC.TL_stickers_changeStickerPosition();
            r22.position = this.movedPos;
            r22.sticker = MediaDataController.getInputStickerSetItem(StickersAlert.m13092$$Nest$fgetdraggedDocument(this.this$0), _UrlKt.FRAGMENT_ENCODE_SET).document;
            this.movedPos = -1;
            StickersAlert.m13114$$Nest$fputdraggedDocument(this.this$0, null);
            return;
        L11:
            if (r3 != 2) goto L14;
            StickersAlert.m13114$$Nest$fputdraggedDocument(this.this$0, ((StickerEmojiCell) r2.itemView).getSticker());
            return;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    public static class AddStickerBtnView extends FrameLayout {
        public AddStickerBtnView(Context r6, Theme.ResourcesProvider r7) {
            super(r6);
            View r0 = new View(r6);
            int r1 = AndroidUtilities.dp(28.0f);
            int r2 = Theme.key_chat_emojiPanelIcon;
            ShapeDrawable r12 = Theme.createRoundRectDrawable(r1, Theme.multAlpha(Theme.getColor(r2, r7), 0.12f));
            Drawable r62 = r6.getResources().getDrawable(R.drawable.filled_add_sticker).mutate();
            r62.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2, r7), PorterDuff.Mode.MULTIPLY));
            CombinedDrawable r72 = new CombinedDrawable(r12, r62);
            r72.setCustomSize(AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
            r72.setIconSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
            r0.setBackground(r72);
            ScaleStateListAnimator.apply(r0);
            addView(r0, LayoutHelper.createFrame(56, 56, 17));
        }
    }

    public class GridAdapter extends RecyclerListView.SelectionAdapter {
        private SparseArray<Object> cache;
        private Context context;
        private SparseArray<TLRPC.StickerSetCovered> positionsToSets;
        private int stickersPerRow;
        private int stickersRowCount;
        final /* synthetic */ StickersAlert this$0;
        private int totalItems;

        /* renamed from: org.telegram.ui.Components.StickersAlert$GridAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends StickerEmojiCell {
            final /* synthetic */ GridAdapter this$1;

            public AnonymousClass1(GridAdapter r1, Context r2, boolean r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(StickersAlert.m13100$$Nest$fgetitemSize(this.this$1.this$0), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(StickersAlert.m13100$$Nest$fgetitemSize(this.this$1.this$0), TLObject.FLAG_30));
            }
        }

        public static /* synthetic */ void $r8$lambda$9_8PcjeL9hOi1uxenn3VPiCJx0s(GridAdapter r0, StickerEmojiCell r1, View r2) {
            r0.lambda$onBindViewHolder$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetcache, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseArray m13125$$Nest$fgetcache(GridAdapter r0) {
            return r0.cache;
        }

        /* renamed from: -$$Nest$fgetpositionsToSets, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseArray m13126$$Nest$fgetpositionsToSets(GridAdapter r0) {
            return r0.positionsToSets;
        }

        /* renamed from: -$$Nest$fgetstickersPerRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13127$$Nest$fgetstickersPerRow(GridAdapter r0) {
            return r0.stickersPerRow;
        }

        /* renamed from: -$$Nest$fgetstickersRowCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13128$$Nest$fgetstickersRowCount(GridAdapter r0) {
            return r0.stickersRowCount;
        }

        /* renamed from: -$$Nest$fgettotalItems, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13129$$Nest$fgettotalItems(GridAdapter r0) {
            return r0.totalItems;
        }

        /* renamed from: -$$Nest$fputstickersPerRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13130$$Nest$fputstickersPerRow(GridAdapter r0, int r1) {
            r0.stickersPerRow = r1;
        }

        public GridAdapter(StickersAlert r1, Context r2) {
            this.this$0 = r1;
            this.cache = new SparseArray();
            this.positionsToSets = new SparseArray();
            this.context = r2;
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(StickerEmojiCell r1, View r2) {
            ContentPreviewViewer.getInstance().setDelegate(StickersAlert.m13104$$Nest$fgetpreviewDelegate(this.this$0));
            ContentPreviewViewer.getInstance().showMenuFor(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.totalItems;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0) == null) goto L13;
            Object r2 = this.cache.get(r3);
            if (r2 != null) goto L7;
            return 1;
        L7:
            if ((r2 instanceof TLRPC.Document) == false) goto L9;
            return 0;
        L9:
            return 2;
        L13:
            TLRPC.TL_messages_stickerSet r22 = this.this$0.stickerSet;
            if (r22 != null) goto L16;
        L19:
            return 0;
        L16:
            if (r22.documents.size() != r3) goto L19;
            return 3;
        }

        public void getThemeDescriptions(List<ThemeDescription> r2, ThemeDescription.ThemeDescriptionDelegate r3) {
            if (StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0) == null) goto L6;
            FeaturedStickerSetInfoCell.createThemeDescriptions(r2, StickersAlert.m13094$$Nest$fgetgridView(this.this$0), r3);
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            ArrayList r0 = StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0);
            StickersAlert r1 = this.this$0;
            int r3 = 0;
            if (r0 == null) goto L35;
            int r02 = StickersAlert.m13094$$Nest$fgetgridView(r1).getMeasuredWidth();
            if (r02 != 0) goto L7;
            r02 = AndroidUtilities.displaySize.x;
        L7:
            this.stickersPerRow = r02 / AndroidUtilities.dp(72.0f);
            StickersAlert.m13101$$Nest$fgetlayoutManager(this.this$0).setSpanCount(this.stickersPerRow);
            this.cache.clear();
            this.positionsToSets.clear();
            this.totalItems = 0;
            this.stickersRowCount = 0;
            int r03 = 0;
        L9:
            if (r03 >= StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0).size()) goto L52;
            TLRPC.StickerSetCovered r12 = (TLRPC.StickerSetCovered) StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0).get(r03);
            if ((r12 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L13;
            List r4 = ((TLRPC.TL_stickerSetFullCovered) r12).documents;
        L14:
            if (r4 == null) goto L16;
            r4 = r4.subList(0, Math.min(r4.size(), this.stickersPerRow));
        L16:
            if (r4 == null) goto L34;
            if (r4.isEmpty() == true) goto L20;
        L22:
            this.stickersRowCount++;
            this.positionsToSets.put(this.totalItems, r12);
            SparseArray<Object> r5 = this.cache;
            int r6 = this.totalItems;
            this.totalItems = r6 + 1;
            r5.put(r6, Integer.valueOf(r03));
            int r52 = this.totalItems / this.stickersPerRow;
            if (r4.isEmpty() == true) goto L28;
            int r53 = (int) Math.ceil(r4.size() / this.stickersPerRow);
            int r62 = 0;
        L26:
            if (r62 >= r4.size()) goto L29;
            this.cache.put(this.totalItems + r62, r4.get(r62));
            r62 = r62 + 1;
        L29:
            int r42 = 0;
        L30:
            int r63 = this.stickersPerRow;
            if (r42 >= (r53 * r63)) goto L33;
            this.positionsToSets.put(this.totalItems + r42, r12);
            r42 = r42 + 1;
            goto L30
        L33:
            this.totalItems += r53 * r63;
            goto L34
        L28:
            this.cache.put(this.totalItems, r12.cover);
            r53 = 1;
            goto L29
        L20:
            if (r12.cover != null) goto L22;
        L34:
            r03 = r03 + 1;
            goto L9
        L13:
            r4 = r12.covers;
        L52:
            super.notifyDataSetChanged();
            return;
        L35:
            ArrayList r04 = StickersAlert.m13097$$Nest$fgetimportingStickersPaths(r1);
            StickersAlert r13 = this.this$0;
            if (r04 == null) goto L38;
            this.totalItems = StickersAlert.m13097$$Nest$fgetimportingStickersPaths(r13).size();
            goto L52
        L38:
            TLRPC.TL_messages_stickerSet r05 = r13.stickerSet;
            if (r05 == null) goto L41;
            r3 = r05.documents.size();
        L41:
            this.totalItems = r3;
            TLRPC.TL_messages_stickerSet r06 = this.this$0.stickerSet;
            if (r06 == null) goto L52;
            if (r06.set.creator == false) goto L52;
            if (r06.documents.size() >= 120) goto L52;
            TLRPC.StickerSet r07 = this.this$0.stickerSet.set;
            if (r07.masks == true) goto L52;
            if (r07.emojis == true) goto L52;
            this.totalItems++;
            goto L52
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRemoved(int r2) {
            if (StickersAlert.m13097$$Nest$fgetimportingStickersPaths(this.this$0) == null) goto L5;
            this.totalItems = StickersAlert.m13097$$Nest$fgetimportingStickersPaths(this.this$0).size();
        L5:
            super.notifyItemRemoved(r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            if (StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0) == null) goto L18;
            int r0 = r8.getItemViewType();
            if (r0 != 0) goto L7;
            TLRPC.Document r02 = (TLRPC.Document) this.cache.get(r9);
            ((StickerEmojiCell) r8.itemView).setSticker(r02, this.positionsToSets.get(r9), false);
            return;
        L7:
            if (r0 != 1) goto L9;
            ((EmptyCell) r8.itemView).setHeight(AndroidUtilities.dp(82.0f));
            return;
        L9:
            if (r0 != 2) goto L29;
            TLRPC.StickerSetCovered r7 = (TLRPC.StickerSetCovered) StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0).get(((Integer) this.cache.get(r9)).intValue());
            ((FeaturedStickerSetInfoCell) r8.itemView).setStickerSet(r7, false);
            return;
        L29:
            return;
        L18:
            if (StickersAlert.m13096$$Nest$fgetimportingStickers(this.this$0) == null) goto L22;
            ((StickerEmojiCell) r8.itemView).setSticker((SendMessagesHelper.ImportingSticker) StickersAlert.m13097$$Nest$fgetimportingStickersPaths(this.this$0).get(r9));
            return;
        L22:
            if (r8.getItemViewType() == 3) goto L28;
            StickerEmojiCell r03 = (StickerEmojiCell) r8.itemView;
            TLRPC.TL_messages_stickerSet r82 = this.this$0.stickerSet;
            if (r82 == null) goto L30;
            TLRPC.Document r1 = r82.documents.get(r9);
            StickersAlert r83 = this.this$0;
            r03.setSticker(r1, null, r83.stickerSet, null, StickersAlert.m13108$$Nest$fgetshowEmoji(r83), StickersAlert.m13098$$Nest$fgetisEditModeEnabled(this.this$0));
            r03.editModeIcon.setOnClickListener(new StickersAlert$GridAdapter$$ExternalSyntheticLambda0(this, r03));
            return;
        L30:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 != 0) goto L4;
            AnonymousClass1 r72 = new AnonymousClass1(this, this.context, false, StickersAlert.access$2800(this.this$0));
            r72.getImageView().setLayerNum(7);
            FrameLayout r73 = r72;
        L11:
            FrameLayout r6 = r73;
        L16:
            return new RecyclerListView.Holder(r6);
        L4:
            if (r8 != 1) goto L6;
            r73 = new EmptyCell(this.context);
            goto L11
        L6:
            if (r8 != 2) goto L8;
            r6 = new FeaturedStickerSetInfoCell(this.context, 8, true, false, StickersAlert.access$2900(this.this$0));
            goto L16
        L8:
            if (r8 == 3) goto L10;
            r6 = null;
            goto L16
        L10:
            r73 = new AddStickerBtnView(this.context, StickersAlert.access$3000(this.this$0));
            goto L11
        }

        public void updateColors() {
            if (StickersAlert.m13111$$Nest$fgetstickerSetCovereds(this.this$0) == null) goto L10;
            int r0 = StickersAlert.m13094$$Nest$fgetgridView(this.this$0).getChildCount();
            int r1 = 0;
        L5:
            if (r1 >= r0) goto L14;
            View r2 = StickersAlert.m13094$$Nest$fgetgridView(this.this$0).getChildAt(r1);
            if ((r2 instanceof FeaturedStickerSetInfoCell) == false) goto L9;
            ((FeaturedStickerSetInfoCell) r2).updateColors();
        L9:
            r1 = r1 + 1;
            goto L5
        L14:
            return;
        }
    }

    public interface StickersAlertCustomButtonDelegate {
        int getCustomButtonColorKey();

        int getCustomButtonRippleColorKey();

        String getCustomButtonText();

        int getCustomButtonTextColorKey();

        boolean onCustomButtonPressed();
    }

    /* loaded from: classes3.dex */
    public interface StickersAlertDelegate {
        boolean canSchedule();

        boolean isInScheduleMode();

        void onStickerSelected(TLRPC.Document r1, String r2, Object r3, MessageObject.SendAnimationData r4, boolean r5, boolean r6, int r7, int r8);
    }

    public interface StickersAlertInstallDelegate {
        void onStickerSetInstalled();

        void onStickerSetUninstalled();
    }

    public static class StickersShaker {
        private final List<Float> imageRotations;
        private final List<Float> imageTranslationsX;
        private final List<Float> imageTranslationsY;
        private final List<ValueAnimator> rotateAnimators;
        private final List<ValueAnimator> translateXAnimators;
        private final List<ValueAnimator> translateYAnimators;

        public static /* synthetic */ void $r8$lambda$9tYwbcuiJ52iBZiSlhWMeiFVrt8(StickersShaker r0, int r1, ValueAnimator r2) {
            r0.lambda$startShake$1(r1, r2);
        }

        /* renamed from: $r8$lambda$StVARy4ptJIdL5cuTk6aY9--zg4, reason: not valid java name */
        public static /* synthetic */ void m13131$r8$lambda$StVARy4ptJIdL5cuTk6aY9zg4(StickersShaker r0, int r1, ValueAnimator r2) {
            r0.lambda$stopShake$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$TvY_Qfy3QspjBbGVoUC_6KktSYk(StickersShaker r0, int r1, ValueAnimator r2) {
            r0.lambda$stopShake$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$VXEPgoTNcOl4jWnxsYEwq2_jwPg(StickersShaker r0, int r1, ValueAnimator r2) {
            r0.lambda$startShake$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$YjuUygLZQqfEZmhLxpLoNyOBsGg(StickersShaker r0, int r1, ValueAnimator r2) {
            r0.lambda$startShake$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$ajE9YMYDBz7ghTGaXVZl8evgsqI(StickersShaker r0, int r1, ValueAnimator r2) {
            r0.lambda$stopShake$5(r1, r2);
        }

        private StickersShaker() {
            this.rotateAnimators = new ArrayList();
            this.translateXAnimators = new ArrayList();
            this.translateYAnimators = new ArrayList();
            this.imageRotations = new ArrayList();
            this.imageTranslationsX = new ArrayList();
            this.imageTranslationsY = new ArrayList();
        }

        public /* synthetic */ StickersShaker(StickersAlertIA r1) {
            this();
        }

        private void init() {
            Float r0 = Float.valueOf(0.0f);
            if (this.imageRotations.isEmpty() == false) goto L9;
            int r1 = 0;
        L7:
            if (r1 >= 6) goto L11;
            this.imageRotations.add(r0);
            this.imageTranslationsX.add(r0);
            this.imageTranslationsY.add(r0);
            r1 = r1 + 1;
            goto L7
        L11:
            return;
        }

        private /* synthetic */ void lambda$startShake$0(int r1, ValueAnimator r2) {
            List<Float> r0 = this.imageRotations;
            Float r22 = (Float) r2.getAnimatedValue();
            r22.floatValue();
            r0.set(r1, r22);
        }

        private /* synthetic */ void lambda$startShake$1(int r1, ValueAnimator r2) {
            List<Float> r0 = this.imageTranslationsX;
            Float r22 = (Float) r2.getAnimatedValue();
            r22.floatValue();
            r0.set(r1, r22);
        }

        private /* synthetic */ void lambda$startShake$2(int r1, ValueAnimator r2) {
            List<Float> r0 = this.imageTranslationsY;
            Float r22 = (Float) r2.getAnimatedValue();
            r22.floatValue();
            r0.set(r1, r22);
        }

        private /* synthetic */ void lambda$stopShake$3(int r1, ValueAnimator r2) {
            List<Float> r0 = this.imageRotations;
            Float r22 = (Float) r2.getAnimatedValue();
            r22.floatValue();
            r0.set(r1, r22);
        }

        private /* synthetic */ void lambda$stopShake$4(int r1, ValueAnimator r2) {
            List<Float> r0 = this.imageTranslationsX;
            Float r22 = (Float) r2.getAnimatedValue();
            r22.floatValue();
            r0.set(r1, r22);
        }

        private /* synthetic */ void lambda$stopShake$5(int r1, ValueAnimator r2) {
            List<Float> r0 = this.imageTranslationsY;
            Float r22 = (Float) r2.getAnimatedValue();
            r22.floatValue();
            r0.set(r1, r22);
        }

        public float getRotationValueForPos(int r2) {
            if (this.imageRotations.isEmpty() == false) goto L6;
            return 0.0f;
        L6:
            List<Float> r1 = this.imageRotations;
            return r1.get(r2 - ((r2 / 6) * 6)).floatValue();
        }

        public float getTranslateXValueForPos(int r2) {
            if (this.imageTranslationsX.isEmpty() == false) goto L6;
            return 0.0f;
        L6:
            List<Float> r1 = this.imageTranslationsX;
            return r1.get(r2 - ((r2 / 6) * 6)).floatValue();
        }

        public float getTranslateYValueForPos(int r2) {
            if (this.imageTranslationsY.isEmpty() == false) goto L6;
            return 0.0f;
        L6:
            List<Float> r1 = this.imageTranslationsY;
            return r1.get(r2 - ((r2 / 6) * 6)).floatValue();
        }

        public void startShake() {
            char r1 = 0;
            stopShake(false);
            init();
            int r2 = 0;
        L4:
            if (r2 >= 6) goto L6;
            long r3 = (long) (Utilities.random.nextFloat() * 300.0f);
            ValueAnimator r6 = ValueAnimator.ofFloat(new float[]{0.0f, -2.0f, 0.0f, 2.0f, 0.0f});
            r6.addUpdateListener(new StickersAlert$StickersShaker$$ExternalSyntheticLambda3(this, r2));
            r6.setRepeatCount(-1);
            r6.setRepeatMode(1);
            r6.setInterpolator(new LinearInterpolator());
            r6.setCurrentPlayTime(r3);
            r6.setDuration(300);
            r6.start();
            float r11 = AndroidUtilities.dp(0.5f);
            float[] r5 = new float[5];
            r5[r1] = 0.0f;
            r5[1] = r11;
            r5[2] = 0.0f;
            r5[3] = -r11;
            r5[4] = 0.0f;
            ValueAnimator r52 = ValueAnimator.ofFloat(r5);
            char r16 = r1;
            r52.addUpdateListener(new StickersAlert$StickersShaker$$ExternalSyntheticLambda4(this, r2));
            r52.setRepeatCount(-1);
            r52.setRepeatMode(1);
            r52.setInterpolator(new LinearInterpolator());
            r52.setCurrentPlayTime(r3);
            r52.setDuration(360);
            r52.start();
            float[] r12 = new float[4];
            r12[r16] = 0.0f;
            r12[1] = r11;
            r12[2] = 0.0f - r11;
            r12[3] = 0.0f;
            ValueAnimator r13 = ValueAnimator.ofFloat(r12);
            r13.addUpdateListener(new StickersAlert$StickersShaker$$ExternalSyntheticLambda5(this, r2));
            r13.setRepeatCount(-1);
            r13.setRepeatMode(1);
            r13.setInterpolator(new LinearInterpolator());
            r13.setCurrentPlayTime(r3);
            r13.setDuration(300);
            r13.start();
            this.rotateAnimators.add(r6);
            this.translateXAnimators.add(r52);
            this.translateYAnimators.add(r13);
            r2 = r2 + 1;
            r1 = r16;
            goto L4
        }

        public void stopShake(boolean r10) {
            int r1 = 0;
        L4:
            if (r1 >= this.rotateAnimators.size()) goto L9;
            this.rotateAnimators.get(r1).cancel();
            if (r10 == false) goto L8;
            ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{this.imageRotations.get(r1).floatValue(), 0.0f});
            r2.addUpdateListener(new StickersAlert$StickersShaker$$ExternalSyntheticLambda0(this, r1));
            r2.setDuration(100);
            r2.start();
        L8:
            r1 = r1 + 1;
            goto L4
        L9:
            int r12 = 0;
        L11:
            if (r12 >= this.translateXAnimators.size()) goto L16;
            this.translateXAnimators.get(r12).cancel();
            if (r10 == false) goto L15;
            ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{this.imageTranslationsX.get(r12).floatValue(), 0.0f});
            r22.addUpdateListener(new StickersAlert$StickersShaker$$ExternalSyntheticLambda1(this, r12));
            r22.setDuration(100);
            r22.start();
        L15:
            r12 = r12 + 1;
            goto L11
        L16:
            int r13 = 0;
        L17:
            int r23 = this.translateYAnimators.size();
            List<ValueAnimator> r8 = this.translateYAnimators;
            if (r13 >= r23) goto L23;
            r8.get(r13).cancel();
            if (r10 == false) goto L22;
            ValueAnimator r24 = ValueAnimator.ofFloat(new float[]{this.imageTranslationsY.get(r13).floatValue(), 0.0f});
            r24.addUpdateListener(new StickersAlert$StickersShaker$$ExternalSyntheticLambda2(this, r13));
            r24.setDuration(100);
            r24.start();
        L22:
            r13 = r13 + 1;
            goto L17
        L23:
            r8.clear();
            this.translateXAnimators.clear();
            this.rotateAnimators.clear();
        }
    }

    /* renamed from: $r8$lambda$-b1H8lTmNxxkX5zurx_oRcP5Ozg, reason: not valid java name */
    public static /* synthetic */ void m13071$r8$lambda$b1H8lTmNxxkX5zurx_oRcP5Ozg(StickersAlert r0, View r1) {
        r0.lambda$updateFields$30(r1);
    }

    /* renamed from: $r8$lambda$-lqm1Hk0sMN3Lcgq9CSP-8eisT4, reason: not valid java name */
    public static /* synthetic */ void m13072$r8$lambda$lqm1Hk0sMN3Lcgq9CSP8eisT4(StickersAlert r0, String r1) {
        r0.lambda$showNameEnterAlert$46(r1);
    }

    public static /* synthetic */ void $r8$lambda$14bmoVaWCj7flVuJxlMWB0_Qrj0(StickersAlert r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$updateFields$31(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$1GPwq7QCvK7CQ6jYGXdOkj0kFzA(StickersAlert r0, String r1, SendMessagesHelper.ImportingSticker r2) {
        r0.lambda$didReceivedNotification$51(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$6pOusNRCLslaUf6Z3_3hkId72v0(StickersAlert r0, String r1, TLRPC.TL_error r2, TLObject r3, TextView r4) {
        r0.lambda$checkUrlAvailable$48(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$CTRZBjce4AiDNtFGtVtRa8ehzWU(BaseFragment r21, TLRPC.TL_messages_stickerSet r22, TLRPC.Document r23, ChatActivity r24, boolean r25) {
        File r2 = StoryEntry.makeCacheFile(r21.getCurrentAccount(), "webp");
        int r0 = SharedConfig.getDevicePerformanceClass();
        if (r0 != 0) goto L5;
        int r02 = 1280;
    L9:
        Size r3 = new Size(512.0f, 512.0f);
        float r03 = r02;
        r3.width = r03;
        double r5 = (r03 * 512.0f) / 512.0f;
        float r7 = (float) Math.floor(r5);
        r3.height = r7;
        if (r7 <= r03) goto L12;
        r3.height = r03;
        r3.width = (float) Math.floor(r5);
    L12:
        Bitmap r6 = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
        r6.compress(Bitmap.CompressFormat.WEBP, 100, new FileOutputStream(r2));     // Catch: Throwable -> L15
    L17:
        r6.recycle();
        ArrayList<Object> r8 = new ArrayList();
        MediaController.PhotoEntry r9 = new MediaController.PhotoEntry(0, 0, 0, r2.getAbsolutePath(), 0, false, 0, 0, 0);
        r8.add(r9);
        VideoEditedInfo.MediaEntity r04 = new VideoEditedInfo.MediaEntity();
        r04.type = 0;
        TLRPC.TL_messages_stickerSet r26 = r22;
        r04.parentObject = r26;
        r04.text = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r23, true).getAbsolutePath();
        r04.x = 0.5f - ((Math.min(512, 512) / 512.0f) / 2.0f);
        r04.y = 0.5f - ((Math.min(512, 512) / 512.0f) / 2.0f);
        r04.width = Math.min(512, 512) / 512.0f;
        r04.height = Math.min(512, 512) / 512.0f;
        int r32 = (int) Math.floor(r3.width * 0.5d);
        r04.viewWidth = r32;
        r04.viewHeight = r32;
        r04.scale = 2.0f;
        r04.document = r23;
        if (MessageObject.isAnimatedStickerDocument(r23, true) == false) goto L20;
    L21:
        boolean r33 = MessageObject.isAnimatedStickerDocument(r23, true);
        byte r4 = r04.subType;
        if (r33 == false) goto L24;
        byte r34 = 1;
    L25:
        r04.subType = (byte) (r34 | r4);
    L26:
        ArrayList<VideoEditedInfo.MediaEntity> r35 = new ArrayList();
        r9.mediaEntities = r35;
        r35.add(r04);
        r9.averageDuration = 3000;
        if (MessageObject.isAnimatedStickerDocument(r23, true) == false) goto L35;
        File r05 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r23, true);
        if (r05 != null) goto L49;
    L37:
        PhotoViewer.getInstance().setParentActivity(r21.getParentActivity(), r21.getResourceProvider());
        PhotoViewer.getInstance().openPhotoForSelect(r8, 0, 11, false, new AnonymousClass3(), r24);
        PhotoViewer r06 = PhotoViewer.getInstance();
        if (r25 == false) goto L40;
        TLRPC.Document r36 = r23;
    L41:
        r06.enableStickerMode(r23, r36, true, null);
        ContentPreviewViewer r07 = ContentPreviewViewer.getInstance();
        if (r25 == true) goto L45;
        r26 = null;
    L45:
        r07.setStickerSetForCustomSticker(r26);
        return;
    L40:
        r36 = null;
        goto L41
    L49:
        r9.averageDuration = (long) (RLottieNative.getDuration(r05.getAbsolutePath(), null) * 1000.0d);     // Catch: Exception -> L32
    L32:
        e = move-exception;
        FileLog.e(e);
        goto L37
    L35:
        if (MessageObject.isVideoStickerDocument(r23) == false) goto L37;
        r9.averageDuration = (long) (MessageObject.getDocumentDuration(r23) * 1000.0d);
        goto L37
    L24:
        r34 = 4;
        goto L25
    L20:
        if (MessageObject.isVideoStickerDocument(r23) == false) goto L26;
    L15:
        th = move-exception;
        FileLog.e(th);
        goto L17
    L5:
        if (r0 == 2) goto L7;
        r02 = 2560;
        goto L9
    L7:
        r02 = 3840;
        goto L9
    }

    public static /* synthetic */ void $r8$lambda$CUKqbt91wL4hn71aBhN863eaIq0(StickersAlert r0, DialogInterface r1) {
        r0.lambda$onSubItemClick$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$CrdDhvP5c2s1W7WzjEKiPVVGZ2o(TLObject r8, Utilities.Callback r9) {
        if ((r8 instanceof TLRPC.TL_messages_stickerSet) == false) goto L8;
        TLRPC.TL_messages_stickerSet r3 = (TLRPC.TL_messages_stickerSet) r8;
        MediaDataController.getInstance(UserConfig.selectedAccount).putStickerSet(r3);
        if (MediaDataController.getInstance(UserConfig.selectedAccount).isStickerPackInstalled(r3.set.id) == true) goto L7;
        MediaDataController.getInstance(UserConfig.selectedAccount).toggleStickerSet(null, r3, 2, null, false, false);
    L7:
        boolean r82 = true;
    L9:
        r9.run(Boolean.valueOf(r82));
        return;
    L8:
        r82 = false;
        goto L9
    }

    public static /* synthetic */ void $r8$lambda$FWmuWfWVq_FF6o8uOmVXWpoDyXM(StickersAlert r0) {
        r0.lambda$checkOptions$20();
    }

    /* renamed from: $r8$lambda$FXbyFAt-2OI1B-CHjqx5h2eYQa0, reason: not valid java name */
    public static /* synthetic */ void m13073$r8$lambda$FXbyFAt2OI1BCHjqx5h2eYQa0(StickersAlert r0, View r1) {
        r0.lambda$updateFields$33(r1);
    }

    public static /* synthetic */ void $r8$lambda$FgFWIMACRD3rck17ZjJtl8br1Sw(StickersAlert r0, View r1) {
        r0.lambda$init$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$GIIjyE4HTTMpqiU0p67H_KBq4xI(StickersAlert r0, View r1) {
        r0.lambda$init$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$Gmsf0yK7sIqvgUC2FLL2FbRUAWQ(StickersAlert r0, TLObject r1, EditTextBoldCursor r2, TextView r3, TextView r4, int[] r5) {
        r0.lambda$showNameEnterAlert$44(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$HdWRfe6zF27DpqIBp2ZVIcyt8PY(StickersAlert r0, ArrayList r1, ArrayList r2) {
        r0.lambda$new$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$HhebAqkK74FKCeNdIIwvDyiAgrk(StickersAlert r0) {
        r0.lambda$enableEditMode$53();
    }

    /* renamed from: $r8$lambda$IVQj6hcpclBUFLceMGo-Ukay13s, reason: not valid java name */
    public static /* synthetic */ void m13074$r8$lambda$IVQj6hcpclBUFLceMGoUkay13s(StickersAlert r0, View r1, int r2) {
        r0.lambda$init$11(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$IYIj6T1tv3qYwZaZujnsq6fDpyM(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$IkWaDm7KqIAk6cqlkhsST33ZYjU(EditTextBoldCursor r0, DialogInterface r1) {
        AndroidUtilities.runOnUIThread(new StickersAlert$$ExternalSyntheticLambda41(r0));
    }

    /* renamed from: $r8$lambda$NCyf-pcwg3EKNh8DhMyCVClHuxg, reason: not valid java name */
    public static /* synthetic */ void m13075$r8$lambda$NCyfpcwg3EKNh8DhMyCVClHuxg(StickersAlert r0, EditTextBoldCursor r1, TextView r2, TextView r3, int[] r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$showNameEnterAlert$45(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$O9Zju3ZVsbkcYVyqKji7QS9rJ1s(StickersAlert r0, TLRPC.TL_messages_getAttachedStickers r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$OSkWdZmNp7F9jNO1iDb0kTEXqb4(Utilities.Callback r0, TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StickersAlert$$ExternalSyntheticLambda56(r1, r0));
    }

    public static /* synthetic */ void $r8$lambda$Q3640OoLCbyGBwGTByqa3VXrcqU(StickersAlert r0, View r1) {
        r0.lambda$checkOptions$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$QBADaHIlM00XT_ujGtK8hEHBbXY(StickersAlert r0, int[] r1, EditTextBoldCursor r2, TextView r3, TextView r4, AlertDialog.Builder r5, View r6) {
        r0.lambda$showNameEnterAlert$47(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$QfZ47zoqnzTBBGg-D_-0G2CkSno, reason: not valid java name */
    public static /* synthetic */ void m13076$r8$lambda$QfZ47zoqnzTBBGgD_0G2CkSno(StickersAlert r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_messages_getAttachedStickers r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$QtWSB1Hfo-SSUHb8XnHNR7L25kU, reason: not valid java name */
    public static /* synthetic */ void m13077$r8$lambda$QtWSB1HfoSSUHb8XnHNR7L25kU(StickersAlert r0, MediaDataController r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadStickerSet$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$RoUTYIzJH1vQOXMHfGpBwRj07Ew(StickersAlert r0, View r1) {
        r0.lambda$updateFields$34(r1);
    }

    /* renamed from: $r8$lambda$TCE1KZ9wFm_tAtSAKGp4tPHTg-Y, reason: not valid java name */
    public static /* synthetic */ void m13078$r8$lambda$TCE1KZ9wFm_tAtSAKGp4tPHTgY(StickersAlert r0, AlertDialog r1, long r2, TLRPC.User r4) {
        r0.lambda$onSubItemClick$22(r1, r2, r4);
    }

    /* renamed from: $r8$lambda$US5-Ol8rXTon1OEmlW3KwQFZJ_s, reason: not valid java name */
    public static /* synthetic */ void m13079$r8$lambda$US5Ol8rXTon1OEmlW3KwQFZJ_s(StickersAlert r0, View r1) {
        r0.lambda$checkOptions$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$Wx5nP8cvc0v3Fea8l6b0MqdOuCA(StickersAlert r0, TLRPC.TL_error r1, TLObject r2, MediaDataController r3) {
        r0.lambda$loadStickerSet$7(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Zfp2pZNbf6_YGC_Ylv81PgBCKn0(StickersAlert r0, ArrayList r1, Boolean r2) {
        r0.lambda$new$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZuRu2h_snIQGNFQU1_zhODPwVvE(StickersAlert r0) {
        r0.lambda$onSubItemClick$28();
    }

    /* renamed from: $r8$lambda$_t6-xmAh70JBZx33mMIfZnICAd0, reason: not valid java name */
    public static /* synthetic */ void m13080$r8$lambda$_t6xmAh70JBZx33mMIfZnICAd0(AlertDialog r0, int r1) {
    }

    /* renamed from: $r8$lambda$a6X-knEjfHgAY9BKWLsVZMQnjDM, reason: not valid java name */
    public static /* synthetic */ void m13081$r8$lambda$a6XknEjfHgAY9BKWLsVZMQnjDM(StickersAlert r0) {
        r0.lambda$disableEditMode$55();
    }

    public static /* synthetic */ void $r8$lambda$dDzl3CrfdzIDOkKZy6bxztTCCeQ(StickersAlert r0, View r1) {
        r0.lambda$updateFields$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$eZ_EaPjp7tY1DXIxv3JjuwcBLz8(StickersAlert r0, View r1) {
        r0.lambda$updateFields$38(r1);
    }

    public static /* synthetic */ void $r8$lambda$hXwG5Me0eVxrtsH3nkHV35L1UnM(File r19, ArrayList r20, BaseFragment r21, ChatActivity r22, TLRPC.Document r23, boolean r24, TLRPC.TL_messages_stickerSet r25) {
        r20.add(new MediaController.PhotoEntry(0, 0, 0, r19.getAbsolutePath(), 0, false, 0, 0, 0));
        PhotoViewer.getInstance().setParentActivity(r21.getParentActivity(), r21.getResourceProvider());
        PhotoViewer.getInstance().openPhotoForSelect(r20, 0, 11, false, new AnonymousClass2(), r22);
        PhotoViewer r0 = PhotoViewer.getInstance();
        TLRPC.TL_messages_stickerSet r1 = null;
        if (r24 == false) goto L5;
        TLRPC.Document r2 = r23;
    L6:
        r0.enableStickerMode(r23, r2, false, null);
        ContentPreviewViewer r02 = ContentPreviewViewer.getInstance();
        if (r24 == false) goto L9;
        r1 = r25;
    L9:
        r02.setStickerSetForCustomSticker(r1);
        return;
    L5:
        r2 = null;
        goto L6
    }

    /* renamed from: $r8$lambda$hmc-mQ9_9V748rRJmyAt2n-wSr4, reason: not valid java name */
    public static /* synthetic */ void m13082$r8$lambda$hmcmQ9_9V748rRJmyAt2nwSr4(StickersAlert r0, Runnable r1) {
        r0.lambda$onSubItemClick$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$hsnTIv0Y3NPLVD4PSF_LqEGLvS8(StickersAlert r0, View r1) {
        r0.lambda$updateFields$36(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$j4azk6_plIPxHxPtfxc3EvZH4n4(AlertDialog.Builder r0, TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        r0.create().getButton(-1).callOnClick();
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ void $r8$lambda$kERGaA3y9yycNVQWgp2q1pHaBl8(StickersAlert r0) {
        r0.playRefreshTransition();
    }

    /* renamed from: $r8$lambda$kY02WWT-Y5S7hUNoECtptgZnvjQ, reason: not valid java name */
    public static /* synthetic */ void m13083$r8$lambda$kY02WWTY5S7hUNoECtptgZnvjQ(StickersAlert r0, String r1, TextView r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$checkUrlAvailable$49(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$mFNteLBmfFnBQRPdwecg56Hwz14(StickersAlert r0, int r1) {
        r0.onSubItemClick(r1);
    }

    /* renamed from: $r8$lambda$mgbrbz8DM-yuAIttfeDYir6nmjc, reason: not valid java name */
    public static /* synthetic */ void m13084$r8$lambda$mgbrbz8DMyuAIttfeDYir6nmjc(EditTextBoldCursor r0, AlertDialog r1, int r2) {
        AndroidUtilities.hideKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$nctyHjOcmFMDqtY43PV7n69en68(StickersAlert r0, CharSequence r1, Utilities.Callback r2) {
        r0.lambda$onSubItemClick$27(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$nl1WRXGgG6_CGRnZfr2CyfrnV9E(StickersAlert r0, String r1, TextView r2) {
        r0.lambda$checkUrlAvailable$50(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$pJi3sfhgz2XNAmUpPEdTqOQvLgI(StickersAlert r0, Object r1, TLRPC.TL_messages_getAttachedStickers r2, RequestDelegate r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$new$4(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$pho_xLZOtqWG7Zmv120brfoYrbg(View r1) {
        if ((r1 instanceof StickerEmojiCell) == false) goto L6;
        ((StickerEmojiCell) r1).disableEditMode(true);
        return;
    }

    public static /* synthetic */ void $r8$lambda$qxtvW1V_KKvUZ3yEDzFVJn7hspM(View r1) {
        if ((r1 instanceof StickerEmojiCell) == false) goto L6;
        ((StickerEmojiCell) r1).enableEditMode(true);
        return;
    }

    public static /* synthetic */ void $r8$lambda$rAAQ25Bx3oWtDLqmQzvMJLS2rgQ(StickersAlert r0, View r1) {
        r0.lambda$updateFields$35(r1);
    }

    public static /* synthetic */ void $r8$lambda$rl9JVOZolzlrHUz61KdOmc5ktKg(StickersAlert r0) {
        r0.lambda$checkOptions$18();
    }

    public static /* synthetic */ void $r8$lambda$t_rwSM_s5rS9L3qpUysl5ec8G44(StickersAlert r0, View r1) {
        r0.lambda$checkOptions$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$uDpUd9fQ_nZKNu3GAaYGURAO1is(StickersAlert r0, View r1) {
        r0.lambda$updateFields$37(r1);
    }

    /* renamed from: $r8$lambda$uPmn7PrWt-S8K4R3vTGm0Ky0e04, reason: not valid java name */
    public static /* synthetic */ void m13085$r8$lambda$uPmn7PrWtS8K4R3vTGm0Ky0e04(StickersAlert r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$updateFields$32(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vHcw9i0Wanm2HpNnjL1WuY3xX_0(StickersAlert r0, View r1) {
        r0.lambda$checkOptions$19(r1);
    }

    /* renamed from: $r8$lambda$vNSD2WwmfzY-K0utMcebeNiG884, reason: not valid java name */
    public static /* synthetic */ void m13086$r8$lambda$vNSD2WwmfzYK0utMcebeNiG884(StickersAlert r0, View r1) {
        r0.lambda$init$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$vQlv1bLm9XXw5TE7xmyjVNc9ONQ(EditTextBoldCursor r0) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    /* renamed from: $r8$lambda$wZn-2uYdGu-QRWY24Wk7y0z7xSs, reason: not valid java name */
    public static /* synthetic */ void m13087$r8$lambda$wZn2uYdGuQRWY24Wk7y0z7xSs(StickersAlert r0, DialogInterface r1) {
        r0.lambda$init$10(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$xVYj8OxxlXRpE4qkxaPlPkM9kWU(StickersAlert r0, View r1, MotionEvent r2) {
        return r0.lambda$init$9(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridAdapter m13088$$Nest$fgetadapter(StickersAlert r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetclearsInputField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13089$$Nest$fgetclearsInputField(StickersAlert r0) {
        return r0.clearsInputField;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickersAlertDelegate m13090$$Nest$fgetdelegate(StickersAlert r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdescriptionTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m13091$$Nest$fgetdescriptionTextView(StickersAlert r0) {
        return r0.descriptionTextView;
    }

    /* renamed from: -$$Nest$fgetdraggedDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Document m13092$$Nest$fgetdraggedDocument(StickersAlert r0) {
        return r0.draggedDocument;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m13093$$Nest$fgetemptyView(StickersAlert r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetgridView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m13094$$Nest$fgetgridView(StickersAlert r0) {
        return r0.gridView;
    }

    /* renamed from: -$$Nest$fgetignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13095$$Nest$fgetignoreLayout(StickersAlert r0) {
        return r0.ignoreLayout;
    }

    /* renamed from: -$$Nest$fgetimportingStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m13096$$Nest$fgetimportingStickers(StickersAlert r0) {
        return r0.importingStickers;
    }

    /* renamed from: -$$Nest$fgetimportingStickersPaths, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m13097$$Nest$fgetimportingStickersPaths(StickersAlert r0) {
        return r0.importingStickersPaths;
    }

    /* renamed from: -$$Nest$fgetisEditModeEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13098$$Nest$fgetisEditModeEnabled(StickersAlert r0) {
        return r0.isEditModeEnabled;
    }

    /* renamed from: -$$Nest$fgetitemHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13099$$Nest$fgetitemHeight(StickersAlert r0) {
        return r0.itemHeight;
    }

    /* renamed from: -$$Nest$fgetitemSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13100$$Nest$fgetitemSize(StickersAlert r0) {
        return r0.itemSize;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridLayoutManager m13101$$Nest$fgetlayoutManager(StickersAlert r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m13102$$Nest$fgetparentFragment(StickersAlert r0) {
        return r0.parentFragment;
    }

    /* renamed from: -$$Nest$fgetpickerBottomFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m13103$$Nest$fgetpickerBottomFrameLayout(StickersAlert r0) {
        return r0.pickerBottomFrameLayout;
    }

    /* renamed from: -$$Nest$fgetpreviewDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContentPreviewViewer.ContentPreviewViewerDelegate m13104$$Nest$fgetpreviewDelegate(StickersAlert r0) {
        return r0.previewDelegate;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13105$$Nest$fgetscrollOffsetY(StickersAlert r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetshadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View[] m13106$$Nest$fgetshadow(StickersAlert r0) {
        return r0.shadow;
    }

    /* renamed from: -$$Nest$fgetshadowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet[] m13107$$Nest$fgetshadowAnimation(StickersAlert r0) {
        return r0.shadowAnimation;
    }

    /* renamed from: -$$Nest$fgetshowEmoji, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13108$$Nest$fgetshowEmoji(StickersAlert r0) {
        return r0.showEmoji;
    }

    /* renamed from: -$$Nest$fgetstickerImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m13109$$Nest$fgetstickerImageView(StickersAlert r0) {
        return r0.stickerImageView;
    }

    /* renamed from: -$$Nest$fgetstickerPreviewLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m13110$$Nest$fgetstickerPreviewLayout(StickersAlert r0) {
        return r0.stickerPreviewLayout;
    }

    /* renamed from: -$$Nest$fgetstickerSetCovereds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m13111$$Nest$fgetstickerSetCovereds(StickersAlert r0) {
        return r0.stickerSetCovereds;
    }

    /* renamed from: -$$Nest$fgetstickersShaker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickersShaker m13112$$Nest$fgetstickersShaker(StickersAlert r0) {
        return r0.stickersShaker;
    }

    /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinkSpanDrawable.LinksTextView m13113$$Nest$fgettitleTextView(StickersAlert r0) {
        return r0.titleTextView;
    }

    /* renamed from: -$$Nest$fputdraggedDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13114$$Nest$fputdraggedDocument(StickersAlert r0, TLRPC.Document r1) {
        r0.draggedDocument = r1;
    }

    /* renamed from: -$$Nest$fputignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13115$$Nest$fputignoreLayout(StickersAlert r0, boolean r1) {
        r0.ignoreLayout = r1;
    }

    /* renamed from: -$$Nest$fputitemHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13116$$Nest$fputitemHeight(StickersAlert r0, int r1) {
        r0.itemHeight = r1;
    }

    /* renamed from: -$$Nest$fputitemSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13117$$Nest$fputitemSize(StickersAlert r0, int r1) {
        r0.itemSize = r1;
    }

    /* renamed from: -$$Nest$mcheckOptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13118$$Nest$mcheckOptions(StickersAlert r0) {
        r0.checkOptions();
    }

    /* renamed from: -$$Nest$mcheckUrlAvailable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13119$$Nest$mcheckUrlAvailable(StickersAlert r0, TextView r1, String r2, boolean r3) {
        r0.checkUrlAvailable(r1, r2, r3);
    }

    /* renamed from: -$$Nest$misEmoji, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13120$$Nest$misEmoji(StickersAlert r0) {
        return r0.isEmoji();
    }

    /* renamed from: -$$Nest$mremoveSticker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13121$$Nest$mremoveSticker(StickersAlert r0, SendMessagesHelper.ImportingSticker r1) {
        r0.removeSticker(r1);
    }

    /* renamed from: -$$Nest$msetScrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13122$$Nest$msetScrollOffsetY(StickersAlert r0, int r1) {
        r0.setScrollOffsetY(r1);
    }

    /* renamed from: -$$Nest$mupdateFields, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13123$$Nest$mupdateFields(StickersAlert r0) {
        r0.updateFields();
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13124$$Nest$mupdateLayout(StickersAlert r0) {
        r0.updateLayout();
    }

    public StickersAlert(Context r6, Object r7, TLObject r8, Theme.ResourcesProvider r9) {
        super(r6, false, r9);
        this.shadowAnimation = new AnimatorSet[2];
        this.shadow = new View[2];
        this.showTooltipWhenToggle = true;
        this.stickersShaker = new StickersShaker(null);
        this.previewDelegate = new AnonymousClass1(this);
        this.cancelSearchCreatorRunnable = null;
        this.resourcesProvider = r9;
        fixNavigationBar();
        this.parentActivity = (Activity) r6;
        TLRPC.TL_messages_getAttachedStickers r92 = new TLRPC.TL_messages_getAttachedStickers();
        if ((r8 instanceof TLRPC.Photo) == false) goto L9;
        TLRPC.Photo r82 = (TLRPC.Photo) r8;
        TLRPC.TL_inputStickeredMediaPhoto r1 = new TLRPC.TL_inputStickeredMediaPhoto();
        TLRPC.TL_inputPhoto r2 = new TLRPC.TL_inputPhoto();
        r1.id = r2;
        r2.id = r82.id;
        r2.access_hash = r82.access_hash;
        byte[] r83 = r82.file_reference;
        r2.file_reference = r83;
        if (r83 != null) goto L7;
        r2.file_reference = new byte[0];
    L7:
        r92.media = r1;
    L14:
        StickersAlert$$ExternalSyntheticLambda27 r84 = new StickersAlert$$ExternalSyntheticLambda27(this, r92);
        this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r92, new StickersAlert$$ExternalSyntheticLambda28(this, r7, r92, r84));
        init(r6);
        return;
    L9:
        if ((r8 instanceof TLRPC.Document) == false) goto L14;
        TLRPC.Document r85 = (TLRPC.Document) r8;
        TLRPC.TL_inputStickeredMediaDocument r12 = new TLRPC.TL_inputStickeredMediaDocument();
        TLRPC.TL_inputDocument r22 = new TLRPC.TL_inputDocument();
        r12.id = r22;
        r22.id = r85.id;
        r22.access_hash = r85.access_hash;
        byte[] r86 = r85.file_reference;
        r22.file_reference = r86;
        if (r86 != null) goto L13;
        r22.file_reference = new byte[0];
    L13:
        r92.media = r12;
        goto L14
    }

    public StickersAlert(Context r2, String r3, ArrayList<Parcelable> r4, ArrayList<String> r5, Theme.ResourcesProvider r6) {
        super(r2, false, r6);
        this.shadowAnimation = new AnimatorSet[2];
        this.shadow = new View[2];
        this.showTooltipWhenToggle = true;
        this.stickersShaker = new StickersShaker(null);
        this.previewDelegate = new AnonymousClass1(this);
        this.cancelSearchCreatorRunnable = null;
        fixNavigationBar();
        this.parentActivity = (Activity) r2;
        this.importingStickers = r4;
        this.importingSoftware = r3;
        Utilities.globalQueue.postRunnable(new StickersAlert$$ExternalSyntheticLambda9(this, r4, r5));
        init(r2);
    }

    public StickersAlert(Context r2, BaseFragment r3, TLRPC.InputStickerSet r4, TLRPC.TL_messages_stickerSet r5, StickersAlertDelegate r6, Theme.ResourcesProvider r7, boolean r8) {
        super(r2, false, r7);
        this.shadowAnimation = new AnimatorSet[2];
        this.shadow = new View[2];
        this.showTooltipWhenToggle = true;
        this.stickersShaker = new StickersShaker(null);
        this.previewDelegate = new AnonymousClass1(this);
        this.cancelSearchCreatorRunnable = null;
        fixNavigationBar();
        this.delegate = r6;
        this.inputStickerSet = r4;
        this.stickerSet = r5;
        this.parentFragment = r3;
        loadStickerSet(r8);
        init(r2);
    }

    public StickersAlert(Context r9, BaseFragment r10, TLRPC.InputStickerSet r11, TLRPC.TL_messages_stickerSet r12, StickersAlertDelegate r13, boolean r14) {
        this(r9, r10, r11, r12, r13, null, r14);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(StickersAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$100(StickersAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1000(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1100(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1200(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1300(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1400(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1500(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Drawable access$1600(StickersAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ Drawable access$1700(StickersAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ int access$1800(StickersAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1900(StickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$200(StickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$2000(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2100(StickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$2200(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2300(StickersAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2400(StickersAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2500(StickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$2600(StickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$2700(StickersAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2800(StickersAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2900(StickersAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$300(StickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$3000(StickersAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$3100(StickersAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3200(StickersAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$400(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$500(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$600(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$700(StickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$800(StickersAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$900(StickersAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    private void checkOptions() {
        MediaDataController r0 = MediaDataController.getInstance(this.currentAccount);
        this.optionsButton.removeAllSubItems();
        this.deleteItem = null;
        this.optionsButton.addSubItem(1, R.drawable.msg_share, LocaleController.getString(R.string.StickersShare));
        TLRPC.TL_messages_stickerSet r2 = this.stickerSet;
        if (r2 == null) goto L9;
        TLRPC.StickerSet r22 = r2.set;
        if (r22 == null) goto L9;
        if (r22.creator == false) goto L9;
        boolean r23 = true;
    L10:
        ActionBarMenuItem r4 = this.optionsButton;
        if (r23 == false) goto L13;
        r4.addSubItem(2, R.drawable.msg_link, LocaleController.getString(R.string.CopyLink));
        this.optionsButton.addSubItem(7, R.drawable.msg_retry, LocaleController.getString(R.string.Refresh));
    L14:
        TLRPC.TL_messages_stickerSet r42 = this.stickerSet;
        if (r42 != null) goto L17;
    L20:
        boolean r02 = true;
    L21:
        if (r23 == false) goto L30;
        this.optionsButton.addSubItem(4, R.drawable.tabs_reorder, LocaleController.getString(R.string.StickersReorder));
        this.optionsButton.addSubItem(5, R.drawable.msg_edit, LocaleController.getString(R.string.EditName));
        if (r02 == false) goto L25;
        this.deleteItem = this.optionsButton.addSubItem(6, R.drawable.msg_delete, LocaleController.getString(R.string.Delete));
    L26:
        this.optionsButton.addColoredGap();
        MessageContainsEmojiButton r6 = new MessageContainsEmojiButton(this.currentAccount, getContext(), this.resourcesProvider, new ArrayList(), 6);
        r6.setOnClickListener(new StickersAlert$$ExternalSyntheticLambda38(this));
        r6.setTag(R.id.fit_width_tag, 1);
        this.optionsButton.addSubItem(r6, -1, -2);
        int r03 = getThemedColor(Theme.key_text_RedBold);
        this.deleteItem.setColors(r03, r03);
        this.deleteItem.setSelectorColor(Theme.multAlpha(r03, 0.1f));
        if (this.deleteItem.getRightIcon() == null) goto L30;
        this.deleteItem.getRightIcon().setColorFilter(r03);
        goto L30
    L25:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r04 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(getContext(), 0, this.resourcesProvider);
        r04.setFitItems(true);
        ActionBarMenuItem.addItem(r04, R.drawable.msg_arrow_back, LocaleController.getString(R.string.Back), false, this.resourcesProvider).setOnClickListener(new StickersAlert$$ExternalSyntheticLambda35(this));
        ActionBarMenuSubItem r43 = ActionBarMenuItem.addItem(r04, 0, LocaleController.getString(R.string.StickersDeleteForEveryone), false, this.resourcesProvider);
        int r62 = getThemedColor(Theme.key_text_RedBold);
        r43.setColors(r62, r62);
        r43.setSelectorColor(Theme.multAlpha(r62, 0.1f));
        ActionBarMenuItem.addItem(r04, 0, LocaleController.getString(R.string.StickersRemoveForMe), false, this.resourcesProvider).setOnClickListener(new StickersAlert$$ExternalSyntheticLambda36(this));
        r43.setOnClickListener(new StickersAlert$$ExternalSyntheticLambda37(this));
        this.deleteItem = this.optionsButton.addSwipeBackItem(R.drawable.msg_delete, null, LocaleController.getString(R.string.Delete), r04);
    L30:
        if (this.optionsButton.getPopupLayout() == null) goto L33;
        this.optionsButton.getPopupLayout().requestLayout();
        return;
    L33:
        return;
    L17:
        if (r0.isStickerPackInstalled(r42.set.id) == false) goto L20;
        r02 = false;
        goto L21
    L13:
        r4.addSubItem(2, R.drawable.msg_link, LocaleController.getString(R.string.CopyLink));
        this.optionsButton.addSubItem(3, R.drawable.msg_openprofile, LocaleController.getString(R.string.ChannelCreator));
        this.optionsButton.addSubItem(7, R.drawable.msg_retry, LocaleController.getString(R.string.Refresh));
    L9:
        r23 = false;
        goto L10
    }

    private void checkPremiumStickers() {
        if (this.stickerSet == null) goto L8;
        TLRPC.TL_messages_stickerSet r0 = MessagesController.getInstance(this.currentAccount).filterPremiumStickers(this.stickerSet);
        this.stickerSet = r0;
        if (r0 != null) goto L9;
        dismiss();
        return;
    L9:
        return;
    }

    private void checkUrlAvailable(TextView r4, String r5, boolean r6) {
        if (r6 == false) goto L6;
        r4.setText(LocaleController.getString(R.string.ImportStickersLinkAvailable));
        r4.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGreenText));
        this.lastNameAvailable = true;
        this.lastCheckName = r5;
        return;
    L6:
        Runnable r62 = this.checkRunnable;
        if (r62 == null) goto L12;
        AndroidUtilities.cancelRunOnUIThread(r62);
        this.checkRunnable = null;
        this.lastCheckName = null;
        if (this.checkReqId == 0) goto L12;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.checkReqId, true);
    L12:
        if (TextUtils.isEmpty(r5) == false) goto L15;
        r4.setText(LocaleController.getString(R.string.ImportStickersEnterUrlInfo));
        r4.setTextColor(getThemedColor(Theme.key_dialogTextGray2));
        return;
    L15:
        int r63 = 0;
        this.lastNameAvailable = false;
        if (r5 != null) goto L18;
    L43:
        if (r5 != null) goto L45;
    L53:
        r4.setText(LocaleController.getString(R.string.ImportStickersLinkInvalidShort));
        r4.setTextColor(getThemedColor(Theme.key_text_RedRegular));
        return;
    L45:
        if (r5.length() < 5) goto L53;
        if (r5.length() <= 32) goto L51;
        r4.setText(LocaleController.getString(R.string.ImportStickersLinkInvalidLong));
        r4.setTextColor(getThemedColor(Theme.key_text_RedRegular));
        return;
    L51:
        r4.setText(LocaleController.getString(R.string.ImportStickersLinkChecking));
        r4.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText8));
        this.lastCheckName = r5;
        StickersAlert$$ExternalSyntheticLambda52 r64 = new StickersAlert$$ExternalSyntheticLambda52(this, r5, r4);
        this.checkRunnable = r64;
        AndroidUtilities.runOnUIThread(r64, 300);
        return;
    L18:
        if (r5.startsWith("_") == false) goto L20;
    L41:
        r4.setText(LocaleController.getString(R.string.ImportStickersLinkInvalid));
        r4.setTextColor(getThemedColor(Theme.key_text_RedRegular));
        return;
    L20:
        if (r5.endsWith("_") == true) goto L41;
        int r0 = r5.length();
    L23:
        if (r63 >= r0) goto L43;
        char r1 = r5.charAt(r63);
        if (r1 < '0') goto L29;
        if (r1 > '9') goto L29;
    L40:
        r63 = r63 + 1;
    L29:
        if (r1 < 'a') goto L33;
        if (r1 <= 'z') goto L40;
    L33:
        if (r1 < 'A') goto L37;
        if (r1 <= 'Z') goto L40;
    L37:
        if (r1 == '_') goto L40;
        r4.setText(LocaleController.getString(R.string.ImportStickersEnterUrlInfo));
        r4.setTextColor(getThemedColor(Theme.key_text_RedRegular));
    }

    public static void editSticker(BaseFragment r11, TLRPC.TL_messages_stickerSet r12, TLRPC.Document r13) {
        if (r11 != null) goto L5;
        return;
    L5:
        if ((r11 instanceof ChatActivity) == false) goto L8;
        ChatActivity r0 = (ChatActivity) r11;
    L7:
        ChatActivity r5 = r0;
        if (r12 == null) goto L16;
        TLRPC.StickerSet r1 = r12.set;
        if (r1 == null) goto L16;
        if (r1.creator == false) goto L16;
        boolean r6 = true;
    L18:
        if (MessageObject.isStaticStickerDocument(r13) == false) goto L26;
        ArrayList r3 = new ArrayList();
        File r2 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r13, true);
        if (r2 != null) goto L22;
        return;
    L22:
        if (r2.exists() == false) goto L30;
        AndroidUtilities.runOnUIThread(new StickersAlert$$ExternalSyntheticLambda44(r2, r3, r11, r5, r13, r6, r12), 300);
        return;
    L30:
        return;
    L26:
        AndroidUtilities.runOnUIThread(new StickersAlert$$ExternalSyntheticLambda45(r11, r12, r13, r5, r6), 300);
        return;
    L16:
        r6 = false;
        goto L18
    L8:
        r0 = null;
        goto L7
    }

    private void hidePreview() {
        AnimatorSet r0 = new AnimatorSet();
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.stickerPreviewLayout, View.ALPHA, new float[]{0.0f})});
        r0.setDuration(200);
        r0.addListener(new AnonymousClass17(this));
        r0.start();
    }

    private void init(Context r28) {
        AnonymousClass5 r1 = new AnonymousClass5(this, r28);
        this.containerView = r1;
        r1.setWillNotDraw(false);
        ViewGroup r12 = this.containerView;
        int r3 = this.backgroundPaddingLeft;
        r12.setPadding(r3, 0, r3, 0);
        FrameLayout.LayoutParams r13 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 51);
        r13.topMargin = AndroidUtilities.dp(48.0f);
        this.shadow[0] = new View(r28);
        View r32 = this.shadow[0];
        int r10 = Theme.key_dialogShadowLine;
        r32.setBackgroundColor(getThemedColor(r10));
        this.shadow[0].setAlpha(0.0f);
        this.shadow[0].setVisibility(4);
        this.shadow[0].setTag(1);
        this.containerView.addView(this.shadow[0], r13);
        AnonymousClass6 r14 = new AnonymousClass6(this, r28);
        this.gridView = r14;
        r14.setTag(14);
        RecyclerListView r15 = this.gridView;
        AnonymousClass7 r33 = new AnonymousClass7(this, getContext(), 5);
        this.layoutManager = r33;
        r15.setLayoutManager(r33);
        this.layoutManager.setSpanSizeLookup(new AnonymousClass8(this));
        this.dragAndDropHelper = new ItemTouchHelper(new AnonymousClass9(this, 15, 0));
        RecyclerListView r16 = this.gridView;
        GridAdapter r34 = new GridAdapter(this, r28);
        this.adapter = r34;
        r16.setAdapter(r34);
        this.gridView.setVerticalScrollBarEnabled(false);
        this.gridView.addItemDecoration(new AnonymousClass10(this));
        this.gridView.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        this.gridView.setClipToPadding(false);
        this.gridView.setEnabled(true);
        this.gridView.setGlowColor(getThemedColor(Theme.key_dialogScrollGlow));
        this.gridView.setOnTouchListener(new StickersAlert$$ExternalSyntheticLambda20(this));
        this.gridView.setOnScrollListener(new AnonymousClass11(this));
        StickersAlert$$ExternalSyntheticLambda21 r17 = new StickersAlert$$ExternalSyntheticLambda21(this);
        this.stickersOnItemClickListener = r17;
        this.gridView.setOnItemClickListener(r17);
        this.containerView.addView(this.gridView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 48.0f, 0.0f, 48.0f));
        AnonymousClass12 r18 = new AnonymousClass12(this, r28);
        this.emptyView = r18;
        this.containerView.addView(r18, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 48.0f));
        this.gridView.setEmptyView(this.emptyView);
        this.emptyView.setOnTouchListener(new StickersAlert$$ExternalSyntheticLambda22());
        LinkSpanDrawable.LinksTextView r19 = new LinkSpanDrawable.LinksTextView(r28);
        this.titleTextView = r19;
        r19.setLines(1);
        this.titleTextView.setSingleLine(true);
        this.titleTextView.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        this.titleTextView.setTextSize(1, 20.0f);
        this.titleTextView.setLinkTextColor(getThemedColor(Theme.key_dialogTextLink));
        this.titleTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.titleTextView.setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(6.0f));
        this.titleTextView.setGravity(16);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        this.containerView.addView(this.titleTextView, LayoutHelper.createFrame(-1, 50.0f, 51, 0.0f, 0.0f, 40.0f, 0.0f));
        ActionBarMenuItem r110 = new ActionBarMenuItem(r28, null, 0, getThemedColor(Theme.key_sheet_other), this.resourcesProvider);
        this.optionsButton = r110;
        r110.setLongClickEnabled(true);
        this.optionsButton.setShowSubmenuByMove(true);
        this.optionsButton.setSubMenuOpenSide(2);
        this.optionsButton.setIcon(R.drawable.ic_ab_other);
        this.optionsButton.setBackgroundDrawable(Theme.createSelectorDrawable(getThemedColor(Theme.key_player_actionBarSelector), 1));
        this.containerView.addView(this.optionsButton, LayoutHelper.createFrame(40, 40.0f, 53, 0.0f, 5.0f, 5.0f, 0.0f));
        this.optionsButton.setOnClickListener(new StickersAlert$$ExternalSyntheticLambda23(this));
        this.optionsButton.setSubMenuDelegate(new AnonymousClass13(this));
        this.optionsButton.setDelegate(new StickersAlert$$ExternalSyntheticLambda24(this));
        this.optionsButton.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        ActionBarMenuItem r111 = this.optionsButton;
        if (this.inputStickerSet == null) goto L5;
        int r35 = 0;
    L6:
        r111.setVisibility(r35);
        checkOptions();
        this.emptyView.addView(new RadialProgressView(r28), LayoutHelper.createFrame(-2, -2, 17));
        FrameLayout.LayoutParams r112 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 83);
        r112.bottomMargin = AndroidUtilities.dp(48.0f);
        this.shadow[1] = new View(r28);
        this.shadow[1].setBackgroundColor(getThemedColor(r10));
        this.containerView.addView(this.shadow[1], r112);
        AnimatedTextView r113 = new AnimatedTextView(r28);
        this.pickerBottomLayout = r113;
        int r36 = Theme.key_dialogBackground;
        int r152 = getThemedColor(r36);
        int r132 = Theme.key_listSelector;
        r113.setBackground(Theme.createSelectorWithBackgroundDrawable(r152, getThemedColor(r132)));
        AnimatedTextView r114 = this.pickerBottomLayout;
        int r122 = Theme.key_dialogTextBlue2;
        this.buttonTextColorKey = r122;
        r114.setTextColor(getThemedColor(r122));
        this.pickerBottomLayout.setTextSize(AndroidUtilities.dp(14.0f));
        this.pickerBottomLayout.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        this.pickerBottomLayout.setTypeface(AndroidUtilities.bold());
        this.pickerBottomLayout.setGravity(17);
        FrameLayout r115 = new FrameLayout(r28);
        this.pickerBottomFrameLayout = r115;
        r115.addView(this.pickerBottomLayout, LayoutHelper.createFrame(-1, 48.0f));
        this.containerView.addView(this.pickerBottomFrameLayout, LayoutHelper.createFrame(-1, -2, 83));
        PremiumButtonView r116 = new PremiumButtonView(r28, AndroidUtilities.dp(24.0f), false, this.resourcesProvider);
        this.premiumButtonView = r116;
        r116.setIcon(R.raw.unlock_icon);
        this.premiumButtonView.setVisibility(4);
        this.containerView.addView(this.premiumButtonView, LayoutHelper.createFrame(-1, 48.0f, 87, 8.0f, 0.0f, 8.0f, 8.0f));
        FrameLayout r117 = new FrameLayout(r28);
        this.stickerPreviewLayout = r117;
        r117.setVisibility(8);
        this.stickerPreviewLayout.setSoundEffectsEnabled(false);
        this.containerView.addView(this.stickerPreviewLayout, LayoutHelper.createFrame(-1, -1.0f));
        this.stickerPreviewLayout.setOnClickListener(new StickersAlert$$ExternalSyntheticLambda25(this));
        BackupImageView r118 = new BackupImageView(r28);
        this.stickerImageView = r118;
        r118.setAspectFit(true);
        this.stickerImageView.setLayerNum(7);
        this.stickerPreviewLayout.addView(this.stickerImageView);
        TextView r119 = new TextView(r28);
        this.stickerEmojiTextView = r119;
        r119.setTextSize(1, 30.0f);
        this.stickerEmojiTextView.setGravity(85);
        this.stickerPreviewLayout.addView(this.stickerEmojiTextView);
        TextView r120 = new TextView(r28);
        this.previewSendButton = r120;
        r120.setTextSize(1, 14.0f);
        this.previewSendButton.setTextColor(getThemedColor(r122));
        this.previewSendButton.setBackground(Theme.createSelectorWithBackgroundDrawable(getThemedColor(r36), getThemedColor(r132)));
        this.previewSendButton.setGravity(17);
        this.previewSendButton.setPadding(AndroidUtilities.dp(29.0f), 0, AndroidUtilities.dp(29.0f), 0);
        this.previewSendButton.setTypeface(AndroidUtilities.bold());
        this.stickerPreviewLayout.addView(this.previewSendButton, LayoutHelper.createFrame(-1, 48, 83));
        this.previewSendButton.setOnClickListener(new StickersAlert$$ExternalSyntheticLambda26(this));
        FrameLayout.LayoutParams r121 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 83);
        r121.bottomMargin = AndroidUtilities.dp(48.0f);
        View r37 = new View(r28);
        this.previewSendButtonShadow = r37;
        r37.setBackgroundColor(getThemedColor(r10));
        this.stickerPreviewLayout.addView(this.previewSendButtonShadow, r121);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        if (this.importingStickers == null) goto L9;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploadFailed);
    L9:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.stickersDidLoad);
        updateFields();
        updateSendButton();
        updateDescription();
        updateColors();
        this.adapter.notifyDataSetChanged();
        return;
    L5:
        r35 = 8;
        goto L6
    }

    private boolean isEmoji() {
        TLRPC.TL_messages_stickerSet r0 = this.stickerSet;
        if (r0 == null) goto L8;
        TLRPC.StickerSet r1 = r0.set;
        if (r1 == null) goto L8;
        if (r1.emojis == false) goto L8;
        return true;
    L8:
        if (r0 == null) goto L10;
        return false;
    L10:
        if (this.probablyEmojis == false) goto L16;
        return true;
    L16:
        return false;
    }

    private /* synthetic */ void lambda$checkOptions$16(View r1) {
        this.optionsButton.getPopupLayout().getSwipeBack().closeForeground();
    }

    private /* synthetic */ void lambda$checkOptions$17(View r8) {
        this.optionsButton.closeSubMenu();
        dismiss();
        MediaDataController.getInstance(this.currentAccount).toggleStickerSet(getContext(), this.stickerSet, 1, this.parentFragment, true, true);
    }

    private /* synthetic */ void lambda$checkOptions$18() {
        dismiss();
        MediaDataController.getInstance(this.currentAccount).toggleStickerSet(getContext(), this.stickerSet, 1, this.parentFragment, false, false);
    }

    private /* synthetic */ void lambda$checkOptions$19(View r4) {
        this.optionsButton.closeSubMenu();
        StickersDialogs.showDeleteForEveryOneDialog(this.stickerSet.set, this.resourcesProvider, getContext(), new StickersAlert$$ExternalSyntheticLambda55(this));
    }

    private /* synthetic */ void lambda$checkOptions$20() {
        MessagesController.getInstance(this.currentAccount).openByUserName("stickers", this.parentFragment, 1);
    }

    private /* synthetic */ void lambda$checkOptions$21(View r3) {
        this.optionsButton.closeSubMenu();
        dismiss();
        AndroidUtilities.runOnUIThread(new StickersAlert$$ExternalSyntheticLambda54(this), 200);
    }

    private /* synthetic */ void lambda$checkUrlAvailable$48(String r3, TLRPC.TL_error r4, TLObject r5, TextView r6) {
        this.checkReqId = 0;
        String r1 = this.lastCheckName;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.equals(r3) == false) goto L14;
        if (r4 == null) goto L8;
    L11:
        r6.setText(LocaleController.getString(R.string.ImportStickersLinkTaken));
        r6.setTextColor(getThemedColor(Theme.key_text_RedRegular));
        this.lastNameAvailable = false;
        return;
    L8:
        if ((r5 instanceof TLRPC.TL_boolTrue) == false) goto L11;
        r6.setText(LocaleController.getString(R.string.ImportStickersLinkAvailable));
        r6.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGreenText));
        this.lastNameAvailable = true;
        return;
    }

    private /* synthetic */ void lambda$checkUrlAvailable$49(String r7, TextView r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new StickersAlert$$ExternalSyntheticLambda58(this, r7, r10, r9, r8));
    }

    private /* synthetic */ void lambda$checkUrlAvailable$50(String r4, TextView r5) {
        TLRPC.TL_stickers_checkShortName r0 = new TLRPC.TL_stickers_checkShortName();
        r0.short_name = r4;
        this.checkReqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StickersAlert$$ExternalSyntheticLambda57(this, r4, r5), 2);
    }

    private /* synthetic */ void lambda$didReceivedNotification$51(String r2, SendMessagesHelper.ImportingSticker r3) {
        if (isDismissed() == true) goto L19;
        this.uploadImportStickers.remove(r2);
        if ("application/x-tgsticker".equals(r3.mimeType) == true) goto L8;
        removeSticker(r3);
    L15:
        if (this.uploadImportStickers.isEmpty() == false) goto L18;
        updateFields();
        return;
    L18:
        return;
    L8:
        r3.validated = true;
        int r22 = this.importingStickersPaths.indexOf(r3);
        if (r22 < 0) goto L13;
        RecyclerView.ViewHolder r23 = this.gridView.findViewHolderForAdapterPosition(r22);
        if (r23 == null) goto L15;
        ((StickerEmojiCell) r23.itemView).setSticker(r3);
        goto L15
    L13:
        this.adapter.notifyDataSetChanged();
        goto L15
    }

    private /* synthetic */ void lambda$disableEditMode$55() {
        this.adapter.notifyDataSetChanged();
    }

    private /* synthetic */ void lambda$enableEditMode$53() {
        this.adapter.notifyDataSetChanged();
    }

    private /* synthetic */ void lambda$init$10(DialogInterface r1) {
        this.masterDismissListener.run();
    }

    private /* synthetic */ void lambda$init$11(View r20, int r21) {
        if ((r20 instanceof AddStickerBtnView) == false) goto L7;
        StickersDialogs.showAddStickerDialog(this.stickerSet, r20, this.parentFragment, this.resourcesProvider);
        return;
    L7:
        if (this.isEditModeEnabled == false) goto L10;
        return;
    L10:
        if (this.stickerSetCovereds == null) goto L18;
        TLRPC.StickerSetCovered r1 = (TLRPC.StickerSetCovered) GridAdapter.m13126$$Nest$fgetpositionsToSets(this.adapter).get(r21);
        if (r1 == null) goto L64;
        this.ignoreMasterDismiss = true;
        dismiss();
        TLRPC.TL_inputStickerSetID r8 = new TLRPC.TL_inputStickerSetID();
        TLRPC.StickerSet r12 = r1.set;
        r8.access_hash = r12.access_hash;
        r8.id = r12.id;
        StickersAlert r5 = new StickersAlert(this.parentActivity, this.parentFragment, r8, null, null, this.resourcesProvider, false);
        if (this.masterDismissListener == null) goto L16;
        r5.setOnDismissListener(new StickersAlert$$ExternalSyntheticLambda43(this));
    L16:
        r5.show();
        return;
    L64:
        return;
    L18:
        ArrayList<SendMessagesHelper.ImportingSticker> r3 = this.importingStickersPaths;
        if (r3 == null) goto L34;
        if (r21 >= 0) goto L22;
        return;
    L22:
        if (r21 >= r3.size()) goto L71;
        SendMessagesHelper.ImportingSticker r13 = this.importingStickersPaths.get(r21);
        this.selectedStickerPath = r13;
        if (r13.validated == false) goto L72;
        TextView r2 = this.stickerEmojiTextView;
        r2.setText(Emoji.replaceEmoji(r13.emoji, r2.getPaint().getFontMetricsInt(), false));
        BackupImageView r9 = this.stickerImageView;
        ImageLocation r10 = ImageLocation.getForPath(this.selectedStickerPath.path);
        if (this.selectedStickerPath.animated == false) goto L31;
        String r14 = "tgs";
    L32:
        r9.setImage(r10, null, null, null, null, null, r14, 0, null);
        FrameLayout.LayoutParams r15 = (FrameLayout.LayoutParams) this.stickerPreviewLayout.getLayoutParams();
        r15.topMargin = this.scrollOffsetY;
        this.stickerPreviewLayout.setLayoutParams(r15);
        this.stickerPreviewLayout.setVisibility(0);
        AnimatorSet r16 = new AnimatorSet();
        r16.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.stickerPreviewLayout, View.ALPHA, new float[]{0.0f, 1.0f})});
        r16.setDuration(200);
        r16.start();
        return;
    L31:
        r14 = null;
        goto L32
    L72:
        return;
    L71:
        return;
    L34:
        TLRPC.TL_messages_stickerSet r32 = this.stickerSet;
        if (r32 == null) goto L66;
        if (r21 >= 0) goto L38;
        return;
    L38:
        if (r21 >= r32.documents.size()) goto L73;
        this.selectedSticker = this.stickerSet.documents.get(r21);
        int r22 = 0;
    L42:
        if (r22 >= this.selectedSticker.attributes.size()) goto L51;
        TLRPC.DocumentAttribute r33 = this.selectedSticker.attributes.get(r22);
        if ((r33 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L45;
        r22 = r22 + 1;
        goto L42
    L45:
        String r23 = r33.alt;
        if (r23 == null) goto L51;
        if (r23.length() <= 0) goto L51;
        TextView r24 = this.stickerEmojiTextView;
        r24.setText(Emoji.replaceEmoji(r33.alt, r24.getPaint().getFontMetricsInt(), false));
    L52:
        TLRPC.TL_messages_stickerSet r25 = this.stickerSet;
        if (r25 == null) goto L59;
        TLRPC.StickerSet r26 = r25.set;
        if (r26 == null) goto L59;
        if (r26.emojis == false) goto L59;
        return;
    L59:
        if (ContentPreviewViewer.getInstance().showMenuFor(r20) == true) goto L69;
        this.stickerImageView.getImageReceiver().setImage(ImageLocation.getForDocument(this.selectedSticker), null, ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(this.selectedSticker.thumbs, 90), this.selectedSticker), null, "webp", this.stickerSet, 1);
        FrameLayout.LayoutParams r17 = (FrameLayout.LayoutParams) this.stickerPreviewLayout.getLayoutParams();
        r17.topMargin = this.scrollOffsetY;
        this.stickerPreviewLayout.setLayoutParams(r17);
        this.stickerPreviewLayout.setVisibility(0);
        AnimatorSet r18 = new AnimatorSet();
        r18.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.stickerPreviewLayout, View.ALPHA, new float[]{0.0f, 1.0f})});
        r18.setDuration(200);
        r18.start();
        return;
    L69:
        return;
    L51:
        this.stickerEmojiTextView.setText(Emoji.replaceEmoji(MediaDataController.getInstance(this.currentAccount).getEmojiForSticker(this.selectedSticker.id), this.stickerEmojiTextView.getPaint().getFontMetricsInt(), false));
        goto L52
    L73:
        return;
    }

    private /* synthetic */ void lambda$init$13(View r1) {
        this.optionsButton.toggleSubMenu();
    }

    private /* synthetic */ void lambda$init$14(View r1) {
        hidePreview();
    }

    private /* synthetic */ void lambda$init$15(View r10) {
        if (this.importingStickersPaths == null) goto L6;
        removeSticker(this.selectedStickerPath);
        hidePreview();
        this.selectedStickerPath = null;
        return;
    L6:
        this.delegate.onStickerSelected(this.selectedSticker, null, this.stickerSet, null, this.clearsInputField, true, 0, 0);
        dismiss();
    }

    private /* synthetic */ boolean lambda$init$9(View r8, MotionEvent r9) {
        if (this.isEditModeEnabled == false) goto L7;
        return false;
    L7:
        return ContentPreviewViewer.getInstance().onTouch(r9, this.gridView, 0, this.stickersOnItemClickListener, this.previewDelegate, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$loadStickerSet$7(TLRPC.TL_error r3, TLObject r4, MediaDataController r5) {
        this.reqId = 0;
        if (r3 != null) goto L22;
        AnonymousClass4 r32 = new AnonymousClass4(this);
        r32.addTarget(this.containerView);
        TransitionManager.beginDelayedTransition(this.container, r32);
        this.optionsButton.setVisibility(0);
        TLRPC.TL_messages_stickerSet r42 = (TLRPC.TL_messages_stickerSet) r4;
        this.stickerSet = r42;
        r5.putStickerSet(r42, false);
        TLRPC.TL_messages_stickerSet r33 = this.stickerSet;
        if (r33 != null) goto L7;
    L10:
        TLRPC.TL_messages_stickerSet r34 = this.stickerSet;
        if (r34 == null) goto L17;
        TLRPC.StickerSet r35 = r34.set;
        if (r35 == null) goto L17;
        if (r35.masks == true) goto L17;
        boolean r36 = true;
    L18:
        this.showEmoji = r36;
        checkPremiumStickers();
        r5.preloadStickerSetThumb(this.stickerSet);
        updateSendButton();
        updateFields();
        updateDescription();
        this.adapter.notifyDataSetChanged();
        if (this.refreshingFromMenu == false) goto L26;
        scheduleRefreshTransition();
        this.refreshingFromMenu = false;
        return;
    L26:
        return;
    L17:
        r36 = false;
        goto L18
    L7:
        if (r33.documents.isEmpty() == false) goto L10;
        dismiss();
        return;
    L22:
        this.refreshingFromMenu = false;
        dismiss();
        BaseFragment r2 = this.parentFragment;
        if (r2 == null) goto L27;
        BulletinFactory.of(r2).createErrorBulletin(LocaleController.getString(R.string.AddStickersNotFound)).show();
        return;
    }

    private /* synthetic */ void lambda$loadStickerSet$8(MediaDataController r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new StickersAlert$$ExternalSyntheticLambda40(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$new$2(TLRPC.TL_error r9, TLObject r10, TLRPC.TL_messages_getAttachedStickers r11) {
        this.reqId = 0;
        if (r9 == null) goto L5;
    L16:
        AlertsCreator.processError(this.currentAccount, r9, this.parentFragment, r11, new Object[0]);
        dismiss();
        return;
    L5:
        if ((r10 instanceof Vector) == false) goto L16;
        Vector r102 = (Vector) r10;
        if (r102.objects.isEmpty() == false) goto L11;
        dismiss();
        return;
    L11:
        if (r102.objects.size() != 1) goto L14;
        TLRPC.StickerSetCovered r92 = (TLRPC.StickerSetCovered) r102.objects.get(0);
        TLRPC.TL_inputStickerSetID r103 = new TLRPC.TL_inputStickerSetID();
        this.inputStickerSet = r103;
        TLRPC.StickerSet r93 = r92.set;
        r103.id = r93.id;
        r103.access_hash = r93.access_hash;
        loadStickerSet(false);
        return;
    L14:
        ArrayList<TLRPC.StickerSetCovered> r94 = new ArrayList();
        this.stickerSetCovereds = r94;
        r94.addAll(r102.objects);
        this.gridView.setLayoutParams(LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 48.0f));
        this.titleTextView.setVisibility(8);
        this.shadow[0].setVisibility(8);
        this.adapter.notifyDataSetChanged();
    }

    private /* synthetic */ void lambda$new$3(TLRPC.TL_messages_getAttachedStickers r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new StickersAlert$$ExternalSyntheticLambda34(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$new$4(Object r2, TLRPC.TL_messages_getAttachedStickers r3, RequestDelegate r4, TLObject r5, TLRPC.TL_error r6) {
        if (r6 != null) goto L4;
    L8:
        r4.run(r5, r6);
        return;
    L4:
        if (FileRefController.isFileRefError(r6.text) == false) goto L8;
        if (r2 == null) goto L8;
        FileRefController.getInstance(this.currentAccount).requestReference(r2, new Object[]{r3, r4});
    }

    private /* synthetic */ void lambda$new$5(ArrayList r6, Boolean r7) {
        this.importingStickersPaths = r6;
        if (r6.isEmpty() == false) goto L6;
        dismiss();
        return;
    L6:
        this.adapter.notifyDataSetChanged();
        if (r7.booleanValue() == false) goto L11;
        this.uploadImportStickers = new HashMap();
        int r62 = this.importingStickersPaths.size();
        int r0 = 0;
    L9:
        if (r0 >= r62) goto L11;
        SendMessagesHelper.ImportingSticker r1 = this.importingStickersPaths.get(r0);
        this.uploadImportStickers.put(r1.path, r1);
        FileLoader.getInstance(this.currentAccount).uploadFile(r1.path, false, true, 67108864);
        r0 = r0 + 1;
    L11:
        updateFields();
    }

    private /* synthetic */ void lambda$new$6(ArrayList r14, ArrayList r15) {
        ArrayList r0 = new ArrayList();
        BitmapFactory.Options r1 = new BitmapFactory.Options();
        r1.inJustDecodeBounds = true;
        int r3 = r14.size();
        Boolean r4 = null;
        int r5 = 0;
    L3:
        if (r5 >= r3) goto L49;
        Object r6 = r14.get(r5);
        if ((r6 instanceof Uri) == false) goto L48;
        Uri r62 = (Uri) r6;
        String r7 = MediaController.getStickerExt(r62);
        if (r7 == null) goto L48;
        boolean r8 = "tgs".equals(r7);
        if (r4 != null) goto L13;
        r4 = Boolean.valueOf(r8);
    L16:
        if (isDismissed() == true) goto L17;
        SendMessagesHelper.ImportingSticker r9 = new SendMessagesHelper.ImportingSticker();
        r9.animated = r8;
        if (r8 == false) goto L21;
        int r11 = 64;
    L22:
        String r63 = MediaController.copyFileToCache(r62, r7, r11 * 1024);
        r9.path = r63;
        if (r63 == null) goto L48;
        if (r8 == true) goto L37;
        BitmapFactory.decodeFile(r63, r1);
        int r64 = r1.outWidth;
        if (r64 != 512) goto L32;
        int r82 = r1.outHeight;
        if (r82 <= 0) goto L32;
        if (r82 > 512) goto L32;
    L36:
        r9.mimeType = "image/".concat(r7);
        r9.validated = true;
    L38:
        if (r15 != null) goto L40;
    L44:
        r9.emoji = "#️⃣";
    L45:
        r0.add(r9);
        if (r0.size() < 200) goto L48;
    L40:
        if (r15.size() != r3) goto L44;
        if ((r15.get(r5) instanceof String) == false) goto L44;
        r9.emoji = (String) r15.get(r5);
    L32:
        if (r1.outHeight != 512) goto L48;
        if (r64 <= 0) goto L48;
        if (r64 <= 512) goto L36;
    L37:
        r9.mimeType = "application/x-tgsticker";
        goto L38
    L21:
        r11 = 512;
        goto L22
    L17:
        return;
    L13:
        if (r4.booleanValue() == r8) goto L16;
    L48:
        r5 = r5 + 1;
    L49:
        AndroidUtilities.runOnUIThread(new StickersAlert$$ExternalSyntheticLambda29(this, r0, r4));
    }

    private /* synthetic */ void lambda$onSubItemClick$22(AlertDialog r1, long r2, TLRPC.User r4) {
        r1.dismiss();
        if (r4 == null) goto L12;
        dismiss();
        Bundle r12 = new Bundle();
        r12.putLong("user_id", r4.id);
        ProfileActivity r22 = new ProfileActivity(r12);
        BaseFragment r13 = this.parentFragment;
        if (r13 == null) goto L9;
        r13.presentFragment(r22, false, false);
        return;
    L9:
        if ((getContext() instanceof LaunchActivity) == false) goto L14;
        ((LaunchActivity) getContext()).presentFragment(r22, false, false);
        return;
    L14:
        return;
    L12:
        AndroidUtilities.addToClipboard(String.valueOf(r2));
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
    }

    private /* synthetic */ void lambda$onSubItemClick$23(Runnable r1) {
        this.cancelSearchCreatorRunnable = r1;
    }

    private /* synthetic */ void lambda$onSubItemClick$24(DialogInterface r1) {
        Runnable r12 = this.cancelSearchCreatorRunnable;
        if (r12 == null) goto L6;
        r12.run();
        this.cancelSearchCreatorRunnable = null;
        return;
    }

    private /* synthetic */ void lambda$onSubItemClick$27(CharSequence r2, Utilities.Callback r3) {
        this.titleTextView.setText(r2);
        TLRPC.TL_stickers_renameStickerSet r0 = new TLRPC.TL_stickers_renameStickerSet();
        r0.stickerset = MediaDataController.getInputStickerSet(this.stickerSet.set);
        r0.title = r2.toString();
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r0, new StickersAlert$$ExternalSyntheticLambda51(r3));
    }

    private /* synthetic */ void lambda$onSubItemClick$28() {
        dismiss();
        MediaDataController.getInstance(this.currentAccount).toggleStickerSet(getContext(), this.stickerSet, 1, this.parentFragment, false, false);
    }

    private /* synthetic */ void lambda$showNameEnterAlert$44(TLObject r3, EditTextBoldCursor r4, TextView r5, TextView r6, int[] r7) {
        if ((r3 instanceof TLRPC.TL_stickers_suggestedShortName) == false) goto L7;
        String r32 = ((TLRPC.TL_stickers_suggestedShortName) r3).short_name;
        if (r32 == null) goto L7;
        r4.setText(r32);
        r4.setSelection(0, r4.length());
        boolean r0 = true;
        checkUrlAvailable(r5, r4.getText().toString(), true);
    L8:
        r6.setVisibility(0);
        r4.setPadding(r6.getMeasuredWidth(), AndroidUtilities.dp(4.0f), 0, 0);
        if (r0 == true) goto L11;
        r4.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L11:
        r7[0] = 2;
        return;
    L7:
        r0 = false;
        goto L8
    }

    private /* synthetic */ void lambda$showNameEnterAlert$45(EditTextBoldCursor r1, TextView r2, TextView r3, int[] r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new StickersAlert$$ExternalSyntheticLambda53(this, r5, r1, r2, r3, r4));
    }

    private /* synthetic */ void lambda$showNameEnterAlert$46(String r4) {
        new ImportingAlert(getContext(), this.lastCheckName, null, this.resourcesProvider).show();
    }

    private /* synthetic */ void lambda$showNameEnterAlert$47(int[] r9, EditTextBoldCursor r10, TextView r11, TextView r12, AlertDialog.Builder r13, View r14) {
        int r0 = r9[0];
        if (r0 == 1) goto L20;
        if (r0 != 0) goto L9;
        r9[0] = 1;
        TLRPC.TL_stickers_suggestShortName r132 = new TLRPC.TL_stickers_suggestShortName();
        String r142 = r10.getText().toString();
        this.setTitle = r142;
        r132.title = r142;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r132, new StickersAlert$$ExternalSyntheticLambda32(this, r10, r11, r12, r9));
        return;
    L9:
        if (r0 != 2) goto L19;
        r9[0] = 3;
        if (this.lastNameAvailable == true) goto L14;
        AndroidUtilities.shakeView(r10);
        r10.performHapticFeedback(3, 2);     // Catch: Exception -> L16
    L14:
        AndroidUtilities.hideKeyboard(r10);
        SendMessagesHelper.getInstance(this.currentAccount).prepareImportStickers(this.setTitle, this.lastCheckName, this.importingSoftware, this.importingStickersPaths, new StickersAlert$$ExternalSyntheticLambda33(this));
        r13.getDismissRunnable().run();
        dismiss();
        return;
    L19:
        return;
    }

    private /* synthetic */ void lambda$updateFields$29(View r3) {
        BaseFragment r32 = this.parentFragment;
        if (r32 == null) goto L7;
        new PremiumFeatureBottomSheet(r32, 11, false).show();
        return;
    L7:
        if ((getContext() instanceof LaunchActivity) == false) goto L10;
        ((LaunchActivity) getContext()).presentFragment(new PremiumPreviewFragment(null));
        return;
    }

    private /* synthetic */ void lambda$updateFields$30(View r1) {
        if (this.customButtonDelegate.onCustomButtonPressed() == false) goto L6;
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$updateFields$31(TLRPC.TL_error r11, TLObject r12) {
        TLRPC.StickerSet r0 = this.stickerSet.set;
        if (r0.masks == false) goto L6;
        int r1 = 1;
    L23:
        if (r11 == null) goto L11;
        Toast.makeText(getContext(), LocaleController.getString(R.string.ErrorOccurred), 0).show();     // Catch: Exception -> L13
    L21:
        MediaDataController.getInstance(this.currentAccount).loadStickers(r1, false, true);
        return;
    L11:
        if (this.showTooltipWhenToggle == false) goto L16;
        Bulletin.make(this.parentFragment, new StickerSetBulletinLayout(this.pickerBottomFrameLayout.getContext(), this.stickerSet, 2, null, this.resourcesProvider), 1500).show();     // Catch: Exception -> L13
    L16:
        if ((r12 instanceof TLRPC.TL_messages_stickerSetInstallResultArchive) == false) goto L21;
        MediaDataController.getInstance(this.currentAccount).processStickerSetInstallResultArchive(this.parentFragment, true, r1, (TLRPC.TL_messages_stickerSetInstallResultArchive) r12);     // Catch: Exception -> L13
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L21
    L6:
        if (r0.emojis == false) goto L8;
        r1 = 5;
        goto L23
    L8:
        r1 = 0;
        goto L23
    }

    private /* synthetic */ void lambda$updateFields$32(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new StickersAlert$$ExternalSyntheticLambda39(this, r3, r2));
    }

    private /* synthetic */ void lambda$updateFields$33(View r3) {
        dismiss();
        StickersAlertInstallDelegate r32 = this.installDelegate;
        if (r32 == null) goto L6;
        r32.onStickerSetInstalled();
    L6:
        if (this.inputStickerSet != null) goto L8;
        return;
    L8:
        if (MediaDataController.getInstance(this.currentAccount).cancelRemovingStickerSet(this.inputStickerSet.id) == true) goto L13;
        TLRPC.TL_messages_installStickerSet r33 = new TLRPC.TL_messages_installStickerSet();
        r33.stickerset = this.inputStickerSet;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r33, new StickersAlert$$ExternalSyntheticLambda11(this));
        return;
    }

    private /* synthetic */ void lambda$updateFields$34(View r1) {
        if (this.isEditModeEnabled == false) goto L6;
        disableEditMode();
        return;
    L6:
        enableEditMode();
    }

    private /* synthetic */ void lambda$updateFields$35(View r8) {
        StickersAlertInstallDelegate r82 = this.installDelegate;
        if (r82 == null) goto L5;
        r82.onStickerSetUninstalled();
    L5:
        dismiss();
        MediaDataController.getInstance(this.currentAccount).toggleStickerSet(getContext(), this.stickerSet, 1, this.parentFragment, true, this.showTooltipWhenToggle);
    }

    private /* synthetic */ void lambda$updateFields$36(View r8) {
        StickersAlertInstallDelegate r82 = this.installDelegate;
        if (r82 == null) goto L5;
        r82.onStickerSetUninstalled();
    L5:
        dismiss();
        MediaDataController.getInstance(this.currentAccount).toggleStickerSet(getContext(), this.stickerSet, 0, this.parentFragment, true, this.showTooltipWhenToggle);
    }

    private /* synthetic */ void lambda$updateFields$37(View r1) {
        showNameEnterAlert();
    }

    private /* synthetic */ void lambda$updateFields$38(View r1) {
        dismiss();
    }

    private void onSubItemClick(int r11) {
        TLRPC.TL_messages_stickerSet r0 = this.stickerSet;
        if (r0 == null) goto L74;
        TLRPC.StickerSet r02 = r0.set;
        if (r02 != null) goto L8;
    L11:
        String r03 = "https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/addstickers/" + this.stickerSet.set.short_name;
    L10:
        String r5 = r03;
        if (r11 != 1) goto L30;
        Context r112 = this.parentActivity;
        if (r112 != null) goto L19;
        BaseFragment r04 = this.parentFragment;
        if (r04 == null) goto L19;
        r112 = r04.getParentActivity();
    L19:
        if (r112 != null) goto L21;
        r112 = getContext();
    L21:
        AnonymousClass14 r1 = new AnonymousClass14(this, r112, null, r5, false, r5, false, this.resourcesProvider);
        BaseFragment r10 = this.parentFragment;
        if (r10 == null) goto L27;
        r10.showDialog(r1);
        BaseFragment r102 = this.parentFragment;
        if ((r102 instanceof ChatActivity) == false) goto L70;
        r1.setCalcMandatoryInsets(((ChatActivity) r102).isKeyboardVisible());
        return;
    L70:
        return;
    L27:
        r1.show();
        return;
    L30:
        if (r11 != 2) goto L36;
        AndroidUtilities.addToClipboard(r5);     // Catch: Exception -> L33
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createCopyLinkBulletin().show();     // Catch: Exception -> L33
        return;
    L33:
        e = move-exception;
        FileLog.e(e);
        return;
    L36:
        if (r11 != 3) goto L48;
        AlertDialog r113 = new AlertDialog(getContext(), 3);     // Catch: Exception -> L45
        r113.setCancelDialog(true);     // Catch: Exception -> L45
        r113.show();     // Catch: Exception -> L45
        long r05 = ChatUtils.extractOwnerId(this.stickerSet.set.id);     // Catch: Exception -> L45
        this.cancelSearchCreatorRunnable = ChatUtils.getInstance().searchUserById(Long.valueOf(r05), new StickersAlert$$ExternalSyntheticLambda46(this, r113, r05), new StickersAlert$$ExternalSyntheticLambda47(this));     // Catch: Exception -> L45
        if (r113.isDismissed() == false) goto L43;
        Runnable r06 = this.cancelSearchCreatorRunnable;     // Catch: Exception -> L45
        if (r06 == null) goto L43;
        r06.run();     // Catch: Exception -> L45
        this.cancelSearchCreatorRunnable = null;     // Catch: Exception -> L45
        return;
    L43:
        r113.setOnCancelListener(new StickersAlert$$ExternalSyntheticLambda48(this));     // Catch: Exception -> L45
        return;
    L45:
        e = move-exception;
        FileLog.e(e);
        return;
    L48:
        if (r11 != 4) goto L56;
        if (this.isEditModeEnabled == false) goto L53;
        disableEditMode();
        return;
    L53:
        enableEditMode();
        return;
    L56:
        if (r11 != 5) goto L60;
        StickersDialogs.showNameEditorDialog(this.stickerSet.set, this.resourcesProvider, getContext(), new StickersAlert$$ExternalSyntheticLambda49(this));
        return;
    L60:
        if (r11 != 6) goto L64;
        StickersDialogs.showDeleteForEveryOneDialog(this.stickerSet.set, this.resourcesProvider, getContext(), new StickersAlert$$ExternalSyntheticLambda50(this));
        return;
    L64:
        if (r11 != 7) goto L73;
        this.refreshingFromMenu = true;
        this.stickerSet = null;
        loadStickerSet(true);
        return;
    L73:
        return;
    L8:
        if (r02.emojis == false) goto L11;
        r03 = "https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/addemoji/" + this.stickerSet.set.short_name;
        goto L10
    }

    private void playRefreshTransition() {
        RecyclerListView r0 = this.gridView;
        if (r0 == null) goto L26;
        int r02 = r0.getChildCount();
        if (r02 <= 0) goto L35;
        int r1 = Integer.MAX_VALUE;
        int r3 = Integer.MAX_VALUE;
        int r4 = 0;
    L9:
        if (r4 >= r02) goto L17;
        View r5 = this.gridView.getChildAt(r4);
        if (r5 == null) goto L16;
        if ((r5 instanceof EmptyCell) == true) goto L16;
        r1 = Math.min(r1, r5.getLeft());
        r3 = Math.min(r3, r5.getTop());
    L16:
        r4 = r4 + 1;
        goto L9
    L17:
        int r42 = 0;
    L18:
        if (r42 >= r02) goto L36;
        View r52 = this.gridView.getChildAt(r42);
        if (r52 == null) goto L25;
        if ((r52 instanceof EmptyCell) == true) goto L25;
        long r6 = Math.min((long) (((Math.max(0, r52.getLeft() - r1) / AndroidUtilities.dp(26.0f)) + (Math.max(0, r52.getTop() - r3) / AndroidUtilities.dp(18.0f))) * 8.0f), 90);
        r52.animate().cancel();
        r52.setAlpha(0.72f);
        r52.setTranslationX(-AndroidUtilities.dp(6.0f));
        r52.setTranslationY(-AndroidUtilities.dp(4.0f));
        r52.animate().alpha(1.0f).translationX(0.0f).translationY(0.0f).setStartDelay(r6).setDuration(130).setInterpolator(CubicBezierInterpolator.EASE_OUT).start();
    L25:
        r42 = r42 + 1;
        goto L18
    L36:
        return;
    L35:
        return;
    }

    private void removeSticker(SendMessagesHelper.ImportingSticker r2) {
        int r22 = this.importingStickersPaths.indexOf(r2);
        if (r22 < 0) goto L10;
        this.importingStickersPaths.remove(r22);
        this.adapter.notifyItemRemoved(r22);
        if (this.importingStickersPaths.isEmpty() == false) goto L8;
        dismiss();
        return;
    L8:
        updateFields();
        return;
    }

    private void runShadowAnimation(int r8, boolean r9) {
        if (this.stickerSetCovereds != null) goto L29;
        if (r9 == true) goto L7;
    L8:
        if (r9 == false) goto L10;
        return;
    L10:
        if (this.shadow[r8].getTag() != null) goto L28;
    L11:
        View r0 = this.shadow[r8];
        if (r9 == false) goto L14;
        Integer r2 = null;
    L15:
        r0.setTag(r2);
        if (r9 == false) goto L18;
        this.shadow[r8].setVisibility(0);
    L18:
        AnimatorSet r22 = this.shadowAnimation[r8];
        if (r22 == null) goto L21;
        r22.cancel();
    L21:
        this.shadowAnimation[r8] = new AnimatorSet();
        AnimatorSet r23 = this.shadowAnimation[r8];
        View r3 = this.shadow[r8];
        Property r4 = View.ALPHA;
        if (r9 == false) goto L24;
        float r5 = 1.0f;
    L25:
        r23.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, r4, new float[]{r5})});
        this.shadowAnimation[r8].setDuration(150);
        this.shadowAnimation[r8].addListener(new AnonymousClass18(this, r8, r9));
        this.shadowAnimation[r8].start();
        return;
    L24:
        r5 = 0.0f;
        goto L25
    L14:
        r2 = 1;
        goto L15
    L28:
        return;
    L7:
        if (this.shadow[r8].getTag() != null) goto L11;
    }

    private void scheduleRefreshTransition() {
        RecyclerListView r0 = this.gridView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.post(new StickersAlert$$ExternalSyntheticLambda42(this));
    }

    private void setButton(View.OnClickListener r7, String r8, int r9) {
        setButton(r7, r8, r9, -1, -1);
    }

    private void setButton(View.OnClickListener r8, String r9, int r10, int r11, int r12) {
        if (r10 < 0) goto L4;
        AnimatedTextView r0 = this.pickerBottomLayout;
        this.buttonTextColorKey = r10;
        r0.setTextColor(getThemedColor(r10));
    L4:
        this.pickerBottomLayout.setText(r9, false);
        this.pickerBottomLayout.setOnClickListener(r8);
        ViewGroup.MarginLayoutParams r92 = (ViewGroup.MarginLayoutParams) this.pickerBottomLayout.getLayoutParams();
        ViewGroup.MarginLayoutParams r102 = (ViewGroup.MarginLayoutParams) this.shadow[1].getLayoutParams();
        ViewGroup.MarginLayoutParams r1 = (ViewGroup.MarginLayoutParams) this.gridView.getLayoutParams();
        ViewGroup.MarginLayoutParams r2 = (ViewGroup.MarginLayoutParams) this.emptyView.getLayoutParams();
        if (r8 != null) goto L8;
        this.pickerBottomLayout.setAlpha(0.0f);
    L16:
        this.containerView.requestLayout();
        return;
    L8:
        if (r11 < 0) goto L13;
        if (r12 < 0) goto L13;
        this.pickerBottomLayout.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(24.0f), getThemedColor(r11), getThemedColor(r12)));
        this.pickerBottomFrameLayout.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        int r112 = AndroidUtilities.dp(8.0f);
        r92.bottomMargin = r112;
        r92.rightMargin = r112;
        r92.topMargin = r112;
        r92.leftMargin = r112;
        int r93 = AndroidUtilities.dp(64.0f);
        r102.bottomMargin = r93;
        r1.bottomMargin = r93;
        r2.bottomMargin = r93;
        if (this.pickerBottomLayout.getAlpha() >= 1.0f) goto L16;
        this.pickerBottomLayout.animate().alpha(1.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(240).start();
    L13:
        this.pickerBottomLayout.setBackground(Theme.createSelectorWithBackgroundDrawable(getThemedColor(Theme.key_dialogBackground), Theme.multAlpha(getThemedColor(Theme.key_text_RedBold), 0.1f)));
        this.pickerBottomFrameLayout.setBackgroundColor(0);
        r92.bottomMargin = 0;
        r92.rightMargin = 0;
        r92.topMargin = 0;
        r92.leftMargin = 0;
        int r94 = AndroidUtilities.dp(48.0f);
        r102.bottomMargin = r94;
        r1.bottomMargin = r94;
        r2.bottomMargin = r94;
        if (this.pickerBottomLayout.getAlpha() >= 1.0f) goto L16;
        this.pickerBottomLayout.animate().alpha(1.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(240).start();
        goto L16
    }

    private void setScrollOffsetY(int r3) {
        this.scrollOffsetY = r3;
        if (this.stickerSetCovereds != null) goto L11;
        float r32 = r3;
        this.titleTextView.setTranslationY(r32);
        TextView r0 = this.descriptionTextView;
        if (r0 == null) goto L8;
        r0.setTranslationY(r32);
    L8:
        if (this.importingStickers != null) goto L10;
        this.optionsButton.setTranslationY(r32);
    L10:
        this.shadow[0].setTranslationY(r32);
    L11:
        this.containerView.invalidate();
    }

    private void showNameEnterAlert() {
        Context r0 = getContext();
        int[] r3 = {0};
        FrameLayout r4 = new FrameLayout(r0);
        AlertDialog.Builder r6 = new AlertDialog.Builder(r0);
        r6.setTitle(LocaleController.getString(R.string.ImportStickersEnterName));
        r6.setPositiveButton(LocaleController.getString(R.string.Next), new StickersAlert$$ExternalSyntheticLambda12());
        LinearLayout r5 = new LinearLayout(r0);
        r5.setOrientation(1);
        r6.setView(r5);
        r5.addView(r4, LayoutHelper.createLinear(-1, 36, 51, 24, 6, 24, 0));
        TextView r8 = new TextView(r0);
        TextView r9 = new TextView(r0);
        r9.setTextSize(1, 16.0f);
        r9.setTextColor(getThemedColor(Theme.key_dialogTextHint));
        r9.setMaxLines(1);
        r9.setLines(1);
        r9.setText("t.me/addstickers/");
        r9.setInputType(16385);
        r9.setGravity(51);
        r9.setSingleLine(true);
        r9.setVisibility(4);
        r9.setImeOptions(6);
        r9.setPadding(0, AndroidUtilities.dp(4.0f), 0, 0);
        r4.addView(r9, LayoutHelper.createFrame(-2, 36, 51));
        EditTextBoldCursor r13 = new EditTextBoldCursor(r0);
        r13.setBackground(null);
        r13.setLineColors(Theme.getColor(Theme.key_dialogInputField), Theme.getColor(Theme.key_dialogInputFieldActivated), Theme.getColor(Theme.key_text_RedBold));
        r13.setTextSize(1, 16.0f);
        r13.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        r13.setMaxLines(1);
        r13.setLines(1);
        r13.setInputType(16385);
        r13.setGravity(51);
        r13.setSingleLine(true);
        r13.setImeOptions(5);
        r13.setCursorColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        r13.setCursorSize(AndroidUtilities.dp(20.0f));
        r13.setCursorWidth(1.5f);
        r13.setPadding(0, AndroidUtilities.dp(4.0f), 0, 0);
        r13.addTextChangedListener(new AnonymousClass16(this, r3, r8, r13));
        r4.addView(r13, LayoutHelper.createFrame(-1, 36, 51));
        r13.setOnEditorActionListener(new StickersAlert$$ExternalSyntheticLambda13(r6));
        r13.setSelection(r13.length());
        r6.setNegativeButton(LocaleController.getString(R.string.Cancel), new StickersAlert$$ExternalSyntheticLambda14(r13));
        r8.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ImportStickersEnterNameInfo)));
        r8.setTextSize(1, 14.0f);
        r8.setPadding(AndroidUtilities.dp(23.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(23.0f), AndroidUtilities.dp(6.0f));
        r8.setTextColor(getThemedColor(Theme.key_dialogTextGray2));
        r5.addView(r8, LayoutHelper.createLinear(-1, -2));
        AlertDialog r2 = r6.create();
        r2.setOnShowListener(new StickersAlert$$ExternalSyntheticLambda15(r13));
        r2.show();
        r13.requestFocus();
        r2.getButton(-1).setOnClickListener(new StickersAlert$$ExternalSyntheticLambda16(this, r3, r13, r8, r9, r6));
    }

    private void updateDescription() {
        if (this.containerView != null) goto L6;
        return;
    L6:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L9;
        MessageObject.isPremiumEmojiPack(this.stickerSet);
        return;
    }

    private void updateFields() {
        if (this.titleTextView != null) goto L5;
        return;
    L5:
        TLRPC.TL_messages_stickerSet r0 = this.stickerSet;
        int r2 = 1;
        if (r0 == null) goto L169;
        ArrayList<TLRPC.Document> r02 = r0.documents;
        if (r02 == null) goto L169;
        if (r02.isEmpty() == true) goto L169;
        CharSequence r4 = Emoji.replaceEmoji(this.stickerSet.set.title, this.titleTextView.getPaint().getFontMetricsInt(), false);
    L15:
        e = move-exception;
        FileLog.e(e);
    L30:
        this.titleTextView.setText(r4);
        if (isEmoji() == false) goto L40;
        int r03 = this.gridView.getMeasuredWidth();
        if (r03 != 0) goto L35;
        r03 = AndroidUtilities.displaySize.x;
    L35:
        GridAdapter r5 = this.adapter;
        if (AndroidUtilities.isTablet() == false) goto L38;
        float r6 = 60.0f;
    L39:
        GridAdapter.m13130$$Nest$fputstickersPerRow(r5, Math.max(1, r03 / AndroidUtilities.dp(r6)));
    L41:
        this.layoutManager.setSpanCount(GridAdapter.m13127$$Nest$fgetstickersPerRow(this.adapter));
        TLRPC.TL_messages_stickerSet r04 = this.stickerSet;
        if (r04 == null) goto L61;
        TLRPC.StickerSet r05 = r04.set;
        if (r05 == null) goto L61;
        if (r05.emojis == false) goto L61;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L61;
        if (this.customButtonDelegate != null) goto L61;
        if (this.stickerSet.documents == null) goto L62;
        int r06 = 0;
    L55:
        if (r06 >= this.stickerSet.documents.size()) goto L62;
        if (MessageObject.isFreeEmoji(this.stickerSet.documents.get(r06)) == false) goto L58;
        r06 = r06 + 1;
        goto L55
    L58:
        this.premiumButtonView.setVisibility(0);
        this.pickerBottomLayout.setBackground(null);
        setButton(null, null, -1);
        this.premiumButtonView.setButton(LocaleController.getString(R.string.UnlockPremiumEmoji), new StickersAlert$$ExternalSyntheticLambda0(this));
        return;
    L62:
        MediaDataController r07 = MediaDataController.getInstance(this.currentAccount);
        TLRPC.TL_messages_stickerSet r1 = this.stickerSet;
        if (r1 == null) goto L82;
        TLRPC.StickerSet r52 = r1.set;
        if (r52 == null) goto L82;
        if (r52.emojis == false) goto L82;
        ArrayList<TLRPC.TL_messages_stickerSet> r12 = r07.getStickerSets(5);
        int r53 = 0;
    L69:
        if (r12 == null) goto L80;
        if (r53 >= r12.size()) goto L80;
        if (r12.get(r53) == null) goto L79;
        if (r12.get(r53).set == null) goto L79;
        if (r12.get(r53).set.id != this.stickerSet.set.id) goto L79;
        boolean r13 = true;
    L81:
        boolean r14 = !r13;
    L91:
        if (this.customButtonDelegate == null) goto L95;
        setButton(new StickersAlert$$ExternalSyntheticLambda1(this), this.customButtonDelegate.getCustomButtonText(), this.customButtonDelegate.getCustomButtonTextColorKey(), this.customButtonDelegate.getCustomButtonColorKey(), this.customButtonDelegate.getCustomButtonRippleColorKey());
        return;
    L95:
        if (r14 == false) goto L114;
        TLRPC.TL_messages_stickerSet r122 = this.stickerSet;
        if (r122 == null) goto L103;
        TLRPC.StickerSet r54 = r122.set;
        if (r54 == null) goto L103;
        if (r54.emojis == false) goto L103;
        r2 = 5;
    L111:
        if (r07.areStickersLoaded(r2) == true) goto L114;
        r07.checkStickers(r2);
        setButton(null, _UrlKt.FRAGMENT_ENCODE_SET, -1, -1, -1);
        return;
    L103:
        if (r122 == null) goto L109;
        TLRPC.StickerSet r123 = r122.set;
        if (r123 == null) goto L109;
        if (r123.masks == true) goto L111;
    L109:
        r2 = 0;
    L114:
        TLRPC.TL_messages_stickerSet r124 = this.stickerSet;
        if (r14 == false) goto L146;
        if (r124 == null) goto L127;
        TLRPC.StickerSet r08 = r124.set;
        if (r08 == null) goto L127;
        if (r08.masks == false) goto L127;
        ArrayList<TLRPC.Document> r125 = r124.documents;
        if (r125 != null) goto L124;
        int r126 = 0;
    L125:
        String r127 = LocaleController.formatPluralString("AddManyMasksCount", r126, new Object[0]);
    L144:
        StickersAlert r15 = this;
        r15.setButton(new StickersAlert$$ExternalSyntheticLambda2(this), r127, Theme.key_featuredStickers_buttonText, Theme.key_featuredStickers_addButton, Theme.key_featuredStickers_addButtonPressed);
    L145:
        StickersAlert r62 = r15;
    L167:
        r62.adapter.notifyDataSetChanged();
        return;
    L124:
        r126 = r125.size();
    L127:
        if (r124 == null) goto L137;
        TLRPC.StickerSet r09 = r124.set;
        if (r09 == null) goto L137;
        if (r09.emojis == false) goto L137;
        ArrayList<TLRPC.Document> r128 = r124.documents;
        if (r128 != null) goto L135;
        int r129 = 0;
    L136:
        r127 = LocaleController.formatPluralString("AddManyEmojiCount", r129, new Object[0]);
        goto L144
    L135:
        r129 = r128.size();
    L137:
        if (r124 == null) goto L142;
        ArrayList<TLRPC.Document> r1210 = r124.documents;
        if (r1210 == null) goto L142;
        int r1211 = r1210.size();
    L143:
        r127 = LocaleController.formatPluralString("AddManyStickersCount", r1211, new Object[0]);
    L142:
        r1211 = 0;
        goto L143
    L146:
        r15 = this;
        TLRPC.StickerSet r010 = r124.set;
        boolean r22 = r010.creator;
        if (r22 == false) goto L155;
        if (r15.isEditModeEnabled == false) goto L151;
        int r1212 = R.string.Done;
    L152:
        String r1213 = LocaleController.getString(r1212);
    L153:
        String r3 = r1213;
        if (r22 == false) goto L163;
        r15.setButton(new StickersAlert$$ExternalSyntheticLambda3(r15), r3, Theme.key_featuredStickers_buttonText, Theme.key_featuredStickers_addButton, Theme.key_featuredStickers_addButtonPressed);
        goto L145
    L163:
        r62 = r15;
        if (r62.stickerSet.set.official == false) goto L166;
        r62.setButton(new StickersAlert$$ExternalSyntheticLambda4(r62), r3, Theme.key_text_RedBold);
        goto L167
    L166:
        r62.setButton(new StickersAlert$$ExternalSyntheticLambda5(r62), r3, Theme.key_text_RedBold);
        goto L167
    L151:
        r1212 = R.string.EditStickers;
        goto L152
    L155:
        if (r010.masks == false) goto L158;
        r1213 = LocaleController.formatPluralString("RemoveManyMasksCount", r124.documents.size(), new Object[0]);
        goto L153
    L158:
        if (r010.emojis == false) goto L160;
        r1213 = LocaleController.formatPluralString("RemoveManyEmojiCount", r124.documents.size(), new Object[0]);
        goto L153
    L160:
        r1213 = LocaleController.formatPluralString("RemoveManyStickersCount", r124.documents.size(), new Object[0]);
    L79:
        r53 = r53 + 1;
    L80:
        r13 = false;
    L82:
        if (r1 == null) goto L89;
        TLRPC.StickerSet r16 = r1.set;
        if (r16 == null) goto L89;
        if (r07.isStickerPackInstalled(r16.id) == false) goto L89;
        r14 = false;
    L89:
        r14 = true;
    L61:
        this.premiumButtonView.setVisibility(4);
        goto L62
    L38:
        r6 = 45.0f;
        goto L39
    L40:
        GridAdapter.m13130$$Nest$fputstickersPerRow(this.adapter, 5);
        goto L41
    L13:
        if (this.urlPattern != null) goto L17;
        this.urlPattern = Pattern.compile("@[a-zA-Z\\d_]{1,32}");     // Catch: Exception -> L15
    L17:
        Matcher r011 = this.urlPattern.matcher(r4);     // Catch: Exception -> L15
        SpannableStringBuilder r55 = null;
    L18:
        if (r011.find() == false) goto L27;
        if (r55 != null) goto L22;
        r55 = new SpannableStringBuilder(r4);     // Catch: Exception -> L15
    L22:
        int r63 = r011.start();     // Catch: Exception -> L15
        int r7 = r011.end();     // Catch: Exception -> L15
        if (this.stickerSet.set.title.charAt(r63) == '@') goto L25;
        r63 = r63 + 1;     // Catch: Exception -> L15
    L25:
        r55.setSpan(new AnonymousClass15(this, r4.subSequence(r63 + 1, r7).toString()), r63, r7, 0);     // Catch: Exception -> L15
        goto L18
    L27:
        if (r55 == null) goto L30;
        r4 = r55;
    L169:
        ArrayList<Parcelable> r1214 = this.importingStickers;
        if (r1214 == null) goto L188;
        LinkSpanDrawable.LinksTextView r012 = this.titleTextView;
        ArrayList<SendMessagesHelper.ImportingSticker> r42 = this.importingStickersPaths;
        if (r42 == null) goto L174;
        int r1215 = r42.size();
    L175:
        r012.setText(LocaleController.formatPluralString("Stickers", r1215, new Object[0]));
        HashMap<String, SendMessagesHelper.ImportingSticker> r1216 = this.uploadImportStickers;
        if (r1216 != null) goto L178;
    L182:
        StickersAlert$$ExternalSyntheticLambda6 r1217 = new StickersAlert$$ExternalSyntheticLambda6(this);
        int r013 = R.string.ImportStickers;
        ArrayList r17 = this.importingStickersPaths;
        if (r17 != null) goto L186;
        r17 = this.importingStickers;
    L186:
        setButton(r1217, LocaleController.formatString("ImportStickers", r013, new Object[]{LocaleController.formatPluralString("Stickers", r17.size(), new Object[0])}), Theme.key_dialogTextBlue2);
        this.pickerBottomLayout.setEnabled(true);
        return;
    L178:
        if (r1216.isEmpty() == true) goto L182;
        setButton(null, LocaleController.getString(R.string.ImportStickersProcessing), Theme.key_dialogTextGray2);
        this.pickerBottomLayout.setEnabled(false);
        return;
    L174:
        r1215 = r1214.size();
        goto L175
    L188:
        setButton(new StickersAlert$$ExternalSyntheticLambda7(this), LocaleController.getString(R.string.Close), Theme.key_dialogTextBlue2);
    }

    @SuppressLint({"NewApi"})
    private void updateLayout() {
        if (this.gridView.getChildCount() > 0) goto L6;
        setScrollOffsetY(this.gridView.getPaddingTop());
        return;
    L6:
        int r1 = 0;
        View r2 = null;
        int r4 = -1;
        int r3 = 0;
    L8:
        if (r3 >= this.gridView.getChildCount()) goto L15;
        View r5 = this.gridView.getChildAt(r3);
        int r6 = this.gridView.getChildAdapterPosition(r5);
        if (r4 == (-1)) goto L12;
        if (r4 > r6) goto L12;
    L13:
        r3 = r3 + 1;
    L12:
        r2 = r5;
        r4 = r6;
        goto L13
    L15:
        if (r2 != null) goto L17;
    L19:
        runShadowAnimation(0, true);
    L20:
        runShadowAnimation(1, true);
        if (this.scrollOffsetY == r1) goto L27;
        setScrollOffsetY(r1);
        return;
    L27:
        return;
    L17:
        if (r2.getTop() < 0) goto L19;
        int r22 = r2.getTop();
        runShadowAnimation(0, false);
        r1 = r22;
        goto L20
    }

    private void updateSendButton() {
        Point r0 = AndroidUtilities.displaySize;
        int r1 = (int) ((Math.min(r0.x, r0.y) / 2) / AndroidUtilities.density);
        if (this.importingStickers == null) goto L7;
        this.previewSendButton.setText(LocaleController.getString(R.string.ImportStickersRemove));
        this.previewSendButton.setTextColor(getThemedColor(Theme.key_text_RedBold));
        float r2 = r1;
        this.stickerImageView.setLayoutParams(LayoutHelper.createFrame(r1, r2, 17, 0.0f, 0.0f, 0.0f, 30.0f));
        this.stickerEmojiTextView.setLayoutParams(LayoutHelper.createFrame(r1, r2, 17, 0.0f, 0.0f, 0.0f, 30.0f));
        this.previewSendButton.setVisibility(0);
        this.previewSendButtonShadow.setVisibility(0);
        return;
    L7:
        if (this.delegate == null) goto L14;
        TLRPC.TL_messages_stickerSet r02 = this.stickerSet;
        if (r02 != null) goto L11;
    L12:
        this.previewSendButton.setText(LocaleController.getString(R.string.SendSticker));
        float r22 = r1;
        this.stickerImageView.setLayoutParams(LayoutHelper.createFrame(r1, r22, 17, 0.0f, 0.0f, 0.0f, 30.0f));
        this.stickerEmojiTextView.setLayoutParams(LayoutHelper.createFrame(r1, r22, 17, 0.0f, 0.0f, 0.0f, 30.0f));
        this.previewSendButton.setVisibility(0);
        this.previewSendButtonShadow.setVisibility(0);
        return;
    L11:
        if (r02.set.masks == false) goto L12;
    L14:
        this.previewSendButton.setText(LocaleController.getString(R.string.Close));
        this.stickerImageView.setLayoutParams(LayoutHelper.createFrame(r1, r1, 17));
        this.stickerEmojiTextView.setLayoutParams(LayoutHelper.createFrame(r1, r1, 17));
        this.previewSendButton.setVisibility(8);
        this.previewSendButtonShadow.setVisibility(8);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        int r0 = 0;
        if (r3 != NotificationCenter.emojiLoaded) goto L10;
        RecyclerListView r32 = this.gridView;
        if (r32 == null) goto L46;
        int r33 = r32.getChildCount();
    L7:
        if (r0 >= r33) goto L52;
        this.gridView.getChildAt(r0).invalidate();
        r0 = r0 + 1;
        goto L7
    L52:
        return;
    L46:
        return;
    L10:
        if (r3 != NotificationCenter.fileUploaded) goto L19;
        HashMap<String, SendMessagesHelper.ImportingSticker> r34 = this.uploadImportStickers;
        if (r34 == null) goto L50;
        String r42 = (String) r5[0];
        SendMessagesHelper.ImportingSticker r35 = r34.get(r42);
        if (r35 == null) goto L47;
        r35.uploadMedia(this.currentAccount, (TLRPC.InputFile) r5[1], new StickersAlert$$ExternalSyntheticLambda10(this, r42, r35));
        return;
    L47:
        return;
    L50:
        return;
    L19:
        if (r3 != NotificationCenter.fileUploadFailed) goto L31;
        HashMap<String, SendMessagesHelper.ImportingSticker> r36 = this.uploadImportStickers;
        if (r36 == null) goto L51;
        SendMessagesHelper.ImportingSticker r37 = r36.remove((String) r5[0]);
        if (r37 == null) goto L27;
        removeSticker(r37);
    L27:
        if (this.uploadImportStickers.isEmpty() == false) goto L48;
        updateFields();
        return;
    L48:
        return;
    L51:
        return;
    L31:
        if (r3 != NotificationCenter.stickersDidLoad) goto L49;
        TLRPC.TL_messages_stickerSet r43 = null;
        if (this.inputStickerSet == null) goto L39;
        MediaDataController r38 = MediaDataController.getInstance(this.currentAccount);
        String r52 = this.inputStickerSet.short_name;
        if (r52 == null) goto L37;
        r43 = r38.getStickerSetByName(r52);
    L37:
        if (r43 != null) goto L39;
        r43 = r38.getStickerSetById(this.inputStickerSet.id);
    L39:
        if (r43 != null) goto L41;
    L43:
        updateFields();
        return;
    L41:
        if (r43 == this.stickerSet) goto L43;
        this.stickerSet = r43;
        loadStickerSet(false);
        goto L43
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void disableEditMode() {
        if (this.isEditModeEnabled == true) goto L5;
        return;
    L5:
        this.dragAndDropHelper.attachToRecyclerView(null);
        this.isEditModeEnabled = false;
        this.stickersShaker.stopShake(true);
        AndroidUtilities.forEachViews(this.gridView, new StickersAlert$$ExternalSyntheticLambda17());
        this.optionsButton.postDelayed(new StickersAlert$$ExternalSyntheticLambda18(this), 200);
        this.pickerBottomLayout.setText(LocaleController.getString(R.string.EditStickers), true);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        this.stickersShaker.stopShake(false);
        if (this.ignoreMasterDismiss == true) goto L7;
        Runnable r0 = this.masterDismissListener;
        if (r0 == null) goto L7;
        r0.run();
    L7:
        Runnable r02 = this.onDismissListener;
        if (r02 == null) goto L11;
        r02.run();
    L11:
        if (this.reqId == 0) goto L13;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
        this.reqId = 0;
    L13:
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        if (this.importingStickers == null) goto L27;
        ArrayList<SendMessagesHelper.ImportingSticker> r03 = this.importingStickersPaths;
        if (r03 == null) goto L26;
        int r04 = r03.size();
        int r2 = 0;
    L18:
        if (r2 >= r04) goto L26;
        SendMessagesHelper.ImportingSticker r3 = this.importingStickersPaths.get(r2);
        if (r3.validated == true) goto L23;
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(r3.path, false);
    L23:
        if (r3.animated == false) goto L25;
        new File(r3.path).delete();
    L25:
        r2 = r2 + 1;
    L26:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadFailed);
    L27:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.stickersDidLoad);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{4});
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void enableEditMode() {
        if (this.isEditModeEnabled == false) goto L5;
        return;
    L5:
        this.dragAndDropHelper.attachToRecyclerView(this.gridView);
        this.isEditModeEnabled = true;
        this.stickersShaker.startShake();
        AndroidUtilities.forEachViews(this.gridView, new StickersAlert$$ExternalSyntheticLambda30());
        this.optionsButton.postDelayed(new StickersAlert$$ExternalSyntheticLambda31(this), 200);
        this.pickerBottomLayout.setText(LocaleController.getString(R.string.Done), true);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        StickersAlert$$ExternalSyntheticLambda8 r8 = new StickersAlert$$ExternalSyntheticLambda8(this);
        ViewGroup r10 = this.containerView;
        Drawable[] r14 = {this.shadowDrawable};
        int r22 = Theme.key_dialogBackground;
        r1.add(new ThemeDescription(r10, 0, null, null, r14, null, r22));
        r1.add(new ThemeDescription(this.containerView, 0, null, null, null, null, Theme.key_sheet_scrollUp));
        this.adapter.getThemeDescriptions(r1, r8);
        View r102 = this.shadow[0];
        int r11 = ThemeDescription.FLAG_BACKGROUND;
        int r19 = Theme.key_dialogShadowLine;
        r1.add(new ThemeDescription(r102, r11, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.shadow[1], ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.gridView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_dialogScrollGlow));
        r1.add(new ThemeDescription(this.titleTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_dialogTextBlack));
        if (this.descriptionTextView == null) goto L5;
        r1.add(new ThemeDescription(this.descriptionTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_emojiPanelTrendingDescription));
    L5:
        r1.add(new ThemeDescription(this.titleTextView, ThemeDescription.FLAG_LINKCOLOR, null, null, null, null, Theme.key_dialogTextLink));
        r1.add(new ThemeDescription(this.optionsButton, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_player_actionBarSelector));
        r1.add(new ThemeDescription(this.pickerBottomLayout, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r22));
        AnimatedTextView r3 = this.pickerBottomLayout;
        int r25 = ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE;
        int r16 = Theme.key_listSelector;
        r1.add(new ThemeDescription(r3, r25, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.pickerBottomLayout, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, this.buttonTextColorKey));
        r1.add(new ThemeDescription(this.previewSendButton, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_dialogTextBlue2));
        r1.add(new ThemeDescription(this.previewSendButton, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r22));
        r1.add(new ThemeDescription(this.previewSendButton, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.previewSendButtonShadow, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r19));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_dialogLinkSelection));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, r22));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_sheet_other));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_actionBarDefaultSubmenuItem));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_actionBarDefaultSubmenuItemIcon));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_dialogButtonSelector));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_actionBarDefaultSubmenuBackground));
        return r1;
    }

    public void loadStickerSet(boolean r4) {
        if (this.inputStickerSet == null) goto L24;
        MediaDataController r0 = MediaDataController.getInstance(this.currentAccount);
        if (r4 == true) goto L15;
        if (this.stickerSet != null) goto L12;
        String r42 = this.inputStickerSet.short_name;
        if (r42 == null) goto L12;
        this.stickerSet = r0.getStickerSetByName(r42);
    L12:
        if (this.stickerSet != null) goto L15;
        this.stickerSet = r0.getStickerSetById(this.inputStickerSet.id);
    L15:
        if (this.stickerSet != null) goto L18;
        TLRPC.TL_messages_getStickerSet r43 = new TLRPC.TL_messages_getStickerSet();
        r43.stickerset = this.inputStickerSet;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r43, new StickersAlert$$ExternalSyntheticLambda19(this, r0));
        goto L24
    L18:
        if (this.adapter == null) goto L22;
        updateSendButton();
        updateFields();
        this.adapter.notifyDataSetChanged();
        if (this.refreshingFromMenu == false) goto L22;
        scheduleRefreshTransition();
        this.refreshingFromMenu = false;
    L22:
        updateDescription();
        r0.preloadStickerSetThumb(this.stickerSet);
        checkPremiumStickers();
    L24:
        if (this.stickerSet == null) goto L26;
        this.showEmoji = !r4.set.masks;
    L26:
        checkPremiumStickers();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        if (ContentPreviewViewer.getInstance().isVisible() == false) goto L6;
        ContentPreviewViewer.getInstance().closeWithMenu();
        return;
    L6:
        super.onBackPressed();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onStart() {
        super.onStart();
        Bulletin.addDelegate((FrameLayout) this.containerView, new AnonymousClass19(this));
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        Bulletin.removeDelegate((FrameLayout) this.containerView);
    }

    public void setClearsInputField(boolean r1) {
        this.clearsInputField = r1;
    }

    public void setCustomButtonDelegate(StickersAlertCustomButtonDelegate r1) {
        this.customButtonDelegate = r1;
        updateFields();
    }

    public void setInstallDelegate(StickersAlertInstallDelegate r1) {
        this.installDelegate = r1;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void setOnDismissListener(Runnable r1) {
        this.onDismissListener = r1;
    }

    public void setShowTooltipWhenToggle(boolean r1) {
        this.showTooltipWhenToggle = r1;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{4});
    }

    public void updateColors() {
        updateColors(false);
    }

    public void updateColors(boolean r5) {
        this.adapter.updateColors();
        this.titleTextView.setHighlightColor(getThemedColor(Theme.key_dialogLinkSelection));
        this.stickerPreviewLayout.setBackgroundColor(getThemedColor(Theme.key_dialogBackground) & (-536870913));
        this.optionsButton.setIconColor(getThemedColor(Theme.key_sheet_other));
        this.optionsButton.setPopupItemsColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem), false);
        this.optionsButton.setPopupItemsColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), true);
        this.optionsButton.setPopupItemsSelectorColor(getThemedColor(Theme.key_dialogButtonSelector));
        this.optionsButton.redrawPopup(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
        if (this.deleteItem == null) goto L7;
        int r0 = getThemedColor(Theme.key_text_RedBold);
        this.deleteItem.setColors(r0, r0);
        this.deleteItem.setSelectorColor(Theme.multAlpha(r0, 0.1f));
        if (this.deleteItem.getRightIcon() == null) goto L7;
        this.deleteItem.getRightIcon().setColorFilter(r0);
    L7:
        if (r5 == false) goto L19;
        if (Theme.isAnimatingColor() == true) goto L11;
    L15:
        int r52 = this.animatingDescriptions.size();
        int r02 = 0;
    L16:
        if (r02 >= r52) goto L19;
        ThemeDescription r1 = this.animatingDescriptions.get(r02);
        r1.setColor(getThemedColor(r1.getCurrentKey()), false, false);
        r02 = r02 + 1;
        goto L16
    L11:
        if (this.animatingDescriptions != null) goto L15;
        ArrayList<ThemeDescription> r53 = getThemeDescriptions();
        this.animatingDescriptions = r53;
        int r54 = r53.size();
        int r03 = 0;
    L13:
        if (r03 >= r54) goto L15;
        this.animatingDescriptions.get(r03).setDelegateDisabled();
        r03 = r03 + 1;
    L19:
        if (Theme.isAnimatingColor() == false) goto L21;
        return;
    L21:
        if (this.animatingDescriptions == null) goto L27;
        this.animatingDescriptions = null;
        return;
    }

    public void updateStickerSet(TLRPC.TL_messages_stickerSet r2) {
        this.stickerSet = r2;
        if (this.adapter == null) goto L5;
        updateSendButton();
        updateFields();
        this.adapter.notifyDataSetChanged();
    L5:
        updateDescription();
        MediaDataController.getInstance(this.currentAccount).preloadStickerSetThumb(this.stickerSet);
        checkPremiumStickers();
    }
}
