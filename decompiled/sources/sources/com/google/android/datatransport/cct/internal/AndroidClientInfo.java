package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_AndroidClientInfo;

/* loaded from: classes4.dex */
public abstract class AndroidClientInfo {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract AndroidClientInfo build();

        public abstract Builder setApplicationBuild(String r1);

        public abstract Builder setCountry(String r1);

        public abstract Builder setDevice(String r1);

        public abstract Builder setFingerprint(String r1);

        public abstract Builder setHardware(String r1);

        public abstract Builder setLocale(String r1);

        public abstract Builder setManufacturer(String r1);

        public abstract Builder setMccMnc(String r1);

        public abstract Builder setModel(String r1);

        public abstract Builder setOsBuild(String r1);

        public abstract Builder setProduct(String r1);

        public abstract Builder setSdkVersion(Integer r1);
    }

    public AndroidClientInfo() {
    }

    public static Builder builder() {
        return new AutoValue_AndroidClientInfo.Builder();
    }

    public abstract String getApplicationBuild();

    public abstract String getCountry();

    public abstract String getDevice();

    public abstract String getFingerprint();

    public abstract String getHardware();

    public abstract String getLocale();

    public abstract String getManufacturer();

    public abstract String getMccMnc();

    public abstract String getModel();

    public abstract String getOsBuild();

    public abstract String getProduct();

    public abstract Integer getSdkVersion();
}
