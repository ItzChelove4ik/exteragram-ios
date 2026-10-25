package com.exteragram.messenger.ai.ui.activities;

import android.view.View;
import com.exteragram.messenger.ai.AiConfig;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.data.Message;
import com.exteragram.messenger.ai.ui.MarkdownPreview;
import com.exteragram.messenger.ai.ui.components.HistoryMessageCell;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0002 !B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\u001e\u0010\u000f\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J0\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0014J0\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0014J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\bH\u0002J\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\bH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "entries", "Ljava/util/ArrayList;", "Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;", "lastEntryId", _UrlKt.FRAGMENT_ENCODE_SET, "onFragmentCreate", _UrlKt.FRAGMENT_ENCODE_SET, "formatContents", _UrlKt.FRAGMENT_ENCODE_SET, "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", PluginsConstants.Settings.ITEMS, "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "Landroid/view/View;", "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "onLongClick", "findEntry", "id", "deleteTurn", "HistoryItem", "Entry", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAiHistoryActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AiHistoryActivity.kt\ncom/exteragram/messenger/ai/ui/activities/AiHistoryActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,146:1\n1915#2,2:147\n1915#2,2:149\n296#2,2:151\n363#2,7:153\n1915#2,2:160\n1586#2:162\n1661#2,3:163\n1915#2,2:166\n1586#2:168\n1661#2,3:169\n*S KotlinDebug\n*F\n+ 1 AiHistoryActivity.kt\ncom/exteragram/messenger/ai/ui/activities/AiHistoryActivity\n*L\n53#1:147,2\n84#1:149,2\n125#1:151,2\n128#1:153,7\n141#1:160,2\n143#1:162\n143#1:163,3\n68#1:166,2\n65#1:168\n65#1:169,3\n*E\n"})
/* loaded from: classes4.dex */
public final class AiHistoryActivity extends BasePreferencesActivity {
    private final ArrayList<Entry> entries;
    private int lastEntryId;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "message", "Lcom/exteragram/messenger/ai/data/Message;", "content", _UrlKt.FRAGMENT_ENCODE_SET, "expanded", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(ILcom/exteragram/messenger/ai/data/Message;Ljava/lang/CharSequence;Z)V", "getId", "()I", "getMessage", "()Lcom/exteragram/messenger/ai/data/Message;", "getContent", "()Ljava/lang/CharSequence;", "setContent", "(Ljava/lang/CharSequence;)V", "getExpanded", "()Z", "setExpanded", "(Z)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Entry {
        private CharSequence content;
        private boolean expanded;
        private final int id;
        private final Message message;

        public Entry(int r1, Message r2, CharSequence r3, boolean r4) {
            this.id = r1;
            this.message = r2;
            this.content = r3;
            this.expanded = r4;
        }

        public /* synthetic */ Entry(int r1, Message r2, CharSequence r3, boolean r4, int r5, DefaultConstructorMarker r6) {
            if ((r5 & 4) == 0) goto L9;
            r3 = r2.content();
            if (r3 != null) goto L9;
            r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        L9:
            if ((r5 & 8) == 0) goto L11;
            r4 = false;
        L11:
            this(r1, r2, r3, r4);
        }

        public final CharSequence getContent() {
            return this.content;
        }

        public final boolean getExpanded() {
            return this.expanded;
        }

        public final int getId() {
            return this.id;
        }

        public final Message getMessage() {
            return this.message;
        }

        public final void setContent(CharSequence r1) {
            this.content = r1;
        }

        public final void setExpanded(boolean r1) {
            this.expanded = r1;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tj\u0002\b\u0004j\u0002\b\u0005¨\u0006\n"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$HistoryItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "SAVE_HISTORY", "CLEAR_HISTORY", "id", _UrlKt.FRAGMENT_ENCODE_SET, "getId", "()I", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum HistoryItem extends Enum<HistoryItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ HistoryItem[] $VALUES = null;
        public static final HistoryItem CLEAR_HISTORY = null;
        public static final HistoryItem SAVE_HISTORY = null;
        private final int id;

        private static final /* synthetic */ HistoryItem[] $values() {
            return new HistoryItem[]{SAVE_HISTORY, CLEAR_HISTORY};
        }

        static {
            SAVE_HISTORY = new HistoryItem("SAVE_HISTORY", 0);
            CLEAR_HISTORY = new HistoryItem("CLEAR_HISTORY", 1);
            HistoryItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        HistoryItem(String r1, int r2) {
            this.id = ordinal() + 1;
        }

        public static EnumEntries<HistoryItem> getEntries() {
            return $ENTRIES;
        }

        public static HistoryItem valueOf(String r1) {
            return (HistoryItem) Enum.valueOf(HistoryItem.class, r1);
        }

        public static HistoryItem[] values() {
            return (HistoryItem[]) $VALUES.clone();
        }

        public final int getId() {
            return this.id;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[HistoryItem.values().length];
            r0[HistoryItem.SAVE_HISTORY.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L9:
            r0[HistoryItem.CLEAR_HISTORY.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L5:
            $EnumSwitchMapping$0 = r0;
        }
    }

    /* renamed from: $r8$lambda$TeKMotS-U3GWzCgiEGZlnRzfEBQ, reason: not valid java name */
    public static void m1079$r8$lambda$TeKMotSU3GWzCgiEGZlnRzfEBQ(Message r0, AiHistoryActivity r1) {
        if (AndroidUtilities.addToClipboard(r0.content()) == false) goto L6;
        BulletinFactory.of(r1).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
        return;
    }

    public static void $r8$lambda$X7WILYf2_F_e3VttIMbLGN8pCPY(AiHistoryActivity r0, UItem r1) {
        r0.deleteTurn(r1.id);
    }

    /* renamed from: $r8$lambda$gfvGL5WLbqSMT9-bA3eD6l_37F4, reason: not valid java name */
    public static void m1080$r8$lambda$gfvGL5WLbqSMT9bA3eD6l_37F4(AiHistoryActivity r1) {
        r1.entries.clear();
        UniversalAdapter r12 = r1.getListView$TMessagesProj().adapter;
        if (r12 == null) goto L6;
        r12.update(true);
        return;
    }

    /* renamed from: $r8$lambda$ofDW94-8OSkXzL1ZijoW2hKrSBA, reason: not valid java name */
    public static void m1081$r8$lambda$ofDW948OSkXzL1ZijoW2hKrSBA(AiHistoryActivity r3, ArrayList r4, List r5) {
        if (r3.getContext() == null) goto L15;
        Iterator<Integer> r0 = CollectionsKt.getIndices(r4).iterator();
    L7:
        if (r0.hasNext() == false) goto L9;
        int r1 = ((IntIterator) r0).nextInt();
        ((Entry) r4.get(r1)).setContent((CharSequence) r5.get(r1));
        goto L7
    L9:
        UniversalAdapter r32 = r3.getListView$TMessagesProj().adapter;
        if (r32 == null) goto L14;
        r32.update(true);
        return;
    L14:
        return;
    }

    public static void $r8$lambda$yHw1Jp_QCjZs1mLMPCKkqJo8p1s(boolean r0) {
        AiConfig.setSaveHistory(r0);
    }

    public static void $r8$lambda$zMhmBRYvGwkxzZlxU_WuxAu4bFc(ArrayList r4, AiHistoryActivity r5) {
        ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r4, 10));
        int r1 = r4.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        Object r3 = r4.get(r2);
        r2 = r2 + 1;
        r0.add(MarkdownPreview.format(((Entry) r3).getMessage().content()));
        goto L3
    L5:
        AndroidUtilities.runOnUIThread(new AiHistoryActivity$$ExternalSyntheticLambda3(r5, r4, r0));
    }

    public AiHistoryActivity() {
        this.entries = new ArrayList();
        this.lastEntryId = 100;
    }

    private final void deleteTurn(int r8) {
        ArrayList<Entry> r0 = this.entries;
        int r1 = r0.size();
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r4 >= r1) goto L9;
        Entry r6 = r0.get(r4);
        r4 = r4 + 1;
        if (r6.getId() == r8) goto L10;
        r3 = r3 + 1;
    L10:
        if (r3 != (-1)) goto L13;
        return;
    L13:
        if (Intrinsics.areEqual("assistant", this.entries.get(r3).getMessage().role()) == false) goto L15;
        int r82 = r3 - 1;
    L16:
        int r12 = r82 + 1;
        if (r82 >= 0) goto L19;
    L26:
        r82 = r3;
    L27:
        Iterator<Integer> r83 = RangesKt.downTo(r3, r82).iterator();
    L29:
        if (r83.hasNext() == false) goto L31;
        this.entries.remove(((IntIterator) r83).nextInt());
        goto L29
    L31:
        ArrayList<Entry> r84 = this.entries;
        ArrayList r02 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r84, 10));
        int r13 = r84.size();
    L32:
        if (r2 >= r13) goto L34;
        Entry r32 = r84.get(r2);
        r2 = r2 + 1;
        r02.add(r32.getMessage());
        goto L32
    L34:
        AiConfig.saveConversationHistory(new ArrayList(r02));
        UniversalAdapter r7 = getListView$TMessagesProj().adapter;
        if (r7 == null) goto L42;
        r7.update(true);
        return;
    L42:
        return;
    L19:
        if (r12 >= this.entries.size()) goto L26;
        if (Intrinsics.areEqual("assistant", this.entries.get(r82).getMessage().role()) == true) goto L26;
        if (Intrinsics.areEqual("assistant", this.entries.get(r12).getMessage().role()) == false) goto L26;
        r3 = r12;
        goto L27
    L15:
        r82 = r3;
        goto L16
    L9:
        r3 = -1;
        goto L10
    }

    private final Entry findEntry(int r5) {
        ArrayList<Entry> r4 = this.entries;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        Entry r2 = r4.get(r1);
        r1 = r1 + 1;
        if (r2.getId() != r5) goto L3;
    L9:
        return r2;
    L7:
        r2 = null;
        goto L9
    }

    private final void formatContents() {
        if (this.entries.isEmpty() == false) goto L5;
        return;
    L5:
        ArrayList r0 = new ArrayList(this.entries);
        Utilities.globalQueue.postRunnable(new AiHistoryActivity$$ExternalSyntheticLambda2(r0, this));
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r17, UniversalAdapter r18) {
        r17.add(UItem.asRippleCheck(HistoryItem.SAVE_HISTORY.getId(), LocaleController.getString(R.string.MessageHistory)).setChecked(AiConfig.getSaveHistory()));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.HistoryInfo)));
        if (this.entries.isEmpty() == false) goto L5;
        return;
    L5:
        int r2 = 0;
        r17.add(UItem.asHeader(LocaleController.formatPluralString("messages", this.entries.size(), new Object[0])));
        ArrayList<Entry> r1 = this.entries;
        int r3 = r1.size();
    L6:
        if (r2 >= r3) goto L8;
        Entry r4 = r1.get(r2);
        r2 = r2 + 1;
        Entry r42 = r4;
        r17.add(HistoryMessageCell.Factory.asHistoryCell(r42.getId(), r42.getMessage(), r42.getContent(), r42.getExpanded()));
        goto L6
    L8:
        r17.add(UItem.asShadow(null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, HistoryItem.CLEAR_HISTORY, LocaleController.getString(R.string.ClearHistory), R.drawable.msg_delete, null, null, null, false, null, true, false, null, 1720, null));
        r17.add(UItem.asShadow(null));
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.MessageHistory);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r22 = r1.id;
        if (r22 > 0) goto L5;
    L17:
        Entry r12 = findEntry(r1.id);
        if (r12 == null) goto L23;
        r12.setExpanded(!r12.getExpanded());
        UniversalAdapter r0 = getListView$TMessagesProj().adapter;
        if (r0 == null) goto L24;
        r0.update(true);
        return;
    L24:
        return;
    L23:
        return;
    L5:
        if (r22 > HistoryItem.getEntries().size()) goto L17;
        int r23 = WhenMappings.$EnumSwitchMapping$0[HistoryItem.getEntries().get(r1.id - 1).ordinal()];
        if (r23 != 1) goto L10;
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new AiHistoryActivity$$ExternalSyntheticLambda4());
        return;
    L10:
        if (r23 != 2) goto L13;
        AiController.clearHistory(this, this.resourceProvider, true, new AiHistoryActivity$$ExternalSyntheticLambda5(this));
        return;
    L13:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        ArrayList<Message> r0 = AiConfig.getConversationHistory();
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        Message r3 = r0.get(r2);
        r2 = r2 + 1;
        Message r6 = r3;
        ArrayList<Entry> r32 = this.entries;
        int r5 = this.lastEntryId;
        this.lastEntryId = r5 + 1;
        r32.add(new Entry(r5, r6, null, false, 12, null));
        goto L3
    L5:
        formatContents();
        return super.onFragmentCreate();
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean onLongClick(UItem r3, View r4, int r5, float r6, float r7) {
        Object r52 = r3.object;
        if ((r52 instanceof Message) == false) goto L5;
        Message r53 = (Message) r52;
    L6:
        if (r53 != null) goto L9;
        return false;
    L9:
        ItemOptions r62 = ItemOptions.makeOptions(this, r4);
        r62.add(R.drawable.msg_copy, LocaleController.getString(R.string.Copy), new AiHistoryActivity$$ExternalSyntheticLambda0(r53, this));
        r62.add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new AiHistoryActivity$$ExternalSyntheticLambda1(this, r3));
        if (LocaleController.isRTL == false) goto L12;
        int r54 = 3;
    L13:
        r62.setGravity(r54);
        r62.setScrimViewBackground(getListView$TMessagesProj().getClipBackground(r4));
        r62.show();
        return true;
    L12:
        r54 = 5;
        goto L13
    L5:
        r53 = null;
        goto L6
    }
}
