package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes7.dex */
public abstract class SenderSelectPopup extends ActionBarPopupWindow {
    private FrameLayout bulletinContainer;
    private Runnable bulletinHideCallback;
    private List<Bulletin> bulletins;
    private boolean clicked;
    private final int currentAccount;
    private TLRPC.Peer defPeer;
    private boolean dismissed;
    private View headerShadow;
    public TextView headerText;
    private boolean isDismissingByBulletin;
    private Boolean isHeaderShadowVisible;
    private LinearLayoutManager layoutManager;
    private int popupX;
    private int popupY;
    public LinearLayout recyclerContainer;
    private RecyclerListView recyclerView;
    protected boolean runningCustomSprings;
    private FrameLayout scrimPopupContainerLayout;
    private TLRPC.TL_channels_sendAsPeers sendAsPeers;
    protected List<SpringAnimation> springAnimations;

    /* renamed from: org.telegram.ui.Components.SenderSelectPopup$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayout {
        final /* synthetic */ SenderSelectPopup this$0;
        final /* synthetic */ int val$maxHeight;
        final /* synthetic */ int val$maxWidth;

        public AnonymousClass1(SenderSelectPopup r1, Context r2, int r3, int r4) {
            this.this$0 = r1;
            this.val$maxWidth = r3;
            this.val$maxHeight = r4;
            super(r2);
        }

        @Override // android.view.View
        public int getSuggestedMinimumWidth() {
            return AndroidUtilities.dp(260.0f);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(r3), this.val$maxWidth), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(r4), this.val$maxHeight), View.MeasureSpec.getMode(r4)));
        }
    }

    /* renamed from: org.telegram.ui.Components.SenderSelectPopup$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ SenderSelectPopup this$0;
        final /* synthetic */ TLRPC.Peer val$defPeer;
        final /* synthetic */ int val$maxWidth;
        final /* synthetic */ MessagesController val$messagesController;
        final /* synthetic */ List val$peers;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass2(SenderSelectPopup r1, Theme.ResourcesProvider r2, List r3, MessagesController r4, int r5, TLRPC.Peer r6) {
            this.this$0 = r1;
            this.val$resourcesProvider = r2;
            this.val$peers = r3;
            this.val$messagesController = r4;
            this.val$maxWidth = r5;
            this.val$defPeer = r6;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.val$peers.size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r11, int r12) {
            SenderView r112 = (SenderView) r11.itemView;
            TLRPC.TL_sendAsPeer r0 = (TLRPC.TL_sendAsPeer) this.val$peers.get(r12);
            TLRPC.Peer r1 = r0.peer;
            long r2 = r1.channel_id;
            if (r2 == 0) goto L5;
            long r22 = -r2;
        L7:
            if (r22 != 0) goto L11;
            long r6 = r1.user_id;
            if (r6 == 0) goto L11;
            r22 = r6;
        L11:
            MessagesController r5 = this.val$messagesController;
            boolean r62 = true;
            if (r22 >= 0) goto L35;
            TLRPC.Chat r23 = r5.getChat(Long.valueOf(-r22));
            if (r23 != null) goto L16;
        L26:
            SimpleAvatarView r113 = r112.avatar;
            TLRPC.Peer r10 = this.val$defPeer;
            if (r10 != null) goto L29;
            if (r12 == 0) goto L33;
        L31:
            r62 = false;
        L33:
            r113.setSelected(r62, false);
            return;
        L29:
            if (r10.channel_id != r1.channel_id) goto L31;
        L16:
            if (r0.premium_required == false) goto L18;
            SpannableString r02 = new SpannableString(TextUtils.ellipsize(r23.title, r112.title.getPaint(), this.val$maxWidth - AndroidUtilities.dp(100.0f), TextUtils.TruncateAt.END) + " d");
            ColoredImageSpan r3 = new ColoredImageSpan(R.drawable.msg_mini_premiumlock);
            r3.setTopOffset(1);
            r3.setSize(AndroidUtilities.dp(14.0f));
            r3.setColorKey(Theme.key_windowBackgroundWhiteGrayText5);
            r02.setSpan(r3, r02.length() - 1, r02.length(), 33);
            r112.title.setEllipsize(null);
            r112.title.setText(r02);
        L19:
            TextView r03 = r112.subtitle;
            if (ChatObject.isChannel(r23) == true) goto L22;
        L24:
            String r32 = "Members";
        L25:
            r03.setText(LocaleController.formatPluralString(r32, r23.participants_count, new Object[0]));
            r112.avatar.setAvatar(r23);
            goto L26
        L22:
            if (r23.megagroup == true) goto L24;
            r32 = "Subscribers";
            goto L25
        L18:
            r112.title.setEllipsize(TextUtils.TruncateAt.END);
            r112.title.setText(r23.title);
            goto L19
        L35:
            TLRPC.User r04 = r5.getUser(Long.valueOf(r22));
            if (r04 == null) goto L38;
            r112.title.setText(UserObject.getUserName(r04));
            r112.subtitle.setText(LocaleController.getString(R.string.VoipGroupPersonalAccount));
            r112.avatar.setAvatar(r04);
        L38:
            SimpleAvatarView r114 = r112.avatar;
            TLRPC.Peer r102 = this.val$defPeer;
            if (r102 != null) goto L41;
            if (r12 == 0) goto L45;
        L43:
            r62 = false;
        L45:
            r114.setSelected(r62, false);
            return;
        L41:
            if (r102.user_id != r1.user_id) goto L43;
        L5:
            r22 = 0;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            return new RecyclerListView.Holder(new SenderView(r2.getContext(), this.val$resourcesProvider));
        }
    }

    /* renamed from: org.telegram.ui.Components.SenderSelectPopup$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SenderSelectPopup this$0;

        public AnonymousClass3(SenderSelectPopup r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r3, int r4, int r5) {
            if (SenderSelectPopup.m12630$$Nest$fgetlayoutManager(this.this$0).findFirstCompletelyVisibleItemPosition() == 0) goto L5;
            boolean r32 = true;
        L7:
            if (SenderSelectPopup.m12629$$Nest$fgetisHeaderShadowVisible(this.this$0) != null) goto L9;
        L12:
            SenderSelectPopup.m12627$$Nest$fgetheaderShadow(this.this$0).animate().cancel();
            ViewPropertyAnimator r42 = SenderSelectPopup.m12627$$Nest$fgetheaderShadow(this.this$0).animate();
            if (r32 == false) goto L15;
            float r52 = 1.0f;
        L16:
            r42.alpha(r52).setDuration(150).start();
            SenderSelectPopup.m12634$$Nest$fputisHeaderShadowVisible(this.this$0, Boolean.valueOf(r32));
            return;
        L15:
            r52 = 0.0f;
            goto L16
        L9:
            if (r32 != SenderSelectPopup.m12629$$Nest$fgetisHeaderShadowVisible(this.this$0).booleanValue()) goto L12;
            return;
        L5:
            r32 = false;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.SenderSelectPopup$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayout {
        final /* synthetic */ SenderSelectPopup this$0;

        public AnonymousClass4(SenderSelectPopup r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r9) {
            View r0 = this.this$0.getContentView();
            int[] r2 = new int[2];
            r0.getLocationInWindow(r2);
            r2[0] = r2[0] + SenderSelectPopup.m12631$$Nest$fgetpopupX(this.this$0);
            r2[1] = r2[1] + SenderSelectPopup.m12632$$Nest$fgetpopupY(this.this$0);
            getLocationInWindow(new int[2]);
            if (r9.getAction() != 0) goto L7;
            if (r9.getX() > r2[0]) goto L7;
        L16:
            if (SenderSelectPopup.m12626$$Nest$fgetdismissed(this.this$0) == false) goto L18;
        L21:
            return true;
        L18:
            if (SenderSelectPopup.m12628$$Nest$fgetisDismissingByBulletin(this.this$0) == true) goto L21;
            SenderSelectPopup.m12633$$Nest$fputisDismissingByBulletin(this.this$0, true);
            this.this$0.startDismissAnimation(new SpringAnimation[0]);
        L7:
            if (r9.getX() >= (r2[0] + r0.getWidth())) goto L16;
            if (r9.getY() <= r2[1]) goto L16;
            if (r9.getY() >= (r2[1] + r0.getHeight())) goto L16;
            r9.offsetLocation(r1[0] - r2[0], (AndroidUtilities.statusBarHeight + r1[1]) - r2[1]);
            return r0.dispatchTouchEvent(r9);
        }
    }

    /* renamed from: org.telegram.ui.Components.SenderSelectPopup$5, reason: invalid class name */
    public class AnonymousClass5 implements Bulletin.Layout.Callback {
        final /* synthetic */ SenderSelectPopup this$0;
        final /* synthetic */ Bulletin val$bulletin;

        public AnonymousClass5(SenderSelectPopup r1, Bulletin r2) {
            this.this$0 = r1;
            this.val$bulletin = r2;
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout.Callback
        public void onHide(Bulletin.Layout r1) {
            SenderSelectPopup.m12625$$Nest$fgetbulletins(this.this$0).remove(this.val$bulletin);
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout.Callback
        public void onShow(Bulletin.Layout r1) {
            SenderSelectPopup.m12625$$Nest$fgetbulletins(this.this$0).add(this.val$bulletin);
        }
    }

    /* renamed from: org.telegram.ui.Components.SenderSelectPopup$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ SenderSelectPopup this$0;
        final /* synthetic */ WindowManager val$windowManager;

        public AnonymousClass6(SenderSelectPopup r1, WindowManager r2) {
            this.this$0 = r1;
            this.val$windowManager = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$windowManager.removeViewImmediate(SenderSelectPopup.m12623$$Nest$fgetbulletinContainer(this.this$0));     // Catch: Exception -> L7
        L4:
            if (SenderSelectPopup.m12624$$Nest$fgetbulletinHideCallback(this.this$0) == null) goto L10;
            AndroidUtilities.cancelRunOnUIThread(SenderSelectPopup.m12624$$Nest$fgetbulletinHideCallback(this.this$0));
            return;
        }
    }

    public class BackButtonFrameLayout extends FrameLayout {
        final /* synthetic */ SenderSelectPopup this$0;

        public BackButtonFrameLayout(SenderSelectPopup r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r3) {
            if (r3.getKeyCode() != 4) goto L10;
            if (r3.getRepeatCount() != 0) goto L10;
            if (this.this$0.isShowing() == false) goto L10;
            this.this$0.dismiss();
        L10:
            return super.dispatchKeyEvent(r3);
        }
    }

    public interface OnSelectCallback {
        void onPeerSelected(RecyclerView r1, SenderView r2, TLRPC.Peer r3);
    }

    public static final class SenderView extends LinearLayout {
        public final SimpleAvatarView avatar;
        public final TextView subtitle;
        public final TextView title;

        public SenderView(Context r11, Theme.ResourcesProvider r12) {
            super(r11);
            setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            setOrientation(0);
            setGravity(16);
            int r1 = AndroidUtilities.dp(14.0f);
            int r2 = r1 / 2;
            setPadding(r1, r2, r1, r2);
            SimpleAvatarView r13 = new SimpleAvatarView(r11);
            this.avatar = r13;
            r13.setAvatarCorners(ExteraConfig.getAvatarCorners(40.0f));
            addView(r13, LayoutHelper.createFrame(40, 40.0f));
            LinearLayout r14 = new LinearLayout(r11);
            r14.setOrientation(1);
            addView(r14, LayoutHelper.createLinear(0, -1, 1.0f, 12, 0, 0, 0));
            TextView r3 = new TextView(r11);
            this.title = r3;
            int r4 = Theme.key_actionBarDefaultSubmenuItem;
            r3.setTextColor(Theme.getColor(r4, r12));
            r3.setTextSize(1, 16.0f);
            r3.setTag(r3);
            r3.setMaxLines(1);
            r3.setTypeface(AndroidUtilities.bold());
            r14.addView(r3);
            TextView r32 = new TextView(r11);
            this.subtitle = r32;
            r32.setTextColor(ColorUtils.setAlphaComponent(Theme.getColor(r4, r12), 102));
            r32.setTextSize(1, 14.0f);
            r32.setTag(r32);
            r32.setMaxLines(1);
            r32.setEllipsize(TextUtils.TruncateAt.END);
            r14.addView(r32);
        }
    }

    public static /* synthetic */ void $r8$lambda$8X8S_mRahdqxWLYcbymTgoCjGzA(SenderSelectPopup r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$startDismissAnimation$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Etj37qL7xJRf29_jC7eq6jBqskI(SenderSelectPopup r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$startShowAnimation$6(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$HIGKI1wAB9kKh9QcQ9WmAYEk_Co(SenderSelectPopup r0, List r1, Context r2, ChatActivity r3, boolean r4, OnSelectCallback r5, View r6, int r7) {
        r0.lambda$new$2(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$LBvO1PYvAXxLV8AdQUR9Qzj5HC8(SenderSelectPopup r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$startDismissAnimation$11(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$MITL3HwcPCRVM0NcAjSAq3dPKGQ(SenderSelectPopup r0, TLRPC.User r1, ChatActivity r2) {
        r0.lambda$new$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YHq6sBfXOgQ6FJ34YzttC47MG64(SenderSelectPopup r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$startDismissAnimation$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$gQNqr8eDrXW6IkHeMoUbtwG0zZI(SenderSelectPopup r0, TLRPC.Chat r1, ChatActivity r2) {
        r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ghjAPA6K3eW8mKiTJQP2SZE989Y(SenderSelectPopup r0, ChatActivity r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$ho1D-alWll4gDz4d92F9oX8TImY, reason: not valid java name */
    public static /* synthetic */ void m12620$r8$lambda$ho1DalWll4gDz4d92F9oX8TImY(SenderSelectPopup r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$startShowAnimation$7(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$rRSZXRzxZZBrW-x7BXrvQtMs42E, reason: not valid java name */
    public static /* synthetic */ void m12621$r8$lambda$rRSZXRzxZZBrWx7BXrvQtMs42E(SenderSelectPopup r0, SpringAnimation r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        r0.lambda$startDismissAnimation$12(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ boolean $r8$lambda$u0XZtqVZ_E2FDclVx06u_2sg8iA(SenderSelectPopup r0, List r1, MessagesController r2, ChatActivity r3, View r4, int r5) {
        return r0.lambda$new$5(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$xNvUw0-NUtgDqZYcwM2bGmUpuro, reason: not valid java name */
    public static /* synthetic */ void m12622$r8$lambda$xNvUw0NUtgDqZYcwM2bGmUpuro(SenderSelectPopup r0, WindowManager r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$z6xcWUaAjExh6K8g8d9pxEUZ164(SenderSelectPopup r0, SpringAnimation r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        r0.lambda$startShowAnimation$8(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$fgetbulletinContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12623$$Nest$fgetbulletinContainer(SenderSelectPopup r0) {
        return r0.bulletinContainer;
    }

    /* renamed from: -$$Nest$fgetbulletinHideCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m12624$$Nest$fgetbulletinHideCallback(SenderSelectPopup r0) {
        return r0.bulletinHideCallback;
    }

    /* renamed from: -$$Nest$fgetbulletins, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m12625$$Nest$fgetbulletins(SenderSelectPopup r0) {
        return r0.bulletins;
    }

    /* renamed from: -$$Nest$fgetdismissed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12626$$Nest$fgetdismissed(SenderSelectPopup r0) {
        return r0.dismissed;
    }

    /* renamed from: -$$Nest$fgetheaderShadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m12627$$Nest$fgetheaderShadow(SenderSelectPopup r0) {
        return r0.headerShadow;
    }

    /* renamed from: -$$Nest$fgetisDismissingByBulletin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12628$$Nest$fgetisDismissingByBulletin(SenderSelectPopup r0) {
        return r0.isDismissingByBulletin;
    }

    /* renamed from: -$$Nest$fgetisHeaderShadowVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Boolean m12629$$Nest$fgetisHeaderShadowVisible(SenderSelectPopup r0) {
        return r0.isHeaderShadowVisible;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m12630$$Nest$fgetlayoutManager(SenderSelectPopup r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetpopupX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12631$$Nest$fgetpopupX(SenderSelectPopup r0) {
        return r0.popupX;
    }

    /* renamed from: -$$Nest$fgetpopupY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12632$$Nest$fgetpopupY(SenderSelectPopup r0) {
        return r0.popupY;
    }

    /* renamed from: -$$Nest$fputisDismissingByBulletin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12633$$Nest$fputisDismissingByBulletin(SenderSelectPopup r0, boolean r1) {
        r0.isDismissingByBulletin = r1;
    }

    /* renamed from: -$$Nest$fputisHeaderShadowVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12634$$Nest$fputisHeaderShadowVisible(SenderSelectPopup r0, Boolean r1) {
        r0.isHeaderShadowVisible = r1;
    }

    @SuppressLint({"WrongConstant"})
    public SenderSelectPopup(Context r11, ChatActivity r12, MessagesController r13, boolean r14, TLRPC.Peer r15, TLRPC.TL_channels_sendAsPeers r16, OnSelectCallback r17, Theme.ResourcesProvider r18) {
        super(r11);
        this.springAnimations = new ArrayList();
        this.bulletins = new ArrayList();
        this.defPeer = r15;
        this.sendAsPeers = r16;
        if (r12 != null) goto L5;
        int r3 = UserConfig.selectedAccount;
    L6:
        this.currentAccount = r3;
        BackButtonFrameLayout r32 = new BackButtonFrameLayout(this, r11);
        this.scrimPopupContainerLayout = r32;
        r32.setLayoutParams(LayoutHelper.createFrame(-2, -2.0f));
        setContentView(this.scrimPopupContainerLayout);
        setWidth(-2);
        setHeight(-2);
        setBackgroundDrawable(null);
        Drawable r33 = ContextCompat.getDrawable(r11, R.drawable.popup_fixed_alert4).mutate();
        r33.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r18), PorterDuff.Mode.MULTIPLY));
        this.scrimPopupContainerLayout.setBackground(r33);
        Rect r4 = new Rect();
        r33.getPadding(r4);
        this.scrimPopupContainerLayout.setPadding(r4.left, r4.top, r4.right, r4.bottom);
        int r34 = AndroidUtilities.dp(450.0f);
        if (r12 != null) goto L9;
        int r42 = AndroidUtilities.displaySize.x;
    L10:
        int r5 = (int) (r42 * 0.75f);
        AnonymousClass1 r43 = new AnonymousClass1(this, r11, r5, r34);
        this.recyclerContainer = r43;
        r43.setOrientation(1);
        TextView r44 = new TextView(r11);
        this.headerText = r44;
        r44.setTextColor(Theme.getColor(Theme.key_dialogTextBlue, r18));
        this.headerText.setTextSize(1, 16.0f);
        this.headerText.setText(LocaleController.getString(R.string.SendMessageAsTitle));
        this.headerText.setTypeface(AndroidUtilities.bold(), 1);
        int r35 = AndroidUtilities.dp(18.0f);
        this.headerText.setPadding(r35, AndroidUtilities.dp(12.0f), r35, AndroidUtilities.dp(12.0f));
        this.recyclerContainer.addView(this.headerText);
        FrameLayout r8 = new FrameLayout(r11);
        ArrayList<TLRPC.TL_sendAsPeer> r36 = r16.peers;
        this.recyclerView = new RecyclerListView(r11);
        LinearLayoutManager r0 = new LinearLayoutManager(r11);
        this.layoutManager = r0;
        this.recyclerView.setLayoutManager(r0);
        this.recyclerView.setAdapter(new AnonymousClass2(this, r18, r36, r13, r5, r15));
        this.recyclerView.addOnScrollListener(new AnonymousClass3(this));
        this.recyclerView.setOnItemClickListener(new SenderSelectPopup$$ExternalSyntheticLambda7(this, r36, r11, r12, r14, r17));
        this.recyclerView.setOnItemLongClickListener(new SenderSelectPopup$$ExternalSyntheticLambda8(this, r36, r13, r12));
        this.recyclerView.setOverScrollMode(2);
        r8.addView(this.recyclerView);
        this.headerShadow = new View(r11);
        Drawable r02 = ContextCompat.getDrawable(r11, R.drawable.header_shadow);
        r02.setAlpha(153);
        this.headerShadow.setBackground(r02);
        this.headerShadow.setAlpha(0.0f);
        r8.addView(this.headerShadow, LayoutHelper.createFrame(-1, 4.0f));
        this.recyclerContainer.addView(r8, LayoutHelper.createFrame(-1, -2.0f));
        this.scrimPopupContainerLayout.addView(this.recyclerContainer);
        return;
    L9:
        r42 = r12.contentView.getWidth();
        goto L10
    L5:
        r3 = r12.getCurrentAccount();
        goto L6
    }

    private /* synthetic */ void lambda$new$0(ChatActivity r3) {
        if (r3 == null) goto L5;
        r3.presentFragment(new PremiumPreviewFragment("select_sender"));
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$new$1(WindowManager r1) {
        r1.removeView(this.bulletinContainer);
    }

    private /* synthetic */ void lambda$new$2(List r2, Context r3, ChatActivity r4, boolean r5, OnSelectCallback r6, View r7, int r8) {
        TLRPC.TL_sendAsPeer r22 = (TLRPC.TL_sendAsPeer) r2.get(r8);
        if (this.clicked == false) goto L6;
        return;
    L6:
        if (r22.premium_required == true) goto L8;
    L27:
        this.clicked = true;
        r6.onPeerSelected(this.recyclerView, (SenderView) r7, r22.peer);
        return;
    L8:
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L27;
        r7.performHapticFeedback(3, 2);     // Catch: Exception -> L29
    L11:
        WindowManager r23 = (WindowManager) r3.getSystemService("window");
        if (this.bulletinContainer != null) goto L14;
        this.bulletinContainer = new AnonymousClass4(this, r3);
    L14:
        Runnable r62 = this.bulletinHideCallback;
        if (r62 == null) goto L18;
        AndroidUtilities.cancelRunOnUIThread(r62);
    L18:
        if (this.bulletinContainer.getParent() != null) goto L23;
        WindowManager.LayoutParams r63 = new WindowManager.LayoutParams();
        r63.height = -1;
        r63.width = -1;
        r63.format = -3;
        r63.type = 99;
        r63.flags |= Integer.MIN_VALUE;
        if (Build.VERSION.SDK_INT < 28) goto L22;
        AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r63, 1);
    L22:
        AndroidUtilities.setPreferredMaxRefreshRate(r23, this.bulletinContainer, r63);
        r23.addView(this.bulletinContainer, r63);
    L23:
        if (r4 == null) goto L25;
        Bulletin r32 = Bulletin.make(this.bulletinContainer, new SelectSendAsPremiumHintBulletinLayout(r3, r4.themeDelegate, r5, new SenderSelectPopup$$ExternalSyntheticLambda9(this, r4)), 1500);
        r32.getLayout().addCallback(new AnonymousClass5(this, r32));
        r32.show();
    L25:
        SenderSelectPopup$$ExternalSyntheticLambda10 r33 = new SenderSelectPopup$$ExternalSyntheticLambda10(this, r23);
        this.bulletinHideCallback = r33;
        AndroidUtilities.runOnUIThread(r33, 2500);
    }

    private /* synthetic */ void lambda$new$3(TLRPC.User r7, ChatActivity r8) {
        MessagesController.getInstance(this.currentAccount).openChatOrProfileWith(r7, null, r8, 0, false);
    }

    private /* synthetic */ void lambda$new$4(TLRPC.Chat r7, ChatActivity r8) {
        MessagesController.getInstance(this.currentAccount).openChatOrProfileWith(null, r7, r8, 1, false);
    }

    private /* synthetic */ boolean lambda$new$5(List r3, MessagesController r4, ChatActivity r5, View r6, int r7) {
        long r62 = MessageObject.getPeerId(((TLRPC.TL_sendAsPeer) r3.get(r7)).peer);
        if (r62 <= 0) goto L7;
        TLRPC.User r32 = r4.getUser(Long.valueOf(r62));
        if (r32 == null) goto L10;
        AndroidUtilities.runOnUIThread(new SenderSelectPopup$$ExternalSyntheticLambda11(this, r32, r5), 500);
    L10:
        startDismissAnimation(new SpringAnimation[0]);
        return true;
    L7:
        TLRPC.Chat r33 = r4.getChat(Long.valueOf(-r62));
        if (r33 == null) goto L10;
        AndroidUtilities.runOnUIThread(new SenderSelectPopup$$ExternalSyntheticLambda12(this, r33, r5), 500);
        goto L10
    }

    private /* synthetic */ void lambda$startDismissAnimation$10(DynamicAnimation r1, float r2, float r3) {
        this.recyclerContainer.setScaleY(1.0f / r2);
    }

    private /* synthetic */ void lambda$startDismissAnimation$11(DynamicAnimation r1, boolean r2, float r3, float r4) {
        this.runningCustomSprings = false;
        dismiss();
    }

    private /* synthetic */ void lambda$startDismissAnimation$12(SpringAnimation r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        if (r3 == true) goto L5;
        this.springAnimations.remove(r1);
        r2.cancel();
        return;
    }

    private /* synthetic */ void lambda$startDismissAnimation$9(DynamicAnimation r1, float r2, float r3) {
        this.recyclerContainer.setScaleX(1.0f / r2);
    }

    private /* synthetic */ void lambda$startShowAnimation$6(DynamicAnimation r1, float r2, float r3) {
        this.recyclerContainer.setScaleX(1.0f / r2);
    }

    private /* synthetic */ void lambda$startShowAnimation$7(DynamicAnimation r1, float r2, float r3) {
        this.recyclerContainer.setScaleY(1.0f / r2);
    }

    private /* synthetic */ void lambda$startShowAnimation$8(SpringAnimation r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        if (r3 == true) goto L5;
        this.springAnimations.remove(r1);
        r2.cancel();
        return;
    }

    @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
    public void dismiss() {
        if (this.dismissed == false) goto L5;
        return;
    L5:
        FrameLayout r0 = this.bulletinContainer;
        if (r0 != null) goto L8;
    L10:
        this.dismissed = true;
        super.dismiss();
        return;
    L8:
        if (r0.getAlpha() != 1.0f) goto L10;
        WindowManager r02 = (WindowManager) this.bulletinContainer.getContext().getSystemService("window");
        this.bulletinContainer.animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass6(this, r02));
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
    public void showAtLocation(View r1, int r2, int r3, int r4) {
        this.popupX = r3;
        this.popupY = r4;
        super.showAtLocation(r1, r2, r3, r4);
    }

    public void startDismissAnimation(SpringAnimation... r12) {
        ArrayList r0 = new ArrayList(this.springAnimations);
        int r1 = r0.size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        Object r4 = r0.get(r3);
        r3 = r3 + 1;
        ((SpringAnimation) r4).cancel();
        goto L3
    L5:
        this.springAnimations.clear();
        this.scrimPopupContainerLayout.setPivotX(AndroidUtilities.dp(8.0f));
        this.scrimPopupContainerLayout.setPivotY(r0.getMeasuredHeight() - AndroidUtilities.dp(8.0f));
        this.recyclerContainer.setPivotX(0.0f);
        this.recyclerContainer.setPivotY(0.0f);
        this.scrimPopupContainerLayout.setScaleX(1.0f);
        this.scrimPopupContainerLayout.setScaleY(1.0f);
        this.recyclerContainer.setAlpha(1.0f);
        ArrayList r02 = new ArrayList();
        SpringAnimation r42 = new SpringAnimation(this.scrimPopupContainerLayout, DynamicAnimation.SCALE_X).setSpring(new SpringForce(0.25f).setStiffness(750.0f).setDampingRatio(1.0f)).addUpdateListener(new SenderSelectPopup$$ExternalSyntheticLambda0(this));
        SpringAnimation r5 = new SpringAnimation(this.scrimPopupContainerLayout, DynamicAnimation.SCALE_Y).setSpring(new SpringForce(0.25f).setStiffness(750.0f).setDampingRatio(1.0f)).addUpdateListener(new SenderSelectPopup$$ExternalSyntheticLambda1(this));
        FrameLayout r9 = this.scrimPopupContainerLayout;
        DynamicAnimation.ViewProperty r10 = DynamicAnimation.ALPHA;
        r02.addAll(Arrays.asList(new SpringAnimation[]{r42, r5, new SpringAnimation(r9, r10).setSpring(new SpringForce(0.0f).setStiffness(750.0f).setDampingRatio(1.0f)), new SpringAnimation(this.recyclerContainer, r10).setSpring(new SpringForce(0.25f).setStiffness(750.0f).setDampingRatio(1.0f))}));
        int r13 = 0;
    L7:
        if (r13 >= r12.length) goto L13;
        SpringAnimation r32 = r12[r13];
        if (r32 == null) goto L11;
        r02.add(r32);
    L11:
        r13 = r13 + 1;
        goto L7
    L13:
        if (r12.length <= 0) goto L15;
        boolean r122 = true;
    L16:
        this.runningCustomSprings = r122;
        ((SpringAnimation) r02.get(0)).addEndListener(new SenderSelectPopup$$ExternalSyntheticLambda2(this));
        int r123 = r02.size();
    L17:
        if (r2 >= r123) goto L19;
        Object r14 = r02.get(r2);
        r2 = r2 + 1;
        SpringAnimation r15 = (SpringAnimation) r14;
        this.springAnimations.add(r15);
        r15.addEndListener(new SenderSelectPopup$$ExternalSyntheticLambda3(this, r15));
        r15.start();
        goto L17
    L19:
        return;
    L15:
        r122 = false;
        goto L16
    }

    public void startShowAnimation() {
        Iterator<SpringAnimation> r0 = this.springAnimations.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().cancel();
        goto L4
    L6:
        this.springAnimations.clear();
        this.scrimPopupContainerLayout.setPivotX(AndroidUtilities.dp(8.0f));
        this.scrimPopupContainerLayout.setPivotY(r0.getMeasuredHeight() - AndroidUtilities.dp(8.0f));
        this.recyclerContainer.setPivotX(0.0f);
        this.recyclerContainer.setPivotY(0.0f);
        ArrayList<TLRPC.TL_sendAsPeer> r02 = this.sendAsPeers.peers;
        if (this.defPeer == null) goto L32;
        int r1 = AndroidUtilities.dp(54.0f);
        int r3 = r02.size() * r1;
        int r4 = 0;
        int r5 = 0;
    L10:
        if (r5 >= r02.size()) goto L32;
        TLRPC.Peer r6 = r02.get(r5).peer;
        long r7 = r6.channel_id;
        if (r7 != 0) goto L14;
    L15:
        long r72 = r6.user_id;
        if (r72 != 0) goto L18;
    L19:
        long r62 = r6.chat_id;
        if (r62 == 0) goto L31;
        if (r62 != this.defPeer.chat_id) goto L31;
    L24:
        if (r5 != (r02.size() - 1)) goto L26;
    L28:
        this.layoutManager.scrollToPositionWithOffset(r5, (r4 + AndroidUtilities.dp(7.0f)) + (r3 - ((r02.size() - 2) * r1)));
        if (this.recyclerView.computeVerticalScrollOffset() <= 0) goto L32;
        this.headerShadow.animate().cancel();
        this.headerShadow.animate().alpha(1.0f).setDuration(150).start();
        goto L32
    L26:
        if (this.recyclerView.getMeasuredHeight() >= r3) goto L28;
        r4 = this.recyclerView.getMeasuredHeight() % r1;
    L31:
        r5 = r5 + 1;
        goto L10
    L18:
        if (r72 == this.defPeer.user_id) goto L24;
    L14:
        if (r7 == this.defPeer.channel_id) goto L24;
    L32:
        this.scrimPopupContainerLayout.setScaleX(0.25f);
        this.scrimPopupContainerLayout.setScaleY(0.25f);
        this.recyclerContainer.setAlpha(0.25f);
        SpringAnimation r03 = new SpringAnimation(this.scrimPopupContainerLayout, DynamicAnimation.SCALE_X).setSpring(new SpringForce(1.0f).setStiffness(750.0f).setDampingRatio(1.0f)).addUpdateListener(new SenderSelectPopup$$ExternalSyntheticLambda4(this));
        SpringAnimation r12 = new SpringAnimation(this.scrimPopupContainerLayout, DynamicAnimation.SCALE_Y).setSpring(new SpringForce(1.0f).setStiffness(750.0f).setDampingRatio(1.0f)).addUpdateListener(new SenderSelectPopup$$ExternalSyntheticLambda5(this));
        FrameLayout r52 = this.scrimPopupContainerLayout;
        DynamicAnimation.ViewProperty r63 = DynamicAnimation.ALPHA;
        Iterator r04 = Arrays.asList(new SpringAnimation[]{r03, r12, new SpringAnimation(r52, r63).setSpring(new SpringForce(1.0f).setStiffness(750.0f).setDampingRatio(1.0f)), new SpringAnimation(this.recyclerContainer, r63).setSpring(new SpringForce(1.0f).setStiffness(750.0f).setDampingRatio(1.0f))}).iterator();
    L34:
        if (r04.hasNext() == false) goto L36;
        SpringAnimation r13 = (SpringAnimation) r04.next();
        this.springAnimations.add(r13);
        r13.addEndListener(new SenderSelectPopup$$ExternalSyntheticLambda6(this, r13));
        r13.start();
        goto L34
    }
}
