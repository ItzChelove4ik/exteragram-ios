.class public final Lcom/google/android/recaptcha/internal/zzha;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzgz;


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaau;->zza()V

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzalm;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 7

    const/4 p0, 0x0

    .line 1
    invoke-static {p2, p0}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object p0

    .line 2
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsz;->zza([B)Lcom/google/android/recaptcha/internal/zzsj;

    move-result-object p0

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zztg;->zza()Lcom/google/android/recaptcha/internal/zzsc;

    move-result-object p2

    const-class p3, Lcom/google/android/recaptcha/internal/zzst;

    invoke-virtual {p0, p2, p3}, Lcom/google/android/recaptcha/internal/zzsj;->zzg(Lcom/google/android/recaptcha/internal/zzsc;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    .line 4
    check-cast p0, Lcom/google/android/recaptcha/internal/zzst;

    .line 5
    :try_start_0
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzalm;->zzb()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p2

    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p2

    .line 6
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzalm;->zza()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p1

    .line 7
    invoke-interface {p0, p2, p1}, Lcom/google/android/recaptcha/internal/zzst;->zza([B[B)V
    :try_end_0
    .catch Ljava/security/GeneralSecurityException; {:try_start_0 .. :try_end_0} :catch_0

    .line 9
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 8
    :catch_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzan:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0
.end method
