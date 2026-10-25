.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzam;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzl;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;


# direct methods
.method public static zzb(Landroid/os/IBinder;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;
    .locals 2

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 1
    :cond_0
    const-string v0, "com.google.android.apps.aicore.aidl.IAICoreService"

    invoke-interface {p0, v0}, Landroid/os/IBinder;->queryLocalInterface(Ljava/lang/String;)Landroid/os/IInterface;

    move-result-object v0

    instance-of v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;

    if-eqz v1, :cond_1

    .line 2
    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;

    return-object v0

    :cond_1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzal;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzal;-><init>(Landroid/os/IBinder;)V

    return-object v0
.end method
