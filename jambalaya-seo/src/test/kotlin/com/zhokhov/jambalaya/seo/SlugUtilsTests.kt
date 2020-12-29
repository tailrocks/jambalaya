package com.zhokhov.jambalaya.seo

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SlugUtilsTests {

    @Test
    fun generateSlug() {
        assertEquals("english", SlugUtils.generateSlug(" English  "))
        assertEquals("american-sign-language", SlugUtils.generateSlug("American  - Sign Language"))
        assertEquals("american-sign-language", SlugUtils.generateSlug("American  --- Sign Language"))
        assertEquals("nisgaa", SlugUtils.generateSlug("Nisga'a"))
        assertEquals("ache", SlugUtils.generateSlug("Aché"))
        assertEquals("kalmyk-oirat-2017", SlugUtils.generateSlug("Kalmyk-Oirat 2017"))
        assertEquals("dutch-english", SlugUtils.generateSlug("Dutch/ English"))
        assertEquals("portuguese-mz", SlugUtils.generateSlug("Portuguese (MZ)"))
        assertEquals("chinese-and-english", SlugUtils.generateSlug("Chinese + English"))
        assertEquals("bubble-gang-episode-1-730", SlugUtils.generateSlug("Bubble Gang Episode #1.730"))
        assertEquals("hui-season-1-episode-730", SlugUtils.generateSlug("Hui season 1 episode 730"))
        assertEquals("rina-en-un-cafe-1987", SlugUtils.generateSlug("Riña en un café 1987"))
        assertEquals("slagsmal-i-gamla-stockholm-1897", SlugUtils.generateSlug("Slagsmål i gamla Stockholm 1897"))
        assertEquals("terminator-2-judgment-day", SlugUtils.generateSlug("Terminator 2: Judgment Day"))
        assertEquals("kill-bill-vol-1", SlugUtils.generateSlug("Kill Bill: Vol. 1"))
        assertEquals("kill-bill-vol-1", SlugUtils.generateSlug("Kill Bill: Vol. 1--"))
        assertEquals("wall-e", SlugUtils.generateSlug("WALL·E"))
        assertEquals("star-wars-episode-vi-return-of-the-jedi", SlugUtils.generateSlug("Star Wars: Episode VI - Return of the Jedi"))
        assertEquals("x-men-first-class", SlugUtils.generateSlug("X-Men: First Class"))
        assertEquals("ni-hao-ma", SlugUtils.generateSlug("你好吗？"))
        assertEquals("shalopai", SlugUtils.generateSlug("шалопай"))
        assertEquals("zhe-ge-ren-shen-me-dou-bu-zhi-dao", SlugUtils.generateSlug("这个人什么都不知道"))
    }

}
