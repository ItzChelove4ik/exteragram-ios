package org.telegram.ui.Components.Premium;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.AdminedChannelCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.ChannelColorActivity;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ChatEditActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.AvatarsImageView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FireworksOverlay;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Loadable;
import org.telegram.ui.Components.LoginOrView;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.Premium.boosts.BoostCounterView;
import org.telegram.ui.Components.Premium.boosts.BoostDialogs;
import org.telegram.ui.Components.Premium.boosts.BoostPagerBottomSheet;
import org.telegram.ui.Components.Premium.boosts.BoostRepository;
import org.telegram.ui.Components.Premium.boosts.ReassignBoostBottomSheet;
import org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity;
import org.telegram.ui.Components.RecyclerItemsEnterAnimator;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.GroupColorActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.StatisticActivity;
import org.telegram.ui.Stories.ChannelBoostUtilities;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.StoryRecorder;

/* loaded from: classes7.dex */
public class LimitReachedBottomSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
    TextView actionBtn;
    ArrayList<BoostFeature> boostFeatures;
    int boostFeaturesStartRow;
    ButtonWithCounterView boostMiniBtn;
    private ButtonWithCounterView boostToUnlockGroupBtn;
    private TL_stories.TL_premium_boostsStatus boostsStatus;
    int bottomRow;
    private ChannelBoostsController.CanApplyBoost canApplyBoost;
    private boolean canSendLink;
    int chatEndRow;
    private ChatMessageCell chatMessageCell;
    int chatStartRow;
    ArrayList<TLRPC.Chat> chats;
    int chatsTitleRow;
    private int currentValue;
    private long dialogId;
    View divider;
    int dividerRow;
    int emptyViewDividerRow;
    RecyclerItemsEnterAnimator enterAnimator;
    FireworksOverlay fireworksOverlay;
    private String forceLink;
    private TLRPC.Chat fromChat;
    int headerRow;
    private HeaderView headerView;
    private ArrayList<TLRPC.Chat> inactiveChats;
    private ArrayList<String> inactiveChatsSignatures;
    private boolean isCurrentChat;
    private boolean isVeryLargeFile;
    LimitParams limitParams;
    LimitPreviewView limitPreviewView;
    private int linkRow;
    private boolean loading;
    boolean loadingAdminedChannels;
    int loadingRow;
    private boolean lockInvalidation;
    public Runnable onShowPremiumScreenRunnable;
    public Runnable onSuccessRunnable;
    BaseFragment parentFragment;
    public boolean parentIsChannel;
    boolean premiumButtonSetSubscribe;
    PremiumButtonView premiumButtonView;
    private ArrayList<Long> premiumInviteBlockedUsers;
    private ArrayList<Long> premiumMessagingBlockedUsers;
    private int requiredLvl;
    private ArrayList<TLRPC.User> restrictedUsers;
    int rowCount;
    HashSet<Object> selectedChats;
    private int shiftDp;
    Runnable statisticClickRunnable;
    protected int storiesCount;
    final int type;

    /* renamed from: org.telegram.ui.Components.Premium.LimitReachedBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends PremiumButtonView {
        final /* synthetic */ LimitReachedBottomSheet this$0;

        public AnonymousClass1(LimitReachedBottomSheet r1, Context r2, boolean r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.view.View
        public void invalidate() {
            if (LimitReachedBottomSheet.m12005$$Nest$fgetlockInvalidation(this.this$0) == false) goto L5;
            return;
        L5:
            super.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.LimitReachedBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ LimitReachedBottomSheet this$0;

        public AnonymousClass2(LimitReachedBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            LimitReachedBottomSheet r0 = this.this$0;
            if ((r0.chatEndRow - r0.chatStartRow) <= 1) goto L9;
            Paint r02 = Theme.getThemePaint("paintDivider", LimitReachedBottomSheet.access$000(r0));
            if (r02 != null) goto L7;
            r02 = Theme.dividerPaint;
        L7:
            r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), 1.0f, r02);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.LimitReachedBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends View {
        final /* synthetic */ LimitReachedBottomSheet this$0;
        final /* synthetic */ Drawable val$boostDrawable;
        final /* synthetic */ Paint val$paint;

        public AnonymousClass3(LimitReachedBottomSheet r1, Context r2, Paint r3, Drawable r4) {
            this.this$0 = r1;
            this.val$paint = r3;
            this.val$boostDrawable = r4;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r13) {
            float r0 = getMeasuredWidth() / 2.0f;
            float r2 = getMeasuredHeight() / 2.0f;
            r13.drawCircle(r0, r2, getMeasuredWidth() / 2.0f, this.val$paint);
            PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, getMeasuredWidth(), getMeasuredHeight(), -AndroidUtilities.dp(10.0f), 0.0f);
            r13.drawCircle(r0, r2, (getMeasuredWidth() / 2.0f) - AndroidUtilities.dp(2.0f), PremiumGradient.getInstance().getMainGradientPaint());
            float r3 = AndroidUtilities.dp(18.0f) / 2.0f;
            this.val$boostDrawable.setBounds((int) (r0 - r3), (int) (r2 - r3), (int) (r0 + r3), (int) (r2 + r3));
            this.val$boostDrawable.draw(r13);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.LimitReachedBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends Visibility {
        final /* synthetic */ LimitReachedBottomSheet this$0;

        public AnonymousClass4(LimitReachedBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.transition.Visibility
        public Animator onAppear(ViewGroup r5, View r6, TransitionValues r7, TransitionValues r8) {
            AnimatorSet r4 = new AnimatorSet();
            r4.playTogether(new Animator[]{ObjectAnimator.ofFloat(r6, View.ALPHA, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(r6, View.TRANSLATION_Y, new float[]{AndroidUtilities.dp(20.0f), 0.0f})});
            r4.setInterpolator(CubicBezierInterpolator.DEFAULT);
            return r4;
        }

        @Override // android.transition.Visibility
        public Animator onDisappear(ViewGroup r6, View r7, TransitionValues r8, TransitionValues r9) {
            AnimatorSet r5 = new AnimatorSet();
            r5.playTogether(new Animator[]{ObjectAnimator.ofFloat(r7, View.ALPHA, new float[]{r7.getAlpha(), 0.0f}), ObjectAnimator.ofFloat(r7, View.TRANSLATION_Y, new float[]{0.0f, -AndroidUtilities.dp(20.0f)})});
            r5.setInterpolator(CubicBezierInterpolator.DEFAULT);
            return r5;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.LimitReachedBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ LimitReachedBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.Premium.LimitReachedBottomSheet$5$1, reason: invalid class name */
        public class AnonymousClass1 extends ClickableSpan {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass1(AnonymousClass5 r1) {
                this.this$1 = r1;
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View r3) {
                BoostPagerBottomSheet.show(this.this$1.this$0.getBaseFragment(), LimitReachedBottomSheet.m11998$$Nest$fgetdialogId(this.this$1.this$0), LimitReachedBottomSheet.access$400(this.this$1.this$0));
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint r2) {
                super.updateDrawState(r2);
                r2.setUnderlineText(false);
                r2.setColor(Theme.getColor(Theme.key_chat_messageLinkIn, LimitReachedBottomSheet.access$300(this.this$1.this$0)));
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.LimitReachedBottomSheet$5$2, reason: invalid class name */
        public class AnonymousClass2 implements View.OnClickListener {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass2(AnonymousClass5 r1) {
                this.this$1 = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View r2) {
                AdminedChannelCell r22 = (AdminedChannelCell) r2.getParent();
                ArrayList r0 = new ArrayList();
                r0.add(r22.getCurrentChannel());
                LimitReachedBottomSheet.m12017$$Nest$mrevokeLinks(this.this$1.this$0, r0);
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.LimitReachedBottomSheet$5$3, reason: invalid class name */
        public class AnonymousClass3 extends View {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass3(AnonymousClass5 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(16.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: $r8$lambda$5AJmKvxSdffWGGStnb-53oaNAi4, reason: not valid java name */
        public static /* synthetic */ void m12019$r8$lambda$5AJmKvxSdffWGGStnb53oaNAi4(AnonymousClass5 r0, View r1) {
            r0.lambda$onCreateViewHolder$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$6CSPArOefpe3b_14D7ETb118Z30(AnonymousClass5 r0, View r1) {
            r0.lambda$onCreateViewHolder$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$9ouTp3yyATFXz9uo6tZLxgBZH_Y(TextView r0, View r1) {
            r0.performClick();
        }

        public static /* synthetic */ void $r8$lambda$JejWgOxjgCgNdqJiF_2Wtrso6DE(AnonymousClass5 r0, View r1) {
            r0.lambda$onCreateViewHolder$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$feSnmd7RDkosdhEoslQFreJBjWg(AnonymousClass5 r0) {
            r0.lambda$onCreateViewHolder$3();
        }

        public static /* synthetic */ void $r8$lambda$icl3D3QFI7GKhPbpSXeaadvFhJQ(AnonymousClass5 r0, View r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        public AnonymousClass5(LimitReachedBottomSheet r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(View r3) {
            BoostPagerBottomSheet.show(this.this$0.getBaseFragment(), LimitReachedBottomSheet.m11998$$Nest$fgetdialogId(this.this$0), LimitReachedBottomSheet.access$2200(this.this$0));
        }

        private /* synthetic */ void lambda$onCreateViewHolder$2(View r1) {
            AndroidUtilities.addToClipboard(LimitReachedBottomSheet.m12011$$Nest$mgetBoostLink(this.this$0));
            this.this$0.dismiss();
        }

        private /* synthetic */ void lambda$onCreateViewHolder$3() {
            this.this$0.getBaseFragment().presentFragment(StatisticActivity.create(LimitReachedBottomSheet.m12014$$Nest$mgetChat(this.this$0)));
        }

        private /* synthetic */ void lambda$onCreateViewHolder$4(View r1) {
            AndroidUtilities.addToClipboard(LimitReachedBottomSheet.m12011$$Nest$mgetBoostLink(this.this$0));
        }

        private /* synthetic */ void lambda$onCreateViewHolder$5(View r1) {
            this.this$0.statisticClickRunnable.run();
            this.this$0.dismiss();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.rowCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r4) {
            LimitReachedBottomSheet r0 = this.this$0;
            if (r0.headerRow != r4) goto L7;
            return 0;
        L7:
            if (r0.dividerRow != r4) goto L11;
            return 2;
        L11:
            if (r0.chatsTitleRow != r4) goto L15;
            return 3;
        L15:
            if (r0.loadingRow != r4) goto L18;
            return 5;
        L18:
            if (r0.emptyViewDividerRow != r4) goto L22;
            return 6;
        L22:
            if (LimitReachedBottomSheet.m12004$$Nest$fgetlinkRow(r0) != r4) goto L25;
            return 7;
        L25:
            LimitReachedBottomSheet r02 = this.this$0;
            if (r02.bottomRow != r4) goto L29;
            return 8;
        L29:
            ArrayList<BoostFeature> r1 = r02.boostFeatures;
            if (r1 == null) goto L37;
            int r03 = r02.boostFeaturesStartRow;
            if (r4 < r03) goto L37;
            if (r4 > (r03 + r1.size())) goto L37;
            return 9;
        L37:
            int r3 = this.this$0.type;
            if (r3 != 5) goto L40;
            return 4;
        L40:
            if (r3 != 11) goto L42;
            return 4;
        L42:
            if (r3 == 34) goto L49;
            return 1;
        L49:
            return 4;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r4) {
            LimitReachedBottomSheet r3 = this.this$0;
            int r0 = r3.type;
            if (r0 == 11) goto L7;
            if (r0 == 34) goto L7;
        L10:
            if (r4.getItemViewType() != 1) goto L12;
        L15:
            return true;
        L12:
            if (r4.getItemViewType() == 4) goto L15;
            return false;
        L7:
            if (LimitReachedBottomSheet.m11995$$Nest$fgetcanSendLink(r3) == true) goto L10;
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            int r0 = r9.getItemViewType();
            boolean r1 = false;
            boolean r2 = true;
            if (r0 != 1) goto L5;
            LimitReachedBottomSheet r02 = this.this$0;
            TLRPC.Chat r102 = r02.chats.get(r10 - r02.chatStartRow);
            AdminedChannelCell r92 = (AdminedChannelCell) r9.itemView;
            TLRPC.Chat r03 = r92.getCurrentChannel();
            r92.setChannel(r102, false);
            boolean r8 = this.this$0.selectedChats.contains(r102);
            if (r03 != r102) goto L71;
            r1 = true;
        L71:
            r92.setChecked(r8, r1);
            return;
        L5:
            if (r0 != 9) goto L7;
            LimitReachedBottomSheet r04 = this.this$0;
            int r103 = r10 - r04.boostFeaturesStartRow;
            ArrayList<BoostFeature> r05 = r04.boostFeatures;
            if (r05 == null) goto L74;
            if (r103 >= 0) goto L65;
            return;
        L65:
            if (r103 >= r05.size()) goto L76;
            ((BoostFeatureCell) r9.itemView).set(this.this$0.boostFeatures.get(r103));
            return;
        L76:
            return;
        L74:
            return;
        L7:
            if (r0 != 3) goto L9;
            HeaderCell r93 = (HeaderCell) r9.itemView;
            LimitReachedBottomSheet r104 = this.this$0;
            int r06 = r104.type;
            if (r06 == 11) goto L52;
            if (r06 == 34) goto L52;
            if (r06 != 2) goto L49;
            r93.setText(LocaleController.getString(R.string.YourPublicCommunities));
            return;
        L49:
            r93.setText(LocaleController.getString(R.string.LastActiveCommunities));
            return;
        L52:
            if (LimitReachedBottomSheet.m11995$$Nest$fgetcanSendLink(r104) == false) goto L56;
            r93.setText(LocaleController.getString(R.string.ChannelInviteViaLink));
            return;
        L56:
            if (LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(this.this$0).size() != 1) goto L59;
            r93.setText(LocaleController.getString(R.string.ChannelInviteViaLinkRestricted2));
            return;
        L59:
            r93.setText(LocaleController.getString(R.string.ChannelInviteViaLinkRestricted3));
            return;
        L9:
            if (r0 != 4) goto L77;
            GroupCreateUserCell r94 = (GroupCreateUserCell) r9.itemView;
            LimitReachedBottomSheet r07 = this.this$0;
            int r3 = r07.type;
            if (r3 != 5) goto L19;
            TLRPC.Chat r08 = (TLRPC.Chat) LimitReachedBottomSheet.m12000$$Nest$fgetinactiveChats(r07).get(r10 - this.this$0.chatStartRow);
            String r32 = (String) LimitReachedBottomSheet.m12001$$Nest$fgetinactiveChatsSignatures(this.this$0).get(r10 - this.this$0.chatStartRow);
            String r4 = r08.title;
            if (r10 != (this.this$0.chatEndRow - 1.0f)) goto L17;
            r2 = false;
        L17:
            r94.setObject(r08, r4, r32, r2);
            r94.setChecked(this.this$0.selectedChats.contains(r08), false);
            return;
        L19:
            if (r3 == 11) goto L21;
            if (r3 == 34) goto L21;
            return;
        L21:
            TLRPC.User r09 = (TLRPC.User) LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r07).get(r10 - this.this$0.chatStartRow);
            if (LimitReachedBottomSheet.m12007$$Nest$fgetpremiumMessagingBlockedUsers(this.this$0) != null) goto L24;
        L26:
            boolean r33 = false;
        L28:
            if (r33 == false) goto L30;
            TL_account.requirementToContactPremium r5 = new TL_account.requirementToContactPremium();
        L31:
            r94.overridePremiumBlocked(r5, false);
            if (r33 == false) goto L34;
            String r34 = LocaleController.getString(R.string.InvitePremiumBlockedUser);
        L35:
            String r42 = ContactsController.formatName(r09.first_name, r09.last_name);
            if (r10 != (this.this$0.chatEndRow - 1.0f)) goto L39;
            r2 = false;
        L39:
            r94.setObject(r09, r42, r34, r2);
            r94.setChecked(this.this$0.selectedChats.contains(r09), false);
            return;
        L34:
            r34 = LocaleController.formatUserStatus(LimitReachedBottomSheet.access$2100(this.this$0), r09, null, null);
            goto L35
        L30:
            r5 = null;
            goto L31
        L24:
            if (LimitReachedBottomSheet.m12007$$Nest$fgetpremiumMessagingBlockedUsers(this.this$0).contains(Long.valueOf(r09.id)) == false) goto L26;
            r33 = true;
            goto L28
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r18, int r19) {
            Context r2 = r18.getContext();
            switch(r19) {
                case 1: goto L41;
                case 2: goto L40;
                case 3: goto L39;
                case 4: goto L38;
                case 5: goto L33;
                case 6: goto L32;
                case 7: goto L19;
                case 8: goto L6;
                case 9: goto L5;
                default: goto L4;
            };
        L4:
            LimitReachedBottomSheet r1 = this.this$0;
            HeaderView r3 = new HeaderView(this.this$0, r2);
            LimitReachedBottomSheet.m12010$$Nest$fputheaderView(r1, r3);
            View r32 = r3;
        L42:
            r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r32);
        L5:
            LimitReachedBottomSheet r0 = this.this$0;
            r32 = new BoostFeatureCell(r0, r2, LimitReachedBottomSheet.access$900(r0));
            goto L42
        L6:
            LinearLayout r5 = new LinearLayout(r2);
            r5.setPadding(LimitReachedBottomSheet.access$100(this.this$0) + AndroidUtilities.dp(6.0f), 0, LimitReachedBottomSheet.access$200(this.this$0) + AndroidUtilities.dp(6.0f), 0);
            r5.setOrientation(1);
            LoginOrView r4 = new LoginOrView(r2);
            LinkSpanDrawable.LinksTextView r7 = new LinkSpanDrawable.LinksTextView(r2);
            if (LimitReachedBottomSheet.m12015$$Nest$misGroup(this.this$0) == false) goto L9;
            int r9 = R.string.BoostingStoriesByGiftingGroup2;
        L10:
            SpannableStringBuilder r92 = AndroidUtilities.replaceTags(LocaleController.getString(r9));
            SpannableStringBuilder r10 = new SpannableStringBuilder(LocaleController.getString(R.string.BoostingStoriesByGiftingLink));
            r10.setSpan(new AnonymousClass1(this), 0, r10.length(), 33);
            SpannableString r11 = new SpannableString(">");
            Drawable r14 = this.this$0.getContext().getResources().getDrawable(R.drawable.msg_arrowright).mutate();
            int r33 = Theme.key_chat_messageLinkIn;
            r14.setColorFilter(new PorterDuffColorFilter(r33, PorterDuff.Mode.SRC_IN));
            ColoredImageSpan r12 = new ColoredImageSpan(r14);
            r12.setColorKey(r33);
            r12.setSize(AndroidUtilities.dp(18.0f));
            r12.setWidth(AndroidUtilities.dp(11.0f));
            r12.setTranslateX(-AndroidUtilities.dp(5.0f));
            r11.setSpan(r12, 0, r11.length(), 33);
            r7.setText(TextUtils.concat(new CharSequence[]{r92, " ", AndroidUtilities.replaceCharSequence(">", r10, r11)}));
            r7.setTextSize(1, 14.0f);
            r7.setLineSpacing(AndroidUtilities.dp(3.0f), 1.0f);
            if ((LimitReachedBottomSheet.access$500(this.this$0) instanceof DarkThemeResourceProvider) == false) goto L13;
            r7.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, LimitReachedBottomSheet.access$600(this.this$0)));
        L14:
            r7.setGravity(1);
            r7.setOnClickListener(new LimitReachedBottomSheet$5$$ExternalSyntheticLambda0(this));
            r4.setOnClickListener(new LimitReachedBottomSheet$5$$ExternalSyntheticLambda1(r7));
            if (LimitReachedBottomSheet.m12016$$Nest$misMiniBoostBtnForAdminAvailable(this.this$0) == false) goto L17;
            ButtonWithCounterView r13 = new ButtonWithCounterView(r2, LimitReachedBottomSheet.access$800(this.this$0));
            r13.setText(LocaleController.getString(R.string.Copy), false);
            r13.setOnClickListener(new LimitReachedBottomSheet$5$$ExternalSyntheticLambda2(this));
            LinearLayout r34 = new LinearLayout(r2);
            r34.addView(this.this$0.boostMiniBtn, LayoutHelper.createLinear(-1, 44, 1.0f, 0, 0, 0, 4, 0));
            r34.addView(r13, LayoutHelper.createLinear(-1, 44, 1.0f, 0, 4, 0, 0, 0));
            r5.addView(r34, LayoutHelper.createLinear(-1, 44, 12.0f, 12.0f, 12.0f, 8.0f));
        L18:
            r5.addView(r4, LayoutHelper.createLinear(-1, 48, 0.0f, -5.0f, 0.0f, 0.0f));
            r5.addView(r7, LayoutHelper.createLinear(-1, -2, 12.0f, -6.0f, 12.0f, 17.0f));
            r32 = r5;
            goto L42
        L17:
            r5.addView(this.this$0.actionBtn, LayoutHelper.createLinear(-1, 48, 12.0f, 12.0f, 12.0f, 8.0f));
            goto L18
        L13:
            r7.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, LimitReachedBottomSheet.access$700(this.this$0)));
            goto L14
        L9:
            r9 = R.string.BoostingStoriesByGiftingChannel2;
            goto L10
        L19:
            FrameLayout r35 = new FrameLayout(this.this$0.getContext());
            r35.setPadding(LimitReachedBottomSheet.access$1000(this.this$0) + AndroidUtilities.dp(6.0f), 0, LimitReachedBottomSheet.access$1100(this.this$0) + AndroidUtilities.dp(6.0f), 0);
            TextView r15 = new TextView(r2);
            LimitReachedBottomSheet r22 = this.this$0;
            if (r22.statisticClickRunnable == null) goto L22;
        L24:
            int r23 = AndroidUtilities.dp(18.0f);
            int r72 = AndroidUtilities.dp(13.0f);
            if (this.this$0.statisticClickRunnable != null) goto L27;
            float r93 = 18.0f;
        L28:
            r15.setPadding(r23, r72, AndroidUtilities.dp(r93), AndroidUtilities.dp(13.0f));
            r15.setTextSize(1, 16.0f);
            r15.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            r15.setSingleLine(true);
            r35.addView(r15, LayoutHelper.createFrame(-1, -2.0f, 0, 11.0f, 0.0f, 11.0f, 0.0f));
            int r24 = AndroidUtilities.dp(8.0f);
            int r42 = Theme.getColor(Theme.key_graySection, LimitReachedBottomSheet.access$1200(this.this$0));
            int r6 = Theme.key_listSelector;
            r15.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r24, r42, ColorUtils.setAlphaComponent(Theme.getColor(r6, LimitReachedBottomSheet.access$1300(this.this$0)), 76)));
            r15.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, LimitReachedBottomSheet.access$1400(this.this$0)));
            r15.setOnClickListener(new LimitReachedBottomSheet$5$$ExternalSyntheticLambda4(this));
            if (this.this$0.statisticClickRunnable == null) goto L31;
            ImageView r25 = new ImageView(this.this$0.getContext());
            r25.setImageResource(R.drawable.msg_stats);
            r25.setColorFilter(Theme.getColor(Theme.key_dialogTextBlack, LimitReachedBottomSheet.access$1500(this.this$0)));
            r25.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
            r25.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(20.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(r6, LimitReachedBottomSheet.access$1600(this.this$0)), 76)));
            r35.addView(r25, LayoutHelper.createFrame(40, 40.0f, 21, 15.0f, 0.0f, 15.0f, 0.0f));
            r25.setOnClickListener(new LimitReachedBottomSheet$5$$ExternalSyntheticLambda5(this));
        L31:
            r15.setText(LimitReachedBottomSheet.m12011$$Nest$mgetBoostLink(this.this$0));
            r15.setGravity(17);
            r32 = r35;
            goto L42
        L27:
            r93 = 50.0f;
            goto L28
        L22:
            if (ChatObject.hasAdminRights(LimitReachedBottomSheet.m12014$$Nest$mgetChat(r22)) == false) goto L24;
            this.this$0.statisticClickRunnable = new LimitReachedBottomSheet$5$$ExternalSyntheticLambda3(this);
            goto L24
        L32:
            r32 = new AnonymousClass3(this, this.this$0.getContext());
            goto L42
        L33:
            FlickerLoadingView r36 = new FlickerLoadingView(r2, null);
            if (this.this$0.type != 2) goto L36;
            int r02 = 22;
        L37:
            r36.setViewType(r02);
            r36.setIsSingleCell(true);
            r36.setIgnoreHeightCheck(true);
            r36.setItemsCount(10);
            r32 = r36;
            goto L42
        L36:
            r02 = 21;
            goto L37
        L38:
            View r16 = new GroupCreateUserCell(r2, 1, 0, false, false, LimitReachedBottomSheet.access$1800(this.this$0));
            r16.setPadding(LimitReachedBottomSheet.access$1900(this.this$0), 0, LimitReachedBottomSheet.access$2000(this.this$0), 0);
            r32 = r16;
            goto L42
        L39:
            View r37 = new HeaderCell(r2);
            r37.setPadding(0, 0, 0, AndroidUtilities.dp(8.0f));
            r32 = r37;
            goto L42
        L40:
            r32 = new ShadowSectionCell(r2, 12, Theme.getColor(Theme.key_windowBackgroundGray, LimitReachedBottomSheet.access$1700(this.this$0)));
            goto L42
        L41:
            r32 = new AdminedChannelCell(r2, new AnonymousClass2(this), true, 9);
            goto L42
        }
    }

    public static class BoostFeature {
        public final int countPlural;
        public final String countValue;
        public final int iconResId;
        public boolean incremental;
        public final int textKey;
        public final String textKeyPlural;

        public static class BoostFeatureLevel extends BoostFeature {
            public final boolean isFirst;
            public final int lvl;

            public BoostFeatureLevel(int r8, boolean r9) {
                super(-1, -1, null, null, -1, null);
                this.lvl = r8;
                this.isFirst = r9;
            }

            @Override // org.telegram.ui.Components.Premium.LimitReachedBottomSheet.BoostFeature
            public /* bridge */ /* synthetic */ BoostFeature asIncremental() {
                return super.asIncremental();
            }

            @Override // org.telegram.ui.Components.Premium.LimitReachedBottomSheet.BoostFeature
            public /* bridge */ /* synthetic */ boolean equals(BoostFeature r1) {
                return super.equals(r1);
            }
        }

        private BoostFeature(int r1, int r2, String r3, String r4, int r5) {
            this.iconResId = r1;
            this.textKey = r2;
            this.countValue = r3;
            this.textKeyPlural = r4;
            this.countPlural = r5;
        }

        public /* synthetic */ BoostFeature(int r1, int r2, String r3, String r4, int r5, LimitReachedBottomSheetIA r6) {
            this(r1, r2, r3, r4, r5);
        }

        public static boolean arraysEqual(ArrayList<BoostFeature> r5, ArrayList<BoostFeature> r6) {
            if (r5 != null) goto L7;
            if (r6 != null) goto L7;
            return true;
        L7:
            if (r5 == null) goto L9;
            if (r6 != null) goto L9;
        L11:
            return false;
        L9:
            if (r5 != null) goto L13;
            if (r6 != null) goto L11;
        L13:
            if (r5.size() == r6.size()) goto L15;
            return false;
        L15:
            int r2 = 0;
        L17:
            if (r2 >= r5.size()) goto L22;
            if (r5.get(r2).equals(r6.get(r2)) == false) goto L20;
            r2 = r2 + 1;
            goto L17
        L20:
            return false;
        L22:
            return true;
        }

        public static BoostFeature of(int r6, int r7) {
            return new BoostFeature(r6, r7, null, null, -1);
        }

        public static BoostFeature of(int r6, int r7, String r8) {
            return new BoostFeature(r6, r7, r8, null, -1);
        }

        public static BoostFeature of(int r6, String r7, int r8) {
            return new BoostFeature(r6, -1, null, r7, r8);
        }

        public BoostFeature asIncremental() {
            this.incremental = true;
            return this;
        }

        public boolean equals(BoostFeature r5) {
            if (r5 != null) goto L6;
            return false;
        L6:
            if (this.incremental == false) goto L11;
            if (this.countPlural <= 2) goto L11;
            return true;
        L11:
            if (this.iconResId == r5.iconResId) goto L13;
        L21:
            return false;
        L13:
            if (this.textKey != r5.textKey) goto L21;
            if (TextUtils.equals(this.countValue, r5.countValue) == false) goto L21;
            if (TextUtils.equals(this.textKeyPlural, r5.textKeyPlural) == false) goto L21;
            if (this.countPlural != r5.countPlural) goto L21;
            return true;
        }
    }

    public class BoostFeatureCell extends FrameLayout {
        public BoostFeature feature;
        private final ImageView imageView;
        public BoostFeature.BoostFeatureLevel level;
        private final FrameLayout levelLayout;
        private final SimpleTextView levelTextView;
        private final Theme.ResourcesProvider resourcesProvider;
        private final SimpleTextView textView;
        final /* synthetic */ LimitReachedBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.Premium.LimitReachedBottomSheet$BoostFeatureCell$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            private final Paint dividerPaint;
            private final PremiumGradient.PremiumGradientTools gradientTools;
            final /* synthetic */ BoostFeatureCell this$1;
            final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
            final /* synthetic */ LimitReachedBottomSheet val$this$0;

            public AnonymousClass1(BoostFeatureCell r8, Context r9, LimitReachedBottomSheet r10, Theme.ResourcesProvider r11) {
                this.this$1 = r8;
                this.val$this$0 = r10;
                this.val$resourcesProvider = r11;
                super(r9);
                this.gradientTools = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, -1, -1, -1, r11);
                Paint r82 = new Paint(1);
                this.dividerPaint = r82;
                r82.setStyle(Paint.Style.STROKE);
                r82.setStrokeWidth(1.0f);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r11) {
                this.dividerPaint.setColor(Theme.getColor(Theme.key_sheet_scrollUp, this.val$resourcesProvider));
                r11.drawLine(AndroidUtilities.dp(18.0f), getHeight() / 2.0f, BoostFeatureCell.m12020$$Nest$fgetlevelTextView(this.this$1).getLeft() - AndroidUtilities.dp(20.0f), getHeight() / 2.0f, this.dividerPaint);
                r11.drawLine(BoostFeatureCell.m12020$$Nest$fgetlevelTextView(this.this$1).getRight() + AndroidUtilities.dp(20.0f), getHeight() / 2.0f, getWidth() - AndroidUtilities.dp(18.0f), getHeight() / 2.0f, this.dividerPaint);
                RectF r1 = AndroidUtilities.rectTmp;
                r1.set(BoostFeatureCell.m12020$$Nest$fgetlevelTextView(this.this$1).getLeft() - AndroidUtilities.dp(15.0f), ((BoostFeatureCell.m12020$$Nest$fgetlevelTextView(this.this$1).getTop() + BoostFeatureCell.m12020$$Nest$fgetlevelTextView(this.this$1).getBottom()) - AndroidUtilities.dp(30.0f)) / 2.0f, BoostFeatureCell.m12020$$Nest$fgetlevelTextView(this.this$1).getRight() + AndroidUtilities.dp(15.0f), ((BoostFeatureCell.m12020$$Nest$fgetlevelTextView(this.this$1).getTop() + BoostFeatureCell.m12020$$Nest$fgetlevelTextView(this.this$1).getBottom()) + AndroidUtilities.dp(30.0f)) / 2.0f);
                r11.save();
                r11.translate(r1.left, r1.top);
                r1.set(0.0f, 0.0f, r1.width(), r1.height());
                this.gradientTools.gradientMatrix(r1);
                r11.drawRoundRect(r1, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), this.gradientTools.paint);
                r11.restore();
                super.dispatchDraw(r11);
            }
        }

        /* renamed from: -$$Nest$fgetlevelTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SimpleTextView m12020$$Nest$fgetlevelTextView(BoostFeatureCell r0) {
            return r0.levelTextView;
        }

        public BoostFeatureCell(LimitReachedBottomSheet r19, Context r20, Theme.ResourcesProvider r21) {
            this.this$0 = r19;
            super(r20);
            this.resourcesProvider = r21;
            setPadding(LimitReachedBottomSheet.access$6600(r19), 0, LimitReachedBottomSheet.access$6700(r19), 0);
            ImageView r4 = new ImageView(r20);
            this.imageView = r4;
            r4.setScaleType(ImageView.ScaleType.FIT_CENTER);
            r4.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_premiumGradient1, r21), PorterDuff.Mode.SRC_IN));
            int r7 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r5 = 5;
        L6:
            addView(r4, LayoutHelper.createFrame(24, 24.0f, r5 | 16, 24.0f, 0.0f, 24.0f, 0.0f));
            SimpleTextView r42 = new SimpleTextView(r20);
            this.textView = r42;
            r42.setWidthWrapContent(true);
            r42.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r21));
            r42.setTextSize(14);
            boolean r10 = LocaleController.isRTL;
            if (r10 == false) goto L9;
            r7 = 5;
        L9:
            int r13 = r7 | 16;
            if (r10 == false) goto L12;
            float r14 = 30.0f;
        L13:
            if (r10 == false) goto L15;
            float r16 = 60.0f;
        L16:
            addView(r42, LayoutHelper.createFrame(-2, -2.0f, r13, r14, 0.0f, r16, 0.0f));
            SimpleTextView r43 = new SimpleTextView(r20);
            this.levelTextView = r43;
            r43.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
            r43.setWidthWrapContent(true);
            r43.setTypeface(AndroidUtilities.bold());
            r43.setTextSize(14);
            AnonymousClass1 r52 = new AnonymousClass1(this, r20, r19, r21);
            this.levelLayout = r52;
            r52.setWillNotDraw(false);
            r52.addView(r43, LayoutHelper.createFrame(-2, -2, 17));
            addView(r52, LayoutHelper.createFrame(-1, -1.0f));
            return;
        L15:
            r16 = 30.0f;
            goto L16
        L12:
            r14 = 60.0f;
            goto L13
        L5:
            r5 = 3;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            if (this.level == null) goto L5;
            float r32 = 49.0f;
        L6:
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r32), TLObject.FLAG_30));
            return;
        L5:
            r32 = 36.0f;
            goto L6
        }

        public void set(BoostFeature r9) {
            if ((r9 instanceof BoostFeature.BoostFeatureLevel) == false) goto L10;
            this.level = (BoostFeature.BoostFeatureLevel) r9;
            this.feature = null;
            this.imageView.setVisibility(8);
            this.textView.setVisibility(8);
            this.levelLayout.setVisibility(0);
            SimpleTextView r92 = this.levelTextView;
            BoostFeature.BoostFeatureLevel r8 = this.level;
            if (r8.isFirst == false) goto L7;
            String r0 = "BoostLevelUnlocks";
        L8:
            r92.setText(LocaleController.formatPluralString(r0, r8.lvl, new Object[0]));
            return;
        L7:
            r0 = "BoostLevel";
            goto L8
        L10:
            if (r9 == null) goto L37;
            this.level = null;
            this.feature = r9;
            this.imageView.setVisibility(0);
            this.imageView.setImageResource(this.feature.iconResId);
            this.textView.setVisibility(0);
            BoostFeature r93 = this.feature;
            String r02 = r93.textKeyPlural;
            String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r02 == null) goto L24;
            String r94 = LocaleController.getString(this.feature.textKeyPlural + "_" + LocaleController.getStringParamForNumber(this.feature.countPlural));
            if (r94 != null) goto L16;
        L17:
            r94 = LocaleController.getString(this.feature.textKeyPlural + "_other");
        L18:
            if (r94 != null) goto L20;
            r94 = _UrlKt.FRAGMENT_ENCODE_SET;
        L20:
            SpannableStringBuilder r03 = new SpannableStringBuilder(r94);
            int r5 = r94.indexOf(TimeModel.NUMBER_FORMAT);
            if (r5 < 0) goto L23;
            r03 = new SpannableStringBuilder(r94);
            SpannableString r95 = new SpannableString(this.feature.countPlural + _UrlKt.FRAGMENT_ENCODE_SET);
            r95.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r95.length(), 33);
            r03.replace(r5, r5 + 2, r95);
        L23:
            this.textView.setText(r03);
        L35:
            this.levelLayout.setVisibility(8);
            return;
        L16:
            if (r94.startsWith("LOC_ERR") == false) goto L18;
        L24:
            String r96 = LocaleController.getString(r93.textKey);
            if (r96 == null) goto L29;
            r4 = r96;
        L29:
            if (this.feature.countValue == null) goto L34;
            SpannableStringBuilder r97 = new SpannableStringBuilder(r4);
            int r04 = r4.indexOf("%s");
            if (r04 < 0) goto L33;
            r97 = new SpannableStringBuilder(r4);
            SpannableString r42 = new SpannableString(this.feature.countValue);
            r42.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r42.length(), 33);
            r97.replace(r04, r04 + 2, r42);
        L33:
            this.textView.setText(r97);
            goto L35
        L34:
            this.textView.setText(r4);
            goto L35
        }
    }

    public class HeaderView extends LinearLayout {
        BoostCounterView boostCounterView;
        TextView description;
        final /* synthetic */ LimitReachedBottomSheet this$0;
        TextView title;
        LinearLayout titleLinearLayout;

        /* renamed from: org.telegram.ui.Components.Premium.LimitReachedBottomSheet$HeaderView$1, reason: invalid class name */
        public class AnonymousClass1 extends TextView {
            private final Paint paint;
            final /* synthetic */ HeaderView this$1;
            final /* synthetic */ LimitReachedBottomSheet val$this$0;

            public AnonymousClass1(HeaderView r1, Context r2, LimitReachedBottomSheet r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
                this.paint = new Paint(1);
            }

            @Override // android.view.View
            public void dispatchDraw(Canvas r11) {
                this.paint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, LimitReachedBottomSheet.access$4800(this.this$1.this$0)), 0.8f));
                this.paint.setStyle(Paint.Style.STROKE);
                this.paint.setStrokeWidth(1.0f);
                float r4 = getHeight() / 2.0f;
                Layout r0 = getLayout();
                int r2 = 0;
                int r3 = 0;
            L4:
                if (r2 >= r0.getLineCount()) goto L6;
                r3 = Math.max(r3, (int) r0.getLineWidth(r2));
                r2 = r2 + 1;
                goto L4
            L6:
                float r8 = r3 / 2.0f;
                r11.drawLine(0.0f, r4, ((getWidth() / 2.0f) - r8) - AndroidUtilities.dp(8.0f), r4, this.paint);
                r11.drawLine(((getWidth() / 2.0f) + r8) + AndroidUtilities.dp(8.0f), r4, getWidth(), r4, this.paint);
                super.dispatchDraw(r11);
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.LimitReachedBottomSheet$HeaderView$2, reason: invalid class name */
        public class AnonymousClass2 extends LimitPreviewView {
            final /* synthetic */ HeaderView this$1;
            final /* synthetic */ LimitReachedBottomSheet val$this$0;

            public AnonymousClass2(HeaderView r1, Context r2, int r3, int r4, int r5, float r6, Theme.ResourcesProvider r7, LimitReachedBottomSheet r8) {
                this.this$1 = r1;
                this.val$this$0 = r8;
                super(r2, r3, r4, r5, r6, r7);
            }

            @Override // android.view.View
            public void invalidate() {
                if (LimitReachedBottomSheet.m12005$$Nest$fgetlockInvalidation(this.this$1.this$0) == false) goto L5;
                return;
            L5:
                super.invalidate();
            }
        }

        /* renamed from: $r8$lambda$PWTFXfmOeKA-SX5j-KEiwKH_w78, reason: not valid java name */
        public static /* synthetic */ void m12021$r8$lambda$PWTFXfmOeKASX5jKEiwKH_w78(HeaderView r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$wrUoHOibd7de0_DLCfrvlh7wtGM(HeaderView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        @SuppressLint({"SetTextI18n"})
        public HeaderView(LimitReachedBottomSheet r45, Context r46) {
            this.this$0 = r45;
            super(r46);
            setOrientation(1);
            setPadding(LimitReachedBottomSheet.access$2300(r45) + AndroidUtilities.dp(6.0f), 0, LimitReachedBottomSheet.access$2400(r45) + AndroidUtilities.dp(6.0f), 0);
            LimitParams r0 = LimitReachedBottomSheet.m12018$$Nest$smgetLimitParams(r45.type, LimitReachedBottomSheet.access$2500(r45));
            r45.limitParams = r0;
            int r3 = r0.icon;
            MessagesController r02 = MessagesController.getInstance(LimitReachedBottomSheet.access$2600(r45));
            boolean r4 = r02.premiumFeaturesBlocked();
            boolean r5 = LimitReachedBottomSheet.m12015$$Nest$misGroup(r45);
            int r6 = r45.type;
            if (r6 != 31) goto L9;
            if (r5 == false) goto L6;
            int r03 = R.string.BoostingAdditionalFeaturesSubtitle;
        L7:
            String r04 = LocaleController.getString(r03);
        L8:
            String r13 = r04;
            boolean r14 = r4;
        L152:
            LimitParams r05 = r45.limitParams;
            int r42 = r05.defaultLimit;
            int r52 = r05.premiumLimit;
            int r06 = LimitReachedBottomSheet.m11997$$Nest$fgetcurrentValue(r45);
            int r62 = r45.type;
            if (r62 != 3) goto L155;
            r06 = MessagesController.getInstance(LimitReachedBottomSheet.access$2800(r45)).dialogFilters.size() - 1;
        L169:
            if (UserConfig.getInstance(LimitReachedBottomSheet.access$3000(r45)).isPremium() == false) goto L171;
        L183:
            r06 = r52;
            float r9 = 1.0f;
        L184:
            float r63 = r42 / r52;
            int r7 = r45.type;
            if (r7 != 18) goto L187;
        L216:
            boolean r15 = true;
        L217:
            if (r15 == false) goto L220;
            r06 = 0;
        L220:
            if (r7 == 11) goto L226;
            if (r7 == 34) goto L226;
        L305:
            int r72 = r45.type;
            if (r72 != 31) goto L308;
        L338:
            int r10 = 19;
        L340:
            if (r45.type != 31) goto L346;
            boolean r07 = Theme.isCurrentThemeMonet();
            int r43 = Theme.getColor(Theme.key_chats_actionIcon);
            FrameLayout r53 = new FrameLayout(r46);
            ImageView r64 = new ImageView(r46);
            r64.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.large_boosts));
            if (r07 == true) goto L345;
            r43 = -1;
        L345:
            r64.setColorFilter(r43, PorterDuff.Mode.SRC_IN);
            r53.addView(r64, LayoutHelper.createFrame(-2, -2, 17));
            r53.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(79.0f), Theme.getColor(Theme.key_featuredStickers_addButton)));
            addView(r53, LayoutHelper.createLinear(79, 79, 1, 0, 23, 0, 0));
        L346:
            TextView r08 = new TextView(r46);
            this.title = r08;
            r08.setTypeface(AndroidUtilities.bold());
            int r09 = r45.type;
            if (r09 != 31) goto L351;
            this.title.setText(LocaleController.getString(R.string.BoostingAdditionalFeaturesTitle));
        L349:
            int r152 = 22;
        L417:
            this.title.setTextSize(1, 20.0f);
            TextView r010 = this.title;
            int r44 = Theme.key_windowBackgroundWhiteBlackText;
            r010.setTextColor(Theme.getColor(r44, LimitReachedBottomSheet.access$5400(r45)));
            this.title.setGravity(17);
            int r011 = r45.type;
            if (r011 != r10) goto L420;
        L429:
            BoostCounterView r012 = new BoostCounterView(r46, LimitReachedBottomSheet.access$5500(r45));
            this.boostCounterView = r012;
            r012.setCount(LimitReachedBottomSheet.m11994$$Nest$fgetcanApplyBoost(r45).boostCount, false);
            if (r45.type != 32) goto L433;
            this.boostCounterView.setVisibility(8);
        L433:
            if (LimitReachedBottomSheet.m12002$$Nest$fgetisCurrentChat(r45) == false) goto L439;
            LinearLayout r013 = new LinearLayout(r46);
            this.titleLinearLayout = r013;
            r013.setOrientation(0);
            this.titleLinearLayout.setWeightSum(1.0f);
            this.titleLinearLayout.addView(this.title, LayoutHelper.createLinear(-2, -2, 1.0f, 0));
            this.titleLinearLayout.addView(this.boostCounterView, LayoutHelper.createLinear(-2, -2, 48, 0, 2, 0, 0));
            View r014 = this.titleLinearLayout;
            if (r14 == false) goto L437;
            int r21 = 8;
        L438:
            addView(r014, LayoutHelper.createLinear(-2, -2, 1, 12, r21, 12, 9));
        L447:
            TextView r015 = new TextView(r46);
            this.description = r015;
            r015.setText(AndroidUtilities.replaceTags(r13));
            this.description.setTextSize(1, 14.0f);
            this.description.setGravity(1);
            TextView r016 = this.description;
            r016.setLineSpacing(r016.getLineSpacingExtra(), this.description.getLineSpacingMultiplier() * 1.1f);
            if (r45.type != 18) goto L453;
            boolean r017 = LimitReachedBottomSheet.access$6000(r45) instanceof DarkThemeResourceProvider;
            TextView r2 = this.description;
            if (r017 == false) goto L452;
            r2.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, LimitReachedBottomSheet.access$6100(r45)));
        L454:
            int r018 = r45.type;
            View r22 = this.description;
            if (r018 != r10) goto L457;
            addView(r22, LayoutHelper.createLinear(-2, -2, 1, 24, -2, 24, 17));
        L459:
            if (r45.type != 31) goto L461;
            ((ViewGroup.MarginLayoutParams) this.description.getLayoutParams()).bottomMargin = AndroidUtilities.dp(15.0f);
            ((ViewGroup.MarginLayoutParams) this.title.getLayoutParams()).bottomMargin = AndroidUtilities.dp(6.0f);
            ((ViewGroup.MarginLayoutParams) this.title.getLayoutParams()).topMargin = AndroidUtilities.dp(12.0f);
        L461:
            r45.updatePremiumButtonText();
            return;
        L457:
            addView(r22, LayoutHelper.createLinear(-2, -2, 1, 24, 0, 24, 24));
            goto L459
        L452:
            r2.setTextColor(Theme.getColor(r44, LimitReachedBottomSheet.access$6200(r45)));
            goto L454
        L453:
            this.description.setTextColor(Theme.getColor(r44, LimitReachedBottomSheet.access$6300(r45)));
            goto L454
        L437:
            r21 = r152;
            goto L438
        L439:
            View r019 = this.title;
            if (r14 == false) goto L442;
            int r27 = 8;
        L443:
            addView(r019, LayoutHelper.createLinear(-2, -2, 1, 0, r27, 0, 0));
            LinearLayout r020 = new LinearLayout(getContext());
            r020.setOrientation(0);
            r020.setClipChildren(false);
            FrameLayout r32 = new FrameLayout(getContext());
            r32.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(14.0f), Theme.getColor(Theme.key_windowBackgroundGray, LimitReachedBottomSheet.access$5600(r45))));
            BackupImageView r54 = new BackupImageView(getContext());
            r54.setRoundRadius(AndroidUtilities.dp(14.0f));
            TLRPC.Chat r65 = MessagesController.getInstance(LimitReachedBottomSheet.access$5700(r45)).getChat(Long.valueOf(-LimitReachedBottomSheet.m11998$$Nest$fgetdialogId(r45)));
            AvatarDrawable r73 = new AvatarDrawable();
            r73.setInfo(LimitReachedBottomSheet.access$5800(r45), r65);
            r54.setForUserOrChat(r65, r73);
            r32.addView(r54, LayoutHelper.createFrame(28, 28.0f));
            TextView r55 = new TextView(getContext());
            if (r65 == null) goto L446;
            r55.setText(r65.title);
        L446:
            r55.setSingleLine(true);
            r55.setMaxLines(1);
            r55.setEllipsize(TextUtils.TruncateAt.END);
            r55.setTextSize(1, 13.0f);
            r55.setTextColor(Theme.getColor(r44, LimitReachedBottomSheet.access$5900(r45)));
            r32.addView(r55, LayoutHelper.createFrame(-2, -2.0f, 16, 36.0f, 0.0f, 12.0f, 0.0f));
            r020.addView(r32, LayoutHelper.createLinear(-2, 28, 80, 18, 0, 18, 0));
            LayoutTransition r33 = new LayoutTransition();
            r33.setDuration(100);
            r33.enableTransitionType(4);
            r020.setLayoutTransition(r33);
            r020.addView(this.boostCounterView, LayoutHelper.createLinear(-2, -2, 48, -30, 2, 18, 0));
            addView(r020, LayoutHelper.createLinear(-2, 38, 17, 0, -4, 0, 12));
            ScaleStateListAnimator.apply(r020);
            r020.setOnClickListener(new LimitReachedBottomSheet$HeaderView$$ExternalSyntheticLambda1(this));
            goto L447
        L442:
            r27 = r152;
            goto L443
        L420:
            if (r011 == 32) goto L429;
            if (LimitReachedBottomSheet.m12016$$Nest$misMiniBoostBtnForAdminAvailable(r45) == true) goto L429;
            View r021 = this.title;
            if (r14 == false) goto L427;
            int r212 = 8;
        L428:
            addView(r021, LayoutHelper.createLinear(-2, -2, 1, 0, r212, 0, 10));
            goto L447
        L427:
            r212 = r152;
            goto L428
        L351:
            if (r09 != 32) goto L353;
            this.title.setText(LimitReachedBottomSheet.m12013$$Nest$mgetBoostsTitleString(r45));
            goto L349
        L353:
            if (r09 != r10) goto L359;
            boolean r022 = r45.getBaseFragment() instanceof GroupColorActivity;
            TextView r47 = this.title;
            if (r022 == false) goto L357;
            r47.setText(LocaleController.getString(R.string.BoostingGroupBoostWhatAreBoosts));
            goto L349
        L357:
            r47.setText(LimitReachedBottomSheet.m12013$$Nest$mgetBoostsTitleString(r45));
            goto L349
        L359:
            if (r09 != 30) goto L362;
            this.title.setText(LimitReachedBottomSheet.m12013$$Nest$mgetBoostsTitleString(r45));
            goto L349
        L362:
            if (r09 != 35) goto L365;
            this.title.setText(LocaleController.getString(R.string.UnlockBoostChannelAutotranslation));
            goto L349
        L365:
            if (r09 != 18) goto L371;
            int r023 = LimitReachedBottomSheet.m11993$$Nest$fgetboostsStatus(r45).level;
            TextView r48 = this.title;
            if (r023 != 0) goto L369;
            r48.setText(LocaleController.getString(R.string.BoostingEnableStories));
            goto L349
        L369:
            r48.setText(LocaleController.getString(R.string.BoostingIncreaseLevel));
            goto L349
        L371:
            if (r09 != 21) goto L374;
            this.title.setText(LocaleController.getString(R.string.ReactionCustomReactions));
            goto L349
        L374:
            if (r09 != 20) goto L377;
            this.title.setText(LocaleController.getString(R.string.BoostingEnableColor));
            goto L349
        L377:
            if (r09 != 24) goto L380;
            this.title.setText(LocaleController.getString(R.string.BoostingEnableProfileColor));
            goto L349
        L380:
            if (r09 != 27) goto L383;
            this.title.setText(LocaleController.getString(R.string.BoostingEnableLinkIcon));
            goto L349
        L383:
            if (r09 != 28) goto L386;
            this.title.setText(LocaleController.getString(R.string.BoostingEnableProfileIcon));
            goto L349
        L386:
            if (r09 != 25) goto L389;
            this.title.setText(LocaleController.getString(R.string.BoostingEnableEmojiStatus));
            goto L349
        L389:
            if (r09 != 26) goto L392;
            this.title.setText(LocaleController.getString(R.string.BoostingEnableWearCollectibles));
            goto L349
        L392:
            if (r09 != 29) goto L394;
            this.title.setText(LocaleController.getString(R.string.BoostingEnableGroupEmojiPack));
            goto L349
        L394:
            r152 = 22;
            if (r09 != 22) goto L397;
        L416:
            this.title.setText(LocaleController.getString(R.string.BoostingEnableWallpaper));
            goto L417
        L397:
            if (r09 == 23) goto L416;
            if (r09 != 11) goto L406;
            boolean r024 = LimitReachedBottomSheet.m11995$$Nest$fgetcanSendLink(r45);
            TextView r49 = this.title;
            if (r024 == false) goto L404;
            r49.setText(LocaleController.getString(R.string.ChannelInviteViaLink));
            goto L417
        L404:
            r49.setText(LocaleController.getString(R.string.ChannelInviteViaLinkRestricted));
            goto L417
        L406:
            if (r09 != 34) goto L408;
            this.title.setText(LocaleController.getString(R.string.CallInviteViaLinkTitle));
            goto L417
        L408:
            if (r09 != 6) goto L411;
            this.title.setText(LocaleController.getString(R.string.FileTooLarge));
            goto L417
        L411:
            if (r09 == 14) goto L413;
        L415:
            this.title.setText(LocaleController.getString(R.string.LimitReached));
            goto L417
        L413:
            if (r45.storiesCount <= 1) goto L415;
            this.title.setText(LocaleController.getString(R.string.CreateMultipleStories));
            goto L417
        L308:
            if (r72 == 34) goto L338;
            r10 = 19;
            AnonymousClass2 r025 = new AnonymousClass2(this, r46, r3, r06, r52, r63, LimitReachedBottomSheet.access$5200(r45), r45);
            r45.limitPreviewView = r025;
            if (r15 == true) goto L312;
            r025.setBagePosition(r9);
            r45.limitPreviewView.setType(r45.type);
            r45.limitPreviewView.defaultCount.setVisibility(8);
            if (r14 == false) goto L324;
            r45.limitPreviewView.setPremiumLocked();
        L332:
            int r026 = r45.type;
            if (r026 != 2) goto L335;
        L336:
            r45.limitPreviewView.setDelayedAnimation();
        L337:
            addView(r45.limitPreviewView, LayoutHelper.createLinear(-1, -2, 0.0f, 0, -4, 0, -4, 0));
            goto L340
        L335:
            if (r026 != 5) goto L337;
        L324:
            if (UserConfig.getInstance(LimitReachedBottomSheet.access$5300(r45)).isPremium() == false) goto L326;
        L327:
            r45.limitPreviewView.premiumCount.setVisibility(8);
            int r027 = r45.type;
            LimitPreviewView r34 = r45.limitPreviewView;
            if (r027 != 6) goto L330;
            r34.defaultCount.setText("2 GB");
        L331:
            r45.limitPreviewView.defaultCount.setVisibility(0);
            goto L332
        L330:
            r34.defaultCount.setText(Integer.toString(r42));
            goto L331
        L326:
            if (LimitReachedBottomSheet.m12003$$Nest$fgetisVeryLargeFile(r45) == false) goto L332;
        L312:
            if (LimitReachedBottomSheet.m11993$$Nest$fgetboostsStatus(r45) == null) goto L332;
            LimitPreviewView r028 = r45.limitPreviewView;
            TL_stories.TL_premium_boostsStatus r35 = LimitReachedBottomSheet.m11993$$Nest$fgetboostsStatus(r45);
            if (LimitReachedBottomSheet.m11994$$Nest$fgetcanApplyBoost(r45) != null) goto L316;
        L318:
            boolean r410 = false;
        L319:
            r028.setBoosts(r35, r410);
            goto L332
        L316:
            if (LimitReachedBottomSheet.m11994$$Nest$fgetcanApplyBoost(r45).boostedNow == false) goto L318;
            r410 = true;
        L226:
            if (MessagesController.getInstance(LimitReachedBottomSheet.access$3100(r45)).premiumFeaturesBlocked() == true) goto L305;
            if (LimitReachedBottomSheet.m12006$$Nest$fgetpremiumInviteBlockedUsers(r45) == null) goto L232;
            if (LimitReachedBottomSheet.m12006$$Nest$fgetpremiumInviteBlockedUsers(r45).isEmpty() == true) goto L232;
        L236:
            if (LimitReachedBottomSheet.m12006$$Nest$fgetpremiumInviteBlockedUsers(r45).isEmpty() == false) goto L238;
            ArrayList r029 = LimitReachedBottomSheet.m12007$$Nest$fgetpremiumMessagingBlockedUsers(r45);
        L239:
            AvatarsImageView r36 = new AvatarsImageView(r46, false);
            r36.avatarsDrawable.strokeWidth = AndroidUtilities.dp(3.33f);
            r36.setSize(AndroidUtilities.dp(72.0f));
            r36.setStepFactor(0.4f);
            int r411 = Math.min(r029.size(), 3);
            r36.setCount(r411);
            int r56 = 0;
        L240:
            if (r56 >= r411) goto L242;
            Long r66 = (Long) r029.get(r56);
            r66.longValue();
            r36.setObject(r56, LimitReachedBottomSheet.access$3300(r45), MessagesController.getInstance(LimitReachedBottomSheet.access$3200(r45)).getUser(r66));
            r56 = r56 + 1;
            goto L240
        L242:
            r36.commitTransition(false);
            addView(r36, LayoutHelper.createLinear(((r411 - 1) * 30) + 72, 72, 1, 0, 16, 0, 13));
            TextView r412 = new TextView(r46);
            r412.setGravity(17);
            r412.setTypeface(AndroidUtilities.bold());
            r412.setTextSize(1, 20.0f);
            int r57 = Theme.key_dialogTextBlack;
            r412.setTextColor(Theme.getColor(r57, LimitReachedBottomSheet.access$3400(r45)));
            r412.setText(LocaleController.getString(R.string.InvitePremiumBlockedTitle));
            addView(r412, LayoutHelper.createLinear(-1, -2, 1, 32, 0, 32, 9));
            TextView r413 = new TextView(r46);
            r413.setGravity(17);
            r413.setTextSize(1, 14.0f);
            r413.setTextColor(Theme.getColor(r57, LimitReachedBottomSheet.access$3500(r45)));
            addView(r413, LayoutHelper.createLinear(-1, -2, 1, 32, 0, 32, 19));
            if (r45.type != 34) goto L245;
            boolean r67 = true;
        L247:
            if (LimitReachedBottomSheet.m12007$$Nest$fgetpremiumMessagingBlockedUsers(r45) != null) goto L249;
        L251:
            boolean r74 = false;
        L253:
            if (r029.size() != 1) goto L261;
            if (r67 == false) goto L256;
            int r37 = R.string.InviteCallMessagePremiumBlockedOne;
        L259:
            String r030 = LocaleController.formatString(r37, new Object[]{UserObject.getForcedFirstName(MessagesController.getInstance(LimitReachedBottomSheet.access$3600(r45)).getUser((Long) r029.get(0)))});
        L282:
            r413.setText(AndroidUtilities.replaceTags(r030));
            if (LimitReachedBottomSheet.m12006$$Nest$fgetpremiumInviteBlockedUsers(r45) != null) goto L285;
            int r031 = 0;
        L287:
            if (LimitReachedBottomSheet.m12007$$Nest$fgetpremiumMessagingBlockedUsers(r45) != null) goto L289;
            int r38 = 0;
        L291:
            if ((r031 - r38) > 0) goto L293;
        L302:
            ((ViewGroup.MarginLayoutParams) r413.getLayoutParams()).bottomMargin = AndroidUtilities.dp(8.0f);
            r45.premiumButtonSetSubscribe = true;
        L303:
            r45.updatePremiumButtonText();
            return;
        L293:
            if (r031 != 1) goto L296;
            if (r38 == 1) goto L302;
        L296:
            if (LimitReachedBottomSheet.m11995$$Nest$fgetcanSendLink(r45) == false) goto L302;
            PremiumButtonView r032 = new PremiumButtonView(r46, false, LimitReachedBottomSheet.access$4500(r45));
            ScaleStateListAnimator.apply(r032, 0.02f, 1.2f);
            r032.setButton(LocaleController.getString(R.string.InvitePremiumBlockedSubscribe), new LimitReachedBottomSheet$HeaderView$$ExternalSyntheticLambda0(this));
            addView(r032, LayoutHelper.createLinear(-1, 48, (LimitReachedBottomSheet.access$4600(r45) / AndroidUtilities.density) + 4.0f, 0.0f, (LimitReachedBottomSheet.access$4700(r45) / AndroidUtilities.density) + 4.0f, 18.0f));
            AnonymousClass1 r033 = new AnonymousClass1(this, r46, r45);
            r033.setGravity(17);
            r033.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, LimitReachedBottomSheet.access$4900(r45)));
            r033.setText(" " + LocaleController.getString(R.string.InvitePremiumBlockedOr) + " ");
            r033.setTextSize(14.0f);
            addView(r033, LayoutHelper.createLinear(190, -2, 1, 12, 0, 12, 20));
            TextView r034 = new TextView(r46);
            r034.setGravity(17);
            r034.setTypeface(AndroidUtilities.bold());
            r034.setTextSize(1, 20.0f);
            r034.setTextColor(Theme.getColor(r57, LimitReachedBottomSheet.access$5000(r45)));
            r034.setText(LocaleController.getString(R.string.InviteBlockedTitle));
            addView(r034, LayoutHelper.createLinear(-1, -2, 1, 32, 0, 32, 9));
            TextView r035 = new TextView(r46);
            r035.setGravity(17);
            r035.setTextSize(1, 14.0f);
            r035.setTextColor(Theme.getColor(r57, LimitReachedBottomSheet.access$5100(r45)));
            if (LimitReachedBottomSheet.m12006$$Nest$fgetpremiumInviteBlockedUsers(r45).size() > 1) goto L300;
            r035.setText(LocaleController.getString(R.string.InviteBlockedOneMessage));
        L301:
            addView(r035, LayoutHelper.createLinear(-1, -2, 1, 32, 0, 32, 19));
            goto L303
        L300:
            r035.setText(LocaleController.getString(R.string.InviteBlockedManyMessage));
            goto L301
        L289:
            r38 = LimitReachedBottomSheet.m12007$$Nest$fgetpremiumMessagingBlockedUsers(r45).size();
            goto L291
        L285:
            r031 = LimitReachedBottomSheet.m12006$$Nest$fgetpremiumInviteBlockedUsers(r45).size();
            goto L287
        L256:
            if (r74 == false) goto L258;
            r37 = R.string.InviteMessagePremiumBlockedOne;
            goto L259
        L258:
            r37 = R.string.InvitePremiumBlockedOne;
            goto L259
        L261:
            if (r029.size() != 2) goto L269;
            if (r67 == false) goto L264;
            int r39 = R.string.InviteCallMessagePremiumBlockedTwo;
        L267:
            r030 = LocaleController.formatString(r39, new Object[]{UserObject.getForcedFirstName(MessagesController.getInstance(LimitReachedBottomSheet.access$3700(r45)).getUser((Long) r029.get(0))), UserObject.getForcedFirstName(MessagesController.getInstance(LimitReachedBottomSheet.access$3800(r45)).getUser((Long) r029.get(1)))});
            goto L282
        L264:
            if (r74 == false) goto L266;
            r39 = R.string.InviteMessagePremiumBlockedTwo;
            goto L267
        L266:
            r39 = R.string.InvitePremiumBlockedTwo;
            goto L267
        L269:
            if (r029.size() != 3) goto L276;
            if (r67 == false) goto L272;
            int r310 = R.string.InviteCallMessagePremiumBlockedThree;
        L275:
            r030 = LocaleController.formatString(r310, new Object[]{UserObject.getForcedFirstName(MessagesController.getInstance(LimitReachedBottomSheet.access$3900(r45)).getUser((Long) r029.get(0))), UserObject.getForcedFirstName(MessagesController.getInstance(LimitReachedBottomSheet.access$4000(r45)).getUser((Long) r029.get(1))), UserObject.getForcedFirstName(MessagesController.getInstance(LimitReachedBottomSheet.access$4100(r45)).getUser((Long) r029.get(2)))});
            goto L282
        L272:
            if (r74 == false) goto L274;
            r310 = R.string.InviteMessagePremiumBlockedThree;
            goto L275
        L274:
            r310 = R.string.InvitePremiumBlockedThree;
            goto L275
        L276:
            if (r67 == false) goto L278;
            String r68 = "InviteCallMessagePremiumBlockedMany";
        L281:
            String r69 = LocaleController.formatPluralString(r68, r029.size() - 2, new Object[]{UserObject.getForcedFirstName(MessagesController.getInstance(LimitReachedBottomSheet.access$4200(r45)).getUser((Long) r029.get(0))), UserObject.getForcedFirstName(MessagesController.getInstance(LimitReachedBottomSheet.access$4300(r45)).getUser((Long) r029.get(1)))});
            r36.setPlus(r029.size() - 2, LimitReachedBottomSheet.access$4400(r45, Theme.key_dialogBackground));
            r030 = r69;
            goto L282
        L278:
            if (r74 == false) goto L280;
            r68 = "InviteMessagePremiumBlockedMany";
            goto L281
        L280:
            r68 = "InvitePremiumBlockedMany";
            goto L281
        L249:
            if (LimitReachedBottomSheet.m12007$$Nest$fgetpremiumMessagingBlockedUsers(r45).size() < LimitReachedBottomSheet.m12006$$Nest$fgetpremiumInviteBlockedUsers(r45).size()) goto L251;
            r74 = true;
            goto L253
        L245:
            r67 = false;
            goto L247
        L238:
            r029 = LimitReachedBottomSheet.m12006$$Nest$fgetpremiumInviteBlockedUsers(r45);
        L232:
            if (LimitReachedBottomSheet.m12007$$Nest$fgetpremiumMessagingBlockedUsers(r45) == null) goto L305;
            if (LimitReachedBottomSheet.m12007$$Nest$fgetpremiumMessagingBlockedUsers(r45).size() < LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size()) goto L305;
        L187:
            if (r7 == 20) goto L216;
            if (r7 == 24) goto L216;
            if (r7 == 25) goto L216;
            if (r7 == 26) goto L216;
            if (r7 == 29) goto L216;
            if (r7 == 22) goto L216;
            if (r7 == 23) goto L216;
            if (r7 == 19) goto L216;
            if (r7 == 30) goto L216;
            if (r7 == 35) goto L216;
            if (r7 == 21) goto L216;
            if (r7 == 27) goto L216;
            if (r7 == 28) goto L216;
            if (r7 == 32) goto L216;
            r15 = false;
            goto L217
        L171:
            if (LimitReachedBottomSheet.m12003$$Nest$fgetisVeryLargeFile(r45) == true) goto L183;
            if (r06 >= 0) goto L176;
            r06 = r42;
        L176:
            if (r45.type != 7) goto L182;
            if (r06 <= r42) goto L181;
            float r610 = r06 - r42;
            float r75 = r52 - r42;
        L179:
            float r611 = r610 / r75;
        L180:
            r9 = r611;
            goto L184
        L181:
            r611 = 0.5f;
            goto L180
        L182:
            r610 = r06;
            r75 = r52;
            goto L179
        L155:
            if (r62 != 7) goto L157;
            r06 = UserConfig.getActivatedAccountsCount();
            goto L169
        L157:
            if (r62 != 0) goto L169;
            ArrayList<TLRPC.Dialog> r036 = MessagesController.getInstance(LimitReachedBottomSheet.access$2900(r45)).getDialogs(0);
            int r612 = r036.size();
            int r76 = 0;
            int r322 = 0;
        L159:
            if (r76 >= r612) goto L167;
            TLRPC.Dialog r11 = r036.get(r76);
            if ((r11 instanceof TLRPC.TL_dialogFolder) == true) goto L166;
            if (r11.pinned == false) goto L166;
            r322 = r322 + 1;
        L166:
            r76 = r76 + 1;
            goto L159
        L167:
            r06 = r322;
            goto L169
        L6:
            r03 = R.string.BoostingAdditionalFeaturesSubtitleChannel;
            goto L7
        L9:
            if (r6 != 32) goto L11;
            r04 = LimitReachedBottomSheet.m12012$$Nest$mgetBoostsDescriptionString(r45, true);
            goto L8
        L11:
            if (r6 == 19) goto L13;
            if (r6 == 18) goto L29;
            if (r6 != 20) goto L44;
            if (r5 == false) goto L41;
            int r037 = R.string.GroupNeedBoostsForColorDescription;
        L42:
            r04 = LocaleController.formatString(r037, new Object[]{Integer.valueOf(r45.channelColorLevelMin())});
            goto L8
        L41:
            r037 = R.string.ChannelNeedBoostsForColorDescription;
            goto L42
        L44:
            if (r6 != 24) goto L50;
            if (r5 == false) goto L47;
            int r038 = R.string.GroupNeedBoostsForProfileColorDescription;
        L48:
            r04 = LocaleController.formatString(r038, new Object[]{Integer.valueOf(r45.channelColorLevelMin())});
            goto L8
        L47:
            r038 = R.string.ChannelNeedBoostsForProfileColorDescription;
            goto L48
        L50:
            if (r6 != 29) goto L53;
            r04 = LocaleController.formatString(R.string.GroupNeedBoostsForCustomEmojiPackDescription, new Object[]{Integer.valueOf(r02.groupEmojiStickersLevelMin)});
            goto L8
        L53:
            if (r6 != 30) goto L56;
            r04 = LocaleController.formatString(R.string.ChannelNeedBoostsForSwitchOffAdsDescription, new Object[]{Integer.valueOf(r02.channelRestrictSponsoredLevelMin)});
            goto L8
        L56:
            if (r6 != 35) goto L59;
            r04 = LocaleController.formatString(R.string.ChannelNeedBoostsForAutotranslationDescription, new Object[]{Integer.valueOf(r02.channelAutotranslationLevelMin)});
            goto L8
        L59:
            if (r6 != 25) goto L68;
            if (r5 == false) goto L62;
            int r613 = R.string.GroupNeedBoostsForEmojiStatusDescription;
        L63:
            if (r5 == false) goto L65;
            int r039 = r02.groupEmojiStatusLevelMin;
        L66:
            r04 = LocaleController.formatString(r613, new Object[]{Integer.valueOf(r039)});
            goto L8
        L65:
            r039 = r02.channelEmojiStatusLevelMin;
            goto L66
        L62:
            r613 = R.string.ChannelNeedBoostsForEmojiStatusDescription;
            goto L63
        L68:
            if (r6 != 26) goto L77;
            if (r5 == false) goto L71;
            int r614 = R.string.GroupNeedBoostsForWearCollectiblesDescription;
        L72:
            if (r5 == false) goto L74;
            int r040 = r02.groupEmojiStatusLevelMin;
        L75:
            r04 = LocaleController.formatString(r614, new Object[]{Integer.valueOf(r040)});
            goto L8
        L74:
            r040 = r02.channelEmojiStatusLevelMin;
            goto L75
        L71:
            r614 = R.string.ChannelNeedBoostsForWearCollectiblesDescription;
            goto L72
        L77:
            if (r6 != 27) goto L83;
            if (r5 == false) goto L80;
            int r58 = R.string.GroupNeedBoostsForReplyIconDescription;
        L81:
            r04 = LocaleController.formatString(r58, new Object[]{Integer.valueOf(r02.channelBgIconLevelMin)});
            goto L8
        L80:
            r58 = R.string.ChannelNeedBoostsForReplyIconDescription;
            goto L81
        L83:
            if (r6 != 28) goto L92;
            if (r5 == false) goto L86;
            int r615 = R.string.GroupNeedBoostsForProfileIconDescription;
        L87:
            if (r5 == false) goto L89;
            int r041 = r02.groupProfileBgIconLevelMin;
        L90:
            r04 = LocaleController.formatString(r615, new Object[]{Integer.valueOf(r041)});
            goto L8
        L89:
            r041 = r02.channelProfileIconLevelMin;
            goto L90
        L86:
            r615 = R.string.ChannelNeedBoostsForProfileIconDescription;
            goto L87
        L92:
            if (r6 != 22) goto L101;
            if (r5 == false) goto L95;
            int r616 = R.string.GroupNeedBoostsForWallpaperDescription;
        L96:
            if (r5 == false) goto L98;
            int r042 = r02.groupWallpaperLevelMin;
        L99:
            r04 = LocaleController.formatString(r616, new Object[]{Integer.valueOf(r042)});
            goto L8
        L98:
            r042 = r02.channelWallpaperLevelMin;
            goto L99
        L95:
            r616 = R.string.ChannelNeedBoostsForWallpaperDescription;
            goto L96
        L101:
            if (r6 != 23) goto L110;
            if (r5 == false) goto L104;
            int r617 = R.string.GroupNeedBoostsForCustomWallpaperDescription;
        L105:
            if (r5 == false) goto L107;
            int r043 = r02.groupCustomWallpaperLevelMin;
        L108:
            r04 = LocaleController.formatString(r617, new Object[]{Integer.valueOf(r043)});
            goto L8
        L107:
            r043 = r02.channelCustomWallpaperLevelMin;
            goto L108
        L104:
            r617 = R.string.ChannelNeedBoostsForCustomWallpaperDescription;
            goto L105
        L110:
            if (r6 != 21) goto L113;
            r04 = LocaleController.formatPluralString("ReactionReachLvlForReaction", LimitReachedBottomSheet.m12008$$Nest$fgetrequiredLvl(r45), new Object[]{Integer.valueOf(LimitReachedBottomSheet.m12008$$Nest$fgetrequiredLvl(r45))});
            goto L8
        L113:
            if (r6 != 11) goto L138;
            if (LimitReachedBottomSheet.m11995$$Nest$fgetcanSendLink(r45) == true) goto L128;
            if (ChatObject.isChannelAndNotMegaGroup(LimitReachedBottomSheet.m11999$$Nest$fgetfromChat(r45)) == false) goto L124;
            if (LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size() != 1) goto L122;
            String r044 = LocaleController.formatString(R.string.InviteChannelRestrictedUsers2One, new Object[]{ContactsController.formatName((TLRPC.User) LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).get(0))});
        L121:
            r13 = r044;
            r14 = true;
            goto L152
        L122:
            r044 = LocaleController.formatPluralString("InviteChannelRestrictedUsers2", LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size(), new Object[]{Integer.valueOf(LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size())});
            goto L121
        L124:
            if (LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size() != 1) goto L126;
            r044 = LocaleController.formatString(R.string.InviteRestrictedUsers2One, new Object[]{ContactsController.formatName((TLRPC.User) LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).get(0))});
            goto L121
        L126:
            r044 = LocaleController.formatPluralString("InviteRestrictedUsers2", LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size(), new Object[]{Integer.valueOf(LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size())});
            goto L121
        L128:
            if (ChatObject.isChannelAndNotMegaGroup(LimitReachedBottomSheet.m11999$$Nest$fgetfromChat(r45)) == false) goto L134;
            if (LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size() != 1) goto L132;
            r044 = LocaleController.formatString(R.string.InviteChannelRestrictedUsersOne, new Object[]{ContactsController.formatName((TLRPC.User) LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).get(0))});
            goto L121
        L132:
            r044 = LocaleController.formatPluralString("InviteChannelRestrictedUsers", LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size(), new Object[]{Integer.valueOf(LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size())});
            goto L121
        L134:
            if (LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size() != 1) goto L136;
            r044 = LocaleController.formatString(R.string.InviteRestrictedUsersOne, new Object[]{ContactsController.formatName((TLRPC.User) LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).get(0))});
            goto L121
        L136:
            r044 = LocaleController.formatPluralString("InviteRestrictedUsers", LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size(), new Object[]{Integer.valueOf(LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size())});
            goto L121
        L138:
            if (r6 == 34) goto L140;
            if (r4 == false) goto L146;
            r04 = r45.limitParams.descriptionStrLocked;
            goto L8
        L146:
            if (UserConfig.getInstance(LimitReachedBottomSheet.access$2700(r45)).isPremium() == false) goto L148;
        L151:
            r04 = r45.limitParams.descriptionStrPremium;
            goto L8
        L148:
            if (LimitReachedBottomSheet.m12003$$Nest$fgetisVeryLargeFile(r45) == true) goto L151;
            r04 = r45.limitParams.descriptionStr;
            goto L8
        L140:
            if (LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size() != 1) goto L142;
            r04 = LocaleController.formatString(R.string.InviteCallRestrictedUsersOne, new Object[]{ContactsController.formatName((TLRPC.User) LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).get(0))});
            goto L8
        L142:
            r04 = LocaleController.formatPluralString("InviteCallRestrictedUsers", LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size(), new Object[]{Integer.valueOf(LimitReachedBottomSheet.m12009$$Nest$fgetrestrictedUsers(r45).size())});
            goto L8
        L29:
            if (LimitReachedBottomSheet.m11993$$Nest$fgetboostsStatus(r45).level != 0) goto L34;
            if (r5 == false) goto L32;
            int r045 = R.string.GroupNeedBoostsDescription;
        L33:
            r04 = LocaleController.formatString(r045, new Object[]{LocaleController.formatPluralString("MoreBoosts", LimitReachedBottomSheet.m11993$$Nest$fgetboostsStatus(r45).next_level_boosts, new Object[]{Integer.valueOf(LimitReachedBottomSheet.m11993$$Nest$fgetboostsStatus(r45).next_level_boosts)})});
            goto L8
        L32:
            r045 = R.string.ChannelNeedBoostsDescription;
            goto L33
        L34:
            if (r5 == false) goto L36;
            int r046 = R.string.GroupNeedBoostsDescriptionNextLevel;
        L37:
            r04 = LocaleController.formatString(r046, new Object[]{LocaleController.formatPluralString("MoreBoosts", LimitReachedBottomSheet.m11993$$Nest$fgetboostsStatus(r45).next_level_boosts - LimitReachedBottomSheet.m11993$$Nest$fgetboostsStatus(r45).boosts, new Object[]{Integer.valueOf(LimitReachedBottomSheet.m11993$$Nest$fgetboostsStatus(r45).next_level_boosts - LimitReachedBottomSheet.m11993$$Nest$fgetboostsStatus(r45).boosts)}), LocaleController.formatPluralString("BoostStories", LimitReachedBottomSheet.m11993$$Nest$fgetboostsStatus(r45).level + 1, new Object[0])});
            goto L8
        L36:
            r046 = R.string.ChannelNeedBoostsDescriptionNextLevel;
            goto L37
        L13:
            if (LimitReachedBottomSheet.m11996$$Nest$fgetchatMessageCell(r45) == null) goto L20;
            int r047 = LimitReachedBottomSheet.m11996$$Nest$fgetchatMessageCell(r45).getMessageObject().messageOwner.from_boosts_applied;
            TLRPC.Chat r59 = LimitReachedBottomSheet.m12014$$Nest$mgetChat(r45);
            SpannableStringBuilder r618 = new SpannableStringBuilder();
            r618.append(LocaleController.formatPluralString("GroupBoostedByUserWithTimes", r047, new Object[]{UserObject.getFirstName(LimitReachedBottomSheet.m11996$$Nest$fgetchatMessageCell(r45).getCurrentUser())}));
            r618.append(" ");
            int r048 = R.string.GroupBoostedByUserWithDescription;
            if (r59 != null) goto L17;
            String r510 = _UrlKt.FRAGMENT_ENCODE_SET;
        L18:
            r618.append(LocaleController.formatString(r048, new Object[]{r510}));
            r04 = r618.toString();
            goto L8
        L17:
            r510 = r59.title;
            goto L18
        L20:
            if ((r45.getBaseFragment() instanceof GroupColorActivity) == false) goto L22;
            r04 = LocaleController.formatPluralString("BoostingGroupBoostWhatAreBoostsDescription", BoostRepository.giveawayBoostsPerPremium(), new Object[0]);
            goto L8
        L22:
            SpannableStringBuilder r049 = new SpannableStringBuilder(LimitReachedBottomSheet.m12012$$Nest$mgetBoostsDescriptionString(r45, true));
            if (ChatObject.hasAdminRights(LimitReachedBottomSheet.m12014$$Nest$mgetChat(r45)) == false) goto L26;
            if (r5 == false) goto L26;
            r049.append(" ").append(LocaleController.getString(R.string.BoostingPremiumUserCanBoostGroupWithLink));
        L26:
            r04 = r049.toString();
            goto L8
        }

        private /* synthetic */ void lambda$new$0(View r3) {
            if (this.this$0.parentFragment != null) goto L5;
            return;
        L5:
            BaseFragment.BottomSheetParams r32 = new BaseFragment.BottomSheetParams();
            r32.transitionFromLeft = true;
            r32.allowNestedScroll = false;
            this.this$0.parentFragment.showAsSheet(new PremiumPreviewFragment("invite_privacy"), r32);
        }

        private /* synthetic */ void lambda$new$1(View r3) {
            this.this$0.getBaseFragment().presentFragment(ChatActivity.of(LimitReachedBottomSheet.m11998$$Nest$fgetdialogId(this.this$0)));
            this.this$0.dismiss();
        }

        public void recreateTitleAndDescription() {
            int r0 = indexOfChild(this.description);
            if (LimitReachedBottomSheet.m12002$$Nest$fgetisCurrentChat(this.this$0) == false) goto L5;
            int r1 = indexOfChild(this.titleLinearLayout);
            removeView(this.titleLinearLayout);
            this.titleLinearLayout.removeView(this.title);
            this.titleLinearLayout.removeView(this.boostCounterView);
            LinearLayout r3 = new LinearLayout(getContext());
            this.titleLinearLayout = r3;
            r3.setOrientation(0);
            this.titleLinearLayout.setWeightSum(1.0f);
            this.titleLinearLayout.addView(this.title, LayoutHelper.createLinear(-2, -2, 1.0f, 0));
            this.titleLinearLayout.addView(this.boostCounterView, LayoutHelper.createLinear(-2, -2, 48, 0, 2, 0, 0));
            addView(this.titleLinearLayout, r1, LayoutHelper.createLinear(-2, -2, 1, 25, 22, 12, 9));
        L6:
            removeView(this.description);
            TextView r12 = new TextView(getContext());
            this.description = r12;
            r12.setTextSize(1, 14.0f);
            TextView r13 = this.description;
            r13.setLineSpacing(r13.getLineSpacingExtra(), this.description.getLineSpacingMultiplier() * 1.1f);
            this.description.setGravity(1);
            this.description.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, LimitReachedBottomSheet.access$6500(this.this$0)));
            addView(this.description, r0, LayoutHelper.createLinear(-2, -2, 1, 24, -2, 24, 17));
            return;
        L5:
            int r14 = indexOfChild(this.title);
            removeView(this.title);
            TextView r32 = new TextView(getContext());
            this.title = r32;
            r32.setTypeface(AndroidUtilities.bold());
            this.title.setTextSize(1, 20.0f);
            this.title.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, LimitReachedBottomSheet.access$6400(this.this$0)));
            this.title.setGravity(17);
            addView(this.title, r14, LayoutHelper.createLinear(-2, -2, 1, 0, 22, 0, 0));
            goto L6
        }
    }

    public static class LimitParams {
        int defaultLimit;
        String descriptionStr;
        String descriptionStrLocked;
        String descriptionStrPremium;
        int icon;
        int premiumLimit;

        public LimitParams() {
            this.icon = 0;
            this.descriptionStr = null;
            this.descriptionStrPremium = null;
            this.descriptionStrLocked = null;
            this.defaultLimit = 0;
            this.premiumLimit = 0;
        }
    }

    /* renamed from: $r8$lambda$0bQ8oxnZu4fWI-Ecry8RyNB91WA, reason: not valid java name */
    public static /* synthetic */ void m11982$r8$lambda$0bQ8oxnZu4fWIEcry8RyNB91WA(LimitReachedBottomSheet r0) {
        r0.lambda$onViewCreated$13();
    }

    public static /* synthetic */ void $r8$lambda$1LH66od9HwPXPxdShjXSx6TqaAs(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    /* renamed from: $r8$lambda$5t3YSnQduSF3z-YfOOjDLRIOIi4, reason: not valid java name */
    public static /* synthetic */ void m11983$r8$lambda$5t3YSnQduSF3zYfOOjDLRIOIi4(LimitReachedBottomSheet r0) {
        r0.lambda$showPremiumBlockedToast$15();
    }

    public static /* synthetic */ void $r8$lambda$C6HrROTgiTjMWuPERfMJW_3ymTI(LimitReachedBottomSheet r0, Loadable r1, TL_stories.TL_premium_myBoosts r2) {
        r0.lambda$boostChannel$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$JSZrBHFliFeLNzfW95HX9O1RV54(LimitReachedBottomSheet r0) {
        r0.lambda$sendInviteMessages$20();
    }

    public static /* synthetic */ void $r8$lambda$KnLmQ_6ISPzwUq3XrZDdJiK529U(LimitReachedBottomSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadInactiveChannels$27(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$L4p3DNebjpow1TjRuG8Yk6IS1KQ(LimitReachedBottomSheet r0, Loadable r1) {
        r0.lambda$boostChannel$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$LN5LWLUXXmwRFBoE0Wm_HuJgAFg(LimitReachedBottomSheet r0, ArrayList r1, AlertDialog r2, int r3) {
        r0.lambda$revokeLinks$25(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$M0vtN8AidCBJKTEd_fN6SyjWZCE(LimitReachedBottomSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$MFd7CkKgRNhuT-gB3IAuGKso7_E, reason: not valid java name */
    public static /* synthetic */ void m11984$r8$lambda$MFd7CkKgRNhuTgB3IAuGKso7_E(LimitReachedBottomSheet r0, AlertDialog r1, int r2) {
        r0.lambda$onViewCreated$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Mpg53iSBzPqVvrRTnKNAXG5CjPk(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$Ri3PGSoqvNXOzqhkiA2tO_KPBuE(LimitReachedBottomSheet r0, TLObject r1) {
        r0.lambda$loadAdminedChannels$22(r1);
    }

    /* renamed from: $r8$lambda$YflJU7X9DJoUR-JQZx7pDbsPNXM, reason: not valid java name */
    public static /* synthetic */ void m11985$r8$lambda$YflJU7X9DJoURJQZx7pDbsPNXM(LimitReachedBottomSheet r0, ArrayList r1, TLRPC.User r2, AlertDialog r3, int r4) {
        r0.lambda$leaveFromSelectedGroups$21(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$YjX9okCNMaKHpLC12iX596aGxyE(LimitReachedBottomSheet r0, ArrayList r1, int r2, TLRPC.TL_messages_inactiveChats r3) {
        r0.lambda$loadInactiveChannels$26(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$bceyYWa3BA6nkx1t4gPBftv5wvM(LimitReachedBottomSheet r0) {
        r0.lambda$new$2();
    }

    /* renamed from: $r8$lambda$cbAPdgsWNAV-9jHH_ifRviwrDws, reason: not valid java name */
    public static /* synthetic */ void m11986$r8$lambda$cbAPdgsWNAV9jHH_ifRviwrDws(LimitReachedBottomSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$dnmI5Y1E5IAyX5sKv1YtcIeGu5k(LimitReachedBottomSheet r0, View r1) {
        r0.lambda$onViewCreated$14(r1);
    }

    /* renamed from: $r8$lambda$fOrjyoAmbGhWN-NhHP5PJVyKtGk, reason: not valid java name */
    public static /* synthetic */ void m11987$r8$lambda$fOrjyoAmbGhWNNhHP5PJVyKtGk(LimitReachedBottomSheet r0, Loadable r1, TL_stories.TL_premium_myBoosts r2, TL_stories.TL_premium_boostsStatus r3) {
        r0.lambda$boostChannel$16(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$gAfmBPqekcQxY-tkfAJx9P6s0nc, reason: not valid java name */
    public static /* synthetic */ void m11988$r8$lambda$gAfmBPqekcQxYtkfAJx9P6s0nc(LimitReachedBottomSheet r0, DialogInterface r1) {
        r0.lambda$onViewCreated$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$inE5Z2sohCCCF5e_hajDs5D4Zqs(LimitReachedBottomSheet r0, View r1, int r2) {
        r0.lambda$onViewCreated$5(r1, r2);
    }

    /* renamed from: $r8$lambda$pO7v-1vg24dAkweU3RfXpA-l-WQ, reason: not valid java name */
    public static /* synthetic */ void m11989$r8$lambda$pO7v1vg24dAkweU3RfXpAlWQ(LimitReachedBottomSheet r0, Context r1, View r2) {
        r0.lambda$onViewCreated$12(r1, r2);
    }

    /* renamed from: $r8$lambda$pVsVg1faed-RXDRqcF1gILDFoTU, reason: not valid java name */
    public static /* synthetic */ boolean m11990$r8$lambda$pVsVg1faedRXDRqcF1gILDFoTU(LimitReachedBottomSheet r0, View r1, int r2) {
        return r0.lambda$onViewCreated$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qRXzL0Jut9OOHOzp2pmckC5srPo(LimitReachedBottomSheet r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$rYQCvvwXehauvq_mtMawLiLJzfs(LimitReachedBottomSheet r0, View r1) {
        r0.lambda$onViewCreated$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$rl4ZO4rh0c6LrJKv7VBYJhefTSE(LimitReachedBottomSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadAdminedChannels$23(r1, r2);
    }

    /* renamed from: $r8$lambda$ro8w7Ja_w-gEEQkltVUX9mdWjtU, reason: not valid java name */
    public static /* synthetic */ void m11991$r8$lambda$ro8w7Ja_wgEEQkltVUX9mdWjtU(LimitReachedBottomSheet r0, AlertDialog r1, int r2) {
        r0.lambda$onViewCreated$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vVg2SMU8W8LsrIauEWyAlaPfg1U(LimitReachedBottomSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$revokeLinks$24(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$x6IvzePHnUd6ndGizBdYLZNLOvs(LimitReachedBottomSheet r0, Loadable r1, TLRPC.TL_error r2) {
        r0.lambda$boostChannel$19(r1, r2);
    }

    /* renamed from: $r8$lambda$yn-iKyBmVjVmzwZQskCFiINYz0E, reason: not valid java name */
    public static /* synthetic */ void m11992$r8$lambda$yniKyBmVjVmzwZQskCFiINYz0E(LimitReachedBottomSheet r0, HashMap r1) {
        r0.sendInviteMessages(r1);
    }

    /* renamed from: -$$Nest$fgetboostsStatus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_stories.TL_premium_boostsStatus m11993$$Nest$fgetboostsStatus(LimitReachedBottomSheet r0) {
        return r0.boostsStatus;
    }

    /* renamed from: -$$Nest$fgetcanApplyBoost, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChannelBoostsController.CanApplyBoost m11994$$Nest$fgetcanApplyBoost(LimitReachedBottomSheet r0) {
        return r0.canApplyBoost;
    }

    /* renamed from: -$$Nest$fgetcanSendLink, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11995$$Nest$fgetcanSendLink(LimitReachedBottomSheet r0) {
        return r0.canSendLink;
    }

    /* renamed from: -$$Nest$fgetchatMessageCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m11996$$Nest$fgetchatMessageCell(LimitReachedBottomSheet r0) {
        return r0.chatMessageCell;
    }

    /* renamed from: -$$Nest$fgetcurrentValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11997$$Nest$fgetcurrentValue(LimitReachedBottomSheet r0) {
        return r0.currentValue;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11998$$Nest$fgetdialogId(LimitReachedBottomSheet r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetfromChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m11999$$Nest$fgetfromChat(LimitReachedBottomSheet r0) {
        return r0.fromChat;
    }

    /* renamed from: -$$Nest$fgetinactiveChats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12000$$Nest$fgetinactiveChats(LimitReachedBottomSheet r0) {
        return r0.inactiveChats;
    }

    /* renamed from: -$$Nest$fgetinactiveChatsSignatures, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12001$$Nest$fgetinactiveChatsSignatures(LimitReachedBottomSheet r0) {
        return r0.inactiveChatsSignatures;
    }

    /* renamed from: -$$Nest$fgetisCurrentChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12002$$Nest$fgetisCurrentChat(LimitReachedBottomSheet r0) {
        return r0.isCurrentChat;
    }

    /* renamed from: -$$Nest$fgetisVeryLargeFile, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12003$$Nest$fgetisVeryLargeFile(LimitReachedBottomSheet r0) {
        return r0.isVeryLargeFile;
    }

    /* renamed from: -$$Nest$fgetlinkRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12004$$Nest$fgetlinkRow(LimitReachedBottomSheet r0) {
        return r0.linkRow;
    }

    /* renamed from: -$$Nest$fgetlockInvalidation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12005$$Nest$fgetlockInvalidation(LimitReachedBottomSheet r0) {
        return r0.lockInvalidation;
    }

    /* renamed from: -$$Nest$fgetpremiumInviteBlockedUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12006$$Nest$fgetpremiumInviteBlockedUsers(LimitReachedBottomSheet r0) {
        return r0.premiumInviteBlockedUsers;
    }

    /* renamed from: -$$Nest$fgetpremiumMessagingBlockedUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12007$$Nest$fgetpremiumMessagingBlockedUsers(LimitReachedBottomSheet r0) {
        return r0.premiumMessagingBlockedUsers;
    }

    /* renamed from: -$$Nest$fgetrequiredLvl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12008$$Nest$fgetrequiredLvl(LimitReachedBottomSheet r0) {
        return r0.requiredLvl;
    }

    /* renamed from: -$$Nest$fgetrestrictedUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12009$$Nest$fgetrestrictedUsers(LimitReachedBottomSheet r0) {
        return r0.restrictedUsers;
    }

    /* renamed from: -$$Nest$fputheaderView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12010$$Nest$fputheaderView(LimitReachedBottomSheet r0, HeaderView r1) {
        r0.headerView = r1;
    }

    /* renamed from: -$$Nest$mgetBoostLink, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m12011$$Nest$mgetBoostLink(LimitReachedBottomSheet r0) {
        return r0.getBoostLink();
    }

    /* renamed from: -$$Nest$mgetBoostsDescriptionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m12012$$Nest$mgetBoostsDescriptionString(LimitReachedBottomSheet r0, boolean r1) {
        return r0.getBoostsDescriptionString(r1);
    }

    /* renamed from: -$$Nest$mgetBoostsTitleString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m12013$$Nest$mgetBoostsTitleString(LimitReachedBottomSheet r0) {
        return r0.getBoostsTitleString();
    }

    /* renamed from: -$$Nest$mgetChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m12014$$Nest$mgetChat(LimitReachedBottomSheet r0) {
        return r0.getChat();
    }

    /* renamed from: -$$Nest$misGroup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12015$$Nest$misGroup(LimitReachedBottomSheet r0) {
        return r0.isGroup();
    }

    /* renamed from: -$$Nest$misMiniBoostBtnForAdminAvailable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12016$$Nest$misMiniBoostBtnForAdminAvailable(LimitReachedBottomSheet r0) {
        return r0.isMiniBoostBtnForAdminAvailable();
    }

    /* renamed from: -$$Nest$mrevokeLinks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12017$$Nest$mrevokeLinks(LimitReachedBottomSheet r0, ArrayList r1) {
        r0.revokeLinks(r1);
    }

    /* renamed from: -$$Nest$smgetLimitParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LimitParams m12018$$Nest$smgetLimitParams(int r0, int r1) {
        return getLimitParams(r0, r1);
    }

    public LimitReachedBottomSheet(BaseFragment r9, Context r10, int r11, int r12, Theme.ResourcesProvider r13) {
        super(r10, r9, false, hasFixedSize(r11), false, r13);
        this.linkRow = -1;
        this.lockInvalidation = false;
        this.chats = new ArrayList();
        this.headerRow = -1;
        this.dividerRow = -1;
        this.chatsTitleRow = -1;
        this.chatStartRow = -1;
        this.chatEndRow = -1;
        this.loadingRow = -1;
        this.emptyViewDividerRow = -1;
        this.bottomRow = -1;
        this.boostFeaturesStartRow = -1;
        this.currentValue = -1;
        this.selectedChats = new HashSet();
        this.inactiveChats = new ArrayList();
        this.inactiveChatsSignatures = new ArrayList();
        this.restrictedUsers = new ArrayList();
        this.premiumMessagingBlockedUsers = new ArrayList();
        this.premiumInviteBlockedUsers = new ArrayList();
        this.loading = false;
        this.requiredLvl = 0;
        this.shiftDp = -4;
        fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
        this.parentFragment = r9;
        this.currentAccount = r12;
        this.type = r11;
        updateTitle();
        updateRows();
        if (r11 != 2) goto L6;
        loadAdminedChannels();
    L8:
        updatePremiumButtonText();
        if (r11 != 32) goto L11;
    L12:
        FireworksOverlay r122 = new FireworksOverlay(getContext());
        this.fireworksOverlay = r122;
        this.container.addView(r122, LayoutHelper.createFrame(-1, -1.0f));
    L14:
        if (r11 != 18) goto L16;
    L41:
        ((ViewGroup) this.premiumButtonView.getParent()).removeView(this.premiumButtonView);
        View r123 = this.divider;
        if (r123 == null) goto L44;
        ((ViewGroup) r123.getParent()).removeView(this.divider);
    L44:
        this.recyclerListView.setPadding(0, 0, 0, 0);
        TextView r124 = new TextView(r10);
        this.actionBtn = r124;
        r124.setGravity(17);
        this.actionBtn.setEllipsize(TextUtils.TruncateAt.END);
        this.actionBtn.setSingleLine(true);
        this.actionBtn.setTextSize(1, 14.0f);
        this.actionBtn.setTypeface(AndroidUtilities.bold());
        this.actionBtn.setText(this.premiumButtonView.getTextView().getText());
        this.actionBtn.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r13));
        this.actionBtn.setOnClickListener(new LimitReachedBottomSheet$$ExternalSyntheticLambda0(this));
        this.actionBtn.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r13), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhite, r13), 120)));
    L45:
        if (r11 != 32) goto L48;
        ((ViewGroup) this.premiumButtonView.getParent()).removeView(this.premiumButtonView);
        ButtonWithCounterView r102 = new ButtonWithCounterView(r10, r13);
        this.boostToUnlockGroupBtn = r102;
        r102.withCounterIcon();
        this.boostToUnlockGroupBtn.setText(LocaleController.getString(R.string.BoostGroup), false);
        this.boostToUnlockGroupBtn.setOnClickListener(new LimitReachedBottomSheet$$ExternalSyntheticLambda1(this));
        this.containerView.addView(this.boostToUnlockGroupBtn, LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 2.0f, 16.0f, 12.0f));
        this.containerView.post(new LimitReachedBottomSheet$$ExternalSyntheticLambda2(this));
    L48:
        if (r11 == 19) goto L52;
        if (r11 == 18) goto L52;
        return;
    L52:
        this.containerView.post(new LimitReachedBottomSheet$$ExternalSyntheticLambda3(this));
        return;
    L16:
        if (r11 == 20) goto L41;
        if (r11 == 24) goto L41;
        if (r11 == 25) goto L41;
        if (r11 == 26) goto L41;
        if (r11 == 29) goto L41;
        if (r11 == 22) goto L41;
        if (r11 == 23) goto L41;
        if (r11 == 21) goto L41;
        if (r11 == 27) goto L41;
        if (r11 == 28) goto L41;
        if (r11 == 30) goto L41;
        if (r11 == 35) goto L41;
        if (r11 != 31) goto L45;
    L11:
        if (isBoostingForAdminPossible() == false) goto L14;
    L6:
        if (r11 != 5) goto L8;
        loadInactiveChannels();
        goto L8
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$100(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1000(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1100(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1200(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1300(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1400(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1500(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1600(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1700(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1800(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1900(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$200(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$2000(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$2100(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2200(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$2300(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$2400(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$2500(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2600(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2700(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2800(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2900(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$3000(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3100(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3200(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3300(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$3400(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$3500(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$3600(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3700(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3800(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3900(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$4000(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4100(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4200(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4300(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4400(LimitReachedBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4500(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$4600(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$4700(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4800(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4900(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$500(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$5000(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$5100(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$5200(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$5300(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$5400(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$5500(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$5600(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$5700(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$5800(LimitReachedBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$5900(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$600(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6000(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6100(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6200(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6300(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6400(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6500(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$6600(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$6700(LimitReachedBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$700(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$800(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$900(LimitReachedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    private void boostChannel() {
        if (this.boostMiniBtn.isAttachedToWindow() == false) goto L6;
        boostChannel(this.boostMiniBtn);
        return;
    L6:
        ButtonWithCounterView r0 = this.boostToUnlockGroupBtn;
        if (r0 != null) goto L9;
    L12:
        boostChannel(this.premiumButtonView);
        return;
    L9:
        if (r0.isAttachedToWindow() == false) goto L12;
        boostChannel(this.boostToUnlockGroupBtn);
    }

    private void boostChannel(Loadable r2) {
        boostChannel(r2, false);
    }

    private void boostChannel(Loadable r7, boolean r8) {
        if (r7.isLoading() == false) goto L6;
        if (r8 == true) goto L6;
        return;
    L6:
        r7.setLoading(true);
        MessagesController.getInstance(this.currentAccount).getBoostsController().applyBoost(this.dialogId, this.canApplyBoost.slot, new LimitReachedBottomSheet$$ExternalSyntheticLambda22(this, r7), new LimitReachedBottomSheet$$ExternalSyntheticLambda23(this, r7));
    }

    private ArrayList<BoostFeature> boostFeaturesForLevel(int r8) {
        boolean r0 = isGroup();
        ArrayList<BoostFeature> r1 = new ArrayList();
        MessagesController r7 = MessagesController.getInstance(this.currentAccount);
        if (r7 != null) goto L5;
        return r1;
    L5:
        if (r0 == false) goto L7;
    L9:
        r1.add(BoostFeature.of(R.drawable.menu_feature_stories, "BoostFeatureStoriesPerDay", r8).asIncremental());
        if (r0 == true) goto L12;
        r1.add(BoostFeature.of(R.drawable.menu_feature_reactions, "BoostFeatureCustomReaction", r8).asIncremental());
    L12:
        MessagesController.PeerColors r2 = r7.peerColors;
        int r3 = 0;
        if (r2 == null) goto L15;
        int r22 = r2.colorsAvailable(r8, false);
    L16:
        MessagesController.PeerColors r4 = r7.profilePeerColors;
        if (r4 == null) goto L19;
        r3 = r4.colorsAvailable(r8, r0);
    L19:
        if (r0 == true) goto L22;
        if (r22 <= 0) goto L22;
        r1.add(BoostFeature.of(R.drawable.menu_feature_color_name, "BoostFeatureNameColor", 7));
    L22:
        if (r0 == true) goto L25;
        if (r22 <= 0) goto L25;
        r1.add(BoostFeature.of(R.drawable.menu_feature_links, "BoostFeatureReplyColor", r22));
    L25:
        if (r0 == false) goto L27;
    L29:
        if (r3 <= 0) goto L35;
        int r23 = R.drawable.menu_feature_color_profile;
        if (r0 == false) goto L33;
        String r42 = "BoostFeatureProfileColorGroup";
    L34:
        r1.add(BoostFeature.of(r23, r42, r3));
        goto L35
    L33:
        r42 = "BoostFeatureProfileColor";
    L35:
        if (r0 == true) goto L37;
    L39:
        if (r0 == false) goto L41;
    L42:
        if (r0 == true) goto L44;
    L50:
        if (r0 == true) goto L52;
    L54:
        if (r0 == false) goto L56;
    L57:
        if (r0 == true) goto L59;
    L61:
        if (r0 == false) goto L63;
    L64:
        if (r0 == true) goto L66;
    L72:
        if (r0 == false) goto L74;
    L75:
        if (r0 == true) goto L77;
    L83:
        if (r0 == false) goto L85;
    L87:
        Collections.reverse(r1);
        return r1;
    L85:
        if (r8 < r7.channelRestrictSponsoredLevelMin) goto L87;
        r1.add(BoostFeature.of(R.drawable.menu_feature_noads, R.string.BoostFeatureSwitchOffAds));
        goto L87
    L77:
        if (r8 < r7.groupCustomWallpaperLevelMin) goto L83;
    L78:
        int r24 = R.drawable.menu_feature_custombg;
        if (r0 == false) goto L81;
        int r32 = R.string.BoostFeatureCustomBackgroundGroup;
    L82:
        r1.add(BoostFeature.of(r24, r32));
        goto L83
    L81:
        r32 = R.string.BoostFeatureCustomBackground;
        goto L82
    L74:
        if (r8 >= r7.channelCustomWallpaperLevelMin) goto L78;
    L66:
        if (r8 < r7.groupWallpaperLevelMin) goto L72;
    L67:
        int r25 = R.drawable.menu_feature_wallpaper;
        if (r0 == false) goto L70;
        String r33 = "BoostFeatureBackgroundGroup";
    L71:
        r1.add(BoostFeature.of(r25, r33, 8));
        goto L72
    L70:
        r33 = "BoostFeatureBackground";
        goto L71
    L63:
        if (r8 >= r7.channelWallpaperLevelMin) goto L67;
    L59:
        if (r8 < r7.groupEmojiStatusLevelMin) goto L61;
    L60:
        r1.add(BoostFeature.of(R.drawable.menu_feature_status, R.string.BoostFeatureEmojiStatuses, "1000+"));
        goto L61
    L56:
        if (r8 >= r7.channelEmojiStatusLevelMin) goto L60;
    L52:
        if (r8 < r7.groupTranscribeLevelMin) goto L54;
        r1.add(BoostFeature.of(R.drawable.menu_feature_voice, R.string.BoostFeatureVoiceToTextConversion));
        goto L54
    L44:
        if (r8 < r7.groupProfileBgIconLevelMin) goto L50;
    L45:
        int r26 = R.drawable.menu_feature_cover;
        if (r0 == false) goto L48;
        int r34 = R.string.BoostFeatureProfileIconGroup;
    L49:
        r1.add(BoostFeature.of(r26, r34));
        goto L50
    L48:
        r34 = R.string.BoostFeatureProfileIcon;
        goto L49
    L41:
        if (r8 >= r7.channelProfileIconLevelMin) goto L45;
    L37:
        if (r8 < r7.groupEmojiStickersLevelMin) goto L39;
        r1.add(BoostFeature.of(R.drawable.menu_feature_pack, R.string.BoostFeatureCustomEmojiPack));
        goto L39
    L27:
        if (r8 < r7.channelBgIconLevelMin) goto L29;
        r1.add(BoostFeature.of(R.drawable.menu_feature_links2, R.string.BoostFeatureReplyIcon));
        goto L29
    L15:
        r22 = 0;
        goto L16
    L7:
        if (r8 < r7.channelAutotranslationLevelMin) goto L9;
        r1.add(BoostFeature.of(R.drawable.menu_feature_translate, R.string.BoostFeatureAutotranslation));
        goto L9
    }

    private String getBoostDescriptionStringAfterBoost() {
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        boolean r1 = isGroup();
        int r2 = this.type;
        if (r2 != 20) goto L9;
        if (r1 == false) goto L6;
        int r02 = R.string.GroupNeedBoostsForColorDescription;
    L7:
        String r03 = LocaleController.formatString(r02, new Object[]{Integer.valueOf(channelColorLevelMin())});
    L75:
        if (r03 == null) goto L78;
        return r03;
    L78:
        return getBoostsDescriptionString(false);
    L6:
        r02 = R.string.ChannelNeedBoostsForColorDescription;
        goto L7
    L9:
        if (r2 != 24) goto L15;
        if (r1 == false) goto L12;
        int r04 = R.string.GroupNeedBoostsForProfileColorDescription;
    L13:
        r03 = LocaleController.formatString(r04, new Object[]{Integer.valueOf(channelColorLevelMin())});
        goto L75
    L12:
        r04 = R.string.ChannelNeedBoostsForProfileColorDescription;
        goto L13
    L15:
        if (r2 != 29) goto L18;
        r03 = LocaleController.formatString(R.string.GroupNeedBoostsForCustomEmojiPackDescription, new Object[]{Integer.valueOf(r0.groupEmojiStickersLevelMin)});
        goto L75
    L18:
        if (r2 != 30) goto L21;
        r03 = LocaleController.formatString(R.string.ChannelNeedBoostsForSwitchOffAdsDescription, new Object[]{Integer.valueOf(r0.channelRestrictSponsoredLevelMin)});
        goto L75
    L21:
        if (r2 != 35) goto L24;
        r03 = LocaleController.formatString(R.string.ChannelNeedBoostsForAutotranslationDescription, new Object[]{Integer.valueOf(r0.channelAutotranslationLevelMin)});
        goto L75
    L24:
        if (r2 != 25) goto L33;
        if (r1 == false) goto L27;
        int r22 = R.string.GroupNeedBoostsForEmojiStatusDescription;
    L28:
        if (r1 == false) goto L30;
        int r05 = r0.groupEmojiStatusLevelMin;
    L31:
        r03 = LocaleController.formatString(r22, new Object[]{Integer.valueOf(r05)});
        goto L75
    L30:
        r05 = r0.channelEmojiStatusLevelMin;
        goto L31
    L27:
        r22 = R.string.ChannelNeedBoostsForEmojiStatusDescription;
        goto L28
    L33:
        if (r2 != 26) goto L42;
        if (r1 == false) goto L36;
        int r23 = R.string.GroupNeedBoostsForWearCollectiblesDescription;
    L37:
        if (r1 == false) goto L39;
        int r06 = r0.groupEmojiStatusLevelMin;
    L40:
        r03 = LocaleController.formatString(r23, new Object[]{Integer.valueOf(r06)});
        goto L75
    L39:
        r06 = r0.channelEmojiStatusLevelMin;
        goto L40
    L36:
        r23 = R.string.ChannelNeedBoostsForWearCollectiblesDescription;
        goto L37
    L42:
        if (r2 != 27) goto L48;
        if (r1 == false) goto L45;
        int r12 = R.string.GroupNeedBoostsForReplyIconDescription;
    L46:
        r03 = LocaleController.formatString(r12, new Object[]{Integer.valueOf(r0.channelBgIconLevelMin)});
        goto L75
    L45:
        r12 = R.string.ChannelNeedBoostsForReplyIconDescription;
        goto L46
    L48:
        if (r2 != 28) goto L57;
        if (r1 == false) goto L51;
        int r24 = R.string.GroupNeedBoostsForProfileIconDescription;
    L52:
        if (r1 == false) goto L54;
        int r07 = r0.groupProfileBgIconLevelMin;
    L55:
        r03 = LocaleController.formatString(r24, new Object[]{Integer.valueOf(r07)});
        goto L75
    L54:
        r07 = r0.channelProfileIconLevelMin;
        goto L55
    L51:
        r24 = R.string.ChannelNeedBoostsForProfileIconDescription;
        goto L52
    L57:
        if (r2 != 22) goto L66;
        if (r1 == false) goto L60;
        int r25 = R.string.GroupNeedBoostsForWallpaperDescription;
    L61:
        if (r1 == false) goto L63;
        int r08 = r0.groupWallpaperLevelMin;
    L64:
        r03 = LocaleController.formatString(r25, new Object[]{Integer.valueOf(r08)});
        goto L75
    L63:
        r08 = r0.channelWallpaperLevelMin;
        goto L64
    L60:
        r25 = R.string.ChannelNeedBoostsForWallpaperDescription;
        goto L61
    L66:
        if (r2 != 23) goto L74;
        if (r1 == false) goto L69;
        int r26 = R.string.GroupNeedBoostsForCustomWallpaperDescription;
    L70:
        if (r1 == false) goto L72;
        int r09 = r0.groupCustomWallpaperLevelMin;
    L73:
        r03 = LocaleController.formatString(r26, new Object[]{Integer.valueOf(r09)});
        goto L75
    L72:
        r09 = r0.channelCustomWallpaperLevelMin;
        goto L73
    L69:
        r26 = R.string.ChannelNeedBoostsForCustomWallpaperDescription;
        goto L70
    L74:
        r03 = null;
        goto L75
    }

    private String getBoostLink() {
        TL_stories.TL_premium_boostsStatus r0 = this.boostsStatus;
        if (r0 == null) goto L9;
        if (TextUtils.isEmpty(r0.boost_url) == true) goto L9;
        return this.boostsStatus.boost_url;
    L9:
        return ChannelBoostUtilities.createLink(this.currentAccount, this.dialogId);
    }

    private String getBoostsDescriptionString(boolean r7) {
        if (this.type == 32) goto L5;
        TLRPC.Chat r0 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        if (r0 == null) goto L9;
        String r02 = r0.title;
    L14:
        TL_stories.TL_premium_boostsStatus r1 = this.boostsStatus;
        if (r1.boosts != r1.current_level_boosts) goto L17;
        boolean r12 = true;
    L19:
        if (isMiniBoostBtnForAdminAvailable() == false) goto L30;
        if (this.boostsStatus.next_level_boosts == 0) goto L30;
        if (r7 == false) goto L30;
        if (isGroup() == false) goto L26;
        int r72 = R.string.GroupNeedBoostsDescriptionForNewFeatures;
    L27:
        TL_stories.TL_premium_boostsStatus r6 = this.boostsStatus;
        int r13 = r6.next_level_boosts;
        int r62 = r6.boosts;
        return LocaleController.formatString(r72, new Object[]{r02, LocaleController.formatPluralString("MoreBoosts", r13 - r62, new Object[]{Integer.valueOf(r13 - r62)})});
    L26:
        r72 = R.string.ChannelNeedBoostsDescriptionForNewFeatures;
    L30:
        if (r12 == true) goto L32;
    L47:
        boolean r14 = this.canApplyBoost.alreadyActive;
        TL_stories.TL_premium_boostsStatus r2 = this.boostsStatus;
        if (r14 == false) goto L72;
        if (r2.level != 0) goto L58;
        if (isGroup() == false) goto L54;
        int r73 = R.string.GroupNeedBoostsDescriptionForNewFeatures;
    L55:
        TL_stories.TL_premium_boostsStatus r63 = this.boostsStatus;
        int r15 = r63.next_level_boosts;
        int r64 = r63.boosts;
        return LocaleController.formatString(r73, new Object[]{r02, LocaleController.formatPluralString("MoreBoosts", r15 - r64, new Object[]{Integer.valueOf(r15 - r64)})});
    L54:
        r73 = R.string.ChannelNeedBoostsDescriptionForNewFeatures;
        goto L55
    L58:
        if (r2.next_level_boosts != 0) goto L66;
        if (isGroup() == false) goto L62;
        int r03 = R.string.GroupBoostsJustReachedLevelNext;
    L64:
        return LocaleController.formatString(r03, new Object[]{Integer.valueOf(this.boostsStatus.level), LocaleController.formatPluralString("BoostStories", this.boostsStatus.level + 1, new Object[0])});
    L62:
        r03 = R.string.ChannelBoostsJustReachedLevelNext;
        goto L64
    L66:
        if (isGroup() == false) goto L68;
        int r74 = R.string.GroupNeedBoostsDescriptionForNewFeatures;
    L69:
        TL_stories.TL_premium_boostsStatus r65 = this.boostsStatus;
        int r16 = r65.next_level_boosts;
        int r66 = r65.boosts;
        return LocaleController.formatString(r74, new Object[]{r02, LocaleController.formatPluralString("MoreBoosts", r16 - r66, new Object[]{Integer.valueOf(r16 - r66)})});
    L68:
        r74 = R.string.ChannelNeedBoostsDescriptionForNewFeatures;
        goto L69
    L72:
        if (r2.level != 0) goto L80;
        if (isGroup() == false) goto L76;
        int r75 = R.string.GroupNeedBoostsDescriptionForNewFeatures;
    L77:
        TL_stories.TL_premium_boostsStatus r67 = this.boostsStatus;
        int r17 = r67.next_level_boosts;
        int r68 = r67.boosts;
        return LocaleController.formatString(r75, new Object[]{r02, LocaleController.formatPluralString("MoreBoosts", r17 - r68, new Object[]{Integer.valueOf(r17 - r68)})});
    L76:
        r75 = R.string.ChannelNeedBoostsDescriptionForNewFeatures;
        goto L77
    L80:
        if (r2.next_level_boosts != 0) goto L88;
        if (isGroup() == false) goto L84;
        int r04 = R.string.GroupBoostsJustReachedLevelNext;
    L86:
        return LocaleController.formatString(r04, new Object[]{Integer.valueOf(this.boostsStatus.level), LocaleController.formatPluralString("BoostStories", this.boostsStatus.level + 1, new Object[0])});
    L84:
        r04 = R.string.ChannelBoostsJustReachedLevelNext;
        goto L86
    L88:
        if (isGroup() == false) goto L90;
        int r76 = R.string.GroupNeedBoostsDescriptionForNewFeatures;
    L91:
        TL_stories.TL_premium_boostsStatus r69 = this.boostsStatus;
        int r18 = r69.next_level_boosts;
        int r610 = r69.boosts;
        return LocaleController.formatString(r76, new Object[]{r02, LocaleController.formatPluralString("MoreBoosts", r18 - r610, new Object[]{Integer.valueOf(r18 - r610)})});
    L90:
        r76 = R.string.ChannelNeedBoostsDescriptionForNewFeatures;
        goto L91
    L32:
        if (this.canApplyBoost.alreadyActive == false) goto L47;
        if (this.boostsStatus.level != 1) goto L42;
        if (isGroup() == false) goto L38;
        int r611 = R.string.GroupBoostsJustReachedLevel1;
    L40:
        return LocaleController.formatString(r611, new Object[0]);
    L38:
        r611 = R.string.ChannelBoostsJustReachedLevel1;
        goto L40
    L42:
        if (isGroup() == false) goto L44;
        int r05 = R.string.GroupBoostsJustReachedLevelNext;
    L46:
        return LocaleController.formatString(r05, new Object[]{Integer.valueOf(this.boostsStatus.level), LocaleController.formatPluralString("BoostStories", this.boostsStatus.level, new Object[0])});
    L44:
        r05 = R.string.ChannelBoostsJustReachedLevelNext;
        goto L46
    L17:
        r12 = false;
        goto L19
    L9:
        if (isGroup() == false) goto L11;
        int r06 = R.string.AccDescrGroup;
    L12:
        r02 = LocaleController.getString(r06);
        goto L14
    L11:
        r06 = R.string.AccDescrChannel;
        goto L12
    L5:
        return getDescriptionForRemoveRestrictions();
    }

    private String getBoostsTitleString() {
        TL_stories.TL_premium_boostsStatus r0 = this.boostsStatus;
        if (r0.next_level_boosts != 0) goto L7;
        return LocaleController.formatString("BoostsMaxLevelReached", R.string.BoostsMaxLevelReached, new Object[0]);
    L7:
        if (r0.level > 0) goto L9;
    L16:
        boolean r02 = this.isCurrentChat;
        int r1 = this.type;
        if (r02 == false) goto L35;
        if (r1 == 32) goto L20;
        TLRPC.Chat r03 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        if (this.canApplyBoost.alreadyActive == false) goto L30;
        return LocaleController.formatString("YouBoostedChannel2", R.string.YouBoostedChannel2, new Object[]{r03.title});
    L30:
        if (isGroup() == false) goto L32;
        int r3 = R.string.BoostGroup;
    L34:
        return LocaleController.getString(r3);
    L32:
        r3 = R.string.BoostChannel;
        goto L34
    L20:
        if (isGroup() == false) goto L22;
        int r32 = R.string.BoostGroup;
    L24:
        return LocaleController.getString(r32);
    L22:
        r32 = R.string.BoostChannel;
        goto L24
    L35:
        if (r1 != 32) goto L43;
        if (isGroup() == false) goto L39;
        int r33 = R.string.BoostGroup;
    L41:
        return LocaleController.getString(r33);
    L39:
        r33 = R.string.BoostChannel;
        goto L41
    L43:
        if (this.canApplyBoost.alreadyActive == false) goto L51;
        if (isGroup() == false) goto L47;
        int r34 = R.string.YouBoostedGroup;
    L49:
        return LocaleController.getString(r34);
    L47:
        r34 = R.string.YouBoostedChannel;
        goto L49
    L51:
        if (isGroup() == false) goto L53;
        int r35 = R.string.BoostingEnableStoriesForGroup;
    L55:
        return LocaleController.getString(r35);
    L53:
        r35 = R.string.BoostingEnableStoriesForChannel;
        goto L55
    L9:
        if (this.canApplyBoost.alreadyActive == true) goto L16;
        if (isGroup() == false) goto L13;
        int r36 = R.string.BoostGroup;
    L15:
        return LocaleController.getString(r36);
    L13:
        r36 = R.string.BoostChannel;
        goto L15
    }

    private TLRPC.Chat getChat() {
        return MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
    }

    private TLRPC.ChatFull getChatFull() {
        return MessagesController.getInstance(this.currentAccount).getChatFull(-this.dialogId);
    }

    private String getDescriptionForRemoveRestrictions() {
        TLRPC.Chat r0 = getChat();
        int r2 = getNeededBoostsForUnlockGroup();
        if (r0 != null) goto L5;
        String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return LocaleController.formatPluralString("BoostingRemoveRestrictionsSubtitle", r2, new Object[]{r02});
    L5:
        r02 = r0.title;
        goto L7
    }

    private static LimitParams getLimitParams(int r4, int r5) {
        LimitParams r0 = new LimitParams();
        if (r4 != 0) goto L7;
        r0.defaultLimit = MessagesController.getInstance(r5).dialogFiltersPinnedLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).dialogFiltersPinnedLimitPremium;
        r0.icon = R.drawable.msg_limit_pin;
        r0.descriptionStr = LocaleController.formatString("LimitReachedPinDialogs", R.string.LimitReachedPinDialogs, new Object[]{Integer.valueOf(r0.defaultLimit), Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedPinDialogsPremium", R.string.LimitReachedPinDialogsPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedPinDialogsLocked", R.string.LimitReachedPinDialogsLocked, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L7:
        if (r4 != 33) goto L11;
        r0.defaultLimit = MessagesController.getInstance(r5).savedDialogsPinnedLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).savedDialogsPinnedLimitPremium;
        r0.icon = R.drawable.msg_limit_pin;
        r0.descriptionStr = LocaleController.formatString(R.string.LimitReachedPinSavedDialogs, new Object[]{Integer.valueOf(r0.defaultLimit), Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString(R.string.LimitReachedPinSavedDialogsPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString(R.string.LimitReachedPinSavedDialogsLocked, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L11:
        if (r4 != 2) goto L15;
        r0.defaultLimit = MessagesController.getInstance(r5).publicLinksLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).publicLinksLimitPremium;
        r0.icon = R.drawable.msg_limit_links;
        r0.descriptionStr = LocaleController.formatString("LimitReachedPublicLinks", R.string.LimitReachedPublicLinks, new Object[]{Integer.valueOf(r0.defaultLimit), Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedPublicLinksPremium", R.string.LimitReachedPublicLinksPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedPublicLinksLocked", R.string.LimitReachedPublicLinksLocked, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L15:
        if (r4 != 12) goto L19;
        r0.defaultLimit = MessagesController.getInstance(r5).chatlistInvitesLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).chatlistInvitesLimitPremium;
        r0.icon = R.drawable.msg_limit_links;
        r0.descriptionStr = LocaleController.formatString("LimitReachedFolderLinks", R.string.LimitReachedFolderLinks, new Object[]{Integer.valueOf(r0.defaultLimit), Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedFolderLinksPremium", R.string.LimitReachedFolderLinksPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedFolderLinksLocked", R.string.LimitReachedFolderLinksLocked, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L19:
        if (r4 != 13) goto L23;
        r0.defaultLimit = MessagesController.getInstance(r5).chatlistJoinedLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).chatlistJoinedLimitPremium;
        r0.icon = R.drawable.msg_limit_folder;
        r0.descriptionStr = LocaleController.formatString("LimitReachedSharedFolders", R.string.LimitReachedSharedFolders, new Object[]{Integer.valueOf(r0.defaultLimit), Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedSharedFoldersPremium", R.string.LimitReachedSharedFoldersPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedSharedFoldersLocked", R.string.LimitReachedSharedFoldersLocked, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L23:
        if (r4 != 3) goto L27;
        r0.defaultLimit = MessagesController.getInstance(r5).dialogFiltersLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).dialogFiltersLimitPremium;
        r0.icon = R.drawable.msg_limit_folder;
        r0.descriptionStr = LocaleController.formatString("LimitReachedFolders", R.string.LimitReachedFolders, new Object[]{Integer.valueOf(r0.defaultLimit), Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedFoldersPremium", R.string.LimitReachedFoldersPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedFoldersLocked", R.string.LimitReachedFoldersLocked, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L27:
        if (r4 != 4) goto L31;
        r0.defaultLimit = MessagesController.getInstance(r5).dialogFiltersChatsLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).dialogFiltersChatsLimitPremium;
        r0.icon = R.drawable.msg_limit_chats;
        r0.descriptionStr = LocaleController.formatString("LimitReachedChatInFolders", R.string.LimitReachedChatInFolders, new Object[]{Integer.valueOf(r0.defaultLimit), Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedChatInFoldersPremium", R.string.LimitReachedChatInFoldersPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedChatInFoldersLocked", R.string.LimitReachedChatInFoldersLocked, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L31:
        if (r4 != 5) goto L35;
        r0.defaultLimit = MessagesController.getInstance(r5).channelsLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).channelsLimitPremium;
        r0.icon = R.drawable.msg_limit_groups;
        r0.descriptionStr = LocaleController.formatString("LimitReachedCommunities", R.string.LimitReachedCommunities, new Object[]{Integer.valueOf(r0.defaultLimit), Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedCommunitiesPremium", R.string.LimitReachedCommunitiesPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedCommunitiesLocked", R.string.LimitReachedCommunitiesLocked, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L35:
        if (r4 != 6) goto L39;
        r0.defaultLimit = 100;
        r0.premiumLimit = 200;
        r0.icon = R.drawable.msg_limit_folder;
        r0.descriptionStr = LocaleController.formatString("LimitReachedFileSize", R.string.LimitReachedFileSize, new Object[]{"2 GB", "4 GB"});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedFileSizePremium", R.string.LimitReachedFileSizePremium, new Object[]{"4 GB"});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedFileSizeLocked", R.string.LimitReachedFileSizeLocked, new Object[]{"2 GB"});
        return r0;
    L39:
        if (r4 != 7) goto L43;
        r0.defaultLimit = 8;
        r0.premiumLimit = 16;
        r0.icon = R.drawable.msg_limit_accounts;
        r0.descriptionStr = LocaleController.formatString("LimitReachedAccounts", R.string.LimitReachedAccounts, new Object[]{8, Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedAccountsPremium", R.string.LimitReachedAccountsPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedAccountsPremium", R.string.LimitReachedAccountsPremium, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L43:
        if (r4 != 11) goto L47;
        r0.defaultLimit = 0;
        r0.premiumLimit = 0;
        r0.icon = R.drawable.msg_limit_links;
        r0.descriptionStr = LocaleController.formatString("LimitReachedAccounts", R.string.LimitReachedAccounts, new Object[]{0, Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.descriptionStrLocked = _UrlKt.FRAGMENT_ENCODE_SET;
        return r0;
    L47:
        if (r4 != 14) goto L51;
        r0.defaultLimit = MessagesController.getInstance(r5).storyExpiringLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).storyExpiringLimitPremium;
        r0.icon = R.drawable.msg_limit_stories;
        r0.descriptionStr = LocaleController.formatPluralStringComma("LimitReachedStoriesCount2First", r0.defaultLimit) + "\n" + LocaleController.formatPluralStringComma("LimitReachedStoriesCount2Second", r0.premiumLimit);
        r0.descriptionStrPremium = LocaleController.formatPluralStringComma("LimitReachedStoriesCount2Premium", r0.premiumLimit);
        r0.descriptionStrLocked = LocaleController.formatPluralStringComma("LimitReachedStoriesCount2Premium", r0.defaultLimit);
        return r0;
    L51:
        if (r4 != 15) goto L55;
        r0.defaultLimit = MessagesController.getInstance(r5).storiesSentWeeklyLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).storiesSentWeeklyLimitPremium;
        r0.icon = R.drawable.msg_limit_stories;
        r0.descriptionStr = LocaleController.formatString("LimitReachedStoriesWeekly", R.string.LimitReachedStoriesWeekly, new Object[]{Integer.valueOf(r0.defaultLimit), Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedStoriesWeeklyPremium", R.string.LimitReachedStoriesWeeklyPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedStoriesWeeklyPremium", R.string.LimitReachedStoriesWeeklyPremium, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L55:
        if (r4 != 16) goto L59;
        r0.defaultLimit = MessagesController.getInstance(r5).storiesSentMonthlyLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).storiesSentMonthlyLimitPremium;
        r0.icon = R.drawable.msg_limit_stories;
        r0.descriptionStr = LocaleController.formatString("LimitReachedStoriesMonthly", R.string.LimitReachedStoriesMonthly, new Object[]{Integer.valueOf(r0.defaultLimit), Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedStoriesMonthlyPremium", R.string.LimitReachedStoriesMonthlyPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedStoriesMonthlyPremium", R.string.LimitReachedStoriesMonthlyPremium, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L59:
        if (r4 != 18) goto L61;
    L90:
        r0.defaultLimit = MessagesController.getInstance(r5).storiesSentMonthlyLimitDefault;
        r0.premiumLimit = MessagesController.getInstance(r5).storiesSentMonthlyLimitPremium;
        r0.icon = R.drawable.filled_limit_boost;
        r0.descriptionStr = LocaleController.formatString("LimitReachedStoriesMonthly", R.string.LimitReachedStoriesMonthly, new Object[]{Integer.valueOf(r0.defaultLimit), Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrPremium = LocaleController.formatString("LimitReachedStoriesMonthlyPremium", R.string.LimitReachedStoriesMonthlyPremium, new Object[]{Integer.valueOf(r0.premiumLimit)});
        r0.descriptionStrLocked = LocaleController.formatString("LimitReachedStoriesMonthlyPremium", R.string.LimitReachedStoriesMonthlyPremium, new Object[]{Integer.valueOf(r0.defaultLimit)});
        return r0;
    L61:
        if (r4 == 32) goto L90;
        if (r4 == 20) goto L90;
        if (r4 == 24) goto L90;
        if (r4 == 27) goto L90;
        if (r4 == 28) goto L90;
        if (r4 == 25) goto L90;
        if (r4 == 30) goto L90;
        if (r4 == 35) goto L90;
        if (r4 == 29) goto L90;
        if (r4 == 22) goto L90;
        if (r4 == 23) goto L90;
        if (r4 == 19) goto L90;
        if (r4 == 21) goto L90;
        if (r4 == 26) goto L90;
        return r0;
    }

    private int getNeededBoostsForUnlockGroup() {
        TLRPC.ChatFull r1 = getChatFull();
        return Math.max(r1.boosts_unrestrict - r1.boosts_applied, 0);
    }

    private static boolean hasFixedSize(int r1) {
        if (r1 != 0) goto L4;
        return true;
    L4:
        if (r1 != 33) goto L6;
        return true;
    L6:
        if (r1 != 3) goto L8;
        return true;
    L8:
        if (r1 != 4) goto L10;
        return true;
    L10:
        if (r1 != 6) goto L12;
        return true;
    L12:
        if (r1 != 7) goto L14;
        return true;
    L14:
        if (r1 != 12) goto L16;
        return true;
    L16:
        if (r1 != 13) goto L18;
        return true;
    L18:
        if (r1 != 14) goto L20;
        return true;
    L20:
        if (r1 != 15) goto L22;
        return true;
    L22:
        if (r1 == 16) goto L37;
        return false;
    L37:
        return true;
    }

    private boolean isBoostingForAdminPossible() {
        int r1 = this.type;
        if (r1 != 19) goto L5;
        return true;
    L5:
        if (r1 != 18) goto L7;
        return true;
    L7:
        if (r1 != 20) goto L9;
        return true;
    L9:
        if (r1 != 24) goto L11;
        return true;
    L11:
        if (r1 != 25) goto L13;
        return true;
    L13:
        if (r1 != 26) goto L15;
        return true;
    L15:
        if (r1 != 29) goto L17;
        return true;
    L17:
        if (r1 != 22) goto L19;
        return true;
    L19:
        if (r1 != 27) goto L21;
        return true;
    L21:
        if (r1 != 28) goto L23;
        return true;
    L23:
        if (r1 != 23) goto L25;
        return true;
    L25:
        if (r1 != 30) goto L27;
        return true;
    L27:
        if (r1 == 35) goto L44;
        return false;
    L44:
        return true;
    }

    private boolean isGroup() {
        return !ChatObject.isChannelAndNotMegaGroup(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId)));
    }

    private boolean isMiniBoostBtnForAdminAvailable() {
        if (isBoostingForAdminPossible() == true) goto L5;
        return false;
    L5:
        if (ChatObject.hasAdminRights(getChat()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$boostChannel$16(Loadable r4, TL_stories.TL_premium_myBoosts r5, TL_stories.TL_premium_boostsStatus r6) {
        boolean r0 = false;
        r4.setLoading(false);
        if (r6 != null) goto L5;
        return;
    L5:
        this.boostsStatus.boosts++;
        if (this.type != 32) goto L10;
        TLRPC.ChatFull r42 = getChatFull();
        if (r42 == null) goto L10;
        r42.boosts_applied++;
    L10:
        limitPreviewIncreaseCurrentValue();
        setBoostsStats(r6, this.isCurrentChat);
        ChannelBoostsController.CanApplyBoost r43 = this.canApplyBoost;
        if (this.boostsStatus.next_level_boosts > 0) goto L13;
        r0 = true;
    L13:
        r43.isMaxLvl = r0;
        r43.boostedNow = true;
        r43.setMyBoosts(r5);
        onBoostSuccess();
    }

    private /* synthetic */ void lambda$boostChannel$17(Loadable r5, TL_stories.TL_premium_myBoosts r6) {
        MessagesController.getInstance(this.currentAccount).getBoostsController().getBoostsStats(this.dialogId, new LimitReachedBottomSheet$$ExternalSyntheticLambda28(this, r5, r6));
    }

    private /* synthetic */ void lambda$boostChannel$18(Loadable r2) {
        boostChannel(r2, true);
    }

    private /* synthetic */ void lambda$boostChannel$19(Loadable r4, TLRPC.TL_error r5) {
        if (r5.text.startsWith("FLOOD_WAIT") == false) goto L9;
        int r52 = Utilities.parseInt(r5.text);
        if (r52 > 5) goto L8;
        AndroidUtilities.runOnUIThread(new LimitReachedBottomSheet$$ExternalSyntheticLambda27(this, r4), r52 * 1000);
        return;
    L8:
        BoostDialogs.showFloodWait(r52);
    L9:
        r4.setLoading(false);
    }

    private /* synthetic */ void lambda$leaveFromSelectedGroups$21(ArrayList r5, TLRPC.User r6, AlertDialog r7, int r8) {
        dismiss();
        int r82 = 0;
    L4:
        if (r82 >= r5.size()) goto L6;
        TLRPC.Chat r0 = (TLRPC.Chat) r5.get(r82);
        MessagesController.getInstance(this.currentAccount).putChat(r0, false);
        MessagesController.getInstance(this.currentAccount).deleteParticipantFromChat(r0.id, r6);
        r82 = r82 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$loadAdminedChannels$22(TLObject r4) {
        this.loadingAdminedChannels = false;
        if (r4 == null) goto L16;
        this.chats.clear();
        this.chats.addAll(((TLRPC.TL_messages_chats) r4).chats);
        this.loading = false;
        this.enterAnimator.showItemsAnimated(this.chatsTitleRow + 4);
        int r42 = 0;
    L6:
        if (r42 >= this.recyclerListView.getChildCount()) goto L11;
        if ((this.recyclerListView.getChildAt(r42) instanceof HeaderView) == true) goto L9;
        r42 = r42 + 1;
        goto L6
    L9:
        int r43 = this.recyclerListView.getChildAt(r42).getTop();
    L12:
        updateRows();
        if (this.headerRow < 0) goto L16;
        if (r43 == 0) goto L16;
        ((LinearLayoutManager) this.recyclerListView.getLayoutManager()).scrollToPositionWithOffset(this.headerRow + 1, r43);
        goto L16
    L11:
        r43 = 0;
    L16:
        int r44 = Math.max(this.chats.size(), this.limitParams.defaultLimit);
        this.limitPreviewView.setIconValue(r44, false);
        this.limitPreviewView.setBagePosition(r44 / this.limitParams.premiumLimit);
        this.limitPreviewView.startDelayedAnimation();
    }

    private /* synthetic */ void lambda$loadAdminedChannels$23(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new LimitReachedBottomSheet$$ExternalSyntheticLambda14(this, r1));
    }

    private /* synthetic */ void lambda$loadInactiveChannels$26(ArrayList r4, int r5, TLRPC.TL_messages_inactiveChats r6) {
        this.inactiveChatsSignatures.clear();
        this.inactiveChats.clear();
        this.inactiveChatsSignatures.addAll(r4);
        int r0 = 0;
    L3:
        if (r0 >= r5) goto L5;
        this.inactiveChats.add(r6.chats.get(r0));
        r0 = r0 + 1;
        goto L3
    L5:
        this.loading = false;
        this.enterAnimator.showItemsAnimated(this.chatsTitleRow + 4);
        int r52 = 0;
    L7:
        if (r52 >= this.recyclerListView.getChildCount()) goto L12;
        if ((this.recyclerListView.getChildAt(r52) instanceof HeaderView) == true) goto L10;
        r52 = r52 + 1;
        goto L7
    L10:
        int r53 = this.recyclerListView.getChildAt(r52).getTop();
    L13:
        updateRows();
        if (this.headerRow < 0) goto L18;
        if (r53 == 0) goto L18;
        ((LinearLayoutManager) this.recyclerListView.getLayoutManager()).scrollToPositionWithOffset(this.headerRow + 1, r53);
    L18:
        if (this.limitParams != null) goto L20;
        this.limitParams = getLimitParams(this.type, this.currentAccount);
    L20:
        int r54 = Math.max(this.inactiveChats.size(), this.limitParams.defaultLimit);
        LimitPreviewView r62 = this.limitPreviewView;
        if (r62 == null) goto L27;
        r62.setIconValue(r54, false);
        this.limitPreviewView.setBagePosition(r54 / this.limitParams.premiumLimit);
        this.limitPreviewView.startDelayedAnimation();
        return;
    L27:
        return;
    L12:
        r53 = 0;
        goto L13
    }

    private /* synthetic */ void lambda$loadInactiveChannels$27(TLObject r9, TLRPC.TL_error r10) {
        if (r10 != null) goto L26;
        TLRPC.TL_messages_inactiveChats r92 = (TLRPC.TL_messages_inactiveChats) r9;
        ArrayList r102 = new ArrayList();
        int r0 = Math.min(r92.chats.size(), r92.dates.size());
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L20;
        TLRPC.Chat r3 = r92.chats.get(r2);
        int r4 = (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() - r92.dates.get(r2).intValue()) / 86400;
        if (r4 >= 30) goto L9;
        String r42 = LocaleController.formatPluralString("Days", r4, new Object[0]);
    L13:
        if (ChatObject.isMegagroup(r3) == false) goto L16;
        r102.add(LocaleController.formatString("InactiveChatSignature", R.string.InactiveChatSignature, new Object[]{LocaleController.formatPluralString("Members", r3.participants_count, new Object[0]), r42}));
    L19:
        r2 = r2 + 1;
        goto L4
    L16:
        if (ChatObject.isChannel(r3) == false) goto L18;
        r102.add(LocaleController.formatString("InactiveChannelSignature", R.string.InactiveChannelSignature, new Object[]{r42}));
        goto L19
    L18:
        r102.add(LocaleController.formatString("InactiveChatSignature", R.string.InactiveChatSignature, new Object[]{LocaleController.formatPluralString("Members", r3.participants_count, new Object[0]), r42}));
        goto L19
    L9:
        if (r4 >= 365) goto L11;
        r42 = LocaleController.formatPluralString("Months", r4 / 30, new Object[0]);
        goto L13
    L11:
        r42 = LocaleController.formatPluralString("Years", r4 / 365, new Object[0]);
        goto L13
    L20:
        AndroidUtilities.runOnUIThread(new LimitReachedBottomSheet$$ExternalSyntheticLambda20(this, r102, r0, r92));
        return;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        AndroidUtilities.addToClipboard(getBoostLink());
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        boolean r12 = this.premiumButtonView.isShowOverlay();
        PremiumButtonView r0 = this.premiumButtonView;
        if (r12 == false) goto L6;
        r0.overlayTextView.performClick();
        return;
    L6:
        r0.buttonLayout.performClick();
    }

    private /* synthetic */ void lambda$new$2() {
        this.boostToUnlockGroupBtn.setCount(getNeededBoostsForUnlockGroup(), false);
    }

    private /* synthetic */ void lambda$new$3() {
        if (ChatObject.hasAdminRights(getChat()) == true) goto L5;
        return;
    L5:
        if (this.premiumButtonView.getParent() == null) goto L7;
        ((ViewGroup) this.premiumButtonView.getParent()).removeView(this.premiumButtonView);
    L7:
        View r0 = this.divider;
        if (r0 != null) goto L10;
    L12:
        this.recyclerListView.setPadding(0, 0, 0, 0);
        return;
    L10:
        if (r0.getParent() == null) goto L12;
        ((ViewGroup) this.divider.getParent()).removeView(this.divider);
        goto L12
    }

    private /* synthetic */ void lambda$onViewCreated$10(AlertDialog r1, int r2) {
        r1.dismiss();
        boostChannel();
    }

    private /* synthetic */ void lambda$onViewCreated$12(Context r21, View r22) {
        int r2 = this.type;
        if (r2 != 11) goto L5;
        return;
    L5:
        if (r2 != 34) goto L8;
        return;
    L8:
        if (r2 != 19) goto L10;
    L62:
        ChannelBoostsController.CanApplyBoost r23 = this.canApplyBoost;
        if (r23.empty == true) goto L65;
        boolean r3 = r23.canApply;
        if (r3 == true) goto L79;
    L88:
        if (r3 == false) goto L91;
        FrameLayout r24 = new FrameLayout(getContext());
        BackupImageView r32 = new BackupImageView(getContext());
        r32.setRoundRadius(AndroidUtilities.dp(30.0f));
        r24.addView(r32, LayoutHelper.createFrame(60, 60.0f));
        r24.setClipChildren(false);
        Paint r8 = new Paint(1);
        r8.setColor(Theme.getColor(Theme.key_dialogBackground));
        Drawable r10 = ContextCompat.getDrawable(getContext(), R.drawable.filled_limit_boost);
        r24.addView(new AnonymousClass3(this, getContext(), r8, r10), LayoutHelper.createFrame(28, 28.0f, 0, 34.0f, 34.0f, 0.0f, 0.0f));
        ImageView r82 = new ImageView(getContext());
        r82.setImageResource(R.drawable.msg_arrow_avatar);
        r82.setColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon));
        r24.addView(r82, LayoutHelper.createFrame(24, 24, 17));
        BackupImageView r4 = new BackupImageView(getContext());
        r4.setRoundRadius(AndroidUtilities.dp(30.0f));
        r24.addView(r4, LayoutHelper.createFrame(60, 60.0f, 0, 96.0f, 0.0f, 0.0f, 0.0f));
        FrameLayout r7 = new FrameLayout(getContext());
        r7.addView(r24, LayoutHelper.createFrame(-2, 60, 1));
        r7.setClipChildren(false);
        TextView r25 = new TextView(r21);
        r25.setLetterSpacing(0.025f);
        r25.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r25.setTextSize(1, 16.0f);
        r7.addView(r25, LayoutHelper.createLinear(-1, -2, 0, 24, 80, 24, 0));
        AvatarDrawable r5 = new AvatarDrawable();
        TLRPC.Chat r6 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.canApplyBoost.replaceDialogId));
        r5.setInfo(this.currentAccount, r6);
        r32.setForUserOrChat(r6, r5);
        AvatarDrawable r33 = new AvatarDrawable();
        TLRPC.Chat r52 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        r33.setInfo(this.currentAccount, r52);
        r4.setForUserOrChat(r52, r33);
        AlertDialog.Builder r34 = new AlertDialog.Builder(r21);
        r34.setView(r7);
        r25.setText(AndroidUtilities.replaceTags(LocaleController.formatString("ReplaceBoostChannelDescription", R.string.ReplaceBoostChannelDescription, new Object[]{r6.title, r52.title})));
        r34.setPositiveButton(LocaleController.getString(R.string.Replace), new LimitReachedBottomSheet$$ExternalSyntheticLambda18(this));
        r34.setNegativeButton(LocaleController.getString(R.string.Cancel), new LimitReachedBottomSheet$$ExternalSyntheticLambda19());
        r34.show();
        return;
    L91:
        int r0 = r23.floodWait;
        if (r0 == 0) goto L96;
        BoostDialogs.showFloodWait(r0);
        return;
    L96:
        return;
    L79:
        if (r23.replaceDialogId != 0) goto L88;
        if (r23.needSelector == true) goto L83;
    L86:
        boostChannel();
        return;
    L83:
        if (BoostRepository.isMultiBoostsAvailable() == false) goto L86;
        this.lockInvalidation = true;
        this.limitPreviewView.invalidationEnabled = false;
        BaseFragment r1 = getBaseFragment();
        ChannelBoostsController.CanApplyBoost r26 = this.canApplyBoost;
        ReassignBoostBottomSheet.show(r1, r26.myBoosts, r26.currentChat).setOnHideListener(new LimitReachedBottomSheet$$ExternalSyntheticLambda17(this));
        return;
    L65:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L67;
    L70:
        AlertDialog.Builder r27 = new AlertDialog.Builder(r21, this.resourcesProvider);
        r27.setTitle(LocaleController.getString(R.string.PremiumNeeded));
        if (isGroup() == false) goto L73;
        int r12 = R.string.PremiumNeededForBoostingGroup;
    L74:
        r27.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(r12)));
        r27.setPositiveButton(LocaleController.getString(R.string.CheckPhoneNumberYes), new LimitReachedBottomSheet$$ExternalSyntheticLambda15(this));
        r27.setNegativeButton(LocaleController.getString(R.string.Cancel), new LimitReachedBottomSheet$$ExternalSyntheticLambda16());
        r27.show();
        return;
    L73:
        r12 = R.string.PremiumNeededForBoosting;
        goto L74
    L67:
        if (BoostRepository.isMultiBoostsAvailable() == false) goto L70;
        BoostDialogs.showMoreBoostsNeeded(this.dialogId, this);
        return;
    L10:
        if (r2 == 32) goto L62;
        if (isMiniBoostBtnForAdminAvailable() == true) goto L62;
        int r13 = this.type;
        if (r13 != 18) goto L17;
    L60:
        AndroidUtilities.addToClipboard(getBoostLink());
        dismiss();
        return;
    L17:
        if (r13 == 20) goto L60;
        if (r13 == 24) goto L60;
        if (r13 == 25) goto L60;
        if (r13 == 26) goto L60;
        if (r13 == 29) goto L60;
        if (r13 == 22) goto L60;
        if (r13 == 23) goto L60;
        if (r13 == 21) goto L60;
        if (r13 == 27) goto L60;
        if (r13 == 28) goto L60;
        if (r13 == 30) goto L60;
        if (r13 == 35) goto L60;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L43;
    L58:
        dismiss();
        return;
    L43:
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == true) goto L58;
        if (this.isVeryLargeFile == true) goto L58;
        BaseFragment r14 = this.parentFragment;
        if (r14 != null) goto L51;
        return;
    L51:
        if (r14.getVisibleDialog() == null) goto L53;
        this.parentFragment.getVisibleDialog().dismiss();
    L53:
        this.parentFragment.presentFragment(new PremiumPreviewFragment(limitTypeToServerString(this.type)));
        Runnable r15 = this.onShowPremiumScreenRunnable;
        if (r15 == null) goto L56;
        r15.run();
    L56:
        dismiss();
    }

    private /* synthetic */ void lambda$onViewCreated$13() {
        this.limitPreviewView.setBoosts(this.boostsStatus, false);
        limitPreviewIncreaseCurrentValue();
    }

    private /* synthetic */ void lambda$onViewCreated$14(View r4) {
        if (this.premiumButtonSetSubscribe == true) goto L5;
        int r42 = this.type;
        if (r42 != 19) goto L11;
    L37:
        ChannelBoostsController.CanApplyBoost r43 = this.canApplyBoost;
        if (r43.canApply == false) goto L50;
        this.premiumButtonView.buttonLayout.callOnClick();
        ChannelBoostsController.CanApplyBoost r44 = this.canApplyBoost;
        if (r44.alreadyActive == true) goto L42;
        return;
    L42:
        if (r44.boostedNow == false) goto L61;
        LimitReachedBottomSheet$$ExternalSyntheticLambda12 r45 = new LimitReachedBottomSheet$$ExternalSyntheticLambda12(this);
        if (this.canApplyBoost.needSelector == false) goto L46;
        long r0 = 300;
    L47:
        AndroidUtilities.runOnUIThread(r45, r0);
        return;
    L46:
        r0 = 0;
        goto L47
    L61:
        return;
    L50:
        if (r43.alreadyActive == true) goto L52;
    L57:
        dismiss();
        return;
    L52:
        if (BoostRepository.isMultiBoostsAvailable() == false) goto L57;
        if (this.canApplyBoost.isMaxLvl == true) goto L57;
        BoostDialogs.showMoreBoostsNeeded(this.dialogId, this);
        return;
    L11:
        if (r42 == 32) goto L37;
        if (isMiniBoostBtnForAdminAvailable() == true) goto L37;
    L15:
        int r46 = this.type;
        if (r46 == 11) goto L32;
        if (r46 == 34) goto L32;
        if (this.selectedChats.isEmpty() == true) goto L63;
        int r47 = this.type;
        if (r47 != 2) goto L28;
        revokeSelectedLinks();
        return;
    L28:
        if (r47 != 5) goto L59;
        leaveFromSelectedGroups();
        return;
    L59:
        return;
    L63:
        return;
    L32:
        if (this.selectedChats.isEmpty() == false) goto L35;
        dismiss();
        return;
    L35:
        sendInviteMessages(null);
        return;
    L5:
        if (this.parentFragment == null) goto L62;
        BaseFragment.BottomSheetParams r48 = new BaseFragment.BottomSheetParams();
        r48.transitionFromLeft = true;
        r48.allowNestedScroll = false;
        this.parentFragment.showAsSheet(new PremiumPreviewFragment("invite_privacy"), r48);
        goto L15
    }

    private /* synthetic */ void lambda$onViewCreated$4(View r1) {
        boolean r12 = this.premiumButtonView.isShowOverlay();
        PremiumButtonView r0 = this.premiumButtonView;
        if (r12 == false) goto L6;
        r0.overlayTextView.performClick();
        return;
    L6:
        r0.buttonLayout.performClick();
    }

    private /* synthetic */ void lambda$onViewCreated$5(View r4, int r5) {
        if ((r4 instanceof AdminedChannelCell) == false) goto L11;
        AdminedChannelCell r42 = (AdminedChannelCell) r4;
        TLRPC.Chat r52 = r42.getCurrentChannel();
        boolean r1 = this.selectedChats.contains(r52);
        HashSet<Object> r2 = this.selectedChats;
        if (r1 == false) goto L7;
        r2.remove(r52);
    L8:
        r42.setChecked(this.selectedChats.contains(r52), true);
        updateButton();
        return;
    L7:
        r2.add(r52);
        goto L8
    L11:
        if ((r4 instanceof GroupCreateUserCell) == true) goto L13;
        return;
    L13:
        if (this.canSendLink == true) goto L19;
        int r53 = this.type;
        if (r53 != 11) goto L17;
        return;
    L17:
        if (r53 != 34) goto L19;
        return;
    L19:
        GroupCreateUserCell r43 = (GroupCreateUserCell) r4;
        Object r54 = r43.getObject();
        if (r43.isBlocked() == true) goto L22;
        boolean r12 = this.selectedChats.contains(r54);
        HashSet<Object> r22 = this.selectedChats;
        if (r12 == false) goto L28;
        r22.remove(r54);
    L29:
        r43.setChecked(this.selectedChats.contains(r54), true);
        updateButton();
        return;
    L28:
        r22.add(r54);
        goto L29
    L22:
        if ((r54 instanceof TLRPC.User) == false) goto L33;
        showPremiumBlockedToast(r43, ((TLRPC.User) r54).id);
        return;
    }

    private /* synthetic */ boolean lambda$onViewCreated$6(View r2, int r3) {
        this.recyclerListView.getOnItemClickListener().onItemClick(r2, r3);
        if (this.type != 19) goto L7;
    L5:
        return false;
    L7:
        r2.performHapticFeedback(0);     // Catch: Exception -> L6
        goto L5
    }

    private /* synthetic */ void lambda$onViewCreated$7(AlertDialog r3, int r4) {
        this.parentFragment.presentFragment(new PremiumPreviewFragment(null));
        dismiss();
        r3.dismiss();
    }

    private /* synthetic */ void lambda$onViewCreated$9(DialogInterface r2) {
        this.lockInvalidation = false;
        this.limitPreviewView.invalidationEnabled = true;
        this.premiumButtonView.invalidate();
        this.limitPreviewView.invalidate();
    }

    private /* synthetic */ void lambda$revokeLinks$24(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        AndroidUtilities.runOnUIThread(this.onSuccessRunnable);
        return;
    }

    private /* synthetic */ void lambda$revokeLinks$25(ArrayList r4, AlertDialog r5, int r6) {
        dismiss();
        int r52 = 0;
    L4:
        if (r52 >= r4.size()) goto L6;
        TLRPC.TL_channels_updateUsername r62 = new TLRPC.TL_channels_updateUsername();
        r62.channel = MessagesController.getInputChannel((TLRPC.Chat) r4.get(r52));
        r62.username = _UrlKt.FRAGMENT_ENCODE_SET;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r62, new LimitReachedBottomSheet$$ExternalSyntheticLambda24(this), 64);
        r52 = r52 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$sendInviteMessages$20() {
        BulletinFactory r0 = BulletinFactory.global();
        if (r0 == null) goto L10;
        int r1 = this.selectedChats.size();
        HashSet<Object> r2 = this.selectedChats;
        if (r1 != 1) goto L8;
        TLRPC.User r4 = (TLRPC.User) r2.iterator().next();
        r0.createSimpleBulletin(R.raw.voip_invite, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.InviteLinkSentSingle, new Object[]{ContactsController.formatName(r4)}))).show();
        return;
    L8:
        r0.createSimpleBulletin(R.raw.voip_invite, AndroidUtilities.replaceTags(LocaleController.formatPluralString("InviteLinkSent", r2.size(), new Object[]{Integer.valueOf(this.selectedChats.size())}))).show();
        return;
    }

    private /* synthetic */ void lambda$showPremiumBlockedToast$15() {
        if (LaunchActivity.getLastFragment() != null) goto L5;
        return;
    L5:
        BaseFragment.BottomSheetParams r0 = new BaseFragment.BottomSheetParams();
        r0.transitionFromLeft = true;
        r0.allowNestedScroll = false;
        this.parentFragment.showAsSheet(new PremiumPreviewFragment("noncontacts"), r0);
    }

    private void leaveFromSelectedGroups() {
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(UserConfig.getInstance(this.currentAccount).getClientUserId()));
        ArrayList r1 = new ArrayList();
        Iterator<Object> r2 = this.selectedChats.iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        r1.add((TLRPC.Chat) r2.next());
        goto L4
    L6:
        AlertDialog.Builder r22 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        r22.setTitle(LocaleController.formatPluralString("LeaveCommunities", r1.size(), new Object[0]));
        if (r1.size() != 1) goto L9;
        TLRPC.Chat r3 = (TLRPC.Chat) r1.get(0);
        r22.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("ChannelLeaveAlertWithName", R.string.ChannelLeaveAlertWithName, new Object[]{r3.title})));
    L10:
        r22.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r22.setPositiveButton(LocaleController.getString(R.string.VoipGroupLeave), new LimitReachedBottomSheet$$ExternalSyntheticLambda21(this, r1, r0));
        AlertDialog r02 = r22.create();
        r02.show();
        TextView r03 = (TextView) r02.getButton(-1);
        if (r03 == null) goto L15;
        r03.setTextColor(Theme.getColor(Theme.key_text_RedBold, this.resourcesProvider));
        return;
    L15:
        return;
    L9:
        r22.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("ChatsLeaveAlert", R.string.ChatsLeaveAlert, new Object[0])));
        goto L10
    }

    private void limitPreviewIncreaseCurrentValue() {
        LimitPreviewView r0 = this.limitPreviewView;
        TL_stories.TL_premium_boostsStatus r4 = this.boostsStatus;
        int r1 = r4.boosts;
        int r2 = r4.current_level_boosts;
        r0.increaseCurrentValue(r1, r1 - r2, r4.next_level_boosts - r2);
    }

    public static String limitTypeToServerString(int r0) {
        switch(r0) {
            case 0: goto L25;
            case 1: goto L3;
            case 2: goto L23;
            case 3: goto L21;
            case 4: goto L19;
            case 5: goto L17;
            case 6: goto L15;
            case 7: goto L3;
            case 8: goto L13;
            case 9: goto L11;
            case 10: goto L9;
            case 11: goto L3;
            case 12: goto L7;
            case 13: goto L5;
            default: goto L3;
        };
    L3:
        return null;
    L5:
        return "double_limits__chatlists_joined";
    L7:
        return "double_limits__chatlist_invites";
    L9:
        return "double_limits__stickers_faved";
    L11:
        return "double_limits__saved_gifs";
    L13:
        return "double_limits__caption_length";
    L15:
        return "double_limits__upload_max_fileparts";
    L17:
        return "double_limits__channels";
    L19:
        return "double_limits__dialog_filters_chats";
    L21:
        return "double_limits__dialog_filters";
    L23:
        return "double_limits__channels_public";
    L25:
        return "double_limits__dialog_pinned";
    }

    private void loadAdminedChannels() {
        this.loadingAdminedChannels = true;
        this.loading = true;
        updateRows();
        TLRPC.TL_channels_getAdminedPublicChannels r0 = new TLRPC.TL_channels_getAdminedPublicChannels();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new LimitReachedBottomSheet$$ExternalSyntheticLambda10(this));
    }

    private void loadInactiveChannels() {
        this.loading = true;
        updateRows();
        TLRPC.TL_channels_getInactiveChannels r0 = new TLRPC.TL_channels_getInactiveChannels();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new LimitReachedBottomSheet$$ExternalSyntheticLambda4(this));
    }

    private boolean onBoostSuccess() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationNameOnUIThread(NotificationCenter.chatWasBoostedByUser, new Object[]{this.boostsStatus, this.canApplyBoost.copy(), Long.valueOf(this.dialogId)});
        if (this.boostToUnlockGroupBtn == null) goto L9;
        int r0 = getNeededBoostsForUnlockGroup();
        if (r0 != 0) goto L8;
        NotificationCenter.getInstance(this.currentAccount).postNotificationNameOnUIThread(NotificationCenter.groupRestrictionsUnlockedByBoosts, new Object[0]);
        dismiss();
        return false;
    L8:
        this.boostToUnlockGroupBtn.setCount(r0, true);
    L9:
        TransitionSet r02 = new TransitionSet();
        r02.addTransition(new AnonymousClass4(this));
        r02.setOrdering(0);
        TransitionManager.beginDelayedTransition(this.headerView, r02);
        this.headerView.recreateTitleAndDescription();
        this.headerView.title.setText(getBoostsTitleString());
        this.headerView.description.setText(AndroidUtilities.replaceTags(getBoostDescriptionStringAfterBoost()));
        updateButton();
        this.fireworksOverlay.start();
        this.fireworksOverlay.performHapticFeedback(3);     // Catch: Exception -> L15
    L11:
        this.headerView.boostCounterView.setCount(this.canApplyBoost.boostCount, true);
        this.recyclerListView.smoothScrollToPosition(0);
        if (this.type != 32) goto L14;
        this.headerView.boostCounterView.setVisibility(8);
    L14:
        return true;
    }

    public static void openBoostsForPostingStories(BaseFragment r7, long r8, ChannelBoostsController.CanApplyBoost r10, TL_stories.TL_premium_boostsStatus r11, Runnable r12) {
        if (r7 == null) goto L10;
        if (r10 == null) goto L11;
        if (r11 != null) goto L6;
        return;
    L6:
        if (r7.getContext() == null) goto L13;
        LimitReachedBottomSheet r1 = new LimitReachedBottomSheet(r7, r7.getContext(), 18, r7.getCurrentAccount(), r7.getResourceProvider());
        r1.setCanApplyBoost(r10);
        r1.setBoostsStats(r11, true);
        r1.setDialogId(r8);
        r1.showStatisticButtonInLink(r12);
        r1.show();
        return;
    L13:
        return;
    L11:
        return;
    }

    public static LimitReachedBottomSheet openBoostsForRemoveRestrictions(BaseFragment r7, TL_stories.TL_premium_boostsStatus r8, ChannelBoostsController.CanApplyBoost r9, long r10, boolean r12) {
        if (r7 == null) goto L14;
        if (r8 == null) goto L16;
        if (r9 != null) goto L6;
        return null;
    L6:
        if (r7.getContext() == null) goto L18;
        LimitReachedBottomSheet r1 = new LimitReachedBottomSheet(r7, r7.getContext(), 32, r7.getCurrentAccount(), r7.getResourceProvider());
        r1.setCanApplyBoost(r9);
        r1.setBoostsStats(r8, true);
        r1.setDialogId(r10);
        if (r12 == false) goto L12;
        r7.showDialog(r1);
        return r1;
    L12:
        r1.show();
        return r1;
    L18:
        return null;
    L16:
        return null;
    L14:
        return null;
    }

    public static void openBoostsForUsers(BaseFragment r7, boolean r8, long r9, ChannelBoostsController.CanApplyBoost r11, TL_stories.TL_premium_boostsStatus r12, ChatMessageCell r13) {
        if (r7 == null) goto L10;
        if (r11 == null) goto L11;
        if (r12 != null) goto L6;
        return;
    L6:
        if (r7.getContext() == null) goto L13;
        LimitReachedBottomSheet r1 = new LimitReachedBottomSheet(r7, r7.getContext(), 19, r7.getCurrentAccount(), r7.getResourceProvider());
        r1.setCanApplyBoost(r11);
        r1.setBoostsStats(r12, r8);
        r1.setDialogId(r9);
        r1.setChatMessageCell(r13);
        r7.showDialog(r1);
        return;
    L13:
        return;
    L11:
        return;
    }

    private void revokeLinks(ArrayList<TLRPC.Chat> r7) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        r0.setTitle(LocaleController.formatPluralString("RevokeLinks", r7.size(), new Object[0]));
        if (r7.size() != 1) goto L9;
        TLRPC.Chat r1 = r7.get(0);
        boolean r2 = this.parentIsChannel;
        int r3 = this.currentAccount;
        if (r2 == false) goto L7;
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("RevokeLinkAlertChannel", R.string.RevokeLinkAlertChannel, new Object[]{MessagesController.getInstance(r3).linkPrefix + "/" + ChatObject.getPublicUsername(r1), r1.title})));
    L12:
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.getString(R.string.RevokeButton), new LimitReachedBottomSheet$$ExternalSyntheticLambda13(this, r7));
        AlertDialog r72 = r0.create();
        r72.show();
        TextView r73 = (TextView) r72.getButton(-1);
        if (r73 == null) goto L16;
        r73.setTextColor(Theme.getColor(Theme.key_text_RedBold, this.resourcesProvider));
        return;
    L16:
        return;
    L7:
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("RevokeLinkAlert", R.string.RevokeLinkAlert, new Object[]{MessagesController.getInstance(r3).linkPrefix + "/" + ChatObject.getPublicUsername(r1), r1.title})));
        goto L12
    L9:
        if (this.parentIsChannel == false) goto L11;
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("RevokeLinksAlertChannel", R.string.RevokeLinksAlertChannel, new Object[0])));
        goto L12
    L11:
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("RevokeLinksAlert", R.string.RevokeLinksAlert, new Object[0])));
        goto L12
    }

    private void revokeSelectedLinks() {
        ArrayList<TLRPC.Chat> r0 = new ArrayList();
        Iterator<Object> r1 = this.selectedChats.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        Object r2 = r1.next();
        this.chats.add((TLRPC.Chat) r2);
        goto L4
    L6:
        revokeLinks(r0);
    }

    private void sendInviteMessages(HashMap<Long, Long> r28) {
        HashMap<Long, Long> r1 = r28;
        if (TextUtils.isEmpty(this.forceLink) == true) goto L6;
        String r2 = this.forceLink;
    L5:
        String r3 = r2;
        ArrayList r22 = new ArrayList();
        ArrayList r4 = new ArrayList();
        Iterator<Object> r5 = this.selectedChats.iterator();
    L18:
        if (r5.hasNext() == false) goto L27;
        TLRPC.User r6 = (TLRPC.User) r5.next();
        long r7 = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(r6.id);
        if (r7 > 0) goto L23;
        r7 = DialogObject.getMessagesStarsPrice(MessagesController.getInstance(this.currentAccount).isUserContactBlocked(r6.id));
    L23:
        if (r7 < 0) goto L25;
        ArrayList r72 = r22;
    L26:
        r72.add(r6);
        goto L18
    L25:
        r72 = r4;
        goto L26
    L27:
        int r42 = 0;
        boolean r52 = true;
        if (r1 == null) goto L30;
    L36:
        Iterator<Object> r23 = this.selectedChats.iterator();
        boolean r20 = false;
    L38:
        if (r23.hasNext() == false) goto L47;
        TLRPC.User r43 = (TLRPC.User) r23.next();
        if (r1 != null) goto L42;
        long r62 = 0;
    L43:
        boolean r9 = r52;
        Iterator<Object> r24 = r23;
        SendMessagesHelper.SendMessageParams r44 = SendMessagesHelper.SendMessageParams.of(r3, r43.id, null, null, null, true, null, null, null, false, 0, 0, null, false);
        r44.payStars = r62;
        SendMessagesHelper.getInstance(this.currentAccount).sendMessage(r44);
        if (r44.payStars <= 0) goto L46;
        r20 = r9;
    L46:
        r1 = r28;
        r52 = r9;
        r23 = r24;
        goto L38
    L42:
        r62 = r1.get(Long.valueOf(r43.id)).longValue();
        goto L43
    L47:
        if (r20 == true) goto L49;
        AndroidUtilities.runOnUIThread(new LimitReachedBottomSheet$$ExternalSyntheticLambda26(this));
    L49:
        dismiss();
        return;
    L30:
        if (r22.isEmpty() == true) goto L36;
        ArrayList r12 = new ArrayList();
        int r32 = r22.size();
    L32:
        if (r42 >= r32) goto L34;
        Object r63 = r22.get(r42);
        r42 = r42 + 1;
        r12.add(Long.valueOf(((TLRPC.User) r63).id));
        goto L32
    L34:
        AlertsCreator.ensurePaidMessagesMultiConfirmation(this.currentAccount, r12, 1, new LimitReachedBottomSheet$$ExternalSyntheticLambda25(this));
        return;
    L6:
        TLRPC.ChatFull r25 = MessagesController.getInstance(this.currentAccount).getChatFull(this.fromChat.id);
        if (r25 != null) goto L11;
        dismiss();
        return;
    L11:
        if (this.fromChat.username == null) goto L13;
        r2 = "@" + this.fromChat.username;
        goto L5
    L13:
        TLRPC.TL_chatInviteExported r26 = r25.exported_invite;
        if (r26 == null) goto L51;
        r2 = r26.link;
        goto L5
    L51:
        dismiss();
    }

    private void setupBoostFeatures() {
        this.boostFeatures = new ArrayList();
        TL_stories.TL_premium_boostsStatus r0 = this.boostsStatus;
        int r1 = 1;
        if (r0 == null) goto L5;
        int r02 = r0.level + 1;
    L7:
        if (this.type == 31) goto L10;
        r1 = r02;
    L10:
        MessagesController r03 = MessagesController.getInstance(this.currentAccount);
        int r2 = 10;
        if (r03 == null) goto L23;
        MessagesController.PeerColors r3 = r03.peerColors;
        int r4 = 0;
        if (r3 == null) goto L15;
        int r32 = r3.maxLevel(isGroup());
    L16:
        int r22 = Math.max(10, r32);
        MessagesController.PeerColors r33 = r03.profilePeerColors;
        if (r33 == null) goto L19;
        r4 = r33.maxLevel(isGroup());
    L19:
        int r23 = Math.max(r22, r4);
        if (isGroup() == false) goto L22;
        r2 = Math.max(Math.max(Math.max(Math.max(Math.max(r23, r03.groupTranscribeLevelMin), r03.groupWallpaperLevelMin), r03.groupCustomWallpaperLevelMin), r03.groupEmojiStatusLevelMin), r03.groupProfileBgIconLevelMin);
        goto L23
    L22:
        r2 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(r23, r03.channelBgIconLevelMin), r03.channelProfileIconLevelMin), r03.channelEmojiStatusLevelMin), r03.channelWallpaperLevelMin), r03.channelCustomWallpaperLevelMin), r03.channelRestrictSponsoredLevelMin), r03.channelAutotranslationLevelMin);
        goto L23
    L15:
        r32 = 0;
    L23:
        ArrayList<BoostFeature> r04 = null;
    L24:
        if (r1 > r2) goto L31;
        ArrayList<BoostFeature> r34 = boostFeaturesForLevel(r1);
        if (r04 != null) goto L28;
    L29:
        ArrayList<BoostFeature> r05 = this.boostFeatures;
        r05.add(new BoostFeature.BoostFeatureLevel(r1, r05.isEmpty()));
        this.boostFeatures.addAll(r34);
        r04 = r34;
    L30:
        r1 = r1 + 1;
        goto L24
    L28:
        if (BoostFeature.arraysEqual(r04, r34) == true) goto L30;
    L31:
        return;
    L5:
        r02 = 1;
        goto L7
    }

    private void showPremiumBlockedToast(View r3, long r4) {
        int r0 = -this.shiftDp;
        this.shiftDp = r0;
        AndroidUtilities.shakeViewSpring(r3, r0);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        if (r4 < 0) goto L5;
        String r32 = UserObject.getForcedFirstName(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r4)));
    L6:
        boolean r42 = MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked();
        ViewGroup r5 = this.containerView;
        if (r42 == false) goto L9;
        Bulletin r2 = BulletinFactory.of((FrameLayout) r5, this.resourcesProvider).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserBlockedNonPremium, new Object[]{r32})));
    L10:
        r2.show();
        return;
    L9:
        r2 = BulletinFactory.of((FrameLayout) r5, this.resourcesProvider).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserBlockedNonPremium, new Object[]{r32})), LocaleController.getString(R.string.UserBlockedNonPremiumButton), new LimitReachedBottomSheet$$ExternalSyntheticLambda11(this));
        goto L10
    L5:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private void updateButton() {
        if (this.premiumButtonSetSubscribe == false) goto L6;
        this.premiumButtonView.setOverlayText(LocaleController.getString(R.string.InvitePremiumBlockedSubscribe), false, false);
        return;
    L6:
        int r0 = this.type;
        if (r0 != 19) goto L9;
    L40:
        ChannelBoostsController.CanApplyBoost r02 = this.canApplyBoost;
        boolean r1 = r02.canApply;
        if (r1 == true) goto L45;
        if (r02.empty == true) goto L45;
    L55:
        if (r1 == false) goto L69;
        boolean r03 = BoostRepository.isMultiBoostsAvailable();
        PremiumButtonView r12 = this.premiumButtonView;
        if (r03 == false) goto L60;
        r12.setOverlayText(LocaleController.getString(R.string.BoostingBoostAgain), true, true);
    L64:
        this.boostMiniBtn.setText(LocaleController.getString(R.string.BoostBtn), true);
        ButtonWithCounterView r4 = this.boostToUnlockGroupBtn;
        if (r4 == null) goto L85;
        r4.setText(LocaleController.getString(R.string.BoostGroup), true);
        return;
    L85:
        return;
    L60:
        if (isGroup() == false) goto L62;
        int r04 = R.string.BoostGroup;
    L63:
        r12.setOverlayText(LocaleController.getString(r04), true, true);
        goto L64
    L62:
        r04 = R.string.BoostChannel;
        goto L63
    L69:
        if (r02.isMaxLvl == false) goto L75;
        this.boostMiniBtn.setText(LocaleController.getString(R.string.OK), true);
        ButtonWithCounterView r05 = this.boostToUnlockGroupBtn;
        if (r05 == null) goto L73;
        r05.setText(LocaleController.getString(R.string.OK), true);
    L73:
        this.premiumButtonView.setOverlayText(LocaleController.getString(R.string.OK), true, true);
        return;
    L75:
        boolean r06 = BoostRepository.isMultiBoostsAvailable();
        ButtonWithCounterView r13 = this.boostToUnlockGroupBtn;
        if (r06 == false) goto L81;
        if (r13 == null) goto L79;
        r13.setText(LocaleController.getString(R.string.BoostGroup), true);
    L79:
        this.boostMiniBtn.setText(LocaleController.getString(R.string.BoostBtn), true);
        this.premiumButtonView.setOverlayText(LocaleController.getString(R.string.BoostingBoostAgain), true, true);
        return;
    L81:
        if (r13 == null) goto L83;
        r13.setText(LocaleController.getString(R.string.OK), true);
    L83:
        this.boostMiniBtn.setText(LocaleController.getString(R.string.OK), true);
        this.premiumButtonView.setOverlayText(LocaleController.getString(R.string.OK), true, true);
        return;
    L45:
        if (r02.boostedNow == true) goto L55;
        if (r02.alreadyActive == true) goto L55;
        boolean r07 = r02.isMaxLvl;
        PremiumButtonView r42 = this.premiumButtonView;
        if (r07 == false) goto L53;
        r42.setOverlayText(LocaleController.getString(R.string.OK), true, true);
        return;
    L53:
        r42.clearOverlayText();
        return;
    L9:
        if (r0 == 32) goto L40;
        if (isMiniBoostBtnForAdminAvailable() == true) goto L40;
        int r08 = this.type;
        if (r08 != 11) goto L16;
    L31:
        this.premiumButtonView.checkCounterView();
        if (this.canSendLink == true) goto L34;
        this.premiumButtonView.setOverlayText(LocaleController.getString(R.string.Close), true, true);
    L38:
        this.premiumButtonView.counterView.setCount(this.selectedChats.size(), true);
        this.premiumButtonView.invalidate();
        return;
    L34:
        int r09 = this.selectedChats.size();
        PremiumButtonView r14 = this.premiumButtonView;
        if (r09 <= 0) goto L37;
        r14.setOverlayText(LocaleController.getString(R.string.SendInviteLink), true, true);
        goto L38
    L37:
        r14.setOverlayText(LocaleController.getString(R.string.ActionSkip), true, true);
        goto L38
    L16:
        if (r08 == 34) goto L31;
        if (this.selectedChats.size() <= 0) goto L29;
        int r010 = this.type;
        if (r010 != 2) goto L24;
        String r011 = LocaleController.formatPluralString("RevokeLinks", this.selectedChats.size(), new Object[0]);
    L27:
        this.premiumButtonView.setOverlayText(r011, true, true);
        return;
    L24:
        if (r010 != 5) goto L26;
        r011 = LocaleController.formatPluralString("LeaveCommunities", this.selectedChats.size(), new Object[0]);
        goto L27
    L26:
        r011 = null;
        goto L27
    L29:
        this.premiumButtonView.clearOverlayText();
    }

    private void updateRows() {
        this.dividerRow = -1;
        this.chatStartRow = -1;
        this.chatEndRow = -1;
        this.loadingRow = -1;
        this.linkRow = -1;
        this.emptyViewDividerRow = -1;
        this.boostFeaturesStartRow = -1;
        this.rowCount = 1;
        this.headerRow = 0;
        int r1 = this.type;
        if (r1 != 19) goto L5;
    L84:
        if (r1 == 19) goto L86;
    L87:
        this.topPadding = 0.24f;
        int r12 = this.rowCount;
        this.rowCount = r12 + 1;
        this.linkRow = r12;
        if (MessagesController.getInstance(this.currentAccount).giveawayGiftsPurchaseAvailable == false) goto L90;
        int r13 = this.rowCount;
        this.rowCount = r13 + 1;
        this.bottomRow = r13;
    L90:
        setupBoostFeatures();
        int r14 = this.rowCount;
        int r2 = r14 + 1;
        this.rowCount = r2;
        this.boostFeaturesStartRow = r14;
        this.rowCount = r2 + (this.boostFeatures.size() - 1);
    L91:
        notifyDataSetChanged();
        return;
    L86:
        if (ChatObject.hasAdminRights(getChat()) == false) goto L90;
    L5:
        if (r1 == 18) goto L84;
        if (r1 == 20) goto L84;
        if (r1 == 24) goto L84;
        if (r1 == 27) goto L84;
        if (r1 == 28) goto L84;
        if (r1 == 22) goto L84;
        if (r1 == 23) goto L84;
        if (r1 == 25) goto L84;
        if (r1 == 26) goto L84;
        if (r1 == 29) goto L84;
        if (r1 == 21) goto L84;
        if (r1 == 30) goto L84;
        if (r1 == 35) goto L84;
        if (r1 != 31) goto L34;
    L83:
        this.topPadding = 0.24f;
        setupBoostFeatures();
        int r15 = this.rowCount;
        this.chatStartRow = r15;
        int r22 = r15 + 1;
        this.rowCount = r22;
        this.boostFeaturesStartRow = r15;
        int r23 = r22 + (this.boostFeatures.size() - 1);
        this.rowCount = r23;
        this.chatEndRow = r23;
        goto L91
    L34:
        if (r1 == 32) goto L83;
        if (hasFixedSize(r1) == true) goto L91;
        int r16 = this.type;
        if (r16 == 11) goto L42;
        if (r16 == 34) goto L42;
        int r24 = this.rowCount;
        int r5 = r24 + 1;
        this.dividerRow = r24;
        this.rowCount = r24 + 2;
        this.chatsTitleRow = r5;
    L44:
        if (this.loading == false) goto L46;
        int r0 = this.rowCount;
        this.rowCount = r0 + 1;
        this.loadingRow = r0;
        goto L91
    L46:
        if (r16 == 11) goto L48;
    L49:
        if (r16 == 11) goto L51;
    L70:
        int r17 = this.rowCount;
        this.chatStartRow = r17;
        int r25 = this.type;
        if (r25 == 11) goto L78;
        if (r25 == 34) goto L78;
        if (r25 != 5) goto L77;
        this.rowCount = r17 + this.inactiveChats.size();
    L79:
        this.chatEndRow = this.rowCount;
    L81:
        if ((this.chatEndRow - this.chatStartRow) <= 1) goto L91;
        int r02 = this.rowCount;
        this.rowCount = r02 + 1;
        this.emptyViewDividerRow = r02;
        goto L91
    L77:
        this.rowCount = r17 + this.chats.size();
    L78:
        this.rowCount = r17 + this.restrictedUsers.size();
        goto L79
    L51:
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == true) goto L70;
        ArrayList<Long> r18 = this.premiumInviteBlockedUsers;
        if (r18 != null) goto L55;
    L56:
        ArrayList<Long> r19 = this.premiumMessagingBlockedUsers;
        if (r19 == null) goto L70;
        if (r19.size() < this.restrictedUsers.size()) goto L70;
    L60:
        ArrayList<Long> r110 = this.premiumInviteBlockedUsers;
        if (r110 == null) goto L70;
        if (r110.size() != 1) goto L70;
        ArrayList<Long> r111 = this.premiumMessagingBlockedUsers;
        if (r111 == null) goto L70;
        if (r111.size() != 1) goto L70;
        if (this.canSendLink == true) goto L81;
    L55:
        if (r18.isEmpty() == false) goto L60;
    L48:
        if (this.canSendLink == false) goto L91;
    L42:
        this.topPadding = 0.24f;
        goto L44
    }

    public int channelColorLevelMin() {
        return 0;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r1) {
        return new AnonymousClass5(this);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r9, int r10, Object... r11) {
        int r3 = 0;
        if (r9 != NotificationCenter.boostByChannelCreated) goto L72;
        TLRPC.Chat r92 = (TLRPC.Chat) r11[0];
        boolean r102 = ((Boolean) r11[1]).booleanValue();
        BaseFragment r112 = getBaseFragment().getParentLayout().getLastFragment();
        BaseFragment r5 = null;
        if ((r112 instanceof ChatCustomReactionsEditActivity) == false) goto L30;
        List<BaseFragment> r4 = getBaseFragment().getParentLayout().getFragmentStack();
        if (r4.size() < 2) goto L9;
        BaseFragment r1 = r4.get(r4.size() - 2);
    L11:
        if (r4.size() < 3) goto L13;
        BaseFragment r0 = r4.get(r4.size() - 3);
    L15:
        if (r4.size() < 4) goto L18;
        r5 = r4.get(r4.size() - 4);
    L18:
        if ((r1 instanceof ChatEditActivity) == false) goto L20;
        getBaseFragment().getParentLayout().removeFragmentFromStack(r1);
    L20:
        dismiss();
        if (r102 == true) goto L23;
        r112.finishFragment();
        BoostDialogs.showBulletin(r0, r92, false);
        return;
    L23:
        if ((r0 instanceof ProfileActivity) == false) goto L25;
        getBaseFragment().getParentLayout().removeFragmentFromStack(r0);
    L25:
        r112.finishFragment();
        BoostDialogs.showBulletin(r5, r92, true);
        return;
    L13:
        r0 = null;
        goto L15
    L9:
        r1 = null;
        goto L11
    L30:
        if ((r112 instanceof ChatActivity) == false) goto L35;
        if (r102 == false) goto L35;
        BoostDialogs.showBulletin(r112, r92, true);
        return;
    L35:
        if ((r112 instanceof ChannelColorActivity) == false) goto L53;
        if (r102 == false) goto L53;
        List<BaseFragment> r103 = getBaseFragment().getParentLayout().getFragmentStack();
        ArrayList r113 = new ArrayList();
        int r02 = r103.size() - 2;
    L38:
        if (r02 < 0) goto L46;
        BaseFragment r12 = r103.get(r02);
        if ((r12 instanceof ChatActivity) == true) goto L45;
        if ((r12 instanceof DialogsActivity) == true) goto L45;
        r113.add(r12);
        r02 = r02 - 1;
    L45:
        r5 = r12;
    L46:
        if (r5 == null) goto L103;
        int r104 = r113.size();
    L49:
        if (r3 >= r104) goto L51;
        Object r03 = r113.get(r3);
        r3 = r3 + 1;
        getBaseFragment().getParentLayout().removeFragmentFromStack((BaseFragment) r03);
        goto L49
    L51:
        getBaseFragment().finishFragment();
        dismiss();
        BoostDialogs.showBulletin(r5, r92, true);
        return;
    L103:
        return;
    L53:
        if (r102 == false) goto L66;
        if (StoryRecorder.isVisible() == false) goto L58;
        ChatActivity r105 = ChatActivity.of(-r92.id);
        LaunchActivity.getLastFragment().presentFragment(r105, false, false);
        StoryRecorder.destroyInstance();
        dismiss();
        BoostDialogs.showBulletin(r105, r92, true);
        return;
    L58:
        List<BaseFragment> r106 = getBaseFragment().getParentLayout().getFragmentStack();
        if (r106.size() < 2) goto L61;
        r5 = r106.get(r106.size() - 2);
    L61:
        getBaseFragment().finishFragment();
        dismiss();
        if ((r5 instanceof ChatActivity) == false) goto L99;
        BoostDialogs.showBulletin(r5, r92, true);
        return;
    L99:
        return;
    L66:
        if (StoryRecorder.isVisible() == false) goto L69;
        ChatActivity r107 = ChatActivity.of(-r92.id);
        LaunchActivity.getLastFragment().presentFragment(r107, false, false);
        StoryRecorder.destroyInstance();
        dismiss();
        BoostDialogs.showBulletin(r107, r92, false);
        return;
    L69:
        dismiss();
        BoostDialogs.showBulletin(LaunchActivity.getLastFragment(), r92, false);
        return;
    L72:
        if (r9 != NotificationCenter.boostedChannelByUser) goto L92;
        TL_stories.TL_premium_myBoosts r93 = (TL_stories.TL_premium_myBoosts) r11[0];
        int r108 = ((Integer) r11[1]).intValue();
        int r13 = ((Integer) r11[2]).intValue();
        TL_stories.TL_premium_boostsStatus r114 = (TL_stories.TL_premium_boostsStatus) r11[3];
        if (r114 != null) goto L76;
        return;
    L76:
        if (this.canApplyBoost == null) goto L104;
        this.boostsStatus.boosts += r108;
        if (this.type != 32) goto L83;
        TLRPC.ChatFull r04 = getChatFull();
        if (r04 == null) goto L83;
        r04.boosts_applied += r108;
    L83:
        limitPreviewIncreaseCurrentValue();
        setBoostsStats(r114, this.isCurrentChat);
        ChannelBoostsController.CanApplyBoost r115 = this.canApplyBoost;
        if (this.boostsStatus.next_level_boosts > 0) goto L86;
        boolean r05 = true;
    L87:
        r115.isMaxLvl = r05;
        r115.boostedNow = true;
        r115.setMyBoosts(r93);
        if (onBoostSuccess() == false) goto L101;
        BulletinFactory.of(this.container, this.resourcesProvider).createSimpleBulletinWithIconSize(R.raw.ic_boosts_replace, LocaleController.formatPluralString("BoostingReassignedFromPlural", r108, new Object[]{LocaleController.formatPluralString("BoostingFromOtherChannel", r13, new Object[0])}), 30).setDuration(4000).show(true);
        return;
    L101:
        return;
    L86:
        r05 = false;
        goto L87
    L104:
        return;
    L92:
        if (r9 != NotificationCenter.didStartedMultiGiftsSelector) goto L102;
        dismiss();
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        int r0 = this.type;
        if (r0 == 11) goto L28;
        if (r0 == 34) goto L26;
        if (r0 == 35) goto L24;
        switch(r0) {
            case 18: goto L22;
            case 19: goto L16;
            case 20: goto L22;
            case 21: goto L22;
            case 22: goto L22;
            case 23: goto L22;
            case 24: goto L22;
            case 25: goto L22;
            case 26: goto L22;
            case 27: goto L22;
            case 28: goto L22;
            case 29: goto L22;
            case 30: goto L16;
            case 31: goto L14;
            case 32: goto L12;
            default: goto L10;
        };
    L10:
        return LocaleController.getString(R.string.LimitReached);
    L12:
        return LocaleController.getString(R.string.BoostGroup);
    L14:
        return LocaleController.getString(R.string.BoostingAdditionalFeaturesTitle);
    L16:
        if (isGroup() == false) goto L18;
        int r2 = R.string.BoostGroup;
    L20:
        return LocaleController.getString(r2);
    L18:
        r2 = R.string.BoostChannel;
        goto L20
    L22:
        return LocaleController.getString(R.string.UnlockBoostChannelFeatures);
    L24:
        return LocaleController.getString(R.string.UnlockBoostChannelFeatures);
    L26:
        return LocaleController.getString(R.string.CallInviteViaLink);
    L28:
        return LocaleController.getString(R.string.ChannelInviteViaLink2);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.boostByChannelCreated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.boostedChannelByUser);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didStartedMultiGiftsSelector);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.boostByChannelCreated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.boostedChannelByUser);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didStartedMultiGiftsSelector);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onViewCreated(FrameLayout r18) {
        super.onViewCreated(r18);
        Context r2 = r18.getContext();
        ButtonWithCounterView r3 = new ButtonWithCounterView(r2, this.resourcesProvider);
        this.boostMiniBtn = r3;
        r3.setFlickeringLoading(true);
        this.boostMiniBtn.setText(LocaleController.getString(R.string.BoostBtn), false);
        this.boostMiniBtn.setOnClickListener(new LimitReachedBottomSheet$$ExternalSyntheticLambda5(this));
        AnonymousClass1 r32 = new AnonymousClass1(this, r2, true, this.resourcesProvider);
        this.premiumButtonView = r32;
        ScaleStateListAnimator.apply(r32, 0.02f, 1.2f);
        if (this.hasFixedSize == true) goto L31;
        int r33 = this.type;
        if (r33 == 18) goto L31;
        if (r33 == 20) goto L31;
        if (r33 == 24) goto L31;
        if (r33 == 25) goto L31;
        if (r33 == 26) goto L31;
        if (r33 == 29) goto L31;
        if (r33 == 22) goto L31;
        if (r33 == 23) goto L31;
        if (r33 == 21) goto L31;
        if (r33 == 27) goto L31;
        if (r33 == 28) goto L31;
        if (r33 == 30) goto L31;
        if (r33 == 35) goto L31;
        AnonymousClass2 r34 = new AnonymousClass2(this, r2);
        this.divider = r34;
        r34.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
        r18.addView(this.divider, LayoutHelper.createFrame(-1, 72.0f, 80, 0.0f, 0.0f, 0.0f, 0.0f));
    L31:
        PremiumButtonView r35 = this.premiumButtonView;
        int r5 = this.backgroundPaddingLeft;
        float r8 = AndroidUtilities.density;
        r18.addView(r35, LayoutHelper.createFrame(-1, 48.0f, 80, (r5 / r8) + 16.0f, 0.0f, (r5 / r8) + 16.0f, 12.0f));
        this.recyclerListView.setPadding(0, 0, 0, AndroidUtilities.dp(72.0f));
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setClipChildren(false);
        this.recyclerListView.setOnItemClickListener(new LimitReachedBottomSheet$$ExternalSyntheticLambda6(this));
        this.recyclerListView.setOnItemLongClickListener(new LimitReachedBottomSheet$$ExternalSyntheticLambda7(this));
        this.premiumButtonView.buttonLayout.setOnClickListener(new LimitReachedBottomSheet$$ExternalSyntheticLambda8(this, r2));
        this.premiumButtonView.overlayTextView.setOnClickListener(new LimitReachedBottomSheet$$ExternalSyntheticLambda9(this));
        this.enterAnimator = new RecyclerItemsEnterAnimator(this.recyclerListView, true);
    }

    public void setBoostsStats(TL_stories.TL_premium_boostsStatus r1, boolean r2) {
        this.boostsStatus = r1;
        this.isCurrentChat = r2;
        updateRows();
    }

    public void setCanApplyBoost(ChannelBoostsController.CanApplyBoost r1) {
        this.canApplyBoost = r1;
        updateButton();
        updatePremiumButtonText();
    }

    public void setChatMessageCell(ChatMessageCell r1) {
        this.chatMessageCell = r1;
    }

    public void setCurrentValue(int r1) {
        this.currentValue = r1;
    }

    public void setDialogId(long r1) {
        this.dialogId = r1;
        updateRows();
    }

    public void setRequiredLvl(int r1) {
        this.requiredLvl = r1;
    }

    public void setRestrictedUsers(TLRPC.Chat r6, ArrayList<TLRPC.User> r7, ArrayList<Long> r8, ArrayList<Long> r9, String r10) {
        this.fromChat = r6;
        this.forceLink = r10;
        if (TextUtils.isEmpty(r10) == true) goto L5;
    L8:
        boolean r62 = true;
    L9:
        this.canSendLink = r62;
        this.restrictedUsers = new ArrayList(r7);
        this.premiumMessagingBlockedUsers = r8;
        this.premiumInviteBlockedUsers = r9;
        this.selectedChats.clear();
        if (this.canSendLink == false) goto L18;
        ArrayList<TLRPC.User> r63 = this.restrictedUsers;
        int r72 = r63.size();
        int r102 = 0;
    L12:
        if (r102 >= r72) goto L18;
        TLRPC.User r2 = r63.get(r102);
        r102 = r102 + 1;
        TLRPC.User r22 = r2;
        if (r8 == null) goto L17;
        if (r8.contains(Long.valueOf(r22.id)) == true) goto L12;
    L17:
        this.selectedChats.add(r22);
    L18:
        updateRows();
        updateButton();
        int r64 = this.type;
        if (r64 == 11) goto L23;
        if (r64 == 34) goto L23;
        return;
    L23:
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == true) goto L59;
        if (r9 != null) goto L26;
    L27:
        if (r8 != null) goto L29;
        return;
    L29:
        if (r8.size() < this.restrictedUsers.size()) goto L61;
    L30:
        if (r9 == null) goto L62;
        if (r8 != null) goto L33;
        return;
    L33:
        if (r9.size() != 1) goto L37;
        if (r8.size() != 1) goto L37;
    L38:
        PremiumButtonView r65 = this.premiumButtonView;
        if (r65 != null) goto L41;
    L43:
        View r66 = this.divider;
        if (r66 != null) goto L46;
    L48:
        RecyclerListView r5 = this.recyclerListView;
        if (r5 == null) goto L65;
        r5.setPadding(0, 0, 0, 0);
        return;
    L65:
        return;
    L46:
        if (r66.getParent() == null) goto L48;
        ((ViewGroup) this.divider.getParent()).removeView(this.divider);
        goto L48
    L41:
        if (r65.getParent() == null) goto L43;
        ((ViewGroup) this.premiumButtonView.getParent()).removeView(this.premiumButtonView);
    L37:
        if (r8.size() >= r9.size()) goto L38;
        return;
    L62:
        return;
    L61:
        return;
    L26:
        if (r9.isEmpty() == false) goto L30;
    L59:
        return;
    L5:
        if (ChatObject.canUserDoAdminAction(r6, 3) == true) goto L8;
        r62 = false;
        goto L9
    }

    public void setVeryLargeFile(boolean r1) {
        this.isVeryLargeFile = r1;
        updatePremiumButtonText();
    }

    public void showStatisticButtonInLink(Runnable r1) {
        this.statisticClickRunnable = r1;
    }

    public void updatePremiumButtonText() {
        if (this.premiumButtonSetSubscribe == false) goto L6;
        this.premiumButtonView.setOverlayText(LocaleController.getString(R.string.InvitePremiumBlockedSubscribe), false, false);
        return;
    L6:
        int r0 = this.type;
        if (r0 != 19) goto L9;
    L69:
        boolean r02 = BoostRepository.isMultiBoostsAvailable();
        PremiumButtonView r1 = this.premiumButtonView;
        if (r02 == false) goto L87;
        AnimatedTextView r03 = r1.buttonTextView;
        ChannelBoostsController.CanApplyBoost r12 = this.canApplyBoost;
        if (r12 == null) goto L77;
        if (r12.alreadyActive == false) goto L77;
        String r13 = LocaleController.getString(R.string.BoostingBoostAgain);
    L81:
        r03.setText(r13);
        ChannelBoostsController.CanApplyBoost r04 = this.canApplyBoost;
        if (r04 != null) goto L84;
        return;
    L84:
        if (r04.isMaxLvl == false) goto L94;
        this.premiumButtonView.buttonTextView.setText(LocaleController.getString(R.string.OK));
        return;
    L94:
        return;
    L77:
        if (isGroup() == false) goto L79;
        int r14 = R.string.BoostGroup;
    L80:
        r13 = LocaleController.getString(r14);
        goto L81
    L79:
        r14 = R.string.BoostChannel;
        goto L80
    L87:
        AnimatedTextView r05 = r1.buttonTextView;
        if (isGroup() == false) goto L90;
        int r4 = R.string.BoostGroup;
    L91:
        r05.setText(LocaleController.getString(r4));
        return;
    L90:
        r4 = R.string.BoostChannel;
        goto L91
    L9:
        if (r0 == 32) goto L69;
        if (isMiniBoostBtnForAdminAvailable() == true) goto L69;
        int r06 = this.type;
        if (r06 != 18) goto L16;
    L67:
        SpannableStringBuilder r07 = new SpannableStringBuilder("d ");
        r07.setSpan(new ColoredImageSpan(R.drawable.msg_copy_filled), 0, 1, 0);
        r07.append(LocaleController.getString(R.string.CopyLink));
        this.premiumButtonView.buttonTextView.setText(r07);
        return;
    L16:
        if (r06 == 20) goto L67;
        if (r06 == 24) goto L67;
        if (r06 == 25) goto L67;
        if (r06 == 26) goto L67;
        if (r06 == 29) goto L67;
        if (r06 == 22) goto L67;
        if (r06 == 23) goto L67;
        if (r06 == 21) goto L67;
        if (r06 == 27) goto L67;
        if (r06 == 28) goto L67;
        if (r06 == 30) goto L67;
        if (r06 == 35) goto L67;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L43;
    L65:
        this.premiumButtonView.buttonTextView.setText(LocaleController.getString(R.string.OK));
        this.premiumButtonView.hideIcon();
        return;
    L43:
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == true) goto L65;
        if (this.isVeryLargeFile == true) goto L65;
        this.premiumButtonView.buttonTextView.setText(LocaleController.getString(R.string.IncreaseLimit));
        LimitParams r08 = this.limitParams;
        if (r08 == null) goto L63;
        int r15 = r08.defaultLimit;
        int r2 = r15 + 1;
        int r09 = r08.premiumLimit;
        if (r2 != r09) goto L53;
        this.premiumButtonView.setIcon(R.raw.addone_icon);
        return;
    L53:
        if (r15 == 0) goto L61;
        if (r09 == 0) goto L61;
        if ((r09 / r15) < 1.6f) goto L61;
        if ((r09 / r15) > 2.5f) goto L61;
        this.premiumButtonView.setIcon(R.raw.double_icon);
        return;
    L61:
        this.premiumButtonView.hideIcon();
        return;
    L63:
        this.premiumButtonView.hideIcon();
    }
}
