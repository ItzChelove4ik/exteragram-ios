.class public final Lcom/google/android/recaptcha/internal/zzzr;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzvu;


# static fields
.field private static final zza:Lcom/google/android/recaptcha/internal/zzzr;

.field private static final zzb:Lcom/google/android/recaptcha/internal/zzvp;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    new-instance v0, Lcom/google/android/recaptcha/internal/zzzr;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzzr;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzzr;->zza:Lcom/google/android/recaptcha/internal/zzzr;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzzq;->zza:Lcom/google/android/recaptcha/internal/zzzq;

    const-class v1, Lcom/google/android/recaptcha/internal/zzui;

    const-class v2, Lcom/google/android/recaptcha/internal/zzst;

    invoke-static {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzvp;->zzc(Lcom/google/android/recaptcha/internal/zzvo;Ljava/lang/Class;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvp;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzzr;->zzb:Lcom/google/android/recaptcha/internal/zzvp;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzb()V
    .locals 2

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzux;->zza()Lcom/google/android/recaptcha/internal/zzux;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzzr;->zza:Lcom/google/android/recaptcha/internal/zzzr;

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzux;->zzc(Lcom/google/android/recaptcha/internal/zzvu;)V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzux;->zza()Lcom/google/android/recaptcha/internal/zzux;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzzr;->zzb:Lcom/google/android/recaptcha/internal/zzvp;

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzux;->zzb(Lcom/google/android/recaptcha/internal/zzvp;)V

    return-void
.end method


# virtual methods
.method public final zza()Ljava/lang/Class;
    .locals 0

    const-class p0, Lcom/google/android/recaptcha/internal/zzst;

    return-object p0
.end method
