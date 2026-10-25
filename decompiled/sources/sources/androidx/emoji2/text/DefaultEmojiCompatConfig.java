package androidx.emoji2.text;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Log;
import androidx.core.provider.FontRequest;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class DefaultEmojiCompatConfig {

    public static class DefaultEmojiCompatConfigFactory {
        private final DefaultEmojiCompatConfigHelper mHelper;

        public DefaultEmojiCompatConfigFactory(DefaultEmojiCompatConfigHelper r1) {
            if (r1 != null) goto L6;
            r1 = getHelperForApi();
        L6:
            this.mHelper = r1;
        }

        private EmojiCompat.Config configOrNull(Context r1, FontRequest r2) {
            if (r2 != null) goto L6;
            return null;
        L6:
            return new FontRequestEmojiCompatConfig(r1, r2);
        }

        private List<List<byte[]>> convertToByteArray(Signature[] r4) {
            ArrayList r3 = new ArrayList();
            int r0 = r4.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L6;
            r3.add(r4[r1].toByteArray());
            r1 = r1 + 1;
            goto L3
        L6:
            return Collections.singletonList(r3);
        }

        private FontRequest generateFontRequestFrom(ProviderInfo r3, PackageManager r4) {
            String r0 = r3.authority;
            String r32 = r3.packageName;
            return new FontRequest(r0, r32, "emojicompat-emoji-font", convertToByteArray(this.mHelper.getSigningSignatures(r4, r32)));
        }

        private static DefaultEmojiCompatConfigHelper getHelperForApi() {
            if (Build.VERSION.SDK_INT < 28) goto L7;
            return new DefaultEmojiCompatConfigHelper_API28();
        L7:
            return new DefaultEmojiCompatConfigHelper_API19();
        }

        private boolean hasFlagSystem(ProviderInfo r1) {
            if (r1 == null) goto L8;
            ApplicationInfo r0 = r1.applicationInfo;
            if (r0 != null) goto L6;
            return false;
        L6:
            if ((r0.flags & 1) != 1) goto L11;
            return true;
        L11:
            return false;
        L8:
            return false;
        }

        private ProviderInfo queryDefaultInstalledContentProvider(PackageManager r4) {
            Iterator<ResolveInfo> r42 = this.mHelper.queryIntentContentProviders(r4, new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0).iterator();
        L4:
            if (r42.hasNext() == false) goto L8;
            ResolveInfo r0 = r42.next();
            ProviderInfo r02 = this.mHelper.getProviderInfo(r0);
            if (hasFlagSystem(r02) == false) goto L4;
            return r02;
        L8:
            return null;
        }

        public EmojiCompat.Config create(Context r2) {
            return configOrNull(r2, queryForDefaultFontRequest(r2));
        }

        public FontRequest queryForDefaultFontRequest(Context r3) {
            PackageManager r32 = r3.getPackageManager();
            Preconditions.checkNotNull(r32, "Package manager required to locate emoji font provider");
            ProviderInfo r0 = queryDefaultInstalledContentProvider(r32);
            if (r0 != null) goto L10;
            return null;
        L10:
            return generateFontRequestFrom(r0, r32);
        L7:
            e = move-exception;
            Log.wtf("emoji2.text.DefaultEmojiConfig", e);
            return null;
        }
    }

    public static class DefaultEmojiCompatConfigHelper {
        public DefaultEmojiCompatConfigHelper() {
        }

        public abstract ProviderInfo getProviderInfo(ResolveInfo r1);

        public Signature[] getSigningSignatures(PackageManager r1, String r2) {
            return r1.getPackageInfo(r2, 64).signatures;
        }

        public abstract List<ResolveInfo> queryIntentContentProviders(PackageManager r1, Intent r2, int r3);
    }

    public static class DefaultEmojiCompatConfigHelper_API19 extends DefaultEmojiCompatConfigHelper {
        public DefaultEmojiCompatConfigHelper_API19() {
        }

        @Override // androidx.emoji2.text.DefaultEmojiCompatConfig.DefaultEmojiCompatConfigHelper
        public ProviderInfo getProviderInfo(ResolveInfo r1) {
            return r1.providerInfo;
        }

        @Override // androidx.emoji2.text.DefaultEmojiCompatConfig.DefaultEmojiCompatConfigHelper
        public List<ResolveInfo> queryIntentContentProviders(PackageManager r1, Intent r2, int r3) {
            return r1.queryIntentContentProviders(r2, r3);
        }
    }

    public static class DefaultEmojiCompatConfigHelper_API28 extends DefaultEmojiCompatConfigHelper_API19 {
        public DefaultEmojiCompatConfigHelper_API28() {
        }

        @Override // androidx.emoji2.text.DefaultEmojiCompatConfig.DefaultEmojiCompatConfigHelper
        public Signature[] getSigningSignatures(PackageManager r1, String r2) {
            return r1.getPackageInfo(r2, 64).signatures;
        }
    }

    public static FontRequestEmojiCompatConfig create(Context r2) {
        return (FontRequestEmojiCompatConfig) new DefaultEmojiCompatConfigFactory(null).create(r2);
    }
}
