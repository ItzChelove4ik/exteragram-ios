package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DialogCell;

/* loaded from: classes7.dex */
public class LinkActionView extends LinearLayout {
    private ActionBarPopupWindow actionBarPopupWindow;
    private final AvatarsContainer avatarsContainer;
    private boolean canEdit;
    private final TextView copyView;
    private Delegate delegate;
    BaseFragment fragment;
    private final FrameLayout frameLayout;
    private boolean hideRevokeOption;
    private final boolean isChannel;
    String link;
    TextView linkView;
    private String loadedInviteLink;
    boolean loadingImporters;
    ImageView optionsView;
    private boolean permanent;
    private final float[] point;
    private QRCodeBottomSheet qrCodeBottomSheet;
    private String qrText;
    private final TextView removeView;
    private boolean revoked;
    private final TextView shareView;
    private int usersCount;

    /* renamed from: org.telegram.ui.Components.LinkActionView$1, reason: invalid class name */
    public class AnonymousClass1 extends ShareAlert {
        final /* synthetic */ LinkActionView this$0;

        public AnonymousClass1(LinkActionView r1, Context r2, ArrayList r3, String r4, boolean r5, String r6, boolean r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(LongSparseArray<TLRPC.Dialog> r2, int r3, TLRPC.TL_forumTopic r4, boolean r5) {
            if (r5 == true) goto L5;
            return;
        L5:
            if (r2 != null) goto L7;
        L15:
            String r22 = LocaleController.formatString(R.string.InvLinkToChats, new Object[]{LocaleController.formatPluralString("Chats", r3, new Object[0])});
        L16:
            this.this$0.showBulletin(R.raw.forward, AndroidUtilities.replaceTags(r22));
            return;
        L7:
            if (r2.size() != 1) goto L15;
            long r23 = r2.valueAt(0).id;
            if (r23 != 0) goto L11;
        L14:
            r22 = LocaleController.getString(R.string.InvLinkToSavedMessages);
            goto L16
        L11:
            if (r23 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L14;
            r22 = LocaleController.formatString(R.string.InvLinkToUser, new Object[]{MessagesController.getInstance(this.currentAccount).getPeerName(r23, true)});
            goto L16
        }
    }

    /* renamed from: org.telegram.ui.Components.LinkActionView$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ LinkActionView this$0;
        final /* synthetic */ FrameLayout val$finalContainer;

        public AnonymousClass2(LinkActionView r1, Context r2, FrameLayout r3) {
            this.this$0 = r1;
            this.val$finalContainer = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            r6.drawColor(855638016);
            LinkActionView r0 = this.this$0;
            LinkActionView.m11195$$Nest$mgetPointOnScreen(r0, LinkActionView.m11190$$Nest$fgetframeLayout(r0), this.val$finalContainer, LinkActionView.m11191$$Nest$fgetpoint(this.this$0));
            r6.save();
            float r02 = ((View) LinkActionView.m11190$$Nest$fgetframeLayout(this.this$0).getParent()).getY() + LinkActionView.m11190$$Nest$fgetframeLayout(this.this$0).getY();
            if (r02 >= 1.0f) goto L5;
            r6.clipRect(0.0f, (LinkActionView.m11191$$Nest$fgetpoint(this.this$0)[1] - r02) + 1.0f, getMeasuredWidth(), getMeasuredHeight());
        L5:
            r6.translate(LinkActionView.m11191$$Nest$fgetpoint(this.this$0)[0], LinkActionView.m11191$$Nest$fgetpoint(this.this$0)[1]);
            LinkActionView.m11190$$Nest$fgetframeLayout(this.this$0).draw(r6);
            r6.restore();
        }
    }

    /* renamed from: org.telegram.ui.Components.LinkActionView$3, reason: invalid class name */
    public class AnonymousClass3 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ LinkActionView this$0;
        final /* synthetic */ View val$dimView;

        public AnonymousClass3(LinkActionView r1, View r2) {
            this.this$0 = r1;
            this.val$dimView = r2;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.val$dimView.invalidate();
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.LinkActionView$4, reason: invalid class name */
    public class AnonymousClass4 implements PopupWindow.OnDismissListener {
        final /* synthetic */ LinkActionView this$0;
        final /* synthetic */ View val$dimView;
        final /* synthetic */ FrameLayout val$finalContainer;
        final /* synthetic */ ViewTreeObserver.OnPreDrawListener val$preDrawListener;

        /* renamed from: org.telegram.ui.Components.LinkActionView$4$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (this.this$1.val$dimView.getParent() == null) goto L5;
                AnonymousClass4 r22 = this.this$1;
                r22.val$finalContainer.removeView(r22.val$dimView);
            L5:
                this.this$1.val$finalContainer.getViewTreeObserver().removeOnPreDrawListener(this.this$1.val$preDrawListener);
            }
        }

        public AnonymousClass4(LinkActionView r1, View r2, FrameLayout r3, ViewTreeObserver.OnPreDrawListener r4) {
            this.this$0 = r1;
            this.val$dimView = r2;
            this.val$finalContainer = r3;
            this.val$preDrawListener = r4;
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            LinkActionView.m11193$$Nest$fputactionBarPopupWindow(this.this$0, null);
            this.val$dimView.animate().cancel();
            this.val$dimView.animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass1(this));
        }
    }

    /* renamed from: org.telegram.ui.Components.LinkActionView$5, reason: invalid class name */
    public class AnonymousClass5 implements View.OnClickListener {
        final /* synthetic */ LinkActionView this$0;

        public AnonymousClass5(LinkActionView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            LinkActionView.m11189$$Nest$fgetcopyView(this.this$0).callOnClick();
        }
    }

    /* renamed from: org.telegram.ui.Components.LinkActionView$6, reason: invalid class name */
    public class AnonymousClass6 extends QRCodeBottomSheet {
        final /* synthetic */ LinkActionView this$0;

        public AnonymousClass6(LinkActionView r1, Context r2, String r3, String r4, String r5, boolean r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            LinkActionView.m11194$$Nest$fputqrCodeBottomSheet(this.this$0, null);
        }
    }

    public class AvatarsContainer extends FrameLayout {
        AvatarsImageView avatarsImageView;
        TextView countTextView;
        final /* synthetic */ LinkActionView this$0;

        /* renamed from: org.telegram.ui.Components.LinkActionView$AvatarsContainer$1, reason: invalid class name */
        public class AnonymousClass1 extends AvatarsImageView {
            final /* synthetic */ AvatarsContainer this$1;
            final /* synthetic */ LinkActionView val$this$0;

            public AnonymousClass1(AvatarsContainer r1, Context r2, boolean r3, LinkActionView r4) {
                this.this$1 = r1;
                this.val$this$0 = r4;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.AvatarsImageView, android.view.View
            public void onMeasure(int r2, int r3) {
                int r22 = Math.min(3, LinkActionView.m11192$$Nest$fgetusersCount(this.this$1.this$0));
                if (r22 != 0) goto L5;
                int r23 = 0;
            L6:
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r23), TLObject.FLAG_30), r3);
                return;
            L5:
                r23 = ((r22 - 1) * 20) + 32;
                goto L6
            }
        }

        public AvatarsContainer(LinkActionView r6, Context r7) {
            this.this$0 = r6;
            super(r7);
            this.avatarsImageView = new AnonymousClass1(this, r7, false, r6);
            LinearLayout r62 = new LinearLayout(r7);
            r62.setOrientation(0);
            addView(r62, LayoutHelper.createFrame(-2, -1, 1));
            TextView r4 = new TextView(r7);
            this.countTextView = r4;
            r4.setTextSize(1, 14.0f);
            this.countTextView.setTypeface(AndroidUtilities.bold());
            r62.addView(this.avatarsImageView, LayoutHelper.createLinear(-2, -1));
            r62.addView(this.countTextView, LayoutHelper.createLinear(-2, -2, 16));
            setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
            this.avatarsImageView.commitTransition(false);
        }
    }

    public interface Delegate {
        default void editLink() {
        }

        default void removeLink() {
        }

        void revokeLink();

        default void showUsersForPermanentLink() {
        }
    }

    public static /* synthetic */ void $r8$lambda$2byRlqHYyzzA8PDQR3g4j3dT1Lo(LinkActionView r0, KeyEvent r1) {
        r0.lambda$new$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$A3pVd1nzHOVNF5gTXhOM7V_SuQg(LinkActionView r0, View r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$EwPo92jU7SuNZEpLZ0O1pZQZqzU(LinkActionView r0, TLRPC.TL_chatInviteExported r1, TLRPC.TL_error r2, TLObject r3) {
        r0.lambda$loadUsers$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$IFnJVGbsur48D_NgrgFcwJgn_5g(LinkActionView r0, AlertDialog r1, int r2) {
        r0.lambda$new$3(r1, r2);
    }

    /* renamed from: $r8$lambda$NqF0augmVZI5MZ9IVsIJAt-23iQ, reason: not valid java name */
    public static /* synthetic */ void m11186$r8$lambda$NqF0augmVZI5MZ9IVsIJAt23iQ(LinkActionView r0, BaseFragment r1, View r2) {
        r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$O_zPl3DEfBqISAh4D_ilKUeYxlQ(LinkActionView r0, Context r1, BottomSheet r2, BaseFragment r3, View r4) {
        r0.lambda$new$9(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$bBLf82l6x9teyu0geGU3DML2tcs(LinkActionView r0, View r1) {
        r0.lambda$new$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$doPtR2WFHBnpLsH2HJBFc5my9rg(LinkActionView r0, View r1) {
        r0.lambda$new$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$fBnxKfFCx2kwm13opynmCSGH4bc(LinkActionView r0, BottomSheet r1, BaseFragment r2, View r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$mmTLY_jXutimN52KgWa-9tM-VYg, reason: not valid java name */
    public static /* synthetic */ void m11187$r8$lambda$mmTLY_jXutimN52KgWa9tMVYg(LinkActionView r0, BaseFragment r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: $r8$lambda$n0BzxJ-5T34ly5eid7j2w8r7WwA, reason: not valid java name */
    public static /* synthetic */ void m11188$r8$lambda$n0BzxJ5T34ly5eid7j2w8r7WwA(LinkActionView r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$n8jRFUCg0m2vxS4Yj3QcIwH7hT4(LinkActionView r0, TLRPC.TL_chatInviteExported r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadUsers$12(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$wbGWdTPxla55EUrfMFSvz_rECYQ(LinkActionView r0, AlertDialog r1, int r2) {
        r0.lambda$revokeLink$10(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcopyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m11189$$Nest$fgetcopyView(LinkActionView r0) {
        return r0.copyView;
    }

    /* renamed from: -$$Nest$fgetframeLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11190$$Nest$fgetframeLayout(LinkActionView r0) {
        return r0.frameLayout;
    }

    /* renamed from: -$$Nest$fgetpoint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m11191$$Nest$fgetpoint(LinkActionView r0) {
        return r0.point;
    }

    /* renamed from: -$$Nest$fgetusersCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11192$$Nest$fgetusersCount(LinkActionView r0) {
        return r0.usersCount;
    }

    /* renamed from: -$$Nest$fputactionBarPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11193$$Nest$fputactionBarPopupWindow(LinkActionView r0, ActionBarPopupWindow r1) {
        r0.actionBarPopupWindow = r1;
    }

    /* renamed from: -$$Nest$fputqrCodeBottomSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11194$$Nest$fputqrCodeBottomSheet(LinkActionView r0, QRCodeBottomSheet r1) {
        r0.qrCodeBottomSheet = r1;
    }

    /* renamed from: -$$Nest$mgetPointOnScreen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11195$$Nest$mgetPointOnScreen(LinkActionView r0, FrameLayout r1, FrameLayout r2, float[] r3) {
        r0.getPointOnScreen(r1, r2, r3);
    }

    public LinkActionView(Context r27, BaseFragment r28, BottomSheet r29, long r30, boolean r32, boolean r33) {
        super(r27);
        this.canEdit = true;
        this.point = new float[2];
        this.fragment = r28;
        this.permanent = r32;
        this.isChannel = r33;
        setOrientation(1);
        FrameLayout r7 = new FrameLayout(r27);
        this.frameLayout = r7;
        TextView r8 = new TextView(r27);
        this.linkView = r8;
        r8.setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(40.0f), AndroidUtilities.dp(13.0f));
        this.linkView.setTextSize(1, 16.0f);
        this.linkView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        this.linkView.setSingleLine(true);
        r7.addView(this.linkView);
        ImageView r82 = new ImageView(r27);
        this.optionsView = r82;
        r82.setImageDrawable(ContextCompat.getDrawable(r27, R.drawable.ic_ab_other));
        this.optionsView.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        this.optionsView.setScaleType(ImageView.ScaleType.CENTER);
        r7.addView(this.optionsView, LayoutHelper.createFrame(40, 48, 21));
        addView(r7, LayoutHelper.createLinear(-1, -2, 0, 4, 0, 4, 0));
        LinearLayout r83 = new LinearLayout(r27);
        r83.setOrientation(0);
        TextView r11 = new TextView(r27);
        this.copyView = r11;
        ScaleStateListAnimator.apply(r11, 0.025f, 1.2f);
        r11.setGravity(17);
        SpannableStringBuilder r9 = new SpannableStringBuilder();
        r9.append("..").setSpan(new ColoredImageSpan(ContextCompat.getDrawable(r27, R.drawable.msg_copy_filled)), 0, 1, 0);
        r9.setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(6.0f)), 1, 2, 0);
        r9.append(LocaleController.getString(R.string.LinkActionCopy));
        r11.setText(r9);
        r11.setContentDescription(LocaleController.getString(R.string.LinkActionCopy));
        r11.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        r11.setTextSize(1, 14.0f);
        r11.setTypeface(AndroidUtilities.bold());
        r11.setSingleLine(true);
        r83.addView(r11, LayoutHelper.createLinear(0, 42, 1.0f, 0, 4, 0, 4, 0));
        TextView r15 = new TextView(r27);
        this.shareView = r15;
        ScaleStateListAnimator.apply(r15, 0.025f, 1.2f);
        r15.setGravity(17);
        SpannableStringBuilder r92 = new SpannableStringBuilder();
        r92.append("..").setSpan(new ColoredImageSpan(ContextCompat.getDrawable(r27, R.drawable.msg_share_filled)), 0, 1, 0);
        r92.setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(6.0f)), 1, 2, 0);
        r92.append(LocaleController.getString(R.string.LinkActionShare));
        r15.setText(r92);
        r15.setContentDescription(LocaleController.getString(R.string.LinkActionShare));
        r15.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        r15.setTextSize(1, 14.0f);
        r15.setTypeface(AndroidUtilities.bold());
        r15.setSingleLine(true);
        r83.addView(r15, LayoutHelper.createLinear(0, 42, 1.0f, 4, 0, 4, 0));
        TextView r6 = new TextView(r27);
        this.removeView = r6;
        ScaleStateListAnimator.apply(r6, 0.025f, 1.2f);
        r6.setGravity(17);
        SpannableStringBuilder r93 = new SpannableStringBuilder();
        r93.append("..").setSpan(new ColoredImageSpan(ContextCompat.getDrawable(r27, R.drawable.msg_delete_filled)), 0, 1, 0);
        r93.setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(8.0f)), 1, 2, 0);
        r93.append(LocaleController.getString(R.string.DeleteLink));
        r93.append(".").setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(5.0f)), r93.length() - 1, r93.length(), 0);
        r6.setText(r93);
        r6.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        r6.setTextSize(1, 14.0f);
        r6.setTypeface(AndroidUtilities.bold());
        r6.setSingleLine(true);
        r83.addView(r6, LayoutHelper.createLinear(0, 42, 1.0f, 4, 0, 4, 0));
        r83.setClickable(true);
        r6.setVisibility(8);
        addView(r83, LayoutHelper.createLinear(-1, -2, 0.0f, 12.0f, 0.0f, 0.0f));
        AvatarsContainer r5 = new AvatarsContainer(this, r27);
        this.avatarsContainer = r5;
        r5.avatarsImageView.setAvatarsTextSize(AndroidUtilities.dp(18.0f));
        addView(r5, LayoutHelper.createLinear(-1, 44, 0.0f, 12.0f, 0.0f, 0.0f));
        r11.setOnClickListener(new LinkActionView$$ExternalSyntheticLambda0(this, r29, r28));
        if (r32 == false) goto L5;
        r5.setOnClickListener(new LinkActionView$$ExternalSyntheticLambda1(this));
    L5:
        r15.setOnClickListener(new LinkActionView$$ExternalSyntheticLambda2(this, r28));
        r6.setOnClickListener(new LinkActionView$$ExternalSyntheticLambda3(this, r28));
        this.optionsView.setOnClickListener(new LinkActionView$$ExternalSyntheticLambda4(this, r27, r29, r28));
        r7.setOnClickListener(new AnonymousClass5(this));
        updateColors();
    }

    private void getPointOnScreen(FrameLayout r3, FrameLayout r4, float[] r5) {
        float r2 = 0.0f;
        View r0 = r3;
        float r32 = 0.0f;
    L3:
        if (r0 == r4) goto L13;
        r2 = r2 + r0.getY();
        r32 = r32 + r0.getX();
        if ((r0 instanceof ScrollView) == false) goto L8;
        r2 = r2 - r0.getScrollY();
    L8:
        if ((r0.getParent() instanceof View) == false) goto L13;
        r0 = (View) r0.getParent();
        if ((r0 instanceof ViewGroup) == true) goto L3;
        return;
    L13:
        r5[0] = r32 - r4.getPaddingLeft();
        r5[1] = r2 - r4.getPaddingTop();
    }

    private /* synthetic */ void lambda$loadUsers$11(TLRPC.TL_chatInviteExported r3, TLRPC.TL_error r4, TLObject r5) {
        int r0 = 0;
        this.loadingImporters = false;
        this.loadedInviteLink = r3.link;
        if (r4 != null) goto L14;
        TLRPC.TL_messages_chatInviteImporters r52 = (TLRPC.TL_messages_chatInviteImporters) r5;
        if (r3.importers != null) goto L7;
        r3.importers = new ArrayList(3);
    L7:
        r3.importers.clear();
    L9:
        if (r0 >= r52.users.size()) goto L11;
        r3.importers.addAll(r52.users);
        r0 = r0 + 1;
        goto L9
    L11:
        setUsers(r3.usage, r3.importers, true);
        return;
    }

    private /* synthetic */ void lambda$loadUsers$12(TLRPC.TL_chatInviteExported r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new LinkActionView$$ExternalSyntheticLambda12(this, r2, r4, r3));
    }

    private /* synthetic */ void lambda$new$0(BottomSheet r2, BaseFragment r3, View r4) {
    L13:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (this.link != null) goto L5;
        return;
    L5:
        ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", this.link));     // Catch: Exception -> L13
        if (r2 != null) goto L8;
    L11:
        BulletinFactory.createCopyLinkBulletin(r3).show();     // Catch: Exception -> L13
        return;
    L8:
        if (r2.getContainer() == null) goto L11;
        BulletinFactory.createCopyLinkBulletin(r2.getContainer()).show();     // Catch: Exception -> L13
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        this.delegate.showUsersForPermanentLink();
    }

    private /* synthetic */ void lambda$new$2(BaseFragment r10, View r11) {
    L7:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (this.link != null) goto L5;
        return;
    L5:
        Context r2 = getContext();     // Catch: Exception -> L7
        String r4 = this.link;     // Catch: Exception -> L7
        r10.showDialog(new AnonymousClass1(this, r2, null, r4, false, r4, false, r10.getResourceProvider()));     // Catch: Exception -> L7
    }

    private /* synthetic */ void lambda$new$3(AlertDialog r1, int r2) {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.removeLink();
        return;
    }

    private /* synthetic */ void lambda$new$4(BaseFragment r3, View r4) {
        AlertDialog.Builder r42 = new AlertDialog.Builder(r3.getParentActivity());
        r42.setTitle(LocaleController.getString(R.string.DeleteLink));
        r42.setMessage(LocaleController.getString(R.string.DeleteLinkHelp));
        r42.setPositiveButton(LocaleController.getString(R.string.Delete), new LinkActionView$$ExternalSyntheticLambda10(this));
        r42.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r3.showDialog(r42.create());
    }

    private /* synthetic */ void lambda$new$5(View r1) {
        ActionBarPopupWindow r12 = this.actionBarPopupWindow;
        if (r12 == null) goto L5;
        r12.dismiss();
    L5:
        this.delegate.editLink();
    }

    private /* synthetic */ void lambda$new$6(View r1) {
        showQrCode();
    }

    private /* synthetic */ void lambda$new$7(View r1) {
        ActionBarPopupWindow r12 = this.actionBarPopupWindow;
        if (r12 == null) goto L5;
        r12.dismiss();
    L5:
        revokeLink();
    }

    private /* synthetic */ void lambda$new$8(KeyEvent r3) {
        if (r3.getKeyCode() == 4) goto L5;
        return;
    L5:
        if (r3.getRepeatCount() == 0) goto L7;
        return;
    L7:
        if (this.actionBarPopupWindow.isShowing() == false) goto L12;
        this.actionBarPopupWindow.dismiss(true);
        return;
    }

    private /* synthetic */ void lambda$new$9(Context r8, BottomSheet r9, BaseFragment r10, View r11) {
        if (this.actionBarPopupWindow != null) goto L23;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r112 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r8);
        if (this.permanent == false) goto L8;
    L10:
        ActionBarMenuSubItem r0 = new ActionBarMenuSubItem(r8, true, false);
        r0.setTextAndIcon(LocaleController.getString(R.string.GetQRCode), R.drawable.msg_qrcode);
        r112.addView(r0, LayoutHelper.createLinear(-1, 48));
        r0.setOnClickListener(new LinkActionView$$ExternalSyntheticLambda7(this));
        if (this.hideRevokeOption == true) goto L13;
        ActionBarMenuSubItem r02 = new ActionBarMenuSubItem(r8, false, true);
        r02.setTextAndIcon(LocaleController.getString(R.string.RevokeLink), R.drawable.msg_delete);
        int r5 = Theme.key_text_RedRegular;
        r02.setColors(Theme.getColor(r5), Theme.getColor(r5));
        r02.setOnClickListener(new LinkActionView$$ExternalSyntheticLambda8(this));
        r112.addView(r02, LayoutHelper.createLinear(-1, 48));
    L13:
        if (r9 != null) goto L15;
        FrameLayout r92 = r10.getParentLayout().getOverlayContainerView();
    L16:
        if (r92 == null) goto L22;
        getPointOnScreen(this.frameLayout, r92, this.point);
        float r102 = this.point[1];
        AnonymousClass2 r03 = new AnonymousClass2(this, r8, r92);
        AnonymousClass3 r82 = new AnonymousClass3(this, r03);
        r92.getViewTreeObserver().addOnPreDrawListener(r82);
        r92.addView(r03, LayoutHelper.createFrame(-1, -1.0f));
        float r1 = 0.0f;
        r03.setAlpha(0.0f);
        r03.animate().alpha(1.0f).setDuration(150);
        r112.measure(View.MeasureSpec.makeMeasureSpec(r92.getMeasuredWidth(), 0), View.MeasureSpec.makeMeasureSpec(r92.getMeasuredHeight(), 0));
        ActionBarPopupWindow r2 = new ActionBarPopupWindow(r112, -2, -2);
        this.actionBarPopupWindow = r2;
        r2.setOnDismissListener(new AnonymousClass4(this, r03, r92, r82));
        this.actionBarPopupWindow.setOutsideTouchable(true);
        this.actionBarPopupWindow.setFocusable(true);
        this.actionBarPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.actionBarPopupWindow.setAnimationStyle(R.style.PopupContextAnimation);
        this.actionBarPopupWindow.setInputMethodMode(2);
        this.actionBarPopupWindow.setSoftInputMode(0);
        r112.setDispatchKeyEventListener(new LinkActionView$$ExternalSyntheticLambda9(this));
        if (AndroidUtilities.isTablet() == false) goto L20;
        r102 = r102 + r92.getPaddingTop();
        r1 = 0.0f - r92.getPaddingLeft();
    L20:
        this.actionBarPopupWindow.showAtLocation(r92, 0, (int) ((((r92.getMeasuredWidth() - r112.getMeasuredWidth()) - AndroidUtilities.dp(16.0f)) + r92.getX()) + r1), (int) ((r102 + this.frameLayout.getMeasuredHeight()) + r92.getY()));
        return;
    L22:
        return;
    L15:
        r92 = r9.getContainer();
        goto L16
    L8:
        if (this.canEdit == false) goto L10;
        ActionBarMenuSubItem r04 = new ActionBarMenuSubItem(r8, true, false);
        r04.setTextAndIcon(LocaleController.getString(R.string.Edit), R.drawable.msg_edit);
        r112.addView(r04, LayoutHelper.createLinear(-1, 48));
        r04.setOnClickListener(new LinkActionView$$ExternalSyntheticLambda6(this));
        goto L10
    }

    private /* synthetic */ void lambda$revokeLink$10(AlertDialog r1, int r2) {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.revokeLink();
        return;
    }

    private void revokeLink() {
        if (this.fragment.getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(this.fragment.getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.RevokeLink));
        r0.setMessage(LocaleController.getString(R.string.RevokeAlert));
        r0.setPositiveButton(LocaleController.getString(R.string.RevokeButton), new LinkActionView$$ExternalSyntheticLambda11(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        TextView r3 = (TextView) r0.create().getButton(-1);
        if (r3 == null) goto L8;
        r3.setTextColor(Theme.getColor(Theme.key_text_RedBold));
    L8:
        r0.show();
    }

    private void showQrCode() {
        String r0 = this.link;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        Context r3 = getContext();
        String r4 = LocaleController.getString(R.string.InviteByQRCode);
        String r5 = this.link;
        String r2 = this.qrText;
        if (r2 != null) goto L18;
        if (this.isChannel == false) goto L17;
        if (r02 == false) goto L14;
        int r03 = R.string.QRCodeLinkHelpChannelDirect;
    L15:
        r2 = LocaleController.getString(r03);
        goto L18
    L14:
        r03 = R.string.QRCodeLinkHelpChannel;
        goto L15
    L17:
        r03 = R.string.QRCodeLinkHelpGroup;
    L18:
        AnonymousClass6 r1 = new AnonymousClass6(this, r3, r4, r5, r2, false);
        this.qrCodeBottomSheet = r1;
        r1.setCenterAnimation(R.raw.qr_code_logo);
        this.qrCodeBottomSheet.show();
        ActionBarPopupWindow r8 = this.actionBarPopupWindow;
        if (r8 == null) goto L22;
        r8.dismiss();
        return;
    L22:
        return;
    L5:
        if (r0.endsWith("?direct") == false) goto L7;
        r02 = true;
        goto L8
    }

    public void hideOptions() {
        this.optionsView.setVisibility(8);
        this.linkView.setGravity(17);
        this.removeView.setVisibility(8);
        this.avatarsContainer.setVisibility(8);
    }

    public void hideRevokeOption(boolean r2) {
        if (this.hideRevokeOption == r2) goto L6;
        this.hideRevokeOption = r2;
        this.optionsView.setVisibility(0);
        ImageView r1 = this.optionsView;
        r1.setImageDrawable(ContextCompat.getDrawable(r1.getContext(), R.drawable.ic_ab_other));
        return;
    }

    public void loadUsers(TLRPC.TL_chatInviteExported r4, long r5) {
        if (r4 != null) goto L7;
        setUsers(0, null, false);
        return;
    L7:
        if (TextUtils.equals(this.loadedInviteLink, r4.link) == true) goto L19;
        setUsers(r4.usage, r4.importers, false);
        if (r4.usage > 0) goto L11;
        return;
    L11:
        if (r4.importers == null) goto L13;
        return;
    L13:
        if (this.loadingImporters == true) goto L22;
        TLRPC.TL_messages_getChatInviteImporters r0 = new TLRPC.TL_messages_getChatInviteImporters();
        String r1 = r4.link;
        if (r1 == null) goto L17;
        r0.flags |= 2;
        r0.link = r1;
    L17:
        r0.peer = MessagesController.getInstance(UserConfig.selectedAccount).getInputPeer(-r5);
        r0.offset_user = new TLRPC.TL_inputUserEmpty();
        r0.limit = Math.min(r4.usage, 3);
        this.loadingImporters = true;
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r0, new LinkActionView$$ExternalSyntheticLambda5(this, r4));
        return;
    L22:
        return;
    }

    public void setCanEdit(boolean r1) {
        this.canEdit = r1;
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public void setLink(String r2) {
        this.link = r2;
        if (r2 != null) goto L6;
        this.linkView.setText(LocaleController.getString(R.string.Loading));
        return;
    L6:
        boolean r0 = r2.startsWith("https://");
        TextView r1 = this.linkView;
        if (r0 == false) goto L10;
        r1.setText(r2.substring(8));
        return;
    L10:
        r1.setText(r2);
    }

    public void setPermanent(boolean r1) {
        this.permanent = r1;
    }

    public void setQrText(String r1) {
        this.qrText = r1;
    }

    public void setRevoke(boolean r4) {
        this.revoked = r4;
        ImageView r0 = this.optionsView;
        if (r4 == false) goto L6;
        r0.setVisibility(8);
        this.shareView.setVisibility(8);
        this.copyView.setVisibility(8);
        this.removeView.setVisibility(0);
        return;
    L6:
        r0.setVisibility(0);
        this.shareView.setVisibility(0);
        this.copyView.setVisibility(0);
        this.removeView.setVisibility(8);
    }

    public void setUsers(int r2, ArrayList<TLRPC.User> r3) {
        setUsers(r2, r3, false);
    }

    public void setUsers(int r6, ArrayList<TLRPC.User> r7, boolean r8) {
        this.usersCount = r6;
        AvatarsContainer r0 = this.avatarsContainer;
        int r3 = 0;
        if (r6 != 0) goto L5;
        r0.setVisibility(8);
        setPadding(AndroidUtilities.dp(19.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(19.0f), AndroidUtilities.dp(18.0f));
    L6:
        if (r7 == null) goto L14;
        int r02 = 0;
    L9:
        if (r02 >= r7.size()) goto L11;
        MessagesController.getInstance(UserConfig.selectedAccount).putUser(r7.get(r02), false);
        r02 = r02 + 1;
        goto L9
    L11:
        int r62 = Math.min(3, Math.min(r6, r7.size()));
        this.avatarsContainer.avatarsImageView.setCount(r62);
    L12:
        if (r3 >= r62) goto L15;
        this.avatarsContainer.avatarsImageView.setObject(r3, UserConfig.selectedAccount, r7.get(r3));
        r3 = r3 + 1;
    L15:
        this.avatarsContainer.avatarsImageView.commitTransition(r8);
        return;
    L14:
        this.avatarsContainer.avatarsImageView.setCount(0);
        goto L15
    L5:
        r0.setVisibility(0);
        setPadding(AndroidUtilities.dp(19.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(19.0f), AndroidUtilities.dp(10.0f));
        this.avatarsContainer.countTextView.setText(LocaleController.formatPluralString("PeopleJoined", r6, new Object[0]));
        this.avatarsContainer.requestLayout();
        goto L6
    }

    public void showBulletin(int r1, CharSequence r2) {
        Bulletin r0 = BulletinFactory.of(this.fragment).createSimpleBulletin(r1, r2);
        r0.hideAfterBottomSheet = false;
        r0.show(true);
    }

    public void updateColors() {
        TextView r0 = this.copyView;
        int r1 = Theme.key_featuredStickers_buttonText;
        r0.setTextColor(Theme.getColor(r1));
        this.shareView.setTextColor(Theme.getColor(r1));
        this.removeView.setTextColor(Theme.getColor(r1));
        TextView r02 = this.copyView;
        int r2 = AndroidUtilities.dp(21.0f);
        int r3 = Theme.key_featuredStickers_addButton;
        int r4 = Theme.getColor(r3);
        int r5 = Theme.key_featuredStickers_addButtonPressed;
        r02.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r2, r4, Theme.getColor(r5)));
        this.shareView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(21.0f), Theme.getColor(r3), Theme.getColor(r5)));
        this.removeView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(21.0f), Theme.getColor(Theme.key_chat_attachAudioBackground), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhite), 120)));
        this.frameLayout.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(21.0f), Theme.getColor(Theme.key_graySection), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_listSelector), 76)));
        this.linkView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.optionsView.setColorFilter(Theme.getColor(Theme.key_dialogTextGray3));
        TextView r03 = this.avatarsContainer.countTextView;
        int r12 = Theme.key_windowBackgroundWhiteBlueText;
        r03.setTextColor(Theme.getColor(r12));
        this.avatarsContainer.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(r12), 76)));
        QRCodeBottomSheet r7 = this.qrCodeBottomSheet;
        if (r7 == null) goto L6;
        r7.updateColors();
        return;
    }
}
