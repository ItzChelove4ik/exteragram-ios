package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.WebFile;
import org.telegram.messenger.utils.tlutils.TLKeyboardHelper;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PlayingGameDrawable;
import org.telegram.ui.Components.PopupAudioView;
import org.telegram.ui.Components.RecordStatusDrawable;
import org.telegram.ui.Components.RoundStatusDrawable;
import org.telegram.ui.Components.SendingFileDrawable;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.StatusDrawable;
import org.telegram.ui.Components.TypingDotsDrawable;

/* loaded from: classes6.dex */
public class PopupNotificationActivity extends Activity implements NotificationCenter.NotificationCenterDelegate {
    private ActionBar actionBar;
    private boolean animationInProgress;
    private long animationStartTime;
    private ArrayList<ViewGroup> audioViews;
    private FrameLayout avatarContainer;
    private BackupImageView avatarImageView;
    private ViewGroup centerButtonsView;
    private ViewGroup centerView;
    private ChatActivityEnterView chatActivityEnterView;
    private int classGuid;
    private TextView countText;
    private TLRPC.Chat currentChat;
    private int currentMessageNum;
    private MessageObject currentMessageObject;
    private TLRPC.User currentUser;
    private boolean finished;
    private ArrayList<ViewGroup> imageViews;
    private boolean isReply;
    private CharSequence lastPrintString;
    private int lastResumedAccount;
    private ViewGroup leftButtonsView;
    private ViewGroup leftView;
    private ViewGroup messageContainer;
    private float moveStartX;
    private TextView nameTextView;
    private Runnable onAnimationEndRunnable;
    private TextView onlineTextView;
    private RelativeLayout popupContainer;
    private ArrayList<MessageObject> popupMessages;
    private ViewGroup rightButtonsView;
    private ViewGroup rightView;
    private MessageObject[] setMessageObjects;
    private boolean startedMoving;
    private StatusDrawable[] statusDrawables;
    private ArrayList<ViewGroup> textViews;
    private VelocityTracker velocityTracker;
    private PowerManager.WakeLock wakeLock;

    /* renamed from: org.telegram.ui.PopupNotificationActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends SizeNotifierFrameLayout {
        final /* synthetic */ PopupNotificationActivity this$0;

        public AnonymousClass1(PopupNotificationActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
            int r102 = getChildCount();
            int r2 = 0;
            if (measureKeyboardHeight() > AndroidUtilities.dp(20.0f)) goto L5;
            int r0 = PopupNotificationActivity.m17560$$Nest$fgetchatActivityEnterView(this.this$0).getEmojiPadding();
        L6:
            if (r2 >= r102) goto L40;
            View r1 = getChildAt(r2);
            if (r1.getVisibility() == 8) goto L39;
            FrameLayout.LayoutParams r3 = (FrameLayout.LayoutParams) r1.getLayoutParams();
            int r4 = r1.getMeasuredWidth();
            int r5 = r1.getMeasuredHeight();
            int r6 = r3.gravity;
            if (r6 != (-1)) goto L13;
            r6 = 51;
        L13:
            int r7 = r6 & 112;
            int r62 = r6 & 7;
            if (r62 != 1) goto L16;
            int r63 = (((r13 - r11) - r4) / 2) + r3.leftMargin;
            int r8 = r3.rightMargin;
        L19:
            int r64 = r63 - r8;
        L22:
            if (r7 != 16) goto L24;
            int r72 = ((((r14 - r0) - r12) - r5) / 2) + r3.topMargin;
            int r82 = r3.bottomMargin;
        L27:
            int r73 = r72 - r82;
        L30:
            if (PopupNotificationActivity.m17560$$Nest$fgetchatActivityEnterView(this.this$0).isPopupView(r1) == false) goto L36;
            int r32 = getMeasuredHeight();
            if (r0 == 0) goto L34;
            r32 = r32 - r0;
        L34:
            r73 = r32;
        L38:
            r1.layout(r64, r73, r4 + r64, r5 + r73);
            goto L39
        L36:
            if (PopupNotificationActivity.m17560$$Nest$fgetchatActivityEnterView(this.this$0).isRecordCircle(r1) == false) goto L38;
            r73 = ((PopupNotificationActivity.m17566$$Nest$fgetpopupContainer(this.this$0).getTop() + PopupNotificationActivity.m17566$$Nest$fgetpopupContainer(this.this$0).getMeasuredHeight()) - r1.getMeasuredHeight()) - r3.bottomMargin;
            r64 = ((PopupNotificationActivity.m17566$$Nest$fgetpopupContainer(this.this$0).getLeft() + PopupNotificationActivity.m17566$$Nest$fgetpopupContainer(this.this$0).getMeasuredWidth()) - r1.getMeasuredWidth()) - r3.rightMargin;
            goto L38
        L24:
            if (r7 == 80) goto L26;
            r73 = r3.topMargin;
            goto L30
        L26:
            r72 = ((r14 - r0) - r12) - r5;
            r82 = r3.bottomMargin;
            goto L27
        L16:
            if (r62 == 5) goto L18;
            r64 = r3.leftMargin;
            goto L22
        L18:
            r63 = r13 - r4;
            r8 = r3.rightMargin;
        L39:
            r2 = r2 + 1;
            goto L6
        L40:
            notifyHeightChanged();
            return;
        L5:
            r0 = 0;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r11, int r12) {
            View.MeasureSpec.getMode(r11);
            View.MeasureSpec.getMode(r12);
            int r0 = View.MeasureSpec.getSize(r11);
            int r1 = View.MeasureSpec.getSize(r12);
            setMeasuredDimension(r0, r1);
            if (measureKeyboardHeight() > AndroidUtilities.dp(20.0f)) goto L5;
            r1 = r1 - PopupNotificationActivity.m17560$$Nest$fgetchatActivityEnterView(this.this$0).getEmojiPadding();
        L5:
            int r2 = getChildCount();
            int r3 = 0;
        L6:
            if (r3 >= r2) goto L18;
            View r5 = this.getChildAt(r3);
            if (r5.getVisibility() != 8) goto L11;
        L9:
            AnonymousClass1 r4 = this;
            int r10 = r11;
            int r8 = r12;
        L17:
            r3 = r3 + 1;
            r11 = r10;
            AnonymousClass1 this = r4;
            r12 = r8;
            goto L6
        L11:
            if (PopupNotificationActivity.m17560$$Nest$fgetchatActivityEnterView(this.this$0).isPopupView(r5) == false) goto L14;
            r5.measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r5.getLayoutParams().height, TLObject.FLAG_30));
            goto L9
        L14:
            if (PopupNotificationActivity.m17560$$Nest$fgetchatActivityEnterView(this.this$0).isRecordCircle(r5) == false) goto L16;
            r4 = this;
            int r6 = r11;
            r8 = r12;
            r4.measureChildWithMargins(r5, r6, 0, r8, 0);
            r10 = r6;
            goto L17
        L16:
            r4 = this;
            r10 = r11;
            r8 = r12;
            r5.measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(Math.max(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(2.0f) + r1), TLObject.FLAG_30));
            goto L17
        }
    }

    /* renamed from: org.telegram.ui.PopupNotificationActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends RelativeLayout {
        final /* synthetic */ PopupNotificationActivity this$0;

        public AnonymousClass2(PopupNotificationActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            super.onLayout(r2, r3, r4, r5, r6);
            int r22 = 0;
        L4:
            if (r22 >= getChildCount()) goto L9;
            View r32 = getChildAt(r22);
            if ((r32.getTag() instanceof String) == false) goto L8;
            r32.layout(r32.getLeft(), PopupNotificationActivity.m17560$$Nest$fgetchatActivityEnterView(this.this$0).getTop() + AndroidUtilities.dp(3.0f), r32.getRight(), PopupNotificationActivity.m17560$$Nest$fgetchatActivityEnterView(this.this$0).getBottom());
        L8:
            r22 = r22 + 1;
            goto L4
        }

        @Override // android.widget.RelativeLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            super.onMeasure(r6, r7);
            int r62 = PopupNotificationActivity.m17560$$Nest$fgetchatActivityEnterView(this.this$0).getMeasuredWidth();
            int r72 = PopupNotificationActivity.m17560$$Nest$fgetchatActivityEnterView(this.this$0).getMeasuredHeight();
            int r0 = 0;
        L4:
            if (r0 >= getChildCount()) goto L9;
            View r1 = getChildAt(r0);
            if ((r1.getTag() instanceof String) == false) goto L8;
            r1.measure(View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r72 - AndroidUtilities.dp(3.0f), TLObject.FLAG_30));
        L8:
            r0 = r0 + 1;
            goto L4
        }
    }

    /* renamed from: org.telegram.ui.PopupNotificationActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements ChatActivityEnterView.ChatActivityEnterViewDelegate {
        final /* synthetic */ PopupNotificationActivity this$0;

        public AnonymousClass3(PopupNotificationActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void didPressAttachButton() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public boolean isVideoRecordingPaused() {
            return false;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needChangeVideoPreviewState(int r1, float r2) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needSendTyping() {
            if (PopupNotificationActivity.m17563$$Nest$fgetcurrentMessageObject(this.this$0) == null) goto L6;
            MessagesController.getInstance(PopupNotificationActivity.m17563$$Nest$fgetcurrentMessageObject(this.this$0).currentAccount).sendTyping(PopupNotificationActivity.m17563$$Nest$fgetcurrentMessageObject(this.this$0).getDialogId(), 0, 0, PopupNotificationActivity.m17561$$Nest$fgetclassGuid(this.this$0));
            return;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needShowMediaBanHint() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needStartRecordAudio(int r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needStartRecordVideo(int r1, boolean r2, int r3, int r4, int r5, long r6, long r8) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onAttachButtonHidden() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onAttachButtonShow() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onAudioVideoInterfaceUpdated() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onMessageEditEnd(boolean r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onMessageSend(CharSequence r13, boolean r14, int r15, int r16, long r17) {
            if (PopupNotificationActivity.m17563$$Nest$fgetcurrentMessageObject(this.this$0) != null) goto L6;
            return;
        L6:
            if (PopupNotificationActivity.m17562$$Nest$fgetcurrentMessageNum(this.this$0) >= 0) goto L8;
        L10:
            MessagesController.getInstance(PopupNotificationActivity.m17563$$Nest$fgetcurrentMessageObject(this.this$0).currentAccount).markDialogAsRead(PopupNotificationActivity.m17563$$Nest$fgetcurrentMessageObject(this.this$0).getDialogId(), PopupNotificationActivity.m17563$$Nest$fgetcurrentMessageObject(this.this$0).getId(), Math.max(0, PopupNotificationActivity.m17563$$Nest$fgetcurrentMessageObject(this.this$0).getId()), PopupNotificationActivity.m17563$$Nest$fgetcurrentMessageObject(this.this$0).messageOwner.date, true, 0, 0, true, 0);
            PopupNotificationActivity.m17569$$Nest$fputcurrentMessageObject(this.this$0, null);
            PopupNotificationActivity.m17572$$Nest$mgetNewMessage(this.this$0);
            return;
        L8:
            if (PopupNotificationActivity.m17562$$Nest$fgetcurrentMessageNum(this.this$0) >= PopupNotificationActivity.m17567$$Nest$fgetpopupMessages(this.this$0).size()) goto L10;
            PopupNotificationActivity.m17567$$Nest$fgetpopupMessages(this.this$0).remove(PopupNotificationActivity.m17562$$Nest$fgetcurrentMessageNum(this.this$0));
            goto L10
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onPreAudioVideoRecord() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onSendLongClick() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onStickersExpandedChange() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onStickersTab(boolean r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onSwitchRecordMode(boolean r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onTextChanged(CharSequence r1, boolean r2, boolean r3) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onTextSelectionChanged(int r1, int r2) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onTextSpansChanged(CharSequence r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onUpdateSlowModeButton(View r1, boolean r2, CharSequence r3) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onWindowSizeChanged(int r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void setFrontface(boolean r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void toggleVideoRecordingPause() {
        }
    }

    /* renamed from: org.telegram.ui.PopupNotificationActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PopupNotificationActivity this$0;

        public AnonymousClass4(PopupNotificationActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L7;
            this.this$0.onFinish();
            this.this$0.finish();
            return;
        L7:
            if (r2 != 1) goto L11;
            PopupNotificationActivity.m17573$$Nest$mopenCurrentMessage(this.this$0);
            return;
        L11:
            if (r2 != 2) goto L14;
            PopupNotificationActivity.m17574$$Nest$mswitchToNextMessage(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.PopupNotificationActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ PopupNotificationActivity this$0;

        public AnonymousClass5(PopupNotificationActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (PopupNotificationActivity.m17565$$Nest$fgetonAnimationEndRunnable(this.this$0) == null) goto L6;
            PopupNotificationActivity.m17565$$Nest$fgetonAnimationEndRunnable(this.this$0).run();
            PopupNotificationActivity.m17570$$Nest$fputonAnimationEndRunnable(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.PopupNotificationActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ PopupNotificationActivity this$0;

        public AnonymousClass6(PopupNotificationActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (PopupNotificationActivity.m17559$$Nest$fgetavatarContainer(this.this$0) == null) goto L5;
            PopupNotificationActivity.m17559$$Nest$fgetavatarContainer(this.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
        L5:
            int r0 = (ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(48.0f)) / 2;
            PopupNotificationActivity.m17559$$Nest$fgetavatarContainer(this.this$0).setPadding(PopupNotificationActivity.m17559$$Nest$fgetavatarContainer(this.this$0).getPaddingLeft(), r0, PopupNotificationActivity.m17559$$Nest$fgetavatarContainer(this.this$0).getPaddingRight(), r0);
            return true;
        }
    }

    /* renamed from: org.telegram.ui.PopupNotificationActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ PopupNotificationActivity this$0;

        public AnonymousClass7(PopupNotificationActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            PopupNotificationActivity.m17564$$Nest$fgetmessageContainer(this.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.this$0.checkTransitionAnimation() == false) goto L5;
            return true;
        L5:
            if (PopupNotificationActivity.m17568$$Nest$fgetstartedMoving(this.this$0) == true) goto L10;
            ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) PopupNotificationActivity.m17564$$Nest$fgetmessageContainer(this.this$0).getLayoutParams();
            r0.topMargin = ActionBar.getCurrentActionBarHeight();
            r0.bottomMargin = AndroidUtilities.dp(48.0f);
            r0.width = -1;
            r0.height = -1;
            PopupNotificationActivity.m17564$$Nest$fgetmessageContainer(this.this$0).setLayoutParams(r0);
            PopupNotificationActivity.m17571$$Nest$mapplyViewsLayoutParams(this.this$0, 0);
            return true;
        L10:
            return true;
        }
    }

    public class FrameLayoutTouch extends FrameLayout {
        final /* synthetic */ PopupNotificationActivity this$0;

        public FrameLayoutTouch(PopupNotificationActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            if (this.this$0.checkTransitionAnimation() == false) goto L5;
            return true;
        L5:
            if (((PopupNotificationActivity) getContext()).onTouchEventMy(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.checkTransitionAnimation() == false) goto L5;
            return true;
        L5:
            if (((PopupNotificationActivity) getContext()).onTouchEventMy(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void requestDisallowInterceptTouchEvent(boolean r3) {
            ((PopupNotificationActivity) getContext()).onTouchEventMy(null);
            super.requestDisallowInterceptTouchEvent(r3);
        }
    }

    /* renamed from: $r8$lambda$0ZtfsiHWK_MLtvB-GQtp_ZoMqlU, reason: not valid java name */
    public static /* synthetic */ void m17557$r8$lambda$0ZtfsiHWK_MLtvBGQtp_ZoMqlU(int r2, MessageObject r3, View r4) {
        TL_keyboard.KeyboardButtonProto r42 = (TL_keyboard.KeyboardButtonProto) r4.getTag();
        if (r42 == null) goto L6;
        SendMessagesHelper.getInstance(r2).sendNotificationCallback(r3.getDialogId(), r3.getId(), r42.getData());
        return;
    }

    public static /* synthetic */ void $r8$lambda$5gaHw3sWeNZ4pQxWjkqw2B1SIl4(PopupNotificationActivity r0) {
        r0.lambda$onTouchEventMy$2();
    }

    public static /* synthetic */ void $r8$lambda$JSvbhomm01LCd4Xny_VB_bH7l28(PopupNotificationActivity r0, View r1) {
        r0.lambda$getViewForMessage$8(r1);
    }

    /* renamed from: $r8$lambda$U6e_jRPoLMjbT3mRRQA-ZisOnaI, reason: not valid java name */
    public static /* synthetic */ void m17558$r8$lambda$U6e_jRPoLMjbT3mRRQAZisOnaI(PopupNotificationActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onRequestPermissionsResult$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VsYZeSnaYigUpXRF3xqbGqfrr9Q(PopupNotificationActivity r0, View r1) {
        r0.lambda$getViewForMessage$6(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$h5vaCQOS0Y721s71pMjDPrRTnU4(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$kwBAGS9yRn8IwhZyfxNXJH8ukns(PopupNotificationActivity r0) {
        r0.lambda$onTouchEventMy$3();
    }

    public static /* synthetic */ void $r8$lambda$ssAOxiem_Dgar4K9BYc7qXKuSLo(PopupNotificationActivity r0, View r1) {
        r0.lambda$getViewForMessage$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$tATYOa06YuqhJHFRUYIWoj8aDDc(PopupNotificationActivity r0) {
        r0.lambda$onTouchEventMy$1();
    }

    /* renamed from: -$$Nest$fgetavatarContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m17559$$Nest$fgetavatarContainer(PopupNotificationActivity r0) {
        return r0.avatarContainer;
    }

    /* renamed from: -$$Nest$fgetchatActivityEnterView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityEnterView m17560$$Nest$fgetchatActivityEnterView(PopupNotificationActivity r0) {
        return r0.chatActivityEnterView;
    }

    /* renamed from: -$$Nest$fgetclassGuid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17561$$Nest$fgetclassGuid(PopupNotificationActivity r0) {
        return r0.classGuid;
    }

    /* renamed from: -$$Nest$fgetcurrentMessageNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17562$$Nest$fgetcurrentMessageNum(PopupNotificationActivity r0) {
        return r0.currentMessageNum;
    }

    /* renamed from: -$$Nest$fgetcurrentMessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m17563$$Nest$fgetcurrentMessageObject(PopupNotificationActivity r0) {
        return r0.currentMessageObject;
    }

    /* renamed from: -$$Nest$fgetmessageContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m17564$$Nest$fgetmessageContainer(PopupNotificationActivity r0) {
        return r0.messageContainer;
    }

    /* renamed from: -$$Nest$fgetonAnimationEndRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m17565$$Nest$fgetonAnimationEndRunnable(PopupNotificationActivity r0) {
        return r0.onAnimationEndRunnable;
    }

    /* renamed from: -$$Nest$fgetpopupContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RelativeLayout m17566$$Nest$fgetpopupContainer(PopupNotificationActivity r0) {
        return r0.popupContainer;
    }

    /* renamed from: -$$Nest$fgetpopupMessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m17567$$Nest$fgetpopupMessages(PopupNotificationActivity r0) {
        return r0.popupMessages;
    }

    /* renamed from: -$$Nest$fgetstartedMoving, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17568$$Nest$fgetstartedMoving(PopupNotificationActivity r0) {
        return r0.startedMoving;
    }

    /* renamed from: -$$Nest$fputcurrentMessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17569$$Nest$fputcurrentMessageObject(PopupNotificationActivity r0, MessageObject r1) {
        r0.currentMessageObject = r1;
    }

    /* renamed from: -$$Nest$fputonAnimationEndRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17570$$Nest$fputonAnimationEndRunnable(PopupNotificationActivity r0, Runnable r1) {
        r0.onAnimationEndRunnable = r1;
    }

    /* renamed from: -$$Nest$mapplyViewsLayoutParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17571$$Nest$mapplyViewsLayoutParams(PopupNotificationActivity r0, int r1) {
        r0.applyViewsLayoutParams(r1);
    }

    /* renamed from: -$$Nest$mgetNewMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17572$$Nest$mgetNewMessage(PopupNotificationActivity r0) {
        r0.getNewMessage();
    }

    /* renamed from: -$$Nest$mopenCurrentMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17573$$Nest$mopenCurrentMessage(PopupNotificationActivity r0) {
        r0.openCurrentMessage();
    }

    /* renamed from: -$$Nest$mswitchToNextMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17574$$Nest$mswitchToNextMessage(PopupNotificationActivity r0) {
        r0.switchToNextMessage();
    }

    public PopupNotificationActivity() {
        this.textViews = new ArrayList();
        this.imageViews = new ArrayList();
        this.audioViews = new ArrayList();
        this.velocityTracker = null;
        this.statusDrawables = new StatusDrawable[5];
        this.lastResumedAccount = -1;
        this.finished = false;
        this.currentMessageObject = null;
        this.setMessageObjects = new MessageObject[3];
        this.currentMessageNum = 0;
        this.wakeLock = null;
        this.animationInProgress = false;
        this.animationStartTime = 0;
        this.moveStartX = -1.0f;
        this.startedMoving = false;
        this.onAnimationEndRunnable = null;
        this.popupMessages = new ArrayList();
    }

    private void applyViewsLayoutParams(int r4) {
        int r0 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(24.0f);
        ViewGroup r1 = this.leftView;
        if (r1 == null) goto L8;
        FrameLayout.LayoutParams r12 = (FrameLayout.LayoutParams) r1.getLayoutParams();
        if (r12.width == r0) goto L7;
        r12.width = r0;
        this.leftView.setLayoutParams(r12);
    L7:
        this.leftView.setTranslationX((-r0) + r4);
    L8:
        ViewGroup r13 = this.leftButtonsView;
        if (r13 == null) goto L11;
        r13.setTranslationX((-r0) + r4);
    L11:
        ViewGroup r14 = this.centerView;
        if (r14 == null) goto L17;
        FrameLayout.LayoutParams r15 = (FrameLayout.LayoutParams) r14.getLayoutParams();
        if (r15.width == r0) goto L16;
        r15.width = r0;
        this.centerView.setLayoutParams(r15);
    L16:
        this.centerView.setTranslationX(r4);
    L17:
        ViewGroup r16 = this.centerButtonsView;
        if (r16 == null) goto L20;
        r16.setTranslationX(r4);
    L20:
        ViewGroup r17 = this.rightView;
        if (r17 == null) goto L26;
        FrameLayout.LayoutParams r18 = (FrameLayout.LayoutParams) r17.getLayoutParams();
        if (r18.width == r0) goto L25;
        r18.width = r0;
        this.rightView.setLayoutParams(r18);
    L25:
        this.rightView.setTranslationX(r0 + r4);
    L26:
        ViewGroup r19 = this.rightButtonsView;
        if (r19 == null) goto L29;
        r19.setTranslationX(r0 + r4);
    L29:
        this.messageContainer.invalidate();
    }

    private void checkAndUpdateAvatar() {
        MessageObject r0 = this.currentMessageObject;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.currentChat == null) goto L15;
        TLRPC.Chat r02 = MessagesController.getInstance(r0.currentAccount).getChat(Long.valueOf(this.currentChat.id));
        if (r02 == null) goto L27;
        this.currentChat = r02;
        if (this.avatarImageView == null) goto L23;
        AvatarDrawable r1 = new AvatarDrawable(this.currentChat);
        this.avatarImageView.setForUserOrChat(r02, r1);
        return;
    L23:
        return;
    L27:
        return;
    L15:
        if (this.currentUser == null) goto L24;
        TLRPC.User r03 = MessagesController.getInstance(r0.currentAccount).getUser(Long.valueOf(this.currentUser.id));
        if (r03 == null) goto L28;
        this.currentUser = r03;
        if (this.avatarImageView == null) goto L25;
        AvatarDrawable r12 = new AvatarDrawable(this.currentUser);
        this.avatarImageView.setForUserOrChat(r03, r12);
        return;
    L25:
        return;
    L28:
        return;
    }

    private void fixLayout() {
        FrameLayout r0 = this.avatarContainer;
        if (r0 == null) goto L5;
        r0.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass6(this));
    L5:
        ViewGroup r02 = this.messageContainer;
        if (r02 == null) goto L9;
        r02.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass7(this));
        return;
    }

    private LinearLayout getButtonsViewForMessage(int r21, boolean r22) {
        int r1 = r21;
        if (this.popupMessages.size() != 1) goto L8;
        if (r1 >= 0) goto L6;
    L7:
        return null;
    L6:
        if (r1 >= this.popupMessages.size()) goto L7;
    L8:
        ArrayList<MessageObject> r2 = this.popupMessages;
        int r6 = 0;
        if (r1 != (-1)) goto L12;
        r1 = r2.size() - 1;
    L14:
        MessageObject r23 = this.popupMessages.get(r1);
        TLRPC.ReplyMarkup r7 = r23.messageOwner.reply_markup;
        if (r23.getDialogId() == 777000) goto L17;
    L27:
        int r12 = 0;
    L28:
        int r3 = r23.currentAccount;
        if (r12 > 0) goto L31;
    L47:
        LinearLayout r32 = null;
    L48:
        if (r32 == null) goto L61;
        int r24 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(24.0f);
        RelativeLayout.LayoutParams r4 = new RelativeLayout.LayoutParams(-1, -2);
        r4.addRule(12);
        if (r22 == false) goto L60;
        int r5 = this.currentMessageNum;
        if (r1 != r5) goto L55;
        r32.setTranslationX(0.0f);
        goto L60
    L55:
        if (r1 != (r5 - 1)) goto L58;
        r32.setTranslationX(-r24);
        goto L60
    L58:
        if (r1 != (r5 + 1)) goto L60;
        r32.setTranslationX(r24);
    L60:
        this.popupContainer.addView(r32, r4);
    L61:
        return r32;
    L31:
        if ((r7 instanceof TLRPC.TL_replyInlineMarkup) == false) goto L47;
        ArrayList<TL_keyboard.KeyboardInlineButtonRow> r72 = ((TLRPC.TL_replyInlineMarkup) r7).rows;
        int r8 = r72.size();
        int r10 = 0;
        LinearLayout r16 = null;
    L33:
        if (r10 >= r8) goto L46;
        TL_keyboard.KeyboardInlineButtonRow r11 = r72.get(r10);
        int r13 = r11.buttons.size();
        int r14 = r6;
    L35:
        if (r14 >= r13) goto L45;
        TL_keyboard.KeyboardInlineButton r15 = r11.buttons.get(r14);
        if (TLKeyboardHelper.isType(r15, TL_keyboard.TL_inlineButtonTypeCallback.class) == true) goto L39;
        ArrayList<TL_keyboard.KeyboardInlineButtonRow> r19 = r72;
    L44:
        r14 = r14 + 1;
        r72 = r19;
        r6 = 0;
        goto L35
    L39:
        if (r16 != null) goto L41;
        LinearLayout r42 = new LinearLayout(this);
        r42.setOrientation(r6);
        r42.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        r42.setWeightSum(100.0f);
        r42.setTag("b");
        r42.setOnTouchListener(new PopupNotificationActivity$$ExternalSyntheticLambda4());
    L42:
        TextView r62 = new TextView(this);
        r19 = r72;
        r62.setTextSize(1, 16.0f);
        r62.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText));
        r62.setTypeface(AndroidUtilities.bold());
        r62.setText(r15.text.toUpperCase());
        r62.setTag(r15);
        r62.setGravity(17);
        r62.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        r42.addView(r62, LayoutHelper.createLinear(-1, -1, 100.0f / r12));
        r62.setOnClickListener(new PopupNotificationActivity$$ExternalSyntheticLambda5(r3, r23));
        r16 = r42;
        goto L44
    L41:
        r42 = r16;
        goto L42
    L45:
        r10 = r10 + 1;
        r6 = 0;
        goto L33
    L46:
        r32 = r16;
        goto L48
    L17:
        if ((r7 instanceof TLRPC.TL_replyInlineMarkup) == false) goto L27;
        ArrayList<TL_keyboard.KeyboardInlineButtonRow> r82 = ((TLRPC.TL_replyInlineMarkup) r7).rows;
        int r102 = r82.size();
        int r112 = 0;
        r12 = 0;
    L19:
        if (r112 >= r102) goto L28;
        TL_keyboard.KeyboardInlineButtonRow r132 = r82.get(r112);
        int r142 = r132.buttons.size();
        int r152 = 0;
    L21:
        if (r152 >= r142) goto L26;
        if (TLKeyboardHelper.isType(r132.buttons.get(r152), TL_keyboard.TL_inlineButtonTypeCallback.class) == false) goto L25;
        r12 = r12 + 1;
    L25:
        r152 = r152 + 1;
        goto L21
    L26:
        r112 = r112 + 1;
        goto L19
    L12:
        if (r1 != r2.size()) goto L14;
        r1 = 0;
        goto L14
    }

    private void getNewMessage() {
        if (this.popupMessages.isEmpty() == false) goto L7;
        onFinish();
        finish();
        return;
    L7:
        if (this.currentMessageNum != 0) goto L13;
        if (this.chatActivityEnterView.hasText() == true) goto L13;
        if (this.startedMoving == true) goto L13;
    L31:
        this.currentMessageNum = 0;
        this.currentMessageObject = this.popupMessages.get(0);
        updateInterfaceForCurrentMessage(0);
    L32:
        this.countText.setText(String.format("%d/%d", new Object[]{Integer.valueOf(this.currentMessageNum + 1), Integer.valueOf(this.popupMessages.size())}));
        return;
    L13:
        if (this.currentMessageObject == null) goto L31;
        int r0 = this.popupMessages.size();
        int r3 = 0;
    L15:
        if (r3 >= r0) goto L31;
        MessageObject r4 = this.popupMessages.get(r3);
        if (r4.currentAccount != this.currentMessageObject.currentAccount) goto L30;
        if (r4.getDialogId() != this.currentMessageObject.getDialogId()) goto L30;
        if (r4.getId() != this.currentMessageObject.getId()) goto L30;
        this.currentMessageNum = r3;
        if (this.startedMoving == false) goto L32;
        if (r3 != (this.popupMessages.size() - 1)) goto L28;
        prepareLayouts(3);
        goto L32
    L28:
        if (this.currentMessageNum != 1) goto L32;
        prepareLayouts(4);
    L30:
        r3 = r3 + 1;
        goto L15
    }

    private ViewGroup getViewForMessage(int r31, boolean r32) {
        int r1 = r31;
        Integer r2 = Integer.valueOf(MediaError.DetailedErrorCode.SEGMENT_NETWORK);
        Integer r4 = Integer.valueOf(MediaError.DetailedErrorCode.HLS_NETWORK_PLAYLIST);
        Integer r5 = Integer.valueOf(MediaError.DetailedErrorCode.HLS_NETWORK_MASTER_PLAYLIST);
        if (this.popupMessages.size() != 1) goto L8;
        if (r1 >= 0) goto L6;
    L7:
        return null;
    L6:
        if (r1 >= this.popupMessages.size()) goto L7;
    L8:
        ArrayList<MessageObject> r6 = this.popupMessages;
        if (r1 != (-1)) goto L12;
        r1 = r6.size() - 1;
    L14:
        MessageObject r62 = this.popupMessages.get(r1);
        int r11 = r62.type;
        if (r11 == 1) goto L18;
        if (r11 == 4) goto L18;
    L53:
        if (r62.type != 2) goto L62;
        if (this.audioViews.size() <= 0) goto L57;
        ViewGroup r22 = this.audioViews.get(0);
        this.audioViews.remove(0);
        PopupAudioView r3 = (PopupAudioView) r22.findViewWithTag(300);
    L58:
        r3.setMessageObject(r62);
        if (DownloadController.getInstance(r62.currentAccount).canDownloadMedia(r62) == false) goto L67;
        r3.downloadAudioIfNeed();
    L67:
        if (r22.getParent() != null) goto L69;
        this.messageContainer.addView(r22);
    L69:
        r22.setVisibility(0);
        if (r32 == false) goto L81;
        int r33 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(24.0f);
        FrameLayout.LayoutParams r42 = (FrameLayout.LayoutParams) r22.getLayoutParams();
        r42.gravity = 51;
        r42.height = -1;
        r42.width = r33;
        int r0 = this.currentMessageNum;
        if (r1 != r0) goto L75;
        r22.setTranslationX(0.0f);
    L80:
        r22.setLayoutParams(r42);
        r22.invalidate();
        goto L81
    L75:
        if (r1 != (r0 - 1)) goto L78;
        r22.setTranslationX(-r33);
        goto L80
    L78:
        if (r1 != (r0 + 1)) goto L80;
        r22.setTranslationX(r33);
    L81:
        return r22;
    L57:
        r22 = new FrameLayout(this);
        FrameLayout r43 = new FrameLayout(this);
        r43.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        r43.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r22.addView(r43, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r52 = new FrameLayout(this);
        r43.addView(r52, LayoutHelper.createFrame(-1, -2.0f, 17, 20.0f, 0.0f, 20.0f, 0.0f));
        PopupAudioView r44 = new PopupAudioView(this);
        r44.setTag(300);
        r52.addView(r44);
        r22.setTag(3);
        r22.setOnClickListener(new PopupNotificationActivity$$ExternalSyntheticLambda7(this));
        r3 = r44;
        goto L58
    L62:
        if (this.textViews.size() <= 0) goto L64;
        ViewGroup r34 = this.textViews.get(0);
        this.textViews.remove(0);
        ViewGroup r35 = r34;
    L65:
        TextView r23 = (TextView) r35.findViewWithTag(r2);
        r23.setTextSize(2, SharedConfig.fontSize);
        r23.setText(r62.messageText);
        r22 = r35;
        goto L67
    L64:
        FrameLayout r36 = new FrameLayout(this);
        ScrollView r53 = new ScrollView(this);
        r53.setFillViewport(true);
        r36.addView(r53, LayoutHelper.createFrame(-1, -1.0f));
        LinearLayout r7 = new LinearLayout(this);
        r7.setOrientation(0);
        r7.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r53.addView(r7, LayoutHelper.createScroll(-1, -2, 1));
        r7.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        r7.setOnClickListener(new PopupNotificationActivity$$ExternalSyntheticLambda8(this));
        TextView r45 = new TextView(this);
        r45.setTextSize(1, 16.0f);
        r45.setTag(r2);
        int r54 = Theme.key_windowBackgroundWhiteBlackText;
        r45.setTextColor(Theme.getColor(r54));
        r45.setLinkTextColor(Theme.getColor(r54));
        r45.setGravity(17);
        r7.addView(r45, LayoutHelper.createLinear(-1, -2, 17));
        r36.setTag(1);
        r35 = r36;
    L18:
        if (r62.isSecretMedia() == true) goto L53;
        if (this.imageViews.size() <= 0) goto L22;
        r22 = this.imageViews.get(0);
        this.imageViews.remove(0);
        int r19 = 0;
        int r18 = 2;
    L23:
        TextView r37 = (TextView) r22.findViewWithTag(r4);
        BackupImageView r112 = (BackupImageView) r22.findViewWithTag(r5);
        r112.setAspectFit(true);
        int r46 = r62.type;
        if (r46 != 1) goto L47;
        TLRPC.PhotoSize r47 = FileLoader.getClosestPhotoSizeWithSize(r62.photoThumbs, AndroidUtilities.getPhotoSize());
        TLRPC.PhotoSize r72 = FileLoader.getClosestPhotoSizeWithSize(r62.photoThumbs, 100);
        if (r47 != null) goto L28;
    L42:
        int r48 = r19;
        r112.setVisibility(8);
        r37.setVisibility(r48);
        r37.setTextSize(r18, SharedConfig.fontSize);
        r37.setText(r62.messageText);
        goto L67
    L28:
        if (r62.type == 1) goto L30;
    L32:
        int r10 = 1;
    L34:
        if (r62.needDrawBluredPreview() == true) goto L42;
        if (r10 == 0) goto L37;
    L43:
        r112.setImage(ImageLocation.getForObject(r47, r62.photoThumbsObject), "100_100", ImageLocation.getForObject(r72, r62.photoThumbsObject), "100_100_b", r47.size, r62);
    L44:
        r112.setVisibility(r19);
        r37.setVisibility(8);
        goto L67
    L37:
        if (DownloadController.getInstance(r62.currentAccount).canDownloadMedia(r62) == true) goto L43;
        if (r72 == null) goto L42;
        r112.setImage(ImageLocation.getForObject(r72, r62.photoThumbsObject), "100_100_b", null, null, r62);
        goto L44
    L30:
        if (FileLoader.getInstance(UserConfig.selectedAccount).getPathToMessage(r62.messageOwner).exists() == true) goto L32;
        r10 = r19;
        goto L34
    L47:
        if (r46 != 4) goto L67;
        r37.setVisibility(8);
        r37.setText(r62.messageText);
        r112.setVisibility(0);
        TLRPC.GeoPoint r38 = r62.messageOwner.media.geo;
        double r49 = r38.lat;
        double r12 = r38._long;
        if (MessagesController.getInstance(r62.currentAccount).mapProvider != 2) goto L51;
        r112.setImage(ImageLocation.getForWebFile(WebFile.createWithGeoPoint(r38, 100, 100, 15, Math.min(2, (int) Math.ceil(AndroidUtilities.density)))), null, null, null, r62);
        goto L67
    L51:
        r112.setImage(AndroidUtilities.formapMapUrl(r62.currentAccount, r49, r12, 100, 100, true, 15, -1), null, null);
        goto L67
    L22:
        r22 = new FrameLayout(this);
        FrameLayout r39 = new FrameLayout(this);
        r18 = 2;
        r19 = 0;
        r39.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        r39.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r22.addView(r39, LayoutHelper.createFrame(-1, -1.0f));
        BackupImageView r102 = new BackupImageView(this);
        r102.setTag(r5);
        r39.addView(r102, LayoutHelper.createFrame(-1, -1.0f));
        TextView r73 = new TextView(this);
        r73.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r73.setTextSize(1, 16.0f);
        r73.setGravity(17);
        r73.setTag(r4);
        r39.addView(r73, LayoutHelper.createFrame(-1, -2, 17));
        r22.setTag(2);
        r22.setOnClickListener(new PopupNotificationActivity$$ExternalSyntheticLambda6(this));
        goto L23
    L12:
        if (r1 != r6.size()) goto L14;
        r1 = 0;
        goto L14
    }

    private void handleIntent(Intent r4) {
        if (r4 != null) goto L5;
    L7:
        boolean r1 = false;
    L8:
        this.isReply = r1;
        this.popupMessages.clear();
        if (this.isReply == false) goto L17;
        if (r4 == null) goto L12;
        int r42 = r4.getIntExtra("currentAccount", UserConfig.selectedAccount);
    L14:
        if (UserConfig.isValidAccount(r42) == true) goto L16;
        return;
    L16:
        this.popupMessages.addAll(NotificationsController.getInstance(r42).popupReplyMessages);
    L25:
        if (((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode() == false) goto L27;
    L30:
        getWindow().addFlags(2623490);
    L32:
        if (this.currentMessageObject != null) goto L34;
        this.currentMessageNum = 0;
    L34:
        getNewMessage();
        return;
    L27:
        if (ApplicationLoader.isScreenOn == false) goto L30;
        getWindow().addFlags(2623488);
        getWindow().clearFlags(2);
        goto L32
    L12:
        r42 = UserConfig.selectedAccount;
        goto L14
    L17:
        int r43 = 0;
    L19:
        if (r43 >= 16) goto L25;
        if (UserConfig.getInstance(r43).isClientActivated() == false) goto L23;
        this.popupMessages.addAll(NotificationsController.getInstance(r43).popupMessages);
    L23:
        r43 = r43 + 1;
        goto L19
    L5:
        if (r4.getBooleanExtra("force", false) == false) goto L7;
        r1 = true;
        goto L8
    }

    private /* synthetic */ void lambda$getViewForMessage$6(View r1) {
        openCurrentMessage();
    }

    private /* synthetic */ void lambda$getViewForMessage$7(View r1) {
        openCurrentMessage();
    }

    private /* synthetic */ void lambda$getViewForMessage$8(View r1) {
        openCurrentMessage();
    }

    private /* synthetic */ void lambda$onRequestPermissionsResult$0(AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onTouchEventMy$1() {
        this.animationInProgress = false;
        switchToPreviousMessage();
        AndroidUtilities.unlockOrientation(this);
    }

    private /* synthetic */ void lambda$onTouchEventMy$2() {
        this.animationInProgress = false;
        switchToNextMessage();
        AndroidUtilities.unlockOrientation(this);
    }

    private /* synthetic */ void lambda$onTouchEventMy$3() {
        this.animationInProgress = false;
        applyViewsLayoutParams(0);
        AndroidUtilities.unlockOrientation(this);
    }

    private void openCurrentMessage() {
        if (this.currentMessageObject != null) goto L5;
        return;
    L5:
        Intent r0 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);
        long r1 = this.currentMessageObject.getDialogId();
        if (DialogObject.isEncryptedDialog(r1) == false) goto L9;
        r0.putExtra("encId", DialogObject.getEncryptedChatId(r1));
    L14:
        r0.putExtra("currentAccount", this.currentMessageObject.currentAccount);
        r0.setAction("com.tmessages.openchat" + Math.random() + Integer.MAX_VALUE);
        r0.setFlags(32768);
        startActivity(r0);
        onFinish();
        finish();
        return;
    L9:
        if (DialogObject.isUserDialog(r1) == false) goto L12;
        r0.putExtra("userId", r1);
        goto L14
    L12:
        if (DialogObject.isChatDialog(r1) == false) goto L14;
        r0.putExtra("chatId", -r1);
        goto L14
    }

    private void prepareLayouts(int r7) {
        int r0 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(24.0f);
        if (r7 != 0) goto L16;
        reuseView(this.centerView);
        reuseView(this.leftView);
        reuseView(this.rightView);
        reuseButtonsView(this.centerButtonsView);
        reuseButtonsView(this.leftButtonsView);
        reuseButtonsView(this.rightButtonsView);
        int r72 = this.currentMessageNum - 1;
    L5:
        int r02 = this.currentMessageNum;
        if (r72 >= (r02 + 2)) goto L44;
        if (r72 != (r02 - 1)) goto L10;
        this.leftView = getViewForMessage(r72, true);
        this.leftButtonsView = getButtonsViewForMessage(r72, true);
    L15:
        r72 = r72 + 1;
        goto L5
    L10:
        if (r72 != r02) goto L13;
        this.centerView = getViewForMessage(r72, true);
        this.centerButtonsView = getButtonsViewForMessage(r72, true);
        goto L15
    L13:
        if (r72 != (r02 + 1)) goto L15;
        this.rightView = getViewForMessage(r72, true);
        this.rightButtonsView = getButtonsViewForMessage(r72, true);
    L44:
        int r73 = 0;
    L45:
        if (r73 >= 3) goto L60;
        int r03 = (this.currentMessageNum - 1) + r73;
        if (this.popupMessages.size() != 1) goto L52;
        if (r03 >= 0) goto L50;
    L51:
        MessageObject r04 = null;
    L59:
        this.setMessageObjects[r73] = r04;
        r73 = r73 + 1;
        goto L45
    L50:
        if (r03 >= this.popupMessages.size()) goto L51;
    L52:
        ArrayList<MessageObject> r4 = this.popupMessages;
        if (r03 != (-1)) goto L56;
        r03 = r4.size() - 1;
    L58:
        r04 = this.popupMessages.get(r03);
        goto L59
    L56:
        if (r03 != r4.size()) goto L58;
        r03 = 0;
        goto L58
    L60:
        return;
    L16:
        if (r7 != 1) goto L19;
        reuseView(this.rightView);
        reuseButtonsView(this.rightButtonsView);
        this.rightView = this.centerView;
        this.centerView = this.leftView;
        this.leftView = getViewForMessage(this.currentMessageNum - 1, true);
        this.rightButtonsView = this.centerButtonsView;
        this.centerButtonsView = this.leftButtonsView;
        this.leftButtonsView = getButtonsViewForMessage(this.currentMessageNum - 1, true);
        goto L44
    L19:
        if (r7 != 2) goto L21;
        reuseView(this.leftView);
        reuseButtonsView(this.leftButtonsView);
        this.leftView = this.centerView;
        this.centerView = this.rightView;
        this.rightView = getViewForMessage(this.currentMessageNum + 1, true);
        this.leftButtonsView = this.centerButtonsView;
        this.centerButtonsView = this.rightButtonsView;
        this.rightButtonsView = getButtonsViewForMessage(this.currentMessageNum + 1, true);
        goto L44
    L21:
        if (r7 != 3) goto L33;
        ViewGroup r74 = this.rightView;
        if (r74 == null) goto L27;
        float r75 = r74.getTranslationX();
        reuseView(this.rightView);
        ViewGroup r42 = getViewForMessage(this.currentMessageNum + 1, false);
        this.rightView = r42;
        if (r42 == null) goto L27;
        FrameLayout.LayoutParams r43 = (FrameLayout.LayoutParams) r42.getLayoutParams();
        r43.width = r0;
        this.rightView.setLayoutParams(r43);
        this.rightView.setTranslationX(r75);
        this.rightView.invalidate();
    L27:
        ViewGroup r76 = this.rightButtonsView;
        if (r76 == null) goto L44;
        float r77 = r76.getTranslationX();
        reuseButtonsView(this.rightButtonsView);
        LinearLayout r05 = getButtonsViewForMessage(this.currentMessageNum + 1, false);
        this.rightButtonsView = r05;
        if (r05 == null) goto L44;
        r05.setTranslationX(r77);
        goto L44
    L33:
        if (r7 != 4) goto L44;
        ViewGroup r78 = this.leftView;
        if (r78 == null) goto L39;
        float r79 = r78.getTranslationX();
        reuseView(this.leftView);
        ViewGroup r44 = getViewForMessage(0, false);
        this.leftView = r44;
        if (r44 == null) goto L39;
        FrameLayout.LayoutParams r45 = (FrameLayout.LayoutParams) r44.getLayoutParams();
        r45.width = r0;
        this.leftView.setLayoutParams(r45);
        this.leftView.setTranslationX(r79);
        this.leftView.invalidate();
    L39:
        ViewGroup r710 = this.leftButtonsView;
        if (r710 == null) goto L44;
        float r711 = r710.getTranslationX();
        reuseButtonsView(this.leftButtonsView);
        LinearLayout r06 = getButtonsViewForMessage(0, false);
        this.leftButtonsView = r06;
        if (r06 == null) goto L44;
        r06.setTranslationX(r711);
        goto L44
    }

    private void reuseButtonsView(ViewGroup r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        this.popupContainer.removeView(r1);
    }

    private void reuseView(ViewGroup r3) {
        if (r3 == null) goto L17;
        int r0 = ((Integer) r3.getTag()).intValue();
        r3.setVisibility(8);
        if (r0 != 1) goto L9;
        this.textViews.add(r3);
        return;
    L9:
        if (r0 != 2) goto L13;
        this.imageViews.add(r3);
        return;
    L13:
        if (r0 != 3) goto L16;
        this.audioViews.add(r3);
        return;
    L16:
        return;
    }

    private void setTypingAnimation(boolean r7) {
        if (this.actionBar == null) goto L23;
        int r0 = 0;
        if (r7 == true) goto L24;
        this.onlineTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        this.onlineTextView.setCompoundDrawablePadding(0);
    L20:
        StatusDrawable[] r72 = this.statusDrawables;
        if (r0 >= r72.length) goto L30;
        r72[r0].stop();
        r0 = r0 + 1;
        goto L20
    L30:
        return;
    L24:
        Integer r73 = MessagesController.getInstance(this.currentMessageObject.currentAccount).getPrintingStringType(this.currentMessageObject.getDialogId(), 0);     // Catch: Exception -> L16
        this.onlineTextView.setCompoundDrawablesWithIntrinsicBounds(this.statusDrawables[r73.intValue()], null, null, null);     // Catch: Exception -> L16
        this.onlineTextView.setCompoundDrawablePadding(AndroidUtilities.dp(4.0f));     // Catch: Exception -> L16
    L8:
        if (r0 >= this.statusDrawables.length) goto L31;
        int r1 = r73.intValue();     // Catch: Exception -> L16
        StatusDrawable[] r2 = this.statusDrawables;
        if (r0 != r1) goto L14;
        r2[r0].start();     // Catch: Exception -> L16
    L15:
        r0 = r0 + 1;
        goto L8
    L14:
        r2[r0].stop();     // Catch: Exception -> L16
        goto L15
    L31:
        return;
    L16:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    private void switchToNextMessage() {
        if (this.popupMessages.size() > 1) goto L5;
        return;
    L5:
        if (this.currentMessageNum >= (this.popupMessages.size() - 1)) goto L7;
        this.currentMessageNum++;
    L8:
        this.currentMessageObject = this.popupMessages.get(this.currentMessageNum);
        updateInterfaceForCurrentMessage(2);
        this.countText.setText(String.format("%d/%d", new Object[]{Integer.valueOf(this.currentMessageNum + 1), Integer.valueOf(this.popupMessages.size())}));
        return;
    L7:
        this.currentMessageNum = 0;
        goto L8
    }

    private void switchToPreviousMessage() {
        if (this.popupMessages.size() <= 1) goto L10;
        int r0 = this.currentMessageNum;
        if (r0 <= 0) goto L7;
        this.currentMessageNum = r0 - 1;
    L8:
        this.currentMessageObject = this.popupMessages.get(this.currentMessageNum);
        updateInterfaceForCurrentMessage(1);
        this.countText.setText(String.format("%d/%d", new Object[]{Integer.valueOf(this.currentMessageNum + 1), Integer.valueOf(this.popupMessages.size())}));
        return;
    L7:
        this.currentMessageNum = this.popupMessages.size() - 1;
        goto L8
    }

    private void updateInterfaceForCurrentMessage(int r9) {
        if (this.actionBar != null) goto L5;
        return;
    L5:
        int r0 = this.lastResumedAccount;
        if (r0 == this.currentMessageObject.currentAccount) goto L10;
        if (r0 < 0) goto L9;
        ConnectionsManager.getInstance(r0).setAppPaused(true, false);
    L9:
        int r02 = this.currentMessageObject.currentAccount;
        this.lastResumedAccount = r02;
        ConnectionsManager.getInstance(r02).setAppPaused(false, false);
    L10:
        this.currentChat = null;
        this.currentUser = null;
        long r3 = this.currentMessageObject.getDialogId();
        this.chatActivityEnterView.setDialogId(r3, this.currentMessageObject.currentAccount);
        if (DialogObject.isEncryptedDialog(r3) == false) goto L14;
        TLRPC.EncryptedChat r1 = MessagesController.getInstance(this.currentMessageObject.currentAccount).getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r3)));
        this.currentUser = MessagesController.getInstance(this.currentMessageObject.currentAccount).getUser(Long.valueOf(r1.user_id));
    L21:
        TLRPC.Chat r12 = this.currentChat;
        if (r12 == null) goto L28;
        this.nameTextView.setText(r12.title);
        TLRPC.User r13 = this.currentUser;
        TextView r32 = this.onlineTextView;
        if (r13 == null) goto L26;
        r32.setText(UserObject.getUserName(r13));
    L27:
        this.nameTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        this.nameTextView.setCompoundDrawablePadding(0);
    L34:
        prepareLayouts(r9);
        updateSubtitle();
        checkAndUpdateAvatar();
        applyViewsLayoutParams(0);
        return;
    L26:
        r32.setText(null);
        goto L27
    L28:
        TLRPC.User r03 = this.currentUser;
        if (r03 == null) goto L34;
        this.nameTextView.setText(UserObject.getUserName(r03));
        boolean r04 = DialogObject.isEncryptedDialog(r3);
        TextView r14 = this.nameTextView;
        if (r04 == false) goto L33;
        r14.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_white, 0, 0, 0);
        this.nameTextView.setCompoundDrawablePadding(AndroidUtilities.dp(4.0f));
        goto L34
    L33:
        r14.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        this.nameTextView.setCompoundDrawablePadding(0);
        goto L34
    L14:
        if (DialogObject.isUserDialog(r3) == false) goto L17;
        this.currentUser = MessagesController.getInstance(this.currentMessageObject.currentAccount).getUser(Long.valueOf(r3));
        goto L21
    L17:
        if (DialogObject.isChatDialog(r3) == false) goto L21;
        this.currentChat = MessagesController.getInstance(this.currentMessageObject.currentAccount).getChat(Long.valueOf(-r3));
        if (this.currentMessageObject.isFromUser() == false) goto L21;
        this.currentUser = MessagesController.getInstance(this.currentMessageObject.currentAccount).getUser(Long.valueOf(this.currentMessageObject.messageOwner.from_id.user_id));
        goto L21
    }

    private void updateSubtitle() {
        if (this.actionBar == null) goto L52;
        MessageObject r0 = this.currentMessageObject;
        if (r0 != null) goto L8;
        return;
    L8:
        if (this.currentChat != null) goto L53;
        TLRPC.User r1 = this.currentUser;
        if (r1 == null) goto L55;
        long r12 = r1.id;
        if ((r12 / 1000) != 777) goto L15;
    L28:
        this.nameTextView.setText(UserObject.getUserName(this.currentUser));
    L29:
        TLRPC.User r02 = this.currentUser;
        if (r02 != null) goto L32;
    L35:
        if (r02 != null) goto L37;
    L40:
        CharSequence r03 = MessagesController.getInstance(this.currentMessageObject.currentAccount).getPrintingString(this.currentMessageObject.getDialogId(), 0, false);
        if (r03 != null) goto L43;
    L47:
        this.lastPrintString = null;
        setTypingAnimation(false);
        TLRPC.User r04 = MessagesController.getInstance(this.currentMessageObject.currentAccount).getUser(Long.valueOf(this.currentUser.id));
        if (r04 == null) goto L50;
        this.currentUser = r04;
    L50:
        this.onlineTextView.setText(LocaleController.formatUserStatus(this.currentMessageObject.currentAccount, this.currentUser));
        return;
    L43:
        if (r03.length() == 0) goto L47;
        this.lastPrintString = r03;
        this.onlineTextView.setText(r03);
        setTypingAnimation(true);
        return;
    L37:
        if (r02.id != 777000) goto L40;
        this.onlineTextView.setText(LocaleController.getString(R.string.ServiceNotifications));
        return;
    L32:
        if (r02.id != UserObject.VERIFY) goto L35;
        this.onlineTextView.setText(LocaleController.getString(R.string.VerifyCodesNotifications));
        return;
    L15:
        if ((r12 / 1000) == 333) goto L28;
        if (ContactsController.getInstance(r0.currentAccount).contactsDict.get(Long.valueOf(this.currentUser.id)) != null) goto L28;
        if (ContactsController.getInstance(this.currentMessageObject.currentAccount).contactsDict.size() == 0) goto L21;
    L22:
        String r05 = this.currentUser.phone;
        if (r05 != null) goto L25;
    L27:
        this.nameTextView.setText(UserObject.getUserName(this.currentUser));
        goto L29
    L25:
        if (r05.length() == 0) goto L27;
        this.nameTextView.setText(PhoneFormat.getInstance().format("+" + this.currentUser.phone));
        goto L29
    L21:
        if (ContactsController.getInstance(this.currentMessageObject.currentAccount).isLoadingContacts() == true) goto L28;
    L55:
        return;
    L53:
        return;
    }

    public boolean checkTransitionAnimation() {
        if (this.animationInProgress == false) goto L10;
        if (this.animationStartTime >= (System.currentTimeMillis() - 400)) goto L10;
        this.animationInProgress = false;
        Runnable r0 = this.onAnimationEndRunnable;
        if (r0 == null) goto L10;
        r0.run();
        this.onAnimationEndRunnable = null;
    L10:
        return this.animationInProgress;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r7, int r8, Object... r9) {
        if (r7 == NotificationCenter.appDidLogout) goto L5;
        int r4 = 0;
        if (r7 != NotificationCenter.pushMessagesUpdated) goto L41;
        if (this.isReply == true) goto L148;
        this.popupMessages.clear();
        int r72 = 0;
    L14:
        if (r72 >= 16) goto L19;
        if (UserConfig.getInstance(r72).isClientActivated() == false) goto L18;
        this.popupMessages.addAll(NotificationsController.getInstance(r72).popupMessages);
    L18:
        r72 = r72 + 1;
        goto L14
    L19:
        getNewMessage();
        if (this.popupMessages.isEmpty() == true) goto L149;
        int r73 = 0;
    L22:
        if (r73 >= 3) goto L160;
        int r82 = (this.currentMessageNum - 1) + r73;
        if (this.popupMessages.size() != 1) goto L29;
        if (r82 >= 0) goto L27;
    L28:
        MessageObject r83 = null;
    L37:
        if (this.setMessageObjects[r73] == r83) goto L39;
        updateInterfaceForCurrentMessage(0);
    L39:
        r73 = r73 + 1;
        goto L22
    L27:
        if (r82 >= this.popupMessages.size()) goto L28;
    L29:
        ArrayList<MessageObject> r92 = this.popupMessages;
        if (r82 != (-1)) goto L33;
        r82 = r92.size() - 1;
    L35:
        r83 = this.popupMessages.get(r82);
        goto L37
    L33:
        if (r82 != r92.size()) goto L35;
        r82 = 0;
        goto L35
    L160:
        return;
    L149:
        return;
    L148:
        return;
    L41:
        if (r7 != NotificationCenter.updateInterfaces) goto L74;
        if (this.currentMessageObject != null) goto L45;
        return;
    L45:
        if (r8 != this.lastResumedAccount) goto L159;
        int r74 = ((Integer) r9[0]).intValue();
        if ((MessagesController.UPDATE_MASK_NAME & r74) == 0) goto L50;
    L55:
        updateSubtitle();
    L57:
        if ((MessagesController.UPDATE_MASK_AVATAR & r74) == 0) goto L59;
    L60:
        checkAndUpdateAvatar();
    L62:
        if ((r74 & MessagesController.UPDATE_MASK_USER_PRINT) == 0) goto L151;
        CharSequence r75 = MessagesController.getInstance(this.currentMessageObject.currentAccount).getPrintingString(this.currentMessageObject.getDialogId(), 0, false);
        CharSequence r84 = this.lastPrintString;
        if (r84 == null) goto L66;
        if (r75 != null) goto L66;
    L71:
        updateSubtitle();
        return;
    L66:
        if (r84 != null) goto L68;
        if (r75 != null) goto L71;
    L68:
        if (r84 != null) goto L70;
        return;
    L70:
        if (r84.equals(r75) == false) goto L71;
        return;
    L151:
        return;
    L59:
        if ((MessagesController.UPDATE_MASK_CHAT_AVATAR & r74) == 0) goto L62;
    L50:
        if ((MessagesController.UPDATE_MASK_STATUS & r74) != 0) goto L55;
        if ((MessagesController.UPDATE_MASK_CHAT_NAME & r74) != 0) goto L55;
        if ((MessagesController.UPDATE_MASK_CHAT_MEMBERS & r74) == 0) goto L57;
    L159:
        return;
    L74:
        if (r7 != NotificationCenter.messagePlayingDidReset) goto L91;
        Integer r76 = (Integer) r9[0];
        ViewGroup r93 = this.messageContainer;
        if (r93 == null) goto L154;
        int r94 = r93.getChildCount();
    L78:
        if (r4 >= r94) goto L161;
        View r1 = this.messageContainer.getChildAt(r4);
        if (((Integer) r1.getTag()).intValue() != 3) goto L89;
        PopupAudioView r12 = (PopupAudioView) r1.findViewWithTag(300);
        MessageObject r3 = r12.getMessageObject();
        if (r3 == null) goto L89;
        if (r3.currentAccount != r8) goto L89;
        if (r3.getId() != r76.intValue()) goto L89;
        r12.updateButtonState();
        return;
    L89:
        r4 = r4 + 1;
        goto L78
    L161:
        return;
    L154:
        return;
    L91:
        if (r7 != NotificationCenter.messagePlayingProgressDidChanged) goto L108;
        Integer r77 = (Integer) r9[0];
        ViewGroup r95 = this.messageContainer;
        if (r95 == null) goto L155;
        int r96 = r95.getChildCount();
    L95:
        if (r4 >= r96) goto L162;
        View r13 = this.messageContainer.getChildAt(r4);
        if (((Integer) r13.getTag()).intValue() != 3) goto L106;
        PopupAudioView r14 = (PopupAudioView) r13.findViewWithTag(300);
        MessageObject r32 = r14.getMessageObject();
        if (r32 == null) goto L106;
        if (r32.currentAccount != r8) goto L106;
        if (r32.getId() != r77.intValue()) goto L106;
        r14.updateProgress();
        return;
    L106:
        r4 = r4 + 1;
        goto L95
    L162:
        return;
    L155:
        return;
    L108:
        if (r7 != NotificationCenter.emojiLoaded) goto L120;
        ViewGroup r78 = this.messageContainer;
        if (r78 == null) goto L156;
        int r79 = r78.getChildCount();
    L112:
        if (r4 >= r79) goto L163;
        View r85 = this.messageContainer.getChildAt(r4);
        if (((Integer) r85.getTag()).intValue() != 1) goto L118;
        TextView r86 = (TextView) r85.findViewWithTag(Integer.valueOf(MediaError.DetailedErrorCode.SEGMENT_NETWORK));
        if (r86 == null) goto L118;
        r86.invalidate();
    L118:
        r4 = r4 + 1;
        goto L112
    L163:
        return;
    L156:
        return;
    L120:
        if (r7 == NotificationCenter.contactsDidLoad) goto L122;
        return;
    L122:
        if (r8 != this.lastResumedAccount) goto L158;
        updateSubtitle();
        return;
    L158:
        return;
    L5:
        if (r8 != this.lastResumedAccount) goto L147;
        onFinish();
        finish();
        return;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.chatActivityEnterView.isPopupShowing() == false) goto L6;
        this.chatActivityEnterView.hidePopup(true);
        return;
    L6:
        super.onBackPressed();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        AndroidUtilities.checkDisplaySize(this, r1);
        AndroidUtilities.setPreferredMaxRefreshRate(getWindow());
        fixLayout();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle r19) {
        super.onCreate(r19);
        Theme.createDialogsResources(this);
        Theme.createChatResources(this, false);
        AndroidUtilities.fillStatusBarHeight(this, false);
        int r2 = 0;
    L4:
        if (r2 >= 16) goto L6;
        NotificationCenter.getInstance(r2).addObserver(this, NotificationCenter.appDidLogout);
        NotificationCenter.getInstance(r2).addObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(r2).addObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        NotificationCenter.getInstance(r2).addObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(r2).addObserver(this, NotificationCenter.contactsDidLoad);
        r2 = r2 + 1;
        goto L4
    L6:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pushMessagesUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        this.classGuid = ConnectionsManager.generateClassGuid();
        this.statusDrawables[0] = new TypingDotsDrawable(false);
        this.statusDrawables[1] = new RecordStatusDrawable(false);
        this.statusDrawables[2] = new SendingFileDrawable(false);
        this.statusDrawables[3] = new PlayingGameDrawable(false, null);
        this.statusDrawables[4] = new RoundStatusDrawable(false);
        AnonymousClass1 r22 = new AnonymousClass1(this, this);
        setContentView(r22);
        r22.setBackgroundColor(-1728053248);
        RelativeLayout r3 = new RelativeLayout(this);
        r22.addView(r3, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass2 r10 = new AnonymousClass2(this, this);
        this.popupContainer = r10;
        r10.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        r3.addView(this.popupContainer, LayoutHelper.createRelative(-1, 240, 12, 0, 12, 0, 13));
        ChatActivityEnterView r32 = this.chatActivityEnterView;
        if (r32 == null) goto L9;
        r32.onDestroy();
    L9:
        ChatActivityEnterView r33 = new ChatActivityEnterView(this, r22, null, false);
        this.chatActivityEnterView = r33;
        r33.setId(1000);
        this.popupContainer.addView(this.chatActivityEnterView, LayoutHelper.createRelative(-1, -2, 12));
        this.chatActivityEnterView.setDelegate(new AnonymousClass3(this));
        FrameLayoutTouch r23 = new FrameLayoutTouch(this, this);
        this.messageContainer = r23;
        this.popupContainer.addView(r23, 0);
        ActionBar r24 = new ActionBar(this);
        this.actionBar = r24;
        r24.setOccupyStatusBar(false);
        this.actionBar.setBackButtonImage(R.drawable.ic_close_white);
        this.actionBar.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefault));
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSelector), false);
        this.popupContainer.addView(this.actionBar);
        ViewGroup.LayoutParams r25 = this.actionBar.getLayoutParams();
        r25.width = -1;
        this.actionBar.setLayoutParams(r25);
        ActionBarMenuItem r26 = this.actionBar.createMenu().addItemWithWidth(2, 0, AndroidUtilities.dp(56.0f));
        TextView r34 = new TextView(this);
        this.countText = r34;
        int r5 = Theme.key_actionBarDefaultSubtitle;
        r34.setTextColor(Theme.getColor(r5));
        this.countText.setTextSize(1, 14.0f);
        this.countText.setGravity(17);
        r26.addView(this.countText, LayoutHelper.createFrame(56, -1.0f));
        FrameLayout r27 = new FrameLayout(this);
        this.avatarContainer = r27;
        r27.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        this.actionBar.addView(this.avatarContainer);
        FrameLayout.LayoutParams r28 = (FrameLayout.LayoutParams) this.avatarContainer.getLayoutParams();
        r28.height = -1;
        r28.width = -2;
        r28.rightMargin = AndroidUtilities.dp(48.0f);
        r28.leftMargin = AndroidUtilities.dp(60.0f);
        r28.gravity = 51;
        this.avatarContainer.setLayoutParams(r28);
        BackupImageView r29 = new BackupImageView(this);
        this.avatarImageView = r29;
        r29.setRoundRadius(ExteraConfig.getAvatarCorners(42.0f));
        this.avatarContainer.addView(this.avatarImageView);
        FrameLayout.LayoutParams r210 = (FrameLayout.LayoutParams) this.avatarImageView.getLayoutParams();
        r210.width = AndroidUtilities.dp(42.0f);
        r210.height = AndroidUtilities.dp(42.0f);
        r210.topMargin = AndroidUtilities.dp(3.0f);
        this.avatarImageView.setLayoutParams(r210);
        TextView r211 = new TextView(this);
        this.nameTextView = r211;
        r211.setTextColor(Theme.getColor(Theme.key_actionBarDefaultTitle));
        this.nameTextView.setTextSize(1, 18.0f);
        this.nameTextView.setLines(1);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setSingleLine(true);
        TextView r212 = this.nameTextView;
        TextUtils.TruncateAt r8 = TextUtils.TruncateAt.END;
        r212.setEllipsize(r8);
        this.nameTextView.setGravity(3);
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        this.avatarContainer.addView(this.nameTextView);
        FrameLayout.LayoutParams r213 = (FrameLayout.LayoutParams) this.nameTextView.getLayoutParams();
        r213.width = -2;
        r213.height = -2;
        r213.leftMargin = AndroidUtilities.dp(54.0f);
        r213.bottomMargin = AndroidUtilities.dp(22.0f);
        r213.gravity = 80;
        this.nameTextView.setLayoutParams(r213);
        TextView r214 = new TextView(this);
        this.onlineTextView = r214;
        r214.setTextColor(Theme.getColor(r5));
        this.onlineTextView.setTextSize(1, 14.0f);
        this.onlineTextView.setLines(1);
        this.onlineTextView.setMaxLines(1);
        this.onlineTextView.setSingleLine(true);
        this.onlineTextView.setEllipsize(r8);
        this.onlineTextView.setGravity(3);
        this.avatarContainer.addView(this.onlineTextView);
        FrameLayout.LayoutParams r215 = (FrameLayout.LayoutParams) this.onlineTextView.getLayoutParams();
        r215.width = -2;
        r215.height = -2;
        r215.leftMargin = AndroidUtilities.dp(54.0f);
        r215.bottomMargin = AndroidUtilities.dp(4.0f);
        r215.gravity = 80;
        this.onlineTextView.setLayoutParams(r215);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass4(this));
        PowerManager.WakeLock r216 = ((PowerManager) ApplicationLoader.applicationContext.getSystemService("power")).newWakeLock(268435462, "screen");
        this.wakeLock = r216;
        r216.setReferenceCounted(false);
        handleIntent(getIntent());
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        onFinish();
        MediaController.getInstance().setFeedbackView(this.chatActivityEnterView, false);
        if (this.wakeLock.isHeld() == false) goto L5;
        this.wakeLock.release();
    L5:
        BackupImageView r3 = this.avatarImageView;
        if (r3 == null) goto L9;
        r3.setImageDrawable(null);
        return;
    }

    public void onFinish() {
        if (this.finished == true) goto L21;
        this.finished = true;
        if (this.isReply == false) goto L8;
        this.popupMessages.clear();
    L8:
        int r0 = 0;
    L10:
        if (r0 >= 16) goto L12;
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.appDidLogout);
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.contactsDidLoad);
        r0 = r0 + 1;
        goto L10
    L12:
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pushMessagesUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        ChatActivityEnterView r02 = this.chatActivityEnterView;
        if (r02 == null) goto L16;
        r02.onDestroy();
    L16:
        if (this.wakeLock.isHeld() == false) goto L20;
        this.wakeLock.release();
        return;
    L20:
        return;
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent r1) {
        super.onNewIntent(r1);
        handleIntent(r1);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
        ChatActivityEnterView r1 = this.chatActivityEnterView;
        if (r1 == null) goto L5;
        r1.hidePopup(false);
        this.chatActivityEnterView.setFieldFocused(false);
    L5:
        int r2 = this.lastResumedAccount;
        if (r2 < 0) goto L9;
        ConnectionsManager.getInstance(r2).setAppPaused(true, false);
        return;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int r1, String[] r2, int[] r3) {
        super.onRequestPermissionsResult(r1, r2, r3);
        if (r1 == 3) goto L5;
        return;
    L5:
        if (r3[0] == 0) goto L10;
        AlertDialog.Builder r12 = new AlertDialog.Builder(this);
        r12.setTitle(LocaleController.getString(R.string.AppName));
        r12.setMessage(LocaleController.getString(R.string.PermissionNoAudioWithHint));
        r12.setNegativeButton(LocaleController.getString(R.string.PermissionOpenSettings), new PopupNotificationActivity$$ExternalSyntheticLambda3(this));
        r12.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r12.show();
        return;
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        MediaController.getInstance().setFeedbackView(this.chatActivityEnterView, true);
        ChatActivityEnterView r0 = this.chatActivityEnterView;
        if (r0 == null) goto L5;
        r0.setFieldFocused(true);
    L5:
        fixLayout();
        checkAndUpdateAvatar();
        this.wakeLock.acquire(7000);
    }

    public boolean onTouchEventMy(MotionEvent r14) {
        int r1 = 0;
        if (checkTransitionAnimation() == false) goto L5;
        return false;
    L5:
        if (r14 == null) goto L10;
        if (r14.getAction() != 0) goto L10;
        this.moveStartX = r14.getX();
    L98:
        return this.startedMoving;
    L10:
        if (r14 == null) goto L40;
        if (r14.getAction() != 2) goto L40;
        float r2 = r14.getX();
        float r4 = this.moveStartX;
        int r5 = (int) (r2 - r4);
        if (r4 == (-1.0f)) goto L25;
        if (this.startedMoving == true) goto L25;
        if (Math.abs(r5) <= AndroidUtilities.dp(10.0f)) goto L25;
        this.startedMoving = true;
        this.moveStartX = r2;
        AndroidUtilities.lockOrientation(this);
        VelocityTracker r0 = this.velocityTracker;
        if (r0 != null) goto L22;
        this.velocityTracker = VelocityTracker.obtain();
    L23:
        r5 = 0;
        goto L25
    L22:
        r0.clear();
    L25:
        if (this.startedMoving == false) goto L98;
        if (this.leftView != null) goto L31;
        if (r5 <= 0) goto L31;
        r5 = 0;
    L31:
        if (this.rightView != null) goto L34;
        if (r5 >= 0) goto L34;
    L35:
        VelocityTracker r02 = this.velocityTracker;
        if (r02 == null) goto L38;
        r02.addMovement(r14);
    L38:
        applyViewsLayoutParams(r1);
    L34:
        r1 = r5;
    L40:
        if (r14 == null) goto L46;
        if (r14.getAction() == 1) goto L46;
        if (r14.getAction() != 3) goto L98;
    L46:
        if (r14 != null) goto L48;
    L92:
        applyViewsLayoutParams(0);
    L93:
        VelocityTracker r142 = this.velocityTracker;
        if (r142 == null) goto L96;
        r142.recycle();
        this.velocityTracker = null;
    L96:
        this.startedMoving = false;
        this.moveStartX = -1.0f;
        goto L98
    L48:
        if (this.startedMoving == false) goto L92;
        int r143 = (int) (r14.getX() - this.moveStartX);
        int r6 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(24.0f);
        VelocityTracker r7 = this.velocityTracker;
        if (r7 == null) goto L57;
        r7.computeCurrentVelocity(1000);
        if (this.velocityTracker.getXVelocity() < 3500.0f) goto L55;
        char r72 = 1;
    L59:
        if (r72 == 1) goto L63;
        if (r143 > (r6 / 3)) goto L63;
    L65:
        if (r72 == 2) goto L69;
        if (r143 < ((-r6) / 3)) goto L69;
    L72:
        if (this.centerView.getTranslationX() == 0.0f) goto L81;
        float r22 = -this.centerView.getTranslationX();
        if (r143 <= 0) goto L76;
        ViewGroup r42 = this.leftView;
    L77:
        if (r143 <= 0) goto L79;
        ViewGroup r144 = this.leftButtonsView;
    L80:
        this.onAnimationEndRunnable = new PopupNotificationActivity$$ExternalSyntheticLambda2(this);
        ViewGroup r12 = r42;
        ViewGroup r43 = r144;
        float r145 = r22;
        ViewGroup r23 = r12;
    L83:
        if (r145 == 0.0f) goto L93;
        int r62 = (int) (Math.abs(r145 / r6) * 200.0f);
        ArrayList r73 = new ArrayList();
        ViewGroup r8 = this.centerView;
        r73.add(ObjectAnimator.ofFloat(r8, "translationX", new float[]{r8.getTranslationX() + r145}));
        ViewGroup r82 = this.centerButtonsView;
        if (r82 == null) goto L87;
        r73.add(ObjectAnimator.ofFloat(r82, "translationX", new float[]{r82.getTranslationX() + r145}));
    L87:
        if (r23 == null) goto L89;
        r73.add(ObjectAnimator.ofFloat(r23, "translationX", new float[]{r23.getTranslationX() + r145}));
    L89:
        if (r43 == null) goto L91;
        r73.add(ObjectAnimator.ofFloat(r43, "translationX", new float[]{r43.getTranslationX() + r145}));
    L91:
        AnimatorSet r146 = new AnimatorSet();
        r146.playTogether(r73);
        r146.setDuration(r62);
        r146.addListener(new AnonymousClass5(this));
        r146.start();
        this.animationInProgress = true;
        this.animationStartTime = System.currentTimeMillis();
        goto L93
    L79:
        r144 = this.rightButtonsView;
        goto L80
    L76:
        r42 = this.rightView;
        goto L77
    L81:
        r23 = null;
        r43 = null;
        r145 = 0.0f;
    L69:
        if (this.rightView == null) goto L72;
        r145 = (-r6) - this.centerView.getTranslationX();
        r23 = this.rightView;
        r43 = this.rightButtonsView;
        this.onAnimationEndRunnable = new PopupNotificationActivity$$ExternalSyntheticLambda1(this);
    L63:
        if (this.leftView == null) goto L65;
        r145 = r6 - this.centerView.getTranslationX();
        r23 = this.leftView;
        r43 = this.leftButtonsView;
        this.onAnimationEndRunnable = new PopupNotificationActivity$$ExternalSyntheticLambda0(this);
        goto L83
    L55:
        if (this.velocityTracker.getXVelocity() > (-3500.0f)) goto L57;
        r72 = 2;
    L57:
        r72 = 0;
        goto L59
    }
}
