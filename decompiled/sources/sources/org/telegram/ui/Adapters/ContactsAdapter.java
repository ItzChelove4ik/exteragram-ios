package org.telegram.ui.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DividerCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.InviteUserCell;
import org.telegram.ui.Cells.LetterSectionCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.ContactsEmptyView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public abstract class ContactsAdapter extends RecyclerListView.SectionsAdapter {
    private final int currentAccount;
    private boolean disableSections;
    BaseFragment fragment;
    private boolean hasPhonebook;
    private final LongSparseArray<TLRPC.User> ignoreUsers;
    public boolean includeSearch;
    private final boolean isAdmin;
    private final boolean isChannel;
    private boolean isEmpty;
    public boolean isEmptyWithMainTabs;
    private final Context mContext;
    private final boolean needPhonebook;
    private ArrayList<TLRPC.TL_contact> onlineContacts;
    private final int onlyUsers;
    private final LongSparseArray<TLRPC.User> selectedContacts;
    private int sortType;

    /* renamed from: org.telegram.ui.Adapters.ContactsAdapter$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ ContactsAdapter this$0;

        public AnonymousClass1(ContactsAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(52.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Adapters.ContactsAdapter$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ ContactsAdapter this$0;
        final /* synthetic */ ViewGroup val$parent;

        public AnonymousClass2(ContactsAdapter r1, Context r2, ViewGroup r3) {
            this.this$0 = r1;
            this.val$parent = r3;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            ContactsAdapter r0 = this.this$0;
            if (r0.isEmptyWithMainTabs == true) goto L5;
        L8:
            int r62 = View.MeasureSpec.getSize(r6);
            if (r62 != 0) goto L11;
            r62 = this.val$parent.getMeasuredHeight();
        L11:
            if (r62 != 0) goto L13;
            r62 = (AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.statusBarHeight;
        L13:
            int r02 = AndroidUtilities.dp(50.0f);
            int r2 = 0;
            if (ContactsAdapter.m5855$$Nest$fgetonlyUsers(this.this$0) == 0) goto L16;
            int r1 = 0;
        L18:
            if (ContactsAdapter.m5853$$Nest$fgetisAdmin(this.this$0) == false) goto L20;
        L22:
            int r63 = (r62 - this.val$parent.getPaddingTop()) - this.val$parent.getPaddingBottom();
            if (r1 >= r63) goto L25;
            r2 = r63 - r1;
        L25:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30));
            return;
        L20:
            if (ContactsAdapter.m5854$$Nest$fgetneedPhonebook(this.this$0) == true) goto L22;
            r1 = r1 + r02;
            goto L22
        L16:
            r1 = AndroidUtilities.dp(30.0f) + r02;
            goto L18
        L5:
            if (ContactsAdapter.m5852$$Nest$fgethasPhonebook(r0) == false) goto L8;
            super.onMeasure(r5, r6);
        }
    }

    public static /* synthetic */ int $r8$lambda$ELiMIR3niEAcM_m7UV_uqfYJfuE(MessagesController r2, int r3, TLRPC.TL_contact r4, TLRPC.TL_contact r5) {
        TLRPC.User r52 = r2.getUser(Long.valueOf(r5.user_id));
        TLRPC.User r22 = r2.getUser(Long.valueOf(r4.user_id));
        if (r52 != null) goto L5;
    L10:
        int r53 = 0;
    L11:
        if (r22 != null) goto L13;
    L18:
        int r32 = 0;
    L19:
        if (r53 <= 0) goto L25;
        if (r32 <= 0) goto L25;
        if (r53 > r32) goto L48;
        if (r53 < r32) goto L45;
    L43:
        return 0;
    L45:
        return -1;
    L48:
        return 1;
    L25:
        if (r53 >= 0) goto L31;
        if (r32 >= 0) goto L31;
        if (r53 > r32) goto L49;
        if (r53 >= r32) goto L43;
        return -1;
    L49:
        return 1;
    L31:
        if (r53 >= 0) goto L33;
        if (r32 <= 0) goto L33;
        return -1;
    L33:
        if (r53 != 0) goto L37;
        if (r32 == 0) goto L37;
        return -1;
    L37:
        if (r32 >= 0) goto L39;
        if (r53 <= 0) goto L39;
        return 1;
    L39:
        if (r32 != 0) goto L43;
        if (r53 == 0) goto L43;
        return 1;
    L13:
        if (r22.self == false) goto L15;
        r32 = r3 + 50000;
        goto L19
    L15:
        TLRPC.UserStatus r23 = r22.status;
        if (r23 == null) goto L18;
        r32 = r23.expires;
        goto L19
    L5:
        if (r52.self == false) goto L7;
        r53 = r3 + 50000;
        goto L11
    L7:
        TLRPC.UserStatus r54 = r52.status;
        if (r54 == null) goto L10;
        r53 = r54.expires;
        goto L11
    }

    /* renamed from: -$$Nest$fgethasPhonebook, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5852$$Nest$fgethasPhonebook(ContactsAdapter r0) {
        return r0.hasPhonebook;
    }

    /* renamed from: -$$Nest$fgetisAdmin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5853$$Nest$fgetisAdmin(ContactsAdapter r0) {
        return r0.isAdmin;
    }

    /* renamed from: -$$Nest$fgetneedPhonebook, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5854$$Nest$fgetneedPhonebook(ContactsAdapter r0) {
        return r0.needPhonebook;
    }

    /* renamed from: -$$Nest$fgetonlyUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5855$$Nest$fgetonlyUsers(ContactsAdapter r0) {
        return r0.onlyUsers;
    }

    public ContactsAdapter(Context r2, BaseFragment r3, int r4, boolean r5, LongSparseArray<TLRPC.User> r6, LongSparseArray<TLRPC.User> r7, int r8) {
        this.currentAccount = UserConfig.selectedAccount;
        this.mContext = r2;
        this.onlyUsers = r4;
        this.needPhonebook = r5;
        this.ignoreUsers = r6;
        this.selectedContacts = r7;
        boolean r22 = false;
        if (r8 == 0) goto L5;
        boolean r52 = true;
    L6:
        this.isAdmin = r52;
        if (r8 != 2) goto L9;
        r22 = true;
    L9:
        this.isChannel = r22;
        this.fragment = r3;
        return;
    L5:
        r52 = false;
        goto L6
    }

    private int getCountForSectionInternal(int r7) {
        if (this.isEmptyWithMainTabs == false) goto L11;
        if (r7 == 0) goto L6;
        if (r7 == 1) goto L9;
        return 0;
    L9:
        return ContactsController.getInstance(this.currentAccount).phoneBookContacts.size() + 2;
    L6:
        return (this.includeSearch ? 1 : 0) + 1;
    L11:
        int r0 = this.onlyUsers;
        int r4 = this.currentAccount;
        if (r0 != 2) goto L14;
        HashMap<String, ArrayList<TLRPC.TL_contact>> r02 = ContactsController.getInstance(r4).usersMutualSectionsDict;
    L15:
        int r42 = this.onlyUsers;
        int r5 = this.currentAccount;
        if (r42 != 2) goto L18;
        ArrayList<String> r43 = ContactsController.getInstance(r5).sortedUsersMutualSectionsArray;
    L20:
        if (this.onlyUsers != 0) goto L22;
    L36:
        boolean r52 = this.isEmpty;
        if (r7 != 0) goto L51;
        if (r52 == false) goto L42;
        return (this.includeSearch ? 1 : 0) + 2;
    L42:
        if (this.isAdmin == true) goto L44;
        boolean r72 = this.needPhonebook;
        boolean r6 = this.includeSearch;
        if (r72 == false) goto L50;
        return (r6 ? 1 : 0) + 4;
    L50:
        return (r6 ? 1 : 0) + 4;
    L44:
        return (this.includeSearch ? 1 : 0) + 3;
    L51:
        if (r52 == false) goto L54;
        return 1;
    L54:
        if (this.sortType != 2) goto L61;
        if (r7 == 1) goto L57;
    L66:
        if (this.needPhonebook == true) goto L68;
        return 0;
    L68:
        return ContactsController.getInstance(this.currentAccount).phoneBookContacts.size();
    L57:
        if (this.onlineContacts.isEmpty() == false) goto L60;
        return 0;
    L60:
        return this.onlineContacts.size();
    L61:
        int r73 = r7 - 1;
        if (r73 >= r43.size()) goto L66;
        int r62 = r02.get(r43.get(r73)).size();
        r43.size();
        return r62;
    L22:
        if (this.isAdmin == true) goto L36;
        if (this.isEmpty == false) goto L27;
        return 1;
    L27:
        if (r7 >= r43.size()) goto L66;
        int r03 = r02.get(r43.get(r7)).size();
        if (r7 != (r43.size() - 1)) goto L35;
        if (this.needPhonebook == true) goto L35;
        return r03;
    L35:
        return r03 + 1;
    L18:
        r43 = ContactsController.getInstance(r5).sortedUsersSectionsArray;
        goto L20
    L14:
        r02 = ContactsController.getInstance(r4).usersSectionsDict;
        goto L15
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
    public int getCountForSection(int r1) {
        return getCountForSectionInternal(r1);
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
    public int getHash(int r1, int r2) {
        Object r0 = getItem(r1, r2);
        return Objects.hash(new Object[]{Integer.valueOf(r1 * (-49612)), r0});
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
    public Object getItem(int r7, int r8) {
        if (this.isEmptyWithMainTabs == false) goto L11;
        if (r7 != 1) goto L11;
        if (r8 <= 1) goto L11;
        int r0 = r8 - 2;
        if (r0 >= ContactsController.getInstance(this.currentAccount).phoneBookContacts.size()) goto L11;
        return ContactsController.getInstance(this.currentAccount).phoneBookContacts.get(r0);
    L11:
        if (getItemViewType(r7, r8) != 2) goto L14;
        return "Header";
    L14:
        int r02 = this.onlyUsers;
        int r3 = this.currentAccount;
        if (r02 != 2) goto L17;
        HashMap<String, ArrayList<TLRPC.TL_contact>> r03 = ContactsController.getInstance(r3).usersMutualSectionsDict;
    L18:
        int r32 = this.onlyUsers;
        int r4 = this.currentAccount;
        if (r32 != 2) goto L21;
        ArrayList<String> r33 = ContactsController.getInstance(r4).sortedUsersMutualSectionsArray;
    L23:
        if (this.onlyUsers != 0) goto L25;
    L33:
        if (r7 != 0) goto L36;
        return null;
    L36:
        if (this.sortType != 2) goto L43;
        if (r7 != 1) goto L51;
        if (r8 < this.onlineContacts.size()) goto L41;
        return null;
    L41:
        return MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.onlineContacts.get(r8).user_id));
    L51:
        if (this.needPhonebook == false) goto L57;
        if (r8 < 0) goto L57;
        if (r8 >= ContactsController.getInstance(this.currentAccount).phoneBookContacts.size()) goto L57;
        return ContactsController.getInstance(this.currentAccount).phoneBookContacts.get(r8);
    L57:
        return null;
    L43:
        int r72 = r7 - 1;
        if (r72 >= r33.size()) goto L51;
        ArrayList<TLRPC.TL_contact> r73 = r03.get(r33.get(r72));
        if (r8 < r73.size()) goto L48;
        return null;
    L48:
        return MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r73.get(r8).user_id));
    L25:
        if (this.isAdmin == true) goto L33;
        if (r7 >= r33.size()) goto L32;
        ArrayList<TLRPC.TL_contact> r74 = r03.get(r33.get(r7));
        if (r8 >= r74.size()) goto L32;
        return MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r74.get(r8).user_id));
    L32:
        return null;
    L21:
        r33 = ContactsController.getInstance(r4).sortedUsersSectionsArray;
        goto L23
    L17:
        r03 = ContactsController.getInstance(r3).usersSectionsDict;
        goto L18
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
    public int getItemViewType(int r11, int r12) {
        if (r11 != 0) goto L10;
        if (this.includeSearch == false) goto L10;
        if (r12 != 0) goto L8;
        return 9;
    L8:
        r12 = r12 - 1;
    L10:
        if (this.isEmptyWithMainTabs == false) goto L20;
        if (r11 != 0) goto L13;
        return 4;
    L13:
        if (r11 != 1) goto L18;
        if (r12 != 0) goto L16;
        return 5;
    L16:
        if (r12 != 1) goto L96;
        return 7;
    L96:
        return 8;
    L18:
        return 8;
    L20:
        int r0 = this.onlyUsers;
        int r5 = this.currentAccount;
        if (r0 != 2) goto L23;
        HashMap<String, ArrayList<TLRPC.TL_contact>> r02 = ContactsController.getInstance(r5).usersMutualSectionsDict;
    L24:
        int r52 = this.onlyUsers;
        int r7 = this.currentAccount;
        if (r52 != 2) goto L27;
        ArrayList<String> r53 = ContactsController.getInstance(r7).sortedUsersMutualSectionsArray;
    L29:
        if (this.onlyUsers != 0) goto L31;
    L39:
        if (r11 != 0) goto L80;
        if (this.isAdmin == false) goto L52;
        if (r12 != 1) goto L44;
        return 5;
    L44:
        if (r12 != 2) goto L95;
        int r10 = this.sortType;
        if (r10 == 1) goto L50;
        if (r10 == 2) goto L50;
        return 2;
    L50:
        return 7;
    L95:
        return 1;
    L52:
        if (this.needPhonebook == false) goto L67;
        if (r12 >= 2) goto L55;
        return 1;
    L55:
        if (r12 != 2) goto L57;
        return 5;
    L57:
        if (r12 != 3) goto L95;
        if (this.isEmpty == false) goto L61;
        return 5;
    L61:
        int r102 = this.sortType;
        if (r102 == 1) goto L66;
        if (r102 == 2) goto L66;
        return 2;
    L66:
        return 7;
    L67:
        if (r12 != 2) goto L69;
        return 5;
    L69:
        if (r12 != 3) goto L95;
        if (this.isEmpty == false) goto L73;
        return 5;
    L73:
        int r103 = this.sortType;
        if (r103 == 1) goto L78;
        if (r103 == 2) goto L78;
        return 2;
    L78:
        return 7;
    L80:
        if (this.isEmpty == false) goto L83;
        return 4;
    L83:
        if (this.sortType != 2) goto L89;
        if (r11 != 1) goto L95;
        if (r12 >= this.onlineContacts.size()) goto L88;
        return 0;
    L88:
        return 3;
    L89:
        int r112 = r11 - 1;
        if (r112 >= r53.size()) goto L95;
        if (r12 >= r02.get(r53.get(r112)).size()) goto L94;
        return 0;
    L94:
        return 3;
    L31:
        if (this.isAdmin == true) goto L39;
        if (this.isEmpty == false) goto L36;
        return 4;
    L36:
        if (r12 >= r02.get(r53.get(r11)).size()) goto L38;
        return 0;
    L38:
        return 3;
    L27:
        r53 = ContactsController.getInstance(r7).sortedUsersSectionsArray;
        goto L29
    L23:
        r02 = ContactsController.getInstance(r5).usersSectionsDict;
        goto L24
    }

    @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
    public String getLetter(int r5) {
        if (this.includeSearch == false) goto L8;
        if (r5 != 0) goto L6;
        return null;
    L6:
        r5 = r5 - 1;
    L8:
        if (this.sortType != 2) goto L10;
    L33:
        return null;
    L10:
        if (this.isEmpty == true) goto L33;
        int r0 = this.onlyUsers;
        int r3 = this.currentAccount;
        if (r0 != 2) goto L15;
        ArrayList<String> r02 = ContactsController.getInstance(r3).sortedUsersMutualSectionsArray;
    L16:
        int r52 = getSectionForPosition(r5);
        if (r52 != (-1)) goto L20;
        r52 = r02.size() - 1;
    L20:
        if (this.onlyUsers != 0) goto L22;
    L28:
        if (r52 <= 0) goto L33;
        if (r52 > r02.size()) goto L33;
        return r02.get(r52 - 1);
    L22:
        if (this.isAdmin == true) goto L28;
        if (r52 < 0) goto L33;
        if (r52 >= r02.size()) goto L33;
        return r02.get(r52);
    L15:
        r02 = ContactsController.getInstance(r3).sortedUsersSectionsArray;
        goto L16
    }

    @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
    public void getPositionForScrollProgress(RecyclerListView r1, float r2, int[] r3) {
        r3[0] = (int) (getItemCount() * r2);
        r3[1] = 0;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
    public int getSectionCount() {
        boolean r0 = false;
        this.isEmpty = false;
        if (this.sortType != 2) goto L6;
        this.isEmpty = this.onlineContacts.isEmpty();
    L5:
        int r1 = 1;
    L14:
        if (this.onlyUsers != 0) goto L17;
        r1 = r1 + 1;
    L17:
        if (this.isAdmin == false) goto L19;
        r1 = r1 + 1;
    L19:
        boolean r4 = ContactsController.getInstance(this.currentAccount).phoneBookContacts.isEmpty();
        this.hasPhonebook = !r4;
        if (this.isEmpty == true) goto L22;
    L28:
        this.isEmptyWithMainTabs = r0;
        if (r0 == false) goto L33;
        if (r4 == true) goto L32;
        return 2;
    L32:
        return 1;
    L33:
        return r1;
    L22:
        if (this.needPhonebook == false) goto L28;
        if (this.isAdmin == true) goto L28;
        if (this.onlyUsers != 0) goto L28;
        r0 = true;
        goto L28
    L6:
        int r12 = this.onlyUsers;
        int r42 = this.currentAccount;
        if (r12 != 2) goto L9;
        ArrayList<String> r13 = ContactsController.getInstance(r42).sortedUsersMutualSectionsArray;
    L10:
        r1 = r13.size();
        if (r1 != 0) goto L14;
        this.isEmpty = true;
        goto L5
    L9:
        r13 = ContactsController.getInstance(r42).sortedUsersSectionsArray;
        goto L10
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
    public View getSectionHeaderView(int r6, View r7) {
        int r0 = this.onlyUsers;
        int r1 = this.currentAccount;
        if (r0 != 2) goto L5;
        HashMap<String, ArrayList<TLRPC.TL_contact>> r02 = ContactsController.getInstance(r1).usersMutualSectionsDict;
    L6:
        int r03 = this.onlyUsers;
        int r12 = this.currentAccount;
        if (r03 != 2) goto L9;
        ArrayList<String> r04 = ContactsController.getInstance(r12).sortedUsersMutualSectionsArray;
    L10:
        if (r7 != null) goto L12;
        r7 = new LetterSectionCell(this.mContext);
    L12:
        LetterSectionCell r13 = (LetterSectionCell) r7;
        if (this.sortType != 2) goto L15;
    L38:
        r13.setLetter(_UrlKt.FRAGMENT_ENCODE_SET);
        return r7;
    L15:
        if (this.disableSections == true) goto L38;
        if (this.isEmpty == true) goto L38;
        if (this.onlyUsers != 0) goto L22;
    L29:
        if (r6 != 0) goto L32;
        r13.setLetter(_UrlKt.FRAGMENT_ENCODE_SET);
        return r7;
    L32:
        int r62 = r6 - 1;
        if (r62 >= r04.size()) goto L36;
        r13.setLetter(r04.get(r62));
        return r7;
    L36:
        r13.setLetter(_UrlKt.FRAGMENT_ENCODE_SET);
        return r7;
    L22:
        if (this.isAdmin == true) goto L29;
        if (r6 >= r04.size()) goto L27;
        r13.setLetter(r04.get(r6));
        return r7;
    L27:
        r13.setLetter(_UrlKt.FRAGMENT_ENCODE_SET);
        return r7;
    L9:
        r04 = ContactsController.getInstance(r12).sortedUsersSectionsArray;
        goto L10
    L5:
        HashMap<String, ArrayList<TLRPC.TL_contact>> r05 = ContactsController.getInstance(r1).usersSectionsDict;
        goto L6
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r6, int r7, int r8) {
        if (this.isEmptyWithMainTabs == false) goto L8;
        if (r7 != 1) goto L7;
        if (r8 <= 1) goto L7;
        return true;
    L7:
        return false;
    L8:
        int r62 = this.onlyUsers;
        int r2 = this.currentAccount;
        if (r62 != 2) goto L11;
        HashMap<String, ArrayList<TLRPC.TL_contact>> r63 = ContactsController.getInstance(r2).usersMutualSectionsDict;
    L12:
        int r22 = this.onlyUsers;
        int r4 = this.currentAccount;
        if (r22 != 2) goto L15;
        ArrayList<String> r23 = ContactsController.getInstance(r4).sortedUsersMutualSectionsArray;
    L17:
        if (this.onlyUsers != 0) goto L19;
    L27:
        if (r7 != 0) goto L43;
        if (this.isAdmin == false) goto L34;
        if (r8 >= 1) goto L32;
        return true;
    L32:
        return false;
    L34:
        if (this.needPhonebook == false) goto L39;
        if (r8 >= 2) goto L37;
        return true;
    L37:
        return false;
    L39:
        if (r8 >= 3) goto L41;
        return true;
    L41:
        return false;
    L43:
        if (this.isEmpty == false) goto L46;
        return false;
    L46:
        if (this.sortType != 2) goto L52;
        if (r7 == 1) goto L49;
    L58:
        return true;
    L49:
        if (r8 >= this.onlineContacts.size()) goto L51;
        return true;
    L51:
        return false;
    L52:
        int r72 = r7 - 1;
        if (r72 >= r23.size()) goto L58;
        if (r8 >= r63.get(r23.get(r72)).size()) goto L57;
        return true;
    L57:
        return false;
    L19:
        if (this.isAdmin == true) goto L27;
        if (this.isEmpty == false) goto L24;
        return false;
    L24:
        if (r8 >= r63.get(r23.get(r7)).size()) goto L26;
        return true;
    L26:
        return false;
    L15:
        r23 = ContactsController.getInstance(r4).sortedUsersSectionsArray;
        goto L17
    L11:
        r63 = ContactsController.getInstance(r2).usersSectionsDict;
        goto L12
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
    public void onBindViewHolder(int r19, int r20, RecyclerView.ViewHolder r21) {
        if (r19 == 0) goto L5;
    L9:
        int r3 = r20;
    L10:
        int r4 = r21.getItemViewType();
        int r5 = 7;
        boolean r8 = true;
        if (r4 == 0) goto L96;
        if (r4 == 1) goto L53;
        if (r4 != 2) goto L15;
        GraySectionCell r1 = (GraySectionCell) r21.itemView;
        int r0 = this.sortType;
        if (r0 != 0) goto L48;
        r1.setText(LocaleController.getString(R.string.Contacts));
        return;
    L48:
        if (r0 != 1) goto L51;
        r1.setText(LocaleController.getString(R.string.SortedByName));
        return;
    L51:
        r1.setText(LocaleController.getString(R.string.SortedByLastSeen));
        return;
    L15:
        if (r4 == 4) goto L38;
        if (r4 != 7) goto L18;
        HeaderCell r2 = (HeaderCell) r21.itemView;
        if (this.isEmptyWithMainTabs == false) goto L33;
        if (r3 != 1) goto L33;
        if (r19 != 1) goto L33;
        r2.setText(LocaleController.getString(R.string.InviteFriends));
        return;
    L33:
        if (this.sortType != 1) goto L36;
        r2.setText(LocaleController.getString(R.string.SortedByName));
        return;
    L36:
        r2.setText(LocaleController.getString(R.string.SortedByLastSeen));
        return;
    L18:
        if (r4 != 8) goto L138;
        InviteUserCell r12 = (InviteUserCell) r21.itemView;
        int r32 = r3 - 2;
        if (r32 >= 0) goto L23;
        return;
    L23:
        if (r32 >= ContactsController.getInstance(this.currentAccount).phoneBookContacts.size()) goto L133;
        r12.setUser(ContactsController.getInstance(this.currentAccount).phoneBookContacts.get(r32), null);
        return;
    L133:
        return;
    L138:
        return;
    L38:
        View r13 = r21.itemView;
        if (this.hasPhonebook == true) goto L41;
        float r02 = 96.0f;
    L42:
        r13.setPadding(0, AndroidUtilities.dp(r02), 0, AndroidUtilities.dp(18.0f));
        return;
    L41:
        r02 = 25.0f;
        goto L42
    L53:
        TextCell r10 = (TextCell) r21.itemView;
        if (this.needPhonebook == false) goto L56;
    L59:
        int r22 = Theme.key_windowBackgroundWhiteBlackText;
        r10.setColors(r22, r22);
    L60:
        if (r19 == 0) goto L62;
        ContactsController.Contact r03 = ContactsController.getInstance(this.currentAccount).phoneBookContacts.get(r3);
        String r14 = r03.first_name;
        if (r14 != null) goto L86;
    L89:
        if (r14 != null) goto L91;
    L94:
        r10.setText(r03.last_name, false);
        return;
    L91:
        if (r03.last_name != null) goto L94;
        r10.setText(r14, false);
        return;
    L86:
        if (r03.last_name == null) goto L89;
        r10.setText(r03.first_name + " " + r03.last_name, false);
        return;
    L62:
        if (this.needPhonebook == false) goto L70;
        if (r3 != 0) goto L66;
        r10.setTextAndValueAndColorfulIcon(LocaleController.getString(R.string.InviteFriends), _UrlKt.FRAGMENT_ENCODE_SET, false, R.drawable.settings_invite, -14899731, -15431455, false);
        return;
    L66:
        if (r3 != 1) goto L134;
        r10.setTextAndValueAndColorfulIcon(LocaleController.getString(R.string.RecentCalls), _UrlKt.FRAGMENT_ENCODE_SET, false, R.drawable.settings_calls, -11154873, -14175180, false);
        return;
    L134:
        return;
    L70:
        if (this.isAdmin == true) goto L72;
        if (r3 != 0) goto L80;
        r10.setTextAndValueAndColorfulIcon(LocaleController.getString(R.string.NewGroup), _UrlKt.FRAGMENT_ENCODE_SET, false, R.drawable.settings_group, -14899731, -15431455, false);
        return;
    L80:
        if (r3 != 1) goto L135;
        r10.setTextAndValueAndColorfulIcon(LocaleController.getString(R.string.NewChannel), _UrlKt.FRAGMENT_ENCODE_SET, false, R.drawable.settings_channel, -11154873, -14175180, false);
        return;
    L135:
        return;
    L72:
        if (this.isChannel == false) goto L75;
        r10.setTextAndIcon(LocaleController.getString(R.string.ChannelInviteViaLink), R.drawable.msg_link2, false);
        return;
    L75:
        r10.setTextAndIcon(LocaleController.getString(R.string.InviteToGroupByLink), R.drawable.msg_link2, false);
        return;
    L56:
        if (this.isAdmin == false) goto L59;
        int r23 = Theme.key_telegram_color_text;
        r10.setColors(r23, r23);
        goto L60
    L96:
        UserCell r24 = (UserCell) r21.itemView;
        r24.storyParams.drawSegments = false;
        if (this.sortType != 2) goto L99;
    L102:
        r24.setAvatarPadding(r5, 1);
        if (this.sortType != 2) goto L105;
        ArrayList<TLRPC.TL_contact> r15 = this.onlineContacts;
    L120:
        TLRPC.User r16 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r15.get(r3).user_id));
        r24.setMutual(r16.mutual_contact);
        r24.setData(r16, null, null, 0);
        if (this.selectedContacts.indexOfKey(r16.id) >= 0) goto L124;
        r8 = false;
    L124:
        r24.setChecked(r8, false);
        LongSparseArray<TLRPC.User> r04 = this.ignoreUsers;
        if (r04 != null) goto L127;
        return;
    L127:
        if (r04.indexOfKey(r16.id) < 0) goto L130;
        r24.setAlpha(0.5f);
        return;
    L130:
        r24.setAlpha(1.0f);
        return;
    L105:
        int r42 = this.onlyUsers;
        int r52 = this.currentAccount;
        if (r42 != 2) goto L108;
        HashMap<String, ArrayList<TLRPC.TL_contact>> r43 = ContactsController.getInstance(r52).usersMutualSectionsDict;
    L109:
        int r53 = this.onlyUsers;
        int r102 = this.currentAccount;
        if (r53 != 2) goto L112;
        ArrayList<String> r54 = ContactsController.getInstance(r102).sortedUsersMutualSectionsArray;
    L114:
        if (this.onlyUsers != 0) goto L116;
    L118:
        int r7 = 1;
    L119:
        r15 = r43.get(r54.get(r19 - r7));
        goto L120
    L116:
        if (this.isAdmin == true) goto L118;
        r7 = 0;
        goto L119
    L112:
        r54 = ContactsController.getInstance(r102).sortedUsersSectionsArray;
        goto L114
    L108:
        r43 = ContactsController.getInstance(r52).usersSectionsDict;
        goto L109
    L99:
        if (this.disableSections == true) goto L102;
        r5 = 58;
        goto L102
    L5:
        if (this.includeSearch == false) goto L9;
        if (r20 == 0) goto L137;
        r3 = r20 - 1;
        goto L10
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r11, int r12) {
        if (r12 == 0) goto L34;
        if (r12 != 1) goto L6;
        View r112 = new TextCell(this.mContext);
    L36:
        return new RecyclerListView.Holder(r112);
    L6:
        if (r12 != 2) goto L8;
        r112 = new GraySectionCell(this.mContext);
        goto L36
    L8:
        if (r12 != 3) goto L10;
        View r113 = new DividerCell(this.mContext);
        float r122 = 72.0f;
        if (LocaleController.isRTL == false) goto L26;
        float r10 = 28.0f;
    L27:
        int r102 = AndroidUtilities.dp(r10);
        int r2 = AndroidUtilities.dp(8.0f);
        if (LocaleController.isRTL == true) goto L31;
        r122 = 28.0f;
    L31:
        r113.setPadding(r102, r2, AndroidUtilities.dp(r122), AndroidUtilities.dp(8.0f));
        r112 = r113;
        goto L36
    L26:
        r10 = 72.0f;
        goto L27
    L10:
        if (r12 != 4) goto L12;
        FrameLayout r123 = new AnonymousClass2(this, this.mContext, r11);
        r123.addView(new ContactsEmptyView(this.mContext), LayoutHelper.createFrame(-1, -2, 17));
        r123.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        r123.setTag(-33024);
        View r124 = r123;
    L19:
        r112 = r124;
        goto L36
    L12:
        if (r12 != 7) goto L14;
        r112 = new HeaderCell(this.mContext, Theme.key_windowBackgroundWhiteBlueHeader, 21, 14, 5, false, null);
        goto L36
    L14:
        if (r12 != 8) goto L16;
        r112 = new InviteUserCell(this.mContext, false);
        goto L36
    L16:
        if (r12 == 9) goto L18;
        r112 = new ShadowSectionCell(this.mContext);
        goto L36
    L18:
        View r125 = new AnonymousClass1(this, this.mContext);
        r125.setId(9);
        r125.setTag(-33024);
        r124 = r125;
        goto L19
    L34:
        UserCell r114 = new UserCell(this.mContext, 58, 1, false);
        r114.setCallCellStyle(58);
        r112 = r114;
        goto L36
    }

    public void setDisableSections(boolean r1) {
        this.disableSections = r1;
    }

    public void setSortType(int r5, boolean r6) {
        this.sortType = r5;
        if (r5 == 2) goto L5;
        notifyDataSetChanged();
        return;
    L5:
        if (this.onlineContacts == null) goto L7;
        if (r6 == true) goto L7;
    L13:
        sortOnlineContacts();
        return;
    L7:
        this.onlineContacts = new ArrayList(ContactsController.getInstance(this.currentAccount).contacts);
        long r52 = UserConfig.getInstance(this.currentAccount).clientUserId;
        int r0 = this.onlineContacts.size();
        int r1 = 0;
    L8:
        if (r1 >= r0) goto L13;
        if (this.onlineContacts.get(r1).user_id == r52) goto L11;
        r1 = r1 + 1;
        goto L8
    L11:
        this.onlineContacts.remove(r1);
        goto L13
    }

    public void sortOnlineContacts() {
        if (this.onlineContacts != null) goto L10;
        return;
    L10:
        int r0 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();     // Catch: Exception -> L7
        MessagesController r1 = MessagesController.getInstance(this.currentAccount);     // Catch: Exception -> L7
        Collections.sort(this.onlineContacts, new ContactsAdapter$$ExternalSyntheticLambda0(r1, r0));     // Catch: Exception -> L7
        notifyDataSetChanged();     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }
}
