package com.libill.demos.activity

import java.util.*

class Contact(val index: String, val name: String) {

    companion object {
        val englishContacts: List<Contact>
            get() {
                val contacts: MutableList<Contact> = ArrayList()
                contacts.add(Contact("A", "Abbey"))
                contacts.add(Contact("A", "Alex"))
                contacts.add(Contact("A", "Amy"))
                contacts.add(Contact("A", "Anne"))
                contacts.add(Contact("B", "Betty"))
                contacts.add(Contact("B", "Bob"))
                contacts.add(Contact("B", "Brian"))
                contacts.add(Contact("C", "Carl"))
                contacts.add(Contact("C", "Candy"))
                contacts.add(Contact("C", "Carlos"))
                contacts.add(Contact("C", "Charles"))
                contacts.add(Contact("C", "Christina"))
                contacts.add(Contact("D", "David"))
                contacts.add(Contact("D", "Daniel"))
                contacts.add(Contact("E", "Elizabeth"))
                contacts.add(Contact("E", "Eric"))
                contacts.add(Contact("E", "Eva"))
                contacts.add(Contact("F", "Frances"))
                contacts.add(Contact("F", "Frank"))
                contacts.add(Contact("I", "Ivy"))
                contacts.add(Contact("J", "James"))
                contacts.add(Contact("J", "John"))
                contacts.add(Contact("J", "Jessica"))
                contacts.add(Contact("K", "Karen"))
                contacts.add(Contact("K", "Karl"))
                contacts.add(Contact("K", "Kim"))
                contacts.add(Contact("L", "Leon"))
                contacts.add(Contact("L", "Lisa"))
                contacts.add(Contact("P", "Paul"))
                contacts.add(Contact("P", "Peter"))
                contacts.add(Contact("S", "Sarah"))
                contacts.add(Contact("S", "Steven"))
                contacts.add(Contact("R", "Robert"))
                contacts.add(Contact("R", "Ryan"))
                contacts.add(Contact("T", "Tom"))
                contacts.add(Contact("T", "Tony"))
                contacts.add(Contact("W", "Wendy"))
                contacts.add(Contact("W", "Will"))
                contacts.add(Contact("W", "William"))
                contacts.add(Contact("Z", "Zoe"))
                return contacts
            }

        val chineseContacts: List<Contact>
            get() {
                val contacts: MutableList<Contact> = ArrayList()
                contacts.add(Contact("B", "白虎"))
                contacts.add(Contact("C", "常羲"))
                contacts.add(Contact("C", "嫦娥"))
                contacts.add(Contact("E", "二郎神"))
                contacts.add(Contact("F", "伏羲"))
                contacts.add(Contact("G", "观世音"))
                contacts.add(Contact("J", "精卫"))
                contacts.add(Contact("K", "夸父"))
                contacts.add(Contact("N", "女娲"))
                contacts.add(Contact("N", "哪吒"))
                contacts.add(Contact("P", "盘古"))
                contacts.add(Contact("Q", "青龙"))
                contacts.add(Contact("R", "如来"))
                contacts.add(Contact("S", "孙悟空"))
                contacts.add(Contact("S", "沙僧"))
                contacts.add(Contact("S", "顺风耳"))
                contacts.add(Contact("T", "太白金星"))
                contacts.add(Contact("T", "太上老君"))
                contacts.add(Contact("X", "羲和"))
                contacts.add(Contact("X", "玄武"))
                contacts.add(Contact("Z", "猪八戒"))
                contacts.add(Contact("Z", "朱雀"))
                contacts.add(Contact("Z", "祝融"))
                return contacts
            }

        val japaneseContacts: List<Contact>
            get() {
                val contacts: MutableList<Contact> = ArrayList()
                contacts.add(Contact("あ", "江户川コナン"))
                contacts.add(Contact("あ", "油女シノ"))
                contacts.add(Contact("あ", "犬夜叉"))
                contacts.add(Contact("か", "旗木カカシ"))
                contacts.add(Contact("か", "神楽"))
                contacts.add(Contact("か", "黒崎一護"))
                contacts.add(Contact("さ", "桜木花道"))
                contacts.add(Contact("さ", "坂田銀時"))
                contacts.add(Contact("さ", "殺生丸"))
                contacts.add(Contact("な", "奈良シカマル"))
                contacts.add(Contact("は", "旗木カカシ"))
                contacts.add(Contact("は", "日向ネジ"))
                contacts.add(Contact("や", "越前リョーマ"))
                contacts.add(Contact("や", "野比のび太"))
                contacts.add(Contact("や", "野原しんのすけ"))
                contacts.add(Contact("ら", "流川楓"))
                return contacts
            }
    }

}
