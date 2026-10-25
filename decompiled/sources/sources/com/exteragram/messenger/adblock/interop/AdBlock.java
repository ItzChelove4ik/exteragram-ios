package com.exteragram.messenger.adblock.interop;

import com.exteragram.messenger.adblock.backend.ScriptletsManager;
import com.exteragram.messenger.adblock.backend.SubscriptionsManager;
import com.exteragram.messenger.adblock.data.BlockResult;
import com.exteragram.messenger.adblock.data.UrlCosmeticResources;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
public class AdBlock {
    private static volatile long enginePtr;
    private static final ReentrantReadWriteLock lock = null;

    static {
        lock = new ReentrantReadWriteLock();
        enginePtr = 0;
    }

    public AdBlock() {
    }

    public static void applyResources() {
        Collection<ScriptletsManager.Scriptlet> r0 = ScriptletsManager.getInstance().iterScriptlets();
        ReentrantReadWriteLock r1 = lock;
        r1.writeLock().lock();
    L8:
        th = move-exception;
        lock.writeLock().unlock();
        throw th;
    L4:
        if (enginePtr == 0) goto L6;
        useResources(enginePtr, r0);     // Catch: Throwable -> L8
    L6:
        r1.writeLock().unlock();
    }

    public static void build() {
        List<String> r0 = SubscriptionsManager.getInstance().getSubscriptionFilePaths();
        if (r0.isEmpty() == false) goto L5;
        return;
    L5:
        long r1 = NativeAdBlock.createFilterSet(new String[0]);
        Iterator<String> r02 = r0.iterator();
    L7:
        if (r02.hasNext() == false) goto L9;
        NativeAdBlock.addFilters(r1, r02.next());
        goto L7
    L9:
        long r03 = NativeAdBlock.createEngine(r1);
        useResources(r03, ScriptletsManager.getInstance().iterScriptlets());
        swap(r03);
    }

    public static void destroy() {
        swap(0);
    }

    public static BlockResult getBlockResult(String r5, String r6, String r7) {
        ReentrantReadWriteLock r0 = lock;
        r0.readLock().lock();
    L10:
        th = move-exception;
        lock.readLock().unlock();
        throw th;
    L4:
        if (enginePtr == 0) goto L7;
        BlockResult r52 = NativeAdBlock.shouldBlock(enginePtr, r5, r6, r7);     // Catch: Throwable -> L10
    L8:
        r0.readLock().unlock();
        return r52;
    L7:
        r52 = null;
        goto L8
    }

    public static UrlCosmeticResources getCosmeticResources(String r5) {
        ReentrantReadWriteLock r0 = lock;
        r0.writeLock().lock();
    L10:
        th = move-exception;
        lock.writeLock().unlock();
        throw th;
    L4:
        if (enginePtr == 0) goto L7;
        UrlCosmeticResources r52 = NativeAdBlock.getCosmeticResources(enginePtr, r5);     // Catch: Throwable -> L10
    L8:
        r0.writeLock().unlock();
        return r52;
    L7:
        r52 = null;
        goto L8
    }

    public static String[] getHiddenSelectors(String[] r5, String[] r6, String[] r7) {
        ReentrantReadWriteLock r0 = lock;
        r0.writeLock().lock();
    L10:
        th = move-exception;
        lock.writeLock().unlock();
        throw th;
    L4:
        if (enginePtr == 0) goto L7;
        String[] r52 = NativeAdBlock.getHiddenSelectors(enginePtr, r5, r6, r7);     // Catch: Throwable -> L10
    L8:
        r0.writeLock().unlock();
        return r52;
    L7:
        r52 = null;
        goto L8
    }

    public static boolean isReady() {
        if (enginePtr == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private static void swap(long r3) {
        ReentrantReadWriteLock r0 = lock;
        r0.writeLock().lock();
        long r1 = enginePtr;     // Catch: Throwable -> L8
        enginePtr = r3;     // Catch: Throwable -> L8
        r0.writeLock().unlock();
        if (r1 == 0) goto L13;
        NativeAdBlock.destroyEngine(r1);
        return;
    L13:
        return;
    L8:
        th = move-exception;
        lock.writeLock().unlock();
        throw th;
    }

    private static void useResources(long r9, Collection<ScriptletsManager.Scriptlet> r11) {
        int r0 = r11.size();
        String[] r3 = new String[r0];
        String[][] r4 = new String[r0][];
        String[] r5 = new String[r0];
        String[] r6 = new String[r0];
        Iterator<ScriptletsManager.Scriptlet> r112 = r11.iterator();
        int r1 = 0;
    L4:
        if (r112.hasNext() == false) goto L10;
        ScriptletsManager.Scriptlet r2 = r112.next();
        r3[r1] = r2.filename;
        List<String> r7 = r2.aliases;
        if (r7 == null) goto L8;
        String[] r72 = (String[]) r7.toArray(new String[0]);
    L9:
        r4[r1] = r72;
        r5[r1] = ScriptletsManager.getExtension(r2.filename);
        r6[r1] = r2.content;
        r1 = r1 + 1;
        goto L4
    L8:
        r72 = new String[0];
        goto L9
    L10:
        NativeAdBlock.useResources(r9, r3, r4, r5, r6);
    }
}
