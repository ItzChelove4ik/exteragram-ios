package com.exteragram.messenger.icons.ui.picker;

import android.app.Activity;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bJ\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0006J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fJ\u0006\u0010\u0010\u001a\u00020\nR \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/exteragram/messenger/icons/ui/picker/IconObserver;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "iconSources", "Ljava/util/WeakHashMap;", "Lorg/telegram/ui/ActionBar/BaseFragment;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "log", _UrlKt.FRAGMENT_ENCODE_SET, "resId", "removeSource", "owner", "getUsedIcons", _UrlKt.FRAGMENT_ENCODE_SET, "clear", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIconObserver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconObserver.kt\ncom/exteragram/messenger/icons/ui/picker/IconObserver\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,50:1\n410#2,7:51\n1391#3:58\n1480#3,5:59\n*S KotlinDebug\n*F\n+ 1 IconObserver.kt\ncom/exteragram/messenger/icons/ui/picker/IconObserver\n*L\n23#1:51,7\n39#1:58\n39#1:59,5\n*E\n"})
/* loaded from: classes.dex */
public final class IconObserver {
    public static final IconObserver INSTANCE = null;
    private static final WeakHashMap<BaseFragment, Set<Integer>> iconSources = null;

    static {
        INSTANCE = new IconObserver();
        iconSources = new WeakHashMap();
    }

    private IconObserver() {
    }

    public final void clear() {
        WeakHashMap<BaseFragment, Set<Integer>> r1 = iconSources;
        monitor-enter(r1);
        r1.clear();     // Catch: Throwable -> L7
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r1);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final Set<Integer> getUsedIcons() {
        List<BaseFragment> r4 = LaunchActivity.getVisibleFragments();
        WeakHashMap<BaseFragment, Set<Integer>> r0 = iconSources;
        monitor-enter(r0);
        ArrayList r1 = new ArrayList();     // Catch: Throwable -> L10
        Iterator<T> r42 = r4.iterator();     // Catch: Throwable -> L10
    L6:
        if (r42.hasNext() == false) goto L13;
        BaseFragment r2 = (BaseFragment) r42.next();     // Catch: Throwable -> L10
        Set<Integer> r22 = iconSources.get(r2);     // Catch: Throwable -> L10
        if (r22 != null) goto L12;
        r22 = SetsKt.emptySet();     // Catch: Throwable -> L10
    L12:
        CollectionsKt.addAll(r1, r22);     // Catch: Throwable -> L10
        goto L6
    L13:
        Set<Integer> r43 = CollectionsKt.toSet(r1);     // Catch: Throwable -> L10
        monitor-exit(r0);
        return r43;
    L10:
        th = move-exception;
        throw th;
    }

    public final void log(int r3) {
        if (ExteraConfig.getEditingIconPackId() == null) goto L28;
        BaseFragment r2 = LaunchActivity.getSafeLastFragment();
        if (r2 != null) goto L8;
        return;
    L8:
        Activity r0 = r2.getParentActivity();
        if ((r0 instanceof LaunchActivity) == false) goto L11;
        LaunchActivity r02 = (LaunchActivity) r0;
    L12:
        if (r02 == null) goto L14;
        IconPickerController.setActive(r02, true);
    L14:
        WeakHashMap<BaseFragment, Set<Integer>> r03 = iconSources;
        monitor-enter(r03);
        Set<Integer> r1 = r03.get(r2);     // Catch: Throwable -> L19
        if (r1 != null) goto L21;
        r1 = new HashSet();     // Catch: Throwable -> L19
        r03.put(r2, r1);     // Catch: Throwable -> L19
    L21:
        r1.add(Integer.valueOf(r3));     // Catch: Throwable -> L19
        monitor-exit(r03);
        return;
    L19:
        th = move-exception;
        throw th;
    L11:
        r02 = null;
        goto L12
    }

    public final void removeSource(BaseFragment r1) {
        WeakHashMap<BaseFragment, Set<Integer>> r0 = iconSources;
        monitor-enter(r0);
        Set<Integer> r12 = r0.remove(r1);     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
