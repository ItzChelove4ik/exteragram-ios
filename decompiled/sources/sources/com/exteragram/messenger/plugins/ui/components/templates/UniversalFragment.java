package com.exteragram.messenger.plugins.ui.components.templates;

import android.content.Context;
import android.view.View;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001,B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0006\u0010\r\u001a\u00020\u000eJ\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J \u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J(\u0010\u0018\u001a\u00020\u00122\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0014J0\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0014J0\u0010'\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0014J\b\u0010(\u001a\u00020\u0015H\u0016J\b\u0010)\u001a\u00020\u0012H\u0016J\u0010\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0015H\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006-"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalFragment;", "Lorg/telegram/ui/Components/UniversalFragment;", "delegate", "Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalFragment$UniversalFragmentDelegate;", "<init>", "(Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalFragment$UniversalFragmentDelegate;)V", "getDelegate", "()Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalFragment$UniversalFragmentDelegate;", "setDelegate", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "getActionBarMenu", "Lorg/telegram/ui/ActionBar/ActionBarMenu;", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "setTitle", _UrlKt.FRAGMENT_ENCODE_SET, "title", "animated", _UrlKt.FRAGMENT_ENCODE_SET, "duration", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "Lkotlin/collections/ArrayList;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "onLongClick", "onFragmentCreate", "onFragmentDestroy", "onBackPressed", "invoked", "UniversalFragmentDelegate", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class UniversalFragment extends org.telegram.ui.Components.UniversalFragment {
    private UniversalFragmentDelegate delegate;

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\u000f\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000bJ\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J(\u0010\u000e\u001a\u00020\u00072\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J0\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016J0\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016J\u0010\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0018H\u0016¨\u0006\u001fÀ\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/templates/UniversalFragment$UniversalFragmentDelegate;", _UrlKt.FRAGMENT_ENCODE_SET, "beforeCreateView", "Landroid/view/View;", "afterCreateView", PluginsConstants.Settings.VIEW, "onFragmentCreate", _UrlKt.FRAGMENT_ENCODE_SET, "onFragmentDestroy", "onBackPressed", _UrlKt.FRAGMENT_ENCODE_SET, "()Ljava/lang/Boolean;", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "Lkotlin/collections/ArrayList;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "onLongClick", "onMenuItemClick", "id", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface UniversalFragmentDelegate {

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static View afterCreateView(UniversalFragmentDelegate r0, View r1) {
                return UniversalFragmentDelegate.access$afterCreateView$jd(r0, r1);
            }

            @Deprecated
            public static View beforeCreateView(UniversalFragmentDelegate r0) {
                return UniversalFragmentDelegate.access$beforeCreateView$jd(r0);
            }

            @Deprecated
            public static void fillItems(UniversalFragmentDelegate r0, ArrayList<UItem> r1, UniversalAdapter r2) {
                UniversalFragmentDelegate.access$fillItems$jd(r0, r1, r2);
            }

            @Deprecated
            public static CharSequence getTitle(UniversalFragmentDelegate r0) {
                return UniversalFragmentDelegate.access$getTitle$jd(r0);
            }

            @Deprecated
            public static Boolean onBackPressed(UniversalFragmentDelegate r0) {
                return UniversalFragmentDelegate.access$onBackPressed$jd(r0);
            }

            @Deprecated
            public static void onClick(UniversalFragmentDelegate r0, UItem r1, View r2, int r3, float r4, float r5) {
                UniversalFragmentDelegate.access$onClick$jd(r0, r1, r2, r3, r4, r5);
            }

            @Deprecated
            public static void onFragmentCreate(UniversalFragmentDelegate r0) {
                UniversalFragmentDelegate.access$onFragmentCreate$jd(r0);
            }

            @Deprecated
            public static void onFragmentDestroy(UniversalFragmentDelegate r0) {
                UniversalFragmentDelegate.access$onFragmentDestroy$jd(r0);
            }

            @Deprecated
            public static boolean onLongClick(UniversalFragmentDelegate r0, UItem r1, View r2, int r3, float r4, float r5) {
                return UniversalFragmentDelegate.access$onLongClick$jd(r0, r1, r2, r3, r4, r5);
            }

            @Deprecated
            public static void onMenuItemClick(UniversalFragmentDelegate r0, int r1) {
                UniversalFragmentDelegate.access$onMenuItemClick$jd(r0, r1);
            }
        }

        static /* synthetic */ View access$afterCreateView$jd(UniversalFragmentDelegate r0, View r1) {
            return super.afterCreateView(r1);
        }

        static /* synthetic */ View access$beforeCreateView$jd(UniversalFragmentDelegate r0) {
            return super.beforeCreateView();
        }

        static /* synthetic */ void access$fillItems$jd(UniversalFragmentDelegate r0, ArrayList r1, UniversalAdapter r2) {
            super.fillItems(r1, r2);
        }

        static /* synthetic */ CharSequence access$getTitle$jd(UniversalFragmentDelegate r0) {
            return super.getTitle();
        }

        static /* synthetic */ Boolean access$onBackPressed$jd(UniversalFragmentDelegate r0) {
            return super.onBackPressed();
        }

        static /* synthetic */ void access$onClick$jd(UniversalFragmentDelegate r0, UItem r1, View r2, int r3, float r4, float r5) {
            super.onClick(r1, r2, r3, r4, r5);
        }

        static /* synthetic */ void access$onFragmentCreate$jd(UniversalFragmentDelegate r0) {
            super.onFragmentCreate();
        }

        static /* synthetic */ void access$onFragmentDestroy$jd(UniversalFragmentDelegate r0) {
            super.onFragmentDestroy();
        }

        static /* synthetic */ boolean access$onLongClick$jd(UniversalFragmentDelegate r0, UItem r1, View r2, int r3, float r4, float r5) {
            return super.onLongClick(r1, r2, r3, r4, r5);
        }

        static /* synthetic */ void access$onMenuItemClick$jd(UniversalFragmentDelegate r0, int r1) {
            super.onMenuItemClick(r1);
        }

        default View afterCreateView(View r1) {
            return r1;
        }

        default View beforeCreateView() {
            return null;
        }

        default void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        }

        default CharSequence getTitle() {
            return null;
        }

        default Boolean onBackPressed() {
            return null;
        }

        default void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        }

        default void onFragmentCreate() {
        }

        default void onFragmentDestroy() {
        }

        default boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
            return false;
        }

        default void onMenuItemClick(int r1) {
        }
    }

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/plugins/ui/components/templates/UniversalFragment$createView$1", "Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;", "onItemClick", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.plugins.ui.components.templates.UniversalFragment$createView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ UniversalFragment this$0;

        public AnonymousClass1(UniversalFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            UniversalFragment r0 = this.this$0;
            if (r3 == (-1)) goto L5;
            UniversalFragmentDelegate r2 = r0.getDelegate();
            if (r2 == null) goto L13;
            r2.onMenuItemClick(r3);
            return;
        L13:
            return;
        L5:
            if (r0.onBackPressed(true) == false) goto L12;
            this.this$0.finishFragment();
            return;
        }
    }

    public UniversalFragment() {
        this(null, 1, null);
    }

    public UniversalFragment(UniversalFragmentDelegate r1) {
        this.delegate = r1;
    }

    public /* synthetic */ UniversalFragment(UniversalFragmentDelegate r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = null;
    L5:
        this(r1);
    }

    @Override // org.telegram.ui.Components.UniversalFragment, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r4) {
        UniversalFragmentDelegate r0 = this.delegate;
        View r1 = null;
        if (r0 == null) goto L5;
        View r02 = r0.beforeCreateView();
    L6:
        if (r02 == null) goto L8;
        return r02;
    L8:
        View r42 = super.createView(r4);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        UniversalFragmentDelegate r3 = this.delegate;
        if (r3 == null) goto L11;
        r1 = r3.afterCreateView(r42);
    L11:
        if (r1 != null) goto L13;
        return r42;
    L13:
        return r1;
    L5:
        r02 = null;
        goto L6
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        UniversalFragmentDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.fillItems(r1, r2);
        return;
    }

    public final ActionBarMenu getActionBarMenu() {
        return this.actionBar.createMenu();
    }

    public final UniversalFragmentDelegate getDelegate() {
        return this.delegate;
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public CharSequence getTitle() {
        UniversalFragmentDelegate r0 = this.delegate;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getTitle();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        UniversalFragmentDelegate r0 = this.delegate;
        if (r0 == null) goto L5;
        Boolean r02 = r0.onBackPressed();
    L6:
        if (r02 == null) goto L10;
        return !r02.booleanValue();
    L10:
        return super.onBackPressed(r2);
    L5:
        r02 = null;
        goto L6
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        UniversalFragmentDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onClick(r1, r2, r3, r4, r5);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        UniversalFragmentDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onFragmentCreate();
    L6:
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        UniversalFragmentDelegate r0 = this.delegate;
        if (r0 == null) goto L5;
        r0.onFragmentDestroy();
    L5:
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        UniversalFragmentDelegate r0 = this.delegate;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.onLongClick(r1, r2, r3, r4, r5);
    }

    public final void setDelegate(UniversalFragmentDelegate r1) {
        this.delegate = r1;
    }

    public final void setTitle(CharSequence r3, boolean r4, long r5) {
        ActionBar r2 = this.actionBar;
        if (r4 == true) goto L5;
        r2.setTitle(r3);
        return;
    L5:
        if (r5 > 0) goto L8;
        r5 = 300;
    L8:
        r2.setTitleAnimated(r3, false, r5);
    }
}
