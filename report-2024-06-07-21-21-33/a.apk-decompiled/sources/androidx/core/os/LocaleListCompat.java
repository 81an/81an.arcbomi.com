package androidx.core.os;

import android.os.Build;
import android.os.LocaleList;
import java.util.Locale;

/* loaded from: classes.dex */
public final class LocaleListCompat {
    private static final LocaleListCompat sEmptyLocaleList = create(new Locale[0]);
    private LocaleListInterface mImpl;

    private LocaleListCompat(LocaleListInterface impl) {
        this.mImpl = impl;
    }

    @Deprecated
    public static LocaleListCompat wrap(Object localeList) {
        return wrap((LocaleList) localeList);
    }

    public static LocaleListCompat wrap(LocaleList localeList) {
        return new LocaleListCompat(new LocaleListPlatformWrapper(localeList));
    }

    public Object unwrap() {
        return this.mImpl.getLocaleList();
    }

    public static LocaleListCompat create(Locale... localeList) {
        if (Build.VERSION.SDK_INT >= 24) {
            return wrap(new LocaleList(localeList));
        }
        return new LocaleListCompat(new LocaleListCompatWrapper(localeList));
    }

    public Locale get(int index) {
        return this.mImpl.get(index);
    }

    public boolean isEmpty() {
        return this.mImpl.isEmpty();
    }

    public int size() {
        return this.mImpl.size();
    }

    public int indexOf(Locale locale) {
        return this.mImpl.indexOf(locale);
    }

    public String toLanguageTags() {
        return this.mImpl.toLanguageTags();
    }

    public Locale getFirstMatch(String[] supportedLocales) {
        return this.mImpl.getFirstMatch(supportedLocales);
    }

    public static LocaleListCompat getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    public static LocaleListCompat forLanguageTags(String list) {
        Locale forLanguageTagCompat;
        if (list == null || list.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] split = list.split(",", -1);
        int length = split.length;
        Locale[] localeArr = new Locale[length];
        for (int i = 0; i < length; i++) {
            if (Build.VERSION.SDK_INT >= 21) {
                forLanguageTagCompat = Locale.forLanguageTag(split[i]);
            } else {
                forLanguageTagCompat = forLanguageTagCompat(split[i]);
            }
            localeArr[i] = forLanguageTagCompat;
        }
        return create(localeArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Locale forLanguageTagCompat(String str) {
        if (str.contains("-")) {
            String[] split = str.split("-", -1);
            if (split.length > 2) {
                return new Locale(split[0], split[1], split[2]);
            }
            if (split.length > 1) {
                return new Locale(split[0], split[1]);
            }
            if (split.length == 1) {
                return new Locale(split[0]);
            }
        } else if (str.contains("_")) {
            String[] split2 = str.split("_", -1);
            if (split2.length > 2) {
                return new Locale(split2[0], split2[1], split2[2]);
            }
            if (split2.length > 1) {
                return new Locale(split2[0], split2[1]);
            }
            if (split2.length == 1) {
                return new Locale(split2[0]);
            }
        } else {
            return new Locale(str);
        }
        throw new IllegalArgumentException("Can not parse language tag: [" + str + "]");
    }

    public static LocaleListCompat getAdjustedDefault() {
        return Build.VERSION.SDK_INT >= 24 ? wrap(LocaleList.getAdjustedDefault()) : create(Locale.getDefault());
    }

    public static LocaleListCompat getDefault() {
        return Build.VERSION.SDK_INT >= 24 ? wrap(LocaleList.getDefault()) : create(Locale.getDefault());
    }

    public boolean equals(Object other) {
        return (other instanceof LocaleListCompat) && this.mImpl.equals(((LocaleListCompat) other).mImpl);
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public String toString() {
        return this.mImpl.toString();
    }
}
