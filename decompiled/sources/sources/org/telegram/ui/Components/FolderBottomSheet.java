package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_chatlists;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.FilterCreateActivity;
import org.telegram.ui.FiltersSetupActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.MainTabsActivity;

/* loaded from: classes7.dex */
public class FolderBottomSheet extends BottomSheetWithRecyclerListView {
    private int alreadyHeaderRow;
    private ArrayList<Long> alreadyJoined;
    private ArrayList<TLRPC.Peer> alreadyPeers;
    private int alreadySectionRow;
    private int alreadyUsersEndRow;
    private int alreadyUsersStartRow;
    private FrameLayout bulletinContainer;
    private Button button;
    private View buttonShadow;
    private boolean deleting;
    private CharSequence escapedTitle;
    private int filterId;
    private HeaderCell headerCell;
    private int headerRow;
    private TL_chatlists.chatlist_ChatlistInvite invite;
    private long lastClicked;
    private long lastClickedDialogId;
    private Utilities.Callback<Boolean> onDone;
    private ArrayList<TLRPC.Peer> peers;
    private int reqId;
    private int rowsCount;
    private int sectionRow;
    private ArrayList<Long> selectedPeers;
    private int shiftDp;
    private String slug;
    private boolean success;
    private CharSequence title;
    private TitleCell titleCell;
    private ArrayList<TLRPC.MessageEntity> titleEntities;
    private boolean titleNoanimate;
    private int titleRow;
    private TL_chatlists.TL_chatlists_chatlistUpdates updates;
    private int usersEndRow;
    private int usersSectionRow;
    private int usersStartRow;

    /* renamed from: org.telegram.ui.Components.FolderBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ FolderBottomSheet this$0;

        public static /* synthetic */ void $r8$lambda$lPMrt2fnboFaftPg7TNON8dutzw(AnonymousClass1 r0, long r1) {
            r0.lambda$onBindViewHolder$0(r1);
        }

        public AnonymousClass1(FolderBottomSheet r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(long r2) {
            this.this$0.dismiss();
            MessagesController r1 = MessagesController.getInstance(UserConfig.selectedAccount);
            TLRPC.Chat r22 = r1.getChat(Long.valueOf(-r2));
            if (r22 != null) goto L5;
            return;
        L5:
            r1.openByUserName(r22.username, LaunchActivity.getLastFragment(), 1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return FolderBottomSheet.m10727$$Nest$fgetrowsCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != FolderBottomSheet.m10732$$Nest$fgettitleRow(this.this$0)) goto L7;
            return 0;
        L7:
            if (r2 != FolderBottomSheet.m10728$$Nest$fgetsectionRow(this.this$0)) goto L9;
            return 1;
        L9:
            if (r2 != FolderBottomSheet.m10735$$Nest$fgetusersSectionRow(this.this$0)) goto L11;
            return 1;
        L11:
            if (r2 != FolderBottomSheet.m10719$$Nest$fgetalreadySectionRow(this.this$0)) goto L14;
            return 1;
        L14:
            if (r2 != FolderBottomSheet.m10724$$Nest$fgetheaderRow(this.this$0)) goto L16;
            return 3;
        L16:
            if (r2 == FolderBottomSheet.m10716$$Nest$fgetalreadyHeaderRow(this.this$0)) goto L24;
            return 2;
        L24:
            return 3;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3) {
            if (r3.getItemViewType() == 2) goto L5;
            return false;
        L5:
            if (r3.getAdapterPosition() >= FolderBottomSheet.m10736$$Nest$fgetusersStartRow(this.this$0)) goto L7;
            return false;
        L7:
            if (r3.getAdapterPosition() > FolderBottomSheet.m10734$$Nest$fgetusersEndRow(this.this$0)) goto L13;
            return true;
        L13:
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r11, int r12) {
            int r0 = r11.getItemViewType();
            TLRPC.Chat r3 = null;
            if (r0 != 2) goto L59;
            GroupCreateUserCell r112 = (GroupCreateUserCell) r11.itemView;
            if (r12 < FolderBottomSheet.m10736$$Nest$fgetusersStartRow(this.this$0)) goto L12;
            if (r12 > FolderBottomSheet.m10734$$Nest$fgetusersEndRow(this.this$0)) goto L12;
            if (FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0) == null) goto L18;
            TLRPC.Peer r122 = (TLRPC.Peer) FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0).get(r12 - FolderBottomSheet.m10736$$Nest$fgetusersStartRow(this.this$0));
        L20:
            if (r122 != null) goto L22;
        L37:
            long r7 = 0;
            String r5 = null;
            String r6 = null;
        L39:
            if ((r3 instanceof TLRPC.Chat) == false) goto L50;
            TLRPC.Chat r123 = r3;
            r5 = r123.title;
            if (r123.participants_count == 0) goto L47;
            boolean r4 = ChatObject.isChannelAndNotMegaGroup(r123);
            int r124 = r123.participants_count;
            if (r4 == false) goto L45;
            r6 = LocaleController.formatPluralStringComma("Subscribers", r124);
            goto L50
        L45:
            r6 = LocaleController.formatPluralStringComma("Members", r124);
            goto L50
        L47:
            if (ChatObject.isChannelAndNotMegaGroup(r123) == false) goto L49;
            r6 = LocaleController.getString(R.string.ChannelPublic);
            goto L50
        L49:
            r6 = LocaleController.getString(R.string.MegaPublic);
        L50:
            r112.setTag(Long.valueOf(r7));
            CheckBoxBase r125 = r112.getCheckBox().getCheckBoxBase();
            if (FolderBottomSheet.m10717$$Nest$fgetalreadyJoined(this.this$0).contains(Long.valueOf(r7)) == false) goto L53;
            float r42 = 0.5f;
        L54:
            r125.setAlpha(r42);
            r112.setChecked(FolderBottomSheet.m10729$$Nest$fgetselectedPeers(this.this$0).contains(Long.valueOf(r7)), false);
            r112.setObject(r3, r5, r6);
            if (r7 >= 0) goto L88;
            r112.setOpenChatView(new FolderBottomSheet$1$$ExternalSyntheticLambda0(this, r7));
            return;
        L88:
            return;
        L53:
            r42 = 1.0f;
            goto L54
        L22:
            if ((r122 instanceof TLRPC.TL_peerUser) == false) goto L31;
            long r32 = r122.user_id;
            TLRPC.User r126 = this.this$0.getBaseFragment().getMessagesController().getUser(Long.valueOf(r122.user_id));
            r5 = UserObject.getUserName(r126);
            if (r126 != 0) goto L26;
        L28:
            r6 = LocaleController.getString(R.string.FilterInviteUser);
        L29:
            r7 = r32;
            r3 = r126;
            goto L39
        L26:
            if (r126.bot == false) goto L28;
            r6 = LocaleController.getString(R.string.FilterInviteBot);
            goto L29
        L31:
            if ((r122 instanceof TLRPC.TL_peerChat) == false) goto L35;
            long r43 = -r122.chat_id;
            TLRPC.Chat r127 = this.this$0.getBaseFragment().getMessagesController().getChat(Long.valueOf(r122.chat_id));
        L33:
            r6 = null;
            r7 = r43;
            r3 = r127;
            r5 = null;
            goto L39
        L35:
            if ((r122 instanceof TLRPC.TL_peerChannel) == false) goto L37;
            r43 = -r122.channel_id;
            r127 = this.this$0.getBaseFragment().getMessagesController().getChat(Long.valueOf(r122.channel_id));
        L18:
            r122 = null;
        L12:
            if (r12 < FolderBottomSheet.m10721$$Nest$fgetalreadyUsersStartRow(this.this$0)) goto L18;
            if (r12 > FolderBottomSheet.m10720$$Nest$fgetalreadyUsersEndRow(this.this$0)) goto L18;
            if (FolderBottomSheet.m10718$$Nest$fgetalreadyPeers(this.this$0) == null) goto L18;
            r122 = (TLRPC.Peer) FolderBottomSheet.m10718$$Nest$fgetalreadyPeers(this.this$0).get(r12 - FolderBottomSheet.m10721$$Nest$fgetalreadyUsersStartRow(this.this$0));
            goto L20
        L59:
            if (r0 != 3) goto L67;
            HeaderCell r113 = (HeaderCell) r11.itemView;
            if (r12 != FolderBottomSheet.m10716$$Nest$fgetalreadyHeaderRow(this.this$0)) goto L64;
            r113.setText(LocaleController.getString(R.string.FolderLinkHeaderAlready), false);
            r113.setAction(_UrlKt.FRAGMENT_ENCODE_SET, null);
            return;
        L64:
            FolderBottomSheet.m10737$$Nest$fputheaderCell(this.this$0, r113);
            FolderBottomSheet.m10739$$Nest$mupdateHeaderCell(this.this$0, false);
            return;
        L67:
            if (r0 != 1) goto L85;
            TextInfoPrivacyCell r114 = (TextInfoPrivacyCell) r11.itemView;
            r114.setForeground(Theme.getThemedDrawableByKey(this.this$0.getContext(), R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
            if (r12 != FolderBottomSheet.m10719$$Nest$fgetalreadySectionRow(this.this$0)) goto L71;
        L83:
            r114.setFixedSize(12);
            r114.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L71:
            if (r12 == FolderBottomSheet.m10728$$Nest$fgetsectionRow(this.this$0)) goto L83;
            if (FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0) == null) goto L83;
            if (FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0).isEmpty() == true) goto L83;
            r114.setFixedSize(0);
            if (FolderBottomSheet.m10722$$Nest$fgetdeleting(this.this$0) == false) goto L81;
            r114.setText(LocaleController.getString(R.string.FolderLinkHintRemove));
            return;
        L81:
            r114.setText(LocaleController.getString(R.string.FolderLinkHint));
            return;
        L85:
            if (r0 != 0) goto L89;
            FolderBottomSheet.m10738$$Nest$fputtitleCell(this.this$0, (TitleCell) r11.itemView);
            this.this$0.updateCount(false);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            if (r10 != 0) goto L12;
            FolderBottomSheet r102 = this.this$0;
            FolderBottomSheet r2 = this.this$0;
            Context r3 = r2.getContext();
            if ((FolderBottomSheet.m10725$$Nest$fgetinvite(this.this$0) instanceof TL_chatlists.TL_chatlists_chatlistInviteAlready) == false) goto L7;
        L10:
            boolean r4 = true;
        L11:
            TitleCell r1 = new TitleCell(r2, r3, r4, FolderBottomSheet.m10723$$Nest$fgetescapedTitle(this.this$0), FolderBottomSheet.m10730$$Nest$fgettitleEntities(this.this$0), FolderBottomSheet.m10731$$Nest$fgettitleNoanimate(this.this$0));
            FolderBottomSheet.m10738$$Nest$fputtitleCell(r102, r1);
            View r12 = r1;
        L22:
            return new RecyclerListView.Holder(r12);
        L7:
            if (FolderBottomSheet.m10733$$Nest$fgetupdates(this.this$0) != null) goto L10;
            r4 = false;
            goto L11
        L12:
            if (r10 != 1) goto L15;
            TextInfoPrivacyCell r13 = new TextInfoPrivacyCell(this.this$0.getContext());
            r13.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
            r12 = r13;
            goto L22
        L15:
            if (r10 != 2) goto L18;
            GroupCreateUserCell r14 = new GroupCreateUserCell(this.this$0.getContext(), 1, 0, false);
            r14.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r12 = r14;
            goto L22
        L18:
            if (r10 != 3) goto L20;
            HeaderCell r15 = new HeaderCell(this.this$0.getContext());
            r15.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r12 = r15;
            goto L22
        L20:
            r12 = null;
            goto L22
        }
    }

    public static class Button extends FrameLayout {
        private ShapeDrawable background;
        float countAlpha;
        AnimatedFloat countAlphaAnimated;
        private ValueAnimator countAnimator;
        private float countScale;
        AnimatedTextView.AnimatedTextDrawable countText;
        private boolean enabled;
        private ValueAnimator enabledAnimator;
        private float enabledT;
        private int lastCount;
        private boolean loading;
        private ValueAnimator loadingAnimator;
        private CircularProgressDrawable loadingDrawable;
        private float loadingT;
        Paint paint;
        private View rippleView;
        AnimatedTextView.AnimatedTextDrawable text;

        /* renamed from: org.telegram.ui.Components.FolderBottomSheet$Button$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ Button this$0;
            final /* synthetic */ boolean val$loading;

            public AnonymousClass1(Button r1, boolean r2) {
                this.this$0 = r1;
                this.val$loading = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                Button r22 = this.this$0;
                if (this.val$loading == false) goto L5;
                float r0 = 1.0f;
            L6:
                Button.m10744$$Nest$fputloadingT(r22, r0);
                this.this$0.invalidate();
                return;
            L5:
                r0 = 0.0f;
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.Components.FolderBottomSheet$Button$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ Button this$0;

            public AnonymousClass2(Button r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                Button.m10743$$Nest$fputcountScale(this.this$0, 1.0f);
                this.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.FolderBottomSheet$Button$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ Button this$0;

            public AnonymousClass3(Button r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
            }
        }

        /* renamed from: $r8$lambda$4Hl8ENQKWXFP1XxL-TGGDd3V3aw, reason: not valid java name */
        public static /* synthetic */ void m10740$r8$lambda$4Hl8ENQKWXFP1XxLTGGDd3V3aw(Button r0, ValueAnimator r1) {
            r0.lambda$setEnabled$2(r1);
        }

        /* renamed from: $r8$lambda$C-ZfMqLI9Q8cXAoQTTaXmqih3yg, reason: not valid java name */
        public static /* synthetic */ void m10741$r8$lambda$CZfMqLI9Q8cXAoQTTaXmqih3yg(Button r0, ValueAnimator r1) {
            r0.lambda$animateCount$1(r1);
        }

        /* renamed from: $r8$lambda$wyAry_-IzSTpJTXDu0sfd314E1M, reason: not valid java name */
        public static /* synthetic */ void m10742$r8$lambda$wyAry_IzSTpJTXDu0sfd314E1M(Button r0, ValueAnimator r1) {
            r0.lambda$setLoading$0(r1);
        }

        /* renamed from: -$$Nest$fputcountScale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10743$$Nest$fputcountScale(Button r0, float r1) {
            r0.countScale = r1;
        }

        /* renamed from: -$$Nest$fputloadingT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10744$$Nest$fputloadingT(Button r0, float r1) {
            r0.loadingT = r1;
        }

        public Button(Context r11, String r12) {
            super(r11);
            CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.countAlphaAnimated = new AnimatedFloat(350, r7);
            this.loadingT = 0.0f;
            this.countScale = 1.0f;
            this.enabledT = 1.0f;
            this.enabled = true;
            View r1 = new View(r11);
            this.rippleView = r1;
            int r112 = Theme.key_featuredStickers_addButton;
            r1.setBackground(Theme.AdaptiveRipple.rect(Theme.getColor(r112), new float[]{8.0f}));
            addView(this.rippleView, LayoutHelper.createFrame(-1, -1.0f));
            ShapeDrawable r13 = Theme.createRoundRectDrawable(AndroidUtilities.dp(8.0f), Theme.getColor(r112));
            this.background = r13;
            setBackground(r13);
            Paint r14 = new Paint(1);
            this.paint = r14;
            int r9 = Theme.key_featuredStickers_buttonText;
            r14.setColor(Theme.getColor(r9));
            AnimatedTextView.AnimatedTextDrawable r15 = new AnimatedTextView.AnimatedTextDrawable(true, true, false);
            this.text = r15;
            r15.setAnimationProperties(0.3f, 0, 250, r7);
            this.text.setCallback(this);
            this.text.setTextSize(AndroidUtilities.dp(14.0f));
            this.text.setTypeface(AndroidUtilities.bold());
            this.text.setTextColor(Theme.getColor(r9));
            this.text.setText(r12);
            this.text.setGravity(1);
            AnimatedTextView.AnimatedTextDrawable r16 = new AnimatedTextView.AnimatedTextDrawable(false, false, true);
            this.countText = r16;
            r16.setAnimationProperties(0.3f, 0, 250, r7);
            this.countText.setCallback(this);
            this.countText.setTextSize(AndroidUtilities.dp(12.0f));
            this.countText.setTypeface(AndroidUtilities.bold());
            this.countText.setTextColor(Theme.getColor(r112));
            this.countText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.countText.setGravity(1);
            setWillNotDraw(false);
        }

        private void animateCount() {
            ValueAnimator r0 = this.countAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.countAnimator = null;
        L5:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.countAnimator = r02;
            r02.addUpdateListener(new FolderBottomSheet$Button$$ExternalSyntheticLambda1(this));
            this.countAnimator.addListener(new AnonymousClass2(this));
            this.countAnimator.setInterpolator(new OvershootInterpolator(2.0f));
            this.countAnimator.setDuration(200);
            this.countAnimator.start();
        }

        private /* synthetic */ void lambda$animateCount$1(ValueAnimator r2) {
            this.countScale = Math.max(1.0f, ((Float) r2.getAnimatedValue()).floatValue());
            invalidate();
        }

        private /* synthetic */ void lambda$setEnabled$2(ValueAnimator r1) {
            this.enabledT = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        private /* synthetic */ void lambda$setLoading$0(ValueAnimator r1) {
            this.loadingT = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            return false;
        }

        public TextPaint getTextPaint() {
            return this.text.getPaint();
        }

        public boolean isLoading() {
            return this.loading;
        }

        @Override // android.view.View
        public void onDraw(Canvas r15) {
            this.rippleView.draw(r15);
            boolean r2 = false;
            if (this.loadingT > 0.0f) goto L5;
        L8:
            float r0 = this.loadingT;
            if (r0 < 1.0f) goto L11;
            return;
        L11:
            if (r0 == 0.0f) goto L13;
            r15.save();
            r15.translate(0.0f, (int) (this.loadingT * AndroidUtilities.dp(-24.0f)));
            r15.scale(1.0f, 1.0f - (this.loadingT * 0.4f));
            r2 = true;
        L13:
            float r02 = this.text.getCurrentWidth();
            float r1 = this.countAlphaAnimated.set(this.countAlpha);
            float r6 = ((AndroidUtilities.dp(15.66f) + this.countText.getCurrentWidth()) * r1) + r02;
            Rect r7 = AndroidUtilities.rectTmp2;
            r7.set((int) (((getMeasuredWidth() - r6) - getWidth()) / 2.0f), (int) (((getMeasuredHeight() - this.text.getHeight()) / 2.0f) - AndroidUtilities.dp(1.0f)), (int) ((((getMeasuredWidth() - r6) + getWidth()) / 2.0f) + r02), (int) (((getMeasuredHeight() + this.text.getHeight()) / 2.0f) - AndroidUtilities.dp(1.0f)));
            this.text.setAlpha((int) (((1.0f - this.loadingT) * 255.0f) * AndroidUtilities.lerp(0.5f, 1.0f, this.enabledT)));
            this.text.setBounds(r7);
            this.text.draw(r15);
            r7.set((int) ((((getMeasuredWidth() - r6) / 2.0f) + r02) + AndroidUtilities.dp(5.0f)), (int) ((getMeasuredHeight() - AndroidUtilities.dp(18.0f)) / 2.0f), (int) (((((getMeasuredWidth() - r6) / 2.0f) + r02) + AndroidUtilities.dp(13.0f)) + Math.max(AndroidUtilities.dp(9.0f), this.countText.getCurrentWidth())), (int) ((getMeasuredHeight() + AndroidUtilities.dp(18.0f)) / 2.0f));
            RectF r03 = AndroidUtilities.rectTmp;
            r03.set(r7);
            if (this.countScale == 1.0f) goto L16;
            r15.save();
            float r62 = this.countScale;
            r15.scale(r62, r62, r7.centerX(), r7.centerY());
        L16:
            this.paint.setAlpha((int) ((((1.0f - this.loadingT) * 255.0f) * r1) * r1));
            r15.drawRoundRect(r03, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.paint);
            r7.offset(-AndroidUtilities.dp(0.3f), -AndroidUtilities.dp(0.4f));
            this.countText.setAlpha((int) (((1.0f - this.loadingT) * 255.0f) * r1));
            this.countText.setBounds(r7);
            this.countText.draw(r15);
            if (this.countScale == 1.0f) goto L19;
            r15.restore();
        L19:
            if (r2 == false) goto L23;
            r15.restore();
            return;
        L23:
            return;
        L5:
            if (this.loadingDrawable != null) goto L7;
            this.loadingDrawable = new CircularProgressDrawable(this.text.getTextColor());
        L7:
            int r04 = (int) ((1.0f - this.loadingT) * AndroidUtilities.dp(24.0f));
            this.loadingDrawable.setBounds(0, r04, getWidth(), getHeight() + r04);
            this.loadingDrawable.setAlpha((int) (this.loadingT * 255.0f));
            this.loadingDrawable.draw(r15);
            invalidate();
            goto L8
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r5) {
            super.onInitializeAccessibilityNodeInfo(r5);
            r5.setClassName("android.widget.Button");
            StringBuilder r0 = new StringBuilder();
            r0.append(this.text.getText());
            if (this.lastCount <= 0) goto L5;
            String r4 = ", " + LocaleController.formatPluralString("Chats", this.lastCount, new Object[0]);
        L6:
            r0.append(r4);
            r5.setContentDescription(r0.toString());
            return;
        L5:
            r4 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L6
        }

        public void setCount(int r4, boolean r5) {
            if (r5 == false) goto L4;
            this.countText.cancelAnimation();
        L4:
            if (r5 == false) goto L10;
            int r0 = this.lastCount;
            if (r4 == r0) goto L10;
            if (r4 <= 0) goto L10;
            if (r0 <= 0) goto L10;
            animateCount();
        L10:
            this.lastCount = r4;
            if (r4 == 0) goto L13;
            float r02 = 1.0f;
        L14:
            this.countAlpha = r02;
            this.countText.setText(_UrlKt.FRAGMENT_ENCODE_SET + r4, r5);
            invalidate();
            return;
        L13:
            r02 = 0.0f;
            goto L14
        }

        public void setEmojiCacheType(int r1) {
            this.text.setEmojiCacheType(r1);
        }

        @Override // android.view.View
        public void setEnabled(boolean r4) {
            if (this.enabled == r4) goto L13;
            ValueAnimator r0 = this.enabledAnimator;
            if (r0 == null) goto L7;
            r0.cancel();
            this.enabledAnimator = null;
        L7:
            float r02 = this.enabledT;
            this.enabled = r4;
            if (r4 == false) goto L10;
            float r42 = 1.0f;
        L11:
            ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r02, r42});
            this.enabledAnimator = r43;
            r43.addUpdateListener(new FolderBottomSheet$Button$$ExternalSyntheticLambda0(this));
            this.enabledAnimator.addListener(new AnonymousClass3(this));
            this.enabledAnimator.start();
            return;
        L10:
            r42 = 0.0f;
            goto L11
        }

        public void setLoading(boolean r5) {
            if (this.loading == r5) goto L13;
            ValueAnimator r0 = this.loadingAnimator;
            if (r0 == null) goto L7;
            r0.cancel();
            this.loadingAnimator = null;
        L7:
            float r02 = this.loadingT;
            this.loading = r5;
            if (r5 == false) goto L10;
            float r1 = 1.0f;
        L11:
            ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
            this.loadingAnimator = r03;
            r03.addUpdateListener(new FolderBottomSheet$Button$$ExternalSyntheticLambda2(this));
            this.loadingAnimator.addListener(new AnonymousClass1(this, r5));
            this.loadingAnimator.setDuration(320);
            this.loadingAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.loadingAnimator.start();
            return;
        L10:
            r1 = 0.0f;
            goto L11
        }

        public void setText(CharSequence r2, boolean r3) {
            if (r3 == false) goto L4;
            this.text.cancelAnimation();
        L4:
            this.text.setText(r2, r3);
            invalidate();
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (this.text != r2) goto L5;
            return true;
        L5:
            if (this.countText != r2) goto L7;
            return true;
        L7:
            if (super.verifyDrawable(r2) == true) goto L14;
            return false;
        L14:
            return true;
        }
    }

    public static class HeaderCell extends FrameLayout {
        public AnimatedTextView actionTextView;
        public AnimatedTextView textView;

        /* renamed from: $r8$lambda$kFktNU1EJbYmEg4fSZ-vDobQYVY, reason: not valid java name */
        public static /* synthetic */ void m10745$r8$lambda$kFktNU1EJbYmEg4fSZvDobQYVY(Runnable r0, View r1) {
            if (r0 == null) goto L5;
            r0.run();
            return;
        }

        public HeaderCell(Context r15) {
            super(r15);
            AnimatedTextView r0 = new AnimatedTextView(r15, true, true, false);
            this.textView = r0;
            r0.setTextSize(AndroidUtilities.dp(15.0f));
            this.textView.setTypeface(AndroidUtilities.bold());
            AnimatedTextView r02 = this.textView;
            int r3 = Theme.key_windowBackgroundWhiteBlueHeader;
            r02.setTextColor(Theme.getColor(r3));
            AnimatedTextView r03 = this.textView;
            int r5 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r4 = 5;
        L6:
            r03.setGravity(r4);
            AnimatedTextView r04 = this.textView;
            if (LocaleController.isRTL == false) goto L9;
            int r42 = 5;
        L10:
            addView(r04, LayoutHelper.createFrame(-1, 20.0f, r42 | 80, 21.0f, 15.0f, 21.0f, 2.0f));
            AnimatedTextView r7 = new AnimatedTextView(r15, true, true, true);
            this.actionTextView = r7;
            r7.setAnimationProperties(0.45f, 0, 250, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.actionTextView.setTextSize(AndroidUtilities.dp(15.0f));
            this.actionTextView.setTextColor(Theme.getColor(r3));
            AnimatedTextView r152 = this.actionTextView;
            if (LocaleController.isRTL == false) goto L13;
            int r05 = 3;
        L14:
            r152.setGravity(r05);
            AnimatedTextView r153 = this.actionTextView;
            if (LocaleController.isRTL == true) goto L18;
            r5 = 5;
        L18:
            addView(r153, LayoutHelper.createFrame(-2, 20.0f, r5 | 80, 21.0f, 15.0f, 21.0f, 2.0f));
            ViewCompat.setAccessibilityHeading(this, true);
            return;
        L13:
            r05 = 5;
            goto L14
        L9:
            r42 = 3;
            goto L10
        L5:
            r4 = 3;
            goto L6
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            r2.setClassName("android.widget.TextView");
            r2.setText(this.textView.getText());
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void setAction(CharSequence r3, Runnable r4) {
            this.actionTextView.setText(r3, !LocaleController.isRTL);
            this.actionTextView.setOnClickListener(new FolderBottomSheet$HeaderCell$$ExternalSyntheticLambda0(r4));
        }

        public void setText(CharSequence r2, boolean r3) {
            if (r3 == false) goto L4;
            this.textView.cancelAnimation();
        L4:
            AnimatedTextView r1 = this.textView;
            if (r3 == true) goto L7;
        L9:
            boolean r32 = false;
        L10:
            r1.setText(r2, r32);
            return;
        L7:
            if (LocaleController.isRTL == true) goto L9;
            r32 = true;
            goto L10
        }
    }

    public class TitleCell extends FrameLayout {
        private boolean already;
        private FoldersPreview preview;
        private AnimatedEmojiSpan.TextViewEmojis subtitleTextView;
        final /* synthetic */ FolderBottomSheet this$0;
        private CharSequence title;
        private AnimatedEmojiSpan.TextViewEmojis titleTextView;

        public class FoldersPreview extends View {
            AnimatedTextView.AnimatedTextDrawable countText;
            Text leftFolder;
            Text leftFolder2;
            LinearGradient leftGradient;
            Matrix leftMatrix;
            Paint leftPaint;
            Text middleFolder;
            TextPaint paint;
            Path path;
            float[] radii;
            Text rightFolder;
            Text rightFolder2;
            LinearGradient rightGradient;
            Matrix rightMatrix;
            Paint rightPaint;
            Paint selectedPaint;
            TextPaint selectedTextPaint;
            final /* synthetic */ TitleCell this$1;

            public FoldersPreview(TitleCell r20, Context r21, CharSequence r22, CharSequence r23, CharSequence r24, ArrayList<TLRPC.MessageEntity> r25, boolean r26, CharSequence r27, CharSequence r28) {
                this.this$1 = r20;
                super(r21);
                this.paint = new TextPaint(1);
                this.selectedTextPaint = new TextPaint(1);
                this.selectedPaint = new Paint(1);
                this.path = new Path();
                this.radii = new float[8];
                this.leftPaint = new Paint(1);
                this.rightPaint = new Paint(1);
                this.leftMatrix = new Matrix();
                this.rightMatrix = new Matrix();
                TextPaint r4 = this.paint;
                int r7 = Theme.key_profile_tabText;
                r4.setColor(Theme.multAlpha(Theme.getColor(r7), 0.8f));
                this.paint.setTextSize(AndroidUtilities.dp(15.33f));
                this.paint.setTypeface(AndroidUtilities.bold());
                TextPaint r42 = this.selectedTextPaint;
                int r10 = Theme.key_windowBackgroundWhiteBlueText2;
                r42.setColor(Theme.getColor(r10));
                this.selectedTextPaint.setTextSize(AndroidUtilities.dp(17.0f));
                this.selectedTextPaint.setTypeface(AndroidUtilities.bold());
                this.selectedPaint.setColor(Theme.getColor(Theme.key_featuredStickers_unread));
                AnimatedTextView.AnimatedTextDrawable r12 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
                this.countText = r12;
                r12.setAnimationProperties(0.3f, 0, 250, CubicBezierInterpolator.EASE_OUT_QUINT);
                this.countText.setCallback(this);
                this.countText.setTextSize(AndroidUtilities.dp(11.66f));
                this.countText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                this.countText.setTypeface(AndroidUtilities.bold());
                this.countText.setGravity(1);
                int r72 = Theme.multAlpha(Theme.getColor(r7), 0.8f);
                int r9 = Theme.getColor(r10);
                if (r22 == null) goto L5;
                this.leftFolder2 = new Text(normalizeTitle(r22), 15.33f, AndroidUtilities.bold()).supportAnimatedEmojis(this).setColor(r72);
            L5:
                if (r23 == null) goto L7;
                this.leftFolder = new Text(normalizeTitle(r23), 15.33f, AndroidUtilities.bold()).supportAnimatedEmojis(this).setColor(r72);
            L7:
                CharSequence r1 = normalizeTitle(r24);
                Text r2 = new Text(r1, 15.33f, AndroidUtilities.bold()).supportAnimatedEmojis(this).setColor(r9);
                this.middleFolder = r2;
                this.middleFolder.setText(MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r1, r2.getFontMetricsInt(), false), r25, this.middleFolder.getFontMetricsInt()));
                Text r13 = this.middleFolder;
                if (r26 == false) goto L10;
                int r29 = 26;
            L11:
                r13.setEmojiCacheType(r29);
                if (r27 == null) goto L14;
                this.rightFolder = new Text(normalizeTitle(r27), 15.33f, AndroidUtilities.bold()).supportAnimatedEmojis(this).setColor(r72);
            L14:
                if (r28 == null) goto L16;
                this.rightFolder2 = new Text(normalizeTitle(r28), 15.33f, AndroidUtilities.bold()).supportAnimatedEmojis(this).setColor(r72);
            L16:
                float[] r14 = this.radii;
                float r210 = AndroidUtilities.dp(3.0f);
                r14[3] = r210;
                r14[2] = r210;
                r14[1] = r210;
                r14[0] = r210;
                float[] r15 = this.radii;
                float r211 = AndroidUtilities.dp(1.0f);
                r15[7] = r211;
                r15[6] = r211;
                r15[5] = r211;
                r15[4] = r211;
                Shader.TileMode r92 = Shader.TileMode.CLAMP;
                LinearGradient r16 = new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(80.0f), 0.0f, new int[]{-1, 16777215}, new float[]{0.0f, 1.0f}, r92);
                this.leftGradient = r16;
                this.leftPaint.setShader(r16);
                Paint r17 = this.leftPaint;
                PorterDuff.Mode r8 = PorterDuff.Mode.DST_OUT;
                r17.setXfermode(new PorterDuffXfermode(r8));
                LinearGradient r18 = new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(80.0f), 0.0f, new int[]{16777215, -1}, new float[]{0.0f, 1.0f}, r92);
                this.rightGradient = r18;
                this.rightPaint.setShader(r18);
                this.rightPaint.setXfermode(new PorterDuffXfermode(r8));
                return;
            L10:
                r29 = 0;
                goto L11
            }

            private boolean isCountEmpty() {
                if (this.countText.getText() != null) goto L5;
                return true;
            L5:
                if (this.countText.getText().length() == 0) goto L11;
                return false;
            L11:
                return true;
            }

            private CharSequence normalizeTitle(CharSequence r2) {
                if (r2 == null) goto L8;
                if ("ALL_CHATS".equals(r2.toString()) == true) goto L8;
                return r2;
            L8:
                return LocaleController.getString(R.string.FilterAllChats);
            }

            @Override // android.view.View
            public void onDraw(Canvas r15) {
                super.onDraw(r15);
                r15.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), 255, 31);
                float r152 = getMeasuredWidth() / 2.0f;
                float r1 = getMeasuredHeight() / 2.0f;
                if (this.middleFolder == null) goto L9;
                r15.save();
                float r2 = this.middleFolder.getCurrentWidth();
                if (isCountEmpty() == false) goto L7;
                float r5 = 0.0f;
            L8:
                float r22 = r2 + r5;
                float r52 = r152 - (r22 / 2.0f);
                r15.translate(r52, r1 - (this.middleFolder.getHeight() / 2.0f));
                this.middleFolder.draw(r15);
                r15.restore();
            L11:
                if (isCountEmpty() == true) goto L13;
                Rect r6 = AndroidUtilities.rectTmp2;
                r6.set((int) ((this.middleFolder.getCurrentWidth() + r52) + AndroidUtilities.dp(4.66f)), (int) (r1 - AndroidUtilities.dp(9.0f)), (int) (((this.middleFolder.getCurrentWidth() + r52) + AndroidUtilities.dp(15.32f)) + this.countText.getCurrentWidth()), (int) (AndroidUtilities.dp(9.0f) + r1));
                RectF r3 = AndroidUtilities.rectTmp;
                r3.set(r6);
                r15.drawRoundRect(r3, AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), this.selectedPaint);
                r6.offset(-AndroidUtilities.dp(0.33f), -AndroidUtilities.dp(0.66f));
                this.countText.setBounds(r6);
                this.countText.draw(r15);
            L13:
                float r32 = AndroidUtilities.dp(30.0f);
                float r62 = (r52 - r32) - this.leftFolder.getCurrentWidth();
                if (this.leftFolder2 != null) goto L16;
            L18:
                float r7 = r62;
            L20:
                if (this.leftFolder == null) goto L22;
                r15.save();
                r15.translate(r62, (r1 - (this.leftFolder.getHeight() / 2.0f)) + AndroidUtilities.dp(1.0f));
                this.leftFolder.draw(r15);
                r15.restore();
            L22:
                float r63 = r52 + r22;
                if (this.rightFolder == null) goto L26;
                r15.save();
                r15.translate(r63 + r32, (r1 - (this.rightFolder.getHeight() / 2.0f)) + AndroidUtilities.dp(1.0f));
                this.rightFolder.draw(r15);
                r15.restore();
                r63 = r63 + (this.rightFolder.getCurrentWidth() + r32);
            L26:
                if (this.rightFolder2 != null) goto L28;
            L30:
                float r12 = (r1 + (this.middleFolder.getHeight() / 2.0f)) + AndroidUtilities.dp(12.0f);
                r15.drawRect(0.0f, r12, getMeasuredWidth(), r12 + 1.0f, this.paint);
                this.path.rewind();
                RectF r13 = AndroidUtilities.rectTmp;
                float r23 = r22 / 2.0f;
                float r24 = r23 + r152;
                r13.set((r152 - r23) - AndroidUtilities.dp(4.0f), r12 - AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f) + r24, r12);
                this.path.addRoundRect(r13, this.radii, Path.Direction.CW);
                r15.drawPath(this.path, this.selectedPaint);
                r15.save();
                float r14 = Math.max(AndroidUtilities.dp(8.0f), r7);
                this.leftMatrix.reset();
                this.leftMatrix.postTranslate(Math.min(r52, r14 + AndroidUtilities.dp(8.0f)), 0.0f);
                this.leftGradient.setLocalMatrix(this.leftMatrix);
                float r0 = Math.min(getMeasuredWidth() - AndroidUtilities.dp(8.0f), r63);
                this.rightMatrix.reset();
                this.rightMatrix.postTranslate(Math.max(r24, r0 - AndroidUtilities.dp(88.0f)), 0.0f);
                this.rightGradient.setLocalMatrix(this.rightMatrix);
                r15.drawRect(0.0f, 0.0f, r152, getMeasuredHeight(), this.leftPaint);
                r15.drawRect(r152, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.rightPaint);
                r15.restore();
                r15.restore();
                return;
            L28:
                if (this.rightFolder.getCurrentWidth() >= AndroidUtilities.dp(64.0f)) goto L30;
                r15.save();
                r15.translate(r63 + r32, (r1 - (this.rightFolder2.getHeight() / 2.0f)) + AndroidUtilities.dp(1.0f));
                this.rightFolder2.draw(r15);
                r15.restore();
                r63 = r63 + (r32 + this.rightFolder2.getCurrentWidth());
                goto L30
            L16:
                if (this.leftFolder.getCurrentWidth() >= AndroidUtilities.dp(64.0f)) goto L18;
                r7 = r62 - (this.leftFolder2.getCurrentWidth() + r32);
                r15.save();
                r15.translate(r7, (r1 - (this.leftFolder2.getHeight() / 2.0f)) + AndroidUtilities.dp(1.0f));
                this.leftFolder2.draw(r15);
                r15.restore();
                goto L20
            L7:
                r5 = AndroidUtilities.dp(15.32f) + this.countText.getCurrentWidth();
                goto L8
            L9:
                r52 = r152;
                r22 = 0.0f;
                goto L11
            }

            public void setCount(int r4, boolean r5) {
                if (r5 == false) goto L4;
                this.countText.cancelAnimation();
            L4:
                AnimatedTextView.AnimatedTextDrawable r0 = this.countText;
                if (r4 <= 0) goto L7;
                String r42 = "+" + r4;
            L8:
                r0.setText(r42, r5);
                invalidate();
                return;
            L7:
                r42 = _UrlKt.FRAGMENT_ENCODE_SET;
                goto L8
            }

            @Override // android.view.View
            public boolean verifyDrawable(Drawable r2) {
                if (r2 != this.countText) goto L5;
                return true;
            L5:
                if (super.verifyDrawable(r2) == true) goto L11;
                return false;
            L11:
                return true;
            }
        }

        public TitleCell(FolderBottomSheet r22, Context r23, boolean r24, CharSequence r25, ArrayList<TLRPC.MessageEntity> r26, boolean r27) {
            this.this$0 = r22;
            super(r23);
            this.already = r24;
            String r4 = LocaleController.getString(R.string.FolderLinkPreviewLeft);
            String r8 = LocaleController.getString(R.string.FolderLinkPreviewRight);
            if (r25 != null) goto L6;
            CharSequence r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            FoldersPreview r0 = new FoldersPreview(this, r23, null, r4, r3, r26, r27, r8, null);
            this.preview = r0;
            addView(r0, LayoutHelper.createFrame(-1, 44.0f, 55, 0.0f, 17.33f, 0.0f, 0.0f));
            AnimatedEmojiSpan.TextViewEmojis r02 = new AnimatedEmojiSpan.TextViewEmojis(r23);
            this.titleTextView = r02;
            int r9 = Theme.key_windowBackgroundWhiteBlackText;
            r02.setTextColor(Theme.getColor(r9));
            this.titleTextView.setTextSize(1, 20.0f);
            this.titleTextView.setTypeface(AndroidUtilities.bold());
            this.titleTextView.setGravity(17);
            this.titleTextView.setLineSpacing(AndroidUtilities.dp(-1.0f), 1.0f);
            CharSequence r32 = Emoji.replaceEmoji(new SpannableStringBuilder(r25), this.titleTextView.getPaint().getFontMetricsInt(), false, 0.8f);
            this.title = r32;
            this.title = MessageObject.replaceAnimatedEmoji(r32, r26, this.titleTextView.getPaint().getFontMetricsInt(), false, 0.8f, 0);
            this.titleTextView.setText(r22.getTitle());
            AnimatedEmojiSpan.TextViewEmojis r03 = this.titleTextView;
            if (r27 == false) goto L10;
            int r33 = 26;
        L11:
            r03.setCacheType(r33);
            this.titleTextView.setEmojiColor(Theme.getColor(Theme.key_featuredStickers_addButton, FolderBottomSheet.access$000(r22)));
            addView(this.titleTextView, LayoutHelper.createFrame(-1, -2.0f, 48, 32.0f, 78.3f, 32.0f, 0.0f));
            AnimatedEmojiSpan.TextViewEmojis r04 = new AnimatedEmojiSpan.TextViewEmojis(r23);
            this.subtitleTextView = r04;
            r04.setTextColor(Theme.getColor(r9));
            this.subtitleTextView.setTextSize(1, 14.0f);
            this.subtitleTextView.setLines(2);
            this.subtitleTextView.setGravity(17);
            this.subtitleTextView.setLineSpacing(0.0f, 1.15f);
            addView(this.subtitleTextView, LayoutHelper.createFrame(-1, -2.0f, 48, 32.0f, 113.0f, 32.0f, 0.0f));
            setSelectedCount(0, false);
            return;
        L10:
            r33 = 0;
            goto L11
        L6:
            r3 = new SpannableStringBuilder(r25);
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(172.0f), TLObject.FLAG_30));
        }

        public void setSelectedCount(int r3, boolean r4) {
            if (FolderBottomSheet.m10722$$Nest$fgetdeleting(this.this$0) == false) goto L7;
            this.subtitleTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.FolderLinkSubtitleRemove, new Object[]{this.title})));
            return;
        L7:
            if (this.already == false) goto L26;
            FoldersPreview r32 = this.preview;
            if (FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0) == null) goto L11;
            int r42 = FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0).size();
        L12:
            r32.setCount(r42, false);
            if (FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0) != null) goto L15;
        L23:
            this.subtitleTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.FolderLinkSubtitleAlready, new Object[]{this.title})));
            return;
        L15:
            if (FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0).isEmpty() == true) goto L23;
            AnimatedEmojiSpan.TextViewEmojis r33 = this.subtitleTextView;
            if (FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0) == null) goto L20;
            int r43 = FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0).size();
        L21:
            r33.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralSpannable("FolderLinkSubtitleChats", r43, new CharSequence[]{this.title})));
            return;
        L20:
            r43 = 0;
            goto L21
        L11:
            r42 = 0;
            goto L12
        L26:
            if (FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0) != null) goto L28;
        L32:
            this.subtitleTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.FolderLinkSubtitleAlready, new Object[]{this.title})));
            return;
        L28:
            if (FolderBottomSheet.m10726$$Nest$fgetpeers(this.this$0).isEmpty() == true) goto L32;
            this.subtitleTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.FolderLinkSubtitle, new Object[]{this.title})));
        }
    }

    public static /* synthetic */ void $r8$lambda$9JxFwf3jckGVRUOp9bth4TFA8Io(DialogsActivity r0, Integer r1, Utilities.Callback r2, BaseFragment r3) {
        r0.scrollToFolder(r1.intValue());
        AndroidUtilities.runOnUIThread(new FolderBottomSheet$$ExternalSyntheticLambda20(r2, r3), 200);
    }

    public static /* synthetic */ void $r8$lambda$FVam0UYKvJXcBUYEoyS6XmUS32s(FolderBottomSheet r0, BaseFragment r1, ArrayList r2) {
        r0.lambda$onJoinButtonClicked$6(r1, r2);
    }

    /* renamed from: $r8$lambda$LnLGAcIF3RNRFVPwgtxe-wCpCVw, reason: not valid java name */
    public static /* synthetic */ void m10705$r8$lambda$LnLGAcIF3RNRFVPwgtxewCpCVw(INavigationLayout r4, Utilities.Callback r5, Integer r6) {
        List<BaseFragment> r42 = r4.getFragmentStack();
        boolean r1 = true;
        int r0 = r42.size() - 1;
        BaseFragment r2 = null;
    L3:
        if (r0 < 0) goto L14;
        r2 = r42.get(r0);
        if ((r2 instanceof DialogsActivity) == true) goto L14;
        if ((r2 instanceof MainTabsActivity) == true) goto L14;
        if (r1 == false) goto L11;
        r2.finishFragment();
        r1 = false;
    L12:
        r0 = r0 - 1;
        goto L3
    L11:
        r2.removeSelfFromStack();
    L14:
        if ((r2 instanceof MainTabsActivity) == false) goto L16;
        BaseFragment r43 = ((MainTabsActivity) r2).getDialogsActivity();
    L18:
        if ((r43 instanceof DialogsActivity) == false) goto L21;
        DialogsActivity r44 = (DialogsActivity) r43;
        r44.closeSearching();
        AndroidUtilities.runOnUIThread(new FolderBottomSheet$$ExternalSyntheticLambda17(r44, r6, r5, r2), 80);
        return;
    L21:
        r5.run(r2);
        return;
    L16:
        r43 = r2;
        goto L18
    }

    /* renamed from: $r8$lambda$OwUM-9QuAfhbW3sNLNqv8_-XJh8, reason: not valid java name */
    public static /* synthetic */ void m10706$r8$lambda$OwUM9QuAfhbW3sNLNqv8_XJh8(FolderBottomSheet r0, Pair r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$onJoinButtonClicked$9(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$PDbAH9s4f_byhnw3HdDxWrL8S-I, reason: not valid java name */
    public static /* synthetic */ void m10707$r8$lambda$PDbAH9s4f_byhnw3HdDxWrL8SI(BaseFragment r0, int r1, Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new FolderBottomSheet$$ExternalSyntheticLambda18(r0, r3, r1, r2));
    }

    public static /* synthetic */ void $r8$lambda$Q6SqAq4739Nre6Iv4QouY1XBwcA(FolderBottomSheet r0, TLRPC.TL_error r1, TLObject r2, Utilities.Callback r3) {
        r0.lambda$onJoinButtonClicked$17(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Zbq4eXWjeKM0x3YipiS99-7MpKA, reason: not valid java name */
    public static /* synthetic */ void m10708$r8$lambda$Zbq4eXWjeKM0x3YipiS997MpKA(FolderBottomSheet r0, ArrayList r1, BaseFragment r2) {
        r0.lambda$onJoinButtonClicked$11(r1, r2);
    }

    /* renamed from: $r8$lambda$ZlAbTkmUsTcUGY5d30-0i_1Fqjg, reason: not valid java name */
    public static /* synthetic */ void m10709$r8$lambda$ZlAbTkmUsTcUGY5d300i_1Fqjg(Utilities.Callback r0, BaseFragment r1) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$aBafx7DGQvMd4-EpKKgZS_yMW80, reason: not valid java name */
    public static /* synthetic */ void m10710$r8$lambda$aBafx7DGQvMd4EpKKgZS_yMW80(FolderBottomSheet r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$onJoinButtonClicked$18(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$cCDM0O0ir7WgxsrczN4I5-ZeImM, reason: not valid java name */
    public static /* synthetic */ void m10711$r8$lambda$cCDM0O0ir7WgxsrczN4I5ZeImM(BaseFragment r4, TLObject r5, int r6, Utilities.Callback r7) {
        if (r4.getParentActivity() != null) goto L6;
        return;
    L6:
        if ((r5 instanceof Vector) == false) goto L13;
        Vector r52 = (Vector) r5;
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L17:
        if (r1 >= r52.objects.size()) goto L12;
        r0.add(Long.valueOf(DialogObject.getPeerDialogId((TLRPC.Peer) r52.objects.get(r1))));     // Catch: Exception -> L16
        r1 = r1 + 1;
    L12:
        FolderBottomSheet r53 = new FolderBottomSheet(r4, r6, r0);
    L14:
        r53.setOnDone(r7);
        r4.showDialog(r53);
        return;
    L13:
        r53 = new FolderBottomSheet(r4, r6, null);
        goto L14
    }

    /* renamed from: $r8$lambda$dtwsdIYGJiKVSANVHh9w2tGF-dw, reason: not valid java name */
    public static /* synthetic */ void m10712$r8$lambda$dtwsdIYGJiKVSANVHh9w2tGFdw(FolderBottomSheet r0, HeaderCell r1, boolean r2) {
        r0.lambda$deselectAll$21(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ghEr4FSQkfj5jUZmnT84c9ENi6U(int r3, BaseFragment r4, Utilities.Callback r5) {
        TL_chatlists.TL_chatlists_getLeaveChatlistSuggestions r0 = new TL_chatlists.TL_chatlists_getLeaveChatlistSuggestions();
        TL_chatlists.TL_inputChatlistDialogFilter r1 = new TL_chatlists.TL_inputChatlistDialogFilter();
        r0.chatlist = r1;
        r1.filter_id = r3;
        r4.getConnectionsManager().sendRequest(r0, new FolderBottomSheet$$ExternalSyntheticLambda16(r4, r3, r5));
    }

    public static /* synthetic */ void $r8$lambda$gwNJNuDj2GaR6lmlciBFAZ7v1ms(Utilities.Callback r0, INavigationLayout r1, Integer r2) {
        r0.run(r1.getLastFragment());
    }

    public static /* synthetic */ void $r8$lambda$ln_cN4PXHcYuzkoWb8hzTC7CvqY(FolderBottomSheet r0, View r1) {
        r0.lambda$init$5(r1);
    }

    /* renamed from: $r8$lambda$lp5dkVKpHmrmggBQ9OG-JZjkB5o, reason: not valid java name */
    public static /* synthetic */ void m10713$r8$lambda$lp5dkVKpHmrmggBQ9OGJZjkB5o(Utilities.Callback r0, AlertDialog r1, int r2) {
        if (r0 == null) goto L5;
        r0.run(Boolean.FALSE);
        return;
    }

    public static /* synthetic */ void $r8$lambda$notM9K6nSLvJRSei0wwJl1RgUss(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$shNvy3xseWIvpwB_W27PIxBFtmc(FolderBottomSheet r0, boolean r1) {
        r0.lambda$updateHeaderCell$20(r1);
    }

    /* renamed from: $r8$lambda$ufHd6XIo-utwVNmYW008cPyL3xY, reason: not valid java name */
    public static /* synthetic */ void m10714$r8$lambda$ufHd6XIoutwVNmYW008cPyL3xY(FolderBottomSheet r0, Pair r1) {
        r0.lambda$onJoinButtonClicked$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$wnRCJsuR0kmuq7Y7u_8ZnBWa1v0(FolderBottomSheet r0, TLObject r1, Pair r2) {
        r0.lambda$onJoinButtonClicked$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xisl20ZieFPZF7BvQCnFPB5C_K0(FolderBottomSheet r0, Utilities.Callback r1, int r2, Boolean r3) {
        r0.lambda$onJoinButtonClicked$16(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$xnRclT29RF1UWNaUbDEV27ejKAo(FolderBottomSheet r0, View r1, int r2) {
        r0.lambda$onViewCreated$19(r1, r2);
    }

    /* renamed from: $r8$lambda$yAVj0ONEU8CKNFdOhez583a-V5Y, reason: not valid java name */
    public static /* synthetic */ void m10715$r8$lambda$yAVj0ONEU8CKNFdOhez583aV5Y(FolderBottomSheet r0, BaseFragment r1, ArrayList r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$onJoinButtonClicked$7(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetalreadyHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10716$$Nest$fgetalreadyHeaderRow(FolderBottomSheet r0) {
        return r0.alreadyHeaderRow;
    }

    /* renamed from: -$$Nest$fgetalreadyJoined, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10717$$Nest$fgetalreadyJoined(FolderBottomSheet r0) {
        return r0.alreadyJoined;
    }

    /* renamed from: -$$Nest$fgetalreadyPeers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10718$$Nest$fgetalreadyPeers(FolderBottomSheet r0) {
        return r0.alreadyPeers;
    }

    /* renamed from: -$$Nest$fgetalreadySectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10719$$Nest$fgetalreadySectionRow(FolderBottomSheet r0) {
        return r0.alreadySectionRow;
    }

    /* renamed from: -$$Nest$fgetalreadyUsersEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10720$$Nest$fgetalreadyUsersEndRow(FolderBottomSheet r0) {
        return r0.alreadyUsersEndRow;
    }

    /* renamed from: -$$Nest$fgetalreadyUsersStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10721$$Nest$fgetalreadyUsersStartRow(FolderBottomSheet r0) {
        return r0.alreadyUsersStartRow;
    }

    /* renamed from: -$$Nest$fgetdeleting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10722$$Nest$fgetdeleting(FolderBottomSheet r0) {
        return r0.deleting;
    }

    /* renamed from: -$$Nest$fgetescapedTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m10723$$Nest$fgetescapedTitle(FolderBottomSheet r0) {
        return r0.escapedTitle;
    }

    /* renamed from: -$$Nest$fgetheaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10724$$Nest$fgetheaderRow(FolderBottomSheet r0) {
        return r0.headerRow;
    }

    /* renamed from: -$$Nest$fgetinvite, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_chatlists.chatlist_ChatlistInvite m10725$$Nest$fgetinvite(FolderBottomSheet r0) {
        return r0.invite;
    }

    /* renamed from: -$$Nest$fgetpeers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10726$$Nest$fgetpeers(FolderBottomSheet r0) {
        return r0.peers;
    }

    /* renamed from: -$$Nest$fgetrowsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10727$$Nest$fgetrowsCount(FolderBottomSheet r0) {
        return r0.rowsCount;
    }

    /* renamed from: -$$Nest$fgetsectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10728$$Nest$fgetsectionRow(FolderBottomSheet r0) {
        return r0.sectionRow;
    }

    /* renamed from: -$$Nest$fgetselectedPeers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10729$$Nest$fgetselectedPeers(FolderBottomSheet r0) {
        return r0.selectedPeers;
    }

    /* renamed from: -$$Nest$fgettitleEntities, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10730$$Nest$fgettitleEntities(FolderBottomSheet r0) {
        return r0.titleEntities;
    }

    /* renamed from: -$$Nest$fgettitleNoanimate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10731$$Nest$fgettitleNoanimate(FolderBottomSheet r0) {
        return r0.titleNoanimate;
    }

    /* renamed from: -$$Nest$fgettitleRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10732$$Nest$fgettitleRow(FolderBottomSheet r0) {
        return r0.titleRow;
    }

    /* renamed from: -$$Nest$fgetupdates, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_chatlists.TL_chatlists_chatlistUpdates m10733$$Nest$fgetupdates(FolderBottomSheet r0) {
        return r0.updates;
    }

    /* renamed from: -$$Nest$fgetusersEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10734$$Nest$fgetusersEndRow(FolderBottomSheet r0) {
        return r0.usersEndRow;
    }

    /* renamed from: -$$Nest$fgetusersSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10735$$Nest$fgetusersSectionRow(FolderBottomSheet r0) {
        return r0.usersSectionRow;
    }

    /* renamed from: -$$Nest$fgetusersStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10736$$Nest$fgetusersStartRow(FolderBottomSheet r0) {
        return r0.usersStartRow;
    }

    /* renamed from: -$$Nest$fputheaderCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10737$$Nest$fputheaderCell(FolderBottomSheet r0, HeaderCell r1) {
        r0.headerCell = r1;
    }

    /* renamed from: -$$Nest$fputtitleCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10738$$Nest$fputtitleCell(FolderBottomSheet r0, TitleCell r1) {
        r0.titleCell = r1;
    }

    /* renamed from: -$$Nest$mupdateHeaderCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10739$$Nest$mupdateHeaderCell(FolderBottomSheet r0, boolean r1) {
        r0.updateHeaderCell(r1);
    }

    public FolderBottomSheet(BaseFragment r5, int r6, List<Long> r7) {
        int r0 = 0;
        super(r5, false, false);
        this.filterId = -1;
        this.title = _UrlKt.FRAGMENT_ENCODE_SET;
        this.titleEntities = new ArrayList();
        this.escapedTitle = _UrlKt.FRAGMENT_ENCODE_SET;
        this.alreadyJoined = new ArrayList();
        this.selectedPeers = new ArrayList();
        this.reqId = -1;
        this.shiftDp = -5;
        this.filterId = r6;
        this.deleting = true;
        this.peers = new ArrayList();
        this.selectedPeers.clear();
        if (r7 == null) goto L5;
        this.selectedPeers.addAll(r7);
    L5:
        ArrayList<MessagesController.DialogFilter> r72 = r5.getMessagesController().dialogFilters;
        if (r72 == null) goto L14;
        int r1 = 0;
    L9:
        if (r1 >= r72.size()) goto L14;
        if (r72.get(r1).id == r6) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        MessagesController.DialogFilter r62 = r72.get(r1);
    L15:
        if (r62 == null) goto L40;
        this.title = r62.name;
        this.titleEntities = r62.entities;
        this.titleNoanimate = r62.title_noanimate;
        int r73 = 0;
    L18:
        if (r73 >= this.selectedPeers.size()) goto L26;
        TLRPC.Peer r12 = r5.getMessagesController().getPeer(this.selectedPeers.get(r73).longValue());
        if ((r12 instanceof TLRPC.TL_peerChat) == false) goto L22;
    L23:
        this.peers.add(r12);
    L24:
        r73 = r73 + 1;
        goto L18
    L22:
        if ((r12 instanceof TLRPC.TL_peerChannel) == false) goto L24;
    L26:
        if (r0 >= r62.alwaysShow.size()) goto L40;
        Long r74 = r62.alwaysShow.get(r0);
        long r13 = r74.longValue();
        if (this.selectedPeers.contains(r74) == true) goto L39;
        TLRPC.Peer r75 = r5.getMessagesController().getPeer(r13);
        if ((r75 instanceof TLRPC.TL_peerChat) == false) goto L33;
    L34:
        TLRPC.Chat r14 = r5.getMessagesController().getChat(Long.valueOf(-r13));
        if (r14 != null) goto L37;
    L38:
        this.peers.add(r75);
        goto L39
    L37:
        if (ChatObject.isNotInChat(r14) == true) goto L39;
    L33:
        if ((r75 instanceof TLRPC.TL_peerChannel) == true) goto L34;
    L39:
        r0 = r0 + 1;
    L40:
        init();
        return;
    L14:
        r62 = null;
        goto L15
    }

    public FolderBottomSheet(BaseFragment r5, int r6, TL_chatlists.TL_chatlists_chatlistUpdates r7) {
        int r0 = 0;
        super(r5, false, false);
        this.filterId = -1;
        this.title = _UrlKt.FRAGMENT_ENCODE_SET;
        this.titleEntities = new ArrayList();
        this.escapedTitle = _UrlKt.FRAGMENT_ENCODE_SET;
        this.alreadyJoined = new ArrayList();
        ArrayList<Long> r2 = new ArrayList();
        this.selectedPeers = r2;
        this.reqId = -1;
        this.shiftDp = -5;
        this.filterId = r6;
        this.updates = r7;
        r2.clear();
        this.peers = r7.missing_peers;
        ArrayList<MessagesController.DialogFilter> r52 = r5.getMessagesController().dialogFilters;
        if (r52 != null) goto L5;
    L10:
        init();
        return;
    L5:
        if (r0 >= r52.size()) goto L10;
        if (r52.get(r0).id == r6) goto L8;
        r0 = r0 + 1;
        goto L5
    L8:
        this.title = r52.get(r0).name;
        goto L10
    }

    public FolderBottomSheet(BaseFragment r5, String r6, TL_chatlists.chatlist_ChatlistInvite r7) {
        int r0 = 0;
        super(r5, false, false);
        this.filterId = -1;
        this.title = _UrlKt.FRAGMENT_ENCODE_SET;
        this.titleEntities = new ArrayList();
        this.escapedTitle = _UrlKt.FRAGMENT_ENCODE_SET;
        this.alreadyJoined = new ArrayList();
        ArrayList<Long> r2 = new ArrayList();
        this.selectedPeers = r2;
        this.reqId = -1;
        this.shiftDp = -5;
        this.slug = r6;
        this.invite = r7;
        r2.clear();
        if ((r7 instanceof TL_chatlists.TL_chatlists_chatlistInvite) == false) goto L6;
        TL_chatlists.TL_chatlists_chatlistInvite r72 = (TL_chatlists.TL_chatlists_chatlistInvite) r7;
        TLRPC.TL_textWithEntities r52 = r72.title;
        this.title = r52.text;
        this.titleEntities = r52.entities;
        this.titleNoanimate = r72.title_noanimate;
        this.peers = r72.peers;
    L15:
        init();
        return;
    L6:
        if ((r7 instanceof TL_chatlists.TL_chatlists_chatlistInviteAlready) == false) goto L15;
        TL_chatlists.TL_chatlists_chatlistInviteAlready r73 = (TL_chatlists.TL_chatlists_chatlistInviteAlready) r7;
        this.peers = r73.missing_peers;
        this.alreadyPeers = r73.already_peers;
        this.filterId = r73.filter_id;
        ArrayList<MessagesController.DialogFilter> r53 = r5.getMessagesController().dialogFilters;
        if (r53 == null) goto L15;
    L10:
        if (r0 >= r53.size()) goto L15;
        MessagesController.DialogFilter r62 = r53.get(r0);
        if (r62.id == this.filterId) goto L13;
        r0 = r0 + 1;
        goto L10
    L13:
        this.title = r62.name;
        this.titleEntities = r62.entities;
        this.titleNoanimate = r62.title_noanimate;
        goto L15
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(FolderBottomSheet r0) {
        return r0.resourcesProvider;
    }

    private void announceSelection(boolean r5) {
        StringBuilder r0 = new StringBuilder();
        r0.append(LocaleController.formatPluralString("FilterInviteHeaderChats", this.selectedPeers.size(), new Object[0]));
        if (r5 == true) goto L5;
    L7:
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        r0.append(r4);
        AndroidUtilities.makeAccessibilityAnnouncement(r0.toString());
        return;
    L5:
        if (this.headerCell == null) goto L7;
        r4 = ", " + this.headerCell.actionTextView.getText();
        goto L8
    }

    private void deselectAll(HeaderCell r7, boolean r8) {
        this.selectedPeers.clear();
        this.selectedPeers.addAll(this.alreadyJoined);
        int r0 = 0;
        if (r8 == true) goto L11;
        int r1 = 0;
    L6:
        if (r1 >= this.peers.size()) goto L11;
        long r2 = DialogObject.getPeerDialogId(this.peers.get(r1));
        if (this.selectedPeers.contains(Long.valueOf(r2)) == true) goto L10;
        this.selectedPeers.add(Long.valueOf(r2));
    L10:
        r1 = r1 + 1;
    L11:
        updateCount(true);
        if (r8 == false) goto L15;
        int r22 = R.string.SelectAll;
    L16:
        r7.setAction(LocaleController.getString(r22), new FolderBottomSheet$$ExternalSyntheticLambda14(this, r7, r8));
        announceSelection(true);
    L18:
        if (r0 >= this.recyclerListView.getChildCount()) goto L25;
        View r72 = this.recyclerListView.getChildAt(r0);
        if ((r72 instanceof GroupCreateUserCell) == false) goto L24;
        Object r82 = r72.getTag();
        if ((r82 instanceof Long) == false) goto L24;
        ((GroupCreateUserCell) r72).setChecked(this.selectedPeers.contains((Long) r82), true);
    L24:
        r0 = r0 + 1;
        goto L18
    L25:
        return;
    L15:
        r22 = R.string.DeselectAll;
        goto L16
    }

    private void init() {
        this.escapedTitle = AndroidUtilities.replaceCharSequence("*", this.title, "✱");
        if (this.peers == null) goto L29;
        int r0 = 0;
    L6:
        if (r0 >= this.peers.size()) goto L29;
        TLRPC.Peer r3 = this.peers.get(r0);
        if (r3 == null) goto L28;
        if ((r3 instanceof TLRPC.TL_peerUser) == false) goto L14;
        long r32 = r3.user_id;
        boolean r7 = false;
    L22:
        if (r32 == 0) goto L28;
        if (this.deleting == true) goto L28;
        if (r7 == false) goto L27;
        this.alreadyJoined.add(Long.valueOf(r32));
    L27:
        this.selectedPeers.add(Long.valueOf(r32));
        goto L28
    L14:
        if ((r3 instanceof TLRPC.TL_peerChat) == false) goto L18;
        r32 = -r3.chat_id;
        boolean r72 = ChatObject.isNotInChat(getBaseFragment().getMessagesController().getChat(Long.valueOf(-r32)));
    L16:
        r7 = !r72;
        goto L22
    L18:
        if ((r3 instanceof TLRPC.TL_peerChannel) == false) goto L20;
        r32 = -r3.channel_id;
        r72 = ChatObject.isNotInChat(getBaseFragment().getMessagesController().getChat(Long.valueOf(-r32)));
        goto L16
    L20:
        r7 = false;
        r32 = 0;
    L28:
        r0 = r0 + 1;
    L29:
        this.rowsCount = 1;
        this.titleRow = 0;
        ArrayList<TLRPC.Peer> r02 = this.peers;
        if (r02 != null) goto L32;
    L34:
        this.sectionRow = -1;
        this.headerRow = -1;
        this.usersStartRow = -1;
        this.usersEndRow = -1;
    L35:
        int r03 = this.rowsCount;
        this.rowsCount = r03 + 1;
        this.usersSectionRow = r03;
        ArrayList<TLRPC.Peer> r04 = this.alreadyPeers;
        if (r04 != null) goto L38;
    L40:
        this.alreadyHeaderRow = -1;
        this.alreadyUsersStartRow = -1;
        this.alreadyUsersEndRow = -1;
        this.alreadySectionRow = -1;
    L41:
        Button r05 = new Button(getContext(), _UrlKt.FRAGMENT_ENCODE_SET);
        this.button = r05;
        r05.setOnClickListener(new FolderBottomSheet$$ExternalSyntheticLambda0(this));
        this.containerView.addView(this.button, LayoutHelper.createFrame(-1, 48.0f, 87, 16.0f, 10.0f, 16.0f, 10.0f));
        View r06 = new View(getContext());
        this.buttonShadow = r06;
        r06.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.containerView.addView(this.buttonShadow, LayoutHelper.createFrame(-1, 1.0f / AndroidUtilities.density, 87, 6.0f, 0.0f, 6.0f, 68.0f));
        RecyclerListView r07 = this.recyclerListView;
        int r33 = AndroidUtilities.dp(6.0f);
        int r2 = AndroidUtilities.dp(6.0f);
        if (this.button == null) goto L44;
        float r4 = 68.0f;
    L45:
        r07.setPadding(r33, 0, r2, AndroidUtilities.dp(r4));
        FrameLayout r08 = new FrameLayout(getContext());
        this.bulletinContainer = r08;
        this.containerView.addView(r08, LayoutHelper.createFrame(-1, 100.0f, 87, 6.0f, 0.0f, 6.0f, 68.0f));
        fixNavigationBar(Theme.getColor(Theme.key_dialogBackground));
        updateCount(false);
        this.actionBar.setTitle(getTitle());
        return;
    L44:
        r4 = 0.0f;
        goto L45
    L38:
        if (r04.isEmpty() == true) goto L40;
        int r09 = this.rowsCount;
        int r22 = r09 + 1;
        this.rowsCount = r22;
        this.alreadyHeaderRow = r09;
        this.alreadyUsersStartRow = r22;
        int r23 = r22 + this.alreadyPeers.size();
        this.alreadyUsersEndRow = r23;
        this.rowsCount = r23 + 1;
        this.alreadySectionRow = r23;
        goto L41
    L32:
        if (r02.isEmpty() == true) goto L34;
        int r010 = this.rowsCount;
        int r34 = r010 + 1;
        this.sectionRow = r010;
        int r011 = r010 + 2;
        this.rowsCount = r011;
        this.headerRow = r34;
        this.usersStartRow = r011;
        int r012 = r011 + this.peers.size();
        this.rowsCount = r012;
        this.usersEndRow = r012;
        goto L35
    }

    private /* synthetic */ void lambda$deselectAll$21(HeaderCell r1, boolean r2) {
        deselectAll(r1, !r2);
    }

    private /* synthetic */ void lambda$init$5(View r1) {
        onJoinButtonClicked();
    }

    private /* synthetic */ void lambda$onJoinButtonClicked$10(TLObject r3, Pair r4) {
        this.reqId = getBaseFragment().getConnectionsManager().sendRequest(r3, new FolderBottomSheet$$ExternalSyntheticLambda12(this, r4));
    }

    private /* synthetic */ void lambda$onJoinButtonClicked$11(ArrayList r6, BaseFragment r7) {
        if (this.updates == null) goto L5;
    L9:
        BulletinFactory r72 = BulletinFactory.of(r7);
        int r0 = R.raw.folder_in;
        SpannableStringBuilder r3 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.FolderLinkUpdatedTitle, new Object[]{this.escapedTitle}));
        if (r6.size() > 0) goto L12;
        String r5 = LocaleController.formatPluralString("FolderLinkUpdatedSubtitle", this.alreadyJoined.size(), new Object[0]);
    L13:
        r72.createSimpleBulletin(r0, r3, r5).setDuration(5000).show();
        return;
    L12:
        r5 = LocaleController.formatPluralString("FolderLinkUpdatedJoinedSubtitle", r6.size(), new Object[0]);
        goto L13
    L5:
        if ((this.invite instanceof TL_chatlists.TL_chatlists_chatlistInviteAlready) == true) goto L9;
        BulletinFactory.of(r7).createSimpleBulletin(R.raw.contact_check, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.FolderLinkAddedTitle, new Object[]{this.escapedTitle})), LocaleController.formatPluralString("FolderLinkAddedSubtitle", r6.size(), new Object[0])).setDuration(5000).show();
    }

    private /* synthetic */ void lambda$onJoinButtonClicked$16(Utilities.Callback r1, int r2, Boolean r3) {
        this.success = r3.booleanValue();
        dismiss();
        r1.run(Integer.valueOf(r2));
    }

    private /* synthetic */ void lambda$onJoinButtonClicked$17(TLRPC.TL_error r4, TLObject r5, Utilities.Callback r6) {
        int r0 = -1;
        this.reqId = -1;
        int r1 = 0;
        if (FilterCreateActivity.processErrors(r4, getBaseFragment(), BulletinFactory.of(getBaseFragment())) == false) goto L27;
        if (r5 == null) goto L27;
        if ((r5 instanceof TLRPC.Updates) == false) goto L19;
        TLRPC.Updates r52 = (TLRPC.Updates) r5;
        ArrayList<TLRPC.Update> r42 = r52.updates;
        if (r42.isEmpty() == false) goto L10;
        TLRPC.Update r43 = r52.update;
        if ((r43 instanceof TL_update.TL_updateDialogFilter) == false) goto L19;
        r0 = ((TL_update.TL_updateDialogFilter) r43).id;
    L10:
        if (r1 >= r42.size()) goto L19;
        if ((r42.get(r1) instanceof TL_update.TL_updateDialogFilter) == true) goto L13;
        r1 = r1 + 1;
        goto L10
    L13:
        r0 = ((TL_update.TL_updateDialogFilter) r42.get(r1)).id;
    L19:
        if ((this.invite instanceof TL_chatlists.TL_chatlists_chatlistInvite) == false) goto L23;
        getBaseFragment().getMessagesController().loadRemoteFilters(true, new FolderBottomSheet$$ExternalSyntheticLambda21(this, r6, r0));
        return;
    L23:
        if (this.updates == null) goto L25;
        getBaseFragment().getMessagesController().checkChatlistFolderUpdate(this.filterId, true);
    L25:
        this.success = true;
        dismiss();
        r6.run(Integer.valueOf(r0));
        return;
    L27:
        this.button.setLoading(false);
    }

    private /* synthetic */ void lambda$onJoinButtonClicked$18(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new FolderBottomSheet$$ExternalSyntheticLambda13(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$onJoinButtonClicked$6(BaseFragment r5, ArrayList r6) {
        this.reqId = -1;
        BulletinFactory.of(r5).createSimpleBulletin(R.raw.ic_delete, LocaleController.formatString(R.string.FolderLinkDeletedTitle, new Object[]{this.title}), LocaleController.formatPluralString("FolderLinkDeletedSubtitle", r6.size(), new Object[0])).setDuration(5000).show();
        this.success = true;
        dismiss();
        getBaseFragment().getMessagesController().invalidateChatlistFolderUpdate(this.filterId);
    }

    private /* synthetic */ void lambda$onJoinButtonClicked$7(BaseFragment r1, ArrayList r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new FolderBottomSheet$$ExternalSyntheticLambda15(this, r1, r2));
    }

    private /* synthetic */ void lambda$onJoinButtonClicked$8(Pair r2) {
        this.reqId = -1;
        ((Runnable) r2.first).run();
    }

    private /* synthetic */ void lambda$onJoinButtonClicked$9(Pair r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new FolderBottomSheet$$ExternalSyntheticLambda19(this, r1));
    }

    private /* synthetic */ void lambda$onViewCreated$19(View r8, int r9) {
        if ((r8 instanceof GroupCreateUserCell) == false) goto L31;
        int r92 = (r9 - 1) - this.usersStartRow;
        if (r92 >= 0) goto L7;
        return;
    L7:
        if (r92 >= this.peers.size()) goto L34;
        long r1 = DialogObject.getPeerDialogId(this.peers.get(r92));
        if (this.selectedPeers.contains(Long.valueOf(r1)) == true) goto L12;
        this.selectedPeers.add(Long.valueOf(r1));
        ((GroupCreateUserCell) r8).setChecked(true, true);
    L29:
        updateCount(true);
        updateHeaderCell(true);
        announceSelection(false);
        return;
    L12:
        if (this.alreadyJoined.contains(Long.valueOf(r1)) == false) goto L27;
        int r93 = -this.shiftDp;
        this.shiftDp = r93;
        AndroidUtilities.shakeViewSpring(r8, r93);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        ArrayList r82 = new ArrayList();
        if (r1 < 0) goto L16;
        r82.add(getBaseFragment().getMessagesController().getUser(Long.valueOf(r1)));
        String r94 = "beep boop.";
    L22:
        if (this.lastClickedDialogId == r1) goto L24;
    L25:
        this.lastClickedDialogId = r1;
        this.lastClicked = System.currentTimeMillis();
        BulletinFactory.of(this.bulletinContainer, null).createChatsBulletin(r82, r94, null).setDuration(1500).show();
        return;
    L24:
        if ((System.currentTimeMillis() - this.lastClicked) > 1500) goto L25;
        return;
    L16:
        TLRPC.Chat r95 = getBaseFragment().getMessagesController().getChat(Long.valueOf(-r1));
        if (ChatObject.isChannelAndNotMegaGroup(r95) == false) goto L19;
        String r0 = LocaleController.getString(R.string.FolderLinkAlreadySubscribed);
    L20:
        r82.add(r95);
        r94 = r0;
        goto L22
    L19:
        r0 = LocaleController.getString(R.string.FolderLinkAlreadyJoined);
        goto L20
    L27:
        this.selectedPeers.remove(Long.valueOf(r1));
        ((GroupCreateUserCell) r8).setChecked(false, true);
        goto L29
    L34:
        return;
    }

    private /* synthetic */ void lambda$updateHeaderCell$20(boolean r2) {
        deselectAll(this.headerCell, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void onJoinButtonClicked() {
        Button r0 = this.button;
        if (r0 != null) goto L5;
    L7:
        ArrayList<TLRPC.Peer> r02 = this.peers;
        if (r02 != null) goto L12;
        dismiss();
        return;
    L12:
        if (r02.isEmpty() == false) goto L18;
        if (this.deleting == true) goto L18;
        dismiss();
        return;
    L18:
        if (this.selectedPeers.isEmpty() == true) goto L20;
    L23:
        ArrayList r03 = new ArrayList();
        int r1 = 0;
        int r2 = 0;
    L25:
        if (r2 >= this.peers.size()) goto L30;
        long r3 = DialogObject.getPeerDialogId(this.peers.get(r2));
        if (this.selectedPeers.contains(Long.valueOf(r3)) == false) goto L29;
        r03.add(getBaseFragment().getMessagesController().getInputPeer(r3));
    L29:
        r2 = r2 + 1;
        goto L25
    L30:
        UndoView r32 = null;
        if (this.deleting == false) goto L34;
        TL_chatlists.TL_chatlists_leaveChatlist r22 = new TL_chatlists.TL_chatlists_leaveChatlist();
        TL_chatlists.TL_inputChatlistDialogFilter r4 = new TL_chatlists.TL_inputChatlistDialogFilter();
        r22.chatlist = r4;
        r4.filter_id = this.filterId;
        r22.peers.addAll(r03);
        TL_chatlists.TL_chatlists_joinChatlistInvite r23 = r22;
    L47:
        INavigationLayout r42 = getBaseFragment().getParentLayout();
        if (this.deleting == false) goto L76;
        if (r42 == null) goto L98;
        BaseFragment r5 = r42.getLastFragment();
        if ((r5 instanceof ChatActivity) == false) goto L55;
        r32 = ((ChatActivity) r5).getUndoView();
    L53:
        UndoView r7 = r32;
        if (r7 != null) goto L70;
        this.button.setLoading(true);
        this.reqId = getBaseFragment().getConnectionsManager().sendRequest(r23, new FolderBottomSheet$$ExternalSyntheticLambda6(this, r5, r03));
        return;
    L70:
        ArrayList<Long> r33 = new ArrayList();
    L72:
        if (r1 >= r03.size()) goto L74;
        r33.add(Long.valueOf(DialogObject.getPeerDialogId((TLRPC.InputPeer) r03.get(r1))));
        r1 = r1 + 1;
        goto L72
    L74:
        Pair<Runnable, Runnable> r12 = getBaseFragment().getMessagesController().removeFolderTemporarily(this.filterId, r33);
        r7.showWithAction(0, 88, this.title, Integer.valueOf(r03.size()), new FolderBottomSheet$$ExternalSyntheticLambda7(this, r23, r12), (Runnable) r12.second);
        this.success = true;
        dismiss();
        getBaseFragment().getMessagesController().invalidateChatlistFolderUpdate(this.filterId);
        return;
    L55:
        if ((r5 instanceof DialogsActivity) == false) goto L58;
        r32 = ((DialogsActivity) r5).getUndoView();
        goto L53
    L58:
        if ((r5 instanceof FiltersSetupActivity) == false) goto L61;
        r32 = ((FiltersSetupActivity) r5).getUndoView();
        goto L53
    L61:
        if ((r5 instanceof FilterCreateActivity) == false) goto L53;
        List<BaseFragment> r43 = r42.getFragmentStack();
        if (r43.size() < 2) goto L53;
        if ((r43.get(r43.size() - 2) instanceof FiltersSetupActivity) == false) goto L53;
        FiltersSetupActivity r34 = (FiltersSetupActivity) r43.get(r43.size() - 2);
        r5.finishFragment();
        r32 = r34.getUndoView();
        goto L53
    L98:
        return;
    L76:
        if (r42 == null) goto L99;
        FolderBottomSheet$$ExternalSyntheticLambda8 r35 = new FolderBottomSheet$$ExternalSyntheticLambda8(this, r03);
        if (this.updates == null) goto L80;
        Utilities.Callback r52 = new FolderBottomSheet$$ExternalSyntheticLambda9(r35, r42);
    L81:
        int r36 = 0;
    L83:
        if (r36 >= r03.size()) goto L90;
        if (this.alreadyJoined.contains(Long.valueOf(DialogObject.getPeerDialogId((TLRPC.InputPeer) r03.get(r36)))) == false) goto L86;
        r36 = r36 + 1;
        goto L83
    L86:
        boolean[] r04 = new boolean[1];
        getBaseFragment().getMessagesController().ensureFolderDialogExists(1, r04);
        if (r04[0] == false) goto L90;
        getBaseFragment().getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    L90:
        this.button.setLoading(true);
        this.reqId = getBaseFragment().getConnectionsManager().sendRequest(r23, new FolderBottomSheet$$ExternalSyntheticLambda11(this, r52));
        return;
    L80:
        r52 = new FolderBottomSheet$$ExternalSyntheticLambda10(r42, r35);
        goto L81
    L99:
        return;
    L34:
        if (this.updates == null) goto L41;
        if (r03.isEmpty() == false) goto L39;
        TL_chatlists.TL_chatlists_hideChatlistUpdates r05 = new TL_chatlists.TL_chatlists_hideChatlistUpdates();
        TL_chatlists.TL_inputChatlistDialogFilter r13 = new TL_chatlists.TL_inputChatlistDialogFilter();
        r05.chatlist = r13;
        r13.filter_id = this.filterId;
        getBaseFragment().getConnectionsManager().sendRequest(r05, null);
        getBaseFragment().getMessagesController().invalidateChatlistFolderUpdate(this.filterId);
        dismiss();
        return;
    L39:
        TL_chatlists.TL_chatlists_joinChatlistUpdates r24 = new TL_chatlists.TL_chatlists_joinChatlistUpdates();
        TL_chatlists.TL_inputChatlistDialogFilter r44 = new TL_chatlists.TL_inputChatlistDialogFilter();
        r24.chatlist = r44;
        r44.filter_id = this.filterId;
        r24.peers.addAll(r03);
        r23 = r24;
        goto L47
    L41:
        if ((this.invite instanceof TL_chatlists.TL_chatlists_chatlistInviteAlready) == true) goto L43;
    L46:
        TL_chatlists.TL_chatlists_joinChatlistInvite r25 = new TL_chatlists.TL_chatlists_joinChatlistInvite();
        r25.slug = this.slug;
        r25.peers.addAll(r03);
        r23 = r25;
        goto L47
    L43:
        if (r03.isEmpty() == false) goto L46;
        dismiss();
        return;
    L20:
        if ((this.invite instanceof TL_chatlists.TL_chatlists_chatlistInvite) == false) goto L23;
        Button r06 = this.button;
        int r14 = -this.shiftDp;
        this.shiftDp = r14;
        AndroidUtilities.shakeViewSpring(r06, r14);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return;
    L5:
        if (r0.isLoading() == false) goto L7;
    }

    public static void showForDeletion(BaseFragment r3, int r4, Utilities.Callback<Boolean> r5) {
        ArrayList<MessagesController.DialogFilter> r0 = r3.getMessagesController().dialogFilters;
        if (r0 == null) goto L11;
        int r1 = 0;
    L6:
        if (r1 >= r0.size()) goto L11;
        if (r0.get(r1).id == r4) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        MessagesController.DialogFilter r02 = r0.get(r1);
    L12:
        FolderBottomSheet$$ExternalSyntheticLambda3 r12 = new FolderBottomSheet$$ExternalSyntheticLambda3(r4, r3, r5);
        if (r02 != null) goto L15;
    L20:
        r12.run();
        return;
    L15:
        if (r02.isMyChatlist() == false) goto L20;
        AlertDialog r42 = new AlertDialog.Builder(r3.getContext()).setTitle(LocaleController.getString(R.string.FilterDelete)).setMessage(LocaleController.getString(R.string.FilterDeleteAlertLinks)).setNegativeButton(LocaleController.getString(R.string.Cancel), new FolderBottomSheet$$ExternalSyntheticLambda4(r5)).setPositiveButton(LocaleController.getString(R.string.Delete), new FolderBottomSheet$$ExternalSyntheticLambda5(r12)).create();
        r3.showDialog(r42);
        TextView r32 = (TextView) r42.getButton(-1);
        if (r32 == null) goto L24;
        r32.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L24:
        return;
    L11:
        r02 = null;
        goto L12
    }

    private void updateHeaderCell(boolean r5) {
        HeaderCell r52 = this.headerCell;
        if (r52 != null) goto L5;
        return;
    L5:
        boolean r0 = this.deleting;
        ArrayList<TLRPC.Peer> r1 = this.peers;
        boolean r2 = false;
        if (r0 == false) goto L8;
        r52.setText(LocaleController.formatPluralString("FolderLinkHeaderChatsQuit", r1.size(), new Object[0]), false);
    L9:
        ArrayList<TLRPC.Peer> r53 = this.peers;
        if (r53 != null) goto L12;
    L23:
        this.headerCell.setAction(_UrlKt.FRAGMENT_ENCODE_SET, null);
        return;
    L12:
        if ((r53.size() - this.alreadyJoined.size()) <= 1) goto L23;
        if (this.selectedPeers.size() < (this.peers.size() - this.alreadyJoined.size())) goto L16;
        r2 = true;
    L16:
        HeaderCell r54 = this.headerCell;
        if (r2 == false) goto L20;
        int r02 = R.string.DeselectAll;
    L21:
        r54.setAction(LocaleController.getString(r02), new FolderBottomSheet$$ExternalSyntheticLambda2(this, r2));
        return;
    L20:
        r02 = R.string.SelectAll;
        goto L21
    L8:
        r52.setText(LocaleController.formatPluralString("FolderLinkHeaderChatsJoin", r1.size(), new Object[0]), false);
        goto L9
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r1) {
        return new AnonymousClass1(this);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        if (this.reqId < 0) goto L5;
        getBaseFragment().getConnectionsManager().cancelRequest(this.reqId, true);
    L5:
        Utilities.Callback<Boolean> r0 = this.onDone;
        if (r0 == null) goto L9;
        r0.run(Boolean.valueOf(this.success));
        this.onDone = null;
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        if (this.deleting == false) goto L7;
        return LocaleController.getString(R.string.FolderLinkTitleRemove);
    L7:
        if ((this.invite instanceof TL_chatlists.TL_chatlists_chatlistInvite) == true) goto L9;
        ArrayList<TLRPC.Peer> r1 = this.peers;
        if (r1 == null) goto L18;
        if (r1.isEmpty() == true) goto L18;
        return LocaleController.getString(R.string.FolderLinkTitleAddChats);
    L18:
        return LocaleController.getString(R.string.FolderLinkTitleAlready);
    L9:
        return LocaleController.getString(R.string.FolderLinkTitleAdd);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onViewCreated(FrameLayout r5) {
        super.onViewCreated(r5);
        this.recyclerListView.setOverScrollMode(2);
        RecyclerListView r52 = this.recyclerListView;
        int r1 = AndroidUtilities.dp(6.0f);
        int r0 = AndroidUtilities.dp(6.0f);
        if (this.button == null) goto L5;
        float r2 = 68.0f;
    L6:
        r52.setPadding(r1, 0, r0, AndroidUtilities.dp(r2));
        this.recyclerListView.setOnItemClickListener(new FolderBottomSheet$$ExternalSyntheticLambda1(this));
        return;
    L5:
        r2 = 0.0f;
        goto L6
    }

    public void setOnDone(Utilities.Callback<Boolean> r1) {
        this.onDone = r1;
    }

    public void updateCount(boolean r6) {
        int r0 = this.selectedPeers.size();
        Button r1 = this.button;
        if (r1 != null) goto L5;
    L31:
        TitleCell r5 = this.titleCell;
        if (r5 == null) goto L35;
        r5.setSelectedCount(r0, r6);
        return;
    L35:
        return;
    L5:
        if (this.deleting == false) goto L11;
        if (r0 <= 0) goto L9;
        int r2 = R.string.FolderLinkButtonRemoveChats;
    L10:
        r1.setText(LocaleController.getString(r2), r6);
    L28:
        this.button.setCount(r0, r6);
        if ((this.invite instanceof TL_chatlists.TL_chatlists_chatlistInvite) == false) goto L31;
        this.button.setEnabled(!this.selectedPeers.isEmpty());
        goto L31
    L9:
        r2 = R.string.FolderLinkButtonRemove;
        goto L10
    L11:
        ArrayList<TLRPC.Peer> r12 = this.peers;
        if (r12 != null) goto L14;
    L27:
        this.button.setText(LocaleController.getString(R.string.OK), r6);
        goto L28
    L14:
        if (r12.isEmpty() == true) goto L27;
        int r22 = 0;
        if ((this.invite instanceof TL_chatlists.TL_chatlists_chatlistInvite) == false) goto L22;
        this.button.setText(LocaleController.formatSpannable(R.string.FolderLinkButtonAdd, new Object[]{MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(this.title, this.button.getTextPaint().getFontMetricsInt(), false), this.titleEntities, this.button.getTextPaint().getFontMetricsInt())}), r6);
        Button r13 = this.button;
        if (this.titleNoanimate == false) goto L21;
        r22 = 26;
    L21:
        r13.setEmojiCacheType(r22);
        goto L28
    L22:
        Button r14 = this.button;
        if (r0 <= 0) goto L25;
        String r23 = LocaleController.formatPluralString("FolderLinkButtonJoinPlural", r0, new Object[0]);
    L26:
        r14.setText(r23, r6);
        goto L28
    L25:
        r23 = LocaleController.getString(R.string.FolderLinkButtonNone);
        goto L26
    }
}
