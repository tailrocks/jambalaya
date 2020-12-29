package com.zhokhov.jambalaya.seo;

import com.github.slugify.Slugify;
import com.ibm.icu.text.Normalizer2;
import edu.umd.cs.findbugs.annotations.NonNull;

import static com.zhokhov.jambalaya.checks.Preconditions.checkNotNull;

/**
 * The collection of static methods to work with the slug.
 *
 * @author Alexey Zhokhov
 */
public final class SlugUtils {

    private SlugUtils() {
    }

    private static final Slugify SLUGIFY = new Slugify();

    static {
        SLUGIFY.withCustomReplacement("+", " and ");
        SLUGIFY.withCustomReplacement("'", "");
        SLUGIFY.withCustomReplacement("·", " ");
        SLUGIFY.withTransliterator(true);
    }

    /**
     * Generates latin slug by using provided text.
     *
     * @param text the input text
     * @return generated slug
     */
    public static String generateSlug(@NonNull String text) {
        checkNotNull(text, "text");

        text = text.strip().trim().toLowerCase();
        text = normalizeSlug(text);

        return SLUGIFY.slugify(text);
    }

    /**
     * @param text the input text
     * @return normalized slug
     */
    private static String normalizeSlug(@NonNull String text) {
        text = Normalizer2.getNFDInstance().normalize(text);
        text = text.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return text;
    }

}
