package androidx.car.app.validation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import android.util.Pair;
import androidx.car.app.HostInfo;
import com.sun.jna.Native$$ExternalSyntheticBUOutline0;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public final class HostValidator {
    public static final HostValidator ALLOW_ALL_HOSTS_VALIDATOR = null;
    private final boolean mAllowAllHosts;
    private final Map<String, List<String>> mAllowedHosts;
    private final Map<String, Pair<Integer, Boolean>> mCallerChecked;
    private final PackageManager mPackageManager;

    public static final class Api28Impl {
        public static PackageInfo getPackageInfo(PackageManager r1, String r2) {
            return r1.getPackageInfo(r2, 134221824);
        }

        public static Signature[] getSignatures(PackageInfo r0) {
            SigningInfo r02 = r0.signingInfo;
            if (r02 != null) goto L7;
            return null;
        L7:
            return r02.getSigningCertificateHistory();
        }
    }

    public static final class Builder {
        private final Map<String, List<String>> mAllowedHosts;
        private final Context mContext;

        public Builder(Context r2) {
            this.mAllowedHosts = new HashMap();
            this.mContext = r2;
        }

        private String cleanUp(String r2) {
            return r2.toLowerCase(Locale.US).replace(" ", _UrlKt.FRAGMENT_ENCODE_SET);
        }

        public Builder addAllowedHost(String r3, String r4) {
            Objects.requireNonNull(r3);
            Objects.requireNonNull(r4);
            List<String> r0 = this.mAllowedHosts.get(r3);
            if (r0 != null) goto L5;
            r0 = new ArrayList();
            this.mAllowedHosts.put(r3, r0);
        L5:
            r0.add(r4);
            return this;
        }

        @SuppressLint({"MissingGetterMatchingBuilder"})
        public Builder addAllowedHosts(int r9) {
            String[] r0 = this.mContext.getResources().getStringArray(r9);
            if (r0 == null) goto L12;
            int r92 = r0.length;
            int r3 = 0;
        L5:
            if (r3 >= r92) goto L11;
            String r4 = r0[r3];
            String[] r5 = r4.split(",", -1);
            if (r5.length != 2) goto L9;
            addAllowedHost(cleanUp(r5[1]), cleanUp(r5[0]));
            r3 = r3 + 1;
            goto L5
        L9:
            Native$$ExternalSyntheticBUOutline0.m("Invalid allowed host entry: '", r4, "'");
            return null;
        L11:
            return this;
        L12:
            CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid allowlist res id: ", r9);
            return null;
        }

        public HostValidator build() {
            return new HostValidator(this.mContext.getPackageManager(), this.mAllowedHosts, false);
        }
    }

    static {
        ALLOW_ALL_HOSTS_VALIDATOR = new HostValidator(null, new HashMap(), true);
    }

    public HostValidator(PackageManager r2, Map<String, List<String>> r3, boolean r4) {
        this.mCallerChecked = new HashMap();
        this.mPackageManager = r2;
        this.mAllowedHosts = r3;
        this.mAllowAllHosts = r4;
    }

    private Boolean checkCache(HostInfo r3) {
        Pair<Integer, Boolean> r2 = this.mCallerChecked.get(r3.getPackageName());
        if (r2 != null) goto L6;
        return null;
    L6:
        if (((Integer) r2.first).intValue() == r3.getUid()) goto L9;
        return null;
    L9:
        return (Boolean) r2.second;
    }

    private String getDigest(Signature r5) {
        byte[] r4 = r5.toByteArray();
        MessageDigest r52 = getMessageDigest();
        if (r52 != null) goto L6;
        return null;
    L6:
        r52.update(r4);
        byte[] r42 = r52.digest();
        StringBuilder r53 = new StringBuilder((r42.length * 3) - 1);
        int r0 = r42.length;
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L10;
        r53.append(String.format("%02x", new Object[]{Byte.valueOf(r42[r1])}));
        r1 = r1 + 1;
        goto L7
    L10:
        return r53.toString();
    }

    private static MessageDigest getMessageDigest() {
        return MessageDigest.getInstance("SHA256");
    L4:
        e = move-exception;
        Log.e("CarApp.Val", "Could not find SHA256 hash algorithm", e);
        return null;
    }

    private PackageInfo getPackageInfo(String r5) {
        PackageManager r4 = this.mPackageManager;     // Catch: PackageManager.NameNotFoundException -> L7
        if (r4 != null) goto L10;
        Log.d("CarApp.Val", "PackageManager is null. Package info cannot be found for package " + r5);     // Catch: PackageManager.NameNotFoundException -> L7
        return null;
    L10:
        if (Build.VERSION.SDK_INT < 28) goto L14;
        return Api28Impl.getPackageInfo(r4, r5);
    L14:
        return r4.getPackageInfo(r5, 4160);
    L7:
        e = move-exception;
        Log.w("CarApp.Val", "Package " + r5 + " not found", e);
        return null;
    }

    private Signature[] getSignatures(PackageInfo r2) {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        Signature[] r1 = r2.signatures;
        if (r1 != null) goto L9;
        return null;
    L9:
        if (r1.length != 1) goto L14;
        return r1;
    L14:
        return null;
    L5:
        return Api28Impl.getSignatures(r2);
    }

    private static boolean hasPermissionGranted(PackageInfo r4, String r5) {
        if (r4.requestedPermissionsFlags != null) goto L5;
    L19:
        return false;
    L5:
        if (r4.requestedPermissions == null) goto L19;
        int r0 = 0;
    L8:
        int[] r2 = r4.requestedPermissionsFlags;
        if (r0 >= r2.length) goto L19;
        if ((r2[r0] & 2) == 0) goto L18;
        String[] r22 = r4.requestedPermissions;
        if (r0 >= r22.length) goto L18;
        if (r5.equals(r22[r0]) == false) goto L18;
        return true;
    L18:
        r0 = r0 + 1;
        goto L8
    }

    private boolean isAllowListed(String r5, Signature[] r6) {
        List<String> r52 = this.mAllowedHosts.get(r5);
        if (r52 != null) goto L5;
        return false;
    L5:
        int r1 = r6.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        if (r52.contains(getDigest(r6[r2])) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    private void updateCache(HostInfo r2, boolean r3) {
        this.mCallerChecked.put(r2.getPackageName(), Pair.create(Integer.valueOf(r2.getUid()), Boolean.valueOf(r3)));
    }

    private boolean validateHost(HostInfo r10) {
        String r0 = r10.getPackageName();
        PackageInfo r1 = getPackageInfo(r0);
        if (r1 != null) goto L6;
        Log.w("CarApp.Val", "Rejected - package name " + r0 + " not found");
        return false;
    L6:
        Signature[] r4 = getSignatures(r1);
        if (r4 != null) goto L9;
    L39:
        Log.w("CarApp.Val", "Package " + r0 + " is not signed or it has more than one signature");
        return false;
    L9:
        if (r4.length == 0) goto L39;
        int r5 = r1.applicationInfo.uid;
        if (r5 != r10.getUid()) goto L38;
        boolean r102 = hasPermissionGranted(r1, "android.car.permission.TEMPLATE_RENDERER");
        boolean r12 = isAllowListed(r0, r4);
        if (r5 == Process.myUid()) goto L16;
        if (r12 == false) goto L25;
        if (Log.isLoggable("CarApp.Val", 3) == false) goto L23;
        Log.d("CarApp.Val", "Accepted - Host in allow-list");
    L23:
        return true;
    L25:
        if (r5 == 1000) goto L27;
        if (r102 == true) goto L32;
        Log.e("CarApp.Val", String.format("Unrecognized host.\nIf this is a valid caller, please add the following to your CarAppService#createHostValidator() implementation:\nreturn new HostValidator.Builder(context)\n\t.addAllowedHost(\"%s\", \"%s\");\n\t.build()", new Object[]{r0, getDigest(r4[0])}));
        return false;
    L32:
        if (Log.isLoggable("CarApp.Val", 3) == false) goto L34;
        Log.d("CarApp.Val", "Accepted - Host has android.car.permission.TEMPLATE_RENDERER");
    L34:
        return true;
    L27:
        if (Log.isLoggable("CarApp.Val", 3) == false) goto L29;
        Log.d("CarApp.Val", "Accepted - System binding");
    L29:
        return true;
    L16:
        if (Log.isLoggable("CarApp.Val", 3) == false) goto L18;
        Log.d("CarApp.Val", "Accepted - Local service call");
    L18:
        return true;
    L38:
        throw new IllegalStateException("Host " + r10 + " doesn't match caller's actual UID " + r5);
    }

    public boolean isValidHost(HostInfo r5) {
        Objects.requireNonNull(r5);
        if (Log.isLoggable("CarApp.Val", 3) == false) goto L6;
        Log.d("CarApp.Val", "Evaluating " + r5);
    L6:
        if (this.mAllowAllHosts == true) goto L8;
        Boolean r0 = checkCache(r5);
        if (r0 != null) goto L15;
        boolean r02 = validateHost(r5);
        updateCache(r5, r02);
        return r02;
    L15:
        return r0.booleanValue();
    L8:
        if (Log.isLoggable("CarApp.Val", 3) == false) goto L18;
        Log.d("CarApp.Val", "Accepted - Validator disabled, all hosts allowed");
        return true;
    L18:
        return true;
    }
}
