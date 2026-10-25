package okhttp3.internal.publicsuffix;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.platform.PlatformRegistry;
import okhttp3.internal.url._UrlKt;
import okio.Okio;
import okio.Source;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lokhttp3/internal/publicsuffix/AssetPublicSuffixList;", "Lokhttp3/internal/publicsuffix/BasePublicSuffixList;", "path", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;)V", "getPath", "()Ljava/lang/String;", "listSource", "Lokio/Source;", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class AssetPublicSuffixList extends BasePublicSuffixList {
    public static final Companion Companion = null;
    private static final String PUBLIC_SUFFIX_RESOURCE = null;
    private final String path;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lokhttp3/internal/publicsuffix/AssetPublicSuffixList$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "PUBLIC_SUFFIX_RESOURCE", _UrlKt.FRAGMENT_ENCODE_SET, "getPUBLIC_SUFFIX_RESOURCE", "()Ljava/lang/String;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final String getPUBLIC_SUFFIX_RESOURCE() {
            return AssetPublicSuffixList.access$getPUBLIC_SUFFIX_RESOURCE$cp();
        }
    }

    static {
        Companion = new Companion(null);
        PUBLIC_SUFFIX_RESOURCE = "PublicSuffixDatabase.list";
    }

    public AssetPublicSuffixList() {
        this(null, 1, null);
    }

    public AssetPublicSuffixList(String r1) {
        this.path = r1;
    }

    public /* synthetic */ AssetPublicSuffixList(String r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = PUBLIC_SUFFIX_RESOURCE;
    L5:
        this(r1);
    }

    public static final /* synthetic */ String access$getPUBLIC_SUFFIX_RESOURCE$cp() {
        return PUBLIC_SUFFIX_RESOURCE;
    }

    @Override // okhttp3.internal.publicsuffix.BasePublicSuffixList
    public /* bridge */ /* synthetic */ Object getPath() {
        return getPath();
    }

    @Override // okhttp3.internal.publicsuffix.BasePublicSuffixList
    public String getPath() {
        return this.path;
    }

    @Override // okhttp3.internal.publicsuffix.BasePublicSuffixList
    public Source listSource() {
        Context r0 = PlatformRegistry.INSTANCE.getApplicationContext();
        if (r0 == null) goto L5;
        AssetManager r02 = r0.getAssets();
    L6:
        if (r02 != null) goto L14;
        if (Build.FINGERPRINT != null) goto L11;
        Model$$ExternalSyntheticBUOutline0.m("Platform applicationContext not initialized. Possibly running Android unit test without Robolectric. Android tests should run with Robolectric and call OkHttp.initialize before test");
        return null;
    L11:
        Model$$ExternalSyntheticBUOutline0.m("Platform applicationContext not initialized. Startup Initializer possibly disabled, call OkHttp.initialize before test.");
        return null;
    L14:
        return Okio.source(r02.open(getPath()));
    L5:
        r02 = null;
        goto L6
    }
}
