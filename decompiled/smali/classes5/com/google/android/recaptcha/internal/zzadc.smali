.class final Lcom/google/android/recaptcha/internal/zzadc;
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

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzadc;->zza:Lcom/google/android/recaptcha/internal/zzadn;

    return-void
.end method


# virtual methods
.method public final zza(Ljava/lang/String;)Ljava/lang/Object;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzadc;->zza:Lcom/google/android/recaptcha/internal/zzadn;

    const/4 v0, 0x0

    invoke-interface {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzadn;->zza(Ljava/lang/String;Ljava/security/Provider;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
