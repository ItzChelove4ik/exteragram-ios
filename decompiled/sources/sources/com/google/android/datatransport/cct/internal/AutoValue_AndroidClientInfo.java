package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AndroidClientInfo;

/* loaded from: classes4.dex */
final class AutoValue_AndroidClientInfo extends AndroidClientInfo {
    private final String applicationBuild;
    private final String country;
    private final String device;
    private final String fingerprint;
    private final String hardware;
    private final String locale;
    private final String manufacturer;
    private final String mccMnc;
    private final String model;
    private final String osBuild;
    private final String product;
    private final Integer sdkVersion;

    /* renamed from: com.google.android.datatransport.cct.internal.AutoValue_AndroidClientInfo$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends AndroidClientInfo.Builder {
        private String applicationBuild;
        private String country;
        private String device;
        private String fingerprint;
        private String hardware;
        private String locale;
        private String manufacturer;
        private String mccMnc;
        private String model;
        private String osBuild;
        private String product;
        private Integer sdkVersion;

        public Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo build() {
            return new AutoValue_AndroidClientInfo(this.sdkVersion, this.model, this.hardware, this.device, this.product, this.osBuild, this.manufacturer, this.fingerprint, this.locale, this.country, this.mccMnc, this.applicationBuild, null);
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setApplicationBuild(String r1) {
            this.applicationBuild = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setCountry(String r1) {
            this.country = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setDevice(String r1) {
            this.device = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setFingerprint(String r1) {
            this.fingerprint = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setHardware(String r1) {
            this.hardware = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setLocale(String r1) {
            this.locale = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setManufacturer(String r1) {
            this.manufacturer = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setMccMnc(String r1) {
            this.mccMnc = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setModel(String r1) {
            this.model = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setOsBuild(String r1) {
            this.osBuild = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setProduct(String r1) {
            this.product = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setSdkVersion(Integer r1) {
            this.sdkVersion = r1;
            return this;
        }
    }

    private AutoValue_AndroidClientInfo(Integer r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12) {
        this.sdkVersion = r1;
        this.model = r2;
        this.hardware = r3;
        this.device = r4;
        this.product = r5;
        this.osBuild = r6;
        this.manufacturer = r7;
        this.fingerprint = r8;
        this.locale = r9;
        this.country = r10;
        this.mccMnc = r11;
        this.applicationBuild = r12;
    }

    public /* synthetic */ AutoValue_AndroidClientInfo(Integer r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, AnonymousClass1 r13) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof AndroidClientInfo) == false) goto L92;
        AndroidClientInfo r52 = (AndroidClientInfo) r5;
        Integer r1 = this.sdkVersion;
        if (r1 != null) goto L13;
        if (r52.getSdkVersion() != null) goto L92;
    L14:
        String r12 = this.model;
        if (r12 != null) goto L20;
        if (r52.getModel() != null) goto L92;
    L21:
        String r13 = this.hardware;
        if (r13 != null) goto L27;
        if (r52.getHardware() != null) goto L92;
    L28:
        String r14 = this.device;
        if (r14 != null) goto L34;
        if (r52.getDevice() != null) goto L92;
    L35:
        String r15 = this.product;
        if (r15 != null) goto L41;
        if (r52.getProduct() != null) goto L92;
    L42:
        String r16 = this.osBuild;
        if (r16 != null) goto L48;
        if (r52.getOsBuild() != null) goto L92;
    L49:
        String r17 = this.manufacturer;
        if (r17 != null) goto L55;
        if (r52.getManufacturer() != null) goto L92;
    L56:
        String r18 = this.fingerprint;
        if (r18 != null) goto L62;
        if (r52.getFingerprint() != null) goto L92;
    L63:
        String r19 = this.locale;
        if (r19 != null) goto L69;
        if (r52.getLocale() != null) goto L92;
    L70:
        String r110 = this.country;
        if (r110 != null) goto L76;
        if (r52.getCountry() != null) goto L92;
    L77:
        String r111 = this.mccMnc;
        if (r111 != null) goto L83;
        if (r52.getMccMnc() != null) goto L92;
    L84:
        String r4 = this.applicationBuild;
        if (r4 != null) goto L90;
        if (r52.getApplicationBuild() != null) goto L92;
    L91:
        return true;
    L90:
        if (r4.equals(r52.getApplicationBuild()) == false) goto L92;
    L83:
        if (r111.equals(r52.getMccMnc()) == false) goto L92;
    L76:
        if (r110.equals(r52.getCountry()) == false) goto L92;
    L69:
        if (r19.equals(r52.getLocale()) == false) goto L92;
    L62:
        if (r18.equals(r52.getFingerprint()) == false) goto L92;
    L55:
        if (r17.equals(r52.getManufacturer()) == false) goto L92;
    L48:
        if (r16.equals(r52.getOsBuild()) == false) goto L92;
    L41:
        if (r15.equals(r52.getProduct()) == false) goto L92;
    L34:
        if (r14.equals(r52.getDevice()) == false) goto L92;
    L27:
        if (r13.equals(r52.getHardware()) == false) goto L92;
    L20:
        if (r12.equals(r52.getModel()) == false) goto L92;
    L13:
        if (r1.equals(r52.getSdkVersion()) == true) goto L14;
    L92:
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getApplicationBuild() {
        return this.applicationBuild;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getCountry() {
        return this.country;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getDevice() {
        return this.device;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getFingerprint() {
        return this.fingerprint;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getHardware() {
        return this.hardware;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getLocale() {
        return this.locale;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getMccMnc() {
        return this.mccMnc;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getModel() {
        return this.model;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getOsBuild() {
        return this.osBuild;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getProduct() {
        return this.product;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public Integer getSdkVersion() {
        return this.sdkVersion;
    }

    public int hashCode() {
        Integer r0 = this.sdkVersion;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = (r02 ^ 1000003) * 1000003;
        String r3 = this.model;
        if (r3 != null) goto L9;
        int r32 = 0;
    L10:
        int r04 = (r03 ^ r32) * 1000003;
        String r33 = this.hardware;
        if (r33 != null) goto L13;
        int r34 = 0;
    L14:
        int r05 = (r04 ^ r34) * 1000003;
        String r35 = this.device;
        if (r35 != null) goto L17;
        int r36 = 0;
    L18:
        int r06 = (r05 ^ r36) * 1000003;
        String r37 = this.product;
        if (r37 != null) goto L21;
        int r38 = 0;
    L22:
        int r07 = (r06 ^ r38) * 1000003;
        String r39 = this.osBuild;
        if (r39 != null) goto L25;
        int r310 = 0;
    L26:
        int r08 = (r07 ^ r310) * 1000003;
        String r311 = this.manufacturer;
        if (r311 != null) goto L29;
        int r312 = 0;
    L30:
        int r09 = (r08 ^ r312) * 1000003;
        String r313 = this.fingerprint;
        if (r313 != null) goto L33;
        int r314 = 0;
    L34:
        int r010 = (r09 ^ r314) * 1000003;
        String r315 = this.locale;
        if (r315 != null) goto L37;
        int r316 = 0;
    L38:
        int r011 = (r010 ^ r316) * 1000003;
        String r317 = this.country;
        if (r317 != null) goto L41;
        int r318 = 0;
    L42:
        int r012 = (r011 ^ r318) * 1000003;
        String r319 = this.mccMnc;
        if (r319 != null) goto L45;
        int r320 = 0;
    L46:
        int r013 = (r012 ^ r320) * 1000003;
        String r4 = this.applicationBuild;
        if (r4 == null) goto L51;
        r1 = r4.hashCode();
    L51:
        return r013 ^ r1;
    L45:
        r320 = r319.hashCode();
        goto L46
    L41:
        r318 = r317.hashCode();
        goto L42
    L37:
        r316 = r315.hashCode();
        goto L38
    L33:
        r314 = r313.hashCode();
        goto L34
    L29:
        r312 = r311.hashCode();
        goto L30
    L25:
        r310 = r39.hashCode();
        goto L26
    L21:
        r38 = r37.hashCode();
        goto L22
    L17:
        r36 = r35.hashCode();
        goto L18
    L13:
        r34 = r33.hashCode();
        goto L14
    L9:
        r32 = r3.hashCode();
        goto L10
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return "AndroidClientInfo{sdkVersion=" + this.sdkVersion + ", model=" + this.model + ", hardware=" + this.hardware + ", device=" + this.device + ", product=" + this.product + ", osBuild=" + this.osBuild + ", manufacturer=" + this.manufacturer + ", fingerprint=" + this.fingerprint + ", locale=" + this.locale + ", country=" + this.country + ", mccMnc=" + this.mccMnc + ", applicationBuild=" + this.applicationBuild + "}";
    }
}
