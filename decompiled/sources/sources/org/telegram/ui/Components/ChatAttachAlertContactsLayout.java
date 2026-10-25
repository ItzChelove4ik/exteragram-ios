package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.messenger.utils.TextWatcherImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ChatAttachAlertContactsLayout extends ChatAttachAlert.AttachAlertLayout implements NotificationCenter.NotificationCenterDelegate, FactorAnimator.Target {
    private final BoolAnimator animatorFadeVisible;
    private PhonebookShareAlertDelegate delegate;
    private final EmptyTextProgressView emptyView;
    private final View fadeView;
    private final FrameLayout frameLayout;
    private final FillLastLinearLayoutManager layoutManager;
    private final ShareAdapter listAdapter;
    private final RecyclerListView listView;
    private boolean multipleSelectionAllowed;
    private final ShareSearchAdapter searchAdapter;
    private final FragmentSearchField searchField;
    private final HashMap<ListItemID, Object> selectedContacts;
    private final ArrayList<ListItemID> selectedContactsOrder;
    private boolean sendPressed;

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertContactsLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcherImpl {
        final /* synthetic */ ChatAttachAlertContactsLayout this$0;

        public AnonymousClass1(ChatAttachAlertContactsLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            String r42 = r4.toString();
            boolean r0 = r42.isEmpty();
            ChatAttachAlertContactsLayout r1 = this.this$0;
            if (r0 == true) goto L8;
            if (ChatAttachAlertContactsLayout.m9565$$Nest$fgetemptyView(r1) == null) goto L13;
            ChatAttachAlertContactsLayout.m9565$$Nest$fgetemptyView(this.this$0).setText(LocaleController.getString(R.string.NoResult));
        L13:
            if (ChatAttachAlertContactsLayout.m9569$$Nest$fgetsearchAdapter(this.this$0) == null) goto L16;
            ChatAttachAlertContactsLayout.m9569$$Nest$fgetsearchAdapter(this.this$0).search(r42);
            return;
        L16:
            return;
        L8:
            if (ChatAttachAlertContactsLayout.m9568$$Nest$fgetlistView(r1).getAdapter() == ChatAttachAlertContactsLayout.m9567$$Nest$fgetlistAdapter(this.this$0)) goto L13;
            int r02 = ChatAttachAlertContactsLayout.m9571$$Nest$mgetCurrentTop(this.this$0);
            ChatAttachAlertContactsLayout.m9565$$Nest$fgetemptyView(this.this$0).setText(LocaleController.getString(R.string.NoContacts));
            ChatAttachAlertContactsLayout.m9565$$Nest$fgetemptyView(this.this$0).showTextView();
            ChatAttachAlertContactsLayout.m9568$$Nest$fgetlistView(this.this$0).setAdapter(ChatAttachAlertContactsLayout.m9567$$Nest$fgetlistAdapter(this.this$0));
            ChatAttachAlertContactsLayout.m9567$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
            if (r02 <= 0) goto L13;
            ChatAttachAlertContactsLayout.m9566$$Nest$fgetlayoutManager(this.this$0).scrollToPositionWithOffset(0, -r02);
            goto L13
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertContactsLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ ChatAttachAlertContactsLayout this$0;

        public AnonymousClass2(ChatAttachAlertContactsLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(float r3, float r4) {
            int r32 = this.this$0.parentAlert.scrollOffsetY[0] + AndroidUtilities.dp(30.0f);
            if (this.this$0.parentAlert.inBubbleMode == true) goto L5;
            int r2 = AndroidUtilities.statusBarHeight;
        L7:
            if (r4 < (r32 + r2)) goto L10;
            return true;
        L10:
            return false;
        L5:
            r2 = 0;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertContactsLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends FillLastLinearLayoutManager {
        final /* synthetic */ ChatAttachAlertContactsLayout this$0;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertContactsLayout$3$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearSmoothScroller {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDyToMakeVisible(View r1, int r2) {
                return super.calculateDyToMakeVisible(r1, r2) - ((ChatAttachAlertContactsLayout.m9568$$Nest$fgetlistView(this.this$1.this$0).getPaddingTop() - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(8.0f));
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateTimeForDeceleration(int r1) {
                return super.calculateTimeForDeceleration(r1) * 2;
            }
        }

        public AnonymousClass3(ChatAttachAlertContactsLayout r1, Context r2, int r3, boolean r4, int r5, RecyclerView r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
            AnonymousClass1 r22 = new AnonymousClass1(this, r1.getContext());
            r22.setTargetPosition(r3);
            startSmoothScroll(r22);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertContactsLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatAttachAlertContactsLayout this$0;

        public AnonymousClass4(ChatAttachAlertContactsLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            ChatAttachAlertContactsLayout r22 = this.this$0;
            r22.parentAlert.updateLayout(r22, true, r4);
            ChatAttachAlertContactsLayout.m9573$$Nest$mupdateEmptyViewPosition(this.this$0);
        }
    }

    public static class ListItemID {
        private final long id;
        private final Type type;

        public enum Type extends Enum<Type> {
            private static final /* synthetic */ Type[] $VALUES = null;
            public static final Type CONTACT = null;
            public static final Type USER = null;

            private static /* synthetic */ Type[] $values() {
                return new Type[]{USER, CONTACT};
            }

            static {
                USER = new Type("USER", 0);
                CONTACT = new Type("CONTACT", 1);
                $VALUES = $values();
            }

            Type(String r1, int r2) {
            }

            public static Type valueOf(String r1) {
                return (Type) Enum.valueOf(Type.class, r1);
            }

            public static Type[] values() {
                return (Type[]) $VALUES.clone();
            }
        }

        public ListItemID(Type r1, long r2) {
            this.type = r1;
            this.id = r2;
        }

        public static ListItemID of(Object r4) {
            if ((r4 instanceof ContactsController.Contact) == false) goto L7;
            return new ListItemID(Type.CONTACT, ((ContactsController.Contact) r4).contact_id);
        L7:
            if ((r4 instanceof TLRPC.User) == true) goto L9;
            return null;
        L9:
            return new ListItemID(Type.USER, ((TLRPC.User) r4).id);
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L15:
            return false;
        L8:
            if (getClass() != r7.getClass()) goto L15;
            ListItemID r72 = (ListItemID) r7;
            if (this.id != r72.id) goto L15;
            if (this.type != r72.type) goto L15;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.type, Long.valueOf(this.id)});
        }
    }

    public interface PhonebookShareAlertDelegate {
        void didSelectContact(TLRPC.User r1, boolean r2, int r3, long r4, boolean r6, long r7);

        default void didSelectContacts(ArrayList<TLRPC.User> r1, String r2, boolean r3, int r4, long r5, boolean r7, long r8) {
        }
    }

    public class ShareAdapter extends RecyclerListView.SectionsAdapter {
        private int currentAccount;
        private Context mContext;
        final /* synthetic */ ChatAttachAlertContactsLayout this$0;

        public static /* synthetic */ CharSequence $r8$lambda$CI_yKwj_ZEXkV2sijw1B0VX0k9c(TLRPC.User r3) {
            return PhoneFormat.getInstance().format("+" + r3.phone);
        }

        public static /* synthetic */ CharSequence $r8$lambda$P5ZiTS1sCM5N3fBMSNztNGvaA88(ContactsController.Contact r2) {
            if (r2.phones.isEmpty() == false) goto L7;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            return PhoneFormat.getInstance().format(r2.phones.get(0));
        }

        public ShareAdapter(ChatAttachAlertContactsLayout r1, Context r2) {
            this.this$0 = r1;
            this.currentAccount = UserConfig.selectedAccount;
            this.mContext = r2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getCountForSection(int r3) {
            if (r3 != 0) goto L5;
        L13:
            return 1;
        L5:
            if (r3 == (getSectionCount() - 1)) goto L13;
            int r32 = r3 - 1;
            HashMap<String, ArrayList<Object>> r0 = ContactsController.getInstance(this.currentAccount).phoneBookSectionsDict;
            ArrayList<String> r2 = ContactsController.getInstance(this.currentAccount).phoneBookSectionsArray;
            if (r32 < r2.size()) goto L10;
            return 0;
        L10:
            return r0.get(r2.get(r32)).size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public Object getItem(int r4, int r5) {
            if (r4 != 0) goto L5;
            return null;
        L5:
            int r42 = r4 - 1;
            HashMap<String, ArrayList<Object>> r1 = ContactsController.getInstance(this.currentAccount).phoneBookSectionsDict;
            ArrayList<String> r3 = ContactsController.getInstance(this.currentAccount).phoneBookSectionsArray;
            if (r42 >= r3.size()) goto L11;
            ArrayList<Object> r32 = r1.get(r3.get(r42));
            if (r5 >= r32.size()) goto L11;
            return r32.get(r5);
        L11:
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getItemViewType(int r1, int r2) {
            if (r1 != 0) goto L6;
            return 1;
        L6:
            if (r1 != (getSectionCount() - 1)) goto L9;
            return 2;
        L9:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r1) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r1, float r2, int[] r3) {
            r3[0] = 0;
            r3[1] = 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getSectionCount() {
            return ContactsController.getInstance(this.currentAccount).phoneBookSectionsArray.size() + 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public View getSectionHeaderView(int r1, View r2) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3, int r4, int r5) {
            if (r4 != 0) goto L5;
        L10:
            return false;
        L5:
            if (r4 == (getSectionCount() - 1)) goto L10;
            if (r5 >= ContactsController.getInstance(this.currentAccount).phoneBookSectionsDict.get(ContactsController.getInstance(this.currentAccount).phoneBookSectionsArray.get(r4 - 1)).size()) goto L10;
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            ChatAttachAlertContactsLayout.m9572$$Nest$mupdateEmptyView(this.this$0);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public void onBindViewHolder(int r6, int r7, RecyclerView.ViewHolder r8) {
            if (r8.getItemViewType() != 0) goto L21;
            UserCell r82 = (UserCell) r8.itemView;
            Object r0 = getItem(r6, r7);
            boolean r3 = true;
            if (r6 != (getSectionCount() - 2)) goto L11;
            if (r7 != (getCountForSection(r6) - 1)) goto L11;
            r3 = false;
        L11:
            if ((r0 instanceof ContactsController.Contact) == false) goto L16;
            ContactsController.Contact r62 = (ContactsController.Contact) r0;
            TLRPC.User r1 = r62.user;
            if (r1 != null) goto L17;
            r82.setCurrentId(r62.contact_id);
            r82.setData(null, ContactsController.formatName(r62.first_name, r62.last_name), new ChatAttachAlertContactsLayout$ShareAdapter$$ExternalSyntheticLambda0(r62), r3);
            r1 = null;
        L17:
            if (r1 == null) goto L19;
            r82.setData(r1, null, new ChatAttachAlertContactsLayout$ShareAdapter$$ExternalSyntheticLambda1(r1), r3);
        L19:
            r82.setChecked(ChatAttachAlertContactsLayout.m9570$$Nest$fgetselectedContacts(this.this$0).containsKey(ListItemID.of(r0)), false);
            return;
        L16:
            r1 = (TLRPC.User) r0;
            goto L17
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L5;
            View r42 = new UserCell(this.mContext, this.this$0.resourcesProvider);
        L10:
            return new RecyclerListView.Holder(r42);
        L5:
            if (r4 == 1) goto L7;
            r42 = new View(this.mContext);
            r42.setTag(-33024);
            goto L10
        L7:
            r42 = new View(this.mContext);
            r42.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(56.0f)));
            r42.setTag(-33024);
            goto L10
        }
    }

    public class ShareSearchAdapter extends RecyclerListView.SelectionAdapter {
        private int lastSearchId;
        private Context mContext;
        private ArrayList<Object> searchResult;
        private ArrayList<CharSequence> searchResultNames;
        private Runnable searchRunnable;
        final /* synthetic */ ChatAttachAlertContactsLayout this$0;

        public static /* synthetic */ void $r8$lambda$Fv6PpNVwYtgSXmQlGPCRdGJH6nQ(ShareSearchAdapter r0, int r1, ArrayList r2, ArrayList r3) {
            r0.lambda$updateSearchResults$3(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$QS6Rt2x_keFf3-QNEI9PhO2jKic, reason: not valid java name */
        public static /* synthetic */ void m9574$r8$lambda$QS6Rt2x_keFf3QNEI9PhO2jKic(ShareSearchAdapter r0, String r1, int r2) {
            r0.lambda$search$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$ToGWnixUqwpRtG8QahP5W5C23AM(ShareSearchAdapter r0, String r1, int r2) {
            r0.lambda$processSearch$2(r1, r2);
        }

        public static /* synthetic */ CharSequence $r8$lambda$UsKdsXu_RNqNGlaI1rvzoDtIVWE(TLRPC.User r3) {
            return PhoneFormat.getInstance().format("+" + r3.phone);
        }

        public static /* synthetic */ void $r8$lambda$hAvU1N2j_4ka49vIdUnn1T7hTOY(ShareSearchAdapter r0, String r1, ArrayList r2, ArrayList r3, int r4, int r5) {
            r0.lambda$processSearch$1(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ CharSequence $r8$lambda$ohVs6ue6ETDwcw3OZ9SHGpDXFzI(ContactsController.Contact r2) {
            if (r2.phones.isEmpty() == false) goto L7;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            return PhoneFormat.getInstance().format(r2.phones.get(0));
        }

        public ShareSearchAdapter(ChatAttachAlertContactsLayout r1, Context r2) {
            this.this$0 = r1;
            this.searchResult = new ArrayList();
            this.searchResultNames = new ArrayList();
            this.mContext = r2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v17 */
        /* JADX WARN: Type inference failed for: r10v21 */
        /* JADX WARN: Type inference failed for: r10v28 */
        /* JADX WARN: Type inference failed for: r10v33 */
        /* JADX WARN: Type inference failed for: r10v35 */
        /* JADX WARN: Type inference failed for: r21v0, types: [org.telegram.ui.Components.ChatAttachAlertContactsLayout$ShareSearchAdapter] */
        private /* synthetic */ void lambda$processSearch$1(String r22, ArrayList r23, ArrayList r24, int r25, int r26) {
            String r2 = r22.trim().toLowerCase();
            if (r2.length() != 0) goto L6;
            this.lastSearchId = -1;
            updateSearchResults(r22, new ArrayList(), new ArrayList(), this.lastSearchId);
            return;
        L6:
            String r3 = LocaleController.getInstance().getTranslitString(r2);
            if (r2.equals(r3) == false) goto L9;
        L10:
            r3 = null;
        L12:
            if (r3 == null) goto L14;
            int r7 = 1;
        L15:
            int r72 = r7 + 1;
            String[] r8 = new String[r72];
            r8[0] = r2;
            if (r3 == null) goto L18;
            r8[1] = r3;
        L18:
            ArrayList r27 = new ArrayList();
            ArrayList r32 = new ArrayList();
            LongSparseIntArray r9 = new LongSparseIntArray();
            int r10 = 0;
        L20:
            if (r10 >= r23.size()) goto L81;
            ContactsController.Contact r15 = (ContactsController.Contact) r23.get(r10);
            String r4 = ContactsController.formatName(r15.first_name, r15.last_name).toLowerCase();
            String r12 = LocaleController.getInstance().getTranslitString(r4);
            TLRPC.User r5 = r15.user;
            if (r5 == null) goto L24;
            String r52 = ContactsController.formatName(r5.first_name, r5.last_name).toLowerCase();
            String r6 = LocaleController.getInstance().getTranslitString(r4);
        L26:
            if (r4.equals(r12) == false) goto L28;
            r12 = null;
        L28:
            String[] r17 = r8;
            int r82 = 0;
            boolean r16 = false;
        L29:
            if (r82 >= r72) goto L79;
            int r18 = r82;
            String r83 = r17[r18];
            int r19 = r10;
            if (r52 != null) goto L33;
        L36:
            if (r6 != null) goto L38;
        L42:
            TLRPC.User r102 = r15.user;
            if (r102 == null) goto L50;
            String r103 = UserObject.getPublicUsername(r102);
            if (r103 == null) goto L50;
            if (r103.startsWith(r83) == false) goto L50;
            ?? r104 = 2;
        L61:
            String r162 = r4;
            if (r104 == 0) goto L78;
            if (r15.phones.isEmpty() == false) goto L68;
            if (r15.shortPhones.isEmpty() == true) goto L78;
        L68:
            if (r104 != 3) goto L70;
            r32.add(AndroidUtilities.generateSearchName(r15.first_name, r15.last_name, r83));
        L74:
            TLRPC.User r42 = r15.user;
            if (r42 == null) goto L77;
            r9.put(r42.id, 1);
        L77:
            r27.add(r15);
        L80:
            r10 = r19 + 1;
            r8 = r17;
            goto L20
        L70:
            TLRPC.User r43 = r15.user;
            if (r104 != 1) goto L73;
            r32.add(AndroidUtilities.generateSearchName(r43.first_name, r43.last_name, r83));
            goto L74
        L73:
            r32.add(AndroidUtilities.generateSearchName("@" + UserObject.getPublicUsername(r43), null, "@" + r83));
        L78:
            r82 = r18 + 1;
            r4 = r162;
            r16 = r104;
            r10 = r19;
        L50:
            if (r4.startsWith(r83) == false) goto L52;
        L60:
            r104 = 3;
            goto L61
        L52:
            if (r4.contains(" " + r83) == true) goto L60;
            if (r12 != null) goto L55;
        L59:
            r104 = r16;
            goto L61
        L55:
            if (r12.startsWith(r83) == true) goto L60;
            if (r12.contains(" " + r83) == false) goto L59;
        L38:
            if (r6.startsWith(r83) == false) goto L40;
        L41:
            r104 = 1;
            goto L61
        L40:
            if (r6.contains(" " + r83) == false) goto L42;
        L33:
            if (r52.startsWith(r83) == true) goto L41;
            if (r52.contains(" " + r83) == true) goto L41;
        L79:
            r19 = r10;
            goto L80
        L24:
            r52 = null;
            r6 = null;
            goto L26
        L81:
            String[] r172 = r8;
            int r44 = 0;
        L83:
            if (r44 >= r24.size()) goto L119;
            TLRPC.TL_contact r62 = (TLRPC.TL_contact) r24.get(r44);
            if (r9.indexOfKey(r62.user_id) < 0) goto L87;
        L86:
            int r163 = r44;
        L118:
            r44 = r163 + 1;
            goto L83
        L87:
            TLRPC.User r63 = MessagesController.getInstance(r25).getUser(Long.valueOf(r62.user_id));
            String r84 = ContactsController.formatName(r63.first_name, r63.last_name).toLowerCase();
            String r105 = LocaleController.getInstance().getTranslitString(r84);
            if (r84.equals(r105) == false) goto L90;
            r105 = null;
        L90:
            char r11 = 0;
            int r122 = 0;
        L91:
            if (r122 >= r72) goto L86;
            String r152 = r172[r122];
            if (r84.startsWith(r152) == true) goto L107;
            r163 = r44;
            if (r84.contains(" " + r152) == true) goto L108;
            if (r105 != null) goto L98;
        L102:
            String r45 = UserObject.getPublicUsername(r63);
            if (r45 != null) goto L105;
        L109:
            if (r11 == 0) goto L117;
            if (r63.phone == null) goto L117;
            if (r11 != 1) goto L115;
            r32.add(AndroidUtilities.generateSearchName(r63.first_name, r63.last_name, r152));
        L116:
            r27.add(r63);
            goto L118
        L115:
            r32.add(AndroidUtilities.generateSearchName("@" + UserObject.getPublicUsername(r63), null, "@" + r152));
        L117:
            r122 = r122 + 1;
            r44 = r163;
            goto L91
        L105:
            if (r45.startsWith(r152) == false) goto L109;
            r11 = 2;
            goto L109
        L98:
            if (r105.startsWith(r152) == true) goto L108;
            if (r105.contains(" " + r152) == false) goto L102;
        L108:
            r11 = 1;
            goto L109
        L107:
            r163 = r44;
            goto L108
        L119:
            updateSearchResults(r22, r27, r32, r26);
            return;
        L14:
            r7 = 0;
            goto L15
        L9:
            if (r3.length() != 0) goto L12;
            goto L10
        }

        private /* synthetic */ void lambda$processSearch$2(String r9, int r10) {
            int r5 = UserConfig.selectedAccount;
            ArrayList r3 = new ArrayList(ContactsController.getInstance(r5).contactsBook.values());
            ArrayList r4 = new ArrayList(ContactsController.getInstance(r5).contacts);
            Utilities.searchQueue.postRunnable(new ChatAttachAlertContactsLayout$ShareSearchAdapter$$ExternalSyntheticLambda4(this, r9, r3, r4, r5, r10));
        }

        private /* synthetic */ void lambda$search$0(String r1, int r2) {
            processSearch(r1, r2);
        }

        private /* synthetic */ void lambda$updateSearchResults$3(int r2, ArrayList r3, ArrayList r4) {
            if (r2 == this.lastSearchId) goto L6;
            return;
        L6:
            if (r2 != (-1)) goto L8;
        L10:
            this.searchResult = r3;
            this.searchResultNames = r4;
            notifyDataSetChanged();
            return;
        L8:
            if (ChatAttachAlertContactsLayout.m9568$$Nest$fgetlistView(this.this$0).getAdapter() == ChatAttachAlertContactsLayout.m9569$$Nest$fgetsearchAdapter(this.this$0)) goto L10;
            ChatAttachAlertContactsLayout.m9568$$Nest$fgetlistView(this.this$0).setAdapter(ChatAttachAlertContactsLayout.m9569$$Nest$fgetsearchAdapter(this.this$0));
            goto L10
        }

        private void processSearch(String r2, int r3) {
            AndroidUtilities.runOnUIThread(new ChatAttachAlertContactsLayout$ShareSearchAdapter$$ExternalSyntheticLambda3(this, r2, r3));
        }

        private void updateSearchResults(String r1, ArrayList<Object> r2, ArrayList<CharSequence> r3, int r4) {
            AndroidUtilities.runOnUIThread(new ChatAttachAlertContactsLayout$ShareSearchAdapter$$ExternalSyntheticLambda5(this, r4, r2, r3));
        }

        public Object getItem(int r2) {
            int r22 = r2 - 1;
            if (r22 >= 0) goto L5;
            return null;
        L5:
            if (r22 < this.searchResult.size()) goto L8;
            return null;
        L8:
            return this.searchResult.get(r22);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.searchResult.size() + 2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != 0) goto L6;
            return 1;
        L6:
            if (r2 != (getItemCount() - 1)) goto L9;
            return 2;
        L9:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            ChatAttachAlertContactsLayout.m9572$$Nest$mupdateEmptyView(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            if (r8.getItemViewType() != 0) goto L19;
            UserCell r82 = (UserCell) r8.itemView;
            if (r9 == (getItemCount() - 2)) goto L7;
            boolean r0 = true;
        L8:
            Object r3 = getItem(r9);
            if ((r3 instanceof ContactsController.Contact) == false) goto L14;
            ContactsController.Contact r4 = (ContactsController.Contact) r3;
            TLRPC.User r5 = r4.user;
            if (r5 != null) goto L15;
            r82.setCurrentId(r4.contact_id);
            r82.setData(null, this.searchResultNames.get(r9 - 1), new ChatAttachAlertContactsLayout$ShareSearchAdapter$$ExternalSyntheticLambda0(r4), r0);
            r5 = null;
        L15:
            if (r5 == null) goto L17;
            r82.setData(r5, this.searchResultNames.get(r9 - 1), new ChatAttachAlertContactsLayout$ShareSearchAdapter$$ExternalSyntheticLambda1(r5), r0);
        L17:
            r82.setChecked(ChatAttachAlertContactsLayout.m9570$$Nest$fgetselectedContacts(this.this$0).containsKey(ListItemID.of(r3)), false);
            return;
        L14:
            r5 = (TLRPC.User) r3;
            goto L15
        L7:
            r0 = false;
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L5;
            View r42 = new UserCell(this.mContext, this.this$0.resourcesProvider);
        L10:
            return new RecyclerListView.Holder(r42);
        L5:
            if (r4 == 1) goto L7;
            r42 = new View(this.mContext);
            r42.setTag(-33024);
            goto L10
        L7:
            r42 = new View(this.mContext);
            r42.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(56.0f)));
            r42.setTag(-33024);
            goto L10
        }

        public void search(String r4) {
            if (this.searchRunnable == null) goto L5;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L5:
            if (r4 != null) goto L8;
            this.searchResult.clear();
            this.searchResultNames.clear();
            notifyDataSetChanged();
            return;
        L8:
            int r0 = this.lastSearchId + 1;
            this.lastSearchId = r0;
            DispatchQueue r1 = Utilities.searchQueue;
            ChatAttachAlertContactsLayout$ShareSearchAdapter$$ExternalSyntheticLambda2 r2 = new ChatAttachAlertContactsLayout$ShareSearchAdapter$$ExternalSyntheticLambda2(this, r4, r0);
            this.searchRunnable = r2;
            r1.postRunnable(r2, 300);
        }
    }

    public static class UserCell extends FrameLayout {
        private AvatarDrawable avatarDrawable;
        private BackupImageView avatarImageView;
        private CheckBox2 checkBox;
        private int currentAccount;
        private int currentId;
        private CharSequence currentName;
        private CharSequence currentStatus;
        private TLRPC.User currentUser;
        private CharSequence formattedPhoneNumber;
        private TLRPC.User formattedPhoneNumberUser;
        private TLRPC.FileLocation lastAvatar;
        private String lastName;
        private int lastStatus;
        private SimpleTextView nameTextView;
        private boolean needDivider;
        private final Theme.ResourcesProvider resourcesProvider;
        private SimpleTextView statusTextView;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertContactsLayout$UserCell$1, reason: invalid class name */
        public class AnonymousClass1 extends SimpleTextView {
            final /* synthetic */ UserCell this$0;

            public AnonymousClass1(UserCell r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.ActionBar.SimpleTextView
            public boolean setText(CharSequence r3, boolean r4) {
                return super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
            }
        }

        public interface CharSequenceCallback {
            CharSequence run();
        }

        /* renamed from: $r8$lambda$0OwuBYTj_mZOrWRLv3-cVEMg15Q, reason: not valid java name */
        public static /* synthetic */ void m9575$r8$lambda$0OwuBYTj_mZOrWRLv3cVEMg15Q(UserCell r0) {
            r0.lambda$setStatus$3();
        }

        public static /* synthetic */ void $r8$lambda$RmCLiI3A691Sm0w97MdHSumY7vo(UserCell r0, CharSequence r1) {
            r0.lambda$setData$0(r1);
        }

        /* renamed from: $r8$lambda$cjnU-KXLGs3x-vWSBq62ttkO0_g, reason: not valid java name */
        public static /* synthetic */ void m9576$r8$lambda$cjnUKXLGs3xvWSBq62ttkO0_g(UserCell r0, CharSequenceCallback r1) {
            r0.lambda$setData$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$lrG5yxSScsmwcndTu0ZEtUeWytA(UserCell r0) {
            r0.lambda$setStatus$2();
        }

        public UserCell(Context r25, Theme.ResourcesProvider r26) {
            super(r25);
            this.currentAccount = UserConfig.selectedAccount;
            this.resourcesProvider = r26;
            this.avatarDrawable = new AvatarDrawable(r26);
            BackupImageView r3 = new BackupImageView(r25);
            this.avatarImageView = r3;
            r3.setRoundRadius(ExteraConfig.getAvatarCorners(46.0f));
            BackupImageView r32 = this.avatarImageView;
            boolean r4 = LocaleController.isRTL;
            int r5 = 5;
            if (r4 == false) goto L5;
            int r7 = 5;
        L6:
            int r10 = r7 | 48;
            if (r4 == false) goto L9;
            float r11 = 0.0f;
        L10:
            if (r4 == false) goto L12;
            float r13 = 14.0f;
        L13:
            addView(r32, LayoutHelper.createFrame(46, 46.0f, r10, r11, 9.0f, r13, 0.0f));
            AnonymousClass1 r33 = new AnonymousClass1(this, r25);
            this.nameTextView = r33;
            NotificationCenter.listenEmojiLoading(r33);
            this.nameTextView.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
            this.nameTextView.setTypeface(AndroidUtilities.bold());
            this.nameTextView.setTextSize(16);
            SimpleTextView r34 = this.nameTextView;
            if (LocaleController.isRTL == false) goto L16;
            int r42 = 5;
        L17:
            r34.setGravity(r42 | 48);
            SimpleTextView r35 = this.nameTextView;
            boolean r43 = LocaleController.isRTL;
            if (r43 == false) goto L20;
            int r72 = 5;
        L21:
            int r102 = r72 | 48;
            if (r43 == false) goto L24;
            float r112 = 28.0f;
        L25:
            if (r43 == false) goto L27;
            float r132 = 72.0f;
        L28:
            addView(r35, LayoutHelper.createFrame(-1, 20.0f, r102, r112, 12.0f, r132, 0.0f));
            SimpleTextView r36 = new SimpleTextView(r25);
            this.statusTextView = r36;
            r36.setTextSize(13);
            this.statusTextView.setTextColor(getThemedColor(Theme.key_dialogTextGray2));
            SimpleTextView r37 = this.statusTextView;
            if (LocaleController.isRTL == false) goto L31;
            int r44 = 5;
        L32:
            r37.setGravity(r44 | 48);
            SimpleTextView r38 = this.statusTextView;
            boolean r45 = LocaleController.isRTL;
            if (r45 == false) goto L35;
            int r8 = 5;
        L36:
            int r19 = r8 | 48;
            if (r45 == false) goto L39;
            float r20 = 28.0f;
        L40:
            if (r45 == false) goto L42;
            float r22 = 72.0f;
        L43:
            addView(r38, LayoutHelper.createFrame(-1, 20.0f, r19, r20, 36.0f, r22, 0.0f));
            CheckBox2 r39 = new CheckBox2(r25, 21, r26);
            this.checkBox = r39;
            r39.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
            this.checkBox.setDrawUnchecked(false);
            this.checkBox.setDrawBackgroundAsArc(3);
            CheckBox2 r1 = this.checkBox;
            boolean r2 = LocaleController.isRTL;
            if (r2 == true) goto L47;
            r5 = 3;
        L47:
            int r82 = r5 | 48;
            if (r2 == false) goto L50;
            float r9 = 0.0f;
        L51:
            if (r2 == false) goto L53;
            float r113 = 44.0f;
        L54:
            addView(r1, LayoutHelper.createFrame(24, 24.0f, r82, r9, 37.0f, r113, 0.0f));
            return;
        L53:
            r113 = 0.0f;
            goto L54
        L50:
            r9 = 44.0f;
            goto L51
        L42:
            r22 = 28.0f;
            goto L43
        L39:
            r20 = 72.0f;
            goto L40
        L35:
            r8 = 3;
            goto L36
        L31:
            r44 = 3;
            goto L32
        L27:
            r132 = 28.0f;
            goto L28
        L24:
            r112 = 72.0f;
            goto L25
        L20:
            r72 = 3;
            goto L21
        L16:
            r42 = 3;
            goto L17
        L12:
            r13 = 0.0f;
            goto L13
        L9:
            r11 = 14.0f;
            goto L10
        L5:
            r7 = 3;
            goto L6
        }

        private /* synthetic */ void lambda$setData$0(CharSequence r1) {
            setStatus(r1);
        }

        private /* synthetic */ void lambda$setData$1(CharSequenceCallback r2) {
            AndroidUtilities.runOnUIThread(new ChatAttachAlertContactsLayout$UserCell$$ExternalSyntheticLambda3(this, r2.run()));
        }

        private /* synthetic */ void lambda$setStatus$2() {
            this.statusTextView.setText(this.formattedPhoneNumber);
        }

        private /* synthetic */ void lambda$setStatus$3() {
            if (this.currentUser == null) goto L6;
            this.formattedPhoneNumber = PhoneFormat.getInstance().format("+" + this.currentUser.phone);
            this.formattedPhoneNumberUser = this.currentUser;
            AndroidUtilities.runOnUIThread(new ChatAttachAlertContactsLayout$UserCell$$ExternalSyntheticLambda1(this));
            return;
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            if (this.needDivider == true) goto L5;
            return;
        L5:
            if (LocaleController.isRTL == false) goto L8;
            float r0 = 0.0f;
        L7:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L12;
            int r1 = AndroidUtilities.dp(70.0f);
        L13:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L12:
            r1 = 0;
            goto L13
        L8:
            r0 = AndroidUtilities.dp(70.0f);
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        }

        public void setChecked(boolean r3, boolean r4) {
            if (this.checkBox.getVisibility() == 0) goto L5;
            this.checkBox.setVisibility(0);
        L5:
            this.checkBox.setChecked(r3, r4);
        }

        public void setCurrentId(int r1) {
            this.currentId = r1;
        }

        public void setData(TLRPC.User r1, CharSequence r2, CharSequence r3, boolean r4) {
            if (r1 != null) goto L7;
            if (r2 != null) goto L7;
            if (r3 != null) goto L7;
            this.currentStatus = null;
            this.currentName = null;
            this.nameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.statusTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.avatarImageView.setImageDrawable(null);
            return;
        L7:
            this.currentStatus = r3;
            this.currentName = r2;
            this.currentUser = r1;
            this.needDivider = r4;
            setWillNotDraw(!r4);
            update(0);
        }

        public void setData(TLRPC.User r2, CharSequence r3, CharSequenceCallback r4, boolean r5) {
            setData(r2, r3, null, r5);
            Utilities.globalQueue.postRunnable(new ChatAttachAlertContactsLayout$UserCell$$ExternalSyntheticLambda2(this, r4));
        }

        public void setStatus(CharSequence r2) {
            this.currentStatus = r2;
            if (r2 == null) goto L6;
            this.statusTextView.setText(r2);
            return;
        L6:
            TLRPC.User r22 = this.currentUser;
            if (r22 != null) goto L9;
            return;
        L9:
            if (TextUtils.isEmpty(r22.phone) == false) goto L13;
            this.statusTextView.setText(LocaleController.getString(R.string.NumberUnknown));
            return;
        L13:
            if (this.formattedPhoneNumberUser == this.currentUser) goto L18;
            CharSequence r23 = this.formattedPhoneNumber;
            if (r23 == null) goto L18;
            this.statusTextView.setText(r23);
            return;
        L18:
            this.statusTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            Utilities.globalQueue.postRunnable(new ChatAttachAlertContactsLayout$UserCell$$ExternalSyntheticLambda0(this));
        }

        public void update(int r11) {
            TLRPC.User r0 = this.currentUser;
            if (r0 == null) goto L7;
            TLRPC.UserProfilePhoto r2 = r0.photo;
            if (r2 == null) goto L7;
            TLRPC.FileLocation r22 = r2.photo_small;
        L9:
            if (r11 == 0) goto L53;
            boolean r5 = true;
            if ((MessagesController.UPDATE_MASK_AVATAR & r11) == 0) goto L24;
            TLRPC.FileLocation r4 = this.lastAvatar;
            if (r4 == null) goto L15;
            if (r22 != null) goto L15;
        L23:
            boolean r42 = true;
        L25:
            if (r0 == null) goto L36;
            if (r42 == true) goto L36;
            if ((MessagesController.UPDATE_MASK_STATUS & r11) == 0) goto L36;
            TLRPC.UserStatus r6 = r0.status;
            if (r6 == null) goto L32;
            int r62 = r6.expires;
        L34:
            if (r62 == this.lastStatus) goto L36;
            r42 = true;
            goto L36
        L32:
            r62 = 0;
        L36:
            if (r42 == false) goto L38;
        L50:
            String r112 = null;
        L49:
            r5 = r42;
        L51:
            if (r5 == true) goto L54;
            return;
        L54:
            TLRPC.User r02 = this.currentUser;
            if (r02 == null) goto L60;
            this.avatarDrawable.setInfo(this.currentAccount, r02);
            TLRPC.UserStatus r03 = this.currentUser.status;
            if (r03 == null) goto L59;
            this.lastStatus = r03.expires;
        L64:
            CharSequence r04 = this.currentName;
            if (r04 == null) goto L67;
            this.lastName = null;
            this.nameTextView.setText(r04);
        L74:
            setStatus(this.currentStatus);
            this.lastAvatar = r22;
            TLRPC.User r113 = this.currentUser;
            BackupImageView r05 = this.avatarImageView;
            if (r113 == null) goto L78;
            r05.setForUserOrChat(r113, this.avatarDrawable);
            return;
        L78:
            r05.setImageDrawable(this.avatarDrawable);
            return;
        L67:
            TLRPC.User r06 = this.currentUser;
            if (r06 == null) goto L72;
            if (r112 != null) goto L71;
            r112 = UserObject.getUserName(r06);
        L71:
            this.lastName = r112;
        L73:
            this.nameTextView.setText(this.lastName);
            goto L74
        L72:
            this.lastName = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L73
        L59:
            this.lastStatus = 0;
            goto L64
        L60:
            CharSequence r07 = this.currentName;
            AvatarDrawable r3 = this.avatarDrawable;
            if (r07 == null) goto L63;
            r3.setInfo(this.currentId, r07.toString(), null);
            goto L64
        L63:
            r3.setInfo(this.currentId, "#", null);
            goto L64
        L38:
            if (this.currentName != null) goto L50;
            if (this.lastName == null) goto L50;
            if ((r11 & MessagesController.UPDATE_MASK_NAME) == 0) goto L50;
            if (r0 == null) goto L45;
            r112 = UserObject.getUserName(r0);
        L47:
            if (r112.equals(this.lastName) == true) goto L49;
        L45:
            r112 = null;
        L15:
            if (r4 != null) goto L17;
            if (r22 != null) goto L23;
        L17:
            if (r4 == null) goto L24;
            if (r22 == null) goto L24;
            if (r4.volume_id != r22.volume_id) goto L23;
            if (r4.local_id != r22.local_id) goto L23;
        L24:
            r42 = false;
            goto L25
        L53:
            r112 = null;
        L7:
            r22 = null;
            goto L9
        }
    }

    public static /* synthetic */ void $r8$lambda$Q67E6eKfltpFtKyHElW5CIFJFIc(ChatAttachAlertContactsLayout r0, TLRPC.User r1, boolean r2, int r3, long r4, boolean r6, long r7) {
        r0.lambda$new$0(r1, r2, r3, r4, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$ZAHwGYWj8soE71mlJtLJESyQ1NA(ChatAttachAlertContactsLayout r0, ArrayList r1, boolean r2, int r3, long r4, boolean r6, Long r7) {
        r0.lambda$sendSelectedItems$3(r1, r2, r3, r4, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$cpGfq2qq429b6393Ma0VoQ1abtI(ChatAttachAlertContactsLayout r0) {
        r0.lambda$getThemeDescriptions$4();
    }

    public static /* synthetic */ void $r8$lambda$pFzCUYFUsGLRvpJXguNsgnN1WZc(ChatAttachAlertContactsLayout r0, Theme.ResourcesProvider r1, View r2, int r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$qJVZYMiu4N_jPBtELlBVUSwe_50(ChatAttachAlertContactsLayout r0, View r1, int r2) {
        return r0.lambda$new$2(r1, r2);
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyTextProgressView m9565$$Nest$fgetemptyView(ChatAttachAlertContactsLayout r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FillLastLinearLayoutManager m9566$$Nest$fgetlayoutManager(ChatAttachAlertContactsLayout r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ShareAdapter m9567$$Nest$fgetlistAdapter(ChatAttachAlertContactsLayout r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m9568$$Nest$fgetlistView(ChatAttachAlertContactsLayout r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ShareSearchAdapter m9569$$Nest$fgetsearchAdapter(ChatAttachAlertContactsLayout r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetselectedContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m9570$$Nest$fgetselectedContacts(ChatAttachAlertContactsLayout r0) {
        return r0.selectedContacts;
    }

    /* renamed from: -$$Nest$mgetCurrentTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9571$$Nest$mgetCurrentTop(ChatAttachAlertContactsLayout r0) {
        return r0.getCurrentTop();
    }

    /* renamed from: -$$Nest$mupdateEmptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9572$$Nest$mupdateEmptyView(ChatAttachAlertContactsLayout r0) {
        r0.updateEmptyView();
    }

    /* renamed from: -$$Nest$mupdateEmptyViewPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9573$$Nest$mupdateEmptyViewPosition(ChatAttachAlertContactsLayout r0) {
        r0.updateEmptyViewPosition();
    }

    public ChatAttachAlertContactsLayout(ChatAttachAlert r19, Context r20, Theme.ResourcesProvider r21) {
        super(r19, r20, r21);
        this.animatorFadeVisible = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.selectedContacts = new HashMap();
        this.selectedContactsOrder = new ArrayList();
        this.sendPressed = false;
        this.searchAdapter = new ShareSearchAdapter(this, r20);
        ChatAttachAlert.SearchFadeView r0 = new ChatAttachAlert.SearchFadeView(r20, Theme.key_windowBackgroundWhite, r21);
        this.fadeView = r0;
        r0.setVisibility(4);
        FrameLayout r10 = new FrameLayout(r20);
        this.frameLayout = r10;
        ChatAttachAlert.AttachSearchField r2 = new ChatAttachAlert.AttachSearchField(r20, this.parentAlert, r21);
        this.searchField = r2;
        r2.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        r2.editText.setHint(LocaleController.getString(R.string.SearchFriends));
        r2.editText.addTextChangedListener(new AnonymousClass1(this));
        r10.addView(r0, LayoutHelper.createFrameMatchParent());
        FrameLayout.LayoutParams r02 = LayoutHelper.createFrame(-1, 48.0f, 51, 7.0f, 8.0f, 7.0f, 4.0f);
        ((ViewGroup.MarginLayoutParams) r02).topMargin += AndroidUtilities.statusBarHeight;
        r10.addView(r2, r02);
        EmptyTextProgressView r03 = new EmptyTextProgressView(r20, null, r21);
        this.emptyView = r03;
        r03.showTextView();
        r03.setText(LocaleController.getString(R.string.NoContacts));
        addView(r03, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 52.0f, 0.0f, 0.0f));
        AnonymousClass2 r6 = new AnonymousClass2(this, r20, r21);
        this.listView = r6;
        this.iBlur3Capture = r6;
        this.iBlur3CaptureView = r6;
        this.occupyStatusBar = true;
        this.occupyNavigationBar = true;
        r6.setSections();
        r6.setClipToPadding(false);
        AnonymousClass3 r04 = new AnonymousClass3(this, getContext(), 1, false, AndroidUtilities.dp(9.0f), r6);
        this.layoutManager = r04;
        r6.setLayoutManager(r04);
        r04.setBind(false);
        r6.setHorizontalScrollBarEnabled(false);
        r6.setVerticalScrollBarEnabled(false);
        r6.setClipToPadding(false);
        addView(r6, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        ShareAdapter r05 = new ShareAdapter(this, r20);
        this.listAdapter = r05;
        r6.setAdapter(r05);
        r6.setGlowColor(getThemedColor(Theme.key_dialogScrollGlow));
        r6.setOnItemClickListener(new ChatAttachAlertContactsLayout$$ExternalSyntheticLambda0(this, r21));
        r6.setOnScrollListener(new AnonymousClass4(this));
        r6.setOnItemLongClickListener(new ChatAttachAlertContactsLayout$$ExternalSyntheticLambda1(this));
        FrameLayout.LayoutParams r06 = LayoutHelper.createFrame(-1, 60, 51);
        ((ViewGroup.MarginLayoutParams) r06).height += AndroidUtilities.statusBarHeight;
        addView(r10, r06);
        NotificationCenter.getInstance(this.parentAlert.currentAccount).addObserver(this, NotificationCenter.contactsDidLoad);
        updateEmptyView();
    }

    private int getCurrentTop() {
        if (this.listView.getChildCount() == 0) goto L13;
        int r1 = 0;
        View r0 = this.listView.getChildAt(0);
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r0);
        if (r2 == null) goto L15;
        int r3 = this.listView.getPaddingTop();
        if (r2.getAdapterPosition() != 0) goto L12;
        if (r0.getTop() < 0) goto L12;
        r1 = r0.getTop();
    L12:
        return r3 - r1;
    L15:
        return -1000;
    L13:
        return -1000;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$4() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L10;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof UserCell) == false) goto L9;
        ((UserCell) r3).update(0);
    L9:
        r2 = r2 + 1;
    L10:
        FragmentSearchField r5 = this.searchField;
        if (r5 == null) goto L17;
        r5.updateColors();
        return;
    }

    private /* synthetic */ void lambda$new$0(TLRPC.User r3, boolean r4, int r5, long r6, boolean r8, long r9) {
        this.parentAlert.dismiss(true);
        this.delegate.didSelectContact(r3, r4, r5, r6, r8, r9);
    }

    private /* synthetic */ void lambda$new$1(Theme.ResourcesProvider r17, View r18, int r19) {
        RecyclerView.Adapter r2 = this.listView.getAdapter();
        ShareSearchAdapter r3 = this.searchAdapter;
        if (r2 != r3) goto L5;
        Object r1 = r3.getItem(r19);
    L10:
        if (r1 != null) goto L12;
        return;
    L12:
        if (this.selectedContacts.isEmpty() == true) goto L16;
        addOrRemoveSelectedContact((UserCell) r18, r1);
        return;
    L16:
        if ((r1 instanceof ContactsController.Contact) == false) goto L22;
        ContactsController.Contact r12 = (ContactsController.Contact) r1;
        TLRPC.User r22 = r12.user;
        if (r22 == null) goto L21;
        String r32 = r22.first_name;
        String r23 = r22.last_name;
    L20:
        ContactsController.Contact r9 = r12;
        String r14 = r23;
        String r13 = r32;
    L23:
        PhonebookShareAlert r7 = new PhonebookShareAlert(this.parentAlert.baseFragment, r9, null, null, null, r13, r14, r17);
        r7.setDelegate(new ChatAttachAlertContactsLayout$$ExternalSyntheticLambda3(this));
        r7.show();
        return;
    L21:
        r32 = r12.first_name;
        r23 = r12.last_name;
        goto L20
    L22:
        TLRPC.User r15 = (TLRPC.User) r1;
        ContactsController.Contact r24 = new ContactsController.Contact();
        String r33 = r15.first_name;
        r24.first_name = r33;
        String r4 = r15.last_name;
        r24.last_name = r4;
        r24.phones.add(r15.phone);
        r24.user = r15;
        r9 = r24;
        r13 = r33;
        r14 = r4;
        goto L23
    L5:
        int r25 = this.listAdapter.getSectionForPosition(r19);
        int r16 = this.listAdapter.getPositionInSectionForPosition(r19);
        if (r16 < 0) goto L25;
        if (r25 < 0) goto L27;
        r1 = this.listAdapter.getItem(r25, r16);
        goto L10
    L27:
        return;
    }

    private /* synthetic */ boolean lambda$new$2(View r3, int r4) {
        RecyclerView.Adapter r0 = this.listView.getAdapter();
        ShareSearchAdapter r1 = this.searchAdapter;
        if (r0 != r1) goto L5;
        Object r42 = r1.getItem(r4);
    L6:
        if (r42 == null) goto L9;
        addOrRemoveSelectedContact((UserCell) r3, r42);
        return true;
    L9:
        return false;
    L5:
        r42 = this.listAdapter.getItem(r4);
        goto L6
    }

    private /* synthetic */ void lambda$sendSelectedItems$3(ArrayList r11, boolean r12, int r13, long r14, boolean r16, Long r17) {
        this.delegate.didSelectContacts(r11, this.parentAlert.getCommentView().getText().toString(), r12, r13, r14, r16, r17.longValue());
        this.parentAlert.dismiss();
    }

    private TLRPC.User prepareContact(Object r14) {
        if ((r14 instanceof ContactsController.Contact) == false) goto L8;
        ContactsController.Contact r142 = (ContactsController.Contact) r14;
        TLRPC.User r0 = r142.user;
        if (r0 == null) goto L7;
        String r1 = r0.first_name;
        String r02 = r0.last_name;
    L9:
        String r2 = ContactsController.formatName(r1, r02);
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList();
        String r6 = r142.key;
        if (r6 == null) goto L12;
        ArrayList<TLRPC.User> r13 = AndroidUtilities.loadVCardFromStream(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, r6), this.parentAlert.currentAccount, true, r3, r2);
    L13:
        TLRPC.User r143 = r142.user;
        if (r13 == null) goto L35;
        int r22 = 0;
    L17:
        if (r22 >= r3.size()) goto L31;
        AndroidUtilities.VcardItem r62 = (AndroidUtilities.VcardItem) r3.get(r22);
        if (r62.type != 0) goto L28;
        int r10 = 0;
    L22:
        if (r10 >= r4.size()) goto L27;
        if (((AndroidUtilities.VcardItem) r4.get(r10)).getValue(false).equals(r62.getValue(false)) == true) goto L25;
        r10 = r10 + 1;
        goto L22
    L25:
        r62.checked = false;
    L29:
        r22 = r22 + 1;
        goto L17
    L27:
        r4.add(r62);
        goto L29
    L28:
        r5.add(r62);
        goto L29
    L31:
        if (r13.isEmpty() == true) goto L35;
        TLRPC.User r132 = r13.get(0);
        ArrayList<TLRPC.RestrictionReason> r23 = r132.restriction_reason;
        if (TextUtils.isEmpty(r1) == false) goto L36;
        r1 = r132.first_name;
        r02 = r132.last_name;
    L36:
        TLRPC.TL_userContact_old2 r133 = new TLRPC.TL_userContact_old2();
        if (r143 == null) goto L41;
        r133.id = r143.id;
        r133.access_hash = r143.access_hash;
        r133.photo = r143.photo;
        r133.status = r143.status;
        r133.first_name = r143.first_name;
        r133.last_name = r143.last_name;
        r133.phone = r143.phone;
        if (r23 == null) goto L43;
        r133.restriction_reason = r23;
    L43:
        if (r133.restriction_reason.isEmpty() == true) goto L45;
        StringBuilder r144 = new StringBuilder(r133.restriction_reason.get(0).text);
    L46:
        int r03 = r144.lastIndexOf("END:VCARD");
        if (r03 < 0) goto L72;
        r133.phone = null;
        int r12 = r4.size() - 1;
    L50:
        if (r12 < 0) goto L62;
        AndroidUtilities.VcardItem r32 = (AndroidUtilities.VcardItem) r4.get(r12);
        if (r32.checked == false) goto L61;
        if (r133.phone != null) goto L57;
        r133.phone = r32.getValue(false);
    L57:
        int r63 = 0;
    L59:
        if (r63 >= r32.vcardData.size()) goto L61;
        r144.insert(r03, r32.vcardData.get(r63) + "\n");
        r63 = r63 + 1;
    L61:
        r12 = r12 - 1;
        goto L50
    L62:
        int r15 = r5.size() - 1;
    L63:
        if (r15 < 0) goto L71;
        AndroidUtilities.VcardItem r33 = (AndroidUtilities.VcardItem) r5.get(r15);
        if (r33.checked == false) goto L70;
        int r42 = r33.vcardData.size() - 1;
    L68:
        if (r42 < 0) goto L70;
        r144.insert(r03, r33.vcardData.get(r42) + "\n");
        r42 = r42 + (-1);
    L70:
        r15 = r15 - 1;
        goto L63
    L71:
        r133.restriction_reason.clear();
        TLRPC.RestrictionReason r04 = new TLRPC.RestrictionReason();
        r04.text = r144.toString();
        r04.reason = _UrlKt.FRAGMENT_ENCODE_SET;
        r04.platform = _UrlKt.FRAGMENT_ENCODE_SET;
        r133.restriction_reason.add(r04);
    L72:
        return r133;
    L45:
        r144 = new StringBuilder(String.format(Locale.US, "BEGIN:VCARD\nVERSION:3.0\nFN:%1$s\nEND:VCARD", new Object[]{ContactsController.formatName(r133.first_name, r133.last_name)}));
        goto L46
    L41:
        r133.first_name = r1;
        r133.last_name = r02;
    L35:
        r23 = null;
        goto L36
    L12:
        AndroidUtilities.VcardItem r134 = new AndroidUtilities.VcardItem();
        r134.type = 0;
        ArrayList<String> r24 = r134.vcardData;
        String r64 = "TEL;MOBILE:+" + r142.user.phone;
        r134.fullData = r64;
        r24.add(r64);
        r4.add(r134);
        r13 = null;
        goto L13
    L7:
        r1 = r142.first_name;
        r02 = r142.last_name;
        goto L9
    L8:
        TLRPC.User r145 = (TLRPC.User) r14;
        ContactsController.Contact r05 = new ContactsController.Contact();
        r1 = r145.first_name;
        r05.first_name = r1;
        String r25 = r145.last_name;
        r05.last_name = r25;
        r05.phones.add(r145.phone);
        r05.user = r145;
        r142 = r05;
        r02 = r25;
        goto L9
    }

    private void showErrorBox(String r3) {
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.AppName)).setMessage(r3).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
    }

    private void updateEmptyView() {
        int r2 = 0;
        if (this.listView.getAdapter().getItemCount() != 2) goto L5;
        boolean r0 = true;
    L6:
        EmptyTextProgressView r1 = this.emptyView;
        if (r0 == true) goto L10;
        r2 = 8;
    L10:
        r1.setVisibility(r2);
        updateEmptyViewPosition();
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void updateEmptyViewPosition() {
        if (this.emptyView.getVisibility() == 0) goto L6;
        return;
    L6:
        if (this.listView.getChildAt(0) != null) goto L8;
        return;
    L8:
        this.emptyView.setTranslationY(((r1.getMeasuredHeight() - getMeasuredHeight()) + r0.getTop()) / 2);
    }

    public void addOrRemoveSelectedContact(UserCell r6, Object r7) {
        boolean r1 = false;
        if (this.selectedContacts.isEmpty() == true) goto L5;
    L8:
        ListItemID r0 = ListItemID.of(r7);
        boolean r2 = this.selectedContacts.containsKey(r0);
        HashMap<ListItemID, Object> r3 = this.selectedContacts;
        int r4 = 1;
        if (r2 == false) goto L11;
        r3.remove(r0);
        this.selectedContactsOrder.remove(r0);
    L12:
        r6.setChecked(r1, true);
        ChatAttachAlert r5 = this.parentAlert;
        if (r1 == true) goto L16;
        r4 = 2;
    L16:
        r5.updateCountButton(r4);
        return;
    L11:
        r3.put(r0, r7);
        this.selectedContactsOrder.add(r0);
        r1 = true;
        goto L12
    L5:
        if (this.multipleSelectionAllowed == true) goto L8;
        showErrorBox(LocaleController.formatString("AttachContactsSlowMode", R.string.AttachContactsSlowMode, new Object[0]));
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.contactsDidLoad) goto L8;
        ShareAdapter r0 = this.listAdapter;
        if (r0 == null) goto L9;
        r0.notifyDataSetChanged();
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        if (this.listView.getChildCount() > 0) goto L6;
        return Integer.MAX_VALUE;
    L6:
        View r0 = this.listView.getChildAt(0);
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r0);
        int r02 = (r0.getTop() - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(8.0f);
        if (r02 <= 0) goto L12;
        if (r2 == null) goto L12;
        if (r2.getAdapterPosition() != 0) goto L12;
        int r3 = r02;
    L14:
        if (r02 < 0) goto L19;
        if (r2 == null) goto L19;
        if (r2.getAdapterPosition() != 0) goto L19;
        this.animatorFadeVisible.setValue(false, true);
    L20:
        this.frameLayout.setTranslationY(r02);
        return r02 + AndroidUtilities.dp(12.0f);
    L19:
        this.animatorFadeVisible.setValue(true, true);
        r02 = r3;
    L12:
        r3 = 0;
        goto L14
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(4.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return this.listView.getPaddingTop();
    }

    public ArrayList<TLRPC.User> getSelected() {
        ArrayList<TLRPC.User> r0 = new ArrayList(this.selectedContacts.size());
        ArrayList<ListItemID> r1 = this.selectedContactsOrder;
        int r2 = r1.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L5;
        ListItemID r4 = r1.get(r3);
        r3 = r3 + 1;
        HashMap<ListItemID, Object> r5 = this.selectedContacts;
        r0.add(prepareContact(r5.get(r4)));
        goto L3
    L5:
        return r0;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getSelectedItemsCount() {
        return this.selectedContacts.size();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ChatAttachAlertContactsLayout$$ExternalSyntheticLambda2 r6 = new ChatAttachAlertContactsLayout$$ExternalSyntheticLambda2(this);
        ArrayList<ThemeDescription> r10 = new ArrayList();
        r10.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        r10.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle));
        r10.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_dialogScrollGlow));
        r10.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r10.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        int r24 = Theme.key_dialogTextGray2;
        r10.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"nameTextView"}, null, null, null, r24));
        r10.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"statusTextView"}, null, null, r6, r24));
        r10.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundRed));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundOrange));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundViolet));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundGreen));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundCyan));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundBlue));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundPink));
        return r10;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onDestroy() {
        NotificationCenter.getInstance(this.parentAlert.currentAccount).removeObserver(this, NotificationCenter.contactsDidLoad);
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L9;
        this.fadeView.setAlpha(r2);
        View r0 = this.fadeView;
        if (r2 <= 0.0f) goto L6;
        int r12 = 0;
    L7:
        r0.setVisibility(r12);
        return;
    L6:
        r12 = 4;
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateEmptyViewPosition();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r3, int r4) {
        if (this.parentAlert.sizeNotifierFrameLayout.measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L6;
        int r32 = AndroidUtilities.dp(8.0f);
        this.parentAlert.setAllowNestedScroll(false);
    L12:
        this.listView.setPaddingWithoutRequestLayout(0, r32 + AndroidUtilities.statusBarHeight, 0, this.listPaddingBottom);
        return;
    L6:
        if (AndroidUtilities.isTablet() == true) goto L10;
        Point r33 = AndroidUtilities.displaySize;
        if (r33.x <= r33.y) goto L10;
        r32 = (int) (r4 / 3.5f);
    L11:
        this.parentAlert.setAllowNestedScroll(true);
    L10:
        r32 = (r4 / 5) * 2;
        goto L11
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r1) {
        this.layoutManager.scrollToPositionWithOffset(0, 0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void scrollToTop() {
        this.listView.smoothScrollToPosition(0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean sendSelectedItems(boolean r12, int r13, int r14, long r15, boolean r17) {
        int r0 = 0;
        if (this.selectedContacts.size() != 0) goto L7;
        if (this.delegate != null) goto L7;
    L8:
        return false;
    L7:
        if (this.sendPressed == true) goto L8;
        this.sendPressed = true;
        ArrayList r3 = new ArrayList(this.selectedContacts.size());
        ArrayList<ListItemID> r142 = this.selectedContactsOrder;
        int r1 = r142.size();
    L10:
        if (r0 >= r1) goto L12;
        ListItemID r2 = r142.get(r0);
        r0 = r0 + 1;
        HashMap<ListItemID, Object> r4 = this.selectedContacts;
        r3.add(prepareContact(r4.get(r2)));
        goto L10
    L12:
        ChatAttachAlert r143 = this.parentAlert;
        return AlertsCreator.ensurePaidMessageConfirmation(r143.currentAccount, r143.getDialogId(), r3.size() + this.parentAlert.getAdditionalMessagesCount(), new ChatAttachAlertContactsLayout$$ExternalSyntheticLambda4(this, r3, r12, r13, r15, r17));
    }

    public void setDelegate(PhonebookShareAlertDelegate r1) {
        this.delegate = r1;
    }

    public void setMultipleSelectionAllowed(boolean r1) {
        this.multipleSelectionAllowed = r1;
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        this.parentAlert.getSheetContainer().invalidate();
    }

    public void setupBlurredSearchField(BlurredBackgroundDrawableViewFactory r2) {
        FragmentSearchField r0 = this.searchField;
        if (r0 == null) goto L6;
        r0.setupBlurredBackground(r2.create(r0, BlurredBackgroundProviderImpl.attachMenuSearch(this.resourcesProvider)));
        return;
    }
}
