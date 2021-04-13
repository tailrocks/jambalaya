/*
 * Copyright 2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    private static final Slugify SLUGIFY = new Slugify();

    static {
        SLUGIFY.withCustomReplacement("+", " and ");
        SLUGIFY.withCustomReplacement("'", "");
        SLUGIFY.withCustomReplacement("·", " ");
        SLUGIFY.withTransliterator(true);
    }

    private SlugUtils() {
    }

    /**
     * Generates latin slug by using provided text.
     *
     * @param text the input text
     * @return generated slug
     */
    public static String generateSlug(@NonNull String text) {
        checkNotNull(text, "text");

        text = text.trim().toLowerCase();
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
