package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
public abstract class zzjm {
    public static final String[] zza = null;
    public static final String[] zzb = null;
    public static final String[] zzc = null;
    public static final String[] zzd = null;
    public static final String[] zze = null;
    public static final String[] zzf = null;
    public static final String[] zzg = null;

    static {
        zza = new String[]{"ad_activeview", "ad_click", "ad_exposure", "ad_query", "ad_reward", "adunit_exposure", "app_clear_data", "app_exception", "app_remove", "app_store_refund", "app_store_subscription_cancel", "app_store_subscription_convert", "app_store_subscription_renew", "app_upgrade", "app_update", "ga_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "session_start_with_rollout", "user_engagement", "ad_impression", "screen_view", "ga_extra_parameter", "app_background", "firebase_campaign"};
        zzb = new String[]{"ad_impression"};
        zzc = new String[]{"ad_impression", "in_app_purchase"};
        zzd = new String[]{"ad_impression"};
        zze = new String[]{"ad_impression", "in_app_purchase"};
        zzf = new String[]{"_aa", "_ac", "_xa", "_aq", "_ar", "_xu", "_cd", "_ae", "_ui", "app_store_refund", "app_store_subscription_cancel", "app_store_subscription_convert", "app_store_subscription_renew", "_ug", "_au", "_cmp", "_err", "_f", "_v", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_ssr", "_e", "_ai", "_vs", "_ep", "_ab", "_cmp"};
        zzg = new String[]{"purchase", "refund", "add_payment_info", "add_shipping_info", "add_to_cart", "add_to_wishlist", "begin_checkout", "remove_from_cart", "select_item", "select_promotion", "view_cart", "view_item", "view_item_list", "view_promotion", "ecommerce_purchase", "purchase_refund", "set_checkout_option", "checkout_progress", "select_content", "view_search_results"};
    }

    public static String zza(String r2) {
        return zzlt.zzc(r2, zzf, zza);
    }

    public static String zzb(String r2) {
        return zzlt.zzc(r2, zza, zzf);
    }
}
