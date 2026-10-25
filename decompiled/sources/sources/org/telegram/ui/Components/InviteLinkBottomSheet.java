package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AvatarSpan;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.LinkActionView;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.LinkEditActivity;
import org.telegram.ui.ManageLinksActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes7.dex */
public class InviteLinkBottomSheet extends BottomSheet {
    Adapter adapter;
    private boolean canEdit;
    private long chatId;
    int creatorHeaderRow;
    int creatorRow;
    int divider2Row;
    int divider3Row;
    int dividerRow;
    int emptyHintRow;
    int emptyView;
    int emptyView2;
    int emptyView3;
    int expiredEndRow;
    int expiredHeaderRow;
    int expiredStartRow;
    ArrayList<TLRPC.TL_chatInviteImporter> expiredUsers;
    BaseFragment fragment;
    boolean hasMore;
    private boolean ignoreLayout;
    TLRPC.ChatFull info;
    TLRPC.TL_chatInviteExported invite;
    InviteDelegate inviteDelegate;
    private boolean isChannel;
    public boolean isNeedReopen;
    int joinedEndRow;
    int joinedHeaderRow;
    int joinedStartRow;
    ArrayList<TLRPC.TL_chatInviteImporter> joinedUsers;
    int linkActionRow;
    int linkInfoRow;
    private RecyclerListView listView;
    int loadingRow;
    private boolean permanent;
    int requestedEndRow;
    int requestedHeaderRow;
    int requestedStartRow;
    ArrayList<TLRPC.TL_chatInviteImporter> requestedUsers;
    int revenueHeaderRow;
    int revenueRow;
    int rowCount;
    private int scrollOffsetY;
    private View shadow;
    private AnimatorSet shadowAnimation;
    private final long timeDif;
    private TextView titleTextView;
    private boolean titleVisible;
    HashMap<Long, TLRPC.User> users;
    boolean usersLoading;

    /* renamed from: org.telegram.ui.Components.InviteLinkBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        private boolean fullHeight;
        private RectF rect;
        private Boolean statusBarOpen;
        final /* synthetic */ InviteLinkBottomSheet this$0;

        public AnonymousClass1(InviteLinkBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new RectF();
        }

        private void updateLightStatusBar(boolean r7) {
            Boolean r0 = this.statusBarOpen;
            if (r0 != null) goto L5;
        L7:
            boolean r2 = false;
            if (AndroidUtilities.computePerceivedBrightness(InviteLinkBottomSheet.access$1600(this.this$0, Theme.key_dialogBackground)) <= 0.721f) goto L10;
            boolean r02 = true;
        L12:
            if (AndroidUtilities.computePerceivedBrightness(Theme.blendOver(InviteLinkBottomSheet.access$1700(this.this$0, Theme.key_actionBarDefault), 855638016)) <= 0.721f) goto L14;
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
            int r0 = (InviteLinkBottomSheet.m11074$$Nest$fgetscrollOffsetY(this.this$0) - InviteLinkBottomSheet.access$200(this.this$0)) - AndroidUtilities.dp(8.0f);
            int r1 = (getMeasuredHeight() + AndroidUtilities.dp(36.0f)) + InviteLinkBottomSheet.access$300(this.this$0);
            int r2 = AndroidUtilities.statusBarHeight;
            int r02 = r0 + r2;
            int r12 = r1 - r2;
            boolean r3 = false;
            if (this.fullHeight == false) goto L12;
            int r22 = InviteLinkBottomSheet.access$400(this.this$0) + r02;
            int r5 = AndroidUtilities.statusBarHeight;
            if (r22 >= (r5 * 2)) goto L7;
            int r23 = Math.min(r5, ((r5 * 2) - r02) - InviteLinkBottomSheet.access$500(this.this$0));
            r02 = r02 - r23;
            r12 = r12 + r23;
            float r24 = 1.0f - Math.min(1.0f, (r23 * 2) / AndroidUtilities.statusBarHeight);
        L8:
            int r52 = InviteLinkBottomSheet.access$600(this.this$0) + r02;
            int r6 = AndroidUtilities.statusBarHeight;
            if (r52 >= r6) goto L11;
            int r53 = Math.min(r6, (r6 - r02) - InviteLinkBottomSheet.access$700(this.this$0));
        L13:
            InviteLinkBottomSheet.access$800(this.this$0).setBounds(0, r02, getMeasuredWidth(), (r12 + AndroidUtilities.dp(10.0f)) + AndroidUtilities.navigationBarHeight);
            InviteLinkBottomSheet.access$900(this.this$0).draw(r13);
            if (r24 == 1.0f) goto L16;
            Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_dialogBackground));
            this.rect.set(InviteLinkBottomSheet.access$1000(this.this$0), InviteLinkBottomSheet.access$1100(this.this$0) + r02, getMeasuredWidth() - InviteLinkBottomSheet.access$1200(this.this$0), (InviteLinkBottomSheet.access$1300(this.this$0) + r02) + AndroidUtilities.dp(24.0f));
            r13.drawRoundRect(this.rect, AndroidUtilities.dp(12.0f) * r24, AndroidUtilities.dp(12.0f) * r24, Theme.dialogs_onlineCirclePaint);
        L16:
            if (r53 <= 0) goto L19;
            Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_dialogBackground));
            r13.drawRect(InviteLinkBottomSheet.access$1400(this.this$0), AndroidUtilities.statusBarHeight - r53, getMeasuredWidth() - InviteLinkBottomSheet.access$1500(this.this$0), AndroidUtilities.statusBarHeight, Theme.dialogs_onlineCirclePaint);
        L19:
            if (r53 <= (AndroidUtilities.statusBarHeight / 2)) goto L21;
            r3 = true;
        L21:
            updateLightStatusBar(r3);
            return;
        L11:
            r53 = 0;
            goto L13
        L7:
            r24 = 1.0f;
            goto L8
        L12:
            r53 = 0;
            r24 = 1.0f;
            goto L13
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L11;
            if (InviteLinkBottomSheet.m11074$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L11;
            if (r3.getY() >= InviteLinkBottomSheet.m11074$$Nest$fgetscrollOffsetY(this.this$0)) goto L11;
            this.this$0.dismiss();
            return true;
        L11:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            InviteLinkBottomSheet.m11080$$Nest$mupdateLayout(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            int r72 = View.MeasureSpec.getSize(r7);
            InviteLinkBottomSheet.m11078$$Nest$fputignoreLayout(this.this$0, true);
            setPadding(InviteLinkBottomSheet.access$000(this.this$0), AndroidUtilities.statusBarHeight, InviteLinkBottomSheet.access$100(this.this$0), 0);
            InviteLinkBottomSheet.m11078$$Nest$fputignoreLayout(this.this$0, false);
            super.onMeasure(r6, View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30));
            this.fullHeight = true;
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
            if (InviteLinkBottomSheet.m11071$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.InviteLinkBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        int lastH;
        final /* synthetic */ InviteLinkBottomSheet this$0;

        public AnonymousClass2(InviteLinkBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r8, int r9) {
            if (this.lastH == View.MeasureSpec.getSize(r9)) goto L8;
            this.lastH = View.MeasureSpec.getSize(r9);
            InviteLinkBottomSheet.m11078$$Nest$fputignoreLayout(this.this$0, true);
            InviteLinkBottomSheet.m11073$$Nest$fgetlistView(this.this$0).setPadding(0, 0, 0, 0);
            InviteLinkBottomSheet.m11078$$Nest$fputignoreLayout(this.this$0, false);
            measure(r8, View.MeasureSpec.makeMeasureSpec(r9, Integer.MIN_VALUE));
            int r3 = getMeasuredHeight();
            int r4 = this.lastH;
            int r5 = (int) ((r4 / 5.0f) * 2.0f);
            if (r5 >= ((r4 - r3) + AndroidUtilities.dp(60.0f))) goto L7;
            r5 = this.lastH - r3;
        L7:
            InviteLinkBottomSheet.m11078$$Nest$fputignoreLayout(this.this$0, true);
            InviteLinkBottomSheet.m11073$$Nest$fgetlistView(this.this$0).setPadding(0, r5, 0, 0);
            InviteLinkBottomSheet.m11078$$Nest$fputignoreLayout(this.this$0, false);
            measure(r8, View.MeasureSpec.makeMeasureSpec(r9, Integer.MIN_VALUE));
        L8:
            super.onMeasure(r8, r9);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (InviteLinkBottomSheet.m11071$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.InviteLinkBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ InviteLinkBottomSheet this$0;
        final /* synthetic */ LinearLayoutManager val$layoutManager;

        public AnonymousClass3(InviteLinkBottomSheet r1, LinearLayoutManager r2) {
            this.this$0 = r1;
            this.val$layoutManager = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            InviteLinkBottomSheet.m11080$$Nest$mupdateLayout(this.this$0);
            InviteLinkBottomSheet r12 = this.this$0;
            if (r12.hasMore == true) goto L5;
            return;
        L5:
            if (r12.usersLoading == true) goto L11;
            int r13 = this.val$layoutManager.findLastVisibleItemPosition();
            InviteLinkBottomSheet r0 = this.this$0;
            if ((r0.rowCount - r13) >= 10) goto L12;
            r0.loadUsers();
            return;
        L12:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.InviteLinkBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ InviteLinkBottomSheet this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass4(InviteLinkBottomSheet r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (InviteLinkBottomSheet.m11076$$Nest$fgetshadowAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (InviteLinkBottomSheet.m11076$$Nest$fgetshadowAnimation(this.this$0).equals(r2) == false) goto L9;
            InviteLinkBottomSheet.m11079$$Nest$fputshadowAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (InviteLinkBottomSheet.m11076$$Nest$fgetshadowAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (InviteLinkBottomSheet.m11076$$Nest$fgetshadowAnimation(this.this$0).equals(r2) == true) goto L7;
            return;
        L7:
            if (this.val$show == true) goto L9;
            InviteLinkBottomSheet.m11075$$Nest$fgetshadow(this.this$0).setVisibility(4);
        L9:
            InviteLinkBottomSheet.m11079$$Nest$fputshadowAnimation(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.InviteLinkBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends ClickableSpan {
        final /* synthetic */ TLRPC.TL_chatInviteImporter val$importer;
        final /* synthetic */ BottomSheet[] val$sheet;

        public AnonymousClass5(BottomSheet[] r1, TLRPC.TL_chatInviteImporter r2) {
            this.val$sheet = r1;
            this.val$importer = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r3) {
            this.val$sheet[0].dismiss();
            BaseFragment r32 = LaunchActivity.getSafeLastFragment();
            if (r32 == null) goto L6;
            r32.presentFragment(ProfileActivity.of(this.val$importer.user_id));
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setUnderlineText(false);
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ InviteLinkBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.InviteLinkBottomSheet$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends LinkActionView {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2, BaseFragment r3, BottomSheet r4, long r5, boolean r7, boolean r8) {
                this.this$1 = r1;
                super(r2, r3, r4, r5, r7, r8);
            }

            @Override // org.telegram.ui.Components.LinkActionView
            public void showBulletin(int r2, CharSequence r3) {
                InviteLinkBottomSheet r1 = this.this$1.this$0;
                Bulletin r12 = BulletinFactory.of(r1.container, InviteLinkBottomSheet.access$1900(r1)).createSimpleBulletin(r2, r3);
                r12.hideAfterBottomSheet = false;
                r12.show(true);
            }
        }

        /* renamed from: org.telegram.ui.Components.InviteLinkBottomSheet$Adapter$2, reason: invalid class name */
        public class AnonymousClass2 implements LinkActionView.Delegate {
            final /* synthetic */ Adapter this$1;

            /* renamed from: org.telegram.ui.Components.InviteLinkBottomSheet$Adapter$2$1, reason: invalid class name */
            public class AnonymousClass1 implements LinkEditActivity.Callback {
                final /* synthetic */ AnonymousClass2 this$2;

                public AnonymousClass1(AnonymousClass2 r1) {
                    this.this$2 = r1;
                }

                @Override // org.telegram.ui.LinkEditActivity.Callback
                public void onLinkCreated(TLObject r1) {
                }

                @Override // org.telegram.ui.LinkEditActivity.Callback
                public void onLinkEdited(TLRPC.TL_chatInviteExported r1, TLObject r2) {
                    InviteDelegate r0 = this.this$2.this$1.this$0.inviteDelegate;
                    if (r0 == null) goto L6;
                    r0.onLinkEdited(r1);
                    return;
                }

                @Override // org.telegram.ui.LinkEditActivity.Callback
                public void onLinkRemoved(TLRPC.TL_chatInviteExported r1) {
                }

                @Override // org.telegram.ui.LinkEditActivity.Callback
                public void revokeLink(TLRPC.TL_chatInviteExported r1) {
                }
            }

            public static /* synthetic */ void $r8$lambda$02NeAHtJ1UZdwQeM4PulAPzO19o(AnonymousClass2 r0, TLObject r1, TLRPC.TL_error r2) {
                r0.lambda$revokeLink$1(r1, r2);
            }

            /* renamed from: $r8$lambda$4cLiA6WotNUeTI0Qgw6BwnC-j_k, reason: not valid java name */
            public static /* synthetic */ void m11081$r8$lambda$4cLiA6WotNUeTI0Qgw6BwnCj_k(AnonymousClass2 r0, TLObject r1, TLRPC.TL_error r2) {
                r0.lambda$removeLink$3(r1, r2);
            }

            public static /* synthetic */ void $r8$lambda$AaSDtmNCXVWSBv92hKWTSYBz73Q(AnonymousClass2 r0, TLRPC.TL_error r1, TLObject r2) {
                r0.lambda$revokeLink$0(r1, r2);
            }

            public static /* synthetic */ void $r8$lambda$J1Ef3NOAjZtAaFC0Oh77mTzdb9g(AnonymousClass2 r0, TLRPC.TL_error r1) {
                r0.lambda$removeLink$2(r1);
            }

            public AnonymousClass2(Adapter r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$removeLink$2(TLRPC.TL_error r1) {
                if (r1 != null) goto L7;
                InviteLinkBottomSheet r0 = this.this$1.this$0;
                InviteDelegate r12 = r0.inviteDelegate;
                if (r12 == null) goto L8;
                r12.onLinkDeleted(r0.invite);
                return;
            L8:
                return;
            }

            private /* synthetic */ void lambda$removeLink$3(TLObject r1, TLRPC.TL_error r2) {
                AndroidUtilities.runOnUIThread(new InviteLinkBottomSheet$Adapter$2$$ExternalSyntheticLambda2(this, r2));
            }

            private /* synthetic */ void lambda$revokeLink$0(TLRPC.TL_error r3, TLObject r4) {
                if (r3 != null) goto L22;
                boolean r32 = r4 instanceof TLRPC.TL_messages_exportedChatInviteReplaced;
                Adapter r0 = this.this$1;
                if (r32 == false) goto L12;
                TLRPC.TL_messages_exportedChatInviteReplaced r42 = (TLRPC.TL_messages_exportedChatInviteReplaced) r4;
                InviteLinkBottomSheet r2 = r0.this$0;
                TLRPC.ChatFull r33 = r2.info;
                if (r33 == null) goto L8;
                r33.exported_invite = (TLRPC.TL_chatInviteExported) r42.new_invite;
            L8:
                InviteDelegate r43 = r2.inviteDelegate;
                if (r43 == null) goto L23;
                r43.permanentLinkReplaced(r2.invite, r33.exported_invite);
                return;
            L23:
                return;
            L12:
                InviteLinkBottomSheet r34 = r0.this$0;
                TLRPC.ChatFull r44 = r34.info;
                if (r44 == null) goto L18;
                int r02 = r44.invitesCount - 1;
                r44.invitesCount = r02;
                if (r02 >= 0) goto L17;
                r44.invitesCount = 0;
            L17:
                MessagesStorage.getInstance(InviteLinkBottomSheet.access$2400(r34)).saveChatLinksCount(InviteLinkBottomSheet.m11070$$Nest$fgetchatId(this.this$1.this$0), this.this$1.this$0.info.invitesCount);
            L18:
                InviteLinkBottomSheet r22 = this.this$1.this$0;
                InviteDelegate r35 = r22.inviteDelegate;
                if (r35 == null) goto L24;
                r35.linkRevoked(r22.invite);
                return;
            L24:
                return;
            }

            private /* synthetic */ void lambda$revokeLink$1(TLObject r2, TLRPC.TL_error r3) {
                AndroidUtilities.runOnUIThread(new InviteLinkBottomSheet$Adapter$2$$ExternalSyntheticLambda3(this, r3, r2));
            }

            @Override // org.telegram.ui.Components.LinkActionView.Delegate
            public void editLink() {
                InviteLinkBottomSheet r0 = this.this$1.this$0;
                BaseFragment r1 = r0.fragment;
                if ((r1 instanceof ManageLinksActivity) == false) goto L5;
                ((ManageLinksActivity) r1).editLink(r0.invite);
            L6:
                this.this$1.this$0.dismiss();
                return;
            L5:
                LinkEditActivity r12 = new LinkEditActivity(1, InviteLinkBottomSheet.m11070$$Nest$fgetchatId(r0));
                r12.setInviteToEdit(this.this$1.this$0.invite);
                r12.setCallback(new AnonymousClass1(this));
                this.this$1.this$0.fragment.presentFragment(r12);
                goto L6
            }

            @Override // org.telegram.ui.Components.LinkActionView.Delegate
            public void removeLink() {
                InviteLinkBottomSheet r0 = this.this$1.this$0;
                BaseFragment r1 = r0.fragment;
                if ((r1 instanceof ManageLinksActivity) == false) goto L5;
                ((ManageLinksActivity) r1).deleteLink(r0.invite);
            L6:
                this.this$1.this$0.dismiss();
                return;
            L5:
                TLRPC.TL_messages_deleteExportedChatInvite r02 = new TLRPC.TL_messages_deleteExportedChatInvite();
                InviteLinkBottomSheet r12 = this.this$1.this$0;
                r02.link = r12.invite.link;
                r02.peer = MessagesController.getInstance(InviteLinkBottomSheet.access$2200(r12)).getInputPeer(-InviteLinkBottomSheet.m11070$$Nest$fgetchatId(this.this$1.this$0));
                ConnectionsManager.getInstance(InviteLinkBottomSheet.access$2300(this.this$1.this$0)).sendRequest(r02, new InviteLinkBottomSheet$Adapter$2$$ExternalSyntheticLambda1(this));
                goto L6
            }

            @Override // org.telegram.ui.Components.LinkActionView.Delegate
            public void revokeLink() {
                InviteLinkBottomSheet r0 = this.this$1.this$0;
                BaseFragment r1 = r0.fragment;
                if ((r1 instanceof ManageLinksActivity) == false) goto L5;
                ((ManageLinksActivity) r1).revokeLink(r0.invite);
            L6:
                this.this$1.this$0.dismiss();
                return;
            L5:
                TLRPC.TL_messages_editExportedChatInvite r02 = new TLRPC.TL_messages_editExportedChatInvite();
                InviteLinkBottomSheet r12 = this.this$1.this$0;
                r02.link = r12.invite.link;
                r02.revoked = true;
                r02.peer = MessagesController.getInstance(InviteLinkBottomSheet.access$2000(r12)).getInputPeer(-InviteLinkBottomSheet.m11070$$Nest$fgetchatId(this.this$1.this$0));
                ConnectionsManager.getInstance(InviteLinkBottomSheet.access$2100(this.this$1.this$0)).sendRequest(r02, new InviteLinkBottomSheet$Adapter$2$$ExternalSyntheticLambda0(this));
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.Components.InviteLinkBottomSheet$Adapter$3, reason: invalid class name */
        public class AnonymousClass3 extends View {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass3(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(5.0f), TLObject.FLAG_30));
            }
        }

        public static /* synthetic */ void $r8$lambda$K1qjMsc4l2G4ay0Cez3HpHldky4(Adapter r0, TLRPC.User r1, String r2, boolean r3, boolean r4, boolean r5, View r6) {
            r0.lambda$onBindViewHolder$0(r1, r2, r3, r4, r5, r6);
        }

        private Adapter(InviteLinkBottomSheet r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(InviteLinkBottomSheet r1, InviteLinkBottomSheetIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(TLRPC.User r12, String r13, boolean r14, boolean r15, boolean r16, View r17) {
            TagEditCell.showInfoSheet(this.this$0.getContext(), InviteLinkBottomSheet.access$3000(this.this$0), -InviteLinkBottomSheet.m11070$$Nest$fgetchatId(this.this$0), r12, r13, r14, r15, r16, InviteLinkBottomSheet.access$3100(this.this$0));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.rowCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            InviteLinkBottomSheet r2 = this.this$0;
            if (r3 != r2.creatorHeaderRow) goto L5;
        L65:
            return 0;
        L5:
            if (r3 == r2.requestedHeaderRow) goto L65;
            if (r3 == r2.joinedHeaderRow) goto L65;
            if (r3 == r2.revenueHeaderRow) goto L65;
            if (r3 != r2.creatorRow) goto L14;
            return 1;
        L14:
            if (r3 < r2.requestedStartRow) goto L18;
            if (r3 >= r2.requestedEndRow) goto L18;
            return 1;
        L18:
            if (r3 < r2.joinedStartRow) goto L23;
            if (r3 >= r2.joinedEndRow) goto L23;
            return 1;
        L23:
            if (r3 != r2.dividerRow) goto L25;
            return 2;
        L25:
            if (r3 != r2.divider2Row) goto L28;
            return 2;
        L28:
            if (r3 != r2.linkActionRow) goto L32;
            return 3;
        L32:
            if (r3 != r2.linkInfoRow) goto L36;
            return 4;
        L36:
            if (r3 != r2.loadingRow) goto L40;
            return 5;
        L40:
            if (r3 != r2.emptyView) goto L42;
            return 6;
        L42:
            if (r3 != r2.emptyView2) goto L44;
            return 6;
        L44:
            if (r3 != r2.emptyView3) goto L47;
            return 6;
        L47:
            if (r3 != r2.divider3Row) goto L51;
            return 7;
        L51:
            if (r3 != r2.emptyHintRow) goto L55;
            return 8;
        L55:
            if (r3 != r2.revenueRow) goto L58;
            return 9;
        L58:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r6) {
            int r62 = r6.getAdapterPosition();
            InviteLinkBottomSheet r5 = this.this$0;
            if (r62 != r5.creatorRow) goto L9;
            if (r5.invite.admin_id != UserConfig.getInstance(InviteLinkBottomSheet.access$2900(r5)).clientUserId) goto L7;
            return false;
        L7:
            return true;
        L9:
            if (r62 < r5.joinedStartRow) goto L13;
            if (r62 >= r5.joinedEndRow) goto L13;
        L16:
            return true;
        L13:
            if (r62 >= r5.requestedStartRow) goto L15;
        L17:
            return false;
        L15:
            if (r62 >= r5.requestedEndRow) goto L17;
            goto L16
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r17, int r18) {
            int r2 = r17.getItemViewType();
            String r3 = null;
            if (r2 != 0) goto L5;
            GraySectionCell r0 = (GraySectionCell) r17.itemView;
            InviteLinkBottomSheet r22 = this.this$0;
            if (r18 != r22.creatorHeaderRow) goto L145;
            r0.setText(LocaleController.getString(R.string.LinkCreatedeBy));
            r0.setRightText(null);
            return;
        L145:
            if (r18 != r22.revenueHeaderRow) goto L149;
            r0.setText(LocaleController.getString(R.string.LinkRevenue));
            r0.setRightText(null);
            return;
        L149:
            if (r18 != r22.joinedHeaderRow) goto L171;
            TLRPC.TL_chatInviteExported r23 = r22.invite;
            int r5 = r23.usage;
            if (r5 <= 0) goto L154;
            r0.setText(LocaleController.formatPluralString("PeopleJoined", r5, new Object[0]));
        L158:
            TLRPC.TL_chatInviteExported r1 = this.this$0.invite;
            if (r1.expired == false) goto L161;
        L168:
            r0.setRightText(null);
            return;
        L161:
            if (r1.revoked == true) goto L168;
            int r24 = r1.usage_limit;
            if (r24 <= 0) goto L168;
            int r12 = r1.usage;
            if (r12 <= 0) goto L168;
            r0.setRightText(LocaleController.formatPluralString("PeopleJoinedRemaining", r24 - r12, new Object[0]));
            return;
        L154:
            if (r23.subscription_pricing == null) goto L156;
            int r25 = R.string.NoOneSubscribed;
        L157:
            r0.setText(LocaleController.getString(r25));
            goto L158
        L156:
            r25 = R.string.NoOneJoined;
            goto L157
        L171:
            if (r18 != r22.expiredHeaderRow) goto L175;
            r0.setText(LocaleController.formatPluralString("PeopleSubscriptionExpired", r22.invite.subscription_expired, new Object[0]));
            r0.setRightText(null);
            return;
        L175:
            if (r18 != r22.requestedHeaderRow) goto L183;
            r0.setText(LocaleController.formatPluralString("JoinRequests", r22.invite.requested, new Object[0]));
            r0.setRightText(null);
            return;
        L183:
            return;
        L5:
            if (r2 != 1) goto L7;
            RevenueUserCell r8 = (RevenueUserCell) r17.itemView;
            InviteLinkBottomSheet r02 = this.this$0;
            if (r18 != r02.creatorRow) goto L55;
            long r9 = r02.invite.admin_id;
            TLRPC.TL_chatInviteImporter r14 = null;
        L64:
            TLRPC.User r03 = this.this$0.users.get(Long.valueOf(r9));
            TLRPC.ChatFull r26 = this.this$0.info;
            if (r26 != null) goto L67;
        L75:
            TLRPC.ChatParticipant r27 = null;
        L76:
            InviteLinkBottomSheet r52 = this.this$0;
            if (r18 != r52.creatorRow) goto L83;
            r03 = r52.users.get(Long.valueOf(r9));
            if (r03 != null) goto L81;
            r03 = MessagesController.getInstance(InviteLinkBottomSheet.access$2500(this.this$0)).getUser(Long.valueOf(this.this$0.invite.admin_id));
        L81:
            if (r03 == null) goto L83;
            TLRPC.User r92 = r03;
            String r15 = LocaleController.formatDateAudio(this.this$0.invite.date, false);
        L85:
            if (r18 != this.this$0.creatorRow) goto L126;
            if (r27 == null) goto L126;
            if ((r27 instanceof TLRPC.TL_chatChannelParticipant) == false) goto L108;
            TLRPC.ChannelParticipant r04 = ((TLRPC.TL_chatChannelParticipant) r27).channelParticipant;
            String r28 = r04.rank;
            if ((r04 instanceof TLRPC.TL_channelParticipantCreator) == false) goto L97;
            if (TextUtils.isEmpty(r28) == false) goto L94;
            r28 = LocaleController.getString("ChannelCreator", R.string.ChannelCreator);
        L94:
            r3 = r28;
            boolean r05 = false;
            boolean r29 = true;
        L95:
            boolean r53 = r29;
        L107:
            boolean r10 = r29;
        L128:
            if (UserObject.isUserSelf(r92) == true) goto L130;
        L132:
            boolean r122 = false;
        L133:
            TLRPC.User r210 = r92;
            String r93 = r3;
            boolean r11 = r53;
            r8.setAdminRole(r93, r10, r11, r122, new InviteLinkBottomSheet$Adapter$$ExternalSyntheticLambda0(this, r210, r3, r10, r53, r05));
            r8.setData(r210, null, r15, 0, false);
            InviteLinkBottomSheet r06 = this.this$0;
            if (r18 == r06.creatorRow) goto L180;
            TL_stars.TL_starsSubscriptionPricing r07 = r06.invite.subscription_pricing;
            if (r07 == null) goto L181;
            if (r14 == null) goto L182;
            r8.setRevenue(r07, r14.date);
            return;
        L182:
            return;
        L181:
            return;
        L180:
            return;
        L130:
            if (ChatObject.canManageMyTag(MessagesController.getInstance(InviteLinkBottomSheet.access$2800(this.this$0)).getChat(Long.valueOf(InviteLinkBottomSheet.m11070$$Nest$fgetchatId(this.this$0)))) == false) goto L132;
            r122 = true;
            goto L133
        L97:
            if ((r04 instanceof TLRPC.TL_channelParticipantAdmin) == true) goto L99;
            r3 = r28;
            r05 = false;
            r29 = false;
            goto L95
        L99:
            if (TextUtils.isEmpty(r28) == false) goto L102;
            r28 = LocaleController.getString("ChannelAdmin", R.string.ChannelAdmin);
        L102:
            if (r04.promoted_by != UserConfig.getInstance(InviteLinkBottomSheet.access$2600(this.this$0)).getClientUserId()) goto L104;
            r05 = true;
        L105:
            r3 = r28;
            r53 = false;
            r29 = true;
            goto L107
        L104:
            r05 = false;
            goto L105
        L108:
            String r08 = r27.rank;
            if ((r27 instanceof TLRPC.TL_chatParticipantCreator) == false) goto L116;
            if (TextUtils.isEmpty(r08) == false) goto L113;
            r08 = LocaleController.getString("ChannelCreator", R.string.ChannelCreator);
        L113:
            r3 = r08;
            r05 = false;
            r53 = true;
        L114:
            r10 = r53;
            goto L128
        L116:
            if ((r27 instanceof TLRPC.TL_chatParticipantAdmin) == true) goto L118;
            r3 = r08;
            goto L126
        L118:
            if (TextUtils.isEmpty(r08) == false) goto L120;
            r08 = LocaleController.getString("ChannelAdmin", R.string.ChannelAdmin);
        L120:
            r3 = r08;
            if (r27.inviter_id != UserConfig.getInstance(InviteLinkBottomSheet.access$2700(this.this$0)).getClientUserId()) goto L123;
            r05 = true;
        L124:
            r53 = false;
            r10 = true;
            goto L128
        L123:
            r05 = false;
        L126:
            r05 = false;
            r53 = false;
        L83:
            r92 = r03;
            r15 = null;
            goto L85
        L67:
            if (r26.participants == null) goto L75;
            int r211 = 0;
        L70:
            if (r211 >= this.this$0.info.participants.participants.size()) goto L75;
            if (this.this$0.info.participants.participants.get(r211).user_id == r9) goto L73;
            r211 = r211 + 1;
            goto L70
        L73:
            r27 = this.this$0.info.participants.participants.get(r211);
            goto L76
        L55:
            int r212 = r02.joinedStartRow;
            ArrayList<TLRPC.TL_chatInviteImporter> r94 = r02.joinedUsers;
            int r102 = r02.expiredStartRow;
            if (r102 == (-1)) goto L59;
            if (r18 < r102) goto L59;
            r94 = r02.expiredUsers;
            r212 = r102;
        L59:
            int r103 = r02.requestedStartRow;
            if (r103 == (-1)) goto L63;
            if (r18 < r103) goto L63;
            r94 = r02.requestedUsers;
            r212 = r103;
        L63:
            TLRPC.TL_chatInviteImporter r09 = r94.get(r18 - r212);
            r9 = r09.user_id;
            r14 = r09;
            goto L64
        L7:
            if (r2 != 3) goto L9;
            LinkActionView r010 = (LinkActionView) r17.itemView;
            r010.setUsers(0, null);
            r010.setLink(this.this$0.invite.link);
            r010.setRevoke(this.this$0.invite.revoked);
            r010.setPermanent(this.this$0.invite.permanent);
            r010.setCanEdit(InviteLinkBottomSheet.m11069$$Nest$fgetcanEdit(this.this$0));
            r010.hideRevokeOption(!InviteLinkBottomSheet.m11069$$Nest$fgetcanEdit(this.this$0));
            return;
        L9:
            if (r2 != 4) goto L11;
            TimerPrivacyCell r011 = (TimerPrivacyCell) r17.itemView;
            r011.cancelTimer();
            r011.timer = false;
            r011.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
            r011.setFixedSize(0);
            TLRPC.TL_chatInviteExported r213 = this.this$0.invite;
            if (r213.revoked == false) goto L28;
            r011.setText(LocaleController.getString(R.string.LinkIsNoActive));
            return;
        L28:
            if (r213.expired == false) goto L38;
            int r13 = r213.usage_limit;
            if (r13 > 0) goto L32;
        L35:
            r011.setText(LocaleController.getString(R.string.LinkIsExpired));
            r011.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
            return;
        L32:
            if (r13 != r213.usage) goto L35;
            r011.setText(LocaleController.getString(R.string.LinkIsExpiredLimitReached));
            return;
        L38:
            if (r213.expire_date <= 0) goto L48;
            long r214 = System.currentTimeMillis() + (InviteLinkBottomSheet.m11077$$Nest$fgettimeDif(this.this$0) * 1000);
            int r16 = this.this$0.invite.expire_date;
            long r7 = (r16 * 1000) - r214;
            if (r7 >= 0) goto L43;
            r7 = 0;
        L43:
            if (r7 <= DurationKt.MILLIS_IN_DAY) goto L46;
            r011.setText(LocaleController.formatString("LinkExpiresIn", R.string.LinkExpiresIn, new Object[]{LocaleController.formatDateAudio(r16, false)}));
            return;
        L46:
            long r72 = r7 / 1000;
            int r32 = (int) (r72 % 60);
            long r73 = r72 / 60;
            StringBuilder r215 = new StringBuilder();
            Locale r54 = Locale.ENGLISH;
            r215.append(String.format(r54, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf((int) (r73 / 60))}));
            r215.append(String.format(r54, ":%02d", new Object[]{Integer.valueOf((int) (r73 % 60))}));
            r215.append(String.format(r54, ":%02d", new Object[]{Integer.valueOf(r32)}));
            String r19 = r215.toString();
            r011.timer = true;
            r011.runTimer();
            r011.setText(LocaleController.formatString("LinkExpiresInTime", R.string.LinkExpiresInTime, new Object[]{r19}));
            return;
        L48:
            r011.setFixedSize(-1);
            r011.setText(null);
            return;
        L11:
            if (r2 != 8) goto L13;
            EmptyHintRow r012 = (EmptyHintRow) r17.itemView;
            int r110 = this.this$0.invite.usage_limit;
            if (r110 <= 0) goto L21;
            r012.textView.setText(LocaleController.formatPluralString("PeopleCanJoinViaLinkCount", r110, new Object[0]));
            r012.textView.setVisibility(0);
            return;
        L21:
            r012.textView.setVisibility(8);
            return;
        L13:
            if (r2 != 9) goto L184;
            RevenueCell r013 = (RevenueCell) r17.itemView;
            TLRPC.TL_chatInviteExported r111 = this.this$0.invite;
            r013.set(r111.subscription_pricing, r111.usage);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r11, int r12) {
            Context r2 = r11.getContext();
            switch(r12) {
                case 1: goto L13;
                case 2: goto L12;
                case 3: goto L11;
                case 4: goto L10;
                case 5: goto L9;
                case 6: goto L8;
                case 7: goto L7;
                case 8: goto L6;
                case 9: goto L5;
                default: goto L4;
            };
        L4:
            View r122 = new GraySectionCell(r2, InviteLinkBottomSheet.access$1800(this.this$0));
        L14:
            r122.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r122);
        L5:
            r122 = new RevenueCell(this.this$0, r2);
            goto L14
        L6:
            r122 = new EmptyHintRow(this.this$0, r2);
            goto L14
        L7:
            r122 = new ShadowSectionCell(r2, 12);
            goto L14
        L8:
            r122 = new AnonymousClass3(this, r2);
            goto L14
        L9:
            FlickerLoadingView r123 = new FlickerLoadingView(r2);
            r123.setIsSingleCell(true);
            r123.setViewType(10);
            r123.showDate(false);
            r123.setPaddingLeft(AndroidUtilities.dp(10.0f));
            r122 = r123;
            goto L14
        L10:
            r122 = new TimerPrivacyCell(this.this$0, r2);
            goto L14
        L11:
            InviteLinkBottomSheet r4 = this.this$0;
            AnonymousClass1 r0 = new AnonymousClass1(this, r2, r4.fragment, r4, InviteLinkBottomSheet.m11070$$Nest$fgetchatId(r4), false, InviteLinkBottomSheet.m11072$$Nest$fgetisChannel(this.this$0));
            r0.setDelegate(new AnonymousClass2(this));
            r0.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            r122 = r0;
            goto L14
        L12:
            r122 = new ShadowSectionCell(r2, 12, Theme.getColor(Theme.key_windowBackgroundGray));
            goto L14
        L13:
            r122 = new RevenueUserCell(this.this$0, r2);
            goto L14
        }
    }

    public class EmptyHintRow extends FrameLayout {
        TextView textView;
        final /* synthetic */ InviteLinkBottomSheet this$0;

        public EmptyHintRow(InviteLinkBottomSheet r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            TextView r82 = new TextView(r9);
            this.textView = r82;
            r82.setTextSize(1, 14.0f);
            this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
            this.textView.setGravity(1);
            addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 16, 60.0f, 0.0f, 60.0f, 0.0f));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(84.0f), TLObject.FLAG_30));
        }
    }

    public interface InviteDelegate {
        void linkRevoked(TLRPC.TL_chatInviteExported r1);

        void onLinkDeleted(TLRPC.TL_chatInviteExported r1);

        void onLinkEdited(TLRPC.TL_chatInviteExported r1);

        void permanentLinkReplaced(TLRPC.TL_chatInviteExported r1, TLRPC.TL_chatInviteExported r2);
    }

    public class RevenueCell extends FrameLayout {
        public final ImageView imageView;
        public final TextView subtitleView;
        final /* synthetic */ InviteLinkBottomSheet this$0;
        public final TextView titleView;

        public RevenueCell(InviteLinkBottomSheet r11, Context r12) {
            this.this$0 = r11;
            super(r12);
            ImageView r112 = new ImageView(r12);
            this.imageView = r112;
            r112.setBackground(Theme.createCircleDrawable(46, Theme.getColor(Theme.key_avatar_backgroundGreen), Theme.getColor(Theme.key_avatar_background2Green)));
            r112.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            r112.setImageResource(R.drawable.large_income);
            r112.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
            addView(r112, LayoutHelper.createFrame(46, 46.0f, 19, 13.0f, 0.0f, 0.0f, 0.0f));
            TextView r113 = new TextView(r12);
            this.titleView = r113;
            r113.setTextSize(1, 16.0f);
            r113.setTypeface(AndroidUtilities.bold());
            r113.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            addView(r113, LayoutHelper.createFrame(-1, -2.0f, 51, 72.0f, 9.0f, 0.0f, 0.0f));
            TextView r114 = new TextView(r12);
            this.subtitleView = r114;
            r114.setTextSize(1, 14.0f);
            r114.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
            addView(r114, LayoutHelper.createFrame(-1, -2.0f, 51, 72.0f, 32.0f, 0.0f, 0.0f));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(58.0f), TLObject.FLAG_30));
        }

        public void set(TL_stars.TL_starsSubscriptionPricing r12, int r13) {
            if (r12 != null) goto L4;
            return;
        L4:
            int r0 = r12.period;
            String r6 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r0 != 2592000) goto L16;
            TextView r02 = this.titleView;
            StringBuilder r1 = new StringBuilder();
            r1.append(LocaleController.formatString(R.string.LinkRevenuePrice, new Object[]{Long.valueOf(r12.amount)}));
            if (r13 <= 0) goto L9;
            r6 = " x " + r13;
        L9:
            r1.append(r6);
            r02.setText(StarsIntroActivity.replaceStarsWithPlain(r1.toString(), 0.8f));
            TextView r03 = this.subtitleView;
            if (r13 != 0) goto L12;
            String r11 = LocaleController.getString(R.string.NoOneSubscribed);
        L13:
            r03.setText(r11);
            return;
        L12:
            r11 = LocaleController.formatString(R.string.LinkRevenuePriceInfo, new Object[]{BillingController.getInstance().formatCurrency((long) (((r12.amount / 1000.0d) * MessagesController.getInstance(InviteLinkBottomSheet.access$3200(this.this$0)).starsUsdWithdrawRate1000) * r13), "USD")});
            goto L13
        L16:
            if (r0 != 300) goto L18;
            String r04 = "5min";
        L19:
            TextView r14 = this.titleView;
            Locale r8 = Locale.US;
            String r9 = String.format(r8, "⭐%1$d/%2$s", new Object[]{Long.valueOf(r12.amount), r04});
            if (r13 <= 0) goto L22;
            r6 = " x " + r13;
        L22:
            r14.setText(StarsIntroActivity.replaceStarsWithPlain(r9.concat(r6), 0.8f));
            TextView r15 = this.subtitleView;
            if (r13 != 0) goto L25;
            String r112 = LocaleController.getString(R.string.NoOneSubscribed);
        L26:
            r15.setText(r112);
            return;
        L25:
            r112 = String.format(r8, "you get approximately %1$s %2$s", new Object[]{BillingController.getInstance().formatCurrency((long) (((r12.amount / 1000.0d) * MessagesController.getInstance(InviteLinkBottomSheet.access$3300(this.this$0)).starsUsdWithdrawRate1000) * r13), "USD"), "for ".concat(r04)});
            goto L26
        L18:
            r04 = "min";
            goto L19
        }
    }

    public class RevenueUserCell extends UserCell {
        public final LinearLayout layout;
        public final TextView periodView;
        public final TextView priceView;
        final /* synthetic */ InviteLinkBottomSheet this$0;

        public RevenueUserCell(InviteLinkBottomSheet r12, Context r13) {
            this.this$0 = r12;
            super(r13, 6, 0, true);
            LinearLayout r122 = new LinearLayout(r13);
            this.layout = r122;
            r122.setOrientation(1);
            TextView r0 = new TextView(r13);
            this.priceView = r0;
            r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            r0.setTextSize(1, 16.0f);
            r0.setTypeface(AndroidUtilities.bold());
            int r3 = 5;
            r122.addView(r0, LayoutHelper.createLinear(-2, -2, 5));
            TextView r02 = new TextView(r13);
            this.periodView = r02;
            r02.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
            r02.setTextSize(1, 13.0f);
            r122.addView(r02, LayoutHelper.createLinear(-2, -2, 5, 0, 1, 0, 0));
            if (LocaleController.isRTL == false) goto L5;
            r3 = 3;
        L5:
            addView(r122, LayoutHelper.createFrame(-2, -2.0f, r3 | 16, 18.0f, 0.0f, 18.0f, 0.0f));
        }

        public void setRevenue(TL_stars.TL_starsSubscriptionPricing r5, int r6) {
            if (r5 != null) goto L6;
            this.priceView.setText(null);
            this.periodView.setText(null);
            setRightPadding(0, true, true);
            return;
        L6:
            SpannableStringBuilder r1 = StarsIntroActivity.replaceStarsWithPlain("⭐️" + r5.amount, 0.7f);
            int r52 = r5.period;
            if (r52 != 2592000) goto L10;
            String r53 = LocaleController.getString(R.string.StarsParticipantSubscriptionPerMonth);
        L13:
            this.priceView.setText(r1);
            this.periodView.setText(r53);
            setRightPadding((int) Math.max(HintView2.measureCorrectly(r1, this.priceView.getPaint()), HintView2.measureCorrectly(r53, this.periodView.getPaint())), true, true);
            this.statusTextView.setText(LocaleController.formatJoined(r6));
            return;
        L10:
            if (r52 != 300) goto L12;
            r53 = "per 5 minutes";
            goto L13
        L12:
            r53 = "per each minute";
            goto L13
        }
    }

    public class TimerPrivacyCell extends TextInfoPrivacyCell {
        final /* synthetic */ InviteLinkBottomSheet this$0;
        boolean timer;
        Runnable timerRunnable;

        /* renamed from: org.telegram.ui.Components.InviteLinkBottomSheet$TimerPrivacyCell$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ TimerPrivacyCell this$1;

            public AnonymousClass1(TimerPrivacyCell r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (InviteLinkBottomSheet.m11073$$Nest$fgetlistView(this.this$1.this$0) != null) goto L5;
            L9:
                AndroidUtilities.runOnUIThread(this);
                return;
            L5:
                if (InviteLinkBottomSheet.m11073$$Nest$fgetlistView(this.this$1.this$0).getAdapter() == null) goto L9;
                int r0 = InviteLinkBottomSheet.m11073$$Nest$fgetlistView(this.this$1.this$0).getChildAdapterPosition(this.this$1);
                if (r0 < 0) goto L9;
                InviteLinkBottomSheet r1 = this.this$1.this$0;
                r1.adapter.onBindViewHolder(InviteLinkBottomSheet.m11073$$Nest$fgetlistView(r1).getChildViewHolder(this.this$1), r0);
                goto L9
            }
        }

        public TimerPrivacyCell(InviteLinkBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.timerRunnable = new AnonymousClass1(this);
        }

        public void cancelTimer() {
            AndroidUtilities.cancelRunOnUIThread(this.timerRunnable);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            runTimer();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            cancelTimer();
        }

        public void runTimer() {
            cancelTimer();
            if (this.timer == false) goto L6;
            AndroidUtilities.runOnUIThread(this.timerRunnable, 500);
            return;
        }
    }

    /* renamed from: $r8$lambda$-xRNX3xEo71MWypwmgQTMWcK5W4, reason: not valid java name */
    public static /* synthetic */ void m11065$r8$lambda$xRNX3xEo71MWypwmgQTMWcK5W4(InviteLinkBottomSheet r0, TLRPC.User r1, BaseFragment r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$0NUEhTFE1_Lwjnc3FGgTiBgtv6M(InviteLinkBottomSheet r0, List r1, boolean r2, boolean r3, boolean r4, boolean r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$loadUsers$7(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$C05AT8kjqsC8bsfZkocx46HDH_o(InviteLinkBottomSheet r0, TLObject r1) {
        r0.lambda$loadCreator$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$Fup4DBioGKA4t_gOvlYNyLSzrEU(InviteLinkBottomSheet r0, AlertDialog r1, Context r2, long r3, TLRPC.TL_chatInviteExported r5, TLRPC.TL_chatInviteImporter r6, TLRPC.ChannelParticipant r7) {
        r0.lambda$new$0(r1, r2, r3, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$J-b6r_SAy06Ou0g7QQQcxRhUo20, reason: not valid java name */
    public static /* synthetic */ void m11066$r8$lambda$Jb6r_SAy06Ou0g7QQQcxRhUo20(InviteLinkBottomSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadCreator$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$RAifFcnvR9AMA7QSZs3FOl5nZuc(BottomSheet[] r0, View r1) {
        r0[0].dismiss();
    }

    /* renamed from: $r8$lambda$SATB-JY4oVEYQD07Y7IPMdyCW8w, reason: not valid java name */
    public static /* synthetic */ void m11067$r8$lambda$SATBJY4oVEYQD07Y7IPMdyCW8w(InviteLinkBottomSheet r0, AlertDialog r1, Context r2, long r3, TLRPC.TL_chatInviteExported r5, TLRPC.TL_chatInviteImporter r6, TLRPC.ChannelParticipant r7) {
        r0.lambda$new$1(r1, r2, r3, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$W8Aa0s-cmLmMWOZPu07xwc2iM0Y, reason: not valid java name */
    public static /* synthetic */ void m11068$r8$lambda$W8Aa0scmLmMWOZPu07xwc2iM0Y(InviteLinkBottomSheet r0, TLRPC.TL_error r1, TLObject r2, List r3, boolean r4, boolean r5, boolean r6, boolean r7) {
        r0.lambda$loadUsers$6(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$__2YuoicMaFZai95J4SPU4xBnJc(Context r1) {
        Browser.openUrl(r1, LocaleController.getString(R.string.StarsTOSLink));
    }

    public static /* synthetic */ void $r8$lambda$xFBtOwhsbkqPdS2873DRoqizSGM(InviteLinkBottomSheet r0, TLRPC.TL_chatInviteExported r1, HashMap r2, TLRPC.ChatFull r3, Context r4, long r5, BaseFragment r7, View r8, int r9) {
        r0.lambda$new$3(r1, r2, r3, r4, r5, r7, r8, r9);
    }

    /* renamed from: -$$Nest$fgetcanEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11069$$Nest$fgetcanEdit(InviteLinkBottomSheet r0) {
        return r0.canEdit;
    }

    /* renamed from: -$$Nest$fgetchatId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11070$$Nest$fgetchatId(InviteLinkBottomSheet r2) {
        return r2.chatId;
    }

    /* renamed from: -$$Nest$fgetignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11071$$Nest$fgetignoreLayout(InviteLinkBottomSheet r0) {
        return r0.ignoreLayout;
    }

    /* renamed from: -$$Nest$fgetisChannel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11072$$Nest$fgetisChannel(InviteLinkBottomSheet r0) {
        return r0.isChannel;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m11073$$Nest$fgetlistView(InviteLinkBottomSheet r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11074$$Nest$fgetscrollOffsetY(InviteLinkBottomSheet r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetshadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m11075$$Nest$fgetshadow(InviteLinkBottomSheet r0) {
        return r0.shadow;
    }

    /* renamed from: -$$Nest$fgetshadowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m11076$$Nest$fgetshadowAnimation(InviteLinkBottomSheet r0) {
        return r0.shadowAnimation;
    }

    /* renamed from: -$$Nest$fgettimeDif, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11077$$Nest$fgettimeDif(InviteLinkBottomSheet r2) {
        return r2.timeDif;
    }

    /* renamed from: -$$Nest$fputignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11078$$Nest$fputignoreLayout(InviteLinkBottomSheet r0, boolean r1) {
        r0.ignoreLayout = r1;
    }

    /* renamed from: -$$Nest$fputshadowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11079$$Nest$fputshadowAnimation(InviteLinkBottomSheet r0, AnimatorSet r1) {
        r0.shadowAnimation = r1;
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11080$$Nest$mupdateLayout(InviteLinkBottomSheet r0) {
        r0.updateLayout();
    }

    public InviteLinkBottomSheet(Context r19, TLRPC.TL_chatInviteExported r20, TLRPC.ChatFull r21, HashMap<Long, TLRPC.User> r22, BaseFragment r23, long r24, boolean r26, boolean r27) {
        super(r19, false);
        this.joinedUsers = new ArrayList();
        this.expiredUsers = new ArrayList();
        this.requestedUsers = new ArrayList();
        this.canEdit = true;
        this.isNeedReopen = false;
        this.invite = r20;
        this.users = r22;
        this.fragment = r23;
        this.info = r21;
        this.chatId = r24;
        this.permanent = r26;
        this.isChannel = r27;
        int r0 = Theme.key_windowBackgroundGray;
        setBackgroundColor(getThemedColor(r0));
        fixNavigationBar(getThemedColor(r0));
        this.behindKeyboardColorKey = -1;
        if (this.users != null) goto L5;
        this.users = new HashMap();
    L5:
        this.timeDif = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() - (System.currentTimeMillis() / 1000);
        AnonymousClass1 r12 = new AnonymousClass1(this, r19);
        this.containerView = r12;
        r12.setWillNotDraw(false);
        FrameLayout.LayoutParams r122 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 51);
        r122.topMargin = AndroidUtilities.dp(48.0f);
        View r02 = new View(r19);
        this.shadow = r02;
        r02.setAlpha(0.0f);
        this.shadow.setVisibility(4);
        this.shadow.setTag(1);
        this.containerView.addView(this.shadow, r122);
        AnonymousClass2 r03 = new AnonymousClass2(this, r19);
        this.listView = r03;
        r03.setSections();
        this.listView.setTag(14);
        LinearLayoutManager r04 = new LinearLayoutManager(getContext(), 1, false);
        this.listView.setLayoutManager(r04);
        RecyclerListView r123 = this.listView;
        Adapter r15 = new Adapter(this, null);
        this.adapter = r15;
        r123.setAdapter(r15);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setClipToPadding(false);
        this.listView.setNestedScrollingEnabled(true);
        this.listView.setOnScrollListener(new AnonymousClass3(this, r04));
        this.listView.setOnItemClickListener(new InviteLinkBottomSheet$$ExternalSyntheticLambda0(this, r20, r22, r21, r19, r24, r23));
        TextView r05 = new TextView(r19);
        this.titleTextView = r05;
        r05.setLines(1);
        this.titleTextView.setSingleLine(true);
        this.titleTextView.setTextSize(1, 20.0f);
        this.titleTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.titleTextView.setPadding(AndroidUtilities.dp(23.0f), 0, AndroidUtilities.dp(23.0f), 0);
        this.titleTextView.setGravity(16);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        if (r26 == false) goto L8;
        this.titleTextView.setText(LocaleController.getString(R.string.InviteLink));
        this.titleVisible = false;
        this.titleTextView.setVisibility(4);
        float r4 = 0.0f;
        this.titleTextView.setAlpha(0.0f);
    L17:
        if (TextUtils.isEmpty(r20.title) == true) goto L19;
        SpannableStringBuilder r06 = new SpannableStringBuilder(r20.title);
        Emoji.replaceEmoji(r06, this.titleTextView.getPaint().getFontMetricsInt(), false);
        this.titleTextView.setText(r06);
    L19:
        ViewGroup r07 = this.containerView;
        RecyclerListView r5 = this.listView;
        float r7 = 44.0f;
        if (this.titleVisible == true) goto L22;
        float r124 = r4;
    L23:
        r07.addView(r5, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, r124, 0.0f, 0.0f));
        ViewGroup r08 = this.containerView;
        TextView r42 = this.titleTextView;
        if (this.titleVisible == false) goto L27;
        r7 = 50.0f;
    L27:
        r08.addView(r42, LayoutHelper.createFrame(-1, r7, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        updateRows();
        loadUsers();
        if (r22 != null) goto L30;
    L31:
        loadCreator();
    L32:
        updateColors();
        return;
    L30:
        if (r22.get(Long.valueOf(r20.admin_id)) != null) goto L32;
    L22:
        r124 = 44.0f;
        goto L23
    L8:
        if (r20.expired == false) goto L10;
        this.titleTextView.setText(LocaleController.getString(R.string.ExpiredLink));
    L14:
        this.titleVisible = true;
        r4 = 0.0f;
        goto L17
    L10:
        boolean r09 = r20.revoked;
        TextView r43 = this.titleTextView;
        if (r09 == false) goto L13;
        r43.setText(LocaleController.getString(R.string.RevokedLink));
        goto L14
    L13:
        r43.setText(LocaleController.getString(R.string.InviteLink));
        goto L14
    }

    public static /* synthetic */ int access$000(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$100(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1000(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1100(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1200(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1300(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1400(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1500(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1600(InviteLinkBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1700(InviteLinkBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1800(InviteLinkBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1900(InviteLinkBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$200(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2000(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2100(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2200(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2300(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2400(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2500(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2600(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2700(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2800(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2900(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$3000(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$3100(InviteLinkBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$3200(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3300(InviteLinkBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$500(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$600(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$700(InviteLinkBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Drawable access$800(InviteLinkBottomSheet r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ Drawable access$900(InviteLinkBottomSheet r0) {
        return r0.shadowDrawable;
    }

    private /* synthetic */ void lambda$loadCreator$4(TLObject r4) {
        if ((r4 instanceof Vector) == false) goto L9;
        Vector r42 = (Vector) r4;
        if (r42.objects.isEmpty() == true) goto L10;
        TLRPC.User r43 = (TLRPC.User) r42.objects.get(0);
        this.users.put(Long.valueOf(this.invite.admin_id), r43);
        this.adapter.notifyDataSetChanged();
        return;
    L10:
        return;
    }

    private /* synthetic */ void lambda$loadCreator$5(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new InviteLinkBottomSheet$$ExternalSyntheticLambda7(this, r1));
    }

    private /* synthetic */ void lambda$loadUsers$6(TLRPC.TL_error r6, TLObject r7, List r8, boolean r9, boolean r10, boolean r11, boolean r12) {
        if (r6 != null) goto L24;
        TLRPC.TL_messages_chatInviteImporters r72 = (TLRPC.TL_messages_chatInviteImporters) r7;
        r8.addAll(r72.importers);
        int r62 = 0;
    L6:
        if (r62 >= r72.users.size()) goto L8;
        TLRPC.User r1 = r72.users.get(r62);
        this.users.put(Long.valueOf(r1.id), r1);
        r62 = r62 + 1;
        goto L6
    L8:
        boolean r63 = true;
        if (r9 == true) goto L11;
        if (r10 == false) goto L20;
        if (r8.size() < r72.count) goto L23;
        if (r11 == true) goto L23;
    L13:
        r63 = false;
    L23:
        this.hasMore = r63;
        updateRows();
        goto L24
    L20:
        if (r8.size() < r72.count) goto L23;
        if (r11 == true) goto L23;
        if (r12 == false) goto L13;
    L11:
        if (r8.size() >= r72.count) goto L13;
    L24:
        this.usersLoading = false;
    }

    private /* synthetic */ void lambda$loadUsers$7(List r10, boolean r11, boolean r12, boolean r13, boolean r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new InviteLinkBottomSheet$$ExternalSyntheticLambda8(this, r16, r15, r10, r11, r12, r13, r14));
    }

    private /* synthetic */ void lambda$new$0(AlertDialog r4, Context r5, long r6, TLRPC.TL_chatInviteExported r8, TLRPC.TL_chatInviteImporter r9, TLRPC.ChannelParticipant r10) {
        r4.dismissUnless(400);
        showSubscriptionSheet(r5, this.currentAccount, -r6, r8.subscription_pricing, r9, r10, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$new$1(AlertDialog r10, Context r11, long r12, TLRPC.TL_chatInviteExported r14, TLRPC.TL_chatInviteImporter r15, TLRPC.ChannelParticipant r16) {
        AndroidUtilities.runOnUIThread(new InviteLinkBottomSheet$$ExternalSyntheticLambda9(this, r10, r11, r12, r14, r15, r16));
    }

    private /* synthetic */ void lambda$new$2(TLRPC.User r5, BaseFragment r6) {
        Bundle r0 = new Bundle();
        r0.putLong("user_id", r5.id);
        r6.presentFragment(new ProfileActivity(r0));
        this.isNeedReopen = true;
    }

    private /* synthetic */ void lambda$new$3(TLRPC.TL_chatInviteExported r24, HashMap r25, TLRPC.ChatFull r26, Context r27, long r28, BaseFragment r30, View r31, int r32) {
        if (r32 == this.creatorRow) goto L5;
    L7:
        int r4 = this.joinedStartRow;
        boolean r5 = true;
        int r7 = 0;
        if (r32 >= r4) goto L10;
    L12:
        boolean r8 = false;
    L13:
        int r9 = this.expiredStartRow;
        if (r32 >= r9) goto L16;
    L18:
        boolean r10 = false;
    L19:
        int r11 = this.requestedStartRow;
        if (r32 >= r11) goto L22;
    L24:
        r5 = false;
    L26:
        if (r32 == this.creatorRow) goto L29;
        if (r8 == true) goto L29;
        if (r5 == true) goto L29;
        return;
    L29:
        if (r25 == null) goto L69;
        long r12 = r24.admin_id;
        TLRPC.ChannelParticipant r14 = null;
        if (r8 == false) goto L34;
        TLRPC.TL_chatInviteImporter r3 = this.joinedUsers.get(r32 - r4);
        r12 = r3.user_id;
    L33:
        TLRPC.TL_chatInviteImporter r20 = r3;
    L39:
        TLRPC.User r92 = (TLRPC.User) r25.get(Long.valueOf(r12));
        if (r92 == null) goto L70;
        MessagesController.getInstance(UserConfig.selectedAccount).putUser(r92, false);
        if (r8 == true) goto L44;
    L62:
        AndroidUtilities.runOnUIThread(new InviteLinkBottomSheet$$ExternalSyntheticLambda4(this, r92, r30), 100);
        dismiss();
        return;
    L44:
        if (r24.subscription_pricing == null) goto L62;
        if (r26 != null) goto L47;
    L55:
        TLRPC.ChannelParticipant r21 = r14;
        if (r21 != null) goto L60;
        AlertDialog r2 = new AlertDialog(r27, 3);
        r2.showDelayed(120);
        MessagesController.getInstance(this.currentAccount).getChannelParticipant(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r28)), r92, new InviteLinkBottomSheet$$ExternalSyntheticLambda3(this, r2, r27, r28, r24, r20));
        return;
    L60:
        showSubscriptionSheet(r27, this.currentAccount, -r28, r24.subscription_pricing, r20, r21, this.resourcesProvider);
        return;
    L47:
        if (r26.participants == null) goto L55;
    L49:
        if (r7 >= r26.participants.participants.size()) goto L55;
        if (r26.participants.participants.get(r7).user_id != r12) goto L56;
        if ((r26.participants.participants.get(r7) instanceof TLRPC.TL_chatChannelParticipant) == false) goto L56;
        r14 = ((TLRPC.TL_chatChannelParticipant) r26.participants.participants.get(r7)).channelParticipant;
    L56:
        r7 = r7 + 1;
        goto L49
    L70:
        return;
    L34:
        if (r10 == false) goto L36;
        r3 = this.expiredUsers.get(r32 - r9);
        r12 = r3.user_id;
        goto L33
    L36:
        if (r5 == false) goto L38;
        r3 = this.requestedUsers.get(r32 - r11);
        r12 = r3.user_id;
        goto L33
    L38:
        r20 = null;
        goto L39
    L69:
        return;
    L22:
        if (r32 >= this.requestedEndRow) goto L24;
    L16:
        if (r32 >= this.expiredEndRow) goto L18;
        r10 = true;
        goto L19
    L10:
        if (r32 >= this.joinedEndRow) goto L12;
        r8 = true;
        goto L13
    L5:
        if (r24.admin_id != UserConfig.getInstance(this.currentAccount).clientUserId) goto L7;
    }

    private void loadCreator() {
        TLRPC.TL_users_getUsers r0 = new TLRPC.TL_users_getUsers();
        r0.id.add(MessagesController.getInstance(UserConfig.selectedAccount).getInputUser(this.invite.admin_id));
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r0, new InviteLinkBottomSheet$$ExternalSyntheticLambda2(this));
    }

    private void runShadowAnimation(boolean r10) {
        if (r10 == true) goto L4;
    L5:
        if (r10 == false) goto L7;
        return;
    L7:
        if (this.shadow.getTag() != null) goto L31;
    L8:
        View r0 = this.shadow;
        if (r10 == false) goto L11;
        Integer r2 = null;
    L12:
        r0.setTag(r2);
        if (r10 == false) goto L15;
        this.shadow.setVisibility(0);
        this.titleTextView.setVisibility(0);
    L15:
        AnimatorSet r22 = this.shadowAnimation;
        if (r22 == null) goto L18;
        r22.cancel();
    L18:
        AnimatorSet r23 = new AnimatorSet();
        this.shadowAnimation = r23;
        View r3 = this.shadow;
        Property r4 = View.ALPHA;
        float r5 = 0.0f;
        if (r10 == false) goto L21;
        float r7 = 1.0f;
    L22:
        r23.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, r4, new float[]{r7})});
        if (this.titleVisible == true) goto L28;
        AnimatorSet r24 = this.shadowAnimation;
        TextView r32 = this.titleTextView;
        if (r10 == false) goto L27;
        r5 = 1.0f;
    L27:
        r24.playTogether(new Animator[]{ObjectAnimator.ofFloat(r32, r4, new float[]{r5})});
    L28:
        this.shadowAnimation.setDuration(150);
        this.shadowAnimation.addListener(new AnonymousClass4(this, r10));
        this.shadowAnimation.start();
        return;
    L21:
        r7 = 0.0f;
        goto L22
    L11:
        r2 = 1;
        goto L12
    L31:
        return;
    L4:
        if (this.shadow.getTag() != null) goto L8;
        goto L5
    }

    public static BottomSheet showSubscriptionSheet(Context r27, int r28, long r29, TL_stars.TL_starsSubscriptionPricing r31, TLRPC.TL_chatInviteImporter r32, TLRPC.ChannelParticipant r33, Theme.ResourcesProvider r34) {
        BottomSheet.Builder r7 = new BottomSheet.Builder(r27, false, r34);
        BottomSheet[] r10 = new BottomSheet[1];
        LinearLayout r11 = new LinearLayout(r27);
        r11.setOrientation(1);
        r11.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(4.0f));
        r11.setClipChildren(false);
        r11.setClipToPadding(false);
        FrameLayout r9 = new FrameLayout(r27);
        r11.addView(r9, LayoutHelper.createLinear(-1, -2, 7, 0, 0, 0, 10));
        BackupImageView r12 = new BackupImageView(r27);
        r12.setRoundRadius(AndroidUtilities.dp(50.0f));
        AvatarDrawable r13 = new AvatarDrawable();
        if (r29 < 0) goto L5;
        TLRPC.User r1 = MessagesController.getInstance(r28).getUser(Long.valueOf(r29));
        r13.setInfo(r1);
        r12.setForUserOrChat(r1, r13);
    L6:
        r9.addView(r12, LayoutHelper.createFrame(100, 100, 17));
        Drawable r14 = r27.getResources().getDrawable(R.drawable.star_small_outline);
        r14.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackground, r34), PorterDuff.Mode.SRC_IN));
        Drawable r122 = r27.getResources().getDrawable(R.drawable.star_small_inner);
        ImageView r132 = new ImageView(r27);
        r132.setImageDrawable(r14);
        r9.addView(r132, LayoutHelper.createFrame(28, 28, 17));
        r132.setTranslationX(AndroidUtilities.dp(34.0f));
        r132.setTranslationY(AndroidUtilities.dp(35.0f));
        r132.setScaleX(1.1f);
        r132.setScaleY(1.1f);
        ImageView r133 = new ImageView(r27);
        r133.setImageDrawable(r122);
        r9.addView(r133, LayoutHelper.createFrame(28, 28, 17));
        r133.setTranslationX(AndroidUtilities.dp(34.0f));
        r133.setTranslationY(AndroidUtilities.dp(35.0f));
        TextView r15 = new TextView(r27);
        r15.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r34));
        r15.setTextSize(1, 20.0f);
        r15.setTypeface(AndroidUtilities.bold());
        r15.setGravity(17);
        r15.setText(LocaleController.getString(R.string.StarsSubscriptionTitle));
        r11.addView(r15, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 4));
        TextView r16 = new TextView(r27);
        r16.setTextSize(1, 14.0f);
        r16.setGravity(17);
        int r123 = Theme.key_windowBackgroundWhiteGrayText4;
        r16.setTextColor(Theme.getColor(r123, r34));
        int r134 = r31.period;
        if (r134 != 2592000) goto L9;
        BottomSheet[] r17 = r10;
        r16.setText(StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatString(R.string.StarsSubscriptionPrice, new Object[]{Long.valueOf(r31.amount)}), 0.8f));
        Object r19 = "min";
    L14:
        r11.addView(r16, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 4));
        TextView r18 = new TextView(r27);
        r18.setTextSize(1, 14.0f);
        r18.setGravity(17);
        r18.setTextColor(Theme.getColor(r123, r34));
        int r2 = r31.period;
        if (r2 != 2592000) goto L17;
        r18.setText(LocaleController.formatString(R.string.StarsParticipantSubscriptionApproxMonth, new Object[]{BillingController.getInstance().formatCurrency((int) ((r31.amount / 1000.0d) * MessagesController.getInstance(r28).starsUsdWithdrawRate1000), "USD")}));
    L21:
        r11.addView(r18, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 4));
        TableView r110 = new TableView(r27, r34);
        LinkSpanDrawable.LinksTextView r22 = new LinkSpanDrawable.LinksTextView(r27, r34);
        r22.setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
        r22.setEllipsize(TextUtils.TruncateAt.END);
        int r3 = Theme.key_chat_messageLinkIn;
        r22.setTextColor(Theme.getColor(r3, r34));
        r22.setLinkTextColor(Theme.getColor(r3, r34));
        r22.setTextSize(1, 14.0f);
        r22.setSingleLine(true);
        r22.setDisablePaddingsOffsetY(true);
        AvatarSpan r8 = new AvatarSpan(r22, r28, 24.0f);
        TLRPC.User r92 = MessagesController.getInstance(r28).getUser(Long.valueOf(r32.user_id));
        if (r92 != null) goto L24;
        boolean r124 = true;
    L25:
        String r135 = UserObject.getUserName(r92);
        r8.setUser(r92);
        SpannableStringBuilder r93 = new SpannableStringBuilder("x  " + r135);
        r93.setSpan(r8, 0, 1, 33);
        BottomSheet[] r142 = r17;
        r93.setSpan(new AnonymousClass5(r142, r32), 3, r93.length(), 33);
        r22.setText(r93);
        if (r124 == true) goto L28;
        r110.addRowUnpadded(LocaleController.getString(R.string.StarsParticipantSubscription), r22);
    L28:
        r110.addRow(LocaleController.getString(R.string.StarsParticipantSubscriptionStart), LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterGiveawayCard().format(new Date(r32.date * 1000)), LocaleController.getInstance().getFormatterDay().format(new Date(r32.date * 1000))}));
        int r23 = ConnectionsManager.getInstance(r28).getCurrentTime();
        if (r33 != null) goto L31;
    L35:
        r11.addView(r110, LayoutHelper.createLinear(-1, -2, 0.0f, 17.0f, 0.0f, 0.0f));
        LinkSpanDrawable.LinksTextView r111 = new LinkSpanDrawable.LinksTextView(r27, r34);
        r111.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r34));
        r111.setLinkTextColor(Theme.getColor(r3, r34));
        r111.setTextSize(1, 14.0f);
        r111.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StarsTransactionTOS), new InviteLinkBottomSheet$$ExternalSyntheticLambda5(r27)));
        r111.setGravity(17);
        r11.addView(r111, LayoutHelper.createLinear(-1, -2, 14.0f, 15.0f, 14.0f, 15.0f));
        ButtonWithCounterView r112 = new ButtonWithCounterView(r27, true, r34);
        r112.setText(LocaleController.getString(R.string.OK), false);
        r11.addView(r112, LayoutHelper.createLinear(-1, 48));
        r112.setOnClickListener(new InviteLinkBottomSheet$$ExternalSyntheticLambda6(r142));
        r7.setCustomView(r11);
        BottomSheet r0 = r7.create();
        r142[0] = r0;
        r0.useBackgroundTopPadding = false;
        r0.fixNavigationBar();
        r142[0].show();
        return r142[0];
    L31:
        if (r33.subscription_until_date <= r23) goto L33;
        int r24 = R.string.StarsParticipantSubscriptionRenews;
    L34:
        r110.addRow(LocaleController.getString(r24), LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterGiveawayCard().format(new Date(r33.subscription_until_date * 1000)), LocaleController.getInstance().getFormatterDay().format(new Date(r33.subscription_until_date * 1000))}));
        goto L35
    L33:
        r24 = R.string.StarsParticipantSubscriptionExpired;
        goto L34
    L24:
        r124 = false;
        goto L25
    L17:
        if (r2 != 300) goto L19;
        Object r143 = "5min";
    L20:
        double r125 = r31.amount / 1000.0d;
        r18.setText(String.format(Locale.US, "appx. %1$s per %2$s", new Object[]{BillingController.getInstance().formatCurrency((int) (MessagesController.getInstance(r28).starsUsdWithdrawRate1000 * r125), "USD"), r143}));
        goto L21
    L19:
        r143 = r19;
        goto L20
    L9:
        r17 = r10;
        if (r134 != 300) goto L12;
        Object r94 = "5min";
    L13:
        r19 = "min";
        r16.setText(StarsIntroActivity.replaceStarsWithPlain(String.format(Locale.US, "⭐%1$d/%2$s", new Object[]{Long.valueOf(r31.amount), r94}), 0.8f));
        goto L14
    L12:
        r94 = "min";
        goto L13
    L5:
        TLRPC.Chat r113 = MessagesController.getInstance(r28).getChat(Long.valueOf(-r29));
        r13.setInfo(r113);
        r12.setForUserOrChat(r113, r13);
        goto L6
    }

    private void updateColorForView(View r1) {
        if ((r1 instanceof HeaderCell) == false) goto L7;
        ((HeaderCell) r1).getTextView().setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader));
        return;
    L7:
        if ((r1 instanceof LinkActionView) == false) goto L11;
        ((LinkActionView) r1).updateColors();
        return;
    L11:
        if ((r1 instanceof TextInfoPrivacyCell) == false) goto L15;
        ((TextInfoPrivacyCell) r1).setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
        return;
    L15:
        if ((r1 instanceof UserCell) == false) goto L18;
        ((UserCell) r1).update(0);
        return;
    }

    private void updateLayout() {
        int r0 = this.listView.getChildCount();
        RecyclerListView r1 = this.listView;
        if (r0 > 0) goto L6;
        int r02 = r1.getPaddingTop();
        this.scrollOffsetY = r02;
        r1.setTopGlowOffset(r02);
        this.titleTextView.setTranslationY(this.scrollOffsetY);
        this.shadow.setTranslationY(this.scrollOffsetY);
        this.containerView.invalidate();
        return;
    L6:
        int r03 = 0;
        View r12 = r1.getChildAt(0);
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r12);
        int r13 = r12.getTop();
        if (r13 < 0) goto L12;
        if (r2 == null) goto L12;
        if (r2.getAdapterPosition() != 0) goto L12;
        runShadowAnimation(false);
        r03 = r13;
    L14:
        if (this.scrollOffsetY == r03) goto L20;
        RecyclerListView r14 = this.listView;
        this.scrollOffsetY = r03;
        r14.setTopGlowOffset(r03);
        TextView r04 = this.titleTextView;
        if (r04 == null) goto L18;
        r04.setTranslationY(this.scrollOffsetY);
    L18:
        this.shadow.setTranslationY(this.scrollOffsetY);
        this.containerView.invalidate();
        return;
    L20:
        return;
    L12:
        runShadowAnimation(true);
        goto L14
    }

    private void updateRows() {
        boolean r0 = false;
        this.rowCount = 0;
        this.dividerRow = -1;
        this.divider2Row = -1;
        this.divider3Row = -1;
        this.joinedHeaderRow = -1;
        this.joinedStartRow = -1;
        this.joinedEndRow = -1;
        this.emptyView2 = -1;
        this.emptyView3 = -1;
        this.linkActionRow = -1;
        this.linkInfoRow = -1;
        this.emptyHintRow = -1;
        this.requestedHeaderRow = -1;
        this.requestedStartRow = -1;
        this.requestedEndRow = -1;
        this.loadingRow = -1;
        this.revenueHeaderRow = -1;
        this.revenueRow = -1;
        this.expiredHeaderRow = -1;
        this.expiredStartRow = -1;
        this.expiredEndRow = -1;
        boolean r2 = true;
        if (this.permanent == true) goto L5;
        this.linkActionRow = 0;
        this.rowCount = 1 + 1;
        this.linkInfoRow = 1;
    L5:
        TLRPC.TL_chatInviteExported r1 = this.invite;
        if (r1.subscription_pricing == null) goto L8;
        int r3 = this.rowCount;
        this.revenueHeaderRow = r3;
        this.rowCount = r3 + 2;
        this.revenueRow = r3 + 1;
    L8:
        int r32 = this.rowCount;
        this.creatorHeaderRow = r32;
        this.rowCount = r32 + 2;
        this.creatorRow = r32 + 1;
        int r33 = r1.usage;
        if (r33 <= 0) goto L11;
    L18:
        boolean r12 = true;
    L20:
        if (r33 <= this.joinedUsers.size()) goto L22;
    L29:
        boolean r34 = true;
    L31:
        if (this.joinedUsers.isEmpty() == true) goto L34;
        int r02 = this.rowCount;
        int r4 = r02 + 1;
        this.rowCount = r4;
        this.joinedHeaderRow = r02;
        this.joinedStartRow = r4;
        int r42 = r4 + this.joinedUsers.size();
        this.rowCount = r42;
        this.joinedEndRow = r42;
        r0 = true;
    L34:
        if (this.expiredUsers.isEmpty() == true) goto L37;
        int r03 = this.rowCount;
        int r43 = r03 + 1;
        this.rowCount = r43;
        this.expiredHeaderRow = r03;
        this.expiredStartRow = r43;
        int r44 = r43 + this.expiredUsers.size();
        this.rowCount = r44;
        this.expiredEndRow = r44;
        r0 = true;
    L37:
        if (this.requestedUsers.isEmpty() == true) goto L39;
        int r04 = this.rowCount;
        int r45 = r04 + 1;
        this.rowCount = r45;
        this.requestedHeaderRow = r04;
        this.requestedStartRow = r45;
        int r46 = r45 + this.requestedUsers.size();
        this.rowCount = r46;
        this.requestedEndRow = r46;
    L40:
        if (r12 == true) goto L42;
        if (r34 == true) goto L42;
    L44:
        this.adapter.notifyDataSetChanged();
        return;
    L42:
        if (r2 == true) goto L44;
        int r05 = this.rowCount;
        this.dividerRow = r05;
        this.loadingRow = r05 + 1;
        this.rowCount = r05 + 3;
        this.emptyView2 = r05 + 2;
        goto L44
    L39:
        r2 = r0;
        goto L40
    L22:
        if (this.invite.subscription_expired > this.expiredUsers.size()) goto L29;
        TLRPC.TL_chatInviteExported r35 = this.invite;
        if (r35.request_needed == true) goto L26;
    L28:
        r34 = false;
        goto L31
    L26:
        if (r35.requested <= this.requestedUsers.size()) goto L28;
    L11:
        if (r1.usage_limit > 0) goto L18;
        if (r1.requested > 0) goto L18;
        if (r1.subscription_expired > 0) goto L18;
        r12 = false;
        goto L20
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    public void loadUsers() {
        if (this.usersLoading == false) goto L6;
        return;
    L6:
        if (this.invite.usage <= this.joinedUsers.size()) goto L8;
        boolean r0 = true;
    L10:
        if (this.invite.subscription_expired <= this.expiredUsers.size()) goto L12;
        boolean r11 = true;
    L13:
        TLRPC.TL_chatInviteExported r1 = this.invite;
        if (r1.request_needed == true) goto L16;
    L18:
        boolean r10 = false;
    L19:
        if (r0 == false) goto L21;
        boolean r8 = false;
        boolean r9 = false;
    L25:
        if (r8 == false) goto L28;
        ArrayList<TLRPC.TL_chatInviteImporter> r02 = this.requestedUsers;
    L27:
        ArrayList<TLRPC.TL_chatInviteImporter> r7 = r02;
        TLRPC.TL_messages_getChatInviteImporters r03 = new TLRPC.TL_messages_getChatInviteImporters();
        r03.flags |= 2;
        r03.link = this.invite.link;
        r03.peer = MessagesController.getInstance(UserConfig.selectedAccount).getInputPeer(-this.chatId);
        r03.requested = r8;
        r03.subscription_expired = r9;
        if (r7.isEmpty() == false) goto L34;
        r03.offset_user = new TLRPC.TL_inputUserEmpty();
    L35:
        this.usersLoading = true;
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r03, new InviteLinkBottomSheet$$ExternalSyntheticLambda1(this, r7, r8, r9, r10, r11));
        return;
    L34:
        TLRPC.TL_chatInviteImporter r12 = r7.get(r7.size() - 1);
        r03.offset_user = MessagesController.getInstance(this.currentAccount).getInputUser(this.users.get(Long.valueOf(r12.user_id)));
        r03.offset_date = r12.date;
        goto L35
    L28:
        if (r9 == false) goto L30;
        r02 = this.expiredUsers;
        goto L27
    L30:
        r02 = this.joinedUsers;
        goto L27
    L21:
        if (r11 == false) goto L23;
        r8 = false;
        r9 = true;
        goto L25
    L23:
        if (r10 == false) goto L37;
        r9 = false;
        r8 = true;
        goto L25
    L37:
        return;
    L16:
        if (r1.requested <= this.requestedUsers.size()) goto L18;
        r10 = true;
        goto L19
    L12:
        r11 = false;
        goto L13
    L8:
        r0 = false;
        goto L10
    }

    public void setCanEdit(boolean r1) {
        this.canEdit = r1;
    }

    public void setInviteDelegate(InviteDelegate r1) {
        this.inviteDelegate = r1;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        this.isNeedReopen = false;
    }

    public void updateColors() {
        TextView r0 = this.titleTextView;
        if (r0 == null) goto L7;
        r0.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        this.titleTextView.setLinkTextColor(Theme.getColor(Theme.key_dialogTextLink));
        this.titleTextView.setHighlightColor(Theme.getColor(Theme.key_dialogLinkSelection));
        if (this.titleVisible == true) goto L7;
        this.titleTextView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
    L7:
        this.listView.setGlowColor(Theme.getColor(Theme.key_dialogScrollGlow));
        this.shadow.setBackgroundColor(Theme.getColor(Theme.key_dialogShadowLine));
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        int r02 = this.listView.getHiddenChildCount();
        int r1 = 0;
        int r2 = 0;
    L9:
        if (r2 >= this.listView.getChildCount()) goto L11;
        updateColorForView(this.listView.getChildAt(r2));
        r2 = r2 + 1;
        goto L9
    L11:
        int r22 = 0;
    L12:
        RecyclerListView r3 = this.listView;
        if (r22 >= r02) goto L15;
        updateColorForView(r3.getHiddenChildAt(r22));
        r22 = r22 + 1;
        goto L12
    L15:
        int r03 = r3.getCachedChildCount();
        int r23 = 0;
    L16:
        RecyclerListView r32 = this.listView;
        if (r23 >= r03) goto L19;
        updateColorForView(r32.getCachedChildAt(r23));
        r23 = r23 + 1;
        goto L16
    L19:
        int r04 = r32.getAttachedScrapChildCount();
    L20:
        if (r1 >= r04) goto L22;
        updateColorForView(this.listView.getAttachedScrapChildAt(r1));
        r1 = r1 + 1;
        goto L20
    L22:
        this.containerView.invalidate();
    }
}
