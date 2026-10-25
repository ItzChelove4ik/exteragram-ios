.class public final Lcom/google/android/recaptcha/internal/zzxv;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzxv;

.field public static final zzb:Lcom/google/android/recaptcha/internal/zzxv;


# instance fields
.field private final zzc:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzxv;

    const-string v1, "IEEE_P1363"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzxv;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzxv;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzxv;

    const-string v1, "DER"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzxv;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzxv;->zzb:Lcom/google/android/recaptcha/internal/zzxv;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxv;->zzc:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzxv;->zzc:Ljava/lang/String;

    return-object p0
.end method
