package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
final class zzbd {
    private static final Logger zza = Logger.getLogger(zzbd.class.getName());
    private final ConcurrentMap zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbd() {
        this.zzb = new ConcurrentHashMap();
    }

    private final zzax zzg(String str, Class cls) throws GeneralSecurityException {
        zzbc zzh = zzh(str);
        if (cls == null) {
            return zzh.zzb();
        }
        if (zzh.zze().contains(cls)) {
            return zzh.zza(cls);
        }
        String name = cls.getName();
        String valueOf = String.valueOf(zzh.zzc());
        Set<Class> zze = zzh.zze();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class cls2 : zze) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(cls2.getCanonicalName());
            z = false;
        }
        throw new GeneralSecurityException("Primitive type " + name + " not supported by key manager of type " + valueOf + ", supported primitives: " + sb.toString());
    }

    private final synchronized zzbc zzh(String str) throws GeneralSecurityException {
        if (!this.zzb.containsKey(str)) {
            throw new GeneralSecurityException("No key manager found for key type ".concat(String.valueOf(str)));
        }
        return (zzbc) this.zzb.get(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x005d, code lost:
    
        r6.zzb.putIfAbsent(r0, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0063, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final synchronized void zzi(com.google.android.gms.internal.p002firebaseauthapi.zzbc r7, boolean r8) throws java.security.GeneralSecurityException {
        /*
            r6 = this;
            monitor-enter(r6)
            com.google.android.gms.internal.firebase-auth-api.zzax r0 = r7.zzb()     // Catch: java.lang.Throwable -> L6b
            java.lang.String r0 = r0.zze()     // Catch: java.lang.Throwable -> L6b
            java.util.concurrent.ConcurrentMap r1 = r6.zzb     // Catch: java.lang.Throwable -> L6b
            java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> L6b
            com.google.android.gms.internal.firebase-auth-api.zzbc r1 = (com.google.android.gms.internal.p002firebaseauthapi.zzbc) r1     // Catch: java.lang.Throwable -> L6b
            if (r1 == 0) goto L5b
            java.lang.Class r2 = r1.zzc()     // Catch: java.lang.Throwable -> L6b
            java.lang.Class r3 = r7.zzc()     // Catch: java.lang.Throwable -> L6b
            boolean r2 = r2.equals(r3)     // Catch: java.lang.Throwable -> L6b
            if (r2 == 0) goto L22
            goto L5b
        L22:
            java.util.logging.Logger r8 = com.google.android.gms.internal.p002firebaseauthapi.zzbd.zza     // Catch: java.lang.Throwable -> L6b
            java.util.logging.Level r2 = java.util.logging.Level.WARNING     // Catch: java.lang.Throwable -> L6b
            java.lang.String r3 = "com.google.crypto.tink.KeyManagerRegistry"
            java.lang.String r4 = "registerKeyManagerContainer"
            java.lang.String r5 = "Attempted overwrite of a registered key manager for key type "
            java.lang.String r5 = r5.concat(r0)     // Catch: java.lang.Throwable -> L6b
            r8.logp(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L6b
            java.security.GeneralSecurityException r8 = new java.security.GeneralSecurityException     // Catch: java.lang.Throwable -> L6b
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L6b
            r3 = 0
            r2[r3] = r0     // Catch: java.lang.Throwable -> L6b
            r0 = 1
            java.lang.Class r1 = r1.zzc()     // Catch: java.lang.Throwable -> L6b
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L6b
            r2[r0] = r1     // Catch: java.lang.Throwable -> L6b
            r0 = 2
            java.lang.Class r7 = r7.zzc()     // Catch: java.lang.Throwable -> L6b
            java.lang.String r7 = r7.getName()     // Catch: java.lang.Throwable -> L6b
            r2[r0] = r7     // Catch: java.lang.Throwable -> L6b
            java.lang.String r7 = "typeUrl (%s) is already registered with %s, cannot be re-registered with %s"
            java.lang.String r7 = java.lang.String.format(r7, r2)     // Catch: java.lang.Throwable -> L6b
            r8.<init>(r7)     // Catch: java.lang.Throwable -> L6b
            throw r8     // Catch: java.lang.Throwable -> L6b
        L5b:
            if (r8 != 0) goto L64
            java.util.concurrent.ConcurrentMap r8 = r6.zzb     // Catch: java.lang.Throwable -> L6b
            r8.putIfAbsent(r0, r7)     // Catch: java.lang.Throwable -> L6b
            monitor-exit(r6)
            return
        L64:
            java.util.concurrent.ConcurrentMap r8 = r6.zzb     // Catch: java.lang.Throwable -> L6b
            r8.put(r0, r7)     // Catch: java.lang.Throwable -> L6b
            monitor-exit(r6)
            return
        L6b:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzbd.zzi(com.google.android.gms.internal.firebase-auth-api.zzbc, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public final zzax zza(String str) throws GeneralSecurityException {
        return zzg(str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzax zzb(String str, Class cls) throws GeneralSecurityException {
        return zzg(str, cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzax zzc(String str) throws GeneralSecurityException {
        return zzh(str).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzd(zzgx zzgxVar, zzgc zzgcVar) throws GeneralSecurityException {
        Class zzd;
        int zzf = zzgcVar.zzf();
        if (!zzdv.zza(1)) {
            throw new GeneralSecurityException("failed to register key manager " + String.valueOf(zzgxVar.getClass()) + " as it is not FIPS compatible.");
        }
        if (zzdv.zza(zzf)) {
            String zzd2 = zzgxVar.zzd();
            String zzd3 = zzgcVar.zzd();
            if (this.zzb.containsKey(zzd2) && ((zzbc) this.zzb.get(zzd2)).zzd() != null && (zzd = ((zzbc) this.zzb.get(zzd2)).zzd()) != null && !zzd.getName().equals(zzgcVar.getClass().getName())) {
                zza.logp(Level.WARNING, "com.google.crypto.tink.KeyManagerRegistry", "registerAsymmetricKeyManagers", "Attempted overwrite of a registered key manager for key type " + zzd2 + " with inconsistent public key type " + zzd3);
                throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", zzgxVar.getClass().getName(), zzd.getName(), zzgcVar.getClass().getName()));
            }
            zzi(new zzbb(zzgxVar, zzgcVar), true);
            zzi(new zzba(zzgcVar), false);
        } else {
            throw new GeneralSecurityException("failed to register key manager " + String.valueOf(zzgcVar.getClass()) + " as it is not FIPS compatible.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zze(zzgc zzgcVar) throws GeneralSecurityException {
        if (zzdv.zza(zzgcVar.zzf())) {
            zzi(new zzba(zzgcVar), false);
        } else {
            throw new GeneralSecurityException("failed to register key manager " + String.valueOf(zzgcVar.getClass()) + " as it is not FIPS compatible.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzf(String str) {
        return this.zzb.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbd(zzbd zzbdVar) {
        this.zzb = new ConcurrentHashMap(zzbdVar.zzb);
    }
}
