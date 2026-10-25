package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public class SelectChatUserSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private ParticipantsList admins;
    private View bottomGradient;
    private ButtonWithCounterView button;
    private TLRPC.Chat chat;
    private Context context;
    private FrameLayout emptySearchView;
    private final TLObject initialOwner;
    private ParticipantsList members;
    private ParticipantsList search;
    private FrameLayout searchBox;
    private FrameLayout searchContainer;
    private EditTextBoldCursor searchEdit;
    private TLObject selectedOwner;
    private final Runnable whenTransferred;

    /* renamed from: org.telegram.ui.SelectChatUserSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextBoldCursor {
        final /* synthetic */ SelectChatUserSheet this$0;

        public AnonymousClass1(SelectChatUserSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (r2.getAction() != 0) goto L8;
            if (AndroidUtilities.showKeyboard(this) == true) goto L8;
            clearFocus();
            requestFocus();
        L8:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.SelectChatUserSheet$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ SelectChatUserSheet this$0;
        private final Runnable updateSearchRunnable;

        /* renamed from: $r8$lambda$IqAgV-I2h5eMU3Ncyjf41riTic0, reason: not valid java name */
        public static /* synthetic */ void m18774$r8$lambda$IqAgVI2h5eMU3Ncyjf41riTic0(AnonymousClass2 r0) {
            r0.lambda$$0();
        }

        public AnonymousClass2(SelectChatUserSheet r1) {
            this.this$0 = r1;
            this.updateSearchRunnable = new SelectChatUserSheet$2$$ExternalSyntheticLambda0(this);
        }

        private /* synthetic */ void lambda$$0() {
            updateSearch();
        }

        private void updateSearch() {
            TLRPC.TL_channelParticipantsSearch r0 = new TLRPC.TL_channelParticipantsSearch();
            r0.q = SelectChatUserSheet.m18771$$Nest$fgetsearchEdit(this.this$0).getText().toString();
            SelectChatUserSheet.m18770$$Nest$fgetsearch(this.this$0).setFilter(r0);
            SelectChatUserSheet.m18772$$Nest$mupdate(this.this$0);
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            int r32 = r3.length();
            Runnable r0 = this.updateSearchRunnable;
            if (r32 > 0) goto L6;
            AndroidUtilities.cancelRunOnUIThread(r0);
            updateSearch();
            return;
        L6:
            AndroidUtilities.cancelRunOnUIThread(r0);
            AndroidUtilities.runOnUIThread(this.updateSearchRunnable, 300);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.SelectChatUserSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ SelectChatUserSheet this$0;

        public AnonymousClass3(SelectChatUserSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(240.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.SelectChatUserSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends DefaultItemAnimator {
        final /* synthetic */ SelectChatUserSheet this$0;

        public AnonymousClass4(SelectChatUserSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onMoveAnimationUpdate(r1);
            SelectChatUserSheet.m18773$$Nest$mupdateSearchY(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.SelectChatUserSheet$5, reason: invalid class name */
    public class AnonymousClass5 implements View.OnLayoutChangeListener {
        final /* synthetic */ SelectChatUserSheet this$0;

        public AnonymousClass5(SelectChatUserSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            SelectChatUserSheet.m18773$$Nest$mupdateSearchY(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.SelectChatUserSheet$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SelectChatUserSheet this$0;

        public AnonymousClass6(SelectChatUserSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (SelectChatUserSheet.access$000(this.this$0).scrollingByUser == false) goto L5;
            AndroidUtilities.hideKeyboard(SelectChatUserSheet.m18771$$Nest$fgetsearchEdit(this.this$0));
        L5:
            SelectChatUserSheet.m18773$$Nest$mupdateSearchY(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.SelectChatUserSheet$7, reason: invalid class name */
    public class AnonymousClass7 extends View {
        private final LinearGradient gradient;
        private final Paint paint;
        final /* synthetic */ SelectChatUserSheet this$0;

        public AnonymousClass7(SelectChatUserSheet r10, Context r11) {
            this.this$0 = r10;
            super(r11);
            this.paint = new Paint(1);
            float r5 = AndroidUtilities.dp(68.0f);
            int r112 = Theme.key_dialogBackground;
            this.gradient = new LinearGradient(0.0f, 0.0f, 0.0f, r5, new int[]{Theme.multAlpha(SelectChatUserSheet.access$100(r10, r112), 0.0f), SelectChatUserSheet.access$200(r10, r112)}, new float[]{0.0f, 0.2f}, Shader.TileMode.CLAMP);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            this.paint.setShader(this.gradient);
            r8.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.paint);
        }
    }

    public static class ParticipantsList implements NotificationCenter.NotificationCenterDelegate {
        private boolean attached;
        private final TLRPC.Chat chat;
        private TLRPC.ChatFull chatInfo;
        private boolean clearOnLoad;
        private final int currentAccount;
        public boolean endReached;
        public TLRPC.ChannelParticipantsFilter filter;
        private ArrayList<Runnable> listeners;
        public boolean loading;
        private int requestId;
        public final ArrayList<TLObject> users;

        public static /* synthetic */ void $r8$lambda$ZXBel_Twkmis7L4JjHa6vEDsPqk(ParticipantsList r0, TLRPC.channels_ChannelParticipants r1, TLRPC.TL_error r2) {
            r0.lambda$load$0(r1, r2);
        }

        public ParticipantsList(int r3, long r4, TLRPC.ChannelParticipantsFilter r6) {
            this.users = new ArrayList();
            this.listeners = new ArrayList();
            this.requestId = -1;
            this.currentAccount = r3;
            this.chat = MessagesController.getInstance(r3).getChat(Long.valueOf(r4));
            TLRPC.ChatFull r0 = MessagesController.getInstance(r3).getChatFull(r4);
            this.chatInfo = r0;
            this.filter = r6;
            if (r0 != null) goto L6;
            attach();
            MessagesController.getInstance(r3).loadFullChat(r4, 0, false);
            return;
        }

        private void emit() {
            ArrayList<Runnable> r3 = this.listeners;
            int r0 = r3.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            Runnable r2 = r3.get(r1);
            r1 = r1 + 1;
            r2.run();
            goto L3
        }

        private /* synthetic */ void lambda$load$0(TLRPC.channels_ChannelParticipants r8, TLRPC.TL_error r9) {
            if (r9 != null) goto L5;
            MessagesController.getInstance(this.currentAccount).putUsers(r8.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r8.chats, false);
            if (this.clearOnLoad == false) goto L12;
            this.users.clear();
            this.clearOnLoad = false;
        L12:
            ArrayList<TLRPC.ChannelParticipant> r92 = r8.participants;
            int r2 = r92.size();
            int r3 = 0;
        L13:
            if (r3 >= r2) goto L18;
            TLRPC.ChannelParticipant r4 = r92.get(r3);
            r3 = r3 + 1;
            TLObject r42 = MessagesController.getInstance(this.currentAccount).getUserOrChat(DialogObject.getPeerDialogId(r4.peer));
            if (r42 == null) goto L13;
            this.users.add(r42);
            goto L13
        L18:
            if (r8.participants.size() >= 30) goto L20;
            this.endReached = true;
        L20:
            this.loading = false;
            emit();
            return;
        L5:
            if (this.clearOnLoad == false) goto L7;
            this.users.clear();
            this.clearOnLoad = false;
        L7:
            this.endReached = true;
            this.loading = false;
            emit();
        }

        public void attach() {
            if (this.attached == false) goto L5;
            return;
        L5:
            this.attached = true;
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatInfoDidLoad);
        }

        public void cancel() {
            if (this.requestId < 0) goto L5;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.requestId, true);
            this.requestId = -1;
        L5:
            this.loading = false;
        }

        public void clear() {
            this.clearOnLoad = false;
            cancel();
            this.users.clear();
            this.endReached = false;
        }

        public void detach() {
            if (this.attached == true) goto L5;
            return;
        L5:
            this.attached = false;
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatInfoDidLoad);
            cancel();
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r5, int r6, Object... r7) {
            if (r5 != NotificationCenter.chatInfoDidLoad) goto L12;
            TLRPC.ChatFull r62 = (TLRPC.ChatFull) r7[0];
            long r0 = r62.id;
            TLRPC.Chat r72 = this.chat;
            if (r0 != r72.id) goto L13;
            this.chatInfo = r62;
            if (ChatObject.isChannel(r72) == false) goto L9;
            return;
        L9:
            if (this.loading == false) goto L15;
            this.loading = false;
            load();
            return;
        L15:
            return;
        L13:
            return;
        }

        public ParticipantsList listen(Runnable r2) {
            this.listeners.add(r2);
            return this;
        }

        public void load() {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.endReached == true) goto L22;
            TLRPC.ChannelParticipantsFilter r0 = this.filter;
            if ((r0 instanceof TLRPC.TL_channelParticipantsSearch) == true) goto L10;
        L12:
            this.loading = true;
            if (ChatObject.isChannel(this.chat) == false) goto L24;
            TLRPC.TL_channels_getParticipants r02 = new TLRPC.TL_channels_getParticipants();
            r02.channel = MessagesController.getInputChannel(this.chat);
            r02.filter = this.filter;
            r02.limit = 30;
            if (this.clearOnLoad == false) goto L18;
            int r1 = 0;
        L19:
            r02.offset = r1;
            ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r02, new AiTonesController$$ExternalSyntheticLambda0(), new SelectChatUserSheet$ParticipantsList$$ExternalSyntheticLambda0(this));
            return;
        L18:
            r1 = this.users.size();
            goto L19
        L24:
            return;
        L10:
            if (TextUtils.isEmpty(r0.q) == false) goto L12;
            return;
        }

        public ParticipantsList setFilter(TLRPC.ChannelParticipantsFilter r5) {
            TLRPC.ChannelParticipantsFilter r0 = this.filter;
            if ((r0 instanceof TLRPC.TL_channelParticipantsSearch) == true) goto L5;
        L7:
            boolean r02 = true;
            boolean r1 = true;
        L8:
            this.filter = r5;
            if (r02 == false) goto L14;
            if (r1 == false) goto L12;
            clear();
        L13:
            load();
            goto L14
        L12:
            this.clearOnLoad = true;
            this.endReached = false;
        L14:
            return this;
        L5:
            if ((r5 instanceof TLRPC.TL_channelParticipantsSearch) == false) goto L7;
            r02 = !TextUtils.equals(r0.q, r5.q);
            r1 = false;
            goto L8
        }
    }

    public static /* synthetic */ void $r8$lambda$39PizuykAK0259sNWfKrzIjQATM(SelectChatUserSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$7MAa_oSK16qbGCiJn9fwtMiWl5Y(SelectChatUserSheet r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$new$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Ak9gsq0220uPd26F3YczaLXrojk(SelectChatUserSheet r0, TLRPC.InputCheckPasswordSRP r1, TLRPC.User r2, TwoStepVerificationActivity r3, TLRPC.TL_channels_editCreator r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$initTransfer$13(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$Dx7NhAX1FvVXkZ1tSctC8CXLVCQ(SelectChatUserSheet r0, TLRPC.User r1, AlertDialog r2, int r3) {
        r0.lambda$initTransfer$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$JKNvom3xmFdZlBGEDskLskO5n50(SelectChatUserSheet r0, View r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$LH3xwBwRcIF9kXtFcwtNMKNXOFk(SelectChatUserSheet r0, AlertDialog r1, int r2) {
        r0.lambda$initTransfer$8(r1, r2);
    }

    /* renamed from: $r8$lambda$UPXQpy_X-Cse6vv8kAjG02P2pTI, reason: not valid java name */
    public static /* synthetic */ void m18765$r8$lambda$UPXQpy_XCse6vv8kAjG02P2pTI(SelectChatUserSheet r0, TLRPC.User r1, TwoStepVerificationActivity r2, TLRPC.InputCheckPasswordSRP r3) {
        r0.lambda$initTransfer$4(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Uvp-LW9KmI6yjEtTyzufkIzci0o, reason: not valid java name */
    public static /* synthetic */ void m18766$r8$lambda$UvpLW9KmI6yjEtTyzufkIzci0o(SelectChatUserSheet r0, TwoStepVerificationActivity r1, TLRPC.User r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$initTransfer$11(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$X6wdjn93WtlsuH9G5QgXL0NJAIg(SelectChatUserSheet r0, AlertDialog r1, int r2) {
        r0.lambda$initTransfer$9(r1, r2);
    }

    /* renamed from: $r8$lambda$YpTTXV2Zg-f0XUWx1OnbyQFQlQc, reason: not valid java name */
    public static /* synthetic */ void m18767$r8$lambda$YpTTXV2Zgf0XUWx1OnbyQFQlQc(SelectChatUserSheet r0, TLRPC.TL_error r1, TLObject r2, TwoStepVerificationActivity r3, TLRPC.User r4) {
        r0.lambda$initTransfer$10(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$gEcBmKQRgTpW5QL2eYz0RHfzW6w(SelectChatUserSheet r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: $r8$lambda$gPgWI2TI4wO6yjTMaSHA660gq-g, reason: not valid java name */
    public static /* synthetic */ void m18768$r8$lambda$gPgWI2TI4wO6yjTMaSHA660gqg(SelectChatUserSheet r0, TLRPC.TL_error r1, TLRPC.InputCheckPasswordSRP r2, TLRPC.User r3, TwoStepVerificationActivity r4, TLRPC.TL_channels_editCreator r5) {
        r0.lambda$initTransfer$12(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$oIvWxDNQ4C6m7prjNCIP2ftGs3g(SelectChatUserSheet r0, AlertDialog r1, int r2) {
        r0.lambda$initTransfer$7(r1, r2);
    }

    /* renamed from: $r8$lambda$ooC_llqWyLLXCYyhk1XJhzZ3k-I, reason: not valid java name */
    public static /* synthetic */ void m18769$r8$lambda$ooC_llqWyLLXCYyhk1XJhzZ3kI(SelectChatUserSheet r0) {
        r0.update();
    }

    public static /* synthetic */ void $r8$lambda$po9JToQHflZmoPXyV_Jnwj2a9wc(SelectChatUserSheet r0, AlertDialog r1, int r2) {
        r0.lambda$initTransfer$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ukUd8PnNXWaSzWS6l66e5oGsPC8(SelectChatUserSheet r0, TLRPC.User r1, TLRPC.InputCheckPasswordSRP r2, TwoStepVerificationActivity r3, long r4) {
        r0.lambda$initTransfer$3(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetsearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ParticipantsList m18770$$Nest$fgetsearch(SelectChatUserSheet r0) {
        return r0.search;
    }

    /* renamed from: -$$Nest$fgetsearchEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m18771$$Nest$fgetsearchEdit(SelectChatUserSheet r0) {
        return r0.searchEdit;
    }

    /* renamed from: -$$Nest$mupdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18772$$Nest$mupdate(SelectChatUserSheet r0) {
        r0.update();
    }

    /* renamed from: -$$Nest$mupdateSearchY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18773$$Nest$mupdateSearchY(SelectChatUserSheet r0) {
        r0.updateSearchY();
    }

    public SelectChatUserSheet(Context r19, TLRPC.Chat r20, TLRPC.User r21, Runnable r22, Theme.ResourcesProvider r23) {
        super(r19, null, true, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r23);
        this.headerMoveTop = AndroidUtilities.dp(12.0f);
        this.smoothKeyboardAnimationEnabled = true;
        this.chat = r20;
        this.initialOwner = r21;
        this.selectedOwner = r21;
        this.whenTransferred = r22;
        UserConfig.getInstance(this.currentAccount).getCurrentUser();
        this.searchContainer = new FrameLayout(r19);
        FrameLayout r3 = new FrameLayout(r19);
        this.searchBox = r3;
        r3.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(20.0f), getThemedColor(Theme.key_dialogSearchBackground)));
        ImageView r32 = new ImageView(r19);
        r32.setScaleType(ImageView.ScaleType.CENTER);
        r32.setImageResource(R.drawable.smiles_inputsearch);
        int r5 = Theme.key_dialogSearchHint;
        r32.setColorFilter(new PorterDuffColorFilter(getThemedColor(r5), PorterDuff.Mode.SRC_IN));
        this.searchBox.addView(r32, LayoutHelper.createFrame(24, 24.0f, 19, 11.0f, 0.0f, 0.0f, 0.0f));
        AnonymousClass1 r33 = new AnonymousClass1(this, r19);
        this.searchEdit = r33;
        r33.setTextColor(getThemedColor(Theme.key_dialogSearchText));
        this.searchEdit.setHintTextColor(getThemedColor(r5));
        this.searchEdit.setTextSize(1, 15.0f);
        this.searchEdit.setSingleLine(true);
        this.searchEdit.setBackground(null);
        this.searchEdit.setCursorWidth(1.5f);
        this.searchEdit.setGravity(112);
        this.searchEdit.setClipToPadding(true);
        this.searchEdit.setPadding(AndroidUtilities.dp(46.0f), 0, AndroidUtilities.dp(16.0f), 0);
        this.searchEdit.setTranslationY(-AndroidUtilities.dp(0.66f));
        EditTextBoldCursor r2 = this.searchEdit;
        r2.setInputType(r2.getInputType() | 524288);
        this.searchEdit.setImeOptions(33554435);
        this.searchEdit.setTextIsSelectable(false);
        this.searchEdit.setOnEditorActionListener(new SelectChatUserSheet$$ExternalSyntheticLambda9(this));
        this.searchEdit.addTextChangedListener(new AnonymousClass2(this));
        this.searchEdit.setHint(LocaleController.getString(R.string.SearchMembers));
        this.searchBox.addView(this.searchEdit, LayoutHelper.createFrame(-1, -1, 119));
        this.searchContainer.addView(this.searchBox, LayoutHelper.createFrame(-1, 40.0f, 23, 11.0f, 0.0f, 11.0f, 0.0f));
        ViewGroup r24 = this.containerView;
        FrameLayout r34 = this.searchContainer;
        int r4 = Math.max(0, r24.indexOfChild(this.actionBar));
        int r7 = this.backgroundPaddingLeft;
        float r10 = AndroidUtilities.density;
        r24.addView(r34, r4, LayoutHelper.createFrame(-1, 64.0f, 55, r7 / r10, 0.0f, r7 / r10, 0.0f));
        this.emptySearchView = new AnonymousClass3(this, r19);
        BackupImageView r25 = new BackupImageView(r19);
        r25.setImageDrawable(new RLottieDrawable(R.raw.utyan_empty, AndroidUtilities.dp(130.0f), AndroidUtilities.dp(130.0f)));
        this.emptySearchView.addView(r25, LayoutHelper.createFrame(130, 130, 17));
        this.admins = new ParticipantsList(this.currentAccount, r20.id, new TLRPC.TL_channelParticipantsAdmins()).listen(new SelectChatUserSheet$$ExternalSyntheticLambda10(this));
        this.members = new ParticipantsList(this.currentAccount, r20.id, new TLRPC.TL_channelParticipantsRecent()).listen(new SelectChatUserSheet$$ExternalSyntheticLambda10(this));
        this.search = new ParticipantsList(this.currentAccount, r20.id, new TLRPC.TL_channelParticipantsSearch()).listen(new SelectChatUserSheet$$ExternalSyntheticLambda10(this));
        RecyclerListView r1 = this.recyclerListView;
        int r26 = this.backgroundPaddingLeft;
        r1.setPadding(r26, 0, r26, AndroidUtilities.dp(68.0f));
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setOnItemClickListener(new SelectChatUserSheet$$ExternalSyntheticLambda11(this));
        AnonymousClass4 r12 = new AnonymousClass4(this);
        r12.setSupportsChangeAnimations(false);
        r12.setDelayAnimations(false);
        r12.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r12.setDurations(350);
        this.recyclerListView.setItemAnimator(r12);
        this.recyclerListView.addOnLayoutChangeListener(new AnonymousClass5(this));
        this.recyclerListView.addOnScrollListener(new AnonymousClass6(this));
        AnonymousClass7 r13 = new AnonymousClass7(this, getContext());
        this.bottomGradient = r13;
        this.containerView.addView(r13, LayoutHelper.createFrame(-1, 68, 87));
        ButtonWithCounterView r14 = new ButtonWithCounterView(getContext(), r23).setRound();
        this.button = r14;
        r14.setColor(getThemedColor(Theme.key_fill_RedNormal));
        updateButton(false);
        ViewGroup r15 = this.containerView;
        ButtonWithCounterView r27 = this.button;
        int r35 = this.backgroundPaddingLeft;
        float r6 = AndroidUtilities.density;
        r15.addView(r27, LayoutHelper.createFrame(-1, 48.0f, 87, (r35 / r6) + 10.0f, 10.0f, (r35 / r6) + 10.0f, 10.0f));
        this.button.setOnClickListener(new SelectChatUserSheet$$ExternalSyntheticLambda12(this));
        UniversalAdapter r16 = this.adapter;
        if (r16 == null) goto L5;
        r16.update(false);
    L5:
        this.admins.load();
        this.members.load();
    }

    public static /* synthetic */ RecyclerListView access$000(SelectChatUserSheet r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ int access$100(SelectChatUserSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$200(SelectChatUserSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    private void fillItems(ArrayList<UItem> r14, UniversalAdapter r15) {
        if (this.admins != null) goto L5;
        return;
    L5:
        if (this.members == null) goto L118;
        HashSet r152 = new HashSet();
        r152.add(Long.valueOf(UserConfig.getInstance(this.currentAccount).getClientUserId()));
        r14.add(UItem.asSpace(3, AndroidUtilities.dp(64.0f)));
        ParticipantsList r0 = this.search;
        if (r0 == null) goto L29;
        if (TextUtils.isEmpty(r0.filter.q) == true) goto L29;
        ArrayList<TLObject> r02 = this.search.users;
        int r4 = r02.size();
        int r5 = 0;
    L12:
        if (r5 >= r4) goto L22;
        TLObject r6 = r02.get(r5);
        r5 = r5 + 1;
        TLObject r62 = r6;
        if (r152.contains(Long.valueOf(DialogObject.getDialogId(r62))) == true) goto L12;
        r152.add(Long.valueOf(DialogObject.getDialogId(r62)));
        UItem r7 = UItem.asProfileCell(r62);
        if (DialogObject.getDialogId(r62) != DialogObject.getDialogId(this.selectedOwner)) goto L19;
        boolean r63 = true;
    L20:
        r14.add(r7.setChecked(r63));
        goto L12
    L19:
        r63 = false;
        goto L20
    L22:
        if (this.search.loading == false) goto L25;
        r14.add(UItem.asFlicker(29));
        r14.add(UItem.asFlicker(29));
        r14.add(UItem.asFlicker(29));
    L25:
        if (r14.size() != 1) goto L115;
        r14.add(UItem.asCustom(this.emptySearchView));
        return;
    L115:
        return;
    L29:
        if (this.initialOwner != null) goto L31;
    L43:
        boolean r03 = true;
    L44:
        ArrayList<TLObject> r42 = this.admins.users;
        int r52 = r42.size();
        int r64 = 0;
    L45:
        if (r64 >= r52) goto L61;
        TLObject r72 = r42.get(r64);
        r64 = r64 + 1;
        TLObject r73 = r72;
        if (r152.contains(Long.valueOf(DialogObject.getDialogId(r73))) == true) goto L45;
        if (r03 == true) goto L51;
    L55:
        r152.add(Long.valueOf(DialogObject.getDialogId(r73)));
        UItem r8 = UItem.asProfileCell(r73);
        if (DialogObject.getDialogId(r73) != DialogObject.getDialogId(this.selectedOwner)) goto L58;
        boolean r74 = true;
    L59:
        r14.add(r8.setChecked(r74));
        goto L45
    L58:
        r74 = false;
        goto L59
    L51:
        if (ChatObject.isChannelAndNotMegaGroup(this.chat) == false) goto L53;
        int r04 = R.string.ChannelAdmins;
    L54:
        r14.add(UItem.asGraySection(LocaleController.getString(r04)));
        r03 = false;
        goto L55
    L53:
        r04 = R.string.GroupAdmins;
        goto L54
    L61:
        if (this.admins.loading == false) goto L63;
        r14.add(UItem.asFlicker(29));
        r14.add(UItem.asFlicker(29));
        r14.add(UItem.asFlicker(29));
    L63:
        TLObject r05 = this.initialOwner;
        if (r05 != null) goto L66;
    L76:
        boolean r06 = true;
    L77:
        ArrayList<TLObject> r43 = this.members.users;
        int r53 = r43.size();
        int r65 = 0;
    L78:
        if (r65 >= r53) goto L94;
        TLObject r75 = r43.get(r65);
        r65 = r65 + 1;
        TLObject r76 = r75;
        if (r152.contains(Long.valueOf(DialogObject.getDialogId(r76))) == true) goto L78;
        if (r06 == true) goto L84;
    L88:
        r152.add(Long.valueOf(DialogObject.getDialogId(r76)));
        UItem r82 = UItem.asProfileCell(r76);
        if (DialogObject.getDialogId(r76) != DialogObject.getDialogId(this.selectedOwner)) goto L91;
        boolean r77 = true;
    L92:
        r14.add(r82.setChecked(r77));
        goto L78
    L91:
        r77 = false;
        goto L92
    L84:
        if (ChatObject.isChannelAndNotMegaGroup(this.chat) == false) goto L86;
        int r07 = R.string.ChannelSubscribers2;
    L87:
        r14.add(UItem.asGraySection(LocaleController.getString(r07)));
        r06 = false;
        goto L88
    L86:
        r07 = R.string.GroupMembers2;
        goto L87
    L94:
        if (this.members.users.isEmpty() == false) goto L96;
        return;
    L96:
        if (this.members.loading == false) goto L117;
        r14.add(UItem.asFlicker(29));
        r14.add(UItem.asFlicker(29));
        r14.add(UItem.asFlicker(29));
        return;
    L117:
        return;
    L66:
        if (r152.contains(Long.valueOf(DialogObject.getDialogId(r05))) == true) goto L76;
        if (ChatObject.isChannelAndNotMegaGroup(this.chat) == false) goto L70;
        int r08 = R.string.ChannelSubscribers2;
    L71:
        r14.add(UItem.asGraySection(LocaleController.getString(r08)));
        r152.add(Long.valueOf(DialogObject.getDialogId(this.initialOwner)));
        UItem r09 = UItem.asProfileCell(this.initialOwner);
        if (DialogObject.getDialogId(this.initialOwner) != DialogObject.getDialogId(this.selectedOwner)) goto L74;
        boolean r44 = true;
    L75:
        r14.add(r09.setChecked(r44));
        r06 = false;
        goto L77
    L74:
        r44 = false;
        goto L75
    L70:
        r08 = R.string.GroupMembers2;
        goto L71
    L31:
        if (isInitialOwnerAdmin() == false) goto L43;
        if (r152.contains(Long.valueOf(DialogObject.getDialogId(this.initialOwner))) == true) goto L43;
        if (ChatObject.isChannelAndNotMegaGroup(this.chat) == false) goto L37;
        int r010 = R.string.ChannelAdmins;
    L38:
        r14.add(UItem.asGraySection(LocaleController.getString(r010)));
        r152.add(Long.valueOf(DialogObject.getDialogId(this.initialOwner)));
        UItem r011 = UItem.asProfileCell(this.initialOwner);
        if (DialogObject.getDialogId(this.initialOwner) != DialogObject.getDialogId(this.selectedOwner)) goto L41;
        boolean r45 = true;
    L42:
        r14.add(r011.setChecked(r45));
        r03 = false;
        goto L44
    L41:
        r45 = false;
        goto L42
    L37:
        r010 = R.string.GroupAdmins;
        goto L38
    }

    private void initTransfer(TLRPC.User r10, TLRPC.InputCheckPasswordSRP r11, TwoStepVerificationActivity r12) {
        if (getContext() == null) goto L6;
        this.context = getContext();
    L6:
        if (this.context == null) goto L25;
        if (r11 != null) goto L10;
    L16:
        TLRPC.TL_channels_editCreator r5 = new TLRPC.TL_channels_editCreator();
        if (ChatObject.isChannel(this.chat) == false) goto L19;
        TLRPC.TL_inputChannel r0 = new TLRPC.TL_inputChannel();
        r5.channel = r0;
        TLRPC.Chat r4 = this.chat;
        r0.channel_id = r4.id;
        r0.access_hash = r4.access_hash;
    L20:
        if (r11 == null) goto L22;
        TLRPC.InputCheckPasswordSRP r02 = r11;
    L23:
        r5.password = r02;
        r5.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(r10);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r5, new SelectChatUserSheet$$ExternalSyntheticLambda14(this, r11, r10, r12, r5));
        return;
    L22:
        r02 = new TLRPC.TL_inputCheckPasswordEmpty();
        goto L23
    L19:
        r5.channel = new TLRPC.TL_inputChannelEmpty();
        goto L20
    L10:
        if (ChatObject.isChannel(this.chat) == true) goto L16;
        BaseFragment r52 = LaunchActivity.getSafeLastFragment();
        if (r52 != null) goto L14;
        return;
    L14:
        MessagesController.getInstance(this.currentAccount).convertToMegaGroup(this.context, this.chat.id, r52, new SelectChatUserSheet$$ExternalSyntheticLambda13(this, r10, r11, r12));
        return;
    }

    private boolean isInitialOwnerAdmin() {
        ArrayList<TLObject> r0 = this.admins.users;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        TLObject r4 = r0.get(r3);
        r3 = r3 + 1;
        if (DialogObject.getDialogId(r4) != DialogObject.getDialogId(this.initialOwner)) goto L3;
        return true;
    L8:
        return false;
    }

    private /* synthetic */ void lambda$initTransfer$10(TLRPC.TL_error r1, TLObject r2, TwoStepVerificationActivity r3, TLRPC.User r4) {
        if (r1 != null) goto L5;
        TL_account.Password r22 = (TL_account.Password) r2;
        r3.setCurrentPasswordInfo(null, r22);
        TwoStepVerificationActivity.initPasswordNewAlgo(r22);
        initTransfer(r4, r3.getNewSrpPassword(), r3);
        return;
    }

    private /* synthetic */ void lambda$initTransfer$11(TwoStepVerificationActivity r7, TLRPC.User r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new SelectChatUserSheet$$ExternalSyntheticLambda7(this, r10, r9, r7, r8));
    }

    private /* synthetic */ void lambda$initTransfer$12(TLRPC.TL_error r25, TLRPC.InputCheckPasswordSRP r26, TLRPC.User r27, TwoStepVerificationActivity r28, TLRPC.TL_channels_editCreator r29) {
        if (r25 != null) goto L5;
        if (r26 == null) goto L109;
        Runnable r1 = this.whenTransferred;
        if (r1 == null) goto L106;
        r1.run();
    L106:
        dismiss();
        r28.needHideProgress();
        r28.finishFragment();
        return;
    L109:
        return;
    L5:
        if (this.context != null) goto L8;
        return;
    L8:
        if ("PASSWORD_HASH_INVALID".equals(r25.text) == false) goto L17;
        if (r26 != null) goto L108;
        AlertDialog.Builder r12 = new AlertDialog.Builder(this.context);
        if (ChatObject.isChannelAndNotMegaGroup(this.chat) == false) goto L13;
        int r3 = R.string.EditAdminChannelTransfer;
    L14:
        r12.setTitle(LocaleController.getString(r3));
        r12.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.EditAdminTransferReadyAlertText2, new Object[]{this.chat.title, UserObject.getFirstName(r27)})));
        r12.setPositiveButton(LocaleController.getString(R.string.EditAdminTransferChangeOwner), new SelectChatUserSheet$$ExternalSyntheticLambda0(this, r27));
        r12.setNegativeButton(LocaleController.getString(R.string.Cancel), new SelectChatUserSheet$$ExternalSyntheticLambda1(this));
        r12.show();
        return;
    L13:
        r3 = R.string.EditAdminGroupTransfer;
        goto L14
    L108:
        return;
    L17:
        if ("PASSWORD_MISSING".equals(r25.text) == false) goto L19;
    L50:
        if (r28 == null) goto L52;
        r28.needHideProgress();
    L52:
        AlertDialog.Builder r32 = new AlertDialog.Builder(this.context);
        r32.setTitle(LocaleController.getString(R.string.EditAdminTransferAlertTitle));
        LinearLayout r4 = new LinearLayout(this.context);
        r4.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(24.0f), 0);
        r4.setOrientation(1);
        r32.setView(r4);
        TextView r7 = new TextView(this.context);
        int r8 = Theme.key_dialogTextBlack;
        r7.setTextColor(Theme.getColor(r8));
        r7.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L55;
        int r11 = 5;
    L56:
        r7.setGravity(r11 | 48);
        if (ChatObject.isChannelAndNotMegaGroup(this.chat) == false) goto L59;
        r7.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.EditChannelAdminTransferAlertText, new Object[]{UserObject.getFirstName(r27)})));
    L60:
        r4.addView(r7, LayoutHelper.createLinear(-1, -2));
        LinearLayout r72 = new LinearLayout(this.context);
        r72.setOrientation(0);
        r4.addView(r72, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r14 = new ImageView(this.context);
        r14.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L63;
        int r15 = AndroidUtilities.dp(11.0f);
    L64:
        int r122 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L67;
        int r13 = 0;
    L68:
        r14.setPadding(r15, r122, r13, 0);
        int r132 = Theme.getColor(r8);
        PorterDuff.Mode r152 = PorterDuff.Mode.MULTIPLY;
        r14.setColorFilter(new PorterDuffColorFilter(r132, r152));
        TextView r123 = new TextView(this.context);
        r123.setTextColor(Theme.getColor(r8));
        r123.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L71;
        int r133 = 5;
    L72:
        r123.setGravity(r133 | 48);
        r123.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EditAdminTransferAlertText1)));
        if (LocaleController.isRTL == false) goto L75;
        r72.addView(r123, LayoutHelper.createLinear(-1, -2));
        r72.addView(r14, LayoutHelper.createLinear(-2, -2, 5));
    L76:
        LinearLayout r73 = new LinearLayout(this.context);
        r73.setOrientation(0);
        r4.addView(r73, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r124 = new ImageView(this.context);
        r124.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L79;
        int r134 = AndroidUtilities.dp(11.0f);
    L80:
        int r142 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L83;
        int r2 = 0;
    L84:
        r124.setPadding(r134, r142, r2, 0);
        r124.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r8), r152));
        TextView r22 = new TextView(this.context);
        r22.setTextColor(Theme.getColor(r8));
        r22.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L87;
        int r10 = 5;
    L88:
        r22.setGravity(r10 | 48);
        r22.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EditAdminTransferAlertText2)));
        if (LocaleController.isRTL == false) goto L91;
        r73.addView(r22, LayoutHelper.createLinear(-1, -2));
        int r135 = 5;
        r73.addView(r124, LayoutHelper.createLinear(-2, -2, 5));
    L93:
        if ("PASSWORD_MISSING".equals(r25.text) == false) goto L95;
        r32.setPositiveButton(LocaleController.getString(R.string.EditAdminTransferSetPassword), new SelectChatUserSheet$$ExternalSyntheticLambda2(this));
        r32.setNegativeButton(LocaleController.getString(R.string.Cancel), new SelectChatUserSheet$$ExternalSyntheticLambda3(this));
    L100:
        r32.show();
        return;
    L95:
        TextView r16 = new TextView(this.context);
        r16.setTextColor(Theme.getColor(r8));
        r16.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L98;
        int r125 = r135;
    L99:
        r16.setGravity(r125 | 48);
        r16.setText(LocaleController.getString(R.string.EditAdminTransferAlertText3));
        r4.addView(r16, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        r32.setNegativeButton(LocaleController.getString(R.string.OK), new SelectChatUserSheet$$ExternalSyntheticLambda4(this));
        goto L100
    L98:
        r125 = 3;
        goto L99
    L91:
        r135 = 5;
        r73.addView(r124, LayoutHelper.createLinear(-2, -2));
        r73.addView(r22, LayoutHelper.createLinear(-1, -2));
        goto L93
    L87:
        r10 = 3;
        goto L88
    L83:
        r2 = AndroidUtilities.dp(11.0f);
        goto L84
    L79:
        r134 = 0;
        goto L80
    L75:
        r72.addView(r14, LayoutHelper.createLinear(-2, -2));
        r72.addView(r123, LayoutHelper.createLinear(-1, -2));
        goto L76
    L71:
        r133 = 3;
        goto L72
    L67:
        r13 = AndroidUtilities.dp(11.0f);
        goto L68
    L63:
        r15 = 0;
        goto L64
    L59:
        r7.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.EditAdminTransferAlertText, new Object[]{UserObject.getFirstName(r27)})));
        goto L60
    L55:
        r11 = 3;
        goto L56
    L19:
        if (r25.text.startsWith("PASSWORD_TOO_FRESH_") == true) goto L50;
        if (r25.text.startsWith("SESSION_TOO_FRESH_") == true) goto L50;
        if ("SRP_ID_INVALID".equals(r25.text) == false) goto L28;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.getPassword(), new SelectChatUserSheet$$ExternalSyntheticLambda5(this, r28, r27), 8);
        return;
    L28:
        if (r25.text.equals("CHANNELS_TOO_MUCH") == true) goto L30;
        if (r28 == null) goto L45;
        r28.needHideProgress();
        r28.finishFragment();
    L45:
        BaseFragment r23 = LaunchActivity.getSafeLastFragment();
        if (r23 == null) goto L113;
        AlertsCreator.showAddUserAlert(r25, r23, ChatObject.isChannelAndNotMegaGroup(this.chat), ChatObject.isCommunity(this.chat), r29);
        return;
    L113:
        return;
    L30:
        if (this.context != null) goto L32;
    L38:
        BaseFragment r17 = LaunchActivity.getSafeLastFragment();
        if (r17 == null) goto L112;
        dismiss();
        r17.presentFragment(new TooManyCommunitiesActivity(1));
        return;
    L112:
        return;
    L32:
        if (AccountInstance.getInstance(this.currentAccount).getUserConfig().isPremium() == true) goto L38;
        BaseFragment r82 = LaunchActivity.getSafeLastFragment();
        if (r82 == null) goto L111;
        showDialog(new LimitReachedBottomSheet(r82, this.context, 5, this.currentAccount, null));
        return;
    }

    private /* synthetic */ void lambda$initTransfer$13(TLRPC.InputCheckPasswordSRP r2, TLRPC.User r3, TwoStepVerificationActivity r4, TLRPC.TL_channels_editCreator r5, TLObject r6, TLRPC.TL_error r7) {
        AndroidUtilities.runOnUIThread(new SelectChatUserSheet$$ExternalSyntheticLambda15(this, r7, r2, r3, r4, r5));
    }

    private /* synthetic */ void lambda$initTransfer$3(TLRPC.User r3, TLRPC.InputCheckPasswordSRP r4, TwoStepVerificationActivity r5, long r6) {
        if (r6 == 0) goto L6;
        this.chat = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r6));
        initTransfer(r3, r4, r5);
        return;
    }

    private /* synthetic */ void lambda$initTransfer$4(TLRPC.User r1, TwoStepVerificationActivity r2, TLRPC.InputCheckPasswordSRP r3) {
        initTransfer(r1, r3, r2);
    }

    private /* synthetic */ void lambda$initTransfer$5(TLRPC.User r2, AlertDialog r3, int r4) {
        BaseFragment r32 = LaunchActivity.getSafeLastFragment();
        if (r32 != null) goto L5;
        return;
    L5:
        dismiss();
        TwoStepVerificationActivity r42 = new TwoStepVerificationActivity();
        r42.setDelegate(0, new SelectChatUserSheet$$ExternalSyntheticLambda6(this, r2, r42));
        r32.presentFragment(r42);
    }

    private /* synthetic */ void lambda$initTransfer$6(AlertDialog r1, int r2) {
        this.button.setLoading(false);
    }

    private /* synthetic */ void lambda$initTransfer$7(AlertDialog r2, int r3) {
        BaseFragment r22 = LaunchActivity.getSafeLastFragment();
        if (r22 != null) goto L5;
        return;
    L5:
        dismiss();
        r22.presentFragment(new TwoStepVerificationSetupActivity(6, null));
    }

    private /* synthetic */ void lambda$initTransfer$8(AlertDialog r1, int r2) {
        this.button.setLoading(false);
    }

    private /* synthetic */ void lambda$initTransfer$9(AlertDialog r1, int r2) {
        this.button.setLoading(false);
    }

    private /* synthetic */ boolean lambda$new$0(TextView r1, int r2, KeyEvent r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r3.getAction() != 1) goto L8;
        if (r3.getKeyCode() != 84) goto L8;
    L11:
        AndroidUtilities.hideKeyboard(this.searchEdit);
        return false;
    L8:
        if (r3.getAction() == 0) goto L10;
        return false;
    L10:
        if (r3.getKeyCode() == 66) goto L11;
        return false;
    }

    private /* synthetic */ void lambda$new$1(View r3, int r4) {
        UItem r42 = this.adapter.getItem(r4 - 1);
        if (r42 == null) goto L13;
        Object r0 = r42.object;
        if ((r0 instanceof TLRPC.User) == false) goto L8;
    L11:
        ((ProfileSearchCell) r3).setChecked(true, true);
        this.selectedOwner = (TLObject) r42.object;
        updateButton(true);
        this.adapter.update(true);
        return;
    L8:
        if ((r0 instanceof TLRPC.Chat) == true) goto L11;
        return;
    }

    private /* synthetic */ void lambda$new$2(View r2) {
        if ((this.selectedOwner instanceof TLRPC.User) == true) goto L6;
        return;
    L6:
        if (this.button.isLoading() == false) goto L8;
        return;
    L8:
        this.button.setLoading(true);
        initTransfer((TLRPC.User) this.selectedOwner, null, null);
    }

    private void update() {
        UniversalAdapter r1 = this.adapter;
        if (r1 == null) goto L6;
        r1.update(true);
        return;
    }

    private void updateButton(boolean r6) {
        if (ChatObject.isChannelAndNotMegaGroup(this.chat) == false) goto L5;
        int r0 = R.string.LeaveChannelAndAppoint;
    L6:
        float r1 = AndroidUtilities.dp(32.0f);
        if (this.button.getWidth() <= 0) goto L9;
        int r2 = this.button.getWidth();
    L10:
        this.button.setText(LocaleController.formatString(r0, new Object[]{TextUtils.ellipsize(DialogObject.getShortTitle(this.selectedOwner), this.button.getTextPaint(), Math.max(r1, (r2 - AndroidUtilities.dp(16.0f)) - this.button.getTextPaint().measureText(LocaleController.getString(r0))), TextUtils.TruncateAt.MIDDLE)}), r6);
        return;
    L9:
        r2 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(20.0f);
        goto L10
    L5:
        r0 = R.string.LeaveGroupAndAppoint;
        goto L6
    }

    private void updateSearchY() {
        float r0 = -AndroidUtilities.dp(64.0f);
        int r1 = 0;
    L4:
        if (r1 >= this.recyclerListView.getChildCount()) goto L9;
        View r2 = this.recyclerListView.getChildAt(r1);
        if (r2.getId() == 3) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        r0 = r2.getY();
    L9:
        this.searchContainer.setTranslationY(r0);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r8) {
        UniversalAdapter r0 = new UniversalAdapter(r8, getContext(), this.currentAccount, 0, new SelectChatUserSheet$$ExternalSyntheticLambda8(this), this.resourcesProvider);
        this.adapter = r0;
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        this.admins.detach();
        this.members.detach();
        this.search.detach();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.AppointNewOwner);
    }
}
