.class final Lcom/google/android/recaptcha/internal/zzadb;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzade;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzadn;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzadn;[B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzadb;->zza:Lcom/google/android/recaptcha/internal/zzadn;

    return-void
.end method


# virtual methods
.method public final zza(Ljava/lang/String;)Ljava/lang/Object;
    .locals 3

    .line 1
    const-string v0, "GmsCore_OpenSSL"

    const-string v1, "AndroidOpenSSL"

    filled-new-array {v0, v1}, [Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzadf;->zza([Ljava/lang/String;)Ljava/util/List;

    move-result-object v0

    .line 2
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :catch_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/security/Provider;

    :try_start_0
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzadb;->zza:Lcom/google/android/recaptcha/internal/zzadn;

    .line 3
    invoke-interface {v2, p1, v1}, Lcom/google/android/recaptcha/internal/zzadn;->zza(Ljava/lang/String;Ljava/security/Provider;)Ljava/lang/Object;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzadb;->zza:Lcom/google/android/recaptcha/internal/zzadn;

    const/4 v0, 0x0

    .line 4
    invoke-interface {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzadn;->zza(Ljava/lang/String;Ljava/security/Provider;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
