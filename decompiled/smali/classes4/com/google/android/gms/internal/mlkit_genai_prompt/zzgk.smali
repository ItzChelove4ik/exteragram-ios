.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgk;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;
.source "SourceFile"


# instance fields
.field final synthetic zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;[B)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    invoke-direct {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;)V

    return-void
.end method


# virtual methods
.method public final onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V
    .locals 1

    .line 1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzt()Landroid/content/ComponentName;

    move-result-object v0

    invoke-static {p1, v0}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2
    invoke-static {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzap;->zzb(Landroid/os/IBinder;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaq;

    move-result-object p1

    :try_start_0
    new-instance p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgj;

    .line 3
    invoke-direct {p2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgj;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgk;)V

    invoke-interface {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaq;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzas;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p1

    goto :goto_0

    :catch_1
    move-exception p1

    :goto_0
    const/4 p2, 0x6

    .line 9
    const-string v0, "AiCore service provider communication error."

    .line 4
    invoke-static {p2, v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p1

    .line 5
    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V

    return-void

    .line 3
    :cond_0
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzq()Landroid/content/ComponentName;

    move-result-object v0

    .line 6
    invoke-static {p1, v0}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 7
    invoke-static {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzam;->zzb(Landroid/os/IBinder;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;

    move-result-object p1

    .line 8
    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)V

    return-void

    .line 9
    :cond_1
    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    const-string p2, "AiCore service is not connected. Unknown component "

    invoke-virtual {p2, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x0

    const/4 v0, 0x0

    .line 10
    invoke-static {v0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p1

    .line 9
    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V

    return-void
.end method

.method public final zzb()V
    .locals 3

    .line 1
    new-instance v0, Landroid/content/Intent;

    invoke-direct {v0}, Landroid/content/Intent;-><init>()V

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzt()Landroid/content/ComponentName;

    move-result-object v1

    .line 2
    invoke-virtual {v0, v1}, Landroid/content/Intent;->setComponent(Landroid/content/ComponentName;)Landroid/content/Intent;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgk;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    const/16 v2, 0x259

    .line 3
    :try_start_0
    invoke-super {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzd(Landroid/content/Intent;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zza:Landroid/content/Context;

    .line 4
    invoke-virtual {v0, p0}, Landroid/content/Context;->unbindService(Landroid/content/ServiceConnection;)V

    new-instance v0, Landroid/content/Intent;

    .line 5
    invoke-direct {v0}, Landroid/content/Intent;-><init>()V

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzq()Landroid/content/ComponentName;

    move-result-object v1

    .line 6
    invoke-virtual {v0, v1}, Landroid/content/Intent;->setComponent(Landroid/content/ComponentName;)Landroid/content/Intent;

    .line 7
    invoke-super {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzd(Landroid/content/Intent;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "AiCore service failed to bind to primary or fallback."

    const/4 v1, 0x0

    .line 8
    invoke-static {v2, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object v0

    .line 9
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    goto :goto_1

    :cond_1
    :goto_0
    return-void

    :goto_1
    const-string v1, "AiCore service failed to bind due to SecurityException."

    .line 10
    invoke-static {v2, v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object v0

    .line 11
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgb;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V

    return-void
.end method
