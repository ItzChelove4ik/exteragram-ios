.class public final Lcom/google/android/recaptcha/internal/zzaaf;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzaaf;

.field public static final zzb:Lcom/google/android/recaptcha/internal/zzaaf;

.field public static final zzc:Lcom/google/android/recaptcha/internal/zzaaf;


# instance fields
.field private final zzd:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzaaf;

    const-string v1, "SHA256"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzaaf;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaaf;->zza:Lcom/google/android/recaptcha/internal/zzaaf;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzaaf;

    const-string v1, "SHA384"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzaaf;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaaf;->zzb:Lcom/google/android/recaptcha/internal/zzaaf;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzaaf;

    const-string v1, "SHA512"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzaaf;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaaf;->zzc:Lcom/google/android/recaptcha/internal/zzaaf;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaaf;->zzd:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaaf;->zzd:Ljava/lang/String;

    return-object p0
.end method
