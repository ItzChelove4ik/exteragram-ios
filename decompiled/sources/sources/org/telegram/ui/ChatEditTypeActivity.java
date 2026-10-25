package org.telegram.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.AdminedChannelCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LoadingCell;
import org.telegram.ui.Cells.RadioButtonCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.ChangeUsernameActivity;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.InviteLinkBottomSheet;
import org.telegram.ui.Components.JoinToSendSettingsView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkActionView;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SectionsScrollView;
import org.telegram.ui.Components.TypefaceSpan;

/* loaded from: classes6.dex */
public class ChatEditTypeActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private boolean activatingEditableLink;
    private ArrayList<AdminedChannelCell> adminedChannelCells;
    private ShadowSectionCell adminedInfoCell;
    private LinearLayout adminnedChannelsLayout;
    private boolean applyToAllInviteLinks;
    private boolean canCreatePublic;
    private long chatId;
    private int checkReqId;
    private Runnable checkRunnable;
    private TextInfoPrivacyCell checkTextView;
    private TLRPC.Chat currentChat;
    private boolean deactivatingLinks;
    private ActionBarMenuItem doneButton;
    private CrossfadeDrawable doneButtonDrawable;
    private ValueAnimator doneButtonDrawableAnimator;
    private EditTextBoldCursor editText;
    private ChangeUsernameActivity.UsernameCell editableUsernameCell;
    private Boolean editableUsernameUpdated;
    private Boolean editableUsernameWasActive;
    private ArrayList<TLRPC.TL_username> editableUsernames;
    private Runnable enableDoneLoading;
    private HeaderCell headerCell;
    private HeaderCell headerCell2;
    private boolean ignoreScroll;
    private boolean ignoreTextChanges;
    private TLRPC.ChatFull info;
    private TextInfoPrivacyCell infoCell;
    private TLRPC.TL_chatInviteExported invite;
    private InviteLinkBottomSheet inviteLinkBottomSheet;
    private boolean isChannel;
    private boolean isForcePublic;
    private boolean isPrivate;
    private boolean isSaveRestricted;
    private JoinToSendSettingsView joinContainer;
    private String lastCheckName;
    private boolean lastNameAvailable;
    private LinearLayout linearLayout;
    private LinearLayout linearLayoutTypeContainer;
    private LinearLayout linkContainer;
    private LoadingCell loadingAdminedCell;
    private boolean loadingAdminedChannels;
    private boolean loadingInvite;
    private ArrayList<String> loadingUsernames;
    private TextInfoPrivacyCell manageLinksInfoCell;
    private TextCell manageLinksTextView;
    private LinkActionView permanentLinkView;
    private LinearLayout privateContainer;
    private LinearLayout publicContainer;
    private RadioButtonCell radioButtonCell1;
    private RadioButtonCell radioButtonCell2;
    private LinearLayout saveContainer;
    private HeaderCell saveHeaderCell;
    private TextCheckCell saveRestrictCell;
    private TextInfoPrivacyCell saveRestrictInfoCell;
    private ShadowSectionCell sectionCell2;
    private TextSettingsCell textCell;
    private TextSettingsCell textCell2;
    private TextInfoPrivacyCell typeInfoCell;
    private EditTextBoldCursor usernameTextView;
    private ArrayList<TLRPC.TL_username> usernames;
    private UsernamesListView usernamesListView;
    HashMap<Long, TLRPC.User> usersMap;

    /* renamed from: org.telegram.ui.ChatEditTypeActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChatEditTypeActivity this$0;

        public AnonymousClass1(ChatEditTypeActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r2 == 1) goto L9;
            return;
        L9:
            if (ChatEditTypeActivity.m8306$$Nest$fgetdoneButtonDrawable(this.this$0) != null) goto L11;
        L13:
            ChatEditTypeActivity.m8331$$Nest$mprocessDone(this.this$0);
            return;
        L11:
            if (ChatEditTypeActivity.m8306$$Nest$fgetdoneButtonDrawable(this.this$0).getProgress() <= 0.0f) goto L13;
        }
    }

    /* renamed from: org.telegram.ui.ChatEditTypeActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends SectionsScrollView {
        final /* synthetic */ ChatEditTypeActivity this$0;

        public AnonymousClass2(ChatEditTypeActivity r1, Context r2, LinearLayout r3, Theme.ResourcesProvider r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            if (ChatEditTypeActivity.m8310$$Nest$fgetignoreScroll(this.this$0) == false) goto L5;
            return false;
        L5:
            if (super.onInterceptTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.widget.ScrollView, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (r2.getAction() == 0) goto L7;
            return super.onTouchEvent(r2);
        L7:
            if (ChatEditTypeActivity.m8310$$Nest$fgetignoreScroll(this.this$0) == false) goto L9;
            return false;
        L9:
            if (super.onTouchEvent(r2) == false) goto L14;
            return true;
        L14:
            return false;
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public boolean requestChildRectangleOnScreen(View r3, Rect r4, boolean r5) {
            r4.bottom += AndroidUtilities.dp(60.0f);
            return super.requestChildRectangleOnScreen(r3, r4, r5);
        }
    }

    /* renamed from: org.telegram.ui.ChatEditTypeActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends EditTextBoldCursor {
        final /* synthetic */ ChatEditTypeActivity this$0;

        public AnonymousClass3(ChatEditTypeActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            StringBuilder r0 = new StringBuilder();
            r0.append(getText());
            if (ChatEditTypeActivity.m8304$$Nest$fgetcheckTextView(this.this$0) != null) goto L5;
        L9:
            r3.setText(r0);
            return;
        L5:
            if (ChatEditTypeActivity.m8304$$Nest$fgetcheckTextView(this.this$0).getTextView() == null) goto L9;
            if (TextUtils.isEmpty(ChatEditTypeActivity.m8304$$Nest$fgetcheckTextView(this.this$0).getTextView().getText()) == true) goto L9;
            r0.append("\n");
            r0.append(ChatEditTypeActivity.m8304$$Nest$fgetcheckTextView(this.this$0).getTextView().getText());
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.ChatEditTypeActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements TextWatcher {
        final /* synthetic */ ChatEditTypeActivity this$0;

        public AnonymousClass4(ChatEditTypeActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            ChatEditTypeActivity.m8328$$Nest$mcheckDoneButton(this.this$0);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            if (ChatEditTypeActivity.m8311$$Nest$fgetignoreTextChanges(this.this$0) == false) goto L5;
            return;
        L5:
            String r12 = ChatEditTypeActivity.m8320$$Nest$fgetusernameTextView(this.this$0).getText().toString();
            if (ChatEditTypeActivity.m8307$$Nest$fgeteditableUsernameCell(this.this$0) == null) goto L8;
            ChatEditTypeActivity.m8307$$Nest$fgeteditableUsernameCell(this.this$0).updateUsername(r12);
        L8:
            ChatEditTypeActivity.m8329$$Nest$mcheckUserName(this.this$0, r12);
        }
    }

    /* renamed from: org.telegram.ui.ChatEditTypeActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements LinkActionView.Delegate {
        final /* synthetic */ ChatEditTypeActivity this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass5(ChatEditTypeActivity r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // org.telegram.ui.Components.LinkActionView.Delegate
        public void revokeLink() {
            ChatEditTypeActivity.m8330$$Nest$mgenerateLink(this.this$0, true);
        }

        @Override // org.telegram.ui.Components.LinkActionView.Delegate
        public void showUsersForPermanentLink() {
            ChatEditTypeActivity r0 = this.this$0;
            Context r2 = this.val$context;
            TLRPC.TL_chatInviteExported r3 = ChatEditTypeActivity.m8313$$Nest$fgetinvite(this.this$0);
            TLRPC.ChatFull r4 = ChatEditTypeActivity.m8312$$Nest$fgetinfo(this.this$0);
            ChatEditTypeActivity r6 = this.this$0;
            ChatEditTypeActivity.m8327$$Nest$fputinviteLinkBottomSheet(r0, new InviteLinkBottomSheet(r2, r3, r4, r6.usersMap, r6, ChatEditTypeActivity.m8303$$Nest$fgetchatId(r6), true, ChatObject.isChannel(ChatEditTypeActivity.m8305$$Nest$fgetcurrentChat(this.this$0))));
            ChatEditTypeActivity.m8314$$Nest$fgetinviteLinkBottomSheet(this.this$0).show();
        }
    }

    /* renamed from: org.telegram.ui.ChatEditTypeActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends TextInfoPrivacyCell {
        int prevHeight;
        final /* synthetic */ ChatEditTypeActivity this$0;
        ValueAnimator translateAnimator;

        /* renamed from: org.telegram.ui.ChatEditTypeActivity$6$1, reason: invalid class name */
        public class AnonymousClass1 extends ClickableSpan {
            final /* synthetic */ AnonymousClass6 this$1;
            final /* synthetic */ String val$username;

            public AnonymousClass1(AnonymousClass6 r1, String r2) {
                this.this$1 = r1;
                this.val$username = r2;
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View r3) {
                Browser.openUrl(this.this$1.getContext(), "https://fragment.com/username/" + this.val$username);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint r1) {
                super.updateDrawState(r1);
                r1.setUnderlineText(false);
            }
        }

        /* renamed from: $r8$lambda$5iypMHzfd2L8oljr3QXR-7PEQpM, reason: not valid java name */
        public static /* synthetic */ void m8332$r8$lambda$5iypMHzfd2L8oljr3QXR7PEQpM(ArrayList r3, float r4, ValueAnimator r5) {
            float r0 = 1.0f - ((Float) r5.getAnimatedValue()).floatValue();
            int r52 = 0;
        L4:
            if (r52 >= r3.size()) goto L9;
            View r1 = (View) r3.get(r52);
            if (r1 == null) goto L8;
            r1.setTranslationY(r4 * r0);
        L8:
            r52 = r52 + 1;
            goto L4
        }

        public AnonymousClass6(ChatEditTypeActivity r1, Context r2, int r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.prevHeight = -1;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (this.prevHeight != (-1)) goto L5;
        L19:
            this.prevHeight = getHeight();
            return;
        L5:
            if (ChatEditTypeActivity.m8317$$Nest$fgetlinearLayout(this.this$0) == null) goto L19;
            ArrayList r12 = new ArrayList();
            int r22 = 0;
            boolean r32 = false;
        L8:
            if (r22 >= ChatEditTypeActivity.m8317$$Nest$fgetlinearLayout(this.this$0).getChildCount()) goto L15;
            View r42 = ChatEditTypeActivity.m8317$$Nest$fgetlinearLayout(this.this$0).getChildAt(r22);
            if (r32 == false) goto L12;
            r12.add(r42);
        L14:
            r22 = r22 + 1;
            goto L8
        L12:
            if (r42 != this) goto L14;
            r32 = true;
            goto L14
        L15:
            float r23 = this.prevHeight - getHeight();
            ValueAnimator r33 = this.translateAnimator;
            if (r33 == null) goto L18;
            r33.cancel();
        L18:
            ValueAnimator r34 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.translateAnimator = r34;
            r34.addUpdateListener(new ChatEditTypeActivity$6$$ExternalSyntheticLambda0(r12, r23));
            this.translateAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.translateAnimator.setDuration(350);
            this.translateAnimator.start();
            goto L19
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r7v0, types: [org.telegram.ui.Cells.TextInfoPrivacyCell, org.telegram.ui.ChatEditTypeActivity$6] */
        /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.CharSequence] */
        /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.CharSequence] */
        /* JADX WARN: Type inference failed for: r8v3, types: [android.text.SpannableStringBuilder] */
        @Override // org.telegram.ui.Cells.TextInfoPrivacyCell
        public void setText(CharSequence r8) {
            if (r8 == 0) goto L16;
            r8 = AndroidUtilities.replaceTags(r8.toString());
            int r0 = r8.toString().indexOf(10);
            int r2 = 0;
            if (r0 < 0) goto L6;
            r8.replace(r0, r0 + 1, " ");
            r8.setSpan(new ForegroundColorSpan(this.this$0.getThemedColor(Theme.key_text_RedRegular)), 0, r0, 33);
        L6:
            TypefaceSpan[] r02 = (TypefaceSpan[]) r8.getSpans(0, r8.length(), TypefaceSpan.class);
            if (ChatEditTypeActivity.m8320$$Nest$fgetusernameTextView(this.this$0) != null) goto L9;
        L12:
            String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        L14:
            if (r2 >= r02.length) goto L16;
            r8.setSpan(new AnonymousClass1(this, r3), r8.getSpanStart(r02[r2]), r8.getSpanEnd(r02[r2]), 33);
            r8.removeSpan(r02[r2]);
            r2 = r2 + 1;
            goto L14
        L9:
            if (ChatEditTypeActivity.m8320$$Nest$fgetusernameTextView(this.this$0).getText() == null) goto L12;
            r3 = ChatEditTypeActivity.m8320$$Nest$fgetusernameTextView(this.this$0).getText().toString();
        L16:
            super.setText(r8);
        }
    }

    /* renamed from: org.telegram.ui.ChatEditTypeActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends JoinToSendSettingsView {
        final /* synthetic */ ChatEditTypeActivity this$0;
        final /* synthetic */ Context val$context;

        public static /* synthetic */ void $r8$lambda$X6oAxlnhaA2kMeyH8F5FJIiZ_Lc(AnonymousClass7 r0, boolean r1, AlertDialog r2, int r3) {
            r0.lambda$onJoinRequestToggle$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$zsgXhRN_aS372FJzUx82kV_uMf0(AnonymousClass7 r0, boolean r1, AlertDialog r2, int r3) {
            r0.lambda$onJoinRequestToggle$1(r1, r2, r3);
        }

        public AnonymousClass7(ChatEditTypeActivity r1, Context r2, TLRPC.Chat r3, Context r4) {
            this.this$0 = r1;
            this.val$context = r4;
            super(r2, r3);
        }

        private /* synthetic */ void lambda$onJoinRequestToggle$0(boolean r1, AlertDialog r2, int r3) {
            setJoinRequest(r1);
            ChatEditTypeActivity.m8322$$Nest$fputapplyToAllInviteLinks(this.this$0, true);
        }

        private /* synthetic */ void lambda$onJoinRequestToggle$1(boolean r1, AlertDialog r2, int r3) {
            setJoinRequest(r1);
            ChatEditTypeActivity.m8322$$Nest$fputapplyToAllInviteLinks(this.this$0, false);
        }

        @Override // org.telegram.ui.Components.JoinToSendSettingsView
        public boolean onJoinRequestToggle(boolean r5, Runnable r6) {
            if (ChatEditTypeActivity.m8316$$Nest$fgetisPrivate(this.this$0) == true) goto L5;
        L20:
            return true;
        L5:
            if (ChatEditTypeActivity.m8312$$Nest$fgetinfo(this.this$0) == null) goto L20;
            int r62 = ChatEditTypeActivity.m8312$$Nest$fgetinfo(this.this$0).invitesCount;
            if (r62 != 0) goto L11;
            return true;
        L11:
            if (ChatEditTypeActivity.m8315$$Nest$fgetisChannel(this.this$0) == false) goto L15;
            if (r5 == false) goto L14;
            String r0 = "ApproveNewMembersEnableForLinksChannel";
        L18:
            AlertDialog.Builder r1 = new AlertDialog.Builder(this.val$context, ChatEditTypeActivity.access$000(this.this$0));
            r1.setTitle(LocaleController.getString(R.string.ApproveNewMembersApplyToLinksTitle));
            r1.setMessage(AndroidUtilities.replaceTags(LocaleController.formatPluralString(r0, r62, new Object[0])));
            r1.setPositiveButton(LocaleController.getString(R.string.ApproveNewMembersApplyToLinksApply), new ChatEditTypeActivity$7$$ExternalSyntheticLambda0(this, r5));
            r1.setNegativeButton(LocaleController.getString(R.string.ApproveNewMembersApplyToLinksDontApply), new ChatEditTypeActivity$7$$ExternalSyntheticLambda1(this, r5));
            AlertDialog r52 = r1.create();
            this.this$0.showDialog(r52);
            return false;
        L14:
            r0 = "ApproveNewMembersDisableForLinksChannel";
            goto L18
        L15:
            if (r5 == false) goto L17;
            r0 = "ApproveNewMembersEnableForLinks";
            goto L18
        L17:
            r0 = "ApproveNewMembersDisableForLinks";
            goto L18
        }
    }

    public class UsernamesListView extends RecyclerListView {
        private final int VIEW_TYPE_HEADER;
        private final int VIEW_TYPE_HELP;
        private final int VIEW_TYPE_USERNAME;
        private Adapter adapter;
        private Paint backgroundPaint;
        private ItemTouchHelper itemTouchHelper;
        private LinearLayoutManager layoutManager;
        private boolean needReorder;
        final /* synthetic */ ChatEditTypeActivity this$0;

        /* renamed from: org.telegram.ui.ChatEditTypeActivity$UsernamesListView$1, reason: invalid class name */
        public class AnonymousClass1 implements RecyclerListView.OnItemClickListener {
            final /* synthetic */ UsernamesListView this$1;
            final /* synthetic */ ChatEditTypeActivity val$this$0;

            /* renamed from: $r8$lambda$5Kglk9O4MCxa61Fpz4TS2Vn-U5o, reason: not valid java name */
            public static /* synthetic */ void m8337$r8$lambda$5Kglk9O4MCxa61Fpz4TS2VnU5o(AnonymousClass1 r0, TLRPC.TL_username r1, boolean r2) {
                r0.lambda$onItemClick$1(r1, r2);
            }

            /* renamed from: $r8$lambda$62TsLsO0pCW1PFFawrVtCtlCt-4, reason: not valid java name */
            public static /* synthetic */ void m8338$r8$lambda$62TsLsO0pCW1PFFawrVtCtlCt4(AnonymousClass1 r0, TLRPC.TL_username r1, View r2, AlertDialog r3, int r4) {
                r0.lambda$onItemClick$4(r1, r2, r3, r4);
            }

            /* renamed from: $r8$lambda$9VT8gldfBMTa1Cq6_635-wxL5qY, reason: not valid java name */
            public static /* synthetic */ void m8339$r8$lambda$9VT8gldfBMTa1Cq6_635wxL5qY(AnonymousClass1 r0, TLRPC.TL_username r1, boolean r2, AlertDialog r3, int r4) {
                r0.lambda$onItemClick$0(r1, r2, r3, r4);
            }

            public static /* synthetic */ void $r8$lambda$DyGqaaWTTUPGJk2Qm2ICDyaNMEk(AnonymousClass1 r0, TLRPC.TL_channels_toggleUsername r1, TLRPC.TL_username r2, boolean r3, TLObject r4, TLRPC.TL_error r5) {
                r0.lambda$onItemClick$3(r1, r2, r3, r4, r5);
            }

            public static /* synthetic */ void $r8$lambda$dcx8AEkpyEzchsHzFE92ptyN7MU(AnonymousClass1 r0, TLRPC.TL_channels_toggleUsername r1, TLObject r2, TLRPC.TL_username r3, boolean r4, TLRPC.TL_error r5) {
                r0.lambda$onItemClick$2(r1, r2, r3, r4, r5);
            }

            /* renamed from: $r8$lambda$gbc-GApQjtInsX8AQI5Fstbmw3c, reason: not valid java name */
            public static /* synthetic */ void m8340$r8$lambda$gbcGApQjtInsX8AQI5Fstbmw3c(AlertDialog r0, int r1) {
                r0.dismiss();
            }

            public AnonymousClass1(UsernamesListView r1, ChatEditTypeActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            private /* synthetic */ void lambda$onItemClick$0(TLRPC.TL_username r1, boolean r2, AlertDialog r3, int r4) {
                this.this$1.toggleUsername(r1, r2, true);
                ChatEditTypeActivity.m8328$$Nest$mcheckDoneButton(this.this$1.this$0);
            }

            private /* synthetic */ void lambda$onItemClick$1(TLRPC.TL_username r4, boolean r5) {
                new AlertDialog.Builder(this.this$1.getContext(), UsernamesListView.access$100(this.this$1)).setTitle(LocaleController.getString(R.string.UsernameActivateErrorTitle)).setMessage(LocaleController.getString(R.string.UsernameActivateErrorMessage)).setPositiveButton(LocaleController.getString(R.string.OK), new ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda0(this, r4, r5)).show();
            }

            private /* synthetic */ void lambda$onItemClick$2(TLRPC.TL_channels_toggleUsername r2, TLObject r3, TLRPC.TL_username r4, boolean r5, TLRPC.TL_error r6) {
                ChatEditTypeActivity.m8319$$Nest$fgetloadingUsernames(this.this$1.this$0).remove(r2.username);
                if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L5;
                this.this$1.toggleUsername(r4, true ^ r5);
            L10:
                this.this$1.this$0.getMessagesController().updateUsernameActiveness(ChatEditTypeActivity.m8305$$Nest$fgetcurrentChat(this.this$1.this$0), r4.username, r4.active);
                return;
            L5:
                if (r6 != null) goto L7;
            L9:
                this.this$1.toggleUsername(r4, r5, true);
                ChatEditTypeActivity.m8328$$Nest$mcheckDoneButton(this.this$1.this$0);
                goto L10
            L7:
                if ("USERNAMES_ACTIVE_TOO_MUCH".equals(r6.text) == false) goto L9;
                AndroidUtilities.runOnUIThread(new ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda5(this, r4, r5));
                goto L10
            }

            private /* synthetic */ void lambda$onItemClick$3(TLRPC.TL_channels_toggleUsername r8, TLRPC.TL_username r9, boolean r10, TLObject r11, TLRPC.TL_error r12) {
                AndroidUtilities.runOnUIThread(new ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda4(this, r8, r11, r9, r10, r12));
            }

            private /* synthetic */ void lambda$onItemClick$4(TLRPC.TL_username r4, View r5, AlertDialog r6, int r7) {
                if (r4.editable == true) goto L5;
                TLRPC.TL_channels_toggleUsername r62 = new TLRPC.TL_channels_toggleUsername();
                TLRPC.TL_inputChannel r0 = new TLRPC.TL_inputChannel();
                r0.channel_id = ChatEditTypeActivity.m8305$$Nest$fgetcurrentChat(this.this$1.this$0).id;
                r0.access_hash = ChatEditTypeActivity.m8305$$Nest$fgetcurrentChat(this.this$1.this$0).access_hash;
                r62.channel = r0;
                r62.username = r4.username;
                boolean r02 = r4.active;
                r62.active = !r02;
                this.this$1.this$0.getConnectionsManager().sendRequest(r62, new ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda3(this, r62, r4, r02));
                ChatEditTypeActivity.m8319$$Nest$fgetloadingUsernames(this.this$1.this$0).add(r4.username);
                ((ChangeUsernameActivity.UsernameCell) r5).setLoading(true);
            L9:
                ChatEditTypeActivity.m8328$$Nest$mcheckDoneButton(this.this$1.this$0);
                return;
            L5:
                if (ChatEditTypeActivity.m8308$$Nest$fgeteditableUsernameWasActive(this.this$1.this$0) != null) goto L7;
                ChatEditTypeActivity.m8325$$Nest$fputeditableUsernameWasActive(this.this$1.this$0, Boolean.valueOf(r4.active));
            L7:
                ChatEditTypeActivity r52 = this.this$1.this$0;
                boolean r63 = !r4.active;
                r4.active = r63;
                ChatEditTypeActivity.m8324$$Nest$fputeditableUsernameUpdated(r52, Boolean.valueOf(r63));
                goto L9
            }

            @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
            public void onItemClick(View r4, int r5) {
                if ((r4 instanceof ChangeUsernameActivity.UsernameCell) == false) goto L31;
                TLRPC.TL_username r52 = ((ChangeUsernameActivity.UsernameCell) r4).currentUsername;
                if (r52 == null) goto L32;
                boolean r0 = r52.editable;
                UsernamesListView r1 = this.this$1;
                if (r0 == false) goto L14;
                ChatEditTypeActivity r42 = r1.this$0;
                View r53 = r42.fragmentView;
                if ((r53 instanceof ScrollView) == false) goto L12;
                ((ScrollView) r53).smoothScrollTo(0, ChatEditTypeActivity.m8318$$Nest$fgetlinkContainer(r42).getTop() - AndroidUtilities.dp(128.0f));
            L12:
                ChatEditTypeActivity.m8320$$Nest$fgetusernameTextView(this.this$1.this$0).requestFocus();
                AndroidUtilities.showKeyboard(ChatEditTypeActivity.m8320$$Nest$fgetusernameTextView(this.this$1.this$0));
                return;
            L14:
                AlertDialog.Builder r02 = new AlertDialog.Builder(r1.getContext(), this.this$1.this$0.getResourceProvider());
                if (r52.active == false) goto L18;
                int r12 = R.string.UsernameDeactivateLink;
            L19:
                AlertDialog.Builder r03 = r02.setTitle(LocaleController.getString(r12));
                if (r52.active == false) goto L23;
                int r13 = R.string.UsernameDeactivateLinkChannelMessage;
            L24:
                AlertDialog.Builder r04 = r03.setMessage(LocaleController.getString(r13));
                if (r52.active == false) goto L28;
                int r14 = R.string.Hide;
            L27:
                String r15 = LocaleController.getString(r14);
                r04.setPositiveButton(r15, new ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda1(this, r52, r4)).setNegativeButton(LocaleController.getString(R.string.Cancel), new ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda2()).show();
                return;
            L28:
                r14 = R.string.Show;
                goto L27
            L23:
                r13 = R.string.UsernameActivateLinkChannelMessage;
                goto L24
            L18:
                r12 = R.string.UsernameActivateLink;
                goto L19
            L32:
                return;
            }
        }

        public class Adapter extends RecyclerListView.SelectionAdapter {
            final /* synthetic */ UsernamesListView this$1;

            /* renamed from: org.telegram.ui.ChatEditTypeActivity$UsernamesListView$Adapter$1, reason: invalid class name */
            public class AnonymousClass1 extends ChangeUsernameActivity.UsernameCell {
                final /* synthetic */ Adapter this$2;

                public AnonymousClass1(Adapter r1, Context r2, Theme.ResourcesProvider r3) {
                    this.this$2 = r1;
                    super(r2, r3);
                }

                @Override // org.telegram.ui.ChangeUsernameActivity.UsernameCell
                public String getUsernameEditable() {
                    if (ChatEditTypeActivity.m8320$$Nest$fgetusernameTextView(this.this$2.this$1.this$0) != null) goto L7;
                    return null;
                L7:
                    return ChatEditTypeActivity.m8320$$Nest$fgetusernameTextView(this.this$2.this$1.this$0).getText().toString();
                }
            }

            private Adapter(UsernamesListView r1) {
                this.this$1 = r1;
            }

            public /* synthetic */ Adapter(UsernamesListView r1, ChatEditTypeActivityIA r2) {
                this(r1);
            }

            private void swapListElements(List<TLRPC.TL_username> r2, int r3, int r4) {
                TLRPC.TL_username r1 = r2.get(r3);
                r2.set(r3, r2.get(r4));
                r2.set(r4, r1);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).size() + 2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r1) {
                if (r1 != 0) goto L6;
                return 0;
            L6:
                if (r1 > ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).size()) goto L9;
                return 1;
            L9:
                return 2;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                if (r1.getItemViewType() != 1) goto L5;
                return true;
            L5:
                return false;
            }

            public void moveElement(int r4, int r5) {
                int r0 = r4 - 1;
                int r1 = r5 - 1;
                if (r0 < ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).size()) goto L5;
                return;
            L5:
                if (r1 >= ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).size()) goto L13;
                ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).add(r1, (TLRPC.TL_username) ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).remove(r0));
                notifyItemMoved(r4, r5);
                int r42 = 0;
            L9:
                if (r42 >= ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).size()) goto L14;
                r42 = r42 + 1;
                notifyItemChanged(r42);
                goto L9
            L14:
                return;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r6, int r7) {
                int r0 = r6.getItemViewType();
                if (r0 == 0) goto L24;
                boolean r1 = true;
                if (r0 != 1) goto L7;
                TLRPC.TL_username r02 = (TLRPC.TL_username) ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).get(r7 - 1);
                if (((ChangeUsernameActivity.UsernameCell) r6.itemView).editable == false) goto L14;
                ChatEditTypeActivity.m8323$$Nest$fputeditableUsernameCell(this.this$1.this$0, null);
            L14:
                ChangeUsernameActivity.UsernameCell r2 = (ChangeUsernameActivity.UsernameCell) r6.itemView;
                if (r7 < ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).size()) goto L18;
                r1 = false;
            L18:
                r2.set(r02, r1, false);
                if (r02 != null) goto L21;
                return;
            L21:
                if (r02.editable == false) goto L27;
                ChatEditTypeActivity.m8323$$Nest$fputeditableUsernameCell(this.this$1.this$0, (ChangeUsernameActivity.UsernameCell) r6.itemView);
                return;
            L27:
                return;
            L7:
                if (r0 != 2) goto L28;
                ((TextInfoPrivacyCell) r6.itemView).setText(LocaleController.getString(R.string.UsernamesChannelHelp));
                ((TextInfoPrivacyCell) r6.itemView).setBackground(Theme.getThemedDrawableByKey(this.this$1.getContext(), R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
                return;
            L28:
                return;
            L24:
                ((HeaderCell) r6.itemView).setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, UsernamesListView.access$500(this.this$1)));
                ((HeaderCell) r6.itemView).setText(LocaleController.getString(R.string.UsernamesChannelHeader));
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
                if (r4 == 0) goto L14;
                if (r4 == 1) goto L12;
                if (r4 == 2) goto L10;
                return null;
            L10:
                return new RecyclerListView.Holder(new TextInfoPrivacyCell(this.this$1.getContext(), 12, UsernamesListView.access$400(this.this$1)));
            L12:
                return new RecyclerListView.Holder(new AnonymousClass1(this, this.this$1.getContext(), UsernamesListView.access$300(this.this$1)));
            L14:
                return new RecyclerListView.Holder(new HeaderCell(this.this$1.getContext(), UsernamesListView.access$200(this.this$1)));
            }

            public void swapElements(int r6, int r7) {
                int r1 = r6 - 1;
                int r2 = r7 - 1;
                if (r1 < ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).size()) goto L5;
                return;
            L5:
                if (r2 >= ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).size()) goto L16;
                if (r6 == r7) goto L9;
                UsernamesListView.m8335$$Nest$fputneedReorder(this.this$1, true);
            L9:
                swapListElements(ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0), r1, r2);
                notifyItemMoved(r6, r7);
                int r12 = ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$1.this$0).size();
                if (r6 == r12) goto L12;
                if (r7 == r12) goto L12;
                return;
            L12:
                notifyItemChanged(r6, 3);
                notifyItemChanged(r7, 3);
                return;
            }
        }

        public class TouchHelperCallback extends ItemTouchHelper.Callback {
            final /* synthetic */ UsernamesListView this$1;

            public TouchHelperCallback(UsernamesListView r1) {
                this.this$1 = r1;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
                super.clearView(r1, r2);
                r2.itemView.setPressed(false);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public int getMovementFlags(RecyclerView r2, RecyclerView.ViewHolder r3) {
                if (r3.getItemViewType() != 1) goto L10;
                if (((ChangeUsernameActivity.UsernameCell) r3.itemView).active == false) goto L10;
                return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
            L10:
                return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
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
                if (r3.getItemViewType() != r4.getItemViewType()) goto L11;
                View r22 = r4.itemView;
                if ((r22 instanceof ChangeUsernameActivity.UsernameCell) == true) goto L7;
            L9:
                UsernamesListView.m8334$$Nest$fgetadapter(this.this$1).swapElements(r3.getAdapterPosition(), r4.getAdapterPosition());
                return true;
            L7:
                if (((ChangeUsernameActivity.UsernameCell) r22).active == true) goto L9;
                return false;
            L11:
                return false;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSelectedChanged(RecyclerView.ViewHolder r4, int r5) {
                UsernamesListView r0 = this.this$1;
                if (r5 != 0) goto L5;
                ChatEditTypeActivity.m8326$$Nest$fputignoreScroll(r0.this$0, false);
                UsernamesListView.m8336$$Nest$msendReorder(this.this$1);
            L6:
                super.onSelectedChanged(r4, r5);
                return;
            L5:
                ChatEditTypeActivity.m8326$$Nest$fputignoreScroll(r0.this$0, true);
                this.this$1.cancelClickRunnables(false);
                r4.itemView.setPressed(true);
                goto L6
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
            }
        }

        /* renamed from: $r8$lambda$oQEsyq3f16mseGL6oDuSzZA7-1Y, reason: not valid java name */
        public static /* synthetic */ void m8333$r8$lambda$oQEsyq3f16mseGL6oDuSzZA71Y(TLObject r0, TLRPC.TL_error r1) {
        }

        /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Adapter m8334$$Nest$fgetadapter(UsernamesListView r0) {
            return r0.adapter;
        }

        /* renamed from: -$$Nest$fputneedReorder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8335$$Nest$fputneedReorder(UsernamesListView r0, boolean r1) {
            r0.needReorder = r1;
        }

        /* renamed from: -$$Nest$msendReorder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8336$$Nest$msendReorder(UsernamesListView r0) {
            r0.sendReorder();
        }

        public UsernamesListView(ChatEditTypeActivity r4, Context r5) {
            this.this$0 = r4;
            super(r5);
            this.VIEW_TYPE_HEADER = 0;
            this.VIEW_TYPE_USERNAME = 1;
            this.VIEW_TYPE_HELP = 2;
            this.needReorder = false;
            this.backgroundPaint = new Paint(1);
            Adapter r0 = new Adapter(this, null);
            this.adapter = r0;
            setAdapter(r0);
            LinearLayoutManager r02 = new LinearLayoutManager(r5);
            this.layoutManager = r02;
            setLayoutManager(r02);
            setOnItemClickListener(new AnonymousClass1(this, r4));
            ItemTouchHelper r42 = new ItemTouchHelper(new TouchHelperCallback(this));
            this.itemTouchHelper = r42;
            r42.attachToRecyclerView(this);
        }

        public static /* synthetic */ Theme.ResourcesProvider access$100(UsernamesListView r0) {
            return r0.resourcesProvider;
        }

        public static /* synthetic */ Theme.ResourcesProvider access$200(UsernamesListView r0) {
            return r0.resourcesProvider;
        }

        public static /* synthetic */ Theme.ResourcesProvider access$300(UsernamesListView r0) {
            return r0.resourcesProvider;
        }

        public static /* synthetic */ Theme.ResourcesProvider access$400(UsernamesListView r0) {
            return r0.resourcesProvider;
        }

        public static /* synthetic */ Theme.ResourcesProvider access$500(UsernamesListView r0) {
            return r0.resourcesProvider;
        }

        private void sendReorder() {
            if (this.needReorder == true) goto L5;
            return;
        L5:
            if (ChatEditTypeActivity.m8305$$Nest$fgetcurrentChat(this.this$0) == null) goto L29;
            int r0 = 0;
            this.needReorder = false;
            TLRPC.TL_channels_reorderUsernames r1 = new TLRPC.TL_channels_reorderUsernames();
            TLRPC.TL_inputChannel r2 = new TLRPC.TL_inputChannel();
            r2.channel_id = ChatEditTypeActivity.m8305$$Nest$fgetcurrentChat(this.this$0).id;
            r2.access_hash = ChatEditTypeActivity.m8305$$Nest$fgetcurrentChat(this.this$0).access_hash;
            r1.channel = r2;
            ArrayList<String> r22 = new ArrayList();
            int r3 = 0;
        L9:
            if (r3 >= ChatEditTypeActivity.m8309$$Nest$fgeteditableUsernames(this.this$0).size()) goto L15;
            if (((TLRPC.TL_username) ChatEditTypeActivity.m8309$$Nest$fgeteditableUsernames(this.this$0).get(r3)).active == false) goto L13;
            r22.add(((TLRPC.TL_username) ChatEditTypeActivity.m8309$$Nest$fgeteditableUsernames(this.this$0).get(r3)).username);
        L13:
            r3 = r3 + 1;
        L15:
            if (r0 >= ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).size()) goto L20;
            if (((TLRPC.TL_username) ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).get(r0)).active == false) goto L19;
            r22.add(((TLRPC.TL_username) ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).get(r0)).username);
        L19:
            r0 = r0 + 1;
            goto L15
        L20:
            r1.order = r22;
            this.this$0.getConnectionsManager().sendRequest(r1, new ChatEditTypeActivity$UsernamesListView$$ExternalSyntheticLambda0());
            updateChat();
            return;
        }

        private void updateChat() {
            ChatEditTypeActivity.m8305$$Nest$fgetcurrentChat(this.this$0).usernames.clear();
            ChatEditTypeActivity.m8305$$Nest$fgetcurrentChat(this.this$0).usernames.addAll(ChatEditTypeActivity.m8309$$Nest$fgeteditableUsernames(this.this$0));
            ChatEditTypeActivity.m8305$$Nest$fgetcurrentChat(this.this$0).usernames.addAll(ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0));
            this.this$0.getMessagesController().putChat(ChatEditTypeActivity.m8305$$Nest$fgetcurrentChat(this.this$0), true);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r11) {
            int r0 = ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).size();
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MIN_VALUE;
            int r3 = 0;
        L4:
            if (r3 >= getChildCount()) goto L13;
            View r4 = getChildAt(r3);
            if (r4 == null) goto L12;
            int r5 = getChildAdapterPosition(r4);
            if (r5 < 1) goto L12;
            if (r5 > r0) goto L12;
            r1 = Math.min(r4.getTop(), r1);
            r2 = Math.max(r4.getBottom(), r2);
        L12:
            r3 = r3 + 1;
            goto L4
        L13:
            if (r1 >= r2) goto L15;
            this.backgroundPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
            Canvas r42 = r11;
            r42.drawRect(0.0f, r1, getWidth(), r2, this.backgroundPaint);
        L16:
            super.dispatchDraw(r42);
            return;
        L15:
            r42 = r11;
            goto L16
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(9999999, Integer.MIN_VALUE));
        }

        public void toggleUsername(int r6, boolean r7, boolean r8) {
            int r0 = r6 - 1;
            if (r0 >= 0) goto L5;
            return;
        L5:
            if (r0 >= ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).size()) goto L59;
            TLRPC.TL_username r02 = (TLRPC.TL_username) ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).get(r0);
            if (r02 == null) goto L60;
            int r2 = -1;
            int r3 = 0;
            if (r02.active == r7) goto L35;
            r02.active = r7;
            if (r7 == false) goto L25;
            int r72 = 0;
        L16:
            if (r72 >= ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).size()) goto L21;
            if (((TLRPC.TL_username) ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).get(r72)).active == false) goto L22;
            r72 = r72 + 1;
        L22:
            if (r72 < 0) goto L35;
            int r73 = Math.max(0, r72 - 1);
        L24:
            r2 = r73 + 1;
            goto L35
        L21:
            r72 = -1;
            goto L22
        L25:
            int r1 = -1;
            int r74 = 0;
        L27:
            if (r74 >= ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).size()) goto L32;
            if (((TLRPC.TL_username) ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).get(r74)).active == false) goto L31;
            r1 = r74;
        L31:
            r74 = r74 + 1;
            goto L27
        L32:
            if (r1 < 0) goto L35;
            r73 = Math.min(ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).size() - 1, r1 + 1);
        L35:
            if (r3 >= getChildCount()) goto L44;
            View r75 = getChildAt(r3);
            if (getChildAdapterPosition(r75) == r6) goto L38;
            r3 = r3 + 1;
            goto L35
        L38:
            if (r8 == false) goto L41;
            AndroidUtilities.shakeView(r75);
        L41:
            if ((r75 instanceof ChangeUsernameActivity.UsernameCell) == false) goto L44;
            ChangeUsernameActivity.UsernameCell r76 = (ChangeUsernameActivity.UsernameCell) r75;
            r76.setLoading(ChatEditTypeActivity.m8319$$Nest$fgetloadingUsernames(this.this$0).contains(r02.username));
            r76.update();
        L44:
            if (r2 < 0) goto L57;
            if (r6 == r2) goto L58;
            this.adapter.moveElement(r6, r2);
            return;
        L58:
            return;
        L57:
            return;
        L60:
            return;
        }

        public void toggleUsername(TLRPC.TL_username r2, boolean r3) {
            toggleUsername(r2, r3, false);
        }

        public void toggleUsername(TLRPC.TL_username r3, boolean r4, boolean r5) {
            int r0 = 0;
        L4:
            if (r0 >= ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).size()) goto L10;
            if (ChatEditTypeActivity.m8321$$Nest$fgetusernames(this.this$0).get(r0) == r3) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            toggleUsername(r0 + 1, r4, r5);
            return;
        }
    }

    /* renamed from: $r8$lambda$-_zuWuYiRCNN5YwJg90vedFPYeA, reason: not valid java name */
    public static /* synthetic */ void m8291$r8$lambda$_zuWuYiRCNN5YwJg90vedFPYeA(ChatEditTypeActivity r0, TLRPC.TL_error r1, TLObject r2, boolean r3) {
        r0.lambda$generateLink$26(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$0lvAWdfN62Cq2m3MWt2Qe-hwFuY, reason: not valid java name */
    public static /* synthetic */ void m8292$r8$lambda$0lvAWdfN62Cq2m3MWt2QehwFuY(ChatEditTypeActivity r0, String r1) {
        r0.lambda$checkUserName$25(r1);
    }

    public static /* synthetic */ void $r8$lambda$3eXDRwatXm1BKhbOZAjSnK_sRow(ChatEditTypeActivity r0, String r1, TLRPC.TL_error r2, TLObject r3, TLRPC.TL_channels_checkUsername r4) {
        r0.lambda$checkUserName$23(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$42mB6cLXd8tH5gGM1lINLFlltmA(ChatEditTypeActivity r0, long r1) {
        r0.lambda$tryUpdateJoinSettings$9(r1);
    }

    /* renamed from: $r8$lambda$6NjhqfYrZD5Uq-8oKwKvQBmnU2M, reason: not valid java name */
    public static /* synthetic */ void m8293$r8$lambda$6NjhqfYrZD5Uq8oKwKvQBmnU2M(ChatEditTypeActivity r0, ValueAnimator r1) {
        r0.lambda$updateDoneProgress$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$7BiWVIVJfjJwMGmi0tqs2kaDvMs(ChatEditTypeActivity r0, long r1) {
        r0.lambda$trySetRestrict$10(r1);
    }

    /* renamed from: $r8$lambda$A9f7sbczlD7VZb-0Fo8YNWEF7wQ, reason: not valid java name */
    public static /* synthetic */ void m8294$r8$lambda$A9f7sbczlD7VZb0Fo8YNWEF7wQ(ChatEditTypeActivity r0) {
        r0.lambda$new$6();
    }

    public static /* synthetic */ void $r8$lambda$AzXWM1aqWpzrtZ5rUdgBcJjbbD8(ChatEditTypeActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$Cwkv0Sdt7Csl7dkWQ1kFf7cGVpE(ChatEditTypeActivity r0, TLRPC.Chat r1, AlertDialog r2, int r3) {
        r0.lambda$loadAdminedChannels$19(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$FuoxIRG-FiFMPZ7zNxzciWBZZTU, reason: not valid java name */
    public static /* synthetic */ void m8295$r8$lambda$FuoxIRGFiFMPZ7zNxzciWBZZTU(ChatEditTypeActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadAdminedChannels$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GdCPtZhs6PgBOXW2eMu6yHfKk1A(ChatEditTypeActivity r0) {
        r0.lambda$trySetUsername$12();
    }

    public static /* synthetic */ void $r8$lambda$GkIT0UH4iRXtyxJ70w2BfRlqf88(ChatEditTypeActivity r0, TLRPC.TL_error r1) {
        r0.lambda$onFragmentCreate$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$J8sn_3O19jbBjJEWqV_b4EwzRqI(ChatEditTypeActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$onFragmentCreate$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Lam26LIOpU_qAQiQXdDyOLNe4Vw(ChatEditTypeActivity r0, String r1, TLRPC.TL_channels_checkUsername r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$checkUserName$24(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$OzKZULcG2n2bgWgunopZulX4hwg(ChatEditTypeActivity r0, TLObject r1) {
        r0.lambda$loadAdminedChannels$21(r1);
    }

    /* renamed from: $r8$lambda$SH8s-5zdJFBuZWn_TRGAqaX7ltE, reason: not valid java name */
    public static /* synthetic */ void m8296$r8$lambda$SH8s5zdJFBuZWn_TRGAqaX7ltE(ChatEditTypeActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadAdminedChannels$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UDYDdVwT8XNCEZtiPj6AQc7gvP0(ChatEditTypeActivity r0, View r1) {
        r0.lambda$loadAdminedChannels$20(r1);
    }

    /* renamed from: $r8$lambda$WAa-Z4m3nixCcVkJm2hHix-AtUM, reason: not valid java name */
    public static /* synthetic */ void m8297$r8$lambda$WAaZ4m3nixCcVkJm2hHixAtUM(ChatEditTypeActivity r0) {
        r0.lambda$trySetUsername$13();
    }

    /* renamed from: $r8$lambda$Z6xEXcNA2TfNXm5Eg2-ogI4027g, reason: not valid java name */
    public static /* synthetic */ void m8298$r8$lambda$Z6xEXcNA2TfNXm5Eg2ogI4027g(ChatEditTypeActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$tryDeactivateAllLinks$15(r1, r2);
    }

    /* renamed from: $r8$lambda$afTvnq2sfiCa-eHP-zIn-TnPi1s, reason: not valid java name */
    public static /* synthetic */ void m8299$r8$lambda$afTvnq2sfiCaeHPzInTnPi1s(ChatEditTypeActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$bS9ONNG384sZQYs2QsqLTONpkVU(ChatEditTypeActivity r0) {
        r0.lambda$getThemeDescriptions$28();
    }

    /* renamed from: $r8$lambda$dcHmER48tTv1G-yeKS2WVeD0PMc, reason: not valid java name */
    public static /* synthetic */ void m8300$r8$lambda$dcHmER48tTv1GyeKS2WVeD0PMc(ChatEditTypeActivity r0) {
        r0.lambda$loadAdminedChannels$17();
    }

    public static /* synthetic */ void $r8$lambda$i4HlN0RXhDBZt6uAgQIjzRv3_Bc(ChatEditTypeActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    /* renamed from: $r8$lambda$p-gjA4IxFx3NSgE4UewlhSr0WwI, reason: not valid java name */
    public static /* synthetic */ void m8301$r8$lambda$pgjA4IxFx3NSgE4UewlhSr0WwI(ChatEditTypeActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$pWFIkEYJajK_3fgXvLFu8_Nkn2Q(ChatEditTypeActivity r0, TLObject r1) {
        r0.lambda$tryDeactivateAllLinks$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$rjW3xwvxxn5rD6bo525LD16VAdc(ChatEditTypeActivity r0) {
        r0.processDone();
    }

    public static /* synthetic */ void $r8$lambda$seh81AbmKw8csda9JhXV4Vnlm2Q(ChatEditTypeActivity r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$generateLink$27(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$x7ii6MdGJmgGCOYSHZw0dLmo-yE, reason: not valid java name */
    public static /* synthetic */ void m8302$r8$lambda$x7ii6MdGJmgGCOYSHZw0dLmoyE(ChatEditTypeActivity r0) {
        r0.lambda$showPremiumIncreaseLimitDialog$8();
    }

    public static /* synthetic */ void $r8$lambda$zqKvEiNyJxtfuq8kPQKwkJnWyAo(ChatEditTypeActivity r0, long r1) {
        r0.lambda$trySetUsername$11(r1);
    }

    /* renamed from: -$$Nest$fgetchatId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m8303$$Nest$fgetchatId(ChatEditTypeActivity r2) {
        return r2.chatId;
    }

    /* renamed from: -$$Nest$fgetcheckTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextInfoPrivacyCell m8304$$Nest$fgetcheckTextView(ChatEditTypeActivity r0) {
        return r0.checkTextView;
    }

    /* renamed from: -$$Nest$fgetcurrentChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m8305$$Nest$fgetcurrentChat(ChatEditTypeActivity r0) {
        return r0.currentChat;
    }

    /* renamed from: -$$Nest$fgetdoneButtonDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CrossfadeDrawable m8306$$Nest$fgetdoneButtonDrawable(ChatEditTypeActivity r0) {
        return r0.doneButtonDrawable;
    }

    /* renamed from: -$$Nest$fgeteditableUsernameCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChangeUsernameActivity.UsernameCell m8307$$Nest$fgeteditableUsernameCell(ChatEditTypeActivity r0) {
        return r0.editableUsernameCell;
    }

    /* renamed from: -$$Nest$fgeteditableUsernameWasActive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Boolean m8308$$Nest$fgeteditableUsernameWasActive(ChatEditTypeActivity r0) {
        return r0.editableUsernameWasActive;
    }

    /* renamed from: -$$Nest$fgeteditableUsernames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m8309$$Nest$fgeteditableUsernames(ChatEditTypeActivity r0) {
        return r0.editableUsernames;
    }

    /* renamed from: -$$Nest$fgetignoreScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8310$$Nest$fgetignoreScroll(ChatEditTypeActivity r0) {
        return r0.ignoreScroll;
    }

    /* renamed from: -$$Nest$fgetignoreTextChanges, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8311$$Nest$fgetignoreTextChanges(ChatEditTypeActivity r0) {
        return r0.ignoreTextChanges;
    }

    /* renamed from: -$$Nest$fgetinfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.ChatFull m8312$$Nest$fgetinfo(ChatEditTypeActivity r0) {
        return r0.info;
    }

    /* renamed from: -$$Nest$fgetinvite, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_chatInviteExported m8313$$Nest$fgetinvite(ChatEditTypeActivity r0) {
        return r0.invite;
    }

    /* renamed from: -$$Nest$fgetinviteLinkBottomSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ InviteLinkBottomSheet m8314$$Nest$fgetinviteLinkBottomSheet(ChatEditTypeActivity r0) {
        return r0.inviteLinkBottomSheet;
    }

    /* renamed from: -$$Nest$fgetisChannel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8315$$Nest$fgetisChannel(ChatEditTypeActivity r0) {
        return r0.isChannel;
    }

    /* renamed from: -$$Nest$fgetisPrivate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8316$$Nest$fgetisPrivate(ChatEditTypeActivity r0) {
        return r0.isPrivate;
    }

    /* renamed from: -$$Nest$fgetlinearLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m8317$$Nest$fgetlinearLayout(ChatEditTypeActivity r0) {
        return r0.linearLayout;
    }

    /* renamed from: -$$Nest$fgetlinkContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m8318$$Nest$fgetlinkContainer(ChatEditTypeActivity r0) {
        return r0.linkContainer;
    }

    /* renamed from: -$$Nest$fgetloadingUsernames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m8319$$Nest$fgetloadingUsernames(ChatEditTypeActivity r0) {
        return r0.loadingUsernames;
    }

    /* renamed from: -$$Nest$fgetusernameTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m8320$$Nest$fgetusernameTextView(ChatEditTypeActivity r0) {
        return r0.usernameTextView;
    }

    /* renamed from: -$$Nest$fgetusernames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m8321$$Nest$fgetusernames(ChatEditTypeActivity r0) {
        return r0.usernames;
    }

    /* renamed from: -$$Nest$fputapplyToAllInviteLinks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8322$$Nest$fputapplyToAllInviteLinks(ChatEditTypeActivity r0, boolean r1) {
        r0.applyToAllInviteLinks = r1;
    }

    /* renamed from: -$$Nest$fputeditableUsernameCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8323$$Nest$fputeditableUsernameCell(ChatEditTypeActivity r0, ChangeUsernameActivity.UsernameCell r1) {
        r0.editableUsernameCell = r1;
    }

    /* renamed from: -$$Nest$fputeditableUsernameUpdated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8324$$Nest$fputeditableUsernameUpdated(ChatEditTypeActivity r0, Boolean r1) {
        r0.editableUsernameUpdated = r1;
    }

    /* renamed from: -$$Nest$fputeditableUsernameWasActive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8325$$Nest$fputeditableUsernameWasActive(ChatEditTypeActivity r0, Boolean r1) {
        r0.editableUsernameWasActive = r1;
    }

    /* renamed from: -$$Nest$fputignoreScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8326$$Nest$fputignoreScroll(ChatEditTypeActivity r0, boolean r1) {
        r0.ignoreScroll = r1;
    }

    /* renamed from: -$$Nest$fputinviteLinkBottomSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8327$$Nest$fputinviteLinkBottomSheet(ChatEditTypeActivity r0, InviteLinkBottomSheet r1) {
        r0.inviteLinkBottomSheet = r1;
    }

    /* renamed from: -$$Nest$mcheckDoneButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8328$$Nest$mcheckDoneButton(ChatEditTypeActivity r0) {
        r0.checkDoneButton();
    }

    /* renamed from: -$$Nest$mcheckUserName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8329$$Nest$mcheckUserName(ChatEditTypeActivity r0, String r1) {
        return r0.checkUserName(r1);
    }

    /* renamed from: -$$Nest$mgenerateLink, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8330$$Nest$mgenerateLink(ChatEditTypeActivity r0, boolean r1) {
        r0.generateLink(r1);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8331$$Nest$mprocessDone(ChatEditTypeActivity r0) {
        r0.processDone();
    }

    public ChatEditTypeActivity(long r2, boolean r4) {
        this.editableUsernames = new ArrayList();
        this.usernames = new ArrayList();
        this.loadingUsernames = new ArrayList();
        this.canCreatePublic = true;
        this.adminedChannelCells = new ArrayList();
        this.usersMap = new HashMap();
        this.enableDoneLoading = new ChatEditTypeActivity$$ExternalSyntheticLambda3(this);
        this.deactivatingLinks = false;
        this.activatingEditableLink = false;
        this.chatId = r2;
        this.isForcePublic = r4;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(ChatEditTypeActivity r0) {
        return r0.resourceProvider;
    }

    private void checkDoneButton() {
        if (this.isPrivate == false) goto L5;
    L11:
        this.doneButton.setEnabled(true);
        this.doneButton.setAlpha(1.0f);
        return;
    L5:
        if (this.usernameTextView.length() > 0) goto L11;
        if (hasActiveLink() == true) goto L11;
        this.doneButton.setEnabled(false);
        this.doneButton.setAlpha(0.5f);
    }

    private boolean checkUserName(String r7) {
        if (r7 != null) goto L5;
    L7:
        this.checkTextView.setVisibility(8);
    L8:
        Runnable r1 = this.checkRunnable;
        if (r1 == null) goto L13;
        AndroidUtilities.cancelRunOnUIThread(r1);
        this.checkRunnable = null;
        this.lastCheckName = null;
        if (this.checkReqId == 0) goto L13;
        getConnectionsManager().cancelRequest(this.checkReqId, true);
    L13:
        this.lastNameAvailable = false;
        if (r7 != null) goto L16;
    L50:
        if (r7 != null) goto L52;
    L60:
        boolean r72 = this.isChannel;
        TextInfoPrivacyCell r12 = this.checkTextView;
        if (r72 == false) goto L63;
        r12.setText(LocaleController.getString(R.string.LinkInvalidShort));
    L64:
        this.checkTextView.setTextColorByKey(Theme.key_text_RedRegular);
        return false;
    L63:
        r12.setText(LocaleController.getString(R.string.LinkInvalidShortMega));
        goto L64
    L52:
        if (r7.length() < 4) goto L60;
        int r13 = r7.length();
        TextInfoPrivacyCell r3 = this.checkTextView;
        if (r13 <= 32) goto L58;
        r3.setText(LocaleController.getString(R.string.LinkInvalidLong));
        this.checkTextView.setTextColorByKey(Theme.key_text_RedRegular);
        return false;
    L58:
        r3.setText(LocaleController.getString(R.string.LinkChecking));
        this.checkTextView.setTextColorByKey(Theme.key_windowBackgroundWhiteGrayText8);
        this.lastCheckName = r7;
        ChatEditTypeActivity$$ExternalSyntheticLambda15 r0 = new ChatEditTypeActivity$$ExternalSyntheticLambda15(this, r7);
        this.checkRunnable = r0;
        AndroidUtilities.runOnUIThread(r0, 300);
        return true;
    L16:
        if (r7.startsWith("_") == false) goto L18;
    L48:
        this.checkTextView.setText(LocaleController.getString(R.string.LinkInvalid));
        this.checkTextView.setTextColorByKey(Theme.key_text_RedRegular);
        return false;
    L18:
        if (r7.endsWith("_") == true) goto L48;
        int r14 = 0;
    L22:
        if (r14 >= r7.length()) goto L50;
        char r32 = r7.charAt(r14);
        if (r14 != 0) goto L33;
        if (r32 < '0') goto L33;
        if (r32 > '9') goto L33;
        boolean r73 = this.isChannel;
        TextInfoPrivacyCell r15 = this.checkTextView;
        if (r73 == false) goto L30;
        r15.setText(LocaleController.getString(R.string.LinkInvalidStartNumber));
    L31:
        this.checkTextView.setTextColorByKey(Theme.key_text_RedRegular);
        return false;
    L30:
        r15.setText(LocaleController.getString(R.string.LinkInvalidStartNumberMega));
    L33:
        if (r32 < '0') goto L36;
        if (r32 > '9') goto L36;
    L47:
        r14 = r14 + 1;
    L36:
        if (r32 < 'a') goto L40;
        if (r32 <= 'z') goto L47;
    L40:
        if (r32 < 'A') goto L44;
        if (r32 <= 'Z') goto L47;
    L44:
        if (r32 == '_') goto L47;
        this.checkTextView.setText(LocaleController.getString(R.string.LinkInvalid));
        this.checkTextView.setTextColorByKey(Theme.key_text_RedRegular);
        return false;
    L5:
        if (r7.length() <= 0) goto L7;
        this.checkTextView.setVisibility(0);
        goto L8
    }

    private void generateLink(boolean r5) {
        this.loadingInvite = true;
        TLRPC.TL_messages_exportChatInvite r1 = new TLRPC.TL_messages_exportChatInvite();
        r1.legacy_revoke_permanent = true;
        r1.peer = getMessagesController().getInputPeer(-this.chatId);
        int r52 = getConnectionsManager().sendRequest(r1, new ChatEditTypeActivity$$ExternalSyntheticLambda9(this, r5));
        getConnectionsManager().bindRequestToGuid(r52, this.classGuid);
    }

    private /* synthetic */ void lambda$checkUserName$23(String r3, TLRPC.TL_error r4, TLObject r5, TLRPC.TL_channels_checkUsername r6) {
        this.checkReqId = 0;
        String r1 = this.lastCheckName;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.equals(r3) == false) goto L34;
        if (r4 != null) goto L12;
        if ((r5 instanceof TLRPC.TL_boolTrue) == false) goto L12;
        this.checkTextView.setText(LocaleController.formatString("LinkAvailable", R.string.LinkAvailable, new Object[]{r3}));
        this.checkTextView.setTextColorByKey(Theme.key_windowBackgroundWhiteGreenText);
        this.lastNameAvailable = true;
        return;
    L12:
        if (r4 != null) goto L14;
    L18:
        if (r4 != null) goto L20;
    L26:
        if (r4 != null) goto L28;
    L30:
        this.checkTextView.setText(LocaleController.getString(R.string.LinkInUse));
        this.checkTextView.setTextColorByKey(Theme.key_text_RedRegular);
    L31:
        this.lastNameAvailable = false;
        return;
    L28:
        if ("CHANNELS_ADMIN_PUBLIC_TOO_MUCH".equals(r4.text) == false) goto L30;
        this.canCreatePublic = false;
        showPremiumIncreaseLimitDialog();
        goto L31
    L20:
        if ("USERNAME_PURCHASE_AVAILABLE".equals(r4.text) == false) goto L26;
        int r42 = r6.username.length();
        TextInfoPrivacyCell r52 = this.checkTextView;
        if (r42 != 4) goto L24;
        r52.setText(LocaleController.getString(R.string.UsernameInvalidShortPurchase));
    L25:
        this.checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText8));
        goto L31
    L24:
        r52.setText(LocaleController.getString(R.string.UsernameInUsePurchase));
        goto L25
    L14:
        if ("USERNAME_INVALID".equals(r4.text) == false) goto L18;
        if (r6.username.length() != 4) goto L18;
        this.checkTextView.setText(LocaleController.getString(R.string.UsernameInvalidShort));
        this.checkTextView.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        goto L31
    }

    private /* synthetic */ void lambda$checkUserName$24(String r7, TLRPC.TL_channels_checkUsername r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new ChatEditTypeActivity$$ExternalSyntheticLambda24(this, r7, r10, r9, r8));
    }

    private /* synthetic */ void lambda$checkUserName$25(String r5) {
        TLRPC.TL_channels_checkUsername r0 = new TLRPC.TL_channels_checkUsername();
        r0.username = r5;
        r0.channel = getMessagesController().getInputChannel(this.chatId);
        this.checkReqId = getConnectionsManager().sendRequest(r0, new ChatEditTypeActivity$$ExternalSyntheticLambda22(this, r5, r0), 2);
    }

    private /* synthetic */ void lambda$createView$2(View r1) {
        if (this.isPrivate == false) goto L5;
        return;
    L5:
        this.isPrivate = true;
        updatePrivatePublic();
    }

    private /* synthetic */ void lambda$createView$3(View r1) {
        if (this.isPrivate == true) goto L6;
        return;
    L6:
        if (this.canCreatePublic == true) goto L9;
        showPremiumIncreaseLimitDialog();
        return;
    L9:
        this.isPrivate = false;
        updatePrivatePublic();
    }

    private /* synthetic */ void lambda$createView$4(View r7) {
        ManageLinksActivity r0 = new ManageLinksActivity(this.chatId, 0, 0);
        r0.setInfo(this.info, this.invite);
        presentFragment(r0);
    }

    private /* synthetic */ void lambda$createView$5(View r2) {
        boolean r0 = !this.isSaveRestricted;
        this.isSaveRestricted = r0;
        ((TextCheckCell) r2).setChecked(r0);
    }

    private /* synthetic */ void lambda$generateLink$26(TLRPC.TL_error r3, TLObject r4, boolean r5) {
        String r0 = null;
        if (r3 != null) goto L12;
        TLRPC.TL_chatInviteExported r42 = (TLRPC.TL_chatInviteExported) r4;
        this.invite = r42;
        TLRPC.ChatFull r32 = this.info;
        if (r32 == null) goto L7;
        r32.exported_invite = r42;
    L7:
        if (r5 == false) goto L12;
        if (getParentActivity() == null) goto L20;
        AlertDialog.Builder r33 = new AlertDialog.Builder(getParentActivity());
        r33.setMessage(LocaleController.getString(R.string.RevokeAlertNewLink));
        r33.setTitle(LocaleController.getString(R.string.RevokeLink));
        r33.setNegativeButton(LocaleController.getString(R.string.OK), null);
        showDialog(r33.create());
        goto L12
    L20:
        return;
    L12:
        this.loadingInvite = false;
        LinkActionView r34 = this.permanentLinkView;
        if (r34 == null) goto L19;
        TLRPC.TL_chatInviteExported r43 = this.invite;
        if (r43 == null) goto L17;
        r0 = r43.link;
    L17:
        r34.setLink(r0);
        this.permanentLinkView.loadUsers(this.invite, this.chatId);
        return;
    }

    private /* synthetic */ void lambda$generateLink$27(boolean r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new ChatEditTypeActivity$$ExternalSyntheticLambda10(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$getThemeDescriptions$28() {
        LinearLayout r0 = this.adminnedChannelsLayout;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r1 = 0;
    L5:
        if (r1 >= r02) goto L10;
        View r2 = this.adminnedChannelsLayout.getChildAt(r1);
        if ((r2 instanceof AdminedChannelCell) == false) goto L9;
        ((AdminedChannelCell) r2).update();
    L9:
        r1 = r1 + 1;
    L10:
        this.permanentLinkView.updateColors();
        InviteLinkBottomSheet r4 = this.inviteLinkBottomSheet;
        if (r4 == null) goto L17;
        r4.updateColors();
        return;
    }

    private /* synthetic */ void lambda$loadAdminedChannels$17() {
        this.canCreatePublic = true;
        if (this.usernameTextView.length() <= 0) goto L5;
        checkUserName(this.usernameTextView.getText().toString());
    L5:
        updatePrivatePublic();
    }

    private /* synthetic */ void lambda$loadAdminedChannels$18(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        AndroidUtilities.runOnUIThread(new ChatEditTypeActivity$$ExternalSyntheticLambda1(this));
        return;
    }

    private /* synthetic */ void lambda$loadAdminedChannels$19(TLRPC.Chat r1, AlertDialog r2, int r3) {
        TLRPC.TL_channels_updateUsername r22 = new TLRPC.TL_channels_updateUsername();
        r22.channel = MessagesController.getInputChannel(r1);
        r22.username = _UrlKt.FRAGMENT_ENCODE_SET;
        getConnectionsManager().sendRequest(r22, new ChatEditTypeActivity$$ExternalSyntheticLambda0(this), 64);
    }

    private /* synthetic */ void lambda$loadAdminedChannels$20(View r6) {
        TLRPC.Chat r62 = ((AdminedChannelCell) r6.getParent()).getCurrentChannel();
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.AppName));
        if (this.isChannel == false) goto L5;
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("RevokeLinkAlertChannel", R.string.RevokeLinkAlertChannel, new Object[]{getMessagesController().linkPrefix + "/" + ChatObject.getPublicUsername(r62), r62.title})));
    L6:
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.getString(R.string.RevokeButton), new ChatEditTypeActivity$$ExternalSyntheticLambda27(this, r62));
        showDialog(r0.create());
        return;
    L5:
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("RevokeLinkAlert", R.string.RevokeLinkAlert, new Object[]{getMessagesController().linkPrefix + "/" + ChatObject.getPublicUsername(r62), r62.title})));
        goto L6
    }

    private /* synthetic */ void lambda$loadAdminedChannels$21(TLObject r7) {
        this.loadingAdminedChannels = false;
        if (r7 != null) goto L5;
        return;
    L5:
        if (getParentActivity() == null) goto L26;
        int r1 = 0;
    L9:
        if (r1 >= this.adminedChannelCells.size()) goto L11;
        this.linearLayout.removeView(this.adminedChannelCells.get(r1));
        r1 = r1 + 1;
        goto L9
    L11:
        this.adminedChannelCells.clear();
        TLRPC.TL_messages_chats r72 = (TLRPC.TL_messages_chats) r7;
        int r12 = 0;
    L13:
        if (r12 >= r72.chats.size()) goto L19;
        AdminedChannelCell r2 = new AdminedChannelCell(getParentActivity(), new ChatEditTypeActivity$$ExternalSyntheticLambda25(this), false, 0);
        TLRPC.Chat r3 = r72.chats.get(r12);
        boolean r5 = true;
        if (r12 == (r72.chats.size() - 1)) goto L18;
        r5 = false;
    L18:
        r2.setChannel(r3, r5);
        this.adminedChannelCells.add(r2);
        this.adminnedChannelsLayout.addView(r2, LayoutHelper.createLinear(-1, 72));
        r12 = r12 + 1;
        goto L13
    L19:
        updatePrivatePublic();
        return;
    }

    private /* synthetic */ void lambda$loadAdminedChannels$22(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChatEditTypeActivity$$ExternalSyntheticLambda23(this, r1));
    }

    private /* synthetic */ void lambda$new$6() {
        updateDoneProgress(true);
    }

    private /* synthetic */ void lambda$onFragmentCreate$0(TLRPC.TL_error r2) {
        if (r2 != null) goto L4;
    L7:
        boolean r22 = true;
    L8:
        this.canCreatePublic = r22;
        if (r22 == false) goto L11;
        return;
    L11:
        if (getUserConfig().isPremium() == false) goto L15;
        loadAdminedChannels();
        return;
    L15:
        return;
    L4:
        if (r2.text.equals("CHANNELS_ADMIN_PUBLIC_TOO_MUCH") == false) goto L7;
        r22 = false;
        goto L8
    }

    private /* synthetic */ void lambda$onFragmentCreate$1(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChatEditTypeActivity$$ExternalSyntheticLambda13(this, r2));
    }

    private /* synthetic */ void lambda$showPremiumIncreaseLimitDialog$8() {
        this.canCreatePublic = true;
        updatePrivatePublic();
    }

    private /* synthetic */ void lambda$tryDeactivateAllLinks$14(TLObject r4) {
        if ((r4 instanceof TLRPC.TL_boolTrue) == false) goto L15;
        int r42 = 0;
    L6:
        if (r42 >= this.currentChat.usernames.size()) goto L15;
        TLRPC.TL_username r1 = this.currentChat.usernames.get(r42);
        if (r1 == null) goto L14;
        if (r1.active == false) goto L14;
        if (r1.editable == true) goto L14;
        r1.active = false;
    L14:
        r42 = r42 + 1;
    L15:
        this.deactivatingLinks = false;
        AndroidUtilities.runOnUIThread(new ChatEditTypeActivity$$ExternalSyntheticLambda28(this));
    }

    private /* synthetic */ void lambda$tryDeactivateAllLinks$15(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChatEditTypeActivity$$ExternalSyntheticLambda26(this, r1));
    }

    private /* synthetic */ void lambda$trySetRestrict$10(long r4) {
        if (r4 == 0) goto L6;
        this.chatId = r4;
        this.currentChat = getMessagesController().getChat(Long.valueOf(r4));
        MessagesController r42 = getMessagesController();
        long r0 = -this.chatId;
        TLRPC.Chat r5 = this.currentChat;
        boolean r2 = this.isSaveRestricted;
        r5.noforwards = r2;
        r42.toggleChatNoForwards(r0, r2);
        processDone();
        return;
    }

    private /* synthetic */ void lambda$trySetUsername$11(long r3) {
        if (r3 == 0) goto L6;
        this.chatId = r3;
        this.currentChat = getMessagesController().getChat(Long.valueOf(r3));
        processDone();
        return;
    }

    private /* synthetic */ void lambda$trySetUsername$12() {
        this.currentChat = getMessagesController().getChat(Long.valueOf(this.chatId));
        processDone();
    }

    private /* synthetic */ void lambda$trySetUsername$13() {
        updateDoneProgress(false);
    }

    private /* synthetic */ void lambda$tryUpdateJoinSettings$9(long r3) {
        if (r3 == 0) goto L6;
        this.chatId = r3;
        this.currentChat = getMessagesController().getChat(Long.valueOf(r3));
        processDone();
        return;
    }

    private /* synthetic */ void lambda$updateDoneProgress$7(ValueAnimator r2) {
        this.doneButtonDrawable.setProgress(((Float) r2.getAnimatedValue()).floatValue());
        this.doneButtonDrawable.invalidateSelf();
    }

    private void loadAdminedChannels() {
        if (this.loadingAdminedChannels == false) goto L5;
        return;
    L5:
        if (this.adminnedChannelsLayout == null) goto L10;
        this.loadingAdminedChannels = true;
        updatePrivatePublic();
        TLRPC.TL_channels_getAdminedPublicChannels r0 = new TLRPC.TL_channels_getAdminedPublicChannels();
        getConnectionsManager().sendRequest(r0, new ChatEditTypeActivity$$ExternalSyntheticLambda14(this));
        return;
    }

    private void processDone() {
        AndroidUtilities.runOnUIThread(this.enableDoneLoading, 200);
        if (trySetUsername() == true) goto L5;
        return;
    L5:
        if (trySetRestrict() == true) goto L7;
        return;
    L7:
        if (tryUpdateJoinSettings() == false) goto L12;
        finishFragment();
        return;
    }

    private void showPremiumIncreaseLimitDialog() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        LimitReachedBottomSheet r1 = new LimitReachedBottomSheet(this, getParentActivity(), 2, this.currentAccount, null);
        r1.parentIsChannel = this.isChannel;
        r1.onSuccessRunnable = new ChatEditTypeActivity$$ExternalSyntheticLambda12(this);
        showDialog(r1);
    }

    private boolean tryDeactivateAllLinks() {
        if (this.isPrivate == false) goto L28;
        ArrayList<TLRPC.TL_username> r0 = this.currentChat.usernames;
        if (r0 == null) goto L28;
        if (r0.isEmpty() == true) goto L28;
        if (this.deactivatingLinks == false) goto L12;
        return false;
    L12:
        this.deactivatingLinks = true;
        int r02 = 0;
        boolean r3 = false;
    L14:
        if (r02 >= this.currentChat.usernames.size()) goto L23;
        TLRPC.TL_username r4 = this.currentChat.usernames.get(r02);
        if (r4 == null) goto L22;
        if (r4.active == false) goto L22;
        if (r4.editable == true) goto L22;
        r3 = true;
    L22:
        r02 = r02 + 1;
        goto L14
    L23:
        if (r3 == false) goto L25;
        TLRPC.TL_channels_deactivateAllUsernames r03 = new TLRPC.TL_channels_deactivateAllUsernames();
        r03.channel = MessagesController.getInputChannel(this.currentChat);
        getConnectionsManager().sendRequest(r03, new ChatEditTypeActivity$$ExternalSyntheticLambda21(this));
    L27:
        return !r3;
    L25:
        this.deactivatingLinks = false;
    L28:
        return true;
    }

    private boolean trySetRestrict() {
        TLRPC.Chat r0 = this.currentChat;
        if (r0.noforwards != this.isSaveRestricted) goto L5;
    L9:
        return true;
    L5:
        if (ChatObject.isChannel(r0) == true) goto L8;
        updateDoneProgress(true);
        getMessagesController().convertToMegaGroup(getParentActivity(), this.chatId, this, new ChatEditTypeActivity$$ExternalSyntheticLambda20(this));
        return false;
    L8:
        MessagesController r10 = getMessagesController();
        long r02 = -this.chatId;
        TLRPC.Chat r2 = this.currentChat;
        boolean r4 = this.isSaveRestricted;
        r2.noforwards = r4;
        r10.toggleChatNoForwards(r02, r4);
        goto L9
    }

    private boolean trySetUsername() {
        if (getParentActivity() != null) goto L5;
        return false;
    L5:
        String r0 = ChatObject.getPublicUsername(this.currentChat, true);
        if (this.isPrivate == true) goto L19;
        if (r0 == null) goto L9;
    L10:
        if (r0 == null) goto L19;
        if (r0.equalsIgnoreCase(this.usernameTextView.getText().toString()) == true) goto L19;
    L14:
        if (this.usernameTextView.length() == 0) goto L19;
        if (this.lastNameAvailable == true) goto L19;
        this.checkTextView.performHapticFeedback(3, 2);
        AndroidUtilities.shakeView(this.checkTextView);
        updateDoneProgress(false);
        return false;
    L9:
        if (this.usernameTextView.length() != 0) goto L14;
    L19:
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 != null) goto L24;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L24:
        if (this.isPrivate == false) goto L26;
    L25:
        String r8 = r3;
        if (r0.equals(r8) == true) goto L36;
        if (ChatObject.isChannel(this.currentChat) == true) goto L33;
        getMessagesController().convertToMegaGroup(getParentActivity(), this.chatId, this, new ChatEditTypeActivity$$ExternalSyntheticLambda17(this));
        return false;
    L33:
        getMessagesController().updateChannelUserName(this, this.chatId, r8, new ChatEditTypeActivity$$ExternalSyntheticLambda18(this), new ChatEditTypeActivity$$ExternalSyntheticLambda19(this));
        return false;
    L36:
        if (tryDeactivateAllLinks() == true) goto L38;
        return false;
    L38:
        return true;
    L26:
        r3 = this.usernameTextView.getText().toString();
        goto L25
    }

    private boolean tryUpdateJoinSettings() {
        if (this.joinContainer != null) goto L6;
        return true;
    L6:
        if (getParentActivity() != null) goto L9;
        return false;
    L9:
        if (this.isChannel == true) goto L21;
        if (ChatObject.isChannel(this.currentChat) == true) goto L21;
        JoinToSendSettingsView r0 = this.joinContainer;
        if (r0.isJoinToSend == false) goto L15;
    L18:
        getMessagesController().convertToMegaGroup(getParentActivity(), this.chatId, this, new ChatEditTypeActivity$$ExternalSyntheticLambda16(this));
        return false;
    L15:
        if (r0.isJoinRequest == true) goto L18;
    L21:
        if (this.currentChat.join_to_send == this.joinContainer.isJoinToSend) goto L24;
        MessagesController r8 = getMessagesController();
        long r9 = this.chatId;
        TLRPC.Chat r14 = this.currentChat;
        boolean r11 = this.joinContainer.isJoinToSend;
        r14.join_to_send = r11;
        r8.toggleChatJoinToSend(r9, r11, null, null);
    L24:
        if (this.currentChat.join_request == this.joinContainer.isJoinRequest) goto L26;
    L27:
        MessagesController r2 = getMessagesController();
        long r3 = this.chatId;
        TLRPC.Chat r142 = this.currentChat;
        boolean r02 = this.joinContainer.isJoinRequest;
        r142.join_request = r02;
        r2.toggleChatJoinRequest(r3, 0, r02, this.applyToAllInviteLinks, false, null, null);
    L28:
        return true;
    L26:
        if (this.applyToAllInviteLinks == false) goto L28;
        goto L27
    }

    private void updateDoneProgress(boolean r7) {
        if (r7 == true) goto L5;
        AndroidUtilities.cancelRunOnUIThread(this.enableDoneLoading);
    L5:
        if (this.doneButtonDrawable == null) goto L18;
        ValueAnimator r0 = this.doneButtonDrawableAnimator;
        if (r0 == null) goto L9;
        r0.cancel();
    L9:
        float r02 = this.doneButtonDrawable.getProgress();
        float r1 = 0.0f;
        if (r7 == false) goto L12;
        float r3 = 1.0f;
    L13:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r3});
        this.doneButtonDrawableAnimator = r03;
        r03.addUpdateListener(new ChatEditTypeActivity$$ExternalSyntheticLambda11(this));
        ValueAnimator r04 = this.doneButtonDrawableAnimator;
        float r32 = this.doneButtonDrawable.getProgress();
        if (r7 == false) goto L16;
        r1 = 1.0f;
    L16:
        r04.setDuration((long) (Math.abs(r32 - r1) * 200.0f));
        this.doneButtonDrawableAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.doneButtonDrawableAnimator.start();
        return;
    L12:
        r3 = 0.0f;
        goto L13
    }

    private void updatePrivatePublic() {
        if (this.sectionCell2 != null) goto L5;
        return;
    L5:
        int r1 = 8;
        if (this.isPrivate == false) goto L8;
    L15:
        TextInfoPrivacyCell r0 = this.typeInfoCell;
        int r3 = Theme.key_windowBackgroundWhiteGrayText4;
        r0.setTag(Integer.valueOf(r3));
        this.typeInfoCell.setTextColor(Theme.getColor(r3));
        boolean r02 = this.isForcePublic;
        ShadowSectionCell r32 = this.sectionCell2;
        if (r02 == false) goto L18;
        r32.setVisibility(8);
    L19:
        this.adminedInfoCell.setVisibility(8);
        this.adminnedChannelsLayout.setVisibility(8);
        this.linkContainer.setVisibility(0);
        this.loadingAdminedCell.setVisibility(8);
        boolean r03 = this.isChannel;
        TextInfoPrivacyCell r33 = this.typeInfoCell;
        if (r03 == false) goto L33;
        if (this.isPrivate == false) goto L25;
        int r04 = R.string.ChannelPrivateLinkHelp;
    L26:
        r33.setText(LocaleController.getString(r04));
        HeaderCell r05 = this.headerCell;
        if (this.isPrivate == false) goto L30;
        int r34 = R.string.ChannelInviteLinkTitle;
    L31:
        r05.setText(LocaleController.getString(r34));
    L43:
        LinearLayout r06 = this.publicContainer;
        if (this.isPrivate == false) goto L46;
        int r35 = 8;
    L47:
        r06.setVisibility(r35);
        LinearLayout r07 = this.privateContainer;
        if (this.isPrivate == false) goto L50;
        int r36 = 0;
    L51:
        r07.setVisibility(r36);
        this.saveContainer.setVisibility(0);
        this.manageLinksTextView.setVisibility(0);
        this.manageLinksInfoCell.setVisibility(0);
        LinearLayout r08 = this.linkContainer;
        if (this.isPrivate == false) goto L54;
        int r37 = 0;
    L55:
        r08.setPadding(0, 0, 0, r37);
        LinkActionView r09 = this.permanentLinkView;
        TLRPC.TL_chatInviteExported r38 = this.invite;
        if (r38 == null) goto L58;
        String r39 = r38.link;
    L59:
        r09.setLink(r39);
        this.permanentLinkView.loadUsers(this.invite, this.chatId);
        TextInfoPrivacyCell r010 = this.checkTextView;
        if (this.isPrivate == false) goto L62;
    L64:
        int r310 = 8;
    L65:
        r010.setVisibility(r310);
        TLRPC.ChatFull r011 = getMessagesController().getChatFull(this.chatId);
        TLRPC.Chat r311 = getMessagesController().getChat(Long.valueOf(this.chatId));
        TextInfoPrivacyCell r4 = this.manageLinksInfoCell;
        if (r011 != null) goto L68;
    L72:
        int r012 = R.string.ManageLinksInfoHelp;
    L73:
        r4.setText(LocaleController.getString(r012));
    L74:
        boolean r42 = true;
        this.radioButtonCell1.setChecked(!this.isPrivate, true);
        this.radioButtonCell2.setChecked(this.isPrivate, true);
        this.usernameTextView.clearFocus();
        JoinToSendSettingsView r013 = this.joinContainer;
        if (r013 != null) goto L77;
    L92:
        UsernamesListView r014 = this.usernamesListView;
        if (r014 != null) goto L95;
    L101:
        checkDoneButton();
        return;
    L95:
        if (this.isPrivate == false) goto L97;
    L100:
        r014.setVisibility(r1);
        goto L101
    L97:
        if (this.usernames.isEmpty() == true) goto L100;
        r1 = 0;
        goto L100
    L77:
        if (this.isChannel == true) goto L79;
    L82:
        int r312 = 0;
    L83:
        r013.setVisibility(r312);
        JoinToSendSettingsView r015 = this.joinContainer;
        TLRPC.ChatFull r313 = this.info;
        if (r313 != null) goto L86;
    L90:
        r42 = false;
    L91:
        r015.showJoinToSend(r42);
        goto L92
    L86:
        if (r313.linked_chat_id == 0) goto L90;
        if (this.isChannel == true) goto L90;
    L79:
        if (this.isPrivate == true) goto L82;
        r312 = 8;
        goto L83
    L68:
        if (r011.paid_media_allowed == false) goto L72;
        if (ChatObject.isChannelAndNotMegaGroup(r311) == false) goto L72;
        r012 = R.string.ManageLinksInfoHelpPaid;
        goto L73
    L62:
        if (r010.length() == 0) goto L64;
        r310 = 0;
        goto L65
    L58:
        r39 = null;
        goto L59
    L54:
        r37 = AndroidUtilities.dp(7.0f);
        goto L55
    L50:
        r36 = 8;
        goto L51
    L46:
        r35 = 0;
        goto L47
    L30:
        r34 = R.string.ChannelLinkTitle;
        goto L31
    L25:
        r04 = R.string.ChannelUsernameHelp;
        goto L26
    L33:
        if (this.isPrivate == false) goto L36;
        int r016 = R.string.MegaPrivateLinkHelp;
    L37:
        r33.setText(LocaleController.getString(r016));
        HeaderCell r017 = this.headerCell;
        if (this.isPrivate == false) goto L41;
        int r314 = R.string.ChannelInviteLinkTitle;
    L42:
        r017.setText(LocaleController.getString(r314));
        goto L43
    L41:
        r314 = R.string.ChannelLinkTitle;
        goto L42
    L36:
        r016 = R.string.MegaUsernameHelp;
        goto L37
    L18:
        r32.setVisibility(0);
        goto L19
    L8:
        if (this.canCreatePublic == true) goto L15;
        if (getUserConfig().isPremium() == false) goto L15;
        this.typeInfoCell.setText(LocaleController.getString(R.string.ChangePublicLimitReached));
        TextInfoPrivacyCell r018 = this.typeInfoCell;
        int r315 = Theme.key_text_RedRegular;
        r018.setTag(Integer.valueOf(r315));
        this.typeInfoCell.setTextColor(Theme.getColor(r315));
        this.linkContainer.setVisibility(8);
        this.checkTextView.setVisibility(8);
        this.sectionCell2.setVisibility(8);
        this.adminedInfoCell.setVisibility(0);
        boolean r019 = this.loadingAdminedChannels;
        LoadingCell r316 = this.loadingAdminedCell;
        if (r019 == false) goto L14;
        r316.setVisibility(0);
        this.adminnedChannelsLayout.setVisibility(8);
        goto L74
    L14:
        r316.setVisibility(8);
        this.adminnedChannelsLayout.setVisibility(0);
        goto L74
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r23) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenu r0 = this.actionBar.createMenu();
        Drawable r3 = r23.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r5 = Theme.key_actionBarDefaultIcon;
        r3.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r5), PorterDuff.Mode.MULTIPLY));
        CrossfadeDrawable r4 = new CrossfadeDrawable(r3, new CircularProgressDrawable(Theme.getColor(r5)));
        this.doneButtonDrawable = r4;
        this.doneButton = r0.addItemWithWidth(1, r4, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        this.linearLayout = new SectionsScrollView.SectionsLinearLayout(r23);
        AnonymousClass2 r02 = new AnonymousClass2(this, r23, this.linearLayout, this.resourceProvider, false);
        this.fragmentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        SectionsScrollView r03 = (SectionsScrollView) this.fragmentView;
        r03.setFillViewport(true);
        r03.addView(this.linearLayout, new FrameLayout.LayoutParams(-1, -2));
        this.actionBar.setAdaptiveBackground(r03);
        this.linearLayout.setOrientation(1);
        if (this.isForcePublic == false) goto L5;
        this.actionBar.setTitle(LocaleController.getString(R.string.TypeLocationGroup));
    L9:
        LinearLayout r04 = new LinearLayout(r23);
        this.linearLayoutTypeContainer = r04;
        r04.setOrientation(1);
        this.linearLayout.addView(this.linearLayoutTypeContainer, LayoutHelper.createLinear(-1, -2));
        HeaderCell r05 = new HeaderCell(r23, 23);
        this.headerCell2 = r05;
        r05.setHeight(46);
        boolean r06 = this.isChannel;
        HeaderCell r32 = this.headerCell2;
        if (r06 == false) goto L12;
        r32.setText(LocaleController.getString(R.string.ChannelTypeHeader));
    L13:
        this.linearLayoutTypeContainer.addView(this.headerCell2);
        RadioButtonCell r07 = new RadioButtonCell(r23);
        this.radioButtonCell2 = r07;
        if (this.isChannel == false) goto L16;
        r07.setTextAndValue(LocaleController.getString(R.string.ChannelPrivate), LocaleController.getString(R.string.ChannelPrivateInfo), false, this.isPrivate);
    L17:
        this.linearLayoutTypeContainer.addView(this.radioButtonCell2, LayoutHelper.createLinear(-1, -2));
        this.radioButtonCell2.setOnClickListener(new ChatEditTypeActivity$$ExternalSyntheticLambda4(this));
        RadioButtonCell r08 = new RadioButtonCell(r23);
        this.radioButtonCell1 = r08;
        if (this.isChannel == false) goto L20;
        r08.setTextAndValue(LocaleController.getString(R.string.ChannelPublic), LocaleController.getString(R.string.ChannelPublicInfo), false, !this.isPrivate);
    L21:
        this.linearLayoutTypeContainer.addView(this.radioButtonCell1, LayoutHelper.createLinear(-1, -2));
        this.radioButtonCell1.setOnClickListener(new ChatEditTypeActivity$$ExternalSyntheticLambda5(this));
        ShadowSectionCell r09 = new ShadowSectionCell(r23);
        this.sectionCell2 = r09;
        this.linearLayout.addView(r09, LayoutHelper.createLinear(-1, -2));
        if (this.isForcePublic == false) goto L24;
        this.radioButtonCell2.setVisibility(8);
        this.radioButtonCell1.setVisibility(8);
        this.sectionCell2.setVisibility(8);
        this.headerCell2.setVisibility(8);
    L24:
        LinearLayout r010 = new LinearLayout(r23);
        this.linkContainer = r010;
        r010.setOrientation(1);
        this.linearLayout.addView(this.linkContainer, LayoutHelper.createLinear(-1, -2));
        HeaderCell r011 = new HeaderCell(r23, 23);
        this.headerCell = r011;
        this.linkContainer.addView(r011);
        LinearLayout r012 = new LinearLayout(r23);
        this.publicContainer = r012;
        r012.setOrientation(0);
        this.linkContainer.addView(this.publicContainer, LayoutHelper.createLinear(-1, 36, 23.0f, 7.0f, 23.0f, 0.0f));
        EditTextBoldCursor r013 = new EditTextBoldCursor(r23);
        this.editText = r013;
        r013.setText(getMessagesController().linkPrefix + "/");
        this.editText.setTextSize(1, 18.0f);
        EditTextBoldCursor r014 = this.editText;
        int r42 = Theme.key_windowBackgroundWhiteHintText;
        r014.setHintTextColor(Theme.getColor(r42));
        EditTextBoldCursor r015 = this.editText;
        int r52 = Theme.key_windowBackgroundWhiteBlackText;
        r015.setTextColor(Theme.getColor(r52));
        this.editText.setMaxLines(1);
        this.editText.setLines(1);
        this.editText.setEnabled(false);
        this.editText.setBackground(null);
        this.editText.setPadding(0, 0, 0, 0);
        this.editText.setSingleLine(true);
        this.editText.setInputType(163840);
        this.editText.setImeOptions(6);
        this.publicContainer.addView(this.editText, LayoutHelper.createLinear(-2, 36));
        AnonymousClass3 r016 = new AnonymousClass3(this, r23);
        this.usernameTextView = r016;
        r016.setTextSize(1, 18.0f);
        this.usernameTextView.setHintTextColor(Theme.getColor(r42));
        this.usernameTextView.setTextColor(Theme.getColor(r52));
        this.usernameTextView.setMaxLines(1);
        this.usernameTextView.setLines(1);
        this.usernameTextView.setBackground(null);
        this.usernameTextView.setPadding(0, 0, 0, 0);
        this.usernameTextView.setSingleLine(true);
        this.usernameTextView.setInputType(163872);
        this.usernameTextView.setImeOptions(6);
        this.usernameTextView.setHint(LocaleController.getString(R.string.ChannelUsernamePlaceholder));
        this.usernameTextView.setCursorColor(Theme.getColor(r52));
        this.usernameTextView.setCursorSize(AndroidUtilities.dp(20.0f));
        this.usernameTextView.setCursorWidth(1.5f);
        this.publicContainer.addView(this.usernameTextView, LayoutHelper.createLinear(-1, 36));
        this.usernameTextView.addTextChangedListener(new AnonymousClass4(this));
        LinearLayout r017 = new LinearLayout(r23);
        this.privateContainer = r017;
        r017.setOrientation(1);
        this.linkContainer.addView(this.privateContainer, LayoutHelper.createLinear(-1, -2));
        LinkActionView r018 = new LinkActionView(r23, this, null, this.chatId, true, ChatObject.isChannel(this.currentChat));
        this.permanentLinkView = r018;
        r018.setDelegate(new AnonymousClass5(this, r23));
        this.permanentLinkView.setUsers(0, null, false);
        this.privateContainer.addView(this.permanentLinkView);
        AnonymousClass6 r019 = new AnonymousClass6(this, r23, 12, this.resourceProvider);
        this.checkTextView = r019;
        r019.setBottomPadding(6);
        this.linearLayout.addView(this.checkTextView, LayoutHelper.createLinear(-2, -2));
        TextInfoPrivacyCell r020 = new TextInfoPrivacyCell(r23, 12, this.resourceProvider);
        this.typeInfoCell = r020;
        r020.setImportantForAccessibility(1);
        this.linearLayout.addView(this.typeInfoCell, LayoutHelper.createLinear(-1, -2));
        LoadingCell r021 = new LoadingCell(r23);
        this.loadingAdminedCell = r021;
        this.linearLayout.addView(r021, LayoutHelper.createLinear(-1, -2));
        LinearLayout r022 = new LinearLayout(r23);
        this.adminnedChannelsLayout = r022;
        r022.setOrientation(1);
        this.linearLayout.addView(this.adminnedChannelsLayout, LayoutHelper.createLinear(-1, -2));
        ShadowSectionCell r023 = new ShadowSectionCell(r23);
        this.adminedInfoCell = r023;
        this.linearLayout.addView(r023, LayoutHelper.createLinear(-1, -2));
        LinearLayout r024 = this.linearLayout;
        UsernamesListView r33 = new UsernamesListView(this, r23);
        this.usernamesListView = r33;
        r024.addView(r33, LayoutHelper.createLinear(-1, -2));
        UsernamesListView r025 = this.usernamesListView;
        if (this.isPrivate == false) goto L27;
    L30:
        int r14 = 8;
    L31:
        r025.setVisibility(r14);
        TextCell r026 = new TextCell(r23);
        this.manageLinksTextView = r026;
        r026.setTextAndIcon(LocaleController.getString(R.string.ManageInviteLinks), R.drawable.msg_link2, false);
        this.manageLinksTextView.setOnClickListener(new ChatEditTypeActivity$$ExternalSyntheticLambda6(this));
        this.linearLayout.addView(this.manageLinksTextView, LayoutHelper.createLinear(-1, -2));
        TextInfoPrivacyCell r027 = new TextInfoPrivacyCell(r23, 12, this.resourceProvider);
        this.manageLinksInfoCell = r027;
        this.linearLayout.addView(r027, LayoutHelper.createLinear(-1, -2));
        AnonymousClass7 r028 = new AnonymousClass7(this, r23, this.currentChat, r23);
        this.joinContainer = r028;
        TLRPC.ChatFull r34 = this.info;
        if (r34 != null) goto L34;
    L38:
        boolean r35 = false;
    L39:
        r028.showJoinToSend(r35);
        this.joinContainer.setFullInfo(this, this.info);
        this.linearLayout.addView(this.joinContainer);
        LinearLayout r029 = new LinearLayout(r23);
        this.saveContainer = r029;
        r029.setOrientation(1);
        this.linearLayout.addView(this.saveContainer);
        HeaderCell r030 = new HeaderCell(r23, 23);
        this.saveHeaderCell = r030;
        r030.setHeight(46);
        this.saveHeaderCell.setText(LocaleController.getString(R.string.SavingContentTitle));
        this.saveContainer.addView(this.saveHeaderCell, LayoutHelper.createLinear(-1, -2));
        TextCheckCell r031 = new TextCheckCell(r23);
        this.saveRestrictCell = r031;
        r031.setTextAndCheck(LocaleController.getString(R.string.RestrictSavingContent), this.isSaveRestricted, false);
        this.saveRestrictCell.setOnClickListener(new ChatEditTypeActivity$$ExternalSyntheticLambda7(this));
        this.saveContainer.addView(this.saveRestrictCell, LayoutHelper.createLinear(-1, -2));
        this.saveRestrictInfoCell = new TextInfoPrivacyCell(r23, 12, this.resourceProvider);
        if (this.isChannel == true) goto L42;
    L44:
        this.saveRestrictInfoCell.setText(LocaleController.getString(R.string.RestrictSavingContentInfoGroup));
    L45:
        this.saveContainer.addView(this.saveRestrictInfoCell, LayoutHelper.createLinear(-1, -2));
        String r032 = ChatObject.getPublicUsername(this.currentChat, true);
        if (this.isPrivate == true) goto L49;
        if (r032 == null) goto L49;
        this.ignoreTextChanges = true;
        this.usernameTextView.setText(r032);
        this.usernameTextView.setSelection(r032.length());
        this.ignoreTextChanges = false;
    L49:
        updatePrivatePublic();
        return this.fragmentView;
    L42:
        if (ChatObject.isMegagroup(this.currentChat) == true) goto L44;
        this.saveRestrictInfoCell.setText(LocaleController.getString(R.string.RestrictSavingContentInfoChannel));
        goto L45
    L34:
        if (r34.linked_chat_id == 0) goto L38;
        if (this.isChannel == true) goto L38;
        r35 = true;
        goto L39
    L27:
        if (this.usernames.isEmpty() == true) goto L30;
        r14 = 0;
        goto L31
    L20:
        r08.setTextAndValue(LocaleController.getString(R.string.MegaPublic), LocaleController.getString(R.string.MegaPublicInfo), false, !this.isPrivate);
        goto L21
    L16:
        r07.setTextAndValue(LocaleController.getString(R.string.MegaPrivate), LocaleController.getString(R.string.MegaPrivateInfo), false, this.isPrivate);
        goto L17
    L12:
        r32.setText(LocaleController.getString(R.string.GroupTypeHeader));
        goto L13
    L5:
        boolean r033 = this.isChannel;
        ActionBar r36 = this.actionBar;
        if (r033 == false) goto L8;
        r36.setTitle(LocaleController.getString(R.string.ChannelSettingsTitle));
        goto L9
    L8:
        r36.setTitle(LocaleController.getString(R.string.GroupSettingsTitle));
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.chatInfoDidLoad) goto L9;
        TLRPC.ChatFull r32 = (TLRPC.ChatFull) r5[0];
        if (r32.id != this.chatId) goto L20;
        this.info = r32;
        this.invite = r32.exported_invite;
        updatePrivatePublic();
        return;
    L20:
        return;
    L9:
        if (r3 != NotificationCenter.dialogDeleted) goto L21;
        long r33 = ((Long) r5[0]).longValue();
        if ((-this.chatId) != r33) goto L22;
        INavigationLayout r34 = this.parentLayout;
        if (r34 != null) goto L15;
    L18:
        removeSelfFromStack();
        return;
    L15:
        if (r34.getLastFragment() != this) goto L18;
        finishFragment();
        return;
    L22:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ChatEditTypeActivity$$ExternalSyntheticLambda8 r8 = new ChatEditTypeActivity$$ExternalSyntheticLambda8(this);
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        int r24 = Theme.key_windowBackgroundGrayShadow;
        r1.add(new ThemeDescription(this.sectionCell2, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, r24));
        r1.add(new ThemeDescription(this.infoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r24));
        int r22 = Theme.key_windowBackgroundWhiteGrayText4;
        r1.add(new ThemeDescription(this.infoCell, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r22));
        TextSettingsCell r11 = this.textCell;
        int r12 = ThemeDescription.FLAG_SELECTOR;
        int r20 = Theme.key_listSelector;
        r1.add(new ThemeDescription(r11, r12, null, null, null, null, r20));
        int r34 = Theme.key_text_RedRegular;
        r1.add(new ThemeDescription(this.textCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r34));
        r1.add(new ThemeDescription(this.textCell2, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r20));
        int r17 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.textCell2, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.usernameTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r17));
        EditTextBoldCursor r4 = this.usernameTextView;
        int r28 = ThemeDescription.FLAG_HINTTEXTCOLOR;
        int r16 = Theme.key_windowBackgroundWhiteHintText;
        r1.add(new ThemeDescription(r4, r28, null, null, null, null, r16));
        LinearLayout r10 = this.linearLayoutTypeContainer;
        int r112 = ThemeDescription.FLAG_BACKGROUND;
        int r42 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r10, r112, null, null, null, null, r42));
        r1.add(new ThemeDescription(this.linkContainer, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r42));
        int r56 = Theme.key_windowBackgroundWhiteBlueHeader;
        r1.add(new ThemeDescription(this.headerCell, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, r56));
        r1.add(new ThemeDescription(this.headerCell2, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, r56));
        r1.add(new ThemeDescription(this.saveHeaderCell, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, r56));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.saveRestrictCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.saveRestrictCell, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.saveRestrictCell, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrack));
        r1.add(new ThemeDescription(this.saveRestrictCell, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackChecked));
        r1.add(new ThemeDescription(this.checkTextView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r34));
        r1.add(new ThemeDescription(this.checkTextView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText8));
        r1.add(new ThemeDescription(this.checkTextView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGreenText));
        r1.add(new ThemeDescription(this.typeInfoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r24));
        r1.add(new ThemeDescription(this.typeInfoCell, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.typeInfoCell, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r34));
        r1.add(new ThemeDescription(this.manageLinksInfoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r24));
        r1.add(new ThemeDescription(this.manageLinksInfoCell, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.manageLinksInfoCell, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r34));
        r1.add(new ThemeDescription(this.saveRestrictInfoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r24));
        r1.add(new ThemeDescription(this.saveRestrictInfoCell, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.saveRestrictInfoCell, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r34));
        r1.add(new ThemeDescription(this.adminedInfoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r24));
        r1.add(new ThemeDescription(this.adminnedChannelsLayout, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r42));
        r1.add(new ThemeDescription(this.loadingAdminedCell, 0, new Class[]{LoadingCell.class}, new String[]{"progressBar"}, null, null, null, Theme.key_progressCircle));
        r1.add(new ThemeDescription(this.radioButtonCell1, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r20));
        int r172 = Theme.key_radioBackground;
        r1.add(new ThemeDescription(this.radioButtonCell1, ThemeDescription.FLAG_CHECKBOX, new Class[]{RadioButtonCell.class}, new String[]{"radioButton"}, null, null, null, r172));
        int r173 = Theme.key_radioBackgroundChecked;
        r1.add(new ThemeDescription(this.radioButtonCell1, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{RadioButtonCell.class}, new String[]{"radioButton"}, null, null, null, r173));
        r1.add(new ThemeDescription(this.radioButtonCell1, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{RadioButtonCell.class}, new String[]{"textView"}, null, null, null, r17));
        int r18 = Theme.key_windowBackgroundWhiteGrayText2;
        r1.add(new ThemeDescription(this.radioButtonCell1, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{RadioButtonCell.class}, new String[]{"valueTextView"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.radioButtonCell2, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.radioButtonCell2, ThemeDescription.FLAG_CHECKBOX, new Class[]{RadioButtonCell.class}, new String[]{"radioButton"}, null, null, null, r172));
        r1.add(new ThemeDescription(this.radioButtonCell2, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{RadioButtonCell.class}, new String[]{"radioButton"}, null, null, null, r173));
        r1.add(new ThemeDescription(this.radioButtonCell2, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{RadioButtonCell.class}, new String[]{"textView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.radioButtonCell2, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{RadioButtonCell.class}, new String[]{"valueTextView"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.adminnedChannelsLayout, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{AdminedChannelCell.class}, new String[]{"nameTextView"}, null, null, null, r17));
        int r182 = Theme.key_windowBackgroundWhiteGrayText;
        r1.add(new ThemeDescription(this.adminnedChannelsLayout, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{AdminedChannelCell.class}, new String[]{"statusTextView"}, null, null, null, r182));
        r1.add(new ThemeDescription(this.adminnedChannelsLayout, ThemeDescription.FLAG_LINKCOLOR, new Class[]{AdminedChannelCell.class}, new String[]{"statusTextView"}, null, null, null, Theme.key_windowBackgroundWhiteLinkText));
        r1.add(new ThemeDescription(this.adminnedChannelsLayout, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{AdminedChannelCell.class}, new String[]{"deleteButton"}, null, null, null, r182));
        r1.add(new ThemeDescription(null, 0, null, null, Theme.avatarDrawables, r8, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.manageLinksTextView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.manageLinksTextView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.manageLinksTextView, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        return r1;
    }

    public boolean hasActiveLink() {
        if (this.usernames != null) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= this.usernames.size()) goto L17;
        TLRPC.TL_username r2 = this.usernames.get(r0);
        if (r2 == null) goto L16;
        if (r2.active == false) goto L16;
        if (TextUtils.isEmpty(r2.username) == true) goto L16;
        return true;
    L16:
        r0 = r0 + 1;
        goto L7
    L17:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyVisible() {
        super.onBecomeFullyVisible();
        if (this.isForcePublic == false) goto L8;
        EditTextBoldCursor r0 = this.usernameTextView;
        if (r0 == null) goto L9;
        r0.requestFocus();
        AndroidUtilities.showKeyboard(this.usernameTextView);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.chatId));
        this.currentChat = r0;
        int r1 = 0;
        if (r0 != null) goto L12;
        TLRPC.Chat r02 = getMessagesStorage().getChatSync(this.chatId);
        this.currentChat = r02;
        if (r02 == null) goto L10;
        getMessagesController().putChat(this.currentChat, true);
        if (this.info != null) goto L12;
        TLRPC.ChatFull r03 = getMessagesStorage().loadChatInfo(this.chatId, ChatObject.isChannel(this.currentChat), new CountDownLatch(1), false, false);
        this.info = r03;
        if (r03 != null) goto L12;
    L10:
        return false;
    L12:
        if (this.isForcePublic == false) goto L14;
    L16:
        boolean r04 = false;
    L17:
        this.isPrivate = r04;
        if (ChatObject.isChannel(this.currentChat) == true) goto L20;
    L22:
        boolean r05 = false;
    L23:
        this.isChannel = r05;
        TLRPC.Chat r06 = this.currentChat;
        this.isSaveRestricted = r06.noforwards;
        if (this.isForcePublic == false) goto L28;
        if (ChatObject.isPublic(r06) == true) goto L28;
    L31:
        TLRPC.TL_channels_checkUsername r07 = new TLRPC.TL_channels_checkUsername();
        r07.username = "1";
        r07.channel = new TLRPC.TL_inputChannelEmpty();
        getConnectionsManager().sendRequest(r07, new ChatEditTypeActivity$$ExternalSyntheticLambda2(this));
    L33:
        if (this.isPrivate == false) goto L38;
        if (this.info == null) goto L38;
        getMessagesController().loadFullChat(this.chatId, this.classGuid, true);
    L38:
        if (this.currentChat == null) goto L52;
        this.editableUsernames.clear();
        this.usernames.clear();
        int r08 = 0;
    L41:
        if (r08 >= this.currentChat.usernames.size()) goto L47;
        if (this.currentChat.usernames.get(r08).active == false) goto L45;
        this.usernames.add(this.currentChat.usernames.get(r08));
    L45:
        r08 = r08 + 1;
    L47:
        if (r1 >= this.currentChat.usernames.size()) goto L52;
        if (this.currentChat.usernames.get(r1).active == true) goto L51;
        this.usernames.add(this.currentChat.usernames.get(r1));
    L51:
        r1 = r1 + 1;
    L52:
        getNotificationCenter().addObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.dialogDeleted);
        return super.onFragmentCreate();
    L28:
        if (this.isPrivate == false) goto L33;
        if (this.currentChat.creator == false) goto L33;
    L20:
        if (this.currentChat.megagroup == true) goto L22;
        r05 = true;
        goto L23
    L14:
        if (ChatObject.isPublic(this.currentChat) == true) goto L16;
        r04 = true;
        goto L17
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.dialogDeleted);
        AndroidUtilities.removeAdjustResize(getParentActivity(), this.classGuid);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r2, int r3, int r4, int r5) {
        LinearLayout r1 = this.linearLayout;
        if (r1 == null) goto L6;
        r1.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f) + r5);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        TextSettingsCell r0 = this.textCell2;
        if (r0 == null) goto L10;
        TLRPC.ChatFull r1 = this.info;
        if (r1 == null) goto L10;
        if (r1.stickerset == null) goto L9;
        r0.setTextAndValue(LocaleController.getString(R.string.GroupStickers), this.info.stickerset.title, false);
        goto L10
    L9:
        r0.setText(LocaleController.getString(R.string.GroupStickers), false);
    L10:
        TLRPC.ChatFull r02 = this.info;
        if (r02 == null) goto L18;
        TLRPC.TL_chatInviteExported r03 = r02.exported_invite;
        this.invite = r03;
        LinkActionView r12 = this.permanentLinkView;
        if (r03 != null) goto L15;
        String r04 = null;
    L16:
        r12.setLink(r04);
        this.permanentLinkView.loadUsers(this.invite, this.chatId);
        return;
    L15:
        r04 = r03.link;
        goto L16
    }

    public void setInfo(TLRPC.ChatFull r1) {
        this.info = r1;
        if (r1 == null) goto L10;
        TLRPC.TL_chatInviteExported r12 = r1.exported_invite;
        if (r12 == null) goto L8;
        this.invite = r12;
        return;
    L8:
        generateLink(false);
        return;
    }
}
