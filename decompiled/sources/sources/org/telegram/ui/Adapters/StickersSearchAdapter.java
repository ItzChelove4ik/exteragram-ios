package org.telegram.ui.Adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.EmptyCell;
import org.telegram.ui.Cells.FeaturedStickerSetInfoCell;
import org.telegram.ui.Cells.StickerEmojiCell;
import org.telegram.ui.Cells.StickerSetNameCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class StickersSearchAdapter extends RecyclerListView.SelectionAdapter {
    private SparseArray<Object> cache;
    private SparseArray<Object> cacheParent;
    boolean cleared;
    private final Context context;
    private final int currentAccount;
    private final Delegate delegate;
    private ArrayList<ArrayList<TLRPC.Document>> emojiArrays;
    private int emojiSearchId;
    private HashMap<ArrayList<TLRPC.Document>, String> emojiStickers;
    private ImageView emptyImageView;
    private TextView emptyTextView;
    private final LongSparseArray<TLRPC.StickerSetCovered> installingStickerSets;
    private ArrayList<TLRPC.TL_messages_stickerSet> localPacks;
    private HashMap<TLRPC.TL_messages_stickerSet, Integer> localPacksByName;
    private HashMap<TLRPC.TL_messages_stickerSet, Boolean> localPacksByShortName;
    private SparseArray<String> positionToEmoji;
    private SparseIntArray positionToRow;
    private SparseArray<TLRPC.StickerSetCovered> positionsToSets;
    private final TLRPC.StickerSetCovered[] primaryInstallingStickerSets;
    private final LongSparseArray<TLRPC.StickerSetCovered> removingStickerSets;
    private int reqId;
    private int reqId2;
    private final Theme.ResourcesProvider resourcesProvider;
    private SparseArray<Object> rowStartPack;
    private String searchQuery;
    private Runnable searchRunnable;
    private ArrayList<TLRPC.StickerSetCovered> serverPacks;
    private int totalItems;

    /* renamed from: org.telegram.ui.Adapters.StickersSearchAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ StickersSearchAdapter this$0;

        /* renamed from: $r8$lambda$2AP5NqkEQl95boJyH-8XfgvvmRo, reason: not valid java name */
        public static /* synthetic */ void m5964$r8$lambda$2AP5NqkEQl95boJyH8XfgvvmRo(AnonymousClass1 r0, int r1, HashMap r2, ArrayList r3, String r4) {
            r0.lambda$run$0(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$7ppSvVJi80amOi8QzAo1duJGDeE(AnonymousClass1 r0, TLRPC.TL_messages_getStickers r1, TLObject r2, ArrayList r3, LongSparseArray r4) {
            r0.lambda$run$3(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$UIuUF90zytquQ_odmWSGzObCFuY(AnonymousClass1 r0, TLRPC.TL_messages_searchStickerSets r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$run$2(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$aEoPFTJYSRKeD3Zg_1HnHYVFDwQ(AnonymousClass1 r0, TLRPC.TL_messages_searchStickerSets r1, TLObject r2) {
            r0.lambda$run$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$m4aeHofrhemXoARXEkuXcvBI1Wc(AnonymousClass1 r0, TLRPC.TL_messages_getStickers r1, ArrayList r2, LongSparseArray r3, TLObject r4, TLRPC.TL_error r5) {
            r0.lambda$run$4(r1, r2, r3, r4, r5);
        }

        public AnonymousClass1(StickersSearchAdapter r1) {
            this.this$0 = r1;
        }

        private void clear() {
            StickersSearchAdapter r0 = this.this$0;
            if (r0.cleared == false) goto L5;
            return;
        L5:
            r0.cleared = true;
            StickersSearchAdapter.m5955$$Nest$fgetemojiStickers(r0).clear();
            StickersSearchAdapter.m5953$$Nest$fgetemojiArrays(this.this$0).clear();
            StickersSearchAdapter.m5956$$Nest$fgetlocalPacks(this.this$0).clear();
            StickersSearchAdapter.m5960$$Nest$fgetserverPacks(this.this$0).clear();
            StickersSearchAdapter.m5958$$Nest$fgetlocalPacksByShortName(this.this$0).clear();
            StickersSearchAdapter.m5957$$Nest$fgetlocalPacksByName(this.this$0).clear();
        }

        private /* synthetic */ void lambda$run$0(int r5, HashMap r6, ArrayList r7, String r8) {
            if (r5 != StickersSearchAdapter.m5954$$Nest$fgetemojiSearchId(this.this$0)) goto L27;
            int r52 = r7.size();
            int r82 = 0;
            boolean r0 = false;
        L6:
            if (r82 >= r52) goto L18;
            String r1 = ((MediaDataController.KeywordResult) r7.get(r82)).emoji;
            if (r6 == null) goto L10;
            ArrayList r2 = (ArrayList) r6.get(r1);
        L11:
            if (r2 == null) goto L17;
            if (r2.isEmpty() == true) goto L17;
            clear();
            if (StickersSearchAdapter.m5955$$Nest$fgetemojiStickers(this.this$0).containsKey(r2) == true) goto L17;
            StickersSearchAdapter.m5955$$Nest$fgetemojiStickers(this.this$0).put(r2, r1);
            StickersSearchAdapter.m5953$$Nest$fgetemojiArrays(this.this$0).add(r2);
            r0 = true;
        L17:
            r82 = r82 + 1;
            goto L6
        L10:
            r2 = null;
            goto L11
        L18:
            if (r0 == false) goto L26;
            this.this$0.notifyDataSetChanged();
            return;
        L26:
            return;
        }

        private /* synthetic */ void lambda$run$1(TLRPC.TL_messages_searchStickerSets r2, TLObject r3) {
            if (r2.q.equals(StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0)) == false) goto L6;
            clear();
            StickersSearchAdapter.m5952$$Nest$fgetdelegate(this.this$0).onSearchStop();
            StickersSearchAdapter.m5962$$Nest$fputreqId(this.this$0, 0);
            StickersSearchAdapter.m5952$$Nest$fgetdelegate(this.this$0).setAdapterVisible(true);
            StickersSearchAdapter.m5960$$Nest$fgetserverPacks(this.this$0).addAll(((TLRPC.TL_messages_foundStickerSets) r3).sets);
            this.this$0.notifyDataSetChanged();
            return;
        }

        private /* synthetic */ void lambda$run$2(TLRPC.TL_messages_searchStickerSets r1, TLObject r2, TLRPC.TL_error r3) {
            if ((r2 instanceof TLRPC.TL_messages_foundStickerSets) == false) goto L6;
            AndroidUtilities.runOnUIThread(new StickersSearchAdapter$1$$ExternalSyntheticLambda4(this, r1, r2));
            return;
        }

        private /* synthetic */ void lambda$run$3(TLRPC.TL_messages_getStickers r6, TLObject r7, ArrayList r8, LongSparseArray r9) {
            if (r6.emoticon.equals(StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0)) == false) goto L24;
            int r0 = 0;
            StickersSearchAdapter.m5963$$Nest$fputreqId2(this.this$0, 0);
            if ((r7 instanceof TLRPC.TL_messages_stickers) == false) goto L26;
            TLRPC.TL_messages_stickers r72 = (TLRPC.TL_messages_stickers) r7;
            int r62 = r8.size();
            int r1 = r72.stickers.size();
        L8:
            if (r0 >= r1) goto L15;
            TLRPC.Document r2 = r72.stickers.get(r0);
            if (r9.indexOfKey(r2.id) >= 0) goto L13;
            r8.add(r2);
        L13:
            r0 = r0 + 1;
            goto L8
        L15:
            if (r62 == r8.size()) goto L25;
            StickersSearchAdapter.m5955$$Nest$fgetemojiStickers(this.this$0).put(r8, StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0));
            if (r62 != 0) goto L19;
            StickersSearchAdapter.m5953$$Nest$fgetemojiArrays(this.this$0).add(r8);
        L19:
            this.this$0.notifyDataSetChanged();
            return;
        L25:
            return;
        L26:
            return;
        }

        private /* synthetic */ void lambda$run$4(TLRPC.TL_messages_getStickers r1, ArrayList r2, LongSparseArray r3, TLObject r4, TLRPC.TL_error r5) {
            AndroidUtilities.runOnUIThread(new StickersSearchAdapter$1$$ExternalSyntheticLambda3(this, r1, r4, r2, r3));
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TextUtils.isEmpty(StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0)) == false) goto L5;
            return;
        L5:
            StickersSearchAdapter.m5952$$Nest$fgetdelegate(this.this$0).onSearchStart();
            StickersSearchAdapter r0 = this.this$0;
            int r1 = 0;
            r0.cleared = false;
            int r2 = StickersSearchAdapter.m5954$$Nest$fgetemojiSearchId(r0) + 1;
            StickersSearchAdapter.m5961$$Nest$fputemojiSearchId(r0, r2);
            ArrayList r02 = new ArrayList(0);
            LongSparseArray r4 = new LongSparseArray(0);
            HashMap<String, ArrayList<TLRPC.Document>> r5 = MediaDataController.getInstance(StickersSearchAdapter.m5951$$Nest$fgetcurrentAccount(this.this$0)).getAllStickers();
            if (StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0).length() > 14) goto L39;
            CharSequence r6 = StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0);
            int r7 = r6.length();
            int r8 = 0;
        L8:
            if (r8 >= r7) goto L29;
            if (r8 >= (r7 - 1)) goto L26;
            if (r6.charAt(r8) != 55356) goto L18;
            int r9 = r8 + 1;
            if (r6.charAt(r9) < 57339) goto L18;
            if (r6.charAt(r9) > 57343) goto L18;
        L23:
            r6 = TextUtils.concat(new CharSequence[]{r6.subSequence(0, r8), r6.subSequence(r8 + 2, r6.length())});
            r7 = r7 - 2;
        L24:
            r8 = r8 - 1;
        L28:
            r8 = r8 + 1;
        L18:
            if (r6.charAt(r8) != 8205) goto L26;
            int r92 = r8 + 1;
            if (r6.charAt(r92) == 9792) goto L23;
            if (r6.charAt(r92) == 9794) goto L23;
        L26:
            if (r6.charAt(r8) != 65039) goto L28;
            r6 = TextUtils.concat(new CharSequence[]{r6.subSequence(0, r8), r6.subSequence(r8 + 1, r6.length())});
            r7 = r7 - 1;
            goto L24
        L29:
            if (r5 == null) goto L31;
            ArrayList<TLRPC.Document> r62 = r5.get(r6.toString());
        L32:
            if (r62 == null) goto L39;
            if (r62.isEmpty() == true) goto L39;
            clear();
            r02.addAll(r62);
            int r72 = r62.size();
            int r82 = 0;
        L36:
            if (r82 >= r72) goto L38;
            TLRPC.Document r93 = r62.get(r82);
            r4.put(r93.id, r93);
            r82 = r82 + 1;
            goto L36
        L38:
            StickersSearchAdapter.m5955$$Nest$fgetemojiStickers(this.this$0).put(r02, StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0));
            StickersSearchAdapter.m5953$$Nest$fgetemojiArrays(this.this$0).add(r02);
            goto L39
        L31:
            r62 = null;
        L39:
            if (r5 != null) goto L41;
        L48:
            ArrayList<TLRPC.TL_messages_stickerSet> r22 = MediaDataController.getInstance(StickersSearchAdapter.m5951$$Nest$fgetcurrentAccount(this.this$0)).getStickerSets(0);
            int r52 = r22.size();
            int r63 = 0;
        L50:
            if (r63 >= r52) goto L66;
            TLRPC.TL_messages_stickerSet r83 = r22.get(r63);
            int r94 = AndroidUtilities.indexOfIgnoreCase(r83.set.title, StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0));
            if (r94 < 0) goto L57;
            if (r94 != 0) goto L55;
        L56:
            clear();
            StickersSearchAdapter.m5956$$Nest$fgetlocalPacks(this.this$0).add(r83);
            StickersSearchAdapter.m5957$$Nest$fgetlocalPacksByName(this.this$0).put(r83, Integer.valueOf(r94));
        L65:
            r63 = r63 + 1;
            goto L50
        L55:
            if (r83.set.title.charAt(r94 - 1) != ' ') goto L65;
        L57:
            String r95 = r83.set.short_name;
            if (r95 == null) goto L65;
            int r96 = AndroidUtilities.indexOfIgnoreCase(r95, StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0));
            if (r96 < 0) goto L65;
            if (r96 != 0) goto L63;
        L64:
            clear();
            StickersSearchAdapter.m5956$$Nest$fgetlocalPacks(this.this$0).add(r83);
            StickersSearchAdapter.m5958$$Nest$fgetlocalPacksByShortName(this.this$0).put(r83, Boolean.TRUE);
            goto L65
        L63:
            if (r83.set.short_name.charAt(r96 - 1) != ' ') goto L65;
        L66:
            ArrayList<TLRPC.TL_messages_stickerSet> r23 = MediaDataController.getInstance(StickersSearchAdapter.m5951$$Nest$fgetcurrentAccount(this.this$0)).getStickerSets(3);
            int r53 = r23.size();
        L67:
            if (r1 >= r53) goto L84;
            TLRPC.TL_messages_stickerSet r64 = r23.get(r1);
            int r84 = AndroidUtilities.indexOfIgnoreCase(r64.set.title, StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0));
            if (r84 < 0) goto L74;
            if (r84 != 0) goto L72;
        L73:
            clear();
            StickersSearchAdapter.m5956$$Nest$fgetlocalPacks(this.this$0).add(r64);
            StickersSearchAdapter.m5957$$Nest$fgetlocalPacksByName(this.this$0).put(r64, Integer.valueOf(r84));
        L82:
            r1 = r1 + 1;
            goto L67
        L72:
            if (r64.set.title.charAt(r84 - 1) != ' ') goto L82;
        L74:
            String r85 = r64.set.short_name;
            if (r85 == null) goto L82;
            int r86 = AndroidUtilities.indexOfIgnoreCase(r85, StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0));
            if (r86 < 0) goto L82;
            if (r86 != 0) goto L80;
        L81:
            clear();
            StickersSearchAdapter.m5956$$Nest$fgetlocalPacks(this.this$0).add(r64);
            StickersSearchAdapter.m5958$$Nest$fgetlocalPacksByShortName(this.this$0).put(r64, Boolean.TRUE);
            goto L82
        L80:
            if (r64.set.short_name.charAt(r86 - 1) != ' ') goto L82;
        L84:
            if (StickersSearchAdapter.m5956$$Nest$fgetlocalPacks(this.this$0).isEmpty() == true) goto L86;
        L87:
            StickersSearchAdapter.m5952$$Nest$fgetdelegate(this.this$0).setAdapterVisible(true);
        L88:
            TLRPC.TL_messages_searchStickerSets r12 = new TLRPC.TL_messages_searchStickerSets();
            r12.q = StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0);
            StickersSearchAdapter r24 = this.this$0;
            StickersSearchAdapter.m5962$$Nest$fputreqId(r24, ConnectionsManager.getInstance(StickersSearchAdapter.m5951$$Nest$fgetcurrentAccount(r24)).sendRequest(r12, new StickersSearchAdapter$1$$ExternalSyntheticLambda1(this, r12)));
            if (Emoji.isValidEmoji(StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0)) == false) goto L91;
            TLRPC.TL_messages_getStickers r13 = new TLRPC.TL_messages_getStickers();
            r13.emoticon = StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0);
            r13.hash = 0;
            StickersSearchAdapter r25 = this.this$0;
            StickersSearchAdapter.m5963$$Nest$fputreqId2(r25, ConnectionsManager.getInstance(StickersSearchAdapter.m5951$$Nest$fgetcurrentAccount(r25)).sendRequest(r13, new StickersSearchAdapter$1$$ExternalSyntheticLambda2(this, r13, r02, r4)));
        L91:
            this.this$0.notifyDataSetChanged();
            return;
        L86:
            if (StickersSearchAdapter.m5955$$Nest$fgetemojiStickers(this.this$0).isEmpty() == true) goto L88;
        L41:
            if (r5.isEmpty() == true) goto L48;
            if (StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0).length() <= 1) goto L48;
            String[] r65 = AndroidUtilities.getCurrentKeyboardLanguage();
            if (Arrays.equals(StickersSearchAdapter.m5952$$Nest$fgetdelegate(this.this$0).getLastSearchKeyboardLanguage(), r65) == true) goto L47;
            MediaDataController.getInstance(StickersSearchAdapter.m5951$$Nest$fgetcurrentAccount(this.this$0)).fetchNewEmojiKeywords(r65);
        L47:
            StickersSearchAdapter.m5952$$Nest$fgetdelegate(this.this$0).setLastSearchKeyboardLanguage(r65);
            MediaDataController.getInstance(StickersSearchAdapter.m5951$$Nest$fgetcurrentAccount(this.this$0)).getEmojiSuggestions(StickersSearchAdapter.m5952$$Nest$fgetdelegate(this.this$0).getLastSearchKeyboardLanguage(), StickersSearchAdapter.m5959$$Nest$fgetsearchQuery(this.this$0), false, new StickersSearchAdapter$1$$ExternalSyntheticLambda0(this, r2, r5), false);
            goto L48
        }
    }

    /* renamed from: org.telegram.ui.Adapters.StickersSearchAdapter$2, reason: invalid class name */
    public class AnonymousClass2 extends StickerEmojiCell {
        final /* synthetic */ StickersSearchAdapter this$0;

        public AnonymousClass2(StickersSearchAdapter r1, Context r2, boolean r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(82.0f), TLObject.FLAG_30));
        }
    }

    public interface Delegate {
        String[] getLastSearchKeyboardLanguage();

        int getStickersPerRow();

        void onSearchStart();

        void onSearchStop();

        void onStickerSetAdd(TLRPC.StickerSetCovered r1, boolean r2);

        void onStickerSetRemove(TLRPC.StickerSetCovered r1);

        void setAdapterVisible(boolean r1);

        void setLastSearchKeyboardLanguage(String[] r1);
    }

    public static /* synthetic */ void $r8$lambda$gyBfiQLlqpMRIAJv9CsS2r79hjI(StickersSearchAdapter r0, View r1) {
        r0.lambda$onCreateViewHolder$0(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5951$$Nest$fgetcurrentAccount(StickersSearchAdapter r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m5952$$Nest$fgetdelegate(StickersSearchAdapter r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetemojiArrays, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5953$$Nest$fgetemojiArrays(StickersSearchAdapter r0) {
        return r0.emojiArrays;
    }

    /* renamed from: -$$Nest$fgetemojiSearchId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5954$$Nest$fgetemojiSearchId(StickersSearchAdapter r0) {
        return r0.emojiSearchId;
    }

    /* renamed from: -$$Nest$fgetemojiStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m5955$$Nest$fgetemojiStickers(StickersSearchAdapter r0) {
        return r0.emojiStickers;
    }

    /* renamed from: -$$Nest$fgetlocalPacks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5956$$Nest$fgetlocalPacks(StickersSearchAdapter r0) {
        return r0.localPacks;
    }

    /* renamed from: -$$Nest$fgetlocalPacksByName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m5957$$Nest$fgetlocalPacksByName(StickersSearchAdapter r0) {
        return r0.localPacksByName;
    }

    /* renamed from: -$$Nest$fgetlocalPacksByShortName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m5958$$Nest$fgetlocalPacksByShortName(StickersSearchAdapter r0) {
        return r0.localPacksByShortName;
    }

    /* renamed from: -$$Nest$fgetsearchQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m5959$$Nest$fgetsearchQuery(StickersSearchAdapter r0) {
        return r0.searchQuery;
    }

    /* renamed from: -$$Nest$fgetserverPacks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5960$$Nest$fgetserverPacks(StickersSearchAdapter r0) {
        return r0.serverPacks;
    }

    /* renamed from: -$$Nest$fputemojiSearchId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5961$$Nest$fputemojiSearchId(StickersSearchAdapter r0, int r1) {
        r0.emojiSearchId = r1;
    }

    /* renamed from: -$$Nest$fputreqId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5962$$Nest$fputreqId(StickersSearchAdapter r0, int r1) {
        r0.reqId = r1;
    }

    /* renamed from: -$$Nest$fputreqId2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5963$$Nest$fputreqId2(StickersSearchAdapter r0, int r1) {
        r0.reqId2 = r1;
    }

    public StickersSearchAdapter(Context r2, Delegate r3, TLRPC.StickerSetCovered[] r4, LongSparseArray<TLRPC.StickerSetCovered> r5, LongSparseArray<TLRPC.StickerSetCovered> r6, Theme.ResourcesProvider r7) {
        this.currentAccount = UserConfig.selectedAccount;
        this.rowStartPack = new SparseArray();
        this.cache = new SparseArray();
        this.cacheParent = new SparseArray();
        this.positionToRow = new SparseIntArray();
        this.positionToEmoji = new SparseArray();
        this.serverPacks = new ArrayList();
        this.localPacks = new ArrayList();
        this.localPacksByShortName = new HashMap();
        this.localPacksByName = new HashMap();
        this.emojiStickers = new HashMap();
        this.emojiArrays = new ArrayList();
        this.positionsToSets = new SparseArray();
        this.searchRunnable = new AnonymousClass1(this);
        this.context = r2;
        this.delegate = r3;
        this.primaryInstallingStickerSets = r4;
        this.installingStickerSets = r5;
        this.removingStickerSets = r6;
        this.resourcesProvider = r7;
    }

    private void bindFeaturedStickerSetInfoCell(FeaturedStickerSetInfoCell r12, int r13, boolean r14) {
        MediaDataController r0 = MediaDataController.getInstance(this.currentAccount);
        ArrayList<Long> r1 = r0.getUnreadStickerSets();
        TLRPC.StickerSetCovered r4 = (TLRPC.StickerSetCovered) this.cache.get(r13);
        boolean r2 = true;
        if (r1 != null) goto L5;
    L7:
        boolean r5 = false;
    L8:
        int r15 = 0;
    L9:
        TLRPC.StickerSetCovered[] r3 = this.primaryInstallingStickerSets;
        if (r15 >= r3.length) goto L22;
        if (r3[r15] == null) goto L21;
        TLRPC.TL_messages_stickerSet r32 = MediaDataController.getInstance(this.currentAccount).getStickerSetById(this.primaryInstallingStickerSets[r15].set.id);
        if (r32 == null) goto L19;
        if (r32.set.archived == true) goto L19;
        this.primaryInstallingStickerSets[r15] = null;
    L19:
        if (this.primaryInstallingStickerSets[r15].set.id != r4.set.id) goto L21;
        boolean r9 = true;
    L24:
        if (TextUtils.isEmpty(this.searchQuery) == false) goto L27;
        int r16 = -1;
    L26:
        int r7 = r16;
        if (r7 < 0) goto L30;
        FeaturedStickerSetInfoCell r33 = r12;
        boolean r6 = r14;
        r33.setStickerSet(r4, r5, r6, r7, this.searchQuery.length(), r9);
    L35:
        if (r5 == false) goto L38;
        r0.markFeaturedStickersByIdAsRead(false, r4.set.id);
    L38:
        if (this.installingStickerSets.indexOfKey(r4.set.id) < 0) goto L40;
        boolean r122 = true;
    L42:
        if (this.removingStickerSets.indexOfKey(r4.set.id) < 0) goto L44;
        boolean r142 = true;
    L45:
        if (r122 == true) goto L47;
        if (r142 == true) goto L47;
    L55:
        if (r9 == true) goto L58;
        if (r122 == false) goto L58;
        boolean r11 = true;
    L59:
        r33.setAddDrawProgress(r11, r6);
        r0.preloadStickerSetThumb(r4);
        if (r13 > 0) goto L63;
        r2 = false;
    L63:
        r33.setNeedDivider(r2);
        return;
    L58:
        r11 = false;
    L47:
        if (r122 == true) goto L49;
    L51:
        if (r142 == false) goto L55;
        if (r33.isInstalled() == true) goto L55;
        this.removingStickerSets.remove(r4.set.id);
        goto L55
    L49:
        if (r33.isInstalled() == false) goto L51;
        this.installingStickerSets.remove(r4.set.id);
        r122 = false;
        goto L55
    L44:
        r142 = false;
        goto L45
    L40:
        r122 = false;
        goto L42
    L30:
        r33 = r12;
        r6 = r14;
        r33.setStickerSet(r4, r5, r6, 0, 0, r9);
        if (TextUtils.isEmpty(this.searchQuery) == true) goto L35;
        if (AndroidUtilities.indexOfIgnoreCase(r4.set.short_name, this.searchQuery) != 0) goto L35;
        r33.setUrl(r4.set.short_name, this.searchQuery.length());
        goto L35
    L27:
        r16 = AndroidUtilities.indexOfIgnoreCase(r4.set.title, this.searchQuery);
    L21:
        r15 = r15 + 1;
        goto L9
    L22:
        r9 = false;
        goto L24
    L5:
        if (r1.contains(Long.valueOf(r4.set.id)) == false) goto L7;
        r5 = true;
        goto L8
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$onCreateViewHolder$0(View r5) {
        FeaturedStickerSetInfoCell r52 = (FeaturedStickerSetInfoCell) r5.getParent();
        TLRPC.StickerSetCovered r0 = r52.getStickerSet();
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.installingStickerSets.indexOfKey(r0.set.id) < 0) goto L7;
        return;
    L7:
        if (this.removingStickerSets.indexOfKey(r0.set.id) < 0) goto L10;
        return;
    L10:
        if (r52.isInstalled() == false) goto L13;
        this.removingStickerSets.put(r0.set.id, r0);
        this.delegate.onStickerSetRemove(r52.getStickerSet());
        return;
    L13:
        installStickerSet(r0, r52);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return Math.max(1, this.totalItems + 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r3) {
        if (r3 != 0) goto L8;
        if (this.totalItems != 0) goto L8;
        return 5;
    L8:
        if (r3 != (getItemCount() - 1)) goto L11;
        return 4;
    L11:
        Object r2 = this.cache.get(r3);
        if (r2 != null) goto L14;
        return 1;
    L14:
        if ((r2 instanceof TLRPC.Document) == false) goto L18;
        return 0;
    L18:
        if ((r2 instanceof TLRPC.StickerSetCovered) == false) goto L21;
        return 3;
    L21:
        return 2;
    }

    public TLRPC.StickerSetCovered getSetForPosition(int r1) {
        return this.positionsToSets.get(r1);
    }

    public int getSpanSize(int r2) {
        if (r2 == this.totalItems) goto L11;
        if (this.cache.get(r2) != null) goto L7;
        return 1;
    L7:
        if ((this.cache.get(r2) instanceof TLRPC.Document) == false) goto L11;
        return 1;
    L11:
        return this.delegate.getStickersPerRow();
    }

    public void getThemeDescriptions(List<ThemeDescription> r12, RecyclerListView r13, ThemeDescription.ThemeDescriptionDelegate r14) {
        FeaturedStickerSetInfoCell.createThemeDescriptions(r12, r13, r14);
        StickerSetNameCell.createThemeDescriptions(r12, r13, r14);
        ImageView r1 = this.emptyImageView;
        int r2 = ThemeDescription.FLAG_IMAGECOLOR;
        int r7 = Theme.key_chat_emojiPanelEmptyText;
        r12.add(new ThemeDescription(r1, r2, null, null, null, null, r7));
        r12.add(new ThemeDescription(this.emptyTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r7));
    }

    public void installStickerSet(TLRPC.InputStickerSet r7) {
        int r0 = 0;
    L4:
        if (r0 >= this.serverPacks.size()) goto L10;
        TLRPC.StickerSetCovered r1 = this.serverPacks.get(r0);
        if (r1.set.id == r7.id) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        installStickerSet(r1, null);
        return;
    }

    public void installStickerSet(TLRPC.StickerSetCovered r7, FeaturedStickerSetInfoCell r8) {
        int r1 = 0;
    L3:
        TLRPC.StickerSetCovered[] r2 = this.primaryInstallingStickerSets;
        if (r1 >= r2.length) goto L16;
        if (r2[r1] == null) goto L15;
        TLRPC.TL_messages_stickerSet r22 = MediaDataController.getInstance(this.currentAccount).getStickerSetById(this.primaryInstallingStickerSets[r1].set.id);
        if (r22 == null) goto L13;
        if (r22.set.archived == true) goto L13;
        this.primaryInstallingStickerSets[r1] = null;
    L13:
        if (this.primaryInstallingStickerSets[r1].set.id != r7.set.id) goto L15;
        return;
    L15:
        r1 = r1 + 1;
    L16:
        int r12 = 0;
    L17:
        TLRPC.StickerSetCovered[] r23 = this.primaryInstallingStickerSets;
        if (r12 >= r23.length) goto L23;
        if (r23[r12] == null) goto L21;
        r12 = r12 + 1;
        goto L17
    L21:
        r23[r12] = r7;
        boolean r13 = true;
    L24:
        if (r13 == true) goto L27;
        if (r8 == null) goto L27;
        r8.setAddDrawProgress(true, true);
    L27:
        this.installingStickerSets.put(r7.set.id, r7);
        if (r8 == null) goto L31;
        this.delegate.onStickerSetAdd(r8.getStickerSet(), r13);
        return;
    L31:
        int r82 = this.positionsToSets.size();
        int r14 = 0;
    L32:
        if (r14 >= r82) goto L52;
        TLRPC.StickerSetCovered r24 = this.positionsToSets.get(r14);
        if (r24 == null) goto L39;
        if (r24.set.id != r7.set.id) goto L39;
        notifyItemChanged(r14, 0);
        return;
    L39:
        r14 = r14 + 1;
        goto L32
    L52:
        return;
    L23:
        r13 = false;
        goto L24
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r1) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v21, types: [org.telegram.tgnet.TLRPC$TL_messages_stickerSet, org.telegram.tgnet.TLRPC$messages_StickerSet] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void notifyDataSetChanged() {
        this.rowStartPack.clear();
        this.positionToRow.clear();
        this.cache.clear();
        this.positionsToSets.clear();
        this.positionToEmoji.clear();
        int r1 = 0;
        this.totalItems = 0;
        int r2 = this.serverPacks.size();
        int r3 = this.localPacks.size();
        int r4 = !this.emojiArrays.isEmpty() ? 1 : 0;
        int r5 = 0;
        int r6 = 0;
    L4:
        if (r5 >= ((r2 + r3) + r4)) goto L49;
        if (r5 >= r3) goto L7;
        TLRPC.TL_messages_stickerSet r7 = this.localPacks.get(r5);
        ArrayList<TLRPC.Document> r8 = r7.documents;
        int r16 = r2;
        TLRPC.StickerSetCovered r72 = r7;
    L29:
        if (r8.isEmpty() == true) goto L48;
        int r12 = (int) Math.ceil(r8.size() / this.delegate.getStickersPerRow());
        this.cache.put(this.totalItems, r72);
        if (r5 >= r3) goto L34;
    L36:
        this.positionToRow.put(this.totalItems, r6);
        int r22 = r8.size();
        int r9 = 0;
    L37:
        if (r9 >= r22) goto L44;
        int r10 = r9 + 1;
        int r11 = this.totalItems + r10;
        int r122 = (r6 + 1) + (r9 / this.delegate.getStickersPerRow());
        this.cache.put(r11, r8.get(r9));
        this.cacheParent.put(r11, r72);
        this.positionToRow.put(r11, r122);
        if (r5 < r3) goto L43;
        if ((r72 instanceof TLRPC.StickerSetCovered) == false) goto L43;
        this.positionsToSets.put(r11, r72);
    L43:
        r9 = r10;
        goto L37
    L44:
        int r23 = r12 + 1;
        int r82 = 0;
    L45:
        if (r82 >= r23) goto L47;
        this.rowStartPack.put(r6 + r82, r72);
        r82 = r82 + 1;
        goto L45
    L47:
        this.totalItems += (r12 * this.delegate.getStickersPerRow()) + 1;
        r6 = r6 + r23;
        goto L48
    L34:
        if ((r72 instanceof TLRPC.StickerSetCovered) == false) goto L36;
        this.positionsToSets.put(this.totalItems, r72);
    L48:
        r5 = r5 + 1;
        r2 = r16;
        r1 = 0;
        goto L4
    L7:
        int r73 = r5 - r3;
        if (r73 >= r4) goto L27;
        int r74 = this.emojiArrays.size();
        String r83 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r92 = r1;
        int r102 = r92;
    L10:
        if (r92 >= r74) goto L23;
        ArrayList<TLRPC.Document> r112 = this.emojiArrays.get(r92);
        String r123 = this.emojiStickers.get(r112);
        if (r123 != null) goto L14;
    L16:
        int r124 = r112.size();
        int r13 = r1;
    L17:
        if (r13 >= r124) goto L22;
        int r14 = this.totalItems + r102;
        int r15 = (r102 / this.delegate.getStickersPerRow()) + r6;
        TLRPC.Document r17 = r112.get(r13);
        int r162 = r2;
        this.cache.put(r14, r17);
        int r172 = r74;
        String r18 = r83;
        TLRPC.TL_messages_stickerSet r19 = MediaDataController.getInstance(this.currentAccount).getStickerSetById(MediaDataController.getStickerSetId(r17));
        if (r19 == null) goto L21;
        this.cacheParent.put(r14, r19);
    L21:
        this.positionToRow.put(r14, r15);
        r102 = r102 + 1;
        r13 = r13 + 1;
        r2 = r162;
        r74 = r172;
        r83 = r18;
        goto L17
    L22:
        r92 = r92 + 1;
        r1 = 0;
        goto L10
    L14:
        if (r83.equals(r123) == true) goto L16;
        this.positionToEmoji.put(this.totalItems + r102, r123);
        r83 = r123;
        goto L16
    L23:
        r16 = r2;
        int r110 = (int) Math.ceil(r102 / this.delegate.getStickersPerRow());
        int r24 = 0;
    L24:
        if (r24 >= r110) goto L26;
        this.rowStartPack.put(r6 + r24, Integer.valueOf(r102));
        r24 = r24 + 1;
        goto L24
    L26:
        this.totalItems += this.delegate.getStickersPerRow() * r110;
        r6 = r6 + r110;
        goto L48
    L27:
        r16 = r2;
        TLRPC.StickerSetCovered r75 = this.serverPacks.get(r73 - r4);
        r8 = r75.covers;
        r72 = r75;
        goto L29
    L49:
        super.notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
        int r0 = r8.getItemViewType();
        if (r0 != 0) goto L5;
        ((StickerEmojiCell) r8.itemView).setSticker((TLRPC.Document) this.cache.get(r9), null, this.cacheParent.get(r9), this.positionToEmoji.get(r9), false);
        return;
    L5:
        if (r0 != 1) goto L7;
        ((EmptyCell) r8.itemView).setHeight(0);
        return;
    L7:
        if (r0 != 2) goto L9;
        StickerSetNameCell r82 = (StickerSetNameCell) r8.itemView;
        Object r92 = this.cache.get(r9);
        if ((r92 instanceof TLRPC.TL_messages_stickerSet) == false) goto L38;
        TLRPC.TL_messages_stickerSet r93 = (TLRPC.TL_messages_stickerSet) r92;
        if (TextUtils.isEmpty(this.searchQuery) == false) goto L18;
    L24:
        Integer r02 = this.localPacksByName.get(r93);
        TLRPC.StickerSet r94 = r93.set;
        if (r94 == null) goto L32;
        if (r02 == null) goto L32;
        String r95 = r94.title;
        int r03 = r02.intValue();
        if (TextUtils.isEmpty(this.searchQuery) == true) goto L30;
        int r7 = this.searchQuery.length();
    L31:
        r82.setText(r95, 0, r03, r7);
        goto L32
    L30:
        r7 = 0;
    L32:
        r82.setUrl(null, 0);
        return;
    L18:
        if (this.localPacksByShortName.containsKey(r93) == false) goto L24;
        TLRPC.StickerSet r04 = r93.set;
        if (r04 == null) goto L22;
        r82.setText(r04.title, 0);
    L22:
        r82.setUrl(r93.set.short_name, this.searchQuery.length());
        return;
    L38:
        return;
    L9:
        if (r0 != 3) goto L39;
        bindFeaturedStickerSetInfoCell((FeaturedStickerSetInfoCell) r8.itemView, r9, false);
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4, List r5) {
        if (r5.contains(0) == true) goto L5;
    L8:
        super.onBindViewHolder(r3, r4, r5);
        return;
    L5:
        if (r3.getItemViewType() != 3) goto L8;
        bindFeaturedStickerSetInfoCell((FeaturedStickerSetInfoCell) r3.itemView, r4, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v7, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r8v9, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
        if (r9 != 0) goto L5;
        AnonymousClass2 r92 = new AnonymousClass2(this, this.context, false, this.resourcesProvider);
        r92.getImageView().setLayerNum(3);
        FrameLayout r7 = r92;
    L22:
        return new RecyclerListView.Holder(r7);
    L5:
        if (r9 != 1) goto L7;
        EmptyCell r82 = new EmptyCell(this.context);
    L15:
        r7 = r82;
        goto L22
    L7:
        if (r9 == 2) goto L18;
        if (r9 != 3) goto L10;
        FeaturedStickerSetInfoCell r0 = new FeaturedStickerSetInfoCell(this.context, 17, true, true, this.resourcesProvider);
        r0.setAddOnClickListener(new StickersSearchAdapter$$ExternalSyntheticLambda0(this));
        r7 = r0;
        goto L22
    L10:
        if (r9 != 4) goto L12;
        r82 = new View(this.context);
        goto L15
    L12:
        if (r9 == 5) goto L14;
        r7 = null;
        goto L22
    L14:
        ?? r83 = new LinearLayout(this.context);
        r83.setOrientation(1);
        r83.setGravity(17);
        ImageView r93 = new ImageView(this.context);
        this.emptyImageView = r93;
        r93.setScaleType(ImageView.ScaleType.CENTER);
        this.emptyImageView.setImageResource(R.drawable.stickers_empty);
        ImageView r94 = this.emptyImageView;
        int r2 = Theme.key_chat_emojiPanelEmptyText;
        r94.setColorFilter(new PorterDuffColorFilter(getThemedColor(r2), PorterDuff.Mode.MULTIPLY));
        r83.addView(this.emptyImageView, LayoutHelper.createLinear(-2, -2));
        r83.addView(new Space(this.context), LayoutHelper.createLinear(-1, 15));
        TextView r95 = new TextView(this.context);
        this.emptyTextView = r95;
        r95.setText(LocaleController.getString(R.string.NoStickersFound));
        this.emptyTextView.setTextSize(1, 16.0f);
        this.emptyTextView.setTextColor(getThemedColor(r2));
        r83.addView(this.emptyTextView, LayoutHelper.createLinear(-2, -2));
        r83.setMinimumHeight(AndroidUtilities.dp(112.0f));
        r83.setLayoutParams(LayoutHelper.createFrame(-1, -1.0f));
        r82 = r83;
        goto L15
    L18:
        r7 = new StickerSetNameCell(this.context, false, true, this.resourcesProvider, false);
        goto L22
    }

    public void search(String r5) {
        if (this.reqId == 0) goto L6;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
        this.reqId = 0;
    L6:
        if (this.reqId2 == 0) goto L9;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId2, true);
        this.reqId2 = 0;
    L9:
        if (TextUtils.isEmpty(r5) == false) goto L11;
        this.searchQuery = null;
        this.localPacks.clear();
        this.emojiStickers.clear();
        this.serverPacks.clear();
        this.delegate.setAdapterVisible(false);
        notifyDataSetChanged();
    L12:
        AndroidUtilities.cancelRunOnUIThread(this.searchRunnable);
        AndroidUtilities.runOnUIThread(this.searchRunnable, 300);
        return;
    L11:
        this.searchQuery = r5.toLowerCase();
        goto L12
    }

    public void updateColors(RecyclerListView r4) {
        int r3 = r4.getChildCount();
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L11;
        View r1 = r4.getChildAt(r0);
        if ((r1 instanceof FeaturedStickerSetInfoCell) == false) goto L8;
        ((FeaturedStickerSetInfoCell) r1).updateColors();
    L10:
        r0 = r0 + 1;
        goto L3
    L8:
        if ((r1 instanceof StickerSetNameCell) == false) goto L10;
        ((StickerSetNameCell) r1).updateColors();
        goto L10
    }
}
