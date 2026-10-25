package org.telegram.ui.Components;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes3.dex */
public class PostsSearchContainer extends FrameLayout {
    private ColoredImageSpan arrowSpan;
    private ForegroundColorAlphaSpan colorSpan;
    private final int currentAccount;
    private final ButtonWithCounterView emptyButton;
    private final BackupImageView emptyImageView;
    private final FrameLayout emptyParentView;
    private final TextView emptyTextView;
    private final TextView emptyTitleView;
    private final TextView emptyUnderButtonTextView;
    private final LinearLayout emptyView;
    private boolean endReached;
    private TLRPC.SearchPostsFlood flood;
    private boolean floodLoading;
    private int floodLoadingRequestId;
    private final BaseFragment fragment;
    private boolean ignoreRequestLayout;
    private boolean isEmpty;
    private String lastQuery;
    private int lastRate;
    public final UniversalRecyclerView listView;
    private boolean loading;
    private final ArrayList<MessageObject> messages;
    private final ArrayList<MessageObject> newsMessages;
    private boolean newsMessagesEndReached;
    private int newsMessagesLastRate;
    private int queryid;
    private int reqId;
    private ColoredImageSpan searchSpan;
    private ColoredImageSpan[] starSpan;
    private final Runnable updateEmptyViewRunnable;
    private boolean wasEmptyOnFloodLoad;
    private boolean wasOpen;

    /* renamed from: org.telegram.ui.Components.PostsSearchContainer$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.OnScrollListener {
        final /* synthetic */ PostsSearchContainer this$0;
        final /* synthetic */ BaseFragment val$fragment;

        public AnonymousClass1(PostsSearchContainer r1, BaseFragment r2) {
            this.this$0 = r1;
            this.val$fragment = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            boolean r12 = TextUtils.isEmpty(PostsSearchContainer.m11924$$Nest$fgetlastQuery(this.this$0));
            PostsSearchContainer r22 = this.this$0;
            if (r12 == false) goto L5;
            ArrayList r13 = PostsSearchContainer.m11926$$Nest$fgetnewsMessages(r22);
        L7:
            if (r13.isEmpty() == false) goto L9;
        L13:
            PostsSearchContainer r14 = this.this$0;
            if (r14.listView.scrollingByUser == true) goto L16;
            return;
        L16:
            if (PostsSearchContainer.m11923$$Nest$fgetisEmpty(r14) == true) goto L24;
            BaseFragment r15 = this.val$fragment;
            if (r15 != null) goto L20;
            return;
        L20:
            if (r15.getParentActivity() == null) goto L26;
            AndroidUtilities.hideKeyboard(this.val$fragment.getParentActivity().getCurrentFocus());
            return;
        L26:
            return;
        L24:
            return;
        L9:
            if (this.this$0.listView.canScrollVertically(1) == true) goto L11;
        L12:
            PostsSearchContainer.m11928$$Nest$mload(this.this$0, false);
            goto L13
        L11:
            if (PostsSearchContainer.m11927$$Nest$misLoadingVisible(this.this$0) == false) goto L13;
        L5:
            r13 = PostsSearchContainer.m11925$$Nest$fgetmessages(r22);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.PostsSearchContainer$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayout {
        final /* synthetic */ PostsSearchContainer this$0;

        public AnonymousClass2(PostsSearchContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(220.0f), View.MeasureSpec.getSize(r3)), View.MeasureSpec.getMode(r3)), r4);
        }
    }

    /* loaded from: classes7.dex */
    public static class ForegroundColorAlphaSpan extends CharacterStyle {
        private final int color;

        public ForegroundColorAlphaSpan(int r1) {
            this.color = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            r3.setColor(Theme.multAlpha(this.color, r3.getAlpha() / 255.0f));
        }
    }

    /* renamed from: $r8$lambda$5IQL7HaA-kJY0Fv_UQd5JHHK0z0, reason: not valid java name */
    public static /* synthetic */ void m11921$r8$lambda$5IQL7HaAkJY0Fv_UQd5JHHK0z0(PostsSearchContainer r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onItemClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$AnQZ2lGepebTz-aa4KY63jQXKB8, reason: not valid java name */
    public static /* synthetic */ void m11922$r8$lambda$AnQZ2lGepebTzaa4KY63jQXKB8(PostsSearchContainer r0) {
        r0.updateEmptyView();
    }

    public static /* synthetic */ void $r8$lambda$EPYMCAfpaCyUPgLzLC1JWe6NS1o(PostsSearchContainer r0, View r1) {
        r0.lambda$updateEmptyView$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$Jxz72IFoRsJ4CyOfd4MjhLJjRA0(PostsSearchContainer r0, long r1) {
        r0.lambda$load$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$LXZLcCGEjZI29RyCsQlCW_b9g7A(PostsSearchContainer r0, TLObject r1) {
        r0.lambda$loadFlood$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$LsWcaO05bfncdsFGS4PHX4SsUnM(PostsSearchContainer r0, View r1) {
        r0.lambda$updateEmptyView$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$MN3CX7lAVRcuHlFIKig3PTIf3iE(PostsSearchContainer r0, boolean r1, ArrayList r2) {
        r0.lambda$load$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NJqaeF2_HWgmHTzHXhP5RdXT63k(PostsSearchContainer r0) {
        r0.lambda$load$1();
    }

    public static /* synthetic */ void $r8$lambda$_j57DbqOh80NCH8c0tgeaFlGK1I(PostsSearchContainer r0, MessagesController r1, boolean r2, TLRPC.TL_channels_searchPosts r3, boolean r4, long r5, ConnectionsManager r7, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$load$4(r1, r2, r3, r4, r5, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$ds6j5BLpUM23OTvpAHI3j57zNF4(PostsSearchContainer r0, TLObject r1, MessagesController r2, boolean r3, TLRPC.TL_channels_searchPosts r4, boolean r5, long r6, TLRPC.TL_error r8, ConnectionsManager r9) {
        r0.lambda$load$3(r1, r2, r3, r4, r5, r6, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$etCWNb_LqAVvvXCFujxRWpiAwD8(PostsSearchContainer r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$haYW6d_GeG1jJbqb5y7yhJBW2mY(PostsSearchContainer r0, View r1) {
        r0.lambda$updateEmptyView$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$mJUnUNU9eVaEP2N2APYDU5YnDms(PostsSearchContainer r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadFlood$6(r1, r2);
    }

    /* renamed from: -$$Nest$fgetisEmpty, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11923$$Nest$fgetisEmpty(PostsSearchContainer r0) {
        return r0.isEmpty;
    }

    /* renamed from: -$$Nest$fgetlastQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m11924$$Nest$fgetlastQuery(PostsSearchContainer r0) {
        return r0.lastQuery;
    }

    /* renamed from: -$$Nest$fgetmessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11925$$Nest$fgetmessages(PostsSearchContainer r0) {
        return r0.messages;
    }

    /* renamed from: -$$Nest$fgetnewsMessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11926$$Nest$fgetnewsMessages(PostsSearchContainer r0) {
        return r0.newsMessages;
    }

    /* renamed from: -$$Nest$misLoadingVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11927$$Nest$misLoadingVisible(PostsSearchContainer r0) {
        return r0.isLoadingVisible();
    }

    /* renamed from: -$$Nest$mload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11928$$Nest$mload(PostsSearchContainer r0, boolean r1) {
        r0.load(r1);
    }

    public PostsSearchContainer(Context r22, BaseFragment r23) {
        super(r22);
        this.newsMessages = new ArrayList();
        this.messages = new ArrayList();
        this.reqId = -1;
        this.queryid = 0;
        this.floodLoadingRequestId = -1;
        this.starSpan = new ColoredImageSpan[1];
        this.updateEmptyViewRunnable = new PostsSearchContainer$$ExternalSyntheticLambda5(this);
        this.fragment = r23;
        int r3 = r23.getCurrentAccount();
        this.currentAccount = r3;
        UniversalRecyclerView r1 = new UniversalRecyclerView(r22, r3, 0, new PostsSearchContainer$$ExternalSyntheticLambda6(this), new PostsSearchContainer$$ExternalSyntheticLambda7(this), null, null);
        this.listView = r1;
        r1.addOnScrollListener(new AnonymousClass1(this, r23));
        addView(r1, LayoutHelper.createFrame(-1, -1, 119));
        FrameLayout r4 = new FrameLayout(r22);
        this.emptyParentView = r4;
        AnonymousClass2 r5 = new AnonymousClass2(this, r22);
        this.emptyView = r5;
        r5.setOrientation(1);
        r4.addView(r5, LayoutHelper.createFrame(-2, -2.0f, 17, 32.0f, 0.0f, 32.0f, 0.0f));
        BackupImageView r6 = new BackupImageView(r22);
        this.emptyImageView = r6;
        r6.setVisibility(8);
        r5.addView(r6, LayoutHelper.createLinear(130, 130, 1, 0, 0, 0, 12));
        TextView r62 = new TextView(r22);
        this.emptyTitleView = r62;
        r62.setTextSize(1, 16.0f);
        r62.setTypeface(AndroidUtilities.bold());
        r62.setGravity(17);
        r62.setSingleLine(false);
        r62.setMaxLines(4);
        TextUtils.TruncateAt r9 = TextUtils.TruncateAt.END;
        r62.setEllipsize(r9);
        r5.addView(r62, LayoutHelper.createLinear(-2, -2, 1));
        TextView r63 = new TextView(r22);
        this.emptyTextView = r63;
        r63.setTextSize(1, 14.0f);
        r63.setGravity(17);
        r63.setSingleLine(false);
        r63.setMaxLines(4);
        r63.setEllipsize(r9);
        r5.addView(r63, LayoutHelper.createLinear(-2, -2, 1, 0, 9, 0, 0));
        ButtonWithCounterView r64 = new ButtonWithCounterView(r22, null).setRound();
        this.emptyButton = r64;
        r5.addView(r64, LayoutHelper.createLinear(-1, 44, 7, 0, 19, 0, 0));
        TextView r65 = new TextView(r22);
        this.emptyUnderButtonTextView = r65;
        r65.setTextSize(1, 12.0f);
        r65.setGravity(17);
        r5.addView(r65, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        addView(r4, LayoutHelper.createFrame(-1, -1, 119));
        r1.setHideIfEmpty(false);
        r1.setEmptyView(r4);
        r1.setAnimateEmptyView(true, 0);
        updateColors();
        updateEmptyView();
    }

    private void cancel() {
        if (this.reqId < 0) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
        this.reqId = -1;
    L5:
        this.loading = false;
        this.emptyButton.setLoading(false);
    }

    private void fillItems(ArrayList<UItem> r6, UniversalAdapter r7) {
        int r0 = 0;
        if (this.flood != null) goto L6;
        r6.add(UItem.asFlicker(-1, 7));
        r6.add(UItem.asFlicker(-2, 7));
        r6.add(UItem.asFlicker(-3, 7));
        this.isEmpty = false;
        return;
    L6:
        boolean r72 = TextUtils.isEmpty(this.lastQuery);
        if (r72 == false) goto L15;
        if (this.newsMessages.isEmpty() == true) goto L11;
        r6.add(UItem.asGraySection(LocaleController.getString(R.string.SearchPostsHeaderNews)));
    L11:
        ArrayList<MessageObject> r2 = this.newsMessages;
        int r3 = r2.size();
    L12:
        if (r0 >= r3) goto L21;
        MessageObject r4 = r2.get(r0);
        r0 = r0 + 1;
        r6.add(UItem.asSearchMessage(r4));
    L21:
        if (this.loading == false) goto L23;
    L31:
        r6.add(UItem.asFlicker(this.queryid * 3, 7));
        r6.add(UItem.asFlicker((this.queryid * 3) + 1, 7));
        r6.add(UItem.asFlicker((this.queryid * 3) + 2, 7));
    L32:
        this.isEmpty = r6.isEmpty();
        return;
    L23:
        if (this.floodLoading == true) goto L25;
    L26:
        if (r72 == true) goto L32;
        if (this.messages.isEmpty() == true) goto L32;
        if (this.endReached == true) goto L32;
    L25:
        if (this.wasEmptyOnFloodLoad == false) goto L31;
    L15:
        if (this.messages.isEmpty() == true) goto L17;
        r6.add(UItem.asGraySection(LocaleController.getString(R.string.SearchPostsHeaderFound)));
    L17:
        ArrayList<MessageObject> r22 = this.messages;
        int r32 = r22.size();
    L18:
        if (r0 >= r32) goto L21;
        MessageObject r42 = r22.get(r0);
        r0 = r0 + 1;
        r6.add(UItem.asSearchMessage(r42));
        goto L18
    }

    private boolean isLoadingVisible() {
        int r1 = 0;
    L4:
        if (r1 >= this.listView.getChildCount()) goto L10;
        if ((this.listView.getChildAt(r1) instanceof FlickerLoadingView) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$load$0(boolean r1, ArrayList r2) {
        if (r1 == false) goto L5;
        r2 = this.newsMessages;
    L5:
        if (r2.isEmpty() == false) goto L7;
        return;
    L7:
        if (this.listView.canScrollVertically(1) == true) goto L9;
    L10:
        load(false);
        return;
    L9:
        if (isLoadingVisible() == true) goto L10;
    }

    private /* synthetic */ void lambda$load$1() {
        load(true);
    }

    private /* synthetic */ void lambda$load$2(long r11) {
        Activity r1 = AndroidUtilities.getActivity();
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (PhotoViewer.getInstance().isVisible() == true) goto L12;
        if (r0 != null) goto L6;
    L8:
        if (r0 == null) goto L11;
        Theme.ResourcesProvider r02 = r0.getResourceProvider();
    L10:
        Theme.ResourcesProvider r2 = r02;
        new StarsIntroActivity.StarsNeededSheet(r1, r2, r11, 15, _UrlKt.FRAGMENT_ENCODE_SET, new PostsSearchContainer$$ExternalSyntheticLambda12(this), 0).show();
        return;
    L11:
        r02 = null;
        goto L10
    L6:
        if (r0.hasShownSheet() == false) goto L8;
    L12:
        r02 = new DarkThemeResourceProvider();
        goto L10
    }

    private /* synthetic */ void lambda$load$3(TLObject r15, MessagesController r16, boolean r17, TLRPC.TL_channels_searchPosts r18, boolean r19, long r20, TLRPC.TL_error r22, ConnectionsManager r23) {
        this.reqId = -1;
        this.loading = false;
        this.emptyButton.setLoading(false);
        if ((r15 instanceof TLRPC.messages_Messages) == false) goto L61;
        TLRPC.messages_Messages r0 = (TLRPC.messages_Messages) r15;
        r16.putUsers(r0.users, false);
        r16.putChats(r0.chats, false);
        TLRPC.SearchPostsFlood r1 = r0.search_flood;
        if (r1 == null) goto L7;
        this.flood = r1;
    L7:
        if (r17 == false) goto L9;
        ArrayList<MessageObject> r12 = this.newsMessages;
    L10:
        boolean r5 = r12.isEmpty();
        ArrayList<TLRPC.Message> r7 = r0.messages;
        int r9 = r7.size();
        int r10 = 0;
    L11:
        if (r10 >= r9) goto L16;
        TLRPC.Message r11 = r7.get(r10);
        r10 = r10 + 1;
        MessageObject r122 = new MessageObject(this.currentAccount, r11, false, false);
        if (r17 == true) goto L15;
        r122.setQuery(r18.query);
    L15:
        r12.add(r122);
        goto L11
    L16:
        if (r17 == true) goto L31;
        if ((r0 instanceof TLRPC.TL_messages_messagesSlice) == false) goto L25;
        this.lastRate = r0.next_rate;
        if ((r0.flags & 1) != 0) goto L22;
        boolean r02 = true;
    L23:
        this.endReached = r02;
    L43:
        updateEmptyView();
        if (r5 == false) goto L46;
        this.listView.scrollToPosition(0);
    L46:
        this.listView.adapter.update(true);
        if (r12.isEmpty() == true) goto L55;
        if (r17 == false) goto L53;
        if (this.newsMessagesEndReached == true) goto L55;
    L54:
        AndroidUtilities.runOnUIThread(new PostsSearchContainer$$ExternalSyntheticLambda10(this, r17, r12));
        goto L55
    L53:
        if (this.endReached == false) goto L54;
    L55:
        if (r19 == true) goto L57;
        return;
    L57:
        if (r20 <= 0) goto L89;
        if (r17 == true) goto L90;
        BulletinFactory.of(this.fragment).createSimpleBulletin(R.raw.stars_topup, AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("SearchPaidStars", (int) r20))).show();
        return;
    L90:
        return;
    L89:
        return;
    L22:
        r02 = false;
        goto L23
    L25:
        if ((r0 instanceof TLRPC.TL_messages_messages) == false) goto L28;
        this.lastRate = 0;
        this.endReached = true;
        goto L43
    L28:
        if ((r0 instanceof TLRPC.TL_messages_channelMessages) == false) goto L43;
        this.lastRate = 0;
        this.endReached = true;
        goto L43
    L31:
        if ((r0 instanceof TLRPC.TL_messages_messagesSlice) == false) goto L38;
        this.newsMessagesLastRate = r0.next_rate;
        if ((r0.flags & 1) != 0) goto L35;
        boolean r03 = true;
    L36:
        this.newsMessagesEndReached = r03;
        goto L43
    L35:
        r03 = false;
        goto L36
    L38:
        if ((r0 instanceof TLRPC.TL_messages_messages) == false) goto L41;
        this.newsMessagesLastRate = 0;
        this.newsMessagesEndReached = true;
        goto L43
    L41:
        if ((r0 instanceof TLRPC.TL_messages_channelMessages) == false) goto L43;
        this.newsMessagesLastRate = 0;
        this.newsMessagesEndReached = true;
        goto L43
    L9:
        r12 = this.messages;
        goto L10
    L61:
        if (r22 != null) goto L63;
    L75:
        if (r22 != null) goto L77;
    L80:
        if (r22 != null) goto L82;
        return;
    L82:
        if ("BALANCE_TOO_LOW".equalsIgnoreCase(r22.text) == false) goto L94;
        updateEmptyView();
        this.listView.adapter.update(true);
        StarsController.getInstance(this.currentAccount).getBalance(true, new PostsSearchContainer$$ExternalSyntheticLambda11(this, r20), true);
        return;
    L94:
        return;
    L77:
        if (MediaError.ERROR_REASON_PREMIUM_ACCOUNT_REQUIRED.equalsIgnoreCase(r22.text) == false) goto L80;
        updateEmptyView();
        this.listView.adapter.update(true);
        return;
    L63:
        if (r22.text.startsWith("FLOOD_WAIT_") == false) goto L75;
        if (r22.text.contains("_OR_STARS_") == false) goto L75;
        Matcher r04 = Pattern.compile("FLOOD_WAIT_(\\d+)_OR_STARS_(\\d+)").matcher(r22.text);
        if (r04 != null) goto L69;
        return;
    L69:
        if (r04.matches() == false) goto L92;
        int r13 = Integer.parseInt(r04.group(1));
        int r05 = Integer.parseInt(r04.group(2));
        TLRPC.SearchPostsFlood r3 = this.flood;
        if (r3 == null) goto L73;
        r3.flags = 2 | r3.flags;
        r3.wait_till = r23.getCurrentTime() + r13;
        this.flood.stars_amount = r05;
    L73:
        updateEmptyView();
        this.listView.adapter.update(true);
        return;
    }

    private /* synthetic */ void lambda$load$4(MessagesController r12, boolean r13, TLRPC.TL_channels_searchPosts r14, boolean r15, long r16, ConnectionsManager r18, TLObject r19, TLRPC.TL_error r20) {
        AndroidUtilities.runOnUIThread(new PostsSearchContainer$$ExternalSyntheticLambda8(this, r19, r12, r13, r14, r15, r16, r20, r18));
    }

    private /* synthetic */ void lambda$loadFlood$5(TLObject r3) {
        this.floodLoading = false;
        if ((r3 instanceof TLRPC.SearchPostsFlood) == false) goto L10;
        TLRPC.SearchPostsFlood r32 = (TLRPC.SearchPostsFlood) r3;
        this.flood = r32;
        if (r32.query_is_free == false) goto L8;
        load(false);
        return;
    L8:
        updateEmptyView();
        this.listView.adapter.update(true);
        return;
    }

    private /* synthetic */ void lambda$loadFlood$6(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new PostsSearchContainer$$ExternalSyntheticLambda9(this, r1));
    }

    private /* synthetic */ void lambda$updateEmptyView$7(View r2) {
        this.fragment.presentFragment(new PremiumPreviewFragment("search"));
    }

    private /* synthetic */ void lambda$updateEmptyView$8(View r2) {
        this.emptyButton.setLoading(true);
        load(true);
    }

    private /* synthetic */ void lambda$updateEmptyView$9(View r2) {
        this.emptyButton.setLoading(true);
        load(false);
    }

    private void load(boolean r11) {
        if (this.loading == true) goto L36;
        boolean r4 = TextUtils.isEmpty(this.lastQuery);
        if (r4 == true) goto L8;
    L10:
        if (r4 == false) goto L12;
    L14:
        if (r4 == false) goto L16;
    L18:
        this.loading = true;
        MessagesController r3 = MessagesController.getInstance(this.currentAccount);
        ConnectionsManager r9 = ConnectionsManager.getInstance(this.currentAccount);
        TLRPC.TL_channels_searchPosts r5 = new TLRPC.TL_channels_searchPosts();
        r5.flags |= 2;
        r5.query = this.lastQuery;
        r5.limit = 30;
        if (r4 == false) goto L25;
        if (this.newsMessages.isEmpty() == true) goto L23;
        ArrayList<MessageObject> r1 = this.newsMessages;
        MessageObject r12 = r1.get(r1.size() - 1);
        r5.offset_rate = this.newsMessagesLastRate;
        r5.offset_id = r12.getRealId();
        r5.offset_peer = r3.getInputPeer(r12.messageOwner.peer_id);
    L28:
        if (r11 == false) goto L33;
        TLRPC.SearchPostsFlood r13 = this.flood;
        if (r13 == null) goto L33;
        r5.flags |= 4;
        long r14 = r13.stars_amount;
        r5.allow_paid_stars = r14;
    L34:
        this.reqId = r9.sendRequest(r5, new PostsSearchContainer$$ExternalSyntheticLambda4(this, r3, r4, r5, r11, r14, r9), 1024);
        updateEmptyView();
        this.listView.adapter.update(true);
        return;
    L33:
        r14 = 0;
        goto L34
    L23:
        r5.offset_peer = new TLRPC.TL_inputPeerEmpty();
        goto L28
    L25:
        if (this.messages.isEmpty() == true) goto L27;
        ArrayList<MessageObject> r15 = this.messages;
        MessageObject r16 = r15.get(r15.size() - 1);
        r5.offset_rate = this.lastRate;
        r5.offset_id = r16.getRealId();
        r5.offset_peer = r3.getInputPeer(r16.messageOwner.peer_id);
        goto L28
    L27:
        r5.offset_peer = new TLRPC.TL_inputPeerEmpty();
        goto L28
    L16:
        if (this.flood != null) goto L18;
        return;
    L12:
        if (this.endReached == false) goto L14;
        return;
    L8:
        if (this.newsMessagesEndReached == false) goto L10;
        return;
    }

    private void loadFlood(String r4) {
        if (this.floodLoadingRequestId < 0) goto L6;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.floodLoadingRequestId, true);
        this.floodLoadingRequestId = -1;
    L6:
        if (this.floodLoading == false) goto L8;
    L16:
        this.floodLoading = true;
        TLRPC.TL_channels_checkSearchPostsFlood r0 = new TLRPC.TL_channels_checkSearchPostsFlood();
        if (TextUtils.isEmpty(r4) == true) goto L19;
        r0.flags = 1 | r0.flags;
        r0.query = r4;
    L19:
        this.floodLoadingRequestId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new PostsSearchContainer$$ExternalSyntheticLambda0(this));
        return;
    L8:
        if (this.isEmpty == true) goto L10;
    L14:
        boolean r02 = false;
    L15:
        this.wasEmptyOnFloodLoad = r02;
        goto L16
    L10:
        if (this.messages.isEmpty() == true) goto L12;
    L13:
        r02 = true;
        goto L15
    L12:
        if (this.endReached == true) goto L14;
        goto L13
    }

    private void onItemClick(UItem r3, View r4, int r5, float r6, float r7) {
        Object r32 = r3.object;
        if ((r32 instanceof MessageObject) == false) goto L10;
        MessageObject r33 = (MessageObject) r32;
        Bundle r42 = new Bundle();
        if (r33.getDialogId() < 0) goto L7;
        r42.putLong("user_id", r33.getDialogId());
    L8:
        r42.putInt("message_id", r33.getId());
        ChatActivity r52 = new ChatActivity(r42);
        this.fragment.presentFragment(DialogsActivity.highlightFoundQuote(r52, r33));
        return;
    L7:
        r42.putLong("chat_id", -r33.getDialogId());
        goto L8
    }

    private void updateEmptyView() {
        AndroidUtilities.cancelRunOnUIThread(this.updateEmptyViewRunnable);
        int r0 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L7;
        this.emptyImageView.setVisibility(8);
        this.emptyTitleView.setText(LocaleController.getString(R.string.SearchPostsTitle));
        this.emptyTextView.setText(LocaleController.getString(R.string.SearchPostsText));
        this.emptyButton.setVisibility(0);
        this.emptyButton.setText(LocaleController.getString(R.string.SearchPostsButtonPremium), true);
        this.emptyButton.setSubText(null, true);
        this.emptyButton.setOnClickListener(new PostsSearchContainer$$ExternalSyntheticLambda1(this));
        this.emptyUnderButtonTextView.setVisibility(0);
        this.emptyUnderButtonTextView.setText(LocaleController.getString(R.string.SearchPostsPremium));
        return;
    L7:
        if (TextUtils.isEmpty(this.lastQuery) == true) goto L18;
        if (this.messages.isEmpty() == false) goto L18;
        if (this.endReached == false) goto L18;
        if (this.emptyImageView.getImageReceiver().getImageDrawable() != null) goto L15;
        this.emptyImageView.setImageDrawable(new RLottieDrawable(R.raw.utyan_empty, AndroidUtilities.dp(130.0f), AndroidUtilities.dp(130.0f)));
    L15:
        this.emptyImageView.setVisibility(0);
        this.emptyTitleView.setText(LocaleController.getString(R.string.SearchPostsNotFound));
        TextView r02 = this.emptyTextView;
        r02.setText(LocaleController.formatString(R.string.SearchPostsNotFoundText, new Object[]{TextUtils.ellipsize(this.lastQuery, r02.getPaint(), AndroidUtilities.dp(100.0f), TextUtils.TruncateAt.END)}));
        this.emptyButton.setVisibility(8);
        this.emptyUnderButtonTextView.setVisibility(8);
        return;
    L18:
        if (TextUtils.isEmpty(this.lastQuery) == true) goto L40;
        TLRPC.SearchPostsFlood r1 = this.flood;
        if (r1 == null) goto L40;
        if ((r1.flags & 2) == 0) goto L40;
        if (r0 >= r1.wait_till) goto L40;
        this.emptyImageView.setVisibility(8);
        this.emptyTitleView.setText(LocaleController.getString(R.string.SearchPostsLimitReached));
        this.emptyTextView.setText(LocaleController.formatPluralStringComma("SearchPostsLimitReachedText", this.flood.total_daily));
        int r12 = this.flood.wait_till - r0;
        int r03 = r12 / 3600;
        int r13 = r12 - (r03 * 3600);
        int r2 = r13 / 60;
        int r14 = r13 - (r2 * 60);
        this.emptyButton.setVisibility(0);
        this.emptyButton.setText(StarsIntroActivity.replaceStars(LocaleController.formatPluralStringComma("SearchPostsButtonPay", (int) this.flood.stars_amount), 1.13f, this.starSpan), true);
        ButtonWithCounterView r6 = this.emptyButton;
        int r7 = R.string.SearchPostsFreeSearchUnlocksIn;
        StringBuilder r8 = new StringBuilder();
        if (r03 <= 0) goto L28;
        String r04 = r03 + ":";
    L29:
        r8.append(r04);
        if (r2 >= 10) goto L32;
        Object r22 = MVEL.VERSION_SUB + r2;
    L33:
        r8.append(r22);
        r8.append(":");
        if (r14 >= 10) goto L36;
        Object r05 = MVEL.VERSION_SUB + r14;
    L37:
        r8.append(r05);
        r6.setSubText(LocaleController.formatString(r7, new Object[]{r8.toString()}), true);
        this.emptyButton.subText.setHacks(false, true, true);
        this.emptyButton.setOnClickListener(new PostsSearchContainer$$ExternalSyntheticLambda2(this));
        AndroidUtilities.runOnUIThread(this.updateEmptyViewRunnable, 1000);
        this.emptyUnderButtonTextView.setVisibility(8);
        return;
    L36:
        r05 = Integer.valueOf(r14);
        goto L37
    L32:
        r22 = Integer.valueOf(r2);
        goto L33
    L28:
        r04 = _UrlKt.FRAGMENT_ENCODE_SET;
    L40:
        if (this.messages.isEmpty() == true) goto L42;
    L63:
        this.emptyImageView.setVisibility(8);
        this.emptyTitleView.setText(LocaleController.getString(R.string.SearchPostsTitle));
        this.emptyTextView.setText(LocaleController.getString(R.string.SearchPostsText));
        this.emptyButton.setVisibility(8);
        TLRPC.SearchPostsFlood r06 = this.flood;
        TextView r23 = this.emptyUnderButtonTextView;
        if (r06 == null) goto L70;
        r23.setVisibility(0);
        TextView r07 = this.emptyUnderButtonTextView;
        TLRPC.SearchPostsFlood r122 = this.flood;
        int r24 = r122.remains;
        if (r24 >= 1) goto L68;
        r24 = r122.total_daily;
    L68:
        r07.setText(LocaleController.formatPluralStringComma("SearchPostsFreeSearches", r24));
        return;
    L70:
        r23.setVisibility(8);
        return;
    L42:
        if (this.loading == true) goto L63;
        if (TextUtils.isEmpty(this.lastQuery) == true) goto L63;
        this.emptyImageView.setVisibility(8);
        this.emptyTitleView.setText(LocaleController.getString(R.string.SearchPostsTitle));
        this.emptyTextView.setText(LocaleController.getString(R.string.SearchPostsText));
        SpannableStringBuilder r08 = new SpannableStringBuilder("s ");
        if (this.searchSpan != null) goto L49;
        ColoredImageSpan r72 = new ColoredImageSpan(R.drawable.smiles_tab_search);
        this.searchSpan = r72;
        r72.setScale(0.79f, 0.79f);
    L49:
        if (this.colorSpan != null) goto L51;
        this.colorSpan = new ForegroundColorAlphaSpan(Theme.blendOver(Theme.getColor(Theme.key_featuredStickers_addButton), Theme.multAlpha(Theme.getColor(Theme.key_featuredStickers_buttonText), 0.75f)));
    L51:
        r08.setSpan(this.searchSpan, 0, 1, 0);
        r08.append(LocaleController.getString(R.string.SearchPostsButton));
        r08.append(" ");
        int r73 = r08.length();
        r08.append(TextUtils.ellipsize(this.lastQuery, this.emptyButton.getTextPaint(), AndroidUtilities.dp(100.0f), TextUtils.TruncateAt.END));
        r08.setSpan(this.colorSpan, r73, r08.length(), 33);
        r08.append(" >");
        if (this.arrowSpan != null) goto L54;
        ColoredImageSpan r62 = new ColoredImageSpan(R.drawable.msg_mini_forumarrow);
        this.arrowSpan = r62;
        r62.setScale(1.05f, 1.05f);
    L54:
        r08.setSpan(this.arrowSpan, r08.length() - 1, r08.length(), 33);
        this.emptyButton.setVisibility(0);
        this.emptyButton.setText(r08, true);
        this.emptyButton.text.setHacks(false, true, false);
        this.emptyButton.setSubText(null, true);
        this.emptyButton.setOnClickListener(new PostsSearchContainer$$ExternalSyntheticLambda3(this));
        TLRPC.SearchPostsFlood r09 = this.flood;
        TextView r25 = this.emptyUnderButtonTextView;
        if (r09 == null) goto L61;
        r25.setVisibility(0);
        TextView r010 = this.emptyUnderButtonTextView;
        TLRPC.SearchPostsFlood r123 = this.flood;
        int r26 = r123.remains;
        if (r26 >= 1) goto L59;
        r26 = r123.total_daily;
    L59:
        r010.setText(LocaleController.formatPluralStringComma("SearchPostsFreeSearches", r26));
        return;
    L61:
        r25.setVisibility(8);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.flood != null) goto L6;
        loadFlood(null);
    L6:
        if (this.wasOpen == true) goto L9;
        this.wasOpen = true;
        MessagesController.getGlobalMainSettings().edit().putInt("searchpostsnew", MessagesController.getGlobalMainSettings().getInt("searchpostsnew", 0) + 1).apply();
        StarsController.getInstance(this.currentAccount).getBalance();
        return;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreRequestLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void search(String r4) {
        if (TextUtils.equals(this.lastQuery, r4) == false) goto L5;
        return;
    L5:
        cancel();
        this.lastQuery = r4;
        if (TextUtils.isEmpty(r4) == false) goto L8;
        this.lastRate = 0;
        this.queryid++;
        this.endReached = false;
        this.messages.clear();
        load(false);
    L9:
        updateEmptyView();
        this.listView.scrollToPosition(0);
        this.listView.adapter.update(true);
        return;
    L8:
        loadFlood(r4);
        this.lastRate = 0;
        this.queryid++;
        this.endReached = false;
        this.messages.clear();
        goto L9
    }

    public void setKeyboardHeight(int r3) {
        this.emptyView.animate().translationY((-r3) / 2.0f).setDuration(250).setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator).start();
    }

    public void setPagesPaddings(int r8, int r9, boolean r10) {
        setClipToPadding(false);
        this.ignoreRequestLayout = r10;
        setPadding(0, r8, 0, r9);
        this.listView.setPadding(0, r8, 0, r9, r10);
        ViewGroup.MarginLayoutParams r82 = (ViewGroup.MarginLayoutParams) this.listView.getLayoutParams();
        r82.topMargin = -r8;
        r82.bottomMargin = -r9;
        this.ignoreRequestLayout = false;
    }

    public void updateColors() {
        this.emptyParentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.emptyTitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        TextView r0 = this.emptyUnderButtonTextView;
        int r1 = Theme.key_windowBackgroundWhiteGrayText;
        r0.setTextColor(Theme.getColor(r1));
        this.emptyTextView.setTextColor(Theme.getColor(r1));
        this.listView.setAdapter(null);
        UniversalRecyclerView r02 = this.listView;
        r02.setAdapter(r02.adapter);
        if (this.colorSpan == null) goto L6;
        this.colorSpan = null;
        updateEmptyView();
        return;
    }
}
