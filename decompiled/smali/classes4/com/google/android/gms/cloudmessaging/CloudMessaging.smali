.class public abstract Lcom/google/android/gms/cloudmessaging/CloudMessaging;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static getClient(Landroid/content/Context;)Lcom/google/android/gms/cloudmessaging/CloudMessagingClient;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/cloudmessaging/zzm;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/cloudmessaging/zzm;-><init>(Landroid/content/Context;)V

    return-object v0
.end method
