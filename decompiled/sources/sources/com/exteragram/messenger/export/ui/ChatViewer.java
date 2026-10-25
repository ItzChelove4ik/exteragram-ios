package com.exteragram.messenger.export.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.collection.LongSparseArray;
import androidx.core.content.FileProvider;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.components.MessageDetailsPopupWrapper;
import com.exteragram.messenger.export.output.FileManager;
import com.exteragram.messenger.export.ui.ExportMapper;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.google.android.gms.cast.MediaError;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatMessageSharedResources;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.tlutils.TLKeyboardHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ArticleViewer;
import org.telegram.ui.AspectRatioFrameLayout;
import org.telegram.ui.AvatarPreviewer;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatLoadingCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.ChatUnreadCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.ChatScrimPopupContainerLayout;
import org.telegram.ui.Components.EmbedBottomSheet;
import org.telegram.ui.Components.EmojiPacksAlert;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PhonebookShareAlert;
import org.telegram.ui.Components.PipRoundVideoView;
import org.telegram.ui.Components.PopupSwipeBackLayout;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.StickersAlert;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.Components.URLSpanUserMention;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.ContactAddActivity;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LanguageSelectActivity;
import org.telegram.ui.LocationActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.ThemePreviewActivity;
import org.telegram.ui.recyclerview.ChatListItemAnimator;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;

/* loaded from: classes4.dex */
public class ChatViewer extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private AspectRatioFrameLayout aspectRatioFrameLayout;
    private ChatAvatarContainer avatarContainer;
    private ChatActivityAdapter chatAdapter;
    private final ExportMapper.ChatInfo chatInfo;
    private LinearLayoutManager chatLayoutManager;
    private ChatListItemAnimator chatListItemAnimator;
    private RecyclerListView chatListView;
    private final ArrayList<ChatMessageCell> chatMessageCellsCache;
    private boolean checkTextureViewPosition;
    private SizeNotifierFrameLayout contentView;
    protected TLRPC.Chat currentChat;
    private boolean currentFloatingDateOnScreen;
    private boolean currentFloatingTopIsNotMessage;
    private TextView emptyView;
    private FrameLayout emptyViewContainer;
    private boolean endReached;
    private AnimatorSet floatingDateAnimation;
    private ChatActionCell floatingDateView;
    private AtomicInteger lastLoadedMsgFileId;
    private boolean loading;
    protected ArrayList<MessageObject> messages;
    private final HashMap<String, ArrayList<MessageObject>> messagesByDays;
    private final LongSparseArray<MessageObject> messagesDict;
    private long minEventId;
    private final AnimationNotificationsLocker notificationsLocker;
    private final String path;
    private boolean paused;
    private RadialProgressView progressBar;
    private FrameLayout progressView;
    private View progressView2;
    private final PhotoViewer.PhotoViewerProvider provider;
    private FrameLayout roundVideoContainer;
    private ActionBarPopupWindow scrimPopupWindow;
    private int scrollToOffsetOnRecreate;
    private int scrollToPositionOnRecreate;
    private boolean scrollingFloatingDate;
    private final String searchQuery;
    private boolean searchWas;
    private MessageObject selectedObject;
    public ChatMessageSharedResources sharedResources;
    private final ChatActivity.ThemeDelegate theme;
    private UndoView undoView;
    private TextureView videoTextureView;
    private boolean wasPaused;

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$1, reason: invalid class name */
    public class AnonymousClass1 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass1(ChatViewer r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r11, TLRPC.FileLocation r12, int r13, boolean r14, boolean r15) {
            int r132 = ChatViewer.m1370$$Nest$fgetchatListView(this.this$0).getChildCount();
            int r152 = 0;
        L3:
            ImageReceiver r0 = null;
            if (r152 >= r132) goto L38;
            View r1 = ChatViewer.m1370$$Nest$fgetchatListView(this.this$0).getChildAt(r152);
            if ((r1 instanceof ChatMessageCell) == false) goto L15;
            ChatMessageCell r2 = (ChatMessageCell) r1;
            if (r11 == null) goto L34;
            MessageObject r3 = r2.getMessageObject();
            if (r3 == null) goto L34;
            if (r3.getId() != r11.getId()) goto L34;
            r0 = r2.getPhotoImage();
        L34:
            if (r0 != null) goto L35;
            r152 = r152 + 1;
            goto L3
        L35:
            int[] r112 = new int[2];
            r1.getLocationInWindow(r112);
            PhotoViewer.PlaceProviderObject r122 = new PhotoViewer.PlaceProviderObject();
            r122.viewX = r112[0];
            r122.viewY = r112[1];
            r122.parentView = ChatViewer.m1370$$Nest$fgetchatListView(this.this$0);
            r122.imageReceiver = r0;
            r122.thumb = r0.getBitmapSafe();
            r122.radius = r0.getRoundRadius();
            r122.isEvent = true;
            return r122;
        L15:
            if ((r1 instanceof ChatActionCell) == false) goto L34;
            ChatActionCell r22 = (ChatActionCell) r1;
            MessageObject r32 = r22.getMessageObject();
            if (r32 == null) goto L34;
            if (r11 != null) goto L20;
            if (r12 == null) goto L34;
            if (r32.photoThumbs == null) goto L34;
            int r4 = 0;
        L27:
            if (r4 >= r32.photoThumbs.size()) goto L34;
            TLRPC.FileLocation r5 = r32.photoThumbs.get(r4).location;
            if (r5.volume_id != r12.volume_id) goto L33;
            if (r5.local_id != r12.local_id) goto L33;
            r0 = r22.getPhotoImage();
        L33:
            r4 = r4 + 1;
            goto L27
        L20:
            if (r32.getId() != r11.getId()) goto L34;
            r0 = r22.getPhotoImage();
            goto L34
        L38:
            return null;
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$10, reason: invalid class name */
    public class AnonymousClass10 extends ChatScrimPopupContainerLayout {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass10(ChatViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r3) {
            if (r3.getKeyCode() != 4) goto L8;
            if (r3.getRepeatCount() != 0) goto L8;
            ChatViewer.m1394$$Nest$mcloseMenu(this.this$0);
        L8:
            return super.dispatchKeyEvent(r3);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            boolean r0 = super.dispatchTouchEvent(r2);
            if (r2.getAction() != 0) goto L6;
            if (r0 == true) goto L6;
            ChatViewer.m1394$$Nest$mcloseMenu(this.this$0);
        L6:
            return r0;
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$11, reason: invalid class name */
    public class AnonymousClass11 extends ActionBarPopupWindow {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass11(ChatViewer r1, View r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
        public void dismiss() {
            super.dismiss();
            if (ChatViewer.m1381$$Nest$fgetscrimPopupWindow(this.this$0) == this) goto L5;
            return;
        L5:
            Bulletin.hideVisible();
            ChatViewer.m1388$$Nest$fputscrimPopupWindow(this.this$0, null);
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$12, reason: invalid class name */
    public class AnonymousClass12 extends FrameLayout {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass12(ChatViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            ChatViewer.m1372$$Nest$fgetcontentView(this.this$0).invalidate();
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$13, reason: invalid class name */
    public class AnonymousClass13 extends ViewOutlineProvider {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass13(ChatViewer r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r1, Outline r2) {
            int r12 = AndroidUtilities.roundMessageSize;
            r2.setOval(0, 0, r12, r12);
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$14, reason: invalid class name */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass14(ChatViewer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(ChatViewer.m1376$$Nest$fgetfloatingDateAnimation(this.this$0)) == false) goto L6;
            ChatViewer.m1387$$Nest$fputfloatingDateAnimation(this.this$0, null);
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$15, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass15 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$ui$AvatarPreviewer$MenuItem = null;

        static {
            int[] r0 = new int[AvatarPreviewer.MenuItem.values().length];
            $SwitchMap$org$telegram$ui$AvatarPreviewer$MenuItem = r0;
            r0[AvatarPreviewer.MenuItem.SEND_MESSAGE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L8:
            $SwitchMap$org$telegram$ui$AvatarPreviewer$MenuItem[AvatarPreviewer.MenuItem.OPEN_PROFILE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L7
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass2(ChatViewer r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$3, reason: invalid class name */
    public class AnonymousClass3 extends ChatAvatarContainer {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass3(ChatViewer r1, Context r2, BaseFragment r3, boolean r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.ChatAvatarContainer
        public boolean canSearch() {
            return TextUtils.isEmpty(_UrlKt.FRAGMENT_ENCODE_SET);
        }

        @Override // org.telegram.ui.Components.ChatAvatarContainer
        public void openSearch() {
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$4, reason: invalid class name */
    public class AnonymousClass4 extends SizeNotifierFrameLayout {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass4(ChatViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (AvatarPreviewer.hasVisibleInstance() == false) goto L7;
            AvatarPreviewer.getInstance().onTouchEvent(r2);
            return true;
        L7:
            return super.dispatchTouchEvent(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            boolean r32 = super.drawChild(r1, r2, r3);
            if (r2 == ChatViewer.access$000(this.this$0)) goto L5;
        L11:
            return r32;
        L5:
            if (ChatViewer.access$100(this.this$0) == null) goto L11;
            INavigationLayout r22 = ChatViewer.access$400(this.this$0);
            if (ChatViewer.access$200(this.this$0).getVisibility() != 0) goto L9;
            int r0 = ChatViewer.access$300(this.this$0).getMeasuredHeight();
        L10:
            r22.drawHeaderShadow(r1, r0);
            goto L11
        L9:
            r0 = 0;
            goto L10
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public Drawable getNewDrawable() {
            Drawable r0 = Theme.getCachedWallpaper();
            if (r0 == null) goto L6;
            return r0;
        L6:
            return super.getNewDrawable();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public Theme.ResourcesProvider getResourceProvider() {
            return ChatViewer.m1384$$Nest$fgettheme(this.this$0);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isActionBarVisible() {
            if (ChatViewer.access$500(this.this$0).getVisibility() != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isRoundVideo() == true) goto L7;
            return;
        L7:
            if (r0.eventId != 0) goto L9;
            return;
        L9:
            if (r0.getDialogId() != ChatViewer.m1397$$Nest$mgetDialogId(this.this$0)) goto L15;
            MediaController.getInstance().setTextureView(ChatViewer.m1396$$Nest$mcreateTextureView(this.this$0, false), ChatViewer.m1368$$Nest$fgetaspectRatioFrameLayout(this.this$0), ChatViewer.m1380$$Nest$fgetroundVideoContainer(this.this$0), true);
            return;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
            int r102 = getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r102) goto L49;
            View r2 = getChildAt(r1);
            if (r2.getVisibility() == 8) goto L48;
            FrameLayout.LayoutParams r3 = (FrameLayout.LayoutParams) r2.getLayoutParams();
            int r4 = r2.getMeasuredWidth();
            int r5 = r2.getMeasuredHeight();
            int r6 = r3.gravity;
            if (r6 != (-1)) goto L10;
            r6 = 51;
        L10:
            int r7 = r6 & 112;
            int r62 = r6 & 7;
            if (r62 != 1) goto L13;
            int r63 = (((r13 - r11) - r4) / 2) + r3.leftMargin;
            int r8 = r3.rightMargin;
        L16:
            int r64 = r63 - r8;
        L19:
            if (r7 != 16) goto L21;
            int r72 = (((r14 - r12) - r5) / 2) + r3.topMargin;
            int r32 = r3.bottomMargin;
        L26:
            int r33 = r72 - r32;
        L34:
            if (r2 != ChatViewer.m1374$$Nest$fgetemptyViewContainer(this.this$0)) goto L42;
            int r73 = AndroidUtilities.dp(24.0f);
            if (ChatViewer.access$1300(this.this$0).getVisibility() != 0) goto L38;
            int r82 = ChatViewer.access$1400(this.this$0).getMeasuredHeight() / 2;
        L39:
            int r74 = r73 - r82;
        L40:
            r33 = r33 - r74;
        L47:
            r2.layout(r64, r33, r4 + r64, r5 + r33);
            goto L48
        L38:
            r82 = 0;
            goto L39
        L42:
            if (r2 != ChatViewer.access$1500(this.this$0)) goto L45;
            r74 = getPaddingTop();
            goto L40
        L45:
            if (r2 != this.backgroundView) goto L47;
            r33 = 0;
            goto L47
        L21:
            if (r7 != 48) goto L23;
            r33 = r3.topMargin + getPaddingTop();
            if (r2 == ChatViewer.access$1000(this.this$0)) goto L34;
            if (ChatViewer.access$1100(this.this$0).getVisibility() != 0) goto L34;
            r33 = r33 + ChatViewer.access$1200(this.this$0).getMeasuredHeight();
            goto L34
        L23:
            if (r7 == 80) goto L25;
            r33 = r3.topMargin;
            goto L34
        L25:
            r72 = (r14 - r12) - r5;
            r32 = r3.bottomMargin;
            goto L26
        L13:
            if (r62 == 5) goto L15;
            r64 = r3.leftMargin;
            goto L19
        L15:
            r63 = r13 - r4;
            r8 = r3.rightMargin;
        L48:
            r1 = r1 + 1;
            goto L3
        L49:
            ChatViewer.m1400$$Nest$mupdateMessagesVisiblePart(this.this$0);
            notifyHeightChanged();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r11, int r12) {
            int r6 = View.MeasureSpec.getSize(r11);
            int r1 = View.MeasureSpec.getSize(r12);
            setMeasuredDimension(r6, r1);
            int r7 = r1 - getPaddingTop();
            measureChildWithMargins(ChatViewer.access$600(this.this$0), r11, 0, r12, 0);
            int r13 = ChatViewer.access$700(this.this$0).getMeasuredHeight();
            if (ChatViewer.access$800(this.this$0).getVisibility() != 0) goto L5;
            r7 = r7 - r13;
        L5:
            int r8 = getChildCount();
            int r9 = 0;
        L6:
            if (r9 >= r8) goto L25;
            View r14 = getChildAt(r9);
            if (r14 == null) goto L24;
            if (r14.getVisibility() == 8) goto L24;
            if (r14 == ChatViewer.access$900(this.this$0)) goto L24;
            if (r14 != ChatViewer.m1370$$Nest$fgetchatListView(this.this$0)) goto L17;
        L23:
            r14.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(Math.max(AndroidUtilities.dp(10.0f), r7), TLObject.FLAG_30));
            goto L24
        L17:
            if (r14 == ChatViewer.m1378$$Nest$fgetprogressView(this.this$0)) goto L23;
            if (r14 != ChatViewer.m1374$$Nest$fgetemptyViewContainer(this.this$0)) goto L22;
            r14.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r7, TLObject.FLAG_30));
            goto L24
        L22:
            measureChildWithMargins(r14, r11, 0, r12, 0);
        L24:
            r9 = r9 + 1;
            goto L6
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerListView {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass5(ChatViewer r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r10, View r11, long r12) {
            boolean r122 = super.drawChild(r10, r11, r12);
            if ((r11 instanceof ChatMessageCell) == false) goto L56;
            ChatMessageCell r13 = (ChatMessageCell) r11;
            ImageReceiver r0 = r13.getAvatarImage();
            if (r0 == null) goto L56;
            if (r13.getMessageObject().deleted == false) goto L10;
            r0.setVisible(false, false);
            return r122;
        L10:
            int r1 = (int) r11.getY();
            if (r13.drawPinnedBottom() == false) goto L18;
            int r3 = ChatViewer.m1370$$Nest$fgetchatListView(this.this$0).getChildViewHolder(r11).getAdapterPosition();
            if (r3 < 0) goto L18;
            if (ChatViewer.m1370$$Nest$fgetchatListView(this.this$0).findViewHolderForAdapterPosition(r3 + 1) == null) goto L18;
            r0.setVisible(false, false);
            return r122;
        L18:
            float r32 = r13.getSlidingOffsetX() + r13.getCheckBoxTranslation();
            int r5 = ((int) r11.getY()) + r13.getLayoutHeight();
            int r6 = ChatViewer.m1370$$Nest$fgetchatListView(this.this$0).getMeasuredHeight() - ChatViewer.m1370$$Nest$fgetchatListView(this.this$0).getPaddingBottom();
            if (r5 <= r6) goto L22;
            r5 = r6;
        L22:
            if (r13.drawPinnedTop() == false) goto L36;
            int r112 = ChatViewer.m1370$$Nest$fgetchatListView(this.this$0).getChildViewHolder(r11).getAdapterPosition();
            if (r112 < 0) goto L36;
            int r62 = 0;
        L27:
            if (r62 >= 20) goto L36;
            r62 = r62 + 1;
            r112 = r112 - 1;
            RecyclerView.ViewHolder r7 = ChatViewer.m1370$$Nest$fgetchatListView(this.this$0).findViewHolderForAdapterPosition(r112);
            if (r7 == null) goto L36;
            r1 = r7.itemView.getTop();
            View r72 = r7.itemView;
            if ((r72 instanceof ChatMessageCell) == false) goto L36;
            r13 = (ChatMessageCell) r72;
            if (r13.drawPinnedTop() == true) goto L27;
        L36:
            if ((r5 - AndroidUtilities.dp(48.0f)) >= r1) goto L39;
            r5 = r1 + AndroidUtilities.dp(48.0f);
        L39:
            if (r13.drawPinnedBottom() == true) goto L43;
            int r9 = (int) (r13.getY() + r13.getMeasuredHeight());
            if (r5 <= r9) goto L43;
            r5 = r9;
        L43:
            r10.save();
            if (r32 == 0.0f) goto L47;
            r10.translate(r32, 0.0f);
        L47:
            if (r13.getCurrentMessagesGroup() != null) goto L49;
        L51:
            r0.setImageY(r5 - AndroidUtilities.dp(44.0f));
            if (r13.shouldDrawAlphaLayer() == false) goto L54;
            r0.setAlpha(r13.getAlpha());
            r10.scale(r13.getScaleX(), r13.getScaleY(), r13.getX() + r13.getPivotX(), r13.getY() + (r13.getHeight() >> 1));
        L55:
            r0.setVisible(true, false);
            r0.draw(r10);
            r10.restore();
            goto L56
        L54:
            r0.setAlpha(1.0f);
            goto L55
        L49:
            if (r13.getCurrentMessagesGroup().transitionParams.backgroundChangeBounds == false) goto L51;
            r5 = (int) (r5 - r13.getTranslationY());
        L56:
            return r122;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.View
        public void setTranslationY(float r2) {
            if (r2 == getTranslationY()) goto L6;
            super.setTranslationY(r2);
            ChatViewer.m1400$$Nest$mupdateMessagesVisiblePart(this.this$0);
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$6, reason: invalid class name */
    public class AnonymousClass6 extends ChatListItemAnimator {
        Runnable finishRunnable;
        int scrollAnimationIndex;
        final /* synthetic */ ChatViewer this$0;

        /* renamed from: $r8$lambda$YYNG54naqKKwJHwxNv-F__MEEfY, reason: not valid java name */
        public static /* synthetic */ void m1401$r8$lambda$YYNG54naqKKwJHwxNvF__MEEfY(AnonymousClass6 r0) {
            r0.lambda$onAllAnimationsDone$0();
        }

        public AnonymousClass6(ChatViewer r1, ChatActivity r2, RecyclerListView r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.scrollAnimationIndex = -1;
        }

        private /* synthetic */ void lambda$onAllAnimationsDone$0() {
            if (this.scrollAnimationIndex == (-1)) goto L6;
            this.this$0.getNotificationCenter().onAnimationFinish(this.scrollAnimationIndex);
            this.scrollAnimationIndex = -1;
        L6:
            if (BuildVars.LOGS_ENABLED == false) goto L8;
            FileLog.d("admin logs chatItemAnimator enable notifications");
        L8:
            ChatViewer.m1400$$Nest$mupdateMessagesVisiblePart(this.this$0);
        }

        @Override // org.telegram.ui.recyclerview.ChatListItemAnimator, androidx.recyclerview.widget.DefaultItemAnimator
        public void onAllAnimationsDone() {
            super.onAllAnimationsDone();
            Runnable r0 = this.finishRunnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
        L5:
            ChatViewer$6$$ExternalSyntheticLambda0 r02 = new ChatViewer$6$$ExternalSyntheticLambda0(this);
            this.finishRunnable = r02;
            AndroidUtilities.runOnUIThread(r02);
        }

        @Override // org.telegram.ui.recyclerview.ChatListItemAnimator
        public void onAnimationStart() {
            if (this.scrollAnimationIndex != (-1)) goto L5;
            this.scrollAnimationIndex = this.this$0.getNotificationCenter().setAnimationInProgress(this.scrollAnimationIndex, null, false);
        L5:
            Runnable r0 = this.finishRunnable;
            if (r0 == null) goto L9;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.finishRunnable = null;
        L9:
            if (BuildVars.LOGS_ENABLED == false) goto L11;
            FileLog.d("admin logs chatItemAnimator disable notifications");
        L11:
            ChatViewer.m1400$$Nest$mupdateMessagesVisiblePart(this.this$0);
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$7, reason: invalid class name */
    public class AnonymousClass7 extends LinearLayoutManager {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass7(ChatViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r2, RecyclerView.State r3, int r4) {
            LinearSmoothScrollerCustom r32 = new LinearSmoothScrollerCustom(r2.getContext(), 0);
            r32.setTargetPosition(r4);
            startSmoothScroll(r32);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return true;
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$8, reason: invalid class name */
    public class AnonymousClass8 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatViewer this$0;

        /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$8$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass8 this$1;

            public AnonymousClass1(AnonymousClass8 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (r2.equals(ChatViewer.m1376$$Nest$fgetfloatingDateAnimation(this.this$1.this$0)) == false) goto L6;
                ChatViewer.m1387$$Nest$fputfloatingDateAnimation(this.this$1.this$0, null);
                return;
            }
        }

        public AnonymousClass8(ChatViewer r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            if (r3 != 1) goto L6;
            ChatViewer.m1391$$Nest$fputscrollingFloatingDate(this.this$0, true);
            ChatViewer.m1386$$Nest$fputcheckTextureViewPosition(this.this$0, true);
            return;
        L6:
            if (r3 != 0) goto L9;
            ChatViewer.m1391$$Nest$fputscrollingFloatingDate(this.this$0, false);
            ChatViewer.m1386$$Nest$fputcheckTextureViewPosition(this.this$0, false);
            ChatViewer.m1398$$Nest$mhideFloatingDateView(this.this$0, true);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r4, int r5, int r6) {
            ChatViewer.m1370$$Nest$fgetchatListView(this.this$0).invalidate();
            if (r6 != 0) goto L5;
        L14:
            ChatViewer.m1393$$Nest$mcheckScrollForLoad(this.this$0, true);
            ChatViewer.m1400$$Nest$mupdateMessagesVisiblePart(this.this$0);
            return;
        L5:
            if (ChatViewer.m1383$$Nest$fgetscrollingFloatingDate(this.this$0) == false) goto L14;
            if (ChatViewer.m1373$$Nest$fgetcurrentFloatingTopIsNotMessage(this.this$0) == true) goto L14;
            if (ChatViewer.m1377$$Nest$fgetfloatingDateView(this.this$0).getTag() != null) goto L14;
            if (ChatViewer.m1376$$Nest$fgetfloatingDateAnimation(this.this$0) == null) goto L13;
            ChatViewer.m1376$$Nest$fgetfloatingDateAnimation(this.this$0).cancel();
        L13:
            ChatViewer.m1377$$Nest$fgetfloatingDateView(this.this$0).setTag(1);
            ChatViewer.m1387$$Nest$fputfloatingDateAnimation(this.this$0, new AnimatorSet());
            ChatViewer.m1376$$Nest$fgetfloatingDateAnimation(this.this$0).setDuration(150);
            ChatViewer.m1376$$Nest$fgetfloatingDateAnimation(this.this$0).playTogether(new Animator[]{ObjectAnimator.ofFloat(ChatViewer.m1377$$Nest$fgetfloatingDateView(this.this$0), "alpha", new float[]{1.0f})});
            ChatViewer.m1376$$Nest$fgetfloatingDateAnimation(this.this$0).addListener(new AnonymousClass1(this));
            ChatViewer.m1376$$Nest$fgetfloatingDateAnimation(this.this$0).start();
            goto L14
        }
    }

    /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$9, reason: invalid class name */
    public class AnonymousClass9 extends MessageDetailsPopupWrapper {
        final /* synthetic */ ChatViewer this$0;

        public AnonymousClass9(ChatViewer r1, BaseFragment r2, PopupSwipeBackLayout r3, MessageObject r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // com.exteragram.messenger.components.MessageDetailsPopupWrapper
        public void copy(String r1) {
            if (AndroidUtilities.addToClipboard(r1) == false) goto L6;
            BulletinFactory.of(this.this$0).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
            return;
        }
    }

    public class ChatActivityAdapter extends RecyclerView.Adapter {
        private int loadingUpRow;
        private final Context mContext;
        private int messagesEndRow;
        private int messagesStartRow;
        private int rowCount;
        final /* synthetic */ ChatViewer this$0;

        /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$ChatActivityAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements ChatMessageCell.ChatMessageCellDelegate {
            final /* synthetic */ ChatActivityAdapter this$1;

            public static /* synthetic */ void $r8$lambda$Y9dL4DwOfXRYLyW6llZwknbYQUw(AnonymousClass1 r0, String r1, DialogInterface r2, int r3) {
                r0.lambda$didPressUrl$1(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$YIwtadjIDb6ZG1A6bvNzTlwcUek(AnonymousClass1 r0, String r1, DialogInterface r2, int r3) {
                r0.lambda$didLongPressCopyButton$3(r1, r2, r3);
            }

            /* renamed from: $r8$lambda$YrGL4FJv0PMQEsOjCRGnbBlg-T4, reason: not valid java name */
            public static /* synthetic */ void m1404$r8$lambda$YrGL4FJv0PMQEsOjCRGnbBlgT4(AnonymousClass1 r0, String r1, TL_keyboard.TL_inlineButtonTypeCallback r2, String r3, TL_keyboard.TL_inlineButtonTypeSwitchInline r4, TL_keyboard.TL_inlineButtonTypeUserProfile r5, DialogInterface r6, int r7) {
                r0.lambda$didLongPressBotButton$2(r1, r2, r3, r4, r5, r6, r7);
            }

            public static /* synthetic */ void $r8$lambda$vztG8_418NVINPYO0rg_2cIfWe4(AnonymousClass1 r0, ChatMessageCell r1, TLRPC.User r2, AvatarPreviewer.MenuItem r3) {
                r0.lambda$didLongPressUserAvatar$0(r1, r2, r3);
            }

            public AnonymousClass1(ChatActivityAdapter r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$didLongPressBotButton$2(String r1, TL_keyboard.TL_inlineButtonTypeCallback r2, String r3, TL_keyboard.TL_inlineButtonTypeSwitchInline r4, TL_keyboard.TL_inlineButtonTypeUserProfile r5, DialogInterface r6, int r7) {
                if (r7 != 0) goto L5;
                AndroidUtilities.addToClipboard(r1);
            L17:
                if (ChatViewer.m1385$$Nest$fgetundoView(this.this$1.this$0) == null) goto L20;
                ChatViewer.m1385$$Nest$fgetundoView(this.this$1.this$0).showWithAction(0, 58, null);
                return;
            L20:
                return;
            L5:
                if (r7 != 1) goto L8;
                AndroidUtilities.addToClipboard(ChatUtils.getInstance().getTextFromCallback(r2.data));
                goto L17
            L8:
                if (r7 != 2) goto L11;
                AndroidUtilities.addToClipboard(r3);
                goto L17
            L11:
                if (r7 != 3) goto L14;
                AndroidUtilities.addToClipboard(r4.query);
                goto L17
            L14:
                if (r7 != 4) goto L17;
                AndroidUtilities.addToClipboard(String.valueOf(r5.user_id));
                goto L17
            }

            private /* synthetic */ void lambda$didLongPressCopyButton$3(String r1, DialogInterface r2, int r3) {
                AndroidUtilities.addToClipboard(r1);
                BulletinFactory.of(this.this$1.this$0).createCopyBulletin(LocaleController.formatString(R.string.ExactTextCopied, new Object[]{r1})).show();
            }

            private /* synthetic */ void lambda$didLongPressUserAvatar$0(ChatMessageCell r2, TLRPC.User r3, AvatarPreviewer.MenuItem r4) {
                int r42 = AnonymousClass15.$SwitchMap$org$telegram$ui$AvatarPreviewer$MenuItem[r4.ordinal()];
                if (r42 != 1) goto L5;
                openDialog(r2, r3);
                return;
            L5:
                if (r42 == 2) goto L7;
                return;
            L7:
                openProfile(r3);
            }

            private /* synthetic */ void lambda$didPressUrl$1(String r1, DialogInterface r2, int r3) {
                if (r3 != 0) goto L6;
                Browser.openUrl(this.this$1.this$0.getParentActivity(), r1, true);
                return;
            L6:
                if (r3 == 1) goto L8;
                return;
            L8:
                if (r1.startsWith("mailto:") == false) goto L11;
                r1 = r1.substring(7);
            L13:
                AndroidUtilities.addToClipboard(r1);
                return;
            L11:
                if (r1.startsWith("tel:") == false) goto L13;
                r1 = r1.substring(4);
                goto L13
            }

            private void openDialog(ChatMessageCell r4, TLRPC.User r5) {
                if (r5 == null) goto L7;
                Bundle r42 = new Bundle();
                r42.putLong("user_id", r5.id);
                if (this.this$1.this$0.getMessagesController().checkCanOpenChat(r42, this.this$1.this$0) == false) goto L8;
                this.this$1.this$0.presentFragment(new ChatActivity(r42));
                return;
            L8:
                return;
            }

            private void openProfile(TLRPC.User r5) {
                Bundle r0 = new Bundle();
                r0.putLong("user_id", r5.id);
                ProfileActivity r52 = new ProfileActivity(r0);
                r52.setPlayProfileAnimation(0);
                this.this$1.this$0.presentFragment(r52);
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean canPerformActions() {
                return true;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean canPerformReply() {
                return false;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didLongPress(ChatMessageCell r1, float r2, float r3) {
                ChatViewer.m1395$$Nest$mcreateMenu(this.this$1.this$0, r1);
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didLongPressBotButton(ChatMessageCell r18, TL_keyboard.KeyboardButtonProto r19) {
                TL_keyboard.TL_inlineButtonTypeCopy r2 = (TL_keyboard.TL_inlineButtonTypeCopy) TLKeyboardHelper.getType(r19, TL_keyboard.TL_inlineButtonTypeCopy.class);
                TL_keyboard.TL_inlineButtonTypeCallback r3 = (TL_keyboard.TL_inlineButtonTypeCallback) TLKeyboardHelper.getType(r19, TL_keyboard.TL_inlineButtonTypeCallback.class);
                TL_keyboard.TL_inlineButtonTypeSwitchInline r5 = (TL_keyboard.TL_inlineButtonTypeSwitchInline) TLKeyboardHelper.getType(r19, TL_keyboard.TL_inlineButtonTypeSwitchInline.class);
                TL_keyboard.TL_inlineButtonTypeUserProfile r6 = (TL_keyboard.TL_inlineButtonTypeUserProfile) TLKeyboardHelper.getType(r19, TL_keyboard.TL_inlineButtonTypeUserProfile.class);
                if (r2 != null) goto L18;
                if (r3 != null) goto L18;
                if (r5 != null) goto L18;
                if (r6 != null) goto L18;
                if (TLKeyboardHelper.isType(r19, TL_keyboard.TL_inlineButtonTypeUrl.class) == true) goto L18;
                if (TLKeyboardHelper.isType(r19, TL_keyboard.TL_inlineButtonTypeGame.class) == true) goto L18;
                if (TLKeyboardHelper.isType(r19, TL_keyboard.TL_inlineButtonTypeBuy.class) == true) goto L18;
                if (TLKeyboardHelper.isType(r19, TL_keyboard.TL_inlineButtonTypeUrlAuth.class) == true) goto L18;
                if (TLKeyboardHelper.isType(r19, TL_keyboard.TL_inlineButtonTypeWebView.class) == true) goto L18;
                return;
            L18:
                if (r2 == null) goto L21;
                didLongPressCopyButton(r2.copy_text);
                return;
            L21:
                String r22 = r19.getText();
                String r4 = r19.getUrl();
                BottomSheet.Builder r7 = new BottomSheet.Builder(this.this$1.this$0.getParentActivity(), false, ChatViewer.m1384$$Nest$fgettheme(this.this$1.this$0));
                r7.setTitle(r22);
                String r0 = LocaleController.getString(R.string.CopyTitle);
                String r8 = null;
                if (r3 != null) goto L24;
            L26:
                String r10 = null;
            L28:
                if (TextUtils.isEmpty(r4) == true) goto L30;
                String r11 = LocaleController.getString(R.string.CopyLink);
            L31:
                if (r5 != null) goto L33;
            L35:
                String r12 = null;
            L36:
                if (r6 != null) goto L38;
            L40:
                r7.setItems(new CharSequence[]{r0, r10, r11, r12, r8}, new ChatViewer$ChatActivityAdapter$1$$ExternalSyntheticLambda0(this, r22, r3, r4, r5, r6));
                this.this$1.this$0.showDialog(r7.create());
                r18.performHapticFeedback(VibratorUtils.getType(0), 1);     // Catch: Exception -> L43
                return;
            L47:
                return;
            L38:
                if (r6.user_id == 0) goto L40;
                r8 = LocaleController.getString(R.string.CopyID);
                goto L40
            L33:
                if (r5.query == null) goto L35;
                r12 = LocaleController.getString(R.string.CopyInlineQuery);
                goto L36
            L30:
                r11 = null;
                goto L31
            L24:
                if (r3.data == null) goto L26;
                r10 = LocaleController.getString(R.string.CopyCallback);
                goto L28
            }

            public void didLongPressCopyButton(String r5) {
                BottomSheet.Builder r0 = new BottomSheet.Builder(this.this$1.this$0.getParentActivity(), false, ChatViewer.m1384$$Nest$fgettheme(this.this$1.this$0));
                r0.setTitle(r5);
                r0.setTitleMultipleLines(true);
                r0.setItems(new CharSequence[]{LocaleController.getString(R.string.Copy)}, new ChatViewer$ChatActivityAdapter$1$$ExternalSyntheticLambda3(this, r5));
                this.this$1.this$0.showDialog(r0.create());
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean didLongPressUserAvatar(ChatMessageCell r4, TLRPC.User r5, float r6, float r7) {
                if (r5 != null) goto L4;
                return false;
            L4:
                if (r5.id == UserConfig.getInstance(ChatViewer.access$1900(this.this$1.this$0)).getClientUserId()) goto L18;
                TLRPC.User r62 = this.this$1.this$0.getMessagesController().getUser(Long.valueOf(r5.id));
                if (r62 == null) goto L8;
                r5 = r62;
            L8:
                AvatarPreviewer.MenuItem[] r63 = {AvatarPreviewer.MenuItem.OPEN_PROFILE, AvatarPreviewer.MenuItem.SEND_MESSAGE};
                TLRPC.UserFull r72 = this.this$1.this$0.getMessagesController().getUserFull(r5.id);
                if (r72 == null) goto L11;
                AvatarPreviewer.Data r64 = AvatarPreviewer.Data.of(r5, r72, r63);
            L13:
                if (AvatarPreviewer.canPreview(r64) == false) goto L19;
                AvatarPreviewer r73 = AvatarPreviewer.getInstance();
                ChatViewer r0 = this.this$1.this$0;
                r73.show((ViewGroup) r0.fragmentView, r0.getResourceProvider(), r64, new ChatViewer$ChatActivityAdapter$1$$ExternalSyntheticLambda2(this, r4, r5));
                return true;
            L19:
                return false;
            L11:
                r64 = AvatarPreviewer.Data.of(r5, ChatViewer.access$2000(this.this$1.this$0), r63);
                goto L13
            L18:
                return false;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean didPressAnimatedEmoji(ChatMessageCell r7, AnimatedEmojiSpan r8) {
                boolean r0 = false;
                if (this.this$1.this$0.getMessagesController().premiumFeaturesBlocked() == true) goto L21;
                if (r8 == null) goto L21;
                if (r8.standard == true) goto L21;
                long r1 = r8.getDocumentId();
                TLRPC.Document r72 = r8.document;
                if (r72 != null) goto L11;
                r72 = AnimatedEmojiDrawable.findDocument(ChatViewer.access$2600(this.this$1.this$0), r1);
            L11:
                if (r72 != null) goto L13;
                return false;
            L13:
                TLRPC.InputStickerSet r82 = MessageObject.getInputStickerSet(r72);
                if (r82 != null) goto L16;
                return false;
            L16:
                ArrayList r12 = new ArrayList(1);
                r12.add(r82);
                ChatViewer r3 = this.this$1.this$0;
                EmojiPacksAlert r83 = new EmojiPacksAlert(r3, r3.getParentActivity(), ChatViewer.m1384$$Nest$fgettheme(this.this$1.this$0), r12);
                r83.setPreviewEmoji(r72);
                if (ChatViewer.m1372$$Nest$fgetcontentView(this.this$1.this$0).getKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L19;
                r0 = true;
            L19:
                r83.setCalcMandatoryInsets(r0);
                this.this$1.this$0.showDialog(r83);
                return true;
            L21:
                return false;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressCancelSendButton(ChatMessageCell r1) {
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressChannelAvatar(ChatMessageCell r1, TLRPC.Chat r2, int r3, float r4, float r5, boolean r6) {
                if (r2 != null) goto L4;
                return;
            L4:
                if (r2 == this.this$1.this$0.currentChat) goto L13;
                Bundle r12 = new Bundle();
                r12.putLong("chat_id", r2.id);
                if (r3 == 0) goto L9;
                r12.putInt("message_id", r3);
            L9:
                if (MessagesController.getInstance(ChatViewer.access$1700(this.this$1.this$0)).checkCanOpenChat(r12, this.this$1.this$0) == false) goto L14;
                this.this$1.this$0.presentFragment(new ChatActivity(r12), true);
                return;
            L14:
                return;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressCodeCopy(ChatMessageCell r7, MessageObject.TextLayoutBlock r8) {
                if (r8 == null) goto L10;
                StaticLayout r72 = r8.textLayout;
                if (r72 != null) goto L6;
                return;
            L6:
                if (r72.getText() == null) goto L12;
                String r5 = r8.textLayout.getText().toString();
                SpannableString r73 = new SpannableString(r5);
                r73.setSpan(new CodeHighlighting.Span(false, 0, null, r8.language, r5), 0, r73.length(), 33);
                AndroidUtilities.addToClipboard(r73);
                BulletinFactory.of(this.this$1.this$0).createCopyBulletin(LocaleController.getString(R.string.CodeCopied)).show();
                return;
            L12:
                return;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressImage(ChatMessageCell r11, float r12, float r13, boolean r14) {
                MessageObject r1 = r11.getMessageObject();
                if (r1.getInputStickerSet() == null) goto L7;
                this.this$1.this$0.showDialog(new StickersAlert(this.this$1.this$0.getParentActivity(), this.this$1.this$0, r1.getInputStickerSet(), null, null, false));
                return;
            L7:
                if (r1.isVideo() == true) goto L72;
                int r112 = r1.type;
                if (r112 == 1) goto L72;
                if (r112 != 0) goto L14;
                if (r1.isWebpageDocument() == false) goto L72;
            L14:
                if (r1.isGif() == true) goto L72;
                int r113 = r1.type;
                File r142 = null;
                if (r113 != 3) goto L31;
                String r114 = r1.messageOwner.attachPath;     // Catch: Exception -> L29
                if (r114 != null) goto L21;
            L23:
                if (r142 != null) goto L25;
            L26:
                r142 = this.this$1.this$0.getFileLoader().getPathToMessage(r1.messageOwner);     // Catch: Exception -> L29
            L27:
                Intent r115 = new Intent("android.intent.action.VIEW");     // Catch: Exception -> L29
                r115.setFlags(1);     // Catch: Exception -> L29
                r115.setDataAndType(FileProvider.getUriForFile(this.this$1.this$0.getParentActivity(), ApplicationLoader.getApplicationId() + ".provider", r142), "video/mp4");     // Catch: Exception -> L29
                this.this$1.this$0.getParentActivity().startActivityForResult(r115, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L29
                return;
            L25:
                if (r142.exists() == true) goto L27;
            L21:
                if (r114.length() == 0) goto L23;
                r142 = new File(r1.messageOwner.attachPath);     // Catch: Exception -> L29
            L29:
                ChatViewer.m1392$$Nest$malertUserOpenError(this.this$1.this$0, r1);
                return;
            L31:
                if (r113 != 4) goto L38;
                if (AndroidUtilities.isMapsInstalled(this.this$1.this$0) == false) goto L80;
                LocationActivity r116 = new LocationActivity(0);
                r116.setMessageObject(r1);
                this.this$1.this$0.presentFragment(r116);
                return;
            L80:
                return;
            L38:
                if (r113 == 9) goto L41;
                if (r113 == 0) goto L41;
                return;
            L41:
                if (r1.getDocumentName().toLowerCase().endsWith("attheme") == false) goto L74;
                String r117 = r1.messageOwner.attachPath;
                if (r117 != null) goto L45;
            L49:
                File r118 = null;
            L50:
                if (r118 != null) goto L55;
                File r132 = this.this$1.this$0.getFileLoader().getPathToMessage(r1.messageOwner);
                if (r132.exists() == false) goto L55;
                r118 = r132;
            L55:
                if (ChatViewer.m1369$$Nest$fgetchatLayoutManager(this.this$1.this$0) == null) goto L63;
                int r133 = ChatViewer.m1369$$Nest$fgetchatLayoutManager(this.this$1.this$0).findLastVisibleItemPosition();
                int r3 = ChatViewer.m1369$$Nest$fgetchatLayoutManager(this.this$1.this$0).getItemCount() - 1;
                ChatActivityAdapter r4 = this.this$1;
                if (r133 >= r3) goto L62;
                ChatViewer r134 = r4.this$0;
                ChatViewer.m1390$$Nest$fputscrollToPositionOnRecreate(r134, ChatViewer.m1369$$Nest$fgetchatLayoutManager(r134).findFirstVisibleItemPosition());
                RecyclerListView.Holder r135 = (RecyclerListView.Holder) ChatViewer.m1370$$Nest$fgetchatListView(this.this$1.this$0).findViewHolderForAdapterPosition(ChatViewer.m1382$$Nest$fgetscrollToPositionOnRecreate(this.this$1.this$0));
                ChatActivityAdapter r32 = this.this$1;
                if (r135 == null) goto L61;
                ChatViewer.m1389$$Nest$fputscrollToOffsetOnRecreate(r32.this$0, r135.itemView.getTop());
                goto L63
            L61:
                ChatViewer.m1390$$Nest$fputscrollToPositionOnRecreate(r32.this$0, -1);
                goto L63
            L62:
                ChatViewer.m1390$$Nest$fputscrollToPositionOnRecreate(r4.this$0, -1);
            L63:
                Theme.ThemeInfo r119 = Theme.applyThemeFile(r118, r1.getDocumentName(), null, true);
                ChatActivityAdapter r122 = this.this$1;
                if (r119 == null) goto L67;
                r122.this$0.presentFragment(new ThemePreviewActivity(r119));
                return;
            L67:
                ChatViewer.m1390$$Nest$fputscrollToPositionOnRecreate(r122.this$0, -1);
                goto L74
            L45:
                if (r117.length() == 0) goto L49;
                r118 = new File(r1.messageOwner.attachPath);
                if (r118.exists() == false) goto L49;
            L74:
                AndroidUtilities.openForView(r1, this.this$1.this$0.getParentActivity(), null, false);     // Catch: Exception -> L70
                return;
            L70:
                ChatViewer.m1392$$Nest$malertUserOpenError(this.this$1.this$0, r1);
                return;
            L72:
                PhotoViewer.getInstance().setParentActivity(this.this$1.this$0);
                PhotoViewer.getInstance().openPhoto(r1, null, 0, 0, 0, ChatViewer.m1379$$Nest$fgetprovider(this.this$1.this$0));
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressInstantButton(ChatMessageCell r3, int r4) {
                MessageObject r32 = r3.getMessageObject();
                if (r4 != 0) goto L13;
                TLRPC.MessageMedia r42 = r32.messageOwner.media;
                if (r42 == null) goto L22;
                TLRPC.WebPage r43 = r42.webpage;
                if (r43 != null) goto L9;
                return;
            L9:
                if (r43.cached_page == null) goto L24;
                ArticleViewer.getInstance().setParentActivity(this.this$1.this$0.getParentActivity(), this.this$1.this$0);
                ArticleViewer.getInstance().open(r32);
                return;
            L24:
                return;
            L22:
                return;
            L13:
                if (r4 != 5) goto L16;
                ChatViewer r2 = this.this$1.this$0;
                TLRPC.User r44 = r2.getMessagesController().getUser(Long.valueOf(r32.messageOwner.media.user_id));
                TLRPC.MessageMedia r33 = r32.messageOwner.media;
                r2.openVCard(r44, r33.vcard, r33.first_name, r33.last_name);
                return;
            L16:
                TLRPC.MessageMedia r45 = r32.messageOwner.media;
                if (r45 != null) goto L19;
                return;
            L19:
                if (r45.webpage == null) goto L26;
                Browser.openUrl(this.this$1.this$0.getParentActivity(), r32.messageOwner.media.webpage.url);
                return;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressOther(ChatMessageCell r1, float r2, float r3) {
                ChatViewer.m1395$$Nest$mcreateMenu(this.this$1.this$0, r1);
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressSideButton(ChatMessageCell r9) {
                if (this.this$1.this$0.getParentActivity() != null) goto L5;
                return;
            L5:
                ChatActivityAdapter r0 = this.this$1;
                ChatViewer r1 = r0.this$0;
                Context r2 = ChatActivityAdapter.m1402$$Nest$fgetmContext(r0);
                MessageObject r3 = r9.getMessageObject();
                if (ChatObject.isChannel(this.this$1.this$0.currentChat) == true) goto L8;
            L11:
                boolean r8 = false;
            L12:
                r1.showDialog(ShareAlert.createShareAlert(r2, r3, null, r8, null, false));
                return;
            L8:
                if (this.this$1.this$0.currentChat.megagroup == true) goto L11;
                r8 = true;
                goto L12
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressUrl(ChatMessageCell r8, CharacterStyle r9, boolean r10) {
                if (r9 == null) goto L54;
                MessageObject r0 = r8.getMessageObject();
                if ((r9 instanceof URLSpanMono) == false) goto L10;
                ((URLSpanMono) r9).copyToClipboard();
                if (AndroidUtilities.shouldShowClipboardToast() == false) goto L50;
                Toast.makeText(this.this$1.this$0.getParentActivity(), LocaleController.getString(R.string.TextCopied), 0).show();
            L50:
                if (r10 == false) goto L53;
                r8.resetPressedLink(-1);
                return;
            L53:
                return;
            L10:
                if ((r9 instanceof URLSpanUserMention) == false) goto L20;
                Long r92 = Utilities.parseLong(((URLSpanUserMention) r9).getURL());
                long r02 = r92.longValue();
                ChatActivityAdapter r3 = this.this$1;
                if (r02 <= 0) goto L16;
                TLRPC.User r2 = MessagesController.getInstance(ChatViewer.access$2100(r3.this$0)).getUser(r92);
                if (r2 == null) goto L50;
                MessagesController.getInstance(ChatViewer.access$2200(this.this$1.this$0)).openChatOrProfileWith(r2, null, this.this$1.this$0, 0, false);
                goto L50
            L16:
                TLRPC.Chat r32 = MessagesController.getInstance(ChatViewer.access$2300(r3.this$0)).getChat(Long.valueOf(-r02));
                if (r32 == null) goto L50;
                MessagesController.getInstance(ChatViewer.access$2400(this.this$1.this$0)).openChatOrProfileWith(null, r32, this.this$1.this$0, 0, false);
                goto L50
            L20:
                if ((r9 instanceof URLSpanNoUnderline) == false) goto L27;
                String r93 = ((URLSpanNoUnderline) r9).getURL();
                if (r93.startsWith("@") == false) goto L25;
                MessagesController.getInstance(ChatViewer.access$2500(this.this$1.this$0)).openByUserName(r93.substring(1), this.this$1.this$0, 0);
                goto L50
            L25:
                if (r93.startsWith("#") == false) goto L50;
                DialogsActivity r03 = new DialogsActivity(null);
                r03.setSearchString(r93);
                this.this$1.this$0.presentFragment(r03);
                goto L50
            L27:
                String r1 = ((URLSpan) r9).getURL();
                if (r10 == false) goto L31;
                BottomSheet.Builder r94 = new BottomSheet.Builder(this.this$1.this$0.getParentActivity());
                r94.setTitle(r1);
                r94.setItems(new CharSequence[]{LocaleController.getString(R.string.Open), LocaleController.getString(R.string.Copy)}, new ChatViewer$ChatActivityAdapter$1$$ExternalSyntheticLambda1(this, r1));
                this.this$1.this$0.showDialog(r94.create());
                goto L50
            L31:
                if ((r9 instanceof URLSpanReplacement) == false) goto L33;
                this.this$1.this$0.showOpenUrlAlert(((URLSpanReplacement) r9).getURL(), true);
                goto L50
            L33:
                TLRPC.MessageMedia r95 = r0.messageOwner.media;
                if ((r95 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L49;
                TLRPC.WebPage r96 = r95.webpage;
                if (r96 == null) goto L49;
                if (r96.cached_page == null) goto L49;
                String r97 = r1.toLowerCase();
                String r4 = r0.messageOwner.media.webpage.url.toLowerCase();
                if (Browser.isTelegraphUrl(r97, false) == true) goto L44;
                if (r97.contains("t.me/iv") == false) goto L49;
            L44:
                if (r97.contains(r4) == false) goto L46;
            L47:
                ArticleViewer.getInstance().setParentActivity(this.this$1.this$0.getParentActivity(), this.this$1.this$0);
                ArticleViewer.getInstance().open(r0);
                return;
            L46:
                if (r4.contains(r97) == true) goto L47;
            L49:
                Browser.openUrl(this.this$1.this$0.getParentActivity(), r1, true);
                goto L50
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressUserAvatar(ChatMessageCell r3, TLRPC.User r4, float r5, float r6, boolean r7) {
                if (r4 != null) goto L4;
                return;
            L4:
                if (r4.id == UserConfig.getInstance(ChatViewer.access$1800(this.this$1.this$0)).getClientUserId()) goto L8;
                openProfile(r4);
                return;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean doNotShowLoadingReply(MessageObject r1) {
                return true;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void forceUpdate(ChatMessageCell r1, boolean r2) {
                if (r1 == null) goto L9;
                MessageObject r12 = r1.getPrimaryMessageObject();
                if (r12 != null) goto L7;
                return;
            L7:
                r12.forceUpdate = true;
                ChatViewer.m1399$$Nest$mupdateMessageAnimatedInternal(this.this$1.this$0, r12, false);
                return;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void needOpenWebView(MessageObject r11, String r12, String r13, String r14, String r15, int r16, int r17) {
                ChatViewer r0 = this.this$1.this$0;
                EmbedBottomSheet.show(r0, r11, ChatViewer.m1379$$Nest$fgetprovider(r0), r13, r14, r15, r12, r16, r17, false);
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean needPlayMessage(ChatMessageCell r3, MessageObject r4, boolean r5) {
                if (r4.isVoice() == false) goto L5;
            L12:
                boolean r2 = MediaController.getInstance().playMessage(r4, r5);
                MediaController.getInstance().setVoiceMessagesPlaylist(null, false);
                return r2;
            L5:
                if (r4.isRoundVideo() == true) goto L12;
                if (r4.isMusic() == true) goto L10;
                return false;
            L10:
                return MediaController.getInstance().setPlaylist(this.this$1.this$0.messages, r4, 0);
            }
        }

        /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$ChatActivityAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends ChatActionCell {
            final /* synthetic */ ChatActivityAdapter this$1;

            public AnonymousClass2(ChatActivityAdapter r1, Context r2, boolean r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Cells.ChatActionCell, android.view.View
            public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
                super.onInitializeAccessibilityNodeInfo(r1);
                r1.setVisibleToUser(true);
            }
        }

        /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$ChatActivityAdapter$3, reason: invalid class name */
        public class AnonymousClass3 implements ChatActionCell.ChatActionCellDelegate {
            final /* synthetic */ ChatActivityAdapter this$1;

            public AnonymousClass3(ChatActivityAdapter r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public void didClickImage(ChatActionCell r11) {
                MessageObject r1 = r11.getMessageObject();
                PhotoViewer.getInstance().setParentActivity(this.this$1.this$0);
                TLRPC.PhotoSize r112 = FileLoader.getClosestPhotoSizeWithSize(r1.photoThumbs, 640);
                if (r112 == null) goto L6;
                ImageLocation r0 = ImageLocation.getForPhoto(r112, r1.messageOwner.action.photo);
                PhotoViewer.getInstance().openPhoto(r112.location, r0, ChatViewer.m1379$$Nest$fgetprovider(this.this$1.this$0));
                return;
            L6:
                PhotoViewer.getInstance().openPhoto(r1, null, 0, 0, 0, ChatViewer.m1379$$Nest$fgetprovider(this.this$1.this$0));
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public boolean didLongPress(ChatActionCell r1, float r2, float r3) {
                return ChatViewer.m1395$$Nest$mcreateMenu(this.this$1.this$0, r1);
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public void didPressReplyMessage(ChatActionCell r1, int r2) {
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public BaseFragment getBaseFragment() {
                return this.this$1.this$0;
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public long getDialogId() {
                return ChatViewer.m1397$$Nest$mgetDialogId(this.this$1.this$0);
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public void needOpenUserProfile(long r3) {
                if (r3 >= 0) goto L9;
                Bundle r0 = new Bundle();
                r0.putLong("chat_id", -r3);
                if (MessagesController.getInstance(ChatViewer.access$2700(this.this$1.this$0)).checkCanOpenChat(r0, this.this$1.this$0) == false) goto L12;
                this.this$1.this$0.presentFragment(new ChatActivity(r0), true);
                return;
            L12:
                return;
            L9:
                if (r3 == UserConfig.getInstance(ChatViewer.access$2800(this.this$1.this$0)).getClientUserId()) goto L13;
                ProfileActivity r4 = new ProfileActivity(new Bundle());
                r4.setPlayProfileAnimation(0);
                this.this$1.this$0.presentFragment(r4);
                return;
            }
        }

        /* renamed from: com.exteragram.messenger.export.ui.ChatViewer$ChatActivityAdapter$4, reason: invalid class name */
        public class AnonymousClass4 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ ChatActivityAdapter this$1;
            final /* synthetic */ RecyclerView.ViewHolder val$holder;
            final /* synthetic */ View val$view;

            public AnonymousClass4(ChatActivityAdapter r1, View r2, RecyclerView.ViewHolder r3) {
                this.this$1 = r1;
                this.val$view = r2;
                this.val$holder = r3;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                this.val$view.getViewTreeObserver().removeOnPreDrawListener(this);
                int r0 = ChatViewer.m1370$$Nest$fgetchatListView(this.this$1.this$0).getMeasuredHeight();
                int r1 = this.val$view.getTop();
                this.val$view.getBottom();
                if (r1 < 0) goto L6;
                int r12 = 0;
            L5:
                int r3 = r12;
                int r13 = this.val$view.getMeasuredHeight();
                if (r13 <= r0) goto L10;
                r13 = r3 + r0;
            L10:
                View r02 = this.val$holder.itemView;
                if ((r02 instanceof ChatMessageCell) == false) goto L14;
                ((ChatMessageCell) this.val$view).setVisiblePart(r3, r13 - r3, ChatViewer.m1372$$Nest$fgetcontentView(this.this$1.this$0).getHeightWithKeyboard() - ChatViewer.m1370$$Nest$fgetchatListView(this.this$1.this$0).getTop(), 0.0f, (this.val$view.getY() + ChatViewer.access$2900(this.this$1.this$0).getMeasuredHeight()) - ChatViewer.m1372$$Nest$fgetcontentView(this.this$1.this$0).getBackgroundTranslationY(), ChatViewer.m1372$$Nest$fgetcontentView(this.this$1.this$0).getMeasuredWidth(), ChatViewer.m1372$$Nest$fgetcontentView(this.this$1.this$0).getBackgroundSizeY(), 0, 0, 0);
            L20:
                ChatViewer.m1400$$Nest$mupdateMessagesVisiblePart(this.this$1.this$0);
                return true;
            L14:
                if ((r02 instanceof ChatActionCell) == false) goto L20;
                if (ChatViewer.access$3000(this.this$1.this$0) == null) goto L20;
                if (ChatViewer.m1372$$Nest$fgetcontentView(this.this$1.this$0) == null) goto L20;
                View r03 = this.val$view;
                ((ChatActionCell) r03).setVisiblePart((r03.getY() + ChatViewer.access$3100(this.this$1.this$0).getMeasuredHeight()) - ChatViewer.m1372$$Nest$fgetcontentView(this.this$1.this$0).getBackgroundTranslationY(), ChatViewer.m1372$$Nest$fgetcontentView(this.this$1.this$0).getBackgroundSizeY());
                goto L20
            L6:
                r12 = -r1;
                goto L5
            }
        }

        /* renamed from: -$$Nest$fgetmContext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Context m1402$$Nest$fgetmContext(ChatActivityAdapter r0) {
            return r0.mContext;
        }

        /* renamed from: -$$Nest$fgetmessagesStartRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1403$$Nest$fgetmessagesStartRow(ChatActivityAdapter r0) {
            return r0.messagesStartRow;
        }

        public ChatActivityAdapter(ChatViewer r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.rowCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r1) {
            return -1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 >= this.messagesStartRow) goto L5;
            return 4;
        L5:
            if (r3 < this.messagesEndRow) goto L7;
            return 4;
        L7:
            return this.this$0.messages.get((r0.size() - (r3 - this.messagesStartRow)) - 1).contentType;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            updateRows();
            super.notifyDataSetChanged();     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemChanged(int r1) {
            updateRows();
            super.notifyItemChanged(r1);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemInserted(int r1) {
            updateRows();
            super.notifyItemInserted(r1);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemMoved(int r1, int r2) {
            updateRows();
            super.notifyItemMoved(r1, r2);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeChanged(int r1, int r2) {
            updateRows();
            super.notifyItemRangeChanged(r1, r2);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeInserted(int r1, int r2) {
            updateRows();
            super.notifyItemRangeInserted(r1, r2);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeRemoved(int r1, int r2) {
            updateRows();
            super.notifyItemRangeRemoved(r1, r2);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRemoved(int r1) {
            updateRows();
            super.notifyItemRemoved(r1);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r14, int r15) {
            if (r15 != this.loadingUpRow) goto L7;
            ((ChatLoadingCell) r14.itemView).setProgressVisible(false);
            return;
        L7:
            if (r15 >= this.messagesStartRow) goto L9;
            return;
        L9:
            if (r15 >= this.messagesEndRow) goto L47;
            ArrayList<MessageObject> r0 = this.this$0.messages;
            boolean r3 = true;
            MessageObject r5 = r0.get((r0.size() - (r15 - this.messagesStartRow)) - 1);
            View r02 = r14.itemView;
            if ((r02 instanceof ChatMessageCell) == false) goto L43;
            ChatMessageCell r4 = (ChatMessageCell) r02;
            r4.isChat = false;
            int r03 = r15 + 1;
            int r2 = getItemViewType(r03);
            int r6 = getItemViewType(r15 - 1);
            if ((r5.messageOwner.reply_markup instanceof TLRPC.TL_replyInlineMarkup) == false) goto L15;
        L25:
            boolean r7 = false;
        L27:
            if (r6 != r14.getItemViewType()) goto L39;
            ArrayList<MessageObject> r142 = this.this$0.messages;
            MessageObject r13 = r142.get(r142.size() - (r15 - this.messagesStartRow));
            if ((r13.messageOwner.reply_markup instanceof TLRPC.TL_replyInlineMarkup) == false) goto L31;
        L37:
            r3 = false;
        L38:
            boolean r8 = r3;
        L40:
            r4.setMessageObject(r5, null, r7, r8, false);
            r4.setHighlighted(false);
            r4.setHighlightedText(null);
            return;
        L31:
            if (r13.isOutOwner() != r5.isOutOwner()) goto L37;
            if (r13.getFromChatId() != r5.getFromChatId()) goto L37;
            if (Math.abs(r13.messageOwner.date - r5.messageOwner.date) > 300) goto L37;
        L39:
            r8 = false;
            goto L40
        L15:
            if (r2 != r14.getItemViewType()) goto L25;
            ArrayList<MessageObject> r22 = this.this$0.messages;
            MessageObject r04 = r22.get((r22.size() - (r03 - this.messagesStartRow)) - 1);
            if (r04.isOutOwner() == r5.isOutOwner()) goto L19;
        L23:
            boolean r05 = false;
        L24:
            r7 = r05;
            goto L27
        L19:
            if (r04.getFromChatId() != r5.getFromChatId()) goto L23;
            if (Math.abs(r04.messageOwner.date - r5.messageOwner.date) > 300) goto L23;
            r05 = true;
            goto L24
        L43:
            if ((r02 instanceof ChatActionCell) == false) goto L48;
            ChatActionCell r06 = (ChatActionCell) r02;
            r06.setMessageObject(r5);
            r06.setAlpha(1.0f);
            return;
        L48:
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v13, types: [android.view.View] */
        /* JADX WARN: Type inference failed for: r4v3, types: [com.exteragram.messenger.export.ui.ChatViewer$ChatActivityAdapter$2, org.telegram.ui.Cells.ChatActionCell] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 == 0) goto L5;
            if (r4 != 1) goto L12;
            ?? r42 = new AnonymousClass2(this, this.mContext, false, ChatViewer.m1384$$Nest$fgettheme(this.this$0));
            r42.setDelegate(new AnonymousClass3(this));
            ViewGroup r43 = r42;
        L15:
            r43.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r43);
        L12:
            if (r4 != 2) goto L14;
            r43 = new ChatUnreadCell(this.mContext, ChatViewer.m1384$$Nest$fgettheme(this.this$0));
            goto L15
        L14:
            r43 = new ChatLoadingCell(this.mContext, ChatViewer.m1372$$Nest$fgetcontentView(this.this$0), ChatViewer.m1384$$Nest$fgettheme(this.this$0));
            goto L15
        L5:
            if (ChatViewer.m1371$$Nest$fgetchatMessageCellsCache(this.this$0).isEmpty() == true) goto L7;
            ?? r44 = (View) ChatViewer.m1371$$Nest$fgetchatMessageCellsCache(this.this$0).get(0);
            ChatViewer.m1371$$Nest$fgetchatMessageCellsCache(this.this$0).remove(0);
            ViewGroup r45 = r44;
        L8:
            ChatMessageCell r0 = (ChatMessageCell) r45;
            r0.setDelegate(new AnonymousClass1(this));
            r0.setAllowAssistant(true);
            r43 = r45;
            goto L15
        L7:
            r45 = new ChatMessageCell(this.mContext, ChatViewer.access$1600(this.this$0));
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r4) {
            View r0 = r4.itemView;
            if ((r0 instanceof ChatMessageCell) == false) goto L5;
        L6:
            r0.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass4(this, r0, r4));
        L7:
            View r3 = r4.itemView;
            if ((r3 instanceof ChatMessageCell) == false) goto L11;
            ChatMessageCell r32 = (ChatMessageCell) r3;
            r32.setBackgroundDrawable(null);
            r32.setCheckPressed(true, false);
            r32.setHighlighted(false);
            return;
        L11:
            return;
        L5:
            if ((r0 instanceof ChatActionCell) == false) goto L7;
            goto L6
        }

        public void updateRows() {
            this.rowCount = 0;
            if (this.this$0.messages.isEmpty() == false) goto L5;
            this.loadingUpRow = -1;
            this.messagesStartRow = -1;
            this.messagesEndRow = -1;
            return;
        L5:
            if (ChatViewer.m1375$$Nest$fgetendReached(this.this$0) == true) goto L7;
            int r0 = this.rowCount;
            this.rowCount = r0 + 1;
            this.loadingUpRow = r0;
        L8:
            int r02 = this.rowCount;
            this.messagesStartRow = r02;
            int r03 = r02 + this.this$0.messages.size();
            this.rowCount = r03;
            this.messagesEndRow = r03;
            return;
        L7:
            this.loadingUpRow = -1;
            goto L8
        }
    }

    public static class QuadroResult {
        private final ArrayList<TLRPC.Chat> chats;
        private LongSparseArray<TLRPC.Chat> chatsDict;
        private final ArrayList<TLRPC.User> users;
        private LongSparseArray<TLRPC.User> usersDict;

        public QuadroResult(ArrayList<TLRPC.User> r1, ArrayList<TLRPC.Chat> r2) {
            this.users = r1;
            this.chats = r2;
        }

        public ArrayList<TLRPC.Chat> getChats() {
            return this.chats;
        }

        public Pair<LongSparseArray<TLRPC.User>, LongSparseArray<TLRPC.Chat>> getDicts() {
            if (this.usersDict != null) goto L13;
            if (this.chatsDict != null) goto L13;
            this.usersDict = new LongSparseArray();
            this.chatsDict = new LongSparseArray();
            ArrayList<TLRPC.User> r0 = this.users;
            int r1 = r0.size();
            int r2 = 0;
            int r3 = 0;
        L7:
            if (r3 >= r1) goto L9;
            TLRPC.User r4 = r0.get(r3);
            r3 = r3 + 1;
            TLRPC.User r42 = r4;
            this.usersDict.put(r42.id, r42);
            goto L7
        L9:
            ArrayList<TLRPC.Chat> r02 = this.chats;
            int r12 = r02.size();
        L10:
            if (r2 >= r12) goto L13;
            TLRPC.Chat r32 = r02.get(r2);
            r2 = r2 + 1;
            TLRPC.Chat r33 = r32;
            this.chatsDict.put(r33.id, r33);
        L13:
            return new Pair(this.usersDict, this.chatsDict);
        }

        public ArrayList<TLRPC.User> getUsers() {
            return this.users;
        }
    }

    /* renamed from: $r8$lambda$6DSknWxE-QzdmDrzFTcnJnuhpAU, reason: not valid java name */
    public static /* synthetic */ boolean m1363$r8$lambda$6DSknWxEQzdmDrzFTcnJnuhpAU(File r0, String r1) {
        if (r1.contains("messages") == true) goto L5;
        return false;
    L5:
        if (r1.contains("json") == false) goto L10;
        return true;
    L10:
        return false;
    }

    /* renamed from: $r8$lambda$FCwMhBQ-q047uSYAuBP2Yay1Zkw, reason: not valid java name */
    public static /* synthetic */ boolean m1364$r8$lambda$FCwMhBQq047uSYAuBP2Yay1Zkw(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$H-mS-pKxSvUeiyKiSbM91ehH2_o, reason: not valid java name */
    public static /* synthetic */ void m1365$r8$lambda$HmSpKxSvUeiyKiSbM91ehH2_o(ChatViewer r0, ArrayList r1) {
        r0.lambda$loadMessages$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$_Z5HNc14lr8La2q6wD020Cnw1iM(ChatViewer r0, View r1, int r2, float r3, float r4) {
        r0.lambda$createView$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$b9vIx0jc9UW1Qgno9AhgqCKp_LY(ChatViewer r0) {
        r0.lambda$fillMessagesCount$1();
    }

    public static /* synthetic */ void $r8$lambda$hR2b0EEyYUiGfEek1sq7x1qlsz0(ChatViewer r0, ArrayList r1, ArrayList r2, ArrayList r3, View r4, float r5, float r6) {
        r0.lambda$createMenu$9(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$hTBgLcVY3fnv_A1Qur7zXN0vDh4(ArrayList r2, MessagesController r3, ArrayList r4) {
        if (r2.isEmpty() == true) goto L6;
        r3.putUsers(r2, true);
    L6:
        if (r4.isEmpty() == true) goto L9;
        r3.putChats(r4, true);
        return;
    }

    /* renamed from: $r8$lambda$lL-MPjEk59hwCnw8ThrH5DzJN3Q, reason: not valid java name */
    public static /* synthetic */ void m1366$r8$lambda$lLMPjEk59hwCnw8ThrH5DzJN3Q(ChatViewer r0, int r1, ArrayList r2, Integer r3, ActionBarPopupWindow.ActionBarPopupWindowLayout r4, ActionBarMenuSubItem r5, View r6) {
        r0.lambda$createMenu$7(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$q4N0PZx_KkSZbXNBnuHoqX9QKnA(ChatViewer r0) {
        r0.lambda$fillMessagesCount$0();
    }

    public static /* synthetic */ void $r8$lambda$v1321gbQZo6kZCfTFZAZBBHkImA(ChatViewer r0, ActionBarPopupWindow.ActionBarPopupWindowLayout r1, int r2, View r3) {
        r0.lambda$createMenu$8(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$v_CCXdyo11M0L8olRfs--sFBSCk, reason: not valid java name */
    public static /* synthetic */ void m1367$r8$lambda$v_CCXdyo11M0L8olRfssFBSCk(ChatViewer r0, ChatActivity r1, int r2) {
        r0.lambda$processSelectedOption$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wR5kJIbOpBNoSMXdQIg3WEHnMbc(ChatViewer r0, String r1, AlertDialog r2, int r3) {
        r0.lambda$showOpenUrlAlert$11(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetaspectRatioFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AspectRatioFrameLayout m1368$$Nest$fgetaspectRatioFrameLayout(ChatViewer r0) {
        return r0.aspectRatioFrameLayout;
    }

    /* renamed from: -$$Nest$fgetchatLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m1369$$Nest$fgetchatLayoutManager(ChatViewer r0) {
        return r0.chatLayoutManager;
    }

    /* renamed from: -$$Nest$fgetchatListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m1370$$Nest$fgetchatListView(ChatViewer r0) {
        return r0.chatListView;
    }

    /* renamed from: -$$Nest$fgetchatMessageCellsCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m1371$$Nest$fgetchatMessageCellsCache(ChatViewer r0) {
        return r0.chatMessageCellsCache;
    }

    /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SizeNotifierFrameLayout m1372$$Nest$fgetcontentView(ChatViewer r0) {
        return r0.contentView;
    }

    /* renamed from: -$$Nest$fgetcurrentFloatingTopIsNotMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1373$$Nest$fgetcurrentFloatingTopIsNotMessage(ChatViewer r0) {
        return r0.currentFloatingTopIsNotMessage;
    }

    /* renamed from: -$$Nest$fgetemptyViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m1374$$Nest$fgetemptyViewContainer(ChatViewer r0) {
        return r0.emptyViewContainer;
    }

    /* renamed from: -$$Nest$fgetendReached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1375$$Nest$fgetendReached(ChatViewer r0) {
        return r0.endReached;
    }

    /* renamed from: -$$Nest$fgetfloatingDateAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m1376$$Nest$fgetfloatingDateAnimation(ChatViewer r0) {
        return r0.floatingDateAnimation;
    }

    /* renamed from: -$$Nest$fgetfloatingDateView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActionCell m1377$$Nest$fgetfloatingDateView(ChatViewer r0) {
        return r0.floatingDateView;
    }

    /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m1378$$Nest$fgetprogressView(ChatViewer r0) {
        return r0.progressView;
    }

    /* renamed from: -$$Nest$fgetprovider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoViewer.PhotoViewerProvider m1379$$Nest$fgetprovider(ChatViewer r0) {
        return r0.provider;
    }

    /* renamed from: -$$Nest$fgetroundVideoContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m1380$$Nest$fgetroundVideoContainer(ChatViewer r0) {
        return r0.roundVideoContainer;
    }

    /* renamed from: -$$Nest$fgetscrimPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarPopupWindow m1381$$Nest$fgetscrimPopupWindow(ChatViewer r0) {
        return r0.scrimPopupWindow;
    }

    /* renamed from: -$$Nest$fgetscrollToPositionOnRecreate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1382$$Nest$fgetscrollToPositionOnRecreate(ChatViewer r0) {
        return r0.scrollToPositionOnRecreate;
    }

    /* renamed from: -$$Nest$fgetscrollingFloatingDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1383$$Nest$fgetscrollingFloatingDate(ChatViewer r0) {
        return r0.scrollingFloatingDate;
    }

    /* renamed from: -$$Nest$fgettheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity.ThemeDelegate m1384$$Nest$fgettheme(ChatViewer r0) {
        return r0.theme;
    }

    /* renamed from: -$$Nest$fgetundoView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UndoView m1385$$Nest$fgetundoView(ChatViewer r0) {
        return r0.undoView;
    }

    /* renamed from: -$$Nest$fputcheckTextureViewPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1386$$Nest$fputcheckTextureViewPosition(ChatViewer r0, boolean r1) {
        r0.checkTextureViewPosition = r1;
    }

    /* renamed from: -$$Nest$fputfloatingDateAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1387$$Nest$fputfloatingDateAnimation(ChatViewer r0, AnimatorSet r1) {
        r0.floatingDateAnimation = r1;
    }

    /* renamed from: -$$Nest$fputscrimPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1388$$Nest$fputscrimPopupWindow(ChatViewer r0, ActionBarPopupWindow r1) {
        r0.scrimPopupWindow = r1;
    }

    /* renamed from: -$$Nest$fputscrollToOffsetOnRecreate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1389$$Nest$fputscrollToOffsetOnRecreate(ChatViewer r0, int r1) {
        r0.scrollToOffsetOnRecreate = r1;
    }

    /* renamed from: -$$Nest$fputscrollToPositionOnRecreate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1390$$Nest$fputscrollToPositionOnRecreate(ChatViewer r0, int r1) {
        r0.scrollToPositionOnRecreate = r1;
    }

    /* renamed from: -$$Nest$fputscrollingFloatingDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1391$$Nest$fputscrollingFloatingDate(ChatViewer r0, boolean r1) {
        r0.scrollingFloatingDate = r1;
    }

    /* renamed from: -$$Nest$malertUserOpenError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1392$$Nest$malertUserOpenError(ChatViewer r0, MessageObject r1) {
        r0.alertUserOpenError(r1);
    }

    /* renamed from: -$$Nest$mcheckScrollForLoad, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1393$$Nest$mcheckScrollForLoad(ChatViewer r0, boolean r1) {
        r0.checkScrollForLoad(r1);
    }

    /* renamed from: -$$Nest$mcloseMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1394$$Nest$mcloseMenu(ChatViewer r0) {
        r0.closeMenu();
    }

    /* renamed from: -$$Nest$mcreateMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1395$$Nest$mcreateMenu(ChatViewer r0, View r1) {
        return r0.createMenu(r1);
    }

    /* renamed from: -$$Nest$mcreateTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m1396$$Nest$mcreateTextureView(ChatViewer r0, boolean r1) {
        return r0.createTextureView(r1);
    }

    /* renamed from: -$$Nest$mgetDialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m1397$$Nest$mgetDialogId(ChatViewer r2) {
        return r2.getDialogId();
    }

    /* renamed from: -$$Nest$mhideFloatingDateView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1398$$Nest$mhideFloatingDateView(ChatViewer r0, boolean r1) {
        r0.hideFloatingDateView(r1);
    }

    /* renamed from: -$$Nest$mupdateMessageAnimatedInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1399$$Nest$mupdateMessageAnimatedInternal(ChatViewer r0, MessageObject r1, boolean r2) {
        r0.updateMessageAnimatedInternal(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateMessagesVisiblePart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1400$$Nest$mupdateMessagesVisiblePart(ChatViewer r0) {
        r0.updateMessagesVisiblePart();
    }

    public ChatViewer(String r7) {
        this.chatMessageCellsCache = new ArrayList();
        this.notificationsLocker = new AnimationNotificationsLocker(new int[]{NotificationCenter.chatInfoDidLoad, NotificationCenter.dialogsNeedReload, NotificationCenter.closeChats, NotificationCenter.messagesDidLoad, NotificationCenter.botKeyboardDidLoad});
        this.messagesDict = new LongSparseArray();
        this.messagesByDays = new HashMap();
        this.messages = new ArrayList();
        this.provider = new AnonymousClass1(this);
        this.scrollToPositionOnRecreate = -1;
        this.scrollToOffsetOnRecreate = 0;
        this.paused = true;
        this.wasPaused = false;
        this.searchQuery = _UrlKt.FRAGMENT_ENCODE_SET;
        this.path = r7;
        this.theme = null;
        this.lastLoadedMsgFileId = null;
        this.chatInfo = (ExportMapper.ChatInfo) ExteraConfig.getGSON().fromJson(FileManager.readFileContent(new File(r7 + "/info.json")), ExportMapper.ChatInfo.class);
    }

    public static /* synthetic */ ActionBar access$000(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$100(ChatViewer r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$1000(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1100(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1200(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1300(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1400(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1500(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$1600(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1700(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1800(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1900(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$200(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$2000(ChatViewer r0) {
        return r0.classGuid;
    }

    public static /* synthetic */ int access$2100(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2200(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2300(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2400(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2500(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2600(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2700(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2800(ChatViewer r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$2900(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$3000(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$3100(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$400(ChatViewer r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$500(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$600(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$700(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$800(ChatViewer r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$900(ChatViewer r0) {
        return r0.actionBar;
    }

    private void alertUserOpenError(MessageObject r4) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.AppName));
        r0.setPositiveButton(LocaleController.getString(R.string.OK), null);
        if (r4.type != 3) goto L8;
        r0.setMessage(LocaleController.getString(R.string.NoPlayerInstalled));
    L9:
        showDialog(r0.create());
        return;
    L8:
        r0.setMessage(LocaleController.formatString(R.string.NoHandleAppInstalled, new Object[]{r4.getDocument().mime_type}));
        goto L9
    }

    private void checkScrollForLoad(boolean r4) {
        LinearLayoutManager r0 = this.chatLayoutManager;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.paused == true) goto L27;
        int r02 = r0.findFirstVisibleItemPosition();
        if (r02 != (-1)) goto L10;
        int r1 = 0;
    L11:
        if (r1 <= 0) goto L23;
        if (r4 == false) goto L14;
        int r42 = 25;
    L15:
        if (r02 <= r42) goto L17;
        return;
    L17:
        if (this.loading == false) goto L19;
        return;
    L19:
        if (this.endReached == true) goto L26;
        loadMessages(false);
        return;
    L26:
        return;
    L14:
        r42 = 5;
        goto L15
    L23:
        return;
    L10:
        r1 = Math.abs(this.chatLayoutManager.findLastVisibleItemPosition() - r02) + 1;
        goto L11
    }

    private void closeMenu() {
        ActionBarPopupWindow r0 = this.scrimPopupWindow;
        if (r0 == null) goto L6;
        r0.dismiss();
        return;
    }

    private boolean createMenu(View r2) {
        return createMenu(r2, 0.0f, 0.0f);
    }

    private boolean createMenu(View r17, float r18, float r19) {
        if ((r17 instanceof ChatMessageCell) == false) goto L6;
        MessageObject r11 = ((ChatMessageCell) r17).getMessageObject();
    L10:
        if (r11 != null) goto L13;
        return false;
    L13:
        if (r11.type != 10) goto L15;
        return false;
    L15:
        int r14 = getMessageType(r11);
        this.selectedObject = r11;
        if (getParentActivity() != null) goto L18;
        return false;
    L18:
        ArrayList r112 = new ArrayList();
        ArrayList r13 = new ArrayList();
        ArrayList r15 = new ArrayList();
        MessageObject r12 = this.selectedObject;
        if (r12.type != 0) goto L21;
    L22:
        r112.add(LocaleController.getString(R.string.Copy));
        r15.add(Integer.valueOf(R.drawable.msg_copy));
        r13.add(3);
    L23:
        if (r14 != 3) goto L29;
        TLRPC.MessageMedia r2 = this.selectedObject.messageOwner.media;
        if ((r2 instanceof TLRPC.TL_messageMediaWebPage) == true) goto L27;
    L72:
        r112.add(LocaleController.getString(R.string.Details));
        r13.add(Integer.valueOf(Opcodes.SUB_DOUBLE_2ADDR));
        r15.add(Integer.valueOf(R.drawable.msg_info));
        new ChatViewer$$ExternalSyntheticLambda3(this, r13, r112, r15, r17, r18, r19).run();
        return true;
    L27:
        if (MessageObject.isNewGifDocument(r2.webpage.document) == false) goto L72;
        r112.add(LocaleController.getString(R.string.SaveToGIFs));
        r15.add(Integer.valueOf(R.drawable.msg_gif));
        r13.add(11);
        goto L72
    L29:
        if (r14 == 4) goto L31;
        if (r14 != 5) goto L46;
        r112.add(LocaleController.getString(R.string.ApplyLocalizationFile));
        r15.add(Integer.valueOf(R.drawable.msg_language));
        r13.add(5);
        r112.add(LocaleController.getString(R.string.SaveToDownloads));
        r15.add(Integer.valueOf(R.drawable.msg_download));
        r13.add(10);
        r112.add(LocaleController.getString(R.string.ShareFile));
        r15.add(Integer.valueOf(R.drawable.msg_share));
        r13.add(6);
        goto L72
    L46:
        if (r14 != 10) goto L49;
        r112.add(LocaleController.getString(R.string.ApplyThemeFile));
        r15.add(Integer.valueOf(R.drawable.msg_theme));
        r13.add(5);
        r112.add(LocaleController.getString(R.string.SaveToDownloads));
        r15.add(Integer.valueOf(R.drawable.msg_download));
        r13.add(10);
        r112.add(LocaleController.getString(R.string.ShareFile));
        r15.add(Integer.valueOf(R.drawable.msg_share));
        r13.add(6);
        goto L72
    L49:
        if (r14 != 6) goto L51;
        r112.add(LocaleController.getString(R.string.SaveToGallery));
        r15.add(Integer.valueOf(R.drawable.msg_gallery));
        r13.add(7);
        r112.add(LocaleController.getString(R.string.SaveToDownloads));
        r15.add(Integer.valueOf(R.drawable.msg_download));
        r13.add(10);
        r112.add(LocaleController.getString(R.string.ShareFile));
        r15.add(Integer.valueOf(R.drawable.msg_share));
        r13.add(6);
        goto L72
    L51:
        if (r14 != 7) goto L58;
        if (this.selectedObject.isMask() == false) goto L55;
        r112.add(LocaleController.getString(R.string.AddToMasks));
    L56:
        r15.add(Integer.valueOf(R.drawable.msg_sticker));
        r13.add(9);
        goto L72
    L55:
        r112.add(LocaleController.getString(R.string.AddToStickers));
        goto L56
    L58:
        if (r14 != 8) goto L72;
        long r22 = this.selectedObject.messageOwner.media.user_id;
        if (r22 == 0) goto L62;
        TLRPC.User r122 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r22));
    L63:
        if (r122 == null) goto L70;
        if (r122.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L70;
        if (ContactsController.getInstance(this.currentAccount).contactsDict.get(Long.valueOf(r122.id)) != null) goto L70;
        r112.add(LocaleController.getString(R.string.AddContactTitle));
        r15.add(Integer.valueOf(R.drawable.msg_addcontact));
        r13.add(15);
    L70:
        if (TextUtils.isEmpty(this.selectedObject.messageOwner.media.phone_number) == true) goto L72;
        r112.add(LocaleController.getString(R.string.Copy));
        r15.add(Integer.valueOf(R.drawable.msg_copy));
        r13.add(16);
        r112.add(LocaleController.getString(R.string.Call));
        r15.add(Integer.valueOf(R.drawable.msg_calls));
        r13.add(17);
        goto L72
    L62:
        r122 = null;
        goto L63
    L31:
        if (this.selectedObject.isVideo() == false) goto L34;
        r112.add(LocaleController.getString(R.string.SaveToGallery));
        r15.add(Integer.valueOf(R.drawable.msg_gallery));
        r13.add(4);
        r112.add(LocaleController.getString(R.string.ShareFile));
        r15.add(Integer.valueOf(R.drawable.msg_share));
        r13.add(6);
        goto L72
    L34:
        if (this.selectedObject.isMusic() == false) goto L37;
        r112.add(LocaleController.getString(R.string.SaveToMusic));
        r15.add(Integer.valueOf(R.drawable.msg_download));
        r13.add(10);
        r112.add(LocaleController.getString(R.string.ShareFile));
        r15.add(Integer.valueOf(R.drawable.msg_share));
        r13.add(6);
        goto L72
    L37:
        if (this.selectedObject.getDocument() != null) goto L39;
        r112.add(LocaleController.getString(R.string.SaveToGallery));
        r15.add(Integer.valueOf(R.drawable.msg_gallery));
        r13.add(4);
        goto L72
    L39:
        if (MessageObject.isNewGifDocument(this.selectedObject.getDocument()) == false) goto L41;
        r112.add(LocaleController.getString(R.string.SaveToGIFs));
        r15.add(Integer.valueOf(R.drawable.msg_gif));
        r13.add(11);
    L41:
        r112.add(LocaleController.getString(R.string.SaveToDownloads));
        r15.add(Integer.valueOf(R.drawable.msg_download));
        r13.add(10);
        r112.add(LocaleController.getString(R.string.ShareFile));
        r15.add(Integer.valueOf(R.drawable.msg_share));
        r13.add(6);
        goto L72
    L21:
        if (r12.caption == null) goto L23;
    L6:
        if ((r17 instanceof ChatActionCell) == false) goto L8;
        r11 = ((ChatActionCell) r17).getMessageObject();
        goto L10
    L8:
        r11 = null;
        goto L10
    }

    private TextureView createTextureView(boolean r8) {
        if (this.parentLayout != null) goto L7;
        return null;
    L7:
        if (this.roundVideoContainer != null) goto L13;
        AnonymousClass12 r0 = new AnonymousClass12(this, getParentActivity());
        this.roundVideoContainer = r0;
        r0.setOutlineProvider(new AnonymousClass13(this));
        this.roundVideoContainer.setClipToOutline(true);
        this.roundVideoContainer.setWillNotDraw(false);
        this.roundVideoContainer.setVisibility(4);
        AspectRatioFrameLayout r02 = new AspectRatioFrameLayout(getParentActivity());
        this.aspectRatioFrameLayout = r02;
        r02.setBackgroundColor(0);
        if (r8 == false) goto L11;
        this.roundVideoContainer.addView(this.aspectRatioFrameLayout, LayoutHelper.createFrame(-1, -1.0f));
    L11:
        TextureView r82 = new TextureView(getParentActivity());
        this.videoTextureView = r82;
        r82.setOpaque(false);
        this.aspectRatioFrameLayout.addView(this.videoTextureView, LayoutHelper.createFrame(-1, -1.0f));
    L13:
        if (this.roundVideoContainer.getParent() != null) goto L15;
        SizeNotifierFrameLayout r83 = this.contentView;
        FrameLayout r03 = this.roundVideoContainer;
        int r5 = AndroidUtilities.roundMessageSize;
        r83.addView(r03, 1, new FrameLayout.LayoutParams(r5, r5));
    L15:
        this.roundVideoContainer.setVisibility(4);
        this.aspectRatioFrameLayout.setDrawingReady(false);
        return this.videoTextureView;
    }

    private void fillMessagesCount() {
        Utilities.globalQueue.postRunnable(new ChatViewer$$ExternalSyntheticLambda6(this));
    }

    private String getDateKey(MessageObject r5) {
        GregorianCalendar r4 = new GregorianCalendar();
        r4.setTimeInMillis(r5.messageOwner.date * 1000);
        int r52 = r4.get(6);
        return String.format("%d_%02d_%02d", new Object[]{Integer.valueOf(r4.get(1)), Integer.valueOf(r4.get(2)), Integer.valueOf(r52)});
    }

    private static TLObject getDialogFromAccountNumber(int r1, long r2) {
        TLObject r0 = MessagesController.getInstance(r1).getUserOrChat(r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        TLRPC.User r02 = MessagesStorage.getInstance(r1).getUserSync(r2);
        if (r02 == null) goto L8;
        return r02;
    L8:
        TLRPC.Chat r03 = MessagesStorage.getInstance(r1).getChatSync(r2);
        if (r03 == null) goto L12;
        return r03;
    L12:
        return MessagesStorage.getInstance(r1).getChatSync(Math.abs(r2));
    }

    private long getDialogId() {
        return this.chatInfo.id;
    }

    public static TLObject getDialogInAnyWay(long r2, Integer r4, boolean r5) {
        TLObject r0 = getDialogFromAccountNumber(r4.intValue(), r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        int r02 = 0;
    L7:
        if (r02 >= 16) goto L18;
        if (r02 == r4.intValue()) goto L17;
        if (UserConfig.isValidAccount(r02) == false) goto L17;
        TLObject r1 = getDialogFromAccountNumber(r02, r2);
        if (r1 == null) goto L17;
        return r1;
    L17:
        r02 = r02 + 1;
        goto L7
    L18:
        if (r5 == false) goto L21;
        TLRPC.TL_chat r42 = new TLRPC.TL_chat();
        r42.id = r2;
        r42.title = "Unknown (ID: " + r2 + ")";
        return r42;
    L21:
        return null;
    }

    public static QuadroResult getEntities(MessagesStorage r3, ArrayList<Long> r4, ArrayList<Long> r5) {
        ArrayList<TLRPC.User> r0 = new ArrayList();
        ArrayList<TLRPC.Chat> r1 = new ArrayList();
        if (r4.isEmpty() == true) goto L15;
        r3.getUsersInternal(r4, r0);     // Catch: Exception -> L11
    L15:
        if (r5.isEmpty() == true) goto L10;
        r3.getChatsInternal(TextUtils.join(",", r5), r1);     // Catch: Exception -> L12
    L10:
        return new QuadroResult(r0, r1);
    }

    private int getMessageType(MessageObject r7) {
        if (r7 != null) goto L5;
        return -1;
    L5:
        int r1 = r7.type;
        if (r1 != 6) goto L8;
        return -1;
    L8:
        boolean r4 = true;
        if (r1 == 10) goto L92;
        if (r1 == 11) goto L92;
        if (r1 == 16) goto L92;
        if (r7.isVoice() == false) goto L19;
        return 2;
    L19:
        if (r7.isSticker() == false) goto L21;
    L80:
        TLRPC.InputStickerSet r72 = r7.getInputStickerSet();
        if ((r72 instanceof TLRPC.TL_inputStickerSetID) == false) goto L86;
        if (MediaDataController.getInstance(this.currentAccount).isStickerPackInstalled(r72.id) == true) goto L90;
        return 7;
    L90:
        return 2;
    L86:
        if ((r72 instanceof TLRPC.TL_inputStickerSetShortName) == false) goto L90;
        if (MediaDataController.getInstance(this.currentAccount).isStickerPackInstalled(r72.short_name) == true) goto L90;
        return 7;
    L21:
        if (r7.isAnimatedSticker() == true) goto L80;
        if (r7.isRoundVideo() == false) goto L30;
        if (r7.isRoundVideo() == false) goto L39;
        if (BuildVars.DEBUG_VERSION == true) goto L30;
    L39:
        if (r7.type != 12) goto L43;
        return 8;
    L43:
        if (r7.isMediaEmpty() == false) goto L90;
        return 3;
    L30:
        if ((r7.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) == false) goto L32;
    L46:
        String r0 = r7.messageOwner.attachPath;
        if (r0 != null) goto L49;
    L53:
        boolean r02 = false;
    L54:
        if (r02 == false) goto L56;
    L58:
        r4 = r02;
    L59:
        if (r4 == false) goto L90;
        if (r7.getDocument() == null) goto L78;
        String r6 = r7.getDocument().mime_type;
        if (r6 != null) goto L65;
        return 4;
    L65:
        if (r7.getDocumentName().toLowerCase().endsWith("attheme") == false) goto L68;
        return 10;
    L68:
        if (r6.endsWith("/xml") == false) goto L72;
        return 5;
    L72:
        if (r6.endsWith("/png") == false) goto L74;
    L77:
        return 6;
    L74:
        if (r6.endsWith("/jpg") == true) goto L77;
        if (r6.endsWith("/jpeg") == true) goto L77;
        return 4;
    L78:
        return 4;
    L56:
        if (getFileLoader().getPathToMessage(r7.messageOwner).exists() == false) goto L58;
    L49:
        if (r0.length() == 0) goto L53;
        if (new File(r7.messageOwner.attachPath).exists() == false) goto L53;
        r02 = true;
        goto L54
    L32:
        if (r7.getDocument() != null) goto L46;
        if (r7.isMusic() == true) goto L46;
        if (r7.isVideo() == false) goto L39;
    L92:
        if (r7.getId() != 0) goto L94;
        return -1;
    L94:
        return 1;
    }

    private void hideFloatingDateView(boolean r6) {
        if (this.floatingDateView.getTag() != null) goto L5;
        return;
    L5:
        if (this.currentFloatingDateOnScreen == false) goto L7;
        return;
    L7:
        if (this.scrollingFloatingDate == true) goto L9;
    L10:
        this.floatingDateView.setTag(null);
        if (r6 == false) goto L14;
        AnimatorSet r62 = new AnimatorSet();
        this.floatingDateAnimation = r62;
        r62.setDuration(150);
        this.floatingDateAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.floatingDateView, "alpha", new float[]{0.0f})});
        this.floatingDateAnimation.addListener(new AnonymousClass14(this));
        this.floatingDateAnimation.setStartDelay(500);
        this.floatingDateAnimation.start();
        return;
    L14:
        AnimatorSet r63 = this.floatingDateAnimation;
        if (r63 == null) goto L17;
        r63.cancel();
        this.floatingDateAnimation = null;
    L17:
        this.floatingDateView.setAlpha(0.0f);
        return;
    L9:
        if (this.currentFloatingTopIsNotMessage == true) goto L10;
    }

    private /* synthetic */ void lambda$createMenu$7(int r1, ArrayList r2, Integer r3, ActionBarPopupWindow.ActionBarPopupWindowLayout r4, ActionBarMenuSubItem r5, View r6) {
        if (this.selectedObject != null) goto L5;
        return;
    L5:
        if (r1 >= r2.size()) goto L10;
        processSelectedOption(r3.intValue(), r2, r4, r5);
        return;
    }

    private /* synthetic */ void lambda$createMenu$8(ActionBarPopupWindow.ActionBarPopupWindowLayout r1, int r2, View r3) {
        if (this.selectedObject != null) goto L5;
        return;
    L5:
        if (getParentActivity() == null) goto L10;
        r1.getSwipeBack().openForeground(r2);
        return;
    }

    private /* synthetic */ void lambda$createMenu$9(ArrayList r20, ArrayList r21, ArrayList r22, View r23, float r24, float r25) {
        ArrayList r3 = r20;
        if (r3.isEmpty() == false) goto L5;
        return;
    L5:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r5 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(getParentActivity(), R.drawable.popup_fixed_alert, getResourceProvider(), 1);
        r5.setMinimumWidth(AndroidUtilities.dp(200.0f));
        Rect r9 = new Rect();
        getParentActivity().getResources().getDrawable(R.drawable.popup_fixed_alert).mutate().getPadding(r9);
        r5.setBackgroundColor(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
        int r10 = r21.size();
        int r11 = 0;
        int r2 = 0;
    L6:
        if (r2 >= r10) goto L22;
        if (r3.get(r2) != null) goto L10;
        r5.addView(new ActionBarPopupWindow.GapView(getContext(), getResourceProvider()), LayoutHelper.createLinear(-1, 8));
        int r14 = r2;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r6 = r5;
    L21:
        r2 = r14 + 1;
        r3 = r20;
        r5 = r6;
        goto L6
    L10:
        Activity r0 = getParentActivity();
        if (r2 != 0) goto L13;
        boolean r4 = true;
    L15:
        if (r2 != (r10 - 1)) goto L17;
        boolean r12 = true;
    L18:
        ActionBarMenuSubItem r62 = new ActionBarMenuSubItem(r0, r4, r12, getResourceProvider());
        r62.setMinimumWidth(AndroidUtilities.dp(200.0f));
        r62.setTextAndIcon((CharSequence) r21.get(r2), ((Integer) r22.get(r2)).intValue());
        Integer r42 = (Integer) r3.get(r2);
        r5.addView(r62);
        ChatViewer$$ExternalSyntheticLambda7 r02 = new ChatViewer$$ExternalSyntheticLambda7(this, r2, r3, r42, r5, r62);
        r14 = r2;
        r6 = r5;
        r62.setOnClickListener(r02);
        if (r42.intValue() != 204) goto L21;
        int r03 = r6.addViewToSwipeBack(new AnonymousClass9(this, this, r6.getSwipeBack(), this.selectedObject, getResourceProvider()).swipeBack);
        r62.setRightIcon(R.drawable.msg_arrowright);
        r62.setOnClickListener(new ChatViewer$$ExternalSyntheticLambda8(this, r6, r03));
        goto L21
    L17:
        r12 = false;
        goto L18
    L13:
        r4 = false;
        goto L15
    L22:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r63 = r5;
        AnonymousClass10 r04 = new AnonymousClass10(this, this.contentView.getContext());
        r04.addView(r63, LayoutHelper.createLinearRelatively(-2.0f, -2.0f, 3, 0.0f, 0.0f, 0.0f, 0.0f));
        r04.setPopupWindowLayout(r63);
        AnonymousClass11 r26 = new AnonymousClass11(this, r04, -2, -2);
        this.scrimPopupWindow = r26;
        r26.setPauseNotifications(true);
        this.scrimPopupWindow.setDismissAnimationDuration(Opcodes.REM_INT_LIT8);
        this.scrimPopupWindow.setOutsideTouchable(true);
        this.scrimPopupWindow.setClippingEnabled(true);
        this.scrimPopupWindow.setAnimationStyle(R.style.PopupContextAnimation);
        this.scrimPopupWindow.setFocusable(true);
        r04.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        this.scrimPopupWindow.setInputMethodMode(2);
        this.scrimPopupWindow.setSoftInputMode(48);
        this.scrimPopupWindow.getContentView().setFocusableInTouchMode(true);
        r63.setFitItems(true);
        int r27 = (((r23.getLeft() + ((int) r24)) - r04.getMeasuredWidth()) + r9.left) - AndroidUtilities.dp(28.0f);
        if (r27 >= AndroidUtilities.dp(6.0f)) goto L26;
        r27 = AndroidUtilities.dp(6.0f);
    L29:
        if (AndroidUtilities.isTablet() == false) goto L31;
        int[] r32 = new int[2];
        this.fragmentView.getLocationInWindow(r32);
        r27 = r27 + r32[0];
    L31:
        int r33 = this.contentView.getHeight();
        int r43 = r04.getMeasuredHeight() + AndroidUtilities.dp(48.0f);
        int r52 = this.contentView.measureKeyboardHeight();
        if (r52 <= AndroidUtilities.dp(20.0f)) goto L34;
        r33 = r33 + r52;
    L34:
        if (r43 >= r33) goto L45;
        int r53 = (int) ((this.chatListView.getY() + r23.getTop()) + r25);
        if (((r43 - r9.top) - r9.bottom) <= AndroidUtilities.dp(240.0f)) goto L39;
        r53 = r53 + (AndroidUtilities.dp(240.0f) - r43);
    L39:
        if (r53 >= (this.chatListView.getY() + AndroidUtilities.dp(24.0f))) goto L41;
        r53 = (int) (this.chatListView.getY() + AndroidUtilities.dp(24.0f));
    L49:
        r04.setMaxHeight(r33 - r53);
        this.scrimPopupWindow.showAtLocation(this.chatListView, 51, r27, r53);
        this.scrimPopupWindow.dimBehind();
        return;
    L41:
        int r44 = r33 - r43;
        if (r53 <= (r44 - AndroidUtilities.dp(8.0f))) goto L49;
        r53 = r44 - AndroidUtilities.dp(8.0f);
        goto L49
    L45:
        if (this.inBubbleMode == true) goto L48;
        r11 = AndroidUtilities.statusBarHeight;
    L48:
        r53 = r11;
        goto L49
    L26:
        if (r27 <= ((this.chatListView.getMeasuredWidth() - AndroidUtilities.dp(6.0f)) - r04.getMeasuredWidth())) goto L29;
        r27 = (this.chatListView.getMeasuredWidth() - AndroidUtilities.dp(6.0f)) - r04.getMeasuredWidth();
        goto L29
    }

    private /* synthetic */ void lambda$createView$6(View r1, int r2, float r3, float r4) {
        createMenu(r1, r3, r4);
    }

    private /* synthetic */ void lambda$fillMessagesCount$0() {
        ChatAvatarContainer r0 = this.avatarContainer;
        if (r0 == null) goto L6;
        r0.setSubtitle("msgs count: " + this.chatInfo.msgsCount);
        return;
    }

    private /* synthetic */ void lambda$fillMessagesCount$1() {
        AndroidUtilities.runOnUIThread(new ChatViewer$$ExternalSyntheticLambda9(this));
    }

    private /* synthetic */ void lambda$loadMessages$4(ArrayList r12) {
        int r1 = 0;
        this.chatListItemAnimator.setShouldAnimateEnterFromBottom(false);
        int r0 = this.messages.size();
        int r2 = r12.size();
        boolean r3 = false;
        int r4 = 0;
    L4:
        if (r4 >= r2) goto L21;
        Object r6 = r12.get(r4);
        r4 = r4 + 1;
        MessageObject r62 = (MessageObject) r6;
        if (this.messagesDict.indexOfKey(r62.messageOwner.id) >= 0) goto L4;
        this.minEventId = Math.min(this.minEventId, r62.messageOwner.id);
        if (r62.contentType < 0) goto L20;
        this.messagesDict.put(r62.messageOwner.id, r62);
        String r32 = getDateKey(r62);
        if (this.messagesByDays.get(r32) != null) goto L15;
        this.messagesByDays.put(r32, new ArrayList());
        TLRPC.TL_message r33 = new TLRPC.TL_message();
        r33.message = LocaleController.formatDateChat(r62.messageOwner.date);
        r33.id = 0;
        r33.date = r62.messageOwner.date;
        MessageObject r7 = new MessageObject(this.currentAccount, r33, false, false);
        r7.type = 10;
        r7.contentType = 1;
        r7.isDateObject = true;
        this.messages.add(r62);
        this.messages.add(r7);
        goto L20
    L15:
        if (this.messages.isEmpty() == true) goto L19;
        ArrayList<MessageObject> r34 = this.messages;
        if (r34.get(r34.size() - 1).isDateObject == false) goto L19;
        ArrayList<MessageObject> r35 = this.messages;
        MessageObject r36 = r35.get(r35.size() - 1);
        ArrayList<MessageObject> r72 = this.messages;
        r72.remove(r72.size() - 1);
        this.messages.add(r62);
        this.messages.add(r36);
    L19:
        this.messages.add(r62);
    L20:
        r3 = true;
        goto L4
    L21:
        int r122 = this.messages.size() - r0;
        this.loading = false;
        if (r3 == true) goto L24;
        this.endReached = true;
    L24:
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, false, 0.3f, true);
        this.chatListView.setEmptyView(this.emptyViewContainer);
        boolean r02 = this.endReached;
        if (r122 == 0) goto L40;
        if (r02 == false) goto L28;
        this.chatAdapter.notifyItemRangeChanged(0, 2);
        int r03 = 1;
    L29:
        int r22 = this.chatLayoutManager.findLastVisibleItemPosition();
        View r37 = this.chatLayoutManager.findViewByPosition(r22);
        if (r37 == null) goto L33;
        r1 = r37.getTop();
    L33:
        int r13 = r1 - this.chatListView.getPaddingTop();
        if ((r122 - r03) <= 0) goto L37;
        int r38 = (r03 ^ 1) + 1;
        this.chatAdapter.notifyItemChanged(r38);
        this.chatAdapter.notifyItemRangeInserted(r38, r122 - r03);
    L37:
        if (r22 == (-1)) goto L48;
        this.chatLayoutManager.scrollToPositionWithOffset((r22 + r122) - r03, r13);
        return;
    L48:
        return;
    L28:
        r03 = 0;
        goto L29
    L40:
        if (r02 == false) goto L49;
        this.chatAdapter.notifyItemRemoved(0);
        return;
    }

    private /* synthetic */ void lambda$processSelectedOption$10(ChatActivity r1, int r2) {
        finishFragment();
        r1.jumpToDate(r2);
    }

    private /* synthetic */ void lambda$showOpenUrlAlert$11(String r1, AlertDialog r2, int r3) {
        Browser.openUrl(getParentActivity(), r1, true);
    }

    private ArrayList<MessageObject> loadDeleted(int r12) {
        AtomicInteger r122 = this.lastLoadedMsgFileId;
        if (r122 != null) goto L5;
    L8:
        AtomicInteger r123 = this.lastLoadedMsgFileId;
        int r0 = 0;
        if (r123 == null) goto L11;
        int r124 = r123.decrementAndGet();
    L18:
        File r1 = new File(this.path + "/messages" + r124 + ".json");
        if (r1.length() == 0) goto L21;
        Log.d("exteraGram", "msg file size: " + r1.length());
        ArrayList<MessageObject> r125 = new ExportMapper(this.currentAccount, this.path, this.chatInfo).mapMessages((ExportMapper.JsonMessage[]) ExteraConfig.getGSON().fromJson(FileManager.readFileContent(r1), ExportMapper.JsonMessage[].class));
        MessagesController r13 = getMessagesController();
        MessagesStorage r2 = getMessagesStorage();
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        int r5 = r125.size();
        int r6 = 0;
    L23:
        if (r6 >= r5) goto L28;
        MessageObject r7 = r125.get(r6);
        r6 = r6 + 1;
        MessageObject r72 = r7;
        if (TextUtils.isEmpty(r72.messageOwner.message) == true) goto L23;
        MessagesStorage.addUsersAndChatsFromMessage(r72.messageOwner, r3, r4, null);
        goto L23
    L28:
        QuadroResult r22 = getEntities(r2, r3, r4);
        Pair<LongSparseArray<TLRPC.User>, LongSparseArray<TLRPC.Chat>> r32 = r22.getDicts();
        AndroidUtilities.runOnUIThread(new ChatViewer$$ExternalSyntheticLambda5(r22.getUsers(), r13, r22.getChats()));
        ArrayList<MessageObject> r14 = new ArrayList();
    L30:
        if (r0 >= r125.size()) goto L32;
        r14.add(new MessageObject(this.currentAccount, r125.get(r0).messageOwner, r32.first, r32.second, false, false));
        r0 = r0 + 1;
        goto L30
    L32:
        return r14;
    L21:
        return new ArrayList();
    L11:
        File[] r126 = new File(this.path).listFiles(new ChatViewer$$ExternalSyntheticLambda4());
        int r15 = r126.length;
        int r23 = Integer.MIN_VALUE;
        int r33 = 0;
    L12:
        if (r33 >= r15) goto L17;
        Matcher r42 = Pattern.compile("(\\d+)").matcher(r126[r33].getName());
        if (r42.find() == false) goto L16;
        r23 = Math.max(r23, Integer.parseInt(r42.group()));
    L16:
        r33 = r33 + 1;
        goto L12
    L17:
        this.lastLoadedMsgFileId = new AtomicInteger(r23);
        r124 = r23;
        goto L18
    L5:
        if (r122.get() != 0) goto L8;
        return new ArrayList();
    }

    private void loadMessages(boolean r4) {
        if (this.loading == false) goto L6;
        return;
    L6:
        if (r4 == false) goto L11;
        this.minEventId = LongCompanionObject.MAX_VALUE;
        FrameLayout r1 = this.progressView;
        if (r1 == null) goto L10;
        AndroidUtilities.updateViewVisibilityAnimated(r1, true, 0.3f, true);
        this.emptyViewContainer.setVisibility(4);
        this.chatListView.setEmptyView(null);
    L10:
        this.messagesDict.clear();
        this.messages.clear();
        this.messagesByDays.clear();
        fillMessagesCount();
    L11:
        this.loading = true;
        updateEmptyPlaceholder();
        if (r4 == false) goto L14;
    L16:
        int r0 = Integer.MAX_VALUE;
    L17:
        AndroidUtilities.runOnUIThread(new ChatViewer$$ExternalSyntheticLambda2(this, loadDeleted(r0)));
        if (r4 == false) goto L23;
        ChatActivityAdapter r3 = this.chatAdapter;
        if (r3 == null) goto L24;
        r3.notifyDataSetChanged();
        return;
    L24:
        return;
    L23:
        return;
    L14:
        if (this.messages.isEmpty() == true) goto L16;
        r0 = (int) this.minEventId;
        goto L17
    }

    private void processSelectedOption(int r14, ArrayList<Integer> r15, ActionBarPopupWindow.ActionBarPopupWindowLayout r16, ActionBarMenuSubItem r17) {
        MessageObject r3 = this.selectedObject;
        if (r3 == null) goto L170;
        int r4 = 0;
        int r6 = 2;
        switch(r14) {
            case 3: goto L157;
            case 4: goto L131;
            case 5: goto L91;
            case 6: goto L74;
            case 7: goto L54;
            case 8: goto L159;
            case 9: goto L53;
            case 10: goto L24;
            case 11: goto L22;
            case 12: goto L159;
            case 13: goto L159;
            case 14: goto L159;
            case 15: goto L21;
            case 16: goto L20;
            case 17: goto L167;
            case 18: goto L8;
            default: goto L159;
        };
    L8:
        int r0 = r3.messageOwner.date;
        INavigationLayout r1 = this.parentLayout;
        if (r1 != null) goto L11;
    L15:
        finishPreviewFragment();
        goto L159
    L11:
        if (r1.getFragmentStack().size() <= 1) goto L15;
        BaseFragment r12 = this.parentLayout.getFragmentStack().get(this.parentLayout.getFragmentStack().size() - 2);
        if ((r12 instanceof ChatActivity) == false) goto L15;
        AndroidUtilities.runOnUIThread(new ChatViewer$$ExternalSyntheticLambda11(this, (ChatActivity) r12, r0), 300);
        goto L15
    L20:
        AndroidUtilities.addToClipboard(r3.messageOwner.media.phone_number);
        BulletinFactory.of(this).createCopyBulletin(LocaleController.getString(R.string.PhoneCopied)).show();
        goto L159
    L21:
        Bundle r02 = new Bundle();
        r02.putLong("user_id", this.selectedObject.messageOwner.media.user_id);
        r02.putString("phone", this.selectedObject.messageOwner.media.phone_number);
        r02.putBoolean("addContact", true);
        presentFragment(new ContactAddActivity(r02));
        goto L159
    L22:
        MessagesController.getInstance(this.currentAccount).saveGif(this.selectedObject, r3.getDocument());
        goto L159
    L53:
        showDialog(new StickersAlert(getParentActivity(), this, this.selectedObject.getInputStickerSet(), null, null, false));
        goto L159
    L54:
        String r03 = r3.messageOwner.attachPath;
        if (r03 != null) goto L57;
    L61:
        if (r03 != null) goto L63;
    L64:
        r03 = getFileLoader().getPathToMessage(this.selectedObject.messageOwner).toString();
    L66:
        if (Build.VERSION.SDK_INT <= 28) goto L70;
        if (BuildVars.NO_SCOPED_STORAGE == true) goto L70;
    L73:
        MediaController.saveFile(r03, getParentActivity(), 0, null, null);
    L70:
        if (getParentActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) goto L73;
        getParentActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 4);
        this.selectedObject = null;
        return;
    L63:
        if (r03.length() != 0) goto L66;
    L57:
        if (r03.length() <= 0) goto L61;
        if (new File(r03).exists() == true) goto L61;
        r03 = null;
        goto L61
    L74:
        String r13 = r3.messageOwner.attachPath;
        if (r13 != null) goto L77;
    L81:
        if (r13 != null) goto L83;
    L84:
        r13 = getFileLoader().getPathToMessage(this.selectedObject.messageOwner).toString();
    L85:
        Intent r32 = new Intent("android.intent.action.SEND");
        r32.setType(this.selectedObject.getDocument().mime_type);
        r32.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(getParentActivity(), ApplicationLoader.getApplicationId() + ".provider", new File(r13)));     // Catch: Exception -> L88
        r32.setFlags(1);     // Catch: Exception -> L88
    L165:
        getParentActivity().startActivityForResult(Intent.createChooser(r32, LocaleController.getString(R.string.ShareFile)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L162
    L88:
        r32.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(r13)));
        goto L165
    L83:
        if (r13.length() != 0) goto L85;
    L77:
        if (r13.length() <= 0) goto L81;
        if (new File(r13).exists() == true) goto L81;
        r13 = null;
        goto L81
    L91:
        String r04 = r3.messageOwner.attachPath;
        if (r04 != null) goto L94;
    L98:
        File r05 = null;
    L99:
        if (r05 != null) goto L103;
        File r18 = getFileLoader().getPathToMessage(this.selectedObject.messageOwner);
        if (r18.exists() == false) goto L103;
        r05 = r18;
    L103:
        if (r05 == null) goto L159;
        if (r05.getName().toLowerCase().endsWith("attheme") == false) goto L124;
        LinearLayoutManager r19 = this.chatLayoutManager;
        if (r19 != null) goto L109;
    L115:
        Theme.ThemeInfo r06 = Theme.applyThemeFile(r05, this.selectedObject.getDocumentName(), null, true);
        if (r06 == null) goto L118;
        presentFragment(new ThemePreviewActivity(r06));
        goto L159
    L118:
        this.scrollToPositionOnRecreate = -1;
        if (getParentActivity() != null) goto L122;
        this.selectedObject = null;
        return;
    L122:
        AlertDialog.Builder r07 = new AlertDialog.Builder(getParentActivity());
        r07.setTitle(LocaleController.getString(R.string.AppName));
        r07.setMessage(LocaleController.getString(R.string.IncorrectTheme));
        r07.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showDialog(r07.create());
        goto L159
    L109:
        if (r19.findLastVisibleItemPosition() >= (this.chatLayoutManager.getItemCount() - 1)) goto L114;
        int r110 = this.chatLayoutManager.findFirstVisibleItemPosition();
        this.scrollToPositionOnRecreate = r110;
        RecyclerListView.Holder r111 = (RecyclerListView.Holder) this.chatListView.findViewHolderForAdapterPosition(r110);
        if (r111 == null) goto L113;
        this.scrollToOffsetOnRecreate = r111.itemView.getTop();
        goto L115
    L113:
        this.scrollToPositionOnRecreate = -1;
        goto L115
    L114:
        this.scrollToPositionOnRecreate = -1;
        goto L115
    L124:
        if (LocaleController.getInstance().applyLanguageFile(r05, this.currentAccount) == false) goto L127;
        presentFragment(new LanguageSelectActivity());
        goto L159
    L127:
        if (getParentActivity() != null) goto L130;
        this.selectedObject = null;
        return;
    L130:
        AlertDialog.Builder r08 = new AlertDialog.Builder(getParentActivity());
        r08.setTitle(LocaleController.getString(R.string.AppName));
        r08.setMessage(LocaleController.getString(R.string.IncorrectLocalization));
        r08.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showDialog(r08.create());
        goto L159
    L94:
        if (r04.length() == 0) goto L98;
        r05 = new File(this.selectedObject.messageOwner.attachPath);
        if (r05.exists() == false) goto L98;
    L131:
        String r09 = r3.messageOwner.attachPath;
        if (r09 != null) goto L134;
    L138:
        if (r09 != null) goto L140;
    L141:
        r09 = getFileLoader().getPathToMessage(this.selectedObject.messageOwner).toString();
    L142:
        int r112 = this.selectedObject.type;
        if (r112 == 3) goto L146;
        if (r112 != 1) goto L159;
    L146:
        if (Build.VERSION.SDK_INT <= 28) goto L150;
        if (BuildVars.NO_SCOPED_STORAGE == true) goto L150;
    L153:
        Activity r113 = getParentActivity();
        if (this.selectedObject.type != 3) goto L156;
        r4 = 1;
    L156:
        MediaController.saveFile(r09, r113, r4, null, null);
    L150:
        if (getParentActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) goto L153;
        getParentActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 4);
        this.selectedObject = null;
        return;
    L140:
        if (r09.length() != 0) goto L142;
    L134:
        if (r09.length() <= 0) goto L138;
        if (new File(r09).exists() == true) goto L138;
        r09 = null;
        goto L138
    L157:
        AndroidUtilities.addToClipboard(ChatUtils.getInstance().getMessageText(this.selectedObject, null));
        BulletinFactory.of(this).createCopyBulletin(LocaleController.getString(R.string.MessageCopied)).show();
        goto L159
    L167:
        Intent r010 = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + this.selectedObject.messageOwner.media.phone_number));     // Catch: Exception -> L18
        r010.addFlags(268435456);     // Catch: Exception -> L18
        getParentActivity().startActivityForResult(r010, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L18
    L18:
        e = move-exception;
        FileLog.e(e);
        goto L159
    L24:
        if (Build.VERSION.SDK_INT <= 28) goto L28;
        if (BuildVars.NO_SCOPED_STORAGE == true) goto L28;
    L31:
        String r011 = FileLoader.getDocumentFileName(this.selectedObject.getDocument());
        if (TextUtils.isEmpty(r011) == false) goto L34;
        r011 = this.selectedObject.getFileName();
    L34:
        String r114 = this.selectedObject.messageOwner.attachPath;
        if (r114 != null) goto L37;
    L41:
        if (r114 != null) goto L43;
    L44:
        r114 = getFileLoader().getPathToMessage(this.selectedObject.messageOwner).toString();
    L45:
        Activity r33 = getParentActivity();
        if (this.selectedObject.isMusic() == false) goto L49;
        r6 = 3;
    L49:
        if (this.selectedObject.getDocument() == null) goto L51;
        String r42 = this.selectedObject.getDocument().mime_type;
    L52:
        MediaController.saveFile(r114, r33, r6, r011, r42);
        goto L159
    L51:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L52
    L43:
        if (r114.length() != 0) goto L45;
    L37:
        if (r114.length() <= 0) goto L41;
        if (new File(r114).exists() == true) goto L41;
        r114 = null;
    L28:
        if (getParentActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) goto L31;
        getParentActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 4);
        this.selectedObject = null;
        return;
    L159:
        if (r14 == 204) goto L169;
        closeMenu();
        this.selectedObject = null;
        return;
    L169:
        return;
    }

    private void updateEmptyPlaceholder() {
        TextView r0 = this.emptyView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(5.0f));
        this.emptyView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.NoResult)));
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void updateMessageAnimatedInternal(MessageObject r4, boolean r5) {
        if (this.chatAdapter != null) goto L6;
        return;
    L6:
        if (this.fragmentView == null) goto L17;
        MessageObject r0 = this.messagesDict.get(r4.getId());
        if (r5 == false) goto L11;
        r4.forceUpdate = true;
        r4.reactionsChanged = true;
    L11:
        int r42 = this.messages.indexOf(r0);
        if (r42 < 0) goto L15;
        this.chatAdapter.notifyItemChanged((this.messages.size() - (r42 - ChatActivityAdapter.m1403$$Nest$fgetmessagesStartRow(this.chatAdapter))) - 1);
        return;
    L15:
        return;
    }

    private void updateMessagesVisiblePart() {
        RecyclerListView r1 = this.chatListView;
        if (r1 != null) goto L5;
        return;
    L5:
        int r12 = r1.getChildCount();
        int r2 = this.chatListView.getMeasuredHeight();
        int r3 = Integer.MAX_VALUE;
        boolean r5 = false;
        int r6 = Integer.MAX_VALUE;
        int r7 = 0;
        boolean r8 = false;
        View r9 = null;
        View r10 = null;
        View r11 = null;
    L7:
        if (r7 >= r12) goto L56;
        View r14 = this.chatListView.getChildAt(r7);
        if ((r14 instanceof ChatMessageCell) == false) goto L25;
        ChatMessageCell r16 = (ChatMessageCell) r14;
        int r15 = r16.getTop();
        r16.getBottom();
        if (r15 < 0) goto L13;
        int r17 = 0;
    L14:
        int r4 = r16.getMeasuredHeight();
        if (r4 <= r2) goto L17;
        r4 = r17 + r2;
    L17:
        r16.setVisiblePart(r17, r4 - r17, this.contentView.getHeightWithKeyboard() - this.chatListView.getTop(), 0.0f, (r14.getY() + this.actionBar.getMeasuredHeight()) - this.contentView.getBackgroundTranslationY(), this.contentView.getMeasuredWidth(), this.contentView.getBackgroundSizeY(), 0, 0, 0);
        r16.invalidate();
        MessageObject r42 = r16.getMessageObject();
        if (this.roundVideoContainer == null) goto L30;
        if (r42.isRoundVideo() == false) goto L30;
        if (MediaController.getInstance().isPlayingMessage(r42) == false) goto L30;
        ImageReceiver r43 = r16.getPhotoImage();
        this.roundVideoContainer.setTranslationX(r43.getImageX());
        this.roundVideoContainer.setTranslationY((this.fragmentView.getPaddingTop() + r15) + r43.getImageY());
        this.fragmentView.invalidate();
        this.roundVideoContainer.invalidate();
        r8 = true;
    L30:
        if (r14.getBottom() <= this.chatListView.getPaddingTop()) goto L55;
        int r44 = r14.getBottom();
        if (r44 < r3) goto L35;
    L40:
        ChatListItemAnimator r13 = this.chatListItemAnimator;
        if (r13 == null) goto L47;
        if (r13.willRemoved(r14) == true) goto L55;
        if (this.chatListItemAnimator.willAddedFromAlpha(r14) == true) goto L55;
    L47:
        if ((r14 instanceof ChatActionCell) == false) goto L55;
        if (((ChatActionCell) r14).getMessageObject().isDateObject == false) goto L55;
        if (r14.getAlpha() == 1.0f) goto L53;
        r14.setAlpha(1.0f);
    L53:
        if (r44 >= r6) goto L55;
        r6 = r44;
        r10 = r14;
        goto L55
    L35:
        if ((r14 instanceof ChatMessageCell) == false) goto L37;
    L38:
        r9 = r14;
    L39:
        r3 = r44;
        r11 = r14;
        goto L40
    L37:
        if ((r14 instanceof ChatActionCell) == false) goto L39;
    L55:
        r7 = r7 + 1;
        goto L7
    L13:
        r17 = -r15;
        goto L14
    L25:
        if ((r14 instanceof ChatActionCell) == false) goto L30;
        ChatActionCell r45 = (ChatActionCell) r14;
        r45.setVisiblePart((r14.getY() + this.actionBar.getMeasuredHeight()) - this.contentView.getBackgroundTranslationY(), this.contentView.getBackgroundSizeY());
        if (r45.hasGradientService() == false) goto L30;
        r45.invalidate();
        goto L30
    L56:
        FrameLayout r18 = this.roundVideoContainer;
        if (r18 == null) goto L67;
        if (r8 == true) goto L66;
        r18.setTranslationY((-AndroidUtilities.roundMessageSize) - 100);
        this.fragmentView.invalidate();
        MessageObject r19 = MediaController.getInstance().getPlayingMessageObject();
        if (r19 == null) goto L67;
        if (r19.isRoundVideo() == false) goto L67;
        if (this.checkTextureViewPosition == false) goto L67;
        MediaController.getInstance().setCurrentVideoVisible(false);
        goto L67
    L66:
        MediaController.getInstance().setCurrentVideoVisible(true);
    L67:
        if (r9 != null) goto L69;
    L73:
        this.currentFloatingDateOnScreen = false;
        if ((r11 instanceof ChatMessageCell) == false) goto L76;
    L78:
        this.currentFloatingTopIsNotMessage = r5;
        if (r10 != null) goto L81;
        hideFloatingDateView(true);
        this.floatingDateView.setTranslationY(0.0f);
        return;
    L81:
        if (r10.getTop() > this.chatListView.getPaddingTop()) goto L99;
        if (this.currentFloatingTopIsNotMessage == true) goto L99;
        if (r10.getAlpha() == 0.0f) goto L88;
        r10.setAlpha(0.0f);
    L88:
        AnimatorSet r22 = this.floatingDateAnimation;
        if (r22 == null) goto L92;
        r22.cancel();
        this.floatingDateAnimation = null;
    L92:
        if (this.floatingDateView.getTag() != null) goto L95;
        this.floatingDateView.setTag(1);
    L95:
        if (this.floatingDateView.getAlpha() == 1.0f) goto L97;
        this.floatingDateView.setAlpha(1.0f);
    L97:
        this.currentFloatingDateOnScreen = true;
    L102:
        int r23 = r10.getBottom() - this.chatListView.getPaddingTop();
        if (r23 > this.floatingDateView.getMeasuredHeight()) goto L105;
    L108:
        this.floatingDateView.setTranslationY(0.0f);
        return;
    L105:
        if (r23 >= (this.floatingDateView.getMeasuredHeight() * 2)) goto L108;
        this.floatingDateView.setTranslationY(((-r0.getMeasuredHeight()) * 2) + r23);
        return;
    L99:
        if (r10.getAlpha() == 1.0f) goto L101;
        r10.setAlpha(1.0f);
    L101:
        hideFloatingDateView(true ^ this.currentFloatingTopIsNotMessage);
        goto L102
    L76:
        if ((r11 instanceof ChatActionCell) == true) goto L78;
        r5 = true;
        goto L78
    L69:
        if ((r9 instanceof ChatMessageCell) == false) goto L71;
        MessageObject r110 = ((ChatMessageCell) r9).getMessageObject();
    L72:
        this.floatingDateView.setCustomDate(r110.messageOwner.date, false, true);
        goto L73
    L71:
        r110 = ((ChatActionCell) r9).getMessageObject();
        goto L72
    }

    private void updateTextureViewPosition() {
        int r0 = this.chatListView.getChildCount();
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L15;
        View r4 = this.chatListView.getChildAt(r2);
        if ((r4 instanceof ChatMessageCell) == false) goto L14;
        ChatMessageCell r42 = (ChatMessageCell) r4;
        MessageObject r5 = r42.getMessageObject();
        if (this.roundVideoContainer == null) goto L14;
        if (r5.isRoundVideo() == false) goto L14;
        if (MediaController.getInstance().isPlayingMessage(r5) == false) goto L14;
        ImageReceiver r02 = r42.getPhotoImage();
        this.roundVideoContainer.setTranslationX(r02.getImageX());
        this.roundVideoContainer.setTranslationY((this.fragmentView.getPaddingTop() + r42.getTop()) + r02.getImageY());
        this.fragmentView.invalidate();
        this.roundVideoContainer.invalidate();
        boolean r03 = true;
    L17:
        if (this.roundVideoContainer == null) goto L38;
        MessageObject r22 = MediaController.getInstance().getPlayingMessageObject();
        if (r03 == true) goto L30;
        this.roundVideoContainer.setTranslationY((-AndroidUtilities.roundMessageSize) - 100);
        this.fragmentView.invalidate();
        if (r22 != null) goto L23;
        return;
    L23:
        if (r22.isRoundVideo() == true) goto L25;
        return;
    L25:
        if (this.checkTextureViewPosition == false) goto L27;
    L28:
        MediaController.getInstance().setCurrentVideoVisible(false);
        return;
    L27:
        if (PipRoundVideoView.getInstance() != null) goto L28;
        return;
    L30:
        MediaController.getInstance().setCurrentVideoVisible(true);
        return;
    L38:
        return;
    L14:
        r2 = r2 + 1;
        goto L4
    L15:
        r03 = false;
        goto L17
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r21) {
        this.sharedResources = new ChatMessageSharedResources(r21);
        if (this.chatMessageCellsCache.isEmpty() == false) goto L8;
        int r0 = 0;
    L6:
        if (r0 >= 8) goto L8;
        this.chatMessageCellsCache.add(new ChatMessageCell(r21, this.currentAccount));
        r0 = r0 + 1;
    L8:
        this.searchWas = false;
        this.hasOwnBackground = true;
        Theme.createChatResources(r21, false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setOccupyStatusBar(!AndroidUtilities.isTablet());
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        AnonymousClass3 r02 = new AnonymousClass3(this, r21, null, false, this.theme);
        this.avatarContainer = r02;
        r02.setOccupyStatusBar(!AndroidUtilities.isTablet());
        this.avatarContainer.setEnabled(false);
        this.actionBar.addView(this.avatarContainer, 0, LayoutHelper.createFrame(-2, -1.0f, 51, 56.0f, 0.0f, 40.0f, 0.0f));
        TLObject r03 = getDialogInAnyWay(this.chatInfo.id, Integer.valueOf(UserConfig.selectedAccount), true);
        if ((r03 instanceof TLRPC.User) == false) goto L12;
        this.avatarContainer.setTitle(this.chatInfo.name);
        fillMessagesCount();
        this.avatarContainer.setUserAvatar((TLRPC.User) r03);
    L14:
        AnonymousClass4 r04 = new AnonymousClass4(this, r21);
        this.fragmentView = r04;
        AnonymousClass4 r05 = r04;
        this.contentView = r05;
        r05.setOccupyStatusBar(!AndroidUtilities.isTablet());
        this.contentView.setBackgroundImage(Theme.getCachedWallpaper(), Theme.isWallpaperMotion());
        FrameLayout r06 = new FrameLayout(r21);
        this.emptyViewContainer = r06;
        r06.setVisibility(4);
        this.contentView.addView(this.emptyViewContainer, LayoutHelper.createFrame(-1, -2, 17));
        this.emptyViewContainer.setOnTouchListener(new ChatViewer$$ExternalSyntheticLambda0());
        TextView r07 = new TextView(r21);
        this.emptyView = r07;
        r07.setTextSize(1, 14.0f);
        this.emptyView.setGravity(17);
        TextView r08 = this.emptyView;
        int r4 = Theme.key_chat_serviceText;
        r08.setTextColor(getThemedColor(r4));
        this.emptyView.setBackground(Theme.createServiceDrawable(AndroidUtilities.dp(6.0f), this.emptyView, this.contentView));
        this.emptyView.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        this.emptyViewContainer.addView(this.emptyView, LayoutHelper.createFrame(-2, -2.0f, 17, 16.0f, 0.0f, 16.0f, 0.0f));
        AnonymousClass5 r09 = new AnonymousClass5(this, r21, this.theme);
        this.chatListView = r09;
        r09.setOnItemClickListener(new ChatViewer$$ExternalSyntheticLambda1(this));
        this.chatListView.setTag(1);
        this.chatListView.setVerticalScrollBarEnabled(true);
        RecyclerListView r010 = this.chatListView;
        ChatActivityAdapter r5 = new ChatActivityAdapter(this, r21);
        this.chatAdapter = r5;
        r010.setAdapter(r5);
        this.chatListView.setClipToPadding(false);
        this.chatListView.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(3.0f));
        RecyclerListView r011 = this.chatListView;
        AnonymousClass6 r52 = new AnonymousClass6(this, null, this.chatListView, this.theme);
        this.chatListItemAnimator = r52;
        r011.setItemAnimator(r52);
        this.chatListItemAnimator.setReversePositions(true);
        this.chatListView.setLayoutAnimation(null);
        AnonymousClass7 r012 = new AnonymousClass7(this, r21);
        this.chatLayoutManager = r012;
        r012.setOrientation(1);
        this.chatLayoutManager.setStackFromEnd(true);
        this.chatListView.setLayoutManager(this.chatLayoutManager);
        this.contentView.addView(this.chatListView, LayoutHelper.createFrame(-1, -1.0f));
        this.chatListView.setOnScrollListener(new AnonymousClass8(this));
        int r013 = this.scrollToPositionOnRecreate;
        if (r013 == (-1)) goto L17;
        this.chatLayoutManager.scrollToPositionWithOffset(r013, this.scrollToOffsetOnRecreate);
        this.scrollToPositionOnRecreate = -1;
    L17:
        FrameLayout r014 = new FrameLayout(r21);
        this.progressView = r014;
        r014.setVisibility(4);
        this.contentView.addView(this.progressView, LayoutHelper.createFrame(-1, -1, 51));
        View r015 = new View(r21);
        this.progressView2 = r015;
        r015.setBackground(Theme.createServiceDrawable(AndroidUtilities.dp(18.0f), this.progressView2, this.contentView));
        this.progressView.addView(this.progressView2, LayoutHelper.createFrame(36, 36, 17));
        RadialProgressView r016 = new RadialProgressView(r21, this.theme);
        this.progressBar = r016;
        r016.setSize(AndroidUtilities.dp(28.0f));
        this.progressBar.setProgressColor(getThemedColor(r4));
        this.progressView.addView(this.progressBar, LayoutHelper.createFrame(32, 32, 17));
        ChatActionCell r017 = new ChatActionCell(r21, false, this.theme);
        this.floatingDateView = r017;
        r017.setAlpha(0.0f);
        this.floatingDateView.setImportantForAccessibility(2);
        this.contentView.addView(this.floatingDateView, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 4.0f, 0.0f, 0.0f));
        this.contentView.addView(this.actionBar);
        this.chatAdapter.updateRows();
        if (this.loading == true) goto L20;
    L22:
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, false, 0.3f, true);
        this.chatListView.setEmptyView(this.emptyViewContainer);
    L23:
        this.chatListView.setAnimateEmptyView(true, 1);
        UndoView r018 = new UndoView(r21, null, false, this.theme);
        this.undoView = r018;
        this.contentView.addView(r018, LayoutHelper.createFrame(-1, -2.0f, 83, 8.0f, 0.0f, 8.0f, 8.0f));
        updateEmptyPlaceholder();
        return this.fragmentView;
    L20:
        if (this.messages.isEmpty() == false) goto L22;
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, true, 0.3f, true);
        this.chatListView.setEmptyView(null);
        goto L23
    L12:
        if ((r03 instanceof TLRPC.Chat) == false) goto L14;
        this.avatarContainer.setTitle(this.chatInfo.name);
        fillMessagesCount();
        this.avatarContainer.setChatAvatar((TLRPC.Chat) r03);
        goto L14
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r7, int r8, Object... r9) {
        if (r7 != NotificationCenter.emojiLoaded) goto L8;
        RecyclerListView r6 = this.chatListView;
        if (r6 == null) goto L86;
        r6.invalidateViews();
        return;
    L86:
        return;
    L8:
        int r2 = 0;
        if (r7 != NotificationCenter.messagePlayingDidStart) goto L36;
        if (((MessageObject) r9[0]).isRoundVideo() == false) goto L13;
        MediaController.getInstance().setTextureView(createTextureView(true), this.aspectRatioFrameLayout, this.roundVideoContainer, true);
        updateTextureViewPosition();
    L13:
        RecyclerListView r72 = this.chatListView;
        if (r72 == null) goto L107;
        int r73 = r72.getChildCount();
        int r82 = 0;
    L16:
        if (r82 >= r73) goto L113;
        View r92 = this.chatListView.getChildAt(r82);
        if ((r92 instanceof ChatMessageCell) == false) goto L34;
        ChatMessageCell r93 = (ChatMessageCell) r92;
        MessageObject r3 = r93.getMessageObject();
        if (r3 == null) goto L34;
        if (r3.isVoice() == false) goto L24;
    L33:
        r93.updateButtonState(false, true, false);
        goto L34
    L24:
        if (r3.isMusic() == true) goto L33;
        if (r3.isRoundVideo() == false) goto L34;
        r93.checkVideoPlayback(false, null);
        if (MediaController.getInstance().isPlayingMessage(r3) == true) goto L34;
        if (r3.audioProgress == 0.0f) goto L34;
        r3.resetPlayingProgress();
        r93.invalidate();
    L34:
        r82 = r82 + 1;
        goto L16
    L113:
        return;
    L107:
        return;
    L36:
        if (r7 != NotificationCenter.messagePlayingDidReset) goto L38;
    L66:
        RecyclerListView r74 = this.chatListView;
        if (r74 == null) goto L112;
        int r75 = r74.getChildCount();
        int r83 = 0;
    L69:
        if (r83 >= r75) goto L115;
        View r94 = this.chatListView.getChildAt(r83);
        if ((r94 instanceof ChatMessageCell) == false) goto L85;
        ChatMessageCell r95 = (ChatMessageCell) r94;
        MessageObject r32 = r95.getMessageObject();
        if (r32 == null) goto L85;
        if (r32.isVoice() == false) goto L77;
    L84:
        r95.updateButtonState(false, true, false);
        goto L85
    L77:
        if (r32.isMusic() == true) goto L84;
        if (r32.isRoundVideo() == false) goto L85;
        if (MediaController.getInstance().isPlayingMessage(r32) == true) goto L85;
        r95.checkVideoPlayback(true, null);
    L85:
        r83 = r83 + 1;
        goto L69
    L115:
        return;
    L112:
        return;
    L38:
        if (r7 == NotificationCenter.messagePlayingPlayStateChanged) goto L66;
        if (r7 != NotificationCenter.messagePlayingProgressDidChanged) goto L58;
        Integer r76 = (Integer) r9[0];
        RecyclerListView r84 = this.chatListView;
        if (r84 == null) goto L108;
        int r85 = r84.getChildCount();
    L45:
        if (r2 >= r85) goto L114;
        View r96 = this.chatListView.getChildAt(r2);
        if ((r96 instanceof ChatMessageCell) == false) goto L56;
        ChatMessageCell r97 = (ChatMessageCell) r96;
        MessageObject r0 = r97.getMessageObject();
        if (r0 == null) goto L56;
        if (r0.getId() != r76.intValue()) goto L56;
        MessageObject r62 = MediaController.getInstance().getPlayingMessageObject();
        if (r62 == null) goto L109;
        r0.audioProgress = r62.audioProgress;
        r0.audioProgressSec = r62.audioProgressSec;
        r0.audioPlayerDuration = r62.audioPlayerDuration;
        r97.updatePlayingMessageProgress();
        return;
    L109:
        return;
    L56:
        r2 = r2 + 1;
        goto L45
    L114:
        return;
    L108:
        return;
    L58:
        if (r7 == NotificationCenter.didSetNewWallpapper) goto L60;
        return;
    L60:
        if (this.fragmentView == null) goto L111;
        this.contentView.setBackgroundImage(Theme.getCachedWallpaper(), Theme.isWallpaperMotion());
        this.progressView2.invalidate();
        TextView r77 = this.emptyView;
        if (r77 == null) goto L64;
        r77.invalidate();
    L64:
        this.chatListView.invalidateViews();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getNavigationBarColor() {
        return getThemedColor(Theme.key_chat_messagePanelBackground);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getThemedColor(int r1) {
        return Theme.getColor(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyHidden() {
        UndoView r2 = this.undoView;
        if (r2 == null) goto L6;
        r2.hide(true, 0);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onConfigurationChanged(Configuration r1) {
        Dialog r0 = this.visibleDialog;
        if ((r0 instanceof DatePickerDialog) == false) goto L6;
        r0.dismiss();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewWallpapper);
        loadMessages(true);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewWallpapper);
        this.notificationsLocker.unlock();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        SizeNotifierFrameLayout r0 = this.contentView;
        if (r0 == null) goto L5;
        r0.onPause();
    L5:
        UndoView r02 = this.undoView;
        if (r02 == null) goto L8;
        r02.hide(true, 0);
    L8:
        this.paused = true;
        this.wasPaused = true;
        if (AvatarPreviewer.hasVisibleInstance() == false) goto L12;
        AvatarPreviewer.getInstance().close();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRemoveFromParent() {
        MediaController.getInstance().setTextureView(this.videoTextureView, null, null, false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        SizeNotifierFrameLayout r0 = this.contentView;
        if (r0 == null) goto L5;
        r0.onResume();
    L5:
        this.paused = false;
        checkScrollForLoad(false);
        if (this.wasPaused == false) goto L11;
        this.wasPaused = false;
        ChatActivityAdapter r2 = this.chatAdapter;
        if (r2 == null) goto L12;
        r2.notifyDataSetChanged();
        return;
    L12:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == false) goto L5;
        this.notificationsLocker.unlock();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r1, boolean r2) {
        if (r1 == false) goto L5;
        this.notificationsLocker.lock();
        return;
    }

    public void openVCard(TLRPC.User r10, String r11, String r12, String r13) {
        File r0 = AndroidUtilities.getSharingDirectory();     // Catch: Exception -> L4
        r0.mkdirs();     // Catch: Exception -> L4
        File r6 = new File(r0, "vcard.vcf");     // Catch: Exception -> L4
        BufferedWriter r02 = new BufferedWriter(new FileWriter(r6));     // Catch: Exception -> L4
        r02.write(r11);     // Catch: Exception -> L4
        r02.close();     // Catch: Exception -> L4
        showDialog(new PhonebookShareAlert(this, null, r10, null, r6, r12, r13));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public void showOpenUrlAlert(String r4, boolean r5) {
        if (Browser.isInternalUrl(r4, null) == true) goto L8;
        if (r5 == false) goto L8;
        AlertDialog.Builder r52 = new AlertDialog.Builder(getParentActivity());
        r52.setTitle(LocaleController.getString(R.string.OpenUrlTitle));
        r52.setMessage(LocaleController.formatString(R.string.OpenUrlAlert2, new Object[]{r4}));
        r52.setPositiveButton(LocaleController.getString(R.string.Open), new ChatViewer$$ExternalSyntheticLambda10(this, r4));
        r52.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r52.create());
        return;
    L8:
        Browser.openUrl(getParentActivity(), r4, true);
    }
}
