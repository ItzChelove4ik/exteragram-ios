package androidx.webkit.internal;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.os.Build;
import androidx.core.content.pm.PackageInfoCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import androidx.webkit.internal.ApiFeature;
import androidx.webkit.internal.StartupApiFeature;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;

@SuppressLint({"UnsafeOptInUsageError"})
/* loaded from: classes4.dex */
public abstract class WebViewFeatureInternal {
    public static final ApiFeature.NoFramework ADD_QUIC_HINTS_V1 = null;
    public static final ApiFeature.T ALGORITHMIC_DARKENING = null;

    @Deprecated
    public static final ApiFeature.NoFrameworkInternal ASYNC_WEBVIEW_STARTUP = null;
    public static final ApiFeature.NoFrameworkInternal ASYNC_WEBVIEW_STARTUP_ASYNC_STARTUP_LOCATIONS = null;
    public static final ApiFeature.NoFrameworkInternal ASYNC_WEBVIEW_STARTUP_V2 = null;
    public static final ApiFeature.NoFramework ATTRIBUTION_REGISTRATION_BEHAVIOR = null;
    public static final ApiFeature.NoFramework BACK_FORWARD_CACHE = null;
    public static final ApiFeature.NoFramework BACK_FORWARD_CACHE_SETTINGS = null;
    public static final ApiFeature.NoFramework BACK_FORWARD_CACHE_SETTINGS_EXPERIMENTAL_V3 = null;
    public static final ApiFeature.NoFramework BACK_FORWARD_CACHE_SETTINGS_EXPERIMENTAL_V4 = null;
    public static final ApiFeature.NoFramework COOKIE_INTERCEPT = null;
    public static final ApiFeature.M CREATE_WEB_MESSAGE_CHANNEL = null;
    public static final ApiFeature.NoFramework CUSTOM_REQUEST_HEADERS = null;
    public static final ApiFeature.NoFramework DEFAULT_TRAFFICSTATS_TAGGING = null;
    public static final ApiFeature.NoFramework DELETE_BROWSING_DATA = null;
    public static final ApiFeature.N DISABLED_ACTION_MODE_MENU_ITEMS = null;
    public static final ApiFeature.NoFramework DOCUMENT_START_SCRIPT = null;
    public static final ApiFeature.NoFramework DOWNLOAD_FAVICONS_ENABLED = null;
    public static final ApiFeature.NoFramework ENQUEUE_PRECONNECT = null;
    public static final ApiFeature.NoFramework ENTERPRISE_AUTHENTICATION_APP_LINK_POLICY = null;
    public static final ApiFeature.Q FORCE_DARK = null;
    public static final ApiFeature.NoFramework FORCE_DARK_STRATEGY = null;
    public static final ApiFeature.NoFramework GET_COOKIE_INFO = null;
    public static final ApiFeature.NoFramework GET_VARIATIONS_HEADER = null;
    public static final ApiFeature.O GET_WEB_CHROME_CLIENT = null;
    public static final ApiFeature.O GET_WEB_VIEW_CLIENT = null;
    public static final ApiFeature.Q GET_WEB_VIEW_RENDERER = null;
    public static final ApiFeature.NoFramework HTTP_CACHE_MANAGER = null;
    public static final ApiFeature.NoFramework HYPERLINK_CONTEXT_MENU_ITEMS = null;
    public static final ApiFeature.NoFramework JS_INJECTION_IN_FRAME_AND_WORLD = null;
    public static final ApiFeature.NoFramework MULTI_PROCESS = null;
    public static final ApiFeature.NoFramework MULTI_PROFILE = null;
    public static final ApiFeature.NoFramework MUTE_AUDIO = null;
    public static final ApiFeature.NoFramework NAVIGATION_GET_WEB_RESOURCE_ERROR = null;
    public static final ApiFeature.NoFramework NAVIGATION_LISTENER = null;
    public static final ApiFeature.M OFF_SCREEN_PRERASTER = null;
    public static final ApiFeature.NoFramework PAYMENT_REQUEST = null;
    public static final ApiFeature.M POST_WEB_MESSAGE = null;
    public static final ApiFeature.NoFramework PRECONNECT = null;
    public static final ApiFeature.NoFramework PREFETCH_CACHE = null;
    public static final ApiFeature.NoFrameworkInternal PREFETCH_WITH_CALLBACK_RESULT = null;
    public static final ApiFeature.NoFramework PRERENDER_WITH_URL = null;
    public static final ApiFeature.NoFramework PROFILE_URL_PREFETCH = null;
    public static final ApiFeature.NoFramework PROVIDER_WEAKLY_REF_WEBVIEW = null;
    public static final ApiFeature.NoFramework PROXY_OVERRIDE = null;
    public static final ApiFeature.NoFramework PROXY_OVERRIDE_REVERSE_BYPASS = null;
    public static final ApiFeature.M RECEIVE_HTTP_ERROR = null;
    public static final ApiFeature.M RECEIVE_WEB_RESOURCE_ERROR = null;

    @Deprecated(forRemoval = true)
    public static final ApiFeature.NoFramework REQUESTED_WITH_HEADER_ALLOW_LIST = null;

    @Deprecated
    public static final ApiFeature.O_MR1 SAFE_BROWSING_ALLOWLIST_DEPRECATED_TO_DEPRECATED = null;

    @Deprecated
    public static final ApiFeature.O_MR1 SAFE_BROWSING_ALLOWLIST_DEPRECATED_TO_PREFERRED = null;
    public static final ApiFeature.O_MR1 SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_DEPRECATED = null;
    public static final ApiFeature.O_MR1 SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_PREFERRED = null;
    public static final ApiFeature.O SAFE_BROWSING_ENABLE = null;
    public static final ApiFeature.O_MR1 SAFE_BROWSING_HIT = null;
    public static final ApiFeature.O_MR1 SAFE_BROWSING_PRIVACY_POLICY_URL = null;
    public static final ApiFeature.O_MR1 SAFE_BROWSING_RESPONSE_BACK_TO_SAFETY = null;
    public static final ApiFeature.O_MR1 SAFE_BROWSING_RESPONSE_PROCEED = null;
    public static final ApiFeature.O_MR1 SAFE_BROWSING_RESPONSE_SHOW_INTERSTITIAL = null;
    public static final ApiFeature.NoFramework SAVE_STATE = null;
    public static final ApiFeature.N SERVICE_WORKER_BASIC_USAGE = null;
    public static final ApiFeature.N SERVICE_WORKER_BLOCK_NETWORK_LOADS = null;
    public static final ApiFeature.N SERVICE_WORKER_CACHE_MODE = null;
    public static final ApiFeature.N SERVICE_WORKER_CONTENT_ACCESS = null;
    public static final ApiFeature.N SERVICE_WORKER_FILE_ACCESS = null;
    public static final ApiFeature.N SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST = null;
    public static final ApiFeature.NoFramework SET_MAX_PRERENDERS = null;
    public static final ApiFeature.N SHOULD_OVERRIDE_WITH_REDIRECTS = null;
    public static final ApiFeature.NoFramework SPECULATIVE_LOADING = null;
    public static final ApiFeature.NoFramework SPECULATIVE_LOADING_CONFIG = null;
    public static final StartupApiFeature.NoFramework STARTUP_FEATURE_CONFIGURE_PARTITIONED_COOKIES = null;
    public static final StartupApiFeature.P STARTUP_FEATURE_SET_DATA_DIRECTORY_SUFFIX = null;
    public static final StartupApiFeature.NoFramework STARTUP_FEATURE_SET_DIRECTORY_BASE_PATH = null;
    public static final StartupApiFeature.NoFramework STARTUP_FEATURE_SET_PROFILES_TO_LOAD = null;

    @Deprecated
    public static final StartupApiFeature.NoFramework STARTUP_FEATURE_SET_UI_THREAD_STARTUP_MODE = null;
    public static final StartupApiFeature.NoFramework STARTUP_FEATURE_SET_UI_THREAD_STARTUP_MODE_V2 = null;
    public static final ApiFeature.O_MR1 START_SAFE_BROWSING = null;
    public static final ApiFeature.P TRACING_CONTROLLER_BASIC_USAGE = null;
    public static final ApiFeature.NoFramework USER_AGENT_METADATA = null;
    public static final ApiFeature.NoFramework USER_AGENT_METADATA_FORM_FACTORS = null;
    public static final ApiFeature.M VISUAL_STATE_CALLBACK = null;
    public static final ApiFeature.NoFramework WARM_UP_RENDERER_PROCESS = null;
    public static final ApiFeature.NoFramework WEBVIEW_BUILDER_V1 = null;
    public static final ApiFeature.NoFramework WEBVIEW_BUILDER_V2 = null;
    public static final ApiFeature.NoFramework WEBVIEW_MEDIA_INTEGRITY_API_STATUS = null;
    public static final ApiFeature.NoFramework WEBVIEW_NAVIGATE_V1 = null;
    public static final ApiFeature.NoFramework WEB_AUTHENTICATION = null;
    public static final ApiFeature.NoFramework WEB_MESSAGE_ARRAY_BUFFER = null;
    public static final ApiFeature.M WEB_MESSAGE_CALLBACK_ON_MESSAGE = null;
    public static final ApiFeature.NoFramework WEB_MESSAGE_LISTENER = null;
    public static final ApiFeature.M WEB_MESSAGE_PORT_CLOSE = null;
    public static final ApiFeature.M WEB_MESSAGE_PORT_POST_MESSAGE = null;
    public static final ApiFeature.M WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK = null;
    public static final ApiFeature.M WEB_RESOURCE_ERROR_GET_CODE = null;
    public static final ApiFeature.M WEB_RESOURCE_ERROR_GET_DESCRIPTION = null;
    public static final ApiFeature.N WEB_RESOURCE_REQUEST_IS_REDIRECT = null;
    public static final ApiFeature.Q WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE = null;
    public static final ApiFeature.Q WEB_VIEW_RENDERER_TERMINATE = null;

    /* renamed from: androidx.webkit.internal.WebViewFeatureInternal$1, reason: invalid class name */
    public class AnonymousClass1 extends ApiFeature.T {
        private final Pattern mVersionPattern;

        public AnonymousClass1(String r1, String r2) {
            super(r1, r2);
            this.mVersionPattern = Pattern.compile("\\A\\d+");
        }

        @Override // androidx.webkit.internal.ApiFeature
        public boolean isSupportedByWebView() {
            boolean r0 = super.isSupportedByWebView();
            if (r0 == true) goto L5;
        L17:
            return r0;
        L5:
            if (Build.VERSION.SDK_INT >= 29) goto L17;
            PackageInfo r02 = WebViewCompat.getCurrentLoadedWebViewPackage();
            if (r02 != null) goto L10;
            return false;
        L10:
            Matcher r3 = this.mVersionPattern.matcher(r02.versionName);
            if (r3.find() == true) goto L13;
        L16:
            return false;
        L13:
            if (Integer.parseInt(r02.versionName.substring(r3.start(), r3.end())) < 105) goto L16;
            return true;
        }
    }

    /* renamed from: androidx.webkit.internal.WebViewFeatureInternal$2, reason: invalid class name */
    public class AnonymousClass2 extends ApiFeature.NoFramework {
        public AnonymousClass2(String r1, String r2) {
            super(r1, r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public boolean isSupportedByWebView() {
            if (super.isSupportedByWebView() == true) goto L5;
            return false;
        L5:
            PackageInfo r5 = WebViewCompat.getCurrentLoadedWebViewPackage();
            if (r5 != null) goto L9;
            return false;
        L9:
            if (PackageInfoCompat.getLongVersionCode(r5) < 636700000) goto L12;
            return true;
        L12:
            return false;
        }
    }

    /* renamed from: androidx.webkit.internal.WebViewFeatureInternal$3, reason: invalid class name */
    public class AnonymousClass3 extends ApiFeature.NoFramework {
        public AnonymousClass3(String r1, String r2) {
            super(r1, r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public boolean isSupportedByWebView() {
            if (super.isSupportedByWebView() == true) goto L6;
            return false;
        L6:
            if (WebViewFeature.isFeatureSupported("MULTI_PROCESS") == true) goto L8;
            return false;
        L8:
            return WebViewCompat.isMultiProcessEnabled();
        }
    }

    /* renamed from: androidx.webkit.internal.WebViewFeatureInternal$4, reason: invalid class name */
    public class AnonymousClass4 extends ApiFeature.NoFramework {
        public AnonymousClass4(String r1, String r2) {
            super(r1, r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public boolean isSupportedByWebView() {
            if (WebViewFeature.isFeatureSupported("MULTI_PROFILE") == true) goto L7;
            return false;
        L7:
            return super.isSupportedByWebView();
        }
    }

    static {
        VISUAL_STATE_CALLBACK = new ApiFeature.M("VISUAL_STATE_CALLBACK", "VISUAL_STATE_CALLBACK");
        OFF_SCREEN_PRERASTER = new ApiFeature.M("OFF_SCREEN_PRERASTER", "OFF_SCREEN_PRERASTER");
        SAFE_BROWSING_ENABLE = new ApiFeature.O("SAFE_BROWSING_ENABLE", "SAFE_BROWSING_ENABLE");
        DISABLED_ACTION_MODE_MENU_ITEMS = new ApiFeature.N("DISABLED_ACTION_MODE_MENU_ITEMS", "DISABLED_ACTION_MODE_MENU_ITEMS");
        START_SAFE_BROWSING = new ApiFeature.O_MR1("START_SAFE_BROWSING", "START_SAFE_BROWSING");
        SAFE_BROWSING_ALLOWLIST_DEPRECATED_TO_DEPRECATED = new ApiFeature.O_MR1("SAFE_BROWSING_WHITELIST", "SAFE_BROWSING_WHITELIST");
        SAFE_BROWSING_ALLOWLIST_DEPRECATED_TO_PREFERRED = new ApiFeature.O_MR1("SAFE_BROWSING_WHITELIST", "SAFE_BROWSING_ALLOWLIST");
        SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_DEPRECATED = new ApiFeature.O_MR1("SAFE_BROWSING_ALLOWLIST", "SAFE_BROWSING_WHITELIST");
        SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_PREFERRED = new ApiFeature.O_MR1("SAFE_BROWSING_ALLOWLIST", "SAFE_BROWSING_ALLOWLIST");
        SAFE_BROWSING_PRIVACY_POLICY_URL = new ApiFeature.O_MR1("SAFE_BROWSING_PRIVACY_POLICY_URL", "SAFE_BROWSING_PRIVACY_POLICY_URL");
        SERVICE_WORKER_BASIC_USAGE = new ApiFeature.N("SERVICE_WORKER_BASIC_USAGE", "SERVICE_WORKER_BASIC_USAGE");
        SERVICE_WORKER_CACHE_MODE = new ApiFeature.N("SERVICE_WORKER_CACHE_MODE", "SERVICE_WORKER_CACHE_MODE");
        SERVICE_WORKER_CONTENT_ACCESS = new ApiFeature.N("SERVICE_WORKER_CONTENT_ACCESS", "SERVICE_WORKER_CONTENT_ACCESS");
        SERVICE_WORKER_FILE_ACCESS = new ApiFeature.N("SERVICE_WORKER_FILE_ACCESS", "SERVICE_WORKER_FILE_ACCESS");
        SERVICE_WORKER_BLOCK_NETWORK_LOADS = new ApiFeature.N("SERVICE_WORKER_BLOCK_NETWORK_LOADS", "SERVICE_WORKER_BLOCK_NETWORK_LOADS");
        SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST = new ApiFeature.N("SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST", "SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST");
        RECEIVE_WEB_RESOURCE_ERROR = new ApiFeature.M("RECEIVE_WEB_RESOURCE_ERROR", "RECEIVE_WEB_RESOURCE_ERROR");
        RECEIVE_HTTP_ERROR = new ApiFeature.M("RECEIVE_HTTP_ERROR", "RECEIVE_HTTP_ERROR");
        SHOULD_OVERRIDE_WITH_REDIRECTS = new ApiFeature.N("SHOULD_OVERRIDE_WITH_REDIRECTS", "SHOULD_OVERRIDE_WITH_REDIRECTS");
        SAFE_BROWSING_HIT = new ApiFeature.O_MR1("SAFE_BROWSING_HIT", "SAFE_BROWSING_HIT");
        WEB_RESOURCE_REQUEST_IS_REDIRECT = new ApiFeature.N("WEB_RESOURCE_REQUEST_IS_REDIRECT", "WEB_RESOURCE_REQUEST_IS_REDIRECT");
        WEB_RESOURCE_ERROR_GET_DESCRIPTION = new ApiFeature.M("WEB_RESOURCE_ERROR_GET_DESCRIPTION", "WEB_RESOURCE_ERROR_GET_DESCRIPTION");
        WEB_RESOURCE_ERROR_GET_CODE = new ApiFeature.M("WEB_RESOURCE_ERROR_GET_CODE", "WEB_RESOURCE_ERROR_GET_CODE");
        SAFE_BROWSING_RESPONSE_BACK_TO_SAFETY = new ApiFeature.O_MR1("SAFE_BROWSING_RESPONSE_BACK_TO_SAFETY", "SAFE_BROWSING_RESPONSE_BACK_TO_SAFETY");
        SAFE_BROWSING_RESPONSE_PROCEED = new ApiFeature.O_MR1("SAFE_BROWSING_RESPONSE_PROCEED", "SAFE_BROWSING_RESPONSE_PROCEED");
        SAFE_BROWSING_RESPONSE_SHOW_INTERSTITIAL = new ApiFeature.O_MR1("SAFE_BROWSING_RESPONSE_SHOW_INTERSTITIAL", "SAFE_BROWSING_RESPONSE_SHOW_INTERSTITIAL");
        WEB_MESSAGE_PORT_POST_MESSAGE = new ApiFeature.M("WEB_MESSAGE_PORT_POST_MESSAGE", "WEB_MESSAGE_PORT_POST_MESSAGE");
        WEB_MESSAGE_PORT_CLOSE = new ApiFeature.M("WEB_MESSAGE_PORT_CLOSE", "WEB_MESSAGE_PORT_CLOSE");
        WEB_MESSAGE_ARRAY_BUFFER = new ApiFeature.NoFramework("WEB_MESSAGE_ARRAY_BUFFER", "WEB_MESSAGE_ARRAY_BUFFER");
        WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK = new ApiFeature.M("WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK", "WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK");
        CREATE_WEB_MESSAGE_CHANNEL = new ApiFeature.M("CREATE_WEB_MESSAGE_CHANNEL", "CREATE_WEB_MESSAGE_CHANNEL");
        POST_WEB_MESSAGE = new ApiFeature.M("POST_WEB_MESSAGE", "POST_WEB_MESSAGE");
        WEB_MESSAGE_CALLBACK_ON_MESSAGE = new ApiFeature.M("WEB_MESSAGE_CALLBACK_ON_MESSAGE", "WEB_MESSAGE_CALLBACK_ON_MESSAGE");
        GET_WEB_VIEW_CLIENT = new ApiFeature.O("GET_WEB_VIEW_CLIENT", "GET_WEB_VIEW_CLIENT");
        GET_WEB_CHROME_CLIENT = new ApiFeature.O("GET_WEB_CHROME_CLIENT", "GET_WEB_CHROME_CLIENT");
        GET_WEB_VIEW_RENDERER = new ApiFeature.Q("GET_WEB_VIEW_RENDERER", "GET_WEB_VIEW_RENDERER");
        WEB_VIEW_RENDERER_TERMINATE = new ApiFeature.Q("WEB_VIEW_RENDERER_TERMINATE", "WEB_VIEW_RENDERER_TERMINATE");
        TRACING_CONTROLLER_BASIC_USAGE = new ApiFeature.P("TRACING_CONTROLLER_BASIC_USAGE", "TRACING_CONTROLLER_BASIC_USAGE");
        STARTUP_FEATURE_SET_DATA_DIRECTORY_SUFFIX = new StartupApiFeature.P("STARTUP_FEATURE_SET_DATA_DIRECTORY_SUFFIX", "STARTUP_FEATURE_SET_DATA_DIRECTORY_SUFFIX");
        STARTUP_FEATURE_SET_DIRECTORY_BASE_PATH = new StartupApiFeature.NoFramework("STARTUP_FEATURE_SET_DIRECTORY_BASE_PATHS", "STARTUP_FEATURE_SET_DIRECTORY_BASE_PATH");
        STARTUP_FEATURE_CONFIGURE_PARTITIONED_COOKIES = new StartupApiFeature.NoFramework("STARTUP_FEATURE_CONFIGURE_PARTITIONED_COOKIES", "STARTUP_FEATURE_CONFIGURE_PARTITIONED_COOKIES");
        WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE = new ApiFeature.Q("WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE", "WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE");
        ALGORITHMIC_DARKENING = new AnonymousClass1("ALGORITHMIC_DARKENING", "ALGORITHMIC_DARKENING");
        PROXY_OVERRIDE = new ApiFeature.NoFramework("PROXY_OVERRIDE", "PROXY_OVERRIDE:3");
        MULTI_PROCESS = new ApiFeature.NoFramework("MULTI_PROCESS", "MULTI_PROCESS_QUERY");
        FORCE_DARK = new ApiFeature.Q("FORCE_DARK", "FORCE_DARK");
        FORCE_DARK_STRATEGY = new ApiFeature.NoFramework("FORCE_DARK_STRATEGY", "FORCE_DARK_BEHAVIOR");
        WEB_MESSAGE_LISTENER = new ApiFeature.NoFramework("WEB_MESSAGE_LISTENER", "WEB_MESSAGE_LISTENER");
        DOCUMENT_START_SCRIPT = new ApiFeature.NoFramework("DOCUMENT_START_SCRIPT", "DOCUMENT_START_SCRIPT:1");
        PROXY_OVERRIDE_REVERSE_BYPASS = new ApiFeature.NoFramework("PROXY_OVERRIDE_REVERSE_BYPASS", "PROXY_OVERRIDE_REVERSE_BYPASS");
        GET_VARIATIONS_HEADER = new ApiFeature.NoFramework("GET_VARIATIONS_HEADER", "GET_VARIATIONS_HEADER");
        ENTERPRISE_AUTHENTICATION_APP_LINK_POLICY = new ApiFeature.NoFramework("ENTERPRISE_AUTHENTICATION_APP_LINK_POLICY", "ENTERPRISE_AUTHENTICATION_APP_LINK_POLICY");
        GET_COOKIE_INFO = new ApiFeature.NoFramework("GET_COOKIE_INFO", "GET_COOKIE_INFO");
        REQUESTED_WITH_HEADER_ALLOW_LIST = new ApiFeature.NoFramework("REQUESTED_WITH_HEADER_ALLOW_LIST", "REQUESTED_WITH_HEADER_ALLOW_LIST");
        USER_AGENT_METADATA = new ApiFeature.NoFramework("USER_AGENT_METADATA", "USER_AGENT_METADATA");
        USER_AGENT_METADATA_FORM_FACTORS = new AnonymousClass2("USER_AGENT_METADATA_FORM_FACTORS", "USER_AGENT_METADATA");
        MULTI_PROFILE = new AnonymousClass3("MULTI_PROFILE", "MULTI_PROFILE");
        ATTRIBUTION_REGISTRATION_BEHAVIOR = new ApiFeature.NoFramework("ATTRIBUTION_REGISTRATION_BEHAVIOR", "ATTRIBUTION_BEHAVIOR");
        WEBVIEW_MEDIA_INTEGRITY_API_STATUS = new ApiFeature.NoFramework("WEBVIEW_MEDIA_INTEGRITY_API_STATUS", "WEBVIEW_INTEGRITY_API_STATUS");
        MUTE_AUDIO = new ApiFeature.NoFramework("MUTE_AUDIO", "MUTE_AUDIO");
        WEB_AUTHENTICATION = new ApiFeature.NoFramework("WEB_AUTHENTICATION", "WEB_AUTHENTICATION");
        SPECULATIVE_LOADING = new ApiFeature.NoFramework("SPECULATIVE_LOADING_STATUS", "SPECULATIVE_LOADING");
        BACK_FORWARD_CACHE = new ApiFeature.NoFramework("BACK_FORWARD_CACHE", "BACK_FORWARD_CACHE");
        BACK_FORWARD_CACHE_SETTINGS = new ApiFeature.NoFramework("BACK_FORWARD_CACHE_SETTINGS", "BACK_FORWARD_CACHE_SETTINGS");
        BACK_FORWARD_CACHE_SETTINGS_EXPERIMENTAL_V3 = new ApiFeature.NoFramework("BACK_FORWARD_CACHE_SETTINGS_EXPERIMENTAL_V3", "BACK_FORWARD_CACHE_SETTINGS_V3");
        BACK_FORWARD_CACHE_SETTINGS_EXPERIMENTAL_V4 = new ApiFeature.NoFramework("BACK_FORWARD_CACHE_SETTINGS_EXPERIMENTAL_V4", "BACK_FORWARD_CACHE_SETTINGS_V4");
        DELETE_BROWSING_DATA = new ApiFeature.NoFramework("DELETE_BROWSING_DATA", "WEB_STORAGE_DELETE_BROWSING_DATA");
        PROFILE_URL_PREFETCH = new AnonymousClass4("PREFETCH_URL_V5", "PREFETCH_URL_V5");
        ASYNC_WEBVIEW_STARTUP_V2 = new ApiFeature.NoFrameworkInternal("ASYNC_WEBVIEW_STARTUP_V2");
        ASYNC_WEBVIEW_STARTUP = new ApiFeature.NoFrameworkInternal("ASYNC_WEBVIEW_STARTUP");
        ASYNC_WEBVIEW_STARTUP_ASYNC_STARTUP_LOCATIONS = new ApiFeature.NoFrameworkInternal("ASYNC_WEBVIEW_STARTUP_ASYNC_STARTUP_LOCATIONS");
        DEFAULT_TRAFFICSTATS_TAGGING = new ApiFeature.NoFramework("DEFAULT_TRAFFICSTATS_TAGGING", "DEFAULT_TRAFFICSTATS_TAGGING");
        PRERENDER_WITH_URL = new ApiFeature.NoFramework("PRERENDER_URL_V2", "PRERENDER_URL_V3");
        SPECULATIVE_LOADING_CONFIG = new ApiFeature.NoFramework("SPECULATIVE_LOADING_CONFIG_V2", "SPECULATIVE_LOADING_CONFIG_V2");
        PREFETCH_CACHE = new ApiFeature.NoFramework("PREFETCH_CACHE_V1", "PREFETCH_CACHE_V1");
        PREFETCH_WITH_CALLBACK_RESULT = new ApiFeature.NoFrameworkInternal("PREFETCH_WITH_CALLBACK_RESULT_V1");
        SET_MAX_PRERENDERS = new ApiFeature.NoFramework("SET_MAX_PRERENDERS_V1", "SET_MAX_PRERENDERS_V1");
        SAVE_STATE = new ApiFeature.NoFramework("SAVE_STATE", "SAVE_STATE");
        NAVIGATION_GET_WEB_RESOURCE_ERROR = new ApiFeature.NoFramework("NAVIGATION_GET_WEB_RESOURCE_ERROR", "NAVIGATION_GET_WEB_RESOURCE_ERROR");
        NAVIGATION_LISTENER = new ApiFeature.NoFramework("NAVIGATION_LISTENER", "PAGE_GET_URL");
        PROVIDER_WEAKLY_REF_WEBVIEW = new ApiFeature.NoFramework("PROVIDER_WEAKLY_REF_WEBVIEW", "PROVIDER_WEAKLY_REF_WEBVIEW");
        PAYMENT_REQUEST = new ApiFeature.NoFramework("PAYMENT_REQUEST", "PAYMENT_REQUEST");
        WEBVIEW_BUILDER_V1 = new ApiFeature.NoFramework("WEBVIEW_BUILDER_EXPERIMENTAL_V1", "WEBVIEW_BUILDER_V1");
        WEBVIEW_BUILDER_V2 = new ApiFeature.NoFramework("WEBVIEW_BUILDER_EXPERIMENTAL_V2", "WEBVIEW_BUILDER_V2");
        COOKIE_INTERCEPT = new ApiFeature.NoFramework("COOKIE_INTERCEPT", "COOKIE_INTERCEPT");
        WARM_UP_RENDERER_PROCESS = new ApiFeature.NoFramework("WARM_UP_RENDERER_PROCESS", "WARM_UP_RENDERER_PROCESS");
        CUSTOM_REQUEST_HEADERS = new ApiFeature.NoFramework("CUSTOM_REQUEST_HEADERS", "CUSTOM_REQUEST_HEADERS");
        STARTUP_FEATURE_SET_PROFILES_TO_LOAD = new StartupApiFeature.NoFramework("STARTUP_FEATURE_SET_PROFILES_TO_LOAD", "STARTUP_FEATURE_SET_PROFILES_TO_LOAD");
        STARTUP_FEATURE_SET_UI_THREAD_STARTUP_MODE = new StartupApiFeature.NoFramework("STARTUP_FEATURE_SET_UI_THREAD_STARTUP_MODE", "STARTUP_FEATURE_SET_UI_THREAD_STARTUP_MODE");
        STARTUP_FEATURE_SET_UI_THREAD_STARTUP_MODE_V2 = new StartupApiFeature.NoFramework("STARTUP_FEATURE_SET_UI_THREAD_STARTUP_MODE_V2", "STARTUP_FEATURE_SET_UI_THREAD_STARTUP_MODE_V2");
        PRECONNECT = new ApiFeature.NoFramework("PRECONNECT", "PRECONNECT");
        ENQUEUE_PRECONNECT = new ApiFeature.NoFramework("ENQUEUE_PRECONNECT", "ENQUEUE_PRECONNECT");
        ADD_QUIC_HINTS_V1 = new ApiFeature.NoFramework("ADD_QUIC_HINTS", "ADD_QUIC_HINTS_V1");
        HYPERLINK_CONTEXT_MENU_ITEMS = new ApiFeature.NoFramework("HYPERLINK_CONTEXT_MENU_ITEMS", "HYPERLINK_CONTEXT_MENU_ITEMS");
        JS_INJECTION_IN_FRAME_AND_WORLD = new ApiFeature.NoFramework("JS_INJECTION_IN_FRAME_AND_WORLD", "JS_INJECTION_IN_FRAME_AND_WORLD");
        WEBVIEW_NAVIGATE_V1 = new ApiFeature.NoFramework("WEBVIEW_NAVIGATE_EXPERIMENTAL_V1", "WEBVIEW_NAVIGATE_V1");
        DOWNLOAD_FAVICONS_ENABLED = new ApiFeature.NoFramework("DOWNLOAD_FAVICONS_ENABLED", "DOWNLOAD_FAVICONS_ENABLED");
        HTTP_CACHE_MANAGER = new ApiFeature.NoFramework("HTTP_CACHE_MANAGER", "HTTP_CACHE_MANAGER");
    }

    public static UnsupportedOperationException getUnsupportedOperationException() {
        return new UnsupportedOperationException("This method is not supported by the current version of the framework and the current WebView APK");
    }

    public static boolean isSupported(String r1) {
        return isSupported(r1, ApiFeature.values());
    }

    public static <T extends ConditionallySupportedFeature> boolean isSupported(String r3, Collection<T> r4) {
        HashSet r0 = new HashSet();
        Iterator<T> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L9;
        T r1 = r42.next();
        if (r1.getPublicFeatureName().equals(r3) == false) goto L4;
        r0.add(r1);
        goto L4
    L9:
        if (r0.isEmpty() == true) goto L18;
        Iterator r32 = r0.iterator();
    L12:
        if (r32.hasNext() == false) goto L17;
        if (((ConditionallySupportedFeature) r32.next()).isSupported() == false) goto L12;
        return true;
    L17:
        return false;
    L18:
        MVEL$$ExternalSyntheticBUOutline0.m("Unknown feature ", r3);
        return false;
    }
}
