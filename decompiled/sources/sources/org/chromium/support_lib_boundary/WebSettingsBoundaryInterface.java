package org.chromium.support_lib_boundary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.util.Map;

/* loaded from: classes5.dex */
public interface WebSettingsBoundaryInterface {

    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributionBehavior {
        public static final int APP_SOURCE_AND_APP_TRIGGER = 3;
        public static final int APP_SOURCE_AND_WEB_TRIGGER = 1;
        public static final int DISABLED = 0;
        public static final int WEB_SOURCE_AND_WEB_TRIGGER = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ForceDarkBehavior {
        public static final int FORCE_DARK_ONLY = 0;
        public static final int MEDIA_QUERY_ONLY = 1;
        public static final int PREFER_MEDIA_QUERY_OVER_FORCE_DARK = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HyperlinkContextMenuItems {
        public static final int COPY_LINK_ADDRESS = 1;
        public static final int COPY_LINK_TEXT = 2;
        public static final int DISABLED = 0;
        public static final int OPEN_LINK = 4;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SpeculativeLoadingStatus {
        public static final int DISABLED = 0;
        public static final int PRERENDER_ENABLED = 1;
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WebViewMediaIntegrityApiStatus {
        public static final int DISABLED = 0;
        public static final int ENABLED = 2;
        public static final int ENABLED_WITHOUT_APP_IDENTITY = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WebauthnSupport {
        public static final int APP = 1;
        public static final int BROWSER = 2;
        public static final int NONE = 0;
    }

    int getAttributionBehavior();

    boolean getBackForwardCacheEnabled();

    InvocationHandler getBackForwardCacheSettings();

    boolean getBackForwardCacheSettingsKeepForwardEntries();

    int getBackForwardCacheSettingsMaxPagesInCache();

    long getBackForwardCacheSettingsTimeout();

    int getDisabledActionModeMenuItems();

    boolean getDownloadFaviconsEnabled();

    boolean getEnterpriseAuthenticationAppLinkPolicyEnabled();

    int getForceDark();

    int getForceDarkBehavior();

    boolean getHasEnrolledInstrumentEnabled();

    boolean getIncludeCookiesOnIntercept();

    boolean getOffscreenPreRaster();

    boolean getPaymentRequestEnabled();

    boolean getSafeBrowsingEnabled();

    int getSpeculativeLoadingStatus();

    Map<String, Object> getUserAgentMetadataMap();

    int getWebViewMediaIntegrityApiDefaultStatus();

    Map<String, Integer> getWebViewMediaIntegrityApiOverrideRules();

    int getWebauthnSupport();

    boolean getWillSuppressErrorPage();

    boolean isAlgorithmicDarkeningAllowed();

    void setAlgorithmicDarkeningAllowed(boolean r1);

    void setAttributionBehavior(int r1);

    void setBackForwardCacheEnabled(boolean r1);

    void setBackForwardCacheSettings(InvocationHandler r1);

    void setBackForwardCacheSettingsKeepForwardEntries(boolean r1);

    void setBackForwardCacheSettingsMaxPagesInCache(int r1);

    void setBackForwardCacheSettingsTimeout(long r1);

    void setDisabledActionModeMenuItems(int r1);

    void setDownloadFaviconsEnabled(boolean r1);

    void setEnterpriseAuthenticationAppLinkPolicyEnabled(boolean r1);

    void setForceDark(int r1);

    void setForceDarkBehavior(int r1);

    void setHasEnrolledInstrumentEnabled(boolean r1);

    void setHyperlinkContextMenuItems(int r1);

    void setIncludeCookiesOnIntercept(boolean r1);

    void setOffscreenPreRaster(boolean r1);

    void setPaymentRequestEnabled(boolean r1);

    void setSafeBrowsingEnabled(boolean r1);

    void setSpeculativeLoadingStatus(int r1);

    void setUserAgentMetadataFromMap(Map<String, Object> r1);

    void setWebViewMediaIntegrityApiStatus(int r1, Map<String, Integer> r2);

    void setWebauthnSupport(int r1);

    void setWillSuppressErrorPage(boolean r1);
}
