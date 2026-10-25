package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.DialogRadioCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.PollEditTextCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCheckCell2;
import org.telegram.ui.Cells.TextDetailCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Cells.UserCell2;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.GuardBotReplaceSheet;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TagEditCell;

/* loaded from: classes3.dex */
public class ChatRightsEditActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private int addAdminsRow;
    private FrameLayout addBotButton;
    private FrameLayout addBotButtonContainer;
    private int addBotButtonRow;
    private AnimatedTextView addBotButtonText;
    private int addUsersRow;
    private TLRPC.TL_chatAdminRights adminRights;
    private int anonymousRow;
    private boolean asAdmin;
    private ValueAnimator asAdminAnimator;
    private float asAdminT;
    private int banUsersRow;
    private TLRPC.TL_chatBannedRights bannedRights;
    public boolean banning;
    private String botHash;
    private boolean canEdit;
    private int cantEditInfoRow;
    private int changeInfoRow;
    private int channelDeleteMessagesRow;
    private int channelDeleteStoriesRow;
    private int channelEditMessagesRow;
    private int channelEditStoriesRow;
    private boolean channelMessagesExpanded;
    private int channelMessagesRow;
    private int channelPostMessagesRow;
    private int channelPostStoriesRow;
    private boolean channelStoriesExpanded;
    private int channelStoriesRow;
    private long chatId;
    private TLRPC.ChatFull chatInfo;
    private boolean closingKeyboardAfterFinish;
    private String currentBannedRights;
    private TLRPC.Chat currentChat;
    private String currentRank;
    private int currentType;
    private TLRPC.User currentUser;
    private boolean currentUserIsBotGuard;
    private TLRPC.TL_chatBannedRights defaultBannedRights;
    private ChatRightsEditActivityDelegate delegate;
    private int deleteMessagesRow;
    private CrossfadeDrawable doneDrawable;
    private ValueAnimator doneDrawableAnimator;
    private int editMesagesRow;
    private int editTagsRow;
    private int embedLinksRow;
    private long guardBotIdToSet;
    private int guardBotInfoRow;
    private int guardBotRow;
    private boolean hasGuardBotToSet;
    private boolean initialAsAdmin;
    private boolean initialIsSet;
    private String initialRank;
    private boolean isAddingNew;
    private boolean isChannel;
    private boolean isCommunity;
    private boolean isForum;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerListView listView;
    private ListAdapter listViewAdapter;
    private boolean loading;
    private int manageDirectRow;
    private int manageLinkedPeersRow;
    private int manageRow;
    private int manageTopicsRow;
    private int manageWelcomeRow;
    private TLRPC.TL_chatAdminRights myAdminRights;
    private int permissionsEndRow;
    private int permissionsStartRow;
    private int pinMessagesRow;
    private int postMessagesRow;
    private PollEditTextCell rankEditTextCell;
    private int rankHeaderRow;
    private int rankInfoRow;
    private int rankRow;
    private int removeAdminRow;
    private int removeAdminShadowRow;
    private int rightsShadowRow;
    private int rowCount;
    private int sendFilesRow;
    private boolean sendMediaExpanded;
    private int sendMediaRow;
    private int sendMessagesRow;
    private int sendMusicRow;
    private int sendPhotosRow;
    private int sendPollsRow;
    private int sendReactionsRow;
    private int sendRoundRow;
    private int sendStickersRow;
    private int sendVideosRow;
    private int sendVoiceRow;
    private int startVoiceChatRow;
    private int transferOwnerRow;
    private int transferOwnerShadowRow;
    private int untilDateRow;
    private int untilSectionRow;

    /* renamed from: org.telegram.ui.ChatRightsEditActivity$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChatRightsEditActivity this$0;

        public AnonymousClass1(ChatRightsEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            ChatRightsEditActivity.m8492$$Nest$monDonePressed(this.this$0);
            return;
        L12:
            return;
        L5:
            if (ChatRightsEditActivity.m8487$$Nest$mcheckDiscard(this.this$0, true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatRightsEditActivity$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends FrameLayout {
        private int previousHeight;
        final /* synthetic */ ChatRightsEditActivity this$0;

        public AnonymousClass2(ChatRightsEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.previousHeight = -1;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r52 = r5 - r3;
            int r12 = this.previousHeight;
            if (r12 != (-1)) goto L5;
        L7:
            this.previousHeight = r52;
            return;
        L5:
            if (Math.abs(r12 - r52) <= AndroidUtilities.dp(20.0f)) goto L7;
            ChatRightsEditActivity.m8447$$Nest$fgetlistView(this.this$0).smoothScrollToPosition(ChatRightsEditActivity.m8463$$Nest$fgetrowCount(this.this$0) - 1);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.ChatRightsEditActivity$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ ChatRightsEditActivity this$0;

        public AnonymousClass3(ChatRightsEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            if (ChatRightsEditActivity.m8448$$Nest$fgetloading(this.this$0) == false) goto L7;
            return false;
        L7:
            return super.onInterceptTouchEvent(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (ChatRightsEditActivity.m8448$$Nest$fgetloading(this.this$0) == false) goto L7;
            return false;
        L7:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.ChatRightsEditActivity$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends LinearLayoutManager {
        final /* synthetic */ ChatRightsEditActivity this$0;

        public AnonymousClass4(ChatRightsEditActivity r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public int getExtraLayoutSpace(RecyclerView.State r1) {
            return 5000;
        }
    }

    /* renamed from: org.telegram.ui.ChatRightsEditActivity$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatRightsEditActivity this$0;

        public AnonymousClass5(ChatRightsEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatRightsEditActivity$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatRightsEditActivity this$0;

        public AnonymousClass6(ChatRightsEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            CrossfadeDrawable r22 = ChatRightsEditActivity.m8437$$Nest$fgetdoneDrawable(this.this$0);
            if (ChatRightsEditActivity.m8448$$Nest$fgetloading(this.this$0) == false) goto L5;
            float r0 = 1.0f;
        L6:
            r22.setProgress(r0);
            ChatRightsEditActivity.m8437$$Nest$fgetdoneDrawable(this.this$0).invalidateSelf();
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public interface ChatRightsEditActivityDelegate {
        void didChangeOwner(TLRPC.User r1);

        void didSetRights(int r1, TLRPC.TL_chatAdminRights r2, TLRPC.TL_chatBannedRights r3, String r4);
    }

    /* loaded from: classes6.dex */
    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private final int VIEW_TYPE_ADD_BOT_CELL;
        private final int VIEW_TYPE_EXPANDABLE_SWITCH;
        private final int VIEW_TYPE_HEADER_CELL;
        private final int VIEW_TYPE_INFO_CELL;
        private final int VIEW_TYPE_INNER_CHECK;
        private final int VIEW_TYPE_RANK_CELL;
        private final int VIEW_TYPE_SHADOW_CELL;
        private final int VIEW_TYPE_SWITCH_CELL;
        private final int VIEW_TYPE_TAG_CELL;
        private final int VIEW_TYPE_TRANSFER_CELL;
        private final int VIEW_TYPE_UNTIL_DATE_CELL;
        private final int VIEW_TYPE_USER_CELL;
        private boolean ignoreTextChange;
        private Context mContext;
        final /* synthetic */ ChatRightsEditActivity this$0;

        /* renamed from: org.telegram.ui.ChatRightsEditActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements TextWatcher {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1) {
                this.this$1 = r1;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r2) {
                if (ListAdapter.m8499$$Nest$fgetignoreTextChange(this.this$1) == true) goto L10;
                ChatRightsEditActivity.m8484$$Nest$fputcurrentRank(this.this$1.this$0, r2.toString());
                RecyclerView.ViewHolder r22 = ChatRightsEditActivity.m8447$$Nest$fgetlistView(this.this$1.this$0).findViewHolderForAdapterPosition(ChatRightsEditActivity.m8457$$Nest$fgetrankHeaderRow(this.this$1.this$0));
                if (r22 == null) goto L9;
                ChatRightsEditActivity.m8496$$Nest$msetTextLeft(this.this$1.this$0, r22.itemView);
                return;
            L9:
                return;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        public static /* synthetic */ void $r8$lambda$54e7amrMpAR8f6tmD2Lrg81_dtc(ListAdapter r0, TextCheckCell2 r1) {
            r0.lambda$onBindViewHolder$2(r1);
        }

        /* renamed from: $r8$lambda$RIABjoJtZz-1yaTNh13hjDgQ0z8, reason: not valid java name */
        public static /* synthetic */ void m8497$r8$lambda$RIABjoJtZz1yaTNh13hjDgQ0z8(ListAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$cMkr0qZpdTso6ID3vZUJoVkAFRM(ListAdapter r0, String r1) {
            r0.lambda$onBindViewHolder$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$i0VfgUjKn2J5w2gQuyhTSC_Fcbg(ListAdapter r0, TextCheckCell2 r1) {
            r0.lambda$onBindViewHolder$3(r1);
        }

        /* renamed from: $r8$lambda$kgpNE2gbR-RoyB1ngIZzz4kx8BU, reason: not valid java name */
        public static /* synthetic */ void m8498$r8$lambda$kgpNE2gbRRoyB1ngIZzz4kx8BU(ListAdapter r0, TextCheckCell2 r1) {
            r0.lambda$onBindViewHolder$1(r1);
        }

        /* renamed from: -$$Nest$fgetignoreTextChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m8499$$Nest$fgetignoreTextChange(ListAdapter r0) {
            return r0.ignoreTextChange;
        }

        public ListAdapter(ChatRightsEditActivity r4, Context r5) {
            this.this$0 = r4;
            this.VIEW_TYPE_USER_CELL = 0;
            this.VIEW_TYPE_INFO_CELL = 1;
            this.VIEW_TYPE_TRANSFER_CELL = 2;
            this.VIEW_TYPE_HEADER_CELL = 3;
            this.VIEW_TYPE_SWITCH_CELL = 4;
            this.VIEW_TYPE_SHADOW_CELL = 5;
            this.VIEW_TYPE_UNTIL_DATE_CELL = 6;
            this.VIEW_TYPE_RANK_CELL = 7;
            this.VIEW_TYPE_ADD_BOT_CELL = 8;
            this.VIEW_TYPE_EXPANDABLE_SWITCH = 9;
            this.VIEW_TYPE_INNER_CHECK = 10;
            this.VIEW_TYPE_TAG_CELL = 11;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(r4) != 2) goto L5;
            setHasStableIds(true);
        L5:
            this.mContext = r5;
        }

        private /* synthetic */ void lambda$onBindViewHolder$1(TextCheckCell2 r2) {
            if (r2.isEnabled() == true) goto L6;
            return;
        L6:
            if (ChatRightsEditActivity.m8486$$Nest$mallDefaultMediaBanned(this.this$0) == false) goto L9;
            new AlertDialog.Builder(this.this$0.getParentActivity()).setTitle(LocaleController.getString(R.string.UserRestrictionsCantModify)).setMessage(LocaleController.getString(R.string.UserRestrictionsCantModifyEnabled)).setPositiveButton(LocaleController.getString(R.string.OK), null).create().show();
            return;
        L9:
            boolean r0 = !r2.isChecked();
            r2.setChecked(r0);
            ChatRightsEditActivity.m8495$$Nest$msetSendMediaEnabled(this.this$0, r0);
        }

        private /* synthetic */ void lambda$onBindViewHolder$2(TextCheckCell2 r2) {
            if (r2.isEnabled() == true) goto L5;
            return;
        L5:
            boolean r0 = r2.isChecked();
            r2.setChecked(r0);
            ChatRightsEditActivity.m8493$$Nest$msetChannelMessagesEnabled(this.this$0, r0);
        }

        private /* synthetic */ void lambda$onBindViewHolder$3(TextCheckCell2 r2) {
            if (r2.isEnabled() == true) goto L5;
            return;
        L5:
            boolean r0 = r2.isChecked();
            r2.setChecked(r0);
            ChatRightsEditActivity.m8494$$Nest$msetChannelStoriesEnabled(this.this$0, r0);
        }

        private /* synthetic */ void lambda$onBindViewHolder$4(String r1) {
            ChatRightsEditActivity.m8484$$Nest$fputcurrentRank(this.this$0, r1);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(View r1) {
            ChatRightsEditActivity.m8492$$Nest$monDonePressed(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ChatRightsEditActivity.m8463$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r3) {
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L207;
            if (r3 != ChatRightsEditActivity.m8451$$Nest$fgetmanageRow(this.this$0)) goto L9;
            return 1;
        L9:
            if (r3 != ChatRightsEditActivity.m8417$$Nest$fgetchangeInfoRow(this.this$0)) goto L13;
            return 2;
        L13:
            if (r3 != ChatRightsEditActivity.m8456$$Nest$fgetpostMessagesRow(this.this$0)) goto L17;
            return 3;
        L17:
            if (r3 != ChatRightsEditActivity.m8438$$Nest$fgeteditMesagesRow(this.this$0)) goto L21;
            return 4;
        L21:
            if (r3 != ChatRightsEditActivity.m8436$$Nest$fgetdeleteMessagesRow(this.this$0)) goto L25;
            return 5;
        L25:
            if (r3 != ChatRightsEditActivity.m8403$$Nest$fgetaddAdminsRow(this.this$0)) goto L29;
            return 6;
        L29:
            if (r3 != ChatRightsEditActivity.m8410$$Nest$fgetanonymousRow(this.this$0)) goto L33;
            return 7;
        L33:
            if (r3 != ChatRightsEditActivity.m8413$$Nest$fgetbanUsersRow(this.this$0)) goto L37;
            return 8;
        L37:
            if (r3 != ChatRightsEditActivity.m8408$$Nest$fgetaddUsersRow(this.this$0)) goto L41;
            return 9;
        L41:
            if (r3 != ChatRightsEditActivity.m8455$$Nest$fgetpinMessagesRow(this.this$0)) goto L45;
            return 10;
        L45:
            if (r3 != ChatRightsEditActivity.m8462$$Nest$fgetrightsShadowRow(this.this$0)) goto L49;
            return 11;
        L49:
            if (r3 != ChatRightsEditActivity.m8460$$Nest$fgetremoveAdminRow(this.this$0)) goto L53;
            return 12;
        L53:
            if (r3 != ChatRightsEditActivity.m8461$$Nest$fgetremoveAdminShadowRow(this.this$0)) goto L57;
            return 13;
        L57:
            if (r3 != ChatRightsEditActivity.m8416$$Nest$fgetcantEditInfoRow(this.this$0)) goto L61;
            return 14;
        L61:
            if (r3 != ChatRightsEditActivity.m8478$$Nest$fgettransferOwnerShadowRow(this.this$0)) goto L65;
            return 15;
        L65:
            if (r3 != ChatRightsEditActivity.m8477$$Nest$fgettransferOwnerRow(this.this$0)) goto L69;
            return 16;
        L69:
            if (r3 != ChatRightsEditActivity.m8457$$Nest$fgetrankHeaderRow(this.this$0)) goto L73;
            return 17;
        L73:
            if (r3 != ChatRightsEditActivity.m8459$$Nest$fgetrankRow(this.this$0)) goto L77;
            return 18;
        L77:
            if (r3 != ChatRightsEditActivity.m8458$$Nest$fgetrankInfoRow(this.this$0)) goto L81;
            return 19;
        L81:
            if (r3 != ChatRightsEditActivity.m8467$$Nest$fgetsendMessagesRow(this.this$0)) goto L85;
            return 20;
        L85:
            if (r3 != ChatRightsEditActivity.m8469$$Nest$fgetsendPhotosRow(this.this$0)) goto L89;
            return 21;
        L89:
            if (r3 != ChatRightsEditActivity.m8473$$Nest$fgetsendStickersRow(this.this$0)) goto L93;
            return 22;
        L93:
            if (r3 != ChatRightsEditActivity.m8470$$Nest$fgetsendPollsRow(this.this$0)) goto L97;
            return 23;
        L97:
            if (r3 != ChatRightsEditActivity.m8440$$Nest$fgetembedLinksRow(this.this$0)) goto L101;
            return 24;
        L101:
            if (r3 != ChatRightsEditActivity.m8476$$Nest$fgetstartVoiceChatRow(this.this$0)) goto L105;
            return 25;
        L105:
            if (r3 != ChatRightsEditActivity.m8480$$Nest$fgetuntilSectionRow(this.this$0)) goto L109;
            return 26;
        L109:
            if (r3 != ChatRightsEditActivity.m8479$$Nest$fgetuntilDateRow(this.this$0)) goto L113;
            return 27;
        L113:
            if (r3 != ChatRightsEditActivity.m8406$$Nest$fgetaddBotButtonRow(this.this$0)) goto L117;
            return 28;
        L117:
            if (r3 != ChatRightsEditActivity.m8452$$Nest$fgetmanageTopicsRow(this.this$0)) goto L121;
            return 29;
        L121:
            if (r3 != ChatRightsEditActivity.m8474$$Nest$fgetsendVideosRow(this.this$0)) goto L125;
            return 30;
        L125:
            if (r3 != ChatRightsEditActivity.m8464$$Nest$fgetsendFilesRow(this.this$0)) goto L129;
            return 31;
        L129:
            if (r3 != ChatRightsEditActivity.m8468$$Nest$fgetsendMusicRow(this.this$0)) goto L133;
            return 32;
        L133:
            if (r3 != ChatRightsEditActivity.m8475$$Nest$fgetsendVoiceRow(this.this$0)) goto L137;
            return 33;
        L137:
            if (r3 != ChatRightsEditActivity.m8472$$Nest$fgetsendRoundRow(this.this$0)) goto L141;
            return 34;
        L141:
            if (r3 != ChatRightsEditActivity.m8466$$Nest$fgetsendMediaRow(this.this$0)) goto L145;
            return 35;
        L145:
            if (r3 != ChatRightsEditActivity.m8423$$Nest$fgetchannelMessagesRow(this.this$0)) goto L149;
            return 36;
        L149:
            if (r3 != ChatRightsEditActivity.m8424$$Nest$fgetchannelPostMessagesRow(this.this$0)) goto L153;
            return 37;
        L153:
            if (r3 != ChatRightsEditActivity.m8420$$Nest$fgetchannelEditMessagesRow(this.this$0)) goto L157;
            return 38;
        L157:
            if (r3 != ChatRightsEditActivity.m8418$$Nest$fgetchannelDeleteMessagesRow(this.this$0)) goto L161;
            return 39;
        L161:
            if (r3 != ChatRightsEditActivity.m8427$$Nest$fgetchannelStoriesRow(this.this$0)) goto L165;
            return 40;
        L165:
            if (r3 != ChatRightsEditActivity.m8425$$Nest$fgetchannelPostStoriesRow(this.this$0)) goto L169;
            return 41;
        L169:
            if (r3 != ChatRightsEditActivity.m8421$$Nest$fgetchannelEditStoriesRow(this.this$0)) goto L173;
            return 42;
        L173:
            if (r3 != ChatRightsEditActivity.m8419$$Nest$fgetchannelDeleteStoriesRow(this.this$0)) goto L177;
            return 43;
        L177:
            if (r3 != ChatRightsEditActivity.m8449$$Nest$fgetmanageDirectRow(this.this$0)) goto L181;
            return 44;
        L181:
            if (r3 != ChatRightsEditActivity.m8439$$Nest$fgeteditTagsRow(this.this$0)) goto L185;
            return 45;
        L185:
            if (r3 != ChatRightsEditActivity.m8471$$Nest$fgetsendReactionsRow(this.this$0)) goto L189;
            return 46;
        L189:
            if (r3 != ChatRightsEditActivity.m8443$$Nest$fgetguardBotRow(this.this$0)) goto L193;
            return 47;
        L193:
            if (r3 != ChatRightsEditActivity.m8442$$Nest$fgetguardBotInfoRow(this.this$0)) goto L197;
            return 48;
        L197:
            if (r3 != ChatRightsEditActivity.m8450$$Nest$fgetmanageLinkedPeersRow(this.this$0)) goto L201;
            return 49;
        L201:
            if (r3 != ChatRightsEditActivity.m8453$$Nest$fgetmanageWelcomeRow(this.this$0)) goto L204;
            return 50;
        L204:
            return 0;
        L207:
            return super.getItemId(r3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r4) {
            if (ChatRightsEditActivity.m8491$$Nest$misExpandableSendMediaRow(this.this$0, r4) == false) goto L7;
            return 10;
        L7:
            if (r4 != ChatRightsEditActivity.m8466$$Nest$fgetsendMediaRow(this.this$0)) goto L9;
            return 9;
        L9:
            if (r4 != ChatRightsEditActivity.m8423$$Nest$fgetchannelMessagesRow(this.this$0)) goto L11;
            return 9;
        L11:
            if (r4 == ChatRightsEditActivity.m8427$$Nest$fgetchannelStoriesRow(this.this$0)) goto L121;
            if (r4 != 0) goto L17;
            return 0;
        L17:
            if (r4 != 1) goto L19;
            return 5;
        L19:
            if (r4 != ChatRightsEditActivity.m8462$$Nest$fgetrightsShadowRow(this.this$0)) goto L21;
            return 5;
        L21:
            if (r4 != ChatRightsEditActivity.m8461$$Nest$fgetremoveAdminShadowRow(this.this$0)) goto L23;
            return 5;
        L23:
            if (r4 != ChatRightsEditActivity.m8480$$Nest$fgetuntilSectionRow(this.this$0)) goto L25;
            return 5;
        L25:
            if (r4 != ChatRightsEditActivity.m8478$$Nest$fgettransferOwnerShadowRow(this.this$0)) goto L28;
            return 5;
        L28:
            if (r4 != 2) goto L30;
            return 3;
        L30:
            if (r4 != ChatRightsEditActivity.m8457$$Nest$fgetrankHeaderRow(this.this$0)) goto L33;
            return 3;
        L33:
            if (r4 != ChatRightsEditActivity.m8417$$Nest$fgetchangeInfoRow(this.this$0)) goto L35;
            return 4;
        L35:
            if (r4 != ChatRightsEditActivity.m8456$$Nest$fgetpostMessagesRow(this.this$0)) goto L37;
            return 4;
        L37:
            if (r4 != ChatRightsEditActivity.m8449$$Nest$fgetmanageDirectRow(this.this$0)) goto L39;
            return 4;
        L39:
            if (r4 != ChatRightsEditActivity.m8438$$Nest$fgeteditMesagesRow(this.this$0)) goto L41;
            return 4;
        L41:
            if (r4 != ChatRightsEditActivity.m8436$$Nest$fgetdeleteMessagesRow(this.this$0)) goto L43;
            return 4;
        L43:
            if (r4 != ChatRightsEditActivity.m8403$$Nest$fgetaddAdminsRow(this.this$0)) goto L45;
            return 4;
        L45:
            if (r4 != ChatRightsEditActivity.m8413$$Nest$fgetbanUsersRow(this.this$0)) goto L47;
            return 4;
        L47:
            if (r4 != ChatRightsEditActivity.m8408$$Nest$fgetaddUsersRow(this.this$0)) goto L49;
            return 4;
        L49:
            if (r4 != ChatRightsEditActivity.m8455$$Nest$fgetpinMessagesRow(this.this$0)) goto L51;
            return 4;
        L51:
            if (r4 != ChatRightsEditActivity.m8439$$Nest$fgeteditTagsRow(this.this$0)) goto L53;
            return 4;
        L53:
            if (r4 != ChatRightsEditActivity.m8467$$Nest$fgetsendMessagesRow(this.this$0)) goto L55;
            return 4;
        L55:
            if (r4 != ChatRightsEditActivity.m8410$$Nest$fgetanonymousRow(this.this$0)) goto L57;
            return 4;
        L57:
            if (r4 != ChatRightsEditActivity.m8476$$Nest$fgetstartVoiceChatRow(this.this$0)) goto L59;
            return 4;
        L59:
            if (r4 != ChatRightsEditActivity.m8451$$Nest$fgetmanageRow(this.this$0)) goto L61;
            return 4;
        L61:
            if (r4 != ChatRightsEditActivity.m8452$$Nest$fgetmanageTopicsRow(this.this$0)) goto L63;
            return 4;
        L63:
            if (r4 != ChatRightsEditActivity.m8443$$Nest$fgetguardBotRow(this.this$0)) goto L65;
            return 4;
        L65:
            if (r4 != ChatRightsEditActivity.m8450$$Nest$fgetmanageLinkedPeersRow(this.this$0)) goto L67;
            return 4;
        L67:
            if (r4 != ChatRightsEditActivity.m8453$$Nest$fgetmanageWelcomeRow(this.this$0)) goto L70;
            return 4;
        L70:
            if (r4 != ChatRightsEditActivity.m8416$$Nest$fgetcantEditInfoRow(this.this$0)) goto L72;
        L89:
            return 1;
        L72:
            if (r4 == ChatRightsEditActivity.m8458$$Nest$fgetrankInfoRow(this.this$0)) goto L89;
            if (r4 == ChatRightsEditActivity.m8442$$Nest$fgetguardBotInfoRow(this.this$0)) goto L89;
            if (r4 != ChatRightsEditActivity.m8479$$Nest$fgetuntilDateRow(this.this$0)) goto L81;
            return 6;
        L81:
            if (r4 != ChatRightsEditActivity.m8459$$Nest$fgetrankRow(this.this$0)) goto L85;
            return 11;
        L85:
            if (r4 != ChatRightsEditActivity.m8406$$Nest$fgetaddBotButtonRow(this.this$0)) goto L88;
            return 8;
        L88:
            return 2;
        L121:
            return 9;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r7) {
            int r0 = r7.getItemViewType();
            if (r0 != 0) goto L5;
        L137:
            return true;
        L5:
            if (ChatRightsEditActivity.m8430$$Nest$fgetcurrentChat(this.this$0).creator == false) goto L17;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 0) goto L9;
        L12:
            if (r0 != 4) goto L17;
            if (r7.getAdapterPosition() != ChatRightsEditActivity.m8410$$Nest$fgetanonymousRow(this.this$0)) goto L17;
        L9:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L17;
            if (ChatRightsEditActivity.m8411$$Nest$fgetasAdmin(this.this$0) == true) goto L12;
        L17:
            if (ChatRightsEditActivity.m8415$$Nest$fgetcanEdit(this.this$0) == true) goto L20;
            return false;
        L20:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 0) goto L22;
        L23:
            if (r0 != 4) goto L127;
            int r72 = r7.getAdapterPosition();
            int r2 = ChatRightsEditActivity.m8451$$Nest$fgetmanageRow(this.this$0);
            ChatRightsEditActivity r3 = this.this$0;
            if (r72 != r2) goto L36;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r3).add_admins == false) goto L29;
        L34:
            return true;
        L29:
            if (ChatRightsEditActivity.m8430$$Nest$fgetcurrentChat(this.this$0) != null) goto L31;
        L33:
            return false;
        L31:
            if (ChatRightsEditActivity.m8430$$Nest$fgetcurrentChat(this.this$0).creator == false) goto L33;
        L36:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(r3) == 2) goto L38;
        L40:
            int r22 = ChatRightsEditActivity.m8417$$Nest$fgetchangeInfoRow(this.this$0);
            ChatRightsEditActivity r32 = this.this$0;
            if (r72 == r22) goto L43;
            int r23 = ChatRightsEditActivity.m8456$$Nest$fgetpostMessagesRow(r32);
            ChatRightsEditActivity r33 = this.this$0;
            if (r72 == r23) goto L55;
            int r24 = ChatRightsEditActivity.m8449$$Nest$fgetmanageDirectRow(r33);
            ChatRightsEditActivity r34 = this.this$0;
            if (r72 == r24) goto L59;
            int r25 = ChatRightsEditActivity.m8453$$Nest$fgetmanageWelcomeRow(r34);
            ChatRightsEditActivity r35 = this.this$0;
            if (r72 == r25) goto L63;
            int r26 = ChatRightsEditActivity.m8438$$Nest$fgeteditMesagesRow(r35);
            ChatRightsEditActivity r36 = this.this$0;
            if (r72 == r26) goto L67;
            int r27 = ChatRightsEditActivity.m8436$$Nest$fgetdeleteMessagesRow(r36);
            ChatRightsEditActivity r37 = this.this$0;
            if (r72 == r27) goto L71;
            int r28 = ChatRightsEditActivity.m8476$$Nest$fgetstartVoiceChatRow(r37);
            ChatRightsEditActivity r38 = this.this$0;
            if (r72 == r28) goto L75;
            int r29 = ChatRightsEditActivity.m8403$$Nest$fgetaddAdminsRow(r38);
            ChatRightsEditActivity r39 = this.this$0;
            if (r72 == r29) goto L79;
            int r210 = ChatRightsEditActivity.m8410$$Nest$fgetanonymousRow(r39);
            ChatRightsEditActivity r310 = this.this$0;
            if (r72 == r210) goto L83;
            int r211 = ChatRightsEditActivity.m8413$$Nest$fgetbanUsersRow(r310);
            ChatRightsEditActivity r311 = this.this$0;
            if (r72 == r211) goto L87;
            int r212 = ChatRightsEditActivity.m8408$$Nest$fgetaddUsersRow(r311);
            ChatRightsEditActivity r312 = this.this$0;
            if (r72 == r212) goto L91;
            int r213 = ChatRightsEditActivity.m8455$$Nest$fgetpinMessagesRow(r312);
            ChatRightsEditActivity r313 = this.this$0;
            if (r72 == r213) goto L95;
            int r214 = ChatRightsEditActivity.m8439$$Nest$fgeteditTagsRow(r313);
            ChatRightsEditActivity r314 = this.this$0;
            if (r72 == r214) goto L105;
            int r215 = ChatRightsEditActivity.m8452$$Nest$fgetmanageTopicsRow(r314);
            ChatRightsEditActivity r315 = this.this$0;
            if (r72 == r215) goto L109;
            int r216 = ChatRightsEditActivity.m8425$$Nest$fgetchannelPostStoriesRow(r315);
            ChatRightsEditActivity r316 = this.this$0;
            if (r72 == r216) goto L113;
            int r217 = ChatRightsEditActivity.m8421$$Nest$fgetchannelEditStoriesRow(r316);
            ChatRightsEditActivity r317 = this.this$0;
            if (r72 == r217) goto L117;
            int r218 = ChatRightsEditActivity.m8419$$Nest$fgetchannelDeleteStoriesRow(r317);
            ChatRightsEditActivity r318 = this.this$0;
            if (r72 != r218) goto L123;
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r318).delete_stories;
        L123:
            if (r72 != ChatRightsEditActivity.m8450$$Nest$fgetmanageLinkedPeersRow(r318)) goto L127;
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).manage_linked_peers;
        L117:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r317).edit_stories;
        L113:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r316).post_stories;
        L109:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r315).manage_topics;
        L105:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r314).manage_ranks;
        L95:
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r313).pin_messages == true) goto L97;
        L101:
            return false;
        L97:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0) != null) goto L99;
        L100:
            return true;
        L99:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).pin_messages == false) goto L101;
        L91:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r312).invite_users;
        L87:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r311).ban_users;
        L83:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r310).anonymous;
        L79:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r39).add_admins;
        L75:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r38).manage_call;
        L71:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r37).delete_messages;
        L67:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r36).edit_messages;
        L63:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r35).manage_welcome_messages;
        L59:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r34).manage_direct_messages;
        L55:
            return ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r33).post_messages;
        L43:
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(r32).change_info == true) goto L45;
        L51:
            return false;
        L45:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0) != null) goto L47;
        L50:
            return true;
        L47:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).change_info == true) goto L50;
            if (ChatRightsEditActivity.m8445$$Nest$fgetisChannel(this.this$0) == false) goto L51;
        L38:
            if (ChatRightsEditActivity.m8411$$Nest$fgetasAdmin(this.this$0) == true) goto L40;
            return false;
        L127:
            if (r0 == 3) goto L136;
            if (r0 == 1) goto L136;
            if (r0 == 5) goto L136;
            if (r0 == 8) goto L136;
            if (r0 == 11) goto L136;
            return true;
        L136:
            return false;
        L22:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L127;
            goto L23
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r11, int r12) {
            boolean r2 = true;
            int r3 = 0;
            switch(r11.getItemViewType()) {
                case 0: goto L788;
                case 1: goto L766;
                case 2: goto L748;
                case 3: goto L725;
                case 4: goto L211;
                case 5: goto L201;
                case 6: goto L190;
                case 7: goto L175;
                case 8: goto L802;
                case 9: goto L211;
                case 10: goto L11;
                case 11: goto L5;
                default: goto L802;
            };
        L5:
            TagEditCell r4 = (TagEditCell) r11.itemView;
            TLRPC.User r5 = ChatRightsEditActivity.m8433$$Nest$fgetcurrentUser(this.this$0);
            String r6 = ChatRightsEditActivity.m8431$$Nest$fgetcurrentRank(this.this$0);
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 0) goto L8;
            boolean r7 = true;
        L9:
            r4.set(r5, r6, r7, false, new ChatRightsEditActivity$ListAdapter$$ExternalSyntheticLambda4(this));
            return;
        L8:
            r7 = false;
            goto L9
        L11:
            CheckBoxCell r42 = (CheckBoxCell) r11.itemView;
            if (r42.getTag() != null) goto L14;
        L16:
            boolean r9 = false;
        L17:
            r42.setTag(Integer.valueOf(r12));
            if (r12 != ChatRightsEditActivity.m8473$$Nest$fgetsendStickersRow(this.this$0)) goto L31;
            String r52 = LocaleController.getString(R.string.SendMediaPermissionStickersGifs);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).send_stickers == false) goto L22;
        L24:
            boolean r72 = false;
        L25:
            r42.setText(r52, _UrlKt.FRAGMENT_ENCODE_SET, r72, true, r9);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_stickers == false) goto L28;
            r3 = R.drawable.permission_locked;
        L28:
            r42.setIcon(r3);
            return;
        L22:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_stickers == true) goto L24;
            r72 = true;
            goto L25
        L31:
            if (r12 != ChatRightsEditActivity.m8440$$Nest$fgetembedLinksRow(this.this$0)) goto L48;
            String r53 = LocaleController.getString(R.string.UserRestrictionsEmbedLinks);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).embed_links == false) goto L35;
        L41:
            boolean r73 = false;
        L42:
            r42.setText(r53, _UrlKt.FRAGMENT_ENCODE_SET, r73, true, r9);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).embed_links == false) goto L45;
            r3 = R.drawable.permission_locked;
        L45:
            r42.setIcon(r3);
            return;
        L35:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).embed_links == true) goto L41;
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).send_plain == true) goto L41;
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_plain == true) goto L41;
            r73 = true;
            goto L42
        L48:
            if (r12 != ChatRightsEditActivity.m8470$$Nest$fgetsendPollsRow(this.this$0)) goto L61;
            String r54 = LocaleController.getString(R.string.SendMediaPolls);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).send_polls == false) goto L52;
        L54:
            boolean r74 = false;
        L55:
            r42.setText(r54, _UrlKt.FRAGMENT_ENCODE_SET, r74, true, r9);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_polls == false) goto L58;
            r3 = R.drawable.permission_locked;
        L58:
            r42.setIcon(r3);
            return;
        L52:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_polls == true) goto L54;
            r74 = true;
            goto L55
        L61:
            if (r12 != ChatRightsEditActivity.m8469$$Nest$fgetsendPhotosRow(this.this$0)) goto L74;
            String r55 = LocaleController.getString(R.string.SendMediaPermissionPhotos);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).send_photos == false) goto L65;
        L67:
            boolean r75 = false;
        L68:
            r42.setText(r55, _UrlKt.FRAGMENT_ENCODE_SET, r75, true, r9);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_photos == false) goto L71;
            r3 = R.drawable.permission_locked;
        L71:
            r42.setIcon(r3);
            return;
        L65:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_photos == true) goto L67;
            r75 = true;
            goto L68
        L74:
            if (r12 != ChatRightsEditActivity.m8474$$Nest$fgetsendVideosRow(this.this$0)) goto L87;
            String r56 = LocaleController.getString(R.string.SendMediaPermissionVideos);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).send_videos == false) goto L78;
        L80:
            boolean r76 = false;
        L81:
            r42.setText(r56, _UrlKt.FRAGMENT_ENCODE_SET, r76, true, r9);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_videos == false) goto L84;
            r3 = R.drawable.permission_locked;
        L84:
            r42.setIcon(r3);
            return;
        L78:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_videos == true) goto L80;
            r76 = true;
            goto L81
        L87:
            if (r12 != ChatRightsEditActivity.m8471$$Nest$fgetsendReactionsRow(this.this$0)) goto L100;
            String r112 = LocaleController.getString(R.string.UserRestrictionsSendReactions);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).send_reactions == false) goto L91;
        L93:
            boolean r122 = false;
        L94:
            r42.setText(r112, _UrlKt.FRAGMENT_ENCODE_SET, r122, true);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_reactions == false) goto L97;
            r3 = R.drawable.permission_locked;
        L97:
            r42.setIcon(r3);
            return;
        L91:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_reactions == true) goto L93;
            r122 = true;
            goto L94
        L100:
            if (r12 != ChatRightsEditActivity.m8468$$Nest$fgetsendMusicRow(this.this$0)) goto L113;
            String r57 = LocaleController.getString(R.string.SendMediaPermissionMusic);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).send_audios == false) goto L104;
        L106:
            boolean r77 = false;
        L107:
            r42.setText(r57, _UrlKt.FRAGMENT_ENCODE_SET, r77, true, r9);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_audios == false) goto L110;
            r3 = R.drawable.permission_locked;
        L110:
            r42.setIcon(r3);
            return;
        L104:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_audios == true) goto L106;
            r77 = true;
            goto L107
        L113:
            if (r12 != ChatRightsEditActivity.m8464$$Nest$fgetsendFilesRow(this.this$0)) goto L126;
            String r58 = LocaleController.getString(R.string.SendMediaPermissionFiles);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).send_docs == false) goto L117;
        L119:
            boolean r78 = false;
        L120:
            r42.setText(r58, _UrlKt.FRAGMENT_ENCODE_SET, r78, true, r9);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_docs == false) goto L123;
            r3 = R.drawable.permission_locked;
        L123:
            r42.setIcon(r3);
            return;
        L117:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_docs == true) goto L119;
            r78 = true;
            goto L120
        L126:
            if (r12 != ChatRightsEditActivity.m8475$$Nest$fgetsendVoiceRow(this.this$0)) goto L139;
            String r59 = LocaleController.getString(R.string.SendMediaPermissionVoice);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).send_voices == false) goto L130;
        L132:
            boolean r79 = false;
        L133:
            r42.setText(r59, _UrlKt.FRAGMENT_ENCODE_SET, r79, true, r9);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_voices == false) goto L136;
            r3 = R.drawable.permission_locked;
        L136:
            r42.setIcon(r3);
            return;
        L130:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_voices == true) goto L132;
            r79 = true;
            goto L133
        L139:
            if (r12 != ChatRightsEditActivity.m8472$$Nest$fgetsendRoundRow(this.this$0)) goto L152;
            String r510 = LocaleController.getString(R.string.SendMediaPermissionRound);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).send_roundvideos == false) goto L143;
        L145:
            boolean r710 = false;
        L146:
            r42.setText(r510, _UrlKt.FRAGMENT_ENCODE_SET, r710, true, r9);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_roundvideos == false) goto L149;
            r3 = R.drawable.permission_locked;
        L149:
            r42.setIcon(r3);
            return;
        L143:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_roundvideos == true) goto L145;
            r710 = true;
            goto L146
        L152:
            if (r12 != ChatRightsEditActivity.m8424$$Nest$fgetchannelPostMessagesRow(this.this$0)) goto L156;
            r42.setText(LocaleController.getString(R.string.EditAdminPostMessages), _UrlKt.FRAGMENT_ENCODE_SET, ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).post_messages, true, r9);
            return;
        L156:
            if (r12 != ChatRightsEditActivity.m8420$$Nest$fgetchannelEditMessagesRow(this.this$0)) goto L160;
            r42.setText(LocaleController.getString(R.string.EditAdminEditMessages), _UrlKt.FRAGMENT_ENCODE_SET, ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).edit_messages, true, r9);
            return;
        L160:
            if (r12 != ChatRightsEditActivity.m8418$$Nest$fgetchannelDeleteMessagesRow(this.this$0)) goto L164;
            r42.setText(LocaleController.getString(R.string.EditAdminDeleteMessages), _UrlKt.FRAGMENT_ENCODE_SET, ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).delete_messages, true, r9);
            return;
        L164:
            if (r12 != ChatRightsEditActivity.m8425$$Nest$fgetchannelPostStoriesRow(this.this$0)) goto L168;
            r42.setText(LocaleController.getString(R.string.EditAdminPostStories), _UrlKt.FRAGMENT_ENCODE_SET, ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).post_stories, true, r9);
            return;
        L168:
            if (r12 != ChatRightsEditActivity.m8421$$Nest$fgetchannelEditStoriesRow(this.this$0)) goto L172;
            r42.setText(LocaleController.getString(R.string.EditAdminEditStories), _UrlKt.FRAGMENT_ENCODE_SET, ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).edit_stories, true, r9);
            return;
        L172:
            if (r12 != ChatRightsEditActivity.m8419$$Nest$fgetchannelDeleteStoriesRow(this.this$0)) goto L794;
            r42.setText(LocaleController.getString(R.string.EditAdminDeleteStories), _UrlKt.FRAGMENT_ENCODE_SET, ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).delete_stories, true, r9);
            return;
        L794:
            return;
        L14:
            if (((Integer) r42.getTag()).intValue() != r12) goto L16;
            r9 = true;
            goto L17
        L175:
            PollEditTextCell r113 = (PollEditTextCell) r11.itemView;
            if (UserObject.isUserSelf(ChatRightsEditActivity.m8433$$Nest$fgetcurrentUser(this.this$0)) == true) goto L178;
        L180:
            String r123 = LocaleController.getString(R.string.ChannelAdmin);
        L181:
            this.ignoreTextChange = true;
            EditTextBoldCursor r0 = r113.getTextView();
            if (ChatRightsEditActivity.m8415$$Nest$fgetcanEdit(this.this$0) == false) goto L184;
        L187:
            boolean r1 = true;
        L188:
            r0.setEnabled(r1);
            r113.getTextView().setSingleLine(true);
            r113.getTextView().setImeOptions(6);
            r113.setTextAndHint(ChatRightsEditActivity.m8431$$Nest$fgetcurrentRank(this.this$0), r123, false);
            this.ignoreTextChange = false;
            return;
        L184:
            if (ChatRightsEditActivity.m8430$$Nest$fgetcurrentChat(this.this$0).creator == true) goto L187;
            r1 = false;
            goto L188
        L178:
            if (ChatRightsEditActivity.m8430$$Nest$fgetcurrentChat(this.this$0).creator == false) goto L180;
            r123 = LocaleController.getString(R.string.ChannelCreator);
            goto L181
        L190:
            TextDetailCell r114 = (TextDetailCell) r11.itemView;
            if (r12 == ChatRightsEditActivity.m8479$$Nest$fgetuntilDateRow(this.this$0)) goto L193;
            return;
        L193:
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).until_date != 0) goto L195;
        L198:
            String r10 = LocaleController.getString(R.string.UserRestrictionsUntilForever);
        L199:
            r114.setTextAndValue(LocaleController.getString(R.string.UserRestrictionsDuration), r10, false);
            return;
        L195:
            if (Math.abs(ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).until_date - (System.currentTimeMillis() / 1000)) > 315360000) goto L198;
            r10 = LocaleController.formatDateForBan(ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).until_date);
            goto L199
        L201:
            ShadowSectionCell r115 = (ShadowSectionCell) r11.itemView;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) == 2) goto L204;
        L209:
            r115.setAlpha(1.0f);
            return;
        L204:
            if (r12 != ChatRightsEditActivity.m8462$$Nest$fgetrightsShadowRow(this.this$0)) goto L206;
        L207:
            r115.setAlpha(ChatRightsEditActivity.m8412$$Nest$fgetasAdminT(this.this$0));
            return;
        L206:
            if (r12 != ChatRightsEditActivity.m8458$$Nest$fgetrankInfoRow(this.this$0)) goto L209;
        L211:
            TextCheckCell2 r116 = (TextCheckCell2) r11.itemView;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) == 2) goto L214;
        L217:
            boolean r02 = true;
        L219:
            if (ChatRightsEditActivity.m8430$$Nest$fgetcurrentChat(this.this$0) != null) goto L221;
        L223:
            boolean r43 = false;
        L224:
            int r511 = ChatRightsEditActivity.m8466$$Nest$fgetsendMediaRow(this.this$0);
            ChatRightsEditActivity r62 = this.this$0;
            if (r12 != r511) goto L235;
            int r03 = ChatRightsEditActivity.m8490$$Nest$mgetSendMediaSelectedCount(r62);
            String r44 = LocaleController.getString(R.string.UserRestrictionsSendMedia);
            if (r03 <= 0) goto L229;
            boolean r512 = true;
        L230:
            r116.setTextAndCheck(r44, r512, true, true);
            r116.setCollapseArrow(String.format(Locale.US, "%d/10", new Object[]{Integer.valueOf(r03)}), !ChatRightsEditActivity.m8465$$Nest$fgetsendMediaExpanded(this.this$0), new ChatRightsEditActivity$ListAdapter$$ExternalSyntheticLambda1(this, r116));
            if (ChatRightsEditActivity.m8486$$Nest$mallDefaultMediaBanned(this.this$0) == false) goto L233;
            int r04 = R.drawable.permission_locked;
        L234:
            r116.setIcon(r04);
        L713:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L716;
            return;
        L716:
            if (r12 == ChatRightsEditActivity.m8467$$Nest$fgetsendMessagesRow(this.this$0)) goto L718;
            return;
        L718:
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).view_messages == false) goto L720;
        L722:
            r2 = false;
        L723:
            r116.setEnabled(r2);
            return;
        L720:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).view_messages == true) goto L722;
        L233:
            r04 = 0;
            goto L234
        L229:
            r512 = false;
            goto L230
        L235:
            int r513 = ChatRightsEditActivity.m8423$$Nest$fgetchannelMessagesRow(r62);
            ChatRightsEditActivity r63 = this.this$0;
            if (r12 != r513) goto L242;
            int r05 = ChatRightsEditActivity.m8488$$Nest$mgetChannelMessagesSelectedCount(r63);
            String r45 = LocaleController.getString(R.string.ChannelManageMessages);
            if (r05 <= 0) goto L240;
            boolean r514 = true;
        L241:
            r116.setTextAndCheck(r45, r514, true, true);
            r116.setCollapseArrow(String.format(Locale.US, "%d/3", new Object[]{Integer.valueOf(r05)}), !ChatRightsEditActivity.m8422$$Nest$fgetchannelMessagesExpanded(this.this$0), new ChatRightsEditActivity$ListAdapter$$ExternalSyntheticLambda2(this, r116));
            goto L713
        L240:
            r514 = false;
            goto L241
        L242:
            int r515 = ChatRightsEditActivity.m8427$$Nest$fgetchannelStoriesRow(r63);
            ChatRightsEditActivity r64 = this.this$0;
            if (r12 != r515) goto L250;
            int r06 = ChatRightsEditActivity.m8489$$Nest$mgetChannelStoriesSelectedCount(r64);
            String r46 = LocaleController.getString(R.string.ChannelManageStories);
            if (r06 <= 0) goto L247;
            boolean r516 = true;
        L248:
            r116.setTextAndCheck(r46, r516, true, true);
            r116.setCollapseArrow(String.format(Locale.US, "%d/3", new Object[]{Integer.valueOf(r06)}), !ChatRightsEditActivity.m8426$$Nest$fgetchannelStoriesExpanded(this.this$0), new ChatRightsEditActivity$ListAdapter$$ExternalSyntheticLambda3(this, r116));
            goto L713
        L247:
            r516 = false;
            goto L248
        L250:
            if (r12 != ChatRightsEditActivity.m8451$$Nest$fgetmanageRow(r64)) goto L258;
            r116.setTextAndCheck(LocaleController.getString(R.string.ManageGroup), ChatRightsEditActivity.m8411$$Nest$fgetasAdmin(this.this$0), true);
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).add_admins == true) goto L256;
            if (r43 == true) goto L256;
            int r07 = R.drawable.permission_locked;
        L257:
            r116.setIcon(r07);
        L256:
            r07 = 0;
            goto L257
        L258:
            int r517 = ChatRightsEditActivity.m8417$$Nest$fgetchangeInfoRow(this.this$0);
            ChatRightsEditActivity r65 = this.this$0;
            if (r12 != r517) goto L323;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(r65) == 0) goto L287;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) == 2) goto L287;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 1) goto L713;
            if (ChatRightsEditActivity.m8446$$Nest$fgetisCommunity(this.this$0) == false) goto L270;
            int r08 = R.string.CommunityAdminRightEditCommunityName;
        L271:
            String r09 = LocaleController.getString(r08);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).change_info == false) goto L274;
        L276:
            boolean r47 = false;
        L278:
            if (ChatRightsEditActivity.m8452$$Nest$fgetmanageTopicsRow(this.this$0) == (-1)) goto L280;
            boolean r518 = true;
        L281:
            r116.setTextAndCheck(r09, r47, r518);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).change_info == false) goto L284;
            int r010 = R.drawable.permission_locked;
        L285:
            r116.setIcon(r010);
            goto L713
        L284:
            r010 = 0;
            goto L285
        L280:
            r518 = false;
            goto L281
        L274:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).change_info == true) goto L276;
            r47 = true;
            goto L278
        L270:
            r08 = R.string.UserRestrictionsChangeInfo;
        L287:
            if (ChatRightsEditActivity.m8446$$Nest$fgetisCommunity(this.this$0) == false) goto L296;
            String r519 = LocaleController.getString(R.string.CommunityAdminRightEditCommunityName);
            if (r02 == true) goto L291;
        L293:
            boolean r011 = false;
        L294:
            r116.setTextAndCheck(r519, r011, true);
        L314:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).change_info == true) goto L320;
            if (r43 == true) goto L320;
            int r012 = R.drawable.permission_locked;
        L321:
            r116.setIcon(r012);
        L320:
            r012 = 0;
            goto L321
        L291:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).change_info == false) goto L293;
            r011 = true;
            goto L294
        L296:
            if (ChatRightsEditActivity.m8445$$Nest$fgetisChannel(this.this$0) == false) goto L304;
            String r520 = LocaleController.getString(R.string.EditAdminChangeChannelInfo);
            if (r02 == true) goto L300;
        L302:
            boolean r013 = false;
        L303:
            r116.setTextAndCheck(r520, r013, true);
            goto L314
        L300:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).change_info == false) goto L302;
            r013 = true;
            goto L303
        L304:
            String r521 = LocaleController.getString(R.string.EditAdminChangeGroupInfo);
            if (r02 == false) goto L309;
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).change_info == false) goto L309;
        L310:
            boolean r014 = true;
        L312:
            r116.setTextAndCheck(r521, r014, true);
        L309:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).change_info == false) goto L310;
            r014 = false;
            goto L312
        L323:
            if (r12 != ChatRightsEditActivity.m8456$$Nest$fgetpostMessagesRow(r65)) goto L339;
            String r522 = LocaleController.getString(R.string.EditAdminPostMessages);
            if (r02 == true) goto L327;
        L329:
            boolean r015 = false;
        L330:
            r116.setTextAndCheck(r522, r015, true);
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).post_messages == true) goto L337;
            if (r43 == true) goto L337;
            int r016 = R.drawable.permission_locked;
        L338:
            r116.setIcon(r016);
        L337:
            r016 = 0;
            goto L338
        L327:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).post_messages == false) goto L329;
            r015 = true;
            goto L330
        L339:
            int r523 = ChatRightsEditActivity.m8453$$Nest$fgetmanageWelcomeRow(this.this$0);
            ChatRightsEditActivity r66 = this.this$0;
            if (r12 != r523) goto L371;
            if (UserObject.isBot(ChatRightsEditActivity.m8433$$Nest$fgetcurrentUser(r66)) == true) goto L344;
            String r524 = LocaleController.getString(R.string.EditAdminUserManageWelcomeMessages);
            if (r02 == true) goto L357;
        L359:
            boolean r017 = false;
        L360:
            r116.setTextAndCheck(r524, r017, true);
        L362:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).manage_welcome_messages == true) goto L368;
            if (r43 == true) goto L368;
            int r018 = R.drawable.permission_locked;
        L369:
            r116.setIcon(r018);
        L368:
            r018 = 0;
            goto L369
        L357:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).manage_welcome_messages == false) goto L359;
            r017 = true;
            goto L360
        L344:
            if (ChatRightsEditActivity.m8445$$Nest$fgetisChannel(this.this$0) == false) goto L346;
            int r525 = R.string.EditAdminBotChannelSendWelcomeMessages;
        L347:
            String r526 = LocaleController.getString(r525);
            if (r02 == true) goto L350;
        L352:
            boolean r019 = false;
        L353:
            r116.setTextAndCheck(r526, r019, true);
            goto L362
        L350:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).manage_welcome_messages == false) goto L352;
            r019 = true;
            goto L353
        L346:
            r525 = R.string.EditAdminBotGroupSendWelcomeMessages;
            goto L347
        L371:
            if (r12 != ChatRightsEditActivity.m8449$$Nest$fgetmanageDirectRow(r66)) goto L388;
            String r527 = LocaleController.getString(R.string.EditAdminManageDirect);
            if (r02 == true) goto L375;
        L377:
            boolean r020 = false;
        L378:
            r116.setTextAndCheck(r527, r020, true);
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).manage_direct_messages == true) goto L385;
            if (r43 == true) goto L385;
            int r021 = R.drawable.permission_locked;
        L386:
            r116.setIcon(r021);
        L385:
            r021 = 0;
            goto L386
        L375:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).manage_direct_messages == false) goto L377;
            r020 = true;
            goto L378
        L388:
            if (r12 != ChatRightsEditActivity.m8438$$Nest$fgeteditMesagesRow(this.this$0)) goto L404;
            String r528 = LocaleController.getString(R.string.EditAdminEditMessages);
            if (r02 == true) goto L392;
        L394:
            boolean r022 = false;
        L395:
            r116.setTextAndCheck(r528, r022, true);
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).edit_messages == true) goto L402;
            if (r43 == true) goto L402;
            int r023 = R.drawable.permission_locked;
        L403:
            r116.setIcon(r023);
        L402:
            r023 = 0;
            goto L403
        L392:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).edit_messages == false) goto L394;
            r022 = true;
            goto L395
        L404:
            int r529 = ChatRightsEditActivity.m8436$$Nest$fgetdeleteMessagesRow(this.this$0);
            ChatRightsEditActivity r67 = this.this$0;
            if (r12 != r529) goto L432;
            if (ChatRightsEditActivity.m8445$$Nest$fgetisChannel(r67) == false) goto L415;
            String r530 = LocaleController.getString(R.string.EditAdminDeleteMessages);
            if (r02 == true) goto L411;
        L413:
            boolean r024 = false;
        L414:
            r116.setTextAndCheck(r530, r024, true);
        L423:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).delete_messages == true) goto L429;
            if (r43 == true) goto L429;
            int r025 = R.drawable.permission_locked;
        L430:
            r116.setIcon(r025);
        L429:
            r025 = 0;
            goto L430
        L411:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).delete_messages == false) goto L413;
            r024 = true;
            goto L414
        L415:
            String r531 = LocaleController.getString(R.string.EditAdminGroupDeleteMessages);
            if (r02 == true) goto L418;
        L420:
            boolean r026 = false;
        L421:
            r116.setTextAndCheck(r531, r026, true);
            goto L423
        L418:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).delete_messages == false) goto L420;
            r026 = true;
            goto L421
        L432:
            if (r12 != ChatRightsEditActivity.m8403$$Nest$fgetaddAdminsRow(r67)) goto L457;
            String r532 = LocaleController.getString(R.string.EditAdminAddAdmins);
            if (r02 == true) goto L436;
        L438:
            boolean r027 = false;
        L440:
            if (ChatRightsEditActivity.m8413$$Nest$fgetbanUsersRow(this.this$0) == (-1)) goto L444;
            if (ChatRightsEditActivity.m8445$$Nest$fgetisChannel(this.this$0) == false) goto L444;
        L445:
            boolean r68 = true;
        L447:
            r116.setTextAndCheck(r532, r027, r68);
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).add_admins == true) goto L454;
            if (r43 == true) goto L454;
            int r028 = R.drawable.permission_locked;
        L455:
            r116.setIcon(r028);
        L454:
            r028 = 0;
        L444:
            if (ChatRightsEditActivity.m8410$$Nest$fgetanonymousRow(this.this$0) != (-1)) goto L445;
            r68 = false;
            goto L447
        L436:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).add_admins == false) goto L438;
            r027 = true;
            goto L440
        L457:
            if (r12 != ChatRightsEditActivity.m8410$$Nest$fgetanonymousRow(this.this$0)) goto L481;
            String r533 = LocaleController.getString(R.string.EditAdminSendAnonymously);
            if (r02 == true) goto L461;
        L463:
            boolean r029 = false;
        L465:
            if (ChatRightsEditActivity.m8452$$Nest$fgetmanageTopicsRow(this.this$0) == (-1)) goto L467;
        L470:
            boolean r69 = true;
        L471:
            r116.setTextAndCheck(r533, r029, r69);
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).anonymous == true) goto L478;
            if (r43 == true) goto L478;
            int r030 = R.drawable.permission_locked;
        L479:
            r116.setIcon(r030);
        L478:
            r030 = 0;
            goto L479
        L467:
            if (ChatRightsEditActivity.m8434$$Nest$fgetcurrentUserIsBotGuard(this.this$0) == true) goto L470;
            r69 = false;
            goto L471
        L461:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).anonymous == false) goto L463;
            r029 = true;
            goto L465
        L481:
            if (r12 != ChatRightsEditActivity.m8443$$Nest$fgetguardBotRow(this.this$0)) goto L497;
            String r031 = LocaleController.getString(R.string.EditAdminProcessJoinRequests);
            if (ChatRightsEditActivity.m8429$$Nest$fgetchatInfo(this.this$0) != null) goto L485;
        L493:
            boolean r48 = false;
        L494:
            r116.setTextAndCheck(r031, r48, false);
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            r116.setIcon(0);
            goto L713
        L485:
            if (ChatRightsEditActivity.m8433$$Nest$fgetcurrentUser(this.this$0) == null) goto L493;
            boolean r49 = ChatRightsEditActivity.m8444$$Nest$fgethasGuardBotToSet(this.this$0);
            ChatRightsEditActivity r534 = this.this$0;
            if (r49 == false) goto L489;
            long r410 = ChatRightsEditActivity.m8441$$Nest$fgetguardBotIdToSet(r534);
        L491:
            if (r410 != ChatRightsEditActivity.m8433$$Nest$fgetcurrentUser(this.this$0).id) goto L493;
            r48 = true;
            goto L494
        L489:
            r410 = ChatRightsEditActivity.m8429$$Nest$fgetchatInfo(r534).guard_bot_id;
            goto L491
        L497:
            int r535 = ChatRightsEditActivity.m8413$$Nest$fgetbanUsersRow(this.this$0);
            ChatRightsEditActivity r610 = this.this$0;
            if (r12 != r535) goto L519;
            if (ChatRightsEditActivity.m8446$$Nest$fgetisCommunity(r610) == false) goto L502;
            int r536 = R.string.CommunityAdminRightBanMembers;
        L503:
            String r537 = LocaleController.getString(r536);
            if (r02 == true) goto L506;
        L508:
            boolean r032 = false;
        L509:
            r116.setTextAndCheck(r537, r032, !ChatRightsEditActivity.m8446$$Nest$fgetisCommunity(this.this$0));
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).ban_users == true) goto L516;
            if (r43 == true) goto L516;
            int r033 = R.drawable.permission_locked;
        L517:
            r116.setIcon(r033);
        L516:
            r033 = 0;
            goto L517
        L506:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).ban_users == false) goto L508;
            r032 = true;
            goto L509
        L502:
            r536 = R.string.EditAdminBanUsers;
            goto L503
        L519:
            if (r12 != ChatRightsEditActivity.m8450$$Nest$fgetmanageLinkedPeersRow(r610)) goto L536;
            String r538 = LocaleController.getString(R.string.CommunityAdminRightEditGroupList);
            if (r02 == true) goto L523;
        L525:
            boolean r034 = false;
        L526:
            r116.setTextAndCheck(r538, r034, true);
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).manage_linked_peers == true) goto L533;
            if (r43 == true) goto L533;
            int r035 = R.drawable.permission_locked;
        L534:
            r116.setIcon(r035);
        L533:
            r035 = 0;
            goto L534
        L523:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).manage_linked_peers == false) goto L525;
            r034 = true;
            goto L526
        L536:
            if (r12 != ChatRightsEditActivity.m8476$$Nest$fgetstartVoiceChatRow(this.this$0)) goto L552;
            String r539 = LocaleController.getString(R.string.StartVoipChatPermission);
            if (r02 == true) goto L540;
        L542:
            boolean r036 = false;
        L543:
            r116.setTextAndCheck(r539, r036, true);
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).manage_call == true) goto L550;
            if (r43 == true) goto L550;
            int r037 = R.drawable.permission_locked;
        L551:
            r116.setIcon(r037);
        L550:
            r037 = 0;
            goto L551
        L540:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).manage_call == false) goto L542;
            r036 = true;
            goto L543
        L552:
            int r540 = ChatRightsEditActivity.m8452$$Nest$fgetmanageTopicsRow(this.this$0);
            ChatRightsEditActivity r611 = this.this$0;
            if (r12 == r540) goto L555;
            int r541 = ChatRightsEditActivity.m8408$$Nest$fgetaddUsersRow(r611);
            ChatRightsEditActivity r612 = this.this$0;
            if (r12 != r541) goto L627;
            int r542 = ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(r612);
            ChatRightsEditActivity r613 = this.this$0;
            if (r542 != 0) goto L600;
            if (ChatObject.isActionBannedByDefault(ChatRightsEditActivity.m8430$$Nest$fgetcurrentChat(r613), 3) == false) goto L598;
            r116.setTextAndCheck(LocaleController.getString(R.string.EditAdminAddUsers), ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).invite_users, true);
            goto L713
        L598:
            r116.setTextAndCheck(LocaleController.getString(R.string.EditAdminAddUsersViaLink), ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).invite_users, true);
            goto L713
        L600:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(r613) != 1) goto L613;
            String r038 = LocaleController.getString(R.string.UserRestrictionsInviteUsers);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).invite_users == false) goto L604;
        L606:
            boolean r411 = false;
        L607:
            r116.setTextAndCheck(r038, r411, true);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).invite_users == false) goto L610;
            int r039 = R.drawable.permission_locked;
        L611:
            r116.setIcon(r039);
            goto L713
        L610:
            r039 = 0;
            goto L611
        L604:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).invite_users == true) goto L606;
            r411 = true;
            goto L607
        L613:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            String r543 = LocaleController.getString(R.string.EditAdminAddUsersViaLink);
            if (r02 == true) goto L617;
        L619:
            boolean r040 = false;
        L620:
            r116.setTextAndCheck(r543, r040, true);
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).invite_users == true) goto L625;
            if (r43 == true) goto L625;
            int r041 = R.drawable.permission_locked;
        L626:
            r116.setIcon(r041);
        L625:
            r041 = 0;
            goto L626
        L617:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).invite_users == false) goto L619;
            r040 = true;
            goto L620
        L627:
            int r544 = ChatRightsEditActivity.m8455$$Nest$fgetpinMessagesRow(r612);
            ChatRightsEditActivity r614 = this.this$0;
            if (r12 == r544) goto L630;
            int r545 = ChatRightsEditActivity.m8439$$Nest$fgeteditTagsRow(r614);
            ChatRightsEditActivity r615 = this.this$0;
            if (r12 != r545) goto L700;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(r615) != 0) goto L669;
        L684:
            String r546 = LocaleController.getString(R.string.EditAdminEditTags);
            if (r02 == true) goto L687;
        L689:
            boolean r042 = false;
        L690:
            r116.setTextAndCheck(r546, r042, true);
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).manage_ranks == true) goto L697;
            if (r43 == true) goto L697;
            int r043 = R.drawable.permission_locked;
        L698:
            r116.setIcon(r043);
        L697:
            r043 = 0;
            goto L698
        L687:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).manage_ranks == false) goto L689;
            r042 = true;
            goto L690
        L669:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) == 2) goto L684;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 1) goto L713;
            String r044 = LocaleController.getString(R.string.UserRestrictionsEditTags);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).edit_rank == false) goto L676;
        L678:
            boolean r412 = false;
        L679:
            r116.setTextAndCheck(r044, r412, true);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).edit_rank == false) goto L682;
            int r045 = R.drawable.permission_locked;
        L683:
            r116.setIcon(r045);
            goto L713
        L682:
            r045 = 0;
            goto L683
        L676:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).edit_rank == true) goto L678;
            r412 = true;
            goto L679
        L700:
            if (r12 != ChatRightsEditActivity.m8467$$Nest$fgetsendMessagesRow(r615)) goto L713;
            String r046 = LocaleController.getString(R.string.UserRestrictionsSend);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).send_plain == false) goto L704;
        L706:
            boolean r413 = false;
        L707:
            r116.setTextAndCheck(r046, r413, true);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_plain == false) goto L710;
            int r047 = R.drawable.permission_locked;
        L711:
            r116.setIcon(r047);
            goto L713
        L710:
            r047 = 0;
            goto L711
        L704:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).send_plain == true) goto L706;
            r413 = true;
            goto L707
        L630:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(r614) != 0) goto L632;
        L647:
            String r547 = LocaleController.getString(R.string.EditAdminPinMessages);
            if (r02 == false) goto L652;
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).pin_messages == false) goto L652;
        L653:
            boolean r048 = true;
        L655:
            r116.setTextAndCheck(r547, r048, true);
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).pin_messages == true) goto L662;
            if (r43 == true) goto L662;
            int r049 = R.drawable.permission_locked;
        L663:
            r116.setIcon(r049);
        L662:
            r049 = 0;
        L652:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).pin_messages == false) goto L653;
            r048 = false;
            goto L655
        L632:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) == 2) goto L647;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 1) goto L713;
            String r050 = LocaleController.getString(R.string.UserRestrictionsPinMessages);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).pin_messages == false) goto L639;
        L641:
            boolean r414 = false;
        L642:
            r116.setTextAndCheck(r050, r414, true);
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).pin_messages == false) goto L645;
            int r051 = R.drawable.permission_locked;
        L646:
            r116.setIcon(r051);
            goto L713
        L645:
            r051 = 0;
            goto L646
        L639:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).pin_messages == true) goto L641;
            r414 = true;
            goto L642
        L555:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(r611) != 0) goto L564;
            String r415 = LocaleController.getString(R.string.ManageTopicsPermission);
            if (r02 == true) goto L559;
        L561:
            boolean r052 = false;
        L562:
            r116.setTextAndCheck(r415, r052, ChatRightsEditActivity.m8434$$Nest$fgetcurrentUserIsBotGuard(this.this$0));
            goto L713
        L559:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).manage_topics == false) goto L561;
            r052 = true;
            goto L562
        L564:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 1) goto L577;
            String r053 = LocaleController.getString(R.string.CreateTopicsPermission);
            if (ChatRightsEditActivity.m8414$$Nest$fgetbannedRights(this.this$0).manage_topics == false) goto L568;
        L570:
            boolean r416 = false;
        L571:
            r116.setTextAndCheck(r053, r416, ChatRightsEditActivity.m8434$$Nest$fgetcurrentUserIsBotGuard(this.this$0));
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).manage_topics == false) goto L574;
            int r054 = R.drawable.permission_locked;
        L575:
            r116.setIcon(r054);
            goto L713
        L574:
            r054 = 0;
            goto L575
        L568:
            if (ChatRightsEditActivity.m8435$$Nest$fgetdefaultBannedRights(this.this$0).manage_topics == true) goto L570;
            r416 = true;
            goto L571
        L577:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L713;
            String r548 = LocaleController.getString(R.string.ManageTopicsPermission);
            if (r02 == true) goto L581;
        L583:
            boolean r055 = false;
        L584:
            r116.setTextAndCheck(r548, r055, ChatRightsEditActivity.m8434$$Nest$fgetcurrentUserIsBotGuard(this.this$0));
            if (ChatRightsEditActivity.m8454$$Nest$fgetmyAdminRights(this.this$0).manage_topics == true) goto L589;
            if (r43 == true) goto L589;
            int r056 = R.drawable.permission_locked;
        L590:
            r116.setIcon(r056);
        L589:
            r056 = 0;
            goto L590
        L581:
            if (ChatRightsEditActivity.m8409$$Nest$fgetadminRights(this.this$0).manage_topics == false) goto L583;
            r055 = true;
            goto L584
        L221:
            if (ChatRightsEditActivity.m8430$$Nest$fgetcurrentChat(this.this$0).creator == false) goto L223;
            r43 = true;
            goto L224
        L214:
            if (ChatRightsEditActivity.m8411$$Nest$fgetasAdmin(this.this$0) == true) goto L217;
            r02 = false;
            goto L219
        L725:
            HeaderCell r117 = (HeaderCell) r11.itemView;
            ChatRightsEditActivity r057 = this.this$0;
            if (r12 != 2) goto L745;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(r057) != 2) goto L730;
        L742:
            r117.setText(LocaleController.getString(R.string.BotRestrictionsCanDo));
            return;
        L730:
            if (ChatRightsEditActivity.m8433$$Nest$fgetcurrentUser(this.this$0) == null) goto L735;
            if (ChatRightsEditActivity.m8433$$Nest$fgetcurrentUser(this.this$0).bot == true) goto L742;
        L735:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 0) goto L739;
            r117.setText(LocaleController.getString(R.string.EditAdminWhatCanDo));
            return;
        L739:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 1) goto L797;
            r117.setText(LocaleController.getString(R.string.UserRestrictionsCanDo));
            return;
        L797:
            return;
        L745:
            if (r12 != ChatRightsEditActivity.m8457$$Nest$fgetrankHeaderRow(r057)) goto L798;
            r117.setText(LocaleController.getString(R.string.EditAdminRank));
            return;
        L798:
            return;
        L748:
            TextSettingsCell r118 = (TextSettingsCell) r11.itemView;
            if (r12 != ChatRightsEditActivity.m8460$$Nest$fgetremoveAdminRow(this.this$0)) goto L759;
            int r124 = Theme.key_text_RedRegular;
            r118.setTextColor(Theme.getColor(r124));
            r118.setTag(Integer.valueOf(r124));
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 0) goto L755;
            r118.setText(LocaleController.getString(R.string.EditAdminRemoveAdmin), false);
            return;
        L755:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 1) goto L799;
            r118.setText(LocaleController.getString(R.string.UserRestrictionsBlock), false);
            return;
        L799:
            return;
        L759:
            if (r12 != ChatRightsEditActivity.m8477$$Nest$fgettransferOwnerRow(this.this$0)) goto L800;
            int r125 = Theme.key_windowBackgroundWhiteBlackText;
            r118.setTextColor(Theme.getColor(r125));
            r118.setTag(Integer.valueOf(r125));
            if (ChatRightsEditActivity.m8445$$Nest$fgetisChannel(this.this$0) == false) goto L764;
            r118.setText(LocaleController.getString(R.string.EditAdminChannelTransfer), false);
            return;
        L764:
            r118.setText(LocaleController.getString(R.string.EditAdminGroupTransfer), false);
            return;
        L800:
            return;
        L766:
            TextInfoPrivacyCell r119 = (TextInfoPrivacyCell) r11.itemView;
            if (r12 != ChatRightsEditActivity.m8442$$Nest$fgetguardBotInfoRow(this.this$0)) goto L771;
            r119.setText(LocaleController.getString(R.string.EditAdminProcessJoinRequestsInfo));
            return;
        L771:
            if (r12 != ChatRightsEditActivity.m8416$$Nest$fgetcantEditInfoRow(this.this$0)) goto L775;
            r119.setText(LocaleController.getString(R.string.EditAdminCantEdit));
            return;
        L775:
            if (r12 == ChatRightsEditActivity.m8458$$Nest$fgetrankInfoRow(this.this$0)) goto L777;
            return;
        L777:
            if (UserObject.isUserSelf(ChatRightsEditActivity.m8433$$Nest$fgetcurrentUser(this.this$0)) == true) goto L779;
        L781:
            String r126 = LocaleController.getString(R.string.ChannelAdmin);
        L783:
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 0) goto L785;
            String r102 = LocaleController.formatString(R.string.EditAdminRankInfo, new Object[]{r126});
        L786:
            r119.setText(r102);
            return;
        L785:
            r102 = LocaleController.formatString(R.string.EditMemberRankInfo, new Object[]{UserObject.getUserName(ChatRightsEditActivity.m8433$$Nest$fgetcurrentUser(this.this$0))});
            goto L786
        L779:
            if (ChatRightsEditActivity.m8430$$Nest$fgetcurrentChat(this.this$0).creator == false) goto L781;
            r126 = LocaleController.getString(R.string.ChannelCreator);
            goto L783
        L788:
            UserCell2 r1110 = (UserCell2) r11.itemView;
            if (ChatRightsEditActivity.m8432$$Nest$fgetcurrentType(this.this$0) != 2) goto L791;
            String r127 = LocaleController.getString(R.string.Bot);
        L792:
            r1110.setData(ChatRightsEditActivity.m8433$$Nest$fgetcurrentUser(this.this$0), null, r127, 0);
            return;
        L791:
            r127 = null;
            goto L792
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r14, int r15) {
            switch(r15) {
                case 0: goto L25;
                case 1: goto L24;
                case 2: goto L4;
                case 3: goto L23;
                case 4: goto L22;
                case 5: goto L21;
                case 6: goto L20;
                case 7: goto L19;
                case 8: goto L9;
                case 9: goto L22;
                case 10: goto L7;
                case 11: goto L5;
                default: goto L4;
            };
        L4:
            View r142 = new TextSettingsCell(this.mContext);
            r142.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        L27:
            return new RecyclerListView.Holder(r142);
        L5:
            View r0 = new TagEditCell(this.mContext, ChatRightsEditActivity.access$000(this.this$0), -ChatRightsEditActivity.m8428$$Nest$fgetchatId(this.this$0), ChatRightsEditActivity.access$100(this.this$0));
        L6:
            r142 = r0;
            goto L27
        L7:
            CheckBoxCell r152 = new CheckBoxCell(this.mContext, 4, 21, this.this$0.getResourceProvider());
            r152.setPad(1);
            r152.getCheckBoxRound().setDrawBackgroundAsArc(14);
            r152.getCheckBoxRound().setColor(Theme.key_switch2TrackChecked, Theme.key_radioBackground, Theme.key_checkboxCheck);
            r152.setEnabled(true);
            r152.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            UserCell2 r153 = r152;
        L8:
            r142 = r153;
            goto L27
        L9:
            ChatRightsEditActivity.m8482$$Nest$fputaddBotButtonContainer(this.this$0, new FrameLayout(this.mContext));
            FrameLayout r143 = ChatRightsEditActivity.m8405$$Nest$fgetaddBotButtonContainer(this.this$0);
            int r154 = Theme.key_windowBackgroundGray;
            r143.setBackgroundColor(Theme.getColor(r154));
            ChatRightsEditActivity.m8481$$Nest$fputaddBotButton(this.this$0, new FrameLayout(this.mContext));
            ChatRightsEditActivity.m8483$$Nest$fputaddBotButtonText(this.this$0, new AnimatedTextView(this.mContext, true, false, false));
            ChatRightsEditActivity.m8407$$Nest$fgetaddBotButtonText(this.this$0).setTypeface(AndroidUtilities.bold());
            AnimatedTextView r144 = ChatRightsEditActivity.m8407$$Nest$fgetaddBotButtonText(this.this$0);
            if (Theme.isCurrentThemeMonet() == false) goto L12;
            int r2 = Theme.getColor(Theme.key_chats_actionIcon);
        L13:
            r144.setTextColor(r2);
            ChatRightsEditActivity.m8407$$Nest$fgetaddBotButtonText(this.this$0).setTextSize(AndroidUtilities.dp(14.0f));
            ChatRightsEditActivity.m8407$$Nest$fgetaddBotButtonText(this.this$0).setGravity(17);
            AnimatedTextView r145 = ChatRightsEditActivity.m8407$$Nest$fgetaddBotButtonText(this.this$0);
            StringBuilder r4 = new StringBuilder();
            r4.append(LocaleController.getString(R.string.AddBotButton));
            r4.append(" ");
            if (ChatRightsEditActivity.m8411$$Nest$fgetasAdmin(this.this$0) == false) goto L17;
            int r5 = R.string.AddBotButtonAsAdmin;
        L18:
            r4.append(LocaleController.getString(r5));
            r145.setText(r4.toString());
            ChatRightsEditActivity.m8404$$Nest$fgetaddBotButton(this.this$0).addView(ChatRightsEditActivity.m8407$$Nest$fgetaddBotButtonText(this.this$0), LayoutHelper.createFrame(-2, -2, 17));
            ChatRightsEditActivity.m8404$$Nest$fgetaddBotButton(this.this$0).setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{4.0f}));
            ChatRightsEditActivity.m8404$$Nest$fgetaddBotButton(this.this$0).setOnClickListener(new ChatRightsEditActivity$ListAdapter$$ExternalSyntheticLambda0(this));
            ChatRightsEditActivity.m8405$$Nest$fgetaddBotButtonContainer(this.this$0).addView(ChatRightsEditActivity.m8404$$Nest$fgetaddBotButton(this.this$0), LayoutHelper.createFrame(-1, 48.0f, 119, 14.0f, 28.0f, 14.0f, 14.0f));
            ChatRightsEditActivity.m8405$$Nest$fgetaddBotButtonContainer(this.this$0).setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            View r146 = new View(this.mContext);
            r146.setBackgroundColor(Theme.getColor(r154));
            ChatRightsEditActivity.m8405$$Nest$fgetaddBotButtonContainer(this.this$0).setClipChildren(false);
            ChatRightsEditActivity.m8405$$Nest$fgetaddBotButtonContainer(this.this$0).setClipToPadding(false);
            ChatRightsEditActivity.m8405$$Nest$fgetaddBotButtonContainer(this.this$0).addView(r146, LayoutHelper.createFrame(-1, 800.0f, 87, 0.0f, 0.0f, 0.0f, -800.0f));
            r142 = ChatRightsEditActivity.m8405$$Nest$fgetaddBotButtonContainer(this.this$0);
            goto L27
        L17:
            r5 = R.string.AddBotButtonAsMember;
            goto L18
        L12:
            r2 = -1;
            goto L13
        L19:
            ChatRightsEditActivity r147 = this.this$0;
            PollEditTextCell r155 = new PollEditTextCell(this.mContext, null);
            ChatRightsEditActivity.m8485$$Nest$fputrankEditTextCell(r147, r155);
            r155.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r155.addTextWatcher(new AnonymousClass1(this));
            r153 = r155;
            goto L8
        L20:
            r142 = new TextDetailCell(this.mContext);
            r142.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L27
        L21:
            r142 = new ShadowSectionCell(this.mContext);
            goto L27
        L22:
            r142 = new TextCheckCell2(this.mContext);
            r142.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L27
        L23:
            r0 = new HeaderCell(this.mContext, Theme.key_windowBackgroundWhiteBlueHeader, 21, 15, true);
            r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L6
        L24:
            r142 = new TextInfoPrivacyCell(this.mContext);
            goto L27
        L25:
            UserCell2 r156 = new UserCell2(this.mContext, 4, 0);
            r156.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r153 = r156;
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r3) {
            if (r3.getAdapterPosition() != ChatRightsEditActivity.m8457$$Nest$fgetrankHeaderRow(this.this$0)) goto L6;
            ChatRightsEditActivity.m8496$$Nest$msetTextLeft(this.this$0, r3.itemView);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewDetachedFromWindow(RecyclerView.ViewHolder r2) {
            if (r2.getAdapterPosition() == ChatRightsEditActivity.m8459$$Nest$fgetrankRow(this.this$0)) goto L5;
            return;
        L5:
            if (this.this$0.getParentActivity() == null) goto L9;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$1Ogmn3wRNHcTkt8vn66LN6vF3CQ(ChatRightsEditActivity r0, Context r1, View r2, int r3) {
        r0.lambda$createView$8(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$2hstxm7O_upgyn9_MmYoiCJgc-w, reason: not valid java name */
    public static /* synthetic */ boolean m8392$r8$lambda$2hstxm7O_upgyn9_MmYoiCJgcw(ChatRightsEditActivity r0, TLRPC.TL_error r1) {
        return r0.lambda$onDonePressed$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$2xtLqKk6J3kKpmZXIN18lbzWxBg(ChatRightsEditActivity r0, int r1, TimePicker r2, int r3, int r4) {
        r0.lambda$createView$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ boolean $r8$lambda$4XyyDLCe4eyl2MnyglXkJDHF2N4(ChatRightsEditActivity r0, TLRPC.TL_error r1) {
        return r0.lambda$onDonePressed$27(r1);
    }

    public static /* synthetic */ void $r8$lambda$5X_W6c4VIRNNJMM6lg6cVBbQPrk(ChatRightsEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$initTransfer$16(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$9qUVAyn7GdpcrR9vKIs9C1XTn6s(ChatRightsEditActivity r0, TLRPC.TL_error r1) {
        return r0.lambda$onDonePressed$26(r1);
    }

    /* renamed from: $r8$lambda$BoVq-9tmLpsImcITB4tbOOab0Ug, reason: not valid java name */
    public static /* synthetic */ void m8393$r8$lambda$BoVq9tmLpsImcITB4tbOOab0Ug(AlertDialog[] r1) {
        r1[0].dismiss();
    }

    public static /* synthetic */ void $r8$lambda$DBFiCSwYvlmpLCGF4bQcNWCNcQQ(ChatRightsEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$31(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GG719N0cZFAz8q9YVpebIz9PhXQ(ChatRightsEditActivity r0, ValueAnimator r1) {
        r0.lambda$updateAsAdmin$32(r1);
    }

    public static /* synthetic */ void $r8$lambda$GcAVE_zNj8EKbdTvEQCDaSmkvNw(AlertDialog[] r1) {
        r1[0].dismiss();
    }

    public static /* synthetic */ void $r8$lambda$IabuBWSAfJO5GhEgIlHa8AldIDc(ChatRightsEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$30(r1, r2);
    }

    /* renamed from: $r8$lambda$JMHJVaUOsstT-VgZ7uC0PnvDUzg, reason: not valid java name */
    public static /* synthetic */ void m8394$r8$lambda$JMHJVaUOsstTVgZ7uC0PnvDUzg(ChatRightsEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onDonePressed$28(r1, r2);
    }

    /* renamed from: $r8$lambda$KeV7surXzQzPfxOOkNcAv8-4eqc, reason: not valid java name */
    public static /* synthetic */ void m8395$r8$lambda$KeV7surXzQzPfxOOkNcAv84eqc(ChatRightsEditActivity r0) {
        r0.lambda$onDonePressed$25();
    }

    public static /* synthetic */ void $r8$lambda$OblO2tIdaIpVLcgPlrNbn5i3CUs(ChatRightsEditActivity r0, TLRPC.InputCheckPasswordSRP r1, TwoStepVerificationActivity r2, TLRPC.TL_channels_editCreator r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$initTransfer$20(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$Q3jDeh5uayCpzn49ZrZfQ7NJ9q0(DialogInterface r0, int r1) {
    }

    /* renamed from: $r8$lambda$QD8h4Xo4hKTF00W31IvJ_FAa-ek, reason: not valid java name */
    public static /* synthetic */ void m8396$r8$lambda$QD8h4Xo4hKTF00W31IvJ_FAaek(ChatRightsEditActivity r0, ValueAnimator r1) {
        r0.lambda$setLoading$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$RJtSL5gs6lxNNuXbTdRbjwE8Umw(ChatRightsEditActivity r0, long r1) {
        r0.lambda$createView$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$T2v2sK8BEQiwFGDBwvmDrclCybE(ChatRightsEditActivity r0, TLRPC.InputCheckPasswordSRP r1, TwoStepVerificationActivity r2, long r3) {
        r0.lambda$initTransfer$13(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Tz_x3qZkDu42YjJbe8bZ3cMSyBo(ChatRightsEditActivity r0) {
        r0.lambda$onDonePressed$23();
    }

    public static /* synthetic */ void $r8$lambda$UA5mO8i9WUqsQKOhwATQmWi66kw(ChatRightsEditActivity r0, long r1) {
        r0.lambda$createView$6(r1);
    }

    /* renamed from: $r8$lambda$XGcFDpfr-PJk_cEX0SQrzTbp-uI, reason: not valid java name */
    public static /* synthetic */ void m8397$r8$lambda$XGcFDpfrPJk_cEX0SQrzTbpuI(DialogInterface r0, int r1) {
    }

    /* renamed from: $r8$lambda$Yk3YqB56Y-_tgTslIysih4Tr2Us, reason: not valid java name */
    public static /* synthetic */ void m8398$r8$lambda$Yk3YqB56Y_tgTslIysih4Tr2Us(ChatRightsEditActivity r0, TwoStepVerificationActivity r1, TLRPC.InputCheckPasswordSRP r2) {
        r0.lambda$initTransfer$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YwHNmBqv15dRPRcFACCgjfw1pjQ(ChatRightsEditActivity r0, TLRPC.TL_error r1, TLRPC.InputCheckPasswordSRP r2, TwoStepVerificationActivity r3, TLRPC.TL_channels_editCreator r4) {
        r0.lambda$initTransfer$19(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$dC5vGROiA_uJDFBMN-Y_z0hb-sk, reason: not valid java name */
    public static /* synthetic */ void m8399$r8$lambda$dC5vGROiA_uJDFBMNY_z0hbsk(ChatRightsEditActivity r0) {
        r0.lambda$getThemeDescriptions$33();
    }

    public static /* synthetic */ void $r8$lambda$e5VCsc_aLtmnjwBaRMa38ZVsBSs(ChatRightsEditActivity r0, long r1) {
        r0.lambda$onDonePressed$21(r1);
    }

    /* renamed from: $r8$lambda$eqDPLYl0psKwBEaDG6Fix-a_TB0, reason: not valid java name */
    public static /* synthetic */ void m8400$r8$lambda$eqDPLYl0psKwBEaDG6Fixa_TB0(ChatRightsEditActivity r0, TwoStepVerificationActivity r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$initTransfer$18(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nOaNp8Z4vaErOmMBljqRf7RkqQ0(ChatRightsEditActivity r0, BottomSheet.Builder r1, View r2) {
        r0.lambda$createView$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$o42lrx0XDnihZpnC04ZGt3NozRM(ChatRightsEditActivity r0, TLRPC.TL_error r1, TLObject r2, TwoStepVerificationActivity r3) {
        r0.lambda$initTransfer$17(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$pEituV1bo3uDT75AaCf_ucPTy1Q(AlertDialog[] r1) {
        AndroidUtilities.runOnUIThread(new ChatRightsEditActivity$$ExternalSyntheticLambda27(r1));
    }

    /* renamed from: $r8$lambda$sRwA-X79N2tYURPzj522blq1yno, reason: not valid java name */
    public static /* synthetic */ void m8401$r8$lambda$sRwAX79N2tYURPzj522blq1yno(ChatRightsEditActivity r0, DatePicker r1, int r2, int r3, int r4) {
        r0.lambda$createView$2(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$t4zNOhWbAXCOEhqAWgVl5O-w3zY, reason: not valid java name */
    public static /* synthetic */ void m8402$r8$lambda$t4zNOhWbAXCOEhqAWgVl5Ow3zY(ChatRightsEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$initTransfer$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$y1tfPxDugEn2X6v3Y_jd4vvYu38(AlertDialog[] r1) {
        AndroidUtilities.runOnUIThread(new ChatRightsEditActivity$$ExternalSyntheticLambda28(r1));
    }

    public static /* synthetic */ void $r8$lambda$yaM__Z3WcJmnpmuCTHQKPiI1Z7A(ChatRightsEditActivity r0) {
        r0.lambda$onDonePressed$22();
    }

    public static /* synthetic */ void $r8$lambda$zfqikcY5TEg_U6UfwCg8gxW60f0(DatePicker r4, DialogInterface r5) {
        int r52 = r4.getChildCount();
        int r0 = 0;
    L3:
        if (r0 >= r52) goto L5;
        View r1 = r4.getChildAt(r0);
        ViewGroup.LayoutParams r2 = r1.getLayoutParams();
        r2.width = -1;
        r1.setLayoutParams(r2);
        r0 = r0 + 1;
        goto L3
    }

    /* renamed from: -$$Nest$fgetaddAdminsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8403$$Nest$fgetaddAdminsRow(ChatRightsEditActivity r0) {
        return r0.addAdminsRow;
    }

    /* renamed from: -$$Nest$fgetaddBotButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m8404$$Nest$fgetaddBotButton(ChatRightsEditActivity r0) {
        return r0.addBotButton;
    }

    /* renamed from: -$$Nest$fgetaddBotButtonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m8405$$Nest$fgetaddBotButtonContainer(ChatRightsEditActivity r0) {
        return r0.addBotButtonContainer;
    }

    /* renamed from: -$$Nest$fgetaddBotButtonRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8406$$Nest$fgetaddBotButtonRow(ChatRightsEditActivity r0) {
        return r0.addBotButtonRow;
    }

    /* renamed from: -$$Nest$fgetaddBotButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m8407$$Nest$fgetaddBotButtonText(ChatRightsEditActivity r0) {
        return r0.addBotButtonText;
    }

    /* renamed from: -$$Nest$fgetaddUsersRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8408$$Nest$fgetaddUsersRow(ChatRightsEditActivity r0) {
        return r0.addUsersRow;
    }

    /* renamed from: -$$Nest$fgetadminRights, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_chatAdminRights m8409$$Nest$fgetadminRights(ChatRightsEditActivity r0) {
        return r0.adminRights;
    }

    /* renamed from: -$$Nest$fgetanonymousRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8410$$Nest$fgetanonymousRow(ChatRightsEditActivity r0) {
        return r0.anonymousRow;
    }

    /* renamed from: -$$Nest$fgetasAdmin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8411$$Nest$fgetasAdmin(ChatRightsEditActivity r0) {
        return r0.asAdmin;
    }

    /* renamed from: -$$Nest$fgetasAdminT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m8412$$Nest$fgetasAdminT(ChatRightsEditActivity r0) {
        return r0.asAdminT;
    }

    /* renamed from: -$$Nest$fgetbanUsersRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8413$$Nest$fgetbanUsersRow(ChatRightsEditActivity r0) {
        return r0.banUsersRow;
    }

    /* renamed from: -$$Nest$fgetbannedRights, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_chatBannedRights m8414$$Nest$fgetbannedRights(ChatRightsEditActivity r0) {
        return r0.bannedRights;
    }

    /* renamed from: -$$Nest$fgetcanEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8415$$Nest$fgetcanEdit(ChatRightsEditActivity r0) {
        return r0.canEdit;
    }

    /* renamed from: -$$Nest$fgetcantEditInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8416$$Nest$fgetcantEditInfoRow(ChatRightsEditActivity r0) {
        return r0.cantEditInfoRow;
    }

    /* renamed from: -$$Nest$fgetchangeInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8417$$Nest$fgetchangeInfoRow(ChatRightsEditActivity r0) {
        return r0.changeInfoRow;
    }

    /* renamed from: -$$Nest$fgetchannelDeleteMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8418$$Nest$fgetchannelDeleteMessagesRow(ChatRightsEditActivity r0) {
        return r0.channelDeleteMessagesRow;
    }

    /* renamed from: -$$Nest$fgetchannelDeleteStoriesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8419$$Nest$fgetchannelDeleteStoriesRow(ChatRightsEditActivity r0) {
        return r0.channelDeleteStoriesRow;
    }

    /* renamed from: -$$Nest$fgetchannelEditMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8420$$Nest$fgetchannelEditMessagesRow(ChatRightsEditActivity r0) {
        return r0.channelEditMessagesRow;
    }

    /* renamed from: -$$Nest$fgetchannelEditStoriesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8421$$Nest$fgetchannelEditStoriesRow(ChatRightsEditActivity r0) {
        return r0.channelEditStoriesRow;
    }

    /* renamed from: -$$Nest$fgetchannelMessagesExpanded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8422$$Nest$fgetchannelMessagesExpanded(ChatRightsEditActivity r0) {
        return r0.channelMessagesExpanded;
    }

    /* renamed from: -$$Nest$fgetchannelMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8423$$Nest$fgetchannelMessagesRow(ChatRightsEditActivity r0) {
        return r0.channelMessagesRow;
    }

    /* renamed from: -$$Nest$fgetchannelPostMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8424$$Nest$fgetchannelPostMessagesRow(ChatRightsEditActivity r0) {
        return r0.channelPostMessagesRow;
    }

    /* renamed from: -$$Nest$fgetchannelPostStoriesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8425$$Nest$fgetchannelPostStoriesRow(ChatRightsEditActivity r0) {
        return r0.channelPostStoriesRow;
    }

    /* renamed from: -$$Nest$fgetchannelStoriesExpanded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8426$$Nest$fgetchannelStoriesExpanded(ChatRightsEditActivity r0) {
        return r0.channelStoriesExpanded;
    }

    /* renamed from: -$$Nest$fgetchannelStoriesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8427$$Nest$fgetchannelStoriesRow(ChatRightsEditActivity r0) {
        return r0.channelStoriesRow;
    }

    /* renamed from: -$$Nest$fgetchatId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m8428$$Nest$fgetchatId(ChatRightsEditActivity r2) {
        return r2.chatId;
    }

    /* renamed from: -$$Nest$fgetchatInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.ChatFull m8429$$Nest$fgetchatInfo(ChatRightsEditActivity r0) {
        return r0.chatInfo;
    }

    /* renamed from: -$$Nest$fgetcurrentChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m8430$$Nest$fgetcurrentChat(ChatRightsEditActivity r0) {
        return r0.currentChat;
    }

    /* renamed from: -$$Nest$fgetcurrentRank, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m8431$$Nest$fgetcurrentRank(ChatRightsEditActivity r0) {
        return r0.currentRank;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8432$$Nest$fgetcurrentType(ChatRightsEditActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetcurrentUser, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.User m8433$$Nest$fgetcurrentUser(ChatRightsEditActivity r0) {
        return r0.currentUser;
    }

    /* renamed from: -$$Nest$fgetcurrentUserIsBotGuard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8434$$Nest$fgetcurrentUserIsBotGuard(ChatRightsEditActivity r0) {
        return r0.currentUserIsBotGuard;
    }

    /* renamed from: -$$Nest$fgetdefaultBannedRights, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_chatBannedRights m8435$$Nest$fgetdefaultBannedRights(ChatRightsEditActivity r0) {
        return r0.defaultBannedRights;
    }

    /* renamed from: -$$Nest$fgetdeleteMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8436$$Nest$fgetdeleteMessagesRow(ChatRightsEditActivity r0) {
        return r0.deleteMessagesRow;
    }

    /* renamed from: -$$Nest$fgetdoneDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CrossfadeDrawable m8437$$Nest$fgetdoneDrawable(ChatRightsEditActivity r0) {
        return r0.doneDrawable;
    }

    /* renamed from: -$$Nest$fgeteditMesagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8438$$Nest$fgeteditMesagesRow(ChatRightsEditActivity r0) {
        return r0.editMesagesRow;
    }

    /* renamed from: -$$Nest$fgeteditTagsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8439$$Nest$fgeteditTagsRow(ChatRightsEditActivity r0) {
        return r0.editTagsRow;
    }

    /* renamed from: -$$Nest$fgetembedLinksRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8440$$Nest$fgetembedLinksRow(ChatRightsEditActivity r0) {
        return r0.embedLinksRow;
    }

    /* renamed from: -$$Nest$fgetguardBotIdToSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m8441$$Nest$fgetguardBotIdToSet(ChatRightsEditActivity r2) {
        return r2.guardBotIdToSet;
    }

    /* renamed from: -$$Nest$fgetguardBotInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8442$$Nest$fgetguardBotInfoRow(ChatRightsEditActivity r0) {
        return r0.guardBotInfoRow;
    }

    /* renamed from: -$$Nest$fgetguardBotRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8443$$Nest$fgetguardBotRow(ChatRightsEditActivity r0) {
        return r0.guardBotRow;
    }

    /* renamed from: -$$Nest$fgethasGuardBotToSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8444$$Nest$fgethasGuardBotToSet(ChatRightsEditActivity r0) {
        return r0.hasGuardBotToSet;
    }

    /* renamed from: -$$Nest$fgetisChannel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8445$$Nest$fgetisChannel(ChatRightsEditActivity r0) {
        return r0.isChannel;
    }

    /* renamed from: -$$Nest$fgetisCommunity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8446$$Nest$fgetisCommunity(ChatRightsEditActivity r0) {
        return r0.isCommunity;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m8447$$Nest$fgetlistView(ChatRightsEditActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetloading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8448$$Nest$fgetloading(ChatRightsEditActivity r0) {
        return r0.loading;
    }

    /* renamed from: -$$Nest$fgetmanageDirectRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8449$$Nest$fgetmanageDirectRow(ChatRightsEditActivity r0) {
        return r0.manageDirectRow;
    }

    /* renamed from: -$$Nest$fgetmanageLinkedPeersRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8450$$Nest$fgetmanageLinkedPeersRow(ChatRightsEditActivity r0) {
        return r0.manageLinkedPeersRow;
    }

    /* renamed from: -$$Nest$fgetmanageRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8451$$Nest$fgetmanageRow(ChatRightsEditActivity r0) {
        return r0.manageRow;
    }

    /* renamed from: -$$Nest$fgetmanageTopicsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8452$$Nest$fgetmanageTopicsRow(ChatRightsEditActivity r0) {
        return r0.manageTopicsRow;
    }

    /* renamed from: -$$Nest$fgetmanageWelcomeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8453$$Nest$fgetmanageWelcomeRow(ChatRightsEditActivity r0) {
        return r0.manageWelcomeRow;
    }

    /* renamed from: -$$Nest$fgetmyAdminRights, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_chatAdminRights m8454$$Nest$fgetmyAdminRights(ChatRightsEditActivity r0) {
        return r0.myAdminRights;
    }

    /* renamed from: -$$Nest$fgetpinMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8455$$Nest$fgetpinMessagesRow(ChatRightsEditActivity r0) {
        return r0.pinMessagesRow;
    }

    /* renamed from: -$$Nest$fgetpostMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8456$$Nest$fgetpostMessagesRow(ChatRightsEditActivity r0) {
        return r0.postMessagesRow;
    }

    /* renamed from: -$$Nest$fgetrankHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8457$$Nest$fgetrankHeaderRow(ChatRightsEditActivity r0) {
        return r0.rankHeaderRow;
    }

    /* renamed from: -$$Nest$fgetrankInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8458$$Nest$fgetrankInfoRow(ChatRightsEditActivity r0) {
        return r0.rankInfoRow;
    }

    /* renamed from: -$$Nest$fgetrankRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8459$$Nest$fgetrankRow(ChatRightsEditActivity r0) {
        return r0.rankRow;
    }

    /* renamed from: -$$Nest$fgetremoveAdminRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8460$$Nest$fgetremoveAdminRow(ChatRightsEditActivity r0) {
        return r0.removeAdminRow;
    }

    /* renamed from: -$$Nest$fgetremoveAdminShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8461$$Nest$fgetremoveAdminShadowRow(ChatRightsEditActivity r0) {
        return r0.removeAdminShadowRow;
    }

    /* renamed from: -$$Nest$fgetrightsShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8462$$Nest$fgetrightsShadowRow(ChatRightsEditActivity r0) {
        return r0.rightsShadowRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8463$$Nest$fgetrowCount(ChatRightsEditActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsendFilesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8464$$Nest$fgetsendFilesRow(ChatRightsEditActivity r0) {
        return r0.sendFilesRow;
    }

    /* renamed from: -$$Nest$fgetsendMediaExpanded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8465$$Nest$fgetsendMediaExpanded(ChatRightsEditActivity r0) {
        return r0.sendMediaExpanded;
    }

    /* renamed from: -$$Nest$fgetsendMediaRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8466$$Nest$fgetsendMediaRow(ChatRightsEditActivity r0) {
        return r0.sendMediaRow;
    }

    /* renamed from: -$$Nest$fgetsendMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8467$$Nest$fgetsendMessagesRow(ChatRightsEditActivity r0) {
        return r0.sendMessagesRow;
    }

    /* renamed from: -$$Nest$fgetsendMusicRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8468$$Nest$fgetsendMusicRow(ChatRightsEditActivity r0) {
        return r0.sendMusicRow;
    }

    /* renamed from: -$$Nest$fgetsendPhotosRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8469$$Nest$fgetsendPhotosRow(ChatRightsEditActivity r0) {
        return r0.sendPhotosRow;
    }

    /* renamed from: -$$Nest$fgetsendPollsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8470$$Nest$fgetsendPollsRow(ChatRightsEditActivity r0) {
        return r0.sendPollsRow;
    }

    /* renamed from: -$$Nest$fgetsendReactionsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8471$$Nest$fgetsendReactionsRow(ChatRightsEditActivity r0) {
        return r0.sendReactionsRow;
    }

    /* renamed from: -$$Nest$fgetsendRoundRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8472$$Nest$fgetsendRoundRow(ChatRightsEditActivity r0) {
        return r0.sendRoundRow;
    }

    /* renamed from: -$$Nest$fgetsendStickersRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8473$$Nest$fgetsendStickersRow(ChatRightsEditActivity r0) {
        return r0.sendStickersRow;
    }

    /* renamed from: -$$Nest$fgetsendVideosRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8474$$Nest$fgetsendVideosRow(ChatRightsEditActivity r0) {
        return r0.sendVideosRow;
    }

    /* renamed from: -$$Nest$fgetsendVoiceRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8475$$Nest$fgetsendVoiceRow(ChatRightsEditActivity r0) {
        return r0.sendVoiceRow;
    }

    /* renamed from: -$$Nest$fgetstartVoiceChatRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8476$$Nest$fgetstartVoiceChatRow(ChatRightsEditActivity r0) {
        return r0.startVoiceChatRow;
    }

    /* renamed from: -$$Nest$fgettransferOwnerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8477$$Nest$fgettransferOwnerRow(ChatRightsEditActivity r0) {
        return r0.transferOwnerRow;
    }

    /* renamed from: -$$Nest$fgettransferOwnerShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8478$$Nest$fgettransferOwnerShadowRow(ChatRightsEditActivity r0) {
        return r0.transferOwnerShadowRow;
    }

    /* renamed from: -$$Nest$fgetuntilDateRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8479$$Nest$fgetuntilDateRow(ChatRightsEditActivity r0) {
        return r0.untilDateRow;
    }

    /* renamed from: -$$Nest$fgetuntilSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8480$$Nest$fgetuntilSectionRow(ChatRightsEditActivity r0) {
        return r0.untilSectionRow;
    }

    /* renamed from: -$$Nest$fputaddBotButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8481$$Nest$fputaddBotButton(ChatRightsEditActivity r0, FrameLayout r1) {
        r0.addBotButton = r1;
    }

    /* renamed from: -$$Nest$fputaddBotButtonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8482$$Nest$fputaddBotButtonContainer(ChatRightsEditActivity r0, FrameLayout r1) {
        r0.addBotButtonContainer = r1;
    }

    /* renamed from: -$$Nest$fputaddBotButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8483$$Nest$fputaddBotButtonText(ChatRightsEditActivity r0, AnimatedTextView r1) {
        r0.addBotButtonText = r1;
    }

    /* renamed from: -$$Nest$fputcurrentRank, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8484$$Nest$fputcurrentRank(ChatRightsEditActivity r0, String r1) {
        r0.currentRank = r1;
    }

    /* renamed from: -$$Nest$fputrankEditTextCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8485$$Nest$fputrankEditTextCell(ChatRightsEditActivity r0, PollEditTextCell r1) {
        r0.rankEditTextCell = r1;
    }

    /* renamed from: -$$Nest$mallDefaultMediaBanned, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8486$$Nest$mallDefaultMediaBanned(ChatRightsEditActivity r0) {
        return r0.allDefaultMediaBanned();
    }

    /* renamed from: -$$Nest$mcheckDiscard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8487$$Nest$mcheckDiscard(ChatRightsEditActivity r0, boolean r1) {
        return r0.checkDiscard(r1);
    }

    /* renamed from: -$$Nest$mgetChannelMessagesSelectedCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8488$$Nest$mgetChannelMessagesSelectedCount(ChatRightsEditActivity r0) {
        return r0.getChannelMessagesSelectedCount();
    }

    /* renamed from: -$$Nest$mgetChannelStoriesSelectedCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8489$$Nest$mgetChannelStoriesSelectedCount(ChatRightsEditActivity r0) {
        return r0.getChannelStoriesSelectedCount();
    }

    /* renamed from: -$$Nest$mgetSendMediaSelectedCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8490$$Nest$mgetSendMediaSelectedCount(ChatRightsEditActivity r0) {
        return r0.getSendMediaSelectedCount();
    }

    /* renamed from: -$$Nest$misExpandableSendMediaRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8491$$Nest$misExpandableSendMediaRow(ChatRightsEditActivity r0, int r1) {
        return r0.isExpandableSendMediaRow(r1);
    }

    /* renamed from: -$$Nest$monDonePressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8492$$Nest$monDonePressed(ChatRightsEditActivity r0) {
        r0.onDonePressed();
    }

    /* renamed from: -$$Nest$msetChannelMessagesEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8493$$Nest$msetChannelMessagesEnabled(ChatRightsEditActivity r0, boolean r1) {
        r0.setChannelMessagesEnabled(r1);
    }

    /* renamed from: -$$Nest$msetChannelStoriesEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8494$$Nest$msetChannelStoriesEnabled(ChatRightsEditActivity r0, boolean r1) {
        r0.setChannelStoriesEnabled(r1);
    }

    /* renamed from: -$$Nest$msetSendMediaEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8495$$Nest$msetSendMediaEnabled(ChatRightsEditActivity r0, boolean r1) {
        r0.setSendMediaEnabled(r1);
    }

    /* renamed from: -$$Nest$msetTextLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8496$$Nest$msetTextLeft(ChatRightsEditActivity r0, View r1) {
        r0.setTextLeft(r1);
    }

    public ChatRightsEditActivity(long r17, long r19, TLRPC.TL_chatAdminRights r21, TLRPC.TL_chatBannedRights r22, TLRPC.TL_chatBannedRights r23, String r24, int r25, boolean r26, boolean r27, String r28) {
        TLRPC.TL_chatAdminRights r1 = r21;
        this.loading = false;
        float r7 = 0.0f;
        this.asAdminT = 0.0f;
        this.asAdmin = false;
        this.initialAsAdmin = false;
        String r8 = _UrlKt.FRAGMENT_ENCODE_SET;
        this.currentBannedRights = _UrlKt.FRAGMENT_ENCODE_SET;
        this.closingKeyboardAfterFinish = false;
        this.isAddingNew = r27;
        this.chatId = r19;
        this.currentUser = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r17));
        this.currentType = r25;
        this.canEdit = r26;
        boolean r9 = true;
        boolean r5 = !r26;
        this.channelStoriesExpanded = r5;
        this.channelMessagesExpanded = r5;
        this.botHash = r28;
        this.currentChat = getMessagesController().getChat(Long.valueOf(this.chatId));
        this.chatInfo = getMessagesController().getChatFull(this.chatId);
        TLRPC.User r52 = this.currentUser;
        if (r52 != null) goto L5;
    L7:
        boolean r53 = false;
    L8:
        this.currentUserIsBotGuard = r53;
        if (r24 == null) goto L12;
        r8 = r24;
    L12:
        this.currentRank = r8;
        this.initialRank = r8;
        this.isCommunity = ChatObject.isCommunity(this.currentChat);
        TLRPC.Chat r54 = this.currentChat;
        if (r54 == null) goto L22;
        if (ChatObject.isChannel(r54) == true) goto L17;
    L19:
        boolean r55 = false;
    L20:
        this.isChannel = r55;
        this.isForum = ChatObject.isForum(this.currentChat);
        this.myAdminRights = this.currentChat.admin_rights;
        goto L22
    L17:
        if (this.currentChat.megagroup == true) goto L19;
        r55 = true;
    L22:
        if (this.myAdminRights == null) goto L24;
    L33:
        if (r25 == 0) goto L118;
        if (r25 == 2) goto L118;
        if (r25 != 1) goto L117;
        this.defaultBannedRights = r22;
        if (r22 != null) goto L40;
        TLRPC.TL_chatBannedRights r12 = new TLRPC.TL_chatBannedRights();
        this.defaultBannedRights = r12;
        r12.view_messages = false;
        r12.send_media = false;
        r12.send_messages = false;
        r12.embed_links = false;
        r12.send_stickers = false;
        r12.send_gifs = false;
        r12.send_games = false;
        r12.send_inline = false;
        r12.send_polls = false;
        r12.invite_users = false;
        r12.change_info = false;
        r12.pin_messages = false;
        r12.manage_topics = false;
        r12.send_plain = false;
        r12.send_videos = false;
        r12.send_photos = false;
        r12.send_audios = false;
        r12.send_docs = false;
        r12.send_voices = false;
        r12.send_roundvideos = false;
        r12.edit_rank = false;
        r12.send_reactions = false;
    L40:
        TLRPC.TL_chatBannedRights r13 = new TLRPC.TL_chatBannedRights();
        this.bannedRights = r13;
        if (r23 != null) goto L43;
        r13.view_messages = false;
        r13.send_media = false;
        r13.send_messages = false;
        r13.embed_links = false;
        r13.send_stickers = false;
        r13.send_gifs = false;
        r13.send_games = false;
        r13.send_inline = false;
        r13.send_polls = false;
        r13.invite_users = false;
        r13.change_info = false;
        r13.pin_messages = false;
        r13.manage_topics = false;
        r13.edit_rank = false;
        r13.send_reactions = false;
    L44:
        TLRPC.TL_chatBannedRights r2 = this.defaultBannedRights;
        if (r2.view_messages == false) goto L48;
        r13.view_messages = true;
    L48:
        if (r2.send_messages == false) goto L51;
        r13.send_messages = true;
    L51:
        if (r2.send_media == false) goto L54;
        r13.send_media = true;
    L54:
        if (r2.send_stickers == false) goto L57;
        r13.send_stickers = true;
    L57:
        if (r2.send_gifs == false) goto L60;
        r13.send_gifs = true;
    L60:
        if (r2.send_games == false) goto L63;
        r13.send_games = true;
    L63:
        if (r2.send_inline == false) goto L66;
        r13.send_inline = true;
    L66:
        if (r2.embed_links == false) goto L69;
        r13.embed_links = true;
    L69:
        if (r2.send_polls == false) goto L72;
        r13.send_polls = true;
    L72:
        if (r2.invite_users == false) goto L75;
        r13.invite_users = true;
    L75:
        if (r2.change_info == false) goto L78;
        r13.change_info = true;
    L78:
        if (r2.pin_messages == false) goto L81;
        r13.pin_messages = true;
    L81:
        if (r2.edit_rank == false) goto L84;
        r13.edit_rank = true;
    L84:
        if (r2.send_reactions == false) goto L87;
        r13.send_reactions = true;
    L87:
        if (r2.manage_topics == false) goto L90;
        r13.manage_topics = true;
    L90:
        if (r2.send_photos == false) goto L93;
        r13.send_photos = true;
    L93:
        if (r2.send_videos == false) goto L96;
        r13.send_videos = true;
    L96:
        if (r2.send_audios == false) goto L99;
        r13.send_audios = true;
    L99:
        if (r2.send_docs == false) goto L102;
        r13.send_docs = true;
    L102:
        if (r2.send_voices == false) goto L105;
        r13.send_voices = true;
    L105:
        if (r2.send_roundvideos == false) goto L108;
        r13.send_roundvideos = true;
    L108:
        if (r2.send_plain == false) goto L110;
        r13.send_plain = true;
    L110:
        this.currentBannedRights = ChatObject.getBannedRightsString(r13);
        if (r23 != null) goto L113;
    L116:
        this.initialIsSet = r9;
        goto L117
    L113:
        if (r23.view_messages == false) goto L116;
        r9 = false;
        goto L116
    L43:
        r13.view_messages = r23.view_messages;
        r13.send_messages = r23.send_messages;
        r13.send_media = r23.send_media;
        r13.send_stickers = r23.send_stickers;
        r13.send_gifs = r23.send_gifs;
        r13.send_games = r23.send_games;
        r13.send_inline = r23.send_inline;
        r13.embed_links = r23.embed_links;
        r13.send_polls = r23.send_polls;
        r13.invite_users = r23.invite_users;
        r13.change_info = r23.change_info;
        r13.pin_messages = r23.pin_messages;
        r13.until_date = r23.until_date;
        r13.manage_topics = r23.manage_topics;
        r13.send_photos = r23.send_photos;
        r13.send_videos = r23.send_videos;
        r13.send_roundvideos = r23.send_roundvideos;
        r13.send_audios = r23.send_audios;
        r13.send_voices = r23.send_voices;
        r13.send_docs = r23.send_docs;
        r13.send_plain = r23.send_plain;
        r13.edit_rank = r23.edit_rank;
        r13.send_reactions = r23.send_reactions;
    L117:
        boolean r14 = false;
    L312:
        updateRows(r14);
        return;
    L118:
        if (r25 != 2) goto L255;
        TLRPC.UserFull r29 = getMessagesController().getUserFull(r17);
        if (r29 == null) goto L255;
        if (this.isChannel == false) goto L124;
        TLRPC.TL_chatAdminRights r210 = r29.bot_broadcast_admin_rights;
    L125:
        if (r210 == null) goto L255;
        if (r1 != null) goto L129;
        r1 = r210;
        goto L255
    L129:
        if (r1.ban_users == false) goto L131;
    L134:
        boolean r3 = true;
    L135:
        r1.ban_users = r3;
        if (r1.add_admins == false) goto L138;
    L141:
        boolean r32 = true;
    L142:
        r1.add_admins = r32;
        if (r1.post_messages == false) goto L145;
    L148:
        boolean r33 = true;
    L149:
        r1.post_messages = r33;
        if (r1.pin_messages == false) goto L152;
    L155:
        boolean r34 = true;
    L156:
        r1.pin_messages = r34;
        if (r1.manage_ranks == false) goto L159;
    L162:
        boolean r35 = true;
    L163:
        r1.manage_ranks = r35;
        if (r1.delete_messages == false) goto L166;
    L169:
        boolean r36 = true;
    L170:
        r1.delete_messages = r36;
        if (r1.change_info == false) goto L173;
    L176:
        boolean r37 = true;
    L177:
        r1.change_info = r37;
        if (r1.anonymous == false) goto L180;
    L183:
        boolean r38 = true;
    L184:
        r1.anonymous = r38;
        if (r1.edit_messages == false) goto L187;
    L190:
        boolean r39 = true;
    L191:
        r1.edit_messages = r39;
        if (r1.manage_call == false) goto L194;
    L197:
        boolean r310 = true;
    L198:
        r1.manage_call = r310;
        if (r1.manage_topics == false) goto L201;
    L204:
        boolean r311 = true;
    L205:
        r1.manage_topics = r311;
        if (r1.post_stories == false) goto L208;
    L211:
        boolean r312 = true;
    L212:
        r1.post_stories = r312;
        if (r1.edit_stories == false) goto L215;
    L218:
        boolean r313 = true;
    L219:
        r1.edit_stories = r313;
        if (r1.delete_stories == false) goto L222;
    L225:
        boolean r314 = true;
    L226:
        r1.delete_stories = r314;
        if (r1.manage_direct_messages == false) goto L229;
    L232:
        boolean r315 = true;
    L233:
        r1.manage_direct_messages = r315;
        if (r1.manage_welcome_messages == false) goto L236;
    L239:
        boolean r316 = true;
    L240:
        r1.manage_welcome_messages = r316;
        if (r1.manage_linked_peers == false) goto L243;
    L246:
        boolean r317 = true;
    L247:
        r1.manage_linked_peers = r317;
        if (r1.other == false) goto L250;
    L253:
        boolean r211 = true;
    L254:
        r1.other = r211;
        goto L255
    L250:
        if (r210.other == true) goto L253;
        r211 = false;
        goto L254
    L243:
        if (r210.manage_linked_peers == true) goto L246;
        r317 = false;
        goto L247
    L236:
        if (r210.manage_welcome_messages == true) goto L239;
        r316 = false;
        goto L240
    L229:
        if (r210.manage_direct_messages == true) goto L232;
        r315 = false;
        goto L233
    L222:
        if (r210.delete_stories == true) goto L225;
        r314 = false;
        goto L226
    L215:
        if (r210.edit_stories == true) goto L218;
        r313 = false;
        goto L219
    L208:
        if (r210.post_stories == true) goto L211;
        r312 = false;
        goto L212
    L201:
        if (r210.manage_topics == true) goto L204;
        r311 = false;
        goto L205
    L194:
        if (r210.manage_call == true) goto L197;
        r310 = false;
        goto L198
    L187:
        if (r210.edit_messages == true) goto L190;
        r39 = false;
        goto L191
    L180:
        if (r210.anonymous == true) goto L183;
        r38 = false;
        goto L184
    L173:
        if (r210.change_info == true) goto L176;
        r37 = false;
        goto L177
    L166:
        if (r210.delete_messages == true) goto L169;
        r36 = false;
        goto L170
    L159:
        if (r210.manage_ranks == true) goto L162;
        r35 = false;
        goto L163
    L152:
        if (r210.pin_messages == true) goto L155;
        r34 = false;
        goto L156
    L145:
        if (r210.post_messages == true) goto L148;
        r33 = false;
        goto L149
    L138:
        if (r210.add_admins == true) goto L141;
        r32 = false;
        goto L142
    L131:
        if (r210.ban_users == true) goto L134;
        r3 = false;
        goto L135
    L124:
        r210 = r29.bot_group_admin_rights;
    L255:
        if (r1 != null) goto L263;
        this.initialAsAdmin = false;
        if (r25 != 2) goto L262;
        this.adminRights = emptyAdminRights(false);
        boolean r15 = this.isChannel;
        this.asAdmin = r15;
        if (r15 == false) goto L261;
        r7 = 1.0f;
    L261:
        this.asAdminT = r7;
        this.initialIsSet = false;
    L296:
        TLRPC.Chat r16 = this.currentChat;
        if (r16 == null) goto L300;
        this.defaultBannedRights = r16.default_banned_rights;
    L300:
        if (this.defaultBannedRights != null) goto L302;
        TLRPC.TL_chatBannedRights r18 = new TLRPC.TL_chatBannedRights();
        this.defaultBannedRights = r18;
        r18.view_messages = false;
        r18.send_media = false;
        r18.send_messages = false;
        r18.embed_links = false;
        r18.send_stickers = false;
        r18.send_gifs = false;
        r18.send_games = false;
        r18.send_inline = false;
        r18.send_polls = false;
        r18.invite_users = false;
        r18.change_info = false;
        r18.pin_messages = false;
        r18.manage_topics = false;
        r18.send_plain = false;
        r18.send_videos = false;
        r18.send_photos = false;
        r18.send_audios = false;
        r18.send_docs = false;
        r18.send_voices = false;
        r18.send_roundvideos = false;
        r18.edit_rank = false;
        r18.send_reactions = false;
    L302:
        TLRPC.TL_chatBannedRights r110 = this.defaultBannedRights;
        if (r110.change_info == false) goto L305;
    L307:
        boolean r318 = true;
    L309:
        if (r110.pin_messages == true) goto L311;
        this.adminRights.pin_messages = r318;
    L311:
        r14 = false;
        goto L312
    L305:
        if (this.isChannel == true) goto L307;
        r318 = true;
        this.adminRights.change_info = true;
        goto L309
    L262:
        TLRPC.TL_chatAdminRights r111 = new TLRPC.TL_chatAdminRights();
        this.adminRights = r111;
        TLRPC.TL_chatAdminRights r212 = this.myAdminRights;
        r111.change_info = r212.change_info;
        r111.post_messages = r212.post_messages;
        r111.edit_messages = r212.edit_messages;
        r111.delete_messages = r212.delete_messages;
        r111.manage_call = r212.manage_call;
        r111.ban_users = r212.ban_users;
        r111.invite_users = r212.invite_users;
        r111.pin_messages = r212.pin_messages;
        r111.manage_ranks = r212.manage_ranks;
        r111.manage_topics = r212.manage_topics;
        r111.post_stories = r212.post_stories;
        r111.edit_stories = r212.edit_stories;
        r111.delete_stories = r212.delete_stories;
        r111.manage_direct_messages = r212.manage_direct_messages;
        r111.manage_welcome_messages = r212.manage_welcome_messages;
        r111.manage_linked_peers = r212.manage_linked_peers;
        r111.other = r212.other;
        this.initialIsSet = false;
        goto L296
    L263:
        this.initialAsAdmin = true;
        TLRPC.TL_chatAdminRights r319 = new TLRPC.TL_chatAdminRights();
        this.adminRights = r319;
        boolean r56 = r1.change_info;
        r319.change_info = r56;
        boolean r10 = r1.post_messages;
        r319.post_messages = r10;
        boolean r11 = r1.edit_messages;
        r319.edit_messages = r11;
        boolean r122 = r1.delete_messages;
        r319.delete_messages = r122;
        boolean r132 = r1.manage_call;
        r319.manage_call = r132;
        boolean r142 = r1.ban_users;
        r319.ban_users = r142;
        boolean r152 = r1.invite_users;
        r319.invite_users = r152;
        boolean r213 = r1.pin_messages;
        r319.pin_messages = r213;
        boolean r72 = r1.manage_ranks;
        r319.manage_ranks = r72;
        boolean r92 = r1.manage_topics;
        r319.manage_topics = r92;
        r319.post_stories = r1.post_stories;
        r319.edit_stories = r1.edit_stories;
        r319.delete_stories = r1.delete_stories;
        boolean r6 = r1.manage_direct_messages;
        r319.manage_direct_messages = r6;
        boolean r82 = r1.manage_welcome_messages;
        r319.manage_welcome_messages = r82;
        boolean r214 = r1.manage_linked_peers;
        r319.manage_linked_peers = r214;
        boolean r215 = r1.add_admins;
        r319.add_admins = r215;
        boolean r216 = r1.anonymous;
        r319.anonymous = r216;
        boolean r112 = r1.other;
        r319.other = r112;
        if (r56 == true) goto L282;
        if (r10 == true) goto L282;
        if (r6 == true) goto L282;
        if (r82 == true) goto L282;
        if (r11 == true) goto L282;
        if (r122 == true) goto L282;
        if (r142 == true) goto L282;
        if (r152 == true) goto L282;
        if (r214 == true) goto L282;
        if (r213 == true) goto L282;
        if (r72 == true) goto L282;
        if (r215 == true) goto L282;
        if (r132 == true) goto L282;
        if (r216 == true) goto L282;
        if (r92 == true) goto L282;
        if (r112 == true) goto L282;
        boolean r113 = false;
    L283:
        this.initialIsSet = r113;
        if (r25 != 2) goto L296;
        if (this.isChannel == true) goto L290;
        if (r113 == true) goto L290;
        boolean r114 = false;
    L291:
        this.asAdmin = r114;
        if (r114 == false) goto L294;
        float r73 = 1.0f;
    L295:
        this.asAdminT = r73;
        this.initialIsSet = false;
        goto L296
    L294:
        r73 = 0.0f;
    L290:
        r114 = true;
    L282:
        r113 = true;
        goto L283
    L24:
        if (this.currentType != 2) goto L31;
        TLRPC.Chat r57 = this.currentChat;
        if (r57 != null) goto L28;
    L30:
        boolean r58 = false;
    L32:
        this.myAdminRights = emptyAdminRights(r58);
        goto L33
    L28:
        if (r57.creator == false) goto L30;
    L31:
        r58 = true;
        goto L32
    L5:
        if (r52.bot_guard == false) goto L7;
        r53 = true;
        goto L8
    }

    public static /* synthetic */ int access$000(ChatRightsEditActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(ChatRightsEditActivity r0) {
        return r0.resourceProvider;
    }

    private boolean allDefaultMediaBanned() {
        TLRPC.TL_chatBannedRights r1 = this.defaultBannedRights;
        if (r1.send_photos == true) goto L5;
        return false;
    L5:
        if (r1.send_videos == true) goto L7;
        return false;
    L7:
        if (r1.send_stickers == true) goto L9;
        return false;
    L9:
        if (r1.send_audios == true) goto L11;
        return false;
    L11:
        if (r1.send_docs == true) goto L13;
        return false;
    L13:
        if (r1.send_voices == true) goto L15;
        return false;
    L15:
        if (r1.send_roundvideos == true) goto L17;
        return false;
    L17:
        if (r1.embed_links == true) goto L19;
        return false;
    L19:
        if (r1.send_polls == true) goto L21;
        return false;
    L21:
        if (r1.send_reactions == false) goto L34;
        return true;
    L34:
        return false;
    }

    private boolean checkDiscard(boolean r4) {
        int r0 = this.currentType;
        if (r0 != 2) goto L5;
        return true;
    L5:
        if (r0 != 1) goto L8;
        boolean r02 = this.currentBannedRights.equals(ChatObject.getBannedRightsString(this.bannedRights));
    L9:
        if ((!r02) == false) goto L14;
        if (r4 == false) goto L15;
        AlertDialog.Builder r42 = new AlertDialog.Builder(getParentActivity());
        r42.setTitle(LocaleController.getString(R.string.UserRestrictionsApplyChanges));
        r42.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("UserRestrictionsApplyChangesText", R.string.UserRestrictionsApplyChangesText, new Object[]{MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.chatId)).title})));
        r42.setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new ChatRightsEditActivity$$ExternalSyntheticLambda0(this));
        r42.setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new ChatRightsEditActivity$$ExternalSyntheticLambda1(this));
        showDialog(r42.create());
        return false;
    L15:
        return false;
    L14:
        return true;
    L8:
        r02 = this.initialRank.equals(this.currentRank);
        goto L9
    }

    private void checkGuardBotRow() {
        int r0 = this.guardBotRow;
        if (r0 < 0) goto L22;
        TextCheckCell2 r02 = (TextCheckCell2) this.linearLayoutManager.findViewByPosition(r0);
        if (r02 == null) goto L20;
        TLRPC.ChatFull r1 = this.chatInfo;
        if (r1 == null) goto L17;
        TLRPC.User r2 = this.currentUser;
        if (r2 == null) goto L17;
        if (this.hasGuardBotToSet == false) goto L13;
        long r3 = this.guardBotIdToSet;
    L15:
        if (r3 != r2.id) goto L17;
        boolean r5 = true;
    L18:
        r02.setChecked(r5);
        return;
    L13:
        r3 = r1.guard_bot_id;
    L17:
        r5 = false;
        goto L18
    L20:
        this.listViewAdapter.notifyItemChanged(this.guardBotRow);
        return;
    }

    public static TLRPC.TL_chatAdminRights emptyAdminRights(boolean r1) {
        TLRPC.TL_chatAdminRights r0 = new TLRPC.TL_chatAdminRights();
        r0.manage_linked_peers = r1;
        r0.manage_ranks = r1;
        r0.manage_welcome_messages = r1;
        r0.manage_direct_messages = r1;
        r0.delete_stories = r1;
        r0.edit_stories = r1;
        r0.post_stories = r1;
        r0.manage_topics = r1;
        r0.manage_call = r1;
        r0.add_admins = r1;
        r0.pin_messages = r1;
        r0.invite_users = r1;
        r0.ban_users = r1;
        r0.delete_messages = r1;
        r0.edit_messages = r1;
        r0.post_messages = r1;
        r0.change_info = r1;
        return r0;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    private int getChannelMessagesSelectedCount() {
        TLRPC.TL_chatAdminRights r2 = this.adminRights;
        ?? r0 = r2.post_messages;
        int r02 = r0;
        if (r2.edit_messages == false) goto L6;
        r02 = r0 + 1;
    L6:
        if (r2.delete_messages == true) goto L8;
        return r02;
    L8:
        return r02 + 1;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    private int getChannelStoriesSelectedCount() {
        TLRPC.TL_chatAdminRights r2 = this.adminRights;
        ?? r0 = r2.post_stories;
        int r02 = r0;
        if (r2.edit_stories == false) goto L6;
        r02 = r0 + 1;
    L6:
        if (r2.delete_stories == true) goto L8;
        return r02;
    L8:
        return r02 + 1;
    }

    private int getSendMediaSelectedCount() {
        TLRPC.TL_chatBannedRights r0 = this.bannedRights;
        if (r0.send_photos == false) goto L5;
    L7:
        int r1 = 0;
    L9:
        if (r0.send_videos == true) goto L14;
        if (this.defaultBannedRights.send_videos == true) goto L14;
        r1 = r1 + 1;
    L14:
        if (r0.send_stickers == true) goto L19;
        if (this.defaultBannedRights.send_stickers == true) goto L19;
        r1 = r1 + 1;
    L19:
        if (r0.send_audios == true) goto L24;
        if (this.defaultBannedRights.send_audios == true) goto L24;
        r1 = r1 + 1;
    L24:
        if (r0.send_docs == true) goto L29;
        if (this.defaultBannedRights.send_docs == true) goto L29;
        r1 = r1 + 1;
    L29:
        if (r0.send_voices == true) goto L34;
        if (this.defaultBannedRights.send_voices == true) goto L34;
        r1 = r1 + 1;
    L34:
        if (r0.send_roundvideos == true) goto L39;
        if (this.defaultBannedRights.send_roundvideos == true) goto L39;
        r1 = r1 + 1;
    L39:
        if (r0.embed_links == true) goto L48;
        TLRPC.TL_chatBannedRights r3 = this.defaultBannedRights;
        if (r3.embed_links == true) goto L48;
        if (r0.send_plain == true) goto L48;
        if (r3.send_plain == true) goto L48;
        r1 = r1 + 1;
    L48:
        if (r0.send_polls == true) goto L53;
        if (this.defaultBannedRights.send_polls == true) goto L53;
        r1 = r1 + 1;
    L53:
        if (r0.send_reactions == false) goto L55;
        return r1;
    L55:
        if (this.defaultBannedRights.send_reactions == false) goto L57;
        return r1;
    L57:
        return r1 + 1;
    L5:
        if (this.defaultBannedRights.send_photos == true) goto L7;
        r1 = 1;
        goto L9
    }

    private boolean hasAllAdminRights() {
        boolean r0 = this.isChannel;
        TLRPC.TL_chatAdminRights r1 = this.adminRights;
        if (r0 == false) goto L31;
        if (r1.change_info == true) goto L7;
    L29:
        return false;
    L7:
        if (r1.post_messages == false) goto L29;
        if (r1.edit_messages == false) goto L29;
        if (r1.delete_messages == false) goto L29;
        if (r1.invite_users == false) goto L29;
        if (r1.add_admins == false) goto L29;
        if (r1.manage_call == false) goto L29;
        if (r1.post_stories == false) goto L29;
        if (r1.edit_stories == false) goto L29;
        if (r1.delete_stories == false) goto L29;
        if (r1.manage_direct_messages == false) goto L29;
        if (r1.manage_welcome_messages == false) goto L29;
        return true;
    L31:
        if (r1.change_info == true) goto L33;
    L53:
        return false;
    L33:
        if (r1.delete_messages == false) goto L53;
        if (r1.ban_users == false) goto L53;
        if (r1.invite_users == false) goto L53;
        if (r1.pin_messages == false) goto L53;
        if (r1.manage_ranks == false) goto L53;
        if (r1.add_admins == false) goto L53;
        if (r1.manage_call == false) goto L53;
        if (this.isForum == false) goto L51;
        if (r1.manage_topics == false) goto L53;
    L51:
        if (r1.manage_welcome_messages == false) goto L53;
        return true;
    }

    private void initTransfer(TLRPC.InputCheckPasswordSRP r8, TwoStepVerificationActivity r9) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        if (r8 != null) goto L7;
    L10:
        TLRPC.TL_channels_editCreator r7 = new TLRPC.TL_channels_editCreator();
        if (ChatObject.isChannel(this.currentChat) == false) goto L13;
        TLRPC.TL_inputChannel r0 = new TLRPC.TL_inputChannel();
        r7.channel = r0;
        TLRPC.Chat r1 = this.currentChat;
        r0.channel_id = r1.id;
        r0.access_hash = r1.access_hash;
    L14:
        if (r8 == null) goto L16;
        TLRPC.InputCheckPasswordSRP r02 = r8;
    L17:
        r7.password = r02;
        r7.user_id = getMessagesController().getInputUser(this.currentUser);
        getConnectionsManager().sendRequest(r7, new ChatRightsEditActivity$$ExternalSyntheticLambda21(this, r8, r9, r7));
        return;
    L16:
        r02 = new TLRPC.TL_inputCheckPasswordEmpty();
        goto L17
    L13:
        r7.channel = new TLRPC.TL_inputChannelEmpty();
        goto L14
    L7:
        if (ChatObject.isChannel(this.currentChat) == true) goto L10;
        MessagesController.getInstance(this.currentAccount).convertToMegaGroup(getParentActivity(), this.chatId, this, new ChatRightsEditActivity$$ExternalSyntheticLambda20(this, r8, r9));
    }

    private boolean isDefaultAdminRights() {
        TLRPC.TL_chatAdminRights r0 = this.adminRights;
        boolean r1 = r0.change_info;
        if (r1 == true) goto L5;
    L24:
        if (r1 == false) goto L26;
        return false;
    L26:
        if (r0.delete_messages == false) goto L28;
        return false;
    L28:
        if (r0.ban_users == false) goto L30;
        return false;
    L30:
        if (r0.invite_users == false) goto L32;
        return false;
    L32:
        if (r0.pin_messages == false) goto L34;
        return false;
    L34:
        if (r0.manage_ranks == false) goto L36;
        return false;
    L36:
        if (this.isForum == false) goto L40;
        if (r0.manage_topics == false) goto L40;
        return false;
    L40:
        if (r0.manage_call == false) goto L42;
        return false;
    L42:
        if (r0.add_admins == false) goto L44;
        return false;
    L44:
        if (r0.anonymous == true) goto L58;
        return true;
    L58:
        return false;
    L5:
        if (r0.delete_messages == false) goto L24;
        if (r0.ban_users == false) goto L24;
        if (r0.invite_users == false) goto L24;
        if (r0.pin_messages == false) goto L24;
        if (r0.manage_ranks == false) goto L24;
        if (this.isForum == false) goto L19;
        if (r0.manage_topics == false) goto L24;
    L19:
        if (r0.manage_call == false) goto L24;
        if (r0.add_admins == true) goto L24;
        if (r0.anonymous == true) goto L24;
        return true;
    }

    private boolean isExpandableSendMediaRow(int r2) {
        if (r2 != this.sendStickersRow) goto L5;
        return true;
    L5:
        if (r2 != this.embedLinksRow) goto L7;
        return true;
    L7:
        if (r2 != this.sendPollsRow) goto L9;
        return true;
    L9:
        if (r2 != this.sendPhotosRow) goto L11;
        return true;
    L11:
        if (r2 != this.sendVideosRow) goto L13;
        return true;
    L13:
        if (r2 != this.sendFilesRow) goto L15;
        return true;
    L15:
        if (r2 != this.sendMusicRow) goto L17;
        return true;
    L17:
        if (r2 != this.sendRoundRow) goto L19;
        return true;
    L19:
        if (r2 != this.sendVoiceRow) goto L21;
        return true;
    L21:
        if (r2 != this.sendReactionsRow) goto L23;
        return true;
    L23:
        if (r2 != this.channelPostMessagesRow) goto L25;
        return true;
    L25:
        if (r2 != this.channelEditMessagesRow) goto L27;
        return true;
    L27:
        if (r2 != this.channelDeleteMessagesRow) goto L29;
        return true;
    L29:
        if (r2 != this.channelPostStoriesRow) goto L31;
        return true;
    L31:
        if (r2 != this.channelEditStoriesRow) goto L33;
        return true;
    L33:
        if (r2 == this.channelDeleteStoriesRow) goto L53;
        return false;
    L53:
        return true;
    }

    private /* synthetic */ void lambda$checkDiscard$30(AlertDialog r1, int r2) {
        onDonePressed();
    }

    private /* synthetic */ void lambda$checkDiscard$31(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$0(int r1, TimePicker r2, int r3, int r4) {
        int r12 = (r1 + (r3 * 3600)) + (r4 * 60);
        this.bannedRights.until_date = r12;
        this.listViewAdapter.notifyItemChanged(this.untilDateRow);
    }

    private /* synthetic */ void lambda$createView$2(DatePicker r7, int r8, int r9, int r10) {
        Calendar r72 = Calendar.getInstance();
        r72.clear();
        r72.set(r8, r9, r10);
        int r73 = (int) (r72.getTime().getTime() / 1000);
        TimePickerDialog r0 = new TimePickerDialog(getParentActivity(), new ChatRightsEditActivity$$ExternalSyntheticLambda25(this, r73), 0, 0, true);     // Catch: Exception -> L5
        r0.setButton(-1, LocaleController.getString(R.string.Set), r0);     // Catch: Exception -> L5
        r0.setButton(-2, LocaleController.getString(R.string.Cancel), new ChatRightsEditActivity$$ExternalSyntheticLambda26());     // Catch: Exception -> L5
        showDialog(r0);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$createView$5(BottomSheet.Builder r11, View r12) {
        int r122 = ((Integer) r12.getTag()).intValue();
        if (r122 != 0) goto L5;
        this.bannedRights.until_date = 0;
        this.listViewAdapter.notifyItemChanged(this.untilDateRow);
    L22:
        r11.getDismissRunnable().run();
        return;
    L5:
        if (r122 != 1) goto L7;
        TLRPC.TL_chatBannedRights r123 = this.bannedRights;
        r123.until_date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + 86400;
        this.listViewAdapter.notifyItemChanged(this.untilDateRow);
        goto L22
    L7:
        if (r122 != 2) goto L9;
        TLRPC.TL_chatBannedRights r124 = this.bannedRights;
        r124.until_date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + 604800;
        this.listViewAdapter.notifyItemChanged(this.untilDateRow);
        goto L22
    L9:
        if (r122 != 3) goto L11;
        TLRPC.TL_chatBannedRights r125 = this.bannedRights;
        r125.until_date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + 2592000;
        this.listViewAdapter.notifyItemChanged(this.untilDateRow);
        goto L22
    L11:
        if (r122 != 4) goto L22;
        Calendar r126 = Calendar.getInstance();
        int r5 = r126.get(1);
        int r6 = r126.get(2);
        int r7 = r126.get(5);
        DatePickerDialog r2 = new DatePickerDialog(getParentActivity(), new ChatRightsEditActivity$$ExternalSyntheticLambda17(this), r5, r6, r7);     // Catch: Exception -> L16
        DatePicker r127 = r2.getDatePicker();     // Catch: Exception -> L16
        Calendar r0 = Calendar.getInstance();     // Catch: Exception -> L16
        r0.setTimeInMillis(System.currentTimeMillis());     // Catch: Exception -> L16
        r0.set(11, r0.getMinimum(11));     // Catch: Exception -> L16
        r0.set(12, r0.getMinimum(12));     // Catch: Exception -> L16
        r0.set(13, r0.getMinimum(13));     // Catch: Exception -> L16
        r0.set(14, r0.getMinimum(14));     // Catch: Exception -> L16
        r127.setMinDate(r0.getTimeInMillis());     // Catch: Exception -> L16
        r0.setTimeInMillis(System.currentTimeMillis() + 31536000000L);     // Catch: Exception -> L16
        r0.set(11, r0.getMaximum(11));     // Catch: Exception -> L16
        r0.set(12, r0.getMaximum(12));     // Catch: Exception -> L16
        r0.set(13, r0.getMaximum(13));     // Catch: Exception -> L16
        r0.set(14, r0.getMaximum(14));     // Catch: Exception -> L16
        r127.setMaxDate(r0.getTimeInMillis());     // Catch: Exception -> L16
        r2.setButton(-1, LocaleController.getString(R.string.Set), r2);     // Catch: Exception -> L16
        r2.setButton(-2, LocaleController.getString(R.string.Cancel), new ChatRightsEditActivity$$ExternalSyntheticLambda18());     // Catch: Exception -> L16
        r2.setOnShowListener(new ChatRightsEditActivity$$ExternalSyntheticLambda19(r127));     // Catch: Exception -> L16
        showDialog(r2);     // Catch: Exception -> L16
    L16:
        e = move-exception;
        FileLog.e(e);
        goto L22
    }

    private /* synthetic */ void lambda$createView$6(long r1) {
        this.guardBotIdToSet = r1;
        this.hasGuardBotToSet = true;
        checkGuardBotRow();
    }

    private /* synthetic */ void lambda$createView$7(long r10) {
        TLRPC.ChatFull r0 = this.chatInfo;
        if (r0 == null) goto L5;
        long r3 = r0.guard_bot_id;
    L7:
        if (r3 == 0) goto L10;
        TLRPC.User r02 = getMessagesController().getUser(Long.valueOf(r3));
    L9:
        TLRPC.User r6 = r02;
        if (r6 != null) goto L13;
    L18:
        this.guardBotIdToSet = r10;
        this.hasGuardBotToSet = true;
        checkGuardBotRow();
        return;
    L13:
        if (r10 == 0) goto L18;
        if (r6.id == r10) goto L18;
        GuardBotReplaceSheet.show(getContext(), this.resourceProvider, this.currentAccount, r6, this.currentUser, new ChatRightsEditActivity$$ExternalSyntheticLambda11(this, r10));
        return;
    L10:
        r02 = null;
        goto L9
    L5:
        r3 = 0;
        goto L7
    }

    private /* synthetic */ void lambda$createView$8(Context r21, View r22, int r23) {
        if (this.canEdit == false) goto L5;
    L12:
        boolean r7 = false;
        if (r23 == this.sendMediaRow) goto L15;
        int r4 = this.channelMessagesRow;
        if (r23 == r4) goto L28;
        int r5 = this.channelStoriesRow;
        if (r23 == r5) goto L41;
        if (r23 != 0) goto L55;
        Bundle r1 = new Bundle();
        r1.putLong("user_id", this.currentUser.id);
        presentFragment(new ProfileActivity(r1));
        return;
    L55:
        if (r23 != this.removeAdminRow) goto L67;
        int r12 = this.currentType;
        if (r12 != 0) goto L63;
        MessagesController.getInstance(this.currentAccount).setUserAdminRole(this.chatId, this.currentUser, new TLRPC.TL_chatAdminRights(), this.currentRank, this.isChannel, getFragmentForAlert(0), this.isAddingNew, false, null, null);
        ChatRightsEditActivityDelegate r13 = this.delegate;
        if (r13 == null) goto L61;
        r13.didSetRights(0, this.adminRights, this.bannedRights, this.currentRank);
    L61:
        finishFragment();
        return;
    L63:
        if (r12 != 1) goto L357;
        this.banning = true;
        TLRPC.TL_chatBannedRights r14 = new TLRPC.TL_chatBannedRights();
        this.bannedRights = r14;
        r14.view_messages = true;
        r14.send_media = true;
        r14.send_messages = true;
        r14.send_stickers = true;
        r14.send_gifs = true;
        r14.send_games = true;
        r14.send_inline = true;
        r14.embed_links = true;
        r14.pin_messages = true;
        r14.edit_rank = true;
        r14.send_reactions = true;
        r14.send_polls = true;
        r14.invite_users = true;
        r14.change_info = true;
        r14.manage_topics = true;
        r14.until_date = 0;
        onDonePressed();
        return;
    L357:
        return;
    L67:
        if (r23 != this.transferOwnerRow) goto L71;
        initTransfer(null, null);
        return;
    L71:
        if (r23 != this.untilDateRow) goto L91;
        if (getParentActivity() == null) goto L371;
        BottomSheet.Builder r10 = new BottomSheet.Builder(r21);
        r10.setApplyTopPadding(false);
        LinearLayout r122 = new LinearLayout(r21);
        r122.setOrientation(1);
        HeaderCell r15 = new HeaderCell(r21, Theme.key_dialogTextBlue2, 23, 15, false);
        r15.setHeight(47);
        r15.setText(LocaleController.getString(R.string.UserRestrictionsDuration));
        r122.addView(r15);
        LinearLayout r16 = new LinearLayout(r21);
        r16.setOrientation(1);
        r122.addView(r16, LayoutHelper.createLinear(-1, -2));
        int r52 = 5;
        BottomSheet.BottomSheetCell[] r6 = new BottomSheet.BottomSheetCell[5];
        int r132 = 0;
    L76:
        if (r132 >= r52) goto L88;
        BottomSheet.BottomSheetCell r142 = new BottomSheet.BottomSheetCell(r21, 0);
        r6[r132] = r142;
        r142.setPadding(AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f), 0);
        r6[r132].setTag(Integer.valueOf(r132));
        r6[r132].setBackgroundDrawable(Theme.getSelectorDrawable(false));
        if (r132 == 0) goto L86;
        if (r132 == 1) goto L85;
        if (r132 == 2) goto L84;
        if (r132 == 3) goto L83;
        String r53 = LocaleController.getString(R.string.UserRestrictionsCustom);
    L87:
        r6[r132].setTextAndIcon(r53, 0);
        r16.addView(r6[r132], LayoutHelper.createLinear(-1, -2));
        r6[r132].setOnClickListener(new ChatRightsEditActivity$$ExternalSyntheticLambda4(this, r10));
        r132 = r132 + 1;
        r52 = 5;
        goto L76
    L83:
        r53 = LocaleController.formatPluralString("Months", 1, new Object[0]);
        goto L87
    L84:
        r53 = LocaleController.formatPluralString("Weeks", 1, new Object[0]);
        goto L87
    L85:
        r53 = LocaleController.formatPluralString("Days", 1, new Object[0]);
        goto L87
    L86:
        r53 = LocaleController.getString(R.string.UserRestrictionsUntilForever);
        goto L87
    L88:
        r10.setCustomView(r122);
        showDialog(r10.create());
        return;
    L371:
        return;
    L91:
        if ((r22 instanceof CheckBoxCell) == false) goto L173;
        CheckBoxCell r17 = (CheckBoxCell) r22;
        int r2 = this.channelPostMessagesRow;
        if (r23 != r2) goto L95;
    L164:
        if (r23 != r2) goto L166;
        TLRPC.TL_chatAdminRights r24 = this.adminRights;
        boolean r3 = !r24.post_messages;
        r24.post_messages = r3;
    L170:
        this.listViewAdapter.notifyItemChanged(r4);
        r17.setChecked(r3, true);
        return;
    L166:
        int r25 = this.channelEditMessagesRow;
        TLRPC.TL_chatAdminRights r54 = this.adminRights;
        if (r23 != r25) goto L169;
        r3 = !r54.edit_messages;
        r54.edit_messages = r3;
        goto L170
    L169:
        r3 = !r54.delete_messages;
        r54.delete_messages = r3;
        goto L170
    L95:
        if (r23 == this.channelEditMessagesRow) goto L164;
        if (r23 == this.channelDeleteMessagesRow) goto L164;
        int r26 = this.channelPostStoriesRow;
        if (r23 != r26) goto L102;
    L156:
        if (r23 != r26) goto L158;
        TLRPC.TL_chatAdminRights r27 = this.adminRights;
        boolean r32 = !r27.post_stories;
        r27.post_stories = r32;
    L162:
        this.listViewAdapter.notifyItemChanged(r5);
        r17.setChecked(r32, true);
        return;
    L158:
        int r28 = this.channelEditStoriesRow;
        TLRPC.TL_chatAdminRights r42 = this.adminRights;
        if (r23 != r28) goto L161;
        r32 = !r42.edit_stories;
        r42.edit_stories = r32;
        goto L162
    L161:
        r32 = !r42.delete_stories;
        r42.delete_stories = r32;
        goto L162
    L102:
        if (r23 == this.channelEditStoriesRow) goto L156;
        if (r23 == this.channelDeleteStoriesRow) goto L156;
        if (this.currentType == 1) goto L109;
        return;
    L109:
        if (this.bannedRights == null) goto L359;
        r17.isChecked();
        if (r17.hasIcon() == false) goto L117;
        if (this.currentType == 2) goto L360;
        new AlertDialog.Builder(getParentActivity()).setTitle(LocaleController.getString(R.string.UserRestrictionsCantModify)).setMessage(LocaleController.getString(R.string.UserRestrictionsCantModifyDisabled)).setPositiveButton(LocaleController.getString(R.string.OK), null).create().show();
        return;
    L360:
        return;
    L117:
        if (r23 != this.sendPhotosRow) goto L120;
        TLRPC.TL_chatBannedRights r29 = this.bannedRights;
        r7 = !r29.send_photos;
        r29.send_photos = r7;
    L154:
        this.listViewAdapter.notifyItemChanged(this.sendMediaRow);
        r17.setChecked(!r7, true);
        return;
    L120:
        if (r23 != this.sendVideosRow) goto L123;
        TLRPC.TL_chatBannedRights r210 = this.bannedRights;
        r7 = !r210.send_videos;
        r210.send_videos = r7;
        goto L154
    L123:
        if (r23 != this.sendMusicRow) goto L126;
        TLRPC.TL_chatBannedRights r211 = this.bannedRights;
        r7 = !r211.send_audios;
        r211.send_audios = r7;
        goto L154
    L126:
        if (r23 != this.sendReactionsRow) goto L129;
        TLRPC.TL_chatBannedRights r212 = this.bannedRights;
        r7 = !r212.send_reactions;
        r212.send_reactions = r7;
        goto L154
    L129:
        if (r23 != this.sendFilesRow) goto L132;
        TLRPC.TL_chatBannedRights r213 = this.bannedRights;
        r7 = !r213.send_docs;
        r213.send_docs = r7;
        goto L154
    L132:
        if (r23 != this.sendRoundRow) goto L135;
        TLRPC.TL_chatBannedRights r214 = this.bannedRights;
        r7 = !r214.send_roundvideos;
        r214.send_roundvideos = r7;
        goto L154
    L135:
        if (r23 != this.sendVoiceRow) goto L138;
        TLRPC.TL_chatBannedRights r215 = this.bannedRights;
        r7 = !r215.send_voices;
        r215.send_voices = r7;
        goto L154
    L138:
        if (r23 != this.sendStickersRow) goto L141;
        TLRPC.TL_chatBannedRights r216 = this.bannedRights;
        r7 = !r216.send_stickers;
        r216.send_inline = r7;
        r216.send_gifs = r7;
        r216.send_games = r7;
        r216.send_stickers = r7;
        goto L154
    L141:
        if (r23 != this.embedLinksRow) goto L152;
        if (this.bannedRights.send_plain == false) goto L145;
    L146:
        View r217 = this.linearLayoutManager.findViewByPosition(this.sendMessagesRow);
        if (r217 == null) goto L150;
        AndroidUtilities.shakeViewSpring(r217);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return;
    L150:
        TLRPC.TL_chatBannedRights r218 = this.bannedRights;
        r7 = !r218.embed_links;
        r218.embed_links = r7;
        goto L154
    L145:
        if (this.defaultBannedRights.send_plain == false) goto L150;
    L152:
        if (r23 != this.sendPollsRow) goto L154;
        TLRPC.TL_chatBannedRights r219 = this.bannedRights;
        r7 = !r219.send_polls;
        r219.send_polls = r7;
        goto L154
    L359:
        return;
    L173:
        if ((r22 instanceof TextCheckCell2) == false) goto L361;
        TextCheckCell2 r62 = (TextCheckCell2) r22;
        if (r62.hasIcon() == true) goto L177;
        boolean r18 = r62.isEnabled();
        int r220 = this.currentType;
        if (r18 == true) goto L204;
        if (r220 == 2) goto L185;
        if (r220 == 0) goto L185;
        return;
    L185:
        if (r23 != this.changeInfoRow) goto L191;
        TLRPC.TL_chatBannedRights r19 = this.defaultBannedRights;
        if (r19 == null) goto L191;
        if (r19.change_info == true) goto L191;
    L202:
        new AlertDialog.Builder(getParentActivity()).setTitle(LocaleController.getString(R.string.UserRestrictionsCantModify)).setMessage(LocaleController.getString(R.string.UserRestrictionsCantModifyEnabled)).setPositiveButton(LocaleController.getString(R.string.OK), null).create().show();
        return;
    L191:
        if (r23 != this.pinMessagesRow) goto L197;
        TLRPC.TL_chatBannedRights r110 = this.defaultBannedRights;
        if (r110 == null) goto L197;
        if (r110.pin_messages == false) goto L202;
    L197:
        if (r23 != this.editTagsRow) goto L364;
        TLRPC.TL_chatBannedRights r111 = this.defaultBannedRights;
        if (r111 != null) goto L201;
        return;
    L201:
        if (r111.edit_rank == false) goto L202;
        return;
    L364:
        return;
    L204:
        if (r220 != 2) goto L206;
    L208:
        boolean r9 = r62.isChecked();
        if (r23 != this.manageRow) goto L212;
        r9 = !this.asAdmin;
        this.asAdmin = r9;
        updateAsAdmin(true);
    L343:
        if (this.currentType == 2) goto L345;
    L349:
        updateRows(true);
        return;
    L345:
        if (this.asAdmin == false) goto L348;
        if (r9 == false) goto L348;
        r7 = true;
    L348:
        r62.setChecked(r7);
        goto L349
    L212:
        if (r23 != this.changeInfoRow) goto L220;
        int r112 = this.currentType;
        if (r112 == 0) goto L218;
        if (r112 == 2) goto L218;
        TLRPC.TL_chatBannedRights r113 = this.bannedRights;
        r9 = !r113.change_info;
        r113.change_info = r9;
    L218:
        TLRPC.TL_chatAdminRights r114 = this.adminRights;
        r9 = !r114.change_info;
        r114.change_info = r9;
        goto L343
    L220:
        if (r23 != this.postMessagesRow) goto L223;
        TLRPC.TL_chatAdminRights r115 = this.adminRights;
        r9 = !r115.post_messages;
        r115.post_messages = r9;
        goto L343
    L223:
        if (r23 != this.guardBotRow) goto L242;
        String r116 = DialogObject.getShortName(this.currentUser);
        String r221 = LocaleController.getString(R.string.ApproveNewMembersTitle);
        if (r9 == true) goto L227;
        int r33 = R.string.ApproveNewMembersEnable;
    L228:
        String r34 = LocaleController.getString(r33);
        boolean r43 = this.isChannel;
        if (r9 == false) goto L233;
        if (r43 == false) goto L232;
        int r44 = R.string.ApproveNewMembersDisabledMessageChannel;
    L236:
        SpannableStringBuilder r117 = AndroidUtilities.replaceTags(LocaleController.formatString(r44, new Object[]{r116}));
        if (r9 == true) goto L239;
        long r45 = this.currentUser.id;
    L240:
        AlertsCreator.showSimpleConfirmAlert(this, r221, r117, r34, false, new ChatRightsEditActivity$$ExternalSyntheticLambda5(this, r45));
        goto L343
    L239:
        r45 = 0;
        goto L240
    L232:
        r44 = R.string.ApproveNewMembersDisabledMessageGroup;
        goto L236
    L233:
        if (r43 == false) goto L235;
        r44 = R.string.ApproveNewMembersMessageChannel;
        goto L236
    L235:
        r44 = R.string.ApproveNewMembersMessageGroup;
        goto L236
    L227:
        r33 = R.string.ApproveNewMembersDisable;
        goto L228
    L242:
        if (r23 != this.manageWelcomeRow) goto L245;
        TLRPC.TL_chatAdminRights r118 = this.adminRights;
        r9 = !r118.manage_welcome_messages;
        r118.manage_welcome_messages = r9;
        goto L343
    L245:
        if (r23 != this.manageDirectRow) goto L248;
        TLRPC.TL_chatAdminRights r119 = this.adminRights;
        r9 = !r119.manage_direct_messages;
        r119.manage_direct_messages = r9;
        goto L343
    L248:
        if (r23 != this.editMesagesRow) goto L251;
        TLRPC.TL_chatAdminRights r120 = this.adminRights;
        r9 = !r120.edit_messages;
        r120.edit_messages = r9;
        goto L343
    L251:
        if (r23 != this.deleteMessagesRow) goto L254;
        TLRPC.TL_chatAdminRights r121 = this.adminRights;
        r9 = !r121.delete_messages;
        r121.delete_messages = r9;
        goto L343
    L254:
        if (r23 != this.addAdminsRow) goto L257;
        TLRPC.TL_chatAdminRights r123 = this.adminRights;
        r9 = !r123.add_admins;
        r123.add_admins = r9;
        goto L343
    L257:
        if (r23 != this.anonymousRow) goto L260;
        TLRPC.TL_chatAdminRights r124 = this.adminRights;
        r9 = !r124.anonymous;
        r124.anonymous = r9;
        goto L343
    L260:
        if (r23 != this.banUsersRow) goto L263;
        TLRPC.TL_chatAdminRights r125 = this.adminRights;
        r9 = !r125.ban_users;
        r125.ban_users = r9;
        goto L343
    L263:
        if (r23 != this.startVoiceChatRow) goto L266;
        TLRPC.TL_chatAdminRights r126 = this.adminRights;
        r9 = !r126.manage_call;
        r126.manage_call = r9;
        goto L343
    L266:
        if (r23 != this.manageTopicsRow) goto L274;
        int r127 = this.currentType;
        if (r127 == 0) goto L272;
        if (r127 == 2) goto L272;
        TLRPC.TL_chatBannedRights r128 = this.bannedRights;
        r9 = !r128.manage_topics;
        r128.manage_topics = r9;
    L272:
        TLRPC.TL_chatAdminRights r129 = this.adminRights;
        r9 = !r129.manage_topics;
        r129.manage_topics = r9;
        goto L343
    L274:
        if (r23 != this.manageLinkedPeersRow) goto L282;
        int r130 = this.currentType;
        if (r130 == 0) goto L280;
        if (r130 == 2) goto L280;
        TLRPC.TL_chatBannedRights r131 = this.bannedRights;
        r9 = !r131.manage_linked_peers;
        r131.manage_linked_peers = r9;
    L280:
        TLRPC.TL_chatAdminRights r133 = this.adminRights;
        r9 = !r133.manage_linked_peers;
        r133.manage_linked_peers = r9;
        goto L343
    L282:
        if (r23 != this.addUsersRow) goto L290;
        int r134 = this.currentType;
        if (r134 == 0) goto L288;
        if (r134 == 2) goto L288;
        TLRPC.TL_chatBannedRights r135 = this.bannedRights;
        r9 = !r135.invite_users;
        r135.invite_users = r9;
    L288:
        TLRPC.TL_chatAdminRights r136 = this.adminRights;
        r9 = !r136.invite_users;
        r136.invite_users = r9;
        goto L343
    L290:
        if (r23 != this.pinMessagesRow) goto L297;
        int r137 = this.currentType;
        if (r137 == 0) goto L296;
        if (r137 == 2) goto L296;
        TLRPC.TL_chatBannedRights r138 = this.bannedRights;
        r9 = !r138.pin_messages;
        r138.pin_messages = r9;
    L296:
        TLRPC.TL_chatAdminRights r139 = this.adminRights;
        r9 = !r139.pin_messages;
        r139.pin_messages = r9;
        goto L343
    L297:
        int r140 = this.editTagsRow;
        int r222 = this.currentType;
        if (r23 != r140) goto L304;
        if (r222 == 0) goto L303;
        if (r222 == 2) goto L303;
        TLRPC.TL_chatBannedRights r141 = this.bannedRights;
        r9 = !r141.edit_rank;
        r141.edit_rank = r9;
    L303:
        TLRPC.TL_chatAdminRights r143 = this.adminRights;
        r9 = !r143.manage_ranks;
        r143.manage_ranks = r9;
        goto L343
    L304:
        if (r222 != 1) goto L343;
        if (this.bannedRights == null) goto L343;
        boolean r144 = r62.isChecked();
        if (r23 != this.sendMessagesRow) goto L310;
        TLRPC.TL_chatBannedRights r223 = this.bannedRights;
        r9 = !r223.send_plain;
        r223.send_plain = r9;
    L310:
        if (r144 == false) goto L336;
        TLRPC.TL_chatBannedRights r145 = this.bannedRights;
        if (r145.send_plain == false) goto L334;
        if (r145.embed_links == false) goto L334;
        if (r145.send_inline == false) goto L334;
        if (r145.send_photos == false) goto L334;
        if (r145.send_videos == false) goto L334;
        if (r145.send_audios == false) goto L334;
        if (r145.send_docs == false) goto L334;
        if (r145.send_voices == false) goto L334;
        if (r145.send_roundvideos == false) goto L334;
        if (r145.send_polls == false) goto L334;
        if (r145.send_reactions == true) goto L336;
    L334:
        if (r145.view_messages == false) goto L336;
        r145.view_messages = false;
    L336:
        int r146 = this.embedLinksRow;
        if (r146 < 0) goto L339;
        this.listViewAdapter.notifyItemChanged(r146);
    L339:
        int r147 = this.sendMediaRow;
        if (r147 < 0) goto L343;
        this.listViewAdapter.notifyItemChanged(r147);
        goto L343
    L206:
        if (r23 == this.guardBotRow) goto L208;
        r62.setChecked(!r62.isChecked());
        goto L208
    L177:
        if (this.currentType == 2) goto L362;
        new AlertDialog.Builder(getParentActivity()).setTitle(LocaleController.getString(R.string.UserRestrictionsCantModify)).setMessage(LocaleController.getString(R.string.UserRestrictionsCantModifyDisabled)).setPositiveButton(LocaleController.getString(R.string.OK), null).create().show();
        return;
    L362:
        return;
    L361:
        return;
    L41:
        if ((r22 instanceof TextCheckCell2) == true) goto L43;
    L45:
        this.channelStoriesExpanded = !this.channelStoriesExpanded;
        updateRows(false);
        this.listViewAdapter.notifyItemChanged(this.channelStoriesRow);
        boolean r148 = this.channelStoriesExpanded;
        ListAdapter r224 = this.listViewAdapter;
        if (r148 == false) goto L49;
        r224.notifyItemRangeInserted(this.channelStoriesRow + 1, 3);
        return;
    L49:
        r224.notifyItemRangeRemoved(this.channelStoriesRow + 1, 3);
        return;
    L43:
        if (((TextCheckCell2) r22).isEnabled() == true) goto L45;
        return;
    L28:
        if ((r22 instanceof TextCheckCell2) == true) goto L30;
    L32:
        this.channelMessagesExpanded = !this.channelMessagesExpanded;
        updateRows(false);
        this.listViewAdapter.notifyItemChanged(this.channelMessagesRow);
        boolean r149 = this.channelMessagesExpanded;
        ListAdapter r225 = this.listViewAdapter;
        if (r149 == false) goto L36;
        r225.notifyItemRangeInserted(this.channelMessagesRow + 1, 3);
        return;
    L36:
        r225.notifyItemRangeRemoved(this.channelMessagesRow + 1, 3);
        return;
    L30:
        if (((TextCheckCell2) r22).isEnabled() == true) goto L32;
        return;
    L15:
        if ((r22 instanceof TextCheckCell2) == true) goto L17;
    L19:
        this.sendMediaExpanded = !this.sendMediaExpanded;
        updateRows(false);
        this.listViewAdapter.notifyItemChanged(this.sendMediaRow);
        boolean r150 = this.sendMediaExpanded;
        ListAdapter r226 = this.listViewAdapter;
        if (r150 == false) goto L23;
        r226.notifyItemRangeInserted(this.sendMediaRow + 1, 10);
        return;
    L23:
        r226.notifyItemRangeRemoved(this.sendMediaRow + 1, 10);
        return;
    L17:
        if (((TextCheckCell2) r22).isEnabled() == true) goto L19;
        return;
    L5:
        if (this.currentChat.creator == true) goto L7;
    L10:
        if (r23 == 0) goto L12;
        return;
    L7:
        if (this.currentType != 0) goto L10;
        if (r23 == this.anonymousRow) goto L12;
        goto L10
    }

    private /* synthetic */ void lambda$getThemeDescriptions$33() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L14;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof UserCell2) == false) goto L9;
        ((UserCell2) r3).update(0);
    L9:
        r2 = r2 + 1;
        goto L5
    L14:
        return;
    }

    private /* synthetic */ void lambda$initTransfer$13(TLRPC.InputCheckPasswordSRP r3, TwoStepVerificationActivity r4, long r5) {
        if (r5 == 0) goto L6;
        this.chatId = r5;
        this.currentChat = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r5));
        initTransfer(r3, r4);
        return;
    }

    private /* synthetic */ void lambda$initTransfer$14(TwoStepVerificationActivity r1, TLRPC.InputCheckPasswordSRP r2) {
        initTransfer(r2, r1);
    }

    private /* synthetic */ void lambda$initTransfer$15(AlertDialog r2, int r3) {
        TwoStepVerificationActivity r22 = new TwoStepVerificationActivity();
        r22.setDelegate(0, new ChatRightsEditActivity$$ExternalSyntheticLambda32(this, r22));
        presentFragment(r22);
    }

    private /* synthetic */ void lambda$initTransfer$16(AlertDialog r2, int r3) {
        presentFragment(new TwoStepVerificationSetupActivity(6, null));
    }

    private /* synthetic */ void lambda$initTransfer$17(TLRPC.TL_error r1, TLObject r2, TwoStepVerificationActivity r3) {
        if (r1 != null) goto L5;
        TL_account.Password r22 = (TL_account.Password) r2;
        r3.setCurrentPasswordInfo(null, r22);
        TwoStepVerificationActivity.initPasswordNewAlgo(r22);
        initTransfer(r3.getNewSrpPassword(), r3);
        return;
    }

    private /* synthetic */ void lambda$initTransfer$18(TwoStepVerificationActivity r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new ChatRightsEditActivity$$ExternalSyntheticLambda33(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$initTransfer$19(TLRPC.TL_error r26, TLRPC.InputCheckPasswordSRP r27, TwoStepVerificationActivity r28, TLRPC.TL_channels_editCreator r29) {
        if (r26 != null) goto L5;
        if (r27 == null) goto L97;
        this.delegate.didChangeOwner(this.currentUser);
        removeSelfFromStack();
        r28.needHideProgress();
        r28.finishFragment();
        return;
    L97:
        return;
    L5:
        if (getParentActivity() != null) goto L8;
        return;
    L8:
        if ("PASSWORD_HASH_INVALID".equals(r26.text) == false) goto L17;
        if (r27 != null) goto L96;
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        if (this.isChannel == false) goto L13;
        r0.setTitle(LocaleController.getString(R.string.EditAdminChannelTransfer));
    L14:
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.EditAdminTransferReadyAlertText, new Object[]{this.currentChat.title, UserObject.getFirstName(this.currentUser)})));
        r0.setPositiveButton(LocaleController.getString(R.string.EditAdminTransferChangeOwner), new ChatRightsEditActivity$$ExternalSyntheticLambda29(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r0.create());
        return;
    L13:
        r0.setTitle(LocaleController.getString(R.string.EditAdminGroupTransfer));
        goto L14
    L96:
        return;
    L17:
        if ("PASSWORD_MISSING".equals(r26.text) == false) goto L19;
    L41:
        if (r28 == null) goto L43;
        r28.needHideProgress();
    L43:
        AlertDialog.Builder r2 = new AlertDialog.Builder(getParentActivity());
        r2.setTitle(LocaleController.getString(R.string.EditAdminTransferAlertTitle));
        LinearLayout r3 = new LinearLayout(getParentActivity());
        r3.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(24.0f), 0);
        r3.setOrientation(1);
        r2.setView(r3);
        TextView r7 = new TextView(getParentActivity());
        int r8 = Theme.key_dialogTextBlack;
        r7.setTextColor(Theme.getColor(r8));
        r7.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L46;
        int r11 = 5;
    L47:
        r7.setGravity(r11 | 48);
        boolean r112 = this.isChannel;
        TLRPC.User r14 = this.currentUser;
        if (r112 == false) goto L50;
        r7.setText(AndroidUtilities.replaceTags(LocaleController.formatString("EditChannelAdminTransferAlertText", R.string.EditChannelAdminTransferAlertText, new Object[]{UserObject.getFirstName(r14)})));
    L51:
        r3.addView(r7, LayoutHelper.createLinear(-1, -2));
        LinearLayout r72 = new LinearLayout(getParentActivity());
        r72.setOrientation(0);
        r3.addView(r72, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r15 = new ImageView(getParentActivity());
        r15.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L54;
        int r12 = AndroidUtilities.dp(11.0f);
    L55:
        int r4 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L58;
        int r13 = 0;
    L59:
        r15.setPadding(r12, r4, r13, 0);
        int r122 = Theme.getColor(r8);
        PorterDuff.Mode r132 = PorterDuff.Mode.MULTIPLY;
        r15.setColorFilter(new PorterDuffColorFilter(r122, r132));
        TextView r42 = new TextView(getParentActivity());
        r42.setTextColor(Theme.getColor(r8));
        r42.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L62;
        int r123 = 5;
    L63:
        r42.setGravity(r123 | 48);
        r42.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EditAdminTransferAlertText1)));
        if (LocaleController.isRTL == false) goto L66;
        r72.addView(r42, LayoutHelper.createLinear(-1, -2));
        r72.addView(r15, LayoutHelper.createLinear(-2, -2, 5));
    L67:
        LinearLayout r43 = new LinearLayout(getParentActivity());
        r43.setOrientation(0);
        r3.addView(r43, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r73 = new ImageView(getParentActivity());
        r73.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L70;
        int r124 = AndroidUtilities.dp(11.0f);
    L71:
        int r152 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L74;
        int r113 = 0;
    L75:
        r73.setPadding(r124, r152, r113, 0);
        r73.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r8), r132));
        TextView r10 = new TextView(getParentActivity());
        r10.setTextColor(Theme.getColor(r8));
        r10.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L78;
        int r114 = 5;
    L79:
        r10.setGravity(r114 | 48);
        r10.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EditAdminTransferAlertText2)));
        if (LocaleController.isRTL == false) goto L82;
        r43.addView(r10, LayoutHelper.createLinear(-1, -2));
        int r125 = 5;
        r43.addView(r73, LayoutHelper.createLinear(-2, -2, 5));
    L84:
        if ("PASSWORD_MISSING".equals(r26.text) == false) goto L86;
        r2.setPositiveButton(LocaleController.getString(R.string.EditAdminTransferSetPassword), new ChatRightsEditActivity$$ExternalSyntheticLambda30(this));
        r2.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
    L91:
        showDialog(r2.create());
        return;
    L86:
        TextView r02 = new TextView(getParentActivity());
        r02.setTextColor(Theme.getColor(r8));
        r02.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == true) goto L90;
        r125 = 3;
    L90:
        r02.setGravity(r125 | 48);
        r02.setText(LocaleController.getString(R.string.EditAdminTransferAlertText3));
        r3.addView(r02, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        r2.setNegativeButton(LocaleController.getString(R.string.OK), null);
        goto L91
    L82:
        r125 = 5;
        r43.addView(r73, LayoutHelper.createLinear(-2, -2));
        r43.addView(r10, LayoutHelper.createLinear(-1, -2));
        goto L84
    L78:
        r114 = 3;
        goto L79
    L74:
        r113 = AndroidUtilities.dp(11.0f);
        goto L75
    L70:
        r124 = 0;
        goto L71
    L66:
        r72.addView(r15, LayoutHelper.createLinear(-2, -2));
        r72.addView(r42, LayoutHelper.createLinear(-1, -2));
        goto L67
    L62:
        r123 = 3;
        goto L63
    L58:
        r13 = AndroidUtilities.dp(11.0f);
        goto L59
    L54:
        r12 = 0;
        goto L55
    L50:
        r7.setText(AndroidUtilities.replaceTags(LocaleController.formatString("EditAdminTransferAlertText", R.string.EditAdminTransferAlertText, new Object[]{UserObject.getFirstName(r14)})));
        goto L51
    L46:
        r11 = 3;
        goto L47
    L19:
        if (r26.text.startsWith("PASSWORD_TOO_FRESH_") == true) goto L41;
        if (r26.text.startsWith("SESSION_TOO_FRESH_") == true) goto L41;
        if ("SRP_ID_INVALID".equals(r26.text) == false) goto L28;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.getPassword(), new ChatRightsEditActivity$$ExternalSyntheticLambda31(this, r28), 8);
        return;
    L28:
        if (r26.text.equals("CHANNELS_TOO_MUCH") == true) goto L30;
        if (r28 == null) goto L39;
        r28.needHideProgress();
        r28.finishFragment();
    L39:
        AlertsCreator.showAddUserAlert(r26, this, this.isChannel, this.isCommunity, r29);
        return;
    L30:
        if (getParentActivity() != null) goto L32;
    L35:
        presentFragment(new TooManyCommunitiesActivity(1));
        return;
    L32:
        if (AccountInstance.getInstance(this.currentAccount).getUserConfig().isPremium() == true) goto L35;
        showDialog(new LimitReachedBottomSheet(this, getParentActivity(), 5, this.currentAccount, null));
    }

    private /* synthetic */ void lambda$initTransfer$20(TLRPC.InputCheckPasswordSRP r1, TwoStepVerificationActivity r2, TLRPC.TL_channels_editCreator r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new ChatRightsEditActivity$$ExternalSyntheticLambda24(this, r5, r1, r2, r3));
    }

    private /* synthetic */ void lambda$onDonePressed$21(long r3) {
        if (r3 == 0) goto L6;
        this.chatId = r3;
        this.currentChat = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r3));
        onDonePressed();
        return;
    }

    private /* synthetic */ void lambda$onDonePressed$22() {
        onDonePressed(false);
    }

    private /* synthetic */ void lambda$onDonePressed$23() {
        if (this.hasGuardBotToSet == false) goto L5;
        setGuardBotImpl(this.guardBotIdToSet);
    L5:
        ChatRightsEditActivityDelegate r0 = this.delegate;
        if (r0 == null) goto L52;
        TLRPC.TL_chatAdminRights r1 = this.adminRights;
        if (r1.change_info == false) goto L10;
    L49:
        int r2 = 1;
    L50:
        r0.didSetRights(r2, r1, this.bannedRights, this.currentRank);
        finishFragment();
        return;
    L10:
        if (r1.post_messages == true) goto L49;
        if (r1.manage_direct_messages == true) goto L49;
        if (r1.manage_welcome_messages == true) goto L49;
        if (r1.edit_messages == true) goto L49;
        if (r1.delete_messages == true) goto L49;
        if (r1.ban_users == true) goto L49;
        if (r1.invite_users == true) goto L49;
        if (this.isForum == false) goto L28;
        if (r1.manage_topics == true) goto L49;
    L28:
        if (r1.pin_messages == true) goto L49;
        if (r1.manage_ranks == true) goto L49;
        if (r1.add_admins == true) goto L49;
        if (r1.anonymous == true) goto L49;
        if (r1.manage_call == true) goto L49;
        if (this.isChannel == false) goto L46;
        if (r1.post_stories == true) goto L49;
        if (r1.edit_stories == true) goto L49;
        if (r1.delete_stories == true) goto L49;
    L46:
        if (r1.other == true) goto L49;
        r2 = 0;
        goto L50
    }

    private /* synthetic */ boolean lambda$onDonePressed$24(TLRPC.TL_error r8) {
        setLoading(false);
        if (r8 != null) goto L5;
        return true;
    L5:
        if ("USER_PRIVACY_RESTRICTED".equals(r8.text) == true) goto L7;
        return true;
    L7:
        if (ChatObject.isChannel(this.currentChat) == true) goto L9;
        LimitReachedBottomSheet r1 = new LimitReachedBottomSheet(this, getParentActivity(), 11, this.currentAccount, getResourceProvider());
        ArrayList<TLRPC.User> r3 = new ArrayList();
        r3.add(this.currentUser);
        r1.setRestrictedUsers(this.currentChat, r3, null, null, null);
        r1.show();
    L9:
        return false;
    }

    private /* synthetic */ void lambda$onDonePressed$25() {
        ChatRightsEditActivityDelegate r0 = this.delegate;
        if (r0 != null) goto L5;
    L9:
        this.closingKeyboardAfterFinish = true;
        Bundle r2 = new Bundle();
        r2.putBoolean("scrollToTopOnResume", true);
        r2.putLong("chat_id", this.currentChat.id);
        if (getMessagesController().checkCanOpenChat(r2, this) == true) goto L13;
        setLoading(false);
        return;
    L13:
        ChatActivity r1 = new ChatActivity(r2);
        presentFragment(r1, true);
        if (BulletinFactory.canShowBulletin(r1) == false) goto L28;
        boolean r02 = this.isAddingNew;
        if (r02 == true) goto L18;
    L21:
        if (r02 == false) goto L23;
        return;
    L23:
        if (this.initialAsAdmin == false) goto L25;
        return;
    L25:
        if (this.asAdmin == false) goto L31;
        BulletinFactory.createPromoteToAdminBulletin(r1, this.currentUser.first_name).show();
        return;
    L31:
        return;
    L18:
        if (this.asAdmin == false) goto L21;
        BulletinFactory.createAddedAsAdminBulletin(r1, this.currentUser.first_name).show();
        return;
    L28:
        return;
    L5:
        if (this.asAdmin == false) goto L7;
        TLRPC.TL_chatAdminRights r22 = this.adminRights;
    L8:
        r0.didSetRights(0, r22, null, this.currentRank);
        goto L9
    L7:
        r22 = null;
        goto L8
    }

    private /* synthetic */ boolean lambda$onDonePressed$26(TLRPC.TL_error r1) {
        setLoading(false);
        return true;
    }

    private /* synthetic */ boolean lambda$onDonePressed$27(TLRPC.TL_error r1) {
        setLoading(false);
        return true;
    }

    private /* synthetic */ void lambda$onDonePressed$28(AlertDialog r14, int r15) {
        setLoading(true);
        ChatRightsEditActivity$$ExternalSyntheticLambda14 r8 = new ChatRightsEditActivity$$ExternalSyntheticLambda14(this);
        if (this.asAdmin == true) goto L9;
        if (this.initialAsAdmin == true) goto L9;
        getMessagesController().addUserToChat(this.currentChat.id, this.currentUser, 0, this.botHash, this, true, r8, new ChatRightsEditActivity$$ExternalSyntheticLambda16(this));
        return;
    L9:
        MessagesController r0 = getMessagesController();
        long r1 = this.currentChat.id;
        TLRPC.User r3 = this.currentUser;
        if (this.asAdmin == false) goto L13;
        TLRPC.TL_chatAdminRights r13 = this.adminRights;
    L12:
        TLRPC.TL_chatAdminRights r4 = r13;
        r0.setUserAdminRole(r1, r3, r4, this.currentRank, false, this, this.isAddingNew, this.asAdmin, this.botHash, r8, new ChatRightsEditActivity$$ExternalSyntheticLambda15(this));
        return;
    L13:
        r13 = emptyAdminRights(false);
        goto L12
    }

    private /* synthetic */ void lambda$setLoading$29(ValueAnimator r2) {
        this.doneDrawable.setProgress(((Float) r2.getAnimatedValue()).floatValue());
        this.doneDrawable.invalidateSelf();
    }

    private /* synthetic */ void lambda$updateAsAdmin$32(ValueAnimator r1) {
        this.asAdminT = ((Float) r1.getAnimatedValue()).floatValue();
        FrameLayout r0 = this.addBotButton;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private void onDonePressed() {
        onDonePressed(true);
    }

    private void onDonePressed(boolean r20) {
        ChatRightsEditActivity r0 = this;
        if (r0.loading == true) goto L162;
        int r4 = 2;
        if (ChatObject.isChannel(r0.currentChat) == true) goto L24;
        int r1 = r0.currentType;
        if (r1 == 1) goto L22;
        if (r1 != 0) goto L17;
        if (r0.isDefaultAdminRights() == false) goto L22;
        if (r0.rankRow == (-1)) goto L17;
        String r12 = r0.currentRank;
        if (r12.codePointCount(0, r12.length()) <= 16) goto L22;
    L17:
        if (r0.currentType != 2) goto L24;
        if (r0.currentRank != null) goto L22;
        if (r0.isDefaultAdminRights() == true) goto L24;
    L22:
        MessagesController.getInstance(r0.currentAccount).convertToMegaGroup(r0.getParentActivity(), r0.chatId, r0, new ChatRightsEditActivity$$ExternalSyntheticLambda6(r0));
        return;
    L24:
        if (r20 == false) goto L36;
        if (r0.isCommunity == false) goto L36;
        if (r0.initialAsAdmin == true) goto L36;
        TLRPC.TL_chatAdminRights r13 = r0.adminRights;
        if (r13.manage_linked_peers == false) goto L36;
        if (r13.change_info == false) goto L36;
        AlertsCreator.showSimpleConfirmAlert(r0, LocaleController.getString(R.string.CommunityMakeAdminTitle), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.CommunityMakeAdminMessage, new Object[]{DialogObject.getShortName(r0.currentUser)})), LocaleController.getString(R.string.CommunityMakeAdminPromote), false, new ChatRightsEditActivity$$ExternalSyntheticLambda7(r0));
        return;
    L36:
        if (r0.rankRow == (-1)) goto L43;
        String r14 = r0.currentRank;
        if (r14 == null) goto L43;
        if (r14.codePointCount(0, r14.length()) <= 16) goto L43;
        r0.listView.smoothScrollToPosition(r0.rankRow);
        RecyclerView.ViewHolder r02 = r0.listView.findViewHolderForAdapterPosition(r0.rankRow);
        r02.itemView.performHapticFeedback(3, 2);
        AndroidUtilities.shakeView(r02.itemView);
        return;
    L43:
        int r15 = r0.currentType;
        if (r15 == 0) goto L46;
        if (r15 == 2) goto L46;
    L89:
        if (r15 == 0) goto L91;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r15 == 1) goto L104;
        if (r15 != 2) goto L157;
        AlertDialog.Builder r16 = new AlertDialog.Builder(r0.getParentActivity());
        if (r0.asAdmin == false) goto L134;
        String r42 = LocaleController.getString(R.string.AddBotAdmin);
    L135:
        r16.setTitle(r42);
        if (ChatObject.isChannel(r0.currentChat) == true) goto L138;
    L140:
        boolean r5 = false;
    L141:
        TLRPC.Chat r43 = r0.currentChat;
        if (r43 == null) goto L146;
        r3 = r43.title;
    L146:
        if (r0.asAdmin == false) goto L150;
        if (r5 == false) goto L149;
        String r32 = LocaleController.formatString(R.string.AddBotMessageAdminChannel, new Object[]{r3});
    L151:
        r16.setMessage(AndroidUtilities.replaceTags(r32));
        r16.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        if (r0.asAdmin == false) goto L155;
        int r2 = R.string.AddAsAdmin;
    L156:
        r16.setPositiveButton(LocaleController.getString(r2), new ChatRightsEditActivity$$ExternalSyntheticLambda10(r0));
        r0.showDialog(r16.create());
        boolean r52 = false;
    L158:
        if (r52 == false) goto L161;
        r0.finishFragment();
        return;
    L161:
        return;
    L155:
        r2 = R.string.AddBot;
        goto L156
    L149:
        r32 = LocaleController.formatString(R.string.AddBotMessageAdminGroup, new Object[]{r3});
        goto L151
    L150:
        r32 = LocaleController.formatString(R.string.AddMembersAlertNamesText, new Object[]{UserObject.getUserName(r0.currentUser), r3});
        goto L151
    L138:
        if (r0.currentChat.megagroup == true) goto L140;
        r5 = true;
        goto L141
    L134:
        r42 = LocaleController.getString(R.string.AddBot);
    L157:
        r52 = true;
        goto L158
    L104:
        if (r0.rankRow < 0) goto L110;
        TLRPC.TL_messages_editChatParticipantRank r17 = new TLRPC.TL_messages_editChatParticipantRank();
        r17.peer = MessagesController.getInputPeer(r0.currentChat);
        r17.participant = MessagesController.getInputPeer(r0.currentUser);
        String r53 = r0.currentRank;
        if (r53 == null) goto L109;
        r3 = r53;
    L109:
        r17.rank = r3;
        ConnectionsManager.getInstance(r0.currentAccount).sendRequest(r17, null);
    L110:
        MessagesController.getInstance(r0.currentAccount).setParticipantBannedRole(r0.chatId, r0.currentUser, null, r0.bannedRights, r0.isChannel, r0.getFragmentForAlert(1));
        TLRPC.TL_chatBannedRights r18 = r0.bannedRights;
        if (r18.send_messages == false) goto L113;
    L126:
        r4 = 1;
    L127:
        ChatRightsEditActivityDelegate r22 = r0.delegate;
        if (r22 == null) goto L157;
        r22.didSetRights(r4, r0.adminRights, r18, r0.currentRank);
        goto L157
    L113:
        if (r18.send_stickers == true) goto L126;
        if (r18.embed_links == true) goto L126;
        if (r18.send_media == true) goto L126;
        if (r18.send_gifs == true) goto L126;
        if (r18.send_games == true) goto L126;
        if (r18.send_inline == true) goto L126;
        r18.until_date = 0;
        goto L127
    L91:
        if (r0.delegate != null) goto L93;
        boolean r132 = true;
    L94:
        r0.setLoading(true);
        MessagesController r19 = MessagesController.getInstance(r0.currentAccount);
        long r110 = r0.chatId;
        TLRPC.User r33 = r0.currentUser;
        TLRPC.TL_chatAdminRights r44 = r0.adminRights;
        boolean r8 = true;
        String r54 = r0.currentRank;
        boolean r6 = r0.isChannel;
        if (r0.isAddingNew == true) goto L97;
    L99:
        r8 = false;
    L100:
        r19.setUserAdminRole(r110, r33, r44, r54, r6, r0, r8, false, null, new ChatRightsEditActivity$$ExternalSyntheticLambda8(r0), new ChatRightsEditActivity$$ExternalSyntheticLambda9(r0));
        r0 = r0;
        r52 = r132;
        goto L158
    L97:
        if (r0.isCommunity == true) goto L99;
    L93:
        r132 = false;
    L46:
        boolean r23 = r0.isChannel;
        TLRPC.TL_chatAdminRights r34 = r0.adminRights;
        if (r23 == false) goto L49;
        r34.pin_messages = false;
        r34.manage_ranks = false;
    L50:
        TLRPC.TL_chatAdminRights r35 = r0.adminRights;
        if (r35.change_info == false) goto L53;
    L88:
        r35.other = false;
        goto L89
    L53:
        if (r35.post_messages == true) goto L88;
        if (r35.edit_messages == true) goto L88;
        if (r35.manage_direct_messages == true) goto L88;
        if (r35.manage_welcome_messages == true) goto L88;
        if (r35.delete_messages == true) goto L88;
        if (r35.ban_users == true) goto L88;
        if (r35.invite_users == true) goto L88;
        if (r0.isForum == false) goto L71;
        if (r35.manage_topics == true) goto L88;
    L71:
        if (r35.pin_messages == true) goto L88;
        if (r35.manage_ranks == true) goto L88;
        if (r35.add_admins == true) goto L88;
        if (r35.anonymous == true) goto L88;
        if (r35.manage_call == true) goto L88;
        if (r23 == true) goto L82;
    L87:
        r35.other = true;
        goto L89
    L82:
        if (r35.post_stories == true) goto L88;
        if (r35.edit_stories == true) goto L88;
        if (r35.delete_stories == true) goto L88;
    L49:
        r34.edit_messages = false;
        r34.post_messages = false;
        goto L50
    }

    public static TLRPC.TL_chatAdminRights rightsOR(TLRPC.TL_chatAdminRights r4, TLRPC.TL_chatAdminRights r5) {
        TLRPC.TL_chatAdminRights r0 = new TLRPC.TL_chatAdminRights();
        boolean r2 = true;
        if (r4.change_info == false) goto L5;
    L8:
        boolean r1 = true;
    L9:
        r0.change_info = r1;
        if (r4.post_messages == false) goto L12;
    L15:
        boolean r12 = true;
    L16:
        r0.post_messages = r12;
        if (r4.edit_messages == false) goto L19;
    L22:
        boolean r13 = true;
    L23:
        r0.edit_messages = r13;
        if (r4.delete_messages == false) goto L26;
    L29:
        boolean r14 = true;
    L30:
        r0.delete_messages = r14;
        if (r4.ban_users == false) goto L33;
    L36:
        boolean r15 = true;
    L37:
        r0.ban_users = r15;
        if (r4.invite_users == false) goto L40;
    L43:
        boolean r16 = true;
    L44:
        r0.invite_users = r16;
        if (r4.pin_messages == false) goto L47;
    L50:
        boolean r17 = true;
    L51:
        r0.pin_messages = r17;
        if (r4.manage_ranks == false) goto L54;
    L57:
        boolean r18 = true;
    L58:
        r0.manage_ranks = r18;
        if (r4.add_admins == false) goto L61;
    L64:
        boolean r19 = true;
    L65:
        r0.add_admins = r19;
        if (r4.manage_call == false) goto L68;
    L71:
        boolean r110 = true;
    L72:
        r0.manage_call = r110;
        if (r4.manage_topics == false) goto L75;
    L78:
        boolean r111 = true;
    L79:
        r0.manage_topics = r111;
        if (r4.post_stories == false) goto L82;
    L85:
        boolean r112 = true;
    L86:
        r0.post_stories = r112;
        if (r4.edit_stories == false) goto L89;
    L92:
        boolean r113 = true;
    L93:
        r0.edit_stories = r113;
        if (r4.delete_stories == false) goto L96;
    L99:
        boolean r114 = true;
    L100:
        r0.delete_stories = r114;
        if (r4.manage_direct_messages == false) goto L103;
    L106:
        boolean r115 = true;
    L107:
        r0.manage_direct_messages = r115;
        if (r4.manage_welcome_messages == false) goto L110;
    L113:
        boolean r116 = true;
    L114:
        r0.manage_welcome_messages = r116;
        if (r4.manage_linked_peers == false) goto L117;
    L120:
        r0.manage_linked_peers = r2;
        return r0;
    L117:
        if (r5.manage_linked_peers == true) goto L120;
        r2 = false;
        goto L120
    L110:
        if (r5.manage_welcome_messages == true) goto L113;
        r116 = false;
        goto L114
    L103:
        if (r5.manage_direct_messages == true) goto L106;
        r115 = false;
        goto L107
    L96:
        if (r5.delete_stories == true) goto L99;
        r114 = false;
        goto L100
    L89:
        if (r5.edit_stories == true) goto L92;
        r113 = false;
        goto L93
    L82:
        if (r5.post_stories == true) goto L85;
        r112 = false;
        goto L86
    L75:
        if (r5.manage_topics == true) goto L78;
        r111 = false;
        goto L79
    L68:
        if (r5.manage_call == true) goto L71;
        r110 = false;
        goto L72
    L61:
        if (r5.add_admins == true) goto L64;
        r19 = false;
        goto L65
    L54:
        if (r5.manage_ranks == true) goto L57;
        r18 = false;
        goto L58
    L47:
        if (r5.pin_messages == true) goto L50;
        r17 = false;
        goto L51
    L40:
        if (r5.invite_users == true) goto L43;
        r16 = false;
        goto L44
    L33:
        if (r5.ban_users == true) goto L36;
        r15 = false;
        goto L37
    L26:
        if (r5.delete_messages == true) goto L29;
        r14 = false;
        goto L30
    L19:
        if (r5.edit_messages == true) goto L22;
        r13 = false;
        goto L23
    L12:
        if (r5.post_messages == true) goto L15;
        r12 = false;
        goto L16
    L5:
        if (r5.change_info == true) goto L8;
        r1 = false;
        goto L9
    }

    private void setChannelMessagesEnabled(boolean r3) {
        TLRPC.TL_chatAdminRights r0 = this.adminRights;
        r0.post_messages = !r3;
        r0.edit_messages = !r3;
        r0.delete_messages = !r3;
        AndroidUtilities.updateVisibleRows(this.listView);
    }

    private void setChannelStoriesEnabled(boolean r3) {
        TLRPC.TL_chatAdminRights r0 = this.adminRights;
        r0.post_stories = !r3;
        r0.edit_stories = !r3;
        r0.delete_stories = !r3;
        AndroidUtilities.updateVisibleRows(this.listView);
    }

    private void setGuardBotImpl(long r12) {
        AlertDialog[] r0 = {new AlertDialog(getContext(), 3)};
        getMessagesController().toggleChatJoinRequest(this.chatId, r12, true, false, true, new ChatRightsEditActivity$$ExternalSyntheticLambda22(r0), new ChatRightsEditActivity$$ExternalSyntheticLambda23(r0));
        r0[0].showDelayed(300);
    }

    private void setSendMediaEnabled(boolean r3) {
        TLRPC.TL_chatBannedRights r0 = this.bannedRights;
        r0.send_media = !r3;
        r0.send_photos = !r3;
        r0.send_videos = !r3;
        r0.send_stickers = !r3;
        r0.send_gifs = !r3;
        r0.send_games = !r3;
        r0.send_inline = !r3;
        r0.send_audios = !r3;
        r0.send_docs = !r3;
        r0.send_voices = !r3;
        r0.send_roundvideos = !r3;
        r0.embed_links = !r3;
        r0.send_polls = !r3;
        r0.send_reactions = !r3;
        AndroidUtilities.updateVisibleRows(this.listView);
    }

    private void setTextLeft(View r3) {
        if ((r3 instanceof HeaderCell) == false) goto L17;
        HeaderCell r32 = (HeaderCell) r3;
        String r2 = this.currentRank;
        int r0 = 0;
        if (r2 == null) goto L7;
        r0 = r2.codePointCount(0, r2.length());
    L7:
        int r22 = 16 - r0;
        if (r22 > 4.8f) goto L15;
        r32.setText2(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r22)}));
        SimpleTextView r33 = r32.getTextView2();
        if (r22 >= 0) goto L12;
        int r23 = Theme.key_text_RedRegular;
    L13:
        r33.setTextColor(Theme.getColor(r23));
        r33.setTag(Integer.valueOf(r23));
        return;
    L12:
        r23 = Theme.key_windowBackgroundWhiteGrayText3;
        goto L13
    L15:
        r32.setText2(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    }

    private void updateAsAdmin(boolean r9) {
        FrameLayout r0 = this.addBotButton;
        if (r0 == null) goto L5;
        r0.invalidate();
    L5:
        int r02 = this.listView.getChildCount();
        int r2 = 0;
    L6:
        boolean r3 = true;
        if (r2 >= r02) goto L105;
        View r4 = this.listView.getChildAt(r2);
        int r5 = this.listView.getChildAdapterPosition(r4);
        if ((r4 instanceof TextCheckCell2) == false) goto L104;
        if (this.asAdmin == true) goto L31;
        if (r5 != this.changeInfoRow) goto L17;
        if (this.defaultBannedRights.change_info == true) goto L17;
    L24:
        TextCheckCell2 r42 = (TextCheckCell2) r4;
        r42.setChecked(true);
        r42.setEnabled(false, false);
    L17:
        if (r5 != this.pinMessagesRow) goto L21;
        if (this.defaultBannedRights.pin_messages == false) goto L24;
    L21:
        if (r5 == this.editTagsRow) goto L23;
    L25:
        TextCheckCell2 r43 = (TextCheckCell2) r4;
        r43.setChecked(false);
        if (r5 == this.manageRow) goto L29;
        r3 = false;
    L29:
        r43.setEnabled(r3, r9);
        goto L104
    L23:
        if (this.defaultBannedRights.edit_rank == true) goto L25;
    L31:
        if (r5 != this.manageRow) goto L41;
        if (this.myAdminRights.add_admins == true) goto L102;
        TLRPC.Chat r52 = this.currentChat;
        if (r52 != null) goto L37;
    L39:
        boolean r53 = false;
    L103:
        TextCheckCell2 r44 = (TextCheckCell2) r4;
        r44.setChecked(r3);
        r44.setEnabled(r53, r9);
        goto L104
    L37:
        if (r52.creator == false) goto L39;
    L102:
        r53 = r3;
        goto L103
    L41:
        if (r5 != this.changeInfoRow) goto L50;
        boolean r54 = this.adminRights.change_info;
        if (this.myAdminRights.change_info == true) goto L45;
    L47:
        r3 = false;
    L48:
        boolean r7 = r54;
        r53 = r3;
        r3 = r7;
        goto L103
    L45:
        if (this.defaultBannedRights.change_info == false) goto L47;
    L50:
        if (r5 != this.postMessagesRow) goto L53;
        r3 = this.adminRights.post_messages;
        r53 = this.myAdminRights.post_messages;
        goto L103
    L53:
        if (r5 != this.manageDirectRow) goto L56;
        r3 = this.adminRights.manage_direct_messages;
        r53 = this.myAdminRights.manage_direct_messages;
        goto L103
    L56:
        if (r5 != this.manageWelcomeRow) goto L59;
        r3 = this.adminRights.manage_welcome_messages;
        r53 = this.myAdminRights.manage_welcome_messages;
        goto L103
    L59:
        if (r5 != this.editMesagesRow) goto L62;
        r3 = this.adminRights.edit_messages;
        r53 = this.myAdminRights.edit_messages;
        goto L103
    L62:
        if (r5 != this.deleteMessagesRow) goto L65;
        r3 = this.adminRights.delete_messages;
        r53 = this.myAdminRights.delete_messages;
        goto L103
    L65:
        if (r5 != this.banUsersRow) goto L68;
        r3 = this.adminRights.ban_users;
        r53 = this.myAdminRights.ban_users;
        goto L103
    L68:
        if (r5 != this.addUsersRow) goto L71;
        r3 = this.adminRights.invite_users;
        r53 = this.myAdminRights.invite_users;
        goto L103
    L71:
        if (r5 != this.pinMessagesRow) goto L78;
        r54 = this.adminRights.pin_messages;
        if (this.myAdminRights.pin_messages == false) goto L47;
        if (this.defaultBannedRights.pin_messages == false) goto L47;
    L78:
        if (r5 != this.editTagsRow) goto L81;
        r3 = this.adminRights.manage_ranks;
        r53 = this.myAdminRights.manage_ranks;
        goto L103
    L81:
        if (r5 != this.startVoiceChatRow) goto L84;
        r3 = this.adminRights.manage_call;
        r53 = this.myAdminRights.manage_call;
        goto L103
    L84:
        if (r5 != this.addAdminsRow) goto L87;
        r3 = this.adminRights.add_admins;
        r53 = this.myAdminRights.add_admins;
        goto L103
    L87:
        if (r5 != this.anonymousRow) goto L96;
        r54 = this.adminRights.anonymous;
        if (this.myAdminRights.anonymous == true) goto L48;
        TLRPC.Chat r6 = this.currentChat;
        if (r6 == null) goto L47;
        if (r6.creator == false) goto L47;
    L96:
        if (r5 != this.manageTopicsRow) goto L99;
        r3 = this.adminRights.manage_topics;
        r53 = this.myAdminRights.manage_topics;
        goto L103
    L99:
        if (r5 != this.manageLinkedPeersRow) goto L101;
        r3 = this.adminRights.manage_linked_peers;
        r53 = this.myAdminRights.manage_linked_peers;
        goto L103
    L101:
        r3 = false;
    L104:
        r2 = r2 + 1;
        goto L6
    L105:
        this.listViewAdapter.notifyDataSetChanged();
        AnimatedTextView r03 = this.addBotButtonText;
        if (r03 == null) goto L113;
        StringBuilder r22 = new StringBuilder();
        r22.append(LocaleController.getString(R.string.AddBotButton));
        r22.append(" ");
        if (this.asAdmin == false) goto L111;
        int r45 = R.string.AddBotButtonAsAdmin;
    L112:
        r22.append(LocaleController.getString(r45));
        r03.setText(r22.toString(), r9, this.asAdmin);
        goto L113
    L111:
        r45 = R.string.AddBotButtonAsMember;
    L113:
        ValueAnimator r04 = this.asAdminAnimator;
        if (r04 == null) goto L116;
        r04.cancel();
        this.asAdminAnimator = null;
    L116:
        float r05 = 0.0f;
        if (r9 == false) goto L128;
        float r92 = this.asAdminT;
        if (this.asAdmin == false) goto L121;
        float r46 = 1.0f;
    L122:
        ValueAnimator r93 = ValueAnimator.ofFloat(new float[]{r92, r46});
        this.asAdminAnimator = r93;
        r93.addUpdateListener(new ChatRightsEditActivity$$ExternalSyntheticLambda13(this));
        ValueAnimator r94 = this.asAdminAnimator;
        float r1 = this.asAdminT;
        if (this.asAdmin == false) goto L125;
        r05 = 1.0f;
    L125:
        r94.setDuration((long) (Math.abs(r1 - r05) * 200.0f));
        this.asAdminAnimator.start();
        return;
    L121:
        r46 = 0.0f;
        goto L122
    L128:
        if (this.asAdmin == false) goto L130;
        r05 = 1.0f;
    L130:
        this.asAdminT = r05;
        FrameLayout r8 = this.addBotButton;
        if (r8 == null) goto L139;
        r8.invalidate();
        return;
    }

    private void updateRows(boolean r10) {
        int r0 = Math.min(this.transferOwnerShadowRow, this.transferOwnerRow);
        this.manageRow = -1;
        this.changeInfoRow = -1;
        this.postMessagesRow = -1;
        this.manageDirectRow = -1;
        this.manageWelcomeRow = -1;
        this.editMesagesRow = -1;
        this.deleteMessagesRow = -1;
        this.addAdminsRow = -1;
        this.anonymousRow = -1;
        this.banUsersRow = -1;
        this.addUsersRow = -1;
        this.pinMessagesRow = -1;
        this.editTagsRow = -1;
        this.sendReactionsRow = -1;
        this.guardBotRow = -1;
        this.guardBotInfoRow = -1;
        this.rightsShadowRow = -1;
        this.removeAdminRow = -1;
        this.removeAdminShadowRow = -1;
        this.cantEditInfoRow = -1;
        this.transferOwnerShadowRow = -1;
        this.transferOwnerRow = -1;
        this.rankHeaderRow = -1;
        this.rankRow = -1;
        this.rankInfoRow = -1;
        this.sendMessagesRow = -1;
        this.sendMediaRow = -1;
        this.channelMessagesRow = -1;
        this.channelPostMessagesRow = -1;
        this.channelEditMessagesRow = -1;
        this.channelDeleteMessagesRow = -1;
        this.channelStoriesRow = -1;
        this.channelPostStoriesRow = -1;
        this.channelEditStoriesRow = -1;
        this.channelDeleteStoriesRow = -1;
        this.sendPhotosRow = -1;
        this.sendVideosRow = -1;
        this.sendMusicRow = -1;
        this.sendFilesRow = -1;
        this.sendVoiceRow = -1;
        this.sendRoundRow = -1;
        this.sendStickersRow = -1;
        this.sendPollsRow = -1;
        this.embedLinksRow = -1;
        this.startVoiceChatRow = -1;
        this.untilSectionRow = -1;
        this.untilDateRow = -1;
        this.addBotButtonRow = -1;
        this.manageTopicsRow = -1;
        this.manageLinkedPeersRow = -1;
        this.rowCount = 3;
        this.permissionsStartRow = 3;
        int r3 = this.currentType;
        if (r3 == 0) goto L15;
        if (r3 == 2) goto L15;
        if (r3 != 1) goto L42;
        int r32 = 3 + 1;
        this.sendMessagesRow = 3;
        int r6 = r32 + 1;
        this.rowCount = r6;
        this.sendMediaRow = r32;
        if (this.sendMediaExpanded == false) goto L10;
        this.sendPhotosRow = r6;
        this.sendVideosRow = r32 + 2;
        this.sendFilesRow = r32 + 3;
        this.sendMusicRow = r32 + 4;
        this.sendVoiceRow = r32 + 5;
        this.sendRoundRow = r32 + 6;
        this.sendStickersRow = r32 + 7;
        this.sendPollsRow = r32 + 8;
        int r7 = r32 + 10;
        this.embedLinksRow = r32 + 9;
        this.rowCount = r32 + 11;
        this.sendReactionsRow = r7;
    L10:
        int r33 = this.rowCount;
        this.addUsersRow = r33;
        this.pinMessagesRow = r33 + 1;
        this.editTagsRow = r33 + 2;
        int r72 = r33 + 4;
        this.rowCount = r72;
        this.changeInfoRow = r33 + 3;
        if (this.isForum == false) goto L13;
        this.rowCount = r33 + 5;
        this.manageTopicsRow = r72;
    L13:
        int r34 = this.rowCount;
        int r62 = r34 + 1;
        this.untilSectionRow = r34;
        this.rowCount = r34 + 2;
        this.untilDateRow = r62;
    L42:
        int r35 = this.rowCount;
        this.permissionsEndRow = r35;
        if (this.canEdit == false) goto L78;
        if (this.isChannel == true) goto L53;
        int r63 = this.currentType;
        if (r63 == 0) goto L52;
        if (r63 == 2) goto L50;
    L51:
        if (r63 != 1) goto L53;
    L50:
        if (this.asAdmin == false) goto L51;
    L52:
        this.rightsShadowRow = r35;
        int r64 = r35 + 2;
        this.rankRow = r35 + 1;
        this.rowCount = r35 + 3;
        this.rankInfoRow = r64;
    L53:
        TLRPC.Chat r2 = this.currentChat;
        if (r2 == null) goto L72;
        if (r2.creator == false) goto L72;
        if (this.currentType != 0) goto L72;
        if (hasAllAdminRights() == false) goto L72;
        if (this.currentUser.bot == true) goto L72;
        if (this.isCommunity == true) goto L72;
        int r22 = this.rightsShadowRow;
        if (r22 != (-1)) goto L68;
        int r36 = this.rowCount;
        this.rowCount = r36 + 1;
        this.transferOwnerShadowRow = r36;
    L68:
        int r37 = this.rowCount;
        int r5 = r37 + 1;
        this.rowCount = r5;
        this.transferOwnerRow = r37;
        if (r22 == (-1)) goto L72;
        this.rowCount = r37 + 2;
        this.transferOwnerShadowRow = r5;
    L72:
        if (this.initialIsSet == false) goto L96;
        if (this.rightsShadowRow != (-1)) goto L76;
        int r23 = this.rowCount;
        this.rowCount = r23 + 1;
        this.rightsShadowRow = r23;
    L76:
        int r24 = this.rowCount;
        int r38 = r24 + 1;
        this.removeAdminRow = r24;
        this.rowCount = r24 + 2;
        this.removeAdminShadowRow = r38;
    L96:
        if (this.currentType != 2) goto L98;
        int r25 = this.rowCount;
        this.rowCount = r25 + 1;
        this.addBotButtonRow = r25;
    L98:
        if (r10 == false) goto L109;
        if (r0 != (-1)) goto L104;
        int r102 = this.transferOwnerShadowRow;
        if (r102 == (-1)) goto L104;
        this.listViewAdapter.notifyItemRangeInserted(Math.min(r102, this.transferOwnerRow), 2);
        return;
    L104:
        if (r0 != (-1)) goto L106;
        return;
    L106:
        if (this.transferOwnerShadowRow != (-1)) goto L111;
        this.listViewAdapter.notifyItemRangeRemoved(r0, 2);
        return;
    L111:
        return;
    L109:
        return;
    L78:
        if (this.currentType == 0) goto L80;
        this.rowCount = r35 + 1;
        this.rightsShadowRow = r35;
        goto L96
    L80:
        if (this.isChannel == false) goto L82;
    L93:
        int r26 = this.rowCount;
        this.rowCount = r26 + 1;
        this.cantEditInfoRow = r26;
        goto L96
    L82:
        if (this.currentRank.isEmpty() == true) goto L84;
    L87:
        int r27 = this.rowCount;
        int r39 = r27 + 1;
        this.rightsShadowRow = r27;
        this.rowCount = r27 + 2;
        this.rankRow = r39;
        if (this.currentChat.creator == true) goto L90;
    L92:
        int r28 = this.rowCount;
        this.rowCount = r28 + 1;
        this.cantEditInfoRow = r28;
        goto L96
    L90:
        if (UserObject.isUserSelf(this.currentUser) == false) goto L92;
        int r29 = this.rowCount;
        this.rowCount = r29 + 1;
        this.rankInfoRow = r29;
        goto L96
    L84:
        if (this.currentChat.creator == false) goto L93;
        if (UserObject.isUserSelf(this.currentUser) == false) goto L93;
    L15:
        if (this.isCommunity == false) goto L18;
        int r310 = 3 + 1;
        this.changeInfoRow = 3;
        this.manageLinkedPeersRow = r310;
        int r73 = r310 + 2;
        this.addAdminsRow = r310 + 1;
        this.rowCount = r310 + 3;
        this.banUsersRow = r73;
        goto L42
    L18:
        if (this.isChannel == false) goto L26;
        int r311 = 3 + 1;
        this.changeInfoRow = 3;
        int r65 = r311 + 1;
        this.rowCount = r65;
        this.channelMessagesRow = r311;
        if (this.channelMessagesExpanded == false) goto L22;
        this.channelPostMessagesRow = r65;
        int r66 = r311 + 3;
        this.channelEditMessagesRow = r311 + 2;
        this.rowCount = r311 + 4;
        this.channelDeleteMessagesRow = r66;
    L22:
        int r312 = this.rowCount;
        int r67 = r312 + 1;
        this.rowCount = r67;
        this.channelStoriesRow = r312;
        if (this.channelStoriesExpanded == false) goto L25;
        this.channelPostStoriesRow = r67;
        int r68 = r312 + 3;
        this.channelEditStoriesRow = r312 + 2;
        this.rowCount = r312 + 4;
        this.channelDeleteStoriesRow = r68;
    L25:
        int r313 = this.rowCount;
        this.manageDirectRow = r313;
        this.manageWelcomeRow = r313 + 1;
        this.addUsersRow = r313 + 2;
        this.startVoiceChatRow = r313 + 3;
        int r69 = r313 + 5;
        this.addAdminsRow = r313 + 4;
        this.rowCount = r313 + 6;
        this.banUsersRow = r69;
        goto L42
    L26:
        if (r3 != 2) goto L28;
        this.rowCount = 3 + 1;
        this.manageRow = 3;
    L28:
        int r610 = this.rowCount;
        this.changeInfoRow = r610;
        this.deleteMessagesRow = r610 + 1;
        this.banUsersRow = r610 + 2;
        this.addUsersRow = r610 + 3;
        int r74 = r610 + 5;
        this.rowCount = r74;
        this.pinMessagesRow = r610 + 4;
        if (r3 == 2) goto L32;
        this.rowCount = r610 + 6;
        this.editTagsRow = r74;
    L32:
        if (ChatObject.isChannel(this.currentChat) == false) goto L36;
        int r314 = this.rowCount;
        int r611 = r314 + 1;
        this.rowCount = r611;
        this.channelStoriesRow = r314;
        if (this.channelStoriesExpanded == false) goto L36;
        this.channelPostStoriesRow = r611;
        int r612 = r314 + 3;
        this.channelEditStoriesRow = r314 + 2;
        this.rowCount = r314 + 4;
        this.channelDeleteStoriesRow = r612;
    L36:
        int r315 = this.rowCount;
        this.manageWelcomeRow = r315;
        this.startVoiceChatRow = r315 + 1;
        this.addAdminsRow = r315 + 2;
        int r75 = r315 + 4;
        this.rowCount = r75;
        this.anonymousRow = r315 + 3;
        if (this.isForum == false) goto L40;
        this.rowCount = r315 + 5;
        this.manageTopicsRow = r75;
    L40:
        if (this.currentUserIsBotGuard == false) goto L42;
        int r316 = this.rowCount;
        int r613 = r316 + 1;
        this.guardBotRow = r316;
        this.rowCount = r316 + 2;
        this.guardBotInfoRow = r613;
        goto L42
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r10) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        int r1 = 1;
        this.actionBar.setAllowOverlayTitle(true);
        int r0 = this.currentType;
        if (r0 != 0) goto L5;
        this.actionBar.setTitle(LocaleController.getString(R.string.EditAdmin));
    L9:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        if (this.canEdit == false) goto L12;
    L17:
        ActionBarMenu r02 = this.actionBar.createMenu();
        Drawable r4 = r10.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r6 = Theme.key_actionBarDefaultIcon;
        r4.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r6), PorterDuff.Mode.MULTIPLY));
        this.doneDrawable = new CrossfadeDrawable(r4, new CircularProgressDrawable(Theme.getColor(r6)));
        r02.addItemWithWidth(1, 0, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        r02.getItem(1).setIcon(this.doneDrawable);
    L18:
        AnonymousClass2 r03 = new AnonymousClass2(this, r10);
        this.fragmentView = r03;
        r03.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        View r04 = this.fragmentView;
        FrameLayout r42 = (FrameLayout) r04;
        r04.setFocusableInTouchMode(true);
        AnonymousClass3 r05 = new AnonymousClass3(this, r10);
        this.listView = r05;
        if (this.currentType == 2) goto L21;
        boolean r5 = true;
    L22:
        r05.setClipChildren(r5);
        AnonymousClass4 r06 = new AnonymousClass4(this, r10, 1, false);
        this.linearLayoutManager = r06;
        r06.setInitialPrefetchItemCount(100);
        this.listView.setLayoutManager(this.linearLayoutManager);
        RecyclerListView r07 = this.listView;
        ListAdapter r52 = new ListAdapter(this, r10);
        this.listViewAdapter = r52;
        r07.setAdapter(r52);
        DefaultItemAnimator r08 = new DefaultItemAnimator();
        if (this.currentType != 2) goto L25;
        this.listView.setResetSelectorOnChanged(false);
    L25:
        r08.setSupportsChangeAnimations(false);
        r08.setDelayAnimations(false);
        r08.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r08.setDurations(350);
        this.listView.setItemAnimator(r08);
        RecyclerListView r09 = this.listView;
        if (LocaleController.isRTL == true) goto L29;
        r1 = 2;
    L29:
        r09.setVerticalScrollbarPosition(r1);
        r42.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setOnScrollListener(new AnonymousClass5(this));
        this.listView.setOnItemClickListener(new ChatRightsEditActivity$$ExternalSyntheticLambda3(this, r10));
        return this.fragmentView;
    L21:
        r5 = false;
        goto L22
    L12:
        if (this.isChannel == true) goto L18;
        if (this.currentChat.creator == false) goto L18;
        if (UserObject.isUserSelf(this.currentUser) == false) goto L18;
    L5:
        ActionBar r3 = this.actionBar;
        if (r0 != 2) goto L8;
        r3.setTitle(LocaleController.getString(R.string.AddBot));
        goto L9
    L8:
        r3.setTitle(LocaleController.getString(R.string.UserRestrictions));
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.chatInfoDidLoad) goto L11;
        TLRPC.ChatFull r32 = (TLRPC.ChatFull) r5[0];
        TLRPC.Chat r42 = this.currentChat;
        if (r42 != null) goto L7;
        return;
    L7:
        if (r32.id != r42.id) goto L23;
        this.chatInfo = r32;
        checkGuardBotRow();
        return;
    L23:
        return;
    L11:
        if (r3 != NotificationCenter.dialogDeleted) goto L24;
        long r33 = ((Long) r5[0]).longValue();
        if ((-this.chatId) != r33) goto L25;
        INavigationLayout r34 = this.parentLayout;
        if (r34 != null) goto L17;
    L20:
        removeSelfFromStack();
        return;
    L17:
        if (r34.getLastFragment() != this) goto L20;
        finishFragment();
        return;
    L25:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ChatRightsEditActivity$$ExternalSyntheticLambda2 r8 = new ChatRightsEditActivity$$ExternalSyntheticLambda2(this);
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{UserCell2.class, TextSettingsCell.class, TextCheckCell2.class, HeaderCell.class, TextDetailCell.class, PollEditTextCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        ActionBar r12 = this.actionBar;
        int r13 = ThemeDescription.FLAG_BACKGROUND;
        int r18 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r12, r13, null, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r18));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        int r21 = Theme.key_text_RedRegular;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r21));
        int r20 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueImageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextDetailCell.class}, new String[]{"textView"}, null, null, null, r20));
        int r35 = Theme.key_windowBackgroundWhiteGrayText2;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextDetailCell.class}, new String[]{"valueTextView"}, null, null, null, r35));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell2.class}, new String[]{"textView"}, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell2.class}, new String[]{"valueTextView"}, null, null, null, r35));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell2.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switch2Track));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell2.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switch2TrackChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{HeaderCell.class}, new String[]{"textView2"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{HeaderCell.class}, new String[]{"textView2"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText3));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{PollEditTextCell.class}, new String[]{"textView"}, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_HINTTEXTCOLOR, new Class[]{PollEditTextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell2.class}, new String[]{"nameTextView"}, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell2.class}, new String[]{"statusColor"}, null, null, r8, Theme.key_windowBackgroundWhiteGrayText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell2.class}, new String[]{"statusOnlineColor"}, null, null, r8, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell2.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(null, 0, new Class[]{DialogRadioCell.class}, new String[]{"textView"}, null, null, null, Theme.key_dialogTextBlack));
        r1.add(new ThemeDescription(null, 0, new Class[]{DialogRadioCell.class}, new String[]{"textView"}, null, null, null, Theme.key_dialogTextGray2));
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_CHECKBOX, new Class[]{DialogRadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_dialogRadioBackground));
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{DialogRadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_dialogRadioBackgroundChecked));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r1) {
        return checkDiscard(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.dialogDeleted);
        getNotificationCenter().addObserver(this, NotificationCenter.chatInfoDidLoad);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.dialogDeleted);
        getNotificationCenter().removeObserver(this, NotificationCenter.chatInfoDidLoad);
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listViewAdapter;
        if (r0 == null) goto L5;
        r0.notifyDataSetChanged();
    L5:
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
    }

    public void setDelegate(ChatRightsEditActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setLoading(boolean r5) {
        ValueAnimator r0 = this.doneDrawableAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.loading = r5;
        this.actionBar.getBackButton().setEnabled(!this.loading);
        CrossfadeDrawable r52 = this.doneDrawable;
        if (r52 == null) goto L13;
        float r53 = r52.getProgress();
        if (this.loading == false) goto L10;
        float r02 = 1.0f;
    L11:
        ValueAnimator r54 = ValueAnimator.ofFloat(new float[]{r53, r02});
        this.doneDrawableAnimator = r54;
        r54.addUpdateListener(new ChatRightsEditActivity$$ExternalSyntheticLambda12(this));
        this.doneDrawableAnimator.addListener(new AnonymousClass6(this));
        this.doneDrawableAnimator.setDuration((long) (Math.abs(this.doneDrawable.getProgress() - (this.loading ? 1.0f : 0.0f)) * 150.0f));
        this.doneDrawableAnimator.start();
        return;
    L10:
        r02 = 0.0f;
        goto L11
    }
}
