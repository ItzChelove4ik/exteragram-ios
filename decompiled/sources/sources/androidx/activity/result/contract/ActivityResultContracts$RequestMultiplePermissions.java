package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u00172%\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0003\u0012\t\u0012\u00070\u0005¢\u0006\u0002\b\u00060\u00040\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0007\u0010\bJ%\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ9\u0010\u0010\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u000f2\u0006\u0010\n\u001a\u00020\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J-\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"androidx/activity/result/contract/ActivityResultContracts$RequestMultiplePermissions", "Landroidx/activity/result/contract/ActivityResultContract;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/jvm/JvmSuppressWildcards;", "<init>", "()V", "Landroid/content/Context;", "context", PluginsConstants.Settings.TYPE_INPUT, "Landroid/content/Intent;", "createIntent", "(Landroid/content/Context;[Ljava/lang/String;)Landroid/content/Intent;", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "getSynchronousResult", "(Landroid/content/Context;[Ljava/lang/String;)Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", _UrlKt.FRAGMENT_ENCODE_SET, "resultCode", "intent", "parseResult", "(ILandroid/content/Intent;)Ljava/util/Map;", "Companion", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nActivityResultContracts.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ActivityResultContracts.kt\nandroidx/activity/result/contract/ActivityResultContracts$RequestMultiplePermissions\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,959:1\n12541#2,2:960\n8676#2,2:962\n9358#2,4:964\n11365#2:968\n11700#2,3:969\n*S KotlinDebug\n*F\n+ 1 ActivityResultContracts.kt\nandroidx/activity/result/contract/ActivityResultContracts$RequestMultiplePermissions\n*L\n188#1:960,2\n195#1:962,2\n195#1:964,4\n208#1:968\n208#1:969,3\n*E\n"})
/* loaded from: classes.dex */
public final class ActivityResultContracts$RequestMultiplePermissions extends ActivityResultContract<String[], Map<String, Boolean>> {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nH\u0000¢\u0006\u0004\b\u000b\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$RequestMultiplePermissions$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "ACTION_REQUEST_PERMISSIONS", _UrlKt.FRAGMENT_ENCODE_SET, "EXTRA_PERMISSIONS", "EXTRA_PERMISSION_GRANT_RESULTS", "createIntent", "Landroid/content/Intent;", PluginsConstants.Settings.TYPE_INPUT, _UrlKt.FRAGMENT_ENCODE_SET, "createIntent$activity_release", "([Ljava/lang/String;)Landroid/content/Intent;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Intent createIntent$activity_release(String[] r2) {
            return new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", r2);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public ActivityResultContracts$RequestMultiplePermissions() {
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public /* bridge */ /* synthetic */ Intent createIntent(Context r1, String[] r2) {
        return createIntent2(r1, r2);
    }

    /* renamed from: createIntent, reason: avoid collision after fix types in other method */
    public Intent createIntent2(Context r1, String[] r2) {
        return Companion.createIntent$activity_release(r2);
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public /* bridge */ /* synthetic */ ActivityResultContract.SynchronousResult<Map<String, Boolean>> getSynchronousResult(Context r1, String[] r2) {
        return getSynchronousResult2(r1, r2);
    }

    /* renamed from: getSynchronousResult, reason: avoid collision after fix types in other method */
    public ActivityResultContract.SynchronousResult<Map<String, Boolean>> getSynchronousResult2(Context r4, String[] r5) {
        if (r5.length == 0) goto L5;
        int r3 = r5.length;
        int r0 = 0;
        int r1 = 0;
    L7:
        if (r1 >= r3) goto L13;
        if (ContextCompat.checkSelfPermission(r4, r5[r1]) != 0) goto L11;
        r1 = r1 + 1;
        goto L7
    L11:
        return null;
    L13:
        LinkedHashMap r42 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(r5.length), 16));
        int r32 = r5.length;
    L14:
        if (r0 >= r32) goto L17;
        Pair r12 = TuplesKt.to(r5[r0], Boolean.TRUE);
        r42.put(r12.getFirst(), r12.getSecond());
        r0 = r0 + 1;
        goto L14
    L17:
        return new ActivityResultContract.SynchronousResult(r42);
    L5:
        return new ActivityResultContract.SynchronousResult(MapsKt.emptyMap());
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public /* bridge */ /* synthetic */ Map<String, Boolean> parseResult(int r1, Intent r2) {
        return parseResult2(r1, r2);
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    /* renamed from: parseResult, reason: avoid collision after fix types in other method */
    public Map<String, Boolean> parseResult2(int r5, Intent r6) {
        if (r5 != (-1)) goto L5;
        if (r6 == null) goto L8;
        String[] r4 = r6.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
        int[] r52 = r6.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
        if (r52 == null) goto L23;
        if (r4 == null) goto L23;
        ArrayList r62 = new ArrayList(r52.length);
        int r0 = r52.length;
        int r2 = 0;
    L14:
        if (r2 >= r0) goto L21;
        if (r52[r2] != 0) goto L18;
        boolean r3 = true;
    L19:
        r62.add(Boolean.valueOf(r3));
        r2 = r2 + 1;
        goto L14
    L18:
        r3 = false;
        goto L19
    L21:
        return MapsKt.toMap(CollectionsKt.zip(ArraysKt.filterNotNull(r4), r62));
    L23:
        return MapsKt.emptyMap();
    L8:
        return MapsKt.emptyMap();
    L5:
        return MapsKt.emptyMap();
    }
}
