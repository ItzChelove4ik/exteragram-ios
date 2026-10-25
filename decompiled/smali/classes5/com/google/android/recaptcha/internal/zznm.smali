.class public final Lcom/google/android/recaptcha/internal/zznm;
.super Lcom/google/android/recaptcha/internal/zzoa;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzne;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzne;Lcom/google/android/recaptcha/internal/zznf;)V
    .locals 11

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzod;->zza:Lcom/google/android/recaptcha/internal/zzod;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzoe;->zza()Ljava/lang/String;

    move-result-object v4

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaks;->zze()Lcom/google/android/recaptcha/internal/zzahe;

    move-result-object v9

    const/4 v10, 0x0

    const/16 v5, 0x44

    const/16 v6, 0x45

    const/16 v7, 0x46

    const/16 v8, 0x49

    move-object v1, p0

    move-object v2, p1

    move-object v3, p2

    .line 3
    invoke-direct/range {v1 .. v10}, Lcom/google/android/recaptcha/internal/zzoa;-><init>(Lcom/google/android/recaptcha/internal/zzoo;Lcom/google/android/recaptcha/internal/zznf;Ljava/lang/String;IIIILcom/google/android/recaptcha/internal/zzahe;Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    iput-object v2, v1, Lcom/google/android/recaptcha/internal/zznm;->zza:Lcom/google/android/recaptcha/internal/zzne;

    return-void
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzne;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zznm;->zza:Lcom/google/android/recaptcha/internal/zzne;

    return-object p0
.end method
