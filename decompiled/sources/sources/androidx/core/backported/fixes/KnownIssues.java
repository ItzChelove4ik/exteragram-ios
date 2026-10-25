package androidx.core.backported.fixes;

import android.os.Build;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b6\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/core/backported/fixes/KnownIssues;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "core-backported-fixes"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class KnownIssues {
    public static final Companion Companion = null;

    @JvmField
    public static final KnownIssue KI_350037023 = null;

    @JvmField
    public static final KnownIssue KI_350037348 = null;

    @JvmField
    public static final KnownIssue KI_372917199 = null;

    @JvmField
    public static final KnownIssue KI_398591036 = null;

    @JvmField
    public static final KnownIssue KI_452390376 = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/core/backported/fixes/KnownIssues$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "KI_350037023", "Landroidx/core/backported/fixes/KnownIssue;", "KI_372917199", "KI_350037348", "KI_398591036", "KI_452390376", "core-backported-fixes"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    public static boolean $r8$lambda$RDZfUCNJyzJpznAWFktDw1wVB4A() {
        return Build.BRAND.equals("google");
    }

    public static boolean $r8$lambda$uqVYSlZqGMjwk0Z4TpjJd8NFDew() {
        Build.BRAND.equals("google");
        return SetsKt.setOf(new String[]{"frankel", "blazer", "mustang", "rango"}).contains(Build.PRODUCT);
    }

    public static boolean $r8$lambda$zp4045SeTunE3h5m25FA9Wav5zE() {
        return Build.BRAND.equals("robolectric");
    }

    static {
        Companion = new Companion(null);
        KI_350037023 = new KnownIssue(350037023, 1, null, null, 12, null);
        KI_372917199 = new KnownIssue(372917199, 2, SetsKt.setOf("foo/bar/manually_tested"), new KnownIssues$$ExternalSyntheticLambda0());
        KI_350037348 = new KnownIssue(350037348, 3, null, null, 12, null);
        KI_398591036 = new KnownIssue(398591036, 5, SetsKt.setOf(new String[]{"google/blazer/blazer:16/BD3A.250721.001.B7/13955164:user/release-keys", "google/caiman/caiman:16/BP3A.250905.014/13873947:user/release-keys", "google/comet/comet:16/BP3A.250905.014/13873947:user/release-keys", "google/frankel/frankel:16/BD3A.250721.001.B7/13955164:user/release-keys", "google/komodo/komodo:16/BP3A.250905.014/13873947:user/release-keys", "google/mustang/mustang:16/BD3A.250721.001.B7/13955164:user/release-keys", "google/tokay/tokay:16/BP3A.250905.014/13873947:user/release-keys", "google/blazer/blazer:16/BD3A.251005.003.W3/14147046:user/release-keys", "google/blazer/blazer:16/BD3A.251005.003.J5/14147083:user/release-keys", "google/caiman/caiman:16/BP3A.251005.004.B1/14042072:user/release-keys", "google/comet/comet:16/BP3A.251005.004.B1/14042072:user/release-keys", "google/frankel/frankel:16/BD3A.251005.003.W3/14147046:user/release-keys", "google/frankel/frankel:16/BD3A.251005.003.J5/14147083:user/release-keys", "google/komodo/komodo:16/BP3A.251005.004.B1/14042072:user/release-keys", "google/mustang/mustang:16/BD3A.251005.003.J5/14147083:user/release-keys", "google/mustang/mustang:16/BD3A.251005.003.W3/14147046:user/release-keys", "google/rango/rango:16/BD3A.251005.003.W3/14147046:user/release-keys", "google/rango/rango:16/BD3A.251005.003.J5/14147083:user/release-keys", "google/tokay/tokay:16/BP3A.251005.004.B1/14042072:user/release-keys", "google/blazer/blazer:16/BD3A.251105.010.E1/14337626:user/release-keys", "google/blazer/blazer:16/BD3A.251105.010.F1/14341671:user/release-keys", "google/blazer/blazer:16/BD3A.251105.010.J3/14341896:user/release-keys", "google/caiman/caiman:16/BP3A.251105.015/14339231:user/release-keys", "google/comet/comet:16/BP3A.251105.015/14339231:user/release-keys", "google/frankel/frankel:16/BD3A.251105.010.E1/14337626:user/release-keys", "google/frankel/frankel:16/BD3A.251105.010.F1/14341671:user/release-keys", "google/frankel/frankel:16/BD3A.251105.010.J3/14341896:user/release-keys", "google/komodo/komodo:16/BP3A.251105.015/14339231:user/release-keys", "google/mustang/mustang:16/BD3A.251105.010.E1/14337626:user/release-keys", "google/mustang/mustang:16/BD3A.251105.010.F1/14341671:user/release-keys", "google/mustang/mustang:16/BD3A.251105.010.J3/14341896:user/release-keys", "google/rango/rango:16/BD3A.251105.010.E1/14337626:user/release-keys", "google/rango/rango:16/BD3A.251105.010.F1/14341671:user/release-keys", "google/rango/rango:16/BD3A.251105.010.J3/14341896:user/release-keys", "google/tokay/tokay:16/BP3A.251105.015/14339231:user/release-keys", "google/blazer/blazer:16/BD4A.251205.006.A1/14402117:user/release-keys", "google/blazer/blazer:16/BD4A.251205.006/14401865:user/release-keys", "google/blazer/blazer:16/BP4A.251205.006.C1/14402245:user/release-keys", "google/caiman/caiman:16/BP4A.251205.006.A1/14402117:user/release-keys", "google/caiman/caiman:16/BP4A.251205.006/14401865:user/release-keys", "google/comet/comet:16/BD4A.251205.006.A1/14402117:user/release-keys", "google/comet/comet:16/BD4A.251205.006/14401865:user/release-keys", "google/frankel/frankel:16/BD4A.251205.006.A1/14402117:user/release-keys", "google/frankel/frankel:16/BD4A.251205.006/14401865:user/release-keys", "google/frankel/frankel:16/BP4A.251205.006.C1/14402245:user/release-keys", "google/komodo/komodo:16/BP4A.251205.006.A1/14402117:user/release-keys", "google/komodo/komodo:16/BP4A.251205.006/14401865:user/release-keys", "google/mustang/mustang:16/BD4A.251205.006.A1/14402117:user/release-keys", "google/mustang/mustang:16/BD4A.251205.006/14401865:user/release-keys", "google/mustang/mustang:16/BP4A.251205.006.C1/14402245:user/release-keys", "google/rango/rango:16/BD4A.251205.006.A1/14402117:user/release-keys", "google/rango/rango:16/BP4A.251205.006.C1/14402245:user/release-keys", "google/rango/rango:16/BD4A.251205.006/14401865:user/release-keys", "google/tokay/tokay:16/BP4A.251205.006.A1/14402117:user/release-keys", "google/tokay/tokay:16/BP4A.251205.006/14401865:user/release-keys"}), new KnownIssues$$ExternalSyntheticLambda1());
        KI_452390376 = new KnownIssue(452390376, 6, null, new KnownIssues$$ExternalSyntheticLambda2(), 4, null);
    }
}
