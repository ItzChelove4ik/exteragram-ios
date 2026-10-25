package org.telegram.ui.Components;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.material.navigation.NavigationBarView;
import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public abstract class BackButtonMenu {

    public static class PulledDialog<T> {
        Class<T> activity;
        TLRPC.Chat chat;
        long dialogId;
        int filterId;
        int folderId;
        int stackIndex;
        TLRPC.TL_forumTopic topic;
        TLRPC.User user;

        public PulledDialog() {
        }
    }

    public static /* synthetic */ void $r8$lambda$LjwJnyIy4aTYeceLn6PSUidUlns(AtomicReference r4, PulledDialog r5, INavigationLayout r6, TLRPC.TL_forumTopic r7, BaseFragment r8, View r9) {
        Long r0 = null;
        if (r4.get() == null) goto L6;
        ((ActionBarPopupWindow) r4.getAndSet(null)).dismiss();
    L6:
        if (r5.stackIndex < 0) goto L42;
        if (r6 != null) goto L9;
    L19:
        Long r42 = null;
    L20:
        if (r0 != null) goto L22;
    L23:
        if (r7 == null) goto L31;
        if (r42 == null) goto L31;
        if (r7.id == r42.longValue()) goto L31;
    L27:
        int r43 = r6.getFragmentStack().size() - 2;
    L29:
        if (r43 <= r5.stackIndex) goto L42;
        r6.removeFragmentFromStack(r43);
        r43 = r43 - 1;
    L31:
        if (r6 == null) goto L42;
        if (r6.getFragmentStack() == null) goto L42;
        ArrayList r44 = new ArrayList(r6.getFragmentStack());
        int r72 = r44.size() - 2;
    L35:
        int r92 = r5.stackIndex;
        if (r72 <= r92) goto L39;
        ((BaseFragment) r44.get(r72)).removeSelfFromStack();
        r72 = r72 - 1;
        goto L35
    L39:
        if (r92 >= r6.getFragmentStack().size()) goto L42;
        r6.closeLastFragment(true);
        return;
    L22:
        if (r0.longValue() != r5.dialogId) goto L27;
    L9:
        if (r6.getFragmentStack() == null) goto L19;
        if (r5.stackIndex >= r6.getFragmentStack().size()) goto L19;
        BaseFragment r45 = r6.getFragmentStack().get(r5.stackIndex);
        if ((r45 instanceof ChatActivity) == false) goto L17;
        ChatActivity r46 = (ChatActivity) r45;
        r0 = Long.valueOf(r46.getDialogId());
        r42 = Long.valueOf(r46.getTopicId());
        goto L20
    L17:
        if ((r45 instanceof ProfileActivity) == false) goto L19;
        ProfileActivity r47 = (ProfileActivity) r45;
        r0 = Long.valueOf(r47.getDialogId());
        r42 = Long.valueOf(r47.getTopicId());
    L42:
        goToPulledDialog(r8, r5);
    }

    /* renamed from: $r8$lambda$sPUzo9otFwawnC1-lMMb0CzYuf8, reason: not valid java name */
    public static /* synthetic */ int m8985$r8$lambda$sPUzo9otFwawnC1lMMb0CzYuf8(PulledDialog r0, PulledDialog r1) {
        return r1.stackIndex - r0.stackIndex;
    }

    public static /* synthetic */ int $r8$lambda$zwJbf49aukFzohFh_mJM0GlNr8Q(PulledDialog r0, PulledDialog r1) {
        return r1.stackIndex - r0.stackIndex;
    }

    public static void addToPulledDialogs(BaseFragment r4, int r5, TLRPC.Chat r6, TLRPC.User r7, TLRPC.TL_forumTopic r8, long r9, int r11, int r12) {
        if (r6 != null) goto L5;
        if (r7 != null) goto L5;
        return;
    L5:
        if (r4 == null) goto L38;
        INavigationLayout r42 = r4.getParentLayout();
        if (r42 != null) goto L11;
        return;
    L11:
        if (r42.getPulledDialogs() != null) goto L13;
        r42.setPulledDialogs(new ArrayList());
    L13:
        Iterator<PulledDialog> r0 = r42.getPulledDialogs().iterator();
    L15:
        if (r0.hasNext() == false) goto L26;
        PulledDialog r1 = r0.next();
        if (r8 == null) goto L19;
    L20:
        if (r8 == null) goto L15;
        TLRPC.TL_forumTopic r13 = r1.topic;
        if (r13 == null) goto L15;
        if (r13.id != r8.id) goto L15;
        return;
    L19:
        if (r1.dialogId != r9) goto L20;
        return;
    L26:
        PulledDialog r02 = new PulledDialog();
        r02.activity = ChatActivity.class;
        r02.stackIndex = r5;
        r02.dialogId = r9;
        r02.filterId = r12;
        r02.folderId = r11;
        r02.chat = r6;
        r02.user = r7;
        r02.topic = r8;
        r42.getPulledDialogs().add(r02);
        return;
    }

    public static void clearPulledDialogs(BaseFragment r2, int r3) {
        if (r2 == null) goto L20;
        INavigationLayout r22 = r2.getParentLayout();
        if (r22 != null) goto L8;
        return;
    L8:
        if (r22.getPulledDialogs() == null) goto L16;
        int r0 = 0;
    L11:
        if (r0 >= r22.getPulledDialogs().size()) goto L22;
        if (r22.getPulledDialogs().get(r0).stackIndex <= r3) goto L15;
        r22.getPulledDialogs().remove(r0);
        r0 = r0 - 1;
    L15:
        r0 = r0 + 1;
        goto L11
    L22:
        return;
    L16:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ArrayList<PulledDialog> getStackedHistoryDialogs(BaseFragment r16, long r17) {
        ArrayList<PulledDialog> r0 = new ArrayList();
        if (r16 == null) goto L7;
        INavigationLayout r1 = r16.getParentLayout();
        if (r1 == null) goto L7;
        List<BaseFragment> r2 = r1.getFragmentStack();
        List<PulledDialog> r12 = r1.getPulledDialogs();
        if (r2 == null) goto L45;
        int r4 = r2.size();
        int r5 = 0;
    L11:
        if (r5 >= r4) goto L45;
        BaseFragment r6 = r2.get(r5);
        if ((r6 instanceof ChatActivity) == false) goto L21;
        ChatActivity r62 = (ChatActivity) r6;
        if (r62.getChatMode() != 0) goto L44;
        if (r62.isReport() == true) goto L44;
        TLRPC.Chat r7 = r62.getCurrentChat();
        TLRPC.User r8 = r62.getCurrentUser();
        long r9 = r62.getDialogId();
        int r11 = r62.getDialogFolderId();
        int r63 = r62.getDialogFilterId();
        Class<T> r122 = ChatActivity.class;
    L28:
        if (r9 == r17) goto L44;
        if (r17 == 0) goto L32;
    L33:
        int r13 = 0;
    L35:
        if (r13 >= r0.size()) goto L40;
        if (r0.get(r13).dialogId == r9) goto L44;
        r13 = r13 + 1;
        goto L35
    L40:
        PulledDialog r132 = new PulledDialog();
        r132.activity = r122;
        r132.stackIndex = r5;
        r132.chat = r7;
        r132.user = r8;
        r132.dialogId = r9;
        r132.folderId = r11;
        r132.filterId = r63;
        if (r7 != null) goto L43;
        if (r8 == null) goto L44;
    L43:
        r0.add(r132);
        goto L44
    L32:
        if (UserObject.isUserSelf(r8) == false) goto L33;
    L44:
        r5 = r5 + 1;
        goto L11
    L21:
        if ((r6 instanceof ProfileActivity) == false) goto L44;
        ProfileActivity r64 = (ProfileActivity) r6;
        r7 = r64.getCurrentChat();
        r8 = r64.getUserInfo().user;     // Catch: Exception -> L25
    L26:
        r9 = r64.getDialogId();
        r63 = 0;
        r11 = 0;
        r122 = ProfileActivity.class;
    L25:
        r8 = null;
    L45:
        if (r12 == null) goto L60;
        int r22 = r12.size() - 1;
    L47:
        if (r22 < 0) goto L60;
        PulledDialog r42 = r12.get(r22);
        if (r42.dialogId == r17) goto L59;
        int r52 = 0;
    L53:
        if (r52 >= r0.size()) goto L58;
        if (r0.get(r52).dialogId == r42.dialogId) goto L59;
        r52 = r52 + 1;
        goto L53
    L58:
        r0.add(r42);
    L59:
        r22 = r22 - 1;
    L60:
        Collections.sort(r0, new BackButtonMenu$$ExternalSyntheticLambda1());
        return r0;
    L7:
        return r0;
    }

    private static ArrayList<PulledDialog> getStackedHistoryForTopic(BaseFragment r9, long r10, long r12) {
        ArrayList<PulledDialog> r0 = new ArrayList();
        if (r9 == null) goto L7;
        INavigationLayout r1 = r9.getParentLayout();
        if (r1 == null) goto L7;
        List<PulledDialog> r2 = r1.getPulledDialogs();
        if (r2 == null) goto L23;
        int r4 = 0;
        int r5 = -1;
    L12:
        if (r4 >= r2.size()) goto L25;
        PulledDialog r6 = r2.get(r4);
        if (r6.topic == null) goto L22;
        if (r7.id == r12) goto L22;
        int r7 = r6.stackIndex;
        if (r7 < r5) goto L21;
        r5 = r7;
    L21:
        r0.add(r6);
    L22:
        r4 = r4 + 1;
    L25:
        if (r1.getFragmentStack().size() > 1) goto L27;
    L29:
        PulledDialog r122 = new PulledDialog();
        r0.add(r122);
        r122.stackIndex = -1;
        r122.activity = TopicsFragment.class;
        r122.chat = MessagesController.getInstance(r9.getCurrentAccount()).getChat(Long.valueOf(-r10));
    L30:
        Collections.sort(r0, new BackButtonMenu$$ExternalSyntheticLambda2());
        return r0;
    L27:
        if ((r1.getFragmentStack().get(r1.getFragmentStack().size() - 2) instanceof TopicsFragment) == false) goto L29;
        PulledDialog r123 = new PulledDialog();
        r0.add(r123);
        r123.stackIndex = r5 + 1;
        r123.activity = DialogsActivity.class;
        PulledDialog r124 = new PulledDialog();
        r0.add(r124);
        r124.stackIndex = -1;
        r124.activity = TopicsFragment.class;
        r124.chat = MessagesController.getInstance(r9.getCurrentAccount()).getChat(Long.valueOf(-r10));
        goto L30
    L23:
        r5 = -1;
    L7:
        return r0;
    }

    public static void goToPulledDialog(BaseFragment r10, PulledDialog r11) {
        if (r11 == null) goto L27;
        GenericDeclaration r0 = r11.activity;
        if (r0 != ChatActivity.class) goto L16;
        Bundle r9 = new Bundle();
        TLRPC.Chat r02 = r11.chat;
        if (r02 == null) goto L9;
        r9.putLong("chat_id", r02.id);
    L12:
        r9.putInt("dialog_folder_id", r11.folderId);
        r9.putInt("dialog_filter_id", r11.filterId);
        TLRPC.TL_forumTopic r7 = r11.topic;
        if (r7 == null) goto L15;
        BaseFragment r4 = r10;
        r4.presentFragment(ForumUtilities.getChatActivityForTopic(r4, r11.chat.id, r7, 0, r9), true);
    L20:
        if (r11.activity != TopicsFragment.class) goto L23;
        Bundle r102 = new Bundle();
        r102.putLong("chat_id", r11.chat.id);
        r4.presentFragment(new TopicsFragment(r102), true);
    L23:
        if (r11.activity != DialogsActivity.class) goto L26;
        r4.presentFragment(new DialogsActivity(null), true);
        return;
    L26:
        return;
    L15:
        r4 = r10;
        r4.presentFragment(new ChatActivity(r9), true);
        goto L20
    L9:
        TLRPC.User r03 = r11.user;
        if (r03 == null) goto L12;
        r9.putLong("user_id", r03.id);
        goto L12
    L16:
        r4 = r10;
        if (r0 != ProfileActivity.class) goto L20;
        Bundle r103 = new Bundle();
        r103.putLong("dialog_id", r11.dialogId);
        r4.presentFragment(new ProfileActivity(r103), true);
        goto L20
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [android.view.View, org.telegram.ui.ActionBar.ActionBarPopupWindow$ActionBarPopupWindowLayout] */
    /* JADX WARN: Type inference failed for: r1v23, types: [android.graphics.drawable.BitmapDrawable] */
    /* JADX WARN: Type inference failed for: r7v4, types: [android.view.View, android.view.ViewGroup, android.widget.FrameLayout] */
    public static ActionBarPopupWindow show(BaseFragment r33, View r34, long r35, long r37, Theme.ResourcesProvider r39) {
        if (r33 != null) goto L5;
        return null;
    L5:
        INavigationLayout r4 = r33.getParentLayout();
        Activity r8 = r33.getParentActivity();
        View r9 = r33.getFragmentView();
        if (r4 == null) goto L101;
        if (r8 == null) goto L101;
        if (r9 == null) goto L101;
        long r10 = 0;
        if (r37 != 0) goto L13;
    L16:
        ArrayList<PulledDialog> r0 = getStackedHistoryDialogs(r33, r35);
    L15:
        ArrayList<PulledDialog> r12 = r0;
        if (r12.size() > 0) goto L20;
        return null;
    L20:
        ?? r13 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r8, R.drawable.popup_fixed_alert4, r39);
        Rect r14 = new Rect();
        r33.getParentActivity().getResources().getDrawable(R.drawable.popup_fixed_alert4).mutate().getPadding(r14);
        r13.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r39));
        AtomicReference r1 = new AtomicReference();
        int r15 = r12.size();
        int r2 = 0;
        boolean r3 = false;
    L21:
        long r17 = r10;
        if (r2 >= r15) goto L94;
        if (r2 != 0) goto L25;
        boolean r11 = true;
    L27:
        if (r2 != (r15 - 1)) goto L29;
        boolean r7 = true;
    L30:
        PulledDialog r102 = r12.get(r2);
        TLRPC.Chat r02 = r102.chat;
        AtomicReference r38 = r1;
        TLRPC.User r16 = r102.user;
        boolean r19 = r3;
        INavigationLayout r32 = r4;
        TLRPC.TL_forumTopic r42 = r102.topic;
        boolean r20 = r7;
        ?? r72 = new FrameLayout(r8);
        int r22 = r2;
        r72.setMinimumWidth(AndroidUtilities.dp(200.0f));
        BackupImageView r23 = new BackupImageView(r8);
        if (r02 != null) goto L34;
        if (r16 != null) goto L34;
        INavigationLayout r21 = r32;
        r23.setRoundRadius(0);
        boolean r232 = r11;
    L41:
        r72.addView(r23, LayoutHelper.createFrameRelatively(32.0f, 32.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 8.0f, 0.0f, 0.0f, 0.0f));
        TextView r36 = new TextView(r8);
        r36.setLines(1);
        r36.setTextSize(1, 16.0f);
        r36.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r39));
        r36.setEllipsize(TextUtils.TruncateAt.END);
        r72.addView(r36, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 52.0f, 0.0f, 8.0f, 0.0f));
        AvatarDrawable r5 = new AvatarDrawable();
        r5.setScaleSize(0.8f);
        if (r42 != null) goto L44;
        ArrayList<PulledDialog> r24 = r12;
        if (r02 == null) goto L61;
        r5.setInfo(r33.getCurrentAccount(), r02);
        TLRPC.ChatPhoto r18 = r02.photo;
        if (r18 == null) goto L60;
        ?? r110 = r18.strippedBitmap;
        if (r110 == 0) goto L60;
        r5 = r110;
    L60:
        r23.setImage(ImageLocation.getForChat(r33.getCurrentAccount(), r02, 1), "50_50", r5, r02);
        r36.setText(r02.title);
    L51:
        TLRPC.TL_forumTopic r25 = r42;
    L52:
        boolean r112 = false;
        r19 = true;
    L82:
        r72.setBackground(Theme.getSelectorDrawable(Theme.getColor(Theme.key_listSelector, r39), false));
        r1 = r38;
        INavigationLayout r310 = r21;
        r72.setOnClickListener(new BackButtonMenu$$ExternalSyntheticLambda0(r1, r102, r310, r25, r33));
        if (r232 == false) goto L85;
        int r30 = 3;
    L86:
        if (r20 == false) goto L88;
        int r322 = 3;
    L89:
        r13.addView(r72, LayoutHelper.createLinear(-1, 44, 0.0f, 0, 0, r30, 0, r322));
        if (r112 == false) goto L92;
        FrameLayout r03 = new FrameLayout(r8);
        r03.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuSeparator, r39));
        r03.setTag(R.id.fit_width_tag, 1);
        r13.addView(r03, LayoutHelper.createLinear(-1, 8));
    L92:
        r2 = r22 + 1;
        r4 = r310;
        r10 = r17;
        r3 = r19;
        r12 = r24;
        goto L21
    L88:
        r322 = 0;
        goto L89
    L85:
        r30 = 0;
        goto L86
    L61:
        if (r16 == null) goto L81;
        TLRPC.UserProfilePhoto r04 = r16.photo;
        if (r04 == null) goto L67;
        Drawable r05 = r04.strippedBitmap;
        if (r05 == null) goto L67;
    L68:
        r25 = r42;
        if (r102.activity != ChatActivity.class) goto L74;
        if (UserObject.isUserSelf(r16) == false) goto L74;
        String r06 = LocaleController.getString(R.string.SavedMessages);
        r5.setAvatarType(1);
        r23.setImageDrawable(r5);
    L80:
        r36.setText(r06);
    L74:
        if (UserObject.isReplyUser(r16) == false) goto L77;
        r06 = LocaleController.getString(R.string.RepliesTitle);
        r5.setAvatarType(12);
        r23.setImageDrawable(r5);
        goto L80
    L77:
        if (UserObject.isDeleted(r16) == false) goto L79;
        r06 = LocaleController.getString(R.string.HiddenName);
        r5.setInfo(r33.getCurrentAccount(), r16);
        r23.setImage(ImageLocation.getForUser(r33.getCurrentAccount(), r16, 1), "50_50", r5, r16);
        goto L80
    L79:
        String r43 = UserObject.getUserName(r16);
        r5.setInfo(r33.getCurrentAccount(), r16);
        r23.setImage(ImageLocation.getForUser(r33.getCurrentAccount(), r16, 1), "50_50", r05, r16);
        r06 = r43;
    L67:
        r05 = r5;
        goto L68
    L81:
        r25 = r42;
        r23.setImageDrawable(ContextCompat.getDrawable(r8, R.drawable.msg_viewchats).mutate());
        r23.setSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        r23.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, r39), PorterDuff.Mode.MULTIPLY));
        r36.setText(LocaleController.getString(R.string.AllChats));
        r112 = true;
        goto L82
    L44:
        if (r42.id != 1) goto L47;
        r23.setImageDrawable(ForumUtilities.createGeneralTopicDrawable(r9.getContext(), 1.0f, Theme.getColor(Theme.key_chat_inMenu, r39), false));
        r24 = r12;
    L50:
        r36.setText(r42.title);
        goto L51
    L47:
        if (r42.icon_emoji_id == r17) goto L49;
        r24 = r12;
        r23.setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(10, r33.getCurrentAccount(), r42.icon_emoji_id));
        goto L50
    L49:
        r24 = r12;
        r23.setImageDrawable(ForumUtilities.createTopicDrawable(r42, false));
    L34:
        r21 = r32;
        if (r02 != null) goto L37;
    L39:
        boolean r311 = false;
    L40:
        r232 = r11;
        r23.setRoundRadius(ExteraConfig.getAvatarCorners(32.0f, false, r311));
        goto L41
    L37:
        if (r02.forum == false) goto L39;
        r311 = true;
        goto L40
    L29:
        r7 = false;
        goto L30
    L25:
        r11 = false;
        goto L27
    L94:
        if (r3 == true) goto L96;
        return null;
    L96:
        ActionBarPopupWindow r07 = new ActionBarPopupWindow(r13, -2, -2);
        r1.set(r07);
        r07.setPauseNotifications(true);
        r07.setDismissAnimationDuration(Opcodes.REM_INT_LIT8);
        r07.setOutsideTouchable(true);
        r07.setClippingEnabled(true);
        r07.setAnimationStyle(R.style.PopupContextAnimation);
        r07.setFocusable(true);
        r13.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        r07.setInputMethodMode(2);
        r07.setSoftInputMode(0);
        r07.getContentView().setFocusableInTouchMode(true);
        r13.setFitItems(true);
        int r26 = AndroidUtilities.dp(7.0f) - r14.left;
        if (AndroidUtilities.isTablet() == false) goto L99;
        int[] r111 = new int[2];
        r9.getLocationInWindow(r111);
        r26 = r26 + r111[0];
    L99:
        r07.showAtLocation(r9, 51, r26, (r34.getBottom() - r14.top) - AndroidUtilities.dp(1.0f));
        return r07;
    L13:
        if (ChatObject.isMonoForum(r33.getCurrentAccount(), r35) == true) goto L16;
        r0 = getStackedHistoryForTopic(r33, r35, r37);
    L101:
        return null;
    }
}
