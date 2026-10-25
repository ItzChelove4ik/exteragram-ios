package com.exteragram.messenger.icons.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.icons.ExteraResources;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.icons.IconPack;
import com.exteragram.messenger.icons.ui.components.NewIconPackBottomSheet;
import com.exteragram.messenger.icons.ui.picker.IconPickerController;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PopupSwipeBackLayout;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0016\u0018\u0000 A2\u00020\u00012\u00020\u0002:\u0002ABB\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0014J\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J \u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u0012H\u0002J\u0010\u0010'\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0012H\u0002J\b\u0010(\u001a\u00020 H\u0002J\b\u0010)\u001a\u00020\bH\u0016J\b\u0010*\u001a\u00020 H\u0016J\b\u0010+\u001a\u00020 H\u0002J\b\u0010,\u001a\u00020\nH\u0016J\u000e\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u001e\u0010.\u001a\u00020 2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u00100\u001a\u000201H\u0014J0\u00102\u001a\u00020 2\u0006\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u00122\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000207H\u0014J5\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020\u00122\u0006\u0010;\u001a\u00020\u00122\u0016\u0010<\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010=0\u000e\"\u0004\u0018\u00010=H\u0016¢\u0006\u0002\u0010>J\u0010\u0010?\u001a\u00020\b2\u0006\u0010@\u001a\u00020\bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "iconPack", "Lcom/exteragram/messenger/icons/IconPack;", "<init>", "(Lcom/exteragram/messenger/icons/IconPack;)V", "searching", _UrlKt.FRAGMENT_ENCODE_SET, "query", _UrlKt.FRAGMENT_ENCODE_SET, "otherItem", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;", "filterItems", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;", "[Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;", "iconFilter", _UrlKt.FRAGMENT_ENCODE_SET, "searchRunnable", "Ljava/lang/Runnable;", "packItems", "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "packItemLowerNames", "[Ljava/lang/String;", "packItemsFor", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "updateAdapter", _UrlKt.FRAGMENT_ENCODE_SET, "createFilterLayout", "Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;", "createFilterItem", "layout", "text", "filter", "setIconFilter", "updateFilterChecks", "onFragmentCreate", "onFragmentDestroy", "loadIconsAsync", "getTitle", "getPackItems", "fillItems", PluginsConstants.Settings.ITEMS, "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "didReceivedNotification", "id", "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "onBackPressed", "invoked", "Companion", "EditorIconCell", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIconPacksEditorActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconPacksEditorActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksEditorActivity\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,388:1\n14113#2,3:389\n1#3:392\n1924#4,3:393\n1021#4,2:396\n*S KotlinDebug\n*F\n+ 1 IconPacksEditorActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksEditorActivity\n*L\n175#1:389,3\n228#1:393,3\n208#1:396,2\n*E\n"})
/* loaded from: classes4.dex */
public class IconPacksEditorActivity extends BasePreferencesActivity implements NotificationCenter.NotificationCenterDelegate {
    public static final Companion Companion = null;
    private static final ArrayList<UItem> cachedIconItems = null;
    private static boolean isIconsLoaded;
    private static boolean isLoading;
    private final ActionBarMenuSubItem[] filterItems;
    private int iconFilter;
    private IconPack iconPack;
    private ActionBarMenuItem otherItem;
    private String[] packItemLowerNames;
    private ArrayList<UItem> packItems;
    private IconPack packItemsFor;
    private String query;
    private Runnable searchRunnable;
    private boolean searching;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "EDIT_INFO_BUTTON", _UrlKt.FRAGMENT_ENCODE_SET, "DONE_BUTTON", "FILTER_ALL", "FILTER_REPLACED", "FILTER_NOT_REPLACED", "cachedIconItems", "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "isIconsLoaded", _UrlKt.FRAGMENT_ENCODE_SET, "isLoading", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\nB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;", "Lorg/telegram/ui/Cells/TextCell;", "context", "Landroid/content/Context;", "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "<init>", "(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V", "boundResId", _UrlKt.FRAGMENT_ENCODE_SET, "Factory", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SuppressLint({"ViewConstructor"})
    public static final class EditorIconCell extends TextCell {
        private int boundResId;

        @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J4\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0096\u0002J\u0018\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J4\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\b\u001a\u0004\u0018\u00010\u001dH\u0016¨\u0006\u001f"}, d2 = {"Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;", "Lorg/telegram/ui/Components/UItem$UItemFactory;", "Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;", "<init>", "()V", "createView", "context", "Landroid/content/Context;", "listView", "Lorg/telegram/ui/Components/RecyclerListView;", "currentAccount", _UrlKt.FRAGMENT_ENCODE_SET, "classGuid", "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "a", "Lorg/telegram/ui/Components/UItem;", "b", "contentsEquals", "bindView", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.VIEW, "Landroid/view/View;", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.TYPE_DIVIDER, "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "Lorg/telegram/ui/Components/UniversalRecyclerView;", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Factory extends UItem.UItemFactory<EditorIconCell> {
            public static final Companion Companion = null;

            @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007¨\u0006\f"}, d2 = {"Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "asIcon", "Lorg/telegram/ui/Components/UItem;", "id", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "pack", "Lcom/exteragram/messenger/icons/IconPack;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                    this();
                }

                @JvmStatic
                public final UItem asIcon(int r1, CharSequence r2, IconPack r3) {
                    UItem r0 = UItem.ofFactory(Factory.class);
                    r0.id = r1;
                    r0.text = r2;
                    r0.object = r3;
                    return r0;
                }
            }

            /* renamed from: $r8$lambda$dS-hbUNjaZCMtCzJXPQ2TkFICTA, reason: not valid java name */
            public static void m1447$r8$lambda$dShbUNjaZCMtCzJXPQ2TkFICTA(EditorIconCell r1, int r2, Drawable r3) {
                if (EditorIconCell.access$getBoundResId$p(r1) != r2) goto L6;
                r1.getValueImageView().setImageDrawable(r3);
                return;
            }

            static {
                Companion = new Companion(null);
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            @JvmStatic
            public static final UItem asIcon(int r1, CharSequence r2, IconPack r3) {
                return Companion.asIcon(r1, r2, r3);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r3, UItem r4, boolean r5, UniversalAdapter r6, UniversalRecyclerView r7) {
                EditorIconCell r32 = (EditorIconCell) r3;
                r32.reset();
                EditorIconCell.access$setBoundResId$p(r32, r4.id);
                Resources r2 = r32.getContext().getResources();
                boolean r62 = r2 instanceof ExteraResources;
                int r72 = r4.id;
                if (r62 == false) goto L5;
                Drawable r22 = ((ExteraResources) r2).getOriginalDrawable(r72);
            L6:
                Object r63 = r4.object;
                Drawable r0 = null;
                if ((r63 instanceof IconPack) == false) goto L9;
                IconPack r64 = (IconPack) r63;
            L10:
                if (r64 == null) goto L12;
                Drawable r73 = IconManager.INSTANCE.getCachedPackIconDrawable(r64, r4.id);
            L13:
                CharSequence r1 = r4.text;
                if (r22 == null) goto L16;
                r0 = r22.mutate();
            L16:
                r32.setTextAndIconAndValueDrawable(r1, r0, r73, r5);
                r32.setIsIcon(true);
                r32.setColors(Theme.key_windowBackgroundWhiteGrayIcon, Theme.key_windowBackgroundWhiteBlackText);
                r32.setOffsetFromImage(68);
                if (r73 != null) goto L24;
                int r23 = r4.id;
                IconPacksEditorActivity$EditorIconCell$Factory$$ExternalSyntheticLambda0 r42 = new IconPacksEditorActivity$EditorIconCell$Factory$$ExternalSyntheticLambda0(r32, r23);
                if (r64 == null) goto L22;
                IconManager.INSTANCE.requestPackIconDrawable(r64, r23, r42);
                return;
            L22:
                IconManager.INSTANCE.requestDrawable(r23, r42);
                return;
            L24:
                return;
            L12:
                r73 = IconManager.INSTANCE.getCachedDrawable(r4.id);
                goto L13
            L9:
                r64 = null;
                goto L10
            L5:
                r22 = r2.getDrawable(r72);
                goto L6
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean contentsEquals(UItem r4, UItem r5) {
                if (r4.id == r5.id) goto L6;
                return false;
            L6:
                if (TextUtils.equals(r4.text, r5.text) == true) goto L8;
                return false;
            L8:
                Object r3 = r4.object;
                IconPack r2 = null;
                if ((r3 instanceof IconPack) == false) goto L11;
                IconPack r32 = (IconPack) r3;
            L12:
                Object r52 = r5.object;
                if ((r52 instanceof IconPack) == false) goto L15;
                r2 = (IconPack) r52;
            L15:
                if (r32 != r2) goto L18;
                return true;
            L18:
                if (r32 == null) goto L23;
                if (r2 == null) goto L23;
                String r42 = r4.text.toString();
                return Intrinsics.areEqual(r32.getIcons().get(r42), r2.getIcons().get(r42));
            L23:
                return false;
            L11:
                r32 = null;
                goto L12
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public EditorIconCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new EditorIconCell(r1, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r1, UItem r2) {
                if (r1.id != r2.id) goto L6;
                return true;
            L6:
                return false;
            }
        }

        public EditorIconCell(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        public static final /* synthetic */ int access$getBoundResId$p(EditorIconCell r0) {
            return r0.boundResId;
        }

        public static final /* synthetic */ void access$setBoundResId$p(EditorIconCell r0, int r1) {
            r0.boundResId = r1;
        }
    }

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1", "Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;", "onItemClick", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nIconPacksEditorActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconPacksEditorActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,388:1\n1#2:389\n*E\n"})
    /* renamed from: com.exteragram.messenger.icons.ui.IconPacksEditorActivity$createView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ Context $context;
        final /* synthetic */ IconPacksEditorActivity this$0;

        public AnonymousClass1(IconPacksEditorActivity r1, Context r2) {
            this.this$0 = r1;
            this.$context = r2;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 != (-1)) goto L5;
            this.this$0.finishFragment();
            return;
        L5:
            if (r3 != 1) goto L7;
            IconPacksEditorActivity r0 = this.this$0;
            new NewIconPackBottomSheet(r0, this.$context, IconPacksEditorActivity.access$getIconPack$p(r0)).show();
            return;
        L7:
            if (r3 == 2) goto L9;
            return;
        L9:
            LaunchActivity r32 = null;
            ExteraConfig.setEditingIconPackId(null);
            Activity r02 = this.this$0.getParentActivity();
            if ((r02 instanceof LaunchActivity) == false) goto L12;
            r32 = (LaunchActivity) r02;
        L12:
            if (r32 == null) goto L14;
            IconPickerController.setActive(r32, false);
        L14:
            this.this$0.finishFragment();
        }
    }

    /* renamed from: $r8$lambda$1D1TkBWKe-ycW3UIMgBeZ5aqz0E, reason: not valid java name */
    public static void m1443$r8$lambda$1D1TkBWKeycW3UIMgBeZ5aqz0E(IconPacksEditorActivity r6) {
        HashMap r0 = new HashMap(IconManager.INSTANCE.getSystemIcons());
        if (r0.isEmpty() == false) goto L6;
        AndroidUtilities.runOnUIThread(new IconPacksEditorActivity$$ExternalSyntheticLambda2());
        return;
    L6:
        ArrayList r1 = new ArrayList(1500);
        Iterator r02 = r0.entrySet().iterator();
    L8:
        if (r02.hasNext() == false) goto L11;
        Map.Entry r2 = (Map.Entry) r02.next();
        String r3 = (String) r2.getKey();
        Integer r22 = (Integer) r2.getValue();
        r1.add(EditorIconCell.Factory.Companion.asIcon(r22.intValue(), r3, null));
        goto L8
    L11:
        if (r1.size() <= 1) goto L13;
        CollectionsKt.sortWith(r1, new IconPacksEditorActivity$loadIconsAsync$lambda$6$$inlined$sortBy$1());
    L13:
        AndroidUtilities.runOnUIThread(new IconPacksEditorActivity$$ExternalSyntheticLambda3(r1, r6));
    }

    /* renamed from: $r8$lambda$69mJBxZl5O5EW_Nl6tO4arW-8Ac, reason: not valid java name */
    public static void m1444$r8$lambda$69mJBxZl5O5EW_Nl6tO4arW8Ac() {
        isLoading = false;
        IconManager.INSTANCE.initialize(true);
    }

    /* renamed from: $r8$lambda$7Bzs4DV-0Xa7qKoQ3-e9Qk0UYws, reason: not valid java name */
    public static void m1445$r8$lambda$7Bzs4DV0Xa7qKoQ3e9Qk0UYws(IconPacksEditorActivity r0, View r1) {
        ActionBarMenuItem r02 = r0.otherItem;
        if (r02 == null) goto L10;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r03 = r02.getPopupLayout();
        if (r03 == null) goto L11;
        PopupSwipeBackLayout r04 = r03.getSwipeBack();
        if (r04 == null) goto L12;
        r04.closeForeground();
        return;
    L12:
        return;
    L11:
        return;
    }

    /* renamed from: $r8$lambda$EYr2Y6g3-Q1c1cfyqK3hs-YGcNo, reason: not valid java name */
    public static void m1446$r8$lambda$EYr2Y6g3Q1c1cfyqK3hsYGcNo(ArrayList r1, IconPacksEditorActivity r2) {
        ArrayList<UItem> r0 = cachedIconItems;
        r0.clear();
        r0.addAll(r1);
        isIconsLoaded = true;
        isLoading = false;
        r2.packItems = null;
        r2.updateAdapter();
    }

    public static void $r8$lambda$o85uiPt34Xr3v1pDEtDOK1NsfDk(IconPacksEditorActivity r0, int r1, View r2) {
        r0.setIconFilter(r1);
    }

    static {
        Companion = new Companion(null);
        cachedIconItems = new ArrayList();
    }

    public IconPacksEditorActivity(IconPack r1) {
        this.iconPack = r1;
        this.filterItems = new ActionBarMenuSubItem[3];
        this.packItemLowerNames = new String[0];
    }

    public static final /* synthetic */ IconPack access$getIconPack$p(IconPacksEditorActivity r0) {
        return r0.iconPack;
    }

    public static final /* synthetic */ ActionBarMenuItem access$getOtherItem$p(IconPacksEditorActivity r0) {
        return r0.otherItem;
    }

    public static final /* synthetic */ Runnable access$getSearchRunnable$p(IconPacksEditorActivity r0) {
        return r0.searchRunnable;
    }

    public static final /* synthetic */ void access$setQuery$p(IconPacksEditorActivity r0, String r1) {
        r0.query = r1;
    }

    public static final /* synthetic */ void access$setSearchRunnable$p(IconPacksEditorActivity r0, Runnable r1) {
        r0.searchRunnable = r1;
    }

    public static final /* synthetic */ void access$setSearching$p(IconPacksEditorActivity r0, boolean r1) {
        r0.searching = r1;
    }

    public static final /* synthetic */ void access$updateAdapter(IconPacksEditorActivity r0) {
        r0.updateAdapter();
    }

    private final void createFilterItem(ActionBarPopupWindow.ActionBarPopupWindowLayout r7, String r8, int r9) {
        ActionBarMenuSubItem r0 = new ActionBarMenuSubItem(r7.getContext(), true, false, false, this.resourceProvider);
        r0.setTextAndIcon(r8, 0);
        r0.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r7.addView(r0, LayoutHelper.createLinear(-1, 48));
        r0.setOnClickListener(new IconPacksEditorActivity$$ExternalSyntheticLambda4(this, r9));
        this.filterItems[r9] = r0;
    }

    private final ActionBarPopupWindow.ActionBarPopupWindowLayout createFilterLayout(Context r6) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r6, 0, this.resourceProvider);
        r0.setFitItems(true);
        ActionBarMenuItem.addItem(r0, R.drawable.msg_arrow_back, LocaleController.getString(R.string.Back), false, this.resourceProvider).setOnClickListener(new IconPacksEditorActivity$$ExternalSyntheticLambda1(this));
        View r1 = ActionBarMenuItem.addGap(0, r0);
        r1.setLayoutParams(LayoutHelper.createLinear(-1, 8));
        r1.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuSeparator));
        createFilterItem(r0, LocaleController.getString(R.string.IconPickerAllIcons), 0);
        createFilterItem(r0, LocaleController.getString(R.string.IconPickerReplacedIcons), 1);
        createFilterItem(r0, LocaleController.getString(R.string.IconPickerNotReplacedIcons), 2);
        updateFilterChecks();
        return r0;
    }

    private final ArrayList<UItem> getPackItems() {
        ArrayList<UItem> r0 = this.packItems;
        if (r0 != null) goto L5;
    L7:
        ArrayList<UItem> r1 = cachedIconItems;
        ArrayList<UItem> r02 = new ArrayList(r1.size());
        int r12 = r1.size();
        String[] r2 = new String[r12];
        int r3 = 0;
        int r4 = 0;
    L9:
        if (r4 >= r12) goto L11;
        r2[r4] = _UrlKt.FRAGMENT_ENCODE_SET;
        r4 = r4 + 1;
        goto L9
    L11:
        ArrayList<UItem> r13 = cachedIconItems;
        int r42 = r13.size();
        int r6 = 0;
    L12:
        if (r6 >= r42) goto L24;
        UItem r7 = r13.get(r6);
        r6 = r6 + 1;
        int r8 = r3 + 1;
        if (r3 >= 0) goto L16;
        CollectionsKt.throwIndexOverflow();
    L16:
        UItem r72 = r7;
        r02.add(EditorIconCell.Factory.Companion.asIcon(r72.id, r72.text, this.iconPack));
        CharSequence r73 = r72.text;
        if (r73 == null) goto L22;
        String r74 = r73.toString();
        if (r74 == null) goto L22;
        String r75 = r74.toLowerCase(Locale.getDefault());
        if (r75 == null) goto L22;
    L23:
        r2[r3] = r75;
        r3 = r8;
    L22:
        r75 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L23
    L24:
        this.packItems = r02;
        this.packItemLowerNames = r2;
        this.packItemsFor = this.iconPack;
        return r02;
    L5:
        if (Intrinsics.areEqual(this.packItemsFor, this.iconPack) == false) goto L7;
        return r0;
    }

    private final void loadIconsAsync() {
        if (isIconsLoaded == false) goto L8;
        if (cachedIconItems.isEmpty() == true) goto L8;
        return;
    L8:
        if (isLoading == false) goto L10;
        return;
    L10:
        isLoading = true;
        Utilities.globalQueue.postRunnable(new IconPacksEditorActivity$$ExternalSyntheticLambda0(this));
    }

    private final void setIconFilter(int r2) {
        if (this.iconFilter != r2) goto L5;
        return;
    L5:
        this.iconFilter = r2;
        updateFilterChecks();
        updateAdapter();
    }

    private final void updateAdapter() {
        if (this.fragmentView == null) goto L8;
        UniversalAdapter r1 = getListView$TMessagesProj().adapter;
        if (r1 == null) goto L9;
        r1.update(true);
        return;
    L9:
        return;
    }

    private final void updateFilterChecks() {
        ActionBarMenuSubItem[] r0 = this.filterItems;
        int r1 = r0.length;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r3 >= r1) goto L12;
        ActionBarMenuSubItem r5 = r0[r3];
        int r6 = r4 + 1;
        if (r5 == null) goto L11;
        if (this.iconFilter != r4) goto L9;
        boolean r42 = true;
    L10:
        r5.setChecked(r42);
        goto L11
    L9:
        r42 = false;
    L11:
        r3 = r3 + 1;
        r4 = r6;
        goto L3
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        View r0 = super.createView(r8);
        ActionBarMenu r1 = this.actionBar.createMenu();
        r1.addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new IconPacksEditorActivity$createView$searchItem$1(this)).setSearchPaddingStart(7);
        ActionBarMenuItem r12 = r1.addItem(3, R.drawable.ic_ab_other);
        this.otherItem = r12;
        r12.addSwipeBackItem(R.drawable.msg_select, null, LocaleController.getString(R.string.IconPickerFilter), createFilterLayout(r8));
        r12.addSubItem(1, R.drawable.msg_edit, LocaleController.getString(R.string.Edit));
        if (ExteraConfig.getEditingIconPackId() == null) goto L5;
        ActionBarMenuSubItem r13 = r12.addSubItem(2, R.drawable.ic_ab_done, LocaleController.getString(R.string.IconPickerSaveAndExit));
        int r2 = Theme.key_featuredStickers_addButtonPressed;
        r13.setColors(getThemedColor(r2), getThemedColor(r2));
    L5:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this, r8));
        return r0;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.iconPackUpdated) goto L6;
        return;
    L6:
        if (getContext() != null) goto L9;
        return;
    L9:
        if (isIconsLoaded == true) goto L11;
        loadIconsAsync();
    L11:
        IconPack r12 = this.iconPack;
        if (r12 == null) goto L19;
        IconPack r13 = IconManager.INSTANCE.findPackById(r12.getId());
        if (r13 != null) goto L17;
        finishFragment();
        return;
    L17:
        this.iconPack = r13;
        this.actionBar.setTitle(getTitle());
    L19:
        if (this.fragmentView == null) goto L21;
        Parcelable r14 = getLayoutManager().onSaveInstanceState();
    L22:
        updateAdapter();
        if (r14 == null) goto L26;
        getLayoutManager().onRestoreInstanceState(r14);
        return;
    L26:
        return;
    L21:
        r14 = null;
        goto L22
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r12, UniversalAdapter r13) {
        if (getContext() != null) goto L5;
        return;
    L5:
        if (isIconsLoaded == false) goto L51;
        ArrayList<UItem> r132 = getPackItems();
        String r0 = this.query;
        if (this.searching == false) goto L14;
        if (r0 == null) goto L14;
        if (r0.length() == 0) goto L14;
        String r02 = r0.toLowerCase(Locale.getDefault());
    L15:
        IconPack r1 = this.iconPack;
        if (r1 == null) goto L18;
        Map<String, String> r14 = r1.getIcons();
    L19:
        int r3 = r132.size();
        int r5 = 0;
    L20:
        if (r5 >= r3) goto L52;
        if (r02 != null) goto L24;
    L25:
        UItem r7 = r132.get(r5);
        if (this.iconFilter == 0) goto L43;
        CharSequence r8 = r7.text;
        if (r8 == null) goto L30;
        String r82 = r8.toString();
    L32:
        if (r82 == null) goto L37;
        if (r14 == null) goto L37;
        if (r14.containsKey(r82) != true) goto L37;
        boolean r83 = true;
    L38:
        int r10 = this.iconFilter;
        if (r10 != 1) goto L41;
        if (r83 == true) goto L41;
    L44:
        r5 = r5 + 1;
    L41:
        if (r10 != 2) goto L43;
        if (r83 == true) goto L44;
    L37:
        r83 = false;
        goto L38
    L30:
        r82 = null;
    L43:
        r12.add(r7);
        goto L44
    L24:
        if (StringsKt.contains$default(this.packItemLowerNames[r5], r02, false, 2, null) == false) goto L44;
    L52:
        return;
    L18:
        r14 = null;
    L14:
        r02 = null;
        goto L15
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        IconPack r0 = this.iconPack;
        if (r0 == null) goto L9;
        String r02 = r0.getName();
        if (r02 == null) goto L9;
        return r02;
    L9:
        return LocaleController.getString(R.string.NewIconPack);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.searching == false) goto L9;
        if (r2 == false) goto L10;
        this.actionBar.closeSearchField();
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r2);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        IconManager.INSTANCE.showReplaceAlert(getContext(), r1.id, this.iconPack);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.iconPackUpdated);
        loadIconsAsync();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.iconPackUpdated);
        super.onFragmentDestroy();
    }
}
