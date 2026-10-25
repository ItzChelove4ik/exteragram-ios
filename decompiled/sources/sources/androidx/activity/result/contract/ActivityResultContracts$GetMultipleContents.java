package androidx.activity.result.contract;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00062\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u000f\u0012\r\u0012\t\u0012\u00070\u0004¢\u0006\u0002\b\u00050\u00030\u0001:\u0001\u0006¨\u0006\u0007"}, d2 = {"androidx/activity/result/contract/ActivityResultContracts$GetMultipleContents", "Landroidx/activity/result/contract/ActivityResultContract;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/net/Uri;", "Lkotlin/jvm/JvmSuppressWildcards;", "Companion", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class ActivityResultContracts$GetMultipleContents extends ActivityResultContract<String, List<Uri>> {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0081\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020\u0006H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$GetMultipleContents$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "getClipDataUris", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/net/Uri;", "Landroid/content/Intent;", "getClipDataUris$activity_release", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final List<Uri> getClipDataUris$activity_release(Intent r4) {
            LinkedHashSet r3 = new LinkedHashSet();
            Uri r0 = r4.getData();
            if (r0 == null) goto L5;
            r3.add(r0);
        L5:
            ClipData r42 = r4.getClipData();
            if (r42 == null) goto L8;
        L11:
            if (r42 == null) goto L19;
            int r02 = r42.getItemCount();
            int r1 = 0;
        L13:
            if (r1 >= r02) goto L19;
            Uri r2 = r42.getItemAt(r1).getUri();
            if (r2 == null) goto L17;
            r3.add(r2);
        L17:
            r1 = r1 + 1;
        L19:
            return new ArrayList(r3);
        L8:
            if (r3.isEmpty() == false) goto L11;
            return CollectionsKt.emptyList();
        }
    }

    static {
        Companion = new Companion(null);
    }
}
