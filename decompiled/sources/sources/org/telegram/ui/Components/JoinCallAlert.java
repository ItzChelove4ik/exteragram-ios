package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Cells.ShareDialogCell;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class JoinCallAlert extends BottomSheet {
    private static ArrayList<TLRPC.Peer> cachedChats;
    private static long lastCacheDid;
    private static long lastCacheTime;
    private static int lastCachedAccount;
    private boolean animationInProgress;
    private ArrayList<TLRPC.Peer> chats;
    private TLRPC.Peer currentPeer;
    private int currentType;
    private JoinCallAlertDelegate delegate;
    private BottomSheetCell doneButton;
    private boolean ignoreLayout;
    private RecyclerListView listView;
    private int[] location;
    private TextView messageTextView;
    private boolean schedule;
    private int scrollOffsetY;
    private TLRPC.InputPeer selectAfterDismiss;
    private TLRPC.Peer selectedPeer;
    private Drawable shadowDrawable;
    private TextView textView;

    /* renamed from: org.telegram.ui.Components.JoinCallAlert$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayout {
        boolean sorted;
        final /* synthetic */ JoinCallAlert this$0;

        public AnonymousClass1(JoinCallAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            if (JoinCallAlert.m11167$$Nest$fgetcurrentType(this.this$0) != 0) goto L22;
            int r0 = View.MeasureSpec.getSize(r6);
            int r1 = JoinCallAlert.m11166$$Nest$fgetchats(this.this$0).size() * AndroidUtilities.dp(95.0f);
            LinearLayout.LayoutParams r2 = (LinearLayout.LayoutParams) JoinCallAlert.m11169$$Nest$fgetlistView(this.this$0).getLayoutParams();
            if (r1 <= r0) goto L12;
            r2.width = -1;
            r2.gravity = 51;
            if (this.sorted == true) goto L22;
            if (JoinCallAlert.m11172$$Nest$fgetselectedPeer(this.this$0) == null) goto L11;
            JoinCallAlert.m11166$$Nest$fgetchats(this.this$0).remove(JoinCallAlert.m11172$$Nest$fgetselectedPeer(this.this$0));
            JoinCallAlert.m11166$$Nest$fgetchats(this.this$0).add(0, JoinCallAlert.m11172$$Nest$fgetselectedPeer(this.this$0));
        L11:
            this.sorted = true;
            goto L22
        L12:
            r2.width = -2;
            r2.gravity = 49;
            if (this.sorted == true) goto L22;
            if (JoinCallAlert.m11172$$Nest$fgetselectedPeer(this.this$0) == null) goto L21;
            int r02 = JoinCallAlert.m11166$$Nest$fgetchats(this.this$0).size() % 2;
            JoinCallAlert r12 = this.this$0;
            if (r02 != 0) goto L19;
            int r03 = Math.max(0, (JoinCallAlert.m11166$$Nest$fgetchats(r12).size() / 2) - 1);
        L20:
            JoinCallAlert.m11166$$Nest$fgetchats(this.this$0).remove(JoinCallAlert.m11172$$Nest$fgetselectedPeer(this.this$0));
            JoinCallAlert.m11166$$Nest$fgetchats(this.this$0).add(r03, JoinCallAlert.m11172$$Nest$fgetselectedPeer(this.this$0));
            goto L21
        L19:
            r03 = JoinCallAlert.m11166$$Nest$fgetchats(r12).size() / 2;
        L21:
            this.sorted = true;
        L22:
            super.onMeasure(r6, r7);
        }
    }

    /* renamed from: org.telegram.ui.Components.JoinCallAlert$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ JoinCallAlert this$0;

        public AnonymousClass2(JoinCallAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            JoinCallAlert.m11173$$Nest$fgetshadowDrawable(this.this$0).setBounds(0, JoinCallAlert.m11171$$Nest$fgetscrollOffsetY(this.this$0) - JoinCallAlert.access$100(this.this$0), getMeasuredWidth(), getMeasuredHeight());
            JoinCallAlert.m11173$$Nest$fgetshadowDrawable(this.this$0).draw(r6);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L11;
            if (JoinCallAlert.m11171$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L11;
            if (r3.getY() >= JoinCallAlert.m11171$$Nest$fgetscrollOffsetY(this.this$0)) goto L11;
            this.this$0.dismiss();
            return true;
        L11:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            JoinCallAlert.m11176$$Nest$mupdateLayout(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            int r0 = View.MeasureSpec.getSize(r10) - AndroidUtilities.statusBarHeight;
            measureChildWithMargins(JoinCallAlert.m11170$$Nest$fgetmessageTextView(this.this$0), r9, 0, r10, 0);
            int r8 = JoinCallAlert.m11170$$Nest$fgetmessageTextView(this.this$0).getMeasuredHeight();
            FrameLayout.LayoutParams r92 = (FrameLayout.LayoutParams) JoinCallAlert.m11169$$Nest$fgetlistView(this.this$0).getLayoutParams();
            r92.topMargin = AndroidUtilities.dp(65.0f) + r8;
            getMeasuredWidth();
            int r93 = (((AndroidUtilities.dp(80.0f) + (JoinCallAlert.m11166$$Nest$fgetchats(this.this$0).size() * AndroidUtilities.dp(58.0f))) + JoinCallAlert.access$000(this.this$0)) + AndroidUtilities.dp(55.0f)) + r8;
            int r82 = r0 / 5;
            if (r93 >= (r82 * 3)) goto L5;
            int r83 = r0 - r93;
        L7:
            if (JoinCallAlert.m11169$$Nest$fgetlistView(this.this$0).getPaddingTop() == r83) goto L9;
            JoinCallAlert.m11175$$Nest$fputignoreLayout(this.this$0, true);
            JoinCallAlert.m11169$$Nest$fgetlistView(this.this$0).setPadding(0, r83, 0, 0);
            JoinCallAlert.m11175$$Nest$fputignoreLayout(this.this$0, false);
        L9:
            super.onMeasure(r9, View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30));
            return;
        L5:
            r83 = r82 * 2;
            goto L7
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
            if (JoinCallAlert.m11168$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.JoinCallAlert$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ JoinCallAlert this$0;

        public AnonymousClass3(JoinCallAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (JoinCallAlert.m11168$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.JoinCallAlert$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ JoinCallAlert this$0;

        public AnonymousClass4(JoinCallAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            JoinCallAlert.m11176$$Nest$mupdateLayout(this.this$0);
        }
    }

    public class BottomSheetCell extends FrameLayout {
        private View background;
        private boolean hasBackground;
        private CharSequence text;
        private TextView[] textView;
        final /* synthetic */ JoinCallAlert this$0;

        /* renamed from: org.telegram.ui.Components.JoinCallAlert$BottomSheetCell$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ BottomSheetCell this$1;

            public AnonymousClass1(BottomSheetCell r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r5) {
                JoinCallAlert.m11174$$Nest$fputanimationInProgress(this.this$1.this$0, false);
                TextView r52 = BottomSheetCell.m11178$$Nest$fgettextView(this.this$1)[0];
                BottomSheetCell.m11178$$Nest$fgettextView(this.this$1)[0] = BottomSheetCell.m11178$$Nest$fgettextView(this.this$1)[1];
                BottomSheetCell.m11178$$Nest$fgettextView(this.this$1)[1] = r52;
            }
        }

        /* renamed from: -$$Nest$fgetbackground, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m11177$$Nest$fgetbackground(BottomSheetCell r0) {
            return r0.background;
        }

        /* renamed from: -$$Nest$fgettextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView[] m11178$$Nest$fgettextView(BottomSheetCell r0) {
            return r0.textView;
        }

        public BottomSheetCell(JoinCallAlert r12, Context r13, boolean r14) {
            this.this$0 = r12;
            super(r13);
            this.textView = new TextView[2];
            this.hasBackground = !r14;
            setBackground(null);
            View r0 = new View(r13);
            this.background = r0;
            if (this.hasBackground == false) goto L5;
            r0.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{4.0f}));
        L5:
            View r02 = this.background;
            if (r14 == false) goto L8;
            float r8 = 0.0f;
        L9:
            addView(r02, LayoutHelper.createFrame(-1, -1.0f, 0, 16.0f, r8, 16.0f, 16.0f));
            int r142 = 0;
        L10:
            if (r142 >= 2) goto L23;
            this.textView[r142] = new TextView(r13);
            this.textView[r142].setFocusable(false);
            this.textView[r142].setLines(1);
            this.textView[r142].setSingleLine(true);
            this.textView[r142].setGravity(1);
            this.textView[r142].setEllipsize(TextUtils.TruncateAt.END);
            this.textView[r142].setGravity(17);
            boolean r03 = this.hasBackground;
            TextView[] r4 = this.textView;
            if (r03 == false) goto L14;
            r4[r142].setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
            this.textView[r142].setTypeface(AndroidUtilities.bold());
        L15:
            this.textView[r142].setImportantForAccessibility(2);
            this.textView[r142].setTextSize(1, 14.0f);
            TextView r04 = this.textView[r142];
            if (this.hasBackground == false) goto L18;
            int r42 = 0;
        L19:
            r04.setPadding(0, 0, 0, r42);
            addView(this.textView[r142], LayoutHelper.createFrame(-2, -2.0f, 17, 24.0f, 0.0f, 24.0f, 0.0f));
            if (r142 != 1) goto L22;
            this.textView[r142].setAlpha(0.0f);
        L22:
            r142 = r142 + 1;
            goto L10
        L18:
            r42 = AndroidUtilities.dp(13.0f);
            goto L19
        L14:
            r4[r142].setTextColor(Theme.getColor(Theme.key_featuredStickers_addButton));
            goto L15
        L23:
            return;
        L8:
            r8 = 16.0f;
            goto L9
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.setClassName("android.widget.Button");
            r1.setClickable(true);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            if (this.hasBackground == false) goto L5;
            float r32 = 80.0f;
        L6:
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r32), TLObject.FLAG_30));
            return;
        L5:
            r32 = 50.0f;
            goto L6
        }

        public void setText(CharSequence r11, boolean r12) {
            this.text = r11;
            TextView[] r0 = this.textView;
            if (r12 == true) goto L6;
            r0[0].setText(r11);
            return;
        L6:
            r0[1].setText(r11);
            JoinCallAlert.m11174$$Nest$fputanimationInProgress(this.this$0, true);
            AnimatorSet r112 = new AnimatorSet();
            r112.setDuration(180);
            r112.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            Property r4 = View.ALPHA;
            Property r6 = View.TRANSLATION_Y;
            r112.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.textView[0], r4, new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(this.textView[0], r6, new float[]{0.0f, -AndroidUtilities.dp(10.0f)}), ObjectAnimator.ofFloat(this.textView[1], r4, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this.textView[1], r6, new float[]{AndroidUtilities.dp(10.0f), 0.0f})});
            r112.addListener(new AnonymousClass1(this));
            r112.start();
        }
    }

    public interface JoinCallAlertDelegate {
        void didSelectChat(TLRPC.InputPeer r1, boolean r2, boolean r3, boolean r4);
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context context;
        final /* synthetic */ JoinCallAlert this$0;

        public ListAdapter(JoinCallAlert r1, Context r2) {
            this.this$0 = r1;
            this.context = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return JoinCallAlert.m11166$$Nest$fgetchats(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            long r0 = MessageObject.getPeerId((TLRPC.Peer) JoinCallAlert.m11166$$Nest$fgetchats(this.this$0).get(r10));
            JoinCallAlert r3 = this.this$0;
            if (r0 <= 0) goto L5;
            TLObject r2 = MessagesController.getInstance(JoinCallAlert.access$200(r3)).getUser(Long.valueOf(r0));
            String r32 = LocaleController.getString(R.string.VoipGroupPersonalAccount);
        L6:
            boolean r6 = false;
            if (JoinCallAlert.m11167$$Nest$fgetcurrentType(this.this$0) != 0) goto L13;
            ShareDialogCell r92 = (ShareDialogCell) r9.itemView;
            if (r0 != MessageObject.getPeerId(JoinCallAlert.m11172$$Nest$fgetselectedPeer(this.this$0))) goto L11;
            r6 = true;
        L11:
            r92.setDialog(r0, r6, null);
            return;
        L13:
            GroupCreateUserCell r93 = (GroupCreateUserCell) r9.itemView;
            if (r10 == (getItemCount() - 1)) goto L16;
            r6 = true;
        L16:
            r93.setObject(r2, null, r32, r6);
            return;
        L5:
            r2 = MessagesController.getInstance(JoinCallAlert.access$300(r3)).getChat(Long.valueOf(-r0));
            r32 = null;
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            if (JoinCallAlert.m11167$$Nest$fgetcurrentType(this.this$0) != 0) goto L5;
            View r92 = new ShareDialogCell(this.context, 2, null);
            r92.setLayoutParams(new RecyclerView.LayoutParams(AndroidUtilities.dp(80.0f), AndroidUtilities.dp(100.0f)));
        L12:
            return new RecyclerListView.Holder(r92);
        L5:
            Context r2 = this.context;
            if (JoinCallAlert.m11167$$Nest$fgetcurrentType(this.this$0) != 2) goto L9;
            boolean r8 = true;
        L10:
            r92 = new GroupCreateUserCell(r2, 2, 0, false, r8, null);
            goto L12
        L9:
            r8 = false;
            goto L10
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r7) {
            r7.getAdapterPosition();
            long r0 = MessageObject.getPeerId(JoinCallAlert.m11172$$Nest$fgetselectedPeer(this.this$0));
            View r6 = r7.itemView;
            boolean r2 = true;
            if ((r6 instanceof GroupCreateUserCell) == false) goto L17;
            GroupCreateUserCell r62 = (GroupCreateUserCell) r6;
            Object r72 = r62.getObject();
            if (r72 != null) goto L7;
            long r4 = 0;
        L12:
            if (r0 == r4) goto L15;
            r2 = false;
        L15:
            r62.setChecked(r2, false);
            return;
        L7:
            if ((r72 instanceof TLRPC.Chat) == false) goto L9;
            r4 = -((TLRPC.Chat) r72).id;
            goto L12
        L9:
            r4 = ((TLRPC.User) r72).id;
            goto L12
        L17:
            ShareDialogCell r63 = (ShareDialogCell) r6;
            if (r0 == r63.getCurrentDialog()) goto L21;
            r2 = false;
        L21:
            r63.setChecked(r2, false);
        }
    }

    public static /* synthetic */ void $r8$lambda$0KTXojNZoxzQ37NO8si2x_aBbss(AccountInstance r0, int r1, DialogInterface r2) {
        r0.getConnectionsManager().cancelRequest(r1, true);
    }

    public static /* synthetic */ void $r8$lambda$70ZW1ygh4lEyGwNrbLJBvkhCsZQ(AlertDialog r3, AccountInstance r4, JoinCallAlertDelegate r5, long r6, Context r8, BaseFragment r9, int r10, TLRPC.Peer r11, TLObject r12, TLRPC.TL_error r13) {
        AndroidUtilities.runOnUIThread(new JoinCallAlert$$ExternalSyntheticLambda8(r3, r12, r4, r5, r6, r8, r9, r10, r11));
    }

    public static /* synthetic */ void $r8$lambda$AqyEI7RazbIJ2c1ee_uq1nIHrLs(JoinCallAlert r0, View r1) {
        r0.lambda$new$8(r1);
    }

    /* renamed from: $r8$lambda$CfMbXpJbAAye0f5VN-Cg9opDVkk, reason: not valid java name */
    public static /* synthetic */ void m11163$r8$lambda$CfMbXpJbAAye0f5VNCg9opDVkk(AccountInstance r0, int r1, DialogInterface r2) {
        r0.getConnectionsManager().cancelRequest(r1, true);
    }

    /* renamed from: $r8$lambda$V1wauqt2O0Je46IaESM-YiZZZ6M, reason: not valid java name */
    public static /* synthetic */ void m11164$r8$lambda$V1wauqt2O0Je46IaESMYiZZZ6M(AlertDialog r0, TLObject r1, long r2, AccountInstance r4, MessagesStorage.BooleanCallback r5) {
        r0.dismiss();     // Catch: Exception -> L4
    L6:
        if (r1 == null) goto L14;
        TL_phone.joinAsPeers r12 = (TL_phone.joinAsPeers) r1;
        cachedChats = r12.peers;
        lastCacheDid = r2;
        lastCacheTime = SystemClock.elapsedRealtime();
        lastCachedAccount = r4.getCurrentAccount();
        boolean r3 = false;
        r4.getMessagesController().putChats(r12.chats, false);
        r4.getMessagesController().putUsers(r12.users, false);
        if (r12.peers.size() != 1) goto L10;
        r3 = true;
    L10:
        r5.run(r3);
        return;
    L14:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$Ve_JMhP7mYnC3dqafaHoLNlPvqA(AlertDialog r2, long r3, AccountInstance r5, MessagesStorage.BooleanCallback r6, TLObject r7, TLRPC.TL_error r8) {
        AndroidUtilities.runOnUIThread(new JoinCallAlert$$ExternalSyntheticLambda4(r2, r7, r3, r5, r6));
    }

    public static /* synthetic */ void $r8$lambda$WulgJK5bRda5E1DckB3UPxblCDI(AlertDialog r10, TLObject r11, AccountInstance r12, JoinCallAlertDelegate r13, long r14, Context r16, BaseFragment r17, int r18, TLRPC.Peer r19) {
        r10.dismiss();     // Catch: Exception -> L4
    L6:
        if (r11 == null) goto L14;
        TL_phone.joinAsPeers r112 = (TL_phone.joinAsPeers) r11;
        if (r112.peers.size() != 1) goto L10;
        r13.didSelectChat(r12.getMessagesController().getInputPeer(MessageObject.getPeerId(r112.peers.get(0))), false, false, false);
        return;
    L10:
        cachedChats = r112.peers;
        lastCacheDid = r14;
        lastCacheTime = SystemClock.elapsedRealtime();
        lastCachedAccount = r12.getCurrentAccount();
        r12.getMessagesController().putChats(r112.chats, false);
        r12.getMessagesController().putUsers(r112.users, false);
        showAlert(r16, r14, r112.peers, r17, r18, r19, r13);
        return;
    L14:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$k1NCN5SoaQ5RJqkGpaYDDzEw4ms(JoinCallAlert r0, JoinCallAlertDelegate r1, View r2) {
        r0.lambda$new$7(r1, r2);
    }

    /* renamed from: $r8$lambda$uf4jepFKF-Tyn3DW4hAmZu2Kh-w, reason: not valid java name */
    public static /* synthetic */ void m11165$r8$lambda$uf4jepFKFTyn3DW4hAmZu2Khw(JoinCallAlert r0, TLRPC.Chat r1, View r2, int r3) {
        r0.lambda$new$6(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetchats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11166$$Nest$fgetchats(JoinCallAlert r0) {
        return r0.chats;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11167$$Nest$fgetcurrentType(JoinCallAlert r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11168$$Nest$fgetignoreLayout(JoinCallAlert r0) {
        return r0.ignoreLayout;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m11169$$Nest$fgetlistView(JoinCallAlert r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetmessageTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m11170$$Nest$fgetmessageTextView(JoinCallAlert r0) {
        return r0.messageTextView;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11171$$Nest$fgetscrollOffsetY(JoinCallAlert r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetselectedPeer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Peer m11172$$Nest$fgetselectedPeer(JoinCallAlert r0) {
        return r0.selectedPeer;
    }

    /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m11173$$Nest$fgetshadowDrawable(JoinCallAlert r0) {
        return r0.shadowDrawable;
    }

    /* renamed from: -$$Nest$fputanimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11174$$Nest$fputanimationInProgress(JoinCallAlert r0, boolean r1) {
        r0.animationInProgress = r1;
    }

    /* renamed from: -$$Nest$fputignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11175$$Nest$fputignoreLayout(JoinCallAlert r0, boolean r1) {
        r0.ignoreLayout = r1;
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11176$$Nest$mupdateLayout(JoinCallAlert r0) {
        r0.updateLayout();
    }

    private JoinCallAlert(Context r20, long r21, ArrayList<TLRPC.Peer> r23, int r24, TLRPC.Peer r25, JoinCallAlertDelegate r26) {
        super(r20, false);
        this.location = new int[2];
        setApplyBottomPadding(false);
        this.chats = new ArrayList(r23);
        this.delegate = r26;
        this.currentType = r24;
        Drawable r6 = r20.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        this.shadowDrawable = r6;
        if (r24 == 2) goto L5;
        int r8 = Theme.getColor(Theme.key_dialogBackground);
        r6.setColorFilter(new PorterDuffColorFilter(r8, PorterDuff.Mode.MULTIPLY));
        this.selectedPeer = this.chats.get(0);
    L22:
        fixNavigationBar(r8);
        if (this.currentType != 0) goto L25;
        AnonymousClass1 r62 = new AnonymousClass1(this, r20);
        r62.setOrientation(1);
        NestedScrollView r82 = new NestedScrollView(r20);
        r82.addView(r62);
        setCustomView(r82);
        ViewGroup r63 = r62;
    L26:
        TLRPC.Chat r83 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r21));
        AnonymousClass3 r9 = new AnonymousClass3(this, r20);
        this.listView = r9;
        Context r11 = getContext();
        if (this.currentType != 0) goto L29;
        int r12 = 0;
    L30:
        r9.setLayoutManager(new LinearLayoutManager(r11, r12, false));
        this.listView.setAdapter(new ListAdapter(this, r20));
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setClipToPadding(false);
        this.listView.setEnabled(true);
        this.listView.setSelectorDrawableColor(0);
        this.listView.setGlowColor(Theme.getColor(Theme.key_dialogScrollGlow));
        this.listView.setOnScrollListener(new AnonymousClass4(this));
        this.listView.setOnItemClickListener(new JoinCallAlert$$ExternalSyntheticLambda5(this, r83));
        RecyclerListView r92 = this.listView;
        if (r24 == 0) goto L33;
        r63.addView(r92, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 100.0f, 0.0f, 80.0f));
    L34:
        if (r24 != 0) goto L36;
        RLottieImageView r93 = new RLottieImageView(r20);
        r93.setAutoRepeat(true);
        r93.setAnimation(R.raw.utyan_schedule, 120, 120);
        r93.playAnimation();
        r63.addView(r93, LayoutHelper.createLinear(160, 160, 49, 17, 8, 17, 0));
    L36:
        TextView r94 = new TextView(r20);
        this.textView = r94;
        r94.setTypeface(AndroidUtilities.bold());
        this.textView.setTextSize(1, 20.0f);
        TextView r95 = this.textView;
        if (r24 != 2) goto L39;
        r95.setTextColor(Theme.getColor(Theme.key_voipgroup_nameText));
    L40:
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        if (r24 != 0) goto L47;
        boolean r96 = ChatObject.isChannelOrGiga(r83);
        TextView r10 = this.textView;
        if (r96 == false) goto L45;
        r10.setText(LocaleController.getString(R.string.StartVoipChannelTitle));
    L46:
        r63.addView(this.textView, LayoutHelper.createLinear(-2, -2, 49, 23, 16, 23, 0));
    L54:
        TextView r97 = new TextView(getContext());
        this.messageTextView = r97;
        if (r24 != 2) goto L57;
        r97.setTextColor(Theme.getColor(Theme.key_voipgroup_lastSeenText));
    L58:
        this.messageTextView.setTextSize(1, 14.0f);
        int r5 = this.chats.size();
        int r98 = 0;
    L59:
        if (r98 >= r5) goto L68;
        long r102 = MessageObject.getPeerId(this.chats.get(r98));
        if (r102 >= 0) goto L67;
        TLRPC.Chat r103 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r102));
        if (ChatObject.isChannel(r103) == false) goto L66;
        if (r103.megagroup == false) goto L67;
    L66:
        boolean r52 = true;
    L69:
        this.messageTextView.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        this.messageTextView.setLinkTextColor(Theme.getColor(Theme.key_dialogTextLink));
        if (r24 != 0) goto L82;
        StringBuilder r53 = new StringBuilder();
        if (ChatObject.isChannel(r83) == true) goto L74;
    L76:
        r53.append(LocaleController.getString(R.string.VoipGroupStart2));
    L78:
        if (this.chats.size() <= 1) goto L80;
        r53.append("\n\n");
        r53.append(LocaleController.getString(R.string.VoipChatDisplayedAs));
    L81:
        this.messageTextView.setText(r53);
        this.messageTextView.setGravity(49);
        r63.addView(this.messageTextView, LayoutHelper.createLinear(-2, -2, 49, 23, 0, 23, 5));
    L91:
        if (r24 != 0) goto L98;
        RecyclerListView r2 = this.listView;
        if (this.chats.size() >= 5) goto L96;
        int r54 = -2;
    L97:
        r63.addView(r2, LayoutHelper.createLinear(r54, 95, 49, 0, 6, 0, 0));
        goto L98
    L96:
        r54 = -1;
    L98:
        BottomSheetCell r22 = new BottomSheetCell(this, r20, false);
        this.doneButton = r22;
        BottomSheetCell.m11177$$Nest$fgetbackground(r22).setOnClickListener(new JoinCallAlert$$ExternalSyntheticLambda6(this, r26));
        int r27 = this.currentType;
        BottomSheetCell r3 = this.doneButton;
        if (r27 != 0) goto L105;
        r63.addView(r3, LayoutHelper.createLinear(-1, 50, 51, 0, 0, 0, 0));
        BottomSheetCell r28 = new BottomSheetCell(this, r20, true);
        if (ChatObject.isChannelOrGiga(r83) == false) goto L103;
        r28.setText(LocaleController.getString(R.string.VoipChannelScheduleVoiceChat), false);
    L104:
        BottomSheetCell.m11177$$Nest$fgetbackground(r28).setOnClickListener(new JoinCallAlert$$ExternalSyntheticLambda7(this));
        r63.addView(r28, LayoutHelper.createLinear(-1, 50, 51, 0, 0, 0, 0));
    L106:
        updateDoneButton(false, r83);
        return;
    L103:
        r28.setText(LocaleController.getString(R.string.VoipGroupScheduleVoiceChat), false);
        goto L104
    L105:
        r63.addView(r3, LayoutHelper.createFrame(-1, 50.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
        goto L106
    L80:
        this.listView.setVisibility(8);
        goto L81
    L74:
        if (r83.megagroup == true) goto L76;
        r53.append(LocaleController.getString(R.string.VoipChannelStart2));
        goto L78
    L82:
        TextView r104 = this.messageTextView;
        if (r52 == false) goto L85;
        r104.setText(LocaleController.getString(R.string.VoipGroupStartAsInfoGroup));
    L86:
        TextView r55 = this.messageTextView;
        if (LocaleController.isRTL == false) goto L89;
        int r105 = 5;
    L90:
        r55.setGravity(r105 | 48);
        r63.addView(this.messageTextView, LayoutHelper.createFrame(-2, -2.0f, 51, 23.0f, 0.0f, 23.0f, 5.0f));
        goto L91
    L89:
        r105 = 3;
        goto L90
    L85:
        r104.setText(LocaleController.getString(R.string.VoipGroupStartAsInfo));
    L67:
        r98 = r98 + 1;
        goto L59
    L68:
        r52 = false;
        goto L69
    L57:
        r97.setTextColor(Theme.getColor(Theme.key_dialogTextGray3));
        goto L58
    L45:
        r10.setText(LocaleController.getString(R.string.StartVoipChatTitle));
        goto L46
    L47:
        if (r24 != 2) goto L49;
        this.textView.setText(LocaleController.getString(R.string.VoipGroupDisplayAs));
    L53:
        r63.addView(this.textView, LayoutHelper.createFrame(-2, -2.0f, 51, 23.0f, 8.0f, 23.0f, 0.0f));
        goto L54
    L49:
        boolean r99 = ChatObject.isChannelOrGiga(r83);
        TextView r106 = this.textView;
        if (r99 == false) goto L52;
        r106.setText(LocaleController.getString(R.string.VoipChannelJoinAs));
        goto L53
    L52:
        r106.setText(LocaleController.getString(R.string.VoipGroupJoinAs));
        goto L53
    L39:
        r95.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        goto L40
    L33:
        r92.setSelectorDrawableColor(0);
        this.listView.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        goto L34
    L29:
        r12 = 1;
        goto L30
    L25:
        AnonymousClass2 r64 = new AnonymousClass2(this, r20);
        this.containerView = r64;
        r64.setWillNotDraw(false);
        ViewGroup r84 = this.containerView;
        int r910 = this.backgroundPaddingLeft;
        r84.setPadding(r910, 0, r910, 0);
        r63 = r64;
        goto L26
    L5:
        if (VoIPService.getSharedInstance() == null) goto L12;
        long r65 = VoIPService.getSharedInstance().getSelfId();
        int r85 = this.chats.size();
        int r911 = 0;
    L7:
        if (r911 >= r85) goto L20;
        TLRPC.Peer r107 = this.chats.get(r911);
        if (MessageObject.getPeerId(r107) == r65) goto L10;
        r911 = r911 + 1;
        goto L7
    L10:
        this.currentPeer = r107;
        this.selectedPeer = r107;
    L20:
        Drawable r66 = this.shadowDrawable;
        r8 = Theme.getColor(Theme.key_voipgroup_inviteMembersBackground);
        r66.setColorFilter(new PorterDuffColorFilter(r8, PorterDuff.Mode.MULTIPLY));
        goto L22
    L12:
        if (r25 == null) goto L19;
        long r67 = MessageObject.getPeerId(r25);
        int r86 = this.chats.size();
        int r912 = 0;
    L14:
        if (r912 >= r86) goto L20;
        TLRPC.Peer r108 = this.chats.get(r912);
        if (MessageObject.getPeerId(r108) == r67) goto L17;
        r912 = r912 + 1;
        goto L14
    L17:
        this.currentPeer = r108;
        this.selectedPeer = r108;
        goto L20
    L19:
        this.selectedPeer = this.chats.get(0);
        goto L20
    }

    public static /* synthetic */ int access$000(JoinCallAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$100(JoinCallAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$200(JoinCallAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(JoinCallAlert r0) {
        return r0.currentAccount;
    }

    public static void checkFewUsers(Context r7, long r8, AccountInstance r10, MessagesStorage.BooleanCallback r11) {
        if (lastCachedAccount == r10.getCurrentAccount()) goto L5;
    L16:
        AlertDialog r1 = new AlertDialog(r7, 3);
        TL_phone.getGroupCallJoinAs r72 = new TL_phone.getGroupCallJoinAs();
        r72.peer = r10.getMessagesController().getInputPeer(r8);
        r1.setOnCancelListener(new JoinCallAlert$$ExternalSyntheticLambda3(r10, r10.getConnectionsManager().sendRequest(r72, new JoinCallAlert$$ExternalSyntheticLambda2(r1, r8, r10, r11))));
        r1.showDelayed(500);     // Catch: Exception -> L19
        return;
    L22:
        return;
    L5:
        if (lastCacheDid != r8) goto L16;
        if (cachedChats == null) goto L16;
        if ((SystemClock.elapsedRealtime() - lastCacheTime) >= 240000) goto L16;
        boolean r82 = true;
        if (cachedChats.size() == 1) goto L14;
        r82 = false;
    L14:
        r11.run(r82);
    }

    private /* synthetic */ void lambda$new$6(TLRPC.Chat r7, View r8, int r9) {
        if (this.animationInProgress == false) goto L5;
        return;
    L5:
        if (this.chats.get(r9) == this.selectedPeer) goto L34;
        this.selectedPeer = this.chats.get(r9);
        boolean r92 = r8 instanceof GroupCreateUserCell;
        if (r92 == false) goto L11;
        ((GroupCreateUserCell) r8).setChecked(true, true);
    L13:
        int r1 = this.listView.getChildCount();
        int r3 = 0;
    L14:
        if (r3 >= r1) goto L24;
        View r4 = this.listView.getChildAt(r3);
        if (r4 == r8) goto L22;
        if (r92 == false) goto L20;
        ((GroupCreateUserCell) r4).setChecked(false, true);
        goto L22
    L20:
        if ((r8 instanceof ShareDialogCell) == false) goto L22;
        ((ShareDialogCell) r4).setChecked(false, true);
    L22:
        r3 = r3 + 1;
        goto L14
    L24:
        if (this.currentType == 0) goto L33;
        updateDoneButton(true, r7);
        return;
    L33:
        return;
    L11:
        if ((r8 instanceof ShareDialogCell) == false) goto L13;
        ((ShareDialogCell) r8).setChecked(true, true);
        r8.invalidate();
        goto L13
    }

    private /* synthetic */ void lambda$new$7(JoinCallAlertDelegate r4, View r5) {
        TLRPC.InputPeer r52 = MessagesController.getInstance(this.currentAccount).getInputPeer(MessageObject.getPeerId(this.selectedPeer));
        if (this.currentType == 2) goto L5;
        this.selectAfterDismiss = r52;
    L12:
        dismiss();
        return;
    L5:
        if (this.selectedPeer == this.currentPeer) goto L12;
        boolean r1 = true;
        if (this.chats.size() > 1) goto L10;
        r1 = false;
    L10:
        r4.didSelectChat(r52, r1, false, false);
        goto L12
    }

    private /* synthetic */ void lambda$new$8(View r3) {
        this.selectAfterDismiss = MessagesController.getInstance(this.currentAccount).getInputPeer(MessageObject.getPeerId(this.selectedPeer));
        this.schedule = true;
        dismiss();
    }

    public static void open(Context r12, long r13, AccountInstance r15, BaseFragment r16, int r17, TLRPC.Peer r18, JoinCallAlertDelegate r19) {
        if (r12 == null) goto L27;
        if (r19 != null) goto L7;
        return;
    L7:
        if (lastCachedAccount == r15.getCurrentAccount()) goto L9;
    L21:
        AlertDialog r3 = new AlertDialog(r12, 3);
        TL_phone.getGroupCallJoinAs r10 = new TL_phone.getGroupCallJoinAs();
        r10.peer = r15.getMessagesController().getInputPeer(r13);
        r3.setOnCancelListener(new JoinCallAlert$$ExternalSyntheticLambda1(r15, r15.getConnectionsManager().sendRequest(r10, new JoinCallAlert$$ExternalSyntheticLambda0(r3, r15, r19, r13, r12, r16, r17, r18))));
        r3.showDelayed(500);     // Catch: Exception -> L24
        return;
    L29:
        return;
    L9:
        if (lastCacheDid != r13) goto L21;
        if (cachedChats == null) goto L21;
        if ((SystemClock.elapsedRealtime() - lastCacheTime) >= 300000) goto L21;
        if (cachedChats.size() != 1) goto L19;
        if (r17 == 0) goto L19;
        r19.didSelectChat(r15.getMessagesController().getInputPeer(MessageObject.getPeerId(cachedChats.get(0))), false, false, false);
        return;
    L19:
        showAlert(r12, r13, cachedChats, r16, r17, r18, r19);
        return;
    }

    public static void processDeletedChat(int r3, long r4) {
        if (lastCachedAccount != r3) goto L21;
        ArrayList<TLRPC.Peer> r32 = cachedChats;
        if (r32 != null) goto L7;
        return;
    L7:
        if (r4 > 0) goto L24;
        int r33 = r32.size();
        int r0 = 0;
    L10:
        if (r0 >= r33) goto L16;
        if (MessageObject.getPeerId(cachedChats.get(r0)) == r4) goto L13;
        r0 = r0 + 1;
        goto L10
    L13:
        cachedChats.remove(r0);
    L16:
        if (cachedChats.isEmpty() == false) goto L23;
        cachedChats = null;
        return;
    L23:
        return;
    L24:
        return;
    }

    public static void resetCache() {
        cachedChats = null;
    }

    private static void showAlert(Context r8, long r9, ArrayList<TLRPC.Peer> r11, BaseFragment r12, int r13, TLRPC.Peer r14, JoinCallAlertDelegate r15) {
        if (r13 != 0) goto L5;
        CreateGroupCallBottomSheet.show(r11, r12, r9, r15);
        return;
    L5:
        JoinCallAlert r0 = new JoinCallAlert(r8, r9, r11, r13, r14, r15);
        if (r12 != null) goto L8;
        r0.show();
        return;
    L8:
        if (r12.getParentActivity() == null) goto L13;
        r12.showDialog(r0);
        return;
    }

    private void updateDoneButton(boolean r5, TLRPC.Chat r6) {
        if (this.currentType != 0) goto L10;
        boolean r62 = ChatObject.isChannelOrGiga(r6);
        BottomSheetCell r4 = this.doneButton;
        if (r62 == false) goto L8;
        r4.setText(LocaleController.formatString("VoipChannelStartVoiceChat", R.string.VoipChannelStartVoiceChat, new Object[0]), r5);
        return;
    L8:
        r4.setText(LocaleController.formatString("VoipGroupStartVoiceChat", R.string.VoipGroupStartVoiceChat, new Object[0]), r5);
        return;
    L10:
        long r0 = MessageObject.getPeerId(this.selectedPeer);
        boolean r63 = DialogObject.isUserDialog(r0);
        int r2 = this.currentAccount;
        if (r63 == false) goto L14;
        TLRPC.User r64 = MessagesController.getInstance(r2).getUser(Long.valueOf(r0));
        this.doneButton.setText(LocaleController.formatString("VoipGroupContinueAs", R.string.VoipGroupContinueAs, new Object[]{UserObject.getFirstName(r64)}), r5);
        return;
    L14:
        TLRPC.Chat r65 = MessagesController.getInstance(r2).getChat(Long.valueOf(-r0));
        BottomSheetCell r42 = this.doneButton;
        int r02 = R.string.VoipGroupContinueAs;
        if (r65 == null) goto L17;
        String r66 = r65.title;
    L18:
        r42.setText(LocaleController.formatString("VoipGroupContinueAs", r02, new Object[]{r66}), r5);
        return;
    L17:
        r66 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L18
    }

    private void updateLayout() {
        if (this.currentType == 0) goto L20;
        int r0 = this.listView.getChildCount();
        RecyclerListView r1 = this.listView;
        if (r0 > 0) goto L9;
        int r02 = r1.getPaddingTop();
        this.scrollOffsetY = r02;
        r1.setTopGlowOffset(r02);
        this.containerView.invalidate();
        return;
    L9:
        int r03 = 0;
        View r12 = r1.getChildAt(0);
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r12);
        int r13 = r12.getTop() - AndroidUtilities.dp(9.0f);
        if (r13 <= 0) goto L16;
        if (r2 == null) goto L16;
        if (r2.getAdapterPosition() != 0) goto L16;
        r03 = r13;
    L16:
        if (this.scrollOffsetY == r03) goto L19;
        this.textView.setTranslationY(AndroidUtilities.dp(19.0f) + r13);
        this.messageTextView.setTranslationY(r13 + AndroidUtilities.dp(56.0f));
        RecyclerListView r14 = this.listView;
        this.scrollOffsetY = r03;
        r14.setTopGlowOffset(r03);
        this.containerView.invalidate();
        return;
    L19:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        TLRPC.InputPeer r0 = this.selectAfterDismiss;
        if (r0 == null) goto L10;
        JoinCallAlertDelegate r1 = this.delegate;
        boolean r4 = true;
        if (this.chats.size() > 1) goto L8;
        r4 = false;
    L8:
        r1.didSelectChat(r0, r4, this.schedule, false);
        return;
    }
}
