package androidx.sharetarget;

import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.service.chooser.ChooserTarget;
import android.service.chooser.ChooserTargetService;
import android.util.Log;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.sharetarget.ShareTargetCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class ChooserTargetServiceCompat extends ChooserTargetService {

    public static class ShortcutHolder implements Comparable<ShortcutHolder> {
        private final ShortcutInfoCompat mShortcut;
        private final ComponentName mTargetClass;

        public ShortcutHolder(ShortcutInfoCompat r1, ComponentName r2) {
            this.mShortcut = r1;
            this.mTargetClass = r2;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(ShortcutHolder r1) {
            return getShortcut().getRank() - r1.getShortcut().getRank();
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(ShortcutHolder r1) {
            return compareTo2(r1);
        }

        public ShortcutInfoCompat getShortcut() {
            return this.mShortcut;
        }

        public ComponentName getTargetClass() {
            return this.mTargetClass;
        }
    }

    public ChooserTargetServiceCompat() {
    }

    public static List<ChooserTarget> convertShortcutsToChooserTargets(ShortcutInfoCompatSaverImpl r13, List<ShortcutHolder> r14) {
        if (r14.isEmpty() == true) goto L5;
        Collections.sort(r14);
        ArrayList r1 = new ArrayList();
        int r0 = r14.get(0).getShortcut().getRank();
        Iterator<ShortcutHolder> r142 = r14.iterator();
        float r3 = 1.0f;
        int r2 = r0;
    L8:
        if (r142.hasNext() == false) goto L22;
        ShortcutHolder r4 = r142.next();
        ShortcutInfoCompat r5 = r4.getShortcut();
        Icon r6 = null;
        IconCompat r02 = r13.getShortcutIcon(r5.getId());     // Catch: Exception -> L12
    L14:
        Bundle r12 = new Bundle();
        r12.putString("android.intent.extra.shortcut.ID", r5.getId());
        if (r2 == r5.getRank()) goto L17;
        r3 = r3 - 0.01f;
        r2 = r5.getRank();
    L17:
        float r10 = r3;
        CharSequence r8 = r5.getShortLabel();
        if (r02 == null) goto L21;
        r6 = r02.toIcon();
    L21:
        r1.add(new ChooserTarget(r8, r6, r10, r4.getTargetClass(), r12));
        r3 = r10;
    L12:
        e = move-exception;
        Log.e("ChooserServiceCompat", "Failed to retrieve shortcut icon: ", e);
        r02 = null;
        goto L14
    L22:
        return r1;
    L5:
        return new ArrayList();
    }

    @Override // android.service.chooser.ChooserTargetService
    public List<ChooserTarget> onGetChooserTargets(ComponentName r11, IntentFilter r12) {
        Context r10 = getApplicationContext();
        ArrayList<ShareTargetCompat> r0 = ShareTargetXmlParser.getShareTargets(r10);
        ArrayList r1 = new ArrayList();
        int r2 = r0.size();
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L14;
        ShareTargetCompat r5 = r0.get(r4);
        r4 = r4 + 1;
        ShareTargetCompat r52 = r5;
        if (r52.mTargetClass.equals(r11.getClassName()) == false) goto L3;
        ShareTargetCompat.TargetData[] r6 = r52.mTargetData;
        int r7 = r6.length;
        int r8 = 0;
    L8:
        if (r8 >= r7) goto L3;
        if (r12.hasDataType(r6[r8].mMimeType) == true) goto L11;
        r8 = r8 + 1;
        goto L8
    L11:
        r1.add(r52);
        goto L3
    L14:
        if (r1.isEmpty() == true) goto L16;
        ShortcutInfoCompatSaverImpl r112 = ShortcutInfoCompatSaverImpl.getInstance(r10);
        List<ShortcutInfoCompat> r122 = r112.getShortcuts();     // Catch: Exception -> L35
        if (r122 == null) goto L34;
        if (r122.isEmpty() == true) goto L34;
        ArrayList r02 = new ArrayList();
        Iterator<ShortcutInfoCompat> r123 = r122.iterator();
    L25:
        if (r123.hasNext() == false) goto L32;
        ShortcutInfoCompat r22 = r123.next();
        int r42 = r1.size();
        int r53 = 0;
    L27:
        if (r53 >= r42) goto L25;
        Object r62 = r1.get(r53);
        r53 = r53 + 1;
        ShareTargetCompat r63 = (ShareTargetCompat) r62;
        if (r22.getCategories().containsAll(Arrays.asList(r63.mCategories)) == false) goto L27;
        r02.add(new ShortcutHolder(r22, new ComponentName(r10.getPackageName(), r63.mTargetClass)));
        goto L25
    L32:
        return convertShortcutsToChooserTargets(r112, r02);
    L34:
        return Collections.EMPTY_LIST;
    L35:
        e = move-exception;
        Log.e("ChooserServiceCompat", "Failed to retrieve shortcuts: ", e);
        return Collections.EMPTY_LIST;
    L16:
        return Collections.EMPTY_LIST;
    }
}
